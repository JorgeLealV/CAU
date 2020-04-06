/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfp.ejb;

import com.sfp.model.CauRolbase;
import com.sfp.model.CauUsuariotipo;
import java.util.Collection;
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
public class CauRolbaseFacade extends AbstractFacade<CauRolbase> implements CauRolbaseFacadeLocal {

    @PersistenceContext(unitName = "com.sfp_CAU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CauRolbaseFacade() {
        super(CauRolbase.class);
    }
    
   public List<CauRolbase> Consulta_RolBase(String consulta1, int tipousuario){    
        List <CauRolbase> lista2;
        lista2 = null;
        CauRolbase paso1;
        paso1 = null;
        // tipousuario = 0;
        Query query=getEntityManager().createQuery(consulta1); 
         if (tipousuario != 0){
           query.setParameter(1, tipousuario); 
           lista2 = query.getResultList();
           if (!lista2.isEmpty()){
             paso1 = lista2.get(0);             
        }
         }
         return lista2;
    }
    
}
