/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfp.ejb;

import com.sfp.model.CauRolxusuario;
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
public class CauRolxusuarioFacade extends AbstractFacade<CauRolxusuario> implements CauRolxusuarioFacadeLocal {

    @PersistenceContext(unitName = "com.sfp_CAU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CauRolxusuarioFacade() {
        super(CauRolxusuario.class);
    }
    
    @Override
    public List<CauRolxusuario> findxRango(String Usuario) {
        List<CauRolxusuario> SalRxusuario;
        String consulta1 = "Select c from CauRolxusuario c"
                + " where c.idUsuario.claveusua = ?1 ";
        Query query = em.createQuery(consulta1);
        query.setParameter(1, Usuario);
        SalRxusuario = query.getResultList();
        return SalRxusuario;
    }    
}
