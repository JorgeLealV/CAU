/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfp.ejb;

import com.sfp.model.CauTipooper;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jleal
 */
@Local
public interface CauTipooperFacadeLocal {

    void create(CauTipooper cauTipooper);

    void edit(CauTipooper cauTipooper);

    void remove(CauTipooper cauTipooper);

    CauTipooper find(Object id);

    List<CauTipooper> findAll();

    List<CauTipooper> findRange(int[] range);

    int count();
    
}
