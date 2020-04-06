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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
@Table(name = "CAU_USUARIOS", catalog = "", schema = "CAU")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CauUsuarios.findAll", query = "SELECT c FROM CauUsuarios c")
    , @NamedQuery(name = "CauUsuarios.findByIdUsuario", query = "SELECT c FROM CauUsuarios c WHERE c.idUsuario = :idUsuario")
    , @NamedQuery(name = "CauUsuarios.findByFechaviginicusua", query = "SELECT c FROM CauUsuarios c WHERE c.fechaviginicusua = :fechaviginicusua")
    , @NamedQuery(name = "CauUsuarios.findByFechavigfinusua", query = "SELECT c FROM CauUsuarios c WHERE c.fechavigfinusua = :fechavigfinusua")
    , @NamedQuery(name = "CauUsuarios.findByContrasena", query = "SELECT c FROM CauUsuarios c WHERE c.contrasena = :contrasena")
    , @NamedQuery(name = "CauUsuarios.findByClaveusua", query = "SELECT c FROM CauUsuarios c WHERE c.claveusua = :claveusua")
    , @NamedQuery(name = "CauUsuarios.findByNocredencial", query = "SELECT c FROM CauUsuarios c WHERE c.nocredencial = :nocredencial")
    , @NamedQuery(name = "CauUsuarios.findByCorreo", query = "SELECT c FROM CauUsuarios c WHERE c.correo = :correo")})
