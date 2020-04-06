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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jleal
 */
@Entity
@Table(name = "CAU_TRANSACCION", catalog = "", schema = "CAU")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CauTransaccion.findAll", query = "SELECT c FROM CauTransaccion c")
    , @NamedQuery(name = "CauTransaccion.findByIdTrans", query = "SELECT c FROM CauTransaccion c WHERE c.idTrans = :idTrans")
    , @NamedQuery(name = "CauTransaccion.findByFechaviginitrans", query = "SELECT c FROM CauTransaccion c WHERE c.fechaviginitrans = :fechaviginitrans")
    , @NamedQuery(name = "CauTransaccion.findByFechavigfintrans", query = "SELECT c FROM CauTransaccion c WHERE c.fechavigfintrans = :fechavigfintrans")
    , @NamedQuery(name = "CauTransaccion.findByDesctrans", query = "SELECT c FROM CauTransaccion c WHERE c.desctrans = :desctrans")
    , @NamedQuery(name = "CauTransaccion.findByUrltrans", query = "SELECT c FROM CauTransaccion c WHERE c.urltrans = :urltrans")
    , @NamedQuery(name = "CauTransaccion.findByCodigotrans", query = "SELECT c FROM CauTransaccion c WHERE c.codigotrans = :codigotrans")})
public class CauTransaccion implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHAVIGINITRANS")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaviginitrans;
    @Size(max = 100)
    @Column(name = "DESCTRANS")
    private String desctrans;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "URLTRANS")
    private String urltrans;
    @Size(max = 20)
    @Column(name = "CODIGOTRANS")
    private String codigotrans;
    @OneToMany(mappedBy = "idTrans")
    private Collection<CauRolbastran> cauRolbastranCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_TRANS")
    private Integer idTrans;
    @Column(name = "FECHAVIGFINTRANS")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechavigfintrans;
    @OneToMany(mappedBy = "idTrans")
    private Collection<CauTranxpar> cauTranxparCollection;
    @OneToMany(mappedBy = "idTrans")
    private Collection<CauLogoper> cauLogoperCollection;
    @OneToMany(mappedBy = "idTrans")
    private Collection<CauLogopdoc> cauLogopdocCollection;
    @OneToMany(mappedBy = "idTrans")
    private Collection<CauMenu> cauMenuCollection;
    @OneToMany(mappedBy = "idTrans")
    private Collection<CauLogopexcep> cauLogopexcepCollection;
    @OneToMany(mappedBy = "idTrans")
    private Collection<CauRolxtrans> cauRolxtransCollection;

    public CauTransaccion() {
    }

    public CauTransaccion(Integer idTrans) {
        this.idTrans = idTrans;
    }

    public CauTransaccion(Integer idTrans, Date fechaviginitrans, String urltrans) {
        this.idTrans = idTrans;
        this.fechaviginitrans = fechaviginitrans;
        this.urltrans = urltrans;
    }

    public Integer getIdTrans() {
        return idTrans;
    }

    public void setIdTrans(Integer idTrans) {
        this.idTrans = idTrans;
    }


    public Date getFechavigfintrans() {
        return fechavigfintrans;
    }

    public void setFechavigfintrans(Date fechavigfintrans) {
        this.fechavigfintrans = fechavigfintrans;
    }


    @XmlTransient
    public Collection<CauTranxpar> getCauTranxparCollection() {
        return cauTranxparCollection;
    }

    public void setCauTranxparCollection(Collection<CauTranxpar> cauTranxparCollection) {
        this.cauTranxparCollection = cauTranxparCollection;
    }

    @XmlTransient
    public Collection<CauLogoper> getCauLogoperCollection() {
        return cauLogoperCollection;
    }

    public void setCauLogoperCollection(Collection<CauLogoper> cauLogoperCollection) {
        this.cauLogoperCollection = cauLogoperCollection;
    }

    @XmlTransient
    public Collection<CauLogopdoc> getCauLogopdocCollection() {
        return cauLogopdocCollection;
    }

    public void setCauLogopdocCollection(Collection<CauLogopdoc> cauLogopdocCollection) {
        this.cauLogopdocCollection = cauLogopdocCollection;
    }

    @XmlTransient
    public Collection<CauMenu> getCauMenuCollection() {
        return cauMenuCollection;
    }

    public void setCauMenuCollection(Collection<CauMenu> cauMenuCollection) {
        this.cauMenuCollection = cauMenuCollection;
    }

    @XmlTransient
    public Collection<CauLogopexcep> getCauLogopexcepCollection() {
        return cauLogopexcepCollection;
    }

    public void setCauLogopexcepCollection(Collection<CauLogopexcep> cauLogopexcepCollection) {
        this.cauLogopexcepCollection = cauLogopexcepCollection;
    }

    @XmlTransient
    public Collection<CauRolxtrans> getCauRolxtransCollection() {
        return cauRolxtransCollection;
    }

    public void setCauRolxtransCollection(Collection<CauRolxtrans> cauRolxtransCollection) {
        this.cauRolxtransCollection = cauRolxtransCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTrans != null ? idTrans.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CauTransaccion)) {
            return false;
        }
        CauTransaccion other = (CauTransaccion) object;
        if ((this.idTrans == null && other.idTrans != null) || (this.idTrans != null && !this.idTrans.equals(other.idTrans))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sfp.model.CauTransaccion[ idTrans=" + idTrans + " ]";
    }

    public Date getFechaviginitrans() {
        return fechaviginitrans;
    }

    public void setFechaviginitrans(Date fechaviginitrans) {
        this.fechaviginitrans = fechaviginitrans;
    }

    public String getDesctrans() {
        return desctrans;
    }

    public void setDesctrans(String desctrans) {
        this.desctrans = desctrans;
    }

    public String getUrltrans() {
        return urltrans;
    }

    public void setUrltrans(String urltrans) {
        this.urltrans = urltrans;
    }

    public String getCodigotrans() {
        return codigotrans;
    }

    public void setCodigotrans(String codigotrans) {
        this.codigotrans = codigotrans;
    }

    public Collection<CauRolbastran> getCauRolbastranCollection() {
        return cauRolbastranCollection;
    }

    public void setCauRolbastranCollection(Collection<CauRolbastran> cauRolbastranCollection) {
        this.cauRolbastranCollection = cauRolbastranCollection;
    }
    
}
