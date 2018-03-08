/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eagestor.dao;

import br.com.eagestor.domain.Pedidos;
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
public class PedidosDAO {
    public void salvarPedido(Pedidos p){
        Session s = HibernateUtil.getSessionfactory().openSession();
        Transaction t = null;
        try{
            t = s.beginTransaction();
            s.save(p);
            t.commit();
        } catch(Exception e){
            e.printStackTrace();
            t.rollback();
            s.close();
        } finally{
            s.close();
        }
    }
    
    public int getProximoID(){
        Session s = HibernateUtil.getSessionfactory().openSession();
        int id = 0;
        try{
            Query q = s.getNamedQuery("Pedidos.findAll");
            id = q.uniqueResult().hashCode() + 1;
        } catch (NullPointerException e){
            //e.printStackTrace();
            id = 0 + 1;
        }finally{
            s.close();
        }
        return id;
    }
    
    public List<Pedidos> getPedidos(){
        Session s = HibernateUtil.getSessionfactory().openSession();
        List<Pedidos> ped = null;
        try{
            Query q = s.getNamedQuery("Pedidos.findByDataPedido");
            q.setParameter("data", new java.util.Date());
            ped = q.list();
        } catch (Exception e){
            e.printStackTrace();
        }finally{
            s.close();
        }
        return ped;
    }
    
    public List<Pedidos> getPedidos1(){
        Session s = HibernateUtil.getSessionfactory().openSession();
        List<Pedidos> ped = null;
        try{
            Query q = s.getNamedQuery("Pedidos.findByAprovacao");
            q.setParameter("data", new java.util.Date());
            ped = q.list();
        } catch (Exception e){
            e.printStackTrace();
        }finally{
            s.close();
        }
        return ped;
    }
    
    public void atualizar(Pedidos ped){
        Session s = HibernateUtil.getSessionfactory().openSession();
        
        try{
            Query q = s.getNamedQuery("Pedidos.aprova");
            q.setParameter("apr", ped.getAprovacao());
            q.setParameter("id", ped.getIdPedido());
            q.executeUpdate();
            JOptionPane.showMessageDialog(null, "Pedido aprovado");
        } catch(Exception e){
            e.printStackTrace();
            s.close();
        } finally{
            s.close();
        }
    }
}
