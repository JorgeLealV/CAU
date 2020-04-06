/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfp.ejb;

import com.sfp.model.CauGrupousuario;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jleal
 */
@Local
public interface CauGrupousuarioFacadeLocal {

    void create(CauGrupousuario cauGrupousuario);

    void edit(CauGrupousuario cauGrupousuario);

    void remove(CauGrupousuario cauGrupousuario);

    CauGrupousuario find(Object id);

    List<CauGrupousuario> findAll();

    List<CauGrupousuario> findRange(int[] range);

    int count();
    
}
