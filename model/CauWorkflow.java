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
@Table(name = "CAU_WORKFLOW", catalog = "", schema = "CAU")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CauWorkflow.findAll", query = "SELECT c FROM CauWorkflow c")
    , @NamedQuery(name = "CauWorkflow.findByIdWorkflow", query = "SELECT c FROM CauWorkflow c WHERE c.idWorkflow = :idWorkflow")
    , @NamedQuery(name = "CauWorkflow.findByDescripcion", query = "SELECT c FROM CauWorkflow c WHERE c.descripcion = :descripcion")
    , @NamedQuery(name = "CauWorkflow.findByFechainic", query = "SELECT c FROM CauWorkflow c WHERE c.fechainic = :fechainic")
    , @NamedQuery(name = "CauWorkflow.findByFechafin", query = "SELECT c FROM CauWorkflow c WHERE c.fechafin = :fechafin")})
public class CauWorkflow implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_WORKFLOW")
    private Integer idWorkflow;
    @Size(max = 200)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Column(name = "FECHAINIC")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechainic;
    @Column(name = "FECHAFIN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechafin;
    @OneToMany(mappedBy = "idWorkflow")
    private Collection<CauTrasolwf> cauTrasolwfCollection;
    @JoinColumn(name = "ID_TIPOWF", referencedColumnName = "ID_TIPOWF")
    @ManyToOne
    private CauTipowf idTipowf;
    @OneToMany(mappedBy = "idWorkflow")
    private Collection<CauTraasigwf> cauTraasigwfCollection;

    public CauWorkflow() {
    }

    public CauWorkflow(Integer idWorkflow) {
        this.idWorkflow = idWorkflow;
    }

    public Integer getIdWorkflow() {
        return idWorkflow;
    }

    public void setIdWorkflow(Integer idWorkflow) {
        this.idWorkflow = idWorkflow;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    @XmlTransient
    public Collection<CauTrasolwf> getCauTrasolwfCollection() {
        return cauTrasolwfCollection;
    }

    public void setCauTrasolwfCollection(Collection<CauTrasolwf> cauTrasolwfCollection) {
        this.cauTrasolwfCollection = cauTrasolwfCollection;
    }

    public CauTipowf getIdTipowf() {
        return idTipowf;
    }

    public void setIdTipowf(CauTipowf idTipowf) {
        this.idTipowf = idTipowf;
    }

    @XmlTransient
    public Collection<CauTraasigwf> getCauTraasigwfCollection() {
        return cauTraasigwfCollection;
    }

    public void setCauTraasigwfCollection(Collection<CauTraasigwf> cauTraasigwfCollection) {
        this.cauTraasigwfCollection = cauTraasigwfCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idWorkflow != null ? idWorkflow.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CauWorkflow)) {
            return false;
        }
        CauWorkflow other = (CauWorkflow) object;
        if ((this.idWorkflow == null && other.idWorkflow != null) || (this.idWorkflow != null && !this.idWorkflow.equals(other.idWorkflow))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sfp.model.CauWorkflow[ idWorkflow=" + idWorkflow + " ]";
    }
    
}
