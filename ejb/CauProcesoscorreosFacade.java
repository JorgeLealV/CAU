/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfp.ejb;

import com.sfp.model.CauProcesoscorreos;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jleal
 */
@Stateless
public class CauProcesoscorreosFacade extends AbstractFacade<CauProcesoscorreos> implements CauProcesoscorreosFacadeLocal {

    @PersistenceContext(unitName = "com.sfp_CAU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CauProcesoscorreosFacade() {
        super(CauProcesoscorreos.class);
    }
    
    @Override
    public Collection<CauProcesoscorreos> Consulta_Proceso(String consulta1, int Nocredencial){          
        return Consulta_3(consulta1,Nocredencial);                               
    }
}
