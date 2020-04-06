/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfp.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author jleal
 */
@Entity
@Table(name = "CAU_ROLBASE", catalog = "", schema = "CAU")
@NamedQueries({
    @NamedQuery(name = "CauRolbase.findAll", query = "SELECT c FROM CauRolbase c")
    , @NamedQuery(name = "CauRolbase.findByIdRolbase", query = "SELECT c FROM CauRolbase c WHERE c.idRolbase = :idRolbase")
    , @NamedQuery(name = "CauRolbase.findByFechainicio", query = "SELECT c FROM CauRolbase c WHERE c.fechainicio = :fechainicio")
    , @NamedQuery(name = "CauRolbase.findByFechafin", query = "SELECT c FROM CauRolbase c WHERE c.fechafin = :fechafin")
    , @NamedQuery(name = "CauRolbase.findByDescripci\u00f3n", query = "SELECT c FROM CauRolbase c WHERE c.descripci\u00f3n = :descripci\u00f3n")})
public class CauRolbase implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_ROLBASE")
    private Integer idRolbase;
    @Column(name = "FECHAINICIO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechainicio;
    @Column(name = "FECHAFIN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechafin;
    @Size(max = 100)
    @Column(name = "DESCRIPCI\u00d3N")
    private String descripción;
    @JoinColumn(name = "ID_TIPOUSUARIO", referencedColumnName = "ID_TIPOUSUARIO")
    @ManyToOne
    private CauTipodeusuario idTipousuario;
    @OneToMany(mappedBy = "idRolbase")
    private Collection<CauRolbastran> cauRolbastranCollection;

    public CauRolbase() {
    }

    public CauRolbase(Integer idRolbase) {
        this.idRolbase = idRolbase;
    }

    public Integer getIdRolbase() {
        return idRolbase;
    }

    public void setIdRolbase(Integer idRolbase) {
        this.idRolbase = idRolbase;
    }

    public Date getFechainicio() {
        return fechainicio;
    }

    public void setFechainicio(Date fechainicio) {
        this.fechainicio = fechainicio;
    }

    public Date getFechafin() {
        return fechafin;
    }

    public void setFechafin(Date fechafin) {
        this.fechafin = fechafin;
    }

    public String getDescripción() {
        return descripción;
    }

    public void setDescripción(String descripción) {
        this.descripción = descripción;
    }

    public CauTipodeusuario getIdTipousuario() {
        return idTipousuario;
    }

    public void setIdTipousuario(CauTipodeusuario idTipousuario) {
        this.idTipousuario = idTipousuario;
    }

    public Collection<CauRolbastran> getCauRolbastranCollection() {
        return cauRolbastranCollection;
    }

    public void setCauRolbastranCollection(Collection<CauRolbastran> cauRolbastranCollection) {
        this.cauRolbastranCollection = cauRolbastranCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRolbase != null ? idRolbase.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CauRolbase)) {
            return false;
        }
        CauRolbase other = (CauRolbase) object;
        if ((this.idRolbase == null && other.idRolbase != null) || (this.idRolbase != null && !this.idRolbase.equals(other.idRolbase))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sfp.model.CauRolbase[ idRolbase=" + idRolbase + " ]";
    }
    
}
