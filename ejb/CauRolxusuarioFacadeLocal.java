/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfp.ejb;

import com.sfp.model.CauRolxusuario;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jleal
 */
@Local
public interface CauRolxusuarioFacadeLocal {

    void create(CauRolxusuario cauRolxusuario);

    void edit(CauRolxusuario cauRolxusuario);

    void remove(CauRolxusuario cauRolxusuario);

    CauRolxusuario find(Object id);

    List<CauRolxusuario> findAll();

    List<CauRolxusuario> findRange(int[] range);

    int count();
    
    List<CauRolxusuario> findxRango(String Usuario);
    
}
