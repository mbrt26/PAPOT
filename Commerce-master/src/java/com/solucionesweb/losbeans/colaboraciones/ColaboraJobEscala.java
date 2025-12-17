package com.solucionesweb.losbeans.colaboraciones;

// Importa la clase helper JDBCAccess que contiene los accesos a la base de datos
import com.solucionesweb.lasayudas.JDBCAccess;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaJobEscala;

// Importa los paquetes de java
import java.sql.*;
import java.io.*;
import java.util.*;
import java.io.IOException.*;

// Importa los paquetes de javax
import javax.naming.*;

public class ColaboraJobEscala extends FachadaJobEscala
        implements Serializable {

    // Nombre JNDI del Data Source que esta clase requiere
    private static final String DATA_SOURCE_NAME = "CommerceAccess";
    // Clase helper usada para acceder a la base de datos
    private JDBCAccess jdbcAccess;

    // Metodo constructor por defecto sin parametros
    // Metodo constructor por defecto sin parametros
    public ColaboraJobEscala() {

        // Al instanciar el Bean establece la conexion a la base de datos
        jdbcAccess = new JDBCAccess(DATA_SOURCE_NAME);

    }

    // listaEscala
    public Vector listaEscala() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        //
        String selectStr
                = "SELECT tblJobEscala.idEscala,              "
                + "       tblJobEscala.nombreEscala,        "
                + "       tblJobEscalaDetalle.item,         "
                + "       tblJobEscalaDetalle.nombreItem,   "
                + "       tblJobEscalaDetalle.estado        "
                + "FROM   tblJobEscala                      "
                + "INNER JOIN tblJobEscalaDetalle           "
                + "ON tblJobEscala.idEscala =               "
                + "         tblJobEscalaDetalle.idEscala    "
                + "WHERE tblJobEscala.idEscala =            "
                + getIdEscala() + "                         "
                + "ORDER BY tblJobEscalaDetalle.nombreItem, "
                + "         tblJobEscalaDetalle.item        ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaJobEscala fachadaBean;

            //
            while (rs.next()) {

                //
                fachadaBean = new FachadaJobEscala();

                //
                fachadaBean.setIdEscala(rs.getInt("idEscala"));
                fachadaBean.setNombreEscala(rs.getString("nombreEscala"));
                fachadaBean.setItem(rs.getInt("item"));
                fachadaBean.setNombreItem(rs.getString("nombreItem"));
                fachadaBean.setEstado(rs.getInt("estado"));

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

            // Cierra la conexion
            jdbcAccess.cleanup(connection, selectStatement, null);
            return contenedor;

        }
    }

    // listaEscalaOpcion
    public Vector listaEscalaOpcion() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        //
        String selectStr
                = "SELECT tblJobEscala.idEscala,              "
                + "       tblJobEscala.nombreEscala,        "
                + "       tblJobEscalaDetalle.item,         "
                + "       tblJobEscalaDetalle.nombreItem,   "
                + "       tblJobEscalaDetalle.estado,       "
                + "       01 AS ordenSalida                 "
                + "FROM   tblJobEscala                      "
                + "INNER JOIN tblJobEscalaDetalle           "
                + "ON tblJobEscala.idEscala =               "
                + "         tblJobEscalaDetalle.idEscala    "
                + "WHERE ( tblJobEscala.idEscala    =       "
                + getIdEscala() + "                         "
                + "AND   tblJobEscalaDetalle.item   =       "
                + getItem() + " )                           "
                + "UNION                                    "
                + "SELECT tblJobEscala.idEscala,            "
                + "       tblJobEscala.nombreEscala,        "
                + "       tblJobEscalaDetalle.item,         "
                + "       tblJobEscalaDetalle.nombreItem,   "
                + "       tblJobEscalaDetalle.estado,       "
                + "       02 AS ordenSalida                 "
                + "FROM   tblJobEscala                      "
                + "INNER JOIN tblJobEscalaDetalle           "
                + "ON tblJobEscala.idEscala =               "
                + "         tblJobEscalaDetalle.idEscala    "
                + "WHERE ( tblJobEscala.idEscala    =       "
                + getIdEscala() + "                         "
                + "AND   tblJobEscalaDetalle.item  !=       "
                + getItem() + " )                           "
                + "ORDER BY 6, 4 ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaJobEscala fachadaBean;

            //
            while (rs.next()) {

                //
                fachadaBean = new FachadaJobEscala();

                //
                fachadaBean.setIdEscala(rs.getInt("idEscala"));
                fachadaBean.setNombreEscala(rs.getString("nombreEscala"));
                fachadaBean.setItem(rs.getInt("item"));
                fachadaBean.setNombreItem(rs.getString("nombreItem"));
                fachadaBean.setEstado(rs.getInt("estado"));

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

            // Cierra la conexion
            jdbcAccess.cleanup(connection, selectStatement, null);
            return contenedor;

        }
    }

    // listaEscalaItemFCH
    public FachadaJobEscala listaEscalaItemFCH() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        // Variable de fachada de los datos
        FachadaJobEscala fachadaBean = new FachadaJobEscala();

        Connection connection = null;

        //
        String selectStr
                = "SELECT tblJobEscala.idEscala,              "
                + "       tblJobEscala.nombreEscala,        "
                + "       tblJobEscalaDetalle.item,         "
                + "       tblJobEscalaDetalle.nombreItem,   "
                + "       tblJobEscalaDetalle.estado,       "
                + "       01 AS ordenSalida                 "
                + "FROM   tblJobEscala                      "
                + "INNER JOIN tblJobEscalaDetalle           "
                + "ON tblJobEscala.idEscala =               "
                + "         tblJobEscalaDetalle.idEscala    "
                + "WHERE ( tblJobEscala.idEscala    =       "
                + getIdEscala() + "                         "
                + "AND   tblJobEscalaDetalle.item   =       "
                + getItem() + " )";

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
                fachadaBean.setIdEscala(rs.getInt("idEscala"));
                fachadaBean.setNombreEscala(rs.getString("nombreEscala"));
                fachadaBean.setItem(rs.getInt("item"));
                fachadaBean.setNombreItem(rs.getString("nombreItem"));
                fachadaBean.setEstado(rs.getInt("estado"));

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
            return fachadaBean;

        }
    }

    // listaUnFCH
    public FachadaJobEscala listaUnFCH() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        //
        FachadaJobEscala fachadaBean = new FachadaJobEscala();

        Connection connection = null;

        //
        String selectStr
                = "SELECT tblJobEscala.idEscala,      "
                + "     tblJobEscala.nombreEscala,  "
                + "     tblJobEscala.idTipoEscala,  "
                + "     tblJobEscala.estado         "
                + "FROM   tblJobEscala              "
                + "WHERE tblJobEscala.idEscala =    "
                + getIdEscala() + "                 ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            //
            while (rs.next()) {

                //
                fachadaBean.setIdEscala(rs.getInt("idEscala"));
                fachadaBean.setNombreEscala(rs.getString("nombreEscala"));
                fachadaBean.setIdTipoEscala(rs.getInt("idTipoEscala"));
                fachadaBean.setEstado(rs.getInt("estado"));

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
            return fachadaBean;

        }
    }

    // listaEscalaMaquinaFCH
    public FachadaJobEscala listaEscalaMaquinaFCH() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        //
        FachadaJobEscala fachadaBean = new FachadaJobEscala();

        Connection connection = null;

        //
        String selectStr
                = " SELECT tmpOPE.*                               "
                + " FROM                                        "
                + " ( SELECT tblJobEscalaOperacion.idEscala     "
                + "         ,tblJobEscalaOperacion.idOperacion  "
                + "   FROM tblJobEscalaOperacion                "
                + "   WHERE tblJobEscalaOperacion.idEscala      "
                + "            IN (600,710,910,1000,1100)   )   "
                + "                               AS tmpOPE     "
                + " WHERE tmpOPE.idOperacion =                  "
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
                fachadaBean.setIdEscala(rs.getInt("idEscala"));

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
            return fachadaBean;

        }
    }

    // listaEscalaProceso
    public Vector listaEscalaProceso() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        //
        String selectStr
                = " SELECT tblJobEscala.idEscala,                  "
                + "        tblJobEscala.nombreEscala,            "
                + "        tblJobEscala.idTipoEscala,            "
                + "        tblJobEscala.estado                   "
                + " FROM   tblJobEscala                          "
                + " INNER JOIN tblJobEscalaOperacion             "
                + " ON tblJobEscala.idEscala =                   "
                + "      tblJobEscalaOperacion.idEscala          "
                + " WHERE tblJobEscalaOperacion.idOperacion =    "
                + getIdOperacion() + "                           "
                + " AND   tblJobEscala.idTipoEscala        !=9   "
                + " ORDER BY tblJobEscalaOperacion.idOrdenSalida ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaJobEscala fachadaBean;

            //
            while (rs.next()) {

                //
                fachadaBean = new FachadaJobEscala();

                //
                fachadaBean.setIdEscala(rs.getInt("idEscala"));
                fachadaBean.setNombreEscala(rs.getString("nombreEscala"));
                fachadaBean.setIdTipoEscala(rs.getInt("idTipoEscala"));
                fachadaBean.setEstado(rs.getInt("estado"));

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

            // Cierra la conexion
            jdbcAccess.cleanup(connection, selectStatement, null);
            return contenedor;

        }
    }

    public Vector listaEscalaItemMaquina() {
        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        String selectStr = " SELECT tblJobEscalaOperacion.idOperacion,        tblJobEscala.idEscala,                  tblJobEscala.nombreEscala,              tblJobEscala.idTipoEscala,              tblJobEscalaDetalle.item,               tblJobEscalaDetalle.nombreItem,         tblJobEscala.estado              FROM   tblJobEscala                     INNER JOIN tblJobEscalaOperacion        ON tblJobEscala.idEscala =                   tblJobEscalaOperacion.idEscala     INNER JOIN tblJobEscalaDetalle          ON tblJobEscala.idEscala =                   tblJobEscalaDetalle.idEscala       WHERE                                   tblJobEscalaOperacion.idOperacion > 0   AND tblJobEscalaOperacion.idEscala        IN (600,710,910,1000,1100,1200)       AND   tblJobEscala.idTipoEscala    !=9  ORDER BY                                  tblJobEscalaOperacion.idOperacion,      tblJobEscalaDetalle.nombreItem  ";

        PreparedStatement selectStatement = null;
        try {
            connection = this.jdbcAccess.getConnection();

            selectStatement = connection.prepareStatement(selectStr);

            ResultSet rs = selectStatement.executeQuery();
            while (rs.next()) {
                FachadaJobEscala fachadaBean = new FachadaJobEscala();

                fachadaBean.setIdOperacion(rs.getInt("idOperacion"));
                fachadaBean.setIdEscala(rs.getInt("idEscala"));
                fachadaBean.setNombreEscala(rs.getString("nombreEscala"));
                fachadaBean.setIdTipoEscala(rs.getInt("idTipoEscala"));
                fachadaBean.setItem(rs.getInt("item"));
                fachadaBean.setNombreItem(rs.getString("nombreItem"));
                fachadaBean.setEstado(rs.getInt("estado"));

                contenedor.add(fachadaBean);
            }
            rs.close();

            return contenedor;
        } catch (NamingException ne) {
            System.out.println("NamingException in " + nombreClase + " " + ne);

            return contenedor;
        } catch (SQLException sqle) {
            System.out.println("SQLException in" + nombreClase);

            String sqlMessage = sqle.getMessage();
            String sqlState = sqle.getSQLState();
            int vendorCode = sqle.getErrorCode();
            System.out.println("Ocurrio una  excepcion");
            System.out.println("Mensaje " + sqlMessage);
            System.out.println("SQL State " + sqlState);
            System.out.println("Vendor Code " + vendorCode);
            Vector localVector1 = contenedor;

            return contenedor;
        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);

            return contenedor;
        } finally {
            this.jdbcAccess.cleanup(connection, selectStatement, null);
            return contenedor;
        }
        
    }

    public Vector listaEscalaMaquinaOpcion() {
        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        String selectStr = " SELECT TOP 1                               0 AS idOperacion,                      0 AS idEscala,                         '-' AS nombreEscala,                   0 AS idTipoEscala,                     0 AS estado,                           1 AS ordenSalida                     FROM   tblJobEscala                    UNION                                  SELECT                                 tblJobEscalaOperacion.idOperacion,            tblJobEscala.idEscala,                 tblJobEscala.nombreEscala,             tblJobEscala.idTipoEscala,             tblJobEscala.estado,                   2 AS ordenSalida                FROM   tblJobEscala                    INNER JOIN tblJobEscalaOperacion       ON tblJobEscala.idEscala =                tblJobEscalaOperacion.idEscala      WHERE                                  tblJobEscalaOperacion.idOperacion > 0  AND tblJobEscalaOperacion.idEscala       IN (600,710,910,1000,1100,1200)      AND tblJobEscala.idTipoEscala    !=9   ORDER BY  6, 2  ";

        PreparedStatement selectStatement = null;
        try {
            connection = this.jdbcAccess.getConnection();

            selectStatement = connection.prepareStatement(selectStr);

            ResultSet rs = selectStatement.executeQuery();
            while (rs.next()) {
                FachadaJobEscala fachadaBean = new FachadaJobEscala();

                fachadaBean.setIdOperacion(rs.getInt("idOperacion"));
                fachadaBean.setIdEscala(rs.getInt("idEscala"));
                fachadaBean.setNombreEscala(rs.getString("nombreEscala"));
                fachadaBean.setIdTipoEscala(rs.getInt("idTipoEscala"));
                fachadaBean.setEstado(rs.getInt("estado"));

                contenedor.add(fachadaBean);
            }
            rs.close();

            return contenedor;
        } catch (NamingException ne) {
            System.out.println("NamingException in " + nombreClase + " " + ne);

            return contenedor;
        } catch (SQLException sqle) {
            System.out.println("SQLException in" + nombreClase);

            String sqlMessage = sqle.getMessage();
            String sqlState = sqle.getSQLState();
            int vendorCode = sqle.getErrorCode();
            System.out.println("Ocurrio una  excepcion");
            System.out.println("Mensaje " + sqlMessage);
            System.out.println("SQL State " + sqlState);
            System.out.println("Vendor Code " + vendorCode);
            Vector localVector1 = contenedor;

            return contenedor;
        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);

            return contenedor;
        } finally {
            this.jdbcAccess.cleanup(connection, selectStatement, null);
            return contenedor;
        }
        
    }

}
