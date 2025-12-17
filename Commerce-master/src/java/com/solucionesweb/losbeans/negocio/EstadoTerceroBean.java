package com.solucionesweb.losbeans.negocio;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaEstadoTerceroBean;

// Importa los paquetes de java
import java.io.*;
import java.util.*;
import java.io.IOException.*;

public class EstadoTerceroBean extends FachadaEstadoTerceroBean
                                                      implements Serializable {

    // Metodo constructor por defecto sin parametros
    public EstadoTerceroBean() { }

    // Consulta que devuelve el id e instruccion de destinacion
    public Vector lista() {

      //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
	  Class tipoObjeto   = this.getClass();

      //
      String nombreClase = tipoObjeto.getName();

      //
      Vector contenedor  = new Vector();

      // Variable de fachada de los datos
      FachadaEstadoTerceroBean fachadaBean;

     // llena el vector con las posibles destinaciones del reporte
     try {

       //
       fachadaBean = new FachadaEstadoTerceroBean();

       //
       fachadaBean.setEstado("1");
       fachadaBean.setNombreEstado("ACTIVO");
       contenedor.add(fachadaBean);

       //
       fachadaBean = new FachadaEstadoTerceroBean();

       //
       fachadaBean.setEstado("2");
       fachadaBean.setNombreEstado("INACTIVO");
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
    public Vector listaUn(int xEstado) {

      //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
	  Class tipoObjeto     = this.getClass();

      //
      String nombreClase   = tipoObjeto.getName();

      //
      Vector contenedor    = new Vector();

      // Variable de fachada de los datos
      FachadaEstadoTerceroBean fachadaBean;

      //
      Vector contenedorOut = new Vector();

      // Variable de fachada de los datos
      FachadaEstadoTerceroBean fachadaBeanOut;

     // llena el vector con las posibles destinaciones del reporte
     try {

       //
       fachadaBean = new FachadaEstadoTerceroBean();

       //
       fachadaBean.setEstado("1");
       fachadaBean.setNombreEstado("ACTIVO");
       contenedor.add(fachadaBean);

       //
       fachadaBean = new FachadaEstadoTerceroBean();

       //
       fachadaBean.setEstado("2");
       fachadaBean.setNombreEstado("INACTIVO");
       contenedor.add(fachadaBean);

       //
       contenedor.trimToSize();

       // adiciona igual
       for(int i=0; i<contenedor.size(); i++){

            //
            fachadaBean = (FachadaEstadoTerceroBean)contenedor.elementAt(i);

            //
            if (fachadaBean.getEstado()==xEstado) {

               //
               fachadaBeanOut = new FachadaEstadoTerceroBean();

               //
               fachadaBeanOut.setEstado(fachadaBean.getEstado());
               fachadaBeanOut.setNombreEstado(fachadaBean.getNombreEstado());
               contenedorOut.add(fachadaBeanOut);
               break;
            }
       }

       // adiciona diferentes
       for(int i=0; i<contenedor.size(); i++){

            //
            fachadaBean = (FachadaEstadoTerceroBean)contenedor.elementAt(i);

            //
            if (fachadaBean.getEstado()!=xEstado) {

               //
               fachadaBeanOut = new FachadaEstadoTerceroBean();

               //
               fachadaBeanOut.setEstado(fachadaBean.getEstado());
               fachadaBeanOut.setNombreEstado(fachadaBean.getNombreEstado());
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
