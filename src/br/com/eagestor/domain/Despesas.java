/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eagestor.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Erick
 */
@Entity
@Table(name = "despesas")
@NamedQueries({
    @NamedQuery(name = "despesas.getAll", query = "SELECT d FROM Despesas d ORDER BY d.idDespesa desc"),
    @NamedQuery(name = "despesas.getPagas", query = "SELECT d FROM Despesas d WHERE d.pago = true ORDER BY d.idDespesa desc"),
    @NamedQuery(name = "despesas.getSoma", query = "SELECT SUM(d.valor) FROM Despesas d WHERE d.dataPagamento = :data"),
    @NamedQuery(name = "despesas.getNPagas", query = "SELECT d FROM Despesas d WHERE d.pago = false ORDER BY d.idDespesa desc"),
    @NamedQuery(name = "despesas.mes", query = "SELECT SUM(d.valor) FROM Despesas d WHERE MONTH(d.dataPagamento) = :mes"),
    @NamedQuery(name = "despesas.lista", query = "SELECT d FROM Despesas d WHERE MONTH(d.dataPagamento) = :mes"),
    @NamedQuery(name = "despesas.prox", query = "SELECT d FROM Despesas d WHERE MONTH(d.vencimento) = :mes"),
})
public class Despesas implements Serializable {
    
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDespesa")
    private int idDespesa;
    
    @Basic(optional = false)
    @Column(name = "descricao", length = 60)
    private String descricao;
    
    @Basic(optional = false)
    @Column(name = "valor")
    private double valor;
    
    @Basic(optional = false)
    @Column(name = "vencimento")
    @Temporal(TemporalType.DATE)
    private Date vencimento;
    
    @Basic(optional = true)
    @Column(name = "dataPagamento")
    @Temporal(TemporalType.DATE)
    private Date dataPagamento;
    
    @Basic(optional = true)
    @Column(name = "pago")
    private boolean pago;

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public int getIdDespesa() {
        return idDespesa;
    }

    public void setIdDespesa(int idDespesa) {
        this.idDespesa = idDespesa;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Date getVencimento() {
        return vencimento;
    }

    public void setVencimento(Date vencimento) {
        this.vencimento = vencimento;
    }

    public boolean isPago() {
        return pago;
    }

    public void setPago(boolean pago) {
        this.pago = pago;
    }
    
    
}
