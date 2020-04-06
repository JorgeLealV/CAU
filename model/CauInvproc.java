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

/**
 *
 * @author jleal
 */
@Entity
@Table(name = "CAU_INVPROC", catalog = "", schema = "CAU")
@NamedQueries({
    @NamedQuery(name = "CauInvproc.findAll", query = "SELECT c FROM CauInvproc c")
    , @NamedQuery(name = "CauInvproc.findByIdInvolucrados", query = "SELECT c FROM CauInvproc c WHERE c.idInvolucrados = :idInvolucrados")
    , @NamedQuery(name = "CauInvproc.findByDescripcion", query = "SELECT c FROM CauInvproc c WHERE c.descripcion = :descripcion")})
public class CauInvproc implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_INVOLUCRADOS")
    private Integer idInvolucrados;
    @Size(max = 150)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @OneToMany(mappedBy = "idInvolucrados")
    private Collection<CauProcesoscorreos> cauProcesoscorreosCollection;

    public CauInvproc() {
    }

    public CauInvproc(Integer idInvolucrados) {
        this.idInvolucrados = idInvolucrados;
    }

    public Integer getIdInvolucrados() {
        return idInvolucrados;
    }

    public void setIdInvolucrados(Integer idInvolucrados) {
        this.idInvolucrados = idInvolucrados;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Collection<CauProcesoscorreos> getCauProcesoscorreosCollection() {
        return cauProcesoscorreosCollection;
    }

    public void setCauProcesoscorreosCollection(Collection<CauProcesoscorreos> cauProcesoscorreosCollection) {
        this.cauProcesoscorreosCollection = cauProcesoscorreosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idInvolucrados != null ? idInvolucrados.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CauInvproc)) {
            return false;
        }
        CauInvproc other = (CauInvproc) object;
        if ((this.idInvolucrados == null && other.idInvolucrados != null) || (this.idInvolucrados != null && !this.idInvolucrados.equals(other.idInvolucrados))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sfp.model.CauInvproc[ idInvolucrados=" + idInvolucrados + " ]";
    }
    
}
