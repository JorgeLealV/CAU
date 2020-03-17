/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfp.controler;


import com.sfp.ejb.CauDepjefeFacadeLocal;
import com.sfp.ejb.CauDocadjuntosFacadeLocal;
import com.sfp.ejb.CauEstatusgeneralFacadeLocal;
import com.sfp.ejb.CauProcesoscorreosFacadeLocal;
import com.sfp.ejb.CauSolicitudesFacadeLocal;
import com.sfp.ejb.CauSolicitudtrabajosFacadeLocal;
import com.sfp.ejb.CauUsuariosFacadeLocal;
import com.sfp.lib01.ServiceMailInterface;
import com.sfp.model.CauDepartamentos;
import com.sfp.model.CauDepjefe;
import com.sfp.model.CauDocadjuntos;
import com.sfp.model.CauEstatusgeneral;
import com.sfp.model.CauProcesoscorreos;
import com.sfp.model.CauSolicitudtrabajos;
import com.sfp.model.CauTipotrabajo;
import com.sfp.model.CauUsuarios;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
//import com.jcraft.jsch.*;


/**
 *
 * @author jleal
 */
@Named(value = "mantTrabControler")
//@Dependent
@ViewScoped
//@SessionScoped 
public class MantTrabControler implements Serializable{
   
    @EJB
    private CauSolicitudtrabajosFacadeLocal EJBSolicitudestrabajo;
    
    @EJB
    private CauUsuariosFacadeLocal EJBUsuario;
    
    @EJB
    private CauEstatusgeneralFacadeLocal EJBTCauEstatusgeneral;
    
    @EJB
    private CauSolicitudesFacadeLocal EJBSolicitudes;
    
    @EJB
    private CauSolicitudtrabajosFacadeLocal EJBSolicTrab;
    
    @EJB 
    private CauDepjefeFacadeLocal EJBDepjefe;
    
    @EJB
    private ServiceMailInterface mail1;
    
    @EJB 
    private CauDocadjuntosFacadeLocal EJBDocAdj;
    
    @EJB 
    private CauProcesoscorreosFacadeLocal EJBCauProcesoscorreos;
    
    @EJB 
    private CauDocadjuntosFacadeLocal EJBCauDocadjuntos;
    
  //  @Inject private SolicControler SolicControler1;
  //  @Inject private DeptoControler DeptoControler1;
   // @Inject private TipoTrabajoControler TipTrabajoControler1;
    @Inject private Login01Controler Login01Controler1;
     
    private CauSolicitudtrabajos selected;
    private Collection<CauSolicitudtrabajos> items;
    private CauUsuarios Usua;
    
    private String EntradaSolic;   
    private String SalidaSolic;
    private String TituloMantTrabajo;  
    private String OrigenLlamada;
    private String NoTrabajos="1";
    private String DesTrabajo = "";
    private String Requisito = "";
    private Collection<CauSolicitudtrabajos> trabajos;
    private Collection<CauDepjefe> depjefe;
    private CauDocadjuntos selecteddocadj;  
    private Collection<CauDocadjuntos> itemsDocAdjuntos;
    private UploadedFile file;
    private Collection<CauProcesoscorreos> Procesos;

    public CauDocadjuntos getSelecteddocadj() {
        return selecteddocadj;
    }

    public void setSelecteddocadj(CauDocadjuntos selecteddocadj) {
        this.selecteddocadj = selecteddocadj;
    }
    
    public Collection<CauDocadjuntos> getItemsDocAdjuntos() {
        if (selected != null) {
            if (selecteddocadj == null) {
                leeAdjuntarArchivos();
                return itemsDocAdjuntos;
            }
        }
        return itemsDocAdjuntos;
    }

    public void setItemsDocAdjuntos(Collection<CauDocadjuntos> itemsDocAdjuntos) {
        this.itemsDocAdjuntos = itemsDocAdjuntos;
    }
 
    
    
    public String getDesTrabajo() {
        return DesTrabajo;
    }

    public void setDesTrabajo(String DesTrabajo) {
        this.DesTrabajo = DesTrabajo;
    }

    public String getRequisito() {
        return Requisito;
    }

    public void setRequisito(String Requisito) {
        this.Requisito = Requisito;
    }
            
    
    
    public String getNoTrabajos() {
        return NoTrabajos;
    }

