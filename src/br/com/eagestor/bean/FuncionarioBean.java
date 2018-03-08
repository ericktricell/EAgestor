/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eagestor.bean;

import br.com.eagestor.dao.FuncionarioDAO;
import br.com.eagestor.domain.Contato;
import br.com.eagestor.domain.Endereco;
import br.com.eagestor.domain.Funcionario;
import java.util.Date;

/**
 *
 * @author Erick
 */
public class FuncionarioBean {
    
    public void salvar(String nome, String sobrenome, String cpf, String rg, Date nascimento,
            Date admissao, Date demissao, String genero, String cargo, String codigoFunc,
            boolean ativo, Contato idContato, Endereco idEndereco){
        
        Funcionario f = new Funcionario(nome, sobrenome, cpf, rg, nascimento, admissao,
                demissao, genero, cargo, codigoFunc, ativo, idContato, idEndereco);
        
        new FuncionarioDAO().salvar(f);
    }
    
    public void atualizar(int id, String nome, String sobrenome, String cpf, String rg, Date nascimento,
            Date admissao, Date demissao, String genero, String cargo, String codigoFunc,
            boolean ativo, Contato idContato, Endereco idEndereco){
        
        Funcionario f = new Funcionario(id, nome, sobrenome, cpf, rg, nascimento, admissao,
                demissao, genero, cargo, codigoFunc, ativo, idContato, idEndereco);
        
        new FuncionarioDAO().atualizar(f);
    }
    
    public void excluir(Funcionario f){
        
        new FuncionarioDAO().excluir(f);
    }
}
