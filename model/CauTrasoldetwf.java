/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfp.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jleal
 */
@Entity
@Table(name = "CAU_TRASOLDETWF", catalog = "", schema = "CAU")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CauTrasoldetwf.findAll", query = "SELECT c FROM CauTrasoldetwf c")
    , @NamedQuery(name = "CauTrasoldetwf.findByIdTrasoldet", query = "SELECT c FROM CauTrasoldetwf c WHERE c.idTrasoldet = :idTrasoldet")})
public class CauTrasoldetwf implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_TRASOLDET")
    private Integer idTrasoldet;
    @JoinColumn(name = "ID_ESTATWF", referencedColumnName = "ID_ESTATWF")
    @ManyToOne
    private CauEsttwf idEstatwf;
    @JoinColumn(name = "ID_REGWF", referencedColumnName = "ID_REGWF")
    @ManyToOne
    private CauReglaswf idRegwf;
    @JoinColumn(name = "ID_SOLTRAB", referencedColumnName = "ID_SOLTRAB")
    @ManyToOne
    private CauSolicitudtrabajos idSoltrab;
    @JoinColumn(name = "ID_TRASOLTEC", referencedColumnName = "ID_TRASOLTEC")
    @ManyToOne
    private CauTrasolwf idTrasoltec;

    public CauTrasoldetwf() {
    }

    public CauTrasoldetwf(Integer idTrasoldet) {
        this.idTrasoldet = idTrasoldet;
    }

    public Integer getIdTrasoldet() {
        return idTrasoldet;
    }

    public void setIdTrasoldet(Integer idTrasoldet) {
        this.idTrasoldet = idTrasoldet;
    }

    public CauEsttwf getIdEstatwf() {
        return idEstatwf;
    }

    public void setIdEstatwf(CauEsttwf idEstatwf) {
        this.idEstatwf = idEstatwf;
    }

    public CauReglaswf getIdRegwf() {
        return idRegwf;
    }

    public void setIdRegwf(CauReglaswf idRegwf) {
        this.idRegwf = idRegwf;
    }

    public CauSolicitudtrabajos getIdSoltrab() {
        return idSoltrab;
    }

    public void setIdSoltrab(CauSolicitudtrabajos idSoltrab) {
        this.idSoltrab = idSoltrab;
    }

    public CauTrasolwf getIdTrasoltec() {
        return idTrasoltec;
    }

    public void setIdTrasoltec(CauTrasolwf idTrasoltec) {
        this.idTrasoltec = idTrasoltec;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTrasoldet != null ? idTrasoldet.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CauTrasoldetwf)) {
            return false;
        }
        CauTrasoldetwf other = (CauTrasoldetwf) object;
        if ((this.idTrasoldet == null && other.idTrasoldet != null) || (this.idTrasoldet != null && !this.idTrasoldet.equals(other.idTrasoldet))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sfp.model.CauTrasoldetwf[ idTrasoldet=" + idTrasoldet + " ]";
    }
    
}
