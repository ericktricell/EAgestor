/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eagestor.domain;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Erick
 */
@Entity
@Table(name = "vendaproduto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vendaproduto.findVendas", query = "SELECT v FROM Vendaproduto v WHERE v.recebimento.dataRecebimento = :data"),
    @NamedQuery(name = "Vendaproduto.findAll", query = "SELECT v FROM Vendaproduto v"),
    @NamedQuery(name = "Vendaproduto.findByIdRecebimento", query = "SELECT v FROM Vendaproduto v WHERE v.vendaprodutoPK.idRecebimento = :idRecebimento"),
    @NamedQuery(name = "Vendaproduto.findByIdProduto", query = "SELECT v FROM Vendaproduto v WHERE v.vendaprodutoPK.idProduto = :idProduto"),
    @NamedQuery(name = "Vendaproduto.findByQuantidade", query = "SELECT v FROM Vendaproduto v WHERE v.quantidade = :quantidade"),
    @NamedQuery(name = "Vendaproduto.findByValorVenda", query = "SELECT v FROM Vendaproduto v WHERE v.valorVenda = :valorVenda")})
public class Vendaproduto implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected VendaprodutoPK vendaprodutoPK;
    
    @Basic(optional = false)
    @Column(name = "quantidade")
    private double quantidade;
    
    @Basic(optional = false)
    @Column(name = "valorVenda")
    private double valorVenda;
    
    @JoinColumn(name = "idProduto", referencedColumnName = "idProduto", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Produtos produtos;
    
    @JoinColumn(name = "idRecebimento", referencedColumnName = "idRecebimento", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Recebimento recebimento;

    public Vendaproduto() {
    }

    public Vendaproduto(VendaprodutoPK vendaprodutoPK) {
        this.vendaprodutoPK = vendaprodutoPK;
    }

    public Vendaproduto(VendaprodutoPK vendaprodutoPK, double quantidade, double valorVenda) {
        this.vendaprodutoPK = vendaprodutoPK;
        this.quantidade = quantidade;
        this.valorVenda = valorVenda;
    }

    public Vendaproduto(int idRecebimento, int idProduto) {
        this.vendaprodutoPK = new VendaprodutoPK(idRecebimento, idProduto);
    }

    public VendaprodutoPK getVendaprodutoPK() {
        return vendaprodutoPK;
    }

    public void setVendaprodutoPK(VendaprodutoPK vendaprodutoPK) {
        this.vendaprodutoPK = vendaprodutoPK;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    public double getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(double valorVenda) {
        this.valorVenda = valorVenda;
    }

    public Produtos getProdutos() {
        return produtos;
    }

    public void setProdutos(Produtos produtos) {
        this.produtos = produtos;
    }

    public Recebimento getRecebimento() {
        return recebimento;
    }

    public void setRecebimento(Recebimento recebimento) {
        this.recebimento = recebimento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (vendaprodutoPK != null ? vendaprodutoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vendaproduto)) {
            return false;
        }
        Vendaproduto other = (Vendaproduto) object;
        if ((this.vendaprodutoPK == null && other.vendaprodutoPK != null) || (this.vendaprodutoPK != null && !this.vendaprodutoPK.equals(other.vendaprodutoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.eagestor.domain.Vendaproduto[ vendaprodutoPK=" + vendaprodutoPK + " ]";
    }
    
}
