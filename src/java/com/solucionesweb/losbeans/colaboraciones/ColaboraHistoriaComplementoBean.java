package com.solucionesweb.losbeans.colaboraciones;

// Importa la clase helper JDBCAccess que contiene los accesos a la base de datos
import com.solucionesweb.lasayudas.JDBCAccess;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaColaboraHistoriaComplementoBean;

// Importa los paquetes de java
import java.sql.*;
import java.io.*;
import java.util.*;
import java.io.IOException.*;

// Importa los paquetes de javax
import javax.naming.*;

public class ColaboraHistoriaComplementoBean
                                  extends FachadaColaboraHistoriaComplementoBean
                                  implements Serializable {

  	// Nombre JNDI del Data Source que esta clase requiere
    private static final String DATA_SOURCE_NAME = "CommerceAccess";

	// Clase helper usada para acceder a la base de datos
	private JDBCAccess jdbcAccess;

	// Metodo constructor por defecto sin parametros
    public ColaboraHistoriaComplementoBean() {

  	// Al instanciar el Bean establece la conexion a la base de datos
	   jdbcAccess = new JDBCAccess(DATA_SOURCE_NAME);
    }

    // validaComplementoCliente
    public boolean  validaComplementoCliente() {

      //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
      Class tipoObjeto       = this.getClass();
      String nombreClase     = tipoObjeto.getName();
      boolean existeOk       = false ;

      Connection connection  = null;

      String selectStr =
      "SELECT tblSumariaPedidosComplemento.idCliente,                      " +
      "       tblSumariaPedidosComplemento.strIdReferencia,                " +
      "       tblSumariaPedidosComplemento.IdReferenciaComplemento         " +
      "FROM   tblSumariaPedidosComplemento                                 " +
      "WHERE  tblSumariaPedidosComplemento.idCliente               = ( ? ) " +
      "AND    tblSumariaPedidosComplemento.strIdReferencia         = ( ? ) " +
      "AND    tblSumariaPedidosComplemento.IdReferenciaComplemento = ( ? ) " ;

      PreparedStatement selectStatement = null;

      try {

        // Obtiene una conexion a la base de datos, requiere Try
        connection = jdbcAccess.getConnection();

        // Prepara la sentencia de Busqueda
        selectStatement = connection.prepareStatement(selectStr);

        //
        selectStatement.setString(1,getIdCliente());
        selectStatement.setString(2,getStrIdReferencia());
        selectStatement.setString(3,getIdReferenciaComplemento());

        // Obtiene el resultset
        ResultSet rs = selectStatement.executeQuery();

        //
        FachadaColaboraHistoriaComplementoBean fachadaColaboraHistoriaBean;

        if (rs.next()) {

            existeOk = true ;

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
        return existeOk;

	  }

	  catch (Exception e)
	  {
		System.out.println("Exception In :" + nombreClase + " " + e);
	  }
      finally {

        // Cierra la conexion
       return existeOk;

      }
    }

    // ingresaComplemento
    public boolean ingresaComplemento() {

      //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
      Class tipoObjeto       = this.getClass();
      String nombreClase     = tipoObjeto.getName();
      boolean okIngresar     = false;

      Connection connection  = null;

      String selectStr =
      "INSERT INTO tblSumariaPedidosComplemento (idCliente,              " +
      "                                          strIdReferencia,        " +
      "                                          idReferenciaComplemento)" +
      "VALUES (       " + getIdCliente()                             + "," +
            "        '" + getStrIdReferencia()                      + "'," +
            "        '" + getIdReferenciaComplemento()              + "')" ;

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

        // Cierra el Resultset
        jdbcAccess.cleanup(connection, selectStatement,null);
        return okIngresar;
      }
    }

    // eliminaComplemento
    public boolean eliminaComplemento() {

      //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
      Class tipoObjeto       = this.getClass();
      String nombreClase     = tipoObjeto.getName();
      boolean okElimino      = false;

      Connection connection  = null;

      String selectStr =
      "DELETE tblSumariaPedidosComplemento.* " +
      "FROM tblSumariaPedidosComplemento     " ;

      PreparedStatement selectStatement = null;

      try {

        // Obtiene una conexion a la base de datos, requiere Try
        connection = jdbcAccess.getConnection();

        // Prepara la sentencia de Busqueda
        selectStatement = connection.prepareStatement(selectStr);

        // Obtiene el resultset
        selectStatement.execute();
        okElimino = true;

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
		return okElimino;

	  }

	  catch (Exception e)
	  {
		System.out.println("Exception In :" + nombreClase + " " + e);
	  }
      finally {

        // Cierra el Resultset
        jdbcAccess.cleanup(connection, selectStatement,null);
        return okElimino;
      }
    }

    // listaComplementoLog
    public Vector  listaComplementoLog(int xIdLog,
                                       String xItem) {

      //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
      Class tipoObjeto       = this.getClass();
      String nombreClase     = tipoObjeto.getName();
      boolean existeOk       = false ;
      Vector contenedor      = new Vector();

      Connection connection  = null;

      String selectStr =
      "SELECT tblSumariaPedidosComplemento.idCliente,               " +
      "       tblSumariaPedidosComplemento.strIdReferencia,         " +
      "       tblSumariaPedidosComplemento.idReferenciaComplemento, " +
      "       FIRST(tmpProducto.item) AS item                       " +
      "FROM   tblSumariaPedidosComplemento,                         " +
      "       ( SELECT tblDctosOrdenesDetalle.STRIDREFERENCIA,      " +
      "                tblDctosOrdenes.IDCLIENTE,                   " +
      "                tblDctosOrdenesDetalle.item                  " +
      "         FROM   tblDctosOrdenes                              " +
      "         INNER JOIN tblDctosOrdenesDetalle                   " +
      "         ON (tblDctosOrdenes.IDLOCAL         =               " +
      "                           tblDctosOrdenesDetalle.IDLOCAL)   " +
      "         AND (tblDctosOrdenes.IDTIPOORDEN    =               " +
      "                      tblDctosOrdenesDetalle.IDTIPOORDEN)    " +
      "         AND (tblDctosOrdenes.IDORDEN        =               " +
      "                           tblDctosOrdenesDetalle.IDORDEN)   " +
      "         WHERE (((tblDctosOrdenes.IDLOG)     =  " + xIdLog + "  ) " +
      "         AND ((tblDctosOrdenesDetalle.item) IN (" + xItem + ")))) " +
      "                                             AS tmpProducto  " +
      "WHERE (((tblSumariaPedidosComplemento.idCliente)=            " +
      "                                    tmpProducto.idCliente))  " +
      "AND  tblSumariaPedidosComplemento.strIdReferencia =          " +
      "                                tmpProducto.STRIDREFERENCIA  " +
      "GROUP BY tblSumariaPedidosComplemento.idCliente,             " +
      "         tblSumariaPedidosComplemento.strIdReferencia,       " +
      "         tblSumariaPedidosComplemento.idReferenciaComplemento" ;

      PreparedStatement selectStatement = null;

      try {

        // Obtiene una conexion a la base de datos, requiere Try
        connection = jdbcAccess.getConnection();

        // Prepara la sentencia de Busqueda
        selectStatement = connection.prepareStatement(selectStr);

        // Obtiene el resultset
        ResultSet rs = selectStatement.executeQuery();

        //
        FachadaColaboraHistoriaComplementoBean fachadaColaboraHistoriaBean;

        while (rs.next()) {

              fachadaColaboraHistoriaBean = new FachadaColaboraHistoriaComplementoBean();

              fachadaColaboraHistoriaBean.setIdCliente(rs.getString("idCliente"));
              fachadaColaboraHistoriaBean.setStrIdReferencia(rs.getString("strIdReferencia"));
              fachadaColaboraHistoriaBean.setIdReferenciaComplemento(rs.getString("idReferenciaComplemento"));
              fachadaColaboraHistoriaBean.setItem(rs.getInt("item"));

              contenedor.add(fachadaColaboraHistoriaBean);

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

    // listaComplementoFlia
    public Vector  listaComplementoFlia(String xIdFlia) {

      //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
      Class tipoObjeto       = this.getClass();
      String nombreClase     = tipoObjeto.getName();
      boolean existeOk       = false ;
      Vector contenedor      = new Vector();

      Connection connection  = null;

      String selectStr =
  	  "SELECT tmpComplemento.idReferenciaComplemento,                        " +
	  "       FIRST(tblSumariaPedidosCliente.nombrePlu) AS nombrePlu         " +
	  "FROM tblSumariaPedidosCliente,                                        " +
	  "     (SELECT MID(tblSumariaPedidosComplemento.strIdReferencia,1,3)    " +
	  "                 AS idFlia,                                           " +
	  "                 tblSumariaPedidosComplemento.idReferenciaComplemento " +
	  "      FROM tblSumariaPedidosComplemento                               " +
	  "      WHERE VAL(MID(tblSumariaPedidosComplemento.strIdReferencia,1,3))" +
	  "                                             IN (" + xIdFlia + ")     " +
	  "      GROUP BY MID(tblSumariaPedidosComplemento.strIdReferencia,1,3), " +
	  "               tblSumariaPedidosComplemento.idReferenciaComplemento)  " +
	  "     AS tmpComplemento                                                " +
	  "WHERE tblSumariaPedidosCliente.strIdReferencia =                      " +
	  "                              tmpComplemento.idReferenciaComplemento  " +
	  "GROUP BY tmpComplemento.idReferenciaComplemento                       " +
	  "ORDER BY 2                                                            " ;

      PreparedStatement selectStatement = null;

      try {

        // Obtiene una conexion a la base de datos, requiere Try
        connection = jdbcAccess.getConnection();

        // Prepara la sentencia de Busqueda
        selectStatement = connection.prepareStatement(selectStr);

        // Obtiene el resultset
        ResultSet rs = selectStatement.executeQuery();

        //
        FachadaColaboraHistoriaComplementoBean fachadaColaboraHistoriaBean;

        while (rs.next()) {

              fachadaColaboraHistoriaBean = new FachadaColaboraHistoriaComplementoBean();

              fachadaColaboraHistoriaBean.setIdReferenciaComplemento(rs.getString("idReferenciaComplemento"));
              fachadaColaboraHistoriaBean.setNombrePlu(rs.getString("nombrePlu"));

              contenedor.add(fachadaColaboraHistoriaBean);

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