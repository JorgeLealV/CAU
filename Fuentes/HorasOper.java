/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfp.cau;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author jleal
 */
// Clase que contiene la hora inicial y final en diferentes formatos para un 
// rango del dia ademas establece una variable Oper que determina si ese rango 
// es inabil o habil con 1 y -1 ademas hay otro campo denominado prioridad
// que establece si el rango se puede sobreescribir o no pero no esta 
// programad actualmente
public class HorasOper {
    private Calendar HoraIni;
    private Calendar HoraFin;
    private Date HoraIniD;
    private Date HoraFinD;
    private String HoraIniS;
    private String HoraFinS;
    private int prioridad;
    private int Oper;
    private int horai, minutosi, segundosi;
    private int horaf, minutosf, segundosf;

    public Calendar getHoraIni() {
        return HoraIni;
    }
    // cuando se establece la hora inicial se definen los demas campos
    public void setHoraIni(Calendar HoraIni) {
        this.HoraIni = Calendar.getInstance();        
        this.HoraIni.setTime(HoraIni.getTime());
        HoraIniD = HoraIni.getTime();
        //FechaInicial = dateFormat.parse("1/01/2019");        
        horai =HoraIni.get(Calendar.HOUR_OF_DAY);
        minutosi = HoraIni.get(Calendar.MINUTE);
        segundosi = HoraIni.get(Calendar.SECOND);
        HoraIniS=String.valueOf(horai)+":"+String.valueOf(minutosi)+":"+String.valueOf(segundosi);
    }

    public Calendar getHoraFin() {
        return HoraFin;
    }

    // cuando se establece la hora final se definen los demas campos
    public void setHoraFin(Calendar HoraFin) {
        this.HoraFin = Calendar.getInstance();        
        this.HoraFin.setTime(HoraFin.getTime());
        HoraFinD = HoraFin.getTime();
         
        horaf =HoraFin.get(Calendar.HOUR_OF_DAY);
        minutosf = HoraFin.get(Calendar.MINUTE);
        segundosf = HoraFin.get(Calendar.SECOND);
        HoraFinS=String.valueOf(horaf)+":"+String.valueOf(minutosf)+":"+String.valueOf(segundosf);
    }

    public String getHoraIniS() {
        return HoraIniS;
    }

    public void setHoraIniS(String HoraIniS) {
        this.HoraIniS = HoraIniS;
    }

    public String getHoraFinS() {
        return HoraFinS;
    }

    public void setHoraFinS(String HoraFinS) {
        this.HoraFinS = HoraFinS;
    }

    public int getOper() {
        return Oper;
    }

    public void setOper(int Oper) {
        this.Oper = Oper;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public Date getHoraIniD() {
        return HoraIniD;
    }

    public void setHoraIniD(Date HoraIniD) {
        this.HoraIniD = HoraIniD;
    }

    public Date getHoraFinD() {
        return HoraFinD;
    }

    public void setHoraFinD(Date HoraFinD) {
        this.HoraFinD = HoraFinD;
    }

    public int getHorai() {
        return horai;
    }

    public void setHorai(int horai) {
        this.horai = horai;
    }

    public int getMinutosi() {
        return minutosi;
    }

    public void setMinutosi(int minutosi) {
        this.minutosi = minutosi;
    }

    public int getSegundosi() {
        return segundosi;
    }

    public void setSegundosi(int segundosi) {
        this.segundosi = segundosi;
    }

    public int getHoraf() {
        return horaf;
    }

    public void setHoraf(int horaf) {
        this.horaf = horaf;
    }

    public int getMinutosf() {
        return minutosf;
    }

    public void setMinutosf(int minutosf) {
        this.minutosf = minutosf;
    }

    public int getSegundosf() {
        return segundosf;
    }

    public void setSegundosf(int segundosf) {
        this.segundosf = segundosf;
    }

    
   
}
