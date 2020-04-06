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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jleal
 */
@Entity
@Table(name = "CAU_MENU", catalog = "", schema = "CAU")
@TableGenerator(name="Menu_Gen", table = "CAU_IDGEN", pkColumnName = "GEN_NAME", 
                  valueColumnName = "GEN_VAL", pkColumnValue = "Menu_Gen", allocationSize=1)

@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CauMenu.findAll", query = "SELECT c FROM CauMenu c")
    , @NamedQuery(name = "CauMenu.findByIdMenu", query = "SELECT c FROM CauMenu c WHERE c.idMenu = :idMenu")
    , @NamedQuery(name = "CauMenu.findByDescmenu", query = "SELECT c FROM CauMenu c WHERE c.descmenu = :descmenu")
    , @NamedQuery(name = "CauMenu.findByAyudamenu", query = "SELECT c FROM CauMenu c WHERE c.ayudamenu = :ayudamenu")
    , @NamedQuery(name = "CauMenu.findByLabel", query = "SELECT c FROM CauMenu c WHERE c.label = :label")
    , @NamedQuery(name = "CauMenu.findByIcons", query = "SELECT c FROM CauMenu c WHERE c.icons = :icons")
    , @NamedQuery(name = "CauMenu.findByStyles", query = "SELECT c FROM CauMenu c WHERE c.styles = :styles")
    , @NamedQuery(name = "CauMenu.findByStyleclass", query = "SELECT c FROM CauMenu c WHERE c.styleclass = :styleclass")
    , @NamedQuery(name = "CauMenu.findByRendered", query = "SELECT c FROM CauMenu c WHERE c.rendered = :rendered")
    , @NamedQuery(name = "CauMenu.findByExpanded", query = "SELECT c FROM CauMenu c WHERE c.expanded = :expanded")})
public class CauMenu implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator="Menu_Gen")
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_MENU")
    private Integer idMenu;
    @Size(max = 100)
    @Column(name = "DESCMENU")
    private String descmenu;
    @Size(max = 120)
    @Column(name = "AYUDAMENU")
    private String ayudamenu;
    @Size(max = 100)
    @Column(name = "LABEL")
    private String label;
    @Size(max = 100)
    @Column(name = "ICONS")
    private String icons;
    @Size(max = 100)
    @Column(name = "STYLES")
    private String styles;
    @Size(max = 100)
    @Column(name = "STYLECLASS")
    private String styleclass;
    @Size(max = 20)
    @Column(name = "RENDERED")
    private String rendered;
    @Size(max = 20)
    @Column(name = "EXPANDED")
    private String expanded;
    @OneToMany(mappedBy = "idPadre")
    private Collection<CauMenu> cauMenuCollection;
    @JoinColumn(name = "ID_PADRE", referencedColumnName = "ID_MENU")
    @ManyToOne
    private CauMenu idPadre;
    @JoinColumn(name = "ID_ROL", referencedColumnName = "ID_ROL")
    @ManyToOne
    private CauRol idRol;
    @JoinColumn(name = "ID_TIPMENU", referencedColumnName = "ID_TIPMENU")
    @ManyToOne
    private CauTipomenu idTipmenu;
    @JoinColumn(name = "ID_TRANS", referencedColumnName = "ID_TRANS")
    @ManyToOne
    private CauTransaccion idTrans;
    @OneToMany(mappedBy = "idMenu")
    private Collection<CauMenuitem> cauMenuitemCollection;

    public CauMenu() {
    }

    public CauMenu(Integer idMenu) {
        this.idMenu = idMenu;
    }

    public Integer getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(Integer idMenu) {
        this.idMenu = idMenu;
    }

    public String getDescmenu() {
        return descmenu;
    }

    public void setDescmenu(String descmenu) {
        this.descmenu = descmenu;
    }

    public String getAyudamenu() {
        return ayudamenu;
    }

    public void setAyudamenu(String ayudamenu) {
        this.ayudamenu = ayudamenu;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getIcons() {
        return icons;
    }

    public void setIcons(String icons) {
        this.icons = icons;
    }

    public String getStyles() {
        return styles;
    }

    public void setStyles(String styles) {
        this.styles = styles;
    }

    public String getStyleclass() {
        return styleclass;
    }

    public void setStyleclass(String styleclass) {
        this.styleclass = styleclass;
    }

    public String getRendered() {
        return rendered;
    }

    public void setRendered(String rendered) {
        this.rendered = rendered;
    }

    public String getExpanded() {
        return expanded;
    }

    public void setExpanded(String expanded) {
        this.expanded = expanded;
    }

    @XmlTransient
    public Collection<CauMenu> getCauMenuCollection() {
        return cauMenuCollection;
    }

    public void setCauMenuCollection(Collection<CauMenu> cauMenuCollection) {
        this.cauMenuCollection = cauMenuCollection;
    }

    public CauMenu getIdPadre() {
        return idPadre;
    }

    public void setIdPadre(CauMenu idPadre) {
        this.idPadre = idPadre;
    }

    public CauRol getIdRol() {
        return idRol;
    }

    public void setIdRol(CauRol idRol) {
        this.idRol = idRol;
    }

    public CauTipomenu getIdTipmenu() {
        return idTipmenu;
    }

    public void setIdTipmenu(CauTipomenu idTipmenu) {
        this.idTipmenu = idTipmenu;
    }

    public CauTransaccion getIdTrans() {
        return idTrans;
    }

    public void setIdTrans(CauTransaccion idTrans) {
        this.idTrans = idTrans;
    }

    @XmlTransient
    public Collection<CauMenuitem> getCauMenuitemCollection() {
        return cauMenuitemCollection;
    }

    public void setCauMenuitemCollection(Collection<CauMenuitem> cauMenuitemCollection) {
        this.cauMenuitemCollection = cauMenuitemCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMenu != null ? idMenu.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CauMenu)) {
            return false;
        }
        CauMenu other = (CauMenu) object;
        if ((this.idMenu == null && other.idMenu != null) || (this.idMenu != null && !this.idMenu.equals(other.idMenu))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sfp.model.CauMenu[ idMenu=" + idMenu + " ]";
    }
    
}
