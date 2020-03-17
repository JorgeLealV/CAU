/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfp.controler;

import com.sfp.ejb.CauDepjefeFacadeLocal;
import com.sfp.ejb.CauMenuFacadeLocal;
import com.sfp.ejb.CauMesaservicioFacadeLocal;
import com.sfp.ejb.CauRolFacadeLocal;
import com.sfp.ejb.CauRolbaseFacadeLocal;
import com.sfp.ejb.CauRolbastranFacadeLocal;
import com.sfp.ejb.CauRolxtransFacadeLocal;
import com.sfp.ejb.CauRolxtranxparFacadeLocal;
import com.sfp.ejb.CauRolxusuarioFacadeLocal;
import com.sfp.ejb.CauTectipotrabFacadeLocal;
import com.sfp.ejb.CauTipomenuFacadeLocal;
import com.sfp.ejb.CauTranxparFacadeLocal;
import com.sfp.ejb.CauUsuariosFacadeLocal;
import com.sfp.ejb.CauUsuariotipoFacadeLocal;
import com.sfp.model.CauDepjefe;
import com.sfp.model.CauMenu;
import com.sfp.model.CauMesaservicio;
import com.sfp.model.CauRol;
import com.sfp.model.CauRolbase;
import com.sfp.model.CauRolbastran;
import com.sfp.model.CauRolxtrans;
import com.sfp.model.CauRolxtranxpar;
import com.sfp.model.CauRolxusuario;
import com.sfp.model.CauTectipotrab;
import com.sfp.model.CauTipomenu;
import com.sfp.model.CauTranxpar;
import com.sfp.model.CauUsuarios;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author jleal
 */
@Named(value = "roles")
@ViewScoped
public class Roles implements Serializable{

   @EJB
    private CauUsuariotipoFacadeLocal EJBUsuariotipo; 
   
   @EJB
   private CauRolbaseFacadeLocal EJBRolBase; 
   
   @EJB
    private CauUsuariosFacadeLocal EJBUsuario;
   
   @EJB
   private CauRolxusuarioFacadeLocal EJBRolxusuario;
   
   @EJB
   private CauRolxtransFacadeLocal EJBRolxtrans;
   
   @EJB
   private CauRolxtranxparFacadeLocal EJBRolxtransxpar;
   
   @EJB
   private CauRolFacadeLocal EJBRol;
          
   @EJB
   private CauRolbastranFacadeLocal EJBRolbastran;
   
   @EJB
   private CauRolxtransFacadeLocal EJBRolxtran;
   
   @EJB
   private  CauMesaservicioFacadeLocal EJBMesaservicio;
   
   @EJB
   private  CauDepjefeFacadeLocal EJBDepjefe;
   
   @EJB
   private  CauTectipotrabFacadeLocal EJBTectipotrab;
   
   @EJB
   private  CauTranxparFacadeLocal EJBCauTranxpar;
   
   @EJB
   private  CauMenuFacadeLocal EJBMenu;
   
   @EJB
   private  CauTipomenuFacadeLocal EJBTipomenu;
   
   private int tipousuario;
   private int rolbase;
   private CauUsuarios Usua;
   private int idrol;
   private CauRol Caurolx;
   private CauRolxusuario CauRolxusuariox;
   private CauRolxtrans CauRolxtransx ;
   
   private Collection<CauRolbase> Lista_BaseRolbase;
   private Collection<CauRolbastran> Lista_BaseTransBase;
   private String usuario;
   private String contraseña;
   private CauUsuarios usuariox;
   private CauMenu menux;
   private List<CauRolxusuario> Lista_RolUsuario;
   private List<CauRolxtrans> Lista_Rolxtrans;
   private List<CauRolxtranxpar> Lista_Rolxtranxpar;
   private List<CauMesaservicio> Lista_Mesaserv;
   private List<CauDepjefe> Lista_DepJefe;
   private List<CauTectipotrab> Lista_Tectipotrab;
   private List<CauTranxpar> List_Tranxpar;
   private List<CauMenu> List_Menu;
   
   
   
    private String MesasServicio_Act = null;
    private String Departamentos_Act = null;
    private String Servicios_Act = null;
    private String Boton_Act = null;
    private String Requisitos_Act = null;

   
    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
   
    public Roles() {
    }
    
    @PostConstruct
    private void init(){
       Usua = (CauUsuarios) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario"); 
       FacesContext facesContext = FacesContext.getCurrentInstance();
       Map params = facesContext.getExternalContext().getRequestParameterMap();
       usuariox = new CauUsuarios();
              
   }  
   
