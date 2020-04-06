/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfp.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "CAU_GRUPOUSUARIO", catalog = "", schema = "CAU")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CauGrupousuario.findAll", query = "SELECT c FROM CauGrupousuario c")
    , @NamedQuery(name = "CauGrupousuario.findByIdGrupo", query = "SELECT c FROM CauGrupousuario c WHERE c.idGrupo = :idGrupo")
    , @NamedQuery(name = "CauGrupousuario.findByDescGrupo", query = "SELECT c FROM CauGrupousuario c WHERE c.descGrupo = :descGrupo")})
public class CauGrupousuario implements Serializable {

    @Size(max = 50)
    @Column(name = "DESC_GRUPO")
    private String descGrupo;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_GRUPO")
    private Integer idGrupo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idGrupo")
    private Collection<CauUsuarios> cauUsuariosCollection;

    public CauGrupousuario() {
    }

    public CauGrupousuario(Integer idGrupo) {
        this.idGrupo = idGrupo;
    }

    public Integer getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(Integer idGrupo) {
        this.idGrupo = idGrupo;
    }

    public String getDescGrupo() {
        return descGrupo;
    }

    public void setDescGrupo(String descGrupo) {
        this.descGrupo = descGrupo;
    }

    @XmlTransient
    public Collection<CauUsuarios> getCauUsuariosCollection() {
        return cauUsuariosCollection;
    }

    public void setCauUsuariosCollection(Collection<CauUsuarios> cauUsuariosCollection) {
        this.cauUsuariosCollection = cauUsuariosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idGrupo != null ? idGrupo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CauGrupousuario)) {
            return false;
        }
        CauGrupousuario other = (CauGrupousuario) object;
        if ((this.idGrupo == null && other.idGrupo != null) || (this.idGrupo != null && !this.idGrupo.equals(other.idGrupo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sfp.model.CauGrupousuario[ idGrupo=" + idGrupo + " ]";
    }    

   
}
