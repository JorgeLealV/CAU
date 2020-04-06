/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfp.ejb;

import com.sfp.cau.CauMenuAmp;
import com.sfp.model.CauMenu;
import com.sfp.model.CauRol;
import com.sfp.model.CauRolxusuario;
import com.sfp.model.CauUsuarios;
import com.sfp.model.CauUsuariotipo;
import java.util.ArrayList;
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
public class CauMenuFacade extends AbstractFacade<CauMenu> implements CauMenuFacadeLocal {

    @PersistenceContext(unitName = "com.sfp_CAU")
    private EntityManager em;
    private Collection<CauRolxusuario> rolxusuario;
    CauRolxusuario UsuarioRol;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CauMenuFacade() {
        super(CauMenu.class);
    }

    
      public List<Object> MenuUsuario1(String usuario) {      
        List<Object> lista2;
        List<CauUsuariotipo> lista3;
        String consulta1;
        int tipoUsuario;
        CauUsuariotipo Usuario1;
        int menuasig = 0;
        lista2 = null;
        
        tipoUsuario = 0;
        try {
                        
//            consulta1 = "SELECT c ,t.urltrans " 
//                        + " FROM CauMenu c LEFT OUTER JOIN c.idTrans t, CauRol r, CauRolxusuario ru,  "
//                        + "      CauUsuarios cu, CauTipomenu tm " 
//                        + " WHERE c.idRol = r " 
//                        + " AND r.idRol = ru.cauRolxusuarioPK.idRol "                        
//                        + " AND  ru.cauRolxusuarioPK.idUsuario = cu.idUsuario "
//                        + " AND c.idTipmenu = tm "
//                     //   + " AND c LEFT OUTER JOIN c.idTrans  t"
//                        + " AND cu.claveusua = ?1 "
//                        + " ORDER BY c.idRol.idRol, c.idTipmenu.idTipmenu, c.idPadre, c.idMenu";
            
//            Query query = em.createQuery(consulta1);
//            query.setParameter(1, usuario);
//            lista2 = query.getResultList();
            
            consulta1 = "Select c from CauUsuariotipo c"
                    + " where c.idUsuario.claveusua = ?1 order by c.idTipousuario.idTipousuario";
               
           // Usuario1.getClaveusua()
            
            Query query = em.createQuery(consulta1);
            query.setParameter(1, usuario);
            lista3 = query.getResultList();
            if (!lista3.isEmpty()){
                Usuario1 = lista3.get(0);
                tipoUsuario = Usuario1.getIdTipousuario().getIdTipousuario();
                switch (tipoUsuario) {
                    case 1:
                        menuasig = 2;
                        break;
                    case 2:
                        menuasig = 2;
                        break;
                    case 3:
                        menuasig = 110;
                        break;
                    case 4:
                        menuasig = 1;
                        break;    
                    default:
                        menuasig = 1;
                        break;
                }
            }
            
            // los menus para los difrentes tipos de usuarios estan asociados a los siguientes roles
            // el rol usuario solicitante esta asociado al rol 1  con menu 1 y tipoUsuario = 1
            if (tipoUsuario != 0) {          
                consulta1 = "Select c, t.urltrans "
                            + " from CauMenu c  LEFT OUTER join c.idTrans t "
                            + " where c.idRol.idRol = ?1 or c.idRol.idRol = 99999"
                            + " ORDER BY c.idRol.idRol, c.idTipmenu.idTipmenu, c.idPadre, c.idMenu";
                query = em.createQuery(consulta1);
                query.setParameter(1, menuasig);
                lista2 = query.getResultList();
                CauMenu menu;
                if (!lista2.isEmpty()) {
                    lista2.get(0);
                    //return lista;
                }
            }
            

        } catch (Exception e) {
            throw e;
        }
        return lista2;
    }
    
    @Override
    public List<CauMenu> MenuRol(String Consulta, int idRol){
        List <CauMenu> lista_menu;
        lista_menu = Consulta_3(Consulta, idRol);
        return lista_menu;
    }
      
