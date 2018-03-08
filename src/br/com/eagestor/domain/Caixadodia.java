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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Erick
 */
@Entity
@Table(name = "caixadodia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Caixadodia.findAll", query = "SELECT c FROM Caixadodia c"),
    @NamedQuery(name = "Caixadodia.findByIdCaixa", query = "SELECT MAX(c.idCaixa) FROM Caixadodia c"),
    @NamedQuery(name = "Caixadodia.findByData", query = "SELECT c.valorInicial FROM Caixadodia c WHERE c.data = :data"),
    @NamedQuery(name = "Caixadodia.findByDat", query = "SELECT c FROM Caixadodia c WHERE c.data = :data"),
    @NamedQuery(name = "Caixadodia.getId", query = "SELECT c.idCaixa FROM Caixadodia c WHERE c.data = :data")
})
public class Caixadodia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idCaixa")
    private Integer idCaixa;
    
    @Basic(optional = false)
    @Column(name = "valorInicial")
    private double valorInicial;
    
    @Column(name = "data")
    @Temporal(TemporalType.DATE)
    private Date data;
    
    /*@OneToMany(cascade = CascadeType.ALL, mappedBy = "idCaixa")
    private List<Recebimento> RecebimentoList;*/

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Caixadodia() {
    }

    public Caixadodia(Integer idCaixa) {
        this.idCaixa = idCaixa;
    }

    public Caixadodia(Integer idCaixa, double valorInicial) {
        this.idCaixa = idCaixa;
        this.valorInicial = valorInicial;
    }

    public Integer getIdCaixa() {
        return idCaixa;
    }

    public void setIdCaixa(Integer idCaixa) {
        this.idCaixa = idCaixa;
    }

    public double getValorInicial() {
        return valorInicial;
    }

    public void setValorInicial(double valorInicial) {
        this.valorInicial = valorInicial;
    }
/*
    public List<Recebimento> getRecebimentoList() {
        return RecebimentoList;
    }

    public void setRecebimentoList(List<Recebimento> RecebimentoList) {
        this.RecebimentoList = RecebimentoList;
    }
*/
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCaixa != null ? idCaixa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Caixadodia)) {
            return false;
        }
        Caixadodia other = (Caixadodia) object;
        if ((this.idCaixa == null && other.idCaixa != null) || (this.idCaixa != null && !this.idCaixa.equals(other.idCaixa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.eagestor.domain.Caixadodia[ idCaixa=" + idCaixa + " ]";
    }
    
}
