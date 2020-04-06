/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfp.ejb;

import com.sfp.model.CauEmp;
import com.sfp.model.CauTipotrabajo;
import com.sfp.model.CauUsuarios;
import java.util.Collection;
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
public class CauEmpFacade extends AbstractFacade<CauEmp> implements CauEmpFacadeLocal {

    @PersistenceContext(unitName = "com.sfp_CAU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CauEmpFacade() {
        super(CauEmp.class);
    }
    
    @Override
    // Este procedimiento es el que se tiene que modificar para consultar el web service de 
    // SAP cuando se tenga se consulta con numero de personal
     public CauEmp iniciarSessionEmpleado(CauUsuarios us)
     {
        CauEmp empleado;
        empleado = null;
        String consulta1;
        Long Long01;
        try {
            consulta1 = "SELECT c FROM CauEmp c WHERE c.idNocredencial = ?1";
            //consulta = "FROM Usuario u WHERE u.usuario = ?1";
            //consulta = "FROM usuario c";
            //List<Usuario> arr_cust = (List<Usuario>)em.createQuery("SELECT c FROM Usuario c")
            //                  .getResultList(); 
            Query query=getEntityManager().createQuery(consulta1); 
            Long01 = Long.parseLong(us.getClaveusua());
            if (Long01 < 1000000){
                Long01 = 27000000 + Long01;
            }
            query.setParameter(1, Long01);            
            List<CauEmp> lista = query.getResultList();
            if (!lista.isEmpty()){
                empleado=lista.get(0);
            }
        } catch (Exception e) {
            throw e;
        }
        finally
        {
            
        }
        return empleado;
     }
     
    @Override
    public Collection<CauEmp> Consulta_TipTrabajo(String consulta1, int Nocredencial) {
        return Consulta_3(consulta1, Nocredencial);
    }
    
    @Override
    public CauEmp ConsultaEmp(long Credencial){       
        CauEmp empleado;
        empleado = null;
        String consulta1;
        Long Long01;
        try {
            consulta1 = "SELECT c FROM CauEmp c WHERE c.idNocredencial = ?1";
            //consulta = "FROM Usuario u WHERE u.usuario = ?1";
            //consulta = "FROM usuario c";
            //List<Usuario> arr_cust = (List<Usuario>)em.createQuery("SELECT c FROM Usuario c")
            //                  .getResultList(); 
            Query query = getEntityManager().createQuery(consulta1);
            Long01 = Credencial;
            if (Long01 < 1000000) {
                Long01 = 27000000 + Long01;
            }
            query.setParameter(1, Long01);
            List<CauEmp> lista = query.getResultList();
            if (!lista.isEmpty()) {
                empleado = lista.get(0);
            }
        } catch (Exception e) {
            throw e;
        } finally {

        }
        return empleado;
    }
}
