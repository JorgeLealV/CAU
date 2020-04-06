/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfp.ejb;

import com.sfp.model.CauTipotrabajo;
import java.util.Collection;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jleal
 */
@Local
public interface CauTipotrabajoFacadeLocal {

    void create(CauTipotrabajo cauTipotrabajo);

    void edit(CauTipotrabajo cauTipotrabajo);

    void remove(CauTipotrabajo cauTipotrabajo);

    CauTipotrabajo find(Object id);

    List<CauTipotrabajo> findAll();

    List<CauTipotrabajo> findRange(int[] range);

    int count();
    
    public Collection<CauTipotrabajo> Consulta_TipTrabajo(String consulta1, int Nocredencial);
}
