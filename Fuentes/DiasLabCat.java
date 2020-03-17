  /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfp.cau;

import com.sfp.ejb.CauHorausuFacadeLocal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author jleal
 */
public class DiasLabCat {

    
    private Calendar Fechax;
    private Date Fechay;
    private int dia;
    private int mes;
    private int anio;
    private Collection<HorasOper> Horas = new ArrayList<>();      
    private int secuencia;
    private int diasemana;
    private String FechaS;
    private String FechaS2;

    public Calendar getFechax() {
        return Fechax;
    }

    public void setFechax(Calendar Fecha) {
        this.Fechax = Fecha;
    }
   
    
    public int getDiasemana() {
        return diasemana;
    }

    public void setDiasemana(int diasemana) {
        this.diasemana = diasemana;
    }

    public Collection<HorasOper> getHoras() {
        return Horas;
    }

    public void setHoras(Collection<HorasOper> Horas) {
        this.Horas = Horas;
    }

    
    
    public DiasLabCat(Calendar FechaAsig, int sec) {
        Fechax = Calendar.getInstance();
        Fechax.set(FechaAsig.get(Calendar.YEAR), 
                   FechaAsig.get(Calendar.MONTH),
                   FechaAsig.get(Calendar.DAY_OF_MONTH),
                   FechaAsig.get(Calendar.HOUR_OF_DAY),
                   FechaAsig.get(Calendar.MINUTE),
                   FechaAsig.get(Calendar.SECOND)); 
        Fechay = FechaAsig.getTime(); 
        FechaS = String.valueOf(FechaAsig.get(Calendar.DAY_OF_MONTH)) + "/" + 
                 String.valueOf(FechaAsig.get(Calendar.MONTH)+1) + "/" +
                 String.valueOf(FechaAsig.get(Calendar.YEAR)) ;
        
        FechaS2 = String.valueOf(Fechax.get(Calendar.DAY_OF_MONTH)) + "/" + 
                 String.valueOf(Fechax.get(Calendar.MONTH)+1) + "/" +
                 String.valueOf(Fechax.get(Calendar.YEAR)) ;
        
        
        dia = FechaAsig.get(Calendar.DAY_OF_MONTH);
        mes = FechaAsig.get(Calendar.MONTH)+1;
        anio = FechaAsig.get(Calendar.YEAR);
        secuencia = sec;        
        diasemana = Fechax.get(Calendar.DAY_OF_WEEK);
    }
    
    public void  AgregarHora(Calendar horaini, Calendar horafin, int oper){
        HorasOper horaadic = new HorasOper();        
        horaadic.setHoraIni(horaini);
        horaadic.setHoraFin(horafin);
        horaadic.setOper(oper);
        // si la matris de rangos de horas es vacio solo se agrega al arreglo
        if (Horas.isEmpty()){
         Horas.add(horaadic);
        }
        else
           // Si no es vacio se procede a analizar los rangos para establecer 
           // que rango se sobreescribe pero ademas se generan mas rangos 
           ProcesaHora(horaadic); 
    }

    public int getSecuencia() {
        return secuencia;
    }

    public void setSecuencia(int secuencia) {
        this.secuencia = secuencia;
    }

    public String getFechaS() {
        return FechaS;
    }

