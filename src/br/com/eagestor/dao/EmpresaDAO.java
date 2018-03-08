/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eagestor.dao;

import br.com.eagestor.domain.Empresa;
import br.com.eagestor.util.HibernateUtil;
import javax.swing.JOptionPane;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Erick
 */
public class EmpresaDAO {
    
    public void salvar(Empresa em){
        Session s = HibernateUtil.getSessionfactory().openSession();
        Transaction t = null;
        try{
            t = s.beginTransaction();
            s.save(em);
            t.commit();
            JOptionPane.showMessageDialog(null, "Empresa cadastrada com sucesso");
        }catch (Exception e){
            e.printStackTrace();
            t.rollback();
        } finally{
            s.close();
        }
    }
    
    public void atualiza(Empresa em){
        Session s = HibernateUtil.getSessionfactory().openSession();
        Transaction t = null;
        try{
            t = s.beginTransaction();
            s.update(em);
            t.commit();
            JOptionPane.showMessageDialog(null, "Dados Atualizados");
        }catch (Exception e){
            e.printStackTrace();
            t.rollback();
        } finally{
            s.close();
        }
    }
    
    public Empresa getEmpresa(){
        Session s = HibernateUtil.getSessionfactory().openSession();
        Empresa em = null;
        try{
            Query q = s.getNamedQuery("empresa.getAll");
            em = (Empresa) q.uniqueResult();
        } catch(Exception e){
            e.printStackTrace();
        } finally{
            s.close();
        }
        return em;
    }
}
