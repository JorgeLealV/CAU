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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
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
@Table(name = "CAU_ROL", catalog = "", schema = "CAU")
@TableGenerator(name="Rol_Gen", table = "CAU_IDGEN", pkColumnName = "GEN_NAME", 
                  valueColumnName = "GEN_VAL", pkColumnValue = "Rol_Gen", allocationSize=1)

@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CauRol.findAll", query = "SELECT c FROM CauRol c")
    , @NamedQuery(name = "CauRol.findByIdRol", query = "SELECT c FROM CauRol c WHERE c.idRol = :idRol")
    , @NamedQuery(name = "CauRol.findByDescrol", query = "SELECT c FROM CauRol c WHERE c.descrol = :descrol")
    , @NamedQuery(name = "CauRol.findByFechaviginic", query = "SELECT c FROM CauRol c WHERE c.fechaviginic = :fechaviginic")
    , @NamedQuery(name = "CauRol.findByFechavigfin", query = "SELECT c FROM CauRol c WHERE c.fechavigfin = :fechavigfin")
    , @NamedQuery(name = "CauRol.findByTextoexplicativo", query = "SELECT c FROM CauRol c WHERE c.textoexplicativo = :textoexplicativo")
    , @NamedQuery(name = "CauRol.findByFechacreacion", query = "SELECT c FROM CauRol c WHERE c.fechacreacion = :fechacreacion")})
public class CauRol implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator="Rol_Gen")
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_ROL")
    private Integer idRol;
    @Size(max = 100)
    @Column(name = "DESCROL")
    private String descrol;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHAVIGINIC")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaviginic;
    @Column(name = "FECHAVIGFIN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechavigfin;
    @Size(max = 500)
    @Column(name = "TEXTOEXPLICATIVO")
    private String textoexplicativo;
    @Column(name = "FECHACREACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechacreacion;
    @OneToMany(mappedBy = "idRol")
    private Collection<CauMenu> cauMenuCollection;
    @OneToMany(mappedBy = "idRol")
    private Collection<CauRolxtrans> cauRolxtransCollection;
    @OneToMany(mappedBy = "idRol")
    private Collection<CauRolxusuario> cauRolxusuarioCollection;

    public CauRol() {
    }

    public CauRol(Integer idRol) {
        this.idRol = idRol;
    }

    public CauRol(Integer idRol, Date fechaviginic) {
        this.idRol = idRol;
        this.fechaviginic = fechaviginic;
    }

    public Integer getIdRol() {
        return idRol;
    }

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }

    public String getDescrol() {
        return descrol;
    }

    public void setDescrol(String descrol) {
        this.descrol = descrol;
    }

    public Date getFechaviginic() {
        return fechaviginic;
    }

    public void setFechaviginic(Date fechaviginic) {
        this.fechaviginic = fechaviginic;
    }

    public Date getFechavigfin() {
        return fechavigfin;
    }

    public void setFechavigfin(Date fechavigfin) {
        this.fechavigfin = fechavigfin;
    }

    public String getTextoexplicativo() {
        return textoexplicativo;
    }

    public void setTextoexplicativo(String textoexplicativo) {
        this.textoexplicativo = textoexplicativo;
    }

    public Date getFechacreacion() {
        return fechacreacion;
    }

    public void setFechacreacion(Date fechacreacion) {
        this.fechacreacion = fechacreacion;
    }

    @XmlTransient
    public Collection<CauMenu> getCauMenuCollection() {
        return cauMenuCollection;
    }

    public void setCauMenuCollection(Collection<CauMenu> cauMenuCollection) {
        this.cauMenuCollection = cauMenuCollection;
    }

    @XmlTransient
    public Collection<CauRolxtrans> getCauRolxtransCollection() {
        return cauRolxtransCollection;
    }

    public void setCauRolxtransCollection(Collection<CauRolxtrans> cauRolxtransCollection) {
        this.cauRolxtransCollection = cauRolxtransCollection;
    }

    @XmlTransient
    public Collection<CauRolxusuario> getCauRolxusuarioCollection() {
        return cauRolxusuarioCollection;
    }

    public void setCauRolxusuarioCollection(Collection<CauRolxusuario> cauRolxusuarioCollection) {
        this.cauRolxusuarioCollection = cauRolxusuarioCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRol != null ? idRol.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CauRol)) {
            return false;
        }
        CauRol other = (CauRol) object;
        if ((this.idRol == null && other.idRol != null) || (this.idRol != null && !this.idRol.equals(other.idRol))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sfp.model.CauRol[ idRol=" + idRol + " ]";
    }
    
}
