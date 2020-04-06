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
import javax.validation.constraints.Size;

/**
 *
 * @author jleal
 */
@Entity
@Table(name = "CAU_DEPARTAMENTOS", catalog = "", schema = "CAU")
@NamedQueries({
    @NamedQuery(name = "CauDepartamentos.findAll", query = "SELECT c FROM CauDepartamentos c")
    , @NamedQuery(name = "CauDepartamentos.findByIdDepartamentos", query = "SELECT c FROM CauDepartamentos c WHERE c.idDepartamentos = :idDepartamentos")
    , @NamedQuery(name = "CauDepartamentos.findByDescripcion", query = "SELECT c FROM CauDepartamentos c WHERE c.descripcion = :descripcion")
    , @NamedQuery(name = "CauDepartamentos.findByFechaviginicdep", query = "SELECT c FROM CauDepartamentos c WHERE c.fechaviginicdep = :fechaviginicdep")
    , @NamedQuery(name = "CauDepartamentos.findByFechavigfindep", query = "SELECT c FROM CauDepartamentos c WHERE c.fechavigfindep = :fechavigfindep")
    , @NamedQuery(name = "CauDepartamentos.findByDespliegue", query = "SELECT c FROM CauDepartamentos c WHERE c.despliegue = :despliegue")})
public class CauDepartamentos implements Serializable {

    @Size(max = 80)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHAVIGINICDEP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaviginicdep;
    @Size(max = 5)
    @Column(name = "DESPLIEGUE")
    private String despliegue;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_DEPARTAMENTOS")
    private Integer idDepartamentos;
    @Column(name = "FECHAVIGFINDEP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechavigfindep;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDepartamentos")
    private Collection<CauDeptrabajo> cauDeptrabajoCollection;
    @JoinColumn(name = "ID_MESASERV", referencedColumnName = "ID_MESASERV")
    @ManyToOne
    private CauMesaservicio idMesaserv;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cauDepartamentos")
    private Collection<CauDepjefe> cauDepjefeCollection;

    public CauDepartamentos() {
    }

    public CauDepartamentos(Integer idDepartamentos) {
        this.idDepartamentos = idDepartamentos;
    }

    public CauDepartamentos(Integer idDepartamentos, Date fechaviginicdep) {
        this.idDepartamentos = idDepartamentos;
        this.fechaviginicdep = fechaviginicdep;
    }

    public Integer getIdDepartamentos() {
        return idDepartamentos;
    }

    public void setIdDepartamentos(Integer idDepartamentos) {
        this.idDepartamentos = idDepartamentos;
    }


    public Date getFechavigfindep() {
        return fechavigfindep;
    }

    public void setFechavigfindep(Date fechavigfindep) {
        this.fechavigfindep = fechavigfindep;
    }


    public Collection<CauDeptrabajo> getCauDeptrabajoCollection() {
        return cauDeptrabajoCollection;
    }

    public void setCauDeptrabajoCollection(Collection<CauDeptrabajo> cauDeptrabajoCollection) {
        this.cauDeptrabajoCollection = cauDeptrabajoCollection;
    }

    public CauMesaservicio getIdMesaserv() {
        return idMesaserv;
    }

    public void setIdMesaserv(CauMesaservicio idMesaserv) {
        this.idMesaserv = idMesaserv;
    }

    public Collection<CauDepjefe> getCauDepjefeCollection() {
        return cauDepjefeCollection;
    }

    public void setCauDepjefeCollection(Collection<CauDepjefe> cauDepjefeCollection) {
        this.cauDepjefeCollection = cauDepjefeCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDepartamentos != null ? idDepartamentos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CauDepartamentos)) {
            return false;
        }
        CauDepartamentos other = (CauDepartamentos) object;
        if ((this.idDepartamentos == null && other.idDepartamentos != null) || (this.idDepartamentos != null && !this.idDepartamentos.equals(other.idDepartamentos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sfp.model.CauDepartamentos[ idDepartamentos=" + idDepartamentos + " ]";
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaviginicdep() {
        return fechaviginicdep;
    }

    public void setFechaviginicdep(Date fechaviginicdep) {
        this.fechaviginicdep = fechaviginicdep;
    }

    public String getDespliegue() {
        return despliegue;
    }

    public void setDespliegue(String despliegue) {
        this.despliegue = despliegue;
    }
    
}
