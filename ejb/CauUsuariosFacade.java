/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfp.ejb;

import com.sfp.model.CauUsuarios;
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
public class CauUsuariosFacade extends AbstractFacade<CauUsuarios> implements CauUsuariosFacadeLocal {

    @PersistenceContext(unitName = "com.sfp_CAU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CauUsuariosFacade() {
        super(CauUsuarios.class);
    }
    
    @Override
    public CauUsuarios iniciarSession(CauUsuarios us){
        CauUsuarios usuario;
        usuario = null;
        String consulta;
        try {
            consulta = "SELECT u FROM CauUsuarios u WHERE u.claveusua = ?1 and u.contrasena = ?2";
            //onsulta = "FROM Usuario u WHERE u.usuario = ?1";
            //consulta = "SELECT c FROM CauUsuarios c";
            //List<Usuario> arr_cust = (List<Usuario>)em.createQuery("SELECT c FROM Usuario c")
            //                  .getResultList(); 
            Query query=em.createQuery(consulta);  
            query.setParameter(1, us.getClaveusua());
            query.setParameter(2, us.getContrasena());
            List<CauUsuarios> lista = query.getResultList();
            if (!lista.isEmpty()){
                usuario=lista.get(0);
            }
        } catch (Exception e) {
            throw e;
        }
        finally
        {
            
        }
        return usuario;
    } 
}
