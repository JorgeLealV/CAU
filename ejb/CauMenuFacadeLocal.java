/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfp.ejb;

import com.sfp.cau.CauMenuAmp;
import com.sfp.model.CauMenu;
import com.sfp.model.CauUsuarios;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jleal
 */
@Local
public interface CauMenuFacadeLocal {

    void create(CauMenu cauMenu);

    void edit(CauMenu cauMenu);

    void remove(CauMenu cauMenu);

    CauMenu find(Object id);

    List<CauMenu> findAll();

    List<CauMenu> findRange(int[] range);

    int count();
    
    List<Object> MenuUsuario(String usuario, String Mesaservicio);
    
    List<Object> MenuAyuda();
    
    List<CauMenu> MenuRol(String Consulta, int idRol);
}
