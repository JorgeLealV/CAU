/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfp.ejb;

import com.sfp.model.CauTranxpar;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jleal
 */
@Local
public interface CauTranxparFacadeLocal {

    void create(CauTranxpar cauTranxpar);

    void edit(CauTranxpar cauTranxpar);

    void remove(CauTranxpar cauTranxpar);

    CauTranxpar find(Object id);

    List<CauTranxpar> findAll();

    List<CauTranxpar> findRange(int[] range);

    int count();
    
    public List<CauTranxpar> Consulta_Tranxpar(String consulta1, int Nocredencial);
    
}
