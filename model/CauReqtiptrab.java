/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfp.model;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "CAU_REQTIPTRAB", catalog = "", schema = "CAU")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CauReqtiptrab.findAll", query = "SELECT c FROM CauReqtiptrab c")
    , @NamedQuery(name = "CauReqtiptrab.findByIdReqtrab", query = "SELECT c FROM CauReqtiptrab c WHERE c.idReqtrab = :idReqtrab")
    , @NamedQuery(name = "CauReqtiptrab.findByFechavigini", query = "SELECT c FROM CauReqtiptrab c WHERE c.fechavigini = :fechavigini")
    , @NamedQuery(name = "CauReqtiptrab.findByFechavigfin", query = "SELECT c FROM CauReqtiptrab c WHERE c.fechavigfin = :fechavigfin")})
public class CauReqtiptrab implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_REQTRAB")
    private BigDecimal idReqtrab;
    @Column(name = "FECHAVIGINI")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechavigini;
    @Column(name = "FECHAVIGFIN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechavigfin;
    @JoinColumn(name = "ID_REQ", referencedColumnName = "ID_REQ")
    @ManyToOne
    private CauRequisitos idReq;
    @JoinColumn(name = "ID_TRABAJO", referencedColumnName = "ID_TRABAJO")
    @ManyToOne
    private CauTipotrabajo idTrabajo;

    public CauReqtiptrab() {
    }

    public CauReqtiptrab(BigDecimal idReqtrab) {
        this.idReqtrab = idReqtrab;
    }

    public BigDecimal getIdReqtrab() {
        return idReqtrab;
    }

    public void setIdReqtrab(BigDecimal idReqtrab) {
        this.idReqtrab = idReqtrab;
    }

    public Date getFechavigini() {
        return fechavigini;
    }

    public void setFechavigini(Date fechavigini) {
        this.fechavigini = fechavigini;
    }

    public Date getFechavigfin() {
        return fechavigfin;
    }

    public void setFechavigfin(Date fechavigfin) {
        this.fechavigfin = fechavigfin;
    }

    public CauRequisitos getIdReq() {
        return idReq;
    }

    public void setIdReq(CauRequisitos idReq) {
        this.idReq = idReq;
    }

    public CauTipotrabajo getIdTrabajo() {
        return idTrabajo;
    }

    public void setIdTrabajo(CauTipotrabajo idTrabajo) {
        this.idTrabajo = idTrabajo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idReqtrab != null ? idReqtrab.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CauReqtiptrab)) {
            return false;
        }
        CauReqtiptrab other = (CauReqtiptrab) object;
        if ((this.idReqtrab == null && other.idReqtrab != null) || (this.idReqtrab != null && !this.idReqtrab.equals(other.idReqtrab))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sfp.model.CauReqtiptrab[ idReqtrab=" + idReqtrab + " ]";
    }
    
}
