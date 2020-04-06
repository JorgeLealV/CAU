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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "CAU_SOLICITUDTRABAJOS", catalog = "", schema = "CAU")
@TableGenerator(name="SolTrab_Gen", table = "CAU_IDGEN", pkColumnName = "GEN_NAME", 
                  valueColumnName = "GEN_VAL", pkColumnValue = "SolTrab_Gen", allocationSize=1)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CauSolicitudtrabajos.findAll", query = "SELECT c FROM CauSolicitudtrabajos c")
    , @NamedQuery(name = "CauSolicitudtrabajos.findByFechaasigsolicusua", query = "SELECT c FROM CauSolicitudtrabajos c WHERE c.fechaasigsolicusua = :fechaasigsolicusua")
    , @NamedQuery(name = "CauSolicitudtrabajos.findByDesctrabusuario", query = "SELECT c FROM CauSolicitudtrabajos c WHERE c.desctrabusuario = :desctrabusuario")
    , @NamedQuery(name = "CauSolicitudtrabajos.findByNodeservicios", query = "SELECT c FROM CauSolicitudtrabajos c WHERE c.nodeservicios = :nodeservicios")
    , @NamedQuery(name = "CauSolicitudtrabajos.findByFechaasigdepto", query = "SELECT c FROM CauSolicitudtrabajos c WHERE c.fechaasigdepto = :fechaasigdepto")
    , @NamedQuery(name = "CauSolicitudtrabajos.findByFechacanctrabajo", query = "SELECT c FROM CauSolicitudtrabajos c WHERE c.fechacanctrabajo = :fechacanctrabajo")
    , @NamedQuery(name = "CauSolicitudtrabajos.findByDesccancelacion", query = "SELECT c FROM CauSolicitudtrabajos c WHERE c.desccancelacion = :desccancelacion")
    , @NamedQuery(name = "CauSolicitudtrabajos.findByNoestandares", query = "SELECT c FROM CauSolicitudtrabajos c WHERE c.noestandares = :noestandares")
    , @NamedQuery(name = "CauSolicitudtrabajos.findByIdSoltrab", query = "SELECT c FROM CauSolicitudtrabajos c WHERE c.idSoltrab = :idSoltrab")
    , @NamedQuery(name = "CauSolicitudtrabajos.findByCumpleestandar", query = "SELECT c FROM CauSolicitudtrabajos c WHERE c.cumpleestandar = :cumpleestandar")
    , @NamedQuery(name = "CauSolicitudtrabajos.findByTiempocalculado", query = "SELECT c FROM CauSolicitudtrabajos c WHERE c.tiempocalculado = :tiempocalculado")})
public class CauSolicitudtrabajos implements Serializable {

    @Size(max = 1500)
    @Column(name = "DESCTRABUSUARIO")
    private String desctrabusuario;
    @Size(max = 1500)
    @Column(name = "DESCCANCELACION")
    private String desccancelacion;
    @Size(max = 10)
    @Column(name = "CUMPLEESTANDAR")
    private String cumpleestandar;
    @Size(max = 1500)
    @Column(name = "DESCRIPCION")
    private String descripcion;

