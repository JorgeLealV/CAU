/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfp.ejb;

import com.sfp.model.CauSolicitudes;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jleal
 */
@Stateless
public class CauSolicitudesFacade extends AbstractFacade<CauSolicitudes> implements CauSolicitudesFacadeLocal {

    @PersistenceContext(unitName = "com.sfp_CAU")
    private EntityManager em;

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    public CauSolicitudesFacade() {
        super(CauSolicitudes.class);
    }
    
    @Override
    public Collection<CauSolicitudes> Consulta_Solic(String consulta1, String Nocredencial){          
        return Consulta(consulta1,Nocredencial);                               
    }
    
}
