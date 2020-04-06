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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "CAU_MENUITEM", catalog = "", schema = "CAU")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CauMenuitem.findAll", query = "SELECT c FROM CauMenuitem c")
    , @NamedQuery(name = "CauMenuitem.findByIdMenuitem", query = "SELECT c FROM CauMenuitem c WHERE c.idMenuitem = :idMenuitem")
    , @NamedQuery(name = "CauMenuitem.findByValue", query = "SELECT c FROM CauMenuitem c WHERE c.value = :value")
    , @NamedQuery(name = "CauMenuitem.findByActionlistener", query = "SELECT c FROM CauMenuitem c WHERE c.actionlistener = :actionlistener")
    , @NamedQuery(name = "CauMenuitem.findByAction", query = "SELECT c FROM CauMenuitem c WHERE c.action = :action")
    , @NamedQuery(name = "CauMenuitem.findByInmediate", query = "SELECT c FROM CauMenuitem c WHERE c.inmediate = :inmediate")
    , @NamedQuery(name = "CauMenuitem.findByUrl", query = "SELECT c FROM CauMenuitem c WHERE c.url = :url")
    , @NamedQuery(name = "CauMenuitem.findByTarget", query = "SELECT c FROM CauMenuitem c WHERE c.target = :target")
    , @NamedQuery(name = "CauMenuitem.findByOnclick", query = "SELECT c FROM CauMenuitem c WHERE c.onclick = :onclick")
    , @NamedQuery(name = "CauMenuitem.findByAsync", query = "SELECT c FROM CauMenuitem c WHERE c.async = :async")
    , @NamedQuery(name = "CauMenuitem.findByAjax", query = "SELECT c FROM CauMenuitem c WHERE c.ajax = :ajax")
    , @NamedQuery(name = "CauMenuitem.findByTitle", query = "SELECT c FROM CauMenuitem c WHERE c.title = :title")
    , @NamedQuery(name = "CauMenuitem.findByDisabled", query = "SELECT c FROM CauMenuitem c WHERE c.disabled = :disabled")})
public class CauMenuitem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_MENUITEM")
    private Integer idMenuitem;
    @Size(max = 100)
    @Column(name = "VALUE")
    private String value;
    @Size(max = 100)
    @Column(name = "ACTIONLISTENER")
    private String actionlistener;
    @Size(max = 100)
    @Column(name = "ACTION")
    private String action;
    @Size(max = 20)
    @Column(name = "INMEDIATE")
    private String inmediate;
    @Size(max = 200)
    @Column(name = "URL")
    private String url;
    @Size(max = 100)
    @Column(name = "TARGET")
    private String target;
    @Size(max = 100)
    @Column(name = "ONCLICK")
    private String onclick;
    @Size(max = 20)
    @Column(name = "ASYNC")
    private String async;
    @Size(max = 20)
    @Column(name = "AJAX")
    private String ajax;
    @Size(max = 80)
    @Column(name = "TITLE")
    private String title;
    @Size(max = 20)
    @Column(name = "DISABLED")
    private String disabled;
    @JoinColumn(name = "ID_MENU", referencedColumnName = "ID_MENU")
    @ManyToOne
    private CauMenu idMenu;

    public CauMenuitem() {
    }

    public CauMenuitem(Integer idMenuitem) {
        this.idMenuitem = idMenuitem;
    }

    public Integer getIdMenuitem() {
        return idMenuitem;
    }

    public void setIdMenuitem(Integer idMenuitem) {
        this.idMenuitem = idMenuitem;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getActionlistener() {
        return actionlistener;
    }

    public void setActionlistener(String actionlistener) {
        this.actionlistener = actionlistener;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getInmediate() {
        return inmediate;
    }

    public void setInmediate(String inmediate) {
        this.inmediate = inmediate;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getOnclick() {
        return onclick;
    }

    public void setOnclick(String onclick) {
        this.onclick = onclick;
    }

    public String getAsync() {
        return async;
    }

    public void setAsync(String async) {
        this.async = async;
    }

    public String getAjax() {
        return ajax;
    }

    public void setAjax(String ajax) {
        this.ajax = ajax;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDisabled() {
        return disabled;
    }

    public void setDisabled(String disabled) {
        this.disabled = disabled;
    }

    public CauMenu getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(CauMenu idMenu) {
        this.idMenu = idMenu;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMenuitem != null ? idMenuitem.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CauMenuitem)) {
            return false;
        }
        CauMenuitem other = (CauMenuitem) object;
        if ((this.idMenuitem == null && other.idMenuitem != null) || (this.idMenuitem != null && !this.idMenuitem.equals(other.idMenuitem))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sfp.model.CauMenuitem[ idMenuitem=" + idMenuitem + " ]";
    }
    
}
