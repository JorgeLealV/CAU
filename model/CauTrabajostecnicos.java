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

/**
 *
 * @author jleal
 */
@Entity
@Table(name = "CAU_TRABAJOSTECNICOS", catalog = "", schema = "CAU")
@TableGenerator(name="SolTec_Gen", table = "CAU_IDGEN", pkColumnName = "GEN_NAME", 
                  valueColumnName = "GEN_VAL", pkColumnValue = "SolTec_Gen", allocationSize=1)
@NamedQueries({
    @NamedQuery(name = "CauTrabajostecnicos.findAll", query = "SELECT c FROM CauTrabajostecnicos c")
    , @NamedQuery(name = "CauTrabajostecnicos.findByDesctrabtec", query = "SELECT c FROM CauTrabajostecnicos c WHERE c.desctrabtec = :desctrabtec")
    , @NamedQuery(name = "CauTrabajostecnicos.findByFechahoraasig", query = "SELECT c FROM CauTrabajostecnicos c WHERE c.fechahoraasig = :fechahoraasig")
    , @NamedQuery(name = "CauTrabajostecnicos.findByNoservasignados", query = "SELECT c FROM CauTrabajostecnicos c WHERE c.noservasignados = :noservasignados")
    , @NamedQuery(name = "CauTrabajostecnicos.findByIdTrabtec", query = "SELECT c FROM CauTrabajostecnicos c WHERE c.idTrabtec = :idTrabtec")
    , @NamedQuery(name = "CauTrabajostecnicos.findByFechahoraconc", query = "SELECT c FROM CauTrabajostecnicos c WHERE c.fechahoraconc = :fechahoraconc")
    , @NamedQuery(name = "CauTrabajostecnicos.findByCumpleestandar", query = "SELECT c FROM CauTrabajostecnicos c WHERE c.cumpleestandar = :cumpleestandar")
    , @NamedQuery(name = "CauTrabajostecnicos.findByTiempocalculado", query = "SELECT c FROM CauTrabajostecnicos c WHERE c.tiempocalculado = :tiempocalculado")
    , @NamedQuery(name = "CauTrabajostecnicos.findByDescasignacion", query = "SELECT c FROM CauTrabajostecnicos c WHERE c.descasignacion = :descasignacion")})
public class CauTrabajostecnicos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Size(max = 1500)
    @Column(name = "DESCTRABTEC")
    private String desctrabtec;
    @Column(name = "FECHAHORAASIG")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechahoraasig;
    @Column(name = "NOSERVASIGNADOS")
    private Integer noservasignados;    
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator="SolTec_Gen")
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_TRABTEC")
    private Integer idTrabtec;
    @Column(name = "FECHAHORACONC")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechahoraconc;
    @Size(max = 10)
    @Column(name = "CUMPLEESTANDAR")
    private String cumpleestandar;
    @Column(name = "TIEMPOCALCULADO")
    private Integer tiempocalculado;
    @Size(max = 1500)
    @Column(name = "DESCASIGNACION")
    private String descasignacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTrabtec")
    private Collection<CauSuspservicio> cauSuspservicioCollection;
    @JoinColumn(name = "ID_SOLTRAB", referencedColumnName = "ID_SOLTRAB")
    @ManyToOne
    private CauSolicitudtrabajos idSoltrab;
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO")
    @ManyToOne
    private CauUsuarios idUsuario;
    @JoinColumn(name = "ID_USUASIG", referencedColumnName = "ID_USUARIO")
    @ManyToOne
    private CauUsuarios idUsuasig;
    @JoinColumn(name = "ID_USUCANCONC", referencedColumnName = "ID_USUARIO")
    @ManyToOne
    private CauUsuarios idUsucanconc;

    public CauTrabajostecnicos() {
    }

    public CauTrabajostecnicos(Integer idTrabtec) {
        this.idTrabtec = idTrabtec;
    }

    public String getDesctrabtec() {
        return desctrabtec;
    }

    public void setDesctrabtec(String desctrabtec) {
        this.desctrabtec = desctrabtec;
    }

    public Date getFechahoraasig() {
        return fechahoraasig;
    }

    public void setFechahoraasig(Date fechahoraasig) {
        this.fechahoraasig = fechahoraasig;
    }

    public Integer getNoservasignados() {
        return noservasignados;
    }

    public void setNoservasignados(Integer noservasignados) {
        this.noservasignados = noservasignados;
    }

    public Integer getIdTrabtec() {
        return idTrabtec;
    }

    public void setIdTrabtec(Integer idTrabtec) {
        this.idTrabtec = idTrabtec;
    }

    public Date getFechahoraconc() {
        return fechahoraconc;
    }

    public void setFechahoraconc(Date fechahoraconc) {
        this.fechahoraconc = fechahoraconc;
    }

    public String getCumpleestandar() {
        return cumpleestandar;
    }

    public void setCumpleestandar(String cumpleestandar) {
        this.cumpleestandar = cumpleestandar;
    }

    public Integer getTiempocalculado() {
        return tiempocalculado;
    }

    public void setTiempocalculado(Integer tiempocalculado) {
        this.tiempocalculado = tiempocalculado;
    }

    public String getDescasignacion() {
        return descasignacion;
    }

    public void setDescasignacion(String descasignacion) {
        this.descasignacion = descasignacion;
    }

    public Collection<CauSuspservicio> getCauSuspservicioCollection() {
        return cauSuspservicioCollection;
    }

    public void setCauSuspservicioCollection(Collection<CauSuspservicio> cauSuspservicioCollection) {
        this.cauSuspservicioCollection = cauSuspservicioCollection;
    }

    public CauSolicitudtrabajos getIdSoltrab() {
        return idSoltrab;
    }

    public void setIdSoltrab(CauSolicitudtrabajos idSoltrab) {
        this.idSoltrab = idSoltrab;
    }

    public CauUsuarios getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(CauUsuarios idUsuario) {
        this.idUsuario = idUsuario;
    }

    public CauUsuarios getIdUsuasig() {
        return idUsuasig;
    }

    public void setIdUsuasig(CauUsuarios idUsuasig) {
        this.idUsuasig = idUsuasig;
    }

    public CauUsuarios getIdUsucanconc() {
        return idUsucanconc;
    }

    public void setIdUsucanconc(CauUsuarios idUsucanconc) {
        this.idUsucanconc = idUsucanconc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTrabtec != null ? idTrabtec.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CauTrabajostecnicos)) {
            return false;
        }
        CauTrabajostecnicos other = (CauTrabajostecnicos) object;
        if ((this.idTrabtec == null && other.idTrabtec != null) || (this.idTrabtec != null && !this.idTrabtec.equals(other.idTrabtec))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sfp.model.CauTrabajostecnicos[ idTrabtec=" + idTrabtec + " ]";
    }
    
}
