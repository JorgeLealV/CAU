/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfp.controler;


import com.sfp.cau.SolicitudComp;
import com.sfp.ejb.CauEmpFacadeLocal;
import com.sfp.ejb.CauEstatusgeneralFacadeLocal;
import com.sfp.ejb.CauMesaservicioFacadeLocal;
import com.sfp.ejb.CauSolicitudesFacadeLocal;
import com.sfp.ejb.CauSolicitudtrabajosFacadeLocal;
import com.sfp.ejb.CauTectipotrabFacadeLocal;
import com.sfp.ejb.CauTrabajostecnicosFacadeLocal;
import com.sfp.ejb.CauUsuariosFacadeLocal;
import com.sfp.model.CauEmp;
import com.sfp.model.CauEstatusgeneral;
import com.sfp.model.CauMesaservicio;
import com.sfp.model.CauSolicitudes;
import com.sfp.model.CauSolicitudtrabajos;
import com.sfp.model.CauTectipotrab;
import com.sfp.model.CauTrabajostecnicos;
import com.sfp.model.CauUsuarios;
import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
@Named(value = "trabtecnicoControlerRea")
//@Dependent
@ViewScoped
//@SessionScoped 
public class TrabTecnicoReaControler implements Serializable{
   
    // Se cambia el estatus del trabajo a 14 Trabajo asignado a tecnico
    @EJB
    private CauSolicitudtrabajosFacadeLocal EJBSolicitudTrabajos;
    
    // Se cambia el estatus de la solicitud a 5 Solicitud en proceso de atención
    @EJB
    private CauSolicitudesFacadeLocal EJBTSolicitudes;
    
    // Se utiliza para cambiar los estatus de las tablas 
    // "CauSolicitudtrabajos" y  "CauSolicitudes"
    @EJB
    private CauEstatusgeneralFacadeLocal EJBTCauEstatusgeneral;
    
    // Se utiliza para obtener las caracteristicas del usuario almacenadas 
    // en memoria
    @EJB
    private CauUsuariosFacadeLocal EJBUsuario;
    
    // Se usa para obtener los tecnicos que pueden atender un trabajo aqui hay
    // que completar la consulta con la tabla CAU_Empledos para obtener el 
    // nombre del tecnico
    @EJB
    private CauTectipotrabFacadeLocal EJBTecTipoTrabajo;
    
    // Tabla en la que se almacnara la relación entre los trabajos y los 
    //tecnicos que atienden la solicitud
    @EJB 
    private CauTrabajostecnicosFacadeLocal EJBTrabajostecnicos;
    
    @EJB 
    private CauEmpFacadeLocal EJBEmp;
    
    @EJB 
    private CauMesaservicioFacadeLocal EJBMesaServicio;
    
    @Inject 
    private Login01Controler Login01Controler1;
    
    
    private CauSolicitudtrabajos selected;    
    private CauTectipotrab selectedTec;    
    private Collection<CauSolicitudtrabajos> items;
    private Collection<CauTectipotrab> itemsTec;
    private Collection<CauSolicitudtrabajos> itemsdet; 
    private SolicitudComp SolicCompleta;
    private CauMesaservicio Mesaservicioc;

    private CauUsuarios Usua; 
    private String SalidaSolic;
    private String OrigenLlamada;
    private String DescAsignacion;

    public String getDescAsignacion() {
        return DescAsignacion;
    }

    public void setDescAsignacion(String DescAsignacion) {
        this.DescAsignacion = DescAsignacion;
    }    

    public String getMesaservicioc() {
        String Mesaservicio = Login01Controler1.getMesaDeServicio_parametro(); 
        Mesaservicioc = EJBMesaServicio.find(Integer.parseInt(Mesaservicio));
        return Mesaservicioc.getDescripcion();
    }

    public void setMesaservicioc(CauMesaservicio Mesaservicioc) {
        this.Mesaservicioc = Mesaservicioc;
    }
    
    public SolicitudComp getSolicCompleta() {
        return SolicCompleta;
    }

    public void setSolicCompleta(SolicitudComp SolicCompleta) {
        this.SolicCompleta = SolicCompleta;
    }
     public Collection<CauSolicitudtrabajos> getItemsdet() {
        return itemsdet;
    }

    public void setItemsdet(Collection<CauSolicitudtrabajos> itemsdet) {
        this.itemsdet = itemsdet;
    }
    public CauTectipotrab getSelectedTec() {
        return selectedTec;
    }

    public void setSelectedTec(CauTectipotrab selectedTec) {
        this.selectedTec = selectedTec;
    }

    public Collection<CauTectipotrab> getItemsTec() {
        return itemsTec;
    }