    public void setFechaS(String FechaS) {
        this.FechaS = FechaS;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public String getFechaS2() {
        return FechaS2;
    }

    public void setFechaS2(String FechaS2) {
        this.FechaS2 = FechaS2;
    }

    public Date getFechay() {
        return Fechay;
    }

    public void setFechay(Date Fechay) {
        this.Fechay = Fechay;
    }
    
    // inserta un rango de hora de inicio y hora de fin establecido en la variable 
    // horaadic y que analiza todos los rangos establecidos en el arreglo de Horas 
    // para incertar el nuevo rango.
    private void ProcesaHora(HorasOper horaadic) {
       //Coleccion de Horas que se utiliza para rearmar la original incorporando 
       // la hora horaadic y posteriormente se asigna esta a la original
       Collection<HorasOper> HoraNew = new ArrayList<>();
       
       // Se obtiene la fecha con la hora inicial 
       Calendar HI = Calendar.getInstance();
       HI.setTime(horaadic.getHoraIniD());
       
       // Se obtiene la fecha con la hora final       
       Calendar HF = Calendar.getInstance();
       HF.setTime(horaadic.getHoraFinD());
       
       int oper = horaadic.getOper();
       boolean Agregado = true;
       int operi;
       Calendar HIi = Calendar.getInstance();
       Calendar HFi = Calendar.getInstance();
        for (HorasOper Ind : Horas) {           
           HIi.setTime(Ind.getHoraIniD());
           HFi.setTime(Ind.getHoraFinD());
           operi = Ind.getOper();
           // Si la fecha final actual es menor que la fecha inicial a incertar
           // quiere decir que es un rango anterior al rango que se 
           // quiere agregar por lo tanto se agrega el rango Ind al arreglo a 
           // armar completo
            if (Agregado == true) {
                if (ComH(HFi, "<", HI)) {
                    HoraNew.add(Ind);
                    // Si la fecha inicial actual es menor que la fecha inicial a incertar
                    // quiere decir que es un rango posterior  al rango que se 
                    // quiere agregar por lo tanto se agrega el rango Ind al arreglo a 
                    // armar completo               
                } else if (ComH(HF, "<", HIi)) {
                    // Se agregan los rangos posteriores ya que la la fecha a incertar
                    // ya se debio haber incertado y la HIi > HF 
                    HoraNew.add(Ind);
                    Agregado = true;
                } else if (ComH(HIi, "=", HI)) {
                    if (ComH(HF, "=", HFi)) {
                        // la HI= HIi y HF=HFi
                        HoraNew.add(horaadic);
                        Agregado = true;
                    } else if (ComH(HF, "<", HFi)) {
                        // la HIi = HI y demas HF < HFi se agregan los rangos
                        // (HI,HF) y (HF+1,HFi)
                        HoraNew.add(horaadic);
                        HorasOper paso1 = new HorasOper();
                        paso1.setHoraIni(sumResMinFecha(HF, 1));
                        paso1.setHoraFin(HFi);
                        paso1.setOper(oper);
                        HoraNew.add(paso1);
                        Agregado = true;
                    } else if (ComH(HF, ">", HFi)) {
                        // HIi = HI y HF > HFi el rango (HI,HF) supera el limite
                        // superior HFi se deja para el siguiente rango no se ahrega 
                        // solo se marca
                        Agregado = false;
                        continue;
                    }
                } else if (ComH(HI, ">", HIi) && (ComH(HF, "<", HFi))) {
                    // HI y HF dentro del rango de HIi y HFi se agregan tres rangos
                    // (HIi-HI-1), (Hi-HF) y (HF+1,HFi)
                    HorasOper paso1 = new HorasOper();
                    paso1.setHoraIni(HIi);
                    paso1.setHoraFin(sumResMinFecha(HI, -1));
                    paso1.setOper(operi);
                    HoraNew.add(paso1); //anterior HIi,HI-1, operi
                    HoraNew.add(horaadic);// nuevo HI, HF, oper
                    HorasOper paso2 = new HorasOper();
                    paso2.setHoraIni(sumResMinFecha(HF, 1));
                    paso2.setHoraFin(HFi);
                    paso2.setOper(operi);
                    HoraNew.add(paso2); //Posterior HF+1, HFi, operi
                    Agregado = true;                    
                } else if ( (ComH(HI,">",HIi) && (ComH(HI,"<",HFi))) && (ComH(HF,">",HFi)) ){
                    HorasOper paso2 = new HorasOper();
                    paso2.setHoraIni(HIi);
                    paso2.setHoraFin(sumResMinFecha(HI, -1));
                    paso2.setOper(operi);
                    HoraNew.add(paso2); //inicial HIi, HI-1, operi
                    Agregado = false;
                    continue;
                } else if (ComH(HI, "=", HFi)) {
                    HorasOper paso2 = new HorasOper();
                    paso2.setHoraIni(HIi);
                    paso2.setHoraFin(sumResMinFecha(HFi, -1));
                    paso2.setOper(operi);
                    HoraNew.add(paso2); //inicial HIi, HFi-1, operi
                    Agregado = false;
                    continue;
                }
            }
            
            if (Agregado == false) {
                if (ComH(HF, "=", HFi)) {
                    HorasOper paso2 = new HorasOper();
                    paso2.setHoraIni(HI);
                    paso2.setHoraFin(HF);
                    paso2.setOper(oper);
                    HoraNew.add(paso2); //inicial HIi, HFi-1, operi
                    Agregado = true;
                } else if (ComH(HF, "<", HFi)) {
                    HorasOper paso2 = new HorasOper();
                    paso2.setHoraIni(HI);
                    paso2.setHoraFin(HF);
                    paso2.setOper(oper);
                    HoraNew.add(paso2); //inicial HIi, HFi-1, operi
                    HorasOper paso3 = new HorasOper();
                    paso3.setHoraIni(sumResMinFecha(HF, 1));
                    paso3.setHoraFin(HFi);
                    paso3.setOper(operi);
                    HoraNew.add(paso3); //inicial HIi, HFi-1, operi
                    Agregado = true;
                } else if (ComH(HF, ">", HFi)) {
                    Agregado = false;
                }
            }
        }
        Horas = HoraNew;
    }
  
    // funcion que compara dos fechas Comp es una cadena que contiene el tipo de 
    // comparación que se requiere esto se puede hacer mejor con Calendar pero 
    // es mas didactico de esta forma
    private boolean ComH(Calendar tiempoi, String Comp, Calendar tiempof){
        int horai;
        int horaf;
        int mini;
        int minf;
        boolean resultado = false;
        horai = tiempoi.get(Calendar.HOUR_OF_DAY);
        horaf = tiempof.get(Calendar.HOUR_OF_DAY);
        mini = tiempoi.get(Calendar.MINUTE);
        minf = tiempof.get(Calendar.MINUTE);
        
        switch (Comp) {
            case "=":
                if ((horai == horaf) && (mini == minf)){
                    resultado = true;
                }   break;
            case ">=":
                if ((horai == horaf) && (mini == minf)){
                    resultado = true;
                } else if ((horai == horaf) && (mini > minf)){
                    resultado = true;
                } else resultado = (horai > horaf);
                break;
            case ">":
                if ((horai == horaf) && (mini > minf)){
                    resultado = true;
                } else resultado = (horai > horaf);
                break;
            case "<=":
                if ((horai == horaf) && (mini == minf)){
                    resultado = true;
                } else if ((horai == horaf) && (mini < minf)){
                    resultado = true;
                } else resultado = (horai < horaf);
                break;
            case "<":
                if ((horai == horaf) && (mini < minf)){
                    resultado = true;
                } else resultado = (horai < horaf);
                break;
            default:
                break;
        }        
        return resultado;
    }
    // funcion que resta y suma minutos a una hora del dia  dependiendo si minuto
    // es positivo o negativo
    public Calendar sumResMinFecha(Calendar fecha, int minuto){
       //Calendar calendar = Calendar.getInstance();
       //calendar.setTime(fecha); // Configuramos la fecha que se recibe
       Calendar fechap = Calendar.getInstance();
       fechap.setTime(fecha.getTime());
       fechap.add(Calendar.MINUTE, minuto);  // numero de minutos a añadir, o restar en caso de minutos<0
       return fechap; // Devuelve el objeto Date con las nuevos minutos añadidos
    }  
}
