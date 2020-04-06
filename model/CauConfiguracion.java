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
@Table(name = "CAU_CONFIGURACION", catalog = "", schema = "CAU")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CauConfiguracion.findAll", query = "SELECT c FROM CauConfiguracion c")
    , @NamedQuery(name = "CauConfiguracion.findByUrl", query = "SELECT c FROM CauConfiguracion c WHERE c.url = :url")
    , @NamedQuery(name = "CauConfiguracion.findByFechainicvigconf", query = "SELECT c FROM CauConfiguracion c WHERE c.fechainicvigconf = :fechainicvigconf")
    , @NamedQuery(name = "CauConfiguracion.findByFechafinvigconf", query = "SELECT c FROM CauConfiguracion c WHERE c.fechafinvigconf = :fechafinvigconf")
    , @NamedQuery(name = "CauConfiguracion.findByIdConf", query = "SELECT c FROM CauConfiguracion c WHERE c.idConf = :idConf")})
public class CauConfiguracion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Size(max = 150)
    @Column(name = "URL")
    private String url;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHAINICVIGCONF")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechainicvigconf;
    @Column(name = "FECHAFINVIGCONF")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechafinvigconf;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_CONF")
    private Integer idConf;
    @JoinColumn(name = "ID_MESASERV", referencedColumnName = "ID_MESASERV")
    @ManyToOne
    private CauMesaservicio idMesaserv;

    public CauConfiguracion() {
    }

    public CauConfiguracion(Integer idConf) {
        this.idConf = idConf;
    }

    public CauConfiguracion(Integer idConf, Date fechainicvigconf) {
        this.idConf = idConf;
        this.fechainicvigconf = fechainicvigconf;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getFechainicvigconf() {
        return fechainicvigconf;
    }

    public void setFechainicvigconf(Date fechainicvigconf) {
        this.fechainicvigconf = fechainicvigconf;
    }

    public Date getFechafinvigconf() {
        return fechafinvigconf;
    }

    public void setFechafinvigconf(Date fechafinvigconf) {
        this.fechafinvigconf = fechafinvigconf;
    }

    public Integer getIdConf() {
        return idConf;
    }

    public void setIdConf(Integer idConf) {
        this.idConf = idConf;
    }

    public CauMesaservicio getIdMesaserv() {
        return idMesaserv;
    }

    public void setIdMesaserv(CauMesaservicio idMesaserv) {
        this.idMesaserv = idMesaserv;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idConf != null ? idConf.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CauConfiguracion)) {
            return false;
        }
        CauConfiguracion other = (CauConfiguracion) object;
        if ((this.idConf == null && other.idConf != null) || (this.idConf != null && !this.idConf.equals(other.idConf))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sfp.model.CauConfiguracion[ idConf=" + idConf + " ]";
    }
    
}
