/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfp.ejb;

import com.sfp.model.CauDepjefe;
import com.sfp.model.CauTipotrabajo;
import java.util.Collection;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jleal
 */
@Local
public interface CauDepjefeFacadeLocal {

    void create(CauDepjefe cauDepjefe);

    void edit(CauDepjefe cauDepjefe);

    void remove(CauDepjefe cauDepjefe);

    CauDepjefe find(Object id);

    List<CauDepjefe> findAll();

    List<CauDepjefe> findRange(int[] range);

    int count();    
    
    public List<CauDepjefe> Consulta_DepJefe(String consulta1, int Nocredencial);
}
