/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfp.menu;

import java.util.Objects;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuItem;



/**
 *
 * @author marco
 */
public class Menu {

    String hijo;
    String padre;
    String tipomenu;
    String Url;
    String Desc;
    //DefaultMenuModel raiz;
    DefaultSubMenu raiz;
    DefaultSubMenu submenu;
    DefaultMenuItem menuitem;
    //MenuItem menuitem;
    
    
    
    //public Menu(DefaultMenuModel raizp){
    public Menu(DefaultSubMenu raizp){
        raiz = raizp;
    }
    public Menu(String hijop,String padrep, String tipomenup,String Urlp){
        this(hijop,padrep,tipomenup,Urlp,"");
    }
    
    public Menu(String hijo,String padre, String tipomenu,String Url,String Desc){
        this.hijo=hijo;
        this.padre=padre;
        this.tipomenu=tipomenu;
        this.Url=Url;
        this.Desc=Desc;
        if (this.tipomenu.equals("AS")){            
            submenu=new DefaultSubMenu(Desc);
            submenu.setId(hijo);
            submenu.setIcon("ui-icon-close");
            
        }
        else if (tipomenu.equals("I")){            
            menuitem=new DefaultMenuItem(Desc);
            menuitem.setId(hijo);
            menuitem.setIcon("ui-icon-close");
            //menuitem.setUrl(Url);   
            menuitem.setOutcome(Url);
            //menuitem.setAjax(false);
        }
        else if (tipomenu.equals("R")){
           // raiz=new DefaultMenuModel();
           raiz=new DefaultSubMenu(Desc);
        }
        else {
            // thru exception
        }
    }
    
    @Override
    public String toString() {
         return String.format("%s-%s-%s-URL-%s%n", tipomenu, hijo, padre,  Url);
    }
    public String getHijo() {
        return hijo;
    }

    public void setHijo(String hijo) {
        this.hijo = hijo;
    }

    public String getPadre() {
        return padre;
    }

    public void setPadre(String padre) {
        this.padre = padre;
    }

    public String getTipomenu() {
        return tipomenu;
    }

    public void setTipomenu(String tipomenu) {
        this.tipomenu = tipomenu;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String Url) {
        this.Url = Url;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String Desc) {
        this.Desc = Desc;
    }

    public DefaultSubMenu getRaiz() {
        return raiz;
    }

    public void setRaiz(DefaultSubMenu raiz) {
        this.raiz = raiz;
    }

    public DefaultSubMenu getSubmenu() {
        return submenu;
    }

    public void setSubmenu(DefaultSubMenu submenu) {
        this.submenu = submenu;
    }

    public DefaultMenuItem getMenuitem() {
        return menuitem;
    }

    public void setMenuitem(DefaultMenuItem menuitem) {
        this.menuitem = menuitem;
    }
    
   
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.hijo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Menu other = (Menu) obj;
        if (!Objects.equals(this.hijo, other.hijo)) {
            return false;
        }
        return true;
    }

    

}
