/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eagestor.bean;

import br.com.eagestor.dao.FornecedorDAO;
import br.com.eagestor.domain.Contato;
import br.com.eagestor.domain.Endereco;
import br.com.eagestor.domain.Fornecedor;
import javax.swing.JOptionPane;

/**
 *
 * @author Erick
 */
public class FornecedorBean {
 
    public void salvar(String razaoSocial, String nomeFantasia, String cnpjCpf, String incricaoEstadual, String site,
            String logradouro, String numero, String complemento, String bairro, String cidade, String cep, String estado,
            String comercial, String celular, String email, boolean inativo){
        try{
            Contato c = new Contato();
            c.setCelular(celular);
            c.setComercial(comercial);
            c.setEmail(email);

            Endereco e = new Endereco();
            e.setBairro(bairro);
            e.setCep(cep);
            e.setCidade(cidade);
            e.setComplemento(complemento);
            e.setEstado(estado);
            e.setLogradouro(logradouro);
            if (numero.equals(""))
                e.setNumero(0);
            else
                e.setNumero(Integer.parseInt(numero));
            
            Fornecedor fo = new Fornecedor();
            fo.setCnpjCpf(cnpjCpf);
            fo.setIdContato(c);
            fo.setIdEndereco(e);
            fo.setInativo(inativo);
            fo.setInscricaoEstadual(incricaoEstadual);
            fo.setNomeFantasia(nomeFantasia);
            fo.setRazaoSocial(razaoSocial);
            fo.setSite(site);
            
            new FornecedorDAO().salvar(fo);
        } catch (NumberFormatException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Caracteres inválidos no campo numero");
        }
    }
    
    public void atualizar(int idFornecedor, String razaoSocial, String nomeFantasia, String cnpjCpf, String incricaoEstadual, String site,
            String logradouro, String numero, String complemento, String bairro, String cidade, String cep, String estado,
            String comercial, String celular, String email, boolean inativo){
        try{
            Contato c = new Contato();
            c.setCelular(celular);
            c.setComercial(comercial);
            c.setEmail(email);

            Endereco e = new Endereco();
            e.setBairro(bairro);
            e.setCep(cep);
            e.setCidade(cidade);
            e.setComplemento(complemento);
            e.setEstado(estado);
            e.setLogradouro(logradouro);
            if (numero.equals(""))
                e.setNumero(0);
            else
                e.setNumero(Integer.parseInt(numero));
            
            Fornecedor fo = new Fornecedor(idFornecedor);
            fo.setCnpjCpf(cnpjCpf);
            fo.setIdContato(c);
            fo.setIdEndereco(e);
            fo.setInativo(inativo);
            fo.setInscricaoEstadual(incricaoEstadual);
            fo.setNomeFantasia(nomeFantasia);
            fo.setRazaoSocial(razaoSocial);
            fo.setSite(site);
            
            new FornecedorDAO().atualizar(fo);
        } catch (NumberFormatException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Caracteres inválidos no campo numero");
        }
    }
    
    public void excluir(Fornecedor f){
            
            new FornecedorDAO().excluir(f);
    }
}
