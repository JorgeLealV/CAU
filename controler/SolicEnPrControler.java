/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfp.controler;


import com.sfp.ejb.CauEstatusgeneralFacadeLocal;
import com.sfp.ejb.CauSolicitudesFacadeLocal;
import com.sfp.ejb.CauSolicitudtrabajosFacadeLocal;
import com.sfp.model.CauEstatusgeneral;
import com.sfp.model.CauSolicitudes;
import com.sfp.model.CauSolicitudtrabajos;
import com.sfp.model.CauUsuarios;
import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author jleal
 */
@Named(value = "solicEnPrControler")
//@Dependent
//@ViewScoped
// @SessionScoped 
@ViewScoped
public class SolicEnPrControler implements Serializable{
   
    @EJB
    private CauSolicitudesFacadeLocal EJBSolicitudes;
    
    @EJB
    private CauEstatusgeneralFacadeLocal EJBTCauEstatusgeneral;
    
    @EJB
    private CauSolicitudtrabajosFacadeLocal EJBSolicitudestrabajo;
     
    @Inject private Login01Controler Login01Controler1;
    
    private CauSolicitudes selected;
    private Collection<CauSolicitudes> items;
    private Collection<CauSolicitudtrabajos> itemsdet;

    public Collection<CauSolicitudtrabajos> getItemsdet() {
        return itemsdet;
    }

    public void setItemsdet(Collection<CauSolicitudtrabajos> itemsdet) {
        this.itemsdet = itemsdet;
    }
    
    
    private CauUsuarios Usua;    
    private String SalidaSolic;   
    private String OrigenLlamada;
    private boolean entMantTrab;
    private String NodeUsuario;

    public String getNodeUsuario() {
        return NodeUsuario;
    }

    public void setNodeUsuario(String NodeUsuario) {
        this.NodeUsuario = NodeUsuario;
    }
    

    public String getSalidaSolic() {
        return SalidaSolic;
    }

    public void setSalidaSolic(String SalidaSolic) {
        this.SalidaSolic = SalidaSolic;
    }

   
   @PostConstruct
   private void init(){
       Usua = (CauUsuarios) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario"); 
       FacesContext facesContext = FacesContext.getCurrentInstance();
       Map params = facesContext.getExternalContext().getRequestParameterMap();              
       OrigenLlamada = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Origen");
       entMantTrab = false;
       selected = null;
   } 
    
    public CauSolicitudes getSelected() {
        //if (entMantTrab) {
        //    MantTrabControler1.setSalidaSolic(selected.getIdSolicitud().toString());
        //    MantTrabControler1.setEntradaSolic(selected.getIdSolicitud().toString());
        //}
        //FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("Solicitud", selected.getIdSolicitud().toString());
        return selected;        
    }

    public void setSelected(CauSolicitudes selected) {
        this.selected = selected;
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("Solicitud", selected.getIdSolicitud().toString());
    }

    public Collection<CauSolicitudes> getItems() {
//        if (items == null) {
//                        
//        }       
        ObtenSolicitudes();
        return items;   
    }

    public void setItems(Collection<CauSolicitudes> items) {
        this.items = items;
    }
          
    public  SolicEnPrControler() {    
    }
    
    public void  ObtenSolicitudes(){  
       String Mesaservicio = Login01Controler1.getMesaDeServicio_parametro();   
       String consulta1 = "SELECT c FROM CauSolicitudes c Join c.idEstatus e" 
                             + " WHERE e.idEstatus in (5, 6, 9) and c.usuariosolicita = ?1 and "
                             + " c.idMesaserv.idMesaserv = " + Mesaservicio
                             + " Order by c.idSolicitud DESC";  
                       //      +  " c.idSolicitud in "
                       //      + "(Select d.idSolicitud.idSolicitud "
                       //      + " from CauSolicitudtrabajos d Join CauDeptrabajo e "
                       //      + " where e.idDepartamentos.idMesaserv.idMesaserv = " 
                       //      + Mesaservicio + " and d.idSolicitud.idSolicitud  = c.idSolicitud )"
                            
       setItems(EJBSolicitudes.Consulta_Solic(consulta1,Usua.getNocredencial().toString()));       
    }
      
    public void prepareDetalle(){
      if (selected != null) {
          String consulta1 = " SELECT c FROM CauSolicitudtrabajos c Join c.idSolicitud e "
                          +" WHERE e.idSolicitud = ?1"; 
       
       //int idSolicitud = Integer.parseInt(selected.getIdSolicitud());
       setItemsdet(EJBSolicitudestrabajo.Consulta_Solicitudestrabajo(consulta1,selected.getIdSolicitud()));
      }
    } 
    
    
    public void cancelarEnv(){
        // Se envia cancela solicitud se cambia el estatus de id_estatus = 6 a 7 
        // Si es que ninguno de los trabajos se ha asignado a un tecnico es decir que 
        // todos los trabajos de la solicitud tengan estatus igual a 4 
        Collection<CauSolicitudtrabajos> trabajos;
        CauSolicitudtrabajos trabajo1;
        boolean sinasig;
        sinasig= false;
        
        String consulta1 = " SELECT c FROM CauSolicitudtrabajos c Join c.idSolicitud e "
                            +" WHERE e.idSolicitud = ?1"; 
       
       int idSolicitud = selected.getIdSolicitud();
       trabajos = (EJBSolicitudestrabajo.Consulta_Solicitudestrabajo(consulta1,idSolicitud));     
       for( CauSolicitudtrabajos trabajo: trabajos){
            if (trabajo.getIdEstatus().getIdEstatus() == 14) {
                sinasig = true;
            }
        }
        if (sinasig == false) {
            CauEstatusgeneral estatus;
            estatus = EJBTCauEstatusgeneral.find(7);
            selected.setIdEstatus(estatus);
            EJBSolicitudes.edit(selected);
            // Hay que poner todos los trabajos de la solicitud en estatus 4 
            // Trabajo sin asignación de tecnico
            consulta1 = " SELECT c FROM CauSolicitudtrabajos c Join c.idSolicitud e "
                        + " WHERE e.idSolicitud = ?1";
            trabajos = EJBSolicitudestrabajo.Consulta_Solicitudestrabajo(consulta1, selected.getIdSolicitud());
            for (CauSolicitudtrabajos trab : trabajos) {
                estatus = EJBTCauEstatusgeneral.find(15);
                trab.setIdEstatus(estatus);
                EJBSolicitudestrabajo.edit(trab);     
        }
            selected = null;
        }
        else {
            FacesContext context = FacesContext.getCurrentInstance();         
            context.addMessage(null, new FacesMessage("Tiene algun trabajo asignado a un tecnico no se puede cancelar", ""));
            context.addMessage(null, new FacesMessage("Comunicarse al area de recursos materiales para proceder", ""));
        }               
    }
}
