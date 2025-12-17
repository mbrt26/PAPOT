package com.solucionesweb.losbeans.negocio;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaAgenteRetencionBean;

// Importa los paquetes de java
import java.io.*;
import java.util.*;
import java.io.IOException.*;

public class AgenteRetencionBean extends FachadaAgenteRetencionBean
                                                      implements Serializable {

    // Metodo constructor por defecto sin parametros
    public AgenteRetencionBean() { }

    // Consulta que devuelve el id e instruccion de destinacion
    public Vector lista() {

      //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
	  Class tipoObjeto   = this.getClass();

      //
      String nombreClase = tipoObjeto.getName();

      //
      Vector contenedor  = new Vector();

      // Variable de fachada de los datos
      FachadaAgenteRetencionBean fachadaBean;

     // llena el vector con las posibles destinaciones del reporte
     try {

       //
       fachadaBean = new FachadaAgenteRetencionBean();

       //
       fachadaBean.setIdAutoRetenedor("1");
       fachadaBean.setNombreAutoRetenedor("NO");
       contenedor.add(fachadaBean);

       //
       fachadaBean = new FachadaAgenteRetencionBean();

       //
       fachadaBean.setIdAutoRetenedor("0");
       fachadaBean.setNombreAutoRetenedor("SI");
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
    public Vector listaUn(int xIdAutoRetenedor) {

      //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
	  Class tipoObjeto     = this.getClass();

      //
      String nombreClase   = tipoObjeto.getName();

      //
      Vector contenedor    = new Vector();

      // Variable de fachada de los datos
      FachadaAgenteRetencionBean fachadaBean;

      //
      Vector contenedorOut = new Vector();

      // Variable de fachada de los datos
      FachadaAgenteRetencionBean fachadaBeanOut;

     // llena el vector con las posibles destinaciones del reporte
     try {

       //
       fachadaBean = new FachadaAgenteRetencionBean();

       //
       fachadaBean.setIdAutoRetenedor("1");
       fachadaBean.setNombreAutoRetenedor("NO");
       contenedor.add(fachadaBean);

       //
       fachadaBean = new FachadaAgenteRetencionBean();

       //
       fachadaBean.setIdAutoRetenedor("0");
       fachadaBean.setNombreAutoRetenedor("SI");
       contenedor.add(fachadaBean);

       //
       contenedor.trimToSize();

       // adiciona igual
       for(int i=0; i<contenedor.size(); i++){

            //
            fachadaBean = (FachadaAgenteRetencionBean)contenedor.elementAt(i);

            //
            if (fachadaBean.getIdAutoRetenedor()==xIdAutoRetenedor) {

               //
               fachadaBeanOut = new FachadaAgenteRetencionBean();

               //
               fachadaBeanOut.setIdAutoRetenedor(
                                              fachadaBean.getIdAutoRetenedor());
               fachadaBeanOut.setNombreAutoRetenedor(
                                          fachadaBean.getNombreAutoRetenedor());
               contenedorOut.add(fachadaBeanOut);
               break;
            }
       }

       // adiciona diferentes
       for(int i=0; i<contenedor.size(); i++){

            //
            fachadaBean = (FachadaAgenteRetencionBean)contenedor.elementAt(i);

            //
            if (fachadaBean.getIdAutoRetenedor()!=xIdAutoRetenedor) {

               //
               fachadaBeanOut = new FachadaAgenteRetencionBean();

               //
               fachadaBeanOut.setIdAutoRetenedor(
                                              fachadaBean.getIdAutoRetenedor());
               fachadaBeanOut.setNombreAutoRetenedor(
                                          fachadaBean.getNombreAutoRetenedor());
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
