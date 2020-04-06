/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfp.ejb;

import com.sfp.model.CauTipoautoriza;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jleal
 */
@Stateless
public class CauTipoautorizaFacade extends AbstractFacade<CauTipoautoriza> implements CauTipoautorizaFacadeLocal {

    @PersistenceContext(unitName = "com.sfp_CAU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CauTipoautorizaFacade() {
        super(CauTipoautoriza.class);
    }
    
}
