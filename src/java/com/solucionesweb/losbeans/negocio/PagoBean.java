package com.solucionesweb.losbeans.negocio;

// Importa la clase helper JDBCAccess que contiene los accesos a la base de datos
import com.solucionesweb.lasayudas.JDBCAccess;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaPagoBean;


// Importa los paquetes de java
import java.sql.*;
import java.io.*;
import java.util.Vector;
import java.io.IOException.*;

// Importa los paquetes de javax
import javax.naming.*;

public class PagoBean extends FachadaPagoBean implements Serializable {

    // Nombre JNDI del Data Source que esta clase requiere
    private static final String DATA_SOURCE_NAME = "CommerceAccess";

    // Clase helper usada para acceder a la base de datos
    private JDBCAccess jdbcAccess;

    // Metodo constructor por defecto sin parametros
    public PagoBean() {

        // Al instanciar el Bean establece la conexion a la base de datos
        jdbcAccess = new JDBCAccess(DATA_SOURCE_NAME);
    }

    // Metodo maximoReciboIdLocalxIndicador
    public int maximoReciboIdLocalxIndicador() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        int maximoIdRecibo = 0;

        Vector contenedor = new Vector();
        Connection connection = null;

        String insertStr =
                "SELECT MAX(tblPagos.idRecibo)      " +
                "                 AS maximoIdRecibo " +
                "FROM tblPagos                      " +
                "WHERE tblPagos.idLocal     = ( ? ) " +
                "AND   tblPagos.idTipoOrden = ( ? ) " +
                "AND   tblPagos.indicador   = ( ? ) ";


        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(insertStr);

            // inicializa los valore de los parametros
            selectStatement.setInt(1, getIdLocal());
            selectStatement.setInt(2, getIdTipoOrden());
            selectStatement.setInt(3, getIndicador());

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaPagoBean fachadaBean;

            if (rs.next()) {
                maximoIdRecibo = rs.getInt("maximoIdRecibo");
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
            return maximoIdRecibo;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {
            // Cierra la conexion a la base de datos limpiando la statement usada y la
            // conexion
            return maximoIdRecibo;
        }
    }

    // maximaPlanilla
    public int maximaPlanilla() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        int maximoIdPlanilla = 0;

        Connection connection = null;

        String insertStr =
                "SELECT MAX(tblPagos.idPlanilla)  " +
                "             AS maximoIdPlanilla " +
                "FROM tblPagos                    " +
                "WHERE tblPagos.idLocal       =   " +
                getIdLocal() + "                  " +
                "AND   tblPagos.idTipoOrden   =   " +
                getIdTipoOrden();

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(insertStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            //
            if (rs.next()) {
                maximoIdPlanilla = rs.getInt("maximoIdPlanilla");
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
            // Cierra la conexion a la base de datos limpiando la statement usada y la
            jdbcAccess.cleanup(connection, selectStatement, null);
            return maximoIdPlanilla;
        }
    }

    // ingresaPago
    public boolean ingresaPago() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                "INSERT INTO tblPagos (idLocal,       " +
                "                      idTipoOrden,   " +
                "                      idRecibo,      " +
                "                      indicador,     " +
                "                      fechaPago,     " +
                "                      vrPago,        " +
                "                      nitCC,         " +
                "                      estado,        " +
                "                      idUsuario,     " +
                "                      vrRteFuente,   " +
                "                      vrDescuento,   " +
                "                      vrRteIva,      " +
                "                      vrRteIca,      " +
                "                      idPeriodo,     " +
                "                      idDcto,        " +
                "                      idDctoNitCC,   " +
                "                      idPlanilla,    " +
                "                      vrSaldo,       " +
                "                      idLog,         " +
                "                      idVendedor,    " +
                "                      idReciboCruce, " +
                "                      vrPagoCambio,  " +
                "                      idOrden,       " +                      
                "                      vrRteCree )    " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?,   " +
                "  ?,?,?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?) ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // inicializa los valore de los parametros
            selectStatement.setInt(1, getIdLocal());
            selectStatement.setInt(2, getIdTipoOrden());
            selectStatement.setInt(3, getIdRecibo());
            selectStatement.setInt(4, getIndicador());
            selectStatement.setString(5, getFechaPagoSqlServer());
            selectStatement.setDouble(6, getVrPago());
            selectStatement.setString(7, getNitCC());
            selectStatement.setInt(8, getEstado());
            selectStatement.setDouble(9, getIdUsuario());
            selectStatement.setDouble(10, getVrRteFuente());
            selectStatement.setDouble(11, getVrDescuento());
            selectStatement.setDouble(12, getVrRteIva());
            selectStatement.setDouble(13, getVrRteIca());
            selectStatement.setInt(14, getIdPeriodo());
            selectStatement.setDouble(15, getIdDcto());
            selectStatement.setString(16, getIdDctoNitCC());
            selectStatement.setInt(17, getIdPlanilla());
            selectStatement.setDouble(18, getVrSaldo());
            selectStatement.setDouble(19, getIdLog());
            selectStatement.setDouble(20, getIdVendedor());
            selectStatement.setDouble(21, getIdReciboCruce());
            selectStatement.setDouble(22, getVrPagoCambio());
            selectStatement.setInt(23, getIdOrden());
            selectStatement.setDouble(24, getVrRteCree());

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

