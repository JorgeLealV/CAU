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
@Table(name = "CAU_TIPOACTIV", catalog = "", schema = "CAU")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CauTipoactiv.findAll", query = "SELECT c FROM CauTipoactiv c")
    , @NamedQuery(name = "CauTipoactiv.findByIdActivacion", query = "SELECT c FROM CauTipoactiv c WHERE c.idActivacion = :idActivacion")
    , @NamedQuery(name = "CauTipoactiv.findByDescripcion", query = "SELECT c FROM CauTipoactiv c WHERE c.descripcion = :descripcion")})
public class CauTipoactiv implements Serializable {

    @Size(max = 150)
    @Column(name = "DESCRIPCION")
    private String descripcion;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_ACTIVACION")
    private Integer idActivacion;
    @OneToMany(mappedBy = "idActivacion")
    private Collection<CauSolicitudtrabajos> cauSolicitudtrabajosCollection;

    public CauTipoactiv() {
    }

    public CauTipoactiv(Integer idActivacion) {
        this.idActivacion = idActivacion;
    }

    public Integer getIdActivacion() {
        return idActivacion;
    }

    public void setIdActivacion(Integer idActivacion) {
        this.idActivacion = idActivacion;
    }


    @XmlTransient
    public Collection<CauSolicitudtrabajos> getCauSolicitudtrabajosCollection() {
        return cauSolicitudtrabajosCollection;
    }

    public void setCauSolicitudtrabajosCollection(Collection<CauSolicitudtrabajos> cauSolicitudtrabajosCollection) {
        this.cauSolicitudtrabajosCollection = cauSolicitudtrabajosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idActivacion != null ? idActivacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CauTipoactiv)) {
            return false;
        }
        CauTipoactiv other = (CauTipoactiv) object;
        if ((this.idActivacion == null && other.idActivacion != null) || (this.idActivacion != null && !this.idActivacion.equals(other.idActivacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sfp.model.CauTipoactiv[ idActivacion=" + idActivacion + " ]";
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
}
