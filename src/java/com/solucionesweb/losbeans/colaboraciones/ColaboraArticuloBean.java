package com.solucionesweb.losbeans.colaboraciones;

// Importa la clase helper JDBCAccess que contiene los accesos a la base de datos
import com.solucionesweb.lasayudas.JDBCAccess;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaPluBean;

// Importa los paquetes de java
import java.sql.*;
import java.io.*;
import java.util.*;
import java.io.IOException.*;

// Importa los paquetes de javax
import javax.naming.*;

public class ColaboraArticuloBean extends FachadaPluBean
                                                       implements Serializable {

  	// Nombre JNDI del Data Source que esta clase requiere
    private static final String DATA_SOURCE_NAME = "CommerceAccess";

	// Clase helper usada para acceder a la base de datos
	private JDBCAccess jdbcAccess;

	// Metodo constructor por defecto sin parametros
    public ColaboraArticuloBean() {

  	// Al instanciar el Bean establece la conexion a la base de datos
	   jdbcAccess = new JDBCAccess(DATA_SOURCE_NAME);
    }

    // listaArticuloXCategoria
    public Vector listaArticuloXCategoria(String xListaPrecio,
                                          String xEstado,
                                          String xStrIdCategoria) {

      //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
      Class tipoObjeto       = this.getClass();
      String nombreClase     = tipoObjeto.getName();
      Vector contenedor      = new Vector();

      boolean    validaOk    = false;
      Connection connection  = null;

      String selectStr =
      "SELECT [Part_Sales].PRTNUM_29   " +
      "             AS strIdReferencia," +
      "       [Part_Sales].PMDES1_29 + " +
      "       [Part_Sales].PMDES2_29   " +
      "                   AS nombrePlu," +
      "       [Part_Sales].BOMUOM_29   " +
      "             AS strUnidadMedida," +
      "       0001 AS pesoTeorico,     " +
      "       [Part_Sales].PRICE_29    " +
      "                   AS vrGeneral " +
      "FROM [MAXCAM].[dbo].[Part_Sales] " +
      "WHERE [Part_Sales].PRTNUM_29 =  " +
      "  " + getStrIdReferencia() + "  " +
      "ORDER BY [Part_Sales].PRTNUM_29 " ;


      PreparedStatement selectStatement = null;

      try {

        // Obtiene una conexion a la base de datos, requiere Try
        connection = jdbcAccess.getConnection();

        // Prepara la sentencia de Busqueda
        selectStatement = connection.prepareStatement(selectStr);

        // Obtiene el resultset
        ResultSet rs = selectStatement.executeQuery();

        // Variable de fachada de los datos
        FachadaPluBean fachadaBean;

        while (rs.next()) {

              fachadaBean = new FachadaPluBean();

              fachadaBean.setStrIdReferencia(rs.getString("strIdReferencia"));
              fachadaBean.setNombrePlu(rs.getString("nombrePlu"));
              fachadaBean.setStrUnidadMedida(rs.getString("strUnidadMedida"));
              fachadaBean.setPesoTeorico(rs.getDouble("pesoTeorico"));
              fachadaBean.setVrGeneral(rs.getDouble("vrGeneral"));

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

    // listaArticuloXPlu
    public Vector listaArticuloXPlu() {

      //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
      Class tipoObjeto       = this.getClass();
      String nombreClase     = tipoObjeto.getName();
      Vector contenedor      = new Vector();

      boolean    validaOk    = false;
      Connection connection  = null;

      String selectStr =
      "SELECT [Part_Sales].PRTNUM_29   " +
      "             AS strIdReferencia," +
      "       [Part_Sales].PMDES1_29 + " +
      "       [Part_Sales].PMDES2_29   " +
      "                   AS nombrePlu," +
      "       [Part_Sales].BOMUOM_29   " +
      "             AS strUnidadMedida," +
      "       0001 AS pesoTeorico,     " +
      "       [Part_Sales].PRICE_29    " +
      "                   AS vrGeneral " +
      "FROM [MAXCAM].[dbo].[Part_Sales] " +
      "WHERE [Part_Sales].PRTNUM_29 =  " +
      "  " + getStrIdReferencia() + "  " +
      "ORDER BY [Part_Sales].PRTNUM_29 " ;

      PreparedStatement selectStatement = null;

      try {

        // Obtiene una conexion a la base de datos, requiere Try
        connection = jdbcAccess.getConnection();

        // Prepara la sentencia de Busqueda
        selectStatement = connection.prepareStatement(selectStr);

        // Obtiene el resultset
        ResultSet rs = selectStatement.executeQuery();

        // Variable de fachada de los datos
        FachadaPluBean fachadaBean;

        while (rs.next()) {

              fachadaBean = new FachadaPluBean();

              fachadaBean.setStrIdReferencia(rs.getString("strIdReferencia"));
              fachadaBean.setNombrePlu(rs.getString("nombrePlu"));
              fachadaBean.setStrUnidadMedida(rs.getString("strUnidadMedida"));
              fachadaBean.setPesoTeorico(rs.getDouble("pesoTeorico"));
              fachadaBean.setVrGeneral(rs.getDouble("vrGeneral"));

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

    // listaArticuloXPluCotizacion
    public FachadaPluBean listaArticuloXPluCotizacion() {

      //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
      Class tipoObjeto       = this.getClass();
      String nombreClase     = tipoObjeto.getName();
      Vector contenedor      = new Vector();

      boolean    validaOk    = false;
      Connection connection  = null;

      // Variable de fachada de los datos
      FachadaPluBean fachadaBean = new FachadaPluBean();

      String selectStr =
      "SELECT [Part_Sales].PRTNUM_29   " +
      "             AS strIdReferencia," +
      "       [Part_Sales].PMDES1_29 + " +
      "       [Part_Sales].PMDES2_29   " +
      "                   AS nombrePlu," +
      "       [Part_Sales].BOMUOM_29   " +
      "             AS strUnidadMedida," +
      "       0001 AS pesoTeorico,     " +
      "       [Part_Sales].PRICE_29    " +
      "                   AS vrGeneral " +
      "FROM [MAXCAM].[dbo].[Part_Sales] " +
      "WHERE [Part_Sales].PRTNUM_29 =  " +
      "  " + getStrIdReferencia() + "  " +
      "ORDER BY [Part_Sales].PRTNUM_29 " ;

      PreparedStatement selectStatement = null;

      try {

        // Obtiene una conexion a la base de datos, requiere Try
        connection = jdbcAccess.getConnection();

        // Prepara la sentencia de Busqueda
        selectStatement = connection.prepareStatement(selectStr);

        // Obtiene el resultset
        ResultSet rs = selectStatement.executeQuery();

        while (rs.next()) {

              fachadaBean.setStrIdReferencia(rs.getString("strIdReferencia"));
              fachadaBean.setNombrePlu(rs.getString("nombrePlu"));
              fachadaBean.setStrUnidadMedida(rs.getString("strUnidadMedida"));
              fachadaBean.setPesoTeorico(rs.getDouble("pesoTeorico"));
              fachadaBean.setVrGeneral(rs.getDouble("vrGeneral"));

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

        // Cierra la conexion
        jdbcAccess.cleanup(connection, selectStatement,null);
        return fachadaBean;

      }
    }

    // listaArticuloxReferenciaCotizacion
    public FachadaPluBean listaArticuloxReferenciaCotizacion() {

      //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
      Class tipoObjeto       = this.getClass();
      String nombreClase     = tipoObjeto.getName();
      Vector contenedor      = new Vector();

      boolean    validaOk    = false;
      Connection connection  = null;

      // Variable de fachada de los datos
      FachadaPluBean fachadaBean = new FachadaPluBean();

      String selectStr =
      "SELECT [Part_Sales].PRTNUM_29   " +
      "             AS strIdReferencia," +
      "       [Part_Sales].PMDES1_29 + " +
      "       [Part_Sales].PMDES2_29   " +
      "                   AS nombrePlu," +
      "       [Part_Sales].BOMUOM_29   " +
      "             AS strUnidadMedida," +
      "       0001 AS pesoTeorico,     " +
      "       [Part_Sales].PRICE_29    " +
      "                   AS vrGeneral," +
      "       [Tax_Master].TAXRTE_25   " +
      "             AS porcentajeIva   " +
      "FROM [MAXCAM].[dbo].[Part_Sales] " +
      "INNER JOIN [MAXCAM].[dbo].[Tax_Master] AS Tax_Master " +
      "ON [Part_Sales].TAXCDE_29 =     " +
      "        [Tax_Master].TAXCDE_25  " +
      "WHERE [Part_Sales].PRTNUM_29 =  " +
      " " + getStrIdReferencia() + "   " ;

      PreparedStatement selectStatement = null;

      try {

        // Obtiene una conexion a la base de datos, requiere Try
        connection = jdbcAccess.getConnection();

        // Prepara la sentencia de Busqueda
        selectStatement = connection.prepareStatement(selectStr);

        // Obtiene el resultset
        ResultSet rs = selectStatement.executeQuery();

        //
        if (rs.next()) {

              //
              fachadaBean.setStrIdReferencia(rs.getString("strIdReferencia"));
              fachadaBean.setNombrePlu(rs.getString("nombrePlu"));
              fachadaBean.setStrUnidadMedida(rs.getString("strUnidadMedida"));
              fachadaBean.setPesoTeorico(rs.getDouble("pesoTeorico"));
              fachadaBean.setVrGeneral(rs.getDouble("vrGeneral"));
              fachadaBean.setPorcentajeIva(rs.getDouble("porcentajeIva"));

              //
              String strTexto  = fachadaBean.getNombrePlu().trim();

              //
              if (strTexto.startsWith("(")) {

                  //
                  int lenTexto =  strTexto.length();
                  strTexto     =  strTexto.substring(6,lenTexto);

              }

              //
              fachadaBean.setNombrePlu(strTexto);

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

        // Cierra la conexion
        jdbcAccess.cleanup(connection, selectStatement,null);
        return fachadaBean;

      }
    }

    // listaArticuloXReferencia MsAccess
    public Vector listaArticuloXReferencia() {

      //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
      Class tipoObjeto       = this.getClass();
      String nombreClase     = tipoObjeto.getName();
      Vector contenedor      = new Vector();

      boolean    validaOk    = false;
      Connection connection  = null;

      String selectStr =
      "SELECT [Part_Sales].PRTNUM_29   " +
      "             AS strIdReferencia," +
      "       [Part_Sales].PMDES1_29 + " +
      "       [Part_Sales].PMDES2_29   " +
      "                   AS nombrePlu," +
      "       [Part_Sales].BOMUOM_29   " +
      "             AS strUnidadMedida," +
      "       0001 AS pesoTeorico,     " +
      "       [Part_Sales].PRICE_29    " +
      "                   AS vrGeneral " +
      "FROM [MAXCAM].[dbo].[Part_Sales] " +
      "WHERE [Part_Sales].PRTNUM_29 =  " +
      "  " + getStrIdReferencia() + "  " +
      "ORDER BY [Part_Sales].PRTNUM_29 " ;

      PreparedStatement selectStatement = null;

      try {

        // Obtiene una conexion a la base de datos, requiere Try
        connection = jdbcAccess.getConnection();

        // Prepara la sentencia de Busqueda
        selectStatement = connection.prepareStatement(selectStr);

        // inicializa los valores de los parametros
        //selectStatement.setString(1,getStrIdLista());

        // Obtiene el resultset
        ResultSet rs = selectStatement.executeQuery();

        // Variable de fachada de los datos
        FachadaPluBean fachadaBean;

        //
        while (rs.next()) {

              fachadaBean = new FachadaPluBean();

              //
              fachadaBean.setStrIdReferencia(rs.getString("strIdReferencia"));
              fachadaBean.setNombrePlu(rs.getString("nombrePlu"));
              fachadaBean.setStrUnidadMedida(rs.getString("strUnidadMedida"));
              fachadaBean.setPesoTeorico(rs.getDouble("pesoTeorico"));
              fachadaBean.setVrGeneral(rs.getDouble("vrGeneral"));

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
}