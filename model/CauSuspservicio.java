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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author jleal
 */
@Entity
@Table(name = "CAU_SUSPSERVICIO", catalog = "", schema = "CAU")
@TableGenerator(name="SuspServ_Gen", table = "CAU_IDGEN", pkColumnName = "GEN_NAME", 
                  valueColumnName = "GEN_VAL", pkColumnValue = "SuspServ_Gen", allocationSize=1)
@NamedQueries({
    @NamedQuery(name = "CauSuspservicio.findAll", query = "SELECT c FROM CauSuspservicio c")
    , @NamedQuery(name = "CauSuspservicio.findByFechahorasusp", query = "SELECT c FROM CauSuspservicio c WHERE c.fechahorasusp = :fechahorasusp")
    , @NamedQuery(name = "CauSuspservicio.findByFechahorareanud", query = "SELECT c FROM CauSuspservicio c WHERE c.fechahorareanud = :fechahorareanud")
    , @NamedQuery(name = "CauSuspservicio.findByIdSuspserv", query = "SELECT c FROM CauSuspservicio c WHERE c.idSuspserv = :idSuspserv")
    , @NamedQuery(name = "CauSuspservicio.findByDescsuspserv", query = "SELECT c FROM CauSuspservicio c WHERE c.descsuspserv = :descsuspserv")
    , @NamedQuery(name = "CauSuspservicio.findByReasuspserv", query = "SELECT c FROM CauSuspservicio c WHERE c.reasuspserv = :reasuspserv")})
public class CauSuspservicio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHAHORASUSP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechahorasusp;
    @Column(name = "FECHAHORAREANUD")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechahorareanud;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator="SuspServ_Gen")
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_SUSPSERV")
    private Integer idSuspserv;
    @Size(max = 1500)
    @Column(name = "DESCSUSPSERV")
    private String descsuspserv;
    @Size(max = 1500)
    @Column(name = "REASUSPSERV")
    private String reasuspserv;
    @JoinColumn(name = "ID_TRABTEC", referencedColumnName = "ID_TRABTEC")
    @ManyToOne(optional = false)
    private CauTrabajostecnicos idTrabtec;
    @JoinColumn(name = "ID_USUAREAN", referencedColumnName = "ID_USUARIO")
    @ManyToOne
    private CauUsuarios idUsuarean;
    @JoinColumn(name = "ID_USUASUSP", referencedColumnName = "ID_USUARIO")
    @ManyToOne
    private CauUsuarios idUsuasusp;

    public CauSuspservicio() {
    }

    public CauSuspservicio(Integer idSuspserv) {
        this.idSuspserv = idSuspserv;
    }

    public CauSuspservicio(Integer idSuspserv, Date fechahorasusp) {
        this.idSuspserv = idSuspserv;
        this.fechahorasusp = fechahorasusp;
    }

    public Date getFechahorasusp() {
        return fechahorasusp;
    }

    public void setFechahorasusp(Date fechahorasusp) {
        this.fechahorasusp = fechahorasusp;
    }

    public Date getFechahorareanud() {
        return fechahorareanud;
    }

    public void setFechahorareanud(Date fechahorareanud) {
        this.fechahorareanud = fechahorareanud;
    }

    public Integer getIdSuspserv() {
        return idSuspserv;
    }

    public void setIdSuspserv(Integer idSuspserv) {
        this.idSuspserv = idSuspserv;
    }

    public String getDescsuspserv() {
        return descsuspserv;
    }

    public void setDescsuspserv(String descsuspserv) {
        this.descsuspserv = descsuspserv;
    }

    public String getReasuspserv() {
        return reasuspserv;
    }

    public void setReasuspserv(String reasuspserv) {
        this.reasuspserv = reasuspserv;
    }

    public CauTrabajostecnicos getIdTrabtec() {
        return idTrabtec;
    }

    public void setIdTrabtec(CauTrabajostecnicos idTrabtec) {
        this.idTrabtec = idTrabtec;
    }

    public CauUsuarios getIdUsuarean() {
        return idUsuarean;
    }

    public void setIdUsuarean(CauUsuarios idUsuarean) {
        this.idUsuarean = idUsuarean;
    }

    public CauUsuarios getIdUsuasusp() {
        return idUsuasusp;
    }

    public void setIdUsuasusp(CauUsuarios idUsuasusp) {
        this.idUsuasusp = idUsuasusp;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSuspserv != null ? idSuspserv.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CauSuspservicio)) {
            return false;
        }
        CauSuspservicio other = (CauSuspservicio) object;
        if ((this.idSuspserv == null && other.idSuspserv != null) || (this.idSuspserv != null && !this.idSuspserv.equals(other.idSuspserv))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sfp.model.CauSuspservicio[ idSuspserv=" + idSuspserv + " ]";
    }
    
}
