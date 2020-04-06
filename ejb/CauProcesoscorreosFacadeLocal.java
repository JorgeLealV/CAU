/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfp.ejb;

import com.sfp.model.CauProcesoscorreos;
import java.util.Collection;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jleal
 */
@Local
public interface CauProcesoscorreosFacadeLocal {

    void create(CauProcesoscorreos cauProcesoscorreos);

    void edit(CauProcesoscorreos cauProcesoscorreos);

    void remove(CauProcesoscorreos cauProcesoscorreos);

    CauProcesoscorreos find(Object id);

    List<CauProcesoscorreos> findAll();

    List<CauProcesoscorreos> findRange(int[] range);

    int count();
    
    public Collection<CauProcesoscorreos> Consulta_Proceso(String consulta1, int Nocredencial);
}
