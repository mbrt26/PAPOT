package com.solucionesweb.losbeans.negocio;

// Importa la clase helper JDBCAccess que contiene los accesos a la base de datos
import com.solucionesweb.lasayudas.JDBCAccess;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaPluPrecio;

// Importa los paquetes de java
import java.sql.*;
import java.io.*;
import java.util.*;
import java.io.IOException.*;

// Importa los paquetes de javax
import javax.naming.*;

// Importa la interface de las constantes
import com.solucionesweb.losbeans.utilidades.IConstantes;

public class PluPrecioBean extends FachadaPluPrecio
        implements Serializable, IConstantes {

    // Nombre JNDI del Data Source que esta clase requiere
    private static final String DATA_SOURCE_NAME = "CommerceAccess";

    // Clase helper usada para acceder a la base de datos
    private JDBCAccess jdbcAccess;

    // Metodo constructor por defecto sin parametros
    public PluPrecioBean() {

        // Al instanciar el Bean establece la conexion a la base de datos
        jdbcAccess = new JDBCAccess(DATA_SOURCE_NAME);
    }

    // listaPluPrecioFCH
    public FachadaPluPrecio listaPluPrecioFCH() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto                   = this.getClass();
        String nombreClase                 = tipoObjeto.getName();

        Connection connection              = null;

        //
        FachadaPluPrecio fachadaPluPrecio  =  new FachadaPluPrecio();

        //
        String selectStr =
                "SELECT tblPlusPrecios.idLocal,       " +
                "       tblPlusPrecios.idListaPrecio, " +
                "       tblPlusPrecios.nombreLista,   " +
                "       tblPlusPrecios.idListaBase,   " +
                "       tblPlusPrecios.factorBase,    " +
                "       tblPlusPrecios.estado         " +
                "FROM tblPlusPrecios                  " +
                "WHERE tblPlusPrecios.idLocal       = " +
                getIdLocal() + "                      " +
                "AND   tblPlusPrecios.idListaPrecio = " +
                getIdListaPrecio();

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
                fachadaPluPrecio.setIdLocal(rs.getInt("idLocal"));
                fachadaPluPrecio.setIdListaPrecio(rs.getInt("idListaPrecio"));
                fachadaPluPrecio.setNombreLista(rs.getString("nombreLista"));
                fachadaPluPrecio.setIdListaBase(rs.getInt("idListaBase"));
                fachadaPluPrecio.setFactorBase(rs.getDouble("factorBase"));
                fachadaPluPrecio.setEstado(rs.getInt("estado"));

            }

            // Cierra el Resultset
            rs.close();

        } catch (NamingException ne) {
            System.out.println("NamingException in " + nombreClase + " " + ne);
        } catch (SQLException sqle) {

            System.out.println("SQLException in" + nombreClase);

            String sqlMessage = sqle.getMessage();
            String sqlState = sqle.getSQLState();
            int vendorCode = sqle.getErrorCode();
            System.out.println("Ocurrio una  excepcion");
            System.out.println("Mensaje " + sqlMessage);
            System.out.println("SQL State " + sqlState);
            System.out.println("Vendor Code " + vendorCode);

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {

            //
            jdbcAccess.cleanup(connection, selectStatement, null);
            return fachadaPluPrecio;

        }
    }

    // Metodo
    public Vector listaUn() {

      //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
      Class tipoObjeto       = this.getClass();
      String nombreClase     = tipoObjeto.getName();

      Vector contenedor      = new Vector();
      Connection connection  = null;

      String selectStr =
                "SELECT tblPlusPrecios.idLocal,        " +
                "       tblPlusPrecios.idListaPrecio,  " +
                "       tblPlusPrecios.nombreLista,    " +
                "       tblPlusPrecios.idListaBase,    " +
                "       tblPlusPrecios.factorBase,     " +
                "       tblPlusPrecios.estado,         " +
                "       01 AS ordenSalida              " +
                "FROM tblPlusPrecios                   " +
                "WHERE tblPlusPrecios.idLocal       =  " +
                getIdLocal() + "                       " +
                "AND   tblPlusPrecios.idListaPrecio =  " +
                getIdListaPrecio() + "                 " +
                "UNION                                 " +
                "SELECT tblPlusPrecios.idLocal,        " +
                "       tblPlusPrecios.idListaPrecio,  " +
                "       tblPlusPrecios.nombreLista,    " +
                "       tblPlusPrecios.idListaBase,    " +
                "       tblPlusPrecios.factorBase,     " +
                "       tblPlusPrecios.estado,         " +
                "       02 AS ordenSalida              " +
                "FROM tblPlusPrecios                   " +
                "WHERE tblPlusPrecios.idLocal       =  " +
                getIdLocal() + "                       " +
                "AND   tblPlusPrecios.idListaPrecio != " +
                getIdListaPrecio() + "                 " +
                "ORDER BY 7,2                          " ;


      PreparedStatement selectStatement = null;

      try {

        // Obtiene una conexion a la base de datos, requiere Try
        connection = jdbcAccess.getConnection();

        // Prepara la sentencia de Busqueda
        selectStatement = connection.prepareStatement(selectStr);

        // Obtiene el resultset
        ResultSet rs = selectStatement.executeQuery();

        // Variable de fachada de los datos
        FachadaPluPrecio fachadaPluPrecio;

        while (rs.next()) {

                //
                fachadaPluPrecio = new FachadaPluPrecio();

                //
                fachadaPluPrecio.setIdLocal(rs.getInt("idLocal"));
                fachadaPluPrecio.setIdListaPrecio(rs.getInt("idListaPrecio"));
                fachadaPluPrecio.setNombreLista(rs.getString("nombreLista"));
                fachadaPluPrecio.setIdListaBase(rs.getInt("idListaBase"));
                fachadaPluPrecio.setFactorBase(rs.getDouble("factorBase"));
                fachadaPluPrecio.setEstado(rs.getInt("estado"));

              contenedor.add(fachadaPluPrecio);

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
        jdbcAccess.cleanup(connection, selectStatement,null);
        return contenedor;
      }
    }
}