    // ingresaPagoLog
    public boolean ingresaPagoLog(int xIdTipoOrdenNew,
            int xIdReciboNew,
            int xIndicadorNew,
            double xIdDctoNew,
            String xIdVendedor,
            double xVrPago,
            int xIdPlanillaNew) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                "INSERT INTO tblPagos         " +
                "           (idLocal          " +
                "           ,idTipoOrden      " +
                "           ,idRecibo         " +
                "           ,indicador        " +
                "           ,fechaPago        " +
                "           ,vrPago           " +
                "           ,nitCC            " +
                "           ,estado           " +
                "           ,idUsuario        " +
                "           ,vrRteFuente      " +
                "           ,vrDescuento      " +
                "           ,vrRteIva         " +
                "           ,vrRteIca         " +
                "           ,idPeriodo        " +
                "           ,idDcto           " +
                "           ,idDctoNitCC      " +
                "           ,idPlanilla       " +
                "           ,vrSaldo          " +
                "           ,idLog            " +
                "           ,idVendedor       " +
                "           ,idReciboCruce    " +
                "           ,vrPagoCambio)    " +
                "SELECT MIN(tblPagos.idLocal) AS idLocal,            " +
                xIdTipoOrdenNew + " AS idTipoOrden,                  " +
                xIdReciboNew + " AS idRecibo,                        " +
                xIndicadorNew + " AS indicador                       " +
                "      ,MIN(tblPagos.fechaPago) AS fechaPago,        " +
                xVrPago + " AS vrPago                                " +
                "      ,MIN(tblPagos.nitCC) AS nitCC                 " +
                "      ,MIN(tblPagos.estado) AS estado               " +
                "      ,MIN(tblPagos.idUsuario) AS idUsuario         " +
                "      ,SUM(tblPagos.vrRteFuente) AS vrRteFuente     " +
                "      ,SUM(tblPagos.vrDescuento) AS vrDescuento     " +
                "      ,SUM(tblPagos.vrRteIva) AS vrRteIva           " +
                "      ,SUM(tblPagos.vrRteIca) AS vrRteIca           " +
                "      ,MIN(tblPagos.idPeriodo) AS idPeriodo,        " +
                xIdDctoNew + " AS idDcto,                            " +
                xIdDctoNew + " AS idDctoNitCC,                       " +
                xIdPlanillaNew + " AS idPlanilla                     " +
                "      ,SUM(tblPagos.vrSaldo) AS vrSaldo             " +
                "      ,tblPagos.idLog,                              " +
                xIdVendedor + " AS idVendedor                        " +
                "      ,MIN(tblPagos.idReciboCruce) AS idReciboCruce " +
                "      ,MIN(tblPagos.vrPagoCambio) AS vrPagoCambio   " +
                "FROM tblPagos                                       " +
                "WHERE tblPagos.idLocal     =                        " +
                getIdLocal() + "                                     " +
                "AND   tblPagos.idTipoOrden =                        " +
                getIdTipoOrden() + "                                 " +
                "AND   tblPagos.idRecibo    =                        " +
                getIdRecibo() + "                                    " +
                "AND   tblPagos.idLog       =                        " +
                getIdLog() + "                                       " +
                "GROUP BY tblPagos.idLog                             ";

 System.out.println(selectStr);

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

