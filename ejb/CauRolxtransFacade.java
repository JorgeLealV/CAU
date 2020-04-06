/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfp.ejb;

import com.sfp.model.CauRolxtrans;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jleal
 */
@Stateless
public class CauRolxtransFacade extends AbstractFacade<CauRolxtrans> implements CauRolxtransFacadeLocal {
                                                                                
    
    
    @PersistenceContext(unitName = "com.sfp_CAU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CauRolxtransFacade() {
        super(CauRolxtrans.class);
    }
    
    public List<CauRolxtrans> consulta(String consulta, int rol){      
      return Consulta_3(consulta, rol);
    }
}
