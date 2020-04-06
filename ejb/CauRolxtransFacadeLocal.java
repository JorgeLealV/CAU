/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfp.ejb;

import com.sfp.model.CauRolxtrans;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jleal
 */
@Local
public interface CauRolxtransFacadeLocal {

    void create(CauRolxtrans cauRolxtrans);

    void edit(CauRolxtrans cauRolxtrans);

    void remove(CauRolxtrans cauRolxtrans);

    CauRolxtrans find(Object id);

    List<CauRolxtrans> findAll();

    List<CauRolxtrans> findRange(int[] range);

    int count();
    
    List<CauRolxtrans> consulta(String consulta, int rol);
    
}