    // ingresaPago
    public boolean ingresaPago(int xIdTipoOrdenNew,
            int xIdReciboNew,
            int xIndicadorNew,
            double xIdDctoNew,
            String xIdVendedor,
            double xVrPago,
            int xIdPlanillaNew,
            String xIdDctoNitCCNew) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                "INSERT INTO tblPagos         " +
                "           (idLocal          " +
                "           ,idTipoOrden      " +
                "           ,idRecibo         " +
                "           ,indicador        " +
                "           ,fechaPago        " +
                "           ,vrPago           " +
                "           ,nitCC            " +
                "           ,estado           " +
                "           ,idUsuario        " +
                "           ,vrRteFuente      " +
                "           ,vrDescuento      " +
                "           ,vrRteIva         " +
                "           ,vrRteIca         " +
                "           ,idPeriodo        " +
                "           ,idDcto           " +
                "           ,idDctoNitCC      " +
                "           ,idPlanilla       " +
                "           ,vrSaldo          " +
                "           ,idLog            " +
                "           ,idVendedor       " +
                "           ,idReciboCruce    " +
                "           ,vrPagoCambio)    " +
                "SELECT MIN(tblPagos.idLocal) AS idLocal,            " +
                xIdTipoOrdenNew + " AS idTipoOrden,                  " +
                xIdReciboNew + " AS idRecibo,                        " +
                xIndicadorNew + " AS indicador                       " +
                "      ,MIN(tblPagos.fechaPago) AS fechaPago,        " +
                xVrPago + " AS vrPago                                " +
                "      ,MIN(tblPagos.nitCC) AS nitCC                 " +
                "      ,MIN(tblPagos.estado) AS estado               " +
                "      ,MIN(tblPagos.idUsuario) AS idUsuario         " +
                "      ,SUM(tblPagos.vrRteFuente) AS vrRteFuente     " +
                "      ,SUM(tblPagos.vrDescuento) AS vrDescuento     " +
                "      ,SUM(tblPagos.vrRteIva) AS vrRteIva           " +
                "      ,SUM(tblPagos.vrRteIca) AS vrRteIca           " +
                "      ,MIN(tblPagos.idPeriodo) AS idPeriodo,        " +
                xIdDctoNew + " AS idDcto,                           '" +
                xIdDctoNitCCNew + "' AS idDctoNitCC,                  " +
                xIdPlanillaNew + " AS idPlanilla,                    " +
                getVrSaldo() + "  AS vrSaldo                         " +
                "      ,tblPagos.idLog,                              " +
                xIdVendedor + " AS idVendedor                        " +
                "      ,MIN(tblPagos.idReciboCruce) AS idReciboCruce " +
                "      ,MIN(tblPagos.vrPagoCambio) AS vrPagoCambio   " +
                "FROM tblPagos                                       " +
                "WHERE tblPagos.idLocal     =                        " +
                getIdLocal() + "                                     " +
                "AND   tblPagos.idTipoOrden =                        " +
                getIdTipoOrden() + "                                 " +
                "AND   tblPagos.idRecibo    =                        " +
                getIdRecibo() + "                                    " +
                "AND   tblPagos.idLog       =                        " +
                getIdLog() + "                                       " +
                "AND   tblPagos.idDcto      =                        " +
                xIdDctoNew + "                                       " +
                "GROUP BY tblPagos.idLog                             ";

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
/////******SE MODIFICO EL CAMPO int xIdDctoNew*******////
    // ingresaPagoInsertLog
    public boolean ingresaPagoInsertLog(int xIdTipoOrdenNew,
            int xIdReciboNew,
            int xIndicadorNew,
            String xIdDctoNew,
            String xIdVendedor,
            double xVrPago,
            int xIdPlanillaNew) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                "INSERT INTO tblPagos         " +
                "           (idLocal          " +
                "           ,idTipoOrden      " +
                "           ,idRecibo         " +
                "           ,indicador        " +
                "           ,fechaPago        " +
                "           ,vrPago           " +
                "           ,nitCC            " +
                "           ,estado           " +
                "           ,idUsuario        " +
                "           ,vrRteFuente      " +
                "           ,vrDescuento      " +
                "           ,vrRteIva         " +
                "           ,vrRteIca         " +
                "           ,idPeriodo        " +
                "           ,idDcto           " +
                "           ,idDctoNitCC      " +
                "           ,idPlanilla       " +
                "           ,vrSaldo          " +
                "           ,idLog            " +
                "           ,idVendedor       " +
                "           ,idReciboCruce    " +
                "           ,vrPagoCambio)    " +
                "SELECT MIN(tblPagos.idLocal) AS idLocal,            " +
                xIdTipoOrdenNew + " AS idTipoOrden,                  " +
                xIdReciboNew + " AS idRecibo,                        " +
                xIndicadorNew + " AS indicador                       " +
                "      ,MIN(tblPagos.fechaPago) AS fechaPago,        " +
                xVrPago + " AS vrPago                                " +
                "      ,MIN(tblPagos.nitCC) AS nitCC                 " +
                "      ,MIN(tblPagos.estado) AS estado               " +
                "      ,MIN(tblPagos.idUsuario) AS idUsuario         " +
                "      ,SUM(tblPagos.vrRteFuente) AS vrRteFuente     " +
                "      ,SUM(tblPagos.vrDescuento) AS vrDescuento     " +
                "      ,SUM(tblPagos.vrRteIva) AS vrRteIva           " +
                "      ,SUM(tblPagos.vrRteIca) AS vrRteIca           " +
                "      ,MIN(tblPagos.idPeriodo) AS idPeriodo,        " +
                xIdDctoNew + " AS idDcto,                            " +
                xIdDctoNew + " AS idDctoNitCC,                       " +
                xIdPlanillaNew + " AS idPlanilla                     " +
                "      ,SUM(tblPagos.vrSaldo) AS vrSaldo             " +
                "      ,tblPagos.idLog,                              " +
                xIdVendedor + " AS idVendedor                        " +
                "      ,MIN(tblPagos.idReciboCruce) AS idReciboCruce " +
                "      ,MIN(tblPagos.vrPagoCambio) AS vrPagoCambio   " +
                "FROM tblPagos                                       " +
                "WHERE tblPagos.idLocal     =                        " +
                getIdLocal() + "                                     " +
                "AND   tblPagos.idTipoOrden =                        " +
                getIdTipoOrden() + "                                 " +
                "AND   tblPagos.idLog       =                        " +
                getIdLog() + "                                       " +
                "GROUP BY tblPagos.idLog                             ";

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
    
