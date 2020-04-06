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
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jleal
 */
@Entity
@Table(name = "CAU_LOGOPEXCEP", catalog = "", schema = "CAU")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CauLogopexcep.findAll", query = "SELECT c FROM CauLogopexcep c")
    , @NamedQuery(name = "CauLogopexcep.findByIdExcep", query = "SELECT c FROM CauLogopexcep c WHERE c.idExcep = :idExcep")
    , @NamedQuery(name = "CauLogopexcep.findByDescExcepcion", query = "SELECT c FROM CauLogopexcep c WHERE c.descExcepcion = :descExcepcion")
    , @NamedQuery(name = "CauLogopexcep.findByFechaexcep", query = "SELECT c FROM CauLogopexcep c WHERE c.fechaexcep = :fechaexcep")})
public class CauLogopexcep implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_EXCEP")
    private Integer idExcep;
    @Size(max = 320)
    @Column(name = "DESC_EXCEPCION")
    private String descExcepcion;
    @Column(name = "FECHAEXCEP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaexcep;
    @JoinColumn(name = "ID_OPERACION", referencedColumnName = "ID_OPERACION")
    @ManyToOne
    private CauTipooper idOperacion;
    @JoinColumn(name = "ID_TRANS", referencedColumnName = "ID_TRANS")
    @ManyToOne
    private CauTransaccion idTrans;
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO")
    @ManyToOne
    private CauUsuarios idUsuario;

    public CauLogopexcep() {
    }

    public CauLogopexcep(Integer idExcep) {
        this.idExcep = idExcep;
    }

    public Integer getIdExcep() {
        return idExcep;
    }

    public void setIdExcep(Integer idExcep) {
        this.idExcep = idExcep;
    }

    public String getDescExcepcion() {
        return descExcepcion;
    }

    public void setDescExcepcion(String descExcepcion) {
        this.descExcepcion = descExcepcion;
    }

    public Date getFechaexcep() {
        return fechaexcep;
    }

    public void setFechaexcep(Date fechaexcep) {
        this.fechaexcep = fechaexcep;
    }

    public CauTipooper getIdOperacion() {
        return idOperacion;
    }

    public void setIdOperacion(CauTipooper idOperacion) {
        this.idOperacion = idOperacion;
    }

    public CauTransaccion getIdTrans() {
        return idTrans;
    }

    public void setIdTrans(CauTransaccion idTrans) {
        this.idTrans = idTrans;
    }

    public CauUsuarios getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(CauUsuarios idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idExcep != null ? idExcep.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CauLogopexcep)) {
            return false;
        }
        CauLogopexcep other = (CauLogopexcep) object;
        if ((this.idExcep == null && other.idExcep != null) || (this.idExcep != null && !this.idExcep.equals(other.idExcep))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sfp.model.CauLogopexcep[ idExcep=" + idExcep + " ]";
    }
    
}
