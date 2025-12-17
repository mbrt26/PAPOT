package com.solucionesweb.losbeans.colaboraciones;

// Importa la clase helper JDBCAccess que contiene los accesos a la base de datos
import com.solucionesweb.lasayudas.JDBCAccess;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaColaboraArticuloAtributoBean;

// Importa los paquetes de java
import java.sql.*;
import java.io.*;
import java.io.IOException.*;

// Importa los paquetes de javax
import javax.naming.*;

public class ColaboraArticuloAtributoBean
           extends FachadaColaboraArticuloAtributoBean implements Serializable {

  	// Nombre JNDI del Data Source que esta clase requiere
    private static final String DATA_SOURCE_NAME = "CommerceAccess";

	// Clase helper usada para acceder a la base de datos
	private JDBCAccess jdbcAccess;

	// Metodo constructor por defecto sin parametros
    public ColaboraArticuloAtributoBean() {

  	// Al instanciar el Bean establece la conexion a la base de datos
	   jdbcAccess = new JDBCAccess(DATA_SOURCE_NAME);
    }
   /*
    // listaArticuloAtributo
    public Vector listaArticuloAtributo() {

      //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
      Class tipoObjeto       = this.getClass();
      String nombreClase     = tipoObjeto.getName();
      Vector contenedor      = new Vector();

      boolean    validaOk    = false;
      Connection connection  = null;

      String selectStr =
      "SELECT FIRST(tblLineas.idLinea)                          " +
      "                              AS idLinea,                " +
      "       FIRST(tblLineas.nombreLinea)                      " +
      "                             AS nombreLinea,             " +
      "       FIRST(tblLineasSublineas.idSublinea)              " +
      "                                       AS idSublinea ,   " +
      "       FIRST(tblLineasSublineas.nombreSublinea)          " +
      "                                    AS nombreSublinea,   " +
      "       FIRST(tblSumariaPedidosCliente.nombrePlu)         " +
      "                                         AS nombrePlu,   " +
      "       LAST(tblSumariaPedidosCliente.vrVentaUnitario)    " +
      "                                  AS vrVentaUnitario ,   " +
      "       tblSumariaPedidosCliente.strIdReferencia          " +
      "FROM (tblSumariaPedidosCliente                           " +
      "INNER JOIN tblLineas                                     " +
      "ON tblSumariaPedidosCliente.idLinea = tblLineas.idLinea) " +
      "INNER JOIN tblLineasSublineas                            " +
      "ON (tblSumariaPedidosCliente.idSublinea      =           " +
      "                          tblLineasSublineas.idSublinea) " +
      "AND (tblSumariaPedidosCliente.idLinea        =           " +
      "                             tblLineasSublineas.idLinea) " +
      "GROUP BY tblSumariaPedidosCliente.strIdReferencia        " +
      "HAVING tblSumariaPedidosCliente.strIdReferencia =        " +
      "                           " + getStrIdReferencia() ;

      PreparedStatement selectStatement = null;

      try {

        // Obtiene una conexion a la base de datos, requiere Try
        connection = jdbcAccess.getConnection();

        // Prepara la sentencia de Busqueda
        selectStatement = connection.prepareStatement(selectStr);

        // Obtiene el resultset
        ResultSet rs = selectStatement.executeQuery();

        // Variable de fachada de los datos
        FachadaColaboraArticuloAtributoBean fachadaColaboraArticuloAtributoBean;

        while (rs.next()) {

              fachadaColaboraArticuloAtributoBean = new FachadaColaboraArticuloAtributoBean();

              fachadaColaboraArticuloAtributoBean.setNombreLinea(rs.getString("nombreLinea"));
              fachadaColaboraArticuloAtributoBean.setNombreSublinea(rs.getString("nombreSublinea"));
              fachadaColaboraArticuloAtributoBean.setNombrePlu(rs.getString("nombrePlu"));
              fachadaColaboraArticuloAtributoBean.setVrVentaUnitario(rs.getDouble("vrVentaUnitario"));
              fachadaColaboraArticuloAtributoBean.setStrIdReferencia(rs.getString("strIdReferencia"));

              //
              contenedor.add(fachadaColaboraArticuloAtributoBean);
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
    }      */

    // listaAtributosUnArticulo
    public FachadaColaboraArticuloAtributoBean listaAtributoUnArticulo() {

      //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
      Class tipoObjeto       = this.getClass();
      String nombreClase     = tipoObjeto.getName();

      // Variable de fachada de los datos
      FachadaColaboraArticuloAtributoBean fachadaColaboraArticuloAtributoBean
                                    = new FachadaColaboraArticuloAtributoBean();


      boolean    validaOk    = false;
      Connection connection  = null;

      String selectStr =
      "SELECT tblLineas.nombreLinea,               " +
      "       tblLineasSublineas.nombreSublinea    " +
      "FROM tblLineas                              " +
      "INNER JOIN tblLineasSublineas               " +
      "ON tblLineas.idLinea                =       " +
      "              tblLineasSublineas.idLinea    " +
      "WHERE tblLineas.idLinea             =       " +
      "                      " + getIdLinea()  + " " +
      "AND   tblLineasSublineas.idSublinea =       " +
      "                      " + getIdSublinea()   ;

      PreparedStatement selectStatement = null;

      try {

        // Obtiene una conexion a la base de datos, requiere Try
        connection = jdbcAccess.getConnection();

        // Prepara la sentencia de Busqueda
        selectStatement = connection.prepareStatement(selectStr);

        // Obtiene el resultset
        ResultSet rs = selectStatement.executeQuery();

        while (rs.next()) {
              fachadaColaboraArticuloAtributoBean.setNombreLinea(rs.getString("nombreLinea"));
              fachadaColaboraArticuloAtributoBean.setNombreSublinea(rs.getString("nombreSublinea"));
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
        return fachadaColaboraArticuloAtributoBean;

	  }

	  catch (Exception e)
	  {
		System.out.println("Exception In :" + nombreClase + " " + e);
	  }
      finally {

        // Cierra la conexion
        jdbcAccess.cleanup(connection, selectStatement,null);
        return fachadaColaboraArticuloAtributoBean;

      }
    }
}