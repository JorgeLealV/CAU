/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfp.ejb;

import com.sfp.model.CauTipovalor;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jleal
 */
@Local
public interface CauTipovalorFacadeLocal {

    void create(CauTipovalor cauTipovalor);

    void edit(CauTipovalor cauTipovalor);

    void remove(CauTipovalor cauTipovalor);

    CauTipovalor find(Object id);

    List<CauTipovalor> findAll();

    List<CauTipovalor> findRange(int[] range);

    int count();
    
}
