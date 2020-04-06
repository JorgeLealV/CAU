/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfp.ejb;

import com.sfp.model.CauRolxtranxpar;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jleal
 */
@Stateless
public class CauRolxtranxparFacade extends AbstractFacade<CauRolxtranxpar> implements CauRolxtranxparFacadeLocal {

    @PersistenceContext(unitName = "com.sfp_CAU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CauRolxtranxparFacade() {
        super(CauRolxtranxpar.class);
    }
    
    @Override
    public List<CauRolxtranxpar> finxRol(String Consulta, int mesa){
      List<CauRolxtranxpar> Salida = Consulta_3(Consulta,mesa);   
      return Salida;
    }
    
}
