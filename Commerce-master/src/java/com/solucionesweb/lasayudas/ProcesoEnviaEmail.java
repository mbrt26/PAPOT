package com.solucionesweb.lasayudas;

// Importa los paquetes del lenguaje especificamente io y los
import java.util.*;

import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

// Este servlet implementa la interface GralManejadorRequest
public class ProcesoEnviaEmail {

  // Metodo contructor por defecto, es decir, sin parametros
  public ProcesoEnviaEmail () { }

       public static void enviarEmail( String fileName,
                                       String strEmailCliente,
                                       String strEmailVendedor,
                                       String strEmailDirector,
                                       String strNumeroOrden,
                                       String strFechaOrden) {
            //
            System.getProperties().put("proxySet","false");
            System.getProperties().put("socksProxyPort","");
            System.getProperties().put("proxyHost", "");
            System.getProperties().put("proxyHost", "");

            /*
            System.getProperties().put("proxySet","true");
            System.getProperties().put("socksProxyPort","1080");
            //System.getProperties().put("socksProxyHost","192.168.155.1");
            System.getProperties().put("proxyHost", "192.168.20.2");
            System.getProperties().put("proxyHost", "FWCAMEJIA");*/

            // Get system properties
            Properties props = System.getProperties();

            //
            String to        = strEmailCliente ;
            String toCC      = strEmailVendedor;       // futuro email cotizacionesBB@ferrasa.com.co
            String toBCC     = strEmailDirector;        // futuro email noeherrera@une.net.co

            //
            String from      = strEmailVendedor;       // futuro email vendedorFerrasa

            //
            String host      = "smtpmed.une.net.co";
	        String subject   = "CA MEJIA & CIA S.A. ORDEN DE VENTA # " + strNumeroOrden ;

            // Get system properties
            final String username = "conector1";
            final String password = "conector1";

            //
            props.put("mail.user", username);
            props.put("mail.host", host);
            props.put("mail.debug", "true");
            props.put("mail.store.protocol", "pop3");
            props.put("mail.transport.protocol", "smtp");

            //
            String strTextoLegal = " COMENTARIOS\n " +
                                   " Su pedido se encuentra en archivo adjunto                                       \n " +
                                   " Si tiene alguna duda o inconformidad por favor comunicarse con nuestra empresa, \n " +
                                   " Tel (57-4) 444-67-67, Fax. (57-4)266-42-82, email info@camejia.com              \n " +
                                   " pasadas algunas horas nuestra empresa dar� por aceptado el pedido y proceder� a \n " +
                                   " hacer el despacho y factura del mismo.                                          \n " ;

            // Setup mail server
            props.put("mail.host", host);
            props.put("mail.debug", "true");
            props.put("mail.store.protocol", "pop3");
            props.put("mail.transport.protocol", "smtp");

            //
            props.put ( "mail.smtp.auth", "true");

            // Get session
            Session session = Session.getDefaultInstance(props,
                              new Authenticator(){
                                 protected PasswordAuthentication getPasswordAuthentication() {
                                    return new PasswordAuthentication(username, password);
                                 }});

           	try {

               // Define message
               Message message = new MimeMessage(session);
               message.setFrom(new InternetAddress(from));
               message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
               //message.addRecipient(Message.RecipientType.CC, new InternetAddress(toCC));
               message.addRecipient(Message.RecipientType.BCC, new InternetAddress(toBCC));

               //
               message.setSubject(subject);

               // Create the message part
               BodyPart messageBodyPart = new MimeBodyPart();

               // Fill the message
               messageBodyPart.setText(strTextoLegal);

               // Create a Multipart
               Multipart multipart = new MimeMultipart();

               // Add part one
               multipart.addBodyPart(messageBodyPart);

               // Part two is attachment // // Create second body part
               messageBodyPart = new MimeBodyPart();

               // Get the attachment
               DataSource source = new FileDataSource(fileName);

               // Set the data handler to the attachment
               messageBodyPart.setDataHandler(new DataHandler(source));

               // Set the filename
               messageBodyPart.setFileName(fileName);

               // Add part two
               multipart.addBodyPart(messageBodyPart);

               // Put parts in message
               message.setContent(multipart);

               // Send the message
               Transport.send(message);

	        } catch (MessagingException mex) {
	          mex.printStackTrace();
	          Exception ex = null;
	          if ((ex = mex.getNextException()) != null) {
		         ex.printStackTrace();
	          }
	        }
       }
}