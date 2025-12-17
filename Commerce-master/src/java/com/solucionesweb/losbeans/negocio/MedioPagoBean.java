package com.solucionesweb.losbeans.negocio;

// Importa la clase helper JDBCAccess que contiene los accesos a la base de datos
import com.solucionesweb.lasayudas.JDBCAccess;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaMedioPagoBean;

// Importa los paquetes de java
import java.sql.*;
import java.io.*;
import java.util.*;
import java.io.IOException.*;

// Importa los paquetes de javax
import javax.naming.*;

public class MedioPagoBean extends FachadaMedioPagoBean
                                                       implements Serializable {

    // Nombre JNDI del Data Source que esta clase requiere
    private static final String DATA_SOURCE_NAME = "CommerceAccess";

    // Clase helper usada para acceder a la base de datos
    private JDBCAccess jdbcAccess;

    // Metodo constructor por defecto sin parametros
    public MedioPagoBean() {

        // Al instanciar el Bean establece la conexion a la base de datos
        jdbcAccess = new JDBCAccess(DATA_SOURCE_NAME);
    }

    // listaAll
    public Vector listaAll() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        String selectStr =
                "SELECT tblMediosPago.idMedio     " +
                "      ,tblMediosPago.nombreMedio " +
                "      ,tblMediosPago.estado      " +
                "FROM tblMediosPago               " +
                "ORDER BY tblMediosPago.idMedio   ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);


            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaMedioPagoBean fachadaMedioPagoBean;

            while (rs.next()) {

                fachadaMedioPagoBean = new FachadaMedioPagoBean();

                //
                fachadaMedioPagoBean.setIdMedio(rs.getInt("idMedio"));
                fachadaMedioPagoBean.setNombreMedio(
                                                   rs.getString("nombreMedio"));
                fachadaMedioPagoBean.setEstado(rs.getInt("estado"));

                //
                contenedor.add(fachadaMedioPagoBean);

            }

            // Cierra el Resultset
            rs.close();
            jdbcAccess.cleanup(connection, selectStatement, null);

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
            return contenedor;

        }
    }

    // listaAllOpcion
    public Vector listaAllOpcion() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        String selectStr =
                "SELECT TOP 1 00 AS idMedio       " +
                "      ,'NN' AS nombreMedio       " +
                "      ,tblMediosPago.estado,     " +
                "      01 AS ordenSalida          " +
                "FROM tblMediosPago               " +
                "UNION                            " +
                "SELECT tblMediosPago.idMedio     " +
                "      ,tblMediosPago.nombreMedio " +
                "      ,tblMediosPago.estado,     " +
                "      02 ordenSalida             " +
                "FROM tblMediosPago               " +
                "ORDER BY 4,1                     ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);


            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaMedioPagoBean fachadaMedioPagoBean;

            while (rs.next()) {

                fachadaMedioPagoBean = new FachadaMedioPagoBean();

                //
                fachadaMedioPagoBean.setIdMedio(rs.getInt("idMedio"));
                fachadaMedioPagoBean.setNombreMedio(
                                                   rs.getString("nombreMedio"));
                fachadaMedioPagoBean.setEstado(rs.getInt("estado"));

                //
                contenedor.add(fachadaMedioPagoBean);

            }

            // Cierra el Resultset
            rs.close();
            jdbcAccess.cleanup(connection, selectStatement, null);

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
            return contenedor;

        }
    }
}