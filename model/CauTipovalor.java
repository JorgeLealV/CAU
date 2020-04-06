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
@Table(name = "CAU_TIPOVALOR", catalog = "", schema = "CAU")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CauTipovalor.findAll", query = "SELECT c FROM CauTipovalor c")
    , @NamedQuery(name = "CauTipovalor.findByIdValor", query = "SELECT c FROM CauTipovalor c WHERE c.idValor = :idValor")
    , @NamedQuery(name = "CauTipovalor.findByDescvalor", query = "SELECT c FROM CauTipovalor c WHERE c.descvalor = :descvalor")})
public class CauTipovalor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_VALOR")
    private Integer idValor;
    @Size(max = 30)
    @Column(name = "DESCVALOR")
    private String descvalor;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idValor")
    private Collection<CauTipoautoriza> cauTipoautorizaCollection;

    public CauTipovalor() {
    }

    public CauTipovalor(Integer idValor) {
        this.idValor = idValor;
    }

    public Integer getIdValor() {
        return idValor;
    }

    public void setIdValor(Integer idValor) {
        this.idValor = idValor;
    }

    public String getDescvalor() {
        return descvalor;
    }

    public void setDescvalor(String descvalor) {
        this.descvalor = descvalor;
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
        hash += (idValor != null ? idValor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CauTipovalor)) {
            return false;
        }
        CauTipovalor other = (CauTipovalor) object;
        if ((this.idValor == null && other.idValor != null) || (this.idValor != null && !this.idValor.equals(other.idValor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sfp.model.CauTipovalor[ idValor=" + idValor + " ]";
    }
    
}
