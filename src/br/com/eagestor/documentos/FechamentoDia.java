/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eagestor.documentos;

import br.com.eagestor.dao.FinanceiroDAO;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import static java.awt.Color.black;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Erick
 */
public class FechamentoDia {
    public void emite() throws DocumentException, FileNotFoundException{
        String DEST = "C:\\Users\\Public\\Documents\\EAGestor\\DocnFiscal\\fechamento_" +
                new SimpleDateFormat("dd_MM_yyyy").format(new Date()) + ".pdf";
        
        DecimalFormat money = new DecimalFormat("¤#,##0.00");
        
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(DEST));
        
        document.open();
        
        Font fontNegrito = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12,Font.BOLD, black );
        Font fontNormal = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12,Font.NORMAL, black );
        Font fontData = FontFactory.getFont(FontFactory.TIMES_ROMAN, 10,Font.ITALIC, black );
        
        Paragraph cabecalho = new Paragraph("FECHAMENTO DO DIA\n\n\n", fontNegrito);
        cabecalho.setAlignment(Element.ALIGN_CENTER);
        
        Paragraph data = new Paragraph(new SimpleDateFormat("EEEE, d' de 'MMMM' de 'yyyy").format(new Date()) + "\n\n", fontData);
        data.setAlignment(Element.ALIGN_LEFT);
        
        document.add(cabecalho);
        document.add(data);
        document.close();
    }
}
