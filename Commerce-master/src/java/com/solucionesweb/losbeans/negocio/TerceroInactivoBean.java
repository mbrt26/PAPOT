package com.solucionesweb.losbeans.negocio;

// Importa la clase helper JDBCAccess que contiene los accesos a la base de datos
import com.solucionesweb.lasayudas.JDBCAccess;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaTerceroInactivoBean;

// Importa los paquetes de java
import java.sql.*;
import java.io.*;
import java.util.*;
import java.io.IOException.*;

// Importa los paquetes de javax
import javax.naming.*;

// Importa la interface de las constantes
import com.solucionesweb.losbeans.utilidades.IConstantes;

public class TerceroInactivoBean extends FachadaTerceroInactivoBean
                                          implements Serializable, IConstantes {

  	// Nombre JNDI del Data Source que esta clase requiere
    private static final String DATA_SOURCE_NAME = "CommerceAccess";

	// Clase helper usada para acceder a la base de datos
	private JDBCAccess jdbcAccess;

	// Metodo constructor por defecto sin parametros
    public TerceroInactivoBean() {

  	// Al instanciar el Bean establece la conexion a la base de datos
	   jdbcAccess = new JDBCAccess(DATA_SOURCE_NAME);
    }


    // ingresaTercero
    public boolean ingresaTercero() {

      //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
      Class tipoObjeto       = this.getClass();
      String nombreClase     = tipoObjeto.getName();
      boolean okIngresar     = false;

      Vector contenedor      = new Vector();
      Connection connection  = null;


      String selectStr =
            "INSERT INTO tblTercerosInactivos (idCliente,     " +
            "                                  idUsuario,     " +
            "                                  fechaInactivo, " +
            "                                  estado)        " +
            "VALUES (?,?,?,?)                                 " ;

      PreparedStatement selectStatement = null;

      try {

        // Obtiene una conexion a la base de datos, requiere Try
        connection = jdbcAccess.getConnection();

        // Prepara la sentencia de Busqueda
        selectStatement = connection.prepareStatement(selectStr);

        //
	    selectStatement.setString(1,getIdCliente());
	    selectStatement.setDouble(2,getIdUsuario());
	    selectStatement.setString(3,getFechaInactivo());
	    selectStatement.setInt(4,getEstado());

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

        // Cierra el connection
        jdbcAccess.cleanup(connection, selectStatement,null);
        return okIngresar;
      }
    }

    //retira
    public boolean retira() {

      //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
      Class tipoObjeto       = this.getClass();
      String nombreClase     = tipoObjeto.getName();
      boolean okIngresar     = false;

      Vector contenedor      = new Vector();
      Connection connection  = null;

      String selectStr =
      "DELETE FROM tblTercerosInactivos             " +
      "WHERE tblTercerosInactivos.idCliente = ( ? ) " ;

      PreparedStatement selectStatement = null;

      try {

        // Obtiene una conexion a la base de datos, requiere Try
        connection = jdbcAccess.getConnection();

        // Prepara la sentencia de Busqueda
        selectStatement = connection.prepareStatement(selectStr);

        //
 	    selectStatement.setString(1,getIdCliente());

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
}