package com.solucionesweb.losbeans.colaboraciones;

// Importa la clase helper JDBCAccess que contiene los accesos a la base de datos
import com.solucionesweb.lasayudas.JDBCAccess;

// Importa el bean de utilidades
import com.solucionesweb.losbeans.utilidades.IConstantes;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaColaboraDctoOrdenBean;

// Importa los paquetes de java
import java.sql.*;
import java.io.*;
import java.io.IOException.*;

//import java.util.Date;
import java.util.Vector;
import java.io.IOException.*;

// Importa los paquetes de javax
import javax.naming.*;

public class ColaboraDctoOrdenBean extends FachadaColaboraDctoOrdenBean
        implements Serializable, IConstantes {

    // Nombre JNDI del Data Source que esta clase requiere
    private static final String DATA_SOURCE_NAME = "CommerceAccess";
    // Clase helper usada para acceder a la base de datos
    private JDBCAccess jdbcAccess;

    // Metodo constructor por defecto sin parametros
    public ColaboraDctoOrdenBean() {

        // Al instanciar el Bean establece la conexion a la base de datos
        jdbcAccess = new JDBCAccess(DATA_SOURCE_NAME);
    }

    // liquidaOrden
    public Vector liquidaOrden() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        // Variable de fachada de los datos
        FachadaColaboraDctoOrdenBean fachadaBean;

        Connection connection = null;

        String selectStr =
                "SELECT tblDctosOrdenes.idLocal,                         "
                + "       tblDctosOrdenes.idTipoOrden,                     "
                + "       tblDctosOrdenes.idOrden,                         "
                + "       tblDctosOrdenes.idLog,                           "
                + "       tblDctosOrdenes.idCliente,                       "
                + "       tblDctosOrdenes.idUsuario,                       "
                + "       tblDctosOrdenes.fechaOrden,                      "
                + "       tblDctosOrdenes.idEstadoTx,                      "
                + "       tblDctosOrdenes.idVendedor,                      "
                + "       COUNT(tblDctosOrdenesDetalle.idPlu)              "
                + "                        AS cantidadArticulos,           "
                + "       SUM ( tblDctosOrdenesDetalle.cantidad         *  "
                + "             tblDctosOrdenesDetalle.vrVentaUnitario )   "
                + "                        AS vrVentaConIva,               "
                + "       SUM ( tblDctosOrdenesDetalle.cantidad         *  "
                + "             tblDctosOrdenesDetalle.vrVentaUnitario  /  "
                + "  (1 + (tblDctosOrdenesDetalle.porcentajeIva/100)))     "
                + "                        AS vrVentaSinIva,               "
                + "       SUM(tblDctosOrdenesDetalle.pesoTeorico *         "
                + "           tblDctosOrdenesDetalle.cantidad)             "
                + "                        AS pesoTeoricoTotal,            "
                + "       SUM(tblDctosOrdenesDetalle.vrCosto *             "
                + "           tblDctosOrdenesDetalle.cantidad)             "
                + "                                   AS vrCostoConIva,    "
                + "       SUM ( tblDctosOrdenesDetalle.cantidad *          "
                + "             tblDctosOrdenesDetalle.vrCosto  /          "
                + "  (1 + (tblDctosOrdenesDetalle.porcentajeIva/100)))     "
                + "                                   AS vrCostoSinIva     "
                + "FROM  tblDctosOrdenes ,                                 "
                + "      tblDctosOrdenesDetalle                            "
                + "WHERE tblDctosOrdenes.idOrden         =                 "
                + "      tblDctosOrdenesDetalle.idOrden                    "
                + "AND   tblDctosOrdenes.idTipoOrden     =                 "
                + "      tblDctosOrdenesDetalle.idTipoOrden                "
                + "AND   tblDctosOrdenes.idLocal         =                 "
                + "      tblDctosOrdenesDetalle.idLocal                    "
                + "AND   tblDctosOrdenes.idLog           = ( ? )           "
                + "AND   tblDctosOrdenes.idTipoOrden     =  10             "
                + "GROUP BY tblDctosOrdenes.idLocal,                       "
                + "       tblDctosOrdenes.idTipoOrden,                     "
                + "       tblDctosOrdenes.idOrden,                         "
                + "       tblDctosOrdenes.idLog,                           "
                + "       tblDctosOrdenes.idCliente,                       "
                + "       tblDctosOrdenes.idUsuario,                       "
                + "       tblDctosOrdenes.fechaOrden,                      "
                + "       tblDctosOrdenes.idEstadoTx,                      "
                + "       tblDctosOrdenes.idVendedor                       ";



        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            //inicializa los valores de los parametros
            selectStatement.setInt(1, getIdLog());

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            if (rs.next()) {

                fachadaBean = new FachadaColaboraDctoOrdenBean();

                //
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("IdTipoOrden"));
                fachadaBean.setIdOrden(rs.getInt("IdOrden"));
                fachadaBean.setIdLog(rs.getInt("idLog"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setIdUsuario(rs.getDouble("idUsuario"));
                fachadaBean.setFechaOrden(
                        rs.getString("fechaOrden").substring(0, 11));
                fachadaBean.setIdEstadoTx(rs.getInt("idEstadoTx"));
                fachadaBean.setCantidadArticulos(
                        rs.getInt("cantidadArticulos"));
                fachadaBean.setVrVentaSinIva(rs.getInt("vrVentaSinIva"));
                fachadaBean.setVrVentaConIva(rs.getInt("vrVentaConIva"));
                fachadaBean.setPesoTeoricoTotal(
                        rs.getDouble("pesoTeoricoTotal"));
                fachadaBean.setVrCostoConIva(rs.getDouble("vrCostoConIva"));
                fachadaBean.setVrCostoSinIva(rs.getDouble("vrCostoSinIva"));

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

    // Metodo listaAllFactura
    public Vector listaUnFactura(int xIdTipoTercero) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        Vector contenedor = new Vector();
        Connection connection = null;

        String insertStr =
               "  SELECT tmpDET.idLog,                               "
               + "         tmpDET.idCliente,                         "
               + "         tmpDET.idUsuario,                         "
               + "         tmpDET.fechaEntrega,                      "
               + "         tblTerceros.nombreTercero,                "
               + "         tblTerceros.direccionTercero,             "
               + "         tblTerceros.telefonoFijo,                 "
               + "         tblTerceros.nombreEmpresa,                "
               + "         tblTerceros.ciudadTercero,                "
               + "         ctrlUsuarios.aliasUsuario AS              "
               + "                              NOMBREUSUARIO,       "
               + "         tmpDET.idLocal,                           "
               + "         tmpDET.idTipoOrden,                       "
               + "         tmpDET.idOrden,                           "
               + "         tmpDET.item,                              "
               + "         tmpDET.cantidad,                          "
               + "         tmpDET.referenciaCliente,                 "
               + "         tmpDET.numeroOrden,                       "
               + "         tmpDET.idFicha,                           "
               + "         tmpDET.ordenCompra,                       "
               + "         tmpDET.fechaOrden,                        "
               + "         tmpDET.pesoPedido,                        "
               + "         tmpDET.pesoTerminado,                     "
               + "         tmpDET.pesoRetal,                         "
               + "         tmpDET.cantidadFacturada,                 "
               + "         tmpDET.cantidadTerminada                  "
               + "  FROM   tblTerceros                               "
               + "  INNER JOIN tblAgendaLogVisitas                   "
               + "  ON tblTerceros.idCliente         =               "
               + "              tblAgendaLogVisitas.idCliente        "
               + "  INNER JOIN ctrlUsuarios                          "
               + "  ON tblAgendaLogVisitas.IDUSUARIO =               "
               + "                     ctrlUsuarios.IDUSUARIO        "
               + "  INNER JOIN                                       "
               + "  (SELECT tblDctosOrdenes.idLocal,                 "
               + "         tblDctosOrdenes.idTipoOrden,              "
               + "         tblDctosOrdenes.idOrden,                  "
               + "         tblDctosOrdenesDetalle.item,              "
               + "         tblDctosOrdenesDetalle.cantidad,          "
               + "         tblDctosOrdenesDetalle.referenciaCliente, "
               + "         tblDctosOrdenesDetalle.fechaEntrega,      "
               + "         tblDctosOrdenes.idLog,                    "
               + "         tblDctosOrdenes.numeroOrden,              "
               + "         tblDctosOrdenes.idFicha,                  "
               + "         tblDctosOrdenes.idCliente,                "
               + "         tblDctosOrdenes.idUsuario,                "
               + "         tblDctosOrdenes.ordenCompra,              "
               + "         tblDctosOrdenes.fechaOrden,               "
               + "        tblDctosOrdenesDetalle.idEstadoRefOriginal,"
               + "        tblDctosOrdenesDetalle.pesoPedido,         "
               + "        tblDctosOrdenesDetalle.pesoTerminado,      "
               + "        tblDctosOrdenesDetalle.pesoRetal,          "
               + "        tblDctosOrdenesDetalle.cantidadFacturada,  "
               + "        tblDctosOrdenesDetalle.cantidadTerminada   "
               + "   FROM       tblDctosOrdenes                      "
               + "   INNER JOIN tblDctosOrdenesDetalle               "
               + "   ON tblDctosOrdenes.IDLOCAL      =               "
               + "                   tblDctosOrdenesDetalle.IDLOCAL  "
               + "   AND tblDctosOrdenes.IDTIPOORDEN =               "
               + "               tblDctosOrdenesDetalle.IDTIPOORDEN  "
               + "   AND tblDctosOrdenes.IDORDEN     =               "
               + "                   tblDctosOrdenesDetalle.IDORDEN) "
               + "                                         AS tmpDET "
               + "  ON tmpDET.idLog = tblAgendaLogVisitas.idLog      "
               + "  WHERE tblAgendaLogVisitas.IDLOG IN (             "
               + "     SELECT tblAgendaLogVisitas.IDLOG              "
               + "     FROM   tblAgendaLogVisitas                    "
               + "     INNER JOIN tblDctosOrdenes                    "
               + "     ON tblAgendaLogVisitas.IDLOG     =            "
               + "                      tblDctosOrdenes.IDLOG        "
               + " AND tmpDET.cantidadTerminada         <=           "
               + "                          tmpDET.cantidadFacturada "
               + " AND tmpDET.cantidadFacturada         > 0          "
               + "  AND tmpDET.pesoPedido               > 0          "
               + "  AND tmpDET.idEstadoRefOriginal     != 9          "
               + "  AND   tblDctosOrdenes.idLocal       =            "
               + getIdLocal() + "                                    "
               + "  AND   tblDctosOrdenes.idTipoOrden   =            "
               + getIdTipoOrden() + "                                "
               + "  AND   tblDctosOrdenes.numeroOrden  != 0          "
                + " AND   tblTerceros.idCliente            =         '"
                + getIdCliente() + "'                                 "
               + "  AND tblTerceros.idTipoTercero       =            "
               + xIdTipoTercero + " )                                "
               + "  ORDER BY tblTerceros.nombreTercero,              "
               + "           tmpDET.numeroOrden,                     "
               + "           tmpDET.fechaEntrega                     ";


        PreparedStatement sentenciaSQL = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            sentenciaSQL = connection.prepareStatement(insertStr);

            // Obtiene el resultset
            ResultSet rs = sentenciaSQL.executeQuery();

            // Variable de fachada de los datos
            FachadaColaboraDctoOrdenBean fachadaBean;

            while (rs.next()) {

                //
                fachadaBean = new FachadaColaboraDctoOrdenBean();

                //
                fachadaBean.setIdLog(rs.getInt("idLog"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setIdUsuario(rs.getDouble("idUsuario"));
                fachadaBean.setFechaEntrega(rs.getString("fechaEntrega"));
                fachadaBean.setNombreTercero(rs.getString("nombreTercero"));
                fachadaBean.setDireccionDespacho(
                                              rs.getString("direccionTercero"));
                fachadaBean.setTelefonoFijo(rs.getString("telefonoFijo"));
                fachadaBean.setNombreEmpresa(rs.getString("nombreEmpresa"));
                fachadaBean.setCiudadTercero(rs.getString("ciudadTercero"));
                fachadaBean.setNombreUsuario(rs.getString("NOMBREUSUARIO"));
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setIdOrden(rs.getInt("idOrden"));
                fachadaBean.setItem(rs.getInt("item"));
                fachadaBean.setCantidad(rs.getDouble("cantidad"));
                fachadaBean.setReferenciaCliente(
                                             rs.getString("referenciaCliente"));
                fachadaBean.setNumeroOrden(rs.getInt("numeroOrden"));
                fachadaBean.setIdFicha(rs.getInt("idFicha"));
                fachadaBean.setOrdenCompra(rs.getString("ordenCompra"));
                fachadaBean.setFechaOrden(rs.getString("fechaOrden"));
                fachadaBean.setPesoPedido(rs.getDouble("pesoPedido"));
                fachadaBean.setPesoTerminado(rs.getDouble("pesoTerminado"));
                fachadaBean.setPesoRetal(rs.getDouble("pesoRetal"));
                fachadaBean.setCantidadFacturada(
                                             rs.getDouble("cantidadFacturada"));
                fachadaBean.setCantidadTerminada(
                                             rs.getDouble("cantidadTerminada"));

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
            // conexion
            jdbcAccess.cleanup(connection, sentenciaSQL, null);
            return contenedor;
        }
    }

    // Metodo listaAllFactura
    public Vector listaAllFactura(int xIdTipoTercero) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        Vector contenedor = new Vector();
        Connection connection = null;

        String insertStr =
               "  SELECT tmpDET.idLog,                               "
               + "         tmpDET.idCliente,                         "
               + "         tmpDET.idUsuario,                         "
               + "         tmpDET.fechaEntrega,                      "
               + "         tblTerceros.nombreTercero,                "
               + "         tblTerceros.direccionTercero,             "
               + "         tblTerceros.telefonoFijo,                 "
               + "         tblTerceros.nombreEmpresa,                "
               + "         tblTerceros.ciudadTercero,                "
               + "         ctrlUsuarios.aliasUsuario AS              "
               + "                              NOMBREUSUARIO,       "
               + "         tmpDET.idLocal,                           "
               + "         tmpDET.idTipoOrden,                       "
               + "         tmpDET.idOrden,                           "
               + "         tmpDET.item,                              "
               + "         tmpDET.cantidad,                          "
               + "         tmpDET.referenciaCliente,                 "
               + "         tmpDET.numeroOrden,                       "
               + "         tmpDET.idFicha,                           "
               + "         tmpDET.ordenCompra,                       "
               + "         tmpDET.fechaOrden,                        "
               + "         tmpDET.pesoPedido,                        "
               + "         tmpDET.pesoTerminado,                     "
               + "         tmpDET.pesoRetal,                         "
               + "         tmpDET.cantidadFacturada,                 "
               + "         tmpDET.cantidadTerminada                  "
               + "  FROM   tblTerceros                               "
               + "  INNER JOIN tblAgendaLogVisitas                   "
               + "  ON tblTerceros.idCliente         =               "
               + "              tblAgendaLogVisitas.idCliente        "
               + "  INNER JOIN ctrlUsuarios                          "
               + "  ON tblAgendaLogVisitas.IDUSUARIO =               "
               + "                     ctrlUsuarios.IDUSUARIO        "
               + "  INNER JOIN                                       "
               + "  (SELECT tblDctosOrdenes.idLocal,                 "
               + "         tblDctosOrdenes.idTipoOrden,              "
               + "         tblDctosOrdenes.idOrden,                  "
               + "         tblDctosOrdenesDetalle.item,              "
               + "         tblDctosOrdenesDetalle.cantidad,          "
               + "         tblDctosOrdenesDetalle.referenciaCliente, "
               + "         tblDctosOrdenesDetalle.fechaEntrega,      "
               + "         tblDctosOrdenes.idLog,                    "
               + "         tblDctosOrdenes.numeroOrden,              "
               + "         tblDctosOrdenes.idFicha,                  "
               + "         tblDctosOrdenes.idCliente,                "
               + "         tblDctosOrdenes.idUsuario,                "
               + "         tblDctosOrdenes.ordenCompra,              "
               + "         tblDctosOrdenes.fechaOrden,               "
               + "        tblDctosOrdenesDetalle.idEstadoRefOriginal,"
               + "        tblDctosOrdenesDetalle.pesoPedido,         "
               + "        tblDctosOrdenesDetalle.pesoTerminado,      "
               + "        tblDctosOrdenesDetalle.pesoRetal,          "
               + "        tblDctosOrdenesDetalle.cantidadFacturada,  "
               + "        tblDctosOrdenesDetalle.cantidadTerminada   "
               + "   FROM       tblDctosOrdenes                      "
               + "   INNER JOIN tblDctosOrdenesDetalle               "
               + "   ON tblDctosOrdenes.IDLOCAL      =               "
               + "                   tblDctosOrdenesDetalle.IDLOCAL  "
               + "   AND tblDctosOrdenes.IDTIPOORDEN =               "
               + "               tblDctosOrdenesDetalle.IDTIPOORDEN  "
               + "   AND tblDctosOrdenes.IDORDEN     =               "
               + "                   tblDctosOrdenesDetalle.IDORDEN) "
               + "                                         AS tmpDET "
               + "  ON tmpDET.idLog = tblAgendaLogVisitas.idLog      "
               + "  WHERE tblAgendaLogVisitas.IDLOG IN (             "
               + "     SELECT tblAgendaLogVisitas.IDLOG              "
               + "     FROM   tblAgendaLogVisitas                    "
               + "     INNER JOIN tblDctosOrdenes                    "
               + "     ON tblAgendaLogVisitas.IDLOG     =            "
               + "                      tblDctosOrdenes.IDLOG        "
               + " AND tmpDET.cantidadTerminada         <=           "
               + "                          tmpDET.cantidadFacturada "
               + " AND tmpDET.cantidadFacturada         > 0          "
               + "  AND tmpDET.pesoPedido               > 0          "
               + "  AND tmpDET.idEstadoRefOriginal     != 9          "
               + "  AND   tblDctosOrdenes.idLocal       =            "
               + getIdLocal() + "                                    "
               + "  AND   tblDctosOrdenes.idTipoOrden   =            "
               + getIdTipoOrden() + "                                "
               + "  AND   tblDctosOrdenes.numeroOrden  != 0          "
               + "  AND tblTerceros.idTipoTercero       =            "
               + xIdTipoTercero + " )                                "
               + "  ORDER BY tblTerceros.nombreTercero,              "
               + "           tmpDET.numeroOrden,                     "
               + "           tmpDET.fechaEntrega                     ";

        PreparedStatement sentenciaSQL = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            sentenciaSQL = connection.prepareStatement(insertStr);

            // Obtiene el resultset
            ResultSet rs = sentenciaSQL.executeQuery();

            // Variable de fachada de los datos
            FachadaColaboraDctoOrdenBean fachadaBean;

            while (rs.next()) {

                //
                fachadaBean = new FachadaColaboraDctoOrdenBean();

                //
                fachadaBean.setIdLog(rs.getInt("idLog"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setIdUsuario(rs.getDouble("idUsuario"));
                fachadaBean.setFechaEntrega(rs.getString("fechaEntrega"));
                fachadaBean.setNombreTercero(rs.getString("nombreTercero"));
                fachadaBean.setDireccionDespacho(
                                              rs.getString("direccionTercero"));
                fachadaBean.setTelefonoFijo(rs.getString("telefonoFijo"));
                fachadaBean.setNombreEmpresa(rs.getString("nombreEmpresa"));
                fachadaBean.setCiudadTercero(rs.getString("ciudadTercero"));
                fachadaBean.setNombreUsuario(rs.getString("NOMBREUSUARIO"));
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setIdOrden(rs.getInt("idOrden"));
                fachadaBean.setItem(rs.getInt("item"));
                fachadaBean.setCantidad(rs.getDouble("cantidad"));
                fachadaBean.setReferenciaCliente(
                                             rs.getString("referenciaCliente"));
                fachadaBean.setNumeroOrden(rs.getInt("numeroOrden"));
                fachadaBean.setIdFicha(rs.getInt("idFicha"));
                fachadaBean.setOrdenCompra(rs.getString("ordenCompra"));
                fachadaBean.setFechaOrden(rs.getString("fechaOrden"));
                fachadaBean.setPesoPedido(rs.getDouble("pesoPedido"));
                fachadaBean.setPesoTerminado(rs.getDouble("pesoTerminado"));
                fachadaBean.setPesoRetal(rs.getDouble("pesoRetal"));
                fachadaBean.setCantidadFacturada(
                                             rs.getDouble("cantidadFacturada"));
                fachadaBean.setCantidadTerminada(
                                             rs.getDouble("cantidadTerminada"));

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
            // conexion
            jdbcAccess.cleanup(connection, sentenciaSQL, null);
            return contenedor;
        }
    }

    // liquidaOrdenFCH
    public FachadaColaboraDctoOrdenBean liquidaOrdenFCH() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        // Variable de fachada de los datos
        FachadaColaboraDctoOrdenBean fachadaBean = new FachadaColaboraDctoOrdenBean();

        Connection connection = null;

        String selectStr =
                "SELECT tblDctosOrdenes.idLocal,                         "
                + "       tblDctosOrdenes.idTipoOrden,                     "
                + "       tblDctosOrdenes.idOrden,                         "
                + "       tblDctosOrdenes.idLog,                           "
                + "       tblDctosOrdenes.idCliente,                       "
                + "       tblDctosOrdenes.idUsuario,                       "
                + "       tblDctosOrdenes.fechaOrden,                      "
                + "       tblDctosOrdenes.idEstadoTx,                      "
                + "       COUNT(tblDctosOrdenesDetalle.idPlu)              "
                + "                        AS cantidadArticulos,           "
                + "       SUM ( tblDctosOrdenesDetalle.cantidad         *  "
                + "             tblDctosOrdenesDetalle.vrVentaUnitario )   "
                + "                        AS vrVentaConIva,               "
                + "       SUM ( tblDctosOrdenesDetalle.cantidad         *  "
                + "             tblDctosOrdenesDetalle.vrVentaUnitario  /  "
                + "  (1 + (tblDctosOrdenesDetalle.porcentajeIva/100)))     "
                + "                        AS vrVentaSinIva,               "
                + "       SUM(tblDctosOrdenesDetalle.pesoTeorico *         "
                + "           tblDctosOrdenesDetalle.cantidad)             "
                + "                        AS pesoTeoricoTotal,            "
                + "       SUM(tblDctosOrdenesDetalle.vrCosto *             "
                + "           tblDctosOrdenesDetalle.cantidad)             "
                + "                                   AS vrCostoConIva,    "
                + "       SUM ( tblDctosOrdenesDetalle.cantidad *          "
                + "             tblDctosOrdenesDetalle.vrCosto  /          "
                + "  (1 + (tblDctosOrdenesDetalle.porcentajeIva/100)))     "
                + "                                   AS vrCostoSinIva     "
                + "FROM  tblDctosOrdenes ,                                 "
                + "      tblDctosOrdenesDetalle                            "
                + "WHERE tblDctosOrdenes.idOrden         =                 "
                + "      tblDctosOrdenesDetalle.idOrden                    "
                + "AND   tblDctosOrdenes.idTipoOrden     =                 "
                + "      tblDctosOrdenesDetalle.idTipoOrden                "
                + "AND   tblDctosOrdenes.idLocal         =                 "
                + "      tblDctosOrdenesDetalle.idLocal                    "
                + "AND   tblDctosOrdenes.idLog           =                 "
                + getIdLog() + "                                           "
                + "AND   tblDctosOrdenes.idTipoOrden     =                 "
                + getIdTipoOrden() + "                                     "
                + "GROUP BY tblDctosOrdenes.idLocal,                       "
                + "       tblDctosOrdenes.idTipoOrden,                     "
                + "       tblDctosOrdenes.idOrden,                         "
                + "       tblDctosOrdenes.idLog,                           "
                + "       tblDctosOrdenes.idCliente,                       "
                + "       tblDctosOrdenes.idUsuario,                       "
                + "       tblDctosOrdenes.fechaOrden,                      "
                + "       tblDctosOrdenes.idEstadoTx                       ";

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
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("IdTipoOrden"));
                fachadaBean.setIdOrden(rs.getInt("IdOrden"));
                fachadaBean.setIdLog(rs.getInt("idLog"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setIdUsuario(rs.getDouble("idUsuario"));
                fachadaBean.setFechaOrden(
                        rs.getString("fechaOrden").substring(0, 11));
                fachadaBean.setIdEstadoTx(rs.getInt("idEstadoTx"));
                fachadaBean.setCantidadArticulos(
                        rs.getInt("cantidadArticulos"));
                fachadaBean.setVrVentaSinIva(rs.getInt("vrVentaSinIva"));
                fachadaBean.setVrVentaConIva(rs.getInt("vrVentaConIva"));
                fachadaBean.setPesoTeoricoTotal(
                        rs.getDouble("pesoTeoricoTotal"));
                fachadaBean.setVrCostoConIva(rs.getDouble("vrCostoConIva"));
                fachadaBean.setVrCostoSinIva(rs.getDouble("vrCostoSinIva"));

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

    // listaOrdenFCH
    public FachadaColaboraDctoOrdenBean listaOrdenFCH() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        // Variable de fachada de los datos
        FachadaColaboraDctoOrdenBean fachadaBean = new FachadaColaboraDctoOrdenBean();

        Connection connection = null;

        String selectStr =
                "SELECT tblDctosOrdenes.idLocal,                         "
                + "       tblDctosOrdenes.idTipoOrden,                     "
                + "       tblDctosOrdenes.idOrden,                         "
                + "       tblDctosOrdenes.idLog,                           "
                + "       tblDctosOrdenes.idCliente,                       "
                + "       tblDctosOrdenes.idUsuario,                       "
                + "       tblDctosOrdenes.fechaOrden,                      "
                + "       tblDctosOrdenes.idEstadoTx,                      "
                + "       tblDctosOrdenes.idVendedor,                      "
                + "       COUNT(tblDctosOrdenesDetalle.idPlu)              "
                + "                        AS cantidadArticulos,           "
                + "       SUM ( tblDctosOrdenesDetalle.cantidad         *  "
                + "             tblDctosOrdenesDetalle.vrVentaUnitario )   "
                + "                        AS vrVentaConIva,               "
                + "       SUM ( tblDctosOrdenesDetalle.cantidad         *  "
                + "             tblDctosOrdenesDetalle.vrVentaUnitario  /  "
                + "  (1 + (tblDctosOrdenesDetalle.porcentajeIva/100)))     "
                + "                        AS vrVentaSinIva,               "
                + "       SUM(tblDctosOrdenesDetalle.pesoTeorico *         "
                + "           tblDctosOrdenesDetalle.cantidad)             "
                + "                        AS pesoTeoricoTotal,            "
                + "       SUM(tblDctosOrdenesDetalle.vrCosto *             "
                + "           tblDctosOrdenesDetalle.cantidad)             "
                + "                                   AS vrCostoConIva,    "
                + "       SUM ( tblDctosOrdenesDetalle.cantidad *          "
                + "             tblDctosOrdenesDetalle.vrCosto  /          "
                + "  (1 + (tblDctosOrdenesDetalle.porcentajeIva/100)))     "
                + "                                   AS vrCostoSinIva     "
                + "FROM  tblDctosOrdenes ,                                 "
                + "      tblDctosOrdenesDetalle                            "
                + "WHERE tblDctosOrdenes.idOrden         =                 "
                + "      tblDctosOrdenesDetalle.idOrden                    "
                + "AND   tblDctosOrdenes.idTipoOrden     =                 "
                + "      tblDctosOrdenesDetalle.idTipoOrden                "
                + "AND   tblDctosOrdenes.idLocal         =                 "
                + "      tblDctosOrdenesDetalle.idLocal                    "
                + "AND   tblDctosOrdenes.idLocal         =                 "
                + getIdLocal() + "                                         "
                + "AND   tblDctosOrdenes.idOrden         =                 "
                + getIdOrden() + "                                         "
                + "AND   tblDctosOrdenes.idTipoOrden     =                 "
                + getIdTipoOrden() + "                                     "
                + "GROUP BY tblDctosOrdenes.idLocal,                       "
                + "       tblDctosOrdenes.idTipoOrden,                     "
                + "       tblDctosOrdenes.idOrden,                         "
                + "       tblDctosOrdenes.idLog,                           "
                + "       tblDctosOrdenes.idCliente,                       "
                + "       tblDctosOrdenes.idUsuario,                       "
                + "       tblDctosOrdenes.fechaOrden,                      "
                + "       tblDctosOrdenes.idEstadoTx,                      "
                + "       tblDctosOrdenes.idVendedor                       " ;


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
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("IdTipoOrden"));
                fachadaBean.setIdOrden(rs.getInt("IdOrden"));
                fachadaBean.setIdLog(rs.getInt("idLog"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setIdUsuario(rs.getDouble("idUsuario"));
                fachadaBean.setFechaOrden(
                        rs.getString("fechaOrden").substring(0, 11));
                fachadaBean.setIdEstadoTx(rs.getInt("idEstadoTx"));
                fachadaBean.setCantidadArticulos(
                        rs.getInt("cantidadArticulos"));
                fachadaBean.setVrVentaSinIva(rs.getInt("vrVentaSinIva"));
                fachadaBean.setVrVentaConIva(rs.getInt("vrVentaConIva"));
                fachadaBean.setPesoTeoricoTotal(
                        rs.getDouble("pesoTeoricoTotal"));
                fachadaBean.setVrCostoConIva(rs.getDouble("vrCostoConIva"));
                fachadaBean.setVrCostoSinIva(rs.getDouble("vrCostoSinIva"));
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

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {

            // Cierra la conexion
            jdbcAccess.cleanup(connection, selectStatement, null);
            return fachadaBean;

        }
    }

    // listaOTFCH
    public FachadaColaboraDctoOrdenBean listaOTFCH() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        // Variable de fachada de los datos
        FachadaColaboraDctoOrdenBean fachadaBean = new FachadaColaboraDctoOrdenBean();

        Connection connection = null;

        String selectStr =
                "SELECT tblDctosOrdenes.idLocal,            "
                + "       tblDctosOrdenes.idTipoOrden,      "
                + "       tblDctosOrdenes.idOrden,          "
                + "       tblDctosOrdenes.idLog,            "
                + "       tblDctosOrdenes.idCliente,        "
                + "       tblDctosOrdenes.idUsuario,        "
                + "       tblDctosOrdenes.fechaOrden,       "
                + "       tblDctosOrdenes.idEstadoTx,       "
                + "       tblDctosOrdenes.idVendedor,       "
                + "       tblDctosOrdenes.numeroOrden,      "
                + "       tblDctosOrdenes.diasInventario,   "
                + "       tblDctosOrdenes.diasHistoria,     "
                + "       tblDctosOrdenesDetalle.idPlu,     "
                + "       tblDctosOrdenes.idFicha           "
                + "FROM  tblDctosOrdenes ,                  "
                + "      tblDctosOrdenesDetalle             "
                + "WHERE tblDctosOrdenes.idOrden         =  "
                + "      tblDctosOrdenesDetalle.idOrden     "
                + "AND   tblDctosOrdenes.idTipoOrden     =  "
                + "      tblDctosOrdenesDetalle.idTipoOrden "
                + "AND   tblDctosOrdenes.idLocal         =  "
                + "      tblDctosOrdenesDetalle.idLocal     "
                + "AND   tblDctosOrdenes.idLocal         =  "
                + getIdLocal() + "                          "
                + "AND   tblDctosOrdenes.idTipoOrden     =  "
                + getIdTipoOrden() + "                      "
                + "AND   tblDctosOrdenes.numeroOrden     =  "
                + getNumeroOrden() ;

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
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("IdTipoOrden"));
                fachadaBean.setIdOrden(rs.getInt("IdOrden"));
                fachadaBean.setIdLog(rs.getInt("idLog"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setIdUsuario(rs.getDouble("idUsuario"));
                fachadaBean.setFechaOrden(
                        rs.getString("fechaOrden").substring(0, 11));
                fachadaBean.setIdEstadoTx(rs.getInt("idEstadoTx"));
                fachadaBean.setIdVendedor(rs.getDouble("idVendedor"));
                fachadaBean.setNumeroOrden(rs.getInt("numeroOrden"));
                fachadaBean.setDiasInventario(rs.getInt("diasInventario"));
                fachadaBean.setDiasHistoria(rs.getInt("diasHistoria"));
                fachadaBean.setIdPlu(rs.getInt("idPlu"));
                fachadaBean.setIdFicha(rs.getInt("idFicha"));

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

    // liquidaCompraFCH
    public FachadaColaboraDctoOrdenBean liquidaCompraFCH() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        // Variable de fachada de los datos
        FachadaColaboraDctoOrdenBean fachadaBean = new FachadaColaboraDctoOrdenBean();

        Connection connection = null;

        String selectStr =
                "SELECT tblDctosOrdenes.idLocal,                         "
                + "       tblDctosOrdenes.idTipoOrden,                     "
                + "       tblDctosOrdenes.idOrden,                         "
                + "       tblDctosOrdenes.idLog,                           "
                + "       tblDctosOrdenes.idCliente,                       "
                + "       tblDctosOrdenes.idUsuario,                       "
                + "       tblDctosOrdenes.fechaOrden,                      "
                + "       tblDctosOrdenes.idEstadoTx,                      "
                + "       COUNT(tblDctosOrdenesDetalle.idPlu)              "
                + "                        AS cantidadArticulos,           "
                + "       SUM ( tblDctosOrdenesDetalle.cantidad         *  "
                + "             tblDctosOrdenesDetalle.vrVentaUnitario )   "
                + "                        AS vrVentaConIva,               "
                + "       SUM ( tblDctosOrdenesDetalle.cantidad         *  "
                + "             tblDctosOrdenesDetalle.vrVentaUnitario  /  "
                + "  (1 + (tblDctosOrdenesDetalle.porcentajeIva/100)))     "
                + "                        AS vrVentaSinIva,               "
                + "       SUM(tblDctosOrdenesDetalle.pesoTeorico *         "
                + "           tblDctosOrdenesDetalle.cantidad)             "
                + "                        AS pesoTeoricoTotal,            "
                + "       SUM(tblDctosOrdenesDetalle.vrCosto *             "
                + "           tblDctosOrdenesDetalle.cantidad)             "
                + "                                   AS vrCostoSinIva,    "
                + "       SUM ( tblDctosOrdenesDetalle.cantidad *          "
                + "             tblDctosOrdenesDetalle.vrCosto  *          "
                + "  (1 + (tblDctosOrdenesDetalle.porcentajeIva/100)))     "
                + "                                   AS vrCostoConIva     "
                + "FROM  tblDctosOrdenes ,                                 "
                + "      tblDctosOrdenesDetalle                            "
                + "WHERE tblDctosOrdenes.idOrden         =                 "
                + "      tblDctosOrdenesDetalle.idOrden                    "
                + "AND   tblDctosOrdenes.idTipoOrden     =                 "
                + "      tblDctosOrdenesDetalle.idTipoOrden                "
                + "AND   tblDctosOrdenes.idLocal         =                 "
                + "      tblDctosOrdenesDetalle.idLocal                    "
                + "AND   tblDctosOrdenes.idLog           =                 "
                + getIdLog() + "                                           "
                + "AND   tblDctosOrdenes.idTipoOrden     =                 "
                + getIdTipoOrden() + "                                     "
                + "GROUP BY tblDctosOrdenes.idLocal,                       "
                + "       tblDctosOrdenes.idTipoOrden,                     "
                + "       tblDctosOrdenes.idOrden,                         "
                + "       tblDctosOrdenes.idLog,                           "
                + "       tblDctosOrdenes.idCliente,                       "
                + "       tblDctosOrdenes.idUsuario,                       "
                + "       tblDctosOrdenes.fechaOrden,                      "
                + "       tblDctosOrdenes.idEstadoTx                       ";

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
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("IdTipoOrden"));
                fachadaBean.setIdOrden(rs.getInt("IdOrden"));
                fachadaBean.setIdLog(rs.getInt("idLog"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setIdUsuario(rs.getDouble("idUsuario"));
                fachadaBean.setFechaOrden(
                        rs.getString("fechaOrden").substring(0, 11));
                fachadaBean.setIdEstadoTx(rs.getInt("idEstadoTx"));
                fachadaBean.setCantidadArticulos(
                        rs.getInt("cantidadArticulos"));
                fachadaBean.setVrVentaSinIva(rs.getInt("vrVentaSinIva"));
                fachadaBean.setVrVentaConIva(rs.getInt("vrVentaConIva"));
                fachadaBean.setPesoTeoricoTotal(
                        rs.getDouble("pesoTeoricoTotal"));
                fachadaBean.setVrCostoSinIva(rs.getDouble("vrCostoSinIva"));
                fachadaBean.setVrCostoConIva(rs.getDouble("vrCostoConIva"));

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

    // liquidaOrdenLog
    public Vector liquidaOrdenLog() {

        //Averigua en tiempo de ejecucion  
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        // Variable de fachada de los datos
        FachadaColaboraDctoOrdenBean fachadaBean;

        boolean validaOk = false;
        Connection connection = null;

        String selectStr =
                "SELECT tblDctosOrdenes.idLocal,                         "
                + "       tblDctosOrdenes.idTipoOrden,                     "
                + "       tblDctosOrdenes.idOrden,                         "
                + "       tblDctosOrdenes.idLog,                           "
                + "       tblDctosOrdenes.idCliente,                       "
                + "       tblDctosOrdenes.idUsuario,                       "
                + "       tblDctosOrdenes.fechaOrden,                      "
                + "       tblDctosOrdenes.idEstadoTx,                      "
                + "       COUNT(tblDctosOrdenesDetalle.idPlu)              "
                + "                        AS cantidadArticulos,           "
                + "       SUM ( tblDctosOrdenesDetalle.cantidad         *  "
                + "             tblDctosOrdenesDetalle.vrVentaUnitario )   "
                + "                        AS vrVentaConIva,               "
                + "       SUM ( tblDctosOrdenesDetalle.cantidad         *  "
                + "             tblDctosOrdenesDetalle.vrVentaUnitario  /  "
                + "  (1 + (tblDctosOrdenesDetalle.porcentajeIva/100)))     "
                + "                        AS vrVentaSinIva,               "
                + "       SUM(tblDctosOrdenesDetalle.pesoTeorico *         "
                + "           tblDctosOrdenesDetalle.cantidad)             "
                + "                        AS pesoTeoricoTotal,            "
                + "       SUM(tblDctosOrdenesDetalle.vrCosto *             "
                + "           tblDctosOrdenesDetalle.cantidad)             "
                + "                                   AS vrCostoConIva,    "
                + "       SUM ( tblDctosOrdenesDetalle.cantidad *          "
                + "             tblDctosOrdenesDetalle.vrCosto  /          "
                + "  (1 + (tblDctosOrdenesDetalle.porcentajeIva/100)))     "
                + "                                   AS vrCostoSinIva     "
                + "FROM  tblDctosOrdenes ,                                 "
                + "      tblDctosOrdenesDetalle                            "
                + "WHERE tblDctosOrdenes.idOrden         =                 "
                + "      tblDctosOrdenesDetalle.idOrden                    "
                + "AND   tblDctosOrdenes.idTipoOrden     =                 "
                + "      tblDctosOrdenesDetalle.idTipoOrden                "
                + "AND   tblDctosOrdenes.idLocal         =                 "
                + "      tblDctosOrdenesDetalle.idLocal                    "
                + "AND   tblDctosOrdenes.idLog           =                 "
                + getIdLog() + "                                           "
                + "AND   tblDctosOrdenes.idTipoOrden     =                 "
                + getIdTipoOrden() + "                                     "
                + "GROUP BY tblDctosOrdenes.idLocal,                       "
                + "       tblDctosOrdenes.idTipoOrden,                     "
                + "       tblDctosOrdenes.idOrden,                         "
                + "       tblDctosOrdenes.idLog,                           "
                + "       tblDctosOrdenes.idCliente,                       "
                + "       tblDctosOrdenes.idUsuario,                       "
                + "       tblDctosOrdenes.fechaOrden,                      "
                + "       tblDctosOrdenes.idEstadoTx                       ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            if (rs.next()) {

                fachadaBean = new FachadaColaboraDctoOrdenBean();

                //
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("IdTipoOrden"));
                fachadaBean.setIdOrden(rs.getInt("IdOrden"));
                fachadaBean.setIdLog(rs.getInt("idLog"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setIdUsuario(rs.getDouble("idUsuario"));
                fachadaBean.setFechaOrden(
                        rs.getString("fechaOrden").substring(0, 11));
                fachadaBean.setIdEstadoTx(rs.getInt("idEstadoTx"));
                fachadaBean.setCantidadArticulos(
                        rs.getInt("cantidadArticulos"));
                fachadaBean.setVrVentaSinIva(rs.getInt("vrVentaSinIva"));
                fachadaBean.setVrVentaConIva(rs.getInt("vrVentaConIva"));
                fachadaBean.setPesoTeoricoTotal(
                        rs.getDouble("pesoTeoricoTotal"));
                fachadaBean.setVrCostoConIva(rs.getDouble("vrCostoConIva"));
                fachadaBean.setVrCostoSinIva(rs.getDouble("vrCostoSinIva"));

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

    // liquidaOrdenLocal
    public Vector liquidaOrdenLocal() {

        //Averigua en tiempo de ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        // Variable de fachada de los datos
        FachadaColaboraDctoOrdenBean fachadaBean;

        Connection connection = null;

        String selectStr =
                "SELECT tblDctosOrdenes.idLocal,                         "
                + "       tblDctosOrdenes.idTipoOrden,                     "
                + "       tblDctosOrdenes.idOrden,                         "
                + "       tblDctosOrdenes.idLog,                           "
                + "       tblDctosOrdenes.idCliente,                       "
                + "       tblDctosOrdenes.idUsuario,                       "
                + "       tblDctosOrdenes.fechaOrden,                      "
                + "       tblDctosOrdenes.idEstadoTx,                      "
                + "       COUNT(tblDctosOrdenesDetalle.idPlu)              "
                + "                        AS cantidadArticulos,           "
                + "       SUM ( tblDctosOrdenesDetalle.cantidad         *  "
                + "             tblDctosOrdenesDetalle.vrVentaUnitario )   "
                + "                        AS vrVentaConIva,               "
                + "       SUM ( tblDctosOrdenesDetalle.cantidad         *  "
                + "             tblDctosOrdenesDetalle.vrVentaUnitario  /  "
                + "  (1 + (tblDctosOrdenesDetalle.porcentajeIva/100)))     "
                + "                        AS vrVentaSinIva,               "
                + "       SUM(tblDctosOrdenesDetalle.pesoTeorico *         "
                + "           tblDctosOrdenesDetalle.cantidad)             "
                + "                        AS pesoTeoricoTotal,            "
                + "       SUM(tblDctosOrdenesDetalle.vrCosto *             "
                + "           tblDctosOrdenesDetalle.cantidad)             "
                + "                                   AS vrCostoConIva,    "
                + "       SUM ( tblDctosOrdenesDetalle.cantidad *          "
                + "             tblDctosOrdenesDetalle.vrCosto  /          "
                + "  (1 + (tblDctosOrdenesDetalle.porcentajeIva/100)))     "
                + "                                   AS vrCostoSinIva     "
                + "FROM  tblDctosOrdenes ,                                 "
                + "      tblDctosOrdenesDetalle                            "
                + "WHERE tblDctosOrdenes.idOrden         =                 "
                + "      tblDctosOrdenesDetalle.idOrden                    "
                + "AND   tblDctosOrdenes.idTipoOrden     =                 "
                + "      tblDctosOrdenesDetalle.idTipoOrden                "
                + "AND   tblDctosOrdenes.idLocal         =                 "
                + "      tblDctosOrdenesDetalle.idLocal                    "
                + "AND   tblDctosOrdenes.idLocal         =                 "
                + getIdLocal() + "                                         "
                + "AND   tblDctosOrdenes.idLog           =                 "
                + getIdLog() + "                                           "
                + "AND   tblDctosOrdenes.idTipoOrden     =                 "
                + getIdTipoOrden() + "                                     "
                + "GROUP BY tblDctosOrdenes.idLocal,                       "
                + "       tblDctosOrdenes.idTipoOrden,                     "
                + "       tblDctosOrdenes.idOrden,                         "
                + "       tblDctosOrdenes.idLog,                           "
                + "       tblDctosOrdenes.idCliente,                       "
                + "       tblDctosOrdenes.idUsuario,                       "
                + "       tblDctosOrdenes.fechaOrden,                      "
                + "       tblDctosOrdenes.idEstadoTx                       ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            if (rs.next()) {

                fachadaBean = new FachadaColaboraDctoOrdenBean();

                //
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("IdTipoOrden"));
                fachadaBean.setIdOrden(rs.getInt("IdOrden"));
                fachadaBean.setIdLog(rs.getInt("idLog"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setIdUsuario(rs.getDouble("idUsuario"));
                fachadaBean.setFechaOrden(
                        rs.getString("fechaOrden").substring(0, 11));
                fachadaBean.setIdEstadoTx(rs.getInt("idEstadoTx"));
                fachadaBean.setCantidadArticulos(
                        rs.getInt("cantidadArticulos"));
                fachadaBean.setVrVentaSinIva(rs.getInt("vrVentaSinIva"));
                fachadaBean.setVrVentaConIva(rs.getInt("vrVentaConIva"));
                fachadaBean.setPesoTeoricoTotal(
                        rs.getDouble("pesoTeoricoTotal"));
                fachadaBean.setVrCostoConIva(rs.getDouble("vrCostoConIva"));
                fachadaBean.setVrCostoSinIva(rs.getDouble("vrCostoSinIva"));

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

    // liquidaLog
    public FachadaColaboraDctoOrdenBean liquidaLog() {
// acaaca
        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        // Variable de fachada de los datos
        FachadaColaboraDctoOrdenBean fachadaBean = new FachadaColaboraDctoOrdenBean();

        boolean validaOk = false;
        Connection connection = null;

        String selectStr =
                "SELECT tblDctosOrdenes.idLocal,                         "
                + "       tblDctosOrdenes.idTipoOrden,                     "
                + "       tblDctosOrdenes.idLog,                           "
                + "       COUNT(tblDctosOrdenesDetalle.idPlu)              "
                + "                        AS cantidadArticulos,           "
                + "        SUM (tblDctosOrdenesDetalle.cantidad         *  "
                + "              tblDctosOrdenesDetalle.vrVentaUnitario )  "
                + "                                      AS vrVentaConIva, "
                + "        SUM((tblDctosOrdenesDetalle.cantidad        *   "
                + "              tblDctosOrdenesDetalle.vrVentaUnitario) / "
                + "  ( 1 + ( tblDctosOrdenesDetalle.porcentajeIva / 100))) "
                + "                                      AS vrVentaSinIva, "
                + "  SUM(((tblDctosOrdenesDetalle.cantidad        *		 "
                + "      tblDctosOrdenesDetalle.vrVentaUnitario) /		 "
                + "  (1 + (tblDctosOrdenesDetalle.porcentajeIva / 100))) * "
                + "( 1 - ( tblDctosOrdenesDetalle.porcentajeDscto / 100))* "
                + "       ( tblDctosOrdenesDetalle.porcentajeIva / 100 ))  "
                + "                                     AS vrIva,          "
                + "  SUM(tblDctosOrdenesDetalle.pesoTeorico *              "
                + "                       tblDctosOrdenesDetalle.cantidad) "
                + "                                   AS pesoTeoricoTotal, "
                + "                  SUM(tblDctosOrdenesDetalle.vrCosto *  "
                + "                      tblDctosOrdenesDetalle.cantidad)  "
                + "                                     AS vrCostoConIva,  "
                + "               SUM( (tblDctosOrdenesDetalle.cantidad *  "
                + "                     tblDctosOrdenesDetalle.vrCosto ) / "
                + "     (1 + (tblDctosOrdenesDetalle.porcentajeIva/100)))  "
                + "                                     AS vrCostoSinIva,  "
                + "          SUM(((tblDctosOrdenesDetalle.cantidad       * "
                + "             tblDctosOrdenesDetalle.vrVentaUnitario)  / "
                + " (1 + ( tblDctosOrdenesDetalle.porcentajeIva / 100))) * "
                + "( 1 - ( tblDctosOrdenesDetalle.porcentajeDscto / 100))) "
                + "                                    AS vrVentaSinDscto  "
                + "FROM  tblDctosOrdenes ,                                 "
                + "      tblDctosOrdenesDetalle                            "
                + "WHERE tblDctosOrdenes.idOrden         =                 "
                + "      tblDctosOrdenesDetalle.idOrden                    "
                + "AND   tblDctosOrdenes.idTipoOrden     =                 "
                + "      tblDctosOrdenesDetalle.idTipoOrden                "
                + "AND   tblDctosOrdenes.idLocal         =                 "
                + "      tblDctosOrdenesDetalle.idLocal                    "
                + "AND   tblDctosOrdenes.idLog           =                 "
                + getIdLog() + "                                           "
                + "AND   tblDctosOrdenes.idLocal         =                 "
                + getIdLocal() + "                                         "
                + "AND   tblDctosOrdenes.idTipoOrden     =                 "
                + getIdTipoOrden() + "                                     "
                + "GROUP BY tblDctosOrdenes.idLocal,                       "
                + "       tblDctosOrdenes.idTipoOrden,                     "
                + "       tblDctosOrdenes.idLog                            ";

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
                fachadaBean = new FachadaColaboraDctoOrdenBean();

                //
                fachadaBean.setCantidadArticulos(
                        rs.getInt("cantidadArticulos"));
                fachadaBean.setVrVentaConIva(rs.getDouble("vrVentaConIva"));
                fachadaBean.setVrVentaSinIva(rs.getDouble("vrVentaSinIva"));
                fachadaBean.setVrIva(rs.getDouble("vrIva"));
                fachadaBean.setPesoTeoricoTotal(
                        rs.getDouble("pesoTeoricoTotal"));
                fachadaBean.setVrCostoConIva(rs.getDouble("vrCostoConIva"));
                fachadaBean.setVrCostoSinIva(rs.getDouble("vrCostoSinIva"));
                fachadaBean.setVrVentaSinDscto(rs.getDouble("vrVentaSinDscto"));

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

    // liquida
    public FachadaColaboraDctoOrdenBean liquida() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        // Variable de fachada de los datos
        FachadaColaboraDctoOrdenBean fachadaBean =
                new FachadaColaboraDctoOrdenBean();

        boolean validaOk = false;
        Connection connection = null;

        String selectStr =
                "SELECT tblDctosOrdenes.idLocal,                         "
                + "       tblDctosOrdenes.idTipoOrden,                     "
                + "       tblDctosOrdenes.idOrden,                         "
                + "       tblDctosOrdenes.idLog,                           "
                + "       tblDctosOrdenes.idCliente,                       "
                + "       tblDctosOrdenes.idUsuario,                       "
                + "       tblDctosOrdenes.fechaOrden,                      "
                + "       COUNT(tblDctosOrdenesDetalle.idPlu)              "
                + "                        AS cantidadArticulos,           "
                + "       SUM ( tblDctosOrdenesDetalle.cantidad          * "
                + "             tblDctosOrdenesDetalle.vrVentaUnitario )   "
                + "                        AS vrVentaConIva,               "
                + "       SUM ( tblDctosOrdenesDetalle.cantidad          * "
                + "             tblDctosOrdenesDetalle.vrVentaUnitario   / "
                + "  (1 + (tblDctosOrdenesDetalle.porcentajeIva/100)))     "
                + "                        AS vrVentaSinIva,               "
                + "       SUM ( tblDctosOrdenesDetalle.cantidad                     * "
                + "             tblDctosOrdenesDetalle.vrVentaUnitario              / "
                + "             (1 - (tblDctosOrdenesDetalle.porcentajeDscto/100))  * "
                + "             (1 + (tblDctosOrdenesDetalle.porcentajeIva/100)))   - "
                + "       SUM ( tblDctosOrdenesDetalle.cantidad                     * "
                + "             tblDctosOrdenesDetalle.vrVentaUnitario              * "
                + "             (1 - (tblDctosOrdenesDetalle.porcentajeDscto/100))) - "
                + "             AS vrIva,                                             "
                + "       SUM(tblDctosOrdenesDetalle.pesoTeorico *         "
                + "           tblDctosOrdenesDetalle.cantidad)             "
                + "                        AS pesoTeoricoTotal             "
                + "FROM  tblDctosOrdenes ,                                 "
                + "      tblDctosOrdenesDetalle                            "
                + "WHERE tblDctosOrdenes.idOrden         =                 "
                + "      tblDctosOrdenesDetalle.idOrden                    "
                + "AND   tblDctosOrdenes.idTipoOrden     =                 "
                + "      tblDctosOrdenesDetalle.idTipoOrden                "
                + "AND   tblDctosOrdenes.idLocal         =                 "
                + "      tblDctosOrdenesDetalle.idLocal                    "
                + "AND   tblDctosOrdenes.idLog           = ( ? )           "
                + "AND   tblDctosOrdenes.idCliente       = ( ? )           "
                + "AND   tblDctosOrdenes.idUsuario       = ( ? )           "
                + "GROUP BY tblDctosOrdenes.idLocal,                       "
                + "       tblDctosOrdenes.idTipoOrden,                     "
                + "       tblDctosOrdenes.idOrden,                         "
                + "       tblDctosOrdenes.idLog,                           "
                + "       tblDctosOrdenes.idCliente,                       "
                + "       tblDctosOrdenes.idUsuario,                       "
                + "       tblDctosOrdenes.fechaOrden                       ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            //inicializa los valores de los parametros
            selectStatement.setInt(1, getIdLog());
            selectStatement.setString(2, getIdCliente());
            selectStatement.setDouble(3, getIdUsuario());

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            if (rs.next()) {

                fachadaBean = new FachadaColaboraDctoOrdenBean();

                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("IdTipoOrden"));
                fachadaBean.setIdOrden(rs.getInt("IdOrden"));
                fachadaBean.setIdLog(rs.getInt("idLog"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setIdUsuario(rs.getDouble("idUsuario"));
                fachadaBean.setFechaOrden(
                        rs.getString("fechaOrden").substring(0, 11));
                fachadaBean.setCantidadArticulos(
                        rs.getInt("cantidadArticulos"));
                fachadaBean.setVrVentaSinIva(rs.getInt("vrVentaSinIva"));
                fachadaBean.setVrVentaConIva(rs.getInt("vrVentaConIva"));
                fachadaBean.setVrIva(rs.getInt("vrIva"));
                fachadaBean.setPesoTeoricoTotal(
                        rs.getDouble("pesoTeoricoTotal"));

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

    // liquidaOrdenIdLog
    public FachadaColaboraDctoOrdenBean liquidaOrdenIdLog() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        // Variable de fachada de los datos
        FachadaColaboraDctoOrdenBean fachadaBean = new FachadaColaboraDctoOrdenBean();

        boolean validaOk = false;
        Connection connection = null;

        String selectStr =
                "SELECT COUNT(tblDctosOrdenesDetalle.IDPLU)                    "
                + "                     AS cantidadArticulos,                    "
                + "       SUM(tblDctosOrdenesDetalle.cantidad        *           "
                + "           tblDctosOrdenesDetalle.vrVentaUnitario )           "
                + "                                           AS vrVentaSinIva,  "
                + "       SUM(tblDctosOrdenesDetalle.cantidad        *           "
                + "           tblDctosOrdenesDetalle.vrVentaUnitario *           "
                + "          (1 + (tblDctosOrdenesDetalle.porcentajeIva/100)))   "
                + "           AS vrVentaConIva,                                  "
                + "       SUM(tblDctosOrdenesDetalle.cantidad                  * "
                + "           tblDctosOrdenesDetalle.vrVentaUnitario           * "
                + "          (1 + (tblDctosOrdenesDetalle.porcentajeIva/100))) - "
                + "           Sum(tblDctosOrdenesDetalle.cantidad              * "
                + "           tblDctosOrdenesDetalle.vrVentaUnitario         )   "
                + "                                                    AS vrIva, "
                + "       SUM(tblDctosOrdenesDetalle.pesoTeorico               * "
                + "           tblDctosOrdenesDetalle.cantidad)                   "
                + "                                         AS pesoTeoricoTotal  "
                + "FROM tblDctosOrdenes,                                         "
                + "     tblDctosOrdenesDetalle                                   "
                + "WHERE tblDctosOrdenes.IDORDEN     =                           "
                + "                          tblDctosOrdenesDetalle.idOrden      "
                + "AND   tblDctosOrdenes.IDTIPOORDEN =                           "
                + "                       tblDctosOrdenesDetalle.idTipoOrden     "
                + "AND   tblDctosOrdenes.IDTIPOORDEN = ( ? )                     "
                + "AND   tblDctosOrdenes.IDLOCAL     =                           "
                + "                          tblDctosOrdenesDetalle.idLocal      "
                + "AND   tblDctosOrdenes.IDLOG       = ( ? )                     ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            //inicializa los valores de los parametros
            selectStatement.setInt(1, getIdTipoOrden());
            selectStatement.setInt(2, getIdLog());

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            if (rs.next()) {

                fachadaBean = new FachadaColaboraDctoOrdenBean();

                fachadaBean.setCantidadArticulos(rs.getInt("cantidadArticulos"));
                fachadaBean.setVrVentaSinIva(rs.getInt("vrVentaSinIva"));
                fachadaBean.setVrIva(rs.getInt("vrIva"));
                fachadaBean.setPesoTeoricoTotal(rs.getDouble("pesoTeoricoTotal"));

                //
                fachadaBean.setVrVentaConIva(fachadaBean.getVrVentaSinIva()
                        + fachadaBean.getVrIva());

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

    // listaDctoIdTipoOrdenIdLog
    public FachadaColaboraDctoOrdenBean listaDctoIdTipoOrdenIdLog() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        // Variable de fachada de los datos
        FachadaColaboraDctoOrdenBean fachadaBean = new FachadaColaboraDctoOrdenBean();

        boolean validaOk = false;
        Connection connection = null;

        String selectStr =
                "SELECT MIN(tblDctosOrdenes.idLocal)      AS idLocal,     "
                + "       tblDctosOrdenes.idTipoOrden,                      "
                + "       tblDctosOrdenes.idOrden,                          "
                + "       tblDctos.idDcto,                                  "
                + "       MIN(tblDctos.diasPlazo) AS diasPlazo,             "
                + "       MIN(tblDctosOrdenes.idLog)  AS idLog,             "
                + "       MIN(tblDctosOrdenes.tipoDcto)     AS tipoDcto,    "
                + "       MIN(tblDctosOrdenes.idCliente)    AS idCliente,   "
                + "       MIN(tblDctosOrdenes.idUsuario)    AS idUsuario,   "
                + "       MIN(tblDctosOrdenes.fechaOrden)   AS fechaOrden,  "
                + "       MIN(tblDctosOrdenes.ordenCompra) AS ordenCompra , "
                + "       MIN(tblDctosOrdenes.descuentoComercial)           "
                + "                                    AS descuentoComercial, "
                + "       MIN(tblDctosOrdenes.impuestoVenta)                "
                + "                                         AS impuestoVenta, "
                + "       MIN(tblDctosOrdenes.idRazon) AS idRazon ,         "
                + "       MIN(tblDctosOrdenes.contacto) AS contacto,        "
                + "       MIN(tblDctosOrdenes.observacion)                  "
                + "                                      AS observacion,      "
                + "       COUNT(tblDctosOrdenesDetalle.idPlu)                 "
                + "                        AS cantidadArticulos,              "
                + "       SUM ( tblDctosOrdenesDetalle.cantidad          *    "
                + "             tblDctosOrdenesDetalle.vrVentaUnitario )      "
                + "                        AS vrVentaConIva ,                 "
                + "       SUM ( tblDctosOrdenesDetalle.cantidad          *    "
                + "             tblDctosOrdenesDetalle.vrVentaUnitario   /    "
                + "  (1 + (tblDctosOrdenesDetalle.porcentajeIva/100)))        "
                + "                        AS vrVentaSinIva,                  "
                + "       SUM ( tblDctosOrdenesDetalle.cantidad                     * "
                + "             tblDctosOrdenesDetalle.vrVentaUnitario )            - "
                + "       SUM ( tblDctosOrdenesDetalle.cantidad                     * "
                + "             tblDctosOrdenesDetalle.vrVentaUnitario              / "
                + "             (1 + (tblDctosOrdenesDetalle.porcentajeIva/100)))     "
                + "             AS vrIva,                                             "
                + "       SUM(tblDctosOrdenesDetalle.pesoTeorico *            "
                + "           tblDctosOrdenesDetalle.cantidad)                "
                + "                        AS pesoTeoricoTotal,               "
                + "       MIN(tblDctosOrdenes.idEstadoTx)    AS idEstadoTx  "
                + "FROM  tblDctosOrdenes ,                                    "
                + "      tblDctosOrdenesDetalle,                              "
                + "      tblDctos                                             "
                + "WHERE tblDctosOrdenes.idOrden         =                    "
                + "      tblDctosOrdenesDetalle.idOrden                       "
                + "AND   tblDctosOrdenes.idTipoOrden     =                    "
                + "      tblDctosOrdenesDetalle.idTipoOrden                   "
                + "AND   tblDctosOrdenes.idLocal         =                    "
                + "      tblDctosOrdenesDetalle.idLocal                       "
                + "AND   tblDctosOrdenes.idOrden         =                    "
                + "      tblDctos.idOrden                                     "
                + "AND   tblDctosOrdenes.idTipoOrden     =                    "
                + "      tblDctos.idTipoOrden                                 "
                + "AND   tblDctosOrdenes.idLocal         =                    "
                + "      tblDctos.idLocal                                     "
                + "AND   tblDctosOrdenes.idOrden         =                    "
                + getIdOrden() + "                                            "
                + "AND   tblDctosOrdenes.idTipoOrden     =                    "
                + getIdTipoOrden() + "                                        "
                + "GROUP BY tblDctosOrdenes.idOrden,                          "
                + "         tblDctosOrdenes.idTipoOrden,                      "
                + "         tblDctos.idDcto                                   ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            if (rs.next()) {

                fachadaBean = new FachadaColaboraDctoOrdenBean();

                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("IdTipoOrden"));
                fachadaBean.setIdOrden(rs.getInt("IdOrden"));
                fachadaBean.setIdLog(rs.getInt("idLog"));
                fachadaBean.setTipoDcto(rs.getString("tipoDcto"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setIdUsuario(rs.getDouble("idUsuario"));
                fachadaBean.setFechaOrden(
                        rs.getString("fechaOrden").substring(0, 11));
                fachadaBean.setOrdenCompra(rs.getString("ordenCompra"));
                fachadaBean.setDescuentoComercial(
                        rs.getString("descuentoComercial"));
                fachadaBean.setImpuestoVenta(rs.getInt("impuestoVenta"));
                fachadaBean.setIdRazon(rs.getString("idRazon"));
                fachadaBean.setContacto(rs.getString("contacto"));
                fachadaBean.setObservacion(rs.getString("observacion"));
                fachadaBean.setCantidadArticulos(
                        rs.getInt("cantidadArticulos"));
                fachadaBean.setVrVentaSinIva(rs.getInt("vrVentaSinIva"));
                fachadaBean.setVrVentaConIva(rs.getInt("vrVentaConIva"));
                fachadaBean.setVrIva(rs.getInt("vrIva"));
                fachadaBean.setPesoTeoricoTotal(
                        rs.getDouble("pesoTeoricoTotal"));
                fachadaBean.setIdEstadoTx(rs.getInt("idEstadoTx"));
                fachadaBean.setIdDcto(rs.getInt("idDcto"));
                fachadaBean.setDiasPlazo(rs.getInt("diasPlazo"));

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

    // listaOrdenIdLog
    public FachadaColaboraDctoOrdenBean listaOrdenIdLog() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        // Variable de fachada de los datos
        FachadaColaboraDctoOrdenBean fachadaBean = new FachadaColaboraDctoOrdenBean();

        boolean validaOk = false;
        Connection connection = null;

        String selectStr =
                "SELECT MIN(tblDctosOrdenes.idLocal)      AS idLocal,         "
                + "       tblDctosOrdenes.idTipoOrden,                        "
                + "       tblDctosOrdenes.idOrden,                            "
                + "       MIN(tblDctosOrdenes.idLog)        AS idLog,         "
                + "       MIN(tblDctosOrdenes.tipoDcto)     AS tipoDcto,      "
                + "       MIN(tblDctosOrdenes.idCliente)    AS idCliente,     "
                + "       MIN(tblDctosOrdenes.idUsuario)    AS idUsuario,     "
                + "       MIN(tblDctosOrdenes.fechaOrden)   AS fechaOrden,    "
                + "       MIN(tblDctosOrdenes.ordenCompra) AS ordenCompra ,   "
                + "       MIN(tblDctosOrdenes.descuentoComercial)             "
                + "                                    AS descuentoComercial, "
                + "       MIN(tblDctosOrdenes.impuestoVenta)                  "
                + "                                         AS impuestoVenta, "
                + "       MIN(tblDctosOrdenes.idRazon) AS idRazon ,           "
                + "       MIN(tblDctosOrdenes.contacto) AS contacto,          "
                + "       MIN(tblDctosOrdenes.observacion)                    "
                + "                                      AS observacion,      "
                + "       MIN(tblDctosOrdenes.ciudadDespacho)                 "
                + "                                      AS ciudadDespacho,   "
                + "       COUNT(tblDctosOrdenesDetalle.idPlu)                 "
                + "                        AS cantidadArticulos,              "
                + "       SUM ( tblDctosOrdenesDetalle.cantidad          *    "
                + "             tblDctosOrdenesDetalle.vrVentaUnitario )      "
                + "                        AS vrVentaSinIva,                  "
                + "       SUM ( tblDctosOrdenesDetalle.cantidad          *    "
                + "             tblDctosOrdenesDetalle.vrVentaUnitario   *    "
                + "  (1 + (tblDctosOrdenesDetalle.porcentajeIva/100)))        "
                + "                        AS vrVentaConIva,                  "
                + "       SUM ( tblDctosOrdenesDetalle.cantidad             * "
                + "             tblDctosOrdenesDetalle.vrVentaUnitario      * "
                + "     (1 + (tblDctosOrdenesDetalle.porcentajeIva/100)))   - "
                + "       SUM ( tblDctosOrdenesDetalle.cantidad             * "
                + "             tblDctosOrdenesDetalle.vrVentaUnitario   )    "
                + "             AS vrIva,                                     "
                + "       SUM(tblDctosOrdenesDetalle.pesoTeorico *            "
                + "           tblDctosOrdenesDetalle.cantidad)                "
                + "                        AS pesoTeoricoTotal,               "
                + "       MIN(tblDctosOrdenes.idEstadoTx)    AS idEstadoTx    "
                + "FROM  tblDctosOrdenes ,                                    "
                + "      tblDctosOrdenesDetalle                               "
                + "WHERE tblDctosOrdenes.idOrden         =                    "
                + "      tblDctosOrdenesDetalle.idOrden                       "
                + "AND   tblDctosOrdenes.idTipoOrden     =                    "
                + "      tblDctosOrdenesDetalle.idTipoOrden                   "
                + "AND   tblDctosOrdenes.idLocal         =                    "
                + "      tblDctosOrdenesDetalle.idLocal                       "
                + "AND   tblDctosOrdenes.idLog           = ( ? )              "
                + "AND   tblDctosOrdenes.idTipoOrden     = ( ? )              "
                + "GROUP BY tblDctosOrdenes.idOrden,                          "
                + "         tblDctosOrdenes.idTipoOrden                       ";


        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            //inicializa los valores de los parametros
            selectStatement.setInt(1, getIdLog());
            selectStatement.setInt(2, getIdTipoOrden());

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            if (rs.next()) {

                fachadaBean = new FachadaColaboraDctoOrdenBean();

                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("IdTipoOrden"));
                fachadaBean.setIdOrden(rs.getInt("IdOrden"));
                fachadaBean.setIdLog(rs.getInt("idLog"));
                fachadaBean.setTipoDcto(rs.getString("tipoDcto"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setIdUsuario(rs.getDouble("idUsuario"));
                fachadaBean.setFechaOrden(rs.getString("fechaOrden").substring(0, 11));
                fachadaBean.setOrdenCompra(rs.getString("ordenCompra"));
                fachadaBean.setDescuentoComercial(rs.getString("descuentoComercial"));
                fachadaBean.setImpuestoVenta(rs.getInt("impuestoVenta"));
                fachadaBean.setIdRazon(rs.getString("idRazon"));
                fachadaBean.setContacto(rs.getString("contacto"));
                fachadaBean.setObservacion(rs.getString("observacion"));
                fachadaBean.setCiudadDespacho(rs.getString("ciudadDespacho"));
                fachadaBean.setCantidadArticulos(rs.getInt("cantidadArticulos"));
                fachadaBean.setVrVentaSinIva(rs.getInt("vrVentaSinIva"));
                fachadaBean.setVrVentaConIva(rs.getInt("vrVentaConIva"));
                fachadaBean.setVrIva(rs.getInt("vrIva"));
                fachadaBean.setPesoTeoricoTotal(rs.getDouble("pesoTeoricoTotal"));
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

    // listaDctoIdTipoOrdenIdOrden
    public FachadaColaboraDctoOrdenBean listaDctoIdTipoOrdenIdOrden() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        // Variable de fachada de los datos
        FachadaColaboraDctoOrdenBean fachadaBean = new FachadaColaboraDctoOrdenBean();

        boolean validaOk = false;
        Connection connection = null;

        String selectStr =
                "SELECT MIN(tblDctosOrdenes.idLocal)      AS idLocal,     "
                + "       tblDctosOrdenes.idTipoOrden,                        "
                + "       tblDctosOrdenes.idOrden,                            "
                + "       MIN(tblDctosOrdenes.idLog)        AS idLog,       "
                + "       MIN(tblDctosOrdenes.tipoDcto)     AS tipoDcto,    "
                + "       MIN(tblDctosOrdenes.idCliente)    AS idCliente,   "
                + "       MIN(tblDctosOrdenes.idUsuario)    AS idUsuario,   "
                + "       MIN(tblDctosOrdenes.fechaOrden)   AS fechaOrden,  "
                + "       MIN(tblDctosOrdenes.ordenCompra) AS ordenCompra , "
                + "       MIN(tblDctosOrdenes.descuentoComercial)           "
                + "                                    AS descuentoComercial, "
                + "       MIN(tblDctosOrdenes.impuestoVenta)                "
                + "                                         AS impuestoVenta, "
                + "       MIN(tblDctosOrdenes.idRazon) AS idRazon ,         "
                + "       MIN(tblDctosOrdenes.contacto) AS contacto,        "
                + "       MIN(tblDctosOrdenes.observacion)                  "
                + "                                      AS observacion,      "
                + "       COUNT(tblDctosOrdenesDetalle.idPlu)                 "
                + "                        AS cantidadArticulos,              "
                + "       SUM ( tblDctosOrdenesDetalle.cantidad          *    "
                + "             tblDctosOrdenesDetalle.vrVentaUnitario   *    "
                + "  (1 - (tblDctosOrdenesDetalle.porcentajeDscto/100)))      "
                + "                        AS vrVentaSinIva,                  "
                + "       SUM ( tblDctosOrdenesDetalle.cantidad          *    "
                + "             tblDctosOrdenesDetalle.vrVentaUnitario   *    "
                + "  (1 - (tblDctosOrdenesDetalle.porcentajeDscto/100))  *    "
                + "  (1 + (tblDctosOrdenesDetalle.porcentajeIva/100)))        "
                + "                        AS vrVentaConIva,                  "
                + "       SUM ( tblDctosOrdenesDetalle.cantidad                     * "
                + "             tblDctosOrdenesDetalle.vrVentaUnitario              * "
                + "             (1 - (tblDctosOrdenesDetalle.porcentajeDscto/100))  * "
                + "             (1 + (tblDctosOrdenesDetalle.porcentajeIva/100)))   - "
                + "       SUM ( tblDctosOrdenesDetalle.cantidad                     * "
                + "             tblDctosOrdenesDetalle.vrVentaUnitario              * "
                + "             (1 - (tblDctosOrdenesDetalle.porcentajeDscto/100)))   "
                + "             AS vrIva,                                             "
                + "       SUM(tblDctosOrdenesDetalle.pesoTeorico *            "
                + "           tblDctosOrdenesDetalle.cantidad)                "
                + "                        AS pesoTeoricoTotal,               "
                + "       MIN(tblDctosOrdenes.idEstadoTx)    AS idEstadoTx  "
                + "FROM  tblDctosOrdenes ,                                    "
                + "      tblDctosOrdenesDetalle                               "
                + "WHERE tblDctosOrdenes.idOrden         =                    "
                + "      tblDctosOrdenesDetalle.idOrden                       "
                + "AND   tblDctosOrdenes.idTipoOrden     =                    "
                + "      tblDctosOrdenesDetalle.idTipoOrden                   "
                + "AND   tblDctosOrdenes.idLocal         =                    "
                + "      tblDctosOrdenesDetalle.idLocal                       "
                + "AND   tblDctosOrdenes.idOrden         = ( ? )              "
                + "AND   tblDctosOrdenes.idTipoOrden     = ( ? )              "
                + "GROUP BY tblDctosOrdenes.idOrden,                          "
                + "         tblDctosOrdenes.idTipoOrden                       ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            //inicializa los valores de los parametros
            selectStatement.setInt(1, getIdOrden());
            selectStatement.setInt(2, getIdTipoOrden());

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            if (rs.next()) {

                fachadaBean = new FachadaColaboraDctoOrdenBean();

                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setIdOrden(rs.getInt("idOrden"));
                fachadaBean.setIdLog(rs.getInt("idLog"));
                fachadaBean.setTipoDcto(rs.getString("tipoDcto"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setIdUsuario(rs.getString("idUsuario"));
                fachadaBean.setFechaOrden(rs.getString("fechaOrden").substring(0, 11));
                fachadaBean.setOrdenCompra(rs.getString("ordenCompra"));
                fachadaBean.setDescuentoComercial(rs.getString("descuentoComercial"));
                fachadaBean.setImpuestoVenta(rs.getInt("impuestoVenta"));
                fachadaBean.setIdRazon(rs.getString("idRazon"));
                fachadaBean.setContacto(rs.getString("contacto"));
                fachadaBean.setObservacion(rs.getString("observacion"));
                fachadaBean.setCantidadArticulos(rs.getInt("cantidadArticulos"));
                fachadaBean.setVrVentaSinIva(rs.getInt("vrVentaSinIva"));
                fachadaBean.setVrVentaConIva(rs.getInt("vrVentaConIva"));
                fachadaBean.setVrIva(rs.getInt("vrIva"));
                fachadaBean.setPesoTeoricoTotal(rs.getDouble("pesoTeoricoTotal"));
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

    // listaTodasCotizaciones
    public Vector listaTodasCotizaciones() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        // Variable de fachada de los datos
        FachadaColaboraDctoOrdenBean fachadaBean;

        boolean validaOk = false;
        Connection connection = null;

        String selectStr =
                "SELECT tblDctosOrdenes.idLocal,                         "
                + "       tblDctosOrdenes.idTipoOrden,                     "
                + "       tblDctosOrdenes.idOrden,                         "
                + "       tblDctosOrdenes.idLog,                           "
                + "       tblDctosOrdenes.idCliente,                       "
                + "       tblDctosOrdenes.idUsuario,                       "
                + "       tblDctosOrdenes.fechaOrden,                      "
                + "       tblDctosOrdenes.tipoDcto,                        "
                + "       tblDctosOrdenes.direccionDespacho,               "
                + "       tblDctosOrdenes.email,                           "
                + "       tblDctosOrdenes.fax,                             "
                + "       tblDctosOrdenes.contacto,                        "
                + "       tblDctosOrdenes.observacion,                     "
                + "       COUNT(tblDctosOrdenesDetalle.idPlu)              "
                + "                        AS cantidadArticulos,           "
                + "       SUM ( tblDctosOrdenesDetalle.cantidad          * "
                + "             tblDctosOrdenesDetalle.vrVentaUnitario   * "
                + "  (1 - (tblDctosOrdenesDetalle.porcentajeDscto/100)))   "
                + "                        AS vrVentaSinIva,               "
                + "       SUM ( tblDctosOrdenesDetalle.cantidad          * "
                + "             tblDctosOrdenesDetalle.vrVentaUnitario   * "
                + "  (1 - (tblDctosOrdenesDetalle.porcentajeDscto/100))  * "
                + "  (1 + (tblDctosOrdenesDetalle.porcentajeIva/100)))     "
                + "                        AS vrVentaConIva,               "
                + "       SUM(tblDctosOrdenesDetalle.pesoTeorico *         "
                + "           tblDctosOrdenesDetalle.cantidad)             "
                + "                        AS pesoTeoricoTotal             "
                + "FROM  tblDctosOrdenes ,                                 "
                + "      tblDctosOrdenesDetalle                            "
                + "WHERE tblDctosOrdenes.idOrden         =                 "
                + "      tblDctosOrdenesDetalle.idOrden                    "
                + "AND   tblDctosOrdenes.idTipoOrden     =                 "
                + "      tblDctosOrdenesDetalle.idTipoOrden                "
                + "AND   tblDctosOrdenes.idLocal         =                 "
                + "      tblDctosOrdenesDetalle.idLocal                    "
                + "AND   tblDctosOrdenes.idCliente       = ( ? )           "
                + "AND   tblDctosOrdenes.idUsuario       = ( ? )           "
                + "AND   tblDctosOrdenes.idTipoOrden     = ( ? )           "
                + "AND   tblDctosOrdenes.idLog NOT IN (                    "
                + "      SELECT tblDctosOrdenes.idLog                      "
                + "      FROM tblDctosOrdenes                              "
                + "      WHERE tblDctosOrdenes.idCliente       = ( ? )     "
                + "      AND   tblDctosOrdenes.idUsuario       = ( ? )     "
                + "      AND   tblDctosOrdenes.idTipoOrden     =   9   )   "
                + "GROUP BY tblDctosOrdenes.idLocal,                       "
                + "       tblDctosOrdenes.idTipoOrden,                     "
                + "       tblDctosOrdenes.idOrden,                         "
                + "       tblDctosOrdenes.idLog,                           "
                + "       tblDctosOrdenes.idCliente,                       "
                + "       tblDctosOrdenes.idUsuario,                       "
                + "       tblDctosOrdenes.fechaOrden,                      "
                + "       tblDctosOrdenes.tipoDcto,                        "
                + "       tblDctosOrdenes.direccionDespacho,               "
                + "       tblDctosOrdenes.email,                           "
                + "       tblDctosOrdenes.fax,                             "
                + "       tblDctosOrdenes.contacto,                        "
                + "       tblDctosOrdenes.observacion                      ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            //inicializa los valores de los parametros
            selectStatement.setString(1, getIdCliente());
            selectStatement.setDouble(2, getIdUsuario());
            selectStatement.setInt(3, getIdTipoOrden());
            selectStatement.setString(4, getIdCliente());
            selectStatement.setDouble(5, getIdUsuario());

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            while (rs.next()) {

                fachadaBean = new FachadaColaboraDctoOrdenBean();

                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("IdTipoOrden"));
                fachadaBean.setIdOrden(rs.getInt("IdOrden"));
                fachadaBean.setIdLog(rs.getInt("idLog"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setIdUsuario(rs.getDouble("idUsuario"));
                fachadaBean.setFechaOrden(rs.getString("fechaOrden").substring(0, 11));
                fachadaBean.setTipoDcto(rs.getString("tipoDcto"));
                fachadaBean.setDireccionDespacho(rs.getString("direccionDespacho"));
                fachadaBean.setEmail(rs.getString("email"));
                fachadaBean.setFax(rs.getString("fax"));
                fachadaBean.setContacto(rs.getString("contacto"));
                fachadaBean.setObservacion(rs.getString("observacion"));
                fachadaBean.setCantidadArticulos(rs.getInt("cantidadArticulos"));
                fachadaBean.setVrVentaSinIva(rs.getInt("vrVentaSinIva"));
                fachadaBean.setVrVentaConIva(rs.getInt("vrVentaConIva"));
                fachadaBean.setPesoTeoricoTotal(rs.getDouble("pesoTeoricoTotal"));

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

    // totalizaCotizacionIdLocalSugerido
    public Vector totalizaCotizacionIdLocalSugerido() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        // Variable de fachada de los datos
        FachadaColaboraDctoOrdenBean fachadaBean;

        boolean validaOk = false;
        Connection connection = null;

        String selectStr =
                "SELECT tblDctosOrdenes.idLocal,                         "
                + "       tblDctosOrdenes.idTipoOrden,                     "
                + "       tblDctosOrdenes.idOrden,                         "
                + "       tblDctosOrdenes.idLog,                           "
                + "       tblDctosOrdenesDetalle.idLocalSugerido,          "
                + "       COUNT(tblDctosOrdenesDetalle.idPlu)              "
                + "                        AS cantidadArticulos,           "
                + "       SUM ( tblDctosOrdenesDetalle.cantidad          * "
                + "             tblDctosOrdenesDetalle.vrVentaUnitario   * "
                + "  (1 - (tblDctosOrdenesDetalle.porcentajeDscto/100)))   "
                + "                        AS vrVentaSinIva,               "
                + "       SUM ( tblDctosOrdenesDetalle.cantidad          * "
                + "             tblDctosOrdenesDetalle.vrVentaUnitario   * "
                + "  (1 - (tblDctosOrdenesDetalle.porcentajeDscto/100))  * "
                + "  (1 + (tblDctosOrdenesDetalle.porcentajeIva/100)))     "
                + "                        AS vrVentaConIva,               "
                + "       SUM(tblDctosOrdenesDetalle.pesoTeorico *         "
                + "           tblDctosOrdenesDetalle.cantidad)             "
                + "                        AS pesoTeoricoTotal             "
                + "FROM  tblDctosOrdenes ,                                 "
                + "      tblDctosOrdenesDetalle                            "
                + "WHERE tblDctosOrdenes.idOrden         =                 "
                + "      tblDctosOrdenesDetalle.idOrden                    "
                + "AND   tblDctosOrdenes.idTipoOrden     =                 "
                + "      tblDctosOrdenesDetalle.idTipoOrden                "
                + "AND   tblDctosOrdenes.idLocal         =                 "
                + "      tblDctosOrdenesDetalle.idLocal                    "
                + "AND   tblDctosOrdenes.idTipoOrden     =                 "
                + getIdTipoOrden() + "                                     "
                + "AND   tblDctosOrdenes.idLog           =                 "
                + getIdLog() + "                                           "
                + "GROUP BY tblDctosOrdenes.idLocal,                       "
                + "       tblDctosOrdenes.idTipoOrden,                     "
                + "       tblDctosOrdenes.idOrden,                         "
                + "       tblDctosOrdenes.idLog,                           "
                + "       tblDctosOrdenesDetalle.idLocalSugerido           "
                + "ORDER BY tblDctosOrdenesDetalle.idLocalSugerido         ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            while (rs.next()) {

                fachadaBean = new FachadaColaboraDctoOrdenBean();

                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("IdTipoOrden"));
                fachadaBean.setIdOrden(rs.getInt("idOrden"));
                fachadaBean.setIdLog(rs.getInt("idLog"));
                fachadaBean.setIdLocalSugerido(rs.getInt("idLocalSugerido"));
                fachadaBean.setCantidadArticulos(rs.getInt("cantidadArticulos"));
                fachadaBean.setVrVentaSinIva(rs.getInt("vrVentaSinIva"));
                fachadaBean.setVrVentaConIva(rs.getInt("vrVentaConIva"));
                fachadaBean.setPesoTeoricoTotal(rs.getDouble("pesoTeoricoTotal"));

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

    // listaTodasPedidos
    public Vector listaTodasPedidos() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        // Variable de fachada de los datos
        FachadaColaboraDctoOrdenBean fachadaBean;

        boolean validaOk = false;
        Connection connection = null;

        String selectStr =
                "SELECT tblDctosOrdenes.idLocal,                         "
                + "       tblDctosOrdenes.idTipoOrden,                     "
                + "       tblDctosOrdenes.idOrden,                         "
                + "       tblDctosOrdenes.idLog,                           "
                + "       tblDctosOrdenes.idCliente,                       "
                + "       tblDctosOrdenes.idUsuario,                       "
                + "       tblDctosOrdenes.fechaOrden,                      "
                + "       tblDctosOrdenes.tipoDcto,                        "
                + "       tblDctosOrdenes.direccionDespacho,               "
                + "       tblDctosOrdenes.email,                           "
                + "       tblDctosOrdenes.fax,                             "
                + "       tblDctosOrdenes.contacto,                        "
                + "       tblDctosOrdenes.observacion,                     "
                + "       COUNT(tblDctosOrdenesDetalle.idPlu)              "
                + "                        AS cantidadArticulos,           "
                + "       SUM ( tblDctosOrdenesDetalle.cantidad          * "
                + "             tblDctosOrdenesDetalle.vrVentaUnitario   * "
                + "  (1 - (tblDctosOrdenesDetalle.porcentajeDscto/100)))   "
                + "                        AS vrVentaSinIva,               "
                + "       SUM ( tblDctosOrdenesDetalle.cantidad          * "
                + "             tblDctosOrdenesDetalle.vrVentaUnitario   * "
                + "  (1 - (tblDctosOrdenesDetalle.porcentajeDscto/100))  * "
                + "  (1 + (tblDctosOrdenesDetalle.porcentajeIva/100)))     "
                + "                        AS vrVentaConIva,               "
                + "       SUM(tblDctosOrdenesDetalle.pesoTeorico *         "
                + "           tblDctosOrdenesDetalle.cantidad)             "
                + "                        AS pesoTeoricoTotal,            "
                + "      tblDctosOrdenes.estado,                           "
                + "      tblDctosEstados.nombreEstado                      "
                + "FROM  tblDctosOrdenes ,                                 "
                + "      tblDctosOrdenesDetalle,                           "
                + "      tblDctosEstados                                   "
                + "WHERE tblDctosOrdenes.idOrden         =                 "
                + "      tblDctosOrdenesDetalle.idOrden                    "
                + "AND   tblDctosOrdenes.idTipoOrden     =                 "
                + "      tblDctosOrdenesDetalle.idTipoOrden                "
                + "AND   tblDctosOrdenes.idLocal         =                 "
                + "      tblDctosOrdenesDetalle.idLocal                    "
                + "AND   tblDctosOrdenes.idCliente       = ( ? )           "
                + "AND   tblDctosOrdenes.idUsuario       = ( ? )           "
                + "AND   tblDctosOrdenes.idTipoOrden     = ( ? )           "
                + "AND   tblDctosOrdenes.estado          =                 "
                + "      tblDctosEstados.estado                            "
                + "GROUP BY tblDctosOrdenes.idLocal,                       "
                + "       tblDctosOrdenes.idTipoOrden,                     "
                + "       tblDctosOrdenes.idOrden,                         "
                + "       tblDctosOrdenes.idLog,                           "
                + "       tblDctosOrdenes.idCliente,                       "
                + "       tblDctosOrdenes.idUsuario,                       "
                + "       tblDctosOrdenes.fechaOrden,                      "
                + "       tblDctosOrdenes.tipoDcto,                        "
                + "       tblDctosOrdenes.direccionDespacho,               "
                + "       tblDctosOrdenes.email,                           "
                + "       tblDctosOrdenes.fax,                             "
                + "       tblDctosOrdenes.contacto,                        "
                + "       tblDctosOrdenes.observacion,                     "
                + "       tblDctosOrdenes.estado,                          "
                + "       tblDctosEstados.nombreEstado                     "
                + "ORDER BY tblDctosOrdenes.fechaOrden DESC                ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            //inicializa los valores de los parametros
            selectStatement.setString(1, getIdCliente());
            selectStatement.setDouble(2, getIdUsuario());
            selectStatement.setInt(3, getIdTipoOrden());

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            while (rs.next()) {

                fachadaBean = new FachadaColaboraDctoOrdenBean();

                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("IdTipoOrden"));
                fachadaBean.setIdOrden(rs.getInt("IdOrden"));
                fachadaBean.setIdLog(rs.getInt("idLog"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setIdUsuario(rs.getDouble("idUsuario"));
                fachadaBean.setFechaOrden(rs.getString("fechaOrden").substring(0, 11));
                fachadaBean.setTipoDcto(rs.getString("tipoDcto"));
                fachadaBean.setDireccionDespacho(rs.getString("direccionDespacho"));
                fachadaBean.setEmail(rs.getString("email"));
                fachadaBean.setFax(rs.getString("fax"));
                fachadaBean.setContacto(rs.getString("contacto"));
                fachadaBean.setObservacion(rs.getString("observacion"));
                fachadaBean.setCantidadArticulos(rs.getInt("cantidadArticulos"));
                fachadaBean.setVrVentaSinIva(rs.getInt("vrVentaSinIva"));
                fachadaBean.setVrVentaConIva(rs.getInt("vrVentaConIva"));
                fachadaBean.setPesoTeoricoTotal(rs.getDouble("pesoTeoricoTotal"));
                fachadaBean.setEstado(rs.getInt("estado"));
                fachadaBean.setNombreEstado(rs.getString("nombreEstado"));

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

    // listaAllPedidoUsuario
    public Vector listaAllPedidoUsuario() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        // Variable de fachada de los datos
        FachadaColaboraDctoOrdenBean fachadaBean;

        Connection connection = null;

        String selectStr =
                "SELECT tblDctosOrdenes.idLocal,                         "
                + "       tblDctosOrdenes.idTipoOrden,                     "
                + "       tblDctosOrdenes.idOrden,                         "
                + "       tblDctosOrdenes.idLog,                           "
                + "       tblDctosOrdenes.idCliente,                       "
                + "       tblDctosOrdenes.idUsuario,                       "
                + "       tblDctosOrdenes.fechaOrden,                      "
                + "       tblDctosOrdenes.tipoDcto,                        "
                + "       tblDctosOrdenes.direccionDespacho,               "
                + "       tblDctosOrdenes.email,                           "
                + "       tblDctosOrdenes.fax,                             "
                + "       tblDctosOrdenes.contacto,                        "
                + "       tblDctosOrdenes.observacion,                     "
                + "       COUNT(tblDctosOrdenesDetalle.idPlu)              "
                + "                        AS cantidadArticulos,           "
                + "       SUM ( tblDctosOrdenesDetalle.cantidad          * "
                + "             tblDctosOrdenesDetalle.vrVentaUnitario   * "
                + "  (1 - (tblDctosOrdenesDetalle.porcentajeDscto/100)))   "
                + "                        AS vrVentaSinIva,               "
                + "       SUM ( tblDctosOrdenesDetalle.cantidad          * "
                + "             tblDctosOrdenesDetalle.vrVentaUnitario   * "
                + "  (1 - (tblDctosOrdenesDetalle.porcentajeDscto/100))  * "
                + "  (1 + (tblDctosOrdenesDetalle.porcentajeIva/100)))     "
                + "                        AS vrVentaConIva,               "
                + "       SUM(tblDctosOrdenesDetalle.pesoTeorico *         "
                + "           tblDctosOrdenesDetalle.cantidad)             "
                + "                        AS pesoTeoricoTotal,            "
                + "      tblDctosOrdenes.estado,                           "
                + "      tblDctosEstados.nombreEstado                      "
                + "FROM  tblDctosOrdenes ,                                 "
                + "      tblDctosOrdenesDetalle,                           "
                + "      tblDctosEstados                                   "
                + "WHERE tblDctosOrdenes.idOrden         =                 "
                + "      tblDctosOrdenesDetalle.idOrden                    "
                + "AND   tblDctosOrdenes.idTipoOrden     =                 "
                + "      tblDctosOrdenesDetalle.idTipoOrden                "
                + "AND   tblDctosOrdenes.idLocal         =                 "
                + "      tblDctosOrdenesDetalle.idLocal                    "
                + "AND   tblDctosOrdenes.idUsuario       = ( ? )           "
                + "AND   tblDctosOrdenes.idTipoOrden     = ( ? )           "
                + "AND   tblDctosOrdenes.estado          =                 "
                + "      tblDctosEstados.estado                            "
                + "GROUP BY tblDctosOrdenes.idLocal,                       "
                + "       tblDctosOrdenes.idTipoOrden,                     "
                + "       tblDctosOrdenes.idOrden,                         "
                + "       tblDctosOrdenes.idLog,                           "
                + "       tblDctosOrdenes.idCliente,                       "
                + "       tblDctosOrdenes.idUsuario,                       "
                + "       tblDctosOrdenes.fechaOrden,                      "
                + "       tblDctosOrdenes.tipoDcto,                        "
                + "       tblDctosOrdenes.direccionDespacho,               "
                + "       tblDctosOrdenes.email,                           "
                + "       tblDctosOrdenes.fax,                             "
                + "       tblDctosOrdenes.contacto,                        "
                + "       tblDctosOrdenes.observacion,                     "
                + "       tblDctosOrdenes.estado,                          "
                + "       tblDctosEstados.nombreEstado                     "
                + "ORDER BY tblDctosOrdenes.fechaOrden DESC,               "
                + "         tblDctosOrdenes.idLocal,                       "
                + "         tblDctosOrdenes.idTipoOrden,                   "
                + "         tblDctosOrdenes.idOrden DESC                   ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            //inicializa los valores de los parametros
            selectStatement.setDouble(1, getIdUsuario());
            selectStatement.setInt(2, getIdTipoOrden());

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            while (rs.next()) {

                fachadaBean = new FachadaColaboraDctoOrdenBean();

                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("IdTipoOrden"));
                fachadaBean.setIdOrden(rs.getInt("IdOrden"));
                fachadaBean.setIdLog(rs.getInt("idLog"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setIdUsuario(rs.getDouble("idUsuario"));
                fachadaBean.setFechaOrden(rs.getString("fechaOrden").substring(0, 11));
                fachadaBean.setTipoDcto(rs.getString("tipoDcto"));
                fachadaBean.setDireccionDespacho(rs.getString("direccionDespacho"));
                fachadaBean.setEmail(rs.getString("email"));
                fachadaBean.setFax(rs.getString("fax"));
                fachadaBean.setContacto(rs.getString("contacto"));
                fachadaBean.setObservacion(rs.getString("observacion"));
                fachadaBean.setCantidadArticulos(rs.getInt("cantidadArticulos"));
                fachadaBean.setVrVentaSinIva(rs.getInt("vrVentaSinIva"));
                fachadaBean.setVrVentaConIva(rs.getInt("vrVentaConIva"));
                fachadaBean.setPesoTeoricoTotal(rs.getDouble("pesoTeoricoTotal"));
                fachadaBean.setEstado(rs.getInt("estado"));
                fachadaBean.setNombreEstado(rs.getString("nombreEstado"));

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

    // listaTodasPedidosAdministrador
    public Vector listaTodasPedidosAdministrador() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        // Variable de fachada de los datos
        FachadaColaboraDctoOrdenBean fachadaBean;

        boolean validaOk = false;
        Connection connection = null;

        String selectStr =
                "SELECT tblDctosOrdenes.idLocal,                         "
                + "       tblDctosOrdenes.idTipoOrden,                     "
                + "       tblDctosOrdenes.idOrden,                         "
                + "       tblDctosOrdenes.idLog,                           "
                + "       tblDctosOrdenes.idCliente,                       "
                + "       tblDctosOrdenes.idUsuario,                       "
                + "       tblDctosOrdenes.fechaOrden,                      "
                + "       tblDctosOrdenes.tipoDcto,                        "
                + "       tblDctosOrdenes.direccionDespacho,               "
                + "       tblDctosOrdenes.email,                           "
                + "       tblDctosOrdenes.fax,                             "
                + "       tblDctosOrdenes.contacto,                        "
                + "       tblDctosOrdenes.observacion,                     "
                + "       COUNT(tblDctosOrdenesDetalle.idPlu)              "
                + "                        AS cantidadArticulos,           "
                + "       SUM ( tblDctosOrdenesDetalle.cantidad          * "
                + "             tblDctosOrdenesDetalle.vrVentaUnitario   * "
                + "  (1 - (tblDctosOrdenesDetalle.porcentajeDscto/100)))   "
                + "                        AS vrVentaSinIva,               "
                + "       SUM ( tblDctosOrdenesDetalle.cantidad          * "
                + "             tblDctosOrdenesDetalle.vrVentaUnitario   * "
                + "  (1 - (tblDctosOrdenesDetalle.porcentajeDscto/100))  * "
                + "  (1 + (tblDctosOrdenesDetalle.porcentajeIva/100)))     "
                + "                        AS vrVentaConIva,               "
                + "       SUM(tblDctosOrdenesDetalle.pesoTeorico *         "
                + "           tblDctosOrdenesDetalle.cantidad)             "
                + "                        AS pesoTeoricoTotal,            "
                + "      tblDctosOrdenes.estado,                           "
                + "      tblDctosEstados.nombreEstado                      "
                + "FROM  tblDctosOrdenes ,                                 "
                + "      tblDctosOrdenesDetalle,                           "
                + "      tblDctosEstados                                   "
                + "WHERE tblDctosOrdenes.idOrden         =                 "
                + "      tblDctosOrdenesDetalle.idOrden                    "
                + "AND   tblDctosOrdenes.idTipoOrden     =                 "
                + "      tblDctosOrdenesDetalle.idTipoOrden                "
                + "AND   tblDctosOrdenes.idLocal         =                 "
                + "      tblDctosOrdenesDetalle.idLocal                    "
                + "AND   tblDctosOrdenes.idTipoOrden     = ( ? )           "
                + "AND   tblDctosOrdenes.estado          =                 "
                + "      tblDctosEstados.estado                            "
                + "GROUP BY tblDctosOrdenes.idLocal,                       "
                + "       tblDctosOrdenes.idTipoOrden,                     "
                + "       tblDctosOrdenes.idOrden,                         "
                + "       tblDctosOrdenes.idLog,                           "
                + "       tblDctosOrdenes.idCliente,                       "
                + "       tblDctosOrdenes.idUsuario,                       "
                + "       tblDctosOrdenes.fechaOrden,                      "
                + "       tblDctosOrdenes.tipoDcto,                        "
                + "       tblDctosOrdenes.direccionDespacho,               "
                + "       tblDctosOrdenes.email,                           "
                + "       tblDctosOrdenes.fax,                             "
                + "       tblDctosOrdenes.contacto,                        "
                + "       tblDctosOrdenes.observacion,                     "
                + "       tblDctosOrdenes.estado,                          "
                + "       tblDctosEstados.nombreEstado                     "
                + "ORDER BY tblDctosOrdenes.fechaOrden DESC,               "
                + "         tblDctosOrdenes.idLocal,                       "
                + "         tblDctosOrdenes.idTipoOrden,                   "
                + "         tblDctosOrdenes.idOrden DESC                   ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            //inicializa los valores de los parametros
            selectStatement.setInt(1, getIdTipoOrden());

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            while (rs.next()) {

                fachadaBean = new FachadaColaboraDctoOrdenBean();

                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("IdTipoOrden"));
                fachadaBean.setIdOrden(rs.getInt("IdOrden"));
                fachadaBean.setIdLog(rs.getInt("idLog"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setIdUsuario(rs.getDouble("idUsuario"));
                fachadaBean.setFechaOrden(rs.getString("fechaOrden").substring(0, 11));
                fachadaBean.setTipoDcto(rs.getString("tipoDcto"));
                fachadaBean.setDireccionDespacho(rs.getString("direccionDespacho"));
                fachadaBean.setEmail(rs.getString("email"));
                fachadaBean.setFax(rs.getString("fax"));
                fachadaBean.setContacto(rs.getString("contacto"));
                fachadaBean.setObservacion(rs.getString("observacion"));
                fachadaBean.setCantidadArticulos(rs.getInt("cantidadArticulos"));
                fachadaBean.setVrVentaSinIva(rs.getInt("vrVentaSinIva"));
                fachadaBean.setVrVentaConIva(rs.getInt("vrVentaConIva"));
                fachadaBean.setPesoTeoricoTotal(rs.getDouble("pesoTeoricoTotal"));
                fachadaBean.setEstado(rs.getInt("estado"));
                fachadaBean.setNombreEstado(rs.getString("nombreEstado"));

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

    // listaPedidosUsuario
    public Vector listaPedidosUsuario() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        // Variable de fachada de los datos
        FachadaColaboraDctoOrdenBean fachadaBean;

        Connection connection = null;

        String selectStr =
                "SELECT tblDctosOrdenes.idLocal,                         "
                + "       tblDctosOrdenes.idTipoOrden,                     "
                + "       tblDctosOrdenes.idOrden,                         "
                + "       tblDctosOrdenes.idLog,                           "
                + "       tblDctosOrdenes.idCliente,                       "
                + "       tblDctosOrdenes.idUsuario,                       "
                + "       tblDctosOrdenes.fechaOrden,                      "
                + "       tblDctosOrdenes.tipoDcto,                        "
                + "       tblDctosOrdenes.direccionDespacho,               "
                + "       tblDctosOrdenes.email,                           "
                + "       tblDctosOrdenes.fax,                             "
                + "       tblDctosOrdenes.contacto,                        "
                + "       tblDctosOrdenes.observacion,                     "
                + "       COUNT(tblDctosOrdenesDetalle.idPlu)              "
                + "                        AS cantidadArticulos,           "
                + "       SUM ( tblDctosOrdenesDetalle.cantidad          * "
                + "             tblDctosOrdenesDetalle.vrVentaUnitario   * "
                + "  (1 - (tblDctosOrdenesDetalle.porcentajeDscto/100)))   "
                + "                        AS vrVentaSinIva,               "
                + "       SUM ( tblDctosOrdenesDetalle.cantidad          * "
                + "             tblDctosOrdenesDetalle.vrVentaUnitario   * "
                + "  (1 - (tblDctosOrdenesDetalle.porcentajeDscto/100))  * "
                + "  (1 + (tblDctosOrdenesDetalle.porcentajeIva/100)))     "
                + "                        AS vrVentaConIva,               "
                + "       SUM(tblDctosOrdenesDetalle.pesoTeorico *         "
                + "           tblDctosOrdenesDetalle.cantidad)             "
                + "                        AS pesoTeoricoTotal             "
                + "FROM  tblDctosOrdenes ,                                 "
                + "      tblDctosOrdenesDetalle                            "
                + "WHERE tblDctosOrdenes.idOrden         =                 "
                + "      tblDctosOrdenesDetalle.idOrden                    "
                + "AND   tblDctosOrdenes.idTipoOrden     =                 "
                + "      tblDctosOrdenesDetalle.idTipoOrden                "
                + "AND   tblDctosOrdenes.idLocal         =                 "
                + "      tblDctosOrdenesDetalle.idLocal                    "
                + "AND   tblDctosOrdenes.idUsuario       = ( ? )           "
                + "AND   tblDctosOrdenes.idTipoOrden     = ( ? )           "
                + "GROUP BY tblDctosOrdenes.idLocal,                       "
                + "       tblDctosOrdenes.idTipoOrden,                     "
                + "       tblDctosOrdenes.idOrden,                         "
                + "       tblDctosOrdenes.idLog,                           "
                + "       tblDctosOrdenes.idCliente,                       "
                + "       tblDctosOrdenes.idUsuario,                       "
                + "       tblDctosOrdenes.fechaOrden,                      "
                + "       tblDctosOrdenes.tipoDcto,                        "
                + "       tblDctosOrdenes.direccionDespacho,               "
                + "       tblDctosOrdenes.email,                           "
                + "       tblDctosOrdenes.fax,                             "
                + "       tblDctosOrdenes.contacto,                        "
                + "       tblDctosOrdenes.observacion                      ";


        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            //inicializa los valores de los parametros
            selectStatement.setDouble(1, getIdUsuario());
            selectStatement.setInt(2, getIdTipoOrden());

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            while (rs.next()) {

                fachadaBean = new FachadaColaboraDctoOrdenBean();

                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("IdTipoOrden"));
                fachadaBean.setIdOrden(rs.getInt("IdOrden"));
                fachadaBean.setIdLog(rs.getInt("idLog"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setIdUsuario(rs.getDouble("idUsuario"));
                fachadaBean.setFechaOrden(rs.getString("fechaOrden").substring(0, 11));
                fachadaBean.setTipoDcto(rs.getString("tipoDcto"));
                fachadaBean.setDireccionDespacho(rs.getString("direccionDespacho"));
                fachadaBean.setEmail(rs.getString("email"));
                fachadaBean.setFax(rs.getString("fax"));
                fachadaBean.setContacto(rs.getString("contacto"));
                fachadaBean.setObservacion(rs.getString("observacion"));
                fachadaBean.setCantidadArticulos(rs.getInt("cantidadArticulos"));
                fachadaBean.setVrVentaSinIva(rs.getInt("vrVentaSinIva"));
                fachadaBean.setVrVentaConIva(rs.getInt("vrVentaConIva"));
                fachadaBean.setPesoTeoricoTotal(rs.getDouble("pesoTeoricoTotal"));

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

    // listaOT
    public Vector listaOT(int xIdTipoTercero) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        // Variable de fachada de los datos
        FachadaColaboraDctoOrdenBean fachadaBean;

        Connection connection = null;

        String selectStr =
               "  SELECT tmpDET.idLog,                               "
               + "         tmpDET.idCliente,                         "
               + "         tmpDET.IDUSUARIO,                         "
               + "         tmpDET.fechaEntrega,                      "
               + "         tblTerceros.nombreTercero,                "
               + "         tblTerceros.direccionTercero,             "
               + "         tblTerceros.telefonoFijo,                 "
               + "         tblTerceros.nombreEmpresa,                "
               + "         tblTerceros.ciudadTercero,                "
               + "         ctrlUsuarios.aliasUsuario AS              "
               + "                              NOMBREUSUARIO,       "
               + "         tmpDET.idOrden,                           "
               + "         tmpDET.item,                              "
               + "         tmpDET.cantidad,                          "
               + "         tmpDET.referenciaCliente,                 "
               + "         tmpDET.numeroOrden,                       "
               + "         tmpDET.idFicha,                           "
               + "         tmpDET.ordenCompra,                       "
               + "         tmpDET.fechaOrden                         "
               + "  FROM   tblTerceros                               "
               + "  INNER JOIN tblAgendaLogVisitas                   "
               + "  ON tblTerceros.idCliente         =               "
               + "              tblAgendaLogVisitas.idCliente        "
               + "  INNER JOIN ctrlUsuarios                          "
               + "  ON tblAgendaLogVisitas.IDUSUARIO =               "
               + "                     ctrlUsuarios.IDUSUARIO        "
               + "  INNER JOIN                                       "
               + "  (SELECT tblDctosOrdenesDetalle.idLocal,          "
               + "         tblDctosOrdenesDetalle.idTipoOrden,       "
               + "         tblDctosOrdenesDetalle.idOrden,           "
               + "         tblDctosOrdenesDetalle.item,              "
               + "         tblDctosOrdenesDetalle.cantidad,          "
               + "         tblDctosOrdenesDetalle.referenciaCliente, "
               + "         tblDctosOrdenesDetalle.fechaEntrega,      "
               + "         tblDctosOrdenes.idLog,                    "
               + "         tblDctosOrdenes.numeroOrden,              "
               + "         tblDctosOrdenes.idFicha,                  "
               + "         tblDctosOrdenes.idCliente,                "
               + "         tblDctosOrdenes.idUsuario,                "
               + "         tblDctosOrdenes.ordenCompra,              "
               + "         tblDctosOrdenes.fechaOrden                "
               + "   FROM       tblDctosOrdenes                      "
               + "   INNER JOIN tblDctosOrdenesDetalle               "
               + "   ON tblDctosOrdenes.IDLOCAL      =               "
               + "                   tblDctosOrdenesDetalle.IDLOCAL  "
               + "   AND tblDctosOrdenes.IDTIPOORDEN =               "
               + "               tblDctosOrdenesDetalle.IDTIPOORDEN  "
               + "   AND tblDctosOrdenes.IDORDEN     =               "
               + "                   tblDctosOrdenesDetalle.IDORDEN) "
               + "                                         AS tmpDET "
               + "  ON tmpDET.idLog = tblAgendaLogVisitas.idLog      "
               + "  WHERE tblAgendaLogVisitas.IDLOG IN (             "
               + "     SELECT tblAgendaLogVisitas.IDLOG              "
               + "     FROM   tblAgendaLogVisitas                    "
               + "     INNER JOIN tblDctosOrdenes                    "
               + "     ON tblAgendaLogVisitas.IDLOG     =            "
               + "                      tblDctosOrdenes.IDLOG        "
               + "  AND   tblDctosOrdenes.idLocal       =            "
               + getIdLocal() + "                                    "
               + "  AND   tblDctosOrdenes.idTipoOrden   =            "
               + getIdTipoOrden() + "                                "
               + "  AND   tblAgendaLogVisitas.estado    =            "
               + getEstado() + "                                     "
               + "  AND   tblTerceros.idCliente         =           '"
               + getIdCliente() + "'                                 "
               + "  AND tblTerceros.idTipoTercero       =            "
               + xIdTipoTercero + " )                                "
               + "  ORDER BY tmpDET.numeroOrden,                     "
               + "           tmpDET.fechaEntrega                     ";
        
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
                fachadaBean = new FachadaColaboraDctoOrdenBean();

                //
                fachadaBean.setIdLog(rs.getInt("idLog"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setIdUsuario(rs.getDouble("idUsuario"));
                fachadaBean.setFechaEntrega(rs.getString("fechaEntrega"));
                fachadaBean.setNombreTercero(rs.getString("nombreTercero"));
                fachadaBean.setDireccionDespacho(
                                              rs.getString("direccionTercero"));
                fachadaBean.setTelefonoFijo(rs.getString("telefonoFijo"));
                fachadaBean.setNombreEmpresa(rs.getString("nombreEmpresa"));
                fachadaBean.setCiudadTercero(rs.getString("ciudadTercero"));
                fachadaBean.setNombreUsuario(rs.getString("NOMBREUSUARIO"));
                fachadaBean.setIdOrden(rs.getInt("IdOrden"));                
                fachadaBean.setItem(rs.getInt("item"));                    
                fachadaBean.setCantidad(rs.getDouble("cantidad"));
                fachadaBean.setReferenciaCliente(
                                             rs.getString("referenciaCliente"));
                fachadaBean.setNumeroOrden(rs.getInt("numeroOrden"));                    
                fachadaBean.setIdFicha(rs.getInt("idFicha"));                    
                fachadaBean.setOrdenCompra(rs.getString("ordenCompra"));
                fachadaBean.setFechaOrden(rs.getString("fechaOrden"));

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

    // listaOT
    public Vector listaOTCosto() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        // Variable de fachada de los datos
        FachadaColaboraDctoOrdenBean fachadaBean;

        Connection connection = null;

        String selectStr =
               "  SELECT tmpDET.idLog,                               "
               + "         tmpDET.idCliente,                         "
               + "         tmpDET.IDUSUARIO,                         "
               + "         tmpDET.fechaEntrega,                      "
               + "         tblTerceros.nombreTercero,                "
               + "         tblTerceros.direccionTercero,             "
               + "         tblTerceros.telefonoFijo,                 "
               + "         tblTerceros.nombreEmpresa,                "
               + "         tblTerceros.ciudadTercero,                "
               + "         ctrlUsuarios.aliasUsuario AS              "
               + "                              NOMBREUSUARIO,       "
               + "         tmpDET.idOrden,                           "
               + "         tmpDET.item,                              "
               + "         tmpDET.cantidad,                          "
               + "         tmpDET.referenciaCliente,                 "
               + "         tmpDET.numeroOrden,                       "
               + "         tmpDET.idFicha,                           "
               + "         tmpDET.ordenCompra,                       "
               + "         tmpDET.fechaOrden                         "
               + "  FROM   tblTerceros                               "
               + "  INNER JOIN tblAgendaLogVisitas                   "
               + "  ON tblTerceros.idCliente         =               "
               + "              tblAgendaLogVisitas.idCliente        "
               + "  INNER JOIN ctrlUsuarios                          "
               + "  ON tblAgendaLogVisitas.IDUSUARIO =               "
               + "                     ctrlUsuarios.IDUSUARIO        "
               + "  INNER JOIN                                       "
               + "  (SELECT tblDctosOrdenesDetalle.idLocal,          "
               + "         tblDctosOrdenesDetalle.idTipoOrden,       "
               + "         tblDctosOrdenesDetalle.idOrden,           "
               + "         tblDctosOrdenesDetalle.item,              "
               + "         tblDctosOrdenesDetalle.cantidad,          "
               + "         tblDctosOrdenesDetalle.referenciaCliente, "
               + "         tblDctosOrdenesDetalle.fechaEntrega,      "
               + "         tblDctosOrdenes.idLog,                    "
               + "         tblDctosOrdenes.numeroOrden,              "
               + "         tblDctosOrdenes.idFicha,                  "
               + "         tblDctosOrdenes.idCliente,                "
               + "         tblDctosOrdenes.idUsuario,                "
               + "         tblDctosOrdenes.ordenCompra,              "
               + "         tblDctosOrdenes.fechaOrden                "
               + "   FROM       tblDctosOrdenes                      "
               + "   INNER JOIN tblDctosOrdenesDetalle               "
               + "   ON tblDctosOrdenes.IDLOCAL      =               "
               + "                   tblDctosOrdenesDetalle.IDLOCAL  "
               + "   AND tblDctosOrdenes.IDTIPOORDEN =               "
               + "               tblDctosOrdenesDetalle.IDTIPOORDEN  "
               + "   AND tblDctosOrdenes.IDORDEN     =               "
               + "                   tblDctosOrdenesDetalle.IDORDEN) "
               + "                                         AS tmpDET "
               + "  ON tmpDET.idLog = tblAgendaLogVisitas.idLog      "
               + "  WHERE tblAgendaLogVisitas.IDLOG IN (             "
               + "     SELECT tblAgendaLogVisitas.IDLOG              "
               + "     FROM   tblAgendaLogVisitas                    "
               + "     INNER JOIN tblDctosOrdenes                    "
               + "     ON tblAgendaLogVisitas.IDLOG     =            "
               + "                      tblDctosOrdenes.IDLOG        "
               + "  AND   tblDctosOrdenesDetalle.idLocalOrigen =     "
               + getIdLocal() + "                                    "
               + "  AND   tblDctosOrdenesDetalle.idTipoOrdenOrigen = "
               + getIdTipoOrden() + "                                "
               + "  AND   tblDctosOrdenesDetalle.idOrdenOrigen =     "
               + getIdTipoOrden() + " )                                "
               + "  ORDER BY tmpDET.numeroOrden,                     "
               + "           tmpDET.fechaEntrega                     ";

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
                fachadaBean = new FachadaColaboraDctoOrdenBean();

                //
                fachadaBean.setIdLog(rs.getInt("idLog"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setIdUsuario(rs.getDouble("idUsuario"));
                fachadaBean.setFechaEntrega(rs.getString("fechaEntrega"));
                fachadaBean.setNombreTercero(rs.getString("nombreTercero"));
                fachadaBean.setDireccionDespacho(
                                              rs.getString("direccionTercero"));
                fachadaBean.setTelefonoFijo(rs.getString("telefonoFijo"));
                fachadaBean.setNombreEmpresa(rs.getString("nombreEmpresa"));
                fachadaBean.setCiudadTercero(rs.getString("ciudadTercero"));
                fachadaBean.setNombreUsuario(rs.getString("NOMBREUSUARIO"));
                fachadaBean.setIdOrden(rs.getInt("IdOrden"));
                fachadaBean.setItem(rs.getInt("item"));
                fachadaBean.setCantidad(rs.getDouble("cantidad"));
                fachadaBean.setReferenciaCliente(
                                             rs.getString("referenciaCliente"));
                fachadaBean.setNumeroOrden(rs.getInt("numeroOrden"));
                fachadaBean.setIdFicha(rs.getInt("idFicha"));
                fachadaBean.setOrdenCompra(rs.getString("ordenCompra"));
                fachadaBean.setFechaOrden(rs.getString("fechaOrden"));

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

    // listaOTAll
    public Vector listaOTAll(int xIdTipoTercero) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        // Variable de fachada de los datos
        FachadaColaboraDctoOrdenBean fachadaBean;

        Connection connection = null;

        String selectStr =
               "  SELECT tmpDET.idLog,                               "
               + "         tmpDET.idCliente,                         "
               + "         tmpDET.IDUSUARIO,                         "
               + "         tmpDET.fechaEntrega,                      "
               + "         tblTerceros.nombreTercero,                "
               + "         tblTerceros.direccionTercero,             "
               + "         tblTerceros.telefonoFijo,                 "
               + "         tblTerceros.nombreEmpresa,                "
               + "         tblTerceros.ciudadTercero,                "
               + "         ctrlUsuarios.aliasUsuario AS              "
               + "                              NOMBREUSUARIO,       "
               + "         tmpDET.idOrden,                           "
               + "         tmpDET.item,                              "
               + "         tmpDET.cantidad,                          "
               + "         tmpDET.referenciaCliente,                 "
               + "         tmpDET.numeroOrden,                       "
               + "         tmpDET.idFicha,                           "
               + "         tmpDET.ordenCompra,                       "
               + "         tmpDET.fechaOrden                         "
               + "  FROM   tblTerceros                               "
               + "  INNER JOIN tblAgendaLogVisitas                   "
               + "  ON tblTerceros.idCliente         =               "
               + "              tblAgendaLogVisitas.idCliente        "
               + "  INNER JOIN ctrlUsuarios                          "
               + "  ON tblAgendaLogVisitas.IDUSUARIO =               "
               + "                     ctrlUsuarios.IDUSUARIO        "
               + "  INNER JOIN                                       "
               + "  (SELECT tblDctosOrdenesDetalle.idLocal,          "
               + "         tblDctosOrdenesDetalle.idTipoOrden,       "
               + "         tblDctosOrdenesDetalle.idOrden,           "
               + "         tblDctosOrdenesDetalle.item,              "
               + "         tblDctosOrdenesDetalle.cantidad,          "
               + "         tblDctosOrdenesDetalle.referenciaCliente, "
               + "         tblDctosOrdenesDetalle.fechaEntrega,      "
               + "         tblDctosOrdenes.idLog,                    "
               + "         tblDctosOrdenes.numeroOrden,              "
               + "         tblDctosOrdenes.idFicha,                  "
               + "         tblDctosOrdenes.idCliente,                "
               + "         tblDctosOrdenes.idUsuario,                "
               + "         tblDctosOrdenes.ordenCompra,              "
               + "         tblDctosOrdenes.fechaOrden,               "
               + "        tblDctosOrdenesDetalle.idEstadoRefOriginal "
               + "   FROM       tblDctosOrdenes                      "
               + "   INNER JOIN tblDctosOrdenesDetalle               "
               + "   ON tblDctosOrdenes.IDLOCAL      =               "
               + "                   tblDctosOrdenesDetalle.IDLOCAL  "
               + "   AND tblDctosOrdenes.IDTIPOORDEN =               "
               + "               tblDctosOrdenesDetalle.IDTIPOORDEN  "
               + "   AND tblDctosOrdenes.IDORDEN     =               "
               + "                   tblDctosOrdenesDetalle.IDORDEN) "
               + "                                         AS tmpDET "
               + "  ON tmpDET.idLog = tblAgendaLogVisitas.idLog      "
               + "  WHERE tblAgendaLogVisitas.IDLOG IN (             "
               + "     SELECT tblAgendaLogVisitas.IDLOG              "
               + "     FROM   tblAgendaLogVisitas                    "
               + "     INNER JOIN tblDctosOrdenes                    "
               + "     ON tblAgendaLogVisitas.IDLOG     =            "
               + "                      tblDctosOrdenes.IDLOG        "
               + "  AND tmpDET.idEstadoRefOriginal     != 9          "
               + "  AND tmpDET.numeroOrden             >  0          "
               + "  AND   tblDctosOrdenes.idLocal       =            "
               + getIdLocal() + "                                    "
               + "  AND   tblDctosOrdenes.idTipoOrden   =            "
               + getIdTipoOrden() + "                                "
               + "  AND   tblAgendaLogVisitas.estado    =            "
               + getEstado() + "                                     "
               + "  AND tblTerceros.idTipoTercero       =            "
               + xIdTipoTercero + " )                                "
               + "  ORDER BY tmpDET.numeroOrden,                     "
               + "           tmpDET.fechaEntrega                     ";

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
                fachadaBean = new FachadaColaboraDctoOrdenBean();

                //
                fachadaBean.setIdLog(rs.getInt("idLog"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setIdUsuario(rs.getDouble("idUsuario"));
                fachadaBean.setFechaEntrega(rs.getString("fechaEntrega"));
                fachadaBean.setNombreTercero(rs.getString("nombreTercero"));
                fachadaBean.setDireccionDespacho(
                                              rs.getString("direccionTercero"));
                fachadaBean.setTelefonoFijo(rs.getString("telefonoFijo"));
                fachadaBean.setNombreEmpresa(rs.getString("nombreEmpresa"));
                fachadaBean.setCiudadTercero(rs.getString("ciudadTercero"));
                fachadaBean.setNombreUsuario(rs.getString("NOMBREUSUARIO"));
                fachadaBean.setIdOrden(rs.getInt("IdOrden"));
                fachadaBean.setItem(rs.getInt("item"));
                fachadaBean.setCantidad(rs.getDouble("cantidad"));
                fachadaBean.setReferenciaCliente(
                                             rs.getString("referenciaCliente"));
                fachadaBean.setNumeroOrden(rs.getInt("numeroOrden"));
                fachadaBean.setIdFicha(rs.getInt("idFicha"));
                fachadaBean.setOrdenCompra(rs.getString("ordenCompra"));
                fachadaBean.setFechaOrden(rs.getString("fechaOrden"));

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

    // listaOTEstado
    public Vector listaOTEstado(int xIdTipoTercero) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        // Variable de fachada de los datos
        FachadaColaboraDctoOrdenBean fachadaBean;

        Connection connection = null;

        String selectStr =
               "  SELECT tmpDET.idLog,                               "
               + "         tmpDET.idCliente,                         "
               + "         tmpDET.IDUSUARIO,                         "
               + "         tmpDET.fechaEntrega,                      "
               + "         tblTerceros.nombreTercero,                "
               + "         tblTerceros.direccionTercero,             "
               + "         tblTerceros.telefonoFijo,                 "
               + "         tblTerceros.nombreEmpresa,                "
               + "         tblTerceros.ciudadTercero,                "
               + "         ctrlUsuarios.aliasUsuario AS              "
               + "                              NOMBREUSUARIO,       "
               + "         tmpDET.idLocal,                           "
               + "         tmpDET.idTipoOrden,                       "
               + "         tmpDET.idOrden,                           "
               + "         tmpDET.item,                              "
               + "         tmpDET.cantidad,                          "
               + "         tmpDET.referenciaCliente,                 "
               + "         tmpDET.numeroOrden,                       "
               + "         tmpDET.idFicha,                           "
               + "         tmpDET.ordenCompra,                       "
               + "         tmpDET.fechaOrden,                        "
               + "         tmpDET.pesoPedido,                        "
               + "         tmpDET.pesoTerminado,                     "
               + "         tmpDET.pesoRetal                          "
               + "  FROM   tblTerceros                               "
               + "  INNER JOIN tblAgendaLogVisitas                   "
               + "  ON tblTerceros.idCliente         =               "
               + "              tblAgendaLogVisitas.idCliente        "
               + "  INNER JOIN ctrlUsuarios                          "
               + "  ON tblAgendaLogVisitas.IDUSUARIO =               "
               + "                     ctrlUsuarios.IDUSUARIO        "
               + "  INNER JOIN                                       "
               + "  (SELECT tblDctosOrdenesDetalle.idLocal,          "
               + "         tblDctosOrdenesDetalle.idTipoOrden,       "
               + "         tblDctosOrdenesDetalle.idOrden,           "
               + "         tblDctosOrdenesDetalle.item,              "
               + "         tblDctosOrdenesDetalle.cantidad,          "
               + "         tblDctosOrdenesDetalle.referenciaCliente, "
               + "         tblDctosOrdenesDetalle.fechaEntrega,      "
               + "         tblDctosOrdenes.idLog,                    "
               + "         tblDctosOrdenes.numeroOrden,              "
               + "         tblDctosOrdenes.idFicha,                  "
               + "         tblDctosOrdenes.idCliente,                "
               + "         tblDctosOrdenes.idUsuario,                "
               + "         tblDctosOrdenes.ordenCompra,              "
               + "         tblDctosOrdenes.fechaOrden,               "
               + "        tblDctosOrdenesDetalle.idEstadoRefOriginal,"
               + "        tblDctosOrdenesDetalle.pesoPedido,         "
               + "        tblDctosOrdenesDetalle.pesoTerminado,      "
               + "        tblDctosOrdenesDetalle.pesoRetal           "
               + "   FROM       tblDctosOrdenes                      "
               + "   INNER JOIN tblDctosOrdenesDetalle               "
               + "   ON tblDctosOrdenes.IDLOCAL      =               "
               + "                   tblDctosOrdenesDetalle.IDLOCAL  "
               + "   AND tblDctosOrdenes.IDTIPOORDEN =               "
               + "               tblDctosOrdenesDetalle.IDTIPOORDEN  "
               + "   AND tblDctosOrdenes.IDORDEN     =               "
               + "                   tblDctosOrdenesDetalle.IDORDEN) "
               + "                                         AS tmpDET "
               + "  ON tmpDET.idLog = tblAgendaLogVisitas.idLog      "
               + "  WHERE tblAgendaLogVisitas.IDLOG IN (             "
               + "     SELECT tblAgendaLogVisitas.IDLOG              "
               + "     FROM   tblAgendaLogVisitas                    "
               + "     INNER JOIN tblDctosOrdenes                    "
               + "     ON tblAgendaLogVisitas.IDLOG     =            "
               + "                      tblDctosOrdenes.IDLOG        "
               + "  AND tmpDET.idEstadoRefOriginal      =            "
               + getIdEstado() + "                                   "
               + "  AND   tblDctosOrdenes.idLocal       =            "
               + getIdLocal() + "                                    "
               + "  AND   tblDctosOrdenes.idTipoOrden   =            "
               + getIdTipoOrden() + "                                "
               + "  AND   tblDctosOrdenes.idCliente     =           '"
               + getIdCliente() + "'                                 "
               + "  AND   tblDctosOrdenes.numeroOrden  != 0          "
               + "  AND tblTerceros.idTipoTercero       =            "
               + xIdTipoTercero + " )                                "
               + "  ORDER BY tmpDET.numeroOrden,                     "
               + "           tmpDET.fechaEntrega                     ";

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
                fachadaBean = new FachadaColaboraDctoOrdenBean();

                //
                fachadaBean.setIdLog(rs.getInt("idLog"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setIdUsuario(rs.getDouble("idUsuario"));
                fachadaBean.setFechaEntrega(rs.getString("fechaEntrega"));
                fachadaBean.setNombreTercero(rs.getString("nombreTercero"));
                fachadaBean.setDireccionDespacho(
                                              rs.getString("direccionTercero"));
                fachadaBean.setTelefonoFijo(rs.getString("telefonoFijo"));
                fachadaBean.setNombreEmpresa(rs.getString("nombreEmpresa"));
                fachadaBean.setCiudadTercero(rs.getString("ciudadTercero"));
                fachadaBean.setNombreUsuario(rs.getString("NOMBREUSUARIO"));
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setIdOrden(rs.getInt("idOrden"));
                fachadaBean.setItem(rs.getInt("item"));
                fachadaBean.setCantidad(rs.getDouble("cantidad"));
                fachadaBean.setReferenciaCliente(
                                             rs.getString("referenciaCliente"));
                fachadaBean.setNumeroOrden(rs.getInt("numeroOrden"));
                fachadaBean.setIdFicha(rs.getInt("idFicha"));
                fachadaBean.setOrdenCompra(rs.getString("ordenCompra"));
                fachadaBean.setFechaOrden(rs.getString("fechaOrden"));                
                fachadaBean.setPesoPedido(rs.getDouble("pesoPedido"));
                fachadaBean.setPesoTerminado(rs.getDouble("pesoTerminado"));
                fachadaBean.setPesoRetal(rs.getDouble("pesoRetal"));

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

    // listaAllAlfabeticoProduccionCumplido
    public Vector listaAllAlfabeticoProduccionCumplido(int xIdTipoTercero) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        // Variable de fachada de los datos
        FachadaColaboraDctoOrdenBean fachadaBean;

        Connection connection = null;

        String selectStr =
               "  SELECT tmpDET.idLog,                               "
               + "         tmpDET.idCliente,                         "
               + "         tmpDET.idUsuario,                         "
               + "         tmpDET.fechaEntrega,                      "
               + "         tblTerceros.nombreTercero,                "
               + "         tblTerceros.direccionTercero,             "
               + "         tblTerceros.telefonoFijo,                 "
               + "         tblTerceros.nombreEmpresa,                "
               + "         tblTerceros.ciudadTercero,                "
               + "         ctrlUsuarios.aliasUsuario AS              "
               + "                              NOMBREUSUARIO,       "
               + "         tmpDET.idLocal,                           "
               + "         tmpDET.idTipoOrden,                       "
               + "         tmpDET.idOrden,                           "
               + "         tmpDET.item,                              "
               + "         tmpDET.cantidad,                          "
               + "         tmpDET.referenciaCliente,                 "
               + "         tmpDET.numeroOrden,                       "
               + "         tmpDET.idFicha,                           "
               + "         tmpDET.ordenCompra,                       "
               + "         tmpDET.fechaOrden,                        "
               + "         tmpDET.pesoPedido,                        "
               + "         tmpDET.pesoTerminado,                     "
               + "         tmpDET.pesoRetal,                         "
               + "         tmpDET.cantidadFacturada,                 "
               + "         tmpDET.cantidadTerminada                  "
               + "  FROM   tblTerceros                               "
               + "  INNER JOIN tblAgendaLogVisitas                   "
               + "  ON tblTerceros.idCliente         =               "
               + "              tblAgendaLogVisitas.idCliente        "
               + "  INNER JOIN ctrlUsuarios                          "
               + "  ON tblAgendaLogVisitas.IDUSUARIO =               "
               + "                     ctrlUsuarios.IDUSUARIO        "
               + "  INNER JOIN                                       "
               + "  (SELECT tblDctosOrdenes.idLocal,                 "
               + "         tblDctosOrdenes.idTipoOrden,              "
               + "         tblDctosOrdenes.idOrden,                  "
               + "         tblDctosOrdenesDetalle.item,              "
               + "         tblDctosOrdenesDetalle.cantidad,          "
               + "         tblDctosOrdenesDetalle.referenciaCliente, "
               + "         tblDctosOrdenesDetalle.fechaEntrega,      "
               + "         tblDctosOrdenes.idLog,                    "
               + "         tblDctosOrdenes.numeroOrden,              "
               + "         tblDctosOrdenes.idFicha,                  "
               + "         tblDctosOrdenes.idCliente,                "
               + "         tblDctosOrdenes.idUsuario,                "
               + "         tblDctosOrdenes.ordenCompra,              "
               + "         tblDctosOrdenes.fechaOrden,               "
               + "        tblDctosOrdenesDetalle.idEstadoRefOriginal,"
               + "        tblDctosOrdenesDetalle.pesoPedido,         "
               + "        tblDctosOrdenesDetalle.pesoTerminado,      "
               + "        tblDctosOrdenesDetalle.pesoRetal,          "
               + "        tblDctosOrdenesDetalle.cantidadFacturada,  "
               + "        tblDctosOrdenesDetalle.cantidadTerminada   "
               + "   FROM       tblDctosOrdenes                      "
               + "   INNER JOIN tblDctosOrdenesDetalle               "
               + "   ON tblDctosOrdenes.IDLOCAL      =               "
               + "                   tblDctosOrdenesDetalle.IDLOCAL  "
               + "   AND tblDctosOrdenes.IDTIPOORDEN =               "
               + "               tblDctosOrdenesDetalle.IDTIPOORDEN  "
               + "   AND tblDctosOrdenes.IDORDEN     =               "
               + "                   tblDctosOrdenesDetalle.IDORDEN) "
               + "                                         AS tmpDET "
               + "  ON tmpDET.idLog = tblAgendaLogVisitas.idLog      "
               + "  WHERE tblAgendaLogVisitas.IDLOG IN (             "
               + "     SELECT tblAgendaLogVisitas.IDLOG              "
               + "     FROM   tblAgendaLogVisitas                    "
               + "     INNER JOIN tblDctosOrdenes                    "
               + "     ON tblAgendaLogVisitas.IDLOG     =            "
               + "                      tblDctosOrdenes.IDLOG        "
               + "  AND tmpDET.cantidad                <=            "
               + "                          tmpDET.cantidadTerminada "
               + "  AND tmpDET.pesoPedido               > 0          "
               + "  AND tmpDET.idEstadoRefOriginal     != 9          "
               + "  AND   tblDctosOrdenes.idLocal       =            "
               + getIdLocal() + "                                    "
               + "  AND   tblDctosOrdenes.idTipoOrden   =            "
               + getIdTipoOrden() + "                                "
               + "  AND   tblDctosOrdenes.numeroOrden  != 0          "
               + "  AND tblTerceros.idTipoTercero       =            "
               + xIdTipoTercero + " )                                "
               + "  ORDER BY tblTerceros.nombreTercero,              "
               + "           tmpDET.numeroOrden,                     "
               + "           tmpDET.fechaEntrega                     ";

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
                fachadaBean = new FachadaColaboraDctoOrdenBean();

                //
                fachadaBean.setIdLog(rs.getInt("idLog"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setIdUsuario(rs.getDouble("idUsuario"));
                fachadaBean.setFechaEntrega(rs.getString("fechaEntrega"));
                fachadaBean.setNombreTercero(rs.getString("nombreTercero"));
                fachadaBean.setDireccionDespacho(
                                              rs.getString("direccionTercero"));
                fachadaBean.setTelefonoFijo(rs.getString("telefonoFijo"));
                fachadaBean.setNombreEmpresa(rs.getString("nombreEmpresa"));
                fachadaBean.setCiudadTercero(rs.getString("ciudadTercero"));
                fachadaBean.setNombreUsuario(rs.getString("NOMBREUSUARIO"));
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setIdOrden(rs.getInt("idOrden"));
                fachadaBean.setItem(rs.getInt("item"));
                fachadaBean.setCantidad(rs.getDouble("cantidad"));
                fachadaBean.setReferenciaCliente(
                                             rs.getString("referenciaCliente"));
                fachadaBean.setNumeroOrden(rs.getInt("numeroOrden"));
                fachadaBean.setIdFicha(rs.getInt("idFicha"));
                fachadaBean.setOrdenCompra(rs.getString("ordenCompra"));
                fachadaBean.setFechaOrden(rs.getString("fechaOrden"));
                fachadaBean.setPesoPedido(rs.getDouble("pesoPedido"));
                fachadaBean.setPesoTerminado(rs.getDouble("pesoTerminado"));
                fachadaBean.setPesoRetal(rs.getDouble("pesoRetal"));
                fachadaBean.setCantidadFacturada(rs.getDouble("cantidadFacturada"));                
                fachadaBean.setCantidadTerminada(rs.getDouble("cantidadTerminada"));                

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

    // listaAllAlfabeticoProduccionPendiente
    public Vector listaAllAlfabeticoProduccionPendiente(int xIdTipoTercero) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        // Variable de fachada de los datos
        FachadaColaboraDctoOrdenBean fachadaBean;

        Connection connection = null;

        String selectStr =
               "  SELECT tmpDET.idLog,                               "
               + "         tmpDET.idCliente,                         "
               + "         tmpDET.idUsuario,                         "
               + "         tmpDET.fechaEntrega,                      "
               + "         tblTerceros.nombreTercero,                "
               + "         tblTerceros.direccionTercero,             "
               + "         tblTerceros.telefonoFijo,                 "
               + "         tblTerceros.nombreEmpresa,                "
               + "         tblTerceros.ciudadTercero,                "
               + "         ctrlUsuarios.aliasUsuario AS              "
               + "                              NOMBREUSUARIO,       "
               + "         tmpDET.idLocal,                           "
               + "         tmpDET.idTipoOrden,                       "
               + "         tmpDET.idOrden,                           "
               + "         tmpDET.item,                              "
               + "         tmpDET.cantidad,                          "
               + "         tmpDET.referenciaCliente,                 "
               + "         tmpDET.numeroOrden,                       "
               + "         tmpDET.idFicha,                           "
               + "         tmpDET.ordenCompra,                       "
               + "         tmpDET.fechaOrden,                        "
               + "         tmpDET.pesoPedido,                        "
               + "         tmpDET.pesoTerminado,                     "
               + "         tmpDET.pesoRetal,                         "
               + "         tmpDET.cantidadFacturada,                 "
               + "         tmpDET.cantidadTerminada                  "
               + "  FROM   tblTerceros                               "
               + "  INNER JOIN tblAgendaLogVisitas                   "
               + "  ON tblTerceros.idCliente         =               "
               + "              tblAgendaLogVisitas.idCliente        "
               + "  INNER JOIN ctrlUsuarios                          "
               + "  ON tblAgendaLogVisitas.IDUSUARIO =               "
               + "                     ctrlUsuarios.IDUSUARIO        "
               + "  INNER JOIN                                       "
               + "  (SELECT tblDctosOrdenes.idLocal,                 "
               + "         tblDctosOrdenes.idTipoOrden,              "
               + "         tblDctosOrdenes.idOrden,                  "
               + "         tblDctosOrdenesDetalle.item,              "
               + "         tblDctosOrdenesDetalle.cantidad,          "
               + "         tblDctosOrdenesDetalle.referenciaCliente, "
               + "         tblDctosOrdenesDetalle.fechaEntrega,      "
               + "         tblDctosOrdenes.idLog,                    "
               + "         tblDctosOrdenes.numeroOrden,              "
               + "         tblDctosOrdenes.idFicha,                  "
               + "         tblDctosOrdenes.idCliente,                "
               + "         tblDctosOrdenes.idUsuario,                "
               + "         tblDctosOrdenes.ordenCompra,              "
               + "         tblDctosOrdenes.fechaOrden,               "
               + "        tblDctosOrdenesDetalle.idEstadoRefOriginal,"
               + "        tblDctosOrdenesDetalle.pesoPedido,         "
               + "        tblDctosOrdenesDetalle.pesoTerminado,      "
               + "        tblDctosOrdenesDetalle.pesoRetal,          "
               + "        tblDctosOrdenesDetalle.cantidadFacturada,  "
               + "        tblDctosOrdenesDetalle.cantidadTerminada   "
               + "   FROM       tblDctosOrdenes                      "
               + "   INNER JOIN tblDctosOrdenesDetalle               "
               + "   ON tblDctosOrdenes.IDLOCAL      =               "
               + "                   tblDctosOrdenesDetalle.IDLOCAL  "
               + "   AND tblDctosOrdenes.IDTIPOORDEN =               "
               + "               tblDctosOrdenesDetalle.IDTIPOORDEN  "
               + "   AND tblDctosOrdenes.IDORDEN     =               "
               + "                   tblDctosOrdenesDetalle.IDORDEN) "
               + "                                         AS tmpDET "
               + "  ON tmpDET.idLog = tblAgendaLogVisitas.idLog      "
               + "  WHERE tblAgendaLogVisitas.IDLOG IN (             "
               + "     SELECT tblAgendaLogVisitas.IDLOG              "
               + "     FROM   tblAgendaLogVisitas                    "
               + "     INNER JOIN tblDctosOrdenes                    "
               + "     ON tblAgendaLogVisitas.IDLOG     =            "
               + "                      tblDctosOrdenes.IDLOG        "
               + "  AND tmpDET.cantidad                 >            "
               + "                          tmpDET.cantidadTerminada "
               + "  AND tmpDET.pesoPedido               > 0          "
               + "  AND tmpDET.idEstadoRefOriginal     != 9          "    
               + "  AND   tblDctosOrdenes.idLocal       =            "
               + getIdLocal() + "                                    "
               + "  AND   tblDctosOrdenes.idTipoOrden   =            "
               + getIdTipoOrden() + "                                "
               + "  AND   tblDctosOrdenes.numeroOrden  != 0          "
               + "  AND tblTerceros.idTipoTercero       =            "
               + xIdTipoTercero + " )                                "
               + "  ORDER BY tblTerceros.nombreTercero,              "
               + "           tmpDET.numeroOrden,                     "
               + "           tmpDET.fechaEntrega                     ";

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
                fachadaBean = new FachadaColaboraDctoOrdenBean();

                //
                fachadaBean.setIdLog(rs.getInt("idLog"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setIdUsuario(rs.getDouble("idUsuario"));
                fachadaBean.setFechaEntrega(rs.getString("fechaEntrega"));
                fachadaBean.setNombreTercero(rs.getString("nombreTercero"));
                fachadaBean.setDireccionDespacho(
                                              rs.getString("direccionTercero"));
                fachadaBean.setTelefonoFijo(rs.getString("telefonoFijo"));
                fachadaBean.setNombreEmpresa(rs.getString("nombreEmpresa"));
                fachadaBean.setCiudadTercero(rs.getString("ciudadTercero"));
                fachadaBean.setNombreUsuario(rs.getString("NOMBREUSUARIO"));
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setIdOrden(rs.getInt("idOrden"));
                fachadaBean.setItem(rs.getInt("item"));
                fachadaBean.setCantidad(rs.getDouble("cantidad"));
                fachadaBean.setReferenciaCliente(
                                             rs.getString("referenciaCliente"));
                fachadaBean.setNumeroOrden(rs.getInt("numeroOrden"));
                fachadaBean.setIdFicha(rs.getInt("idFicha"));
                fachadaBean.setOrdenCompra(rs.getString("ordenCompra"));
                fachadaBean.setFechaOrden(rs.getString("fechaOrden"));
                fachadaBean.setPesoPedido(rs.getDouble("pesoPedido"));
                fachadaBean.setPesoTerminado(rs.getDouble("pesoTerminado"));
                fachadaBean.setPesoRetal(rs.getDouble("pesoRetal"));
                fachadaBean.setCantidadFacturada(rs.getDouble("cantidadFacturada"));
                fachadaBean.setCantidadTerminada(rs.getDouble("cantidadTerminada"));

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

    // listaUnAlfabeticoProduccionPendiente
    public Vector listaUnAlfabeticoProduccionPendiente(int xIdTipoTercero) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        // Variable de fachada de los datos
        FachadaColaboraDctoOrdenBean fachadaBean;

        Connection connection = null;

        String selectStr =
               "  SELECT tmpDET.idLog,                               "
               + "         tmpDET.idCliente,                         "
               + "         tmpDET.idUsuario,                         "
               + "         tmpDET.fechaEntrega,                      "
               + "         tblTerceros.nombreTercero,                "
               + "         tblTerceros.direccionTercero,             "
               + "         tblTerceros.telefonoFijo,                 "
               + "         tblTerceros.nombreEmpresa,                "
               + "         tblTerceros.ciudadTercero,                "
               + "         ctrlUsuarios.aliasUsuario AS              "
               + "                              NOMBREUSUARIO,       "
               + "         tmpDET.idLocal,                           "
               + "         tmpDET.idTipoOrden,                       "
               + "         tmpDET.idOrden,                           "
               + "         tmpDET.item,                              "
               + "         tmpDET.cantidad,                          "
               + "         tmpDET.referenciaCliente,                 "
               + "         tmpDET.numeroOrden,                       "
               + "         tmpDET.idFicha,                           "
               + "         tmpDET.ordenCompra,                       "
               + "         tmpDET.fechaOrden,                        "
               + "         tmpDET.pesoPedido,                        "
               + "         tmpDET.pesoTerminado,                     "
               + "         tmpDET.pesoRetal,                         "
               + "         tmpDET.cantidadFacturada,                 "
               + "         tmpDET.cantidadTerminada                  "
               + "  FROM   tblTerceros                               "
               + "  INNER JOIN tblAgendaLogVisitas                   "
               + "  ON tblTerceros.idCliente         =               "
               + "              tblAgendaLogVisitas.idCliente        "
               + "  INNER JOIN ctrlUsuarios                          "
               + "  ON tblAgendaLogVisitas.IDUSUARIO =               "
               + "                     ctrlUsuarios.IDUSUARIO        "
               + "  INNER JOIN                                       "
               + "  (SELECT tblDctosOrdenes.idLocal,                 "
               + "         tblDctosOrdenes.idTipoOrden,              "
               + "         tblDctosOrdenes.idOrden,                  "
               + "         tblDctosOrdenesDetalle.item,              "
               + "         tblDctosOrdenesDetalle.cantidad,          "
               + "         tblDctosOrdenesDetalle.referenciaCliente, "
               + "         tblDctosOrdenesDetalle.fechaEntrega,      "
               + "         tblDctosOrdenes.idLog,                    "
               + "         tblDctosOrdenes.numeroOrden,              "
               + "         tblDctosOrdenes.idFicha,                  "
               + "         tblDctosOrdenes.idCliente,                "
               + "         tblDctosOrdenes.idUsuario,                "
               + "         tblDctosOrdenes.ordenCompra,              "
               + "         tblDctosOrdenes.fechaOrden,               "
               + "        tblDctosOrdenesDetalle.idEstadoRefOriginal,"
               + "        tblDctosOrdenesDetalle.pesoPedido,         "
               + "        tblDctosOrdenesDetalle.pesoTerminado,      "
               + "        tblDctosOrdenesDetalle.pesoRetal,          "
               + "        tblDctosOrdenesDetalle.cantidadFacturada,  "
               + "        tblDctosOrdenesDetalle.cantidadTerminada   "
               + "   FROM       tblDctosOrdenes                      "
               + "   INNER JOIN tblDctosOrdenesDetalle               "
               + "   ON tblDctosOrdenes.IDLOCAL      =               "
               + "                   tblDctosOrdenesDetalle.IDLOCAL  "
               + "   AND tblDctosOrdenes.IDTIPOORDEN =               "
               + "               tblDctosOrdenesDetalle.IDTIPOORDEN  "
               + "   AND tblDctosOrdenes.IDORDEN     =               "
               + "                   tblDctosOrdenesDetalle.IDORDEN) "
               + "                                         AS tmpDET "
               + "  ON tmpDET.idLog = tblAgendaLogVisitas.idLog      "
               + "  WHERE tblAgendaLogVisitas.IDLOG IN (             "
               + "     SELECT tblAgendaLogVisitas.IDLOG              "
               + "     FROM   tblAgendaLogVisitas                    "
               + "     INNER JOIN tblDctosOrdenes                    "
               + "     ON tblAgendaLogVisitas.IDLOG     =            "
               + "                      tblDctosOrdenes.IDLOG        "
               + "  AND tmpDET.cantidad                 >            "
               + "                          tmpDET.cantidadTerminada "
               + "  AND tmpDET.pesoPedido               > 0          "
               + "  AND tmpDET.idEstadoRefOriginal     != 9          "    
               + "  AND   tblDctosOrdenes.idLocal       =            "
               + getIdLocal() + "                                    "
               + "  AND   tblDctosOrdenes.idTipoOrden   =            "
               + getIdTipoOrden() + "                                "
               + "  AND   tblDctosOrdenes.idCliente     =           '"
               + getIdCliente() + "'                                 "
               + "  AND   tblDctosOrdenes.numeroOrden  != 0          "
               + "  AND tblTerceros.idTipoTercero       =            "
               + xIdTipoTercero + " )                                "
               + "  ORDER BY tblTerceros.nombreTercero,              "
               + "           tmpDET.numeroOrden,                     "
               + "           tmpDET.fechaEntrega                     ";

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
                fachadaBean = new FachadaColaboraDctoOrdenBean();

                //
                fachadaBean.setIdLog(rs.getInt("idLog"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setIdUsuario(rs.getDouble("idUsuario"));
                fachadaBean.setFechaEntrega(rs.getString("fechaEntrega"));
                fachadaBean.setNombreTercero(rs.getString("nombreTercero"));
                fachadaBean.setDireccionDespacho(
                                              rs.getString("direccionTercero"));
                fachadaBean.setTelefonoFijo(rs.getString("telefonoFijo"));
                fachadaBean.setNombreEmpresa(rs.getString("nombreEmpresa"));
                fachadaBean.setCiudadTercero(rs.getString("ciudadTercero"));
                fachadaBean.setNombreUsuario(rs.getString("NOMBREUSUARIO"));
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setIdOrden(rs.getInt("idOrden"));
                fachadaBean.setItem(rs.getInt("item"));
                fachadaBean.setCantidad(rs.getDouble("cantidad"));
                fachadaBean.setReferenciaCliente(
                                             rs.getString("referenciaCliente"));
                fachadaBean.setNumeroOrden(rs.getInt("numeroOrden"));
                fachadaBean.setIdFicha(rs.getInt("idFicha"));
                fachadaBean.setOrdenCompra(rs.getString("ordenCompra"));
                fachadaBean.setFechaOrden(rs.getString("fechaOrden"));
                fachadaBean.setPesoPedido(rs.getDouble("pesoPedido"));
                fachadaBean.setPesoTerminado(rs.getDouble("pesoTerminado"));
                fachadaBean.setPesoRetal(rs.getDouble("pesoRetal"));
                fachadaBean.setCantidadFacturada(rs.getDouble("cantidadFacturada"));
                fachadaBean.setCantidadTerminada(rs.getDouble("cantidadTerminada"));

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

    // listaUnAlfabeticoProduccionPendiente
    public Vector listaUnAlfabeticoProduccionCumplido(int xIdTipoTercero) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        // Variable de fachada de los datos
        FachadaColaboraDctoOrdenBean fachadaBean;

        Connection connection = null;

        String selectStr =
               "  SELECT tmpDET.idLog,                               "
               + "         tmpDET.idCliente,                         "
               + "         tmpDET.idUsuario,                         "
               + "         tmpDET.fechaEntrega,                      "
               + "         tblTerceros.nombreTercero,                "
               + "         tblTerceros.direccionTercero,             "
               + "         tblTerceros.telefonoFijo,                 "
               + "         tblTerceros.nombreEmpresa,                "
               + "         tblTerceros.ciudadTercero,                "
               + "         ctrlUsuarios.aliasUsuario AS              "
               + "                              NOMBREUSUARIO,       "
               + "         tmpDET.idLocal,                           "
               + "         tmpDET.idTipoOrden,                       "
               + "         tmpDET.idOrden,                           "
               + "         tmpDET.item,                              "
               + "         tmpDET.cantidad,                          "
               + "         tmpDET.referenciaCliente,                 "
               + "         tmpDET.numeroOrden,                       "
               + "         tmpDET.idFicha,                           "
               + "         tmpDET.ordenCompra,                       "
               + "         tmpDET.fechaOrden,                        "
               + "         tmpDET.pesoPedido,                        "
               + "         tmpDET.pesoTerminado,                     "
               + "         tmpDET.pesoRetal,                         "
               + "         tmpDET.cantidadFacturada,                 "
               + "         tmpDET.cantidadTerminada                  "
               + "  FROM   tblTerceros                               "
               + "  INNER JOIN tblAgendaLogVisitas                   "
               + "  ON tblTerceros.idCliente         =               "
               + "              tblAgendaLogVisitas.idCliente        "
               + "  INNER JOIN ctrlUsuarios                          "
               + "  ON tblAgendaLogVisitas.IDUSUARIO =               "
               + "                     ctrlUsuarios.IDUSUARIO        "
               + "  INNER JOIN                                       "
               + "  (SELECT tblDctosOrdenes.idLocal,                 "
               + "         tblDctosOrdenes.idTipoOrden,              "
               + "         tblDctosOrdenes.idOrden,                  "
               + "         tblDctosOrdenesDetalle.item,              "
               + "         tblDctosOrdenesDetalle.cantidad,          "
               + "         tblDctosOrdenesDetalle.referenciaCliente, "
               + "         tblDctosOrdenesDetalle.fechaEntrega,      "
               + "         tblDctosOrdenes.idLog,                    "
               + "         tblDctosOrdenes.numeroOrden,              "
               + "         tblDctosOrdenes.idFicha,                  "
               + "         tblDctosOrdenes.idCliente,                "
               + "         tblDctosOrdenes.idUsuario,                "
               + "         tblDctosOrdenes.ordenCompra,              "
               + "         tblDctosOrdenes.fechaOrden,               "
               + "        tblDctosOrdenesDetalle.idEstadoRefOriginal,"
               + "        tblDctosOrdenesDetalle.pesoPedido,         "
               + "        tblDctosOrdenesDetalle.pesoTerminado,      "
               + "        tblDctosOrdenesDetalle.pesoRetal,          "
               + "        tblDctosOrdenesDetalle.cantidadFacturada,  "
               + "        tblDctosOrdenesDetalle.cantidadTerminada   "
               + "   FROM       tblDctosOrdenes                      "
               + "   INNER JOIN tblDctosOrdenesDetalle               "
               + "   ON tblDctosOrdenes.IDLOCAL      =               "
               + "                   tblDctosOrdenesDetalle.IDLOCAL  "
               + "   AND tblDctosOrdenes.IDTIPOORDEN =               "
               + "               tblDctosOrdenesDetalle.IDTIPOORDEN  "
               + "   AND tblDctosOrdenes.IDORDEN     =               "
               + "                   tblDctosOrdenesDetalle.IDORDEN) "
               + "                                         AS tmpDET "
               + "  ON tmpDET.idLog = tblAgendaLogVisitas.idLog      "
               + "  WHERE tblAgendaLogVisitas.IDLOG IN (             "
               + "     SELECT tblAgendaLogVisitas.IDLOG              "
               + "     FROM   tblAgendaLogVisitas                    "
               + "     INNER JOIN tblDctosOrdenes                    "
               + "     ON tblAgendaLogVisitas.IDLOG     =            "
               + "                      tblDctosOrdenes.IDLOG        "
               + "  AND tmpDET.cantidad                <=            "
               + "                          tmpDET.cantidadTerminada "
               + "  AND tmpDET.pesoPedido               > 0          "
               + "  AND tmpDET.idEstadoRefOriginal     != 9          "    
               + "  AND   tblDctosOrdenes.idLocal       =            "
               + getIdLocal() + "                                    "
               + "  AND   tblDctosOrdenes.idTipoOrden   =            "
               + getIdTipoOrden() + "                                "
               + "  AND   tblDctosOrdenes.numeroOrden  != 0          "
               + "  AND   tblDctosOrdenes.idCliente     =           '"
               + getIdCliente() + "'                                 "
               + "  AND tblTerceros.idTipoTercero       =            "
               + xIdTipoTercero + " )                                "
               + "  ORDER BY tblTerceros.nombreTercero,              "
               + "           tmpDET.numeroOrden,                     "
               + "           tmpDET.fechaEntrega                     ";

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
                fachadaBean = new FachadaColaboraDctoOrdenBean();

                //
                fachadaBean.setIdLog(rs.getInt("idLog"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setIdUsuario(rs.getDouble("idUsuario"));
                fachadaBean.setFechaEntrega(rs.getString("fechaEntrega"));
                fachadaBean.setNombreTercero(rs.getString("nombreTercero"));
                fachadaBean.setDireccionDespacho(
                                              rs.getString("direccionTercero"));
                fachadaBean.setTelefonoFijo(rs.getString("telefonoFijo"));
                fachadaBean.setNombreEmpresa(rs.getString("nombreEmpresa"));
                fachadaBean.setCiudadTercero(rs.getString("ciudadTercero"));
                fachadaBean.setNombreUsuario(rs.getString("NOMBREUSUARIO"));
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setIdOrden(rs.getInt("idOrden"));
                fachadaBean.setItem(rs.getInt("item"));
                fachadaBean.setCantidad(rs.getDouble("cantidad"));
                fachadaBean.setReferenciaCliente(
                                             rs.getString("referenciaCliente"));
                fachadaBean.setNumeroOrden(rs.getInt("numeroOrden"));
                fachadaBean.setIdFicha(rs.getInt("idFicha"));
                fachadaBean.setOrdenCompra(rs.getString("ordenCompra"));
                fachadaBean.setFechaOrden(rs.getString("fechaOrden"));
                fachadaBean.setPesoPedido(rs.getDouble("pesoPedido"));
                fachadaBean.setPesoTerminado(rs.getDouble("pesoTerminado"));
                fachadaBean.setPesoRetal(rs.getDouble("pesoRetal"));
                fachadaBean.setCantidadFacturada(rs.getDouble("cantidadFacturada"));
                fachadaBean.setCantidadTerminada(rs.getDouble("cantidadTerminada"));

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

    // listaAllAlfabeticoFacturado
    public Vector listaAllAlfabeticoFacturado(int xIdTipoTercero) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        // Variable de fachada de los datos
        FachadaColaboraDctoOrdenBean fachadaBean;

        Connection connection = null;

        String selectStr =
               "  SELECT tmpDET.idLog,                               "
               + "         tmpDET.idCliente,                         "
               + "         tmpDET.idUsuario,                         "
               + "         tmpDET.fechaEntrega,                      "
               + "         tblTerceros.nombreTercero,                "
               + "         tblTerceros.direccionTercero,             "
               + "         tblTerceros.telefonoFijo,                 "
               + "         tblTerceros.nombreEmpresa,                "
               + "         tblTerceros.ciudadTercero,                "
               + "         ctrlUsuarios.aliasUsuario AS              "
               + "                              NOMBREUSUARIO,       "
               + "         tmpDET.idLocal,                           "
               + "         tmpDET.idTipoOrden,                       "
               + "         tmpDET.idOrden,                           "
               + "         tmpDET.item,                              "
               + "         tmpDET.cantidad,                          "
               + "         tmpDET.referenciaCliente,                 "
               + "         tmpDET.numeroOrden,                       "
               + "         tmpDET.idFicha,                           "
               + "         tmpDET.ordenCompra,                       "
               + "         tmpDET.fechaOrden,                        "
               + "         tmpDET.pesoPedido,                        "
               + "         tmpDET.pesoTerminado,                     "
               + "         tmpDET.pesoRetal,                         "
               + "         tmpDET.cantidadFacturada,                 "
               + "         tmpDET.cantidadTerminada                  "
               + "  FROM   tblTerceros                               "
               + "  INNER JOIN tblAgendaLogVisitas                   "
               + "  ON tblTerceros.idCliente         =               "
               + "              tblAgendaLogVisitas.idCliente        "
               + "  INNER JOIN ctrlUsuarios                          "
               + "  ON tblAgendaLogVisitas.IDUSUARIO =               "
               + "                     ctrlUsuarios.IDUSUARIO        "
               + "  INNER JOIN                                       "
               + "  (SELECT tblDctosOrdenesDetalle.idLocal,          "
               + "         tblDctosOrdenesDetalle.idTipoOrden,       "
               + "         tblDctosOrdenesDetalle.idOrden,           "
               + "         tblDctosOrdenesDetalle.item,              "
               + "         tblDctosOrdenesDetalle.cantidad,          "
               + "         tblDctosOrdenesDetalle.referenciaCliente, "
               + "         tblDctosOrdenesDetalle.fechaEntrega,      "
               + "         tblDctosOrdenes.idLog,                    "
               + "         tblDctosOrdenes.numeroOrden,              "
               + "         tblDctosOrdenes.idFicha,                  "
               + "         tblDctosOrdenes.idCliente,                "
               + "         tblDctosOrdenes.idUsuario,                "
               + "         tblDctosOrdenes.ordenCompra,              "
               + "         tblDctosOrdenes.fechaOrden,               "
               + "        tblDctosOrdenesDetalle.idEstadoRefOriginal,"
               + "        tblDctosOrdenesDetalle.pesoPedido,         "
               + "        tblDctosOrdenesDetalle.pesoTerminado,      "
               + "        tblDctosOrdenesDetalle.pesoRetal,          "
               + "        tblDctosOrdenesDetalle.cantidadFacturada,  "
               + "        tblDctosOrdenesDetalle.cantidadTerminada   "
               + "   FROM       tblDctosOrdenes                      "
               + "   INNER JOIN tblDctosOrdenesDetalle               "
               + "   ON tblDctosOrdenes.IDLOCAL      =               "
               + "                   tblDctosOrdenesDetalle.IDLOCAL  "
               + "   AND tblDctosOrdenes.IDTIPOORDEN =               "
               + "               tblDctosOrdenesDetalle.IDTIPOORDEN  "
               + "   AND tblDctosOrdenes.IDORDEN     =               "
               + "                   tblDctosOrdenesDetalle.IDORDEN) "
               + "                                         AS tmpDET "
               + "  ON tmpDET.idLog = tblAgendaLogVisitas.idLog      "
               + "  WHERE tblAgendaLogVisitas.IDLOG IN (             "
               + "     SELECT tblAgendaLogVisitas.IDLOG              "
               + "     FROM   tblAgendaLogVisitas                    "
               + "     INNER JOIN tblDctosOrdenes                    "
               + "     ON tblAgendaLogVisitas.IDLOG     =            "
               + "                      tblDctosOrdenes.IDLOG        "
               + " AND tmpDET.cantidadTerminada <=                   "
               + "                          tmpDET.cantidadFacturada "
               + "  AND tmpDET.cantidadFacturada        > 0          "
               + "  AND tmpDET.pesoPedido               > 0          "
               + "  AND tmpDET.idEstadoRefOriginal     != 9          "    
               + "  AND   tblDctosOrdenes.idLocal       =            "
               + getIdLocal() + "                                    "
               + "  AND   tblDctosOrdenes.idTipoOrden   =            "
               + getIdTipoOrden() + "                                "
               + "  AND   tblDctosOrdenes.numeroOrden  != 0          "
               + "  AND tblTerceros.idTipoTercero       =            "
               + xIdTipoTercero + " )                                "
               + "  ORDER BY tblTerceros.nombreTercero,              "
               + "           tmpDET.numeroOrden,                     "
               + "           tmpDET.fechaEntrega                     ";

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
                fachadaBean = new FachadaColaboraDctoOrdenBean();

                //
                fachadaBean.setIdLog(rs.getInt("idLog"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setIdUsuario(rs.getDouble("idUsuario"));
                fachadaBean.setFechaEntrega(rs.getString("fechaEntrega"));
                fachadaBean.setNombreTercero(rs.getString("nombreTercero"));
                fachadaBean.setDireccionDespacho(
                                              rs.getString("direccionTercero"));
                fachadaBean.setTelefonoFijo(rs.getString("telefonoFijo"));
                fachadaBean.setNombreEmpresa(rs.getString("nombreEmpresa"));
                fachadaBean.setCiudadTercero(rs.getString("ciudadTercero"));
                fachadaBean.setNombreUsuario(rs.getString("NOMBREUSUARIO"));
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setIdOrden(rs.getInt("idOrden"));
                fachadaBean.setItem(rs.getInt("item"));
                fachadaBean.setCantidad(rs.getDouble("cantidad"));
                fachadaBean.setReferenciaCliente(
                                             rs.getString("referenciaCliente"));
                fachadaBean.setNumeroOrden(rs.getInt("numeroOrden"));
                fachadaBean.setIdFicha(rs.getInt("idFicha"));
                fachadaBean.setOrdenCompra(rs.getString("ordenCompra"));
                fachadaBean.setFechaOrden(rs.getString("fechaOrden"));
                fachadaBean.setPesoPedido(rs.getDouble("pesoPedido"));
                fachadaBean.setPesoTerminado(rs.getDouble("pesoTerminado"));
                fachadaBean.setPesoRetal(rs.getDouble("pesoRetal"));
                fachadaBean.setCantidadFacturada(
                                             rs.getDouble("cantidadFacturada"));


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

    // listaAllAlfabeticoSinFactura
    public Vector listaAllAlfabeticoSinFactura(int xIdTipoTercero) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        // Variable de fachada de los datos
        FachadaColaboraDctoOrdenBean fachadaBean;

        Connection connection = null;

        String selectStr =
               "  SELECT tmpDET.idLog,                               "
               + "         tmpDET.idCliente,                         "
               + "         tmpDET.idUsuario,                         "
               + "         tmpDET.fechaEntrega,                      "
               + "         tblTerceros.nombreTercero,                "
               + "         tblTerceros.direccionTercero,             "
               + "         tblTerceros.telefonoFijo,                 "
               + "         tblTerceros.nombreEmpresa,                "
               + "         tblTerceros.ciudadTercero,                "
               + "         ctrlUsuarios.aliasUsuario AS              "
               + "                              NOMBREUSUARIO,       "
               + "         tmpDET.idLocal,                           "
               + "         tmpDET.idTipoOrden,                       "
               + "         tmpDET.idOrden,                           "
               + "         tmpDET.item,                              "
               + "         tmpDET.cantidad,                          "
               + "         tmpDET.referenciaCliente,                 "
               + "         tmpDET.numeroOrden,                       "
               + "         tmpDET.idFicha,                           "
               + "         tmpDET.ordenCompra,                       "
               + "         tmpDET.fechaOrden,                        "
               + "         tmpDET.pesoPedido,                        "
               + "         tmpDET.pesoTerminado,                     "
               + "         tmpDET.pesoRetal,                         "
               + "         tmpDET.cantidadFacturada,                 "
               + "         tmpDET.cantidadTerminada                  "
               + "  FROM   tblTerceros                               "
               + "  INNER JOIN tblAgendaLogVisitas                   "
               + "  ON tblTerceros.idCliente         =               "
               + "              tblAgendaLogVisitas.idCliente        "
               + "  INNER JOIN ctrlUsuarios                          "
               + "  ON tblAgendaLogVisitas.IDUSUARIO =               "
               + "                     ctrlUsuarios.IDUSUARIO        "
               + "  INNER JOIN                                       "
               + "  (SELECT tblDctosOrdenes.idLocal,                 "
               + "         tblDctosOrdenes.idTipoOrden,              "
               + "         tblDctosOrdenes.idOrden,                  "
               + "         tblDctosOrdenesDetalle.item,              "
               + "         tblDctosOrdenesDetalle.cantidad,          "
               + "         tblDctosOrdenesDetalle.referenciaCliente, "
               + "         tblDctosOrdenesDetalle.fechaEntrega,      "
               + "         tblDctosOrdenes.idLog,                    "
               + "         tblDctosOrdenes.numeroOrden,              "
               + "         tblDctosOrdenes.idFicha,                  "
               + "         tblDctosOrdenes.idCliente,                "
               + "         tblDctosOrdenes.idUsuario,                "
               + "         tblDctosOrdenes.ordenCompra,              "
               + "         tblDctosOrdenes.fechaOrden,               "
               + "        tblDctosOrdenesDetalle.idEstadoRefOriginal,"
               + "        tblDctosOrdenesDetalle.pesoPedido,         "
               + "        tblDctosOrdenesDetalle.pesoTerminado,      "
               + "        tblDctosOrdenesDetalle.pesoRetal,          "
               + "        tblDctosOrdenesDetalle.cantidadFacturada,  "
               + "        tblDctosOrdenesDetalle.cantidadTerminada   "
               + "   FROM       tblDctosOrdenes                      "
               + "   INNER JOIN tblDctosOrdenesDetalle               "
               + "   ON tblDctosOrdenes.IDLOCAL      =               "
               + "                   tblDctosOrdenesDetalle.IDLOCAL  "
               + "   AND tblDctosOrdenes.IDTIPOORDEN =               "
               + "               tblDctosOrdenesDetalle.IDTIPOORDEN  "
               + "   AND tblDctosOrdenes.IDORDEN     =               "
               + "                   tblDctosOrdenesDetalle.IDORDEN) "
               + "                                         AS tmpDET "
               + "  ON tmpDET.idLog = tblAgendaLogVisitas.idLog      "
               + "  WHERE tblAgendaLogVisitas.IDLOG IN (             "
               + "     SELECT tblAgendaLogVisitas.IDLOG              "
               + "     FROM   tblAgendaLogVisitas                    "
               + "     INNER JOIN tblDctosOrdenes                    "
               + "     ON tblAgendaLogVisitas.IDLOG     =            "
               + "                      tblDctosOrdenes.IDLOG        "
               + " AND tmpDET.cantidadTerminada        >=            "
               + "                          tmpDET.cantidadFacturada "
               + "  AND tmpDET.pesoPedido               > 0          "
               + "  AND tmpDET.idEstadoRefOriginal     != 9          "
               + "  AND   tblDctosOrdenes.idLocal       =            "
               + getIdLocal() + "                                    "
               + "  AND   tblDctosOrdenes.idTipoOrden   =            "
               + getIdTipoOrden() + "                                "
               + "  AND   tblDctosOrdenes.numeroOrden  != 0          "
               + "  AND tblTerceros.idTipoTercero       =            "
               + xIdTipoTercero + " )                                "
               + "  ORDER BY tblTerceros.nombreTercero,              "
               + "           tmpDET.numeroOrden,                     "
               + "           tmpDET.fechaEntrega                     ";

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
                fachadaBean = new FachadaColaboraDctoOrdenBean();

                //
                fachadaBean.setIdLog(rs.getInt("idLog"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setIdUsuario(rs.getDouble("idUsuario"));
                fachadaBean.setFechaEntrega(rs.getString("fechaEntrega"));
                fachadaBean.setNombreTercero(rs.getString("nombreTercero"));
                fachadaBean.setDireccionDespacho(
                                              rs.getString("direccionTercero"));
                fachadaBean.setTelefonoFijo(rs.getString("telefonoFijo"));
                fachadaBean.setNombreEmpresa(rs.getString("nombreEmpresa"));
                fachadaBean.setCiudadTercero(rs.getString("ciudadTercero"));
                fachadaBean.setNombreUsuario(rs.getString("NOMBREUSUARIO"));
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setIdOrden(rs.getInt("idOrden"));
                fachadaBean.setItem(rs.getInt("item"));
                fachadaBean.setCantidad(rs.getDouble("cantidad"));
                fachadaBean.setReferenciaCliente(
                                             rs.getString("referenciaCliente"));
                fachadaBean.setNumeroOrden(rs.getInt("numeroOrden"));
                fachadaBean.setIdFicha(rs.getInt("idFicha"));
                fachadaBean.setOrdenCompra(rs.getString("ordenCompra"));
                fachadaBean.setFechaOrden(rs.getString("fechaOrden"));
                fachadaBean.setPesoPedido(rs.getDouble("pesoPedido"));
                fachadaBean.setPesoTerminado(rs.getDouble("pesoTerminado"));
                fachadaBean.setPesoRetal(rs.getDouble("pesoRetal"));
                fachadaBean.setCantidadFacturada(
                                             rs.getDouble("cantidadFacturada"));
                fachadaBean.setCantidadTerminada(
                                             rs.getDouble("cantidadTerminada"));


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

    // listaAllAlfabeticoRemisionSinFactura
    public Vector listaAllAlfabeticoRemisionSinFactura(int xIdTipoTercero) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        // Variable de fachada de los datos
        FachadaColaboraDctoOrdenBean fachadaBean;

        Connection connection = null;

        String selectStr =
               "  SELECT tmpDET.idLog,                               "
               + "         tmpDET.idCliente,                         "
               + "         tmpDET.idUsuario,                         "
               + "         tmpDET.fechaEntrega,                      "
               + "         tblTerceros.nombreTercero,                "
               + "         tblTerceros.direccionTercero,             "
               + "         tblTerceros.telefonoFijo,                 "
               + "         tblTerceros.nombreEmpresa,                "
               + "         tblTerceros.ciudadTercero,                "
               + "         ctrlUsuarios.aliasUsuario AS              "
               + "                              NOMBREUSUARIO,       "
               + "         tmpDET.idLocal,                           "
               + "         tmpDET.idTipoOrden,                       "
               + "         tmpDET.idOrden,                           "
               + "         tmpDET.item,                              "
               + "         tmpDET.cantidad,                          "
               + "         tmpDET.referenciaCliente,                 "
               + "         tmpDET.numeroOrden,                       "
               + "         tmpDET.idFicha,                           "
               + "         tmpDET.ordenCompra,                       "
               + "         tmpDET.fechaOrden,                        "
               + "         tmpDET.pesoPedido,                        "
               + "         tmpDET.pesoTerminado,                     "
               + "         tmpDET.pesoRetal,                         "
               + "         tmpDET.cantidadFacturada,                 "
               + "         tmpDET.cantidadTerminada,                 "
               + "         tmpDET.cantidadEntregada                  "
               + "  FROM   tblTerceros                               "
               + "  INNER JOIN tblAgendaLogVisitas                   "
               + "  ON tblTerceros.idCliente         =               "
               + "              tblAgendaLogVisitas.idCliente        "
               + "  INNER JOIN ctrlUsuarios                          "
               + "  ON tblAgendaLogVisitas.IDUSUARIO =               "
               + "                     ctrlUsuarios.IDUSUARIO        "
               + "  INNER JOIN                                       "
               + "  (SELECT tblDctosOrdenes.idLocal,                 "
               + "         tblDctosOrdenes.idTipoOrden,              "
               + "         tblDctosOrdenes.idOrden,                  "
               + "         tblDctosOrdenesDetalle.item,              "
               + "         tblDctosOrdenesDetalle.cantidad,          "
               + "         tblDctosOrdenesDetalle.referenciaCliente, "
               + "         tblDctosOrdenesDetalle.fechaEntrega,      "
               + "         tblDctosOrdenes.idLog,                    "
               + "         tblDctosOrdenes.numeroOrden,              "
               + "         tblDctosOrdenes.idFicha,                  "
               + "         tblDctosOrdenes.idCliente,                "
               + "         tblDctosOrdenes.idUsuario,                "
               + "         tblDctosOrdenes.ordenCompra,              "
               + "         tblDctosOrdenes.fechaOrden,               "
               + "        tblDctosOrdenesDetalle.idEstadoRefOriginal,"
               + "        tblDctosOrdenesDetalle.pesoPedido,         "
               + "        tblDctosOrdenesDetalle.pesoTerminado,      "
               + "        tblDctosOrdenesDetalle.pesoRetal,          "
               + "        tblDctosOrdenesDetalle.cantidadFacturada,  "
               + "        tblDctosOrdenesDetalle.cantidadTerminada,  "
               + "        tblDctosOrdenesDetalle.cantidadEntregada   "
               + "   FROM       tblDctosOrdenes                      "
               + "   INNER JOIN tblDctosOrdenesDetalle               "
               + "   ON tblDctosOrdenes.IDLOCAL      =               "
               + "                   tblDctosOrdenesDetalle.IDLOCAL  "
               + "   AND tblDctosOrdenes.IDTIPOORDEN =               "
               + "               tblDctosOrdenesDetalle.IDTIPOORDEN  "
               + "   AND tblDctosOrdenes.IDORDEN     =               "
               + "                   tblDctosOrdenesDetalle.IDORDEN) "
               + "                                         AS tmpDET "
               + "  ON tmpDET.idLog = tblAgendaLogVisitas.idLog      "
               + "  WHERE tblAgendaLogVisitas.IDLOG IN (             "
               + "     SELECT tblAgendaLogVisitas.IDLOG              "
               + "     FROM   tblAgendaLogVisitas                    "
               + "     INNER JOIN tblDctosOrdenes                    "
               + "     ON tblAgendaLogVisitas.IDLOG     =            "
               + "                      tblDctosOrdenes.IDLOG        "
               + " AND tmpDET.cantidadEntregada         > 0          "
               + "  AND tmpDET.pesoPedido               > 0          "
               + "  AND tmpDET.idEstadoRefOriginal     != 9          "
               + "  AND   tblDctosOrdenes.idLocal       =            "
               + getIdLocal() + "                                    "
               + "  AND   tblDctosOrdenes.idTipoOrden   =            "
               + getIdTipoOrden() + "                                "
               + "  AND   tblDctosOrdenes.numeroOrden  != 0          "
               + "  AND tblTerceros.idTipoTercero       =            "
               + xIdTipoTercero + " )                                "
               + "  ORDER BY tblTerceros.nombreTercero,              "
               + "           tmpDET.numeroOrden,                     "
               + "           tmpDET.fechaEntrega                     ";

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
                fachadaBean = new FachadaColaboraDctoOrdenBean();

                //
                fachadaBean.setIdLog(rs.getInt("idLog"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setIdUsuario(rs.getDouble("idUsuario"));
                fachadaBean.setFechaEntrega(rs.getString("fechaEntrega"));
                fachadaBean.setNombreTercero(rs.getString("nombreTercero"));
                fachadaBean.setDireccionDespacho(
                                              rs.getString("direccionTercero"));
                fachadaBean.setTelefonoFijo(rs.getString("telefonoFijo"));
                fachadaBean.setNombreEmpresa(rs.getString("nombreEmpresa"));
                fachadaBean.setCiudadTercero(rs.getString("ciudadTercero"));
                fachadaBean.setNombreUsuario(rs.getString("NOMBREUSUARIO"));
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setIdOrden(rs.getInt("idOrden"));
                fachadaBean.setItem(rs.getInt("item"));
                fachadaBean.setCantidad(rs.getDouble("cantidad"));
                fachadaBean.setReferenciaCliente(
                                             rs.getString("referenciaCliente"));
                fachadaBean.setNumeroOrden(rs.getInt("numeroOrden"));
                fachadaBean.setIdFicha(rs.getInt("idFicha"));
                fachadaBean.setOrdenCompra(rs.getString("ordenCompra"));
                fachadaBean.setFechaOrden(rs.getString("fechaOrden"));
                fachadaBean.setPesoPedido(rs.getDouble("pesoPedido"));
                fachadaBean.setPesoTerminado(rs.getDouble("pesoTerminado"));
                fachadaBean.setPesoRetal(rs.getDouble("pesoRetal"));
                fachadaBean.setCantidadFacturada(
                                             rs.getDouble("cantidadFacturada"));
                fachadaBean.setCantidadTerminada(
                                             rs.getDouble("cantidadTerminada"));


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

    // listaAllAlfabeticoGeneralMercadeo
    public Vector listaAllAlfabeticoGeneralMercadeo(int xIdTipoTercero) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        // Variable de fachada de los datos
        FachadaColaboraDctoOrdenBean fachadaBean;

        Connection connection = null;

        String selectStr =
               "  SELECT tmpDET.idLog,                               "
               + "         tmpDET.idCliente,                         "
               + "         tmpDET.idUsuario,                         "
               + "         tmpDET.fechaEntrega,                      "
               + "         tblTerceros.nombreTercero,                "
               + "         tblTerceros.direccionTercero,             "
               + "         tblTerceros.telefonoFijo,                 "
               + "         tblTerceros.nombreEmpresa,                "
               + "         tblTerceros.ciudadTercero,                "
               + "         ctrlUsuarios.aliasUsuario AS              "
               + "                              NOMBREUSUARIO,       "
               + "         tmpDET.idLocal,                           "
               + "         tmpDET.idTipoOrden,                       "
               + "         tmpDET.idOrden,                           "
               + "         tmpDET.item,                              "
               + "         tmpDET.cantidad,                          "
               + "         tmpDET.referenciaCliente,                 "
               + "         tmpDET.numeroOrden,                       "
               + "         tmpDET.idFicha,                           "
               + "         tmpDET.ordenCompra,                       "
               + "         tmpDET.fechaOrden,                        "
               + "         tmpDET.pesoPedido,                        "
               + "         tmpDET.pesoTerminado,                     "
               + "         tmpDET.pesoRetal,                         "
               + "         tmpDET.cantidadFacturada,                 "
               + "         tmpDET.cantidadTerminada                  "
               + "  FROM   tblTerceros                               "
               + "  INNER JOIN tblAgendaLogVisitas                   "
               + "  ON tblTerceros.idCliente         =               "
               + "              tblAgendaLogVisitas.idCliente        "
               + "  INNER JOIN ctrlUsuarios                          "
               + "  ON tblAgendaLogVisitas.IDUSUARIO =               "
               + "                     ctrlUsuarios.IDUSUARIO        "
               + "  INNER JOIN                                       "
               + "  (SELECT tblDctosOrdenes.idLocal,                 "
               + "         tblDctosOrdenes.idTipoOrden,              "
               + "         tblDctosOrdenes.idOrden,                  "
               + "         tblDctosOrdenesDetalle.item,              "
               + "         tblDctosOrdenesDetalle.cantidad,          "
               + "         tblDctosOrdenesDetalle.referenciaCliente, "
               + "         tblDctosOrdenesDetalle.fechaEntrega,      "
               + "         tblDctosOrdenes.idLog,                    "
               + "         tblDctosOrdenes.numeroOrden,              "
               + "         tblDctosOrdenes.idFicha,                  "
               + "         tblDctosOrdenes.idCliente,                "
               + "         tblDctosOrdenes.idUsuario,                "
               + "         tblDctosOrdenes.ordenCompra,              "
               + "         tblDctosOrdenes.fechaOrden,               "
               + "        tblDctosOrdenesDetalle.idEstadoRefOriginal,"
               + "        tblDctosOrdenesDetalle.pesoPedido,         "
               + "        tblDctosOrdenesDetalle.pesoTerminado,      "
               + "        tblDctosOrdenesDetalle.pesoRetal,          "
               + "        tblDctosOrdenesDetalle.cantidadFacturada,  "
               + "        tblDctosOrdenesDetalle.cantidadTerminada   "
               + "   FROM       tblDctosOrdenes                      "
               + "   INNER JOIN tblDctosOrdenesDetalle               "
               + "   ON tblDctosOrdenes.IDLOCAL      =               "
               + "                   tblDctosOrdenesDetalle.IDLOCAL  "
               + "   AND tblDctosOrdenes.IDTIPOORDEN =               "
               + "               tblDctosOrdenesDetalle.IDTIPOORDEN  "
               + "   AND tblDctosOrdenes.IDORDEN     =               "
               + "                   tblDctosOrdenesDetalle.IDORDEN) "
               + "                                         AS tmpDET "
               + "  ON tmpDET.idLog = tblAgendaLogVisitas.idLog      "
               + "  WHERE tblAgendaLogVisitas.IDLOG IN (             "
               + "     SELECT tblAgendaLogVisitas.IDLOG              "
               + "     FROM   tblAgendaLogVisitas                    "
               + "     INNER JOIN tblDctosOrdenes                    "
               + "     ON tblAgendaLogVisitas.IDLOG     =            "
               + "                      tblDctosOrdenes.IDLOG        "
               + " AND tmpDET.cantidad                  >            "
               + "                          tmpDET.cantidadFacturada "
               + "  AND   tblDctosOrdenes.idLocal       =            "
               + getIdLocal() + "                                    "
               + "  AND   tblDctosOrdenes.idTipoOrden   =            "
               + getIdTipoOrden() + "                                "
               + "  AND   tblDctosOrdenes.numeroOrden  != 0          "
               + "  AND tmpDET.idEstadoRefOriginal     != 9          "
               + "  AND tblTerceros.idTipoTercero       =            "
               + xIdTipoTercero + " )                                "
               + "  ORDER BY tblTerceros.nombreTercero,              "
               + "           tmpDET.numeroOrden,                     "
               + "           tmpDET.fechaEntrega                     ";

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
                fachadaBean = new FachadaColaboraDctoOrdenBean();

                //
                fachadaBean.setIdLog(rs.getInt("idLog"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setIdUsuario(rs.getDouble("idUsuario"));
                fachadaBean.setFechaEntrega(rs.getString("fechaEntrega"));
                fachadaBean.setNombreTercero(rs.getString("nombreTercero"));
                fachadaBean.setDireccionDespacho(
                                              rs.getString("direccionTercero"));
                fachadaBean.setTelefonoFijo(rs.getString("telefonoFijo"));
                fachadaBean.setNombreEmpresa(rs.getString("nombreEmpresa"));
                fachadaBean.setCiudadTercero(rs.getString("ciudadTercero"));
                fachadaBean.setNombreUsuario(rs.getString("NOMBREUSUARIO"));
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setIdOrden(rs.getInt("idOrden"));
                fachadaBean.setItem(rs.getInt("item"));
                fachadaBean.setCantidad(rs.getDouble("cantidad"));
                fachadaBean.setReferenciaCliente(
                                             rs.getString("referenciaCliente"));
                fachadaBean.setNumeroOrden(rs.getInt("numeroOrden"));
                fachadaBean.setIdFicha(rs.getInt("idFicha"));
                fachadaBean.setOrdenCompra(rs.getString("ordenCompra"));
                fachadaBean.setFechaOrden(rs.getString("fechaOrden"));
                fachadaBean.setPesoPedido(rs.getDouble("pesoPedido"));
                fachadaBean.setPesoTerminado(rs.getDouble("pesoTerminado"));
                fachadaBean.setPesoRetal(rs.getDouble("pesoRetal"));
                fachadaBean.setCantidadFacturada(
                                             rs.getDouble("cantidadFacturada"));
                fachadaBean.setCantidadTerminada(
                                             rs.getDouble("cantidadTerminada"));


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

    // listaAllHistoricoGeneral
    public Vector listaAllHistoricoGeneral(int xIdTipoTercero) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        // Variable de fachada de los datos
        FachadaColaboraDctoOrdenBean fachadaBean;

        Connection connection = null;

        String selectStr =
               "  SELECT tmpDET.idLog,                               "
               + "         tmpDET.idCliente,                         "
               + "         tmpDET.idUsuario,                         "
               + "         tmpDET.fechaEntrega,                      "
               + "         tblTerceros.nombreTercero,                "
               + "         tblTerceros.direccionTercero,             "
               + "         tblTerceros.telefonoFijo,                 "
               + "         tblTerceros.nombreEmpresa,                "
               + "         tblTerceros.ciudadTercero,                "
               + "         ctrlUsuarios.aliasUsuario AS              "
               + "                              NOMBREUSUARIO,       "
               + "         tmpDET.idLocal,                           "
               + "         tmpDET.idTipoOrden,                       "
               + "         tmpDET.idOrden,                           "
               + "         tmpDET.item,                              "
               + "         tmpDET.cantidad,                          "
               + "         tmpDET.referenciaCliente,                 "
               + "         tmpDET.numeroOrden,                       "
               + "         tmpDET.idFicha,                           "
               + "         tmpDET.ordenCompra,                       "
               + "         tmpDET.fechaOrden,                        "
               + "         tmpDET.pesoPedido,                        "
               + "         tmpDET.pesoTerminado,                     "
               + "         tmpDET.pesoRetal,                         "
               + "         tmpDET.cantidadFacturada,                 "
               + "         tmpDET.cantidadTerminada                  "
               + "  FROM   tblTerceros                               "
               + "  INNER JOIN tblAgendaLogVisitas                   "
               + "  ON tblTerceros.idCliente         =               "
               + "              tblAgendaLogVisitas.idCliente        "
               + "  INNER JOIN ctrlUsuarios                          "
               + "  ON tblAgendaLogVisitas.IDUSUARIO =               "
               + "                     ctrlUsuarios.IDUSUARIO        "
               + "  INNER JOIN                                       "
               + "  (SELECT tblDctosOrdenes.idLocal,                 "
               + "         tblDctosOrdenes.idTipoOrden,              "
               + "         tblDctosOrdenes.idOrden,                  "
               + "         tblDctosOrdenesDetalle.item,              "
               + "         tblDctosOrdenesDetalle.cantidad,          "
               + "         tblDctosOrdenesDetalle.referenciaCliente, "
               + "         tblDctosOrdenesDetalle.fechaEntrega,      "
               + "         tblDctosOrdenes.idLog,                    "
               + "         tblDctosOrdenes.numeroOrden,              "
               + "         tblDctosOrdenes.idFicha,                  "
               + "         tblDctosOrdenes.idCliente,                "
               + "         tblDctosOrdenes.idUsuario,                "
               + "         tblDctosOrdenes.ordenCompra,              "
               + "         tblDctosOrdenes.fechaOrden,               "
               + "        tblDctosOrdenesDetalle.idEstadoRefOriginal,"
               + "        tblDctosOrdenesDetalle.pesoPedido,         "
               + "        tblDctosOrdenesDetalle.pesoTerminado,      "
               + "        tblDctosOrdenesDetalle.pesoRetal,          "
               + "        tblDctosOrdenesDetalle.cantidadFacturada,  "
               + "        tblDctosOrdenesDetalle.cantidadTerminada   "
               + "   FROM       tblDctosOrdenes                      "
               + "   INNER JOIN tblDctosOrdenesDetalle               "
               + "   ON tblDctosOrdenes.IDLOCAL      =               "
               + "                   tblDctosOrdenesDetalle.IDLOCAL  "
               + "   AND tblDctosOrdenes.IDTIPOORDEN =               "
               + "               tblDctosOrdenesDetalle.IDTIPOORDEN  "
               + "   AND tblDctosOrdenes.IDORDEN     =               "
               + "                   tblDctosOrdenesDetalle.IDORDEN) "
               + "                                         AS tmpDET "
               + "  ON tmpDET.idLog = tblAgendaLogVisitas.idLog      "
               + "  WHERE tblAgendaLogVisitas.IDLOG IN (             "
               + "     SELECT tblAgendaLogVisitas.IDLOG              "
               + "     FROM   tblAgendaLogVisitas                    "
               + "     INNER JOIN tblDctosOrdenes                    "
               + "     ON tblAgendaLogVisitas.IDLOG     =            "
               + "                      tblDctosOrdenes.IDLOG        "
               + "  AND   tblDctosOrdenes.idLocal       =            "
               + getIdLocal() + "                                    "
               + "  AND   tblDctosOrdenes.idTipoOrden   =            "
               + getIdTipoOrden() + "                                "
               + "  AND   tblDctosOrdenes.numeroOrden  != 0          "
               + "  AND tblTerceros.idTipoTercero       =            "
               + xIdTipoTercero + " )                                "
               + "  ORDER BY tblTerceros.nombreTercero,              "
               + "           tmpDET.numeroOrden,                     "
               + "           tmpDET.fechaEntrega                     ";

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
                fachadaBean = new FachadaColaboraDctoOrdenBean();

                //
                fachadaBean.setIdLog(rs.getInt("idLog"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setIdUsuario(rs.getDouble("idUsuario"));
                fachadaBean.setFechaEntrega(rs.getString("fechaEntrega"));
                fachadaBean.setNombreTercero(rs.getString("nombreTercero"));
                fachadaBean.setDireccionDespacho(
                                              rs.getString("direccionTercero"));
                fachadaBean.setTelefonoFijo(rs.getString("telefonoFijo"));
                fachadaBean.setNombreEmpresa(rs.getString("nombreEmpresa"));
                fachadaBean.setCiudadTercero(rs.getString("ciudadTercero"));
                fachadaBean.setNombreUsuario(rs.getString("NOMBREUSUARIO"));
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setIdOrden(rs.getInt("idOrden"));
                fachadaBean.setItem(rs.getInt("item"));
                fachadaBean.setCantidad(rs.getDouble("cantidad"));
                fachadaBean.setReferenciaCliente(
                                             rs.getString("referenciaCliente"));
                fachadaBean.setNumeroOrden(rs.getInt("numeroOrden"));
                fachadaBean.setIdFicha(rs.getInt("idFicha"));
                fachadaBean.setOrdenCompra(rs.getString("ordenCompra"));
                fachadaBean.setFechaOrden(rs.getString("fechaOrden"));
                fachadaBean.setPesoPedido(rs.getDouble("pesoPedido"));
                fachadaBean.setPesoTerminado(rs.getDouble("pesoTerminado"));
                fachadaBean.setPesoRetal(rs.getDouble("pesoRetal"));
                fachadaBean.setCantidadFacturada(
                                             rs.getDouble("cantidadFacturada"));
                fachadaBean.setCantidadTerminada(
                                             rs.getDouble("cantidadTerminada"));


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

    // listaUnHistoricoGeneral
    public Vector listaUnHistoricoGeneral(int xIdTipoTercero) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        // Variable de fachada de los datos
        FachadaColaboraDctoOrdenBean fachadaBean;

        Connection connection = null;

        String selectStr =
               "  SELECT tmpDET.idLog,                               "
               + "         tmpDET.idCliente,                         "
               + "         tmpDET.idUsuario,                         "
               + "         tmpDET.fechaEntrega,                      "
               + "         tblTerceros.nombreTercero,                "
               + "         tblTerceros.direccionTercero,             "
               + "         tblTerceros.telefonoFijo,                 "
               + "         tblTerceros.nombreEmpresa,                "
               + "         tblTerceros.ciudadTercero,                "
               + "         ctrlUsuarios.aliasUsuario AS              "
               + "                              NOMBREUSUARIO,       "
               + "         tmpDET.idLocal,                           "
               + "         tmpDET.idTipoOrden,                       "
               + "         tmpDET.idOrden,                           "
               + "         tmpDET.item,                              "
               + "         tmpDET.cantidad,                          "
               + "         tmpDET.referenciaCliente,                 "
               + "         tmpDET.numeroOrden,                       "
               + "         tmpDET.idFicha,                           "
               + "         tmpDET.ordenCompra,                       "
               + "         tmpDET.fechaOrden,                        "
               + "         tmpDET.pesoPedido,                        "
               + "         tmpDET.pesoTerminado,                     "
               + "         tmpDET.pesoRetal,                         "
               + "         tmpDET.cantidadFacturada,                 "
               + "         tmpDET.cantidadTerminada                  "
               + "  FROM   tblTerceros                               "
               + "  INNER JOIN tblAgendaLogVisitas                   "
               + "  ON tblTerceros.idCliente         =               "
               + "              tblAgendaLogVisitas.idCliente        "
               + "  INNER JOIN ctrlUsuarios                          "
               + "  ON tblAgendaLogVisitas.IDUSUARIO =               "
               + "                     ctrlUsuarios.IDUSUARIO        "
               + "  INNER JOIN                                       "
               + "  (SELECT tblDctosOrdenes.idLocal,                 "
               + "         tblDctosOrdenes.idTipoOrden,              "
               + "         tblDctosOrdenes.idOrden,                  "
               + "         tblDctosOrdenesDetalle.item,              "
               + "         tblDctosOrdenesDetalle.cantidad,          "
               + "         tblDctosOrdenesDetalle.referenciaCliente, "
               + "         tblDctosOrdenesDetalle.fechaEntrega,      "
               + "         tblDctosOrdenes.idLog,                    "
               + "         tblDctosOrdenes.numeroOrden,              "
               + "         tblDctosOrdenes.idFicha,                  "
               + "         tblDctosOrdenes.idCliente,                "
               + "         tblDctosOrdenes.idUsuario,                "
               + "         tblDctosOrdenes.ordenCompra,              "
               + "         tblDctosOrdenes.fechaOrden,               "
               + "        tblDctosOrdenesDetalle.idEstadoRefOriginal,"
               + "        tblDctosOrdenesDetalle.pesoPedido,         "
               + "        tblDctosOrdenesDetalle.pesoTerminado,      "
               + "        tblDctosOrdenesDetalle.pesoRetal,          "
               + "        tblDctosOrdenesDetalle.cantidadFacturada,  "
               + "        tblDctosOrdenesDetalle.cantidadTerminada   "
               + "   FROM       tblDctosOrdenes                      "
               + "   INNER JOIN tblDctosOrdenesDetalle               "
               + "   ON tblDctosOrdenes.IDLOCAL      =               "
               + "                   tblDctosOrdenesDetalle.IDLOCAL  "
               + "   AND tblDctosOrdenes.IDTIPOORDEN =               "
               + "               tblDctosOrdenesDetalle.IDTIPOORDEN  "
               + "   AND tblDctosOrdenes.IDORDEN     =               "
               + "                   tblDctosOrdenesDetalle.IDORDEN) "
               + "                                         AS tmpDET "
               + "  ON tmpDET.idLog = tblAgendaLogVisitas.idLog      "
               + "  WHERE tblAgendaLogVisitas.IDLOG IN (             "
               + "     SELECT tblAgendaLogVisitas.IDLOG              "
               + "     FROM   tblAgendaLogVisitas                    "
               + "     INNER JOIN tblDctosOrdenes                    "
               + "     ON tblAgendaLogVisitas.IDLOG     =            "
               + "                      tblDctosOrdenes.IDLOG        "
               + "  AND   tblDctosOrdenes.idLocal       =            "
               + getIdLocal() + "                                    "
               + "  AND   tblDctosOrdenes.idTipoOrden   =            "
               + getIdTipoOrden() + "                                "
               + "  AND   tblDctosOrdenes.numeroOrden  != 0          "
               + "  AND   tblDctosOrdenes.idCliente     =           '"
               + getIdCliente() + "'                                 "
               + "  AND tblTerceros.idTipoTercero       =            "
               + xIdTipoTercero + " )                                "
               + "  ORDER BY tblTerceros.nombreTercero,              "
               + "           tmpDET.numeroOrden,                     "
               + "           tmpDET.fechaEntrega                     ";

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
                fachadaBean = new FachadaColaboraDctoOrdenBean();

                //
                fachadaBean.setIdLog(rs.getInt("idLog"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setIdUsuario(rs.getDouble("idUsuario"));
                fachadaBean.setFechaEntrega(rs.getString("fechaEntrega"));
                fachadaBean.setNombreTercero(rs.getString("nombreTercero"));
                fachadaBean.setDireccionDespacho(
                                              rs.getString("direccionTercero"));
                fachadaBean.setTelefonoFijo(rs.getString("telefonoFijo"));
                fachadaBean.setNombreEmpresa(rs.getString("nombreEmpresa"));
                fachadaBean.setCiudadTercero(rs.getString("ciudadTercero"));
                fachadaBean.setNombreUsuario(rs.getString("NOMBREUSUARIO"));
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setIdOrden(rs.getInt("idOrden"));
                fachadaBean.setItem(rs.getInt("item"));
                fachadaBean.setCantidad(rs.getDouble("cantidad"));
                fachadaBean.setReferenciaCliente(
                                             rs.getString("referenciaCliente"));
                fachadaBean.setNumeroOrden(rs.getInt("numeroOrden"));
                fachadaBean.setIdFicha(rs.getInt("idFicha"));
                fachadaBean.setOrdenCompra(rs.getString("ordenCompra"));
                fachadaBean.setFechaOrden(rs.getString("fechaOrden"));
                fachadaBean.setPesoPedido(rs.getDouble("pesoPedido"));
                fachadaBean.setPesoTerminado(rs.getDouble("pesoTerminado"));
                fachadaBean.setPesoRetal(rs.getDouble("pesoRetal"));
                fachadaBean.setCantidadFacturada(
                                             rs.getDouble("cantidadFacturada"));
                fachadaBean.setCantidadTerminada(
                                             rs.getDouble("cantidadTerminada"));


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

    // listaUnAlfabeticoGeneralMercadeo
    public Vector listaUnAlfabeticoGeneralMercadeo(int xIdTipoTercero) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        // Variable de fachada de los datos
        FachadaColaboraDctoOrdenBean fachadaBean;

        Connection connection = null;

        String selectStr =
               "  SELECT tmpDET.idLog,                               "
               + "         tmpDET.idCliente,                         "
               + "         tmpDET.idUsuario,                         "
               + "         tmpDET.fechaEntrega,                      "
               + "         tblTerceros.nombreTercero,                "
               + "         tblTerceros.direccionTercero,             "
               + "         tblTerceros.telefonoFijo,                 "
               + "         tblTerceros.nombreEmpresa,                "
               + "         tblTerceros.ciudadTercero,                "
               + "         ctrlUsuarios.aliasUsuario AS              "
               + "                              NOMBREUSUARIO,       "
               + "         tmpDET.idLocal,                           "
               + "         tmpDET.idTipoOrden,                       "
               + "         tmpDET.idOrden,                           "
               + "         tmpDET.item,                              "
               + "         tmpDET.cantidad,                          "
               + "         tmpDET.referenciaCliente,                 "
               + "         tmpDET.numeroOrden,                       "
               + "         tmpDET.idFicha,                           "
               + "         tmpDET.ordenCompra,                       "
               + "         tmpDET.fechaOrden,                        "
               + "         tmpDET.pesoPedido,                        "
               + "         tmpDET.pesoTerminado,                     "
               + "         tmpDET.pesoRetal,                         "
               + "         tmpDET.cantidadFacturada,                 "
               + "         tmpDET.cantidadTerminada                  "
               + "  FROM   tblTerceros                               "
               + "  INNER JOIN tblAgendaLogVisitas                   "
               + "  ON tblTerceros.idCliente         =               "
               + "              tblAgendaLogVisitas.idCliente        "
               + "  INNER JOIN ctrlUsuarios                          "
               + "  ON tblAgendaLogVisitas.IDUSUARIO =               "
               + "                     ctrlUsuarios.IDUSUARIO        "
               + "  INNER JOIN                                       "
               + "  (SELECT tblDctosOrdenes.idLocal,                 "
               + "         tblDctosOrdenes.idTipoOrden,              "
               + "         tblDctosOrdenes.idOrden,                  "
               + "         tblDctosOrdenesDetalle.item,              "
               + "         tblDctosOrdenesDetalle.cantidad,          "
               + "         tblDctosOrdenesDetalle.referenciaCliente, "
               + "         tblDctosOrdenesDetalle.fechaEntrega,      "
               + "         tblDctosOrdenes.idLog,                    "
               + "         tblDctosOrdenes.numeroOrden,              "
               + "         tblDctosOrdenes.idFicha,                  "
               + "         tblDctosOrdenes.idCliente,                "
               + "         tblDctosOrdenes.idUsuario,                "
               + "         tblDctosOrdenes.ordenCompra,              "
               + "         tblDctosOrdenes.fechaOrden,               "
               + "        tblDctosOrdenesDetalle.idEstadoRefOriginal,"
               + "        tblDctosOrdenesDetalle.pesoPedido,         "
               + "        tblDctosOrdenesDetalle.pesoTerminado,      "
               + "        tblDctosOrdenesDetalle.pesoRetal,          "
               + "        tblDctosOrdenesDetalle.cantidadFacturada,  "
               + "        tblDctosOrdenesDetalle.cantidadTerminada   "
               + "   FROM       tblDctosOrdenes                      "
               + "   INNER JOIN tblDctosOrdenesDetalle               "
               + "   ON tblDctosOrdenes.IDLOCAL      =               "
               + "                   tblDctosOrdenesDetalle.IDLOCAL  "
               + "   AND tblDctosOrdenes.IDTIPOORDEN =               "
               + "               tblDctosOrdenesDetalle.IDTIPOORDEN  "
               + "   AND tblDctosOrdenes.IDORDEN     =               "
               + "                   tblDctosOrdenesDetalle.IDORDEN) "
               + "                                         AS tmpDET "
               + "  ON tmpDET.idLog = tblAgendaLogVisitas.idLog      "
               + "  WHERE tblAgendaLogVisitas.IDLOG IN (             "
               + "     SELECT tblAgendaLogVisitas.IDLOG              "
               + "     FROM   tblAgendaLogVisitas                    "
               + "     INNER JOIN tblDctosOrdenes                    "
               + "     ON tblAgendaLogVisitas.IDLOG     =            "
               + "                      tblDctosOrdenes.IDLOG        "
               + " AND tmpDET.cantidad                  >            "
               + "                          tmpDET.cantidadFacturada "
               + "  AND   tblDctosOrdenes.idLocal       =            "
               + getIdLocal() + "                                    "
               + "  AND   tblDctosOrdenes.idTipoOrden   =            "
               + getIdTipoOrden() + "                                "
               + "  AND   tblDctosOrdenes.numeroOrden  != 0          "
               + "  AND tmpDET.idEstadoRefOriginal     != 9          "
               + "  AND   tblDctosOrdenes.idCliente     =           '"
               + getIdCliente() + "'                                 "
               + "  AND tblTerceros.idTipoTercero       =            "
               + xIdTipoTercero + " )                                "
               + "  ORDER BY tblTerceros.nombreTercero,              "
               + "           tmpDET.numeroOrden,                     "
               + "           tmpDET.fechaEntrega                     ";


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
                fachadaBean = new FachadaColaboraDctoOrdenBean();

                //
                fachadaBean.setIdLog(rs.getInt("idLog"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setIdUsuario(rs.getDouble("idUsuario"));
                fachadaBean.setFechaEntrega(rs.getString("fechaEntrega"));
                fachadaBean.setNombreTercero(rs.getString("nombreTercero"));
                fachadaBean.setDireccionDespacho(
                                              rs.getString("direccionTercero"));
                fachadaBean.setTelefonoFijo(rs.getString("telefonoFijo"));
                fachadaBean.setNombreEmpresa(rs.getString("nombreEmpresa"));
                fachadaBean.setCiudadTercero(rs.getString("ciudadTercero"));
                fachadaBean.setNombreUsuario(rs.getString("NOMBREUSUARIO"));
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setIdOrden(rs.getInt("idOrden"));
                fachadaBean.setItem(rs.getInt("item"));
                fachadaBean.setCantidad(rs.getDouble("cantidad"));
                fachadaBean.setReferenciaCliente(
                                             rs.getString("referenciaCliente"));
                fachadaBean.setNumeroOrden(rs.getInt("numeroOrden"));
                fachadaBean.setIdFicha(rs.getInt("idFicha"));
                fachadaBean.setOrdenCompra(rs.getString("ordenCompra"));
                fachadaBean.setFechaOrden(rs.getString("fechaOrden"));
                fachadaBean.setPesoPedido(rs.getDouble("pesoPedido"));
                fachadaBean.setPesoTerminado(rs.getDouble("pesoTerminado"));
                fachadaBean.setPesoRetal(rs.getDouble("pesoRetal"));
                fachadaBean.setCantidadFacturada(
                                             rs.getDouble("cantidadFacturada"));
                fachadaBean.setCantidadTerminada(
                                             rs.getDouble("cantidadTerminada"));


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

    // listaAllAlfabeticoRemisionSinFactura
    public Vector listaUnAlfabeticoRemisionSinFactura(int xIdTipoTercero) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        // Variable de fachada de los datos
        FachadaColaboraDctoOrdenBean fachadaBean;

        Connection connection = null;

        String selectStr =
               "  SELECT tmpDET.idLog,                               "
               + "         tmpDET.idCliente,                         "
               + "         tmpDET.idUsuario,                         "
               + "         tmpDET.fechaEntrega,                      "
               + "         tblTerceros.nombreTercero,                "
               + "         tblTerceros.direccionTercero,             "
               + "         tblTerceros.telefonoFijo,                 "
               + "         tblTerceros.nombreEmpresa,                "
               + "         tblTerceros.ciudadTercero,                "
               + "         ctrlUsuarios.aliasUsuario AS              "
               + "                              NOMBREUSUARIO,       "
               + "         tmpDET.idLocal,                           "
               + "         tmpDET.idTipoOrden,                       "
               + "         tmpDET.idOrden,                           "
               + "         tmpDET.item,                              "
               + "         tmpDET.cantidad,                          "
               + "         tmpDET.referenciaCliente,                 "
               + "         tmpDET.numeroOrden,                       "
               + "         tmpDET.idFicha,                           "
               + "         tmpDET.ordenCompra,                       "
               + "         tmpDET.fechaOrden,                        "
               + "         tmpDET.pesoPedido,                        "
               + "         tmpDET.pesoTerminado,                     "
               + "         tmpDET.pesoRetal,                         "
               + "         tmpDET.cantidadFacturada,                 "
               + "         tmpDET.cantidadTerminada,                 "
               + "         tmpDET.cantidadEntregada                  "
               + "  FROM   tblTerceros                               "
               + "  INNER JOIN tblAgendaLogVisitas                   "
               + "  ON tblTerceros.idCliente         =               "
               + "              tblAgendaLogVisitas.idCliente        "
               + "  INNER JOIN ctrlUsuarios                          "
               + "  ON tblAgendaLogVisitas.IDUSUARIO =               "
               + "                     ctrlUsuarios.IDUSUARIO        "
               + "  INNER JOIN                                       "
               + "  (SELECT tblDctosOrdenes.idLocal,                 "
               + "         tblDctosOrdenes.idTipoOrden,              "
               + "         tblDctosOrdenes.idOrden,                  "
               + "         tblDctosOrdenesDetalle.item,              "
               + "         tblDctosOrdenesDetalle.cantidad,          "
               + "         tblDctosOrdenesDetalle.referenciaCliente, "
               + "         tblDctosOrdenesDetalle.fechaEntrega,      "
               + "         tblDctosOrdenes.idLog,                    "
               + "         tblDctosOrdenes.numeroOrden,              "
               + "         tblDctosOrdenes.idFicha,                  "
               + "         tblDctosOrdenes.idCliente,                "
               + "         tblDctosOrdenes.idUsuario,                "
               + "         tblDctosOrdenes.ordenCompra,              "
               + "         tblDctosOrdenes.fechaOrden,               "
               + "        tblDctosOrdenesDetalle.idEstadoRefOriginal,"
               + "        tblDctosOrdenesDetalle.pesoPedido,         "
               + "        tblDctosOrdenesDetalle.pesoTerminado,      "
               + "        tblDctosOrdenesDetalle.pesoRetal,          "
               + "        tblDctosOrdenesDetalle.cantidadFacturada,  "
               + "        tblDctosOrdenesDetalle.cantidadTerminada,  "
               + "        tblDctosOrdenesDetalle.cantidadEntregada   "
               + "   FROM       tblDctosOrdenes                      "
               + "   INNER JOIN tblDctosOrdenesDetalle               "
               + "   ON tblDctosOrdenes.IDLOCAL      =               "
               + "                   tblDctosOrdenesDetalle.IDLOCAL  "
               + "   AND tblDctosOrdenes.IDTIPOORDEN =               "
               + "               tblDctosOrdenesDetalle.IDTIPOORDEN  "
               + "   AND tblDctosOrdenes.IDORDEN     =               "
               + "                   tblDctosOrdenesDetalle.IDORDEN) "
               + "                                         AS tmpDET "
               + "  ON tmpDET.idLog = tblAgendaLogVisitas.idLog      "
               + "  WHERE tblAgendaLogVisitas.IDLOG IN (             "
               + "     SELECT tblAgendaLogVisitas.IDLOG              "
               + "     FROM   tblAgendaLogVisitas                    "
               + "     INNER JOIN tblDctosOrdenes                    "
               + "     ON tblAgendaLogVisitas.IDLOG     =            "
               + "                      tblDctosOrdenes.IDLOG        "
               + " AND tmpDET.cantidadEntregada         > 0          "
               + "  AND tmpDET.pesoPedido               > 0          "
               + "  AND tmpDET.idEstadoRefOriginal     != 9          "
               + "  AND   tblDctosOrdenes.idLocal       =            "
               + getIdLocal() + "                                    "
               + "  AND   tblDctosOrdenes.idTipoOrden   =            "
               + getIdTipoOrden() + "                                "
               + "  AND   tblDctosOrdenes.numeroOrden  != 0          "
               + "  AND   tblDctosOrdenes.idCliente     =           '"
               + getIdCliente() + "'                                 "
               + "  AND tblTerceros.idTipoTercero       =            "
               + xIdTipoTercero + " )                                "
               + "  ORDER BY tblTerceros.nombreTercero,              "
               + "           tmpDET.numeroOrden,                     "
               + "           tmpDET.fechaEntrega                     ";

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
                fachadaBean = new FachadaColaboraDctoOrdenBean();

                //
                fachadaBean.setIdLog(rs.getInt("idLog"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setIdUsuario(rs.getDouble("idUsuario"));
                fachadaBean.setFechaEntrega(rs.getString("fechaEntrega"));
                fachadaBean.setNombreTercero(rs.getString("nombreTercero"));
                fachadaBean.setDireccionDespacho(
                                              rs.getString("direccionTercero"));
                fachadaBean.setTelefonoFijo(rs.getString("telefonoFijo"));
                fachadaBean.setNombreEmpresa(rs.getString("nombreEmpresa"));
                fachadaBean.setCiudadTercero(rs.getString("ciudadTercero"));
                fachadaBean.setNombreUsuario(rs.getString("NOMBREUSUARIO"));
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setIdOrden(rs.getInt("idOrden"));
                fachadaBean.setItem(rs.getInt("item"));
                fachadaBean.setCantidad(rs.getDouble("cantidad"));
                fachadaBean.setReferenciaCliente(
                                             rs.getString("referenciaCliente"));
                fachadaBean.setNumeroOrden(rs.getInt("numeroOrden"));
                fachadaBean.setIdFicha(rs.getInt("idFicha"));
                fachadaBean.setOrdenCompra(rs.getString("ordenCompra"));
                fachadaBean.setFechaOrden(rs.getString("fechaOrden"));
                fachadaBean.setPesoPedido(rs.getDouble("pesoPedido"));
                fachadaBean.setPesoTerminado(rs.getDouble("pesoTerminado"));
                fachadaBean.setPesoRetal(rs.getDouble("pesoRetal"));
                fachadaBean.setCantidadFacturada(
                                             rs.getDouble("cantidadFacturada"));
                fachadaBean.setCantidadTerminada(
                                             rs.getDouble("cantidadTerminada"));


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

    // listaAllAlfabeticoNuevo
    public Vector listaAllAlfabeticoNuevo(int xIdTipoTercero) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        // Variable de fachada de los datos
        FachadaColaboraDctoOrdenBean fachadaBean;

        Connection connection = null;

        String selectStr =
               "  SELECT tmpDET.idLog,                               "
               + "         tmpDET.idCliente,                         "
               + "         tmpDET.idUsuario,                         "
               + "         tmpDET.fechaEntrega,                      "
               + "         tblTerceros.nombreTercero,                "
               + "         tblTerceros.direccionTercero,             "
               + "         tblTerceros.telefonoFijo,                 "
               + "         tblTerceros.nombreEmpresa,                "
               + "         tblTerceros.ciudadTercero,                "
               + "         ctrlUsuarios.aliasUsuario AS              "
               + "                              NOMBREUSUARIO,       "
               + "         tmpDET.idLocal,                           "
               + "         tmpDET.idTipoOrden,                       "
               + "         tmpDET.idOrden,                           "
               + "         tmpDET.item,                              "
               + "         tmpDET.cantidad,                          "
               + "         tmpDET.referenciaCliente,                 "
               + "         tmpDET.numeroOrden,                       "
               + "         tmpDET.idFicha,                           "
               + "         tmpDET.ordenCompra,                       "
               + "         tmpDET.fechaOrden,                        "
               + "         tmpDET.pesoPedido,                        "
               + "         tmpDET.pesoTerminado,                     "
               + "         tmpDET.pesoRetal,                         "
               + "         tmpDET.cantidadFacturada                  "
               + "  FROM   tblTerceros                               "
               + "  INNER JOIN tblAgendaLogVisitas                   "
               + "  ON tblTerceros.idCliente         =               "
               + "              tblAgendaLogVisitas.idCliente        "
               + "  INNER JOIN ctrlUsuarios                          "
               + "  ON tblAgendaLogVisitas.IDUSUARIO =               "
               + "                     ctrlUsuarios.IDUSUARIO        "
               + "  INNER JOIN                                       "
               + "  (SELECT tblDctosOrdenes.idLocal,                 "
               + "         tblDctosOrdenes.idTipoOrden,              "
               + "         tblDctosOrdenes.idOrden,                  "
               + "         tblDctosOrdenesDetalle.item,              "
               + "         tblDctosOrdenesDetalle.cantidad,          "
               + "         tblDctosOrdenesDetalle.referenciaCliente, "
               + "         tblDctosOrdenesDetalle.fechaEntrega,      "
               + "         tblDctosOrdenes.idLog,                    "
               + "         tblDctosOrdenes.numeroOrden,              "
               + "         tblDctosOrdenes.idFicha,                  "
               + "         tblDctosOrdenes.idCliente,                "
               + "         tblDctosOrdenes.idUsuario,                "
               + "         tblDctosOrdenes.ordenCompra,              "
               + "         tblDctosOrdenes.fechaOrden,               "
               + "        tblDctosOrdenesDetalle.idEstadoRefOriginal,"
               + "        tblDctosOrdenesDetalle.pesoPedido,         "
               + "        tblDctosOrdenesDetalle.pesoTerminado,      "
               + "        tblDctosOrdenesDetalle.pesoRetal,          "
               + "        tblDctosOrdenesDetalle.cantidadFacturada   "
               + "   FROM       tblDctosOrdenes                      "
               + "   INNER JOIN tblDctosOrdenesDetalle               "
               + "   ON tblDctosOrdenes.IDLOCAL      =               "
               + "                   tblDctosOrdenesDetalle.IDLOCAL  "
               + "   AND tblDctosOrdenes.IDTIPOORDEN =               "
               + "               tblDctosOrdenesDetalle.IDTIPOORDEN  "
               + "   AND tblDctosOrdenes.IDORDEN     =               "
               + "                   tblDctosOrdenesDetalle.IDORDEN) "
               + "                                         AS tmpDET "
               + "  ON tmpDET.idLog = tblAgendaLogVisitas.idLog      "
               + "  WHERE tblAgendaLogVisitas.IDLOG IN (             "
               + "     SELECT tblAgendaLogVisitas.IDLOG              "
               + "     FROM   tblAgendaLogVisitas                    "
               + "     INNER JOIN tblDctosOrdenes                    "
               + "     ON tblAgendaLogVisitas.IDLOG     =            "
               + "                      tblDctosOrdenes.IDLOG        "
               + "  AND tmpDET.pesoPedido               = 0          "
               + "  AND tmpDET.idEstadoRefOriginal     != 9          "
               + "  AND   tblDctosOrdenes.idLocal       =            "
               + getIdLocal() + "                                    "
               + "  AND   tblDctosOrdenes.idTipoOrden   =            "
               + getIdTipoOrden() + "                                "
               + "  AND   tblDctosOrdenes.numeroOrden  != 0          "
               + "  AND tblTerceros.idTipoTercero       =            "
               + xIdTipoTercero + " )                                "
               + "  ORDER BY tblTerceros.nombreTercero,              "
               + "           tmpDET.numeroOrden,                     "
               + "           tmpDET.fechaEntrega                     ";

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
                fachadaBean = new FachadaColaboraDctoOrdenBean();

                //
                fachadaBean.setIdLog(rs.getInt("idLog"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setIdUsuario(rs.getDouble("idUsuario"));
                fachadaBean.setFechaEntrega(rs.getString("fechaEntrega"));
                fachadaBean.setNombreTercero(rs.getString("nombreTercero"));
                fachadaBean.setDireccionDespacho(
                                              rs.getString("direccionTercero"));
                fachadaBean.setTelefonoFijo(rs.getString("telefonoFijo"));
                fachadaBean.setNombreEmpresa(rs.getString("nombreEmpresa"));
                fachadaBean.setCiudadTercero(rs.getString("ciudadTercero"));
                fachadaBean.setNombreUsuario(rs.getString("NOMBREUSUARIO"));
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setIdOrden(rs.getInt("idOrden"));
                fachadaBean.setItem(rs.getInt("item"));
                fachadaBean.setCantidad(rs.getDouble("cantidad"));
                fachadaBean.setReferenciaCliente(
                                             rs.getString("referenciaCliente"));
                fachadaBean.setNumeroOrden(rs.getInt("numeroOrden"));
                fachadaBean.setIdFicha(rs.getInt("idFicha"));
                fachadaBean.setOrdenCompra(rs.getString("ordenCompra"));
                fachadaBean.setFechaOrden(rs.getString("fechaOrden"));
                fachadaBean.setPesoPedido(rs.getDouble("pesoPedido"));
                fachadaBean.setPesoTerminado(rs.getDouble("pesoTerminado"));
                fachadaBean.setPesoRetal(rs.getDouble("pesoRetal"));
                fachadaBean.setCantidadFacturada(
                                             rs.getDouble("cantidadFacturada"));


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

    // listaUnAlfabeticoFacturado
    public Vector listaUnAlfabeticoFacturado(int xIdTipoTercero) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        // Variable de fachada de los datos
        FachadaColaboraDctoOrdenBean fachadaBean;

        Connection connection = null;

        String selectStr =
               "  SELECT tmpDET.idLog,                               "
               + "         tmpDET.idCliente,                         "
               + "         tmpDET.idUsuario,                         "
               + "         tmpDET.fechaEntrega,                      "
               + "         tblTerceros.nombreTercero,                "
               + "         tblTerceros.direccionTercero,             "
               + "         tblTerceros.telefonoFijo,                 "
               + "         tblTerceros.nombreEmpresa,                "
               + "         tblTerceros.ciudadTercero,                "
               + "         ctrlUsuarios.aliasUsuario AS              "
               + "                              NOMBREUSUARIO,       "
               + "         tmpDET.idLocal,                           "
               + "         tmpDET.idTipoOrden,                       "
               + "         tmpDET.idOrden,                           "
               + "         tmpDET.item,                              "
               + "         tmpDET.cantidad,                          "
               + "         tmpDET.referenciaCliente,                 "
               + "         tmpDET.numeroOrden,                       "
               + "         tmpDET.idFicha,                           "
               + "         tmpDET.ordenCompra,                       "
               + "         tmpDET.fechaOrden,                        "
               + "         tmpDET.pesoPedido,                        "
               + "         tmpDET.pesoTerminado,                     "
               + "         tmpDET.pesoRetal,                         "
               + "         tmpDET.cantidadFacturada,                 "
               + "         tmpDET.cantidadTerminada                  "
               + "  FROM   tblTerceros                               "
               + "  INNER JOIN tblAgendaLogVisitas                   "
               + "  ON tblTerceros.idCliente         =               "
               + "              tblAgendaLogVisitas.idCliente        "
               + "  INNER JOIN ctrlUsuarios                          "
               + "  ON tblAgendaLogVisitas.IDUSUARIO =               "
               + "                     ctrlUsuarios.IDUSUARIO        "
               + "  INNER JOIN                                       "
               + "  (SELECT tblDctosOrdenesDetalle.idLocal,          "
               + "         tblDctosOrdenesDetalle.idTipoOrden,       "
               + "         tblDctosOrdenesDetalle.idOrden,           "
               + "         tblDctosOrdenesDetalle.item,              "
               + "         tblDctosOrdenesDetalle.cantidad,          "
               + "         tblDctosOrdenesDetalle.referenciaCliente, "
               + "         tblDctosOrdenesDetalle.fechaEntrega,      "
               + "         tblDctosOrdenes.idLog,                    "
               + "         tblDctosOrdenes.numeroOrden,              "
               + "         tblDctosOrdenes.idFicha,                  "
               + "         tblDctosOrdenes.idCliente,                "
               + "         tblDctosOrdenes.idUsuario,                "
               + "         tblDctosOrdenes.ordenCompra,              "
               + "         tblDctosOrdenes.fechaOrden,               "
               + "        tblDctosOrdenesDetalle.idEstadoRefOriginal,"
               + "        tblDctosOrdenesDetalle.pesoPedido,         "
               + "        tblDctosOrdenesDetalle.pesoTerminado,      "
               + "        tblDctosOrdenesDetalle.pesoRetal,          "
               + "        tblDctosOrdenesDetalle.cantidadFacturada,  "
               + "        tblDctosOrdenesDetalle.cantidadTerminada   "
               + "   FROM       tblDctosOrdenes                      "
               + "   INNER JOIN tblDctosOrdenesDetalle               "
               + "   ON tblDctosOrdenes.IDLOCAL      =               "
               + "                   tblDctosOrdenesDetalle.IDLOCAL  "
               + "   AND tblDctosOrdenes.IDTIPOORDEN =               "
               + "               tblDctosOrdenesDetalle.IDTIPOORDEN  "
               + "   AND tblDctosOrdenes.IDORDEN     =               "
               + "                   tblDctosOrdenesDetalle.IDORDEN) "
               + "                                         AS tmpDET "
               + "  ON tmpDET.idLog = tblAgendaLogVisitas.idLog      "
               + "  WHERE tblAgendaLogVisitas.IDLOG IN (             "
               + "     SELECT tblAgendaLogVisitas.IDLOG              "
               + "     FROM   tblAgendaLogVisitas                    "
               + "     INNER JOIN tblDctosOrdenes                    "
               + "     ON tblAgendaLogVisitas.IDLOG     =            "
               + "                      tblDctosOrdenes.IDLOG        "
               + " AND tmpDET.cantidadTerminada <=                   "
               + "                          tmpDET.cantidadFacturada "    
               + "  AND tmpDET.pesoPedido               > 0          "
               + "  AND tmpDET.pesoPedido               > 0          "
               + "  AND tmpDET.idEstadoRefOriginal     != 9          "    
               + "  AND   tblDctosOrdenes.idLocal       =            "
               + getIdLocal() + "                                    "
               + "  AND   tblDctosOrdenes.idTipoOrden   =            "
               + getIdTipoOrden() + "                                "
               + "  AND   tblDctosOrdenes.numeroOrden  != 0          "
               + "  AND   tblDctosOrdenes.idCliente     =           '"
               + getIdCliente() + "'                                 "
               + "  AND tblTerceros.idTipoTercero       =            "
               + xIdTipoTercero + " )                                "
               + "  ORDER BY tblTerceros.nombreTercero,              "
               + "           tmpDET.numeroOrden,                     "
               + "           tmpDET.fechaEntrega                     ";

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
                fachadaBean = new FachadaColaboraDctoOrdenBean();

                //
                fachadaBean.setIdLog(rs.getInt("idLog"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setIdUsuario(rs.getDouble("idUsuario"));
                fachadaBean.setFechaEntrega(rs.getString("fechaEntrega"));
                fachadaBean.setNombreTercero(rs.getString("nombreTercero"));
                fachadaBean.setDireccionDespacho(
                                              rs.getString("direccionTercero"));
                fachadaBean.setTelefonoFijo(rs.getString("telefonoFijo"));
                fachadaBean.setNombreEmpresa(rs.getString("nombreEmpresa"));
                fachadaBean.setCiudadTercero(rs.getString("ciudadTercero"));
                fachadaBean.setNombreUsuario(rs.getString("NOMBREUSUARIO"));
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setIdOrden(rs.getInt("idOrden"));
                fachadaBean.setItem(rs.getInt("item"));
                fachadaBean.setCantidad(rs.getDouble("cantidad"));
                fachadaBean.setReferenciaCliente(
                                             rs.getString("referenciaCliente"));
                fachadaBean.setNumeroOrden(rs.getInt("numeroOrden"));
                fachadaBean.setIdFicha(rs.getInt("idFicha"));
                fachadaBean.setOrdenCompra(rs.getString("ordenCompra"));
                fachadaBean.setFechaOrden(rs.getString("fechaOrden"));
                fachadaBean.setPesoPedido(rs.getDouble("pesoPedido"));
                fachadaBean.setPesoTerminado(rs.getDouble("pesoTerminado"));
                fachadaBean.setPesoRetal(rs.getDouble("pesoRetal"));
                fachadaBean.setCantidadFacturada(
                                             rs.getDouble("cantidadFacturada"));
                fachadaBean.setCantidadTerminada(
                                             rs.getDouble("cantidadTerminada"));

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

    // listaUnAlfabeticoSinFactura
    public Vector listaUnAlfabeticoSinFactura(int xIdTipoTercero) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        // Variable de fachada de los datos
        FachadaColaboraDctoOrdenBean fachadaBean;

        Connection connection = null;

        String selectStr =
               "  SELECT tmpDET.idLog,                               "
               + "         tmpDET.idCliente,                         "
               + "         tmpDET.idUsuario,                         "
               + "         tmpDET.fechaEntrega,                      "
               + "         tblTerceros.nombreTercero,                "
               + "         tblTerceros.direccionTercero,             "
               + "         tblTerceros.telefonoFijo,                 "
               + "         tblTerceros.nombreEmpresa,                "
               + "         tblTerceros.ciudadTercero,                "
               + "         ctrlUsuarios.aliasUsuario AS              "
               + "                              NOMBREUSUARIO,       "
               + "         tmpDET.idLocal,                           "
               + "         tmpDET.idTipoOrden,                       "
               + "         tmpDET.idOrden,                           "
               + "         tmpDET.item,                              "
               + "         tmpDET.cantidad,                          "
               + "         tmpDET.referenciaCliente,                 "
               + "         tmpDET.numeroOrden,                       "
               + "         tmpDET.idFicha,                           "
               + "         tmpDET.ordenCompra,                       "
               + "         tmpDET.fechaOrden,                        "
               + "         tmpDET.pesoPedido,                        "
               + "         tmpDET.pesoTerminado,                     "
               + "         tmpDET.pesoRetal,                         "
               + "         tmpDET.cantidadFacturada,                 "
               + "         tmpDET.cantidadTerminada                  "
               + "  FROM   tblTerceros                               "
               + "  INNER JOIN tblAgendaLogVisitas                   "
               + "  ON tblTerceros.idCliente         =               "
               + "              tblAgendaLogVisitas.idCliente        "
               + "  INNER JOIN ctrlUsuarios                          "
               + "  ON tblAgendaLogVisitas.IDUSUARIO =               "
               + "                     ctrlUsuarios.IDUSUARIO        "
               + "  INNER JOIN                                       "
               + "  (SELECT tblDctosOrdenes.idLocal,                 "
               + "         tblDctosOrdenes.idTipoOrden,              "
               + "         tblDctosOrdenes.idOrden,                  "
               + "         tblDctosOrdenesDetalle.item,              "
               + "         tblDctosOrdenesDetalle.cantidad,          "
               + "         tblDctosOrdenesDetalle.referenciaCliente, "
               + "         tblDctosOrdenesDetalle.fechaEntrega,      "
               + "         tblDctosOrdenes.idLog,                    "
               + "         tblDctosOrdenes.numeroOrden,              "
               + "         tblDctosOrdenes.idFicha,                  "
               + "         tblDctosOrdenes.idCliente,                "
               + "         tblDctosOrdenes.idUsuario,                "
               + "         tblDctosOrdenes.ordenCompra,              "
               + "         tblDctosOrdenes.fechaOrden,               "
               + "        tblDctosOrdenesDetalle.idEstadoRefOriginal,"
               + "        tblDctosOrdenesDetalle.pesoPedido,         "
               + "        tblDctosOrdenesDetalle.pesoTerminado,      "
               + "        tblDctosOrdenesDetalle.pesoRetal,          "
               + "        tblDctosOrdenesDetalle.cantidadFacturada,  "
               + "        tblDctosOrdenesDetalle.cantidadTerminada   "
               + "   FROM       tblDctosOrdenes                      "
               + "   INNER JOIN tblDctosOrdenesDetalle               "
               + "   ON tblDctosOrdenes.IDLOCAL      =               "
               + "                   tblDctosOrdenesDetalle.IDLOCAL  "
               + "   AND tblDctosOrdenes.IDTIPOORDEN =               "
               + "               tblDctosOrdenesDetalle.IDTIPOORDEN  "
               + "   AND tblDctosOrdenes.IDORDEN     =               "
               + "                   tblDctosOrdenesDetalle.IDORDEN) "
               + "                                         AS tmpDET "
               + "  ON tmpDET.idLog = tblAgendaLogVisitas.idLog      "
               + "  WHERE tblAgendaLogVisitas.IDLOG IN (             "
               + "     SELECT tblAgendaLogVisitas.IDLOG              "
               + "     FROM   tblAgendaLogVisitas                    "
               + "     INNER JOIN tblDctosOrdenes                    "
               + "     ON tblAgendaLogVisitas.IDLOG     =            "
               + "                      tblDctosOrdenes.IDLOG        "
               + " AND tmpDET.cantidadTerminada        >=            "
               + "                          tmpDET.cantidadFacturada "
               + "  AND tmpDET.pesoPedido               > 0          "
               + "  AND tmpDET.idEstadoRefOriginal     != 9          " 
               + "  AND   tblDctosOrdenes.idLocal       =            "
               + getIdLocal() + "                                    "
               + "  AND   tblDctosOrdenes.idTipoOrden   =            "
               + getIdTipoOrden() + "                                "
               + "  AND   tblDctosOrdenes.numeroOrden  != 0          "
               + "  AND   tblDctosOrdenes.idCliente     =           '"
               + getIdCliente() + "'                                 "
               + "  AND tblTerceros.idTipoTercero       =            "
               + xIdTipoTercero + " )                                "
               + "  ORDER BY tblTerceros.nombreTercero,              "
               + "           tmpDET.numeroOrden,                     "
               + "           tmpDET.fechaEntrega                     ";

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
                fachadaBean = new FachadaColaboraDctoOrdenBean();

                //
                fachadaBean.setIdLog(rs.getInt("idLog"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setIdUsuario(rs.getDouble("idUsuario"));
                fachadaBean.setFechaEntrega(rs.getString("fechaEntrega"));
                fachadaBean.setNombreTercero(rs.getString("nombreTercero"));
                fachadaBean.setDireccionDespacho(
                                              rs.getString("direccionTercero"));
                fachadaBean.setTelefonoFijo(rs.getString("telefonoFijo"));
                fachadaBean.setNombreEmpresa(rs.getString("nombreEmpresa"));
                fachadaBean.setCiudadTercero(rs.getString("ciudadTercero"));
                fachadaBean.setNombreUsuario(rs.getString("NOMBREUSUARIO"));
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setIdOrden(rs.getInt("idOrden"));
                fachadaBean.setItem(rs.getInt("item"));
                fachadaBean.setCantidad(rs.getDouble("cantidad"));
                fachadaBean.setReferenciaCliente(
                                             rs.getString("referenciaCliente"));
                fachadaBean.setNumeroOrden(rs.getInt("numeroOrden"));
                fachadaBean.setIdFicha(rs.getInt("idFicha"));
                fachadaBean.setOrdenCompra(rs.getString("ordenCompra"));
                fachadaBean.setFechaOrden(rs.getString("fechaOrden"));
                fachadaBean.setPesoPedido(rs.getDouble("pesoPedido"));
                fachadaBean.setPesoTerminado(rs.getDouble("pesoTerminado"));
                fachadaBean.setPesoRetal(rs.getDouble("pesoRetal"));
                fachadaBean.setCantidadFacturada(
                                             rs.getDouble("cantidadFacturada"));
                fachadaBean.setCantidadTerminada(
                                             rs.getDouble("cantidadTerminada"));

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

    // listaUnAlfabeticoNuevo
    public Vector listaUnAlfabeticoNuevo(int xIdTipoTercero) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        // Variable de fachada de los datos
        FachadaColaboraDctoOrdenBean fachadaBean;

        Connection connection = null;

        String selectStr =
               "  SELECT tmpDET.idLog,                               "
               + "         tmpDET.idCliente,                         "
               + "         tmpDET.idUsuario,                         "
               + "         tmpDET.fechaEntrega,                      "
               + "         tblTerceros.nombreTercero,                "
               + "         tblTerceros.direccionTercero,             "
               + "         tblTerceros.telefonoFijo,                 "
               + "         tblTerceros.nombreEmpresa,                "
               + "         tblTerceros.ciudadTercero,                "
               + "         ctrlUsuarios.aliasUsuario AS              "
               + "                              NOMBREUSUARIO,       "
               + "         tmpDET.idLocal,                           "
               + "         tmpDET.idTipoOrden,                       "
               + "         tmpDET.idOrden,                           "
               + "         tmpDET.item,                              "
               + "         tmpDET.cantidad,                          "
               + "         tmpDET.referenciaCliente,                 "
               + "         tmpDET.numeroOrden,                       "
               + "         tmpDET.idFicha,                           "
               + "         tmpDET.ordenCompra,                       "
               + "         tmpDET.fechaOrden,                        "
               + "         tmpDET.pesoPedido,                        "
               + "         tmpDET.pesoTerminado,                     "
               + "         tmpDET.pesoRetal,                         "
               + "         tmpDET.cantidadFacturada                  "
               + "  FROM   tblTerceros                               "
               + "  INNER JOIN tblAgendaLogVisitas                   "
               + "  ON tblTerceros.idCliente         =               "
               + "              tblAgendaLogVisitas.idCliente        "
               + "  INNER JOIN ctrlUsuarios                          "
               + "  ON tblAgendaLogVisitas.IDUSUARIO =               "
               + "                     ctrlUsuarios.IDUSUARIO        "
               + "  INNER JOIN                                       "
               + "  (SELECT tblDctosOrdenes.idLocal,                 "
               + "         tblDctosOrdenes.idTipoOrden,              "
               + "         tblDctosOrdenes.idOrden,                  "
               + "         tblDctosOrdenesDetalle.item,              "
               + "         tblDctosOrdenesDetalle.cantidad,          "
               + "         tblDctosOrdenesDetalle.referenciaCliente, "
               + "         tblDctosOrdenesDetalle.fechaEntrega,      "
               + "         tblDctosOrdenes.idLog,                    "
               + "         tblDctosOrdenes.numeroOrden,              "
               + "         tblDctosOrdenes.idFicha,                  "
               + "         tblDctosOrdenes.idCliente,                "
               + "         tblDctosOrdenes.idUsuario,                "
               + "         tblDctosOrdenes.ordenCompra,              "
               + "         tblDctosOrdenes.fechaOrden,               "
               + "        tblDctosOrdenesDetalle.idEstadoRefOriginal,"
               + "        tblDctosOrdenesDetalle.pesoPedido,         "
               + "        tblDctosOrdenesDetalle.pesoTerminado,      "
               + "        tblDctosOrdenesDetalle.pesoRetal,          "
               + "        tblDctosOrdenesDetalle.cantidadFacturada   "
               + "   FROM       tblDctosOrdenes                      "
               + "   INNER JOIN tblDctosOrdenesDetalle               "
               + "   ON tblDctosOrdenes.IDLOCAL      =               "
               + "                   tblDctosOrdenesDetalle.IDLOCAL  "
               + "   AND tblDctosOrdenes.IDTIPOORDEN =               "
               + "               tblDctosOrdenesDetalle.IDTIPOORDEN  "
               + "   AND tblDctosOrdenes.IDORDEN     =               "
               + "                   tblDctosOrdenesDetalle.IDORDEN) "
               + "                                         AS tmpDET "
               + "  ON tmpDET.idLog = tblAgendaLogVisitas.idLog      "
               + "  WHERE tblAgendaLogVisitas.IDLOG IN (             "
               + "     SELECT tblAgendaLogVisitas.IDLOG              "
               + "     FROM   tblAgendaLogVisitas                    "
               + "     INNER JOIN tblDctosOrdenes                    "
               + "     ON tblAgendaLogVisitas.IDLOG     =            "
               + "                      tblDctosOrdenes.IDLOG        "
               + "  AND tmpDET.pesoPedido               = 0          "
               + "  AND tmpDET.idEstadoRefOriginal     != 9          " 
               + "  AND   tblDctosOrdenes.idLocal       =            "
               + getIdLocal() + "                                    "
               + "  AND   tblDctosOrdenes.idTipoOrden   =            "
               + getIdTipoOrden() + "                                "
               + "  AND   tblDctosOrdenes.numeroOrden  != 0          "
               + "  AND   tblDctosOrdenes.idCliente     =           '"
               + getIdCliente() + "'                                 "
               + "  AND tblTerceros.idTipoTercero       =            "
               + xIdTipoTercero + " )                                "
               + "  ORDER BY tblTerceros.nombreTercero,              "
               + "           tmpDET.numeroOrden,                     "
               + "           tmpDET.fechaEntrega                     ";

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
                fachadaBean = new FachadaColaboraDctoOrdenBean();

                //
                fachadaBean.setIdLog(rs.getInt("idLog"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setIdUsuario(rs.getDouble("idUsuario"));
                fachadaBean.setFechaEntrega(rs.getString("fechaEntrega"));
                fachadaBean.setNombreTercero(rs.getString("nombreTercero"));
                fachadaBean.setDireccionDespacho(
                                              rs.getString("direccionTercero"));
                fachadaBean.setTelefonoFijo(rs.getString("telefonoFijo"));
                fachadaBean.setNombreEmpresa(rs.getString("nombreEmpresa"));
                fachadaBean.setCiudadTercero(rs.getString("ciudadTercero"));
                fachadaBean.setNombreUsuario(rs.getString("NOMBREUSUARIO"));
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setIdOrden(rs.getInt("idOrden"));
                fachadaBean.setItem(rs.getInt("item"));
                fachadaBean.setCantidad(rs.getDouble("cantidad"));
                fachadaBean.setReferenciaCliente(
                                             rs.getString("referenciaCliente"));
                fachadaBean.setNumeroOrden(rs.getInt("numeroOrden"));
                fachadaBean.setIdFicha(rs.getInt("idFicha"));
                fachadaBean.setOrdenCompra(rs.getString("ordenCompra"));
                fachadaBean.setFechaOrden(rs.getString("fechaOrden"));
                fachadaBean.setPesoPedido(rs.getDouble("pesoPedido"));
                fachadaBean.setPesoTerminado(rs.getDouble("pesoTerminado"));
                fachadaBean.setPesoRetal(rs.getDouble("pesoRetal"));
                fachadaBean.setCantidadFacturada(
                                             rs.getDouble("cantidadFacturada"));

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

    // listaAllOT
    public Vector listaAllOT(int xIdTipoTercero) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        // Variable de fachada de los datos
        FachadaColaboraDctoOrdenBean fachadaBean;

        Connection connection = null;

        String selectStr =
               "  SELECT TOP 1 '00' AS idCliente,                    "
               + "         '--' AS nombreTercero,                    "
               + "         01 AS ordenSalida                         "
               + "  FROM   tblTerceros                               "
               + "  UNION                                            "
               + "  SELECT tmpDET.idCliente,                         "
               + "         tblTerceros.nombreTercero,                "
               + "         02 AS ordenSalida                         "
               + "  FROM   tblTerceros                               "
               + "  INNER JOIN tblAgendaLogVisitas                   "
               + "  ON tblTerceros.idCliente         =               "
               + "              tblAgendaLogVisitas.idCliente        "
               + "  INNER JOIN ctrlUsuarios                          "
               + "  ON tblAgendaLogVisitas.IDUSUARIO =               "
               + "                     ctrlUsuarios.IDUSUARIO        "
               + "  INNER JOIN                                       "
               + " (SELECT tblDctosOrdenes.idCliente,                "
               + "         tblDctosOrdenes.idLog                     "
               + "   FROM       tblDctosOrdenes                      "
               + "   INNER JOIN tblDctosOrdenesDetalle               "
               + "   ON tblDctosOrdenes.IDLOCAL      =               "
               + "                   tblDctosOrdenesDetalle.IDLOCAL  "
               + "   AND tblDctosOrdenes.IDTIPOORDEN =               "
               + "               tblDctosOrdenesDetalle.IDTIPOORDEN  "
               + "   AND tblDctosOrdenes.IDORDEN     =               "
               + "                   tblDctosOrdenesDetalle.IDORDEN) "
               + "                                         AS tmpDET "
               + "  ON tmpDET.idLog = tblAgendaLogVisitas.idLog      "
               + "  WHERE tblAgendaLogVisitas.IDLOG IN (             "
               + "     SELECT tblAgendaLogVisitas.IDLOG              "
               + "     FROM   tblAgendaLogVisitas                    "
               + "     INNER JOIN tblDctosOrdenes                    "
               + "     ON tblAgendaLogVisitas.IDLOG     =            "
               + "                      tblDctosOrdenes.IDLOG        "
               + "  AND   tblDctosOrdenes.idLocal       =            "
               + getIdLocal() + "                                    "
               + "  AND   tblDctosOrdenes.idTipoOrden   =            "
               + getIdTipoOrden() + "                                "
               + "  AND   tblAgendaLogVisitas.estado    =            "
               + getEstado() + "                                     "
               + "  AND tblTerceros.idTipoTercero       =            "
               + xIdTipoTercero + " )                                "
               + "GROUP BY tmpDET.idCliente,                         "
               + "         tblTerceros.nombreTercero                 "
               + "ORDER BY 3,2                                       " ;

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
                fachadaBean = new FachadaColaboraDctoOrdenBean();

                //
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setNombreTercero(rs.getString("nombreTercero"));

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

    // listaPedidoAdministradorFechaOrden MsAccess
    public Vector listaPedidoAdministradorFechaOrden() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        // Variable de fachada de los datos
        FachadaColaboraDctoOrdenBean fachadaBean;

        boolean validaOk = false;
        Connection connection = null;

        String selectStr =
                "SELECT tblDctosOrdenes.idLocal,                         "
                + "       tblDctosOrdenes.idTipoOrden,                     "
                + "       tblDctosOrdenes.idOrden,                         "
                + "       tblDctosOrdenes.idLog,                           "
                + "       tblDctosOrdenes.idCliente,                       "
                + "       tblDctosOrdenes.idUsuario,                       "
                + "       tblDctosOrdenes.fechaOrden,                      "
                + "       tblDctosOrdenes.tipoDcto,                        "
                + "       tblDctosOrdenes.direccionDespacho,               "
                + "       tblDctosOrdenes.email,                           "
                + "       tblDctosOrdenes.fax,                             "
                + "       tblDctosOrdenes.contacto,                        "
                + "       tblDctosOrdenes.observacion,                     "
                + "       COUNT(tblDctosOrdenesDetalle.idPlu)              "
                + "                        AS cantidadArticulos,           "
                + "       SUM ( tblDctosOrdenesDetalle.cantidad          * "
                + "             tblDctosOrdenesDetalle.vrVentaUnitario   * "
                + "  (1 - (tblDctosOrdenesDetalle.porcentajeDscto/100)))   "
                + "                        AS vrVentaSinIva,               "
                + "       SUM ( tblDctosOrdenesDetalle.cantidad          * "
                + "             tblDctosOrdenesDetalle.vrVentaUnitario   * "
                + "  (1 - (tblDctosOrdenesDetalle.porcentajeDscto/100))  * "
                + "  (1 + (tblDctosOrdenesDetalle.porcentajeIva/100)))     "
                + "                        AS vrVentaConIva,               "
                + "       SUM(tblDctosOrdenesDetalle.pesoTeorico *         "
                + "           tblDctosOrdenesDetalle.cantidad)             "
                + "                        AS pesoTeoricoTotal,            "
                + "      tblDctosOrdenes.estado,                           "
                + "      tblDctosEstados.nombreEstado                      "
                + "FROM  tblDctosOrdenes ,                                 "
                + "      tblDctosOrdenesDetalle,                           "
                + "      tblDctosEstados                                   "
                + "WHERE tblDctosOrdenes.idOrden         =                 "
                + "      tblDctosOrdenesDetalle.idOrden                    "
                + "AND   tblDctosOrdenes.idTipoOrden     =                 "
                + "      tblDctosOrdenesDetalle.idTipoOrden                "
                + "AND   tblDctosOrdenes.idLocal         =                 "
                + "      tblDctosOrdenesDetalle.idLocal                    "
                + "AND   tblDctosOrdenes.idTipoOrden     = ( ? )           "
                + "AND   tblDctosOrdenes.estado          =                 "
                + "      tblDctosEstados.estado                            "
                + "AND   ( tblDctosOrdenes.fechaOrden    = ( ? )           "
                + "        OR tblDctosEstados.estado     = ( ? ) )         "
                + "GROUP BY tblDctosOrdenes.idLocal,                       "
                + "       tblDctosOrdenes.idTipoOrden,                     "
                + "       tblDctosOrdenes.idOrden,                         "
                + "       tblDctosOrdenes.idLog,                           "
                + "       tblDctosOrdenes.idCliente,                       "
                + "       tblDctosOrdenes.idUsuario,                       "
                + "       tblDctosOrdenes.fechaOrden,                      "
                + "       tblDctosOrdenes.tipoDcto,                        "
                + "       tblDctosOrdenes.direccionDespacho,               "
                + "       tblDctosOrdenes.email,                           "
                + "       tblDctosOrdenes.fax,                             "
                + "       tblDctosOrdenes.contacto,                        "
                + "       tblDctosOrdenes.observacion,                     "
                + "       tblDctosOrdenes.estado,                          "
                + "       tblDctosEstados.nombreEstado                     "
                + "ORDER BY tblDctosOrdenes.fechaOrden DESC,               "
                + "         tblDctosOrdenes.idLocal,                       "
                + "         tblDctosOrdenes.idTipoOrden,                   "
                + "         tblDctosOrdenes.idOrden DESC                   ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            //inicializa los valores de los parametros
            selectStatement.setInt(1, getIdTipoOrden());
            selectStatement.setString(2, getFechaOrden());
            selectStatement.setInt(3, getEstado());

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            while (rs.next()) {

                fachadaBean = new FachadaColaboraDctoOrdenBean();

                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("IdTipoOrden"));
                fachadaBean.setIdOrden(rs.getInt("IdOrden"));
                fachadaBean.setIdLog(rs.getInt("idLog"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setIdUsuario(rs.getDouble("idUsuario"));
                fachadaBean.setFechaOrden(rs.getString("fechaOrden").substring(0, 11));
                fachadaBean.setTipoDcto(rs.getString("tipoDcto"));
                fachadaBean.setDireccionDespacho(rs.getString("direccionDespacho"));
                fachadaBean.setEmail(rs.getString("email"));
                fachadaBean.setFax(rs.getString("fax"));
                fachadaBean.setContacto(rs.getString("contacto"));
                fachadaBean.setObservacion(rs.getString("observacion"));
                fachadaBean.setCantidadArticulos(rs.getInt("cantidadArticulos"));
                fachadaBean.setVrVentaSinIva(rs.getInt("vrVentaSinIva"));
                fachadaBean.setVrVentaConIva(rs.getInt("vrVentaConIva"));
                fachadaBean.setPesoTeoricoTotal(rs.getDouble("pesoTeoricoTotal"));
                fachadaBean.setEstado(rs.getInt("estado"));
                fachadaBean.setNombreEstado(rs.getString("nombreEstado"));

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

    // listaAllPedidoUsuarioFecha MsAccess
    public Vector listaAllPedidoUsuarioFecha() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        // Variable de fachada de los datos
        FachadaColaboraDctoOrdenBean fachadaBean;

        boolean validaOk = false;
        Connection connection = null;

        String selectStr =
                "SELECT tblDctosOrdenes.idLocal,                         "
                + "       tblDctosOrdenes.idTipoOrden,                     "
                + "       tblDctosOrdenes.idOrden,                         "
                + "       tblDctosOrdenes.idLog,                           "
                + "       tblDctosOrdenes.idCliente,                       "
                + "       tblDctosOrdenes.idUsuario,                       "
                + "       tblDctosOrdenes.fechaOrden,                      "
                + "       tblDctosOrdenes.tipoDcto,                        "
                + "       tblDctosOrdenes.direccionDespacho,               "
                + "       tblDctosOrdenes.email,                           "
                + "       tblDctosOrdenes.fax,                             "
                + "       tblDctosOrdenes.contacto,                        "
                + "       tblDctosOrdenes.observacion,                     "
                + "       COUNT(tblDctosOrdenesDetalle.idPlu)              "
                + "                        AS cantidadArticulos,           "
                + "       SUM ( tblDctosOrdenesDetalle.cantidad          * "
                + "             tblDctosOrdenesDetalle.vrVentaUnitario )   "
                + "                        AS vrVentaSinIva,               "
                + "       SUM ( tblDctosOrdenesDetalle.cantidad          * "
                + "             tblDctosOrdenesDetalle.vrVentaUnitario   * "
                + "  (1 + (tblDctosOrdenesDetalle.porcentajeIva/100)))     "
                + "                        AS vrVentaConIva,               "
                + "       SUM(tblDctosOrdenesDetalle.pesoTeorico *         "
                + "           tblDctosOrdenesDetalle.cantidad)             "
                + "                        AS pesoTeoricoTotal,            "
                + "      tblDctosOrdenes.idEstadoTx,                       "
                + "      tblDctosEstados.nombreEstado                      "
                + "FROM  tblDctosOrdenes ,                                 "
                + "      tblDctosOrdenesDetalle,                           "
                + "      tblDctosEstados                                   "
                + "WHERE tblDctosOrdenes.idOrden         =                 "
                + "      tblDctosOrdenesDetalle.idOrden                    "
                + "AND   tblDctosOrdenes.idTipoOrden     =                 "
                + "      tblDctosOrdenesDetalle.idTipoOrden                "
                + "AND   tblDctosOrdenes.idLocal         =                 "
                + "      tblDctosOrdenesDetalle.idLocal                    "
                + "AND   tblDctosOrdenes.idUsuario       =                 "
                + getIdUsuario() + "                                       "
                + "AND   tblDctosOrdenes.idTipoOrden     =                 "
                + getIdTipoOrden() + "                                     "
                + "AND   tblDctosOrdenes.fechaOrden      =                '"
                + getFechaOrdenSqlServer() + "'                            "
                + "AND   tblDctosOrdenes.idEstadoTx      =                 "
                + "      tblDctosEstados.estado                            "
                + "GROUP BY tblDctosOrdenes.idLocal,                       "
                + "       tblDctosOrdenes.idTipoOrden,                     "
                + "       tblDctosOrdenes.idOrden,                         "
                + "       tblDctosOrdenes.idLog,                           "
                + "       tblDctosOrdenes.idCliente,                       "
                + "       tblDctosOrdenes.idUsuario,                       "
                + "       tblDctosOrdenes.fechaOrden,                      "
                + "       tblDctosOrdenes.tipoDcto,                        "
                + "       tblDctosOrdenes.direccionDespacho,               "
                + "       tblDctosOrdenes.email,                           "
                + "       tblDctosOrdenes.fax,                             "
                + "       tblDctosOrdenes.contacto,                        "
                + "       tblDctosOrdenes.observacion,                     "
                + "       tblDctosOrdenes.idEstadoTx,                      "
                + "       tblDctosEstados.nombreEstado                     "
                + "ORDER BY tblDctosOrdenes.fechaOrden DESC,               "
                + "         tblDctosOrdenes.idLocal,                       "
                + "         tblDctosOrdenes.idTipoOrden,                   "
                + "         tblDctosOrdenes.idOrden DESC                   ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            while (rs.next()) {

                fachadaBean = new FachadaColaboraDctoOrdenBean();

                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("IdTipoOrden"));
                fachadaBean.setIdOrden(rs.getInt("IdOrden"));
                fachadaBean.setIdLog(rs.getInt("idLog"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setIdUsuario(rs.getDouble("idUsuario"));
                fachadaBean.setFechaOrden(rs.getString("fechaOrden").substring(0, 11));
                fachadaBean.setTipoDcto(rs.getString("tipoDcto"));
                fachadaBean.setDireccionDespacho(rs.getString("direccionDespacho"));
                fachadaBean.setEmail(rs.getString("email"));
                fachadaBean.setFax(rs.getString("fax"));
                fachadaBean.setContacto(rs.getString("contacto"));
                fachadaBean.setObservacion(rs.getString("observacion"));
                fachadaBean.setCantidadArticulos(rs.getInt("cantidadArticulos"));
                fachadaBean.setVrVentaSinIva(rs.getInt("vrVentaSinIva"));
                fachadaBean.setVrVentaConIva(rs.getInt("vrVentaConIva"));
                fachadaBean.setPesoTeoricoTotal(rs.getDouble("pesoTeoricoTotal"));
                fachadaBean.setIdEstadoTx(rs.getInt("idEstadoTx"));
                fachadaBean.setNombreEstado(rs.getString("nombreEstado"));

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

    // listaAllPedidoClienteFecha MsAccess
    public Vector listaAllPedidoClienteFecha() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        // Variable de fachada de los datos
        FachadaColaboraDctoOrdenBean fachadaBean;

        boolean validaOk = false;
        Connection connection = null;

        String selectStr =
                "SELECT tblDctosOrdenes.idLocal,                         "
                + "       tblDctosOrdenes.idTipoOrden,                     "
                + "       tblDctosOrdenes.idOrden,                         "
                + "       tblDctosOrdenes.idLog,                           "
                + "       tblDctosOrdenes.idCliente,                       "
                + "       tblDctosOrdenes.idUsuario,                       "
                + "       tblDctosOrdenes.fechaOrden,                      "
                + "       tblDctosOrdenes.tipoDcto,                        "
                + "       tblDctosOrdenes.direccionDespacho,               "
                + "       tblDctosOrdenes.email,                           "
                + "       tblDctosOrdenes.fax,                             "
                + "       tblDctosOrdenes.contacto,                        "
                + "       tblDctosOrdenes.observacion,                     "
                + "       COUNT(tblDctosOrdenesDetalle.idPlu)              "
                + "                        AS cantidadArticulos,           "
                + "       SUM ( tblDctosOrdenesDetalle.cantidad          * "
                + "             tblDctosOrdenesDetalle.vrVentaUnitario )   "
                + "                        AS vrVentaSinIva,               "
                + "       SUM ( tblDctosOrdenesDetalle.cantidad          * "
                + "             tblDctosOrdenesDetalle.vrVentaUnitario   * "
                + "  (1 + (tblDctosOrdenesDetalle.porcentajeIva/100)))     "
                + "                        AS vrVentaConIva,               "
                + "       SUM(tblDctosOrdenesDetalle.pesoTeorico *         "
                + "           tblDctosOrdenesDetalle.cantidad)             "
                + "                        AS pesoTeoricoTotal,            "
                + "      tblDctosOrdenes.idEstadoTx,                       "
                + "      tblDctosEstados.nombreEstado                      "
                + "FROM  tblDctosOrdenes ,                                 "
                + "      tblDctosOrdenesDetalle,                           "
                + "      tblDctosEstados                                   "
                + "WHERE tblDctosOrdenes.idOrden         =                 "
                + "      tblDctosOrdenesDetalle.idOrden                    "
                + "AND   tblDctosOrdenes.idTipoOrden     =                 "
                + "      tblDctosOrdenesDetalle.idTipoOrden                "
                + "AND   tblDctosOrdenes.idLocal         =                 "
                + "      tblDctosOrdenesDetalle.idLocal                    "
                + "AND   tblDctosOrdenes.idCliente       =                '"
                + getIdCliente() + "'                                      "
                + "AND   tblDctosOrdenes.idTipoOrden     =                 "
                + getIdTipoOrden() + "                                     "
                + "AND   tblDctosOrdenes.fechaOrden      =                '"
                + getFechaOrdenSqlServer() + "'                            "
                + "AND   tblDctosOrdenes.idEstadoTx      =                 "
                + "      tblDctosEstados.estado                            "
                + "GROUP BY tblDctosOrdenes.idLocal,                       "
                + "       tblDctosOrdenes.idTipoOrden,                     "
                + "       tblDctosOrdenes.idOrden,                         "
                + "       tblDctosOrdenes.idLog,                           "
                + "       tblDctosOrdenes.idCliente,                       "
                + "       tblDctosOrdenes.idUsuario,                       "
                + "       tblDctosOrdenes.fechaOrden,                      "
                + "       tblDctosOrdenes.tipoDcto,                        "
                + "       tblDctosOrdenes.direccionDespacho,               "
                + "       tblDctosOrdenes.email,                           "
                + "       tblDctosOrdenes.fax,                             "
                + "       tblDctosOrdenes.contacto,                        "
                + "       tblDctosOrdenes.observacion,                     "
                + "       tblDctosOrdenes.idEstadoTx,                      "
                + "       tblDctosEstados.nombreEstado                     "
                + "ORDER BY tblDctosOrdenes.fechaOrden DESC,               "
                + "         tblDctosOrdenes.idLocal,                       "
                + "         tblDctosOrdenes.idTipoOrden,                   "
                + "         tblDctosOrdenes.idOrden DESC                   ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            while (rs.next()) {

                fachadaBean = new FachadaColaboraDctoOrdenBean();

                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("IdTipoOrden"));
                fachadaBean.setIdOrden(rs.getInt("IdOrden"));
                fachadaBean.setIdLog(rs.getInt("idLog"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setIdUsuario(rs.getDouble("idUsuario"));
                fachadaBean.setFechaOrden(rs.getString("fechaOrden").substring(0, 11));
                fachadaBean.setTipoDcto(rs.getString("tipoDcto"));
                fachadaBean.setDireccionDespacho(rs.getString("direccionDespacho"));
                fachadaBean.setEmail(rs.getString("email"));
                fachadaBean.setFax(rs.getString("fax"));
                fachadaBean.setContacto(rs.getString("contacto"));
                fachadaBean.setObservacion(rs.getString("observacion"));
                fachadaBean.setCantidadArticulos(rs.getInt("cantidadArticulos"));
                fachadaBean.setVrVentaSinIva(rs.getInt("vrVentaSinIva"));
                fachadaBean.setVrVentaConIva(rs.getInt("vrVentaConIva"));
                fachadaBean.setPesoTeoricoTotal(rs.getDouble("pesoTeoricoTotal"));
                fachadaBean.setIdEstadoTx(rs.getInt("idEstadoTx"));
                fachadaBean.setNombreEstado(rs.getString("nombreEstado"));

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

    // listaPedidoAdministradorFechaOrdenIdLocal MsAccess
    public Vector listaPedidoAdministradorFechaOrdenIdLocal() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();
        int idTipoOrdenCotiza = 10;

        // Variable de fachada de los datos
        FachadaColaboraDctoOrdenBean fachadaBean;

        Connection connection = null;

        String selectStr =
                "SELECT tblDctosOrdenes.idLocal,                         "
                + "       tblDctosOrdenes.idTipoOrden,                     "
                + "       tblDctosOrdenes.idOrden,                         "
                + "       tblDctosOrdenes.idLog,                           "
                + "       tblDctosOrdenes.idCliente,                       "
                + "       tblDctosOrdenes.idUsuario,                       "
                + "       tblDctosOrdenes.fechaOrden,                      "
                + "       tblDctosOrdenes.tipoDcto,                        "
                + "       tblDctosOrdenes.direccionDespacho,               "
                + "       tblDctosOrdenes.email,                           "
                + "       tblDctosOrdenes.fax,                             "
                + "       tblDctosOrdenes.contacto,                        "
                + "       tblDctosOrdenes.observacion,                     "
                + "       COUNT(tblDctosOrdenesDetalle.idPlu)              "
                + "                        AS cantidadArticulos,           "
                + "       SUM ( tblDctosOrdenesDetalle.cantidad          * "
                + "             tblDctosOrdenesDetalle.vrVentaUnitario   * "
                + "  (1 - (tblDctosOrdenesDetalle.porcentajeDscto/100)))   "
                + "                        AS vrVentaSinIva,               "
                + "       SUM ( tblDctosOrdenesDetalle.cantidad          * "
                + "             tblDctosOrdenesDetalle.vrVentaUnitario   * "
                + "  (1 - (tblDctosOrdenesDetalle.porcentajeDscto/100))  * "
                + "  (1 + (tblDctosOrdenesDetalle.porcentajeIva/100)))     "
                + "                        AS vrVentaConIva,               "
                + "       SUM(tblDctosOrdenesDetalle.pesoTeorico *         "
                + "           tblDctosOrdenesDetalle.cantidad)             "
                + "                        AS pesoTeoricoTotal,            "
                + "      tblDctosOrdenes.estado,                           "
                + "      tblDctosEstados.nombreEstado                      "
                + "FROM  tblDctosOrdenes ,                                 "
                + "      tblDctosOrdenesDetalle,                           "
                + "      tblDctosEstados                                   "
                + "WHERE tblDctosOrdenes.idOrden         =                 "
                + "      tblDctosOrdenesDetalle.idOrden                    "
                + "AND   tblDctosOrdenes.idTipoOrden     =                 "
                + "      tblDctosOrdenesDetalle.idTipoOrden                "
                + "AND   tblDctosOrdenes.idLocal         =                 "
                + "      tblDctosOrdenesDetalle.idLocal                    "
                + "AND  tblDctosOrdenes.idLog IN ( SELECT tblDctosOrdenes.idLog                                    "
                + "                                FROM   tblDctosOrdenes                                          "
                + "                                WHERE  tblDctosOrdenes.idLocal     = " + getIdLocal() + "  "
                + "                                AND    tblDctosOrdenes.idTipoOrden = " + idTipoOrdenCotiza + ") "
                + "AND   tblDctosOrdenes.idTipoOrden     = " + getIdTipoOrden() + "  "
                + "AND   tblDctosOrdenes.estado          =                                   "
                + "      tblDctosEstados.estado                                              "
                + "AND   ( tblDctosOrdenes.fechaOrden    = " + getFechaOrden() + "  "
                + "        OR tblDctosEstados.estado     = " + getEstado() + "   )           "
                + "GROUP BY tblDctosOrdenes.idLocal,                       "
                + "       tblDctosOrdenes.idTipoOrden,                     "
                + "       tblDctosOrdenes.idOrden,                         "
                + "       tblDctosOrdenes.idLog,                           "
                + "       tblDctosOrdenes.idCliente,                       "
                + "       tblDctosOrdenes.idUsuario,                       "
                + "       tblDctosOrdenes.fechaOrden,                      "
                + "       tblDctosOrdenes.tipoDcto,                        "
                + "       tblDctosOrdenes.direccionDespacho,               "
                + "       tblDctosOrdenes.email,                           "
                + "       tblDctosOrdenes.fax,                             "
                + "       tblDctosOrdenes.contacto,                        "
                + "       tblDctosOrdenes.observacion,                     "
                + "       tblDctosOrdenes.estado,                          "
                + "       tblDctosEstados.nombreEstado                     "
                + "ORDER BY tblDctosOrdenes.fechaOrden DESC,               "
                + "         tblDctosOrdenes.idLocal,                       "
                + "         tblDctosOrdenes.idTipoOrden,                   "
                + "         tblDctosOrdenes.idLog DESC                     ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            while (rs.next()) {

                fachadaBean = new FachadaColaboraDctoOrdenBean();

                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("IdTipoOrden"));
                fachadaBean.setIdOrden(rs.getInt("IdOrden"));
                fachadaBean.setIdLog(rs.getInt("idLog"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setIdUsuario(rs.getDouble("idUsuario"));
                fachadaBean.setFechaOrden(rs.getString("fechaOrden").substring(0, 11));
                fachadaBean.setTipoDcto(rs.getString("tipoDcto"));
                fachadaBean.setDireccionDespacho(rs.getString("direccionDespacho"));
                fachadaBean.setEmail(rs.getString("email"));
                fachadaBean.setFax(rs.getString("fax"));
                fachadaBean.setContacto(rs.getString("contacto"));
                fachadaBean.setObservacion(rs.getString("observacion"));
                fachadaBean.setCantidadArticulos(rs.getInt("cantidadArticulos"));
                fachadaBean.setVrVentaSinIva(rs.getInt("vrVentaSinIva"));
                fachadaBean.setVrVentaConIva(rs.getInt("vrVentaConIva"));
                fachadaBean.setPesoTeoricoTotal(rs.getDouble("pesoTeoricoTotal"));
                fachadaBean.setEstado(rs.getInt("estado"));
                fachadaBean.setNombreEstado(rs.getString("nombreEstado"));

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

    // listaPedidoPendientesTx MsAccess
    public Vector listaPedidoPendientesTx() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();
        int idTipoOrdenCotiza = 10;

        // Variable de fachada de los datos
        FachadaColaboraDctoOrdenBean fachadaBean;

        boolean validaOk = false;
        Connection connection = null;

        String selectStr =
                "SELECT tblDctosOrdenes.idLocal,                         "
                + "       tblDctosOrdenes.idTipoOrden,                     "
                + "       tblDctosOrdenes.idOrden,                         "
                + "       tblDctosOrdenes.idLog,                           "
                + "       tblDctosOrdenes.idCliente,                       "
                + "       tblDctosOrdenes.idUsuario,                       "
                + "       tblDctosOrdenes.fechaOrden,                      "
                + "       tblDctosOrdenes.tipoDcto,                        "
                + "       tblDctosOrdenes.direccionDespacho,               "
                + "       tblDctosOrdenes.email,                           "
                + "       tblDctosOrdenes.fax,                             "
                + "       tblDctosOrdenes.contacto,                        "
                + "       tblDctosOrdenes.observacion,                     "
                + "       COUNT(tblDctosOrdenesDetalle.idPlu)              "
                + "                        AS cantidadArticulos,           "
                + "       SUM ( tblDctosOrdenesDetalle.cantidad          * "
                + "             tblDctosOrdenesDetalle.vrVentaUnitario   * "
                + "  (1 - (tblDctosOrdenesDetalle.porcentajeDscto/100)))   "
                + "                        AS vrVentaSinIva,               "
                + "       SUM ( tblDctosOrdenesDetalle.cantidad          * "
                + "             tblDctosOrdenesDetalle.vrVentaUnitario   * "
                + "  (1 - (tblDctosOrdenesDetalle.porcentajeDscto/100))  * "
                + "  (1 + (tblDctosOrdenesDetalle.porcentajeIva/100)))     "
                + "                        AS vrVentaConIva,               "
                + "       SUM(tblDctosOrdenesDetalle.pesoTeorico *         "
                + "           tblDctosOrdenesDetalle.cantidad)             "
                + "                        AS pesoTeoricoTotal,            "
                + "      tblDctosOrdenes.idEstadoTx,                       "
                + "      tblDctosEstados.nombreEstado,                     "
                + "      tblDctosOrdenes.idRazon                           "
                + "FROM  tblDctosOrdenes ,                                 "
                + "      tblDctosOrdenesDetalle,                           "
                + "      tblDctosEstados                                   "
                + "WHERE tblDctosOrdenes.idOrden         =                 "
                + "      tblDctosOrdenesDetalle.idOrden                    "
                + "AND   tblDctosOrdenes.idTipoOrden     =                 "
                + "      tblDctosOrdenesDetalle.idTipoOrden                "
                + "AND   tblDctosOrdenes.idLocal         =                 "
                + "      tblDctosOrdenesDetalle.idLocal                    "
                + "AND  tblDctosOrdenes.idLog IN ( SELECT tblDctosOrdenes.idLog                                    "
                + "                                FROM   tblDctosOrdenes                                          "
                + "                                WHERE  tblDctosOrdenes.idLocal     = " + getIdLocal() + "  "
                + "                                AND    tblDctosOrdenes.idTipoOrden = " + idTipoOrdenCotiza + ") "
                + "AND   tblDctosOrdenes.idTipoOrden     = " + getIdTipoOrden() + "  "
                + "AND   tblDctosOrdenes.idEstadoTx      =                                   "
                + "               tblDctosEstados.estado                                     "
                + "AND   ( tblDctosOrdenes.fechaOrden    = " + getFechaOrden() + "  "
                + "        OR tblDctosOrdenes.idEstadoTx IN (1))                             "
                + "GROUP BY tblDctosOrdenes.idLocal,                       "
                + "       tblDctosOrdenes.idTipoOrden,                     "
                + "       tblDctosOrdenes.idOrden,                         "
                + "       tblDctosOrdenes.idLog,                           "
                + "       tblDctosOrdenes.idCliente,                       "
                + "       tblDctosOrdenes.idUsuario,                       "
                + "       tblDctosOrdenes.fechaOrden,                      "
                + "       tblDctosOrdenes.tipoDcto,                        "
                + "       tblDctosOrdenes.direccionDespacho,               "
                + "       tblDctosOrdenes.email,                           "
                + "       tblDctosOrdenes.fax,                             "
                + "       tblDctosOrdenes.contacto,                        "
                + "       tblDctosOrdenes.observacion,                     "
                + "       tblDctosOrdenes.idEstadoTx,                      "
                + "       tblDctosEstados.nombreEstado,                    "
                + "       tblDctosOrdenes.idRazon                          "
                + "ORDER BY tblDctosOrdenes.fechaOrden DESC,               "
                + "         tblDctosOrdenes.idLocal,                       "
                + "         tblDctosOrdenes.idTipoOrden,                   "
                + "         tblDctosOrdenes.idLog DESC                     ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            while (rs.next()) {

                fachadaBean = new FachadaColaboraDctoOrdenBean();

                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("IdTipoOrden"));
                fachadaBean.setIdOrden(rs.getInt("IdOrden"));
                fachadaBean.setIdLog(rs.getInt("idLog"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setIdUsuario(rs.getDouble("idUsuario"));
                fachadaBean.setFechaOrden(rs.getString("fechaOrden").substring(0, 11));
                fachadaBean.setTipoDcto(rs.getString("tipoDcto"));
                fachadaBean.setDireccionDespacho(rs.getString("direccionDespacho"));
                fachadaBean.setEmail(rs.getString("email"));
                fachadaBean.setFax(rs.getString("fax"));
                fachadaBean.setContacto(rs.getString("contacto"));
                fachadaBean.setObservacion(rs.getString("observacion"));
                fachadaBean.setCantidadArticulos(rs.getInt("cantidadArticulos"));
                fachadaBean.setVrVentaSinIva(rs.getInt("vrVentaSinIva"));
                fachadaBean.setVrVentaConIva(rs.getInt("vrVentaConIva"));
                fachadaBean.setPesoTeoricoTotal(rs.getDouble("pesoTeoricoTotal"));
                fachadaBean.setIdEstadoTx(rs.getInt("idEstadoTx"));
                fachadaBean.setNombreEstado(rs.getString("nombreEstado"));
                fachadaBean.setIdRazon(rs.getString("idRazon"));

                //
                if (fachadaBean.getIdRazon() != null) {
                    contenedor.add(fachadaBean);
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
            return contenedor;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {

            // Cierra la conexion
            jdbcAccess.cleanup(connection, selectStatement, null);
            return contenedor;

        }
    }

    // listaPedidoRuta MsAccess
    public Vector listaPedidoRuta(String xIdRuta) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();
        int idTipoOrdenCotiza = 10;

        // Variable de fachada de los datos
        FachadaColaboraDctoOrdenBean fachadaBean;

        boolean validaOk = false;
        Connection connection = null;

        String selectStr =
                "SELECT tblDctosOrdenes.idLocal,                         "
                + "       tblDctosOrdenes.idTipoOrden,                     "
                + "       tblDctosOrdenes.idOrden,                         "
                + "       tblDctosOrdenes.idLog,                           "
                + "       tblDctosOrdenes.idCliente,                       "
                + "       tblDctosOrdenes.idUsuario,                       "
                + "       tblDctosOrdenes.fechaOrden,                      "
                + "       tblDctosOrdenes.tipoDcto,                        "
                + "       tblDctosOrdenes.direccionDespacho,               "
                + "       tblDctosOrdenes.email,                           "
                + "       tblDctosOrdenes.fax,                             "
                + "       tblDctosOrdenes.contacto,                        "
                + "       tblDctosOrdenes.observacion,                     "
                + "       COUNT(tblDctosOrdenesDetalle.idPlu)              "
                + "                        AS cantidadArticulos,           "
                + "       SUM ( tblDctosOrdenesDetalle.cantidad          * "
                + "             tblDctosOrdenesDetalle.vrVentaUnitario )   "
                + "                        AS vrVentaSinIva,               "
                + "       SUM ( tblDctosOrdenesDetalle.cantidad          * "
                + "             tblDctosOrdenesDetalle.vrVentaUnitario   * "
                + "  (1 + (tblDctosOrdenesDetalle.porcentajeIva/100)))     "
                + "                        AS vrVentaConIva,               "
                + "       SUM(tblDctosOrdenesDetalle.pesoTeorico *         "
                + "           tblDctosOrdenesDetalle.cantidad)             "
                + "                        AS pesoTeoricoTotal,            "
                + "      tblDctosOrdenes.idEstadoTx,                       "
                + "      tblDctosEstados.nombreEstado,                     "
                + "      tblDctosOrdenes.idRazon                           "
                + "FROM  tblDctosOrdenes ,                                 "
                + "      tblDctosOrdenesDetalle,                           "
                + "      tblDctosEstados                                   "
                + "WHERE tblDctosOrdenes.idOrden         =                 "
                + "      tblDctosOrdenesDetalle.idOrden                    "
                + "AND   tblDctosOrdenes.idTipoOrden     =                 "
                + "      tblDctosOrdenesDetalle.idTipoOrden                "
                + "AND   tblDctosOrdenes.idLocal         =                 "
                + "      tblDctosOrdenesDetalle.idLocal                    "
                + "AND   tblDctosOrdenes.idCliente IN ( SELECT tblTerceros.idCliente                                "
                + "                                    FROM tblTerceros                                             "
                + "                                    WHERE tblTerceros.idRuta IN (" + xIdRuta + ") )              "
                + "AND   tblDctosOrdenes.idLog IN ( SELECT tblDctosOrdenes.idLog                                    "
                + "                                 FROM   tblDctosOrdenes                                          "
                + "                                 WHERE  tblDctosOrdenes.idTipoOrden = " + getIdTipoOrden() + ") "
                + "AND   tblDctosOrdenes.idTipoOrden     = " + getIdTipoOrden() + "  "
                + "AND   tblDctosOrdenes.idEstadoTx      =                                   "
                + "               tblDctosEstados.estado                                     "
                + "AND   ( tblDctosOrdenes.idEstadoTx IN (1))                                "
                + "GROUP BY tblDctosOrdenes.idLocal,                       "
                + "       tblDctosOrdenes.idTipoOrden,                     "
                + "       tblDctosOrdenes.idOrden,                         "
                + "       tblDctosOrdenes.idLog,                           "
                + "       tblDctosOrdenes.idCliente,                       "
                + "       tblDctosOrdenes.idUsuario,                       "
                + "       tblDctosOrdenes.fechaOrden,                      "
                + "       tblDctosOrdenes.tipoDcto,                        "
                + "       tblDctosOrdenes.direccionDespacho,               "
                + "       tblDctosOrdenes.email,                           "
                + "       tblDctosOrdenes.fax,                             "
                + "       tblDctosOrdenes.contacto,                        "
                + "       tblDctosOrdenes.observacion,                     "
                + "       tblDctosOrdenes.idEstadoTx,                      "
                + "       tblDctosEstados.nombreEstado,                    "
                + "       tblDctosOrdenes.idRazon                          "
                + "ORDER BY tblDctosOrdenes.fechaOrden DESC,               "
                + "         tblDctosOrdenes.idLocal,                       "
                + "         tblDctosOrdenes.idTipoOrden,                   "
                + "         tblDctosOrdenes.idLog DESC                     ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            while (rs.next()) {

                fachadaBean = new FachadaColaboraDctoOrdenBean();

                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("IdTipoOrden"));
                fachadaBean.setIdOrden(rs.getInt("IdOrden"));
                fachadaBean.setIdLog(rs.getInt("idLog"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setIdUsuario(rs.getDouble("idUsuario"));
                fachadaBean.setFechaOrden(rs.getString("fechaOrden").substring(0, 11));
                fachadaBean.setTipoDcto(rs.getString("tipoDcto"));
                fachadaBean.setDireccionDespacho(rs.getString("direccionDespacho"));
                fachadaBean.setEmail(rs.getString("email"));
                fachadaBean.setFax(rs.getString("fax"));
                fachadaBean.setContacto(rs.getString("contacto"));
                fachadaBean.setObservacion(rs.getString("observacion"));
                fachadaBean.setCantidadArticulos(rs.getInt("cantidadArticulos"));
                fachadaBean.setVrVentaSinIva(rs.getInt("vrVentaSinIva"));
                fachadaBean.setVrVentaConIva(rs.getInt("vrVentaConIva"));
                fachadaBean.setPesoTeoricoTotal(rs.getDouble("pesoTeoricoTotal"));
                fachadaBean.setIdEstadoTx(rs.getInt("idEstadoTx"));
                fachadaBean.setNombreEstado(rs.getString("nombreEstado"));
                fachadaBean.setIdRazon(rs.getString("idRazon"));

                //
                if (fachadaBean.getIdRazon() != null) {
                    contenedor.add(fachadaBean);
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
            return contenedor;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {

            // Cierra la conexion
            jdbcAccess.cleanup(connection, selectStatement, null);
            return contenedor;

        }
    }

    // listaCotizacionesFecha MsAccess
    public Vector listaCotizacionesFecha() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        // Variable de fachada de los datos
        FachadaColaboraDctoOrdenBean fachadaBean;

        boolean validaOk = false;
        Connection connection = null;

        String selectStr =
                "SELECT tblDctosOrdenes.idLocal,                          "
                + "       tblDctosOrdenes.idTipoOrden,                      "
                + "       tblDctosOrdenes.idOrden,                          "
                + "       tblDctosOrdenes.idLog,                            "
                + "       tblDctosOrdenes.idCliente,                        "
                + "       tblDctosOrdenes.idUsuario,                        "
                + "       tblDctosOrdenes.fechaOrden,                       "
                + "       tblDctosOrdenes.tipoDcto,                         "
                + "       tblDctosOrdenes.direccionDespacho,                "
                + "       tblDctosOrdenes.email,                            "
                + "       tblDctosOrdenes.fax,                              "
                + "       tblDctosOrdenes.contacto,                         "
                + "       tblDctosOrdenes.observacion,                      "
                + "       COUNT(tblDctosOrdenesDetalle.idPlu)               "
                + "                        AS cantidadArticulos,            "
                + "       SUM ( tblDctosOrdenesDetalle.cantidad          *  "
                + "             tblDctosOrdenesDetalle.vrVentaUnitario   *  "
                + "  (1 - (tblDctosOrdenesDetalle.porcentajeDscto/100)))    "
                + "                        AS vrVentaSinIva,                "
                + "       SUM ( tblDctosOrdenesDetalle.cantidad          *  "
                + "             tblDctosOrdenesDetalle.vrVentaUnitario   *  "
                + "  (1 - (tblDctosOrdenesDetalle.porcentajeDscto/100))  *  "
                + "  (1 + (tblDctosOrdenesDetalle.porcentajeIva/100)))      "
                + "                        AS vrVentaConIva,                "
                + "       SUM(tblDctosOrdenesDetalle.pesoTeorico *          "
                + "           tblDctosOrdenesDetalle.cantidad)              "
                + "                        AS pesoTeoricoTotal              "
                + "FROM  tblDctosOrdenes ,                                  "
                + "      tblDctosOrdenesDetalle                             "
                + "WHERE tblDctosOrdenes.idOrden         =                  "
                + "      tblDctosOrdenesDetalle.idOrden                     "
                + "AND   tblDctosOrdenes.idTipoOrden     =                  "
                + "      tblDctosOrdenesDetalle.idTipoOrden                 "
                + "AND   tblDctosOrdenes.idLocal         =                  "
                + "      tblDctosOrdenesDetalle.idLocal                     "
                + "AND   tblDctosOrdenes.idCliente                  = ( ? ) "
                + "AND   tblDctosOrdenes.idUsuario                  = ( ? ) "
                + "AND   tblDctosOrdenes.idTipoOrden                = ( ? ) "
                + "AND   CDATE(MID(tblDctosOrdenes.fechaOrden,1,10))= #" + getFechaOrden() + "# "
                + "AND   tblDctosOrdenes.idLog NOT IN (                     "
                + "      SELECT tblDctosOrdenes.idLog                       "
                + "      FROM tblDctosOrdenes                               "
                + "      WHERE tblDctosOrdenes.idCliente            = ( ? ) "
                + "      AND   tblDctosOrdenes.idUsuario            = ( ? ) "
                + "      AND   tblDctosOrdenes.idTipoOrden          =   9 ) "
                + "GROUP BY tblDctosOrdenes.idLocal,                        "
                + "       tblDctosOrdenes.idTipoOrden,                      "
                + "       tblDctosOrdenes.idOrden,                          "
                + "       tblDctosOrdenes.idLog,                            "
                + "       tblDctosOrdenes.idCliente,                        "
                + "       tblDctosOrdenes.idUsuario,                        "
                + "       tblDctosOrdenes.fechaOrden,                       "
                + "       tblDctosOrdenes.tipoDcto,                         "
                + "       tblDctosOrdenes.direccionDespacho,                "
                + "       tblDctosOrdenes.email,                            "
                + "       tblDctosOrdenes.fax,                              "
                + "       tblDctosOrdenes.contacto,                         "
                + "       tblDctosOrdenes.observacion                       ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            //inicializa los valores de los parametros
            selectStatement.setString(1, getIdCliente());
            selectStatement.setDouble(2, getIdUsuario());
            selectStatement.setInt(3, getIdTipoOrden());
            selectStatement.setString(4, getIdCliente());
            selectStatement.setDouble(5, getIdUsuario());

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            while (rs.next()) {

                fachadaBean = new FachadaColaboraDctoOrdenBean();

                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("IdTipoOrden"));
                fachadaBean.setIdOrden(rs.getInt("IdOrden"));
                fachadaBean.setIdLog(rs.getInt("idLog"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setIdUsuario(rs.getDouble("idUsuario"));
                fachadaBean.setFechaOrden(rs.getString("fechaOrden").substring(0, 11));
                fachadaBean.setTipoDcto(rs.getString("tipoDcto"));
                fachadaBean.setDireccionDespacho(rs.getString("direccionDespacho"));
                fachadaBean.setEmail(rs.getString("email"));
                fachadaBean.setFax(rs.getString("fax"));
                fachadaBean.setContacto(rs.getString("contacto"));
                fachadaBean.setObservacion(rs.getString("observacion"));
                fachadaBean.setCantidadArticulos(rs.getInt("cantidadArticulos"));
                fachadaBean.setVrVentaSinIva(rs.getInt("vrVentaSinIva"));
                fachadaBean.setVrVentaConIva(rs.getInt("vrVentaConIva"));
                fachadaBean.setPesoTeoricoTotal(rs.getDouble("pesoTeoricoTotal"));

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

    // listaHistoriaPedido
    public Vector listaHistoriaPedido() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        // Variable de fachada de los datos
        FachadaColaboraDctoOrdenBean fachadaBean;

        Connection connection = null;

        String selectStr =
                "SELECT tblDctosOrdenes.idLocal,                           "
                + "       tblDctosOrdenes.idTipoOrden,                       "
                + "       tblDctosOrdenes.idOrden,                           "
                + "       tblDctosOrdenes.idLog,                             "
                + "       tblDctos.idDcto,                                   "
                + "       tblDctos.indicador,                                "
                + "       MIN(tblDctos.vrRteFuente) AS vrCostoRteFuente,     "
                + "       MIN(tblDctos.vrDescuento) AS vrCostoDescuento,     "
                + "       MIN(tblDctos.vrIva) AS vrCostoIva,                 "
                + "       MIN(tblDctos.vrBase) AS vrCostoBase,               "
                + "       MAX(tblDctos.idDctoNitCC) AS idDctoNitCC,          "
                + "       MAX(tblDctosOrdenes.idCliente) AS idCliente,       "
                + "       MAX(tblDctosOrdenes.idUsuario) AS idUsuario,       "
                + "       MAX(tblDctosOrdenes.fechaOrden) AS fechaOrden,     "
                + "       MAX(tblDctosOrdenes.tipoDcto) AS tipoDcto,         "
                + "       MAX(tblDctosOrdenes.direccionDespacho)             "
                + "                                  AS direccionDespacho,   "
                + "       MAX(tblDctosOrdenes.email) AS email,               "
                + "       MAX(tblDctosOrdenes.fax) AS fax,                   "
                + "       MAX(tblDctosOrdenes.contacto) AS contacto,         "
                + "       MAX(tblDctosOrdenes.observacion) AS observacion,   "
                + "       COUNT(tblDctosOrdenesDetalle.idPlu)                "
                + "                              AS cantidadArticulos,       "
                + "       SUM ( tblDctosOrdenesDetalle.cantidad          *   "
                + "               tblDctosOrdenesDetalle.vrVentaUnitario )   "
                + "                              AS vrVentaConIva,           "
                + "       SUM ( tblDctosOrdenesDetalle.cantidad          *   "
                + "             tblDctosOrdenesDetalle.vrVentaUnitario   /   "
                + "        (1 + (tblDctosOrdenesDetalle.porcentajeIva/100))) "
                + "                              AS vrVentaSinIva,           "
                + "             SUM(tblDctosOrdenesDetalle.pesoTeorico *     "
                + "                     tblDctosOrdenesDetalle.cantidad)     "
                + "                                    AS pesoTeoricoTotal,  "
                + "       MAX(tblDctosOrdenes.idEstadoTx)   AS idEstadoTx,   "
                + "       MAX(tblDctosEstados.nombreEstado) AS nombreEstado, "
                + "       SUM ( tblDctosOrdenesDetalle.cantidad          *   "
                + "               tblDctosOrdenesDetalle.vrCosto )           "
                + "                              AS vrCostoSinIva,           "
                + "       SUM ( tblDctosOrdenesDetalle.cantidad          *   "
                + "             tblDctosOrdenesDetalle.vrCosto           *   "
                + "        (1 + (tblDctosOrdenesDetalle.porcentajeIva/100))) "
                + "                              AS   vrCostoConIva          "
                + "FROM   tblDctos                                           "
                + "INNER JOIN tblDctosOrdenes                                "
                + "ON    tblDctos.IDLOCAL            =                       "
                + "                                 tblDctosOrdenes.IDLOCAL  "
                + "AND   tblDctos.IDTIPOORDEN        =                       "
                + "                             tblDctosOrdenes.IDTIPOORDEN  "
                + "AND   tblDctos.IDORDEN            =                       "
                + "                                 tblDctosOrdenes.IDORDEN  "
                + "INNER JOIN tblDctosOrdenesDetalle                         "
                + "ON    tblDctosOrdenes.IDLOCAL     =                       "
                + "                          tblDctosOrdenesDetalle.IDLOCAL  "
                + "AND   tblDctosOrdenes.IDTIPOORDEN =                       "
                + "                      tblDctosOrdenesDetalle.IDTIPOORDEN  "
                + "AND   tblDctosOrdenes.IDORDEN     =                       "
                + "                          tblDctosOrdenesDetalle.IDORDEN  "
                + "INNER JOIN tblDctosEstados                                "
                + "ON tblDctosOrdenes.idEstadoTx     =                       "
                + "                                  tblDctosEstados.ESTADO  "
                + "WHERE tblDctosOrdenes.idLocal     =                       "
                + getIdLocal() + "                                           "
                + "AND   tblDctosOrdenes.idTipoOrden =                       "
                + getIdTipoOrden() + "                                       "
                + "AND   tblDctosOrdenes.idCliente   =                      '"
                + getIdCliente() + "'                                        "
                + "AND tblDctosOrdenes.fechaOrden    =                      '"
                + getFechaOrdenSqlServer() + "'                              "
                + "AND tblDctosOrdenes.idEstadoTx    =                       "
                + getIdEstadoTx() + "                                        "
                + "GROUP BY tblDctosOrdenes.idLocal,                         "
                + "         tblDctosOrdenes.idTipoOrden,                     "
                + "         tblDctosOrdenes.idOrden,                         "
                + "         tblDctosOrdenes.idLog,                           "
                + "         tblDctos.idDcto,                                 "
                + "         tblDctos.indicador                               "
                + "ORDER BY tblDctosOrdenes.idOrden DESC                     ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            while (rs.next()) {

                fachadaBean = new FachadaColaboraDctoOrdenBean();

                //
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("IdTipoOrden"));
                fachadaBean.setIdOrden(rs.getInt("IdOrden"));
                fachadaBean.setIdLog(rs.getInt("idLog"));
                fachadaBean.setIdDcto(rs.getInt("idDcto"));
                fachadaBean.setIndicador(rs.getInt("indicador"));
                fachadaBean.setVrCostoRteFuente(
                        rs.getDouble("vrCostoRteFuente"));
                fachadaBean.setVrCostoDescuento(
                        rs.getDouble("vrCostoDescuento"));
                fachadaBean.setVrCostoIva(rs.getDouble("vrCostoIva"));
                fachadaBean.setVrCostoBase(rs.getDouble("vrCostoBase"));
                fachadaBean.setIdDctoNitCC(rs.getString("idDctoNitCC"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setIdUsuario(rs.getDouble("idUsuario"));
                fachadaBean.setFechaOrden(
                        rs.getString("fechaOrden").substring(0, 11));
                fachadaBean.setTipoDcto(rs.getString("tipoDcto"));
                fachadaBean.setDireccionDespacho(
                        rs.getString("direccionDespacho"));
                fachadaBean.setEmail(rs.getString("email"));
                fachadaBean.setFax(rs.getString("fax"));
                fachadaBean.setContacto(rs.getString("contacto"));
                fachadaBean.setObservacion(rs.getString("observacion"));
                fachadaBean.setCantidadArticulos(
                        rs.getInt("cantidadArticulos"));
                fachadaBean.setVrVentaSinIva(rs.getInt("vrVentaSinIva"));
                fachadaBean.setVrVentaConIva(rs.getInt("vrVentaConIva"));
                fachadaBean.setPesoTeoricoTotal(
                        rs.getDouble("pesoTeoricoTotal"));
                fachadaBean.setIdEstadoTx(rs.getInt("idEstadoTx"));
                fachadaBean.setNombreEstado(rs.getString("nombreEstado"));
                fachadaBean.setVrCostoConIva(rs.getDouble("vrCostoConIva"));
                fachadaBean.setVrCostoSinIva(rs.getDouble("vrCostoSinIva"));

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

    // listaResurtidoLocal
    public Vector listaResurtidoLocal() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        // Variable de fachada de los datos
        FachadaColaboraDctoOrdenBean fachadaBean;

        Connection connection = null;

        //
        String selectStr =
                " SELECT tblAgendaLogVisitas.IDLOG,          "
                + "        tblAgendaLogVisitas.FECHAVISITA   "
                + "                          AS fechaOrden,  "
                + "        tmpResurtido.IDLOCAL,             "
                + "        COUNT(*) AS cantidadArticulos,    "
                + "        tblLocales.nombreLocal            "
                + " FROM   tblAgendaLogVisitas               "
                + " INNER JOIN tmpResurtido                  "
                + " ON tblAgendaLogVisitas.IDLOG =           "
                + "                       tmpResurtido.IDLOG "
                + " INNER JOIN tblLocales                    "
                + " ON tmpResurtido.IDLOCAL      =           "
                + "                       tblLocales.idLocal "
                + " GROUP BY tblAgendaLogVisitas.IDLOG,      "
                + "          tblAgendaLogVisitas.FECHAVISITA,"
                + "          tmpResurtido.IDLOCAL,           "
                + "          tblLocales.nombreLocal          "
                + " ORDER BY tblAgendaLogVisitas.IDLOG,      "
                + "          tblLocales.nombreLocal          ";

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
                fachadaBean = new FachadaColaboraDctoOrdenBean();

                //
                fachadaBean.setIdLog(rs.getInt("idLog"));
                fachadaBean.setFechaOrden(rs.getString("fechaOrden"));
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setCantidadArticulos(
                        rs.getInt("cantidadArticulos"));
                fachadaBean.setNombreLocal(rs.getString("nombreLocal"));

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
