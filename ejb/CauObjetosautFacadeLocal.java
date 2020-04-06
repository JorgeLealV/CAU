/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfp.ejb;

import com.sfp.model.CauObjetosaut;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jleal
 */
@Local
public interface CauObjetosautFacadeLocal {

    void create(CauObjetosaut cauObjetosaut);

    void edit(CauObjetosaut cauObjetosaut);

    void remove(CauObjetosaut cauObjetosaut);

    CauObjetosaut find(Object id);

    List<CauObjetosaut> findAll();

    List<CauObjetosaut> findRange(int[] range);

    int count();
    
}
