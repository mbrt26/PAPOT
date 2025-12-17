package com.solucionesweb.losbeans.negocio;

// Importa la clase helper JDBCAccess que contiene los accesos a la base de datos
import com.solucionesweb.lasayudas.JDBCAccess;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaTipoOrden;

// Importa los paquetes de java
import java.sql.*;
import java.io.*;
import java.util.*;
import java.io.IOException.*;

// Importa la interface de las constantes
import com.solucionesweb.losbeans.utilidades.IConstantes;

// Importa los paquetes de javax
import javax.naming.*;

public class TipoOrdenBean extends FachadaTipoOrden
        implements Serializable, IConstantes {

    // Nombre JNDI del Data Source que esta clase requiere
    private static final String DATA_SOURCE_NAME = "CommerceAccess";

    // Clase helper usada para acceder a la base de datos
    private JDBCAccess jdbcAccess;

    // Metodo constructor por defecto sin parametros
    public TipoOrdenBean() {

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
                "SELECT tblTipoOrden.idTipoOrden     " +
                "      ,tblTipoOrden.nombreTipoOrden " +
                "      ,tblTipoOrden.estado          " +
                "      ,tblTipoOrden.signo           " +
                "      ,tblTipoOrden.idSeq           " +
                "      ,tblTipoOrden.idAlcance       " +
                "FROM tblTipoOrden                   " +
                "WHERE tblTipoOrden.estado = 1       " +
                "ORDER BY tblTipoOrden.idTipoOrden   ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaTipoOrden fachadaBean;

            while (rs.next()) {

                fachadaBean = new FachadaTipoOrden();

                //
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setNombreTipoOrden(rs.getString("nombreTipoOrden"));
                fachadaBean.setEstado(rs.getInt("estado"));
                fachadaBean.setSigno(rs.getDouble("signo"));
                fachadaBean.setIdSeq(rs.getInt("idSeq"));
                fachadaBean.setIdAlcance(rs.getInt("idAlcance"));

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

    // listaAllOpcion
    public Vector listaAllOpcion(String xIdTipoOrdenOpcion) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        Vector contenedor = new Vector();
        Connection connection = null;

        String selectStr =
                "SELECT tblTipoOrden.idTipoOrden     " +
                "      ,tblTipoOrden.nombreTipoOrden " +
                "      ,tblTipoOrden.estado          " +
                "      ,tblTipoOrden.signo           " +
                "      ,tblTipoOrden.idSeq           " +
                "      ,tblTipoOrden.idAlcance       " +
                "FROM tblTipoOrden                   " +
                "WHERE tblTipoOrden.estado = 1       " +
                "AND   tblTipoOrden.idTipoOrden  IN (" +
                xIdTipoOrdenOpcion +")               " +
                "ORDER BY tblTipoOrden.idTipoOrden   ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaTipoOrden fachadaBean;

            while (rs.next()) {

                fachadaBean = new FachadaTipoOrden();

                //
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setNombreTipoOrden(rs.getString("nombreTipoOrden"));
                fachadaBean.setEstado(rs.getInt("estado"));
                fachadaBean.setSigno(rs.getDouble("signo"));
                fachadaBean.setIdSeq(rs.getInt("idSeq"));
                fachadaBean.setIdAlcance(rs.getInt("idAlcance"));

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

    // listaUnFCH
    public FachadaTipoOrden listaUnFCH() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        //
        FachadaTipoOrden fachadaBean = new FachadaTipoOrden();

        Connection connection = null;

        String selectStr =
                "SELECT tblTipoOrden.idTipoOrden     " +
                "      ,tblTipoOrden.nombreTipoOrden " +
                "      ,tblTipoOrden.estado          " +
                "      ,tblTipoOrden.signo           " +
                "      ,tblTipoOrden.idSeq           " +
                "      ,tblTipoOrden.idAlcance       " +
                "FROM tblTipoOrden                   " +
                "WHERE tblTipoOrden.estado    = 1    " +
                "AND tblTipoOrden.idTipoOrden =      " +
                getIdTipoOrden() + "                 " +
                "ORDER BY tblTipoOrden.idTipoOrden   ";

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
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setNombreTipoOrden(rs.getString("nombreTipoOrden"));
                fachadaBean.setEstado(rs.getInt("estado"));
                fachadaBean.setSigno(rs.getDouble("signo"));
                fachadaBean.setIdSeq(rs.getInt("idSeq"));
                fachadaBean.setIdAlcance(rs.getInt("idAlcance"));

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

    // listaUn
    public Vector listaUn() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        String selectStr =
                "SELECT tblTipoOrden.idTipoOrden     " +
                "      ,tblTipoOrden.nombreTipoOrden " +
                "      ,tblTipoOrden.estado          " +
                "      ,tblTipoOrden.signo           " +
                "      ,tblTipoOrden.idSeq           " +
                "      ,tblTipoOrden.idAlcance       " +                
                "FROM tblTipoOrden                   " +
                "WHERE tblTipoOrden.estado      = 1  " +
                "AND   tblTipoOrden.idTipoOrden =    " +
                getIdTipoOrden() + "                 " +
                "ORDER BY tblTipoOrden.idTipoOrden   ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaTipoOrden fachadaBean;

            //
            if (rs.next()) {

                //
                fachadaBean = new FachadaTipoOrden();

                //
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setNombreTipoOrden(rs.getString("nombreTipoOrden"));
                fachadaBean.setEstado(rs.getInt("estado"));
                fachadaBean.setSigno(rs.getDouble("signo"));
                fachadaBean.setIdSeq(rs.getInt("idSeq"));
                fachadaBean.setIdAlcance(rs.getInt("idAlcance"));                

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

    // listaAlcance
    public Vector listaAlcance() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        String selectStr =
                "SELECT tblTipoOrden.idTipoOrden       " +
                "      ,tblTipoOrden.nombreTipoOrden   " +
                "      ,tblTipoOrden.estado            " +
                "      ,tblTipoOrden.signo             " +
                "      ,tblTipoOrden.idSeq             " +
                "      ,tblTipoOrden.idAlcance         " +
                "FROM tblTipoOrden                     " +
                "WHERE tblTipoOrden.estado      = 1    " +
                "AND   tblTipoOrden.idAlcance   =      " +
                getIdAlcance() + "                     " +
                "ORDER BY tblTipoOrden.nombreTipoOrden ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaTipoOrden fachadaBean;

            //
            while (rs.next()) {

                //
                fachadaBean = new FachadaTipoOrden();

                //
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setNombreTipoOrden(rs.getString("nombreTipoOrden"));
                fachadaBean.setEstado(rs.getInt("estado"));
                fachadaBean.setSigno(rs.getDouble("signo"));
                fachadaBean.setIdSeq(rs.getInt("idSeq"));
                fachadaBean.setIdAlcance(rs.getInt("idAlcance"));

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

    // listaAlcance
    public Vector listaAlcanceOpcion() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        String selectStr =
                "SELECT TOP 1  00 AS idTipoOrden       " +
                "      ,'NN' AS nombreTipoOrden        " +
                "      ,0 AS estado                    " +
                "      ,0 AS signo                     " +
                "      ,0 AS idSeq                     " +
                "      ,0 AS idAlcance                 " +
                "      ,01 AS ordenSalida              " +
                "FROM tblTipoOrden                     " +
                "UNION                                 " +
                "SELECT tblTipoOrden.idTipoOrden       " +
                "      ,tblTipoOrden.nombreTipoOrden   " +
                "      ,tblTipoOrden.estado            " +
                "      ,tblTipoOrden.signo             " +
                "      ,tblTipoOrden.idSeq             " +
                "      ,tblTipoOrden.idAlcance         " +
                "      ,02 AS ordenSalida              " +
                "FROM tblTipoOrden                     " +
                "WHERE tblTipoOrden.estado      = 1    " +
                "AND   tblTipoOrden.idAlcance   =      " +
                getIdAlcance() + "                     " +
                "ORDER BY 7, 2 ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaTipoOrden fachadaBean;

            //
            while (rs.next()) {

                //
                fachadaBean = new FachadaTipoOrden();

                //
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setNombreTipoOrden(rs.getString("nombreTipoOrden"));
                fachadaBean.setEstado(rs.getInt("estado"));
                fachadaBean.setSigno(rs.getDouble("signo"));
                fachadaBean.setIdSeq(rs.getInt("idSeq"));
                fachadaBean.setIdAlcance(rs.getInt("idAlcance"));

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
}
