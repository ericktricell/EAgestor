/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eagestor.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "medidas")
@NamedQueries({
    @NamedQuery(name = "Medidas.findAll", query = "SELECT m FROM Medidas m"),
    @NamedQuery(name = "Medidas.findByIdMedida", query = "SELECT m FROM Medidas m WHERE m.idMedida = :idMedida"),
    @NamedQuery(name = "Medidas.findByDescricao", query = "SELECT m FROM Medidas m WHERE m.descricao = :descricao"),
    @NamedQuery(name = "Medidas.findByUnidade", query = "SELECT m FROM Medidas m WHERE m.unidade = :unidade")})
public class Medidas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idMedida")
    private Integer idMedida;
    
    @Basic(optional = false)
    @Column(name = "descricao")
    private String descricao;
    
    @Basic(optional = false)
    @Column(name = "unidade")
    private String unidade;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMedida")
    private List<Produtos> produtoList;
    public Medidas() {
    }

    public Medidas(Integer idMedida) {
        this.idMedida = idMedida;
    }

    public Medidas(Integer idMedida, String descricao, String unidade) {
        this.idMedida = idMedida;
        this.descricao = descricao;
        this.unidade = unidade;
    }

    public Integer getIdMedida() {
        return idMedida;
    }

    public void setIdMedida(Integer idMedida) {
        this.idMedida = idMedida;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public List<Produtos> getProdutoList() {
        return produtoList;
    }

    public void setProdutoList(List<Produtos> produtoList) {
        this.produtoList = produtoList;
    }

}
