/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eagestor.domain;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author Erick
 */
@Entity
@Table (name = "empresa")
@NamedQueries({
    @NamedQuery(name = "empresa.getAll", query = "SELECT e FROM Empresa e")
})
public class Empresa implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idempresa")
    private int idEmpresa;
    
    @Basic(optional = false)
    @Column(name = "denominacao", length = 60)
    private String denominacao;
    
    @Basic(optional = false)
    @Column(name = "cnpj", length = 20)
    private String cnpj;
    
    @Basic(optional = true)
    @Column(name = "logo")
    private String logo;
    
    @Basic(optional = false)
    @Column(name = "logradouro", length = 80)
    private String logradouro;
    
    @Basic(optional = false)
    @Column(name = "numero")
    private int numero;
    
    @Basic(optional = false)
    @Column(name = "bairro")
    private String bairro;
    
    @Basic(optional = false)
    @Column(name = "cidade")
    private String cidade;
    
    @Basic(optional = false)
    @Column(name = "estado")
    private String estado;
    
    public Empresa() {
    }

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getDenominacao() {
        return denominacao;
    }

    public void setDenominacao(String denominacao) {
        this.denominacao = denominacao;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
    
}
