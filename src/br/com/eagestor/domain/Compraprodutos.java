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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Erick
 */
@Entity
@Table(name = "compraprodutos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Compraprodutos.findAll", query = "SELECT c FROM Compraprodutos c"),
    @NamedQuery(name = "Compraprodutos.findByIdProduto", query = "SELECT c FROM Compraprodutos c WHERE c.compraprodutosPK.idProduto = :idProduto"),
    @NamedQuery(name = "Compraprodutos.verValidade", query = "SELECT c FROM Compraprodutos c WHERE MONTH(c.dataValidade) BETWEEN :mes and :mes1"),
    @NamedQuery(name = "Compraprodutos.verValidad", query = "SELECT c FROM Compraprodutos c WHERE MONTH(c.dataValidade) BETWEEN :mes and 12 or MONTH(c.dataValidade) = 1 ORDER BY MONTH(c.dataValidade) desc"),
    @NamedQuery(name = "Compraprodutos.verValida", query = "SELECT c FROM Compraprodutos c WHERE MONTH(c.dataValidade) BETWEEN 1 and 2 or MONTH(c.dataValidade) = :mes ORDER BY MONTH(c.dataValidade) desc")
    })
public class Compraprodutos implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CompraprodutosPK compraprodutosPK;
    
    @Basic(optional = false)
    @Column(name = "quantidade")
    private double quantidade;
    
    @Basic(optional = true)
    @Column(name = "numLote")
    private String numLote;
    
    @Basic(optional = false)
    @Column(name = "valorCompra")
    private double valorCompra;
    
    @Column(name = "dataValidade")
    @Temporal(TemporalType.DATE)
    private Date dataValidade;
    
    @JoinColumn(name = "idCompra", referencedColumnName = "idCompra", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Compras idCompra;
    
    @JoinColumn(name = "idProduto", referencedColumnName = "idProduto", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Produtos produtos;

    private double qntTotal;

    public double getQntTotal() {
        return qntTotal;
    }

    public void setQntTotal(double qntTotal) {
        this.qntTotal = qntTotal;
    }
    
    public Compraprodutos() {
    }

    public Compraprodutos(CompraprodutosPK compraprodutosPK) {
        this.compraprodutosPK = compraprodutosPK;
    }

    public Compraprodutos(CompraprodutosPK compraprodutosPK, double quantidade, String numLote, double valorCompra) {
        this.compraprodutosPK = compraprodutosPK;
        this.quantidade = quantidade;
        this.numLote = numLote;
        this.valorCompra = valorCompra;
    }

    public Compraprodutos(int idProduto, int idCompra) {
        this.compraprodutosPK = new CompraprodutosPK(idProduto, idCompra);
    }

    public CompraprodutosPK getCompraprodutosPK() {
        return compraprodutosPK;
    }

    public void setCompraprodutosPK(CompraprodutosPK compraprodutosPK) {
        this.compraprodutosPK = compraprodutosPK;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    public String getNumLote() {
        return numLote;
    }

    public void setNumLote(String numLote) {
        this.numLote = numLote;
    }

    public double getValorCompra() {
        return valorCompra;
    }

    public void setValorCompra(double valorCompra) {
        this.valorCompra = valorCompra;
    }

    public Date getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(Date dataValidade) {
        this.dataValidade = dataValidade;
    }

    public Compras getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(Compras idCompra) {
        this.idCompra = idCompra;
    }

    public Produtos getProdutos() {
        return produtos;
    }

    public void setProdutos(Produtos produtos) {
        this.produtos = produtos;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (compraprodutosPK != null ? compraprodutosPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Compraprodutos)) {
            return false;
        }
        Compraprodutos other = (Compraprodutos) object;
        if ((this.compraprodutosPK == null && other.compraprodutosPK != null) || (this.compraprodutosPK != null && !this.compraprodutosPK.equals(other.compraprodutosPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.eagestor.domain.Compraprodutos[ compraprodutosPK=" + compraprodutosPK + " ]";
    }
    
}
