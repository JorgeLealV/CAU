/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfp.ejb;

import com.sfp.cau.SolicTrabComp;
import java.util.Collection;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jleal
 */
@Local
public interface SolicTrabCompFacadeLocal {

    void create(SolicTrabComp solicTrabComp);

    void edit(SolicTrabComp solicTrabComp);

    void remove(SolicTrabComp solicTrabComp);

    SolicTrabComp find(Object id);

    List<SolicTrabComp> findAll();

    List<SolicTrabComp> findRange(int[] range);

    int count();
    
    public Collection<SolicTrabComp> Consulta(String consulta1, int  Nocredencial);
}
