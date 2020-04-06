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
@Table(name = "CAU_TIPOMENU", catalog = "", schema = "CAU")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CauTipomenu.findAll", query = "SELECT c FROM CauTipomenu c")
    , @NamedQuery(name = "CauTipomenu.findByIdTipmenu", query = "SELECT c FROM CauTipomenu c WHERE c.idTipmenu = :idTipmenu")
    , @NamedQuery(name = "CauTipomenu.findByDesctipomenu", query = "SELECT c FROM CauTipomenu c WHERE c.desctipomenu = :desctipomenu")})
public class CauTipomenu implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_TIPMENU")
    private Integer idTipmenu;
    @Size(max = 100)
    @Column(name = "DESCTIPOMENU")
    private String desctipomenu;
    @OneToMany(mappedBy = "idTipmenu")
    private Collection<CauMenu> cauMenuCollection;

    public CauTipomenu() {
    }

    public CauTipomenu(Integer idTipmenu) {
        this.idTipmenu = idTipmenu;
    }

    public Integer getIdTipmenu() {
        return idTipmenu;
    }

    public void setIdTipmenu(Integer idTipmenu) {
        this.idTipmenu = idTipmenu;
    }

    public String getDesctipomenu() {
        return desctipomenu;
    }

    public void setDesctipomenu(String desctipomenu) {
        this.desctipomenu = desctipomenu;
    }

    @XmlTransient
    public Collection<CauMenu> getCauMenuCollection() {
        return cauMenuCollection;
    }

    public void setCauMenuCollection(Collection<CauMenu> cauMenuCollection) {
        this.cauMenuCollection = cauMenuCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipmenu != null ? idTipmenu.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CauTipomenu)) {
            return false;
        }
        CauTipomenu other = (CauTipomenu) object;
        if ((this.idTipmenu == null && other.idTipmenu != null) || (this.idTipmenu != null && !this.idTipmenu.equals(other.idTipmenu))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sfp.model.CauTipomenu[ idTipmenu=" + idTipmenu + " ]";
    }
    
}
