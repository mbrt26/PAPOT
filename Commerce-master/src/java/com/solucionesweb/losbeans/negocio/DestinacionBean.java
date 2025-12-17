package com.solucionesweb.losbeans.negocio;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaDestinacionBean;

import java.io.*;
import java.util.*;

public class DestinacionBean implements Serializable {

	// Propiedades
	private String instruccionDestinacion;
	private String idDestinacion;


	// Metodo constructor por defecto sin parametros
	public DestinacionBean() {
	}

	public void setInstruccionDestinacion(String instruccionDestinacion) {
		this.instruccionDestinacion = instruccionDestinacion;
	}

	public String getInstruccionDestinacion() {
		return instruccionDestinacion;
	}

	public void setIdDestinacion(String idDestinacion) {
		this.idDestinacion = idDestinacion;
	}

	public String getIdDestinacion() {
		return idDestinacion;
	}


    // Consulta que devuelve el id e instruccion de destinacion
    public Vector obtenerDestinacion() {

      //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
	  Class tipoObjeto = this.getClass();
      String nombreClase = tipoObjeto.getName();

      Vector contenedor = new Vector();

      // Variable de fachada de los datos
      FachadaDestinacionBean fachadaBean;

     // llena el vector con las posibles destinaciones del reporte
     try {
       fachadaBean = new FachadaDestinacionBean();
       fachadaBean.setInstruccionDestinacion("text/html");
       fachadaBean.setIdDestinacion("Pantalla");
       contenedor.add(fachadaBean);

       fachadaBean = new FachadaDestinacionBean();
       fachadaBean.setInstruccionDestinacion("application/rnd.ms-excel");
       fachadaBean.setIdDestinacion("Archivo");
       contenedor.add(fachadaBean);

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
}
