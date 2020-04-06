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
@Table(name = "CAU_TIPOWF", catalog = "", schema = "CAU")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CauTipowf.findAll", query = "SELECT c FROM CauTipowf c")
    , @NamedQuery(name = "CauTipowf.findByIdTipowf", query = "SELECT c FROM CauTipowf c WHERE c.idTipowf = :idTipowf")
    , @NamedQuery(name = "CauTipowf.findByDescripcion", query = "SELECT c FROM CauTipowf c WHERE c.descripcion = :descripcion")})
public class CauTipowf implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_TIPOWF")
    private Integer idTipowf;
    @Size(max = 100)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @OneToMany(mappedBy = "idTipowf")
    private Collection<CauWorkflow> cauWorkflowCollection;

    public CauTipowf() {
    }

    public CauTipowf(Integer idTipowf) {
        this.idTipowf = idTipowf;
    }

    public Integer getIdTipowf() {
        return idTipowf;
    }

    public void setIdTipowf(Integer idTipowf) {
        this.idTipowf = idTipowf;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public Collection<CauWorkflow> getCauWorkflowCollection() {
        return cauWorkflowCollection;
    }

    public void setCauWorkflowCollection(Collection<CauWorkflow> cauWorkflowCollection) {
        this.cauWorkflowCollection = cauWorkflowCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipowf != null ? idTipowf.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CauTipowf)) {
            return false;
        }
        CauTipowf other = (CauTipowf) object;
        if ((this.idTipowf == null && other.idTipowf != null) || (this.idTipowf != null && !this.idTipowf.equals(other.idTipowf))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sfp.model.CauTipowf[ idTipowf=" + idTipowf + " ]";
    }
    
}
