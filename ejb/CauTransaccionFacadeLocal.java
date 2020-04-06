/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfp.ejb;

import com.sfp.model.CauTransaccion;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jleal
 */
@Local
public interface CauTransaccionFacadeLocal {

    void create(CauTransaccion cauTransaccion);

    void edit(CauTransaccion cauTransaccion);

    void remove(CauTransaccion cauTransaccion);

    CauTransaccion find(Object id);

    List<CauTransaccion> findAll();

    List<CauTransaccion> findRange(int[] range);

    int count();
    
}
