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
@Table(name = "CAU_NIVELPUESTO", catalog = "", schema = "CAU")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CauNivelpuesto.findAll", query = "SELECT c FROM CauNivelpuesto c")
    , @NamedQuery(name = "CauNivelpuesto.findByIdCvenpuesto", query = "SELECT c FROM CauNivelpuesto c WHERE c.idCvenpuesto = :idCvenpuesto")
    , @NamedQuery(name = "CauNivelpuesto.findByDescripcion", query = "SELECT c FROM CauNivelpuesto c WHERE c.descripcion = :descripcion")
    , @NamedQuery(name = "CauNivelpuesto.findByNivpuesto", query = "SELECT c FROM CauNivelpuesto c WHERE c.nivpuesto = :nivpuesto")})
public class CauNivelpuesto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_CVENPUESTO")
    private Integer idCvenpuesto;
    @Size(max = 150)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Size(max = 20)
    @Column(name = "NIVPUESTO")
    private String nivpuesto;
    @OneToMany(mappedBy = "idCvenpuesto")
    private Collection<CauAuttipotrab> cauAuttipotrabCollection;

    public CauNivelpuesto() {
    }

    public CauNivelpuesto(Integer idCvenpuesto) {
        this.idCvenpuesto = idCvenpuesto;
    }

    public Integer getIdCvenpuesto() {
        return idCvenpuesto;
    }

    public void setIdCvenpuesto(Integer idCvenpuesto) {
        this.idCvenpuesto = idCvenpuesto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNivpuesto() {
        return nivpuesto;
    }

    public void setNivpuesto(String nivpuesto) {
        this.nivpuesto = nivpuesto;
    }

    @XmlTransient
    public Collection<CauAuttipotrab> getCauAuttipotrabCollection() {
        return cauAuttipotrabCollection;
    }

    public void setCauAuttipotrabCollection(Collection<CauAuttipotrab> cauAuttipotrabCollection) {
        this.cauAuttipotrabCollection = cauAuttipotrabCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCvenpuesto != null ? idCvenpuesto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CauNivelpuesto)) {
            return false;
        }
        CauNivelpuesto other = (CauNivelpuesto) object;
        if ((this.idCvenpuesto == null && other.idCvenpuesto != null) || (this.idCvenpuesto != null && !this.idCvenpuesto.equals(other.idCvenpuesto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sfp.model.CauNivelpuesto[ idCvenpuesto=" + idCvenpuesto + " ]";
    }
    
}
