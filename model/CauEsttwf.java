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
@Table(name = "CAU_ESTTWF", catalog = "", schema = "CAU")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CauEsttwf.findAll", query = "SELECT c FROM CauEsttwf c")
    , @NamedQuery(name = "CauEsttwf.findByIdEstatwf", query = "SELECT c FROM CauEsttwf c WHERE c.idEstatwf = :idEstatwf")
    , @NamedQuery(name = "CauEsttwf.findByDescripcion", query = "SELECT c FROM CauEsttwf c WHERE c.descripcion = :descripcion")})
public class CauEsttwf implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_ESTATWF")
    private Integer idEstatwf;
    @Size(max = 100)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @OneToMany(mappedBy = "idEstatwf")
    private Collection<CauReglaswf> cauReglaswfCollection;
    @OneToMany(mappedBy = "idEstatwf")
    private Collection<CauTrasoldetwf> cauTrasoldetwfCollection;
    @OneToMany(mappedBy = "idEstatwf")
    private Collection<CauTrasolwf> cauTrasolwfCollection;

    public CauEsttwf() {
    }

    public CauEsttwf(Integer idEstatwf) {
        this.idEstatwf = idEstatwf;
    }

    public Integer getIdEstatwf() {
        return idEstatwf;
    }

    public void setIdEstatwf(Integer idEstatwf) {
        this.idEstatwf = idEstatwf;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public Collection<CauReglaswf> getCauReglaswfCollection() {
        return cauReglaswfCollection;
    }

    public void setCauReglaswfCollection(Collection<CauReglaswf> cauReglaswfCollection) {
        this.cauReglaswfCollection = cauReglaswfCollection;
    }

    @XmlTransient
    public Collection<CauTrasoldetwf> getCauTrasoldetwfCollection() {
        return cauTrasoldetwfCollection;
    }

    public void setCauTrasoldetwfCollection(Collection<CauTrasoldetwf> cauTrasoldetwfCollection) {
        this.cauTrasoldetwfCollection = cauTrasoldetwfCollection;
    }

    @XmlTransient
    public Collection<CauTrasolwf> getCauTrasolwfCollection() {
        return cauTrasolwfCollection;
    }

    public void setCauTrasolwfCollection(Collection<CauTrasolwf> cauTrasolwfCollection) {
        this.cauTrasolwfCollection = cauTrasolwfCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstatwf != null ? idEstatwf.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CauEsttwf)) {
            return false;
        }
        CauEsttwf other = (CauEsttwf) object;
        if ((this.idEstatwf == null && other.idEstatwf != null) || (this.idEstatwf != null && !this.idEstatwf.equals(other.idEstatwf))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sfp.model.CauEsttwf[ idEstatwf=" + idEstatwf + " ]";
    }
    
}
