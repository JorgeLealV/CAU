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
@Table(name = "CAU_TIPOURG", catalog = "", schema = "CAU")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CauTipourg.findAll", query = "SELECT c FROM CauTipourg c")
    , @NamedQuery(name = "CauTipourg.findByIdTipourg", query = "SELECT c FROM CauTipourg c WHERE c.idTipourg = :idTipourg")
    , @NamedQuery(name = "CauTipourg.findByDescripcion", query = "SELECT c FROM CauTipourg c WHERE c.descripcion = :descripcion")})
public class CauTipourg implements Serializable {

    @Size(max = 150)
    @Column(name = "DESCRIPCION")
    private String descripcion;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_TIPOURG")
    private Integer idTipourg;
    @OneToMany(mappedBy = "idTipourg")
    private Collection<CauTipotrabajo> cauTipotrabajoCollection;

    public CauTipourg() {
    }

    public CauTipourg(Integer idTipourg) {
        this.idTipourg = idTipourg;
    }

    public Integer getIdTipourg() {
        return idTipourg;
    }

    public void setIdTipourg(Integer idTipourg) {
        this.idTipourg = idTipourg;
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
        hash += (idTipourg != null ? idTipourg.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CauTipourg)) {
            return false;
        }
        CauTipourg other = (CauTipourg) object;
        if ((this.idTipourg == null && other.idTipourg != null) || (this.idTipourg != null && !this.idTipourg.equals(other.idTipourg))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sfp.model.CauTipourg[ idTipourg=" + idTipourg + " ]";
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
}
