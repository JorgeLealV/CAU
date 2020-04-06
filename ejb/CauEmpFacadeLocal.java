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
import javax.ejb.Local;

/**
 *
 * @author jleal
 */
@Local
public interface CauEmpFacadeLocal {

    void create(CauEmp cauEmp);

    void edit(CauEmp cauEmp);

    void remove(CauEmp cauEmp);

    CauEmp find(Object id);

    List<CauEmp> findAll();

    List<CauEmp> findRange(int[] range);

    int count();

    public CauEmp iniciarSessionEmpleado(CauUsuarios usuario);
    
    public Collection<CauEmp> Consulta_TipTrabajo(String consulta1, int Nocredencial);
    
    public CauEmp ConsultaEmp(long Credencial);
}
