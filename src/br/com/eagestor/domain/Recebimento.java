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
@Table(name = "recebimento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Recebimento.findAll", query = "SELECT r FROM Recebimento r"),
    @NamedQuery(name = "Recebimento.findByIdRecebimento", query = "SELECT r FROM Recebimento r WHERE r.idRecebimento = :idRecebimento"),
    @NamedQuery(name = "Recebimento.findByDataRecebimento", query = "SELECT MAX(r.idRecebimento) FROM Recebimento r WHERE r.dataRecebimento = :data"),
    @NamedQuery(name = "Baixa.produto", query = "update Produtos p set p.quantidade = :qnt where p.idProduto = :id"),
    @NamedQuery(name = "total.dinheiro", query = "SELECT SUM(r.total) FROM Recebimento r WHERE r.formaPagamento = 'Dinheiro' and r.dataRecebimento = :data"),
    @NamedQuery(name = "total.cheque", query = "SELECT SUM(r.total) FROM Recebimento r WHERE r.formaPagamento = 'Cheque' and r.dataRecebimento = :data"),
    @NamedQuery(name = "total.credito", query = "SELECT SUM(r.total) FROM Recebimento r WHERE r.formaPagamento = 'Cartão Crédito' and r.dataRecebimento = :data"),
    @NamedQuery(name = "total.debito", query = "SELECT SUM(r.total) FROM Recebimento r WHERE r.formaPagamento = 'Cartão Débito' and r.dataRecebimento = :data"),
    @NamedQuery(name = "total.ticket", query = "SELECT SUM(r.total) FROM Recebimento r WHERE r.formaPagamento = 'Ticket' and r.dataRecebimento = :data"),
    @NamedQuery(name = "Recebimento.mes", query = "SELECT SUM(r.total) FROM Recebimento r WHERE MONTH(r.dataRecebimento) = :mes"),
    @NamedQuery(name = "Recebimento.maior", query = "SELECT r FROM Recebimento r WHERE MONTH(r.dataRecebimento) = :mes")
})
public class Recebimento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idRecebimento")
    private Integer idRecebimento;
    
    @Basic(optional = false)
    @Column(name = "dataRecebimento")
    @Temporal(TemporalType.DATE)
    private Date dataRecebimento;
    
    @Column(name = "numNF")
    private Integer numNF;
    
    @Basic(optional = false)
    @Column(name = "total")
    private double total;
    
    @Basic(optional = false)
    @Column(name = "formaPagamento", length = 20)
    private String formaPagamento;
    
    @Basic(optional = false)
    @Column(name = "TipoPagamento", length = 20)
    private String tipoPagamento; // a vista, parcelado
    
    @Basic(optional = true)
    @Column(name = "numParcelas")
    private int numParcelas;
    
    @Basic(optional = true)
    @Column(name = "vlrParcelas")
    private double vlrParcelas;
    
    @JoinColumn(name = "idCaixa", referencedColumnName = "idCaixa")
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    private Caixadodia idCaixa;
    
    @JoinColumn(name = "idCliente", referencedColumnName = "idCliente")
    @ManyToOne(cascade = CascadeType.ALL, optional = true)
    private Cliente idCliente;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recebimento")
    private List<Vendaproduto> vendaprodutoList;

    public Cliente getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Cliente idCliente) {
        this.idCliente = idCliente;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public String getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(String tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public int getNumParcelas() {
        return numParcelas;
    }

    public void setNumParcelas(int numParcelas) {
        this.numParcelas = numParcelas;
    }

    public double getVlrParcelas() {
        return vlrParcelas;
    }

    public void setVlrParcelas(double vlrParcelas) {
        this.vlrParcelas = vlrParcelas;
    }

    public Recebimento() {
    }

    public Recebimento(Integer idRecebimento) {
        this.idRecebimento = idRecebimento;
    }

    public Recebimento(Integer idRecebimento, Date dataRecebimento, double total) {
        this.idRecebimento = idRecebimento;
        this.dataRecebimento = dataRecebimento;
        this.total = total;
    }

    public Integer getIdRecebimento() {
        return idRecebimento;
    }

    public void setIdRecebimento(Integer idRecebimento) {
        this.idRecebimento = idRecebimento;
    }

    public Date getDataRecebimento() {
        return dataRecebimento;
    }

    public void setDataRecebimento(Date dataRecebimento) {
        this.dataRecebimento = dataRecebimento;
    }

    public Integer getNumNF() {
        return numNF;
    }

    public void setNumNF(Integer numNF) {
        this.numNF = numNF;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @XmlTransient
    public Caixadodia getCaixa() {
        return idCaixa;
    }

    public void setIdCaixa(Caixadodia idCaixadodia) {
        this.idCaixa = idCaixadodia;
    }

    @XmlTransient
    public List<Vendaproduto> getVendaprodutoList() {
        return vendaprodutoList;
    }

    public void setVendaprodutoList(List<Vendaproduto> vendaprodutoList) {
        this.vendaprodutoList = vendaprodutoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRecebimento != null ? idRecebimento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Recebimento)) {
            return false;
        }
        Recebimento other = (Recebimento) object;
        if ((this.idRecebimento == null && other.idRecebimento != null) || (this.idRecebimento != null && !this.idRecebimento.equals(other.idRecebimento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.eagestor.domain.Recebimento[ idRecebimento=" + idRecebimento + " ]";
    }
    
}