    public void setNoTrabajos(String NoTrabajos) {
        this.NoTrabajos = NoTrabajos;
    }
    
    
    public String getTituloMantTrabajo() {
        String paso1 = EntradaSolic;
        //EntradaSolic = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Solicitud");
        TituloMantTrabajo = "Trabajos Asignados a la Solicitud ";
        return TituloMantTrabajo;
    }

    public void setTituloMantTrabajo(String TituloMantTrabajo) {
        this.TituloMantTrabajo = TituloMantTrabajo;
    }
   

    public String getEntradaSolic() {
        return EntradaSolic;
    }

    public void setEntradaSolic(String EntradaSolic) {
        this.EntradaSolic = EntradaSolic;
    }

    public String getSalidaSolic() {
        return SalidaSolic;
    }

    public void setSalidaSolic(String SalidaSolic) {
        this.SalidaSolic = SalidaSolic;
    }
    
    @PostConstruct
    private void init(){
       selecteddocadj = null;
       Usua = (CauUsuarios) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario"); 
       //FacesContext facesContext = FacesContext.getCurrentInstance();
       //Map params = facesContext.getExternalContext().getRequestParameterMap();
       
       OrigenLlamada = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Origen");
        if (OrigenLlamada.equals("CAU_Deptos")) {
            EntradaSolic = Login01Controler1.getSolicitudLogin_Parametro();
        } else if (OrigenLlamada.equals("CAU_Tipotrabajo")) {
            EntradaSolic = Login01Controler1.getSolicitudLogin_Parametro();
        } else if (OrigenLlamada.equals("CAU_SolicConstruc")) {
            EntradaSolic = Login01Controler1.getSolicitudLogin_Parametro();
        } else if (OrigenLlamada.equals("CAU_MantTrab")) {
            EntradaSolic = Login01Controler1.getSolicitudLogin_Parametro();
        }
        
        EntradaSolic = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Solicitud");
        //TituloMantTrabajo = "Trabajos Asignados a la Solicitud 2: " + EntradaSolic;
   } 

    public void GuardarTecnico(){
        
    }
    
    public CauSolicitudtrabajos getSelected() {
        return selected;
    }

    public void setSelected(CauSolicitudtrabajos selected) {
        this.selected = selected;
        //NoTrabajos = selected.getNodeservicios().toString();        
    }

    public void  prepareCreate(){
        String[] Requisitos;
        NoTrabajos = selected.getNodeservicios().toString();
        if (selected.getDescripcion() != null) {
            DesTrabajo = selected.getDescripcion().toString();
        }

        if (selected.getIdDeptrab().getIdTrabajo().getRequisitos().length() > 0) {
            Requisitos = selected.getIdDeptrab().getIdTrabajo().getRequisitos().split("\\|");
            Requisito = Requisitos[0];
        } else {
            Requisito = "";
        }        
    }
    
    public Collection<CauSolicitudtrabajos> getItems() {
        OrigenLlamada = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Origen");
        if (OrigenLlamada.equals("CAU_Deptos")) {
            EntradaSolic = Login01Controler1.getSolicitudLogin_Parametro();
        } else if (OrigenLlamada.equals("CAU_Tipotrabajo")) {
            EntradaSolic = Login01Controler1.getSolicitudLogin_Parametro();
        } else if (OrigenLlamada.equals("CAU_SolicConstruc")) {
            EntradaSolic = Login01Controler1.getSolicitudLogin_Parametro();
        }else if (OrigenLlamada.equals("CAU_MantTrab")) {
            EntradaSolic = Login01Controler1.getSolicitudLogin_Parametro();
        }
        
        //EntradaSolic = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Solicitud");
        TituloMantTrabajo = "Trabajos Asignados a la Solicitud ";
        if (items == null){
            ObtenSolicitudestrabajo();
        }
        return items;
    }

    public void setItems(Collection<CauSolicitudtrabajos> items) {
        this.items = items;
    }
          
