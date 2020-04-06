/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfp.ejb;

import com.sfp.model.CauSolicitudtrabajos;
import com.sfp.model.CauTrabajostecnicos;
import java.util.Collection;
import java.util.List;
import javax.ejb.Local;
import java.util.Date;

/**
 *
 * @author jleal
 */
@Local
public interface CauTrabajostecnicosFacadeLocal {

    void create(CauTrabajostecnicos cauTrabajostecnicos);

    void edit(CauTrabajostecnicos cauTrabajostecnicos);

    void remove(CauTrabajostecnicos cauTrabajostecnicos);

    CauTrabajostecnicos find(Object id);

    List<CauTrabajostecnicos> findAll();

    List<CauTrabajostecnicos> findRange(int[] range);

    int count();
    
    public Collection<CauTrabajostecnicos> Consulta_TrabajosTecnico(String consulta1, int Nocredencial);
    
    public Collection<CauTrabajostecnicos> Consulta_TrabajosTecnico2(String consulta1, Date Fechainic, Date Fechafin);
}