   public void CrearRol() throws ParseException{
    // 1.- Determinar l tipo de usuario en variable tipousuario
     TipoUsuario();  
    // 2.- Obtener las transacciones base a las que tiene acceso conforme al tipo de usuario:
     TransBase();
    // 3.- Determinar el ID_Rol Asignado al usuario   
     RolUsuAct();
     // 4. Se obtienen todos los valores de la Lista_BaseTransBase  obtenida en el paso 2) 
     // y para cada elemento se agrega un registro en la tabla CAU_RolXTrans, con el campo 
     // ID_Trans y el campo ID_Rol obtenido en el paso 3) 
     ListTransBase();
     // 5.	Crear los valores para llenar la tabla CAU_RolXTranXPar
     TablaCauRolxTranxPart();
      //6. Crear los valores de la tabla CAU_RolXTranXPar
     TablaCauRolxTranxPart2();
     //7.- Crear Menu para el rol en cuestión
     Menubase_rol();
   } 
   
   public void BorrarUsuarioTra(){
      String Consulta;
    //      usuariox.setClaveusua(usuario);
    //      usuariox.setContrasena(contraseña);       
    //      Usua = EJBUsuario.iniciarSession(usuariox);
      
      TipoUsuario();
      
      Consulta = "Select c from CauRolxusuario c"
              + " c.idUsuario.idUsuario =  ?1";
      // a. Consultar la tabla CAU_RolXUsuario para obtener los ID_Rol aignado 
      Lista_RolUsuario = EJBRolxusuario.findxRango(Usua.getClaveusua());
     
      // b. Si la consulta anterior tiene resultados entonces 
      if (Lista_RolUsuario.isEmpty() == false)     { 
          // se obtiene el idrol
          idrol = Lista_RolUsuario.get(0).getIdRol().getIdRol();
          Caurolx = Lista_RolUsuario.get(0).getIdRol();
          // i.	Consultar los registros de la tabla CAU_RolXtrans para obtener 
          // una lista de los ID_RolXTrans conforme al ID_Rol obtenido
          // en el paso a) 
          for(CauRolxusuario paso1 : Lista_RolUsuario){
             Consulta = "Select c from CauRolxtrans c "
                     + "where c.idRol.idRol = ?1" ;
             Lista_Rolxtrans = EJBRolxtrans.consulta(Consulta, paso1.getIdRol().getIdRol());
             // ii.Borrar los registros de la tabla CAU_RolXTranXPar conforme 
             // al ID_RolXTrans obtenido en la consulta anterior
              for (CauRolxtrans paso2 : Lista_Rolxtrans) {
                  Consulta = "Select c from CauRolxtranxpar c "
                          + "where c.idRolxtrans.idRolxtrans = ?1";
                  Lista_Rolxtranxpar = EJBRolxtransxpar.finxRol(Consulta, paso2.getIdRolxtrans());
                  for (CauRolxtranxpar paso3 : Lista_Rolxtranxpar){
                      EJBRolxtransxpar.remove(paso3);
                  }
                  EJBRolxtrans.remove(paso2);
              }
          }
          // iv. Borra los registros de la tabla CAU_Menu conforme al rol asignado al usuario
          Consulta = "Select c from CauMenu c where c.idRol.idRol = ?1";
          List_Menu = EJBMenu.MenuRol(Consulta, idrol);
          for (CauMenu paso3: List_Menu){
             EJBMenu.remove(paso3);
          }
          
      } 
   }
   
    public void BorrarUsuarioRol() {
        BorrarUsuarioTra();
        String Consulta;
        Consulta = "S elect c from CauRolxusuario c"
                + " c.idUsuario.idUsuario =  ?1";
        // a. Consultar la tabla CAU_RolXUsuario para obtener los ID_Rol aignado 
        Lista_RolUsuario = EJBRolxusuario.findxRango(Usua.getClaveusua());
        for (CauRolxusuario paso1 : Lista_RolUsuario) {
            EJBRolxusuario.remove(paso1);
        }
        if (Caurolx != null) {
            EJBRol.remove(Caurolx);
        }
    }
    
    
   // 1.- Determinar l tipo de usuario  
   public void TipoUsuario(){       
       String consulta;
       usuariox.setClaveusua(usuario);
       usuariox.setContrasena(contraseña);       
       Usua = EJBUsuario.iniciarSession(usuariox);
       tipousuario = 0;
       if (Usua != null) {
           consulta = "Select c from CauUsuariotipo c"
                   + " where c.idUsuario.claveusua = ?1 order by c.idTipousuario.idTipousuario";
           tipousuario = EJBUsuariotipo.Consulta_TipoUsuario(consulta, Usua.getClaveusua());
       }       
   }
   
