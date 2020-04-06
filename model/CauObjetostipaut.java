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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jleal
 */
@Entity
@Table(name = "CAU_OBJETOSTIPAUT", catalog = "", schema = "CAU")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CauObjetostipaut.findAll", query = "SELECT c FROM CauObjetostipaut c")
    , @NamedQuery(name = "CauObjetostipaut.findByIdObjtipaut", query = "SELECT c FROM CauObjetostipaut c WHERE c.idObjtipaut = :idObjtipaut")})
public class CauObjetostipaut implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_OBJTIPAUT")
    private Integer idObjtipaut;
    @JoinColumn(name = "ID_OBJAUT", referencedColumnName = "ID_OBJAUT")
    @ManyToOne
    private CauObjetosaut idObjaut;
    @JoinColumn(name = "ID_TIPOAUT", referencedColumnName = "ID_TIPOAUT")
    @ManyToOne
    private CauTipoautoriza idTipoaut;
    @OneToMany(mappedBy = "idObjtipaut")
    private Collection<CauParametros> cauParametrosCollection;

    public CauObjetostipaut() {
    }

    public CauObjetostipaut(Integer idObjtipaut) {
        this.idObjtipaut = idObjtipaut;
    }

    public Integer getIdObjtipaut() {
        return idObjtipaut;
    }

    public void setIdObjtipaut(Integer idObjtipaut) {
        this.idObjtipaut = idObjtipaut;
    }

    public CauObjetosaut getIdObjaut() {
        return idObjaut;
    }

    public void setIdObjaut(CauObjetosaut idObjaut) {
        this.idObjaut = idObjaut;
    }

    public CauTipoautoriza getIdTipoaut() {
        return idTipoaut;
    }

    public void setIdTipoaut(CauTipoautoriza idTipoaut) {
        this.idTipoaut = idTipoaut;
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
        hash += (idObjtipaut != null ? idObjtipaut.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CauObjetostipaut)) {
            return false;
        }
        CauObjetostipaut other = (CauObjetostipaut) object;
        if ((this.idObjtipaut == null && other.idObjtipaut != null) || (this.idObjtipaut != null && !this.idObjtipaut.equals(other.idObjtipaut))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sfp.model.CauObjetostipaut[ idObjtipaut=" + idObjtipaut + " ]";
    }
    
}
