package com.solucionesweb.losbeans.negocio;

// Importa la clase helper JDBCAccess que contiene los accesos a la base de datos
import com.solucionesweb.lasayudas.JDBCAccess;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaJobOperacion;

// Importa los paquetes de java
import java.sql.*;
import java.io.*;
import java.util.*;
import java.io.IOException.*;

// Importa los paquetes de javax
import javax.naming.*;

public class JobOperacionBean extends FachadaJobOperacion
        implements Serializable {

    // Nombre JNDI del Data Source que esta clase requiere
    private static final String DATA_SOURCE_NAME = "CommerceAccess";
    // Clase helper usada para acceder a la base de datos
    private JDBCAccess jdbcAccess;

    // Metodo constructor por defecto sin parametros
    public JobOperacionBean() {

        // Al instanciar el Bean establece la conexion a la base de datos
        jdbcAccess = new JDBCAccess(DATA_SOURCE_NAME);
    }

    // listaOperacion
    public Vector listaOperacion() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        Vector contenedor = new Vector();
        Connection connection = null;

        String selectStr =
                "SELECT tblJobOperacion.idOperacion        "
                + "      ,tblJobOperacion.nombreOperacion  "
                + "      ,tblJobOperacion.estado           "
                + "      ,tblJobOperacion.idSeq            "
                + "FROM tblJobOperacion                    "
                + "WHERE tblJobOperacion.idOperacion < 500 "
                + "ORDER BY tblJobOperacion.idOperacion    ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaJobOperacion fachadaBean;

            while (rs.next()) {

                fachadaBean = new FachadaJobOperacion();

                //
                fachadaBean.setIdOperacion(rs.getInt("idOperacion"));
                fachadaBean.setNombreOperacion(rs.getString("nombreOperacion"));
                fachadaBean.setEstado(rs.getInt("estado"));
                fachadaBean.setIdSeq(rs.getInt("idSeq"));

                //
                contenedor.add(fachadaBean);

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
            // Cierra la conexion a la base de datos limpiando la statement usada y la
            jdbcAccess.cleanup(connection, selectStatement, null);
            return contenedor;
        }
    }

    // listaOperacionOT
    public Vector listaOperacionOT() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        Vector contenedor = new Vector();
        Connection connection = null;

        String selectStr =
                "SELECT tblJobOperacion.idOperacion        "
                + "      ,tblJobOperacion.nombreOperacion  "
                + "      ,tblJobOperacion.estado           "
                + "      ,tblJobOperacion.idSeq            "
                + "FROM tblJobOperacion                    "
                + "ORDER BY tblJobOperacion.nombreOperacion";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaJobOperacion fachadaBean;

            while (rs.next()) {

                fachadaBean = new FachadaJobOperacion();

                //
                fachadaBean.setIdOperacion(rs.getInt("idOperacion"));
                fachadaBean.setNombreOperacion(rs.getString("nombreOperacion"));
                fachadaBean.setEstado(rs.getInt("estado"));
                fachadaBean.setIdSeq(rs.getInt("idSeq"));

                //
                contenedor.add(fachadaBean);

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
            // Cierra la conexion a la base de datos limpiando la statement usada y la
            jdbcAccess.cleanup(connection, selectStatement, null);
            return contenedor;
        }
    }

    // listaOperacionTouch
    public Vector listaOperacionTouch() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        Vector contenedor = new Vector();
        Connection connection = null;

        String selectStr =
                "SELECT tblJobOperacion.idOperacion        "
                + "      ,tblJobOperacion.nombreOperacion  "
                + "      ,tblJobOperacion.estado           "
                + "      ,tblJobOperacion.idSeq            "
                + "      ,tblJobOperacion.idColor          "
                + "FROM tblJobOperacion                    "
                + "WHERE tblJobOperacion.idOperacion       "
                + "                 IN (2,3,4,5,6)         "
                + "ORDER BY tblJobOperacion.nombreOperacion";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaJobOperacion fachadaBean;

            while (rs.next()) {

                fachadaBean = new FachadaJobOperacion();

                //
                fachadaBean.setIdOperacion(rs.getInt("idOperacion"));
                fachadaBean.setNombreOperacion(rs.getString("nombreOperacion"));
                fachadaBean.setEstado(rs.getInt("estado"));
                fachadaBean.setIdColor(rs.getString("idColor").trim());

                //
                contenedor.add(fachadaBean);

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
            // Cierra la conexion a la base de datos limpiando la statement usada y la
            jdbcAccess.cleanup(connection, selectStatement, null);
            return contenedor;
        }
    }

    // listaOperacionOpcion
    public Vector listaOperacionOpcion(String xIdOperacionCadena) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        Vector contenedor = new Vector();
        Connection connection = null;

        String selectStr =
                " SELECT TOP 1 00 AS idOperacion           "
                + "      ,'NN' AS nombreOperacion          "
                + "      ,0 AS estado                      "
                + "      ,0 AS idSeq                       "
                + "      ,1 AS ordenSalida                 "
                + " FROM tblJobOperacion                   "
                + " WHERE tblJobOperacion.idOperacion IN  ("
                + xIdOperacionCadena + ")                  "
                + "UNION                                   "
                + "SELECT tblJobOperacion.idOperacion      "
                + "      ,tblJobOperacion.nombreOperacion  "
                + "      ,tblJobOperacion.estado           "
                + "      ,tblJobOperacion.idSeq            "
                + "      ,2 AS ordenSalida                 "
                + " FROM tblJobOperacion                   "
                + " WHERE tblJobOperacion.idOperacion IN  ("
                + xIdOperacionCadena + ")                  "
                + "ORDER BY 5,2 ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaJobOperacion fachadaBean;

            while (rs.next()) {

                fachadaBean = new FachadaJobOperacion();

                //
                fachadaBean.setIdOperacion(rs.getInt("idOperacion"));
                fachadaBean.setNombreOperacion(rs.getString("nombreOperacion"));
                fachadaBean.setEstado(rs.getInt("estado"));
                fachadaBean.setIdSeq(rs.getInt("idSeq"));

                //
                contenedor.add(fachadaBean);

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
            // Cierra la conexion a la base de datos limpiando la statement usada y la
            jdbcAccess.cleanup(connection, selectStatement, null);
            return contenedor;
        }
    }

    // listaOperacionCambio
    public Vector listaOperacionCambio() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        Vector contenedor = new Vector();
        Connection connection = null;

        String selectStr =
                "SELECT tblJobOperacion.idOperacion        "
                + "      ,tblJobOperacion.nombreOperacion  "
                + "      ,tblJobOperacion.estado           "
                + "      ,tblJobOperacion.idSeq            "
                + "      ,1 AS ordenSalida                 "
                + " FROM tblJobOperacion                   "
                + " WHERE tblJobOperacion.idOperacion =    "
                + getIdOperacion() + "                     "
                + "UNION                                   "
                + "SELECT tblJobOperacion.idOperacion      "
                + "      ,tblJobOperacion.nombreOperacion  "
                + "      ,tblJobOperacion.estado           "
                + "      ,tblJobOperacion.idSeq            "
                + "      ,2 AS ordenSalida                 "
                + " FROM tblJobOperacion                   "
                + " WHERE tblJobOperacion.idOperacion !=   "
                + getIdOperacion() + "                     "
                + "ORDER BY 5,2  ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaJobOperacion fachadaBean;

            while (rs.next()) {

                fachadaBean = new FachadaJobOperacion();

                //
                fachadaBean.setIdOperacion(rs.getInt("idOperacion"));
                fachadaBean.setNombreOperacion(rs.getString("nombreOperacion"));
                fachadaBean.setEstado(rs.getInt("estado"));
                fachadaBean.setIdSeq(rs.getInt("idSeq"));

                //
                contenedor.add(fachadaBean);

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
            // Cierra la conexion a la base de datos limpiando la statement usada y la
            jdbcAccess.cleanup(connection, selectStatement, null);
            return contenedor;
        }
    }

    // listaUnaOperacion
    public Vector listaUnaOperacion() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        Vector contenedor = new Vector();
        Connection connection = null;

        String selectStr =
                "SELECT tblJobOperacion.idOperacion        "
                + "      ,tblJobOperacion.nombreOperacion  "
                + "      ,tblJobOperacion.estado           "
                + "      ,tblJobOperacion.idSeq            "
                + "FROM tblJobOperacion                    "
                + "WHERE tblJobOperacion.idOperacion  =    "
                + getIdOperacion();

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaJobOperacion fachadaBean;

            while (rs.next()) {

                fachadaBean = new FachadaJobOperacion();

                //
                fachadaBean.setIdOperacion(rs.getInt("idOperacion"));
                fachadaBean.setNombreOperacion(rs.getString("nombreOperacion"));
                fachadaBean.setEstado(rs.getInt("estado"));
                fachadaBean.setIdSeq(rs.getInt("idSeq"));

                //
                contenedor.add(fachadaBean);

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
            // Cierra la conexion a la base de datos limpiando la statement usada y la
            jdbcAccess.cleanup(connection, selectStatement, null);
            return contenedor;
        }
    }

    // listaUnaOperacionFCH
    public FachadaJobOperacion listaUnaOperacionFCH() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        // Variable de fachada de los datos
        FachadaJobOperacion fachadaBean = new FachadaJobOperacion();

        Connection connection = null;

        String selectStr =
                "SELECT tblJobOperacion.idOperacion        "
                + "      ,tblJobOperacion.nombreOperacion  "
                + "      ,tblJobOperacion.estado           "
                + "      ,tblJobOperacion.idSeq            "
                + "FROM tblJobOperacion                    "
                + "WHERE tblJobOperacion.idOperacion  =    "
                + getIdOperacion();

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
                fachadaBean.setIdOperacion(rs.getInt("idOperacion"));
                fachadaBean.setNombreOperacion(rs.getString("nombreOperacion"));
                fachadaBean.setEstado(rs.getInt("estado"));
                fachadaBean.setIdSeq(rs.getInt("idSeq"));

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
            // Cierra la conexion a la base de datos limpiando la statement usada y la
            jdbcAccess.cleanup(connection, selectStatement, null);
            return fachadaBean;
        }
    }

    // listaOperacionAnteriorActualFCH
    public FachadaJobOperacion listaOperacionAnteriorActualFCH(int xIdFicha,
            int xIdEscala) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        // Variable de fachada de los datos
        FachadaJobOperacion fachadaBean = new FachadaJobOperacion();

        //
        Connection connection = null;

        String selectStr =
                "SELECT TOP 1 tblPlusFicha.idOperacion           "
                + "              AS idOperacionAnterior,         "
                + "      ( SELECT tblJobOperacion.nombreOperacion"
                + "        FROM tblJobOperacion                  "
                + "        WHERE tblJobOperacion.idOperacion =   "
                + "                 tblPlusFicha.idOperacion )   "
                + "                AS nombreOperacionAnterior,   "
                + "       tblPlusFicha.vrEscala                  "
                + "              AS idOperacionActual,           "
                + "      ( SELECT tblJobOperacion.nombreOperacion"
                + "        FROM tblJobOperacion                  "
                + "        WHERE tblJobOperacion.idOperacion =   "
                + "                 tblPlusFicha.vrEscala )      "
                + "                AS nombreOperacionActual      "
                + "FROM   tblPlusFicha                           "
                + "INNER JOIN tblJobOperacion                    "
                + "ON tblPlusFicha.idOperacion =                 "
                + "        tblJobOperacion.idOperacion           "
                + "WHERE   tblPlusFicha.idFicha =                "
                + xIdFicha + "                                   "
                + "AND tblPlusFicha.idEscala =                   "
                + xIdEscala + "                                  "
                + "AND tblPlusFicha.vrEscala =                   "
                + getIdOperacion();

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
                fachadaBean.setIdOperacionAnterior(
                        rs.getInt("idOperacionAnterior"));
                fachadaBean.setNombreOperacionAnterior(
                        rs.getString("nombreOperacionAnterior"));
                fachadaBean.setIdOperacion(rs.getInt("idOperacionActual"));
                fachadaBean.setNombreOperacion(
                        rs.getString("nombreOperacionActual"));

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
            // Cierra la conexion a la base de datos limpiando la statement usada y la
            jdbcAccess.cleanup(connection, selectStatement, null);
            return fachadaBean;
        }
    }

    // listaOperacionActualAnterior
    public Vector listaOperacionAnteriorActual(int xIdFicha,
            int xIdEscala) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        Vector contenedor = new Vector();
        Connection connection = null;

        String selectStr =
                "SELECT TOP 1 tblPlusFicha.idOperacion           "
                + "              AS idOperacionAnterior,         "
                + "      ( SELECT tblJobOperacion.nombreOperacion"
                + "        FROM tblJobOperacion                  "
                + "        WHERE tblJobOperacion.idOperacion =   "
                + "                 tblPlusFicha.idOperacion )   "
                + "                AS nombreOperacionAnterior,   "
                + "       tblPlusFicha.vrEscala                  "
                + "              AS idOperacionActual,           "
                + "      ( SELECT tblJobOperacion.nombreOperacion"
                + "        FROM tblJobOperacion                  "
                + "        WHERE tblJobOperacion.idOperacion =   "
                + "                 tblPlusFicha.vrEscala )      "
                + "                AS nombreOperacionActual      "
                + "FROM   tblPlusFicha                           "
                + "INNER JOIN tblJobOperacion                    "
                + "ON tblPlusFicha.idOperacion =                 "
                + "        tblJobOperacion.idOperacion           "
                + "WHERE   tblPlusFicha.idFicha =                "
                + xIdFicha + "                                   "
                + "AND tblPlusFicha.idEscala =                   "
                + xIdEscala + "                                  "
                + "AND tblPlusFicha.vrEscala =                   "
                + getIdOperacion();

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaJobOperacion fachadaBean;

            //
            if (rs.next()) {

                fachadaBean = new FachadaJobOperacion();

                //
                fachadaBean.setIdOperacionAnterior(
                        rs.getInt("idOperacionAnterior"));
                fachadaBean.setNombreOperacionAnterior(
                        rs.getString("nombreOperacionAnterior"));
                fachadaBean.setIdOperacion(rs.getInt("idOperacionActual"));
                fachadaBean.setNombreOperacion(
                        rs.getString("nombreOperacionActual"));

                //
                contenedor.add(fachadaBean);

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
            // Cierra la conexion a la base de datos limpiando la statement usada y la
            jdbcAccess.cleanup(connection, selectStatement, null);
            return contenedor;
        }
    }

    // listaOperacionActualSiguienteFCH
    public FachadaJobOperacion listaOperacionActualSiguienteFCH(int xIdFicha,
            int xIdEscala) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        // Variable de fachada de los datos
        FachadaJobOperacion fachadaBean = new FachadaJobOperacion();


        Connection connection = null;

        String selectStr =
                "SELECT TOP 1  tblPlusFicha.idOperacion          "
                + "              AS idOperacionActual,           "
                + "      ( SELECT tblJobOperacion.nombreOperacion"
                + "        FROM tblJobOperacion                  "
                + "        WHERE tblJobOperacion.idOperacion =   "
                + "                 tblPlusFicha.idOperacion )   "
                + "                AS nombreOperacionActual,     "
                + "       tblPlusFicha.vrEscala                  "
                + "              AS idOperacionSiguiente,        "
                + "      ( SELECT tblJobOperacion.nombreOperacion"
                + "        FROM tblJobOperacion                  "
                + "        WHERE tblJobOperacion.idOperacion =   "
                + "                 tblPlusFicha.vrEscala )      "
                + "                AS nombreOperacionSiguiente   "
                + "FROM   tblPlusFicha                           "
                + "INNER JOIN tblJobOperacion                    "
                + "ON tblPlusFicha.idOperacion =                 "
                + "        tblJobOperacion.idOperacion           "
                + "WHERE   tblPlusFicha.idFicha =                "
                + xIdFicha + "                                   "
                + "AND tblPlusFicha.idEscala =                   "
                + xIdEscala + "                                  "
                + "AND tblPlusFicha.idOperacion =                "
                + getIdOperacion();

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
                fachadaBean.setIdOperacionSiguiente(
                        rs.getInt("idOperacionSiguiente"));
                fachadaBean.setNombreOperacionSiguiente(
                        rs.getString("nombreOperacionSiguiente"));
                fachadaBean.setIdOperacion(rs.getInt("idOperacionActual"));
                fachadaBean.setNombreOperacion(
                        rs.getString("nombreOperacionActual"));

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
            // Cierra la conexion a la base de datos limpiando la statement usada y la
            jdbcAccess.cleanup(connection, selectStatement, null);
            return fachadaBean;
        }
    }

    // listaOperacionActualSiguiente
    public Vector listaOperacionActualSiguiente(int xIdFicha,
            int xIdEscala) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        // Variable de fachada de los datos
        Vector contenedor = new Vector();

        Connection connection = null;

        String selectStr =
                "SELECT TOP 1  tblPlusFicha.idOperacion          "
                + "              AS idOperacionActual,           "
                + "      ( SELECT tblJobOperacion.nombreOperacion"
                + "        FROM tblJobOperacion                  "
                + "        WHERE tblJobOperacion.idOperacion =   "
                + "                 tblPlusFicha.idOperacion )   "
                + "                AS nombreOperacionActual,     "
                + "       tblPlusFicha.vrEscala                  "
                + "              AS idOperacionSiguiente,        "
                + "      ( SELECT tblJobOperacion.nombreOperacion"
                + "        FROM tblJobOperacion                  "
                + "        WHERE tblJobOperacion.idOperacion =   "
                + "                 tblPlusFicha.vrEscala )      "
                + "                AS nombreOperacionSiguiente   "
                + "FROM   tblPlusFicha                           "
                + "INNER JOIN tblJobOperacion                    "
                + "ON tblPlusFicha.idOperacion =                 "
                + "        tblJobOperacion.idOperacion           "
                + "WHERE   tblPlusFicha.idFicha =                "
                + xIdFicha + "                                   "
                + "AND tblPlusFicha.idEscala =                   "
                + xIdEscala + "                                  "
                + "AND tblPlusFicha.idOperacion =                "
                + getIdOperacion();

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            //
            FachadaJobOperacion fachadaBean;

            //
            if (rs.next()) {

                //
                fachadaBean = new FachadaJobOperacion();

                //
                fachadaBean.setIdOperacionSiguiente(
                        rs.getInt("idOperacionSiguiente"));
                fachadaBean.setNombreOperacionSiguiente(
                        rs.getString("nombreOperacionSiguiente"));
                fachadaBean.setIdOperacion(rs.getInt("idOperacionActual"));
                fachadaBean.setNombreOperacion(
                        rs.getString("nombreOperacionActual"));

                //
                contenedor.add(fachadaBean);

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
            // Cierra la conexion a la base de datos limpiando la statement usada y la
            jdbcAccess.cleanup(connection, selectStatement, null);
            return contenedor;
        }
    }

    // listaOperacionSiguiente
    public Vector listaOperacionSiguiente(int xIdFicha) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        Vector contenedor = new Vector();

        //
        Connection connection = null;

        String selectStr =
                " SELECT tblPlusFicha.idOperacion        "
                + "               AS idOperacion,        "
                + "    tblJobOperacion.nombreOperacion,  "
                + "        tblPlusFicha.vrEscala         "
                + "             AS idOperacionSiguiente  "
                + " FROM tblPlusFicha                    "
                + " INNER JOIN tblJobOperacion           "
                + " ON  tblPlusFicha.idOperacion =       "
                + "        tblJobOperacion.idOperacion   "
                + " WHERE tblPlusFicha.idFicha   =       "
                + xIdFicha + "                           "
                + " AND tblPlusFicha.idEscala    = 610   "
                + " ORDER BY tblPlusFicha.idOperacion    ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaJobOperacion fachadaBean;

            //
            while (rs.next()) {

                //
                fachadaBean = new FachadaJobOperacion();

                //
                fachadaBean.setIdOperacionSiguiente(
                        rs.getInt("idOperacionSiguiente"));
                fachadaBean.setIdOperacion(rs.getInt("idOperacion"));
                fachadaBean.setNombreOperacion(rs.getString("nombreOperacion"));

                //
                contenedor.add(fachadaBean);

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
            // Cierra la conexion a la base de datos limpiando la statement usada y la
            jdbcAccess.cleanup(connection, selectStatement, null);
            return contenedor;
        }
    }
}
