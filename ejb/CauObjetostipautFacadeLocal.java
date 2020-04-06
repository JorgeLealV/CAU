/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfp.ejb;

import com.sfp.model.CauObjetostipaut;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jleal
 */
@Local
public interface CauObjetostipautFacadeLocal {

    void create(CauObjetostipaut cauObjetostipaut);

    void edit(CauObjetostipaut cauObjetostipaut);

    void remove(CauObjetostipaut cauObjetostipaut);

    CauObjetostipaut find(Object id);

    List<CauObjetostipaut> findAll();

    List<CauObjetostipaut> findRange(int[] range);

    int count();
    
}