    private static final long serialVersionUID = 1L;
    @Column(name = "FECHAASIGSOLICUSUA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaasigsolicusua;
    @Column(name = "NODESERVICIOS")
    private Integer nodeservicios;
    @Column(name = "FECHAASIGDEPTO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaasigdepto;
    @Column(name = "FECHACANCTRABAJO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechacanctrabajo;
    @Column(name = "NOESTANDARES")
    private Integer noestandares;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator="SolTrab_Gen")
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_SOLTRAB")
    private Integer idSoltrab;
    @Column(name = "TIEMPOCALCULADO")
    private Integer tiempocalculado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSoltrab")
    private Collection<CauDocadjuntos> cauDocadjuntosCollection;
    @OneToMany(mappedBy = "idSoltrab")
    private Collection<CauTrabajostecnicos> cauTrabajostecnicosCollection;
    @OneToMany(mappedBy = "idSoltrab")
    private Collection<CauTrasoldetwf> cauTrasoldetwfCollection;
    @OneToMany(mappedBy = "idSoltrabpadre")
    private Collection<CauTrasolwf> cauTrasolwfCollection;
    @JoinColumn(name = "ID_DEPTRAB", referencedColumnName = "ID_DEPTRAB")
    @ManyToOne(optional = false)
    private CauDeptrabajo idDeptrab;
    @JoinColumn(name = "ID_ESTATUS", referencedColumnName = "ID_ESTATUS")
    @ManyToOne
    private CauEstatusgeneral idEstatus;
    @JoinColumn(name = "ID_SOLICITUD", referencedColumnName = "ID_SOLICITUD")
    @ManyToOne(optional = false)
    private CauSolicitudes idSolicitud;
    @JoinColumn(name = "ID_ACTIVACION", referencedColumnName = "ID_ACTIVACION")
    @ManyToOne
    private CauTipoactiv idActivacion;

    public CauSolicitudtrabajos() {
    }

    public CauSolicitudtrabajos(Integer idSoltrab) {
        this.idSoltrab = idSoltrab;
    }

    public Date getFechaasigsolicusua() {
        return fechaasigsolicusua;
    }

    public void setFechaasigsolicusua(Date fechaasigsolicusua) {
        this.fechaasigsolicusua = fechaasigsolicusua;
    }


    public Integer getNodeservicios() {
        return nodeservicios;
    }

    public void setNodeservicios(Integer nodeservicios) {
        this.nodeservicios = nodeservicios;
    }

    public Date getFechaasigdepto() {
        return fechaasigdepto;
    }

    public void setFechaasigdepto(Date fechaasigdepto) {
        this.fechaasigdepto = fechaasigdepto;
    }

    public Date getFechacanctrabajo() {
        return fechacanctrabajo;
    }

    public void setFechacanctrabajo(Date fechacanctrabajo) {
        this.fechacanctrabajo = fechacanctrabajo;
    }


    public Integer getNoestandares() {
        return noestandares;
    }

    public void setNoestandares(Integer noestandares) {
        this.noestandares = noestandares;
    }

    public Integer getIdSoltrab() {
        return idSoltrab;
    }

    public void setIdSoltrab(Integer idSoltrab) {
        this.idSoltrab = idSoltrab;
    }


    public Integer getTiempocalculado() {
        return tiempocalculado;
    }

    public void setTiempocalculado(Integer tiempocalculado) {
        this.tiempocalculado = tiempocalculado;
    }


    
    
    @XmlTransient
    public Collection<CauDocadjuntos> getCauDocadjuntosCollection() {
        return cauDocadjuntosCollection;
    }

    public void setCauDocadjuntosCollection(Collection<CauDocadjuntos> cauDocadjuntosCollection) {
        this.cauDocadjuntosCollection = cauDocadjuntosCollection;
    }

    @XmlTransient
    public Collection<CauTrabajostecnicos> getCauTrabajostecnicosCollection() {
        return cauTrabajostecnicosCollection;
    }

    public void setCauTrabajostecnicosCollection(Collection<CauTrabajostecnicos> cauTrabajostecnicosCollection) {
        this.cauTrabajostecnicosCollection = cauTrabajostecnicosCollection;
    }

    @XmlTransient
    public Collection<CauTrasoldetwf> getCauTrasoldetwfCollection() {
        return cauTrasoldetwfCollection;
    }

    public void setCauTrasoldetwfCollection(Collection<CauTrasoldetwf> cauTrasoldetwfCollection) {
        this.cauTrasoldetwfCollection = cauTrasoldetwfCollection;
    }

    @XmlTransient
    public Collection<CauTrasolwf> getCauTrasolwfCollection() {
        return cauTrasolwfCollection;
    }

    public void setCauTrasolwfCollection(Collection<CauTrasolwf> cauTrasolwfCollection) {
        this.cauTrasolwfCollection = cauTrasolwfCollection;
    }

    public CauDeptrabajo getIdDeptrab() {
        return idDeptrab;
    }

    public void setIdDeptrab(CauDeptrabajo idDeptrab) {
        this.idDeptrab = idDeptrab;
    }

    public CauEstatusgeneral getIdEstatus() {
        return idEstatus;
    }

    public void setIdEstatus(CauEstatusgeneral idEstatus) {
        this.idEstatus = idEstatus;
    }

    public CauSolicitudes getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(CauSolicitudes idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public CauTipoactiv getIdActivacion() {
        return idActivacion;
    }

    public void setIdActivacion(CauTipoactiv idActivacion) {
        this.idActivacion = idActivacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSoltrab != null ? idSoltrab.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CauSolicitudtrabajos)) {
            return false;
        }
        CauSolicitudtrabajos other = (CauSolicitudtrabajos) object;
        if ((this.idSoltrab == null && other.idSoltrab != null) || (this.idSoltrab != null && !this.idSoltrab.equals(other.idSoltrab))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sfp.model.CauSolicitudtrabajos[ idSoltrab=" + idSoltrab + " ]";
    }

    public String getDesctrabusuario() {
        return desctrabusuario;
    }

    public void setDesctrabusuario(String desctrabusuario) {
        this.desctrabusuario = desctrabusuario;
    }

    public String getDesccancelacion() {
        return desccancelacion;
    }

    public void setDesccancelacion(String desccancelacion) {
        this.desccancelacion = desccancelacion;
    }

    public String getCumpleestandar() {
        return cumpleestandar;
    }

    public void setCumpleestandar(String cumpleestandar) {
        this.cumpleestandar = cumpleestandar;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
}
