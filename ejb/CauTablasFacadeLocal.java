/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfp.ejb;

import com.sfp.model.CauTablas;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jleal
 */
@Local
public interface CauTablasFacadeLocal {

    void create(CauTablas cauTablas);

    void edit(CauTablas cauTablas);

    void remove(CauTablas cauTablas);

    CauTablas find(Object id);

    List<CauTablas> findAll();

    List<CauTablas> findRange(int[] range);

    int count();
    
}
