package com.solucionesweb.losbeans.negocio;

// Importa la clase helper JDBCAccess que contiene los accesos a la base de datos
import com.solucionesweb.lasayudas.JDBCAccess;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaEstadoReferenciaBean;
// Importa los paquetes de java
import java.sql.*;
import java.io.*;
import java.util.*;
import java.util.Date;
import java.io.IOException.*;

// Importa los paquetes de javax
import javax.sql.*;
import javax.naming.*;

public class EstadoReferenciaBean extends FachadaEstadoReferenciaBean
                                                       implements Serializable {

  	// Nombre JNDI del Data Source que esta clase requiere
    private static final String DATA_SOURCE_NAME = "CommerceAccess";

	// Clase helper usada para acceder a la base de datos
	private JDBCAccess jdbcAccess;

	// Metodo constructor por defecto sin parametros
    public EstadoReferenciaBean() {

  	// Al instanciar el Bean establece la conexion a la base de datos
	   jdbcAccess = new JDBCAccess(DATA_SOURCE_NAME);
    }

    // Propiedades
    private int estado;
    private String nombreEstado;
    private int estadoRegistro;

    // Metodos
    public void setEstado( int estado )
    {
        this.estado = estado ;
    }

    public int getEstado()
    {
        return estado;
    }

    public void setEstado( String estadoStr )
    {
        this.estado = new Integer(estadoStr).intValue() ;
    }

    public String getEstadoStr()
    {
        return new Integer(estado).toString();
    }

    public String getNombreEstado()
    {
        return nombreEstado;
    }

    public void setNombreEstado( String nombreEstado )
    {
        this.nombreEstado = nombreEstado.trim();
    }

    public int getEstadoRegistro()
    {
        return estadoRegistro;
    }

    public String getEstadoRegistroStr()
    {
        return new Integer(estadoRegistro).toString();
    }

    public void setEstadoRegistro( int estadoRegistro )
    {
        this.estadoRegistro = estadoRegistro ;
    }

    public void setEstadoRegistro( String estadoRegistroStr )
    {
        this.estadoRegistro = new Integer(estadoRegistroStr).intValue() ;
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
      "SELECT tblEstadosReferencias.ESTADO,         " +
      "       tblEstadosReferencias.NOMBREESTADO,   " +
      "       tblEstadosReferencias.ESTADOREGISTRO  " +
      "FROM   tblEstadosReferencias                 " ;

      PreparedStatement selectStatement = null;

      try {

        // Obtiene una conexion a la base de datos, requiere Try
        connection = jdbcAccess.getConnection();

        // Prepara la sentencia de Busqueda
        selectStatement = connection.prepareStatement(selectStr);

        // Obtiene el resultset
        ResultSet rs = selectStatement.executeQuery();

        // Variable de fachada de los datos
        FachadaEstadoReferenciaBean fachadaBean;

        while (rs.next()) {

              fachadaBean = new FachadaEstadoReferenciaBean();
              fachadaBean.setEstado(rs.getInt("estado"));
              fachadaBean.setNombreEstado(rs.getString("nombreEstado"));
              fachadaBean.setEstadoRegistro(rs.getInt("estadoRegistro"));
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

    // listaUnEstadoDcto
    public Vector listaUnEstadoDcto() {

      //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
      Class tipoObjeto       = this.getClass();
      String nombreClase     = tipoObjeto.getName();
      Vector contenedor      = new Vector();

      boolean    validaOk    = false;
      Connection connection  = null;

      String selectStr =
      "SELECT tblEstadosReferencias.ESTADO,         " +
      "       tblEstadosReferencias.NOMBREESTADO,   " +
      "       tblEstadosReferencias.ESTADOREGISTRO  " +
      "FROM   tblEstadosReferencias                 " +
      "WHERE tblEstadosReferencias.estado = ( ? )   " ;

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
        FachadaEstadoReferenciaBean fachadaBean;

        while (rs.next()) {

              fachadaBean = new FachadaEstadoReferenciaBean();
              fachadaBean.setEstado(rs.getInt("estado"));
              fachadaBean.setNombreEstado(rs.getString("nombreEstado"));
              fachadaBean.setEstadoRegistro(rs.getInt("estadoRegistro"));
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

    // listaUnEstadoDctoFachada
    public FachadaEstadoReferenciaBean listaUnEstadoDctoFachada() {

      //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
      Class tipoObjeto       = this.getClass();
      String nombreClase     = tipoObjeto.getName();

      //
      FachadaEstadoReferenciaBean
                fachadaEstadoReferenciaBean = new FachadaEstadoReferenciaBean();

      boolean    validaOk    = false;
      Connection connection  = null;

      String selectStr =
      "SELECT tblEstadosReferencias.ESTADO,         " +
      "       tblEstadosReferencias.NOMBREESTADO,   " +
      "       tblEstadosReferencias.ESTADOREGISTRO  " +
      "FROM   tblEstadosReferencias                 " +
      "WHERE tblEstadosReferencias.estado = ( ? )   " ;

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

              fachadaEstadoReferenciaBean.setEstado(rs.getInt("estado"));
              fachadaEstadoReferenciaBean.setNombreEstado(rs.getString("nombreEstado"));
              fachadaEstadoReferenciaBean.setEstadoRegistro(rs.getInt("estadoRegistro"));

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
        return fachadaEstadoReferenciaBean;

	  }

	  catch (Exception e)
	  {
		System.out.println("Exception In :" + nombreClase + " " + e);
	  }
      finally {

        // Cierra la conexion
       return fachadaEstadoReferenciaBean;

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
      "SELECT tblEstadosReferencias.ESTADO,         " +
      "       tblEstadosReferencias.NOMBREESTADO,   " +
      "       tblEstadosReferencias.ESTADOREGISTRO, " +
      "       01 AS orden                           " +
      "FROM   tblEstadosReferencias                 " +
      "WHERE tblEstadosReferencias.estado = ( ? )   " +
      "UNION                                        " +
      "SELECT tblEstadosReferencias.ESTADO,         " +
      "       tblEstadosReferencias.NOMBREESTADO,   " +
      "       tblEstadosReferencias.ESTADOREGISTRO, " +
      "       02 AS orden                           " +
      "FROM   tblEstadosReferencias                 " +
      "WHERE tblEstadosReferencias.estado <> ( ? )  " +
      "ORDER BY 4,2                                 " ;

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
        FachadaEstadoReferenciaBean fachadaBean;

        while (rs.next()) {

              fachadaBean = new FachadaEstadoReferenciaBean();
              fachadaBean.setEstado(rs.getInt("estado"));
              fachadaBean.setNombreEstado(rs.getString("nombreEstado"));
              fachadaBean.setEstadoRegistro(rs.getInt("estadoRegistro"));
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

    // seleccionaUnEstadoVisita
    public boolean seleccionaUnEstadoVisita() {

      //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
      Class tipoObjeto       = this.getClass();
      String nombreClase     = tipoObjeto.getName();
      Vector contenedor      = new Vector();

      boolean    validaOk    = false;
      Connection connection  = null;

      String selectStr =
      "SELECT tblEstadosReferencias.ESTADO,         " +
      "       tblEstadosReferencias.NOMBREESTADO,   " +
      "       tblEstadosReferencias.ESTADOREGISTRO  " +
      "FROM   tblEstadosReferencias                 " +
      "WHERE tblEstadosReferencias.estado = ( ? )   " ;

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

        // Cierra la conexion
       return validaOk;

      }
    }

}