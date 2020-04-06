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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jleal
 */
@Entity
@Table(name = "CAU_LOGOPER", catalog = "", schema = "CAU")
@TableGenerator(name="LogOper_Gen", table = "CAU_IDGEN", pkColumnName = "GEN_NAME", 
                  valueColumnName = "GEN_VAL", pkColumnValue = "LogOper_Gen", allocationSize=1)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CauLogoper.findAll", query = "SELECT c FROM CauLogoper c")
    , @NamedQuery(name = "CauLogoper.findByIdOper", query = "SELECT c FROM CauLogoper c WHERE c.idOper = :idOper")
    , @NamedQuery(name = "CauLogoper.findByFechaoper", query = "SELECT c FROM CauLogoper c WHERE c.fechaoper = :fechaoper")
    , @NamedQuery(name = "CauLogoper.findByDescOperacion1", query = "SELECT c FROM CauLogoper c WHERE c.descOperacion1 = :descOperacion1")
    , @NamedQuery(name = "CauLogoper.findById01", query = "SELECT c FROM CauLogoper c WHERE c.id01 = :id01")
    , @NamedQuery(name = "CauLogoper.findById02", query = "SELECT c FROM CauLogoper c WHERE c.id02 = :id02")
    , @NamedQuery(name = "CauLogoper.findById03", query = "SELECT c FROM CauLogoper c WHERE c.id03 = :id03")
    , @NamedQuery(name = "CauLogoper.findByDescOperacion2", query = "SELECT c FROM CauLogoper c WHERE c.descOperacion2 = :descOperacion2")
    , @NamedQuery(name = "CauLogoper.findByDescOperacion3", query = "SELECT c FROM CauLogoper c WHERE c.descOperacion3 = :descOperacion3")
    , @NamedQuery(name = "CauLogoper.findBySecuencia", query = "SELECT c FROM CauLogoper c WHERE c.secuencia = :secuencia")})
public class CauLogoper implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator="LogOper_Gen")
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_OPER")
    private Integer idOper;
    @Column(name = "FECHAOPER")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaoper;
    @Size(max = 500)
    @Column(name = "DESC_OPERACION1")
    private String descOperacion1;
    @Size(max = 25)
    @Column(name = "ID01")
    private String id01;
    @Size(max = 25)
    @Column(name = "ID02")
    private String id02;
    @Size(max = 25)
    @Column(name = "ID03")
    private String id03;
    @Size(max = 500)
    @Column(name = "DESC_OPERACION2")
    private String descOperacion2;
    @Size(max = 500)
    @Column(name = "DESC_OPERACION3")
    private String descOperacion3;
    @Column(name = "SECUENCIA")
    private Integer secuencia;
    @JoinColumn(name = "ID_MESASERV", referencedColumnName = "ID_MESASERV")
    @ManyToOne
    private CauMesaservicio idMesaserv;
    @JoinColumn(name = "ID_OPERACION", referencedColumnName = "ID_OPERACION")
    @ManyToOne
    private CauTipooper idOperacion;
    @JoinColumn(name = "ID_TRANS", referencedColumnName = "ID_TRANS")
    @ManyToOne
    private CauTransaccion idTrans;
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO")
    @ManyToOne
    private CauUsuarios idUsuario;

    public CauLogoper() {
    }

    public CauLogoper(Integer idOper) {
        this.idOper = idOper;
    }

    public Integer getIdOper() {
        return idOper;
    }

    public void setIdOper(Integer idOper) {
        this.idOper = idOper;
    }

    public Date getFechaoper() {
        return fechaoper;
    }

    public void setFechaoper(Date fechaoper) {
        this.fechaoper = fechaoper;
    }

    public String getDescOperacion1() {
        return descOperacion1;
    }

    public void setDescOperacion1(String descOperacion1) {
        this.descOperacion1 = descOperacion1;
    }

    public String getId01() {
        return id01;
    }

    public void setId01(String id01) {
        this.id01 = id01;
    }

    public String getId02() {
        return id02;
    }

    public void setId02(String id02) {
        this.id02 = id02;
    }

    public String getId03() {
        return id03;
    }

    public void setId03(String id03) {
        this.id03 = id03;
    }

    public String getDescOperacion2() {
        return descOperacion2;
    }

    public void setDescOperacion2(String descOperacion2) {
        this.descOperacion2 = descOperacion2;
    }

    public String getDescOperacion3() {
        return descOperacion3;
    }

    public void setDescOperacion3(String descOperacion3) {
        this.descOperacion3 = descOperacion3;
    }

    public Integer getSecuencia() {
        return secuencia;
    }

    public void setSecuencia(Integer secuencia) {
        this.secuencia = secuencia;
    }

    public CauMesaservicio getIdMesaserv() {
        return idMesaserv;
    }

    public void setIdMesaserv(CauMesaservicio idMesaserv) {
        this.idMesaserv = idMesaserv;
    }

    public CauTipooper getIdOperacion() {
        return idOperacion;
    }

    public void setIdOperacion(CauTipooper idOperacion) {
        this.idOperacion = idOperacion;
    }

    public CauTransaccion getIdTrans() {
        return idTrans;
    }

    public void setIdTrans(CauTransaccion idTrans) {
        this.idTrans = idTrans;
    }

    public CauUsuarios getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(CauUsuarios idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOper != null ? idOper.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CauLogoper)) {
            return false;
        }
        CauLogoper other = (CauLogoper) object;
        if ((this.idOper == null && other.idOper != null) || (this.idOper != null && !this.idOper.equals(other.idOper))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sfp.model.CauLogoper[ idOper=" + idOper + " ]";
    }
    
}
