package com.solucionesweb.losbeans.negocio;

// Importa la clase helper JDBCAccess que contiene los accesos a la base de datos
import com.solucionesweb.lasayudas.JDBCAccess;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaAgendaLogVisitaBean;

// Importa los paquetes de java
import java.sql.*;
import java.io.*;
import java.util.*;
import java.io.IOException.*;

// Importa los paquetes de javax
import javax.naming.*;

public class AgendaLogVisitaBean extends FachadaAgendaLogVisitaBean
        implements Serializable {

    // Nombre JNDI del Data Source que esta clase requiere
    private static final String DATA_SOURCE_NAME = "CommerceAccess";
    // Clase helper usada para acceder a la base de datos
    private JDBCAccess jdbcAccess;

    // Metodo constructor por defecto sin parametros
    public AgendaLogVisitaBean() {

        // Al instanciar el Bean establece la conexion a la base de datos
        jdbcAccess = new JDBCAccess(DATA_SOURCE_NAME);
    }

    // seleccionaUnClienteFechaVisita
    public Vector seleccionaUnClienteFechaVisita() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        String selectStr =
                "SELECT tblAgendaLogVisitas.idLog,          "
                + "        tblAgendaLogVisitas.idCliente,     "
                + "        tblAgendaLogVisitas.idUsuario,     "
                + "       tblAgendaLogVisitas.idLocalTercero, "
                + "        tblAgendaLogVisitas.idPeriodo,     "
                + "        tblAgendaLogVisitas.fechaVisita,   "
                + "        tblAgendaLogVisitas.idEstadoVisita,"
                + "        tblAgendaLogVisitas.estado         "
                + "FROM tblAgendaLogVisitas                   "
                + "WHERE tblAgendaLogVisitas.idCliente   =    "
                + getIdCliente() + "                          "
                + "AND   tblAgendaLogVisitas.fechaVisita =    "
                + getFechaVisitaStrOracle();

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaAgendaLogVisitaBean fachadaBean;

            while (rs.next()) {

                fachadaBean = new FachadaAgendaLogVisitaBean();

                fachadaBean.setIdLog(rs.getInt("idLog"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setIdUsuario(rs.getInt("idUsuario"));
                fachadaBean.setIdLocalTercero(rs.getInt("idLocalTercero"));
                fachadaBean.setIdPeriodo(rs.getInt("idPeriodo"));
                fachadaBean.setFechaVisitaStr(rs.getString("fechaVisita"));
                fachadaBean.setIdEstadoVisita(rs.getInt("idEstadoVisita"));
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

            // Cierra el connection
            jdbcAccess.cleanup(connection, selectStatement, null);
            return contenedor;

        }
    }

    // maximoIdLog
    public int maximoIdLog() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        int maximoIdLog = 0;

        Connection connection = null;

        String selectStr =
                "SELECT MAX(tblAgendaLogVisitas.idLog) " +
                "                          AS idMaximo "
                + "FROM tblAgendaLogVisitas            ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            if (rs.next()) {

                maximoIdLog = rs.getInt("idMaximo");

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
            return maximoIdLog;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {

            // Cierra el connection
            jdbcAccess.cleanup(connection, selectStatement, null);
            return maximoIdLog;

        }
    }

    // actualizaLogVisitaUsuario
    public boolean actualizaLogVisitaUsuario(int xEstadoAnterior) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        boolean validaOk = false;
        Connection connection = null;

        String updateStr =
                "UPDATE tblAgendaLogVisitas             "
                + "SET tblAgendaLogVisitas.estado       = "
                + getEstado() + "                         "
                + "WHERE tblAgendaLogVisitas.idUsuario  = "
                + getIdUsuario() + "                      "
                + "AND tblAgendaLogVisitas.estado      =  "
                + xEstadoAnterior;


        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(updateStr);

            // Obtiene el resultset
            selectStatement.executeUpdate();

            //
            validaOk = true;

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

            // Cierra el connection
            jdbcAccess.cleanup(connection, selectStatement, null);
            return validaOk;
        }
    }

    // actualizaEstadoTx
    public boolean actualizaEstadoTx() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        boolean validaOk = false;
        Connection connection = null;

        String updateStr =
                "UPDATE tblAgendaLogVisitas                 "
                + "SET    tblAgendaLogVisitas.idEstadoTx =  "
                + getIdEstadoTx() + "                       "
                + "WHERE  tblAgendaLogVisitas.idLocal    =  "
                + getIdLocal() + "                          "
                + "AND    tblAgendaLogVisitas.idTipoOrden=  "
                + getIdTipoOrden() + "                      "
                + "AND    tblAgendaLogVisitas.idLog      =  "
                + getIdLog();

        
        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(updateStr);

            // Obtiene el resultset
            selectStatement.executeUpdate();

            //
            validaOk = true;

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

            // Cierra el connection
            jdbcAccess.cleanup(connection, selectStatement, null);
            return validaOk;
        }
    }

    // validaLogOcupado
    public boolean validaLogOcupado() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        boolean validaOk = false;
        Connection connection = null;

        String updateStr =
                "SELECT tblAgendaLogVisitas.IDLOG                  "
                + "FROM   tblAgendaLogVisitas                        "
                + "WHERE  tblAgendaLogVisitas.idusuario        =     "
                + getIdUsuario() + "                                 "
                + "AND    tblAgendaLogVisitas.FECHAVISITA      =    '"
                + getFechaVisitaSqlServer() + "'                     "
                + "AND    tblAgendaLogVisitas.estado           =     "
                + getEstado() + "                                    "
                + "AND ( EXISTS (SELECT tblDctosOrdenes.*            "
                + "            FROM   tblDctosOrdenes                "
                + "            INNER JOIN tblDctosOrdenesDetalle     "
                + "            ON tblDctosOrdenes.IDLOCAL      =     "
                + "               tblDctosOrdenesDetalle.IDLOCAL     "
                + "            AND tblDctosOrdenes.IDTIPOORDEN =     "
                + "               tblDctosOrdenesDetalle.IDTIPOORDEN "
                + "            AND tblDctosOrdenes.IDORDEN     =     "
                + "                   tblDctosOrdenesDetalle.IDORDEN "
                + "          WHERE tblAgendaLogVisitas.IDLOG   =     "
                + "                           tblDctosOrdenes.idLog) "
                + "OR  EXISTS ( SELECT tblPagos.*                    "
                + "             FROM   tblPagos                      "
                + "             INNER JOIN tblPagosMedios            "
                + "                    ON tblPagos.idLocal      =    "
                + "                          tblPagosMedios.idLocal  "
                + "                    AND tblPagos.idTipoOrden =    "
                + "                       tblPagosMedios.idTipoOrden "
                + "                    AND tblPagos.idRecibo    =    "
                + "                          tblPagosMedios.idRecibo "
                + "                    AND tblPagos.indicador   =    "
                + "                         tblPagosMedios.indicador "
                + "               AND tblAgendaLogVisitas.idLog =    "
                + "                                 tblPagos.idLog)) ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(updateStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            //
            if (rs.next()) {

                //
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

            // Cierra el connection
            jdbcAccess.cleanup(connection, selectStatement, null);
            return validaOk;
        }
    }

    // validaTipoOrdenProceso
    public boolean validaTipoOrdenProceso(int xIdTipoOrden) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        boolean validaOk = false;
        Connection connection = null;

        String updateStr =
                "SELECT tblAgendaLogVisitas.IDLOG                  "
                + "FROM   tblAgendaLogVisitas                        "
                + "WHERE  tblAgendaLogVisitas.idusuario        =     "
                + getIdUsuario() + "                                 "
                + "AND    tblAgendaLogVisitas.FECHAVISITA      =    '"
                + getFechaVisitaSqlServer() + "'                     "
                + "AND    tblAgendaLogVisitas.estado           =     "
                + getEstado() + "                                    "
                + "AND ( EXISTS (SELECT tblDctosOrdenes.*            "
                + "            FROM   tblDctosOrdenes                "
                + "            INNER JOIN tblDctosOrdenesDetalle     "
                + "            ON tblDctosOrdenes.IDLOCAL      =     "
                + "               tblDctosOrdenesDetalle.IDLOCAL     "
                + "            AND tblDctosOrdenes.IDTIPOORDEN =     "
                + "               tblDctosOrdenesDetalle.IDTIPOORDEN "
                + "            AND tblDctosOrdenes.IDORDEN     =     "
                + "                   tblDctosOrdenesDetalle.IDORDEN "
                + "          WHERE tblAgendaLogVisitas.IDLOG   =     "
                + "                           tblDctosOrdenes.idLog  "
                + "          AND   tblDctosOrdenes.IDTIPOORDEN =     "
                + xIdTipoOrden + " )                                 "
                + "OR  EXISTS ( SELECT tblPagos.*                    "
                + "             FROM   tblPagos                      "
                + "             INNER JOIN tblPagosMedios            "
                + "             ON tblPagos.idLocal      =           "
                + "                          tblPagosMedios.idLocal  "
                + "             AND tblPagos.idTipoOrden =           "
                + "                       tblPagosMedios.idTipoOrden "
                + "             AND tblPagos.idRecibo    =           "
                + "                          tblPagosMedios.idRecibo "
                + "             AND tblPagos.indicador   =           "
                + "                         tblPagosMedios.indicador "
                + "             AND tblAgendaLogVisitas.idLog =      "
                + "                                   tblPagos.idLog "
                + "             WHERE tblPagos.idTipoOrden      =    "
                + xIdTipoOrden + " )) ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(updateStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            //
            if (rs.next()) {

                //
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

            // Cierra el connection
            jdbcAccess.cleanup(connection, selectStatement, null);
            return validaOk;
        }
    }

    // validaDiferenteOrdenProceso
    public boolean validaDiferenteOrdenProceso(int xIdTipoOrden) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        boolean validaOk = false;
        Connection connection = null;

        String updateStr =
                "SELECT tblAgendaLogVisitas.IDLOG                  "
                + "FROM   tblAgendaLogVisitas                        "
                + "WHERE  tblAgendaLogVisitas.idusuario        =     "
                + getIdUsuario() + "                                 "
                + "AND    tblAgendaLogVisitas.FECHAVISITA      =    '"
                + getFechaVisitaSqlServer() + "'                     "
                + "AND    tblAgendaLogVisitas.estado           =     "
                + getEstado() + "                                    "
                + "AND ( EXISTS (SELECT tblDctosOrdenes.*            "
                + "            FROM   tblDctosOrdenes                "
                + "            INNER JOIN tblDctosOrdenesDetalle     "
                + "            ON tblDctosOrdenes.IDLOCAL      =     "
                + "               tblDctosOrdenesDetalle.IDLOCAL     "
                + "            AND tblDctosOrdenes.IDTIPOORDEN =     "
                + "               tblDctosOrdenesDetalle.IDTIPOORDEN "
                + "            AND tblDctosOrdenes.IDORDEN     =     "
                + "                   tblDctosOrdenesDetalle.IDORDEN "
                + "          WHERE tblAgendaLogVisitas.IDLOG   =     "
                + "                           tblDctosOrdenes.idLog  "
                + "          AND   tblDctosOrdenes.IDTIPOORDEN !=    "
                + xIdTipoOrden + " )                                 "
                + "OR  EXISTS ( SELECT tblPagos.*                    "
                + "             FROM   tblPagos                      "
                + "             INNER JOIN tblPagosMedios            "
                + "             ON tblPagos.idLocal      =           "
                + "                          tblPagosMedios.idLocal  "
                + "             AND tblPagos.idTipoOrden =           "
                + "                       tblPagosMedios.idTipoOrden "
                + "             AND tblPagos.idRecibo    =           "
                + "                          tblPagosMedios.idRecibo "
                + "             AND tblPagos.indicador   =           "
                + "                         tblPagosMedios.indicador "
                + "             AND tblAgendaLogVisitas.idLog =      "
                + "                                   tblPagos.idLog "
                + "             WHERE tblPagos.idTipoOrden   !=      "
                + xIdTipoOrden + " )) ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(updateStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            //
            if (rs.next()) {

                //
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

            // Cierra el connection
            jdbcAccess.cleanup(connection, selectStatement, null);
            return validaOk;
        }
    }

    // listaOrdenSuspendidoFCH
    public FachadaAgendaLogVisitaBean listaOrdenSuspendidoFCH(int xIdTipoOrden) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        //
        FachadaAgendaLogVisitaBean fachadaBean = new FachadaAgendaLogVisitaBean();

        //
        Connection connection = null;

        String updateStr =
                "SELECT tblAgendaLogVisitas.idLog,                 "
                + "        tblAgendaLogVisitas.idCliente,            "
                + "        tblAgendaLogVisitas.idUsuario,            "
                + "        tblAgendaLogVisitas.idLocalTercero,        "
                + "        tblAgendaLogVisitas.idPeriodo,            "
                + "        tblAgendaLogVisitas.fechaVisita,          "
                + "        CONVERT(CHAR(10),                         "
                + "         tblAgendaLogVisitas.fechaVisita,111)     "
                + "                              AS fechaVisita,     "
                + "        tblAgendaLogVisitas.idEstadoVisita,       "
                + "        tblAgendaLogVisitas.estado                "
                + "FROM   tblAgendaLogVisitas                        "
                + "WHERE  tblAgendaLogVisitas.idusuario        =     "
                + getIdUsuario() + "                                 "
                + "AND    tblAgendaLogVisitas.FECHAVISITA      =    '"
                + getFechaVisitaSqlServer() + "'                     "
                + "AND    tblAgendaLogVisitas.estado           =     "
                + getEstado() + "                                    "
                + "AND ( EXISTS (SELECT tblDctosOrdenes.*            "
                + "            FROM   tblDctosOrdenes                "
                + "            INNER JOIN tblDctosOrdenesDetalle     "
                + "            ON tblDctosOrdenes.IDLOCAL      =     "
                + "               tblDctosOrdenesDetalle.IDLOCAL     "
                + "            AND tblDctosOrdenes.IDTIPOORDEN =     "
                + "               tblDctosOrdenesDetalle.IDTIPOORDEN "
                + "            AND tblDctosOrdenes.IDORDEN     =     "
                + "                   tblDctosOrdenesDetalle.IDORDEN "
                + "          WHERE tblAgendaLogVisitas.IDLOG   =     "
                + "                           tblDctosOrdenes.idLog  "
                + "          AND   tblDctosOrdenes.IDTIPOORDEN =     "
                + xIdTipoOrden + " )                                 "
                + "OR  EXISTS ( SELECT tblPagos.*                    "
                + "             FROM   tblPagos                      "
                + "             INNER JOIN tblPagosMedios            "
                + "             ON tblPagos.idLocal      =           "
                + "                          tblPagosMedios.idLocal  "
                + "             AND tblPagos.idTipoOrden =           "
                + "                       tblPagosMedios.idTipoOrden "
                + "             AND tblPagos.idRecibo    =           "
                + "                          tblPagosMedios.idRecibo "
                + "             AND tblPagos.indicador   =           "
                + "                         tblPagosMedios.indicador "
                + "             AND tblAgendaLogVisitas.idLog =      "
                + "                                   tblPagos.idLog "
                + "             WHERE tblPagos.idTipoOrden      =    "
                + xIdTipoOrden + " )) ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(updateStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            //
            if (rs.next()) {

                //
                fachadaBean.setIdLog(rs.getInt("idLog"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setIdUsuario(rs.getInt("idUsuario"));
                fachadaBean.setIdLocalTercero(rs.getInt("idLocalTercero"));
                fachadaBean.setIdPeriodo(rs.getInt("idPeriodo"));
                fachadaBean.setFechaVisita(rs.getString("fechaVisita"));
                fachadaBean.setIdEstadoVisita(rs.getInt("idEstadoVisita"));
                fachadaBean.setEstado(rs.getInt("estado"));



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

            // Cierra el connection
            jdbcAccess.cleanup(connection, selectStatement, null);
            return fachadaBean;
        }
    }

    // listaLogSuspendidoFCH
    public FachadaAgendaLogVisitaBean listaLogSuspendidoFCH(int xIdTipoOrden) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        //
        FachadaAgendaLogVisitaBean fachadaBean = new FachadaAgendaLogVisitaBean();

        //
        Connection connection = null;

        String updateStr =
                "SELECT tblAgendaLogVisitas.idLog,                 "
                + "        tblAgendaLogVisitas.idCliente,            "
                + "        tblAgendaLogVisitas.idUsuario,            "
                + "        tblAgendaLogVisitas.idLocalTercero,        "
                + "        tblAgendaLogVisitas.idPeriodo,            "
                + "        tblAgendaLogVisitas.fechaVisita,          "
                + "        CONVERT(CHAR(10),                         "
                + "         tblAgendaLogVisitas.fechaVisita,111)     "
                + "                              AS fechaVisita,     "
                + "        tblAgendaLogVisitas.idEstadoVisita,       "
                + "        tblAgendaLogVisitas.estado                "
                + "FROM   tblAgendaLogVisitas                        "
                + "WHERE  tblAgendaLogVisitas.idLog            =     "
                + getIdLog() + "                                     "
                + "AND    tblAgendaLogVisitas.estado           =     "
                + getEstado() + "                                    "
                + "AND ( EXISTS (SELECT tblDctosOrdenes.*            "
                + "            FROM   tblDctosOrdenes                "
                + "            INNER JOIN tblDctosOrdenesDetalle     "
                + "            ON tblDctosOrdenes.IDLOCAL      =     "
                + "               tblDctosOrdenesDetalle.IDLOCAL     "
                + "            AND tblDctosOrdenes.IDTIPOORDEN =     "
                + "               tblDctosOrdenesDetalle.IDTIPOORDEN "
                + "            AND tblDctosOrdenes.IDORDEN     =     "
                + "                   tblDctosOrdenesDetalle.IDORDEN "
                + "          WHERE tblAgendaLogVisitas.IDLOG   =     "
                + "                           tblDctosOrdenes.idLog  "
                + "          AND   tblDctosOrdenes.IDTIPOORDEN =     "
                + xIdTipoOrden + " )                                 "
                + "OR  EXISTS ( SELECT tblPagos.*                    "
                + "             FROM   tblPagos                      "
                + "             INNER JOIN tblPagosMedios            "
                + "             ON tblPagos.idLocal      =           "
                + "                          tblPagosMedios.idLocal  "
                + "             AND tblPagos.idTipoOrden =           "
                + "                       tblPagosMedios.idTipoOrden "
                + "             AND tblPagos.idRecibo    =           "
                + "                          tblPagosMedios.idRecibo "
                + "             AND tblPagos.indicador   =           "
                + "                         tblPagosMedios.indicador "
                + "             AND tblAgendaLogVisitas.idLog =      "
                + "                                   tblPagos.idLog "
                + "             WHERE tblPagos.idTipoOrden      =    "
                + xIdTipoOrden + " )) ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(updateStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            //
            if (rs.next()) {

                //
                fachadaBean.setIdLog(rs.getInt("idLog"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setIdUsuario(rs.getInt("idUsuario"));
                fachadaBean.setIdLocalTercero(rs.getInt("idLocalTercero"));
                fachadaBean.setIdPeriodo(rs.getInt("idPeriodo"));
                fachadaBean.setFechaVisita(rs.getString("fechaVisita"));
                fachadaBean.setIdEstadoVisita(rs.getInt("idEstadoVisita"));
                fachadaBean.setEstado(rs.getInt("estado"));



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

            // Cierra el connection
            jdbcAccess.cleanup(connection, selectStatement, null);
            return fachadaBean;
        }
    }

    // actualizaVisita
    public boolean actualizaVisita(int xEstadoAnterior) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        boolean validaOk = false;
        Connection connection = null;

        String updateStr =
                "UPDATE tblAgendaLogVisitas             "
                + "SET tblAgendaLogVisitas.estado       = "
                + getEstado() + ",                        "
                + "tblAgendaLogVisitas.fechaVisita      ='"
                + getFechaVisitaSqlServer() + "'          "
                + "WHERE tblAgendaLogVisitas.idUsuario  = "
                + getIdUsuario() + "                      "
                + "AND tblAgendaLogVisitas.estado      =  "
                + xEstadoAnterior;


        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(updateStr);

            // Obtiene el resultset
            selectStatement.executeUpdate();

            //
            validaOk = true;

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

            // Cierra el connection
            jdbcAccess.cleanup(connection, selectStatement, null);
            return validaOk;
        }
    }

    // seleccionaVisitaEstadoFecha
    public FachadaAgendaLogVisitaBean seleccionaVisitaEstadoFechaSql() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        // Variable de fachada de los datos
        FachadaAgendaLogVisitaBean fachadaBean = new FachadaAgendaLogVisitaBean();

        Connection connection = null;

        String selectStr =
                "SELECT tblAgendaLogVisitas.idLog,             "
                + "        tblAgendaLogVisitas.idCliente,        "
                + "        tblAgendaLogVisitas.idUsuario,        "
                + "        tblAgendaLogVisitas.idLocalTercero,   "
                + "        tblAgendaLogVisitas.idPeriodo,        "
                + "        tblAgendaLogVisitas.fechaVisita,      "
                + "        CONVERT(CHAR(10),                     "
                + "         tblAgendaLogVisitas.fechaVisita,111) "
                + "                              AS fechaVisita, "
                + "        tblAgendaLogVisitas.idEstadoVisita,   "
                + "        tblAgendaLogVisitas.estado            "
                + "FROM tblAgendaLogVisitas                      "
                + "WHERE tblAgendaLogVisitas.estado      =       "
                + getEstado() + "                                "
                + "AND   CONVERT(CHAR(10),                       "
                + "  tblAgendaLogVisitas.fechaVisita,111)     = '"
                + getFechaVisitaCorta() + "'                       "
                + "AND   tblAgendaLogVisitas.idUsuario   =       "
                + getIdUsuario();


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
                fachadaBean = new FachadaAgendaLogVisitaBean();

                //
                fachadaBean.setIdLog(rs.getInt("idLog"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setIdUsuario(rs.getInt("idUsuario"));
                fachadaBean.setIdLocalTercero(rs.getInt("idLocalTercero"));
                fachadaBean.setIdPeriodo(rs.getInt("idPeriodo"));
                fachadaBean.setFechaVisitaStr(rs.getString("fechaVisita"));
                fachadaBean.setIdEstadoVisita(rs.getInt("idEstadoVisita"));
                fachadaBean.setEstado(rs.getInt("estado"));
                fachadaBean.setFechaVisita(rs.getString("fechaVisita"));

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

            // Cierra connection
            jdbcAccess.cleanup(connection, selectStatement, null);
            return fachadaBean;

        }
    }

    public boolean activaLogUsuario() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        boolean validaOk = false;
        Connection connection = null;

        String updateStr =
                "UPDATE tblAgendaLogVisitas             "
                + "SET tblAgendaLogVisitas.estado       = "
                + getEstado() + ",                        "
                + "tblAgendaLogVisitas.idUsuario        = "
                + getIdUsuario() + ",                     "
                + "tblAgendaLogVisitas.fechaVisita      ='"
                + getFechaVisitaSqlServer() + "'          "
                + "WHERE tblAgendaLogVisitas.idLog      = "
                + getIdLog() + "                          ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(updateStr);

            // Obtiene el resultset
            selectStatement.executeUpdate();

            //
            validaOk = true;

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

            // Cierra el connection
            jdbcAccess.cleanup(connection, selectStatement, null);
            return validaOk;
        }
    }

    // actualizaLogVisita
    public boolean actualizaLogVisita() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        boolean validaOk = false;
        Connection connection = null;

        String updateStr =
                "UPDATE tblAgendaLogVisitas                  "
                + "SET tblAgendaLogVisitas.estado      = ( ? ) "
                + "WHERE tblAgendaLogVisitas.idLog     = ( ? ) ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(updateStr);

            //inicializa los valores de los parametros
            selectStatement.setInt(1, getEstado());
            selectStatement.setInt(2, getIdLog());

            // Obtiene el resultset
            selectStatement.executeUpdate();

            //
            validaOk = true;

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

            // Cierra el connection
            jdbcAccess.cleanup(connection, selectStatement, null);
            return validaOk;

        }
    }

    // finalizaVisita
    public boolean finalizaVisita() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        boolean validaOk = false;
        Connection connection = null;

        //
        String updateStr =
                "UPDATE tblAgendaLogVisitas                 "
                + "SET tblAgendaLogVisitas.estado         = "
                + getEstado() + ",                          "
                + "    tblAgendaLogVisitas.idEstadoVisita = "
                + getIdEstadoVisita() + ",                  "
                + "    tblAgendaLogVisitas.idTipoOrden    = "
                + getIdTipoOrden() + ",                     "
                + "    tblAgendaLogVisitas.idEstadoTx     = "
                + getIdEstadoTx() + ",                      "
                + "    tblAgendaLogVisitas.idLocal        = "
                + getIdLocal() + ",                         "
                + "    tblAgendaLogVisitas.ipTx           ='"
                + getIpTx() + "',                           "
                + "    tblAgendaLogVisitas.fechaTx        ='"
                + getFechaTx() + "'                         "
                + "WHERE tblAgendaLogVisitas.idLog        = "
                + getIdLog();

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(updateStr);

            // Obtiene el resultset
            selectStatement.executeUpdate();

            //
            validaOk = true;
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

            // Cierra el connection
            jdbcAccess.cleanup(connection, selectStatement, null);
            return validaOk;
        }
    }

    // finaliza
    public boolean finaliza() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        boolean validaOk = false;
        Connection connection = null;

        //
        String updateStr =
                "UPDATE tblAgendaLogVisitas                        "
                + "SET tblAgendaLogVisitas.estado         =        "
                + getEstado() + ",                                 "
                + "    tblAgendaLogVisitas.idCliente      =       '"
                + getIdCliente() + "',                             "
                + "    tblAgendaLogVisitas.idEstadoVisita =        "
                + getIdEstadoVisita() + ",                         "
                + "    tblAgendaLogVisitas.idTipoOrden    =        "
                + getIdTipoOrden() + ",                            "
                + "    tblAgendaLogVisitas.idEstadoTx     =        "
                + getIdEstadoTx() + ",                             "
                + "    tblAgendaLogVisitas.idLocal        =        "
                + getIdLocal() + ",                                "
                + "    tblAgendaLogVisitas.ipTx           =       '"
                + getIpTx() + "',                                  "
                + "    tblAgendaLogVisitas.fechaTx        =       '"
                + getFechaTx() + "'                                "
                + "WHERE tblAgendaLogVisitas.idLog        =        "
                + getIdLog();

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(updateStr);

            // Obtiene el resultset
            selectStatement.executeUpdate();

            //
            validaOk = true;
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

            // Cierra el connection
            jdbcAccess.cleanup(connection, selectStatement, null);
            return validaOk;
        }
    }

    public boolean supendeVisita() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        boolean validaOk = false;
        Connection connection = null;

        String updateStr =
                "UPDATE tblAgendaLogVisitas                      "
                + "SET tblAgendaLogVisitas.estado         = ( ? ), "
                + "    tblAgendaLogVisitas.idEstadoVisita = ( ? )  "
                + "WHERE tblAgendaLogVisitas.idLog        = ( ? )  ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(updateStr);

            //inicializa los valores de los parametros
            selectStatement.setInt(1, getEstado());
            selectStatement.setInt(2, getIdEstadoVisita());
            selectStatement.setInt(3, getIdLog());

            // Obtiene el resultset
            selectStatement.executeUpdate();

            //
            validaOk = true;
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

            // Cierra el connection
            jdbcAccess.cleanup(connection, selectStatement, null);
            return validaOk;
        }
    }

    // ingresaLogVisita
    public boolean ingresaLogVisita() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        boolean validaOk = false;
        Connection connection = null;

        String updateStr =
                "INSERT INTO tblAgendaLogVisitas (idLog,           "
                + "                                 idCliente,       "
                + "                                 idUsuario,       "
                + "                                 idLocalTercero,  "
                + "                                 idPeriodo,       "
                + "                                 fechaVisita,     "
                + "                                 idEstadoVisita,  "
                + "                                 estado,          "
                + "                                 fechaTxInicio)   "
                + "VALUES ( " + getIdLog() + ",'"
                + getIdCliente() + "',"
                + getIdUsuario() + ", "
                + getIdLocalTercero() + ", "
                + getIdPeriodo() + ",'"
                + getFechaVisitaSqlServer() + "', "
                + getIdEstadoVisita() + ", "
                + getEstado() + ","
                + " GETDATE() ) ";

       PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(updateStr);

            // Obtiene el resultset
            selectStatement.executeUpdate();

            //
            validaOk = true;

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

            // Cierra connection
            jdbcAccess.cleanup(connection, selectStatement, null);
            return validaOk;
        }
    }

    // ingresa
    public boolean ingresa() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        boolean validaOk = false;
        Connection connection = null;

        String updateStr =
                "INSERT INTO tblAgendaLogVisitas (idLog,           "
                + "                                 idCliente,       "
                + "                                 idUsuario,       "
                + "                                 idLocalTercero,  "
                + "                                 idPeriodo,       "
                + "                                 fechaVisita,     "
                + "                                 idEstadoVisita,  "
                + "                                 estado,          "
                + "                                 idTipoOrden,     "
                + "                                 fechaTxInicio)   "
                + "VALUES ( " + getIdLog() + ",'"
                + getIdCliente() + "',"
                + getIdUsuario() + ", "
                + getIdLocalTercero() + ", "
                + getIdPeriodo() + ",'"
                + getFechaVisitaSqlServer() + "', "
                + getIdEstadoVisita() + ", "
                + getEstado() + ", "
                + getIdTipoOrden() + ","
                + "GETDATE() ) ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(updateStr);

            // Obtiene el resultset
            selectStatement.executeUpdate();

            //
            validaOk = true;

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

            // Cierra connection
            jdbcAccess.cleanup(connection, selectStatement, null);
            return validaOk;
        }
    }

    // seleccionaVisitaEstadoFecha
    public FachadaAgendaLogVisitaBean seleccionaVisitaEstadoFecha() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        // Variable de fachada de los datos
        FachadaAgendaLogVisitaBean fachadaBean = new FachadaAgendaLogVisitaBean();

        Connection connection = null;

        String selectStr =
                "SELECT tblAgendaLogVisitas.idLog,             "
                + "        tblAgendaLogVisitas.idCliente,        "
                + "        tblAgendaLogVisitas.idUsuario,        "
                + "        tblAgendaLogVisitas.idLocalTercero,   "
                + "        tblAgendaLogVisitas.idPeriodo,        "
                + "        tblAgendaLogVisitas.fechaVisita,      "
                + "        CONVERT(CHAR(10),                     "
                + "         tblAgendaLogVisitas.fechaVisita,111) "
                + "                              AS fechaVisita, "
                + "        tblAgendaLogVisitas.idEstadoVisita,   "
                + "        tblAgendaLogVisitas.estado            "
                + "FROM tblAgendaLogVisitas                      "
                + "WHERE tblAgendaLogVisitas.estado      =       "
                + getEstado() + "                                "
                + "AND   CONVERT(CHAR(10),                       "
                + "  tblAgendaLogVisitas.fechaVisita,111)     = '"
                + getFechaVisitaStr() + "'                       "
                + "AND   tblAgendaLogVisitas.idUsuario   =       "
                + getIdUsuario();

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
                fachadaBean = new FachadaAgendaLogVisitaBean();

                //
                fachadaBean.setIdLog(rs.getInt("idLog"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setIdUsuario(rs.getInt("idUsuario"));
                fachadaBean.setIdLocalTercero(rs.getInt("idLocalTercero"));
                fachadaBean.setIdPeriodo(rs.getInt("idPeriodo"));
                fachadaBean.setFechaVisitaStr(rs.getString("fechaVisita"));
                fachadaBean.setIdEstadoVisita(rs.getInt("idEstadoVisita"));
                fachadaBean.setEstado(rs.getInt("estado"));
                fachadaBean.setFechaVisita(rs.getString("fechaVisita"));

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

            // Cierra connection
            jdbcAccess.cleanup(connection, selectStatement, null);
            return fachadaBean;

        }
    }

    public FachadaAgendaLogVisitaBean listaLogActivo() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        // Variable de fachada de los datos
        FachadaAgendaLogVisitaBean fachadaBean = new FachadaAgendaLogVisitaBean();

        Connection connection = null;

        String selectStr =
                "SELECT tblAgendaLogVisitas.idLog,             "
                + "        tblAgendaLogVisitas.idCliente,        "
                + "        tblAgendaLogVisitas.idUsuario,        "
                + "        tblAgendaLogVisitas.idLocalTercero,   "
                + "        tblAgendaLogVisitas.idPeriodo,        "
                + "        tblAgendaLogVisitas.fechaVisita,      "
                + "        CONVERT(CHAR(10),                     "
                + "         tblAgendaLogVisitas.fechaVisita,111) "
                + "                              AS fechaVisita, "
                + "        tblAgendaLogVisitas.idEstadoVisita,   "
                + "        tblAgendaLogVisitas.estado            "
                + "FROM tblAgendaLogVisitas                      "
                + "WHERE tblAgendaLogVisitas.estado      =       "
                + getEstado() + "                                "
                + "AND   CONVERT(CHAR(10),                       "
                + "  tblAgendaLogVisitas.fechaVisita,111)     = '"
                + getFechaVisita() + "'                          "
                + "AND   tblAgendaLogVisitas.idUsuario   =       "
                + getIdUsuario();

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            if (rs.next()) {

                fachadaBean = new FachadaAgendaLogVisitaBean();

                //
                fachadaBean.setIdLog(rs.getInt("idLog"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setIdUsuario(rs.getInt("idUsuario"));
                fachadaBean.setIdLocalTercero(rs.getInt("idLocalTercero"));
                fachadaBean.setIdPeriodo(rs.getInt("idPeriodo"));
                fachadaBean.setFechaVisita(rs.getString("fechaVisita"));
                fachadaBean.setIdEstadoVisita(rs.getInt("idEstadoVisita"));
                fachadaBean.setEstado(rs.getInt("estado"));

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

            // Cierra connection
            jdbcAccess.cleanup(connection, selectStatement, null);
            return fachadaBean;

        }
    }

    // listaLogFachada
    public FachadaAgendaLogVisitaBean listaLogFachada() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        // Variable de fachada de los datos
        FachadaAgendaLogVisitaBean fachadaBean = new FachadaAgendaLogVisitaBean();

        Connection connection = null;

        String selectStr =
                "SELECT tblAgendaLogVisitas.idLog,            "
                + "        tblAgendaLogVisitas.idCliente,     "
                + "        tblAgendaLogVisitas.idUsuario,     "
                + "        tblAgendaLogVisitas.idLocalTercero,"
                + "        tblAgendaLogVisitas.idPeriodo,     "
                + "        tblAgendaLogVisitas.fechaVisita,   "
                + "        tblAgendaLogVisitas.idEstadoVisita,"
                + "        tblAgendaLogVisitas.estado,        "
                + "        tblAgendaLogVisitas.fechaTxInicio  "
                + "FROM tblAgendaLogVisitas                   "
                + "WHERE tblAgendaLogVisitas.idLog =          "
                + getIdLog() ;

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

                fachadaBean = new FachadaAgendaLogVisitaBean();

                fachadaBean.setIdLog(rs.getInt("idLog"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setIdUsuario(rs.getInt("idUsuario"));
                fachadaBean.setIdLocalTercero(rs.getInt("idLocalTercero"));
                fachadaBean.setIdPeriodo(rs.getInt("idPeriodo"));
                fachadaBean.setFechaVisitaStr(rs.getString("fechaVisita"));
                fachadaBean.setIdEstadoVisita(rs.getInt("idEstadoVisita"));
                fachadaBean.setEstado(rs.getInt("estado"));
                fachadaBean.setFechaTxInicio(rs.getString("fechaTxInicio"));

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

            // Cierra connection
            jdbcAccess.cleanup(connection, selectStatement, null);
            return fachadaBean;

        }
    }

    // validaEstadoFechaUduario
    public boolean validaEstadoFechaUduariox() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        //
        boolean xLogActivo = false;

        //
        Connection connection = null;

        String selectStr =
                "SELECT tblAgendaLogVisitas.idLog,          "
                + "        tblAgendaLogVisitas.idCliente,     "
                + "        tblAgendaLogVisitas.idUsuario,     "
                + "       tblAgendaLogVisitas.idLocalTercero, "
                + "        tblAgendaLogVisitas.idPeriodo,     "
                + "        tblAgendaLogVisitas.fechaVisita,   "
                + "        tblAgendaLogVisitas.idEstadoVisita,"
                + "        tblAgendaLogVisitas.estado         "
                + "FROM tblAgendaLogVisitas                   "
                + "WHERE tblAgendaLogVisitas.idUsuario   =    "
                + getIdUsuario() + "                          "
                + "AND   tblAgendaLogVisitas.estado      =    "
                + getEstado() + "                             "
                + "AND tblAgendaLogVisitas.fechaVisita   =   '"
                + getFechaVisitaSqlServer() + "'               ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaAgendaLogVisitaBean fachadaBean;

            if (rs.next()) {

                fachadaBean = new FachadaAgendaLogVisitaBean();

                //
                fachadaBean.setIdLog(rs.getInt("idLog"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setIdUsuario(rs.getInt("idUsuario"));
                fachadaBean.setIdLocalTercero(rs.getInt("idLocalTercero"));
                fachadaBean.setIdPeriodo(rs.getInt("idPeriodo"));
                fachadaBean.setFechaVisitaStr(rs.getString("fechaVisita"));
                fachadaBean.setIdEstadoVisita(rs.getInt("idEstadoVisita"));
                fachadaBean.setEstado(rs.getInt("estado"));

                //
                xLogActivo = true;

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

            // Cierra el connection
            jdbcAccess.cleanup(connection, selectStatement, null);
            return xLogActivo;
        }
    }

    // validaEstadoFechaUduario
    public boolean validaEstadoFechaUduario() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        //
        boolean xLogActivo = false;

        Connection connection = null;

        String selectStr =
                "SELECT tblAgendaLogVisitas.idLog,          "
                + "        tblAgendaLogVisitas.idCliente,     "
                + "        tblAgendaLogVisitas.idUsuario,     "
                + "        tblAgendaLogVisitas.idLocalTercero, "
                + "        tblAgendaLogVisitas.idPeriodo,     "
                + "        tblAgendaLogVisitas.fechaVisita,   "
                + "        tblAgendaLogVisitas.idEstadoVisita,"
                + "        tblAgendaLogVisitas.estado         "
                + "FROM tblAgendaLogVisitas                   "
                + "WHERE tblAgendaLogVisitas.idUsuario   =    "
                + getIdUsuario() + "                          "
                + "AND   tblAgendaLogVisitas.estado      =    "
                + getEstado() + "                             ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            if (rs.next()) {

                xLogActivo = true;

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

            // Cierra el connection
            jdbcAccess.cleanup(connection, selectStatement, null);
            return xLogActivo;

        }
    }

    // listaOrdenFCH
    public FachadaAgendaLogVisitaBean listaOrdenFCH(int xIdOrden) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        // Variable de fachada de los datos
        FachadaAgendaLogVisitaBean fachadaBean = new FachadaAgendaLogVisitaBean();

        Connection connection = null;

        String selectStr =
                "SELECT tblAgendaLogVisitas.idLog,          "
                + "        tblAgendaLogVisitas.idCliente,     "
                + "        tblAgendaLogVisitas.idUsuario,     "
                + "        tblAgendaLogVisitas.idLocalTercero,"
                + "        tblAgendaLogVisitas.idPeriodo,     "
                + "        tblAgendaLogVisitas.fechaVisita,   "
                + "        tblAgendaLogVisitas.idEstadoVisita,"
                + "        tblAgendaLogVisitas.estado,        "
                + "        tblAgendaLogVisitas.idLocal,       "
                + "        tblAgendaLogVisitas.ipTx,          "
                + "        tblAgendaLogVisitas.fechaTx,       "
                + "        tblAgendaLogVisitas.idLocalTercero,"
                + "      LTRIM(SUBSTRING(CONVERT(VARCHAR(20), "
                + "  tblAgendaLogVisitas.fechaTx, 22), 10,5)+ "
                + "                RIGHT(CONVERT(VARCHAR(20), "
                + "    tblAgendaLogVisitas.fechaTx, 22), 3))  "
                + "                                AS horaTx  "
                + "FROM       tblAgendaLogVisitas             "
                + "INNER JOIN tblDctosOrdenes                 "
                + "ON tblAgendaLogVisitas.IDLOG       =       "
                + "                    tblDctosOrdenes.idLog  "
                + "WHERE  tblDctosOrdenes.IDLOCAL     =       "
                + getIdLocal() + "                            "
                + "AND    tblDctosOrdenes.IDTIPOORDEN =       "
                + getIdTipoOrden() + "                        "
                + "AND    tblDctosOrdenes.IDORDEN     =       "
                + xIdOrden;

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
                fachadaBean = new FachadaAgendaLogVisitaBean();

                //
                fachadaBean.setIdLog(rs.getInt("idLog"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setIdUsuario(rs.getInt("idUsuario"));
                fachadaBean.setIdLocalTercero(rs.getInt("idLocalTercero"));
                fachadaBean.setIdPeriodo(rs.getInt("idPeriodo"));
                fachadaBean.setFechaVisitaStr(rs.getString("fechaVisita"));
                fachadaBean.setIdEstadoVisita(rs.getInt("idEstadoVisita"));
                fachadaBean.setEstado(rs.getInt("estado"));
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIpTx(rs.getString("ipTx"));
                fachadaBean.setFechaTx(rs.getString("fechaTx"));
                fachadaBean.setIdLocalTercero(rs.getInt("idLocalTercero"));
                fachadaBean.setHoraTx(rs.getString("horaTx"));

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

            // Cierra connection
            jdbcAccess.cleanup(connection, selectStatement, null);
            return fachadaBean;

        }
    }
}
