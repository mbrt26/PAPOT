package com.solucionesweb.losbeans.negocio;

// Importa la clase helper JDBCAccess que contiene los accesos a la base de datos
import com.solucionesweb.lasayudas.JDBCAccess;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaDctoBean;

// Importa los paquetes de java
import java.sql.*;
import java.io.*;
import java.util.*;
import java.io.IOException.*;

//
import org.kxml.*;
import org.kxml.parser.*;

// Importa los paquetes de javax
import javax.naming.*;

public class DctoContableTx extends FachadaDctoBean
        implements Serializable {

    // Nombre JNDI del Data Source que esta clase requiere
    private static final String DATA_SOURCE_NAME = "CommerceAccess";
    // Clase helper usada para acceder a la base de datos
    private JDBCAccess jdbcAccess;
    //
    Vector contenedor = new Vector();
    Vector contenedorLog = new Vector();
    // Variable de fachada de los datos
    FachadaDctoBean fachadaDctoBean;
    // Variable de fachada de los datos
    FachadaDctoBean fachadaLog;

    // Metodo constructor por defecto sin parametros
    public DctoContableTx() {

        // Al instanciar el Bean establece la conexion a la base de datos
        jdbcAccess = new JDBCAccess(DATA_SOURCE_NAME);

    }

    // ingresaDcto
    public boolean ingresaDcto() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        //
        String selectStr =
                "INSERT INTO tmpDctos (idLocal,         "
                + "                      idTipoOrden,   "
                + "                      idDcto,        "
                + "                      idDctoNitCC,   "
                + "                      idCliente,     "
                + "                      fechaDcto,     "
                + "                      vrBase,        "
                + "                      nombreTercero, "
                + "                    nombreSubcuenta, "
                + "                      porcentajeIva, "
                + "                        prefijo) "
                + "VALUES ( " + getIdLocal() + ","
                + getIdTipoOrden() + ",'"
                + getIdDcto() + "','"
                + getIdDctoNitCC() + "','"
                + getIdCliente() + "','"
                + getFechaDctoSqlServer() + "',"
                + getVrBaseRedondeo() + ",'"
                + getNombreTercero() + "','"
                + getNombreSubcuenta() + "',"
                + getPorcentajeIva() + ",'"
                + getPrefijo() + "')";
 
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
            jdbcAccess.cleanup(connection, selectStatement, null);
            return okIngresar;
        }
    }

    // retiraDcto
    public boolean retiraDcto() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        //
        String selectStr =
                "DELETE FROM tmpDctos      "
                + "WHERE tmpDctos.idLocal  = "
                + getIdLocal();

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
            jdbcAccess.cleanup(connection, selectStatement, null);
            return okIngresar;
        }
    }

    // ajusta
    public boolean ajusta() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        //
        String selectStr =
                "DELETE FROM tmpDctos      "
                + "WHERE tmpDctos.idLocal  = "
                + getIdLocal();

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
            jdbcAccess.cleanup(connection, selectStatement, null);
            return okIngresar;
        }
    }

    // calculoAjuste
    public int valorAjuste() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        //
        int xVrAjuste = 0;

        Connection connection = null;

        String selectStr =
                "SELECT SUM(tmpAJU.vrAjuste) AS vrAjuste        "
                + "FROM (                                       "
                + "SELECT tblTipoOrdenSubcuenta.idAsiento,      "
                + "       SUM(tmpDctos.vrBase *                 "
                + "      (CASE WHEN                             "
                + "         tblTipoOrdenSubcuenta.idAsiento = 1 "
                + "       THEN               1                  "
                + "       ELSE              -1                  "
                + "       END)) AS vrAjuste                     "
                + "FROM   tblTipoOrdenSubcuenta                 "
                + "INNER JOIN tmpDctos                          "
                + "ON tblTipoOrdenSubcuenta.idTipoOrden      =  "
                + "                        tmpDctos.IDTIPOORDEN "
                + "AND tblTipoOrdenSubcuenta.porcentajeIva   =  "
                + "                      tmpDctos.porcentajeIva "
                + "AND tblTipoOrdenSubcuenta.nombreSubcuenta =  "
                + "                    tmpDctos.nombreSubcuenta "
                + "WHERE tmpDctos.idLocal                 =     "
                + getIdLocal() + "                              "
                + " AND tmpDctos.idTipoOrden             =      "
                + getIdTipoOrden() + "                          "
                + "GROUP BY tblTipoOrdenSubcuenta.idAsiento)    "
                + "                                   AS tmpAJu ";

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
                xVrAjuste = rs.getInt("vrAjuste");

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
            return xVrAjuste;

        }
    }

    // listaUnLog_tipoOrden_9_29
    public Vector listaUnLog_tipoOrden_9_29() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        // Variable de fachada de los datos
        FachadaDctoBean fachadaBean;

        Connection connection = null;

        String selectStr =
                "SELECT tblDctos.idLocal                       "
                + "      ,tblDctos.idTipoOrden                 "
                + "      ,tblDctos.idDcto                      "
                + "      ,tblDctos.idCliente                   "
                + "      ,tblDctos.fechaDcto                   "
                + "      ,tblDctos.vrBase                      "
                + "      ,tblDctos.vrPago                      "
                + "      ,tblDctos.vrIva                       "
                + "      ,tblDctos.idTipoNegocio               "
                + "      ,tblDctos.vrRteFuente                 "
                + "      ,tblDctos.vrRteIva                    "
                + "      ,tblDctos.vrRteIca                    "
                + "      ,tblDctos.nombreTercero               "
                + "      ,tblDctos.idDctoNitCC                 "
                + "      ,tblDctos.vrDsctoFcro                 "
                + "      ,tblDctos.vrCostoMV                   "
                + "      ,tblDctos.vrImpoconsumo,              "
                + "       tblDctosOrdenesDetalle.porcentajeIva,"
                + " ( SELECT TOP 1 tblLocalesCaja.prefijo      "
                + "   FROM     tblAgendaLogVisitas             "
                + "   INNER JOIN tblLocalesCaja                "
                + "   ON tblAgendaLogVisitas.ipTx =            "
                + "              tblLocalesCaja.ipLocal        "
                + "   WHERE tblAgendaLogVisitas.idLog =        "
                + getIdLog() + " ) AS prefijo,                 "
                + "      SUM(tblDctosOrdenesDetalle.cantidad   *                     "
                + "      (tblDctosOrdenesDetalle.vrVentaUnitario   -		     "
                + "      tblDctosOrdenesDetalle.vrImpoconsumo)    *		     "
                + "      ( 1 - ( tblDctosOrdenesDetalle.vrDsctoPie /  100 ) )      * "
                + "      ( 1 - ( tblDctosOrdenesDetalle.porcentajeDscto /  100 ) ) / "
                + "      ( 1 + ( tblDctosOrdenesDetalle.porcentajeIva /  100 ) ))    "
                + "                                       AS vrVentaSinIva ,         "
                + "      SUM(tblDctosOrdenesDetalle.cantidad          *		     "
                + "      (tblDctosOrdenesDetalle.vrVentaUnitario   -		     "
                + "      tblDctosOrdenesDetalle.vrImpoconsumo)    *		     "
                + "      ( 1 - ( tblDctosOrdenesDetalle.vrDsctoPie /  100 ) )      * "
                + "    ( 1 - ( tblDctosOrdenesDetalle.porcentajeDscto /  100 ) ) ) - "
                + "      SUM(tblDctosOrdenesDetalle.cantidad          *		     "
                + "      (tblDctosOrdenesDetalle.vrVentaUnitario   -		     "
                + "      tblDctosOrdenesDetalle.vrImpoconsumo)    *		     "
                + "      ( 1 - ( tblDctosOrdenesDetalle.vrDsctoPie /  100 ) )      * "
                + "      ( 1 - ( tblDctosOrdenesDetalle.porcentajeDscto /  100 ) ) / "
                + "      ( 1 + ( tblDctosOrdenesDetalle.porcentajeIva /  100 ) ))    "
                + "                                             AS vrIvaVenta        "
                + "FROM tblDctos,                              "
                + "     tblDctosOrdenesDetalle                 "
                + "WHERE EXISTS (                              "
                + "SELECT tblAgendaLogVisitas.*                "
                + "FROM   tblAgendaLogVisitas                  "
                + "INNER JOIN tblDctosOrdenes                  "
                + "ON    tblAgendaLogVisitas.idLocal     =     "
                + "                  tblDctosOrdenes.IDLOCAL   "
                + "AND   tblAgendaLogVisitas.idTipoOrden =     "
                + "              tblDctosOrdenes.idTipoOrden   "
                + "AND   tblAgendaLogVisitas.IDLOG       =     "
                + "                    tblDctosOrdenes.IDLOG   "
                + "WHERE tblDctos.idLocal                =     "
                + "                  tblDctosOrdenes.IDLOCAL   "
                + "AND tblDctos.idTipoOrden              =     "
                + "              tblDctosOrdenes.idTipoOrden   "
                + "AND tblDctos.idOrden                  =     "
                + "                   tblDctosOrdenes.idOrden  "
                + "AND   tblDctos.idLocal                =     "
                + "           tblDctosOrdenesDetalle.IDLOCAL   "
                + "AND tblDctos.idTipoOrden              =     "
                + "       tblDctosOrdenesDetalle.idTipoOrden   "
                + "AND tblDctos.idOrden                  =     "
                + "            tblDctosOrdenesDetalle.idOrden  "
                + " AND tblDctos.idLocal                 =     "
                + getIdLocal() + "                             "
                + " AND tblDctos.idTipoOrden             =     "
                + getIdTipoOrden() + "                         "
                + " AND tblAgendaLogVisitas.idLog        =     "
                + getIdLog() + "   )                           "
                + "GROUP BY tblDctos.idLocal                   "
                + "      ,tblDctos.idTipoOrden                 "
                + "      ,tblDctos.idDcto                      "
                + "      ,tblDctos.idCliente                   "
                + "      ,tblDctos.fechaDcto                   "
                + "      ,tblDctos.vrBase                      "
                + "      ,tblDctos.vrPago                      "
                + "      ,tblDctos.vrIva                       "
                + "      ,tblDctos.idTipoNegocio               "
                + "      ,tblDctos.vrRteFuente                 "
                + "      ,tblDctos.vrRteIva                    "
                + "      ,tblDctos.vrRteIca                    "
                + "      ,tblDctos.nombreTercero               "
                + "      ,tblDctos.idDctoNitCC                 "
                + "      ,tblDctos.vrDsctoFcro                 "
                + "      ,tblDctos.vrCostoMV                   "
                + "      ,tblDctos.vrImpoconsumo               "
                + "      ,tblDctosOrdenesDetalle.porcentajeIva "
                + " ORDER BY tblDctosOrdenesDetalle.porcentajeIva";

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
                fachadaBean = new FachadaDctoBean();

                //
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setIdDcto(rs.getInt("idDcto"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setFechaDcto(rs.getString("fechaDcto"));
                fachadaBean.setVrBase(rs.getDouble("vrBase"));
                fachadaBean.setVrPago(rs.getDouble("vrPago"));
                fachadaBean.setVrIva(rs.getDouble("vrIva"));
                fachadaBean.setIdTipoNegocio(rs.getInt("idTipoNegocio"));
                fachadaBean.setVrRteFuente(rs.getDouble("vrRteFuente"));
                fachadaBean.setVrRteIva(rs.getDouble("vrRteIva"));
                fachadaBean.setVrRteIca(rs.getDouble("vrRteIca"));
                fachadaBean.setNombreTercero(rs.getString("nombreTercero"));
                fachadaBean.setIdDctoNitCC(rs.getString("idDctoNitCC"));
                fachadaBean.setVrDsctoFcro(rs.getDouble("vrDsctoFcro"));
                fachadaBean.setVrCostoMV(rs.getDouble("vrCostoMV"));
                fachadaBean.setVrImpoconsumo(rs.getDouble("vrImpoconsumo"));
                fachadaBean.setPorcentajeIva(rs.getDouble("porcentajeIva"));
                fachadaBean.setPrefijo(rs.getString("prefijo"));
                fachadaBean.setVrVentaSinIva(rs.getDouble("vrVentaSinIva"));
                fachadaBean.setVrIvaVenta(rs.getDouble("vrIvaVenta"));

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

    // listaUnLog_tipoOrden_1_21
    public Vector listaUnLog_tipoOrden_1_21() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        // Variable de fachada de los datos
        FachadaDctoBean fachadaBean;

        Connection connection = null;

        String selectStr = "";

        if (getIdTipoOrden() == 1) {

            // ---
            selectStr =
                "SELECT tblDctos.idLocal                       "
                + "      ,tblDctos.idTipoOrden                 "
                + "      ,tblDctos.idDcto                      "
                + "      ,tblDctos.idCliente                   "
                + "      ,tblDctos.fechaDcto                   "
                + "      ,tblDctos.vrBase                      "
                + "      ,tblDctos.vrPago                      "
                + "      ,tblDctos.vrIva                       "
                + "      ,tblDctos.idTipoNegocio               "
                + "      ,tblDctos.vrRteFuente                 "
                + "      ,tblDctos.vrRteIva                    "
                + "      ,tblDctos.vrRteIca                    "
                + "      ,tblDctos.nombreTercero               "
                + "      ,tblDctos.idDctoNitCC                 "
                + "      ,tblDctos.vrDsctoFcro                 "
                + "      ,tblDctos.vrCostoMV                   "
                + "      ,tblDctos.vrImpoconsumo,              "
                + "       tblDctosOrdenesDetalle.porcentajeIva,"
                + " ( SELECT   tblLocalesCaja.prefijo          "
                + "   FROM     tblAgendaLogVisitas             "
                + "   INNER JOIN tblLocalesCaja                "
                + "   ON tblAgendaLogVisitas.ipTx =            "
                + "              tblLocalesCaja.ipLocal        "
                + "   WHERE tblAgendaLogVisitas.idLog =        "
                + getIdLog() + " ) AS prefijo,                 "
                + "      SUM(tblDctosOrdenesDetalle.cantidad   *                     "
                + "      (tblDctosOrdenesDetalle.vrCosto   -	         	     "
                + "      tblDctosOrdenesDetalle.vrImpoconsumo/                       "
                + "                 (tblDctosOrdenesDetalle.cantidad))    *	     "
                + "      ( 1 - ( tblDctosOrdenesDetalle.vrDsctoPie /  100 ) )      * "
                + "      ( 1 - ( tblDctosOrdenesDetalle.porcentajeDscto /  100 ) ) / "
                + "      ( 1 + ( tblDctosOrdenesDetalle.porcentajeIva /  100 ) ))    "
                + "                                       AS vrVentaSinIva ,         "
                + "      SUM(tblDctosOrdenesDetalle.cantidad          *		     "
                + "      (tblDctosOrdenesDetalle.vrCosto   -	         	     "
                + "      tblDctosOrdenesDetalle.vrImpoconsumo/                       "
                + "                 (tblDctosOrdenesDetalle.cantidad))    *	     "
                + "      ( 1 - ( tblDctosOrdenesDetalle.vrDsctoPie /  100 ) )      * "
                + "    ( 1 - ( tblDctosOrdenesDetalle.porcentajeDscto /  100 ) ) ) - "
                + "      SUM(tblDctosOrdenesDetalle.cantidad          *		     "
                + "      (tblDctosOrdenesDetalle.vrCosto   -	         	     "
                + "      tblDctosOrdenesDetalle.vrImpoconsumo/                       "
                + "                 (tblDctosOrdenesDetalle.cantidad))    *	     "
                + "      ( 1 - ( tblDctosOrdenesDetalle.vrDsctoPie /  100 ) )      * "
                + "      ( 1 - ( tblDctosOrdenesDetalle.porcentajeDscto /  100 ) ) / "
                + "      ( 1 + ( tblDctosOrdenesDetalle.porcentajeIva /  100 ) ))    "
                + "                                             AS vrIvaVenta        "
                + "FROM tblDctos,                              "
                + "     tblDctosOrdenesDetalle                 "
                + "WHERE EXISTS (                              "
                + "SELECT tblAgendaLogVisitas.*                "
                + "FROM   tblAgendaLogVisitas                  "
                + "INNER JOIN tblDctosOrdenes                  "
                + "ON    tblAgendaLogVisitas.idLocal     =     "
                + "                  tblDctosOrdenes.IDLOCAL   "
                + "AND   tblAgendaLogVisitas.idTipoOrden =     "
                + "              tblDctosOrdenes.idTipoOrden   "
                + "AND   tblAgendaLogVisitas.IDLOG       =     "
                + "                    tblDctosOrdenes.IDLOG   "
                + "WHERE tblDctos.idLocal                =     "
                + "                  tblDctosOrdenes.IDLOCAL   "
                + "AND tblDctos.idTipoOrden              =     "
                + "              tblDctosOrdenes.idTipoOrden   "
                + "AND tblDctos.idOrden                  =     "
                + "                   tblDctosOrdenes.idOrden  "
                + "AND   tblDctos.idLocal                =     "
                + "           tblDctosOrdenesDetalle.IDLOCAL   "
                + "AND tblDctos.idTipoOrden              =     "
                + "       tblDctosOrdenesDetalle.idTipoOrden   "
                + "AND tblDctos.idOrden                  =     "
                + "            tblDctosOrdenesDetalle.idOrden  "
                + " AND tblDctos.idLocal                 =     "
                + getIdLocal() + "                             "
                + " AND tblDctos.idTipoOrden             =     "
                + getIdTipoOrden() + "                         "
                + " AND tblAgendaLogVisitas.idLog        =     "
                + getIdLog() + "   )                           "
                + "GROUP BY tblDctos.idLocal                   "
                + "      ,tblDctos.idTipoOrden                 "
                + "      ,tblDctos.idDcto                      "
                + "      ,tblDctos.idCliente                   "
                + "      ,tblDctos.fechaDcto                   "
                + "      ,tblDctos.vrBase                      "
                + "      ,tblDctos.vrPago                      "
                + "      ,tblDctos.vrIva                       "
                + "      ,tblDctos.idTipoNegocio               "
                + "      ,tblDctos.vrRteFuente                 "
                + "      ,tblDctos.vrRteIva                    "
                + "      ,tblDctos.vrRteIca                    "
                + "      ,tblDctos.nombreTercero               "
                + "      ,tblDctos.idDctoNitCC                 "
                + "      ,tblDctos.vrDsctoFcro                 "
                + "      ,tblDctos.vrCostoMV                   "
                + "      ,tblDctos.vrImpoconsumo               "
                + "      ,tblDctosOrdenesDetalle.porcentajeIva "
                + " ORDER BY tblDctosOrdenesDetalle.porcentajeIva";

        }

        if (getIdTipoOrden() == 21) {

            // --- cantidad unitaria vrImpoconsumo
            selectStr =
                "SELECT tblDctos.idLocal                       "
                + "      ,tblDctos.idTipoOrden                 "
                + "      ,tblDctos.idDcto                      "
                + "      ,tblDctos.idCliente                   "
                + "      ,tblDctos.fechaDcto                   "
                + "      ,tblDctos.vrBase                      "
                + "      ,tblDctos.vrPago                      "
                + "      ,tblDctos.vrIva                       "
                + "      ,tblDctos.idTipoNegocio               "
                + "      ,tblDctos.vrRteFuente                 "
                + "      ,tblDctos.vrRteIva                    "
                + "      ,tblDctos.vrRteIca                    "
                + "      ,tblDctos.nombreTercero               "
                + "      ,tblDctos.idDctoNitCC                 "
                + "      ,tblDctos.vrDsctoFcro                 "
                + "      ,tblDctos.vrCostoMV                   "
                + "      ,tblDctos.vrImpoconsumo,              "
                + "       tblDctosOrdenesDetalle.porcentajeIva,"
                + " ( SELECT   tblLocalesCaja.prefijo          "
                + "   FROM     tblAgendaLogVisitas             "
                + "   INNER JOIN tblLocalesCaja                "
                + "   ON tblAgendaLogVisitas.ipTx =            "
                + "              tblLocalesCaja.ipLocal        "
                + "   WHERE tblAgendaLogVisitas.idLog =        "
                + getIdLog() + " ) AS prefijo,                 "
                + "      SUM(tblDctosOrdenesDetalle.cantidad   *                     "
                + "      (tblDctosOrdenesDetalle.vrCosto   -	         	     "
                + "      tblDctosOrdenesDetalle.vrImpoconsumo       )    *	     "
                + "      ( 1 - ( tblDctosOrdenesDetalle.vrDsctoPie /  100 ) )      * "
                + "      ( 1 - ( tblDctosOrdenesDetalle.porcentajeDscto /  100 ) ) / "
                + "      ( 1 + ( tblDctosOrdenesDetalle.porcentajeIva /  100 ) ))    "
                + "                                       AS vrVentaSinIva ,         "
                + "      SUM(tblDctosOrdenesDetalle.cantidad          *		     "
                + "      (tblDctosOrdenesDetalle.vrCosto   -	         	     "
                + "      tblDctosOrdenesDetalle.vrImpoconsumo       )    *	     "
                + "      ( 1 - ( tblDctosOrdenesDetalle.vrDsctoPie /  100 ) )      * "
                + "    ( 1 - ( tblDctosOrdenesDetalle.porcentajeDscto /  100 ) ) ) - "
                + "      SUM(tblDctosOrdenesDetalle.cantidad             *           "
                + "      (tblDctosOrdenesDetalle.vrCosto   -	         	     "
                + "      tblDctosOrdenesDetalle.vrImpoconsumo       )    *	     "
                + "      ( 1 - ( tblDctosOrdenesDetalle.vrDsctoPie /  100 ) )      * "
                + "      ( 1 - ( tblDctosOrdenesDetalle.porcentajeDscto /  100 ) ) / "
                + "      ( 1 + ( tblDctosOrdenesDetalle.porcentajeIva /  100 ) ))    "
                + "                                             AS vrIvaVenta        "
                + "FROM tblDctos,                              "
                + "     tblDctosOrdenesDetalle                 "
                + "WHERE EXISTS (                              "
                + "SELECT tblAgendaLogVisitas.*                "
                + "FROM   tblAgendaLogVisitas                  "
                + "INNER JOIN tblDctosOrdenes                  "
                + "ON    tblAgendaLogVisitas.idLocal     =     "
                + "                  tblDctosOrdenes.IDLOCAL   "
                + "AND   tblAgendaLogVisitas.idTipoOrden =     "
                + "              tblDctosOrdenes.idTipoOrden   "
                + "AND   tblAgendaLogVisitas.IDLOG       =     "
                + "                    tblDctosOrdenes.IDLOG   "
                + "WHERE tblDctos.idLocal                =     "
                + "                  tblDctosOrdenes.IDLOCAL   "
                + "AND tblDctos.idTipoOrden              =     "
                + "              tblDctosOrdenes.idTipoOrden   "
                + "AND tblDctos.idOrden                  =     "
                + "                   tblDctosOrdenes.idOrden  "
                + "AND   tblDctos.idLocal                =     "
                + "           tblDctosOrdenesDetalle.IDLOCAL   "
                + "AND tblDctos.idTipoOrden              =     "
                + "       tblDctosOrdenesDetalle.idTipoOrden   "
                + "AND tblDctos.idOrden                  =     "
                + "            tblDctosOrdenesDetalle.idOrden  "
                + " AND tblDctos.idLocal                 =     "
                + getIdLocal() + "                             "
                + " AND tblDctos.idTipoOrden             =     "
                + getIdTipoOrden() + "                         "
                + " AND tblAgendaLogVisitas.idLog        =     "
                + getIdLog() + "   )                           "
                + "GROUP BY tblDctos.idLocal                   "
                + "      ,tblDctos.idTipoOrden                 "
                + "      ,tblDctos.idDcto                      "
                + "      ,tblDctos.idCliente                   "
                + "      ,tblDctos.fechaDcto                   "
                + "      ,tblDctos.vrBase                      "
                + "      ,tblDctos.vrPago                      "
                + "      ,tblDctos.vrIva                       "
                + "      ,tblDctos.idTipoNegocio               "
                + "      ,tblDctos.vrRteFuente                 "
                + "      ,tblDctos.vrRteIva                    "
                + "      ,tblDctos.vrRteIca                    "
                + "      ,tblDctos.nombreTercero               "
                + "      ,tblDctos.idDctoNitCC                 "
                + "      ,tblDctos.vrDsctoFcro                 "
                + "      ,tblDctos.vrCostoMV                   "
                + "      ,tblDctos.vrImpoconsumo               "
                + "      ,tblDctosOrdenesDetalle.porcentajeIva "
                + " ORDER BY tblDctosOrdenesDetalle.porcentajeIva";

        }

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
                fachadaBean = new FachadaDctoBean();

                //
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setIdDcto(rs.getInt("idDcto"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setFechaDcto(rs.getString("fechaDcto"));
                fachadaBean.setVrBase(rs.getDouble("vrBase"));
                fachadaBean.setVrPago(rs.getDouble("vrPago"));
                fachadaBean.setVrIva(rs.getDouble("vrIva"));
                fachadaBean.setIdTipoNegocio(rs.getInt("idTipoNegocio"));
                fachadaBean.setVrRteFuente(rs.getDouble("vrRteFuente"));
                fachadaBean.setVrRteIva(rs.getDouble("vrRteIva"));
                fachadaBean.setVrRteIca(rs.getDouble("vrRteIca"));
                fachadaBean.setNombreTercero(rs.getString("nombreTercero"));
                fachadaBean.setIdDctoNitCC(rs.getString("idDctoNitCC"));
                fachadaBean.setVrDsctoFcro(rs.getDouble("vrDsctoFcro"));
                fachadaBean.setVrCostoMV(rs.getDouble("vrCostoMV"));
                fachadaBean.setVrImpoconsumo(rs.getDouble("vrImpoconsumo"));
                fachadaBean.setPorcentajeIva(rs.getDouble("porcentajeIva"));
                fachadaBean.setPrefijo(rs.getString("prefijo"));
                fachadaBean.setVrVentaSinIva(rs.getDouble("vrVentaSinIva"));
                fachadaBean.setVrIvaVenta(rs.getDouble("vrIvaVenta"));

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


    /* listaUnLog_tipoOrden_1_21
    public Vector listaUnLog_tipoOrden_1_21() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        // Variable de fachada de los datos
        FachadaDctoBean fachadaBean;

        Connection connection = null;

        String selectStr =
                "SELECT tblDctos.idLocal                        "
                + "      ,tblDctos.idTipoOrden                  "
                + "      ,tblDctos.idDcto                       "
                + "      ,tblDctos.idCliente                    "
                + "      ,tblDctos.fechaDcto                    "
                + "      ,tblDctos.vrBase                       "
                + "      ,tblDctos.vrPago                       "
                + "      ,tblDctos.vrIva                        "
                + "      ,tblDctos.idTipoNegocio                "
                + "      ,tblDctos.vrRteFuente                  "
                + "      ,tblDctos.vrRteIva                     "
                + "      ,tblDctos.vrRteIca                     "
                + "      ,tblDctos.nombreTercero                "
                + "      ,tblDctos.idDctoNitCC                  "
                + "      ,tblDctos.vrDsctoFcro                  "
                + "      ,tblDctos.vrCostoMV                    "
                + "      ,tblDctos.vrImpoconsumo                "
                + "      ,0 AS porcentajeIva                    "
                + "FROM tblDctos				"
                + "WHERE EXISTS (                               "
                + "SELECT tblAgendaLogVisitas.*                 "
                + "FROM   tblAgendaLogVisitas                   "
                + "INNER JOIN tblDctosOrdenes                   "
                + "ON    tblAgendaLogVisitas.idLocal     =      "
                + "                  tblDctosOrdenes.IDLOCAL    "
                + "AND   tblAgendaLogVisitas.idTipoOrden =      "
                + "              tblDctosOrdenes.idTipoOrden    "
                + "AND   tblAgendaLogVisitas.IDLOG       =      "
                + "                    tblDctosOrdenes.IDLOG    "
                + "WHERE tblDctos.idLocal                =      "
                + "                  tblDctosOrdenes.IDLOCAL    "
                + "AND tblDctos.idTipoOrden              =      "
                + "              tblDctosOrdenes.idTipoOrden    "
                + "AND tblDctos.idOrden                  =      "
                + "                   tblDctosOrdenes.idOrden   "
                + " AND tblDctos.idLocal                 =      "
                + getIdLocal() + "                              "
                + " AND tblDctos.idTipoOrden             =      "
                + getIdTipoOrden() + "                          "
                + " AND tblAgendaLogVisitas.idLog        =      "
                + getIdLog() + " ) ";

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
                fachadaBean = new FachadaDctoBean();

                //
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setIdDcto(rs.getInt("idDcto"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setFechaDcto(rs.getString("fechaDcto"));
                fachadaBean.setVrBase(rs.getDouble("vrBase"));
                fachadaBean.setVrPago(rs.getDouble("vrPago"));
                fachadaBean.setVrIva(rs.getDouble("vrIva"));
                fachadaBean.setIdTipoNegocio(rs.getInt("idTipoNegocio"));
                fachadaBean.setVrRteFuente(rs.getDouble("vrRteFuente"));
                fachadaBean.setVrRteIva(rs.getDouble("vrRteIva"));
                fachadaBean.setVrRteIca(rs.getDouble("vrRteIca"));
                fachadaBean.setNombreTercero(rs.getString("nombreTercero"));
                fachadaBean.setIdDctoNitCC(rs.getString("idDctoNitCC"));
                fachadaBean.setVrDsctoFcro(rs.getDouble("vrDsctoFcro"));
                fachadaBean.setVrCostoMV(rs.getDouble("vrCostoMV"));
                fachadaBean.setVrImpoconsumo(rs.getDouble("vrImpoconsumo"));
                fachadaBean.setPorcentajeIva(rs.getDouble("porcentajeIva"));

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
    }*/

    // listaUnLog_tipoOrden_4
    public Vector listaUnLog_tipoOrden_4() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        // Variable de fachada de los datos
        FachadaDctoBean fachadaBean;

        Connection connection = null;

        String selectStr =
                "SELECT tblDctos.idLocal                       "
                + "      ,tblDctos.idTipoOrden                 "
                + "      ,tblDctos.idDcto                      "
                + "      ,tblDctos.idCliente                   "
                + "      ,tblDctos.fechaDcto                   "
                + "      ,tblDctos.vrCostoMV                   "
                + "      ,(CASE WHEN                           "
                + "  (MAX(tblDctosOrdenesDetalle.cantidad)<0)  "
                + "       THEN               1                 "
                + "       ELSE              -1                 "
                + "       END) AS porcentajeIva                "
                + "FROM tblDctos,                              "
                + "     tblDctosOrdenesDetalle                 "
                + "WHERE EXISTS (                              "
                + "SELECT tblAgendaLogVisitas.*                "
                + "FROM   tblAgendaLogVisitas                  "
                + "INNER JOIN tblDctosOrdenes                  "
                + "ON    tblAgendaLogVisitas.idLocal     =     "
                + "                  tblDctosOrdenes.IDLOCAL   "
                + "AND   tblAgendaLogVisitas.idTipoOrden =     "
                + "              tblDctosOrdenes.idTipoOrden   "
                + "AND   tblAgendaLogVisitas.IDLOG       =     "
                + "                    tblDctosOrdenes.IDLOG   "
                + "WHERE tblDctos.idLocal                =     "
                + "                  tblDctosOrdenes.IDLOCAL   "
                + "AND tblDctos.idTipoOrden              =     "
                + "              tblDctosOrdenes.idTipoOrden   "
                + "AND tblDctos.idOrden                  =     "
                + "                   tblDctosOrdenes.idOrden  "
                + "AND   tblDctos.idLocal                =     "
                + "           tblDctosOrdenesDetalle.IDLOCAL   "
                + "AND tblDctos.idTipoOrden              =     "
                + "       tblDctosOrdenesDetalle.idTipoOrden   "
                + "AND tblDctos.idOrden                  =     "
                + "            tblDctosOrdenesDetalle.idOrden  "
                + " AND tblDctos.idLocal                 =     "
                + getIdLocal() + "                             "
                + " AND tblDctos.idTipoOrden             =     "
                + getIdTipoOrden() + "                         "
                + " AND tblAgendaLogVisitas.idLog        =     "
                + getIdLog() + "   )                           "
                + "GROUP BY tblDctos.idLocal                   "
                + "      ,tblDctos.idTipoOrden                 "
                + "      ,tblDctos.idDcto                      "
                + "      ,tblDctos.idCliente                   "
                + "      ,tblDctos.fechaDcto                   "
                + "      ,tblDctos.vrCostoMV                   ";

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
                fachadaBean = new FachadaDctoBean();

                //
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setIdDcto(rs.getInt("idDcto"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setFechaDcto(rs.getString("fechaDcto"));
                fachadaBean.setVrCostoMV(rs.getDouble("vrCostoMV"));
                fachadaBean.setPorcentajeIva(rs.getDouble("porcentajeIva"));

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

    // listaUnLog_tipoOrden_15_16
    public Vector listaUnLog_tipoOrden_15_16() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        // Variable de fachada de los datos
        FachadaDctoBean fachadaBean;

        Connection connection = null;

        String selectStr =
                "SELECT tblDctos.idLocal                       "
                + "      ,tblDctos.idTipoOrden                 "
                + "      ,tblDctos.idDcto                      "
                + "      ,tblDctos.idCliente                   "
                + "      ,tblDctos.fechaDcto                   "
                + "      ,tblDctos.vrCostoMV                   "
                + "      ,(CASE WHEN                           "
                + "  (MAX(tblDctosOrdenesDetalle.cantidad)<0)  "
                + "       THEN              -1                 "
                + "       ELSE               1                 "
                + "       END) AS porcentajeIva                "
                + "FROM tblDctos,                              "
                + "     tblDctosOrdenesDetalle                 "
                + "WHERE EXISTS (                              "
                + "SELECT tblAgendaLogVisitas.*                "
                + "FROM   tblAgendaLogVisitas                  "
                + "INNER JOIN tblDctosOrdenes                  "
                + "ON    tblAgendaLogVisitas.idLocal     =     "
                + "                  tblDctosOrdenes.IDLOCAL   "
                + "AND   tblAgendaLogVisitas.idTipoOrden =     "
                + "              tblDctosOrdenes.idTipoOrden   "
                + "AND   tblAgendaLogVisitas.IDLOG       =     "
                + "                    tblDctosOrdenes.IDLOG   "
                + "WHERE tblDctos.idLocal                =     "
                + "                  tblDctosOrdenes.IDLOCAL   "
                + "AND tblDctos.idTipoOrden              =     "
                + "              tblDctosOrdenes.idTipoOrden   "
                + "AND tblDctos.idOrden                  =     "
                + "                   tblDctosOrdenes.idOrden  "
                + "AND   tblDctos.idLocal                =     "
                + "           tblDctosOrdenesDetalle.IDLOCAL   "
                + "AND tblDctos.idTipoOrden              =     "
                + "       tblDctosOrdenesDetalle.idTipoOrden   "
                + "AND tblDctos.idOrden                  =     "
                + "            tblDctosOrdenesDetalle.idOrden  "
                + " AND tblDctos.idLocal                 =     "
                + getIdLocal() + "                             "
                + " AND tblDctos.idTipoOrden             =     "
                + getIdTipoOrden() + "                         "
                + " AND tblAgendaLogVisitas.idLog        =     "
                + getIdLog() + "   )                           "
                + "GROUP BY tblDctos.idLocal                   "
                + "      ,tblDctos.idTipoOrden                 "
                + "      ,tblDctos.idDcto                      "
                + "      ,tblDctos.idCliente                   "
                + "      ,tblDctos.fechaDcto                   "
                + "      ,tblDctos.vrCostoMV                   ";

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
                fachadaBean = new FachadaDctoBean();

                //
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setIdDcto(rs.getInt("idDcto"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setFechaDcto(rs.getString("fechaDcto"));
                fachadaBean.setVrCostoMV(rs.getDouble("vrCostoMV"));
                fachadaBean.setPorcentajeIva(rs.getDouble("porcentajeIva"));

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

    // listaUnLog_tipoOrden_5
    public Vector listaUnLog_tipoOrden_5() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        // Variable de fachada de los datos
        FachadaDctoBean fachadaBean;

        Connection connection = null;

        String selectStr =
                "SELECT tblDctos.idLocal                       "
                + "      ,tblDctos.idTipoOrden                 "
                + "      ,tblDctos.idDcto                      "
                + "      ,tblDctos.idCliente                   "
                + "      ,tblDctos.fechaDcto                   "
                + "      ,tblDctos.vrCostoMV                   "
                + "      ,1 AS porcentajeIva                   "
                + "FROM tblDctos                               "
                + "WHERE EXISTS (                              "
                + "SELECT tblAgendaLogVisitas.*                "
                + "FROM   tblAgendaLogVisitas                  "
                + "INNER JOIN tblDctosOrdenes                  "
                + "ON    tblAgendaLogVisitas.idLocal     =     "
                + "                  tblDctosOrdenes.IDLOCAL   "
                + "AND   tblAgendaLogVisitas.idTipoOrden =     "
                + "              tblDctosOrdenes.idTipoOrden   "
                + "AND   tblAgendaLogVisitas.IDLOG       =     "
                + "                    tblDctosOrdenes.IDLOG   "
                + "WHERE tblDctos.idLocal                =     "
                + "                  tblDctosOrdenes.IDLOCAL   "
                + "AND tblDctos.idTipoOrden              =     "
                + "              tblDctosOrdenes.idTipoOrden   "
                + "AND tblDctos.idOrden                  =     "
                + "                   tblDctosOrdenes.idOrden  "
                + " AND tblDctos.idLocal                 =     "
                + getIdLocal() + "                             "
                + " AND tblDctos.idTipoOrden             =     "
                + getIdTipoOrden() + "                         "
                + " AND tblAgendaLogVisitas.idLog        =     "
                + getIdLog() + "   )                           "
                + "GROUP BY tblDctos.idLocal                   "
                + "      ,tblDctos.idTipoOrden                 "
                + "      ,tblDctos.idDcto                      "
                + "      ,tblDctos.idCliente                   "
                + "      ,tblDctos.fechaDcto                   "
                + "      ,tblDctos.vrCostoMV                   ";
 
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
                fachadaBean = new FachadaDctoBean();

                //
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setIdDcto(rs.getInt("idDcto"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setFechaDcto(rs.getString("fechaDcto"));
                fachadaBean.setVrCostoMV(rs.getDouble("vrCostoMV"));
                fachadaBean.setPorcentajeIva(rs.getDouble("porcentajeIva"));

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

    // listaUnLog_tipoOrden_500
    public Vector listaUnLog_tipoOrden_500() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
 

        // Variable de fachada de los datos
        FachadaDctoBean fachadaBean;

        Connection connection = null;

        String selectStr =
                "SELECT tblDctos.idLocal                       "
                + "      ,tblDctos.idTipoOrden                 "
                + "      ,tblDctos.idDcto                      "
                + "      ,tblDctos.idCliente                   "
                + "      ,tblDctos.fechaDcto                   "
                + "      ,tblDctos.vrBase                      "
                + "      ,0 AS porcentajeIva                   "
                + "FROM tblDctos                               "
                + "WHERE EXISTS (                              "
                + "SELECT tblAgendaLogVisitas.*                "
                + "FROM   tblAgendaLogVisitas                  "
                + "INNER JOIN tblDctosOrdenes                  "
                + "ON    tblAgendaLogVisitas.idLocal     =     "
                + "                  tblDctosOrdenes.IDLOCAL   "
                + "AND   tblAgendaLogVisitas.idTipoOrden =     "
                + "              tblDctosOrdenes.idTipoOrden   "
                + "AND   tblAgendaLogVisitas.IDLOG       =     "
                + "                    tblDctosOrdenes.IDLOG   "
                + "WHERE tblDctos.idLocal                =     "
                + "                  tblDctosOrdenes.IDLOCAL   "
                + "AND tblDctos.idTipoOrden              =     "
                + "              tblDctosOrdenes.idTipoOrden   "
                + "AND tblDctos.idOrden                  =     "
                + "                   tblDctosOrdenes.idOrden  "
                + " AND tblDctos.idLocal                 =     "
                + getIdLocal() + "                             "
                + " AND tblDctos.idTipoOrden             =     "
                + getIdTipoOrden() + "                         "
                + " AND tblAgendaLogVisitas.idLog        =     "
                + getIdLog() + "   )                           "
                + "GROUP BY tblDctos.idLocal                   "
                + "      ,tblDctos.idTipoOrden                 "
                + "      ,tblDctos.idDcto                      "
                + "      ,tblDctos.idCliente                   "
                + "      ,tblDctos.fechaDcto                   "
                + "      ,tblDctos.vrBase                      ";

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
                fachadaBean = new FachadaDctoBean();

                //
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setIdDcto(rs.getInt("idDcto"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setFechaDcto(rs.getString("fechaDcto"));
                fachadaBean.setVrBase(rs.getDouble("vrBase"));
                fachadaBean.setPorcentajeIva(rs.getDouble("porcentajeIva"));

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

    // listaLog
    public Vector listaLog() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        // Variable de fachada de los datos
        FachadaDctoBean fachadaBean;

        Connection connection = null;

        String selectStr =
                "SELECT TOP 1   tblDctos.idLocal,           "
                + "       tblDctos.idTipoOrden,             "
                + "       tblAgendaLogVisitas.idLog         "
                + "FROM   tblAgendaLogVisitas               "
                + "INNER JOIN tblDctosOrdenes               "
                + "ON tblAgendaLogVisitas.idLog         =   "
                + "                   tblDctosOrdenes.idLog "
                + "AND tblAgendaLogVisitas.idLocal      =   "
                + "                 tblDctosOrdenes.IDLOCAL "
                + "AND tblAgendaLogVisitas.idTipoOrden  =   "
                + "             tblDctosOrdenes.IDTIPOORDEN "
                + "INNER JOIN tblDctos                      "
                + "ON  tblDctosOrdenes.IDLOCAL          =   "
                + "                        tblDctos.IDLOCAL "
                + "AND tblDctosOrdenes.IDTIPOORDEN      =   "
                + "                    tblDctos.IDTIPOORDEN "
                + "AND tblDctosOrdenes.IDORDEN          =   "
                + "                        tblDctos.IDORDEN "
                + "WHERE tblAgendaLogVisitas.idEstadoTx = 1 "
                + "AND   tblAgendaLogVisitas.idLocal    =   "
                + getIdLocal() + "                          "
                + "GROUP BY tblDctos.idLocal,               "
                + "        tblDctos.idTipoOrden,            "
                + "        tblAgendaLogVisitas.idLog        "
                + "ORDER BY tblDctos.idLocal,               "
                + "         tblAgendaLogVisitas.idLog DESC  ";

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
                fachadaBean = new FachadaDctoBean();

                //
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("IdTipoOrden"));
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

    // listaSubcuentaUnLog
    public Vector listaSubcuentaUnLog(int xVrAjuste,
            String xNombreTipoOrden) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        // Variable de fachada de los datos
        FachadaDctoBean fachadaBean;

        Connection connection = null;

        String selectStr =
                "SELECT tmpDctos.idLocal,                       "
                + "       tmpDctos.idTipoOrden,                 "
                + "       tmpDctos.idDcto,                      "
                + "       tmpDctos.idDctoNitCC,                 "
                + "       tmpDctos.idCliente,                   "
                + "       tmpDctos.fechaDcto,                   "
                + "       tmpDctos.vrBase,                      "
                + "       tmpDctos.nombreTercero,               "
                + "       tmpDctos.prefijo,                     "
                + "       tmpDctos.nombreSubcuenta,             "
                + "       tblTipoOrdenSubcuenta.idAsiento,      "
                + "       tblTipoOrdenSubcuenta.idSubcuenta,    "
                + "       tblTipoOrdenSubcuenta.idComprobante,  "
                + "      (CASE WHEN                             "
                + "         tblTipoOrdenSubcuenta.idAsiento = 1 "
                + "       THEN              'DNO'               "
                + "       ELSE              'CNO'               "
                + "       END)  AS nombreAsiento,               "
                + " ISNULL(( SELECT TOP 1                       "
                + "   tmpDctos.vrBase /                         "
                + "   (tblContableRetencion.porcentajeRetencion "
                + "                                  / 100)     "
                + "          FROM tblContableRetencion          "
                + "  WHERE tblContableRetencion.idSubcuenta =   "
                + "      tblTipoOrdenSubcuenta.idSubcuenta ),0) "
                + "                          AS vrBaseContable  "                                  
                + "FROM   tblTipoOrdenSubcuenta                 "
                + "INNER JOIN tmpDctos                          "
                + "ON tblTipoOrdenSubcuenta.idTipoOrden      =  "
                + "                        tmpDctos.IDTIPOORDEN "
                + "AND tblTipoOrdenSubcuenta.porcentajeIva   =  "
                + "                      tmpDctos.porcentajeIva "
                + "AND tblTipoOrdenSubcuenta.nombreSubcuenta =  "
                + "                    tmpDctos.nombreSubcuenta "
                + "WHERE tmpDctos.vrBase     != 0               "
                + "AND   tmpDctos.idLocal     =                 "
                + getIdLocal() + "                              "
                + "AND   tmpDctos.idTipoOrden =                 "
                + getIdTipoOrden() + "                          "
                + "ORDER BY tblTipoOrdenSubcuenta.idAsiento,    "
                + "         tblTipoOrdenSubcuenta.idSubcuenta   ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            int xItemMovimiento = 0;
            double xCeroDouble = 0.0;
            int xCeroInt = 0;
            String xNombreSubcuenta = "";
            boolean xOkAsiento = true;

            //
            while (rs.next()) {

                //
                fachadaBean = new FachadaDctoBean();

                //
                xNombreSubcuenta = rs.getString("nombreSubcuenta");

                //
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("IdTipoOrden"));
                fachadaBean.setIdDcto(rs.getString("idDcto"));
                fachadaBean.setIdDctoNitCC(rs.getString("idDctoNitCC"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setFechaDcto(rs.getString("fechaDcto"));
                fachadaBean.setVrBase(rs.getDouble("vrBase"));
                fachadaBean.setNombreTercero(rs.getString("nombreTercero"));
                fachadaBean.setPrefijo(rs.getString("prefijo"));
                fachadaBean.setIdSubcuenta(rs.getString("idSubcuenta"));
                fachadaBean.setIdComprobanteContable(rs.getInt("idComprobante"));
                fachadaBean.setNombreAsiento(rs.getString("nombreAsiento"));
                fachadaBean.setVrSaldo(xCeroDouble);
                fachadaBean.setNumeroDctos(xCeroInt);
                fachadaBean.setComentarioMovimiento(xNombreTipoOrden);
                fachadaBean.setVrBaseContable(rs.getDouble("vrBaseContable"));

                //
                if ((xOkAsiento) && (xNombreSubcuenta.compareTo("vrIva") == 0)) {

                    //
                    xOkAsiento = false;

                    //
                    fachadaBean.setVrBase(rs.getDouble("vrBase")
                            + xVrAjuste);
                }

                //
                xItemMovimiento++;

                //                
                fachadaBean.setItemMovimiento(xItemMovimiento);

                /*
                System.out.println(fachadaBean.getAnoMovimientoSoftLand() + ","
                        + fachadaBean.getPeriodoMovimientoSoftLand() + ","
                        + fachadaBean.getIdComprobanteContableSoftLand() + ","
                        + fachadaBean.getIdPrefijoMovimientoSoftLand() + ","
                        + fachadaBean.getIdDctoPrefijoSoftLand() + ","
                        + fachadaBean.getFechaDctoSoftLand() + ","
                        + fachadaBean.getItemMovimientoSoftLand() + ","
                        + fachadaBean.getIdSubcuenta() + ","
                        + fachadaBean.getStrIdLocalSoftLand() + ","
                        + fachadaBean.getCodigoMonedaMovimientoSoftLand() + ","
                        + fachadaBean.getIdClienteSoftLand() + ","
                        + fachadaBean.getIdSucursalSoftLand() + ","
                        + fachadaBean.getIdClienteSoftLand() + ","
                        + fachadaBean.getIdPrefijoMovimientoSoftLand() + ","
                        + fachadaBean.getIdDctoPrefijoSoftLand() + ","
                        + fachadaBean.getComentarioMovimientoSoftLand() + ","
                        + fachadaBean.getVrBase() + ","
                        + fachadaBean.getVrTotal() + ","
                        + fachadaBean.getVrTotal() + ","
                        + fachadaBean.getNombreAsiento() + ","
                        + fachadaBean.getOrigenMovimientoSoftland() + ","
                        + fachadaBean.getNumeroDctos());*/

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

    // listaSubcuentaUnLogPago
    public Vector listaSubcuentaUnLogPago(int xVrAjuste,
            String xNombreTipoOrden) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        // Variable de fachada de los datos
        FachadaDctoBean fachadaBean;

        Connection connection = null;

        String selectStr =
                "SELECT  tmpDctos.IDLOCAL, 		        "
                + "        tmpDctos.IDTIPOORDEN, 		"
                + "        tmpDctos.idDcto, 		        "
                + "        tmpDctos.idDctoNitCC,                "
                + "        tmpDctos.idCliente, 		        "
                + "        tmpDctos.fechaDcto, 		        "
                + "        tmpDctos.vrBase, 		        "
                + "        tmpDctos.nombreTercero, 	        "
                + "        tmpDctos.prefijo,                    "
                + "        tblTipoOrdenSubcuenta.idAsiento,     "
                + "        tblTipoOrdenSubcuenta.idSubcuenta,   "
                + "        tblTipoOrdenSubcuenta.idComprobante, "
                + "      (CASE WHEN                             "
                + "         tblTipoOrdenSubcuenta.idAsiento = 1 "
                + "       THEN              'DNO'               "
                + "       ELSE              'CNO'               "
                + "       END)  AS nombreAsiento,               "
                + " ISNULL(( SELECT TOP 1                       "
                + "   tmpDctos.vrBase /                         "
                + "   (tblContableRetencion.porcentajeRetencion "
                + "                                  / 100)     "
                + "          FROM tblContableRetencion          "
                + "  WHERE tblContableRetencion.idSubcuenta =   "
                + "      tblTipoOrdenSubcuenta.idSubcuenta ),0) "
                + "                          AS vrBaseContable  "
                + "FROM    tblTipoOrdenSubcuenta		"
                + "INNER JOIN tmpDctos			        "
                + "ON  tblTipoOrdenSubcuenta.idTipoOrden     =  "
                + "                       tmpDctos.IDTIPOORDEN  "
                + "AND tblTipoOrdenSubcuenta.porcentajeIva   =  "
                + "                     tmpDctos.porcentajeIva  "
                + "AND tblTipoOrdenSubcuenta.nombreSubcuenta =  "
                + "                   tmpDctos.nombreSubcuenta  "
                + "WHERE tmpDctos.vrBase     != 0     	        "
                + "AND   tmpDctos.idLocal     =                 "
                + getIdLocal() + "                              "
                + "AND   tmpDctos.idTipoOrden =                 "
                + getIdTipoOrden() + "                          "
                + "ORDER BY tmpDctos.idDcto,                    "
                + "         tblTipoOrdenSubcuenta.idAsiento,    "
                + "         tblTipoOrdenSubcuenta.idSubcuenta   ";


        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            int xItemMovimiento = 0;
            double xCeroDouble = 0.0;
            int xCeroInt = 0;
            int xIdAsiento = 0;
            boolean xOkAsiento = true;

            //
            while (rs.next()) {

                //
                fachadaBean = new FachadaDctoBean();

                //
                if (xOkAsiento) {

                    xOkAsiento = false;
                    xIdAsiento = rs.getInt("idAsiento");

                }

                //
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("IdTipoOrden"));
                fachadaBean.setIdDcto(rs.getString("idDcto"));
                fachadaBean.setIdDctoNitCC(rs.getString("idDctoNitCC"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setFechaDcto(rs.getString("fechaDcto"));
                fachadaBean.setVrBase(rs.getDouble("vrBase"));
                fachadaBean.setNombreTercero(rs.getString("nombreTercero"));
                fachadaBean.setPrefijo(rs.getString("prefijo"));
                fachadaBean.setIdSubcuenta(rs.getString("idSubcuenta"));
                fachadaBean.setIdComprobanteContable(
                                                    rs.getInt("idComprobante"));
                fachadaBean.setNombreAsiento(rs.getString("nombreAsiento"));
                fachadaBean.setVrSaldo(xCeroDouble);
                fachadaBean.setNumeroDctos(xCeroInt);
                fachadaBean.setComentarioMovimiento(xNombreTipoOrden);
                fachadaBean.setVrBaseContable(rs.getDouble("vrBaseContable"));

                //
                if (xIdAsiento != rs.getInt("idAsiento")) {

                    //
                    fachadaBean.setVrBase(rs.getDouble("vrBase")
                            + xVrAjuste);

                    //
                    xIdAsiento = rs.getInt("idAsiento");

                }

                //
                xItemMovimiento++;

                //
                fachadaBean.setItemMovimiento(xItemMovimiento);

                /*
                System.out.println(fachadaBean.getAnoMovimientoSoftLand() + ","
                        + fachadaBean.getPeriodoMovimientoSoftLand() + ","
                        + fachadaBean.getIdComprobanteContableSoftLand() + ","
                        + fachadaBean.getIdPrefijoMovimientoSoftLand() + ","
                        + fachadaBean.getIdDctoSoftLand() + ","
                        + fachadaBean.getFechaDctoSoftLand() + ","
                        + fachadaBean.getItemMovimientoSoftLand() + ","
                        + fachadaBean.getIdSubcuenta() + ","
                        + fachadaBean.getStrIdLocalSoftLand() + ","
                        + fachadaBean.getCodigoMonedaMovimientoSoftLand() + ","
                        + fachadaBean.getIdClienteSoftLand() + ","
                        + fachadaBean.getIdSucursalSoftLand() + ","
                        + fachadaBean.getIdClienteSoftLand() + ","
                        + fachadaBean.getIdPrefijoMovimientoSoftLand() + ","
                        + fachadaBean.getIdDctoSoftLand() + ","
                        + fachadaBean.getComentarioMovimientoSoftLand() + ","
                        + fachadaBean.getVrBase() + ","
                        + fachadaBean.getVrTotal() + ","
                        + fachadaBean.getVrTotal() + ","
                        + fachadaBean.getNombreAsiento() + ","
                        + fachadaBean.getOrigenMovimientoSoftland() + ","
                        + fachadaBean.getNumeroDctos());*/

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

    // listaUnLog_tipoOrden_Pago_1
    public Vector listaUnLog_tipoOrden_Pago_1() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        // Variable de fachada de los datos
        FachadaDctoBean fachadaBean;

        Connection connection = null;

        String selectStr =
                "SELECT tblPagos.idLocal,                 "
                + "       tblPagos.idTipoOrden,           "
                + "       tblPagos.idRecibo,              "
                + "       tblPagos.fechaPago,             "
                + "       tblPagos.vrPago,                "
                + "       tblPagos.vrDescuento,           "
                + "       tblPagos.vrRteFuente,           "
                + "       tblPagos.nitCC,                 "
                + "       tblPagos.idDctoNitCC,           "
                + "       -4 AS porcentajeIva             "
                + "FROM   tblAgendaLogVisitas             "
                + "INNER JOIN tblPagos                    "
                + "ON tblAgendaLogVisitas.idLocal      =  "
                + "                      tblPagos.idLocal "
                + "AND tblAgendaLogVisitas.idTipoOrden =  "
                + "                  tblPagos.idTipoOrden "
                + "AND tblAgendaLogVisitas.idLog       =  "
                + "                       tblPagos.idLog  "
                + "WHERE tblAgendaLogVisitas.idLog     =  "
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
            while (rs.next()) {

                //
                fachadaBean = new FachadaDctoBean();

                //
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setIdDcto(rs.getString("idRecibo"));
                fachadaBean.setIdCliente(rs.getString("nitCC"));
                fachadaBean.setIdDctoNitCC(rs.getString("idDctoNitCC"));
                fachadaBean.setFechaDcto(rs.getString("fechaPago"));
                fachadaBean.setVrCostoMV(rs.getDouble("vrPago"));
                fachadaBean.setVrDescuento(rs.getDouble("vrDescuento"));    
                fachadaBean.setVrRteFuente(rs.getDouble("vrRteFuente"));
                fachadaBean.setPorcentajeIva(rs.getDouble("porcentajeIva"));

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

    // listaUnLog_tipoOrden_Pago_9
    public Vector listaUnLog_tipoOrden_Pago_9() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        // Variable de fachada de los datos
        FachadaDctoBean fachadaBean;

        Connection connection = null;

        String selectStr =
                "SELECT tblPagos.idLocal,                  "
                + "       tblPagos.idTipoOrden,            "
                + "       tblPagos.idRecibo,               "
                + "       tblPagos.idDcto,                 "
                + "       tblPagos.fechaPago,              "
                + "       tblPagos.vrPago,                 "
                + "       tblPagos.nitCC,                  "
                + "       tblPagos.vrDescuento,            "
                + "       tblPagos.vrRteFuente,            "
                + "       -2 AS porcentajeIva,             "
                + " ( SELECT TOP 1 tblLocalesCaja.prefijo  "
                + "   FROM     tblAgendaLogVisitas         "
                + "   INNER JOIN tblLocalesCaja            "
                + "   ON tblAgendaLogVisitas.ipTx =        "
                + "              tblLocalesCaja.ipLocal    "
                + "   WHERE tblAgendaLogVisitas.idLog =    "
                + getIdLog() + " ) AS prefijo              "
                + "FROM   tblAgendaLogVisitas              "
                + "INNER JOIN tblPagos                     "
                + "ON tblAgendaLogVisitas.idLocal      =   "
                + "                      tblPagos.idLocal  "
                + "AND tblAgendaLogVisitas.idTipoOrden =   "
                + "                  tblPagos.idTipoOrden  "
                + "AND tblAgendaLogVisitas.idLog       =   "
                + "                       tblPagos.idLog   "
                + "WHERE tblPagos.indicador            = 1 "
                + "AND   tblAgendaLogVisitas.idLog     =   "
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
            while (rs.next()) {

                //
                fachadaBean = new FachadaDctoBean();

                //
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setIdDcto(rs.getInt("idRecibo"));
                fachadaBean.setIdDctoNitCC(rs.getString("idDcto"));
                fachadaBean.setIdCliente(rs.getString("nitCC"));
                fachadaBean.setFechaDcto(rs.getString("fechaPago"));
                fachadaBean.setVrCostoMV(rs.getDouble("vrPago"));
                fachadaBean.setVrDescuento(rs.getDouble("vrDescuento"));
                fachadaBean.setVrRteFuente(rs.getDouble("vrRteFuente"));
                fachadaBean.setPorcentajeIva(rs.getDouble("porcentajeIva"));
                fachadaBean.setPrefijo(rs.getString("prefijo"));

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

// traverse_DctoContableTx
    public Vector traverse_DctoContableTx(XmlParser parser,
            String indent) throws Exception {

        //
        boolean leave = false;

        //
        String idLocal = new String();
        String idTipoOrden = new String();
        String idLog = new String();

        do {
            ParseEvent event = parser.read();
            ParseEvent pe;

            switch (event.getType()) {

                // For example, <CxC>
                case Xml.START_TAG:

                    // see API doc of StartTag for more access methods
                    // Pick up idagenda for display
                    if ("idLocal".equals(event.getName())) {
                        pe = parser.read();
                        idLocal = pe.getText();

                    }

                    // Pick up clave for display
                    if ("idTipoOrden".equals(event.getName())) {
                        pe = parser.read();
                        idTipoOrden = pe.getText();

                    }

                    // Pick up clave for display
                    if ("idLog".equals(event.getName())) {
                        pe = parser.read();
                        idLog = pe.getText();

                        //
                        fachadaDctoBean = new FachadaDctoBean();

                        //
                        fachadaDctoBean.setIdLocal(idLocal);
                        fachadaDctoBean.setIdTipoOrden(idTipoOrden);
                        fachadaDctoBean.setIdLog(idLog);

                        //
                        contenedor.add(fachadaDctoBean);


                    }

                    traverse_DctoContableTx(parser, ""); // recursion call for each <tag></tag>
                    break;

                // For example </title?
                case Xml.END_TAG:
                    leave = true;
                    break;

                // For example </rss>
                case Xml.END_DOCUMENT:

                    leave = true;
                    break;

                // For example, the text between tags
                case Xml.TEXT:
                    break;
                case Xml.WHITESPACE:
                    break;
                default:
            }
        } while (!leave);

        //
        return contenedor;
    }

// traverse_DctoContableOrden_9_29_Tx
    public Vector traverse_DctoContableOrden_9_29_Tx(XmlParser parser,
            String indent) throws Exception {

        //
        boolean leave = false;

        //
        String idLocal = new String();
        String idTipoOrden = new String();
        String idDcto = new String();
        String idCliente = new String();
        String fechaDcto = new String();
        String vrBase = new String();
        String vrPago = new String();
        String vrIva = new String();
        String idTipoNegocio = new String();
        String vrRteFuente = new String();
        String vrRteIva = new String();
        String vrRteIca = new String();
        String nombreTercero = new String();
        String idDctoNitCC = new String();
        String vrDsctoFcro = new String();
        String vrCostoMV = new String();
        String vrImpoconsumo = new String();
        String porcentajeIva = new String();
        String prefijo = new String();
        String vrVentaSinIva = new String();
        String vrIvaVenta = new String();

        //
        do {
            ParseEvent event = parser.read();
            ParseEvent pe;

            switch (event.getType()) {

                // For example, <CxC>
                case Xml.START_TAG:

                    // see API doc of StartTag for more access methods
                    // Pick up idagenda for display
                    if ("idLocal".equals(event.getName())) {
                        pe = parser.read();
                        idLocal = pe.getText();

                    }

                    // Pick up clave for display
                    if ("idTipoOrden".equals(event.getName())) {
                        pe = parser.read();
                        idTipoOrden = pe.getText();

                    }

                    // Pick up clave for display
                    if ("idDcto".equals(event.getName())) {
                        pe = parser.read();
                        idDcto = pe.getText();

                    }

                    // Pick up clave for display
                    if ("idCliente".equals(event.getName())) {
                        pe = parser.read();
                        idCliente = pe.getText();

                    }

                    // Pick up clave for display
                    if ("fechaDcto".equals(event.getName())) {
                        pe = parser.read();
                        fechaDcto = pe.getText();

                    }

                    // Pick up clave for display
                    if ("vrBase".equals(event.getName())) {
                        pe = parser.read();
                        vrBase = pe.getText();

                    }

                    // Pick up clave for display
                    if ("vrPago".equals(event.getName())) {
                        pe = parser.read();
                        vrPago = pe.getText();

                    }

                    // Pick up clave for display
                    if ("vrIva".equals(event.getName())) {
                        pe = parser.read();
                        vrIva = pe.getText();

                    }

                    // Pick up clave for display
                    if ("idTipoNegocio".equals(event.getName())) {
                        pe = parser.read();
                        idTipoNegocio = pe.getText();

                    }

                    // Pick up clave for display
                    if ("vrRteFuente".equals(event.getName())) {
                        pe = parser.read();
                        vrRteFuente = pe.getText();

                    }

                    // Pick up clave for display
                    if ("vrRteIva".equals(event.getName())) {
                        pe = parser.read();
                        vrRteIva = pe.getText();

                    }

                    // Pick up clave for display
                    if ("vrRteIca".equals(event.getName())) {
                        pe = parser.read();
                        vrRteIca = pe.getText();

                    }

                    // Pick up clave for display
                    if ("nombreTercero".equals(event.getName())) {
                        pe = parser.read();
                        nombreTercero = pe.getText();

                    }

                    // Pick up clave for display
                    if ("idDctoNitCC".equals(event.getName())) {
                        pe = parser.read();
                        idDctoNitCC = pe.getText();

                    }

                    // Pick up clave for display
                    if ("vrDsctoFcro".equals(event.getName())) {
                        pe = parser.read();
                        vrDsctoFcro = pe.getText();

                    }

                    // Pick up clave for display
                    if ("vrCostoMV".equals(event.getName())) {
                        pe = parser.read();
                        vrCostoMV = pe.getText();

                    }

                    // Pick up clave for display
                    if ("vrImpoconsumo".equals(event.getName())) {
                        pe = parser.read();
                        vrImpoconsumo = pe.getText();

                    }

                    // Pick up clave for display
                    if ("porcentajeIva".equals(event.getName())) {
                        pe = parser.read();
                        porcentajeIva = pe.getText();

                    }

                    // Pick up clave for display
                    if ("prefijo".equals(event.getName())) {
                        pe = parser.read();
                        prefijo = pe.getText();
                    }

                    // Pick up clave for display
                    if ("vrVentaSinIva".equals(event.getName())) {
                        pe = parser.read();
                        vrVentaSinIva = pe.getText();

                    }

                    // Pick up clave for display
                    if ("vrIvaVenta".equals(event.getName())) {
                        pe = parser.read();
                        vrIvaVenta = pe.getText();

                        //
                        fachadaLog = new FachadaDctoBean();

                        //
                        fachadaLog.setIdLocal(idLocal);
                        fachadaLog.setIdTipoOrden(idTipoOrden);
                        fachadaLog.setIdDcto(idDcto);
                        fachadaLog.setIdCliente(idCliente);
                        fachadaLog.setFechaDcto(fechaDcto);
                        fachadaLog.setVrBase(vrBase);
                        fachadaLog.setVrPago(vrPago);
                        fachadaLog.setVrIva(vrIva);
                        fachadaLog.setIdTipoNegocio(idTipoNegocio);
                        fachadaLog.setVrRteFuente(vrRteFuente);
                        fachadaLog.setVrRteIva(vrRteIva);
                        fachadaLog.setVrRteIca(vrRteIca);
                        fachadaLog.setNombreTercero(nombreTercero);
                        fachadaLog.setIdDctoNitCC(idDctoNitCC);
                        fachadaLog.setVrDsctoFcro(vrDsctoFcro);
                        fachadaLog.setVrCostoMV(vrCostoMV);
                        fachadaLog.setVrImpoconsumo(vrImpoconsumo);
                        fachadaLog.setPorcentajeIva(porcentajeIva);
                        fachadaLog.setPrefijo(prefijo);
                        fachadaLog.setVrVentaSinIva(vrVentaSinIva);
                        fachadaLog.setVrIvaVenta(vrIvaVenta);

                        //
                        contenedorLog.add(fachadaLog);

                    }

                    //recursion call for each <tag></tag>
                    traverse_DctoContableOrden_9_29_Tx(parser, "");
                    break;

                // For example </title?
                case Xml.END_TAG:
                    leave = true;
                    break;

                // For example </rss>
                case Xml.END_DOCUMENT:

                    leave = true;
                    break;

                // For example, the text between tags
                case Xml.TEXT:
                    break;
                case Xml.WHITESPACE:
                    break;
                default:
            }
        } while (!leave);

        //
        return contenedorLog;
    }

// traverse_DctoContableOrden_1_21_Tx
    public Vector traverse_DctoContableOrden_1_21_Tx(XmlParser parser,
            String indent) throws Exception {

        //
        boolean leave = false;

        //
        String idLocal = new String();
        String idTipoOrden = new String();
        String idDcto = new String();
        String idCliente = new String();
        String fechaDcto = new String();
        String vrBase = new String();
        String vrPago = new String();
        String vrIva = new String();
        String idTipoNegocio = new String();
        String vrRteFuente = new String();
        String vrRteIva = new String();
        String vrRteIca = new String();
        String nombreTercero = new String();
        String idDctoNitCC = new String();
        String vrDsctoFcro = new String();
        String vrCostoMV = new String();
        String vrImpoconsumo = new String();
        String porcentajeIva = new String();

        //
        do {
            ParseEvent event = parser.read();
            ParseEvent pe;

            switch (event.getType()) {

                // For example, <CxC>
                case Xml.START_TAG:

                    // see API doc of StartTag for more access methods
                    // Pick up idagenda for display
                    if ("idLocal".equals(event.getName())) {
                        pe = parser.read();
                        idLocal = pe.getText();

                    }

                    // Pick up clave for display
                    if ("idTipoOrden".equals(event.getName())) {
                        pe = parser.read();
                        idTipoOrden = pe.getText();

                    }

                    // Pick up clave for display
                    if ("idDcto".equals(event.getName())) {
                        pe = parser.read();
                        idDcto = pe.getText();

                    }

                    // Pick up clave for display
                    if ("idCliente".equals(event.getName())) {
                        pe = parser.read();
                        idCliente = pe.getText();

                    }

                    // Pick up clave for display
                    if ("fechaDcto".equals(event.getName())) {
                        pe = parser.read();
                        fechaDcto = pe.getText();

                    }

                    // Pick up clave for display
                    if ("vrBase".equals(event.getName())) {
                        pe = parser.read();
                        vrBase = pe.getText();

                    }

                    // Pick up clave for display
                    if ("vrPago".equals(event.getName())) {
                        pe = parser.read();
                        vrPago = pe.getText();

                    }

                    // Pick up clave for display
                    if ("vrIva".equals(event.getName())) {
                        pe = parser.read();
                        vrIva = pe.getText();

                    }

                    // Pick up clave for display
                    if ("idTipoNegocio".equals(event.getName())) {
                        pe = parser.read();
                        idTipoNegocio = pe.getText();

                    }

                    // Pick up clave for display
                    if ("vrRteFuente".equals(event.getName())) {
                        pe = parser.read();
                        vrRteFuente = pe.getText();

                    }

                    // Pick up clave for display
                    if ("vrRteIva".equals(event.getName())) {
                        pe = parser.read();
                        vrRteIva = pe.getText();

                    }

                    // Pick up clave for display
                    if ("vrRteIca".equals(event.getName())) {
                        pe = parser.read();
                        vrRteIca = pe.getText();

                    }

                    // Pick up clave for display
                    if ("nombreTercero".equals(event.getName())) {
                        pe = parser.read();
                        nombreTercero = pe.getText();

                    }

                    // Pick up clave for display
                    if ("idDctoNitCC".equals(event.getName())) {
                        pe = parser.read();
                        idDctoNitCC = pe.getText();

                    }

                    // Pick up clave for display
                    if ("vrDsctoFcro".equals(event.getName())) {
                        pe = parser.read();
                        vrDsctoFcro = pe.getText();

                    }

                    // Pick up clave for display
                    if ("vrCostoMV".equals(event.getName())) {
                        pe = parser.read();
                        vrCostoMV = pe.getText();

                    }

                    // Pick up clave for display
                    if ("vrImpoconsumo".equals(event.getName())) {
                        pe = parser.read();
                        vrImpoconsumo = pe.getText();

                    }

                    // Pick up clave for display
                    if ("porcentajeIva".equals(event.getName())) {
                        pe = parser.read();
                        porcentajeIva = pe.getText();

                        //
                        fachadaLog = new FachadaDctoBean();

                        //
                        fachadaLog.setIdLocal(idLocal);
                        fachadaLog.setIdTipoOrden(idTipoOrden);
                        fachadaLog.setIdDcto(idDcto);
                        fachadaLog.setIdCliente(idCliente);
                        fachadaLog.setFechaDcto(fechaDcto);
                        fachadaLog.setVrBase(vrBase);
                        fachadaLog.setVrPago(vrPago);
                        fachadaLog.setVrIva(vrIva);
                        fachadaLog.setIdTipoNegocio(idTipoNegocio);
                        fachadaLog.setVrRteFuente(vrRteFuente);
                        fachadaLog.setVrRteIva(vrRteIva);
                        fachadaLog.setVrRteIca(vrRteIca);
                        fachadaLog.setNombreTercero(nombreTercero);
                        fachadaLog.setIdDctoNitCC(idDctoNitCC);
                        fachadaLog.setVrDsctoFcro(vrDsctoFcro);
                        fachadaLog.setVrCostoMV(vrCostoMV);
                        fachadaLog.setVrImpoconsumo(vrImpoconsumo);
                        fachadaLog.setPorcentajeIva(porcentajeIva);

                       
                        //
                        contenedorLog.add(fachadaLog);

                    }

                    //recursion call for each <tag></tag>
                    traverse_DctoContableOrden_1_21_Tx(parser, "");
                    break;

                // For example </title?
                case Xml.END_TAG:
                    leave = true;
                    break;

                // For example </rss>
                case Xml.END_DOCUMENT:

                    leave = true;
                    break;

                // For example, the text between tags
                case Xml.TEXT:
                    break;
                case Xml.WHITESPACE:
                    break;
                default:
            }
        } while (!leave);

        //
        return contenedorLog;
    }

    // traverse_DctoContableOrden_4_Tx
    public Vector traverse_DctoContableOrden_4_Tx(XmlParser parser,
            String indent) throws Exception {

        //
        boolean leave = false;

        //
        String idLocal = new String();
        String idTipoOrden = new String();
        String idDcto = new String();
        String idDctoNitCC = new String();
        String idCliente = new String();
        String fechaDcto = new String();
        String vrCostoMV = new String();
        String vrDescuento = new String();
        String vrRteFuente = new String();
        String prefijo = new String();
        String porcentajeIva = new String();



        //
        do {
            ParseEvent event = parser.read();
            ParseEvent pe;

            switch (event.getType()) {

                // For example, <CxC>
                case Xml.START_TAG:

                    // see API doc of StartTag for more access methods
                    // Pick up idagenda for display
                    if ("idLocal".equals(event.getName())) {
                        pe = parser.read();
                        idLocal = pe.getText();

                    }

                    // Pick up clave for display
                    if ("idTipoOrden".equals(event.getName())) {
                        pe = parser.read();
                        idTipoOrden = pe.getText();

                    }

                    // Pick up clave for display
                    if ("idDcto".equals(event.getName())) {
                        pe = parser.read();
                        idDcto = pe.getText();

                    }

                    // Pick up clave for display
                    if ("idDctoNitCC".equals(event.getName())) {
                        pe = parser.read();
                        idDctoNitCC = pe.getText();

                    }

                    // Pick up clave for display
                    if ("idCliente".equals(event.getName())) {
                        pe = parser.read();
                        idCliente = pe.getText();

                    }

                    // Pick up clave for display
                    if ("fechaDcto".equals(event.getName())) {
                        pe = parser.read();
                        fechaDcto = pe.getText();

                    }

                    // Pick up clave for display
                    if ("vrCostoMV".equals(event.getName())) {
                        pe = parser.read();
                        vrCostoMV = pe.getText();

                    }

                    // Pick up clave for display
                    if ("vrDescuento".equals(event.getName())) {
                        pe = parser.read();
                        vrDescuento= pe.getText();

                    }

                    // Pick up clave for display
                    if ("vrRteFuente".equals(event.getName())) {
                        pe = parser.read();
                        vrRteFuente= pe.getText();

                    }

                    // Pick up clave for display
                    if ("prefijo".equals(event.getName())) {
                        pe = parser.read();
                        prefijo= pe.getText();

                    }

                    // Pick up clave for display
                    if ("porcentajeIva".equals(event.getName())) {
                        pe = parser.read();
                        porcentajeIva = pe.getText();

                        //
                        fachadaLog = new FachadaDctoBean();
 
                        //
                        fachadaLog.setIdLocal(idLocal);
                        fachadaLog.setIdTipoOrden(idTipoOrden);
                        fachadaLog.setIdDcto(idDcto);
                        fachadaLog.setIdDctoNitCC(idDctoNitCC);
                        fachadaLog.setIdCliente(idCliente);
                        fachadaLog.setFechaDcto(fechaDcto);
                        fachadaLog.setVrCostoMV(vrCostoMV);
                        fachadaLog.setVrDescuento(vrDescuento);
                        fachadaLog.setVrRteFuente(vrRteFuente);
                        fachadaLog.setPrefijo(prefijo);
                        fachadaLog.setPorcentajeIva(porcentajeIva);

                        //
                        contenedorLog.add(fachadaLog);

                    }

                    //recursion call for each <tag></tag>
                    traverse_DctoContableOrden_4_Tx(parser, "");
                    break;

                // For example </title?
                case Xml.END_TAG:
                    leave = true;
                    break;

                // For example </rss>
                case Xml.END_DOCUMENT:

                    leave = true;
                    break;

                // For example, the text between tags
                case Xml.TEXT:
                    break;
                case Xml.WHITESPACE:
                    break;
                default:
            }
        } while (!leave);

        //
        return contenedorLog;
    }

    // traverse_DctoContableOrdenPago_1_Tx
    public Vector traverse_DctoContableOrdenPago_1_Tx(XmlParser parser,
            String indent) throws Exception {

        //
        boolean leave = false;

        //
        String idLocal = new String();
        String idTipoOrden = new String();
        String idDcto = new String();
        String idDctoNitCC = new String();
        String idCliente = new String();
        String fechaDcto = new String();
        String vrCostoMV = new String();
        String vrDescuento = new String();
        String vrRteFuente = new String();
        String porcentajeIva = new String();

        //
        do {
            ParseEvent event = parser.read();
            ParseEvent pe;

            switch (event.getType()) {

                // For example, <CxC>
                case Xml.START_TAG:

                    // see API doc of StartTag for more access methods
                    // Pick up idagenda for display
                    if ("idLocal".equals(event.getName())) {
                        pe = parser.read();
                        idLocal = pe.getText();

                    }

                    // Pick up clave for display
                    if ("idTipoOrden".equals(event.getName())) {
                        pe = parser.read();
                        idTipoOrden = pe.getText();

                    }

                    // Pick up clave for display
                    if ("idDcto".equals(event.getName())) {
                        pe = parser.read();
                        idDcto = pe.getText();

                    }

                    // Pick up clave for display
                    if ("idDctoNitCC".equals(event.getName())) {
                        pe = parser.read();
                        idDctoNitCC = pe.getText();

                    }

                    // Pick up clave for display
                    if ("idCliente".equals(event.getName())) {
                        pe = parser.read();
                        idCliente = pe.getText();

                    }

                    // Pick up clave for display
                    if ("fechaDcto".equals(event.getName())) {
                        pe = parser.read();
                        fechaDcto = pe.getText();

                    }

                    // Pick up clave for display
                    if ("vrCostoMV".equals(event.getName())) {
                        pe = parser.read();
                        vrCostoMV = pe.getText();

                    }

                    // Pick up clave for display
                    if ("vrDescuento".equals(event.getName())) {
                        pe = parser.read();
                        vrDescuento= pe.getText();

                    }

                    // Pick up clave for display
                    if ("vrRteFuente".equals(event.getName())) {
                        pe = parser.read();
                        vrRteFuente= pe.getText();

                    }

                    // Pick up clave for display
                    if ("porcentajeIva".equals(event.getName())) {
                        pe = parser.read();
                        porcentajeIva = pe.getText();

                        //
                        fachadaLog = new FachadaDctoBean();

                        //
                        fachadaLog.setIdLocal(idLocal);
                        fachadaLog.setIdTipoOrden(idTipoOrden);
                        fachadaLog.setIdDcto(idDcto);
                        fachadaLog.setIdDctoNitCC(idDctoNitCC);
                        fachadaLog.setIdCliente(idCliente);
                        fachadaLog.setFechaDcto(fechaDcto);
                        fachadaLog.setVrCostoMV(vrCostoMV);
                        fachadaLog.setVrDescuento(vrDescuento);
                        fachadaLog.setVrRteFuente(vrRteFuente);
                        fachadaLog.setPorcentajeIva(porcentajeIva);

                        //
                        contenedorLog.add(fachadaLog);

                    }

                    //recursion call for each <tag></tag>
                    traverse_DctoContableOrdenPago_1_Tx(parser, "");
                    break;

                // For example </title?
                case Xml.END_TAG:
                    leave = true;
                    break;

                // For example </rss>
                case Xml.END_DOCUMENT:

                    leave = true;
                    break;

                // For example, the text between tags
                case Xml.TEXT:
                    break;
                case Xml.WHITESPACE:
                    break;
                default:
            }
        } while (!leave);

        //
        return contenedorLog;
    }

    // traverse_DctoContableOrden_5_Tx
    public Vector traverse_DctoContableOrden_5_Tx(XmlParser parser,
            String indent) throws Exception {

        //
        boolean leave = false;

        //
        String idLocal = new String();
        String idTipoOrden = new String();
        String idDcto = new String();
        String idCliente = new String();
        String fechaDcto = new String();
        String vrCostoMV = new String();
        String porcentajeIva = new String();

        //
        do {
            ParseEvent event = parser.read();
            ParseEvent pe;

            switch (event.getType()) {

                // For example, <CxC>
                case Xml.START_TAG:

                    // see API doc of StartTag for more access methods
                    // Pick up idagenda for display
                    if ("idLocal".equals(event.getName())) {
                        pe = parser.read();
                        idLocal = pe.getText();

                    }

                    // Pick up clave for display
                    if ("idTipoOrden".equals(event.getName())) {
                        pe = parser.read();
                        idTipoOrden = pe.getText();

                    }

                    // Pick up clave for display
                    if ("idDcto".equals(event.getName())) {
                        pe = parser.read();
                        idDcto = pe.getText();

                    }

                    // Pick up clave for display
                    if ("idCliente".equals(event.getName())) {
                        pe = parser.read();
                        idCliente = pe.getText();

                    }

                    // Pick up clave for display
                    if ("fechaDcto".equals(event.getName())) {
                        pe = parser.read();
                        fechaDcto = pe.getText();

                    }

                    // Pick up clave for display
                    if ("vrCostoMV".equals(event.getName())) {
                        pe = parser.read();
                        vrCostoMV = pe.getText();

                    }

                    // Pick up clave for display
                    if ("porcentajeIva".equals(event.getName())) {
                        pe = parser.read();
                        porcentajeIva = pe.getText();

                        //
                        fachadaLog = new FachadaDctoBean();

                        //
                        fachadaLog.setIdLocal(idLocal);
                        fachadaLog.setIdTipoOrden(idTipoOrden);
                        fachadaLog.setIdDcto(idDcto);
                        fachadaLog.setIdDctoNitCC(idDcto);
                        fachadaLog.setIdCliente(idCliente);
                        fachadaLog.setFechaDcto(fechaDcto);
                        fachadaLog.setVrCostoMV(vrCostoMV);
                        fachadaLog.setPorcentajeIva(porcentajeIva);

                        //
                        contenedorLog.add(fachadaLog);

                    }

                    //recursion call for each <tag></tag>
                    traverse_DctoContableOrden_5_Tx(parser, "");
                    break;

                // For example </title?
                case Xml.END_TAG:
                    leave = true;
                    break;

                // For example </rss>
                case Xml.END_DOCUMENT:

                    leave = true;
                    break;

                // For example, the text between tags
                case Xml.TEXT:
                    break;
                case Xml.WHITESPACE:
                    break;
                default:
            }
        } while (!leave);

        //
        return contenedorLog;
    }

    // traverse_DctoContableOrden_500_Tx
    public Vector traverse_DctoContableOrden_500_Tx(XmlParser parser,
            String indent) throws Exception {

        //
        boolean leave = false;

        //
        String idLocal = new String();
        String idTipoOrden = new String();
        String idDcto = new String();
        String idCliente = new String();
        String fechaDcto = new String();
        String vrBase = new String();
        String porcentajeIva = new String();

        //
        do {
            ParseEvent event = parser.read();
            ParseEvent pe;

            switch (event.getType()) {

                // For example, <CxC>
                case Xml.START_TAG:

                    // see API doc of StartTag for more access methods
                    // Pick up idagenda for display
                    if ("idLocal".equals(event.getName())) {
                        pe = parser.read();
                        idLocal = pe.getText();

                    }

                    // Pick up clave for display
                    if ("idTipoOrden".equals(event.getName())) {
                        pe = parser.read();
                        idTipoOrden = pe.getText();

                    }

                    // Pick up clave for display
                    if ("idDcto".equals(event.getName())) {
                        pe = parser.read();
                        idDcto = pe.getText();

                    }

                    // Pick up clave for display
                    if ("idCliente".equals(event.getName())) {
                        pe = parser.read();
                        idCliente = pe.getText();

                    }

                    // Pick up clave for display
                    if ("fechaDcto".equals(event.getName())) {
                        pe = parser.read();
                        fechaDcto = pe.getText();

                    }

                    // Pick up clave for display
                    if ("vrBase".equals(event.getName())) {
                        pe = parser.read();
                        vrBase = pe.getText();

                    }

                    // Pick up clave for display
                    if ("porcentajeIva".equals(event.getName())) {
                        pe = parser.read();
                        porcentajeIva = pe.getText();

                        //
                        fachadaLog = new FachadaDctoBean();

                        //
                        fachadaLog.setIdLocal(idLocal);
                        fachadaLog.setIdTipoOrden(idTipoOrden);
                        fachadaLog.setIdDcto(idDcto);
                        fachadaLog.setIdDctoNitCC(idDcto);
                        fachadaLog.setIdCliente(idCliente);
                        fachadaLog.setFechaDcto(fechaDcto);
                        fachadaLog.setVrBase(vrBase);
                        fachadaLog.setPorcentajeIva(porcentajeIva);

                        //
                        contenedorLog.add(fachadaLog);

                    }

                    //recursion call for each <tag></tag>
                    traverse_DctoContableOrden_500_Tx(parser, "");
                    break;

                // For example </title?
                case Xml.END_TAG:
                    leave = true;
                    break;

                // For example </rss>
                case Xml.END_DOCUMENT:

                    leave = true;
                    break;

                // For example, the text between tags
                case Xml.TEXT:
                    break;
                case Xml.WHITESPACE:
                    break;
                default:
            }
        } while (!leave);

        //
        return contenedorLog;
    }
}
