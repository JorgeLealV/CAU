/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfp.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jleal
 */
@Entity
@Table(name = "CAU_ESTATUSGENERAL", catalog = "", schema = "CAU")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CauEstatusgeneral.findAll", query = "SELECT c FROM CauEstatusgeneral c")
    , @NamedQuery(name = "CauEstatusgeneral.findByIdEstatus", query = "SELECT c FROM CauEstatusgeneral c WHERE c.idEstatus = :idEstatus")
    , @NamedQuery(name = "CauEstatusgeneral.findByFechaviginicestatrab", query = "SELECT c FROM CauEstatusgeneral c WHERE c.fechaviginicestatrab = :fechaviginicestatrab")
    , @NamedQuery(name = "CauEstatusgeneral.findByFechavigfinestatrab", query = "SELECT c FROM CauEstatusgeneral c WHERE c.fechavigfinestatrab = :fechavigfinestatrab")
    , @NamedQuery(name = "CauEstatusgeneral.findByDescestatustrab", query = "SELECT c FROM CauEstatusgeneral c WHERE c.descestatustrab = :descestatustrab")})
public class CauEstatusgeneral implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHAVIGINICESTATRAB")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaviginicestatrab;
    @Size(max = 120)
    @Column(name = "DESCESTATUSTRAB")
    private String descestatustrab;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_ESTATUS")
    private Integer idEstatus;
    @Column(name = "FECHAVIGFINESTATRAB")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechavigfinestatrab;
    @OneToMany(mappedBy = "idEstatus")
    private Collection<CauSolicitudes> cauSolicitudesCollection;
    @OneToMany(mappedBy = "idEstatus")
    private Collection<CauSolicitudtrabajos> cauSolicitudtrabajosCollection;

    public CauEstatusgeneral() {
    }

    public CauEstatusgeneral(Integer idEstatus) {
        this.idEstatus = idEstatus;
    }

    public CauEstatusgeneral(Integer idEstatus, Date fechaviginicestatrab) {
        this.idEstatus = idEstatus;
        this.fechaviginicestatrab = fechaviginicestatrab;
    }

    public Integer getIdEstatus() {
        return idEstatus;
    }

    public void setIdEstatus(Integer idEstatus) {
        this.idEstatus = idEstatus;
    }


    public Date getFechavigfinestatrab() {
        return fechavigfinestatrab;
    }

    public void setFechavigfinestatrab(Date fechavigfinestatrab) {
        this.fechavigfinestatrab = fechavigfinestatrab;
    }


    @XmlTransient
    public Collection<CauSolicitudes> getCauSolicitudesCollection() {
        return cauSolicitudesCollection;
    }

    public void setCauSolicitudesCollection(Collection<CauSolicitudes> cauSolicitudesCollection) {
        this.cauSolicitudesCollection = cauSolicitudesCollection;
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
        hash += (idEstatus != null ? idEstatus.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CauEstatusgeneral)) {
            return false;
        }
        CauEstatusgeneral other = (CauEstatusgeneral) object;
        if ((this.idEstatus == null && other.idEstatus != null) || (this.idEstatus != null && !this.idEstatus.equals(other.idEstatus))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sfp.model.CauEstatusgeneral[ idEstatus=" + idEstatus + " ]";
    }

    public Date getFechaviginicestatrab() {
        return fechaviginicestatrab;
    }

    public void setFechaviginicestatrab(Date fechaviginicestatrab) {
        this.fechaviginicestatrab = fechaviginicestatrab;
    }

    public String getDescestatustrab() {
        return descestatustrab;
    }

    public void setDescestatustrab(String descestatustrab) {
        this.descestatustrab = descestatustrab;
    }
    
}
