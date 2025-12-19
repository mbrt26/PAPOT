package com.solucionesweb.losbeans.negocio;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaAgenteRetencionIva;

// Importa los paquetes de java
import java.io.*;
import java.util.*;
import java.io.IOException.*;

public class AgenteRetencionIvaBean extends FachadaAgenteRetencionIva
                                                      implements Serializable {

    // Metodo constructor por defecto sin parametros
    public AgenteRetencionIvaBean() { }

    // Consulta que devuelve el id e instruccion de destinacion
    public Vector lista() {

      //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
	  Class tipoObjeto   = this.getClass();

      //
      String nombreClase = tipoObjeto.getName();

      //
      Vector contenedor  = new Vector();

      // Variable de fachada de los datos
      FachadaAgenteRetencionIva fachadaBean;

     // llena el vector con las posibles destinaciones del reporte
     try {

       //
       fachadaBean = new FachadaAgenteRetencionIva();

       //
       fachadaBean.setIdRteIva("1");
       fachadaBean.setNombreRteIva("NO");
       contenedor.add(fachadaBean);

       //
       fachadaBean = new FachadaAgenteRetencionIva();

       //
       fachadaBean.setIdRteIva("0");
       fachadaBean.setNombreRteIva("SI");
       contenedor.add(fachadaBean);

       //
       contenedor.trimToSize();

     }
     catch (Exception e) {
		System.out.println("Exception In :" + nombreClase + " " + e);
	 }
     finally {
        // Cierra la conexion a la base de datos limpiando la statement usada y la
        // conexion
       return contenedor;
     }
    }

    // Consulta que devuelve el id e instruccion de destinacion
    public Vector listaUn(int xIdRteIva) {

      //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
	  Class tipoObjeto     = this.getClass();

      //
      String nombreClase   = tipoObjeto.getName();

      //
      Vector contenedor    = new Vector();

      // Variable de fachada de los datos
      FachadaAgenteRetencionIva fachadaBean;

      //
      Vector contenedorOut = new Vector();

      // Variable de fachada de los datos
      FachadaAgenteRetencionIva fachadaBeanOut;

     // llena el vector con las posibles destinaciones del reporte
     try {

       //
       fachadaBean = new FachadaAgenteRetencionIva();

       //
       fachadaBean.setIdRteIva("1");
       fachadaBean.setNombreRteIva("NO");
       contenedor.add(fachadaBean);

       //
       fachadaBean = new FachadaAgenteRetencionIva();

       //
       fachadaBean.setIdRteIva("0");
       fachadaBean.setNombreRteIva("SI");
       contenedor.add(fachadaBean);

       //
       contenedor.trimToSize();

       // adiciona igual
       for(int i=0; i<contenedor.size(); i++){

            //
            fachadaBean = (FachadaAgenteRetencionIva)contenedor.elementAt(i);

            //
            if (fachadaBean.getIdRteIva()==xIdRteIva) {

               //
               fachadaBeanOut = new FachadaAgenteRetencionIva();

               //
               fachadaBeanOut.setIdRteIva(
                                              fachadaBean.getIdRteIva());
               fachadaBeanOut.setNombreRteIva(
                                          fachadaBean.getNombreRteIva());
               contenedorOut.add(fachadaBeanOut);
               break;
            }
       }

       // adiciona diferentes
       for(int i=0; i<contenedor.size(); i++){

            //
            fachadaBean = (FachadaAgenteRetencionIva)contenedor.elementAt(i);

            //
            if (fachadaBean.getIdRteIva()!=xIdRteIva) {

               //
               fachadaBeanOut = new FachadaAgenteRetencionIva();

               //
               fachadaBeanOut.setIdRteIva(
                                              fachadaBean.getIdRteIva());
               fachadaBeanOut.setNombreRteIva(
                                          fachadaBean.getNombreRteIva());
               contenedorOut.add(fachadaBeanOut);

            }
       }

       //
       contenedorOut.trimToSize();

	}

     catch (Exception e) {
		System.out.println("Exception In :" + nombreClase + " " + e);
	 }
     finally {
        // Cierra la conexion a la base de datos limpiando la statement usada y la
        // conexion
       return contenedorOut;
     }
    }
}