    public void setItemsTec(Collection<CauTectipotrab> itemsTec) {
        this.itemsTec = itemsTec;
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
    public CauSolicitudtrabajos getSelected() {
        return selected;
    }

    public void  prepareCreate(){
       DescAsignacion = "";       
    }
    
    public void setSelected(CauSolicitudtrabajos selected) {
        this.selected = selected;
    }

    public void  prepareAsigTec(){
        int idTrabajo;
        Calendar Fecha = Calendar.getInstance();
        String CadFecha = Integer.toString((Fecha.get(Calendar.DAY_OF_MONTH))) + "/" 
                          + Integer.toString((Fecha.get(Calendar.MONTH)+1)) + "/"
                          + Integer.toString((Fecha.get(Calendar.YEAR)));
        Date Fecha2 = new Date();
        // transacción 38 
        // seleccionar los tecnicos asociados al parametro 5  tecnicos
        idTrabajo = selected.getIdDeptrab().getIdTrabajo().getIdTrabajo();
        
        
        String consulta1 = "SELECT c FROM CauTectipotrab c join c.cauTipotrabajo e" 
                           + " WHERE e.idTrabajo = ?1 and ?2 "
                           + " BETWEEN c.fechaviginictectrab  AND c.fechavigfintectrab ";        
        setItemsTec(EJBTecTipoTrabajo.Consulta_TecTipoTrabajo2(consulta1,idTrabajo,Fecha2));
        selectedTec = null;
    }
    
    
    public Collection<CauSolicitudtrabajos> getItems() {
        OrigenLlamada = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Origen");
        if (items == null){
            ObtenTipoTrabajo();   
        }
        return items;     
    }

    public void setItems(Collection<CauSolicitudtrabajos> items) {
        this.items = items;
    }
          
    public  TrabTecnicoReaControler() {
        
    }

    public void ObtenTipoTrabajo() {
        int idstatus;
        idstatus = 4;
        String cadenaFiltro = "";
        // la trasaccion asociada a este proceso es la no 38 
        // los niveles de consulta son
        // 1 para mesa de servicio
        // 2 para departamentos
        // 3 trabajos                
        // en este caso se obtienen los departamentos (2) de la tranbsacción 38 
        // transaccion 8 que es cau_trabTecnico y el parametro de tipo 2 que son jefaturas de departamento
        cadenaFiltro = EJBSolicitudTrabajos.ObtenFiltro1(Usua.getClaveusua(), 38, 2);
       
        
        String consulta1;
        String Mesaservicio = Integer.toString(Usua.getIdAreagrupos().getIdMesaserv().getIdMesaserv());
        Mesaservicio = Login01Controler1.getMesaDeServicio_parametro();
        // todos los departamentos sin filtro para administradores
        consulta1 = "";
        if (cadenaFiltro.length() == 0) {
            consulta1 = "SELECT c FROM CauSolicitudtrabajos c Join c.idDeptrab e Join e.idDepartamentos f "
                    + " Join e.idTrabajo g Join c.idEstatus h Join c.idSolicitud i "
                    + " WHERE h.idEstatus  = ?1 and i.idEstatus.idEstatus in ( 6, 9) and  "
                    + " c.idSolicitud.idMesaserv.idMesaserv in ( " + Mesaservicio + ")";
        } else  {
            consulta1 = "SELECT c FROM CauSolicitudtrabajos c Join c.idDeptrab e Join e.idDepartamentos f "
                    + " Join e.idTrabajo g Join c.idEstatus h Join c.idSolicitud i"
                    + " WHERE h.idEstatus  = ?1 and i.idEstatus.idEstatus in ( 6, 9) and  "
                    + " c.idSolicitud.idMesaserv.idMesaserv in ( " + Mesaservicio + ")"
                    + " and  f.idDepartamentos " + cadenaFiltro;
        } 
        setItems(EJBSolicitudTrabajos.Consulta_Solicitudestrabajo(consulta1, idstatus));
    }

    
    public String GuardarTecnico() throws IOException{
        CauEstatusgeneral estatus;
        CauUsuarios usuario;
        CauTectipotrab tipotrab;
        CauTrabajostecnicos newItem;
        newItem = new CauTrabajostecnicos();            
        
        // crea el registro de la tabla CAU_TrabajosTecnicos
        // selected es del tipo CauSolicitudtrabajos
        newItem.setIdSoltrab(selected);        
        newItem.setIdUsuario(selectedTec.getCauUsuarios());
        newItem.setFechahoraasig(new Date());        
        DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
        newItem.setDesctrabtec("Trabajo Asignado a Tecnico " + hourFormat.format(new Date()));
        newItem.setNoservasignados(selected.getNodeservicios());
        EJBTrabajostecnicos.create(newItem);
        
        // Se actualiza el estatus de CAU_SolicitudTrabajos a 14 Trabajo asignado a Tecnico
        estatus = EJBTCauEstatusgeneral.find(14);
        selected.setIdEstatus(estatus);        
        EJBSolicitudTrabajos.edit(selected);
        
        // Se actualiza el estatus de CAU_Solicitudes a 5 Solicitudes en proceso de atención
        CauSolicitudes Solicitud;
        Solicitud = selected.getIdSolicitud();
        estatus = EJBTCauEstatusgeneral.find(5);
        EJBTSolicitudes.edit(Solicitud);
        

        items = null;
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("Origen", "CAU_Tipotrabajo");
        //FacesContext contex = FacesContext.getCurrentInstance();
        //    contex.getExternalContext().redirect("../MantTrab/index?faces-redirect=true");
        return "/PrimeFaces/cauTrabTecnico/index?faces-redirect=true";
    }
    
    public String redireccionURL(){
      //  FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("Origen", "CAU_Tipotrabajo");
      //  return "../PrimeFaces/MantTrab/index?faces-redirect=true"; 
      return "";
    }
    
     public String salir(){
        SalidaSolic = "0";  
       if (SalidaSolic.equals("0")){
         FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("Origen", "CAU_Tipotrabajo");  
         return "/PrimeFaces/Deptos/index?faces-redirect=true";  
       } else {
          FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("Origen", "CAU_Tipotrabajo");  
          return "/PrimeFaces/MantTrab/index?faces-redirect=true";
       }           
    }
    
    public void prepareDetalle() {
        if (selected != null) {
            String consulta1 = " SELECT c FROM CauSolicitudtrabajos c Join c.idSolicitud e "
                    + " WHERE e.idSolicitud = ?1";
            //int idSolicitud = Integer.parseInt(selected.getIdSolicitud());

            setItemsdet(EJBSolicitudTrabajos.Consulta_Solicitudestrabajo(consulta1, selected.getIdSolicitud().getIdSolicitud()));
            SolicCompleta = new SolicitudComp();
            if (itemsdet != null) {
                consulta1 = "Select c from CauEmp c where c.idNocredencial = ? ";
                CauEmp EmpSolic;
                Long Long01 = Long.parseLong(selected.getIdSolicitud().getUsuariosolicita());
                if (Long01 < 1000000) {
                    Long01 = 27000000 + Long01;
                }
                EmpSolic = EJBEmp.find(Long01);
                if (EmpSolic != null) {
                    SolicCompleta.setIdSolicitud(selected.getIdSolicitud().getIdSolicitud());
                    SolicCompleta.setFechaCaptura(selected.getIdSolicitud().getFechacaptura());
                    SolicCompleta.setNoCredUsuarioSolic(selected.getIdSolicitud().getUsuariosolicita());
                    SolicCompleta.setNombreUsuaSolic(EmpSolic.getNombre());
                    SolicCompleta.setDescUsuario(selected.getIdSolicitud().getDescusuario());
                    SolicCompleta.setDescEstatus(selected.getIdSolicitud().getIdEstatus().getDescestatustrab());

                    SolicCompleta.setUrSolic(EmpSolic.getUr());
                    SolicCompleta.setDenoSolic(EmpSolic.getDenominacion());
                    SolicCompleta.setDenComSolic(EmpSolic.getDencompleta());
                    SolicCompleta.setDescPuestoSolic(EmpSolic.getDescpuesto());
                    SolicCompleta.setNivelPuestoSolic(EmpSolic.getNivelpresup());
                    SolicCompleta.setCorreoSolic(EmpSolic.getCorreo());
                    SolicCompleta.setPisoSolic(EmpSolic.getPiso());
                    SolicCompleta.setAlaSolic(EmpSolic.getAla());
                    SolicCompleta.setUbicaconSolic(EmpSolic.getUbicacion());
                    SolicCompleta.setExtencion(EmpSolic.getExtension());
                    // obtiene la información dl usuario atendido
                    if (!selected.getIdSolicitud().getUsuarioatendido().trim().equals("")
                            && !selected.getIdSolicitud().getUsuariosolicita().equals(selected.getIdSolicitud().getUsuarioatendido())) {

                        Long01 = Long.parseLong(selected.getIdSolicitud().getUsuarioatendido());
                        if (Long01 < 1000000) {
                            Long01 = 27000000 + Long01;
                        }
                        EmpSolic = EJBEmp.find(Long01);

                       // EmpSolic = EJBEmp.find(selected.getIdSolicitud().getUsuariosolicita());
                        if (EmpSolic != null) {
                            SolicCompleta.setNombreUsuaAtend(EmpSolic.getNombre());
                            SolicCompleta.setUrAtend(EmpSolic.getUr());
                            SolicCompleta.setDenoAtend(EmpSolic.getDenominacion());
                            SolicCompleta.setDenComAtend(EmpSolic.getDencompleta());
                            SolicCompleta.setDescPuestoAtend(EmpSolic.getDescpuesto());
                            SolicCompleta.setNivelPuestoAtend(EmpSolic.getNivelpresup());
                            SolicCompleta.setCorreoAtend(EmpSolic.getCorreo());
                            SolicCompleta.setPisoAtend(EmpSolic.getPiso());
                            SolicCompleta.setAlaAtend(EmpSolic.getAla());
                            SolicCompleta.setUbicaconAtend(EmpSolic.getUbicacion());
                        }
                    }
                }
            }
        }
    }
     
}
