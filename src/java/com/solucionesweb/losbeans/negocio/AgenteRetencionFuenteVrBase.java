package com.solucionesweb.losbeans.negocio;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaAgenteRteFuenteVrBase;

// Importa los paquetes de java
import java.io.*;
import java.util.*;
import java.io.IOException.*;

public class AgenteRetencionFuenteVrBase extends FachadaAgenteRteFuenteVrBase
                                                      implements Serializable {

    // Metodo constructor por defecto sin parametros
    public AgenteRetencionFuenteVrBase() { }

    // Consulta que devuelve el id e instruccion de destinacion
    public Vector lista() {

      //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
	  Class tipoObjeto   = this.getClass();

      //
      String nombreClase = tipoObjeto.getName();

      //
      Vector contenedor  = new Vector();

      // Variable de fachada de los datos
      FachadaAgenteRteFuenteVrBase fachadaBean;

     // llena el vector con las posibles destinaciones del reporte
     try {

       //
       fachadaBean = new FachadaAgenteRteFuenteVrBase();

       //
       fachadaBean.setIdRteFuenteVrBase("1");
       fachadaBean.setNombreRteFuenteVrBase("VR.COMPLETO");
       contenedor.add(fachadaBean);

       //
       fachadaBean = new FachadaAgenteRteFuenteVrBase();

       //
       fachadaBean.setIdRteFuenteVrBase("0");
       fachadaBean.setNombreRteFuenteVrBase("VR.TOPE");
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
    public Vector listaUn(int xIdRteFuenteVrBase) {

      //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
	  Class tipoObjeto     = this.getClass();

      //
      String nombreClase   = tipoObjeto.getName();

      //
      Vector contenedor    = new Vector();

      // Variable de fachada de los datos
      FachadaAgenteRteFuenteVrBase fachadaBean;

      //
      Vector contenedorOut = new Vector();

      // Variable de fachada de los datos
      FachadaAgenteRteFuenteVrBase fachadaBeanOut;

     // llena el vector con las posibles destinaciones del reporte
     try {

       //
       fachadaBean = new FachadaAgenteRteFuenteVrBase();

       //
       fachadaBean.setIdRteFuenteVrBase("1");
       fachadaBean.setNombreRteFuenteVrBase("VR.COMPLETO");
       contenedor.add(fachadaBean);

       //
       fachadaBean = new FachadaAgenteRteFuenteVrBase();

       //
       fachadaBean.setIdRteFuenteVrBase("0");
       fachadaBean.setNombreRteFuenteVrBase("VR.TOPE");
       contenedor.add(fachadaBean);

       //
       contenedor.trimToSize();

       // adiciona igual
       for(int i=0; i<contenedor.size(); i++){

            //
            fachadaBean = (FachadaAgenteRteFuenteVrBase)contenedor.elementAt(i);

            //
            if (fachadaBean.getIdRteFuenteVrBase()==xIdRteFuenteVrBase) {

               //
               fachadaBeanOut = new FachadaAgenteRteFuenteVrBase();

               //
               fachadaBeanOut.setIdRteFuenteVrBase(
                                              fachadaBean.getIdRteFuenteVrBase());
               fachadaBeanOut.setNombreRteFuenteVrBase(
                                          fachadaBean.getNombreRteFuenteVrBase());
               contenedorOut.add(fachadaBeanOut);
               break;
            }
       }

       // adiciona diferentes
       for(int i=0; i<contenedor.size(); i++){

            //
            fachadaBean = (FachadaAgenteRteFuenteVrBase)contenedor.elementAt(i);

            //
            if (fachadaBean.getIdRteFuenteVrBase()!=xIdRteFuenteVrBase) {

               //
               fachadaBeanOut = new FachadaAgenteRteFuenteVrBase();

               //
               fachadaBeanOut.setIdRteFuenteVrBase(
                                              fachadaBean.getIdRteFuenteVrBase());
               fachadaBeanOut.setNombreRteFuenteVrBase(
                                          fachadaBean.getNombreRteFuenteVrBase());
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
