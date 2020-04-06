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
@Table(name = "CAU_TIPODIA", catalog = "", schema = "CAU")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CauTipodia.findAll", query = "SELECT c FROM CauTipodia c")
    , @NamedQuery(name = "CauTipodia.findByIdTipodia", query = "SELECT c FROM CauTipodia c WHERE c.idTipodia = :idTipodia")
    , @NamedQuery(name = "CauTipodia.findByDescripcion", query = "SELECT c FROM CauTipodia c WHERE c.descripcion = :descripcion")})
public class CauTipodia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_TIPODIA")
    private Integer idTipodia;
    @Size(max = 100)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @OneToMany(mappedBy = "idTipodia")
    private Collection<CauPeriodo> cauPeriodoCollection;

    public CauTipodia() {
    }

    public CauTipodia(Integer idTipodia) {
        this.idTipodia = idTipodia;
    }

    public Integer getIdTipodia() {
        return idTipodia;
    }

    public void setIdTipodia(Integer idTipodia) {
        this.idTipodia = idTipodia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public Collection<CauPeriodo> getCauPeriodoCollection() {
        return cauPeriodoCollection;
    }

    public void setCauPeriodoCollection(Collection<CauPeriodo> cauPeriodoCollection) {
        this.cauPeriodoCollection = cauPeriodoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipodia != null ? idTipodia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CauTipodia)) {
            return false;
        }
        CauTipodia other = (CauTipodia) object;
        if ((this.idTipodia == null && other.idTipodia != null) || (this.idTipodia != null && !this.idTipodia.equals(other.idTipodia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sfp.model.CauTipodia[ idTipodia=" + idTipodia + " ]";
    }
    
}
