package com.solucionesweb.losbeans.negocio;

// Importa la clase helper JDBCAccess que contiene los accesos a la base de datos
import com.solucionesweb.lasayudas.JDBCAccess;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaLineaBean;

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

public class LineaBean extends FachadaLineaBean implements Serializable {

    // Nombre JNDI del Data Source que esta clase requiere
    private static final String DATA_SOURCE_NAME = "CommerceAccess";
    // Clase helper usada para acceder a la base de datos
    private JDBCAccess jdbcAccess;

    // Metodo constructor por defecto sin parametros
    public LineaBean() {

        // Al instanciar el Bean establece la conexion a la base de datos
        jdbcAccess = new JDBCAccess(DATA_SOURCE_NAME);
    }

    // seleccionaTercero
    public Vector listaAll() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        Vector contenedor = new Vector();
        Connection connection = null;


        String selectStr =
                "SELECT tblLineas.idLinea,      "
                + "       tblLineas.nombreLinea   "
                + "FROM tblLineas                 "
                + "ORDER BY tblLineas.nombreLinea ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaLineaBean fachadaLineaBean;

            while (rs.next()) {

                fachadaLineaBean = new FachadaLineaBean();

                fachadaLineaBean.setIdLinea(rs.getString("idLinea"));
                fachadaLineaBean.setNombreLinea(
                        rs.getString("nombreLinea").toUpperCase());

                //
                contenedor.add(fachadaLineaBean);

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

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {
            // Cierra la conexion a la base de datos limpiando la statement usada y la
            jdbcAccess.cleanup(connection, selectStatement, null);
            return contenedor;
        }
    }

    // listaFCH
    public FachadaLineaBean listaFCH() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        FachadaLineaBean fachadaLineaBean = new FachadaLineaBean();
        Connection connection = null;


        String selectStr =
                "SELECT tblLineas.idLinea,       "
                + "       tblLineas.nombreLinea    "
                + "FROM tblLineas                  "
                + "WHERE tblLineas.idLinea = ( ? ) ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // inicializa los valores de los parametros
            selectStatement.setInt(1, getIdLinea());

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            //
            if (rs.next()) {

                fachadaLineaBean.setIdLinea(rs.getString("idLinea"));
                fachadaLineaBean.setNombreLinea(
                        rs.getString("nombreLinea").toUpperCase());
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
            return fachadaLineaBean;
        }
    }

    // Metodo
    public Vector seleccionaUnaLinea() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        Vector contenedor = new Vector();
        Connection connection = null;

        String selectStr =
                "SELECT 0000 AS idLinea,       "
                + "       '-' AS nombreLinea,   "
                + "       00 AS estado,          "
                + "       0 AS orden             "
                + "FROM tblLineas UNION          "
                + "SELECT tblLineas.idLinea,     "
                + "       tblLineas.nombreLinea, "
                + "       tblLineas.estado,      "
                + "       1 AS orden             "
                + "FROM tblLineas                "
                + "ORDER BY 4, 2                 ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaLineaBean fachadaBean;

            while (rs.next()) {

                fachadaBean = new FachadaLineaBean();
                fachadaBean.setIdLinea(rs.getInt("idLinea"));
                fachadaBean.setNombreLinea(rs.getString("nombreLinea"));
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

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {
            // Cierra la conexion a la base de datos limpiando la statement usada y la
            jdbcAccess.cleanup(connection, selectStatement, null);
            return contenedor;
        }
    }

    //
    public Vector seleccionaLineaxNombre() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        Vector contenedor = new Vector();
        Connection connection = null;


        String selectStr =
                "SELECT tblLineas.idLinea,         "
                + "       tblLineas.nombreLinea,     "
                + "       tblLineas.estado           "
                + "FROM tblLineas                    "
                + "WHERE tblLineas.nombreLinea LIKE ("
                + getNombreLinea() + ")   "
                + "ORDER BY tblLineas.nombreLinea    ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaLineaBean fachadaBean;

            while (rs.next()) {

                fachadaBean = new FachadaLineaBean();
                fachadaBean.setIdLinea(rs.getInt("idLinea"));
                fachadaBean.setNombreLinea(rs.getString("nombreLinea"));
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

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {
            // Cierra la conexion a la base de datos limpiando la statement usada y la
            jdbcAccess.cleanup(connection, selectStatement, null);
            return contenedor;
        }
    }

    //
    public Vector listaLineaTx() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        Vector contenedor = new Vector();
        Connection connection = null;


        String selectStr =
                "SELECT tblLineas.idLinea     "
                + "      ,tblLineas.nombreLinea "
                + "      ,tblLineas.estado      "
                + "      ,tblLineas.idSeq       "
                + "FROM tblLineas               "
                + "WHERE tblLineas.idSeq >      "
                + getIdSeq() + "                "
                + "ORDER BY tblLineas.idSeq     ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaLineaBean fachadaBean;

            while (rs.next()) {

                fachadaBean = new FachadaLineaBean();

                //
                fachadaBean.setIdLinea(rs.getInt("idLinea"));
                fachadaBean.setNombreLinea(rs.getString("nombreLinea"));
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

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {
            // Cierra la conexion a la base de datos limpiando la statement usada y la
            jdbcAccess.cleanup(connection, selectStatement, null);
            return contenedor;
        }
    }

    // actualizaLinea
    public boolean actualizaLinea() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Vector contenedor = new Vector();
        Connection connection = null;

        String selectStr =
                "UPDATE tblLineas                   "
                + "SET tblLineas.nombreLinea = ( ? ), "
                + "    tblLineas.idSeq       = ( ? )  "
                + "WHERE tblLineas.idLinea   = ( ? )  ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            //
            selectStatement.setString(1, getNombreLinea().toUpperCase());
            selectStatement.setInt(2, getIdSeq());
            selectStatement.setInt(3, getIdLinea());


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

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {
            // Cierra la conexion a la base de datos limpiando la statement usada y la
            jdbcAccess.cleanup(connection, selectStatement, null);
            return okIngresar;
        }
    }

    // listaLineaMaxima
    public int listaLineaMaxima() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        int idLineaMaxima = 0;
        Connection connection = null;

        String selectStr =
                "SELECT MAX(tblLineas.idLinea) "
                + "             AS idLineaMaxima "
                + "FROM tblLineas                ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            if (rs.next()) {

                idLineaMaxima = rs.getInt("idLineaMaxima");

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

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {
            // Cierra la conexion a la base de datos limpiando la statement usada y la
            jdbcAccess.cleanup(connection, selectStatement, null);
            return idLineaMaxima;
        }
    }

    // listaIdSeqMaxima
    public int listaIdSeqMaxima() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        int intMaxIdSeq = 0;
        Connection connection = null;

        String selectStr =
                "SELECT MAX(tblLineas.idSeq) "
                + "                AS maxIdSeq "
                + "FROM tblLineas";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            if (rs.next()) {

                intMaxIdSeq = rs.getInt("maxIdSeq");

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
            return intMaxIdSeq;

        }
    }

    // ingresaLinea
    public boolean ingresaLinea() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                "INSERT INTO  tblLineas (idLinea,     "
                + "                        nombreLinea, "
                + "                        estado,      "
                + "                        idSeq)       "
                + "VALUES ( ?, ?, ?, ?)                 ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            //
            selectStatement.setInt(1, getIdLinea());
            selectStatement.setString(2, getNombreLinea().toUpperCase().trim());
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

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {
            // Cierra la conexion a la base de datos limpiando la statement usada y la
            jdbcAccess.cleanup(connection, selectStatement, null);
            return okIngresar;
        }
    }

