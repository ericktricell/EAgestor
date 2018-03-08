/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eagestor.domain;

import java.io.Serializable;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Erick
 */
@Entity
@Table(name = "usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findByFuncionario", query = "SELECT u FROM Usuario u, Funcionario f WHERE f.nome like :nomeFunc and u.idFuncionario = f.idFuncionario"),
    @NamedQuery(name = "Usuario.findBySenha", query = "SELECT u FROM Usuario u, Funcionario f WHERE u.senha = :senha and f.idFuncionario = :cod and u.idFuncionario = f.idFuncionario")})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idUsuario")
    private Integer idUsuario;
    
    @Basic(optional = false)
    @Column(name = "senha")
    private String senha;
    
    @Basic(optional = true)
    @Column(name = "inativo")
    private boolean inativo;
    
    @Basic(optional = false)
    @Column(name = "tipo", length = 40)
    private String tipo;
    
    @JoinColumn(name = "idDepartamento", referencedColumnName = "idDepartamento")
    @OneToOne(optional = false, cascade = CascadeType.ALL)
    private Departamento idDepartamento;
    
    @JoinColumn(name = "idFuncionario", referencedColumnName = "idFuncionario")
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    private Funcionario idFuncionario;

    public Usuario() {
    }

    public boolean isInativo() {
        return inativo;
    }

    public void setInativo(boolean inativo) {
        this.inativo = inativo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Usuario(String senha, Departamento idDepartamento, Funcionario idFuncionario) {
        this.senha = senha;
        this.idDepartamento = idDepartamento;
        this.idFuncionario = idFuncionario;
    }

    public Usuario(Integer idUsuario, String senha, Departamento idDepartamento, Funcionario idFuncionario) {
        this.idUsuario = idUsuario;
        this.senha = senha;
        this.idDepartamento = idDepartamento;
        this.idFuncionario = idFuncionario;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Departamento getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(Departamento idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public Funcionario getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(Funcionario idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

}
