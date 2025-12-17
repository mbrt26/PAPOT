package com.solucionesweb.losbeans.negocio;

// Importa la clase helper JDBCAccess que contiene los accesos a la base de datos
import com.solucionesweb.lasayudas.JDBCAccess;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaDctoEstadoBean;

// Importa los paquetes de java
import java.sql.*;
import java.io.*;
import java.util.*;
import java.io.IOException.*;

// Importa los paquetes de javax
import javax.naming.*;

public class DctoEstadoBean extends FachadaDctoEstadoBean
                                                       implements Serializable {

  	// Nombre JNDI del Data Source que esta clase requiere
    private static final String DATA_SOURCE_NAME = "CommerceAccess";

	// Clase helper usada para acceder a la base de datos
	private JDBCAccess jdbcAccess;

	// Metodo constructor por defecto sin parametros
    public DctoEstadoBean() {

  	// Al instanciar el Bean establece la conexion a la base de datos
	   jdbcAccess = new JDBCAccess(DATA_SOURCE_NAME);
    }

    // listaEstadoDcto
    public Vector listaEstadoDcto() {

      //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
      Class tipoObjeto       = this.getClass();
      String nombreClase     = tipoObjeto.getName();
      Vector contenedor      = new Vector();

      boolean    validaOk    = false;
      Connection connection  = null;

      String selectStr =
        "SELECT tblDctosEstados.estado,        " +
        "       tblDctosEstados.nombreEstado,  " +
        "       tblDctosEstados.estadoregistro " +
        "FROM tblDctosEstados                  " +
        "ORDER BY tblDctosEstados.nombreEstado " ;

      PreparedStatement selectStatement = null;

      try {

        // Obtiene una conexion a la base de datos, requiere Try
        connection = jdbcAccess.getConnection();

        // Prepara la sentencia de Busqueda
        selectStatement = connection.prepareStatement(selectStr);

        // Obtiene el resultset
        ResultSet rs = selectStatement.executeQuery();

        // Variable de fachada de los datos
        FachadaDctoEstadoBean fachadaBean;

        while (rs.next()) {

              fachadaBean = new FachadaDctoEstadoBean();
              fachadaBean.setEstado(rs.getInt("estado"));
              fachadaBean.setNombreEstado(rs.getString("nombreEstado"));
              fachadaBean.setEstadoRegistro(rs.getInt("estadoRegistro"));
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

        // Cierra la conexion
        jdbcAccess.cleanup(connection, selectStatement,null);
        return contenedor;

      }
    }

    // listaUnEstadoDcto
    public Vector listaUnEstadoDcto() {

      //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
      Class tipoObjeto       = this.getClass();
      String nombreClase     = tipoObjeto.getName();
      Vector contenedor      = new Vector();

      boolean    validaOk    = false;
      Connection connection  = null;

      String selectStr =
      "SELECT tblDctosEstados.estado,         " +
      "       tblDctosEstados.nombreEstado,   " +
      "       tblDctosEstados.estadoRegistro  " +
      "FROM tblDctosEstados                   " +
      "WHERE tblDctosEstados.estado = ( ? )   " ;

      PreparedStatement selectStatement = null;

      try {

        // Obtiene una conexion a la base de datos, requiere Try
        connection = jdbcAccess.getConnection();

        // Prepara la sentencia de Busqueda
        selectStatement = connection.prepareStatement(selectStr);

       // inicializa los valores de los parametros
       selectStatement.setInt(1,getEstado());

        // Obtiene el resultset
        ResultSet rs = selectStatement.executeQuery();

        // Variable de fachada de los datos
        FachadaDctoEstadoBean fachadaBean;

        while (rs.next()) {

              fachadaBean = new FachadaDctoEstadoBean();
              fachadaBean.setEstado(rs.getInt("estado"));
              fachadaBean.setNombreEstado(rs.getString("nombreEstado"));
              fachadaBean.setEstadoRegistro(rs.getInt("estadoRegistro"));
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

        // Cierra la conexion
        jdbcAccess.cleanup(connection, selectStatement,null);
        return contenedor;

      }
    }

    // listaUnEstadoDctoFachada
    public FachadaDctoEstadoBean listaUnEstadoDctoFachada() {

      //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
      Class tipoObjeto       = this.getClass();
      String nombreClase     = tipoObjeto.getName();

      //
      FachadaDctoEstadoBean fachadaDctoEstadoBean = new FachadaDctoEstadoBean();

      boolean    validaOk    = false;
      Connection connection  = null;

      String selectStr =
      "SELECT tblDctosEstados.estado,         " +
      "       tblDctosEstados.nombreEstado,   " +
      "       tblDctosEstados.estadoRegistro  " +
      "FROM tblDctosEstados                   " +
      "WHERE tblDctosEstados.estado = ( ? )   " ;

      PreparedStatement selectStatement = null;

      try {

        // Obtiene una conexion a la base de datos, requiere Try
        connection = jdbcAccess.getConnection();

        // Prepara la sentencia de Busqueda
        selectStatement = connection.prepareStatement(selectStr);

       // inicializa los valores de los parametros
       selectStatement.setInt(1,getEstado());

        // Obtiene el resultset
        ResultSet rs = selectStatement.executeQuery();

        //
        if (rs.next()) {

              fachadaDctoEstadoBean.setEstado(rs.getInt("estado"));
              fachadaDctoEstadoBean.setNombreEstado(rs.getString("nombreEstado"));
              fachadaDctoEstadoBean.setEstadoRegistro(rs.getInt("estadoRegistro"));

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
        return fachadaDctoEstadoBean;

	  }

	  catch (Exception e)
	  {
		System.out.println("Exception In :" + nombreClase + " " + e);
	  }
      finally {

        // Cierra la conexion
        jdbcAccess.cleanup(connection, selectStatement,null);
        return fachadaDctoEstadoBean;

      }
    }

    // listaOpcionEstados
    public Vector listaOpcionEstados() {

      //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
      Class tipoObjeto       = this.getClass();
      String nombreClase     = tipoObjeto.getName();
      Vector contenedor      = new Vector();

      boolean    validaOk    = false;
      Connection connection  = null;

      String selectStr =
      "SELECT tblDctosEstados.estado,         " +
      "       tblDctosEstados.nombreEstado,   " +
      "       tblDctosEstados.estadoRegistro, " +
      "       01 AS orden                     " +
      "FROM tblDctosEstados                   " +
      "WHERE tblDctosEstados.estado = ( ? )   " +
      "UNION                                  " +
      "SELECT tblDctosEstados.estado,         " +
      "       tblDctosEstados.nombreEstado,   " +
      "       tblDctosEstados.estadoRegistro, " +
      "       02 AS orden                     " +
      "FROM tblDctosEstados                   " +
      "WHERE tblDctosEstados.estado <> ( ? )  " +
      "ORDER BY 4,2                           " ;

      PreparedStatement selectStatement = null;

      try {

        // Obtiene una conexion a la base de datos, requiere Try
        connection = jdbcAccess.getConnection();

        // Prepara la sentencia de Busqueda
        selectStatement = connection.prepareStatement(selectStr);

       // inicializa los valores de los parametros
       selectStatement.setInt(1,getEstado());
       selectStatement.setInt(2,getEstado());

        // Obtiene el resultset
        ResultSet rs = selectStatement.executeQuery();

        // Variable de fachada de los datos
        FachadaDctoEstadoBean fachadaBean;

        while (rs.next()) {

              fachadaBean = new FachadaDctoEstadoBean();
              fachadaBean.setEstado(rs.getInt("estado"));
              fachadaBean.setNombreEstado(rs.getString("nombreEstado"));
              fachadaBean.setEstadoRegistro(rs.getInt("estadoRegistro"));
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

        // Cierra la conexion
        jdbcAccess.cleanup(connection, selectStatement,null);
        return contenedor;

      }
    }

    // seleccionaUnEstadoVisita
    public boolean seleccionaUnEstadoVisita() {

      //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
      Class tipoObjeto       = this.getClass();
      String nombreClase     = tipoObjeto.getName();
      Vector contenedor      = new Vector();

      boolean    validaOk    = false;
      Connection connection  = null;

      String selectStr =
        "SELECT tblDctosEstados.estado,         " +
        "       tblDctosEstados.nombreEstado,   " +
        "       tblDctosEstados.estadoRegistro  " +
        "FROM tblDctosEstados                   " +
        "WHERE tblDctosEstados.estado = ( ? )   " ;

      PreparedStatement selectStatement = null;

      try {

        // Obtiene una conexion a la base de datos, requiere Try
        connection = jdbcAccess.getConnection();

        // Prepara la sentencia de Busqueda
        selectStatement = connection.prepareStatement(selectStr);

       // inicializa los valores de los parametros
       selectStatement.setInt(1,getEstado());

        // Obtiene el resultset
        ResultSet rs = selectStatement.executeQuery();

        if  (rs.next()) {

            validaOk    = true;

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
        return validaOk;

	  }

	  catch (Exception e)
	  {
		System.out.println("Exception In :" + nombreClase + " " + e);
	  }
      finally {

        // Cierra la conexion
        jdbcAccess.cleanup(connection, selectStatement,null);
        return validaOk;

      }
    }

}