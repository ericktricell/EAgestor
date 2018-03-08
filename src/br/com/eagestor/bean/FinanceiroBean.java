/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eagestor.bean;

import br.com.eagestor.dao.FinanceiroDAO;
import br.com.eagestor.documentos.docnfiscal;
import br.com.eagestor.domain.Caixadodia;
import br.com.eagestor.domain.Recebimento;
import com.lowagie.text.DocumentException;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Erick
 */
public class FinanceiroBean {
    
    public void abreCaixa(double valorInicial, Date data){
        Caixadodia caixa = new Caixadodia();
        caixa.setData(data);
        caixa.setValorInicial(valorInicial);
        
        new FinanceiroDAO().abreCaixa(caixa);
    }
    
    public void registroVenda(Recebimento rec, boolean cliente, String cod, String vlrPago, String troco){
        new FinanceiroDAO().registraVenda(rec);
        new FinanceiroDAO().registraBaixa(rec);
        JOptionPane.showMessageDialog(null, "Venda registrada com sucesso");
        try {
            new docnfiscal().emite(cod, rec, cliente, vlrPago, troco);
        } catch (DocumentException ex) {
            Logger.getLogger(FinanceiroBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FinanceiroBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
