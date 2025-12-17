package com.solucionesweb.lasayudas;

//
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.kxml.parser.XmlParser;

// Importa la clase que contiene el DctoOrdenDetalleTx

import com.solucionesweb.losbeans.negocio.DctoOrdenDetalleTrasladoTx;

//
public class ProcesoTrasladoEnviaRecibeXML extends Thread {

    //
    private String pagina;

    // xmlByteArray
    byte[] xmlByteArray = null;
    ByteArrayInputStream xmlStream = null;
    InputStreamReader xmlReader = null;

    // inicializamos las variables no gestionadas por el compilador NetBeans 3.6
    private URL destino = null;

    //
    public ProcesoTrasladoEnviaRecibeXML(String xPagina) {

       //
       this.pagina =  xPagina;


    }

    //
    public void run() {

        //
        DctoOrdenDetalleTrasladoTx  dctoOrdenDetalleTrasladoTx
                                             = new DctoOrdenDetalleTrasladoTx();

        //
        String xmlString = enviar(getPagina());



        // xmlString
        xmlByteArray = xmlString.getBytes();
        xmlStream = new ByteArrayInputStream(xmlByteArray);
        xmlReader = new InputStreamReader(xmlStream);
        try {
            //
            XmlParser parser = new XmlParser(xmlReader);

               try {

                    dctoOrdenDetalleTrasladoTx.traverse_DctoOrdenDetalleTrasladoTx(parser,"");

                } catch (Exception ex) {
                    Logger.getLogger(
                            ProcesoResurtidoEnviaRecibeXML.class.getName()).log(Level.SEVERE, null, ex);
                }

        } catch (IOException ex) {
            Logger.getLogger(
                    ProcesoResurtidoEnviaRecibeXML.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    // la tarea a ejecutar es independiente del interfaz de usuario
    public String enviar(String xPagina)  {

        try {
            // inicializamos el destino si es necesario
            if (destino == null) {

                String SERVLET_URL = "http://";

                String url = SERVLET_URL + xPagina;

                System.out.println(" url " + url);

                destino = new URL(url);
            }

            // abrimos la conexión al servidor
            HttpURLConnection enlace =
                                   (HttpURLConnection) destino.openConnection();

            //
            String contenido = recuperaContenido(enlace);

            //
            enlace.disconnect();

            //
            return contenido;

        } catch (Exception e) {
            return "Error " + e.getMessage() + "\n";
        }

    }

    // esta función recupera el texto de la página
    String recuperaContenido(HttpURLConnection enlace) {
        try {

            //
            String respuesta = "";

            //
            BufferedReader in = new BufferedReader(
                                new InputStreamReader(enlace.getInputStream()));

            String cadena = "";

            //
            while (cadena != null) {
                cadena = in.readLine();
                if (cadena != null) {
                    respuesta += ("\n" + cadena);
                }
            }

            in.close();
            return respuesta;

        } catch (Exception e) {
            return "Error " + e.getMessage() + "\n";
        }
    }

    public String getPagina() {
        return pagina;
    }

    public void setPagina(String pagina) {
        this.pagina = pagina;
    }

}
