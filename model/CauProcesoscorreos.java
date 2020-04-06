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

/**
 *
 * @author jleal
 */
@Entity
@Table(name = "CAU_PROCESOSCORREOS", catalog = "", schema = "CAU")
@NamedQueries({
    @NamedQuery(name = "CauProcesoscorreos.findAll", query = "SELECT c FROM CauProcesoscorreos c")
    , @NamedQuery(name = "CauProcesoscorreos.findByIdProccorreo", query = "SELECT c FROM CauProcesoscorreos c WHERE c.idProccorreo = :idProccorreo")})
public class CauProcesoscorreos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PROCCORREO")
    private Integer idProccorreo;
    @JoinColumn(name = "ID_ETAPAPROCESO", referencedColumnName = "ID_ETAPAPROCESO")
    @ManyToOne
    private CauEtapas idEtapaproceso;
    @JoinColumn(name = "ID_INVOLUCRADOS", referencedColumnName = "ID_INVOLUCRADOS")
    @ManyToOne
    private CauInvproc idInvolucrados;
    @JoinColumn(name = "ID_MESASERV", referencedColumnName = "ID_MESASERV")
    @ManyToOne
    private CauMesaservicio idMesaserv;
    @JoinColumn(name = "ID_TIPODAT", referencedColumnName = "ID_TIPODAT")
    @ManyToOne
    private CauTipdatosadjuntos idTipodat;

    public CauProcesoscorreos() {
    }

    public CauProcesoscorreos(Integer idProccorreo) {
        this.idProccorreo = idProccorreo;
    }

    public Integer getIdProccorreo() {
        return idProccorreo;
    }

    public void setIdProccorreo(Integer idProccorreo) {
        this.idProccorreo = idProccorreo;
    }

    public CauEtapas getIdEtapaproceso() {
        return idEtapaproceso;
    }

    public void setIdEtapaproceso(CauEtapas idEtapaproceso) {
        this.idEtapaproceso = idEtapaproceso;
    }

    public CauInvproc getIdInvolucrados() {
        return idInvolucrados;
    }

    public void setIdInvolucrados(CauInvproc idInvolucrados) {
        this.idInvolucrados = idInvolucrados;
    }

    public CauMesaservicio getIdMesaserv() {
        return idMesaserv;
    }

    public void setIdMesaserv(CauMesaservicio idMesaserv) {
        this.idMesaserv = idMesaserv;
    }

    public CauTipdatosadjuntos getIdTipodat() {
        return idTipodat;
    }

    public void setIdTipodat(CauTipdatosadjuntos idTipodat) {
        this.idTipodat = idTipodat;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProccorreo != null ? idProccorreo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CauProcesoscorreos)) {
            return false;
        }
        CauProcesoscorreos other = (CauProcesoscorreos) object;
        if ((this.idProccorreo == null && other.idProccorreo != null) || (this.idProccorreo != null && !this.idProccorreo.equals(other.idProccorreo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sfp.model.CauProcesoscorreos[ idProccorreo=" + idProccorreo + " ]";
    }
    
}
