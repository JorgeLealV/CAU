/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfp.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author jleal
 */
@Embeddable
public class CauTectipotrabPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_TRABAJO")
    private int idTrabajo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_USUARIO")
    private int idUsuario;

    public CauTectipotrabPK() {
    }

    public CauTectipotrabPK(int idTrabajo, int idUsuario) {
        this.idTrabajo = idTrabajo;
        this.idUsuario = idUsuario;
    }

    public int getIdTrabajo() {
        return idTrabajo;
    }

    public void setIdTrabajo(int idTrabajo) {
        this.idTrabajo = idTrabajo;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idTrabajo;
        hash += (int) idUsuario;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CauTectipotrabPK)) {
            return false;
        }
        CauTectipotrabPK other = (CauTectipotrabPK) object;
        if (this.idTrabajo != other.idTrabajo) {
            return false;
        }
        if (this.idUsuario != other.idUsuario) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sfp.model.CauTectipotrabPK[ idTrabajo=" + idTrabajo + ", idUsuario=" + idUsuario + " ]";
    }
    
}
