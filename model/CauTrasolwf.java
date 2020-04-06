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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jleal
 */
@Entity
@Table(name = "CAU_TRASOLWF", catalog = "", schema = "CAU")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CauTrasolwf.findAll", query = "SELECT c FROM CauTrasolwf c")
    , @NamedQuery(name = "CauTrasolwf.findByIdTrasoltec", query = "SELECT c FROM CauTrasolwf c WHERE c.idTrasoltec = :idTrasoltec")
    , @NamedQuery(name = "CauTrasolwf.findByFechaconc", query = "SELECT c FROM CauTrasolwf c WHERE c.fechaconc = :fechaconc")})
public class CauTrasolwf implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_TRASOLTEC")
    private Integer idTrasoltec;
    @Column(name = "FECHACONC")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaconc;
    @OneToMany(mappedBy = "idTrasoltec")
    private Collection<CauTrasoldetwf> cauTrasoldetwfCollection;
    @JoinColumn(name = "ID_ESTATWF", referencedColumnName = "ID_ESTATWF")
    @ManyToOne
    private CauEsttwf idEstatwf;
    @JoinColumn(name = "ID_SOLTRABPADRE", referencedColumnName = "ID_SOLTRAB")
    @ManyToOne
    private CauSolicitudtrabajos idSoltrabpadre;
    @JoinColumn(name = "ID_WORKFLOW", referencedColumnName = "ID_WORKFLOW")
    @ManyToOne
    private CauWorkflow idWorkflow;

    public CauTrasolwf() {
    }

    public CauTrasolwf(Integer idTrasoltec) {
        this.idTrasoltec = idTrasoltec;
    }

    public Integer getIdTrasoltec() {
        return idTrasoltec;
    }

    public void setIdTrasoltec(Integer idTrasoltec) {
        this.idTrasoltec = idTrasoltec;
    }

    public Date getFechaconc() {
        return fechaconc;
    }

    public void setFechaconc(Date fechaconc) {
        this.fechaconc = fechaconc;
    }

    @XmlTransient
    public Collection<CauTrasoldetwf> getCauTrasoldetwfCollection() {
        return cauTrasoldetwfCollection;
    }

    public void setCauTrasoldetwfCollection(Collection<CauTrasoldetwf> cauTrasoldetwfCollection) {
        this.cauTrasoldetwfCollection = cauTrasoldetwfCollection;
    }

    public CauEsttwf getIdEstatwf() {
        return idEstatwf;
    }

    public void setIdEstatwf(CauEsttwf idEstatwf) {
        this.idEstatwf = idEstatwf;
    }

    public CauSolicitudtrabajos getIdSoltrabpadre() {
        return idSoltrabpadre;
    }

    public void setIdSoltrabpadre(CauSolicitudtrabajos idSoltrabpadre) {
        this.idSoltrabpadre = idSoltrabpadre;
    }

    public CauWorkflow getIdWorkflow() {
        return idWorkflow;
    }

    public void setIdWorkflow(CauWorkflow idWorkflow) {
        this.idWorkflow = idWorkflow;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTrasoltec != null ? idTrasoltec.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CauTrasolwf)) {
            return false;
        }
        CauTrasolwf other = (CauTrasolwf) object;
        if ((this.idTrasoltec == null && other.idTrasoltec != null) || (this.idTrasoltec != null && !this.idTrasoltec.equals(other.idTrasoltec))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sfp.model.CauTrasolwf[ idTrasoltec=" + idTrasoltec + " ]";
    }
    
}
