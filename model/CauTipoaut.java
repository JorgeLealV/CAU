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
@Table(name = "CAU_TIPOAUT", catalog = "", schema = "CAU")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CauTipoaut.findAll", query = "SELECT c FROM CauTipoaut c")
    , @NamedQuery(name = "CauTipoaut.findByIdTipo", query = "SELECT c FROM CauTipoaut c WHERE c.idTipo = :idTipo")
    , @NamedQuery(name = "CauTipoaut.findByDescripcion", query = "SELECT c FROM CauTipoaut c WHERE c.descripcion = :descripcion")})
public class CauTipoaut implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_TIPO")
    private Integer idTipo;
    @Size(max = 150)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @OneToMany(mappedBy = "idTipo")
    private Collection<CauAuttipotrab> cauAuttipotrabCollection;

    public CauTipoaut() {
    }

    public CauTipoaut(Integer idTipo) {
        this.idTipo = idTipo;
    }

    public Integer getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(Integer idTipo) {
        this.idTipo = idTipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public Collection<CauAuttipotrab> getCauAuttipotrabCollection() {
        return cauAuttipotrabCollection;
    }

    public void setCauAuttipotrabCollection(Collection<CauAuttipotrab> cauAuttipotrabCollection) {
        this.cauAuttipotrabCollection = cauAuttipotrabCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipo != null ? idTipo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CauTipoaut)) {
            return false;
        }
        CauTipoaut other = (CauTipoaut) object;
        if ((this.idTipo == null && other.idTipo != null) || (this.idTipo != null && !this.idTipo.equals(other.idTipo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sfp.model.CauTipoaut[ idTipo=" + idTipo + " ]";
    }
    
}
