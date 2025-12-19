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

public class ColaboraResurtidoOrden extends FachadaColaboraDctoOrdenBean
        implements Serializable, IConstantes {

    // Nombre JNDI del Data Source que esta clase requiere
    private static final String DATA_SOURCE_NAME = "CommerceAccess";
    // Clase helper usada para acceder a la base de datos
    private JDBCAccess jdbcAccess;

    // Metodo constructor por defecto sin parametros
    public ColaboraResurtidoOrden() {

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
        FachadaColaboraDctoOrdenBean fachadaBean;

        Connection connection = null;

        String selectStr =
                "SELECT   tblDctosOrdenes.IDLOCAL,      	"
                + "         tblDctosOrdenes.IDTIPOORDEN,  	"
                + "         tblDctosOrdenes.IDORDEN,		"
                + "         tblDctosOrdenes.FECHAORDEN,		"
                + "         tblDctosOrdenes.idCliente,	    	"
                + "         tblDctosOrdenes.IDUSUARIO,	   	"
                + "         tblDctosOrdenes.IDLOG,		"
                + "         tblDctosOrdenes.FECHAENTREGA,	"
                + "         tblDctosOrdenes.diasHistoria,	"
                + "         tblDctosOrdenes.diasInventario,	"
                + "  ( SELECT TOP 1 tblTerceros.nombreTercero   "
                + "          FROM tblTerceros                   "
                + "          WHERE tblTerceros.idCliente =      "
                + "                tblDctosOrdenes.idCliente ) 	"
                + "                          AS nombreTercero,  "
                + "        ( SELECT ctrlUsuarios.nombreUsuario  "
                + "          FROM ctrlUsuarios                  "
                + "          WHERE ctrlUsuarios.idUsuario =     "
                + "                   tblDctosOrdenes.IDUSUARIO)"
                + "                          AS nombreUsuario,	"
                + " SUM(tblDctosOrdenesDetalle.cantidadPedida * "
                + "    tblDctosOrdenesDetalle.vrCostoNegociado) "
                + "                                vrCostoBase,	"
                + "       COUNT(*) AS cantidadArticulos		"
                + "FROM tblDctosOrdenes				"
                + "INNER JOIN tblDctosOrdenesDetalle		"
                + "ON tblDctosOrdenes.IDLOCAL      = 		"
                + "              tblDctosOrdenesDetalle.IDLOCAL	"
                + "AND tblDctosOrdenes.IDTIPOORDEN = 		"
                + "          tblDctosOrdenesDetalle.IDTIPOORDEN	"
                + "AND tblDctosOrdenes.IDORDEN     = 		"
                + "              tblDctosOrdenesDetalle.IDORDEN	"
                + "AND  NOT EXISTS                              "
                + "    ( SELECT tblDctos.*                      "
                + "      FROM tblDctos                          "
                + "      WHERE tblDctos.idLocal      =          "
                + "                   tblDctosOrdenes.idLocal   "
                + "    AND    tblDctos.idTipoOrden =            "
                + "            tblDctosOrdenes.IDTIPOORDEN      "
                + "    AND    tblDctos.idOrden     =            "
                + "                 tblDctosOrdenes.idOrden)    "
                + "AND EXISTS                                   "
                + "    ( SELECT tblAgendaLogVisitas.*           "
                + "      FROM   tblAgendaLogVisitas             "
                + "      WHERE tblAgendaLogVisitas.idLog     =  "
                + "                     tblDctosOrdenes.idLog   "
                + "    AND   tblAgendaLogVisitas.estado   != 1) "
                + "AND tblDctosOrdenes.IDLOCAL       =          "
                + getIdLocal() + "                              "
                + "AND tblDctosOrdenes.IDTIPOORDEN   =          "
                + getIdTipoOrden() + "                          "
                + "GROUP BY tblDctosOrdenes.IDLOCAL,      	"
                + "         tblDctosOrdenes.IDTIPOORDEN,  	"
                + "         tblDctosOrdenes.IDORDEN,		"
                + "         tblDctosOrdenes.FECHAORDEN,		"
                + "         tblDctosOrdenes.idCliente,		"
                + "         tblDctosOrdenes.IDUSUARIO,		"
                + "         tblDctosOrdenes.IDLOG,		"
                + "         tblDctosOrdenes.FECHAENTREGA,	"
                + "         tblDctosOrdenes.diasHistoria,	"
                + "         tblDctosOrdenes.diasInventario	"
                + "ORDER BY tblDctosOrdenes.IDLOG DESC          ";
   
        
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
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("IdTipoOrden"));
                fachadaBean.setIdOrden(rs.getInt("IdOrden"));
                fachadaBean.setFechaOrden(rs.getString("fechaOrden"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setIdUsuario(rs.getDouble("idUsuario"));
                fachadaBean.setIdLog(rs.getInt("idLog"));
                fachadaBean.setFechaEntrega(rs.getString("fechaEntrega"));
                fachadaBean.setDiasHistoria(rs.getInt("diasHistoria"));
                fachadaBean.setDiasInventario(rs.getInt("diasInventario"));
                fachadaBean.setNombreTercero(rs.getString("nombreTercero"));
                fachadaBean.setNombreUsuario(rs.getString("nombreUsuario"));
                fachadaBean.setVrCostoBase(rs.getDouble("vrCostoBase"));
                fachadaBean.setCantidadArticulos(
                        rs.getInt("cantidadArticulos"));

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

    // listaLegalizaTercero
    public Vector listaLegalizaTercero() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        // Variable de fachada de los datos
        FachadaColaboraDctoOrdenBean fachadaBean;

        Connection connection = null;

        String selectStr =
                "SELECT   tblDctosOrdenes.IDLOCAL,      	"
                + "         tblDctosOrdenes.IDTIPOORDEN,  	"
                + "         tblDctosOrdenes.IDORDEN,		"
                + "         tblDctosOrdenes.FECHAORDEN,		"
                + "         tblDctosOrdenes.idCliente,	    	"
                + "         tblDctosOrdenes.IDUSUARIO,	   	"
                + "         tblDctosOrdenes.IDLOG,		"
                + "         tblDctosOrdenes.FECHAENTREGA,	"
                + "         tblDctosOrdenes.diasHistoria,	"
                + "         tblDctosOrdenes.diasInventario,	"
                + "  ( SELECT TOP 1 tblTerceros.nombreTercero   "
                + "          FROM tblTerceros                   "
                + "          WHERE tblTerceros.idCliente =      "
                + "                tblDctosOrdenes.idCliente ) 	"
                + "                          AS nombreTercero,  "
                + "        ( SELECT ctrlUsuarios.nombreUsuario  "
                + "          FROM ctrlUsuarios                  "
                + "          WHERE ctrlUsuarios.idUsuario =     "
                + "                   tblDctosOrdenes.IDUSUARIO)"
                + "                          AS nombreUsuario,	"
                + " SUM(tblDctosOrdenesDetalle.cantidadPedida * "
                + "    tblDctosOrdenesDetalle.vrCostoNegociado) "
                + "                                vrCostoBase,	"
                + "       COUNT(*) AS cantidadArticulos		"
                + "FROM tblDctosOrdenes				"
                + "INNER JOIN tblDctosOrdenesDetalle		"
                + "ON tblDctosOrdenes.IDLOCAL      = 		"
                + "              tblDctosOrdenesDetalle.IDLOCAL	"
                + "AND tblDctosOrdenes.IDTIPOORDEN = 		"
                + "          tblDctosOrdenesDetalle.IDTIPOORDEN	"
                + "AND tblDctosOrdenes.IDORDEN     = 		"
                + "              tblDctosOrdenesDetalle.IDORDEN	"
                + "AND  NOT EXISTS                              "
                + "    ( SELECT tblDctos.*                      "
                + "      FROM tblDctos                          "
                + "      WHERE tblDctos.idLocal      =          "
                + "                   tblDctosOrdenes.idLocal   "
                + "    AND    tblDctos.idTipoOrden =            "
                + "            tblDctosOrdenes.IDTIPOORDEN      "
                + "    AND    tblDctos.idOrden     =            "
                + "                 tblDctosOrdenes.idOrden)    "
                + "AND EXISTS                                   "
                + "    ( SELECT tblAgendaLogVisitas.*           "
                + "      FROM   tblAgendaLogVisitas             "
                + "      WHERE tblAgendaLogVisitas.idLog     =  "
                + "                     tblDctosOrdenes.idLog   "
                + "    AND   tblAgendaLogVisitas.estado   != 1) "
                + "AND tblDctosOrdenes.IDLOCAL       =          "
                + getIdLocal() + "                              "
                + "AND tblDctosOrdenes.IDTIPOORDEN   =          "
                + getIdTipoOrden() + "                          "
                + "AND tblDctosOrdenes.idCliente     =         '"
                + getIdCliente() + "'                           "
                + "GROUP BY tblDctosOrdenes.IDLOCAL,      	"
                + "         tblDctosOrdenes.IDTIPOORDEN,  	"
                + "         tblDctosOrdenes.IDORDEN,		"
                + "         tblDctosOrdenes.FECHAORDEN,		"
                + "         tblDctosOrdenes.idCliente,		"
                + "         tblDctosOrdenes.IDUSUARIO,		"
                + "         tblDctosOrdenes.IDLOG,		"
                + "         tblDctosOrdenes.FECHAENTREGA,	"
                + "         tblDctosOrdenes.diasHistoria,	"
                + "         tblDctosOrdenes.diasInventario	"
                + "ORDER BY tblDctosOrdenes.IDLOG DESC          ";


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
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("IdTipoOrden"));
                fachadaBean.setIdOrden(rs.getInt("IdOrden"));
                fachadaBean.setFechaOrden(rs.getString("fechaOrden"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setIdUsuario(rs.getDouble("idUsuario"));
                fachadaBean.setIdLog(rs.getInt("idLog"));
                fachadaBean.setFechaEntrega(rs.getString("fechaEntrega"));
                fachadaBean.setDiasHistoria(rs.getInt("diasHistoria"));
                fachadaBean.setDiasInventario(rs.getInt("diasInventario"));
                fachadaBean.setNombreTercero(rs.getString("nombreTercero"));
                fachadaBean.setNombreUsuario(rs.getString("nombreUsuario"));
                fachadaBean.setVrCostoBase(rs.getDouble("vrCostoBase"));
                fachadaBean.setCantidadArticulos(
                        rs.getInt("cantidadArticulos"));

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
    public Vector listaOrdenResurtido(int xIdLocalOrigen) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        // Variable de fachada de los datos
        FachadaColaboraDctoOrdenBean fachadaBean;

        Connection connection = null;

        String selectStr =
          "            SELECT   tblDctosOrdenes.IDLOCAL,      	   "
         +"                 tblDctosOrdenes.IDTIPOORDEN,  	   "
         +"                 tblDctosOrdenes.IDORDEN,		   "
         +"                 tblDctosOrdenes.FECHAORDEN,		   "
         +"                 tblDctosOrdenes.idCliente,	    	   "
         +"                 tblDctosOrdenes.IDUSUARIO,	   	   "
         +"                 tblDctosOrdenes.IDLOG,		   "
         +"                 tblDctosOrdenes.FECHAENTREGA,	   "
         +"                 tblDctosOrdenes.diasHistoria,	   "
         +"                 tblDctosOrdenes.diasInventario,	   "
         +"                 tblDctosOrdenesDetalle.idOrdenOrigen,  "
         +"          ( SELECT TOP 1 tblTerceros.nombreTercero      "
         +"                  FROM tblTerceros                      "
         +"                  WHERE tblTerceros.idCliente =         "
         +"                        tblDctosOrdenes.idCliente ) 	   "
         +"                                  AS nombreTercero,     "
         +"                ( SELECT ctrlUsuarios.nombreUsuario     "
         +"                  FROM ctrlUsuarios                     "
         +"                  WHERE ctrlUsuarios.idUsuario =        "
         +"                           tblDctosOrdenes.IDUSUARIO)   "
         +"                                  AS nombreUsuario,	   "
         +"         SUM(tblDctosOrdenesDetalle.cantidadPedida *    "
         +"            tblDctosOrdenesDetalle.vrCostoNegociado)    "
         +"                                        vrCostoBase,	   "
         +"               COUNT(*) AS cantidadArticulos		   "
         +"        FROM tblDctosOrdenes				   "
         +"        INNER JOIN tblDctosOrdenesDetalle		   "
         +"        ON tblDctosOrdenes.IDLOCAL      = 		   "
         +"                      tblDctosOrdenesDetalle.IDLOCAL	   "
         +"        AND tblDctosOrdenes.IDTIPOORDEN = 		   "
         +"                  tblDctosOrdenesDetalle.IDTIPOORDEN	   "
         +"        AND tblDctosOrdenes.IDORDEN     = 		   "
         +"                      tblDctosOrdenesDetalle.IDORDEN	   "
         +"        AND  NOT EXISTS                                 "
         +"            ( SELECT tblDctos.*                         "
         +"              FROM tblDctos                             "
         +"              WHERE tblDctos.idLocal      =             "
         +"                           tblDctosOrdenes.idLocal      "
         +"            AND    tblDctos.idTipoOrden =               "
         +"                    tblDctosOrdenes.IDTIPOORDEN         "
         +"            AND    tblDctos.idOrden     =               "
         +"                         tblDctosOrdenes.idOrden)       "
         +"        AND EXISTS                                      "
         +"            ( SELECT tblAgendaLogVisitas.*              "
         +"              FROM   tblAgendaLogVisitas                "
         +"              WHERE tblAgendaLogVisitas.idLog     =     "
         +"                             tblDctosOrdenes.idLog      "
         +"            AND   tblAgendaLogVisitas.estado   != 1)    "
         +"        AND tblDctosOrdenes.IDLOCAL       =             "
         +         getIdLocal() + "                                "
         +"        AND tblDctosOrdenes.IDTIPOORDEN   =             "
         +         getIdTipoOrden() + "                            "
         +"        AND tblDctosOrdenesDetalle.idLocalOrigen =      "
         +         xIdLocalOrigen + "                              "
         +"        GROUP BY tblDctosOrdenes.IDLOCAL,      	   "
         +"                 tblDctosOrdenes.IDTIPOORDEN,  	   "
         +"                 tblDctosOrdenes.IDORDEN,		   "
         +"                 tblDctosOrdenes.FECHAORDEN,		   "
         +"                 tblDctosOrdenes.idCliente,		   "
         +"                 tblDctosOrdenes.IDUSUARIO,		   "
         +"                 tblDctosOrdenes.IDLOG,		   "
         +"                 tblDctosOrdenes.FECHAENTREGA,	   "
         +"                 tblDctosOrdenes.diasHistoria,	   "
         +"                 tblDctosOrdenes.diasInventario,	   "
         +"                 tblDctosOrdenesDetalle.idOrdenOrigen   "
         +"        ORDER BY tblDctosOrdenes.IDLOG DESC             ";


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
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("IdTipoOrden"));
                fachadaBean.setIdOrden(rs.getInt("IdOrden"));
                fachadaBean.setFechaOrden(rs.getString("fechaOrden"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setIdUsuario(rs.getDouble("idUsuario"));
                fachadaBean.setIdLog(rs.getInt("idLog"));
                fachadaBean.setFechaEntrega(rs.getString("fechaEntrega"));
                fachadaBean.setDiasHistoria(rs.getInt("diasHistoria"));
                fachadaBean.setDiasInventario(rs.getInt("diasInventario"));
                fachadaBean.setNombreTercero(rs.getString("nombreTercero"));
                fachadaBean.setNombreUsuario(rs.getString("nombreUsuario"));
                fachadaBean.setVrCostoBase(rs.getDouble("vrCostoBase"));
                fachadaBean.setCantidadArticulos(rs.getInt("cantidadArticulos"));
                fachadaBean.setIdOrdenOrigen(rs.getInt("IdOrdenOrigen"));
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
        FachadaColaboraDctoOrdenBean fachadaBean;

        Connection connection = null;

        String selectStr =
                "SELECT   tblDctosOrdenes.IDLOCAL,                      "
                + "         tblDctosOrdenes.IDTIPOORDEN,                  "
                + "         tblDctosOrdenes.IDORDEN,                      "
                + "         tblDctosOrdenes.FECHAORDEN,                   "
                + "         tblDctosOrdenes.idCliente,                    "
                + "         tblDctosOrdenes.IDUSUARIO,                    "
                + "         tblDctosOrdenes.IDLOG,                        "
                + "         tblDctosOrdenes.FECHAENTREGA,                 "
                + "         tblDctosOrdenes.diasHistoria,                 "
                + "         tblDctosOrdenes.diasInventario,               "
                + "         (SELECT  tblTerceros.nombreTercero            "
                + "          FROM    tblTerceros                          "
                + "          WHERE  (tblTerceros.idCliente =              "
                + "                tblDctosOrdenes.idCliente))            "
                + "                          AS nombreTercero,            "
                + "         (SELECT  tblLocales.nombreLocal               "
                + "          FROM    tblLocales                           "
                + "          WHERE  (tblLocales.idLocal    =              "
                + "                tblDctosOrdenes.idCliente))            "
                + "                          AS nombreLocal,              "
                + "         (SELECT     nombreUsuario                     "
                + "          FROM  ctrlUsuarios                           "
                + "          WHERE  (ctrlUsuarios.idUsuario =             "
                + "                           tblDctosOrdenes.IDUSUARIO)) "
                + "                                     AS nombreUsuario, "
                + "          SUM(tblDctosOrdenesDetalle.CANTIDADPEDIDA *  "
                + "              tblDctosOrdenesDetalle.vrCostoNegociado) "
                + "              AS vrCostoBase,                          "
                + "          COUNT(*) AS cantidadArticulos,               "
                + "          MAX(tblDctos.idDcto) AS idDcto,              "
                + "          MAX(tblDctos.vrCostoMV) AS vrCostoMV         "
                + "FROM  tblDctosOrdenes                                  "
                + "INNER JOIN tblDctosOrdenesDetalle                      "
                + "ON  tblDctosOrdenes.IDLOCAL     =                      "
                + "                        tblDctosOrdenesDetalle.IDLOCAL "
                + "AND tblDctosOrdenes.IDTIPOORDEN =                      "
                + "                    tblDctosOrdenesDetalle.IDTIPOORDEN "
                + "AND tblDctosOrdenes.IDORDEN     =                      "
                + "                        tblDctosOrdenesDetalle.IDORDEN "
                + "AND tblDctosOrdenes.IDLOCAL     =                      "
                + getIdLocal() + "                                        "
                + "AND tblDctosOrdenes.IDTIPOORDEN =                      "
                + getIdTipoOrden() + "                                    "
                + "INNER JOIN tblDctos                                    "
                + "ON tblDctosOrdenes.IDLOCAL = tblDctos.IDLOCAL          "
                + "AND tblDctosOrdenes.IDTIPOORDEN = tblDctos.IDTIPOORDEN "
                + "AND tblDctosOrdenes.IDORDEN     = tblDctos.IDORDEN     "
                + "GROUP BY tblDctosOrdenes.IDLOCAL,                      "
                + "         tblDctosOrdenes.IDTIPOORDEN,                  "
                + "         tblDctosOrdenes.IDORDEN,                      "
                + "         tblDctosOrdenes.FECHAORDEN,                   "
                + "         tblDctosOrdenes.idCliente,                    "
                + "         tblDctosOrdenes.IDUSUARIO,                    "
                + "         tblDctosOrdenes.IDLOG,                        "
                + "         tblDctosOrdenes.FECHAENTREGA,                 "
                + "         tblDctosOrdenes.diasHistoria,                 "
                + "         tblDctosOrdenes.diasInventario                "
                + "ORDER BY tblDctosOrdenes.IDLOG DESC                    ";

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
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("IdTipoOrden"));
                fachadaBean.setIdOrden(rs.getInt("IdOrden"));
                fachadaBean.setFechaOrden(rs.getString("fechaOrden"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setIdUsuario(rs.getDouble("idUsuario"));
                fachadaBean.setIdLog(rs.getInt("idLog"));
                fachadaBean.setFechaEntrega(rs.getString("fechaEntrega"));
                fachadaBean.setDiasHistoria(rs.getInt("diasHistoria"));
                fachadaBean.setDiasInventario(rs.getInt("diasInventario"));
                fachadaBean.setNombreTercero(rs.getString("nombreTercero"));
                fachadaBean.setNombreLocal(rs.getString("nombreLocal"));
                fachadaBean.setNombreUsuario(rs.getString("nombreUsuario"));
                fachadaBean.setVrCostoBase(rs.getDouble("vrCostoBase"));
                fachadaBean.setCantidadArticulos(
                        rs.getInt("cantidadArticulos"));
                fachadaBean.setIdDcto(rs.getInt("idDcto"));
                fachadaBean.setVrCostoMV(rs.getDouble("vrCostoMV"));

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

    // listaOrdenCompraUn
    public Vector listaOrdenCompraUn() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        // Variable de fachada de los datos
        FachadaColaboraDctoOrdenBean fachadaBean;

        Connection connection = null;

        String selectStr =
                "SELECT   tblDctosOrdenes.IDLOCAL,                        "
                + "         tblDctosOrdenes.IDTIPOORDEN,                  "
                + "         tblDctosOrdenes.IDORDEN,                      "
                + "         tblDctosOrdenes.FECHAORDEN,                   "
                + "         tblDctosOrdenes.idCliente,                    "
                + "         tblDctosOrdenes.IDUSUARIO,                    "
                + "         tblDctosOrdenes.IDLOG,                        "
                + "         tblDctosOrdenes.FECHAENTREGA,                 "
                + "         tblDctosOrdenes.diasHistoria,                 "
                + "         tblDctosOrdenes.diasInventario,               "
                + "         ( SELECT  tblAgendaLogVisitas.estado          "
                + "           FROM    tblAgendaLogVisitas                 "
                + "           WHERE   tblAgendaLogVisitas.idLog =         "
                + "                    tblDctosOrdenes.idLog ) AS estado, "
                + "         ( SELECT  tblAgendaLogVisitas.idEstadoVisita  "
                + "           FROM    tblAgendaLogVisitas                 "
                + "           WHERE   tblAgendaLogVisitas.idLog =         "
                + "            tblDctosOrdenes.idLog ) AS idEstadoVisita, "
                + "         (SELECT  TOP 1 tblTerceros.nombreTercero      "
                + "          FROM    tblTerceros                          "
                + "          WHERE  (tblTerceros.idCliente =              "
                + "                tblDctosOrdenes.idCliente))            "
                + "                          AS nombreTercero,            "
                + "         (SELECT  TOP 1 tblLocales.nombreLocal         "
                + "          FROM    tblLocales                           "
                + "          WHERE  tblLocales.idLocal    =               "
                + "                          tblDctosOrdenes.IDLOCAL)     "
                + "                          AS nombreLocal,              "
                + "         (SELECT  TOP 1 nombreUsuario                  "
                + "          FROM  ctrlUsuarios                           "
                + "          WHERE  (ctrlUsuarios.idUsuario =             "
                + "                           tblDctosOrdenes.IDUSUARIO)) "
                + "                                     AS nombreUsuario, "
                + "          SUM(tblDctosOrdenesDetalle.CANTIDADPEDIDA *  "
                + "              tblDctosOrdenesDetalle.vrCostoNegociado) "
                + "              AS vrCostoBase,                          "
                + "          COUNT(*) AS cantidadArticulos,               "
                + "          MAX(tblDctos.idDcto) AS idDcto,              "
                + "          MAX(tblDctos.idDctoNitCC) AS idDctoNitCC,    "
                + "          MAX(tblDctos.vrCostoMV) AS vrCostoMV         "
                + "FROM  tblDctosOrdenes                                  "
                + "INNER JOIN tblDctosOrdenesDetalle                      "
                + "ON  tblDctosOrdenes.IDLOCAL     =                      "
                + "                        tblDctosOrdenesDetalle.IDLOCAL "
                + "AND tblDctosOrdenes.IDTIPOORDEN =                      "
                + "                    tblDctosOrdenesDetalle.IDTIPOORDEN "
                + "AND tblDctosOrdenes.IDORDEN     =                      "
                + "                        tblDctosOrdenesDetalle.IDORDEN "
                + "AND tblDctosOrdenes.IDLOCAL     =                      "
                + getIdLocal() + "                                        "
                + "AND tblDctosOrdenes.IDTIPOORDEN =                      "
                + getIdTipoOrden() + "                                    "
                + "AND tblDctosOrdenes.idCliente   =                     '"
                + getIdCliente() + "'                                     "
                + "INNER JOIN tblDctos                                    "
                + "ON tblDctosOrdenes.IDLOCAL      = tblDctos.IDLOCAL     "
                + "AND tblDctosOrdenes.IDTIPOORDEN = tblDctos.IDTIPOORDEN "
                + "AND tblDctosOrdenes.IDORDEN     = tblDctos.IDORDEN     "
                + "GROUP BY tblDctosOrdenes.IDLOCAL,                      "
                + "         tblDctosOrdenes.IDTIPOORDEN,                  "
                + "         tblDctosOrdenes.IDORDEN,                      "
                + "         tblDctosOrdenes.FECHAORDEN,                   "
                + "         tblDctosOrdenes.idCliente,                    "
                + "         tblDctosOrdenes.IDUSUARIO,                    "
                + "         tblDctosOrdenes.IDLOG,                        "
                + "         tblDctosOrdenes.FECHAENTREGA,                 "
                + "         tblDctosOrdenes.diasHistoria,                 "
                + "         tblDctosOrdenes.diasInventario,               "
                + "         tblDctosOrdenes.estado                        "
                + "ORDER BY tblDctosOrdenes.IDLOG DESC                    ";

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
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("IdTipoOrden"));
                fachadaBean.setIdOrden(rs.getInt("IdOrden"));
                fachadaBean.setFechaOrden(rs.getString("fechaOrden"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setIdUsuario(rs.getDouble("idUsuario"));
                fachadaBean.setIdLog(rs.getInt("idLog"));
                fachadaBean.setFechaEntrega(rs.getString("fechaEntrega"));
                fachadaBean.setDiasHistoria(rs.getInt("diasHistoria"));
                fachadaBean.setDiasInventario(rs.getInt("diasInventario"));
                fachadaBean.setNombreTercero(rs.getString("nombreTercero"));
                fachadaBean.setNombreLocal(rs.getString("nombreLocal"));
                fachadaBean.setNombreUsuario(rs.getString("nombreUsuario"));
                fachadaBean.setVrCostoBase(rs.getDouble("vrCostoBase"));
                fachadaBean.setCantidadArticulos(
                        rs.getInt("cantidadArticulos"));
                fachadaBean.setIdDcto(rs.getInt("idDcto"));
                fachadaBean.setIdDctoNitCC(rs.getString("idDctoNitCC"));
                fachadaBean.setVrCostoMV(rs.getDouble("vrCostoMV"));
                fachadaBean.setEstado(rs.getInt("estado"));
                fachadaBean.setIdEstadoVisita(rs.getInt("idEstadoVisita"));

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

    // listaOrdenCompraAll
    public Vector listaOrdenCompraAll() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        // Variable de fachada de los datos
        FachadaColaboraDctoOrdenBean fachadaBean;

        Connection connection = null;

        String selectStr =
                "SELECT   tblDctosOrdenes.IDLOCAL,                      "
                + "         tblDctosOrdenes.IDTIPOORDEN,                  "
                + "         tblDctosOrdenes.IDORDEN,                      "
                + "         tblDctosOrdenes.FECHAORDEN,                   "
                + "         tblDctosOrdenes.idCliente,                    "
                + "         tblDctosOrdenes.IDUSUARIO,                    "
                + "         tblDctosOrdenes.IDLOG,                        "
                + "         tblDctosOrdenes.FECHAENTREGA,                 "
                + "         tblDctosOrdenes.diasHistoria,                 "
                + "         tblDctosOrdenes.diasInventario,               "
                + "         ( SELECT  tblAgendaLogVisitas.estado          "
                + "           FROM    tblAgendaLogVisitas                 "
                + "           WHERE   tblAgendaLogVisitas.idLog =         "
                + "                    tblDctosOrdenes.idLog ) AS estado, "
                + "         ( SELECT  tblAgendaLogVisitas.idEstadoVisita  "
                + "           FROM    tblAgendaLogVisitas                 "
                + "           WHERE   tblAgendaLogVisitas.idLog =         "
                + "            tblDctosOrdenes.idLog ) AS idEstadoVisita, "
                + "         (SELECT  TOP 1 tblTerceros.nombreTercero      "
                + "          FROM    tblTerceros                          "
                + "          WHERE  (tblTerceros.idCliente =              "
                + "                tblDctosOrdenes.idCliente))            "
                + "                          AS nombreTercero,            "
                + "         (SELECT  TOP 1 tblLocales.nombreLocal         "
                + "          FROM    tblLocales                           "
                + "          WHERE  tblLocales.idLocal    =               "
                + "                          tblDctosOrdenes.IDLOCAL)     "
                + "                          AS nombreLocal,              "
                + "         (SELECT  TOP 1 nombreUsuario                  "
                + "          FROM  ctrlUsuarios                           "
                + "          WHERE  (ctrlUsuarios.idUsuario =             "
                + "                           tblDctosOrdenes.IDUSUARIO)) "
                + "                                     AS nombreUsuario, "
                + "          SUM(tblDctosOrdenesDetalle.CANTIDADPEDIDA *  "
                + "              tblDctosOrdenesDetalle.vrCostoNegociado) "
                + "              AS vrCostoBase,                          "
                + "          COUNT(*) AS cantidadArticulos                "
                + "FROM  tblDctosOrdenes                                  "
                + "INNER JOIN tblDctosOrdenesDetalle                      "
                + "ON  tblDctosOrdenes.IDLOCAL     =                      "
                + "                        tblDctosOrdenesDetalle.IDLOCAL "
                + "AND tblDctosOrdenes.IDTIPOORDEN =                      "
                + "                    tblDctosOrdenesDetalle.IDTIPOORDEN "
                + "AND tblDctosOrdenes.IDORDEN     =                      "
                + "                        tblDctosOrdenesDetalle.IDORDEN "
                + "AND tblDctosOrdenes.IDLOCAL     =                      "
                + getIdLocal() + "                                        "
                + "AND tblDctosOrdenes.IDTIPOORDEN =                      "
                + getIdTipoOrden() + "                                    "
                + "AND NOT EXISTS ( SELECT tblDctos.*                     "
                + "                 FROM tblDctos                         "
                + "                 WHERE tblDctosOrdenes.IDLOCAL    =    "
                + "                                    tblDctos.IDLOCAL   "
                + "                 AND tblDctosOrdenes.IDTIPOORDEN  =    "
                + "                                  tblDctos.IDTIPOORDEN "
                + "                 AND tblDctosOrdenes.IDORDEN      =    "
                + "                                     tblDctos.IDORDEN) "
                + "GROUP BY tblDctosOrdenes.IDLOCAL,                      "
                + "         tblDctosOrdenes.IDTIPOORDEN,                  "
                + "         tblDctosOrdenes.IDORDEN,                      "
                + "         tblDctosOrdenes.FECHAORDEN,                   "
                + "         tblDctosOrdenes.idCliente,                    "
                + "         tblDctosOrdenes.IDUSUARIO,                    "
                + "         tblDctosOrdenes.IDLOG,                        "
                + "         tblDctosOrdenes.FECHAENTREGA,                 "
                + "         tblDctosOrdenes.diasHistoria,                 "
                + "         tblDctosOrdenes.diasInventario,               "
                + "         tblDctosOrdenes.estado                        "
                + "ORDER BY tblDctosOrdenes.IDLOG DESC                    ";
        
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
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("IdTipoOrden"));
                fachadaBean.setIdOrden(rs.getInt("IdOrden"));
                fachadaBean.setFechaOrden(rs.getString("fechaOrden"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setIdUsuario(rs.getDouble("idUsuario"));
                fachadaBean.setIdLog(rs.getInt("idLog"));
                fachadaBean.setFechaEntrega(rs.getString("fechaEntrega"));
                fachadaBean.setDiasHistoria(rs.getInt("diasHistoria"));
                fachadaBean.setDiasInventario(rs.getInt("diasInventario"));
                fachadaBean.setNombreTercero(rs.getString("nombreTercero"));
                fachadaBean.setNombreLocal(rs.getString("nombreLocal"));
                fachadaBean.setNombreUsuario(rs.getString("nombreUsuario"));
                fachadaBean.setVrCostoBase(rs.getDouble("vrCostoBase"));
                fachadaBean.setCantidadArticulos(
                        rs.getInt("cantidadArticulos"));
                fachadaBean.setEstado(rs.getInt("estado"));
                fachadaBean.setIdEstadoVisita(rs.getInt("idEstadoVisita"));

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

    // listaResurtido
    public Vector listaResurtido() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        // Variable de fachada de los datos
        FachadaColaboraDctoOrdenBean fachadaBean;

        Connection connection = null;

        String selectStr =
                "SELECT   tblDctosOrdenes.IDLOCAL,                      "
                + "         tblDctosOrdenes.IDTIPOORDEN,                  "
                + "         tblDctosOrdenes.IDORDEN,                      "
                + "         tblDctosOrdenes.FECHAORDEN,                   "
                + "         tblDctosOrdenes.idCliente,                    "
                + "         tblDctosOrdenes.IDUSUARIO,                    "
                + "         tblDctosOrdenes.IDLOG,                        "
                + "         tblDctosOrdenes.FECHAENTREGA,                 "
                + "         tblDctosOrdenes.diasHistoria,                 "
                + "         tblDctosOrdenes.diasInventario,               "
                + "         (SELECT  tblLocales.nombreLocal               "
                + "          FROM    tblLocales                           "
                + "          WHERE  (tblLocales.idLocal    =              "
                + "                tblDctosOrdenes.idCliente))            "
                + "                          AS nombreTercero,            "
                + "         (SELECT  tblLocales.nombreLocal               "
                + "          FROM    tblLocales                           "
                + "          WHERE  tblLocales.idLocal    =               "
                + "                          tblDctosOrdenes.IDLOCAL)     "
                + "                          AS nombreLocal,              "
                + "         (SELECT     nombreUsuario                     "
                + "          FROM  ctrlUsuarios                           "
                + "          WHERE  (ctrlUsuarios.idUsuario =             "
                + "                           tblDctosOrdenes.IDUSUARIO)) "
                + "                                     AS nombreUsuario, "
                + "          SUM(tblDctosOrdenesDetalle.CANTIDADPEDIDA *  "
                + "              tblDctosOrdenesDetalle.vrCostoNegociado) "
                + "              AS vrCostoBase,                          "
                + "          COUNT(*) AS cantidadArticulos,               "
                + "          MAX(tblDctos.idDcto) AS idDcto,              "
                + "          MAX(tblDctos.idDctoNitCC) AS idDctoNitCC,    "
                + "          MAX(tblDctos.vrCostoMV) AS vrCostoMV         "
                + "FROM  tblDctosOrdenes                                  "
                + "INNER JOIN tblDctosOrdenesDetalle                      "
                + "ON  tblDctosOrdenes.IDLOCAL     =                      "
                + "                        tblDctosOrdenesDetalle.IDLOCAL "
                + "AND tblDctosOrdenes.IDTIPOORDEN =                      "
                + "                    tblDctosOrdenesDetalle.IDTIPOORDEN "
                + "AND tblDctosOrdenes.IDORDEN     =                      "
                + "                        tblDctosOrdenesDetalle.IDORDEN "
                + "AND tblDctosOrdenes.IDLOCAL     =                      "
                + getIdLocal() + "                                        "
                + "AND tblDctosOrdenes.IDTIPOORDEN =                      "
                + getIdTipoOrden() + "                                    "
                + "INNER JOIN tblDctos                                    "
                + "ON tblDctosOrdenes.IDLOCAL = tblDctos.IDLOCAL          "
                + "AND tblDctosOrdenes.IDTIPOORDEN = tblDctos.IDTIPOORDEN "
                + "AND tblDctosOrdenes.IDORDEN     = tblDctos.IDORDEN     "
                + "GROUP BY tblDctosOrdenes.IDLOCAL,                      "
                + "         tblDctosOrdenes.IDTIPOORDEN,                  "
                + "         tblDctosOrdenes.IDORDEN,                      "
                + "         tblDctosOrdenes.FECHAORDEN,                   "
                + "         tblDctosOrdenes.idCliente,                    "
                + "         tblDctosOrdenes.IDUSUARIO,                    "
                + "         tblDctosOrdenes.IDLOG,                        "
                + "         tblDctosOrdenes.FECHAENTREGA,                 "
                + "         tblDctosOrdenes.diasHistoria,                 "
                + "         tblDctosOrdenes.diasInventario                "
                + "ORDER BY tblDctosOrdenes.IDLOG DESC                    ";

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
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("IdTipoOrden"));
                fachadaBean.setIdOrden(rs.getInt("IdOrden"));
                fachadaBean.setFechaOrden(rs.getString("fechaOrden"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setIdUsuario(rs.getDouble("idUsuario"));
                fachadaBean.setIdLog(rs.getInt("idLog"));
                fachadaBean.setFechaEntrega(rs.getString("fechaEntrega"));
                fachadaBean.setDiasHistoria(rs.getInt("diasHistoria"));
                fachadaBean.setDiasInventario(rs.getInt("diasInventario"));
                fachadaBean.setNombreTercero(rs.getString("nombreTercero"));
                fachadaBean.setNombreLocal(rs.getString("nombreLocal"));
                fachadaBean.setNombreUsuario(rs.getString("nombreUsuario"));
                fachadaBean.setVrCostoBase(rs.getDouble("vrCostoBase"));
                fachadaBean.setCantidadArticulos(
                        rs.getInt("cantidadArticulos"));
                fachadaBean.setIdDcto(rs.getInt("idDcto"));
                fachadaBean.setIdDctoNitCC(rs.getString("idDctoNitCC"));
                fachadaBean.setVrCostoMV(rs.getDouble("vrCostoMV"));

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

    // listaResurtidoPeriodo
    public Vector listaResurtidoPeriodo() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        // Variable de fachada de los datos
        FachadaColaboraDctoOrdenBean fachadaBean;

        Connection connection = null;

        String selectStr =
                "SELECT   tblDctosOrdenes.IDLOCAL,                      "
                + "         tblDctosOrdenes.IDTIPOORDEN,                  "
                + "         tblDctosOrdenes.IDORDEN,                      "
                + "         tblDctosOrdenes.FECHAORDEN,                   "
                + "         tblDctosOrdenes.idCliente,                    "
                + "         tblDctosOrdenes.IDUSUARIO,                    "
                + "         tblDctosOrdenes.IDLOG,                        "
                + "         tblDctosOrdenes.FECHAENTREGA,                 "
                + "         tblDctosOrdenes.diasHistoria,                 "
                + "         tblDctosOrdenes.diasInventario,               "
                + "         (SELECT  tblLocales.nombreLocal               "
                + "          FROM    tblLocales                           "
                + "          WHERE  (tblLocales.idLocal    =              "
                + "                tblDctosOrdenes.idCliente))            "
                + "                          AS nombreTercero,            "
                + "         (SELECT  tblLocales.nombreLocal               "
                + "          FROM    tblLocales                           "
                + "          WHERE  tblLocales.idLocal    =               "
                + "                          tblDctosOrdenes.IDLOCAL)     "
                + "                          AS nombreLocal,              "
                + "         (SELECT     nombreUsuario                     "
                + "          FROM  ctrlUsuarios                           "
                + "          WHERE  (ctrlUsuarios.idUsuario =             "
                + "                           tblDctosOrdenes.IDUSUARIO)) "
                + "                                     AS nombreUsuario, "
                + "          SUM(tblDctosOrdenesDetalle.CANTIDADPEDIDA *  "
                + "              tblDctosOrdenesDetalle.vrCostoNegociado) "
                + "              AS vrCostoBase,                          "
                + "          COUNT(*) AS cantidadArticulos,               "
                + "          MAX(tblDctos.idDcto) AS idDcto,              "
                + "          MAX(tblDctos.idDctoNitCC) AS idDctoNitCC,    "
                + "          MAX(tblDctos.vrCostoMV) AS vrCostoMV         "
                + "FROM  tblDctosOrdenes                                  "
                + "INNER JOIN tblDctosOrdenesDetalle                      "
                + "ON  tblDctosOrdenes.IDLOCAL     =                      "
                + "                        tblDctosOrdenesDetalle.IDLOCAL "
                + "AND tblDctosOrdenes.IDTIPOORDEN =                      "
                + "                    tblDctosOrdenesDetalle.IDTIPOORDEN "
                + "AND tblDctosOrdenes.IDORDEN     =                      "
                + "                        tblDctosOrdenesDetalle.IDORDEN "
                + "AND tblDctosOrdenes.IDLOCAL     =                      "
                + getIdLocal() + "                                        "
                + "AND tblDctosOrdenes.IDTIPOORDEN IN                    ("
                + getIdTipoOrdenCadena() + ")                             "
                + "AND tblDctosOrdenes.FECHAORDEN BETWEEN                '"
                + getFechaInicialSqlServer() + "'  AND                   '"
                + getFechaFinalSqlServer() + "'                           "
                + "INNER JOIN tblDctos                                    "
                + "ON tblDctosOrdenes.IDLOCAL = tblDctos.IDLOCAL          "
                + "AND tblDctosOrdenes.IDTIPOORDEN = tblDctos.IDTIPOORDEN "
                + "AND tblDctosOrdenes.IDORDEN     = tblDctos.IDORDEN     "
                + "GROUP BY tblDctosOrdenes.IDLOCAL,                      "
                + "         tblDctosOrdenes.IDTIPOORDEN,                  "
                + "         tblDctosOrdenes.IDORDEN,                      "
                + "         tblDctosOrdenes.FECHAORDEN,                   "
                + "         tblDctosOrdenes.idCliente,                    "
                + "         tblDctosOrdenes.IDUSUARIO,                    "
                + "         tblDctosOrdenes.IDLOG,                        "
                + "         tblDctosOrdenes.FECHAENTREGA,                 "
                + "         tblDctosOrdenes.diasHistoria,                 "
                + "         tblDctosOrdenes.diasInventario                "
                + "ORDER BY tblDctosOrdenes.IDLOCAL,                      "
                + "         tblDctosOrdenes.IDTIPOORDEN,                  "
                + "         tblDctosOrdenes.IDORDEN                       ";

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
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("IdTipoOrden"));
                fachadaBean.setIdOrden(rs.getInt("IdOrden"));
                fachadaBean.setFechaOrden(rs.getString("fechaOrden"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setIdUsuario(rs.getDouble("idUsuario"));
                fachadaBean.setIdLog(rs.getInt("idLog"));
                fachadaBean.setFechaEntrega(rs.getString("fechaEntrega"));
                fachadaBean.setDiasHistoria(rs.getInt("diasHistoria"));
                fachadaBean.setDiasInventario(rs.getInt("diasInventario"));
                fachadaBean.setNombreTercero(rs.getString("nombreTercero"));
                fachadaBean.setNombreLocal(rs.getString("nombreLocal"));
                fachadaBean.setNombreUsuario(rs.getString("nombreUsuario"));
                fachadaBean.setVrCostoBase(rs.getDouble("vrCostoBase"));
                fachadaBean.setCantidadArticulos(
                        rs.getInt("cantidadArticulos"));
                fachadaBean.setIdDcto(rs.getInt("idDcto"));
                fachadaBean.setIdDctoNitCC(rs.getString("idDctoNitCC"));
                fachadaBean.setVrCostoMV(rs.getDouble("vrCostoMV"));

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

    // listaLegalizaFCH
    public FachadaColaboraDctoOrdenBean listaLegalizaFCH() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        // Variable de fachada de los datos
        FachadaColaboraDctoOrdenBean fachadaBean = new FachadaColaboraDctoOrdenBean();

        Connection connection = null;

        String selectStr =
                "SELECT   tblDctosOrdenes.IDLOCAL,      		"
                + "         tblDctosOrdenes.IDTIPOORDEN,  		"
                + "         tblDctosOrdenes.IDORDEN,		        "
                + "         tblDctosOrdenes.FECHAORDEN,		    "
                + "         tblDctosOrdenes.idCliente,	    	"
                + "         tblDctosOrdenes.IDUSUARIO,	   	    "
                + "         tblDctosOrdenes.IDLOG,			    "
                + "         tblDctosOrdenes.FECHAENTREGA,		    "
                + "         tblDctosOrdenes.diasHistoria,		    "
                + "         tblDctosOrdenes.diasInventario,	    "
                + "        ( SELECT tblLocales.nombreLocal        "
                + "          FROM tblLocales                      "
                + "          WHERE tblLocales.idLocal    =        "
                + "                  tblDctosOrdenes.idLocal ) 	"
                + "                          AS nombreTercero,    "
                + "        ( SELECT ctrlUsuarios.nombreUsuario    "
                + "          FROM ctrlUsuarios                    "
                + "          WHERE ctrlUsuarios.idUsuario =       "
                + "                   tblDctosOrdenes.IDUSUARIO)	"
                + "                          AS nombreUsuario,	"
                + "   SUM(tblDctosOrdenesDetalle.cantidadPedida * "
                + "      tblDctosOrdenesDetalle.vrCostoNegociado) "
                + "                               vrCostoBase,	"
                + "         COUNT(*) AS cantidadArticulos		    "
                + "FROM tblDctosOrdenes				            "
                + "INNER JOIN tblDctosOrdenesDetalle		        "
                + "ON tblDctosOrdenes.IDLOCAL      = 		        "
                + "              tblDctosOrdenesDetalle.IDLOCAL	"
                + "AND tblDctosOrdenes.IDTIPOORDEN = 		        "
                + "          tblDctosOrdenesDetalle.IDTIPOORDEN	"
                + "AND tblDctosOrdenes.IDORDEN     = 		        "
                + "              tblDctosOrdenesDetalle.IDORDEN	"
                + "AND  NOT EXISTS                                "
                + "    ( SELECT tblDctos.*                        "
                + "      FROM tblDctos                            "
                + "      WHERE tblDctos.idLocal      =            "
                + "                     tblDctosOrdenes.idLocal   "
                + "      AND    tblDctos.idTipoOrden =            "
                + "              tblDctosOrdenes.IDTIPOORDEN      "
                + "      AND    tblDctos.idOrden     =            "
                + "                   tblDctosOrdenes.idOrden)    "
                + "AND tblDctosOrdenes.IDLOCAL     =              "
                + getIdLocal() + "                                "
                + "AND tblDctosOrdenes.IDTIPOORDEN =              "
                + getIdTipoOrden() + "                            "
                + "AND tblDctosOrdenes.idLog       =              "
                + getIdLog() + "                                  "
                + "GROUP BY tblDctosOrdenes.IDLOCAL,      		"
                + "         tblDctosOrdenes.IDTIPOORDEN,  		"
                + "         tblDctosOrdenes.IDORDEN,		        "
                + "         tblDctosOrdenes.FECHAORDEN,		    "
                + "         tblDctosOrdenes.idCliente,		    "
                + "         tblDctosOrdenes.IDUSUARIO,		    "
                + "         tblDctosOrdenes.IDLOG,			    "
                + "         tblDctosOrdenes.FECHAENTREGA,		    "
                + "         tblDctosOrdenes.diasHistoria,		    "
                + "         tblDctosOrdenes.diasInventario	    "
                + "ORDER BY tblDctosOrdenes.IDLOG DESC            ";

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
                fachadaBean.setIdTipoOrden(rs.getInt("IdTipoOrden"));
                fachadaBean.setIdOrden(rs.getInt("IdOrden"));
                fachadaBean.setFechaOrden(rs.getString("fechaOrden"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setIdUsuario(rs.getDouble("idUsuario"));
                fachadaBean.setIdLog(rs.getInt("idLog"));
                fachadaBean.setFechaEntrega(rs.getString("fechaEntrega"));
                fachadaBean.setDiasHistoria(rs.getInt("diasHistoria"));
                fachadaBean.setDiasInventario(rs.getInt("diasInventario"));
                fachadaBean.setNombreTercero(rs.getString("nombreTercero"));
                fachadaBean.setNombreUsuario(rs.getString("nombreUsuario"));
                fachadaBean.setVrCostoBase(rs.getDouble("vrCostoBase"));
                fachadaBean.setCantidadArticulos(
                        rs.getInt("cantidadArticulos"));

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
            return fachadaBean;

        }
    }



    // listaLegalizaCompraFCH
    public FachadaColaboraDctoOrdenBean listaLegalizaCompraFCH() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        // Variable de fachada de los datos
        FachadaColaboraDctoOrdenBean fachadaBean
                                           = new FachadaColaboraDctoOrdenBean();

        Connection connection = null;

        String selectStr =
                "SELECT   tblDctosOrdenes.IDLOCAL,      	  "
                + "         tblDctosOrdenes.IDTIPOORDEN,  	  "
                + "         tblDctosOrdenes.IDORDEN,		  "
                + "         tblDctosOrdenes.FECHAORDEN,		  "
                + "         tblDctosOrdenes.idCliente,	    	  "
                + "         tblDctosOrdenes.IDUSUARIO,	   	  "
                + "         tblDctosOrdenes.IDLOG,		  "
                + "         tblDctosOrdenes.fechaEntrega,	  "
                + "         tblDctosOrdenes.diasHistoria,	  "
                + "         tblDctosOrdenes.diasInventario,	  "
                + "         MAX(tblDctosOrdenes.observacion)      "
                + "                             AS observacion,	  "
                + "  ( SELECT TOP 1 tblTerceros.nombreTercero     "
                + "          FROM tblTerceros                     "
                + "          WHERE tblTerceros.idCliente  =       "
                + "                  tblDctosOrdenes.idCliente )  "
                + "                          AS nombreTercero,    "
                + "        ( SELECT ctrlUsuarios.nombreUsuario    "
                + "          FROM ctrlUsuarios                    "
                + "          WHERE ctrlUsuarios.idUsuario =       "
                + "                   tblDctosOrdenes.IDUSUARIO)  "
                + "                          AS nombreUsuario,	  "
                + "   SUM(tblDctosOrdenesDetalle.cantidadPedida * "
                + "      tblDctosOrdenesDetalle.vrCostoNegociado) "
                + "                               vrCostoBase,	  "
                + "         COUNT(*) AS cantidadArticulos	  "
                + "FROM tblDctosOrdenes				  "
                + "INNER JOIN tblDctosOrdenesDetalle		  "
                + "ON tblDctosOrdenes.IDLOCAL      = 		  "
                + "              tblDctosOrdenesDetalle.IDLOCAL	  "
                + "AND tblDctosOrdenes.IDTIPOORDEN = 		  "
                + "          tblDctosOrdenesDetalle.IDTIPOORDEN	  "
                + "AND tblDctosOrdenes.IDORDEN     = 		  "
                + "              tblDctosOrdenesDetalle.IDORDEN	  "
                + "AND  NOT EXISTS                                "
                + "    ( SELECT tblDctos.*                        "
                + "      FROM tblDctos                            "
                + "      WHERE tblDctos.idLocal      =            "
                + "                     tblDctosOrdenes.idLocal   "
                + "      AND    tblDctos.idTipoOrden =            "
                + "              tblDctosOrdenes.IDTIPOORDEN      "
                + "      AND    tblDctos.idOrden     =            "
                + "                   tblDctosOrdenes.idOrden)    "
                + "AND tblDctosOrdenes.IDLOCAL     =              "
                + getIdLocal() + "                                "
                + "AND tblDctosOrdenes.IDTIPOORDEN =              "
                + getIdTipoOrden() + "                            "
                + "AND tblDctosOrdenes.idLog       =              "
                + getIdLog() + "                                  "
                + "GROUP BY tblDctosOrdenes.IDLOCAL,      	  "
                + "         tblDctosOrdenes.IDTIPOORDEN,  	  "
                + "         tblDctosOrdenes.IDORDEN,		  "
                + "         tblDctosOrdenes.FECHAORDEN,		  "
                + "         tblDctosOrdenes.idCliente,		  "
                + "         tblDctosOrdenes.IDUSUARIO,		  "
                + "         tblDctosOrdenes.IDLOG,		  "
                + "         tblDctosOrdenes.FECHAENTREGA,	  "
                + "         tblDctosOrdenes.diasHistoria,	  "
                + "         tblDctosOrdenes.diasInventario	  "
                + "ORDER BY tblDctosOrdenes.IDLOG DESC            ";


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
                fachadaBean.setIdTipoOrden(rs.getInt("IdTipoOrden"));
                fachadaBean.setIdOrden(rs.getInt("IdOrden"));
                fachadaBean.setFechaOrden(rs.getString("fechaOrden"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setIdUsuario(rs.getDouble("idUsuario"));
                fachadaBean.setIdLog(rs.getInt("idLog"));
                fachadaBean.setFechaEntrega(rs.getString("fechaEntrega"));
                fachadaBean.setDiasHistoria(rs.getInt("diasHistoria"));
                fachadaBean.setDiasInventario(rs.getInt("diasInventario"));
                fachadaBean.setObservacion(rs.getString("observacion"));
                fachadaBean.setNombreTercero(rs.getString("nombreTercero"));
                fachadaBean.setNombreUsuario(rs.getString("nombreUsuario"));
                fachadaBean.setVrCostoBase(rs.getDouble("vrCostoBase"));
                fachadaBean.setCantidadArticulos(
                        rs.getInt("cantidadArticulos"));

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
            return fachadaBean;

        }
    }

    // listaAjusteFCH
    public FachadaColaboraDctoOrdenBean listaAjusteFCH() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        // Variable de fachada de los datos
        FachadaColaboraDctoOrdenBean fachadaBean = new FachadaColaboraDctoOrdenBean();

        Connection connection = null;

        String selectStr =
                "SELECT   tblDctosOrdenes.IDLOCAL,      		"
                + "         tblDctosOrdenes.IDTIPOORDEN,  		"
                + "         tblDctosOrdenes.IDORDEN,		        "
                + "         tblDctosOrdenes.FECHAORDEN,		    "
                + "         tblDctosOrdenes.idCliente,	    	"
                + "         tblDctosOrdenes.IDUSUARIO,	   	    "
                + "         tblDctosOrdenes.IDLOG,			    "
                + "         tblDctosOrdenes.FECHAENTREGA,		    "
                + "         tblDctosOrdenes.diasHistoria,		    "
                + "         tblDctosOrdenes.diasInventario,	    "
                + "        ( SELECT tblLocales.nombreLocal        "
                + "          FROM tblLocales                      "
                + "          WHERE tblLocales.idLocal    =        "
                + "                tblDctosOrdenes.idCliente ) 	"
                + "                          AS nombreTercero,    "
                + "        ( SELECT ctrlUsuarios.nombreUsuario    "
                + "          FROM ctrlUsuarios                    "
                + "          WHERE ctrlUsuarios.idUsuario =       "
                + "                   tblDctosOrdenes.IDUSUARIO)	"
                + "                          AS nombreUsuario,	"
                + "   SUM(tblDctosOrdenesDetalle.cantidadPedida * "
                + "      tblDctosOrdenesDetalle.vrCostoNegociado) "
                + "                               vrCostoBase,	"
                + "         COUNT(*) AS cantidadArticulos		    "
                + "FROM tblDctosOrdenes				            "
                + "INNER JOIN tblDctosOrdenesDetalle		        "
                + "ON tblDctosOrdenes.IDLOCAL      = 		        "
                + "              tblDctosOrdenesDetalle.IDLOCAL	"
                + "AND tblDctosOrdenes.IDTIPOORDEN = 		        "
                + "          tblDctosOrdenesDetalle.IDTIPOORDEN	"
                + "AND tblDctosOrdenes.IDORDEN     = 		        "
                + "              tblDctosOrdenesDetalle.IDORDEN	"
                + "AND tblDctosOrdenes.IDLOCAL     =              "
                + getIdLocal() + "                                "
                + "AND tblDctosOrdenes.IDTIPOORDEN =              "
                + getIdTipoOrden() + "                            "
                + "AND tblDctosOrdenes.idOrden     =              "
                + getIdOrden() + "                                "
                + "GROUP BY tblDctosOrdenes.IDLOCAL,      		"
                + "         tblDctosOrdenes.IDTIPOORDEN,  		"
                + "         tblDctosOrdenes.IDORDEN,		        "
                + "         tblDctosOrdenes.FECHAORDEN,		    "
                + "         tblDctosOrdenes.idCliente,		    "
                + "         tblDctosOrdenes.IDUSUARIO,		    "
                + "         tblDctosOrdenes.IDLOG,			    "
                + "         tblDctosOrdenes.FECHAENTREGA,		    "
                + "         tblDctosOrdenes.diasHistoria,		    "
                + "         tblDctosOrdenes.diasInventario	    "
                + "ORDER BY tblDctosOrdenes.IDLOG DESC            ";

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
                fachadaBean.setIdTipoOrden(rs.getInt("IdTipoOrden"));
                fachadaBean.setIdOrden(rs.getInt("IdOrden"));
                fachadaBean.setFechaOrden(rs.getString("fechaOrden"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setIdUsuario(rs.getDouble("idUsuario"));
                fachadaBean.setIdLog(rs.getInt("idLog"));
                fachadaBean.setFechaEntrega(rs.getString("fechaEntrega"));
                fachadaBean.setDiasHistoria(rs.getInt("diasHistoria"));
                fachadaBean.setDiasInventario(rs.getInt("diasInventario"));
                fachadaBean.setNombreTercero(rs.getString("nombreTercero"));
                fachadaBean.setNombreUsuario(rs.getString("nombreUsuario"));
                fachadaBean.setVrCostoBase(rs.getDouble("vrCostoBase"));
                fachadaBean.setCantidadArticulos(
                        rs.getInt("cantidadArticulos"));

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
            return fachadaBean;

        }
    }

    // listaLegalizaOrigenFCH
    public FachadaColaboraDctoOrdenBean listaLegalizaOrigenFCH() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        // Variable de fachada de los datos
        FachadaColaboraDctoOrdenBean fachadaBean = new FachadaColaboraDctoOrdenBean();

        Connection connection = null;

        String selectStr =
                "SELECT   tblDctosOrdenes.IDLOCAL,      		"
                + "         tblDctosOrdenes.IDTIPOORDEN,  		"
                + "         tblDctosOrdenes.IDORDEN,		        "
                + "         tblDctosOrdenes.FECHAORDEN,		    "
                + "         tblDctosOrdenes.idCliente,	    	"
                + "         tblDctosOrdenes.IDUSUARIO,	   	    "
                + "         tblDctosOrdenes.IDLOG,			    "
                + "         tblDctosOrdenes.FECHAENTREGA,		    "
                + "         tblDctosOrdenes.diasHistoria,		    "
                + "         tblDctosOrdenes.diasInventario,	    "
                + "        ( SELECT tblLocales.nombreLocal        "
                + "          FROM tblLocales                      "
                + "          WHERE tblLocales.idLocal    =        "
                + "                  tblDctosOrdenes.idLocal ) 	"
                + "                          AS nombreTercero,    "
                + "        ( SELECT ctrlUsuarios.nombreUsuario    "
                + "          FROM ctrlUsuarios                    "
                + "          WHERE ctrlUsuarios.idUsuario =       "
                + "                   tblDctosOrdenes.IDUSUARIO)	"
                + "                          AS nombreUsuario,	"
                + "   SUM(tblDctosOrdenesDetalle.cantidadPedida * "
                + "      tblDctosOrdenesDetalle.vrCostoNegociado) "
                + "                               vrCostoBase,	"
                + "         COUNT(*) AS cantidadArticulos		    "
                + "FROM tblDctosOrdenes				            "
                + "INNER JOIN tblDctosOrdenesDetalle		        "
                + "ON tblDctosOrdenes.IDLOCAL      = 		        "
                + "              tblDctosOrdenesDetalle.IDLOCAL	"
                + "AND tblDctosOrdenes.IDTIPOORDEN = 		        "
                + "          tblDctosOrdenesDetalle.IDTIPOORDEN	"
                + "AND tblDctosOrdenes.IDORDEN     = 		        "
                + "              tblDctosOrdenesDetalle.IDORDEN	"
                + "AND  NOT EXISTS                                "
                + "    ( SELECT tblDctos.*                        "
                + "      FROM tblDctos                            "
                + "      WHERE tblDctos.idLocal      =            "
                + "                     tblDctosOrdenes.idLocal   "
                + "      AND    tblDctos.idTipoOrden =            "
                + "              tblDctosOrdenes.IDTIPOORDEN      "
                + "      AND    tblDctos.idOrden     =            "
                + "                   tblDctosOrdenes.idOrden)    "
                + "AND tblDctosOrdenes.IDLOCAL     =              "
                + getIdLocal() + "                                "
                + "AND tblDctosOrdenes.IDTIPOORDEN =              "
                + getIdTipoOrden() + "                            "
                + "AND tblDctosOrdenes.idLog       =              "
                + getIdLog() + "                                  "
                + "AND tblDctosOrdenesDetalle.idOrdenOrigen       =     "
                + getIdOrdenOrigen() + "                          "
                + "GROUP BY tblDctosOrdenes.IDLOCAL,      		"
                + "         tblDctosOrdenes.IDTIPOORDEN,  		"
                + "         tblDctosOrdenes.IDORDEN,		        "
                + "         tblDctosOrdenes.FECHAORDEN,		    "
                + "         tblDctosOrdenes.idCliente,		    "
                + "         tblDctosOrdenes.IDUSUARIO,		    "
                + "         tblDctosOrdenes.IDLOG,			    "
                + "         tblDctosOrdenes.FECHAENTREGA,		    "
                + "         tblDctosOrdenes.diasHistoria,		    "
                + "         tblDctosOrdenes.diasInventario	    "
                + "ORDER BY tblDctosOrdenes.IDLOG DESC            ";

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
                fachadaBean.setIdTipoOrden(rs.getInt("IdTipoOrden"));
                fachadaBean.setIdOrden(rs.getInt("IdOrden"));
                fachadaBean.setFechaOrden(rs.getString("fechaOrden"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setIdUsuario(rs.getDouble("idUsuario"));
                fachadaBean.setIdLog(rs.getInt("idLog"));
                fachadaBean.setFechaEntrega(rs.getString("fechaEntrega"));
                fachadaBean.setDiasHistoria(rs.getInt("diasHistoria"));
                fachadaBean.setDiasInventario(rs.getInt("diasInventario"));
                fachadaBean.setNombreTercero(rs.getString("nombreTercero"));
                fachadaBean.setNombreUsuario(rs.getString("nombreUsuario"));
                fachadaBean.setVrCostoBase(rs.getDouble("vrCostoBase"));
                fachadaBean.setCantidadArticulos(
                        rs.getInt("cantidadArticulos"));

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
            return fachadaBean;

        }
    }
}
