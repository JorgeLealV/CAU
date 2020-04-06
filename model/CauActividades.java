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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jleal
 */
@Entity
@Table(name = "CAU_ACTIVIDADES", catalog = "", schema = "CAU")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CauActividades.findAll", query = "SELECT c FROM CauActividades c")
    , @NamedQuery(name = "CauActividades.findByIdActividad", query = "SELECT c FROM CauActividades c WHERE c.idActividad = :idActividad")
    , @NamedQuery(name = "CauActividades.findByDescActividad", query = "SELECT c FROM CauActividades c WHERE c.descActividad = :descActividad")})
public class CauActividades implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_ACTIVIDAD")
    private Integer idActividad;
    @Size(max = 100)
    @Column(name = "DESC_ACTIVIDAD")
    private String descActividad;

    public CauActividades() {
    }

    public CauActividades(Integer idActividad) {
        this.idActividad = idActividad;
    }

    public Integer getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(Integer idActividad) {
        this.idActividad = idActividad;
    }

    public String getDescActividad() {
        return descActividad;
    }

    public void setDescActividad(String descActividad) {
        this.descActividad = descActividad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idActividad != null ? idActividad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CauActividades)) {
            return false;
        }
        CauActividades other = (CauActividades) object;
        if ((this.idActividad == null && other.idActividad != null) || (this.idActividad != null && !this.idActividad.equals(other.idActividad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sfp.model.CauActividades[ idActividad=" + idActividad + " ]";
    }
    
}
