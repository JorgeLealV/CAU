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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "CAU_TIPOAUTORIZA", catalog = "", schema = "CAU")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CauTipoautoriza.findAll", query = "SELECT c FROM CauTipoautoriza c")
    , @NamedQuery(name = "CauTipoautoriza.findByIdTipoaut", query = "SELECT c FROM CauTipoautoriza c WHERE c.idTipoaut = :idTipoaut")
    , @NamedQuery(name = "CauTipoautoriza.findByDescaut", query = "SELECT c FROM CauTipoautoriza c WHERE c.descaut = :descaut")
    , @NamedQuery(name = "CauTipoautoriza.findByFechaviginictaut", query = "SELECT c FROM CauTipoautoriza c WHERE c.fechaviginictaut = :fechaviginictaut")
    , @NamedQuery(name = "CauTipoautoriza.findByFechavigfintaut", query = "SELECT c FROM CauTipoautoriza c WHERE c.fechavigfintaut = :fechavigfintaut")
    , @NamedQuery(name = "CauTipoautoriza.findByDescautlarg", query = "SELECT c FROM CauTipoautoriza c WHERE c.descautlarg = :descautlarg")})
public class CauTipoautoriza implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_TIPOAUT")
    private Integer idTipoaut;
    @Size(max = 30)
    @Column(name = "DESCAUT")
    private String descaut;
    @Column(name = "FECHAVIGINICTAUT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaviginictaut;
    @Column(name = "FECHAVIGFINTAUT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechavigfintaut;
    @Size(max = 350)
    @Column(name = "DESCAUTLARG")
    private String descautlarg;
    @OneToMany(mappedBy = "idTipoaut")
    private Collection<CauObjetostipaut> cauObjetostipautCollection;
    @JoinColumn(name = "ID_TIPPARAM", referencedColumnName = "ID_TIPPARAM")
    @ManyToOne(optional = false)
    private CauTipoparametro idTipparam;
    @JoinColumn(name = "ID_VALOR", referencedColumnName = "ID_VALOR")
    @ManyToOne(optional = false)
    private CauTipovalor idValor;

    public CauTipoautoriza() {
    }

    public CauTipoautoriza(Integer idTipoaut) {
        this.idTipoaut = idTipoaut;
    }

    public Integer getIdTipoaut() {
        return idTipoaut;
    }

    public void setIdTipoaut(Integer idTipoaut) {
        this.idTipoaut = idTipoaut;
    }

    public String getDescaut() {
        return descaut;
    }

    public void setDescaut(String descaut) {
        this.descaut = descaut;
    }

    public Date getFechaviginictaut() {
        return fechaviginictaut;
    }

    public void setFechaviginictaut(Date fechaviginictaut) {
        this.fechaviginictaut = fechaviginictaut;
    }

    public Date getFechavigfintaut() {
        return fechavigfintaut;
    }

    public void setFechavigfintaut(Date fechavigfintaut) {
        this.fechavigfintaut = fechavigfintaut;
    }

    public String getDescautlarg() {
        return descautlarg;
    }

    public void setDescautlarg(String descautlarg) {
        this.descautlarg = descautlarg;
    }

    @XmlTransient
    public Collection<CauObjetostipaut> getCauObjetostipautCollection() {
        return cauObjetostipautCollection;
    }

    public void setCauObjetostipautCollection(Collection<CauObjetostipaut> cauObjetostipautCollection) {
        this.cauObjetostipautCollection = cauObjetostipautCollection;
    }

    public CauTipoparametro getIdTipparam() {
        return idTipparam;
    }

    public void setIdTipparam(CauTipoparametro idTipparam) {
        this.idTipparam = idTipparam;
    }

    public CauTipovalor getIdValor() {
        return idValor;
    }

    public void setIdValor(CauTipovalor idValor) {
        this.idValor = idValor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoaut != null ? idTipoaut.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CauTipoautoriza)) {
            return false;
        }
        CauTipoautoriza other = (CauTipoautoriza) object;
        if ((this.idTipoaut == null && other.idTipoaut != null) || (this.idTipoaut != null && !this.idTipoaut.equals(other.idTipoaut))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sfp.model.CauTipoautoriza[ idTipoaut=" + idTipoaut + " ]";
    }
    
}
