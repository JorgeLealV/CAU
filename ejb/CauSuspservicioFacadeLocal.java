/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfp.ejb;

import com.sfp.model.CauSuspservicio;
import com.sfp.model.CauTrabajostecnicos;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jleal
 */
@Local
public interface CauSuspservicioFacadeLocal {

    void create(CauSuspservicio cauSuspservicio);

    void edit(CauSuspservicio cauSuspservicio);

    void remove(CauSuspservicio cauSuspservicio);

    CauSuspservicio find(Object id);

    List<CauSuspservicio> findAll();

    List<CauSuspservicio> findRange(int[] range);

    int count();
    
    public Collection<CauSuspservicio> Consulta_TrabajosTecnico(String consulta1, int Nocredencial);
    
    public Collection<CauSuspservicio> Consulta_SuspServicio2(String consulta1, Date FechaInicial, Date FechaFin);
}
