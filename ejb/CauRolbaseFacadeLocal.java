/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfp.ejb;

import com.sfp.model.CauRolbase;
import java.util.Collection;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jleal
 */
@Local
public interface CauRolbaseFacadeLocal {

    void create(CauRolbase cauRolbase);

    void edit(CauRolbase cauRolbase);

    void remove(CauRolbase cauRolbase);

    CauRolbase find(Object id);

    List<CauRolbase> findAll();

    List<CauRolbase> findRange(int[] range);

    int count();
    
   public Collection<CauRolbase> Consulta_RolBase(String consulta1, int tipousuario);
    
}
