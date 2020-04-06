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
@Table(name = "CAU_FECHAS", catalog = "", schema = "CAU")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CauFechas.findAll", query = "SELECT c FROM CauFechas c")
    , @NamedQuery(name = "CauFechas.findByFechainic", query = "SELECT c FROM CauFechas c WHERE c.fechainic = :fechainic")
    , @NamedQuery(name = "CauFechas.findByFechafin", query = "SELECT c FROM CauFechas c WHERE c.fechafin = :fechafin")
    , @NamedQuery(name = "CauFechas.findByIdFechas", query = "SELECT c FROM CauFechas c WHERE c.idFechas = :idFechas")})
public class CauFechas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "FECHAINIC")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechainic;
    @Column(name = "FECHAFIN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechafin;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_FECHAS")
    private Integer idFechas;
    @JoinColumn(name = "ID_PERIODO", referencedColumnName = "ID_PERIODO")
    @ManyToOne
    private CauPeriodo idPeriodo;

    public CauFechas() {
    }

    public CauFechas(Integer idFechas) {
        this.idFechas = idFechas;
    }

    public Date getFechainic() {
        return fechainic;
    }

    public void setFechainic(Date fechainic) {
        this.fechainic = fechainic;
    }

    public Date getFechafin() {
        return fechafin;
    }

    public void setFechafin(Date fechafin) {
        this.fechafin = fechafin;
    }

    public Integer getIdFechas() {
        return idFechas;
    }

    public void setIdFechas(Integer idFechas) {
        this.idFechas = idFechas;
    }

    public CauPeriodo getIdPeriodo() {
        return idPeriodo;
    }

    public void setIdPeriodo(CauPeriodo idPeriodo) {
        this.idPeriodo = idPeriodo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFechas != null ? idFechas.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CauFechas)) {
            return false;
        }
        CauFechas other = (CauFechas) object;
        if ((this.idFechas == null && other.idFechas != null) || (this.idFechas != null && !this.idFechas.equals(other.idFechas))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sfp.model.CauFechas[ idFechas=" + idFechas + " ]";
    }
    
}
