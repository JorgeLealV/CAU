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
import com.sfp.ejb.CauProcesoscorreosFacadeLocal;
import com.sfp.ejb.CauSolicitudesFacadeLocal;
import com.sfp.ejb.CauSolicitudtrabajosFacadeLocal;
import com.sfp.ejb.CauTectipotrabFacadeLocal;
import com.sfp.ejb.CauTrabajostecnicosFacadeLocal;
import com.sfp.ejb.CauUsuariosFacadeLocal;
import com.sfp.lib01.ServiceMailInterface;
import com.sfp.model.CauEmp;
import com.sfp.model.CauEstatusgeneral;
import com.sfp.model.CauMesaservicio;
import com.sfp.model.CauProcesoscorreos;
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
import java.util.Iterator;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.naming.NamingException;

/**
 *
 * @author jleal
 */
@Named(value = "trabtecnicoControler")
//@Dependent
@ViewScoped
//@SessionScoped 
public class TrabTecnicoControler implements Serializable{
   
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
    
    @EJB
    private ServiceMailInterface mail1;
    
    @EJB 
    private CauProcesoscorreosFacadeLocal EJBCauProcesoscorreos;
        
    private CauSolicitudtrabajos selected;    
    private CauTectipotrab selectedTec;    
    private Collection<CauSolicitudtrabajos> items;
    private Collection<CauTectipotrab> itemsTec;
    private Collection<CauSolicitudtrabajos> itemsdet; 
    private SolicitudComp SolicCompleta;
    private CauMesaservicio Mesaservicioc;
    private Collection<CauProcesoscorreos> Procesos;
    private CauEmp Empleado;
    private Collection<CauSolicitudtrabajos> items2;
    

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
        // transacción 8 
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
          
    public  TrabTecnicoControler() {
        
    }

    public void ObtenTipoTrabajo() {
        int idstatus;
        idstatus = 4;
        String cadenaFiltro = "";
        
        // la trasaccion asociada a este proceso es la no 8 
        // los niveles de consulta son
        // 1 para mesa de servicio
        // 2 para departamentos
        // 3 trabajos                
        // en este caso se obtienen los departamentos (2) de la tranbsacción 8 
        // transaccion 8 que es cau_trabTecnico y el parametro de tipo 2 que son jefaturas de departamento
        cadenaFiltro = EJBSolicitudTrabajos.ObtenFiltro1(Usua.getClaveusua(), 8, 2);
       
        
        
        
        String consulta1;
        String Mesaservicio = Integer.toString(Usua.getIdAreagrupos().getIdMesaserv().getIdMesaserv());
        Mesaservicio = Login01Controler1.getMesaDeServicio_parametro();
        // todos los departamentos sin filtro para administradores
        consulta1 = "";
        if (cadenaFiltro.length() == 0) {
            consulta1 = "SELECT c FROM CauSolicitudtrabajos c Join c.idDeptrab e Join e.idDepartamentos f "
                    + " Join e.idTrabajo g Join c.idEstatus h Join c.idSolicitud i "
                    + " WHERE h.idEstatus  = ?1 and i.idEstatus.idEstatus in ( 6, 9) and  "
                    + " c.idSolicitud.idMesaserv.idMesaserv in ( " + Mesaservicio + ")"
                    + " order by c.fechaasigdepto desc ";
        } else  {
            consulta1 = "SELECT c FROM CauSolicitudtrabajos c Join c.idDeptrab e Join e.idDepartamentos f "
                    + " Join e.idTrabajo g Join c.idEstatus h Join c.idSolicitud i"
                    + " WHERE h.idEstatus  = ?1 and i.idEstatus.idEstatus in ( 6, 9) and  "
                    + " c.idSolicitud.idMesaserv.idMesaserv in ( " + Mesaservicio + ")"
                    + " and  f.idDepartamentos " + cadenaFiltro
                    + " order by c.fechaasigdepto desc ";
        } 
        
        items2 = EJBSolicitudTrabajos.Consulta_Solicitudestrabajo(consulta1, idstatus);
        int credencial;
        
        for(CauSolicitudtrabajos pasosoltrab :items2 ){
            credencial = Integer.parseInt(pasosoltrab.getIdSolicitud().getUsuariosolicita());
            if (credencial  < 27000000){
                credencial = credencial + 27000000;
            }
            CauEmp aa = EJBEmp.ConsultaEmp(credencial);
            if (aa != null){
               pasosoltrab.setDesccancelacion(aa.getNombre());
            }
        }
        setItems(items2);
    }

