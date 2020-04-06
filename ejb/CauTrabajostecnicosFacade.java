/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfp.ejb;

import com.sfp.model.CauSolicitudtrabajos;
import com.sfp.model.CauTrabajostecnicos;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;

/**
 *
 * @author jleal
 */
@Stateless
public class CauTrabajostecnicosFacade extends AbstractFacade<CauTrabajostecnicos> implements CauTrabajostecnicosFacadeLocal {

    @PersistenceContext(unitName = "com.sfp_CAU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CauTrabajostecnicosFacade() {
        super(CauTrabajostecnicos.class);
    }
    
    @Override
    public Collection<CauTrabajostecnicos> Consulta_TrabajosTecnico(String consulta1, int Nocredencial){          
        return Consulta_3(consulta1,Nocredencial);                               
    } 
    @Override
    public Collection<CauTrabajostecnicos> Consulta_TrabajosTecnico2(String consulta1, Date Fechainic, Date Fechafin) {
        return Consulta_4(consulta1, Fechainic, Fechafin);
    }   
}
