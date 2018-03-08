/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eagestor.bean;

import java.util.List;
import br.com.eagestor.domain.Pedidoprodutos;
import br.com.eagestor.domain.Pedidos;
import br.com.eagestor.dao.PedidosDAO;
import br.com.eagestor.documentos.PedidodeCompras;
import com.lowagie.text.DocumentException;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Erick
 */
public class PedidosBean {
    
    public void salvaPedido(Date data, List<Pedidoprodutos> lpedidoproduto, String obs){
        Pedidos p = new Pedidos();
        p.setDataPedido(data);
        p.setAprovacao(false);
        p.setPedidoprodutosList(lpedidoproduto);
        p.setObs(obs);
        new PedidosDAO().salvarPedido(p);
        
        
    }
    
    public void aprova(int idPedido, boolean aprovacao){
        Pedidos p = new Pedidos();
        p.setAprovacao(aprovacao);
        p.setIdPedido(idPedido);
        new PedidosDAO().atualizar(p);
        
    }
}
