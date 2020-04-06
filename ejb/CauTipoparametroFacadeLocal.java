/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfp.ejb;

import com.sfp.model.CauTipoparametro;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jleal
 */
@Local
public interface CauTipoparametroFacadeLocal {

    void create(CauTipoparametro cauTipoparametro);

    void edit(CauTipoparametro cauTipoparametro);

    void remove(CauTipoparametro cauTipoparametro);

    CauTipoparametro find(Object id);

    List<CauTipoparametro> findAll();

    List<CauTipoparametro> findRange(int[] range);

    int count();
    
}
