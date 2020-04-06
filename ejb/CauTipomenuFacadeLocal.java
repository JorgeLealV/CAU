/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfp.ejb;

import com.sfp.model.CauTipomenu;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jleal
 */
@Local
public interface CauTipomenuFacadeLocal {

    void create(CauTipomenu cauTipomenu);

    void edit(CauTipomenu cauTipomenu);

    void remove(CauTipomenu cauTipomenu);

    CauTipomenu find(Object id);

    List<CauTipomenu> findAll();

    List<CauTipomenu> findRange(int[] range);

    int count();
    
}
