/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eagestor.dao;

import br.com.eagestor.domain.Departamento;
import br.com.eagestor.domain.Usuario;
import br.com.eagestor.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Erick
 */
public class UsuarioDAO {
    
    public void salvar(Usuario user){
        Session ses = HibernateUtil.getSessionfactory().openSession();
        Transaction trans = null;
        
        try {
            trans = ses.beginTransaction();
            ses.save(user);
            trans.commit();
            JOptionPane.showMessageDialog(null, "Salvo com sucesso");
        } catch(Exception e){
            e.printStackTrace();
            trans.rollback();
            ses.close();
        }finally{
            ses.close();
        }
    }
    
    public void atualizar(Usuario user){
        Session ses = HibernateUtil.getSessionfactory().openSession();
        Transaction trans = null;
        
        try {
            trans = ses.beginTransaction();
            ses.update(user);
            trans.commit();
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso");
        } catch(Exception e){
            e.printStackTrace();
            trans.rollback();
            ses.close();
        }finally{
            ses.close();
        }
    }
    
    public void salvaDepartamento(Departamento dep){
        Session s = HibernateUtil.getSessionfactory().openSession();
        Transaction t = null;
        try{
            t = s.beginTransaction();
            s.save(dep);
            t.commit();
            JOptionPane.showMessageDialog(null, "Salvo com sucesso");
        } catch(Exception e){
            e.printStackTrace();
            s.close();
        } finally{
            s.close();
        }
    }
    
    public void AtualizaDepartamento(Departamento dep){
        Session s = HibernateUtil.getSessionfactory().openSession();
        Transaction t = null;
        try{
            t = s.beginTransaction();
            s.update(dep);
            t.commit();
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso");
        } catch(Exception e){
            e.printStackTrace();
            s.close();
        } finally{
            s.close();
        }
    }
    
    public List<Departamento> listaDpt(){
        Session s = HibernateUtil.getSessionfactory().openSession();
        List<Departamento> list = new ArrayList<>();
        
        try{
            Query q = s.getNamedQuery("Departamento.findAll");
            list = q.list();
        } catch (Exception e){
            e.printStackTrace();
            s.close();
            list = null;
        } finally{
            s.close();
        }
        return list;
    }
    
    public List<Departamento> listaDpt(String txt){
        Session s = HibernateUtil.getSessionfactory().openSession();
        List<Departamento> list = new ArrayList<>();
        
        try{
            Query q = s.getNamedQuery("Departamento.findByNomeDpto");
            q.setParameter("nomeDpto", "%" + txt + "%");
            list = q.list();
        } catch (Exception e){
            e.printStackTrace();
            s.close();
            list = null;
        } finally{
            s.close();
        }
        return list;
    }
    
    public List<Usuario> listaUser(){
        Session s = HibernateUtil.getSessionfactory().openSession();
        List<Usuario> list = new ArrayList<>();
        try{
            Query q = s.getNamedQuery("Usuario.findAll");
            list = q.list();
        } catch (Exception e){
            e.printStackTrace();
            s.close();
            list = null;
        } finally{
            s.close();
        }
        return list;
    }
    
    public List<Usuario> listaUser(String txt){
        Session s = HibernateUtil.getSessionfactory().openSession();
        List<Usuario> list = new ArrayList<>();
        try{
            Query q = s.getNamedQuery("Usuario.findByFuncionario");
            q.setParameter("nomeFunc", "%" + txt + "%");
            list = q.list();
        } catch (Exception e){
            e.printStackTrace();
            s.close();
            list = null;
        } finally{
            s.close();
        }
        return list;
    }
    
}
