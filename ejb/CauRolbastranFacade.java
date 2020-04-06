/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfp.ejb;

import com.sfp.model.CauRolbase;
import com.sfp.model.CauRolbastran;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author jleal
 */
@Stateless
public class CauRolbastranFacade extends AbstractFacade<CauRolbastran> implements CauRolbastranFacadeLocal {

    @PersistenceContext(unitName = "com.sfp_CAU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CauRolbastranFacade() {
        super(CauRolbastran.class);
    }
    
    public List<CauRolbastran> Consulta_RolBasTran(String consulta1, int parametro){    
        List <CauRolbastran> lista2;
        lista2 = null;
        CauRolbastran paso1;
        paso1 = null;
        // tipousuario = 0;
        Query query=getEntityManager().createQuery(consulta1); 
         if (parametro != 0){
           query.setParameter(1, parametro); 
           lista2 = query.getResultList();
           if (!lista2.isEmpty()){
             paso1 = lista2.get(0);             
        }
         }
         return lista2;
    }
}
