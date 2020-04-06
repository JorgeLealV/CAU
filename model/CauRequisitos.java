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
@Table(name = "CAU_REQUISITOS", catalog = "", schema = "CAU")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CauRequisitos.findAll", query = "SELECT c FROM CauRequisitos c")
    , @NamedQuery(name = "CauRequisitos.findByIdReq", query = "SELECT c FROM CauRequisitos c WHERE c.idReq = :idReq")
    , @NamedQuery(name = "CauRequisitos.findByDescripcion", query = "SELECT c FROM CauRequisitos c WHERE c.descripcion = :descripcion")})
public class CauRequisitos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_REQ")
    private Integer idReq;
    @Size(max = 150)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @OneToMany(mappedBy = "idReq")
    private Collection<CauReqtiptrab> cauReqtiptrabCollection;

    public CauRequisitos() {
    }

    public CauRequisitos(Integer idReq) {
        this.idReq = idReq;
    }

    public Integer getIdReq() {
        return idReq;
    }

    public void setIdReq(Integer idReq) {
        this.idReq = idReq;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public Collection<CauReqtiptrab> getCauReqtiptrabCollection() {
        return cauReqtiptrabCollection;
    }

    public void setCauReqtiptrabCollection(Collection<CauReqtiptrab> cauReqtiptrabCollection) {
        this.cauReqtiptrabCollection = cauReqtiptrabCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idReq != null ? idReq.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CauRequisitos)) {
            return false;
        }
        CauRequisitos other = (CauRequisitos) object;
        if ((this.idReq == null && other.idReq != null) || (this.idReq != null && !this.idReq.equals(other.idReq))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sfp.model.CauRequisitos[ idReq=" + idReq + " ]";
    }
    
}
