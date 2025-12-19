package com.solucionesweb.losbeans.colaboraciones;

// Importa la clase helper JDBCAccess que contiene los accesos a la base de datos
import com.solucionesweb.lasayudas.JDBCAccess;

// Importa el bean de utilidades
import com.solucionesweb.losbeans.utilidades.IConstantes;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaColaboraReporteDctoBean;

// Importa los paquetes de java
import java.sql.*;
import java.io.*;
import java.io.IOException.*;

//import java.util.Date;
import java.util.Vector;
import java.io.IOException.*;

// Importa los paquetes de javax
import javax.naming.*;

public class ColaboraReporteDctoBean extends FachadaColaboraReporteDctoBean
        implements Serializable, IConstantes {

    // Nombre JNDI del Data Source que esta clase requiere
    private static final String DATA_SOURCE_NAME = "CommerceAccess";

    // Clase helper usada para acceder a la base de datos
    private JDBCAccess jdbcAccess;

    // Metodo constructor por defecto sin parametros
    public ColaboraReporteDctoBean() {

        // Al instanciar el Bean establece la conexion a la base de datos
        jdbcAccess = new JDBCAccess(DATA_SOURCE_NAME);
    }

    // Metodo listaFechasTipoOrdenLocal MsAccess
    public Vector listaFechasTipoOrdenLocal() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        boolean validaOk = false;
        Connection connection = null;

        String selectStr
                = "SELECT tblDctosOrdenes.idLocal,                  "
                + "       tblDctosOrdenes.idTipoOrden,              "
                + "       tblDctosOrdenes.fechaOrden                "
                + "FROM tblDctosOrdenes,                            "
                + "     tblDctosOrdenesDetalle                      "
                + "WHERE tblDctosOrdenes.IDORDEN     =              "
                + "         tblDctosOrdenesDetalle.IDORDEN          "
                + "AND   tblDctosOrdenes.IDTIPOORDEN =              "
                + "         tblDctosOrdenesDetalle.IDTIPOORDEN      "
                + "AND   tblDctosOrdenes.IDLOCAL     =              "
                + "         tblDctosOrdenesDetalle.IDLOCAL          "
                + "AND   tblDctosOrdenes.IDLOCAL     = ( ? )        "
                + "AND   tblDctosOrdenes.IDTIPOORDEN = ( ? )        "
                + "AND   tblDctosOrdenes.FECHAORDEN                 "
                + "         BETWEEN ( ? ) AND ( ? )                 "
                + "GROUP BY tblDctosOrdenes.IDLOCAL,                "
                + "         tblDctosOrdenes.idTipoOrden,            "
                + "         tblDctosOrdenes.FECHAORDEN              "
                + "ORDER BY tblDctosOrdenes.IDLOCAL,                "
                + "         tblDctosOrdenes.idTipoOrden,            "
                + "         tblDctosOrdenes.FECHAORDEN              ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // inicializa los valores de los parametros
            selectStatement.setInt(1, getIdLocal());
            selectStatement.setInt(2, getIdTipoOrden());
            selectStatement.setString(3, getFechaInicialSqlServer());
            selectStatement.setString(4, getFechaFinalSqlServer());

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaColaboraReporteDctoBean fachadaBean;

            while (rs.next()) {

                fachadaBean = new FachadaColaboraReporteDctoBean();

                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setFechaOrden(rs.getString("fechaOrden").substring(0, 10));

                //
                contenedor.add(fachadaBean);

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

    // Metodo listaFechasTipoOrdenUsuario
    public Vector listaFechasTipoOrdenUsuario() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        boolean validaOk = false;
        Connection connection = null;

        String selectStr
                = "SELECT tblDctosOrdenes.idUsuario,                "
                + "       tblDctosOrdenes.idTipoOrden,              "
                + "       tblDctosOrdenes.fechaOrden                "
                + "FROM tblDctosOrdenes,                            "
                + "     tblDctosOrdenesDetalle                      "
                + "WHERE tblDctosOrdenes.IDORDEN     =              "
                + "         tblDctosOrdenesDetalle.IDORDEN          "
                + "AND   tblDctosOrdenes.IDTIPOORDEN =              "
                + "         tblDctosOrdenesDetalle.IDTIPOORDEN      "
                + "AND   tblDctosOrdenes.IDLOCAL     =              "
                + "         tblDctosOrdenesDetalle.IDLOCAL          "
                + "AND   tblDctosOrdenes.idUsuario   =              "
                + getIdUsuario() + "                               "
                + "AND   tblDctosOrdenes.IDTIPOORDEN =              "
                + getIdTipoOrden() + "                             "
                + "AND   tblDctosOrdenes.FECHAORDEN                 "
                + "         BETWEEN                                '"
                + getFechaInicialSqlServer() + "' AND              '"
                + getFechaFinalSqlServer() + "'                     "
                + "GROUP BY tblDctosOrdenes.idUsuario,              "
                + "         tblDctosOrdenes.idTipoOrden,            "
                + "         tblDctosOrdenes.FECHAORDEN              "
                + "ORDER BY tblDctosOrdenes.idUsuario,              "
                + "         tblDctosOrdenes.idTipoOrden,            "
                + "         tblDctosOrdenes.FECHAORDEN DESC         ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaColaboraReporteDctoBean fachadaBean;

            while (rs.next()) {

                fachadaBean = new FachadaColaboraReporteDctoBean();

                fachadaBean.setIdUsuario(rs.getDouble("idUsuario"));
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setFechaOrden(
                        rs.getString("fechaOrden").substring(0, 10));

                //
                contenedor.add(fachadaBean);

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

    // Metodo listaFechasRuta MsAccess
    public Vector listaFechasRuta(String xIdRuta) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        boolean validaOk = false;
        Connection connection = null;

        String selectStr
                = "SELECT tblDctosOrdenes.idUsuario,                "
                + "       tblDctosOrdenes.idTipoOrden,              "
                + "       tblDctosOrdenes.fechaOrden                "
                + "FROM tblDctosOrdenes,                            "
                + "     tblDctosOrdenesDetalle                      "
                + "WHERE tblDctosOrdenes.IDORDEN     =              "
                + "         tblDctosOrdenesDetalle.IDORDEN          "
                + "AND   tblDctosOrdenes.IDTIPOORDEN =              "
                + "         tblDctosOrdenesDetalle.IDTIPOORDEN      "
                + "AND   tblDctosOrdenes.IDLOCAL     =              "
                + "         tblDctosOrdenesDetalle.IDLOCAL          "
                + "AND   tblDctosOrdenes.IDTIPOORDEN =              "
                + getIdTipoOrden() + "                              "
                + "AND   tblDctosOrdenes.FECHAORDEN                 "
                + "         BETWEEN                                '"
                + getFechaInicialSqlServer() + "'       AND        '"
                + getFechaFinalSqlServer() + "'                     "
                + "AND   tblDctosOrdenes.idCliente IN ( SELECT tblTerceros.idCliente                    "
                + "                                     FROM   tblTerceros                              "
                + "                                     WHERE  tblTerceros.idRuta IN (" + xIdRuta + ")) "
                + "GROUP BY tblDctosOrdenes.idUsuario,              "
                + "         tblDctosOrdenes.idTipoOrden,            "
                + "         tblDctosOrdenes.FECHAORDEN              "
                + "ORDER BY tblDctosOrdenes.FECHAORDEN,             "
                + "         tblDctosOrdenes.idUsuario,              "
                + "         tblDctosOrdenes.idTipoOrden             ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaColaboraReporteDctoBean fachadaBean;

            while (rs.next()) {

                fachadaBean = new FachadaColaboraReporteDctoBean();

                fachadaBean.setIdUsuario(rs.getDouble("idUsuario"));
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setFechaOrden(rs.getString("fechaOrden").substring(0, 10));

                //
                contenedor.add(fachadaBean);

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

    // Metodo totalTipoOrdenRuta MsAccess
    public Vector totalTipoOrdenRuta(String xIdRuta) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        boolean validaOk = false;
        Connection connection = null;

        String selectStr
                = "SELECT  tmpTotales.idLocal,                            "
                + "        tmpTotales.idTipoOrden,                        "
                + "        tmpTotales.idUsuario,                          "
                + "        ctrlUsuarios.nombreUsuario,                    "
                + "        COUNT(tmpTotales.idOrden)                      "
                + "                           AS totalOrdenes,            "
                + "        SUM(tmpTotales.pesoTeorico)                    "
                + "                        AS totalPesoTeorico,           "
                + "        SUM(tmpTotales.vrVentaConIva)                  "
                + "                        AS totalVrVentaConIva          "
                + "FROM ctrlUsuarios,                                     "
                + "     (SELECT tblDctosOrdenes.IDLOCAL,                  "
                + "       tblDctosOrdenes.idTipoOrden,                    "
                + "       tblDctosOrdenes.idUsuario,                      "
                + "       tblDctosOrdenes.fechaOrden,                     "
                + "       tblDctosOrdenes.idOrden,                        "
                + "       SUM( tblDctosOrdenesDetalle.cantidad          * "
                + "            tblDctosOrdenesDetalle.pesoteorico )       "
                + "            AS pesoteorico,                            "
                + "       SUM ( tblDctosOrdenesDetalle.cantidad         * "
                + "             tblDctosOrdenesDetalle.vrVentaUnitario  * "
                + "  (1 - (tblDctosOrdenesDetalle.porcentajeDscto/100)) * "
                + "  (1 + (tblDctosOrdenesDetalle.porcentajeIva/100)))    "
                + "                        AS vrVentaConIva               "
                + "       FROM tblDctosOrdenes,                           "
                + "            tblDctosOrdenesDetalle                     "
                + "       WHERE tblDctosOrdenes.IDORDEN     =             "
                + "                  tblDctosOrdenesDetalle.IDORDEN       "
                + "       AND   tblDctosOrdenes.IDTIPOORDEN =             "
                + "              tblDctosOrdenesDetalle.IDTIPOORDEN       "
                + "       AND   tblDctosOrdenes.IDLOCAL     =             "
                + "                  tblDctosOrdenesDetalle.IDLOCAL       "
                + "       AND   tblDctosOrdenes.IDTIPOORDEN = ( ? )       "
                + "       AND   tblDctosOrdenes.FECHAORDEN                "
                + "                        BETWEEN  ( ? ) AND ( ? )       "
                + "       AND   tblDctosOrdenes.idCliente IN ( SELECT tblTerceros.idCliente                    "
                + "                                            FROM   tblTerceros                              "
                + "                                            WHERE  tblTerceros.idRuta IN (" + xIdRuta + ")) "
                + "       GROUP BY tblDctosOrdenes.IDLOCAL,               "
                + "                tblDctosOrdenes.idTipoOrden,           "
                + "                tblDctosOrdenes.FECHAORDEN,            "
                + "                tblDctosOrdenes.IDUSUARIO,             "
                + "                tblDctosOrdenes.idOrden)               "
                + "                                    AS tmpTotales      "
                + "WHERE tmpTotales.idUsuario = ctrlUsuarios.idUsuario    "
                + "GROUP BY tmpTotales.idLocal,                           "
                + "         tmpTotales.idTipoOrden,                       "
                + "         tmpTotales.idUsuario,                         "
                + "         ctrlUsuarios.nombreUsuario                    ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // inicializa los valores de los parametros
            selectStatement.setInt(1, getIdTipoOrden());
            selectStatement.setString(2, getFechaInicialSqlServer());
            selectStatement.setString(3, getFechaFinalSqlServer());

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaColaboraReporteDctoBean fachadaBean;

            while (rs.next()) {

                fachadaBean = new FachadaColaboraReporteDctoBean();

                //
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setIdUsuario(rs.getDouble("idUsuario"));
                fachadaBean.setTotalOrdenes(rs.getInt("totalOrdenes"));
                fachadaBean.setTotalPesoTeorico(rs.getDouble("totalPesoTeorico"));
                fachadaBean.setTotalVrVentaConIva(rs.getDouble("totalVrVentaConIva"));
                fachadaBean.setNombreUsuario(rs.getString("nombreUsuario"));

                //
                contenedor.add(fachadaBean);

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

    // Metodo listaTipoOrdenFechaRuta MsAccess
    public Vector listaTipoOrdenFechaRuta(String xIdRuta) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        boolean validaOk = false;
        Connection connection = null;

        String selectStr
                = "SELECT  tmpTotales.idLocal,                            "
                + "        tmpTotales.idTipoOrden,                        "
                + "        tmpTotales.idUsuario,                          "
                + "        ctrlUsuarios.nombreUsuario,                    "
                + "        tmpTotales.fechaOrden,                         "
                + "        COUNT(tmpTotales.idOrden)                      "
                + "                             AS totalOrdenes,          "
                + "        SUM(tmpTotales.pesoTeorico)                    "
                + "                         AS totalPesoTeorico,          "
                + "        SUM(tmpTotales.vrVentaConIva)                  "
                + "                        AS totalVrVentaConIva          "
                + "FROM ctrlUsuarios,                                     "
                + "     (SELECT tblDctosOrdenes.IDLOCAL,                  "
                + "       tblDctosOrdenes.idTipoOrden,                    "
                + "       tblDctosOrdenes.idUsuario,                      "
                + "       tblDctosOrdenes.fechaOrden,                     "
                + "       tblDctosOrdenes.idOrden,                        "
                + "       SUM( tblDctosOrdenesDetalle.cantidad *          "
                + "            tblDctosOrdenesDetalle.pesoTeorico )       "
                + "            AS pesoTeorico,                            "
                + "       SUM ( tblDctosOrdenesDetalle.cantidad         * "
                + "             tblDctosOrdenesDetalle.vrVentaUnitario  * "
                + "  (1 - (tblDctosOrdenesDetalle.porcentajeDscto/100)) * "
                + "  (1 + (tblDctosOrdenesDetalle.porcentajeIva/100)))    "
                + "                                   AS vrVentaConIva    "
                + "       FROM tblDctosOrdenes,                           "
                + "            tblDctosOrdenesDetalle                     "
                + "       WHERE tblDctosOrdenes.IDORDEN     =             "
                + "                  tblDctosOrdenesDetalle.IDORDEN       "
                + "       AND   tblDctosOrdenes.IDTIPOORDEN =             "
                + "              tblDctosOrdenesDetalle.IDTIPOORDEN       "
                + "       AND   tblDctosOrdenes.IDLOCAL     =             "
                + "                  tblDctosOrdenesDetalle.IDLOCAL       "
                + "       AND   tblDctosOrdenes.IDTIPOORDEN =             "
                + getIdTipoOrden() + "                                    "
                + "       AND   tblDctosOrdenes.FECHAORDEN  =            '"
                + getFechaOrdenSqlServer() + "'                           "
                + "       AND   tblDctosOrdenes.idCliente IN ( SELECT tblTerceros.idCliente                   "
                + "                                            FROM tblTerceros                               "
                + "                                            WHERE tblTerceros.idRuta IN (" + xIdRuta + ")) "
                + "       GROUP BY tblDctosOrdenes.IDLOCAL,               "
                + "                tblDctosOrdenes.idTipoOrden,           "
                + "                tblDctosOrdenes.FECHAORDEN,            "
                + "                tblDctosOrdenes.IDUSUARIO,             "
                + "                tblDctosOrdenes.idOrden)               "
                + "                                    AS tmpTotales      "
                + "WHERE tmpTotales.idUsuario = ctrlUsuarios.idUsuario    "
                + "GROUP BY tmpTotales.idLocal,                           "
                + "         tmpTotales.idTipoOrden,                       "
                + "         tmpTotales.fechaOrden,                        "
                + "         tmpTotales.idUsuario,                         "
                + "         ctrlUsuarios.nombreUsuario                    ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaColaboraReporteDctoBean fachadaBean;

            while (rs.next()) {

                fachadaBean = new FachadaColaboraReporteDctoBean();

                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setIdUsuario(rs.getDouble("idUsuario"));
                fachadaBean.setFechaOrden(rs.getString("fechaOrden").substring(0, 10));
                fachadaBean.setTotalOrdenes(rs.getInt("totalOrdenes"));
                fachadaBean.setTotalPesoTeorico(rs.getDouble("totalPesoTeorico"));
                fachadaBean.setTotalVrVentaConIva(rs.getDouble("totalVrVentaConIva"));
                fachadaBean.setNombreUsuario(rs.getString("nombreUsuario"));

                //
                contenedor.add(fachadaBean);

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

    // Metodo listaFechasTipoOrdenCliente MsAccess
    public Vector listaFechasTipoOrdenCliente() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        boolean validaOk = false;
        Connection connection = null;

        String selectStr
                = "SELECT tblDctosOrdenes.idUsuario,                "
                + "       tblDctosOrdenes.idTipoOrden,              "
                + "       tblDctosOrdenes.fechaOrden                "
                + "FROM tblDctosOrdenes,                            "
                + "     tblDctosOrdenesDetalle                      "
                + "WHERE tblDctosOrdenes.IDORDEN     =              "
                + "         tblDctosOrdenesDetalle.IDORDEN          "
                + "AND   tblDctosOrdenes.IDTIPOORDEN =              "
                + "         tblDctosOrdenesDetalle.IDTIPOORDEN      "
                + "AND   tblDctosOrdenes.IDLOCAL     =              "
                + "         tblDctosOrdenesDetalle.IDLOCAL          "
                + "AND   tblDctosOrdenes.idCliente   =             '"
                + getIdCliente() + "'                               "
                + "AND   tblDctosOrdenes.IDTIPOORDEN =              "
                + getIdTipoOrden() + "                              "
                + "AND   tblDctosOrdenes.FECHAORDEN                 "
                + "         BETWEEN                                '"
                + getFechaInicialSqlServer() + "'           AND    '"
                + getFechaFinalSqlServer() + "'                     "
                + "GROUP BY tblDctosOrdenes.idUsuario,              "
                + "         tblDctosOrdenes.idTipoOrden,            "
                + "         tblDctosOrdenes.FECHAORDEN              "
                + "ORDER BY tblDctosOrdenes.FECHAORDEN DESC         ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaColaboraReporteDctoBean fachadaBean;

            while (rs.next()) {

                fachadaBean = new FachadaColaboraReporteDctoBean();

                fachadaBean.setIdUsuario(rs.getDouble("idUsuario"));
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setFechaOrden(
                        rs.getString("fechaOrden").substring(0, 10));

                //
                contenedor.add(fachadaBean);

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

    // Metodo listaOrdenPeriodo
    public Vector listaOrdenPeriodo() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        String selectStr
                = "SELECT tblDctos.IDLOCAL,                      "
                + "       tblDctos.IDTIPOORDEN,                  "
                + "       tblDctos.IDORDEN,                      "
                + "       tblDctos.idDcto,                       "
                + "       tblDctos.indicador,                    "
                + "       tblDctos.fechaDcto,                    "
                + "       tblDctos.vrBase,                       "
                + "       tblDctos.vrIva,                        "
                + "       tblDctos.vrRteFuente,                  "
                + "       tblDctos.vrDescuento,                  "
                + "       tblDctos.vrRteIva,                     "
                + "       tblDctos.vrRteIca,                     "
                + "       tblDctos.idDctoNitCC,                  "
                + "       tblDctos.vrImpoconsumo,                "
                + "      tblDctos.idDctoCruce,                  "
                + "       (SELECT COUNT(*) AS numeroArticulo     "
                + "        FROM   tblDctosOrdenes                "
                + "        INNER JOIN tblDctosOrdenesDetalle     "
                + "        ON tblDctosOrdenes.IDLOCAL         =  "
                + "              tblDctosOrdenesDetalle.IDLOCAL  "
                + "        AND tblDctosOrdenes.IDTIPOORDEN    =  "
                + "          tblDctosOrdenesDetalle.IDTIPOORDEN  "
                + "        AND tblDctosOrdenes.IDORDEN        =  "
                + "              tblDctosOrdenesDetalle.IDORDEN  "
                + "        WHERE tblDctosOrdenes.IDLOCAL      =  "
                + "                            tblDctos.IDLOCAL  "
                + "        AND   tblDctosOrdenes.IDTIPOORDEN  =  "
                + "                        tblDctos.IDTIPOORDEN  "
                + "        AND   tblDctosOrdenes.IDORDEN      =  "
                + "                           tblDctos.IDORDEN)  "
                + "                           AS numeroArticulo, "
                + "        ( SELECT tblTipoOrden.nombreTipoOrden "
                + "          FROM tblTipoOrden                   "
                + "          WHERE tblTipoOrden.idTipoOrden   =  "
                + "                        tblDctos.idTipoOrden) "
                + "                           AS nombreTipoOrden,"
                + "        tmpORD.idOrdenOrigen,                  "
                + "        isnull((select TOP 1 idDctoDian from tblFacturaElectronica where idOrden = tblDctos.IDORDEN and idTipoOrden = tblDctos.IDTIPOORDEN and tipo_xml = 1 ),0) dctofe,\n"
                + "	   isnull((select TOP 1 url from tblFacturaElectronica where idOrden = tblDctos.IDORDEN and idTipoOrden = tblDctos.IDTIPOORDEN and tipo_xml = 1 ),0) url                  "
                + "FROM tblDctos                                 "
                + "INNER JOIN                                    "
                + "      (SELECT tblDctosOrdenesDetalle.idLocal  "
                + "          ,tblDctosOrdenesDetalle.idTipoOrden "
                + "          ,tblDctosOrdenesDetalle.idOrden     "
                + "    ,MAX(tblDctosOrdenesDetalle.idLocalOrigen)"
                + "                            AS idLocalOrigen, "
                + "MAX(tblDctosOrdenesDetalle.idTipoOrdenOrigen) "
                + "                         AS idTipoOrdenOrigen "
                + ",MAX(tblDctosOrdenesDetalle.idOrdenOrigen)    "
                + "                            AS idOrdenOrigen  "
                + "       FROM tblDctosOrdenesDetalle            "
                + "     GROUP BY tblDctosOrdenesDetalle.idLocal  "
                + "          ,tblDctosOrdenesDetalle.idTipoOrden "
                + "             ,tblDctosOrdenesDetalle.idOrden) "
                + "                                    AS tmpORD "
                + "ON  tblDctos.IDLOCAL     = tmpORD.idLocal     "
                + "AND tblDctos.IDTIPOORDEN = tmpORD.idTipoOrden "
                + "AND tblDctos.IDORDEN     = tmpORD.idOrden     "
                + "WHERE tblDctos.idLocal                     =  "
                + getIdLocal() + "                               "
                + "AND   tblDctos.idTipoOrden                 IN "
                + "  (" + getIdTipoOrdenCadena() + ")            "
                + "AND   tblDctos.idCliente                   = '"
                + getIdCliente() + "'                            "
                + "AND   tblDctos.fechaDcto BETWEEN             '"
                + getFechaInicialSqlServer() + "'     AND       '"
                + getFechaFinalSqlServer() + "'                  "
                + "AND   tblDctos.indicador BETWEEN              "
                + getIndicadorInicial() + "   AND                "
                + getIndicadorFinal() + "                        "
                + "ORDER BY tblDctos.fechaDcto DESC              ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);
            System.out.println(selectStatement);
            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaColaboraReporteDctoBean fachadaBean;

            while (rs.next()) {

                fachadaBean = new FachadaColaboraReporteDctoBean();

                //
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setIdOrden(rs.getInt("idOrden"));
                fachadaBean.setIdDcto(rs.getInt("idDcto"));
                fachadaBean.setIndicador(rs.getInt("indicador"));
                fachadaBean.setFechaDcto(rs.getString("fechaDcto"));
                fachadaBean.setVrBase(rs.getDouble("vrBase"));
                fachadaBean.setVrIva(rs.getDouble("vrIva"));
                fachadaBean.setVrRteFuente(rs.getDouble("vrRteFuente"));
                fachadaBean.setVrDescuento(rs.getDouble("vrDescuento"));
                fachadaBean.setVrRteIva(rs.getDouble("vrRteIva"));
                fachadaBean.setVrRteIca(rs.getDouble("vrRteIca"));
                fachadaBean.setIdDctoNitCC(rs.getString("idDctoNitCC"));
                fachadaBean.setVrImpoconsumo(rs.getDouble("vrImpoconsumo"));
                fachadaBean.setNumeroArticulo(rs.getInt("numeroArticulo"));
                fachadaBean.setNombreTipoOrden(rs.getString("nombreTipoOrden"));
                fachadaBean.setIdDctoCruce(rs.getInt("idDctoCruce"));
                fachadaBean.setIdOrdenOrigen(rs.getInt("idOrdenOrigen"));
                fachadaBean.setDctoDian(rs.getInt("dctofe"));
                fachadaBean.setUrlDian(rs.getString("url"));

                //
                contenedor.add(fachadaBean);

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

    // listaVentaAll
    public Vector listaVentaAll(String xIdTipoOrdenCadena,
            String xFechaInicial,
            String xFechaFinal) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        String selectStr
                = "   SELECT tblDctos.idLocal,                                    "
                + "          tblDctos.idTipoOrden,		                   "
                + "          tblDctos.idDcto,		                           "
                + "          (CASE WHEN tblDctos.idTipoOrden = 9 THEN (SELECT TOP 1 tblFacturaElectronica.idDctoDian  FROM tblFacturaElectronica \n"
                + "          WHERE tblFacturaElectronica.idOrden = tblDctos.IDORDEN)    \n"
                + "          WHEN tblDctos.idTipoOrden= 29 THEN (idDcto)   END) AS numeroFE, "
                + "          tblDctos.fechaDcto,		                   "
                + "          tblDctos.indicador,		                   "
                + "          tblDctos.idCliente,		                   "
                + "          tblDctos.vrBase,		                           "
                + "          tblDctos.vrIva,			                   "
                + "          tblDctos.vrImpoconsumo,                              "
                + "          tblDctos.nombreTercero,		                   "
                + "          tblDctos.idDctoNitCC,		                   "
                + "          tblDctos.fechaDctoNitCC,	                           "
                + "          tblDctos.idLocalCruce,		                   "
                + "          tblDctos.idTipoOrdenCruce,	                   "
                + "          tblDctos.idDctoCruce,		                   "
                + "          (tblDctos.vrBase        +	                           "
                + "           tblDctos.vrIva         +	                           "
                + "           tblDctos.vrImpoconsumo -	                           "
                + "           tblDctos.vrRteFuente   -	                           "
                + "          tblDctos.vrRteIva) AS vrFacturaVenta, +              "
                + "          tblDctos.vrDescuento,		                   "
                + "          tblDctos.vrRteFuente,		                   "
                + "          tblDctos.vrRteIva,		                   "
                + "          (CASE WHEN			                   "
                + "                tblDctos.idTipoOrden = 1	                   "
                + "           THEN    tblDctos.idDctoNitCC	                   "
                + "           ELSE    STR(tblDctos.idDcto)	                   "
                + "           END) AS idDctoStr,		                   "
                + "          (CASE WHEN			                   "
                + "                tblDctos.idTipoNegocio = 1                     "
                + "           THEN      'CONTADO'		                   "
                + "           ELSE      'CREDITO'		                   "
                + "           END) AS nombreTipoNegocio,	                   "
                + "          tblDctos.idTipoNegocio,	    	                   "
                + "          ( SELECT ctrlUsuarios.aliasUsuario                   "
                + "            FROM ctrlUsuarios		                   "
                + "            WHERE ctrlUsuarios.idUsuario =                     "
                + "                        tblDctos.idVendedor )                  "
                + "                              AS aliasUsuario,                 "
                + "           tmpLOG.horaTx,                                      "
                + "           tmpLOG.prefijo,                                     "
                + "           tmpLOG.idCaja                                       "
                + "   FROM   tblDctos,(                                           "
                + "   SELECT     tblDctosOrdenes.IDLOCAL,                         "
                + "              tblDctosOrdenes.idOrden,                         "
                + "              tblDctosOrdenes.idTipoOrden,                     "
                + "              LTRIM(SUBSTRING(CONVERT(VARCHAR(20),             "
                + "              tblAgendaLogVisitas.fechaTx, 22), 10,5)+         "
                + "              RIGHT(CONVERT(VARCHAR(20), +                     "
                + "             tblAgendaLogVisitas.fechaTx, 22), 3))             "
                + "                         AS horaTx,                            "
                + "             tblLocalesCaja.prefijo,                           "
                + "             tblLocalesCaja.idCaja                             "
                + "   FROM      tblDctos                                          "
                + "   INNER JOIN tblDctosOrdenes                                  "
                + "   ON tblDctos.IDLOCAL      = tblDctosOrdenes.IDLOCAL          "
                + "   AND tblDctos.IDTIPOORDEN = tblDctosOrdenes.IDTIPOORDEN      "
                + "   AND tblDctos.IDORDEN     = tblDctosOrdenes.IDORDEN          "
                + "   INNER JOIN tblAgendaLogVisitas                              "
                + "   ON tblDctosOrdenes.IDLOCAL = tblAgendaLogVisitas.idLocal    "
                + "   AND tblDctosOrdenes.IDLOG  = tblAgendaLogVisitas.IDLOG      "
                + "   INNER JOIN tblLocalesCaja                                   "
                + "   ON tblAgendaLogVisitas.idLocal = tblLocalesCaja.idLocal     "
                + "   AND tblAgendaLogVisitas.ipTx = tblLocalesCaja.ipLocal       "
                + "   ) AS tmpLOG		                                   "
                + "   WHERE EXISTS				                   "
                + "      ( SELECT *				                   "
                + "        FROM   tblDctosOrdenes		                   "
                + "        INNER JOIN tblDctosOrdenesDetalle	                   "
                + "        ON tblDctosOrdenes.IDLOCAL      =	                   "
                + "           tblDctosOrdenesDetalle.IDLOCAL	                   "
                + "        AND tblDctosOrdenes.IDTIPOORDEN =                      "
                + "           tblDctosOrdenesDetalle.IDTIPOORDEN                  "
                + "        AND tblDctosOrdenes.IDORDEN     =	                   "
                + "           tblDctosOrdenesDetalle.IDORDEN	                   "
                + "        WHERE tblDctosOrdenes.IDLOCAL   =	                   "
                + "                             tblDctos.IDLOCAL                  "
                + "        AND tblDctosOrdenes.IDTIPOORDEN =	                   "
                + "                         tblDctos.IDTIPOORDEN                  "
                + "        AND tblDctosOrdenes.IDORDEN     =	                   "
                + "                            tblDctos.IDORDEN)                  "
                + "        AND tblDctos.idLocal   =                               "
                + getIdLocal() + "                                       "
                + "        AND tblDctos.fechaDcto BETWEEN                        '"
                + xFechaInicial + "'  AND                               '"
                + xFechaFinal + "'                                        "
                + "        AND tblDctos.idTipoOrden  IN                          ("
                + xIdTipoOrdenCadena + ")                                 "
                + "        AND  tblDctos.indicador   BETWEEN                      "
                + getIndicadorInicial() + "  AND  "
                + getIndicadorFinal() + "                                  "
                + "        AND tmpLOG.idLocal=tblDctos.idLocal               "
                + "        AND tmpLOG.idTipoOrden=tblDctos.idTipoOrden       "
                + "        AND tmpLOG.idOrden=tblDctos.idOrden               "
                + "        ORDER BY tblDctos.fechaDcto,		      "
                + "            tblDctos.idTipoOrden,		              "
                + "            tblDctos.idDcto	                              ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaColaboraReporteDctoBean fachadaBean;

            while (rs.next()) {

                fachadaBean = new FachadaColaboraReporteDctoBean();

                //
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setIdDcto(rs.getInt("numeroFE"));
                fachadaBean.setFechaDcto(rs.getString("fechaDcto"));
                fachadaBean.setIndicador(rs.getInt("indicador"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setVrBase(rs.getDouble("vrBase"));
                fachadaBean.setVrIva(rs.getDouble("vrIva"));
                fachadaBean.setVrImpoconsumo(rs.getDouble("vrImpoconsumo"));
                fachadaBean.setNombreTercero(rs.getString("nombreTercero"));
                fachadaBean.setIdDctoNitCC(rs.getString("idDctoNitCC"));
                fachadaBean.setFechaDctoNitCC(rs.getString("fechaDctoNitCC"));
                fachadaBean.setIdLocalCruce(rs.getInt("idLocalCruce"));
                fachadaBean.setIdTipoOrdenCruce(rs.getInt("idTipoOrdenCruce"));
                fachadaBean.setIdDctoCruce(rs.getInt("idDctoCruce"));
                fachadaBean.setVrFacturaVenta(rs.getDouble("vrFacturaVenta"));
                fachadaBean.setVrDescuento(rs.getDouble("vrDescuento"));
                fachadaBean.setVrRteFuente(rs.getDouble("vrRteFuente"));
                fachadaBean.setVrRteIva(rs.getDouble("vrRteIva"));
                fachadaBean.setVrFacturaVenta(rs.getDouble("vrFacturaVenta"));
                fachadaBean.setNombreTipoNegocio(
                        rs.getString("nombreTipoNegocio"));
                fachadaBean.setAliasUsuario(rs.getString("aliasUsuario"));
                fachadaBean.setHoraTx(rs.getString("horaTx"));
                fachadaBean.setPrefijo(rs.getString("prefijo"));
                fachadaBean.setIdCaja(rs.getInt("idCaja"));

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

    // listaVentaUn
    public Vector listaVentaUn(String xIdTipoOrdenCadena,
            String xFechaInicial,
            String xFechaFinal) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        String selectStr
                = "   SELECT tblDctos.idLocal,                                    "
                + "          tblDctos.idTipoOrden,		                   "
                + "          tblDctos.idDcto,		                           "
                + "          tblDctos.fechaDcto,		                   "
                + "          tblDctos.indicador,		                   "
                + "          tblDctos.idCliente,		                   "
                + "          tblDctos.vrBase,		                           "
                + "          tblDctos.vrIva,			                   "
                + "          tblDctos.vrImpoconsumo,                              "
                + "          tblDctos.nombreTercero,		                   "
                + "          tblDctos.idDctoNitCC,		                   "
                + "          tblDctos.fechaDctoNitCC,	                           "
                + "          tblDctos.idLocalCruce,		                   "
                + "          tblDctos.idTipoOrdenCruce,	                   "
                + "          tblDctos.idDctoCruce,		                   "
                + "          (tblDctos.vrBase        +	                           "
                + "           tblDctos.vrIva         +	                           "
                + "           tblDctos.vrImpoconsumo -	                           "
                + "           tblDctos.vrRteFuente   -	                           "
                + "          tblDctos.vrRteIva) AS vrFacturaVenta, +              "
                + "          tblDctos.vrDescuento,		                   "
                + "          tblDctos.vrRteFuente,		                   "
                + "          tblDctos.vrRteIva,		                   "
                + "          (CASE WHEN			                   "
                + "                tblDctos.idTipoOrden = 1	                   "
                + "           THEN    tblDctos.idDctoNitCC	                   "
                + "           ELSE    STR(tblDctos.idDcto)	                   "
                + "           END) AS idDctoStr,		                   "
                + "          (CASE WHEN			                   "
                + "                tblDctos.idTipoNegocio = 1                     "
                + "           THEN      'CONTADO'		                   "
                + "           ELSE      'CREDITO'		                   "
                + "           END) AS nombreTipoNegocio,	                   "
                + "          tblDctos.idTipoNegocio,	    	                   "
                + "          ( SELECT ctrlUsuarios.aliasUsuario                   "
                + "            FROM ctrlUsuarios		                   "
                + "            WHERE ctrlUsuarios.idUsuario =                     "
                + "                        tblDctos.idVendedor )                  "
                + "                              AS aliasUsuario,                 "
                + "           tmpLOG.horaTx,                                      "
                + "           tmpLOG.prefijo,                                     "
                + "           tmpLOG.idCaja                                       "
                + "   FROM   tblDctos,(                                           "
                + "   SELECT     tblDctosOrdenes.IDLOCAL,                         "
                + "              tblDctosOrdenes.idOrden,                         "
                + "              tblDctosOrdenes.idTipoOrden,                     "
                + "              LTRIM(SUBSTRING(CONVERT(VARCHAR(20),             "
                + "              tblAgendaLogVisitas.fechaTx, 22), 10,5)+         "
                + "              RIGHT(CONVERT(VARCHAR(20), +                     "
                + "             tblAgendaLogVisitas.fechaTx, 22), 3))             "
                + "                         AS horaTx,                            "
                + "             tblLocalesCaja.prefijo,                           "
                + "             tblLocalesCaja.idCaja                             "
                + "   FROM      tblDctos                                          "
                + "   INNER JOIN tblDctosOrdenes                                  "
                + "   ON tblDctos.IDLOCAL      = tblDctosOrdenes.IDLOCAL          "
                + "   AND tblDctos.IDTIPOORDEN = tblDctosOrdenes.IDTIPOORDEN      "
                + "   AND tblDctos.IDORDEN     = tblDctosOrdenes.IDORDEN          "
                + "   INNER JOIN tblAgendaLogVisitas                              "
                + "   ON tblDctosOrdenes.IDLOCAL = tblAgendaLogVisitas.idLocal    "
                + "   AND tblDctosOrdenes.IDLOG  = tblAgendaLogVisitas.IDLOG      "
                + "   INNER JOIN tblLocalesCaja                                   "
                + "   ON tblAgendaLogVisitas.idLocal = tblLocalesCaja.idLocal     "
                + "   AND tblAgendaLogVisitas.ipTx = tblLocalesCaja.ipLocal       "
                + "   WHERE  tblDctos.idVendedor    =                             "
                + getIdVendedor() + "  ) AS tmpLOG		                   "
                + "   WHERE EXISTS				                   "
                + "      ( SELECT *				                   "
                + "        FROM   tblDctosOrdenes		                   "
                + "        INNER JOIN tblDctosOrdenesDetalle	                   "
                + "        ON tblDctosOrdenes.IDLOCAL      =	                   "
                + "           tblDctosOrdenesDetalle.IDLOCAL	                   "
                + "        AND tblDctosOrdenes.IDTIPOORDEN =                      "
                + "           tblDctosOrdenesDetalle.IDTIPOORDEN                  "
                + "        AND tblDctosOrdenes.IDORDEN     =	                   "
                + "           tblDctosOrdenesDetalle.IDORDEN	                   "
                + "        WHERE tblDctosOrdenes.IDLOCAL   =	                   "
                + "                             tblDctos.IDLOCAL                  "
                + "        AND tblDctosOrdenes.IDTIPOORDEN =	                   "
                + "                         tblDctos.IDTIPOORDEN                  "
                + "        AND tblDctosOrdenes.IDORDEN     =	                   "
                + "                            tblDctos.IDORDEN)                  "
                + "        AND tblDctos.idLocal   =                               "
                + getIdLocal() + "                                       "
                + "        AND tblDctos.fechaDcto BETWEEN                        '"
                + xFechaInicial + "'  AND                               '"
                + xFechaFinal + "'                                        "
                + "        AND tblDctos.idTipoOrden  IN                          ("
                + xIdTipoOrdenCadena + ")                                 "
                + "        AND  tblDctos.indicador   BETWEEN                      "
                + getIndicadorInicial() + "  AND  "
                + getIndicadorFinal() + "                                  "
                + "        AND tmpLOG.idLocal=tblDctos.idLocal               "
                + "        AND tmpLOG.idTipoOrden=tblDctos.idTipoOrden       "
                + "        AND tmpLOG.idOrden=tblDctos.idOrden               "
                + "        ORDER BY tblDctos.fechaDcto,		      "
                + "            tblDctos.idTipoOrden,		              "
                + "            tblDctos.idDcto	                              ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaColaboraReporteDctoBean fachadaBean;

            while (rs.next()) {

                fachadaBean = new FachadaColaboraReporteDctoBean();

                //
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setIdDcto(rs.getInt("idDcto"));
                fachadaBean.setFechaDcto(rs.getString("fechaDcto"));
                fachadaBean.setIndicador(rs.getInt("indicador"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setVrBase(rs.getDouble("vrBase"));
                fachadaBean.setVrIva(rs.getDouble("vrIva"));
                fachadaBean.setVrImpoconsumo(rs.getDouble("vrImpoconsumo"));
                fachadaBean.setNombreTercero(rs.getString("nombreTercero"));
                fachadaBean.setIdDctoNitCC(rs.getString("idDctoNitCC"));
                fachadaBean.setFechaDctoNitCC(rs.getString("fechaDctoNitCC"));
                fachadaBean.setIdLocalCruce(rs.getInt("idLocalCruce"));
                fachadaBean.setIdTipoOrdenCruce(rs.getInt("idTipoOrdenCruce"));
                fachadaBean.setIdDctoCruce(rs.getInt("idDctoCruce"));
                fachadaBean.setVrFacturaVenta(rs.getDouble("vrFacturaVenta"));
                fachadaBean.setVrDescuento(rs.getDouble("vrDescuento"));
                fachadaBean.setVrRteFuente(rs.getDouble("vrRteFuente"));
                fachadaBean.setVrRteIva(rs.getDouble("vrRteIva"));
                fachadaBean.setVrFacturaVenta(rs.getDouble("vrFacturaVenta"));
                fachadaBean.setNombreTipoNegocio(
                        rs.getString("nombreTipoNegocio"));
                fachadaBean.setAliasUsuario(rs.getString("aliasUsuario"));
                fachadaBean.setHoraTx(rs.getString("horaTx"));
                fachadaBean.setPrefijo(rs.getString("prefijo"));
                fachadaBean.setIdCaja(rs.getInt("idCaja"));

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

    //listaCompraAll
    public Vector listaCompraAll(String xIdTipoOrdenCadena,
            String xFechaInicial,
            String xFechaFinal) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        String selectStr
                = "   SELECT tblDctos.idLocal,                                    "
                + "          tblDctos.idTipoOrden,		                   "
                + "          tblDctos.idDcto,		                           "
                + "          tblDctos.fechaDcto,		                   "
                + "          tblDctos.indicador,		                   "
                + "          tblDctos.idCliente,		                   "
                + "          tblDctos.vrBase,		                           "
                + "          tblDctos.vrIva,			                   "
                + "          tblDctos.vrImpoconsumo,                              "
                + "          tblDctos.nombreTercero,		                   "
                + "          tblDctos.idDctoNitCC,		                   "
                + "          tblDctos.fechaDctoNitCC,	                           "
                + "          tblDctos.idLocalCruce,		                   "
                + "          tblDctos.idTipoOrdenCruce,	                   "
                + "          tblDctos.idDctoCruce,		                   "
                + "          (tblDctos.vrBase        +	                           "
                + "           tblDctos.vrIva         +	                           "
                + "           tblDctos.vrImpoconsumo -	                           "
                + "           tblDctos.vrRteFuente   -	                           "
                + "          tblDctos.vrRteIva) AS vrFacturaVenta,                "
                + "          tblDctos.vrDescuento,		                   "
                + "          tblDctos.vrRteFuente,		                   "
                + "          (CASE WHEN			                   "
                + "                tblDctos.idTipoOrden = 1	                   "
                + "           THEN    tblDctos.idDctoNitCC	                   "
                + "           ELSE    STR(tblDctos.idDcto)	                   "
                + "           END) AS idDctoStr,		                   "
                + "          (CASE WHEN			                   "
                + "                tblDctos.idTipoNegocio = 1                     "
                + "           THEN      'CONTADO'		                   "
                + "           ELSE      'CREDITO'		                   "
                + "           END) AS nombreTipoNegocio,	                   "
                + "          tblDctos.idTipoNegocio,	    	                   "
                + "          ( SELECT ctrlUsuarios.aliasUsuario                   "
                + "            FROM ctrlUsuarios		                   "
                + "            WHERE ctrlUsuarios.idUsuario =                     "
                + "                        tblDctos.idVendedor )                  "
                + "                              AS aliasUsuario                  "
                + "   FROM      tblDctos                                          "
                + "   WHERE EXISTS				                   "
                + "      ( SELECT *				                   "
                + "        FROM   tblDctosOrdenes		                   "
                + "        INNER JOIN tblDctosOrdenesDetalle	                   "
                + "        ON tblDctosOrdenes.IDLOCAL      =	                   "
                + "           tblDctosOrdenesDetalle.IDLOCAL	                   "
                + "        AND tblDctosOrdenes.IDTIPOORDEN =                      "
                + "           tblDctosOrdenesDetalle.IDTIPOORDEN                  "
                + "        AND tblDctosOrdenes.IDORDEN     =	                   "
                + "           tblDctosOrdenesDetalle.IDORDEN	                   "
                + "        WHERE tblDctosOrdenes.IDLOCAL   =	                   "
                + "                             tblDctos.IDLOCAL                  "
                + "        AND tblDctosOrdenes.IDTIPOORDEN =	                   "
                + "                         tblDctos.IDTIPOORDEN                  "
                + "        AND tblDctosOrdenes.IDORDEN     =	                   "
                + "                            tblDctos.IDORDEN)                  "
                + "        AND tblDctos.idLocal   =                               "
                + getIdLocal() + "                                       "
                + "        AND tblDctos.fechaDcto BETWEEN                        '"
                + xFechaInicial + "'  AND  '"
                + xFechaFinal + "'                                        "
                + "        AND tblDctos.idTipoOrden  IN                          ("
                + xIdTipoOrdenCadena + ")                                 "
                + "        AND  tblDctos.indicador   BETWEEN                      "
                + getIndicadorInicial() + "  AND  "
                + getIndicadorFinal() + "                                  "
                + "        ORDER BY tblDctos.fechaDcto,		      "
                + "            tblDctos.idTipoOrden,		              "
                + "            tblDctos.idDcto	                              ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaColaboraReporteDctoBean fachadaBean;

            while (rs.next()) {

                fachadaBean = new FachadaColaboraReporteDctoBean();

                //
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setIdDcto(rs.getInt("idDcto"));
                fachadaBean.setFechaDcto(rs.getString("fechaDcto"));
                fachadaBean.setIndicador(rs.getInt("indicador"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setVrBase(rs.getDouble("vrBase"));
                fachadaBean.setVrIva(rs.getDouble("vrIva"));
                fachadaBean.setVrImpoconsumo(rs.getDouble("vrImpoconsumo"));
                fachadaBean.setNombreTercero(rs.getString("nombreTercero"));
                fachadaBean.setIdDctoNitCC(rs.getString("idDctoNitCC"));
                fachadaBean.setFechaDctoNitCC(rs.getString("fechaDctoNitCC"));
                fachadaBean.setIdLocalCruce(rs.getInt("idLocalCruce"));
                fachadaBean.setIdTipoOrdenCruce(rs.getInt("idTipoOrdenCruce"));
                fachadaBean.setIdDctoCruce(rs.getInt("idDctoCruce"));
                fachadaBean.setVrFacturaVenta(rs.getDouble("vrFacturaVenta"));
                fachadaBean.setVrDescuento(rs.getDouble("vrDescuento"));
                fachadaBean.setVrRteFuente(rs.getDouble("vrRteFuente"));
                fachadaBean.setVrFacturaVenta(rs.getDouble("vrFacturaVenta"));
                fachadaBean.setNombreTipoNegocio(
                        rs.getString("nombreTipoNegocio"));
                fachadaBean.setAliasUsuario(rs.getString("aliasUsuario"));
                //fachadaBean.setHoraTx(rs.getString("horaTx"));
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

    // listaVentaAll
    public Vector listaMargenAll(String xIdTipoOrdenCadena,
            String xFechaInicial,
            String xFechaFinal) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        //
        String selectStr
                = " SELECT tmpDET.*,           "
                + " (CASE                      "
                + "  WHEN (tmpDET.vrBase)= 0   "
                + "  THEN (tmpDET.vrBase)      "
                + "  ELSE ((tmpDET.vrBase)-    "
                + "     (tmpDET.vrCostoIND))/  "
                + "      (tmpDET.vrBase)       "
                + "         END) AS margenIND  "
                + "FROM (                                                         "
                + "SELECT tblDctos.idLocal,                                       "
                + "       tblDctos.idTipoOrden,                                   "
                + "       tblDctos.idDcto,                                        "
                + "       tblDctos.idOrden,                                       "
                + "       tblDctos.fechaDcto,                                     "
                + "       tblDctos.indicador,                                     "
                + "       tblDctos.idCliente,                                     "
                + "(CASE WHEN                                                     "
                + "      tblDctos.indicador = 1                                   "
                + "THEN  tblDctos.vrBase                                          "
                + "ELSE  ( tblDctos.vrBase +                                      "
                + "        tblDctos.vrIva )                                       "
                + "END) AS vrBase,                                                "
                + "                                                               "
                + "(CASE WHEN                                                     "
                + "          tblDctos.indicador = 1                               "
                + "THEN      tblDctos.vrIva                                       "
                + "ELSE    0                                                      "
                + "END) AS vrIva,                                                 "
                + "          tblDctos.vrImpoconsumo,                              "
                + "          tblDctos.nombreTercero,                              "
                + "          tblDctos.idDctoNitCC,                                "
                + "          tblDctos.fechaDctoNitCC,                             "
                + "          tblDctos.idLocalCruce,                               "
                + "          tblDctos.idTipoOrdenCruce,                           "
                + "          tblDctos.idDctoCruce,                                "
                + "          (tblDctos.vrBase      +                              "
                + "           tblDctos.vrIva       +                              "
                + "           tblDctos.vrImpoconsumo -                            "
                + "           tblDctos.vrRteFuente   -                            "
                + "           tblDctos.vrRteIva) AS vrFacturaVenta,               "
                + "           tblDctos.vrDescuento,                               "
                + "           tblDctos.vrRteFuente,                               "
                + "           tmpDOR.vrCostoMV,                                   "
                + "           tblDctos.vrCostoIND,                                "
                + "(CASE WHEN                                                     "
                + "           tblDctos.idTipoOrden = 1                            "
                + "THEN       tblDctos.idDctoNitCC                                "
                + "ELSE       STR(tblDctos.idDcto)                                "
                + "END) AS idDctoStr,                                             "
                + "(CASE WHEN                                                     "
                + "               tblDctos.idTipoNegocio = 1                      "
                + "THEN      'CONTADO'                                            "
                + "ELSE      'CREDITO'                                            "
                + "END) AS nombreTipoNegocio,                                     "
                + "               tblDctos.idTipoNegocio,                         "
                + "( SELECT   ctrlUsuarios.aliasUsuario                           "
                + "  FROM     ctrlUsuarios                                        "
                + "  WHERE    ctrlUsuarios.idUsuario =                            "
                + "           tblDctos.idVendedor )                               "
                + "           AS aliasUsuario                                     "
                + "  FROM   tblDctos,                                             "
                + "                                                               "
                + "( SELECT  tblDctosOrdenesDetalle.IDLOCAL                       "
                + "          ,tblDctosOrdenesDetalle.IDTIPOORDEN                  "
                + "          , tblDctosOrdenesDetalle.IDORDEN                     "
                + "          ,SUM( tblDctosOrdenesDetalle.CANTIDAD *              "
                + "                tblDctosOrdenesDetalle.vrCosto ) AS vrCostoMV  "
                + "FROM tblDctosOrdenesDetalle                                    "
                + "GROUP BY  tblDctosOrdenesDetalle.IDLOCAL                       "
                + "          ,tblDctosOrdenesDetalle.IDTIPOORDEN                  "
                + "          ,tblDctosOrdenesDetalle.IDORDEN ) AS tmpDOR          "
                + "                                                               "
                + "WHERE EXISTS                                                   "
                + "(   SELECT *                                                   "
                + "    FROM   tblDctosOrdenes                                     "
                + "    INNER JOIN tblDctosOrdenesDetalle                          "
                + "       ON  tblDctosOrdenes.IDLOCAL      =                      "
                + "           tblDctosOrdenesDetalle.IDLOCAL                      "
                + "       AND tblDctosOrdenes.IDTIPOORDEN =                       "
                + "           tblDctosOrdenesDetalle.IDTIPOORDEN                  "
                + "       AND tblDctosOrdenes.IDORDEN     =                       "
                + "           tblDctosOrdenesDetalle.IDORDEN                      "
                + "    WHERE tblDctosOrdenes.IDLOCAL   =                          "
                + "          tblDctos.IDLOCAL                                     "
                + "	AND tblDctosOrdenes.IDTIPOORDEN =                       "
                + "           tblDctos.IDTIPOORDEN                                "
                + "	AND tblDctosOrdenes.IDORDEN     =                       "
                + "           tblDctos.IDORDEN)                                   "
                + "	AND tblDctos.idLocal   =     				"
                + getIdLocal() + " 						"
                + "	AND tblDctos.fechaDcto BETWEEN                         '"
                + xFechaInicial + "'  AND 	                       '"
                + xFechaFinal + "' 		                        "
                + " 	AND   tblDctos.idTipoOrden  IN            (		"
                + xIdTipoOrdenCadena + " )		  			"
                + "	AND  tblDctos.indicador   BETWEEN     			"
                + getIndicadorInicial() + " AND       			        "
                + getIndicadorFinal() + "					"
                + "       AND tmpDOR.idLocal = tblDctos.IDLOCAL			"
                + "       AND tmpDOR.idTipoOrden = tblDctos.idTipoOrden           "
                + "       AND tmpDOR.idOrden = tblDctos.idOrden) AS tmpDET        "
                + "       ORDER BY tmpDET.fechaDcto,                              "
                + "                tmpDET.idTipoOrden,                            "
                + "                tmpDET.idDcto                                  ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaColaboraReporteDctoBean fachadaBean;

            while (rs.next()) {

                fachadaBean = new FachadaColaboraReporteDctoBean();

                //
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setIdDcto(rs.getInt("idDcto"));
                fachadaBean.setFechaDcto(rs.getString("fechaDcto"));
                fachadaBean.setIndicador(rs.getInt("indicador"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setVrBase(rs.getDouble("vrBase"));
                fachadaBean.setVrIva(rs.getDouble("vrIva"));
                fachadaBean.setVrImpoconsumo(rs.getDouble("vrImpoconsumo"));
                fachadaBean.setNombreTercero(rs.getString("nombreTercero"));
                fachadaBean.setIdDctoNitCC(rs.getString("idDctoNitCC"));
                fachadaBean.setFechaDctoNitCC(rs.getString("fechaDctoNitCC"));
                fachadaBean.setIdLocalCruce(rs.getInt("idLocalCruce"));
                fachadaBean.setIdTipoOrdenCruce(rs.getInt("idTipoOrdenCruce"));
                fachadaBean.setIdDctoCruce(rs.getInt("idDctoCruce"));
                fachadaBean.setVrDescuento(rs.getDouble("vrDescuento"));
                fachadaBean.setVrRteFuente(rs.getDouble("vrRteFuente"));
                fachadaBean.setVrCostoIND(rs.getDouble("vrCostoIND"));
                fachadaBean.setVrCostoMV(rs.getDouble("vrCostoMV"));
                fachadaBean.setVrFacturaVenta(rs.getDouble("vrFacturaVenta"));
                fachadaBean.setNombreTipoNegocio(
                        rs.getString("nombreTipoNegocio"));
                fachadaBean.setAliasUsuario(rs.getString("aliasUsuario"));
                fachadaBean.setMargenIND(rs.getDouble("margenIND"));

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

    // Metodo listaTipoOrdenFechaCO MsAccess
    public Vector listaTipoOrdenFechaCO() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        boolean validaOk = false;
        Connection connection = null;

        String selectStr
                = "SELECT  tmpTotales.idLocal,                            "
                + "        tmpTotales.idTipoOrden,                        "
                + "        tmpTotales.idUsuario,                          "
                + "        ctrlUsuarios.nombreUsuario,                    "
                + "        tmpTotales.fechaOrden,                         "
                + "        COUNT(tmpTotales.idOrden)                      "
                + "                           AS totalOrdenes,            "
                + "        SUM(tmpTotales.pesoTeorico)                    "
                + "                        AS totalPesoTeorico,           "
                + "        SUM(tmpTotales.vrVentaConIva)                  "
                + "                        AS totalVrVentaConIva          "
                + "FROM ctrlUsuarios,                                     "
                + "     (SELECT tblDctosOrdenes.IDLOCAL,                  "
                + "       tblDctosOrdenes.idTipoOrden,                    "
                + "       tblDctosOrdenes.idUsuario,                      "
                + "       tblDctosOrdenes.fechaOrden,                     "
                + "       tblDctosOrdenes.idOrden,                        "
                + "       SUM( tblDctosOrdenesDetalle.cantidad *          "
                + "            tblDctosOrdenesDetalle.pesoTeorico )       "
                + "            AS pesoTeorico,                            "
                + "       SUM ( tblDctosOrdenesDetalle.cantidad         * "
                + "             tblDctosOrdenesDetalle.vrVentaUnitario  * "
                + "  (1 - (tblDctosOrdenesDetalle.porcentajeDscto/100)) * "
                + "  (1 + (tblDctosOrdenesDetalle.porcentajeIva/100)))    "
                + "                                   AS vrVentaConIva    "
                + "       FROM tblDctosOrdenes,                           "
                + "            tblDctosOrdenesDetalle                     "
                + "       WHERE tblDctosOrdenes.IDORDEN     =             "
                + "                  tblDctosOrdenesDetalle.IDORDEN       "
                + "       AND   tblDctosOrdenes.IDTIPOORDEN =             "
                + "              tblDctosOrdenesDetalle.IDTIPOORDEN       "
                + "       AND   tblDctosOrdenes.IDLOCAL     =             "
                + "                  tblDctosOrdenesDetalle.IDLOCAL       "
                + "       AND   tblDctosOrdenes.IDLOCAL     = ( ? )       "
                + "       AND   tblDctosOrdenes.IDTIPOORDEN = ( ? )       "
                + "       AND   tblDctosOrdenes.FECHAORDEN  = ( ? )       "
                + "       GROUP BY tblDctosOrdenes.IDLOCAL,               "
                + "                tblDctosOrdenes.idTipoOrden,           "
                + "                tblDctosOrdenes.FECHAORDEN,            "
                + "                tblDctosOrdenes.IDUSUARIO,             "
                + "                tblDctosOrdenes.idOrden                "
                + "       ORDER BY tblDctosOrdenes.IDLOCAL,               "
                + "                tblDctosOrdenes.FECHAORDEN,            "
                + "                tblDctosOrdenes.IDUSUARIO,             "
                + "                tblDctosOrdenes.idOrden)               "
                + "                                    AS tmpTotales      "
                + "WHERE tmpTotales.idUsuario = ctrlUsuarios.idUsuario    "
                + "GROUP BY tmpTotales.idLocal,                           "
                + "         tmpTotales.idTipoOrden,                       "
                + "         tmpTotales.fechaOrden,                        "
                + "         tmpTotales.idUsuario,                         "
                + "         ctrlUsuarios.nombreUsuario                    ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // inicializa los valores de los parametros
            selectStatement.setInt(1, getIdLocal());
            selectStatement.setInt(2, getIdTipoOrden());
            selectStatement.setString(3, getFechaOrden());

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaColaboraReporteDctoBean fachadaBean;

            while (rs.next()) {

                fachadaBean = new FachadaColaboraReporteDctoBean();

                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setIdUsuario(rs.getDouble("idUsuario"));
                fachadaBean.setFechaOrden(rs.getString("fechaOrden").substring(0, 10));
                fachadaBean.setTotalOrdenes(rs.getInt("totalOrdenes"));
                fachadaBean.setTotalPesoTeorico(rs.getDouble("totalPesoTeorico"));
                fachadaBean.setTotalVrVentaConIva(rs.getDouble("totalVrVentaConIva"));
                fachadaBean.setNombreUsuario(rs.getString("nombreUsuario"));

                //
                contenedor.add(fachadaBean);

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

    // Metodo listaTotalTipoOrdenFechaCO MsAccess
    public Vector listaTotalTipoOrdenFechaCO() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        boolean validaOk = false;
        Connection connection = null;

        String selectStr
                = "SELECT  tmpTotales.idLocal,                            "
                + "        tmpTotales.idTipoOrden,                        "
                + "        tmpTotales.idUsuario,                          "
                + "        ctrlUsuarios.nombreUsuario,                    "
                + "        COUNT(tmpTotales.idOrden)                      "
                + "                           AS totalOrdenes,            "
                + "        SUM(tmpTotales.pesoTeorico)                    "
                + "                        AS totalPesoTeorico,           "
                + "        SUM(tmpTotales.vrVentaConIva)                  "
                + "                        AS totalVrVentaConIva          "
                + "FROM ctrlUsuarios,                                     "
                + "     (SELECT tblDctosOrdenes.IDLOCAL,                  "
                + "       tblDctosOrdenes.idTipoOrden,                    "
                + "       tblDctosOrdenes.idUsuario,                      "
                + "       tblDctosOrdenes.fechaOrden,                     "
                + "       tblDctosOrdenes.idOrden,                        "
                + "       SUM( tblDctosOrdenesDetalle.cantidad          * "
                + "            tblDctosOrdenesDetalle.pesoteorico )       "
                + "            AS pesoteorico,                            "
                + "       SUM ( tblDctosOrdenesDetalle.cantidad         * "
                + "             tblDctosOrdenesDetalle.vrVentaUnitario  * "
                + "  (1 - (tblDctosOrdenesDetalle.porcentajeDscto/100)) * "
                + "  (1 + (tblDctosOrdenesDetalle.porcentajeIva/100)))    "
                + "                        AS vrVentaConIva               "
                + "       FROM tblDctosOrdenes,                           "
                + "            tblDctosOrdenesDetalle                     "
                + "       WHERE tblDctosOrdenes.IDORDEN     =             "
                + "                  tblDctosOrdenesDetalle.IDORDEN       "
                + "       AND   tblDctosOrdenes.IDTIPOORDEN =             "
                + "              tblDctosOrdenesDetalle.IDTIPOORDEN       "
                + "       AND   tblDctosOrdenes.IDLOCAL     =             "
                + "                  tblDctosOrdenesDetalle.IDLOCAL       "
                + "       AND   tblDctosOrdenes.IDLOCAL     = ( ? )       "
                + "       AND   tblDctosOrdenes.IDTIPOORDEN = ( ? )       "
                + "       AND   tblDctosOrdenes.FECHAORDEN                "
                + "                        BETWEEN  ( ? ) AND ( ? )       "
                + "       GROUP BY tblDctosOrdenes.IDLOCAL,               "
                + "                tblDctosOrdenes.idTipoOrden,           "
                + "                tblDctosOrdenes.FECHAORDEN,            "
                + "                tblDctosOrdenes.IDUSUARIO,             "
                + "                tblDctosOrdenes.idOrden                "
                + "       ORDER BY tblDctosOrdenes.IDLOCAL,               "
                + "                tblDctosOrdenes.FECHAORDEN,            "
                + "                tblDctosOrdenes.IDUSUARIO,             "
                + "                tblDctosOrdenes.idOrden)               "
                + "                                    AS tmpTotales      "
                + "WHERE tmpTotales.idUsuario = ctrlUsuarios.idUsuario    "
                + "GROUP BY tmpTotales.idLocal,                           "
                + "         tmpTotales.idTipoOrden,                       "
                + "         tmpTotales.idUsuario,                         "
                + "         ctrlUsuarios.nombreUsuario                    ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // inicializa los valores de los parametros
            selectStatement.setInt(1, getIdLocal());
            selectStatement.setInt(2, getIdTipoOrden());
            selectStatement.setString(3, getFechaInicialSqlServer());
            selectStatement.setString(4, getFechaFinalSqlServer());

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaColaboraReporteDctoBean fachadaBean;

            while (rs.next()) {

                fachadaBean = new FachadaColaboraReporteDctoBean();

                //
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setIdUsuario(rs.getDouble("idUsuario"));
                fachadaBean.setTotalOrdenes(rs.getInt("totalOrdenes"));
                fachadaBean.setTotalPesoTeorico(rs.getDouble("totalPesoTeorico"));
                fachadaBean.setTotalVrVentaConIva(rs.getDouble("totalVrVentaConIva"));
                fachadaBean.setNombreUsuario(rs.getString("nombreUsuario"));

                //
                contenedor.add(fachadaBean);

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

    // Metodo listaFechasTipoOrdenCorporativo MsAccess
    public Vector listaFechasTipoOrdenCorporativo() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        boolean validaOk = false;
        Connection connection = null;

        String selectStr
                = "SELECT tblDctosOrdenes.idTipoOrden,              "
                + "       tblDctosOrdenes.fechaOrden                "
                + "FROM tblDctosOrdenes,                            "
                + "     tblDctosOrdenesDetalle                      "
                + "WHERE tblDctosOrdenes.IDORDEN     =              "
                + "         tblDctosOrdenesDetalle.IDORDEN          "
                + "AND   tblDctosOrdenes.IDTIPOORDEN =              "
                + "         tblDctosOrdenesDetalle.IDTIPOORDEN      "
                + "AND   tblDctosOrdenes.IDLOCAL     =              "
                + "         tblDctosOrdenesDetalle.IDLOCAL          "
                + "AND   tblDctosOrdenes.IDTIPOORDEN = ( ? )        "
                + "AND   tblDctosOrdenes.FECHAORDEN                 "
                + "         BETWEEN ( ? ) AND ( ? )                 "
                + "GROUP BY tblDctosOrdenes.idTipoOrden,            "
                + "         tblDctosOrdenes.fechaOrden              "
                + "ORDER BY tblDctosOrdenes.idTipoOrden,            "
                + "         tblDctosOrdenes.fechaOrden              ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // inicializa los valores de los parametros
            selectStatement.setInt(1, getIdTipoOrden());
            selectStatement.setString(2, getFechaInicialSqlServer());
            selectStatement.setString(3, getFechaFinalSqlServer());

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaColaboraReporteDctoBean fachadaBean;

            while (rs.next()) {

                fachadaBean = new FachadaColaboraReporteDctoBean();

                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setFechaOrden(rs.getString("fechaOrden").substring(0, 10));

                //
                contenedor.add(fachadaBean);
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

    // Metodo listaTotalTipoOrdenFechaCorporativo MsAccess
    public Vector listaTotalTipoOrdenFechaCorporativo() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        boolean validaOk = false;
        Connection connection = null;

        String selectStr
                = "SELECT   tmpDcto.idtipoorden,                                              "
                + "         tmpDcto.idusuario,                                                "
                + "         tmpDcto.nombreusuario,                                            "
                + "         COUNT (*) AS totalOrdenes,                                        "
                + "         SUM (tmpDcto.pesoteorico) AS totalPesoTeorico,                    "
                + "         SUM (tmpDcto.vrVentaConIva) AS totalvrVentaConIva                 "
                + "    FROM (SELECT   tbldctosordenes.idtipoorden AS idtipoorden,             "
                + "                   tbldctosordenes.idusuario AS idusuario,                 "
                + "                   ctrlusuarios.nombreusuario AS nombreusuario,            "
                + "                   tbldctosordenes.fechaorden AS fechaorden,               "
                + "                   tbldctosordenes.idorden AS idorden,                     "
                + "                   SUM (  tbldctosordenesdetalle.cantidad                  "
                + "                      * tbldctosordenesdetalle.pesoteorico) AS pesoteorico,"
                + "                           SUM ( tblDctosOrdenesDetalle.cantidad         * "
                + "                                 tblDctosOrdenesDetalle.vrVentaUnitario  * "
                + "                      (1 - (tblDctosOrdenesDetalle.porcentajeDscto/100)) * "
                + "                      (1 + (tblDctosOrdenesDetalle.porcentajeIva/100)))    "
                + "                                                       AS vrVentaConIva    "
                + "          FROM tbldctosordenes, tbldctosordenesdetalle, ctrlusuarios       "
                + "          WHERE tbldctosordenes.idorden = tbldctosordenesdetalle.idorden   "
                + "          AND tbldctosordenes.idtipoorden =                                "
                + "                                    tbldctosordenesdetalle.idtipoorden     "
                + "          AND tbldctosordenes.idlocal = tbldctosordenesdetalle.idlocal     "
                + "          AND   tblDctosOrdenes.IDTIPOORDEN = ( ? )                        "
                + "          AND   tblDctosOrdenes.FECHAORDEN                                 "
                + "                                   BETWEEN ( ? ) AND ( ? )                 "
                + "          AND ctrlusuarios.idusuario = tbldctosordenes.idusuario           "
                + "          GROUP BY tbldctosordenes.idtipoorden,                            "
                + "                   tbldctosordenes.fechaorden,                             "
                + "                   tbldctosordenes.idusuario,                              "
                + "                   ctrlusuarios.nombreusuario,                             "
                + "                   tbldctosordenes.idorden) AS tmpDcto                     "
                + "GROUP BY tmpDcto.idtipoorden,                                              "
                + "         tmpDcto.idusuario,                                                "
                + "         tmpDcto.nombreusuario                                             "
                + "ORDER BY tmpDcto.nombreusuario                                             ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // inicializa los valores de los parametros
            selectStatement.setInt(1, getIdTipoOrden());
            selectStatement.setString(2, getFechaInicialSqlServer());
            selectStatement.setString(3, getFechaFinalSqlServer());

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaColaboraReporteDctoBean fachadaBean;

            while (rs.next()) {

                fachadaBean = new FachadaColaboraReporteDctoBean();

                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setIdUsuario(rs.getDouble("idUsuario"));
                fachadaBean.setNombreUsuario(rs.getString("nombreUsuario"));
                fachadaBean.setTotalOrdenes(rs.getInt("totalOrdenes"));
                fachadaBean.setTotalPesoTeorico(rs.getDouble("totalPesoTeorico"));
                fachadaBean.setTotalVrVentaConIva(rs.getDouble("totalvrVentaConIva"));

                //
                contenedor.add(fachadaBean);
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

    // Metodo listaTipoOrdenFechaCorporativo MsAccess
    public Vector listaTipoOrdenFechaCorporativo() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        boolean validaOk = false;
        Connection connection = null;

        String selectStr
                = "SELECT   tmpDcto.idtipoorden,                                              "
                + "         tmpDcto.idusuario,                                                "
                + "         tmpDcto.nombreusuario,                                            "
                + "         tmpDcto.fechaOrden,                                               "
                + "         COUNT (*) AS totalOrdenes,                                        "
                + "         SUM (tmpDcto.pesoteorico) AS totalPesoTeorico,                    "
                + "         SUM (tmpDcto.vrVentaConIva) AS totalVrVentaConIva                 "
                + "    FROM (SELECT   tbldctosordenes.idtipoorden AS idtipoorden,             "
                + "                   tbldctosordenes.idusuario AS idusuario,                 "
                + "                   ctrlusuarios.nombreusuario AS nombreusuario,            "
                + "                   tbldctosordenes.fechaorden AS fechaorden,               "
                + "                   tbldctosordenes.idorden AS idorden,                     "
                + "                   SUM (  tbldctosordenesdetalle.cantidad                  "
                + "                      * tbldctosordenesdetalle.pesoteorico) AS pesoteorico,"
                + "                   SUM ( tblDctosOrdenesDetalle.cantidad         *         "
                + "                                 tblDctosOrdenesDetalle.vrVentaUnitario  * "
                + "                      (1 - (tblDctosOrdenesDetalle.porcentajeDscto/100)) * "
                + "                      (1 + (tblDctosOrdenesDetalle.porcentajeIva/100)))    "
                + "                                                       AS vrVentaConIva    "
                + "          FROM tbldctosordenes, tbldctosordenesdetalle, ctrlusuarios       "
                + "          WHERE tbldctosordenes.idorden = tbldctosordenesdetalle.idorden   "
                + "          AND tbldctosordenes.idtipoorden =                                "
                + "                                    tbldctosordenesdetalle.idtipoorden     "
                + "          AND tbldctosordenes.idlocal = tbldctosordenesdetalle.idlocal     "
                + "          AND   tblDctosOrdenes.IDTIPOORDEN  =                             "
                + getIdTipoOrden() + "                                                        "
                + "          AND   tblDctosOrdenes.FECHAORDEN  BETWEEN                       '"
                + getFechaInicialSqlServer() + "'  AND '" + getFechaFinalSqlServer() + "'    "
                + "          AND ctrlusuarios.idusuario = tbldctosordenes.idusuario           "
                + "          GROUP BY tbldctosordenes.idtipoorden,                            "
                + "                   tbldctosordenes.idusuario,                              "
                + "                   ctrlusuarios.nombreusuario,                             "
                + "                   tbldctosordenes.fechaorden,                             "
                + "                   tbldctosordenes.idorden ) AS tmpDcto                    "
                + "GROUP BY tmpDcto.idtipoorden,                                              "
                + "         tmpDcto.idusuario,                                                "
                + "         tmpDcto.nombreusuario,                                            "
                + "         tmpDcto.fechaOrden                                                "
                + "ORDER BY tmpDcto.idtipoorden,                                              "
                + "         tmpDcto.nombreusuario                                             ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaColaboraReporteDctoBean fachadaBean;

            while (rs.next()) {

                fachadaBean = new FachadaColaboraReporteDctoBean();

                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setIdUsuario(rs.getDouble("idUsuario"));
                fachadaBean.setNombreUsuario(rs.getString("nombreUsuario"));
                fachadaBean.setFechaOrden(rs.getString("fechaOrden").substring(0, 10));
                fachadaBean.setTotalOrdenes(rs.getInt("totalOrdenes"));
                fachadaBean.setTotalPesoTeorico(rs.getDouble("totalPesoTeorico"));
                fachadaBean.setTotalVrVentaConIva(rs.getDouble("totalVrVentaConIva"));

                //
                contenedor.add(fachadaBean);

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

    // Metodo listaAllRemision
    public Vector listaAllRemision() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        boolean validaOk = false;
        Connection connection = null;

        String selectStr
                = " SELECT tblDctos.idLocal,                             "
                + "        tblDctos.idTipoOrden,                       "
                + "        tblDctos.idOrden,                           "
                + "        tblDctos.idDcto,                            "
                + "        tblDctos.idCliente,                         "
                + "        tblDctos.fechaDcto,                         "
                + "        tblDctosOrdenesDetalle.cantidad,            "
                + "        tblDctosOrdenesDetalle.idLocalOrigen,       "
                + "        tblDctosOrdenesDetalle.idTipoOrdenOrigen,   "
                + "        tblDctosOrdenesDetalle.idOrdenOrigen,       "
                + "        tblTerceros.nombreTercero,                  "
                + "        tmpREM.referenciaCliente,                   "
                + "        tmpREM.numeroOrden                          "
                + " FROM   tblDctos                                    "
                + " INNER JOIN tblDctosOrdenesDetalle                  "
                + " ON tblDctos.IDLOCAL                   =            "
                + "             tblDctosOrdenesDetalle.IDLOCAL         "
                + " AND tblDctos.IDTIPOORDEN              =            "
                + "         tblDctosOrdenesDetalle.IDTIPOORDEN         "
                + " AND tblDctos.IDORDEN                  =            "
                + "             tblDctosOrdenesDetalle.IDORDEN         "
                + " INNER JOIN tblDctosOrdenes                         "
                + " ON tblDctosOrdenesDetalle.IDTIPOORDEN =            "
                + "                tblDctosOrdenes.IDTIPOORDEN         "
                + " AND tblDctosOrdenesDetalle.IDORDEN    =            "
                + "                    tblDctosOrdenes.IDORDEN         "
                + " AND tblDctosOrdenesDetalle.IDLOCAL    =            "
                + "                    tblDctosOrdenes.IDLOCAL         "
                + " INNER JOIN tblTerceros                             "
                + " ON tblDctos.idCliente                 =            "
                + "                      tblTerceros.idCliente         "
                + " INNER JOIN                                         "
                + "   (SELECT tblDctosOrdenes.IDLOCAL,                 "
                + "           tblDctosOrdenes.IDTIPOORDEN,             "
                + "           tblDctosOrdenes.IDORDEN,                 "
                + "           tblDctosOrdenes.numeroOrden,             "
                + "           tblPlusFicha.nombreReferencia,           "
                + "           tblPlusFicha.referenciaCliente,          "
                + "           tblPlusFicha.referencia                  "
                + " FROM   tblDctosOrdenes                             "
                + " INNER JOIN tblPlusFicha                            "
                + " ON tblDctosOrdenes.idCliente   =                   "
                + "                   tblPlusFicha.idCliente           "
                + " AND tblDctosOrdenes.idFicha    =                   "
                + "                     tblPlusFicha.idFicha           "
                + " GROUP BY tblDctosOrdenes.IDLOCAL,                  "
                + "          tblDctosOrdenes.IDTIPOORDEN,              "
                + "          tblDctosOrdenes.IDORDEN,                  "
                + "        tblDctosOrdenes.numeroOrden,                "
                + "          tblPlusFicha.nombreReferencia,            "
                + "          tblPlusFicha.referenciaCliente,           "
                + "          tblPlusFicha.referencia )                 "
                + "                               AS tmpREM            "
                + " ON  tmpREM.IDLOCAL                    =            "
                + "       tblDctosOrdenesDetalle.idLocalOrigen         "
                + " AND tmpREM.IDTIPOORDEN                =            "
                + "   tblDctosOrdenesDetalle.idTipoOrdenOrigen         "
                + " AND tmpREM.IDORDEN                    =            "
                + "       tblDctosOrdenesDetalle.idOrdenOrigen         "
                + " WHERE  tblDctosOrdenesDetalle.idLocal       =      "
                + getIdLocal() + "                                     "
                + " AND    tblDctosOrdenesDetalle.idTipoOrden   =      "
                + getIdTipoOrden() + "                                 "
                + " AND tblDctosOrdenesDetalle.cantidad         > 0    "
                + " AND tblTerceros.idTipoTercero               = 1    "
                + " AND tblDctos.fechaDcto BETWEEN                    '"
                + getFechaInicialSqlServer() + "'  AND                '"
                + getFechaFinalSqlServer() + "'                        "
                + " ORDER BY tblTerceros.nombreTercero,                "
                + "          tblDctos.idDcto                           ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaColaboraReporteDctoBean fachadaBean;

            while (rs.next()) {

                //
                fachadaBean = new FachadaColaboraReporteDctoBean();

                //
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setIdOrden(rs.getInt("idOrden"));
                fachadaBean.setIdDcto(rs.getInt("idDcto"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setFechaDcto(rs.getString("fechaDcto"));
                fachadaBean.setCantidad(rs.getDouble("cantidad"));
                fachadaBean.setNombreTercero(rs.getString("nombreTercero"));
                fachadaBean.setReferenciaCliente(rs.getString("referenciaCliente"));
                fachadaBean.setNumeroOrden(rs.getInt("numeroOrden"));

                //
                contenedor.add(fachadaBean);

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

// Metodo listaUnRemision
    public Vector listaUnRemision() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        boolean validaOk = false;
        Connection connection = null;

        String selectStr
                = " SELECT tblDctos.idDcto,                              "
                + "        tblDctos.idCliente,                         "
                + "        tblDctos.fechaDcto,                         "
                + "        tblDctosOrdenesDetalle.cantidadEntregada,   "
                + "        tblDctosOrdenesDetalle.idLocalOrigen,       "
                + "        tblDctosOrdenesDetalle.idTipoOrdenOrigen,   "
                + "        tblDctosOrdenesDetalle.idOrdenOrigen,       "
                + "        tblTerceros.nombreTercero,                  "
                + "        tmpREM.referenciaCliente,                   "
                + "        tmpREM.numeroOrden                          "
                + " FROM   tblDctos                                    "
                + " INNER JOIN tblDctosOrdenesDetalle                  "
                + " ON tblDctos.IDLOCAL                   =            "
                + "             tblDctosOrdenesDetalle.IDLOCAL         "
                + " AND tblDctos.IDTIPOORDEN              =            "
                + "         tblDctosOrdenesDetalle.IDTIPOORDEN         "
                + " AND tblDctos.IDORDEN                  =            "
                + "             tblDctosOrdenesDetalle.IDORDEN         "
                + " INNER JOIN tblDctosOrdenes                         "
                + " ON tblDctosOrdenesDetalle.IDTIPOORDEN =            "
                + "                tblDctosOrdenes.IDTIPOORDEN         "
                + " AND tblDctosOrdenesDetalle.IDORDEN    =            "
                + "                    tblDctosOrdenes.IDORDEN         "
                + " AND tblDctosOrdenesDetalle.IDLOCAL    =            "
                + "                    tblDctosOrdenes.IDLOCAL         "
                + " INNER JOIN tblTerceros                             "
                + " ON tblDctos.idCliente                 =            "
                + "                      tblTerceros.idCliente         "
                + " INNER JOIN                                         "
                + "   (SELECT tblDctosOrdenes.IDLOCAL,                 "
                + "           tblDctosOrdenes.IDTIPOORDEN,             "
                + "           tblDctosOrdenes.IDORDEN,                 "
                + "           tblDctosOrdenes.numeroOrden,             "
                + "           tblPlusFicha.nombreReferencia,           "
                + "           tblPlusFicha.referenciaCliente,          "
                + "           tblPlusFicha.referencia                  "
                + " FROM   tblDctosOrdenes                             "
                + " INNER JOIN tblPlusFicha                            "
                + " ON tblDctosOrdenes.idCliente   =                   "
                + "                   tblPlusFicha.idCliente           "
                + " AND tblDctosOrdenes.idFicha    =                   "
                + "                     tblPlusFicha.idFicha           "
                + " GROUP BY tblDctosOrdenes.IDLOCAL,                  "
                + "          tblDctosOrdenes.IDTIPOORDEN,              "
                + "          tblDctosOrdenes.IDORDEN,                  "
                + "        tblDctosOrdenes.numeroOrden,                "
                + "          tblPlusFicha.nombreReferencia,            "
                + "          tblPlusFicha.referenciaCliente,           "
                + "          tblPlusFicha.referencia )                 "
                + "                               AS tmpREM            "
                + " ON  tmpREM.IDLOCAL                    =            "
                + "       tblDctosOrdenesDetalle.idLocalOrigen         "
                + " AND tmpREM.IDTIPOORDEN                =            "
                + "   tblDctosOrdenesDetalle.idTipoOrdenOrigen         "
                + " AND tmpREM.IDORDEN                    =            "
                + "       tblDctosOrdenesDetalle.idOrdenOrigen         "
                + " WHERE  tblDctosOrdenesDetalle.idLocal       =      "
                + getIdLocal() + "                                     "
                + " AND    tblDctosOrdenesDetalle.idTipoOrden   =      "
                + getIdTipoOrden() + "                                 "
                + " AND tblDctosOrdenesDetalle.cantidadEntregada > 0   "
                + " AND tblTerceros.idTipoTercero               = 1    "
                + " AND tblDctos.fechaDcto BETWEEN                    '"
                + getFechaInicialSqlServer() + "'  AND                '"
                + getFechaFinalSqlServer() + "'                        "
                + " AND tblTerceros.idCliente                   =     '"
                + getIdCliente() + "'                                  "
                + " ORDER BY tblTerceros.nombreTercero,                "
                + "          tblDctos.idDcto                           ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaColaboraReporteDctoBean fachadaBean;

            while (rs.next()) {

                //
                fachadaBean = new FachadaColaboraReporteDctoBean();

                //
                fachadaBean.setIdDcto(rs.getInt("idDcto"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setFechaDcto(rs.getString("fechaDcto"));
                fachadaBean.setCantidad(rs.getDouble("cantidadEntregada"));
                fachadaBean.setNombreTercero(rs.getString("nombreTercero"));
                fachadaBean.setReferenciaCliente(rs.getString("referenciaCliente"));
                fachadaBean.setNumeroOrden(rs.getInt("numeroOrden"));

                //
                contenedor.add(fachadaBean);

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
