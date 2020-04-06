/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfp.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jleal
 */
@Entity
@Table(name = "CAU_TECTIPOTRAB", catalog = "", schema = "CAU")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CauTectipotrab.findAll", query = "SELECT c FROM CauTectipotrab c")
    , @NamedQuery(name = "CauTectipotrab.findByFechaviginictectrab", query = "SELECT c FROM CauTectipotrab c WHERE c.fechaviginictectrab = :fechaviginictectrab")
    , @NamedQuery(name = "CauTectipotrab.findByFechavigfintectrab", query = "SELECT c FROM CauTectipotrab c WHERE c.fechavigfintectrab = :fechavigfintectrab")
    , @NamedQuery(name = "CauTectipotrab.findByIdTrabajo", query = "SELECT c FROM CauTectipotrab c WHERE c.cauTectipotrabPK.idTrabajo = :idTrabajo")
    , @NamedQuery(name = "CauTectipotrab.findByIdUsuario", query = "SELECT c FROM CauTectipotrab c WHERE c.cauTectipotrabPK.idUsuario = :idUsuario")})
public class CauTectipotrab implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CauTectipotrabPK cauTectipotrabPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHAVIGINICTECTRAB")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaviginictectrab;
    @Column(name = "FECHAVIGFINTECTRAB")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechavigfintectrab;
    @JoinColumn(name = "ID_TRABAJO", referencedColumnName = "ID_TRABAJO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private CauTipotrabajo cauTipotrabajo;
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private CauUsuarios cauUsuarios;

    public CauTectipotrab() {
    }

    public CauTectipotrab(CauTectipotrabPK cauTectipotrabPK) {
        this.cauTectipotrabPK = cauTectipotrabPK;
    }

    public CauTectipotrab(CauTectipotrabPK cauTectipotrabPK, Date fechaviginictectrab) {
        this.cauTectipotrabPK = cauTectipotrabPK;
        this.fechaviginictectrab = fechaviginictectrab;
    }

    public CauTectipotrab(int idTrabajo, int idUsuario) {
        this.cauTectipotrabPK = new CauTectipotrabPK(idTrabajo, idUsuario);
    }

    public CauTectipotrabPK getCauTectipotrabPK() {
        return cauTectipotrabPK;
    }

    public void setCauTectipotrabPK(CauTectipotrabPK cauTectipotrabPK) {
        this.cauTectipotrabPK = cauTectipotrabPK;
    }

    public Date getFechaviginictectrab() {
        return fechaviginictectrab;
    }

    public void setFechaviginictectrab(Date fechaviginictectrab) {
        this.fechaviginictectrab = fechaviginictectrab;
    }

    public Date getFechavigfintectrab() {
        return fechavigfintectrab;
    }

    public void setFechavigfintectrab(Date fechavigfintectrab) {
        this.fechavigfintectrab = fechavigfintectrab;
    }

    public CauTipotrabajo getCauTipotrabajo() {
        return cauTipotrabajo;
    }

    public void setCauTipotrabajo(CauTipotrabajo cauTipotrabajo) {
        this.cauTipotrabajo = cauTipotrabajo;
    }

    public CauUsuarios getCauUsuarios() {
        return cauUsuarios;
    }

    public void setCauUsuarios(CauUsuarios cauUsuarios) {
        this.cauUsuarios = cauUsuarios;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cauTectipotrabPK != null ? cauTectipotrabPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CauTectipotrab)) {
            return false;
        }
        CauTectipotrab other = (CauTectipotrab) object;
        if ((this.cauTectipotrabPK == null && other.cauTectipotrabPK != null) || (this.cauTectipotrabPK != null && !this.cauTectipotrabPK.equals(other.cauTectipotrabPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sfp.model.CauTectipotrab[ cauTectipotrabPK=" + cauTectipotrabPK + " ]";
    }
    
}
