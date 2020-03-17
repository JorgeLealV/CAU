/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfp.controler;


import com.sfp.cau.SolicTrabComp;
import com.sfp.cau.SolicitudComp;
import com.sfp.ejb.CauEmpFacadeLocal;
import com.sfp.ejb.CauEstatusgeneralFacadeLocal;
import com.sfp.ejb.CauSolicitudesFacadeLocal;
import com.sfp.ejb.CauSolicitudtrabajosFacadeLocal;
import com.sfp.ejb.CauSuspservicioFacadeLocal;
import com.sfp.ejb.CauTectipotrabFacadeLocal;
import com.sfp.ejb.CauTrabajostecnicosFacadeLocal;
import com.sfp.ejb.CauUsuariosFacadeLocal;
import com.sfp.ejb.CauUsuariotipoFacadeLocal;
import com.sfp.ejb.SolicTrabCompFacadeLocal;
import com.sfp.model.CauEmp;
import com.sfp.model.CauEstatusgeneral;
import com.sfp.model.CauSolicitudes;
import com.sfp.model.CauSolicitudtrabajos;
import com.sfp.model.CauSuspservicio;
import com.sfp.model.CauTectipotrab;
import com.sfp.model.CauTrabajostecnicos;
import com.sfp.model.CauUsuarios;
import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
@Named(value = "serviciosConcCanc")
//@Dependent
@ViewScoped
//@SessionScoped 
public class ServiciosConcCanc implements Serializable{
   
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
    
    // Se utiliza para obtener las caracteristicas del usuario almacenadas 
    // en memoria
    @EJB
    private CauUsuariosFacadeLocal EJBUsuario;
      
    // Tabla en la que se almacnara la relación entre los trabajos y los 
    //tecnicos que atienden la solicitud
    @EJB 
    private CauTrabajostecnicosFacadeLocal EJBTrabajostecnicos;
    
    @EJB 
    private CauSuspservicioFacadeLocal EJBSuspservicio;
   
    @EJB 
    private CauEmpFacadeLocal EJBEmp;
    
    @EJB 
    private SolicTrabCompFacadeLocal EJBServConCan;
    
    @EJB
    private CauUsuariotipoFacadeLocal EJBUsuariotipo;
    
    @Inject 
    private Login01Controler Login01Controler1;
    
    private CauTrabajostecnicos selected1;   
    private Collection<CauTrabajostecnicos> items1;
    private Collection<CauSolicitudtrabajos> itemsdet; 
    private Collection<SolicTrabComp> itemsdet2; 

    public Collection<SolicTrabComp> getItemsdet2() {
        return itemsdet2;
    }

    public void setItemsdet2(Collection<SolicTrabComp> itemsdet2) {
        this.itemsdet2 = itemsdet2;
    }
    private CauSolicitudtrabajos solictrab;
    SolicitudComp SolicCompleta;

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
     
    public Collection<CauSolicitudtrabajos> getItemsdet() {
        return itemsdet;
    }

    public void setItemsdet(Collection<CauSolicitudtrabajos> itemsdet) {
        this.itemsdet = itemsdet;
    } 
    
    public  ServiciosConcCanc() {
        
    }
    
//            consulta1 = "SELECT c FROM CauTrabajostecnicos c Join c.idSoltrab f "
//                    + " Join f.idEstatus g Join f.idSolicitud h join c.idUsuario u  "
//                    + " WHERE  g.idEstatus  in (2 , ?1) and h.idEstatus.idEstatus in ( ?1, 9) and "
//                    + " h.idMesaserv.idMesaserv in ( " + Mesaservicio + ")";
//            consulta1 = "SELECT c FROM CauTrabajostecnicos c Join c.idSoltrab f "
//                    + " Join f.idEstatus g Join f.idSolicitud h join c.idUsuario u  "
//                    + " WHERE  g.idEstatus  in (14 , 13) and h.idEstatus.idEstatus in ( ?1, 9) and  "
//                    + " h.idMesaserv.idMesaserv in ( " + Mesaservicio + ") and "
//                    + " f.idDeptrab.idDepartamentos.idDepartamentos " + cadenaFiltro;
//             consulta1 = "SELECT c FROM CauTrabajostecnicos c Join c.idSoltrab f " 
//                           + " Join f.idEstatus g Join f.idSolicitud h "                            
//                           + " WHERE  g.idEstatus  in (2 , ?1) and "
//                           + " h.idMesaserv.idMesaserv in (" + Mesaservicio + ")";
    
