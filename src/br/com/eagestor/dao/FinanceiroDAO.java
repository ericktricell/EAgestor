/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eagestor.dao;

import br.com.eagestor.domain.Caixadodia;
import br.com.eagestor.domain.Compraprodutos;
import br.com.eagestor.domain.Compras;
import br.com.eagestor.domain.Despesas;
import br.com.eagestor.domain.Produtos;
import br.com.eagestor.domain.Recebimento;
import br.com.eagestor.domain.Vendaproduto;
import br.com.eagestor.util.HibernateUtil;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Erick
 */
public class FinanceiroDAO {
    
    public void abreCaixa(Caixadodia caixa){
        Session s = HibernateUtil.getSessionfactory().openSession();
        Transaction t = null;
        try{
            t = s.beginTransaction();
            s.save(caixa);
            t.commit();
            JOptionPane.showMessageDialog(null, "Caixa aberto");
        }catch (Exception e){
            e.printStackTrace();
            t.rollback();
            s.close();
        } finally{
            s.close();
        }
    }
    
    public int getIdCaixa(){
        Session s = HibernateUtil.getSessionfactory().openSession();
        int id = 0;
        
        try{
            Query q = s.getNamedQuery("Caixadodia.findByIdCaixa");
            id = q.uniqueResult().hashCode();
        } catch(Exception e){
            e.printStackTrace();
            s.close();
        } finally{
            s.close();
        }
        return id;
    }
    
    public int getIdRecebimento(){
        Session s = HibernateUtil.getSessionfactory().openSession();
        int id = 0;
        
        try{
            Query q = s.getNamedQuery("Recebimento.findByDataRecebimento");
            q.setParameter("data", new Date());
            id = q.uniqueResult().hashCode() + 1;
        } catch(NullPointerException e){
            //e.printStackTrace();
            id = 1;
        } finally{
            s.close();
        }
        return id;
    }
    
    public double getCaixa(){
        Session s = HibernateUtil.getSessionfactory().openSession();
        double caixa = 0;
        
        try{
            Query q = s.getNamedQuery("Caixadodia.findByData");
            q.setParameter("data", new Date());
            String v = q.uniqueResult().toString();
            caixa = Double.parseDouble(v);
        } catch (NullPointerException e){
            //e.printStackTrace();
            caixa = 0.0;
            s.close();
        }
        return caixa;
    }
    
    public Caixadodia getCaixaDia(){
        Session s = HibernateUtil.getSessionfactory().openSession();
        Caixadodia caixa = new Caixadodia();
        
        try{
            Query q = s.getNamedQuery("Caixadodia.findByDat");
            q.setParameter("data", new Date());
            caixa = (Caixadodia) q.uniqueResult();
        } catch (NullPointerException e){
            //e.printStackTrace();
            caixa = null;
            s.close();
        }
        return caixa;
    }
    
    public void registraVenda(Recebimento rec){
        Session s = HibernateUtil.getSessionfactory().openSession();
        Transaction t = null;
        try{
            t = s.beginTransaction();
            s.save(rec);
            t.commit();
        } catch (Exception e){
            e.printStackTrace();
            t.rollback();
            s.close();
        } finally{
            s.close();
        }
    }
    
    public void registraBaixa(Recebimento rec){
        Session s = HibernateUtil.getSessionfactory().openSession();
        try{
            for (Vendaproduto vp : rec.getVendaprodutoList()){
                Query q = s.getNamedQuery("Baixa.produto");
                q.setParameter("qnt", vp.getProdutos().getQuantidade());
                q.setParameter("id", vp.getProdutos().getIdProduto());
                q.executeUpdate();
            }
        }catch(Exception e){
            e.printStackTrace();
            s.close();
        }finally{
            s.close();
        }
    }
    
    public double getRecDinheiro(){
        Session s = HibernateUtil.getSessionfactory().openSession();
        double vlr = 0;
        try{
            Query q = s.getNamedQuery("total.dinheiro");
            q.setParameter("data", new Date());
            vlr = (double) q.uniqueResult();
        } catch (NullPointerException e){
            //e.printStackTrace();
            vlr = 0.0;
            //s.close();
        } finally{
            s.close();
        }
        return vlr;
    }
    
