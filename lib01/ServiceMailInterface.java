/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfp.lib01;

import java.io.IOException;
import java.util.List;
import javax.ejb.Local;
import javax.naming.NamingException;

/**
 *
 * @author cgarias
 */
@Local
public interface ServiceMailInterface {
    public static final String ADMIN_MAIL_OMEXT = "jleal@funcionpublica.gob.mx";
    public void enviaContentText(String email, List<String> copias,List<String> copiasocultas, String subject, String body, DTODocumento documento) throws NamingException, IOException;
    public void enviaContentHtml(String email, List<String> copias, List<String> copiasocultas, String subject, String body, DTODocumento documento) throws NamingException, IOException;
}
