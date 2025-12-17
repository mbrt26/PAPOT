package com.solucionesweb.losbeans.colaboraciones;


// Importa la clase helper JDBCAccess que contiene los accesos a la base de datos
import com.solucionesweb.lasayudas.JDBCAccess;

// Importa el bean de utilidades
import com.solucionesweb.losbeans.utilidades.IConstantes;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaDctoOrdenDetalleBean;

// Importa los paquetes de java
import java.sql.*;
import java.io.*;
import java.io.IOException.*;

//import java.util.Date;
import java.util.Vector;
import java.io.IOException.*;

// Importa los paquetes de javax
import javax.naming.*;

public class ColaboraResurtidoDetalle extends FachadaDctoOrdenDetalleBean
        implements Serializable, IConstantes {

    // Nombre JNDI del Data Source que esta clase requiere
    private static final String DATA_SOURCE_NAME = "CommerceAccess";

    // Clase helper usada para acceder a la base de datos
    private JDBCAccess jdbcAccess;

    // Metodo constructor por defecto sin parametros
    public ColaboraResurtidoDetalle() {

        // Al instanciar el Bean establece la conexion a la base de datos
        jdbcAccess = new JDBCAccess(DATA_SOURCE_NAME);
    }

    // listaLegaliza
    public Vector listaLegaliza() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        // Variable de fachada de los datos
        FachadaDctoOrdenDetalleBean fachadaBean;

        Connection connection = null;

        //
        String selectStr =
                "SELECT tblDctosOrdenesDetalle.IDLOCAL,     " +
                "       tblPlus.nombrePlu,                  " +
                "       tblMarcas.nombreMarca,              " +
                "       tblCategorias.nombreCategoria,      " +
                "      tblDctosOrdenesDetalle.IDTIPOORDEN,  " +
                "       tblDctosOrdenesDetalle.IDORDEN,     " +
                "       tblDctosOrdenesDetalle.IDPLU,       " +
                "    tblDctosOrdenesDetalle.porcentajeIva,  " +
                "       tblDctosOrdenesDetalle.vrCosto,     " +
                " tblDctosOrdenesDetalle.vrCostoNegociado,  " +
                "       tblDctosOrdenes.idLog,              " +
                "   tblDctosOrdenesDetalle.cantidadPedida   " +
                "                       AS cantidadPedido,  " +
                " tblDctosOrdenesDetalle.cantidadBonificada " +
                "                   AS cantidadBonificada,  " +
                "(tblDctosOrdenesDetalle.cantidadPedida *   " +
                " tblDctosOrdenesDetalle.vrCostoNegociado)  " +
                "                       AS vrCostoPedido,   " +
                "       tblDctosOrdenesDetalle.item,        " +
                "   tblDctosOrdenesDetalle.cantidad,        " +
                " tblDctosOrdenesDetalle.vrCostoResurtido,  " +
                " tblDctosOrdenesDetalle.vrIvaResurtido,    " +
                " tblDctosOrdenesDetalle.vrImpoconsumo,     " +
                " tblDctosOrdenesDetalle.numeroOrden,       " +
                " tblDctosOrdenesDetalle.itemOrden,         " +
                " tblDctosOrdenesDetalle.idOperacion,       " +
                " tblDctosOrdenesDetalle.cantidadFacturada  " +
                "FROM tblLineas                             " +
                "INNER JOIN tblPlus                         " +
                "ON tblLineas.idLinea = tblPlus.idLinea     " +
                "INNER JOIN tblDctosOrdenesDetalle          " +
                "INNER JOIN tblDctosOrdenes                 " +
                "ON tblDctosOrdenesDetalle.IDLOCAL  =       " +
                "               tblDctosOrdenes.IDLOCAL     " +
                "AND tblDctosOrdenesDetalle.IDTIPOORDEN     " +
                "                                   =       " +
                "           tblDctosOrdenes.IDTIPOORDEN     " +
                "AND tblDctosOrdenesDetalle.IDORDEN =       " +
                "               tblDctosOrdenes.IDORDEN     " +
                "ON tblPlus.idPlu                   =       " +
                "          tblDctosOrdenesDetalle.IDPLU     " +
                "INNER JOIN tblMarcas                       " +
                "ON tblPlus.idMarca                 =       " +
                "                     tblMarcas.idMarca     " +
                "INNER JOIN tblCategorias                   " +
                "ON tblPlus.idLinea                 =       " +
                "                 tblCategorias.idLinea     " +
                "AND tblPlus.idCategoria            =       " +
                "             tblCategorias.IdCategoria     " +
                "WHERE  tblDctosOrdenes.IDLOCAL     =       " +
                getIdLocal() + "                            " +
                "AND    tblDctosOrdenes.IDTIPOORDEN =       " +
                getIdTipoOrden() + "                        " +
                "AND    tblDctosOrdenes.IDLOG       =       " +
                getIdLog() + "                              " +
                "ORDER BY tblDctosOrdenesDetalle.item ";
        
        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            while (rs.next()) {

                //
                fachadaBean = new FachadaDctoOrdenDetalleBean();

                //
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setNombrePlu(rs.getString("nombrePlu"));
                fachadaBean.setNombreMarca(rs.getString("nombreMarca"));
                fachadaBean.setNombreCategoria(rs.getString("nombreCategoria"));
                fachadaBean.setIdTipoOrden(rs.getInt("IdTipoOrden"));
                fachadaBean.setIdOrden(rs.getInt("idOrden"));
                fachadaBean.setIdPlu(rs.getInt("idPlu"));
                fachadaBean.setPorcentajeIva(rs.getDouble("porcentajeIva"));
                fachadaBean.setVrCosto(rs.getDouble("vrCosto"));                
                fachadaBean.setVrCostoNegociado(
                                              rs.getDouble("vrCostoNegociado"));
                fachadaBean.setIdLog(rs.getInt("idLog"));
                fachadaBean.setCantidadPedido(rs.getDouble("cantidadPedido") +
                                            rs.getDouble("cantidadBonificada"));
                fachadaBean.setVrCostoPedido(rs.getDouble("vrCostoPedido"));
                fachadaBean.setCantidad(rs.getDouble("cantidad"));
                fachadaBean.setVrCostoResurtido(
                                              rs.getDouble("vrCostoResurtido"));
                fachadaBean.setVrIvaResurtido(rs.getDouble("vrIvaResurtido"));
                fachadaBean.setVrImpoconsumo(rs.getDouble("vrImpoconsumo"));
                fachadaBean.setNumeroOrden(rs.getInt("numeroOrden"));
                fachadaBean.setItemOrden(rs.getInt("itemOrden"));
                fachadaBean.setIdOperacion(rs.getInt("idOperacion"));
                fachadaBean.setCantidadFacturada(rs.getDouble("cantidadFacturada"));

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


    // listaLegaliza
    public Vector listaLegalizaDespacho() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        // Variable de fachada de los datos
        FachadaDctoOrdenDetalleBean fachadaBean;

        Connection connection = null;

        //
        String selectStr =
                    " SELECT tblDctosOrdenesDetalle.IDLOCAL,      "
              +"         tblPlus.nombrePlu,                  "
              +"         tblMarcas.nombreMarca,              "
              +"         tblCategorias.nombreCategoria,      "
              +"        tblDctosOrdenesDetalle.IDTIPOORDEN,  "
              +"         tblDctosOrdenesDetalle.IDORDEN,     "
              +"         tblDctosOrdenesDetalle.IDPLU,       "
              +"      tblDctosOrdenesDetalle.porcentajeIva,  "
              +"         tblDctosOrdenesDetalle.vrCosto,     "
              +"   tblDctosOrdenesDetalle.vrCostoNegociado,  "
              +"         tblDctosOrdenes.idLog,              "
              +"     tblDctosOrdenesDetalle.cantidadPedida   "
              +"                         AS cantidadPedido,  "
              +"   tblDctosOrdenesDetalle.cantidadBonificada "
              +"                     AS cantidadBonificada,  "
              +"  (tblDctosOrdenesDetalle.cantidadPedida *   "
              +"   tblDctosOrdenesDetalle.vrCostoNegociado)  "
              +"                         AS vrCostoPedido,   "
              +"         tblDctosOrdenesDetalle.item,        "
              +"     tblDctosOrdenesDetalle.cantidad,        "
              +"   tblDctosOrdenesDetalle.vrCostoResurtido,  "
              +"   tblDctosOrdenesDetalle.vrIvaResurtido,    "
              +"   tblPlus.vrCostoIND,                       "
              +"   tblDctosOrdenesDetalle.vrImpoconsumo,     "
              +"   tblPlusInventario.existencia              "
              +"  FROM tblPlusInventario,                    "
              +"  tblLineas                                  "
              +"  INNER JOIN tblPlus                         "
              +"  ON tblLineas.idLinea = tblPlus.idLinea     "
              +"  INNER JOIN tblDctosOrdenesDetalle          "
              +"  INNER JOIN tblDctosOrdenes                 "
              +"  ON tblDctosOrdenesDetalle.IDLOCAL  =       "
              +"                 tblDctosOrdenes.IDLOCAL     "
              +"  AND tblDctosOrdenesDetalle.IDTIPOORDEN     "
              +"                                     =       "
              +"             tblDctosOrdenes.IDTIPOORDEN     "
              +"  AND tblDctosOrdenesDetalle.IDORDEN =       "
              +"                 tblDctosOrdenes.IDORDEN     "
              +"  ON tblPlus.idPlu                   =       "
              +"            tblDctosOrdenesDetalle.IDPLU     "
              +"  INNER JOIN tblMarcas                       "
              +"  ON tblPlus.idMarca                 =       "
              +"                       tblMarcas.idMarca     "
              +"  INNER JOIN tblCategorias                   "
              +"  ON tblPlus.idLinea                 =       "
              +"                   tblCategorias.idLinea     "
              +"  AND tblPlus.idCategoria            =       "
              +"               tblCategorias.IdCategoria     "
              +"  WHERE  tblDctosOrdenes.IDLOCAL     =       "
              +   getIdLocal() + "                           "
              +"  AND    tblDctosOrdenes.IDTIPOORDEN =       "
              +   getIdTipoOrden() + "                       "
              +"  AND    tblDctosOrdenes.IDLOG       =       "
              +   getIdLog() + "                             "
              +"  AND    tblPlusInventario.idBodega  =       "
              +"  1                                          "
              +" AND  tblDctosOrdenesDetalle.idOrdenOrigen = "
              +   getIdOrdenOrigen() + "                     "
              +"  AND tblPlusInventario.idPlu =              "
              +"             tblDctosOrdenesDetalle.IDPLU    "
              +"  ORDER BY tblCategorias.nombreCategoria,    "
              +"           tblPlus.nombrePlu                 " ;

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            while (rs.next()) {

                //
                fachadaBean = new FachadaDctoOrdenDetalleBean();

                //
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setNombrePlu(rs.getString("nombrePlu"));
                fachadaBean.setNombreMarca(rs.getString("nombreMarca"));
                fachadaBean.setNombreCategoria(rs.getString("nombreCategoria"));
                fachadaBean.setIdTipoOrden(rs.getInt("IdTipoOrden"));
                fachadaBean.setIdOrden(rs.getInt("idOrden"));
                fachadaBean.setIdPlu(rs.getInt("idPlu"));
                fachadaBean.setPorcentajeIva(rs.getDouble("porcentajeIva"));
                fachadaBean.setVrCosto(rs.getDouble("vrCosto"));
                fachadaBean.setVrCostoNegociado(
                                              rs.getDouble("vrCostoNegociado"));
                fachadaBean.setIdLog(rs.getInt("idLog"));
                fachadaBean.setCantidadPedido(rs.getDouble("cantidadPedido") +
                                            rs.getDouble("cantidadBonificada"));
                fachadaBean.setVrCostoPedido(rs.getDouble("vrCostoPedido"));
                fachadaBean.setCantidad(rs.getDouble("cantidad"));
                fachadaBean.setVrCostoResurtido(
                                              rs.getDouble("vrCostoResurtido"));
                fachadaBean.setVrIvaResurtido(rs.getDouble("vrIvaResurtido"));
                fachadaBean.setVrImpoconsumo(rs.getDouble("vrImpoconsumo"));
                fachadaBean.setVrCostoIND(rs.getDouble("vrCostoIND"));
                fachadaBean.setExistencia(rs.getDouble("existencia"));

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


    // listaAjuste
    public Vector listaAjuste() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        // Variable de fachada de los datos
        FachadaDctoOrdenDetalleBean fachadaBean;

        Connection connection = null;

        //
        String selectStr =
                "SELECT tblDctosOrdenesDetalle.IDLOCAL,   " +
                "       tblPlus.nombrePlu,                " +
                "       tblMarcas.nombreMarca,            " +
                "       tblCategorias.nombreCategoria,    " +
                "      tblDctosOrdenesDetalle.IDTIPOORDEN," +
                "       tblDctosOrdenesDetalle.IDORDEN,   " +
                "       tblDctosOrdenesDetalle.IDPLU,     " +
                "    tblDctosOrdenesDetalle.porcentajeIva," +
                "       tblDctosOrdenesDetalle.VRCOSTO,   " +
                "       tblDctosOrdenes.IDLOG,            " +
                "   tblDctosOrdenesDetalle.cantidadPedida " +
                "                       AS cantidadPedido," +
                "(tblDctosOrdenesDetalle.cantidadPedida * " +
                " tblDctosOrdenesDetalle.vrCostoNegociado)" +
                "                       AS vrCostoPedido, " +
                "       tblDctosOrdenesDetalle.item,      " +
                "       tblDctosOrdenesDetalle.cantidad   " +
                "FROM tblLineas                           " +
                "INNER JOIN tblPlus                       " +
                "ON tblLineas.idLinea = tblPlus.idLinea   " +
                "INNER JOIN tblDctosOrdenesDetalle        " +
                "INNER JOIN tblDctosOrdenes               " +
                "ON tblDctosOrdenesDetalle.IDLOCAL  =     " +
                "               tblDctosOrdenes.IDLOCAL   " +
                "AND tblDctosOrdenesDetalle.IDTIPOORDEN   " +
                "                                   =     " +
                "           tblDctosOrdenes.IDTIPOORDEN   " +
                "AND tblDctosOrdenesDetalle.IDORDEN =     " +
                "               tblDctosOrdenes.IDORDEN   " +
                "ON tblPlus.idPlu                   =     " +
                "          tblDctosOrdenesDetalle.IDPLU   " +
                "INNER JOIN tblMarcas                     " +
                "ON tblPlus.idMarca                 =     " +
                "                     tblMarcas.idMarca   " +
                "INNER JOIN tblCategorias                 " +
                "ON tblPlus.idLinea                 =     " +
                "                 tblCategorias.idLinea   " +
                "AND tblPlus.idCategoria            =     " +
                "             tblCategorias.IdCategoria   " +
                "WHERE  tblDctosOrdenes.IDLOCAL     =     " +
                getIdLocal() + "                          " +
                "AND    tblDctosOrdenes.IDTIPOORDEN =     " +
                getIdTipoOrden() + "                      " +
                "AND    tblDctosOrdenes.IDORDEN     =     " +
                getIdOrden() + "                          " +
                "ORDER BY tblDctosOrdenesDetalle.item ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            while (rs.next()) {

                //
                fachadaBean = new FachadaDctoOrdenDetalleBean();

                //
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setNombrePlu(rs.getString("nombrePlu"));
                fachadaBean.setNombreMarca(rs.getString("nombreMarca"));
                fachadaBean.setNombreCategoria(rs.getString("nombreCategoria"));
                fachadaBean.setIdTipoOrden(rs.getInt("IdTipoOrden"));
                fachadaBean.setIdOrden(rs.getInt("idOrden"));
                fachadaBean.setIdPlu(rs.getInt("idPlu"));
                fachadaBean.setPorcentajeIva(rs.getDouble("porcentajeIva"));
                fachadaBean.setVrCosto(rs.getDouble("vrCosto"));
                fachadaBean.setIdLog(rs.getInt("idLog"));
                fachadaBean.setCantidadPedido(rs.getDouble("cantidadPedido"));
                fachadaBean.setVrCostoPedido(rs.getDouble("vrCostoPedido"));
                fachadaBean.setCantidad(rs.getDouble("cantidad"));

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

        // listaLegaliza
    public Vector listaLegalizaTraslado() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        // Variable de fachada de los datos
        FachadaDctoOrdenDetalleBean fachadaBean;

        Connection connection = null;

        //
        String selectStr =
                    " SELECT tblDctosOrdenesDetalle.IDLOCAL,      "
              +"         tblPlus.nombrePlu,                  "
              +"         tblMarcas.nombreMarca,              "
              +"         tblCategorias.nombreCategoria,      "
              +"        tblDctosOrdenesDetalle.IDTIPOORDEN,  "
              +"         tblDctosOrdenesDetalle.IDORDEN,     "
              +"         tblDctosOrdenesDetalle.IDPLU,       "
              +"      tblDctosOrdenesDetalle.porcentajeIva,  "
              +"         tblDctosOrdenesDetalle.vrCosto,     "
              +"   tblDctosOrdenesDetalle.vrCostoNegociado,  "
              +"         tblDctosOrdenes.idLog,              "
              +"     tblDctosOrdenesDetalle.cantidadPedida   "
              +"                         AS cantidadPedido,  "
              +"   tblDctosOrdenesDetalle.cantidadBonificada "
              +"                     AS cantidadBonificada,  "
              +"  (tblDctosOrdenesDetalle.cantidadPedida *   "
              +"   tblDctosOrdenesDetalle.vrCostoNegociado)  "
              +"                         AS vrCostoPedido,   "
              +"         tblDctosOrdenesDetalle.item,        "
              +"     tblDctosOrdenesDetalle.cantidad,        "
              +"   tblDctosOrdenesDetalle.vrCostoResurtido,  "
              +"   tblDctosOrdenesDetalle.vrIvaResurtido,    "
              +"   tblDctosOrdenesDetalle.vrCostoIND,        "
              +"   tblDctosOrdenesDetalle.vrImpoconsumo,     "
              +"   tblPlusInventario.existencia              "
              +"  FROM tblPlusInventario,                    "
              +"  tblLineas                                  "
              +"  INNER JOIN tblPlus                         "
              +"  ON tblLineas.idLinea = tblPlus.idLinea     "
              +"  INNER JOIN tblDctosOrdenesDetalle          "
              +"  INNER JOIN tblDctosOrdenes                 "
              +"  ON tblDctosOrdenesDetalle.IDLOCAL  =       "
              +"                 tblDctosOrdenes.IDLOCAL     "
              +"  AND tblDctosOrdenesDetalle.IDTIPOORDEN     "
              +"                                     =       "
              +"             tblDctosOrdenes.IDTIPOORDEN     "
              +"  AND tblDctosOrdenesDetalle.IDORDEN =       "
              +"                 tblDctosOrdenes.IDORDEN     "
              +"  ON tblPlus.idPlu                   =       "
              +"            tblDctosOrdenesDetalle.IDPLU     "
              +"  INNER JOIN tblMarcas                       "
              +"  ON tblPlus.idMarca                 =       "
              +"                       tblMarcas.idMarca     "
              +"  INNER JOIN tblCategorias                   "
              +"  ON tblPlus.idLinea                 =       "
              +"                   tblCategorias.idLinea     "
              +"  AND tblPlus.idCategoria            =       "
              +"               tblCategorias.IdCategoria     "
              +"  WHERE  tblDctosOrdenes.IDLOCAL     =       "
              +   getIdLocal() + "                           "
              +"  AND    tblDctosOrdenes.IDTIPOORDEN =       "
              +   getIdTipoOrden() + "                       "
              +"  AND    tblDctosOrdenes.IDLOG       =       "
              +   getIdLog() + "                             "
              +"  AND    tblPlusInventario.idBodega  =       "
              +"  1                                          "
              +"  AND tblPlusInventario.idPlu =              "
              +"             tblDctosOrdenesDetalle.IDPLU    "
              +"  ORDER BY tblDctosOrdenesDetalle.item       ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            while (rs.next()) {

                //
                fachadaBean = new FachadaDctoOrdenDetalleBean();

                //
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setNombrePlu(rs.getString("nombrePlu"));
                fachadaBean.setNombreMarca(rs.getString("nombreMarca"));
                fachadaBean.setNombreCategoria(rs.getString("nombreCategoria"));
                fachadaBean.setIdTipoOrden(rs.getInt("IdTipoOrden"));
                fachadaBean.setIdOrden(rs.getInt("idOrden"));
                fachadaBean.setIdPlu(rs.getInt("idPlu"));
                fachadaBean.setPorcentajeIva(rs.getDouble("porcentajeIva"));
                fachadaBean.setVrCosto(rs.getDouble("vrCosto"));
                fachadaBean.setVrCostoNegociado(
                                              rs.getDouble("vrCostoNegociado"));
                fachadaBean.setIdLog(rs.getInt("idLog"));
                fachadaBean.setCantidadPedido(rs.getDouble("cantidadPedido") +
                                            rs.getDouble("cantidadBonificada"));
                fachadaBean.setVrCostoPedido(rs.getDouble("vrCostoPedido"));
                fachadaBean.setCantidad(rs.getDouble("cantidad"));
                fachadaBean.setVrCostoResurtido(
                                              rs.getDouble("vrCostoResurtido"));
                fachadaBean.setVrIvaResurtido(rs.getDouble("vrIvaResurtido"));
                fachadaBean.setVrImpoconsumo(rs.getDouble("vrImpoconsumo"));
                fachadaBean.setVrCostoIND(rs.getDouble("vrCostoIND"));
                fachadaBean.setExistencia(rs.getDouble("existencia"));

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
}
