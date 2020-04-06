/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfp.ejb;

import com.sfp.model.CauRol;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jleal
 */
@Local
public interface CauRolFacadeLocal {

    void create(CauRol cauRol);

    void edit(CauRol cauRol);

    void remove(CauRol cauRol);

    CauRol find(Object id);

    List<CauRol> findAll();

    List<CauRol> findRange(int[] range);

    int count();
    
}
