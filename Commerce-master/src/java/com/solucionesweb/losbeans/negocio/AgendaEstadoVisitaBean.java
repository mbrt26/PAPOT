package com.solucionesweb.losbeans.negocio;

// Importa la clase helper JDBCAccess que contiene los accesos a la base de datos
import com.solucionesweb.lasayudas.JDBCAccess;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaAgendaEstadoVisitaBean;

// Importa los paquetes de java
import java.sql.*;
import java.io.*;
import java.util.*;
import java.io.IOException.*;

// Importa los paquetes de javax
import javax.naming.*;

public class AgendaEstadoVisitaBean   extends FachadaAgendaEstadoVisitaBean
                                          implements Serializable {

  	// Nombre JNDI del Data Source que esta clase requiere
    private static final String DATA_SOURCE_NAME = "CommerceAccess";

	// Clase helper usada para acceder a la base de datos
	private JDBCAccess jdbcAccess;

	// Metodo constructor por defecto sin parametros
    public AgendaEstadoVisitaBean() {

  	// Al instanciar el Bean establece la conexion a la base de datos
	   jdbcAccess = new JDBCAccess(DATA_SOURCE_NAME);
    }

    // listaEstadosVisitas
    public Vector listaEstadosVisitas() {

      //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
      Class tipoObjeto       = this.getClass();
      String nombreClase     = tipoObjeto.getName();
      Vector contenedor      = new Vector();

      boolean    validaOk    = false;
      Connection connection  = null;

      String selectStr =
        "SELECT tblAgendaEstadosVisitas.idEstadoVisita,  " +
        "       tblAgendaEstadosVisitas.nombreEstado,    " +
        "       tblAgendaEstadosVisitas.estado           " +
        "FROM tblAgendaEstadosVisitas                    " +
        "ORDER BY tblAgendaEstadosVisitas.idEstadoVisita " ;

      PreparedStatement selectStatement = null;

      try {

        // Obtiene una conexion a la base de datos, requiere Try
        connection = jdbcAccess.getConnection();

        // Prepara la sentencia de Busqueda
        selectStatement = connection.prepareStatement(selectStr);

        // Obtiene el resultset
        ResultSet rs = selectStatement.executeQuery();

        // Variable de fachada de los datos
        FachadaAgendaEstadoVisitaBean fachadaBean;

        while (rs.next()) {

              fachadaBean = new FachadaAgendaEstadoVisitaBean();
              fachadaBean.setIdEstadoVisita(rs.getInt("idEstadoVisita"));
              fachadaBean.setNombreEstado(rs.getString("nombreEstado"));
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

    // seleccionaUnEstadoVisita
    public boolean seleccionaUnEstadoVisita() {

      //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
      Class tipoObjeto       = this.getClass();
      String nombreClase     = tipoObjeto.getName();
      Vector contenedor      = new Vector();

      boolean    validaOk    = false;
      Connection connection  = null;

      String selectStr =
        "SELECT tblAgendaEstadosVisitas.idEstadoVisita,       " +
        "       tblAgendaEstadosVisitas.nombreEstado,         " +
        "       tblAgendaEstadosVisitas.estado                " +
        "FROM tblAgendaEstadosVisitas                         " +
        "WHERE tblAgendaEstadosVisitas.idEstadoVisita = ( ? ) " ;

      PreparedStatement selectStatement = null;

      try {

        // Obtiene una conexion a la base de datos, requiere Try
        connection = jdbcAccess.getConnection();

        // Prepara la sentencia de Busqueda
        selectStatement = connection.prepareStatement(selectStr);

       // inicializa los valores de los parametros
       selectStatement.setInt(1,getIdEstadoVisita());

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