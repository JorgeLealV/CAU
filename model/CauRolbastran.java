/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfp.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author jleal
 */
@Entity
@Table(name = "CAU_ROLBASTRAN", catalog = "", schema = "CAU")
@NamedQueries({
    @NamedQuery(name = "CauRolbastran.findAll", query = "SELECT c FROM CauRolbastran c")
    , @NamedQuery(name = "CauRolbastran.findByIdRolbastran", query = "SELECT c FROM CauRolbastran c WHERE c.idRolbastran = :idRolbastran")})
public class CauRolbastran implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_ROLBASTRAN")
    private Integer idRolbastran;
    @JoinColumn(name = "ID_ROLBASE", referencedColumnName = "ID_ROLBASE")
    @ManyToOne
    private CauRolbase idRolbase;
    @JoinColumn(name = "ID_TRANS", referencedColumnName = "ID_TRANS")
    @ManyToOne
    private CauTransaccion idTrans;

    public CauRolbastran() {
    }

    public CauRolbastran(Integer idRolbastran) {
        this.idRolbastran = idRolbastran;
    }

    public Integer getIdRolbastran() {
        return idRolbastran;
    }

    public void setIdRolbastran(Integer idRolbastran) {
        this.idRolbastran = idRolbastran;
    }

    public CauRolbase getIdRolbase() {
        return idRolbase;
    }

    public void setIdRolbase(CauRolbase idRolbase) {
        this.idRolbase = idRolbase;
    }

    public CauTransaccion getIdTrans() {
        return idTrans;
    }

    public void setIdTrans(CauTransaccion idTrans) {
        this.idTrans = idTrans;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRolbastran != null ? idRolbastran.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CauRolbastran)) {
            return false;
        }
        CauRolbastran other = (CauRolbastran) object;
        if ((this.idRolbastran == null && other.idRolbastran != null) || (this.idRolbastran != null && !this.idRolbastran.equals(other.idRolbastran))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sfp.model.CauRolbastran[ idRolbastran=" + idRolbastran + " ]";
    }
    
}
