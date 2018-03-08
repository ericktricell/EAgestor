/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eagestor.bean;

import br.com.eagestor.dao.ComprasDAO;
import br.com.eagestor.domain.Compras;
import javax.swing.JOptionPane;
/**
 *
 * @author Erick
 */
public class ComprasBean {
    
    public void salvaCompra(Compras c){
        new ComprasDAO().salvaCompra(c);
        new ComprasDAO().entradaProduto(c);
        JOptionPane.showMessageDialog(null, "Salvo com sucesso");
    }
}
