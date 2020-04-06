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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jleal
 */
@Entity
@Table(name = "CAU_PARAMETROS", catalog = "", schema = "CAU")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CauParametros.findAll", query = "SELECT c FROM CauParametros c")
    , @NamedQuery(name = "CauParametros.findByIdParam", query = "SELECT c FROM CauParametros c WHERE c.idParam = :idParam")
    , @NamedQuery(name = "CauParametros.findByValor", query = "SELECT c FROM CauParametros c WHERE c.valor = :valor")
    , @NamedQuery(name = "CauParametros.findBySecuencia", query = "SELECT c FROM CauParametros c WHERE c.secuencia = :secuencia")})
public class CauParametros implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PARAM")
    private Integer idParam;
    @Size(max = 250)
    @Column(name = "VALOR")
    private String valor;
    @Column(name = "SECUENCIA")
    private Integer secuencia;
    @OneToMany(mappedBy = "idParam")
    private Collection<CauTranxpar> cauTranxparCollection;
    @JoinColumn(name = "ID_OBJTIPAUT", referencedColumnName = "ID_OBJTIPAUT")
    @ManyToOne
    private CauObjetostipaut idObjtipaut;
    @JoinColumn(name = "ID_TABLASVALORES", referencedColumnName = "ID_TABLASVALORES")
    @ManyToOne
    private CauTablas idTablasvalores;
    @OneToMany(mappedBy = "idParam")
    private Collection<CauRolxtranxpar> cauRolxtranxparCollection;

    public CauParametros() {
    }

    public CauParametros(Integer idParam) {
        this.idParam = idParam;
    }

    public Integer getIdParam() {
        return idParam;
    }

    public void setIdParam(Integer idParam) {
        this.idParam = idParam;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public Integer getSecuencia() {
        return secuencia;
    }

    public void setSecuencia(Integer secuencia) {
        this.secuencia = secuencia;
    }

    @XmlTransient
    public Collection<CauTranxpar> getCauTranxparCollection() {
        return cauTranxparCollection;
    }

    public void setCauTranxparCollection(Collection<CauTranxpar> cauTranxparCollection) {
        this.cauTranxparCollection = cauTranxparCollection;
    }

    public CauObjetostipaut getIdObjtipaut() {
        return idObjtipaut;
    }

    public void setIdObjtipaut(CauObjetostipaut idObjtipaut) {
        this.idObjtipaut = idObjtipaut;
    }

    public CauTablas getIdTablasvalores() {
        return idTablasvalores;
    }

    public void setIdTablasvalores(CauTablas idTablasvalores) {
        this.idTablasvalores = idTablasvalores;
    }

    @XmlTransient
    public Collection<CauRolxtranxpar> getCauRolxtranxparCollection() {
        return cauRolxtranxparCollection;
    }

    public void setCauRolxtranxparCollection(Collection<CauRolxtranxpar> cauRolxtranxparCollection) {
        this.cauRolxtranxparCollection = cauRolxtranxparCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idParam != null ? idParam.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CauParametros)) {
            return false;
        }
        CauParametros other = (CauParametros) object;
        if ((this.idParam == null && other.idParam != null) || (this.idParam != null && !this.idParam.equals(other.idParam))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sfp.model.CauParametros[ idParam=" + idParam + " ]";
    }
    
}
