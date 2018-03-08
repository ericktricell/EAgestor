/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eagestor.documentos;

import br.com.eagestor.dao.ComprasDAO;
import br.com.eagestor.dao.FinanceiroDAO;
import br.com.eagestor.domain.Compraprodutos;
import br.com.eagestor.domain.Despesas;
import com.lowagie.text.BadElementException;
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
import java.util.List;

/**
 *
 * @author Erick
 */
public class FechamentoMes {
    public void emite() throws DocumentException, FileNotFoundException, BadElementException, IOException{
        String DEST = "C:\\Users\\Public\\Documents\\EAGestor\\Fechamento_mensal\\Mes" + new SimpleDateFormat("MM_yyyy").format(new Date()) + ".pdf";
        
        DecimalFormat money = new DecimalFormat("¤#,##0.00");
        
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(DEST));
        
        document.open();
        
        Font fontNegrito = FontFactory.getFont(FontFactory.TIMES_ROMAN, 14,Font.BOLD, black );
        Font fontNormal = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12,Font.NORMAL, black );
        Font fontData = FontFactory.getFont(FontFactory.TIMES_ROMAN, 10,Font.ITALIC, black );
        
        Paragraph cabecalho = new Paragraph("FECHAMENTO DO MÊS\n\n\n", fontNegrito);
        cabecalho.setAlignment(Element.ALIGN_CENTER);
        
        Paragraph data = new Paragraph(new SimpleDateFormat("EEEE, d' de 'MMMM' de 'yyyy").format(new Date()) + "\n\n", fontData);
        data.setAlignment(Element.ALIGN_LEFT);
        
        String recPrej  = "";
        if (this.calculaReceita() < 0){
            recPrej = "houve prejuizo de " + money.format(this.calculaReceita());
        } else if (this.calculaReceita() > 0){
            recPrej = "houve uma receita de " + money.format(this.calculaReceita());
        } else {
            recPrej = "não houve receita e nem prejuizo";
        }
        
        Paragraph intro = new Paragraph("   O fechamento bruto é de " + money.format(new FinanceiroDAO().getTotalMensal()) +
           ", sendo que neste mês " + recPrej + ". Conforme apresentado abaixo:\n\n", fontNormal);
        intro.setAlignment(Element.ALIGN_JUSTIFIED);
        
        PdfPTable demoReceita = new PdfPTable(2);
        demoReceita.setWidthPercentage(60);
        demoReceita.setHorizontalAlignment(Element.ALIGN_LEFT);
        demoReceita.setWidths(new float[]{ 6, 4});
        demoReceita.addCell(new Paragraph("Total Recebimentos", fontNormal));
        demoReceita.addCell(new Paragraph(money.format(new FinanceiroDAO().getTotalMensal()), fontNormal));
        demoReceita.addCell(new Paragraph("Total Despesas", fontNormal));
        demoReceita.addCell(new Paragraph(money.format(new FinanceiroDAO().getDespesaMes()), fontNormal));
        demoReceita.addCell(new Paragraph("Total Compras", fontNormal));
        demoReceita.addCell(new Paragraph(money.format(new FinanceiroDAO().getTotalCompras()), fontNormal));
        demoReceita.addCell(new Paragraph("Receita/Prejuizo", fontNormal));
        demoReceita.addCell(new Paragraph(money.format(this.calculaReceita()), fontNormal));
        
        Paragraph resDespesas = new Paragraph("\n\nRESUMO DESPESAS\n\n", fontNegrito);
        resDespesas.setAlignment(Element.ALIGN_CENTER);
        
        List<Despesas> listDesp = new FinanceiroDAO().getDespesaTodas();
        
        PdfPTable tableDesp = new PdfPTable(4);
        tableDesp.setWidthPercentage(100);
        tableDesp.setHorizontalAlignment(Element.ALIGN_CENTER);
        tableDesp.addCell(new Paragraph("Descrição", fontNormal));
        tableDesp.addCell(new Paragraph("Valor", fontNormal));
        tableDesp.addCell(new Paragraph("Vencimento", fontNormal));
        tableDesp.addCell(new Paragraph("Data Pagamento", fontNormal));
        
        for (Despesas d : listDesp){
            tableDesp.addCell(new Paragraph(d.getDescricao(), fontNormal));
            tableDesp.addCell(new Paragraph(money.format(d.getValor()), fontNormal));
            tableDesp.addCell(new Paragraph(new SimpleDateFormat("dd/MM/yyyy").format(d.getVencimento()), fontNormal));
            tableDesp.addCell(new Paragraph(new SimpleDateFormat("dd/MM/yyyy").format(d.getDataPagamento()), fontNormal));
        }
        
        Paragraph proxDespesas = new Paragraph("\n\nDESPESAS A VENCER\n\n", fontNegrito);
        proxDespesas.setAlignment(Element.ALIGN_CENTER);
        
        List<Despesas> listPDesp = new FinanceiroDAO().getDespesaVencer()   ;
        PdfPTable tableDesp1 = new PdfPTable(3);
        tableDesp1.setWidthPercentage(100);
        tableDesp1.setHorizontalAlignment(Element.ALIGN_CENTER);
        tableDesp1.addCell(new Paragraph("Descrição", fontNormal));
        tableDesp1.addCell(new Paragraph("Valor", fontNormal));
        tableDesp1.addCell(new Paragraph("Vencimento", fontNormal));
        
        for (Despesas d : listPDesp){
            tableDesp1.addCell(new Paragraph(d.getDescricao(), fontNormal));
            tableDesp1.addCell(new Paragraph(money.format(d.getValor()), fontNormal));
            tableDesp1.addCell(new Paragraph(new SimpleDateFormat("dd/MM/yyyy").format(d.getVencimento()), fontNormal));
        }
        
        Paragraph compra = new Paragraph("\n\nDESPESAS RELATIVAS A COMPRAS\n\n", fontNegrito);
        compra.setAlignment(Element.ALIGN_CENTER);
        
        PdfPTable compraProd = new PdfPTable(4);
        compraProd.setWidthPercentage(100);
        compraProd.setHorizontalAlignment(Element.ALIGN_CENTER);
        compraProd.addCell(new Paragraph("Produto", fontNormal));
        compraProd.addCell(new Paragraph("QTD", fontNormal));
        compraProd.addCell(new Paragraph("UN", fontNormal));
        compraProd.addCell(new Paragraph("Valor Compra", fontNormal));
        
        List<Compraprodutos> listCP = new FinanceiroDAO().getComprasProd();
        for(Compraprodutos cp : listCP){
            compraProd.addCell(new Paragraph(cp.getProdutos().getNome(), fontNormal));
            compraProd.addCell(new Paragraph(String.valueOf(cp.getQuantidade()), fontNormal));
            compraProd.addCell(new Paragraph(cp.getProdutos().getIdMedida().getUnidade(), fontNormal));
            compraProd.addCell(new Paragraph(money.format(cp.getValorCompra()), fontNormal));
        }
        
        Paragraph resEstoque = new Paragraph("\n\nRESUMO ESTOQUE\n\n", fontNegrito);
        resEstoque.setAlignment(Element.ALIGN_CENTER);
        
        Paragraph infoEst = new Paragraph("Atualmente há em estoque um montante de " +
                money.format(new FinanceiroDAO().getTotalEstoque()) +
                " em produtos.\n Obs.: O valor citado acima é o referente a compra dos mesmos, ou seja, o valor de custo.\n\n", fontNormal);
        infoEst.setAlignment(Element.ALIGN_JUSTIFIED);
        
        Paragraph infoVal = new Paragraph("Segue abaixo a listagem dos produtos a vencerem em até 2(dois) meses.\n\n", fontNormal);
        infoVal.setAlignment(Element.ALIGN_CENTER);
        
        PdfPTable validade = new PdfPTable(6);
        validade.setWidthPercentage(100);
        validade.setHorizontalAlignment(Element.ALIGN_CENTER);
        validade.addCell(new Paragraph("Produto", fontNormal));
        validade.addCell(new Paragraph("QTD/UN", fontNormal));
        validade.addCell(new Paragraph("VLR Compra", fontNormal));
        validade.addCell(new Paragraph("VLR Venda", fontNormal));
        validade.addCell(new Paragraph("Validade", fontNormal));
        validade.addCell(new Paragraph("Nº Lote", fontNormal));
        
        List<Compraprodutos> lis  = new ComprasDAO().getValidade();
        for (Compraprodutos c : lis){
            validade.addCell(new Paragraph(c.getProdutos().getNome(), fontNormal));
            validade.addCell(new Paragraph(String.valueOf(c.getQuantidade()) + " " + c.getProdutos().getIdMedida().getUnidade(), fontNormal));
            validade.addCell(new Paragraph(money.format(c.getValorCompra()), fontNormal));
            validade.addCell(new Paragraph(money.format(c.getProdutos().getValorVenda()), fontNormal));
            validade.addCell(new Paragraph(new SimpleDateFormat("dd/MM/yyyy").format(c.getDataValidade()), fontNormal));
            validade.addCell(new Paragraph(c.getNumLote(), fontNormal));
        }
        
        document.add(cabecalho);
        document.add(data);
        document.add(intro);
        document.add(demoReceita);
        document.add(resDespesas);
        if (listDesp.isEmpty())
            document.add(new Paragraph("Não há registro de despesa paga no mês anterior", fontNormal));
        else
            document.add(tableDesp);
        document.add(proxDespesas);
        document.add(tableDesp1);
        document.add(compra);
        document.add(compraProd);
        document.add(resEstoque);
        document.add(infoEst);
        document.add(infoVal);
        document.add(validade);
        document.close();
        
        Runtime.getRuntime().exec("cmd /c start C:\\Users\\Public\\Documents\\EAGestor\\Fechamento_mensal\\Mes" + new SimpleDateFormat("MM_yyyy").format(new Date()) + ".pdf");
    }
    
    private double calculaReceita(){
        double totRec = new FinanceiroDAO().getTotalMensal();
        double totDes = new FinanceiroDAO().getDespesaMes();
        double totComp = new FinanceiroDAO().getTotalCompras();
        double receita = totRec - (totDes + totComp);
        
        return receita;
    }
    
}
