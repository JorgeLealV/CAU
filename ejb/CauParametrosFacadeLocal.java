/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfp.ejb;

import com.sfp.model.CauParametros;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jleal
 */
@Local
public interface CauParametrosFacadeLocal {

    void create(CauParametros cauParametros);

    void edit(CauParametros cauParametros);

    void remove(CauParametros cauParametros);

    CauParametros find(Object id);

    List<CauParametros> findAll();

    List<CauParametros> findRange(int[] range);

    int count();
    
}
