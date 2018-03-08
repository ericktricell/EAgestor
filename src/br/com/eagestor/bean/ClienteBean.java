/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eagestor.bean;

import br.com.eagestor.dao.ClienteDAO;
import br.com.eagestor.domain.Contato;
import br.com.eagestor.domain.Endereco;
import br.com.eagestor.domain.Cliente;
import java.util.Date;

/**
 *
 * @author Erick
 */
public class ClienteBean {
    
    public void salvar(String nome, String sobrenome, String cpf, String rg, Date nascimento,
            String genero, Contato idContato, Endereco idEndereco){
        
        Cliente f = new Cliente(nome, sobrenome, cpf, rg, nascimento, genero, idContato, idEndereco);
        
        new ClienteDAO().salvar(f);
    }
    
    public void atualizar(int id, String nome, String sobrenome, String cpf, String rg, Date nascimento,
            String genero, Contato idContato, Endereco idEndereco){
        
        Cliente f = new Cliente(id, nome, sobrenome, cpf, rg, nascimento, genero, idContato, idEndereco);
        
        new ClienteDAO().atualizar(f);
    }
    
    public void excluir(Cliente f){
        
        new ClienteDAO().excluir(f);
    }
}
