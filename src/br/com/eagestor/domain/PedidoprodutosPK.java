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
public class PedidoprodutosPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "idProduto")
    private int idProduto;
    
    @Basic(optional = false)
    @Column(name = "idPedido")
    private int idPedido;

    public PedidoprodutosPK() {
    }

    public PedidoprodutosPK(int idProduto, int idPedido) {
        this.idProduto = idProduto;
        this.idPedido = idPedido;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idProduto;
        hash += (int) idPedido;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PedidoprodutosPK)) {
            return false;
        }
        PedidoprodutosPK other = (PedidoprodutosPK) object;
        if (this.idProduto != other.idProduto) {
            return false;
        }
        if (this.idPedido != other.idPedido) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.eagestor.domain.PedidoprodutosPK[ idProduto=" + idProduto + ", idPedido=" + idPedido + " ]";
    }
    
}
