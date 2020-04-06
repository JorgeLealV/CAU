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
@Table(name = "CAU_TIPOPERSONAL", catalog = "", schema = "CAU")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CauTipopersonal.findAll", query = "SELECT c FROM CauTipopersonal c")
    , @NamedQuery(name = "CauTipopersonal.findByIdCvetipopers", query = "SELECT c FROM CauTipopersonal c WHERE c.idCvetipopers = :idCvetipopers")
    , @NamedQuery(name = "CauTipopersonal.findByDescripcion", query = "SELECT c FROM CauTipopersonal c WHERE c.descripcion = :descripcion")})
public class CauTipopersonal implements Serializable {

    @Size(max = 150)
    @Column(name = "DESCRIPCION")
    private String descripcion;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_CVETIPOPERS")
    private Integer idCvetipopers;
    @OneToMany(mappedBy = "idCvetipopers")
    private Collection<CauUsuarios> cauUsuariosCollection;

    public CauTipopersonal() {
    }

    public CauTipopersonal(Integer idCvetipopers) {
        this.idCvetipopers = idCvetipopers;
    }

    public Integer getIdCvetipopers() {
        return idCvetipopers;
    }

    public void setIdCvetipopers(Integer idCvetipopers) {
        this.idCvetipopers = idCvetipopers;
    }


    @XmlTransient
    public Collection<CauUsuarios> getCauUsuariosCollection() {
        return cauUsuariosCollection;
    }

    public void setCauUsuariosCollection(Collection<CauUsuarios> cauUsuariosCollection) {
        this.cauUsuariosCollection = cauUsuariosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCvetipopers != null ? idCvetipopers.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CauTipopersonal)) {
            return false;
        }
        CauTipopersonal other = (CauTipopersonal) object;
        if ((this.idCvetipopers == null && other.idCvetipopers != null) || (this.idCvetipopers != null && !this.idCvetipopers.equals(other.idCvetipopers))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sfp.model.CauTipopersonal[ idCvetipopers=" + idCvetipopers + " ]";
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
}
