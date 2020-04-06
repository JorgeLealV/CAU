/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfp.ejb;

import com.sfp.cau.SolicTrabComp;
import com.sfp.model.CauDepjefe;
import com.sfp.model.CauRol;
import com.sfp.model.CauRolxtrans;
import com.sfp.model.CauRolxtranxpar;
import com.sfp.model.CauRolxusuario;
import com.sfp.model.CauSolicitudtrabajos;
import com.sfp.model.CauTipotrabajo;
import com.sfp.model.CauUsuarios;
import com.sfp.model.CauUsuariotipo;
import java.util.Collection;
import java.util.Date;
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
public class CauSolicitudtrabajosFacade extends AbstractFacade<CauSolicitudtrabajos> implements CauSolicitudtrabajosFacadeLocal {

    @PersistenceContext(unitName = "com.sfp_CAU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CauSolicitudtrabajosFacade() {
        super(CauSolicitudtrabajos.class);
    }
   @Override
    public Collection<CauSolicitudtrabajos> Consulta_Solicitudestrabajo(String consulta1, int Nocredencial){          
        return Consulta_3(consulta1,Nocredencial);                               
    } 
    @Override
    public Collection<CauSolicitudtrabajos> Consulta_Solicitudestrabajo2(String consulta1, Date FechaInicial, Date FechaFin){
         return Consulta_4(consulta1, FechaInicial,  FechaFin); 
    }
    
    @Override
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
    
    
    @Override
    public String  ObtenFiltro1(String usuario, int transaccion, int niveCons){
      String cadenaFiltro;
      String consulta1;
      cadenaFiltro = "";
      Collection<CauRol> lista2;
      Collection<CauRolxtranxpar> lista3;
      CauRolxusuario aa;
      int paso1 ;

      // Se obtienen los roles por usuario para posteriormente de esos roles y con la transacción 
      // se obtengan los diferentes tipos de acceso para esta transaccion, que pueden ser
      // 1 departamentos , 2 trabajos y 3 usuarios
      consulta1 = " Select e "
                  + " FROM  CauRolxusuario d join d.idRol e"
                  + " where d.idUsuario.claveusua = ?1";
      
      Query query = em.createQuery(consulta1);
      query.setParameter(1, usuario);
      lista2 = query.getResultList();
      // obtiene los roles del usuario
        for (CauRol rolx : lista2) {
            // obtiene las trransacciones de cada rol 
            for (CauRolxtrans rolxtrans : rolx.getCauRolxtransCollection()) {
                // si la transacción es igual a la del prametro se procesa dicha transacción 
                if (rolxtrans.getIdTrans().getIdTrans() == transaccion) {
                    String consulta3 = " Select d from CauRolxtranxpar d join d.idRolxtrans e join d.idParam f "
                            + " where e.idTrans.idTrans  = ?1 and f.idParam = ?2 and e.idRol.idRol = " + rolx.getIdRol().toString();
                    query = em.createQuery(consulta3);
                    query.setParameter(1, transaccion);
                    query.setParameter(2, niveCons);
                    lista3 = query.getResultList();
                    for (CauRolxtranxpar parametro : lista3) {
                        if (cadenaFiltro.equals("")) {
                            cadenaFiltro = cadenaFiltro + parametro.getValor();
                        } else {
                            cadenaFiltro = cadenaFiltro + "," + parametro.getValor();
                        }
                    }
                }
            }
        }
        
        if (cadenaFiltro.contains("*")) {
            cadenaFiltro = " <> 0 ";
        } else if (!cadenaFiltro.isEmpty()){
            cadenaFiltro = "in (" + cadenaFiltro + ")";
        } else {
            cadenaFiltro = " in (99999999) ";
        }
       
        return cadenaFiltro;
    }
    
   @Override
    public String  ObtenFiltro2(String usuario, int transaccion, int niveCons){
      String cadenaFiltro;
      String consulta1;
      cadenaFiltro = "";
      Collection<CauRol> lista2;
      Collection<CauRolxtranxpar> lista3;
      CauRolxusuario aa;
      int paso1 ;

      // Se obtienen los roles por usuario para posteriormente de esos roles y con la transacción 
      // se obtengan los diferentes tipos de acceso para esta transaccion, que pueden ser
      // 1 departamentos , 2 trabajos y 3 usuarios
      consulta1 = " Select e "
                  + " FROM  CauRolxusuario d join d.idRol e"
                  + " where d.idUsuario.claveusua = ?1";
      
      Query query = em.createQuery(consulta1);
      query.setParameter(1, usuario);
      lista2 = query.getResultList();
      // obtiene los roles del usuario
        for (CauRol rolx : lista2) {
            // obtiene las trransacciones de cada rol 
            for (CauRolxtrans rolxtrans : rolx.getCauRolxtransCollection()) {
                // si la transacción es igual a la del prametro se procesa dicha transacción 
                if (rolxtrans.getIdTrans().getIdTrans() == transaccion) {
                    String consulta3 = " Select d from CauRolxtranxpar d join d.idRolxtrans e join d.idParam f "
                            + " where e.idTrans.idTrans  = ?1 and f.idParam = ?2 and e.idRol.idRol = " + rolx.getIdRol().toString();
                    query = em.createQuery(consulta3);
                    query.setParameter(1, transaccion);
                    query.setParameter(2, niveCons);
                    lista3 = query.getResultList();
                    for (CauRolxtranxpar parametro : lista3) {
                        if (cadenaFiltro.equals("")) {
                            cadenaFiltro = cadenaFiltro + parametro.getValor();
                        } else {
                            cadenaFiltro = cadenaFiltro + "," + parametro.getValor();
                        }
                    }
                }
            }
        }
        
        if (cadenaFiltro.contains("*")) {
            cadenaFiltro = " <> 0 ";
        } else if (!cadenaFiltro.isEmpty()){
            cadenaFiltro = "in (" + cadenaFiltro + ")";
        } else {
            cadenaFiltro = " in (99999999) ";
        }
        
        return cadenaFiltro;
    } 
    
    
    @Override
    public String  ObtenFiltro(String usuario, int transaccion, int niveCons){
      String cadenaFiltro;
      String consulta1;
      cadenaFiltro = "";
      Collection<CauDepjefe> lista2;
      Collection<CauRolxtranxpar> lista3;
      int paso1;
       // es una jefe de departamento
      if (niveCons == 1) {
          consulta1 = " Select e "
                  + " FROM  CauDepjefe e "
                  + " where e.cauUsuarios.claveusua = ?1";
          Query query = em.createQuery(consulta1);
          query.setParameter(1, usuario);
          lista2 = query.getResultList();
          for (CauDepjefe depto : lista2) {
              if (cadenaFiltro.equals("")) {
                  cadenaFiltro = "(" + depto.getCauDepartamentos().getIdDepartamentos();
              } else {
                  cadenaFiltro = cadenaFiltro + "," + depto.getCauDepartamentos().getIdDepartamentos();
              }
          }
      }
      if (cadenaFiltro.length() > 0 ){
          cadenaFiltro = cadenaFiltro + ") ";
      }
      return cadenaFiltro;
    }     
}
