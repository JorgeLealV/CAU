/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfp.ejb;

import com.sfp.model.CauSolicitudtrabajos;
import com.sfp.model.CauUsuarios;
import com.sfp.model.CauUsuariotipo;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jleal
 */
@Local
public interface CauSolicitudtrabajosFacadeLocal {

    void create(CauSolicitudtrabajos cauSolicitudtrabajos);

    void edit(CauSolicitudtrabajos cauSolicitudtrabajos);

    void remove(CauSolicitudtrabajos cauSolicitudtrabajos);

    CauSolicitudtrabajos find(Object id);

    List<CauSolicitudtrabajos> findAll();

    List<CauSolicitudtrabajos> findRange(int[] range);

    int count();
    
    public Collection<CauSolicitudtrabajos> Consulta_Solicitudestrabajo(String consulta1, int Nocredencial);
    
    public Collection<CauSolicitudtrabajos> Consulta_Solicitudestrabajo2(String consulta1, Date FechaInicial, Date FechaFin);
    
    public int Consulta_TipoUsuario(String consulta1, String Nocredencial);
    
    public String  ObtenFiltro(String usuario, int transaccion, int niveCons);
    
    public String  ObtenFiltro1(String usuario, int transaccion, int niveCons);
    
    public String  ObtenFiltro2(String usuario, int transaccion, int niveCons);
}
