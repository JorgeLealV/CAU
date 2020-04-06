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
@Table(name = "CAU_TIPOSERV", catalog = "", schema = "CAU")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CauTiposerv.findAll", query = "SELECT c FROM CauTiposerv c")
    , @NamedQuery(name = "CauTiposerv.findByIdTiposerv", query = "SELECT c FROM CauTiposerv c WHERE c.idTiposerv = :idTiposerv")
    , @NamedQuery(name = "CauTiposerv.findByDescripcion", query = "SELECT c FROM CauTiposerv c WHERE c.descripcion = :descripcion")})
public class CauTiposerv implements Serializable {

    @Size(max = 150)
    @Column(name = "DESCRIPCION")
    private String descripcion;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_TIPOSERV")
    private Integer idTiposerv;
    @OneToMany(mappedBy = "idTiposerv")
    private Collection<CauTipotrabajo> cauTipotrabajoCollection;

    public CauTiposerv() {
    }

    public CauTiposerv(Integer idTiposerv) {
        this.idTiposerv = idTiposerv;
    }

    public Integer getIdTiposerv() {
        return idTiposerv;
    }

    public void setIdTiposerv(Integer idTiposerv) {
        this.idTiposerv = idTiposerv;
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
        hash += (idTiposerv != null ? idTiposerv.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CauTiposerv)) {
            return false;
        }
        CauTiposerv other = (CauTiposerv) object;
        if ((this.idTiposerv == null && other.idTiposerv != null) || (this.idTiposerv != null && !this.idTiposerv.equals(other.idTiposerv))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sfp.model.CauTiposerv[ idTiposerv=" + idTiposerv + " ]";
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
}
