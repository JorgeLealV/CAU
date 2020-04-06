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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jleal
 */
@Entity
@Table(name = "CAU_ROLXTRANS", catalog = "", schema = "CAU")
@TableGenerator(name="Rolxt_Gen", table = "CAU_IDGEN", pkColumnName = "GEN_NAME", 
                  valueColumnName = "GEN_VAL", pkColumnValue = "Rolxt_Gen", allocationSize=1)

@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CauRolxtrans.findAll", query = "SELECT c FROM CauRolxtrans c")
    , @NamedQuery(name = "CauRolxtrans.findByIdRolxtrans", query = "SELECT c FROM CauRolxtrans c WHERE c.idRolxtrans = :idRolxtrans")})
public class CauRolxtrans implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator="Rolxt_Gen")
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_ROLXTRANS")
    private Integer idRolxtrans;
    @OneToMany(mappedBy = "idRolxtrans")
    private Collection<CauRolxtranxpar> cauRolxtranxparCollection;
    @JoinColumn(name = "ID_ROL", referencedColumnName = "ID_ROL")
    @ManyToOne
    private CauRol idRol;
    @JoinColumn(name = "ID_TRANS", referencedColumnName = "ID_TRANS")
    @ManyToOne
    private CauTransaccion idTrans;

    public CauRolxtrans() {
    }

    public CauRolxtrans(Integer idRolxtrans) {
        this.idRolxtrans = idRolxtrans;
    }

    public Integer getIdRolxtrans() {
        return idRolxtrans;
    }

    public void setIdRolxtrans(Integer idRolxtrans) {
        this.idRolxtrans = idRolxtrans;
    }

    @XmlTransient
    public Collection<CauRolxtranxpar> getCauRolxtranxparCollection() {
        return cauRolxtranxparCollection;
    }

    public void setCauRolxtranxparCollection(Collection<CauRolxtranxpar> cauRolxtranxparCollection) {
        this.cauRolxtranxparCollection = cauRolxtranxparCollection;
    }

    public CauRol getIdRol() {
        return idRol;
    }

    public void setIdRol(CauRol idRol) {
        this.idRol = idRol;
    }

    public CauTransaccion getIdTrans() {
        return idTrans;
    }

    public void setIdTrans(CauTransaccion idTrans) {
        this.idTrans = idTrans;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRolxtrans != null ? idRolxtrans.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CauRolxtrans)) {
            return false;
        }
        CauRolxtrans other = (CauRolxtrans) object;
        if ((this.idRolxtrans == null && other.idRolxtrans != null) || (this.idRolxtrans != null && !this.idRolxtrans.equals(other.idRolxtrans))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sfp.model.CauRolxtrans[ idRolxtrans=" + idRolxtrans + " ]";
    }
    
}
