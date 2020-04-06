/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfp.ejb;

import com.sfp.model.CauSuspservicio;
import com.sfp.model.CauTrabajostecnicos;
import java.util.Collection;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jleal
 */
@Stateless
public class CauSuspservicioFacade extends AbstractFacade<CauSuspservicio> implements CauSuspservicioFacadeLocal {

    @PersistenceContext(unitName = "com.sfp_CAU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CauSuspservicioFacade() {
        super(CauSuspservicio.class);
    }
    
    @Override
    public Collection<CauSuspservicio> Consulta_TrabajosTecnico(String consulta1, int Nocredencial){          
        return Consulta_3(consulta1,Nocredencial);                               
    } 
    
     @Override
    public Collection<CauSuspservicio> Consulta_SuspServicio2(String consulta1, Date FechaInicial, Date FechaFin){
         return Consulta_4(consulta1, FechaInicial,  FechaFin); 
    }
}
