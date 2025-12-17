package com.solucionesweb.losbeans.negocio;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaUsuarioNivel;

// Importa los paquetes de java
import java.io.*;
import java.util.*;
import java.io.IOException.*;

public class UsuarioNivelBean extends FachadaUsuarioNivel
                                                      implements Serializable {


    // Metodo constructor por defecto sin parametros
    public UsuarioNivelBean() { }

    // Consulta que devuelve el id e instruccion de destinacion
    public Vector lista() {

      //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
	  Class tipoObjeto   = this.getClass();

      //
      String nombreClase = tipoObjeto.getName();

      //
      Vector contenedor  = new Vector();

      // Variable de fachada de los datos
      FachadaUsuarioNivel fachadaBean;

     // llena el vector con las posibles destinaciones del reporte
     try {

       //
       fachadaBean = new FachadaUsuarioNivel();

       //
       fachadaBean.setIdNivel("0");
       fachadaBean.setNombreNivel("N/N");
       contenedor.add(fachadaBean);

       //
       fachadaBean = new FachadaUsuarioNivel();

       //
       fachadaBean.setIdNivel("1");
       fachadaBean.setNombreNivel("VENDEDOR ALMACEN");
       contenedor.add(fachadaBean);

       //
       fachadaBean = new FachadaUsuarioNivel();

       //
       fachadaBean.setIdNivel("2");
       fachadaBean.setNombreNivel("ADMINISTRADOR ALMACEN");
       contenedor.add(fachadaBean);

       //
       fachadaBean = new FachadaUsuarioNivel();

       //
       fachadaBean.setIdNivel("6");
       fachadaBean.setNombreNivel("CORDINADOR GENERAL");
       contenedor.add(fachadaBean);

       //
       fachadaBean = new FachadaUsuarioNivel();

       //
       fachadaBean.setIdNivel("3");
       fachadaBean.setNombreNivel("ADMNISTRADOR GENERAL");
       contenedor.add(fachadaBean);

       //
       fachadaBean = new FachadaUsuarioNivel();

       //
       fachadaBean.setIdNivel("4");
       fachadaBean.setNombreNivel("AUDITOR GENERAL");
       contenedor.add(fachadaBean);

       //
       fachadaBean = new FachadaUsuarioNivel();

       //
       fachadaBean.setIdNivel("7");
       fachadaBean.setNombreNivel("TESORERO GENERAL");
       contenedor.add(fachadaBean);
       fachadaBean = new FachadaUsuarioNivel();

       //
       fachadaBean.setIdNivel("8");
       fachadaBean.setNombreNivel("PROGRAMADOR");
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
    public Vector listaUn(int xIdNivel) {

      //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
	  Class tipoObjeto     = this.getClass();

      //
      String nombreClase   = tipoObjeto.getName();

      //
      Vector contenedor    = new Vector();

      // Variable de fachada de los datos
      FachadaUsuarioNivel fachadaBean;
      
      //
      Vector contenedorOut = new Vector();

      // Variable de fachada de los datos
      FachadaUsuarioNivel fachadaBeanOut;

     // llena el vector con las posibles destinaciones del reporte
     try {

       //
       fachadaBean = new FachadaUsuarioNivel();

       //
       fachadaBean.setIdNivel("0");
       fachadaBean.setNombreNivel("N/N");
       contenedor.add(fachadaBean);

       //
       fachadaBean = new FachadaUsuarioNivel();

       //
       fachadaBean.setIdNivel("1");
       fachadaBean.setNombreNivel("VENDEDOR ALMACEN");
       contenedor.add(fachadaBean);

       //
       fachadaBean = new FachadaUsuarioNivel();

       //
       fachadaBean.setIdNivel("2");
       fachadaBean.setNombreNivel("ADMINISTRADOR ALMACEN");
       contenedor.add(fachadaBean);

       //
       fachadaBean = new FachadaUsuarioNivel();

       //
       fachadaBean.setIdNivel("6");
       fachadaBean.setNombreNivel("CORDINADOR GENERAL");
       contenedor.add(fachadaBean);

       //
       fachadaBean = new FachadaUsuarioNivel();

       //
       fachadaBean.setIdNivel("3");
       fachadaBean.setNombreNivel("ADMNISTRADOR GENERAL");
       contenedor.add(fachadaBean);

       //
       fachadaBean = new FachadaUsuarioNivel();

       //
       fachadaBean.setIdNivel("4");
       fachadaBean.setNombreNivel("AUDITOR GENERAL");
       contenedor.add(fachadaBean);

       //
       fachadaBean = new FachadaUsuarioNivel();

       //
       fachadaBean.setIdNivel("7");
       fachadaBean.setNombreNivel("TESORERO GENERAL");
       contenedor.add(fachadaBean);

       //
       contenedor.trimToSize();

       // adiciona igual
       for(int i=0; i<contenedor.size(); i++){

            //
            fachadaBean = (FachadaUsuarioNivel)contenedor.elementAt(i);

            //
            if (fachadaBean.getIdNivel()==xIdNivel) {

               //
               fachadaBeanOut = new FachadaUsuarioNivel();

               //
               fachadaBeanOut.setIdNivel(fachadaBean.getIdNivel());
               fachadaBeanOut.setNombreNivel(fachadaBean.getNombreNivel());
               contenedorOut.add(fachadaBeanOut);
               break;
            }            
       }

       // adiciona diferentes
       for(int i=0; i<contenedor.size(); i++){

            //
            fachadaBean = (FachadaUsuarioNivel)contenedor.elementAt(i);

            //
            if (fachadaBean.getIdNivel()!=xIdNivel) {

               //
               fachadaBeanOut = new FachadaUsuarioNivel();

               //
               fachadaBeanOut.setIdNivel(fachadaBean.getIdNivel());
               fachadaBeanOut.setNombreNivel(fachadaBean.getNombreNivel());
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
