/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfp.ejb;

import com.sfp.model.CauSolicitudtrabajos;
import com.sfp.model.CauTectipotrab;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jleal
 */
@Local
public interface CauTectipotrabFacadeLocal {

    void create(CauTectipotrab cauTectipotrab);

    void edit(CauTectipotrab cauTectipotrab);

    void remove(CauTectipotrab cauTectipotrab);

    CauTectipotrab find(Object id);

    List<CauTectipotrab> findAll();

    List<CauTectipotrab> findRange(int[] range);

    int count();
    
    public List<CauTectipotrab> Consulta_TecTipoTrabajo(String consulta1, int Nocredencial);
    
    public List<CauTectipotrab> Consulta_TecTipoTrabajo2(String consulta1, int idTrabajo, Date fecha);
    
}
