/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * Se modifica para en develop con Vinicio
 * 
 * Se modifica para un feacture en Martin
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

/**
 *
 * @author jleal
 */
@Named(value = "consulta1Controler")
//@SessionScoped 
@ViewScoped
public class Consulta1 implements Serializable {
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
           
    public Consulta1() {
         
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
       Date Fecha = new Date();
       String error;
       String fechaS;
       DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
       fechaS = dateFormat.format(Fecha);       
       if((FechaInicial == null) || (FechaFin == null)){

       }
       else{
           String consulta1 = "SELECT c FROM CauTrabajostecnicos c " 
                        + " WHERE c.fechahoraasig between  ?1  and  ?2 order by c.idTrabtec";          
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
    
    public String CalculaTiempoSelected() {
        // se consultan los horarios de labores anuales , descansos anuales 
        // fechas especiales de descanso vacaciones etc. Ver documentación
        Calendar Fechapaso = Calendar.getInstance();
        Fechapaso.setTime(selected.getFechahoraasig());
        
        String Fecha1 = String.valueOf(Fechapaso.get(Calendar.DAY_OF_MONTH)) + "/" + 
                 String.valueOf(Fechapaso.get(Calendar.MONTH)+1) + "/" +
                 String.valueOf(Fechapaso.get(Calendar.YEAR)) ;
        
        String consulta1 = "SELECT c FROM CauHorausu c "
                + " WHERE ?1 " 
                + "  between c.fechaini and c.fechafin and "  +  "c.idUsuario.idUsuario = ?2 ";
        setHorasUsu(EJBHorausu.Consulta_Usua(consulta1, selected.getFechahoraasig(), selected.getIdUsuario().getIdUsuario()));
        // se consulta del trabajo las suspenciones de servicio que ha tenido 
        // durante su atención
        consulta1 = "SELECT c FROM CauSuspservicio c "
                    + " where c.idTrabtec.idTrabtec = ?1";
        setSuspServicio(EJBSuspServ.Consulta_TrabajosTecnico(consulta1, selected.getIdTrabtec()));
        
        // con las fechas inicial y final, las fechas de labores y las 
        // suspenciones de servicio se procede a resalizar el calculo del tiempo 
        // real asignado al servicio
        //======================================================================
        // Se crea clase de calculo de tiempo y se pasa las fechas inicial y final 
        // del servicio, un arreglo de los horarios de labores, y un arreglo de 
        // los horarios de suspención de estandar
        CalculaTiempo Inicio = new CalculaTiempo(selected.getIdUsuario().getIdUsuario(),
                selected.getFechahoraasig(), selected.getFechahoraconc(),
                HorasUsu, SuspServicio );
        // se solicita obtener de forma secuencial por dia los horarios de labores
        // asignados en el arreglo de HorasUsu
        Inicio.ObtenHorariousuario(selected.getIdUsuario().getIdUsuario());
        // se solicita calcular el tiempo efectivo con los datos anteriores
        minutos = Inicio.CalculaTiempoEfectivo();
        
        Grabar_Logs(Inicio, selected);
        // Leer el estandar de servicio para el servicio y compararlo con los
        // minutos que se contabilizaron si es menor establecer que si se cumplio 
        // el estandar de servicio si es mayor establecer que no se cumplio con el 
        // estandar de servicio actualizar tabla tabajosTecnicos 
        return "";
    }

    public String CalculaTiempoSelected2() {
        // se consultan los horarios de labores anuales , descansos anuales 
        // fechas especiales de descanso vacaciones etc. Ver documentación

        for (CauTrabajostecnicos TrabTecnico : items) {
            if ((TrabTecnico.getFechahoraconc() != null) && (TrabTecnico.getTiempocalculado() == null)) {

                Calendar Fechapaso = Calendar.getInstance();
                Fechapaso.setTime(TrabTecnico.getFechahoraasig());
                String Fecha1 = String.valueOf(Fechapaso.get(Calendar.DAY_OF_MONTH)) + "/"
                        + String.valueOf(Fechapaso.get(Calendar.MONTH) + 1) + "/"
                        + String.valueOf(Fechapaso.get(Calendar.YEAR));

                String consulta1 = "SELECT c FROM CauHorausu c "
                        + " WHERE ?1 " 
                        + " between c.fechaini and c.fechafin and " + "c.idUsuario.idUsuario = ?2 ";
                setHorasUsu(EJBHorausu.Consulta_Usua(consulta1,TrabTecnico.getFechahoraasig(),  TrabTecnico.getIdUsuario().getIdUsuario()));
                // se consulta del trabajo las suspenciones de servicio que ha tenido 
                // durante su atención
                consulta1 = "SELECT c FROM CauSuspservicio c "
                        + " where c.idTrabtec.idTrabtec = ?1";
                setSuspServicio(EJBSuspServ.Consulta_TrabajosTecnico(consulta1, TrabTecnico.getIdTrabtec()));

                // con las fechas inicial y final, las fechas de labores y las 
                // suspenciones de servicio se procede a resalizar el calculo del tiempo 
                // real asignado al servicio
                //======================================================================
                // Se crea clase de calculo de tiempo y se pasa las fechas inicial y final 
                // del servicio, un arreglo de los horarios de labores, y un arreglo de 
                // los horarios de suspención de estandar
                CalculaTiempo Inicio = new CalculaTiempo(TrabTecnico.getIdUsuario().getIdUsuario(),
                        TrabTecnico.getFechahoraasig(), TrabTecnico.getFechahoraconc(),
                        HorasUsu, SuspServicio);
                // se solicita obtener de forma secuencial por dia los horarios de labores
                // asignados en el arreglo de HorasUsu
                Inicio.ObtenHorariousuario(TrabTecnico.getIdUsuario().getIdUsuario());
                // se solicita calcular el tiempo efectivo con los datos anteriores
                minutos = Inicio.CalculaTiempoEfectivo();

                // Leer el estandar de servicio para el servicio y compararlo con los
                // minutos que se contabilizaron si es menor establecer que si se cumplio 
                // el estandar de servicio si es mayor establecer que no se cumplio con el 
                // estandar de servicio actualizar tabla tabajosTecnicos 
                CauTipotrabajo tipoTra;
                CauEstandarservicio estandserv;
                tipoTra = TrabTecnico.getIdSoltrab().getIdDeptrab().getIdTrabajo();
                estandserv = tipoTra.getIdEstserv();

                int minEst;
                minEst = estandserv.getMinutosnormal();
               // actualizar el estandar de servicio
                if (minutos > minEst){
                   TrabTecnico.setCumpleestandar("NO");                   
                } else {
                    TrabTecnico.setCumpleestandar("SI");
                }
                TrabTecnico.setTiempocalculado((int)minutos);
                EJBTrabajoTec.edit(TrabTecnico);
                
                // Grabar logs de calculo del servicio
                Grabar_Logs(Inicio, TrabTecnico);
            }
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
