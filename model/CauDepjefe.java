/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfp.model;

import java.io.Serializable;
import java.util.Date;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jleal
 */
@Entity
@Table(name = "CAU_DEPJEFE", catalog = "", schema = "CAU")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CauDepjefe.findAll", query = "SELECT c FROM CauDepjefe c")
    , @NamedQuery(name = "CauDepjefe.findByIdUsuario", query = "SELECT c FROM CauDepjefe c WHERE c.cauDepjefePK.idUsuario = :idUsuario")
    , @NamedQuery(name = "CauDepjefe.findByIdDepartamentos", query = "SELECT c FROM CauDepjefe c WHERE c.cauDepjefePK.idDepartamentos = :idDepartamentos")
    , @NamedQuery(name = "CauDepjefe.findByFechaviginicjefdepto", query = "SELECT c FROM CauDepjefe c WHERE c.fechaviginicjefdepto = :fechaviginicjefdepto")
    , @NamedQuery(name = "CauDepjefe.findByFechavigfinjefdepto", query = "SELECT c FROM CauDepjefe c WHERE c.fechavigfinjefdepto = :fechavigfinjefdepto")})
public class CauDepjefe implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CauDepjefePK cauDepjefePK;
    @Column(name = "FECHAVIGINICJEFDEPTO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaviginicjefdepto;
    @Column(name = "FECHAVIGFINJEFDEPTO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechavigfinjefdepto;
    @JoinColumn(name = "ID_DEPARTAMENTOS", referencedColumnName = "ID_DEPARTAMENTOS", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private CauDepartamentos cauDepartamentos;
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private CauUsuarios cauUsuarios;

    public CauDepjefe() {
    }

    public CauDepjefe(CauDepjefePK cauDepjefePK) {
        this.cauDepjefePK = cauDepjefePK;
    }

    public CauDepjefe(int idUsuario, int idDepartamentos) {
        this.cauDepjefePK = new CauDepjefePK(idUsuario, idDepartamentos);
    }

    public CauDepjefePK getCauDepjefePK() {
        return cauDepjefePK;
    }

    public void setCauDepjefePK(CauDepjefePK cauDepjefePK) {
        this.cauDepjefePK = cauDepjefePK;
    }

    public Date getFechaviginicjefdepto() {
        return fechaviginicjefdepto;
    }

    public void setFechaviginicjefdepto(Date fechaviginicjefdepto) {
        this.fechaviginicjefdepto = fechaviginicjefdepto;
    }

    public Date getFechavigfinjefdepto() {
        return fechavigfinjefdepto;
    }

    public void setFechavigfinjefdepto(Date fechavigfinjefdepto) {
        this.fechavigfinjefdepto = fechavigfinjefdepto;
    }

    public CauDepartamentos getCauDepartamentos() {
        return cauDepartamentos;
    }

    public void setCauDepartamentos(CauDepartamentos cauDepartamentos) {
        this.cauDepartamentos = cauDepartamentos;
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
        hash += (cauDepjefePK != null ? cauDepjefePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CauDepjefe)) {
            return false;
        }
        CauDepjefe other = (CauDepjefe) object;
        if ((this.cauDepjefePK == null && other.cauDepjefePK != null) || (this.cauDepjefePK != null && !this.cauDepjefePK.equals(other.cauDepjefePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sfp.model.CauDepjefe[ cauDepjefePK=" + cauDepjefePK + " ]";
    }
    
}
