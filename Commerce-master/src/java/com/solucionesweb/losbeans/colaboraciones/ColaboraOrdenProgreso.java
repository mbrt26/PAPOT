package com.solucionesweb.losbeans.colaboraciones;

// Importa la clase helper JDBCAccess que contiene los accesos a la base de datos
import com.solucionesweb.lasayudas.JDBCAccess;

// Importa el bean de utilidades
import com.solucionesweb.losbeans.utilidades.IConstantes;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaDctoOrdenProgreso;

// Importa los paquetes de java
import java.sql.*;
import java.io.*;
import java.util.*;
import java.io.IOException.*;

// Importa los paquetes de javax
import javax.naming.*;

public class ColaboraOrdenProgreso extends FachadaDctoOrdenProgreso
        implements Serializable, IConstantes {

    // Nombre JNDI del Data Source que esta clase requiere
    private static final String DATA_SOURCE_NAME = "CommerceAccess";
    // Clase helper usada para acceder a la base de datos
    private JDBCAccess jdbcAccess;

    // Metodo constructor por defecto sin parametros
    public ColaboraOrdenProgreso() {

        // Al instanciar el Bean establece la conexion a la base de datos
        jdbcAccess = new JDBCAccess(DATA_SOURCE_NAME);
    }

    // listaOT
    public Vector listaOT(int xIdLog) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        //
        String selectStr =
                "  SELECT tblDctosOrdenesProgreso.idLocal,             "
                + "         tblDctosOrdenesProgreso.idTipoOrden,       "
                + "         tblDctosOrdenesProgreso.idOrden,           "
                + "         tblDctosOrdenesProgreso.item,              "
                + "         tblDctosOrdenesProgreso.idOperacion,       "
                + "         tblDctosOrdenesProgreso.idOperario,        "
                + "         tblDctosOrdenesProgreso.cantidadPerdida,   "
                + "         tblDctosOrdenesProgreso.cantidadTerminada, "
                + "         tblDctosOrdenesProgreso.pesoPerdido,       "
                + "         tblDctosOrdenesProgreso.pesoTerminado,     "
                + "         tblDctosOrdenesProgreso.fechaInicio,       "
                + "         tblDctosOrdenesProgreso.fechaFin,          "
                + "         tblDctosOrdenesProgreso.idCausa,           "
                + "         tblDctosOrdenesProgreso.estado,            "
                + "         tmpOPE.nombreTercero AS nombreOperario,    "
                + "         tblDctosOrdenesProgreso.cantidadPedida,    "
                + "         tblDctosOrdenesProgreso.pesoPedido,        "
                + "         tblDctosOrdenesProgreso.idControl,         "
                + "         tblDctosOrdenesProgreso.idPlu,             "
                + "         tblDctosOrdenesProgreso.pesoTara,          "
                + "         tblDctosOrdenesProgreso.idOrdenCruce,      "                
                + "         tblDctosOrdenesProgreso.idDctoNitCC        "                
                + "  FROM   tblDctosOrdenes                            "
                + "  INNER JOIN tblDctosOrdenesProgreso                "
                + "  ON  tblDctosOrdenes.idLocal     =                 "
                + "                  tblDctosOrdenesProgreso.idLocal   "
                + "  AND tblDctosOrdenes.idTipoOrden =                 "
                + "              tblDctosOrdenesProgreso.idTipoOrden   "
                + "  AND tblDctosOrdenes.idOrden     =                 "
                + "                  tblDctosOrdenesProgreso.idOrden   "
                + "  INNER JOIN (SELECT tmpTER.idTercero               "
                + "             ,MAX(tmpTER.nombreTercero)             "
                + "                                 AS nombreTercero   "
                + "              FROM                                  "
                + "               ( SELECT ctrlUsuarios.idUsuario      "
                + "                                    AS idTercero    "
                + "                     ,ctrlUsuarios.nombreUsuario    "
                + "                                AS nombreTercero    "
                + "                FROM ctrlUsuarios                   "
                + "                UNION                               "
                + "                SELECT tblTerceros.idCliente        "
                + "                                   AS idTercero     "
                + "                     ,tblTerceros.nombreTercero     "
                + "                               AS nombreTercero     "
                + "                FROM tblTerceros ) AS tmpTER        "
                + "          GROUP BY tmpTER.idTercero ) AS tmpOPE     "
                + "  ON tblDctosOrdenesProgreso.idOperario    =        "
                + "                              tmpOPE.idTercero      "
                + " WHERE tblDctosOrdenes.idLocal             =        "
                + getIdLocal() + "                                     "
                + " AND   tblDctosOrdenes.idTipoOrden         =        "
                + getIdTipoOrden() + "                                 "
                + " AND   tblDctosOrdenes.idLog               =        "
                + xIdLog + "                                           "
                + " AND   tblDctosOrdenesProgreso.idOperacion =        "
                + getIdOperacion() + "                                 "
                + " AND   tblDctosOrdenesProgreso.itemPadre   =        "
                + getItemPadre() + "                                   "
                + "  AND  tblDctosOrdenesProgreso.idControlTipo != 2   "
                + "  ORDER BY tblDctosOrdenesProgreso.idLocal,         "
                + "           tblDctosOrdenesProgreso.idTipoOrden,     "
                + "           tblDctosOrdenesProgreso.idOrden,         "
                + "           tblDctosOrdenesProgreso.item             ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaDctoOrdenProgreso fachadaBean;

            //
            while (rs.next()) {

                //
                fachadaBean = new FachadaDctoOrdenProgreso();

                //
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setIdOrden(rs.getInt("idOrden"));
                fachadaBean.setItem(rs.getInt("item"));
                fachadaBean.setIdOperacion(rs.getInt("idOperacion"));
                fachadaBean.setIdOperario(rs.getDouble("idOperario"));
                fachadaBean.setCantidadPerdida(rs.getDouble("cantidadPerdida"));
                fachadaBean.setCantidadTerminada(
                        rs.getDouble("cantidadTerminada"));
                fachadaBean.setPesoPerdido(rs.getDouble("pesoPerdido"));
                fachadaBean.setPesoTerminado(rs.getDouble("pesoTerminado"));
                fachadaBean.setFechaInicio(rs.getString("fechaInicio"));
                fachadaBean.setFechaFin(rs.getString("fechaFin"));
                fachadaBean.setIdCausa(rs.getInt("idCausa"));
                fachadaBean.setEstado(rs.getInt("estado"));
                fachadaBean.setNombreOperario(rs.getString("nombreOperario"));
                fachadaBean.setCantidadPedida(rs.getDouble("cantidadPedida"));
                fachadaBean.setPesoPedido(rs.getDouble("pesoPedido"));
                fachadaBean.setIdControl(rs.getInt("idControl"));
                fachadaBean.setIdPlu(rs.getInt("idPlu"));
                fachadaBean.setPesoTara(rs.getDouble("pesoTara"));
                fachadaBean.setIdOrdenCruce(rs.getInt("idOrdenCruce"));                
                fachadaBean.setIdDctoNitCC(rs.getString("idDctoNitCC"));                

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

    // listaSalidaOT
    public Vector listaSalidaOT(int xIdLog) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        //
        String selectStr =
                "  SELECT tblDctosOrdenesProgreso.idLocal,             "
                + "         tblDctosOrdenesProgreso.idTipoOrden,       "
                + "         tblDctosOrdenesProgreso.idOrden,           "
                + "         tblDctosOrdenesProgreso.item,              "
                + "         tblDctosOrdenesProgreso.idOperacion,       "
                + "         tblDctosOrdenesProgreso.idOperario,        "
                + "         tblDctosOrdenesProgreso.cantidadPerdida,   "
                + "         tblDctosOrdenesProgreso.cantidadTerminada, "
                + "         tblDctosOrdenesProgreso.pesoPerdido,       "
                + "         tblDctosOrdenesProgreso.pesoTerminado,     "
                + "         tblDctosOrdenesProgreso.fechaInicio,       "
                + "         tblDctosOrdenesProgreso.fechaFin,          "
                + "         tblDctosOrdenesProgreso.idCausa,           "
                + "         tblDctosOrdenesProgreso.estado,            "
                + "         tmpOPE.nombreTercero AS nombreOperario,    "
                + "         tblDctosOrdenesProgreso.cantidadPedida,    "
                + "         tblDctosOrdenesProgreso.pesoPedido,        "
                + "         tblDctosOrdenesProgreso.idControl,         "
                + "         tblDctosOrdenesProgreso.idPlu,             "
                + "         tblDctosOrdenesProgreso.pesoTara,          "                
                + "         tblDctosOrdenesProgreso.idDctoNitCC,       "                
                + "         tblDctosOrdenesProgreso.idOrdenCruce       "                                                
                + "  FROM   tblDctosOrdenes                            "
                + "  INNER JOIN tblDctosOrdenesProgreso                "
                + "  ON  tblDctosOrdenes.idLocal     =                 "
                + "                  tblDctosOrdenesProgreso.idLocal   "
                + "  AND tblDctosOrdenes.idTipoOrden =                 "
                + "              tblDctosOrdenesProgreso.idTipoOrden   "
                + "  AND tblDctosOrdenes.idOrden     =                 "
                + "                  tblDctosOrdenesProgreso.idOrden   "
                + "  INNER JOIN (SELECT tmpTER.idTercero               "
                + "             ,MAX(tmpTER.nombreTercero)             "
                + "                                 AS nombreTercero   "
                + "              FROM                                  "
                + "               ( SELECT ctrlUsuarios.idUsuario      "
                + "                                    AS idTercero    "
                + "                     ,ctrlUsuarios.nombreUsuario    "
                + "                                AS nombreTercero    "
                + "                FROM ctrlUsuarios                   "
                + "                UNION                               "
                + "                SELECT tblTerceros.idCliente        "
                + "                                   AS idTercero     "
                + "                     ,tblTerceros.nombreTercero     "
                + "                               AS nombreTercero     "
                + "                FROM tblTerceros ) AS tmpTER        "
                + "          GROUP BY tmpTER.idTercero ) AS tmpOPE     "
                + "  ON tblDctosOrdenesProgreso.idOperario    =        "
                + "                              tmpOPE.idTercero      "
                + " WHERE tblDctosOrdenes.idLocal             =        "
                + getIdLocal() + "                                     "
                + " AND   tblDctosOrdenes.idTipoOrden         =        "
                + getIdTipoOrden() + "                                 "
                + " AND   tblDctosOrdenes.idLog               =        "
                + xIdLog + "                                           "
                + " AND   tblDctosOrdenesProgreso.idOperacion =        "
                + getIdOperacion() + "                                 "
                + " AND   tblDctosOrdenesProgreso.itemPadre   =        "
                + getItemPadre() + "                                   "
                + "  AND  tblDctosOrdenesProgreso.idControlTipo  = 2   "
                + "  ORDER BY tblDctosOrdenesProgreso.idLocal,         "
                + "           tblDctosOrdenesProgreso.idTipoOrden,     "
                + "           tblDctosOrdenesProgreso.idOrden,         "
                + "           tblDctosOrdenesProgreso.item             ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaDctoOrdenProgreso fachadaBean;

            //
            while (rs.next()) {

                //
                fachadaBean = new FachadaDctoOrdenProgreso();

                //
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setIdOrden(rs.getInt("idOrden"));
                fachadaBean.setItem(rs.getInt("item"));
                fachadaBean.setIdOperacion(rs.getInt("idOperacion"));
                fachadaBean.setIdOperario(rs.getDouble("idOperario"));
                fachadaBean.setCantidadPerdida(rs.getDouble("cantidadPerdida"));
                fachadaBean.setCantidadTerminada(
                        rs.getDouble("cantidadTerminada"));
                fachadaBean.setPesoPerdido(rs.getDouble("pesoPerdido"));
                fachadaBean.setPesoTerminado(rs.getDouble("pesoTerminado"));
                fachadaBean.setFechaInicio(rs.getString("fechaInicio"));
                fachadaBean.setFechaFin(rs.getString("fechaFin"));
                fachadaBean.setIdCausa(rs.getInt("idCausa"));
                fachadaBean.setEstado(rs.getInt("estado"));
                fachadaBean.setNombreOperario(rs.getString("nombreOperario"));
                fachadaBean.setCantidadPedida(rs.getDouble("cantidadPedida"));
                fachadaBean.setPesoPedido(rs.getDouble("pesoPedido"));
                fachadaBean.setIdControl(rs.getInt("idControl"));
                fachadaBean.setIdPlu(rs.getInt("idPlu"));
                fachadaBean.setPesoTara(rs.getDouble("pesoTara"));
                fachadaBean.setIdDctoNitCC(rs.getString("idDctoNitCC"));                
                fachadaBean.setIdOrdenCruce(rs.getInt("idOrdenCruce"));
                
                
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

    // listaOTExterna
    public Vector listaOTExterna(int xIdLog) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        //
        String selectStr =
                "     SELECT tblDctosOrdenesProgreso.idLocal,              "
                + "          tblDctosOrdenesProgreso.idTipoOrden,          "
                + "          tblDctosOrdenesProgreso.idOrden,              "
                + "          tblDctosOrdenesProgreso.item,                 "
                + "          tblDctosOrdenesProgreso.idOperacion,          "
                + "          tblDctosOrdenesProgreso.idOperario,           "
                + "          tblDctosOrdenesProgreso.cantidadPerdida,      "
                + "          tblDctosOrdenesProgreso.cantidadTerminada,    "
                + "          tblDctosOrdenesProgreso.pesoPerdido,          "
                + "          tblDctosOrdenesProgreso.pesoTerminado,        "
                + "          tblDctosOrdenesProgreso.fechaInicio,          "
                + "          tblDctosOrdenesProgreso.fechaFin,             "
                + "          tblDctosOrdenesProgreso.idCausa,              "
                + "          tblDctosOrdenesProgreso.estado,               "
                + "          tmpOPE.nombreTercero AS nombreOperario,       "
                + "          tblDctosOrdenesProgreso.cantidadPedida,       "
                + "          tblDctosOrdenesProgreso.pesoPedido,           "
                + "          tblDctosOrdenesProgreso.idControl,            "
                + "          tblDctosOrdenesProgreso.idPlu,                "
                + "          ( SELECT                                      "
                + "              RTRIM(tblCategorias.nombreCategoria) +    "
                + "                                               ' ' +    "
                + "                    tblPlus.nombrePlu AS nombrePlu      "
                + "            FROM   tblCategorias                        "
                + "            INNER JOIN tblPlus                          "
                + "            ON tblCategorias.idLinea      =             "
                + "                                    tblPlus.idLinea     "
                + "            AND tblCategorias.IdCategoria =             "
                + "                                tblPlus.idCategoria     "
                + "            WHERE tblPlus.idPlu =                       "
                + "                     tblDctosOrdenesProgreso.idPlu )    "
                + "                                        AS nombrePlu,   "
                + "           (SELECT TOP 1                                "
                + "                       tblPlusFicha.referenciaCliente   "
                + "            FROM tblPlusFicha                           "
                + "            WHERE tblPlusFicha.idFicha =                "
                + "                             tblDctosOrdenes.idFicha )  "
                + "                                  AS referenciaCliente  "
                + "   FROM   tblDctosOrdenes                               "
                + "   INNER JOIN tblDctosOrdenesProgreso                   "
                + "   ON  tblDctosOrdenes.idLocal     =                    "
                + "                   tblDctosOrdenesProgreso.idLocal      "
                + "   AND tblDctosOrdenes.idTipoOrden =                    "
                + "               tblDctosOrdenesProgreso.idTipoOrden      "
                + "   AND tblDctosOrdenes.idOrden     =                    "
                + "                   tblDctosOrdenesProgreso.idOrden      "
                + "   INNER JOIN (SELECT tmpTER.idTercero                  "
                + "              ,MAX(tmpTER.nombreTercero)                "
                + "                                  AS nombreTercero      "
                + "               FROM                                     "
                + "                ( SELECT ctrlUsuarios.idUsuario         "
                + "                                     AS idTercero       "
                + "                      ,ctrlUsuarios.nombreUsuario       "
                + "                                 AS nombreTercero       "
                + "                 FROM ctrlUsuarios                      "
                + "                 UNION                                  "
                + "                 SELECT tblTerceros.idCliente           "
                + "                                    AS idTercero        "
                + "                      ,tblTerceros.nombreTercero        "
                + "                                AS nombreTercero        "
                + "                 FROM tblTerceros ) AS tmpTER           "
                + "           GROUP BY tmpTER.idTercero ) AS tmpOPE        "
                + "   ON tblDctosOrdenesProgreso.idOperario   =            "
                + "                               tmpOPE.idTercero         "
                + " WHERE tblDctosOrdenes.idLocal             =            "
                + getIdLocal() + "                                         "
                + " AND   tblDctosOrdenes.idTipoOrden         =            "
                + getIdTipoOrden() + "                                     "
                + " AND   tblDctosOrdenes.idLog               =            "
                + xIdLog + "                                               "
                + " AND   tblDctosOrdenesProgreso.idOperacion =            "
                + getIdOperacion() + "                                     "
                + " AND   tblDctosOrdenesProgreso.itemPadre   =            "
                + getItemPadre() + "                                       "
                + " AND  tblDctosOrdenesProgreso.idControlTipo =           "
                + getIdControlTipo() + "                                   "
                + "  ORDER BY tblDctosOrdenesProgreso.idLocal,             "
                + "           tblDctosOrdenesProgreso.idTipoOrden,         "
                + "           tblDctosOrdenesProgreso.idOrden,             "
                + "           tblDctosOrdenesProgreso.item                 ";


        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaDctoOrdenProgreso fachadaBean;

            //
            while (rs.next()) {

                //
                fachadaBean = new FachadaDctoOrdenProgreso();

                //
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setIdOrden(rs.getInt("idOrden"));
                fachadaBean.setItem(rs.getInt("item"));
                fachadaBean.setIdOperacion(rs.getInt("idOperacion"));
                fachadaBean.setIdOperario(rs.getDouble("idOperario"));
                fachadaBean.setCantidadPerdida(rs.getDouble("cantidadPerdida"));
                fachadaBean.setCantidadTerminada(
                        rs.getDouble("cantidadTerminada"));
                fachadaBean.setPesoPerdido(rs.getDouble("pesoPerdido"));
                fachadaBean.setPesoTerminado(rs.getDouble("pesoTerminado"));
                fachadaBean.setFechaInicio(rs.getString("fechaInicio"));
                fachadaBean.setFechaFin(rs.getString("fechaFin"));
                fachadaBean.setIdCausa(rs.getInt("idCausa"));
                fachadaBean.setEstado(rs.getInt("estado"));
                fachadaBean.setNombreOperario(rs.getString("nombreOperario"));
                fachadaBean.setCantidadPedida(rs.getDouble("cantidadPedida"));
                fachadaBean.setPesoPedido(rs.getDouble("pesoPedido"));
                fachadaBean.setIdControl(rs.getInt("idControl"));
                fachadaBean.setIdPlu(rs.getInt("idPlu"));
                fachadaBean.setNombrePlu(rs.getString("nombrePlu"));
                fachadaBean.setReferenciaCliente(
                        rs.getString("referenciaCliente"));

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

    // listaUnOTExterna
    public Vector listaUnOTExterna() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        //
        String selectStr =
                "     SELECT tblDctosOrdenesProgreso.idLocal,              "
                + "          tblDctosOrdenesProgreso.idTipoOrden,          "
                + "          tblDctosOrdenesProgreso.idOrden,              "
                + "          tblDctosOrdenesProgreso.item,                 "
                + "          tblDctosOrdenesProgreso.idOperacion,          "
                + "          tblDctosOrdenesProgreso.idOperario,           "
                + "          tblDctosOrdenesProgreso.cantidadPerdida,      "
                + "          tblDctosOrdenesProgreso.cantidadTerminada,    "
                + "          tblDctosOrdenesProgreso.pesoPerdido,          "
                + "          tblDctosOrdenesProgreso.pesoTerminado,        "
                + "          tblDctosOrdenesProgreso.fechaInicio,          "
                + "          tblDctosOrdenesProgreso.fechaFin,             "
                + "          tblDctosOrdenesProgreso.idCausa,              "
                + "          tblDctosOrdenesProgreso.estado,               "
                + "          tmpOPE.nombreTercero AS nombreOperario,       "
                + "          tblDctosOrdenesProgreso.cantidadPedida,       "
                + "          tblDctosOrdenesProgreso.pesoPedido,           "
                + "          tblDctosOrdenesProgreso.idControl,            "
                + "          tblDctosOrdenesProgreso.idPlu,                "
                + "          ( SELECT                                      "
                + "              RTRIM(tblCategorias.nombreCategoria) +    "
                + "                                               ' ' +    "
                + "                    tblPlus.nombrePlu AS nombrePlu      "
                + "            FROM   tblCategorias                        "
                + "            INNER JOIN tblPlus                          "
                + "            ON tblCategorias.idLinea      =             "
                + "                                    tblPlus.idLinea     "
                + "            AND tblCategorias.IdCategoria =             "
                + "                                tblPlus.idCategoria     "
                + "            WHERE tblPlus.idPlu =                       "
                + "                     tblDctosOrdenesProgreso.idPlu )    "
                + "                                        AS nombrePlu,   "
                + "           (SELECT TOP 1                                "
                + "                       tblPlusFicha.referenciaCliente   "
                + "            FROM tblPlusFicha                           "
                + "            WHERE tblPlusFicha.idFicha =                "
                + "                             tblDctosOrdenes.idFicha )  "
                + "                                  AS referenciaCliente, "
                + "            tblDctosOrdenes.numeroOrden,                "
                + "            tblDctosOrdenesProgreso.observacion         "
                + "   FROM   tblDctosOrdenes                               "
                + "   INNER JOIN tblDctosOrdenesProgreso                   "
                + "   ON  tblDctosOrdenes.idLocal     =                    "
                + "                   tblDctosOrdenesProgreso.idLocal      "
                + "   AND tblDctosOrdenes.idTipoOrden =                    "
                + "               tblDctosOrdenesProgreso.idTipoOrden      "
                + "   AND tblDctosOrdenes.idOrden     =                    "
                + "                   tblDctosOrdenesProgreso.idOrden      "
                + "   INNER JOIN (SELECT tmpTER.idTercero                  "
                + "              ,MAX(tmpTER.nombreTercero)                "
                + "                                  AS nombreTercero      "
                + "               FROM                                     "
                + "                ( SELECT ctrlUsuarios.idUsuario         "
                + "                                     AS idTercero       "
                + "                      ,ctrlUsuarios.nombreUsuario       "
                + "                                 AS nombreTercero       "
                + "                 FROM ctrlUsuarios                      "
                + "                 UNION                                  "
                + "                 SELECT tblTerceros.idCliente           "
                + "                                    AS idTercero        "
                + "                      ,tblTerceros.nombreTercero        "
                + "                                AS nombreTercero        "
                + "                 FROM tblTerceros ) AS tmpTER           "
                + "           GROUP BY tmpTER.idTercero ) AS tmpOPE        "
                + "   ON tblDctosOrdenesProgreso.idOperario   =            "
                + "                               tmpOPE.idTercero         "
                + " WHERE tblDctosOrdenes.idLocal             =            "
                + getIdLocal() + "                                         "
                + " AND   tblDctosOrdenes.idTipoOrden         =            "
                + getIdTipoOrden() + "                                     "
                + " AND   tblDctosOrdenes.idOrden             =            "
                + getIdOrden() + "                                         "
                + " AND   tblDctosOrdenesProgreso.idOperacion =            "
                + getIdOperacion() + "                                     "
                + " AND   tblDctosOrdenesProgreso.itemPadre   =            "
                + getItemPadre() + "                                       "
                + " AND   tblDctosOrdenesProgreso.idControl   =            "
                + getIdControl() + "                                       "
                + "  ORDER BY tblDctosOrdenesProgreso.idLocal,             "
                + "           tblDctosOrdenesProgreso.idTipoOrden,         "
                + "           tblDctosOrdenesProgreso.idOrden,             "
                + "           tblDctosOrdenesProgreso.item                 ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaDctoOrdenProgreso fachadaBean;

            //
            while (rs.next()) {

                //
                fachadaBean = new FachadaDctoOrdenProgreso();

                //
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setIdOrden(rs.getInt("idOrden"));
                fachadaBean.setItem(rs.getInt("item"));
                fachadaBean.setIdOperacion(rs.getInt("idOperacion"));
                fachadaBean.setIdOperario(rs.getDouble("idOperario"));
                fachadaBean.setCantidadPerdida(rs.getDouble("cantidadPerdida"));
                fachadaBean.setCantidadTerminada(
                        rs.getDouble("cantidadTerminada"));
                fachadaBean.setPesoPerdido(rs.getDouble("pesoPerdido"));
                fachadaBean.setPesoTerminado(rs.getDouble("pesoTerminado"));
                fachadaBean.setFechaInicio(rs.getString("fechaInicio"));
                fachadaBean.setFechaFin(rs.getString("fechaFin"));
                fachadaBean.setIdCausa(rs.getInt("idCausa"));
                fachadaBean.setEstado(rs.getInt("estado"));
                fachadaBean.setNombreOperario(rs.getString("nombreOperario"));
                fachadaBean.setCantidadPedida(rs.getDouble("cantidadPedida"));
                fachadaBean.setPesoPedido(rs.getDouble("pesoPedido"));
                fachadaBean.setIdControl(rs.getInt("idControl"));
                fachadaBean.setIdPlu(rs.getInt("idPlu"));
                fachadaBean.setNombrePlu(rs.getString("nombrePlu"));
                fachadaBean.setReferenciaCliente(
                        rs.getString("referenciaCliente"));
                fachadaBean.setNumeroOrden(rs.getInt("numeroOrden"));
                fachadaBean.setObservacion(rs.getString("observacion"));

                //-------
                if (fachadaBean.getIdPlu() > 0) {
                    fachadaBean.setReferenciaCliente(fachadaBean.getNombrePlu());
                }


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

    // listaUnOTExternaPlu
    public Vector listaUnOTExternaPlu() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        //
        String selectStr =
                "     SELECT tblDctosOrdenesProgreso.idPlu,                "
                + "          MAX(tblDctosOrdenesProgreso.item) AS item,    "
                + "          ( SELECT                                      "
                + "              RTRIM(tblCategorias.nombreCategoria) +    "
                + "                                               ' ' +    "
                + "                    tblPlus.nombrePlu AS nombrePlu      "
                + "            FROM   tblCategorias                        "
                + "            INNER JOIN tblPlus                          "
                + "            ON tblCategorias.idLinea      =             "
                + "                                    tblPlus.idLinea     "
                + "            AND tblCategorias.IdCategoria =             "
                + "                                tblPlus.idCategoria     "
                + "            WHERE tblPlus.idPlu =                       "
                + "                     tblDctosOrdenesProgreso.idPlu )    "
                + "                                        AS nombrePlu,   "
                + "           (SELECT TOP 1                                "
                + "                       tblPlusFicha.referenciaCliente   "
                + "            FROM tblPlusFicha                           "
                + "            WHERE tblPlusFicha.idFicha =                "
                + "                             tblDctosOrdenes.idFicha )  "
                + "                                  AS referenciaCliente  "
                + "   FROM   tblDctosOrdenes                               "
                + "   INNER JOIN tblDctosOrdenesProgreso                   "
                + "   ON  tblDctosOrdenes.idLocal     =                    "
                + "                   tblDctosOrdenesProgreso.idLocal      "
                + "   AND tblDctosOrdenes.idTipoOrden =                    "
                + "               tblDctosOrdenesProgreso.idTipoOrden      "
                + "   AND tblDctosOrdenes.idOrden     =                    "
                + "                   tblDctosOrdenesProgreso.idOrden      "
                + "   INNER JOIN (SELECT tmpTER.idTercero                  "
                + "              ,MAX(tmpTER.nombreTercero)                "
                + "                                  AS nombreTercero      "
                + "               FROM                                     "
                + "                ( SELECT ctrlUsuarios.idUsuario         "
                + "                                     AS idTercero       "
                + "                      ,ctrlUsuarios.nombreUsuario       "
                + "                                 AS nombreTercero       "
                + "                 FROM ctrlUsuarios                      "
                + "                 UNION                                  "
                + "                 SELECT tblTerceros.idCliente           "
                + "                                    AS idTercero        "
                + "                      ,tblTerceros.nombreTercero        "
                + "                                AS nombreTercero        "
                + "                 FROM tblTerceros ) AS tmpTER           "
                + "           GROUP BY tmpTER.idTercero ) AS tmpOPE        "
                + "   ON tblDctosOrdenesProgreso.idOperario   =            "
                + "                               tmpOPE.idTercero         "
                + " WHERE tblDctosOrdenes.idLocal             =            "
                + getIdLocal() + "                                         "
                + " AND   tblDctosOrdenes.idTipoOrden         =            "
                + getIdTipoOrden() + "                                     "
                + " AND   tblDctosOrdenes.idOrden             =            "
                + getIdOrden() + "                                         "
                + " AND   tblDctosOrdenesProgreso.idOperacion =            "
                + getIdOperacion() + "                                     "
                + " AND   tblDctosOrdenesProgreso.itemPadre   =            "
                + getItemPadre() + "                                       "
                + " AND   tblDctosOrdenesProgreso.idControl   =            "
                + getIdControl() + "                                       "
                + "  GROUP BY tblDctosOrdenesProgreso.idPlu,               "
                + "          tblDctosOrdenes.idFicha                       "
                + "  ORDER BY tblDctosOrdenesProgreso.idPlu                ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaDctoOrdenProgreso fachadaBean;

            //
            while (rs.next()) {

                //
                fachadaBean = new FachadaDctoOrdenProgreso();

                //
                fachadaBean.setItem(rs.getInt("item"));
                fachadaBean.setIdPlu(rs.getInt("idPlu"));
                fachadaBean.setNombrePlu(rs.getString("nombrePlu"));
                fachadaBean.setReferenciaCliente(
                        rs.getString("referenciaCliente"));

                //-------
                if (fachadaBean.getIdPlu() > 0) {
                    fachadaBean.setReferenciaCliente(fachadaBean.getNombrePlu());
                }


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

    // listaUnOTExternaCopia
    public Vector listaUnOTExternaCopia() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        //
        String selectStr =
                "     SELECT tblDctosOrdenesProgreso.idLocal,              "
                + "          tblDctosOrdenesProgreso.idTipoOrden,          "
                + "          tblDctosOrdenesProgreso.idOrden,              "
                + "          tblDctosOrdenesProgreso.item,                 "
                + "          tblDctosOrdenesProgreso.idOperacion,          "
                + "          tblDctosOrdenesProgreso.idOperario,           "
                + "          tblDctosOrdenesProgreso.cantidadPerdida,      "
                + "          tblDctosOrdenesProgreso.cantidadTerminada,    "
                + "          tblDctosOrdenesProgreso.pesoPerdido,          "
                + "          tblDctosOrdenesProgreso.pesoTerminado,        "
                + "          tblDctosOrdenesProgreso.fechaInicio,          "
                + "          tblDctosOrdenesProgreso.fechaFin,             "
                + "          tblDctosOrdenesProgreso.idCausa,              "
                + "          tblDctosOrdenesProgreso.estado,               "
                + "          tmpOPE.nombreTercero AS nombreOperario,       "
                + "          tblDctosOrdenesProgreso.cantidadPedida,       "
                + "          tblDctosOrdenesProgreso.pesoPedido,           "
                + "          tblDctosOrdenesProgreso.idControl,            "
                + "          tblDctosOrdenesProgreso.idPlu,                "
                + "          ( SELECT                                      "
                + "              RTRIM(tblCategorias.nombreCategoria) +    "
                + "                                               ' ' +    "
                + "                    tblPlus.nombrePlu AS nombrePlu      "
                + "            FROM   tblCategorias                        "
                + "            INNER JOIN tblPlus                          "
                + "            ON tblCategorias.idLinea      =             "
                + "                                    tblPlus.idLinea     "
                + "            AND tblCategorias.IdCategoria =             "
                + "                                tblPlus.idCategoria     "
                + "            WHERE tblPlus.idPlu =                       "
                + "                     tblDctosOrdenesProgreso.idPlu )    "
                + "                                        AS nombrePlu,   "
                + "           (SELECT TOP 1                                "
                + "                       tblPlusFicha.referenciaCliente   "
                + "            FROM tblPlusFicha                           "
                + "            WHERE tblPlusFicha.idFicha =                "
                + "                             tblDctosOrdenes.idFicha )  "
                + "                                  AS referenciaCliente, "
                + "            tblDctosCopia.idCopia,                      "
                + "            tblDctosCopia.nombreCopia,                  "
                + "            tblDctosOrdenes.numeroOrden,                "
                + "            tblDctosOrdenesProgreso.observacion,        "
                + "            tblDctosOrdenesProgreso.cantidadPendiente,  "
                + "            tblDctosOrdenesProgreso.idDctoNitCC         "
                + "   FROM   tblDctosOrdenes                               "
                + "   INNER JOIN tblDctosOrdenesProgreso                   "
                + "   ON  tblDctosOrdenes.idLocal     =                    "
                + "                   tblDctosOrdenesProgreso.idLocal      "
                + "   AND tblDctosOrdenes.idTipoOrden =                    "
                + "               tblDctosOrdenesProgreso.idTipoOrden      "
                + "   AND tblDctosOrdenes.idOrden     =                    "
                + "                   tblDctosOrdenesProgreso.idOrden      "
                + "   INNER JOIN tblDctosCopia                             "
                + "   ON tblDctosCopia.idTipoOrden    =                    "
                + "                 tblDctosOrdenesProgreso.idTipoOrden    "
                + "   INNER JOIN (SELECT tmpTER.idTercero                  "
                + "              ,MAX(tmpTER.nombreTercero)                "
                + "                                  AS nombreTercero      "
                + "               FROM                                     "
                + "                ( SELECT ctrlUsuarios.idUsuario         "
                + "                                     AS idTercero       "
                + "                      ,ctrlUsuarios.nombreUsuario       "
                + "                                 AS nombreTercero       "
                + "                 FROM ctrlUsuarios                      "
                + "                 UNION                                  "
                + "                 SELECT tblTerceros.idCliente           "
                + "                                    AS idTercero        "
                + "                      ,tblTerceros.nombreTercero        "
                + "                                AS nombreTercero        "
                + "                 FROM tblTerceros ) AS tmpTER           "
                + "           GROUP BY tmpTER.idTercero ) AS tmpOPE        "
                + "   ON tblDctosOrdenesProgreso.idOperario   =            "
                + "                               tmpOPE.idTercero         "
                + " WHERE tblDctosOrdenes.idLocal             =            "
                + getIdLocal() + "                                         "
                + " AND   tblDctosOrdenes.idTipoOrden         =            "
                + getIdTipoOrden() + "                                     "
                + " AND   tblDctosOrdenes.idOrden             =            "
                + getIdOrden() + "                                         "
                + " AND   tblDctosOrdenesProgreso.idOperacion =            "
                + getIdOperacion() + "                                     "
                + " AND   tblDctosOrdenesProgreso.itemPadre   =            "
                + getItemPadre() + "                                       "
                + " AND   tblDctosOrdenesProgreso.idControl   =            "
                + getIdControl() + "                                       "
                + " AND tblDctosOrdenesProgreso.idControlTipo =            "
                + getIdControlTipo() + "                                   "
                + "  ORDER BY tblDctosOrdenesProgreso.idLocal,             "
                + "           tblDctosOrdenesProgreso.idTipoOrden,         "
                + "           tblDctosOrdenesProgreso.idOrden,             "
                + "           tblDctosCopia.idCopia,                       "
                + "           tblDctosOrdenesProgreso.item                 ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaDctoOrdenProgreso fachadaBean;

            //
            while (rs.next()) {

                //
                fachadaBean = new FachadaDctoOrdenProgreso();

                //
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setIdOrden(rs.getInt("idOrden"));
                fachadaBean.setItem(rs.getInt("item"));
                fachadaBean.setIdOperacion(rs.getInt("idOperacion"));
                fachadaBean.setIdOperario(rs.getDouble("idOperario"));
                fachadaBean.setCantidadPerdida(rs.getDouble("cantidadPerdida"));
                fachadaBean.setCantidadTerminada(
                        rs.getDouble("cantidadTerminada"));
                fachadaBean.setPesoPerdido(rs.getDouble("pesoPerdido"));
                fachadaBean.setPesoTerminado(rs.getDouble("pesoTerminado"));
                fachadaBean.setFechaInicio(rs.getString("fechaInicio"));
                fachadaBean.setFechaFin(rs.getString("fechaFin"));
                fachadaBean.setIdCausa(rs.getInt("idCausa"));
                fachadaBean.setEstado(rs.getInt("estado"));
                fachadaBean.setNombreOperario(rs.getString("nombreOperario"));
                fachadaBean.setCantidadPedida(rs.getDouble("cantidadPedida"));
                fachadaBean.setPesoPedido(rs.getDouble("pesoPedido"));
                fachadaBean.setIdControl(rs.getInt("idControl"));
                fachadaBean.setIdPlu(rs.getInt("idPlu"));
                fachadaBean.setNombrePlu(rs.getString("nombrePlu"));
                fachadaBean.setReferenciaCliente(
                        rs.getString("referenciaCliente"));
                fachadaBean.setIdCopia(rs.getInt("idCopia"));
                fachadaBean.setNombreCopia(rs.getString("nombreCopia"));
                fachadaBean.setNumeroOrden(rs.getInt("numeroOrden"));
                fachadaBean.setObservacion(rs.getString("observacion"));
                fachadaBean.setCantidadPendiente(
                                             rs.getDouble("cantidadPendiente"));
                fachadaBean.setIdDctoNitCC(rs.getString("idDctoNitCC"));                

                //-------
                if (fachadaBean.getIdPlu() > 0) {
                    fachadaBean.setReferenciaCliente(fachadaBean.getNombrePlu());
                }


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

    // listaUnOTExternaCopiaFCH
    public FachadaDctoOrdenProgreso listaUnOTExternaCopiaFCH() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        // Variable de fachada de los datos
        FachadaDctoOrdenProgreso fachadaBean = new FachadaDctoOrdenProgreso();

        Connection connection = null;

        //
        String selectStr =
                "     SELECT TOP 1 tblDctosOrdenesProgreso.idLocal,        "
                + "          tblDctosOrdenesProgreso.idTipoOrden,          "
                + "          tblDctosOrdenesProgreso.idOrden,              "
                + "          tblDctosOrdenesProgreso.item,                 "
                + "          tblDctosOrdenesProgreso.idOperacion,          "
                + "          tblDctosOrdenesProgreso.idOperario,           "
                + "          tblDctosOrdenesProgreso.cantidadPerdida,      "
                + "          tblDctosOrdenesProgreso.cantidadTerminada,    "
                + "          tblDctosOrdenesProgreso.pesoPerdido,          "
                + "          tblDctosOrdenesProgreso.pesoTerminado,        "
                + "          tblDctosOrdenesProgreso.fechaInicio,          "
                + "          tblDctosOrdenesProgreso.fechaFin,             "
                + "          tblDctosOrdenesProgreso.idCausa,              "
                + "          tblDctosOrdenesProgreso.estado,               "
                + "          tmpOPE.nombreTercero AS nombreOperario,       "
                + "          tblDctosOrdenesProgreso.cantidadPedida,       "
                + "          tblDctosOrdenesProgreso.pesoPedido,           "
                + "          tblDctosOrdenesProgreso.idControl,            "
                + "          tblDctosOrdenesProgreso.idPlu,                "
                + "          ( SELECT                                      "
                + "              RTRIM(tblCategorias.nombreCategoria) +    "
                + "                                               ' ' +    "
                + "                    tblPlus.nombrePlu AS nombrePlu      "
                + "            FROM   tblCategorias                        "
                + "            INNER JOIN tblPlus                          "
                + "            ON tblCategorias.idLinea      =             "
                + "                                    tblPlus.idLinea     "
                + "            AND tblCategorias.IdCategoria =             "
                + "                                tblPlus.idCategoria     "
                + "            WHERE tblPlus.idPlu =                       "
                + "                     tblDctosOrdenesProgreso.idPlu )    "
                + "                                        AS nombrePlu,   "
                + "           (SELECT TOP 1                                "
                + "                       tblPlusFicha.referenciaCliente   "
                + "            FROM tblPlusFicha                           "
                + "            WHERE tblPlusFicha.idFicha =                "
                + "                             tblDctosOrdenes.idFicha )  "
                + "                                  AS referenciaCliente, "
                + "            tblDctosCopia.idCopia,                      "
                + "            tblDctosCopia.nombreCopia,                  "
                + "            tblDctosOrdenes.numeroOrden,                "
                + "            tblDctosOrdenesProgreso.observacion         "
                + "   FROM   tblDctosOrdenes                               "
                + "   INNER JOIN tblDctosOrdenesProgreso                   "
                + "   ON  tblDctosOrdenes.idLocal     =                    "
                + "                   tblDctosOrdenesProgreso.idLocal      "
                + "   AND tblDctosOrdenes.idTipoOrden =                    "
                + "               tblDctosOrdenesProgreso.idTipoOrden      "
                + "   AND tblDctosOrdenes.idOrden     =                    "
                + "                   tblDctosOrdenesProgreso.idOrden      "
                + "   INNER JOIN tblDctosCopia                             "
                + "   ON tblDctosCopia.idTipoOrden    =                    "
                + "                 tblDctosOrdenesProgreso.idTipoOrden    "
                + "   INNER JOIN (SELECT tmpTER.idTercero                  "
                + "              ,MAX(tmpTER.nombreTercero)                "
                + "                                  AS nombreTercero      "
                + "               FROM                                     "
                + "                ( SELECT ctrlUsuarios.idUsuario         "
                + "                                     AS idTercero       "
                + "                      ,ctrlUsuarios.nombreUsuario       "
                + "                                 AS nombreTercero       "
                + "                 FROM ctrlUsuarios                      "
                + "                 UNION                                  "
                + "                 SELECT tblTerceros.idCliente           "
                + "                                    AS idTercero        "
                + "                      ,tblTerceros.nombreTercero        "
                + "                                AS nombreTercero        "
                + "                 FROM tblTerceros ) AS tmpTER           "
                + "           GROUP BY tmpTER.idTercero ) AS tmpOPE        "
                + "   ON tblDctosOrdenesProgreso.idOperario   =            "
                + "                               tmpOPE.idTercero         "
                + " WHERE tblDctosOrdenes.idLocal             =            "
                + getIdLocal() + "                                         "
                + " AND   tblDctosOrdenes.idTipoOrden         =            "
                + getIdTipoOrden() + "                                     "
                + " AND   tblDctosOrdenes.idOrden             =            "
                + getIdOrden() + "                                         "
                + " AND   tblDctosOrdenesProgreso.idOperacion =            "
                + getIdOperacion() + "                                     "
                + " AND   tblDctosOrdenesProgreso.itemPadre   =            "
                + getItemPadre() + "                                       "
                + " AND   tblDctosOrdenesProgreso.idControl   =            "
                + getIdControl() + "                                       "
                + " AND tblDctosOrdenesProgreso.idControlTipo =            "
                + getIdControlTipo() + "                                   "
                + "  ORDER BY tblDctosOrdenesProgreso.idLocal,             "
                + "           tblDctosOrdenesProgreso.idTipoOrden,         "
                + "           tblDctosOrdenesProgreso.idOrden,             "
                + "           tblDctosCopia.idCopia,                       "
                + "           tblDctosOrdenesProgreso.item                 ";
        
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
                fachadaBean.setItem(rs.getInt("item"));
                fachadaBean.setIdOperacion(rs.getInt("idOperacion"));
                fachadaBean.setIdOperario(rs.getDouble("idOperario"));
                fachadaBean.setCantidadPerdida(rs.getDouble("cantidadPerdida"));
                fachadaBean.setCantidadTerminada(
                        rs.getDouble("cantidadTerminada"));
                fachadaBean.setPesoPerdido(rs.getDouble("pesoPerdido"));
                fachadaBean.setPesoTerminado(rs.getDouble("pesoTerminado"));
                fachadaBean.setFechaInicio(rs.getString("fechaInicio"));
                fachadaBean.setFechaFin(rs.getString("fechaFin"));
                fachadaBean.setIdCausa(rs.getInt("idCausa"));
                fachadaBean.setEstado(rs.getInt("estado"));
                fachadaBean.setNombreOperario(rs.getString("nombreOperario"));
                fachadaBean.setCantidadPedida(rs.getDouble("cantidadPedida"));
                fachadaBean.setPesoPedido(rs.getDouble("pesoPedido"));
                fachadaBean.setIdControl(rs.getInt("idControl"));
                fachadaBean.setIdPlu(rs.getInt("idPlu"));
                fachadaBean.setNombrePlu(rs.getString("nombrePlu"));
                fachadaBean.setReferenciaCliente(
                        rs.getString("referenciaCliente"));
                fachadaBean.setIdCopia(rs.getInt("idCopia"));
                fachadaBean.setNombreCopia(rs.getString("nombreCopia"));
                fachadaBean.setNumeroOrden(rs.getInt("numeroOrden"));
                fachadaBean.setObservacion(rs.getString("observacion"));

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

    // listaUnOTItem
    public Vector listaUnOTItem() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        //
        Vector contenedor = new Vector();

        Connection connection = null;

        //
        String selectStr =
                "    SELECT TOP 1 tblDctosOrdenesProgreso.idLocal,      "
                + "         tblDctosOrdenesProgreso.idTipoOrden,        "
                + "         tblDctosOrdenesProgreso.idOrden,            "
                + "         tblDctosOrdenesProgreso.itemPadre,          "
                + "         tblDctosOrdenesProgreso.item,               "
                + "         tblDctosOrdenesProgreso.idOperacion,        "
                + "         tblJobOperacion.nombreOperacion,            "
                + "         tblDctosOrdenesProgreso.idOperario,         "
                + "         tblDctosOrdenesProgreso.cantidadPerdida,    "
                + "         tblDctosOrdenesProgreso.cantidadTerminada,  "
                + "         tblDctosOrdenesProgreso.pesoPerdido,        "
                + "         tblDctosOrdenesProgreso.pesoTerminado,      "
                + "         tblDctosOrdenesProgreso.fechaInicio,        "
                + "         tblDctosOrdenesProgreso.fechaFin,           "
                + "         tblDctosOrdenesProgreso.idCausa,            "
                + "         tblDctosOrdenesProgreso.estado,             "
                + "         tmpOPE.nombreTercero AS nombreOperario,     "
                + "         tblDctosOrdenesProgreso.cantidadPedida,     "
                + "         tblDctosOrdenesProgreso.pesoPedido,         "
                + "         tblDctosOrdenesProgreso.idPlu,              "
                + "         ( SELECT                                    "
                + "             RTRIM(tblCategorias.nombreCategoria) +  "
                + "                                              ' ' +  "
                + "                   tblPlus.nombrePlu AS nombrePlu    "
                + "           FROM   tblCategorias                      "
                + "           INNER JOIN tblPlus                        "
                + "           ON tblCategorias.idLinea      =           "
                + "                                   tblPlus.idLinea   "
                + "           AND tblCategorias.IdCategoria =           "
                + "                               tblPlus.idCategoria   "
                + "           WHERE tblPlus.idPlu =                     "
                + "                   tblDctosOrdenesProgreso.idPlu )   "
                + "                                     AS nombrePlu,   "
                + "          (SELECT TOP 1                              "
                + "                    tblPlusFicha.referenciaCliente   "
                + "           FROM tblPlusFicha                         "
                + "           WHERE tblPlusFicha.idFicha =              "
                + "                         tblDctosOrdenes.idFicha )   "
                + "                             AS referenciaCliente,   "
                + "           tblDctosOrdenes.numeroOrden,              "
                + "           tblDctosOrdenes.ordenCompra,              "
                + "           tblTerceros.nombreTercero,                "
                + "           tblDctosOrdenes.idLog                     "
                + "  FROM   tblDctosOrdenes                             "
                + "  INNER JOIN tblTerceros                             "
                + "  ON tblTerceros.idCliente =                         "
                + "                        tblDctosOrdenes.idCliente    "
                + "  INNER JOIN tblDctosOrdenesProgreso                 "
                + "  ON  tblDctosOrdenes.idLocal     =                  "
                + "                  tblDctosOrdenesProgreso.idLocal    "
                + "  AND tblDctosOrdenes.idTipoOrden =                  "
                + "              tblDctosOrdenesProgreso.idTipoOrden    "
                + "  AND tblDctosOrdenes.idOrden     =                  "
                + "                  tblDctosOrdenesProgreso.idOrden    "
                + "  INNER JOIN tblJobOperacion                         "
                + "  ON  tblDctosOrdenesProgreso.idOperacion  =         "
                + "                  tblJobOperacion.idOperacion        "
                + "  INNER JOIN (SELECT tmpTER.idTercero                "
                + "             ,MAX(tmpTER.nombreTercero)              "
                + "                                 AS nombreTercero    "
                + "              FROM                                   "
                + "               ( SELECT ctrlUsuarios.idUsuario       "
                + "                                    AS idTercero     "
                + "                     ,ctrlUsuarios.aliasUsuario      "
                + "                                AS nombreTercero     "
                + "                FROM ctrlUsuarios                    "
                + "                UNION                                "
                + "                SELECT tblTerceros.idCliente         "
                + "                                   AS idTercero      "
                + "                     ,'-' AS nombreTercero           "
                + "                FROM tblTerceros ) AS tmpTER         "
                + "          GROUP BY tmpTER.idTercero ) AS tmpOPE      "
                + "  ON tblDctosOrdenesProgreso.idOperario   =          "
                + "                              tmpOPE.idTercero       "
                + "  WHERE tblDctosOrdenes.idLocal             =        "
                + getIdLocal() + "                                      "
                + "  AND   tblDctosOrdenes.idTipoOrden         =        "
                + getIdTipoOrden() + "                                  "
                + "  AND   tblDctosOrdenes.idLog               =        "
                + getIdLog() + "                                        "
                + "  AND   tblDctosOrdenesProgreso.idOperacion =        "
                + getIdOperacion() + "                                  "
                + "  AND   tblDctosOrdenesProgreso.itemPadre   =        "
                + getItemPadre() + "                                    "
                + "  AND   tblDctosOrdenesProgreso.item        =        "
                + getItem();


        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaDctoOrdenProgreso fachadaBean;

            //
            if (rs.next()) {

                //
                fachadaBean = new FachadaDctoOrdenProgreso();

                //
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setIdOrden(rs.getInt("idOrden"));
                fachadaBean.setItemPadre(rs.getInt("itemPadre"));
                fachadaBean.setItem(rs.getInt("item"));
                fachadaBean.setIdOperacion(rs.getInt("idOperacion"));
                fachadaBean.setIdOperario(rs.getDouble("idOperario"));
                fachadaBean.setNombreOperacion(rs.getString("nombreOperacion"));
                fachadaBean.setCantidadPerdida(rs.getDouble("cantidadPerdida"));
                fachadaBean.setCantidadTerminada(
                        rs.getDouble("cantidadTerminada"));
                fachadaBean.setPesoPerdido(rs.getDouble("pesoPerdido"));
                fachadaBean.setPesoTerminado(rs.getDouble("pesoTerminado"));
                fachadaBean.setFechaInicio(rs.getString("fechaInicio"));
                fachadaBean.setFechaFin(rs.getString("fechaFin"));
                fachadaBean.setIdCausa(rs.getInt("idCausa"));
                fachadaBean.setEstado(rs.getInt("estado"));
                fachadaBean.setNombreOperario(rs.getString("nombreOperario"));
                fachadaBean.setCantidadPedida(rs.getDouble("cantidadPedida"));
                fachadaBean.setPesoPedido(rs.getDouble("pesoPedido"));
                fachadaBean.setIdPlu(rs.getInt("idPlu"));
                fachadaBean.setNombrePlu(rs.getString("nombrePlu"));
                fachadaBean.setReferenciaCliente(
                        rs.getString("referenciaCliente"));
                fachadaBean.setNumeroOrden(rs.getInt("numeroOrden"));
                fachadaBean.setOrdenCompra(rs.getString("ordenCompra"));
                fachadaBean.setNombreTercero(rs.getString("nombreTercero"));
                fachadaBean.setIdLog(rs.getInt("idLog"));

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

    // listaUnOTExternaSaldo
    public FachadaDctoOrdenProgreso listaUnOTExternaSaldo() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        // Variable de fachada de los datos
        FachadaDctoOrdenProgreso fachadaBean = new FachadaDctoOrdenProgreso();

        Connection connection = null;

        //
        String selectStr =
                " SELECT tmpPRG.idLocal,                                "
                + "        tmpPRG.idTipoOrden,                          "
                + "        tmpPRG.idOrden,                              "
                + "        tmpPRG.idOperacion,                          "
                + "        tmpPRG.itempadre,                            "
                + "     SUM(tmpPRG.cantidadSalida) AS cantidadSalida ,  "
                + "     SUM(tmpPRG.cantidadEntrada) AS cantidadEntrada  "
                + " FROM                                                "
                + " (SELECT tblDctosOrdenesProgreso.idLocal             "
                + "       ,tblDctosOrdenesProgreso.idTipoOrden          "
                + "       ,tblDctosOrdenesProgreso.idOrden              "
                + "       ,tblDctosOrdenesProgreso.idOperacion          "
                + "       ,tblDctosOrdenesProgreso.itempadre            "
                + "       , ISNULL(                                     "
                + " 	    CASE tblDctosOrdenesProgreso.idControlTipo  "
                + "          WHEN 2 THEN                                "
                + "          tblDctosOrdenesProgreso.cantidadTerminada  "
                + "          WHEN 1 THEN 0                              "
                + "         END,0.00) AS cantidadSalida                 "
                + "       , ISNULL(                                     "
                + " 	    CASE tblDctosOrdenesProgreso.idControlTipo  "
                + "          WHEN 1 THEN                                "
                + " 	      tblDctosOrdenesProgreso.cantidadTerminada "
                + "          WHEN 2 THEN 0                              "
                + "         END,0.00) AS cantidadEntrada                "
                + " FROM tblDctosOrdenesProgreso                        "
                + " WHERE tblDctosOrdenesProgreso.idLocal     =         "
                + getIdLocal() + "                                      "
                + " AND   tblDctosOrdenesProgreso.idTipoOrden =         "
                + getIdTipoOrden() + "                                  "
                + " AND   tblDctosOrdenesProgreso.idOrden     =         "
                + getIdOrden() + "                                      "
                + " AND   tblDctosOrdenesProgreso.idOperacion =         "
                + getIdOperacion() + "                                  "
                + " AND   tblDctosOrdenesProgreso.itemPadre   =         "
                + getItemPadre() + "  )                      AS tmpPRG  "
                + " GROUP BY tmpPRG.idLocal,                            "
                + "        tmpPRG.idTipoOrden,                          "
                + "        tmpPRG.idOrden,                              "
                + "        tmpPRG.idOperacion,                          "
                + "        tmpPRG.itempadre                             ";

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
                fachadaBean.setItemPadre(rs.getInt("itempadre"));
                fachadaBean.setCantidadSalida(rs.getDouble("cantidadSalida"));
                fachadaBean.setCantidadEntrada(rs.getDouble("cantidadEntrada"));

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

    // listaOTAllOperacion
    public Vector listaOTAllOperacion(int xIdTipoTercero) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        //
        Vector contenedor = new Vector();

        // Variable de fachada de los datos
        FachadaDctoOrdenProgreso fachadaBean;

        Connection connection = null;

        String selectStr =
                " SELECT tblDctosOrdenesProgreso.idLocal,                 "
                + "        tblDctosOrdenesProgreso.idTipoOrden,           "
                + "        tblDctosOrdenesProgreso.idOrden,               "
                + "        tblDctosOrdenesProgreso.idOperacion,           "
                + "        tblTerceros.idCliente,                         "
                + "        tblJobOperacion.nombreOperacion,               "
                + "        tblDctosOrdenesDetalle.item,                   "
                + "        tblDctosOrdenes.fechaOrden,                    "
                + "        tblDctosOrdenes.idLog,                         "
                + "        tblDctosOrdenes.idFicha,                       "
                + "        tblDctosOrdenes.ordenCompra,                   "
                + "        tblDctosOrdenes.numeroOrden,                   "
                + "        tblDctosOrdenesDetalle.fechaEntrega,           "
                + "        tblDctosOrdenesProgreso.itemPadre,             "
                + "        MAX(tblDctosOrdenesDetalle.cantidad)           "
                + "                                         AS cantidad,  "
                + "        SUM(tblDctosOrdenesProgreso.cantidadPerdida)   "
                + "                                 AS cantidadPerdida,   "
                + "        SUM(tblDctosOrdenesProgreso.cantidadTerminada) "
                + "                                 AS cantidadTerminada, "
                + "        SUM(tblDctosOrdenesProgreso.pesoPerdido)       "
                + "                                      AS pesoPerdido,  "
                + "        SUM(tblDctosOrdenesProgreso.pesoTerminado)     "
                + "                                    AS pesoTerminado,  "
                + "        SUM(tblDctosOrdenesDetalle.cantidad -          "
                + "            tblDctosOrdenesProgreso.cantidadTerminada) "
                + "                                AS cantidadPendiente,  "
                + "        MAX(tblTerceros.nombreTercero)                 "
                + "                                     AS nombreTercero, "
                + "  MAX(tmpFIC.referenciaCliente) AS referenciaCliente,  "
                + "  MAX(tmpFIC.referencia) AS referencia                 "
                + " FROM   tblDctosOrdenes                                "
                + " INNER JOIN tblDctosOrdenesProgreso                    "
                + " ON tblDctosOrdenes.IDLOCAL      =                     "
                + "                      tblDctosOrdenesProgreso.idLocal  "
                + " AND tblDctosOrdenes.IDTIPOORDEN =                     "
                + "                  tblDctosOrdenesProgreso.idTipoOrden  "
                + " AND tblDctosOrdenes.IDORDEN     =                     "
                + "                      tblDctosOrdenesProgreso.idOrden  "
                + " INNER JOIN tblTerceros                                "
                + " ON tblDctosOrdenes.idCliente = tblTerceros.idCliente  "
                + " INNER JOIN tblJobOperacion                            "
                + " ON tblJobOperacion.idOperacion    =                   "
                + "                  tblDctosOrdenesProgreso.idOperacion  "
                + " INNER JOIN tblDctosOrdenesDetalle                     "
                + " ON  tblDctosOrdenes.IDLOCAL     =                     "
                + "                       tblDctosOrdenesDetalle.IDLOCAL  "
                + " AND tblDctosOrdenes.IDTIPOORDEN =                     "
                + "                   tblDctosOrdenesDetalle.IDTIPOORDEN  "
                + " AND tblDctosOrdenes.IDORDEN     =                     "
                + "                       tblDctosOrdenesDetalle.IDORDEN  "
                + " AND tblDctosOrdenesProgreso.itemPadre =               "
                + "                          tblDctosOrdenesDetalle.item  "
                + " INNER JOIN (SELECT tblPlusFicha.referenciaCliente     "
                + "                    ,tblPlusFicha.idFicha              "
                + "                    ,tblPlusFicha.referencia           "
                + "             FROM tblPlusFicha                         "
                + "             GROUP BY tblPlusFicha.referenciaCliente   "
                + "                      ,tblPlusFicha.idFicha            "
                + "                      ,tblPlusFicha.referencia)        "
                + "                                         AS tmpFIC     "
                + " ON tmpFIC.idFicha = tblDctosOrdenes.idFicha           "
                + " WHERE tblDctosOrdenes.IDLOCAL     =                   "
                + getIdLocal() + "                                        "
                + " AND   tblDctosOrdenes.IDTIPOORDEN =                   "
                + getIdTipoOrden() + "                                    "
                + " GROUP BY tblDctosOrdenesProgreso.idLocal,             "
                + "          tblDctosOrdenesProgreso.idTipoOrden,         "
                + "          tblDctosOrdenesProgreso.idOrden,             "
                + "          tblDctosOrdenesProgreso.idOperacion,         "
                + "          tblTerceros.idCliente,                       "
                + "          tblJobOperacion.nombreOperacion,             "
                + "          tblDctosOrdenesDetalle.item,                 "
                + "          tblDctosOrdenes.fechaOrden,                  "
                + "          tblDctosOrdenes.idLog,                       "
                + "          tblDctosOrdenes.idFicha,                     "
                + "          tblDctosOrdenes.ordenCompra,                 "
                + "          tblDctosOrdenes.numeroOrden,                 "
                + "          tblDctosOrdenesDetalle.fechaEntrega,         "
                + "          tblDctosOrdenesProgreso.itemPadre            ";

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
                fachadaBean = new FachadaDctoOrdenProgreso();

                //
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setIdOrden(rs.getInt("idOrden"));
                fachadaBean.setIdOperacion(rs.getInt("idOperacion"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setNombreOperacion(rs.getString("nombreOperacion"));
                fachadaBean.setItem(rs.getInt("item"));
                fachadaBean.setFechaOrden(rs.getString("fechaOrden"));
                fachadaBean.setIdOrden(rs.getInt("idOrden"));
                fachadaBean.setIdLog(rs.getInt("idLog"));
                fachadaBean.setIdFicha(rs.getInt("idFicha"));
                fachadaBean.setOrdenCompra(rs.getString("ordenCompra"));
                fachadaBean.setNumeroOrden(rs.getInt("numeroOrden"));
                fachadaBean.setFechaEntrega(rs.getString("fechaEntrega"));
                fachadaBean.setItemPadre(rs.getInt("itemPadre"));
                fachadaBean.setCantidad(rs.getDouble("cantidad"));
                fachadaBean.setCantidadPerdida(rs.getDouble("cantidadPerdida"));
                fachadaBean.setCantidadTerminada(
                        rs.getDouble("cantidadTerminada"));
                fachadaBean.setPesoPerdido(rs.getDouble("pesoPerdido"));
                fachadaBean.setPesoTerminado(rs.getDouble("pesoTerminado"));
                fachadaBean.setNombreTercero(rs.getString("nombreTercero"));
                fachadaBean.setReferenciaCliente(
                        rs.getString("referenciaCliente"));
                fachadaBean.setReferencia(rs.getString("referencia"));

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

    // listaOTAllOperacionCliente
    public Vector listaOTAllOperacionCliente(int xIdTipoTercero) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        //
        Vector contenedor = new Vector();

        // Variable de fachada de los datos
        FachadaDctoOrdenProgreso fachadaBean;

        Connection connection = null;

        String selectStr =
                " SELECT tblDctosOrdenesProgreso.idLocal,                 "
                + "        tblDctosOrdenesProgreso.idTipoOrden,           "
                + "        tblDctosOrdenesProgreso.idOrden,               "
                + "        tblDctosOrdenesProgreso.idOperacion,           "
                + "        tblTerceros.idCliente,                         "
                + "        tblJobOperacion.nombreOperacion,               "
                + "        tblDctosOrdenesDetalle.item,                   "
                + "        tblDctosOrdenes.fechaOrden,                    "
                + "        tblDctosOrdenes.idLog,                         "
                + "        tblDctosOrdenes.idFicha,                       "
                + "        tblDctosOrdenes.ordenCompra,                   "
                + "        tblDctosOrdenes.numeroOrden,                   "
                + "        tblDctosOrdenesDetalle.fechaEntrega,           "
                + "        tblDctosOrdenesProgreso.itemPadre,             "
                + "        MAX(tblDctosOrdenesDetalle.cantidad)           "
                + "                                         AS cantidad,  "
                + "        SUM(tblDctosOrdenesProgreso.cantidadPerdida)   "
                + "                                 AS cantidadPerdida,   "
                + "        SUM(tblDctosOrdenesProgreso.cantidadTerminada) "
                + "                                 AS cantidadTerminada, "
                + "        SUM(tblDctosOrdenesProgreso.pesoPerdido)       "
                + "                                      AS pesoPerdido,  "
                + "        SUM(tblDctosOrdenesProgreso.pesoTerminado)     "
                + "                                    AS pesoTerminado,  "
                + "        SUM(tblDctosOrdenesDetalle.cantidad -          "
                + "            tblDctosOrdenesProgreso.cantidadTerminada) "
                + "                                AS cantidadPendiente,  "
                + "        MAX(tblDctosOrdenesDetalle.cantidadFacturada)  "
                + "                                AS cantidadFacturada,  "
                + "        MAX(tblTerceros.nombreTercero)                 "
                + "                                     AS nombreTercero, "
                + "  MAX(tmpFIC.referenciaCliente) AS referenciaCliente,  "
                + "  MAX(tmpFIC.referencia) AS referencia                 "
                + " FROM   tblDctosOrdenes                                "
                + " INNER JOIN tblDctosOrdenesProgreso                    "
                + " ON tblDctosOrdenes.IDLOCAL      =                     "
                + "                      tblDctosOrdenesProgreso.idLocal  "
                + " AND tblDctosOrdenes.IDTIPOORDEN =                     "
                + "                  tblDctosOrdenesProgreso.idTipoOrden  "
                + " AND tblDctosOrdenes.IDORDEN     =                     "
                + "                      tblDctosOrdenesProgreso.idOrden  "
                + " INNER JOIN tblTerceros                                "
                + " ON tblDctosOrdenes.idCliente = tblTerceros.idCliente  "
                + " INNER JOIN tblJobOperacion                            "
                + " ON tblJobOperacion.idOperacion    =                   "
                + "                  tblDctosOrdenesProgreso.idOperacion  "
                + " INNER JOIN tblDctosOrdenesDetalle                     "
                + " ON  tblDctosOrdenes.IDLOCAL     =                     "
                + "                       tblDctosOrdenesDetalle.IDLOCAL  "
                + " AND tblDctosOrdenes.IDTIPOORDEN =                     "
                + "                   tblDctosOrdenesDetalle.IDTIPOORDEN  "
                + " AND tblDctosOrdenes.IDORDEN     =                     "
                + "                       tblDctosOrdenesDetalle.IDORDEN  "
                + " AND tblDctosOrdenesProgreso.itemPadre =               "
                + "                          tblDctosOrdenesDetalle.item  "
                + " INNER JOIN (SELECT tblPlusFicha.referenciaCliente     "
                + "                    ,tblPlusFicha.idFicha              "
                + "                    ,tblPlusFicha.referencia           "
                + "             FROM tblPlusFicha                         "
                + "             GROUP BY tblPlusFicha.referenciaCliente   "
                + "                      ,tblPlusFicha.idFicha            "
                + "                      ,tblPlusFicha.referencia)        "
                + "                                         AS tmpFIC     "
                + " ON tmpFIC.idFicha = tblDctosOrdenes.idFicha           "
                + " WHERE tblDctosOrdenes.IDLOCAL     =                   "
                + getIdLocal() + "                                        "
                + " AND   tblDctosOrdenes.IDTIPOORDEN =                   "
                + getIdTipoOrden() + "                                    "
                + " AND   tblDctosOrdenes.idCliente   =                  '"
                + getIdCliente() + "'                                     "
                + " GROUP BY tblDctosOrdenesProgreso.idLocal,             "
                + "          tblDctosOrdenesProgreso.idTipoOrden,         "
                + "          tblDctosOrdenesProgreso.idOrden,             "
                + "          tblDctosOrdenesProgreso.idOperacion,         "
                + "          tblTerceros.idCliente,                       "
                + "          tblJobOperacion.nombreOperacion,             "
                + "          tblDctosOrdenesDetalle.item,                 "
                + "          tblDctosOrdenes.fechaOrden,                  "
                + "          tblDctosOrdenes.idLog,                       "
                + "          tblDctosOrdenes.idFicha,                     "
                + "          tblDctosOrdenes.ordenCompra,                 "
                + "          tblDctosOrdenes.numeroOrden,                 "
                + "          tblDctosOrdenesDetalle.fechaEntrega,         "
                + "          tblDctosOrdenesProgreso.itemPadre            ";

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
                fachadaBean = new FachadaDctoOrdenProgreso();

                //
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setIdOrden(rs.getInt("idOrden"));
                fachadaBean.setIdOperacion(rs.getInt("idOperacion"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setNombreOperacion(rs.getString("nombreOperacion"));
                fachadaBean.setItem(rs.getInt("item"));
                fachadaBean.setFechaOrden(rs.getString("fechaOrden"));
                fachadaBean.setIdOrden(rs.getInt("idOrden"));
                fachadaBean.setIdLog(rs.getInt("idLog"));
                fachadaBean.setIdFicha(rs.getInt("idFicha"));
                fachadaBean.setOrdenCompra(rs.getString("ordenCompra"));
                fachadaBean.setNumeroOrden(rs.getInt("numeroOrden"));
                fachadaBean.setFechaEntrega(rs.getString("fechaEntrega"));
                fachadaBean.setItemPadre(rs.getInt("itemPadre"));
                fachadaBean.setCantidad(rs.getDouble("cantidad"));
                fachadaBean.setCantidadPerdida(rs.getDouble("cantidadPerdida"));
                fachadaBean.setCantidadTerminada(
                        rs.getDouble("cantidadTerminada"));
                fachadaBean.setPesoPerdido(rs.getDouble("pesoPerdido"));
                fachadaBean.setPesoTerminado(rs.getDouble("pesoTerminado"));
                fachadaBean.setCantidadFacturada(rs.getDouble("cantidadFacturada"));
                fachadaBean.setNombreTercero(rs.getString("nombreTercero"));
                fachadaBean.setReferenciaCliente(rs.getString("referenciaCliente"));
                fachadaBean.setReferencia(rs.getString("referencia"));

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

    // listaOTUnOperacion
    public Vector listaOTUnOperacion(int xIdTipoTercero) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        // Variable de fachada de los datos
        FachadaDctoOrdenProgreso fachadaBean;

        Connection connection = null;

        String selectStr =
                " SELECT tblDctosOrdenesProgreso.idLocal,                 "
                + "        tblDctosOrdenesProgreso.idTipoOrden,           "
                + "        tblDctosOrdenesProgreso.idOrden,               "
                + "        tblDctosOrdenesProgreso.idOperacion,           "
                + "        tblTerceros.idCliente,                         "
                + "        tblJobOperacion.nombreOperacion,               "
                + "        tblDctosOrdenesDetalle.item,                   "
                + "        tblDctosOrdenes.fechaOrden,                    "
                + "        tblDctosOrdenes.idLog,                         "
                + "        tblDctosOrdenes.idFicha,                       "
                + "        tblDctosOrdenes.ordenCompra,                   "
                + "        tblDctosOrdenes.numeroOrden,                   "
                + "        tblDctosOrdenesDetalle.fechaEntrega,           "
                + "        tblDctosOrdenesProgreso.itemPadre,             "
                + "        MAX(tblDctosOrdenesDetalle.cantidad)           "
                + "                                         AS cantidad,  "
                + "        SUM(tblDctosOrdenesProgreso.cantidadPerdida)   "
                + "                                 AS cantidadPerdida,   "
                + "        SUM(tblDctosOrdenesProgreso.cantidadTerminada) "
                + "                                 AS cantidadTerminada, "
                + "        SUM(tblDctosOrdenesProgreso.pesoPerdido)       "
                + "                                      AS pesoPerdido,  "
                + "        SUM(tblDctosOrdenesProgreso.pesoTerminado)     "
                + "                                    AS pesoTerminado,  "
                + "        SUM(tblDctosOrdenesDetalle.cantidadTerminada - "
                + "            tblDctosOrdenesDetalle.cantidadFacturada)  "
                + "                                AS cantidadPendiente,  "
                + "        MAX(tblDctosOrdenesDetalle.cantidadFacturada)  "
                + "                                AS cantidadFacturada,  "
                + "        MAX(tblTerceros.nombreTercero)                 "
                + "                                     AS nombreTercero, "
                + "  MAX(tmpFIC.referenciaCliente) AS referenciaCliente,  "
                + "  MAX(tmpFIC.referencia) AS referencia                 "
                + " FROM   tblDctosOrdenes                                "
                + " INNER JOIN tblDctosOrdenesProgreso                    "
                + " ON tblDctosOrdenes.IDLOCAL      =                     "
                + "                      tblDctosOrdenesProgreso.idLocal  "
                + " AND tblDctosOrdenes.IDTIPOORDEN =                     "
                + "                  tblDctosOrdenesProgreso.idTipoOrden  "
                + " AND tblDctosOrdenes.IDORDEN     =                     "
                + "                      tblDctosOrdenesProgreso.idOrden  "
                + " INNER JOIN tblTerceros                                "
                + " ON tblDctosOrdenes.idCliente = tblTerceros.idCliente  "
                + " INNER JOIN tblJobOperacion                            "
                + " ON tblJobOperacion.idOperacion    =                   "
                + "                  tblDctosOrdenesProgreso.idOperacion  "
                + " INNER JOIN tblDctosOrdenesDetalle                     "
                + " ON  tblDctosOrdenes.IDLOCAL     =                     "
                + "                       tblDctosOrdenesDetalle.IDLOCAL  "
                + " AND tblDctosOrdenes.IDTIPOORDEN =                     "
                + "                   tblDctosOrdenesDetalle.IDTIPOORDEN  "
                + " AND tblDctosOrdenes.IDORDEN     =                     "
                + "                       tblDctosOrdenesDetalle.IDORDEN  "
                + " AND tblDctosOrdenesProgreso.itemPadre =               "
                + "                          tblDctosOrdenesDetalle.item  "
                + " INNER JOIN (SELECT tblPlusFicha.referenciaCliente     "
                + "                    ,tblPlusFicha.idFicha              "
                + "                    ,tblPlusFicha.referencia           "
                + "             FROM tblPlusFicha                         "
                + "             GROUP BY tblPlusFicha.referenciaCliente   "
                + "                      ,tblPlusFicha.idFicha            "
                + "                      ,tblPlusFicha.referencia)        "
                + "                                         AS tmpFIC     "
                + " ON tmpFIC.idFicha = tblDctosOrdenes.idFicha           "
                + " WHERE tblDctosOrdenes.IDLOCAL     =                   "
                + getIdLocal() + "                                        "
                + " AND   tblDctosOrdenes.IDTIPOORDEN =                   "
                + getIdTipoOrden() + "                                    "
                + " AND tblDctosOrdenesProgreso.idOperacion =             "
                + getIdOperacion() + "                                    "
                + " AND AND  tblDctosOrdenesProgreso.idControlTipo != 2   "
                + " AND tblDctosOrdenesDetalle.idEstadoRefOriginal != 9   "
                + " GROUP BY tblDctosOrdenesProgreso.idLocal,             "
                + "          tblDctosOrdenesProgreso.idTipoOrden,         "
                + "          tblDctosOrdenesProgreso.idOrden,             "
                + "          tblDctosOrdenesProgreso.idOperacion,         "
                + "          tblTerceros.idCliente,                       "
                + "          tblJobOperacion.nombreOperacion,             "
                + "          tblDctosOrdenesDetalle.item,                 "
                + "          tblDctosOrdenes.fechaOrden,                  "
                + "          tblDctosOrdenes.idLog,                       "
                + "          tblDctosOrdenes.idFicha,                     "
                + "          tblDctosOrdenes.ordenCompra,                 "
                + "          tblDctosOrdenes.numeroOrden,                 "
                + "          tblDctosOrdenesDetalle.fechaEntrega,         "
                + "          tblDctosOrdenesProgreso.itemPadre            ";

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
                fachadaBean = new FachadaDctoOrdenProgreso();

                //
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setIdOrden(rs.getInt("idOrden"));
                fachadaBean.setIdOperacion(rs.getInt("idOperacion"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setNombreOperacion(rs.getString("nombreOperacion"));
                fachadaBean.setItem(rs.getInt("item"));
                fachadaBean.setFechaOrden(rs.getString("fechaOrden"));
                fachadaBean.setIdOrden(rs.getInt("idOrden"));
                fachadaBean.setIdLog(rs.getInt("idLog"));
                fachadaBean.setIdFicha(rs.getInt("idFicha"));
                fachadaBean.setOrdenCompra(rs.getString("ordenCompra"));
                fachadaBean.setNumeroOrden(rs.getInt("numeroOrden"));
                fachadaBean.setFechaEntrega(rs.getString("fechaEntrega"));
                fachadaBean.setItemPadre(rs.getInt("itemPadre"));
                fachadaBean.setCantidad(rs.getDouble("cantidad"));
                fachadaBean.setCantidadPerdida(rs.getDouble("cantidadPerdida"));
                fachadaBean.setCantidadTerminada(
                        rs.getDouble("cantidadTerminada"));
                fachadaBean.setCantidadFacturada(rs.getDouble("cantidadFacturada"));
                fachadaBean.setPesoPerdido(rs.getDouble("pesoPerdido"));
                fachadaBean.setPesoTerminado(rs.getDouble("pesoTerminado"));
                fachadaBean.setCantidadFacturada(rs.getDouble("cantidadFacturada"));
                fachadaBean.setNombreTercero(rs.getString("nombreTercero"));
                fachadaBean.setReferenciaCliente(rs.getString("referenciaCliente"));
                fachadaBean.setReferencia(rs.getString("referencia"));

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

    // listaOTUnOperacionCliente
    public Vector listaOTUnOperacionCliente(int xIdTipoTercero) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        // Variable de fachada de los datos
        FachadaDctoOrdenProgreso fachadaBean;

        Connection connection = null;

        String selectStr =
" SELECT  tblDctosOrdenesDetalle.idLocal,                "
+ "         tblDctosOrdenesDetalle.idTipoOrden,          "
+ "         tblDctosOrdenesDetalle.idOrden,              "
+ "         tblDctosOrdenes.idCliente,                   "
+ "         tblDctosOrdenesDetalle.item,                 "
+ "         tblDctosOrdenes.fechaOrden,                  "
+ "         tblDctosOrdenes.idLog,                       "
+ "         tblDctosOrdenes.idFicha,                     "
+ "         tblDctosOrdenes.ordenCompra,                 "
+ "         tblDctosOrdenes.numeroOrden,                 "
+ "         tblDctosOrdenesDetalle.fechaEntrega ,        "
+ "         tblDctosOrdenesDetalle.itemPadre,            "
+ "         tblDctosOrdenesDetalle.cantidad,             "
+ "         tblDctosOrdenes.idTipoTx,             "
+ "      tblDctosOrdenesDetalle.cantidadFacturada,       "
+ "      tblDctosOrdenesDetalle.cantidadTerminada,       "
+ "         tblDctosOrdenesDetalle.pesoRetal             "
+ "                               AS pesoPerdido,        "
+ "         tblDctosOrdenesDetalle.pesoTerminado,        "
+ "    (tblDctosOrdenesDetalle.cantidadTerminada -       "
+ "     tblDctosOrdenesDetalle.cantidadFacturada)        "
+ "                         AS cantidadPendiente,        "
+ "     tblDctosOrdenesDetalle.cantidadFacturada,        "
+ "         tblTerceros.nombreTercero,                   "
+ "         tmpFIC.referenciaCliente,                    "
+ "         tmpFIC.referencia                            "
+ " FROM   tblDctosOrdenes                               "
+ " INNER JOIN tblDctosOrdenesDetalle                    "
+ " ON tblDctosOrdenes.IDLOCAL      =                    "
+ "        tblDctosOrdenesDetalle.IDLOCAL                "
+ " AND tblDctosOrdenes.IDTIPOORDEN =                    "
+ "    tblDctosOrdenesDetalle.IDTIPOORDEN                "
+ " AND tblDctosOrdenes.IDORDEN     =                    "
+ "        tblDctosOrdenesDetalle.IDORDEN                "
+ " INNER JOIN tblTerceros                               "
+ " ON tblDctosOrdenes.idCliente   =                     "
+ "                    tblTerceros.idCliente             "
+ " INNER JOIN                                           "
+ "   (SELECT tblPlusFicha.referenciaCliente             "
+ "          ,tblPlusFicha.idFicha                       "
+ "          ,tblPlusFicha.referencia                    "
+ "    FROM tblPlusFicha                                 "
+ "    GROUP BY tblPlusFicha.referenciaCliente           "
+ "            ,tblPlusFicha.idFicha                     "
+ "            ,tblPlusFicha.referencia)                 "
+ "                                  AS tmpFIC           "
+ " ON tmpFIC.idFicha = tblDctosOrdenes.idFicha          "
+ " WHERE tblDctosOrdenes.idLocal     =                  "
+ getIdLocal() + "                                       "
+ " AND  tblDctosOrdenes.idTipoOrden  =                  "
+ getIdTipoOrden() + "                                   "
+ " AND   tblDctosOrdenes.idCliente   =                 '"
+ getIdCliente() + "'                                    "
+ " AND ( tblDctosOrdenesDetalle.cantidadTerminada >     "
+ "        tblDctosOrdenesDetalle.cantidadFacturada )    "
+ " AND tblDctosOrdenesDetalle.idEstadoRefOriginal != 9  "
+ " AND   tblTerceros.idTipoTercero   = 1                "
+ " AND   tblDctosOrdenes.numeroOrden > 0 ";

/*                " SELECT tblDctosOrdenesProgreso.idLocal,                 "
                + "        tblDctosOrdenesProgreso.idTipoOrden,           "
                + "        tblDctosOrdenesProgreso.idOrden,               "
                + "        tblDctosOrdenesProgreso.idOperacion,           "
                + "        tblTerceros.idCliente,                         "
                + "        tblJobOperacion.nombreOperacion,               "
                + "        tblDctosOrdenesDetalle.item,                   "
                + "        tblDctosOrdenes.fechaOrden,                    "
                + "        tblDctosOrdenes.idLog,                         "
                + "        tblDctosOrdenes.idFicha,                       "
                + "        tblDctosOrdenes.ordenCompra,                   "
                + "        tblDctosOrdenes.numeroOrden,                   "
                + "        tblDctosOrdenesDetalle.fechaEntrega,           "
                + "        tblDctosOrdenesProgreso.itemPadre,             "
                + "        MAX(tblDctosOrdenesDetalle.cantidad)           "
                + "                                         AS cantidad,  "
                + "        MAX(tblDctosOrdenesDetalle.cantidadFacturada)  "
                + "                                AS cantidadFacturada,  "
                + "        SUM(tblDctosOrdenesProgreso.cantidadPerdida)   "
                + "                                 AS cantidadPerdida,   "
                + "        SUM(tblDctosOrdenesProgreso.cantidadTerminada) "
                + "                                 AS cantidadTerminada, "
                + "        SUM(tblDctosOrdenesProgreso.pesoPerdido)       "
                + "                                      AS pesoPerdido,  "
                + "        SUM(tblDctosOrdenesProgreso.pesoTerminado)     "
                + "                                    AS pesoTerminado,  "
                + "        SUM(tblDctosOrdenesDetalle.cantidadTerminada - "
                + "            tblDctosOrdenesDetalle.cantidadFacturada)  "
                + "                                AS cantidadPendiente,  "
                + "        MAX(tblDctosOrdenesDetalle.cantidadFacturada)  "
                + "                                AS cantidadFacturada,  "
                + "        MAX(tblTerceros.nombreTercero)                 "
                + "                                     AS nombreTercero, "
                + "  MAX(tmpFIC.referenciaCliente) AS referenciaCliente,  "
                + "  MAX(tmpFIC.referencia) AS referencia                 "
                + " FROM   tblDctosOrdenes                                "
                + " INNER JOIN tblDctosOrdenesProgreso                    "
                + " ON tblDctosOrdenes.IDLOCAL      =                     "
                + "                      tblDctosOrdenesProgreso.idLocal  "
                + " AND tblDctosOrdenes.IDTIPOORDEN =                     "
                + "                  tblDctosOrdenesProgreso.idTipoOrden  "
                + " AND tblDctosOrdenes.IDORDEN     =                     "
                + "                      tblDctosOrdenesProgreso.idOrden  "
                + " INNER JOIN tblTerceros                                "
                + " ON tblDctosOrdenes.idCliente = tblTerceros.idCliente  "
                + " INNER JOIN tblJobOperacion                            "
                + " ON tblJobOperacion.idOperacion    =                   "
                + "                  tblDctosOrdenesProgreso.idOperacion  "
                + " INNER JOIN tblDctosOrdenesDetalle                     "
                + " ON  tblDctosOrdenes.IDLOCAL     =                     "
                + "                       tblDctosOrdenesDetalle.IDLOCAL  "
                + " AND tblDctosOrdenes.IDTIPOORDEN =                     "
                + "                   tblDctosOrdenesDetalle.IDTIPOORDEN  "
                + " AND tblDctosOrdenes.IDORDEN     =                     "
                + "                       tblDctosOrdenesDetalle.IDORDEN  "
                + " AND tblDctosOrdenesProgreso.itemPadre =               "
                + "                          tblDctosOrdenesDetalle.item  "
                + " INNER JOIN (SELECT tblPlusFicha.referenciaCliente     "
                + "                    ,tblPlusFicha.idFicha              "
                + "                    ,tblPlusFicha.referencia           "
                + "             FROM tblPlusFicha                         "
                + "             GROUP BY tblPlusFicha.referenciaCliente   "
                + "                      ,tblPlusFicha.idFicha            "
                + "                      ,tblPlusFicha.referencia)        "
                + "                                         AS tmpFIC     "
                + " ON tmpFIC.idFicha = tblDctosOrdenes.idFicha           "
                + " WHERE tblDctosOrdenes.IDLOCAL     =                   "
                + getIdLocal() + "                                        "
                + " AND   tblDctosOrdenes.IDTIPOORDEN =                   "
                + getIdTipoOrden() + "                                    "
                + " AND tblDctosOrdenesProgreso.idOperacion =             "
                + getIdOperacion() + "                                    "
                + " AND   tblDctosOrdenes.idCliente   =                  '"
                + getIdCliente() + "'                                     "
                + " AND tblDctosOrdenesProgreso.idControlTipo != 2        "
                + " AND tblTerceros.idTipoTercero              = 1        "
                + " AND ( tblDctosOrdenesDetalle.cantidadTerminada >      "
                + "           tblDctosOrdenesDetalle.cantidadFacturada )  "
                + " AND tblDctosOrdenesDetalle.idEstadoRefOriginal != 9   "
                + " GROUP BY tblDctosOrdenesProgreso.idLocal,             "
                + "          tblDctosOrdenesProgreso.idTipoOrden,         "
                + "          tblDctosOrdenesProgreso.idOrden,             "
                + "          tblDctosOrdenesProgreso.idOperacion,         "
                + "          tblTerceros.idCliente,                       "
                + "          tblJobOperacion.nombreOperacion,             "
                + "          tblDctosOrdenesDetalle.item,                 "
                + "          tblDctosOrdenes.fechaOrden,                  "
                + "          tblDctosOrdenes.idLog,                       "
                + "          tblDctosOrdenes.idFicha,                     "
                + "          tblDctosOrdenes.ordenCompra,                 "
                + "          tblDctosOrdenes.numeroOrden,                 "
                + "          tblDctosOrdenesDetalle.fechaEntrega,         "
                + "          tblDctosOrdenesProgreso.itemPadre            ";*/

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
                fachadaBean = new FachadaDctoOrdenProgreso();

                //
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setIdOrden(rs.getInt("idOrden"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setItem(rs.getInt("item"));
                fachadaBean.setFechaOrden(rs.getString("fechaOrden"));
                fachadaBean.setIdLog(rs.getInt("idLog"));
                fachadaBean.setIdFicha(rs.getInt("idFicha"));
                fachadaBean.setOrdenCompra(rs.getString("ordenCompra"));
                fachadaBean.setNumeroOrden(rs.getInt("numeroOrden"));
                fachadaBean.setFechaEntrega(rs.getString("fechaEntrega"));
                fachadaBean.setItemPadre(rs.getInt("itemPadre"));
                fachadaBean.setCantidad(rs.getDouble("cantidad"));
                fachadaBean.setCantidadFacturada(rs.getDouble("cantidadFacturada"));
                fachadaBean.setCantidadTerminada(
                        rs.getDouble("cantidadTerminada"));
                fachadaBean.setPesoPerdido(rs.getDouble("pesoPerdido"));
                fachadaBean.setPesoTerminado(rs.getDouble("pesoTerminado"));
                fachadaBean.setCantidadFacturada(rs.getDouble("cantidadFacturada"));
                fachadaBean.setNombreTercero(rs.getString("nombreTercero"));
                fachadaBean.setReferenciaCliente(rs.getString("referenciaCliente"));
                fachadaBean.setReferencia(rs.getString("referencia"));
                fachadaBean.setIdControl(rs.getInt("idTipoTx"));

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

    // listaOTExterna
    public Vector listaOTExterna() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        // Variable de fachada de los datos
        FachadaDctoOrdenProgreso fachadaBean;

        Connection connection = null;

        String selectStr =
                " SELECT  tblDctosOrdenesProgreso.idOrden,              "
                + "         tblDctosOrdenesProgreso.item,               "
                + "         tblDctosOrdenesProgreso.cantidadPerdida,    "
                + "         tblDctosOrdenesProgreso.cantidadTerminada,  "
                + "         tblDctosOrdenesProgreso.pesoPerdido,        "
                + "         tblDctosOrdenesProgreso.pesoTerminado,      "
                + "         tblDctosOrdenesProgreso.fechaInicio,        "
                + "         tblDctosOrdenesProgreso.cantidadPedida,     "
                + "         tblDctosOrdenesProgreso.pesoPedido,         "
                + "         tblDctosOrdenesProgreso.idControl,          "
                + "         tblDctosOrdenesProgreso.idControlTipo,      "
                + "         tblDctosOrdenesProgreso.observacion,        "
                + "         tblJobOperacion.nombreOperacion,            "
                + "         tblTerceros.nombreTercero,                  "
                + "         tmpFIC.referenciaCliente,                   "
                + "         tmpFIC.referencia,                          "
                + "   (CASE                                             "
                + "    WHEN tblDctosOrdenesProgreso.idControlTipo = 2   "
                + "                                           THEN -1   "
                + "    ELSE +1                                          "
                + "    END) AS idSigno                                  "
                + " FROM    tblDctosOrdenesProgreso                     "
                + " INNER JOIN tblJobOperacion                          "
                + " ON tblDctosOrdenesProgreso.idOperacion =            "
                + "                    tblJobOperacion.idOperacion      "
                + " INNER JOIN tblTerceros                              "
                + " ON tblDctosOrdenesProgreso.idOperario  =            "
                + "                          tblTerceros.idCliente      "
                + " INNER JOIN tblDctosOrdenes                          "
                + " ON tblDctosOrdenes.idLocal             =            "
                + "                tblDctosOrdenesProgreso.idLocal      "
                + " AND tblDctosOrdenes.idTipoOrden        =            "
                + "            tblDctosOrdenesProgreso.idTipoOrden      "
                + " AND tblDctosOrdenes.idOrden            =            "
                + "                tblDctosOrdenesProgreso.idOrden      "
                + " INNER JOIN                                          "
                + "    ( SELECT  tblPlusFicha.referenciaCliente,        "
                + "              tblPlusFicha.idFicha,                  "
                + "              tblPlusFicha.referencia                "
                + "      FROM    tblPlusFicha                           "
                + "      GROUP BY tblPlusFicha.referenciaCliente,       "
                + "               tblPlusFicha.idFicha,                 "
                + "               tblPlusFicha.referencia )             "
                + "	                               AS tmpFIC        "
                + "    ON tmpFIC.idFicha                     =          "
                + "                      tblDctosOrdenes.idFicha        "
                + " WHERE  tblDctosOrdenes.idLocal           =          "
                + getIdLocal() + "                                      "
                + " AND    tblDctosOrdenes.idTipoOrden       =          "
                + getIdTipoOrden() + "                                  "
                + " AND    tblDctosOrdenes.numeroOrden       =          "
                + getNumeroOrden() + "                                  "
                + " AND    tblDctosOrdenesProgreso.itemPadre =          "
                + getItemPadre() + "                                    "
                + " AND  tblDctosOrdenesProgreso.idControlTipo          "
                + "                                   IN (2, 1)         "
                + " ORDER BY tblDctosOrdenesProgreso.idOrden,           "
                + "          tblDctosOrdenesProgreso.idPlu,             "
                + "          tblDctosOrdenesProgreso.item               ";

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
                fachadaBean = new FachadaDctoOrdenProgreso();

                //
                fachadaBean.setIdOrden(rs.getInt("idOrden"));
                fachadaBean.setItem(rs.getInt("item"));
                fachadaBean.setCantidadPerdida(rs.getDouble("cantidadPerdida"));
                fachadaBean.setCantidadTerminada(rs.getDouble("cantidadTerminada"));
                fachadaBean.setPesoPerdido(rs.getString("pesoPerdido"));
                fachadaBean.setPesoTerminado(rs.getString("pesoTerminado"));
                fachadaBean.setFechaInicio(rs.getString("fechaInicio"));
                fachadaBean.setCantidadPedida(rs.getDouble("cantidadPedida"));
                fachadaBean.setPesoPedido(rs.getDouble("pesoPedido"));
                fachadaBean.setIdControlTipo(rs.getInt("idControlTipo"));
                fachadaBean.setIdControl(rs.getInt("idControl"));
                fachadaBean.setObservacion(rs.getString("observacion"));
                fachadaBean.setNombreOperacion(rs.getString("nombreOperacion"));
                fachadaBean.setNombreTercero(rs.getString("nombreTercero"));
                fachadaBean.setReferenciaCliente(rs.getString("referenciaCliente"));
                fachadaBean.setReferencia(rs.getString("referencia"));
                fachadaBean.setIdSigno(rs.getInt("idSigno"));

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

    // listaOTExternaFCH
    public FachadaDctoOrdenProgreso listaOTExternaFCH() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        // Variable de fachada de los datos
        FachadaDctoOrdenProgreso  fachadaBean = new FachadaDctoOrdenProgreso();

        Connection connection = null;

        String selectStr =
                " SELECT  TOP 1 tblDctosOrdenesProgreso.idLocal,        "
                + "         tblDctosOrdenesProgreso.idTipoOrden,        "
                + "         tblDctosOrdenesProgreso.idOrden,            "
                + "         tblDctosOrdenesProgreso.itemPadre,          "
                + "         tblDctosOrdenesProgreso.item,               "
                + "         tblDctosOrdenesProgreso.cantidadPerdida,    "
                + "         tblDctosOrdenesProgreso.cantidadTerminada,  "
                + "         tblDctosOrdenesProgreso.pesoPerdido,        "
                + "         tblDctosOrdenesProgreso.pesoTerminado,      "
                + "         tblDctosOrdenesProgreso.fechaInicio,        "
                + "         tblDctosOrdenesProgreso.cantidadPedida,     "
                + "         tblDctosOrdenesProgreso.pesoPedido,         "
                + "         tblDctosOrdenesProgreso.idControl,          "
                + "         tblDctosOrdenesProgreso.idControlTipo,      "
                + "         tblDctosOrdenesProgreso.observacion,        "
                + "         tblJobOperacion.nombreOperacion,            "
                + "         tblTerceros.nombreTercero,                  "
                + "         tmpFIC.referenciaCliente,                   "
                + "         tmpFIC.referencia,                          "
                + "   (CASE                                             "
                + "    WHEN tblDctosOrdenesProgreso.idControlTipo = 2   "
                + "                                           THEN -1   "
                + "    ELSE +1                                          "
                + "    END) AS idSigno,                                 "
                + "         tblDctosOrdenes.numeroOrden                 "
                + " FROM    tblDctosOrdenesProgreso                     "
                + " INNER JOIN tblJobOperacion                          "
                + " ON tblDctosOrdenesProgreso.idOperacion =            "
                + "                    tblJobOperacion.idOperacion      "
                + " INNER JOIN tblTerceros                              "
                + " ON tblDctosOrdenesProgreso.idOperario  =            "
                + "                          tblTerceros.idCliente      "
                + " INNER JOIN tblDctosOrdenes                          "
                + " ON tblDctosOrdenes.idLocal             =            "
                + "                tblDctosOrdenesProgreso.idLocal      "
                + " AND tblDctosOrdenes.idTipoOrden        =            "
                + "            tblDctosOrdenesProgreso.idTipoOrden      "
                + " AND tblDctosOrdenes.idOrden            =            "
                + "                tblDctosOrdenesProgreso.idOrden      "
                + " INNER JOIN                                          "
                + "    ( SELECT  tblPlusFicha.referenciaCliente,        "
                + "              tblPlusFicha.idFicha,                  "
                + "              tblPlusFicha.referencia                "
                + "      FROM    tblPlusFicha                           "
                + "      GROUP BY tblPlusFicha.referenciaCliente,       "
                + "               tblPlusFicha.idFicha,                 "
                + "               tblPlusFicha.referencia )             "
                + "	                               AS tmpFIC        "
                + "    ON tmpFIC.idFicha                     =          "
                + "                      tblDctosOrdenes.idFicha        "
                + " WHERE  tblDctosOrdenes.idLocal           =          "
                + getIdLocal() + "                                      "
                + " AND    tblDctosOrdenes.idTipoOrden       =          "
                + getIdTipoOrden() + "                                  "
                + " AND    tblDctosOrdenes.numeroOrden       =          "
                + getNumeroOrden() + "                                  "
                + " AND    tblDctosOrdenesProgreso.itemPadre =          "
                + getItemPadre() + "                                    "
                + " AND  tblDctosOrdenesProgreso.idControlTipo          "
                + "                                   IN (2, 1)         "
                + " ORDER BY tblDctosOrdenesProgreso.idOrden,           "
                + "          tblDctosOrdenesProgreso.idPlu,             "
                + "          tblDctosOrdenesProgreso.item               ";

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
                fachadaBean.setItemPadre(rs.getInt("itemPadre"));
                fachadaBean.setIdOrden(rs.getInt("idOrden"));
                fachadaBean.setItem(rs.getInt("item"));
                fachadaBean.setCantidadPerdida(rs.getDouble("cantidadPerdida"));
                fachadaBean.setCantidadTerminada(rs.getDouble("cantidadTerminada"));
                fachadaBean.setPesoPerdido(rs.getString("pesoPerdido"));
                fachadaBean.setPesoTerminado(rs.getString("pesoTerminado"));
                fachadaBean.setFechaInicio(rs.getString("fechaInicio"));
                fachadaBean.setCantidadPedida(rs.getDouble("cantidadPedida"));
                fachadaBean.setPesoPedido(rs.getDouble("pesoPedido"));
                fachadaBean.setIdControlTipo(rs.getInt("idControlTipo"));
                fachadaBean.setIdControl(rs.getInt("idControl"));
                fachadaBean.setObservacion(rs.getString("observacion"));
                fachadaBean.setNombreOperacion(rs.getString("nombreOperacion"));
                fachadaBean.setNombreTercero(rs.getString("nombreTercero"));
                fachadaBean.setReferenciaCliente(rs.getString("referenciaCliente"));
                fachadaBean.setReferencia(rs.getString("referencia"));
                fachadaBean.setIdSigno(rs.getInt("idSigno"));
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

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {

            // Cierra la conexion
            jdbcAccess.cleanup(connection, selectStatement, null);
            return fachadaBean;

        }
    }

    // listaOTExternaAllMaterial
    public Vector listaOTExternaAllMaterial() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        // Variable de fachada de los datos
        FachadaDctoOrdenProgreso fachadaBean;

        Connection connection = null;

        String selectStr =
                "   SELECT  tblDctosOrdenes.idCliente,                          "
                + "           tblDctosOrdenes.numeroOrden,                     "
                + "           tblDctosOrdenesProgreso.itemPadre,               "
                + "           MAX(tmpTER.nombreTercero) AS nombreTercero,      "
                + "           SUM(tblDctosOrdenesProgreso.cantidadTerminada *  "
                + "           (CASE                                            "
                + "            WHEN tblDctosOrdenesProgreso.idControlTipo = 2  "
                + "                                             THEN -1        "
                + "            ELSE +1                                         "
                + "            END)) * (-1) AS cantidadTerminada,              "
                + "           SUM(tblDctosOrdenesProgreso.pesoTerminado *      "
                + "           (CASE                                            "
                + "            WHEN tblDctosOrdenesProgreso.idControlTipo = 2  "
                + "                                             THEN -1        "
                + "            ELSE +1                                         "
                + "            END)) * (-1) AS pesoTerminado,                  "
                + "           MAX(tblJobOperacion.nombreOperacion)             "
                + "                                     AS nombreOperacion,    "
                + "           MAX(tblTerceros.nombreTercero)                   "
                + "                            AS nombreTerceroOperacion,      "
                + "           MAX(tmpFIC.referenciaCliente)                    "
                + "                                     AS referenciaCliente   "
                + "   FROM    tblDctosOrdenesProgreso                          "
                + "   INNER JOIN tblJobOperacion                               "
                + "   ON tblDctosOrdenesProgreso.idOperacion =                 "
                + "                      tblJobOperacion.idOperacion           "
                + "   INNER JOIN tblTerceros                                   "
                + "   ON tblDctosOrdenesProgreso.idOperario  =                 "
                + "                            tblTerceros.idCliente           "
                + "   INNER JOIN tblDctosOrdenes                               "
                + "   ON tblDctosOrdenes.idLocal             =                 "
                + "                  tblDctosOrdenesProgreso.idLocal           "
                + "   AND tblDctosOrdenes.idTipoOrden        =                 "
                + "              tblDctosOrdenesProgreso.idTipoOrden           "
                + "   AND tblDctosOrdenes.idOrden            =                 "
                + "                  tblDctosOrdenesProgreso.idOrden           "
                + "   INNER JOIN ( SELECT tblTerceros.idCliente,               "
                + "                       MAX(tblTerceros.nombreTercero)       "
                + "                                    AS nombreTercero        "
                + "                FROM   tblTerceros                          "
                + "                GROUP BY tblTerceros.idCliente )            "
                + "                                           AS tmpTER        "
                + "   ON tmpTER.idCliente  =  tblDctosOrdenes.idCliente        "
                + "   INNER JOIN                                               "
                + "      ( SELECT  tblPlusFicha.referenciaCliente,             "
                + "                tblPlusFicha.idFicha,                       "
                + "                tblPlusFicha.referencia                     "
                + "        FROM    tblPlusFicha                                "
                + "        GROUP BY tblPlusFicha.referenciaCliente,            "
                + "                 tblPlusFicha.idFicha,                      "
                + "                 tblPlusFicha.referencia )                  "
                + "  	                               AS tmpFIC              "
                + "      ON tmpFIC.idFicha                     =               "
                + "                        tblDctosOrdenes.idFicha             "
                + "   WHERE  tblDctosOrdenes.idLocal           =               "
                + getIdLocal() + "                                             "
                + "   AND    tblDctosOrdenes.idTipoOrden       =               "
                + getIdTipoOrden() + "                                         "
                + "   AND  tblDctosOrdenesProgreso.idControlTipo               "
                + "                                     IN (2, 1)              "
                + "   AND  tblDctosOrdenesProgreso.idPlu       = 0             "
                + "   AND  EXISTS (                                            "
                + "   SELECT tblDctosOrdenesDetalle.*                          "
                + "   FROM tblDctosOrdenesDetalle                              "
                + "   WHERE tblDctosOrdenesDetalle.idLocal              =      "
                + "                       tblDctosOrdenes.idLocal              "
                + "   AND   tblDctosOrdenesDetalle.idTipoOrden          =      "
                + "                   tblDctosOrdenes.idTipoOrden              "
                + "   AND   tblDctosOrdenesDetalle.idOrden              =      "
                + "                        tblDctosOrdenes.idOrden             "
                + "   AND   tblDctosOrdenesDetalle.idEstadoRefOriginal != 9 )  "
                + "   GROUP BY tblDctosOrdenes.idCliente,                      "
                + "            tblDctosOrdenes.numeroOrden,                    "
                + "            tblDctosOrdenesProgreso.itemPadre               ";

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
                fachadaBean = new FachadaDctoOrdenProgreso();

                //
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setNumeroOrden(rs.getInt("numeroOrden"));
                fachadaBean.setItemPadre(rs.getInt("itemPadre"));
                fachadaBean.setCantidadTerminada(rs.getDouble("cantidadTerminada"));
                fachadaBean.setPesoTerminado(rs.getString("pesoTerminado"));
                fachadaBean.setNombreOperacion(rs.getString("nombreOperacion"));
                fachadaBean.setNombreTercero(rs.getString("nombreTercero"));
                fachadaBean.setReferenciaCliente(rs.getString("referenciaCliente"));
                fachadaBean.setNombreTerceroOperacion(
                        rs.getString("nombreTerceroOperacion"));



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

    // listaOTExternaAllSuministro
    public Vector listaOTExternaAllSuministro() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        // Variable de fachada de los datos
        FachadaDctoOrdenProgreso fachadaBean;

        Connection connection = null;

        String selectStr =
                "   SELECT  tblDctosOrdenesProgreso.idPlu,                     "
                + "           tmpSUM.nombrePlu,                                "
                + "           SUM(tblDctosOrdenesProgreso.cantidadTerminada *  "
                + "           (CASE                                            "
                + "            WHEN tblDctosOrdenesProgreso.idControlTipo = 2  "
                + "                                             THEN -1        "
                + "            ELSE +1                                         "
                + "            END)) * (-1) AS cantidadTerminada,              "
                + "           SUM(tblDctosOrdenesProgreso.pesoTerminado *      "
                + "           (CASE                                            "
                + "            WHEN tblDctosOrdenesProgreso.idControlTipo = 2  "
                + "                                             THEN -1        "
                + "            ELSE +1                                         "
                + "            END)) * (-1) AS pesoTerminado,                  "
                + "           MAX(tblTerceros.nombreTercero)                   "
                + "                            AS nombreTerceroOperacion       "
                + "   FROM    tblDctosOrdenesProgreso                          "
                + "   INNER JOIN tblTerceros                                   "
                + "   ON tblDctosOrdenesProgreso.idOperario  =                 "
                + "                            tblTerceros.idCliente           "
                + "   INNER JOIN tblDctosOrdenes                               "
                + "   ON tblDctosOrdenes.idLocal             =                 "
                + "                  tblDctosOrdenesProgreso.idLocal           "
                + "   AND tblDctosOrdenes.idTipoOrden        =                 "
                + "              tblDctosOrdenesProgreso.idTipoOrden           "
                + "   AND tblDctosOrdenes.idOrden            =                 "
                + "                  tblDctosOrdenesProgreso.idOrden           "
                + "   INNER JOIN                                               "
                + "      ( SELECT  tblPlusFicha.referenciaCliente,             "
                + "                tblPlusFicha.idFicha,                       "
                + "                tblPlusFicha.referencia                     "
                + "        FROM    tblPlusFicha                                "
                + "        GROUP BY tblPlusFicha.referenciaCliente,            "
                + "                 tblPlusFicha.idFicha,                      "
                + "                 tblPlusFicha.referencia )                  "
                + "  	                               AS tmpFIC               "
                + "      ON tmpFIC.idFicha                     =               "
                + "                        tblDctosOrdenes.idFicha             "
                + "      INNER JOIN                                            "
                + "      ( SELECT  tblPlus.idPlu,                              "
                + "                        tblCategorias.nombreCategoria +     "
                + "                                                  ' ' +     "
                + "                        tblPlus.nombrePlu AS nombrePlu      "
                + "                FROM    tblCategorias                       "
                + "                INNER JOIN tblPlus                          "
                + "                ON tblCategorias.idLinea      =             "
                + "                                      tblPlus.idLinea       "
                + "                AND tblCategorias.IdCategoria =             "
                + "                                  tblPlus.idCategoria )     "
                + "  	                               AS tmpSUM               "
                + "      ON tmpSUM.idPlu                     =                 "
                + "                       tblDctosOrdenesProgreso.idPlu        "
                + "   WHERE  tblDctosOrdenes.idLocal           =               "
                + getIdLocal() + "                                             "
                + "   AND    tblDctosOrdenes.idTipoOrden       =               "
                + getIdTipoOrden() + "                                         "
                + "   AND  tblDctosOrdenesProgreso.idControlTipo               "
                + "                                     IN (2, 1)              "
                + "   AND  tblDctosOrdenesProgreso.idPlu      != 0             "
                + "   GROUP BY tblDctosOrdenesProgreso.idPlu,                  "
                + "           tmpSUM.nombrePlu                                 ";

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
                fachadaBean = new FachadaDctoOrdenProgreso();

                //
                fachadaBean.setIdPlu(rs.getString("idPlu"));
                fachadaBean.setNombrePlu(rs.getString("nombrePlu"));
                fachadaBean.setCantidadTerminada(rs.getDouble("cantidadTerminada"));
                fachadaBean.setPesoTerminado(rs.getString("pesoTerminado"));
                fachadaBean.setNombreTerceroOperacion(
                        rs.getString("nombreTerceroOperacion"));

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

    // inventarioOTExternaFCH
    public FachadaDctoOrdenProgreso inventarioOTExternaFCH() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        // Variable de fachada de los datos
        FachadaDctoOrdenProgreso fachadaBean = new FachadaDctoOrdenProgreso();

        Connection connection = null;

        String selectStr =
                " SELECT  tblDctosOrdenesProgreso.idOrden,              "
                + "         tblDctosOrdenesProgreso.idPlu,              "
                + "   SUM ( tblDctosOrdenesProgreso.cantidadTerminada * "
                + "   (CASE                                             "
                + "    WHEN tblDctosOrdenesProgreso.idControlTipo = 2   "
                + "                                           THEN -1   "
                + "    ELSE +1                                          "
                + "    END) ) AS  cantidadTerminada,                    "
                + "   SUM( tblDctosOrdenesProgreso.pesoTerminado   *    "
                + "   (CASE                                             "
                + "    WHEN tblDctosOrdenesProgreso.idControlTipo = 2   "
                + "                                           THEN -1   "
                + "    ELSE +1                                          "
                + "    END) ) AS  pesoTerminado                         "
                + " FROM    tblDctosOrdenesProgreso                     "
                + " INNER JOIN tblJobOperacion                          "
                + " ON tblDctosOrdenesProgreso.idOperacion =            "
                + "                    tblJobOperacion.idOperacion      "
                + " INNER JOIN tblTerceros                              "
                + " ON tblDctosOrdenesProgreso.idOperario  =            "
                + "                          tblTerceros.idCliente      "
                + " INNER JOIN tblDctosOrdenes                          "
                + " ON tblDctosOrdenes.idLocal             =            "
                + "                tblDctosOrdenesProgreso.idLocal      "
                + " AND tblDctosOrdenes.idTipoOrden        =            "
                + "            tblDctosOrdenesProgreso.idTipoOrden      "
                + " AND tblDctosOrdenes.idOrden            =            "
                + "                tblDctosOrdenesProgreso.idOrden      "
                + " INNER JOIN                                          "
                + "    ( SELECT  tblPlusFicha.referenciaCliente,        "
                + "              tblPlusFicha.idFicha,                  "
                + "              tblPlusFicha.referencia                "
                + "      FROM    tblPlusFicha                           "
                + "      GROUP BY tblPlusFicha.referenciaCliente,       "
                + "               tblPlusFicha.idFicha,                 "
                + "               tblPlusFicha.referencia )             "
                + "	                               AS tmpFIC        "
                + "    ON tmpFIC.idFicha                     =          "
                + "                      tblDctosOrdenes.idFicha        "
                + " WHERE  tblDctosOrdenes.idLocal           =          "
                + getIdLocal() + "                                      "
                + " AND    tblDctosOrdenes.idTipoOrden       =          "
                + getIdTipoOrden() + "                                  "
                + " AND    tblDctosOrdenes.numeroOrden       =          "
                + getNumeroOrden() + "                                  "
                + " AND    tblDctosOrdenesProgreso.itemPadre =          "
                + getItemPadre() + "                                    "
                + " AND  tblDctosOrdenesProgreso.idControlTipo          "
                + "                                   IN (2, 1)         "
                + " GROUP BY tblDctosOrdenesProgreso.idOrden,           "
                + "          tblDctosOrdenesProgreso.idPlu              ";

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
                fachadaBean.setIdOrden(rs.getInt("idOrden"));
                fachadaBean.setIdPlu(rs.getInt("idPlu"));
                fachadaBean.setCantidadTerminada(
                        rs.getDouble("cantidadTerminada"));
                fachadaBean.setPesoTerminado(rs.getString("pesoTerminado"));

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

    // listaFichaCH
    public FachadaDctoOrdenProgreso listaFichaCH(int xIdLog) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        //
        FachadaDctoOrdenProgreso fachadaBean = new FachadaDctoOrdenProgreso();

        Connection connection = null;

        //
        String selectStr =
                " SELECT tblPlusFicha.referencia         "
                + " FROM   tblDctosOrdenes               "
                + " INNER JOIN tblPlusFicha              "
                + " ON tblDctosOrdenes.idFicha =         "
                + "           tblPlusFicha.idFicha       "
                + " WHERE tblDctosOrdenes.IDLOCAL     =  "
                + getIdLocal() + "                       "
                + " AND   tblDctosOrdenes.IDTIPOORDEN =  "
                + getIdTipoOrden() + "                   "
                + " AND   tblDctosOrdenes.IDLOG       =  "
                + xIdLog;

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
                fachadaBean.setFechaFin(rs.getString("referencia"));

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