    public void  prepareCreate(){
       DescAsignacion = "_";       
    }
    
    
    public String GuardarTecnico() throws InterruptedException, IOException {
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
        newItem.setDescasignacion(DescAsignacion);
        newItem.setIdUsuasig(Usua);
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
        // en este punto se envia correo al tecnico asignado verificando si 
        // tiene correo designado
        String correo = "";
        String subject = "";
        String body = "";
        String body2 = "";
        String CadenaCorreo;
        
       // String[] parts = trabajos.split("\\|");
        String[] BodySubject = null;
        String[] Partecorreo = null;
        String consulta1;
        CauProcesoscorreos recorre;
        Date fechaasig=null;
              
        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        // la etapa del proceso en este modulo es 2 que es el envio de solicitud a la mesa de servicio
        // para su atención se envian correos a todos los jefes de departamento que atienden ese servicio
        CadenaCorreo = "select c from CauProcesoscorreos c where c.idMesaserv.idMesaserv  = "
                + Login01Controler1.getMesaDeServicio_parametro()
                + " and c.idEtapaproceso.idEtapaproceso = ?1 ";
        
        Procesos = EJBCauProcesoscorreos.Consulta_Proceso(CadenaCorreo, 2);
        // if (Procesos != null) {
        if (Procesos != null) {    
             Iterator<CauProcesoscorreos> iProcesos = Procesos.iterator();
            // separación del body del subject

            iProcesos.hasNext();
            if (Procesos.size() > 0) {
                recorre = iProcesos.next();
                BodySubject = recorre.getIdTipodat().getDescripcion().split("\\#");
            }
            if (BodySubject.length == 2) {
                fechaasig = (new Date());
                BodySubject[0] = BodySubject[0].replaceAll("REPP1", Integer.toString(selected.getIdSolicitud().getIdSolicitud()));
                BodySubject[0] = BodySubject[0].replaceAll("REPP2", Integer.toString(selected.getIdSoltrab()));
                BodySubject[0] = BodySubject[0].replaceAll("REPP3", formateador.format(fechaasig));
                BodySubject[0] = BodySubject[0].replaceAll("REPP4", Integer.toString(selected.getIdDeptrab().getIdDepartamentos().getIdDepartamentos()));
                BodySubject[0] = BodySubject[0].replaceAll("REPP5", selected.getIdDeptrab().getIdDepartamentos().getDescripcion());
                BodySubject[0] = BodySubject[0].replaceAll("REPP6", Integer.toString(selected.getIdDeptrab().getIdTrabajo().getIdTrabajo()));
                BodySubject[0] = BodySubject[0].replaceAll("REPP7", selected.getIdDeptrab().getIdTrabajo().getDescripcion());
                BodySubject[0] = BodySubject[0].replaceAll("REPP8", Usua.getIdNocredencial().getNombre());
                BodySubject[0] = BodySubject[0].replaceAll("REPP9", selectedTec.getCauUsuarios().getIdNocredencial().getNombre());                 
                BodySubject[0] = BodySubject[0].replaceAll("REPPA1", (char) 13 + DescAsignacion);
                
                BodySubject[1] = BodySubject[1].replaceAll("REPP6", Integer.toString(selected.getIdDeptrab().getIdTrabajo().getIdTrabajo())); 
                BodySubject[1] = BodySubject[1].replaceAll("REPP7", selected.getIdDeptrab().getIdTrabajo().getDescripcion()); 
                                
                Partecorreo = BodySubject[0].split("\\|");
            }
            if (Long.parseLong(selected.getIdSolicitud().getUsuariosolicita()) < 1000000){
                String CadenaCons;
                long credencial = 27000000 + Long.parseLong(selected.getIdSolicitud().getUsuariosolicita());
                CadenaCons = "Select c from CauEmp where c.idNocredencial = ?1";
                Empleado = EJBEmp.ConsultaEmp(credencial);
            }
            CadenaCorreo = "";
            CadenaCorreo = CadenaCorreo + Partecorreo[0] + (char) 13 + (char) 13;
            CadenaCorreo = CadenaCorreo + Partecorreo[1] + (char) 13 + (char) 13;
            CadenaCorreo = CadenaCorreo + Partecorreo[2] + (char) 13 + (char) 13;
            CadenaCorreo = CadenaCorreo + Partecorreo[3] + (char) 13 + (char) 13;
            CadenaCorreo = CadenaCorreo + Partecorreo[4] + (char) 13 + (char) 13;
            CadenaCorreo = CadenaCorreo + Partecorreo[5] + (char) 13 + (char) 13;
            CadenaCorreo = CadenaCorreo + Partecorreo[6] + (char) 13 + (char) 13;
            CadenaCorreo = CadenaCorreo + Partecorreo[7] + (char) 13 + (char) 13;
            CadenaCorreo = CadenaCorreo + Partecorreo[8] + (char) 13 + (char) 13;
            
            String desc = selected.getDescripcion();
            String desc1 = "";
            String[] secciones = null;
            if (desc == null){
                desc = "";
            }
            if (desc.indexOf("|") > -1) {
               secciones = desc.split("\\|");
               for (String paso:secciones){
                   desc1 = desc1 +  paso + (char) 13 + (char) 13;
               }
            } else{
                desc1 = desc;
            }
            
            
            CadenaCorreo = CadenaCorreo + "Datos Solicitados [Servicio]" + desc1  + (char) 13 + (char) 13;
            CadenaCorreo = CadenaCorreo + "Usuario que solicita: " + Empleado.getNombre() + (char) 13  + (char) 13;            
            CadenaCorreo = CadenaCorreo + "Ubicación fisica del Usuario: " + Empleado.getUbicacion() + (char) 13 + (char) 13;
            CadenaCorreo = CadenaCorreo + "EVALUACIÓN DEL SERVICIO: " + (char) 13 + (char) 13 + (char) 13;
            CadenaCorreo = CadenaCorreo + "CALIDAD ............................... EXELENTE( )   BUENO( )   MALO( )   PESIMO( ) " + (char) 13 + (char) 13 ;
            CadenaCorreo = CadenaCorreo + "RAPIDEZ ............................... EXELENTE( )   BUENO( )   MALO( )   PESIMO( ) " + (char) 13 + (char) 13 ;
            CadenaCorreo = CadenaCorreo + "ATENCIÓN Y AMABILIDAD.... EXELENTE( )   BUENO( )   MALO( )   PESIMO( ) " + (char) 13 + (char) 13 ;            
            CadenaCorreo = CadenaCorreo + "                 FIRMA DEL USUARIO" + (char) 13 + (char) 13 ;
            CadenaCorreo = CadenaCorreo + (char) 13 + (char) 13 + (char) 13 + (char) 13;
            CadenaCorreo = CadenaCorreo + (char) 13 + (char) 13 + (char) 13 + (char) 13 + (char) 13;
            CadenaCorreo = CadenaCorreo + "___________________________________________________" + (char) 13 + (char) 13  ;
            CadenaCorreo = CadenaCorreo + "                      " + Empleado.getNombre() + (char) 13 ;
            
           

            String  bodyresp="";
            String  email= "";
//            subject = "Atención de reporte No." + selected.getIdSolicitud().getIdSolicitud() + "Servicio: " 
//                      + selected.getIdDeptrab().getIdTrabajo().getDescripcion();
            subject = BodySubject[1];

            body = CadenaCorreo;
            bodyresp = body;
            body2 = body + (char) 13 + "REPLICA";
            email = "cau_servicios@funcionpublica.gob.mx";
            boolean enviado = false;
            correo = selectedTec.getCauUsuarios().getCorreo();
            if (correo.length() > 0) {
                try {
                    mail1.enviaContentText(correo.trim(), null, null, subject, body, null);
                    mail1.enviaContentText("jleal@funcionpublica.gob.mx", null, null, subject, body2, null);
                } catch (IOException | NamingException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        
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

                        EmpSolic = EJBEmp.find(selected.getIdSolicitud().getUsuariosolicita());
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
