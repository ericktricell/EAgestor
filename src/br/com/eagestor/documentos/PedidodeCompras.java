/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eagestor.documentos;

import br.com.eagestor.domain.Pedidoprodutos;
import br.com.eagestor.domain.Pedidos;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
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

/**
 *
 * @author Erick
 */
public class PedidodeCompras {
    
    public void emite(int numDoc, Pedidos ped) throws DocumentException, FileNotFoundException{
        String DEST = "C:\\Users\\Public\\Documents\\EAGestor\\Pedido\\Pedido" + numDoc + ".pdf";
        
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(DEST));
        
        document.open();
        
        Font fontNegrito = FontFactory.getFont(FontFactory.TIMES_ROMAN, 14,Font.BOLD, black );
        Font fontNormal = FontFactory.getFont(FontFactory.TIMES_ROMAN, 14,Font.NORMAL, black );
        
        Paragraph documento = new Paragraph("PEDIDO DE COMPRA DE PRODUTOS\n\n", fontNegrito);
        
        PdfPTable docdata = new PdfPTable(2);
        docdata.setWidthPercentage(40);
        docdata.setWidths(new float[] {3, 7});
        docdata.setHorizontalAlignment(Element.ALIGN_LEFT);
        docdata.addCell("Pedido nº:");
        docdata.addCell(String.valueOf(numDoc));
        docdata.addCell("Data:");
        docdata.addCell(new SimpleDateFormat("EEEE, d' de 'MMMM' de 'yyyy").format(new Date()));
        
        PdfPTable produtosTable = new PdfPTable(5);
        produtosTable.setWidths(new float[]{
            1, 5, 3, 3, 2
        });
        produtosTable.setWidthPercentage(100);
        produtosTable.addCell("Item");
        produtosTable.addCell("Decrição do Produto");
        produtosTable.addCell("QTD/Medida");
        produtosTable.addCell("Vlr. Unit.");
        produtosTable.addCell("Total");
        double totalPedido = 0;
        int i = 1;
        for(Pedidoprodutos vp : ped.getPedidoprodutosList()){
            produtosTable.addCell(String.valueOf(i));
            produtosTable.addCell(vp.getProdutos().getNome());
            produtosTable.addCell(String.valueOf(vp.getQuantidade()) + " " + vp.getProdutos().getIdMedida().getUnidade());
            produtosTable.addCell(new DecimalFormat("¤#,##0.00").format(vp.getProdutos().getValorCompra()));
            produtosTable.addCell(new DecimalFormat("¤#,##0.00").format(vp.getQuantidade() * vp.getProdutos().getValorCompra()));
            i++;
            totalPedido += vp.getQuantidade() * vp.getProdutos().getValorCompra();
        }
        
        PdfPTable total = new PdfPTable(2);
        total.setWidthPercentage(30);
        total.setHorizontalAlignment(Element.ALIGN_RIGHT);
        total.addCell("Total do Pedido:");
        total.addCell(new DecimalFormat("¤#,##0.00").format(totalPedido));
        
        PdfPTable obsT = new PdfPTable(1);
        obsT.setWidthPercentage(100);
        obsT.addCell("OBSERVAÇÃO");
        obsT.addCell(ped.getObs());
        
        PdfPTable assinaturas = new PdfPTable(1);
        assinaturas.setWidthPercentage(100);
        assinaturas.addCell(new Paragraph("Responsável pela Aprovação", fontNegrito));
        assinaturas.addCell("");
        
        document.add(documento);
        document.add(docdata);
        document.add(new Paragraph("\nCONFORME ORÇAMENTO Nº " + numDoc +"\n\n", fontNegrito));
        document.add(produtosTable);
        document.add(new Paragraph("\n"));
        document.add(total);
        document.add(new Paragraph("\n"));
        document.add(obsT);
        document.add(new Paragraph("\n"));
        document.add(assinaturas);
        
        document.close();
        
        try {
            Runtime.getRuntime().exec("cmd /c start C:\\Users\\Public\\Documents\\EAGestor\\Pedido\\Pedido" + numDoc + ".pdf");
        } catch (IOException ex) {
            Logger.getLogger(PedidodeCompras.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
