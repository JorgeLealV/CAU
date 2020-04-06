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
@Table(name = "CAU_TIPODOC", catalog = "", schema = "CAU")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CauTipodoc.findAll", query = "SELECT c FROM CauTipodoc c")
    , @NamedQuery(name = "CauTipodoc.findByIdTipdoc", query = "SELECT c FROM CauTipodoc c WHERE c.idTipdoc = :idTipdoc")
    , @NamedQuery(name = "CauTipodoc.findByDescTipdoc", query = "SELECT c FROM CauTipodoc c WHERE c.descTipdoc = :descTipdoc")})
public class CauTipodoc implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_TIPDOC")
    private Integer idTipdoc;
    @Size(max = 120)
    @Column(name = "DESC_TIPDOC")
    private String descTipdoc;
    @OneToMany(mappedBy = "idTipdoc")
    private Collection<CauLogopdoc> cauLogopdocCollection;

    public CauTipodoc() {
    }

    public CauTipodoc(Integer idTipdoc) {
        this.idTipdoc = idTipdoc;
    }

    public Integer getIdTipdoc() {
        return idTipdoc;
    }

    public void setIdTipdoc(Integer idTipdoc) {
        this.idTipdoc = idTipdoc;
    }

    public String getDescTipdoc() {
        return descTipdoc;
    }

    public void setDescTipdoc(String descTipdoc) {
        this.descTipdoc = descTipdoc;
    }

    @XmlTransient
    public Collection<CauLogopdoc> getCauLogopdocCollection() {
        return cauLogopdocCollection;
    }

    public void setCauLogopdocCollection(Collection<CauLogopdoc> cauLogopdocCollection) {
        this.cauLogopdocCollection = cauLogopdocCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipdoc != null ? idTipdoc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CauTipodoc)) {
            return false;
        }
        CauTipodoc other = (CauTipodoc) object;
        if ((this.idTipdoc == null && other.idTipdoc != null) || (this.idTipdoc != null && !this.idTipdoc.equals(other.idTipdoc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sfp.model.CauTipodoc[ idTipdoc=" + idTipdoc + " ]";
    }
    
}
