/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfp.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jleal
 */
@Entity
@Table(name = "CAU_ROLXUSUARIO", catalog = "", schema = "CAU")
@TableGenerator(name="Rolxu_Gen", table = "CAU_IDGEN", pkColumnName = "GEN_NAME", 
                  valueColumnName = "GEN_VAL", pkColumnValue = "Rolxu_Gen", allocationSize=1)

@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CauRolxusuario.findAll", query = "SELECT c FROM CauRolxusuario c")
    , @NamedQuery(name = "CauRolxusuario.findByFechaasig", query = "SELECT c FROM CauRolxusuario c WHERE c.fechaasig = :fechaasig")
    , @NamedQuery(name = "CauRolxusuario.findByIdRolusuario", query = "SELECT c FROM CauRolxusuario c WHERE c.idRolusuario = :idRolusuario")})
public class CauRolxusuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "FECHAASIG")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaasig;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator="Rolxu_Gen")
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_ROLUSUARIO")
    private Integer idRolusuario;
    @JoinColumn(name = "ID_ROL", referencedColumnName = "ID_ROL")
    @ManyToOne
    private CauRol idRol;
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO")
    @ManyToOne
    private CauUsuarios idUsuario;

    public CauRolxusuario() {
    }

    public CauRolxusuario(Integer idRolusuario) {
        this.idRolusuario = idRolusuario;
    }

    public Date getFechaasig() {
        return fechaasig;
    }

    public void setFechaasig(Date fechaasig) {
        this.fechaasig = fechaasig;
    }

    public Integer getIdRolusuario() {
        return idRolusuario;
    }

    public void setIdRolusuario(Integer idRolusuario) {
        this.idRolusuario = idRolusuario;
    }

    public CauRol getIdRol() {
        return idRol;
    }

    public void setIdRol(CauRol idRol) {
        this.idRol = idRol;
    }

    public CauUsuarios getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(CauUsuarios idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRolusuario != null ? idRolusuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CauRolxusuario)) {
            return false;
        }
        CauRolxusuario other = (CauRolxusuario) object;
        if ((this.idRolusuario == null && other.idRolusuario != null) || (this.idRolusuario != null && !this.idRolusuario.equals(other.idRolusuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sfp.model.CauRolxusuario[ idRolusuario=" + idRolusuario + " ]";
    }
    
}