    public  MantTrabControler() {  
//        OrigenLlamada = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Origen");
//        if (DeptoControler1 != null && OrigenLlamada.equals("CAU_Deptos")) {
//            EntradaSolic = DeptoControler1.getSalidaSolic();
//        } else if (TipTrabajoControler1 != null && OrigenLlamada.equals("CAU_Tipotrabajo")) {
//            EntradaSolic = TipTrabajoControler1.getSalidaSolic();
//        } else if (SolicControler1 != null && OrigenLlamada.equals("CAU_SolicConstruc")) {
//            EntradaSolic = SolicControler1.getSalidaSolic();
//        } else if (OrigenLlamada.equals("CAU_MantTrab")) {
//            EntradaSolic = SalidaSolic;
//        }

        TituloMantTrabajo = "Trabajos Asignados a la Solicitud ";
    }

    public void  ObtenSolicitudestrabajo(){     
        String consulta1 = " SELECT c FROM CauSolicitudtrabajos c Join c.idSolicitud e "                         
                          +" WHERE e.idSolicitud = ?1"; 
        
       int idSolicitud = Integer.parseInt(EntradaSolic);
       setItems(EJBSolicitudestrabajo.Consulta_Solicitudestrabajo(consulta1,idSolicitud));
    }
    
    public String nuevoTrabajo(){
       SalidaSolic = EntradaSolic;
       FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("Origen", "CAU_MantTrab");  
       return "/PrimeFaces/Deptos/index?faces-redirect=true";
    }  
    public String borrarTrab(){
      SalidaSolic = EntradaSolic;   
      EJBSolicitudestrabajo.remove(selected);
      selected = null;
      FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("Origen", "CAU_MantTrab");    
      return "/PrimeFaces/MantTrab/index?faces-redirect=true";  
    }
    
    public String salir(){
       FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("Origen", "CAU_MantTrab");   
       selected = null;
       return "/PrimeFaces/cauSolicitudes/index?faces-redirect=true";
    }  
    
    public boolean activarenviar() {
        boolean activar = false;
        if (!items.isEmpty()) {
            activar = true;
        }
        return activar;
    }
    
    
    public String enviar() {
        
        if (!items.isEmpty()) {
            if (selected == null) {
                selected = items.iterator().next();
            }
            EnviarSolicitud(selected.getIdSolicitud().getIdSolicitud());
            selected = null;
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("Origen", "CAU_SolicConstruc");
            // se debe enviar a la consulta de solicitudes enviadas ojo ojo
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Solicitud Enviada: ", ""));
            // context.addMessage(null, new FacesMessage("Seleccione la opción del menu \"Solicitudes enviadas\" para ver el estatus de su solicitud", ""));            
            return "/PrimeFaces/cauSolicitudes/index?faces-redirect=true";
        }
        else {
            FacesContext context = FacesContext.getCurrentInstance();         
            context.addMessage(null, new FacesMessage("Solicitud: No tiene trabajos !no se  realiza el envio!", ""));
           // return "/PrimeFaces/cauSolicitudes/index?faces-redirect=true";   
           selected = null;
        }         
            
        return "";
    }
    
    public String Modificar(){
     SalidaSolic = EntradaSolic;
     selected.setNodeservicios(Integer.parseInt(NoTrabajos));
     selected.setDescripcion(DesTrabajo);
     EJBSolicitudestrabajo.edit(selected);
     FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("Origen", "CAU_MantTrab");    
     return "/PrimeFaces/MantTrab/index?faces-redirect=true";   
    }
    
    private void EnviarSolicitud(int SolicitudB) {
        // Se envia  la solicitud SolicitudB cambiando el estatus a id_estatus = 6
        // SalidaSolic = EntradaSolic;
        enviarCorreo();
        CauEstatusgeneral estatus;
        estatus = EJBTCauEstatusgeneral.find(6);
        selected.getIdSolicitud().setIdEstatus(estatus);
        selected.getIdSolicitud().setFechaenvio(new Date());        
        EJBSolicitudes.edit(selected.getIdSolicitud());
               
        String consulta2 = " SELECT c FROM CauSolicitudtrabajos c Join c.idSolicitud e "
                         + " join c.idDeptrab f join f.idDepartamentos k join f.idDepartamentos i" 
                         + " WHERE e.idSolicitud = ?1"
                         + " order by k.idDepartamentos "; 
         
        trabajos = EJBSolicitudestrabajo.Consulta_Solicitudestrabajo(consulta2, selected.getIdSolicitud().getIdSolicitud());
        for (CauSolicitudtrabajos trab : trabajos) {
            estatus = EJBTCauEstatusgeneral.find(4);
            trab.setIdEstatus(estatus);
            trab.setFechaasigdepto(new Date());
            EJBSolicitudestrabajo.edit(trab);
        }        
        Login01Controler1.setSolicitudLogin_Parametro(selected.getIdSolicitud().getIdSolicitud().toString());
        selected = null;
        setItems(null);
        getItems();
    }
 
