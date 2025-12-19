package com.solucionesweb.losbeans.negocio;

// Importa la clase helper JDBCAccess que contiene los accesos a la base de datos
import com.solucionesweb.lasayudas.JDBCAccess;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaCategoriaBean;

// Importa los paquetes de java
import java.sql.*;
import java.io.*;
import java.util.*;
import java.io.IOException.*;

//
import org.kxml.*;
import org.kxml.parser.*;

// Importa los paquetes de javax
import javax.naming.*;

public class TipoOrdenSubcuentaBean extends FachadaCategoriaBean
                                                       implements Serializable {

  	// Nombre JNDI del Data Source que esta clase requiere
    private static final String DATA_SOURCE_NAME = "CommerceAccess";

	// Clase helper usada para acceder a la base de datos
	private JDBCAccess jdbcAccess;

	// Metodo constructor por defecto sin parametros
    public TipoOrdenSubcuentaBean() {

  	// Al instanciar el Bean establece la conexion a la base de datos
	   jdbcAccess = new JDBCAccess(DATA_SOURCE_NAME);
    }

    // Metodo
    public Vector listaCategorias() {

      //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
      Class tipoObjeto       = this.getClass();
      String nombreClase     = tipoObjeto.getName();

      Vector contenedor      = new Vector();
      Connection connection  = null;

      String selectStr =
        "SELECT 00 AS idLinea,                  " +
        "       00 AS idCategoria,              " +
        "       '--' AS  nombreCategoria,       " +
        "       00 AS estado,                   " +
        "       00 AS idSeq,                    " +
        "       01 AS ordenSalida               " +
        "FROM tblCategorias                     " +
        "UNION                                  " +
        "SELECT tblCategorias.idLinea,          " +
        "       tblCategorias.IdCategoria,      " +
        "       tblCategorias.nombreCategoria,  " +
        "       tblCategorias.estado,           " +
        "       tblCategorias.idSeq,            " +
        "       02 AS ordenSalida               " +
        "FROM tblCategorias                     " +
        "ORDER BY 6, 3                          " ;

      PreparedStatement selectStatement = null;

      try {

        // Obtiene una conexion a la base de datos, requiere Try
        connection = jdbcAccess.getConnection();

        // Prepara la sentencia de Busqueda
        selectStatement = connection.prepareStatement(selectStr);

        // Obtiene el resultset
        ResultSet rs = selectStatement.executeQuery();

        // Variable de fachada de los datos
        FachadaCategoriaBean fachadaBean;

        while (rs.next()) {

              fachadaBean = new FachadaCategoriaBean();
              fachadaBean.setIdCategoria(rs.getInt("idCategoria"));
              fachadaBean.setIdLinea(rs.getInt("idLinea"));
              fachadaBean.setNombreCategoria(rs.getString("nombreCategoria"));
              fachadaBean.setEstado(rs.getInt("estado"));
              fachadaBean.setIdSeq(rs.getInt("idSeq"));

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

    // listaUnAllCategorias
    public Vector listaUnAllCategoria() {

      //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
      Class tipoObjeto       = this.getClass();
      String nombreClase     = tipoObjeto.getName();

      Vector contenedor      = new Vector();
      Connection connection  = null;

      String selectStr =
        "SELECT 00 AS idLinea,                  " +
        "       00 AS idCategoria,              " +
        "       '--' AS  nombreCategoria,       " +
        "       00 AS estado,                   " +
        "       00 AS idSeq,                    " +
        "       01 AS ordenSalida               " +
        "FROM tblCategorias                     " +
        "UNION                                  " +
        "SELECT 00 AS idLinea,                  " +
        "       9999 AS idCategoria,            " +
        "       'TODAS' AS  nombreCategoria,    " +
        "       00 AS estado,                   " +
        "       00 AS idSeq,                    " +
        "       02 AS ordenSalida               " +
        "FROM tblCategorias                     " +
        "UNION                                  " +
        "SELECT tblCategorias.idLinea,          " +
        "       tblCategorias.IdCategoria,      " +
        "       tblCategorias.nombreCategoria,  " +
        "       tblCategorias.estado,           " +
        "       tblCategorias.idSeq,            " +
        "       03 AS ordenSalida               " +
        "FROM tblCategorias                     " +
        "ORDER BY 6, 3                          " ;

      PreparedStatement selectStatement = null;

      try {

        // Obtiene una conexion a la base de datos, requiere Try
        connection = jdbcAccess.getConnection();

        // Prepara la sentencia de Busqueda
        selectStatement = connection.prepareStatement(selectStr);

        // Obtiene el resultset
        ResultSet rs = selectStatement.executeQuery();

        // Variable de fachada de los datos
        FachadaCategoriaBean fachadaBean;

        while (rs.next()) {

              fachadaBean = new FachadaCategoriaBean();
              fachadaBean.setIdCategoria(rs.getInt("idCategoria"));
              fachadaBean.setIdLinea(rs.getInt("idLinea"));
              fachadaBean.setNombreCategoria(rs.getString("nombreCategoria"));
              fachadaBean.setEstado(rs.getInt("estado"));
              fachadaBean.setIdSeq(rs.getInt("idSeq"));

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

    // Metodo
    public Vector listaCategoriasxLineas() {

      //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
      Class tipoObjeto       = this.getClass();
      String nombreClase     = tipoObjeto.getName();

      Vector contenedor      = new Vector();
      Connection connection  = null;

      String selectStr =
        "SELECT tblCategorias.idLinea,          " +
        "       tblCategorias.idCategoria,      " +
        "       tblCategorias.nombreCategoria,  " +
        "       tblCategorias.estado,           " +
        "       tblCategorias.idSeq             " +
        "FROM tblCategorias                     " +
        "WHERE tblCategorias.idLinea  =  (?)    " +
        "ORDER BY tblCategorias.nombreCategoria " ;

      PreparedStatement selectStatement = null;

      try {

        // Obtiene una conexion a la base de datos, requiere Try
        connection = jdbcAccess.getConnection();

        // Prepara la sentencia de Busqueda
        selectStatement = connection.prepareStatement(selectStr);

        //
        selectStatement.setInt(1,getIdLinea());

        // Obtiene el resultset
        ResultSet rs = selectStatement.executeQuery();

        // Variable de fachada de los datos
        FachadaCategoriaBean fachadaBean;

        while (rs.next()) {

              fachadaBean = new FachadaCategoriaBean();

              //
              fachadaBean.setIdCategoria(rs.getInt("idCategoria"));
              fachadaBean.setIdLinea(rs.getInt("idLinea"));
              fachadaBean.setNombreCategoria(rs.getString("nombreCategoria"));
              fachadaBean.setEstado(rs.getInt("estado"));
              fachadaBean.setIdSeq(rs.getInt("idSeq"));

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

    // listaCategoriaTx
    public Vector listaCategoriaTx() {

      //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
      Class tipoObjeto       = this.getClass();
      String nombreClase     = tipoObjeto.getName();

      Vector contenedor      = new Vector();
      Connection connection  = null;

      String selectStr =
        "SELECT tblCategorias.idLinea,        " +
        "       tblCategorias.IdCategoria,    " +
        "       tblCategorias.nombreCategoria," +
        "       tblCategorias.estado,         " +
        "       tblCategorias.idSeq           " +
        "FROM tblCategorias                   " +
        "WHERE tblCategorias.idSeq >          " +
        getIdSeq() + "                        " +
        "ORDER BY tblCategorias.idSeq ";


      PreparedStatement selectStatement = null;

      try {

        // Obtiene una conexion a la base de datos, requiere Try
        connection = jdbcAccess.getConnection();

        // Prepara la sentencia de Busqueda
        selectStatement = connection.prepareStatement(selectStr);

        // Obtiene el resultset
        ResultSet rs = selectStatement.executeQuery();

        // Variable de fachada de los datos
        FachadaCategoriaBean fachadaBean;

        while (rs.next()) {

              fachadaBean = new FachadaCategoriaBean();
              fachadaBean.setIdCategoria(rs.getInt("idCategoria"));
              fachadaBean.setIdLinea(rs.getInt("idLinea"));
              fachadaBean.setNombreCategoria(rs.getString("nombreCategoria"));
              fachadaBean.setEstado(rs.getInt("estado"));
              fachadaBean.setIdSeq(rs.getInt("idSeq"));

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

    // ingresarCategoria
    public boolean ingresa() {

      //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
      Class tipoObjeto       = this.getClass();
      String nombreClase     = tipoObjeto.getName();
      boolean okIngresar     = false;

      Connection connection  = null;

      String selectStr =
        // Propiedades del bean
        "INSERT INTO tblCategorias (idSeq, " +
        "                       idLinea,   " +
        "                    idCategoria,  " +
        "                 nombreCategoria, " +
        "                          estado) " +
        "VALUES ( ?, ?, ?, ?, ? )          " ;


      PreparedStatement selectStatement = null;

      try {

        // Obtiene una conexion a la base de datos, requiere Try
        connection = jdbcAccess.getConnection();

        // Prepara la sentencia de Busqueda
        selectStatement = connection.prepareStatement(selectStr);

  	    // inicializa los valore de los parametros
	    selectStatement.setInt(1,getIdSeq());
	    selectStatement.setInt(2,getIdLinea());
	    selectStatement.setInt(3,getIdCategoria());
	    selectStatement.setString(4,getNombreCategoria());
	    selectStatement.setInt(5,getEstado());


        // Obtiene el resultset
        selectStatement.execute();
        okIngresar = true;

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

	  }

	  catch (Exception e)
	  {
		System.out.println("Exception In :" + nombreClase + " " + e);
	  }
      finally {
        // Cierra la conexion a la base de datos limpiando la statement usada y la
        jdbcAccess.cleanup(connection, selectStatement,null);
        return okIngresar;
      }
    }

    // seleccionaCategoriaxNombre
    public Vector seleccionaCategoriaxNombre() {

      //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
      Class tipoObjeto       = this.getClass();
      String nombreClase     = tipoObjeto.getName();

      Vector contenedor      = new Vector();
      Connection connection  = null;


      String selectStr =
        "SELECT tblCategorias.idLinea,             " +
        "       tblCategorias.IdCategoria,         " +
        "       tblCategorias.nombreCategoria,     " +
        "       tblCategorias.estado               " +
        "FROM tblCategorias                        " +
        "WHERE tblCategorias.nombreCategoria LIKE (" +
                   getNombreCategoria() +       ") " +
        "ORDER BY tblCategorias.nombreCategoria    " ;

      PreparedStatement selectStatement = null;

      try {

        // Obtiene una conexion a la base de datos, requiere Try
        connection = jdbcAccess.getConnection();

        // Prepara la sentencia de Busqueda
        selectStatement = connection.prepareStatement(selectStr);

        // Obtiene el resultset
        ResultSet rs = selectStatement.executeQuery();

        // Variable de fachada de los datos
        FachadaCategoriaBean fachadaBean;

        while (rs.next()) {

              fachadaBean = new FachadaCategoriaBean();
              fachadaBean.setIdCategoria(rs.getInt("idCategoria"));
              fachadaBean.setIdLinea(rs.getInt("idLinea"));
              fachadaBean.setNombreCategoria(rs.getString("nombreCategoria"));
              fachadaBean.setEstado(rs.getInt("estado"));

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

    // listaIdSeqMaxima
    public int listaIdSeqMaxima() {

      //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
      Class tipoObjeto       = this.getClass();
      String nombreClase     = tipoObjeto.getName();

      int idMaxIdSeq         = 0;
      Connection connection  = null;

      String selectStr =
        "SELECT MAX(tblCategorias.idSeq) " +
        "                    AS maxIdSeq " +
        "FROM tblCategorias              " ;

      PreparedStatement selectStatement = null;

      try {

        // Obtiene una conexion a la base de datos, requiere Try
        connection = jdbcAccess.getConnection();

        // Prepara la sentencia de Busqueda
        selectStatement = connection.prepareStatement(selectStr);

        // Obtiene el resultset
        ResultSet rs = selectStatement.executeQuery();

        if (rs.next()) {

           idMaxIdSeq = rs.getInt("maxIdSeq");

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

	  }

	  catch (Exception e)
	  {
		System.out.println("Exception In :" + nombreClase + " " + e);
	  }
      finally {
        // Cierra la conexion a la base de datos limpiando la statement usada y la
        jdbcAccess.cleanup(connection, selectStatement,null);
       return idMaxIdSeq;
      }
    }

    // actualizaCategoria
    public boolean actualizaCategoria() {

      //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
      Class tipoObjeto       = this.getClass();
      String nombreClase     = tipoObjeto.getName();
      boolean okIngresar     = false;

      Connection connection  = null;

      String selectStr =
      	"UPDATE tblCategorias                 " +
       	"SET tblCategorias.nombreCategoria = '" +
        getNombreCategoria() + "',            " +
        "    tblCategorias.idSeq           =  " +
        getIdSeq() + ",                       " +
        "    tblCategorias.estado          =  " +
        getEstado() + "                       " +
        "WHERE tblCategorias.idLinea       =  " +
        getIdLinea() + "                      " +
        "AND   tblCategorias.idCategoria   =  " +
        getIdCategoria() ;

      PreparedStatement selectStatement = null;

      try {

        // Obtiene una conexion a la base de datos, requiere Try
        connection = jdbcAccess.getConnection();

        // Prepara la sentencia de Busqueda
        selectStatement = connection.prepareStatement(selectStr);

        // Obtiene el resultset
        selectStatement.execute();
        okIngresar = true;

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
		return okIngresar;

	  }

	  catch (Exception e)
	  {
		System.out.println("Exception In :" + nombreClase + " " + e);
	  }
      finally {
        // Cierra la conexion a la base de datos limpiando la statement usada y la
        jdbcAccess.cleanup(connection, selectStatement,null);
        return okIngresar;
      }
    }

    // listaIdCategoriaMaxima
    public int listaIdCategoriaMaxima() {

      //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
      Class tipoObjeto       = this.getClass();
      String nombreClase     = tipoObjeto.getName();

      int idCategoriaMaxima  = 0;
      Connection connection  = null;

      String selectStr =
        "SELECT MAX(tblCategorias.idCategoria) " +
        "                 AS idCategoriaMaxima " +
        "FROM tblCategorias                    " +
        "WHERE tblCategorias.idLinea = ( ? )   " ;

      PreparedStatement selectStatement = null;

      try {

        // Obtiene una conexion a la base de datos, requiere Try
        connection = jdbcAccess.getConnection();

        // Prepara la sentencia de Busqueda
        selectStatement = connection.prepareStatement(selectStr);

        //
	    selectStatement.setInt(1,getIdLinea());

        // Obtiene el resultset
        ResultSet rs = selectStatement.executeQuery();

        if (rs.next()) {

           idCategoriaMaxima = rs.getInt("idCategoriaMaxima");

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

	  }

	  catch (Exception e)
	  {
		System.out.println("Exception In :" + nombreClase + " " + e);
	  }
      finally {
        // Cierra la conexion a la base de datos limpiando la statement usada y la
        jdbcAccess.cleanup(connection, selectStatement,null);
        return idCategoriaMaxima;
      }
    }

    // Metodo
    public Vector listaCategoriaSeleccion() {

      //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
      Class tipoObjeto       = this.getClass();
      String nombreClase     = tipoObjeto.getName();

      Vector contenedor      = new Vector();
      Connection connection  = null;

      String selectStr =
        "SELECT tblCategorias.idLinea,          " +
        "       tblCategorias.idCategoria,      " +
        "       tblCategorias.nombreCategoria,  " +
        "       tblCategorias.estado,           " +
        "       tblCategorias.idSeq,            " +
        "       01 AS orden                     " +
        "FROM tblCategorias                     " +
        "WHERE tblCategorias.idLinea     =      " +
        getIdLinea() + "                        " +
        "AND   tblCategorias.idCategoria =      " +
        getIdCategoria() + "                    " +
        "UNION                                  " +
        "SELECT tblCategorias.idLinea,          " +
        "       tblCategorias.idCategoria,      " +
        "       tblCategorias.nombreCategoria,  " +
        "       tblCategorias.estado,           " +
        "       tblCategorias.idSeq,            " +
        "       02 AS orden                     " +
        "FROM tblCategorias                     " +
        "WHERE tblCategorias.idLinea +          " +
        "      tblCategorias.idCategoria NOT IN " +
        " ( SELECT tblCategorias.idLinea +      " +
        "         tblCategorias.idCategoria     " +
        "FROM tblCategorias                     " +
        "WHERE tblCategorias.idLinea     =      " +
        getIdLinea() + "                        " +
        "AND   tblCategorias.idCategoria =      " +
        getIdCategoria() + "                  ) " +
        "ORDER BY 6, 3                          " ;

      PreparedStatement selectStatement = null;

      try {

        // Obtiene una conexion a la base de datos, requiere Try
        connection = jdbcAccess.getConnection();

        // Prepara la sentencia de Busqueda
        selectStatement = connection.prepareStatement(selectStr);

        // Obtiene el resultset
        ResultSet rs = selectStatement.executeQuery();

        // Variable de fachada de los datos
        FachadaCategoriaBean fachadaBean;

        while (rs.next()) {

              fachadaBean = new FachadaCategoriaBean();
              fachadaBean.setIdCategoria(rs.getInt("idCategoria"));
              fachadaBean.setIdLinea(rs.getInt("idLinea"));
              fachadaBean.setNombreCategoria(rs.getString("nombreCategoria"));
              fachadaBean.setEstado(rs.getInt("estado"));
              fachadaBean.setIdSeq(rs.getInt("idSeq"));

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

    // Metodo
    public FachadaCategoriaBean listaUnaCategoriaFCH() {

      //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
      Class tipoObjeto       = this.getClass();
      String nombreClase     = tipoObjeto.getName();

      // Variable de fachada de los datos
      FachadaCategoriaBean fachadaBean         = new FachadaCategoriaBean();

      Connection connection  = null;

      String selectStr =
        "SELECT tblCategorias.idLinea,          " +
        "       tblCategorias.idCategoria,      " +
        "       tblCategorias.nombreCategoria,  " +
        "       tblCategorias.estado,           " +
        "       tblCategorias.idSeq             " +
        "FROM tblCategorias                     " +
        "WHERE tblCategorias.idLinea     =      " +
        getIdLinea() + "                        " +
        "AND   tblCategorias.idCategoria =      " +
        getIdCategoria() + "                    " +
        "ORDER BY tblCategorias.nombreCategoria " ;

      PreparedStatement selectStatement = null;

      try {

        // Obtiene una conexion a la base de datos, requiere Try
        connection = jdbcAccess.getConnection();

        // Prepara la sentencia de Busqueda
        selectStatement = connection.prepareStatement(selectStr);

        // Obtiene el resultset
        ResultSet rs = selectStatement.executeQuery();

        if (rs.next()) {

              //
              fachadaBean.setIdCategoria(rs.getInt("idCategoria"));
              fachadaBean.setIdLinea(rs.getInt("idLinea"));
              fachadaBean.setNombreCategoria(rs.getString("nombreCategoria"));
              fachadaBean.setEstado(rs.getInt("estado"));
              fachadaBean.setIdSeq(rs.getInt("idSeq"));

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

	  }

	  catch (Exception e)
	  {
		System.out.println("Exception In :" + nombreClase + " " + e);
	  }
      finally {

        jdbcAccess.cleanup(connection, selectStatement,null);
        return fachadaBean;

      }
    }

// traverse_CategoriaTx
public void traverse_CategoriaTx( XmlParser parser,
                                  String indent ) throws Exception {

    //
	boolean leave          = false;
    String idLinea         = new String();
    String idCategoria     = new String();
    String nombreCategoria = new String();
    String estado      = new String();
    String idSeq       = new String();

	do {
	   ParseEvent event = parser.read();
	   ParseEvent pe;

	switch ( event.getType() ) {

	 // For example, <CxC>
	 case Xml.START_TAG:

	 // see API doc of StartTag for more access methods
	 // Pick up idagenda for display
	  if ("idLinea".equals(event.getName()))
	  {
			pe          = parser.read();
			idLinea     = pe.getText();
	  }

	  if ("idCategoria".equals(event.getName()))
	  {
			pe          = parser.read();
			idCategoria = pe.getText();
	  }

	  // Pick up clave for display
	  if ("nombreCategoria".equals(event.getName()))
	  {
	  		pe              = parser.read();
	  		nombreCategoria = pe.getText();
	  }

	  // Pick up clave for display
	  if ("estado".equals(event.getName()))
	  {
	  		pe          = parser.read();
	  		estado      = pe.getText();
	  }

	  // Pick up idlocal for display
	  if ("idSeq".equals(event.getName()))
	  {
			pe     = parser.read();
			idSeq  = pe.getText();

   	        // ingresarCategoria
   	        setIdLinea(idLinea);
   	        setIdCategoria(idCategoria);
   	        setNombreCategoria(nombreCategoria);
   	        setEstado(Integer.parseInt(estado));
   	        setIdSeq(idSeq);

   	        if ( this.existeCategoria() ) {
   	             System.out.println(" actualiza categoria");

                 //
                 this.actualizaCategoria();
   	        } else {
   	             System.out.println(" ingresa categoria");

                 //
    	         this.ingresa();
   	        }

	  }

	  traverse_CategoriaTx( parser, "" ) ; // recursion call for each <tag></tag>
	  break;

	  // For example </title?
	 case Xml.END_TAG:
	  leave = true;
	  break;

	  // For example </rss>
	 case Xml.END_DOCUMENT:
	  leave = true;
	  break;

	  // For example, the text between tags
	 case Xml.TEXT:
	  break;
	 case Xml.WHITESPACE:
	  break;
	  default:
	  }
	  } while( !leave );
  }

    // existeCategoria
    public boolean existeCategoria() {

      //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
      Class tipoObjeto       = this.getClass();
      String nombreClase     = tipoObjeto.getName();
      boolean existeLinea    = false;

      Connection connection  = null;

      String selectStr =
        "SELECT tblCategorias.*          " +
        "FROM tblCategorias              " +
        "WHERE tblCategorias.idLinea   = " +
        getIdLinea() + "                 " +
        "AND tblCategorias.idCategoria = " +
        getIdCategoria() ;

      PreparedStatement selectStatement = null;

      try {

        // Obtiene una conexion a la base de datos, requiere Try
        connection = jdbcAccess.getConnection();

        // Prepara la sentencia de Busqueda
        selectStatement = connection.prepareStatement(selectStr);

        // Obtiene el resultset
        ResultSet rs = selectStatement.executeQuery();

        if (rs.next()) {

           existeLinea = true;

        }

        // Cierra el Resultset
        rs.close();

      } catch (SQLException sqle) {

		System.out.println("SQLException in" + nombreClase);

		String sqlMessage = sqle.getMessage();
                String sqlState = sqle.getSQLState();
                int vendorCode = sqle.getErrorCode();
		System.out.println("Ocurrio una  excepcion");
		System.out.println("Mensaje " + sqlMessage);
		System.out.println("SQL State " + sqlState);
		System.out.println("Vendor Code " + vendorCode);
		return existeLinea;

	  }

	  catch (Exception e)
	  {
		System.out.println("Exception In :" + nombreClase + " " + e);
	  }
      finally {
        // Cierra la conexion a la base de datos limpiando la statement usada y la
            jdbcAccess.cleanup(connection, selectStatement, null);
       return existeLinea;
      }
    }
}