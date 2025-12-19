package com.solucionesweb.losbeans.negocio;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaAgenteRetencionIvaVrBase;

// Importa los paquetes de java
import java.io.*;
import java.util.*;
import java.io.IOException.*;

public class AgenteRetencionIvaVrBase extends FachadaAgenteRetencionIvaVrBase
                                                      implements Serializable {

    // Metodo constructor por defecto sin parametros
    public AgenteRetencionIvaVrBase() { }

    // Consulta que devuelve el id e instruccion de destinacion
    public Vector lista() {

      //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
	  Class tipoObjeto   = this.getClass();

      //
      String nombreClase = tipoObjeto.getName();

      //
      Vector contenedor  = new Vector();

      // Variable de fachada de los datos
      FachadaAgenteRetencionIvaVrBase fachadaBean;

     // llena el vector con las posibles destinaciones del reporte
     try {

       //
       fachadaBean = new FachadaAgenteRetencionIvaVrBase();

       //
       fachadaBean.setIdRteIvaVrBase("1");
       fachadaBean.setNombreRteIvaVrBase("VR.COMPLETO");
       contenedor.add(fachadaBean);

       //
       fachadaBean = new FachadaAgenteRetencionIvaVrBase();

       //
       fachadaBean.setIdRteIvaVrBase("0");
       fachadaBean.setNombreRteIvaVrBase("VR.TOPE");
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
    public Vector listaUn(int xIdRteIvaVrBase) {

      //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
	  Class tipoObjeto     = this.getClass();

      //
      String nombreClase   = tipoObjeto.getName();

      //
      Vector contenedor    = new Vector();

      // Variable de fachada de los datos
      FachadaAgenteRetencionIvaVrBase fachadaBean;

      //
      Vector contenedorOut = new Vector();

      // Variable de fachada de los datos
      FachadaAgenteRetencionIvaVrBase fachadaBeanOut;

     // llena el vector con las posibles destinaciones del reporte
     try {

       //
       fachadaBean = new FachadaAgenteRetencionIvaVrBase();

       //
       fachadaBean.setIdRteIvaVrBase("1");
       fachadaBean.setNombreRteIvaVrBase("VR.COMPLETO");
       contenedor.add(fachadaBean);

       //
       fachadaBean = new FachadaAgenteRetencionIvaVrBase();

       //
       fachadaBean.setIdRteIvaVrBase("0");
       fachadaBean.setNombreRteIvaVrBase("VR.TOPE");
       contenedor.add(fachadaBean);

       //
       contenedor.trimToSize();

       // adiciona igual
       for(int i=0; i<contenedor.size(); i++){

            //
            fachadaBean = (FachadaAgenteRetencionIvaVrBase)contenedor.elementAt(i);

            //
            if (fachadaBean.getIdRteIvaVrBase()==xIdRteIvaVrBase) {

               //
               fachadaBeanOut = new FachadaAgenteRetencionIvaVrBase();

               //
               fachadaBeanOut.setIdRteIvaVrBase(
                                              fachadaBean.getIdRteIvaVrBase());
               fachadaBeanOut.setNombreRteIvaVrBase(
                                          fachadaBean.getNombreRteIvaVrBase());
               contenedorOut.add(fachadaBeanOut);
               break;
            }
       }

       // adiciona diferentes
       for(int i=0; i<contenedor.size(); i++){

            //
            fachadaBean = (FachadaAgenteRetencionIvaVrBase)contenedor.elementAt(i);

            //
            if (fachadaBean.getIdRteIvaVrBase()!=xIdRteIvaVrBase) {

               //
               fachadaBeanOut = new FachadaAgenteRetencionIvaVrBase();

               //
               fachadaBeanOut.setIdRteIvaVrBase(
                                              fachadaBean.getIdRteIvaVrBase());
               fachadaBeanOut.setNombreRteIvaVrBase(
                                          fachadaBean.getNombreRteIvaVrBase());
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
