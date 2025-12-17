package com.solucionesweb.losservlets;

import javax.servlet.ServletContextListener;
import javax.servlet.ServletContextEvent;

import java.net.*;
import java.io.*;
import java.util.*;

//
import org.kxml.parser.XmlParser;

//
import com.solucionesweb.losbeans.negocio.LineaBean;

//
import com.solucionesweb.losbeans.negocio.CategoriaBean;

//
import com.solucionesweb.losbeans.negocio.MarcaBean;

//
import com.solucionesweb.losbeans.negocio.PluBean;

//
import com.solucionesweb.losbeans.negocio.PluInventarioBean;

//
import com.solucionesweb.losbeans.negocio.UsuarioBean;

//
import com.solucionesweb.losbeans.negocio.UsuarioRutaBean;

//
import com.solucionesweb.losbeans.negocio.LocalBean;

//
import com.solucionesweb.losbeans.fachada.FachadaLocalBean;

public class GralServletNovedad extends TimerTask implements ServletContextListener {

    //
    private Timer timer;

    // xmlByteArray
    byte[]  xmlByteArray           = null;
    ByteArrayInputStream xmlStream = null;
    InputStreamReader xmlReader    = null;

    // PLASTICAUCA
    int xIdLocalBase               = 12;
    
    // CEDIPLAST
    //int xIdLocalBase               = 51;

    //
    String xPrefijo                = ":8600/Commerce/jsp/";


    // inicializamos las variables no gestionadas por el compilador NetBeans 3.6
    private URL destino            = null;


    public void contextInitialized(ServletContextEvent evt) {

        // Iniciamos el timer
        int  numeroMinutos         = 1;

        //
        timer                      = new Timer();

        //
        timer.schedule(this, 0, numeroMinutos * 60 * 1000 );  // Ejemplo: Cada numeroMinutos

    }

    public void contextDestroyed(ServletContextEvent evt) {
        timer.cancel();
    }

