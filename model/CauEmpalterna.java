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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jleal
 */
@Entity
@Table(name = "CAU_EMPALTERNA", catalog = "", schema = "CAU")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CauEmpalterna.findAll", query = "SELECT c FROM CauEmpalterna c")
    , @NamedQuery(name = "CauEmpalterna.findByIdNocredencial", query = "SELECT c FROM CauEmpalterna c WHERE c.idNocredencial = :idNocredencial")
    , @NamedQuery(name = "CauEmpalterna.findByUr", query = "SELECT c FROM CauEmpalterna c WHERE c.ur = :ur")
    , @NamedQuery(name = "CauEmpalterna.findByDenominacion", query = "SELECT c FROM CauEmpalterna c WHERE c.denominacion = :denominacion")
    , @NamedQuery(name = "CauEmpalterna.findByNombre", query = "SELECT c FROM CauEmpalterna c WHERE c.nombre = :nombre")
    , @NamedQuery(name = "CauEmpalterna.findByDencompleta", query = "SELECT c FROM CauEmpalterna c WHERE c.dencompleta = :dencompleta")
    , @NamedQuery(name = "CauEmpalterna.findByDescpuesto", query = "SELECT c FROM CauEmpalterna c WHERE c.descpuesto = :descpuesto")
    , @NamedQuery(name = "CauEmpalterna.findByNivelpuesto", query = "SELECT c FROM CauEmpalterna c WHERE c.nivelpuesto = :nivelpuesto")
    , @NamedQuery(name = "CauEmpalterna.findByCorreo", query = "SELECT c FROM CauEmpalterna c WHERE c.correo = :correo")
    , @NamedQuery(name = "CauEmpalterna.findByPiso", query = "SELECT c FROM CauEmpalterna c WHERE c.piso = :piso")
    , @NamedQuery(name = "CauEmpalterna.findByAla", query = "SELECT c FROM CauEmpalterna c WHERE c.ala = :ala")
    , @NamedQuery(name = "CauEmpalterna.findByUbicacion", query = "SELECT c FROM CauEmpalterna c WHERE c.ubicacion = :ubicacion")
    , @NamedQuery(name = "CauEmpalterna.findByExtencion", query = "SELECT c FROM CauEmpalterna c WHERE c.extencion = :extencion")})
public class CauEmpalterna implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_NOCREDENCIAL")
    private Long idNocredencial;
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
    @Column(name = "NIVELPUESTO")
    private String nivelpuesto;
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
    @Column(name = "EXTENCION")
    private String extencion;

    public CauEmpalterna() {
    }

    public CauEmpalterna(Long idNocredencial) {
        this.idNocredencial = idNocredencial;
    }

    public Long getIdNocredencial() {
        return idNocredencial;
    }

    public void setIdNocredencial(Long idNocredencial) {
        this.idNocredencial = idNocredencial;
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

    public String getNivelpuesto() {
        return nivelpuesto;
    }

    public void setNivelpuesto(String nivelpuesto) {
        this.nivelpuesto = nivelpuesto;
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

    public String getExtencion() {
        return extencion;
    }

    public void setExtencion(String extencion) {
        this.extencion = extencion;
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
        if (!(object instanceof CauEmpalterna)) {
            return false;
        }
        CauEmpalterna other = (CauEmpalterna) object;
        if ((this.idNocredencial == null && other.idNocredencial != null) || (this.idNocredencial != null && !this.idNocredencial.equals(other.idNocredencial))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sfp.model.CauEmpalterna[ idNocredencial=" + idNocredencial + " ]";
    }
    
}
