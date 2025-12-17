package com.solucionesweb.losbeans.colaboraciones;

// Importa la clase helper JDBCAccess que contiene los accesos a la base de datos
import com.solucionesweb.lasayudas.JDBCAccess;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaPluCombo;

// Importa los paquetes de java
import java.sql.*;
import java.io.*;
import java.util.*;
import java.io.IOException.*;

// Importa los paquetes de javax
import javax.naming.*;

public class ColaboraPluCombo extends FachadaPluCombo
        implements Serializable {

    // Nombre JNDI del Data Source que esta clase requiere
    private static final String DATA_SOURCE_NAME = "CommerceAccess";

    // Clase helper usada para acceder a la base de datos
    private JDBCAccess jdbcAccess;

    // Metodo constructor por defecto sin parametros
    public ColaboraPluCombo() {

        // Al instanciar el Bean establece la conexion a la base de datos
        jdbcAccess = new JDBCAccess(DATA_SOURCE_NAME);
    }

    // Metodo listaLocales
    public Vector listaUnCombo() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        //
        Connection connection = null;

        //
        String selectStr =
               "SELECT tblPlusCombo.idPluCombo,      " +
               "       tblPlusCombo.idPlu,           " +
               "       tblPlus.nombrePlu,            " +
               "       tblPlusCombo.cantidad,        " +
               "       tblPlusCombo.estado,          " +
               "       tblCategorias.nombreCategoria " +
               "FROM   tblPlus                       " +
               "INNER JOIN tblPlusCombo              " +
               "ON tblPlus.idPlu        =            " +
               "                 tblPlusCombo.idPlu  " +
               "INNER JOIN tblCategorias             " +
               "ON tblPlus.idLinea      =            " +
               "              tblCategorias.idLinea  " +
               "AND tblPlus.idCategoria =            " +
               "          tblCategorias.IdCategoria  " +
               "WHERE tblPlusCombo.idPluCombo =      " +
               getIdPluCombo() + "                   " +
               "AND   tblPlusCombo.estado     =  1   " +
               "ORDER BY tblPlus.nombrePlu           " ;

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaPluCombo fachadaPluCombo;

            while (rs.next()) {

                //
                fachadaPluCombo = new FachadaPluCombo();

                //
                fachadaPluCombo.setIdPluCombo(rs.getInt("idPluCombo"));
                fachadaPluCombo.setIdPlu(rs.getInt("idPlu"));
                fachadaPluCombo.setNombrePlu(rs.getString("nombrePlu"));
                fachadaPluCombo.setCantidad(rs.getDouble("cantidad"));
                fachadaPluCombo.setEstado(rs.getInt("estado"));
                fachadaPluCombo.setNombreCategoria(
                                               rs.getString("nombreCategoria"));

                //
                contenedor.add(fachadaPluCombo);

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
            return contenedor;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {

            // Cierra la conexion
            jdbcAccess.cleanup(connection, selectStatement, null);
            return contenedor;

        }
    }

    // Metodo listaUnComboPlu
    public FachadaPluCombo listaUnComboPluFCH() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        //
        FachadaPluCombo fachadaPluCombo
                                 = new FachadaPluCombo();

        //
        Connection connection    = null;

        //
        String selectStr =
               "SELECT tblPlusCombo.idPluCombo,      " +
               "       tblPlusCombo.idPlu,           " +
               "       tblPlus.nombrePlu,            " +
               "       tblPlusCombo.cantidad,        " +
               "       tblPlusCombo.estado,          " +
               "       tblCategorias.nombreCategoria " +
               "FROM   tblPlus                       " +
               "INNER JOIN tblPlusCombo              " +
               "ON tblPlus.idPlu        =            " +
               "                 tblPlusCombo.idPlu  " +
               "INNER JOIN tblCategorias             " +
               "ON tblPlus.idLinea      =            " +
               "              tblCategorias.idLinea  " +
               "AND tblPlus.idCategoria =            " +
               "          tblCategorias.IdCategoria  " +
               "WHERE tblPlusCombo.idPluCombo =      " +
               getIdPluCombo() + "                   " +
               "AND   tblPlusCombo.idPlu      =      " +
               getIdPlu() + "                        " +
               "ORDER BY tblPlus.nombrePlu           " ;

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
                fachadaPluCombo.setIdPluCombo(rs.getInt("idPluCombo"));
                fachadaPluCombo.setIdPlu(rs.getInt("idPlu"));
                fachadaPluCombo.setNombrePlu(rs.getString("nombrePlu"));
                fachadaPluCombo.setCantidad(rs.getDouble("cantidad"));
                fachadaPluCombo.setEstado(rs.getInt("estado"));
                fachadaPluCombo.setNombreCategoria(
                                               rs.getString("nombreCategoria"));

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

            // Cierra la conexion
            jdbcAccess.cleanup(connection, selectStatement, null);
            return fachadaPluCombo;

        }
    }
}
