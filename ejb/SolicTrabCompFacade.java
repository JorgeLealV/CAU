/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfp.ejb;

import com.sfp.cau.SolicTrabComp;
import com.sfp.model.CauDepartamentos;
import com.sfp.model.CauSolicitudes;
import java.util.Collection;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jleal
 */
@Stateless
public class SolicTrabCompFacade extends AbstractFacade<SolicTrabComp> implements SolicTrabCompFacadeLocal {

    @PersistenceContext(unitName = "com.sfp_CAU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SolicTrabCompFacade() {
        super(SolicTrabComp.class);
    }
    @Override
    public Collection<SolicTrabComp> Consulta(String consulta1, int  Nocredencial){          
        return Consulta_3(consulta1,Nocredencial);                               
    }
}