   // 2.- Obtener las transacciones base a las que tiene acceso conforme al tipo de usuario:
   public void TransBase(){
      String consulta;
      
      consulta = "Select c from CauRolbase c"
                    + " where c.idTipousuario.idTipousuario = ?1";
      Lista_BaseRolbase = EJBRolBase.Consulta_RolBase(consulta, tipousuario); 
      // ojo si el tipo de usuario tiene mas de un rol base entoces hay que sumarizar en la 
      // Lista_BaseTransBase todas las transacciones 
      Lista_BaseTransBase = new ArrayList<>();
      for (CauRolbase paso:Lista_BaseRolbase){
          consulta = "Select c from CauRolbastran c" 
                     + " where c.idRolbase.idRolbase = ?1";
          if (Lista_BaseTransBase.isEmpty()){
              int idrolbasea = paso.getIdRolbase();
              Lista_BaseTransBase = EJBRolbastran.Consulta_RolBasTran(consulta, idrolbasea );
          } else {
              Lista_BaseTransBase.addAll(EJBRolbastran.Consulta_RolBasTran(consulta, paso.getIdRolbase())) ;
          }          
      }
   }
    
   // 3.- Determinar el ID_Rol Asignado al usuario   
   public void RolUsuAct() throws ParseException{
      String Consulta;
      Consulta = "Select c from CauRolxusuario c"
              + " c.idUsuario.idUsuario =  ?1";
      // a. Consultar la tabla CAU_RolXUsuario para obtener los ID_Rol aignado 
      Lista_RolUsuario = EJBRolxusuario.findxRango(Usua.getClaveusua());
     
      // b. Si la consulta anterior tiene resultados entonces 
      if (Lista_RolUsuario.isEmpty() == false)     { 
          // se obtiene el idrol
          idrol = Lista_RolUsuario.get(0).getIdRol().getIdRol();
          Caurolx = Lista_RolUsuario.get(0).getIdRol();
          // i.	Consultar los registros de la tabla CAU_RolXtrans para obtener 
          // una lista de los ID_RolXTrans conforme al ID_Rol obtenido
          // en el paso a) 
          for(CauRolxusuario paso1 : Lista_RolUsuario){
             Consulta = "Select c from CauRolxtrans c "
                     + "where c.idRol.idRol = ?1" ;
             Lista_Rolxtrans = EJBRolxtrans.consulta(Consulta, paso1.getIdRol().getIdRol());
             // ii.Borrar los registros de la tabla CAU_RolXTranXPar conforme 
             // al ID_RolXTrans obtenido en la consulta anterior
              for (CauRolxtrans paso2 : Lista_Rolxtrans) {
                  Consulta = "Select c from CauRolxtranxpar c "
                          + "where c.idRolxtrans.idRolxtrans = ?1";
                  Lista_Rolxtranxpar = EJBRolxtransxpar.finxRol(Consulta, paso2.getIdRolxtrans());
                  for (CauRolxtranxpar paso3 : Lista_Rolxtranxpar){
                      EJBRolxtransxpar.remove(paso3);
                  }
                  EJBRolxtrans.remove(paso2);
              }
          }
          // iv. Borra los registros de la tabla CAU_Menu conforme al rol asignado al usuario
          Consulta = "Select c from CauMenu c where c.idRol.idRol = ?1";
          List_Menu = EJBMenu.MenuRol(Consulta, idrol);
          for (CauMenu paso3: List_Menu){
             EJBMenu.remove(paso3);
          }
      }
       else {           //i. Se crea un registro en la tabla CAU_ROL y se obtiene el ID_Rol
           Caurolx = new CauRol();
           Caurolx.setFechaviginic(new Date());
           SimpleDateFormat objSDF = new SimpleDateFormat("dd-mm-yyyy");
           Date Fecha = objSDF.parse("01-01-9999");
           Caurolx.setFechavigfin(Fecha);
           Caurolx.setDescrol("Rol del usuario: " + Usua.getNocredencial().toString());
           Caurolx.setTextoexplicativo("Rol exclusivo para el usuario:" + Usua.getClaveusua());
           Caurolx.setFechacreacion(new Date());
           EJBRol.create(Caurolx);
           idrol = Caurolx.getIdRol();
           Caurolx = EJBRol.find(idrol);
           
            //ii. Conforme al valor del campo ID_Rol y a la variable Usuario.ID_Usuario asignada 
            //    se crea un registro en la tabla CAU_RolXUsuario obteniendo el valor de ID_RolUsuario
           CauRolxusuariox = new CauRolxusuario();
           CauRolxusuariox.setFechaasig(new Date());
           CauRolxusuariox.setIdUsuario(Usua);
           CauRolxusuariox.setIdRol(Caurolx);
           //CauRolxusuariox.setIdRolusuario(3);
           EJBRolxusuario.create(CauRolxusuariox);  
           
       }          
   }
// 4. Se obtienen todos los valores de la Lista_BaseTransBase  obtenida en el paso 2) 
// y para cada elemento se agrega un registro en la tabla CAU_RolXTrans, con el campo 
// ID_Trans y el campo ID_Rol obtenido en el paso 3)   
  public void ListTransBase() {
      for (CauRolbastran paso: Lista_BaseTransBase){
          CauRolxtransx = new CauRolxtrans();
          CauRolxtransx.setIdRol(Caurolx);
          CauRolxtransx.setIdTrans(paso.getIdTrans());
          EJBRolxtran.create(CauRolxtransx);
      }
  }
  
