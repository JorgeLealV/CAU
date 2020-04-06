/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfp.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jleal
 */
@Entity
@Table(name = "CAU_TIPOOPER", catalog = "", schema = "CAU")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CauTipooper.findAll", query = "SELECT c FROM CauTipooper c")
    , @NamedQuery(name = "CauTipooper.findByIdOperacion", query = "SELECT c FROM CauTipooper c WHERE c.idOperacion = :idOperacion")
    , @NamedQuery(name = "CauTipooper.findByDescoperacion", query = "SELECT c FROM CauTipooper c WHERE c.descoperacion = :descoperacion")})
public class CauTipooper implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_OPERACION")
    private Integer idOperacion;
    @Size(max = 100)
    @Column(name = "DESCOPERACION")
    private String descoperacion;
    @OneToMany(mappedBy = "idOperacion")
    private Collection<CauLogoper> cauLogoperCollection;
    @OneToMany(mappedBy = "idOperacion")
    private Collection<CauLogopexcep> cauLogopexcepCollection;

    public CauTipooper() {
    }

    public CauTipooper(Integer idOperacion) {
        this.idOperacion = idOperacion;
    }

    public Integer getIdOperacion() {
        return idOperacion;
    }

    public void setIdOperacion(Integer idOperacion) {
        this.idOperacion = idOperacion;
    }

    public String getDescoperacion() {
        return descoperacion;
    }

    public void setDescoperacion(String descoperacion) {
        this.descoperacion = descoperacion;
    }

    @XmlTransient
    public Collection<CauLogoper> getCauLogoperCollection() {
        return cauLogoperCollection;
    }

    public void setCauLogoperCollection(Collection<CauLogoper> cauLogoperCollection) {
        this.cauLogoperCollection = cauLogoperCollection;
    }

    @XmlTransient
    public Collection<CauLogopexcep> getCauLogopexcepCollection() {
        return cauLogopexcepCollection;
    }

    public void setCauLogopexcepCollection(Collection<CauLogopexcep> cauLogopexcepCollection) {
        this.cauLogopexcepCollection = cauLogopexcepCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOperacion != null ? idOperacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CauTipooper)) {
            return false;
        }
        CauTipooper other = (CauTipooper) object;
        if ((this.idOperacion == null && other.idOperacion != null) || (this.idOperacion != null && !this.idOperacion.equals(other.idOperacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sfp.model.CauTipooper[ idOperacion=" + idOperacion + " ]";
    }
    
}