// traverse_LineaTx
    public void traverse_LineaTx(XmlParser parser, String indent) throws Exception {

        boolean leave = false;
        String idLinea = new String();
        String nombreLinea = new String();
        String estado = new String();
        String idSeq = new String();

        do {
            ParseEvent event = parser.read();
            ParseEvent pe;

            switch (event.getType()) {

                // For example, <CxC>
                case Xml.START_TAG:

                    // see API doc of StartTag for more access methods
                    // Pick up idagenda for display
                    if ("idLinea".equals(event.getName())) {
                        pe = parser.read();
                        idLinea = pe.getText();
                    }

                    // Pick up clave for display
                    if ("nombreLinea".equals(event.getName())) {
                        pe = parser.read();
                        nombreLinea = pe.getText();
                    }

                    // Pick up clave for display
                    if ("estado".equals(event.getName())) {
                        pe = parser.read();
                        estado = pe.getText();
                    }

                    // Pick up idlocal for display
                    if ("idSeq".equals(event.getName())) {
                        pe = parser.read();
                        idSeq = pe.getText();

                        // ingresarLinea
                        setIdSeq(idSeq);
                        setIdLinea(idLinea);
                        setNombreLinea(nombreLinea);
                        setEstado(Integer.parseInt(estado));

                        if (this.existenLinea()) {
                            System.out.println(" actualiza linea");
                            this.actualizaLinea();
                        } else {
                            System.out.println(" ingresa linea");
                            this.ingresaLinea();
                        }

                    }

                    traverse_LineaTx(parser, ""); // recursion call for each <tag></tag>
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
        } while (!leave);
    }

    // existenLinea
    public boolean existenLinea() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean existeLinea = false;

        Connection connection = null;

        String selectStr =
                "SELECT tblLineas.*        "
                + "FROM tblLineas            "
                + "WHERE tblLineas.idLinea = "
                + getIdLinea();

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

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {
            // Cierra la conexion a la base de datos limpiando la statement usada y la
            jdbcAccess.cleanup(connection, selectStatement, null);
            return existeLinea;
        }
    }
}
