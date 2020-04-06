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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jleal
 */
@Entity
@Table(name = "CAU_DEPTRABAJO", catalog = "", schema = "CAU")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CauDeptrabajo.findAll", query = "SELECT c FROM CauDeptrabajo c")
    , @NamedQuery(name = "CauDeptrabajo.findByFechainic", query = "SELECT c FROM CauDeptrabajo c WHERE c.fechainic = :fechainic")
    , @NamedQuery(name = "CauDeptrabajo.findByFechafin", query = "SELECT c FROM CauDeptrabajo c WHERE c.fechafin = :fechafin")
    , @NamedQuery(name = "CauDeptrabajo.findByIdDeptrab", query = "SELECT c FROM CauDeptrabajo c WHERE c.idDeptrab = :idDeptrab")})
public class CauDeptrabajo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "FECHAINIC")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechainic;
    @Column(name = "FECHAFIN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechafin;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_DEPTRAB")
    private Integer idDeptrab;
    @JoinColumn(name = "ID_DEPARTAMENTOS", referencedColumnName = "ID_DEPARTAMENTOS")
    @ManyToOne(optional = false)
    private CauDepartamentos idDepartamentos;
    @JoinColumn(name = "ID_TRABAJO", referencedColumnName = "ID_TRABAJO")
    @ManyToOne
    private CauTipotrabajo idTrabajo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDeptrab")
    private Collection<CauSolicitudtrabajos> cauSolicitudtrabajosCollection;
    @OneToMany(mappedBy = "idDeptrab")
    private Collection<CauTraasigwf> cauTraasigwfCollection;

    public CauDeptrabajo() {
    }

    public CauDeptrabajo(Integer idDeptrab) {
        this.idDeptrab = idDeptrab;
    }

    public Date getFechainic() {
        return fechainic;
    }

    public void setFechainic(Date fechainic) {
        this.fechainic = fechainic;
    }

    public Date getFechafin() {
        return fechafin;
    }

    public void setFechafin(Date fechafin) {
        this.fechafin = fechafin;
    }

    public Integer getIdDeptrab() {
        return idDeptrab;
    }

    public void setIdDeptrab(Integer idDeptrab) {
        this.idDeptrab = idDeptrab;
    }

    public CauDepartamentos getIdDepartamentos() {
        return idDepartamentos;
    }

    public void setIdDepartamentos(CauDepartamentos idDepartamentos) {
        this.idDepartamentos = idDepartamentos;
    }

    public CauTipotrabajo getIdTrabajo() {
        return idTrabajo;
    }

    public void setIdTrabajo(CauTipotrabajo idTrabajo) {
        this.idTrabajo = idTrabajo;
    }

    @XmlTransient
    public Collection<CauSolicitudtrabajos> getCauSolicitudtrabajosCollection() {
        return cauSolicitudtrabajosCollection;
    }

    public void setCauSolicitudtrabajosCollection(Collection<CauSolicitudtrabajos> cauSolicitudtrabajosCollection) {
        this.cauSolicitudtrabajosCollection = cauSolicitudtrabajosCollection;
    }

    @XmlTransient
    public Collection<CauTraasigwf> getCauTraasigwfCollection() {
        return cauTraasigwfCollection;
    }

    public void setCauTraasigwfCollection(Collection<CauTraasigwf> cauTraasigwfCollection) {
        this.cauTraasigwfCollection = cauTraasigwfCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDeptrab != null ? idDeptrab.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CauDeptrabajo)) {
            return false;
        }
        CauDeptrabajo other = (CauDeptrabajo) object;
        if ((this.idDeptrab == null && other.idDeptrab != null) || (this.idDeptrab != null && !this.idDeptrab.equals(other.idDeptrab))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sfp.model.CauDeptrabajo[ idDeptrab=" + idDeptrab + " ]";
    }
    
}
