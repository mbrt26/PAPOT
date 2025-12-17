package com.solucionesweb.losbeans.negocio;

// Importa la clase helper JDBCAccess que contiene los accesos a la base de datos
import com.solucionesweb.lasayudas.JDBCAccess;

// Importa los paquetes de java
import java.sql.*;
import java.io.*;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaImpuestoVentaBean;

import java.io.*;
import java.util.*;

//import java.util.Date;
import java.util.Vector;
import java.io.IOException.*;

// Importa los paquetes de javax
import javax.sql.*;
import javax.naming.*;

public class ImpuestoVentaBean extends FachadaImpuestoVentaBean
                                                       implements Serializable {
  	// Nombre JNDI del Data Source que esta clase requiere
    private static final String DATA_SOURCE_NAME = "CommerceAccess";

	// Clase helper usada para acceder a la base de datos
	private JDBCAccess jdbcAccess;

	// Metodo constructor por defecto sin parametros
    public ImpuestoVentaBean() {

  	// Al instanciar el Bean establece la conexion a la base de datos
	   jdbcAccess = new JDBCAccess(DATA_SOURCE_NAME);
    }

    // Metodo
    public Vector obtenerImpuesto() {

      //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
      Class tipoObjeto       = this.getClass();
      String nombreClase     = tipoObjeto.getName();

      Vector contenedor      = new Vector();
      Connection connection  = null;

      String selectStr =
      "SELECT tblImpuestoVentas.idImpuesto,         " +
      "       tblImpuestoVentas.nombreImpuesto,     " +
      "       tblImpuestoVentas.porcentajeImpuesto, " +
      "       tblImpuestoVentas.estado              " +
      "FROM tblImpuestoVentas                       " +
      "ORDER BY tblImpuestoVentas.nombreImpuesto    " ;

      PreparedStatement selectStatement = null;

      try {

        // Obtiene una conexion a la base de datos, requiere Try
        connection = jdbcAccess.getConnection();

        // Prepara la sentencia de Busqueda
        selectStatement = connection.prepareStatement(selectStr);

        // Obtiene el resultset
        ResultSet rs = selectStatement.executeQuery();

        // Variable de fachada de los datos
        FachadaImpuestoVentaBean fachadaBean;

        while (rs.next()) {

              fachadaBean = new FachadaImpuestoVentaBean();
              fachadaBean.setIdImpuesto(rs.getInt("idImpuesto"));
              fachadaBean.setNombreImpuesto(rs.getString("nombreImpuesto"));
              fachadaBean.setPorcentajeImpuesto(rs.getInt("porcentajeImpuesto"));
              fachadaBean.setEstado(rs.getInt("estado"));

              contenedor.add(fachadaBean);

        }

        // Cierra el Resultset
        rs.close();
        jdbcAccess.cleanup(connection, selectStatement,null);

      }
      catch(NamingException ne)
      {
      	System.out.println("NamingException in " + nombreClase + " " + ne);
      }
      catch (SQLException sqle) {

		System.out.println("SQLException in" + nombreClase);

		String sqlMessage = sqle.getMessage();
        String sqlState = sqle.getSQLState();
        int vendorCode = sqle.getErrorCode();
		System.out.println("Ocurrio una  excepcion");
		System.out.println("Mensaje " + sqlMessage);
		System.out.println("SQL State " + sqlState);
		System.out.println("Vendor Code " + vendorCode);
		return contenedor;

	  }

	  catch (Exception e)
	  {
		System.out.println("Exception In :" + nombreClase + " " + e);
	  }
      finally {
        // Cierra la conexion a la base de datos limpiando la statement usada y la
        // conexion
       return contenedor;
      }
    }

    // obtenerImpuestoFachada
    public FachadaImpuestoVentaBean obtenerImpuestoFachada() {

      //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
      Class tipoObjeto       = this.getClass();
      String nombreClase     = tipoObjeto.getName();

      Connection connection  = null;

      String selectStr =
      "SELECT tblImpuestoVentas.idImpuesto,         " +
      "       tblImpuestoVentas.nombreImpuesto,     " +
      "       tblImpuestoVentas.porcentajeImpuesto, " +
      "       tblImpuestoVentas.estado              " +
      "FROM   tblImpuestoVentas                     " +
      "WHERE  tblImpuestoVentas.idImpuesto = ( ? )  " ;



      PreparedStatement selectStatement = null;

      FachadaImpuestoVentaBean fachadaBean =
                                                 new FachadaImpuestoVentaBean();

      try {

        // Obtiene una conexion a la base de datos, requiere Try
        connection = jdbcAccess.getConnection();

        // Prepara la sentencia de Busqueda
        selectStatement = connection.prepareStatement(selectStr);

        //
	    selectStatement.setInt(1,getIdImpuesto());

        // Obtiene el resultset
        ResultSet rs = selectStatement.executeQuery();

        if   (rs.next()) {

              fachadaBean.setIdImpuesto(rs.getInt("idImpuesto"));
              fachadaBean.setNombreImpuesto(rs.getString("nombreImpuesto"));
              fachadaBean.setPorcentajeImpuesto(rs.getInt("porcentajeImpuesto"));
              fachadaBean.setEstado(rs.getInt("estado"));

        }

        // Cierra el Resultset
        rs.close();
        jdbcAccess.cleanup(connection, selectStatement,null);

      }
      catch(NamingException ne)
      {
      	System.out.println("NamingException in " + nombreClase + " " + ne);
      }
      catch (SQLException sqle) {

		System.out.println("SQLException in" + nombreClase);

		String sqlMessage = sqle.getMessage();
        String sqlState = sqle.getSQLState();
        int vendorCode = sqle.getErrorCode();
		System.out.println("Ocurrio una  excepcion");
		System.out.println("Mensaje " + sqlMessage);
		System.out.println("SQL State " + sqlState);
		System.out.println("Vendor Code " + vendorCode);
		return fachadaBean;

	  }

	  catch (Exception e)
	  {
		System.out.println("Exception In :" + nombreClase + " " + e);
	  }
      finally {
        // Cierra la conexion a la base de datos limpiando la statement usada y la
        // conexion
       return fachadaBean;
      }
    }


}