    public void  ObtenTrabajoTecnico(){    
        int idstatus;
        idstatus = 3;
        String Mesaservicio = Login01Controler1.getMesaDeServicio_parametro();
        
        String cadenaFiltroDep = "";
        String cadenaFiltroTra = "";
        int tipousu;
        tipousu = TipoUsuario();
        // la trasaccion asociada a este proceso es la no 10 
        // los niveles de consulta son
        // 1 para mesa de servicio
        // 2 para departamentos
        // 3 trabajos                
        // en este caso se obtienen los departamentos (2) de la tranbsacción 8 
        // transaccion 8 que es cau_trabTecnico y el parametro de tipo 2 que son jefaturas de departamento
        cadenaFiltroDep = EJBSolicitudTrabajos.ObtenFiltro1(Usua.getClaveusua(), 10, 2);
        cadenaFiltroTra = EJBSolicitudTrabajos.ObtenFiltro1(Usua.getClaveusua(), 10, 3);
         
        String consulta1;
        consulta1 = "";
        
        // Sin departamentos y sin trabajos entonces es un administraor
        //if ((cadenaFiltroDep.length() == 0) && (cadenaFiltroTra.length() == 0)) {
        //if ((cadenaFiltroDep.length() == 0) && (cadenaFiltroTra.length() == 0)) {
        if (tipousu == 1 || tipousu == 5) {    
            consulta1 = "SELECT c FROM CauTrabajostecnicos c Join c.idSoltrab f "
                    + " Join f.idEstatus g Join f.idSolicitud h "
                    + " WHERE  (g.idEstatus  in (2 , ?1) or c.fechahoraconc != null)  and "
                    + " h.idMesaserv.idMesaserv in (" + Mesaservicio + ")"
                    + " order by c.fechahoraasig desc ";
            // tiene deprtamentos aunque tenga trabajos se considera jefe de departamento  
        //} else if (cadenaFiltroDep.length() > 0) {
         } else if (tipousu ==  2) { 
            if (cadenaFiltroTra.length() > 0) {
                // ademas de departamento tiene asignado trabajos es jefe de departamento
                consulta1 = "SELECT c FROM CauTrabajostecnicos c Join c.idSoltrab f "
                        + " Join f.idEstatus g Join f.idSolicitud h join c.idUsuario u  "
                        + " WHERE  g.idEstatus  in (14 , 13, 17) and h.idEstatus.idEstatus in ( ?1, 9) and  "
                        + " h.idMesaserv.idMesaserv in ( " + Mesaservicio + ") and "
                        + " ( f.idDeptrab.idDepartamentos.idDepartamentos " + cadenaFiltroDep
                        + " or f.idDeptrab.idTrabajo.idTrabajo " + cadenaFiltroTra + ") and c.fechahoraconc = null"
                        + " order by c.fechahoraasig desc ";
                       // + " f.idDeptrab.idTrabajo.idTrabajo " + cadenaFiltroTra + ")";
            } else { // Tiene solo departamentos es un jefe de departamento
                consulta1 = "SELECT c FROM CauTrabajostecnicos c Join c.idSoltrab f "
                        + " Join f.idEstatus g Join f.idSolicitud h "
                        + " WHERE  (g.idEstatus  in (2 , ?1) or c.fechahoraconc != null) and "
                        + " h.idMesaserv.idMesaserv in (" + Mesaservicio + ") and "
                        + " f.idDeptrab.idDepartamentos.idDepartamentos " + cadenaFiltroDep
                        + " order by c.fechahoraasig desc " ;
            } //tiene trabajos y no tiene departamento se considera tecnico   
        //} else if (cadenaFiltroTra.length() > 0) {
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
    
    public void prepareDetalle() {
        // com.sfp.cau.SolicTrabComp(
        if (selected1 != null) {
            String consulta1 = " SELECT NEW com.sfp.cau.SolicTrabComp(c) FROM CauSolicitudtrabajos c Join c.idSolicitud e "                 
                    + " WHERE e.idSolicitud = ?1 ";
            
            setItemsdet2(EJBServConCan.Consulta(consulta1, selected1.getIdSoltrab().getIdSolicitud().getIdSolicitud()));
            
            consulta1 = " SELECT c FROM CauSolicitudtrabajos c Join c.idSolicitud e "                   
                    + " WHERE e.idSolicitud = ?1 ";
            
            //int idSolicitud = Integer.parseInt(selected.getIdSolicitud());
            
           // itemsdet2
            setItemsdet(EJBSolicitudTrabajos.Consulta_Solicitudestrabajo(consulta1, selected1.getIdSoltrab().getIdSolicitud().getIdSolicitud()));
            //itemsdet2 = (Collection<SolicTrabComp>) (SolicTrabComp) itemsdet;
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

                    SolicCompleta.setUrSolic(EmpSolic.getUr());
                    SolicCompleta.setDenoSolic(EmpSolic.getDenominacion());
                    SolicCompleta.setDenComSolic(EmpSolic.getDencompleta());
                    SolicCompleta.setDescPuestoSolic(EmpSolic.getDescpuesto());
                    SolicCompleta.setNivelPuestoSolic(EmpSolic.getNivelpresup());
                    SolicCompleta.setCorreoSolic(EmpSolic.getCorreo());
                    SolicCompleta.setPisoSolic(EmpSolic.getPiso());
                    SolicCompleta.setAlaSolic(EmpSolic.getAla());
                    SolicCompleta.setUbicaconSolic(EmpSolic.getUbicacion());
                    
                    // obtiene la información dl usuario atendido
                    if (!selected1.getIdSoltrab().getIdSolicitud().getUsuarioatendido().trim().equals("")
                            && !selected1.getIdSoltrab().getIdSolicitud().getUsuariosolicita().equals(selected1.getIdSoltrab().getIdSolicitud().getUsuarioatendido())) {
                        
                        Long01 = Long.parseLong(selected1.getIdSoltrab().getIdSolicitud().getUsuarioatendido());
                        if (Long01 < 1000000){
                            Long01 = 27000000 + Long01;
                        }
                        EmpSolic = EJBEmp.find(Long01);
                        
                        EmpSolic = EJBEmp.find(selected1.getIdSoltrab().getIdSolicitud().getUsuariosolicita());
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
