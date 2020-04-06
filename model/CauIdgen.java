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
@Table(name = "CAU_IDGEN", catalog = "", schema = "CAU")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CauIdgen.findAll", query = "SELECT c FROM CauIdgen c")
    , @NamedQuery(name = "CauIdgen.findByGenName", query = "SELECT c FROM CauIdgen c WHERE c.genName = :genName")
    , @NamedQuery(name = "CauIdgen.findByGenVal", query = "SELECT c FROM CauIdgen c WHERE c.genVal = :genVal")})
public class CauIdgen implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "GEN_NAME")
    private String genName;
    @Column(name = "GEN_VAL")
    private Integer genVal;

    public CauIdgen() {
    }

    public CauIdgen(String genName) {
        this.genName = genName;
    }

    public String getGenName() {
        return genName;
    }

    public void setGenName(String genName) {
        this.genName = genName;
    }

    public Integer getGenVal() {
        return genVal;
    }

    public void setGenVal(Integer genVal) {
        this.genVal = genVal;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (genName != null ? genName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CauIdgen)) {
            return false;
        }
        CauIdgen other = (CauIdgen) object;
        if ((this.genName == null && other.genName != null) || (this.genName != null && !this.genName.equals(other.genName))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sfp.model.CauIdgen[ genName=" + genName + " ]";
    }
    
}
