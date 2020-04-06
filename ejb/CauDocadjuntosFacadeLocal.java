/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfp.ejb;

import com.sfp.model.CauDocadjuntos;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jleal
 */
@Local
public interface CauDocadjuntosFacadeLocal {

    void create(CauDocadjuntos cauDocadjuntos);

    void edit(CauDocadjuntos cauDocadjuntos);

    void remove(CauDocadjuntos cauDocadjuntos);

    CauDocadjuntos find(Object id);

    List<CauDocadjuntos> findAll();

    List<CauDocadjuntos> findRange(int[] range);

    int count();
    
    public List<CauDocadjuntos> Consulta_CauDocAdjuntos(String Consulta, int soltrab);
}
