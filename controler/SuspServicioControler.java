/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfp.controler;


import com.sfp.cau.CalculaTiempo;
import com.sfp.cau.ImagenDiaHora;
import com.sfp.cau.SolicitudComp;
import com.sfp.ejb.CauDepjefeFacadeLocal;
import com.sfp.ejb.CauEmpFacadeLocal;
import com.sfp.ejb.CauEstatusgeneralFacadeLocal;
import com.sfp.ejb.CauHorausuFacadeLocal;
import com.sfp.ejb.CauLogoperFacadeLocal;
import com.sfp.ejb.CauMesaservicioFacadeLocal;
import com.sfp.ejb.CauProcesoscorreosFacadeLocal;
import com.sfp.ejb.CauSolicitudesFacadeLocal;
import com.sfp.ejb.CauSolicitudtrabajosFacadeLocal;
import com.sfp.ejb.CauSuspservicioFacadeLocal;
import com.sfp.ejb.CauTectipotrabFacadeLocal;
import com.sfp.ejb.CauTipooperFacadeLocal;
import com.sfp.ejb.CauTrabajostecnicosFacadeLocal;
import com.sfp.ejb.CauTransaccionFacadeLocal;
import com.sfp.ejb.CauUsuariosFacadeLocal;
import com.sfp.ejb.CauUsuariotipoFacadeLocal;
import com.sfp.lib01.ServiceMailInterface;
import com.sfp.model.CauDepjefe;
import com.sfp.model.CauEmp;
import com.sfp.model.CauEstandarservicio;
import com.sfp.model.CauEstatusgeneral;
import com.sfp.model.CauHorausu;
import com.sfp.model.CauLogoper;
import com.sfp.model.CauMesaservicio;
import com.sfp.model.CauProcesoscorreos;
import com.sfp.model.CauSolicitudes;
import com.sfp.model.CauSolicitudtrabajos;
import com.sfp.model.CauSuspservicio;
import com.sfp.model.CauTectipotrab;
import com.sfp.model.CauTipooper;
import com.sfp.model.CauTipotrabajo;
import com.sfp.model.CauTrabajostecnicos;
import com.sfp.model.CauTransaccion;
import com.sfp.model.CauUsuarios;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
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
import java.io.IOException;
import java.util.Iterator;
import java.util.logging.Logger;
import javax.naming.NamingException;

/**
 *
 * @author jleal
 */
@Named(value = "suspServicioControler")
//@Dependent
@ViewScoped
//@SessionScoped 
/* *********************************************
      Transacción 4
*********************************************** */


public class SuspServicioControler implements Serializable{
   
    private static final Logger logger = Logger.getLogger("CalculoTiempo");
    
    // Se cambia el estatus del trabajo a 13 Trabajo con Solicitud de Suspención de estandar
    @EJB
    private CauSolicitudtrabajosFacadeLocal EJBSolicitudTrabajos;
    
    // Se cambia el estatus de la solicitud a 11 Solicitud en suspención
    @EJB
    private CauSolicitudesFacadeLocal EJBTSolicitudes;
    
    // Se utiliza para cambiar los estatus de las tablas 
    // "CauSolicitudtrabajos" y  "CauSolicitudes"
    @EJB
    private CauEstatusgeneralFacadeLocal EJBTCauEstatusgeneral;
      
    // Tabla en la que se almacnara la relación entre los trabajos y los 
    //tecnicos que atienden la solicitud
    @EJB 
    private CauTrabajostecnicosFacadeLocal EJBTrabajostecnicos;
    
    @EJB 
    private CauSuspservicioFacadeLocal EJBSuspservicio;
   
    @EJB 
    private CauEmpFacadeLocal EJBEmp;
    
    @EJB
    private CauHorausuFacadeLocal EJBHorausu;
    
    @EJB
    private CauSuspservicioFacadeLocal EJBSuspServ;
    
    @EJB
    private CauTransaccionFacadeLocal EJBTransaccion;
    
    @EJB
    private CauTipooperFacadeLocal EJBTipoOper;
    
    @EJB
    private CauMesaservicioFacadeLocal EJBMesaServicio;
    
    @EJB
    private CauLogoperFacadeLocal EJBLogOper;
    
    @EJB
    private CauTrabajostecnicosFacadeLocal EJBTrabajoTec;
    
    @EJB
    private CauEmpFacadeLocal EJBEmpleado;
    
    @EJB 
    private CauDepjefeFacadeLocal EJBDepjefe;
    
    @EJB
    private CauUsuariotipoFacadeLocal EJBUsuariotipo;
    
    // Se usa para obtener los tecnicos que pueden atender un trabajo aqui hay
    // que completar la consulta con la tabla CAU_Empledos para obtener el 
    // nombre del tecnico
    @EJB
    private CauTectipotrabFacadeLocal EJBTecTipoTrabajo;
    
     @EJB 
    private CauProcesoscorreosFacadeLocal EJBCauProcesoscorreos;
    
    @Inject private Login01Controler Login01Controler1;
    
    @EJB
    private ServiceMailInterface mail1;
    
    private CauSolicitudtrabajos selected; 
    private CauTrabajostecnicos selected1;   
    private Collection<CauTrabajostecnicos> items1;
    private Collection<CauTrabajostecnicos> items2;
    private Collection<CauSolicitudtrabajos> itemsdet; 
    private CauSolicitudtrabajos solictrab;
    private Collection<CauHorausu> HorasUsu;
    private Collection<CauSuspservicio> SuspServicio;
    private Collection<CauTectipotrab> itemsTec;
    private CauTectipotrab selectedTec; 
    private CauSuspservicio SelectedSuspServ1;
    private Collection<CauProcesoscorreos> Procesos;
    private CauEmp emp;
    private CauEmp Empleado;
    private Collection<CauDepjefe> depjefe;
    CauSuspservicio suspservicio;
    
    private String DescSuspServ = "";
    private String DescReanServ = "";    
    
    private String DescConcServ = "";
    private String DescCancServ = "";    
    
    private SolicitudComp SolicCompleta;
    
    private String DescAsignacion;

    public String getDescAsignacion() {
        return DescAsignacion;
    }

    public void setDescAsignacion(String DescAsignacion) {
        this.DescAsignacion = DescAsignacion;
    }    

    public Collection<CauTrabajostecnicos> getItems2() {
        return items2;
    }

    public void setItems2(Collection<CauTrabajostecnicos> items2) {
        this.items2 = items2;
    }
        
    public CauSuspservicio getSelectedSuspServ1() {
        return SelectedSuspServ1;
    }

