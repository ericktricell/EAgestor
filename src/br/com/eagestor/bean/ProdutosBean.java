/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eagestor.bean;

import br.com.eagestor.dao.ProdutosDAO;
import br.com.eagestor.domain.Categoria;
import br.com.eagestor.domain.Medidas;
import br.com.eagestor.domain.Produtos;

/**
 *
 * @author Erick
 */
public class ProdutosBean {
    
    public void salvar(String nome, String descricao, Medidas idMedida, 
            String marca, Categoria cat, boolean inativo, String codigo){
        
        
        Produtos prod = new Produtos();
        prod.setNome(nome);
        prod.setDescricao(descricao);
        prod.setQuantidade(0.0);
        prod.setIdMedida(idMedida);
        prod.setMarca(marca);
        prod.setValorVenda(0.0);
        prod.setIdCategoria(cat);
        prod.setInativo(inativo);
        prod.setCodigo(codigo);
        
        new ProdutosDAO().salvar(prod);
    }
    
    public void excluir(Produtos prod){
        new ProdutosDAO().exluir(prod);
    }
    
    public void atualizar(int idProduto,String nome, String descricao, Medidas idMedida, 
            String marca, Categoria cat, boolean inativo, String codigo){
        
        
        Produtos prod = new Produtos();
        prod.setIdProduto(idProduto);
        prod.setNome(nome);
        prod.setDescricao(descricao);
        prod.setQuantidade(0.0);
        prod.setIdMedida(idMedida);
        prod.setMarca(marca);
        prod.setValorVenda(0.0);
        prod.setIdCategoria(cat);
        prod.setInativo(inativo);
        prod.setCodigo(codigo);
        
        new ProdutosDAO().atualizar(prod);
    }
}
