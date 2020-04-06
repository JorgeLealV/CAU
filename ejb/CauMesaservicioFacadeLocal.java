/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfp.ejb;

import com.sfp.model.CauMesaservicio;
import java.util.Collection;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jleal
 */
@Local
public interface CauMesaservicioFacadeLocal {

    void create(CauMesaservicio cauMesaservicio);

    void edit(CauMesaservicio cauMesaservicio);

    void remove(CauMesaservicio cauMesaservicio);

    CauMesaservicio find(Object id);

    List<CauMesaservicio> findAll();

    List<CauMesaservicio> findRange(int[] range);

    int count();
    
    public List<CauMesaservicio> ConsultaMesa(String consulta1, int tipousuario);
}
