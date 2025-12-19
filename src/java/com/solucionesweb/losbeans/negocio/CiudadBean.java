package com.solucionesweb.losbeans.negocio;

// Importa la clase helper JDBCAccess que contiene los accesos a la base de datos
import com.solucionesweb.lasayudas.JDBCAccess;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaCiudadBean;

// Importa los paquetes de java
import java.sql.*;
import java.io.*;
import java.util.*;
import java.io.IOException.*;

// Importa los paquetes de javax
import javax.naming.*;

public class CiudadBean extends FachadaCiudadBean implements Serializable {

  	// Nombre JNDI del Data Source que esta clase requiere
    private static final String DATA_SOURCE_NAME = "CommerceAccess";

	// Clase helper usada para acceder a la base de datos
	private JDBCAccess jdbcAccess;

	// Metodo constructor por defecto sin parametros
    public CiudadBean() {

  	// Al instanciar el Bean establece la conexion a la base de datos
	   jdbcAccess = new JDBCAccess(DATA_SOURCE_NAME);
    }

    // listaDptoCiudad
    public Vector listaDptoCiudad() {

      //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
      Class tipoObjeto       = this.getClass();
      String nombreClase     = tipoObjeto.getName();

      Vector contenedor      = new Vector();
      Connection connection  = null;


      String selectStr =
        "SELECT tblCiudades.idCiudad,       " +
        "       tblCiudades.nombreCiudad,   " +
        "       tblCiudades.nombreDpto      " +
        "FROM tblCiudades                   " +
        "ORDER BY tblCiudades.nombreCiudad  " ;

       PreparedStatement selectStatement = null;

      try {

        // Obtiene una conexion a la base de datos, requiere Try
        connection = jdbcAccess.getConnection();

        // Prepara la sentencia de Busqueda
        selectStatement = connection.prepareStatement(selectStr);

        // Obtiene el resultset
        ResultSet rs = selectStatement.executeQuery();

        // Variable de fachada de los datos
        FachadaCiudadBean fachadaBean;

        while (rs.next()) {

              //
              fachadaBean = new FachadaCiudadBean();

              //
              fachadaBean.setIdCiudad(rs.getInt("idCiudad"));
              fachadaBean.setNombreCiudad(rs.getString("nombreCiudad"));
              fachadaBean.setNombreDpto(rs.getString("nombreDpto"));

              //
              contenedor.add(fachadaBean);

        }

        // Cierra el Resultset
        rs.close();

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
        jdbcAccess.cleanup(connection, selectStatement,null);
        return contenedor;
      }
   }

    // listaUnCiudad
    public Vector listaUnCiudad() {

      //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
      Class tipoObjeto       = this.getClass();
      String nombreClase     = tipoObjeto.getName();

      Vector contenedor      = new Vector();
      Connection connection  = null;


      String selectStr =
        "SELECT tblCiudades.idCiudad,       " +
        "       tblCiudades.nombreCiudad,   " +
        "       tblCiudades.nombreDpto      " +
        "FROM tblCiudades                   " +
        "WHERE tblCiudades.idCiudad     =   " +
        getIdCiudad() + "                   " ;

       PreparedStatement selectStatement = null;

      try {

        // Obtiene una conexion a la base de datos, requiere Try
        connection = jdbcAccess.getConnection();

        // Prepara la sentencia de Busqueda
        selectStatement = connection.prepareStatement(selectStr);

        // Obtiene el resultset
        ResultSet rs = selectStatement.executeQuery();

        // Variable de fachada de los datos
        FachadaCiudadBean fachadaBean;

        while (rs.next()) {

              //
              fachadaBean = new FachadaCiudadBean();

              //
              fachadaBean.setIdCiudad(rs.getInt("idCiudad"));
              fachadaBean.setNombreCiudad(rs.getString("nombreCiudad"));
              fachadaBean.setNombreDpto(rs.getString("nombreDpto"));

              //
              contenedor.add(fachadaBean);

        }

        // Cierra el Resultset
        rs.close();

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
        jdbcAccess.cleanup(connection, selectStatement,null);
       return contenedor;
      }
   }

    // listaDptoCiudad
    public FachadaCiudadBean listaUnDptoCiudad() {

      //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
      Class tipoObjeto       = this.getClass();
      String nombreClase     = tipoObjeto.getName();

      FachadaCiudadBean fachadaBean      = new FachadaCiudadBean();
      Connection connection  = null;


      String selectStr =
        "SELECT tblCiudades.idCiudad,       " +
        "       tblCiudades.nombreCiudad,   " +
        "       tblCiudades.nombreDpto      " +
        "FROM tblCiudades                   " +
        "WHERE tblCiudades.idCiudad  =      " +
        getIdCiudad()  + "                  " +
        "ORDER BY tblCiudades.nombreCiudad  " ;

       PreparedStatement selectStatement = null;

      try {

        // Obtiene una conexion a la base de datos, requiere Try
        connection = jdbcAccess.getConnection();

        // Prepara la sentencia de Busqueda
        selectStatement = connection.prepareStatement(selectStr);

        // Obtiene el resultset
        ResultSet rs = selectStatement.executeQuery();

        while (rs.next()) {

              //
              fachadaBean.setIdCiudad(rs.getInt("idCiudad"));
              fachadaBean.setNombreCiudad(rs.getString("nombreCiudad"));
              fachadaBean.setNombreDpto(rs.getString("nombreDpto"));

        }

        // Cierra el Resultset
        rs.close();

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
        jdbcAccess.cleanup(connection, selectStatement,null);
       return fachadaBean;
      }
   }
}