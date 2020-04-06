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
@Table(name = "CAU_TRANXPAR", catalog = "", schema = "CAU")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CauTranxpar.findAll", query = "SELECT c FROM CauTranxpar c")
    , @NamedQuery(name = "CauTranxpar.findByFechaasig", query = "SELECT c FROM CauTranxpar c WHERE c.fechaasig = :fechaasig")
    , @NamedQuery(name = "CauTranxpar.findByIdTransparam", query = "SELECT c FROM CauTranxpar c WHERE c.idTransparam = :idTransparam")})
public class CauTranxpar implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "FECHAASIG")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaasig;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_TRANSPARAM")
    private Integer idTransparam;
    @JoinColumn(name = "ID_PARAM", referencedColumnName = "ID_PARAM")
    @ManyToOne
    private CauParametros idParam;
    @JoinColumn(name = "ID_TRANS", referencedColumnName = "ID_TRANS")
    @ManyToOne
    private CauTransaccion idTrans;

    public CauTranxpar() {
    }

    public CauTranxpar(Integer idTransparam) {
        this.idTransparam = idTransparam;
    }

    public Date getFechaasig() {
        return fechaasig;
    }

    public void setFechaasig(Date fechaasig) {
        this.fechaasig = fechaasig;
    }

    public Integer getIdTransparam() {
        return idTransparam;
    }

    public void setIdTransparam(Integer idTransparam) {
        this.idTransparam = idTransparam;
    }

    public CauParametros getIdParam() {
        return idParam;
    }

    public void setIdParam(CauParametros idParam) {
        this.idParam = idParam;
    }

    public CauTransaccion getIdTrans() {
        return idTrans;
    }

    public void setIdTrans(CauTransaccion idTrans) {
        this.idTrans = idTrans;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTransparam != null ? idTransparam.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CauTranxpar)) {
            return false;
        }
        CauTranxpar other = (CauTranxpar) object;
        if ((this.idTransparam == null && other.idTransparam != null) || (this.idTransparam != null && !this.idTransparam.equals(other.idTransparam))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sfp.model.CauTranxpar[ idTransparam=" + idTransparam + " ]";
    }
    
}
