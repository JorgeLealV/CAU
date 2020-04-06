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
@Table(name = "CAU_ETAPAS", catalog = "", schema = "CAU")
@NamedQueries({
    @NamedQuery(name = "CauEtapas.findAll", query = "SELECT c FROM CauEtapas c")
    , @NamedQuery(name = "CauEtapas.findByIdEtapaproceso", query = "SELECT c FROM CauEtapas c WHERE c.idEtapaproceso = :idEtapaproceso")
    , @NamedQuery(name = "CauEtapas.findByDescripcion", query = "SELECT c FROM CauEtapas c WHERE c.descripcion = :descripcion")})
public class CauEtapas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_ETAPAPROCESO")
    private Integer idEtapaproceso;
    @Size(max = 150)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @OneToMany(mappedBy = "idEtapaproceso")
    private Collection<CauProcesoscorreos> cauProcesoscorreosCollection;

    public CauEtapas() {
    }

    public CauEtapas(Integer idEtapaproceso) {
        this.idEtapaproceso = idEtapaproceso;
    }

    public Integer getIdEtapaproceso() {
        return idEtapaproceso;
    }

    public void setIdEtapaproceso(Integer idEtapaproceso) {
        this.idEtapaproceso = idEtapaproceso;
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
        hash += (idEtapaproceso != null ? idEtapaproceso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CauEtapas)) {
            return false;
        }
        CauEtapas other = (CauEtapas) object;
        if ((this.idEtapaproceso == null && other.idEtapaproceso != null) || (this.idEtapaproceso != null && !this.idEtapaproceso.equals(other.idEtapaproceso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sfp.model.CauEtapas[ idEtapaproceso=" + idEtapaproceso + " ]";
    }
    
}
