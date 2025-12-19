package com.solucionesweb.losbeans.negocio;

// Importa la clase helper JDBCAccess que contiene los accesos a la base de datos
import com.solucionesweb.lasayudas.JDBCAccess;



// Importa los paquetes de java
import com.solucionesweb.losbeans.fachada.FachadaTipoCausaNota;

import java.sql.*;
import java.io.*;
import java.util.*;
import java.io.IOException.*;

// Importa los paquetes de javax
import javax.naming.*;

public class TipoCausaNotaBean extends FachadaTipoCausaNota
                                                       implements Serializable {

  	// Nombre JNDI del Data Source que esta clase requiere
    private static final String DATA_SOURCE_NAME = "CommerceAccess";

	// Clase helper usada para acceder a la base de datos
	private JDBCAccess jdbcAccess;

	// Metodo constructor por defecto sin parametros
    public TipoCausaNotaBean() {

  	// Al instanciar el Bean establece la conexion a la base de datos
	   jdbcAccess = new JDBCAccess(DATA_SOURCE_NAME);
    }

    // Metodo
    public Vector listaCausaNota() {

      //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
      Class tipoObjeto       = this.getClass();
      String nombreClase     = tipoObjeto.getName();

      Vector contenedor      = new Vector();
      Connection connection  = null;

      String selectStr =
      
        " SELECT 00 AS idCausa                  "+
        "       ,'NN' AS nombreCausa            "+
        "       ,01 AS ordenSalida              "+
        " FROM tblTipoCausaNota                 "+
        " UNION                                 "+
        " SELECT tblTipoCausaNota.idCausa       "+
        "      ,tblTipoCausaNota.nombreCausa    "+
        "      ,02 AS ordenSalida               "+
        " FROM tblTipoCausaNota                 "+
        " ORDER BY 3, 2                         ";

      PreparedStatement selectStatement = null;

      try {

        // Obtiene una conexion a la base de datos, requiere Try
        connection = jdbcAccess.getConnection();

        // Prepara la sentencia de Busqueda
        selectStatement = connection.prepareStatement(selectStr);

        // Obtiene el resultset
        ResultSet rs = selectStatement.executeQuery();

        // Variable de fachada de los datos
        FachadaTipoCausaNota fachadaTipoCausaNota;

        while (rs.next()) {

              fachadaTipoCausaNota = new FachadaTipoCausaNota();
              
              fachadaTipoCausaNota.setIdCausa(rs.getInt("idCausa"));
              fachadaTipoCausaNota.setNombreCausa(rs.getString("nombreCausa"));

              contenedor.add(fachadaTipoCausaNota);

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
    public Vector listaCausaNotaAll() {

      //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
      Class tipoObjeto       = this.getClass();
      String nombreClase     = tipoObjeto.getName();

      Vector contenedor      = new Vector();
      Connection connection  = null;

      String selectStr =

        " SELECT                                 "+
        "        tblTipoCausaNota.idCausa        "+
        "       ,tblTipoCausaNota.nombreCausa    "+
        "       ,tblTipoCausaNota.estado         "+
        "       ,tblTipoCausaNota.idSeq          "+
        " FROM tblTipoCausaNota                  ";

      PreparedStatement selectStatement = null;

      try {

        // Obtiene una conexion a la base de datos, requiere Try
        connection = jdbcAccess.getConnection();

        // Prepara la sentencia de Busqueda
        selectStatement = connection.prepareStatement(selectStr);

        // Obtiene el resultset
        ResultSet rs = selectStatement.executeQuery();

        // Variable de fachada de los datos
        FachadaTipoCausaNota fachadaTipoCausaNota;

        while (rs.next()) {

              fachadaTipoCausaNota = new FachadaTipoCausaNota();

              fachadaTipoCausaNota.setIdCausa(rs.getInt("idCausa"));
              fachadaTipoCausaNota.setNombreCausa(rs.getString("nombreCausa"));
              fachadaTipoCausaNota.setEstado(rs.getInt("estado"));
              fachadaTipoCausaNota.setIdSeq(rs.getInt("idSeq"));

              contenedor.add(fachadaTipoCausaNota);

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

}