  // 5.	Crear los valores para llenar la tabla CAU_RolXTranXPar
    public void TablaCauRolxTranxPart() {
        // a. Se definen tres variables donde se almacenan los valores 
        // generales de los parámetros que son:  
        MesasServicio_Act = null;
        Departamentos_Act = null;
        Servicios_Act = null;
        Boton_Act = null;
        Requisitos_Act = null;

        String consulta;

        // b. Si ID_TipoUsuario = 1 //se trata de un administrador general  
        if (tipousuario == 1) {
            // i. Se consulta la tabla CAU_MesaServicio y se obtienen todos
            // los valores del campo ID_MesaServ y se almacenan en la variable 
            // MesasServicio_Act separados por comas.  
            consulta = "Select c from CauMesaservicio c";
            Lista_Mesaserv = EJBMesaservicio.ConsultaMesa(consulta, 0);
            for (CauMesaservicio paso1 : Lista_Mesaserv) {
                if (MesasServicio_Act == null) {
                    MesasServicio_Act = paso1.getIdMesaserv().toString().trim();
                } else {
                    MesasServicio_Act = MesasServicio_Act + "," + paso1.getIdMesaserv().toString().trim();
                }
            }
            Departamentos_Act = "*";
            Servicios_Act = "*";
            Boton_Act = "true";
            Requisitos_Act = "*";
        } else {
            // i.consultar la tabla CAU_Grupos conforme el valor que se encuentra 
            // en la variable Usuarios.ID_AreaGrupos y se asigna a la 
            // Variable MesasServicio_Act = ID_MesaServ 
            MesasServicio_Act = Usua.getIdAreagrupos().getIdMesaserv().getIdMesaserv().toString();
            // ii.Si ID_TipoUsuario = 5 // trata de un administrador de mesa de servicio especifico
            if (tipousuario == 5) { 
                Departamentos_Act = "*";
                Servicios_Act = "*";
                Boton_Act = "true";
                Requisitos_Act = "*";
              // iii. Otro Si ID_TipoUsuario = 2 // se trata de un jefe de departamento    
            } else if (tipousuario == 2) { // se trata de un jefe de departamento
                consulta = "Select c from CauDepjefe c where c.cauUsuarios.idUsuario = ?1";
                Lista_DepJefe = EJBDepjefe.Consulta_DepJefe(consulta, Usua.getIdUsuario());
                for (CauDepjefe paso1 : Lista_DepJefe) {
                    if (Departamentos_Act == null) {
                        Departamentos_Act = paso1.getCauDepartamentos().getIdDepartamentos().toString().trim();
                    } else {
                        Departamentos_Act = Departamentos_Act + "," + paso1.getCauDepartamentos().getIdDepartamentos().toString().trim();
                    }
                }
                Servicios_Act = "*";
                Boton_Act = "true";
                Requisitos_Act = "";
            // iv. Otro Si ID_TipoUsuario = 3 // se trata de un jefe de un tecnico
            } else if (tipousuario == 3) { 
                consulta = "Select c from CauTectipotrab c where c.cauUsuarios.idUsuario = ?1 ";
                Lista_Tectipotrab = EJBTectipotrab.Consulta_TecTipoTrabajo(consulta, Usua.getIdUsuario());
                for (CauTectipotrab paso1 : Lista_Tectipotrab) {
                    if (Servicios_Act == null) {
                        Servicios_Act = paso1.getCauTipotrabajo().getIdTrabajo().toString().trim();
                    } else {
                        Servicios_Act = Servicios_Act + "," + paso1.getCauTipotrabajo().getIdTrabajo().toString().trim();
                    }
                }
                Departamentos_Act = "";
                Boton_Act = "false";
                Requisitos_Act = "";
            }
        }
    }
  //6. Crear los valores de la tabla CAU_RolXTranXPar
  public void TablaCauRolxTranxPart2(){
    String consulta = null;
      
   // a. Consultar la tabla CAU_RolXTrans respecto del ID_Rol obtenido en el paso3)
   // dejando el resultado en la lista List_RolXTrans. 
   consulta = "Select c from CauRolxtrans c where c.idRol.idRol = ?1";
   Lista_Rolxtrans =  EJBRolxtrans.consulta(consulta, idrol);
   // b. Ciclar la lista List_RolXTrans y para cada uno de esos valores consultar
   // la tabla CAU_TranXPar respecto del campo ID_Trans y se obtiene la lista List_TranXPar
   for (CauRolxtrans paso1:Lista_Rolxtrans){
     consulta = "Select c from CauTranxpar c " 
                + " where c.idTrans.idTrans = ?1 ";
     List_Tranxpar =  EJBCauTranxpar.Consulta_Tranxpar(consulta, paso1.getIdTrans().getIdTrans());
     // c. .Cilcar la lista List_TranXPar
     for (CauTranxpar paso2: List_Tranxpar){
        // 1. Insertar un registro en la tabla CAU_RolXTransXPar con los siguientes valores  
        CauRolxtranxpar Tranxpar = new CauRolxtranxpar();
        Tranxpar.setIdParam(paso2.getIdParam());
        Tranxpar.setIdRolxtrans(paso1);
        switch  (paso2.getIdParam().getIdParam()) {
            case 1 :Tranxpar.setValor( MesasServicio_Act);
                    break;
            case 2 :Tranxpar.setValor( Departamentos_Act);
                    break;
            case 3 :Tranxpar.setValor( Servicios_Act);
                    break;
            case 4 :Tranxpar.setValor( Requisitos_Act);
                    break;
            case 5 :Tranxpar.setValor( Boton_Act);
                    break;
        }
        if (Tranxpar.getValor() == null){
            Tranxpar.setValor("");
        }
//        if (Tranxpar.getValor().length() > 0) {
//          EJBRolxtransxpar.create(Tranxpar);
//        }
        EJBRolxtransxpar.create(Tranxpar);
     }
   }
  }
  
