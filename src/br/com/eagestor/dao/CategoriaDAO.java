/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eagestor.dao;

import br.com.eagestor.domain.Categoria;
import br.com.eagestor.util.HibernateUtil;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Erick
 */
public class CategoriaDAO {
    
    public void salvar(Categoria cat){
        Session sessao = HibernateUtil.getSessionfactory().openSession();
        Transaction transacao = null;
        try {
            // inicializa a transação
            transacao = sessao.beginTransaction();
            // persiste o dado
            sessao.save(cat);
            transacao.commit();
        } catch (Exception e) {
            transacao.rollback();
            e.printStackTrace();
        }finally{
            sessao.close();
        }
    }
    
    public List<Categoria> buscar(){
        Session sessao = HibernateUtil.getSessionfactory().openSession();
        List<Categoria> cat = null;
        try{
            Query query = sessao.getNamedQuery("Categoria.findAll");
            cat = query.list();
        }catch (RuntimeException e){
            e.printStackTrace();
            return null;
        }finally{
            sessao.close();
        } 
        return cat;
    }   
    
    public List<Categoria> buscar(String txt){
        Session sessao = HibernateUtil.getSessionfactory().openSession();
        List<Categoria> cat = null;
        try{
            Query query = sessao.getNamedQuery("Categoria.findByCategoria");
            query.setString("categoria", "%" + txt + "%");
            cat = query.list();
        }catch (RuntimeException e){
            e.printStackTrace();
            return null;
        }finally{
            sessao.close();
        } 
        return cat;
    }   
    
    public void excluir(Categoria cat){
        Session sessao = HibernateUtil.getSessionfactory().openSession();
        Transaction transacao = null;
        try{
            transacao = sessao.beginTransaction();
            sessao.delete(cat);
            transacao.commit();
            JOptionPane.showMessageDialog(null, "Excluido com sucesso");
        } catch(Exception e){
            e.printStackTrace();
            transacao.rollback();
        }finally{
            sessao.close();
        }
    }
}