    public void run() {

       try {

           //
           FachadaLocalBean fachadaLocalBean 
                               = new FachadaLocalBean();

           //
           LocalBean  localBean= new LocalBean();

           localBean.setIdLocal(xIdLocalBase);

           //
           fachadaLocalBean    = localBean.listaUnLocal();

           String xIpLocalBase = fachadaLocalBean.getIpLocal();

           // --------- lineaBean
           LineaBean lineaBean = new LineaBean();

           //
           int maxIdSeqLocal     = lineaBean.listaIdSeqMaxima();

           String strIdSeq       =
                              "?idSeq=" + new Integer(maxIdSeqLocal).toString();

           //
           String xPagina        = "txNovedadLocalLineas.jsp" ;

           //
           String xmlString      = enviar( xPagina + strIdSeq,
                                           xIpLocalBase);
           destino               = null;

           // xmlString
		   xmlByteArray          = xmlString.getBytes();
		   xmlStream             = new ByteArrayInputStream(xmlByteArray);
		   xmlReader             = new InputStreamReader(xmlStream);
           XmlParser parser      = new XmlParser( xmlReader );

           //
           lineaBean.traverse_LineaTx( parser , "" );

           // --------- categoriaBean
           CategoriaBean categoriaBean = new CategoriaBean();

           //
           maxIdSeqLocal         = categoriaBean.listaIdSeqMaxima();

                  strIdSeq       =
                              "?idSeq=" + new Integer(maxIdSeqLocal).toString();

           //
                  xPagina        = "txNovedadLocalCategorias.jsp" ;

           //
                  xmlString      = enviar( xPagina + strIdSeq,
                                           xIpLocalBase);
           destino               = null;

           // xmlString
		   xmlByteArray          = xmlString.getBytes();
		   xmlStream             = new ByteArrayInputStream(xmlByteArray);
		   xmlReader             = new InputStreamReader(xmlStream);
                     parser      = new XmlParser( xmlReader );

           //
           categoriaBean.traverse_CategoriaTx(parser, "");

           // --------- marcaBean
           MarcaBean marcaBean   = new MarcaBean();

           //
           maxIdSeqLocal         = marcaBean.listaIdSeqMaxima();

                  strIdSeq       =
                              "?idSeq=" + new Integer(maxIdSeqLocal).toString();

           //
                  xPagina        = "txNovedadLocalMarcas.jsp" ;

           //
                  xmlString      = enviar( xPagina + strIdSeq,
                                           xIpLocalBase);
           destino               = null;

           // xmlString
		   xmlByteArray          = xmlString.getBytes();
		   xmlStream             = new ByteArrayInputStream(xmlByteArray);
		   xmlReader             = new InputStreamReader(xmlStream);
                     parser      = new XmlParser( xmlReader );

           //
           marcaBean.traverse_MarcaTx(parser, "");

           // --------- pluBean
           PluBean pluBean       = new PluBean();

           //
           maxIdSeqLocal         = pluBean.listaIdSeqMaxima();

                  strIdSeq       =
                              "?idSeq=" + new Integer(maxIdSeqLocal).toString();

           //
                  xPagina        = "txNovedadLocalPlus.jsp" ;

           //
                  xmlString      = enviar( xPagina + strIdSeq,
                                           xIpLocalBase);
           destino               = null;

           // xmlString
		   xmlByteArray          = xmlString.getBytes();
		   xmlStream             = new ByteArrayInputStream(xmlByteArray);
		   xmlReader             = new InputStreamReader(xmlStream);
                     parser              = new XmlParser( xmlReader );

           //
           pluBean.traverse_PluTx(parser, "");
           
           // --------- pluInventarioBean
           PluInventarioBean pluInventarioBean = new PluInventarioBean();

           //
           pluInventarioBean.ingresaBodega();

           //---
           UsuarioBean usuarioBean
                                 = new UsuarioBean();

           //
           maxIdSeqLocal         = usuarioBean.listaIdSeqMaxima();

                  strIdSeq       =
                              "?idSeq=" + new Integer(maxIdSeqLocal).toString();

           //
                  xPagina        = "txNovedadLocalUsuario.jsp" ;

           //
                  xmlString      = enviar( xPagina + strIdSeq,
                                           xIpLocalBase);

           destino               = null;

           // xmlString
		   xmlByteArray          = xmlString.getBytes();
		   xmlStream             = new ByteArrayInputStream(xmlByteArray);
		   xmlReader             = new InputStreamReader(xmlStream);
                     parser      = new XmlParser( xmlReader );

           //
           usuarioBean.traverse_UsuarioTx(parser, "");

           // actualizaSeguridad
           usuarioBean.actualizaSeguridad();

           //---
           UsuarioRutaBean usuarioRutaBean
                                 = new UsuarioRutaBean();

           //
           maxIdSeqLocal         = usuarioRutaBean.listaIdSeqMaxima();

           //
                  strIdSeq       =
                              "?idSeq=" + new Integer(maxIdSeqLocal).toString();

           //
                  xPagina        = "txNovedadLocalUsuarioRuta.jsp" ;

           //
                  xmlString      = enviar( xPagina + strIdSeq,
                                           xIpLocalBase);

           destino               = null;

           // xmlString
		   xmlByteArray          = xmlString.getBytes();
		   xmlStream             = new ByteArrayInputStream(xmlByteArray);
		   xmlReader             = new InputStreamReader(xmlStream);
                     parser      = new XmlParser( xmlReader );

           //
           usuarioRutaBean.traverse_UsuarioRutaTx(parser, "");
                  
           System.out.println(" ----------------- "  );

       } catch (Exception exc)  {
       	 exc.printStackTrace();
       }
    }

    // la tarea a ejecutar es independiente del interfaz de usuario
    public String enviar(String xPagina,
                         String xIpLocalBase) {

       try
       {
           // inicializamos el destino si es necesario
           if(destino == null)
           {

             String SERVLET_URL      = "http://"    +
                                       xIpLocalBase + xPrefijo ;
             String url              = SERVLET_URL + xPagina ;

             System.out.println(" url " + url );

             destino = new URL(url);
           }

           // abrimos la conexión al servidor
           HttpURLConnection enlace = (
                                     HttpURLConnection)destino.openConnection();

           //
           String contenido = recuperaContenido(enlace);

           //
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

}