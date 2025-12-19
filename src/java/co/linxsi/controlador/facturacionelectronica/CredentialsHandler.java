/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.linxsi.controlador.facturacionelectronica;

import java.util.Collections;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFactory;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPHeaderElement;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

/**
 *
 * @author foqc
 */
public class CredentialsHandler implements SOAPHandler<SOAPMessageContext> {

    @Override
    public Set<QName> getHeaders() {
        return Collections.EMPTY_SET;
    }

    @Override
    public boolean handleMessage(SOAPMessageContext messageContext) {
        try {
            SOAPMessage msg = messageContext.getMessage();

            boolean bolMsgSalida = (Boolean) messageContext.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
            // Obtenemos el contenedor del mensaje SOAP
            SOAPPart sp = msg.getSOAPPart();
            // A partir del contendor, obtenemos el nodo "Envelope"
            SOAPEnvelope env = sp.getEnvelope();
            if (env.getHeader() != null) {
                env.getHeader().detachNode();
            }

            // Instanciamos un objeto SOAPFactory para crear cualquier elemento perteneciente a un mensaje SOAP, en nuestro caso, los nodos que formarán la cabecera
            SOAPFactory soapFactory = SOAPFactory.newInstance();

//          Definimos los elementos a incluir en el mensaje para enviarlos al servidor que ofrece el servicio web
            SOAPElement soapElementoCabecera = soapFactory.createElement("Authorization", "", "http://tempuri.org/");
//            SOAPElement soapElementoCabecera = soapFactory.createElement("credentials", "", "http://paginaWebQueOfreceServicioWeb.com/");

            SOAPElement soapUsername = soapFactory.createElement("Username", "", "http://tempuri.org/");
            SOAPElement soapPassword = soapFactory.createElement("Password", "", "http://tempuri.org/");
            SOAPElement soapTipoAutenticacion = soapFactory.createElement("Authentication", "", "http://tempuri.org/");

//          Rellenamos la información del nodo credencial
            soapUsername.addTextNode("1DCD171F741313781C");
            soapPassword.addTextNode("1DCD171F741313781C");
            soapTipoAutenticacion.addTextNode("Preemptive");

//          Incluimos los elementos dentro de los objetos correspondientes
            soapElementoCabecera.addChildElement(soapUsername);
            soapElementoCabecera.addChildElement(soapPassword);
            soapElementoCabecera.addChildElement(soapTipoAutenticacion);

            SOAPHeader soapHeader = env.addHeader(); // Crea un elemento cabecera SOAP

            soapHeader.addChildElement(soapElementoCabecera);
            if (bolMsgSalida) {
                try {
                    //get the soap message and envelope
                    SOAPMessage soapMsg = messageContext.getMessage();
                    soapMsg.getSOAPPart().getEnvelope().setPrefix("s");
                    soapMsg.getSOAPBody().setPrefix("s");
                    soapMsg.getSOAPHeader().setPrefix("s");

                    soapMsg.getSOAPPart().getEnvelope().setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
                    soapMsg.getSOAPPart().getEnvelope().removeAttributeNS("http://schemas.xmlsoap.org/soap/envelope/", "S");
                    soapMsg.getSOAPPart().getEnvelope().setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:xsd", "http://www.w3.org/2001/XMLSchema");
                    soapMsg.getSOAPPart().getEnvelope().removeAttributeNS("http://schemas.xmlsoap.org/soap/envelope/", "S");
                    soapMsg.getSOAPPart().getEnvelope().setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:soap", "http://schemas.xmlsoap.org/soap/envelope/");
                    soapMsg.getSOAPPart().getEnvelope().removeAttribute("xmlns:S");
                    soapMsg.getSOAPPart().getEnvelope().removeAttribute("xmlns:SOAP-ENV");
                } catch (SOAPException e) {
                    e.printStackTrace();
                }
            }
            
            msg.setProperty(SOAPMessage.WRITE_XML_DECLARATION, Boolean.TRUE.toString());
            msg.saveChanges();
        } catch (SOAPException ex) {
            Logger.getLogger(CredentialsHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    @Override
    public boolean handleFault(SOAPMessageContext context) {
        return true;
    }

    @Override
    public void close(MessageContext context) {

    }

}
