/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfp.ejb;

import com.sfp.model.CauTipodeusuario;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jleal
 */
@Local
public interface CauTipodeusuarioFacadeLocal {

    void create(CauTipodeusuario cauTipodeusuario);

    void edit(CauTipodeusuario cauTipodeusuario);

    void remove(CauTipodeusuario cauTipodeusuario);

    CauTipodeusuario find(Object id);

    List<CauTipodeusuario> findAll();

    List<CauTipodeusuario> findRange(int[] range);

    int count();
    
}
