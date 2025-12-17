package com.solucionesweb.losbeans.negocio;

// Importa la clase helper JDBCAccess que contiene los accesos a la base de datos
import com.solucionesweb.lasayudas.JDBCAccess;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaAgendaLogSoporteBean;

// Importa los paquetes de java
import java.sql.*;
import java.io.*;
import java.util.*;
import java.io.IOException.*;

// Importa los paquetes de javax
import javax.naming.*;

public class AgendaLogSoporteBean extends FachadaAgendaLogSoporteBean
                                                       implements Serializable {

  	// Nombre JNDI del Data Source que esta clase requiere
    private static final String DATA_SOURCE_NAME = "CommerceAccess";

	// Clase helper usada para acceder a la base de datos
	private JDBCAccess jdbcAccess;

	// Metodo constructor por defecto sin parametros
    public AgendaLogSoporteBean() {

  	// Al instanciar el Bean establece la conexion a la base de datos
	   jdbcAccess = new JDBCAccess(DATA_SOURCE_NAME);
    }

    // ingresaSoporte
    public boolean ingresaSoporte() {

      //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
      Class tipoObjeto       = this.getClass();
      String nombreClase     = tipoObjeto.getName();

      boolean    validaOk    = false;
      Connection connection  = null;

      String updateStr =
        "INSERT INTO tblAgendaLogSoportes (idLog,           " +
        "                                  idCliente,       " +
        "                                  nombreCliente,   " +
        "                                  telefono,        " +
        "                                  estado,          " +
        "                                  email,           " +
        "                                  contacto,        " +
        "                                  idEstadoTx)      " +
        "VALUES ( " + getIdLog()                      + ",' " +
                      getIdCliente()                  + "','" +
                      getNombreCliente()              + "','" +
                      getTelefono()                   + "', " +
                      getEstado()                     + ",' " +
                      getEmail()                      + "','" +
                      getContacto()                   + "', " +
                      getIdEstadoTx()                 + ") " ;

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

    // actualizaSoporte
    public boolean actualizaSoporte() {

      //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
      Class tipoObjeto       = this.getClass();
      String nombreClase     = tipoObjeto.getName();

      boolean    validaOk    = false;
      Connection connection  = null;

      String updateStr =
      "UPDATE tblAgendaLogSoportes                     " +
      "SET tblAgendaLogSoportes.idCliente     = ( ? ), " +
      "    tblAgendaLogSoportes.NOMBRECLIENTE = ( ? ), " +
      "    tblAgendaLogSoportes.TELEFONO      = ( ? ), " +
      "    tblAgendaLogSoportes.email         = ( ? ), " +
      "    tblAgendaLogSoportes.contacto      = ( ? )  " +
      "WHERE tblAgendaLogSoportes.IDLOG       = ( ? )  " ;


      PreparedStatement selectStatement = null;

      try {

        // Obtiene una conexion a la base de datos, requiere Try
        connection = jdbcAccess.getConnection();

        // Prepara la sentencia de Busqueda
        selectStatement = connection.prepareStatement(updateStr);

       // inicializa los valores de los parametros
       selectStatement.setString(1,getIdCliente());
       selectStatement.setString(2,getNombreCliente());
       selectStatement.setString(3,getTelefono());
       selectStatement.setString(4,getEmail());
       selectStatement.setString(5,getContacto());
       selectStatement.setInt(6,getIdLog());

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

    // actualiza
    public boolean actualiza() {

      //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
      Class tipoObjeto       = this.getClass();
      String nombreClase     = tipoObjeto.getName();

      boolean    validaOk    = false;
      Connection connection  = null;

      String updateStr =
      "UPDATE tblAgendaLogSoportes                     " +
      "SET tblAgendaLogSoportes.idEstadoTx    = ( ? )  " +
      "WHERE tblAgendaLogSoportes.IDLOG       = ( ? )  " ;


      PreparedStatement selectStatement = null;

      try {

        // Obtiene una conexion a la base de datos, requiere Try
        connection = jdbcAccess.getConnection();

        // Prepara la sentencia de Busqueda
        selectStatement = connection.prepareStatement(updateStr);

       // inicializa los valores de los parametros
       selectStatement.setInt(1,getIdEstadoTx());
       selectStatement.setInt(2,getIdLog());

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

    // Metodo listaUnLog
    public Vector listaUnLog() {

      //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
      Class tipoObjeto       = this.getClass();
      String nombreClase     = tipoObjeto.getName();
      Vector contenedor      = new Vector();

      boolean    validaOk    = false;
      Connection connection  = null;

      String selectStr =
        "SELECT tblAgendaLogSoportes.idLog,             " +
        "       tblAgendaLogSoportes.idCliente,         " +
        "       tblAgendaLogSoportes.nombreCliente,     " +
        "       tblAgendaLogSoportes.telefono,          " +
        "       tblAgendaLogSoportes.estado,            " +
        "       tblAgendaLogSoportes.email,             " +
        "       tblAgendaLogSoportes.contacto,          " +
        "       tblAgendaLogSoportes.idEstadoTx         " +
        "FROM tblAgendaLogSoportes                      " +
        "WHERE tblAgendaLogSoportes.idLog =  ( ? )      " ;

      PreparedStatement selectStatement = null;

      try {

        // Obtiene una conexion a la base de datos, requiere Try
        connection = jdbcAccess.getConnection();

        // Prepara la sentencia de Busqueda
        selectStatement = connection.prepareStatement(selectStr);

       // inicializa los valores de los parametros
       selectStatement.setInt(1,getIdLog());

        // Obtiene el resultset
        ResultSet rs = selectStatement.executeQuery();

        // Variable de fachada de los datos
        FachadaAgendaLogSoporteBean fachadaBean;

        while (rs.next()) {

              fachadaBean = new FachadaAgendaLogSoporteBean();

              fachadaBean.setIdLog(rs.getInt("idLog"));
              fachadaBean.setIdCliente(rs.getString("idCliente"));
              fachadaBean.setNombreCliente(rs.getString("nombreCliente"));
              fachadaBean.setTelefono(rs.getString("telefono"));
              fachadaBean.setEstado(rs.getInt("estado"));
              fachadaBean.setEmail(rs.getString("email"));
              fachadaBean.setContacto(rs.getString("contacto"));
              fachadaBean.setIdEstadoTx(rs.getInt("idEstadoTx"));

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

    // Metodo listaSoporteRuta
    public Vector listaSoporteRuta(String xIdRuta) {

      //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
      Class tipoObjeto       = this.getClass();
      String nombreClase     = tipoObjeto.getName();
      Vector contenedor      = new Vector();

      boolean    validaOk    = false;
      Connection connection  = null;

      String selectStr =
"SELECT MIN(tblAgendaLogSoportes.IDLOG)         AS IDLOG,          " +
"       tblAgendaLogSoportes.idCliente,                              " +
"       MIN(tblAgendaLogSoportes.NOMBRECLIENTE) AS NOMBRECLIENTE,  " +
"       MIN(tblAgendaLogSoportes.TELEFONO)      AS TELEFONO,       " +
"       MIN(tblAgendaLogSoportes.ESTADO)        AS ESTADO,         " +
"       MIN(tblAgendaLogSoportes.email)         AS email,          " +
"       MIN(tblAgendaLogSoportes.contacto)      AS contacto,       " +
"       MIN(tblAgendaLogSoportes.idEstadoTx)    AS idEstadoTx      " +
"FROM ctrlUsuariosRutas                                              " +
"INNER JOIN (tblAgendaLogSoportes                                    " +
"INNER JOIN tblAgendaLogVisitas                                      " +
"ON tblAgendaLogSoportes.IDLOG  = tblAgendaLogVisitas.IDLOG)         " +
"ON ctrlUsuariosRutas.idUsuario = tblAgendaLogVisitas.IDUSUARIO      " +
"GROUP BY tblAgendaLogSoportes.idCliente                             " +
"HAVING (MIN(tblAgendaLogSoportes.idEstadoTx) = 1                  " +
"AND     MIN(ctrlUsuariosRutas.idRuta)       IN (" + xIdRuta + ")) " +
"ORDER BY MIN(tblAgendaLogSoportes.NOMBRECLIENTE)                  " ;

      PreparedStatement selectStatement = null;

      try {

        // Obtiene una conexion a la base de datos, requiere Try
        connection = jdbcAccess.getConnection();

        // Prepara la sentencia de Busqueda
        selectStatement = connection.prepareStatement(selectStr);

        // Obtiene el resultset
        ResultSet rs = selectStatement.executeQuery();

        // Variable de fachada de los datos
        FachadaAgendaLogSoporteBean fachadaBean;

        while (rs.next()) {

              fachadaBean = new FachadaAgendaLogSoporteBean();

              fachadaBean.setIdLog(rs.getInt("idLog"));
              fachadaBean.setIdCliente(rs.getString("idCliente"));
              fachadaBean.setNombreCliente(rs.getString("nombreCliente"));
              fachadaBean.setTelefono(rs.getString("telefono"));
              fachadaBean.setEstado(rs.getInt("estado"));
              fachadaBean.setEmail(rs.getString("email"));
              fachadaBean.setContacto(rs.getString("contacto"));
              fachadaBean.setIdEstadoTx(rs.getInt("idEstadoTx"));

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

    // Metodo listaUnLogFachada
    public FachadaAgendaLogSoporteBean listaUnLogFachada() {

      //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
      Class tipoObjeto       = this.getClass();
      String nombreClase     = tipoObjeto.getName();

      //
      FachadaAgendaLogSoporteBean fachadaAgendaLogSoporteBean =
                                              new FachadaAgendaLogSoporteBean();

      boolean    validaOk    = false;
      Connection connection  = null;

      String selectStr =
        "SELECT tblAgendaLogSoportes.idLog,             " +
        "       tblAgendaLogSoportes.idCliente,         " +
        "       tblAgendaLogSoportes.nombreCliente,     " +
        "       tblAgendaLogSoportes.telefono,          " +
        "       tblAgendaLogSoportes.estado,            " +
        "       tblAgendaLogSoportes.email,             " +
        "       tblAgendaLogSoportes.contacto,          " +
        "       tblAgendaLogSoportes.idEstadoTx         " +
        "FROM tblAgendaLogSoportes                      " +
        "WHERE tblAgendaLogSoportes.idLog =  ( ? )      " ;

      PreparedStatement selectStatement = null;

      try {

        // Obtiene una conexion a la base de datos, requiere Try
        connection = jdbcAccess.getConnection();

        // Prepara la sentencia de Busqueda
        selectStatement = connection.prepareStatement(selectStr);

        // inicializa los valores de los parametros
        selectStatement.setInt(1,getIdLog());

        // Obtiene el resultset
        ResultSet rs = selectStatement.executeQuery();

        if (rs.next()) {

              fachadaAgendaLogSoporteBean.setIdLog(rs.getInt("idLog"));
              fachadaAgendaLogSoporteBean.setIdCliente(rs.getString("idCliente"));
              fachadaAgendaLogSoporteBean.setNombreCliente(rs.getString("nombreCliente"));
              fachadaAgendaLogSoporteBean.setTelefono(rs.getString("telefono"));
              fachadaAgendaLogSoporteBean.setEstado(rs.getInt("estado"));
              fachadaAgendaLogSoporteBean.setEmail(rs.getString("email"));
              fachadaAgendaLogSoporteBean.setContacto(rs.getString("contacto"));
              fachadaAgendaLogSoporteBean.setIdEstadoTx(rs.getInt("idEstadoTx"));

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
        return fachadaAgendaLogSoporteBean;

	  }

	  catch (Exception e)
	  {
		System.out.println("Exception In :" + nombreClase + " " + e);
	  }
      finally {

        // Cierra la conexion
       return fachadaAgendaLogSoporteBean;

      }
    }

    // Metodo listaPendientes
    public Vector listaPendientes(double xIdUsuario) {

      //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
      Class tipoObjeto       = this.getClass();
      String nombreClase     = tipoObjeto.getName();
      Vector contenedor      = new Vector();

      boolean    validaOk    = false;
      Connection connection  = null;

      String selectStr =

      "SELECT tblAgendaLogSoportes.IDLOG,            " +
      "       tblAgendaLogSoportes.idCliente,        " +
      "       tblAgendaLogSoportes.NOMBRECLIENTE,    " +
      "       tblAgendaLogSoportes.TELEFONO,         " +
      "       tblAgendaLogSoportes.ESTADO,           " +
      "       tblAgendaLogSoportes.email,            " +
      "       tblAgendaLogSoportes.contacto,         " +
      "       tblAgendaLogSoportes.idEstadoTx,       " +
      "       tblAgendaLogSoportes.idEstadoTx        " +
      "FROM tblAgendaLogVisitas                      " +
      "INNER JOIN tblAgendaLogSoportes               " +
      "ON tblAgendaLogVisitas.IDLOG =                " +
      "               tblAgendaLogSoportes.IDLOG     " +
      "WHERE tblAgendaLogVisitas.IDUSUARIO   = ( ? ) " +
      "AND   tblAgendaLogSoportes.idEstadoTx = ( ? ) " +
      "ORDER BY tblAgendaLogVisitas.FECHAVISITA      " ;

      PreparedStatement selectStatement = null;

      try {

        // Obtiene una conexion a la base de datos, requiere Try
        connection = jdbcAccess.getConnection();

        // Prepara la sentencia de Busqueda
        selectStatement = connection.prepareStatement(selectStr);

       // inicializa los valores de los parametros
       selectStatement.setDouble(1,xIdUsuario);
       selectStatement.setInt(2,getIdEstadoTx());

        // Obtiene el resultset
        ResultSet rs = selectStatement.executeQuery();

        // Variable de fachada de los datos
        FachadaAgendaLogSoporteBean fachadaBean;

        while (rs.next()) {

              fachadaBean = new FachadaAgendaLogSoporteBean();

              fachadaBean.setIdLog(rs.getInt("idLog"));
              fachadaBean.setIdCliente(rs.getString("idCliente"));
              fachadaBean.setNombreCliente(rs.getString("nombreCliente"));
              fachadaBean.setTelefono(rs.getString("telefono"));
              fachadaBean.setEstado(rs.getInt("estado"));
              fachadaBean.setEmail(rs.getString("email"));
              fachadaBean.setContacto(rs.getString("contacto"));
              fachadaBean.setIdEstadoTx(rs.getInt("idEstadoTx"));

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

}