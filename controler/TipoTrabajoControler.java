/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfp.controler;


import com.sfp.ejb.CauDepartamentosFacadeLocal;
import com.sfp.ejb.CauDeptrabajoFacadeLocal;
import com.sfp.ejb.CauEstatusgeneralFacadeLocal;
import com.sfp.ejb.CauMesaservicioFacadeLocal;
import com.sfp.ejb.CauSolicitudesFacadeLocal;
import com.sfp.ejb.CauSolicitudtrabajosFacadeLocal;
import com.sfp.ejb.CauTipotrabajoFacadeLocal;
import com.sfp.ejb.CauUsuariosFacadeLocal;
import com.sfp.model.CauDepartamentos;
import com.sfp.model.CauDeptrabajo;
import com.sfp.model.CauEstatusgeneral;
import com.sfp.model.CauMesaservicio;
import com.sfp.model.CauSolicitudes;
import com.sfp.model.CauSolicitudtrabajos;
import com.sfp.model.CauTipotrabajo;
import com.sfp.model.CauUsuarios;
import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author jleal
 */
@Named(value = "tipotrabControler")
//@Dependent
@ViewScoped
//@SessionScoped 
public class TipoTrabajoControler implements Serializable{
   
    @EJB
    private CauTipotrabajoFacadeLocal EJBTipotrabajo;
    
    @EJB
    private CauSolicitudesFacadeLocal EJBTSolicitudes;
    
    @EJB
    private CauEstatusgeneralFacadeLocal EJBTCauEstatusgeneral;
    
    @EJB
    private CauUsuariosFacadeLocal EJBUsuario;
    
    @EJB
    private CauSolicitudtrabajosFacadeLocal EJBSolTrab;
    
    @EJB
    private CauDeptrabajoFacadeLocal EJBDepTrabajo;
    
    @EJB
    private CauDepartamentosFacadeLocal EJBDepartamento;
    
    @EJB
    private CauMesaservicioFacadeLocal EJBMesaserv;
    
    @Inject private Login01Controler Login01Controler1;
    
