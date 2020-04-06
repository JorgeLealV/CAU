/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfp.ejb;

import com.sfp.model.CauRolbase;
import com.sfp.model.CauRolbastran;
import java.util.Collection;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jleal
 */
@Local
public interface CauRolbastranFacadeLocal {

    void create(CauRolbastran cauRolbastran);

    void edit(CauRolbastran cauRolbastran);

    void remove(CauRolbastran cauRolbastran);

    CauRolbastran find(Object id);

    List<CauRolbastran> findAll();

    List<CauRolbastran> findRange(int[] range);

    int count();
 
    public Collection<CauRolbastran> Consulta_RolBasTran(String consulta1, int parametro);
}
