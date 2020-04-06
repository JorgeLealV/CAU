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
@Table(name = "CAU_TIPOIMP", catalog = "", schema = "CAU")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CauTipoimp.findAll", query = "SELECT c FROM CauTipoimp c")
    , @NamedQuery(name = "CauTipoimp.findByIdTipoimp", query = "SELECT c FROM CauTipoimp c WHERE c.idTipoimp = :idTipoimp")
    , @NamedQuery(name = "CauTipoimp.findByDescripcion", query = "SELECT c FROM CauTipoimp c WHERE c.descripcion = :descripcion")})
public class CauTipoimp implements Serializable {

    @Size(max = 150)
    @Column(name = "DESCRIPCION")
    private String descripcion;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_TIPOIMP")
    private Integer idTipoimp;
    @OneToMany(mappedBy = "idTipoimp")
    private Collection<CauTipotrabajo> cauTipotrabajoCollection;

    public CauTipoimp() {
    }

    public CauTipoimp(Integer idTipoimp) {
        this.idTipoimp = idTipoimp;
    }

    public Integer getIdTipoimp() {
        return idTipoimp;
    }

    public void setIdTipoimp(Integer idTipoimp) {
        this.idTipoimp = idTipoimp;
    }


    @XmlTransient
    public Collection<CauTipotrabajo> getCauTipotrabajoCollection() {
        return cauTipotrabajoCollection;
    }

    public void setCauTipotrabajoCollection(Collection<CauTipotrabajo> cauTipotrabajoCollection) {
        this.cauTipotrabajoCollection = cauTipotrabajoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoimp != null ? idTipoimp.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CauTipoimp)) {
            return false;
        }
        CauTipoimp other = (CauTipoimp) object;
        if ((this.idTipoimp == null && other.idTipoimp != null) || (this.idTipoimp != null && !this.idTipoimp.equals(other.idTipoimp))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sfp.model.CauTipoimp[ idTipoimp=" + idTipoimp + " ]";
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
}
