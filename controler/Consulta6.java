/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfp.controler;
import com.sfp.ejb.CauSolicitudtrabajosFacadeLocal;
import com.sfp.ejb.CauSuspservicioFacadeLocal;
import com.sfp.model.CauSolicitudtrabajos;
import com.sfp.model.CauSuspservicio;
import com.sfp.model.CauUsuarios;
import java.io.Serializable;
import java.text.DateFormat;
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
@Named(value = "consulta1Controler6")
//@SessionScoped 
@ViewScoped
public class Consulta6 implements Serializable {    
    @EJB
    private CauSolicitudtrabajosFacadeLocal EJBSolicTrab;

    @Inject private Login01Controler Login01Controler1;
    
    private CauSolicitudtrabajos selected;
    private Collection<CauSolicitudtrabajos> items;
  
    private CauUsuarios Usua;
    private Date FechaInicial;
    private Date FechaFin;
    private long minutos=0;
           
    public Consulta6() {
         
    }

    public Date getFechaInicial() {
        return FechaInicial;
    }

    public void setFechaInicial(Date FechaInicial) {
        this.FechaInicial = FechaInicial;
    }

    public Date getFechaFin() {
        return FechaFin;
    }

    public void setFechaFin(Date FechaFin) {
        this.FechaFin = FechaFin;
    }
    @PostConstruct
    private void init() {
       FacesContext facesContext = FacesContext.getCurrentInstance();
       Map params = facesContext.getExternalContext().getRequestParameterMap();
       Usua = (CauUsuarios) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
    }

    private void ObtenParametros(){
        
    }
    
    
    public CauSolicitudtrabajos getSelected() {
        return selected;
    }
    
    public void setSelected(CauSolicitudtrabajos selected) {
        this.selected = selected;
    }

    public Collection<CauSolicitudtrabajos> getItems() {
        if (items == null) {
            ObtenSolicitudes(1);
        }
        return items; 
    }

    public void setItems(Collection<CauSolicitudtrabajos> items) {
        this.items = items;
    }
    
    
    public void  ObtenSolicitudes(int flag){    
       Date Fecha = new Date();
       String error;
       String fechaS;
       DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
       fechaS = dateFormat.format(Fecha);  
       String Mesaservicio = Login01Controler1.getMesaDeServicio_parametro();
       if((FechaInicial == null) || (FechaFin == null)){

       }
       else{
           String consulta1 = "SELECT c FROM CauSolicitudtrabajos c " 
                        + " WHERE c.idSolicitud.idMesaserv.idMesaserv in (" + Mesaservicio + ") "
                        + " and c.idEstatus.idEstatus in ( 7, 15) "
                        + " and c.fechaasigsolicusua between  ?1  and  ?2 order by c.idSoltrab ";
           setItems(EJBSolicTrab.Consulta_Solicitudestrabajo2(consulta1, FechaInicial, FechaFin));            
       }
       
//       if (flag == 1){
//           
//       } else {
//           Date Fecha2 = ParseFecha("01/03/2017");
//           String consulta2 = "SELECT c FROM CauDepartamentos c " 
//                        + " WHERE ?1 between c.fechaviginicdep and c.fechavigfindep ";   
//           setItems(EJBDepartamentos.Consulta_Depto(consulta2,Fecha2));           
//       }       
    }
   
    public String Consultar() {       
       ObtenSolicitudes(2);
       return ""; 
    }
}
