package com.solucionesweb.losbeans.negocio;

// Importa la clase helper JDBCAccess que contiene los accesos a la base de datos
import com.solucionesweb.lasayudas.JDBCAccess;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaAgendaProgramacionBean;

// Importa los paquetes de java
import java.sql.*;
import java.io.*;
import java.util.*;
import java.io.IOException.*;

// Importa los paquetes de javax
import javax.naming.*;

public class AgendaProgramacionBean extends FachadaAgendaProgramacionBean
                                                       implements Serializable {

  	// Nombre JNDI del Data Source que esta clase requiere
    private static final String DATA_SOURCE_NAME = "CommerceAccess";

	// Clase helper usada para acceder a la base de datos
	private JDBCAccess jdbcAccess;

	// Metodo constructor por defecto sin parametros
    public AgendaProgramacionBean() {

  	// Al instanciar el Bean establece la conexion a la base de datos
	   jdbcAccess = new JDBCAccess(DATA_SOURCE_NAME);
    }

    // listaClientesVendedor
    public Vector listaClientesVendedor() {

      //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
      Class tipoObjeto       = this.getClass();
      String nombreClase     = tipoObjeto.getName();
      Vector contenedor      = new Vector();

      boolean    validaOk    = false;
      Connection connection  = null;

      String selectStr =
        "SELECT tblAgendaProgramacion.idCliente,       " +
        "       tblAgendaProgramacion.idUsuario,       " +
        "       tblAgendaProgramacion.idSucursal,      " +
        "       tblAgendaProgramacion.idFrecuencia,    " +
        "       tblAgendaProgramacion.idDiaVisita,     " +
        "       tblAgendaProgramacion.estado           " +
        "FROM tblAgendaProgramacion                    " +
        "WHERE tblAgendaProgramacion.idUsuario = ( ? ) " +
        "ORDER BY tblAgendaProgramacion.idCliente,     " +
        "         tblAgendaProgramacion.idFrecuencia,  " +
        "         tblAgendaProgramacion.idDiaVisita    " ;

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
        FachadaAgendaProgramacionBean fachadaBean;

        while (rs.next()) {

              fachadaBean = new FachadaAgendaProgramacionBean();

              fachadaBean.setIdCliente(rs.getString("idCliente"));
              fachadaBean.setIdUsuario(rs.getInt("idUsuario"));
              fachadaBean.setIdSucursal(rs.getString("idSucursal"));
              fachadaBean.setIdFrecuencia(rs.getInt("idFrecuencia"));
              fachadaBean.setIdDiaVisita(rs.getInt("idDiaVisita"));
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

    // listaClientesVendedorDiaVisita
    public Vector listaClientesVendedorDiaVisita() {

      //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
      Class tipoObjeto       = this.getClass();
      String nombreClase     = tipoObjeto.getName();
      Vector contenedor      = new Vector();

      boolean    validaOk    = false;
      Connection connection  = null;

      String selectStr =
        "SELECT tblAgendaProgramacion.idCliente,         " +
        "       tblAgendaProgramacion.idUsuario,         " +
        "       tblAgendaProgramacion.idSucursal,        " +
        "       tblAgendaProgramacion.idFrecuencia,      " +
        "       tblAgendaProgramacion.idDiaVisita,       " +
        "       tblAgendaProgramacion.estado             " +
        "FROM tblAgendaProgramacion                      " +
        "WHERE tblAgendaProgramacion.idUsuario   = ( ? ) " +
        "AND   tblAgendaProgramacion.idDiaVisita = ( ? ) " +
        "AND   tblAgendaProgramacion.estado      = ( ? ) " +
        "ORDER BY tblAgendaProgramacion.idCliente,       " +
        "         tblAgendaProgramacion.idFrecuencia,    " +
        "         tblAgendaProgramacion.idDiaVisita      " ;

      PreparedStatement selectStatement = null;

      try {

        // Obtiene una conexion a la base de datos, requiere Try
        connection = jdbcAccess.getConnection();

        // Prepara la sentencia de Busqueda
        selectStatement = connection.prepareStatement(selectStr);

        // inicializa los valores de los parametros
        selectStatement.setDouble(1,getIdUsuario());
        selectStatement.setInt(2,getIdDiaVisita());
        selectStatement.setInt(3,getEstado());

        // Obtiene el resultset
        ResultSet rs = selectStatement.executeQuery();

        // Variable de fachada de los datos
        FachadaAgendaProgramacionBean fachadaBean;

        while (rs.next()) {

              fachadaBean = new FachadaAgendaProgramacionBean();

              fachadaBean.setIdCliente(rs.getString("idCliente"));
              fachadaBean.setIdUsuario(rs.getInt("idUsuario"));
              fachadaBean.setIdSucursal(rs.getString("idSucursal"));
              fachadaBean.setIdFrecuencia(rs.getInt("idFrecuencia"));
              fachadaBean.setIdDiaVisita(rs.getInt("idDiaVisita"));
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

    // ingresaCliente
    public boolean ingresaCliente() {

      //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
      Class tipoObjeto       = this.getClass();
      String nombreClase     = tipoObjeto.getName();

      boolean    validaOk    = false;
      Connection connection  = null;

      String updateStr =
        "INSERT INTO tblAgendaProgramacion (idCliente,    " +
        "                                   idSucursal,   " +
        "                                   idUsuario,    " +
        "                                   idFrecuencia, " +
        "                                   idDiaVisita,  " +
        "                                   estado)       " +
        "VALUES (  ?, ?, ?, ?, ?, ? )                     " ;

      PreparedStatement selectStatement = null;

      try {

        // Obtiene una conexion a la base de datos, requiere Try
        connection = jdbcAccess.getConnection();

        // Prepara la sentencia de Busqueda
        selectStatement = connection.prepareStatement(updateStr);

        // inicializa los valores de los parametros
        selectStatement.setString(1,getIdCliente());
        selectStatement.setString(2,getIdSucursal());
        selectStatement.setDouble(3,getIdUsuario());
        selectStatement.setInt(4,getIdFrecuencia());
        selectStatement.setInt(5,getIdDiaVisita());
        selectStatement.setInt(6,getEstado());

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

    // retiraCliente Oracle
    public boolean retiraCliente() {

      //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
      Class tipoObjeto       = this.getClass();
      String nombreClase     = tipoObjeto.getName();

      boolean    validaOk    = false;
      Connection connection  = null;

      String updateStr =
      "DELETE FROM tblAgendaProgramacion             " +
      "WHERE tblAgendaProgramacion.idCliente = ( ? ) " +
      "AND   tblAgendaProgramacion.idUsuario = ( ? ) " ;

      PreparedStatement selectStatement = null;

      try {

        // Obtiene una conexion a la base de datos, requiere Try
        connection = jdbcAccess.getConnection();

        // Prepara la sentencia de Busqueda
        selectStatement = connection.prepareStatement(updateStr);

        // inicializa los valores de los parametros
        selectStatement.setString(1,getIdCliente());
        selectStatement.setDouble(2,getIdUsuario());

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

    /* retiraCliente MsAccess
    public boolean retiraCliente() {

      //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
      Class tipoObjeto       = this.getClass();
      String nombreClase     = tipoObjeto.getName();

      boolean    validaOk    = false;
      Connection connection  = null;

      String updateStr =
      "DELETE tblAgendaProgramacion                  " +
      "FROM tblAgendaProgramacion                    " +
      "WHERE tblAgendaProgramacion.idCliente = ( ? ) " +
      "AND   tblAgendaProgramacion.idUsuario = ( ? ) " ;

      PreparedStatement selectStatement = null;

      try {

        // Obtiene una conexion a la base de datos, requiere Try
        connection = jdbcAccess.getConnection();

        // Prepara la sentencia de Busqueda
        selectStatement = connection.prepareStatement(updateStr);

        // inicializa los valores de los parametros
        selectStatement.setDouble(1,getIdCliente());
        selectStatement.setDouble(2,getIdUsuario());

        System.out.println(" getIdCliente() " + getIdCliente() );
        System.out.println(" getIdUsuario() " + getIdUsuario() );
        System.out.println(" ---------------"  );

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
    } */
}