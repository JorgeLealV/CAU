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

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


import javax.persistence.TableGenerator;
import javax.validation.constraints.Size;





/**
 *
 * @author jleal
 */
@Entity
@Table(name = "CAU_DOCADJUNTOS", catalog = "", schema = "CAU")
@TableGenerator(name="DocAdjuntos_Gen", table = "CAU_IDGEN", pkColumnName = "GEN_NAME", 
                  valueColumnName = "GEN_VAL", pkColumnValue = "DocAdjuntos_Gen", allocationSize=1)

@NamedQueries({
    @NamedQuery(name = "CauDocadjuntos.findAll", query = "SELECT c FROM CauDocadjuntos c")
    , @NamedQuery(name = "CauDocadjuntos.findByIdDocadjunt", query = "SELECT c FROM CauDocadjuntos c WHERE c.idDocadjunt = :idDocadjunt")
    , @NamedQuery(name = "CauDocadjuntos.findByNombrearchivo", query = "SELECT c FROM CauDocadjuntos c WHERE c.nombrearchivo = :nombrearchivo")
    , @NamedQuery(name = "CauDocadjuntos.findByNombrearchenviado", query = "SELECT c FROM CauDocadjuntos c WHERE c.nombrearchenviado = :nombrearchenviado")
    , @NamedQuery(name = "CauDocadjuntos.findByTamano", query = "SELECT c FROM CauDocadjuntos c WHERE c.tamano = :tamano")
    , @NamedQuery(name = "CauDocadjuntos.findByDescripcion", query = "SELECT c FROM CauDocadjuntos c WHERE c.descripcion = :descripcion")})
public class CauDocadjuntos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator="DocAdjuntos_Gen")
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_DOCADJUNT")
    private Integer idDocadjunt;
    @Size(max = 100)
    @Column(name = "NOMBREARCHIVO")
    private String nombrearchivo;
    @Size(max = 100)
    @Column(name = "NOMBREARCHENVIADO")
    private String nombrearchenviado;
    @Column(name = "TAMANO")
    private Integer tamano;
    @Size(max = 200)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @JoinColumn(name = "ID_SOLTRAB", referencedColumnName = "ID_SOLTRAB")
    @ManyToOne(optional = false)
    private CauSolicitudtrabajos idSoltrab;
    

    public CauDocadjuntos() {
    }

    public CauDocadjuntos(Integer idDocadjunt) {
        this.idDocadjunt = idDocadjunt;
    }

    public Integer getIdDocadjunt() {
        return idDocadjunt;
    }

    public void setIdDocadjunt(Integer idDocadjunt) {
        this.idDocadjunt = idDocadjunt;
    }

    public String getNombrearchivo() {
        return nombrearchivo;
    }

    public void setNombrearchivo(String nombrearchivo) {
        this.nombrearchivo = nombrearchivo;
    }

    public String getNombrearchenviado() {
        return nombrearchenviado;
    }

    public void setNombrearchenviado(String nombrearchenviado) {
        this.nombrearchenviado = nombrearchenviado;
    }

    public Integer getTamano() {
        return tamano;
    }

    public void setTamano(Integer tamano) {
        this.tamano = tamano;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public CauSolicitudtrabajos getIdSoltrab() {
        return idSoltrab;
    }

    public void setIdSoltrab(CauSolicitudtrabajos idSoltrab) {
        this.idSoltrab = idSoltrab;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDocadjunt != null ? idDocadjunt.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CauDocadjuntos)) {
            return false;
        }
        CauDocadjuntos other = (CauDocadjuntos) object;
        if ((this.idDocadjunt == null && other.idDocadjunt != null) || (this.idDocadjunt != null && !this.idDocadjunt.equals(other.idDocadjunt))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sfp.model.CauDocadjuntos[ idDocadjunt=" + idDocadjunt + " ]";
    }
    
}
