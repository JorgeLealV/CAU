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
@Table(name = "CAU_TIPDATOSADJUNTOS", catalog = "", schema = "CAU")
@NamedQueries({
    @NamedQuery(name = "CauTipdatosadjuntos.findAll", query = "SELECT c FROM CauTipdatosadjuntos c")
    , @NamedQuery(name = "CauTipdatosadjuntos.findByIdTipodat", query = "SELECT c FROM CauTipdatosadjuntos c WHERE c.idTipodat = :idTipodat")
    , @NamedQuery(name = "CauTipdatosadjuntos.findByDescripcion", query = "SELECT c FROM CauTipdatosadjuntos c WHERE c.descripcion = :descripcion")})
public class CauTipdatosadjuntos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_TIPODAT")
    private Integer idTipodat;
    @Size(max = 500)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @OneToMany(mappedBy = "idTipodat")
    private Collection<CauProcesoscorreos> cauProcesoscorreosCollection;

    public CauTipdatosadjuntos() {
    }

    public CauTipdatosadjuntos(Integer idTipodat) {
        this.idTipodat = idTipodat;
    }

    public Integer getIdTipodat() {
        return idTipodat;
    }

    public void setIdTipodat(Integer idTipodat) {
        this.idTipodat = idTipodat;
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
        hash += (idTipodat != null ? idTipodat.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CauTipdatosadjuntos)) {
            return false;
        }
        CauTipdatosadjuntos other = (CauTipdatosadjuntos) object;
        if ((this.idTipodat == null && other.idTipodat != null) || (this.idTipodat != null && !this.idTipodat.equals(other.idTipodat))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sfp.model.CauTipdatosadjuntos[ idTipodat=" + idTipodat + " ]";
    }
    
}
