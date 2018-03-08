/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eagestor.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Erick
 */
@Entity
@Table(name = "funcionario")
@NamedQueries({
    @NamedQuery(name = "Funcionario.findAll", query = "SELECT f FROM Funcionario f"),
    @NamedQuery(name = "Funcionario.findByNome", query = "SELECT f FROM Funcionario f WHERE f.nome like :nome")
})
public class Funcionario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idFuncionario")
    private Integer idFuncionario;
    
    @Basic(optional = false)
    @Column(name = "nome")
    private String nome;
    
    @Basic(optional = false)
    @Column(name = "sobrenome")
    private String sobrenome;
    
    @Basic(optional = false)
    @Column(name = "cpf")
    private String cpf;
    
    @Column(name = "rg")
    private String rg;
    
    @Basic(optional = false)
    @Column(name = "nascimento")
    @Temporal(TemporalType.DATE)
    private Date nascimento;
    
    @Basic(optional = false)
    @Column(name = "admissao")
    @Temporal(TemporalType.DATE)
    private Date admissao;
    
    @Basic(optional = true)
    @Column(name = "demissao")
    @Temporal(TemporalType.DATE)
    private Date demissao;
    
    @Basic(optional = false)
    @Column(name = "genero")
    private String genero;
    
    @Basic(optional = false)
    @Column(name = "cargo")
    private String cargo;
    
    @Basic(optional = false)
    @Column(name = "codigoFunc")
    private String codigoFunc;
    
    @Basic(optional = false)
    @Column(name = "ativo")
    private boolean ativo;
    
    @JoinColumn(name = "idContato", referencedColumnName = "idContato", updatable = true)
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    private Contato idContato;
    
    @JoinColumn(name = "idEndereco", referencedColumnName = "idEndereco", updatable = true)
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    private Endereco idEndereco;

    public Funcionario() {
    }

    public Funcionario(Integer idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public Funcionario(String nome, String sobrenome, String cpf, String rg, Date nascimento, Date admissao, Date demissao, String genero, String cargo, String codigoFunc, boolean ativo, Contato idContato, Endereco idEndereco) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
        this.rg = rg;
        this.nascimento = nascimento;
        this.admissao = admissao;
        this.demissao = demissao;
        this.genero = genero;
        this.cargo = cargo;
        this.codigoFunc = codigoFunc;
        this.ativo = ativo;
        this.idContato = idContato;
        this.idEndereco = idEndereco;
    }

    public Funcionario(Integer idFuncionario, String nome, String sobrenome, String cpf, String rg, Date nascimento, Date admissao, Date demissao, String genero, String cargo, String codigoFunc, boolean ativo, Contato idContato, Endereco idEndereco) {
        this.idFuncionario = idFuncionario;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
        this.rg = rg;
        this.nascimento = nascimento;
        this.admissao = admissao;
        this.demissao = demissao;
        this.genero = genero;
        this.cargo = cargo;
        this.codigoFunc = codigoFunc;
        this.ativo = ativo;
        this.idContato = idContato;
        this.idEndereco = idEndereco;
    }

    public Integer getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(Integer idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public Date getNascimento() {
        return nascimento;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

    public Date getAdmissao() {
        return admissao;
    }

    public void setAdmissao(Date admissao) {
        this.admissao = admissao;
    }

    public Date getDemissao() {
        return demissao;
    }

    public void setDemissao(Date demissao) {
        this.demissao = demissao;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getCodigoFunc() {
        return codigoFunc;
    }

    public void setCodigoFunc(String codigoFunc) {
        this.codigoFunc = codigoFunc;
    }

    public boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public Contato getIdContato() {
        return idContato;
    }

    public void setIdContato(Contato idContato) {
        this.idContato = idContato;
    }

    public Endereco getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(Endereco idEndereco) {
        this.idEndereco = idEndereco;
    }
    
}
