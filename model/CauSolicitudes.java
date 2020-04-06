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
@Table(name = "CAU_SOLICITUDES", catalog = "", schema = "CAU")
@TableGenerator(name="Solic_Gen", table = "CAU_IDGEN", pkColumnName = "GEN_NAME", 
                  valueColumnName = "GEN_VAL", pkColumnValue = "Solic_Gen", allocationSize=1)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CauSolicitudes.findAll", query = "SELECT c FROM CauSolicitudes c")
    , @NamedQuery(name = "CauSolicitudes.findByIdSolicitud", query = "SELECT c FROM CauSolicitudes c WHERE c.idSolicitud = :idSolicitud")
    , @NamedQuery(name = "CauSolicitudes.findByFechacaptura", query = "SELECT c FROM CauSolicitudes c WHERE c.fechacaptura = :fechacaptura")
    , @NamedQuery(name = "CauSolicitudes.findByFechaenvio", query = "SELECT c FROM CauSolicitudes c WHERE c.fechaenvio = :fechaenvio")
    , @NamedQuery(name = "CauSolicitudes.findByFechacancusuario", query = "SELECT c FROM CauSolicitudes c WHERE c.fechacancusuario = :fechacancusuario")
    , @NamedQuery(name = "CauSolicitudes.findByFechacierre", query = "SELECT c FROM CauSolicitudes c WHERE c.fechacierre = :fechacierre")
    , @NamedQuery(name = "CauSolicitudes.findByDescadmin", query = "SELECT c FROM CauSolicitudes c WHERE c.descadmin = :descadmin")
    , @NamedQuery(name = "CauSolicitudes.findByUsuariosolicita", query = "SELECT c FROM CauSolicitudes c WHERE c.usuariosolicita = :usuariosolicita")
    , @NamedQuery(name = "CauSolicitudes.findByUsuarioatendido", query = "SELECT c FROM CauSolicitudes c WHERE c.usuarioatendido = :usuarioatendido")
    , @NamedQuery(name = "CauSolicitudes.findByFechacancela", query = "SELECT c FROM CauSolicitudes c WHERE c.fechacancela = :fechacancela")
    , @NamedQuery(name = "CauSolicitudes.findByDesccancea", query = "SELECT c FROM CauSolicitudes c WHERE c.desccancea = :desccancea")
    , @NamedQuery(name = "CauSolicitudes.findByDescusuario", query = "SELECT c FROM CauSolicitudes c WHERE c.descusuario = :descusuario")})
public class CauSolicitudes implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHACAPTURA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechacaptura;
    @Size(max = 1500)
    @Column(name = "DESCADMIN")
    private String descadmin;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "USUARIOSOLICITA")
    private String usuariosolicita;
    @Size(max = 20)
    @Column(name = "USUARIOATENDIDO")
    private String usuarioatendido;
    @Size(max = 120)
    @Column(name = "DESCCANCEA")
    private String desccancea;
    @Size(max = 1500)
    @Column(name = "DESCUSUARIO")
    private String descusuario;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator="Solic_Gen")
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_SOLICITUD")
    private Integer idSolicitud;
    @Column(name = "FECHAENVIO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaenvio;
    @Column(name = "FECHACANCUSUARIO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechacancusuario;
    @Column(name = "FECHACIERRE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechacierre;
    @Column(name = "FECHACANCELA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechacancela;
    @JoinColumn(name = "ID_ESTATUS", referencedColumnName = "ID_ESTATUS")
    @ManyToOne
    private CauEstatusgeneral idEstatus;
    @JoinColumn(name = "ID_MESASERV", referencedColumnName = "ID_MESASERV")
    @ManyToOne
    private CauMesaservicio idMesaserv;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSolicitud")
    private Collection<CauSolicitudtrabajos> cauSolicitudtrabajosCollection;

    public CauSolicitudes() {
    }

    public CauSolicitudes(Integer idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public CauSolicitudes(Integer idSolicitud, Date fechacaptura, String usuariosolicita) {
        this.idSolicitud = idSolicitud;
        this.fechacaptura = fechacaptura;
        this.usuariosolicita = usuariosolicita;
    }

    public Integer getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(Integer idSolicitud) {
        this.idSolicitud = idSolicitud;
    }


    public Date getFechaenvio() {
        return fechaenvio;
    }

    public void setFechaenvio(Date fechaenvio) {
        this.fechaenvio = fechaenvio;
    }

    public Date getFechacancusuario() {
        return fechacancusuario;
    }

    public void setFechacancusuario(Date fechacancusuario) {
        this.fechacancusuario = fechacancusuario;
    }

    public Date getFechacierre() {
        return fechacierre;
    }

    public void setFechacierre(Date fechacierre) {
        this.fechacierre = fechacierre;
    }


    public Date getFechacancela() {
        return fechacancela;
    }

    public void setFechacancela(Date fechacancela) {
        this.fechacancela = fechacancela;
    }


    public CauEstatusgeneral getIdEstatus() {
        return idEstatus;
    }

    public void setIdEstatus(CauEstatusgeneral idEstatus) {
        this.idEstatus = idEstatus;
    }

    public CauMesaservicio getIdMesaserv() {
        return idMesaserv;
    }

    public void setIdMesaserv(CauMesaservicio idMesaserv) {
        this.idMesaserv = idMesaserv;
    }

    @XmlTransient
    public Collection<CauSolicitudtrabajos> getCauSolicitudtrabajosCollection() {
        return cauSolicitudtrabajosCollection;
    }

    public void setCauSolicitudtrabajosCollection(Collection<CauSolicitudtrabajos> cauSolicitudtrabajosCollection) {
        this.cauSolicitudtrabajosCollection = cauSolicitudtrabajosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSolicitud != null ? idSolicitud.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CauSolicitudes)) {
            return false;
        }
        CauSolicitudes other = (CauSolicitudes) object;
        if ((this.idSolicitud == null && other.idSolicitud != null) || (this.idSolicitud != null && !this.idSolicitud.equals(other.idSolicitud))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sfp.model.CauSolicitudes[ idSolicitud=" + idSolicitud + " ]";
    }

    public Date getFechacaptura() {
        return fechacaptura;
    }

    public void setFechacaptura(Date fechacaptura) {
        this.fechacaptura = fechacaptura;
    }

    public String getDescadmin() {
        return descadmin;
    }

    public void setDescadmin(String descadmin) {
        this.descadmin = descadmin;
    }

    public String getUsuariosolicita() {
        return usuariosolicita;
    }

    public void setUsuariosolicita(String usuariosolicita) {
        this.usuariosolicita = usuariosolicita;
    }

    public String getUsuarioatendido() {
        return usuarioatendido;
    }

    public void setUsuarioatendido(String usuarioatendido) {
        this.usuarioatendido = usuarioatendido;
    }

    public String getDesccancea() {
        return desccancea;
    }

    public void setDesccancea(String desccancea) {
        this.desccancea = desccancea;
    }

    public String getDescusuario() {
        return descusuario;
    }

    public void setDescusuario(String descusuario) {
        this.descusuario = descusuario;
    }
    
}
