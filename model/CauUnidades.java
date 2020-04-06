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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jleal
 */
@Entity
@Table(name = "CAU_UNIDADES", catalog = "", schema = "CAU")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CauUnidades.findAll", query = "SELECT c FROM CauUnidades c")
    , @NamedQuery(name = "CauUnidades.findByIdUnidades", query = "SELECT c FROM CauUnidades c WHERE c.idUnidades = :idUnidades")
    , @NamedQuery(name = "CauUnidades.findByDescUnidades", query = "SELECT c FROM CauUnidades c WHERE c.descUnidades = :descUnidades")})
public class CauUnidades implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 18)
    @Column(name = "ID_UNIDADES")
    private String idUnidades;
    @Size(max = 18)
    @Column(name = "DESC_UNIDADES")
    private String descUnidades;
    @JoinColumn(name = "ID_SOCIEDAD", referencedColumnName = "ID_SOCIEDAD")
    @ManyToOne
    private CauSociedad idSociedad;

    public CauUnidades() {
    }

    public CauUnidades(String idUnidades) {
        this.idUnidades = idUnidades;
    }

    public String getIdUnidades() {
        return idUnidades;
    }

    public void setIdUnidades(String idUnidades) {
        this.idUnidades = idUnidades;
    }

    public String getDescUnidades() {
        return descUnidades;
    }

    public void setDescUnidades(String descUnidades) {
        this.descUnidades = descUnidades;
    }

    public CauSociedad getIdSociedad() {
        return idSociedad;
    }

    public void setIdSociedad(CauSociedad idSociedad) {
        this.idSociedad = idSociedad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUnidades != null ? idUnidades.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CauUnidades)) {
            return false;
        }
        CauUnidades other = (CauUnidades) object;
        if ((this.idUnidades == null && other.idUnidades != null) || (this.idUnidades != null && !this.idUnidades.equals(other.idUnidades))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sfp.model.CauUnidades[ idUnidades=" + idUnidades + " ]";
    }
    
}
