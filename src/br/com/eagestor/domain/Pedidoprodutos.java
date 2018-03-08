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
@Table(name = "pedidoprodutos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pedidoprodutos.findAll", query = "SELECT p FROM Pedidoprodutos p"),
    })
public class Pedidoprodutos implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PedidoprodutosPK pedidoprodutosPK;
    
    @Basic(optional = false)
    @Column(name = "quantidade")
    private double quantidade;
    
    @JoinColumn(name = "idPedido", referencedColumnName = "idPedido", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Pedidos pedidos;
    
    @JoinColumn(name = "idProduto", referencedColumnName = "idProduto", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Produtos produtos;

    public Pedidoprodutos() {
    }

    public Pedidoprodutos(PedidoprodutosPK pedidoprodutosPK) {
        this.pedidoprodutosPK = pedidoprodutosPK;
    }

    public Pedidoprodutos(PedidoprodutosPK pedidoprodutosPK, double quantidade) {
        this.pedidoprodutosPK = pedidoprodutosPK;
        this.quantidade = quantidade;
    }

    public Pedidoprodutos(int idProduto, int idPedido) {
        this.pedidoprodutosPK = new PedidoprodutosPK(idProduto, idPedido);
    }

    public PedidoprodutosPK getPedidoprodutosPK() {
        return pedidoprodutosPK;
    }

    public void setPedidoprodutosPK(PedidoprodutosPK pedidoprodutosPK) {
        this.pedidoprodutosPK = pedidoprodutosPK;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    public Pedidos getPedidos() {
        return pedidos;
    }

    public void setPedidos(Pedidos pedidos) {
        this.pedidos = pedidos;
    }

    public Produtos getProdutos() {
        return produtos;
    }

    public void setProdutos(Produtos produtos) {
        this.produtos = produtos;
    }

}
