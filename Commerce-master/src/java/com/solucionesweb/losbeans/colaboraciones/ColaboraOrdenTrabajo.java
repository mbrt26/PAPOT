package com.solucionesweb.losbeans.colaboraciones;

// Importa la clase helper JDBCAccess que contiene los accesos a la base de datos
import com.solucionesweb.lasayudas.JDBCAccess;

// Importa el bean de utilidades
import com.solucionesweb.losbeans.utilidades.IConstantes;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaPluFicha;

// Importa los paquetes de java
import java.sql.*;
import java.io.*;
import java.util.*;

// Importa los paquetes de javax
import javax.naming.*;

public class ColaboraOrdenTrabajo extends FachadaPluFicha
        implements Serializable, IConstantes {

    // Nombre JNDI del Data Source que esta clase requiere
    private static final String DATA_SOURCE_NAME = "CommerceAccess";
    // Clase helper usada para acceder a la base de datos
    private JDBCAccess jdbcAccess;

    // Metodo constructor por defecto sin parametros
    public ColaboraOrdenTrabajo() {

        // Al instanciar el Bean establece la conexion a la base de datos
        jdbcAccess = new JDBCAccess(DATA_SOURCE_NAME);
    }

    // listaUnOrdenDetalle
    public Vector listaUnOrdenDetalle() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        //
        String selectStr =
                " SELECT     tblPlusFicha.idCliente,          "
                + "            tblPlusFicha.referenciaCliente,  "
                + "            tblPlusFicha.idOperacion,      "
                + "            tblPlusFicha.idPlu,              "
                + "            tblPlusFicha.nombreReferencia,   "
                + "            tmpESC.nombreEscala,             "
                + "            tmpESC.idTipoEscala,             "
                + "            tblPlusFicha.idEscala,           "
                + "            tblPlusFicha.item,               "
                + "            tblPlusFicha.vrEscala,           "
                + "            tblPlusFicha.textoEscala,        "
                + "            'MAGNITUD' AS nombreItem,        "
                + "            tblPlusFicha.estado              "
                + " FROM         tblPlusFicha                   "
                + " INNER JOIN                                  "
                + " (SELECT tblJobEscalaDetalle.idEscala,       "
                + "         tblJobEscalaDetalle.item,           "
                + "         tblJobEscala.nombreEscala,          "
                + "         tblJobEscala.idTipoEscala,          "
                + "         tblJobEscalaDetalle.nombreItem      "
                + "  FROM   tblJobEscala                        "
                + "  INNER JOIN tblJobEscalaDetalle             "
                + "  ON tblJobEscala.idEscala =                 "
                + "          tblJobEscalaDetalle.idEscala)      "
                + "                              AS tmpESC      "
                + " ON tblPlusFicha.idEscala  = tmpESC.idEscala "
                + " WHERE tmpESC.idTipoEscala = 1               "
                + " AND   tblPlusFicha.idCliente =             '"
                + getIdCliente() + "'                           "
                + " AND  tblPlusFicha.idOperacion =             "
                + getIdOperacion() + "                          "
                + " AND  tblPlusFicha.idFicha     =             "
                + getIdFicha() + "                              "
                + " UNION ALL                                   "
                + " SELECT     tblPlusFicha.idCliente,          "
                + "            tblPlusFicha.referenciaCliente,  "
                + "            tblPlusFicha.idOperacion,      "
                + "            tblPlusFicha.idPlu,              "
                + "            tblPlusFicha.nombreReferencia,   "
                + "            tmpESC.nombreEscala,             "
                + "            tmpESC.idTipoEscala,             "
                + "            tblPlusFicha.idEscala,           "
                + "            tblPlusFicha.item,               "
                + "            tblPlusFicha.vrEscala,           "
                + "            tblPlusFicha.textoEscala,        "
                + "            tmpESC.nombreItem,               "
                + "            tblPlusFicha.estado              "
                + " FROM         tblPlusFicha                   "
                + " INNER JOIN                                  "
                + " (SELECT tblJobEscalaDetalle.idEscala,       "
                + "         tblJobEscalaDetalle.item,           "
                + "         tblJobEscala.nombreEscala,          "
                + "         tblJobEscala.idTipoEscala,          "
                + "         tblJobEscalaDetalle.nombreItem      "
                + "  FROM   tblJobEscala                        "
                + "  INNER JOIN tblJobEscalaDetalle             "
                + "  ON tblJobEscala.idEscala =                 "
                + "          tblJobEscalaDetalle.idEscala)      "
                + "                              AS tmpESC      "
                + " ON tblPlusFicha.idEscala  = tmpESC.idEscala "
                + " AND tblPlusFicha.vrEscala = tmpESC.item     "
                + " WHERE tmpESC.idTipoEscala = 2               "
                + " AND   tblPlusFicha.idCliente =             '"
                + getIdCliente() + "'                           "
                + " AND  tblPlusFicha.idOperacion =             "
                + getIdOperacion() + "                          "
                + " AND  tblPlusFicha.idFicha     =             "
                + getIdFicha() + "                              "
                + " UNION ALL                                   "
                + " SELECT     tblPlusFicha.idCliente,          "
                + "            tblPlusFicha.referenciaCliente,  "
                + "            tblPlusFicha.idOperacion,      "
                + "            tblPlusFicha.idPlu,              "
                + "            tblPlusFicha.nombreReferencia,   "
                + "            tmpESC.nombreEscala,             "
                + "            tmpESC.idTipoEscala,             "
                + "            tblPlusFicha.idEscala,           "
                + "            tblPlusFicha.item,               "
                + "            tblPlusFicha.vrEscala,           "
                + "            tblPlusFicha.textoEscala,        "
                + "            'OBSERVACION' AS nombreItem,     "
                + "            tblPlusFicha.estado              "
                + " FROM         tblPlusFicha                   "
                + " INNER JOIN                                  "
                + " (SELECT tblJobEscalaDetalle.idEscala,       "
                + "         tblJobEscalaDetalle.item,           "
                + "         tblJobEscala.nombreEscala,          "
                + "         tblJobEscala.idTipoEscala,          "
                + "         tblJobEscalaDetalle.nombreItem      "
                + "  FROM   tblJobEscala                        "
                + "  INNER JOIN tblJobEscalaDetalle             "
                + "  ON tblJobEscala.idEscala =                 "
                + "          tblJobEscalaDetalle.idEscala)      "
                + "                              AS tmpESC      "
                + " ON tblPlusFicha.idEscala  = tmpESC.idEscala "
                + " WHERE tmpESC.idTipoEscala = 3               "
                + " AND   tblPlusFicha.idCliente =             '"
                + getIdCliente() + "'                           "
                + " AND  tblPlusFicha.idOperacion =             "
                + getIdOperacion() + "                          "
                + " AND  tblPlusFicha.idFicha     =             "
                + getIdFicha() + "                              "
                + " ORDER BY 8, 4, 7                            ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaPluFicha fachadaBean;

            while (rs.next()) {

                //
                fachadaBean = new FachadaPluFicha();

                //
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setReferenciaCliente(
                        rs.getString("referenciaCliente"));
                fachadaBean.setIdOperacion(rs.getInt("idOperacion"));
                fachadaBean.setIdPlu(rs.getInt("idPlu"));
                fachadaBean.setNombreReferencia(
                        rs.getString("nombreReferencia"));
                fachadaBean.setNombreEscala(rs.getString("nombreEscala"));
                fachadaBean.setIdTipoEscala(rs.getInt("idTipoEscala"));
                fachadaBean.setIdEscala(rs.getInt("idEscala"));
                fachadaBean.setItem(rs.getInt("item"));
                fachadaBean.setVrEscala(rs.getDouble("vrEscala"));
                fachadaBean.setTextoEscala(rs.getString("textoEscala"));
                fachadaBean.setEstado(rs.getInt("estado"));
                fachadaBean.setNombreItem(rs.getString("nombreItem"));

                // magnitud
                if (fachadaBean.getIdTipoEscala() == 1) {

                    fachadaBean.setTextoEscala(fachadaBean.getVrEscalaFormato());

                }

                // varios
                if (fachadaBean.getIdTipoEscala() == 2) {

                    fachadaBean.setTextoEscala(fachadaBean.getNombreItem());

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

    // listaUnPedido
    public Vector listaUnPedido() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        //
        String selectStr =
                " SELECT tblPlusFicha.idCliente,                   "
                + "        tblPlusFicha.referenciaCliente,         "
                + "        tblPlusFicha.idPlu,                     "
                + "        tblPlusFicha.nombreReferencia,          "
                + "        tblPlusFicha.idOperacion,               "
                + "        tblPlus.nombrePlu,                      "
                + "        tblPlusFicha.idFicha,                   "
                + "        tblPlusFicha.referencia,                "
                + "        tmpORD.numeroOrden,                     "
                + "        tmpORD.cantidad                         "
                + " FROM   tblPlusFicha                            "
                + " INNER JOIN tblPlus                             "
                + " ON tblPlusFicha.idPlu = tblPlus.idPlu          "
                + " INNER JOIN                                     "
                + " ( SELECT tblDctosOrdenes.idCliente,            "
                + "          tblDctosOrdenes.idLocal,              "
                + "          tblDctosOrdenes.idTipoOrden,          "
                + "          tblDctosOrdenes.idOrden,              "
                + "          tblDctosOrdenes.idFicha,              "
                + "          tblDctosOrdenes.numeroOrden,          "
                + "          MAX(tblDctosOrdenesDetalle.CANTIDAD)  "
                + "                       AS cantidad              "
                + "   FROM   tblDctosOrdenes                       "
                + "   INNER JOIN tblDctosOrdenesDetalle            "
                + "   ON tblDctosOrdenes.IDLOCAL      =            "
                + "              tblDctosOrdenesDetalle.IDLOCAL    "
                + "   AND tblDctosOrdenes.IDTIPOORDEN =            "
                + "          tblDctosOrdenesDetalle.IDTIPOORDEN    "
                + "   AND tblDctosOrdenes.IDORDEN     =            "
                + "              tblDctosOrdenesDetalle.IDORDEN    "
                + "   GROUP BY tblDctosOrdenes.idCliente,          "
                + "          tblDctosOrdenes.idLocal,              "
                + "          tblDctosOrdenes.idTipoOrden,          "
                + "          tblDctosOrdenes.idOrden,              "
                + "          tblDctosOrdenes.numeroOrden,          "
                + "          tblDctosOrdenes.idFicha )             "
                + "                                      AS tmpORD "
                + " ON tmpORD.idCliente          =                 "
                + "                         tblPlusFicha.idCliente "
                + " AND tmpORD.idFicha           =                 "
                + "                           tblPlusFicha.idFicha "
                + " WHERE   tblPlusFicha.idCliente =              '"
                + getIdCliente() + "'                              "
                + " AND   tblPlusFicha.idOperacion = 1             "
                + " GROUP BY tblPlusFicha.idCliente,               "
                + "          tblPlusFicha.referenciaCliente,       "
                + "          tblPlusFicha.idPlu,                   "
                + "          tblPlusFicha.nombreReferencia,        "
                + "          tblPlusFicha.idOperacion,             "
                + "          tblPlus.nombrePlu,                    "
                + "          tblPlusFicha.idFicha,                 "
                + "          tblPlusFicha.referencia,              "
                + "          tmpORD.numeroOrden,                   "
                + "          tmpORD.cantidad                       "
                + " ORDER BY tblPlusFicha.idCliente,               "
                + "          tblPlusFicha.referenciaCliente        ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaPluFicha fachadaBean;

            while (rs.next()) {

                //
                fachadaBean = new FachadaPluFicha();

                //
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setReferenciaCliente(
                        rs.getString("referenciaCliente"));
                fachadaBean.setIdOperacion(rs.getInt("idOperacion"));
                fachadaBean.setIdPlu(rs.getInt("idPlu"));
                fachadaBean.setNombreReferencia(
                        rs.getString("nombreReferencia"));
                fachadaBean.setNombrePlu(
                        rs.getString("nombrePlu"));
                fachadaBean.setCantidad(
                        rs.getDouble("cantidad"));
                fachadaBean.setIdFicha(rs.getInt("idFicha"));
                fachadaBean.setReferencia(
                        rs.getString("referencia"));
                fachadaBean.setNumeroOrden(rs.getInt("numeroOrden"));

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

    // listaUnOrden
    public Vector listaUnOrden() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        //
        String selectStr =
                " SELECT tblPlusFicha.idCliente,                   "
                + "        tblPlusFicha.referenciaCliente,         "
                + "        tblPlusFicha.idPlu,                     "
                + "        tblPlusFicha.nombreReferencia,          "
                + "        tblPlusFicha.idOperacion,               "
                + "        tblPlus.nombrePlu,                      "
                + "        tblPlusFicha.idFicha,                   "
                + "        tblPlusFicha.referencia,                "
                + "        tmpORD.numeroOrden,                     "
                + "        tmpORD.cantidad                         "
                + " FROM   tblPlusFicha                            "
                + " INNER JOIN tblPlus                             "
                + " ON tblPlusFicha.idPlu = tblPlus.idPlu          "
                + " INNER JOIN                                     "
                + " ( SELECT tblDctosOrdenes.idCliente,            "
                + "          tblDctosOrdenes.idLocal,              "
                + "          tblDctosOrdenes.idTipoOrden,          "
                + "          tblDctosOrdenes.idOrden,              "
                + "          tblDctosOrdenes.idFicha,              "
                + "          tblDctosOrdenes.numeroOrden,          "
                + "          MAX(tblDctosOrdenesDetalle.CANTIDAD)  "
                + "                       AS cantidad              "
                + "   FROM   tblDctosOrdenes                       "
                + "   INNER JOIN tblDctosOrdenesDetalle            "
                + "   ON tblDctosOrdenes.IDLOCAL      =            "
                + "              tblDctosOrdenesDetalle.IDLOCAL    "
                + "   AND tblDctosOrdenes.IDTIPOORDEN =            "
                + "          tblDctosOrdenesDetalle.IDTIPOORDEN    "
                + "   AND tblDctosOrdenes.IDORDEN     =            "
                + "              tblDctosOrdenesDetalle.IDORDEN    "
                + "   GROUP BY tblDctosOrdenes.idCliente,          "
                + "          tblDctosOrdenes.idLocal,              "
                + "          tblDctosOrdenes.idTipoOrden,          "
                + "          tblDctosOrdenes.idOrden,              "
                + "          tblDctosOrdenes.numeroOrden,          "
                + "          tblDctosOrdenes.idFicha )             "
                + "                                      AS tmpORD "
                + " ON tmpORD.idCliente          =                 "
                + "                         tblPlusFicha.idCliente "
                + " AND tmpORD.idFicha           =                 "
                + "                           tblPlusFicha.idFicha "
                + " WHERE   tmpORD.numeroOrden   =                 "
                + getNumeroOrden() + "                             "
                + " AND   tblPlusFicha.idOperacion = 1             "
                + " GROUP BY tblPlusFicha.idCliente,               "
                + "          tblPlusFicha.referenciaCliente,       "
                + "          tblPlusFicha.idPlu,                   "
                + "          tblPlusFicha.nombreReferencia,        "
                + "          tblPlusFicha.idOperacion,             "
                + "          tblPlus.nombrePlu,                    "
                + "          tblPlusFicha.idFicha,                 "
                + "          tblPlusFicha.referencia,              "
                + "          tmpORD.numeroOrden,                   "
                + "          tmpORD.cantidad                       "
                + " ORDER BY tblPlusFicha.idCliente,               "
                + "          tblPlusFicha.referenciaCliente        ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaPluFicha fachadaBean;

            //
            while (rs.next()) {

                //
                fachadaBean = new FachadaPluFicha();

                //
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setReferenciaCliente(
                        rs.getString("referenciaCliente"));
                fachadaBean.setIdOperacion(rs.getInt("idOperacion"));
                fachadaBean.setIdPlu(rs.getInt("idPlu"));
                fachadaBean.setNombreReferencia(
                        rs.getString("nombreReferencia"));
                fachadaBean.setNombrePlu(
                        rs.getString("nombrePlu"));
                fachadaBean.setCantidad(
                        rs.getDouble("cantidad"));
                fachadaBean.setIdFicha(rs.getInt("idFicha"));
                fachadaBean.setReferencia(
                        rs.getString("referencia"));
                fachadaBean.setNumeroOrden(rs.getInt("numeroOrden"));

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

    // listaUnOrdenItem
    public Vector listaUnOrdenItem() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        //
        String selectStr =
                "   SELECT tblPlusFicha.idCliente,                       "
                + "          tblPlusFicha.referenciaCliente,             "
                + "          tblPlusFicha.idPlu,                         "
                + "          tblPlusFicha.nombreReferencia,              "
                + "          tblPlusFicha.idOperacion,                   "
                + "          tblPlus.nombrePlu,                          "
                + "          tblPlusFicha.idFicha,                       "
                + "          tblPlusFicha.referencia,                    "
                + "          tmpORD.numeroOrden,                         "
                + "          tmpORD.cantidad,                            "
                + "          tmpORD.item,                                "
                + "          tmpORD.fechaEntrega,                        "
                + "          tmpORD.idLog                                "
                + "   FROM   tblPlusFicha                                "
                + "   INNER JOIN tblPlus                                 "
                + "   ON tblPlusFicha.idPlu = tblPlus.idPlu              "
                + "   INNER JOIN                                         "
                + "   ( SELECT tblDctosOrdenes.idCliente,                "
                + "            tblDctosOrdenes.idLocal,                  "
                + "            tblDctosOrdenes.idTipoOrden,              "
                + "            tblDctosOrdenes.idOrden,                  "
                + "            tblDctosOrdenes.idFicha,                  "
                + "            tblDctosOrdenes.idLog,                    "
                + "            tblDctosOrdenes.numeroOrden,              "
                + "            tblDctosOrdenesDetalle.item,              "
                + "            MAX(tblDctosOrdenesDetalle.CANTIDAD)      "
                + "                         AS cantidad,                 "
                + "            MAX(tblDctosOrdenesDetalle.fechaEntrega)  "
                + "                         AS fechaEntrega              "
                + "     FROM   tblDctosOrdenes                           "
                + "     INNER JOIN tblDctosOrdenesDetalle                "
                + "     ON tblDctosOrdenes.IDLOCAL      =                "
                + "                tblDctosOrdenesDetalle.IDLOCAL        "
                + "     AND tblDctosOrdenes.IDTIPOORDEN =                "
                + "            tblDctosOrdenesDetalle.IDTIPOORDEN        "
                + "     AND tblDctosOrdenes.IDORDEN     =                "
                + "                tblDctosOrdenesDetalle.IDORDEN        "
                + "     GROUP BY tblDctosOrdenes.idCliente,              "
                + "            tblDctosOrdenes.idLocal,                  "
                + "            tblDctosOrdenes.idTipoOrden,              "
                + "            tblDctosOrdenes.idOrden,                  "
                + "            tblDctosOrdenes.idLog,                    "
                + "            tblDctosOrdenes.numeroOrden,              "
                + "            tblDctosOrdenesDetalle.item,              "
                + "            tblDctosOrdenes.idFicha )                 "
                + "                                        AS tmpORD     "
                + "   ON tmpORD.idCliente          =                     "
                + "                           tblPlusFicha.idCliente     "
                + "   AND tmpORD.idFicha           =                     "
                + "                             tblPlusFicha.idFicha     "
                + " WHERE   tmpORD.numeroOrden   =                       "
                + getNumeroOrden() + "                                   "
                + "   AND   tblPlusFicha.idOperacion = 1                 "
                + "   GROUP BY tblPlusFicha.idCliente,                   "
                + "            tblPlusFicha.referenciaCliente,           "
                + "            tblPlusFicha.idPlu,                       "
                + "            tblPlusFicha.nombreReferencia,            "
                + "            tblPlusFicha.idOperacion,                 "
                + "            tblPlus.nombrePlu,                        "
                + "            tblPlusFicha.idFicha,                     "
                + "            tblPlusFicha.referencia,                  "
                + "            tmpORD.numeroOrden,                       "
                + "            tmpORD.cantidad,                          "
                + "            tmpORD.item,                              "
                + "            tmpORD.fechaEntrega,                      "
                + "            tmpORD.idLog                              "
                + "   ORDER BY tmpORD.item                               ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaPluFicha fachadaBean;

            //
            while (rs.next()) {

                //
                fachadaBean = new FachadaPluFicha();

                //
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setReferenciaCliente(
                        rs.getString("referenciaCliente"));
                fachadaBean.setIdOperacion(rs.getInt("idOperacion"));
                fachadaBean.setIdPlu(rs.getInt("idPlu"));
                fachadaBean.setNombreReferencia(
                        rs.getString("nombreReferencia"));
                fachadaBean.setNombrePlu(
                        rs.getString("nombrePlu"));
                fachadaBean.setCantidad(
                        rs.getDouble("cantidad"));
                fachadaBean.setIdFicha(rs.getInt("idFicha"));
                fachadaBean.setReferencia(
                        rs.getString("referencia"));
                fachadaBean.setNumeroOrden(rs.getInt("numeroOrden"));
                fachadaBean.setItem(rs.getInt("item"));
                fachadaBean.setFechaEntrega(rs.getString("fechaEntrega"));
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
            return contenedor;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {

            // Cierra la conexion
            jdbcAccess.cleanup(connection, selectStatement, null);
            return contenedor;

        }
    }

    // listaUnOT
    public Vector listaUnOT(int xItemPadre) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        //
        String selectStr =
                " SELECT  TOP (1) tblDctosOrdenes.idCliente,     "
                + "         tblTerceros.nombreTercero,           "
                + "         tblDctosOrdenes.direccionDespacho,   "
                + "         tblCiudades.nombreCiudad,            "
                + "         tblCiudades.nombreDpto,              "
                + "         tblPlusFicha.idFicha,                "
                + "         tblDctosOrdenes.numeroOrden,         "
                + "         tblPlusFicha.referenciaCliente,      "
                + "         tblPlusFicha.idPlu,                  "
                + "         tblPlusFicha.referencia,             "
                + "         tblPlusFicha.nombreReferencia,       "
                + "         tblDctosOrdenesDetalle.cantidad,     "
                + "         tblDctosOrdenesDetalle.fechaEntrega, "
                + "         tblDctosOrdenesDetalle.item,         "
                + "         tblPlus.nombrePlu                    "
                + " FROM    tblDctosOrdenes                      "
                + " INNER JOIN tblDctosOrdenesDetalle            "
                + " ON tblDctosOrdenes.IDLOCAL      =            "
                + "             tblDctosOrdenesDetalle.IDLOCAL   "
                + " AND tblDctosOrdenes.IDTIPOORDEN =            "
                + "         tblDctosOrdenesDetalle.IDTIPOORDEN   "
                + " AND tblDctosOrdenes.IDORDEN     =            "
                + "             tblDctosOrdenesDetalle.IDORDEN   "
                + " INNER JOIN tblPlusFicha                      "
                + " ON tblDctosOrdenes.idFicha      =            "
                + "                      tblPlusFicha.idFicha    "
                + " INNER JOIN tblTerceros                       "
                + " ON tblDctosOrdenes.idCliente    =            "
                + "                     tblTerceros.idCliente    "
                + " INNER JOIN tblPlus                           "
                + " ON tblPlusFicha.idPlu           =            "
                + "                             tblPlus.idPlu    "
                + " INNER JOIN tblCiudades                       "
                + " ON tblTerceros.idDptoCiudad     =            "
                + "                      tblCiudades.idCiudad    "
                + " WHERE  tblDctosOrdenes.numeroOrden =         "
                + getNumeroOrden() + "                           "
                + " AND  tblDctosOrdenesDetalle.item   =         "
                + xItemPadre;

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaPluFicha fachadaBean;

            //
            while (rs.next()) {

                //
                fachadaBean = new FachadaPluFicha();

                //
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setNombreTercero(rs.getString("nombreTercero"));
                fachadaBean.setDireccionDespacho(rs.getString("direccionDespacho"));
                fachadaBean.setNombreCiudad(rs.getString("nombreCiudad"));
                fachadaBean.setNombreDpto(rs.getString("nombreDpto"));
                fachadaBean.setIdFicha(rs.getInt("idFicha"));
                fachadaBean.setNumeroOrden(rs.getInt("numeroOrden"));
                fachadaBean.setReferenciaCliente(
                        rs.getString("referenciaCliente"));
                fachadaBean.setIdPlu(rs.getInt("idPlu"));
                fachadaBean.setReferencia(
                        rs.getString("referencia"));
                fachadaBean.setNombreReferencia(
                        rs.getString("nombreReferencia"));
                fachadaBean.setCantidad(
                        rs.getDouble("cantidad"));
                fachadaBean.setFechaEntrega(rs.getString("fechaEntrega"));
                fachadaBean.setNombrePlu(
                        rs.getString("nombrePlu"));
                fachadaBean.setItem(rs.getInt("item"));

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

    // listaUnOTFCH
    public FachadaPluFicha listaUnOTFCH(int xItemPadre) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        //
        FachadaPluFicha fachadaBean = new FachadaPluFicha();

        Connection connection = null;

        //
        String selectStr =
                " SELECT  TOP (1) tblDctosOrdenes.idCliente,     "
                + "         tblTerceros.nombreTercero,           "
                + "         tblDctosOrdenes.direccionDespacho,   "
                + "         tblDctosOrdenes.fechaOrden,          "
                + "         tblDctosOrdenes.contacto,            "
                + "         tblCiudades.nombreCiudad,            "
                + "         tblCiudades.nombreDpto,              "
                + "         tblPlusFicha.idFicha,                "
                + "         tblDctosOrdenes.idLocal,             "
                + "         tblDctosOrdenes.idTipoOrden,         "
                + "         tblDctosOrdenes.idOrden,             "
                + "         tblDctosOrdenes.numeroOrden,         "
                + "         tblPlusFicha.referenciaCliente,      "
                + "         tblPlusFicha.idPlu,                  "
                + "         tblPlusFicha.referencia,             "
                + "         tblPlusFicha.nombreReferencia,       "
                + "         tblDctosOrdenesDetalle.cantidad,     "
                + "         tblDctosOrdenesDetalle.fechaEntrega, "
                + "         tblDctosOrdenesDetalle.item,         "
                + "         tblPlus.nombrePlu,                   "
                + " tblDctosOrdenesDetalle.vrVentaUnitarioSinIva,"
                + "         tblDctosOrdenes.ordenCompra,         "
                + "         tblDctosOrdenes.formaPago,           "
                + "         ctrlUsuarios.nombreUsuario           "
                + "                           AS nombreVendedor, "
                + "         tblDctosOrdenes.observacion,         "
                + "         tblDctosOrdenesDetalle.pesoPedido,   "
                + "     tblDctosOrdenesDetalle.cantidadTerminada,"
                + "     tblDctosOrdenesDetalle.cantidadFacturada,"
                + "     tblDctosOrdenesDetalle.cantidadEntregada,"
                + "     tblDctosOrdenesDetalle.pesoTerminado     "
                + " FROM    tblDctosOrdenes                      "
                + " INNER JOIN tblDctosOrdenesDetalle            "
                + " ON tblDctosOrdenes.IDLOCAL      =            "
                + "             tblDctosOrdenesDetalle.IDLOCAL   "
                + " AND tblDctosOrdenes.IDTIPOORDEN =            "
                + "         tblDctosOrdenesDetalle.IDTIPOORDEN   "
                + " AND tblDctosOrdenes.IDORDEN     =            "
                + "             tblDctosOrdenesDetalle.IDORDEN   "
                + " INNER JOIN tblPlusFicha                      "
                + " ON tblDctosOrdenes.idFicha      =            "
                + "                      tblPlusFicha.idFicha    "
                + " INNER JOIN tblTerceros                       "
                + " ON tblDctosOrdenes.idCliente    =            "
                + "                     tblTerceros.idCliente    "
                + " INNER JOIN tblPlus                           "
                + " ON tblPlusFicha.idPlu           =            "
                + "                             tblPlus.idPlu    "
                + " INNER JOIN tblCiudades                       "
                + " ON tblTerceros.idDptoCiudad     =            "
                + "                      tblCiudades.idCiudad    "
                + " INNER JOIN ctrlUsuarios                      "
                + " ON ctrlUsuarios.idUsuario       =            "
                + "                   tblDctosOrdenes.idVendedor "
                + " WHERE  tblDctosOrdenes.idTipoOrden = 59      "
                + " AND    tblDctosOrdenes.numeroOrden =         "
                + getNumeroOrden() + "                           "
                + " AND  tblDctosOrdenesDetalle.item   =         "
                + xItemPadre;

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
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setNombreTercero(rs.getString("nombreTercero"));
                fachadaBean.setDireccionDespacho(rs.getString("direccionDespacho"));
                fachadaBean.setFechaOrden(rs.getString("fechaOrden"));
                fachadaBean.setContacto(rs.getString("contacto"));
                fachadaBean.setNombreCiudad(rs.getString("nombreCiudad"));
                fachadaBean.setNombreDpto(rs.getString("nombreDpto"));
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setIdOrden(rs.getInt("idOrden"));
                fachadaBean.setIdFicha(rs.getInt("idFicha"));
                fachadaBean.setNumeroOrden(rs.getInt("numeroOrden"));
                fachadaBean.setReferenciaCliente(
                        rs.getString("referenciaCliente"));
                fachadaBean.setIdPlu(rs.getInt("idPlu"));
                fachadaBean.setReferencia(
                        rs.getString("referencia"));
                fachadaBean.setNombreReferencia(
                        rs.getString("nombreReferencia"));
                fachadaBean.setCantidad(
                        rs.getDouble("cantidad"));
                fachadaBean.setFechaEntrega(rs.getString("fechaEntrega"));
                fachadaBean.setNombrePlu(
                        rs.getString("nombrePlu"));
                fachadaBean.setItem(rs.getInt("item"));
                fachadaBean.setVrVentaUnitarioSinIva(
                        rs.getDouble("vrVentaUnitarioSinIva"));
                fachadaBean.setOrdenCompra(rs.getString("ordenCompra"));
                fachadaBean.setIdFormaPago(rs.getString("formaPago"));
                fachadaBean.setNombreVendedor(rs.getString("nombreVendedor"));
                fachadaBean.setObservacion(rs.getString("observacion"));
                fachadaBean.setPesoPedido(rs.getDouble("pesoPedido"));
                fachadaBean.setCantidadTerminada(
                        rs.getDouble("cantidadTerminada"));
                fachadaBean.setCantidadFacturada(
                        rs.getDouble("cantidadFacturada"));
                fachadaBean.setCantidadEntregada(
                        rs.getDouble("cantidadEntregada"));
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

    // listaPendiente
    public FachadaPluFicha listaPendiente(int xIdOperacionAnterior,
            int xIdOperacionActual) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        //
        FachadaPluFicha fachadaBean = new FachadaPluFicha();

        Connection connection = null;

        //
        String selectStr =
                " SELECT tmpRES.idLocal,                                  "
                + "        tmpRES.idTipoOrden,                            "
                + "        tmpRES.idOrden,                                "
                + "        tmpRES.itemPadre,                              "
                + "        SUM(tmpRES.pesoTerminadoAnterior)              "
                + "                AS pesoTerminadoAnterior,              "
                + "        SUM(tmpRES.cantidadTerminadaAnterior)          "
                + "                AS cantidadTerminadaAnterior,          "
                + "        SUM(tmpRES.pesoTerminadoActual)                "
                + "                      AS pesoTerminadoActual,          "
                + "        SUM(tmpRES.cantidadTerminadaActual)            "
                + "                   AS cantidadTerminadaActual          "
                + " FROM (                                                "
                + " SELECT tmpPRG.idLocalOrigen                           "
                + "                     AS idLocal,                       "
                + "        tmpPRG.idTipoOrdenOrigen                       "
                + "                 AS idTipoOrden,                       "
                + "        tmpPRG.idOrdenOrigen                           "
                + "                     AS idOrden                        "
                + "        ,tmpPRG.itemPAdre                              "
                + " ,CASE                                                 "
                + " WHEN tmpPRG.idOperacionAnterior > 0                   "
                + " THEN tmpPRG.pesoTerminado                             "
                + " ELSE 0 END                                            "
                + " AS pesoTerminadoAnterior                              "
                + " ,CASE                                                 "
                + " WHEN tmpPRG.idOperacionAnterior > 0                   "
                + " THEN tmpPRG.cantidadTerminada                         "
                + " ELSE 0 END                                            "
                + " AS cantidadTerminadaAnterior                          "
                + " ,CASE                                                 "
                + " WHEN tmpPRG.idOperacionActual > 0                     "
                + " THEN tmpPRG.pesoTerminado                             "
                + " ELSE 0 END                                            "
                + " AS pesoTerminadoActual                                "
                + " ,CASE                                                 "
                + " WHEN tmpPRG.idOperacionActual > 0                     "
                + " THEN tmpPRG.cantidadTerminada                         "
                + " ELSE 0 END                                            "
                + " AS cantidadTerminadaActual                            "
                + " FROM                                                  "
                + " (SELECT                                               "
                + "     tblDctosOrdenesDetalle.idLocalOrigen              "
                + "    ,tblDctosOrdenesDetalle.idTipoOrdenOrigen          "
                + "    ,tblDctosOrdenesDetalle.idOrdenOrigen              "
                + "    ,tblDctosOrdenesDetalle.itemPadre                  "
                + "    ,tblDctosOrdenesDetalle.idOperacion                "
                + "                       AS idOperacionAnterior          "
                + "    ,00 AS idOperacionActual                           "
                + "    ,SUM(tblDctosOrdenesDetalle.pesoTerminado)         "
                + "                              AS pesoTerminado         "
                + "    ,SUM(tblDctosOrdenesDetalle.cantidadTerminada)     "
                + "                             AS cantidadTerminada      "
                + " FROM tblDctosOrdenesDetalle                           "
                + " WHERE tblDctosOrdenesDetalle.idLocalOrigen =          "
                + getIdLocal() + "                                        "
                + " AND  tblDctosOrdenesDetalle.idTipoOrdenOrigen =       "
                + getIdTipoOrden() + "                                    "
                + " AND   tblDctosOrdenesDetalle.idOrdenOrigen =          "
                + getIdOrden() + "                                        "
                + " AND   tblDctosOrdenesDetalle.idOperacion   =          "
                + xIdOperacionAnterior + "                                "
                + " AND   tblDctosOrdenesDetalle.idPlu         = 209      "
                + " AND   tblDctosOrdenesDetalle.cantidad      > 0        "
                + " GROUP BY tblDctosOrdenesDetalle.idLocalOrigen         "
                + "         ,tblDctosOrdenesDetalle.idTipoOrdenOrigen     "
                + "         ,tblDctosOrdenesDetalle.idOrdenOrigen         "
                + "         ,tblDctosOrdenesDetalle.idOperacion           "
                + "         ,tblDctosOrdenesDetalle.itemPadre             "
                + " UNION                                                 "
                + " SELECT                                                "
                + "        tblDctosOrdenesDetalle.idLocalOrigen           "
                + "       ,tblDctosOrdenesDetalle.idTipoOrdenOrigen       "
                + "       ,tblDctosOrdenesDetalle.idOrdenOrigen           "
                + "       ,tblDctosOrdenesDetalle.itemPadre               "
                + "       ,00 AS idOperacionAnterior                      "
                + "       ,tblDctosOrdenesDetalle.idOperacion AS          "
                + "                               idOperacionActual       "
                + "      ,SUM(tblDctosOrdenesDetalle.pesoTerminado)       "
                + "                                AS pesoTerminado       "
                + "      ,SUM(tblDctosOrdenesDetalle.cantidadTerminada)   "
                + "                               AS cantidadTerminada    "
                + " FROM tblDctosOrdenesDetalle                           "
                + " WHERE tblDctosOrdenesDetalle.idLocalOrigen =          "
                + getIdLocal() + "                                        "
                + " AND  tblDctosOrdenesDetalle.idTipoOrdenOrigen =       "
                + getIdTipoOrden() + "                                    "
                + " AND   tblDctosOrdenesDetalle.idOrdenOrigen =          "
                + getIdOrden() + "                                        "
                + " AND   tblDctosOrdenesDetalle.idOperacion   =          "
                + xIdOperacionActual + "                                  "
                + " AND   tblDctosOrdenesDetalle.idPlu         = 209      "
                + " AND   tblDctosOrdenesDetalle.cantidad      > 0        "
                + " GROUP BY tblDctosOrdenesDetalle.idLocalOrigen         "
                + "         ,tblDctosOrdenesDetalle.idTipoOrdenOrigen     "
                + "         ,tblDctosOrdenesDetalle.idOrdenOrigen         "
                + "         ,tblDctosOrdenesDetalle.idOperacion           "
                + "         ,tblDctosOrdenesDetalle.itemPadre)            "
                + "                                      AS tmpPRG )      "
                + "                                         AS tmpRES     "
                + " GROUP BY tmpRES.idLocal,                              "
                + "          tmpRES.idTipoOrden,                          "
                + "          tmpRES.idOrden,                              "
                + "          tmpRES.itemPadre                             ";

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
                fachadaBean.setPesoPendiente(
                        rs.getDouble("pesoTerminadoAnterior")
                        - rs.getDouble("pesoTerminadoActual"));
                fachadaBean.setCantidadPendiente(
                        rs.getDouble("cantidadTerminadaAnterior")
                        - rs.getDouble("cantidadTerminadaActual"));

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
    public FachadaPluFicha listaOrdenFCH() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        // Variable de fachada de los datos
        FachadaPluFicha fachadaBean = new FachadaPluFicha();

        Connection connection = null;

        //
        String selectStr =
                "   SELECT   tmpORD.idLocal,                    "
                + "          tmpORD.idTipoOrden,                "
                + "          tblPlusFicha.idCliente,            "
                + "          tblPlusFicha.referenciaCliente,    "
                + "          tblPlusFicha.idPlu,                "
                + "          tblPlusFicha.nombreReferencia,     "
                + "          tblPlusFicha.idOperacion,          "
                + "          tblPlus.nombrePlu,                 "
                + "          tblPlusFicha.idFicha,              "
                + "          tblPlusFicha.referencia,           "
                + "          tmpORD.numeroOrden,                "
                + "          tmpORD.cantidad,                   "
                + "          tmpORD.idLog                       "
                + "   FROM   tblPlusFicha                       "
                + "   INNER JOIN tblPlus                        "
                + "   ON tblPlusFicha.idPlu = tblPlus.idPlu     "
                + "   INNER JOIN                                "
                + "   ( SELECT tblDctosOrdenes.idCliente,       "
                + "            tblDctosOrdenes.idLocal,         "
                + "            tblDctosOrdenes.idTipoOrden,     "
                + "            tblDctosOrdenes.idOrden,         "
                + "            tblDctosOrdenes.idFicha,         "
                + "            tblDctosOrdenes.idLog,           "
                + "            tblDctosOrdenes.numeroOrden,     "
                + "            tblDctosOrdenesDetalle.item,     "
                + "            tblDctosOrdenesDetalle.cantidad  "
                + "     FROM   tblDctosOrdenes                  "
                + "     INNER JOIN tblDctosOrdenesDetalle       "
                + "     ON tblDctosOrdenes.IDLOCAL      =       "
                + "            tblDctosOrdenesDetalle.IDLOCAL   "
                + "     AND tblDctosOrdenes.IDTIPOORDEN =       "
                + "         tblDctosOrdenesDetalle.IDTIPOORDEN  "
                + "     AND tblDctosOrdenes.IDORDEN     =       "
                + "            tblDctosOrdenesDetalle.IDORDEN   "
                + "     GROUP BY tblDctosOrdenes.idCliente,     "
                + "            tblDctosOrdenes.idLocal,         "
                + "            tblDctosOrdenes.idTipoOrden,     "
                + "            tblDctosOrdenes.idOrden,         "
                + "              tblDctosOrdenes.idLog,         "
                + "            tblDctosOrdenes.numeroOrden,     "
                + "            tblDctosOrdenesDetalle.item,     "
                + "            tblDctosOrdenesDetalle.cantidad, "
                + "            tblDctosOrdenes.idFicha )        "
                + "                                AS tmpORD    "
                + "   ON tmpORD.idCliente          =            "
                + "                   tblPlusFicha.idCliente    "
                + "   AND tmpORD.idFicha           =            "
                + "                     tblPlusFicha.idFicha    "
                + " WHERE   tmpORD.numeroOrden          =       "
                + getNumeroOrden() + "                          "
                + "   AND   tblPlusFicha.idOperacion    =       "
                + getIdOperacion() + "                          "
                + "   AND   tmpORD.item                 =       "
                + getItem() + "                                 "
                + "   GROUP BY tmpORD.idLocal,                  "
                + "            tmpORD.idTipoOrden,              "
                + "            tblPlusFicha.idCliente,          "
                + "            tblPlusFicha.referenciaCliente,  "
                + "            tblPlusFicha.idPlu,              "
                + "            tblPlusFicha.nombreReferencia,   "
                + "            tblPlusFicha.idOperacion,        "
                + "            tblPlus.nombrePlu,               "
                + "            tblPlusFicha.idFicha,            "
                + "            tblPlusFicha.referencia,         "
                + "            tmpORD.numeroOrden,              "
                + "            tmpORD.cantidad,                 "
                + "            tmpORD.idLog                     "
                + "   ORDER BY tblPlusFicha.idCliente,          "
                + "            tblPlusFicha.referenciaCliente ";

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
                fachadaBean.setIdLocal(rs.getString("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getString("idTipoOrden"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setReferenciaCliente(
                        rs.getString("referenciaCliente"));
                fachadaBean.setIdOperacion(rs.getInt("idOperacion"));
                fachadaBean.setIdPlu(rs.getInt("idPlu"));
                fachadaBean.setNombreReferencia(
                        rs.getString("nombreReferencia"));
                fachadaBean.setNombrePlu(
                        rs.getString("nombrePlu"));
                fachadaBean.setCantidad(
                        rs.getDouble("cantidad"));
                fachadaBean.setIdFicha(rs.getInt("idFicha"));
                fachadaBean.setReferencia(
                        rs.getString("referencia"));
                fachadaBean.setNumeroOrden(rs.getInt("numeroOrden"));
                fachadaBean.setIdLog(rs.getInt("idLog"));

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

    // listaUnaFicha
    public Vector listaUnaFicha() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        //
        String selectStr =
                " SELECT tblPlusFicha.idCliente,            "
                + "        tblPlusFicha.referenciaCliente,  "
                + "        tblPlusFicha.idPlu,              "
                + "        tblPlusFicha.nombreReferencia,   "
                + "        tblPlusFicha.idOperacion,        "
                + "        tblPlus.nombrePlu,               "
                + "        tblPlusFicha.referencia,         "
                + "        tblPlusFicha.idFicha             "
                + " FROM   tblPlusFicha                     "
                + " INNER JOIN tblPlus                      "
                + " ON tblPlusFicha.idPlu = tblPlus.idPlu   "
                + " WHERE   tblPlusFicha.idCliente =       '"
                + getIdCliente() + "'                       "
                + " AND   tblPlusFicha.idOperacion = 1      "
                + " AND   tblPlusFicha.estado = 1           "
                + " GROUP BY tblPlusFicha.idCliente,        "
                + "          tblPlusFicha.referenciaCliente,"
                + "          tblPlusFicha.idPlu,            "
                + "          tblPlusFicha.nombreReferencia, "
                + "          tblPlusFicha.idOperacion,      "
                + "          tblPlus.nombrePlu,             "
                + "          tblPlusFicha.referencia,       "
                + "          tblPlusFicha.idFicha           "
                + " ORDER BY tblPlusFicha.idCliente,        "
                + "          tblPlusFicha.referenciaCliente ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaPluFicha fachadaBean;

            while (rs.next()) {

                //
                fachadaBean = new FachadaPluFicha();

                //
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setReferenciaCliente(
                        rs.getString("referenciaCliente"));
                fachadaBean.setIdOperacion(rs.getInt("idOperacion"));
                fachadaBean.setIdPlu(rs.getInt("idPlu"));
                fachadaBean.setNombreReferencia(
                        rs.getString("nombreReferencia"));
                fachadaBean.setNombrePlu(rs.getString("nombrePlu"));
                fachadaBean.setReferencia(rs.getString("referencia"));
                fachadaBean.setIdFicha(rs.getInt("idFicha"));

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
    
    // listaUnaFicha
    public Vector listaUnaFichaAll() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        //
        String selectStr =
                " SELECT tblPlusFicha.idCliente,            "
                + "        tblPlusFicha.referenciaCliente,  "
                + "        tblPlusFicha.idPlu,              "
                + "        tblPlusFicha.nombreReferencia,   "
                + "        tblPlusFicha.idOperacion,        "
                + "        tblPlus.nombrePlu,               "
                + "        tblPlusFicha.referencia,         "
                + "        tblPlusFicha.idFicha,            "
                + "        tblPlusFicha.estado              "
                + " FROM   tblPlusFicha                     "
                + " INNER JOIN tblPlus                      "
                + " ON tblPlusFicha.idPlu = tblPlus.idPlu   "
                + " WHERE   tblPlusFicha.idCliente =        "
                + getIdCliente() + "                        "
                + " AND   tblPlusFicha.idOperacion = 1      "
//                + " AND   tblPlusFicha.estado = 1           "
                + " GROUP BY tblPlusFicha.idCliente,        "
                + "          tblPlusFicha.referenciaCliente,"
                + "          tblPlusFicha.idPlu,            "
                + "          tblPlusFicha.nombreReferencia, "
                + "          tblPlusFicha.idOperacion,      "
                + "          tblPlus.nombrePlu,             "
                + "          tblPlusFicha.referencia,       "
                + "          tblPlusFicha.idFicha,          "
                + "          tblPlusFicha.estado            "
                + " ORDER BY tblPlusFicha.idCliente,        "
                + "          tblPlusFicha.referenciaCliente ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaPluFicha fachadaBean;

            while (rs.next()) {

                //
                fachadaBean = new FachadaPluFicha();

                //
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setReferenciaCliente(
                        rs.getString("referenciaCliente"));
                fachadaBean.setIdOperacion(rs.getInt("idOperacion"));
                fachadaBean.setIdPlu(rs.getInt("idPlu"));
                fachadaBean.setNombreReferencia(
                        rs.getString("nombreReferencia"));
                fachadaBean.setNombrePlu(rs.getString("nombrePlu"));
                fachadaBean.setReferencia(rs.getString("referencia"));
                fachadaBean.setIdFicha(rs.getInt("idFicha"));
                fachadaBean.setEstado(rs.getInt("estado"));

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

    // listaUnFichaFCH
    public FachadaPluFicha listaUnFichaFCH() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        //
        FachadaPluFicha fachadaBean = new FachadaPluFicha();

        Connection connection = null;

        //
        String selectStr =
                " SELECT tblPlusFicha.idCliente,              "
                + "        tblPlusFicha.referenciaCliente,    "
                + "        tblPlusFicha.idPlu,                "
                + "        tblPlusFicha.nombreReferencia,     "
                + "        tblPlusFicha.idOperacion,          "
                + "        tblPlus.nombrePlu                  "
                + " FROM   tblPlusFicha                       "
                + " INNER JOIN tblPlus                        "
                + " ON tblPlusFicha.idPlu = tblPlus.idPlu     "
                + " WHERE   tblPlusFicha.idCliente =         '"
                + getIdCliente() + "'                         "
                + " AND tblPlusFicha.referenciaCliente =     '"
                + getReferenciaCliente() + "'                 "
                + " AND tblPlusFicha.idOperacion = 1          "
                + " GROUP BY tblPlusFicha.idCliente,          "
                + "          tblPlusFicha.referenciaCliente,  "
                + "          tblPlusFicha.idPlu,              "
                + "          tblPlusFicha.nombreReferencia,   "
                + "          tblPlusFicha.idOperacion,      "
                + "          tblPlus.nombrePlu                "
                + " ORDER BY tblPlusFicha.idCliente,          "
                + "          tblPlusFicha.referenciaCliente   ";

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
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setReferenciaCliente(
                        rs.getString("referenciaCliente"));
                fachadaBean.setIdOperacion(rs.getInt("idOperacion"));
                fachadaBean.setIdPlu(rs.getInt("idPlu"));
                fachadaBean.setNombreReferencia(
                        rs.getString("nombreReferencia"));
                fachadaBean.setNombrePlu(
                        rs.getString("nombrePlu"));

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

    // listaIdFichaFCH
    public FachadaPluFicha listaIdFichaFCH() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        //
        FachadaPluFicha fachadaBean = new FachadaPluFicha();

        Connection connection = null;

        //
        String selectStr =
                " SELECT tblPlusFicha.idCliente,              "
                + "        tblPlusFicha.referenciaCliente,    "
                + "        tblPlusFicha.idPlu,                "
                + "        tblPlusFicha.nombreReferencia,     "
                + "        tblPlusFicha.idOperacion,          "
                + "        tblPlus.nombrePlu,                 "
                + "        tblPlusFicha.idFicha,              "
                + "        tblPlusFicha.referencia            "
                + " FROM   tblPlusFicha                       "
                + " INNER JOIN tblPlus                        "
                + " ON tblPlusFicha.idPlu = tblPlus.idPlu     "
                + " WHERE  tblPlusFicha.idFicha    =          "
                + getIdFicha() + "                            "
                + " AND tblPlusFicha.idOperacion = 1          "
                + " GROUP BY tblPlusFicha.idCliente,          "
                + "          tblPlusFicha.referenciaCliente,  "
                + "          tblPlusFicha.idPlu,              "
                + "          tblPlusFicha.nombreReferencia,   "
                + "          tblPlusFicha.idOperacion,        "
                + "          tblPlus.nombrePlu,               "
                + "          tblPlusFicha.idFicha,            "
                + "          tblPlusFicha.referencia          "
                + " ORDER BY tblPlusFicha.idCliente,          "
                + "          tblPlusFicha.referenciaCliente   ";

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
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setReferenciaCliente(
                        rs.getString("referenciaCliente"));
                fachadaBean.setIdOperacion(rs.getInt("idOperacion"));
                fachadaBean.setIdPlu(rs.getInt("idPlu"));
                fachadaBean.setNombreReferencia(
                        rs.getString("nombreReferencia"));
                fachadaBean.setNombrePlu(
                        rs.getString("nombrePlu"));
                fachadaBean.setIdFicha(rs.getInt("idFicha"));
                fachadaBean.setReferencia(rs.getString("referencia"));

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

    // listaFichaOperacionCliente
    public Vector listaFichaOperacionCliente() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        //
        String selectStr =
                " SELECT tblPlusFicha.idCliente,            "
                + "        tblPlusFicha.referenciaCliente,  "
                + " 	   tblPlusFicha.idOperacion,        "
                + " 	   tblPlusFicha.idPlu,              "
                + " 	   tblPlusFicha.nombreReferencia,   "
                + "        tblPlusFicha.idEscala,           "
                + " 	   tblPlusFicha.item,               "
                + " 	   tblPlusFicha.vrEscala,           "
                + " 	   tblPlusFicha.textoEscala,        "
                + " 	   tblPlusFicha.estado,             "
                + " 	   tblJobEscala.nombreEscala,       "
                + "        tblJobEscala.idTipoEscala        "
                + " FROM   tblPlusFicha                     "
                + " INNER JOIN tblJobEscala                 "
                + " ON tblPlusFicha.idEscala =              "
                + "                tblJobEscala.idEscala    "
                + " INNER JOIN tblJobEscalaOperacion        "
                + " ON tblJobEscalaOperacion.idEscala     = "
                + "                  tblPlusFicha.idEscala  "
                + " AND tblJobEscalaOperacion.idOperacion = "
                + "                tblPlusFicha.idOperacion "
                + " WHERE tblPlusFicha.idCliente         = '"
                + getIdCliente() + "'                       "
                + " AND   tblPlusFicha.idFicha           =  "
                + getIdFicha() + "                          "
                + " AND   tblPlusFicha.idOperacion       =  "
                + getIdOperacion() + "                      "
                + " AND   tblJobEscala.idTipoEscala     !=9 "
                + " ORDER BY                                "
                + "     tblJobEscalaOperacion.idOrdenSalida,"
                + "     tblJobEscala.idEscala";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaPluFicha fachadaBean;

            //
            while (rs.next()) {

                //
                fachadaBean = new FachadaPluFicha();

                //
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setReferenciaCliente(rs.getString("referenciaCliente"));
                fachadaBean.setIdOperacion(rs.getInt("idOperacion"));
                fachadaBean.setIdPlu(rs.getInt("idPlu"));
                fachadaBean.setNombreReferencia(rs.getString("nombreReferencia"));
                fachadaBean.setIdEscala(rs.getInt("idEscala"));
                fachadaBean.setItem(rs.getInt("item"));
                fachadaBean.setVrEscala(rs.getDouble("vrEscala"));
                fachadaBean.setTextoEscala(rs.getString("textoEscala"));
                fachadaBean.setEstado(rs.getInt("estado"));
                fachadaBean.setNombreEscala(rs.getString("nombreEscala"));
                fachadaBean.setIdTipoEscala(rs.getInt("idTipoEscala"));

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

    // listaFichaOperacion
    public Vector listaFichaOperacion() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        //
        String selectStr =
                " SELECT tblPlusFicha.idCliente                      "
                + "      ,tblPlusFicha.referenciaCliente             "
                + "      ,tblPlusFicha.idOperacion                   "
                + "      ,tblPlusFicha.idPlu                         "
                + "      ,tblPlusFicha.nombreReferencia              "
                + "      ,tblPlusFicha.idEscala                      "
                + "      ,tblPlusFicha.item                          "
                + "      ,tblPlusFicha.vrEscala                      "
                + "      ,tblPlusFicha.textoEscala                   "
                + "      ,tblPlusFicha.estado                        "
                + "      ,tblPlusFicha.idFicha                       "
                + "      ,tblPlusFicha.referencia                    "
                + "      ,tblJobOperacion.nombreOperacion            "
                + " FROM tblPlusFicha                                "
                + " INNER JOIN tblJobOperacion                       "
                + " ON tblJobOperacion.idOperacion =                 "
                + "              tblPlusFicha.idOperacion            "
                + " INNER JOIN ( SELECT tblJobOperacion.idOperacion, "
                + "                 tblJobOperacion.nombreOperacion  "
                + "              FROM tblJobOperacion ) AS tmpOPE    "
                + " ON tmpOPE.idOperacion =                          "
                + "                 tblPlusFicha.vrEscala            "
                + " AND tblPlusFicha.idEscala = 610                  "
                + " AND tblPlusFicha.vrEscala != 0                   "
                + " AND tblPlusFicha.idFicha   =                     "
                + getIdFicha() + "                                   "
                + " ORDER BY tblPlusFicha.idOperacion                ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaPluFicha fachadaBean;

            //
            while (rs.next()) {

                //
                fachadaBean = new FachadaPluFicha();

                //
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setReferenciaCliente(
                        rs.getString("referenciaCliente"));
                fachadaBean.setIdOperacion(rs.getInt("idOperacion"));
                fachadaBean.setIdPlu(rs.getInt("idPlu"));
                fachadaBean.setNombreReferencia(
                        rs.getString("nombreReferencia"));
                fachadaBean.setIdEscala(rs.getInt("idEscala"));
                fachadaBean.setItem(rs.getInt("item"));
                fachadaBean.setVrEscala(rs.getDouble("vrEscala"));
                fachadaBean.setTextoEscala(rs.getString("textoEscala"));
                fachadaBean.setEstado(rs.getInt("estado"));
                fachadaBean.setIdFicha(rs.getInt("idFicha"));
                fachadaBean.setReferencia(rs.getString("referencia"));
                fachadaBean.setNombreOperacion(rs.getString("nombreOperacion"));


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

    // totalMezcla
    public double totalMezcla(String xIdCliente,
            String xReferenciaCliente,
            int xIdOperacion,
            String xIdEscalaCadena) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        //
        double xTotalMezcla = 0.0;

        Connection connection = null;

        //
        String selectStr =
                " SELECT SUM(tblPlusFicha.vrEscala)      "
                + "               AS totalMezcla         "
                + " FROM tblPlusFicha                    "
                + " WHERE tblPlusFicha.idCliente       ='"
                + xIdCliente + "'                        "
                + " AND tblPlusFicha.referenciaCliente ='"
                + xReferenciaCliente + "'                "
                + " AND tblPlusFicha.idOperacion       = "
                + xIdOperacion + "                       "
                + " AND tblPlusFicha.idEscala IN        ("
                + xIdEscalaCadena + ")";

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
                xTotalMezcla = rs.getDouble("totalMezcla");

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
            return xTotalMezcla;

        }
    }

    // valorEscala
    public double valorEscala(String xIdCliente,
            int xIdFicha,
            int xIdOperacion,
            String xIdEscalaCadena) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        //
        double xTotalMezcla = 0.0;

        Connection connection = null;

        //
        String selectStr =
                " SELECT SUM(tblPlusFicha.vrEscala)      "
                + "               AS totalMezcla         "
                + " FROM tblPlusFicha                    "
                + " WHERE tblPlusFicha.idFicha         = "
                + xIdFicha + "                         "
                + " AND tblPlusFicha.idOperacion       = "
                + xIdOperacion + "                       "
                + " AND tblPlusFicha.idEscala IN        ("
                + xIdEscalaCadena + ")";
        
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
                xTotalMezcla = rs.getDouble("totalMezcla");

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
            return xTotalMezcla;

        }
    }

    // factorLamina
    public double factorLamina(int xIdFicha,
            int xIdOperacion) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        //
        double xOkFactorLamina = 1;

        Connection connection = null;

        //
        String selectStr =
                " SELECT TOP 1                              "
                + "        tblPlus.nombrePlu                "
                + " FROM   tblPlusFicha                     "
                + " INNER JOIN tblPlus                      "
                + " ON tblPlusFicha.idPlu  = tblPlus.idPlu  "
                + " WHERE  tblPlusFicha.idFicha  =          "
                + xIdFicha + "                              "
                + " AND tblPlus.nombrePlu LIKE '%LAMINA%'   "
                + " AND tblPlusFicha.idOperacion =          "
                + xIdOperacion + "                          "
                + " AND tblPlusFicha.idEscala    = 502      "
                + " AND tblPlusFicha.vrEscala    > 0   ";

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
                xOkFactorLamina = 0.5;

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
            return xOkFactorLamina;

        }
    }

    // referencia
    public String referencia() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        //
        int xIdEscalaAncho = 501;
        int xIdEscalaLargo = 502;
        int xIdEscalaFuelle_1 = 508;
        int xIdEscalaFuelle_2 = 504;
        int xIdEscalaTipoFuelle = 505;
        int xIdEscalaSolapa = 507;
        int xIdEscalaTipoSolapa = 506;
        int xIdEscalaCalibre = 503;
        double xIdEscalaTipoFuelle_505_Lateral = 1.0;
        double xIdEscalaTipoFuelle_505_Fondo = 2.0;
        double xIdEscalaTipoFuelle_505_Ninguno = 3.0;
        int xIdEscalaTipoSolapa_506_Interna = 1;
        int xIdEscalaTipoSolapa_506_Externa = 2;
        int xIdEscalaTipoSolapa_506_InternaDoble = 3;

        //
        String xNombreReferencia = "";
        String xReferencia = "";

        //
        String xIdEscalaAnchoStr = "";
        String xIdEscalaLargoStr = "";
        String xIdEscalaTipoFuelleStr = "";
        String xIdEscalaSolapaStr = "";
        String xIdEscalaTipoSolapaStr = "";
        String xIdEscalaCalibreStr = "";

        // Variable de fachada de los datos
        FachadaPluFicha fachadaBean = new FachadaPluFicha();

        Connection connection = null;

        //
        String selectStr =
                " SELECT tblPlusFicha.idEscala            "
                + "       ,tblPlusFicha.vrEscala          "
                + "       ,tblPlusFicha.nombreReferencia  "
                + " FROM tblPlusFicha                     "
                + " WHERE tblPlusFicha.idCliente       = '"
                + getIdCliente() + "'                     "
                + " AND tblPlusFicha.referenciaCliente = '"
                + getReferenciaCliente() + "'             "
                + " AND tblPlusFicha.idEscala IN          "
                + "    (501,502,504,505,506,503,507,508)  "
                + " AND tblPlusFicha.idOperacion      = 1 ";

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
                fachadaBean.setVrEscala(rs.getDouble("vrEscala"));
                fachadaBean.setNombreReferencia(rs.getString("nombreReferencia"));

                //
                int xIdEscala = rs.getInt("idEscala");

                //--------------------------------------------------------------
                if (xIdEscala == xIdEscalaAncho) {

                    //
                    xIdEscalaAnchoStr = fachadaBean.getVrEscalaFormato();

                }

                //--------------------------------------------------------------
                if (xIdEscala == xIdEscalaLargo) {

                    //
                    if (fachadaBean.getVrEscala() != 0) {

                        //
                        xIdEscalaLargoStr = "X" + fachadaBean.getVrEscalaFormato();
                    }
                }

                //--------------------------------------------------------------
                if (xIdEscala == xIdEscalaFuelle_1) {

                    //
                    if (fachadaBean.getVrEscala() != 0) {

                        //
                        xIdEscalaTipoFuelleStr += "+"
                                + fachadaBean.getVrEscalaFormato();
                    } else {

                        //
                        xIdEscalaTipoFuelleStr = "";
                    }

                }

                //--------------------------------------------------------------
                if (xIdEscala == xIdEscalaFuelle_2) {

                    //
                    if (fachadaBean.getVrEscala() != 0) {

                        //
                        xIdEscalaTipoFuelleStr += "+"
                                + fachadaBean.getVrEscalaFormato();
                    } else {

                        //
                        xIdEscalaTipoFuelleStr = "";
                    }
                }

                //--------------------------------------------------------------
                if (xIdEscala == xIdEscalaTipoFuelle) {

                    //
                    xIdEscalaTipoFuelle = (int) fachadaBean.getVrEscala();

                }

                //--------------------------------------------------------------
                if (xIdEscala == xIdEscalaSolapa) {

                    //
                    if (fachadaBean.getVrEscala() != 0) {

                        //
                        xIdEscalaSolapaStr += "+"
                                + fachadaBean.getVrEscalaFormato();
                    }

                }

                //--------------------------------------------------------------
                if (xIdEscala == xIdEscalaCalibre) {

                    //
                    xIdEscalaCalibreStr = fachadaBean.getVrEscalaFormato();

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

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {

            // Cierra la conexion
            jdbcAccess.cleanup(connection, selectStatement, null);

            //
            if (xIdEscalaTipoFuelle == xIdEscalaTipoFuelle_505_Lateral) {

                //
                xReferencia = fachadaBean.getNombreReferencia()
                        + xIdEscalaAnchoStr
                        + xIdEscalaTipoFuelleStr
                        + xIdEscalaLargoStr
                        + xIdEscalaSolapaStr
                        + "X" + xIdEscalaCalibreStr;

            }

            //
            if (xIdEscalaTipoFuelle == xIdEscalaTipoFuelle_505_Fondo) {

                //
                xReferencia = fachadaBean.getNombreReferencia()
                        + xIdEscalaAnchoStr
                        + xIdEscalaLargoStr
                        + xIdEscalaTipoFuelleStr
                        + xIdEscalaSolapaStr
                        + "X" + xIdEscalaCalibreStr;

            }

            //
            if (xIdEscalaTipoFuelle == xIdEscalaTipoFuelle_505_Ninguno) {

                //
                xReferencia = fachadaBean.getNombreReferencia()
                        + xIdEscalaAnchoStr
                        + xIdEscalaLargoStr
                        + xIdEscalaSolapaStr
                        + "X" + xIdEscalaCalibreStr;
            }

            //
            return xReferencia;

        }
    }

    // listaOTOperacion
    public Vector listaOTOperacion() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        //
        String selectStr =
                " SELECT tblDctosOrdenesDetalle.idLocal,               "
                + "        tblDctosOrdenesDetalle.idTipoOrden,         "
                + "        tblDctosOrdenesDetalle.idOrden,             "
                + "        tmpPRG.itemPadre,                           "
                + "        tmpPRG.cantidadPerdida,                     "
                + "        tmpPRG.pesoPerdido,                         "
                + "        tmpPRG.pesoTerminado,                       "
                + "        tblDctosOrdenes.numeroOrden,                "
                + "        tblDctosOrdenes.idCliente,                  "
                + "        tblDctosOrdenesDetalle.fechaEntrega,        "
                + "        tblDctosOrdenesDetalle.cantidad,            "
                + "        tmpPRG.cantidadTerminada,                   "
                + "        ( tmpPRG.cantidadTerminada /                "
                + "          tblDctosOrdenesDetalle.cantidad ) * 100   "
                + "                     AS porcentajeTerminado,        "
                + "        tblTerceros.nombreTercero,                  "
                + "        tblDctosOrdenesDetalle.referenciaCliente,   "
                + "        tblJobOperacion.nombreOperacion             "
                + " FROM   tblDctosOrdenesDetalle                      "
                + " INNER JOIN (                                       "
                + " SELECT tblDctosOrdenesProgreso.idLocal             "
                + "       ,tblDctosOrdenesProgreso.idTipoOrden         "
                + "       ,tblDctosOrdenesProgreso.idOrden             "
                + "       ,tblDctosOrdenesProgreso.itemPadre           "
                + "       ,tblDctosOrdenesProgreso.idOPeracion         "
                + "       ,SUM([cantidadPerdida])                      "
                + "             AS cantidadPerdida                     "
                + "       ,SUM([cantidadTerminada])                    "
                + "           AS cantidadTerminada                     "
                + "       ,SUM([pesoPerdido])                          "
                + "                 AS pesoPerdido                     "
                + "       ,SUM([pesoTerminado])                        "
                + "               AS pesoTerminado                     "
                + " FROM tblDctosOrdenesProgreso                       "
                + " WHERE tblDctosOrdenesProgreso.idOperacion =        "
                + getIdOperacion() + "                                 "
                + " GROUP BY  tblDctosOrdenesProgreso.idLocal          "
                + "          ,tblDctosOrdenesProgreso.idTipoOrden      "
                + "          ,tblDctosOrdenesProgreso.idOrden          "
                + "          ,tblDctosOrdenesProgreso.idOPeracion      "
                + "          ,tblDctosOrdenesProgreso.itemPadre)       "
                + "                                    AS tmpPRG       "
                + " ON    tblDctosOrdenesDetalle.idLocal     =         "
                + "                               tmpPRG.idLocal       "
                + " AND   tblDctosOrdenesDetalle.idTipoOrden =         "
                + "                           tmpPRG.idTipoOrden       "
                + " AND   tblDctosOrdenesDetalle.idorden     =         "
                + "                               tmpPRG.idorden       "
                + " AND   tblDctosOrdenesDetalle.itemPadre   =         "
                + "                             tmpPRG.itemPadre       "
                + " INNER JOIN tblDctosOrdenes                         "
                + " ON    tblDctosOrdenesDetalle.idLocal     =         "
                + "                      tblDctosOrdenes.idLocal       "
                + " AND   tblDctosOrdenesDetalle.idTipoOrden =         "
                + "                 tblDctosOrdenes.idTipoOrden        "
                + " AND   tblDctosOrdenesDetalle.idorden     =         "
                + "                      tblDctosOrdenes.idorden       "
                + " INNER JOIN tblJobOperacion                         "
                + " ON tblJobOperacion.idOperacion           =         "
                + "                            tmpPRG.idOperacion      "
                + " INNER JOIN tblTerceros                             "
                + " ON tblTerceros.idCliente                 =         "
                + "                    tblDctosOrdenes.idCliente       "
                + " WHERE tblTerceros.idTipoTercero          = 1       "
                + " AND tblDctosOrdenesDetalle.idEstadoRefOriginal != 9"
                + " ORDER BY tblDctosOrdenesDetalle.fechaEntrega       ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaPluFicha fachadaBean;

            //
            while (rs.next()) {

                //
                fachadaBean = new FachadaPluFicha();

                //
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setIdOrden(rs.getInt("idOrden"));
                fachadaBean.setItemPadre(rs.getInt("itemPadre"));
                fachadaBean.setCantidadPerdida(rs.getDouble("cantidadPerdida"));
                fachadaBean.setPesoPerdido(rs.getDouble("pesoPerdido"));
                fachadaBean.setPesoTerminado(rs.getDouble("pesoTerminado"));
                fachadaBean.setNumeroOrden(rs.getInt("numeroOrden"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setFechaEntrega(rs.getString("fechaEntrega"));
                fachadaBean.setCantidad(rs.getDouble("cantidad"));
                fachadaBean.setCantidadTerminada(
                        rs.getDouble("cantidadTerminada"));
                fachadaBean.setCantidadPerdida(rs.getDouble("cantidadPerdida"));
                fachadaBean.setPorcentajeTerminado(
                        rs.getDouble("porcentajeTerminado"));
                fachadaBean.setNombreTercero(rs.getString("nombreTercero"));
                fachadaBean.setReferenciaCliente(
                        rs.getString("referenciaCliente"));
                fachadaBean.setNombreOperacion(rs.getString("NombreOperacion"));

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

    // listaOTMaquina
    public Vector listaOTMaquina() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        //
        String selectStr =
                " SELECT tblDctosOrdenesDetalle.idLocal,                "
                + "        tblDctosOrdenesDetalle.idTipoOrden,          "
                + "        tblDctosOrdenesDetalle.idOrden,              "
                + "        tmpPRG.itemPadre,                            "
                + "        tmpPRG.cantidadPerdida,                      "
                + "        tmpPRG.pesoPerdido,                          "
                + "        tmpPRG.pesoTerminado,                        "
                + "        tblDctosOrdenes.numeroOrden,                 "
                + "        tblDctosOrdenes.idCliente,                   "
                + "        tblDctosOrdenesDetalle.fechaEntrega,         "
                + "        tblDctosOrdenesDetalle.cantidad,             "
                + "        tmpPRG.cantidadTerminada,                    "
                + "        ( tmpPRG.cantidadTerminada /                 "
                + "          tblDctosOrdenesDetalle.cantidad ) * 100    "
                + "                     AS porcentajeTerminado,         "
                + "        tblTerceros.nombreTercero,                   "
                + "        tblDctosOrdenesDetalle.referenciaCliente,    "
                + "        tblJobOperacion.nombreOperacion,             "
                + "        tmpMAQ.nombreItem,                           "
                + "        tmpMAQ.referencia                            "
                + " FROM   tblDctosOrdenesDetalle                       "
                + " INNER JOIN (                                        "
                + " SELECT tblDctosOrdenesProgreso.idLocal              "
                + "       ,tblDctosOrdenesProgreso.idTipoOrden          "
                + "       ,tblDctosOrdenesProgreso.idOrden              "
                + "       ,tblDctosOrdenesProgreso.itemPadre            "
                + "       ,tblDctosOrdenesProgreso.idOPeracion          "
                + "       ,SUM([cantidadPerdida])                       "
                + "             AS cantidadPerdida                      "
                + "       ,SUM([cantidadTerminada])                     "
                + "           AS cantidadTerminada                      "
                + "       ,SUM([pesoPerdido])                           "
                + "                 AS pesoPerdido                      "
                + "       ,SUM([pesoTerminado])                         "
                + "               AS pesoTerminado                      "
                + " FROM tblDctosOrdenesProgreso                        "
                + " WHERE tblDctosOrdenesProgreso.idOperacion =         "
                + getIdOperacion() + "                                  "
                + " GROUP BY  tblDctosOrdenesProgreso.idLocal           "
                + "          ,tblDctosOrdenesProgreso.idTipoOrden       "
                + "          ,tblDctosOrdenesProgreso.idOrden           "
                + "          ,tblDctosOrdenesProgreso.idOPeracion       "
                + "          ,tblDctosOrdenesProgreso.itemPadre)        "
                + "                                    AS tmpPRG        "
                + " ON    tblDctosOrdenesDetalle.idLocal     =          "
                + "                               tmpPRG.idLocal        "
                + " AND   tblDctosOrdenesDetalle.idTipoOrden =          "
                + "                           tmpPRG.idTipoOrden        "
                + " AND   tblDctosOrdenesDetalle.idorden     =          "
                + "                               tmpPRG.idorden        "
                + " AND   tblDctosOrdenesDetalle.itemPadre   =          "
                + "                             tmpPRG.itemPadre        "
                + " INNER JOIN tblDctosOrdenes                          "
                + " ON    tblDctosOrdenesDetalle.idLocal     =          "
                + "                      tblDctosOrdenes.idLocal        "
                + " AND   tblDctosOrdenesDetalle.idTipoOrden =          "
                + "                 tblDctosOrdenes.idTipoOrden         "
                + " AND   tblDctosOrdenesDetalle.idorden     =          "
                + "                      tblDctosOrdenes.idorden        "
                + " INNER JOIN tblJobOperacion                          "
                + " ON tblJobOperacion.idOperacion           =          "
                + "                            tmpPRG.idOperacion       "
                + " INNER JOIN tblTerceros                              "
                + " ON tblTerceros.idCliente                 =          "
                + "                    tblDctosOrdenes.idCliente        "
                + " INNER JOIN                                          "
                + " (SELECT tblPlusFicha.idFicha                        "
                + "       ,tblPlusFicha.idOperacion                     "
                + "       ,tblJobEscalaDetalle.nombreItem               "
                + "       ,tblJobOperacion.nombreOperacion              "
                + "       ,tblPlusFicha.referencia                      "
                + " FROM tblPlusFicha                                   "
                + " INNER JOIN tblJobEscalaDetalle                      "
                + " ON tblJobEscalaDetalle.idEscala =                   "
                + "               tblPlusFicha.idEscala                 "
                + " AND tblPlusFicha.vrEscala =                         "
                + "            tblJobEscalaDetalle.item                 "
                + " INNER JOIN tblJobOperacion                          "
                + " ON tblJobOperacion.idOperacion =                    "
                + "            tblPlusFicha.idOperacion                 "
                + " AND tblPlusFicha.idEscala                           "
                + "           IN (600,710,910,1000,1100))               "
                + "                          AS tmpMAQ                  "
                + " ON tmpMAQ.idFicha  = tblDctosOrdenes.idFicha        "
                + " WHERE tblTerceros.idTipoTercero          = 1        "
                + " AND tblDctosOrdenesDetalle.idEstadoRefOriginal != 9 "
                + " AND   tmpMAQ.idOperacion                 =          "
                + "                           tmpPRG.idOperacion        "
                + " ORDER BY tblDctosOrdenesDetalle.fechaEntrega,       "
                + "          tmpMAQ.nombreItem                          ";


        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaPluFicha fachadaBean;

            //
            while (rs.next()) {

                //
                fachadaBean = new FachadaPluFicha();

                //
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setIdOrden(rs.getInt("idOrden"));
                fachadaBean.setItemPadre(rs.getInt("itemPadre"));
                fachadaBean.setCantidadPerdida(rs.getDouble("cantidadPerdida"));
                fachadaBean.setPesoPerdido(rs.getDouble("pesoPerdido"));
                fachadaBean.setPesoTerminado(rs.getDouble("pesoTerminado"));
                fachadaBean.setNumeroOrden(rs.getInt("numeroOrden"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setFechaEntrega(rs.getString("fechaEntrega"));
                fachadaBean.setCantidad(rs.getDouble("cantidad"));
                fachadaBean.setCantidadTerminada(
                        rs.getDouble("cantidadTerminada"));
                fachadaBean.setCantidadPerdida(rs.getDouble("cantidadPerdida"));
                fachadaBean.setPorcentajeTerminado(
                        rs.getDouble("porcentajeTerminado"));
                fachadaBean.setNombreTercero(rs.getString("nombreTercero"));
                fachadaBean.setReferenciaCliente(
                        rs.getString("referenciaCliente"));
                fachadaBean.setNombreOperacion(rs.getString("nombreOperacion"));
                fachadaBean.setNombreItem(rs.getString("nombreItem"));
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

    // listaOTUnMaquina
    public Vector listaOTUnMaquina() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        //
        String selectStr =
                "   SELECT tblDctosOrdenesDetalle.idLocal,                     "
                + "          tblDctosOrdenesDetalle.idTipoOrden,               "
                + "          tblDctosOrdenesDetalle.idOrden,                   "
                + "          tmpPRG.itemPadre,                                 "
                + "          tmpPRG.cantidadPerdida,                           "
                + "          tmpPRG.pesoPerdido,                               "
                + "          tmpPRG.pesoTerminado,                             "
                + "          tblDctosOrdenes.numeroOrden,                      "
                + "          tblDctosOrdenes.idCliente,                        "
                + "          tblDctosOrdenesDetalle.fechaEntrega,              "
                + "          tblDctosOrdenesDetalle.cantidad,                  "
                + "          tmpPRG.cantidadTerminada,                         "
                + "          ( tmpPRG.cantidadTerminada /                      "
                + "            tblDctosOrdenesDetalle.cantidad ) * 100         "
                + "                       AS porcentajeTerminado,              "
                + "          tblTerceros.nombreTercero,                        "
                + "          tblDctosOrdenesDetalle.referenciaCliente,         "
                + "          tblJobOperacion.nombreOperacion,                  "
                + "          tmpMAQ.nombreItem,                                "
                + "          tmpMAQ.referencia                                 "
                + "   FROM   tblDctosOrdenesDetalle                            "
                + "   INNER JOIN (                                             "
                + "   SELECT tblDctosOrdenesProgreso.idLocal                   "
                + "         ,tblDctosOrdenesProgreso.idTipoOrden               "
                + "         ,tblDctosOrdenesProgreso.idOrden                   "
                + "         ,tblDctosOrdenesProgreso.itemPadre                 "
                + "         ,tblDctosOrdenesProgreso.idOPeracion               "
                + "         ,SUM([cantidadPerdida])                            "
                + "               AS cantidadPerdida                           "
                + "         ,SUM([cantidadTerminada])                          "
                + "             AS cantidadTerminada                           "
                + "         ,SUM([pesoPerdido])                                "
                + "                   AS pesoPerdido                           "
                + "         ,SUM([pesoTerminado])                              "
                + "                 AS pesoTerminado                           "
                + "   FROM tblDctosOrdenesProgreso                             "
                + "   WHERE tblDctosOrdenesProgreso.idOperacion =              "
                + getIdOperacion() + "                                         "
                + "   GROUP BY  tblDctosOrdenesProgreso.idLocal                "
                + "            ,tblDctosOrdenesProgreso.idTipoOrden            "
                + "            ,tblDctosOrdenesProgreso.idOrden                "
                + "            ,tblDctosOrdenesProgreso.idOPeracion            "
                + "            ,tblDctosOrdenesProgreso.itemPadre)             "
                + "                                      AS tmpPRG             "
                + "   ON    tblDctosOrdenesDetalle.idLocal     =               "
                + "                                 tmpPRG.idLocal             "
                + "   AND   tblDctosOrdenesDetalle.idTipoOrden =               "
                + "                             tmpPRG.idTipoOrden             "
                + "   AND   tblDctosOrdenesDetalle.idorden     =               "
                + "                                 tmpPRG.idorden             "
                + "   AND   tblDctosOrdenesDetalle.itemPadre   =               "
                + "                               tmpPRG.itemPadre             "
                + "   INNER JOIN tblDctosOrdenes                               "
                + "   ON    tblDctosOrdenesDetalle.idLocal     =               "
                + "                        tblDctosOrdenes.idLocal             "
                + "   AND   tblDctosOrdenesDetalle.idTipoOrden =               "
                + "                   tblDctosOrdenes.idTipoOrden              "
                + "   AND   tblDctosOrdenesDetalle.idorden     =               "
                + "                        tblDctosOrdenes.idorden             "
                + "   INNER JOIN tblJobOperacion                               "
                + "   ON tblJobOperacion.idOperacion           =               "
                + "                              tmpPRG.idOperacion            "
                + "   INNER JOIN tblTerceros                                   "
                + "   ON tblTerceros.idCliente                 =               "
                + "                      tblDctosOrdenes.idCliente             "
                + "   INNER JOIN                                               "
                + "   (SELECT tblPlusFicha.idFicha                             "
                + "         ,tblPlusFicha.idOperacion                          "
                + "         ,tblJobEscalaDetalle.nombreItem                    "
                + "         ,tblJobOperacion.nombreOperacion                   "
                + "         ,tblPlusFicha.referencia                           "
                + "   FROM tblPlusFicha                                        "
                + "   INNER JOIN tblJobEscalaDetalle                           "
                + "   ON tblJobEscalaDetalle.idEscala =                        "
                + "                 tblPlusFicha.idEscala                      "
                + "   AND tblPlusFicha.vrEscala =                              "
                + "              tblJobEscalaDetalle.item                      "
                + "   INNER JOIN tblJobOperacion                               "
                + "   ON tblJobOperacion.idOperacion =                         "
                + "              tblPlusFicha.idOperacion                      "
                + "   AND tblPlusFicha.idEscala  IN (600,710,910,1000,1100)    "
                + "   AND tblPlusFicha.vrEscala                       =        "
                + getVrEscala() + "                           )  AS tmpMAQ     "
                + "   ON tmpMAQ.idFicha  = tblDctosOrdenes.idFicha             "
                + "   WHERE tblTerceros.idTipoTercero                 = 1      "
                + "   AND tblDctosOrdenesDetalle.idEstadoRefOriginal != 9      "
                + "   AND   tmpMAQ.idOperacion  =  tmpPRG.idOperacion          "
                + "   UNION                                                    "
                + "   SELECT tblDctosOrdenesDetalle.idLocal,                   "
                + "          tblDctosOrdenesDetalle.idTipoOrden,               "
                + "          tblDctosOrdenesDetalle.idOrden,                   "
                + "          tblDctosOrdenesDetalle.itemPadre,                 "
                + "          tblDctosOrdenesDetalle.cantidad                   "
                + " 		    AS cantidadPerdida,                       "
                + "          0.0 AS pesoPerdido,                               "
                + "          0.0 AS pesoTerminado,                             "
                + "          tblDctosOrdenes.numeroOrden,                      "
                + "          tblDctosOrdenes.idCliente,                        "
                + "          tblDctosOrdenesDetalle.fechaEntrega,              "
                + "          tblDctosOrdenesDetalle.cantidad,                  "
                + "          0.0 AS cantidadTerminada,                         "
                + "          0.0 AS porcentajeTerminado,                       "
                + "          tblTerceros.nombreTercero,                        "
                + "          tblDctosOrdenesDetalle.referenciaCliente,         "
                + "          tblJobOperacion.nombreOperacion,                  "
                + "          tmpMAQ.nombreItem,                                "
                + "          tmpMAQ.referencia                                 "
                + "   FROM   tblDctosOrdenesDetalle                            "
                + "   INNER JOIN tblDctosOrdenes                               "
                + "   ON    tblDctosOrdenesDetalle.idLocal     =               "
                + "                        tblDctosOrdenes.idLocal             "
                + "   AND   tblDctosOrdenesDetalle.idTipoOrden =               "
                + "                   tblDctosOrdenes.idTipoOrden              "
                + "   AND   tblDctosOrdenesDetalle.idorden     =               "
                + "                        tblDctosOrdenes.idorden             "
                + "   INNER JOIN tblTerceros                                   "
                + "   ON tblTerceros.idCliente                 =               "
                + "                      tblDctosOrdenes.idCliente             "
                + "   INNER JOIN                                               "
                + "   (SELECT tblPlusFicha.idFicha                             "
                + "         ,tblPlusFicha.idOperacion                          "
                + "         ,tblJobEscalaDetalle.nombreItem                    "
                + "         ,tblJobOperacion.nombreOperacion                   "
                + "         ,tblPlusFicha.referencia                           "
                + "   FROM tblPlusFicha                                        "
                + "   INNER JOIN tblJobEscalaDetalle                           "
                + "   ON tblJobEscalaDetalle.idEscala =                        "
                + "                 tblPlusFicha.idEscala                      "
                + "   AND tblPlusFicha.vrEscala =                              "
                + "              tblJobEscalaDetalle.item                      "
                + "   INNER JOIN tblJobOperacion                               "
                + "   ON tblJobOperacion.idOperacion =                         "
                + "              tblPlusFicha.idOperacion                      "
                + "   AND tblPlusFicha.idEscala  IN (600,710,910,1000,1100)    "
                + "   AND tblPlusFicha.vrEscala             =                  "
                + getVrEscala() + "                          ) AS tmpMAQ       "
                + "   ON tmpMAQ.idFicha  = tblDctosOrdenes.idFicha             "
                + "                                                            "
                + "   INNER JOIN tblJobOperacion                               "
                + "   ON tblJobOperacion.idOperacion           =               "
                + "                              tmpMAQ.idOperacion            "
                + "   WHERE tblTerceros.idTipoTercero                 = 1      "
                + "   AND tblDctosOrdenesDetalle.idEstadoRefOriginal != 9      "
                + "   AND   tmpMAQ.idOperacion  =  tmpMAQ.idOperacion          "
                + "   AND   tblDctosOrdenes.numeroOrden              != 0      "
                + "   AND   NOT EXISTS                                         "
                + "       ( SELECT tblDctosOrdenesProgreso.*                   "
                + "         FROM tblDctosOrdenesProgreso                       "
                + "         WHERE tblDctosOrdenesProgreso.idLocal     =        "
                + "                         tblDctosOrdenesDetalle.idLocal     "
                + "         AND   tblDctosOrdenesProgreso.idTipoOrden =        "
                + "                     tblDctosOrdenesDetalle.idTipoOrden     "
                + "         AND   tblDctosOrdenesProgreso.idOrden     =        "
                + "                         tblDctosOrdenesDetalle.idOrden     "
                + "         AND   tblDctosOrdenesProgreso.itemPadre   =        "
                + "                     tblDctosOrdenesDetalle.itemPadre )     "
                + "   ORDER BY tblDctosOrdenesDetalle.fechaEntrega,            "
                + "            tmpMAQ.nombreItem                               ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaPluFicha fachadaBean;

            //
            while (rs.next()) {

                //
                fachadaBean = new FachadaPluFicha();

                //
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setIdOrden(rs.getInt("idOrden"));
                fachadaBean.setItemPadre(rs.getInt("itemPadre"));
                fachadaBean.setCantidadPerdida(rs.getDouble("cantidadPerdida"));
                fachadaBean.setPesoPerdido(rs.getDouble("pesoPerdido"));
                fachadaBean.setPesoTerminado(rs.getDouble("pesoTerminado"));
                fachadaBean.setNumeroOrden(rs.getInt("numeroOrden"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setFechaEntrega(rs.getString("fechaEntrega"));
                fachadaBean.setCantidad(rs.getDouble("cantidad"));
                fachadaBean.setCantidadTerminada(
                        rs.getDouble("cantidadTerminada"));
                fachadaBean.setCantidadPerdida(rs.getDouble("cantidadPerdida"));
                fachadaBean.setPorcentajeTerminado(
                        rs.getDouble("porcentajeTerminado"));
                fachadaBean.setNombreTercero(rs.getString("nombreTercero"));
                fachadaBean.setReferenciaCliente(
                        rs.getString("referenciaCliente"));
                fachadaBean.setNombreOperacion(rs.getString("nombreOperacion"));
                fachadaBean.setNombreItem(rs.getString("nombreItem"));
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

    // listaOTUnMaquinaPendiente
    public Vector listaOTUnMaquinaPendiente() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        //
        String selectStr =
                "   SELECT tmpDEF.*,                                                  "
                + "              tmpCAN.cantidadPendiente,                            "
                + "              tmpCAN.pesoPendiente                                 "
                + "       FROM                                                        "
                + "        (SELECT tblDctosOrdenesDetalle.idLocal,                    "
                + "               tblDctosOrdenesDetalle.idTipoOrden,                 "
                + "               tblDctosOrdenesDetalle.idOrden,                     "
                + "               tmpPRG.itemPadre,                                   "
                + "               tmpPRG.cantidadPerdida,                             "
                + "               tmpPRG.pesoPerdido,                                 "
                + "               tmpPRG.pesoTerminado,                               "
                + "               tblDctosOrdenes.numeroOrden,                        "
                + "               tblDctosOrdenes.idCliente,                          "
                + "               tblDctosOrdenesDetalle.fechaEntrega,                "
                + "               tblDctosOrdenesDetalle.cantidad,                    "
                + "               tmpPRG.cantidadTerminada,                           "
                + "               ( tmpPRG.cantidadTerminada /                        "
                + "                 tblDctosOrdenesDetalle.cantidad ) * 100           "
                + "                            AS porcentajeTerminado,                "
                + "               tblTerceros.nombreTercero,                          "
                + "               tblDctosOrdenesDetalle.referenciaCliente,           "
                + "               tblJobOperacion.nombreOperacion,                    "
                + "               tmpMAQ.nombreItem,                                  "
                + "               tmpMAQ.referencia                                   "
                + "        FROM   tblDctosOrdenesDetalle                              "
                + "        INNER JOIN (                                               "
                + "        SELECT tblDctosOrdenesProgreso.idLocal                     "
                + "              ,tblDctosOrdenesProgreso.idTipoOrden                 "
                + "              ,tblDctosOrdenesProgreso.idOrden                     "
                + "              ,tblDctosOrdenesProgreso.itemPadre                   "
                + "              ,tblDctosOrdenesProgreso.idOPeracion                 "
                + "              ,SUM([cantidadPerdida])                              "
                + "                    AS cantidadPerdida                             "
                + "              ,SUM([cantidadTerminada])                            "
                + "                  AS cantidadTerminada                             "
                + "              ,SUM([pesoPerdido])                                  "
                + "                        AS pesoPerdido                             "
                + "              ,SUM([pesoTerminado])                                "
                + "                      AS pesoTerminado                             "
                + "        FROM tblDctosOrdenesProgreso                               "
                + "        WHERE tblDctosOrdenesProgreso.idOperacion =                "
                + getIdOperacion() + "                                                "
                + "        GROUP BY  tblDctosOrdenesProgreso.idLocal                  "
                + "                 ,tblDctosOrdenesProgreso.idTipoOrden              "
                + "                 ,tblDctosOrdenesProgreso.idOrden                  "
                + "                 ,tblDctosOrdenesProgreso.idOPeracion              "
                + "                 ,tblDctosOrdenesProgreso.itemPadre)               "
                + "                                           AS tmpPRG               "
                + "        ON    tblDctosOrdenesDetalle.idLocal     =                 "
                + "                                      tmpPRG.idLocal               "
                + "        AND   tblDctosOrdenesDetalle.idTipoOrden =                 "
                + "                                  tmpPRG.idTipoOrden               "
                + "        AND   tblDctosOrdenesDetalle.idorden     =                 "
                + "                                      tmpPRG.idorden               "
                + "        AND   tblDctosOrdenesDetalle.itemPadre   =                 "
                + "                                    tmpPRG.itemPadre               "
                + "        INNER JOIN tblDctosOrdenes                                 "
                + "        ON    tblDctosOrdenesDetalle.idLocal     =                 "
                + "                             tblDctosOrdenes.idLocal               "
                + "        AND   tblDctosOrdenesDetalle.idTipoOrden =                 "
                + "                        tblDctosOrdenes.idTipoOrden                "
                + "        AND   tblDctosOrdenesDetalle.idorden     =                 "
                + "                             tblDctosOrdenes.idorden               "
                + "        INNER JOIN tblJobOperacion                                 "
                + "        ON tblJobOperacion.idOperacion           =                 "
                + "                                   tmpPRG.idOperacion              "
                + "        INNER JOIN tblTerceros                                     "
                + "        ON tblTerceros.idCliente                 =                 "
                + "                           tblDctosOrdenes.idCliente               "
                + "        INNER JOIN                                                 "
                + "        (SELECT tblPlusFicha.idFicha                               "
                + "              ,tblPlusFicha.idOperacion                            "
                + "              ,tblJobEscalaDetalle.nombreItem                      "
                + "              ,tblJobOperacion.nombreOperacion                     "
                + "              ,tblPlusFicha.referencia                             "
                + "        FROM tblPlusFicha                                          "
                + "        INNER JOIN tblJobEscalaDetalle                             "
                + "        ON tblJobEscalaDetalle.idEscala =                          "
                + "                      tblPlusFicha.idEscala                        "
                + "        AND tblPlusFicha.vrEscala =                                "
                + "                   tblJobEscalaDetalle.item                        "
                + "        INNER JOIN tblJobOperacion                                 "
                + "        ON tblJobOperacion.idOperacion =                           "
                + "                   tblPlusFicha.idOperacion                        "
                + "        AND tblPlusFicha.idEscala IN (600,710,910,1000,1100)       "
                + "        AND tblPlusFicha.vrEscala                =                 "
                + getVrEscala() + " )                                                 "
                + "                                              AS tmpMAQ            "
                + "        ON tmpMAQ.idFicha  = tblDctosOrdenes.idFicha               "
                + "        WHERE tblTerceros.idTipoTercero          = 1               "
                + "        AND tblDctosOrdenesDetalle.idEstadoRefOriginal             "
                + "                                                   != 9            "
                + "        AND   tmpMAQ.idOperacion  =  tmpPRG.idOperacion            "
                + "        UNION                                                      "
                + "        SELECT tblDctosOrdenesDetalle.idLocal,                     "
                + "               tblDctosOrdenesDetalle.idTipoOrden,                 "
                + "               tblDctosOrdenesDetalle.idOrden,                     "
                + "               tblDctosOrdenesDetalle.itemPadre,                   "
                + "               tblDctosOrdenesDetalle.cantidad                     "
                + " 	    AS cantidadPerdida,                                   "
                + "               0.0 AS pesoPerdido,                                 "
                + "               0.0 AS pesoTerminado,                               "
                + "               tblDctosOrdenes.numeroOrden,                        "
                + "               tblDctosOrdenes.idCliente,                          "
                + "               tblDctosOrdenesDetalle.fechaEntrega,                "
                + "               tblDctosOrdenesDetalle.cantidad,                    "
                + "               0.0 AS cantidadTerminada,                           "
                + "               0.0 AS porcentajeTerminado,                         "
                + "               tblTerceros.nombreTercero,                          "
                + "               tblDctosOrdenesDetalle.referenciaCliente,           "
                + "               tblJobOperacion.nombreOperacion,                    "
                + "               tmpMAQ.nombreItem,                                  "
                + "               tmpMAQ.referencia                                   "
                + "        FROM   tblDctosOrdenesDetalle                              "
                + "        INNER JOIN tblDctosOrdenes                                 "
                + "        ON    tblDctosOrdenesDetalle.idLocal     =                 "
                + "                             tblDctosOrdenes.idLocal               "
                + "        AND   tblDctosOrdenesDetalle.idTipoOrden =                 "
                + "                        tblDctosOrdenes.idTipoOrden                "
                + "        AND   tblDctosOrdenesDetalle.idorden     =                 "
                + "                             tblDctosOrdenes.idorden               "
                + "        INNER JOIN tblTerceros                                     "
                + "        ON tblTerceros.idCliente                 =                 "
                + "                           tblDctosOrdenes.idCliente               "
                + "        INNER JOIN                                                 "
                + "        (SELECT tblPlusFicha.idFicha                               "
                + "              ,tblPlusFicha.idOperacion                            "
                + "              ,tblJobEscalaDetalle.nombreItem                      "
                + "              ,tblJobOperacion.nombreOperacion                     "
                + "              ,tblPlusFicha.referencia                             "
                + "        FROM tblPlusFicha                                          "
                + "        INNER JOIN tblJobEscalaDetalle                             "
                + "        ON tblJobEscalaDetalle.idEscala =                          "
                + "                      tblPlusFicha.idEscala                        "
                + "        AND tblPlusFicha.vrEscala =                                "
                + "                   tblJobEscalaDetalle.item                        "
                + "        INNER JOIN tblJobOperacion                                 "
                + "        ON tblJobOperacion.idOperacion =                           "
                + "                   tblPlusFicha.idOperacion                        "
                + "        AND tblPlusFicha.idEscala  IN (600,710,910,1000,1100)      "
                + "        AND tblPlusFicha.vrEscala                =                 "
                + getVrEscala() + " )                                                 "
                + "                                              AS tmpMAQ            "
                + "        ON tmpMAQ.idFicha  = tblDctosOrdenes.idFicha               "
                + "        INNER JOIN tblJobOperacion                                 "
                + "        ON tblJobOperacion.idOperacion           =                 "
                + "                                   tmpMAQ.idOperacion              "
                + "        WHERE tblTerceros.idTipoTercero                 = 1        "
                + "        AND tblDctosOrdenesDetalle.idEstadoRefOriginal != 9        "
                + "        AND   tmpMAQ.idOperacion  =  tmpMAQ.idOperacion            "
                + "        AND   tblDctosOrdenes.numeroOrden              != 0        "
                + "        AND   NOT EXISTS                                           "
                + "            ( SELECT tblDctosOrdenesProgreso.*                     "
                + "              FROM tblDctosOrdenesProgreso                         "
                + "              WHERE tblDctosOrdenesProgreso.idLocal     =          "
                + "                           tblDctosOrdenesDetalle.idLocal          "
                + "              AND   tblDctosOrdenesProgreso.idTipoOrden =          "
                + "                       tblDctosOrdenesDetalle.idTipoOrden          "
                + "              AND   tblDctosOrdenesProgreso.idOrden     =          "
                + "                           tblDctosOrdenesDetalle.idOrden          "
                + "              AND   tblDctosOrdenesProgreso.itemPadre   =          "
                + "                     tblDctosOrdenesDetalle.itemPadre ) )          "
                + "                                                AS tmpDEF          "
                + "       INNER JOIN                                                  "
                + "        ( SELECT tmpRES.idLocal,                                   "
                + "                 tmpRES.idTipoOrden,                               "
                + "                 tmpRES.idOrden,                                   "
                + "                 tmpRES.itemPadre,                                 "
                + "                 SUM(tmpRES.pesoTerminadoAnterior)                 "
                + "                             AS pesoTerminadoAnterior,             "
                + "                 SUM(tmpRES.cantidadTerminadaAnterior)             "
                + "                           AS cantidadTerminadaAnterior,           "
                + "                 SUM(tmpRES.pesoTerminadoActual)                   "
                + "                                 AS pesoTerminadoActual,           "
                + "                 SUM(tmpRES.cantidadTerminadaActual)               "
                + "                             AS cantidadTerminadaActual,           "
                + "                 SUM(tmpRES.cantidadTerminadaAnterior) -           "
                + "                 SUM(tmpRES.cantidadTerminadaActual)               "
                + "                                   AS cantidadPendiente,           "
                + "                 SUM(tmpRES.pesoTerminadoAnterior) -               "
                + "                 SUM(tmpRES.pesoTerminadoActual)                   "
                + "                                        AS pesoPendiente           "
                + "        FROM (                                                     "
                + "            SELECT tmpPRG.idLocalOrigen                            "
                + "                               AS idLocal,                         "
                + "                   tmpPRG.idTipoOrdenOrigen                        "
                + "                            AS idTipoOrden,                        "
                + "                   tmpPRG.idOrdenOrigen                            "
                + "                                AS idOrden                         "
                + "                  ,tmpPRG.itemPAdre                                "
                + "                  ,CASE                                            "
                + "                   WHEN tmpPRG.idOperacionAnterior > 0             "
                + "                   THEN tmpPRG.pesoTerminado                       "
                + "                   ELSE 0 END                                      "
                + "                              AS pesoTerminadoAnterior             "
                + "                  ,CASE                                            "
                + "                   WHEN tmpPRG.idOperacionAnterior > 0             "
                + "                   THEN tmpPRG.cantidadTerminada                   "
                + "                   ELSE 0 END                                      "
                + "                   AS cantidadTerminadaAnterior                    "
                + "                   ,CASE                                           "
                + "                   WHEN tmpPRG.idOperacionActual > 0               "
                + "                   THEN tmpPRG.pesoTerminado                       "
                + "                   ELSE 0 END AS pesoTerminadoActual               "
                + "                  ,CASE                                            "
                + "                   WHEN tmpPRG.idOperacionActual > 0               "
                + "                   THEN tmpPRG.cantidadTerminada                   "
                + "                   ELSE 0 END                                      "
                + "                   AS cantidadTerminadaActual                      "
                + "            FROM                                                   "
                + "              (SELECT                                              "
                + "                  tblDctosOrdenesDetalle.idLocalOrigen             "
                + "                 ,tblDctosOrdenesDetalle.idTipoOrdenOrigen         "
                + "                 ,tblDctosOrdenesDetalle.idOrdenOrigen             "
                + "                 ,tblDctosOrdenesDetalle.itemPadre                 "
                + "                 ,tblDctosOrdenesDetalle.idOperacion               "
                + "                                    AS idOperacionAnterior         "
                + "                 ,00 AS idOperacionActual                          "
                + "                 ,SUM(tblDctosOrdenesDetalle.pesoTerminado)        "
                + "                                          AS pesoTerminado         "
                + "             ,SUM(tblDctosOrdenesDetalle.cantidadTerminada)        "
                + "                                       AS cantidadTerminada        "
                + "               FROM tblDctosOrdenesDetalle                         "
                + "               INNER JOIN tblDctosOrdenes                          "
                + "   ON tblDctosOrdenesDetalle.idLocalOrigen      =                  "
                + "                            tblDctosOrdenes.idLocal                "
                + "   AND tblDctosOrdenesDetalle.idTipoOrdenOrigen =                  "
                + "                        tblDctosOrdenes.idTipoOrden                "
                + "   AND tblDctosOrdenesDetalle.idOrdenOrigen     =                  "
                + "                            tblDctosOrdenes.idOrden                "
                + "   INNER JOIN (SELECT tblPlusFicha.idFicha,                        "
                + "                                 MAX(tblPlusFicha.idOperacion)     "
                + "                                         AS idOperacionAnterior    "
                + "                   FROM tblPlusFicha                               "
                + "                           WHERE tblPlusFicha.idEscala  =  610     "
                + "                           AND tblPlusFicha.vrEscala    =          "
                + getIdOperacion() + "                                                "
                + " 	 	  GROUP BY tblPlusFicha.idFicha )                 "
                + "                                                      AS tmpOPA    "
                + "   ON tmpOPA.idFicha = tblDctosOrdenes.idFicha			  "
                + "   AND tmpOPA.idOperacionAnterior =                                "
                + "                             tblDctosOrdenesDetalle.idOperacion	  "
                + "               WHERE tblDctosOrdenesDetalle.idPlu         = 209    "
                + "               AND   tblDctosOrdenesDetalle.cantidad      < 0      "
                + "               GROUP BY tblDctosOrdenesDetalle.idLocalOrigen       "
                + "                       ,tblDctosOrdenesDetalle.idTipoOrdenOrigen   "
                + "                       ,tblDctosOrdenesDetalle.idOrdenOrigen       "
                + "                       ,tblDctosOrdenesDetalle.idOperacion         "
                + "                       ,tblDctosOrdenesDetalle.itemPadre           "
                + "               UNION                                               "
                + "               SELECT                                              "
                + "                  tblDctosOrdenesDetalle.idLocalOrigen             "
                + "                 ,tblDctosOrdenesDetalle.idTipoOrdenOrigen         "
                + "                 ,tblDctosOrdenesDetalle.idOrdenOrigen             "
                + "                 ,tblDctosOrdenesDetalle.itemPadre                 "
                + "                 ,00 AS idOperacionAnterior                        "
                + "                 ,tblDctosOrdenesDetalle.idOperacion AS            "
                + "                                         idOperacionActual         "
                + "                 ,SUM(tblDctosOrdenesDetalle.pesoTerminado)        "
                + "                                           AS pesoTerminado        "
                + "                 ,SUM(tblDctosOrdenesDetalle.cantidadTerminada)    "
                + "                                           AS cantidadTerminada    "
                + "               FROM tblDctosOrdenesDetalle                         "
                + "               WHERE tblDctosOrdenesDetalle.idOperacion   =        "
                + getIdOperacion() + "                                                "
                + "               AND   tblDctosOrdenesDetalle.idPlu         = 209    "
                + "               AND   tblDctosOrdenesDetalle.cantidad      < 0      "
                + "               GROUP BY tblDctosOrdenesDetalle.idLocalOrigen       "
                + "                       ,tblDctosOrdenesDetalle.idTipoOrdenOrigen   "
                + "                       ,tblDctosOrdenesDetalle.idOrdenOrigen       "
                + "                       ,tblDctosOrdenesDetalle.idOperacion         "
                + "                       ,tblDctosOrdenesDetalle.itemPadre)          "
                + "                                                    AS tmpPRG )    "
                + "                                                    AS tmpRES      "
                + "               GROUP BY tmpRES.idLocal,                            "
                + "                        tmpRES.idTipoOrden,                        "
                + "                        tmpRES.idOrden,                            "
                + "                        tmpRES.itemPadre ) AS tmpCAN               "
                + "        ON  tmpCAN.idLocal     = tmpDEF.idLocal                    "
                + "        AND tmpCAN.idTipoOrden = tmpDEF.idTipoOrden                "
                + "        AND tmpCAN.idOrden     = tmpDEF.idOrden                    "
                + "        AND tmpCAN.itemPadre   = tmpDEF.itemPadre                  "
                + "        ORDER BY tmpDEF.fechaEntrega,                              "
                + "                 tmpDEF.numeroOrden                                ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaPluFicha fachadaBean;

            //
            while (rs.next()) {

                //
                fachadaBean = new FachadaPluFicha();

                //
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setIdOrden(rs.getInt("idOrden"));
                fachadaBean.setItemPadre(rs.getInt("itemPadre"));
                fachadaBean.setCantidadPerdida(rs.getDouble("cantidadPerdida"));
                fachadaBean.setPesoPerdido(rs.getDouble("pesoPerdido"));
                fachadaBean.setPesoTerminado(rs.getDouble("pesoTerminado"));
                fachadaBean.setNumeroOrden(rs.getInt("numeroOrden"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setFechaEntrega(rs.getString("fechaEntrega"));
                fachadaBean.setCantidad(rs.getDouble("cantidad"));
                fachadaBean.setCantidadTerminada(
                        rs.getDouble("cantidadTerminada"));
                fachadaBean.setCantidadPerdida(rs.getDouble("cantidadPerdida"));
                fachadaBean.setPorcentajeTerminado(
                        rs.getDouble("porcentajeTerminado"));
                fachadaBean.setNombreTercero(rs.getString("nombreTercero"));
                fachadaBean.setReferenciaCliente(
                        rs.getString("referenciaCliente"));
                fachadaBean.setNombreOperacion(rs.getString("nombreOperacion"));
                fachadaBean.setNombreItem(rs.getString("nombreItem"));
                fachadaBean.setReferencia(rs.getString("referencia"));
                fachadaBean.setCantidadPendiente(rs.getDouble("cantidadPendiente"));
                fachadaBean.setPesoPendiente(rs.getDouble("pesoPendiente"));

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

    // lista
    public Vector lista() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        //
        String selectStr =
                " SELECT tmpPEN.idLocal,                                 "
                + "        tmpPEN.idTipoOrden,                           "
                + "        tmpPEN.idOrden,                               "
                + "        tmpPEN.itemPadre,                             "
                + "        tmpPEN.idCliente,                             "
                + "        tmpPEN.numeroOrden,                           "
                + "        tmpPEN.idFicha,                               "
                + "        tmpPEN.fechaEntrega,                          "
                + "        tmpPEN.referencia,                            "
                + "        tmpPEN.nombreOperacion,                       "
                + "        tmpPEN.referenciaCliente,                     "
                + "        tmpPEN.nombreReferencia,                      "
                + "        tmpPEN.nombreItem,                            "
                + "        tmpPEN.nombreTercero,                         "
                + "        tmpPEN.idOperacionAnterior,                   "
                + "        tmpPEN.idOperacion,                           "
                + "        CASE                                          "
                + "        WHEN   tmpPEN.idOperacionAnterior = 1         "
                + "                       THEN tmpPEN.pesoPedido         "
                + "        ELSE   tmpPEN.pesoTerminadoAnterior           "
                + "        END AS pesoTerminadoAnterior,                 "
                + "        CASE                                          "
                + "        WHEN   tmpPEN.idOperacionAnterior = 1         "
                + "                   THEN tmpPEN.cantidadPedida         "
                + "        ELSE   tmpPEN.cantidadTerminadaAnterior       "
                + "        END AS cantidadTerminadaAnterior,             "
                + "        tmpPEN.pesoTerminadoActual,                   "
                + "        tmpPEN.cantidadTerminadaActual                "
                + " FROM (                                               "
                + " SELECT tmpPED.*,                                     "
                + " ( SELECT SUM(tblDctosOrdenesDetalle.pesoTerminado)   "
                + " FROM tblDctosOrdenesDetalle                          "
                + " WHERE tblDctosOrdenesDetalle.idLocalOrigen     =     "
                + "                                    tmpPED.idLocal    "
                + " AND   tblDctosOrdenesDetalle.idTipoOrdenOrigen =     "
                + "                                tmpPED.idTipoOrden    "
                + " AND   tblDctosOrdenesDetalle.idOrdenOrigen     =     "
                + "                                    tmpPED.idOrden    "
                + " AND   tblDctosOrdenesDetalle.itemPadre         =     "
                + "                                  tmpPED.itemPadre    "
                + " AND   tblDctosOrdenesDetalle.idOperacion       =     "
                + "                        tmpPED.idOperacionAnterior    "
                + " AND   tblDctosOrdenesDetalle.idPlu         = 209     "
                + " AND   tblDctosOrdenesDetalle.cantidad      < 0 )     "
                + "                        AS pesoTerminadoAnterior,     "
                + " ( SELECT                                             "
                + "     SUM(tblDctosOrdenesDetalle.cantidadTerminada)    "
                + " FROM tblDctosOrdenesDetalle                          "
                + " WHERE tblDctosOrdenesDetalle.idLocalOrigen     =     "
                + "                                    tmpPED.idLocal    "
                + " AND   tblDctosOrdenesDetalle.idTipoOrdenOrigen =     "
                + "                                tmpPED.idTipoOrden    "
                + " AND   tblDctosOrdenesDetalle.idOrdenOrigen     =     "
                + "                                    tmpPED.idOrden    "
                + " AND   tblDctosOrdenesDetalle.itemPadre         =     "
                + "                                  tmpPED.itemPadre    "
                + " AND   tblDctosOrdenesDetalle.idOperacion       =     "
                + "                        tmpPED.idOperacionAnterior    "
                + " AND   tblDctosOrdenesDetalle.idPlu         = 209     "
                + " AND   tblDctosOrdenesDetalle.cantidad      < 0 )     "
                + "                     AS cantidadTerminadaAnterior,    "
                + " ( SELECT SUM(tblDctosOrdenesDetalle.pesoTerminado)   "
                + " FROM tblDctosOrdenesDetalle                          "
                + " WHERE tblDctosOrdenesDetalle.idLocalOrigen     =     "
                + "                                     tmpPED.idLocal   "
                + " AND   tblDctosOrdenesDetalle.idTipoOrdenOrigen =     "
                + "                                 tmpPED.idTipoOrden   "
                + " AND   tblDctosOrdenesDetalle.idOrdenOrigen     =     "
                + "                                     tmpPED.idOrden   "
                + " AND   tblDctosOrdenesDetalle.itemPadre         =     "
                + "                                   tmpPED.itemPadre   "
                + " AND   tblDctosOrdenesDetalle.idOperacion       =     "
                + "                                 tmpPED.idOperacion   "
                + " AND   tblDctosOrdenesDetalle.idPlu         = 209     "
                + " AND   tblDctosOrdenesDetalle.cantidad      < 0 )     "
                + "                             AS pesoTerminadoActual,  "
                + " ( SELECT                                             "
                + "      SUM(tblDctosOrdenesDetalle.cantidadTerminada)   "
                + " FROM tblDctosOrdenesDetalle                          "
                + " WHERE tblDctosOrdenesDetalle.idLocalOrigen     =     "
                + "                                     tmpPED.idLocal   "
                + " AND   tblDctosOrdenesDetalle.idTipoOrdenOrigen =     "
                + "                                 tmpPED.idTipoOrden   "
                + " AND   tblDctosOrdenesDetalle.idOrdenOrigen     =     "
                + "                                     tmpPED.idOrden   "
                + " AND   tblDctosOrdenesDetalle.itemPadre         =     "
                + "                                   tmpPED.itemPadre   "
                + " AND   tblDctosOrdenesDetalle.idOperacion       =     "
                + "                                 tmpPED.idOperacion   "
                + " AND   tblDctosOrdenesDetalle.idPlu         = 209     "
                + " AND   tblDctosOrdenesDetalle.cantidad      < 0 )     "
                + "                        AS cantidadTerminadaActual    "
                + " FROM (                                               "
                + "  SELECT tblDctosOrdenes.idLocal,                     "
                + "         tblDctosOrdenes.idTipoOrden,                 "
                + "         tblDctosOrdenes.idOrden,                     "
                + "         tblDctosOrdenesDetalle.itemPadre,            "
                + "         tblDctosOrdenes.idCliente,                   "
                + "         tblDctosOrdenes.numeroOrden,                 "
                + "         tblDctosOrdenes.idFicha,                     "
                + "         tblDctosOrdenesDetalle.fechaEntrega,         "
                + "         tblDctosOrdenesDetalle.cantidad              "
                + "                           AS cantidadPedida,         "
                + "         tblDctosOrdenesDetalle.pesoPedido,           "
                + "         tmpFIC.referencia,                           "
                + "         tmpFIC.nombreOperacion,                      "
                + "         tmpFIC.referenciaCliente,                    "
                + "         tmpFIC.nombreReferencia,                     "
                + "         tmpFIC.nombreItem,                           "
                + "         tblTerceros.nombreTercero,                   "
                + "         1 AS idOperacionAnterior,                    "
                + "         tmpFIC.idOperacion                           "
                + "   FROM   tblDctosOrdenes                             "
                + "   INNER JOIN tblTerceros                             "
                + "   ON  tblTerceros.idCliente =                        "
                + "                       tblDctosOrdenes.idCliente      "
                + "   INNER JOIN (                                       "
                + "   SELECT tblPlusFicha.idFicha,                       "
                + "          MAX(tblPlusFicha.referencia)                "
                + "                        AS referencia,                "
                + "          MAX(tblJobOperacion.nombreOperacion)        "
                + "                           AS nombreOperacion,        "
                + "          MAX(tblPlusFicha.referenciaCliente)         "
                + "                         AS referenciaCliente,        "
                + "          MAX(tblPlusFicha.nombreReferencia)          "
                + "                          AS nombreReferencia,        "
                + "          tblJobEscalaDetalle.nombreItem,             "
                + "          tblPlusFicha.idOperacion                    "
                + "   FROM   tblPlusFicha                                "
                + "   INNER JOIN tblJobOperacion                         "
                + "   ON tblPlusFicha.idOperacion      =                 "
                + "                 tblJobOperacion.idOperacion          "
                + "   INNER JOIN tblJobEscalaDetalle                     "
                + "   ON tblPlusFicha.idEscala         =                 "
                + "                tblJobEscalaDetalle.idEscala          "
                + "   AND tblPlusFicha.vrEscala        =                 "
                + "                    tblJobEscalaDetalle.item          "
                + "   WHERE   tblPlusFicha.idOperacion =                 "
                + getIdOperacion() + "                                   "
                + "   AND tblPlusFicha.idEscala                          "
                + "                    IN (600, 710, 910, 1000,1100)     "
                + "   AND tblPlusFicha.vrEscala        =                 "
                + getVrEscala() + "                                      "
                + "   GROUP BY tblPlusFicha.idFicha,                     "
                + "            tblPlusFicha.idOperacion,                 "
                + "            tblJobEscalaDetalle.nombreItem)           "
                + "                                  AS tmpFIC           "
                + "   ON tblDctosOrdenes.idFicha  =                      "
                + "                             tmpFIC.idFicha           "
                + "   INNER JOIN tblDctosOrdenesDetalle                  "
                + "   ON tblDctosOrdenesDetalle.idLocal     =            "
                + "                          tblDctosOrdenes.idLocal     "
                + "   AND   tblDctosOrdenesDetalle.idTipoOrden =         "
                + "                      tblDctosOrdenes.idTipoOrden     "
                + "   AND   tblDctosOrdenesDetalle.idOrden     =         "
                + "                        tblDctosOrdenes.idOrden       "
                + "   WHERE tblDctosOrdenes.idLocal            =         "
                + getIdLocal() + "                                       "
                + "   AND   tblDctosOrdenes.idTipoOrden        =         "
                + getIdTipoOrden() + "                                   "
                + "  AND tblDctosOrdenesDetalle.idEstadoRefOriginal !=9  "
                + "  AND ( tblDctosOrdenesDetalle.cantidad       >       "
                + "           tblDctosOrdenesDetalle.cantidadFacturada ) "
                + "   AND   tblDctosOrdenes.numeroOrden        > 0       "
                + "   AND   tblTerceros.idTipoTercero          = 1 )     "
                + "                                      AS tmpPED )     "
                + "                                      AS tmpPEN       "
                + "  ORDER BY tmpPEN.nombreTercero,                      "
                + "           tmpPEN.numeroOrden,                        "
                + "           tmpPEN.fechaEntrega ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaPluFicha fachadaBean;

            //
            while (rs.next()) {

                //
                fachadaBean = new FachadaPluFicha();

                //
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setIdOrden(rs.getInt("idOrden"));
                fachadaBean.setItemPadre(rs.getInt("itemPadre"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setNumeroOrden(rs.getInt("numeroOrden"));
                fachadaBean.setIdFicha(rs.getInt("idFicha"));
                fachadaBean.setFechaEntrega(rs.getString("fechaEntrega"));
                fachadaBean.setReferencia(rs.getString("referencia"));
                fachadaBean.setNombreOperacion(rs.getString("nombreOperacion"));
                fachadaBean.setReferenciaCliente(
                        rs.getString("referenciaCliente"));
                fachadaBean.setNombreItem(rs.getString("nombreItem"));
                fachadaBean.setNombreTercero(rs.getString("nombreTercero"));

                //
                fachadaBean.setCantidadPendiente(
                        rs.getDouble("cantidadTerminadaAnterior")
                        - rs.getDouble("cantidadTerminadaActual"));
                fachadaBean.setPesoPendiente(
                        rs.getDouble("pesoTerminadoAnterior")
                        - rs.getDouble("pesoTerminadoActual"));

                //
                //
                fachadaBean.setPesoPerdido(0);
                fachadaBean.setCantidad(0);
                fachadaBean.setPesoTerminado(0);
                fachadaBean.setCantidadTerminada(0);
                fachadaBean.setCantidadPerdida(0);
                fachadaBean.setPorcentajeTerminado(0);

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

    // listaOTPrograma
    public Vector listaOTPrograma() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        //
        String selectStr =
                "  SELECT tmpPEN.idLocal,                                 "
                + "         tmpPEN.idTipoOrden,                           "
                + "         tmpPEN.idOrden,                               "
                + "         tmpPEN.itemPadre,                             "
                + "         tmpPEN.idCliente,                             "
                + "         tmpPEN.numeroOrden,                           "
                + "         tmpPEN.idFicha,                               "
                + "         tmpPEN.idLog,                                 "
                + "         tmpPEN.fechaEntrega,                          "
                + "         tmpPEN.referencia,                            "
                + "         tmpPEN.nombreOperacion,                       "
                + "         tmpPEN.idColor,                               "
                + "         tmpPEN.referenciaCliente,                     "
                + "         tmpPEN.nombreReferencia,                      "
                + "         tmpPEN.nombreItem,                            "
                + "         tmpPEN.nombreTercero,                         "
                + "         tmpPEN.idOperacionAnterior,                   "
                + "         tmpPEN.idOperacion,                           "
                + "         CASE                                          "
                + "         WHEN   tmpPEN.idOperacionAnterior = 1         "
                + "                        THEN tmpPEN.pesoPedido         "
                + "         ELSE   tmpPEN.pesoTerminadoAnterior           "
                + "         END AS pesoTerminadoAnterior,                 "
                + "         CASE                                          "
                + "         WHEN   tmpPEN.idOperacionAnterior = 1         "
                + "                    THEN tmpPEN.cantidadPedida         "
                + "         ELSE   tmpPEN.cantidadTerminadaAnterior       "
                + "         END AS cantidadTerminadaAnterior,             "
                + "         tmpPEN.pesoTerminadoActual,                   "
                + "         tmpPEN.cantidadTerminadaActual,               "
                + "         tmpPEN.idOrdenPrograma                        "
                + "  FROM (                                               "
                + "  SELECT tmpPED.*,                                     "
                + "  ( SELECT SUM(tblDctosOrdenesDetalle.pesoTerminado)   "
                + "  FROM tblDctosOrdenesDetalle                          "
                + "  WHERE tblDctosOrdenesDetalle.idLocal           =     "
                + "                                     tmpPED.idLocal    "
                + "  AND   tblDctosOrdenesDetalle.idTipoOrdenOrigen =     "
                + "                                 tmpPED.idTipoOrden    "
                + "  AND   tblDctosOrdenesDetalle.idOrdenOrigen     =     "
                + "                                     tmpPED.idOrden    "
                + "  AND   tblDctosOrdenesDetalle.itemPadre         =     "
                + "                                   tmpPED.itemPadre )  "
                + "                         AS pesoTerminadoAnterior,     "
                + "  ( SELECT                                             "
                + "      SUM(tblDctosOrdenesDetalle.cantidadTerminada)    "
                + "  FROM tblDctosOrdenesDetalle                          "
                + "  WHERE tblDctosOrdenesDetalle.idLocalOrigen     =     "
                + "                                     tmpPED.idLocal    "
                + "  AND   tblDctosOrdenesDetalle.idTipoOrdenOrigen =     "
                + "                                 tmpPED.idTipoOrden    "
                + "  AND   tblDctosOrdenesDetalle.idOrdenOrigen     =     "
                + "                                     tmpPED.idOrden    "
                + "  AND   tblDctosOrdenesDetalle.itemPadre         =     "
                + "                                   tmpPED.itemPadre )  "
                + "                      AS cantidadTerminadaAnterior,    "
                + "  ( SELECT SUM(tblDctosOrdenesProgreso.pesoTerminado)  "
                + "  FROM tblDctosOrdenesProgreso                         "
                + "  WHERE tblDctosOrdenesProgreso.idLocal           =    "
                + "                                      tmpPED.idLocal   "
                + "  AND   tblDctosOrdenesProgreso.idTipoOrden       =    "
                + "                                  tmpPED.idTipoOrden   "
                + "  AND   tblDctosOrdenesProgreso.idOrden           =    "
                + "                                      tmpPED.idOrden   "
                + "  AND   tblDctosOrdenesProgreso.itemPadre         =    "
                + "                                    tmpPED.itemPadre   "
                + "  AND   tblDctosOrdenesProgreso.idOperacion       =    "
                + "                                  tmpPED.idOperacion ) "
                + "                              AS pesoTerminadoActual,  "
                + "  ( SELECT                                             "
                + "      SUM(tblDctosOrdenesProgreso.cantidadTerminada)   "
                + "  FROM tblDctosOrdenesProgreso                         "
                + "  WHERE tblDctosOrdenesProgreso.idLocal          =     "
                + "                                      tmpPED.idLocal   "
                + "  AND   tblDctosOrdenesProgreso.idTipoOrden      =     "
                + "                                  tmpPED.idTipoOrden   "
                + "  AND   tblDctosOrdenesProgreso.idOrden          =     "
                + "                                      tmpPED.idOrden   "
                + "  AND   tblDctosOrdenesProgreso.itemPadre        =     "
                + "                                    tmpPED.itemPadre   "
                + "  AND   tblDctosOrdenesProgreso.idOperacion       =    "
                + "                                 tmpPED.idOperacion )  "
                + "                         AS cantidadTerminadaActual    "
                + "  FROM (                                               "
                + "   SELECT tblDctosOrdenes.idLocal,                     "
                + "          tblDctosOrdenes.idTipoOrden,                 "
                + "          tblDctosOrdenes.idOrden,                     "
                + "          tblDctosOrdenesDetalle.itemPadre,            "
                + "          tblDctosOrdenes.idCliente,                   "
                + "          tblDctosOrdenes.numeroOrden,                 "
                + "          tblDctosOrdenes.idFicha,                     "
                + "          tblDctosOrdenes.idLog,                       "
                + "          tblDctosOrdenesDetalle.fechaEntrega,         "
                + "          tblDctosOrdenesDetalle.cantidad              "
                + "                            AS cantidadPedida,         "
                + "          tblDctosOrdenesDetalle.pesoPedido,           "
                + "          tmpFIC.referencia,                           "
                + "          tmpFIC.nombreOperacion,                      "
                + "          tmpFIC.idColor,                              "
                + "          tmpFIC.referenciaCliente,                    "
                + "          tmpFIC.nombreReferencia,                     "
                + "          tmpFIC.nombreItem,                           "
                + "          tblTerceros.nombreTercero,                   "
                + "          1 AS idOperacionAnterior,                    "
                + "          tmpFIC.idOperacion,                          "
                + "          tmpPPL.idOrdenPrograma                       "
                + "    FROM   tblDctosOrdenes                             "
                + "    INNER JOIN tblTerceros                             "
                + "    ON  tblTerceros.idCliente =                        "
                + "                        tblDctosOrdenes.idCliente      "
                + "    INNER JOIN (                                       "
                + "    SELECT tblJobProgramaPlusFicha.idFicha,            "
                + "           MAX(tblJobProgramaPlusFicha.referencia)     "
                + "                         AS referencia,                "
                + "           MAX(tblJobOperacion.nombreOperacion)        "
                + "                            AS nombreOperacion,        "
                + "           MAX(tblJobOperacion.idColor)                "
                + "                                    AS idColor,        "
                + "      MAX(tblJobProgramaPlusFicha.referenciaCliente)   "
                + "                          AS referenciaCliente,        "
                + "        MAX(tblJobProgramaPlusFicha.nombreReferencia)  "
                + "                           AS nombreReferencia,        "
                + "           tblJobEscalaDetalle.nombreItem,             "
                + "           tblJobProgramaPlusFicha.idOperacion         "
                + "    FROM   tblJobProgramaPlusFicha                     "
                + "    INNER JOIN tblJobOperacion                         "
                + "    ON tblJobProgramaPlusFicha.idOperacion      =      "
                + "                  tblJobOperacion.idOperacion          "
                + "    INNER JOIN tblJobEscalaDetalle                     "
                + "    ON tblJobProgramaPlusFicha.idEscala         =      "
                + "                 tblJobEscalaDetalle.idEscala          "
                + "    AND tblJobProgramaPlusFicha.vrEscala        =      "
                + "                     tblJobEscalaDetalle.item          "
                + "    WHERE   tblJobProgramaPlusFicha.idOperacion =      "
                + getIdOperacion() + "                                    "
                + "    AND tblJobProgramaPlusFicha.idEscala               "
                + "                     IN (600, 710, 910, 1000,1100)     "
                + "    AND tblJobProgramaPlusFicha.vrEscala        =      "
                + getVrEscala() + "                                       "
                + "    GROUP BY tblJobProgramaPlusFicha.idFicha,          "
                + "             tblJobProgramaPlusFicha.idOperacion,      "
                + "             tblJobEscalaDetalle.nombreItem)           "
                + "                                   AS tmpFIC           "
                + "    ON tblDctosOrdenes.idFicha  =                      "
                + "                              tmpFIC.idFicha           "
                + "    INNER JOIN tblDctosOrdenesDetalle                  "
                + "    ON tblDctosOrdenesDetalle.idLocal     =            "
                + "                           tblDctosOrdenes.idLocal     "
                + "    AND   tblDctosOrdenesDetalle.idTipoOrden =         "
                + "                       tblDctosOrdenes.idTipoOrden     "
                + "    AND   tblDctosOrdenesDetalle.idOrden     =         "
                + "                         tblDctosOrdenes.idOrden       "
                + "    INNER JOIN                                         "
                + "     ( SELECT tblJobProgramaPlusFicha.idLocal          "
                + "           ,tblJobProgramaPlusFicha.idTipoOrden        "
                + "           ,tblJobProgramaPlusFicha.idOrden            "
                + "           ,tblJobProgramaPlusFicha.itemPadre          "
                + "           ,tblJobProgramaPlusFicha.idOperacion        "
                + "           ,tblJobProgramaPlusFicha.fechaPrograma      "
                + "           ,tblJobProgramaPlusFicha.estadoPrograma     "
                + "           ,tblJobProgramaPlusFicha.idOrdenPrograma    "
                + "       FROM tblJobProgramaPlusFicha                    "
                + "       GROUP BY tblJobProgramaPlusFicha.idLocal        "
                + "           ,tblJobProgramaPlusFicha.idTipoOrden        "
                + "           ,tblJobProgramaPlusFicha.idOrden            "
                + "           ,tblJobProgramaPlusFicha.itemPadre          "
                + "           ,tblJobProgramaPlusFicha.idOperacion        "
                + "           ,tblJobProgramaPlusFicha.fechaPrograma      "
                + "           ,tblJobProgramaPlusFicha.estadoPrograma     "
                + "           ,tblJobProgramaPlusFicha.idOrdenPrograma)   "
                + "                                          AS tmpPPL    "
                + "       ON    tblDctosOrdenesDetalle.idLocal     =      "
                + "                                      tmpPPL.idLocal   "
                + "       AND   tblDctosOrdenesDetalle.idTipoOrden =      "
                + "                                  tmpPPL.idTipoOrden   "
                + "       AND   tblDctosOrdenesDetalle.idorden     =      "
                + "                                      tmpPPL.idorden   "
                + "       AND   tblDctosOrdenesDetalle.itemPadre     =    "
                + "                                    tmpPPL.itemPadre   "
                + "    WHERE tblDctosOrdenes.idLocal            =         "
                + getIdLocal() + "                                        "
                + "    AND   tblDctosOrdenes.idTipoOrden        =         "
                + getIdTipoOrden() + "                                    "
                + "    AND   tblDctosOrdenes.numeroOrden        > 0       "
                + "    AND   tblTerceros.idTipoTercero          = 1       "
                + "   AND tmpPPL.fechaPrograma                  =        '"
                + getFechaProgramaSqlServer() + "'                        "
                + "   AND tmpPPL.idOperacion                    =         "
                + getIdOperacion() + " )                                  "
                + "                                       AS tmpPED )     "
                + "                                       AS tmpPEN       "
                + "   ORDER BY tmpPEN.idOrdenPrograma                     ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaPluFicha fachadaBean;

            //
            while (rs.next()) {

                //
                fachadaBean = new FachadaPluFicha();

                //
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setIdOrden(rs.getInt("idOrden"));
                fachadaBean.setItemPadre(rs.getInt("itemPadre"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setNumeroOrden(rs.getInt("numeroOrden"));
                fachadaBean.setIdFicha(rs.getInt("idFicha"));
                fachadaBean.setFechaEntrega(rs.getString("fechaEntrega"));
                fachadaBean.setReferencia(rs.getString("referencia"));
                fachadaBean.setNombreOperacion(rs.getString("nombreOperacion"));
                fachadaBean.setReferenciaCliente(
                        rs.getString("referenciaCliente"));
                fachadaBean.setNombreItem(rs.getString("nombreItem"));
                fachadaBean.setNombreTercero(rs.getString("nombreTercero"));
                fachadaBean.setIdOrdenPrograma(rs.getInt("idOrdenPrograma"));

                //
                fachadaBean.setCantidadPendiente(
                        rs.getDouble("cantidadTerminadaAnterior")
                        - rs.getDouble("cantidadTerminadaActual"));
                fachadaBean.setPesoPendiente(
                        rs.getDouble("pesoTerminadoAnterior")
                        - rs.getDouble("pesoTerminadoActual"));

                //
                //
                fachadaBean.setPesoPerdido(0);
                fachadaBean.setCantidad(0);
                fachadaBean.setPesoTerminado(0);
                fachadaBean.setCantidadTerminada(0);
                fachadaBean.setCantidadPerdida(0);
                fachadaBean.setPorcentajeTerminado(0);

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

    // listaOTProgramaTouch
    public Vector listaOTProgramaTouch() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        //
        String selectStr =
                "  SELECT tmpPEN.idLocal,                                 "
                + "         tmpPEN.idTipoOrden,                           "
                + "         tmpPEN.idOrden,                               "
                + "         tmpPEN.itemPadre,                             "
                + "         tmpPEN.idCliente,                             "
                + "         tmpPEN.numeroOrden,                           "
                + "         tmpPEN.idFicha,                               "
                + "         tmpPEN.idLog,                                 "
                + "         tmpPEN.fechaEntrega,                          "
                + "         tmpPEN.referencia,                            "
                + "         tmpPEN.nombreOperacion,                       "
                + "         tmpPEN.idColor,                               "
                + "         tmpPEN.referenciaCliente,                     "
                + "         tmpPEN.nombreReferencia,                      "
                + "         tmpPEN.nombreItem,                            "
                + "         tmpPEN.nombreTercero,                         "
                + "         tmpPEN.idOperacionAnterior,                   "
                + "         tmpPEN.idOperacion,                           "
                + "         CASE                                          "
                + "         WHEN   tmpPEN.idOperacionAnterior = 1         "
                + "                        THEN tmpPEN.pesoPedido         "
                + "         ELSE   tmpPEN.pesoTerminadoAnterior           "
                + "         END AS pesoTerminadoAnterior,                 "
                + "         CASE                                          "
                + "         WHEN   tmpPEN.idOperacionAnterior = 1         "
                + "                    THEN tmpPEN.cantidadPedida         "
                + "         ELSE   tmpPEN.cantidadTerminadaAnterior       "
                + "         END AS cantidadTerminadaAnterior,             "
                + "         tmpPEN.pesoTerminadoActual,                   "
                + "         tmpPEN.cantidadTerminadaActual,               "
                + "         tmpPEN.idOrdenPrograma                        "
                + "  FROM (                                               "
                + "  SELECT tmpPED.*,                                     "
                + "  ( SELECT SUM(tblDctosOrdenesDetalle.pesoTerminado)   "
                + "  FROM tblDctosOrdenesDetalle                          "
                + "  WHERE tblDctosOrdenesDetalle.idLocalOrigen     =     "
                + "                                     tmpPED.idLocal    "
                + "  AND   tblDctosOrdenesDetalle.idTipoOrdenOrigen =     "
                + "                                 tmpPED.idTipoOrden    "
                + "  AND   tblDctosOrdenesDetalle.idOrdenOrigen     =     "
                + "                                     tmpPED.idOrden    "
                + "  AND   tblDctosOrdenesDetalle.itemPadre         =     "
                + "                                   tmpPED.itemPadre    "
                + "  AND   tblDctosOrdenesDetalle.idOperacion       =     "
                + "                         tmpPED.idOperacionAnterior    "
                + "  AND   tblDctosOrdenesDetalle.idPlu         = 209     "
                + "  AND   tblDctosOrdenesDetalle.cantidad      < 0 )     "
                + "                         AS pesoTerminadoAnterior,     "
                + "  ( SELECT                                             "
                + "      SUM(tblDctosOrdenesDetalle.cantidadTerminada)    "
                + "  FROM tblDctosOrdenesDetalle                          "
                + "  WHERE tblDctosOrdenesDetalle.idLocalOrigen     =     "
                + "                                     tmpPED.idLocal    "
                + "  AND   tblDctosOrdenesDetalle.idTipoOrdenOrigen =     "
                + "                                 tmpPED.idTipoOrden    "
                + "  AND   tblDctosOrdenesDetalle.idOrdenOrigen     =     "
                + "                                     tmpPED.idOrden    "
                + "  AND   tblDctosOrdenesDetalle.itemPadre         =     "
                + "                                   tmpPED.itemPadre    "
                + "  AND   tblDctosOrdenesDetalle.idOperacion       =     "
                + "                         tmpPED.idOperacionAnterior    "
                + "  AND   tblDctosOrdenesDetalle.idPlu         = 209     "
                + "  AND   tblDctosOrdenesDetalle.cantidad      < 0 )     "
                + "                      AS cantidadTerminadaAnterior,    "
                + "  ( SELECT SUM(tblDctosOrdenesDetalle.pesoTerminado)   "
                + "  FROM tblDctosOrdenesDetalle                          "
                + "  WHERE tblDctosOrdenesDetalle.idLocalOrigen     =     "
                + "                                      tmpPED.idLocal   "
                + "  AND   tblDctosOrdenesDetalle.idTipoOrdenOrigen =     "
                + "                                  tmpPED.idTipoOrden   "
                + "  AND   tblDctosOrdenesDetalle.idOrdenOrigen     =     "
                + "                                      tmpPED.idOrden   "
                + "  AND   tblDctosOrdenesDetalle.itemPadre         =     "
                + "                                    tmpPED.itemPadre   "
                + "  AND   tblDctosOrdenesDetalle.idOperacion       =     "
                + "                                  tmpPED.idOperacion   "
                + "  AND   tblDctosOrdenesDetalle.idPlu         = 209     "
                + "  AND   tblDctosOrdenesDetalle.cantidad      < 0 )     "
                + "                              AS pesoTerminadoActual,  "
                + "  ( SELECT                                             "
                + "       SUM(tblDctosOrdenesDetalle.cantidadTerminada)   "
                + "  FROM tblDctosOrdenesDetalle                          "
                + "  WHERE tblDctosOrdenesDetalle.idLocalOrigen     =     "
                + "                                      tmpPED.idLocal   "
                + "  AND   tblDctosOrdenesDetalle.idTipoOrdenOrigen =     "
                + "                                  tmpPED.idTipoOrden   "
                + "  AND   tblDctosOrdenesDetalle.idOrdenOrigen     =     "
                + "                                      tmpPED.idOrden   "
                + "  AND   tblDctosOrdenesDetalle.itemPadre         =     "
                + "                                    tmpPED.itemPadre   "
                + "  AND   tblDctosOrdenesDetalle.idOperacion       =     "
                + "                                  tmpPED.idOperacion   "
                + "  AND   tblDctosOrdenesDetalle.idPlu         = 209     "
                + "  AND   tblDctosOrdenesDetalle.cantidad      < 0 )     "
                + "                         AS cantidadTerminadaActual    "
                + "  FROM (                                               "
                + "   SELECT tblDctosOrdenes.idLocal,                     "
                + "          tblDctosOrdenes.idTipoOrden,                 "
                + "          tblDctosOrdenes.idOrden,                     "
                + "          tblDctosOrdenesDetalle.itemPadre,            "
                + "          tblDctosOrdenes.idCliente,                   "
                + "          tblDctosOrdenes.numeroOrden,                 "
                + "          tblDctosOrdenes.idFicha,                     "
                + "          tblDctosOrdenes.idLog,                       "
                + "          tblDctosOrdenesDetalle.fechaEntrega,         "
                + "          tblDctosOrdenesDetalle.cantidad              "
                + "                            AS cantidadPedida,         "
                + "          tblDctosOrdenesDetalle.pesoPedido,           "
                + "          tmpFIC.referencia,                           "
                + "          tmpFIC.nombreOperacion,                      "
                + "          tmpFIC.idColor,                              "
                + "          tmpFIC.referenciaCliente,                    "
                + "          tmpFIC.nombreReferencia,                     "
                + "          tmpFIC.nombreItem,                           "
                + "          tblTerceros.nombreTercero,                   "
                + "          (SELECT TOP 1 tblPlusFicha.idOperacion       "
                + "          FROM   tblPlusFicha                          "
                + "          INNER JOIN tblJobOperacion                   "
                + "          ON tblPlusFicha.idOperacion =                "
                + "                   tblJobOperacion.idOperacion         "
                + "          WHERE   tblPlusFicha.idFicha =               "
                + "                      tblDctosOrdenes.idFicha          "
                + "          AND tblPlusFicha.idEscala    =  610          "
                + "          AND tblPlusFicha.vrEscala    =               "
                + getIdOperacion() + " )                                  "
                + "                         AS idOperacionAnterior,       "
                + "          tmpFIC.idOperacion,                          "
                + "          tmpPPL.idOrdenPrograma                       "
                + "    FROM   tblDctosOrdenes                             "
                + "    INNER JOIN tblTerceros                             "
                + "    ON  tblTerceros.idCliente =                        "
                + "                        tblDctosOrdenes.idCliente      "
                + "    INNER JOIN (                                       "
                + "    SELECT tblPlusFicha.idFicha,                       "
                + "           MAX(tblPlusFicha.referencia)                "
                + "                         AS referencia,                "
                + "           MAX(tblJobOperacion.nombreOperacion)        "
                + "                            AS nombreOperacion,        "
                + "           MAX(tblJobOperacion.idColor)                "
                + "                                    AS idColor,        "
                + "           MAX(tblPlusFicha.referenciaCliente)         "
                + "                          AS referenciaCliente,        "
                + "           MAX(tblPlusFicha.nombreReferencia)          "
                + "                           AS nombreReferencia,        "
                + "           tblJobEscalaDetalle.nombreItem,             "
                + "           tblPlusFicha.idOperacion                    "
                + "    FROM   tblPlusFicha                                "
                + "    INNER JOIN tblJobOperacion                         "
                + "    ON tblPlusFicha.idOperacion      =                 "
                + "                  tblJobOperacion.idOperacion          "
                + "    INNER JOIN tblJobEscalaDetalle                     "
                + "    ON tblPlusFicha.idEscala         =                 "
                + "                 tblJobEscalaDetalle.idEscala          "
                + "    AND tblPlusFicha.vrEscala        =                 "
                + "                     tblJobEscalaDetalle.item          "
                + "    WHERE   tblPlusFicha.idOperacion =                 "
                + getIdOperacion() + "                                    "
                + "    AND tblPlusFicha.idEscala                          "
                + "                     IN (600, 710, 910, 1000,1100)     "
                + "    GROUP BY tblPlusFicha.idFicha,                     "
                + "             tblPlusFicha.idOperacion,                 "
                + "             tblJobEscalaDetalle.nombreItem)           "
                + "                                   AS tmpFIC           "
                + "    ON tblDctosOrdenes.idFicha  =                      "
                + "                              tmpFIC.idFicha           "
                + "    INNER JOIN tblDctosOrdenesDetalle                  "
                + "    ON tblDctosOrdenesDetalle.idLocal     =            "
                + "                           tblDctosOrdenes.idLocal     "
                + "    AND   tblDctosOrdenesDetalle.idTipoOrden =         "
                + "                       tblDctosOrdenes.idTipoOrden     "
                + "    AND   tblDctosOrdenesDetalle.idOrden     =         "
                + "                         tblDctosOrdenes.idOrden       "
                + "    INNER JOIN                                         "
                + "     ( SELECT tblJobProgramaPlusFicha.idLocal          "
                + "           ,tblJobProgramaPlusFicha.idTipoOrden        "
                + "           ,tblJobProgramaPlusFicha.idOrden            "
                + "           ,tblJobProgramaPlusFicha.itemPadre          "
                + "           ,tblJobProgramaPlusFicha.idOperacion        "
                + "           ,tblJobProgramaPlusFicha.fechaPrograma      "
                + "           ,tblJobProgramaPlusFicha.estadoPrograma     "
                + "           ,tblJobProgramaPlusFicha.idOrdenPrograma    "
                + "       FROM tblJobProgramaPlusFicha                    "
                + "       GROUP BY tblJobProgramaPlusFicha.idLocal        "
                + "           ,tblJobProgramaPlusFicha.idTipoOrden        "
                + "           ,tblJobProgramaPlusFicha.idOrden            "
                + "           ,tblJobProgramaPlusFicha.itemPadre          "
                + "           ,tblJobProgramaPlusFicha.idOperacion        "
                + "           ,tblJobProgramaPlusFicha.fechaPrograma      "
                + "           ,tblJobProgramaPlusFicha.estadoPrograma     "
                + "           ,tblJobProgramaPlusFicha.idOrdenPrograma)   "
                + "                                          AS tmpPPL    "
                + "       ON    tblDctosOrdenesDetalle.idLocal     =      "
                + "                                      tmpPPL.idLocal   "
                + "       AND   tblDctosOrdenesDetalle.idTipoOrden =      "
                + "                                  tmpPPL.idTipoOrden   "
                + "       AND   tblDctosOrdenesDetalle.idorden     =      "
                + "                                      tmpPPL.idorden   "
                + "       AND   tblDctosOrdenesDetalle.itemPadre     =    "
                + "                                    tmpPPL.itemPadre   "
                + "    WHERE tblDctosOrdenes.idLocal            =         "
                + getIdLocal() + "                                        "
                + "    AND   tblDctosOrdenes.idTipoOrden        =         "
                + getIdTipoOrden() + "                                    "
                + "    AND   tblDctosOrdenes.numeroOrden        > 0       "
                + "   AND tmpPPL.fechaPrograma                  =        '"
                + getFechaProgramaSqlServer() + "'                        "
                + "   AND tmpPPL.idOperacion                    =         "
                + getIdOperacion() + " )                                  "
                + "                                       AS tmpPED )     "
                + "                                       AS tmpPEN       "
                + "   ORDER BY tmpPEN.idOrdenPrograma                     ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaPluFicha fachadaBean;

            //
            while (rs.next()) {

                //
                fachadaBean = new FachadaPluFicha();

                //
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setIdOrden(rs.getInt("idOrden"));
                fachadaBean.setItemPadre(rs.getInt("itemPadre"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setNumeroOrden(rs.getInt("numeroOrden"));
                fachadaBean.setIdFicha(rs.getInt("idFicha"));
                fachadaBean.setIdLog(rs.getInt("idLog"));
                fachadaBean.setFechaEntrega(rs.getString("fechaEntrega"));
                fachadaBean.setReferencia(rs.getString("referencia"));
                fachadaBean.setNombreOperacion(rs.getString("nombreOperacion"));
                fachadaBean.setIdColor(rs.getString("idColor").trim());
                fachadaBean.setReferenciaCliente(
                        rs.getString("referenciaCliente"));
                fachadaBean.setNombreItem(rs.getString("nombreItem"));
                fachadaBean.setNombreTercero(rs.getString("nombreTercero"));
                fachadaBean.setIdOrdenPrograma(rs.getInt("idOrdenPrograma"));

                //
                fachadaBean.setCantidadPendiente(
                        rs.getDouble("cantidadTerminadaAnterior")
                        - rs.getDouble("cantidadTerminadaActual"));
                fachadaBean.setPesoPendiente(
                        rs.getDouble("pesoTerminadoAnterior")
                        - rs.getDouble("pesoTerminadoActual"));

                //
                //
                fachadaBean.setPesoPerdido(0);
                fachadaBean.setCantidad(0);
                fachadaBean.setPesoTerminado(0);
                fachadaBean.setCantidadTerminada(0);
                fachadaBean.setCantidadPerdida(0);
                fachadaBean.setPorcentajeTerminado(0);

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

    // listaOTProgramaTouch_Operacion_Pedido
    public Vector listaOTProgramaTouch_Operacion_Pedido(double xPorcentajeAdicion) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        //
        String selectStr =
                " SELECT DISTINCT  *                                      "
                + " FROM (                                                "
                + "   SELECT tmpPEN.idLocal,                              "
                + "        tmpPEN.idTipoOrden,                            "
                + "        tmpPEN.idOrden,                                "
                + "        tmpPEN.itemPadre,                              "
                + "        tmpPEN.idCliente,                              "
                + "        tmpPEN.numeroOrden,                            "
                + "        tmpPEN.idFicha,                                "
                + "        tmpPEN.idLog,                                  "
                + "        tmpPEN.fechaEntrega,                           "
                + "        tmpPEN.referencia,                             "
                + "        tmpPEN.nombreOperacion,                        "
                + "        tmpPEN.idColor,                                "
                + "        tmpPEN.referenciaCliente,                      "
                + "        tmpPEN.nombreReferencia,                       "
                + "        tmpPEN.nombreItem,                             "
                + "        tmpPEN.nombreTercero,                          "
                + "        tmpPEN.idOperacionAnterior,                    "
                + "        tmpPEN.idOperacion,                            "
//                + "        CASE                                           "
//                + "        WHEN   tmpPEN.idOperacionAnterior = 1          "
//                + "                       THEN tmpPEN.pesoPedido          "
                + "                        tmpPEN.pesoPedido          "
//                + "        ELSE   tmpPEN.pesoTerminadoAnterior            "
                + "         AS pesoTerminadoAnterior,                  "
//                + "        CASE                                           "
//                + "        WHEN   tmpPEN.idOperacionAnterior = 1          "
//                + "                   THEN tmpPEN.cantidadPedida          "
                + "                   tmpPEN.cantidadPedida          "
//                + "        ELSE   tmpPEN.cantidadTerminadaAnterior        "
                + "        AS cantidadTerminadaAnterior,              "
                + "        ISNULL(tmpPEN.pesoTerminadoActual,0)           "
                + "                         AS pesoTerminadoActual,       "
                + "        ISNULL(tmpPEN.cantidadTerminadaActual,0)       "
                + "                     AS cantidadTerminadaActual,       "
                + "        tmpPEN.idOrdenPrograma                         "
                + " FROM (                                                "
                + " SELECT tmpPED.*,                                      "
                + " ( SELECT SUM(tblDctosOrdenesDetalle.pesoTerminado)    "
                + " FROM tblDctosOrdenesDetalle                           "
                + " WHERE tblDctosOrdenesDetalle.idLocalOrigen     =      "
                + "                                    tmpPED.idLocal     "
                + " AND   tblDctosOrdenesDetalle.idTipoOrdenOrigen =      "
                + "                                tmpPED.idTipoOrden     "
                + " AND   tblDctosOrdenesDetalle.idOrdenOrigen     =      "
                + "                                    tmpPED.idOrden     "
                + " AND   tblDctosOrdenesDetalle.itemPadre         =      "
                + "                                  tmpPED.itemPadre     "
                + " AND   tblDctosOrdenesDetalle.idOperacion       =      "
                + "                      tmpPED.idOperacionAnterior )     "
                + "                        AS pesoTerminadoAnterior,      "
                + " ( SELECT                                              "
                + "     SUM(tblDctosOrdenesDetalle.cantidadTerminada)     "
                + " FROM tblDctosOrdenesDetalle                           "
                + " WHERE tblDctosOrdenesDetalle.idLocalOrigen     =      "
                + "                                    tmpPED.idLocal     "
                + " AND   tblDctosOrdenesDetalle.idTipoOrdenOrigen =      "
                + "                                tmpPED.idTipoOrden     "
                + " AND   tblDctosOrdenesDetalle.idOrdenOrigen     =      "
                + "                                    tmpPED.idOrden     "
                + " AND   tblDctosOrdenesDetalle.itemPadre         =      "
                + "                                  tmpPED.itemPadre     "
                + " AND   tblDctosOrdenesDetalle.idOperacion       =      "
                + "                    tmpPED.idOperacionAnterior   )     "
                + "                     AS cantidadTerminadaAnterior,     "
                + " ( SELECT SUM(tblDctosOrdenesProgreso.pesoTerminado)   "
                + " FROM tblDctosOrdenesProgreso                          "
                + " WHERE tblDctosOrdenesProgreso.idLocal           =     "
                + "                                    tmpPED.idLocal     "
                + " AND  tblDctosOrdenesProgreso.idControlTipo     != 2   "
                + " AND   tblDctosOrdenesProgreso.idTipoOrden       =     "
                + "                                tmpPED.idTipoOrden     "
                + " AND   tblDctosOrdenesProgreso.idOrden           =     "
                + "                                    tmpPED.idOrden     "
                + " AND   tblDctosOrdenesProgreso.itemPadre         =     "
                + "                                  tmpPED.itemPadre     "
                + " AND   tblDctosOrdenesProgreso.idOperacion       =     "
                + "                              tmpPED.idOperacion )     "
                + "                           AS pesoTerminadoActual,     "
                + " ( SELECT                                              "
                + "     SUM(tblDctosOrdenesProgreso.cantidadTerminada)    "
                + " FROM tblDctosOrdenesProgreso                          "
                + " WHERE tblDctosOrdenesProgreso.idLocal          =      "
                + "                                   tmpPED.idLocal      "
                + " AND  tblDctosOrdenesProgreso.idControlTipo    != 2    "
                + " AND   tblDctosOrdenesProgreso.idTipoOrden      =      "
                + "                               tmpPED.idTipoOrden      "
                + " AND   tblDctosOrdenesProgreso.idOrden          =      "
                + "                                   tmpPED.idOrden      "
                + " AND   tblDctosOrdenesProgreso.itemPadre        =      "
                + "                                 tmpPED.itemPadre      "
                + " AND   tblDctosOrdenesProgreso.idOperacion       =     "
                + "                              tmpPED.idOperacion )     "
                + "                        AS cantidadTerminadaActual     "
                + " FROM (                                                "
                + "  SELECT tblDctosOrdenes.idLocal,                      "
                + "         tblDctosOrdenes.idTipoOrden,                  "
                + "         tblDctosOrdenes.idOrden,                      "
                + "         tblDctosOrdenesDetalle.itemPadre,             "
                + "         tblDctosOrdenes.idCliente,                    "
                + "         tblDctosOrdenes.numeroOrden,                  "
                + "         tblDctosOrdenes.idFicha,                      "
                + "         tblDctosOrdenes.idLog,                        "
                + "         tblDctosOrdenesDetalle.fechaEntrega,          "
                + "         tblDctosOrdenesDetalle.cantidad               "
                + "                           AS cantidadPedida,          "
                + "         tblDctosOrdenesDetalle.pesoPedido,            "
                + "         tmpFIC.referencia,                            "
                + "         tmpFIC.nombreOperacion,                       "
                + "         tmpFIC.idColor,                               "
                + "         tmpFIC.referenciaCliente,                     "
                + "         tmpFIC.nombreReferencia,                      "
                + "         tmpFIC.nombreItem,                            "
                + "         tblTerceros.nombreTercero,                    "
                + "         1 AS idOperacionAnterior,                     "
                + "         tmpFIC.idOperacion,                           "
                + "         tmpPPL.idOrdenPrograma                        "
                + "   FROM   tblDctosOrdenes                              "
                + "   INNER JOIN tblTerceros                              "
                + "   ON  tblTerceros.idCliente =                         "
                + "                       tblDctosOrdenes.idCliente       "
                + "   INNER JOIN (                                        "
                + "   SELECT tblJobProgramaPlusFicha.idOrden,             "
                + "          tblJobProgramaPlusFicha.idFicha,             "
                + "          MAX(tblJobProgramaPlusFicha.referencia)      "
                + "                        AS referencia,                 "
                + "          MAX(tblJobOperacion.nombreOperacion)         "
                + "                           AS nombreOperacion,         "
                + "          MAX(tblJobOperacion.idColor)                 "
                + "                                   AS idColor,         "
                + "        MAX(tblJobProgramaPlusFicha.referenciaCliente) "
                + "                         AS referenciaCliente,         "
                + "       MAX(tblJobProgramaPlusFicha.nombreReferencia)   "
                + "                          AS nombreReferencia,         "
                + "          tblJobEscalaDetalle.nombreItem,              "
                + "          tblJobProgramaPlusFicha.idOperacion          "
                + "   FROM   tblJobProgramaPlusFicha                      "
                + "   INNER JOIN tblJobOperacion                          "
                + "   ON tblJobProgramaPlusFicha.idOperacion      =       "
                + "                 tblJobOperacion.idOperacion           "
                + "   INNER JOIN tblJobEscalaDetalle                      "
                + "   ON tblJobProgramaPlusFicha.idEscala         =       "
                + "                tblJobEscalaDetalle.idEscala           "
                + "   AND tblJobProgramaPlusFicha.vrEscala        =       "
                + "                    tblJobEscalaDetalle.item           "
                + "   WHERE   tblJobProgramaPlusFicha.idOperacion =       "
                + getIdOperacion() + "                                    "
                + "   AND tblJobProgramaPlusFicha.idEscala                "
                + "                    IN (600, 710, 910, 1000,1100)      "
                + "   GROUP BY tblJobProgramaPlusFicha.idOrden,           "
                + "            tblJobProgramaPlusFicha.idFicha,           "
                + "            tblJobProgramaPlusFicha.idOperacion,       "
                + "            tblJobEscalaDetalle.nombreItem)            "
                + "                                  AS tmpFIC            "
                + "   ON tblDctosOrdenes.idFicha  =                       "
                + "                             tmpFIC.idFicha            "
                + "   AND tblDctosOrdenes.idOrden =                       "
                + "                             tmpFIC.idOrden  	  "
                + "   INNER JOIN tblDctosOrdenesDetalle                   "
                + "   ON tblDctosOrdenesDetalle.idLocal     =             "
                + "                          tblDctosOrdenes.idLocal      "
                + "   AND   tblDctosOrdenesDetalle.idTipoOrden =          "
                + "                      tblDctosOrdenes.idTipoOrden      "
                + "   AND   tblDctosOrdenesDetalle.idOrden     =          "
                + "                        tblDctosOrdenes.idOrden        "
                + "   INNER JOIN                                          "
                + "    ( SELECT tblJobProgramaPlusFicha.idLocal           "
                + "          ,tblJobProgramaPlusFicha.idTipoOrden         "
                + "          ,tblJobProgramaPlusFicha.idOrden             "
                + "          ,tblJobProgramaPlusFicha.itemPadre           "
                + "          ,tblJobProgramaPlusFicha.idOperacion         "
                + "          ,tblJobProgramaPlusFicha.fechaPrograma       "
                + "          ,tblJobProgramaPlusFicha.estadoPrograma      "
                + "          ,tblJobProgramaPlusFicha.idOrdenPrograma     "
                + "      FROM tblJobProgramaPlusFicha                     "
                + "      WHERE tblJobProgramaPlusFicha.idOperacion =      "
                + getIdOperacion() + "                                    "
                + "      GROUP BY tblJobProgramaPlusFicha.idLocal         "
                + "          ,tblJobProgramaPlusFicha.idTipoOrden         "
                + "          ,tblJobProgramaPlusFicha.idOrden             "
                + "          ,tblJobProgramaPlusFicha.itemPadre           "
                + "          ,tblJobProgramaPlusFicha.idOperacion         "
                + "          ,tblJobProgramaPlusFicha.fechaPrograma       "
                + "          ,tblJobProgramaPlusFicha.estadoPrograma      "
                + "          ,tblJobProgramaPlusFicha.idOrdenPrograma)    "
                + "                                         AS tmpPPL     "
                + "      ON    tblDctosOrdenesDetalle.idLocal     =       "
                + "                                     tmpPPL.idLocal    "
                + "      AND   tblDctosOrdenesDetalle.idTipoOrden =       "
                + "                                 tmpPPL.idTipoOrden    "
                + "      AND   tblDctosOrdenesDetalle.idOrden     =       "
                + "                                     tmpPPL.idOrden    "
                + "      AND   tblDctosOrdenesDetalle.itemPadre     =     "
                + "                                   tmpPPL.itemPadre    "
                + "    WHERE tblDctosOrdenes.idLocal            =         "
                + getIdLocal() + "                                        "
                + "   AND   tblDctosOrdenes.idTipoOrden         =         "
                + getIdTipoOrden() + "                                    "
                + " AND   tblDctosOrdenes.numeroOrden        > 0          "
                + " AND tblDctosOrdenesDetalle.idEstadoRefOriginal != 9   "
                + " AND tmpPPL.idOperacion                    =           "
                + getIdOperacion() + " )                                  "
                + "                                      AS tmpPED )      "
                + "                                      AS tmpPEN )      "
                + "                                        AS tmpRES      "
                + "      WHERE tmpRES.pesoTerminadoActual <               "
                + "                    ( tmpRES.pesoTerminadoAnterior *   "
                + xPorcentajeAdicion + " )                                "
                + "     AND tmpRES.fechaEntrega > '20170101'            "
                + "     ORDER BY tmpRES.numeroOrden";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaPluFicha fachadaBean;

            //
            while (rs.next()) {

                //
                fachadaBean = new FachadaPluFicha();

                //
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setIdOrden(rs.getInt("idOrden"));
                fachadaBean.setItemPadre(rs.getInt("itemPadre"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setNumeroOrden(rs.getInt("numeroOrden"));
                fachadaBean.setIdFicha(rs.getInt("idFicha"));
                fachadaBean.setIdLog(rs.getInt("idLog"));
                fachadaBean.setFechaEntrega(rs.getString("fechaEntrega"));
                fachadaBean.setReferencia(rs.getString("referencia"));
                fachadaBean.setNombreOperacion(rs.getString("nombreOperacion"));
                fachadaBean.setIdColor(rs.getString("idColor").trim());
                fachadaBean.setReferenciaCliente(
                        rs.getString("referenciaCliente"));
                fachadaBean.setNombreItem(rs.getString("nombreItem"));
                fachadaBean.setNombreTercero(rs.getString("nombreTercero"));
                fachadaBean.setIdOrdenPrograma(rs.getInt("idOrdenPrograma"));

                //
                fachadaBean.setCantidadPendiente(
                        rs.getDouble("cantidadTerminadaAnterior")
                        - rs.getDouble("cantidadTerminadaActual"));
                fachadaBean.setPesoPendiente(
                        rs.getDouble("pesoTerminadoAnterior")
                        - rs.getDouble("pesoTerminadoActual"));

                //
                //
                fachadaBean.setPesoPerdido(0);
                fachadaBean.setCantidad(0);
                fachadaBean.setPesoTerminado(0);
                fachadaBean.setCantidadTerminada(0);
                fachadaBean.setCantidadPerdida(0);
                fachadaBean.setPorcentajeTerminado(0);

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
    // listaOTProgramaTouch_Operacion_Pedido
    public Vector listaOTProgramaTouch_Operacion_Pedido2(double xPorcentajeAdicion) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        //
        String selectStr =
                " SELECT DISTINCT  *                                      "
                + " FROM (                                                "
                + "   SELECT tmpPEN.idLocal,                              "
                + "        tmpPEN.idTipoOrden,                            "
                + "        tmpPEN.idOrden,                                "
                + "        tmpPEN.itemPadre,                              "
                + "        tmpPEN.idCliente,                              "
                + "        tmpPEN.numeroOrden,                            "
                + "        tmpPEN.idFicha,                                "
                + "        tmpPEN.idLog,                                  "
                + "        tmpPEN.fechaEntrega,                           "
                + "        tmpPEN.referencia,                             "
                + "        tmpPEN.nombreOperacion,                        "
                + "        tmpPEN.idColor,                                "
                + "        tmpPEN.referenciaCliente,                      "
                + "        tmpPEN.nombreReferencia,                       "
                + "        tmpPEN.nombreItem,                             "
                + "        tmpPEN.nombreTercero,                          "
                + "        tmpPEN.idOperacionAnterior,                    "
                + "        tmpPEN.idOperacion,                            "
                + "        CASE                                           "
                + "        WHEN   tmpPEN.idOperacionAnterior = 1          "
                + "                       THEN tmpPEN.pesoPedido          "
                + "        ELSE   tmpPEN.pesoTerminadoAnterior            "
                + "        END AS pesoTerminadoAnterior,                  "
                + "        CASE                                           "
                + "        WHEN   tmpPEN.idOperacionAnterior = 1          "
                + "                   THEN tmpPEN.cantidadPedida          "
                + "        ELSE   tmpPEN.cantidadTerminadaAnterior        "
                + "        END AS cantidadTerminadaAnterior,              "
                + "        ISNULL(tmpPEN.pesoTerminadoActual,0)           "
                + "                         AS pesoTerminadoActual,       "
                + "        ISNULL(tmpPEN.cantidadTerminadaActual,0)       "
                + "                     AS cantidadTerminadaActual,       "
                + "        tmpPEN.idOrdenPrograma                         "
                + " FROM (                                                "
                + " SELECT tmpPED.*,                                      "
                + " ( SELECT SUM(tblDctosOrdenesDetalle.pesoTerminado)    "
                + " FROM tblDctosOrdenesDetalle                           "
                + " WHERE tblDctosOrdenesDetalle.idLocalOrigen     =      "
                + "                                    tmpPED.idLocal     "
                + " AND   tblDctosOrdenesDetalle.idTipoOrdenOrigen =      "
                + "                                tmpPED.idTipoOrden     "
                + " AND   tblDctosOrdenesDetalle.idOrdenOrigen     =      "
                + "                                    tmpPED.idOrden     "
                + " AND   tblDctosOrdenesDetalle.itemPadre         =      "
                + "                                  tmpPED.itemPadre     "
                + " AND   tblDctosOrdenesDetalle.idOperacion       =      "
                + "                      tmpPED.idOperacionAnterior )     "
                + "                        AS pesoTerminadoAnterior,      "
                + " ( SELECT                                              "
                + "     SUM(tblDctosOrdenesDetalle.cantidadTerminada)     "
                + " FROM tblDctosOrdenesDetalle                           "
                + " WHERE tblDctosOrdenesDetalle.idLocalOrigen     =      "
                + "                                    tmpPED.idLocal     "
                + " AND   tblDctosOrdenesDetalle.idTipoOrdenOrigen =      "
                + "                                tmpPED.idTipoOrden     "
                + " AND   tblDctosOrdenesDetalle.idOrdenOrigen     =      "
                + "                                    tmpPED.idOrden     "
                + " AND   tblDctosOrdenesDetalle.itemPadre         =      "
                + "                                  tmpPED.itemPadre     "
                + " AND   tblDctosOrdenesDetalle.idOperacion       =      "
                + "                    tmpPED.idOperacionAnterior   )     "
                + "                     AS cantidadTerminadaAnterior,     "
                + " ( SELECT SUM(tblDctosOrdenesProgreso.pesoTerminado)   "
                + " FROM tblDctosOrdenesProgreso                          "
                + " WHERE tblDctosOrdenesProgreso.idLocal           =     "
                + "                                    tmpPED.idLocal     "
                + " AND  tblDctosOrdenesProgreso.idControlTipo     != 2   "
                + " AND   tblDctosOrdenesProgreso.idTipoOrden       =     "
                + "                                tmpPED.idTipoOrden     "
                + " AND   tblDctosOrdenesProgreso.idOrden           =     "
                + "                                    tmpPED.idOrden     "
                + " AND   tblDctosOrdenesProgreso.itemPadre         =     "
                + "                                  tmpPED.itemPadre     "
                + " AND   tblDctosOrdenesProgreso.idOperacion       =     "
                + "                              tmpPED.idOperacion )     "
                + "                           AS pesoTerminadoActual,     "
                + " ( SELECT                                              "
                + "     SUM(tblDctosOrdenesProgreso.cantidadTerminada)    "
                + " FROM tblDctosOrdenesProgreso                          "
                + " WHERE tblDctosOrdenesProgreso.idLocal          =      "
                + "                                   tmpPED.idLocal      "
                + " AND  tblDctosOrdenesProgreso.idControlTipo    != 2    "
                + " AND   tblDctosOrdenesProgreso.idTipoOrden      =      "
                + "                               tmpPED.idTipoOrden      "
                + " AND   tblDctosOrdenesProgreso.idOrden          =      "
                + "                                   tmpPED.idOrden      "
                + " AND   tblDctosOrdenesProgreso.itemPadre        =      "
                + "                                 tmpPED.itemPadre      "
                + " AND   tblDctosOrdenesProgreso.idOperacion       =     "
                + "                              tmpPED.idOperacion )     "
                + "                        AS cantidadTerminadaActual     "
                + " FROM (                                                "
                + "  SELECT tblDctosOrdenes.idLocal,                      "
                + "         tblDctosOrdenes.idTipoOrden,                  "
                + "         tblDctosOrdenes.idOrden,                      "
                + "         tblDctosOrdenesDetalle.itemPadre,             "
                + "         tblDctosOrdenes.idCliente,                    "
                + "         tblDctosOrdenes.numeroOrden,                  "
                + "         tblDctosOrdenes.idFicha,                      "
                + "         tblDctosOrdenes.idLog,                        "
                + "         tblDctosOrdenesDetalle.fechaEntrega,          "
                + "         tblDctosOrdenesDetalle.cantidad               "
                + "                           AS cantidadPedida,          "
                + "         tblDctosOrdenesDetalle.pesoPedido,            "
                + "         tmpFIC.referencia,                            "
                + "         tmpFIC.nombreOperacion,                       "
                + "         tmpFIC.idColor,                               "
                + "         tmpFIC.referenciaCliente,                     "
                + "         tmpFIC.nombreReferencia,                      "
                + "         tmpFIC.nombreItem,                            "
                + "         tblTerceros.nombreTercero,                    "
                + "         1 AS idOperacionAnterior,                     "
                + "         tmpFIC.idOperacion,                           "
                + "         tmpPPL.idOrdenPrograma                        "
                + "   FROM   tblDctosOrdenes                              "
                + "   INNER JOIN tblTerceros                              "
                + "   ON  tblTerceros.idCliente =                         "
                + "                       tblDctosOrdenes.idCliente       "
                + "   INNER JOIN (                                        "
                + "   SELECT tblJobProgramaPlusFicha.idOrden,             "
                + "          tblJobProgramaPlusFicha.idFicha,             "
                + "          MAX(tblJobProgramaPlusFicha.referencia)      "
                + "                        AS referencia,                 "
                + "          MAX(tblJobOperacion.nombreOperacion)         "
                + "                           AS nombreOperacion,         "
                + "          MAX(tblJobOperacion.idColor)                 "
                + "                                   AS idColor,         "
                + "        MAX(tblJobProgramaPlusFicha.referenciaCliente) "
                + "                         AS referenciaCliente,         "
                + "       MAX(tblJobProgramaPlusFicha.nombreReferencia)   "
                + "                          AS nombreReferencia,         "
                + "          tblJobEscalaDetalle.nombreItem,              "
                + "          tblJobProgramaPlusFicha.idOperacion          "
                + "   FROM   tblJobProgramaPlusFicha                      "
                + "   INNER JOIN tblJobOperacion                          "
                + "   ON tblJobProgramaPlusFicha.idOperacion      =       "
                + "                 tblJobOperacion.idOperacion           "
                + "   INNER JOIN tblJobEscalaDetalle                      "
                + "   ON tblJobProgramaPlusFicha.idEscala         =       "
                + "                tblJobEscalaDetalle.idEscala           "
                + "   AND tblJobProgramaPlusFicha.vrEscala        =       "
                + "                    tblJobEscalaDetalle.item           "
                + "   WHERE   tblJobProgramaPlusFicha.idOperacion =       "
                + getIdOperacion() + "                                    "
                + "   AND tblJobProgramaPlusFicha.idEscala                "
                + "                    IN (600, 710, 910, 1000,1100)      "
                + "   GROUP BY tblJobProgramaPlusFicha.idOrden,           "
                + "            tblJobProgramaPlusFicha.idFicha,           "
                + "            tblJobProgramaPlusFicha.idOperacion,       "
                + "            tblJobEscalaDetalle.nombreItem)            "
                + "                                  AS tmpFIC            "
                + "   ON tblDctosOrdenes.idFicha  =                       "
                + "                             tmpFIC.idFicha            "
                + "   AND tblDctosOrdenes.idOrden =                       "
                + "                             tmpFIC.idOrden  	  "
                + "   INNER JOIN tblDctosOrdenesDetalle                   "
                + "   ON tblDctosOrdenesDetalle.idLocal     =             "
                + "                          tblDctosOrdenes.idLocal      "
                + "   AND   tblDctosOrdenesDetalle.idTipoOrden =          "
                + "                      tblDctosOrdenes.idTipoOrden      "
                + "   AND   tblDctosOrdenesDetalle.idOrden     =          "
                + "                        tblDctosOrdenes.idOrden        "
                + "   INNER JOIN                                          "
                + "    ( SELECT tblJobProgramaPlusFicha.idLocal           "
                + "          ,tblJobProgramaPlusFicha.idTipoOrden         "
                + "          ,tblJobProgramaPlusFicha.idOrden             "
                + "          ,tblJobProgramaPlusFicha.itemPadre           "
                + "          ,tblJobProgramaPlusFicha.idOperacion         "
                + "          ,tblJobProgramaPlusFicha.fechaPrograma       "
                + "          ,tblJobProgramaPlusFicha.estadoPrograma      "
                + "          ,tblJobProgramaPlusFicha.idOrdenPrograma     "
                + "      FROM tblJobProgramaPlusFicha                     "
                + "      WHERE tblJobProgramaPlusFicha.idOperacion =      "
                + getIdOperacion() + "                                    "
                + "      GROUP BY tblJobProgramaPlusFicha.idLocal         "
                + "          ,tblJobProgramaPlusFicha.idTipoOrden         "
                + "          ,tblJobProgramaPlusFicha.idOrden             "
                + "          ,tblJobProgramaPlusFicha.itemPadre           "
                + "          ,tblJobProgramaPlusFicha.idOperacion         "
                + "          ,tblJobProgramaPlusFicha.fechaPrograma       "
                + "          ,tblJobProgramaPlusFicha.estadoPrograma      "
                + "          ,tblJobProgramaPlusFicha.idOrdenPrograma)    "
                + "                                         AS tmpPPL     "
                + "      ON    tblDctosOrdenesDetalle.idLocal     =       "
                + "                                     tmpPPL.idLocal    "
                + "      AND   tblDctosOrdenesDetalle.idTipoOrden =       "
                + "                                 tmpPPL.idTipoOrden    "
                + "      AND   tblDctosOrdenesDetalle.idOrden     =       "
                + "                                     tmpPPL.idOrden    "
                + "      AND   tblDctosOrdenesDetalle.itemPadre     =     "
                + "                                   tmpPPL.itemPadre    "
                + "    WHERE tblDctosOrdenes.idLocal            =         "
                + getIdLocal() + "                                        "
                + "   AND   tblDctosOrdenes.idTipoOrden         =         "
                + getIdTipoOrden() + "                                    "
                + " AND   tblDctosOrdenes.numeroOrden        > 0          "
                + " AND tblDctosOrdenesDetalle.idEstadoRefOriginal != 9   "
                + " AND tmpPPL.idOperacion                    =           "
                + getIdOperacion() + " )                                  "
                + "                                      AS tmpPED )      "
                + "                                      AS tmpPEN )      "
                + "                                        AS tmpRES      "
                + "      WHERE tmpRES.pesoTerminadoActual <               "
                + "                    ( tmpRES.pesoTerminadoAnterior *   "
                + xPorcentajeAdicion + " )                                "
                + "     AND tmpRES.fechaEntrega > '20170101'            "
                + "     ORDER BY tmpRES.numeroOrden";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaPluFicha fachadaBean;

            //
            while (rs.next()) {

                //
                fachadaBean = new FachadaPluFicha();

                //
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setIdOrden(rs.getInt("idOrden"));
                fachadaBean.setItemPadre(rs.getInt("itemPadre"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setNumeroOrden(rs.getInt("numeroOrden"));
                fachadaBean.setIdFicha(rs.getInt("idFicha"));
                fachadaBean.setIdLog(rs.getInt("idLog"));
                fachadaBean.setFechaEntrega(rs.getString("fechaEntrega"));
                fachadaBean.setReferencia(rs.getString("referencia"));
                fachadaBean.setNombreOperacion(rs.getString("nombreOperacion"));
                fachadaBean.setIdColor(rs.getString("idColor").trim());
                fachadaBean.setReferenciaCliente(
                        rs.getString("referenciaCliente"));
                fachadaBean.setNombreItem(rs.getString("nombreItem"));
                fachadaBean.setNombreTercero(rs.getString("nombreTercero"));
                fachadaBean.setIdOrdenPrograma(rs.getInt("idOrdenPrograma"));

                //
                fachadaBean.setCantidadPendiente(
                        rs.getDouble("cantidadTerminadaAnterior")
                        - rs.getDouble("cantidadTerminadaActual"));
                fachadaBean.setPesoPendiente(
                        rs.getDouble("pesoTerminadoAnterior")
                        - rs.getDouble("pesoTerminadoActual"));

                //
                //
                fachadaBean.setPesoPerdido(0);
                fachadaBean.setCantidad(0);
                fachadaBean.setPesoTerminado(0);
                fachadaBean.setCantidadTerminada(0);
                fachadaBean.setCantidadPerdida(0);
                fachadaBean.setPorcentajeTerminado(0);

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

    // listaOTProgramaTouchFCH
    public FachadaPluFicha listaOTProgramaTouchFCH() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        //
        FachadaPluFicha fachadaBean = new FachadaPluFicha();

        Connection connection = null;

        //
        String selectStr =
                "  SELECT tmpPEN.idLocal,                                 "
                + "         tmpPEN.idTipoOrden,                           "
                + "         tmpPEN.idOrden,                               "
                + "         tmpPEN.itemPadre,                             "
                + "         tmpPEN.idCliente,                             "
                + "         tmpPEN.numeroOrden,                           "
                + "         tmpPEN.idFicha,                               "
                + "         tmpPEN.idLog,                                 "
                + "         tmpPEN.fechaEntrega,                          "
                + "         tmpPEN.referencia,                            "
                + "         tmpPEN.nombreOperacion,                       "
                + "         tmpPEN.idColor,                               "
                + "         tmpPEN.referenciaCliente,                     "
                + "         tmpPEN.nombreReferencia,                      "
                + "         tmpPEN.nombreItem,                            "
                + "         tmpPEN.nombreTercero,                         "
                + "         tmpPEN.idOperacionAnterior,                   "
                + "         tmpPEN.idOperacion,                           "
                + "         CASE                                          "
                + "         WHEN   tmpPEN.idOperacionAnterior = 1         "
                + "                        THEN tmpPEN.pesoPedido         "
                + "         ELSE   tmpPEN.pesoTerminadoAnterior           "
                + "         END AS pesoTerminadoAnterior,                 "
                + "         CASE                                          "
                + "         WHEN   tmpPEN.idOperacionAnterior = 1         "
                + "                    THEN tmpPEN.cantidadPedida         "
                + "         ELSE   tmpPEN.cantidadTerminadaAnterior       "
                + "         END AS cantidadTerminadaAnterior,             "
                + "         tmpPEN.pesoTerminadoActual,                   "
                + "         tmpPEN.cantidadTerminadaActual,               "
                + "         tmpPEN.idOrdenPrograma                        "
                + "  FROM (                                               "
                + "  SELECT tmpPED.*,                                     "
                + "  ( SELECT SUM(tblDctosOrdenesDetalle.pesoTerminado)   "
                + "  FROM tblDctosOrdenesDetalle                          "
                + "  WHERE tblDctosOrdenesDetalle.idLocalOrigen     =     "
                + "                                     tmpPED.idLocal    "
                + "  AND   tblDctosOrdenesDetalle.idTipoOrdenOrigen =     "
                + "                                 tmpPED.idTipoOrden    "
                + "  AND   tblDctosOrdenesDetalle.idOrdenOrigen     =     "
                + "                                     tmpPED.idOrden    "
                + "  AND   tblDctosOrdenesDetalle.itemPadre         =     "
                + "                                     tmpPED.itemPadre  "
                + "  AND   tblDctosOrdenesDetalle.idOperacion       =     "
                + "                          tmpPED.idOperacionAnterior ) "
                + "                         AS pesoTerminadoAnterior,     "
                + "  ( SELECT                                             "
                + "      SUM(tblDctosOrdenesDetalle.cantidadTerminada)    "
                + "  FROM tblDctosOrdenesDetalle                          "
                + "  WHERE tblDctosOrdenesDetalle.idLocalOrigen     =     "
                + "                                     tmpPED.idLocal    "
                + "  AND   tblDctosOrdenesDetalle.idTipoOrdenOrigen =     "
                + "                                 tmpPED.idTipoOrden    "
                + "  AND   tblDctosOrdenesDetalle.idOrdenOrigen     =     "
                + "                                     tmpPED.idOrden    "
                + "  AND   tblDctosOrdenesDetalle.itemPadre         =     "
                + "                                     tmpPED.itemPadre  "
                + "  AND   tblDctosOrdenesDetalle.idOperacion       =     "
                + "                          tmpPED.idOperacionAnterior ) "
                + "                      AS cantidadTerminadaAnterior,    "
                + "  ( SELECT SUM(tblDctosOrdenesProgreso.pesoTerminado)  "
                + "  FROM tblDctosOrdenesProgreso                         "
                + "  WHERE tblDctosOrdenesProgreso.idLocal           =    "
                + "                                      tmpPED.idLocal   "
                + "  AND   tblDctosOrdenesProgreso.idTipoOrden       =    "
                + "                                  tmpPED.idTipoOrden   "
                + "  AND   tblDctosOrdenesProgreso.idOrden           =    "
                + "                                      tmpPED.idOrden   "
                + "  AND   tblDctosOrdenesProgreso.itemPadre         =    "
                + "                                    tmpPED.itemPadre   "
                + "  AND   tblDctosOrdenesProgreso.idOperacion       =    "
                + "                                  tmpPED.idOperacion ) "
                + "                              AS pesoTerminadoActual,  "
                + "  ( SELECT                                             "
                + "      SUM(tblDctosOrdenesProgreso.cantidadTerminada)   "
                + "  FROM tblDctosOrdenesProgreso                         "
                + "  WHERE tblDctosOrdenesProgreso.idLocal          =     "
                + "                                      tmpPED.idLocal   "
                + "  AND   tblDctosOrdenesProgreso.idTipoOrden      =     "
                + "                                  tmpPED.idTipoOrden   "
                + "  AND   tblDctosOrdenesProgreso.idOrden          =     "
                + "                                      tmpPED.idOrden   "
                + "  AND   tblDctosOrdenesProgreso.itemPadre        =     "
                + "                                    tmpPED.itemPadre   "
                + "  AND   tblDctosOrdenesProgreso.idOperacion       =    "
                + "                                 tmpPED.idOperacion )  "
                + "                         AS cantidadTerminadaActual    "
                + "  FROM (                                               "
                + "   SELECT tblDctosOrdenes.idLocal,                     "
                + "          tblDctosOrdenes.idTipoOrden,                 "
                + "          tblDctosOrdenes.idOrden,                     "
                + "          tblDctosOrdenesDetalle.itemPadre,            "
                + "          tblDctosOrdenes.idCliente,                   "
                + "          tblDctosOrdenes.numeroOrden,                 "
                + "          tblDctosOrdenes.idFicha,                     "
                + "          tblDctosOrdenes.idLog,                       "
                + "          tblDctosOrdenesDetalle.fechaEntrega,         "
                + "          tblDctosOrdenesDetalle.cantidad              "
                + "                            AS cantidadPedida,         "
                + "          tblDctosOrdenesDetalle.pesoPedido,           "
                + "          tmpFIC.referencia,                           "
                + "          tmpFIC.nombreOperacion,                      "
                + "          tmpFIC.idColor,                              "
                + "          tmpFIC.referenciaCliente,                    "
                + "          tmpFIC.nombreReferencia,                     "
                + "          tmpFIC.nombreItem,                           "
                + "          tblTerceros.nombreTercero,                   "
                + "          1  AS idOperacionAnterior,                   "
                + "          tmpFIC.idOperacion,                          "
                + "          tmpPPL.idOrdenPrograma                       "
                + "    FROM   tblDctosOrdenes                             "
                + "    INNER JOIN tblTerceros                             "
                + "    ON  tblTerceros.idCliente =                        "
                + "                        tblDctosOrdenes.idCliente      "
                + "    INNER JOIN (                                       "
                + "    SELECT tblPlusFicha.idFicha,                       "
                + "           MAX(tblPlusFicha.referencia)                "
                + "                         AS referencia,                "
                + "           MAX(tblJobOperacion.nombreOperacion)        "
                + "                            AS nombreOperacion,        "
                + "           MAX(tblJobOperacion.idColor)                "
                + "                                    AS idColor,        "
                + "           MAX(tblPlusFicha.referenciaCliente)         "
                + "                          AS referenciaCliente,        "
                + "           MAX(tblPlusFicha.nombreReferencia)          "
                + "                           AS nombreReferencia,        "
                + "           tblJobEscalaDetalle.nombreItem,             "
                + "           tblPlusFicha.idOperacion                    "
                + "    FROM   tblPlusFicha                                "
                + "    INNER JOIN tblJobOperacion                         "
                + "    ON tblPlusFicha.idOperacion      =                 "
                + "                  tblJobOperacion.idOperacion          "
                + "    INNER JOIN tblJobEscalaDetalle                     "
                + "    ON tblPlusFicha.idEscala         =                 "
                + "                 tblJobEscalaDetalle.idEscala          "
                + "    AND tblPlusFicha.vrEscala        =                 "
                + "                     tblJobEscalaDetalle.item          "
                + "    WHERE   tblPlusFicha.idOperacion =                 "
                + getIdOperacion() + "                                    "
                + "    AND tblPlusFicha.idEscala                          "
                + "                     IN (600, 710, 910, 1000,1100)     "
                + "    GROUP BY tblPlusFicha.idFicha,                     "
                + "             tblPlusFicha.idOperacion,                 "
                + "             tblJobEscalaDetalle.nombreItem)           "
                + "                                   AS tmpFIC           "
                + "    ON tblDctosOrdenes.idFicha  =                      "
                + "                              tmpFIC.idFicha           "
                + "    INNER JOIN tblDctosOrdenesDetalle                  "
                + "    ON tblDctosOrdenesDetalle.idLocal     =            "
                + "                           tblDctosOrdenes.idLocal     "
                + "    AND   tblDctosOrdenesDetalle.idTipoOrden =         "
                + "                       tblDctosOrdenes.idTipoOrden     "
                + "    AND   tblDctosOrdenesDetalle.idOrden     =         "
                + "                         tblDctosOrdenes.idOrden       "
                + "    INNER JOIN                                         "
                + "     ( SELECT tblJobProgramaPlusFicha.idLocal          "
                + "           ,tblJobProgramaPlusFicha.idTipoOrden        "
                + "           ,tblJobProgramaPlusFicha.idOrden            "
                + "           ,tblJobProgramaPlusFicha.itemPadre          "
                + "           ,tblJobProgramaPlusFicha.idOperacion        "
                + "           ,tblJobProgramaPlusFicha.fechaPrograma      "
                + "           ,tblJobProgramaPlusFicha.estadoPrograma     "
                + "           ,tblJobProgramaPlusFicha.idOrdenPrograma    "
                + "       FROM tblJobProgramaPlusFicha                    "
                + "       GROUP BY tblJobProgramaPlusFicha.idLocal        "
                + "           ,tblJobProgramaPlusFicha.idTipoOrden        "
                + "           ,tblJobProgramaPlusFicha.idOrden            "
                + "           ,tblJobProgramaPlusFicha.itemPadre          "
                + "           ,tblJobProgramaPlusFicha.idOperacion        "
                + "           ,tblJobProgramaPlusFicha.fechaPrograma      "
                + "           ,tblJobProgramaPlusFicha.estadoPrograma     "
                + "           ,tblJobProgramaPlusFicha.idOrdenPrograma)   "
                + "                                          AS tmpPPL    "
                + "       ON    tblDctosOrdenesDetalle.idLocal     =      "
                + "                                      tmpPPL.idLocal   "
                + "       AND   tblDctosOrdenesDetalle.idTipoOrden =      "
                + "                                  tmpPPL.idTipoOrden   "
                + "       AND   tblDctosOrdenesDetalle.idorden     =      "
                + "                                      tmpPPL.idorden   "
                + "       AND   tblDctosOrdenesDetalle.itemPadre     =    "
                + "                                    tmpPPL.itemPadre   "
                + "    WHERE tblDctosOrdenes.idLocal            =         "
                + getIdLocal() + "                                        "
                + "    AND   tblDctosOrdenes.idTipoOrden        =         "
                + getIdTipoOrden() + "                                    "
                + "    AND   tblDctosOrdenes.numeroOrden        > 0       "
                + "   AND tmpPPL.fechaPrograma                  =        '"
                + getFechaProgramaSqlServer() + "'                        "
                + "   AND tmpPPL.idOperacion                    =         "
                + getIdOperacion() + " )                                  "
                + "                                       AS tmpPED )     "
                + "                                       AS tmpPEN       "
                + " WHERE tmpPEN.numeroOrden                    =         "
                + getNumeroOrden() + "                                    "
                + "   AND tmpPEN.itemPadre                      =         "
                + getItemPadre();

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
                fachadaBean.setItemPadre(rs.getInt("itemPadre"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setNumeroOrden(rs.getInt("numeroOrden"));
                fachadaBean.setIdFicha(rs.getInt("idFicha"));
                fachadaBean.setIdLog(rs.getInt("idLog"));
                fachadaBean.setFechaEntrega(rs.getString("fechaEntrega"));
                fachadaBean.setReferencia(rs.getString("referencia"));
                fachadaBean.setNombreOperacion(rs.getString("nombreOperacion"));
                fachadaBean.setIdColor(rs.getString("idColor").trim());
                fachadaBean.setReferenciaCliente(
                        rs.getString("referenciaCliente"));
                fachadaBean.setNombreItem(rs.getString("nombreItem"));
                fachadaBean.setNombreTercero(rs.getString("nombreTercero"));
                fachadaBean.setIdOrdenPrograma(rs.getInt("idOrdenPrograma"));

                //
                fachadaBean.setCantidadPendiente(
                        rs.getDouble("cantidadTerminadaAnterior")
                        - rs.getDouble("cantidadTerminadaActual"));
                fachadaBean.setPesoPendiente(
                        rs.getDouble("pesoTerminadoAnterior")
                        - rs.getDouble("pesoTerminadoActual"));

                //
                //
                fachadaBean.setPesoPerdido(0);
                fachadaBean.setCantidad(0);
                fachadaBean.setPesoTerminado(0);
                fachadaBean.setCantidadTerminada(0);
                fachadaBean.setCantidadPerdida(0);
                fachadaBean.setPorcentajeTerminado(0);


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

    // listaOTProgramaFecha
    public Vector listaOTProgramaFecha() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        //
        String selectStr =
                "   SELECT   tmpPPL.idOrdenPrograma,                      "
                + "          tblDctosOrdenes.numeroOrden,                 "
                + "          tmpPPL.itemPadre,                            "
                + "          tblDctosOrdenes.idCliente,                   "
                + "          tblDctosOrdenesDetalle.fechaEntrega,         "
                + "          tblTerceros.nombreTercero,                   "
                + "          tblDctosOrdenesDetalle.referenciaCliente,    "
                + "          tblJobOperacion.nombreOperacion,             "
                + "          tmpMAQ.nombreItem,                           "
                + "          tmpFIC.referencia,                           "
                + "          tmpPPL.cantidadPedida,                       "
                + "          tmpPPL.pesoPedido                            "
                + "   FROM   tblDctosOrdenesDetalle                       "
                + "   INNER JOIN tblDctosOrdenes                          "
                + "   ON    tblDctosOrdenesDetalle.idLocal     =          "
                + "                        tblDctosOrdenes.idLocal        "
                + "   AND   tblDctosOrdenesDetalle.idTipoOrden =          "
                + "                   tblDctosOrdenes.idTipoOrden         "
                + "   AND   tblDctosOrdenesDetalle.idorden     =          "
                + "                        tblDctosOrdenes.idorden        "
                + "   INNER JOIN                                          "
                + "    ( SELECT tblJobProgramaPlusFicha.idLocal           "
                + "            ,tblJobProgramaPlusFicha.idTipoOrden       "
                + "            ,tblJobProgramaPlusFicha.idOrden           "
                + "            ,tblJobProgramaPlusFicha.itemPadre         "
                + "            ,tblJobProgramaPlusFicha.idOperacion       "
                + "            ,tblJobProgramaPlusFicha.fechaPrograma     "
                + "            ,tblJobProgramaPlusFicha.estadoPrograma    "
                + "            ,tblJobProgramaPlusFicha.idOrdenPrograma   "
                + "            ,tblJobProgramaPlusFicha.cantidadPedida    "
                + "            ,tblJobProgramaPlusFicha.pesoPedido        "
                + "     FROM tblJobProgramaPlusFicha                      "
                + "     WHERE tblJobProgramaPlusFicha.fechaPrograma   =  '"
                + getFechaProgramaSqlServer() + "'                        "
                + "     GROUP BY tblJobProgramaPlusFicha.idLocal          "
                + "              ,tblJobProgramaPlusFicha.idTipoOrden     "
                + "              ,tblJobProgramaPlusFicha.idOrden         "
                + "              ,tblJobProgramaPlusFicha.itemPadre       "
                + "              ,tblJobProgramaPlusFicha.idOperacion     "
                + "              ,tblJobProgramaPlusFicha.fechaPrograma   "
                + "            ,tblJobProgramaPlusFicha.estadoPrograma    "
                + "            ,tblJobProgramaPlusFicha.idOrdenPrograma   "
                + "            ,tblJobProgramaPlusFicha.cantidadPedida    "
                + "           ,tblJobProgramaPlusFicha.pesoPedido)        "
                + "                                           AS tmpPPL   "
                + "   ON    tblDctosOrdenesDetalle.idLocal     =          "
                + "                                      tmpPPL.idLocal   "
                + "   AND   tblDctosOrdenesDetalle.idTipoOrden =          "
                + "                                  tmpPPL.idTipoOrden   "
                + "   AND   tblDctosOrdenesDetalle.idorden     =          "
                + "                                     tmpPPL.idorden    "
                + "   AND   tblDctosOrdenesDetalle.itemPadre     =        "
                + "                                   tmpPPL.itemPadre    "
                + "   INNER JOIN tblTerceros                              "
                + "   ON tblTerceros.idCliente                 =          "
                + "                      tblDctosOrdenes.idCliente        "
                + "   INNER JOIN ( SELECT tblPlusFicha.idFicha            "
                + "                       ,MAX(tblPlusFicha.referencia)   "
                + "                                       AS referencia   "
                + "                FROM tblPlusFicha                      "
                + "                GROUP BY tblPlusFicha.idFicha )        "
                + "                                          AS tmpFIC    "
                + "  ON tmpFIC.idFicha = tblDctosOrdenes.idFicha          "
                + "   INNER JOIN                                          "
                + "   (SELECT tblJobProgramaPlusFicha.idLocal             "
                + "          ,tblJobProgramaPlusFicha.idTipoOrden         "
                + "          ,tblJobProgramaPlusFicha.idOrden             "
                + "          ,tblJobProgramaPlusFicha.idOperacion         "
                + "          ,tblJobProgramaPlusFicha.fechaPrograma       "
                + "          ,tblJobEscalaDetalle.nombreItem              "
                + "          ,tblJobOperacion.nombreOperacion             "
                + "   FROM tblJobProgramaPlusFicha                        "
                + "   INNER JOIN tblJobEscalaDetalle                      "
                + "   ON tblJobEscalaDetalle.idEscala =                   "
                + "             tblJobProgramaPlusFicha.idEscala          "
                + "   AND tblJobProgramaPlusFicha.vrEscala =              "
                + "              tblJobEscalaDetalle.item                 "
                + "   INNER JOIN tblJobOperacion                          "
                + "   ON tblJobOperacion.idOperacion =                    "
                + "              tblJobProgramaPlusFicha.idOperacion      "
                + "   AND tblJobProgramaPlusFicha.idEscala                "
                + "             IN (600,710,910,1000,1100))               "
                + "                            AS tmpMAQ                  "
                + "   ON tmpMAQ.idLocal  = tblDctosOrdenes.idLocal        "
                + "   AND tmpMAQ.idTipoOrden  =                           "
                + "                        tblDctosOrdenes.idTipoOrden    "
                + "   AND tmpMAQ.idOrden     = tblDctosOrdenes.idOrden    "
                + "   AND tmpMAQ.idOperacion = tmpPPL.idOperacion         "
                + "   AND tmpMAQ.fechaPrograma= tmpPPL.fechaPrograma      "
                + "                     INNER JOIN tblJobOperacion        "
                + "   ON tblJobOperacion.idOperacion           =          "
                + "                                tmpMAQ.idOperacion     "
                + "   WHERE tblTerceros.idTipoTercero                 =1  "
                + "   AND tblDctosOrdenesDetalle.idEstadoRefOriginal !=9  "
                + "   AND tmpPPL.fechaPrograma                        =  '"
                + getFechaProgramaSqlServer() + "'                        "
                + "   ORDER BY tblJobOperacion.nombreOperacion,           "
                + "            tmpMAQ.nombreItem,                         "
                + "            tmpPPL.idOrdenPrograma                     ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaPluFicha fachadaBean;

            //
            while (rs.next()) {

                //
                fachadaBean = new FachadaPluFicha();

                //
                fachadaBean.setIdOrdenPrograma(rs.getInt("idOrdenPrograma"));
                fachadaBean.setNumeroOrden(rs.getInt("numeroOrden"));
                fachadaBean.setItemPadre(rs.getInt("itemPadre"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setFechaEntrega(rs.getString("fechaEntrega"));
                fachadaBean.setNombreTercero(rs.getString("nombreTercero"));
                fachadaBean.setReferenciaCliente(
                        rs.getString("referenciaCliente"));
                fachadaBean.setNombreOperacion(rs.getString("nombreOperacion"));
                fachadaBean.setNombreItem(rs.getString("nombreItem"));
                fachadaBean.setReferencia(rs.getString("referencia"));
                fachadaBean.setCantidadPedida(rs.getDouble("cantidadPedida"));
                fachadaBean.setPesoPedido(rs.getDouble("pesoPedido"));

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

    // listaOTEstadoPedido
    public Vector listaOTEstadoPedido() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        //
        String selectStr =
                "   SELECT tmpDEF.idLocal,                           "
                + "          tmpDEF.idTipoOrden,                     "
                + "          tmpDEF.idOrden,                         "
                + "          tmpDEF.idOperacion,                     "
                + "          tmpDEF.itemPadre,                       "
                + "          SUM(tmpDEF.cantidadPerdida)             "
                + "                  AS cantidadPerdida,             "
                + "          SUM(tmpDEF.cantidadTerminada)           "
                + "                  AS cantidadTerminada,           "
                + "          SUM(tmpDEF.pesoPerdido)                 "
                + "                        AS pesoPerdido,           "
                + "          SUM(tmpDEF.pesoTerminado)               "
                + "                      AS pesoTerminado,           "
                + "          tblJobOperacion.nombreOperacion         "
                + "   FROM (                                         "
                + "   SELECT                                         "
                + getIdLocal() + " AS idLocal,                       "
                + getIdTipoOrden() + " AS idTipoOrden,               "
                + getIdOrden() + " AS idOrden,                       "
                + "          tblJobOperacion.idOperacion,            "
                + getItemPadre() + " AS itemPadre,                   "
                + "          0.0 AS cantidadPerdida,                 "
                + "          0.0 AS cantidadTerminada,               "
                + "          0.0 AS pesoPerdido,                     "
                + "          0.0 AS pesoTerminado                    "
                + "   FROM tblJobOperacion                           "
                + "   UNION                                          "
                + "   SELECT tblDctosOrdenesProgreso.idLocal         "
                + "         ,tblDctosOrdenesProgreso.idTipoOrden     "
                + "         ,tblDctosOrdenesProgreso.idOrden         "
                + "         ,tblDctosOrdenesProgreso.idOperacion     "
                + "         ,tblDctosOrdenesProgreso.itemPadre       "
                + "         ,SUM(cantidadPerdida)                    "
                + "                 AS cantidadPerdida               "
                + "         ,SUM(cantidadTerminada)                  "
                + "               AS cantidadTerminada               "
                + "         ,SUM(pesoPerdido)                        "
                + "                     AS pesoPerdido               "
                + "         ,SUM(pesoTerminado)                      "
                + "                   AS pesoTerminado               "
                + "   FROM tblDctosOrdenesProgreso                   "
                + "   WHERE tblDctosOrdenesProgreso.idLocal       =  "
                + getIdLocal() + "                                   "
                + "   AND   tblDctosOrdenesProgreso.idTipoOrden   =  "
                + getIdTipoOrden() + "                               "
                + "   AND   tblDctosOrdenesProgreso.idOrden       =  "
                + getIdOrden() + "                                   "
                + "   AND   tblDctosOrdenesProgreso.itemPadre     =  "
                + getItemPadre() + "                                 "
                + "   AND   tblDctosOrdenesProgreso.idControlTipo!=2 "
                + "   GROUP BY tblDctosOrdenesProgreso.idLocal       "
                + "         ,tblDctosOrdenesProgreso.idTipoOrden     "
                + "         ,tblDctosOrdenesProgreso.idOrden         "
                + "         ,tblDctosOrdenesProgreso.idOperacion     "
                + "         ,tblDctosOrdenesProgreso.itemPadre )     "
                + "                                    AS tmpDEF     "
                + "   INNER JOIN tblJobOperacion                     "
                + "   ON tblJobOperacion.idOperacion   =             "
                + "                           tmpDEF.idOperacion     "
                + "   WHERE tblJobOperacion.idOperacion              "
                + "                         IN (2,3,4,5,6,9,999)     "
                + "   GROUP BY tmpDEF.idLocal,                       "
                + "          tmpDEF.idTipoOrden,                     "
                + "          tmpDEF.idOrden,                         "
                + "          tmpDEF.idOperacion,                     "
                + "          tmpDEF.itemPadre,                       "
                + "          tblJobOperacion.nombreOperacion         ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaPluFicha fachadaBean;

            while (rs.next()) {

                //
                fachadaBean = new FachadaPluFicha();

                //
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setIdOrden(rs.getInt("idOrden"));
                fachadaBean.setItemPadre(rs.getInt("itemPadre"));
                fachadaBean.setNombreOperacion(rs.getString("nombreOperacion"));
                fachadaBean.setCantidadTerminada(rs.getDouble("cantidadTerminada"));
                fachadaBean.setPesoTerminado(rs.getDouble("pesoTerminado"));
                fachadaBean.setPesoPerdido(rs.getDouble("pesoPerdido"));
                fachadaBean.setCantidadPerdida(rs.getDouble("cantidadPerdida"));

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

    // listaOTAllOperacion
    public Vector listaOTAllOperacion() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        //
        String selectStr =
                "   SELECT tblDctosOrdenesDetalle.idLocal                "
                + "         ,tblDctosOrdenesDetalle.idTipoOrden          "
                + "         ,tblDctosOrdenesDetalle.idOrden              "
                + "         ,tblDctosOrdenesDetalle.item                 "
                + "         ,tblJobOperacion.idOperacion                 "
                + "         ,tblJobOperacion.nombreOperacion             "
                + "         ,tblDctosOrdenesDetalle.cantidad             "
                + "                                 AS cantidadPedida    "
                + "         ,tmpPRG.pesoPerdido                          "
                + "         ,tmpPRG.cantidadPerdida                      "
                + "         ,tmpPED.nombreTercero                        "
                + "         ,tmpPED.referenciaCliente                    "
                + "         ,tmpPED.nombreReferencia                     "
                + "         ,tblDctosOrdenes.numeroOrden                 "
                + "         ,tmpPRG.cantidadTerminada                    "
                + "         ,tblDctosOrdenesDetalle.cantidadEntregada    "
                + "         ,tblDctosOrdenesDetalle.cantidadFacturada    "
                + "         ,tblDctosOrdenesDetalle.pesoEntregado        "
                + "         ,tblDctosOrdenesDetalle.pesoFacturado        "
                + "         ,tmpPRG.pesoTerminado                        "
                + "   FROM tblDctosOrdenesDetalle                        "
                + "   INNER JOIN tblDctosOrdenes                         "
                + "   ON tblDctosOrdenesDetalle.idLocal      =           "
                + "                      tblDctosOrdenes.idLocal         "
                + "   AND tblDctosOrdenesDetalle.idTipoOrden =           "
                + "                  tblDctosOrdenes.idTipoOrden         "
                + "   AND tblDctosOrdenesDetalle.idOrden     =           "
                + "                      tblDctosOrdenes.idOrden         "
                + "   INNER JOIN (                                       "
                + "   SELECT tblDctosOrdenesProgreso.idLocal             "
                + "         ,tblDctosOrdenesProgreso.idTipoOrden         "
                + "         ,tblDctosOrdenesProgreso.idOrden             "
                + "         ,tblDctosOrdenesProgreso.idOperacion         "
                + "         ,tblDctosOrdenesProgreso.itemPadre           "
                + "         ,SUM(cantidadPerdida)                        "
                + "                 AS cantidadPerdida                   "
                + "         ,SUM(cantidadTerminada)                      "
                + "               AS cantidadTerminada                   "
                + "         ,SUM(pesoPerdido)                            "
                + "                     AS pesoPerdido                   "
                + "         ,SUM(pesoTerminado)                          "
                + "                   AS pesoTerminado                   "
                + "   FROM tblDctosOrdenesProgreso                       "
                + "   GROUP BY tblDctosOrdenesProgreso.idLocal           "
                + "         ,tblDctosOrdenesProgreso.idTipoOrden         "
                + "         ,tblDctosOrdenesProgreso.idOrden             "
                + "         ,tblDctosOrdenesProgreso.idOperacion         "
                + "         ,tblDctosOrdenesProgreso.itemPadre)          "
                + "                                        AS tmpPRG     "
                + "   ON tblDctosOrdenesDetalle.idLocal      =           "
                + "                               tmpPRG.idLocal         "
                + "   AND tblDctosOrdenesDetalle.idTipoOrden =           "
                + "                           tmpPRG.idTipoOrden         "
                + "   AND tblDctosOrdenesDetalle.idOrden     =           "
                + "                               tmpPRG.idOrden         "
                + "   AND tblDctosOrdenesDetalle.item        =           "
                + "                             tmpPRG.itemPadre         "
                + "   INNER JOIN tblJobOperacion                         "
                + "   ON tblJobOperacion.idOperacion         =           "
                + "                            tmpPRG.idOperacion        "
                + "   INNER JOIN                                         "
                + "   (SELECT tblPlusFicha.idFicha,                      "
                + "              tblPlusFicha.referenciaCliente,         "
                + "              MAX(tblPlusFicha.nombreReferencia)      "
                + "                           AS nombreReferencia,       "
                + "              MAX(tblTerceros.nombreTercero)          "
                + "                               AS nombreTercero       "
                + "       FROM  tblDctosOrdenes                          "
                + "       INNER JOIN tblPlusFicha                        "
                + "       ON tblDctosOrdenes.idCliente =                 "
                + "                         tblPlusFicha.idCliente       "
                + "       AND tblDctosOrdenes.idFicha  =                 "
                + "                           tblPlusFicha.idFicha       "
                + "       INNER JOIN tblTerceros                         "
                + "       ON tblDctosOrdenes.idCliente =                 "
                + "                          tblTerceros.idCliente       "
                + "       WHERE tblTerceros.idTipoTercero = 1            "
                + "       GROUP BY tblPlusFicha.idFicha,                 "
                + "                tblPlusFicha.referenciaCliente)       "
                + "                                      AS tmpPED       "
                + "   ON tmpPED.idFicha                      =           "
                + "                        tblDctosOrdenes.idFicha       "
                + " WHERE tblDctosOrdenes.idLocal           =            "
                + getIdLocal() + "                                       "
                + " AND   tblDctosOrdenes.idTipoOrden       =            "
                + getIdTipoOrden() + "                                   "
                + "   AND  tmpPRG.cantidadTerminada >                    "
                + "     ( tblDctosOrdenesDetalle.cantidadEntregada +     "
                + "       tblDctosOrdenesDetalle.cantidadFacturada  )    "
                + "   AND tblJobOperacion.idOperacion IN (5,6,999)       "
                + "   UNION                                              "
                + "   SELECT tblDctosOrdenesDetalle.idLocal              "
                + "         ,tblDctosOrdenesDetalle.idTipoOrden          "
                + "         ,tblDctosOrdenesDetalle.idOrden              "
                + "         ,tblDctosOrdenesDetalle.item                 "
                + "         ,tblJobOperacion.idOperacion                 "
                + "         ,tblJobOperacion.nombreOperacion             "
                + "         ,tblDctosOrdenesDetalle.cantidad             "
                + "                                 AS cantidadPedida    "
                + "         ,tmpPRG.pesoPerdido                          "
                + "         ,tmpPRG.cantidadPerdida                      "
                + "         ,tmpPED.nombreTercero                        "
                + "         ,tmpPED.referenciaCliente                    "
                + "         ,tmpPED.nombreReferencia                     "
                + "         ,tblDctosOrdenes.numeroOrden                 "
                + "         ,tmpPRG.cantidadTerminada                    "
                + "         ,tblDctosOrdenesDetalle.cantidadEntregada    "
                + "         ,tblDctosOrdenesDetalle.cantidadFacturada    "
                + "         ,tblDctosOrdenesDetalle.pesoEntregado        "
                + "         ,tblDctosOrdenesDetalle.pesoFacturado        "
                + "         ,tmpPRG.pesoTerminado                        "
                + "   FROM tblDctosOrdenesDetalle                        "
                + "   INNER JOIN tblDctosOrdenes                         "
                + "   ON tblDctosOrdenesDetalle.idLocal      =           "
                + "                      tblDctosOrdenes.idLocal         "
                + "   AND tblDctosOrdenesDetalle.idTipoOrden =           "
                + "                  tblDctosOrdenes.idTipoOrden         "
                + "   AND tblDctosOrdenesDetalle.idOrden     =           "
                + "                      tblDctosOrdenes.idOrden         "
                + "   INNER JOIN (                                       "
                + "   SELECT tblDctosOrdenesProgreso.idLocal             "
                + "         ,tblDctosOrdenesProgreso.idTipoOrden         "
                + "         ,tblDctosOrdenesProgreso.idOrden             "
                + "         ,tblDctosOrdenesProgreso.idOperacion         "
                + "         ,tblDctosOrdenesProgreso.itemPadre           "
                + "         ,SUM(cantidadPerdida)                        "
                + "                 AS cantidadPerdida                   "
                + "         ,SUM(cantidadTerminada)                      "
                + "               AS cantidadTerminada                   "
                + "         ,SUM(pesoPerdido)                            "
                + "                     AS pesoPerdido                   "
                + "         ,SUM(pesoTerminado)                          "
                + "                   AS pesoTerminado                   "
                + "   FROM tblDctosOrdenesProgreso                       "
                + "   GROUP BY tblDctosOrdenesProgreso.idLocal           "
                + "         ,tblDctosOrdenesProgreso.idTipoOrden         "
                + "         ,tblDctosOrdenesProgreso.idOrden             "
                + "         ,tblDctosOrdenesProgreso.idOperacion         "
                + "         ,tblDctosOrdenesProgreso.itemPadre)          "
                + "                                        AS tmpPRG     "
                + "   ON tblDctosOrdenesDetalle.idLocal      =           "
                + "                               tmpPRG.idLocal         "
                + "   AND tblDctosOrdenesDetalle.idTipoOrden =           "
                + "                           tmpPRG.idTipoOrden         "
                + "   AND tblDctosOrdenesDetalle.idOrden     =           "
                + "                               tmpPRG.idOrden         "
                + "   AND tblDctosOrdenesDetalle.item        =           "
                + "                             tmpPRG.itemPadre         "
                + "   INNER JOIN tblJobOperacion                         "
                + "   ON tblJobOperacion.idOperacion         =           "
                + "                            tmpPRG.idOperacion        "
                + "   INNER JOIN                                         "
                + "   (SELECT tblPlusFicha.idFicha,                      "
                + "              tblPlusFicha.referenciaCliente,         "
                + "              MAX(tblPlusFicha.nombreReferencia)      "
                + "                           AS nombreReferencia,       "
                + "              MAX(tblTerceros.nombreTercero)          "
                + "                               AS nombreTercero       "
                + "       FROM  tblDctosOrdenes                          "
                + "       INNER JOIN tblPlusFicha                        "
                + "       ON tblDctosOrdenes.idCliente =                 "
                + "                         tblPlusFicha.idCliente       "
                + "       AND tblDctosOrdenes.idFicha  =                 "
                + "                           tblPlusFicha.idFicha       "
                + "       INNER JOIN tblTerceros                         "
                + "       ON tblDctosOrdenes.idCliente =                 "
                + "                          tblTerceros.idCliente       "
                + "       WHERE tblTerceros.idTipoTercero = 1            "
                + "       GROUP BY tblPlusFicha.idFicha,                 "
                + "                tblPlusFicha.referenciaCliente)       "
                + "                                      AS tmpPED       "
                + "   ON tmpPED.idFicha                      =           "
                + "                        tblDctosOrdenes.idFicha       "
                + " WHERE tblDctosOrdenes.idLocal           =            "
                + getIdLocal() + "                                       "
                + " AND   tblDctosOrdenes.idTipoOrden       =            "
                + getIdTipoOrden() + "                                   "
                + "   AND  tmpPRG.pesoTerminado >                        "
                + "     ( tblDctosOrdenesDetalle.pesoEntregado +         "
                + "         tblDctosOrdenesDetalle.pesoFacturado  )      "
                + "   AND tblJobOperacion.idOperacion IN (2,3,4)         "
                + "   ORDER BY 5,   10  ,                                "
                + "            tblDctosOrdenes.numeroOrden,              "
                + "            tblDctosOrdenesDetalle.item               ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaPluFicha fachadaBean;

            while (rs.next()) {

                //
                fachadaBean = new FachadaPluFicha();

                //
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setIdOrden(rs.getInt("idOrden"));
                fachadaBean.setItem(rs.getInt("item"));
                fachadaBean.setNombreOperacion(rs.getString("nombreOperacion"));
                fachadaBean.setCantidadPedida(rs.getDouble("cantidadPedida"));
                fachadaBean.setPesoPerdido(rs.getDouble("pesoPerdido"));
                fachadaBean.setCantidadPerdida(rs.getDouble("cantidadPerdida"));
                fachadaBean.setNombreTercero(rs.getString("nombreTercero"));
                fachadaBean.setReferenciaCliente(rs.getString("referenciaCliente"));
                fachadaBean.setNombreReferencia(rs.getString("nombreReferencia"));
                fachadaBean.setNumeroOrden(rs.getInt("numeroOrden"));
                fachadaBean.setCantidadTerminada(rs.getDouble("cantidadTerminada"));
                fachadaBean.setCantidadEntregada(rs.getDouble("cantidadEntregada"));
                fachadaBean.setCantidadFacturada(rs.getDouble("cantidadFacturada"));
                fachadaBean.setPesoEntregado(rs.getDouble("pesoEntregado"));
                fachadaBean.setPesoFacturado(rs.getDouble("pesoFacturado"));
                fachadaBean.setPesoTerminado(rs.getDouble("pesoTerminado"));

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
    public Vector listaOT() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        //
        String selectStr =
                "   SELECT tblDctosOrdenesDetalle.idLocal                "
                + "         ,tblDctosOrdenesDetalle.idTipoOrden          "
                + "         ,tblDctosOrdenesDetalle.idOrden              "
                + "         ,tblDctosOrdenesDetalle.item                 "
                + "         ,tblJobOperacion.idOperacion                 "
                + "         ,tblJobOperacion.nombreOperacion             "
                + "         ,tblDctosOrdenesDetalle.cantidad             "
                + "                                 AS cantidadPedida    "
                + "         ,tmpPRG.pesoPerdido                          "
                + "         ,tmpPRG.cantidadPerdida                      "
                + "         ,tmpPED.nombreTercero                        "
                + "         ,tmpPED.referenciaCliente                    "
                + "         ,tmpPED.nombreReferencia                     "
                + "         ,tblDctosOrdenes.numeroOrden                 "
                + "         ,tmpPRG.cantidadTerminada                    "
                + "         ,tblDctosOrdenesDetalle.cantidadEntregada    "
                + "         ,tblDctosOrdenesDetalle.cantidadFacturada    "
                + "         ,tblDctosOrdenesDetalle.pesoEntregado        "
                + "         ,tblDctosOrdenesDetalle.pesoFacturado        "
                + "         ,tmpPRG.pesoTerminado                        "
                + "   FROM tblDctosOrdenesDetalle                        "
                + "   INNER JOIN tblDctosOrdenes                         "
                + "   ON tblDctosOrdenesDetalle.idLocal      =           "
                + "                      tblDctosOrdenes.idLocal         "
                + "   AND tblDctosOrdenesDetalle.idTipoOrden =           "
                + "                  tblDctosOrdenes.idTipoOrden         "
                + "   AND tblDctosOrdenesDetalle.idOrden     =           "
                + "                      tblDctosOrdenes.idOrden         "
                + "   INNER JOIN (                                       "
                + "   SELECT tblDctosOrdenesProgreso.idLocal             "
                + "         ,tblDctosOrdenesProgreso.idTipoOrden         "
                + "         ,tblDctosOrdenesProgreso.idOrden             "
                + "         ,tblDctosOrdenesProgreso.idOperacion         "
                + "         ,tblDctosOrdenesProgreso.itemPadre           "
                + "         ,SUM(cantidadPerdida)                        "
                + "                 AS cantidadPerdida                   "
                + "         ,SUM(cantidadTerminada)                      "
                + "               AS cantidadTerminada                   "
                + "         ,SUM(pesoPerdido)                            "
                + "                     AS pesoPerdido                   "
                + "         ,SUM(pesoTerminado)                          "
                + "                   AS pesoTerminado                   "
                + "   FROM tblDctosOrdenesProgreso                       "
                + "   GROUP BY tblDctosOrdenesProgreso.idLocal           "
                + "         ,tblDctosOrdenesProgreso.idTipoOrden         "
                + "         ,tblDctosOrdenesProgreso.idOrden             "
                + "         ,tblDctosOrdenesProgreso.idOperacion         "
                + "         ,tblDctosOrdenesProgreso.itemPadre)          "
                + "                                        AS tmpPRG     "
                + "   ON tblDctosOrdenesDetalle.idLocal      =           "
                + "                               tmpPRG.idLocal         "
                + "   AND tblDctosOrdenesDetalle.idTipoOrden =           "
                + "                           tmpPRG.idTipoOrden         "
                + "   AND tblDctosOrdenesDetalle.idOrden     =           "
                + "                               tmpPRG.idOrden         "
                + "   AND tblDctosOrdenesDetalle.item        =           "
                + "                             tmpPRG.itemPadre         "
                + "   INNER JOIN tblJobOperacion                         "
                + "   ON tblJobOperacion.idOperacion         =           "
                + "                            tmpPRG.idOperacion        "
                + "   INNER JOIN                                         "
                + "   (SELECT tblPlusFicha.idFicha,                      "
                + "              tblPlusFicha.referenciaCliente,         "
                + "              MAX(tblPlusFicha.nombreReferencia)      "
                + "                           AS nombreReferencia,       "
                + "              MAX(tblTerceros.nombreTercero)          "
                + "                               AS nombreTercero       "
                + "       FROM  tblDctosOrdenes                          "
                + "       INNER JOIN tblPlusFicha                        "
                + "       ON tblDctosOrdenes.idCliente =                 "
                + "                         tblPlusFicha.idCliente       "
                + "       AND tblDctosOrdenes.idFicha  =                 "
                + "                           tblPlusFicha.idFicha       "
                + "       INNER JOIN tblTerceros                         "
                + "       ON tblDctosOrdenes.idCliente =                 "
                + "                          tblTerceros.idCliente       "
                + "       WHERE tblTerceros.idTipoTercero = 1            "
                + "       GROUP BY tblPlusFicha.idFicha,                 "
                + "                tblPlusFicha.referenciaCliente)       "
                + "                                      AS tmpPED       "
                + "   ON tmpPED.idFicha                      =           "
                + "                        tblDctosOrdenes.idFicha       "
                + " WHERE tblDctosOrdenes.idLocal           =            "
                + getIdLocal() + "                                       "
                + " AND   tblDctosOrdenes.idTipoOrden       =            "
                + getIdTipoOrden() + "                                   "
                + " AND tblJobOperacion.idOperacion         =            "
                + getIdOperacion() + "                                   "
                + "   AND  tmpPRG.cantidadTerminada >                    "
                + "     ( tblDctosOrdenesDetalle.cantidadEntregada +     "
                + "       tblDctosOrdenesDetalle.cantidadFacturada  )    "
                + "   AND tblJobOperacion.idOperacion IN (5,6,999)       "
                + "   UNION                                              "
                + "   SELECT tblDctosOrdenesDetalle.idLocal              "
                + "         ,tblDctosOrdenesDetalle.idTipoOrden          "
                + "         ,tblDctosOrdenesDetalle.idOrden              "
                + "         ,tblDctosOrdenesDetalle.item                 "
                + "         ,tblJobOperacion.idOperacion                 "
                + "         ,tblJobOperacion.nombreOperacion             "
                + "         ,tblDctosOrdenesDetalle.cantidad             "
                + "                                 AS cantidadPedida    "
                + "         ,tmpPRG.pesoPerdido                          "
                + "         ,tmpPRG.cantidadPerdida                      "
                + "         ,tmpPED.nombreTercero                        "
                + "         ,tmpPED.referenciaCliente                    "
                + "         ,tmpPED.nombreReferencia                     "
                + "         ,tblDctosOrdenes.numeroOrden                 "
                + "         ,tmpPRG.cantidadTerminada                    "
                + "         ,tblDctosOrdenesDetalle.cantidadEntregada    "
                + "         ,tblDctosOrdenesDetalle.cantidadFacturada    "
                + "         ,tblDctosOrdenesDetalle.pesoEntregado        "
                + "         ,tblDctosOrdenesDetalle.pesoFacturado        "
                + "         ,tmpPRG.pesoTerminado                        "
                + "   FROM tblDctosOrdenesDetalle                        "
                + "   INNER JOIN tblDctosOrdenes                         "
                + "   ON tblDctosOrdenesDetalle.idLocal      =           "
                + "                      tblDctosOrdenes.idLocal         "
                + "   AND tblDctosOrdenesDetalle.idTipoOrden =           "
                + "                  tblDctosOrdenes.idTipoOrden         "
                + "   AND tblDctosOrdenesDetalle.idOrden     =           "
                + "                      tblDctosOrdenes.idOrden         "
                + "   INNER JOIN (                                       "
                + "   SELECT tblDctosOrdenesProgreso.idLocal             "
                + "         ,tblDctosOrdenesProgreso.idTipoOrden         "
                + "         ,tblDctosOrdenesProgreso.idOrden             "
                + "         ,tblDctosOrdenesProgreso.idOperacion         "
                + "         ,tblDctosOrdenesProgreso.itemPadre           "
                + "         ,SUM(cantidadPerdida)                        "
                + "                 AS cantidadPerdida                   "
                + "         ,SUM(cantidadTerminada)                      "
                + "               AS cantidadTerminada                   "
                + "         ,SUM(pesoPerdido)                            "
                + "                     AS pesoPerdido                   "
                + "         ,SUM(pesoTerminado)                          "
                + "                   AS pesoTerminado                   "
                + "   FROM tblDctosOrdenesProgreso                       "
                + "   GROUP BY tblDctosOrdenesProgreso.idLocal           "
                + "         ,tblDctosOrdenesProgreso.idTipoOrden         "
                + "         ,tblDctosOrdenesProgreso.idOrden             "
                + "         ,tblDctosOrdenesProgreso.idOperacion         "
                + "         ,tblDctosOrdenesProgreso.itemPadre)          "
                + "                                        AS tmpPRG     "
                + "   ON tblDctosOrdenesDetalle.idLocal      =           "
                + "                               tmpPRG.idLocal         "
                + "   AND tblDctosOrdenesDetalle.idTipoOrden =           "
                + "                           tmpPRG.idTipoOrden         "
                + "   AND tblDctosOrdenesDetalle.idOrden     =           "
                + "                               tmpPRG.idOrden         "
                + "   AND tblDctosOrdenesDetalle.item        =           "
                + "                             tmpPRG.itemPadre         "
                + "   INNER JOIN tblJobOperacion                         "
                + "   ON tblJobOperacion.idOperacion         =           "
                + "                            tmpPRG.idOperacion        "
                + "   INNER JOIN                                         "
                + "   (SELECT tblPlusFicha.idFicha,                      "
                + "              tblPlusFicha.referenciaCliente,         "
                + "              MAX(tblPlusFicha.nombreReferencia)      "
                + "                           AS nombreReferencia,       "
                + "              MAX(tblTerceros.nombreTercero)          "
                + "                               AS nombreTercero       "
                + "       FROM  tblDctosOrdenes                          "
                + "       INNER JOIN tblPlusFicha                        "
                + "       ON tblDctosOrdenes.idCliente =                 "
                + "                         tblPlusFicha.idCliente       "
                + "       AND tblDctosOrdenes.idFicha  =                 "
                + "                           tblPlusFicha.idFicha       "
                + "       INNER JOIN tblTerceros                         "
                + "       ON tblDctosOrdenes.idCliente =                 "
                + "                          tblTerceros.idCliente       "
                + "       WHERE tblTerceros.idTipoTercero = 1            "
                + "       GROUP BY tblPlusFicha.idFicha,                 "
                + "                tblPlusFicha.referenciaCliente)       "
                + "                                      AS tmpPED       "
                + "   ON tmpPED.idFicha                      =           "
                + "                        tblDctosOrdenes.idFicha       "
                + " WHERE tblDctosOrdenes.idLocal           =            "
                + getIdLocal() + "                                       "
                + " AND   tblDctosOrdenes.idTipoOrden       =            "
                + getIdTipoOrden() + "                                   "
                + " AND tblJobOperacion.idOperacion         =            "
                + getIdOperacion() + "                                   "
                + "   AND  tmpPRG.pesoTerminado >                        "
                + "     ( tblDctosOrdenesDetalle.pesoEntregado +         "
                + "         tblDctosOrdenesDetalle.pesoFacturado  )      "
                + "   AND tblJobOperacion.idOperacion IN (2,3,4)         "
                + "   ORDER BY 5,   10  ,                                "
                + "            tblDctosOrdenes.numeroOrden,              "
                + "            tblDctosOrdenesDetalle.item               ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaPluFicha fachadaBean;

            while (rs.next()) {

                //
                fachadaBean = new FachadaPluFicha();

                //
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setIdOrden(rs.getInt("idOrden"));
                fachadaBean.setItem(rs.getInt("item"));
                fachadaBean.setNombreOperacion(rs.getString("nombreOperacion"));
                fachadaBean.setCantidadPedida(rs.getDouble("cantidadPedida"));
                fachadaBean.setPesoPerdido(rs.getDouble("pesoPerdido"));
                fachadaBean.setCantidadPerdida(rs.getDouble("cantidadPerdida"));
                fachadaBean.setNombreTercero(rs.getString("nombreTercero"));
                fachadaBean.setReferenciaCliente(rs.getString("referenciaCliente"));
                fachadaBean.setNombreReferencia(rs.getString("nombreReferencia"));
                fachadaBean.setNumeroOrden(rs.getInt("numeroOrden"));
                fachadaBean.setCantidadTerminada(rs.getDouble("cantidadTerminada"));
                fachadaBean.setCantidadEntregada(rs.getDouble("cantidadEntregada"));
                fachadaBean.setCantidadFacturada(rs.getDouble("cantidadFacturada"));
                fachadaBean.setPesoEntregado(rs.getDouble("pesoEntregado"));
                fachadaBean.setPesoFacturado(rs.getDouble("pesoFacturado"));
                fachadaBean.setPesoTerminado(rs.getDouble("pesoTerminado"));

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

    // listaMPUnOperacion
    public Vector listaMPUnOperacion() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        //
        String selectStr =
                "  SELECT tmpORD.idPlu,                                 "
                + "          MAX(tmpPLU.nombrePlu)                      "
                + "                           AS nombrePlu,             "
                + "          (SELECT tblplusinventario.existencia\n"
                + "  FROM tblplusinventario\n"
                + "  WHERE tblplusinventario.idplu = tmpORD.idPlu\n"
                + "  AND tblplusinventario.idBodega = "
                + getIdBodega() + " )AS existenciaCantidad,             "
                + "          (SELECT tblplusinventario.existencia\n"
                + "  FROM tblplusinventario\n"
                + "  WHERE tblplusinventario.idplu = tmpORD.idPlu\n"
                + "  AND tblplusinventario.idBodega = "
                + getIdBodega() + " )   AS existenciaPeso,             "
                + "          0 AS numeroOrden,                          "
                + "          'M.P.' AS nombreItem,                      "
                + "          'P.U.' AS nombreOperario                   "
                + "   FROM                                              "
                + "   ( SELECT tblDctosOrdenesDetalle.idLocalOrigen,    "
                + "        tblDctosOrdenesDetalle.idTipoOrdenOrigen,    "
                + "            tblDctosOrdenesDetalle.idOrdenOrigen,    "
                + "            tblDctosOrdenesDetalle.idPlu,            "
                + "            tblDctosOrdenesDetalle.idBodega          "
                + "                                AS idOperacion,      "
                + "            SUM( tblDctosOrdenesDetalle.cantidad *   "
                + "                              tblTipoOrden.signo )   "
                + "                            AS existenciaCantidad,   "
                + "       SUM( tblDctosOrdenesDetalle.pesoTerminado *   "
                + "                              tblTipoOrden.signo )   "
                + "                                AS existenciaPeso    "
                + "   FROM tblDctosOrdenesDetalle                       "
                + "   INNER JOIN tblTipoOrden                           "
                + "   ON tblDctosOrdenesDetalle.IDTIPOORDEN =           "
                + "                         tblTipoOrden.idTipoOrden    "
                + "     WHERE  tblDctosOrdenesDetalle.idLocal  =        "
                + getIdLocal() + "                                      "
                + "     AND    tblDctosOrdenesDetalle.idBodega =        "
                + getIdBodega() + "                                     "
                + "   AND   tblDctosOrdenesDetalle.idPlu   != 209       "
                + "   GROUP BY tblDctosOrdenesDetalle.idLocalOrigen,    "
                + "        tblDctosOrdenesDetalle.idTipoOrdenOrigen,    "
                + "        tblDctosOrdenesDetalle.idOrdenOrigen,        "
                + "        tblDctosOrdenesDetalle.idPlu,                "
                + "        tblDctosOrdenesDetalle.idBodega)             "
                + "                                AS tmpORD            "
                + "   INNER JOIN                                        "
                + "     ( SELECT tblPlus.idPlu,                         "
                + "          tblCategorias.nombreCategoria +            "
                + "                                 ' ' +               "
                + "          tblPlus.nombrePlu AS nombrePlu,            "
                + "                tblMarcas.nombreMarca                "
                + "       FROM   tblMarcas                              "
                + "       INNER JOIN tblPlus                            "
                + "       ON tblMarcas.idMarca    =                     "
                + "                         tblPlus.idMarca             "
                + "       INNER JOIN tblCategorias                      "
                + "       ON tblPlus.idLinea      =                     "
                + "                   tblCategorias.idLinea             "
                + "       AND tblPlus.idCategoria =                     "
                + "               tblCategorias.idCategoria)            "
                + "                                AS tmpPLU            "
                + "        ON tmpPLU.idPlu = tmpORD.idPlu               "
                + "   WHERE NOT EXISTS (                                "
                + "   (SELECT tblDctosOrdenesDetalle.*                  "
                + "    FROM  tblDctos                                   "
                + "    INNER JOIN tblDctosOrdenesDetalle                "
                + "    ON tblDctos.IDLOCAL      =                       "
                + "          tblDctosOrdenesDetalle.IDLOCAL             "
                + "    AND tblDctos.IDTIPOORDEN =                       "
                + "      tblDctosOrdenesDetalle.IDTIPOORDEN             "
                + "    AND tblDctos.IDORDEN     =                       "
                + "          tblDctosOrdenesDetalle.IDORDEN             "
                + "    INNER JOIN tblTipoOrden                          "
                + "    ON tblDctosOrdenesDetalle.IDTIPOORDEN    =       "
                + "                tblTipoOrden.idTipoOrden             "
                + "     WHERE  tblDctosOrdenesDetalle.idLocal  =        "
                + getIdLocal() + "                                      "
                + "     AND    tblDctosOrdenesDetalle.idBodega =        "
                + getIdBodega() + "                                     "
                + "   AND    tblDctosOrdenesDetalle.idPlu   != 209      "
                + "   AND tblDctosOrdenesDetalle.idTipoOrden IN (18)    "
                + "   AND    tblDctosOrdenesDetalle.idLocalOrigen  =    "
                + "                       tmpORD.idLocalOrigen          "
                + "   AND tblDctosOrdenesDetalle.idTipoOrdenOrigen =    "
                + "                       tmpORD.idTipoOrdenOrigen      "
                + "   AND tblDctosOrdenesDetalle.idOrdenOrigen     =    "
                + "                          tmpORD.idOrdenOrigen))     "
                + "   GROUP BY tmpORD.idPlu                             "
                + "   UNION                                             "
                + "  SELECT 0 AS idPlu,                                 "
                + "       MAX(tmpFIC.referenciaCliente)                 "
                + "   	           AS referenciaCliente,               "
                + "          SUM(tmpORD.existenciaCantidad)             "
                + "                  AS existenciaCantidad,             "
                + "          SUM(tmpORD.existenciaPeso)                 "
                + "                      AS existenciaPeso,             "
                + "          tmpFIC.numeroOrden,                        "
                + "          MAX(tmpFIC.nombreItem) AS nombreItem  ,    "
                + "          MAX(tmpEXT.nombreOperario)                 "
                + "                                AS nombreOperario    "
                + "   FROM                                              "
                + "    (SELECT tblDctosOrdenesDetalle.idLocalOrigen     "
                + "                                     AS idLocal,     "
                + "        tblDctosOrdenesDetalle.idTipoOrdenOrigen     "
                + "                                 AS idTipoOrden,     "
                + "           tblDctosOrdenesDetalle.idOrdenOrigen      "
                + "                                     AS idOrden      "
                + "        FROM  tblDctos                               "
                + "        INNER JOIN tblDctosOrdenesDetalle            "
                + "        ON tblDctos.IDLOCAL      =                   "
                + "                 tblDctosOrdenesDetalle.IDLOCAL      "
                + "        AND tblDctos.IDTIPOORDEN =                   "
                + "             tblDctosOrdenesDetalle.IDTIPOORDEN      "
                + "        AND tblDctos.IDORDEN     =                   "
                + "                 tblDctosOrdenesDetalle.IDORDEN      "
                + "        INNER JOIN tblTipoOrden                      "
                + "        ON tblDctosOrdenesDetalle.IDTIPOORDEN =      "
                + "                       tblTipoOrden.idTipoOrden      "
                + "     WHERE  tblDctosOrdenesDetalle.idLocal  =        "
                + getIdLocal() + "                                      "
                + "     AND    tblDctosOrdenesDetalle.idBodega =        "
                + getIdBodega() + "                                     "
                + "   AND    tblDctosOrdenesDetalle.idPlu   != 209      "
                + "   AND  tblDctosOrdenesDetalle.idTipoOrden IN (18)   "
                + "   GROUP BY tblDctosOrdenesDetalle.idLocalOrigen,    "
                + "         tblDctosOrdenesDetalle.idTipoOrdenOrigen,   "
                + "            tblDctosOrdenesDetalle.idOrdenOrigen)    "
                + "                                        AS tmpINV    "
                + "   INNER JOIN                                        "
                + "    ( SELECT tblDctosOrdenesDetalle.idLocalOrigen,   "
                + "         tblDctosOrdenesDetalle.idTipoOrdenOrigen,   "
                + "             tblDctosOrdenesDetalle.idOrdenOrigen,   "
                + "               tblDctosOrdenesDetalle.idPlu,         "
                + "               tblDctosOrdenesDetalle.idBodega       "
                + "                                  AS idOperacion,    "
                + "             SUM( tblDctosOrdenesDetalle.cantidad *  "
                + "                               tblTipoOrden.signo )  "
                + "                             AS existenciaCantidad,  "
                + "        SUM( tblDctosOrdenesDetalle.pesoTerminado *  "
                + "                               tblTipoOrden.signo )  "
                + "                               AS existenciaPeso     "
                + "   FROM tblDctosOrdenesDetalle                       "
                + "   INNER JOIN tblTipoOrden                           "
                + "   ON tblDctosOrdenesDetalle.IDTIPOORDEN =           "
                + "                          tblTipoOrden.idTipoOrden   "
                + "     WHERE  tblDctosOrdenesDetalle.idLocal  =        "
                + getIdLocal() + "                                      "
                + "     AND    tblDctosOrdenesDetalle.idBodega =        "
                + getIdBodega() + "                                     "
                + "   GROUP BY tblDctosOrdenesDetalle.idLocalOrigen,    "
                + "        tblDctosOrdenesDetalle.idTipoOrdenOrigen,    "
                + "            tblDctosOrdenesDetalle.idOrdenOrigen,    "
                + "            tblDctosOrdenesDetalle.idPlu,            "
                + "            tblDctosOrdenesDetalle.idBodega)         "
                + "                                        AS tmpORD    "
                + "                                                     "
                + "   ON tmpINV.idLocal      = tmpORD.idLocalOrigen     "
                + "   AND tmpINV.idTipoOrden =                          "
                + "                        tmpORD.idTipoOrdenOrigen     "
                + "   AND tmpINV.idOrden     = tmpORD.idOrdenOrigen     "
                + "   INNER JOIN (                                      "
                + "       SELECT tblDctosOrdenes.idLocal,               "
                + "              tblDctosOrdenes.idTipoOrden,           "
                + "              tblDctosOrdenes.idOrden,               "
                + "              tblDctosOrdenes.numeroOrden,           "
                + "              tblDctosOrdenes.idFicha,               "
                + "              tmpFHA.referenciaCliente,              "
                + "              tmpITM.idOperacion,                    "
                + "              tmpITM.nombreItem                      "
                + "       FROM  tblDctosOrdenes                         "
                + "       INNER JOIN (                                  "
                + "       SELECT tblJobProgramaPlusFicha.idFicha,       "
                + "              tblJobProgramaPlusFicha.idOrden,       "
                + "    MAX(tblJobProgramaPlusFicha.referenciaCliente)   "
                + "                            AS referenciaCliente     "
                + "       FROM tblJobProgramaPlusFicha                  "
                + "       GROUP BY tblJobProgramaPlusFicha.idFicha,     "
                + "                tblJobProgramaPlusFicha.idOrden )    "
                + "                                     AS tmpFHA       "
                + "   ON tmpFHA.idFicha = tblDctosOrdenes.idFicha       "
                + "   AND tmpFHA.idOrden = tblDctosOrdenes.idOrden      "
                + "     INNER JOIN                                      "
                + "      ( SELECT tblJobProgramaPlusFicha.idFicha       "
                + "              ,tblJobEscalaDetalle.nombreItem        "
                + "              ,tblJobProgramaPlusFicha.idOperacion   "
                + "              ,tblJobProgramaPlusFicha.idOrden       "
                + "        FROM tblJobProgramaPlusFicha                 "
                + "        INNER JOIN tblJobEscalaDetalle               "
                + "        ON tblJobEscalaDetalle.idEscala              "
                + "                = tblJobProgramaPlusFicha.idEscala   "
                + "        AND tblJobEscalaDetalle.item                 "
                + "                = tblJobProgramaPlusFicha.vrEscala   "
                + "        WHERE tblJobProgramaPlusFicha.idEscala       "
                + "            IN ( 600, 710, 910, 1000, 1100, 1200 )   "
                + "        AND tblJobProgramaPlusFicha.idOperacion =    "
                + getIdBodega() + "                                     "
                + "        GROUP BY tblJobProgramaPlusFicha.idFicha     "
                + "                ,tblJobEscalaDetalle.nombreItem      "
                + "             ,tblJobProgramaPlusFicha.idOperacion    "
                + "             ,tblJobProgramaPlusFicha.idOrden  )     "
                + "                                        AS tmpITM    "
                + "     ON tmpITM.idFicha = tblDctosOrdenes.idFicha     "
                + "     AND tmpITM.idOrden = tblDctosOrdenes.idOrden    "
                + "                                      ) AS tmpFIC    "
                + "   ON  tmpFIC.idLocal     = tmpORD.idLocalOrigen     "
                + "   AND tmpFIC.idTipoOrden =                          "
                + "                         tmpORD.idTipoOrdenOrigen    "
                + "   AND tmpFIC.idOrden = tmpORD.idOrdenOrigen         "
                + "   INNER JOIN                                        "
                + "   ( SELECT tblDctosOrdenesProgreso.idLocal          "
                + "           ,tblDctosOrdenesProgreso.idTipoOrden      "
                + "           ,tblDctosOrdenesProgreso.idOrden          "
                + "           ,tblDctosOrdenesProgreso.idOperacion      "
                + "           ,ISNULL(MAX(tmpOPE.nombreTercero),'')     "
                + "                               AS nombreOperario     "
                + "     FROM tblDctosOrdenesProgreso                    "
                + "     LEFT JOIN                                       "
                + "     ( SELECT tblTerceros.idTercero                  "
                + "              ,MAX(tblTerceros.nombreTercero)        "
                + "                             AS nombreTercero        "
                + "       FROM tblTerceros                              "
                + "       GROUP BY tblTerceros.idTercero )              "
                + "                                 AS tmpOPE           "
                + "       ON tmpOPE.idTercero =                         "
                + "             tblDctosOrdenesProgreso.idOperario      "
                + "    WHERE tblDctosOrdenesProgreso.idControlTipo = 2  "
                + "    GROUP BY tblDctosOrdenesProgreso.idLocal         "
                + "            ,tblDctosOrdenesProgreso.idTipoOrden     "
                + "            ,tblDctosOrdenesProgreso.idOrden         "
                + "            ,tblDctosOrdenesProgreso.idOperacion)    "
                + "                                       AS tmpEXT     "
                + "      ON   tmpFIC.idLocal     = tmpEXT.idLocal       "
                + "      AND  tmpFIC.idTipoOrden = tmpEXT.idTipoOrden   "
                + "      AND  tmpFIC.idOrden     = tmpEXT.idOrden       "
                + "      AND  tmpFIC.idOperacion = tmpEXT.idOperacion   "
                + "      GROUP BY tmpFIC.numeroOrden                    ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaPluFicha fachadaBean;

            while (rs.next()) {

                //
                fachadaBean = new FachadaPluFicha();

                //
                fachadaBean.setIdPlu(rs.getInt("idPlu"));
                fachadaBean.setNombrePlu(rs.getString("nombrePlu"));
                fachadaBean.setExistenciaCantidad(
                        rs.getDouble("existenciaCantidad"));
                fachadaBean.setExistenciaPeso(rs.getDouble("existenciaPeso"));
                fachadaBean.setNumeroOrden(rs.getInt("numeroOrden"));
                fachadaBean.setNombreItem(rs.getString("nombreItem"));
                fachadaBean.setNombreOperario(rs.getString("nombreOperario"));

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

    // listaOTExterno
    public Vector listaOTExterno() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        //
        String selectStr =
                " SELECT tmpORD.numeroOrden,                               "
                + "        tmpORD.idFicha,                                 "
                + "        tmpORD.referenciaCliente,                       "
                + "        tmpORD.nombreTercero,                           "
                + "        tmpRES.idLocal,                                 "
                + "        tmpRES.idTipoOrden,                             "
                + "        tmpRES.idOrden,                                 "
                + "        tmpTER.nombreOperario,                          "
                + "        SUM(tmpRES.cantidadSalida -                     "
                + "            tmpRES.cantidadEntrada)                     "
                + "              AS cantidadTerminada ,                    "
                + "        SUM(tmpRES.pesoSalida     -                     "
                + "                tmpRES.pesoEntrada)                     "
                + "                   AS pesoTerminado                     "
                + " FROM (                                                 "
                + " SELECT [tblDctosOrdenesProgreso].idLocal,              "
                + "        [tblDctosOrdenesProgreso].idTipoOrden,          "
                + "        [tblDctosOrdenesProgreso].idOrden,              "
                + "        [tblDctosOrdenesProgreso].idControlTipo,        "
                + "        [tblDctosOrdenesProgreso].idOperario,           "
                + "        CASE                                            "
                + "         WHEN                                           "
                + "         [tblDctosOrdenesProgreso].idControlTipo = 1    "
                + "          THEN                                          "
                + "          SUM(                                          "
                + "          [tblDctosOrdenesProgreso].cantidadTerminada)  "
                + "        ELSE 0.0                                        "
                + "        END AS cantidadEntrada,                         "
                + "        CASE                                            "
                + "         WHEN                                           "
                + "          [tblDctosOrdenesProgreso].idControlTipo = 1   "
                + "          THEN                                          "
                + "          SUM([tblDctosOrdenesProgreso].pesoTerminado)  "
                + "        ELSE 0.0                                        "
                + "        END AS pesoEntrada,                             "
                + "        CASE                                            "
                + "         WHEN                                           "
                + "          [tblDctosOrdenesProgreso].idControlTipo = 2   "
                + "          THEN                                          "
                + "           SUM(                                         "
                + "           [tblDctosOrdenesProgreso].cantidadTerminada) "
                + "        ELSE 0.0                                        "
                + "        END AS cantidadSalida,                          "
                + "        CASE                                            "
                + "         WHEN                                           "
                + "         [tblDctosOrdenesProgreso].idControlTipo = 2    "
                + "          THEN                                          "
                + "           SUM([tblDctosOrdenesProgreso].pesoTerminado) "
                + "        ELSE 0.0                                        "
                + "        END AS pesoSalida                               "
                + " FROM   [tblDctosOrdenesProgreso]                       "
                + " WHERE [tblDctosOrdenesProgreso].idLocal       =        "
                + getIdLocal() + "                                         "
                + " AND [tblDctosOrdenesProgreso].idTipoOrden  IN (59)     "
                + " AND   [tblDctosOrdenesProgreso].idOperacion   =        "
                + getIdOperacion() + "                                     "
                + " AND   [tblDctosOrdenesProgreso].idControlTipo IN (1,2) "
                + " GROUP BY [tblDctosOrdenesProgreso].idLocal,            "
                + "          [tblDctosOrdenesProgreso].idTipoOrden,        "
                + "          [tblDctosOrdenesProgreso].idOrden,            "
                + "          [tblDctosOrdenesProgreso].idControlTipo,      "
                + "          [tblDctosOrdenesProgreso].idOperario )        "
                + "                                          AS tmpRES     "
                + " INNER JOIN (                                           "
                + " SELECT tblDctosOrdenes.IDLOCAL,                        "
                + "        tblDctosOrdenes.idTipoOrden,                    "
                + "        tblDctosOrdenes.idOrden,                        "
                + "        tblDctosOrdenes.numeroOrden,                    "
                + "        tblDctosOrdenes.idFicha,                        "
                + "        tmpFIC.referenciaCliente,                       "
                + "        tblTerceros.nombreTercero                       "
                + " FROM tblDctosOrdenes                                   "
                + " INNER JOIN (                                           "
                + " SELECT [idFicha]                                       "
                + "       ,referenciaCliente                               "
                + " FROM tblPlusFicha                                      "
                + " GROUP BY [idFicha]                                     "
                + "       ,referenciaCliente) AS tmpFIC                    "
                + " ON tblDctosOrdenes.idFicha =                           "
                + "                tmpFIC.idFicha                          "
                + " INNER JOIN tblTerceros                                 "
                + " ON  tblTerceros.idCliente =                            "
                + "            tblDctosOrdenes.idCliente                   "
                + " WHERE tblDctosOrdenes.IDLOCAL =                        "
                + getIdLocal() + "                                         "
                + " AND tblDctosOrdenes.IDTIPOORDEN IN (59)                "
                + " AND tblTerceros.idTipoTercero   = 1)                   "
                + "                            AS tmpORD                   "
                + " ON tmpORD.IDLOCAL               =                      "
                + "                      tmpRES.idLocal                    "
                + " AND tmpORD.IDTIPOORDEN          =                      "
                + "                  tmpRES.idTipoOrden                    "
                + " AND tmpORD.IDORDEN              =                      "
                + "                      tmpRES.idOrden                    "                
                + " INNER JOIN                                             "
                + " ( SELECT ctrlUsuarios.idUsuario                        "
                + "                        AS idCliente                    "
                + "         ,ctrlUsuarios.nombreUsuario                    "
                + "                     AS nombreOperario,                 "
                + "                           1 AS orden                   "
                + "  FROM ctrlUsuarios                                     "
                + "  UNION                                                 "
                + "  SELECT tblTerceros.idCliente,                         "
                + "         tblTerceros.nombreTercero                      "
                + "                                 AS nombreOperario,     "
                + "                         1 AS orden                     "
                + "  FROM tblTerceros                                      "
                + "  WHERE tblTerceros.idTipoTercero = 2                   "
                + "                                      ) AS tmpTER       "         
                + " ON  tmpTER.idCliente       =   tmpRES.idOperario       "
                + " GROUP BY tmpORD.numeroOrden,                           "                
                + "        tmpORD.idFicha,                                 "
                + "        tmpORD.referenciaCliente,                       "
                + "        tmpORD.nombreTercero,                           "
                + "        tmpRES.idLocal,                                 "
                + "        tmpRES.idTipoOrden,                             "
                + "        tmpRES.idOrden,                                 "
                + "        tmpTER.nombreOperario ";
        
        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaPluFicha fachadaBean;

            while (rs.next()) {

                //
                fachadaBean = new FachadaPluFicha();

                //
                fachadaBean.setNumeroOrden(rs.getInt("numeroOrden"));
                fachadaBean.setIdFicha(rs.getInt("idFicha"));                
                fachadaBean.setReferenciaCliente(rs.getString("referenciaCliente"));                
                fachadaBean.setNombreTercero(rs.getString("nombreTercero"));                                
                fachadaBean.setNombreOperario(rs.getString("nombreOperario"));                                                
                fachadaBean.setExistenciaCantidad(rs.getDouble("cantidadTerminada"));
                fachadaBean.setExistenciaPeso(rs.getDouble("pesoTerminado"));
                fachadaBean.setNombreItem("");
                
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
    
    // listaMPMaterial
    public Vector listaMPMaterial() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        //
        String selectStr =
                "  SELECT tmpINV.idPlu,                                "
                + "         (SELECT tblplusinventario.existencia\n"
                + "  FROM tblplusinventario\n"
                + "  WHERE tblplusinventario.idplu = tmpINV.idPlu\n"
                + "  AND tblplusinventario.idBodega = "
                + getIdBodega() + ") AS existenciaPeso,                     "
                + "        (SELECT tblplusinventario.existencia\n"
                + "  FROM tblplusinventario\n"
                + "  WHERE tblplusinventario.idplu = tmpINV.idPlu\n"
                + "  AND tblplusinventario.idBodega = "
                + getIdBodega() + "  ) AS existenciaCantidad, "
                + "         tmpPLU.nombrePlu                           "
                + "  FROM                                              "
                + "   (SELECT  tblDctosOrdenesDetalle.idPlu,           "
                + "            tblDctosOrdenesDetalle.idBodega,        "
                + "      SUM( tblDctosOrdenesDetalle.cantidad *        "
                + "                              tblTipoOrden.signo )  "
                + "                            AS existenciaCantidad,  "
                + "      SUM( tblDctosOrdenesDetalle.pesoTerminado *   "
                + "                              tblTipoOrden.signo )  "
                + "                                AS existenciaPeso   "
                + "    FROM  tblDctos                                  "
                + "    INNER JOIN tblDctosOrdenesDetalle               "
                + "    ON tblDctos.IDLOCAL      =                      "
                + "                   tblDctosOrdenesDetalle.IDLOCAL   "
                + "    AND tblDctos.IDTIPOORDEN =                      "
                + "               tblDctosOrdenesDetalle.IDTIPOORDEN   "
                + "    AND tblDctos.IDORDEN     =                      "
                + "                   tblDctosOrdenesDetalle.IDORDEN   "
                + "    INNER JOIN tblTipoOrden                         "
                + "    ON tblDctosOrdenesDetalle.IDTIPOORDEN =          "
                + "                         tblTipoOrden.idTipoOrden   "
                + "    INNER JOIN tblPlusInventario                    "
                + "    ON tblDctosOrdenesDetalle.IDLOCAL               "
                + "                      = tblPlusInventario.idLocal   "
                + "    AND tblDctosOrdenesDetalle.IDPLU                "
                + "                        = tblPlusInventario.idPlu   "
                + "    AND tblDctosOrdenesDetalle.idBodega             "
                + "                     = tblPlusInventario.idBodega   "
                + "   WHERE  tblDctosOrdenesDetalle.idLocal  =         "
                + getIdLocal() + "                                     "
                + "   AND    tblDctosOrdenesDetalle.idBodega =         "
                + getIdBodega() + "                                    "
                + "    AND    tblDctosOrdenesDetalle.idPlu   != 209    "
                + "    GROUP BY tblDctosOrdenesDetalle.idPlu,          "
                + "             tblDctosOrdenesDetalle.idBodega)       "
                + "                                        AS tmpINV   "
                + "  INNER JOIN                                        "
                + "    ( SELECT tblPlus.idPlu,                         "
                + "             tblCategorias.nombreCategoria +        "
                + "                                       ' ' +        "
                + "             tblPlus.nombrePlu AS nombrePlu,        "
                + "             tblMarcas.nombreMarca                  "
                + "      FROM   tblMarcas                              "
                + "      INNER JOIN tblPlus                            "
                + "      ON tblMarcas.idMarca    =                     "
                + "                            tblPlus.idMarca         "
                + "      INNER JOIN tblCategorias                      "
                + "      ON tblPlus.idLinea      =                     "
                + "                     tblCategorias.idLinea          "
                + "      AND tblPlus.idCategoria =                     "
                + "                 tblCategorias.idCategoria)         "
                + "                                 AS tmpPLU          "
                + "  ON tmpINV.idPlu = tmpPLU.idPlu                    "
                + "                ORDER BY tmpPLU.nombrePlu           ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaPluFicha fachadaBean;

            while (rs.next()) {

                //
                fachadaBean = new FachadaPluFicha();

                //
                fachadaBean.setIdPlu(rs.getInt("idPlu"));
                fachadaBean.setNombrePlu(rs.getString("nombrePlu"));
                fachadaBean.setExistenciaCantidad(rs.getDouble("existenciaCantidad"));
                fachadaBean.setExistenciaPeso(rs.getDouble("existenciaPeso"));
                fachadaBean.setNumeroOrden(0);
                fachadaBean.setIdFicha(0);
                fachadaBean.setNombreItem("");

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
    

    // listaMPMaquina_XXX
    public Vector listaMPMaquina_XXX() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        //
        String selectStr =
                "   SELECT  tmpORD.numeroOrden AS idPlu,                  "
                + "           SUM(tmpINV.existenciaPeso)                  "
                + "                                 AS existenciaPeso,    "
                + "           SUM(tmpINV.existenciaCantidad)              "
                + "                              AS existenciaCantidad,   "
                + "           tmpORD.referenciaCliente AS nombrePlu,      "
                + "           tmpOPE.nombreItem,                          "
                + "           tmpORD.numeroOrden,                         "
                + "           tmpORD.referenciaCliente                    "
                + "   FROM                                                "
                + "      (SELECT  tblDctosOrdenesDetalle.idPlu,           "
                + "               tblDctosOrdenesDetalle.idBodega         "
                + "                                      AS idOperacion,  "
                + "         SUM( tblDctosOrdenesDetalle.cantidad *        "
                + "                                 tblTipoOrden.signo )  "
                + "                               AS existenciaCantidad,  "
                + "         SUM( tblDctosOrdenesDetalle.pesoTerminado *   "
                + "                                 tblTipoOrden.signo )  "
                + "                                   AS existenciaPeso,  "
                + "                tblDctosOrdenesDetalle.idLocalOrigen,  "
                + "            tblDctosOrdenesDetalle.idTipoOrdenOrigen,  "
                + "                tblDctosOrdenesDetalle.idOrdenOrigen   "
                + "       FROM  tblDctos                                  "
                + "       INNER JOIN tblDctosOrdenesDetalle               "
                + "       ON tblDctos.IDLOCAL      =                      "
                + "                      tblDctosOrdenesDetalle.IDLOCAL   "
                + "       AND tblDctos.IDTIPOORDEN =                      "
                + "                  tblDctosOrdenesDetalle.IDTIPOORDEN   "
                + "       AND tblDctos.IDORDEN     =                      "
                + "                      tblDctosOrdenesDetalle.IDORDEN   "
                + "       INNER JOIN tblTipoOrden                         "
                + "       ON tblDctosOrdenesDetalle.IDTIPOORDEN           "
                + "                                =                      "
                + "                            tblTipoOrden.idTipoOrden   "
                + "       INNER JOIN tblPlusInventario                    "
                + "       ON tblDctosOrdenesDetalle.IDLOCAL               "
                + "                         = tblPlusInventario.idLocal   "
                + "       AND tblDctosOrdenesDetalle.IDPLU                "
                + "                           = tblPlusInventario.idPlu   "
                + "       AND tblDctosOrdenesDetalle.idBodega             "
                + "                        = tblPlusInventario.idBodega   "
                + "    WHERE  tblDctosOrdenesDetalle.idLocal  =         "
                + getIdLocal() + "                                      "
                + "    AND    tblDctosOrdenesDetalle.idBodega =         "
                + getIdBodega() + "                                     "
                + "       AND    tblDctosOrdenesDetalle.idPlu   != 209    "
                + "       GROUP BY tblDctosOrdenesDetalle.idPlu,          "
                + "                tblDctosOrdenesDetalle.idBodega,       "
                + "                tblDctosOrdenesDetalle.idLocalOrigen,  "
                + "            tblDctosOrdenesDetalle.idTipoOrdenOrigen,  "
                + "                tblDctosOrdenesDetalle.idOrdenOrigen)  "
                + "                                           AS tmpINV   "
                + "     INNER JOIN (                                      "
                + "     SELECT tblDctosOrdenes.idLocal,                   "
                + "            tblDctosOrdenes.idTipoOrden,               "
                + "            tblDctosOrdenes.idOrden,                   "
                + "            tblDctosOrdenes.numeroOrden,               "
                + "            tblDctosOrdenes.idFicha,                   "
                + "            tmpFHA.referenciaCliente                   "
                + "     FROM  tblDctosOrdenes                             "
                + "     INNER JOIN (                                      "
                + "       SELECT tblJobProgramaPlusFicha.idFicha,         "
                + "              tblJobProgramaPlusFicha.idOrden,         "
                + "       MAX(tblJobProgramaPlusFicha.referenciaCliente)  "
                + "                   AS referenciaCliente                "
                + "       FROM tblJobProgramaPlusFicha                    "
                + "       GROUP BY tblJobProgramaPlusFicha.idFicha,       "
                + "                tblJobProgramaPlusFicha.idOrden )      "
                + "                                            AS tmpFHA  "
                + "     ON tmpFHA.idFicha = tblDctosOrdenes.idFicha       "
                + "    AND tmpFHA.idOrden = tblDctosOrdenes.idOrden       "
                + "     WHERE EXISTS                                      "
                + "      ( SELECT tblDctosOrdenesDetalle.*                "
                + "        FROM tblDctosOrdenesDetalle                    "
                + "        WHERE tblDctosOrdenes.IDLOCAL =                "
                + "          tblDctosOrdenesDetalle.idLocalOrigen         "
                + "        AND tblDctosOrdenes.IDTIPOORDEN =              "
                + "          tblDctosOrdenesDetalle.idTipoOrdenOrigen     "
                + "        AND tblDctosOrdenes.IDORDEN =                  "
                + "          tblDctosOrdenesDetalle.idOrdenOrigen )       "
                + "     AND tblDctosOrdenes.idTipoOrden = 59  )           "
                + "                                          AS tmpORD    "
                + "     ON tmpORD.idLocal                                 "
                + "                     = tmpINV.idLocalOrigen            "
                + "     AND tmpORD.idTipoOrden                            "
                + "                     = tmpINV.idTipoOrdenOrigen        "
                + "     AND tmpORD.idOrden                                "
                + "                     = tmpINV.idOrdenOrigen            "
                + "    INNER JOIN                                         "
                + "    ( SELECT tblJobProgramaPlusFicha.idFicha           "
                + "           ,tblJobEscalaDetalle.nombreItem             "
                + "           ,tblJobProgramaPlusFicha.idOperacion        "
                + "     FROM tblJobProgramaPlusFicha                      "
                + "     INNER JOIN tblJobEscalaDetalle                    "
                + "     ON tblJobEscalaDetalle.idEscala                   "
                + "               = tblJobProgramaPlusFicha.idEscala      "
                + "     AND tblJobEscalaDetalle.item                      "
                + "               = tblJobProgramaPlusFicha.vrEscala      "
                + "     WHERE tblJobProgramaPlusFicha.idEscala            "
                + "      IN ( 600, 710, 910, 1000, 1100, 1200 )           "
                + "    GROUP BY tblJobProgramaPlusFicha.idFicha           "
                + "           ,tblJobEscalaDetalle.nombreItem             "
                + "           ,tblJobProgramaPlusFicha.idOperacion  )     "
                + "                                        AS tmpOPE      "
                + "    ON  tmpOPE.idOperacion = tmpINV.idOperacion        "
                + "    AND tmpOPE.idFicha = tmpORD.idFicha                "
                + "    GROUP BY  tmpORD.numeroOrden,                      "
                + "              tmpORD.referenciaCliente,                "
                + "              tmpORD.numeroOrden,                      "
                + "              tmpOPE.nombreItem                        "
                + "    UNION                                              "
                + "    SELECT tmpINV.idPlu,                               "
                + "            SUM(tmpINV.existenciaPeso)                 "
                + "                               AS existenciaPeso,      "
                + "            SUM(tmpINV.existenciaCantidad)             "
                + "                           AS existenciaCantidad,      "
                + "            tmpPLU.nombrePlu,                          "
                + "           'M.P.' AS nombreItem,                       "
                + "           0 AS numeroOrden,                           "
                + "           'MATERIA PRIMA' AS referenciaCliente        "
                + "     FROM                                              "
                + "      (SELECT  tblDctosOrdenesDetalle.idPlu,           "
                + "               tblDctosOrdenesDetalle.idBodega         "
                + "                                  AS idOperacion,      "
                + "         SUM( tblDctosOrdenesDetalle.cantidad *        "
                + "                             tblTipoOrden.signo )      "
                + "                           AS existenciaCantidad,      "
                + "         SUM( tblDctosOrdenesDetalle.pesoTerminado *   "
                + "                             tblTipoOrden.signo )      "
                + "                               AS existenciaPeso,      "
                + "             tblDctosOrdenesDetalle.idLocalOrigen,     "
                + "         tblDctosOrdenesDetalle.idTipoOrdenOrigen,     "
                + "             tblDctosOrdenesDetalle.idOrdenOrigen      "
                + "       FROM  tblDctos                                  "
                + "       INNER JOIN tblDctosOrdenesDetalle               "
                + "       ON tblDctos.IDLOCAL      =                      "
                + "                      tblDctosOrdenesDetalle.IDLOCAL   "
                + "       AND tblDctos.IDTIPOORDEN =                      "
                + "                  tblDctosOrdenesDetalle.IDTIPOORDEN   "
                + "       AND tblDctos.IDORDEN     =                      "
                + "                      tblDctosOrdenesDetalle.IDORDEN   "
                + "       INNER JOIN tblTipoOrden                         "
                + "       ON tblDctosOrdenesDetalle.IDTIPOORDEN           "
                + "                                =                      "
                + "                            tblTipoOrden.idTipoOrden   "
                + "       INNER JOIN tblPlusInventario                    "
                + "       ON tblDctosOrdenesDetalle.IDLOCAL               "
                + "                         = tblPlusInventario.idLocal   "
                + "       AND tblDctosOrdenesDetalle.IDPLU                "
                + "                           = tblPlusInventario.idPlu   "
                + "       AND tblDctosOrdenesDetalle.idBodega             "
                + "                        = tblPlusInventario.idBodega   "
                + "    WHERE  tblDctosOrdenesDetalle.idLocal  =         "
                + getIdLocal() + "                                      "
                + "    AND    tblDctosOrdenesDetalle.idBodega =         "
                + getIdBodega() + "                                     "
                + "       AND    tblDctosOrdenesDetalle.idPlu   != 209    "
                + "       GROUP BY tblDctosOrdenesDetalle.idPlu,          "
                + "         tblDctosOrdenesDetalle.idBodega,              "
                + "         tblDctosOrdenesDetalle.idLocalOrigen,         "
                + "         tblDctosOrdenesDetalle.idTipoOrdenOrigen,     "
                + "         tblDctosOrdenesDetalle.idOrdenOrigen)         "
                + "                                      AS tmpINV        "
                + "     INNER JOIN (                                      "
                + "     SELECT tblDctosOrdenes.idLocal,                   "
                + "            tblDctosOrdenes.idTipoOrden,               "
                + "            tblDctosOrdenes.idOrden,                   "
                + "            tblDctosOrdenes.numeroOrden,               "
                + "            tblDctosOrdenes.idFicha                    "
                + "     FROM  tblDctosOrdenes                             "
                + "     WHERE EXISTS                                      "
                + "      ( SELECT tblDctosOrdenesDetalle.*                "
                + "        FROM tblDctosOrdenesDetalle                    "
                + "        WHERE tblDctosOrdenes.IDLOCAL =                "
                + "          tblDctosOrdenesDetalle.idLocalOrigen         "
                + "        AND tblDctosOrdenes.IDTIPOORDEN =              "
                + "        tblDctosOrdenesDetalle.idTipoOrdenOrigen       "
                + "        AND tblDctosOrdenes.IDORDEN =                  "
                + "          tblDctosOrdenesDetalle.idOrdenOrigen )       "
                + " AND NOT EXISTS                                        "
                + "      ( SELECT tblDctosOrdenesDetalle.*                "
                + "        FROM tblDctosOrdenesDetalle                    "
                + "        WHERE tblDctosOrdenes.IDLOCAL =                "
                + "          tblDctosOrdenesDetalle.idLocalOrigen         "
                + "        AND tblDctosOrdenes.IDTIPOORDEN =              "
                + "        tblDctosOrdenesDetalle.idTipoOrdenOrigen       "
                + "        AND tblDctosOrdenes.IDORDEN =                  "
                + "          tblDctosOrdenesDetalle.idOrdenOrigen         "
                + "        AND tblDctosOrdenesDetalle.idTipoOrden = 18    "
                + "     AND    tblDctosOrdenesDetalle.idBodega    =       "
                + getIdBodega() + " )                                     "
                + "     AND tblDctosOrdenes.idTipoOrden = 59  )           "
                + "                                       AS tmpORD       "
                + "     ON tmpORD.idLocal                                 "
                + "                     = tmpINV.idLocalOrigen            "
                + "     AND tmpORD.idTipoOrden                            "
                + "                     = tmpINV.idTipoOrdenOrigen        "
                + "     AND tmpORD.idOrden                                "
                + "                     = tmpINV.idOrdenOrigen            "
                + "     INNER JOIN                                        "
                + "       ( SELECT tblPlus.idPlu,                         "
                + "                tblCategorias.nombreCategoria +        "
                + "                                          ' ' +        "
                + "                tblPlus.nombrePlu AS nombrePlu,        "
                + "                tblMarcas.nombreMarca                  "
                + "         FROM   tblMarcas                              "
                + "         INNER JOIN tblPlus                            "
                + "         ON tblMarcas.idMarca    =                     "
                + "                               tblPlus.idMarca         "
                + "         INNER JOIN tblCategorias                      "
                + "         ON tblPlus.idLinea      =                     "
                + "                        tblCategorias.idLinea          "
                + "         AND tblPlus.idCategoria =                     "
                + "                    tblCategorias.idCategoria)         "
                + "                                    AS tmpPLU          "
                + "     ON tmpINV.idPlu = tmpPLU.idPlu                    "
                + "    GROUP BY tmpINV.idPlu,                             "
                + "            tmpPLU.nombrePlu                           ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaPluFicha fachadaBean;

            //
            while (rs.next()) {

                //
                fachadaBean = new FachadaPluFicha();

                //
                fachadaBean.setIdPlu(rs.getInt("idPlu"));
                fachadaBean.setNombrePlu(rs.getString("nombrePlu"));
                fachadaBean.setNombreItem(rs.getString("nombreItem"));
                fachadaBean.setExistenciaCantidad(
                        rs.getDouble("existenciaCantidad"));
                fachadaBean.setExistenciaPeso(rs.getDouble("existenciaPeso"));
                fachadaBean.setNumeroOrden(rs.getInt("numeroOrden"));
                fachadaBean.setIdFicha(0);
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

    // listaOTUnOperacion
    public Vector listaOTUnOperacion() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        //
        String selectStr =
                "  SELECT tblTerceros.nombreTercero                       "
                + "        ,tblDctosOrdenes.IDLOCAL                       "
                + "        ,tblDctosOrdenes.IDTIPOORDEN                   "
                + "        ,tblDctosOrdenes.idOrden                       "
                + "        ,tblDctosOrdenes.idFicha                       "
                + "        ,tblDctosOrdenes.numeroOrden                   "
                + "        ,tmpFIC.referenciaCliente                      "
                + "        ,tmpORD.existenciaCantidad                     "
                + "        ,tmpORD.existenciaPeso                         "
                + "        ,tmpORD.idOperacion                            "
                + "        ,tblJobOperacion.nombreOperacion               "
                + "        ,tblJobEscalaDetalle.nombreItem                "
                + "        ,ISNULL(tmpTER.nombreOperario,'')              "
                + "                                  AS nombreOperario    "
                + "  FROM tblDctosOrdenes                                 "
                + "  INNER JOIN (                                         "
                + "     SELECT tblJobProgramaPlusFicha.idOperacion        "
                + "           ,tblJobProgramaPlusFicha.idOrden            "
                + "           ,tblJobProgramaPlusFicha.idFicha            "
                + "           ,tblJobProgramaPlusFicha.idEscala           "
                + "           ,tblJobProgramaPlusFicha.vrEscala,          "
                + "       MAX(tblJobProgramaPlusFicha.referenciaCliente)  "
                + "                               AS referenciaCliente    "
                + "     FROM tblJobProgramaPlusFicha                      "
                + "     WHERE tblJobProgramaPlusFicha.idEscala            "
                + "            IN ( 600, 710, 910, 1000, 1100, 1200 )     "
                + "     AND tblJobProgramaPlusFicha.idOperacion =         "
                + getIdBodega() + "                                       "
                + "     GROUP BY tblJobProgramaPlusFicha.idOperacion      "
                + "             ,tblJobProgramaPlusFicha.idOrden          "
                + "            ,tblJobProgramaPlusFicha.idFicha           "
                + "            ,tblJobProgramaPlusFicha.idEscala          "
                + "            ,tblJobProgramaPlusFicha.vrEscala )        "
                + "                                          AS tmpFIC    "
                + "  ON  tblDctosOrdenes.idFicha =  tmpFIC.idFicha        "
                + "  AND tblDctosOrdenes.idOrden =  tmpFIC.idOrden        "
                + "  INNER JOIN (                                         "
                + "    SELECT tblDctosOrdenesDetalle.idLocalOrigen        "
                + "          ,tblDctosOrdenesDetalle.idTipoORdenOrigen    "
                + "          ,tblDctosOrdenesDetalle.idOrdenOrigen        "
                + "     ,SUM(tblDctosOrdenesDetalle.cantidadTerminada     "
                + "               * tblTipoOrden.signo )                  "
                + "                              AS existenciaCantidad    "
                + "    ,SUM(tblDctosOrdenesDetalle.pesoTerminado          "
                + "                * tblTipoOrden.signo)                  "
                + "                                   AS existenciaPeso   "
                + "          ,tblDctosOrdenesDetalle.idBodega             "
                + "                                     AS idOperacion    "
                + "    FROM tblDctosOrdenesDetalle                        "
                + "    INNER JOIN tblTipoOrden                            "
                + "    ON tblTipoOrden.idTipoOrden            =           "
                + "                 tblDctosOrdenesDetalle.idTipoOrden    "
                + "    WHERE tblDctosOrdenesDetalle.idPlu     =  209      "
                + "    GROUP BY tblDctosOrdenesDetalle.idLocalOrigen      "
                + "        ,tblDctosOrdenesDetalle.IDTIPOORDENorigen      "
                + "            ,tblDctosOrdenesDetalle.IDORDENORIGEN      "
                + "            ,tblDctosOrdenesDetalle.idBodega           "
                + "    HAVING SUM(tblDctosOrdenesDetalle.pesoTerminado    "
                + "               * tblTipoOrden.signo) != 0)             "
                + "         	                               AS tmpORD  "
                + "  ON  tblDctosOrdenes.IDLOCAL     =                    "
                + "                                 tmpORD.idLocalOrigen  "
                + "  AND tblDctosOrdenes.IDTIPOORDEN =                    "
                + "                             tmpORD.IDTIPOORDENOrigen  "
                + "  AND tblDctosOrdenes.IDORDEN     =                    "
                + "                                 tmpORD.IDORDENOrigen  "
                + "  INNER JOIN tblTerceros                               "
                + "  ON  tblTerceros.idCliente       =                    "
                + "                            tblDctosOrdenes.idCliente  "
                + "  INNER JOIN tblJobOperacion                           "
                + "  ON tblJobOperacion.idOperacion =                     "
                + "                                  tmpORD.idOperacion   "
                + "  INNER JOIN tblJobEscalaDetalle                       "
                + "  ON tblJobEscalaDetalle.idEscala                      "
                + "                           = tmpFIC.idEscala           "
                + "  AND tblJobEscalaDetalle.item                         "
                + "                           = tmpFIC.vrEscala           "
                + "  LEFT JOIN                                            "
                + "  ( SELECT tblDctosOrdenesProgreso.idLocal             "
                + "        ,tblDctosOrdenesProgreso.idTipoOrden           "
                + "        ,tblDctosOrdenesProgreso.idOrden               "
                + "        ,tblDctosOrdenesProgreso.idOperacion           "
                + "        ,ISNULL(MAX(tmpOPE.nombreTercero),'')          "
                + "                            AS nombreOperario          "
                + "  FROM tblDctosOrdenesProgreso                         "
                + "  LEFT JOIN                                            "
                + "  ( SELECT tblTerceros.idTercero                       "
                + "        ,MAX(tblTerceros.nombreTercero)                "
                + "                       AS nombreTercero                "
                + "  FROM tblTerceros                                     "
                + "  GROUP BY tblTerceros.idTercero )                     "
                + "                              AS tmpOPE                "
                + "  ON tmpOPE.idTercero =                                "
                + "              tblDctosOrdenesProgreso.idOperario       "
                + "  WHERE tblDctosOrdenesProgreso.idControlTipo = 2      "
                + "  GROUP BY tblDctosOrdenesProgreso.idLocal             "
                + "        ,tblDctosOrdenesProgreso.idTipoOrden           "
                + "        ,tblDctosOrdenesProgreso.idOrden               "
                + "        ,tblDctosOrdenesProgreso.idOperacion)          "
                + "                                       AS tmpTER       "
                + "  ON  tblDctosOrdenes.idLocal     =                    "
                + "                          tmpTER.idLocal               "
                + "  AND tblDctosOrdenes.idTipoOrden =                    "
                + "                      tmpTER.idTipoOrden               "
                + "  AND tblDctosOrdenes.idOrden     =                    "
                + "                          tmpTER.idOrden               "
                + "  AND tmpORD.idOperacion          =                    "
                + "                       tmpTER.idOperacion              "
                + "  WHERE tblTerceros.idTipoTercero   = 1                "
                + "  AND   tblDctosOrdenes.idTipoOrden = 59               "
                + "  AND   tblDctosOrdenes.idLocal     =                  "
                + getIdLocal() + "                                        "
                + "  AND   tmpORD.idOperacion          =                  "
                + getIdBodega() + "                                       "
                + "  AND EXISTS (                                         "
                + "      SELECT tblJobProgramaPlusFicha.*                 "
                + "      FROM tblJobProgramaPlusFicha                     "
                + "      WHERE tblJobProgramaPlusFicha.idEscala           "
                + "            IN ( 600, 710, 910, 1000, 1100, 1200 )     "
                + "      AND tblJobProgramaPlusFicha.idOperacion =        "
                + "                              tmpORD.idOperacion       "
                + "      AND tblJobProgramaPlusFicha.idFicha     =        "
                + "                         tblDctosOrdenes.idFicha       "
                + "      AND tblJobProgramaPlusFicha.idOrden     =        "
                + "                      tblDctosOrdenes.idOrden )        "
                + "      ORDER BY tmpORD.idOperacion,                     "
                + "               tblJobEscalaDetalle.nombreItem,         "
                + "               tblDctosOrdenes.numeroOrden             ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaPluFicha fachadaBean;

            while (rs.next()) {

                //
                fachadaBean = new FachadaPluFicha();

                //
                fachadaBean.setNombreTercero(rs.getString("nombreTercero"));
                fachadaBean.setNumeroOrden(rs.getInt("numeroOrden"));
                fachadaBean.setReferenciaCliente(rs.getString("referenciaCliente"));
                fachadaBean.setExistenciaCantidad(rs.getDouble("existenciaCantidad"));
                fachadaBean.setExistenciaPeso(rs.getDouble("existenciaPeso"));
                fachadaBean.setIdOperacion(rs.getInt("idOperacion"));
                fachadaBean.setNombreOperacion(rs.getString("nombreOperacion"));
                fachadaBean.setNombreItem(rs.getString("nombreItem"));
                fachadaBean.setNombreOperario(rs.getString("nombreOperario"));

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

    // listaOTUnOperacionExtrusion
    public Vector listaOTUnOperacionExtrusion() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        //
        String selectStr =
                "  SELECT tblTerceros.nombreTercero                       "
                + "        ,tblDctosOrdenes.IDLOCAL                       "
                + "        ,tblDctosOrdenes.IDTIPOORDEN                   "
                + "        ,tblDctosOrdenes.idOrden                       "
                + "        ,tblDctosOrdenes.idFicha                       "
                + "        ,tblDctosOrdenes.numeroOrden                   "
                + "        ,tmpFIC.referenciaCliente                      "
                + "        ,tmpORD.existenciaCantidad                     "
                + "        ,tmpORD.existenciaPeso                         "
                + "        ,tmpORD.idOperacion                            "
                + "        ,tblJobOperacion.nombreOperacion               "
                + "        ,tblJobEscalaDetalle.nombreItem                "
                + "        ,ISNULL(tmpTER.nombreOperario,'')              "
                + "                                  AS nombreOperario    "
                + "  FROM tblDctosOrdenes                                 "
                + "  INNER JOIN (                                         "
                + "     SELECT tblJobProgramaPlusFicha.idOperacion        "
                + "           ,tblJobProgramaPlusFicha.idOrden            "
                + "           ,tblJobProgramaPlusFicha.idFicha            "
                + "           ,tblJobProgramaPlusFicha.idEscala           "
                + "           ,tblJobProgramaPlusFicha.vrEscala,          "
                + "       MAX(tblJobProgramaPlusFicha.referenciaCliente)  "
                + "                               AS referenciaCliente    "
                + "     FROM tblJobProgramaPlusFicha                      "
                + "     WHERE tblJobProgramaPlusFicha.idEscala            "
                + "            IN ( 600, 710, 910, 1000, 1100, 1200 )     "
                + "     AND tblJobProgramaPlusFicha.idOperacion =         "
                + getIdBodega() + "                                       "
                + "     GROUP BY tblJobProgramaPlusFicha.idOperacion      "
                + "             ,tblJobProgramaPlusFicha.idOrden          "
                + "            ,tblJobProgramaPlusFicha.idFicha           "
                + "            ,tblJobProgramaPlusFicha.idEscala          "
                + "            ,tblJobProgramaPlusFicha.vrEscala )        "
                + "                                          AS tmpFIC    "
                + "  ON  tblDctosOrdenes.idFicha =  tmpFIC.idFicha        "
                + "  AND tblDctosOrdenes.idOrden =  tmpFIC.idOrden        "
                + "  INNER JOIN (                                         "
                + "    SELECT tblDctosOrdenesDetalle.idLocalOrigen        "
                + "          ,tblDctosOrdenesDetalle.idTipoORdenOrigen    "
                + "          ,tblDctosOrdenesDetalle.idOrdenOrigen        "
                + "     ,SUM(tblDctosOrdenesDetalle.cantidadTerminada     "
                + "                * tblTipoOrden.signo )                 "
                + "                              AS existenciaCantidad    "
                + "    ,SUM(tblDctosOrdenesDetalle.pesoTerminado          "
                + "               * tblTipoOrden.signo )                  "
                + "                                   AS existenciaPeso   "
                + "          ,tblDctosOrdenesDetalle.idBodega             "
                + "                                     AS idOperacion    "
                + "    FROM tblDctosOrdenesDetalle                        "
                + "    INNER JOIN tblTipoOrden                            "
                + "    ON tblTipoOrden.idTipoOrden            =           "
                + "                 tblDctosOrdenesDetalle.idTipoOrden    "
                + "    GROUP BY tblDctosOrdenesDetalle.idLocalOrigen      "
                + "        ,tblDctosOrdenesDetalle.IDTIPOORDENorigen      "
                + "            ,tblDctosOrdenesDetalle.IDORDENORIGEN      "
                + "            ,tblDctosOrdenesDetalle.idBodega           "
                + "    HAVING SUM(tblDctosOrdenesDetalle.pesoTerminado    "
                + "               * tblTipoOrden.signo) != 0)             "
                + "         	                               AS tmpORD  "
                + "  ON  tblDctosOrdenes.IDLOCAL     =                    "
                + "                                 tmpORD.idLocalOrigen  "
                + "  AND tblDctosOrdenes.IDTIPOORDEN =                    "
                + "                             tmpORD.IDTIPOORDENOrigen  "
                + "  AND tblDctosOrdenes.IDORDEN     =                    "
                + "                                 tmpORD.IDORDENOrigen  "
                + "  INNER JOIN tblTerceros                               "
                + "  ON  tblTerceros.idCliente       =                    "
                + "                            tblDctosOrdenes.idCliente  "
                + "  INNER JOIN tblJobOperacion                           "
                + "  ON tblJobOperacion.idOperacion =                     "
                + "                                  tmpORD.idOperacion   "
                + "  INNER JOIN tblJobEscalaDetalle                       "
                + "  ON tblJobEscalaDetalle.idEscala                      "
                + "                           = tmpFIC.idEscala           "
                + "  AND tblJobEscalaDetalle.item                         "
                + "                           = tmpFIC.vrEscala           "
                + "  LEFT JOIN                                            "
                + "  ( SELECT tblDctosOrdenesProgreso.idLocal             "
                + "        ,tblDctosOrdenesProgreso.idTipoOrden           "
                + "        ,tblDctosOrdenesProgreso.idOrden               "
                + "        ,tblDctosOrdenesProgreso.idOperacion           "
                + "        ,ISNULL(MAX(tmpOPE.nombreTercero),'')          "
                + "                            AS nombreOperario          "
                + "  FROM tblDctosOrdenesProgreso                         "
                + "  LEFT JOIN                                            "
                + "  ( SELECT tblTerceros.idTercero                       "
                + "        ,MAX(tblTerceros.nombreTercero)                "
                + "                       AS nombreTercero                "
                + "  FROM tblTerceros                                     "
                + "  GROUP BY tblTerceros.idTercero )                     "
                + "                              AS tmpOPE                "
                + "  ON tmpOPE.idTercero =                                "
                + "              tblDctosOrdenesProgreso.idOperario       "
                + "  WHERE tblDctosOrdenesProgreso.idControlTipo = 2      "
                + "  GROUP BY tblDctosOrdenesProgreso.idLocal             "
                + "        ,tblDctosOrdenesProgreso.idTipoOrden           "
                + "        ,tblDctosOrdenesProgreso.idOrden               "
                + "        ,tblDctosOrdenesProgreso.idOperacion)          "
                + "                                       AS tmpTER       "
                + "  ON  tblDctosOrdenes.idLocal     =                    "
                + "                          tmpTER.idLocal               "
                + "  AND tblDctosOrdenes.idTipoOrden =                    "
                + "                      tmpTER.idTipoOrden               "
                + "  AND tblDctosOrdenes.idOrden     =                    "
                + "                          tmpTER.idOrden               "
                + "  AND tmpORD.idOperacion          =                    "
                + "                       tmpTER.idOperacion              "
                + "  WHERE tblTerceros.idTipoTercero   = 1                "
                + "  AND   tblDctosOrdenes.idTipoOrden = 59               "
                + "  AND   tblDctosOrdenes.idLocal     =                  "
                + getIdLocal() + "                                        "
                + "  AND   tmpORD.idOperacion          =                  "
                + getIdBodega() + "                                       "
                + "  AND EXISTS (                                         "
                + "      SELECT tblJobProgramaPlusFicha.*                 "
                + "      FROM tblJobProgramaPlusFicha                     "
                + "      WHERE tblJobProgramaPlusFicha.idEscala           "
                + "            IN ( 600, 710, 910, 1000, 1100, 1200 )     "
                + "      AND tblJobProgramaPlusFicha.idOperacion =        "
                + "                              tmpORD.idOperacion       "
                + "      AND tblJobProgramaPlusFicha.idFicha     =        "
                + "                         tblDctosOrdenes.idFicha       "
                + "      AND tblJobProgramaPlusFicha.idOrden     =        "
                + "                      tblDctosOrdenes.idOrden )        "
                + "      ORDER BY tmpORD.idOperacion,                     "
                + "               tblJobEscalaDetalle.nombreItem,         "
                + "               tblDctosOrdenes.numeroOrden             ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaPluFicha fachadaBean;

            while (rs.next()) {

                //
                fachadaBean = new FachadaPluFicha();

                //
                fachadaBean.setNombreTercero(rs.getString("nombreTercero"));
                fachadaBean.setNumeroOrden(rs.getInt("numeroOrden"));
                fachadaBean.setReferenciaCliente(rs.getString("referenciaCliente"));
                fachadaBean.setExistenciaCantidad(rs.getDouble("existenciaCantidad"));
                fachadaBean.setExistenciaPeso(rs.getDouble("existenciaPeso"));
                fachadaBean.setIdOperacion(rs.getInt("idOperacion"));
                fachadaBean.setNombreOperacion(rs.getString("nombreOperacion"));
                fachadaBean.setNombreItem(rs.getString("nombreItem"));
                fachadaBean.setNombreOperario(rs.getString("nombreOperario"));

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

    // listaOTTerminados
    public Vector listaOTTerminados() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        //
        String selectStr =
                "  SELECT tblTerceros.nombreTercero                       "
                + "        ,tblDctosOrdenes.IDLOCAL                       "
                + "        ,tblDctosOrdenes.IDTIPOORDEN                   "
                + "        ,tblDctosOrdenes.IDORDEN                       "
                + "        ,tblDctosOrdenes.idFicha                       "
                + "        ,tblDctosOrdenes.numeroOrden                   "
                + "        ,tmpFIC.referenciaCliente                      "
                + "        ,tmpORD.existenciaCantidad                     "
                + "        ,tmpORD.existenciaPeso                         "
                + "        ,tmpORD.idOperacion                            "
                + "        ,tblJobOperacion.nombreOperacion               "
                + "        ,'PLASTICOS UNION' AS nombreItem               "
                + "        ,'P.U.' AS nombreOperario                      "
                + "    FROM tblDctosOrdenes                               "
                + "    INNER JOIN (                                       "
                + "     SELECT 						  "
                + "       MAX(tblJobProgramaPlusFicha.referenciaCliente)  "
                + "             AS referenciaCliente                      "
                + "           ,tblJobProgramaPlusFicha.idOrden            "
                + "           ,tblJobProgramaPlusFicha.idFicha            "
                + "     FROM tblJobProgramaPlusFicha                      "
                + "     GROUP BY tblJobProgramaPlusFicha.idOrden,         "
                + "              tblJobProgramaPlusFicha.idFicha)         "
                + "                                            AS tmpFIC  "
                + "  ON  tblDctosOrdenes.idFicha =  tmpFIC.idFicha        "
                + "  AND tblDctosOrdenes.idOrden =  tmpFIC.idOrden        "
                + "  INNER JOIN (                                         "
                + "  SELECT tblDctosOrdenesDetalle.idLocal                "
                + "        ,tblDctosOrdenesDetalle.idTipoOrden            "
                + "        ,tblDctosOrdenesDetalle.idOrden                "
                + "     ,SUM(tblDctosOrdenesDetalle.cantidadTerminada -   "
                + "          tblDctosOrdenesDetalle.cantidadFacturada)    "
                + "                             AS existenciaCantidad     "
                + "    , SUM(tblDctosOrdenesDetalle.pesoTerminado     -   "
                + "         tblDctosOrdenesDetalle.pesoFacturado)         "
                + "                               AS existenciaPeso,      "
                + getIdBodega() + " AS idOperacion                        "
                + "    FROM tblDctosOrdenesDetalle                        "
                + "  GROUP BY tblDctosOrdenesDetalle.idLocal              "
                + "        ,tblDctosOrdenesDetalle.IDTIPOORDEN            "
                + "        ,tblDctosOrdenesDetalle.IDORDEN                "
                + "        ,tblDctosOrdenesDetalle.idOperacion            "
                + "  HAVING SUM(tblDctosOrdenesDetalle.pesoTerminado -    "
                + "         tblDctosOrdenesDetalle.pesoFacturado)         "
                + "                                  > 0)   AS tmpORD     "
                + "  ON  tblDctosOrdenes.IDLOCAL     =                    "
                + "                               tmpORD.idLocal          "
                + "  AND tblDctosOrdenes.IDTIPOORDEN =                    "
                + "                           tmpORD.IDTIPOORDEN          "
                + "  AND tblDctosOrdenes.IDORDEN     =                    "
                + "                               tmpORD.IDORDEN          "
                + "  INNER JOIN tblTerceros                               "
                + "  ON  tblTerceros.idCliente       =                    "
                + "                       tblDctosOrdenes.idCliente       "
                + " INNER JOIN tblJobOperacion                            "
                + "  ON tblJobOperacion.idOperacion =                     "
                + "                              tmpORD.idOperacion       "
                + "  WHERE tblTerceros.idTipoTercero   = 1                "
                + "  AND   tblDctosOrdenes.idTipoOrden = 59               "
                + "  AND   tblDctosOrdenes.idLocal     =                  "
                + getIdLocal() + "                                        "
                + "  AND   tmpORD.idOperacion          =                  "
                + getIdBodega() + "                                       "
                + "  ORDER BY tmpORD.idOperacion,                         "
                + "          tblDctosOrdenes.numeroOrden                  ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaPluFicha fachadaBean;

            //
            while (rs.next()) {

                //
                fachadaBean = new FachadaPluFicha();

                //
                fachadaBean.setNombreTercero(rs.getString("nombreTercero"));
                fachadaBean.setNumeroOrden(rs.getInt("numeroOrden"));
                fachadaBean.setReferenciaCliente(rs.getString("referenciaCliente"));
                fachadaBean.setExistenciaCantidad(rs.getDouble("existenciaCantidad"));
                fachadaBean.setExistenciaPeso(rs.getDouble("existenciaPeso"));
                fachadaBean.setIdOperacion(rs.getInt("idOperacion"));
                fachadaBean.setNombreOperacion(rs.getString("nombreOperacion"));
                fachadaBean.setNombreItem(rs.getString("nombreItem"));
                fachadaBean.setNombreOperario(rs.getString("nombreOperario"));

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

    // listaMPRetal
    public Vector listaMPRetal() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        //
        String selectStr =
                "  SELECT tmpPLU.idPlu                                    "
                + "        ,MAX(tmpPLU.nombrePlu) AS nombrePlu            "
                + "        ,tmpORD.idOperacion                            "
                + "        ,0 AS numeroOrden                              "
                + "        ,0 AS idFicha                                  "
                + "        ,MAX(tblJobOperacion.nombreOperacion)          "
                + "                          AS nombreOperacion           "
                + "        ,SUM( tmpORD.existenciaCantidad *              "
                + "           tmpMAT.porcentajeMaterial )                 "
                + "                 AS existenciaCantidad                 "
                + "        ,SUM( tmpORD.existenciaPeso *                  "
                + "           tmpMAT.porcentajeMaterial )                 "
                + "                 AS existenciaPeso                     "
                + "    FROM tblDctosOrdenes                               "
                + "  INNER JOIN (                                         "
                + "     SELECT MAX(referenciaCliente)                     "
                + "             AS referenciaCliente                      "
                + "           ,idFicha                                    "
                + "     FROM tblJobProgramaPlusFicha                      "
                + "     GROUP BY idFicha ) AS tmpFIC                      "
                + "  ON  tblDctosOrdenes.idFicha =                        "
                + "                   tmpFIC.idFicha                      "
                + "  INNER JOIN (                                         "
                + "  SELECT tblDctosOrdenesDetalle.idLocalOrigen          "
                + "        ,tblDctosOrdenesDetalle.idTipoORdenOrigen      "
                + "        ,tblDctosOrdenesDetalle.idOrdenOrigen          "
                + "        ,SUM(tblDctosOrdenesDetalle.cantidad * (-1))   "
                + "                               AS existenciaCantidad   "
                + "    ,SUM(tblDctosOrdenesDetalle.pesoTerminado * (-1))  "
                + "                               AS existenciaPeso       "
                + "        ,tblDctosOrdenesDetalle.idOperacion            "
                + "    FROM tblDctosOrdenesDetalle                        "
                + "  WHERE tblDctosOrdenesDetalle.idPlu           =209    "
                + "  GROUP BY tblDctosOrdenesDetalle.idLocalOrigen        "
                + "        ,tblDctosOrdenesDetalle.IDTIPOORDENorigen      "
                + "        ,tblDctosOrdenesDetalle.IDORDENORIGEN          "
                + "        ,tblDctosOrdenesDetalle.idOperacion)           "
                + "                                         AS tmpORD     "
                + "  ON  tblDctosOrdenes.IDLOCAL     =                    "
                + "                               tmpORD.idLocalOrigen    "
                + "  AND tblDctosOrdenes.IDTIPOORDEN =                    "
                + "                           tmpORD.IDTIPOORDENOrigen    "
                + "  AND tblDctosOrdenes.IDORDEN     =                    "
                + "                               tmpORD.IDORDENOrigen    "
                + "  INNER JOIN tblTerceros                               "
                + "  ON  tblTerceros.idCliente       =                    "
                + "                          tblDctosOrdenes.idCliente    "
                + "  INNER JOIN tblJobOperacion                           "
                + "  ON tblJobOperacion.idOperacion =                     "
                + "                                 tmpORD.idOperacion    "
                + " INNER JOIN                                            "
                + " (SELECT  tblJobProgramaPlusFicha.idFicha,             "
                + "          tblJobProgramaPlusFicha.idEscala,            "
                + "        (  tblJobProgramaPlusFicha.vrEscala /          "
                + "           tmpFIC.cantidaMaterial )                    "
                + "              AS porcentajeMaterial                    "
                + "  FROM tblJobProgramaPlusFicha                         "
                + " INNER JOIN                                            "
                + "  (SELECT tmpFCH.idFicha,                              "
                + "          SUM(tmpFCH.vrEscala)                         "
                + "                 AS cantidaMaterial                    "
                + "   FROM tblJobProgramaPlusFicha tmpFCH                 "
                + "   WHERE tmpFCH.idEscala                               "
                + "                 IN (611,612,613,614,617)              "
                + "   GROUP BY tmpFCH.idFicha                             "
                + "   HAVING SUM(tmpFCH.vrEscala) != 0 )                  "
                + "                            AS tmpFIC                  "
                + " ON tmpFIC.idFicha          =                          "
                + "                 tblJobProgramaPlusFicha.idFicha       "
                + " WHERE tblJobProgramaPlusFicha.idFicha =               "
                + "                       tmpFIC.idFicha                  "
                + " AND tblJobProgramaPlusFicha.idEscala                  "
                + "                 IN (611,612,613,614,617)              "
                + " AND tblJobProgramaPlusFicha.vrEscala != 0 ) AS tmpMAT "
                + " ON tmpMAT.idFicha = tblDctosOrdenes.idFicha           "
                + " INNER JOIN                                            "
                + " ( SELECT tblPlus.idPlu,                               "
                + " (tblCategorias.nombrecategoria + '  ' + tblPlus.nombrePlu) AS nombrePlu,   "
                + "        tblPlusOperacion.idEscala                      "
                + " FROM   tblCategorias                                  "
                + " INNER JOIN tblPlus                                    "
                + " ON tblCategorias.idLinea      =                       "
                + "                  tblPlus.idLinea                      "
                + " AND tblCategorias.IdCategoria =                       "
                + "              tblPlus.idCategoria                      "
                + " INNER JOIN tblPlusOperacion                           "
                + " ON tblPlus.idPlu              =                       "
                + "           tblPlusOperacion.idPlu                      "
                + " WHERE tblPlusOperacion.idEscala                       "
                + "              IN (611,612,613,614,617) )               "
                + "                               AS tmpPLU               "
                + " ON tmpPLU.idEscala = tmpMAT.idEscala                  "
                + "  WHERE tblTerceros.idTipoTercero   = 1                "
                + "  AND   tblDctosOrdenes.idTipoOrden = 59               "
                + "  AND   tblDctosOrdenes.idLocal     =                  "
                + getIdLocal() + "                                        "
                + "  AND   tmpORD.idOperacion          =                  "
                + getIdBodega() + "                                       "
                + " GROUP BY tmpPLU.idPlu                                 "
                + "        ,tmpPLU.nombrePlu                              "
                + "        ,tmpORD.idOperacion                            ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaPluFicha fachadaBean;

            while (rs.next()) {

                //
                fachadaBean = new FachadaPluFicha();

                //
                fachadaBean.setIdPlu(rs.getInt("idPlu"));
                fachadaBean.setNombrePlu(rs.getString("nombrePlu"));
                fachadaBean.setNombreOperacion(rs.getString("nombreOperacion"));
                fachadaBean.setIdOperacion(rs.getInt("idOperacion"));
                fachadaBean.setExistenciaCantidad(rs.getDouble("existenciaCantidad"));
                fachadaBean.setExistenciaPeso(rs.getDouble("existenciaPeso"));
                fachadaBean.setNumeroOrden(rs.getInt("numeroOrden"));
                fachadaBean.setIdFicha(rs.getInt("idFicha"));

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
    // listaMPRetal
    public Vector listaMPRetalInv() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        //
        String selectStr =
                  " SELECT tblPlus.idPlu,                                   \n"
                + "  (tblCategorias.nombrecategoria + '  ' + "
                + "tblPlus.nombrePlu) AS nombrePlu,                         \n"
                + "  tblPlusOperacion.idEscala,                             \n"
                + "  tblPlusInventario.idBodega,                            \n"
                + "  tblPlusInventario.existencia                           \n"
                + "  FROM   tblCategorias                                   \n"
                + "  INNER JOIN tblPlus                                     \n"
                + "  ON tblCategorias.idLinea      = tblPlus.idLinea        \n"
                + "  AND tblCategorias.IdCategoria =   tblPlus.idCategoria  \n"
                + "  INNER JOIN tblPlusOperacion                            \n"
                + "  ON tblPlus.idPlu   =   tblPlusOperacion.idPlu          \n"
                + "  INNER JOIN tblPlusInventario                           \n"
                + "  ON tblPlus.idplu =  tblPlusInventario.idPlu            \n"
                + "  WHERE tblPlusOperacion.idEscala    "
                + "IN (611,612,613,614,617,615)                             \n"
                + "  AND tblPlusInventario.idBodega = "
                + getIdBodega() + "                                       ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaPluFicha fachadaBean;

            while (rs.next()) {

                //
                fachadaBean = new FachadaPluFicha();

                //
                fachadaBean.setIdPlu(rs.getInt("idPlu"));
                fachadaBean.setNombrePlu(rs.getString("nombrePlu"));
                fachadaBean.setIdOperacion(rs.getInt("idBodega"));
                fachadaBean.setExistenciaCantidad(rs.getDouble("existencia"));
                

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

    // listaMPRetalMaquina
    public Vector listaMPRetalMaquina() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        //
        String selectStr =
                "  SELECT  tblDctosOrdenes.numeroOrden AS idPlu           "
                + "        ,MAX(tmpFIC.referenciaCliente) AS nombrePlu    "
                + "        ,tmpORD.idOperacion                            "
                + "        ,tblDctosOrdenes.numeroOrden                   "
                + "        ,0 AS idFicha                                  "
                + "        ,MAX(tblJobOperacion.nombreOperacion)          "
                + "                          AS nombreOperacion           "
                + "        ,SUM( tmpORD.existenciaCantidad *              "
                + "           tmpMAT.porcentajeMaterial )                 "
                + "                 AS existenciaCantidad                 "
                + "        ,SUM( tmpORD.existenciaPeso *                  "
                + "           tmpMAT.porcentajeMaterial )                 "
                + "                 AS existenciaPeso                     "
                + "    FROM tblDctosOrdenes                               "
                + "  INNER JOIN (                                         "
                + "     SELECT MAX(referenciaCliente)                     "
                + "             AS referenciaCliente                      "
                + "           ,idFicha                                    "
                + "     FROM tblJobProgramaPlusFicha                      "
                + "     GROUP BY idFicha ) AS tmpFIC                      "
                + "  ON  tblDctosOrdenes.idFicha =                        "
                + "                   tmpFIC.idFicha                      "
                + "  INNER JOIN (                                         "
                + "  SELECT tblDctosOrdenesDetalle.idLocalOrigen          "
                + "        ,tblDctosOrdenesDetalle.idTipoORdenOrigen      "
                + "        ,tblDctosOrdenesDetalle.idOrdenOrigen          "
                + "        ,SUM(tblDctosOrdenesDetalle.cantidad * (-1))   "
                + "                               AS existenciaCantidad   "
                + "    ,SUM(tblDctosOrdenesDetalle.pesoTerminado * (-1))  "
                + "                               AS existenciaPeso       "
                + "        ,tblDctosOrdenesDetalle.idOperacion            "
                + "    FROM tblDctosOrdenesDetalle                        "
                + "  WHERE tblDctosOrdenesDetalle.idPlu           =209    "
                + "  GROUP BY tblDctosOrdenesDetalle.idLocalOrigen        "
                + "        ,tblDctosOrdenesDetalle.IDTIPOORDENorigen      "
                + "        ,tblDctosOrdenesDetalle.IDORDENORIGEN          "
                + "        ,tblDctosOrdenesDetalle.idOperacion)           "
                + "                                         AS tmpORD     "
                + "  ON  tblDctosOrdenes.IDLOCAL     =                    "
                + "                               tmpORD.idLocalOrigen    "
                + "  AND tblDctosOrdenes.IDTIPOORDEN =                    "
                + "                           tmpORD.IDTIPOORDENOrigen    "
                + "  AND tblDctosOrdenes.IDORDEN     =                    "
                + "                               tmpORD.IDORDENOrigen    "
                + "  INNER JOIN tblTerceros                               "
                + "  ON  tblTerceros.idCliente       =                    "
                + "                          tblDctosOrdenes.idCliente    "
                + "  INNER JOIN tblJobOperacion                           "
                + "  ON tblJobOperacion.idOperacion =                     "
                + "                                 tmpORD.idOperacion    "
                + " INNER JOIN                                            "
                + " (SELECT  tblJobProgramaPlusFicha.idFicha,             "
                + "          tblJobProgramaPlusFicha.idEscala,            "
                + "        (  tblJobProgramaPlusFicha.vrEscala /          "
                + "           tmpFIC.cantidaMaterial )                    "
                + "              AS porcentajeMaterial                    "
                + "  FROM tblJobProgramaPlusFicha                         "
                + " INNER JOIN                                            "
                + "  (SELECT tmpFCH.idFicha,                              "
                + "          SUM(tmpFCH.vrEscala)                         "
                + "                 AS cantidaMaterial                    "
                + "   FROM tblJobProgramaPlusFicha tmpFCH                 "
                + "   WHERE tmpFCH.idEscala                               "
                + "                 IN (611,612,613,614,617)              "
                + "   GROUP BY tmpFCH.idFicha                             "
                + "   HAVING SUM(tmpFCH.vrEscala) != 0 )                  "
                + "                            AS tmpFIC                  "
                + " ON tmpFIC.idFicha          =                          "
                + "                 tblJobProgramaPlusFicha.idFicha       "
                + " WHERE tblJobProgramaPlusFicha.idFicha =               "
                + "                       tmpFIC.idFicha                  "
                + " AND tblJobProgramaPlusFicha.idEscala                  "
                + "                 IN (611,612,613,614,617)              "
                + " AND tblJobProgramaPlusFicha.vrEscala != 0 ) AS tmpMAT "
                + " ON tmpMAT.idFicha = tblDctosOrdenes.idFicha           "
                + " INNER JOIN                                            "
                + " ( SELECT tblPlus.idPlu,                               "
                + "        tblPlus.nombrePlu,                             "
                + "        tblPlusOperacion.idEscala                      "
                + " FROM   tblCategorias                                  "
                + " INNER JOIN tblPlus                                    "
                + " ON tblCategorias.idLinea      =                       "
                + "                  tblPlus.idLinea                      "
                + " AND tblCategorias.IdCategoria =                       "
                + "              tblPlus.idCategoria                      "
                + " INNER JOIN tblPlusOperacion                           "
                + " ON tblPlus.idPlu              =                       "
                + "           tblPlusOperacion.idPlu                      "
                + " WHERE tblPlusOperacion.idEscala                       "
                + "              IN (611,612,613,614,617) )               "
                + "                               AS tmpPLU               "
                + " ON tmpPLU.idEscala = tmpMAT.idEscala                  "
                + "  WHERE tblTerceros.idTipoTercero   = 1                "
                + "  AND   tblDctosOrdenes.idTipoOrden = 59               "
                + "  AND   tblDctosOrdenes.idLocal     =                  "
                + getIdLocal() + "                                        "
                + "  AND   tmpORD.idOperacion          =                  "
                + getIdBodega() + "                                       "
                + " GROUP BY tblDctosOrdenes.numeroOrden                  "
                + "        ,tmpORD.idOperacion                            ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaPluFicha fachadaBean;

            while (rs.next()) {

                //
                fachadaBean = new FachadaPluFicha();

                //
                fachadaBean.setIdPlu(rs.getInt("idPlu"));
                fachadaBean.setNombrePlu(rs.getString("nombrePlu"));
                fachadaBean.setNombreItem("");
                fachadaBean.setNombreOperacion(rs.getString("nombreOperacion"));
                fachadaBean.setIdOperacion(rs.getInt("idOperacion"));
                fachadaBean.setExistenciaCantidad(rs.getDouble("existenciaCantidad"));
                fachadaBean.setExistenciaPeso(rs.getDouble("existenciaPeso"));
                fachadaBean.setNumeroOrden(rs.getInt("numeroOrden"));
                fachadaBean.setIdFicha(rs.getInt("idFicha"));

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
