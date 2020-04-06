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
public class CauDepjefePK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_USUARIO")
    private int idUsuario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_DEPARTAMENTOS")
    private int idDepartamentos;

    public CauDepjefePK() {
    }

    public CauDepjefePK(int idUsuario, int idDepartamentos) {
        this.idUsuario = idUsuario;
        this.idDepartamentos = idDepartamentos;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdDepartamentos() {
        return idDepartamentos;
    }

    public void setIdDepartamentos(int idDepartamentos) {
        this.idDepartamentos = idDepartamentos;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idUsuario;
        hash += (int) idDepartamentos;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CauDepjefePK)) {
            return false;
        }
        CauDepjefePK other = (CauDepjefePK) object;
        if (this.idUsuario != other.idUsuario) {
            return false;
        }
        if (this.idDepartamentos != other.idDepartamentos) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sfp.model.CauDepjefePK[ idUsuario=" + idUsuario + ", idDepartamentos=" + idDepartamentos + " ]";
    }
    
}
