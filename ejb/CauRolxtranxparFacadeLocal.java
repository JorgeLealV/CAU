/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfp.ejb;

import com.sfp.model.CauRolxtranxpar;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jleal
 */
@Local
public interface CauRolxtranxparFacadeLocal {

    void create(CauRolxtranxpar cauRolxtranxpar);

    void edit(CauRolxtranxpar cauRolxtranxpar);

    void remove(CauRolxtranxpar cauRolxtranxpar);

    CauRolxtranxpar find(Object id);

    List<CauRolxtranxpar> findAll();

    List<CauRolxtranxpar> findRange(int[] range);
    
    List<CauRolxtranxpar> finxRol(String Consulta, int mesa);

    int count();
    
}
