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
@Table(name = "CAU_MESASERVICIO", catalog = "", schema = "CAU")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CauMesaservicio.findAll", query = "SELECT c FROM CauMesaservicio c")
    , @NamedQuery(name = "CauMesaservicio.findByIdMesaserv", query = "SELECT c FROM CauMesaservicio c WHERE c.idMesaserv = :idMesaserv")
    , @NamedQuery(name = "CauMesaservicio.findByDescripcion", query = "SELECT c FROM CauMesaservicio c WHERE c.descripcion = :descripcion")})
public class CauMesaservicio implements Serializable {

    @Size(max = 80)
    @Column(name = "DESCRIPCION")
    private String descripcion;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_MESASERV")
    private Integer idMesaserv;
    @OneToMany(mappedBy = "idMesaserv")
    private Collection<CauGrupos> cauGruposCollection;
    @OneToMany(mappedBy = "idMesaserv")
    private Collection<CauLogoper> cauLogoperCollection;
    @OneToMany(mappedBy = "idMesaserv")
    private Collection<CauSolicitudes> cauSolicitudesCollection;
    @OneToMany(mappedBy = "idMesaserv")
    private Collection<CauDepartamentos> cauDepartamentosCollection;
    @OneToMany(mappedBy = "idMesaserv")
    private Collection<CauConfiguracion> cauConfiguracionCollection;
    @OneToMany(mappedBy = "idMesaserv")
    private Collection<CauProcesoscorreos> cauProcesoscorreosCollection;

    public CauMesaservicio() {
    }

    public CauMesaservicio(Integer idMesaserv) {
        this.idMesaserv = idMesaserv;
    }

    public Integer getIdMesaserv() {
        return idMesaserv;
    }

    public void setIdMesaserv(Integer idMesaserv) {
        this.idMesaserv = idMesaserv;
    }


    @XmlTransient
    public Collection<CauGrupos> getCauGruposCollection() {
        return cauGruposCollection;
    }

    public void setCauGruposCollection(Collection<CauGrupos> cauGruposCollection) {
        this.cauGruposCollection = cauGruposCollection;
    }

    @XmlTransient
    public Collection<CauLogoper> getCauLogoperCollection() {
        return cauLogoperCollection;
    }

    public void setCauLogoperCollection(Collection<CauLogoper> cauLogoperCollection) {
        this.cauLogoperCollection = cauLogoperCollection;
    }

    @XmlTransient
    public Collection<CauSolicitudes> getCauSolicitudesCollection() {
        return cauSolicitudesCollection;
    }

    public void setCauSolicitudesCollection(Collection<CauSolicitudes> cauSolicitudesCollection) {
        this.cauSolicitudesCollection = cauSolicitudesCollection;
    }

    @XmlTransient
    public Collection<CauDepartamentos> getCauDepartamentosCollection() {
        return cauDepartamentosCollection;
    }

    public void setCauDepartamentosCollection(Collection<CauDepartamentos> cauDepartamentosCollection) {
        this.cauDepartamentosCollection = cauDepartamentosCollection;
    }

    @XmlTransient
    public Collection<CauConfiguracion> getCauConfiguracionCollection() {
        return cauConfiguracionCollection;
    }

    public void setCauConfiguracionCollection(Collection<CauConfiguracion> cauConfiguracionCollection) {
        this.cauConfiguracionCollection = cauConfiguracionCollection;
    }
    
    @XmlTransient
    public Collection<CauProcesoscorreos> getCauProcesoscorreosCollection() {
        return cauProcesoscorreosCollection;
    }

    public void setCauProcesoscorreosCollection(Collection<CauProcesoscorreos> cauProcesoscorreosCollection) {
        this.cauProcesoscorreosCollection = cauProcesoscorreosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMesaserv != null ? idMesaserv.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CauMesaservicio)) {
            return false;
        }
        CauMesaservicio other = (CauMesaservicio) object;
        if ((this.idMesaserv == null && other.idMesaserv != null) || (this.idMesaserv != null && !this.idMesaserv.equals(other.idMesaserv))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sfp.model.CauMesaservicio[ idMesaserv=" + idMesaserv + " ]";
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
}