    public double getRecCheque(){
        Session s = HibernateUtil.getSessionfactory().openSession();
        double vlr = 0;
        try{
            Query q = s.getNamedQuery("total.cheque");
            q.setParameter("data", new Date());
            vlr = (double) q.uniqueResult();
        } catch (NullPointerException e){
            //e.printStackTrace();
            vlr = 0.0;
            //s.close();
        } finally{
            s.close();
        }
        return vlr;
    }
    
    public double getRecCredito(){
        Session s = HibernateUtil.getSessionfactory().openSession();
        double vlr = 0;
        try{
            Query q = s.getNamedQuery("total.credito");
            q.setParameter("data", new Date());
            vlr = (double) q.uniqueResult();
        } catch (NullPointerException e){
            //e.printStackTrace();
            vlr = 0.0;
            //s.close();
        } finally{
            s.close();
        }
        return vlr;
    }
    
    public double getRecDebito(){
        Session s = HibernateUtil.getSessionfactory().openSession();
        double vlr = 0;
        try{
            Query q = s.getNamedQuery("total.debito");
            q.setParameter("data", new Date());
            vlr = (double) q.uniqueResult();
        } catch (NullPointerException e){
            //e.printStackTrace();
            vlr = 0.0;
            //s.close();
        } finally{
            s.close();
        }
        return vlr;
    }
    
    public double getRecTicekt(){
        Session s = HibernateUtil.getSessionfactory().openSession();
        double vlr = 0;
        try{
            Query q = s.getNamedQuery("total.ticket");
            q.setParameter("data", new Date());
            vlr = (double) q.uniqueResult();
        } catch (NullPointerException e){
            //e.printStackTrace();
            vlr = 0.0;
            //s.close();
        } finally{
            s.close();
        }
        return vlr;
    }
    
    public void registraDespesa(Despesas dep){
        Session s = HibernateUtil.getSessionfactory().openSession();
        Transaction t = null;
        try{
            t = s.beginTransaction();
            s.save(dep);
            t.commit();
            JOptionPane.showMessageDialog(null, "Despesa salva com sucesso");
        } catch (Exception e){
            e.printStackTrace();
            t.rollback();
        } finally{
            s.close();
        }
    }
    
    public List<Despesas> getDespesa(){
        Session s = HibernateUtil.getSessionfactory().openSession();
        List<Despesas> list = new ArrayList<>();
        
        try{
            Query q = s.getNamedQuery("despesas.getAll");
            list = q.list();
        } catch(Exception e){
            e.printStackTrace();
        } finally{
            s.close();
        }
        return list;
    }
    
    public List<Despesas> getDespesa1(){
        Session s = HibernateUtil.getSessionfactory().openSession();
        List<Despesas> list = new ArrayList<>();
        
        try{
            Query q = s.getNamedQuery("despesas.getNPagas");
            list = q.list();
        } catch(Exception e){
            e.printStackTrace();
        } finally{
            s.close();
        }
        return list;
    }
    
    public List<Despesas> getDespesa2(){
        Session s = HibernateUtil.getSessionfactory().openSession();
        List<Despesas> list = new ArrayList<>();
        
        try{
            Query q = s.getNamedQuery("despesas.getPagas");
            list = q.list();
        } catch(Exception e){
            e.printStackTrace();
        } finally{
            s.close();
        }
        return list;
    }
    
    public double getDespesa3(){
        Session s = HibernateUtil.getSessionfactory().openSession();
        double des = 0;
        try{
            Query q = s.getNamedQuery("despesas.getSoma");
            q.setParameter("data", new Date());
            des = (double) q.uniqueResult();
        }catch (NullPointerException e){
            //e.printStackTrace();
            des = 0;
        } finally{
            s.close();
        }
        return des;
    }
    
    public double getDespesaMes(){
        Session s = HibernateUtil.getSessionfactory().openSession();
        int mes = Integer.parseInt(new SimpleDateFormat("MM").format(new Date())) - 1;
        if(mes == 0)
            mes = 12;
        
        double des = 0;
        try{
            Query q = s.getNamedQuery("despesas.mes");
            q.setParameter("mes", mes);
            des = (double) q.uniqueResult();
        }catch (NullPointerException e){
            //e.printStackTrace();
            des = 0;
        } finally{
            s.close();
        }
        return des;
    }
    
