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
@Table(name = "CAU_CLASEOBJETO", catalog = "", schema = "CAU")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CauClaseobjeto.findAll", query = "SELECT c FROM CauClaseobjeto c")
    , @NamedQuery(name = "CauClaseobjeto.findByIdClase", query = "SELECT c FROM CauClaseobjeto c WHERE c.idClase = :idClase")
    , @NamedQuery(name = "CauClaseobjeto.findByDescripClase", query = "SELECT c FROM CauClaseobjeto c WHERE c.descripClase = :descripClase")
    , @NamedQuery(name = "CauClaseobjeto.findByFechaviginicclase", query = "SELECT c FROM CauClaseobjeto c WHERE c.fechaviginicclase = :fechaviginicclase")
    , @NamedQuery(name = "CauClaseobjeto.findByFechavigfinclase", query = "SELECT c FROM CauClaseobjeto c WHERE c.fechavigfinclase = :fechavigfinclase")})
public class CauClaseobjeto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_CLASE")
    private Integer idClase;
    @Size(max = 100)
    @Column(name = "DESCRIP_CLASE")
    private String descripClase;
    @Column(name = "FECHAVIGINICCLASE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaviginicclase;
    @Column(name = "FECHAVIGFINCLASE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechavigfinclase;
    @OneToMany(mappedBy = "idClase")
    private Collection<CauObjetosaut> cauObjetosautCollection;

    public CauClaseobjeto() {
    }

    public CauClaseobjeto(Integer idClase) {
        this.idClase = idClase;
    }

    public Integer getIdClase() {
        return idClase;
    }

    public void setIdClase(Integer idClase) {
        this.idClase = idClase;
    }

    public String getDescripClase() {
        return descripClase;
    }

    public void setDescripClase(String descripClase) {
        this.descripClase = descripClase;
    }

    public Date getFechaviginicclase() {
        return fechaviginicclase;
    }

    public void setFechaviginicclase(Date fechaviginicclase) {
        this.fechaviginicclase = fechaviginicclase;
    }

    public Date getFechavigfinclase() {
        return fechavigfinclase;
    }

    public void setFechavigfinclase(Date fechavigfinclase) {
        this.fechavigfinclase = fechavigfinclase;
    }

    @XmlTransient
    public Collection<CauObjetosaut> getCauObjetosautCollection() {
        return cauObjetosautCollection;
    }

    public void setCauObjetosautCollection(Collection<CauObjetosaut> cauObjetosautCollection) {
        this.cauObjetosautCollection = cauObjetosautCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idClase != null ? idClase.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CauClaseobjeto)) {
            return false;
        }
        CauClaseobjeto other = (CauClaseobjeto) object;
        if ((this.idClase == null && other.idClase != null) || (this.idClase != null && !this.idClase.equals(other.idClase))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sfp.model.CauClaseobjeto[ idClase=" + idClase + " ]";
    }
    
}
