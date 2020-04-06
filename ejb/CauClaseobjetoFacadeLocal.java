/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfp.ejb;

import com.sfp.model.CauClaseobjeto;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jleal
 */
@Local
public interface CauClaseobjetoFacadeLocal {

    void create(CauClaseobjeto cauClaseobjeto);

    void edit(CauClaseobjeto cauClaseobjeto);

    void remove(CauClaseobjeto cauClaseobjeto);

    CauClaseobjeto find(Object id);

    List<CauClaseobjeto> findAll();

    List<CauClaseobjeto> findRange(int[] range);

    int count();
    
}
