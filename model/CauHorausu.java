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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jleal
 */
@Entity
@Table(name = "CAU_HORAUSU", catalog = "", schema = "CAU")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CauHorausu.findAll", query = "SELECT c FROM CauHorausu c")
    , @NamedQuery(name = "CauHorausu.findByIdHorario", query = "SELECT c FROM CauHorausu c WHERE c.idHorario = :idHorario")
    , @NamedQuery(name = "CauHorausu.findByFechaini", query = "SELECT c FROM CauHorausu c WHERE c.fechaini = :fechaini")
    , @NamedQuery(name = "CauHorausu.findByFechafin", query = "SELECT c FROM CauHorausu c WHERE c.fechafin = :fechafin")})
public class CauHorausu implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_HORARIO")
    private Integer idHorario;
    @Column(name = "FECHAINI")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaini;
    @Column(name = "FECHAFIN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechafin;
    @JoinColumn(name = "ID_PERIODO", referencedColumnName = "ID_PERIODO")
    @ManyToOne
    private CauPeriodo idPeriodo;
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO")
    @ManyToOne
    private CauUsuarios idUsuario;

    public CauHorausu() {
    }

    public CauHorausu(Integer idHorario) {
        this.idHorario = idHorario;
    }

    public Integer getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(Integer idHorario) {
        this.idHorario = idHorario;
    }

    public Date getFechaini() {
        return fechaini;
    }

    public void setFechaini(Date fechaini) {
        this.fechaini = fechaini;
    }

    public Date getFechafin() {
        return fechafin;
    }

    public void setFechafin(Date fechafin) {
        this.fechafin = fechafin;
    }

    public CauPeriodo getIdPeriodo() {
        return idPeriodo;
    }

    public void setIdPeriodo(CauPeriodo idPeriodo) {
        this.idPeriodo = idPeriodo;
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
        hash += (idHorario != null ? idHorario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CauHorausu)) {
            return false;
        }
        CauHorausu other = (CauHorausu) object;
        if ((this.idHorario == null && other.idHorario != null) || (this.idHorario != null && !this.idHorario.equals(other.idHorario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sfp.model.CauHorausu[ idHorario=" + idHorario + " ]";
    }
    
}
