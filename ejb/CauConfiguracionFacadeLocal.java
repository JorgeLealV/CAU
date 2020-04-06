/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfp.ejb;

import com.sfp.model.CauConfiguracion;
import com.sfp.model.CauDepjefe;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jleal
 */
@Local
public interface CauConfiguracionFacadeLocal {

    void create(CauConfiguracion cauConfiguracion);

    void edit(CauConfiguracion cauConfiguracion);

    void remove(CauConfiguracion cauConfiguracion);

    CauConfiguracion find(Object id);

    List<CauConfiguracion> findAll();

    List<CauConfiguracion> findRange(int[] range);

    int count();
    
    public List<CauConfiguracion> Consulta_Mesaconf(String consulta1, String Mesaserv);
    
}
