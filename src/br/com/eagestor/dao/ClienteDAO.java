/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eagestor.dao;

import br.com.eagestor.domain.Cliente;
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
public class ClienteDAO {
    
    public void salvar(Cliente f){
        Session s = HibernateUtil.getSessionfactory().openSession();
        Transaction t = null;        
        try{
            t = s.beginTransaction();
            s.save(f);
            t.commit();
            JOptionPane.showMessageDialog(null, "Salvo com sucesso");
        } catch(Exception e){
            e.printStackTrace();
            t.rollback();
            s.close();
        } finally{
            s.close();
        }
    }
    
    
    public void atualizar(Cliente f){
        Session s = HibernateUtil.getSessionfactory().openSession();
        Transaction t = null;        
        try{
            t = s.beginTransaction();
            s.update(f);
            t.commit();
            JOptionPane.showMessageDialog(null, "Alterado com sucesso");
        } catch(Exception e){
            e.printStackTrace();
            t.rollback();
            s.close();
        } finally{
            s.close();
        }
    }
    
    public void excluir(Cliente f){
        Session s = HibernateUtil.getSessionfactory().openSession();
        Transaction t = null;        
        try{
            t = s.beginTransaction();
            s.delete(f);
            t.commit();
            JOptionPane.showMessageDialog(null, "Excluido com sucesso");
        } catch(Exception e){
            e.printStackTrace();
            t.rollback();
            s.close();
        } finally{
            s.close();
        }
    }
    
    public List<Cliente> busca(){
        List<Cliente> list;
        Session s = HibernateUtil.getSessionfactory().openSession();
        try{
            Query q = s.getNamedQuery("Cliente.findAll");
            list = q.list();
        } catch (Exception e){
            e.printStackTrace();
            s.close();
            return null;
        } finally{
            s.close();
        }
            return list;
    }
    
    public List<Cliente> busca(String txt){
        List<Cliente> list;
        Session s = HibernateUtil.getSessionfactory().openSession();
        try{
            Query q = s.getNamedQuery("Cliente.findByNome");
            q.setParameter("nome", "%" + txt + "%");
            list = q.list();
        } catch (Exception e){
            e.printStackTrace();
            s.close();
            return null;
        } finally{
            s.close();
        }
            return list;
    }
}