    public void leeAdjuntarArchivos() {
        String consulta1 = "SELECT c FROM CauDocadjuntos c"
                + " WHERE c.idSoltrab.idSoltrab = ?1 "; 
       int paso1 = selected.getIdSoltrab();
       setItemsDocAdjuntos(EJBDocAdj.Consulta_CauDocAdjuntos(consulta1, selected.getIdSoltrab()));
       selecteddocadj = null;
    }
    
    public void  guardarTecnico(){
        
    }
    
    public UploadedFile getFile() {
        return file;
    }
 
    public void setFile(UploadedFile file) {
        this.file = file;
    }
     
    public void upload() {
        if(file != null) {
           // FacesMessage message = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
           // FacesContext.getCurrentInstance().addMessage(null, message);
           String paso = file.getFileName();
         
        }
    }
     
    public void handleFileUpload(FileUploadEvent event) {
        FacesMessage msg = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void fileUploadListener(FileUploadEvent e) {
        // Get uploaded file from the FileUploadEvent
        UploadedFile uploadedFile = e.getFile();

        this.file = e.getFile();
        String Nombrearchivo = uploadedFile.getFileName();
        String nombrearchivoenviado;
        int numero = (int)(Math.random()*1000+1);
        Calendar fechacalendar= Calendar.getInstance();
        nombrearchivoenviado = Nombrearchivo + "_" + Integer.toString(fechacalendar.get(Calendar.DATE)).trim()
                                             + "_" + Integer.toString(fechacalendar.get(Calendar.MONTH)+ 1).trim()
                                             + "_" + Integer.toString(fechacalendar.get(Calendar.DAY_OF_MONTH)).trim()
                                             + "_" + Integer.toString(numero).trim();        
        long tamanio = uploadedFile.getSize();
        String contentType = uploadedFile.getContentType();
        byte[] contents = uploadedFile.getContents(); // Or getInputStream()
        // ... Alta del registro en la tabla CAU_DocAdjuntos
        CauDocadjuntos docadjuntos;
        docadjuntos = new CauDocadjuntos();
        docadjuntos.setIdSoltrab(selected);
        docadjuntos.setNombrearchivo(Nombrearchivo + "|" + contentType);
        docadjuntos.setNombrearchenviado(nombrearchivoenviado);
        EJBCauDocadjuntos.create(docadjuntos);
        selecteddocadj = null;
        // en este punto se debe de guardar el archivo en la tabla CAU_DocAdjuntos y se envia el archivo por sftp 
        // al repositorio definido para este sistema hay que ver como se conecta uno al binding name
        System.out.println("Uploaded File Name Is :: " + file.getFileName() + " :: Uploaded File Size :: " + file.getSize());
    }
   
    public void Borrar(){        
        // borrado de archivo adjunto seleccionado
        if (selecteddocadj != null){
            
        }
    }
        
//    private void enviarCorreo(){
//        // consulta los trabajos que se solicitan para obtener el departamento a que pertenecen  
//        // ordenado por departamento
//         String consulta1 = " SELECT c FROM CauSolicitudtrabajos c Join c.idSolicitud e "
//                         + " join c.idDeptrab f join f.idDepartamentos k join f.idDepartamentos i" 
//                         + " WHERE e.idSolicitud = ?1"
//                         + " order by k.idDepartamentos ";
//        trabajos = EJBSolicTrab.Consulta_Solicitudestrabajo(consulta1, selected.getIdSolicitud().getIdSolicitud()); 
//       // Una vez que se tiene los trabajos se csicla la colección para obtener una lista de los trabajos 
//        // del mismo departamento para enviar solo un correo por departamento a los jefes de deprtamento
//        CauDepartamentos deptoActual = null;
//        String cadenaTrabajos = "";
//        Date fechaasig=null;
//        
//        for(CauSolicitudtrabajos trabajo: trabajos){
//            // cuando se inicia se establece el departamento actual y se inicializa la cadena de trabajos y la
//            // fecha de asignación
//            if (deptoActual == null){
//                deptoActual = trabajo.getIdDeptrab().getIdDepartamentos();
//                cadenaTrabajos = trabajo.getIdDeptrab().getIdTrabajo().getDescripcion()                                  
//                                 + " ID_Depto = " +  Integer.toString(trabajo.getIdDeptrab().getIdDepartamentos().getIdDepartamentos())                                  
//                                 + " ID_Trabajo = " + Integer.toString(trabajo.getIdDeptrab().getIdTrabajo().getIdTrabajo()); 
//                fechaasig = (new Date());
//            } else if (!deptoActual.getIdDepartamentos().equals(trabajo.getIdDeptrab().getIdDepartamentos().getIdDepartamentos())){
//                // cuando el departamento actual no es igual al departamento en curso se realiza el corte 
//                // y se procede a enviar el correo al todos los jefes de departamento que tienen asignado los trabajos solicitados
//                MandaCorreo(selected.getIdSolicitud().getIdSolicitud(), cadenaTrabajos, deptoActual,fechaasig);
//                deptoActual = trabajo.getIdDeptrab().getIdDepartamentos();
//                 cadenaTrabajos = trabajo.getIdDeptrab().getIdTrabajo().getDescripcion() 
//                                 + " (ID_Depto = " +  trabajo.getIdDeptrab().getIdDepartamentos() 
//                                 + ") (ID_Trabajo = " + trabajo.getIdDeptrab().getIdTrabajo() + ")"; 
//                fechaasig = (new Date());
//            }
//            else {
//                // si el departamento en curso es igual al departamento actual entonces se procede a concatenar eltrabajo
//                // con los trabajosasociados al mismo departamento
//               cadenaTrabajos = cadenaTrabajos + "|" + trabajo.getIdDeptrab().getIdTrabajo().getDescripcion()
//                                 + " (ID_Depto = " +  Integer.toString(trabajo.getIdDeptrab().getIdDepartamentos().getIdDepartamentos()) 
//                                 + ") (ID_Trabajo = " + Integer.toString(trabajo.getIdDeptrab().getIdTrabajo().getIdTrabajo()) + ")";
//            }    
//        } 
//        // al final se envial el ultimo departamento de la secuencia de trabajos
//        MandaCorreo(selected.getIdSolicitud().getIdSolicitud(), cadenaTrabajos, deptoActual, fechaasig);        
//    }
    
    private void enviarCorreo(){
        // consulta los trabajos que se solicitan para obtener el departamento a que pertenecen  
        // ordenado por departamento
        String consulta1 = " SELECT c FROM CauSolicitudtrabajos c Join c.idSolicitud e "
                         + " join c.idDeptrab f join f.idDepartamentos k join f.idDepartamentos i" 
                         + " WHERE e.idSolicitud = ?1"
                         + " order by k.idDepartamentos ";
        trabajos = EJBSolicTrab.Consulta_Solicitudestrabajo(consulta1, selected.getIdSolicitud().getIdSolicitud()); 
        
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
                MandaCorreo(selected.getIdSolicitud().getIdSolicitud(), cadenaTrabajos, deptoActual,fechaasig);
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
        MandaCorreo(selected.getIdSolicitud().getIdSolicitud(), cadenaTrabajos, deptoActual, fechaasig);
    }
    
//    private void MandaCorreo(Integer  solicitud, String trabajos,CauDepartamentos depto, Date fecha ) {
//        String[] parts = trabajos.split("\\|");
//        String[] BodySubject = null;
//        String[] Partecorreo = null;
//        String CadenaCorreo;
//        String consulta1;
//        String correo;
//        CauProcesoscorreos recorre;
//
//        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//        // la etapa del proceso en este modulo es 1 que es el envio de solicitud a la mesa de servicio
//        // para su atención se envian correos a todos los jefes de departamento que atienden ese servicio
//        CadenaCorreo = "select c from CauProcesoscorreos c where c.idMesaserv.idMesaserv  = "
//                + Login01Controler1.getMesaDeServicio_parametro()
//                + " and c.idEtapaproceso.idEtapaproceso = ?1 ";
//
//        Procesos = EJBCauProcesoscorreos.Consulta_Proceso(CadenaCorreo, 1);
//        if (Procesos != null) {
//
//            Iterator<CauProcesoscorreos> iProcesos = Procesos.iterator();
//            // separación del body del subject
//
//            iProcesos.hasNext();
//            if (Procesos.size() > 0) {
//                recorre = iProcesos.next();
//                BodySubject = recorre.getIdTipodat().getDescripcion().split("\\#");
//            }
//            if (BodySubject.length == 2) {
//                BodySubject[0] = BodySubject[0].replaceAll("REPP1", solicitud.toString());
//                BodySubject[0] = BodySubject[0].replaceAll("REPP2", depto.getDescripcion());
//                BodySubject[0] = BodySubject[0].replaceAll("REPP3", formateador.format(fecha));
//                BodySubject[1] = BodySubject[1].replaceAll("REPP1", solicitud.toString());
//                Partecorreo = BodySubject[0].split("\\|");
//            }
//            CadenaCorreo = "";
//            CadenaCorreo = CadenaCorreo + Partecorreo[0] + (char) 13 + (char) 13;
//            CadenaCorreo = CadenaCorreo + Partecorreo[1] + (char) 13 + (char) 13;
//            CadenaCorreo = CadenaCorreo + Partecorreo[2] + (char) 13 + (char) 13;
//            CadenaCorreo = CadenaCorreo + Partecorreo[3] + (char) 13 + (char) 13;
//            CadenaCorreo = CadenaCorreo + Partecorreo[4] + (char) 13;
//            for (String cad : parts) {
//                CadenaCorreo = CadenaCorreo + "==>" + cad + (char) 13;
//            }
//
////      CadenaCorreo = "Se ingreso la solicitud " + solicitud.toString() + (char)13 + (char)13 + "Relacionado con el Departamento : " + depto.getDescripcion() + (char)13;      
////      CadenaCorreo = CadenaCorreo +  (char)13 + "Que tiene asignado a su cargo" + (char)13  +"Con los siguientes trabajos solicitados: " + (char)13; 
////      CadenaCorreo = CadenaCorreo + "Con fecha de: " + formateador.format(fecha) +(char)13 + (char)13;
////      for(String cad : parts){
////        CadenaCorreo = CadenaCorreo + "==>" +  cad + (char)13  ;   
////      }
//            // envio de correo final en CadenaCorreo
//            // Obtener los correos de todos los jefes de departamento que atienden el depto depto.getIdDepartamentos()
//            consulta1 = "Select c from CauDepjefe c where c.cauDepartamentos.idDepartamentos = ?1 ";
//            // trabajos = EJBSolicTrab.Consulta_Solicitudestrabajo(consulta1, selected.getIdSolicitud()); 
//            depjefe = EJBDepjefe.Consulta_DepJefe(consulta1, depto.getIdDepartamentos());
//            String subject, body, email, body2, bodyresp;
//            subject = "Atención de reporte No." + solicitud.toString();
//            subject = BodySubject[1];
//
//            body = CadenaCorreo;
//            bodyresp = body;
//            body2 = body + (char) 13 + "REPLICA";
//            email = "cau_servicios@funcionpublica.gob.mx";
//            boolean enviado = false;
//            for (CauDepjefe deptos1 : depjefe) {
//                correo = deptos1.getCauUsuarios().getCorreo();
//                body = bodyresp + (char) 13 + "Jefe de departamento = " + deptos1.getCauUsuarios().getIdNocredencial().getNombre();
//                // LLama a el correo con los parametros correo y CadenaCorreo
//                // cau_servicios@funcionpublica.gob.mx                 
//                // enviaContentText(String email, List<String> copias, List<String> copiasocultas, String subject, String body, DTODocumento documento);
//                try {
//                    mail1.enviaContentText(correo.trim(), null, null, subject, body, null);
//                    enviado = true;
//                } catch (Exception e) {
//                    System.out.println(e.getMessage());
//                }
//            }
//            if (enviado) {
//                try {
//                    mail1.enviaContentText("jleal@funcionpublica.gob.mx", null, null, subject, body2, null);
//                } catch (Exception e) {
//                    System.out.println(e.getMessage());
//                }
//            }
//        }
//    }
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
            subject = "Atención de reporte No." + solicitud.toString();
            subject = BodySubject[1];

            body = CadenaCorreo;
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