     @Override
     public List<Object> MenuUsuario(String usuario, String Mesaservicio) {
        List<Object> lista2;
        lista2 = null;
        String consulta1;
        int tipoUsuario;
        CauUsuariotipo Usuario1;
        int menuasig = 0;

        // realizar consulta para determinar cuales roles tiene el usuario y conforme a esos roles 
        // consultar en que menus se encuentran asociados por lo pronto es solo un rol por usuario
        consulta1 = "select c "
                + " from CauRolxusuario c"
                + " where c.idUsuario.claveusua = ?1 ";

        Query query = em.createQuery(consulta1);

        query.setParameter(1, usuario);
        rolxusuario = query.getResultList();
        String Cadena = "";
        if (!rolxusuario.isEmpty()) {
            for (CauRolxusuario UsuarioRol1 :rolxusuario){
              if (Cadena.length() == 0){
                  Cadena = UsuarioRol1.getIdRol().getIdRol().toString();
              }  
              else {
                  Cadena = Cadena + "," + UsuarioRol1.getIdRol().getIdRol().toString();
              }
            }

            consulta1 = "Select c, t.urltrans "
                    + " from CauMenu c LEFT OUTER join c.idTrans t"
                    + " where c.idRol.idRol in ( " + Cadena + " )  or c.idRol.idRol = 99999"                     
                    + " ORDER BY c.idRol.idRol, c.idTipmenu.idTipmenu, c.idPadre, c.idMenu, t.idTrans";
            
            // si solo es una mesa de servicio se omite el proceso de seleccion de mesa de servicios
            // id_trans = 36
            if (Mesaservicio.split(",").length == 1){
               consulta1 = "Select c, t.urltrans "
                    + " from CauMenu c LEFT OUTER join c.idTrans t"
                    + " where ( c.idRol.idRol in ( " + Cadena + " )  or c.idRol.idRol = 99999 ) and c.idTrans.idTrans <> 36"
                    + " ORDER BY c.idRol.idRol, c.idTipmenu.idTipmenu, c.idPadre, c.idMenu, t.idTrans"; 
            }
           
            
            query = em.createQuery(consulta1);
            //query.setParameter(1, Cadena);
            lista2 = query.getResultList();
            CauMenu menu;
            if (!lista2.isEmpty()) {
                lista2.get(0);
                //return lista;
            }
        }
        return lista2;
    }
      
    @Override
    public List<Object> MenuAyuda() {
        List<Object> lista2;
        String consulta1;
        try {
            consulta1 = "SELECT c ,t.urltrans "
                    + " FROM CauMenu c LEFT OUTER JOIN c.idTrans t, CauRol r, CauRolxusuario ru,  "
                    + "      CauUsuarios cu, CauTipomenu tm "
                    + " WHERE c.idRol = r "
                    + " AND r.idRol = ru.cauRolxusuarioPK.idRol "
                    + " AND  ru.cauRolxusuarioPK.idUsuario = cu.idUsuario "
                    + " AND c.idTipmenu = tm "
                    + " AND (c.idMenu = 5 or c.idPadre.idMenu = 5)"
                    + " AND cu.claveusua = 'UsuaSolicitante' "
                    + " ORDER BY c.idTipmenu, c.idPadre, c.idMenu ";

            consulta1 = "SELECT c ,t.urltrans "
                    + " FROM CauMenu c JOIN c.idTrans t, CauRol r, CauRolxusuario ru,  "
                    + "      CauUsuarios cu, CauTipomenu tm "
                    + " WHERE "
                    + " (c.idMenu = 5 or c.idPadre.idMenu = 5)"
                    + "  AND cu.claveusua = 'UsuaSolicitante' "
                    + "  ORDER BY c.idTipmenu, c.idPadre, c.idMenu ";
            
            consulta1 = "Select c, t.urltrans "
                    + " from CauMenu c LEFT OUTER join c.idTrans t"
                    + " where c.idRol.idRol = 99999"                     
                    + " ORDER BY c.idRol.idRol, c.idTipmenu.idTipmenu, c.idPadre, c.idMenu, t.idTrans";
            
            Query query = em.createQuery(consulta1);
            lista2 = query.getResultList();
        } catch (Exception e) {
            throw e;
        }
        return lista2;
    }   
}