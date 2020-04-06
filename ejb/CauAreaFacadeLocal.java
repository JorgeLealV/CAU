/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfp.ejb;

import com.sfp.model.CauArea;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jleal
 */
@Local
public interface CauAreaFacadeLocal {

    void create(CauArea cauArea);

    void edit(CauArea cauArea);

    void remove(CauArea cauArea);

    CauArea find(Object id);

    List<CauArea> findAll();

    List<CauArea> findRange(int[] range);

    int count();
    
}
