/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfp.ejb;

import com.sfp.model.CauDepartamentos;
import com.sfp.model.CauDeptrabajo;
import com.sfp.model.CauTipotrabajo;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jleal
 */
@Local
public interface CauDeptrabajoFacadeLocal {

    void create(CauDeptrabajo cauDeptrabajo);

    void edit(CauDeptrabajo cauDeptrabajo);

    void remove(CauDeptrabajo cauDeptrabajo);

    CauDeptrabajo find(Object id);

    List<CauDeptrabajo> findAll();

    List<CauDeptrabajo> findRange(int[] range);

    int count();
      
    public List<CauDeptrabajo> Consulta_CauDepTrabajo(CauTipotrabajo CauTT, CauDepartamentos CauDepto);
}
