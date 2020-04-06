/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfp.lib01;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import javax.naming.NamingException;

/**
 *
 * @author cgarias
 */
@Stateless
public class ServiceMail implements ServiceMailInterface{
    
    private static final Logger logger = Logger.getLogger(ServiceMail.class.getName());
    
    @Resource(name = "java:/CAUM")
    private Session mail;
    
    @Override
    public void enviaContentText(String email, List<String> copias, List<String> copiasocultas, String subject, String body, DTODocumento documento) throws NamingException, IOException {
        this.envia(email, copias, copiasocultas, "", subject, body, documento);
    }

    @Override
    public void enviaContentHtml(String email, List<String> copias, List<String> copiasocultas, String subject, String body, DTODocumento documento) throws NamingException,  IOException {
        this.envia(email, copias, copiasocultas, "html", subject, body, documento);
    }

    private void envia(String email, List<String> copias, List<String> copiasocultas, 
                       String tipo, String subject, String body, DTODocumento documento) throws NamingException, IOException {
        MimeMessage message = new MimeMessage(mail);
        try {
        
            if (email!= null){
                String mails = email;
                if("".equals(email)){
                    mails = ADMIN_MAIL_OMEXT;
                }
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mails, false));

            }
            if (copias != null && !copias.isEmpty()) {
                StringBuilder copiass = new StringBuilder();
                for (String direc : copias) {
                    copiass.append(direc).append(",");
                }
                copiass.delete(copiass.length() - 1, copiass.length());
                message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(copiass.toString(), true));
            }
            if (copiasocultas != null && !copiasocultas.isEmpty()) {
                StringBuilder copiassoc = new StringBuilder();
                for (String direc : copiasocultas) {
                    copiassoc.append(direc).append(",");
                }
                copiassoc.delete(copiassoc.length()-1, copiassoc.length());
                message.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(copiassoc.toString(), false));

            }
            message.setSubject(subject);

            BodyPart messageBodyPart = new MimeBodyPart();
            if ("html".equals(tipo))
                messageBodyPart.setContent(body, "text/html");
            else
                messageBodyPart.setText(body);

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);

            if (documento != null){    
                messageBodyPart = new MimeBodyPart();
                DataSource ds = new ByteArrayDataSource(documento.getContenido(), "application/octet-stream");
                messageBodyPart.setFileName(documento.getDocNombreOrig());
                messageBodyPart.setDataHandler(new DataHandler(ds));
                multipart.addBodyPart(messageBodyPart);
            }
            message.setContent(multipart);
            logger.log(Level.INFO, "Antes Envia email, destinatario: {0} , asunto: {1}", new Object[]{email, subject});     
            Transport.send(message);
            logger.log(Level.INFO, "Despues Envia email, destinatario: {0} , asunto: {1}", new Object[]{email, subject}); 
        } catch (MessagingException ex) {
            logger.log(Level.INFO, "ERROR en ServiceMail, envia MessagingException: {0}", ex);
        }
    }
}
