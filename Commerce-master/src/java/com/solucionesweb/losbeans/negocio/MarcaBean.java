package com.solucionesweb.losbeans.negocio;

// Importa la clase helper JDBCAccess que contiene los accesos a la base de datos
import com.solucionesweb.lasayudas.JDBCAccess;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaMarcaBean;

// Importa los paquetes de java
import java.sql.*;
import java.io.*;
import java.util.*;
import java.io.IOException.*;

//
import org.kxml.*;
import org.kxml.parser.*;

// Importa los paquetes de javax
import javax.naming.*;

public class MarcaBean extends FachadaMarcaBean implements Serializable {

    // Nombre JNDI del Data Source que esta clase requiere
    private static final String DATA_SOURCE_NAME = "CommerceAccess";

    // Clase helper usada para acceder a la base de datos
    private JDBCAccess jdbcAccess;

    // Metodo constructor por defecto sin parametros
    public MarcaBean() {

        // Al instanciar el Bean establece la conexion a la base de datos
        jdbcAccess = new JDBCAccess(DATA_SOURCE_NAME);
    }

    // Metodo
    public Vector listaTodosMarca() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        Vector contenedor = new Vector();
        Connection connection = null;

        String selectStr =
                "SELECT tblMarcas.idMarca,       " +
                "       tblMarcas.nombreMarca,   " +
                "       tblMarcas.estado         " +
                "FROM tblMarcas                  " +
                "ORDER BY tblMarcas.nombreMarca  ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaMarcaBean fachadaBean;

