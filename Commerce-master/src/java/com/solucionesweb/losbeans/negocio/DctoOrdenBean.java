package com.solucionesweb.losbeans.negocio;

// Importa la clase helper JDBCAccess que contiene los accesos a la base de datos
import com.solucionesweb.lasayudas.JDBCAccess;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaDctoOrdenBean;

// Importa los paquetes de java
import java.sql.*;
import java.io.*;

//import java.util.Date;
import java.util.Vector;
import java.io.IOException.*;

// Importa los paquetes de javax
import javax.naming.*;

// Importa el bean de utilidades
import com.solucionesweb.losbeans.utilidades.IConstantes;

public class DctoOrdenBean extends FachadaDctoOrdenBean
        implements Serializable, IConstantes {

    // Nombre JNDI del Data Source que esta clase requiere
    private static final String DATA_SOURCE_NAME = "CommerceAccess";
    // Clase helper usada para acceder a la base de datos
    private JDBCAccess jdbcAccess;

    // Metodo constructor por defecto sin parametros
    public DctoOrdenBean() {

        // Al instanciar el Bean establece la conexion a la base de datos
        jdbcAccess = new JDBCAccess(DATA_SOURCE_NAME);
    }

    // listaDctoOrdenIdLog
    public FachadaDctoOrdenBean listaDctoOrdenIdLog() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        //
        FachadaDctoOrdenBean fachadaBean = new FachadaDctoOrdenBean();

        Connection connection = null;

        String selectStr =
                "SELECT tblDctosOrdenes.idLocal,           "
                + "       tblDctosOrdenes.idTipoOrden,       "
                + "       tblDctosOrdenes.idOrden,           "
                + "       tblDctosOrdenes.fechaOrden,        "
                + "       tblDctosOrdenes.estado,            "
                + "       tblDctosOrdenes.idCliente,         "
                + "       tblDctosOrdenes.idUsuario,         "
                + "       tblDctosOrdenes.idOrigen,          "
                + "       tblDctosOrdenes.idLog,             "
                + "       tblDctosOrdenes.fechaEntrega,      "
                + "       tblDctosOrdenes.tipoDcto,          "
                + "       tblDctosOrdenes.direccionDespacho, "
                + "       tblDctosOrdenes.email,             "
                + "       tblDctosOrdenes.fax,               "
                + "       tblDctosOrdenes.contacto,          "
                + "       tblDctosOrdenes.observacion,       "
                + "       tblDctosOrdenes.ciudadDespacho,    "
                + "       tblDctosOrdenes.formaPago,         "
                + "       tblDctosOrdenes.ordenCompra,       "
                + "       tblDctosOrdenes.descuentoComercial,"
                + "       tblDctosOrdenes.impuestoVenta,     "
                + "       tblDctosOrdenes.idRazon,           "
                + "       tblDctosOrdenes.numeroOrden,       "
                + "       tblDctosOrdenes.idFicha,           "
                + "       tblDctosOrdenes.idVendedor         "
                + "FROM tblDctosOrdenes                      "
                + "WHERE tblDctosOrdenes.idLog =             "
                + getIdLog();

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
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setIdOrden(rs.getInt("idOrden"));
                fachadaBean.setFechaOrden(rs.getString("fechaOrden"));
                fachadaBean.setEstado(rs.getInt("estado"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setIdUsuario(rs.getDouble("idUsuario"));
                fachadaBean.setIdOrigen(rs.getInt("idOrigen"));
                fachadaBean.setIdLog(rs.getInt("idLog"));
                fachadaBean.setFechaEntrega(rs.getString("fechaEntrega"));
                fachadaBean.setTipoDcto(rs.getString("tipoDcto"));
                fachadaBean.setDireccionDespacho(
                        rs.getString("direccionDespacho"));
                fachadaBean.setEmail(rs.getString("email"));
                fachadaBean.setFax(rs.getString("fax"));
                fachadaBean.setContacto(rs.getString("contacto"));
                fachadaBean.setObservacion(rs.getString("observacion"));
                fachadaBean.setCiudadDespacho(rs.getString("ciudadDespacho"));
                fachadaBean.setFormaPago(rs.getString("formaPago"));
                fachadaBean.setOrdenCompra(rs.getString("ordenCompra"));
                fachadaBean.setDescuentoComercial(
                        rs.getDouble("descuentoComercial"));
                fachadaBean.setImpuestoVenta(rs.getInt("impuestoVenta"));
                fachadaBean.setIdRazon(rs.getString("idRazon"));
                fachadaBean.setNumeroOrden(rs.getInt("numeroOrden"));
                fachadaBean.setIdFicha(rs.getInt("idFicha"));
                fachadaBean.setIdVendedor(rs.getInt("idVendedor"));

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
            return fachadaBean;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {

            // Cierra la conexion
            jdbcAccess.cleanup(connection, selectStatement, null);
            return fachadaBean;

        }
    }

    // listaUltimoPrecioVenta
    public double listaUltimoPrecioVenta() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        //
        double xVrVentaUnitarioSinIva = 0.0;

        Connection connection = null;

        String selectStr =
                " SELECT TOP 1                                           "
                + "        tblDctosOrdenesDetalle.vrVentaUnitarioSinIva  "
                + " FROM   tblDctosOrdenes                               "
                + " INNER JOIN tblDctosOrdenesDetalle                    "
                + " ON tblDctosOrdenes.IDLOCAL      =                    "
                + "       tblDctosOrdenesDetalle.IDLOCAL                 "
                + " AND tblDctosOrdenes.IDTIPOORDEN =                    "
                + "   tblDctosOrdenesDetalle.IDTIPOORDEN                 "
                + " AND tblDctosOrdenes.IDORDEN     =                    "
                + "       tblDctosOrdenesDetalle.IDORDEN                 "
                + " WHERE tblDctosOrdenes.idFicha   =                    "
                + getIdFicha() + "                                       "
                + " AND   tblDctosOrdenes.idCliente =                   '"
                + getIdCliente() + "'                                    "
                + " AND   tblDctosOrdenesDetalle.vrVentaUnitarioSinIva   "
                + "                                        IS NOT NULL   "
                + " ORDER BY tblDctosOrdenes.idLocal,                    "
                + "          tblDctosOrdenes.idTipoOrden,                "
                + "          tblDctosOrdenes.idOrden                     ";

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
                xVrVentaUnitarioSinIva =
                        rs.getDouble("vrVentaUnitarioSinIva");


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
            return xVrVentaUnitarioSinIva;

        }
    }

    // listaOrdenFCH
    public FachadaDctoOrdenBean listaOrdenFCH() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        //
        FachadaDctoOrdenBean fachadaBean = new FachadaDctoOrdenBean();

        Connection connection = null;

        String selectStr =
                "SELECT tblDctosOrdenes.idLocal,           "
                + "       tblDctosOrdenes.idTipoOrden,       "
                + "       tblDctosOrdenes.idOrden,           "
                + "       tblDctosOrdenes.fechaOrden,        "
                + "       tblDctosOrdenes.estado,            "
                + "       tblDctosOrdenes.idCliente,         "
                + "       tblDctosOrdenes.idUsuario,         "
                + "       tblDctosOrdenes.idOrigen,          "
                + "       tblDctosOrdenes.idLog,             "
                + "       tblDctosOrdenes.fechaEntrega,      "
                + "       tblDctosOrdenes.tipoDcto,          "
                + "       tblDctosOrdenes.direccionDespacho, "
                + "       tblDctosOrdenes.email,             "
                + "       tblDctosOrdenes.fax,               "
                + "       tblDctosOrdenes.contacto,          "
                + "       tblDctosOrdenes.observacion,       "
                + "       tblDctosOrdenes.ciudadDespacho,    "
                + "       tblDctosOrdenes.formaPago,         "
                + "       tblDctosOrdenes.ordenCompra,       "
                + "       tblDctosOrdenes.descuentoComercial,"
                + "       tblDctosOrdenes.impuestoVenta,     "
                + "       tblDctosOrdenes.idRazon,           "
                + "       tblDctosOrdenes.diasHistoria,      "
                + "       tblDctosOrdenes.diasInventario,    "
                + "       tblDctosOrdenes.idBodegaOrigen,    "
                + "       tblDctosOrdenes.idBodegaDestino,   "
                + "       tblDctosOrdenes.idFicha,           "
                + "       tblDctosOrdenes.numeroOrden        "
                + "FROM tblDctosOrdenes                      "
                + "WHERE tblDctosOrdenes.idLocal     =       "
                + getIdLocal() + "                           "
                + "AND   tblDctosOrdenes.idTipoOrden =       "
                + getIdTipoOrden() + "                       "
                + "AND   tblDctosOrdenes.idLog =             "
                + getIdLog();

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


                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setIdOrden(rs.getInt("idOrden"));
                fachadaBean.setFechaOrden(rs.getString("fechaOrden"));
                fachadaBean.setEstado(rs.getInt("estado"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setIdUsuario(rs.getDouble("idUsuario"));
                fachadaBean.setIdOrigen(rs.getInt("idOrigen"));
                fachadaBean.setIdLog(rs.getInt("idLog"));
                fachadaBean.setFechaEntrega(rs.getString("fechaEntrega"));
                fachadaBean.setTipoDcto(rs.getString("tipoDcto"));
                fachadaBean.setDireccionDespacho(
                        rs.getString("direccionDespacho"));
                fachadaBean.setEmail(rs.getString("email"));
                fachadaBean.setFax(rs.getString("fax"));
                fachadaBean.setContacto(rs.getString("contacto"));
                fachadaBean.setObservacion(rs.getString("observacion"));
                fachadaBean.setCiudadDespacho(rs.getString("ciudadDespacho"));
                fachadaBean.setFormaPago(rs.getString("formaPago"));
                fachadaBean.setOrdenCompra(rs.getString("ordenCompra"));
                fachadaBean.setDescuentoComercial(
                        rs.getDouble("descuentoComercial"));
                fachadaBean.setImpuestoVenta(rs.getInt("impuestoVenta"));
                fachadaBean.setIdRazon(rs.getString("idRazon"));
                fachadaBean.setDiasHistoria(rs.getInt("diasHistoria"));
                fachadaBean.setDiasInventario(rs.getInt("diasInventario"));
                fachadaBean.setIdBodegaOrigen(rs.getInt("idBodegaOrigen"));
                fachadaBean.setIdBodegaDestino(rs.getInt("idBodegaDestino"));
                fachadaBean.setIdFicha(rs.getInt("idFicha"));
                fachadaBean.setNumeroOrden(rs.getInt("numeroOrden"));

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
            return fachadaBean;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {

            // Cierra la conexion
            jdbcAccess.cleanup(connection, selectStatement, null);
            return fachadaBean;

        }
    }

    // listaDctoOrdenIdLogIdTipoOrden
    public FachadaDctoOrdenBean listaDctoOrdenIdLogIdTipoOrden() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        //
        FachadaDctoOrdenBean fachadaBean = new FachadaDctoOrdenBean();

        Connection connection = null;

        String selectStr =
                "SELECT tblDctosOrdenes.idLocal,           "
                + "       tblDctosOrdenes.idTipoOrden,       "
                + "       tblDctosOrdenes.idOrden,           "
                + "       tblDctosOrdenes.fechaOrden,        "
                + "       tblDctosOrdenes.estado,            "
                + "       tblDctosOrdenes.idCliente,         "
                + "       tblDctosOrdenes.idUsuario,         "
                + "       tblDctosOrdenes.idOrigen,          "
                + "       tblDctosOrdenes.idLog,             "
                + "       tblDctosOrdenes.fechaEntrega,      "
                + "       tblDctosOrdenes.tipoDcto,          "
                + "       tblDctosOrdenes.direccionDespacho, "
                + "       tblDctosOrdenes.email,             "
                + "       tblDctosOrdenes.fax,               "
                + "       tblDctosOrdenes.contacto,          "
                + "       tblDctosOrdenes.observacion,       "
                + "       tblDctosOrdenes.ciudadDespacho,    "
                + "       tblDctosOrdenes.formaPago,         "
                + "       tblDctosOrdenes.ordenCompra,       "
                + "       tblDctosOrdenes.descuentoComercial,"
                + "       tblDctosOrdenes.impuestoVenta,     "
                + "       tblDctosOrdenes.idRazon,           "
                + "       tblDctosOrdenes.idEstadoTx,        "
                + "       tblDctosOrdenes.idTipoTx,          "
                + "       tblDctosOrdenes.numeroOrden,       "
                + "       tblDctosOrdenes.idResponsable,     "
                + "       tblDctosOrdenes.diasHistoria,      "
                + "       tblDctosOrdenes.diasInventario,    "
                + "       tblDctosOrdenes.idFicha,           "
                + "       tblDctosOrdenes.idVendedor         "
                + "FROM tblDctosOrdenes                      "
                + "WHERE tblDctosOrdenes.idLog       =       "
                + getIdLog() + "                             "
                + "AND   tblDctosOrdenes.idTipoOrden =       "
                + getIdTipoOrden();

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
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setIdOrden(rs.getInt("idOrden"));
                fachadaBean.setFechaOrden(rs.getString("fechaOrden"));
                fachadaBean.setEstado(rs.getInt("estado"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setIdUsuario(rs.getDouble("idUsuario"));
                fachadaBean.setIdOrigen(rs.getInt("idOrigen"));
                fachadaBean.setIdLog(rs.getInt("idLog"));
                fachadaBean.setFechaEntrega(rs.getString("fechaEntrega"));
                fachadaBean.setTipoDcto(rs.getString("tipoDcto"));
                fachadaBean.setDireccionDespacho(
                        rs.getString("direccionDespacho"));
                fachadaBean.setEmail(rs.getString("email"));
                fachadaBean.setFax(rs.getString("fax"));
                fachadaBean.setContacto(rs.getString("contacto"));
                fachadaBean.setObservacion(rs.getString("observacion"));
                fachadaBean.setCiudadDespacho(rs.getString("ciudadDespacho"));
                fachadaBean.setFormaPago(rs.getString("formaPago"));
                fachadaBean.setOrdenCompra(rs.getString("ordenCompra"));
                fachadaBean.setDescuentoComercial(
                        rs.getDouble("descuentoComercial"));
                fachadaBean.setImpuestoVenta(rs.getInt("impuestoVenta"));
                fachadaBean.setIdRazon(rs.getString("idRazon"));
                fachadaBean.setIdEstadoTx(rs.getInt("idEstadoTx"));
                fachadaBean.setIdTipoTx(rs.getInt("idTipoTx"));
                fachadaBean.setNumeroOrden(rs.getInt("numeroOrden"));
                fachadaBean.setIdResponsable(rs.getInt("idResponsable"));
                fachadaBean.setDiasHistoria(rs.getInt("diasHistoria"));
                fachadaBean.setDiasInventario(rs.getInt("diasInventario"));
                fachadaBean.setIdFicha(rs.getInt("IdFicha"));
                fachadaBean.setIdVendedor(rs.getDouble("idVendedor"));

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
            return fachadaBean;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {

            // Cierra la conexion
            jdbcAccess.cleanup(connection, selectStatement, null);
            return fachadaBean;
        }
    }

    // listaDctoOrdenLog
    public FachadaDctoOrdenBean listaDctoOrdenLog() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        //
        FachadaDctoOrdenBean fachadaBean = new FachadaDctoOrdenBean();

        Connection connection = null;

        String selectStr =
                "SELECT tblDctosOrdenes.idLocal,           "
                + "       tblDctosOrdenes.idTipoOrden,       "
                + "       tblDctosOrdenes.idOrden,           "
                + "       tblDctosOrdenes.fechaOrden,        "
                + "       tblDctosOrdenes.estado,            "
                + "       tblDctosOrdenes.idCliente,         "
                + "       tblDctosOrdenes.idUsuario,         "
                + "       tblDctosOrdenes.idOrigen,          "
                + "       tblDctosOrdenes.idLog,             "
                + "       tblDctosOrdenes.fechaEntrega,      "
                + "       tblDctosOrdenes.tipoDcto,          "
                + "       tblDctosOrdenes.direccionDespacho, "
                + "       tblDctosOrdenes.email,             "
                + "       tblDctosOrdenes.fax,               "
                + "       tblDctosOrdenes.contacto,          "
                + "       tblDctosOrdenes.observacion,       "
                + "       tblDctosOrdenes.ciudadDespacho,    "
                + "       tblDctosOrdenes.formaPago,         "
                + "       tblDctosOrdenes.ordenCompra,       "
                + "       tblDctosOrdenes.descuentoComercial,"
                + "       tblDctosOrdenes.impuestoVenta,     "
                + "       tblDctosOrdenes.idRazon,           "
                + "       tblDctosOrdenes.idEstadoTx,        "
                + "       tblDctosOrdenes.idTipoTx,          "
                + "       tblDctosOrdenes.numeroOrden,       "
                + "       tblDctosOrdenes.idResponsable,     "
                + "       tblDctosOrdenes.diasHistoria,      "
                + "       tblDctosOrdenes.diasInventario     "
                + "FROM tblDctosOrdenes                      "
                + "WHERE tblDctosOrdenes.idLog       =       "
                + getIdLog() + "                             "
                + "AND   tblDctosOrdenes.idTipoOrden =       "
                + getIdTipoOrden() + "                       "
                + "AND   tblDctosOrdenes.idLocal     =       "
                + getIdLocal();

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
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setIdOrden(rs.getInt("idOrden"));
                fachadaBean.setFechaOrden(rs.getString("fechaOrden"));
                fachadaBean.setEstado(rs.getInt("estado"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setIdUsuario(rs.getDouble("idUsuario"));
                fachadaBean.setIdOrigen(rs.getInt("idOrigen"));
                fachadaBean.setIdLog(rs.getInt("idLog"));
                fachadaBean.setFechaEntrega(rs.getString("fechaEntrega"));
                fachadaBean.setTipoDcto(rs.getString("tipoDcto"));
                fachadaBean.setDireccionDespacho(
                        rs.getString("direccionDespacho"));
                fachadaBean.setEmail(rs.getString("email"));
                fachadaBean.setFax(rs.getString("fax"));
                fachadaBean.setContacto(rs.getString("contacto"));
                fachadaBean.setObservacion(rs.getString("observacion"));
                fachadaBean.setCiudadDespacho(rs.getString("ciudadDespacho"));
                fachadaBean.setFormaPago(rs.getString("formaPago"));
                fachadaBean.setOrdenCompra(rs.getString("ordenCompra"));
                fachadaBean.setDescuentoComercial(
                        rs.getDouble("descuentoComercial"));
                fachadaBean.setImpuestoVenta(rs.getInt("impuestoVenta"));
                fachadaBean.setIdRazon(rs.getString("idRazon"));
                fachadaBean.setIdEstadoTx(rs.getInt("idEstadoTx"));
                fachadaBean.setIdTipoTx(rs.getInt("idTipoTx"));
                fachadaBean.setNumeroOrden(rs.getInt("numeroOrden"));
                fachadaBean.setIdResponsable(rs.getInt("idResponsable"));
                fachadaBean.setDiasHistoria(rs.getInt("diasHistoria"));
                fachadaBean.setDiasInventario(rs.getInt("diasInventario"));

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
            return fachadaBean;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {

            // Cierra la conexion
            jdbcAccess.cleanup(connection, selectStatement, null);
            return fachadaBean;
        }
    }

    // listaOrdenResponsable
    public boolean existeOrdenResponsable() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        //
        boolean validaOk = false;
        Connection connection = null;

        String selectStr =
                "SELECT tblDctosOrdenes.idLocal,           "
                + "       tblDctosOrdenes.idTipoOrden,       "
                + "       tblDctosOrdenes.idOrden,           "
                + "       tblDctosOrdenes.fechaOrden,        "
                + "       tblDctosOrdenes.estado,            "
                + "       tblDctosOrdenes.idCliente,         "
                + "       tblDctosOrdenes.idUsuario,         "
                + "       tblDctosOrdenes.idOrigen,          "
                + "       tblDctosOrdenes.idLog,             "
                + "       tblDctosOrdenes.fechaEntrega,      "
                + "       tblDctosOrdenes.tipoDcto,          "
                + "       tblDctosOrdenes.direccionDespacho, "
                + "       tblDctosOrdenes.email,             "
                + "       tblDctosOrdenes.fax,               "
                + "       tblDctosOrdenes.contacto,          "
                + "       tblDctosOrdenes.observacion,       "
                + "       tblDctosOrdenes.ciudadDespacho,    "
                + "       tblDctosOrdenes.formaPago,         "
                + "       tblDctosOrdenes.ordenCompra,       "
                + "       tblDctosOrdenes.descuentoComercial,"
                + "       tblDctosOrdenes.impuestoVenta,     "
                + "       tblDctosOrdenes.idRazon            "
                + "FROM tblDctosOrdenes                      "
                + "INNER JOIN tblDctosOrdenesDetalle         "
                + "ON tblDctosOrdenes.IDORDEN                 =       "
                + "                tblDctosOrdenesDetalle.IDORDEN     "
                + "AND tblDctosOrdenes.IDTIPOORDEN            =       "
                + "            tblDctosOrdenesDetalle.IDTIPOORDEN     "
                + "AND tblDctosOrdenes.IDLOCAL                =       "
                + "                tblDctosOrdenesDetalle.IDLOCAL     "
                + "WHERE tblDctosOrdenesDetalle.idResponsable = ( ? ) "
                + "AND tblDctosOrdenes.idTipoOrden            = ( ? ) "
                + "AND tblDctosOrdenes.idLog                  = ( ? ) ";


        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            //
            selectStatement.setInt(1, getIdResponsable());
            selectStatement.setInt(2, getIdTipoOrden());
            selectStatement.setInt(3, getIdLog());


            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            //
            if (rs.next()) {

                validaOk = true;

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
            return validaOk;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {

            // Cierra la conexion
            jdbcAccess.cleanup(connection, selectStatement, null);
            return validaOk;

        }
    }

    // existeFichaOT
    public boolean existeFichaOT() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        //
        boolean validaOk = false;
        Connection connection = null;

        String selectStr =
                " SELECT tblDctosOrdenes.idFicha                      "
                + " FROM   tblDctosOrdenes                            "
                + " INNER JOIN tblDctosOrdenesDetalle                 "
                + " ON tblDctosOrdenes.IDLOCAL      =                 "
                + "         tblDctosOrdenesDetalle.IDLOCAL            "
                + " AND tblDctosOrdenes.IDTIPOORDEN =                 "
                + "     tblDctosOrdenesDetalle.IDTIPOORDEN            "
                + " AND tblDctosOrdenes.IDORDEN =                     "
                + "         tblDctosOrdenesDetalle.IDORDEN            "
                + " WHERE                                             "
                + "  (tblDctosOrdenesDetalle.idEstadoRefOriginal      "
                + "                                         != 9)     "
                + " AND tblDctosOrdenes.idFicha              =        "
                + getIdFicha() + "                                    "
                + " AND EXISTS (                                      "
                + "   SELECT tblDctosOrdenesProgreso.*                "
                + "   FROM tblDctosOrdenesProgreso                    "
                + "   WHERE tblDctosOrdenes.idLocal     =             "
                + "                tblDctosOrdenesProgreso.idLocal    "
                + "   AND   tblDctosOrdenes.idTipoOrden =             "
                + "            tblDctosOrdenesProgreso.idTipoOrden    "
                + "   AND   tblDctosOrdenes.idOrden     =             "
                + "               tblDctosOrdenesProgreso.idOrden )   ";


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

                validaOk = true;

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
            return validaOk;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {

            // Cierra la conexion
            jdbcAccess.cleanup(connection, selectStatement, null);
            return validaOk;

        }
    }

    // existePedido
    public boolean existePedido() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        boolean validaOk = false;
        Connection connection = null;

        String selectStr =
                "SELECT tblDctosOrdenes.*              "
                + "FROM tblDctosOrdenes                "
                + "WHERE tblDctosOrdenes.idLog       = "
                + getIdLog() + "                       "
                + "AND   tblDctosOrdenes.idTipoOrden = "
                + getIdTipoOrden();

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

                validaOk = true;

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
            return validaOk;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {

            // Cierra la conexion
            jdbcAccess.cleanup(connection, selectStatement, null);
            return validaOk;

        }
    }

    // existeOT
    public boolean existeOT() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        boolean validaOk = false;

        //
        int xNumeroOrden = 0;
        Connection connection = null;

        String selectStr =
                "SELECT tblDctosOrdenes.numeroOrden "
                + "FROM tblDctosOrdenes             "
                + "WHERE tblDctosOrdenes.idLog    = "
                + getIdLog() + "                    "
                + "AND   tblDctosOrdenes.idLocal  = "
                + getIdLocal();

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
                xNumeroOrden = rs.getInt("numeroOrden");

                //
                if (xNumeroOrden > 0) {

                    //
                    validaOk = true;
                }
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
            return validaOk;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {

            // Cierra la conexion
            jdbcAccess.cleanup(connection, selectStatement, null);
            return validaOk;

        }
    }

    // existeResurtido
    public boolean existeResurtido() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();


        boolean validaOk = false;
        Connection connection = null;

        String selectStr =
                "SELECT tblDctosOrdenes.*             "
                + "FROM tblDctosOrdenes                 "
                + "WHERE tblDctosOrdenes.idLocal  =     "
                + getIdLocal() + "                      "
                + "AND   tblDctosOrdenes.idLog    =     "
                + getIdLog() + "                        "
                + "AND   tblDctosOrdenes.idTipoOrden =  "
                + getIdTipoOrden() + "                  "
                + "AND EXISTS                           "
                + "   (SELECT tblDctos.*                "
                + "    FROM tblDctos                    "
                + "    WHERE tblDctos.idLocal     =     "
                + "            tblDctosOrdenes.idLocal  "
                + "    AND   tblDctos.idTipoOrden =     "
                + "        tblDctosOrdenes.idTipoOrden  "
                + "    AND   tblDctos.idOrden     =     "
                + "            tblDctosOrdenes.idOrden) ";

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

                validaOk = true;

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
            return validaOk;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {

            // Cierra la conexion
            jdbcAccess.cleanup(connection, selectStatement, null);
            return validaOk;

        }
    }

    // existePedidoIdLocal
    public boolean existePedidoIdLocal() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        //
        FachadaDctoOrdenBean fachadaBean = new FachadaDctoOrdenBean();

        boolean validaOk = false;
        Connection connection = null;

        String selectStr =
                "SELECT tblDctosOrdenes.*                      "
                + "FROM tblDctosOrdenes                          "
                + "WHERE tblDctosOrdenes.idLog           = ( ? ) "
                + "AND   tblDctosOrdenes.idTipoOrden     = ( ? ) "
                + "AND   tblDctosOrdenes.idLocal         = ( ? ) ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            //
            selectStatement.setInt(1, getIdLog());
            selectStatement.setInt(2, getIdTipoOrden());
            selectStatement.setInt(3, getIdLocal());

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            //
            if (rs.next()) {

                validaOk = true;

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
            return validaOk;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {

            // Cierra la conexion
            jdbcAccess.cleanup(connection, selectStatement, null);
            return validaOk;

        }
    }

    // listaDctoOrden
    public FachadaDctoOrdenBean listaDctoOrden() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        //
        FachadaDctoOrdenBean fachadaBean = new FachadaDctoOrdenBean();

        Connection connection = null;

        String selectStr =
                "SELECT tblDctosOrdenes.idLocal,           "
                + "       tblDctosOrdenes.idTipoOrden,       "
                + "       tblDctosOrdenes.idOrden,           "
                + "       tblDctosOrdenes.fechaOrden,        "
                + "       tblDctosOrdenes.estado,            "
                + "       tblDctosOrdenes.idCliente,         "
                + "       tblDctosOrdenes.idUsuario,         "
                + "       tblDctosOrdenes.idOrigen,          "
                + "       tblDctosOrdenes.idLog,             "
                + "       tblDctosOrdenes.fechaEntrega,      "
                + "       tblDctosOrdenes.tipoDcto,          "
                + "       tblDctosOrdenes.direccionDespacho, "
                + "       tblDctosOrdenes.email,             "
                + "       tblDctosOrdenes.fax,               "
                + "       tblDctosOrdenes.contacto,          "
                + "       tblDctosOrdenes.observacion,       "
                + "       tblDctosOrdenes.ciudadDespacho,    "
                + "       tblDctosOrdenes.formaPago,         "
                + "       tblDctosOrdenes.ordenCompra,       "
                + "       tblDctosOrdenes.descuentoComercial,"
                + "       tblDctosOrdenes.impuestoVenta,     "
                + "       tblDctosOrdenes.idRazon,           "
                + "       tblDctosOrdenes.idEstadoTx         "
                + "FROM tblDctosOrdenes                      "
                + "WHERE tblDctosOrdenes.idLocal     =       "
                + getIdLocal() + "                   "
                + "AND   tblDctosOrdenes.idTipoOrden =       "
                + getIdTipoOrden() + "               "
                + "AND   tblDctosOrdenes.idOrden     =       "
                + getIdOrden();

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
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setIdOrden(rs.getInt("idOrden"));
                fachadaBean.setFechaOrden(rs.getString("fechaOrden"));
                fachadaBean.setEstado(rs.getInt("estado"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setIdUsuario(rs.getDouble("idUsuario"));
                fachadaBean.setIdOrigen(rs.getInt("idOrigen"));
                fachadaBean.setIdLog(rs.getInt("idLog"));
                fachadaBean.setFechaEntrega(rs.getString("fechaEntrega"));
                fachadaBean.setTipoDcto(rs.getString("tipoDcto"));
                fachadaBean.setDireccionDespacho(
                        rs.getString("direccionDespacho"));
                fachadaBean.setEmail(rs.getString("email"));
                fachadaBean.setFax(rs.getString("fax"));
                fachadaBean.setContacto(rs.getString("contacto"));
                fachadaBean.setObservacion(rs.getString("observacion"));
                fachadaBean.setCiudadDespacho(rs.getString("ciudadDespacho"));
                fachadaBean.setFormaPago(rs.getString("formaPago"));
                fachadaBean.setOrdenCompra(rs.getString("ordenCompra"));
                fachadaBean.setDescuentoComercial(
                        rs.getDouble("descuentoComercial"));
                fachadaBean.setImpuestoVenta(rs.getInt("impuestoVenta"));
                fachadaBean.setIdRazon(rs.getString("idRazon"));
                fachadaBean.setIdEstadoTx(rs.getInt("idEstadoTx"));

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
            return fachadaBean;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {

            // Cierra la conexion
            jdbcAccess.cleanup(connection, selectStatement, null);
            return fachadaBean;

        }
    }

    // listaDctoNumeroOrden
    public FachadaDctoOrdenBean listaDctoNumeroOrden() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        //
        FachadaDctoOrdenBean fachadaBean = new FachadaDctoOrdenBean();

        Connection connection = null;

        String selectStr =
                "SELECT tblDctosOrdenes.idLocal,           "
                + "       tblDctosOrdenes.idTipoOrden,       "
                + "       tblDctosOrdenes.idOrden,           "
                + "       tblDctosOrdenes.fechaOrden,        "
                + "       tblDctosOrdenes.estado,            "
                + "       tblDctosOrdenes.idCliente,         "
                + "       tblDctosOrdenes.idUsuario,         "
                + "       tblDctosOrdenes.idOrigen,          "
                + "       tblDctosOrdenes.idLog,             "
                + "       tblDctosOrdenes.fechaEntrega,      "
                + "       tblDctosOrdenes.tipoDcto,          "
                + "       tblDctosOrdenes.direccionDespacho, "
                + "       tblDctosOrdenes.email,             "
                + "       tblDctosOrdenes.fax,               "
                + "       tblDctosOrdenes.contacto,          "
                + "       tblDctosOrdenes.observacion,       "
                + "       tblDctosOrdenes.ciudadDespacho,    "
                + "       tblDctosOrdenes.formaPago,         "
                + "       tblDctosOrdenes.ordenCompra,       "
                + "       tblDctosOrdenes.descuentoComercial,"
                + "       tblDctosOrdenes.impuestoVenta,     "
                + "       tblDctosOrdenes.idRazon,           "
                + "       tblDctosOrdenes.idEstadoTx         "
                + "FROM tblDctosOrdenes                      "
                + "WHERE tblDctosOrdenes.idLocal     =       "
                + getIdLocal() + "                           "
                + "AND   tblDctosOrdenes.idTipoOrden =       "
                + getIdTipoOrden() + "                       "
                + "AND   tblDctosOrdenes.numeroOrden =       "
                + getNumeroOrden();

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
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setIdOrden(rs.getInt("idOrden"));
                fachadaBean.setFechaOrden(rs.getString("fechaOrden"));
                fachadaBean.setEstado(rs.getInt("estado"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setIdUsuario(rs.getDouble("idUsuario"));
                fachadaBean.setIdOrigen(rs.getInt("idOrigen"));
                fachadaBean.setIdLog(rs.getInt("idLog"));
                fachadaBean.setFechaEntrega(rs.getString("fechaEntrega"));
                fachadaBean.setTipoDcto(rs.getString("tipoDcto"));
                fachadaBean.setDireccionDespacho(
                        rs.getString("direccionDespacho"));
                fachadaBean.setEmail(rs.getString("email"));
                fachadaBean.setFax(rs.getString("fax"));
                fachadaBean.setContacto(rs.getString("contacto"));
                fachadaBean.setObservacion(rs.getString("observacion"));
                fachadaBean.setCiudadDespacho(rs.getString("ciudadDespacho"));
                fachadaBean.setFormaPago(rs.getString("formaPago"));
                fachadaBean.setOrdenCompra(rs.getString("ordenCompra"));
                fachadaBean.setDescuentoComercial(
                        rs.getDouble("descuentoComercial"));
                fachadaBean.setImpuestoVenta(rs.getInt("impuestoVenta"));
                fachadaBean.setIdRazon(rs.getString("idRazon"));
                fachadaBean.setIdEstadoTx(rs.getInt("idEstadoTx"));

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
            return fachadaBean;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {

            // Cierra la conexion
            jdbcAccess.cleanup(connection, selectStatement, null);
            return fachadaBean;

        }
    }

    // retiraDctoOrden
    public boolean retiraDctoOrden() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Vector contenedor = new Vector();
        Connection connection = null;

        String selectStr =
                "DELETE FROM   tblDctosOrdenes              "
                + "WHERE  tblDctosOrdenes.idLocal     = ( ? ) "
                + "AND    tblDctosOrdenes.idTipoOrden = ( ? ) "
                + "AND    tblDctosOrdenes.idOrden     = ( ? ) ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            //
            selectStatement.setInt(1, getIdLocal());
            selectStatement.setInt(2, getIdTipoOrden());
            selectStatement.setInt(3, getIdOrden());

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

            // Cierra la conexion
            jdbcAccess.cleanup(connection, selectStatement, null);
            return okIngresar;
        }
    }

    // retiraOrdenLog
    public boolean retiraOrdenLog() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                "DELETE FROM   tblDctosOrdenes         "
                + "WHERE  tblDctosOrdenes.idLocal     =  "
                + getIdLocal() + "                       "
                + "AND    tblDctosOrdenes.idLog       =  "
                + getIdLog();

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

            // Cierra la conexion
            jdbcAccess.cleanup(connection, selectStatement, null);
            return okIngresar;
        }
    }

    // retiraOrden
    public boolean retiraOrden() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                "DELETE FROM   tblDctosOrdenes         "
                + "WHERE  tblDctosOrdenes.idLocal     =  "
                + getIdLocal() + "                       "
                + "AND    tblDctosOrdenes.idTipoOrden =  "
                + getIdTipoOrden() + "                   "
                + "AND    tblDctosOrdenes.idLog       =  "
                + getIdLog();

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

            // Cierra la conexion
            jdbcAccess.cleanup(connection, selectStatement, null);
            return okIngresar;
        }
    }

    // actualizaPedido
    public boolean actualizaPedido() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Vector contenedor = new Vector();
        Connection connection = null;

        String selectStr =
                "UPDATE tblDctosOrdenes                        "
                + "SET tblDctosOrdenes.estado            = ( ? ) "
                + "WHERE tblDctosOrdenes.idLocal         = ( ? ) "
                + "AND   tblDctosOrdenes.idTipoOrden     = ( ? ) "
                + "AND   tblDctosOrdenes.idOrden         = ( ? ) ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            //
            selectStatement.setInt(1, getEstado());
            selectStatement.setInt(2, getIdLocal());
            selectStatement.setInt(3, getIdTipoOrden());
            selectStatement.setInt(4, getIdOrden());

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

            // Cierra el connection
            jdbcAccess.cleanup(connection, selectStatement, null);
            return okIngresar;
        }
    }

    // actualizaOT
    public boolean actualizaOT() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                "UPDATE tblDctosOrdenes                  "
                + "SET tblDctosOrdenes.numeroOrden   =   "
                + getNumeroOrden() + "                   "
                + "WHERE tblDctosOrdenes.idLog       =   "
                + getIdLog() + "                         "
                + "AND   tblDctosOrdenes.idLocal     =   "
                + getIdLocal() + "                       "
                + "AND ( tblDctosOrdenes.numeroOrden = 0 "
                + " OR tblDctosOrdenes.numeroOrden       "
                + "                            IS NULL ) ";

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

            // Cierra el connection
            jdbcAccess.cleanup(connection, selectStatement, null);
            return okIngresar;
        }
    }

    // guardaResurtido
    public boolean guardaResurtido() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                "UPDATE tblDctosOrdenes                  "
                + "SET  tblDctosOrdenes.idCliente     = '"
                + getIdCliente() + "',                   "
                + "     tblDctosOrdenes.observacion   = '"
                + getObservacion() + "',                 "
                + "     tblDctosOrdenes.diasHistoria  =  "
                + getDiasHistoria() + ",                 "
                + "   tblDctosOrdenes.diasInventario  =  "
                + getDiasInventario() + ",               "
                + "   tblDctosOrdenes.fechaEntrega    = '"
                + getFechaEntregaSqlServer() + "'        "
                + "WHERE tblDctosOrdenes.IDLOCAL      =  "
                + getIdLocal() + "                       "
                + "AND tblDctosOrdenes.IDTIPOORDEN    =  "
                + getIdTipoOrden() + "                   "
                + "AND   tblDctosOrdenes.IDLOG        =  "
                + getIdLog();

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

            // Cierra el connection
            jdbcAccess.cleanup(connection, selectStatement, null);
            return okIngresar;
        }
    }

    // actualizaDescuento
    public boolean actualizaDescuento() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                "UPDATE tblDctosOrdenes                   "
                + "SET tblDctosOrdenes.descuentoComercial = "
                + getDescuentoComercial() + "               "
                + "WHERE tblDctosOrdenes.idLocal          = "
                + getIdLocal() + "                          "
                + "AND   tblDctosOrdenes.idTipoOrden      = "
                + getIdTipoOrden() + "                      "
                + "AND   tblDctosOrdenes.idLog            = "
                + getIdLog();

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

            // Cierra el connection
            jdbcAccess.cleanup(connection, selectStatement, null);
            return okIngresar;
        }
    }

    // actualizaEstadoTx
    public boolean actualizaEstadoTx() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Vector contenedor = new Vector();
        Connection connection = null;

        String selectStr =
                "UPDATE tblDctosOrdenes                        "
                + "SET tblDctosOrdenes.idEstadoTx        = ( ? ),"
                + "    tblDctosOrdenes.idTipoTx          = ( ? ) "
                + "WHERE tblDctosOrdenes.idLocal         = ( ? ) "
                + "AND   tblDctosOrdenes.idTipoOrden     = ( ? ) "
                + "AND   tblDctosOrdenes.idLog           = ( ? ) ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            //
            selectStatement.setInt(1, getIdEstadoTx());
            selectStatement.setInt(2, getIdTipoTx());
            selectStatement.setInt(3, getIdLocal());
            selectStatement.setInt(4, getIdTipoOrden());
            selectStatement.setInt(5, getIdLog());

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

            // Cierra el connection
            jdbcAccess.cleanup(connection, selectStatement, null);
            return okIngresar;
        }
    }

    // maximaIdOrdenIdLocal
    public int maximaIdOrdenIdLocal() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        //
        int maximaIdOrden = 0;

        boolean validaOk = false;
        Connection connection = null;

        String selectStr =
                "SELECT MAX(tblDctosOrdenes.idOrden)  "
                + "                   AS maximaIdOrden  "
                + "FROM tblDctosOrdenes                 "
                + "WHERE tblDctosOrdenes.idLocal     =  "
                + getIdLocal();

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

                maximaIdOrden = rs.getInt("maximaIdOrden");

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
            return maximaIdOrden;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {

            // Cierra la conexion
            jdbcAccess.cleanup(connection, selectStatement, null);
            return maximaIdOrden;

        }
    }

    // maximoOT
    public int maximoOT() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        //
        int xMaximoOT = 0;

        Connection connection = null;

        String selectStr =
                "SELECT MAX(tblDctosOrdenes.numeroOrden)  "
                + "                          AS maximoOT  "
                + "FROM tblDctosOrdenes                   ";

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
                xMaximoOT = rs.getInt("maximoOT");

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
            return xMaximoOT;

        }
    }

    // finalizaCotizacion 
    public boolean finalizaCotizacion() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                "UPDATE tblDctosOrdenes                         "
                + "SET tblDctosOrdenes.estado             = ( ? ),"
                + "    tblDctosOrdenes.direccionDespacho  = ( ? ),"
                + "    tblDctosOrdenes.email              = ( ? ),"
                + "    tblDctosOrdenes.fax                = ( ? ),"
                + "    tblDctosOrdenes.contacto           = ( ? ),"
                + "    tblDctosOrdenes.observacion        = ( ? ),"
                + "    tblDctosOrdenes.fechaEntrega       = ( ? ),"
                + "    tblDctosOrdenes.formaPago          = ( ? ),"
                + "    tblDctosOrdenes.ciudadDespacho     = ( ? ),"
                + "    tblDctosOrdenes.ordenCompra        = ( ? ),"
                + "    tblDctosOrdenes.descuentoComercial = ( ? ),"
                + "    tblDctosOrdenes.impuestoVenta      = ( ? ),"
                + "    tblDctosOrdenes.idRazon            = ( ? ),"
                + "    tblDctosOrdenes.numeroOrden        = ( ? ) "
                + "WHERE tblDctosOrdenes.idLog            = ( ? ) ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            //
            selectStatement.setInt(1, getEstado());
            selectStatement.setString(2, getDireccionDespacho());
            selectStatement.setString(3, getEmail());
            selectStatement.setString(4, getFax());
            selectStatement.setString(5, getContacto());
            selectStatement.setString(6, getObservacion());
            selectStatement.setString(7, getFechaEntregaSqlServer());
            selectStatement.setString(8, getFormaPago());
            selectStatement.setString(9, getCiudadDespacho());
            selectStatement.setString(10, getOrdenCompra());
            selectStatement.setDouble(11, getDescuentoComercial());
            selectStatement.setInt(12, getImpuestoVenta());
            selectStatement.setString(13, getIdRazon());
            selectStatement.setInt(14, getNumeroOrden());
            selectStatement.setInt(15, getIdLog());

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

            // Cierra el connection
            jdbcAccess.cleanup(connection, selectStatement, null);
            return okIngresar;
        }
    }

    // cambiaOT
    public boolean cambiaOT() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                "UPDATE tblDctosOrdenes                      "
                + "SET tblDctosOrdenes.estado             =  "
                + getEstado() + ",                           "
                + "    tblDctosOrdenes.direccionDespacho  = '"
                + getDireccionDespacho() + "',               "
                + "    tblDctosOrdenes.email              = '"
                + getEmail() + "',                           "
                + "    tblDctosOrdenes.fax                = '"
                + getFax() + "',                             "
                + "    tblDctosOrdenes.contacto           = '"
                + getContacto() + "',                        "
                + "    tblDctosOrdenes.observacion        = '"
                + getObservacion() + "',                     "
                + "    tblDctosOrdenes.fechaEntrega       = '"
                + getFechaEntregaSqlServer() + "',           "
                + "    tblDctosOrdenes.formaPago          = '"
                + getFormaPago() + "',                       "
                + "    tblDctosOrdenes.ciudadDespacho     = '"
                + getCiudadDespacho() + "',                  "
                + "    tblDctosOrdenes.ordenCompra        = '"
                + getOrdenCompra() + "',                     "
                + "    tblDctosOrdenes.descuentoComercial =  "
                + getDescuentoComercial() + ",               "
                + "    tblDctosOrdenes.impuestoVenta      =  "
                + getImpuestoVenta() + ",                    "
                + "    tblDctosOrdenes.idRazon            =  "
                + getIdRazon() + " ,                         "
                + "    tblDctosOrdenes.idUsuario          =  "
                + getIdUsuario() + ",                        "
                + "    tblDctosOrdenes.idVendedor         =  "
                + getIdVendedor() + "                        "
                + "WHERE tblDctosOrdenes.idLog            =  "
                + getIdLog();

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

            // Cierra el connection
            jdbcAccess.cleanup(connection, selectStatement, null);
            return okIngresar;
        }
    }

    // retira
    public boolean retira() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                "DELETE FROM tblDctosOrdenes          "
                + "WHERE tblDctosOrdenes.IDLOCAL      = "
                + getIdLocal() + "                      "
                + "AND   tblDctosOrdenes.IDTIPOORDEN  = "
                + getIdTipoOrden() + "                  "
                + "AND   tblDctosOrdenes.IDORDEN      = "
                + getIdOrden() + "                      ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            selectStatement.execute();
            okIngresar = true;

            // Cierra el Resultset
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
            return okIngresar;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {
            // Cierra la conexion a la base de datos limpiando la statement usada y la
            // conexion
            return okIngresar;
        }
    }

    // retira
    public boolean retiraLog() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                "DELETE FROM tblDctosOrdenes          "
                + "WHERE tblDctosOrdenes.IDLOCAL      = "
                + getIdLocal() + "                      "
                + "AND   tblDctosOrdenes.IDTIPOORDEN  = "
                + getIdTipoOrden() + "                  "
                + "AND   tblDctosOrdenes.IDORDEN      = "
                + getIdOrden() + "                      ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            selectStatement.execute();
            okIngresar = true;

            // Cierra el Resultset
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
            return okIngresar;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {
            // Cierra la conexion a la base de datos limpiando la statement usada y la
            // conexion
            return okIngresar;
        }
    }

    // ingresaPedido
    public boolean ingresaPedido() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                "INSERT INTO tblDctosOrdenes (idLocal,    "
                + "                      idTipoOrden,       "
                + "                      idOrden,           "
                + "                      fechaOrden,        "
                + "                      estado,            "
                + "                      idCliente,         "
                + "                      idUsuario,         "
                + "                      idOrigen,          "
                + "                      idLog,             "
                + "                      fechaEntrega,      "
                + "                      tipoDcto,          "
                + "                      email,             "
                + "                      fax,               "
                + "                      contacto,          "
                + "                      observacion,       "
                + "                      direccionDespacho, "
                + "                      ciudadDespacho,    "
                + "                      formaPago,         "
                + "                      ordenCompra,       "
                + "                      descuentoComercial,"
                + "                      impuestoVenta,     "
                + "                      idRazon,           "
                + "                      idEstadoTx,        "
                + "                      idTipoTx,          "
                + "                      numeroOrden,       "
                + "                      idResponsable,     "
                + "                      diasHistoria,      "
                + "                      diasInventario,    "
                + "                      idFicha)           "
                + "VALUES (" + getIdLocal() + "            ,"
                + getIdTipoOrden() + ",                     "
                + getIdOrden() + ",                        '"
                + getFechaOrdenSqlServer() + "',            "
                + getEstado() + ",                         '"
                + getIdCliente() + "',                      "
                + getIdUsuario() + ",                       "
                + getIdOrigen() + ",                        "
                + getIdLog() + ",                          '"
                + getFechaEntregaSqlServer() + "',         '"
                + getTipoDcto() + "',                      '"
                + getEmail() + "',                         '"
                + getFax() + "',                           '"
                + getContacto() + "',                      '"
                + getObservacion() + "',                   '"
                + getDireccionDespacho() + "',             '"
                + getCiudadDespacho() + "',                '"
                + getFormaPago() + "',                     '"
                + getOrdenCompra() + "',                   '"
                + getDescuentoComercial() + "',             "
                + getImpuestoVenta() + ",                  '"
                + getIdRazon() + "',                        "
                + getIdEstadoTx() + ",                      "
                + getIdTipoTx() + ",                       '"
                + getNumeroOrden() + "',                    "
                + getIdResponsable() + ",                   "
                + getDiasHistoria() + ",                    "
                + getDiasInventario() + ",                  "
                + getIdFicha() + ")                  ";

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

            // Cierra el connection
            jdbcAccess.cleanup(connection, selectStatement, null);
            return okIngresar;
        }
    }

    // ingresaDctosOrden MsAccess
    public boolean ingresaDctosOrden() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;


        Connection connection = null;

        String selectStr =
                "INSERT INTO tblDctosOrdenes (idLocal, "
                + "                      idTipoOrden,    "
                + "                      idOrden,        "
                + "                      fechaOrden,     "
                + "                      estado,         "
                + "                      idCliente,      "
                + "                      idUsuario,      "
                + "                      idOrigen,       "
                + "                      idLog,          "
                + "                      fechaEntrega,   "
                + "                      tipoDcto,       "
                + "                      email,          "
                + "                      formaPago,      "
                + "                      diasHistoria,   "
                + "                      diasInventario, "
                + "                      observacion,    "
                + "                      idFicha,        "
                + "                      ciudadDespacho, "
                + "                   direccionDespacho, "
                + "                            contacto, "
                + "                         numeroOrden, "
                + "                         idVendedor)  "
                + "VALUES (" + getIdLocal() + ","
                + getIdTipoOrden() + ","
                + getIdOrden() + ",'"
                + getFechaOrdenSqlServer() + "',"
                + getEstado() + ",'"
                + getIdCliente() + "',"
                + getIdUsuario() + ","
                + getIdOrigen() + ","
                + getIdLog() + ",'"
                + getFechaEntregaSqlServer() + "','"
                + getTipoDcto() + "','"
                + getEmail() + "','"
                + getFormaPago() + "',"
                + getDiasHistoria() + ","
                + getDiasInventario() + ",'"
                + getObservacion() + "',"
                + getIdFicha() + ",'"
                + getCiudadDespacho() + "','"
                +  "','"
                + getContacto() + "',"
                + getNumeroOrden() + ","
                + getIdVendedor() + ")";

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
}