     public boolean actualizaSaldo() {
    Class tipoObjeto = getClass();
    String nombreClase = tipoObjeto.getName();
    boolean okIngresar = false;
    
    Connection connection = null;
    
    String selectStr = 
            "UPDATE tblPagos                "
            + "SET tblPagos.vrSaldo       = " 
            + getVrSaldo() + "              " 
            + "WHERE tblPagos.idLocal     = " 
            + getIdLocal() + "              " 
            + "AND   tblPagos.idTipoOrden = " 
            + getIdTipoOrden() + "          " 
            + "AND   tblPagos.idLog       = " 
            + getIdLog() + "                " 
            + "AND   tblPagos.idRecibo    = " 
            + getIdRecibo() + "             " 
            + "AND   tblPagos.indicador   = " 
            + getIndicador() + "            ";
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

    // listaUnFCH
    public FachadaPagoBean listaUnFCH() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        // Variable de fachada de los datos
        FachadaPagoBean fachadaBean = new FachadaPagoBean();

        Connection connection = null;

        String insertStr =
                "SELECT tblPagos.idLocal,           " +
                "       tblPagos.idTipoOrden,       " +
                "       tblPagos.idRecibo,          " +
                "       tblPagos.indicador,         " +
                "       tblPagos.fechaPago,         " +
                "       tblPagos.vrPago,            " +
                "       tblPagos.nitCC,             " +
                "       tblPagos.estado,            " +
                "       tblPagos.idUsuario,         " +
                "       tblPagos.vrRteFuente,       " +
                "       tblPagos.vrDescuento,       " +
                "       tblPagos.vrRteIva,          " +
                "       tblPagos.vrRteIca,          " +
                "       tblPagos.idPeriodo,         " +
                "       tblPagos.idDcto,            " +
                "       tblPagos.idDctoNitCC,       " +
                "       tblPagos.idPlanilla,        " +
                "       tblPagos.vrSaldo,           " +
                "       tblPagos.IDLOG,             " +
                "       tblPagos.idVendedor         " +
                "FROM  tblPagos                     " +
                "WHERE tblPagos.idLocal     =       " +
                getIdLocal() + "                    " +
                "AND   tblPagos.idTipoOrden =       " +
                getIdTipoOrden() + "                " +
                "AND   tblPagos.idRecibo    =       " +
                getIdRecibo() + "                   " +
                "AND   tblPagos.indicador   =       " +
                getIndicador();


        PreparedStatement sentenciaSQL = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            sentenciaSQL = connection.prepareStatement(insertStr);

            // Obtiene el resultset
            ResultSet rs = sentenciaSQL.executeQuery();

            if (rs.next()) {

                //
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setIdRecibo(rs.getInt("idRecibo"));
                fachadaBean.setIndicador(rs.getInt("indicador"));
                fachadaBean.setFechaPago(rs.getString("fechaPago"));
                fachadaBean.setVrPago(rs.getDouble("vrPago"));
                fachadaBean.setNitCC(rs.getString("nitCC"));
                fachadaBean.setEstado(rs.getInt("estado"));
                fachadaBean.setIdUsuario(rs.getDouble("idUsuario"));
                fachadaBean.setVrRteFuente(rs.getDouble("vrRteFuente"));
                fachadaBean.setVrDescuento(rs.getDouble("vrDescuento"));
                fachadaBean.setVrRteIva(rs.getDouble("vrRteIva"));
                fachadaBean.setVrRteIca(rs.getDouble("vrRteIca"));
                fachadaBean.setIdPeriodo(rs.getString("idPeriodo"));
                fachadaBean.setIdDcto(rs.getInt("idDcto"));
                fachadaBean.setIdDctoNitCC(rs.getString("idDctoNitCC"));
                fachadaBean.setIdPlanilla(rs.getInt("idPlanilla"));
                fachadaBean.setVrSaldo(rs.getDouble("vrSaldo"));
                fachadaBean.setIdLog(rs.getInt("idLog"));
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

            //
            jdbcAccess.cleanup(connection, sentenciaSQL, null);
            return fachadaBean;
        }
    }

    // validaReciboRetirado
    public boolean validaReciboRetirado() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        // Variable de fachada de los datos
        boolean xReciboRetirado = false;

