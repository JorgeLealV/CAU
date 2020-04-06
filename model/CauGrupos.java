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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jleal
 */
@Entity
@Table(name = "CAU_GRUPOS", catalog = "", schema = "CAU")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CauGrupos.findAll", query = "SELECT c FROM CauGrupos c")
    , @NamedQuery(name = "CauGrupos.findByIdAreagrupos", query = "SELECT c FROM CauGrupos c WHERE c.idAreagrupos = :idAreagrupos")
    , @NamedQuery(name = "CauGrupos.findByDescripcion", query = "SELECT c FROM CauGrupos c WHERE c.descripcion = :descripcion")})
public class CauGrupos implements Serializable {

    @Size(max = 150)
    @Column(name = "DESCRIPCION")
    private String descripcion;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "ID_AREAGRUPOS")
    private String idAreagrupos;
    @OneToMany(mappedBy = "idAreagrupos")
    private Collection<CauUsuarios> cauUsuariosCollection;
    @JoinColumn(name = "ID_CVEAREA", referencedColumnName = "ID_CVEAREA")
    @ManyToOne
    private CauArea idCvearea;
    @JoinColumn(name = "ID_MESASERV", referencedColumnName = "ID_MESASERV")
    @ManyToOne
    private CauMesaservicio idMesaserv;
    @OneToMany(mappedBy = "idAreagrupos")
    private Collection<CauTipotrabajo> cauTipotrabajoCollection;

    public CauGrupos() {
    }

    public CauGrupos(String idAreagrupos) {
        this.idAreagrupos = idAreagrupos;
    }

    public String getIdAreagrupos() {
        return idAreagrupos;
    }

    public void setIdAreagrupos(String idAreagrupos) {
        this.idAreagrupos = idAreagrupos;
    }


    @XmlTransient
    public Collection<CauUsuarios> getCauUsuariosCollection() {
        return cauUsuariosCollection;
    }

    public void setCauUsuariosCollection(Collection<CauUsuarios> cauUsuariosCollection) {
        this.cauUsuariosCollection = cauUsuariosCollection;
    }

    public CauArea getIdCvearea() {
        return idCvearea;
    }

    public void setIdCvearea(CauArea idCvearea) {
        this.idCvearea = idCvearea;
    }

    public CauMesaservicio getIdMesaserv() {
        return idMesaserv;
    }

    public void setIdMesaserv(CauMesaservicio idMesaserv) {
        this.idMesaserv = idMesaserv;
    }

    @XmlTransient
    public Collection<CauTipotrabajo> getCauTipotrabajoCollection() {
        return cauTipotrabajoCollection;
    }

    public void setCauTipotrabajoCollection(Collection<CauTipotrabajo> cauTipotrabajoCollection) {
        this.cauTipotrabajoCollection = cauTipotrabajoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAreagrupos != null ? idAreagrupos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CauGrupos)) {
            return false;
        }
        CauGrupos other = (CauGrupos) object;
        if ((this.idAreagrupos == null && other.idAreagrupos != null) || (this.idAreagrupos != null && !this.idAreagrupos.equals(other.idAreagrupos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sfp.model.CauGrupos[ idAreagrupos=" + idAreagrupos + " ]";
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
}
