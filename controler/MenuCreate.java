/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfp.controler;

import com.sfp.cau.CauMenuAmp;
import com.sfp.ejb.CauMenuFacade;
import com.sfp.ejb.CauMenuFacadeLocal;
import com.sfp.ejb.CauUsuariosFacadeLocal;
import com.sfp.menu.ArbolN;
import com.sfp.menu.Menu;
import com.sfp.menu.NodoNario;
import com.sfp.model.CauMenu;
import com.sfp.model.CauUsuarios;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.MenuElement;

/**
 *
 * @author jleal
 */
@Named(value = "menuCreate")
//@Dependent
//@ViewScoped
@SessionScoped 
public class MenuCreate implements Serializable{

    /**
     * Creates a new instance of MenuCreate
     */
    private MenuModel model;
    private DefaultSubMenu menuarmado;
    private boolean creausua;
    private boolean creadefault;

    public MenuModel getModel() {
        CrearMenu(); 
        return model;
    }

    public void setModel(MenuModel model) {
        this.model = model;
    }
    private List<Menu> Menus;
    
    @EJB
    private CauMenuFacadeLocal EJBMenu;
    
    @EJB
    private CauUsuariosFacadeLocal EJBUsuario;
    
    @Inject private Login01Controler Login01Controler1;

    @PostConstruct
    public void init() {      
        Menus = new ArrayList<>();
        creausua = false;
        creadefault = false;
        model = new DefaultMenuModel();
        CrearMenu();
    }
    
    public MenuCreate() {
        
    }
       
//     private String redirect(){
//        return "/PrimeFaces/Solicitudes/index?faces-redirect=true";
//    }
    
    
    private void CrearMenu() {
        Menu cc;
        CauUsuarios Usua = (CauUsuarios) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        String  EnvioMesa=Login01Controler1.getEnvioMesa();
        List<Object> Menues = null;
        if (Usua != null) {
           Menues = EJBMenu.MenuUsuario(Usua.getClaveusua(),Login01Controler1.getMesasServicio_Act()); 
        }
        if (Menues == null) {
           creadefault = false; 
        }
        if (Usua != null && !creausua && EnvioMesa.equals("False") && Menues != null ) {
            creausua = true;
            // Se crea menu 
            //Menus = new ArrayList<>();
            //cc = new Menu("1", "0", "AS", "url1", "Desc=1");
            //Menus.add(cc);
            
            ArbolN ObjArbol = new ArbolN();
            //Realiza consulta de menus dependiendo del tipo de usuario
            //EJBMenu = new CauMenuFacade();
            String idp;
            // esta consulta es la importante se establece el tipo de menu en funcion del usuario
            // el usuario tiene asignado un nmenu en especial pero ese menu tiene asignado diferentes 
            // parametros ojo ojo hay que revisar
           // List<Object> Menues = EJBMenu.MenuUsuario(Usua.getClaveusua());
            CauMenuAmp bbb;            
            for (Object bb : Menues) {
                bbb = new CauMenuAmp(bb);
                String cadena = bbb.getCodigotrans();
                CauMenu pp = bbb.getCaumenu();
                String TipoMenu = "";
                switch(pp.getIdTipmenu().getIdTipmenu()){
                    case 1 :
                    case 2 : TipoMenu = "AS";
                             break;
                    case 3 : TipoMenu = "I";
                             break;
                }      
                String Padre, hijo, DescMenu;
                if (pp.getIdPadre() == null){
                    Padre = "0";
                }
                else {
                    Padre =  pp.getIdPadre().getIdMenu().toString(); 
                }
               
                hijo  =  pp.getIdMenu().toString();
                DescMenu = pp.getDescmenu();
                
                cc = new Menu(pp.getIdMenu().toString(), Padre, TipoMenu, cadena, pp.getDescmenu());
                NodoNario nodoHijo = new NodoNario(cc);
                ObjArbol.insertar(ObjArbol.getRaiz(), nodoHijo);
            }       
            
            
            menuarmado = ObjArbol.getModel();
            model.getElements().clear();
            for (MenuElement menu : menuarmado.getElements()){
               model.addElement(menu);
            }
            // model.addElement(menuarmado);
            idp = "paso1";
        } else if (!creadefault){
            Menus = new ArrayList<>();
            creadefault = true;
  
            ArbolN ObjArbol = new ArbolN();
            // Realiza consulta de menus dependiendo del tipo de usuario
            // EJBMenu = new CauMenuFacade();
            String idp;
            Menues = EJBMenu.MenuAyuda();
            CauMenuAmp bbb;            
            for (Object bb : Menues) {
                bbb = new CauMenuAmp(bb);
                String cadena = bbb.getCodigotrans();
                CauMenu pp = bbb.getCaumenu();
                String TipoMenu = "";
                switch(pp.getIdTipmenu().getIdTipmenu()){
                    case 1 :
                    case 2 : TipoMenu = "AS";
                             break;
                    case 3 : TipoMenu = "I";
                             break;
                }      
                String Padre, hijo, DescMenu;
                if (pp.getIdPadre() == null){
                    Padre = "0";
                }
                else {
                    Padre =  pp.getIdPadre().getIdMenu().toString(); 
                }
               
                hijo  =  pp.getIdMenu().toString();
                DescMenu = pp.getDescmenu();
                
                cc = new Menu(pp.getIdMenu().toString(), Padre, TipoMenu, cadena, pp.getDescmenu());
                NodoNario nodoHijo = new NodoNario(cc);
                ObjArbol.insertar(ObjArbol.getRaiz(), nodoHijo);
            }          
            
//            for (Menu ff : Menus) {
//                idp = ff.getHijo();
//                NodoNario nodoHijo = new NodoNario(ff);
//                ObjArbol.insertar(ObjArbol.getRaiz(), nodoHijo);
//            }
            //model = ObjArbol.getModel();
            menuarmado = ObjArbol.getModel(); 
            //if (model.getElements().)
            model.getElements().clear();
            for (MenuElement menu : menuarmado.getElements()){
               model.addElement(menu);
            }            
            idp = "paso1";
            //model = null;            
        }
    }
}