public class CauUsuarios implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHAVIGINICUSUA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaviginicusua;
    @Size(max = 20)
    @Column(name = "CONTRASENA")
    private String contrasena;
    @Size(max = 20)
    @Column(name = "CLAVEUSUA")
    private String claveusua;
    @Size(max = 150)
    @Column(name = "CORREO")
    private String correo;
    @OneToMany(mappedBy = "idUsuarean")
    private Collection<CauSuspservicio> cauSuspservicioCollection;
    @OneToMany(mappedBy = "idUsuasusp")
    private Collection<CauSuspservicio> cauSuspservicioCollection1;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_USUARIO")
    private Integer idUsuario;
    @Column(name = "FECHAVIGFINUSUA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechavigfinusua;
    @Column(name = "NOCREDENCIAL")
    private Long nocredencial;
    @JoinColumn(name = "ID_NOCREDENCIAL", referencedColumnName = "ID_NOCREDENCIAL")
    @ManyToOne
    private CauEmp idNocredencial;
    @JoinColumn(name = "ID_AREAGRUPOS", referencedColumnName = "ID_AREAGRUPOS")
    @ManyToOne
    private CauGrupos idAreagrupos;
    @JoinColumn(name = "ID_GRUPO", referencedColumnName = "ID_GRUPO")
    @ManyToOne(optional = false)
    private CauGrupousuario idGrupo;
    @JoinColumn(name = "ID_CVETIPOPERS", referencedColumnName = "ID_CVETIPOPERS")
    @ManyToOne
    private CauTipopersonal idCvetipopers;
    @OneToMany(mappedBy = "idUsuario")
    private Collection<CauLogoper> cauLogoperCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cauUsuarios")
    private Collection<CauDepjefe> cauDepjefeCollection;
    @OneToMany(mappedBy = "idUsuario")
    private Collection<CauLogopdoc> cauLogopdocCollection;
    @OneToMany(mappedBy = "idUsuario")
    private Collection<CauTrabajostecnicos> cauTrabajostecnicosCollection;
    @OneToMany(mappedBy = "idUsuario")
    private Collection<CauUsuariotipo> cauUsuariotipoCollection;
    @OneToMany(mappedBy = "idUsuario")
    private Collection<CauLogopexcep> cauLogopexcepCollection;
    @OneToMany(mappedBy = "idUsuario")
    private Collection<CauHorausu> cauHorausuCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cauUsuarios")
    private Collection<CauTectipotrab> cauTectipotrabCollection;
    @OneToMany(mappedBy = "idUsuario")
    private Collection<CauRolxusuario> cauRolxusuarioCollection;

    public CauUsuarios() {
    }

    public CauUsuarios(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public CauUsuarios(Integer idUsuario, Date fechaviginicusua) {
        this.idUsuario = idUsuario;
        this.fechaviginicusua = fechaviginicusua;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }


    public Date getFechavigfinusua() {
        return fechavigfinusua;
    }

    public void setFechavigfinusua(Date fechavigfinusua) {
        this.fechavigfinusua = fechavigfinusua;
    }


    public Long getNocredencial() {
        return nocredencial;
    }

    public void setNocredencial(Long nocredencial) {
        this.nocredencial = nocredencial;
    }


    public CauEmp getIdNocredencial() {
        return idNocredencial;
    }

    public void setIdNocredencial(CauEmp idNocredencial) {
        this.idNocredencial = idNocredencial;
    }

    public CauGrupos getIdAreagrupos() {
        return idAreagrupos;
    }

    public void setIdAreagrupos(CauGrupos idAreagrupos) {
        this.idAreagrupos = idAreagrupos;
    }

    public CauGrupousuario getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(CauGrupousuario idGrupo) {
        this.idGrupo = idGrupo;
    }

    public CauTipopersonal getIdCvetipopers() {
        return idCvetipopers;
    }

    public void setIdCvetipopers(CauTipopersonal idCvetipopers) {
        this.idCvetipopers = idCvetipopers;
    }

    @XmlTransient
    public Collection<CauLogoper> getCauLogoperCollection() {
        return cauLogoperCollection;
    }

    public void setCauLogoperCollection(Collection<CauLogoper> cauLogoperCollection) {
        this.cauLogoperCollection = cauLogoperCollection;
    }

    @XmlTransient
    public Collection<CauDepjefe> getCauDepjefeCollection() {
        return cauDepjefeCollection;
    }

    public void setCauDepjefeCollection(Collection<CauDepjefe> cauDepjefeCollection) {
        this.cauDepjefeCollection = cauDepjefeCollection;
    }

    @XmlTransient
    public Collection<CauLogopdoc> getCauLogopdocCollection() {
        return cauLogopdocCollection;
    }

    public void setCauLogopdocCollection(Collection<CauLogopdoc> cauLogopdocCollection) {
        this.cauLogopdocCollection = cauLogopdocCollection;
    }

    @XmlTransient
    public Collection<CauTrabajostecnicos> getCauTrabajostecnicosCollection() {
        return cauTrabajostecnicosCollection;
    }

    public void setCauTrabajostecnicosCollection(Collection<CauTrabajostecnicos> cauTrabajostecnicosCollection) {
        this.cauTrabajostecnicosCollection = cauTrabajostecnicosCollection;
    }

    @XmlTransient
    public Collection<CauUsuariotipo> getCauUsuariotipoCollection() {
        return cauUsuariotipoCollection;
    }

    public void setCauUsuariotipoCollection(Collection<CauUsuariotipo> cauUsuariotipoCollection) {
        this.cauUsuariotipoCollection = cauUsuariotipoCollection;
    }

    @XmlTransient
    public Collection<CauLogopexcep> getCauLogopexcepCollection() {
        return cauLogopexcepCollection;
    }

    public void setCauLogopexcepCollection(Collection<CauLogopexcep> cauLogopexcepCollection) {
        this.cauLogopexcepCollection = cauLogopexcepCollection;
    }

    @XmlTransient
    public Collection<CauHorausu> getCauHorausuCollection() {
        return cauHorausuCollection;
    }

    public void setCauHorausuCollection(Collection<CauHorausu> cauHorausuCollection) {
        this.cauHorausuCollection = cauHorausuCollection;
    }

    @XmlTransient
    public Collection<CauTectipotrab> getCauTectipotrabCollection() {
        return cauTectipotrabCollection;
    }

    public void setCauTectipotrabCollection(Collection<CauTectipotrab> cauTectipotrabCollection) {
        this.cauTectipotrabCollection = cauTectipotrabCollection;
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
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CauUsuarios)) {
            return false;
        }
        CauUsuarios other = (CauUsuarios) object;
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sfp.model.CauUsuarios[ idUsuario=" + idUsuario + " ]";
    }

    public Date getFechaviginicusua() {
        return fechaviginicusua;
    }

    public void setFechaviginicusua(Date fechaviginicusua) {
        this.fechaviginicusua = fechaviginicusua;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getClaveusua() {
        return claveusua;
    }

    public void setClaveusua(String claveusua) {
        this.claveusua = claveusua;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Collection<CauSuspservicio> getCauSuspservicioCollection() {
        return cauSuspservicioCollection;
    }

    public void setCauSuspservicioCollection(Collection<CauSuspservicio> cauSuspservicioCollection) {
        this.cauSuspservicioCollection = cauSuspservicioCollection;
    }

    public Collection<CauSuspservicio> getCauSuspservicioCollection1() {
        return cauSuspservicioCollection1;
    }

    public void setCauSuspservicioCollection1(Collection<CauSuspservicio> cauSuspservicioCollection1) {
        this.cauSuspservicioCollection1 = cauSuspservicioCollection1;
    }
    
}
