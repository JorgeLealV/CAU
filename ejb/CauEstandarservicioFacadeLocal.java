/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfp.ejb;

import com.sfp.model.CauEstandarservicio;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jleal
 */
@Local
public interface CauEstandarservicioFacadeLocal {

    void create(CauEstandarservicio cauEstandarservicio);

    void edit(CauEstandarservicio cauEstandarservicio);

    void remove(CauEstandarservicio cauEstandarservicio);

    CauEstandarservicio find(Object id);

    List<CauEstandarservicio> findAll();

    List<CauEstandarservicio> findRange(int[] range);

    int count();
    
}
