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
@Table(name = "CAU_SOCIEDAD", catalog = "", schema = "CAU")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CauSociedad.findAll", query = "SELECT c FROM CauSociedad c")
    , @NamedQuery(name = "CauSociedad.findByIdSociedad", query = "SELECT c FROM CauSociedad c WHERE c.idSociedad = :idSociedad")
    , @NamedQuery(name = "CauSociedad.findByDescSociedad", query = "SELECT c FROM CauSociedad c WHERE c.descSociedad = :descSociedad")})
public class CauSociedad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_SOCIEDAD")
    private Integer idSociedad;
    @Size(max = 100)
    @Column(name = "DESC_SOCIEDAD")
    private String descSociedad;
    @OneToMany(mappedBy = "idSociedad")
    private Collection<CauUnidades> cauUnidadesCollection;

    public CauSociedad() {
    }

    public CauSociedad(Integer idSociedad) {
        this.idSociedad = idSociedad;
    }

    public Integer getIdSociedad() {
        return idSociedad;
    }

    public void setIdSociedad(Integer idSociedad) {
        this.idSociedad = idSociedad;
    }

    public String getDescSociedad() {
        return descSociedad;
    }

    public void setDescSociedad(String descSociedad) {
        this.descSociedad = descSociedad;
    }

    @XmlTransient
    public Collection<CauUnidades> getCauUnidadesCollection() {
        return cauUnidadesCollection;
    }

    public void setCauUnidadesCollection(Collection<CauUnidades> cauUnidadesCollection) {
        this.cauUnidadesCollection = cauUnidadesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSociedad != null ? idSociedad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CauSociedad)) {
            return false;
        }
        CauSociedad other = (CauSociedad) object;
        if ((this.idSociedad == null && other.idSociedad != null) || (this.idSociedad != null && !this.idSociedad.equals(other.idSociedad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sfp.model.CauSociedad[ idSociedad=" + idSociedad + " ]";
    }
    
}