    public List<Despesas> getDespesaTodas(){
        Session s = HibernateUtil.getSessionfactory().openSession();
        int mes = Integer.parseInt(new SimpleDateFormat("MM").format(new Date())) - 1;
        if(mes == 0)
            mes = 12;
        
        List<Despesas> des = new ArrayList<>();
        try{
            Query q = s.getNamedQuery("despesas.lista");
            q.setParameter("mes", mes);
            des = q.list();
        }catch (NullPointerException e){
            //e.printStackTrace();
            des = null;
        } finally{
            s.close();
        }
        return des;
    }
    
    
    public List<Despesas> getDespesaVencer(){
        Session s = HibernateUtil.getSessionfactory().openSession();
        int mes = Integer.parseInt(new SimpleDateFormat("MM").format(new Date()));
        List<Despesas> des = new ArrayList<>();
        try{
            Query q = s.getNamedQuery("despesas.prox");
            q.setParameter("mes", mes);
            des = q.list();
        }catch (NullPointerException e){
            //e.printStackTrace();
            des = null;
        } finally{
            s.close();
        }
        return des;
    }
    
    public List<Vendaproduto> getFluxo(){
        List<Vendaproduto> list = new ArrayList<>();
        Session s = HibernateUtil.getSessionfactory().openSession();
        try{
            Query q = s.getNamedQuery("Vendaproduto.findVendas");
            q.setParameter("data", new Date());
            list = q.list();
        }catch(Exception e){
            e.printStackTrace();
        } finally{
            s.close();
        }
        return list;
    }
    
    public double getTotalMensal(){
        Session s = HibernateUtil.getSessionfactory().openSession();
        int mes = Integer.parseInt(new SimpleDateFormat("MM").format(new Date()));
        if(mes == 0)
            mes = 12;
        double total = 0;
        
        try{
            Query q = s.getNamedQuery("Recebimento.mes");
            q.setParameter("mes", mes);
            total = (double) q.uniqueResult();
        } catch (Exception e){
            e.printStackTrace();
        }finally{
            s.close();
        }
        return total;
    }
    
    public double getTotalCompras(){
        Session s = HibernateUtil.getSessionfactory().openSession();
        int mes = Integer.parseInt(new SimpleDateFormat("MM").format(new Date())) - 1;
        if(mes == 0)
            mes = 12;
        
        double totComp = 0;
        
        try{
            Query q = s.getNamedQuery("Compras.mes");
            q.setParameter("mes", mes);
            List<Compras> c = q.list();
            
            for (Compras co : c){
                for(Compraprodutos cp : co.getCompraprodutosList()){
                    totComp += cp.getQuantidade() * cp.getValorCompra();
                }
            }
        } catch(Exception e){
            e.printStackTrace();
        }finally{
            s.close();
        }
        return totComp;
    }
    
    public List<Compraprodutos> getComprasProd(){
        Session s = HibernateUtil.getSessionfactory().openSession();
        int mes = Integer.parseInt(new SimpleDateFormat("MM").format(new Date())) - 1;
        if(mes == 0)
            mes = 12;
        
        List<Compraprodutos> totComp = new ArrayList<>();
        
        try{
            Query q = s.getNamedQuery("Compras.mes");
            q.setParameter("mes", mes);
            List<Compras> c = q.list();
            
            for (Compras co : c){
                for(Compraprodutos cp : co.getCompraprodutosList())
                    totComp.add(cp);
            }
        } catch(Exception e){
            e.printStackTrace();
        }finally{
            s.close();
        }
        return totComp;
    }
    
    public double getTotalEstoque(){
        Session s = HibernateUtil.getSessionfactory().openSession();
        double tot = 0;
        List<Produtos> list = new ArrayList<>();
        try{
            Query q = s.getNamedQuery("Produtos.somaC");
            list = q.list();
            
            for (Produtos p : list){
                tot += p.getValorCompra() * p.getQuantidade();
            }
        } catch(Exception e){
            e.printStackTrace();
            tot = 0;
        }finally{
            s.close();
        }
        return tot;
    }
}
