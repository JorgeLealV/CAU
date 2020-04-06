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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jleal
 */
@Entity
@Table(name = "CAU_USUARIOTIPO", catalog = "", schema = "CAU")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CauUsuariotipo.findAll", query = "SELECT c FROM CauUsuariotipo c")
    , @NamedQuery(name = "CauUsuariotipo.findByFechaasig", query = "SELECT c FROM CauUsuariotipo c WHERE c.fechaasig = :fechaasig")
    , @NamedQuery(name = "CauUsuariotipo.findByIdUsuariotipo", query = "SELECT c FROM CauUsuariotipo c WHERE c.idUsuariotipo = :idUsuariotipo")})
public class CauUsuariotipo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "FECHAASIG")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaasig;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_USUARIOTIPO")
    private Integer idUsuariotipo;
    @JoinColumn(name = "ID_TIPOUSUARIO", referencedColumnName = "ID_TIPOUSUARIO")
    @ManyToOne
    private CauTipodeusuario idTipousuario;
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO")
    @ManyToOne
    private CauUsuarios idUsuario;

    public CauUsuariotipo() {
    }

    public CauUsuariotipo(Integer idUsuariotipo) {
        this.idUsuariotipo = idUsuariotipo;
    }

    public Date getFechaasig() {
        return fechaasig;
    }

    public void setFechaasig(Date fechaasig) {
        this.fechaasig = fechaasig;
    }

    public Integer getIdUsuariotipo() {
        return idUsuariotipo;
    }

    public void setIdUsuariotipo(Integer idUsuariotipo) {
        this.idUsuariotipo = idUsuariotipo;
    }

    public CauTipodeusuario getIdTipousuario() {
        return idTipousuario;
    }

    public void setIdTipousuario(CauTipodeusuario idTipousuario) {
        this.idTipousuario = idTipousuario;
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
        hash += (idUsuariotipo != null ? idUsuariotipo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CauUsuariotipo)) {
            return false;
        }
        CauUsuariotipo other = (CauUsuariotipo) object;
        if ((this.idUsuariotipo == null && other.idUsuariotipo != null) || (this.idUsuariotipo != null && !this.idUsuariotipo.equals(other.idUsuariotipo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sfp.model.CauUsuariotipo[ idUsuariotipo=" + idUsuariotipo + " ]";
    }
    
}