            while (rs.next()) {

                //
                fachadaBean = new FachadaMarcaBean();

                //
                fachadaBean.setIdMarca(rs.getInt("idMarca"));
                fachadaBean.setNombreMarca(rs.getString("nombreMarca"));
                fachadaBean.setEstado(rs.getInt("estado"));

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
            jdbcAccess.cleanup(connection, selectStatement, null);
            return contenedor;
        }
    }

    //
    public Vector listaMarcaTx() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        Vector contenedor = new Vector();
        Connection connection = null;

        String selectStr =
                "SELECT tblMarcas.idMarca,     " +
                "       tblMarcas.nombreMarca, " +
                "       tblMarcas.estado,      " +
                "       tblMarcas.idSeq        " +
                "FROM tblMarcas                " +
                "WHERE  tblMarcas.idSeq >      " +
                getIdSeq() + "                 " +
                "ORDER BY tblMarcas.idSeq ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaMarcaBean fachadaBean;

            while (rs.next()) {

                //
                fachadaBean = new FachadaMarcaBean();
                fachadaBean.setIdMarca(rs.getInt("idMarca"));
                fachadaBean.setNombreMarca(rs.getString("nombreMarca"));
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
            jdbcAccess.cleanup(connection, selectStatement, null);
            return contenedor;
        }
    }

    public FachadaMarcaBean listaMarcaFCH() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        //
        FachadaMarcaBean fachadaBean = new FachadaMarcaBean();

        Connection connection = null;

        String selectStr =
                "SELECT tblMarcas.idMarca,       " +
                "       tblMarcas.nombreMarca,   " +
                "       tblMarcas.estado,        " +
                "       tblMarcas.idSeq          " +
                "FROM tblMarcas                  " +
                "WHERE  tblMarcas.idMarca    =   " +
                getIdMarca() ;

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            if (rs.next()) {

                fachadaBean = new FachadaMarcaBean();
                fachadaBean.setIdMarca(rs.getInt("idMarca"));
                fachadaBean.setNombreMarca(rs.getString("nombreMarca"));
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
            jdbcAccess.cleanup(connection, selectStatement, null);
            return fachadaBean;
        }
    }

    //
    public Vector seleccionaMarcaxNombre() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        Vector contenedor = new Vector();
        Connection connection = null;


        String selectStr =
                "SELECT tblMarcas.idMarca,         " +
                "       tblMarcas.nombreMarca,     " +
                "       tblMarcas.estado           " +
                "FROM tblMarcas                    " +
                "WHERE tblMarcas.nombreMarca LIKE (" +
                getNombreMarca() + ")   " +
                "ORDER BY tblMarcas.nombreMarca    ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaMarcaBean fachadaBean;

            while (rs.next()) {

                fachadaBean = new FachadaMarcaBean();
                fachadaBean.setIdMarca(rs.getInt("idMarca"));
                fachadaBean.setNombreMarca(rs.getString("nombreMarca"));
                fachadaBean.setEstado(rs.getInt("estado"));

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
            jdbcAccess.cleanup(connection, selectStatement, null);
            return contenedor;
        }
    }

    // seleccionaIdMarca
    public Vector seleccionaIdMarca() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        Vector contenedor = new Vector();
        Connection connection = null;


        String selectStr =
                "SELECT tblMarcas.idMarca,      " +
                "       tblMarcas.nombreMarca,  " +
                "       tblMarcas.estado        " +
                "FROM tblMarcas                 " +
                "WHERE tblMarcas.idMarca = ( ? )";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // inicializa los valore de los parametros
            selectStatement.setInt(1, getIdMarca());

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaMarcaBean fachadaBean;

            while (rs.next()) {

                fachadaBean = new FachadaMarcaBean();
                fachadaBean.setIdMarca(rs.getInt("idMarca"));
                fachadaBean.setNombreMarca(rs.getString("nombreMarca"));
                fachadaBean.setEstado(rs.getInt("estado"));

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
            jdbcAccess.cleanup(connection, selectStatement, null);
            return contenedor;
        }
    }

    // listaMarcaSeleccion
    public Vector listaMarcaSeleccion() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        Vector contenedor = new Vector();
        Connection connection = null;


        String selectStr =
                "SELECT tblMarcas.idMarca,      " +
                "       tblMarcas.nombreMarca,  " +
                "       tblMarcas.estado,       " +
                "       01 AS orden             " +
                "FROM tblMarcas                 " +
                "WHERE tblMarcas.idMarca =      " +
                getIdMarca() + "                " +
                "UNION                          " +
                "SELECT tblMarcas.idMarca,      " +
                "       tblMarcas.nombreMarca,  " +
                "       tblMarcas.estado,       " +
                "       02 AS orden             " +
                "FROM tblMarcas                 " +
                "WHERE tblMarcas.idMarca NOT IN " +
                "( SELECT tblMarcas.idMarca     " +
                "FROM tblMarcas                 " +
                "WHERE tblMarcas.idMarca =      " +
                getIdMarca() + " )              " +
                "ORDER BY 4, 2                  ";


        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaMarcaBean fachadaBean;

            while (rs.next()) {

                fachadaBean = new FachadaMarcaBean();
                fachadaBean.setIdMarca(rs.getInt("idMarca"));
                fachadaBean.setNombreMarca(rs.getString("nombreMarca"));
                fachadaBean.setEstado(rs.getInt("estado"));

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
            jdbcAccess.cleanup(connection, selectStatement, null);
            return contenedor;
        }
    }

    // listaIdSeqMaxima
    public int listaIdSeqMaxima() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        int idSeqMaxima = 0;
        Connection connection = null;

        String selectStr =
                "SELECT MAX(tblMarcas.idSeq) " +
                "                AS maxIdSeq " +
                "FROM tblMarcas              ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            if (rs.next()) {

                idSeqMaxima = rs.getInt("maxIdSeq");

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
            jdbcAccess.cleanup(connection, selectStatement, null);
            return idSeqMaxima;
        }
    }

    // actualizaMarca
    public boolean actualizaMarca() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Vector contenedor = new Vector();
        Connection connection = null;

        String selectStr =
                "UPDATE tblMarcas             " +
                "SET tblMarcas.nombreMarca = '" +
                getNombreMarca() + "',        " +
                "    tblMarcas.idSeq       =  " +
                getIdSeq() + ",               " +
                "    tblMarcas.estado      =  " +
                getEstado() + "               " +
                "WHERE tblMarcas.idMarca   =  " +
                getIdMarca() ;

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            selectStatement.execute();
            okIngresar = true;


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
            return okIngresar;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {
            // Cierra el Resultset
            jdbcAccess.cleanup(connection, selectStatement, null);
            return okIngresar;
        }
    }

    // ingresa
    public boolean ingresa() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                "INSERT INTO  tblMarcas (idMarca, " +
                "                    nombreMarca, " +
                "                         estado, " +
                "                          idSeq) " +
                "VALUES ( ?, ?, ?, ?)";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            //
            selectStatement.setInt(1, getIdMarca());
            selectStatement.setString(2, getNombreMarca().toUpperCase().trim());
            selectStatement.setInt(3, getEstado());
            selectStatement.setInt(4, getIdSeq());

            // Obtiene el resultset
            selectStatement.execute();
            okIngresar = true;

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
            return okIngresar;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {
            // Cierra el Resultset
            jdbcAccess.cleanup(connection, selectStatement, null);
            return okIngresar;
        }
    }

    // listaIdMarcaMaxima
    public int listaIdMarcaMaxima() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        int idMarcaMaxima = 0;
        Connection connection = null;

        String selectStr =
                "SELECT MAX(tblMarcas.idMarca) " +
                "             AS idMarcaMaxima " +
                "FROM tblMarcas                ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            if (rs.next()) {

                idMarcaMaxima = rs.getInt("idMarcaMaxima");

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
            jdbcAccess.cleanup(connection, selectStatement, null);
            return idMarcaMaxima;
        }
    }

    // listaUnAllMarca
    public Vector listaUnAllMarca() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        Vector contenedor = new Vector();
        Connection connection = null;

        String selectStr =
                "SELECT TOP 1 00 AS idMarca,    " +
                "       '--' AS nombreMarca,    " +
                "       00 AS estado,           " +
                "       00 AS idSeq,            " +
                "       01 AS ordenSalida       " +
                "FROM   tblMarcas               " +
                "UNION                          " +
                "SELECT TOP 1 00 AS idMarca,    " +
                "       'TODAS' AS nombreMarca, " +
                "       00 AS estado,           " +
                "       00 AS idSeq,            " +
                "       02 AS ordenSalida       " +
                "FROM   tblMarcas               " +
                "UNION                          " +
                "SELECT tblMarcas.idMarca,      " +
                "       tblMarcas.nombreMarca,  " +
                "       tblMarcas.estado,       " +
                "       tblMarcas.idSeq,        " +
                "       03 AS ordenSalida       " +
                "FROM   tblMarcas               " +
                "ORDER BY 5,2";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaMarcaBean fachadaMarcaBean;

            while (rs.next()) {

                fachadaMarcaBean = new FachadaMarcaBean();

                //
                fachadaMarcaBean.setIdMarca(rs.getInt("idMarca"));
                fachadaMarcaBean.setNombreMarca(rs.getString("nombreMarca"));
                fachadaMarcaBean.setEstado(rs.getInt("estado"));
                fachadaMarcaBean.setIdSeq(rs.getInt("idSeq"));

                //
                contenedor.add(fachadaMarcaBean);

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
            // Cierra la conexion a la base de datos limpiando la statement usada y la
            // conexion
            return contenedor;
        }
    }

    // listaAllMarca
    public Vector listaAllMarca() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        Vector contenedor = new Vector();
        Connection connection = null;

        String selectStr =
                "SELECT TOP 1 00 AS idMarca,    " +
                "       'NN' AS nombreMarca,    " +
                "       00 AS estado,           " +
                "       00 AS idSeq,            " +
                "       01 AS ordenSalida       " +
                "FROM   tblMarcas               " +
                "UNION                          " +
                "SELECT tblMarcas.idMarca,      " +
                "       tblMarcas.nombreMarca,  " +
                "       tblMarcas.estado,       " +
                "       tblMarcas.idSeq,        " +
                "       02 AS ordenSalida       " +
                "FROM   tblMarcas               " +
                "ORDER BY 5,2";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaMarcaBean fachadaMarcaBean;

            while (rs.next()) {

                fachadaMarcaBean = new FachadaMarcaBean();

                //
                fachadaMarcaBean.setIdMarca(rs.getInt("idMarca"));
                fachadaMarcaBean.setNombreMarca(rs.getString("nombreMarca"));
                fachadaMarcaBean.setEstado(rs.getInt("estado"));
                fachadaMarcaBean.setIdSeq(rs.getInt("idSeq"));

                //
                contenedor.add(fachadaMarcaBean);

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
            // Cierra la conexion a la base de datos limpiando la statement usada y la
            // conexion
            return contenedor;
        }
    }

