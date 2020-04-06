/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfp.ejb;

import com.sfp.model.CauLogoper;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jleal
 */
@Local
public interface CauLogoperFacadeLocal {

    void create(CauLogoper cauLogoper);

    void edit(CauLogoper cauLogoper);

    void remove(CauLogoper cauLogoper);

    CauLogoper find(Object id);

    List<CauLogoper> findAll();

    List<CauLogoper> findRange(int[] range);

    int count();
    
}
