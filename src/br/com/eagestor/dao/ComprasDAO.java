/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eagestor.dao;

import br.com.eagestor.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import br.com.eagestor.domain.Compras;
import org.hibernate.Transaction;
import br.com.eagestor.domain.Compraprodutos;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Erick
 */
public class ComprasDAO {
    
    public int getProximoID(){
        Session s = HibernateUtil.getSessionfactory().openSession();
        int id = 0;
        try{
            Query q = s.getNamedQuery("Compras.findAll");
            id = q.uniqueResult().hashCode() + 1;
        } catch (NullPointerException e){
            //e.printStackTrace();
            id = 0 + 1;
        }finally{
            s.close();
        }
        return id;
    }
    
    public void salvaCompra(Compras c){
        Session s = HibernateUtil.getSessionfactory().openSession();
        Transaction t = null;
        try{
            t = s.beginTransaction();
            s.save(c);
            t.commit();
        } catch (Exception e){
            e.printStackTrace();
            s.close();
        } finally{
            s.close();
        }
    }
    
    public void entradaProduto(Compras c){
        Session s = HibernateUtil.getSessionfactory().openSession();
        Query q = null;
        try{
            for (Compraprodutos cp : c.getCompraprodutosList()){
                if (cp.getProdutos().getValorVenda() == 0.0){
                    q = s.getNamedQuery("Compras.entrada");
                    q.setParameter("qnt", cp.getProdutos().getQuantidade());
                    q.setParameter("vlr", cp.getValorCompra());
                    q.setParameter("idProduto", cp.getCompraprodutosPK().getIdProduto());
                    q.executeUpdate();
                } else {
                    q = s.getNamedQuery("Compras.entradaV");
                    q.setParameter("qnt", cp.getProdutos().getQuantidade());
                    q.setParameter("vlr", cp.getValorCompra());
                    q.setParameter("venda", cp.getProdutos().getValorVenda());
                    q.setParameter("idProduto", cp.getCompraprodutosPK().getIdProduto());
                    q.executeUpdate();
                }
            }
        } catch (Exception e){
            e.printStackTrace();
            s.close();
        } finally{
            s.close();
        }
    }
    
    public List<Compraprodutos> getValidade(){
        Session s = HibernateUtil.getSessionfactory().openSession();
        List<Compraprodutos> list = new ArrayList<>();
        
        int mes = Integer.parseInt(new SimpleDateFormat("MM").format(new Date()));
        int mes1 = mes + 2;
        if (mes1 == 13)
            mes1 = 1;
        else if (mes1 == 14)
            mes1 = 2;
        
        try{
            if (mes1 <= 12 && mes1 != 1 && mes1 != 2){
                Query q = s.getNamedQuery("Compraprodutos.verValidade");
                q.setParameter("mes", mes);
                q.setParameter("mes1", mes1);
                list = q.list();
            } else if(mes1 == 1){
                Query q = s.getNamedQuery("Compraprodutos.verValidad");
                q.setParameter("mes", mes);
                list = q.list();
            } else if (mes1 == 2){
                Query q = s.getNamedQuery("Compraprodutos.verValida");
                q.setParameter("mes", mes);
                list = q.list();
            }
        }catch (Exception e){
            list = null;
        }finally{
            s.close();
        }
        return list;
    }
}
