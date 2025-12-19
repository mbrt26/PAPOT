package com.solucionesweb.losbeans.colaboraciones;

// Importa la clase helper JDBCAccess que contiene los accesos a la base de datos
import com.solucionesweb.lasayudas.JDBCAccess;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaPluBean;

// Importa los paquetes de java
import java.sql.*;
import java.io.*;
import java.util.*;
import java.io.IOException.*;

// Importa los paquetes de javax
import javax.naming.*;

public class ColaboraPlu extends FachadaPluBean
        implements Serializable {

    // Nombre JNDI del Data Source que esta clase requiere
    private static final String DATA_SOURCE_NAME = "CommerceAccess";
    // Clase helper usada para acceder a la base de datos
    private JDBCAccess jdbcAccess;

    // Metodo constructor por defecto sin parametros
    public ColaboraPlu() {

        // Al instanciar el Bean establece la conexion a la base de datos
        jdbcAccess = new JDBCAccess(DATA_SOURCE_NAME);
    }

    // listaNombrePlu
    public Vector listaNombrePlu(String xNombrePlu) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        String selectStr =
                "SELECT    tblPlus.idPlu,                "
                + "        tblPlus.nombrePlu,            "
                + "        tblPlus.vrGeneral,            "
                + "        tblPlus.vrMayorista,          "
                + "        tblPlus.porcentajeIva,        "
                + "        tblPlus.idTipo,               "
                + "        tblPlus.idLinea,              "
                + "        tblPlus.idUCompra,            "
                + "        tblPlus.idUVenta,             "
                + "        tblPlus.vrCosto,              "
                + "        tblPlus.idCategoria,          "
                + "        tblPlus.idMarca,              "
                + "        tblPlus.vrSucursal,           "
                + "        tblPlus.factorVenta,          "
                + "        tblPlus.factorDespacho,       "
                + "        tblPlus.estado,               "
                + "        tblPlus.idSeq,                "
                + "        tblPlus.referencia,           "
                + "        tblCategorias.nombreCategoria,"
                + "        tblMarcas.nombreMarca         "
                + "FROM tblPlus                          "
                + "INNER JOIN tblMarcas                  "
                + "ON tblPlus.idMarca      =             "
                + "                    tblMarcas.idMarca "
                + "INNER JOIN tblCategorias              "
                + "ON tblPlus.idLinea      =             "
                + "                tblCategorias.idLinea "
                + "AND tblPlus.idCategoria =             "
                + "            tblCategorias.IdCategoria "
                + "WHERE tblPlus.nombrePlu               "
                + "       LIKE (" + xNombrePlu + ")      "
                + "ORDER BY tblPlus.nombrePlu            ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaPluBean fachadaPluBean;

            while (rs.next()) {

                fachadaPluBean = new FachadaPluBean();

                //
                fachadaPluBean.setIdPlu(rs.getInt("idPlu"));
                fachadaPluBean.setNombrePlu(rs.getString("nombrePlu"));
                fachadaPluBean.setVrGeneral(rs.getDouble("vrGeneral"));
                fachadaPluBean.setVrMayorista(rs.getDouble("vrMayorista"));
                fachadaPluBean.setPorcentajeIva(rs.getDouble("porcentajeIva"));
                fachadaPluBean.setIdTipo(rs.getInt("idTipo"));
                fachadaPluBean.setIdLinea(rs.getInt("idLinea"));
                fachadaPluBean.setIdUCompra(rs.getInt("idUCompra"));
                fachadaPluBean.setIdUVenta(rs.getInt("idUVenta"));
                fachadaPluBean.setVrCosto(rs.getDouble("vrCosto"));
                fachadaPluBean.setIdCategoria(rs.getInt("idCategoria"));
                fachadaPluBean.setIdMarca(rs.getInt("idMarca"));
                fachadaPluBean.setVrSucursal(rs.getDouble("vrSucursal"));
                fachadaPluBean.setFactorVenta(rs.getDouble("factorVenta"));
                fachadaPluBean.setFactorDespacho(
                        rs.getInt("factorDespacho"));
                fachadaPluBean.setEstado(rs.getInt("estado"));
                fachadaPluBean.setIdSeq(rs.getInt("idSeq"));
                fachadaPluBean.setReferencia(rs.getString("referencia"));
                fachadaPluBean.setNombreCategoria(
                        rs.getString("nombreCategoria"));
                fachadaPluBean.setNombreMarca(rs.getString("nombreMarca"));

                //
                contenedor.add(fachadaPluBean);
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
    public Vector listaNombrePluTop(String xNombrePlu) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        String selectStr =
                "SELECT TOP 500 tblPlus.idPlu,                "
                + "        tblPlus.nombrePlu,            "
                + "        tblPlus.vrGeneral,            "
                + "        tblPlus.vrMayorista,          "
                + "        tblPlus.porcentajeIva,        "
                + "        tblPlus.idTipo,               "
                + "        tblPlus.idLinea,              "
                + "        tblPlus.idUCompra,            "
                + "        tblPlus.idUVenta,             "
                + "        tblPlus.vrCosto,              "
                + "        tblPlus.idCategoria,          "
                + "        tblPlus.idMarca,              "
                + "        tblPlus.vrSucursal,           "
                + "        tblPlus.factorVenta,          "
                + "        tblPlus.factorDespacho,       "
                + "        tblPlus.estado,               "
                + "        tblPlus.idSeq,                "
                + "        tblPlus.referencia,           "
                + "        tblCategorias.nombreCategoria,"
                + "        tblMarcas.nombreMarca         "
                + "FROM tblPlus                          "
                + "INNER JOIN tblMarcas                  "
                + "ON tblPlus.idMarca      =             "
                + "                    tblMarcas.idMarca "
                + "INNER JOIN tblCategorias              "
                + "ON tblPlus.idLinea      =             "
                + "                tblCategorias.idLinea "
                + "AND tblPlus.idCategoria =             "
                + "            tblCategorias.IdCategoria "
                + "WHERE tblPlus.nombrePlu               "
                + "       LIKE (" + xNombrePlu + ")      "
                + "       OR tblCategorias.nombreCategoria LIKE (" + xNombrePlu + ")      "
                + "ORDER BY tblPlus.nombrePlu            ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaPluBean fachadaPluBean;

            while (rs.next()) {

                fachadaPluBean = new FachadaPluBean();

                //
                fachadaPluBean.setIdPlu(rs.getInt("idPlu"));
                fachadaPluBean.setNombrePlu(rs.getString("nombrePlu"));
                fachadaPluBean.setVrGeneral(rs.getDouble("vrGeneral"));
                fachadaPluBean.setVrMayorista(rs.getDouble("vrMayorista"));
                fachadaPluBean.setPorcentajeIva(rs.getDouble("porcentajeIva"));
                fachadaPluBean.setIdTipo(rs.getInt("idTipo"));
                fachadaPluBean.setIdLinea(rs.getInt("idLinea"));
                fachadaPluBean.setIdUCompra(rs.getInt("idUCompra"));
                fachadaPluBean.setIdUVenta(rs.getInt("idUVenta"));
                fachadaPluBean.setVrCosto(rs.getDouble("vrCosto"));
                fachadaPluBean.setIdCategoria(rs.getInt("idCategoria"));
                fachadaPluBean.setIdMarca(rs.getInt("idMarca"));
                fachadaPluBean.setVrSucursal(rs.getDouble("vrSucursal"));
                fachadaPluBean.setFactorVenta(rs.getDouble("factorVenta"));
                fachadaPluBean.setFactorDespacho(rs.getInt("factorDespacho"));
                fachadaPluBean.setEstado(rs.getInt("estado"));
                fachadaPluBean.setIdSeq(rs.getInt("idSeq"));
                fachadaPluBean.setReferencia(rs.getString("referencia"));
                fachadaPluBean.setNombreCategoria(rs.getString("nombreCategoria"));
                fachadaPluBean.setNombreMarca(rs.getString("nombreMarca"));

                //
                contenedor.add(fachadaPluBean);
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

    // listaNombreCategoria
    public Vector listaNombreCategoria(String xNombreCategoria) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        String selectStr =
                "SELECT  tblPlus.idPlu,                "
                + "        tblPlus.nombrePlu,            "
                + "        tblPlus.vrGeneral,            "
                + "        tblPlus.vrMayorista,          "
                + "        tblPlus.porcentajeIva,        "
                + "        tblPlus.idTipo,               "
                + "        tblPlus.idLinea,              "
                + "        tblPlus.idUCompra,            "
                + "        tblPlus.idUVenta,             "
                + "        tblPlus.vrCosto,              "
                + "        tblPlus.idCategoria,          "
                + "        tblPlus.idMarca,              "
                + "        tblPlus.vrSucursal,           "
                + "        tblPlus.factorVenta,          "
                + "        tblPlus.factorDespacho,       "
                + "        tblPlus.estado,               "
                + "        tblPlus.idSeq,                "
                + "        tblPlus.referencia,           "
                + "        tblCategorias.nombreCategoria,"
                + "        tblMarcas.nombreMarca,        "
                + " (SELECT tblPlusInventario.existencia "
                + "  FROM tblPlusInventario              "
                + "  WHERE tblPlusInventario.idPlu   =   "
                + "                       tblPlus.idPlu  "
                + "  AND   tblPlusInventario.idLocal =   "
                + getIdLocal() + "                       "
                + "  AND  tblPlusInventario.idBodega =   "
                + getIdBodega() + "    ) AS existencia   "
                + "FROM tblPlus                          "
                + "INNER JOIN tblMarcas                  "
                + "ON tblPlus.idMarca      =             "
                + "                    tblMarcas.idMarca "
                + "INNER JOIN tblCategorias              "
                + "ON tblPlus.idLinea      =             "
                + "                tblCategorias.idLinea "
                + "AND tblPlus.idCategoria =             "
                + "            tblCategorias.IdCategoria "
                + "WHERE tblCategorias.nombreCategoria   "
                + "      LIKE (" + xNombreCategoria + ") "
                + "OR   tblPlus.nombrePlu                "
                + "    LIKE (" + xNombreCategoria + ")   "
                + "ORDER BY tblMarcas.nombreMarca,       "
                + "         tblPlus.nombrePlu            ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaPluBean fachadaPluBean;

            while (rs.next()) {

                fachadaPluBean = new FachadaPluBean();

                //
                fachadaPluBean.setIdPlu(rs.getInt("idPlu"));
                fachadaPluBean.setNombrePlu(rs.getString("nombrePlu"));
                fachadaPluBean.setVrGeneral(rs.getDouble("vrGeneral"));
                fachadaPluBean.setVrMayorista(rs.getDouble("vrMayorista"));
                fachadaPluBean.setPorcentajeIva(rs.getDouble("porcentajeIva"));
                fachadaPluBean.setIdTipo(rs.getInt("idTipo"));
                fachadaPluBean.setIdLinea(rs.getInt("idLinea"));
                fachadaPluBean.setIdUCompra(rs.getInt("idUCompra"));
                fachadaPluBean.setIdUVenta(rs.getInt("idUVenta"));
                fachadaPluBean.setVrCosto(rs.getDouble("vrCosto"));
                fachadaPluBean.setIdCategoria(rs.getInt("idCategoria"));
                fachadaPluBean.setIdMarca(rs.getInt("idMarca"));
                fachadaPluBean.setVrSucursal(rs.getDouble("vrSucursal"));
                fachadaPluBean.setFactorVenta(rs.getDouble("factorVenta"));
                fachadaPluBean.setFactorDespacho(
                        rs.getInt("factorDespacho"));
                fachadaPluBean.setEstado(rs.getInt("estado"));
                fachadaPluBean.setIdSeq(rs.getInt("idSeq"));
                fachadaPluBean.setReferencia(rs.getString("referencia"));
                fachadaPluBean.setNombreCategoria(
                        rs.getString("nombreCategoria"));
                fachadaPluBean.setNombreMarca(rs.getString("nombreMarca"));
                fachadaPluBean.setExistencia(rs.getDouble("existencia"));

                //
                contenedor.add(fachadaPluBean);

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

    // listaPlu
    public Vector listaPlu() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        String selectStr =
                "SELECT  tblPlus.idPlu,                 "
                + "        tblPlus.nombrePlu,             "
                + "        tblPlus.vrGeneral,             "
                + "        tblPlus.vrMayorista,           "
                + "        tblPlus.porcentajeIva,         "
                + "        tblPlus.idTipo,                "
                + "        tblPlus.idLinea,               "
                + "        tblPlus.idUCompra,             "
                + "        tblPlus.idUVenta,              "
                + "        tblPlus.vrCosto,               "
                + "        tblPlus.idCategoria,           "
                + "        tblPlus.idMarca,               "
                + "        tblPlus.vrSucursal,            "
                + "        tblPlus.factorVenta,           "
                + "        tblPlus.factorDespacho,        "
                + "        tblPlus.estado,                "
                + "        tblPlus.idSeq,                 "
                + "        tblPlus.referencia,            "
                + "        tblCategorias.nombreCategoria, "
                + "        tblMarcas.nombreMarca,         "
                + "  (SELECT tblPlusInventario.existencia "
                + "   FROM tblPlusInventario              "
                + "   WHERE tblPlusInventario.idPlu   =   "
                + "                        tblPlus.idPlu  "
                + "   AND   tblPlusInventario.idLocal =   "
                + getIdLocal() + "                        "
                + "   AND  tblPlusInventario.idBodega =   "
                + getIdBodega() + "     ) AS existencia   "
                + "FROM tblPlus                           "
                + "INNER JOIN tblMarcas                   "
                + "ON tblPlus.idMarca      =              "
                + "                    tblMarcas.idMarca  "
                + "INNER JOIN tblCategorias               "
                + "ON tblPlus.idLinea      =              "
                + "                tblCategorias.idLinea  "
                + "AND tblPlus.idCategoria =              "
                + "            tblCategorias.IdCategoria  "
                + "WHERE tblPlus.idPlu =                  "
                + getIdPlu() + "                          "
                + "ORDER BY tblMarcas.nombreMarca,        "
                + "         tblPlus.nombrePlu             ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaPluBean fachadaPluBean;

            while (rs.next()) {

                fachadaPluBean = new FachadaPluBean();

                //
                fachadaPluBean.setIdPlu(rs.getInt("idPlu"));
                fachadaPluBean.setNombrePlu(rs.getString("nombrePlu"));
                fachadaPluBean.setVrGeneral(rs.getDouble("vrGeneral"));
                fachadaPluBean.setVrMayorista(rs.getDouble("vrMayorista"));
                fachadaPluBean.setPorcentajeIva(rs.getDouble("porcentajeIva"));
                fachadaPluBean.setIdTipo(rs.getInt("idTipo"));
                fachadaPluBean.setIdLinea(rs.getInt("idLinea"));
                fachadaPluBean.setIdUCompra(rs.getInt("idUCompra"));
                fachadaPluBean.setIdUVenta(rs.getInt("idUVenta"));
                fachadaPluBean.setVrCosto(rs.getDouble("vrCosto"));
                fachadaPluBean.setIdCategoria(rs.getInt("idCategoria"));
                fachadaPluBean.setIdMarca(rs.getInt("idMarca"));
                fachadaPluBean.setVrSucursal(rs.getDouble("vrSucursal"));
                fachadaPluBean.setFactorVenta(rs.getDouble("factorVenta"));
                fachadaPluBean.setFactorDespacho(
                        rs.getInt("factorDespacho"));
                fachadaPluBean.setEstado(rs.getInt("estado"));
                fachadaPluBean.setIdSeq(rs.getInt("idSeq"));
                fachadaPluBean.setReferencia(rs.getString("referencia"));
                fachadaPluBean.setNombreCategoria(
                        rs.getString("nombreCategoria"));
                fachadaPluBean.setNombreMarca(rs.getString("nombreMarca"));
                fachadaPluBean.setExistencia(rs.getDouble("existencia"));

                //
                contenedor.add(fachadaPluBean);
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

    // listaAllBodegaTipo
    public Vector listaAllBodegaTipo() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        String selectStr =
                "SELECT  tblPlus.idPlu,                 "
                + "        tblPlus.nombrePlu,             "
                + "        tblPlus.vrGeneral,             "
                + "        tblPlus.vrMayorista,           "
                + "        tblPlus.porcentajeIva,         "
                + "        tblPlus.idTipo,                "
                + "        tblPlus.idLinea,               "
                + "        tblPlus.idUCompra,             "
                + "        tblPlus.idUVenta,              "
                + "        tblPlus.vrCosto,               "
                + "        tblPlus.idCategoria,           "
                + "        tblPlus.idMarca,               "
                + "        tblPlus.vrSucursal,            "
                + "        tblPlus.factorVenta,           "
                + "        tblPlus.factorDespacho,        "
                + "        tblPlus.estado,                "
                + "        tblPlus.idSeq,                 "
                + "        tblPlus.referencia,            "
                + "        tblCategorias.nombreCategoria, "
                + "        tblMarcas.nombreMarca,         "
                + "  (SELECT tblPlusInventario.existencia "
                + "   FROM tblPlusInventario              "
                + "   WHERE tblPlusInventario.idPlu   =   "
                + "                        tblPlus.idPlu  "
                + "   AND   tblPlusInventario.idLocal =   "
                + getIdLocal() + "                        "
                + "   AND  tblPlusInventario.idBodega =   "
                + getIdBodega() + "     ) AS existencia   "
                + "FROM tblPlus                           "
                + "INNER JOIN tblMarcas                   "
                + "ON tblPlus.idMarca      =              "
                + "                    tblMarcas.idMarca  "
                + "INNER JOIN tblCategorias               "
                + "ON tblPlus.idLinea      =              "
                + "                tblCategorias.idLinea  "
                + "AND tblPlus.idCategoria =              "
                + "            tblCategorias.IdCategoria  "
                + "WHERE tblPlus.idTipo    =              "
                + getIdTipo() + "                         "
                + "ORDER BY tblCategorias.nombreCategoria,"
                + "         tblPlus.nombrePlu             ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaPluBean fachadaPluBean;

            while (rs.next()) {

                fachadaPluBean = new FachadaPluBean();

                //
                fachadaPluBean.setIdPlu(rs.getInt("idPlu"));
                fachadaPluBean.setNombrePlu(rs.getString("nombrePlu"));
                fachadaPluBean.setVrGeneral(rs.getDouble("vrGeneral"));
                fachadaPluBean.setVrMayorista(rs.getDouble("vrMayorista"));
                fachadaPluBean.setPorcentajeIva(rs.getDouble("porcentajeIva"));
                fachadaPluBean.setIdTipo(rs.getInt("idTipo"));
                fachadaPluBean.setIdLinea(rs.getInt("idLinea"));
                fachadaPluBean.setIdUCompra(rs.getInt("idUCompra"));
                fachadaPluBean.setIdUVenta(rs.getInt("idUVenta"));
                fachadaPluBean.setVrCosto(rs.getDouble("vrCosto"));
                fachadaPluBean.setIdCategoria(rs.getInt("idCategoria"));
                fachadaPluBean.setIdMarca(rs.getInt("idMarca"));
                fachadaPluBean.setVrSucursal(rs.getDouble("vrSucursal"));
                fachadaPluBean.setFactorVenta(rs.getDouble("factorVenta"));
                fachadaPluBean.setFactorDespacho(
                        rs.getInt("factorDespacho"));
                fachadaPluBean.setEstado(rs.getInt("estado"));
                fachadaPluBean.setIdSeq(rs.getInt("idSeq"));
                fachadaPluBean.setReferencia(rs.getString("referencia"));
                fachadaPluBean.setNombreCategoria(
                        rs.getString("nombreCategoria"));
                fachadaPluBean.setNombreMarca(rs.getString("nombreMarca"));
                fachadaPluBean.setExistencia(rs.getDouble("existencia"));

                //
                contenedor.add(fachadaPluBean);
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

    // listaEan
    public Vector listaEan() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        String selectStr =
                "SELECT  tblPlus.idPlu,                 "
                + "        tblPlus.nombrePlu,             "
                + "        tblPlus.vrGeneral,             "
                + "        tblPlus.vrMayorista,           "
                + "        tblPlus.porcentajeIva,         "
                + "        tblPlus.idTipo,                "
                + "        tblPlus.idLinea,               "
                + "        tblPlus.idUCompra,             "
                + "        tblPlus.idUVenta,              "
                + "        tblPlus.vrCosto,               "
                + "        tblPlus.idCategoria,           "
                + "        tblPlus.idMarca,               "
                + "        tblPlus.vrSucursal,            "
                + "        tblPlus.factorVenta,           "
                + "        tblPlus.factorDespacho,        "
                + "        tblPlus.estado,                "
                + "        tblPlus.idSeq,                 "
                + "        tblPlus.referencia,            "
                + "        tblCategorias.nombreCategoria, "
                + "        tblMarcas.nombreMarca,         "
                + "  (SELECT tblPlusInventario.existencia "
                + "   FROM tblPlusInventario              "
                + "   WHERE tblPlusInventario.idPlu   =   "
                + "                        tblPlus.idPlu  "
                + "   AND   tblPlusInventario.idLocal =   "
                + getIdLocal() + "                        "
                + "   AND  tblPlusInventario.idBodega =   "
                + getIdBodega() + "     ) AS existencia   "
                + "FROM tblPlus                           "
                + "INNER JOIN tblMarcas                   "
                + "ON tblPlus.idMarca        =            "
                + "                    tblMarcas.idMarca  "
                + "INNER JOIN tblCategorias               "
                + "ON tblPlus.idLinea        =            "
                + "                tblCategorias.idLinea  "
                + "AND tblPlus.idCategoria   =            "
                + "            tblCategorias.IdCategoria  "
                + "WHERE tblPlus.idPlu     IN             "
                + "  ( SELECT tblPlusEan.idPlu            "
                + "    FROM tblPlusEan                    "
                + "    WHERE tblPlusEan.ean  =           '"
                + getEan() + "'                           "
                + "    AND tblPlusEan.estado = 1   )      "
                + "ORDER BY tblMarcas.nombreMarca,        "
                + "         tblPlus.nombrePlu             ";



        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaPluBean fachadaPluBean;

            while (rs.next()) {

                fachadaPluBean = new FachadaPluBean();

                //
                fachadaPluBean.setIdPlu(rs.getInt("idPlu"));
                fachadaPluBean.setNombrePlu(rs.getString("nombrePlu"));
                fachadaPluBean.setVrGeneral(rs.getDouble("vrGeneral"));
                fachadaPluBean.setVrMayorista(rs.getDouble("vrMayorista"));
                fachadaPluBean.setPorcentajeIva(rs.getDouble("porcentajeIva"));
                fachadaPluBean.setIdTipo(rs.getInt("idTipo"));
                fachadaPluBean.setIdLinea(rs.getInt("idLinea"));
                fachadaPluBean.setIdUCompra(rs.getInt("idUCompra"));
                fachadaPluBean.setIdUVenta(rs.getInt("idUVenta"));
                fachadaPluBean.setVrCosto(rs.getDouble("vrCosto"));
                fachadaPluBean.setIdCategoria(rs.getInt("idCategoria"));
                fachadaPluBean.setIdMarca(rs.getInt("idMarca"));
                fachadaPluBean.setVrSucursal(rs.getDouble("vrSucursal"));
                fachadaPluBean.setFactorVenta(rs.getDouble("factorVenta"));
                fachadaPluBean.setFactorDespacho(
                        rs.getInt("factorDespacho"));
                fachadaPluBean.setEstado(rs.getInt("estado"));
                fachadaPluBean.setIdSeq(rs.getInt("idSeq"));
                fachadaPluBean.setReferencia(rs.getString("referencia"));
                fachadaPluBean.setNombreCategoria(
                        rs.getString("nombreCategoria"));
                fachadaPluBean.setNombreMarca(rs.getString("nombreMarca"));
                fachadaPluBean.setExistencia(rs.getDouble("existencia"));

                //
                contenedor.add(fachadaPluBean);

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
    
    // listaEan
    public ArrayList listaEanTras() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        ArrayList contenedor = new ArrayList();

        Connection connection = null;

        String selectStr =
                  " SELECT tmp.*                          "
                + " FROM                                  "
                + " (                                     "
                + "SELECT  tblPlus.idPlu,                 "
                + "        tblPlus.nombrePlu,             "
                + "        tblPlus.vrGeneral,             "
                + "        tblPlus.vrMayorista,           "
                + "        tblPlus.porcentajeIva,         "
                + "        tblPlus.idTipo,                "
                + "        tblPlus.idLinea,               "
                + "        tblPlus.idUCompra,             "
                + "        tblPlus.idUVenta,              "
                + "        tblPlus.vrCosto,               "
                + "        tblPlus.vrCostoIND,            "
                + "        tblPlus.idCategoria,           "
                + "        tblPlus.idMarca,               "
                + "        tblPlus.vrSucursal,            "
                + "        tblPlus.factorVenta,           "
                + "        tblPlus.factorDespacho,        "
                + "        tblPlus.estado,                "
                + "        tblPlus.idSeq,                 "
                + "        tblPlus.referencia,            "
                + "        tblCategorias.nombreCategoria, "
                + "        tblMarcas.nombreMarca,         "
		+ "  (  CASE                                                "        
		+ "     WHEN tblPlus.idTipo = 1                             "
		+ "     THEN                                                "
		+ "	(SELECT tblPlusInventario.existencia                "
		+ "	 FROM tblPlusInventario                             "
		+ "      WHERE tblPlusInventario.idPlu   =                  "
		+ "                             tblPlus.idPlu               "
		+ "	 AND   tblPlusInventario.idLocal =                  "
                + getIdLocal() + "                        "                
		+ "	 AND  tblPlusInventario.idBodega = "
                + getIdBodega() + "                         "
                + " )              "    
		+ "	 ELSE                                               "    
		+ "	(SELECT tblPlusInventario.existencia                "
		+ "	 FROM tblPlusInventario                             "
		+ "	 INNER JOIN tblPlusCombo                            "
		+ "	 ON tblPlusInventario.idPlu                         "
		+ "                 = tblPlusCombo.idPlu                    "
		+ "	 WHERE tblPlusCombo.idPluCombo   =                  "
		+ "                                 tblPlus.idPlu           "
		+ "	 AND   tblPlusInventario.idLocal =                "
                + getIdLocal() + "                        "                
		+ "	 AND  tblPlusInventario.idBodega = "
                + getIdBodega() + "                                     "
                + "  )             "
		+ "	 END                                                "
		+ "	 ) As existencia                                 "
                + "FROM tblPlus                           "
                + "INNER JOIN tblMarcas                   "
                + "ON tblPlus.idMarca        =            "
                + "                    tblMarcas.idMarca  "
                + "INNER JOIN tblCategorias               "
                + "ON tblPlus.idLinea        =            "
                + "                tblCategorias.idLinea  "
                + "AND tblPlus.idCategoria   =            "
                + "            tblCategorias.IdCategoria  "
                + "WHERE tblPlus.idPlu     IN             "
                + "  ( SELECT tblPlusEan.idPlu            "
                + "    FROM tblPlusEan                    "
                + "    WHERE tblPlusEan.ean  =           '"
                + getEan() + "'                           "
                + "      )      "
                + " ) As tmp                                "
                + " WHERE tmp.estado = 1                    "
		+ "		OR tmp.existencia > 0    "
                + " ORDER BY tmp.nombreMarca,               "
                + "          tmp.nombrePlu                  ";



        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaPluBean fachadaPluBean;

            while (rs.next()) {

                fachadaPluBean = new FachadaPluBean();

                //
                fachadaPluBean.setIdPlu(rs.getInt("idPlu"));
                fachadaPluBean.setNombrePlu(rs.getString("nombrePlu"));
                fachadaPluBean.setVrGeneral(rs.getDouble("vrGeneral"));
                fachadaPluBean.setVrMayorista(rs.getDouble("vrMayorista"));
                fachadaPluBean.setPorcentajeIva(rs.getDouble("porcentajeIva"));
                fachadaPluBean.setIdTipo(rs.getInt("idTipo"));
                fachadaPluBean.setIdLinea(rs.getInt("idLinea"));
                fachadaPluBean.setIdUCompra(rs.getInt("idUCompra"));
                fachadaPluBean.setIdUVenta(rs.getInt("idUVenta"));
                fachadaPluBean.setVrCosto(rs.getDouble("vrCosto"));
                fachadaPluBean.setVrCostoIND(rs.getDouble("vrCostoIND"));
                fachadaPluBean.setIdCategoria(rs.getInt("idCategoria"));
                fachadaPluBean.setIdMarca(rs.getInt("idMarca"));
                fachadaPluBean.setVrSucursal(rs.getDouble("vrSucursal"));
                fachadaPluBean.setFactorVenta(rs.getDouble("factorVenta"));
                fachadaPluBean.setFactorDespacho(
                        rs.getInt("factorDespacho"));
                fachadaPluBean.setEstado(rs.getInt("estado"));
                fachadaPluBean.setIdSeq(rs.getInt("idSeq"));
                fachadaPluBean.setReferencia(rs.getString("referencia"));
                fachadaPluBean.setNombreCategoria(
                        rs.getString("nombreCategoria"));
                fachadaPluBean.setNombreMarca(rs.getString("nombreMarca"));
                fachadaPluBean.setExistencia(rs.getDouble("existencia"));

                //
                contenedor.add(fachadaPluBean);

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

    // densidad
    public double densidad() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        //
        double xFactorDensidad = 0.0;

        //
        Connection connection = null;

        String selectStr =
                "SELECT  TOP 1                    "
                + "        tblPlus.factorDensidad   "
                + "FROM tblPlus                     "
                + "WHERE tblPlus.idLinea     =      "
                + getIdLinea() + "                  "
                + "AND   tblPlus.idCategoria =      "
                + getIdCategoria();

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
                xFactorDensidad = rs.getDouble("factorDensidad");

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
            return xFactorDensidad;

        }
    }

    // listaPluLocalBodega
    public Vector listaPluLocalBodega() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        String selectStr =
                "SELECT  tblPlus.idPlu,                 "
                + "        tblPlus.nombrePlu,             "
                + "        tblPlus.vrGeneral,             "
                + "        tblPlus.vrMayorista,           "
                + "        tblPlus.porcentajeIva,         "
                + "        tblPlus.idTipo,                "
                + "        tblPlus.idLinea,               "
                + "        tblPlus.idUCompra,             "
                + "        tblPlus.idUVenta,              "
                + "        tblPlus.vrCosto,               "
                + "        tblPlus.vrCostoIND,            "
                + "        tblPlus.idCategoria,           "
                + "        tblPlus.idMarca,               "
                + "        tblPlus.vrSucursal,            "
                + "        tblPlus.factorVenta,           "
                + "        tblPlus.factorDespacho,        "
                + "        tblPlus.estado,                "
                + "        tblPlus.idSeq,                 "
                + "        tblPlus.referencia,            "
                + "        tblCategorias.nombreCategoria, "
                + "        tblMarcas.nombreMarca,         "
                + "  (SELECT tblPlusInventario.existencia "
                + "   FROM tblPlusInventario              "
                + "   WHERE tblPlusInventario.idPlu   =   "
                + "                        tblPlus.idPlu  "
                + "   AND   tblPlusInventario.idLocal =   "
                + getIdLocal() + "                        "
                + "   AND  tblPlusInventario.idBodega =   "
                + getIdBodega() + "     ) AS existencia   "
                + "FROM tblPlus                           "
                + "INNER JOIN tblMarcas                   "
                + "ON tblPlus.idMarca      =              "
                + "                    tblMarcas.idMarca  "
                + "INNER JOIN tblCategorias               "
                + "ON tblPlus.idLinea      =              "
                + "                tblCategorias.idLinea  "
                + "AND tblPlus.idCategoria =              "
                + "            tblCategorias.IdCategoria  "
                + "WHERE tblPlus.idPlu =                  "
                + getIdPlu() + "                          "
                + "ORDER BY tblMarcas.nombreMarca,        "
                + "         tblPlus.nombrePlu             ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaPluBean fachadaPluBean;

            while (rs.next()) {

                //
                fachadaPluBean = new FachadaPluBean();

                //
                fachadaPluBean.setIdPlu(rs.getInt("idPlu"));
                fachadaPluBean.setNombrePlu(rs.getString("nombrePlu"));
                fachadaPluBean.setVrGeneral(rs.getDouble("vrGeneral"));
                fachadaPluBean.setVrMayorista(rs.getDouble("vrMayorista"));
                fachadaPluBean.setPorcentajeIva(rs.getDouble("porcentajeIva"));
                fachadaPluBean.setIdTipo(rs.getInt("idTipo"));
                fachadaPluBean.setIdLinea(rs.getInt("idLinea"));
                fachadaPluBean.setIdUCompra(rs.getInt("idUCompra"));
                fachadaPluBean.setIdUVenta(rs.getInt("idUVenta"));
                fachadaPluBean.setVrCosto(rs.getDouble("vrCosto"));
                fachadaPluBean.setVrCostoIND(rs.getDouble("vrCostoIND"));
                fachadaPluBean.setIdCategoria(rs.getInt("idCategoria"));
                fachadaPluBean.setIdMarca(rs.getInt("idMarca"));
                fachadaPluBean.setVrSucursal(rs.getDouble("vrSucursal"));
                fachadaPluBean.setFactorVenta(rs.getDouble("factorVenta"));
                fachadaPluBean.setFactorDespacho(
                        rs.getInt("factorDespacho"));
                fachadaPluBean.setEstado(rs.getInt("estado"));
                fachadaPluBean.setIdSeq(rs.getInt("idSeq"));
                fachadaPluBean.setReferencia(rs.getString("referencia"));
                fachadaPluBean.setNombreCategoria(
                        rs.getString("nombreCategoria"));
                fachadaPluBean.setNombreMarca(rs.getString("nombreMarca"));
                fachadaPluBean.setExistencia(rs.getDouble("existencia"));

                //
                contenedor.add(fachadaPluBean);
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

    // listaCategiriaDescripcion
    public Vector listaCategoriaDescripcion(String xCategoriaNombrePlu) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        String selectStr =
                "SELECT  tblPlus.idPlu,                "
                + "        tblPlus.nombrePlu,            "
                + "        tblPlus.vrGeneral,            "
                + "        tblPlus.vrMayorista,          "
                + "        tblPlus.porcentajeIva,        "
                + "        tblPlus.idTipo,               "
                + "        tblPlus.idLinea,              "
                + "        tblPlus.idUCompra,            "
                + "        tblPlus.idUVenta,             "
                + "        tblPlus.vrCosto,              "
                + "        tblPlus.idCategoria,          "
                + "        tblPlus.idMarca,              "
                + "        tblPlus.vrSucursal,           "
                + "        tblPlus.factorVenta,          "
                + "        tblPlus.factorDespacho,       "
                + "        tblPlus.estado,               "
                + "        tblPlus.idSeq,                "
                + "        tblPlus.referencia,           "
                + "        tblCategorias.nombreCategoria,"
                + "        tblMarcas.nombreMarca,        "
                + " (SELECT tblPlusInventario.existencia "
                + "  FROM tblPlusInventario              "
                + "  WHERE tblPlusInventario.idPlu   =   "
                + "                       tblPlus.idPlu  "
                + "  AND   tblPlusInventario.idLocal =   "
                + getIdLocal() + "                       "
                + "  AND  tblPlusInventario.idBodega =   "
                + getIdBodega() + "    ) AS existencia   "
                + "FROM tblPlus                          "
                + "INNER JOIN tblMarcas                  "
                + "ON tblPlus.idMarca      =             "
                + "                    tblMarcas.idMarca "
                + "INNER JOIN tblCategorias              "
                + "ON tblPlus.idLinea      =             "
                + "                tblCategorias.idLinea "
                + "AND tblPlus.idCategoria =             "
                + "            tblCategorias.IdCategoria "
                + "AND tblPlus.nombrePlu +               "
                + "     tblCategorias.nombreCategoria    "
                + " LIKE (" + xCategoriaNombrePlu + ")   "
                + "ORDER BY tblPlus.nombrePlu            ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaPluBean fachadaPluBean;

            while (rs.next()) {

                fachadaPluBean = new FachadaPluBean();

                //
                fachadaPluBean.setIdPlu(rs.getInt("idPlu"));
                fachadaPluBean.setNombrePlu(rs.getString("nombrePlu"));
                fachadaPluBean.setVrGeneral(rs.getDouble("vrGeneral"));
                fachadaPluBean.setVrMayorista(rs.getDouble("vrMayorista"));
                fachadaPluBean.setPorcentajeIva(rs.getDouble("porcentajeIva"));
                fachadaPluBean.setIdTipo(rs.getInt("idTipo"));
                fachadaPluBean.setIdLinea(rs.getInt("idLinea"));
                fachadaPluBean.setIdUCompra(rs.getInt("idUCompra"));
                fachadaPluBean.setIdUVenta(rs.getInt("idUVenta"));
                fachadaPluBean.setVrCosto(rs.getDouble("vrCosto"));
                fachadaPluBean.setIdCategoria(rs.getInt("idCategoria"));
                fachadaPluBean.setIdMarca(rs.getInt("idMarca"));
                fachadaPluBean.setVrSucursal(rs.getDouble("vrSucursal"));
                fachadaPluBean.setFactorVenta(rs.getDouble("factorVenta"));
                fachadaPluBean.setFactorDespacho(
                        rs.getInt("factorDespacho"));
                fachadaPluBean.setEstado(rs.getInt("estado"));
                fachadaPluBean.setIdSeq(rs.getInt("idSeq"));
                fachadaPluBean.setReferencia(rs.getString("referencia"));
                fachadaPluBean.setNombreCategoria(
                        rs.getString("nombreCategoria"));
                fachadaPluBean.setNombreMarca(rs.getString("nombreMarca"));
                fachadaPluBean.setExistencia(rs.getDouble("existencia"));

                //
                contenedor.add(fachadaPluBean);
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

    // listaPluCategoria
    public Vector listaPluCategoria() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        String selectStr =
                "SELECT  tblPlus.idPlu,                  "
                + "        tblPlus.nombrePlu,              "
                + "        tblPlus.vrGeneral,              "
                + "        tblPlus.vrMayorista,            "
                + "        tblPlus.porcentajeIva,          "
                + "        tblPlus.idTipo,                 "
                + "        tblPlus.idLinea,                "
                + "        tblPlus.idUCompra,              "
                + "        tblPlus.idUVenta,               "
                + "        tblPlus.vrCosto,                "
                + "        tblPlus.vrCostoIND,             "
                + "        tblPlus.idCategoria,            "
                + "        tblPlus.idMarca,                "
                + "        tblPlus.vrSucursal,             "
                + "        tblPlus.factorVenta,            "
                + "        tblPlus.factorDespacho,         "
                + "        tblPlus.estado,                 "
                + "        tblPlus.idSeq,                  "
                + "        tblPlus.referencia,             "
                + "        tblCategorias.nombreCategoria,  "
                + "        tblMarcas.nombreMarca,          "
                + " (SELECT tblPlusInventario.existencia   "
                + "  FROM tblPlusInventario                "
                + "  WHERE tblPlusInventario.idPlu   =     "
                + "                       tblPlus.idPlu    "
                + "  AND   tblPlusInventario.idLocal =     "
                + getIdLocal() + "                         "
                + "  AND  tblPlusInventario.idBodega =     "
                + getIdBodega() + "    ) AS existencia     "
                + "FROM tblPlus                            "
                + "INNER JOIN tblMarcas                    "
                + "ON tblPlus.idMarca      =               "
                + "                    tblMarcas.idMarca   "
                + "INNER JOIN tblCategorias                "
                + "ON tblPlus.idLinea      =               "
                + "                tblCategorias.idLinea   "
                + "AND tblPlus.idCategoria =               "
                + "            tblCategorias.IdCategoria   "
                + "WHERE tblPlus.idLinea     =             "
                + getIdLinea() + "                         "
                + "AND   tblPlus.idCategoria =             "
                + getIdCategoria() + "                     "
                + "ORDER BY tblCategorias.nombreCategoria, "
                + "         tblPlus.nombrePlu";


        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaPluBean fachadaPluBean;

            while (rs.next()) {

                fachadaPluBean = new FachadaPluBean();

                //
                fachadaPluBean.setIdPlu(rs.getInt("idPlu"));
                fachadaPluBean.setNombrePlu(rs.getString("nombrePlu"));
                fachadaPluBean.setVrGeneral(rs.getDouble("vrGeneral"));
                fachadaPluBean.setVrMayorista(rs.getDouble("vrMayorista"));
                fachadaPluBean.setPorcentajeIva(rs.getDouble("porcentajeIva"));
                fachadaPluBean.setIdTipo(rs.getInt("idTipo"));
                fachadaPluBean.setIdLinea(rs.getInt("idLinea"));
                fachadaPluBean.setIdUCompra(rs.getInt("idUCompra"));
                fachadaPluBean.setIdUVenta(rs.getInt("idUVenta"));
                fachadaPluBean.setVrCosto(rs.getDouble("vrCosto"));
                fachadaPluBean.setVrCostoIND(rs.getDouble("vrCostoIND"));
                fachadaPluBean.setIdCategoria(rs.getInt("idCategoria"));
                fachadaPluBean.setIdMarca(rs.getInt("idMarca"));
                fachadaPluBean.setVrSucursal(rs.getDouble("vrSucursal"));
                fachadaPluBean.setFactorVenta(rs.getDouble("factorVenta"));
                fachadaPluBean.setFactorDespacho(
                        rs.getInt("factorDespacho"));
                fachadaPluBean.setEstado(rs.getInt("estado"));
                fachadaPluBean.setIdSeq(rs.getInt("idSeq"));
                fachadaPluBean.setReferencia(rs.getString("referencia"));
                fachadaPluBean.setNombreCategoria(
                        rs.getString("nombreCategoria"));
                fachadaPluBean.setNombreMarca(rs.getString("nombreMarca"));
                fachadaPluBean.setExistencia(rs.getDouble("existencia"));

                //
                contenedor.add(fachadaPluBean);
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

    // listaAllPluCategoria
    public Vector listaAllPluCategoria() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        String selectStr =
                "SELECT  tblPlus.idPlu,                 "
                + "        tblPlus.nombrePlu,             "
                + "        tblPlus.vrGeneral,             "
                + "        tblPlus.vrMayorista,           "
                + "        tblPlus.porcentajeIva,         "
                + "        tblPlus.idTipo,                "
                + "        tblPlus.idLinea,               "
                + "        tblPlus.idUCompra,             "
                + "        tblPlus.idUVenta,              "
                + "        tblPlus.vrCosto,               "
                + "        tblPlus.vrCostoIND,            "
                + "        tblPlus.idCategoria,           "
                + "        tblPlus.idMarca,               "
                + "        tblPlus.vrSucursal,            "
                + "        tblPlus.factorVenta,           "
                + "        tblPlus.factorDespacho,        "
                + "        tblPlus.estado,                "
                + "        tblPlus.idSeq,                 "
                + "        tblPlus.referencia,            "
                + "        tblCategorias.nombreCategoria, "
                + "        tblMarcas.nombreMarca,         "
                + " (SELECT tblPlusInventario.existencia  "
                + "  FROM tblPlusInventario               "
                + "  WHERE tblPlusInventario.idPlu   =    "
                + "                       tblPlus.idPlu   "
                + "  AND   tblPlusInventario.idLocal =    "
                + getIdLocal() + "                        "
                + "  AND  tblPlusInventario.idBodega =    "
                + getIdBodega() + "    ) AS existencia    "
                + "FROM tblPlus                           "
                + "INNER JOIN tblMarcas                   "
                + "ON tblPlus.idMarca      =              "
                + "                    tblMarcas.idMarca  "
                + "INNER JOIN tblCategorias               "
                + "ON tblPlus.idLinea      =              "
                + "                tblCategorias.idLinea  "
                + "AND tblPlus.idCategoria =              "
                + "            tblCategorias.IdCategoria  "
                + "ORDER BY tblCategorias.nombreCategoria,"
                + "         tblPlus.nombrePlu             ";


        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaPluBean fachadaPluBean;

            while (rs.next()) {

                fachadaPluBean = new FachadaPluBean();

                //
                fachadaPluBean.setIdPlu(rs.getInt("idPlu"));
                fachadaPluBean.setNombrePlu(rs.getString("nombrePlu"));
                fachadaPluBean.setVrGeneral(rs.getDouble("vrGeneral"));
                fachadaPluBean.setVrMayorista(rs.getDouble("vrMayorista"));
                fachadaPluBean.setPorcentajeIva(rs.getDouble("porcentajeIva"));
                fachadaPluBean.setIdTipo(rs.getInt("idTipo"));
                fachadaPluBean.setIdLinea(rs.getInt("idLinea"));
                fachadaPluBean.setIdUCompra(rs.getInt("idUCompra"));
                fachadaPluBean.setIdUVenta(rs.getInt("idUVenta"));
                fachadaPluBean.setVrCosto(rs.getDouble("vrCosto"));
                fachadaPluBean.setVrCostoIND(rs.getDouble("vrCostoIND"));
                fachadaPluBean.setIdCategoria(rs.getInt("idCategoria"));
                fachadaPluBean.setIdMarca(rs.getInt("idMarca"));
                fachadaPluBean.setVrSucursal(rs.getDouble("vrSucursal"));
                fachadaPluBean.setFactorVenta(rs.getDouble("factorVenta"));
                fachadaPluBean.setFactorDespacho(
                        rs.getInt("factorDespacho"));
                fachadaPluBean.setEstado(rs.getInt("estado"));
                fachadaPluBean.setIdSeq(rs.getInt("idSeq"));
                fachadaPluBean.setReferencia(rs.getString("referencia"));
                fachadaPluBean.setNombreCategoria(
                        rs.getString("nombreCategoria"));
                fachadaPluBean.setNombreMarca(rs.getString("nombreMarca"));
                fachadaPluBean.setExistencia(rs.getDouble("existencia"));

                //
                contenedor.add(fachadaPluBean);
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

    // listaPluCategoriaOpcion
    public Vector listaPluCategoriaOpcion() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        String selectStr =
                " SELECT  tblPlus.idPlu,                    "
                + "         tblPlus.nombrePlu,              "
                + "         01 AS ordenSalida               "
                + " FROM tblPlus                            "
                + " INNER JOIN tblMarcas                    "
                + " ON tblPlus.idMarca      =               "
                + "                     tblMarcas.idMarca   "
                + " INNER JOIN tblCategorias                "
                + " ON tblPlus.idLinea      =               "
                + "                 tblCategorias.idLinea   "
                + " AND tblPlus.idCategoria =               "
                + "             tblCategorias.IdCategoria   "
                + " WHERE tblPlus.idLinea     =             "
                + getIdLinea() + "                          "
                + " AND   tblPlus.idCategoria =             "
                + getIdCategoria() + "                      "
                + " AND   tblPlus.idPlu       =             "
                + getIdPlu() + "                            "
                + " UNION                                   "
                + " SELECT  TOP 1 00 AS idPlu,              "
                + "         'NN' AS nombrePlu,              "
                + "         02 AS ordenSalida               "
                + " FROM tblPlus                            "
                + " UNION                                   "
                + " SELECT  tblPlus.idPlu,                  "
                + "         tblPlus.nombrePlu,              "
                + "         03 AS ordenSalida               "
                + " FROM tblPlus                            "
                + " INNER JOIN tblMarcas                    "
                + " ON tblPlus.idMarca      =               "
                + "                     tblMarcas.idMarca   "
                + " INNER JOIN tblCategorias                "
                + " ON tblPlus.idLinea      =               "
                + "                 tblCategorias.idLinea   "
                + " AND tblPlus.idCategoria =               "
                + "             tblCategorias.IdCategoria   "
                + " WHERE tblPlus.idLinea     =             "
                + getIdLinea() + "                          "
                + " AND   tblPlus.idCategoria =             "
                + getIdCategoria() + "                      "
                + " AND   tblPlus.idPlu      !=             "
                + getIdPlu() + "                            "
                + " ORDER BY ordenSalida                    ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaPluBean fachadaPluBean;

            while (rs.next()) {

                fachadaPluBean = new FachadaPluBean();

                //
                fachadaPluBean.setIdPlu(rs.getInt("idPlu"));
                fachadaPluBean.setNombrePlu(rs.getString("nombrePlu"));

                //
                contenedor.add(fachadaPluBean);
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

    // listaPluMarca
    public Vector listaPluMarca() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        String selectStr =
                "SELECT  tblPlus.idPlu,                "
                + "        tblPlus.nombrePlu,            "
                + "        tblPlus.vrGeneral,            "
                + "        tblPlus.vrMayorista,          "
                + "        tblPlus.porcentajeIva,        "
                + "        tblPlus.idTipo,               "
                + "        tblPlus.idLinea,              "
                + "        tblPlus.idUCompra,            "
                + "        tblPlus.idUVenta,             "
                + "        tblPlus.vrCosto,              "
                + "        tblPlus.idCategoria,          "
                + "        tblPlus.idMarca,              "
                + "        tblPlus.vrSucursal,           "
                + "        tblPlus.factorVenta,          "
                + "        tblPlus.factorDespacho,       "
                + "        tblPlus.estado,               "
                + "        tblPlus.idSeq,                "
                + "        tblPlus.referencia,           "
                + "        tblCategorias.nombreCategoria,"
                + "        tblMarcas.nombreMarca,        "
                + " (SELECT tblPlusInventario.existencia "
                + "  FROM tblPlusInventario              "
                + "  WHERE tblPlusInventario.idPlu   =   "
                + "                       tblPlus.idPlu  "
                + "  AND   tblPlusInventario.idLocal =   "
                + getIdLocal() + "                       "
                + "  AND  tblPlusInventario.idBodega =   "
                + getIdBodega() + "    ) AS existencia   "
                + "FROM tblPlus                          "
                + "INNER JOIN tblMarcas                  "
                + "ON tblPlus.idMarca      =             "
                + "                    tblMarcas.idMarca "
                + "INNER JOIN tblCategorias              "
                + "ON tblPlus.idLinea      =             "
                + "                tblCategorias.idLinea "
                + "AND tblPlus.idCategoria =             "
                + "            tblCategorias.IdCategoria "
                + "WHERE tblPlus.idMarca     =           "
                + getIdMarca() + "                       "
                + "AND tblPlus.idTipo        =           "
                + getIdTipo() + "                        "
                + "ORDER BY tblMarcas.nombreMarca,       "
                + "         tblPlus.nombrePlu            ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaPluBean fachadaPluBean;

            while (rs.next()) {

                fachadaPluBean = new FachadaPluBean();

                //
                fachadaPluBean.setIdPlu(rs.getInt("idPlu"));
                fachadaPluBean.setNombrePlu(rs.getString("nombrePlu"));
                fachadaPluBean.setVrGeneral(rs.getDouble("vrGeneral"));
                fachadaPluBean.setVrMayorista(rs.getDouble("vrMayorista"));
                fachadaPluBean.setPorcentajeIva(rs.getDouble("porcentajeIva"));
                fachadaPluBean.setIdTipo(rs.getInt("idTipo"));
                fachadaPluBean.setIdLinea(rs.getInt("idLinea"));
                fachadaPluBean.setIdUCompra(rs.getInt("idUCompra"));
                fachadaPluBean.setIdUVenta(rs.getInt("idUVenta"));
                fachadaPluBean.setVrCosto(rs.getDouble("vrCosto"));
                fachadaPluBean.setIdCategoria(rs.getInt("idCategoria"));
                fachadaPluBean.setIdMarca(rs.getInt("idMarca"));
                fachadaPluBean.setVrSucursal(rs.getDouble("vrSucursal"));
                fachadaPluBean.setFactorVenta(rs.getDouble("factorVenta"));
                fachadaPluBean.setFactorDespacho(
                        rs.getInt("factorDespacho"));
                fachadaPluBean.setEstado(rs.getInt("estado"));
                fachadaPluBean.setIdSeq(rs.getInt("idSeq"));
                fachadaPluBean.setReferencia(rs.getString("referencia"));
                fachadaPluBean.setNombreCategoria(
                        rs.getString("nombreCategoria"));
                fachadaPluBean.setNombreMarca(rs.getString("nombreMarca"));
                fachadaPluBean.setExistencia(rs.getDouble("existencia"));

                //
                contenedor.add(fachadaPluBean);
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

    // listaAllPluMarca
    public Vector listaAllPluMarca() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        String selectStr =
                "SELECT  tblPlus.idPlu,                 "
                + "        tblPlus.nombrePlu,             "
                + "        tblPlus.vrGeneral,             "
                + "        tblPlus.vrMayorista,           "
                + "        tblPlus.porcentajeIva,         "
                + "        tblPlus.idTipo,                "
                + "        tblPlus.idLinea,               "
                + "        tblPlus.idUCompra,             "
                + "        tblPlus.idUVenta,              "
                + "        tblPlus.vrCosto,               "
                + "        tblPlus.idCategoria,           "
                + "        tblPlus.idMarca,               "
                + "        tblPlus.vrSucursal,            "
                + "        tblPlus.factorVenta,           "
                + "        tblPlus.factorDespacho,        "
                + "        tblPlus.estado,                "
                + "        tblPlus.idSeq,                 "
                + "        tblPlus.referencia,            "
                + "        tblCategorias.nombreCategoria, "
                + "        tblMarcas.nombreMarca,         "
                + " (SELECT tblPlusInventario.existencia  "
                + "  FROM tblPlusInventario               "
                + "  WHERE tblPlusInventario.idPlu   =    "
                + "                       tblPlus.idPlu   "
                + "  AND   tblPlusInventario.idLocal =    "
                + getIdLocal() + "                        "
                + "  AND  tblPlusInventario.idBodega =    "
                + getIdBodega() + "    ) AS existencia    "
                + "FROM tblPlus                           "
                + "INNER JOIN tblMarcas                   "
                + "ON tblPlus.idMarca      =              "
                + "                    tblMarcas.idMarca  "
                + "INNER JOIN tblCategorias               "
                + "ON tblPlus.idLinea      =              "
                + "                tblCategorias.idLinea  "
                + "AND tblPlus.idCategoria =              "
                + "            tblCategorias.IdCategoria  "
                + "AND tblPlus.idTipo      =              "
                + getIdTipo() + "                         "
                + "ORDER BY tblMarcas.nombreMarca,        "
                + "         tblPlus.nombrePlu             ";


        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaPluBean fachadaPluBean;

            while (rs.next()) {

                fachadaPluBean = new FachadaPluBean();

                //
                fachadaPluBean.setIdPlu(rs.getInt("idPlu"));
                fachadaPluBean.setNombrePlu(rs.getString("nombrePlu"));
                fachadaPluBean.setVrGeneral(rs.getDouble("vrGeneral"));
                fachadaPluBean.setVrMayorista(rs.getDouble("vrMayorista"));
                fachadaPluBean.setPorcentajeIva(rs.getDouble("porcentajeIva"));
                fachadaPluBean.setIdTipo(rs.getInt("idTipo"));
                fachadaPluBean.setIdLinea(rs.getInt("idLinea"));
                fachadaPluBean.setIdUCompra(rs.getInt("idUCompra"));
                fachadaPluBean.setIdUVenta(rs.getInt("idUVenta"));
                fachadaPluBean.setVrCosto(rs.getDouble("vrCosto"));
                fachadaPluBean.setIdCategoria(rs.getInt("idCategoria"));
                fachadaPluBean.setIdMarca(rs.getInt("idMarca"));
                fachadaPluBean.setVrSucursal(rs.getDouble("vrSucursal"));
                fachadaPluBean.setFactorVenta(rs.getDouble("factorVenta"));
                fachadaPluBean.setFactorDespacho(
                        rs.getInt("factorDespacho"));
                fachadaPluBean.setEstado(rs.getInt("estado"));
                fachadaPluBean.setIdSeq(rs.getInt("idSeq"));
                fachadaPluBean.setReferencia(rs.getString("referencia"));
                fachadaPluBean.setNombreCategoria(
                        rs.getString("nombreCategoria"));
                fachadaPluBean.setNombreMarca(rs.getString("nombreMarca"));
                fachadaPluBean.setExistencia(rs.getDouble("existencia"));

                //
                contenedor.add(fachadaPluBean);
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

    // listaNombreCategoria
    public Vector listaNombrePluCategoria(String xNombreCategoria,
            String xNombrePlu) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        String selectStr =
                "SELECT  tblPlus.idPlu,                "
                + "        tblPlus.nombrePlu,            "
                + "        tblPlus.vrGeneral,            "
                + "        tblPlus.vrMayorista,          "
                + "        tblPlus.porcentajeIva,        "
                + "        tblPlus.idTipo,               "
                + "        tblPlus.idLinea,              "
                + "        tblPlus.idUCompra,            "
                + "        tblPlus.idUVenta,             "
                + "        tblPlus.vrCosto,              "
                + "        tblPlus.idCategoria,          "
                + "        tblPlus.idMarca,              "
                + "        tblPlus.vrSucursal,           "
                + "        tblPlus.factorVenta,          "
                + "        tblPlus.factorDespacho,       "
                + "        tblPlus.estado,               "
                + "        tblPlus.idSeq,                "
                + "        tblPlus.referencia,           "
                + "        tblCategorias.nombreCategoria,"
                + "        tblMarcas.nombreMarca,        "
                + " (SELECT tblPlusInventario.existencia "
                + "  FROM tblPlusInventario              "
                + "  WHERE tblPlusInventario.idPlu   =   "
                + "                       tblPlus.idPlu  "
                + "  AND   tblPlusInventario.idLocal =   "
                + getIdLocal() + "                       "
                + "  AND  tblPlusInventario.idBodega =   "
                + getIdBodega() + "    ) AS existencia   "
                + "FROM tblPlus                          "
                + "INNER JOIN tblMarcas                  "
                + "ON tblPlus.idMarca      =             "
                + "                    tblMarcas.idMarca "
                + "INNER JOIN tblCategorias              "
                + "ON tblPlus.idLinea      =             "
                + "                tblCategorias.idLinea "
                + "AND tblPlus.idCategoria =             "
                + "            tblCategorias.IdCategoria "
                + "WHERE tblCategorias.nombreCategoria   "
                + "      LIKE (" + xNombreCategoria + ") "
                + "AND   tblPlus.nombrePlu               "
                + "       LIKE (" + xNombrePlu + ")      "
                + "ORDER BY tblMarcas.nombreMarca,       "
                + "         tblPlus.nombrePlu            ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaPluBean fachadaPluBean;

            while (rs.next()) {

                fachadaPluBean = new FachadaPluBean();

                //
                fachadaPluBean.setIdPlu(rs.getInt("idPlu"));
                fachadaPluBean.setNombrePlu(rs.getString("nombrePlu"));
                fachadaPluBean.setVrGeneral(rs.getDouble("vrGeneral"));
                fachadaPluBean.setVrMayorista(rs.getDouble("vrMayorista"));
                fachadaPluBean.setPorcentajeIva(rs.getDouble("porcentajeIva"));
                fachadaPluBean.setIdTipo(rs.getInt("idTipo"));
                fachadaPluBean.setIdLinea(rs.getInt("idLinea"));
                fachadaPluBean.setIdUCompra(rs.getInt("idUCompra"));
                fachadaPluBean.setIdUVenta(rs.getInt("idUVenta"));
                fachadaPluBean.setVrCosto(rs.getDouble("vrCosto"));
                fachadaPluBean.setIdCategoria(rs.getInt("idCategoria"));
                fachadaPluBean.setIdMarca(rs.getInt("idMarca"));
                fachadaPluBean.setVrSucursal(rs.getDouble("vrSucursal"));
                fachadaPluBean.setFactorVenta(rs.getDouble("factorVenta"));
                fachadaPluBean.setFactorDespacho(
                        rs.getInt("factorDespacho"));
                fachadaPluBean.setEstado(rs.getInt("estado"));
                fachadaPluBean.setIdSeq(rs.getInt("idSeq"));
                fachadaPluBean.setReferencia(rs.getString("referencia"));
                fachadaPluBean.setNombreCategoria(
                        rs.getString("nombreCategoria"));
                fachadaPluBean.setNombreMarca(rs.getString("nombreMarca"));
                fachadaPluBean.setExistencia(rs.getDouble("existencia"));

                //
                contenedor.add(fachadaPluBean);
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

    // listaUnPluFCH
    public FachadaPluBean listaUnPluFCH() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        // Variable de fachada de los datos
        FachadaPluBean fachadaPluBean = new FachadaPluBean();

        Connection connection = null;

        String selectStr =
                "SELECT  tblPlus.idPlu,                "
                + "        tblPlus.nombrePlu,            "
                + "        tblPlus.vrGeneral,            "
                + "        tblPlus.vrMayorista,          "
                + "        tblPlus.porcentajeIva,        "
                + "        tblPlus.idTipo,               "
                + "        tblPlus.idLinea,              "
                + "        tblPlus.idUCompra,            "
                + "        tblPlus.idUVenta,             "
                + "        tblPlus.vrCosto,              "
                + "        tblPlus.idCategoria,          "
                + "        tblPlus.idMarca,              "
                + "        tblPlus.vrSucursal,           "
                + "        tblPlus.factorVenta,          "
                + "        tblPlus.factorDespacho,       "
                + "        tblPlus.estado,               "
                + "        tblPlus.idSeq,                "
                + "        tblPlus.referencia,           "
                + "        tblCategorias.nombreCategoria,"
                + "        tblMarcas.nombreMarca,        "
                + "        tblPlus.vrImpoconsumo,        "
                + "        tblPlus.vrCostoIND,           "
                + "        tblPlus.factorDensidad,       "
                + "        tblPlus.sk_tercero_ppal,      "
                + "        tblPlus.factor,               "
                + "        tblPlus.dolarizado            "
                + "FROM tblPlus                          "
                + "INNER JOIN tblMarcas                  "
                + "ON tblPlus.idMarca      =             "
                + "                    tblMarcas.idMarca "
                + "INNER JOIN tblCategorias              "
                + "ON tblPlus.idLinea      =             "
                + "                tblCategorias.idLinea "
                + "AND tblPlus.idCategoria =             "
                + "            tblCategorias.IdCategoria "
                + "WHERE tblPlus.idPlu =                 "
                + getIdPlu() + "                         ";

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
                fachadaPluBean.setIdPlu(rs.getInt("idPlu"));
                fachadaPluBean.setNombrePlu(rs.getString("nombrePlu"));
                fachadaPluBean.setVrGeneral(rs.getDouble("vrGeneral"));
                fachadaPluBean.setVrMayorista(rs.getDouble("vrMayorista"));
                fachadaPluBean.setPorcentajeIva(rs.getDouble("porcentajeIva"));
                fachadaPluBean.setIdTipo(rs.getInt("idTipo"));
                fachadaPluBean.setIdLinea(rs.getInt("idLinea"));
                fachadaPluBean.setIdUCompra(rs.getInt("idUCompra"));
                fachadaPluBean.setIdUVenta(rs.getInt("idUVenta"));
                fachadaPluBean.setVrCosto(rs.getDouble("vrCosto"));
                fachadaPluBean.setIdCategoria(rs.getInt("idCategoria"));
                fachadaPluBean.setIdMarca(rs.getInt("idMarca"));
                fachadaPluBean.setVrSucursal(rs.getDouble("vrSucursal"));
                fachadaPluBean.setFactorVenta(rs.getDouble("factorVenta"));
                fachadaPluBean.setFactorDespacho(
                        rs.getInt("factorDespacho"));
                fachadaPluBean.setEstado(rs.getInt("estado"));
                fachadaPluBean.setIdSeq(rs.getInt("idSeq"));
                fachadaPluBean.setReferencia(rs.getString("referencia"));
                fachadaPluBean.setNombreCategoria(
                        rs.getString("nombreCategoria"));
                fachadaPluBean.setNombreMarca(rs.getString("nombreMarca"));
                fachadaPluBean.setVrImpoconsumo(rs.getDouble("vrImpoconsumo"));
                fachadaPluBean.setVrCostoIND(rs.getDouble("vrCostoIND"));
                fachadaPluBean.setFactorDensidad(rs.getDouble("factorDensidad"));
                fachadaPluBean.setSk_proveedor(rs.getDouble("sk_tercero_ppal"));
                fachadaPluBean.setFactor(rs.getDouble("factor"));
                fachadaPluBean.setDolarizado(rs.getInt("dolarizado"));

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
            return fachadaPluBean;

        }
    }

    // listaPluTipo
    public Vector listaPluTipo() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        String selectStr =
                "SELECT  tblPlus.idPlu,                "
                + "        tblPlus.nombrePlu,            "
                + "        tblPlus.vrGeneral,            "
                + "        tblPlus.vrMayorista,          "
                + "        tblPlus.porcentajeIva,        "
                + "        tblPlus.idTipo,               "
                + "        tblPlus.idLinea,              "
                + "        tblPlus.idUCompra,            "
                + "        tblPlus.idUVenta,             "
                + "        tblPlus.vrCosto,              "
                + "        tblPlus.vrCostoIND,           "
                + "        tblPlus.idCategoria,          "
                + "        tblPlus.idMarca,              "
                + "        tblPlus.vrSucursal,           "
                + "        tblPlus.factorVenta,          "
                + "        tblPlus.factorDespacho,       "
                + "        tblPlus.estado,               "
                + "        tblPlus.idSeq,                "
                + "        tblPlus.referencia,           "
                + "        tblCategorias.nombreCategoria,"
                + "        tblMarcas.nombreMarca,        "
                + " (SELECT tblPlusInventario.existencia "
                + "  FROM tblPlusInventario              "
                + "  WHERE tblPlusInventario.idPlu   =   "
                + "                       tblPlus.idPlu  "
                + "  AND   tblPlusInventario.idLocal =   "
                + getIdLocal() + "                       "
                + "  AND  tblPlusInventario.idBodega =   "
                + getIdBodega() + "    ) AS existencia,  "
                + "        tblPlus.vrImpoconsumo         "
                + "FROM tblPlus                          "
                + "INNER JOIN tblMarcas                  "
                + "ON tblPlus.idMarca      =             "
                + "                    tblMarcas.idMarca "
                + "INNER JOIN tblCategorias              "
                + "ON tblPlus.idLinea      =             "
                + "                tblCategorias.idLinea "
                + "AND tblPlus.idCategoria =             "
                + "            tblCategorias.IdCategoria "
                + "WHERE tblPlus.idTipo    =             "
                + getIdTipo() + "                        "
                + "ORDER BY tblCategorias.nombreCategoria"
                + "         ,tblPlus.nombrePlu           ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaPluBean fachadaPluBean;

            while (rs.next()) {

                //
                fachadaPluBean = new FachadaPluBean();

                //
                fachadaPluBean.setIdPlu(rs.getInt("idPlu"));
                fachadaPluBean.setNombrePlu(rs.getString("nombrePlu"));
                fachadaPluBean.setVrGeneral(rs.getDouble("vrGeneral"));
                fachadaPluBean.setVrMayorista(rs.getDouble("vrMayorista"));
                fachadaPluBean.setPorcentajeIva(rs.getDouble("porcentajeIva"));
                fachadaPluBean.setIdTipo(rs.getInt("idTipo"));
                fachadaPluBean.setIdLinea(rs.getInt("idLinea"));
                fachadaPluBean.setIdUCompra(rs.getInt("idUCompra"));
                fachadaPluBean.setIdUVenta(rs.getInt("idUVenta"));
                fachadaPluBean.setVrCosto(rs.getDouble("vrCosto"));
                fachadaPluBean.setVrCostoIND(rs.getDouble("vrCostoIND"));
                fachadaPluBean.setIdCategoria(rs.getInt("idCategoria"));
                fachadaPluBean.setIdMarca(rs.getInt("idMarca"));
                fachadaPluBean.setVrSucursal(rs.getDouble("vrSucursal"));
                fachadaPluBean.setFactorVenta(rs.getDouble("factorVenta"));
                fachadaPluBean.setFactorDespacho(
                        rs.getInt("factorDespacho"));
                fachadaPluBean.setEstado(rs.getInt("estado"));
                fachadaPluBean.setIdSeq(rs.getInt("idSeq"));
                fachadaPluBean.setReferencia(rs.getString("referencia"));
                fachadaPluBean.setNombreCategoria(
                        rs.getString("nombreCategoria"));
                fachadaPluBean.setNombreMarca(rs.getString("nombreMarca"));
                fachadaPluBean.setExistencia(rs.getDouble("existencia"));
                fachadaPluBean.setVrImpoconsumo(rs.getDouble("vrImpoconsumo"));

                //
                contenedor.add(fachadaPluBean);
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

    // listaPluTipoOpcion
    public Vector listaPluTipoOpcion() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        String selectStr =
                "SELECT  tblPlus.idPlu,                "
                + "        tblPlus.nombrePlu,            "
                + "        tblCategorias.nombreCategoria,"
                + "         02 AS ordenSalida            "
                + "FROM tblPlus                          "
                + "INNER JOIN tblMarcas                  "
                + "ON tblPlus.idMarca      =             "
                + "                    tblMarcas.idMarca "
                + "INNER JOIN tblCategorias              "
                + "ON tblPlus.idLinea      =             "
                + "                tblCategorias.idLinea "
                + "AND tblPlus.idCategoria =             "
                + "            tblCategorias.IdCategoria "
                + "WHERE tblPlus.idTipo    =             "
                + getIdTipo() + "                        "
                + "UNION                                 "
                + "SELECT  TOP 1 00 AS idPlu,            "
                + "        'NN' AS nombrePlu,            "
                + "        'NN' AS nombreCategoria,      "
                + "        01 AS ordenSalida             "
                + "FROM tblPlus                          "
                + "INNER JOIN tblMarcas                  "
                + "ON tblPlus.idMarca      =             "
                + "                    tblMarcas.idMarca "
                + "INNER JOIN tblCategorias              "
                + "ON tblPlus.idLinea      =             "
                + "                tblCategorias.idLinea "
                + "AND tblPlus.idCategoria =             "
                + "            tblCategorias.IdCategoria "
                + "WHERE tblPlus.idTipo    =             "
                + getIdTipo() + "                        "
                + "ORDER BY 4,3,2                        ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaPluBean fachadaPluBean;

            while (rs.next()) {

                //
                fachadaPluBean = new FachadaPluBean();

                //
                fachadaPluBean.setIdPlu(rs.getInt("idPlu"));
                fachadaPluBean.setNombrePlu(rs.getString("nombrePlu"));
                fachadaPluBean.setNombreCategoria(
                        rs.getString("nombreCategoria"));

                //
                contenedor.add(fachadaPluBean);
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

    // listaPluCategoriaTipo
    public Vector listaPluCategoriaTipo() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        String selectStr =
                "SELECT  tblPlus.idPlu,                "
                + "        tblPlus.nombrePlu,            "
                + "        tblPlus.vrGeneral,            "
                + "        tblPlus.vrMayorista,          "
                + "        tblPlus.porcentajeIva,        "
                + "        tblPlus.idTipo,               "
                + "        tblPlus.idLinea,              "
                + "        tblPlus.idUCompra,            "
                + "        tblPlus.idUVenta,             "
                + "        tblPlus.vrCosto,              "
                + "        tblPlus.idCategoria,          "
                + "        tblPlus.idMarca,              "
                + "        tblPlus.vrSucursal,           "
                + "        tblPlus.factorVenta,          "
                + "        tblPlus.factorDespacho,       "
                + "        tblPlus.estado,               "
                + "        tblPlus.idSeq,                "
                + "        tblPlus.referencia,           "
                + "        tblCategorias.nombreCategoria,"
                + "        tblMarcas.nombreMarca,        "
                + " (SELECT tblPlusInventario.existencia "
                + "  FROM tblPlusInventario              "
                + "  WHERE tblPlusInventario.idPlu   =   "
                + "                       tblPlus.idPlu  "
                + "  AND   tblPlusInventario.idLocal =   "
                + getIdLocal() + "                       "
                + "  AND  tblPlusInventario.idBodega =   "
                + getIdBodega() + "    ) AS existencia   "
                + "FROM tblPlus                          "
                + "INNER JOIN tblMarcas                  "
                + "ON tblPlus.idMarca      =             "
                + "                    tblMarcas.idMarca "
                + "INNER JOIN tblCategorias              "
                + "ON tblPlus.idLinea      =             "
                + "                tblCategorias.idLinea "
                + "AND tblPlus.idCategoria =             "
                + "            tblCategorias.IdCategoria "
                + "WHERE tblPlus.idLinea     =           "
                + getIdLinea() + "                       "
                + "AND   tblPlus.idCategoria =           "
                + getIdCategoria() + "                   "
                + "AND   tblPlus.idTipo    =             "
                + getIdTipo() + "                        "
                + "ORDER BY tblPlus.nombrePlu            ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaPluBean fachadaPluBean;

            while (rs.next()) {

                fachadaPluBean = new FachadaPluBean();

                //
                fachadaPluBean.setIdPlu(rs.getInt("idPlu"));
                fachadaPluBean.setNombrePlu(rs.getString("nombrePlu"));
                fachadaPluBean.setVrGeneral(rs.getDouble("vrGeneral"));
                fachadaPluBean.setVrMayorista(rs.getDouble("vrMayorista"));
                fachadaPluBean.setPorcentajeIva(rs.getDouble("porcentajeIva"));
                fachadaPluBean.setIdTipo(rs.getInt("idTipo"));
                fachadaPluBean.setIdLinea(rs.getInt("idLinea"));
                fachadaPluBean.setIdUCompra(rs.getInt("idUCompra"));
                fachadaPluBean.setIdUVenta(rs.getInt("idUVenta"));
                fachadaPluBean.setVrCosto(rs.getDouble("vrCosto"));
                fachadaPluBean.setIdCategoria(rs.getInt("idCategoria"));
                fachadaPluBean.setIdMarca(rs.getInt("idMarca"));
                fachadaPluBean.setVrSucursal(rs.getDouble("vrSucursal"));
                fachadaPluBean.setFactorVenta(rs.getDouble("factorVenta"));
                fachadaPluBean.setFactorDespacho(
                        rs.getInt("factorDespacho"));
                fachadaPluBean.setEstado(rs.getInt("estado"));
                fachadaPluBean.setIdSeq(rs.getInt("idSeq"));
                fachadaPluBean.setReferencia(rs.getString("referencia"));
                fachadaPluBean.setNombreCategoria(
                        rs.getString("nombreCategoria"));
                fachadaPluBean.setNombreMarca(rs.getString("nombreMarca"));
                fachadaPluBean.setExistencia(rs.getDouble("existencia"));

                //
                contenedor.add(fachadaPluBean);
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
    
    // listaPluActivo
    public ArrayList listaPluActivo() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        ArrayList contenedor = new ArrayList();

        Connection connection = null;

        String selectStr =
                
                  " SELECT tmp.*                          "
                + " FROM                                  "
                + " (                                     "
                + " SELECT  tblPlus.idPlu,                "
                + "        tblPlus.nombrePlu,             "
                + "        tblPlus.vrGeneral,             "
                + "        tblPlus.vrMayorista,           "
                + "        tblPlus.porcentajeIva,         "
                + "        tblPlus.idTipo,                "
                + "        tblPlus.idLinea,               "
                + "        tblPlus.idUCompra,             "
                + "        tblPlus.idUVenta,              "
                + "        tblPlus.vrCosto,               "
                + "        tblPlus.vrCostoIND,            "
                + "        tblPlus.idCategoria,           "
                + "        tblPlus.idMarca,               "
                + "        tblPlus.vrSucursal,            "
                + "        tblPlus.factorVenta,           "
                + "        tblPlus.factorDespacho,        "
                + "        tblPlus.estado,                "
                + "        tblPlus.idSeq,                 "
                + "        tblPlus.referencia,            "
                + "        tblCategorias.nombreCategoria, "
                + "        tblMarcas.nombreMarca,         "
         /*    + "  (SELECT tblPlusInventario.existencia "
                + "   FROM tblPlusInventario              "
                + "   WHERE tblPlusInventario.idPlu   =   "
                + "                        tblPlus.idPlu  "
                + "   AND   tblPlusInventario.idLocal =   "
                + getIdLocal() + "                        "
                + "   AND  tblPlusInventario.idBodega =   "
                + getIdBodega() + "     ) AS existencia   "
         */
		+ "  (  CASE                                                "        
		+ "     WHEN tblPlus.idTipo = 1                             "
		+ "     THEN                                                "
		+ "	(SELECT tblPlusInventario.existencia                "
		+ "	 FROM tblPlusInventario                             "
		+ "      WHERE tblPlusInventario.idPlu   =                  "
		+ "                             tblPlus.idPlu               "
		+ "	 AND   tblPlusInventario.idLocal =                  "
                + getIdLocal() + "                        "                
		+ "	 AND  tblPlusInventario.idBodega = "
                + getIdBodega() + "                         "
                + " )              "    
		+ "	 ELSE                                               "    
		+ "	(SELECT tblPlusInventario.existencia                "
		+ "	 FROM tblPlusInventario                             "
		+ "	 INNER JOIN tblPlusCombo                            "
		+ "	 ON tblPlusInventario.idPlu                         "
		+ "                 = tblPlusCombo.idPlu                    "
		+ "	 WHERE tblPlusCombo.idPluCombo   =                  "
		+ "                                 tblPlus.idPlu           "
		+ "	 AND   tblPlusInventario.idLocal =                "
                + getIdLocal() + "                        "                
		+ "	 AND  tblPlusInventario.idBodega = "
                + getIdBodega() + "                                     "
                + "  )             "
		+ "	 END                                                "
		+ "	 ) As existencia                                 "
                + "FROM tblPlus                           "
                + "INNER JOIN tblMarcas                   "
                + "ON tblPlus.idMarca      =              "
                + "                    tblMarcas.idMarca  "
                + "INNER JOIN tblCategorias               "
                + "ON tblPlus.idLinea      =              "
                + "                tblCategorias.idLinea  "
                + "AND tblPlus.idCategoria =              "
                + "            tblCategorias.IdCategoria  "
                + "WHERE tblPlus.idPlu =                  "
                + getIdPlu() + "                            "
                + "AND tblPlus.idTipo =  1                 "
                + " ) As tmp                                "
//                + " WHERE tmp.estado = 1                    "
		+ " ORDER BY tmp.nombreMarca,               "
                + "          tmp.nombrePlu                  ";
        
        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaPluBean fachadaPluBean;

            while (rs.next()) {

                fachadaPluBean = new FachadaPluBean();

                //
                fachadaPluBean.setIdPlu(rs.getInt("idPlu"));
                fachadaPluBean.setNombrePlu(rs.getString("nombrePlu"));
                fachadaPluBean.setVrGeneral(rs.getDouble("vrGeneral"));
                fachadaPluBean.setVrMayorista(rs.getDouble("vrMayorista"));
                fachadaPluBean.setPorcentajeIva(rs.getDouble("porcentajeIva"));
                fachadaPluBean.setIdTipo(rs.getInt("idTipo"));
                fachadaPluBean.setIdLinea(rs.getInt("idLinea"));
                fachadaPluBean.setIdUCompra(rs.getInt("idUCompra"));
                fachadaPluBean.setIdUVenta(rs.getInt("idUVenta"));
                fachadaPluBean.setVrCosto(rs.getDouble("vrCosto"));
                fachadaPluBean.setVrCostoIND(rs.getDouble("vrCostoIND"));
                fachadaPluBean.setIdCategoria(rs.getInt("idCategoria"));
                fachadaPluBean.setIdMarca(rs.getInt("idMarca"));
                fachadaPluBean.setVrSucursal(rs.getDouble("vrSucursal"));
                fachadaPluBean.setFactorVenta(rs.getDouble("factorVenta"));
                fachadaPluBean.setFactorDespacho(
                        rs.getInt("factorDespacho"));
                fachadaPluBean.setEstado(rs.getInt("estado"));
                fachadaPluBean.setIdSeq(rs.getInt("idSeq"));
                fachadaPluBean.setReferencia(rs.getString("referencia"));
                fachadaPluBean.setNombreCategoria(
                        rs.getString("nombreCategoria"));
                fachadaPluBean.setNombreMarca(rs.getString("nombreMarca"));
                fachadaPluBean.setExistencia(rs.getDouble("existencia"));

                //
                contenedor.add(fachadaPluBean);
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
    
    // listaNombreCategoria
    public ArrayList listaNombrePluCategoriaActivo(String xNombreCategoria,
            String xNombrePlu) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        ArrayList contenedor = new ArrayList();

        Connection connection = null;

        String selectStr =
                  " SELECT tmp.*                          "
                + " FROM                                  "
                + " (                                     "
                + "SELECT  tblPlus.idPlu,                "
                + "        tblPlus.nombrePlu,            "
                + "        tblPlus.vrGeneral,            "
                + "        tblPlus.vrMayorista,          "
                + "        tblPlus.porcentajeIva,        "
                + "        tblPlus.idTipo,               "
                + "        tblPlus.idLinea,              "
                + "        tblPlus.idUCompra,            "
                + "        tblPlus.idUVenta,             "
                + "        tblPlus.vrCosto,              "
                + "        tblPlus.idCategoria,          "
                + "        tblPlus.idMarca,              "
                + "        tblPlus.vrSucursal,           "
                + "        tblPlus.factorVenta,          "
                + "        tblPlus.factorDespacho,       "
                + "        tblPlus.estado,               "
                + "        tblPlus.idSeq,                "
                + "        tblPlus.referencia,           "
                + "        tblCategorias.nombreCategoria,"
                + "        tblMarcas.nombreMarca,        "
		+ "  (  CASE                                                "        
		+ "     WHEN tblPlus.idTipo = 1                             "
		+ "     THEN                                                "
		+ "	(SELECT tblPlusInventario.existencia                "
		+ "	 FROM tblPlusInventario                             "
		+ "      WHERE tblPlusInventario.idPlu   =                  "
		+ "                             tblPlus.idPlu               "
		+ "	 AND   tblPlusInventario.idLocal =                  "
                + getIdLocal() + "                        "                
		+ "	 AND  tblPlusInventario.idBodega = "
                + getIdBodega() + "                                         "
                + " )    "    
		+ "	 ELSE                                               "    
		+ "	(SELECT tblPlusInventario.existencia                "
		+ "	 FROM tblPlusInventario                             "
		+ "	 INNER JOIN tblPlusCombo                            "
		+ "	 ON tblPlusInventario.idPlu                         "
		+ "                 = tblPlusCombo.idPlu                    "
		+ "	 WHERE tblPlusCombo.idPluCombo   =                  "
		+ "                                 tblPlus.idPlu           "
		+ "	 AND   tblPlusInventario.idLocal =                  "
                + getIdLocal() + "                        "                
		+ "	 AND  tblPlusInventario.idBodega = "
                + getIdBodega() + "                                         "
                + "  )             "
		+ "	 END                                                "
		+ "	 ) As existencia                                    "
                + "FROM tblPlus                          "
                + "INNER JOIN tblMarcas                  "
                + "ON tblPlus.idMarca      =             "
                + "                    tblMarcas.idMarca "
                + "INNER JOIN tblCategorias              "
                + "ON tblPlus.idLinea      =             "
                + "                tblCategorias.idLinea "
                + "AND tblPlus.idCategoria =             "
                + "            tblCategorias.IdCategoria "
                + "WHERE tblCategorias.nombreCategoria   "
                + "      LIKE (" + xNombreCategoria + ") "
                + "AND   tblPlus.nombrePlu               "
                + "       LIKE (" + xNombrePlu + ")      "
                + "AND   tblPlus.idTipo = 1               "
                + " ) As tmp                                "
                + " WHERE tmp.estado = 1                    "
                + " ORDER BY tmp.nombreMarca,               "
                + "          tmp.nombrePlu                  ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaPluBean fachadaPluBean;

            while (rs.next()) {

                fachadaPluBean = new FachadaPluBean();

                //
                fachadaPluBean.setIdPlu(rs.getInt("idPlu"));
                fachadaPluBean.setNombrePlu(rs.getString("nombrePlu"));
                fachadaPluBean.setVrGeneral(rs.getDouble("vrGeneral"));
                fachadaPluBean.setVrMayorista(rs.getDouble("vrMayorista"));
                fachadaPluBean.setPorcentajeIva(rs.getDouble("porcentajeIva"));
                fachadaPluBean.setIdTipo(rs.getInt("idTipo"));
                fachadaPluBean.setIdLinea(rs.getInt("idLinea"));
                fachadaPluBean.setIdUCompra(rs.getInt("idUCompra"));
                fachadaPluBean.setIdUVenta(rs.getInt("idUVenta"));
                fachadaPluBean.setVrCosto(rs.getDouble("vrCosto"));
                fachadaPluBean.setIdCategoria(rs.getInt("idCategoria"));
                fachadaPluBean.setIdMarca(rs.getInt("idMarca"));
                fachadaPluBean.setVrSucursal(rs.getDouble("vrSucursal"));
                fachadaPluBean.setFactorVenta(rs.getDouble("factorVenta"));
                fachadaPluBean.setFactorDespacho(
                        rs.getInt("factorDespacho"));
                fachadaPluBean.setEstado(rs.getInt("estado"));
                fachadaPluBean.setIdSeq(rs.getInt("idSeq"));
                fachadaPluBean.setReferencia(rs.getString("referencia"));
                fachadaPluBean.setNombreCategoria(
                        rs.getString("nombreCategoria"));
                fachadaPluBean.setNombreMarca(rs.getString("nombreMarca"));
                fachadaPluBean.setExistencia(rs.getDouble("existencia"));

                //
                contenedor.add(fachadaPluBean);
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
    
    // listaNombreCategoriaActivo
    public ArrayList listaNombreCategoriaActivo(String xNombreCategoria) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        ArrayList contenedor = new ArrayList();

        Connection connection = null;

        String selectStr =
                  " SELECT tmp.*                          "
                + " FROM                                  "
                + " (                                     "
                + "  SELECT  tblPlus.idPlu,              "
                + "        tblPlus.nombrePlu,            "
                + "        tblPlus.vrGeneral,            "
                + "        tblPlus.vrMayorista,          "
                + "        tblPlus.porcentajeIva,        "
                + "        tblPlus.idTipo,               "
                + "        tblPlus.idLinea,              "
                + "        tblPlus.idUCompra,            "
                + "        tblPlus.idUVenta,             "
                + "        tblPlus.vrCosto,              "
                + "        tblPlus.idCategoria,          "
                + "        tblPlus.idMarca,              "
                + "        tblPlus.vrSucursal,           "
                + "        tblPlus.factorVenta,          "
                + "        tblPlus.factorDespacho,       "
                + "        tblPlus.estado,               "
                + "        tblPlus.idSeq,                "
                + "        tblPlus.referencia,           "
                + "        tblCategorias.nombreCategoria,"
                + "        tblMarcas.nombreMarca,        "
                + " (SELECT tblPlusInventario.existencia "
                + "  FROM tblPlusInventario              "
                + "  WHERE tblPlusInventario.idPlu   =   "
                + "                       tblPlus.idPlu  "
                + "  AND   tblPlusInventario.idLocal =   "
                + getIdLocal() + "                       "
                + "  AND  tblPlusInventario.idBodega =   "
                + getIdBodega() + "    ) AS existencia   "
                + "FROM tblPlus                          "
                + "INNER JOIN tblMarcas                  "
                + "ON tblPlus.idMarca      =             "
                + "                    tblMarcas.idMarca "
                + "INNER JOIN tblCategorias              "
                + "ON tblPlus.idLinea      =             "
                + "                tblCategorias.idLinea "
                + "AND tblPlus.idCategoria =             "
                + "            tblCategorias.IdCategoria "
                + "WHERE tblCategorias.nombreCategoria   "
                + "      LIKE (" + xNombreCategoria + ") "
                + "OR   tblPlus.nombrePlu                "
                + "    LIKE (" + xNombreCategoria + ")   "
                + "AND   tblPlus.idTipo = 1"
                + " ) As tmp                             "
                + " WHERE tmp.estado = 1                 "
                + " ORDER BY tmp.nombreMarca,            "
                + "          tmp.nombrePlu               ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaPluBean fachadaPluBean;

            while (rs.next()) {

                fachadaPluBean = new FachadaPluBean();

                //
                fachadaPluBean.setIdPlu(rs.getInt("idPlu"));
                fachadaPluBean.setNombrePlu(rs.getString("nombrePlu"));
                fachadaPluBean.setVrGeneral(rs.getDouble("vrGeneral"));
                fachadaPluBean.setVrMayorista(rs.getDouble("vrMayorista"));
                fachadaPluBean.setPorcentajeIva(rs.getDouble("porcentajeIva"));
                fachadaPluBean.setIdTipo(rs.getInt("idTipo"));
                fachadaPluBean.setIdLinea(rs.getInt("idLinea"));
                fachadaPluBean.setIdUCompra(rs.getInt("idUCompra"));
                fachadaPluBean.setIdUVenta(rs.getInt("idUVenta"));
                fachadaPluBean.setVrCosto(rs.getDouble("vrCosto"));
                fachadaPluBean.setIdCategoria(rs.getInt("idCategoria"));
                fachadaPluBean.setIdMarca(rs.getInt("idMarca"));
                fachadaPluBean.setVrSucursal(rs.getDouble("vrSucursal"));
                fachadaPluBean.setFactorVenta(rs.getDouble("factorVenta"));
                fachadaPluBean.setFactorDespacho(
                        rs.getInt("factorDespacho"));
                fachadaPluBean.setEstado(rs.getInt("estado"));
                fachadaPluBean.setIdSeq(rs.getInt("idSeq"));
                fachadaPluBean.setReferencia(rs.getString("referencia"));
                fachadaPluBean.setNombreCategoria(
                        rs.getString("nombreCategoria"));
                fachadaPluBean.setNombreMarca(rs.getString("nombreMarca"));
                fachadaPluBean.setExistencia(rs.getDouble("existencia"));

                //
                contenedor.add(fachadaPluBean);

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
}
