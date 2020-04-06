/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfp.ejb;

import com.sfp.model.CauUsuarios;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jleal
 */
@Local
public interface CauUsuariosFacadeLocal {

    void create(CauUsuarios cauUsuarios);

    void edit(CauUsuarios cauUsuarios);

    void remove(CauUsuarios cauUsuarios);

    CauUsuarios find(Object id);

    List<CauUsuarios> findAll();

    List<CauUsuarios> findRange(int[] range);

    public CauUsuarios iniciarSession(CauUsuarios us);
      
    int count();
    
}