// traverse_MarcaTx
public void traverse_MarcaTx( XmlParser parser,
                                  String indent ) throws Exception {

    //
	boolean leave      = false;
    String idMarca     = new String();
    String nombreMarca = new String();
    String estado      = new String();
    String idSeq       = new String();

	do {
	   ParseEvent event = parser.read();
	   ParseEvent pe;

	switch ( event.getType() ) {

	 // For example, <CxC>
	 case Xml.START_TAG:

	 // see API doc of StartTag for more access methods
	 // Pick up idagenda for display
	  if ("idMarca".equals(event.getName()))
	  {
			pe          = parser.read();
			idMarca     = pe.getText();
	  }

	  // Pick up clave for display
	  if ("nombreMarca".equals(event.getName()))
	  {
	  		pe              = parser.read();
	  		nombreMarca = pe.getText();
	  }

	  // Pick up clave for display
	  if ("estado".equals(event.getName()))
	  {
	  		pe          = parser.read();
	  		estado      = pe.getText();
	  }

	  // Pick up idlocal for display
	  if ("idSeq".equals(event.getName()))
	  {
			pe     = parser.read();
			idSeq  = pe.getText();

   	        // ingresarCategoria
   	        setIdMarca(idMarca);
   	        setNombreMarca(nombreMarca);
   	        setEstado(estado);
   	        setIdSeq(idSeq);

   	        if ( this.existeMarca() ) {
   	             System.out.println(" actualiza categoria");

                 //
                 this.actualizaMarca();
   	        } else {
   	             System.out.println(" ingresa categoria");

                 //
    	         this.ingresa();
   	        }

	  }

	  traverse_MarcaTx( parser, "" ) ; // recursion call for each <tag></tag>
	  break;

	  // For example </title?
	 case Xml.END_TAG:
	  leave = true;
	  break;

	  // For example </rss>
	 case Xml.END_DOCUMENT:
	  leave = true;
	  break;

	  // For example, the text between tags
	 case Xml.TEXT:
	  break;
	 case Xml.WHITESPACE:
	  break;
	  default:
	  }
	  } while( !leave );
  }

    // existeMarca
    public boolean existeMarca() {

      //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
      Class tipoObjeto       = this.getClass();
      String nombreClase     = tipoObjeto.getName();
      boolean existeLinea    = false;

      Connection connection  = null;

      String selectStr =
                "SELECT tblMarcas.idMarca,    " +
                "       tblMarcas.nombreMarca," +
                "       tblMarcas.estado      " +
                "FROM tblMarcas               " +
                "WHERE tblMarcas.idMarca =    " +
                getIdMarca();

      PreparedStatement selectStatement = null;

      try {

        // Obtiene una conexion a la base de datos, requiere Try
        connection = jdbcAccess.getConnection();

        // Prepara la sentencia de Busqueda
        selectStatement = connection.prepareStatement(selectStr);

        // Obtiene el resultset
        ResultSet rs = selectStatement.executeQuery();

        if (rs.next()) {

           existeLinea = true;

        }

        // Cierra el Resultset
        rs.close();

      } catch (SQLException sqle) {

		System.out.println("SQLException in" + nombreClase);

		String sqlMessage = sqle.getMessage();
        String sqlState = sqle.getSQLState();
        int vendorCode = sqle.getErrorCode();
		System.out.println("Ocurrio una  excepcion");
		System.out.println("Mensaje " + sqlMessage);
		System.out.println("SQL State " + sqlState);
		System.out.println("Vendor Code " + vendorCode);
		return existeLinea;

	  }

	  catch (Exception e)
	  {
		System.out.println("Exception In :" + nombreClase + " " + e);
	  }
      finally {
        // Cierra la conexion a la base de datos limpiando la statement usada y la
            jdbcAccess.cleanup(connection, selectStatement, null);
       return existeLinea;
      }
    }
}
