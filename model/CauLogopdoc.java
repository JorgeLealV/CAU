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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jleal
 */
@Entity
@Table(name = "CAU_LOGOPDOC", catalog = "", schema = "CAU")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CauLogopdoc.findAll", query = "SELECT c FROM CauLogopdoc c")
    , @NamedQuery(name = "CauLogopdoc.findByIdOpera", query = "SELECT c FROM CauLogopdoc c WHERE c.idOpera = :idOpera")
    , @NamedQuery(name = "CauLogopdoc.findByDescOpera", query = "SELECT c FROM CauLogopdoc c WHERE c.descOpera = :descOpera")
    , @NamedQuery(name = "CauLogopdoc.findByFechaOpera", query = "SELECT c FROM CauLogopdoc c WHERE c.fechaOpera = :fechaOpera")
    , @NamedQuery(name = "CauLogopdoc.findByIdDocgen1", query = "SELECT c FROM CauLogopdoc c WHERE c.idDocgen1 = :idDocgen1")
    , @NamedQuery(name = "CauLogopdoc.findByIdDocgen2", query = "SELECT c FROM CauLogopdoc c WHERE c.idDocgen2 = :idDocgen2")
    , @NamedQuery(name = "CauLogopdoc.findByIdDocgen3", query = "SELECT c FROM CauLogopdoc c WHERE c.idDocgen3 = :idDocgen3")
    , @NamedQuery(name = "CauLogopdoc.findByIdDocgen4", query = "SELECT c FROM CauLogopdoc c WHERE c.idDocgen4 = :idDocgen4")})
public class CauLogopdoc implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_OPERA")
    private Integer idOpera;
    @Size(max = 250)
    @Column(name = "DESC_OPERA")
    private String descOpera;
    @Column(name = "FECHA_OPERA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaOpera;
    @Column(name = "ID_DOCGEN1")
    private Integer idDocgen1;
    @Column(name = "ID_DOCGEN2")
    private Integer idDocgen2;
    @Column(name = "ID_DOCGEN3")
    private Integer idDocgen3;
    @Column(name = "ID_DOCGEN4")
    private Integer idDocgen4;
    @JoinColumn(name = "ID_TIPDOC", referencedColumnName = "ID_TIPDOC")
    @ManyToOne
    private CauTipodoc idTipdoc;
    @JoinColumn(name = "ID_TRANS", referencedColumnName = "ID_TRANS")
    @ManyToOne
    private CauTransaccion idTrans;
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO")
    @ManyToOne
    private CauUsuarios idUsuario;

    public CauLogopdoc() {
    }

    public CauLogopdoc(Integer idOpera) {
        this.idOpera = idOpera;
    }

    public Integer getIdOpera() {
        return idOpera;
    }

    public void setIdOpera(Integer idOpera) {
        this.idOpera = idOpera;
    }

    public String getDescOpera() {
        return descOpera;
    }

    public void setDescOpera(String descOpera) {
        this.descOpera = descOpera;
    }

    public Date getFechaOpera() {
        return fechaOpera;
    }

    public void setFechaOpera(Date fechaOpera) {
        this.fechaOpera = fechaOpera;
    }

    public Integer getIdDocgen1() {
        return idDocgen1;
    }

    public void setIdDocgen1(Integer idDocgen1) {
        this.idDocgen1 = idDocgen1;
    }

    public Integer getIdDocgen2() {
        return idDocgen2;
    }

    public void setIdDocgen2(Integer idDocgen2) {
        this.idDocgen2 = idDocgen2;
    }

    public Integer getIdDocgen3() {
        return idDocgen3;
    }

    public void setIdDocgen3(Integer idDocgen3) {
        this.idDocgen3 = idDocgen3;
    }

    public Integer getIdDocgen4() {
        return idDocgen4;
    }

    public void setIdDocgen4(Integer idDocgen4) {
        this.idDocgen4 = idDocgen4;
    }

    public CauTipodoc getIdTipdoc() {
        return idTipdoc;
    }

    public void setIdTipdoc(CauTipodoc idTipdoc) {
        this.idTipdoc = idTipdoc;
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
        hash += (idOpera != null ? idOpera.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CauLogopdoc)) {
            return false;
        }
        CauLogopdoc other = (CauLogopdoc) object;
        if ((this.idOpera == null && other.idOpera != null) || (this.idOpera != null && !this.idOpera.equals(other.idOpera))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sfp.model.CauLogopdoc[ idOpera=" + idOpera + " ]";
    }
    
}
