/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfp.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jleal
 */
@Entity
@Table(name = "CAU_REGLASWF", catalog = "", schema = "CAU")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CauReglaswf.findAll", query = "SELECT c FROM CauReglaswf c")
    , @NamedQuery(name = "CauReglaswf.findByIdRegwf", query = "SELECT c FROM CauReglaswf c WHERE c.idRegwf = :idRegwf")
    , @NamedQuery(name = "CauReglaswf.findByIdNodo", query = "SELECT c FROM CauReglaswf c WHERE c.idNodo = :idNodo")
    , @NamedQuery(name = "CauReglaswf.findByIdNodopadre", query = "SELECT c FROM CauReglaswf c WHERE c.idNodopadre = :idNodopadre")
    , @NamedQuery(name = "CauReglaswf.findByIdNivel", query = "SELECT c FROM CauReglaswf c WHERE c.idNivel = :idNivel")})
public class CauReglaswf implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_REGWF")
    private Integer idRegwf;
    @Column(name = "ID_NODO")
    private BigInteger idNodo;
    @Column(name = "ID_NODOPADRE")
    private BigInteger idNodopadre;
    @Column(name = "ID_NIVEL")
    private Integer idNivel;
    @JoinColumn(name = "ID_ESTATWF", referencedColumnName = "ID_ESTATWF")
    @ManyToOne
    private CauEsttwf idEstatwf;
    @JoinColumn(name = "ID_REALCION", referencedColumnName = "ID_REALCION")
    @ManyToOne
    private CauTiporelacon idRealcion;
    @JoinColumn(name = "ID_TRAASOCWF", referencedColumnName = "ID_TRAASOCWF")
    @ManyToOne
    private CauTraasigwf idTraasocwf;
    @OneToMany(mappedBy = "idRegwf")
    private Collection<CauTrasoldetwf> cauTrasoldetwfCollection;

    public CauReglaswf() {
    }

    public CauReglaswf(Integer idRegwf) {
        this.idRegwf = idRegwf;
    }

    public Integer getIdRegwf() {
        return idRegwf;
    }

    public void setIdRegwf(Integer idRegwf) {
        this.idRegwf = idRegwf;
    }

    public BigInteger getIdNodo() {
        return idNodo;
    }

    public void setIdNodo(BigInteger idNodo) {
        this.idNodo = idNodo;
    }

    public BigInteger getIdNodopadre() {
        return idNodopadre;
    }

    public void setIdNodopadre(BigInteger idNodopadre) {
        this.idNodopadre = idNodopadre;
    }

    public Integer getIdNivel() {
        return idNivel;
    }

    public void setIdNivel(Integer idNivel) {
        this.idNivel = idNivel;
    }

    public CauEsttwf getIdEstatwf() {
        return idEstatwf;
    }

    public void setIdEstatwf(CauEsttwf idEstatwf) {
        this.idEstatwf = idEstatwf;
    }

    public CauTiporelacon getIdRealcion() {
        return idRealcion;
    }

    public void setIdRealcion(CauTiporelacon idRealcion) {
        this.idRealcion = idRealcion;
    }

    public CauTraasigwf getIdTraasocwf() {
        return idTraasocwf;
    }

    public void setIdTraasocwf(CauTraasigwf idTraasocwf) {
        this.idTraasocwf = idTraasocwf;
    }

    @XmlTransient
    public Collection<CauTrasoldetwf> getCauTrasoldetwfCollection() {
        return cauTrasoldetwfCollection;
    }

    public void setCauTrasoldetwfCollection(Collection<CauTrasoldetwf> cauTrasoldetwfCollection) {
        this.cauTrasoldetwfCollection = cauTrasoldetwfCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRegwf != null ? idRegwf.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CauReglaswf)) {
            return false;
        }
        CauReglaswf other = (CauReglaswf) object;
        if ((this.idRegwf == null && other.idRegwf != null) || (this.idRegwf != null && !this.idRegwf.equals(other.idRegwf))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sfp.model.CauReglaswf[ idRegwf=" + idRegwf + " ]";
    }
    
}