    private CauTipotrabajo selected;    
    private Collection<CauTipotrabajo> items;
    private Collection<CauDeptrabajo> itemsDT;
    private CauUsuarios Usua;
    private String EntradaSolic;
    private String EntradaDepto;
    private String EntradaDescripcion;
    private CauSolicitudtrabajos SolicTrabajo;
    private String NoTrabajos="1";
    private String DesTrabajo = "";
    private boolean showUndo=false;
    private String descripcion = "";
    private String Titulo;

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String Titulo) {
        this.Titulo = Titulo;
    }

    
    
    public String getDesTrabajo() {
        return DesTrabajo;
    }

    public void setDesTrabajo(String DesTrabajo) {
        this.DesTrabajo = DesTrabajo;
    }
    
    public boolean isShowUndo() {
        return showUndo;
    }

    public void setShowUndo(boolean showUndo) {
        this.showUndo = showUndo;
    }
    
    public String getNoTrabajos() {
        return NoTrabajos;
    }

    public void setNoTrabajos(String NoTrabajos) {
        this.NoTrabajos = NoTrabajos;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
    
    private String SalidaSolic;
    private String OrigenLlamada;

    public String getEntradaSolic() {
        return EntradaSolic;
    }

    public void setEntradaSolic(String EntradaSolic) {
        this.EntradaSolic = EntradaSolic;
    }

    public String getEntradaDepto() {
        return EntradaDepto;
    }

    public void setEntradaDepto(String EntradaDepto) {
        this.EntradaDepto = EntradaDepto;
    }

    public String getEntradaDescripcion() {
        return "Trabajos Disponibles del departamento: " + Login01Controler1.getDescripcionDeptoLogin_Parametro();
    }

    public void setEntradaDescripcion(String EntradaDescripcion) {
        this.EntradaDescripcion = EntradaDescripcion;
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
   } 
    public CauTipotrabajo getSelected() {
        if (selected != null) {
            String paso1 = selected.getDespliegue();           
            if (selected.getDespliegue().equals("SI")) {
                String[] Desc1;
                String[] Requisitos;
                String paso2 = selected.getRequisitos();
                Desc1 = paso2.split("\\|");
                descripcion = "";
                for (int i = 0; i < Desc1.length-1; i++) {
                    if (descripcion.length() == 0) {
                        descripcion = Desc1[i];
                    } else {
                        descripcion = descripcion + (char)13 + Desc1[i];
                    }
                }
                Requisitos = Desc1[Desc1.length-1].split(",");
                String req = "";
                for (int i = 0;i < Requisitos.length; i++){
                    if (req.length() == 0) {
                        req = Requisitos[i];
                    } else {
                        req = req + (char)13 + Requisitos[i];
                    }
                }
                DesTrabajo = req;
                showUndo = true;
            } else {
                descripcion = "";
                showUndo = false;
            }
            Titulo = "     " + selected.getDescripcion();
        }
        return selected;
    }

    public void setSelected(CauTipotrabajo selected) {
        this.selected = selected;
    }

    public void  prepareCreate(){
        NoTrabajos = "1";
    }
    
    
    public Collection<CauTipotrabajo> getItems() {
        OrigenLlamada = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Origen");
        if (OrigenLlamada.equals("CAU_Deptos") && Login01Controler1 != null) {
            EntradaSolic =  Login01Controler1.getSolicitudLogin_Parametro();
            EntradaDepto = Login01Controler1.getDepartamentoLogin_Parametro();                 
            EntradaDescripcion = Login01Controler1.getDescripcionDeptoLogin_Parametro();
        }
        if (items == null){
            ObtenTipoTrabajo();  
        }
        
        return items;     
    }

    public void setItems(Collection<CauTipotrabajo> items) {
        this.items = items;
    }
          
    public  TipoTrabajoControler() {    
    }

    public void  ObtenTipoTrabajo(){           
        String consulta1 = "SELECT c FROM CauTipotrabajo c, CauDeptrabajo e " 
                +                          " WHERE c.idTrabajo = e.idTrabajo.idTrabajo and "
                +                          " e.idDepartamentos.idDepartamentos = ?1"; 
        if (EntradaDepto != null) {
            int depto = Integer.parseInt(EntradaDepto);
            setItems(EJBTipotrabajo.Consulta_TipTrabajo(consulta1, depto));
        }
       //return "/PrimeFaces/Menu/index?faces-redirect=true";
    }
    
    public String GuardarTrabajo() throws IOException{
        
//Poner las instrucciones para el guardador de el trabajo segun lo que tenga el parametro solic
//        if (solic.equals("0")){
//            // 1.- Guardar la solicitud 
//            // 2.- Obtener el no de solicitud 
//            solic = "9191919";   // no de solicitud generado
//            // 3.- Guardar trabajo
//        }
       // la solicitud tiene que ser la que se genero nueva
        CauEstatusgeneral estatus;
        CauMesaservicio mesaserv;
        CauSolicitudes newItem;
        if (DesTrabajo.length() > 0){
           DesTrabajo = DesTrabajo.replace("\r\n", "|");
        }
        if (EntradaSolic.equals("0")) {
            // Alta de la solicitud
            newItem = new CauSolicitudes();
            estatus = EJBTCauEstatusgeneral.find(7);
            newItem.setDescadmin("Descripción del administrador");
            newItem.setFechacaptura(new Date());
            // newItem.setFechaenvio(new Date());
            // newItem.setFechacancusuario(new Date());
            newItem.setFechacierre(new Date());
            // newItem.setFechacancela(new Date());
            newItem.setUsuariosolicita(Usua.getNocredencial().toString());
            newItem.setUsuarioatendido(Usua.getNocredencial().toString());
            newItem.setDesccancea("Descripcion de cancelación");
            newItem.setDescusuario("Descripcion del usuario");
            newItem.setIdEstatus(estatus);
            
            String Mesaserv = Login01Controler1.getMesaDeServicio_parametro();
            mesaserv = EJBMesaserv.find(Integer.parseInt(Mesaserv.trim()));
            newItem.setIdMesaserv(mesaserv);
                    
            EJBTSolicitudes.create(newItem);
            // se obtiene el no de solicitud que se agreo a la base de datos
            //SalidaSolic = newItem.getIdSolicitud().toString().trim();// ojo ojo 
            Login01Controler1.setSolicitudLogin_Parametro(newItem.getIdSolicitud().toString().trim());
            // ojo ojo  = newItem.getIdSolicitud().toString().trim();// ojo ojo 
            //Alta de item 

            CauDepartamentos CauDep = new CauDepartamentos();
            CauDep = EJBDepartamento.find(Integer.parseInt(EntradaDepto));
            itemsDT = EJBDepTrabajo.Consulta_CauDepTrabajo(selected, CauDep);

            if (itemsDT.size() > 0) {
                SolicTrabajo = new CauSolicitudtrabajos();
                estatus = EJBTCauEstatusgeneral.find(15);
                SolicTrabajo.setIdEstatus(estatus);
                SolicTrabajo.setIdSolicitud(newItem);               
                SolicTrabajo.setIdDeptrab(itemsDT.iterator().next());
                SolicTrabajo.setFechaasigsolicusua(new Date());
                SolicTrabajo.setFechaasigdepto(null);
                SolicTrabajo.setDesctrabusuario("_");
                SolicTrabajo.setNodeservicios(Integer.parseInt(NoTrabajos));
                SolicTrabajo.setDescripcion(DesTrabajo);
                SolicTrabajo.setFechacanctrabajo(null);
                SolicTrabajo.setDesccancelacion("");
                SolicTrabajo.setNoestandares(1);
                EJBSolTrab.create(SolicTrabajo);
                String paso = newItem.getIdSolicitud().toString().trim();

            }
        } else {
            CauDepartamentos CauDep = new CauDepartamentos();
            CauDep = EJBDepartamento.find(Integer.parseInt(EntradaDepto));
            itemsDT = EJBDepTrabajo.Consulta_CauDepTrabajo(selected, CauDep);

            if (itemsDT.size() > 0) {
                SolicTrabajo = new CauSolicitudtrabajos();
                estatus = EJBTCauEstatusgeneral.find(15);
                SolicTrabajo.setIdEstatus(estatus);
                newItem = EJBTSolicitudes.find(Integer.parseInt(EntradaSolic));
                SolicTrabajo.setIdSolicitud(newItem);
                SolicTrabajo.setIdDeptrab(itemsDT.iterator().next());
                SolicTrabajo.setFechaasigsolicusua(new Date());
                SolicTrabajo.setFechaasigdepto(null);
                SolicTrabajo.setDesctrabusuario("_");
                SolicTrabajo.setNodeservicios(Integer.parseInt(NoTrabajos));
                SolicTrabajo.setDescripcion(DesTrabajo);
                SolicTrabajo.setFechacanctrabajo(null);
                SolicTrabajo.setDesccancelacion("");
                SolicTrabajo.setNoestandares(1);
                EJBSolTrab.create(SolicTrabajo);
                String paso = newItem.getIdSolicitud().toString().trim();
                SalidaSolic = EntradaSolic;
            }
           selected = null;
        }

        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("Origen", "CAU_Tipotrabajo");
        //FacesContext contex = FacesContext.getCurrentInstance();
        //    contex.getExternalContext().redirect("../MantTrab/index?faces-redirect=true");


        return "/PrimeFaces/MantTrab/index?faces-redirect=true";
        
    }
    
    public String redireccionURL(){
      //  FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("Origen", "CAU_Tipotrabajo");
      //  return "../PrimeFaces/MantTrab/index?faces-redirect=true"; 
      return "";
    }
    
     public String salir(){
        SalidaSolic = EntradaSolic;  
       if (SalidaSolic.equals("0")){
         FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("Origen", "CAU_Tipotrabajo");  
         return "/PrimeFaces/Deptos/index?faces-redirect=true";  
       } else {
          FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("Origen", "CAU_Tipotrabajo");  
          return "/PrimeFaces/MantTrab/index?faces-redirect=true";
       }           
    }  
}
