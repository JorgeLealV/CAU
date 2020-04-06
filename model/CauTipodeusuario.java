/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfp.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jleal
 */
@Entity
@Table(name = "CAU_TIPODEUSUARIO", catalog = "", schema = "CAU")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CauTipodeusuario.findAll", query = "SELECT c FROM CauTipodeusuario c")
    , @NamedQuery(name = "CauTipodeusuario.findByIdTipousuario", query = "SELECT c FROM CauTipodeusuario c WHERE c.idTipousuario = :idTipousuario")
    , @NamedQuery(name = "CauTipodeusuario.findByDescripciontipousuario", query = "SELECT c FROM CauTipodeusuario c WHERE c.descripciontipousuario = :descripciontipousuario")})
public class CauTipodeusuario implements Serializable {

    @Size(max = 50)
    @Column(name = "DESCRIPCIONTIPOUSUARIO")
    private String descripciontipousuario;
    @OneToMany(mappedBy = "idTipousuario")
    private Collection<CauRolbase> cauRolbaseCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_TIPOUSUARIO")
    private Integer idTipousuario;
    @OneToMany(mappedBy = "idTipousuario")
    private Collection<CauUsuariotipo> cauUsuariotipoCollection;

    public CauTipodeusuario() {
    }

    public CauTipodeusuario(Integer idTipousuario) {
        this.idTipousuario = idTipousuario;
    }

    public Integer getIdTipousuario() {
        return idTipousuario;
    }

    public void setIdTipousuario(Integer idTipousuario) {
        this.idTipousuario = idTipousuario;
    }


    @XmlTransient
    public Collection<CauUsuariotipo> getCauUsuariotipoCollection() {
        return cauUsuariotipoCollection;
    }

    public void setCauUsuariotipoCollection(Collection<CauUsuariotipo> cauUsuariotipoCollection) {
        this.cauUsuariotipoCollection = cauUsuariotipoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipousuario != null ? idTipousuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CauTipodeusuario)) {
            return false;
        }
        CauTipodeusuario other = (CauTipodeusuario) object;
        if ((this.idTipousuario == null && other.idTipousuario != null) || (this.idTipousuario != null && !this.idTipousuario.equals(other.idTipousuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sfp.model.CauTipodeusuario[ idTipousuario=" + idTipousuario + " ]";
    }

    public String getDescripciontipousuario() {
        return descripciontipousuario;
    }

    public void setDescripciontipousuario(String descripciontipousuario) {
        this.descripciontipousuario = descripciontipousuario;
    }

    public Collection<CauRolbase> getCauRolbaseCollection() {
        return cauRolbaseCollection;
    }

    public void setCauRolbaseCollection(Collection<CauRolbase> cauRolbaseCollection) {
        this.cauRolbaseCollection = cauRolbaseCollection;
    }
    
}
