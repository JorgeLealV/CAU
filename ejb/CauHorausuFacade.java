/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfp.ejb;

import com.sfp.model.CauHorausu;
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
public class CauHorausuFacade extends AbstractFacade<CauHorausu> implements CauHorausuFacadeLocal {

    @PersistenceContext(unitName = "com.sfp_CAU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CauHorausuFacade() {
        super(CauHorausu.class);
    }
    
    @Override
    public Collection<CauHorausu> Consulta_Usua(String consulta1,Date fecha1, int usuario){          
        return Consulta_5(consulta1,fecha1, usuario);                               
    }
}
