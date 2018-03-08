/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eagestor.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Erick
 */
@Entity
@Table(name = "compras")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Compras.findAll", query = "SELECT MAX(c.idCompra) FROM Compras c"),
    @NamedQuery(name = "Compras.findByIdCompra", query = "SELECT c FROM Compras c WHERE c.idCompra = :idCompra"),
    @NamedQuery(name = "Compras.entrada", query = "UPDATE Produtos p SET p.quantidade = :qnt, p.valorCompra = :vlr WHERE p.idProduto = :idProduto"),
    @NamedQuery(name = "Compras.entradaV", query = "UPDATE Produtos p SET p.quantidade = :qnt, p.valorCompra = :vlr, p.valorVenda = :venda WHERE p.idProduto = :idProduto"),
    @NamedQuery(name = "Compras.mes", query = "SELECT c FROM Compras c WHERE MONTH(c.dataCompra) = :mes")
})
public class Compras implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idCompra")
    private Integer idCompra;
    
    @Basic(optional = false)
    @Column(name = "dataCompra")
    @Temporal(TemporalType.DATE)
    private Date dataCompra;
    
    @Basic(optional = true)
    @Column(name = "numeroNF")
    private Integer numeroNF;
    
    @Basic(optional = true)
    @Column(name = "dataEntradaNF")
    @Temporal(TemporalType.DATE)
    private Date dataEntradaNF;
    
    @Basic(optional = true)
    @Column(name = "dataSaidaNf")
    @Temporal(TemporalType.DATE)
    private Date dataSaidaNf;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCompra")
    private List<Compraprodutos> compraprodutosList;
    
    @JoinColumn(name = "idFornecedor", referencedColumnName = "idFornecedor")
    @ManyToOne(optional = false)
    private Fornecedor idFornecedor;
    
    public Compras() {
    }

    public Compras(Integer idCompra) {
        this.idCompra = idCompra;
    }

    public Compras(Integer idCompra, Date dataCompra) {
        this.idCompra = idCompra;
        this.dataCompra = dataCompra;
    }

    public Integer getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(Integer idCompra) {
        this.idCompra = idCompra;
    }

    public Date getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(Date dataCompra) {
        this.dataCompra = dataCompra;
    }

    public Integer getNumeroNF() {
        return numeroNF;
    }

    public void setNumeroNF(Integer numeroNF) {
        this.numeroNF = numeroNF;
    }

    public Date getDataEntradaNF() {
        return dataEntradaNF;
    }

    public void setDataEntradaNF(Date dataEntradaNF) {
        this.dataEntradaNF = dataEntradaNF;
    }

    public Date getDataSaidaNf() {
        return dataSaidaNf;
    }

    public void setDataSaidaNf(Date dataSaidaNf) {
        this.dataSaidaNf = dataSaidaNf;
    }

    @XmlTransient
    public List<Compraprodutos> getCompraprodutosList() {
        return compraprodutosList;
    }

    public void setCompraprodutosList(List<Compraprodutos> compraprodutosList) {
        this.compraprodutosList = compraprodutosList;
    }

    public Fornecedor getIdFornecedor() {
        return idFornecedor;
    }

    public void setIdFornecedor(Fornecedor idFornecedor) {
        this.idFornecedor = idFornecedor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCompra != null ? idCompra.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Compras)) {
            return false;
        }
        Compras other = (Compras) object;
        if ((this.idCompra == null && other.idCompra != null) || (this.idCompra != null && !this.idCompra.equals(other.idCompra))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.eagestor.domain.Compras[ idCompra=" + idCompra + " ]";
    }
    
}
