/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eagestor.dao;

import br.com.eagestor.domain.Medidas;
import br.com.eagestor.domain.Produtos;
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
public class ProdutosDAO {
    
    public void salvar(Produtos prod){
        Session sessao = HibernateUtil.getSessionfactory().openSession();
        Transaction transacao = null;
        
        try{
            transacao = sessao.beginTransaction();
            sessao.save(prod);
            transacao.commit();
            JOptionPane.showMessageDialog(null, "Salvo com sucesso");
        } catch (Exception e){
            e.printStackTrace();
            transacao.rollback();
            sessao.close();
        } finally{
            sessao.close();
        }
    }
    
    public List<Produtos> listaProdutos(){
        
        Session sessao = HibernateUtil.getSessionfactory().openSession();
        List<Produtos> list = null;
        
        try{
            Query query = sessao.getNamedQuery("Produtos.findAll");
            list = query.list();
        }catch (RuntimeException e){
            e.printStackTrace();
            return null;
        }finally{
            sessao.close();
        } 
        return list;
    }
    
    
    public List<Medidas> listaMedidas(){
        
        Session sessao = HibernateUtil.getSessionfactory().openSession();
        List<Medidas> list = null;
        
        try{
            Query query = sessao.getNamedQuery("Medidas.findAll");
            list = query.list();
        }catch (RuntimeException e){
            e.printStackTrace();
            return null;
        }finally{
            sessao.close();
        } 
        return list;
    }
    
    public List<Produtos> listaProdutos(String txt){
        
        Session sessao = HibernateUtil.getSessionfactory().openSession();
        List<Produtos> list = null;
        
        try{
            Query query = sessao.getNamedQuery("Produtos.findByNome");
            query.setString("nome", "%" + txt + "%");
            list = query.list();
        }catch (RuntimeException e){
            e.printStackTrace();
            return null;
        }finally{
            sessao.close();
        } 
        return list;
    }
    
    public void exluir(Produtos prod){
        Session sessao = HibernateUtil.getSessionfactory().openSession();
        Transaction transacao = null;
        try{
            transacao = sessao.beginTransaction();
            sessao.delete(prod);
            transacao.commit();
            JOptionPane.showMessageDialog(null, "Produto excluido com sucesso");
        } catch (Exception e){
            e.printStackTrace();
            transacao.rollback();
            sessao.close();
        } finally{
            sessao.close();
        }
    }
    
    public void atualizar(Produtos prod){
        Session sessao = HibernateUtil.getSessionfactory().openSession();
        Transaction transacao = null;
        try{
            transacao = sessao.beginTransaction();
            sessao.update(prod);
            transacao.commit();
            JOptionPane.showMessageDialog(null, "Dados atualizados com sucesso");
        } catch (Exception e){
            e.printStackTrace();
            transacao.rollback();
            sessao.close();
        } finally{
            sessao.close();
        }
    }
    
    public void reajuste(Produtos p){
        Session s = HibernateUtil.getSessionfactory().openSession();
        try{
            Query q = s.getNamedQuery("Produtos.reajuste");
            q.setParameter("venda", p.getValorVenda());
            q.setParameter("id", p.getIdProduto());
            q.executeUpdate();
            JOptionPane.showMessageDialog(null, "Valor reajustado");
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            s.close();
        }
    }
}
