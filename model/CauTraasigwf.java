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
@Table(name = "CAU_TRAASIGWF", catalog = "", schema = "CAU")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CauTraasigwf.findAll", query = "SELECT c FROM CauTraasigwf c")
    , @NamedQuery(name = "CauTraasigwf.findByIdTraasocwf", query = "SELECT c FROM CauTraasigwf c WHERE c.idTraasocwf = :idTraasocwf")})
public class CauTraasigwf implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_TRAASOCWF")
    private Integer idTraasocwf;
    @OneToMany(mappedBy = "idTraasocwf")
    private Collection<CauReglaswf> cauReglaswfCollection;
    @JoinColumn(name = "ID_DEPTRAB", referencedColumnName = "ID_DEPTRAB")
    @ManyToOne
    private CauDeptrabajo idDeptrab;
    @JoinColumn(name = "ID_WORKFLOW", referencedColumnName = "ID_WORKFLOW")
    @ManyToOne
    private CauWorkflow idWorkflow;

    public CauTraasigwf() {
    }

    public CauTraasigwf(Integer idTraasocwf) {
        this.idTraasocwf = idTraasocwf;
    }

    public Integer getIdTraasocwf() {
        return idTraasocwf;
    }

    public void setIdTraasocwf(Integer idTraasocwf) {
        this.idTraasocwf = idTraasocwf;
    }

    @XmlTransient
    public Collection<CauReglaswf> getCauReglaswfCollection() {
        return cauReglaswfCollection;
    }

    public void setCauReglaswfCollection(Collection<CauReglaswf> cauReglaswfCollection) {
        this.cauReglaswfCollection = cauReglaswfCollection;
    }

    public CauDeptrabajo getIdDeptrab() {
        return idDeptrab;
    }

    public void setIdDeptrab(CauDeptrabajo idDeptrab) {
        this.idDeptrab = idDeptrab;
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
        hash += (idTraasocwf != null ? idTraasocwf.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CauTraasigwf)) {
            return false;
        }
        CauTraasigwf other = (CauTraasigwf) object;
        if ((this.idTraasocwf == null && other.idTraasocwf != null) || (this.idTraasocwf != null && !this.idTraasocwf.equals(other.idTraasocwf))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sfp.model.CauTraasigwf[ idTraasocwf=" + idTraasocwf + " ]";
    }
    
}
