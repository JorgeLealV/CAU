/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfp.ejb;

import com.sfp.model.CauHorausu;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jleal
 */
@Local
public interface CauHorausuFacadeLocal {

    void create(CauHorausu cauHorausu);

    void edit(CauHorausu cauHorausu);

    void remove(CauHorausu cauHorausu);

    CauHorausu find(Object id);

    List<CauHorausu> findAll();

    List<CauHorausu> findRange(int[] range);

    int count();
    
    public Collection<CauHorausu> Consulta_Usua(String consulta1, Date fecha1,  int usuario);
    
}
