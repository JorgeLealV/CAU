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
@Table(name = "CAU_TABLAS", catalog = "", schema = "CAU")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CauTablas.findAll", query = "SELECT c FROM CauTablas c")
    , @NamedQuery(name = "CauTablas.findByIdTablasvalores", query = "SELECT c FROM CauTablas c WHERE c.idTablasvalores = :idTablasvalores")
    , @NamedQuery(name = "CauTablas.findByDescTabla", query = "SELECT c FROM CauTablas c WHERE c.descTabla = :descTabla")})
public class CauTablas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_TABLASVALORES")
    private Integer idTablasvalores;
    @Size(max = 100)
    @Column(name = "DESC_TABLA")
    private String descTabla;
    @OneToMany(mappedBy = "idTablasvalores")
    private Collection<CauParametros> cauParametrosCollection;

    public CauTablas() {
    }

    public CauTablas(Integer idTablasvalores) {
        this.idTablasvalores = idTablasvalores;
    }

    public Integer getIdTablasvalores() {
        return idTablasvalores;
    }

    public void setIdTablasvalores(Integer idTablasvalores) {
        this.idTablasvalores = idTablasvalores;
    }

    public String getDescTabla() {
        return descTabla;
    }

    public void setDescTabla(String descTabla) {
        this.descTabla = descTabla;
    }

    @XmlTransient
    public Collection<CauParametros> getCauParametrosCollection() {
        return cauParametrosCollection;
    }

    public void setCauParametrosCollection(Collection<CauParametros> cauParametrosCollection) {
        this.cauParametrosCollection = cauParametrosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTablasvalores != null ? idTablasvalores.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CauTablas)) {
            return false;
        }
        CauTablas other = (CauTablas) object;
        if ((this.idTablasvalores == null && other.idTablasvalores != null) || (this.idTablasvalores != null && !this.idTablasvalores.equals(other.idTablasvalores))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sfp.model.CauTablas[ idTablasvalores=" + idTablasvalores + " ]";
    }
    
}
