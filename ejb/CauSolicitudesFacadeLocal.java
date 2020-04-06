/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfp.ejb;

import com.sfp.model.CauSolicitudes;
import java.util.Collection;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jleal
 */
@Local
public interface CauSolicitudesFacadeLocal {

    void create(CauSolicitudes cauSolicitudes);

    void edit(CauSolicitudes cauSolicitudes);

    void remove(CauSolicitudes cauSolicitudes);

    CauSolicitudes find(Object id);

    List<CauSolicitudes> findAll();

    List<CauSolicitudes> findRange(int[] range);

    int count();
    
    public Collection<CauSolicitudes> Consulta_Solic(String consulta1, String Nocredencial);
}