        Connection connection = null;

        //
        String insertStr =
                "SELECT tblPagos.*              " +
                "FROM  tblPagos                 " +
                "WHERE tblPagos.idLocal       = " +
                getIdLocal() + "                " +
                "AND   tblPagos.idTipoOrden   = " +
                getIdTipoOrden() + "            " +
                "AND   tblPagos.idReciboCruce = " +
                getIdReciboCruce() + "          " +
                "AND   tblPagos.indicador   =   " +
                getIndicador();


        PreparedStatement sentenciaSQL = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            sentenciaSQL = connection.prepareStatement(insertStr);

            // Obtiene el resultset
            ResultSet rs = sentenciaSQL.executeQuery();

            if (rs.next()) {

                //
                xReciboRetirado = true;

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

            //
            jdbcAccess.cleanup(connection, sentenciaSQL, null);
            return xReciboRetirado;
        }
    }

    // retiraPagoDctoTemporal
    public boolean retiraPagoDctoTemporal() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                "DELETE FROM tblPagos            " +
                "WHERE   tblPagos.idLocal    =   " +
                getIdLocal() + "                 " +
                "AND tblPagos.idTipoOrden    =   " +
                getIdTipoOrden() + "             " +
                "AND tblPagos.indicador      =   " +
                getIndicador() + "               " +
                "AND tblPagos.idLog          =   " +
                getIdLog() + "                   " +
                "AND tblPagos.idDcto         =   " +
                getIdDcto() + "                  ";

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

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {

            // Cierra la conexion a la base de datos limpiando la statement usada y la
            jdbcAccess.cleanup(connection, selectStatement, null);
            return okIngresar;
        }
    }

    // retiraPagoTemporal
    public boolean retiraPagoTemporal() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                "DELETE FROM tblPagos            " +
                "WHERE   tblPagos.idLocal    =   " +
                getIdLocal() + "                 " +
                "AND tblPagos.idTipoOrden    =   " +
                getIdTipoOrden() + "             " +
                "AND tblPagos.idLog          =   " +
                getIdLog() + "                   " +
                "AND tblPagos.idRecibo       =   " +
                getIdRecibo() + "                ";

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

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {

            // Cierra la conexion a la base de datos limpiando la statement usada y la
            jdbcAccess.cleanup(connection, selectStatement, null);
            return okIngresar;
        }
    }

    // retiraPagoTemporalLog
    public boolean retiraPagoTemporalLog() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                "DELETE FROM tblPagos            " +
                "WHERE   tblPagos.idLocal    =   " +
                getIdLocal() + "                 " +
                "AND tblPagos.idTipoOrden    =   " +
                getIdTipoOrden() + "             " +
                "AND tblPagos.idLog          =   " +
                getIdLog() ;

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

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {

            // Cierra la conexion a la base de datos limpiando la statement usada y la
            jdbcAccess.cleanup(connection, selectStatement, null);
            return okIngresar;
        }
    }

    // retiraReciboTemporal
    public boolean retiraReciboTemporal() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                "DELETE FROM tblPagos        " +
                "WHERE tblPagos .idLocal   = " +
                getIdLocal() + "             " +
                "AND tblPagos .idTipoOrden = " +
                getIdTipoOrden() + "         " +
                "AND tblPagos .indicador   = " +
                getIndicador() + "           " +
                "AND tblPagos.idRecibo     = " +
                getIdRecibo();

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

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {

            // Cierra la conexion a la base de datos limpiando la statement usada y la
            jdbcAccess.cleanup(connection, selectStatement, null);
            return okIngresar;
        }
    }

    // listaPagoProceso
    public Vector listaPagoProceso() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        Vector contenedor = new Vector();

        Connection connection = null;

        String insertStr =
                "SELECT tblPagos.idLocal,        " +
                "       tblPagos.idTipoOrden,    " +
                "       tblPagos.idDcto,         " +
                "       tblPagos.idDctoNitCC,    " +
                "       tblPagos.indicador,      " +
                "       tblPagos.vrPago,         " +
                "       tblPagos.vrRteFuente,    " +
                "       tblPagos.vrDescuento,    " +
                "       tblPagos.vrRteIva,       " +
                "       tblPagos.vrRteIca,       " +
                "       tblPagos.nitCC,          " +
                "       tblPagos.idRecibo        " +
                "FROM   tblPagos                 " +
                "WHERE EXISTS (                  " +
                "SELECT tblPagosMedios.*         " +
                "FROM tblPagosMedios             " +
                "WHERE tblPagos.idLocal        = " +
                "        tblPagosMedios.idLocal  " +
                "AND tblPagos.idTipoOrden      = " +
                "    tblPagosMedios.idTipoOrden  " +
                "AND tblPagos.idRecibo         = " +
                "        tblPagosMedios.idRecibo " +
                "AND tblPagos.indicador        = " +
                "      tblPagosMedios.indicador) " +
                "AND tblPagos.idLocal          = " +
                getIdLocal() + "                 " +
                "AND   tblPagos.idTipoOrden    = " +
                getIdTipoOrden() + "             " +
                "AND   tblPagos.idLog          = " +
                getIdLog() + "                   " +
                "AND   (tblPagos.vrPago     != 0 " +
                "OR    tblPagos.vrRteFuente != 0 " +
                "OR    tblPagos.vrDescuento != 0 " +
                "OR    tblPagos.vrRteIva    != 0 " +
                "OR    tblPagos.vrRteIca    != 0)";

        PreparedStatement sentenciaSQL = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            sentenciaSQL = connection.prepareStatement(insertStr);

            // Variable de fachada de los datos
            FachadaPagoBean fachadaBean;

            // Obtiene el resultset
            ResultSet rs = sentenciaSQL.executeQuery();

            while (rs.next()) {

                //
                fachadaBean = new FachadaPagoBean();

                //
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setIdDcto(rs.getInt("idDcto"));
                fachadaBean.setIdDctoNitCC(rs.getString("idDctoNitCC"));
                fachadaBean.setIndicador(rs.getInt("indicador"));
                fachadaBean.setVrPago(rs.getDouble("vrPago"));
                fachadaBean.setVrRteFuente(rs.getDouble("vrRteFuente"));
                fachadaBean.setVrDescuento(rs.getDouble("vrDescuento"));
                fachadaBean.setVrRteIva(rs.getDouble("vrRteIva"));
                fachadaBean.setVrRteIca(rs.getDouble("vrRteIca"));
                fachadaBean.setNitCC(rs.getString("nitCC"));
                fachadaBean.setIdRecibo(rs.getInt("idRecibo"));

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

            //
            jdbcAccess.cleanup(connection, sentenciaSQL, null);
            return contenedor;
        }
    }

    // actualizarPagoTemporal
    public boolean actualizarPagoTemporal(int xIdTipoOrdenTmp) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                "UPDATE tblPagos              " +
                "SET tblPagos.idRecibo      = " +
                getIdRecibo() + ",            " +
                "    tblPagos.idTipoOrden   = " +
                getIdTipoOrden() + ",         " +
                "    tblPagos.idPlanilla    = " +
                getIdPlanilla() + ",          " +
                "    tblPagos.vrSaldo       = " +
                getVrSaldo() + ",             " +
                "    tblPagos.idVendedor    = " +
                getIdVendedor() + "           " +
                "WHERE tblPagos.idLocal     = " +
                getIdLocal() + "              " +
                "AND   tblPagos.idTipoOrden = " +
                xIdTipoOrdenTmp + "           " +
                "AND   tblPagos.idLog       = " +
                getIdLog() + "                " +
                "AND   tblPagos.idDcto      = " +
                getIdDcto() + "               " +
                "AND   tblPagos.indicador   = " +
                getIndicador();

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

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {

            // Cierra la conexion a la base de datos limpiando la statement usada y la
            jdbcAccess.cleanup(connection, selectStatement, null);
            return okIngresar;
        }
    }

    // retiraPagoLog
    public boolean retiraPagoLog() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                "DELETE FROM tblPagos            " +
                "WHERE tblPagos.idLocal     =    " +
                getIdLocal() + "                 " +
                "AND   tblPagos.idTipoOrden =    " +
                getIdTipoOrden() + "             " +
                "AND   tblPagos.idLog       =    " +
                getIdLog();

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

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {

            // Cierra la conexion a la base de datos limpiando la statement usada y la
            jdbcAccess.cleanup(connection, selectStatement, null);
            return okIngresar;
        }
    }

    // actualizaObservacion
    public boolean actualizaObservacion() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                "UPDATE tblPagos                 " +
                "SET tblPagos.observacion   =   '" +
                getObservacion() + "'            " +
                "WHERE tblPagos.idLocal     =    " +
                getIdLocal() + "                 " +
                "AND   tblPagos.idTipoOrden =    " +
                getIdTipoOrden() + "             " +
                "AND   tblPagos.idRecibo    =    " +
                getIdRecibo() + "                " +
                "AND   tblPagos.indicador   =    " +
                getIndicador();

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

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {

            // Cierra la conexion a la base de datos limpiando la statement usada y la
            jdbcAccess.cleanup(connection, selectStatement, null);
            return okIngresar;
        }
    }

    // actualizaPagoCambio
    public boolean actualizaPagoCambio() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                "UPDATE tblPagos                 " +
                "SET tblPagos.vrPago        =    " +
                getVrPago() + ",                 " +
                "    tblPagos.vrPagoCambio  =    " +
                getVrPagoCambio() + ",           " +
                " tblPagos.idPlanilla       =    " +
                getIdPlanilla() + "              " +
                "WHERE tblPagos.idLocal     =    " +
                getIdLocal() + "                 " +
                "AND   tblPagos.idTipoOrden =    " +
                getIdTipoOrden() + "             " +
                "AND   tblPagos.idRecibo    =    " +
                getIdRecibo() + "                " +
                "AND   tblPagos.indicador   =    " +
                getIndicador();

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

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {

            // Cierra la conexion a la base de datos limpiando la statement usada y la
            jdbcAccess.cleanup(connection, selectStatement, null);
            return okIngresar;
        }
    }

    // listaUnFCH
    public FachadaPagoBean listaUnPagoFCH() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        // Variable de fachada de los datos
        FachadaPagoBean fachadaBean = new FachadaPagoBean();

        Connection connection = null;

        String insertStr =
                "SELECT tblPagos.idLocal             " +
                "      ,tblPagos.idTipoOrden         " +
                "      ,tblPagos.idRecibo            " +
                "      ,tblPagos.indicador           " +
                "      ,tblPagos.fechaPago           " +
                "      ,tblPagos.vrPago              " +
                "      ,tblPagos.nitCC               " +
                "      ,tblPagos.estado              " +
                "      ,tblPagos.idUsuario           " +
                "      ,tblPagos.vrRteFuente         " +
                "      ,tblPagos.vrDescuento         " +
                "      ,tblPagos.vrRteIva            " +
                "      ,tblPagos.vrRteIca            " +
                "      ,tblPagos.idPeriodo           " +
                "      ,tblPagos.idDcto              " +
                "      ,tblPagos.idDctoNitCC         " +
                "      ,tblPagos.idPlanilla          " +
                "      ,tblPagos.vrSaldo             " +
                "      ,tblPagos.idLog               " +
                "      ,tblPagos.idVendedor          " +
                "      ,tblPagos.idReciboCruce       " +
                "      ,tblPagos.vrPagoCambio,       " +
                " (SELECT ctrlUsuarios.nombreUsuario " +
                "  FROM ctrlUsuarios                 " +
                "  WHERE ctrlUsuarios.idUsuario =    " +
                "              tblPagos.idVendedor)  " +
                "                 AS nombreVendedor  " +
                "FROM tblPagos                       " +
                "WHERE EXISTS (                      " +
                "   SELECT tblPagosMedios.*          " +
                "   FROM tblPagosMedios              " +
                "   WHERE tblPagos.idLocal   =       " +
                "         tblPagosMedios.idLocal     " +
                "   AND tblPagos.idTipoOrden =       " +
                "     tblPagosMedios.idTipoOrden     " +
                "   AND tblPagos.idRecibo    =       " +
                "        tblPagosMedios.idRecibo     " +
                "   AND tblPagos.indicador   =       " +
                "       tblPagosMedios.indicador)    " +
                "AND    tblPagos.idLog       =       " +
                getIdLog() + "                       " +
                "AND    tblPagos.idLocal     =       " +
                getIdLocal() + "                     " +
                "AND    tblPagos.idTipoOrden =       " +
                getIdTipoOrden();

        PreparedStatement sentenciaSQL = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            sentenciaSQL = connection.prepareStatement(insertStr);

            // Obtiene el resultset
            ResultSet rs = sentenciaSQL.executeQuery();

            //
            if (rs.next()) {

                //
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setIdRecibo(rs.getInt("idRecibo"));
                fachadaBean.setIndicador(rs.getInt("indicador"));
                fachadaBean.setFechaPago(rs.getString("fechaPago"));
                fachadaBean.setVrPago(rs.getDouble("vrPago"));
                fachadaBean.setNitCC(rs.getString("nitCC"));
                fachadaBean.setEstado(rs.getInt("estado"));
                fachadaBean.setIdUsuario(rs.getDouble("idUsuario"));
                fachadaBean.setVrRteFuente(rs.getDouble("vrRteFuente"));
                fachadaBean.setVrDescuento(rs.getDouble("vrDescuento"));
                fachadaBean.setVrRteIva(rs.getDouble("vrRteIva"));
                fachadaBean.setVrRteIca(rs.getDouble("vrRteIca"));
                fachadaBean.setIdPeriodo(rs.getString("idPeriodo"));
                fachadaBean.setIdDcto(rs.getInt("idDcto"));
                fachadaBean.setIdDctoNitCC(rs.getString("idDctoNitCC"));
                fachadaBean.setIdPlanilla(rs.getInt("idPlanilla"));
                fachadaBean.setVrSaldo(rs.getDouble("vrSaldo"));
                fachadaBean.setIdLog(rs.getInt("idLog"));
                fachadaBean.setIdVendedor(rs.getDouble("idVendedor"));
                fachadaBean.setIdReciboCruce(rs.getInt("idReciboCruce"));
                fachadaBean.setVrPagoCambio(rs.getDouble("vrPagoCambio"));
                fachadaBean.setNombreVendedor(rs.getString("nombreVendedor"));

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

            //
            jdbcAccess.cleanup(connection, sentenciaSQL, null);
            return fachadaBean;
        }
    }

    // listaUnPagoDctoFCH
    public FachadaPagoBean listaUnPagoDctoFCH() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        // Variable de fachada de los datos
        FachadaPagoBean fachadaBean = new FachadaPagoBean();

        Connection connection = null;

        String insertStr =
                "SELECT tblPagos.idLocal             " +
                "      ,tblPagos.idTipoOrden         " +
                "      ,tblPagos.idRecibo            " +
                "      ,tblPagos.indicador           " +
                "      ,tblPagos.fechaPago           " +
                "      ,tblPagos.vrPago              " +
                "      ,tblPagos.nitCC               " +
                "      ,tblPagos.estado              " +
                "      ,tblPagos.idUsuario           " +
                "      ,tblPagos.vrRteFuente         " +
                "      ,tblPagos.vrDescuento         " +
                "      ,tblPagos.vrRteIva            " +
                "      ,tblPagos.vrRteIca            " +
                "      ,tblPagos.idPeriodo           " +
                "      ,tblPagos.idDcto              " +
                "      ,tblPagos.idDctoNitCC         " +
                "      ,tblPagos.idPlanilla          " +
                "      ,tblPagos.vrSaldo             " +
                "      ,tblPagos.idLog               " +
                "      ,tblPagos.idVendedor          " +
                "      ,tblPagos.idReciboCruce       " +
                "      ,tblPagos.vrPagoCambio,       " +
                " (SELECT ctrlUsuarios.nombreUsuario " +
                "  FROM ctrlUsuarios                 " +
                "  WHERE ctrlUsuarios.idUsuario =    " +
                "              tblPagos.idVendedor)  " +
                "                 AS nombreVendedor  " +
                "FROM tblPagos                       " +
                "WHERE EXISTS (                      " +
                "   SELECT tblPagosMedios.*          " +
                "   FROM tblPagosMedios              " +
                "   WHERE tblPagos.idLocal   =       " +
                "         tblPagosMedios.idLocal     " +
                "   AND tblPagos.idTipoOrden =       " +
                "     tblPagosMedios.idTipoOrden     " +
                "   AND tblPagos.idRecibo    =       " +
                "        tblPagosMedios.idRecibo     " +
                "   AND tblPagos.indicador   =       " +
                "       tblPagosMedios.indicador)    " +
                "AND    tblPagos.indicador   =       " +
                getIndicador() + "                   " +
                "AND    tblPagos.idDcto      =       " +
                getIdDcto() + "                      " +
                "AND    tblPagos.idLocal     =       " +
                getIdLocal() + "                     " +
                "AND    tblPagos.idTipoOrden =       " +
                getIdTipoOrden();

        PreparedStatement sentenciaSQL = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            sentenciaSQL = connection.prepareStatement(insertStr);

            // Obtiene el resultset
            ResultSet rs = sentenciaSQL.executeQuery();

            //
            if (rs.next()) {

                //
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setIdRecibo(rs.getInt("idRecibo"));
                fachadaBean.setIndicador(rs.getInt("indicador"));
                fachadaBean.setFechaPago(rs.getString("fechaPago"));
                fachadaBean.setVrPago(rs.getDouble("vrPago"));
                fachadaBean.setNitCC(rs.getString("nitCC"));
                fachadaBean.setEstado(rs.getInt("estado"));
                fachadaBean.setIdUsuario(rs.getDouble("idUsuario"));
                fachadaBean.setVrRteFuente(rs.getDouble("vrRteFuente"));
                fachadaBean.setVrDescuento(rs.getDouble("vrDescuento"));
                fachadaBean.setVrRteIva(rs.getDouble("vrRteIva"));
                fachadaBean.setVrRteIca(rs.getDouble("vrRteIca"));
                fachadaBean.setIdPeriodo(rs.getString("idPeriodo"));
                fachadaBean.setIdDcto(rs.getInt("idDcto"));
                fachadaBean.setIdDctoNitCC(rs.getString("idDctoNitCC"));
                fachadaBean.setIdPlanilla(rs.getInt("idPlanilla"));
                fachadaBean.setVrSaldo(rs.getDouble("vrSaldo"));
                fachadaBean.setIdLog(rs.getInt("idLog"));
                fachadaBean.setIdVendedor(rs.getDouble("idVendedor"));
                fachadaBean.setIdReciboCruce(rs.getInt("idReciboCruce"));
                fachadaBean.setVrPagoCambio(rs.getDouble("vrPagoCambio"));
                fachadaBean.setNombreVendedor(rs.getString("nombreVendedor"));

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

            //
            jdbcAccess.cleanup(connection, sentenciaSQL, null);
            return fachadaBean;
        }
    }
}