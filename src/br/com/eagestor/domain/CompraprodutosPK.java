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
public class CompraprodutosPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "idProduto")
    private int idProduto;
    
    @Basic(optional = false)
    @Column(name = "idCompra")
    private int idCompra;

    public CompraprodutosPK() {
    }

    public CompraprodutosPK(int idProduto, int idCompra) {
        this.idProduto = idProduto;
        this.idCompra = idCompra;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public int getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idProduto;
        hash += (int) idCompra;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CompraprodutosPK)) {
            return false;
        }
        CompraprodutosPK other = (CompraprodutosPK) object;
        if (this.idProduto != other.idProduto) {
            return false;
        }
        if (this.idCompra != other.idCompra) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.eagestor.domain.CompraprodutosPK[ idProduto=" + idProduto + ", idCompra=" + idCompra + " ]";
    }
    
}
