/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eagestor.bean;

import br.com.eagestor.dao.UsuarioDAO;
import br.com.eagestor.domain.Departamento;
import br.com.eagestor.domain.Funcionario;
import br.com.eagestor.domain.Usuario;

/**
 *
 * @author Erick
 */
public class UsuarioBean {
    
    public void salvar(Usuario user){
        new UsuarioDAO().salvar(user);
    }
    
    public void atualizar(Usuario user){
        new UsuarioDAO().atualizar(user);
    }
}
