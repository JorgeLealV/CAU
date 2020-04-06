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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jleal
 */
@Entity
@Table(name = "CAU_OBJETOSAUT", catalog = "", schema = "CAU")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CauObjetosaut.findAll", query = "SELECT c FROM CauObjetosaut c")
    , @NamedQuery(name = "CauObjetosaut.findByIdObjaut", query = "SELECT c FROM CauObjetosaut c WHERE c.idObjaut = :idObjaut")
    , @NamedQuery(name = "CauObjetosaut.findByDescObj", query = "SELECT c FROM CauObjetosaut c WHERE c.descObj = :descObj")
    , @NamedQuery(name = "CauObjetosaut.findByFechaviginicoaut", query = "SELECT c FROM CauObjetosaut c WHERE c.fechaviginicoaut = :fechaviginicoaut")
    , @NamedQuery(name = "CauObjetosaut.findByFechavigfinoaut", query = "SELECT c FROM CauObjetosaut c WHERE c.fechavigfinoaut = :fechavigfinoaut")})
public class CauObjetosaut implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_OBJAUT")
    private Integer idObjaut;
    @Size(max = 100)
    @Column(name = "DESC_OBJ")
    private String descObj;
    @Column(name = "FECHAVIGINICOAUT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaviginicoaut;
    @Column(name = "FECHAVIGFINOAUT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechavigfinoaut;
    @OneToMany(mappedBy = "idObjaut")
    private Collection<CauObjetostipaut> cauObjetostipautCollection;
    @JoinColumn(name = "ID_CLASE", referencedColumnName = "ID_CLASE")
    @ManyToOne
    private CauClaseobjeto idClase;

    public CauObjetosaut() {
    }

    public CauObjetosaut(Integer idObjaut) {
        this.idObjaut = idObjaut;
    }

    public Integer getIdObjaut() {
        return idObjaut;
    }

    public void setIdObjaut(Integer idObjaut) {
        this.idObjaut = idObjaut;
    }

    public String getDescObj() {
        return descObj;
    }

    public void setDescObj(String descObj) {
        this.descObj = descObj;
    }

    public Date getFechaviginicoaut() {
        return fechaviginicoaut;
    }

    public void setFechaviginicoaut(Date fechaviginicoaut) {
        this.fechaviginicoaut = fechaviginicoaut;
    }

    public Date getFechavigfinoaut() {
        return fechavigfinoaut;
    }

    public void setFechavigfinoaut(Date fechavigfinoaut) {
        this.fechavigfinoaut = fechavigfinoaut;
    }

    @XmlTransient
    public Collection<CauObjetostipaut> getCauObjetostipautCollection() {
        return cauObjetostipautCollection;
    }

    public void setCauObjetostipautCollection(Collection<CauObjetostipaut> cauObjetostipautCollection) {
        this.cauObjetostipautCollection = cauObjetostipautCollection;
    }

    public CauClaseobjeto getIdClase() {
        return idClase;
    }

    public void setIdClase(CauClaseobjeto idClase) {
        this.idClase = idClase;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idObjaut != null ? idObjaut.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CauObjetosaut)) {
            return false;
        }
        CauObjetosaut other = (CauObjetosaut) object;
        if ((this.idObjaut == null && other.idObjaut != null) || (this.idObjaut != null && !this.idObjaut.equals(other.idObjaut))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sfp.model.CauObjetosaut[ idObjaut=" + idObjaut + " ]";
    }
    
}
