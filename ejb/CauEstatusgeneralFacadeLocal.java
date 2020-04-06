/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfp.ejb;

import com.sfp.model.CauEstatusgeneral;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jleal
 */
@Local
public interface CauEstatusgeneralFacadeLocal {

    void create(CauEstatusgeneral cauEstatusgeneral);

    void edit(CauEstatusgeneral cauEstatusgeneral);

    void remove(CauEstatusgeneral cauEstatusgeneral);

    CauEstatusgeneral find(Object id);

    List<CauEstatusgeneral> findAll();

    List<CauEstatusgeneral> findRange(int[] range);

    int count();
    
}
