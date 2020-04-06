/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfp.ejb;

import com.sfp.model.CauTectipotrab;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;

/**
 *
 * @author jleal
 */
@Stateless
public class CauTectipotrabFacade extends AbstractFacade<CauTectipotrab> implements CauTectipotrabFacadeLocal {

    @PersistenceContext(unitName = "com.sfp_CAU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CauTectipotrabFacade() {
        super(CauTectipotrab.class);
    }
    
    @Override
    public List<CauTectipotrab> Consulta_TecTipoTrabajo(String consulta1, int Nocredencial){
        return Consulta_3(consulta1,Nocredencial); 
    }
    @Override
    public List<CauTectipotrab> Consulta_TecTipoTrabajo2(String consulta1, int idTrabajo, Date fecha){
         Query query=getEntityManager().createQuery(consulta1); 
         if (fecha != null){
           query.setParameter(1, idTrabajo);   
           query.setParameter(2, fecha,TemporalType.DATE);   
         }
         return query.getResultList();
    }
}
