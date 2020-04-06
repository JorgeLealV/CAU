/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfp.ejb;

import com.sfp.model.CauConfiguracion;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jleal
 */
@Stateless
public class CauConfiguracionFacade extends AbstractFacade<CauConfiguracion> implements CauConfiguracionFacadeLocal {

    @PersistenceContext(unitName = "com.sfp_CAU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CauConfiguracionFacade() {
        super(CauConfiguracion.class);
    }
    
    public List<CauConfiguracion> Consulta_Mesaconf(String consulta1, String Mesaserv){
        return Consulta(consulta1,Mesaserv);
    }
}
