/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eagestor.dao;

import br.com.eagestor.domain.Fornecedor;
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
public class FornecedorDAO {

    public void salvar(Fornecedor fo){
        Session sessao = HibernateUtil.getSessionfactory().openSession();
        Transaction transacao = null;
        try{
            transacao = sessao.beginTransaction();
            sessao.save(fo);
            transacao.commit();
            JOptionPane.showMessageDialog(null, "Salvo com sucesso");
        } catch(Exception e){
            e.printStackTrace();
            transacao.rollback();
            sessao.close();
        } finally{
            sessao.close();
        }
    }
    
    public void atualizar(Fornecedor fo){
        Session sessao = HibernateUtil.getSessionfactory().openSession();
        Transaction transacao = null;
        try{
            transacao = sessao.beginTransaction();
            sessao.update(fo);
            transacao.commit();
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso");
        } catch(Exception e){
            e.printStackTrace();
            transacao.rollback();
            sessao.close();
        } finally{
            sessao.close();
        }
    }
    
    public void excluir(Fornecedor fo){
        Session sessao = HibernateUtil.getSessionfactory().openSession();
        Transaction transacao = null;
        try{
            transacao = sessao.beginTransaction();
            sessao.delete(fo);
            transacao.commit();
            JOptionPane.showMessageDialog(null, "Excluido com sucesso");
        } catch(Exception e){
            e.printStackTrace();
            transacao.rollback();
            sessao.close();
        } finally{
            sessao.close();
        }
    }
    
    public List<Fornecedor> busca(){
        Session sessao = HibernateUtil.getSessionfactory().openSession();
        List<Fornecedor> list = null;
        
        try{
            Query q = sessao.getNamedQuery("Fornecedor.findAll");
            list = q.list();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        } finally{
            sessao.close();
        }
        return list;
    }
    
    public List<Fornecedor> busca(String nome){
        Session sessao = HibernateUtil.getSessionfactory().openSession();
        List<Fornecedor> list = null;
        
        try{
            Query q = sessao.getNamedQuery("Fornecedor.findByRazaoSocial");
            q.setParameter("razaoSocial", "%" + nome + "%");
            q.setParameter("nomeFantasia", "%" + nome + "%");
            list = q.list();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        } finally{
            sessao.close();
        }
        return list;
    }
    
}
