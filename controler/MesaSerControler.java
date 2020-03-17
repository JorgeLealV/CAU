/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfp.controler;
import com.sfp.ejb.CauEmpFacadeLocal;
import com.sfp.ejb.CauRolxtranxparFacadeLocal;
import com.sfp.ejb.CauRolxusuarioFacadeLocal;
import com.sfp.model.CauRolxtranxpar;
import com.sfp.model.CauRolxusuario;
import com.sfp.model.CauUsuarios;
import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author jleal
 */
@Named(value = "mesaSerControler")
//@SessionScoped 
@ViewScoped
public class MesaSerControler implements Serializable {

    @EJB
    private CauRolxusuarioFacadeLocal EJBERolXUsuario;
    
    @EJB
    private CauRolxtranxparFacadeLocal EJBERolXtranxpar;
    
    @Inject private Login01Controler Login01Controler1;

    private CauUsuarios Usua;
    private String OrigenLlamada;
    private Boolean Showundo;
    private boolean value1;  
    private boolean value2;
    private String console; 
    
    Collection<CauRolxtranxpar> CauRolxtranxparCollection;
    
    public MesaSerControler() {
        // super(CauDepartamentos.class);
    }
    
    @PostConstruct
    private void init() {
       FacesContext facesContext = FacesContext.getCurrentInstance();
       Map params = facesContext.getExternalContext().getRequestParameterMap();
       ObtenParametros();
       Login01Controler1.setEnvioMesa("True");
       Showundo = false;
       ObtenMesas();
    }

    public Boolean getShowundo() {
        return Showundo;
    }

    public void setShowundo(Boolean Showundo) {
        this.Showundo = Showundo;
    }

     public String getConsole() {
        return console;
    }
 
    public void setConsole(String console) {
        this.console = console;
    }
    
    private void ObtenParametros(){
         OrigenLlamada = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Origen");         
    }
    
   public String RedirecMesaDGRM(){    
       // Procede a dar de alta un trabajo
       ObtenParametros();
       Login01Controler1.setMesaDeServicio_parametro("1"); // mesa de servicio DGRM       
       //FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("Origen", "CAU_Deptos");
       //return "/PrimeFaces/cauTipotrabajo/index?faces-redirect=true";
       Login01Controler1.setEnvioMesa("False");
       return "/PrimeFaces/cauSolicitudes/index?faces-redirect=true";
   } 
   public String RedirecMesaDGTI(){    
       // Procede a dar de alta un trabajo
       ObtenParametros();
       Login01Controler1.setEnvioMesa("False");
       Login01Controler1.setMesaDeServicio_parametro("2"); // mesa servicio DGTI
       //FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("Origen", "CAU_Deptos");
       //return "/PrimeFaces/cauTipotrabajo/index?faces-redirect=true";
       return "/PrimeFaces/cauSolicitudes/index?faces-redirect=true";
   } 
    
   public String RedirecMesaGRP(){    
       // Procede a dar de alta un trabajo
       ObtenParametros();
       Login01Controler1.setEnvioMesa("False");
       Login01Controler1.setMesaDeServicio_parametro("3"); // mesa servicio GRP
       //FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("Origen", "CAU_Deptos");
       //return "/PrimeFaces/cauTipotrabajo/index?faces-redirect=true";
       return "/PrimeFaces/cauSolicitudes/index?faces-redirect=true";
   } 
   
   // obtiene las mesas a las que tiene acceso el usuario que se especifica en  
   // el el rol que tiene asignado   
    private void ObtenMesas() {
        // se obtienen los roles del usuario de la tabla CAU_RolXUsuario
        Collection<CauRolxusuario> cauRolxusuarioCollection;
        String RolesUsuario = "";
        String Usuario = Login01Controler1.getUsuario().getClaveusua();
        cauRolxusuarioCollection = EJBERolXUsuario.findxRango(Usuario);

        for (CauRolxusuario paso : cauRolxusuarioCollection) {
            if (RolesUsuario.length() == 0) {
                RolesUsuario = paso.getIdRol().getIdRol().toString();
            } else {
                RolesUsuario = RolesUsuario + "," + paso.getIdRol().getIdRol();
            }
        }
        // La transacción de la mesa de servicio es 36
        String Cadena1 = "Select c "
                       + " from CauRolxtranxpar c"
                       + " where c.idRolxtrans.idRol.idRol in (" + RolesUsuario + " ) and "
                       + "  c.idRolxtrans.idTrans.idTrans = ?1";
        
//      Cadena1 = "Select c "
//                       + " from CauRolxtranxpar c"
//                       + " where c.idRolxtrans.idRol.idRol = 1 and "
//                       + "  c.idRolxtrans.idTrans.idTrans = 36";
//        
//      Cadena1 = "Select c "
//                       + " from CauRolxtranxpar c";
        
        CauRolxtranxparCollection = EJBERolXtranxpar.finxRol(Cadena1,36);
    }
    
    public boolean ActivaMesa(int mesa) {
        for (CauRolxtranxpar paso : CauRolxtranxparCollection) {
            if (paso.getIdParam().getIdObjtipaut().getIdObjtipaut() == 1) {
                for (String pasito : paso.getValor().split(",")) {
                    if (Integer.parseInt(pasito) == mesa) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
     public boolean isValue1() {
        return value1;
    }
 
    public void setValue1(boolean value1) {
        this.value1 = value1;
    }
 
    public boolean isValue2() {
        return value2;
    }
 
    public void setValue2(boolean value2) {
        this.value2 = value2;
    }
     
    public void addMessage() {
        String summary = value2 ? "Checked" : "Unchecked";
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(summary));
    }    
}
