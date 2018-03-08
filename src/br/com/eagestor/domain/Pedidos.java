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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "pedidos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pedidos.findAll", query = "SELECT MAX(p.idPedido) FROM Pedidos p"),
    @NamedQuery(name = "Pedidos.findByDataPedido", query = "SELECT p FROM Pedidos p WHERE MONTH(p.dataPedido) = MONTH(:data)"),
    @NamedQuery(name = "Pedidos.findByAprovacao", query = "SELECT p FROM Pedidos p WHERE p.aprovacao = true and MONTH(p.dataPedido) = MONTH(:data)"),
    @NamedQuery(name = "Pedidos.aprova", query = "update Pedidos p set p.aprovacao = :apr where p.idPedido = :id")
})
public class Pedidos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPedido")
    private Integer idPedido;
    
    @Basic(optional = false)
    @Column(name = "dataPedido")
    @Temporal(TemporalType.DATE)
    private Date dataPedido;
    
    @Basic(optional = true)
    @Column(name = "obs")
    private String obs;
    
    @Column(name = "aprovacao")
    private Boolean aprovacao;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pedidos",fetch = FetchType.EAGER)
    private List<Pedidoprodutos> pedidoprodutosList;

    public Pedidos() {
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public Pedidos(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public Pedidos(Integer idPedido, Date dataPedido) {
        this.idPedido = idPedido;
        this.dataPedido = dataPedido;
    }

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public Date getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(Date dataPedido) {
        this.dataPedido = dataPedido;
    }

    public Boolean getAprovacao() {
        return aprovacao;
    }

    public void setAprovacao(Boolean aprovacao) {
        this.aprovacao = aprovacao;
    }

    @XmlTransient
    public List<Pedidoprodutos> getPedidoprodutosList() {
        return pedidoprodutosList;
    }

    public void setPedidoprodutosList(List<Pedidoprodutos> pedidoprodutosList) {
        this.pedidoprodutosList = pedidoprodutosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPedido != null ? idPedido.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pedidos)) {
            return false;
        }
        Pedidos other = (Pedidos) object;
        if ((this.idPedido == null && other.idPedido != null) || (this.idPedido != null && !this.idPedido.equals(other.idPedido))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.eagestor.domain.Pedidos[ idPedido=" + idPedido + " ]";
    }
    
}
