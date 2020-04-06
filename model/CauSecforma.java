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
@Table(name = "CAU_SECFORMA", catalog = "", schema = "CAU")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CauSecforma.findAll", query = "SELECT c FROM CauSecforma c")
    , @NamedQuery(name = "CauSecforma.findByIdSecciones", query = "SELECT c FROM CauSecforma c WHERE c.idSecciones = :idSecciones")
    , @NamedQuery(name = "CauSecforma.findByDescForma", query = "SELECT c FROM CauSecforma c WHERE c.descForma = :descForma")})
public class CauSecforma implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_SECCIONES")
    private Integer idSecciones;
    @Size(max = 100)
    @Column(name = "DESC_FORMA")
    private String descForma;

    public CauSecforma() {
    }

    public CauSecforma(Integer idSecciones) {
        this.idSecciones = idSecciones;
    }

    public Integer getIdSecciones() {
        return idSecciones;
    }

    public void setIdSecciones(Integer idSecciones) {
        this.idSecciones = idSecciones;
    }

    public String getDescForma() {
        return descForma;
    }

    public void setDescForma(String descForma) {
        this.descForma = descForma;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSecciones != null ? idSecciones.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CauSecforma)) {
            return false;
        }
        CauSecforma other = (CauSecforma) object;
        if ((this.idSecciones == null && other.idSecciones != null) || (this.idSecciones != null && !this.idSecciones.equals(other.idSecciones))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sfp.model.CauSecforma[ idSecciones=" + idSecciones + " ]";
    }
    
}
