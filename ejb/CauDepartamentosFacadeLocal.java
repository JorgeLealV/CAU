/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfp.ejb;

import com.sfp.model.CauDepartamentos;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jleal
 */
@Local
public interface CauDepartamentosFacadeLocal {

    void create(CauDepartamentos cauDepartamentos);

    void edit(CauDepartamentos cauDepartamentos);

    void remove(CauDepartamentos cauDepartamentos);

    CauDepartamentos find(Object id);

    List<CauDepartamentos> findAll();

    List<CauDepartamentos> findRange(int[] range);

    int count();
    
    public Collection<CauDepartamentos> Consulta_Depto(String consulta1, Date Nocredencial);
}
