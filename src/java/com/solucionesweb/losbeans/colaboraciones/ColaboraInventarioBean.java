package com.solucionesweb.losbeans.colaboraciones;

// Importa la clase helper JDBCAccess que contiene los accesos a la base de datos
import com.solucionesweb.lasayudas.JDBCAccess;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaColaboraInventario;

// Importa los paquetes de java
import java.sql.*;
import java.io.*;
import java.util.*;
import java.io.IOException.*;

// Importa los paquetes de javax
import javax.naming.*;

public class ColaboraInventarioBean
        extends FachadaColaboraInventario implements Serializable {

    // Nombre JNDI del Data Source que esta clase requiere
    private static String DATA_SOURCE_NAME = "CommerceAccess";

    public static String getDATA_SOURCE_NAME() {
        return DATA_SOURCE_NAME;
    }

    public static void setDATA_SOURCE_NAME(String aDATA_SOURCE_NAME) {
        DATA_SOURCE_NAME = aDATA_SOURCE_NAME;
    }

    // Clase helper usada para acceder a la base de datos
    private JDBCAccess jdbcAccess;

    //
    private int   idLocal;
    private int   idPlu;
    private double existencia;
    private String nombreCategoria;
    private String nombreMarca;
    private String nombrePlu;
    private int idMarca;
    private double vrCostoInventario;
    private double vrVentaInventario;
    private double vrCostoInventarioSinIva;
    private double vrCostoIva;
    private double vrTotalImpoconsumo;
    private double vrTotalCostoIND;

    // Metodo constructor por defecto sin parametros
    public ColaboraInventarioBean() {

        // Al instanciar el Bean establece la conexion a la base de datos
        jdbcAccess = new JDBCAccess(getDATA_SOURCE_NAME());
    }

    //
    public ColaboraInventarioBean(int xIdLocal,
                                  int xIdPlu,
                                  double xExistencia,
                                  String xNombreCategoria,
                                  String xNombreMarca,
                                  String xNombrePlu,
                                  double xVrCostoInventario,
                                  double xVrCostoInventarioSinIva,
                                  double xVrCostoIva,
                                  double xVrTotalImpoconsumo,
                                  double xVrTotalCostoIND) {

        this.idLocal = xIdLocal;
        this.idPlu   = xIdPlu;
        this.existencia = xExistencia;
        this.nombreCategoria = xNombreCategoria;
        this.nombreMarca   = xNombreMarca;
        this.nombrePlu = xNombrePlu;
        this.vrCostoInventario   = xVrCostoInventario;
        this.vrCostoInventarioSinIva = xVrCostoInventarioSinIva;
        this.vrCostoIva = xVrCostoIva;
        this.vrTotalImpoconsumo = xVrTotalImpoconsumo;
        this.vrTotalCostoIND = xVrTotalCostoIND;

        jdbcAccess = new JDBCAccess(getDATA_SOURCE_NAME());

    }

    // listaAll
    public Vector listaAll() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        String selectStr =
                "SELECT tblPlusInventario.idLocal,       " +
                "       tblPlusInventario.idPlu,         " +
                "       tblPlusInventario.existencia,    " +
                "       tmPlu.nombreCategoria,           " +
                "       tmPlu.nombreMarca,               " +
                "       tmPlu.nombrePlu,                 " +
                "       (tblPlusInventario.existencia *  " +
                "        tmPlu.vrCosto)                  " +
                "                  AS vrCostoInventario, " +
                "       (tblPlusInventario.existencia *  " +
                "        tmPlu.vrGeneral)                " +
                "                AS vrVentaInventario    " +
                "FROM   tblPlusInventario,               " +
                "( SELECT  tblPlus.idPlu,                " +
                "          tblPlus.nombrePlu,            " +
                "          tblPlus.vrGeneral,            " +
                "          tblPlus.vrMayorista,          " +
                "          tblPlus.porcentajeIva,        " +
                "          tblPlus.idTipo,               " +
                "          tblPlus.idLinea,              " +
                "          tblPlus.idUCompra,            " +
                "          tblPlus.idUVenta,             " +
                "          tblPlus.vrCosto,              " +
                "          tblPlus.idCategoria,          " +
                "          tblPlus.idMarca,              " +
                "          tblPlus.vrSucursal,           " +
                "          tblPlus.factorVenta,          " +
                "          tblPlus.factorDespacho,       " +
                "          tblPlus.estado,               " +
                "          tblPlus.idSeq,                " +
                "          tblPlus.referencia,           " +
                "          tblCategorias.nombreCategoria," +
                "          tblMarcas.nombreMarca         " +
                "  FROM tblPlus                          " +
                "  INNER JOIN tblMarcas                  " +
                "  ON tblPlus.idMarca      =             " +
                "                    tblMarcas.idMarca   " +
                "  INNER JOIN tblCategorias              " +
                "  ON tblPlus.idLinea      =             " +
                "                  tblCategorias.idLinea " +
                "  AND tblPlus.idCategoria =             " +
                "            tblCategorias.IdCategoria ) " +
                "                               AS tmPlu " +
                "  WHERE tblPlusInventario.idLocal=      " +
                getIdLocal() + "                         " +
                "  AND   tblPlusInventario.idPlu  =      " +
                "                          tmPlu.idPlu   " +
                "  AND   tmPlu.idTipo             =      " +
                getIdTipo() + "                          " +
                "  ORDER BY tmPlu.nombreCategoria,       " +
                "           tmPlu.nombrePlu,             " +
                "           tmPlu.nombreMarca            ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = getJdbcAccess().getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaColaboraInventario fachadaBean;

            while (rs.next()) {

                //
                fachadaBean = new FachadaColaboraInventario();

                //
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdPlu(rs.getInt("idPlu"));
                fachadaBean.setExistencia(rs.getDouble("existencia"));
                fachadaBean.setNombreCategoria(rs.getString("nombreCategoria"));
                fachadaBean.setNombreMarca(rs.getString("nombreMarca"));
                fachadaBean.setNombrePlu(rs.getString("nombrePlu"));                
                fachadaBean.setVrCostoInventario(
                                             rs.getDouble("vrCostoInventario"));
                fachadaBean.setVrVentaInventario(
                                             rs.getDouble("vrVentaInventario"));

                //
                contenedor.add(fachadaBean);
            }

            // Cierra el Resultset
            rs.close();
            getJdbcAccess().cleanup(connection, selectStatement, null);

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

    // listaUnaCategoria
    public Vector listaUnaCategoria() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        //
        String selectStr =
                "SELECT tblPlusInventario.idLocal,       " +
                "       tblPlusInventario.idPlu,         " +
                "       tblPlusInventario.existencia,    " +
                "       tmPlu.nombreCategoria,           " +
                "       tmPlu.nombreMarca,               " +
                "       tmPlu.nombrePlu,                 " +
                "    SUM(tblPlusInventario.existencia *  " +
                "          tmPlu.vrCosto )               " +
                "             AS vrCostoInventario,      " +
                "       (tblPlusInventario.existencia *  " +
                "        tmPlu.vrGeneral)                " +
                "                AS vrVentaInventario,   " +
                "    SUM(tblPlusInventario.existencia *  " +
                "         tmPlu.vrImpoconsumo)           " +
                "             AS vrTotalImpoconsumo,     " +
                "    SUM(tblPlusInventario.existencia *  " +
                "         tmPlu.vrCostoIND)              " +
                "            AS vrTotalCostoIND,         " +
                "    SUM(tblPlusInventario.existencia *  " +
                "         tmPlu.vrCostoIND            *  " +
                "        (tmPlu.porcentajeIva / 100) )   " +
                "                        AS VrCostoIva   " +
                "FROM   tblPlusInventario,               " +
                "( SELECT  tblPlus.idPlu,                " +
                "          tblPlus.nombrePlu,            " +
                "          tblPlus.vrGeneral,            " +
                "          tblPlus.vrMayorista,          " +
                "          tblPlus.porcentajeIva,        " +
                "          tblPlus.idTipo,               " +
                "          tblPlus.idLinea,              " +
                "          tblPlus.idUCompra,            " +
                "          tblPlus.idUVenta,             " +
                "          tblPlus.vrCosto,              " +
                "          tblPlus.vrImpoconsumo,        " +
                "          tblPlus.vrCostoIND,           " +
                "          tblPlus.idCategoria,          " +
                "          tblPlus.idMarca,              " +
                "          tblPlus.vrSucursal,           " +
                "          tblPlus.factorVenta,          " +
                "          tblPlus.factorDespacho,       " +
                "          tblPlus.estado,               " +
                "          tblPlus.idSeq,                " +
                "          tblPlus.referencia,           " +
                "          tblCategorias.nombreCategoria," +
                "          tblMarcas.nombreMarca         " +
                "  FROM tblPlus                          " +
                "  INNER JOIN tblMarcas                  " +
                "  ON tblPlus.idMarca      =             " +
                "                    tblMarcas.idMarca   " +
                "  INNER JOIN tblCategorias              " +
                "  ON tblPlus.idLinea      =             " +
                "                  tblCategorias.idLinea " +
                "  AND tblPlus.idCategoria =             " +
                "            tblCategorias.IdCategoria ) " +
                "                               AS tmPlu " +
                "  WHERE tblPlusInventario.idLocal=      " +
                getIdLocal() + "                         " +
                "  AND   tblPlusInventario.idPlu  =      " +
                "                          tmPlu.idPlu   " +
                "  AND tblPlusInventario.idBodega =      " +
                getIdBodega() + "                        " +
                "  AND   tmPlu.idTipo             =      " +
                getIdTipo() + "                          " +
                "  AND   tmPlu.idCategoria        =      " +
                getIdCategoria() + "                     " +
                "  AND   tmPlu.idLinea            =      " +
                getIdLinea() + "                         " +
                " GROUP BY  tblPlusInventario.idLocal,   " +
                "           tblPlusInventario.idPlu,     " +
                "           tblPlusInventario.existencia," +
                "           tmPlu.nombreCategoria,       " +
                "           tmPlu.nombreMarca,           " +
                "           tmPlu.nombrePlu,             " +
                "           tmPlu.vrGeneral,             " +
                "           tmPlu.vrCosto,               " +
                "           tmPlu.vrImpoconsumo,         " +
                "           tmPlu.porcentajeIva          " +
                "  ORDER BY tmPlu.nombreCategoria,       " +
                "           tmPlu.nombrePlu,             " +
                "           tmPlu.nombreMarca            ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = getJdbcAccess().getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaColaboraInventario fachadaBean;

            while (rs.next()) {

                //
                fachadaBean = new FachadaColaboraInventario();

                //
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdPlu(rs.getInt("idPlu"));
                fachadaBean.setExistencia(rs.getDouble("existencia"));
                fachadaBean.setNombreCategoria(rs.getString("nombreCategoria"));
                fachadaBean.setNombreMarca(rs.getString("nombreMarca"));
                fachadaBean.setNombrePlu(rs.getString("nombrePlu"));
                fachadaBean.setVrCostoInventario(
                                             rs.getDouble("vrCostoInventario"));
                fachadaBean.setVrVentaInventario(
                                             rs.getDouble("vrVentaInventario"));
                fachadaBean.setVrTotalImpoconsumo(
                                           rs.getDouble("vrTotalImpoconsumo"));
                fachadaBean.setVrTotalCostoIND(
                                           rs.getDouble("vrTotalCostoIND"));
                fachadaBean.setVrCostoIva(rs.getDouble("vrCostoIva"));

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

    // listaUnaCategoria
    public Vector listaUnaCategoria(int xIdLocal) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        //
        String selectStr =
                "SELECT tblPlusInventario.idLocal,       " +
                "       tblPlusInventario.idPlu,         " +
                "       tblPlusInventario.existencia,    " +
                "       tmPlu.nombreCategoria,           " +
                "       tmPlu.nombreMarca,               " +
                "       tmPlu.nombrePlu,                 " +
                "    SUM(tblPlusInventario.existencia *  " +
                "          tmPlu.vrCosto )               " +
                "             AS vrCostoInventario,      " +
                "       (tblPlusInventario.existencia *  " +
                "        tmPlu.vrGeneral)                " +
                "                AS vrVentaInventario,   " +
                "    SUM(tblPlusInventario.existencia *  " +
                "         tmPlu.vrImpoconsumo)           " +
                "             AS vrTotalImpoconsumo,     " +
                "    SUM(tblPlusInventario.existencia *  " +
                "         tmPlu.vrCostoIND)              " +
                "            AS vrTotalCostoIND,         " +
                "    SUM(tblPlusInventario.existencia *  " +
                "         tmPlu.vrCostoIND            *  " +
                "        (tmPlu.porcentajeIva / 100) )   " +
                "                        AS VrCostoIva   " +
                "FROM   tblPlusInventario,               " +
                "( SELECT  tblPlus.idPlu,                " +
                "          tblPlus.nombrePlu,            " +
                "          tblPlus.vrGeneral,            " +
                "          tblPlus.vrMayorista,          " +
                "          tblPlus.porcentajeIva,        " +
                "          tblPlus.idTipo,               " +
                "          tblPlus.idLinea,              " +
                "          tblPlus.idUCompra,            " +
                "          tblPlus.idUVenta,             " +
                "          tblPlus.vrCosto,              " +
                "          tblPlus.vrImpoconsumo,        " +
                "          tblPlus.vrCostoIND,           " +
                "          tblPlus.idCategoria,          " +
                "          tblPlus.idMarca,              " +
                "          tblPlus.vrSucursal,           " +
                "          tblPlus.factorVenta,          " +
                "          tblPlus.factorDespacho,       " +
                "          tblPlus.estado,               " +
                "          tblPlus.idSeq,                " +
                "          tblPlus.referencia,           " +
                "          tblCategorias.nombreCategoria," +
                "          tblMarcas.nombreMarca         " +
                "  FROM tblPlus                          " +
                "  INNER JOIN tblMarcas                  " +
                "  ON tblPlus.idMarca      =             " +
                "                    tblMarcas.idMarca   " +
                "  INNER JOIN tblCategorias              " +
                "  ON tblPlus.idLinea      =             " +
                "                  tblCategorias.idLinea " +
                "  AND tblPlus.idCategoria =             " +
                "            tblCategorias.IdCategoria ) " +
                "                               AS tmPlu " +
                "  WHERE tblPlusInventario.idLocal=      " +
                xIdLocal + "                             " +
                "  AND   tblPlusInventario.idPlu  =      " +
                "                          tmPlu.idPlu   " +
                "  AND tblPlusInventario.idBodega =      " +
                getIdBodega() + "                        " +
                "  AND   tmPlu.idTipo             =      " +
                getIdTipo() + "                          " +
                "  AND   tmPlu.idCategoria        =      " +
                getIdCategoria() + "                     " +
                "  AND   tmPlu.idLinea            =      " +
                getIdLinea() + "                         " +
                " GROUP BY  tblPlusInventario.idLocal,   " +
                "           tblPlusInventario.idPlu,     " +
                "           tblPlusInventario.existencia," +
                "           tmPlu.nombreCategoria,       " +
                "           tmPlu.nombreMarca,           " +
                "           tmPlu.nombrePlu,             " +
                "           tmPlu.vrGeneral,             " +
                "           tmPlu.vrCosto,               " +
                "           tmPlu.vrImpoconsumo,         " +
                "           tmPlu.porcentajeIva          " +
                "  ORDER BY tmPlu.nombreCategoria,       " +
                "           tmPlu.nombrePlu,             " +
                "           tmPlu.nombreMarca            ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = getJdbcAccess().getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaColaboraInventario fachadaBean;

            while (rs.next()) {

                //
                fachadaBean = new FachadaColaboraInventario();

                //
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdPlu(rs.getInt("idPlu"));
                fachadaBean.setExistencia(rs.getDouble("existencia"));
                fachadaBean.setNombreCategoria(rs.getString("nombreCategoria"));
                fachadaBean.setNombreMarca(rs.getString("nombreMarca"));
                fachadaBean.setNombrePlu(rs.getString("nombrePlu"));
                fachadaBean.setVrCostoInventario(
                                             rs.getDouble("vrCostoInventario"));
                fachadaBean.setVrVentaInventario(
                                             rs.getDouble("vrVentaInventario"));
                fachadaBean.setVrTotalImpoconsumo(
                                           rs.getDouble("vrTotalImpoconsumo"));
                fachadaBean.setVrTotalCostoIND(
                                           rs.getDouble("vrTotalCostoIND"));
                fachadaBean.setVrCostoIva(rs.getDouble("vrCostoIva"));

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

    // listaUnaMarca
    public Vector listaUnaMarca(int xIdLocal) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        //
        String selectStr =
                "SELECT tblPlusInventario.idLocal,       " +
                "       tblPlusInventario.idPlu,         " +
                "       tblPlusInventario.existencia,    " +
                "       tmPlu.nombreCategoria,           " +
                "       tmPlu.nombreMarca,               " +
                "       tmPlu.nombrePlu,                 " +
                "    SUM(tblPlusInventario.existencia *  " +
                "          tmPlu.vrCosto )               " +
                "             AS vrCostoInventario,      " +
                "       (tblPlusInventario.existencia *  " +
                "        tmPlu.vrGeneral)                " +
                "                AS vrVentaInventario,   " +
                "    SUM(tblPlusInventario.existencia *  " +
                "         tmPlu.vrImpoconsumo)           " +
                "             AS vrTotalImpoconsumo,     " +
                "    SUM(tblPlusInventario.existencia *  " +
                "         tmPlu.vrCostoIND)              " +
                "            AS vrTotalCostoIND,         " +
                "    SUM(tblPlusInventario.existencia *  " +
                "         tmPlu.vrCostoIND            *  " +
                "        (tmPlu.porcentajeIva / 100) )   " +
                "                        AS VrCostoIva   " +
                "FROM   tblPlusInventario,               " +
                "( SELECT  tblPlus.idPlu,                " +
                "          tblPlus.nombrePlu,            " +
                "          tblPlus.vrGeneral,            " +
                "          tblPlus.vrMayorista,          " +
                "          tblPlus.porcentajeIva,        " +
                "          tblPlus.idTipo,               " +
                "          tblPlus.idLinea,              " +
                "          tblPlus.idUCompra,            " +
                "          tblPlus.idUVenta,             " +
                "          tblPlus.vrCosto,              " +
                "          tblPlus.vrImpoconsumo,        " +
                "          tblPlus.vrCostoIND,           " +
                "          tblPlus.idCategoria,          " +
                "          tblPlus.idMarca,              " +
                "          tblPlus.vrSucursal,           " +
                "          tblPlus.factorVenta,          " +
                "          tblPlus.factorDespacho,       " +
                "          tblPlus.estado,               " +
                "          tblPlus.idSeq,                " +
                "          tblPlus.referencia,           " +
                "          tblCategorias.nombreCategoria," +
                "          tblMarcas.nombreMarca         " +
                "  FROM tblPlus                          " +
                "  INNER JOIN tblMarcas                  " +
                "  ON tblPlus.idMarca      =             " +
                "                    tblMarcas.idMarca   " +
                "  INNER JOIN tblCategorias              " +
                "  ON tblPlus.idLinea      =             " +
                "                  tblCategorias.idLinea " +
                "  AND tblPlus.idCategoria =             " +
                "            tblCategorias.IdCategoria ) " +
                "                               AS tmPlu " +
                "  WHERE tblPlusInventario.idLocal=      " +
                xIdLocal + "                             " +
                "  AND   tblPlusInventario.idPlu  =      " +
                "                          tmPlu.idPlu   " +
                "  AND tblPlusInventario.idBodega =      " +
                getIdBodega() + "                        " +
                "  AND   tmPlu.idTipo             =      " +
                getIdTipo() + "                          " +
                "  AND   tmPlu.idMarca            =      " +
                getIdMarca() + "                         " +
                " GROUP BY  tblPlusInventario.idLocal,   " +
                "           tblPlusInventario.idPlu,     " +
                "           tblPlusInventario.existencia," +
                "           tmPlu.nombreCategoria,       " +
                "           tmPlu.nombreMarca,           " +
                "           tmPlu.nombrePlu,             " +
                "           tmPlu.vrGeneral,             " +
                "           tmPlu.vrCosto,               " +
                "           tmPlu.vrImpoconsumo,         " +
                "           tmPlu.porcentajeIva          " +
                "  ORDER BY tmPlu.nombreCategoria,       " +
                "           tmPlu.nombrePlu,             " +
                "           tmPlu.nombreMarca            ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = getJdbcAccess().getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaColaboraInventario fachadaBean;

            while (rs.next()) {

                //
                fachadaBean = new FachadaColaboraInventario();

                //
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdPlu(rs.getInt("idPlu"));
                fachadaBean.setExistencia(rs.getDouble("existencia"));
                fachadaBean.setNombreCategoria(rs.getString("nombreCategoria"));
                fachadaBean.setNombreMarca(rs.getString("nombreMarca"));
                fachadaBean.setNombrePlu(rs.getString("nombrePlu"));
                fachadaBean.setVrCostoInventario(
                                             rs.getDouble("vrCostoInventario"));
                fachadaBean.setVrVentaInventario(
                                             rs.getDouble("vrVentaInventario"));
                fachadaBean.setVrTotalImpoconsumo(
                                           rs.getDouble("vrTotalImpoconsumo"));
                fachadaBean.setVrTotalCostoIND(
                                           rs.getDouble("vrTotalCostoIND"));
                fachadaBean.setVrCostoIva(rs.getDouble("vrCostoIva"));

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

    // listaAllCategoria
    public Vector listaAllCategoria() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;


        String selectStr =
                "SELECT tblPlusInventario.idLocal,       " +
                "       tblPlusInventario.idPlu,         " +
                "       tblPlusInventario.existencia,    " +
                "       tmPlu.nombreCategoria,           " +
                "       tmPlu.nombreMarca,               " +
                "       tmPlu.nombrePlu,                 " +
                "    SUM(tblPlusInventario.existencia *  " +
                "          tmPlu.vrCosto )               " +
                "             AS vrCostoInventario,      " +
                "       (tblPlusInventario.existencia *  " +
                "        tmPlu.vrGeneral)                " +
                "                AS vrVentaInventario,   " +
                "    SUM(tblPlusInventario.existencia *  " +
                "         tmPlu.vrImpoconsumo)           " +
                "             AS vrTotalImpoconsumo,     " +
                "    SUM(tblPlusInventario.existencia *  " +
                "         tmPlu.vrCostoIND)              " +
                "            AS vrTotalCostoIND,         " +
                "    SUM(tblPlusInventario.existencia *  " +
                "         tmPlu.vrCostoIND            *  " +
                "        (tmPlu.porcentajeIva / 100) )   " +
                "                        AS VrCostoIva   " +
                "FROM   tblPlusInventario,               " +
                "( SELECT  tblPlus.idPlu,                " +
                "          tblPlus.nombrePlu,            " +
                "          tblPlus.vrGeneral,            " +
                "          tblPlus.vrMayorista,          " +
                "          tblPlus.porcentajeIva,        " +
                "          tblPlus.idTipo,               " +
                "          tblPlus.idLinea,              " +
                "          tblPlus.idUCompra,            " +
                "          tblPlus.idUVenta,             " +
                "          tblPlus.vrCosto,              " +
                "          tblPlus.vrImpoconsumo,        " +
                "          tblPlus.vrCostoIND,           " +
                "          tblPlus.idCategoria,          " +
                "          tblPlus.idMarca,              " +
                "          tblPlus.vrSucursal,           " +
                "          tblPlus.factorVenta,          " +
                "          tblPlus.factorDespacho,       " +
                "          tblPlus.estado,               " +
                "          tblPlus.idSeq,                " +
                "          tblPlus.referencia,           " +
                "          tblCategorias.nombreCategoria," +
                "          tblMarcas.nombreMarca         " +
                "  FROM tblPlus                          " +
                "  INNER JOIN tblMarcas                  " +
                "  ON tblPlus.idMarca      =             " +
                "                    tblMarcas.idMarca   " +
                "  INNER JOIN tblCategorias              " +
                "  ON tblPlus.idLinea      =             " +
                "                  tblCategorias.idLinea " +
                "  AND tblPlus.idCategoria =             " +
                "            tblCategorias.IdCategoria ) " +
                "                               AS tmPlu " +
                "  WHERE tblPlusInventario.idLocal=      " +
                getIdLocal() + "                         " +
                "  AND   tblPlusInventario.idPlu  =      " +
                "                          tmPlu.idPlu   "
                + " AND tblPlusInventario.idPlu != 209   " +
                "  AND tblPlusInventario.idBodega =      " +
                getIdBodega() + "                        " +
                "  AND   tmPlu.idTipo             =      " +
                getIdTipo() + "                          " +
                " GROUP BY  tblPlusInventario.idLocal,   " +
                "           tblPlusInventario.idPlu,     " +
                "           tblPlusInventario.existencia," +
                "           tmPlu.nombreCategoria,       " +
                "           tmPlu.nombreMarca,           " +
                "           tmPlu.nombrePlu,             " +
                "           tmPlu.vrGeneral,             " +
                "           tmPlu.vrCosto,               " +
                "           tmPlu.vrImpoconsumo,         " +
                "           tmPlu.porcentajeIva          " +
                "  ORDER BY tmPlu.nombreCategoria,       " +
                "           tmPlu.nombrePlu,             " +
                "           tmPlu.nombreMarca            " ;

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = getJdbcAccess().getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaColaboraInventario fachadaBean;

            while (rs.next()) {

                //
                fachadaBean = new FachadaColaboraInventario();

                //
                fachadaBean = new FachadaColaboraInventario();

                //
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdPlu(rs.getInt("idPlu"));
                fachadaBean.setExistencia(rs.getDouble("existencia"));
                fachadaBean.setNombreCategoria(rs.getString("nombreCategoria"));
                fachadaBean.setNombreMarca(rs.getString("nombreMarca"));
                fachadaBean.setNombrePlu(rs.getString("nombrePlu"));
                fachadaBean.setVrCostoInventario(
                                             rs.getDouble("vrCostoInventario"));
                fachadaBean.setVrVentaInventario(
                                             rs.getDouble("vrVentaInventario"));
                fachadaBean.setVrTotalImpoconsumo(
                                           rs.getDouble("vrTotalImpoconsumo"));
                fachadaBean.setVrTotalCostoIND(
                                           rs.getDouble("vrTotalCostoIND"));
                fachadaBean.setVrCostoIva(rs.getDouble("vrCostoIva"));

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

    // listaAllCategoria
    public Vector listaAllCategoria(int xIdLocal) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;


        String selectStr =
                "SELECT tblPlusInventario.idLocal,       " +
                "       tblPlusInventario.idPlu,         " +
                "       tblPlusInventario.existencia,    " +
                "       tmPlu.nombreCategoria,           " +
                "       tmPlu.nombreMarca,               " +
                "       tmPlu.nombrePlu,                 " +
                "    SUM(tblPlusInventario.existencia *  " +
                "          tmPlu.vrCosto )               " +
                "             AS vrCostoInventario,      " +
                "       (tblPlusInventario.existencia *  " +
                "        tmPlu.vrGeneral)                " +
                "                AS vrVentaInventario,   " +
                "    SUM(tblPlusInventario.existencia *  " +
                "         tmPlu.vrImpoconsumo)           " +
                "             AS vrTotalImpoconsumo,     " +
                "    SUM(tblPlusInventario.existencia *  " +
                "         tmPlu.vrCostoIND)              " +
                "            AS vrTotalCostoIND,         " +
                "    SUM(tblPlusInventario.existencia *  " +
                "         tmPlu.vrCostoIND            *  " +
                "        (tmPlu.porcentajeIva / 100) )   " +
                "                        AS VrCostoIva   " +
                "FROM   tblPlusInventario,               " +
                "( SELECT  tblPlus.idPlu,                " +
                "          tblPlus.nombrePlu,            " +
                "          tblPlus.vrGeneral,            " +
                "          tblPlus.vrMayorista,          " +
                "          tblPlus.porcentajeIva,        " +
                "          tblPlus.idTipo,               " +
                "          tblPlus.idLinea,              " +
                "          tblPlus.idUCompra,            " +
                "          tblPlus.idUVenta,             " +
                "          tblPlus.vrCosto,              " +
                "          tblPlus.vrImpoconsumo,        " +
                "          tblPlus.vrCostoIND,           " +
                "          tblPlus.idCategoria,          " +
                "          tblPlus.idMarca,              " +
                "          tblPlus.vrSucursal,           " +
                "          tblPlus.factorVenta,          " +
                "          tblPlus.factorDespacho,       " +
                "          tblPlus.estado,               " +
                "          tblPlus.idSeq,                " +
                "          tblPlus.referencia,           " +
                "          tblCategorias.nombreCategoria," +
                "          tblMarcas.nombreMarca         " +
                "  FROM tblPlus                          " +
                "  INNER JOIN tblMarcas                  " +
                "  ON tblPlus.idMarca      =             " +
                "                    tblMarcas.idMarca   " +
                "  INNER JOIN tblCategorias              " +
                "  ON tblPlus.idLinea      =             " +
                "                  tblCategorias.idLinea " +
                "  AND tblPlus.idCategoria =             " +
                "            tblCategorias.IdCategoria ) " +
                "                               AS tmPlu " +
                "  WHERE tblPlusInventario.idLocal=      " +
                xIdLocal + "                             " +
                "  AND   tblPlusInventario.idPlu  =      " +
                "                          tmPlu.idPlu   " +
                "  AND tblPlusInventario.idBodega =      " +
                getIdBodega() + "                        " +
                "  AND   tmPlu.idTipo             =      " +
                getIdTipo() + "                          " +
                " GROUP BY  tblPlusInventario.idLocal,   " +
                "           tblPlusInventario.idPlu,     " +
                "           tblPlusInventario.existencia," +
                "           tmPlu.nombreCategoria,       " +
                "           tmPlu.nombreMarca,           " +
                "           tmPlu.nombrePlu,             " +
                "           tmPlu.vrGeneral,             " +
                "           tmPlu.vrCosto,               " +
                "           tmPlu.vrImpoconsumo,         " +
                "           tmPlu.porcentajeIva          " +
                "  ORDER BY tmPlu.nombreCategoria,       " +
                "           tmPlu.nombrePlu,             " +
                "           tmPlu.nombreMarca            ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = getJdbcAccess().getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaColaboraInventario fachadaBean;

            while (rs.next()) {

                //
                fachadaBean = new FachadaColaboraInventario();

                //
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdPlu(rs.getInt("idPlu"));
                fachadaBean.setExistencia(rs.getDouble("existencia"));
                fachadaBean.setNombreCategoria(rs.getString("nombreCategoria"));
                fachadaBean.setNombreMarca(rs.getString("nombreMarca"));
                fachadaBean.setNombrePlu(rs.getString("nombrePlu"));
                fachadaBean.setVrCostoInventario(
                                             rs.getDouble("vrCostoInventario"));
                fachadaBean.setVrVentaInventario(
                                             rs.getDouble("vrVentaInventario"));
                fachadaBean.setVrTotalImpoconsumo(
                                           rs.getDouble("vrTotalImpoconsumo"));
                fachadaBean.setVrTotalCostoIND(
                                           rs.getDouble("vrTotalCostoIND"));
                fachadaBean.setVrCostoIva(rs.getDouble("vrCostoIva"));

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

    // listaAllMarca
    public Vector listaAllMarca(int xIdLocal) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;


        String selectStr =
                "SELECT tblPlusInventario.idLocal,       " +
                "       tblPlusInventario.idPlu,         " +
                "       tblPlusInventario.existencia,    " +
                "       tmPlu.nombreCategoria,           " +
                "       tmPlu.nombreMarca,               " +
                "       tmPlu.nombrePlu,                 " +
                "    SUM(tblPlusInventario.existencia *  " +
                "          tmPlu.vrCosto )               " +
                "             AS vrCostoInventario,      " +
                "       (tblPlusInventario.existencia *  " +
                "        tmPlu.vrGeneral)                " +
                "                AS vrVentaInventario,   " +
                "    SUM(tblPlusInventario.existencia *  " +
                "         tmPlu.vrImpoconsumo)           " +
                "             AS vrTotalImpoconsumo,     " +
                "    SUM(tblPlusInventario.existencia *  " +
                "         tmPlu.vrCostoIND)              " +
                "            AS vrTotalCostoIND,         " +
                "    SUM(tblPlusInventario.existencia *  " +
                "         tmPlu.vrCostoIND            *  " +
                "        (tmPlu.porcentajeIva / 100) )   " +
                "                        AS VrCostoIva   " +
                "FROM   tblPlusInventario,               " +
                "( SELECT  tblPlus.idPlu,                " +
                "          tblPlus.nombrePlu,            " +
                "          tblPlus.vrGeneral,            " +
                "          tblPlus.vrMayorista,          " +
                "          tblPlus.porcentajeIva,        " +
                "          tblPlus.idTipo,               " +
                "          tblPlus.idLinea,              " +
                "          tblPlus.idUCompra,            " +
                "          tblPlus.idUVenta,             " +
                "          tblPlus.vrCosto,              " +
                "          tblPlus.vrImpoconsumo,        " +
                "          tblPlus.vrCostoIND,           " +
                "          tblPlus.idCategoria,          " +
                "          tblPlus.idMarca,              " +
                "          tblPlus.vrSucursal,           " +
                "          tblPlus.factorVenta,          " +
                "          tblPlus.factorDespacho,       " +
                "          tblPlus.estado,               " +
                "          tblPlus.idSeq,                " +
                "          tblPlus.referencia,           " +
                "          tblCategorias.nombreCategoria," +
                "          tblMarcas.nombreMarca         " +
                "  FROM tblPlus                          " +
                "  INNER JOIN tblMarcas                  " +
                "  ON tblPlus.idMarca      =             " +
                "                    tblMarcas.idMarca   " +
                "  INNER JOIN tblCategorias              " +
                "  ON tblPlus.idLinea      =             " +
                "                  tblCategorias.idLinea " +
                "  AND tblPlus.idCategoria =             " +
                "            tblCategorias.IdCategoria ) " +
                "                               AS tmPlu " +
                "  WHERE tblPlusInventario.idLocal=      " +
                xIdLocal + "                             " +
                "  AND   tblPlusInventario.idPlu  =      " +
                "                          tmPlu.idPlu   " +
                "  AND tblPlusInventario.idBodega =      " +
                getIdBodega() + "                        " +
                "  AND   tmPlu.idTipo             =      " +
                getIdTipo() + "                          " +
                " GROUP BY  tblPlusInventario.idLocal,   " +
                "           tblPlusInventario.idPlu,     " +
                "           tblPlusInventario.existencia," +
                "           tmPlu.nombreCategoria,       " +
                "           tmPlu.nombreMarca,           " +
                "           tmPlu.nombrePlu,             " +
                "           tmPlu.vrGeneral,             " +
                "           tmPlu.vrCosto,               " +
                "           tmPlu.vrImpoconsumo,         " +
                "           tmPlu.porcentajeIva          " +
                "  ORDER BY tmPlu.nombreCategoria,       " +
                "           tmPlu.nombrePlu,             " +
                "           tmPlu.nombreMarca            ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = getJdbcAccess().getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaColaboraInventario fachadaBean;

            while (rs.next()) {

                //
                fachadaBean = new FachadaColaboraInventario();

                //
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdPlu(rs.getInt("idPlu"));
                fachadaBean.setExistencia(rs.getDouble("existencia"));
                fachadaBean.setNombreCategoria(rs.getString("nombreCategoria"));
                fachadaBean.setNombreMarca(rs.getString("nombreMarca"));
                fachadaBean.setNombrePlu(rs.getString("nombrePlu"));
                fachadaBean.setVrCostoInventario(
                                             rs.getDouble("vrCostoInventario"));
                fachadaBean.setVrVentaInventario(
                                             rs.getDouble("vrVentaInventario"));
                fachadaBean.setVrTotalImpoconsumo(
                                           rs.getDouble("vrTotalImpoconsumo"));
                fachadaBean.setVrTotalCostoIND(
                                           rs.getDouble("vrTotalCostoIND"));
                fachadaBean.setVrCostoIva(rs.getDouble("vrCostoIva"));

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

    // listaAllMarca
    public Collection listaAllMarca() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        
        //
        Collection list = new ArrayList();

        Connection connection = null;

        String selectStr =
                "SELECT tblPlusInventario.idLocal,       " +
                "       tblPlusInventario.idPlu,         " +
                "       tblPlusInventario.existencia,    " +
                "       tmPlu.nombreCategoria,           " +
                "       tmPlu.nombreMarca,               " +
                "       tmPlu.nombrePlu,                 " +
                "    SUM(tblPlusInventario.existencia *  " +
                "          tmPlu.vrCosto )               " +
                "             AS vrCostoInventario,      " +
                "       (tblPlusInventario.existencia *  " +
                "        tmPlu.vrGeneral)                " +
                "                AS vrVentaInventario,   " +
                "    SUM(tblPlusInventario.existencia *  " +
                "         tmPlu.vrImpoconsumo)           " +
                "             AS vrTotalImpoconsumo,     " +
                "    SUM(tblPlusInventario.existencia *  " +
                "         tmPlu.vrCostoIND)              " +
                "            AS vrTotalCostoIND,         " +
                "    SUM(tblPlusInventario.existencia *  " +
                "         tmPlu.vrCostoIND            *  " +
                "        (tmPlu.porcentajeIva / 100) )   " +
                "                        AS VrCostoIva   " +
                "FROM   tblPlusInventario,               " +
                "( SELECT  tblPlus.idPlu,                " +
                "          tblPlus.nombrePlu,            " +
                "          tblPlus.vrGeneral,            " +
                "          tblPlus.vrMayorista,          " +
                "          tblPlus.porcentajeIva,        " +
                "          tblPlus.idTipo,               " +
                "          tblPlus.idLinea,              " +
                "          tblPlus.idUCompra,            " +
                "          tblPlus.idUVenta,             " +
                "          tblPlus.vrCosto,              " +
                "          tblPlus.vrImpoconsumo,        " +
                "          tblPlus.vrCostoIND,           " +
                "          tblPlus.idCategoria,          " +
                "          tblPlus.idMarca,              " +
                "          tblPlus.vrSucursal,           " +
                "          tblPlus.factorVenta,          " +
                "          tblPlus.factorDespacho,       " +
                "          tblPlus.estado,               " +
                "          tblPlus.idSeq,                " +
                "          tblPlus.referencia,           " +
                "          tblCategorias.nombreCategoria," +
                "          tblMarcas.nombreMarca         " +
                "  FROM tblPlus                          " +
                "  INNER JOIN tblMarcas                  " +
                "  ON tblPlus.idMarca      =             " +
                "                    tblMarcas.idMarca   " +
                "  INNER JOIN tblCategorias              " +
                "  ON tblPlus.idLinea      =             " +
                "                  tblCategorias.idLinea " +
                "  AND tblPlus.idCategoria =             " +
                "            tblCategorias.IdCategoria ) " +
                "                               AS tmPlu " +
                "  WHERE tblPlusInventario.idLocal=      " +
                getIdLocal() + "                         " +
                "  AND   tblPlusInventario.idPlu  =      " +
                "                          tmPlu.idPlu   " +
                "  AND tblPlusInventario.idBodega =      " +
                getIdBodega() + "                        " +
                "  AND   tmPlu.idTipo             =      " +
                getIdTipo() + "                          " +
                " GROUP BY  tblPlusInventario.idLocal,   " +
                "           tblPlusInventario.idPlu,     " +
                "           tblPlusInventario.existencia," +
                "           tmPlu.nombreCategoria,       " +
                "           tmPlu.nombreMarca,           " +
                "           tmPlu.nombrePlu,             " +
                "           tmPlu.vrGeneral,             " +
                "           tmPlu.vrCosto,               " +
                "           tmPlu.vrImpoconsumo,         " +
                "           tmPlu.porcentajeIva          " +
                "  ORDER BY tmPlu.nombreMarca,           " +
                "           tmPlu.nombreCategoria,       " +
                "           tmPlu.nombrePlu";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = getJdbcAccess().getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaColaboraInventario fachadaBean;

            while (rs.next()) {

                //
                fachadaBean = new FachadaColaboraInventario();

                //
                fachadaBean = new FachadaColaboraInventario();

                //
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdPlu(rs.getInt("idPlu"));
                fachadaBean.setExistencia(rs.getDouble("existencia"));
                fachadaBean.setNombreCategoria(rs.getString("nombreCategoria"));
                fachadaBean.setNombreMarca(rs.getString("nombreMarca"));
                fachadaBean.setNombrePlu(rs.getString("nombrePlu"));
                fachadaBean.setVrCostoInventario(
                                             rs.getDouble("vrCostoInventario"));
                fachadaBean.setVrVentaInventario(
                                             rs.getDouble("vrVentaInventario"));
                fachadaBean.setVrCostoIva(rs.getDouble("vrCostoIva"));
                fachadaBean.setVrTotalImpoconsumo(
                                           rs.getDouble("vrTotalImpoconsumo"));
                fachadaBean.setVrTotalCostoIND(
                                           rs.getDouble("vrTotalCostoIND"));

                //
                list.add(new ColaboraInventarioBean(fachadaBean.getIdLocal(),
                                                    fachadaBean.getIdPlu(),
                                                    fachadaBean.getExistencia(),
                                               fachadaBean.getNombreCategoria(),
                                                   fachadaBean.getNombreMarca(),
                                                     fachadaBean.getNombrePlu(),
                                             fachadaBean.getVrCostoInventario(),
                                       fachadaBean.getVrCostoInventarioSinIva(),
                                                    fachadaBean.getVrCostoIva(),
                                            fachadaBean.getVrTotalImpoconsumo(),
                                             fachadaBean.getVrTotalCostoIND()));

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
            return list;

        }
    }

    // listaUnMarca
    public Collection listaUnMarca() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        //
        Collection list = new ArrayList();

        Connection connection = null;

        String selectStr =
                "SELECT tblPlusInventario.idLocal,       " +
                "       tblPlusInventario.idPlu,         " +
                "       tblPlusInventario.existencia,    " +
                "       tmPlu.nombreCategoria,           " +
                "       tmPlu.nombreMarca,               " +
                "       tmPlu.nombrePlu,                 " +
                "    SUM(tblPlusInventario.existencia *  " +
                "          tmPlu.vrCosto )               " +
                "             AS vrCostoInventario,      " +
                "       (tblPlusInventario.existencia *  " +
                "        tmPlu.vrGeneral)                " +
                "                AS vrVentaInventario,   " +
                "    SUM(tblPlusInventario.existencia *  " +
                "         tmPlu.vrImpoconsumo)           " +
                "             AS vrTotalImpoconsumo,     " +
                "    SUM(tblPlusInventario.existencia *  " +
                "         tmPlu.vrCostoIND)              " +
                "            AS vrTotalCostoIND,         " +
                "    SUM(tblPlusInventario.existencia *  " +
                "         tmPlu.vrCostoIND            *  " +
                "        (tmPlu.porcentajeIva / 100) )   " +
                "                        AS VrCostoIva   " +
                "FROM   tblPlusInventario,               " +
                "( SELECT  tblPlus.idPlu,                " +
                "          tblPlus.nombrePlu,            " +
                "          tblPlus.vrGeneral,            " +
                "          tblPlus.vrMayorista,          " +
                "          tblPlus.porcentajeIva,        " +
                "          tblPlus.idTipo,               " +
                "          tblPlus.idLinea,              " +
                "          tblPlus.idUCompra,            " +
                "          tblPlus.idUVenta,             " +
                "          tblPlus.vrCosto,              " +
                "          tblPlus.vrImpoconsumo,        " +
                "          tblPlus.vrCostoIND,           " +
                "          tblPlus.idCategoria,          " +
                "          tblPlus.idMarca,              " +
                "          tblPlus.vrSucursal,           " +
                "          tblPlus.factorVenta,          " +
                "          tblPlus.factorDespacho,       " +
                "          tblPlus.estado,               " +
                "          tblPlus.idSeq,                " +
                "          tblPlus.referencia,           " +
                "          tblCategorias.nombreCategoria," +
                "          tblMarcas.nombreMarca         " +
                "  FROM tblPlus                          " +
                "  INNER JOIN tblMarcas                  " +
                "  ON tblPlus.idMarca      =             " +
                "                    tblMarcas.idMarca   " +
                "  INNER JOIN tblCategorias              " +
                "  ON tblPlus.idLinea      =             " +
                "                  tblCategorias.idLinea " +
                "  AND tblPlus.idCategoria =             " +
                "            tblCategorias.IdCategoria ) " +
                "                               AS tmPlu " +
                "  WHERE tblPlusInventario.idLocal=      " +
                getIdLocal() + "                         " +
                "  AND   tblPlusInventario.idPlu  =      " +
                "                          tmPlu.idPlu   " +
                "  AND tblPlusInventario.idBodega =      " +
                getIdBodega() + "                        " +
                "  AND   tmPlu.idTipo             =      " +
                getIdTipo() + "                          " +
                "AND   tmPlu.idMarca            =        " +
                getIdMarca() + "                         " +
                " GROUP BY  tblPlusInventario.idLocal,   " +
                "           tblPlusInventario.idPlu,     " +
                "           tblPlusInventario.existencia," +
                "           tmPlu.nombreCategoria,       " +
                "           tmPlu.nombreMarca,           " +
                "           tmPlu.nombrePlu,             " +
                "           tmPlu.vrGeneral,             " +
                "           tmPlu.vrCosto,               " +
                "           tmPlu.vrImpoconsumo,         " +
                "           tmPlu.porcentajeIva          " +
                "  ORDER BY tmPlu.nombreCategoria,       " +
                "           tmPlu.nombrePlu ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = getJdbcAccess().getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaColaboraInventario fachadaBean;

            //
            while (rs.next()) {

                //
                fachadaBean = new FachadaColaboraInventario();

                //
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdPlu(rs.getInt("idPlu"));
                fachadaBean.setExistencia(rs.getDouble("existencia"));
                fachadaBean.setNombreCategoria(rs.getString("nombreCategoria"));
                fachadaBean.setNombreMarca(rs.getString("nombreMarca"));
                fachadaBean.setNombrePlu(rs.getString("nombrePlu"));
                fachadaBean.setVrCostoInventario(
                                             rs.getDouble("vrCostoInventario"));
                fachadaBean.setVrVentaInventario(
                                             rs.getDouble("vrVentaInventario"));
                fachadaBean.setVrCostoIva(rs.getDouble("vrCostoIva"));
                fachadaBean.setVrTotalImpoconsumo(
                                           rs.getDouble("vrTotalImpoconsumo"));
                fachadaBean.setVrTotalCostoIND(
                                           rs.getDouble("vrTotalCostoIND"));

                //
                list.add(new ColaboraInventarioBean(fachadaBean.getIdLocal(),
                                                    fachadaBean.getIdPlu(),
                                                    fachadaBean.getExistencia(),
                                               fachadaBean.getNombreCategoria(),
                                                   fachadaBean.getNombreMarca(),
                                                     fachadaBean.getNombrePlu(),
                                             fachadaBean.getVrCostoInventario(),
                                       fachadaBean.getVrCostoInventarioSinIva(),
                                                    fachadaBean.getVrCostoIva(),
                                            fachadaBean.getVrTotalImpoconsumo(),
                                             fachadaBean.getVrTotalCostoIND()));

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
            return list;

        }
    }

    // listaUnaCategoriaFCH
    public FachadaColaboraInventario listaUnaCategoriaFCH() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        //
        FachadaColaboraInventario fachadaBean
                                      = new FachadaColaboraInventario();

        Connection connection = null;

        String selectStr =
               "SELECT tmPlu.idLinea,                   " +
               "       tmPlu.idCategoria,               " +
               "       COUNT(*) AS cuentaReferencia,    " +
               "       SUM(tblPlusInventario.existencia)" +
               "                         AS existencia, " +
               "     SUM(tblPlusInventario.existencia * " +
               "        ( tmPlu.vrCosto -               " +
               "          tmPlu.vrImpoconsumo))         " +
               "             AS vrTotalCostoInventario, " +
               "     SUM(tblPlusInventario.existencia * " +
               "          tmPlu.vrImpoconsumo)          " +
               "              AS vrTotalImpoconsumo,    " +
               "     SUM(tblPlusInventario.existencia * " +
               "        ( tmPlu.vrCostoIND -            " +
               "          tmPlu.vrImpoconsumo))         " +
               "             AS vrTotalCostoIND,        " +
               "     SUM(tblPlusInventario.existencia * " +
               "        tmPlu.vrGeneral)                " +
               "              AS vrTotalVentaInventario " +
               "FROM   tblPlusInventario,               " +
               "( SELECT  tblPlus.idPlu,                " +
               "          tblPlus.nombrePlu,            " +
               "          tblPlus.vrGeneral,            " +
               "          tblPlus.vrMayorista,          " +
               "          tblPlus.porcentajeIva,        " +
               "          tblPlus.idTipo,               " +
               "          tblPlus.idLinea,              " +
               "          tblPlus.vrCosto,              " +
               "          tblPlus.vrCostoIND,           " +
               "          tblPlus.vrImpoconsumo,        " +
               "          tblPlus.idCategoria           " +
               "  FROM tblPlus                          " +
               "  INNER JOIN tblMarcas                  " +
               "  ON tblPlus.idMarca      =             " +
               "                    tblMarcas.idMarca   " +
               "  INNER JOIN tblCategorias              " +
               "  ON tblPlus.idLinea      =             " +
               "                  tblCategorias.idLinea " +
               "  AND tblPlus.idCategoria =             " +
               "            tblCategorias.IdCategoria ) " +
               "                               AS tmPlu " +
               "  WHERE tblPlusInventario.idLocal=      " +
               getIdLocal() + "                         " +
               "  AND tblPlusInventario.idBodega =      " +
               getIdBodega() + "                        " +
               "  AND   tblPlusInventario.idPlu  =      " +
               "                          tmPlu.idPlu   " +
               "  AND   tmPlu.idTipo             =      " +
               getIdTipo() + "                          " +
               "  AND   tmPlu.idCategoria        =      " +
               getIdCategoria() + "                     " +
               "  AND   tmPlu.idLinea            =      " +
               getIdLinea() + "                         " +
               "  GROUP BY tmPlu.idLinea,               " +
               "           tmPlu.idCategoria            " ;

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = getJdbcAccess().getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            //
            if (rs.next()) {

                //
                fachadaBean.setCuentaReferencia(rs.getInt("cuentaReferencia"));
                fachadaBean.setExistencia(rs.getDouble("existencia"));
                fachadaBean.setVrTotalCostoInventario(
                                        rs.getDouble("vrTotalCostoInventario"));
                fachadaBean.setVrTotalImpoconsumo(
                                        rs.getDouble("vrTotalImpoconsumo"));
                fachadaBean.setVrTotalCostoIND(
                                        rs.getDouble("vrTotalCostoIND"));
                fachadaBean.setVrTotalVentaInventario(
                                        rs.getDouble("vrTotalVentaInventario"));

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
            getJdbcAccess().cleanup(connection, selectStatement, null);
            return fachadaBean;

        }
    }

    // listaAllMarcaFCH
    public FachadaColaboraInventario listaAllMarcaFCH() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        //
        FachadaColaboraInventario fachadaBean
                                      = new FachadaColaboraInventario();

        Connection connection = null;

        String selectStr =
               "SELECT tblPlusInventario.idLocal,       " +
               "       COUNT(*) AS cuentaReferencia,    " +
               "       SUM(tblPlusInventario.existencia)" +
               "                         AS existencia, " +
               "     SUM(tblPlusInventario.existencia * " +
               "        tmPlu.vrCosto)                  " +
               "             AS vrTotalCostoInventario, " +
               "     SUM(tblPlusInventario.existencia * " +
               "        tmPlu.vrGeneral)                " +
               "              AS vrTotalVentaInventario " +
               "FROM   tblPlusInventario,               " +
               "( SELECT  tblPlus.idPlu,                " +
               "          tblPlus.nombrePlu,            " +
               "          tblPlus.vrGeneral,            " +
               "          tblPlus.vrMayorista,          " +
               "          tblPlus.porcentajeIva,        " +
               "          tblPlus.idTipo,               " +
               "          tblPlus.idLinea,              " +
               "          tblPlus.vrCosto,              " +
               "          tblPlus.idCategoria,          " +
               "          tblPlus.idMarca               " +
               "  FROM tblPlus                          " +
               "  INNER JOIN tblMarcas                  " +
               "  ON tblPlus.idMarca      =             " +
               "                    tblMarcas.idMarca   " +
               "  INNER JOIN tblCategorias              " +
               "  ON tblPlus.idLinea      =             " +
               "                  tblCategorias.idLinea " +
               "  AND tblPlus.idCategoria =             " +
               "            tblCategorias.IdCategoria ) " +
               "                               AS tmPlu " +
               "WHERE tblPlusInventario.idLocal=        " +
               getIdLocal() + "                         " +
               "AND   tblPlusInventario.idPlu  =        " +
               "                          tmPlu.idPlu   " +
               "AND tblPlusInventario.idBodega =        " +
               getIdBodega() + "                        " +
               "AND   tmPlu.idTipo             =        " +
               getIdTipo() + "                          " +
               "GROUP BY tblPlusInventario.idLocal" ;

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = getJdbcAccess().getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            //
            if (rs.next()) {

                //
                fachadaBean.setCuentaReferencia(rs.getInt("cuentaReferencia"));
                fachadaBean.setExistencia(rs.getDouble("existencia"));
                fachadaBean.setVrTotalCostoInventario(
                                        rs.getDouble("vrTotalCostoInventario"));
                fachadaBean.setVrTotalVentaInventario(
                                        rs.getDouble("vrTotalVentaInventario"));

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
            getJdbcAccess().cleanup(connection, selectStatement, null);
            return fachadaBean;

        }
    }

    // listaTotalUnMarcaFCH
    public FachadaColaboraInventario listaTotalUnMarcaFCH() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        //
        FachadaColaboraInventario fachadaBean
                                      = new FachadaColaboraInventario();

        Connection connection = null;

        String selectStr =
               "SELECT tblPlusInventario.idLocal,       " +
               "       COUNT(*) AS cuentaReferencia,    " +
               "       SUM(tblPlusInventario.existencia)" +
               "                         AS existencia, " +
               "     SUM(tblPlusInventario.existencia * " +
               "        ( tmPlu.vrCosto -               " +
               "          tmPlu.vrImpoconsumo))         " +
               "             AS vrTotalCostoInventario, " +
               "     SUM(tblPlusInventario.existencia * " +
               "          tmPlu.vrImpoconsumo)          " +
               "              AS vrTotalImpoconsumo,    " +
               "     SUM(tblPlusInventario.existencia * " +
               "        ( tmPlu.vrCostoIND -            " +
               "          tmPlu.vrImpoconsumo))         " +
               "             AS vrTotalCostoIND,        " +
               "     SUM(tblPlusInventario.existencia * " +
               "        tmPlu.vrGeneral)                " +
               "              AS vrTotalVentaInventario " +
               "FROM   tblPlusInventario,               " +
               "( SELECT  tblPlus.idPlu,                " +
               "          tblPlus.nombrePlu,            " +
               "          tblPlus.vrGeneral,            " +
               "          tblPlus.vrMayorista,          " +
               "          tblPlus.porcentajeIva,        " +
               "          tblPlus.idTipo,               " +
               "          tblPlus.idLinea,              " +
               "          tblPlus.vrCosto,              " +
               "          tblPlus.vrCostoIND,           " +
               "          tblPlus.vrImpoconsumo,        " +
               "          tblPlus.idCategoria,          " +
               "          tblPlus.idMarca               " +
               "  FROM tblPlus                          " +
               "  INNER JOIN tblMarcas                  " +
               "  ON tblPlus.idMarca      =             " +
               "                    tblMarcas.idMarca   " +
               "  INNER JOIN tblCategorias              " +
               "  ON tblPlus.idLinea      =             " +
               "                  tblCategorias.idLinea " +
               "  AND tblPlus.idCategoria =             " +
               "            tblCategorias.IdCategoria ) " +
               "                               AS tmPlu " +
               "WHERE tblPlusInventario.idLocal=        " +
               getIdLocal() + "                         " +
               "AND   tblPlusInventario.idPlu  =        " +
               "                          tmPlu.idPlu   " +
               "AND tblPlusInventario.idBodega =        " +
               getIdBodega() + "                        " +
               "AND   tmPlu.idTipo             =        " +
               getIdTipo() + "                          " +
               "AND   tmPlu.idMarca            =        " +
               getIdMarca() + "                         " +
               "GROUP BY tblPlusInventario.idLocal" ;

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = getJdbcAccess().getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            //
            if (rs.next()) {

                //
                fachadaBean.setCuentaReferencia(rs.getInt("cuentaReferencia"));
                fachadaBean.setExistencia(rs.getDouble("existencia"));
                fachadaBean.setVrTotalCostoInventario(
                                        rs.getDouble("vrTotalCostoInventario"));
                fachadaBean.setVrTotalImpoconsumo(
                                        rs.getDouble("vrTotalImpoconsumo"));
                fachadaBean.setVrTotalCostoIND(
                                        rs.getDouble("vrTotalCostoIND"));
                fachadaBean.setVrTotalVentaInventario(
                                        rs.getDouble("vrTotalVentaInventario"));

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
            getJdbcAccess().cleanup(connection, selectStatement, null);
            return fachadaBean;

        }
    }

    // listaAllCategoriaFCH
    public FachadaColaboraInventario listaAllCategoriaFCH() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        //
        FachadaColaboraInventario fachadaBean
                                      = new FachadaColaboraInventario();

        Connection connection = null;

        String selectStr =
               "SELECT tmPlu.idTipo,                    " +
               "       COUNT(*) AS cuentaReferencia,    " +
               "       SUM(tblPlusInventario.existencia)" +
               "                         AS existencia, " +
               "     SUM(tblPlusInventario.existencia * " +
               "        ( tmPlu.vrCosto -               " +
               "          tmPlu.vrImpoconsumo))         " +
               "             AS vrTotalCostoInventario, " +
               "     SUM(tblPlusInventario.existencia * " +
               "          tmPlu.vrImpoconsumo)          " +
               "              AS vrTotalImpoconsumo,    " +
               "     SUM(tblPlusInventario.existencia * " +
               "        ( tmPlu.vrCostoIND -            " +
               "          tmPlu.vrImpoconsumo))         " +
               "             AS vrTotalCostoIND,        " +
               "     SUM(tblPlusInventario.existencia * " +
               "        tmPlu.vrGeneral)                " +
               "              AS vrTotalVentaInventario " +
               "FROM   tblPlusInventario,               " +
               "( SELECT  tblPlus.idPlu,                " +
               "          tblPlus.nombrePlu,            " +
               "          tblPlus.vrGeneral,            " +
               "          tblPlus.vrMayorista,          " +
               "          tblPlus.porcentajeIva,        " +
               "          tblPlus.idTipo,               " +
               "          tblPlus.idLinea,              " +
               "          tblPlus.vrCosto,              " +
               "          tblPlus.vrCostoIND,           " +
               "          tblPlus.vrImpoconsumo,        " +
               "          tblPlus.idCategoria           " +
               "  FROM tblPlus                          " +
               "  INNER JOIN tblMarcas                  " +
               "  ON tblPlus.idMarca      =             " +
               "                    tblMarcas.idMarca   " +
               "  INNER JOIN tblCategorias              " +
               "  ON tblPlus.idLinea      =             " +
               "                  tblCategorias.idLinea " +
               "  AND tblPlus.idCategoria =             " +
               "            tblCategorias.IdCategoria ) " +
               "                               AS tmPlu " +
               "  WHERE tblPlusInventario.idLocal=      " +
               getIdLocal() + "                         " +
               "  AND tblPlusInventario.idBodega =      " +
               getIdBodega() + "                        " +
               "  AND   tblPlusInventario.idPlu  =      " +
               "                          tmPlu.idPlu   " +
               "  AND   tmPlu.idTipo             =      " +
               getIdTipo() + "                          " +
               "  GROUP BY tmPlu.idTipo                 " ;

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = getJdbcAccess().getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            //
            if (rs.next()) {

                //
                fachadaBean.setCuentaReferencia(rs.getInt("cuentaReferencia"));
                fachadaBean.setExistencia(rs.getDouble("existencia"));
                fachadaBean.setVrTotalCostoInventario(
                                        rs.getDouble("vrTotalCostoInventario"));
                fachadaBean.setVrTotalVentaInventario(
                                        rs.getDouble("vrTotalVentaInventario"));

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
            getJdbcAccess().cleanup(connection, selectStatement, null);
            return fachadaBean;

        }
    }

    public JDBCAccess getJdbcAccess() {
        return jdbcAccess;
    }

    public void setJdbcAccess(JDBCAccess jdbcAccess) {
        this.jdbcAccess = jdbcAccess;
    }

    public int getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(int idLocal) {
        this.idLocal = idLocal;
    }

    public int getIdPlu() {
        return idPlu;
    }

    public void setIdPlu(int idPlu) {
        this.idPlu = idPlu;
    }

    public double getExistencia() {
        return existencia;
    }

    public void setExistencia(double existencia) {
        this.existencia = existencia;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public String getNombreMarca() {
        return nombreMarca;
    }

    public void setNombreMarca(String nombreMarca) {
        this.nombreMarca = nombreMarca;
    }

    public String getNombrePlu() {
        return nombrePlu;
    }

    public void setNombrePlu(String nombrePlu) {
        this.nombrePlu = nombrePlu;
    }

    public int getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(int idMarca) {
        this.idMarca = idMarca;
    }

    public double getVrCostoInventario() {
        return vrCostoInventario;
    }

    public void setVrCostoInventario(double vrCostoInventario) {
        this.vrCostoInventario = vrCostoInventario;
    }

    public double getVrVentaInventario() {
        return vrVentaInventario;
    }

    public void setVrVentaInventario(double vrVentaInventario) {
        this.vrVentaInventario = vrVentaInventario;
    }

    public double getVrCostoInventarioSinIva() {
        return vrCostoInventarioSinIva;
    }

    public void setVrCostoInventarioSinIva(double vrCostoInventarioSinIva) {
        this.vrCostoInventarioSinIva = vrCostoInventarioSinIva;
    }

    public double getVrCostoIva() {
        return vrCostoIva;
    }

    public void setVrCostoIva(double vrCostoIva) {
        this.vrCostoIva = vrCostoIva;
    }

    public double getVrTotalImpoconsumo() {
        return vrTotalImpoconsumo;
    }

    public void setVrTotalImpoconsumo(double vrTotalImpoconsumo) {
        this.vrTotalImpoconsumo = vrTotalImpoconsumo;
    }

    public double getVrTotalCostoIND() {
        return vrTotalCostoIND;
    }

    public void setVrTotalCostoIND(double vrTotalCostoIND) {
        this.vrTotalCostoIND = vrTotalCostoIND;
    }
}