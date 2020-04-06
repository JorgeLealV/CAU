/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfp.ejb;

import com.sfp.model.CauUsuariotipo;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jleal
 */
@Local
public interface CauUsuariotipoFacadeLocal {

    void create(CauUsuariotipo cauUsuariotipo);

    void edit(CauUsuariotipo cauUsuariotipo);

    void remove(CauUsuariotipo cauUsuariotipo);

    CauUsuariotipo find(Object id);

    List<CauUsuariotipo> findAll();

    List<CauUsuariotipo> findRange(int[] range);

    int count();
    
    public int Consulta_TipoUsuario(String consulta1, String Nocredencial);
    
}
