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
@Table(name = "CAU_EMP", catalog = "", schema = "CAU")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CauEmp.findAll", query = "SELECT c FROM CauEmp c")
    , @NamedQuery(name = "CauEmp.findByIdNocredencial", query = "SELECT c FROM CauEmp c WHERE c.idNocredencial = :idNocredencial")
    , @NamedQuery(name = "CauEmp.findByUr", query = "SELECT c FROM CauEmp c WHERE c.ur = :ur")
    , @NamedQuery(name = "CauEmp.findByDenominacion", query = "SELECT c FROM CauEmp c WHERE c.denominacion = :denominacion")
    , @NamedQuery(name = "CauEmp.findByNombre", query = "SELECT c FROM CauEmp c WHERE c.nombre = :nombre")
    , @NamedQuery(name = "CauEmp.findByDencompleta", query = "SELECT c FROM CauEmp c WHERE c.dencompleta = :dencompleta")
    , @NamedQuery(name = "CauEmp.findByDescpuesto", query = "SELECT c FROM CauEmp c WHERE c.descpuesto = :descpuesto")
    , @NamedQuery(name = "CauEmp.findByNivelpresup", query = "SELECT c FROM CauEmp c WHERE c.nivelpresup = :nivelpresup")
    , @NamedQuery(name = "CauEmp.findByCorreo", query = "SELECT c FROM CauEmp c WHERE c.correo = :correo")
    , @NamedQuery(name = "CauEmp.findByPiso", query = "SELECT c FROM CauEmp c WHERE c.piso = :piso")
    , @NamedQuery(name = "CauEmp.findByAla", query = "SELECT c FROM CauEmp c WHERE c.ala = :ala")
    , @NamedQuery(name = "CauEmp.findByUbicacion", query = "SELECT c FROM CauEmp c WHERE c.ubicacion = :ubicacion")
    , @NamedQuery(name = "CauEmp.findByExtension", query = "SELECT c FROM CauEmp c WHERE c.extension = :extension")})
public class CauEmp implements Serializable {

    @Size(max = 4)
    @Column(name = "UR")
    private String ur;
    @Size(max = 150)
    @Column(name = "DENOMINACION")
    private String denominacion;
    @Size(max = 100)
    @Column(name = "NOMBRE")
    private String nombre;
    @Size(max = 150)
    @Column(name = "DENCOMPLETA")
    private String dencompleta;
    @Size(max = 150)
    @Column(name = "DESCPUESTO")
    private String descpuesto;
    @Size(max = 20)
    @Column(name = "NIVELPRESUP")
    private String nivelpresup;
    @Size(max = 120)
    @Column(name = "CORREO")
    private String correo;
    @Size(max = 10)
    @Column(name = "PISO")
    private String piso;
    @Size(max = 10)
    @Column(name = "ALA")
    private String ala;
    @Size(max = 100)
    @Column(name = "UBICACION")
    private String ubicacion;
    @Size(max = 20)
    @Column(name = "EXTENSION")
    private String extension;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_NOCREDENCIAL")
    private Long idNocredencial;
    @OneToMany(mappedBy = "idNocredencial")
    private Collection<CauUsuarios> cauUsuariosCollection;

    public CauEmp() {
    }

    public CauEmp(Long idNocredencial) {
        this.idNocredencial = idNocredencial;
    }

    public Long getIdNocredencial() {
        return idNocredencial;
    }

    public void setIdNocredencial(Long idNocredencial) {
        this.idNocredencial = idNocredencial;
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
        hash += (idNocredencial != null ? idNocredencial.hashCode() : 0);
        return hash;
    }
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CauEmp)) {
            return false;
        }
        CauEmp other = (CauEmp) object;
        if ((this.idNocredencial == null && other.idNocredencial != null) || (this.idNocredencial != null && !this.idNocredencial.equals(other.idNocredencial))) {
            return false;
        }
        return true;
    }
    @Override
    public String toString() {
        return "com.sfp.model.CauEmp[ idNocredencial=" + idNocredencial + " ]";
    }

    public String getUr() {
        return ur;
    }

    public void setUr(String ur) {
        this.ur = ur;
    }

    public String getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDencompleta() {
        return dencompleta;
    }

    public void setDencompleta(String dencompleta) {
        this.dencompleta = dencompleta;
    }

    public String getDescpuesto() {
        return descpuesto;
    }

    public void setDescpuesto(String descpuesto) {
        this.descpuesto = descpuesto;
    }

    public String getNivelpresup() {
        return nivelpresup;
    }

    public void setNivelpresup(String nivelpresup) {
        this.nivelpresup = nivelpresup;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPiso() {
        return piso;
    }

    public void setPiso(String piso) {
        this.piso = piso;
    }

    public String getAla() {
        return ala;
    }

    public void setAla(String ala) {
        this.ala = ala;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }
    
}
