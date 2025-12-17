package com.solucionesweb.losbeans.colaboraciones;

// Importa la clase helper JDBCAccess que contiene los accesos a la base de datos
import com.solucionesweb.lasayudas.JDBCAccess;

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

public class ColaboraDctoOrdenDetalleBean extends FachadaDctoOrdenDetalleBean
        implements Serializable {

    // Nombre JNDI del Data Source que esta clase requiere
    private static final String DATA_SOURCE_NAME = "CommerceAccess";
    // Clase helper usada para acceder a la base de datos
    private JDBCAccess jdbcAccess;

    // Metodo constructor por defecto sin parametros
    public ColaboraDctoOrdenDetalleBean() {

        // Al instanciar el Bean establece la conexion a la base de datos
        jdbcAccess = new JDBCAccess(DATA_SOURCE_NAME);
    }

    // listaDetalleCotizacion
    public Vector listaDetalleCotizacion(int xIdLog,
            int xIdTipoOrden) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        // Variable de fachada de los datos
        FachadaDctoOrdenDetalleBean fachadaBean;

        Connection connection = null;

        String selectStr =
                "SELECT     MIN(tblDctosOrdenesDetalle.IDPLU) AS idPlu,            "
                + "           MIN(tblDctosOrdenesDetalle.STRIDREFERENCIA)            "
                + "                                   AS strIdReferencia,            "
                + "           MIN(tblDctosOrdenesDetalle.NOMBREPLU)                  "
                + "                                         AS nombrePlu,            "
                + "           MIN(tblDctosOrdenesDetalle.IDLOCALSUGERIDO)            "
                + "                                   AS idLocalSugerido,            "
                + "           MIN(tblDctosOrdenesDetalle.IDBODEGASUGERIDO)           "
                + "                                   AS idBodegaSugerido,           "
                + "           tblDctosOrdenesDetalle.item,                           "
                + "           tblDctosOrdenesDetalle.itemPadre,                      "
                + "           MIN(tblDctosOrdenesDetalle.CANTIDAD)                   "
                + "                                          AS cantidad,            "
                + "           MIN(tblDctosOrdenesDetalle.vrCosto)                    "
                + "                                          AS VrCosto,             "
                + "           MIN(tblDctosOrdenesDetalle.VRVENTAUNITARIO)            "
                + "                                   AS vrVentaUnitario,            "
                + "           MIN(tblDctosOrdenesDetalle.VRVENTAORIGINAL)            "
                + "                                   AS vrVentaOriginal,            "
                + "           COUNT(tblDctosOrdenesDetalle.IDPLU)                    "
                + "                                 AS cantidadArticulos,            "
                + "           SUM(tblDctosOrdenesDetalle.CANTIDAD  *                 "
                + "               tblDctosOrdenesDetalle.VRVENTAUNITARIO /           "
                + "             (1 + tblDctosOrdenesDetalle.porcentajeDscto / 100))  "
                + "                                     AS vrVentaConIva,            "
                + "           SUM((tblDctosOrdenesDetalle.CANTIDAD *                 "
                + "                tblDctosOrdenesDetalle.VRVENTAUNITARIO) /         "
                + "             (1 + tblDctosOrdenesDetalle.PORCENTAJEIVA / 100)   * "
                + "             (1 + tblDctosOrdenesDetalle.porcentajeDscto / 100))  "
                + "                                                AS vrVentaSinIva, "
                + "           MIN(tblDctosOrdenesDetalle.PORCENTAJEIVA)              "
                + "                                                AS porcentajeIva, "
                + "           SUM(tblDctosOrdenesDetalle.PESOTEORICO *               "
                + "               tblDctosOrdenesDetalle.CANTIDAD)                   "
                + "                                             AS pesoTeoricoTotal, "
                + "           MIN(tblDctosOrdenesDetalle.marcaArteCliente)           "
                + "                                             AS marcaArteCliente, "
                + "           MIN(tblDctosOrdenesDetalle.referenciaCliente)          "
                + "                                            AS referenciaCliente, "
                + "           MIN(tblDctosOrdenesDetalle.comentario) AS comentario,  "
                + "           MIN(tblDctosOrdenesDetalle.idEstadoTx) AS idEstadoTx,  "
                + "           MIN(tblDctosOrdenesDetalle.NOMBREUNIDADMEDIDA)         "
                + "                                          AS nombreUnidadMedida,  "
                + "           MIN(tblMarcas.nombreMarca) AS nombreMarca,             "
                + "           MIN(tblCategorias.nombreCategoria) AS nombreCategoria, "
                + "           MIN(tblPlus.vrCosto) AS vrCostoActual,                 "
                + "           SUM((tblDctosOrdenesDetalle.CANTIDAD *                 "
                + "              tblDctosOrdenesDetalle.vrCosto) ) AS vrCostoConIva, "
                + "           MAX(tblPlusInventario.existencia) AS existencia,       "
                + "           MIN(tblDctosOrdenesDetalle.porcentajeDscto)            "
                + "                                               AS porcentajeDscto "
                + "FROM       tblDctosOrdenesDetalle                                 "
                + "INNER JOIN tblDctosOrdenes                                        "
                + "ON  tblDctosOrdenesDetalle.IDLOCAL     = tblDctosOrdenes.IDLOCAL  "
                + "AND tblDctosOrdenesDetalle.IDTIPOORDEN =                          "
                + "                                     tblDctosOrdenes.IDTIPOORDEN  "
                + "AND tblDctosOrdenesDetalle.IDORDEN     = tblDctosOrdenes.IDORDEN  "
                + "INNER JOIN tblPlus                                                "
                + "ON tblDctosOrdenesDetalle.IDPLU        = tblPlus.idPlu            "
                + "INNER JOIN tblMarcas                                              "
                + "ON tblPlus.idMarca                     = tblMarcas.idMarca        "
                + "INNER JOIN tblCategorias                                          "
                + "ON tblPlus.idLinea                     = tblCategorias.idLinea    "
                + "AND tblPlus.idCategoria                =                          "
                + "                                        tblCategorias.IdCategoria "
                + "INNER JOIN tblPlusInventario                                      "
                + "ON  tblDctosOrdenesDetalle.IDLOCAL     =                          "
                + "                                       tblPlusInventario.idLocal  "
                + "AND tblDctosOrdenesDetalle.idPlu       = tblPlusInventario.idPlu  "
                + "WHERE tblDctosOrdenes.idLog            =                          "
                + xIdLog + "                                                         "
                + "AND   tblDctosOrdenes.idTipoOrden      =                          "
                + xIdTipoOrden + "                                                   "
                + "GROUP BY tblDctosOrdenesDetalle.itemPadre,                        "
                + "         tblDctosOrdenesDetalle.item                              "
                + "ORDER BY tblDctosOrdenesDetalle.itemPadre,                        "
                + "         tblDctosOrdenesDetalle.item                              ";

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
                fachadaBean.setIdPlu(rs.getInt("idPlu"));
                fachadaBean.setStrIdReferencia(rs.getString("strIdReferencia"));
                fachadaBean.setNombrePlu(rs.getString("nombrePlu"));
                fachadaBean.setIdLocalSugerido(rs.getInt("idLocalSugerido"));
                fachadaBean.setIdBodegaSugerido(rs.getString("idBodegaSugerido"));
                fachadaBean.setItem(rs.getInt("item"));
                fachadaBean.setItemPadre(rs.getInt("itemPadre"));
                fachadaBean.setCantidad(rs.getDouble("cantidad"));
                fachadaBean.setVrCosto(rs.getDouble("vrCosto"));
                fachadaBean.setVrVentaUnitario(rs.getDouble("vrVentaUnitario"));
                fachadaBean.setVrVentaOriginal(rs.getDouble("vrVentaOriginal"));
                fachadaBean.setCantidadArticulos(rs.getInt("cantidadArticulos"));
                fachadaBean.setVrVentaSinIva(rs.getInt("vrVentaSinIva"));
                fachadaBean.setVrVentaConIva(rs.getInt("vrVentaConIva"));
                fachadaBean.setPorcentajeIva(rs.getDouble("porcentajeIva"));
                fachadaBean.setPesoTeoricoTotal(rs.getDouble("pesoTeoricoTotal"));
                fachadaBean.setMarcaArteCliente(rs.getString("marcaArteCliente"));
                fachadaBean.setReferenciaCliente(
                        rs.getString("referenciaCliente"));
                fachadaBean.setNombreUnidadMedida(
                        rs.getString("nombreUnidadMedida"));
                fachadaBean.setNombreMarca(rs.getString("nombreMarca"));
                fachadaBean.setNombreCategoria(rs.getString("nombreCategoria"));
                fachadaBean.setVrCostoActual(rs.getDouble("vrCostoActual"));
                fachadaBean.setVrCostoConIva(rs.getDouble("vrCostoConIva"));
                fachadaBean.setExistencia(rs.getDouble("existencia"));
                fachadaBean.setPorcentajeDscto(rs.getDouble("porcentajeDscto"));

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

    // Metodo listaDctoOT
    public Vector listaDctoOT() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        Vector contenedor = new Vector();

        //
        Connection connection = null;

        String insertStr =
                " SELECT     tmpDCT.idLocal,                                "
                + "            tmpDCT.idTipoOrden,                          "
                + "            tmpDCT.idDcto,                               "
                + "            tmpDCT.indicador,                            "
                + "            tmpDCT.fechaDcto,                            "
                + "            tmpDCT.vrFactura,                            "
                + "            tblDctosOrdenes.idCliente,                   "
                + "            tmpDCT.nombreTercero,                        "
                + "            tmpFIC.referencia,                           "
                + "        ( SELECT  tblDctosOrdenes.numeroOrden            "
                + "          FROM   tblDctosOrdenes                         "
                + "          WHERE  tblDctosOrdenes.idLocal =               "
                + "           tblDctosOrdenesDetalle.idLocalOrigen          "
                + "          AND  tblDctosOrdenes.idTipoOrden =             "
                + "           tblDctosOrdenesDetalle.idTipoOrdenOrigen      "
                + "          AND  tblDctosOrdenes.idOrden =                 "
                + "           tblDctosOrdenesDetalle.idOrdenOrigen)         "
                + "                                    AS numeroOrden,      "
                + "          ( SELECT  tblDctosOrdenes.ordenCompra          "
                + "            FROM   tblDctosOrdenes                       "
                + "            WHERE  tblDctosOrdenes.idLocal =             "
                + "             tblDctosOrdenesDetalle.idLocalOrigen        "
                + "            AND  tblDctosOrdenes.idTipoOrden =           "
                + "             tblDctosOrdenesDetalle.idTipoOrdenOrigen    "
                + "            AND  tblDctosOrdenes.idOrden =               "
                + "             tblDctosOrdenesDetalle.idOrdenOrigen)       "
                + "                                      AS ordenCompra,    "
                + "            MIN(tblDctosOrdenesDetalle.cantidad)         "
                + "                                         AS cantidad,    "
                + "            MIN(tblDctosOrdenesDetalle.pesoFacturado)    "
                + "                                      AS pesoFacturado,  "
                + "            MIN( tblDctosOrdenesDetalle.vrVentaUnitario) "
                + "                                  AS vrVentaUnitario,    "
                + " 		 SUM((tblDctosOrdenesDetalle.cantidad     / "
                + "               tblDctosOrdenesDetalle.unidadVenta      * "
                + "               tblDctosOrdenesDetalle.vrVentaUnitario) / "
                + "   ( 1 + ( tblDctosOrdenesDetalle.porcentajeIva / 100))) "
                + "                                     AS vrVentaSinIva,   "
                + "            MIN(tblDctosOrdenesDetalle.porcentajeIva)    "
                + "                                     AS porcentajeIva,   "
                + "          MAX(tblDctosOrdenesDetalle.referenciaCliente)  "
                + "                                  AS referenciaCliente   "
                + " FROM   tblDctosOrdenes                                  "
                + " INNER JOIN (                                            "
                + " SELECT tblTerceros.nombreTercero,                       "
                + "        tblDctos.idDcto,                                 "
                + "        tblDctos.IDORDEN,                                "
                + "        tblDctos.IDTIPOORDEN,                            "
                + "        tblDctos.IDLOCAL,                                "
                + "        tblDctos.fechaDcto,                              "
                + "        tblDctos.indicador,                              "
                + "        (tblDctos.vrBase +                               "
                + "        tblDctos.vrIva  -                                "
                + "        tblDctos.vrRteFuente -                           "
                + "        tblDctos.vrRteIva )                              "
                + "                  AS vrFactura                           "
                + " FROM   tblDctos                                         "
                + " INNER JOIN tblTerceros                                  "
                + " ON tblDctos.idCliente =                                 "
                + "               tblTerceros.idCliente                     "
                + " WHERE tblTerceros.idTipoTercero = 1 )                   "
                + "                            AS tmpDCT                    "
                + "  ON tmpDCT.IDLOCAL      =                               "
                + "               tblDctosOrdenes.IDLOCAL                   "
                + "  AND tmpDCT.IDTIPOORDEN =                               "
                + "           tblDctosOrdenes.IDTIPOORDEN                   "
                + "  AND tmpDCT.IDORDEN     =                               "
                + "               tblDctosOrdenes.IDORDEN                   "
                + "  INNER JOIN tblDctosOrdenesDetalle                      "
                + "  ON tblDctosOrdenes.IDLOCAL      =                      "
                + "        tblDctosOrdenesDetalle.IDLOCAL                   "
                + "  AND tblDctosOrdenes.IDTIPOORDEN =                      "
                + "   tblDctosOrdenesDetalle.IDTIPOORDEN                    "
                + "  AND tblDctosOrdenes.IDORDEN     =                      "
                + "       tblDctosOrdenesDetalle.IDORDEN                    "
                + "  INNER JOIN                                             "
                + "    ( SELECT tblDctosOrdenes.idLocal,                    "
                + "             tblDctosOrdenes.idTipoOrden,                "
                + "             tblDctosOrdenes.idOrden,                    "
                + "             tblPlusFicha.referencia                     "
                + "      FROM   tblDctosOrdenes                             "
                + "      INNER JOIN tblPlusFicha                            "
                + "      ON tblDctosOrdenes.idFicha =                       "
                + "                  tblPlusFicha.idFicha                   "
                + "      GROUP BY tblDctosOrdenes.idLocal,                  "
                + "           tblDctosOrdenes.idTipoOrden,                  "
                + "               tblDctosOrdenes.idOrden,                  "
                + "               tblPlusFicha.referencia)                  "
                + "  			                 AS tmpFIC          "
                + "      ON  tmpFIC.idLocal            =                    "
                + "        tblDctosOrdenesDetalle.idLocalOrigen             "
                + "      AND  tmpFIC.idTipoOrden       =                    "
                + "        tblDctosOrdenesDetalle.idTipoOrdenOrigen         "
                + "      AND  tmpFIC.idOrden           =                    "
                + "        tblDctosOrdenesDetalle.idOrdenOrigen             "
                + " AND   tblDctosOrdenes.idLocal      =                    "
                + getIdLocal() + "                                          "
                + " WHERE  ( tblDctosOrdenes.IDTIPOORDEN  =                 "
                + getIdTipoOrdenINI() + "                                   "
                + " OR       tblDctosOrdenes.IDTIPOORDEN  =                 "
                + getIdTipoOrdenFIN() + "                           )       "
                + " AND   tmpDCT.fechaDcto BETWEEN                         '"
                + getFechaInicialSqlServer() + "'                     AND  '"
                + getFechaFinalSqlServer() + "'                             "
                + " AND   tmpDCT.indicador BETWEEN                          "
                + getIndicadorInicial() + "                           AND   "
                + getIndicadorFinal() + "                                   "
                + " GROUP BY tmpDCT.idLocal,                                "
                + "          tmpDCT.idTipoOrden,                            "
                + "          tmpDCT.idDcto,                                 "
                + "          tmpDCT.indicador,                              "
                + "          tmpDCT.fechaDcto,                              "
                + "          tmpDCT.vrFactura,                              "
                + "          tblDctosOrdenes.idCliente,                     "
                + "          tmpDCT.nombreTercero,                          "
                + "          tblDctosOrdenesDetalle.idOrden,                "
                + "          tblDctosOrdenesDetalle.item,                   "
                + "          tblDctosOrdenesDetalle.idLocalOrigen,          "
                + "          tblDctosOrdenesDetalle.idTipoOrdenOrigen,      "
                + "          tblDctosOrdenesDetalle.idOrdenOrigen,          "
                + "          tmpFIC.referencia                              "
                + " ORDER BY   tmpDCT.idDcto,                               "
                + "            tmpDCT.fechaDcto                             ";


        PreparedStatement sentenciaSQL = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            sentenciaSQL = connection.prepareStatement(insertStr);

            // Obtiene el resultset
            ResultSet rs = sentenciaSQL.executeQuery();

            // Variable de fachada de los datos
            FachadaDctoOrdenDetalleBean fachadaBean;

            //
            while (rs.next()) {

                //
                fachadaBean = new FachadaDctoOrdenDetalleBean();

                //
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setIdDcto(rs.getDouble("idDcto"));
                fachadaBean.setIndicador(rs.getInt("indicador"));
                fachadaBean.setFechaDcto(rs.getString("fechaDcto"));
                fachadaBean.setVrFactura(rs.getDouble("vrFactura"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setNombreTercero(rs.getString("nombreTercero"));
                fachadaBean.setReferencia(rs.getString("referencia"));
                fachadaBean.setNumeroOrden(rs.getInt("numeroOrden"));
                fachadaBean.setOrdenCompra(rs.getString("ordenCompra"));
                fachadaBean.setCantidad(rs.getDouble("cantidad"));
                fachadaBean.setPesoFacturado(rs.getDouble("pesoFacturado"));
                fachadaBean.setVrVentaUnitario(rs.getDouble("vrVentaUnitario"));
                fachadaBean.setVrVentaSinIva(rs.getDouble("vrVentaSinIva"));
                fachadaBean.setPorcentajeIva(rs.getDouble("porcentajeIva"));
                fachadaBean.setReferenciaCliente(rs.getString("referenciaCliente"));

                //
                contenedor.add(fachadaBean);
            }

            // Cierra el Resultset
            rs.close();
            jdbcAccess.cleanup(connection, sentenciaSQL, null);

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

    // listaDetalleIdOrden
    public Vector listaDetalleIdOrden() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        // Variable de fachada de los datos
        FachadaDctoOrdenDetalleBean fachadaBean;

        Connection connection = null;

        String selectStr =
                "SELECT MIN(tblDctosOrdenesDetalle.idPlu) AS idPlu,    "
                + "       MIN(tblDctosOrdenesDetalle.strIdReferencia)    "
                + "                         AS    strIdReferencia     ,    "
                + "       MIN(tblDctosOrdenesDetalle.nombrePlu)          "
                + "                                      AS nombrePlu ,    "
                + "       MIN(tblDctosOrdenesDetalle.idLocalSugerido)    "
                + "                                 AS idLocalSugerido,    "
                + "       MIN(tblDctosOrdenesDetalle.idBodegaSugerido)   "
                + "                                AS idBodegaSugerido,    "
                + "       tblDctosOrdenesDetalle.item,                     "
                + "       tblDctosOrdenesDetalle.itemPadre,                "
                + "       MIN(tblDctosOrdenesDetalle.cantidad) AS        "
                + "                                           cantidad,    "
                + "       MIN( tblDctosOrdenesDetalle.vrVentaUnitario)   "
                + "                                 AS vrVentaUnitario,    "
                + "       MIN( tblDctosOrdenesDetalle.vrVentaOriginal)   "
                + "                                 AS vrVentaOriginal,    "
                + "       COUNT(tblDctosOrdenesDetalle.idPlu)              "
                + "                        AS cantidadArticulos,           "
                + "       SUM ( tblDctosOrdenesDetalle.cantidad          * "
                + "             tblDctosOrdenesDetalle.vrVentaUnitario)    "
                + "                        AS vrVentaConIva,               "
                + "       SUM ( tblDctosOrdenesDetalle.cantidad          * "
                + "             tblDctosOrdenesDetalle.vrVentaUnitario   / "
                + "  (1 + (tblDctosOrdenesDetalle.porcentajeIva/100)))     "
                + "                        AS vrVentaSinIva ,              "
                + "       SUM(tblDctosOrdenesDetalle.pesoTeorico *         "
                + "           tblDctosOrdenesDetalle.cantidad)             "
                + "                        AS pesoTeoricoTotal,            "
                + "       MIN( tblDctosOrdenesDetalle.marcaArteCliente)  "
                + "                        AS marcaArteCliente,            "
                + "       MIN( tblDctosOrdenesDetalle.referenciaCliente) "
                + "                        AS referenciaCliente,           "
                + "       MIN( tblDctosOrdenesDetalle.comentario)        "
                + "                        AS comentario,                  "
                + "       MIN( tblDctosOrdenesDetalle.idEstadoTx)        "
                + "                        AS idEstadoTx                   "
                + "FROM  tblDctosOrdenes ,                                 "
                + "      tblDctosOrdenesDetalle                            "
                + "WHERE tblDctosOrdenes.idOrden         =                 "
                + "      tblDctosOrdenesDetalle.idOrden                    "
                + "AND   tblDctosOrdenes.idTipoOrden     =                 "
                + "      tblDctosOrdenesDetalle.idTipoOrden                "
                + "AND   tblDctosOrdenes.idLocal         =                 "
                + "      tblDctosOrdenesDetalle.idLocal                    "
                + "AND   tblDctosOrdenes.idOrden         =                 "
                + getIdOrden() + "                                         "
                + "AND   tblDctosOrdenes.idTipoOrden     =                 "
                + getIdTipoOrden() + "                                     "
                + "GROUP BY tblDctosOrdenesDetalle.itemPadre,              "
                + "         tblDctosOrdenesDetalle.item                    "
                + "ORDER BY tblDctosOrdenesDetalle.itemPadre,              "
                + "         tblDctosOrdenesDetalle.item                    ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            while (rs.next()) {

                fachadaBean = new FachadaDctoOrdenDetalleBean();

                fachadaBean.setIdPlu(rs.getInt("idPlu"));
                fachadaBean.setStrIdReferencia(rs.getString("strIdReferencia"));
                fachadaBean.setNombrePlu(rs.getString("nombrePlu"));
                fachadaBean.setIdLocalSugerido(rs.getInt("idLocalSugerido"));
                fachadaBean.setIdBodegaSugerido(rs.getString("idBodegaSugerido"));
                fachadaBean.setItem(rs.getInt("item"));
                fachadaBean.setItemPadre(rs.getInt("itemPadre"));
                fachadaBean.setCantidad(rs.getDouble("cantidad"));
                fachadaBean.setVrVentaUnitario(rs.getDouble("vrVentaUnitario"));
                fachadaBean.setVrVentaOriginal(rs.getDouble("vrVentaOriginal"));
                fachadaBean.setCantidadArticulos(rs.getInt("cantidadArticulos"));
                fachadaBean.setVrVentaSinIva(rs.getInt("vrVentaSinIva"));
                fachadaBean.setVrVentaConIva(rs.getInt("vrVentaConIva"));
                fachadaBean.setPesoTeoricoTotal(rs.getDouble("pesoTeoricoTotal"));
                fachadaBean.setMarcaArteCliente(rs.getString("marcaArteCliente"));
                fachadaBean.setReferenciaCliente(rs.getString("referenciaCliente"));
                fachadaBean.setComentario(rs.getString("comentario"));
                fachadaBean.setIdEstadoTx(rs.getInt("idEstadoTx"));

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

            // Cierra la conexion
            jdbcAccess.cleanup(connection, selectStatement, null);
            return contenedor;

        }
    }

    // maximoItem
    public int maximoItem(int xIdLog) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        int maximoItem = 0;
        Connection connection = null;

        String selectStr =
                "SELECT MAX(tblDctosOrdenesDetalle.item)   AS maxItem "
                + "FROM   tblDctosOrdenes                               "
                + "INNER  JOIN tblDctosOrdenesDetalle                   "
                + "ON     tblDctosOrdenes.IDORDEN       =               "
                + "                      tblDctosOrdenesDetalle.IDORDEN "
                + "AND    tblDctosOrdenes.IDTIPOORDEN   =               "
                + "                  tblDctosOrdenesDetalle.IDTIPOORDEN "
                + "AND    tblDctosOrdenes.IDLOCAL       =               "
                + "                      tblDctosOrdenesDetalle.IDLOCAL "
                + "WHERE tblDctosOrdenes.IDTIPOORDEN    = ( ? )         "
                + "AND   tblDctosOrdenes.IDLOG          = ( ? )         ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            //inicializa los valores de los parametros
            selectStatement.setInt(1, getIdTipoOrden());
            selectStatement.setInt(2, xIdLog);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            if (rs.next()) {

                maximoItem = rs.getInt("maxItem");

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
            return maximoItem;

        }
    }

    // itemLogFachada
    public FachadaDctoOrdenDetalleBean itemLogFachada(int xIdLog) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        //
        FachadaDctoOrdenDetalleBean fachadaBean =
                new FachadaDctoOrdenDetalleBean();

        Connection connection = null;

        String selectStr =
                "SELECT tblDctosOrdenesDetalle.idPlu,          "
                + "       tblDctosOrdenesDetalle.idOrden,        "
                + "       tblDctosOrdenesDetalle.idTipoOrden,    "
                + "       tblDctosOrdenesDetalle.idLocal,        "
                + "       tblDctosOrdenesDetalle.nombrePlu,      "
                + "       tblDctosOrdenesDetalle.cantidad,       "
                + "       tblDctosOrdenesDetalle.vrVentaUnitario,"
                + "       tblDctosOrdenesDetalle.vrCosto,        "
                + "       tblDctosOrdenesDetalle.porcentajeDscto,"
                + "       tblDctosOrdenesDetalle.fechaEntrega,   "
                + "       tblDctosOrdenesDetalle.unidadVenta,    "
                + " tblDctosOrdenesDetalle.vrVentaUnitarioSinIva, "
                + " tblDctosOrdenesDetalle.idTipoEmbalaje        "
                + "FROM  tblDctosOrdenes ,                       "
                + "      tblDctosOrdenesDetalle                  "
                + "WHERE tblDctosOrdenes.idOrden         =       "
                + "      tblDctosOrdenesDetalle.idOrden          "
                + "AND   tblDctosOrdenes.idTipoOrden     =       "
                + "      tblDctosOrdenesDetalle.idTipoOrden      "
                + "AND   tblDctosOrdenes.idLocal         =       "
                + "      tblDctosOrdenesDetalle.idLocal          "
                + "AND   tblDctosOrdenes.idLog              =    "
                + xIdLog + "                                     "
                + "AND   tblDctosOrdenesDetalle.item        =    "
                + getItem() + "                                  "
                + "AND   tblDctosOrdenesDetalle.idTipoOrden =    "
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
                fachadaBean.setIdPlu(rs.getInt("idPlu"));
                fachadaBean.setStrIdReferencia(rs.getString("idPlu"));
                fachadaBean.setIdOrden(rs.getInt("idOrden"));
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setNombrePlu(rs.getString("nombrePlu"));
                fachadaBean.setCantidad(rs.getDouble("cantidad"));
                fachadaBean.setVrVentaUnitario(rs.getDouble("vrVentaUnitario"));
                fachadaBean.setVrCosto(rs.getDouble("vrCosto"));
                fachadaBean.setPorcentajeDscto(rs.getDouble("porcentajeDscto"));
                fachadaBean.setFechaEntrega(rs.getString("fechaEntrega"));
                fachadaBean.setUnidadVenta(rs.getDouble("unidadVenta"));
                fachadaBean.setVrVentaUnitarioSinIva(
                        rs.getDouble("vrVentaUnitarioSinIva"));
                fachadaBean.setUnidadDian(rs.getInt("idTipoEmbalaje"));

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

    // itemOrdenFCH
    public FachadaDctoOrdenDetalleBean itemOrdenFCH() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        //
        FachadaDctoOrdenDetalleBean fachadaBean =
                new FachadaDctoOrdenDetalleBean();

        Connection connection = null;

        //
        String selectStr =
                "SELECT tblDctosOrdenesDetalle.idPlu,          "
                + "       tblDctosOrdenesDetalle.idOrden,        "
                + "       tblDctosOrdenesDetalle.idTipoOrden,    "
                + "       tblDctosOrdenesDetalle.idLocal,        "
                + "       tblDctosOrdenesDetalle.nombrePlu,      "
                + "       tblDctosOrdenesDetalle.cantidad,       "
                + "       tblDctosOrdenesDetalle.vrVentaUnitario,"
                + "       tblDctosOrdenesDetalle.vrCosto,        "
                + "       tblDctosOrdenesDetalle.porcentajeDscto,"
                + "       tblDctosOrdenesDetalle.fechaEntrega,   "
                + "       tblDctosOrdenesDetalle.unidadVenta,    "
                + "       tblDctosOrdenes.idFicha,               "
                + " tblDctosOrdenesDetalle.vrVentaUnitarioSinIva,"
                + "   tblDctosOrdenesDetalle.cantidadTerminada,  "
                + "   tblDctosOrdenesDetalle.cantidadFacturada,  "
                + "   tblDctosOrdenesDetalle.porcentajeIva,       "
                + "   tblDctosOrdenesDetalle.pesoFacturado,  "
                + "   tblDctosOrdenesDetalle.pesoTerminado       "
                + "FROM  tblDctosOrdenes ,                       "
                + "      tblDctosOrdenesDetalle                  "
                + "WHERE tblDctosOrdenes.idOrden         =       "
                + "      tblDctosOrdenesDetalle.idOrden          "
                + "AND   tblDctosOrdenes.idTipoOrden     =       "
                + "      tblDctosOrdenesDetalle.idTipoOrden      "
                + "AND   tblDctosOrdenes.idLocal         =       "
                + "      tblDctosOrdenesDetalle.idLocal          "
                + "AND   tblDctosOrdenes.idLocal         =       "
                + getIdLocal() + "                               "
                + "AND   tblDctosOrdenes.idOrden         =       "
                + getIdOrden() + "                               "
                + "AND   tblDctosOrdenesDetalle.item        =    "
                + getItem() + "                                  "
                + "AND   tblDctosOrdenesDetalle.idTipoOrden =    "
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
                fachadaBean.setIdPlu(rs.getInt("idPlu"));
                fachadaBean.setStrIdReferencia(rs.getString("idPlu"));
                fachadaBean.setIdOrden(rs.getInt("idOrden"));
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setNombrePlu(rs.getString("nombrePlu"));
                fachadaBean.setCantidad(rs.getDouble("cantidad"));
                fachadaBean.setVrVentaUnitario(rs.getDouble("vrVentaUnitario"));
                fachadaBean.setVrCosto(rs.getDouble("vrCosto"));
                fachadaBean.setPorcentajeDscto(rs.getDouble("porcentajeDscto"));
                fachadaBean.setFechaEntrega(rs.getString("fechaEntrega"));
                fachadaBean.setUnidadVenta(rs.getDouble("unidadVenta"));
                fachadaBean.setIdFicha(rs.getInt("idFicha"));
                fachadaBean.setVrVentaUnitarioSinIva(
                        rs.getDouble("vrVentaUnitarioSinIva"));
                fachadaBean.setCantidadTerminada(
                        rs.getDouble("cantidadTerminada"));
                fachadaBean.setCantidadFacturada(
                        rs.getDouble("cantidadFacturada"));
                fachadaBean.setPorcentajeIva(
                        rs.getDouble("porcentajeIva"));
                fachadaBean.setPesoTerminado(rs.getDouble("pesoTerminado"));
                fachadaBean.setPesoFacturado(rs.getDouble("pesoFacturado"));
                
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

    // listaResurtidoFCH
    public FachadaDctoOrdenDetalleBean listaResurtidoFCH() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        //
        FachadaDctoOrdenDetalleBean fachadaBean =
                new FachadaDctoOrdenDetalleBean();

        Connection connection = null;

        String selectStr =
                " SELECT TOP 1                    "
                + "        tmpResurtido.IDLOCAL     "
                + "      ,tmpResurtido.IDTIPOORDEN  "
                + "      ,tmpResurtido.IDPLU        "
                + "      ,tmpResurtido.CANTIDAD     "
                + "      ,tmpResurtido.existencia   "
                + "      ,tmpResurtido.IDLOG        "
                + "      ,tmpResurtido.fechaInicial "
                + "      ,tmpResurtido.diasHistoria "
                + "FROM tmpResurtido                "
                + "WHERE tmpResurtido.IDLOG    =    "
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
                fachadaBean.setIdPlu(rs.getInt("idPlu"));
                fachadaBean.setCantidad(rs.getDouble("cantidad"));
                fachadaBean.setExistencia(rs.getDouble("existencia"));
                fachadaBean.setIdLog(rs.getInt("idLog"));
                fachadaBean.setFechaInicial(rs.getString("fechaInicial"));
                fachadaBean.setDiasHistoria(rs.getInt("diasHistoria"));

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

    // listaOrdenOrigenFCH
    public FachadaDctoOrdenDetalleBean listaOrdenOrigenFCH() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        //
        FachadaDctoOrdenDetalleBean fachadaBean =
                new FachadaDctoOrdenDetalleBean();

        Connection connection = null;

        String selectStr =
                " SELECT TOP 1 tblDctosOrdenesDetalle.idOrdenOrigen,    "
                + "         tblDctosOrdenesDetalle.idLocalOrigen,       "
                + "         tblDctosOrdenesDetalle.idTipoOrdenOrigen    "
                + " FROM    tblDctosOrdenes                             "
                + " INNER JOIN tblDctosOrdenesDetalle                   "
                + " ON  tblDctosOrdenes.IDLOCAL     =                   "
                + "                   tblDctosOrdenesDetalle.IDLOCAL    "
                + " AND tblDctosOrdenes.IDTIPOORDEN =                   "
                + "               tblDctosOrdenesDetalle.IDTIPOORDEN    "
                + " AND tblDctosOrdenes.IDORDEN     =                   "
                + "                   tblDctosOrdenesDetalle.IDORDEN    "
                + " WHERE tblDctosOrdenes.IDLOCAL     =                 "
                + getIdLocal() + "                                      "
                + " AND tblDctosOrdenes.IDTIPOORDEN   =                 "
                + getIdTipoOrden() + "                                  "
                + " AND tblDctosOrdenes.IDORDEN       =                 "
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
                fachadaBean.setIdLocalOrigen(rs.getInt("idLocalOrigen"));
                fachadaBean.setIdTipoOrdenOrigen(rs.getInt("idTipoOrdenOrigen"));
                fachadaBean.setIdOrdenOrigen(rs.getInt("idOrdenOrigen"));

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

    // listaPlu
    public Vector listaPlu(int xIdLog) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        Vector contenedor = new Vector();

        Connection connection = null;

        FachadaDctoOrdenDetalleBean fachadaBean;

        String selectStr =
                "SELECT tblDctosOrdenesDetalle.idPlu,          "
                + "       tblDctosOrdenesDetalle.idOrden,        "
                + "       tblDctosOrdenesDetalle.idTipoOrden,    "
                + "       tblDctosOrdenesDetalle.idLocal,        "
                + "       tblDctosOrdenesDetalle.nombrePlu,      "
                + "       tblDctosOrdenesDetalle.cantidad,       "
                + "       tblDctosOrdenesDetalle.vrVentaUnitario,"
                + "       tblDctosOrdenesDetalle.vrCosto,        "
                + "       tblDctosOrdenesDetalle.porcentajeDscto,"
                + "      tblDctosOrdenesDetalle.vrCostoNegociado "
                + "FROM  tblDctosOrdenes ,                       "
                + "      tblDctosOrdenesDetalle                  "
                + "WHERE tblDctosOrdenes.idOrden         =       "
                + "      tblDctosOrdenesDetalle.idOrden          "
                + "AND   tblDctosOrdenes.idTipoOrden     =       "
                + "      tblDctosOrdenesDetalle.idTipoOrden      "
                + "AND   tblDctosOrdenes.idLocal         =       "
                + "      tblDctosOrdenesDetalle.idLocal          "
                + "AND   tblDctosOrdenes.idLog           =       "
                + xIdLog + "                                     "
                + "AND   tblDctosOrdenesDetalle.idPlu    =       "
                + getIdPlu() + "                                 "
                + "AND   tblDctosOrdenesDetalle.idTipoOrden =    "
                + getIdTipoOrden();

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
                fachadaBean = new FachadaDctoOrdenDetalleBean();

                //
                fachadaBean.setIdPlu(rs.getInt("idPlu"));
                fachadaBean.setStrIdReferencia(rs.getString("idPlu"));
                fachadaBean.setIdOrden(rs.getInt("idOrden"));
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setNombrePlu(rs.getString("nombrePlu"));
                fachadaBean.setCantidad(rs.getDouble("cantidad"));
                fachadaBean.setVrVentaUnitario(rs.getDouble("vrVentaUnitario"));
                fachadaBean.setVrCosto(rs.getDouble("vrCosto"));
                fachadaBean.setPorcentajeDscto(rs.getDouble("porcentajeDscto"));
                fachadaBean.setVrCostoNegociado(
                        rs.getDouble("vrCostoNegociado"));

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

            // Cierra la conexion
            jdbcAccess.cleanup(connection, selectStatement, null);
            return contenedor;

        }
    }

    // listaRetiraArticulo
    public Vector listaRetiraArticulo() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        // Variable de fachada de los datos
        FachadaDctoOrdenDetalleBean fachadaBean;

        Connection connection = null;

        String selectStr =
                "SELECT MIN(tblDctosOrdenesDetalle.idPlu) AS idPlu,    "
                + "       MIN(tblDctosOrdenesDetalle.strIdReferencia)    "
                + "                           AS strIdReferencia,        "
                + "       MIN(tblDctosOrdenesDetalle.nombrePlu)          "
                + "                           AS nombrePlu,              "
                + "       tblDctosOrdenesDetalle.item,                   "
                + "       tblDctosOrdenesDetalle.itemPadre,              "
                + "       SUM(tblDctosOrdenesDetalle.cantidad) AS        "
                + "                         cantidad,                    "
                + "       SUM ( tblDctosOrdenesDetalle.cantidad        * "
                + "             tblDctosOrdenesDetalle.vrVentaUnitario * "
                + "  (1 - (tblDctosOrdenesDetalle.porcentajeDscto/100))) "
                + "                        AS vrVentaUnitario            "
                + "FROM  tblDctosOrdenes ,                               "
                + "      tblDctosOrdenesDetalle                          "
                + "WHERE tblDctosOrdenes.idOrden         =               "
                + "      tblDctosOrdenesDetalle.idOrden                  "
                + "AND   tblDctosOrdenes.idTipoOrden     =               "
                + "      tblDctosOrdenesDetalle.idTipoOrden              "
                + "AND   tblDctosOrdenes.idLocal         =               "
                + "      tblDctosOrdenesDetalle.idLocal                  "
                + "AND   tblDctosOrdenes.idLocal         =               "
                + getIdLocal() + "                                       "
                + "AND   tblDctosOrdenes.idLog           =               "
                + getIdLog() + "                                         "
                + "AND   tblDctosOrdenesDetalle.estado   =               "
                + getEstado() + "                                        "
                + "GROUP BY tblDctosOrdenesDetalle.itemPadre,            "
                + "         tblDctosOrdenesDetalle.item                  "
                + "ORDER BY tblDctosOrdenesDetalle.itemPadre,            "
                + "         tblDctosOrdenesDetalle.item                  ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            while (rs.next()) {

                fachadaBean = new FachadaDctoOrdenDetalleBean();

                fachadaBean.setIdPlu(rs.getInt("idPlu"));
                fachadaBean.setStrIdReferencia(rs.getString("strIdReferencia"));
                fachadaBean.setNombrePlu(rs.getString("nombrePlu"));
                fachadaBean.setCantidad(rs.getDouble("cantidad"));
                fachadaBean.setVrVentaUnitario(rs.getDouble("vrVentaUnitario"));

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

            // Cierra la conexion
            jdbcAccess.cleanup(connection, selectStatement, null);
            return contenedor;

        }
    }

    // listaArticulos
    public Vector listaArticulos(int xIdLog) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        // Variable de fachada de los datos
        FachadaDctoOrdenDetalleBean fachadaBean;

        Connection connection = null;

        String selectStr =
                "SELECT tblDctosOrdenesDetalle.idPlu,                  "
                + "       tblDctosOrdenesDetalle.strIdReferencia,        "
                + "       tblDctosOrdenesDetalle.nombrePlu,              "
                + "       tblDctosOrdenesDetalle.vrVentaOriginal,        "
                + "       tblDctosOrdenesDetalle.pesoTeorico,            "
                + "       tblDctosOrdenesDetalle.nombreUnidadMedida,     "
                + "       tblDctosOrdenesDetalle.idLocalSugerido,        "
                + "       tblDctosOrdenesDetalle.idBodegaSugerido,       "
                + "       tblDctosOrdenesDetalle.cantidad,               "
                + "       tblDctosOrdenesDetalle.vrVentaUnitario,        "
                + "       tblDctosOrdenesDetalle.strIdLista,             "
                + "       tblDctosOrdenesDetalle.idTipo,                 "
                + "       tblDctosOrdenesDetalle.porcentajeIva,          "
                + "       tblDctosOrdenesDetalle.marcaArteCliente,       "
                + "       tblDctosOrdenesDetalle.referenciaCliente,      "
                + "       tblDctosOrdenesDetalle.comentario,             "
                + "       tblDctosOrdenesDetalle.item,                   "
                + "       tblDctosOrdenesDetalle.itemPadre,              "
                + "       tblDctosOrdenesDetalle.idEstadoTx,             "
                + "       tblDctosOrdenesDetalle.idTipoTx,               "
                + "       tblDctosOrdenesDetalle.idReferenciaOriginal,   "
                + "       tblDctosOrdenesDetalle.idEstadoRefOriginal,    "
                + "       tblDctosOrdenesDetalle.idClasificacion,        "
                + "       tblDctosOrdenesDetalle.idResponsable,          "
                + "       tblDctosOrdenesDetalle.fechaEntrega,           "
                + "       tblDctosOrdenesDetalle.strIdBodega             "
                + "FROM  tblDctosOrdenes ,                               "
                + "      tblDctosOrdenesDetalle                          "
                + "WHERE tblDctosOrdenes.idOrden         =               "
                + "      tblDctosOrdenesDetalle.idOrden                  "
                + "AND   tblDctosOrdenes.idTipoOrden     =               "
                + "      tblDctosOrdenesDetalle.idTipoOrden              "
                + "AND   tblDctosOrdenes.idLocal         =               "
                + "      tblDctosOrdenesDetalle.idLocal                  "
                + "AND   tblDctosOrdenes.idLog                  =        "
                + xIdLog + "                                             "
                + "AND   tblDctosOrdenes.idTipoOrden            =        "
                + getIdTipoOrden();

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            while (rs.next()) {

                fachadaBean = new FachadaDctoOrdenDetalleBean();

                //
                fachadaBean.setIdPlu(rs.getInt("idPlu"));
                fachadaBean.setStrIdReferencia(rs.getString("strIdReferencia"));
                fachadaBean.setNombrePlu(rs.getString("nombrePlu"));
                fachadaBean.setVrVentaOriginal(rs.getDouble("vrVentaOriginal"));
                fachadaBean.setPesoTeorico(rs.getDouble("pesoTeorico"));
                fachadaBean.setNombreUnidadMedida(rs.getString("nombreUnidadMedida"));
                fachadaBean.setIdLocalSugerido(rs.getInt("idLocalSugerido"));
                fachadaBean.setIdBodegaSugerido(rs.getString("idBodegaSugerido"));
                fachadaBean.setCantidad(rs.getDouble("cantidad"));
                fachadaBean.setVrVentaUnitario(rs.getDouble("vrVentaUnitario"));
                fachadaBean.setStrIdLista(rs.getString("strIdLista"));
                fachadaBean.setIdTipo(rs.getInt("idTipo"));
                fachadaBean.setPorcentajeIva(rs.getDouble("porcentajeIva"));
                fachadaBean.setMarcaArteCliente(rs.getString("marcaArteCliente"));
                fachadaBean.setReferenciaCliente(rs.getString("referenciaCliente"));
                fachadaBean.setComentario(rs.getString("comentario"));
                fachadaBean.setItem(rs.getInt("item"));
                fachadaBean.setItemPadre(rs.getInt("itemPadre"));
                fachadaBean.setIdEstadoTx(rs.getInt("idEstadoTx"));
                fachadaBean.setIdTipoTx(rs.getInt("idTipoTx"));
                fachadaBean.setIdReferenciaOriginal(rs.getString("idReferenciaOriginal"));
                fachadaBean.setIdEstadoRefOriginal(rs.getInt("idEstadoRefOriginal"));
                fachadaBean.setIdClasificacion(rs.getInt("idClasificacion"));
                fachadaBean.setIdResponsable(rs.getInt("idResponsable"));
                fachadaBean.setFechaEntrega(rs.getString("fechaEntrega"));
                fachadaBean.setStrIdBodega(rs.getString("strIdBodega"));

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

    // liquidaPedidoResurtidoFCH
    public FachadaDctoOrdenDetalleBean liquidaPedidoResurtidoFCH() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        // Variable de fachada de los datos
        FachadaDctoOrdenDetalleBean fachadaBean = new FachadaDctoOrdenDetalleBean();

        Connection connection = null;

        String selectStr =
                " SELECT tblDctosOrdenes.IDLOCAL, 		   "
                + "        tblDctosOrdenes.IDTIPOORDEN, 	   "
                + "        tblDctosOrdenes.IDLOG, 		   "
                + "        COUNT(*) AS cantidadArticulos,          "
                + "  SUM(tblDctosOrdenesDetalle.cantidadPedida *   "
                + "       tblDctosOrdenesDetalle.vrCostoNegociado) "
                + "                            AS vrCostoNegociado "
                + " FROM  tblDctosOrdenesDetalle 	           "
                + " INNER JOIN tblDctosOrdenes			   "
                + " ON  tblDctosOrdenesDetalle.IDLOCAL     = 	   "
                + "                       tblDctosOrdenes.IDLOCAL  "
                + " AND tblDctosOrdenesDetalle.IDTIPOORDEN = 	   "
                + "                   tblDctosOrdenes.IDTIPOORDEN  "
                + " AND tblDctosOrdenesDetalle.IDORDEN     = 	   "
                + "                       tblDctosOrdenes.IDORDEN  "
                + " WHERE tblDctosOrdenes.idLocal          =       "
                + getIdLocal() + "                                 "
                + " AND   tblDctosOrdenes.idTipoOrden      =       "
                + getIdTipoOrden() + "                             "
                + " AND   tblDctosOrdenes.idLog            =       "
                + getIdLog() + "                                   "
                + " AND   tblDctosOrdenesDetalle.CANTIDAD  > 0	   "
                + " GROUP BY tblDctosOrdenes.IDLOCAL, 		   "
                + "          tblDctosOrdenes.IDTIPOORDEN, 	   "
                + "          tblDctosOrdenes.IDLOG		   ";

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
                fachadaBean.setIdLog(rs.getInt("idLog"));
                fachadaBean.setCantidadArticulos(rs.getInt("cantidadArticulos"));
                fachadaBean.setVrCostoNegociado(rs.getDouble("vrCostoNegociado"));
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

    // listaArticulosIdLocalSugeridoIdTipoOrden
    public Vector listaArticulosIdLocalSugeridoIdTipoOrden(int xIdLog) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        // Variable de fachada de los datos
        FachadaDctoOrdenDetalleBean fachadaBean;

        Connection connection = null;

        String selectStr =
                "SELECT tblDctosOrdenesDetalle.idPlu,                  "
                + "       tblDctosOrdenesDetalle.strIdReferencia,        "
                + "       tblDctosOrdenesDetalle.nombrePlu,              "
                + "       tblDctosOrdenesDetalle.vrVentaOriginal,        "
                + "       tblDctosOrdenesDetalle.pesoTeorico,            "
                + "       tblDctosOrdenesDetalle.nombreUnidadMedida,     "
                + "       tblDctosOrdenesDetalle.idLocalSugerido,        "
                + "       tblDctosOrdenesDetalle.idBodegaSugerido,       "
                + "       tblDctosOrdenesDetalle.cantidad,               "
                + "       tblDctosOrdenesDetalle.vrVentaUnitario,        "
                + "       tblDctosOrdenesDetalle.strIdLista,             "
                + "       tblDctosOrdenesDetalle.idTipo,                 "
                + "       tblDctosOrdenesDetalle.porcentajeIva,          "
                + "       tblDctosOrdenesDetalle.marcaArteCliente,       "
                + "       tblDctosOrdenesDetalle.referenciaCliente,      "
                + "       tblDctosOrdenesDetalle.comentario,             "
                + "       tblDctosOrdenesDetalle.item,                   "
                + "       tblDctosOrdenesDetalle.itemPadre               "
                + "FROM  tblDctosOrdenes ,                               "
                + "      tblDctosOrdenesDetalle                          "
                + "WHERE tblDctosOrdenes.idOrden         =               "
                + "      tblDctosOrdenesDetalle.idOrden                  "
                + "AND   tblDctosOrdenes.idTipoOrden     =               "
                + "      tblDctosOrdenesDetalle.idTipoOrden              "
                + "AND   tblDctosOrdenes.idLocal         =               "
                + "      tblDctosOrdenesDetalle.idLocal                  "
                + "AND   tblDctosOrdenes.idLog                  = ( ? )  "
                + "AND   tblDctosOrdenes.idTipoOrden            = ( ? )  "
                + "AND   tblDctosOrdenesDetalle.idLocalSugerido = ( ? )  ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            //inicializa los valores de los parametros
            selectStatement.setInt(1, xIdLog);
            selectStatement.setInt(2, getIdTipoOrden());
            selectStatement.setInt(3, getIdLocalSugerido());

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            while (rs.next()) {

                fachadaBean = new FachadaDctoOrdenDetalleBean();

                fachadaBean.setIdPlu(rs.getInt("idPlu"));
                fachadaBean.setStrIdReferencia(rs.getString("strIdReferencia"));
                fachadaBean.setNombrePlu(rs.getString("nombrePlu"));
                fachadaBean.setVrVentaOriginal(rs.getInt("vrVentaOriginal"));
                fachadaBean.setPesoTeorico(rs.getDouble("pesoTeorico"));
                fachadaBean.setNombreUnidadMedida(rs.getString("nombreUnidadMedida"));
                fachadaBean.setIdLocalSugerido(rs.getInt("idLocalSugerido"));
                fachadaBean.setIdBodegaSugerido(rs.getString("idBodegaSugerido"));
                fachadaBean.setCantidad(rs.getDouble("cantidad"));
                fachadaBean.setVrVentaUnitario(rs.getDouble("vrVentaUnitario"));
                fachadaBean.setStrIdLista(rs.getString("strIdLista"));
                fachadaBean.setIdTipo(rs.getString("idTipo"));
                fachadaBean.setPorcentajeIva(rs.getDouble("porcentajeIva"));
                fachadaBean.setMarcaArteCliente(rs.getString("marcaArteCliente"));
                fachadaBean.setReferenciaCliente(rs.getString("referenciaCliente"));
                fachadaBean.setComentario(rs.getString("comentario"));
                fachadaBean.setItem(rs.getInt("item"));
                fachadaBean.setItemPadre(rs.getInt("itemPadre"));

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

            // Cierra la conexion
            jdbcAccess.cleanup(connection, selectStatement, null);
            return contenedor;

        }
    }

    // listaArticulosIdLocalSugeridoIdTipoOrden
    public Vector listaArticulosIdLocalSugeridoIdOrden(int xIdLog) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        // Variable de fachada de los datos
        FachadaDctoOrdenDetalleBean fachadaBean;

        boolean validaOk = false;
        Connection connection = null;

        String selectStr =
                "SELECT tblDctosOrdenesDetalle.idPlu,                  "
                + "       tblDctosOrdenesDetalle.strIdReferencia,        "
                + "       tblDctosOrdenesDetalle.nombrePlu,              "
                + "       tblDctosOrdenesDetalle.vrVentaOriginal,        "
                + "       tblDctosOrdenesDetalle.pesoTeorico,            "
                + "       tblDctosOrdenesDetalle.nombreUnidadMedida,     "
                + "       tblDctosOrdenesDetalle.idLocalSugerido,        "
                + "       tblDctosOrdenesDetalle.idBodegaSugerido,       "
                + "       tblDctosOrdenesDetalle.cantidad,               "
                + "       tblDctosOrdenesDetalle.vrVentaUnitario,        "
                + "       tblDctosOrdenesDetalle.strIdLista,             "
                + "       tblDctosOrdenesDetalle.idTipo,                 "
                + "       tblDctosOrdenesDetalle.porcentajeIva           "
                + "FROM  tblDctosOrdenes ,                               "
                + "      tblDctosOrdenesDetalle                          "
                + "WHERE tblDctosOrdenes.idOrden         =               "
                + "      tblDctosOrdenesDetalle.idOrden                  "
                + "AND   tblDctosOrdenes.idTipoOrden     =               "
                + "      tblDctosOrdenesDetalle.idTipoOrden              "
                + "AND   tblDctosOrdenes.idLocal         =               "
                + "      tblDctosOrdenesDetalle.idLocal                  "
                + "AND   tblDctosOrdenes.idLog                  = ( ? )  "
                + "AND   tblDctosOrdenes.idTipoOrden            = ( ? )  "
                + "AND   tblDctosOrdenesDetalle.idOrden         = ( ? )  ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            //inicializa los valores de los parametros
            selectStatement.setInt(1, xIdLog);
            selectStatement.setInt(2, getIdTipoOrden());
            selectStatement.setInt(3, getIdOrden());

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            while (rs.next()) {

                fachadaBean = new FachadaDctoOrdenDetalleBean();

                fachadaBean.setIdPlu(rs.getInt("idPlu"));
                fachadaBean.setStrIdReferencia(rs.getString("strIdReferencia"));
                fachadaBean.setNombrePlu(rs.getString("nombrePlu"));
                fachadaBean.setVrVentaOriginal(rs.getInt("vrVentaOriginal"));
                fachadaBean.setPesoTeorico(rs.getDouble("pesoTeorico"));
                fachadaBean.setNombreUnidadMedida(rs.getString("nombreUnidadMedida"));
                fachadaBean.setIdLocalSugerido(rs.getInt("idLocalSugerido"));
                fachadaBean.setIdBodegaSugerido(rs.getString("idBodegaSugerido"));
                fachadaBean.setCantidad(rs.getDouble("cantidad"));
                fachadaBean.setVrVentaUnitario(rs.getDouble("vrVentaUnitario"));
                fachadaBean.setStrIdLista(rs.getString("strIdLista"));
                fachadaBean.setIdTipo(rs.getString("idTipo"));
                fachadaBean.setPorcentajeIva(rs.getDouble("porcentajeIva"));

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

            // Cierra la conexion
            jdbcAccess.cleanup(connection, selectStatement, null);
            return contenedor;

        }
    }

    // listaDevolucion
    public Vector listaDevolucion(int xIdDcto,
            int xIndicador) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        // Variable de fachada de los datos
        FachadaDctoOrdenDetalleBean fachadaBean;

        Connection connection = null;

        String selectStr =
                "SELECT tmpDcto.IDPLU,                          "
                + "       SUM(tmpDcto.CANTIDAD) AS cantidad,      "
                + "       MIN(tmpDcto.NOMBREPLU) AS nombrePlu,    "
                + "       MIN(tmpDcto.vrVentaUnitario)            "
                + "                           AS vrVentaUnitario, "
                + "       MIN(tmpDcto.PORCENTAJEIVA)              "
                + "                           AS porcentajeIva,   "
                + "       MIN(tmpDcto.VRCOSTO) AS vrCosto ,       "
                + "       MIN(tmpDcto.item) AS item,              "
                + "       MIN(tmpDcto.vrVentaConIva)              "
                + "                         AS vrVentaConIva,     "
                + "       MIN(tmpDcto.vrCostoConIva)              "
                + "                             AS vrCostoConIva  "
                + "FROM (                                         "
                + "SELECT tblDctosOrdenesDetalle.IDPLU,           "
                + "       tblDctosOrdenesDetalle.CANTIDAD,        "
                + "       tblDctosOrdenesDetalle.NOMBREPLU,       "
                + "   tblDctosOrdenesDetalle.vrVentaUnitario    * "
                + "  (1 - tblDctosOrdenesDetalle.porcentajeDscto  "
                + "                                      / 100) * "
                + " (1 - tblDctosOrdenesDetalle.vrDsctoPie/100)   "
                + "                           AS vrVentaUnitario, "
                + "       tblDctosOrdenesDetalle.PORCENTAJEIVA,   "
                + "       tblDctosOrdenesDetalle.VRCOSTO,         "
                + "       tblDctosOrdenesDetalle.item,            "
                + "  ((tblDctosOrdenesDetalle.CANTIDAD          * "
                + "   tblDctosOrdenesDetalle.vrVentaUnitario )  * "
                + "  (1 - tblDctosOrdenesDetalle.porcentajeDscto  "
                + "                                      / 100) * "
                + "  (1 - tblDctosOrdenesDetalle.vrDsctoPie       "
                + "                                      / 100))  "
                + "                             AS vrVentaConIva, "
                + "  (tblDctosOrdenesDetalle.CANTIDAD          *  "
                + "              tblDctosOrdenesDetalle.VRCOSTO ) "
                + "                             AS vrCostoConIva  "
                + "FROM   tblDctos                                "
                + "INNER JOIN tblDctosOrdenes                     "
                + "ON  tblDctos.IDLOCAL            =              "
                + "                      tblDctosOrdenes.IDLOCAL  "
                + "AND tblDctos.IDTIPOORDEN        =              "
                + "                  tblDctosOrdenes.IDTIPOORDEN  "
                + "AND tblDctos.IDORDEN            =              "
                + "                      tblDctosOrdenes.IDORDEN  "
                + "INNER JOIN tblDctosOrdenesDetalle              "
                + "ON  tblDctosOrdenes.IDLOCAL     =              "
                + "               tblDctosOrdenesDetalle.IDLOCAL  "
                + "AND tblDctosOrdenes.IDTIPOORDEN =              "
                + "           tblDctosOrdenesDetalle.IDTIPOORDEN  "
                + "AND tblDctosOrdenes.IDORDEN     =              "
                + "               tblDctosOrdenesDetalle.IDORDEN  "
                + "WHERE tblDctos.IDLOCAL          =              "
                + getIdLocal() + "                                "
                + "AND tblDctos.IDTIPOORDEN        =              "
                + getIdTipoOrden() + "                            "
                + "AND tblDctos.idDcto             =              "
                + xIdDcto + "                                     "
                + "AND tblDctos.indicador          =              "
                + xIndicador + "                                  "
                + "UNION                                          "
                + "SELECT tblDctosOrdenesDetalle.IDPLU,           "
                + "       SUM(tblDctosOrdenesDetalle.CANTIDAD)    "
                + "                                 AS CANTIDAD , "
                + "       MAX(tblDctosOrdenesDetalle.NOMBREPLU)   "
                + "                                 AS NOMBREPLU, "
                + "   MAX(tblDctosOrdenesDetalle.VRVENTAUNITARIO) "
                + "                           AS VRVENTAUNITARIO, "
                + "   MAX(tblDctosOrdenesDetalle.PORCENTAJEIVA)   "
                + "                             AS PORCENTAJEIVA, "
                + "   MAX(tblDctosOrdenesDetalle.VRCOSTO)         "
                + "                                   AS VRCOSTO, "
                + "   MAX(tblDctosOrdenesDetalle.item) AS item,   "
                + "   SUM(tblDctosOrdenesDetalle.CANTIDAD      *  "
                + "       tblDctosOrdenesDetalle.VRVENTAUNITARIO) "
                + "                             AS vrVentaConIva, "
                + "  SUM(tblDctosOrdenesDetalle.CANTIDAD       *  "
                + "              tblDctosOrdenesDetalle.VRCOSTO ) "
                + "                             AS vrCostoConIva  "
                + "FROM   tblDctos                                "
                + "INNER JOIN tblDctosOrdenes                     "
                + "ON  tblDctos.IDLOCAL            =              "
                + "                      tblDctosOrdenes.IDLOCAL  "
                + "AND tblDctos.IDTIPOORDEN        =              "
                + "                  tblDctosOrdenes.IDTIPOORDEN  "
                + "AND tblDctos.IDORDEN            =              "
                + "                      tblDctosOrdenes.IDORDEN  "
                + "INNER JOIN tblDctosOrdenesDetalle              "
                + "ON  tblDctosOrdenes.IDLOCAL     =              "
                + "               tblDctosOrdenesDetalle.IDLOCAL  "
                + "AND tblDctosOrdenes.IDTIPOORDEN =              "
                + "           tblDctosOrdenesDetalle.IDTIPOORDEN  "
                + "AND tblDctosOrdenes.IDORDEN     =              "
                + "               tblDctosOrdenesDetalle.IDORDEN  "
                + "WHERE tblDctos.IDLOCALCruce     =              "
                + getIdLocal() + "                                "
                + "AND tblDctos.IDTIPOORDENCRUCE   =              "
                + getIdTipoOrden() + "                            "
                + "AND tblDctos.idDctoCruce        =              "
                + xIdDcto + "                                     "
                + "AND tblDctos.indicador          =              "
                + xIndicador + "                                  "
                + "GROUP BY tblDctosOrdenesDetalle.IDPLU    )     "
                + "                                   AS tmpDcto  "
                + "GROUP BY tmpDcto.IDPLU                         "
                + "ORDER BY 7                                     ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            while (rs.next()) {

                fachadaBean = new FachadaDctoOrdenDetalleBean();

                //
                fachadaBean.setIdPlu(rs.getInt("idPlu"));
                fachadaBean.setCantidad(rs.getDouble("cantidad"));
                fachadaBean.setNombrePlu(rs.getString("nombrePlu"));
                fachadaBean.setVrVentaUnitario(rs.getDouble("vrVentaUnitario"));
                fachadaBean.setPorcentajeIva(rs.getDouble("porcentajeIva"));
                fachadaBean.setVrCosto(rs.getDouble("vrCosto"));
                fachadaBean.setItem(rs.getInt("item"));
                fachadaBean.setVrVentaConIva(rs.getDouble("vrVentaConIva"));
                fachadaBean.setVrCostoConIva(rs.getDouble("vrCostoConIva"));

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

            // Cierra la conexion
            jdbcAccess.cleanup(connection, selectStatement, null);
            return contenedor;

        }
    }

    // listaDevolucion
    public Vector listaDevolucionOrden(int xIdOrden,
            int xIndicador) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        // Variable de fachada de los datos
        FachadaDctoOrdenDetalleBean fachadaBean;

        Connection connection = null;

        String selectStr =
                "   SELECT tmpDcto.idPlu,                                     "
                + "          SUM(tmpDcto.CANTIDAD) AS cantidad,               "
                + "          SUM(tmpDcto.pesoFacturado) AS pesoFacturado,     "
                + "          MIN(tmpDcto.NOMBREPLU) AS nombrePlu,             "
                + "          MIN(tmpDcto.vrVentaUnitario)                     "
                + "                              AS vrVentaUnitario,          "
                + "          MIN(tmpDcto.PORCENTAJEIVA)                       "
                + "                              AS porcentajeIva,            "
                + "          MIN(tmpDcto.VRCOSTO) AS vrCosto ,                "
                + "          tmpDcto.item,                                    "
                + "          MIN(tmpDcto.vrVentaConIva)                       "
                + "                            AS vrVentaConIva,              "
                + "          MIN(tmpDcto.vrCostoConIva)                       "
                + "                                AS vrCostoConIva,          "
                + "          MIN(tmpDcto.unidadVenta)                         "
                + "                                AS unidadVenta,            "
                + "     MIN(tmpDcto.idLocalOrigen) AS idLocalOrigen,          "
                + "     MIN(tmpDcto.idTipoOrdenOrigen)                        "
                + "                             AS idTipoOrdenOrigen,         "
                + "     MIN(tmpDcto.idOrdenOrigen) AS idOrdenOrigen           "
                + "   FROM (                                                  "
                + "   SELECT tblDctosOrdenesDetalle.IDPLU,                    "
                + "          tblDctosOrdenesDetalle.CANTIDAD,                 "
                + "          tblDctosOrdenesDetalle.pesoFacturado,            "
                + "          tblDctosOrdenesDetalle.NOMBREPLU,                "
                + "      tblDctosOrdenesDetalle.vrVentaUnitario    *          "
                + "     (1 - tblDctosOrdenesDetalle.porcentajeDscto / 100) *  "
                + "    (1 - tblDctosOrdenesDetalle.vrDsctoPie / 100)          "
                + "                              AS vrVentaUnitario,          "
                + "          tblDctosOrdenesDetalle.PORCENTAJEIVA,            "
                + "          tblDctosOrdenesDetalle.VRCOSTO,                  "
                + "          tblDctosOrdenesDetalle.item,                     "
                + "     ((tblDctosOrdenesDetalle.CANTIDAD          *          "
                + "       tblDctosOrdenesDetalle.vrVentaUnitario ) *          "
                + "     (1 - tblDctosOrdenesDetalle.porcentajeDscto           "
                + "                                         / 100) *          "
                + "     (1 - tblDctosOrdenesDetalle.vrDsctoPie                "
                + "                                         / 100))           "
                + "                                AS vrVentaConIva,          "
                + "     (tblDctosOrdenesDetalle.CANTIDAD          *           "
                + "                 tblDctosOrdenesDetalle.VRCOSTO )          "
                + "                                AS vrCostoConIva,          "
                + "     tblDctosOrdenesDetalle.unidadVenta,                   "
                + "     tblDctosOrdenesDetalle.idLocalOrigen,                 "
                + "     tblDctosOrdenesDetalle.idTipoOrdenOrigen,             "
                + "     tblDctosOrdenesDetalle.idOrdenOrigen                  "
                + "   FROM   tblDctos                                         "
                + "   INNER JOIN tblDctosOrdenes                              "
                + "   ON  tblDctos.IDLOCAL            =                       "
                + "                         tblDctosOrdenes.IDLOCAL           "
                + "   AND tblDctos.IDTIPOORDEN        =                       "
                + "                     tblDctosOrdenes.IDTIPOORDEN           "
                + "   AND tblDctos.IDORDEN            =                       "
                + "                         tblDctosOrdenes.IDORDEN           "
                + "   INNER JOIN tblDctosOrdenesDetalle                       "
                + "   ON  tblDctosOrdenes.IDLOCAL     =                       "
                + "                  tblDctosOrdenesDetalle.IDLOCAL           "
                + "   AND tblDctosOrdenes.IDTIPOORDEN =                       "
                + "              tblDctosOrdenesDetalle.IDTIPOORDEN           "
                + "   AND tblDctosOrdenes.IDORDEN     =                       "
                + "                  tblDctosOrdenesDetalle.IDORDEN           "
                + "WHERE tblDctos.IDLOCAL          =              "
                + getIdLocal() + "                                "
                + "AND tblDctos.IDTIPOORDEN        =              "
                + getIdTipoOrden() + "                            "
                + "AND tblDctos.idOrden            =              "
                + xIdOrden + "                                     "
                + "AND tblDctos.indicador          =              "
                + xIndicador + "                                  "
                + "   UNION                                                   "
                + "   SELECT tblDctosOrdenesDetalle.IDPLU,                    "
                + "          SUM(tblDctosOrdenesDetalle.CANTIDAD)             "
                + "                                    AS CANTIDAD ,          "
                + "          SUM(tblDctosOrdenesDetalle.pesoFacturado)        "
                + "                                    AS pesoFacturado ,     "
                + "          MAX(tblDctosOrdenesDetalle.NOMBREPLU)            "
                + "                                    AS NOMBREPLU,          "
                + "      MAX(tblDctosOrdenesDetalle.VRVENTAUNITARIO)          "
                + "                              AS VRVENTAUNITARIO,          "
                + "      MAX(tblDctosOrdenesDetalle.PORCENTAJEIVA)            "
                + "                                AS PORCENTAJEIVA,          "
                + "      MAX(tblDctosOrdenesDetalle.VRCOSTO)                  "
                + "                                      AS VRCOSTO,          "
                + "      tblDctosOrdenesDetalle.item,                         "
                + "      SUM(tblDctosOrdenesDetalle.CANTIDAD      *           "
                + "          tblDctosOrdenesDetalle.VRVENTAUNITARIO)          "
                + "                                AS vrVentaConIva,          "
                + "     SUM(tblDctosOrdenesDetalle.CANTIDAD       *           "
                + "                 tblDctosOrdenesDetalle.VRCOSTO )          "
                + "                                AS vrCostoConIva,          "
                + "     MIN(tblDctosOrdenesDetalle.unidadVenta)               "
                + "                          AS unidadVenta,                  "
                + "     MIN(tblDctosOrdenesDetalle.idLocalOrigen)             "
                + "                              AS idLocalOrigen,            "
                + "     MIN(tblDctosOrdenesDetalle.idTipoOrdenOrigen)         "
                + "                             AS idTipoOrdenOrigen,         "
                + "     MIN(tblDctosOrdenesDetalle.idOrdenOrigen)             "
                + "                               AS idOrdenOrigen            "
                + "   FROM   tblDctos                                         "
                + "   INNER JOIN tblDctosOrdenes                              "
                + "   ON  tblDctos.IDLOCAL            =                       "
                + "                         tblDctosOrdenes.IDLOCAL           "
                + "   AND tblDctos.IDTIPOORDEN        =                       "
                + "                     tblDctosOrdenes.IDTIPOORDEN           "
                + "   AND tblDctos.IDORDEN            =                       "
                + "                         tblDctosOrdenes.IDORDEN           "
                + "   INNER JOIN tblDctosOrdenesDetalle                       "
                + "   ON  tblDctosOrdenes.IDLOCAL     =                       "
                + "                  tblDctosOrdenesDetalle.IDLOCAL           "
                + "   AND tblDctosOrdenes.IDTIPOORDEN =                       "
                + "              tblDctosOrdenesDetalle.IDTIPOORDEN           "
                + "   AND tblDctosOrdenes.IDORDEN     =                       "
                + "                  tblDctosOrdenesDetalle.IDORDEN           "
                + "WHERE tblDctos.IDLOCALCruce     =              "
                + getIdLocal() + "                                "
                + "AND tblDctos.IDTIPOORDENCRUCE   =              "
                + getIdTipoOrden() + "                            "
                + "AND tblDctos.idOrdenCruce       =              "
                + xIdOrden + "                                    "
                + "AND tblDctos.indicador          =              "
                + xIndicador + "                                  "
                + "   GROUP BY tblDctosOrdenesDetalle.IDPLU,                  "
                + "            tblDctosOrdenesDetalle.item )                  "
                + "                                      AS tmpDcto           "
                + "   GROUP BY tmpDcto.IDPLU,                                 "
                + "            tmpDcto.item                                   "
                + "   ORDER BY 7                                              ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            while (rs.next()) {

                fachadaBean = new FachadaDctoOrdenDetalleBean();

                //
                fachadaBean.setIdPlu(rs.getInt("idPlu"));
                fachadaBean.setCantidad(rs.getDouble("cantidad"));
                fachadaBean.setPesoFacturado(rs.getDouble("pesoFacturado"));
                fachadaBean.setNombrePlu(rs.getString("nombrePlu"));
                fachadaBean.setVrVentaUnitario(rs.getDouble("vrVentaUnitario"));
                fachadaBean.setPorcentajeIva(rs.getDouble("porcentajeIva"));
                fachadaBean.setVrCosto(rs.getDouble("vrCosto"));
                fachadaBean.setItem(rs.getInt("item"));
                fachadaBean.setVrVentaConIva(rs.getDouble("vrVentaConIva"));
                fachadaBean.setVrCostoConIva(rs.getDouble("vrCostoConIva"));
                fachadaBean.setUnidadVenta(rs.getDouble("unidadVenta"));

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

            // Cierra la conexion
            jdbcAccess.cleanup(connection, selectStatement, null);
            return contenedor;

        }
    }

    // listaDevolucionFCH
    public FachadaDctoOrdenDetalleBean listaDevolucionFCH(int xIdDcto,
            int xIndicador) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        // Variable de fachada de los datos
        FachadaDctoOrdenDetalleBean fachadaBean =
                new FachadaDctoOrdenDetalleBean();

        Connection connection = null;

        String selectStr =
                "SELECT tmpDcto.IDPLU,                          "
                + "       SUM(tmpDcto.CANTIDAD) AS cantidad,      "
                + "       MIN(tmpDcto.NOMBREPLU) AS nombrePlu,    "
                + "       MIN(tmpDcto.VRVENTAUNITARIO)            "
                + "                         AS vrVentaUnitario,   "
                + "       MIN(tmpDcto.PORCENTAJEIVA)              "
                + "                           AS porcentajeIva,   "
                + "       MIN(tmpDcto.VRCOSTO) AS vrCosto ,       "
                + "       MIN(tmpDcto.item) AS item,              "
                + "       SUM(tmpDcto.vrVentaConIva)              "
                + "                              AS vrVentaConIva "
                + "FROM (                                         "
                + "SELECT tblDctosOrdenesDetalle.IDPLU,           "
                + "       tblDctosOrdenesDetalle.CANTIDAD,        "
                + "       tblDctosOrdenesDetalle.NOMBREPLU,       "
                + "       tblDctosOrdenesDetalle.VRVENTAUNITARIO, "
                + "       tblDctosOrdenesDetalle.PORCENTAJEIVA,   "
                + "       tblDctosOrdenesDetalle.VRCOSTO,         "
                + "       tblDctosOrdenesDetalle.item,            "
                + "       (tblDctosOrdenesDetalle.CANTIDAD     *  "
                + "       tblDctosOrdenesDetalle.VRVENTAUNITARIO) "
                + "                              AS vrVentaConIva "
                + "FROM   tblDctos                                "
                + "INNER JOIN tblDctosOrdenes                     "
                + "ON  tblDctos.IDLOCAL            =              "
                + "                      tblDctosOrdenes.IDLOCAL  "
                + "AND tblDctos.IDTIPOORDEN        =              "
                + "                  tblDctosOrdenes.IDTIPOORDEN  "
                + "AND tblDctos.IDORDEN            =              "
                + "                      tblDctosOrdenes.IDORDEN  "
                + "INNER JOIN tblDctosOrdenesDetalle              "
                + "ON  tblDctosOrdenes.IDLOCAL     =              "
                + "               tblDctosOrdenesDetalle.IDLOCAL  "
                + "AND tblDctosOrdenes.IDTIPOORDEN =              "
                + "           tblDctosOrdenesDetalle.IDTIPOORDEN  "
                + "AND tblDctosOrdenes.IDORDEN     =              "
                + "               tblDctosOrdenesDetalle.IDORDEN  "
                + "WHERE tblDctos.IDLOCAL          =              "
                + getIdLocal() + "                                "
                + "AND tblDctos.IDTIPOORDEN        =              "
                + getIdTipoOrden() + "                            "
                + "AND tblDctos.idDcto             =              "
                + xIdDcto + "                                     "
                + "AND tblDctos.indicador          =              "
                + xIndicador + "                                  "
                + "UNION                                          "
                + "SELECT tblDctosOrdenesDetalle.IDPLU,           "
                + "       SUM(tblDctosOrdenesDetalle.CANTIDAD)    "
                + "                                 AS CANTIDAD , "
                + "       MAX(tblDctosOrdenesDetalle.NOMBREPLU)   "
                + "                                 AS NOMBREPLU, "
                + "   MAX(tblDctosOrdenesDetalle.VRVENTAUNITARIO) "
                + "                           AS VRVENTAUNITARIO, "
                + "   MAX(tblDctosOrdenesDetalle.PORCENTAJEIVA)   "
                + "                             AS PORCENTAJEIVA, "
                + "   MAX(tblDctosOrdenesDetalle.VRCOSTO)         "
                + "                                   AS VRCOSTO, "
                + "   MAX(tblDctosOrdenesDetalle.item) AS item,   "
                + "   SUM(tblDctosOrdenesDetalle.CANTIDAD      *  "
                + "       tblDctosOrdenesDetalle.VRVENTAUNITARIO) "
                + "                              AS vrVentaConIva "
                + "FROM   tblDctos                                "
                + "INNER JOIN tblDctosOrdenes                     "
                + "ON  tblDctos.IDLOCAL            =              "
                + "                      tblDctosOrdenes.IDLOCAL  "
                + "AND tblDctos.IDTIPOORDEN        =              "
                + "                  tblDctosOrdenes.IDTIPOORDEN  "
                + "AND tblDctos.IDORDEN            =              "
                + "                      tblDctosOrdenes.IDORDEN  "
                + "INNER JOIN tblDctosOrdenesDetalle              "
                + "ON  tblDctosOrdenes.IDLOCAL     =              "
                + "               tblDctosOrdenesDetalle.IDLOCAL  "
                + "AND tblDctosOrdenes.IDTIPOORDEN =              "
                + "           tblDctosOrdenesDetalle.IDTIPOORDEN  "
                + "AND tblDctosOrdenes.IDORDEN     =              "
                + "               tblDctosOrdenesDetalle.IDORDEN  "
                + "WHERE tblDctos.IDLOCALCruce     =              "
                + getIdLocal() + "                                "
                + "AND tblDctos.IDTIPOORDENCRUCE   =              "
                + getIdTipoOrden() + "                            "
                + "AND tblDctos.idDctoCruce        =              "
                + xIdDcto + "                                     "
                + "AND tblDctos.indicador          =              "
                + xIndicador + "                                  "
                + "GROUP BY tblDctosOrdenesDetalle.IDPLU    )     "
                + "AS tmpDcto  GROUP BY tmpDcto.IDPLU             "
                + "ORDER BY 7                                     ";

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
                fachadaBean.setIdPlu(rs.getInt("idPlu"));
                fachadaBean.setCantidad(rs.getDouble("cantidad"));
                fachadaBean.setNombrePlu(rs.getString("nombrePlu"));
                fachadaBean.setVrVentaUnitario(rs.getDouble("vrVentaUnitario"));
                fachadaBean.setPorcentajeIva(rs.getDouble("porcentajeIva"));
                fachadaBean.setVrCosto(rs.getDouble("vrCosto"));
                fachadaBean.setItem(rs.getInt("item"));
                fachadaBean.setVrVentaConIva(rs.getDouble("vrVentaConIva"));

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

    // listaIvaDiscriminado
    public String listaIvaDiscriminado() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        // Variable de fachada de los datos
        String xIvaPorcentaje = "";

        // Variable de fachada de los datos
        FachadaDctoOrdenDetalleBean fachadaBean;

        Connection connection = null;

        String selectStr =
                "SELECT tblDctosOrdenesDetalle.porcentajeIva,                                "
                + "                           SUM(((tblDctosOrdenesDetalle.cantidad       *    "
                + "                              (tblDctosOrdenesDetalle.vrVentaUnitario  -    "
                + "                               tblDctosOrdenesDetalle.vrImpoconsumo))  /    "
                + "                 ( 1 + ( tblDctosOrdenesDetalle.porcentajeIva / 100))) *    "
                + "                 ( 1 - ( tblDctosOrdenesDetalle.vrDsctoPie / 100))     *    "
                + "                 ( 1 - ( tblDctosOrdenesDetalle.porcentajeDscto / 100)))    "
                + "                                                     AS vrVentaSinDscto,    "
                + "                   SUM(((tblDctosOrdenesDetalle.cantidad        *           "
                + "                       (tblDctosOrdenesDetalle.vrVentaUnitario  -           "
                + "                               tblDctosOrdenesDetalle.vrImpoconsumo))  /    "
                + "                 ( 1 + ( tblDctosOrdenesDetalle.porcentajeIva / 100))) *    "
                + "                 ( 1 - ( tblDctosOrdenesDetalle.vrDsctoPie / 100))     *    "
                + "                 ( 1 - ( tblDctosOrdenesDetalle.porcentajeDscto / 100))*    "
                + "                        ( tblDctosOrdenesDetalle.porcentajeIva / 100 ))     "
                + "                                                             AS vrIvaVenta  "
                + "                FROM tblDctosOrdenes,                                       "
                + "                     tblDctosOrdenesDetalle,                                "
                + "                     tblDctos                                               "
                + "                WHERE tblDctosOrdenes.IDORDEN     =                         "
                + "                                          tblDctosOrdenesDetalle.idOrden    "
                + "                AND   tblDctosOrdenes.IDTIPOORDEN =                         "
                + "                                       tblDctosOrdenesDetalle.idTipoOrden   "
                + "                AND   tblDctosOrdenes.idLocal     =                         "
                + "                                       tblDctosOrdenesDetalle.idLocal       "
                + "                AND   tblDctosOrdenes.IDORDEN     =                         "
                + "                                          tblDctos.idOrden                  "
                + "                AND   tblDctosOrdenes.IDTIPOORDEN =                         "
                + "                                       tblDctos.idTipoOrden                 "
                + "                AND   tblDctosOrdenes.idLocal     =                         "
                + "                                       tblDctos.idLocal                     "
                + "                AND   tblDctosOrdenes.idLocal     =                         "
                + getIdLocal() + "                                           "
                + "AND   tblDctosOrdenes.IDTIPOORDEN =                       "
                + getIdTipoOrden() + "                                       "
                + "AND   tblDctosOrdenes.idOrden     =                       "
                + getIdOrden() + "                                           "
                + "GROUP BY tblDctosOrdenesDetalle.porcentajeIva             ";

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
                fachadaBean.setPorcentajeIva(rs.getDouble("porcentajeIva"));
                fachadaBean.setVrVentaSinDscto(rs.getDouble("vrVentaSinDscto"));
                fachadaBean.setVrIvaVenta(rs.getDouble("vrIvaVenta"));

                //
                xIvaPorcentaje += fachadaBean.getPorcentajeIvaDf0() + "% "
                        + "VrBase "
                        + fachadaBean.getVrVentaSinDsctoDf0() + "  "
                        + "VrIva "
                        + fachadaBean.getVrIvaVentaDf0() + "  ";

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
            return xIvaPorcentaje;

        }
    }

    // listaPesoPedido
    public Vector listaPesoPedido() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        // Variable de fachada de los datos
        FachadaDctoOrdenDetalleBean fachadaBean;

        Connection connection = null;

        String selectStr =
                " SELECT tblDctosOrdenes.idLocal,            "
                + "        tblDctosOrdenes.idTipoOrden,      "
                + "        tblDctosOrdenes.idOrden,          "
                + "        tblDctosOrdenesDetalle.item,      "
                + "        tblDctosOrdenes.idCliente,        "
                + "        tblDctosOrdenes.numeroOrden,      "
                + "        tblDctosOrdenes.idFicha,          "
                + "        tblDctosOrdenesDetalle.cantidad   "
                + " FROM tblDctosOrdenes                     "
                + " INNER JOIN tblDctosOrdenesDetalle        "
                + " ON tblDctosOrdenes.IDLOCAL      =        "
                + "         tblDctosOrdenesDetalle.IDLOCAL   "
                + " AND tblDctosOrdenes.IDTIPOORDEN =        "
                + "     tblDctosOrdenesDetalle.IDTIPOORDEN   "
                + " AND tblDctosOrdenes.IDORDEN     =        "
                + "         tblDctosOrdenesDetalle.IDORDEN   "
                + " WHERE tblDctosOrdenes.IDLOCAL   = 1      "
                + " AND tblDctosOrdenes.IDTIPOORDEN = 59     ";

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
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setIdOrden(rs.getInt("idOrden"));
                fachadaBean.setItem(rs.getInt("item"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setNumeroOrden(rs.getInt("numeroOrden"));
                fachadaBean.setIdFicha(rs.getInt("idFicha"));
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

    // calculaPesoFCH
    public FachadaDctoOrdenDetalleBean calculaPesoFCH() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        //
        FachadaDctoOrdenDetalleBean fachadaBean =
                new FachadaDctoOrdenDetalleBean();

        Connection connection = null;

        //
        String selectStr =
                " SELECT SUM(tblDctosOrdenesDetalle.CANTIDAD)        "
                + "                                AS cantidad,      "
                + "   SUM(tblDctosOrdenesDetalle.CANTIDADPEDIDA)     "
                + "                              AS CANTIDADPEDIDA,  "
                + "   SUM(tblDctosOrdenesDetalle.cantidadTerminada)  "
                + "                           AS cantidadTerminada,  "
                + "       SUM(tblDctosOrdenesDetalle.pesoTerminado)  "
                + "                               AS pesoTerminado   "
                + " FROM  tblDctosOrdenes                            "
                + " INNER JOIN tblDctosOrdenesDetalle                "
                + " ON tblDctosOrdenes.IDLOCAL            =          "
                + "                 tblDctosOrdenesDetalle.IDLOCAL   "
                + " AND tblDctosOrdenes.IDTIPOORDEN       =          "
                + "             tblDctosOrdenesDetalle.IDTIPOORDEN   "
                + " AND tblDctosOrdenes.IDORDEN           =          "
                + "                 tblDctosOrdenesDetalle.IDORDEN   "
                + " WHERE tblDctosOrdenes.idLocal         =          "
                + getIdLocal() + "                                   "
                + " AND   tblDctosOrdenes.idTipoOrden     =          "
                + getIdTipoOrden() + "                               "
                + " AND   tblDctosOrdenes.idOrden         =          "
                + getIdOrden() + "                                   "
                + " AND   tblDctosOrdenesDetalle.idBodega =          "
                + getIdBodega();

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
                fachadaBean.setCantidad(rs.getDouble("cantidad"));
                fachadaBean.setCantidadPedida(rs.getDouble("cantidadPedida"));
                fachadaBean.setCantidadTerminada(
                        rs.getDouble("cantidadTerminada"));
                fachadaBean.setPesoTerminado(rs.getDouble("pesoTerminado"));


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
}
