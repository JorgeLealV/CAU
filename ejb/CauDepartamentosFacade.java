/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfp.ejb;

import com.sfp.model.CauDepartamentos;
import com.sfp.model.CauSolicitudes;
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
public class CauDepartamentosFacade extends AbstractFacade<CauDepartamentos> implements CauDepartamentosFacadeLocal {

    @PersistenceContext(unitName = "com.sfp_CAU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CauDepartamentosFacade() {
        super(CauDepartamentos.class);
    }
    @Override
    public Collection<CauDepartamentos> Consulta_Depto(String consulta1, Date Nocredencial){          
        return Consulta_2(consulta1,Nocredencial);                               
    }
}
