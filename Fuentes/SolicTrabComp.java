/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfp.cau;

import com.sfp.model.CauSolicitudtrabajos;
import com.sfp.model.CauTrabajostecnicos;

/**
 *
 * @author jleal
 */
public class SolicTrabComp {

    private String tecnico;
    private CauSolicitudtrabajos interno;

    public CauSolicitudtrabajos getInterno() {
        return interno;
    }

    public void setInterno(CauSolicitudtrabajos interno) {
        this.interno = interno;
    }

    public String getTecnico() {
        return tecnico;
    }

    public void setTecnico(String tecnico) {
        this.tecnico = tecnico;
    }
    public SolicTrabComp(CauSolicitudtrabajos solictrab) {
        interno = solictrab;
        tecnico = "";
        int asig = solictrab.getCauTrabajostecnicosCollection().size();
        if (asig > 0){
            // CauTrabajostecnicos paso1;
            for(CauTrabajostecnicos paso2 :solictrab.getCauTrabajostecnicosCollection()){
                tecnico = paso2.getIdUsuario().getIdNocredencial().getNombre();    
            }
        }
    } 
}
