/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfp.controler;


import com.sfp.ejb.CauDepjefeFacadeLocal;
import com.sfp.ejb.CauEstatusgeneralFacadeLocal;
import com.sfp.ejb.CauProcesoscorreosFacadeLocal;
import com.sfp.ejb.CauSolicitudesFacadeLocal;
import com.sfp.ejb.CauSolicitudtrabajosFacadeLocal;
import com.sfp.lib01.ServiceMailInterface;
import com.sfp.model.CauDepartamentos;
import com.sfp.model.CauDepjefe;
import com.sfp.model.CauEstatusgeneral;
import com.sfp.model.CauProcesoscorreos;
import com.sfp.model.CauSolicitudes;
import com.sfp.model.CauSolicitudtrabajos;
import com.sfp.model.CauUsuarios;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
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
@Named(value = "solicConCancControler")
//@Dependent
@ViewScoped
//@SessionScoped 
public class SolicConCancControler implements Serializable{
   
    @EJB
    private CauSolicitudesFacadeLocal EJBSolicitudes;
    
    @EJB
    private CauSolicitudtrabajosFacadeLocal EJBSolicitudestrabajo;
    
    @EJB
    private CauEstatusgeneralFacadeLocal EJBTCauEstatusgeneral;
    
    @EJB
    private CauSolicitudtrabajosFacadeLocal EJBSolicTrab;
    
    @EJB 
    private CauProcesoscorreosFacadeLocal EJBCauProcesoscorreos;
    
    @EJB 
    private CauDepjefeFacadeLocal EJBDepjefe;
    
    @EJB
    private ServiceMailInterface mail1; 
    
    @Inject private Login01Controler Login01Controler1;

    private CauSolicitudes selected;
    private Collection<CauSolicitudes> items;
    private Collection<CauSolicitudtrabajos> itemsdet;
    private Collection<CauSolicitudtrabajos> trabajos;
    private Collection<CauProcesoscorreos> Procesos;
    private Collection<CauDepjefe> depjefe;

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
          
    public  SolicConCancControler() {    
    }
    
