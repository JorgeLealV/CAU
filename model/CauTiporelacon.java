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
@Table(name = "CAU_TIPORELACON", catalog = "", schema = "CAU")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CauTiporelacon.findAll", query = "SELECT c FROM CauTiporelacon c")
    , @NamedQuery(name = "CauTiporelacon.findByIdRealcion", query = "SELECT c FROM CauTiporelacon c WHERE c.idRealcion = :idRealcion")
    , @NamedQuery(name = "CauTiporelacon.findByDescripcion", query = "SELECT c FROM CauTiporelacon c WHERE c.descripcion = :descripcion")})
public class CauTiporelacon implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_REALCION")
    private Integer idRealcion;
    @Size(max = 100)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @OneToMany(mappedBy = "idRealcion")
    private Collection<CauReglaswf> cauReglaswfCollection;

    public CauTiporelacon() {
    }

    public CauTiporelacon(Integer idRealcion) {
        this.idRealcion = idRealcion;
    }

    public Integer getIdRealcion() {
        return idRealcion;
    }

    public void setIdRealcion(Integer idRealcion) {
        this.idRealcion = idRealcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public Collection<CauReglaswf> getCauReglaswfCollection() {
        return cauReglaswfCollection;
    }

    public void setCauReglaswfCollection(Collection<CauReglaswf> cauReglaswfCollection) {
        this.cauReglaswfCollection = cauReglaswfCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRealcion != null ? idRealcion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CauTiporelacon)) {
            return false;
        }
        CauTiporelacon other = (CauTiporelacon) object;
        if ((this.idRealcion == null && other.idRealcion != null) || (this.idRealcion != null && !this.idRealcion.equals(other.idRealcion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sfp.model.CauTiporelacon[ idRealcion=" + idRealcion + " ]";
    }
    
}
