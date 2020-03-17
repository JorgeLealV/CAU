/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfp.cau;

import com.sfp.model.CauMenu;

/**
 *
 * @author jleal
 */
public class CauMenuAmp {
    private CauMenu caumenu;
    private String codigotrans;
    
    
    public CauMenuAmp(Object origen) {
        Object Original = origen;
        Object[] arr1;
        arr1 = (Object[]) Original;
        caumenu = new CauMenu();
        caumenu = (CauMenu) arr1[0];
        codigotrans = (String) arr1[1];
    }
    
    public CauMenu getCaumenu() {
        return caumenu;
    }
    public void setCaumenu(CauMenu caumenu) {
        this.caumenu = caumenu;
    }
    public String getCodigotrans() {
        return codigotrans;
    }
    public void setCodigotrans(String codigotrans) {
        this.codigotrans = codigotrans;
    }
}