    public void Menubase_rol() {
        menux = new CauMenu();
        CauMenu idMenu;
        idMenu = EJBMenu.find(0);
        menux.setIdPadre(idMenu);
        
        menux.setDescmenu("Administracion");
        menux.setAyudamenu("Menu de Administración de Solicitudes");
        CauTipomenu idTipomenu;
        idTipomenu = EJBTipomenu.find(1);
        menux.setIdTipmenu(idTipomenu);
        menux.setIdTrans(null);
        menux.setLabel("Administración");
        menux.setIdRol(Caurolx);
        EJBMenu.create(menux);

        int idmenu = menux.getIdMenu();
        menux = EJBMenu.find(idmenu);

        //Ciclar la tabla CauRolxTrans e incertar los elementos del menu 
        for (CauRolxtrans paso1 : Lista_Rolxtrans) {
            CauMenu menuy = new CauMenu();
            menuy.setIdPadre(menux);
            menuy.setDescmenu(paso1.getIdTrans().getDesctrans());
            menuy.setAyudamenu(paso1.getIdTrans().getDesctrans());

            idTipomenu = EJBTipomenu.find(3);
            menuy.setIdTipmenu(idTipomenu);
            menuy.setIdTrans(paso1.getIdTrans());
            menuy.setLabel(paso1.getIdTrans().getDesctrans());
            menuy.setIdRol(Caurolx);
            EJBMenu.create(menuy);
        }
    }
}
