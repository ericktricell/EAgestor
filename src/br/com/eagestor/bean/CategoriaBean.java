/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eagestor.bean;

import br.com.eagestor.dao.CategoriaDAO;
import br.com.eagestor.domain.Categoria;
import javax.swing.JOptionPane;

/**
 *
 * @author Erick
 */
public class CategoriaBean {
    
    public void salvar(String categoria) {
        
        if (!(categoria.equals(""))){
            Categoria cat = new Categoria();
            cat.setCategoria(categoria);
            new CategoriaDAO().salvar(cat);
            JOptionPane.showMessageDialog(null, "Salvo com sucesso");
        } else {
            JOptionPane.showMessageDialog(null, "Campo Vazio, preencha o dado");
        }
    }
    
    public void excluir(Categoria c){
        Categoria cat = c;
        new CategoriaDAO().excluir(cat);
        
    }
    
}
