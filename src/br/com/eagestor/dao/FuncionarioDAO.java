/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eagestor.dao;

import br.com.eagestor.domain.Funcionario;
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
public class FuncionarioDAO {
    
    public void salvar(Funcionario f){
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
    
    
    public void atualizar(Funcionario f){
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
    
    public void excluir(Funcionario f){
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
    
    public List<Funcionario> busca(){
        List<Funcionario> list;
        Session s = HibernateUtil.getSessionfactory().openSession();
        try{
            Query q = s.getNamedQuery("Funcionario.findAll");
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
    
    public List<Funcionario> busca(String txt){
        List<Funcionario> list;
        Session s = HibernateUtil.getSessionfactory().openSession();
        try{
            Query q = s.getNamedQuery("Funcionario.findByNome");
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