    public void setSelectedSuspServ1(CauSuspservicio SelectedSuspServ1) {
        this.SelectedSuspServ1 = SelectedSuspServ1;
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

    
    
    public String getDescSuspServ() {
        return DescSuspServ;
    }

    public void setDescSuspServ(String DescSuspServ) {
        this.DescSuspServ = DescSuspServ;
    }

    public String getDescConcServ() {
        return DescConcServ;
    }

    public void setDescConcServ(String DescConcServ) {
        this.DescConcServ = DescConcServ;
    }

    public String getDescCancServ() {
        return DescCancServ;
    }

    public void setDescCancServ(String DescCancServ) {
        this.DescCancServ = DescCancServ;
    }

    
    
    public String getDescReanServ() {
        return DescReanServ;
    }

    public void setDescReanServ(String DescReanServ) {
        this.DescReanServ = DescReanServ;
    }

    
    
    public Collection<CauHorausu> getHorasUsu() {
        return HorasUsu;
    }

    public void setHorasUsu(Collection<CauHorausu> HorasUsu) {
        this.HorasUsu = HorasUsu;
    }

    public Collection<CauSuspservicio> getSuspServicio() {
        return SuspServicio;
    }

    public void setSuspServicio(Collection<CauSuspservicio> SuspServicio) {
        this.SuspServicio = SuspServicio;
    }

    
    
    public SolicitudComp getSolicCompleta() {
        return SolicCompleta;
    }

    public void setSolicCompleta(SolicitudComp SolicCompleta) {
        this.SolicCompleta = SolicCompleta;
    }

    private CauUsuarios Usua; 
    private String SalidaSolic;
    private String OrigenLlamada;
    

    public String getSalidaSolic() {
        return SalidaSolic;
    }

    public void setSalidaSolic(String SalidaSolic) {
        this.SalidaSolic = SalidaSolic;
    } 
    
     public CauTrabajostecnicos getSelected1() {
        return selected1;
    }

    public void setSelected1(CauTrabajostecnicos selected1) {
        this.selected1 = selected1;
    }

    public Collection<CauTrabajostecnicos> getItems1() {
        if (items1 == null){
            ObtenTrabajoTecnico();   
        }
        return items1; 
    }

    public void setItems1(Collection<CauTrabajostecnicos> items1) {
        this.items1 = items1;
    }
    @PostConstruct
    private void init(){
       Usua = (CauUsuarios) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario"); 
       FacesContext facesContext = FacesContext.getCurrentInstance();
       Map params = facesContext.getExternalContext().getRequestParameterMap();       
   }   
    
    public void  prepareCreate(){
        DescSuspServ = "_";
        DescReanServ = "_";
        DescAsignacion = "_";
    }
    
    public void  prepareCreate2(){        
        DescReanServ = "_";
    }
    
    public void  prepareCreateCanConc(){        
         DescConcServ = "_";
         DescCancServ = "_"; 
    }
    
    public Collection<CauSolicitudtrabajos> getItemsdet() {
        return itemsdet;
    }

    public void setItemsdet(Collection<CauSolicitudtrabajos> itemsdet) {
        this.itemsdet = itemsdet;
    } 
    
    public  SuspServicioControler() {
        
    }
    
    public void  ObtenTrabajoTecnicoAnt(){    
        int idstatus;
        idstatus = 6;
        int tipousuario;
        int nivelconsulta;
        
        String consulta2 = "Select c from CauUsuariotipo c"
                    + " where c.idUsuario.claveusua = ?1 order by c.idTipousuario.idTipousuario";
        
        tipousuario = EJBSolicitudTrabajos.Consulta_TipoUsuario(consulta2,Usua.getClaveusua());
        nivelconsulta = 0; 
        if (tipousuario == 1){ // administrador
             nivelconsulta = 0;
        } else if ( tipousuario == 2) { // Jefe de Departamento
            nivelconsulta = 1;
        } else if ( tipousuario == 3) { // Tecnico
            nivelconsulta = 2;
        } else if ( tipousuario == 4){ // usuario Solicitante
            nivelconsulta = 3;
        }
        
        String cadenaFiltro= "";
        if (nivelconsulta == 0) {// administradores
           cadenaFiltro = "";           
        } else if (nivelconsulta == 1){ // jefes de departamento
            cadenaFiltro = EJBSolicitudTrabajos.ObtenFiltro(Usua.getClaveusua(), 8, nivelconsulta);
        } else if (nivelconsulta == 2) {// tecnicos
 
        }
        
        String consulta1;
        
        consulta1 = "";
        if (nivelconsulta == 0) {
            consulta1 = "SELECT c FROM CauTrabajostecnicos c Join c.idSoltrab f "
                    + " Join f.idEstatus g Join f.idSolicitud h join c.idUsuario u  "
                    + " WHERE  g.idEstatus  in (14 , 13) and h.idEstatus.idEstatus in ( ?1, 9) ";
        } else if (nivelconsulta == 1) {
            consulta1 = "SELECT c FROM CauTrabajostecnicos c Join c.idSoltrab f " 
                           + " Join f.idEstatus g Join f.idSolicitud h join c.idUsuario u  "                            
                           + " WHERE  g.idEstatus  in (14 , 13) and h.idEstatus.idEstatus in ( ?1, 9) and  "
                           + " f.idDeptrab.idDepartamentos.idDepartamentos in " + cadenaFiltro;
        } else if (nivelconsulta == 2) {
           consulta1 = "SELECT c FROM CauTrabajostecnicos c Join c.idSoltrab f " 
                           + " Join f.idEstatus g Join f.idSolicitud h join c.idUsuario u  "                            
                           + " WHERE  g.idEstatus  in (14 , 13) and h.idEstatus.idEstatus in ( ?1, 9) and  "
                           + " u.claveusua = '" + Usua.getClaveusua() + "'";
        }
       setItems1(EJBTrabajostecnicos.Consulta_TrabajosTecnico(consulta1,idstatus));
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
        idTrabajo = selected1.getIdSoltrab().getIdDeptrab().getIdTrabajo().getIdTrabajo();
        
        
        String consulta1 = "SELECT c FROM CauTectipotrab c join c.cauTipotrabajo e" 
                           + " WHERE e.idTrabajo = ?1 and ?2 "
                           + " BETWEEN c.fechaviginictectrab  AND c.fechavigfintectrab ";        
        setItemsTec(EJBTecTipoTrabajo.Consulta_TecTipoTrabajo2(consulta1,idTrabajo,Fecha2));
        //selectedTec = null;
    }
    
     public String GuardarTecnico() throws IOException{
//        CauEstatusgeneral estatus;
//        CauUsuarios usuario;
//        CauTectipotrab tipotrab;
        CauTrabajostecnicos newItem;
        newItem = new CauTrabajostecnicos();            
//        
        // crea el registro de la tabla CAU_TrabajosTecnicos
        // selected es del tipo CauSolicitudtrabajos
        newItem.setIdSoltrab(selected1.getIdSoltrab());        
        newItem.setIdUsuario(selectedTec.getCauUsuarios());
        newItem.setFechahoraasig(new Date());        
        DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
      
        newItem.setDesctrabtec(" Trabajo Reasignado a Tecnico " + hourFormat.format(new Date()));
        newItem.setNoservasignados(selected1.getIdSoltrab().getNodeservicios());
        newItem.setDescasignacion(DescAsignacion);
        newItem.setIdUsuasig(Usua);
        EJBTrabajostecnicos.create(newItem);
        // Se cierra la fechahoraconc  del registro actual 
        selected1.setFechahoraconc(new Date());
        selected1.setDesctrabtec(selected1.getDesctrabtec() + " Reasignado");
        CalculaTiempoSelected(true, selected1);
        // en este punto se envia correo al tecnico asignado verificando si 
        // tiene correo designado
        EnviaCorreoReasignacion();
        return "/PrimeFaces/cauSuspServicio/index?faces-redirect=true";
    }
    
    private void EnviaCorreoReasignacion() {
        String correoAsig = "";
        String correoReasig = "";
        String subject = "";
        String body = "";
        String body2 = "";
        String CadenaCorreo;

        // String[] parts = trabajos.split("\\|");
        String[] BodySubject = null;
        String[] Partecorreo = null;
        String consulta1;
        CauProcesoscorreos recorre;
        Date fechaasig = null;

        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        // la etapa del proceso en este modulo es 3 que es el envio de solicitud a la mesa de servicio
        // para su atención se envian correos a todos los jefes de departamento que atienden ese servicio
        CadenaCorreo = "select c from CauProcesoscorreos c where c.idMesaserv.idMesaserv  = "
                + Login01Controler1.getMesaDeServicio_parametro()
                + " and c.idEtapaproceso.idEtapaproceso = ?1 ";

        Procesos = EJBCauProcesoscorreos.Consulta_Proceso(CadenaCorreo, 3);
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

                BodySubject[0] = BodySubject[0].replaceAll("REPP1", Integer.toString(selected1.getIdSoltrab().getIdSolicitud().getIdSolicitud()));
                BodySubject[0] = BodySubject[0].replaceAll("REPP2", Integer.toString(selected1.getIdSoltrab().getIdSoltrab()));
                BodySubject[0] = BodySubject[0].replaceAll("REPP3", formateador.format(fechaasig));
                BodySubject[0] = BodySubject[0].replaceAll("REPP4", selected1.getIdUsuario().getIdNocredencial().getNombre());
                BodySubject[0] = BodySubject[0].replaceAll("REPP5", selectedTec.getCauUsuarios().getIdNocredencial().getNombre());
                BodySubject[0] = BodySubject[0].replaceAll("REPP6", Integer.toString(selected1.getIdSoltrab().getIdDeptrab().getIdDepartamentos().getIdDepartamentos()));
                BodySubject[0] = BodySubject[0].replaceAll("REPP7", selected1.getIdSoltrab().getIdDeptrab().getIdDepartamentos().getDescripcion());                
                BodySubject[0] = BodySubject[0].replaceAll("REPP8", Integer.toString(selected1.getIdSoltrab().getIdDeptrab().getIdTrabajo().getIdTrabajo()));
                BodySubject[0] = BodySubject[0].replaceAll("REPP9", selected1.getIdSoltrab().getIdDeptrab().getIdTrabajo().getDescripcion());
                BodySubject[0] = BodySubject[0].replaceAll("REPPA1",(char) 13 + DescAsignacion);

                BodySubject[1] = BodySubject[1].replaceAll("REPP1", Integer.toString(selected1.getIdSoltrab().getIdSolicitud().getIdSolicitud()));
                BodySubject[1] = BodySubject[1].replaceAll("REPP2", Integer.toString(selected1.getIdSoltrab().getIdSoltrab()));
                BodySubject[1] = BodySubject[1].replaceAll("REPP7", selected1.getIdSoltrab().getIdDeptrab().getIdDepartamentos().getDescripcion());                
                BodySubject[1] = BodySubject[1].replaceAll("REPP9", selected1.getIdSoltrab().getIdDeptrab().getIdTrabajo().getDescripcion());
                
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
            
            String bodyresp = "";
            String email = "";
//            subject = "Atención de reporte No." + selected.getIdSolicitud().getIdSolicitud() + "Servicio: " 
//                      + selected.getIdDeptrab().getIdTrabajo().getDescripcion();
            subject = BodySubject[1];

            body = CadenaCorreo;
            bodyresp = body;
            body2 = body + (char) 13 + "REPLICA";
            email = "cau_servicios@funcionpublica.gob.mx";
            boolean enviado = false;
            correoAsig = selectedTec.getCauUsuarios().getCorreo();
            correoReasig = selected1.getIdUsuario().getCorreo();
            if (correoAsig.length() > 0) {
                try {
                    mail1.enviaContentText(correoAsig.trim(), null, null, subject, body, null);
                    mail1.enviaContentText(correoReasig.trim(), null, null, subject, body, null);
                    mail1.enviaContentText("jleal@funcionpublica.gob.mx", null, null, subject, body2, null);
                } catch (IOException | NamingException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
     
      private void EnviaCorreoConclusion(){
        String correoSolicitante = "";        
        String subject = "";
        String body = "";
        String body2 = "";
        String CadenaCorreo;

        // String[] parts = trabajos.split("\\|");
        String[] BodySubject = null;
        String[] Partecorreo = null;
        String consulta1;
        CauProcesoscorreos recorre;
        Date fechaasig = null;

        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        // la etapa del proceso en este modulo es 4 que es el envio de solicitud a la mesa de servicio
        // para su atención se envian correos a todos los jefes de departamento que atienden ese servicio
        CadenaCorreo = "select c from CauProcesoscorreos c where c.idMesaserv.idMesaserv  = "
                + Login01Controler1.getMesaDeServicio_parametro()
                + " and c.idEtapaproceso.idEtapaproceso = ?1 ";

        Procesos = EJBCauProcesoscorreos.Consulta_Proceso(CadenaCorreo, 4);
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

                BodySubject[0] = BodySubject[0].replaceAll("REPP1", Integer.toString(selected1.getIdSoltrab().getIdSolicitud().getIdSolicitud()));
                BodySubject[0] = BodySubject[0].replaceAll("REPP2", Integer.toString(selected1.getIdSoltrab().getIdDeptrab().getIdTrabajo().getIdTrabajo()));
                BodySubject[0] = BodySubject[0].replaceAll("REPP3", selected1.getIdSoltrab().getIdDeptrab().getIdTrabajo().getDescripcion());               
                BodySubject[0] = BodySubject[0].replaceAll("REPP4", formateador.format(fechaasig));
                BodySubject[0] = BodySubject[0].replaceAll("REPP5", DescConcServ);

                BodySubject[1] = BodySubject[1].replaceAll("REPP1", Integer.toString(selected1.getIdSoltrab().getIdSolicitud().getIdSolicitud()));
                BodySubject[1] = BodySubject[1].replaceAll("REPP2", Integer.toString(selected1.getIdSoltrab().getIdDeptrab().getIdTrabajo().getIdTrabajo()));
                BodySubject[1] = BodySubject[1].replaceAll("REPP3", selected1.getIdSoltrab().getIdDeptrab().getIdTrabajo().getDescripcion());
                
                Partecorreo = BodySubject[0].split("\\|");
            }
            CadenaCorreo = "";
            CadenaCorreo = CadenaCorreo + Partecorreo[0] + (char) 13 + (char) 13;
            CadenaCorreo = CadenaCorreo + Partecorreo[1] + (char) 13 + (char) 13;
            CadenaCorreo = CadenaCorreo + Partecorreo[2] + (char) 13 + (char) 13;
            CadenaCorreo = CadenaCorreo + Partecorreo[3] + (char) 13 ;
            CadenaCorreo = CadenaCorreo + Partecorreo[4] + (char) 13 + (char) 13;

            String bodyresp = "";
            String email = "";
            String Credencialenvia = "";
//            subject = "Atención de reporte No." + selected.getIdSolicitud().getIdSolicitud() + "Servicio: " 
//                      + selected.getIdDeptrab().getIdTrabajo().getDescripcion();
            subject = BodySubject[1];

            body = CadenaCorreo;
            bodyresp = body;
            body2 = body + (char) 13 + "REPLICA";
            email = "cau_servicios@funcionpublica.gob.mx";
            boolean enviado = false;                                   
            Credencialenvia = selected1.getIdSoltrab().getIdSolicitud().getUsuariosolicita();
            emp = EJBEmpleado.ConsultaEmp(Long.parseLong(Credencialenvia));
            correoSolicitante = emp.getCorreo().trim();

            if (correoSolicitante.length() > 0) {
                try {
                    mail1.enviaContentText(correoSolicitante.trim(), null, null, subject, body, null);                    
                    mail1.enviaContentText("jleal@funcionpublica.gob.mx", null, null, subject, body2, null);
                } catch (IOException | NamingException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
      }
    
      private void EnvCorreoCancSuspRean(int etapa){
          String correoSolicitante = "";        
        String subject = "";
        String body = "";
        String body2 = "";
        String CadenaCorreo, CadenaCorreo2;

        // String[] parts = trabajos.split("\\|");
        String[] BodySubject = null;
        String[] Partecorreo = null;
        String[] Partecorreo1 = null;
        String consulta1;
        CauProcesoscorreos recorre;
        Date fechaasig = null;

        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        // la etapa del proceso en este modulo es 5,6,7 es el envio de correos de cancelacion, suspencion y
        // reanudacion
        // para su conocimiento se envian correos a todos los jefes de departamento que atienden ese servicio
        CadenaCorreo = "select c from CauProcesoscorreos c where c.idMesaserv.idMesaserv  = "
                + Login01Controler1.getMesaDeServicio_parametro()
                + " and c.idEtapaproceso.idEtapaproceso = ?1 ";

        Procesos = EJBCauProcesoscorreos.Consulta_Proceso(CadenaCorreo, etapa);
        if (Procesos != null) {
            
            Iterator<CauProcesoscorreos> iProcesos = Procesos.iterator();
            // separación del body del subject

            iProcesos.hasNext();
            if (Procesos.size() > 0) {
                recorre = iProcesos.next();
                BodySubject = recorre.getIdTipodat().getDescripcion().split("\\#");
            }
            if (BodySubject.length == 3) {
                fechaasig = (new Date());
                int idSoltrab;
                idSoltrab = selected1.getIdSoltrab().getIdSoltrab();        
                CauSolicitudtrabajos SolicTrabx = EJBSolicitudTrabajos.find(idSoltrab);
                BodySubject[0] = BodySubject[0].replaceAll("REPP1", Integer.toString(selected1.getIdSoltrab().getIdSolicitud().getIdSolicitud()));
                BodySubject[0] = BodySubject[0].replaceAll("REPP2", Integer.toString(selected1.getIdSoltrab().getIdSoltrab()));
                BodySubject[0] = BodySubject[0].replaceAll("REPP3", formateador.format(fechaasig));
                BodySubject[0] = BodySubject[0].replaceAll("REPP4", Integer.toString(selected1.getIdSoltrab().getIdDeptrab().getIdDepartamentos().getIdDepartamentos()));
                BodySubject[0] = BodySubject[0].replaceAll("REPP5", selected1.getIdSoltrab().getIdDeptrab().getIdDepartamentos().getDescripcion());
                BodySubject[0] = BodySubject[0].replaceAll("REPP6", Integer.toString(selected1.getIdSoltrab().getIdDeptrab().getIdTrabajo().getIdTrabajo()));
                BodySubject[0] = BodySubject[0].replaceAll("REPP7", selected1.getIdSoltrab().getIdDeptrab().getIdTrabajo().getDescripcion());               
                BodySubject[0] = BodySubject[0].replaceAll("REPP8", Usua.getIdNocredencial().getNombre());               
                
                BodySubject[1] = BodySubject[1].replaceAll("REPP1", Integer.toString(selected1.getIdSoltrab().getIdSolicitud().getIdSolicitud()));
                BodySubject[1] = BodySubject[1].replaceAll("REPP2", Integer.toString(selected1.getIdSoltrab().getIdSoltrab()));
                BodySubject[1] = BodySubject[1].replaceAll("REPP3", formateador.format(fechaasig));
                BodySubject[1] = BodySubject[1].replaceAll("REPP4", Integer.toString(selected1.getIdSoltrab().getIdDeptrab().getIdDepartamentos().getIdDepartamentos()));
                BodySubject[1] = BodySubject[1].replaceAll("REPP5", selected1.getIdSoltrab().getIdDeptrab().getIdDepartamentos().getDescripcion());
                BodySubject[1] = BodySubject[1].replaceAll("REPP6", Integer.toString(selected1.getIdSoltrab().getIdDeptrab().getIdTrabajo().getIdTrabajo()));
                BodySubject[1] = BodySubject[1].replaceAll("REPP7", selected1.getIdSoltrab().getIdDeptrab().getIdTrabajo().getDescripcion());               
                BodySubject[1] = BodySubject[1].replaceAll("REPP8", Usua.getIdNocredencial().getNombre());               
                
                
                switch (etapa) {
                    case 5:
                        // Descripción de cancelación
                        BodySubject[0] = BodySubject[0].replaceAll("REPP9", DescCancServ);
                        BodySubject[1] = BodySubject[1].replaceAll("REPP9", DescCancServ);
                        break;
                    case 6:
                        // Descripción de Suspención
                        BodySubject[0] = BodySubject[0].replaceAll("REPP9", DescSuspServ );
                        BodySubject[1] = BodySubject[1].replaceAll("REPP9", DescSuspServ );
                        break;
                    case 7:
                        // Descripcion de Reanudación
                        BodySubject[0] = BodySubject[0].replaceAll("REPP9", DescReanServ );
                        BodySubject[1] = BodySubject[1].replaceAll("REPP9", DescReanServ );
                        break;
                    default:
                        break;
                }
                    
               // BodySubject[0] = BodySubject[0].replaceAll("REPP5", SolicTrabx.getDesccancelacion());

                BodySubject[2] = BodySubject[2].replaceAll("REPP1", Integer.toString(selected1.getIdSoltrab().getIdSolicitud().getIdSolicitud()));
                BodySubject[2] = BodySubject[2].replaceAll("REPP2", Integer.toString(selected1.getIdSoltrab().getIdSoltrab()));
                BodySubject[2] = BodySubject[2].replaceAll("REPP7", selected1.getIdSoltrab().getIdDeptrab().getIdTrabajo().getDescripcion());
                
                
                Partecorreo = BodySubject[0].split("\\|");
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
            CadenaCorreo = CadenaCorreo + Partecorreo[8] + (char) 13 ;
            
            
            

            String bodyresp = "";
            String email = "";
            String Credencialenvia = "";
//            subject = "Atención de reporte No." + selected.getIdSolicitud().getIdSolicitud() + "Servicio: " 
//                      + selected.getIdDeptrab().getIdTrabajo().getDescripcion();
            subject = BodySubject[2];

            body = CadenaCorreo;
            bodyresp = body;
            body2 = body + (char) 13 + "REPLICA";
            email = "cau_servicios@funcionpublica.gob.mx";
            boolean enviado = false;                                   
            Credencialenvia = selected1.getIdSoltrab().getIdSolicitud().getUsuariosolicita();
            emp = EJBEmpleado.ConsultaEmp(Long.parseLong(Credencialenvia));
            correoSolicitante = emp.getCorreo().trim();

            if (correoSolicitante.length() > 0) {
                try {
                    mail1.enviaContentText(correoSolicitante.trim(), null, null, subject, body, null);                    
                    mail1.enviaContentText("jleal@funcionpublica.gob.mx", null, null, subject, body2, null);
                } catch (IOException | NamingException e) {
                    System.out.println(e.getMessage());
                }
            }
             // jefes de departamento           
            Partecorreo1 = BodySubject[1].split("\\|");
            
            CadenaCorreo2 = "";
            CadenaCorreo2 = CadenaCorreo2 + Partecorreo1[0] + (char) 13 + (char) 13;
            CadenaCorreo2 = CadenaCorreo2 + Partecorreo1[1] + (char) 13 + (char) 13;
            CadenaCorreo2 = CadenaCorreo2 + Partecorreo1[2] + (char) 13 + (char) 13;
            CadenaCorreo2 = CadenaCorreo2 + Partecorreo1[3] + (char) 13 + (char) 13;
            CadenaCorreo2 = CadenaCorreo2 + Partecorreo1[4] + (char) 13 + (char) 13;
            CadenaCorreo2 = CadenaCorreo2 + Partecorreo1[5] + (char) 13 + (char) 13;
            
            
            // Obtener los correos de todos los jefes de departamento que atienden el depto depto.getIdDepartamentos()
            consulta1 = "Select c from CauDepjefe c where c.cauDepartamentos.idDepartamentos = ?1 ";
            // trabajos = EJBSolicTrab.Consulta_Solicitudestrabajo(consulta1, selected.getIdSolicitud()); 
            depjefe = EJBDepjefe.Consulta_DepJefe(consulta1, selected1.getIdSoltrab().getIdDeptrab().getIdDepartamentos().getIdDepartamentos());
            subject = BodySubject[2];
            
            bodyresp = CadenaCorreo2;
            String parte6 = Partecorreo1[6];
            body2 = body + (char) 13 + "REPLICA";
            email = "cau_servicios@funcionpublica.gob.mx";
            enviado = false;
            for (CauDepjefe deptos1 : depjefe) {
                correoSolicitante = deptos1.getCauUsuarios().getCorreo();
                // body = bodyresp + (char) 13 + "Jefe de departamento = " + deptos1.getCauUsuarios().getIdNocredencial().getNombre();
                Partecorreo1[6] = parte6;
                Partecorreo1[6] = Partecorreo1[6].replaceAll("REPPA1", deptos1.getCauUsuarios().getIdNocredencial().getNombre());  
                CadenaCorreo2 = CadenaCorreo2 + Partecorreo1[6] + (char) 13 + (char) 13;
                CadenaCorreo2 = CadenaCorreo2 + Partecorreo1[7] + (char) 13 + (char) 13;
                CadenaCorreo2 = CadenaCorreo2 + Partecorreo1[8] + (char) 13 + (char) 13;
                CadenaCorreo2 = CadenaCorreo2 + Partecorreo1[9] + (char) 13 + (char) 13;
                body = CadenaCorreo2;
                // LLama a el correo con los parametros correo y CadenaCorreo
                // cau_servicios@funcionpublica.gob.mx                 
                // enviaContentText(String email, List<String> copias, List<String> copiasocultas, String subject, String body, DTODocumento documento);
                try {
                    mail1.enviaContentText(correoSolicitante.trim(), null, null, subject, body, null);
                    enviado = true;
                    CadenaCorreo2 = bodyresp;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
      }
      
      private void EnviaCorreoSuspension(){
          
      }
      private void EnviaCorreoReanudacion(){
          
      }
    
    public void  ObtenTrabajoTecnico(){ 
        int idstatus;

        String cadenaFiltroDep = "";
        String cadenaFiltroTra = "";
        int tipousu;
        tipousu = TipoUsuario();
        // la trasaccion asociada a este proceso es la no 4 
        // los niveles de consulta son
        // 1 para mesa de servicio
        // 2 para departamentos
        // 3 trabajos                
        // en este caso se obtienen los departamentos (2) de la tranbsacción 4 
        
        cadenaFiltroDep = EJBSolicitudTrabajos.ObtenFiltro1(Usua.getClaveusua(), 4, 2);
        // si cadenaFiltroDep no es vacia entonces se trata de un usuario de jefe de departamento 
        // por lo tanto se hace filtro por  mesa y departamento ya no se toma el filtro de 
        // trabajos asignados al tenico ya que aunque sea tecnico de algun trabajo, como es jefe de
        // departamento puede administrar los trabajos asignados a su departamento en su totalidad
        // si cadena Filtro es vacia hay dos posibilidades que sea un administrador de 
        // mnesa o sea un tecnico por lo tanto se debe de hacer una consulta de trabajos asignados al usuario 
        // si existen trabajos asignados al usuario entonces se hace el filtro por mesa y trabajos 
        cadenaFiltroTra = EJBSolicitudTrabajos.ObtenFiltro1(Usua.getClaveusua(), 4, 3);

        idstatus = 6;
        String consulta1;
        String Mesaservicio = Integer.toString(Usua.getIdAreagrupos().getIdMesaserv().getIdMesaserv());
        Mesaservicio = Login01Controler1.getMesaDeServicio_parametro();
        
        // todos los departamentos sin filtro para administradores
        consulta1 = "";
        
        // Sin departamentos y sin trabajos entonces es un administraor
        // tipo de usuario 1 o 5 es administrador general o de mesa de servicio
        //if ((cadenaFiltroDep.length() == 0) && (cadenaFiltroTra.length() == 0)) {
        if (tipousu == 1 || tipousu == 5) {    
            consulta1 = "SELECT c FROM CauTrabajostecnicos c Join c.idSoltrab f "
                    + " Join f.idEstatus g Join f.idSolicitud h join c.idUsuario u  "
                    + " WHERE  g.idEstatus  in (14 , 13, 17) and h.idEstatus.idEstatus in ( ?1, 9) and "
                    + " h.idMesaserv.idMesaserv in ( " + Mesaservicio + ") and c.fechahoraconc = null" 
                    + " order by c.fechahoraasig desc ";
            // tiene deprtamentos aunque tenga trabajos se considera jefe de departamento  
        //} else if (cadenaFiltroDep.length() > 0) {
        } else if (tipousu ==  2) {    
            if (cadenaFiltroTra.length() > 0) {
                // ademas de departamento tiene asignado trabajos -> es jefe de departamento
                consulta1 = "SELECT c FROM CauTrabajostecnicos c Join c.idSoltrab f "
                        + " Join f.idEstatus g Join f.idSolicitud h join c.idUsuario u  "
                        + " WHERE  g.idEstatus  in (14 , 13, 17) and h.idEstatus.idEstatus in ( ?1, 9) and  "
                        + " h.idMesaserv.idMesaserv in ( " + Mesaservicio + ") and "
                        + " ( f.idDeptrab.idDepartamentos.idDepartamentos " + cadenaFiltroDep
                        + " or f.idDeptrab.idTrabajo.idTrabajo " + cadenaFiltroTra + ") and c.fechahoraconc = null"
                        + " order by c.fechahoraasig desc ";
            } else {
                // Tiene solo departamentos es un jefe de departamento
                consulta1 = "SELECT c FROM CauTrabajostecnicos c Join c.idSoltrab f "
                        + " Join f.idEstatus g Join f.idSolicitud h join c.idUsuario u  "
                        + " WHERE  g.idEstatus  in (14 , 13, 17) and h.idEstatus.idEstatus in ( ?1, 9) and  "
                        + " h.idMesaserv.idMesaserv in ( " + Mesaservicio + ") and c.fechahoraconc = null and "
                        + " f.idDeptrab.idDepartamentos.idDepartamentos " + cadenaFiltroDep
                        + " order by c.fechahoraasig desc ";
            }
            //tiene trabajos y no tiene departamento se considera tecnico   
        //} else if ((cadenaFiltroTra.length() > 0) && (cadenaFiltroDep.length() == 0)) {
        } else if (tipousu ==  3) {    
            consulta1 = "SELECT c FROM CauTrabajostecnicos c Join c.idSoltrab f "
                    + " Join f.idEstatus g Join f.idSolicitud h join c.idUsuario u  "
                    + " WHERE  g.idEstatus  in (14 , 13, 17) and h.idEstatus.idEstatus in ( ?1, 9) and  "
                    + " h.idMesaserv.idMesaserv in ( " + Mesaservicio + ") and c.fechahoraconc = null and "
                    + " f.idDeptrab.idTrabajo.idTrabajo " + cadenaFiltroTra 
                    + " and c.idUsuario.idUsuario = " + Integer.toString(Usua.getIdUsuario())
                    + " order by c.fechahoraasig desc ";
        }
       setItems1(EJBTrabajostecnicos.Consulta_TrabajosTecnico(consulta1,idstatus));
       // en este punto se actualiza el semaforo de tiempo para cada servicio calculando el tiempo que 
       // lleva en la asignación y compararlo con el tiempo del limite del semaforo de la tabla
       // Random aleatorio = new Random(System.currentTimeMillis());
       for(CauTrabajostecnicos trab:items1){
          trab.setFechahoraconc(new Date()); 
          CalculaTiempos(trab); 
       }
    }
    
    public int TipoUsuario(){       
       int tipousuario = 0;
       tipousuario = 0;
       String consulta;
       if (Usua != null) {
           consulta = "Select c from CauUsuariotipo c"
                   + " where c.idUsuario.claveusua = ?1 order by c.idTipousuario.idTipousuario";
           tipousuario = EJBUsuariotipo.Consulta_TipoUsuario(consulta, Usua.getClaveusua());
       }       
       return tipousuario;
   }
    
    private void CalculaTiempos(CauTrabajostecnicos trab) {
        int defase1 = 0;
        int defase2 = 0;
        int defase3 = 0;
        int tiempocalculado = 0;
        // tiempocalculado = aleatorio.nextInt(trab.getIdSoltrab().getIdDeptrab().getIdTrabajo().getIdEstserv().getMinutosnormal() + 20);           
        
        CalculaTiempoSelected(false, trab);
        tiempocalculado = trab.getTiempocalculado();
        defase1 = trab.getIdSoltrab().getIdDeptrab().getIdTrabajo().getIdEstserv().getDefase1();
        defase2 = trab.getIdSoltrab().getIdDeptrab().getIdTrabajo().getIdEstserv().getDefase2();
        defase3 = trab.getIdSoltrab().getIdDeptrab().getIdTrabajo().getIdEstserv().getDefase3();

        String Fases = (char) 13 + "Fase_1:" + Integer.toString(defase1) + (char) 13 + "Fase_2:" + Integer.toString(defase2)
                + (char) 13 + "Fase_3:" + Integer.toString(defase3);

        if (tiempocalculado <= defase1) {
            trab.setCumpleestandar("Verde: Tiempo= " + Integer.toString(tiempocalculado) + "\r\b" + Fases);
        } else if ((defase1 < tiempocalculado) && (tiempocalculado <= defase2)) {
            trab.setCumpleestandar("Naranja: " + Integer.toString(tiempocalculado) + "\r\b" + Fases);
        } else if (((defase2 < tiempocalculado) && (tiempocalculado <= defase3))) {
            trab.setCumpleestandar("Rojo: " + Integer.toString(tiempocalculado) + Fases);
        } else if (defase3 < tiempocalculado) {
            trab.setCumpleestandar("Negro: " + Integer.toString(tiempocalculado) + "\r\b" + Fases);
        } else {
            trab.setCumpleestandar("Sin calculo");
        }
    }
    
    public String refrescar(){
        selected1=null;
        return "/PrimeFaces/cauSuspServicio/index?faces-redirect=true";
    }
    
    
     // el servicio seleccionado se establece en estatus 13 
     // " trabajo con solicitud de suspencion de estandar"
    public String SuspServ(){
        CauEstatusgeneral estatus;
        int idSoltrab;
        estatus = EJBTCauEstatusgeneral.find(13);
        idSoltrab = selected1.getIdSoltrab().getIdSoltrab();
        CauSolicitudtrabajos SolicTrab = EJBSolicitudTrabajos.find(idSoltrab);
        SolicTrab.setIdEstatus(estatus);
        EJBSolicitudTrabajos.edit(SolicTrab);
        //return "/PrimeFaces/cauSuspServicio/index?faces-redirect=true";
        
        // Se crea registro en la tabla CauSuspservicio 
        // con campo FechaHoraReanud = 01/01/999
        
        suspservicio = new CauSuspservicio();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
           Date fecha1 = formatter.parse("01/01/9999"); 
           suspservicio.setFechahorareanud(fecha1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        suspservicio.setFechahorasusp(new Date());
        suspservicio.setIdTrabtec(selected1);
        suspservicio.setDescsuspserv(DescSuspServ);
        suspservicio.setReasuspserv(DescReanServ);
        suspservicio.setIdUsuasusp(Usua);
        EJBSuspservicio.create(suspservicio);
        EnvCorreoCancSuspRean(6);
        setItems1(null);
        selected1=null;
        return "";
    }
    
    public String  ReanudaServ(){
        CauEstatusgeneral estatus;
        int idSoltrab;        
        idSoltrab = selected1.getIdSoltrab().getIdSoltrab();
        
        //return "/PrimeFaces/cauSuspServicio/index?faces-redirect=true";
        
        // busca el registro de suspencion de servicio que se guardo previamente 
        // en la solicitud de suspención de servicio para establecer la 
        // fecha de reanudación
        int soltrab;
        soltrab = idSoltrab;
        
        String consulta1 = "SELECT c FROM CauSuspservicio c join c.idTrabtec d join d.idSoltrab g"
                           + " WHERE  g.idEstatus.idEstatus in (13) and g.idSoltrab = ?1";
        //                 + "        and c.fechahorareanud = ";
        
        Collection<CauSuspservicio> itemassusp;
        itemassusp = EJBSuspservicio.Consulta_TrabajosTecnico(consulta1,soltrab);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String  fecha1; 
        for (CauSuspservicio aa: itemassusp){
            fecha1 = "";            
            fecha1 = formatter.format(aa.getFechahorareanud());                  
            if (fecha1.equals("01/01/9999")){
                aa.setFechahorareanud(new Date());
                aa.setIdUsuarean(Usua);
                aa.setReasuspserv(DescReanServ);
                suspservicio = aa;
                EJBSuspservicio.edit(aa);
            } 
        }
        
        // se actualiza el estatus del servicio a 14 trabajo asignado a tecnico
        estatus = EJBTCauEstatusgeneral.find(14);
        CauSolicitudtrabajos SolicTrab = EJBSolicitudTrabajos.find(idSoltrab);
        SolicTrab.setIdEstatus(estatus);       
        EJBSolicitudTrabajos.edit(SolicTrab); 
        EnvCorreoCancSuspRean(7);
        setItems1(null);
        selected1=null;
        return "";
    }
    
    
    
    public String  ConclucionServ(){
        CauEstatusgeneral estatus;
        CauSolicitudes Solicitud ;
        int idSoltrab;
        int idSolicitud;
         
        Collection<CauSolicitudtrabajos> itemsdet3;
        
        
        estatus = EJBTCauEstatusgeneral.find(2);
        idSoltrab = selected1.getIdSoltrab().getIdSoltrab();
        CauSolicitudtrabajos SolicTrab = EJBSolicitudTrabajos.find(idSoltrab);
        SolicTrab.setIdEstatus(estatus);
        SolicTrab.setDesctrabusuario(DescConcServ);        
        EJBSolicitudTrabajos.edit(SolicTrab); 
        // se verifica que sea el ultimo trabajo de la solicitud con estatus 14 o 13 
        // y se concluye con estatus de 8 "Solicitud atendida totalmente"
        // si el servicio no es el ultimo se pone el estatus de la solicitud en 
        // estatus a 9 "Solicitud atendida parcialmente "
        
        idSolicitud = selected1.getIdSoltrab().getIdSolicitud().getIdSolicitud();
        Solicitud = EJBTSolicitudes.find(idSolicitud);
        
        
        String consulta1 = "SELECT c FROM CauSolicitudtrabajos c Join c.idSolicitud f "
                           + " WHERE  f.idSolicitud = ?1 ";
        itemsdet3 = EJBSolicitudTrabajos.Consulta_Solicitudestrabajo(consulta1,idSolicitud);
        
        
        CauTrabajostecnicos trabtec = EJBTrabajostecnicos.find(selected1.getIdTrabtec()) ;
        trabtec.setFechahoraconc(new Date());
        trabtec.setIdUsucanconc(Usua);
        trabtec.setDesctrabtec("Concluido-" + trabtec.getDesctrabtec());
        EJBTrabajostecnicos.edit(trabtec);
        selected1 = EJBTrabajostecnicos.find(selected1.getIdTrabtec());
        
        
        //String consulta2 = "SELECT c FROM CauTrabajosTecnicos c Join c.idSoltrab f "
        //                   + " WHERE  f.idSolicitud.idSolicitud = ?1 ";
        //itemsdet3 = EJBSolicitudTrabajos.Consulta_Solicitudestrabajo(consulta1,idSolicitud);
        
        //for (CauSolicitudtrabajos paso : Solicitud.getCauSolicitudtrabajosCollection()){
//        for (CauSolicitudtrabajos paso : itemsdet3){            
//            if (paso.getIdEstatus().getIdEstatus() == 14 || paso.getIdEstatus().getIdEstatus() == 13 
//                                                         || paso.getIdEstatus().getIdEstatus() == 4){
//                parcial = true;
//            }
//        }
//        
//        // se pone el estatus a 8 
//        if (parcial){
//            estatus = EJBTCauEstatusgeneral.find(9);            
//        }else {
//          // se pone el estatus a 9  
//           estatus = EJBTCauEstatusgeneral.find(8);
//        }
        int contador = 0;
        boolean parcial = false;
        boolean cantotal = false;
        boolean canparcial = false;
        for (CauSolicitudtrabajos paso : itemsdet3) {
            
            if (paso.getIdEstatus().getIdEstatus() == 14 || paso.getIdEstatus().getIdEstatus() == 13
                    || paso.getIdEstatus().getIdEstatus() == 4) {
                parcial = true;
            } else if (paso.getIdEstatus().getIdEstatus() == 3)  {
                contador++;
                canparcial = true;
                if (itemsdet3.size() == contador){
                   cantotal = true;
                }                
            }
        }
        
        // se pone el estatus a 8 ojo verificar esta parte esttus 9 10 y 16
        if (parcial){
            estatus = EJBTCauEstatusgeneral.find(9);  // atendida parcialmente
        }else if (cantotal) {
           estatus = EJBTCauEstatusgeneral.find(10);  //cancelada totalmente 
        } else if (canparcial) {            
            estatus = EJBTCauEstatusgeneral.find(16); //atendida con trabajos cancelados 
        } else {
            estatus = EJBTCauEstatusgeneral.find(8); // Solicitud atendida totalmente
        }

        Solicitud.setIdEstatus(estatus);
        EJBTSolicitudes.edit(Solicitud);
        // envio de correo a Servidor publico de la conclucion del servicio
        EnviaCorreoConclusion();
                
        CalculaTiempoSelected(true,selected1);
        setItems1(null);
        selected1=null;
        return "";
    }
     
    
    public String  CancelaServicio(){
        CauEstatusgeneral estatus;
        CauSolicitudes Solicitud ;
        int idSoltrab;
        int idSolicitud;
        Collection<CauSolicitudtrabajos> itemsdet3;
              
        
        estatus = EJBTCauEstatusgeneral.find(3);
        idSoltrab = selected1.getIdSoltrab().getIdSoltrab();        
        CauSolicitudtrabajos SolicTrab = EJBSolicitudTrabajos.find(idSoltrab);
        SolicTrab.setIdEstatus(estatus);
        SolicTrab.setFechacanctrabajo(new Date());
        SolicTrab.setDesccancelacion(DescCancServ);
        EJBSolicitudTrabajos.edit(SolicTrab); 
        // se verifica que sea el ultimo trabajo de la solicitud con estatus 14 o 13 
        // y se concluye con estatus de 8 "Solicitud atendida totalmente"
        // si el servicio no es el ultimo se pone el estatus de la solicitud en 
        // estatus a 9 "Solicitud atendida parcialmente "
        
        idSolicitud = selected1.getIdSoltrab().getIdSolicitud().getIdSolicitud();
        Solicitud = EJBTSolicitudes.find(idSolicitud);
        
        String consulta1 = "SELECT c FROM CauSolicitudtrabajos c Join c.idSolicitud f "
                           + " WHERE  f.idSolicitud = ?1 ";
        itemsdet3 = EJBSolicitudTrabajos.Consulta_Solicitudestrabajo(consulta1,idSolicitud);
        
        CauTrabajostecnicos trabtec = EJBTrabajostecnicos.find(selected1.getIdTrabtec()) ;
        trabtec.setFechahoraconc(new Date());
        trabtec.setIdUsucanconc(Usua);
        trabtec.setDesctrabtec("Cancelado-" + trabtec.getDesctrabtec());
        EJBTrabajostecnicos.edit(trabtec);
        selected1 = EJBTrabajostecnicos.find(selected1.getIdTrabtec());
        
        
        
       // for (CauSolicitudtrabajos paso : Solicitud.getCauSolicitudtrabajosCollection()) {
         int contador = 0;
        boolean parcial = false;
        boolean total = false; 
        boolean cantotal = false;
        boolean canparcial = false;
        for (CauSolicitudtrabajos paso : itemsdet3) {
            
            if (paso.getIdEstatus().getIdEstatus() == 14 || paso.getIdEstatus().getIdEstatus() == 13
                    || paso.getIdEstatus().getIdEstatus() == 4) {
                parcial = true;
            } else if (paso.getIdEstatus().getIdEstatus() == 3)  {
                contador++;
                canparcial = true;
                if (itemsdet3.size() == contador){
                   cantotal = true;
                }                
            }
        }
        
        // se pone el estatus a 8 ojo verificar esta parte esttus 9 10 y 16
        if (parcial){
            estatus = EJBTCauEstatusgeneral.find(9);  // atendida parcialmente
        }else if (cantotal) {
           estatus = EJBTCauEstatusgeneral.find(10);  //cancelada totalmente 
        } else if (canparcial) {            
            estatus = EJBTCauEstatusgeneral.find(16); //atendida con trabajos cancelados 
        } else {
            estatus = EJBTCauEstatusgeneral.find(8); // Solicitud atendida totalmente
        }
                   
        Solicitud.setIdEstatus(estatus);
        Solicitud.setFechacancela(new Date());
        EJBTSolicitudes.edit(Solicitud); 
        EnvCorreoCancSuspRean(5);
        //return "/PrimeFaces/cauSuspServicio/index?faces-redirect=true";
        setItems1(null);
        selected1=null;
       
        return "";
    }
    
    public boolean selectedrec1(){
        boolean paso1;
        paso1 = false;
        if (selected1 == null){
            paso1 = true;
        } else if (selected1.getIdSoltrab().getIdEstatus().getIdEstatus() != 13) {
            paso1 = false;
        } else {
            paso1 = true;
        }            
        return paso1;
    }
    
    public boolean selectedrec2(){
        boolean paso1;
        paso1 = false;
        if (selected1 == null){
            paso1 = true;
        } else if (selected1.getIdSoltrab().getIdEstatus().getIdEstatus() == 13) {
            paso1 = false;
        } else {
            paso1 = true;
        }
        return paso1;
    }
    
    public void prepareDetalle() {        
        if (selected1 != null) {
            String consulta1 = " SELECT c FROM CauSolicitudtrabajos c Join c.idSolicitud e "
                    + " WHERE e.idSolicitud = ?1";
            //int idSolicitud = Integer.parseInt(selected.getIdSolicitud());            
            setItemsdet(EJBSolicitudTrabajos.Consulta_Solicitudestrabajo(consulta1, selected1.getIdSoltrab().getIdSolicitud().getIdSolicitud()));
            
//            consulta1 = " SELECT c FROM CauTrabajostecnicos c"
//                    + " WHERE c.idSoltrab = ?1 and e.";
//            //int idSolicitud = Integer.parseInt(selected.getIdSolicitud());            
//            setItemsdet(EJBSolicitudTrabajos.Consulta_Solicitudestrabajo(consulta1, selected1.getIdSoltrab().getIdSolicitud().getIdSolicitud()));
                        
            SolicCompleta = new SolicitudComp();
            if (itemsdet != null) {
                consulta1 = "Select c from CauEmp c where c.idNocredencial = ? ";
                CauEmp EmpSolic;
                Long Long01 = Long.parseLong(selected1.getIdSoltrab().getIdSolicitud().getUsuariosolicita());
                    if (Long01 < 1000000){
                        Long01 = 27000000 + Long01;
                    }
                EmpSolic = EJBEmp.find(Long01);
                if (EmpSolic != null) {
                    SolicCompleta.setIdSolicitud(selected1.getIdSoltrab().getIdSolicitud().getIdSolicitud());
                    SolicCompleta.setFechaCaptura(selected1.getIdSoltrab().getIdSolicitud().getFechacaptura());
                    SolicCompleta.setNoCredUsuarioSolic(selected1.getIdSoltrab().getIdSolicitud().getUsuariosolicita());
                    SolicCompleta.setNombreUsuaSolic(EmpSolic.getNombre());
                    SolicCompleta.setDescUsuario(selected1.getIdSoltrab().getIdSolicitud().getDescusuario());
                    SolicCompleta.setDescEstatus(selected1.getIdSoltrab().getIdSolicitud().getIdEstatus().getDescestatustrab());
                    
                    SolicCompleta.setDescTrabUsuario(selected1.getIdSoltrab().getDesctrabusuario());
                    SolicCompleta.setDescripcion(selected1.getIdSoltrab().getDescripcion());
                    
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
                    if (!selected1.getIdSoltrab().getIdSolicitud().getUsuarioatendido().trim().equals("")
                            && !selected1.getIdSoltrab().getIdSolicitud().getUsuariosolicita().equals(selected1.getIdSoltrab().getIdSolicitud().getUsuarioatendido())) {
                        
                        Long01 = Long.parseLong(selected1.getIdSoltrab().getIdSolicitud().getUsuarioatendido());
                        if (Long01 < 1000000){
                            Long01 = 27000000 + Long01;
                        }
                        EmpSolic = EJBEmp.find(Long01);
                        
                        Long NoCred = Long.parseLong(selected1.getIdSoltrab().getIdSolicitud().getUsuariosolicita());
                        
//                        if (NoCred < 1000000){
//                            NoCred = 27000000 + NoCred;
//                        }
//                                                
//                        EmpSolic = EJBEmp.find(NoCred);
                        
                        // EmpSolic = EJBEmp.find(selected1.getIdSoltrab().getIdSolicitud().getUsuariosolicita());
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
        
    public void prepareSuspencion (){
         // selected1 es la selección de CauTrabajostecnicos 
        if (selected1 != null) {
          String consulta1 = " SELECT c FROM CauSuspservicio c Join c.idTrabtec.idSoltrab  e "
                    + " WHERE e.idSoltrab = ?1 order by c.fechahorasusp DESC";            
            setSuspServicio(EJBSuspServ.Consulta_TrabajosTecnico(consulta1, selected1.getIdSoltrab().getIdSoltrab()));  
        }
    }
    
     public void prepareReasignacion (){
        // selected1 es la selección de CauTrabajostecnicos 
        if (selected1 != null) {
            String consulta1 = " SELECT c FROM CauTrabajostecnicos c Join c.idSoltrab e "
                    + " WHERE e.idSoltrab = ?1 order by c.fechahoraasig DESC";
            //int idSolicitud = Integer.parseInt(selected.getIdSolicitud());
            
            setItems2(EJBTrabajoTec.Consulta_TrabajosTecnico(consulta1,selected1.getIdSoltrab().getIdSoltrab()));
        }
    }
    
    public String CalculaTiempoSelected(boolean logs, CauTrabajostecnicos selectedx ) {
        // se consultan los horarios de labores anuales , descansos anuales 
        // fechas especiales de descanso vacaciones etc. Ver documentación
        long minutos=0;
        Calendar Fechapaso = Calendar.getInstance();
        Fechapaso.setTime(selectedx.getFechahoraasig());
        
        String Fecha1 = String.valueOf(Fechapaso.get(Calendar.DAY_OF_MONTH)) + "/" + 
                 String.valueOf(Fechapaso.get(Calendar.MONTH)+1) + "/" +
                 String.valueOf(Fechapaso.get(Calendar.YEAR)) ;
        //logger.log(Level.INFO, "Fecha: {0} ", new Object[]{Fecha1}); 
        //System.out.println("Fecha= " + Fecha1);
        String consulta1 = "SELECT c FROM CauHorausu c "
                + " WHERE (( ?1 "  
                + " between c.fechaini and c.fechafin) and "  +  "(c.idUsuario.idUsuario = ?2)) ";
        //logger.log(Level.INFO, " Antes Consulta: {0}  ", new Object[]{consulta1}); 
        
        setHorasUsu(EJBHorausu.Consulta_Usua(consulta1, selectedx.getFechahoraasig(), selectedx.getIdUsuario().getIdUsuario()));
        // se consulta del trabajo las suspenciones de servicio que ha tenido 
        // durante su atención
        //logger.log(Level.INFO, " Despues Consulta: {0}  ", new Object[]{consulta1});
        consulta1 = "SELECT c FROM CauSuspservicio c "
                    + " where c.idTrabtec.idTrabtec = ?1";
        setSuspServicio(EJBSuspServ.Consulta_TrabajosTecnico(consulta1, selectedx.getIdTrabtec()));
        // las suspenciones de servicio que aun no estan cerradas se consideran cerradas hasta 
        // a la fecha del calculo se procede a cerrarlas de forma manual
        int anio ;
        Date fechaf = new Date();
        for (CauSuspservicio paso : SuspServicio ){
            anio = 0;
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
            anio = Integer.parseInt(dateFormat.format(paso.getFechahorareanud()));
            if (anio == 9999){
                paso.setFechahorareanud(new Date());
            }
        }
        
        
        // con las fechas inicial y final, las fechas de labores y las 
        // suspenciones de servicio se procede a resalizar el calculo del tiempo 
        // real asignado al servicio
        //======================================================================
        // Se crea clase de calculo de tiempo y se pasa las fechas inicial y final 
        // del servicio, un arreglo de los horarios de labores, y un arreglo de 
        // los horarios de suspención de estandar
        //logger.log(Level.INFO, " Antes  CalculaTiempo ");
         
          CalculaTiempo Inicio = new CalculaTiempo(selectedx.getIdUsuario().getIdUsuario(),
                selectedx.getFechahoraasig(), selectedx.getFechahoraconc(),
                HorasUsu, SuspServicio );
                  
        //logger.log(Level.INFO, " Despues CalculaTiempo  ");
        // se solicita obtener de forma secuencial por dia los horarios de labores
        // asignados en el arreglo de HorasUsu
        //logger.log(Level.INFO, " Antes de  Inicio.ObtenHorariousuario  ");
        Inicio.ObtenHorariousuario(selectedx.getIdUsuario().getIdUsuario());
        //logger.log(Level.INFO, " Despues Inicio.ObtenHorariousuario  ");
        // se solicita calcular el tiempo efectivo con los datos anteriores
        //logger.log(Level.INFO, " Antes Inicio.CalculaTiempoEfectivo()  ");
        minutos = Inicio.CalculaTiempoEfectivo();
        //logger.log(Level.INFO, " Despues Inicio.CalculaTiempoEfectivo()  ");
        
        Grabar_Logs(Inicio, selectedx);
        // Leer el estandar de servicio para el servicio y compararlo con los
        // minutos que se contabilizaron si es menor establecer que si se cumplio 
        // el estandar de servicio si es mayor establecer que no se cumplio con el 
        // estandar de servicio actualizar tabla tabajosTecnicos 
        CauTipotrabajo tipoTra;
        CauEstandarservicio estandserv;
        tipoTra = selectedx.getIdSoltrab().getIdDeptrab().getIdTrabajo();
        estandserv = tipoTra.getIdEstserv();

        int minEst;
        minEst = estandserv.getMinutosnormal();
        // actualizar el estandar de servicio
        if (minutos > minEst) {
            selectedx.setCumpleestandar("NO");
        } else {
            selectedx.setCumpleestandar("SI");
        }
        selectedx.setTiempocalculado((int) minutos);
        if (logs){
            EJBTrabajoTec.edit(selectedx);
        }
        

        // Grabar logs de calculo del servicio
        if (logs){
            Grabar_Logs(Inicio, selectedx);
        }
        
        return "";
    }
    
    private void Grabar_Logs(CalculaTiempo Inicio, CauTrabajostecnicos TrabTecnico ) {        
        for(ImagenDiaHora paso:Inicio.getDiaHora()){
            CauLogoper newItemLogoper = new CauLogoper();
            // se asigna la fecha
            newItemLogoper.setFechaoper(new Date());
            // se asigna el usuario
            newItemLogoper.setIdUsuario(Usua);
            // Se asigna a la transaccion 35 "Reporte de Servicios Concluidos"
            CauTransaccion Transaccion;
            Transaccion = EJBTransaccion.find(35);
            newItemLogoper.setIdTrans(Transaccion);
            //Se asigna Operación realizada
            newItemLogoper.setDescOperacion1(paso.getTipoHorario());
            //Se asigna secuencia de operación realizada
            newItemLogoper.setSecuencia(paso.getSecuencia());
            //Se asigna descripción de operacion 2 realizada
            newItemLogoper.setDescOperacion2(paso.getCadenaDia());            
            //Se asigna descripción de operacion 3 realizada
            newItemLogoper.setDescOperacion3(paso.getHoras());
            // Se asigna el tipo de operación 1 "Calculo de tiempo de atención a un servicio"
            CauTipooper TipoOpera;
            TipoOpera = EJBTipoOper.find(1);
            newItemLogoper.setIdOperacion(TipoOpera);
            //Se asigna mesa de servicio
            CauMesaservicio MesaServicio;
            MesaServicio = EJBMesaServicio.find(1);
            newItemLogoper.setIdMesaserv(MesaServicio);
            // Se aigna el numero de solicitud
            //newItemLogoper.setIdOper(1);
            newItemLogoper.setId01("ID_Solicitud = " + Integer.toString(TrabTecnico.getIdSoltrab().getIdSolicitud().getIdSolicitud()));
            newItemLogoper.setId02("ID_SolTrab   = " + Integer.toString(TrabTecnico.getIdSoltrab().getIdSoltrab()));
            newItemLogoper.setId03("ID_TrabTec   = " + Integer.toString(TrabTecnico.getIdTrabtec()));
            EJBLogOper.create(newItemLogoper);            
        }
    }
}
