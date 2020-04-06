/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfp.ejb;

import com.sfp.model.CauSolicitudes;
import com.sfp.model.CauTipotrabajo;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jleal
 */
@Stateless
public class CauTipotrabajoFacade extends AbstractFacade<CauTipotrabajo> implements CauTipotrabajoFacadeLocal {

    @PersistenceContext(unitName = "com.sfp_CAU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CauTipotrabajoFacade() {
        super(CauTipotrabajo.class);
    }
    
    @Override
    public Collection<CauTipotrabajo> Consulta_TipTrabajo(String consulta1, int Nocredencial){          
        return Consulta_3(consulta1,Nocredencial);                               
    }
}
