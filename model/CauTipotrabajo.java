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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jleal
 */
@Entity
@Table(name = "CAU_TIPOTRABAJO", catalog = "", schema = "CAU")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CauTipotrabajo.findAll", query = "SELECT c FROM CauTipotrabajo c")
    , @NamedQuery(name = "CauTipotrabajo.findByIdTrabajo", query = "SELECT c FROM CauTipotrabajo c WHERE c.idTrabajo = :idTrabajo")
    , @NamedQuery(name = "CauTipotrabajo.findByDescripcion", query = "SELECT c FROM CauTipotrabajo c WHERE c.descripcion = :descripcion")
    , @NamedQuery(name = "CauTipotrabajo.findByFechaviginictra", query = "SELECT c FROM CauTipotrabajo c WHERE c.fechaviginictra = :fechaviginictra")
    , @NamedQuery(name = "CauTipotrabajo.findByFechavigfintra", query = "SELECT c FROM CauTipotrabajo c WHERE c.fechavigfintra = :fechavigfintra")})
public class CauTipotrabajo implements Serializable {

    @Size(max = 200)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHAVIGINICTRA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaviginictra;
    @Size(max = 500)
    @Column(name = "REQUISITOS")
    private String requisitos;
    @Size(max = 5)
    @Column(name = "DESPLIEGUE")
    private String despliegue;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_TRABAJO")
    private Integer idTrabajo;
    @Column(name = "FECHAVIGFINTRA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechavigfintra;
    @OneToMany(mappedBy = "idTrabajo")
    private Collection<CauDeptrabajo> cauDeptrabajoCollection;
    @JoinColumn(name = "ID_ESTSERV", referencedColumnName = "ID_ESTSERV")
    @ManyToOne
    private CauEstandarservicio idEstserv;
    @JoinColumn(name = "ID_AREAGRUPOS", referencedColumnName = "ID_AREAGRUPOS")
    @ManyToOne
    private CauGrupos idAreagrupos;
    @JoinColumn(name = "ID_TIPOIMP", referencedColumnName = "ID_TIPOIMP")
    @ManyToOne
    private CauTipoimp idTipoimp;
    @JoinColumn(name = "ID_TIPOSERV", referencedColumnName = "ID_TIPOSERV")
    @ManyToOne
    private CauTiposerv idTiposerv;
    @JoinColumn(name = "ID_TIPOURG", referencedColumnName = "ID_TIPOURG")
    @ManyToOne
    private CauTipourg idTipourg;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cauTipotrabajo")
    private Collection<CauTectipotrab> cauTectipotrabCollection;
    @OneToMany(mappedBy = "idTrabajo")
    private Collection<CauAuttipotrab> cauAuttipotrabCollection;
    @OneToMany(mappedBy = "idTrabajo")
    private Collection<CauReqtiptrab> cauReqtiptrabCollection;

    public CauTipotrabajo() {
    }

    public CauTipotrabajo(Integer idTrabajo) {
        this.idTrabajo = idTrabajo;
    }

    public CauTipotrabajo(Integer idTrabajo, Date fechaviginictra) {
        this.idTrabajo = idTrabajo;
        this.fechaviginictra = fechaviginictra;
    }

    public Integer getIdTrabajo() {
        return idTrabajo;
    }

    public void setIdTrabajo(Integer idTrabajo) {
        this.idTrabajo = idTrabajo;
    }


    public Date getFechavigfintra() {
        return fechavigfintra;
    }

    public void setFechavigfintra(Date fechavigfintra) {
        this.fechavigfintra = fechavigfintra;
    }

    @XmlTransient
    public Collection<CauDeptrabajo> getCauDeptrabajoCollection() {
        return cauDeptrabajoCollection;
    }

    public void setCauDeptrabajoCollection(Collection<CauDeptrabajo> cauDeptrabajoCollection) {
        this.cauDeptrabajoCollection = cauDeptrabajoCollection;
    }

    public CauEstandarservicio getIdEstserv() {
        return idEstserv;
    }

    public void setIdEstserv(CauEstandarservicio idEstserv) {
        this.idEstserv = idEstserv;
    }

    public CauGrupos getIdAreagrupos() {
        return idAreagrupos;
    }

    public void setIdAreagrupos(CauGrupos idAreagrupos) {
        this.idAreagrupos = idAreagrupos;
    }

    public CauTipoimp getIdTipoimp() {
        return idTipoimp;
    }

    public void setIdTipoimp(CauTipoimp idTipoimp) {
        this.idTipoimp = idTipoimp;
    }

    public CauTiposerv getIdTiposerv() {
        return idTiposerv;
    }

    public void setIdTiposerv(CauTiposerv idTiposerv) {
        this.idTiposerv = idTiposerv;
    }

    public CauTipourg getIdTipourg() {
        return idTipourg;
    }

    public void setIdTipourg(CauTipourg idTipourg) {
        this.idTipourg = idTipourg;
    }

    @XmlTransient
    public Collection<CauTectipotrab> getCauTectipotrabCollection() {
        return cauTectipotrabCollection;
    }

    public void setCauTectipotrabCollection(Collection<CauTectipotrab> cauTectipotrabCollection) {
        this.cauTectipotrabCollection = cauTectipotrabCollection;
    }

    @XmlTransient
    public Collection<CauAuttipotrab> getCauAuttipotrabCollection() {
        return cauAuttipotrabCollection;
    }

    public void setCauAuttipotrabCollection(Collection<CauAuttipotrab> cauAuttipotrabCollection) {
        this.cauAuttipotrabCollection = cauAuttipotrabCollection;
    }

    @XmlTransient
    public Collection<CauReqtiptrab> getCauReqtiptrabCollection() {
        return cauReqtiptrabCollection;
    }

    public void setCauReqtiptrabCollection(Collection<CauReqtiptrab> cauReqtiptrabCollection) {
        this.cauReqtiptrabCollection = cauReqtiptrabCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTrabajo != null ? idTrabajo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CauTipotrabajo)) {
            return false;
        }
        CauTipotrabajo other = (CauTipotrabajo) object;
        if ((this.idTrabajo == null && other.idTrabajo != null) || (this.idTrabajo != null && !this.idTrabajo.equals(other.idTrabajo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sfp.model.CauTipotrabajo[ idTrabajo=" + idTrabajo + " ]";
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaviginictra() {
        return fechaviginictra;
    }

    public void setFechaviginictra(Date fechaviginictra) {
        this.fechaviginictra = fechaviginictra;
    }

    public String getRequisitos() {
        return requisitos;
    }

    public void setRequisitos(String requisitos) {
        this.requisitos = requisitos;
    }

    public String getDespliegue() {
        return despliegue;
    }

    public void setDespliegue(String despliegue) {
        this.despliegue = despliegue;
    }
    
}
