/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfp.ejb;

import com.sfp.model.CauMesaservicio;
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
public class CauMesaservicioFacade extends AbstractFacade<CauMesaservicio> implements CauMesaservicioFacadeLocal {

    @PersistenceContext(unitName = "com.sfp_CAU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CauMesaservicioFacade() {
        super(CauMesaservicio.class);
    }
    
    public List<CauMesaservicio> ConsultaMesa(String consulta1, int mesaserv) {
        List<CauMesaservicio> lista2;
        lista2 = null;
        CauMesaservicio paso1;
        paso1 = null;
        // tipousuario = 0;
        Query query = getEntityManager().createQuery(consulta1);
        if (mesaserv != 0) {
            query.setParameter(1, mesaserv);
            lista2 = query.getResultList();
            if (!lista2.isEmpty()) {
                paso1 = lista2.get(0);
            }
        } else {
            lista2 = query.getResultList();
            if (!lista2.isEmpty()) {
                paso1 = lista2.get(0);
            }
        }
        return lista2;
    }
}
