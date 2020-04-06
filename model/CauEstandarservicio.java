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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jleal
 */
@Entity
@Table(name = "CAU_ESTANDARSERVICIO", catalog = "", schema = "CAU")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CauEstandarservicio.findAll", query = "SELECT c FROM CauEstandarservicio c")
    , @NamedQuery(name = "CauEstandarservicio.findByIdEstserv", query = "SELECT c FROM CauEstandarservicio c WHERE c.idEstserv = :idEstserv")
    , @NamedQuery(name = "CauEstandarservicio.findByMinutosnormal", query = "SELECT c FROM CauEstandarservicio c WHERE c.minutosnormal = :minutosnormal")
    , @NamedQuery(name = "CauEstandarservicio.findByMinutosvip", query = "SELECT c FROM CauEstandarservicio c WHERE c.minutosvip = :minutosvip")
    , @NamedQuery(name = "CauEstandarservicio.findByFechainicestserv", query = "SELECT c FROM CauEstandarservicio c WHERE c.fechainicestserv = :fechainicestserv")
    , @NamedQuery(name = "CauEstandarservicio.findByFechafinestserv", query = "SELECT c FROM CauEstandarservicio c WHERE c.fechafinestserv = :fechafinestserv")
    , @NamedQuery(name = "CauEstandarservicio.findByDefase1", query = "SELECT c FROM CauEstandarservicio c WHERE c.defase1 = :defase1")
    , @NamedQuery(name = "CauEstandarservicio.findByDefase2", query = "SELECT c FROM CauEstandarservicio c WHERE c.defase2 = :defase2")
    , @NamedQuery(name = "CauEstandarservicio.findByDefase3", query = "SELECT c FROM CauEstandarservicio c WHERE c.defase3 = :defase3")
    , @NamedQuery(name = "CauEstandarservicio.findByDefase4", query = "SELECT c FROM CauEstandarservicio c WHERE c.defase4 = :defase4")})
public class CauEstandarservicio implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHAINICESTSERV")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechainicestserv;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_ESTSERV")
    private Integer idEstserv;
    @Column(name = "MINUTOSNORMAL")
    private Integer minutosnormal;
    @Column(name = "MINUTOSVIP")
    private Integer minutosvip;
    @Column(name = "FECHAFINESTSERV")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechafinestserv;
    @Column(name = "DEFASE1")
    private Integer defase1;
    @Column(name = "DEFASE2")
    private Integer defase2;
    @Column(name = "DEFASE3")
    private Integer defase3;
    @Column(name = "DEFASE4")
    private Integer defase4;
    @OneToMany(mappedBy = "idEstserv")
    private Collection<CauTipotrabajo> cauTipotrabajoCollection;

    public CauEstandarservicio() {
    }

    public CauEstandarservicio(Integer idEstserv) {
        this.idEstserv = idEstserv;
    }

    public CauEstandarservicio(Integer idEstserv, Date fechainicestserv) {
        this.idEstserv = idEstserv;
        this.fechainicestserv = fechainicestserv;
    }

    public Integer getIdEstserv() {
        return idEstserv;
    }

    public void setIdEstserv(Integer idEstserv) {
        this.idEstserv = idEstserv;
    }

    public Integer getMinutosnormal() {
        return minutosnormal;
    }

    public void setMinutosnormal(Integer minutosnormal) {
        this.minutosnormal = minutosnormal;
    }

    public Integer getMinutosvip() {
        return minutosvip;
    }

    public void setMinutosvip(Integer minutosvip) {
        this.minutosvip = minutosvip;
    }


    public Date getFechafinestserv() {
        return fechafinestserv;
    }

    public void setFechafinestserv(Date fechafinestserv) {
        this.fechafinestserv = fechafinestserv;
    }

    public Integer getDefase1() {
        return defase1;
    }

    public void setDefase1(Integer defase1) {
        this.defase1 = defase1;
    }

    public Integer getDefase2() {
        return defase2;
    }

    public void setDefase2(Integer defase2) {
        this.defase2 = defase2;
    }

    public Integer getDefase3() {
        return defase3;
    }

    public void setDefase3(Integer defase3) {
        this.defase3 = defase3;
    }

    public Integer getDefase4() {
        return defase4;
    }

    public void setDefase4(Integer defase4) {
        this.defase4 = defase4;
    }

    @XmlTransient
    public Collection<CauTipotrabajo> getCauTipotrabajoCollection() {
        return cauTipotrabajoCollection;
    }

    public void setCauTipotrabajoCollection(Collection<CauTipotrabajo> cauTipotrabajoCollection) {
        this.cauTipotrabajoCollection = cauTipotrabajoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstserv != null ? idEstserv.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CauEstandarservicio)) {
            return false;
        }
        CauEstandarservicio other = (CauEstandarservicio) object;
        if ((this.idEstserv == null && other.idEstserv != null) || (this.idEstserv != null && !this.idEstserv.equals(other.idEstserv))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sfp.model.CauEstandarservicio[ idEstserv=" + idEstserv + " ]";
    }

    public Date getFechainicestserv() {
        return fechainicestserv;
    }

    public void setFechainicestserv(Date fechainicestserv) {
        this.fechainicestserv = fechainicestserv;
    }
    
}
