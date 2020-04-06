/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfp.ejb;

import com.sfp.model.CauDocadjuntos;
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
public class CauDocadjuntosFacade extends AbstractFacade<CauDocadjuntos> implements CauDocadjuntosFacadeLocal {

    @PersistenceContext(unitName = "com.sfp_CAU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CauDocadjuntosFacade() {
        super(CauDocadjuntos.class);
    }
    
    public List<CauDocadjuntos> Consulta_CauDocAdjuntos(String Consulta, int soltrab){
        List <CauDocadjuntos> DocAdjuntos = Consulta_3(Consulta, soltrab);
        return DocAdjuntos;
        
    }
}
