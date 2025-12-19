package com.solucionesweb.losbeans.colaboraciones;

// Importa la clase helper JDBCAccess que contiene los accesos a la base de datos
import com.solucionesweb.lasayudas.JDBCAccess;

// Importa el bean de utilidades
import com.solucionesweb.losbeans.utilidades.IConstantes;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaColaboraAgendaControlBean;

// Importa los paquetes de java
import java.sql.*;
import java.io.*;
import java.util.*;
import java.io.IOException.*;

// Importa los paquetes de javax
import javax.naming.*;

public class ColaboraAgendaControlBean extends FachadaColaboraAgendaControlBean
                                          implements Serializable, IConstantes {

    // Nombre JNDI del Data Source que esta clase requiere
    private static final String DATA_SOURCE_NAME = "CommerceAccess";

	// Clase helper usada para acceder a la base de datos
	private JDBCAccess jdbcAccess;

	// Metodo constructor por defecto sin parametros
    public ColaboraAgendaControlBean() {

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
        FachadaColaboraAgendaControlBean fachadaBean;

        while (rs.next()) {

              fachadaBean = new FachadaColaboraAgendaControlBean();

              fachadaBean.setIdCliente(rs.getString("idCliente"));
              fachadaBean.setIdUsuario(rs.getInt("idUsuario"));
              fachadaBean.setIdSucursal(rs.getString("strIdSucursal"));
              fachadaBean.setIdPeriodo(rs.getInt("idPeriodo"));
              fachadaBean.setFechaVisitaStr(
                                   rs.getString("fechaVisita").substring(0,11));
              fachadaBean.setEstado(rs.getInt("estado"));

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

    // Metodo listaVendedorxFechaVisita
    public Vector listaVendedorxFechaVisita() {

      //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
      Class tipoObjeto       = this.getClass();
      String nombreClase     = tipoObjeto.getName();
      Vector contenedor      = new Vector();

      boolean    validaOk    = false;
      Connection connection  = null;

      String selectStr =
      "SELECT [Customer_Master].CUSTID_23     " +
      "                          AS idCliente," +
      "       [Customer_Master].NAME_23       " +
      "                      AS nombreCliente," +
      "       '00' AS strIdSucursal,          " +
      "       [Customer_Master].NAME_23       " +
      "                      AS nombreempresa," +
      "       [Customer_Master].ADDR1_23 +    " +
      "       [Customer_Master].ADDR2_23      " +
      "                          AS direccion," +
      "       [Customer_Master].CITY_23       " +
      "                    AS ciudadDireccion," +
      "       [Customer_Master].STATE_23      " +
      "                       AS departamento," +
      "       [Customer_Master].PHONE_23      " +
      "                           AS telefono," +
      "       tblagendacontrol.idUsuario,     " +
      "       tblagendacontrol.idPeriodo,     " +
      "       tblagendacontrol.fechaVisita,   " +
      "       tblagendacontrol.estado         " +
      "FROM  [MAXCAM].[dbo].[Customer_Master]," +
      "       tblagendacontrol                " +
      "WHERE [Customer_Master].CUSTID_23=     " +
      "            tblagendacontrol.idcliente " ;

      PreparedStatement selectStatement = null;

      try {

        // Obtiene una conexion a la base de datos, requiere Try
        connection = jdbcAccess.getConnection();

        // Prepara la sentencia de Busqueda
        selectStatement = connection.prepareStatement(selectStr);



        // Obtiene el resultset
        ResultSet rs = selectStatement.executeQuery();

        // Variable de fachada de los datos
        FachadaColaboraAgendaControlBean fachadaBean;

        while (rs.next()) {

              fachadaBean = new FachadaColaboraAgendaControlBean();

              fachadaBean.setIdCliente(rs.getString("idcliente"));
              fachadaBean.setNombreCliente(rs.getString("nombrecliente"));
              fachadaBean.setIdSucursal(rs.getString("strIdSucursal"));
              fachadaBean.setNombreEmpresa(rs.getString("nombreempresa"));
              fachadaBean.setDireccion(rs.getString("direccion"));
              fachadaBean.setCiudadDireccion(rs.getString("ciudaddireccion"));
              fachadaBean.setDepartamento(rs.getString("departamento"));
              fachadaBean.setTelefono(rs.getString("telefono"));
              fachadaBean.setIdUsuario(rs.getString("idUsuario"));
              fachadaBean.setIdPeriodo(rs.getInt("idPeriodo"));
              fachadaBean.setFechaVisitaStr(
                                   rs.getString("fechaVisita").substring(0,11));
              fachadaBean.setEstado(rs.getInt("estado"));

              //
              contenedor.add(fachadaBean);
        }

        // Cierra ResultSet
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

        // Cierra connection
        jdbcAccess.cleanup(connection, selectStatement,null);
        return contenedor;

      }
    }

    // Metodo listaVariosClientes
    public Vector listaVariosClientes() {

      //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
      Class tipoObjeto       = this.getClass();
      String nombreClase     = tipoObjeto.getName();
      Vector contenedor      = new Vector();

      boolean    validaOk    = false;
      Connection connection  = null;

      String selectStr =
      "SELECT [Customer_Master].CUSTID_23     " +
      "                          AS idCliente," +
      "       [Customer_Master].NAME_23       " +
      "                      AS nombreCliente," +
      "       '00' AS strIdSucursal,          " +
      "       [Customer_Master].NAME_23       " +
      "                      AS nombreempresa," +
      "       [Customer_Master].ADDR1_23 +    " +
      "       [Customer_Master].ADDR2_23      " +
      "                          AS direccion," +
      "       [Customer_Master].CITY_23       " +
      "                    AS ciudadDireccion," +
      "       [Customer_Master].STATE_23      " +
      "                       AS departamento," +
      "       [Customer_Master].PHONE_23      " +
      "                           AS telefono," +
      "       '                 ' AS email,   " +
      "       [Customer_Master].PHONE_23      " +
      "                           AS fax,     " +
      "       [Customer_Master].cntct_23      " +
      "                           AS contacto " +
      "FROM [MAXCAM].[dbo].[Customer_Master]  " +
      "WHERE [Customer_Master].CUSTID_23      " +
      "     IN (" + getIdClienteCadena() + ") " +
      "ORDER BY [Customer_Master].NAME_23     " ;

      PreparedStatement selectStatement = null;

      try {

        // Obtiene una conexion a la base de datos, requiere Try
        connection = jdbcAccess.getConnection();

        // Prepara la sentencia de Busqueda
        selectStatement = connection.prepareStatement(selectStr);


        // Obtiene el resultset
        ResultSet rs = selectStatement.executeQuery();

        // Variable de fachada de los datos
        FachadaColaboraAgendaControlBean fachadaBean;

        while (rs.next()) {

              fachadaBean = new FachadaColaboraAgendaControlBean();

              fachadaBean.setIdCliente(rs.getString("idcliente"));
              fachadaBean.setNombreCliente(rs.getString("nombrecliente"));
              fachadaBean.setIdSucursal(rs.getString("strIdSucursal"));
              fachadaBean.setNombreEmpresa(rs.getString("nombreempresa"));
              fachadaBean.setDireccion(rs.getString("direccion"));
              fachadaBean.setCiudadDireccion(rs.getString("ciudaddireccion"));
              fachadaBean.setDepartamento(rs.getString("departamento"));
              fachadaBean.setTelefono(rs.getString("telefono"));
              fachadaBean.setEmail(rs.getString("email"));
              fachadaBean.setFax(rs.getString("fax"));
              fachadaBean.setContacto(rs.getString("contacto"));

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

       // Cierra connection
       jdbcAccess.cleanup(connection, selectStatement,null);
       return contenedor;

      }
    }

    // Metodo  listaClienteSucursalNit
    public Vector listaClienteSucursalNit() {

      //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
      Class tipoObjeto       = this.getClass();
      String nombreClase     = tipoObjeto.getName();
      Vector contenedor      = new Vector();

      boolean    validaOk    = false;
      Connection connection  = null;

      String selectStr =
      "SELECT terceros.TERCERO             " +
      "         AS idCliente,              " +
      "       terceros.TER_RAZONS          " +
      "         AS nombreCliente,          " +
      "       '001' AS strIdSucursal,      " +
      "       terceros.TER_RAZONS          " +
      "         AS nombreEmpresa,          " +
      "       terceros.TER_DIRECC          " +
      "         AS direccion,              " +
      "       terceros.TER_CIUDAD          " +
      "         AS ciudadDireccion,        " +
      "       terceros.TER_PAIS            " +
      "         AS departamento,           " +
      "       terceros.TER_TELEFO          " +
      "         AS telefono,               " +
      "       01 AS estado                 " +
      "FROM terceros                       " +
      "WHERE VAL(terceros.tercero) = ( ? ) " ;
      PreparedStatement selectStatement = null;

      try {

        // Obtiene una conexion a la base de datos, requiere Try
        connection = jdbcAccess.getConnection();

        // Prepara la sentencia de Busqueda
        selectStatement = connection.prepareStatement(selectStr);

        // inicializa los valores de los parametros
        selectStatement.setString(1,getIdCliente());


        // Obtiene el resultset
        ResultSet rs = selectStatement.executeQuery();

        // Variable de fachada de los datos
        FachadaColaboraAgendaControlBean fachadaBean;

        while (rs.next()) {

              fachadaBean = new FachadaColaboraAgendaControlBean();

              fachadaBean.setIdCliente(rs.getString("idcliente"));
              fachadaBean.setNombreCliente(rs.getString("nombrecliente"));
              fachadaBean.setIdSucursal(rs.getString("strIdSucursal"));
              fachadaBean.setNombreEmpresa(rs.getString("nombreempresa"));
              fachadaBean.setDireccion(rs.getString("direccion"));
              fachadaBean.setCiudadDireccion(rs.getString("ciudaddireccion"));
              fachadaBean.setDepartamento(rs.getString("departamento"));
              fachadaBean.setTelefono(rs.getString("telefono"));
              fachadaBean.setEstado(rs.getInt("estado"));

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

       // Cierra connection
       jdbcAccess.cleanup(connection, selectStatement,null);
       return contenedor;

      }
    }

    // MetodolistaSucursales
    public Vector listaSucursales() {

      //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
      Class tipoObjeto       = this.getClass();
      String nombreClase     = tipoObjeto.getName();
      Vector contenedor      = new Vector();

      boolean    validaOk    = false;
      Connection connection  = null;

      String selectStr =
      "SELECT [Customer_Master].CUSTID_23           " +
      "                          AS idCliente,      " +
      "       [Customer_Master].NAME_23             " +
      "                      AS nombreCliente,      " +
      "       '00' AS strIdSucursal,                " +
      "       [Customer_Master].NAME_23             " +
      "                      AS nombreempresa,      " +
      "       [Customer_Master].ADDR1_23 +          " +
      "       [Customer_Master].ADDR2_23            " +
      "                          AS direccion,      " +
      "       [Customer_Master].CITY_23             " +
      "                    AS ciudadDireccion,      " +
      "       [Customer_Master].STATE_23            " +
      "                       AS departamento,      " +
      "       [Customer_Master].PHONE_23            " +
      "                           AS telefono,      " +
      "       '                 ' AS email,         " +
      "       [Customer_Master].PHONE_23            " +
      "                           AS fax,           " +
      "       [Customer_Master].cntct_23            " +
      "                           AS contacto       " +
      "FROM [MAXCAM].[dbo].[Customer_Master]        " +
      "WHERE [Customer_Master].CUSTID_23 = (?)      " ;

      PreparedStatement selectStatement = null;

      try {

        // Obtiene una conexion a la base de datos, requiere Try
        connection = jdbcAccess.getConnection();

        // Prepara la sentencia de Busqueda
        selectStatement = connection.prepareStatement(selectStr);

        // inicializa los valores de los parametros
        selectStatement.setString(1,getIdCliente());

        // Obtiene el resultset
        ResultSet rs = selectStatement.executeQuery();

        // Variable de fachada de los datos
        FachadaColaboraAgendaControlBean fachadaBean;

        while (rs.next()) {

              fachadaBean = new FachadaColaboraAgendaControlBean();

              fachadaBean.setIdCliente(rs.getString("idcliente"));
              fachadaBean.setNombreCliente(rs.getString("nombrecliente"));
              fachadaBean.setIdSucursal(rs.getString("strIdSucursal"));
              fachadaBean.setNombreEmpresa(rs.getString("nombreempresa"));
              fachadaBean.setDireccion(rs.getString("direccion"));
              fachadaBean.setCiudadDireccion(rs.getString("ciudaddireccion"));
              fachadaBean.setDepartamento(rs.getString("departamento"));
              fachadaBean.setTelefono(rs.getString("telefono"));
              fachadaBean.setEmail(rs.getString("email"));
              fachadaBean.setFax(rs.getString("fax"));
              fachadaBean.setContacto(rs.getString("contacto"));

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

       // Cierra connection
       jdbcAccess.cleanup(connection, selectStatement,null);
       return contenedor;

      }
    }

    // Metodo listaUnaSucursal
    public FachadaColaboraAgendaControlBean listaUnaSucursal() {

      //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
      Class tipoObjeto       = this.getClass();
      String nombreClase     = tipoObjeto.getName();
      Vector contenedor      = new Vector();

      boolean    validaOk    = false;
      Connection connection  = null;

      //
      FachadaColaboraAgendaControlBean fachadaBean
                                       = new FachadaColaboraAgendaControlBean();

      String selectStr =
      "SELECT [Customer_Master].CUSTID_23           " +
      "                          AS idCliente,      " +
      "       [Customer_Master].NAME_23             " +
      "                      AS nombreCliente,      " +
      "       '00' AS strIdSucursal,                " +
      "       [Customer_Master].NAME_23             " +
      "                      AS nombreempresa,      " +
      "       [Customer_Master].ADDR1_23 +          " +
      "       [Customer_Master].ADDR2_23            " +
      "                          AS direccion,      " +
      "       [Customer_Master].CITY_23             " +
      "                    AS ciudadDireccion,      " +
      "       [Customer_Master].STATE_23            " +
      "                       AS departamento,      " +
      "       [Customer_Master].PHONE_23            " +
      "                           AS telefono,      " +
      "       '                 ' AS email,         " +
      "       [Customer_Master].PHONE_23            " +
      "                           AS fax,           " +
      "       [Customer_Master].cntct_23            " +
      "                           AS contacto,      " +
      "       [Code_Master].DESC_36                 " +
      "                           AS formaPago      " +
      "FROM   [MAXCAM].[dbo].[Customer_Master],     " +
      "       [MAXCAM].[dbo].[Code_Master]          " +
      "WHERE [Customer_Master].CUSTID_23 = (?)      " +
      "AND    [Customer_Master].terms_23 =          " +
      "                       [Code_Master].CODE_36 " +
      "AND   [Code_Master].CDEKEY_36     = 'TERM'   " ;

      PreparedStatement selectStatement = null;

      try {

        // Obtiene una conexion a la base de datos, requiere Try
        connection = jdbcAccess.getConnection();

        // Prepara la sentencia de Busqueda
        selectStatement = connection.prepareStatement(selectStr);

        // inicializa los valores de los parametros
        selectStatement.setString(1,getIdCliente());
        //selectStatement.setString(2,getIdSucursal());

        // Obtiene el resultset
        ResultSet rs = selectStatement.executeQuery();

        if (rs.next()) {

              fachadaBean.setIdCliente(rs.getString("idcliente"));
              fachadaBean.setNombreCliente(rs.getString("nombrecliente"));
              fachadaBean.setIdSucursal(rs.getString("strIdSucursal"));
              fachadaBean.setNombreEmpresa(rs.getString("nombreempresa"));
              fachadaBean.setDireccion(rs.getString("direccion"));
              fachadaBean.setCiudadDireccion(rs.getString("ciudaddireccion"));
              fachadaBean.setDepartamento(rs.getString("departamento"));
              fachadaBean.setTelefono(rs.getString("telefono"));
              fachadaBean.setEmail(rs.getString("email"));
              fachadaBean.setFax(rs.getString("fax"));
              fachadaBean.setContacto(rs.getString("contacto"));
              fachadaBean.setFormaPago(rs.getString("formaPago"));

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

       // Cierra connection
       jdbcAccess.cleanup(connection, selectStatement,null);
       return fachadaBean;

      }
    }

    // Metodo listaVendedorxFechaVisitaEstado
    public Vector listaVendedorxFechaVisitaEstado() {

      //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
      Class tipoObjeto       = this.getClass();
      String nombreClase     = tipoObjeto.getName();
      Vector contenedor      = new Vector();

      boolean    validaOk    = false;
      Connection connection  = null;

      String selectStr =
      "SELECT [Customer_Master].CUSTID_23           " +
      "                          AS idCliente,      " +
      "       [Customer_Master].NAME_23             " +
      "                      AS nombreCliente,      " +
      "       '00' AS strIdSucursal,                " +
      "       [Customer_Master].NAME_23             " +
      "                      AS nombreempresa,      " +
      "       [Customer_Master].ADDR1_23 +          " +
      "       [Customer_Master].ADDR2_23            " +
      "                          AS direccion,      " +
      "       [Customer_Master].CITY_23             " +
      "                    AS ciudadDireccion,      " +
      "       [Customer_Master].STATE_23            " +
      "                       AS departamento,      " +
      "       [Customer_Master].PHONE_23            " +
      "                           AS telefono,      " +
      "       tblAgendaControl.idUsuario,           " +
      "       tblAgendaControl.idPeriodo,           " +
      "       tblAgendaControl.fechaVisita,         " +
      "       tblAgendaControlEstados.nombreEstado  " +
      "FROM   [MAXCAM].[dbo].[Customer_Master],     " +
      "       tblAgendaControl,                     " +
      "       tblAgendaControlEstados               " +
      "WHERE  [Customer_Master].CUSTID_23      =    " +
      "              tblAgendaControl.idCliente     " +
      "AND    tblAgendaControl.idUsuario       =    " +
      "                 " + getIdUsuario() + "      " +
      "AND    tblAgendaControl.fechaVisita     =    " +
      "         '" + getFechaVisitaSqlServer() + "' " +
      "AND    tblAgendaControl.estado          =    " +
      "             tblAgendaControlEstados.estado  " ;

      PreparedStatement selectStatement = null;

      try {

        // Obtiene una conexion a la base de datos, requiere Try
        connection = jdbcAccess.getConnection();

        // Prepara la sentencia de Busqueda
        selectStatement = connection.prepareStatement(selectStr);



        // Obtiene el resultset
        ResultSet rs = selectStatement.executeQuery();

        // Variable de fachada de los datos
        FachadaColaboraAgendaControlBean fachadaBean;

        while (rs.next()) {

              fachadaBean = new FachadaColaboraAgendaControlBean();

              fachadaBean.setIdCliente(rs.getString("idcliente"));
              fachadaBean.setNombreCliente(rs.getString("nombrecliente"));
              fachadaBean.setIdSucursal(rs.getString("strIdSucursal"));
              fachadaBean.setNombreEmpresa(rs.getString("nombreempresa"));
              fachadaBean.setDireccion(rs.getString("direccion"));
              fachadaBean.setCiudadDireccion(rs.getString("ciudaddireccion"));
              fachadaBean.setDepartamento(rs.getString("departamento"));
              fachadaBean.setTelefono(rs.getString("telefono"));
              fachadaBean.setIdUsuario(rs.getString("idUsuario"));
              fachadaBean.setIdPeriodo(rs.getInt("idPeriodo"));
              fachadaBean.setFechaVisitaStr(rs.getString("fechaVisita").substring(0,10));
              fachadaBean.setNombreEstado(rs.getString("nombreEstado"));

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

       // Cierra connection
       jdbcAccess.cleanup(connection, selectStatement,null);
       return contenedor;

      }
    }

    // Metodo listaVisita
    public Vector listaVisita(String xFechaInicial,
                              String xFechaFinal) {

      //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
      Class tipoObjeto       = this.getClass();
      String nombreClase     = tipoObjeto.getName();
      Vector contenedor      = new Vector();

      boolean    validaOk    = false;
      Connection connection  = null;

      String selectStr =
      "SELECT [Customer_Master].CUSTID_23           " +
      "                          AS idCliente,      " +
      "       [Customer_Master].NAME_23             " +
      "                      AS nombreCliente,      " +
      "       '00' AS strIdSucursal,                " +
      "       [Customer_Master].NAME_23             " +
      "                      AS nombreempresa,      " +
      "       [Customer_Master].ADDR1_23 +          " +
      "       [Customer_Master].ADDR2_23            " +
      "                          AS direccion,      " +
      "       [Customer_Master].CITY_23             " +
      "                    AS ciudadDireccion,      " +
      "       [Customer_Master].STATE_23            " +
      "                       AS departamento,      " +
      "       [Customer_Master].PHONE_23            " +
      "                           AS telefono,      " +
      "       tblAgendaControl.idUsuario,           " +
      "       tblAgendaControl.idPeriodo,           " +
      "       tblAgendaControl.fechaVisita,         " +
      "       tblAgendaControlEstados.nombreEstado  " +
      "FROM   [MAXCAM].[dbo].[Customer_Master],     " +
      "       tblAgendaControl,                     " +
      "       tblAgendaControlEstados               " +
      "WHERE  [Customer_Master].CUSTID_23      =    " +
      "              tblAgendaControl.idCliente     " +
      "AND    tblAgendaControl.idUsuario       =    " +
      "                 " + getIdUsuario() + "      " +
      "AND    tblAgendaControl.fechaVisita    >=    " +
      "            '" + xFechaInicial          + "' " +
      "AND    tblAgendaControl.fechaVisita    <=    " +
      "         '" + xFechaFinal               + "' " +
      "AND    tblAgendaControl.estado          =    " +
      "             tblAgendaControlEstados.estado  " ;
 
      PreparedStatement selectStatement = null;

      try {

        // Obtiene una conexion a la base de datos, requiere Try
        connection = jdbcAccess.getConnection();

        // Prepara la sentencia de Busqueda
        selectStatement = connection.prepareStatement(selectStr);



        // Obtiene el resultset
        ResultSet rs = selectStatement.executeQuery();

        // Variable de fachada de los datos
        FachadaColaboraAgendaControlBean fachadaBean;

        while (rs.next()) {

              fachadaBean = new FachadaColaboraAgendaControlBean();

              fachadaBean.setIdCliente(rs.getString("idcliente"));
              fachadaBean.setNombreCliente(rs.getString("nombrecliente"));
              fachadaBean.setIdSucursal(rs.getString("strIdSucursal"));
              fachadaBean.setNombreEmpresa(rs.getString("nombreempresa"));
              fachadaBean.setDireccion(rs.getString("direccion"));
              fachadaBean.setCiudadDireccion(rs.getString("ciudaddireccion"));
              fachadaBean.setDepartamento(rs.getString("departamento"));
              fachadaBean.setTelefono(rs.getString("telefono"));
              fachadaBean.setIdUsuario(rs.getString("idUsuario"));
              fachadaBean.setIdPeriodo(rs.getInt("idPeriodo"));
              fachadaBean.setFechaVisitaStr(rs.getString("fechaVisita").substring(0,10));
              fachadaBean.setNombreEstado(rs.getString("nombreEstado"));

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

       // Cierra connection
       jdbcAccess.cleanup(connection, selectStatement,null);
       return contenedor;

      }
    }


    // Metodo listaClienteSucursal
    public Vector listaClienteSucursal() {

      //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
      Class tipoObjeto       = this.getClass();
      String nombreClase     = tipoObjeto.getName();
      Vector contenedor      = new Vector();

      boolean    validaOk    = false;
      Connection connection  = null;

      String selectStr =
      "SELECT [Customer_Master].CUSTID_23           " +
      "                          AS idCliente,      " +
      "       [Customer_Master].NAME_23             " +
      "                      AS nombreCliente,      " +
      "       '00' AS strIdSucursal,                " +
      "       [Customer_Master].NAME_23             " +
      "                      AS nombreempresa,      " +
      "       [Customer_Master].ADDR1_23 +          " +
      "       [Customer_Master].ADDR2_23            " +
      "                          AS direccion,      " +
      "       [Customer_Master].CITY_23             " +
      "                    AS ciudadDireccion,      " +
      "       [Customer_Master].STATE_23            " +
      "                       AS departamento,      " +
      "       [Customer_Master].PHONE_23            " +
      "                           AS telefono,      " +
      "       tblAgendaControl.idUsuario,           " +
      "       tblAgendaControl.idPeriodo,           " +
      "       tblAgendaControl.fechaVisita,         " +
      "       tblAgendaControlEstados.nombreEstado  " +
      "FROM   [MAXCAM].[dbo].[Customer_Master],     " +
      "       tblAgendaControl,                     " +
      "       tblAgendaControlEstados               " +
      "WHERE  [Customer_Master].CUSTID_23      =    " +
      "              tblAgendaControl.idCliente     " +
      "AND    tblAgendaControl.idCliente       =    " +
      "                 " + getIdCliente() + "      " +
      "AND    tblAgendaControl.estado          =    " +
      "             tblAgendaControlEstados.estado  " ;


      PreparedStatement selectStatement = null;

      try {

        // Obtiene una conexion a la base de datos, requiere Try
        connection = jdbcAccess.getConnection();

        // Prepara la sentencia de Busqueda
        selectStatement = connection.prepareStatement(selectStr);



        // Obtiene el resultset
        ResultSet rs = selectStatement.executeQuery();

        // Variable de fachada de los datos
        FachadaColaboraAgendaControlBean fachadaBean;

        while (rs.next()) {

              fachadaBean = new FachadaColaboraAgendaControlBean();

              fachadaBean.setIdCliente(rs.getString("idcliente"));
              fachadaBean.setNombreCliente(rs.getString("nombrecliente"));
              fachadaBean.setIdSucursal(rs.getString("strIdSucursal"));
              fachadaBean.setNombreEmpresa(rs.getString("nombreempresa"));
              fachadaBean.setDireccion(rs.getString("direccion"));
              fachadaBean.setCiudadDireccion(rs.getString("ciudaddireccion"));
              fachadaBean.setDepartamento(rs.getString("departamento"));
              fachadaBean.setTelefono(rs.getString("telefono"));
              fachadaBean.setIdUsuario(rs.getString("idUsuario"));
              fachadaBean.setIdPeriodo(rs.getInt("idPeriodo"));
              fachadaBean.setFechaVisitaStr(rs.getString("fechaVisita").substring(0,10));
              fachadaBean.setEstado(rs.getString("estado"));

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

       // Cierra connection
       jdbcAccess.cleanup(connection, selectStatement,null);
       return contenedor;

      }
    }

    // Metodo listaClienteSucursalFechaVisita
    public Vector listaClienteSucursalFechaVisita() {

      //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
      Class tipoObjeto       = this.getClass();
      String nombreClase     = tipoObjeto.getName();
      Vector contenedor      = new Vector();

      boolean    validaOk    = false;
      Connection connection  = null;

      String selectStr =
      "SELECT [Customer_Master].CUSTID_23           " +
      "                          AS idCliente,      " +
      "       [Customer_Master].NAME_23             " +
      "                      AS nombreCliente,      " +
      "       '00' AS strIdSucursal,                " +
      "       [Customer_Master].NAME_23             " +
      "                      AS nombreempresa,      " +
      "       [Customer_Master].ADDR1_23 +          " +
      "       [Customer_Master].ADDR2_23            " +
      "                          AS direccion,      " +
      "       [Customer_Master].CITY_23             " +
      "                    AS ciudadDireccion,      " +
      "       [Customer_Master].STATE_23            " +
      "                       AS departamento,      " +
      "       [Customer_Master].PHONE_23            " +
      "                           AS telefono,      " +
      "       tblAgendaControl.idUsuario,           " +
      "       tblAgendaControl.idPeriodo,           " +
      "       tblAgendaControl.fechaVisita,         " +
      "       tblAgendaControlEstados.estado        " +
      "FROM   [MAXCAM].[dbo].[Customer_Master],     " +
      "       tblAgendaControl,                     " +
      "       tblAgendaControlEstados               " +
      "WHERE  [Customer_Master].CUSTID_23      =    " +
      "              tblAgendaControl.idCliente     " +
      "AND    tblAgendaControl.idCliente       =    " +
      "                '" + getIdCliente() + "'     " +
      "AND    tblAgendaControl.fechaVisita     =    " +
      "        '" + getFechaVisitaSqlServer() +  "' " +
      "AND    tblAgendaControl.estado          =    " +
      "             tblAgendaControlEstados.estado  " ;



      PreparedStatement selectStatement = null;

      try {

        // Obtiene una conexion a la base de datos, requiere Try
        connection = jdbcAccess.getConnection();

        // Prepara la sentencia de Busqueda
        selectStatement = connection.prepareStatement(selectStr);



        // Obtiene el resultset
        ResultSet rs = selectStatement.executeQuery();

        // Variable de fachada de los datos
        FachadaColaboraAgendaControlBean fachadaBean;

        while (rs.next()) {

              fachadaBean = new FachadaColaboraAgendaControlBean();

              fachadaBean.setIdCliente(rs.getString("idCliente"));
              fachadaBean.setNombreCliente(rs.getString("nombreCliente"));
              fachadaBean.setIdSucursal(rs.getString("strIdSucursal"));
              fachadaBean.setNombreEmpresa(rs.getString("nombreempresa"));
              fachadaBean.setDireccion(rs.getString("direccion"));
              fachadaBean.setCiudadDireccion(rs.getString("ciudadDireccion"));
              fachadaBean.setDepartamento(rs.getString("departamento"));
              fachadaBean.setTelefono(rs.getString("telefono"));
              fachadaBean.setIdUsuario(rs.getString("idUsuario"));
              fachadaBean.setIdPeriodo(rs.getInt("idPeriodo"));
              fachadaBean.setFechaVisitaStr(rs.getString("fechaVisita").substring(0,10));
              fachadaBean.setEstado(rs.getString("estado"));

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

       // Cierra connection
       jdbcAccess.cleanup(connection, selectStatement,null);
       return contenedor;

      }
    }
}