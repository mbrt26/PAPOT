package com.solucionesweb.losbeans.negocio;

// Importa la clase helper JDBCAccess que contiene los accesos a la base de datos
import com.solucionesweb.lasayudas.JDBCAccess;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaAgendaControlBean;

// Importa los paquetes de java
import java.sql.*;
import java.io.*;
import java.util.*;
import java.io.IOException.*;

// Importa los paquetes de javax
import javax.naming.*;

public class AgendaControlBean extends FachadaAgendaControlBean
                                                       implements Serializable {

  	// Nombre JNDI del Data Source que esta clase requiere
    private static final String DATA_SOURCE_NAME = "CommerceAccess";

	// Clase helper usada para acceder a la base de datos
	private JDBCAccess jdbcAccess;

	// Metodo constructor por defecto sin parametros
    public AgendaControlBean() {

  	// Al instanciar el Bean establece la conexion a la base de datos
	   jdbcAccess = new JDBCAccess(DATA_SOURCE_NAME);
    }

    // Metodo
    public Vector listaClientesVendedor() {

      //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
      Class tipoObjeto       = this.getClass();
      String nombreClase     = tipoObjeto.getName();
      Vector contenedor      = new Vector();

      boolean    validaOk    = false;
      Connection connection  = null;

      String selectStr =
        "SELECT tblAgendaControl.idCliente,     " +
        "       tblAgendaControl.idUsuario,     " +
        "       tblAgendaControl.strIdSucursal, " +
        "       tblAgendaControl.idPeriodo,     " +
        "       tblAgendaControl.fechaVisita,   " +
        "       tblAgendaControl.estado         " +
        "FROM tblAgendaControl                  " +
        "WHERE tblAgendaControl.idUsuario = (?) " ;

      PreparedStatement selectStatement = null;

      try {

        // Obtiene una conexion a la base de datos, requiere Try
        connection = jdbcAccess.getConnection();

        // Prepara la sentencia de Busqueda
        selectStatement = connection.prepareStatement(selectStr);

        // inicializa los valores de los parametros
        selectStatement.setDouble(1,getIdUsuario());

        // Obtiene el resultset
        ResultSet rs = selectStatement.executeQuery();

        // Variable de fachada de los datos
        FachadaAgendaControlBean fachadaBean;

        while (rs.next()) {

              fachadaBean = new FachadaAgendaControlBean();

              fachadaBean.setIdCliente(rs.getString("idCliente"));
              fachadaBean.setIdUsuario(rs.getInt("idUsuario"));
              fachadaBean.setStrIdSucursal(rs.getString("strIdSucursal"));
              fachadaBean.setIdPeriodo(rs.getInt("idPeriodo"));
              fachadaBean.setFechaVisitaStr(rs.getString("fechaVisita").substring(0,11));
              fachadaBean.setEstado(rs.getInt("estado"));

              //
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

        // Cierra la conexion
       return contenedor;

      }
    }

    // Metodo listaVendedorxFechaVisita MsAccess
    public Vector listaVendedorxFechaVisita() {

      //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
      Class tipoObjeto       = this.getClass();
      String nombreClase     = tipoObjeto.getName();
      Vector contenedor      = new Vector();

      boolean    validaOk    = false;
      Connection connection  = null;

      String selectStr =
        "SELECT tblAgendaControl.idCliente,       " +
        "       tblAgendaControl.idUsuario,       " +
        "       tblAgendaControl.strIdSucursal,   " +
        "       tblAgendaControl.idPeriodo,       " +
        "       tblAgendaControl.fechaVisita,     " +
        "       tblAgendaControl.estado           " +
        "FROM tblAgendaControl                    " +
        "WHERE tblAgendaControl.idUsuario   =  " + getIdUsuario()            + "  " +
        "AND   tblAgendaControl.fechaVisita = '" + getFechaVisitaSqlServer() + "' " +
        "AND   tblAgendaControl.estado      = 1" ;

      PreparedStatement selectStatement = null;

      try {

        // Obtiene una conexion a la base de datos, requiere Try
        connection = jdbcAccess.getConnection();

        // Prepara la sentencia de Busqueda
        selectStatement = connection.prepareStatement(selectStr);

        // Obtiene el resultset
        ResultSet rs = selectStatement.executeQuery();

        // Variable de fachada de los datos
        FachadaAgendaControlBean fachadaBean;

        while (rs.next()) {

              fachadaBean = new FachadaAgendaControlBean();

              fachadaBean.setIdCliente(rs.getString("idCliente"));
              fachadaBean.setIdUsuario(rs.getInt("idUsuario"));
              fachadaBean.setStrIdSucursal(rs.getString("strIdSucursal"));
              fachadaBean.setIdPeriodo(rs.getInt("idPeriodo"));
              fachadaBean.setFechaVisitaStr(rs.getString("fechaVisita").substring(0,10));
              fachadaBean.setEstado(rs.getInt("estado"));

              //
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

        // Cierra la conexion
       return contenedor;

      }
    }

    // ingresaAgendaVendedorDiaVisita MsAccess
    public boolean ingresaAgendaVendedorDiaVisita() {

      //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
      Class tipoObjeto       = this.getClass();
      String nombreClase     = tipoObjeto.getName();

      boolean    validaOk    = false;
      Connection connection  = null;

      String updateStr =
        "INSERT INTO tblAgendaControl (idCliente,      " +
        "                              idUsuario,      " +
        "                              strIdSucursal,  " +
        "                              idPeriodo,      " +
        "                              fechaVisita,    " +
        "                              estado)         " +
        "VALUES ( '"+ getIdCliente()              + "'," +
                      getIdUsuario()              + ", " +
                      getStrIdSucursalOracle()    + ", " +
                      getIdPeriodo()              + ",'" +
                      getFechaVisitaSqlServer( ) + "', " +
                      getEstado()                 + ") " ;

      PreparedStatement selectStatement = null;

      try {

        // Obtiene una conexion a la base de datos, requiere Try
        connection = jdbcAccess.getConnection();

        // Prepara la sentencia de Busqueda
        selectStatement = connection.prepareStatement(updateStr);

        // Obtiene el resultset
        selectStatement.executeUpdate();

        //
        validaOk  = true;

        // Cierra el Resultset
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
		return validaOk;

	  }

	  catch (Exception e)
	  {
		System.out.println("Exception In :" + nombreClase + " " + e);
	  }
      finally {
        // Cierra la conexion a la base de datos limpiando la statement usada y la
        // conexion
       return validaOk;
      }
    }

    // actualizaEstado MsAccess
    public boolean actualizaEstado() {

      //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
      Class tipoObjeto       = this.getClass();
      String nombreClase     = tipoObjeto.getName();

      boolean    validaOk    = false;
      Connection connection  = null;

      String updateStr =
             "UPDATE tblAgendaControl                                                  " +
             "SET tblAgendaControl.estado          = " + getEstado()              + "  " +
             "WHERE tblAgendaControl.idCliente     ='" + getIdCliente()           + "' " +
             "AND   tblAgendaControl.idUsuario     = " + getIdUsuario()           + "  " +
             "AND   tblAgendaControl.strIdSucursal = " + getStrIdSucursalOracle() + "  " +
             "AND   tblAgendaControl.fechaVisita   ='" + getFechaVisitaSqlServer() + "'"  ;

      PreparedStatement selectStatement = null;

      try {

        // Obtiene una conexion a la base de datos, requiere Try
        connection = jdbcAccess.getConnection();

        // Prepara la sentencia de Busqueda
        selectStatement = connection.prepareStatement(updateStr);

        // Obtiene el resultset
        selectStatement.executeUpdate();

        //
        validaOk  = true;

        // Cierra el Resultset
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
		return validaOk;

	  }

	  catch (Exception e)
	  {
		System.out.println("Exception In :" + nombreClase + " " + e);
	  }
      finally {
        // Cierra la conexion a la base de datos limpiando la statement usada y la
        // conexion
       return validaOk;
      }
    }
}