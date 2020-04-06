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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jleal
 */
@Entity
@Table(name = "CAU_PERIODO", catalog = "", schema = "CAU")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CauPeriodo.findAll", query = "SELECT c FROM CauPeriodo c")
    , @NamedQuery(name = "CauPeriodo.findByIdPeriodo", query = "SELECT c FROM CauPeriodo c WHERE c.idPeriodo = :idPeriodo")})
public class CauPeriodo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PERIODO")
    private Integer idPeriodo;
    @OneToMany(mappedBy = "idPeriodo")
    private Collection<CauFechas> cauFechasCollection;
    @JoinColumn(name = "ID_TIPODIA", referencedColumnName = "ID_TIPODIA")
    @ManyToOne
    private CauTipodia idTipodia;
    @OneToMany(mappedBy = "idPeriodo")
    private Collection<CauHorausu> cauHorausuCollection;

    public CauPeriodo() {
    }

    public CauPeriodo(Integer idPeriodo) {
        this.idPeriodo = idPeriodo;
    }

    public Integer getIdPeriodo() {
        return idPeriodo;
    }

    public void setIdPeriodo(Integer idPeriodo) {
        this.idPeriodo = idPeriodo;
    }

    @XmlTransient
    public Collection<CauFechas> getCauFechasCollection() {
        return cauFechasCollection;
    }

    public void setCauFechasCollection(Collection<CauFechas> cauFechasCollection) {
        this.cauFechasCollection = cauFechasCollection;
    }

    public CauTipodia getIdTipodia() {
        return idTipodia;
    }

    public void setIdTipodia(CauTipodia idTipodia) {
        this.idTipodia = idTipodia;
    }

    @XmlTransient
    public Collection<CauHorausu> getCauHorausuCollection() {
        return cauHorausuCollection;
    }

    public void setCauHorausuCollection(Collection<CauHorausu> cauHorausuCollection) {
        this.cauHorausuCollection = cauHorausuCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPeriodo != null ? idPeriodo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CauPeriodo)) {
            return false;
        }
        CauPeriodo other = (CauPeriodo) object;
        if ((this.idPeriodo == null && other.idPeriodo != null) || (this.idPeriodo != null && !this.idPeriodo.equals(other.idPeriodo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sfp.model.CauPeriodo[ idPeriodo=" + idPeriodo + " ]";
    }
    
}
