/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfp.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jleal
 */
@Entity
@Table(name = "CAU_AUTTIPOTRAB", catalog = "", schema = "CAU")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CauAuttipotrab.findAll", query = "SELECT c FROM CauAuttipotrab c")
    , @NamedQuery(name = "CauAuttipotrab.findByIdAuttrab", query = "SELECT c FROM CauAuttipotrab c WHERE c.idAuttrab = :idAuttrab")
    , @NamedQuery(name = "CauAuttipotrab.findByFechavigini", query = "SELECT c FROM CauAuttipotrab c WHERE c.fechavigini = :fechavigini")
    , @NamedQuery(name = "CauAuttipotrab.findByFechavigfin", query = "SELECT c FROM CauAuttipotrab c WHERE c.fechavigfin = :fechavigfin")})
public class CauAuttipotrab implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_AUTTRAB")
    private Integer idAuttrab;
    @Column(name = "FECHAVIGINI")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechavigini;
    @Column(name = "FECHAVIGFIN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechavigfin;
    @JoinColumn(name = "ID_CVENPUESTO", referencedColumnName = "ID_CVENPUESTO")
    @ManyToOne
    private CauNivelpuesto idCvenpuesto;
    @JoinColumn(name = "ID_TIPO", referencedColumnName = "ID_TIPO")
    @ManyToOne
    private CauTipoaut idTipo;
    @JoinColumn(name = "ID_TRABAJO", referencedColumnName = "ID_TRABAJO")
    @ManyToOne
    private CauTipotrabajo idTrabajo;

    public CauAuttipotrab() {
    }

    public CauAuttipotrab(Integer idAuttrab) {
        this.idAuttrab = idAuttrab;
    }

    public Integer getIdAuttrab() {
        return idAuttrab;
    }

    public void setIdAuttrab(Integer idAuttrab) {
        this.idAuttrab = idAuttrab;
    }

    public Date getFechavigini() {
        return fechavigini;
    }

    public void setFechavigini(Date fechavigini) {
        this.fechavigini = fechavigini;
    }

    public Date getFechavigfin() {
        return fechavigfin;
    }

    public void setFechavigfin(Date fechavigfin) {
        this.fechavigfin = fechavigfin;
    }

    public CauNivelpuesto getIdCvenpuesto() {
        return idCvenpuesto;
    }

    public void setIdCvenpuesto(CauNivelpuesto idCvenpuesto) {
        this.idCvenpuesto = idCvenpuesto;
    }

    public CauTipoaut getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(CauTipoaut idTipo) {
        this.idTipo = idTipo;
    }

    public CauTipotrabajo getIdTrabajo() {
        return idTrabajo;
    }

    public void setIdTrabajo(CauTipotrabajo idTrabajo) {
        this.idTrabajo = idTrabajo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAuttrab != null ? idAuttrab.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CauAuttipotrab)) {
            return false;
        }
        CauAuttipotrab other = (CauAuttipotrab) object;
        if ((this.idAuttrab == null && other.idAuttrab != null) || (this.idAuttrab != null && !this.idAuttrab.equals(other.idAuttrab))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sfp.model.CauAuttipotrab[ idAuttrab=" + idAuttrab + " ]";
    }
    
}
