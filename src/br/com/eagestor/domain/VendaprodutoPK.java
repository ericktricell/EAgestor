/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eagestor.domain;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Erick
 */
@Embeddable
public class VendaprodutoPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "idRecebimento")
    private int idRecebimento;
    
    @Basic(optional = false)
    @Column(name = "idProduto")
    private int idProduto;

    public VendaprodutoPK() {
    }

    public VendaprodutoPK(int idRecebimento, int idProduto) {
        this.idRecebimento = idRecebimento;
        this.idProduto = idProduto;
    }

    public int getIdRecebimento() {
        return idRecebimento;
    }

    public void setIdRecebimento(int idRecebimento) {
        this.idRecebimento = idRecebimento;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idRecebimento;
        hash += (int) idProduto;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VendaprodutoPK)) {
            return false;
        }
        VendaprodutoPK other = (VendaprodutoPK) object;
        if (this.idRecebimento != other.idRecebimento) {
            return false;
        }
        if (this.idProduto != other.idProduto) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.eagestor.domain.VendaprodutoPK[ idRecebimento=" + idRecebimento + ", idProduto=" + idProduto + " ]";
    }
    
}