    public void  ObtenSolicitudes(){ 
       String Mesaservicio = Login01Controler1.getMesaDeServicio_parametro(); 
       String consulta1 = "SELECT c FROM CauSolicitudes c Join c.idEstatus e" 
                             + " WHERE e.idEstatus in (8, 16, 10) and c.usuariosolicita = ?1 and " 
                             + " c.idMesaserv.idMesaserv = " + Mesaservicio
                             + " Order by c.idSolicitud DESC";  
                          //   +  " c.idSolicitud in "
                          //   + "(Select d.idSolicitud.idSolicitud "
                          //   + " from CauSolicitudtrabajos d Join CauDeptrabajo e "
                          //   + " where e.idDepartamentos.idMesaserv.idMesaserv = " 
                          //   + Mesaservicio + " and d.idSolicitud.idSolicitud  = c.idSolicitud )"
                            
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
    
    public String Reactivar (){
        EnviarSolicitud(selected.getIdSolicitud()); 
        selected = null;
        return "/PrimeFaces/cauSolicitudes/index?faces-redirect=true"; 
    }
    
    private void EnviarSolicitud(int SolicitudB) {
        // Se envia  la solicitud SolicitudB cambiando el estatus a id_estatus = 6
        // Enviada para su atnción        
        enviarCorreo();
        CauEstatusgeneral estatus;
        estatus = EJBTCauEstatusgeneral.find(6);
        selected.setIdEstatus(estatus);
        selected.setFechaenvio(new Date());        
        EJBSolicitudes.edit(selected);
        // a los trabajos se les cambia el estatus a 4 
        // Sin tecnico asignado
        String consulta2 = " SELECT c FROM CauSolicitudtrabajos c Join c.idSolicitud e "
                         + " join c.idDeptrab f join f.idDepartamentos k join f.idDepartamentos i" 
                          +" WHERE e.idSolicitud = ?1"; 
        
        trabajos = EJBSolicitudestrabajo.Consulta_Solicitudestrabajo(consulta2, selected.getIdSolicitud());
        for (CauSolicitudtrabajos trab : trabajos) {
            estatus = EJBTCauEstatusgeneral.find(4);
            trab.setIdEstatus(estatus);
            trab.setFechaasigdepto(new Date());
            EJBSolicitudestrabajo.edit(trab);
        }        
        Login01Controler1.setSolicitudLogin_Parametro(selected.getIdSolicitud().toString());
        selected = null;
        setItems(null);
        getItems();
    }
    
     private void enviarCorreo(){
        // consulta los trabajos que se solicitan para obtener el departamento a que pertenecen  
        // ordenado por departamento
        String consulta1 = " SELECT c FROM CauSolicitudtrabajos c Join c.idSolicitud e "
                         + " join c.idDeptrab f join f.idDepartamentos k join f.idDepartamentos i" 
                         + " WHERE e.idSolicitud = ?1"
                         + " order by k.idDepartamentos ";
        trabajos = EJBSolicTrab.Consulta_Solicitudestrabajo(consulta1, selected.getIdSolicitud()); 
        
        // Una vez que se tiene los trabajos se cicla la colección para obtener una lista de los trabajos 
        // del mismo departamento para enviar solo un correo por departamento a los jefes de deprtamento
        CauDepartamentos deptoActual = null;
        String cadenaTrabajos = "";
        Date fechaasig=null;
        
        for(CauSolicitudtrabajos trabajo: trabajos){
            // cuando se inicia se establece el departamento actual y se inicializa la cadena de trabajos y la
            // fecha de asignación
            if (deptoActual == null){
                deptoActual = trabajo.getIdDeptrab().getIdDepartamentos();
                cadenaTrabajos = "[" + Integer.toString(trabajo.getIdDeptrab().getIdTrabajo().getIdTrabajo()) + " " +
                                 trabajo.getIdDeptrab().getIdTrabajo().getDescripcion() + "]";
                fechaasig = (new Date());
            } else if (!deptoActual.getIdDepartamentos().equals(trabajo.getIdDeptrab().getIdDepartamentos().getIdDepartamentos())){
                // cuando el departamento actual no es igual al departamento en curso se realiza el corte 
                // y se procede a enviar el correo al todos los jefes de departamento que tienen asignado los trabajos solicitados
                MandaCorreo(selected.getIdSolicitud(), cadenaTrabajos, deptoActual,fechaasig);
                deptoActual = trabajo.getIdDeptrab().getIdDepartamentos();
                cadenaTrabajos = "[" + Integer.toString(trabajo.getIdDeptrab().getIdTrabajo().getIdTrabajo()) + " " +
                                 trabajo.getIdDeptrab().getIdTrabajo().getDescripcion() + "]";
                fechaasig = (new Date());
            }
            else {
                // si el departamento en curso es igual al departamento actual entonces se procede a concatenar eltrabajo
                // con los trabajosasociados al mismo departamento
               cadenaTrabajos = cadenaTrabajos + "| [" + Integer.toString(trabajo.getIdDeptrab().getIdTrabajo().getIdTrabajo()) + " " +
                                trabajo.getIdDeptrab().getIdTrabajo().getDescripcion() + "]";
                                 
                                  
            }    
        } 
        // al final se envial el ultimo departamento de la secuencia de trabajos
        MandaCorreo(selected.getIdSolicitud(), cadenaTrabajos, deptoActual, fechaasig);
    }
    
    private void MandaCorreo(Integer  solicitud, String trabajos,CauDepartamentos depto, Date fecha ) {       
        String[] parts = trabajos.split("\\|");
        String[] BodySubject = null;
        String[] Partecorreo = null;
        String CadenaCorreo;
        String consulta1;
        String correo;
        CauProcesoscorreos recorre;

        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        // la etapa del proceso en este modulo es 1 que es el envio de solicitud a la mesa de servicio
        // para su atención se envian correos a todos los jefes de departamento que atienden ese servicio
        CadenaCorreo = "select c from CauProcesoscorreos c where c.idMesaserv.idMesaserv  = "
                + Login01Controler1.getMesaDeServicio_parametro()
                + " and c.idEtapaproceso.idEtapaproceso = ?1 ";

        Procesos = EJBCauProcesoscorreos.Consulta_Proceso(CadenaCorreo, 1);
        if (Procesos != null) {

            Iterator<CauProcesoscorreos> iProcesos = Procesos.iterator();
            // separación del body del subject

            iProcesos.hasNext();
            if (Procesos.size() > 0) {
                recorre = iProcesos.next();
                BodySubject = recorre.getIdTipodat().getDescripcion().split("\\#");
            }
            if (BodySubject.length == 2) {
                BodySubject[0] = BodySubject[0].replaceAll("REPP1", solicitud.toString());
                BodySubject[0] = BodySubject[0].replaceAll("REPP2", formateador.format(fecha));
                BodySubject[0] = BodySubject[0].replaceAll("REPP3", depto.getIdDepartamentos().toString().trim());
                BodySubject[0] = BodySubject[0].replaceAll("REPP4", depto.getDescripcion());                
                
                BodySubject[1] = BodySubject[1].replaceAll("REPP1", solicitud.toString());
                BodySubject[1] = BodySubject[1].replaceAll("REPP4", depto.getDescripcion());
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
            
            String ParteCorreoresp = Partecorreo[7];
            
            for (String cad : parts) {              
                Partecorreo[7] = ParteCorreoresp;
                Partecorreo[7] = Partecorreo[7].replaceAll("REPP7", cad) + (char) 13 + (char) 13;
                CadenaCorreo = CadenaCorreo + Partecorreo[7];                
            }

//      CadenaCorreo = "Se ingreso la solicitud " + solicitud.toString() + (char)13 + (char)13 + "Relacionado con el Departamento : " + depto.getDescripcion() + (char)13;      
//      CadenaCorreo = CadenaCorreo +  (char)13 + "Que tiene asignado a su cargo" + (char)13  +"Con los siguientes trabajos solicitados: " + (char)13; 
//      CadenaCorreo = CadenaCorreo + "Con fecha de: " + formateador.format(fecha) +(char)13 + (char)13;
//      for(String cad : parts){
//        CadenaCorreo = CadenaCorreo + "==>" +  cad + (char)13  ;   
//      }
            // envio de correo final en CadenaCorreo
            // Obtener los correos de todos los jefes de departamento que atienden el depto depto.getIdDepartamentos()
            consulta1 = "Select c from CauDepjefe c where c.cauDepartamentos.idDepartamentos = ?1 ";
            // trabajos = EJBSolicTrab.Consulta_Solicitudestrabajo(consulta1, selected.getIdSolicitud()); 
            depjefe = EJBDepjefe.Consulta_DepJefe(consulta1, depto.getIdDepartamentos());
            String subject, body, email, body2, bodyresp;
            subject = "Reactivación de la Solicitud No." + solicitud.toString();
            subject = BodySubject[1];

            body = "REENVIO DE SOLICITUD CERRADA POR TECNICO "  + (char) 13 + (char) 13 
                   + "PERO NO SATISFACTORIA PARA EL USUARIO " + (char) 13 + (char) 13 +  CadenaCorreo;
            bodyresp = body;
            body2 = body + (char) 13 + "REPLICA";
            email = "cau_servicios@funcionpublica.gob.mx";
            boolean enviado = false;
            for (CauDepjefe deptos1 : depjefe) {
                correo = deptos1.getCauUsuarios().getCorreo();
                body = bodyresp;
                body = body.replaceAll("REPP5",deptos1.getCauUsuarios().getIdNocredencial().getNombre() );
                body = body.replaceAll("REPP6",deptos1.getCauUsuarios().getCorreo() );                    
                //body = bodyresp + (char) 13 + "Jefe de departamento = " + deptos1.getCauUsuarios().getIdNocredencial().getNombre() 
                //                + (char) 13 +  correo;
                // LLama a el correo con los parametros correo y CadenaCorreo
                // cau_servicios@funcionpublica.gob.mx                 
                // enviaContentText(String email, List<String> copias, List<String> copiasocultas, String subject, String body, DTODocumento documento);
                try {
                    mail1.enviaContentText(correo.trim(), null, null, subject, body, null);
                    enviado = true;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            if (enviado) {
                try {
                    body = bodyresp;
                    body = body.replaceAll("REPP5","Jorge Marco Vinicio" );
                    body = body.replaceAll("REPP6","jleal@funcionpublica.gob.mx" );  
                    body2 = body + (char) 13 + "REPLICA";
                    mail1.enviaContentText("jleal@funcionpublica.gob.mx", null, null, subject, body2, null);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
