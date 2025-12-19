package com.solucionesweb.lasayudas;

import java.util.*;
import java.net.*;
import java.io.*;

import org.kxml.parser.*;


import com.solucionesweb.losbeans.negocio.LineaBean;

/**
 *
 * @author  Noé Herrera
 */
public class CorporativoToLocales {


    // xmlByteArray
    byte[]  xmlByteArray           = null;
    ByteArrayInputStream xmlStream = null;
    InputStreamReader xmlReader    = null;
    XmlParser parser               = null;

    // CorporativoToLocales
    public CorporativoToLocales() { }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        new CorporativoToLocales().Arrancar();
    }

    // Ejecuta una función periódicamente
    public void Arrancar() {

    java.util.Timer timer = new java.util.Timer();

    // creamos una clase inline
    timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                EjecutarTarea();
           }
        }, retardo, periodo);
   }

    // EjecutarTarea
    public void EjecutarTarea() {

       try {

           // lineaBean
           LineaBean lineaBean = new LineaBean();
           int maxIdSeqLocal     = lineaBean.listaIdSeqMaxima();

           String strIdSeq       = "?idSeq=" + new Integer(maxIdSeqLocal).toString();
           String xPagina        = "../GralControladorServlet?nombrePaginaRequest=/jsp/txNovedadLocalLineas.jsp" ;

           //
           String xmlString = enviar( xPagina + strIdSeq );
           destino          = null;

           // xmlString
		   xmlByteArray     = xmlString.getBytes();
		   xmlStream        = new ByteArrayInputStream(xmlByteArray);
		   xmlReader        = new InputStreamReader(xmlStream);
           XmlParser parser = new XmlParser( xmlReader );

           //
           lineaBean.traverse_LineaTx( parser , "" );


           System.out.println(" ----------------- "  );


       } catch (Exception exc)  {
       	 exc.printStackTrace();
       }
	}

    // la tarea a ejecutar es independiente del interfaz de usuario
    public String enviar(String xPagina) {

       try
       {
           // inicializamos el destino si es necesario
           if(destino == null)
           {

             String ipNoe            = "http://201.232.212.14:8000/Commerce/";
             String ipPlasticauca    = "http://200.35.35.11:8000/Corporativo/";
             String SERVLET_URL      = "http://localhost:8000/Corporativo/";
             String url              = ipNoe + xPagina ;

             System.out.println(" url " + url );

             destino = new URL(url);
           }

           // abrimos la conexión al servidor
           HttpURLConnection enlace = (HttpURLConnection)destino.openConnection();
           String contenido = recuperaContenido(enlace);
           enlace.disconnect();
           return contenido;

       }
       catch(Exception e)
       {
            return "Error " + e.getMessage() + "\n";
       }

    }

    // esta función recupera el texto de la página
    String recuperaContenido(HttpURLConnection enlace)
    {
      try
       {
           String respuesta = "";
           BufferedReader in = new BufferedReader (new InputStreamReader(enlace.getInputStream()));

           String cadena = "";

	   while (cadena != null)
	   {
		cadena = in.readLine();
		if (cadena != null)
		{
                	respuesta+=("\n" + cadena);
		}
            }

           in.close();
           return respuesta;

       }
       catch(Exception e)
       {
            return "Error " + e.getMessage() + "\n";
       }
    }

    // inicializamos las variables no gestionadas por el compilador NetBeans 3.6
    private URL destino = null;

    private int retardo = 0;   // no esperar
//  private int periodo =  2 * 60 * 1000;  // ejecutar cada 2 minutos
    private int periodo =  5000;

}