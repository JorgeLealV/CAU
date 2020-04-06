/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfp.model;

import java.io.Serializable;
import javax.persistence.Basic;
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
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jleal
 */
@Entity
@Table(name = "CAU_ROLXTRANXPAR", catalog = "", schema = "CAU")
@TableGenerator(name="Rolxtxp_Gen", table = "CAU_IDGEN", pkColumnName = "GEN_NAME", 
                  valueColumnName = "GEN_VAL", pkColumnValue = "Rolxtxp_Gen", allocationSize=1)

@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CauRolxtranxpar.findAll", query = "SELECT c FROM CauRolxtranxpar c")
    , @NamedQuery(name = "CauRolxtranxpar.findByValor", query = "SELECT c FROM CauRolxtranxpar c WHERE c.valor = :valor")
    , @NamedQuery(name = "CauRolxtranxpar.findByIdRolxtranspar", query = "SELECT c FROM CauRolxtranxpar c WHERE c.idRolxtranspar = :idRolxtranspar")})
public class CauRolxtranxpar implements Serializable {

    private static final long serialVersionUID = 1L;
    @Size(max = 500)
    @Column(name = "VALOR")
    private String valor;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator="Rolxtxp_Gen")
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_ROLXTRANSPAR")
    private Integer idRolxtranspar;
    @JoinColumn(name = "ID_PARAM", referencedColumnName = "ID_PARAM")
    @ManyToOne
    private CauParametros idParam;
    @JoinColumn(name = "ID_ROLXTRANS", referencedColumnName = "ID_ROLXTRANS")
    @ManyToOne
    private CauRolxtrans idRolxtrans;

    public CauRolxtranxpar() {
    }

    public CauRolxtranxpar(Integer idRolxtranspar) {
        this.idRolxtranspar = idRolxtranspar;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public Integer getIdRolxtranspar() {
        return idRolxtranspar;
    }

    public void setIdRolxtranspar(Integer idRolxtranspar) {
        this.idRolxtranspar = idRolxtranspar;
    }

    public CauParametros getIdParam() {
        return idParam;
    }

    public void setIdParam(CauParametros idParam) {
        this.idParam = idParam;
    }

    public CauRolxtrans getIdRolxtrans() {
        return idRolxtrans;
    }

    public void setIdRolxtrans(CauRolxtrans idRolxtrans) {
        this.idRolxtrans = idRolxtrans;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRolxtranspar != null ? idRolxtranspar.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CauRolxtranxpar)) {
            return false;
        }
        CauRolxtranxpar other = (CauRolxtranxpar) object;
        if ((this.idRolxtranspar == null && other.idRolxtranspar != null) || (this.idRolxtranspar != null && !this.idRolxtranspar.equals(other.idRolxtranspar))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sfp.model.CauRolxtranxpar[ idRolxtranspar=" + idRolxtranspar + " ]";
    }
    
}
