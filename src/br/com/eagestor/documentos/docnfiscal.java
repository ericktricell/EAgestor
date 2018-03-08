/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eagestor.documentos;

import br.com.eagestor.domain.Empresa;
import br.com.eagestor.domain.Recebimento;
import br.com.eagestor.domain.Vendaproduto;
import br.com.eagestor.util.HibernateUtil;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import static java.awt.Color.black;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Erick
 */
public class docnfiscal {
    
    public Empresa getEmpresa(){
        Session s = HibernateUtil.getSessionfactory().openSession();
        Empresa em = new Empresa();
        try{
            Query q = s.getNamedQuery("empresa.getAll");
            em = (Empresa) q.uniqueResult();
        } catch (Exception e){
            e.printStackTrace();
        } finally{
            s.close();
        }
        return em;
    }
    
    public void emite(String numDoc, Recebimento rec, boolean cliente, String vlrpago, String troco) throws DocumentException, FileNotFoundException{
        String DEST = "C:\\Users\\Public\\Documents\\EAGestor\\DocnFiscal\\nfiscal" + numDoc + ".pdf";
        
        Empresa em = new Empresa();
        em = this.getEmpresa();
        
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(DEST));
        
        document.open();
        
        Font fontNegrito = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12,Font.BOLD, black );
        Font fontNormal = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12,Font.NORMAL, black );
        
        Paragraph line = new Paragraph("_____________________________________________________________________________");
        Paragraph cabecalho = new Paragraph("DOCUMENTO AUXILIAR DE VENDA\n"
                + "NÃO É DOCUMENTO FISCAL\n"
                + "NÃO É VALIDO COMO RECIBO E COMO GARANTIA DE MERCADORIA\n"
                + "NÃO COMPROVA PAGAMENTO\n", fontNegrito);
        cabecalho.setAlignment(Element.ALIGN_CENTER);
        
        Paragraph loja = new Paragraph("IDENTIFICAÇÃO DO ESTABELECIMENTO EMITENTE\n"
                + "DENOMINAÇÃO:\t" + em.getDenominacao() + "\nCNPJ:\t " + em.getCnpj() + "\n"
                + em.getLogradouro() + ", " + em.getNumero() + ", " + em.getBairro() + ", " + 
                em.getCidade() + "-" + em.getEstado());
        
        Paragraph clienteDado;
        if (cliente){
            clienteDado = new Paragraph("IDENTIFICAÇÃO DO DESTINATARIO\n"
                    + "Nome:  " + rec.getIdCliente().getNome() + " " + rec.getIdCliente().getSobrenome() + "\n"
                    + "CNPJ/CPF:  " + rec.getIdCliente().getCpf() + "\n"
                    + "Endereço:  " + rec.getIdCliente().getIdEndereco().getLogradouro() + " Nº: " + rec.getIdCliente().getIdEndereco().getNumero() + "\n"
                    + "Bairro:  " + rec.getIdCliente().getIdEndereco().getBairro() + "\n"
                    + "Cidade:  " + rec.getIdCliente().getIdEndereco().getCidade() + "\n"
                    + "Contatos:  " + rec.getIdCliente().getIdContato().getCelular() + " - " + rec.getIdCliente().getIdContato().getComercial() + "\n"
                    + "Data:  " + new SimpleDateFormat("dd/MM/yyyy").format(new Date()) + "\t Hora:  " + new SimpleDateFormat("HH:mm").format(new Date()));
        } else{
            clienteDado = new Paragraph("Cliente não Identificado");
        }
        Paragraph nDoc = new Paragraph("Nº do doc.: " + numDoc + "\n\n\n");
        
        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100);
        table.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.setWidths(new int[]{ 1, 4, 2, 2, 2, 3});
        table.getDefaultCell().setBorder(PdfPCell.NO_BORDER); // Aqui eu tiro a borda
        
        table.addCell("Item");
        table.addCell("Descrição");
        table.addCell("UN");
        table.addCell("QTD");
        table.addCell("Prc.Uni");
        table.addCell("Vlr.Total");
        
        int i = 1;
        double total = 0;
        for(Vendaproduto vp : rec.getVendaprodutoList()){
            table.addCell(String.valueOf(i));
            table.addCell(vp.getProdutos().getNome());
            table.addCell(vp.getProdutos().getIdMedida().getDescricao());
            table.addCell(String.valueOf(vp.getQuantidade()));
            table.addCell(new DecimalFormat("¤#,##0.00").format(vp.getValorVenda()));
            table.addCell(new DecimalFormat("¤#,##0.00").format(vp.getQuantidade() * vp.getValorVenda()));
            total += vp.getQuantidade() * vp.getValorVenda();
            i++;
        }   
        
        PdfPTable table1 = new PdfPTable(2);
        table1.setWidthPercentage(100);
        table1.setWidths(new float[]{8, 2});
        table1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table1.getDefaultCell().setBorder(PdfPCell.NO_BORDER); // Aqui eu tiro a borda
        
        Paragraph info1 = new Paragraph("QTD total de itens:");
        info1.setAlignment(Element.ALIGN_LEFT);
        table1.addCell(info1);
        
        Paragraph totalD1 = new Paragraph(String.valueOf(rec.getVendaprodutoList().size()));
        totalD1.setAlignment(Element.ALIGN_RIGHT);
        table1.addCell(totalD1);
        
        Paragraph info = new Paragraph("Vlr. Total:");
        info.setAlignment(Element.ALIGN_LEFT);
        table1.addCell(info);
        
        Paragraph totalD = new Paragraph(new DecimalFormat("¤#,##0.00").format(total));
        totalD.setAlignment(Element.ALIGN_RIGHT);
        table1.addCell(totalD);
        
        Paragraph info2 = new Paragraph("Forma Pagamento:");
        info2.setAlignment(Element.ALIGN_LEFT);
        table1.addCell(info2);
        
        Paragraph totalD2 = new Paragraph("VLR PAGO");
        totalD2.setAlignment(Element.ALIGN_RIGHT);
        table1.addCell(totalD2);
        
        Paragraph info3 = new Paragraph(rec.getFormaPagamento());
        info3.setAlignment(Element.ALIGN_LEFT);
        table1.addCell(info3);
        
        Paragraph totalD3 = new Paragraph(vlrpago);
        totalD3.setAlignment(Element.ALIGN_RIGHT);
        table1.addCell(totalD3);
        
        Paragraph info4 = new Paragraph("Troco");
        info4.setAlignment(Element.ALIGN_LEFT);
        table1.addCell(info4);
        
        Paragraph totalD4 = new Paragraph(troco);
        totalD4.setAlignment(Element.ALIGN_RIGHT);
        table1.addCell(totalD4);
        
        document.add(cabecalho);
        document.add(line);
        document.add(loja);
        document.add(line);
        document.add(clienteDado);
        document.add(line);
        document.add(nDoc);
        document.add(table);
        document.add(new Paragraph("\n\n"));
        document.add(table1);
        document.close();
        
        try {
            Runtime.getRuntime().exec("cmd /c start C:\\Users\\Public\\Documents\\EAGestor\\DocnFiscal\\nfiscal" + numDoc + ".pdf");
        } catch (IOException ex) {
            Logger.getLogger(docnfiscal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
