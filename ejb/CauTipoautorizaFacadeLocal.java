/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfp.ejb;

import com.sfp.model.CauTipoautoriza;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jleal
 */
@Local
public interface CauTipoautorizaFacadeLocal {

    void create(CauTipoautoriza cauTipoautoriza);

    void edit(CauTipoautoriza cauTipoautoriza);

    void remove(CauTipoautoriza cauTipoautoriza);

    CauTipoautoriza find(Object id);

    List<CauTipoautoriza> findAll();

    List<CauTipoautoriza> findRange(int[] range);

    int count();
    
}
