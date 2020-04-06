/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfp.ejb;

import com.sfp.model.CauDepartamentos;
import com.sfp.model.CauDeptrabajo;
import com.sfp.model.CauTipotrabajo;
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
public class CauDeptrabajoFacade extends AbstractFacade<CauDeptrabajo> implements CauDeptrabajoFacadeLocal {

    @PersistenceContext(unitName = "com.sfp_CAU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CauDeptrabajoFacade() {
        super(CauDeptrabajo.class);
    }
 
     public List<CauDeptrabajo> Consulta_CauDepTrabajo(CauTipotrabajo CauTT, CauDepartamentos CauDepto){
       String Consulta;
       Consulta = "Select c from CauDeptrabajo c where c.idTrabajo = ?1 and c.idDepartamentos = ?2";
       Query query=getEntityManager().createQuery(Consulta); 
         if (CauTT != null &&  CauDepto != null){
           query.setParameter(1, CauTT);    
           query.setParameter(2, CauDepto);   
           
         }
         return query.getResultList();  
     }
}
