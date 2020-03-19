/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfp.controler;
import com.sfp.cau.CalculaTiempo;
import com.sfp.cau.ImagenDiaHora;
import com.sfp.ejb.CauEstandarservicioFacadeLocal;
import com.sfp.ejb.CauHorausuFacadeLocal;
import com.sfp.ejb.CauLogoperFacadeLocal;
import com.sfp.ejb.CauMesaservicioFacadeLocal;
import com.sfp.ejb.CauSuspservicioFacadeLocal;
import com.sfp.ejb.CauTipooperFacadeLocal;
import com.sfp.ejb.CauTrabajostecnicosFacadeLocal;
import com.sfp.ejb.CauTransaccionFacadeLocal;
import com.sfp.model.CauEstandarservicio;
import com.sfp.model.CauHorausu;
import com.sfp.model.CauLogoper;
import com.sfp.model.CauMesaservicio;
import com.sfp.model.CauSuspservicio;
import com.sfp.model.CauTipooper;
import com.sfp.model.CauTipotrabajo;
import com.sfp.model.CauTrabajostecnicos;
import com.sfp.model.CauTransaccion;
import com.sfp.model.CauUsuarios;
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
import javax.xml.ws.WebServiceRef;

/**
 *
 * @author jleal
 * 
 * Esto es un feature de vinicio esta es la corrección de vinicio Hotfix
 */
@Named(value = "consulta1Controler2")
//@SessionScoped 
@ViewScoped
public class Consulta2 implements Serializable {

    
    @EJB
    private CauEstandarservicioFacadeLocal EJBEstandarServ;
    
    @EJB
    private CauTrabajostecnicosFacadeLocal EJBTrabajoTec;
    
    @EJB
    private CauHorausuFacadeLocal EJBHorausu;

    @EJB
    private CauSuspservicioFacadeLocal EJBSuspServ;
    
    @EJB
    private CauTransaccionFacadeLocal EJBTransaccion;
    
    @EJB
    private CauTipooperFacadeLocal EJBTipoOper;
    
    @EJB
    private CauLogoperFacadeLocal EJBLogOper;
    
    @EJB
    private CauMesaservicioFacadeLocal EJBMesaServicio;
    
    @Inject private Login01Controler Login01Controler1;
    
    private Collection<CauHorausu> HorasUsu; 
    private Collection<CauSuspservicio> SuspServicio; 
    private CauTrabajostecnicos selected;
    private Collection<CauTrabajostecnicos> items;
  
    private CauUsuarios Usua;
    private Date FechaInicial;
    private Date FechaFin;
    private long minutos=0;
           
    public Consulta2() {
         
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
    
    
    public CauTrabajostecnicos getSelected() {
        return selected;
    }
    
    public void setSelected(CauTrabajostecnicos selected) {
        this.selected = selected;
    }

    public Collection<CauTrabajostecnicos> getItems() {
        if (items == null) {
            ObtenSolicitudes(1);
        }
        return items; 
    }

    public void setItems(Collection<CauTrabajostecnicos> items) {
        this.items = items;
    }
    
    
    public void  ObtenSolicitudes(int flag){ 
       // ojo utilizar los parametros de la transacción 37 
       Date Fecha = new Date();
       String error;
       String fechaS;
       DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
       fechaS = dateFormat.format(Fecha);  
       String Mesaservicio = Login01Controler1.getMesaDeServicio_parametro();
       if((FechaInicial == null) || (FechaFin == null)){

       }
       else{
           String consulta1 = "SELECT c FROM CauTrabajostecnicos c " 
                        + " WHERE c.idSoltrab.idSolicitud.idMesaserv.idMesaserv in (" + Mesaservicio + ") and "
                        + " c.fechahoraconc = NULL and c.fechahoraasig between  ?1  and  ?2 order by c.idTrabtec ";
           setItems(EJBTrabajoTec.Consulta_TrabajosTecnico2(consulta1, FechaInicial, FechaFin));
            
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
