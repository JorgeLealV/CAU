/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfp.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "CAU_TIPOPARAMETRO", catalog = "", schema = "CAU")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CauTipoparametro.findAll", query = "SELECT c FROM CauTipoparametro c")
    , @NamedQuery(name = "CauTipoparametro.findByIdTipparam", query = "SELECT c FROM CauTipoparametro c WHERE c.idTipparam = :idTipparam")
    , @NamedQuery(name = "CauTipoparametro.findByDescparametro", query = "SELECT c FROM CauTipoparametro c WHERE c.descparametro = :descparametro")})
public class CauTipoparametro implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_TIPPARAM")
    private Integer idTipparam;
    @Size(max = 100)
    @Column(name = "DESCPARAMETRO")
    private String descparametro;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipparam")
    private Collection<CauTipoautoriza> cauTipoautorizaCollection;

    public CauTipoparametro() {
    }

    public CauTipoparametro(Integer idTipparam) {
        this.idTipparam = idTipparam;
    }

    public Integer getIdTipparam() {
        return idTipparam;
    }

    public void setIdTipparam(Integer idTipparam) {
        this.idTipparam = idTipparam;
    }

    public String getDescparametro() {
        return descparametro;
    }

    public void setDescparametro(String descparametro) {
        this.descparametro = descparametro;
    }

    @XmlTransient
    public Collection<CauTipoautoriza> getCauTipoautorizaCollection() {
        return cauTipoautorizaCollection;
    }

    public void setCauTipoautorizaCollection(Collection<CauTipoautoriza> cauTipoautorizaCollection) {
        this.cauTipoautorizaCollection = cauTipoautorizaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipparam != null ? idTipparam.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CauTipoparametro)) {
            return false;
        }
        CauTipoparametro other = (CauTipoparametro) object;
        if ((this.idTipparam == null && other.idTipparam != null) || (this.idTipparam != null && !this.idTipparam.equals(other.idTipparam))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sfp.model.CauTipoparametro[ idTipparam=" + idTipparam + " ]";
    }
    
}
