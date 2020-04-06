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
@Table(name = "CAU_AREA", catalog = "", schema = "CAU")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CauArea.findAll", query = "SELECT c FROM CauArea c")
    , @NamedQuery(name = "CauArea.findByIdCvearea", query = "SELECT c FROM CauArea c WHERE c.idCvearea = :idCvearea")
    , @NamedQuery(name = "CauArea.findByDescripcion", query = "SELECT c FROM CauArea c WHERE c.descripcion = :descripcion")})
public class CauArea implements Serializable {

    @Size(max = 150)
    @Column(name = "DESCRIPCION")
    private String descripcion;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "ID_CVEAREA")
    private String idCvearea;
    @OneToMany(mappedBy = "idCvearea")
    private Collection<CauGrupos> cauGruposCollection;

    public CauArea() {
    }

    public CauArea(String idCvearea) {
        this.idCvearea = idCvearea;
    }

    public String getIdCvearea() {
        return idCvearea;
    }

    public void setIdCvearea(String idCvearea) {
        this.idCvearea = idCvearea;
    }


    @XmlTransient
    public Collection<CauGrupos> getCauGruposCollection() {
        return cauGruposCollection;
    }

    public void setCauGruposCollection(Collection<CauGrupos> cauGruposCollection) {
        this.cauGruposCollection = cauGruposCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCvearea != null ? idCvearea.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CauArea)) {
            return false;
        }
        CauArea other = (CauArea) object;
        if ((this.idCvearea == null && other.idCvearea != null) || (this.idCvearea != null && !this.idCvearea.equals(other.idCvearea))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sfp.model.CauArea[ idCvearea=" + idCvearea + " ]";
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
}
