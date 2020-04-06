/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfp.ejb;

import com.sfp.model.CauTranxpar;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jleal
 */
@Stateless
public class CauTranxparFacade extends AbstractFacade<CauTranxpar> implements CauTranxparFacadeLocal {

    @PersistenceContext(unitName = "com.sfp_CAU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CauTranxparFacade() {
        super(CauTranxpar.class);
    }
        
    @Override
    public List<CauTranxpar> Consulta_Tranxpar(String consulta1, int Nocredencial){
        return Consulta_3(consulta1,Nocredencial);
    }
}
