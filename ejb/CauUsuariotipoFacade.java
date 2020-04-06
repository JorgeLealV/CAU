/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfp.ejb;

import com.sfp.model.CauUsuariotipo;
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
public class CauUsuariotipoFacade extends AbstractFacade<CauUsuariotipo> implements CauUsuariotipoFacadeLocal {

    @PersistenceContext(unitName = "com.sfp_CAU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CauUsuariotipoFacade() {
        super(CauUsuariotipo.class);
    }
    
    public int Consulta_TipoUsuario(String consulta1, String Nocredencial){    
        List <CauUsuariotipo> lista2;
        int tipousuario;
        tipousuario = 0;
        Query query=getEntityManager().createQuery(consulta1); 
         if (Nocredencial.length() != 0){
           query.setParameter(1, Nocredencial); 
           lista2 = query.getResultList();
           if (!lista2.isEmpty()){
             CauUsuariotipo tipo= lista2.get(0);
             tipousuario = tipo.getIdTipousuario().getIdTipousuario();
        }
         }
         return tipousuario;
    }
}
