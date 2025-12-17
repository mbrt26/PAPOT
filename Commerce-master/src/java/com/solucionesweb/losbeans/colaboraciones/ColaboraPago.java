package com.solucionesweb.losbeans.colaboraciones;

// Importa los paquetes de java
import java.sql.*;
import java.util.*;

// Importa los paquetes de javax
import javax.naming.*;

// Importa la clase helper JDBCAccess que contiene acceso a la base de datos
import com.solucionesweb.lasayudas.JDBCAccess;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaPagoBean;

public class ColaboraPago extends FachadaPagoBean {

    //
    private int idLocal;
    private int idTipoOrden;
    private int idRecibo;
    private String fechaPago;
    private double vrPago;
    private String nitCC;
    private double idUsuario;
    private double vrRteFuente;
    private double vrDescuento;
    private double vrRteIva;
    private double vrRteIca;
    private int idPlanilla;
    private double idDcto;
    private String idDctoNitCC;
    private double vrSaldo;
    private String nombreCliente;
    private String idTipoOrdenCadena;

    // Nombre JNDI del Data Source que esta clase requiere
    private static final String DATA_SOURCE_NAME = "CommerceAccess";

    // Clase helper usada para acceder a la base de datos
    private JDBCAccess jdbcAccess;

    // Metodo constructor por defecto sin parametros
    public ColaboraPago() {

        // Al instanciar el Bean establece la conexion a la base de datos
        jdbcAccess = new JDBCAccess(DATA_SOURCE_NAME);

    }

    //
    public ColaboraPago(int xIdLocal,
            int xIdTipoOrden,
            int xIdRecibo,
            String xFechaPago,
            double xVrPago,
            String xNitCC,
            double xIdUsuario,
            double xVrRteFuente,
            double xVrDescuento,
            double xVrRteIva,
            double xVrRteIca,
            int xIdPlanilla,
            double xIdDcto,
            String xIdDctoNitCC,
            double xVrSaldo) {
        this.idLocal = xIdLocal;
        this.idTipoOrden = xIdTipoOrden;
        this.idRecibo = xIdRecibo;
        this.fechaPago = xFechaPago;
        this.vrPago = xVrPago;
        this.nitCC = xNitCC;
        this.idUsuario = xIdUsuario;
        this.vrRteFuente = xVrRteFuente;
        this.vrDescuento = xVrDescuento;
        this.vrRteIva = xVrRteIva;
        this.vrRteIca = xVrRteIca;
        this.idPlanilla = xIdPlanilla;
        this.idDcto = xIdDcto;
        this.idDctoNitCC = xIdDctoNitCC;
        this.vrSaldo = xVrSaldo;
    }

    //
    public ColaboraPago(int xIdLocal,
            int xIdTipoOrden,
            int xIdRecibo,
            String xFechaPago,
            double xVrPago,
            String xNitCC,
            double xIdUsuario,
            double xVrRteFuente,
            double xVrDescuento,
            double xVrRteIva,
            double xVrRteIca,
            int xIdPlanilla,
            double xIdDcto,
            String xIdDctoNitCC,
            double xVrSaldo,
            String xNombreCliente) {
        this.idLocal = xIdLocal;
        this.idTipoOrden = xIdTipoOrden;
        this.idRecibo = xIdRecibo;
        this.fechaPago = xFechaPago;
        this.vrPago = xVrPago;
        this.nitCC = xNitCC;
        this.idUsuario = xIdUsuario;
        this.vrRteFuente = xVrRteFuente;
        this.vrDescuento = xVrDescuento;
        this.vrRteIva = xVrRteIva;
        this.vrRteIca = xVrRteIca;
        this.idPlanilla = xIdPlanilla;
        this.idDcto = xIdDcto;
        this.idDctoNitCC = xIdDctoNitCC;
        this.vrSaldo = xVrSaldo;
        this.nombreCliente = xNombreCliente;
    }

    // Metodo totalPlanillaFCH
    public FachadaPagoBean totalPlanillaFCH() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        //
        FachadaPagoBean fachadaPagoBean = new FachadaPagoBean();

        Connection connection = null;

        String selectStr
                = "SELECT tblPagos.idLocal,         "
                + "       tblPagos.idTipoOrden,     "
                + "       tblPagos.fechaPago,       "
                + "       MAX(tblPagos.observacion) "
                + "                  AS observacion,"
                + "       SUM(tblPagos.vrPago)      "
                + "                     AS vrPago,  "
                + "       tblPagos.nitCC,           "
                + "       tblPagos.idUsuario,       "
                + "       MAX(tblPagos.idVendedor)  "
                + "                  AS idVendedor, "
                + "       SUM(tblPagos.vrRteFuente) "
                + "                 AS vrRteFuente ,"
                + "       SUM(tblPagos.vrDescuento) "
                + "                  AS vrDescuento,"
                + "       SUM(tblPagos.vrRteIva)    "
                + "                     AS vrRteIva,"
                + "       SUM(tblPagos.vrRteIca)    "
                + "                     AS vrRteIca,"
                + "       tblPagos.idPlanilla,      "
                + "       COUNT(*) AS numeroDcto,   "
                + "       SUM(tblPagos.vrSaldo)     "
                + "                     AS vrSaldo  "
                + "FROM   tblPagos                  "
                + "WHERE EXISTS (                   "
                + "SELECT tblPagosMedios.*          "
                + "FROM tblPagosMedios              "
                + "WHERE tblPagos.idLocal      =    "
                + "        tblPagosMedios.idLocal   "
                + "AND tblPagos.idTipoOrden    =    "
                + "    tblPagosMedios.idTipoOrden   "
                + "AND tblPagos.idRecibo       =    "
                + "       tblPagosMedios.idRecibo   "
                + "AND tblPagos.indicador      =    "
                + "     tblPagosMedios.indicador)   "
                + "AND  tblPagos.idLocal     =      "
                + getIdLocal() + "                  "
                + "AND  tblPagos.idTipoOrden =      "
                + getIdTipoOrden() + "              "
                + "AND    tblPagos.idPlanilla  =    "
                + getIdPlanilla() + "               "
                + "GROUP BY tblPagos.idLocal,       "
                + "       tblPagos.idTipoOrden,     "
                + "       tblPagos.fechaPago,       "
                + "       tblPagos.nitCC,           "
                + "       tblPagos.idUsuario,       "
                + "       tblPagos.idPlanilla       ";

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
                fachadaPagoBean.setIdLocal(rs.getInt("idLocal"));
                fachadaPagoBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaPagoBean.setFechaPago(rs.getString("fechaPago"));
                fachadaPagoBean.setObservacion(rs.getString("observacion"));
                fachadaPagoBean.setVrPago(rs.getDouble("vrPago"));
                fachadaPagoBean.setNitCC(rs.getString("nitCC"));
                fachadaPagoBean.setIdUsuario(rs.getDouble("idUsuario"));
                fachadaPagoBean.setIdVendedor(rs.getDouble("idVendedor"));
                fachadaPagoBean.setVrRteFuente(rs.getDouble("vrRteFuente"));
                fachadaPagoBean.setVrDescuento(rs.getDouble("vrDescuento"));
                fachadaPagoBean.setVrRteIva(rs.getDouble("vrRteIva"));
                fachadaPagoBean.setVrRteIca(rs.getDouble("vrRteIca"));
                fachadaPagoBean.setIdPlanilla(rs.getInt("idPlanilla"));
                fachadaPagoBean.setNumeroDcto(rs.getInt("numeroDcto"));
                fachadaPagoBean.setVrSaldo(rs.getDouble("vrSaldo"));

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
            return fachadaPagoBean;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {

            // Cierra connection
            jdbcAccess.cleanup(connection, selectStatement, null);
            return fachadaPagoBean;

        }
    }

    // Metodo totalPlanillaVendedorFCH
    public FachadaPagoBean totalPlanillaVendedorFCH() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        //
        FachadaPagoBean fachadaPagoBean = new FachadaPagoBean();

        Connection connection = null;

        String selectStr
                = "SELECT tblPagos.idLocal,         "
                + "       tblPagos.idTipoOrden,     "
                + "       tblPagos.fechaPago,       "
                + "       SUM(tblPagos.vrPago)      "
                + "                     AS vrPago,  "
                + "       tblPagos.idVendedor,      "
                + "       SUM(tblPagos.vrRteFuente) "
                + "                 AS vrRteFuente ,"
                + "       SUM(tblPagos.vrDescuento) "
                + "                  AS vrDescuento,"
                + "       SUM(tblPagos.vrRteIva)    "
                + "                     AS vrRteIva,"
                + "       SUM(tblPagos.vrRteIca)    "
                + "                     AS vrRteIca,"
                + "       tblPagos.idPlanilla,      "
                + "       COUNT(*) AS numeroDcto,   "
                + "       SUM(tblPagos.vrSaldo)     "
                + "                     AS vrSaldo  "
                + "FROM   tblPagos                  "
                + "WHERE EXISTS (                   "
                + "SELECT tblPagosMedios.*          "
                + "FROM tblPagosMedios              "
                + "WHERE tblPagos.idLocal      =    "
                + "        tblPagosMedios.idLocal   "
                + "AND tblPagos.idTipoOrden    =    "
                + "    tblPagosMedios.idTipoOrden   "
                + "AND tblPagos.idRecibo       =    "
                + "       tblPagosMedios.idRecibo   "
                + "AND tblPagos.indicador      =    "
                + "     tblPagosMedios.indicador)   "
                + "AND  tblPagos.idLocal     =      "
                + getIdLocal() + "                  "
                + "AND    tblPagos.idTipoOrden =    "
                + getIdTipoOrden() + "              "
                + "AND    tblPagos.idPlanilla  =    "
                + getIdPlanilla() + "               "
                + "GROUP BY tblPagos.idLocal,       "
                + "       tblPagos.idTipoOrden,     "
                + "       tblPagos.fechaPago,       "
                + "       tblPagos.idVendedor,      "
                + "       tblPagos.idPlanilla       ";

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
                fachadaPagoBean.setIdLocal(rs.getInt("idLocal"));
                fachadaPagoBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaPagoBean.setFechaPago(rs.getString("fechaPago"));
                fachadaPagoBean.setVrPago(rs.getDouble("vrPago"));
                fachadaPagoBean.setIdVendedor(rs.getDouble("idVendedor"));
                fachadaPagoBean.setVrRteFuente(rs.getDouble("vrRteFuente"));
                fachadaPagoBean.setVrDescuento(rs.getDouble("vrDescuento"));
                fachadaPagoBean.setVrRteIva(rs.getDouble("vrRteIva"));
                fachadaPagoBean.setVrRteIca(rs.getDouble("vrRteIca"));
                fachadaPagoBean.setIdPlanilla(rs.getInt("idPlanilla"));
                fachadaPagoBean.setNumeroDcto(rs.getInt("numeroDcto"));
                fachadaPagoBean.setVrSaldo(rs.getDouble("vrSaldo"));

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
            return fachadaPagoBean;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {

            // Cierra connection
            jdbcAccess.cleanup(connection, selectStatement, null);
            return fachadaPagoBean;

        }
    }

    // Metodo totalHistoriaFCH
    public FachadaPagoBean totalHistoriaFCH() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        //
        FachadaPagoBean fachadaPagoBean = new FachadaPagoBean();

        boolean validaOk = false;
        Connection connection = null;

        String selectStr
                = "SELECT tblPagos.idLocal,         "
                + "       tblPagos.idTipoOrden,     "
                + "       SUM(tblPagos.vrPago)      "
                + "                     AS vrPago,  "
                + "       tblPagos.nitCC,           "
                + "       SUM(tblPagos.vrRteFuente) "
                + "                 AS vrRteFuente ,"
                + "       SUM(tblPagos.vrDescuento) "
                + "                  AS vrDescuento,"
                + "       SUM(tblPagos.vrRteIva)    "
                + "                     AS vrRteIva,"
                + "       SUM(tblPagos.vrRteIca)    "
                + "                     AS vrRteIca,"
                + "       COUNT(*) AS numeroDcto,   "
                + "       SUM(tblPagos.vrSaldo)     "
                + "                     AS vrSaldo  "
                + "FROM   tblPagos                  "
                + "WHERE EXISTS (                   "
                + "SELECT tblPagosMedios.*          "
                + "FROM tblPagosMedios              "
                + "WHERE tblPagos.idLocal      =    "
                + "        tblPagosMedios.idLocal   "
                + "AND tblPagos.idTipoOrden    =    "
                + "    tblPagosMedios.idTipoOrden   "
                + "AND tblPagos.idRecibo       =    "
                + "       tblPagosMedios.idRecibo   "
                + "AND tblPagos.indicador      =    "
                + "     tblPagosMedios.indicador)   "
                + "AND  tblPagos.idLocal       =    "
                + getIdLocal() + "                  "
                + "AND    tblPagos.idTipoOrden =    "
                + getIdTipoOrden() + "              "
                + "AND    tblPagos.nitCC  =        '"
                + getNitCC() + "'                   "
                + "AND    tblPagos.fechaPago        "
                + "BETWEEN                         '"
                + getFechaInicialSqlServer() + "'   "
                + " AND                            '"
                + getFechaFinalSqlServer() + "'     "
                + "GROUP BY tblPagos.idLocal,       "
                + "       tblPagos.idTipoOrden,     "
                + "       tblPagos.nitCC            ";

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
                fachadaPagoBean.setIdLocal(rs.getInt("idLocal"));
                fachadaPagoBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaPagoBean.setVrPago(rs.getDouble("vrPago"));
                fachadaPagoBean.setNitCC(rs.getString("nitCC"));
                fachadaPagoBean.setVrRteFuente(rs.getDouble("vrRteFuente"));
                fachadaPagoBean.setVrDescuento(rs.getDouble("vrDescuento"));
                fachadaPagoBean.setVrRteIva(rs.getDouble("vrRteIva"));
                fachadaPagoBean.setVrRteIca(rs.getDouble("vrRteIca"));
                fachadaPagoBean.setNumeroDcto(rs.getInt("numeroDcto"));
                fachadaPagoBean.setVrSaldo(rs.getDouble("vrSaldo"));

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
            return fachadaPagoBean;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {

            // Cierra connection
            jdbcAccess.cleanup(connection, selectStatement, null);
            return fachadaPagoBean;

        }
    }

    // Metodo totalReciboFCH
    public FachadaPagoBean totalReciboFCH() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        //
        FachadaPagoBean fachadaPagoBean = new FachadaPagoBean();

        boolean validaOk = false;
        Connection connection = null;

        String selectStr
                = "SELECT tblPagos.idLocal,         "
                + "       tblPagos.idTipoOrden,     "
                + "       tblPagos.fechaPago,       "
                + "       SUM(tblPagos.vrPago)      "
                + "                     AS vrPago,  "
                + "       tblPagos.nitCC,           "
                + "       tblPagos.idUsuario,       "
                + "       SUM(tblPagos.vrRteFuente) "
                + "                 AS vrRteFuente ,"
                + "       SUM(tblPagos.vrDescuento) "
                + "                  AS vrDescuento,"
                + "       SUM(tblPagos.vrRteIva)    "
                + "                     AS vrRteIva,"
                + "       SUM(tblPagos.vrRteIca)    "
                + "                     AS vrRteIca,"
                + "       tblPagos.idPlanilla,      "
                + "       COUNT(*) AS numeroDcto,   "
                + "       SUM(tblPagos.vrSaldo)     "
                + "                      AS vrSaldo "
                + "FROM   tblPagos                  "
                + "WHERE EXISTS (                   "
                + "SELECT tblPagosMedios.*          "
                + "FROM tblPagosMedios              "
                + "WHERE tblPagos.idLocal         = "
                + "        tblPagosMedios.idLocal   "
                + "AND tblPagos.idTipoOrden       = "
                + "    tblPagosMedios.idTipoOrden   "
                + "AND tblPagos.idRecibo          = "
                + "       tblPagosMedios.idRecibo   "
                + "AND tblPagos.indicador         = "
                + "     tblPagosMedios.indicador)   "
                + "AND  tblPagos.idLocal          = "
                + getIdLocal() + "                  "
                + "AND  tblPagos.idTipoOrden      = "
                + getIdTipoOrden() + "              "
                + "AND    tblPagos.idRecibo       = "
                + getIdRecibo() + "                 "
                + "AND    tblPagos.indicador      = "
                + getIndicador() + "                "
                + "GROUP BY tblPagos.idLocal,       "
                + "       tblPagos.idTipoOrden,     "
                + "       tblPagos.fechaPago,       "
                + "       tblPagos.nitCC,           "
                + "       tblPagos.idUsuario,       "
                + "       tblPagos.idPlanilla       ";

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
                fachadaPagoBean.setIdLocal(rs.getInt("idLocal"));
                fachadaPagoBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaPagoBean.setFechaPago(rs.getString("fechaPago"));
                fachadaPagoBean.setVrPago(rs.getDouble("vrPago"));
                fachadaPagoBean.setNitCC(rs.getString("nitCC"));
                fachadaPagoBean.setIdUsuario(rs.getDouble("idUsuario"));
                fachadaPagoBean.setVrRteFuente(rs.getDouble("vrRteFuente"));
                fachadaPagoBean.setVrDescuento(rs.getDouble("vrDescuento"));
                fachadaPagoBean.setVrRteIva(rs.getDouble("vrRteIva"));
                fachadaPagoBean.setVrRteIca(rs.getDouble("vrRteIca"));
                fachadaPagoBean.setIdPlanilla(rs.getInt("idPlanilla"));
                fachadaPagoBean.setNumeroDcto(rs.getInt("numeroDcto"));
                fachadaPagoBean.setVrSaldo(rs.getDouble("vrSaldo"));

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
            return fachadaPagoBean;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {

            // Cierra connection
            jdbcAccess.cleanup(connection, selectStatement, null);
            return fachadaPagoBean;

        }
    }

    // Metodo
    public Collection listaPlanilla() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        //
        Collection list = new ArrayList();

        Connection connection = null;

        //
        String selectStr
                = "SELECT tblPagos.idLocal,         "
                + "       tblPagos.idTipoOrden,     "
                + "       tblPagos.idRecibo,        "
                + "       tblPagos.indicador,       "
                + "       tblPagos.fechaPago,       "
                + "       tblPagos.vrPago,          "
                + "       tblPagos.nitCC,           "
                + "       tblPagos.estado,          "
                + "       tblPagos.idUsuario,       "
                + "       tblPagos.vrRteFuente,     "
                + "       tblPagos.vrDescuento,     "
                + "       tblPagos.idPeriodo,       "
                + "       tblPagos.vrRteIva,        "
                + "       tblPagos.vrRteIca,        "
                + "ISNULL((SELECT TOP 1 tblFacturaElectronica.idDctoDian "
                + "FROM tblFacturaElectronica "
                + "WHERE tblFacturaElectronica.idDcto =  tblPagos.idDcto),0) as idDcto,          "
                + "       tblPagos.idDctoNitCC,     "
                + "       tblPagos.idPlanilla,      "
                + "       tblPagos.vrSaldo          "
                + "FROM   tblPagos                  "
                + "WHERE EXISTS (                   "
                + "SELECT tblPagosMedios.*          "
                + "FROM tblPagosMedios              "
                + "WHERE tblPagos.idLocal      =    "
                + "        tblPagosMedios.idLocal   "
                + "AND tblPagos.idTipoOrden    =    "
                + "    tblPagosMedios.idTipoOrden   "
                + "AND tblPagos.idRecibo       =    "
                + "       tblPagosMedios.idRecibo   "
                + "AND tblPagos.indicador      =    "
                + "     tblPagosMedios.indicador)   "
                + "AND  tblPagos.idLocal     =      "
                + getIdLocal() + "                  "
                + "AND    tblPagos.idTipoOrden =    "
                + getIdTipoOrden() + "              "
                + "AND    tblPagos.idPlanilla  =  "
                + getIdPlanilla();

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaPagoBean fachadaPagoBean;

            while (rs.next()) {

                //
                fachadaPagoBean = new FachadaPagoBean();

                //
                fachadaPagoBean.setIdLocal(rs.getInt("idLocal"));
                fachadaPagoBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaPagoBean.setIdRecibo(rs.getInt("idRecibo"));
                fachadaPagoBean.setFechaPago(rs.getString("fechaPago"));
                fachadaPagoBean.setVrPago(rs.getDouble("vrPago"));
                fachadaPagoBean.setNitCC(rs.getString("nitCC"));
                fachadaPagoBean.setIdUsuario(rs.getDouble("idUsuario"));
                fachadaPagoBean.setVrRteFuente(rs.getDouble("vrRteFuente"));
                fachadaPagoBean.setVrDescuento(rs.getDouble("vrDescuento"));
                fachadaPagoBean.setVrRteIva(rs.getDouble("vrRteIva"));
                fachadaPagoBean.setVrRteIca(rs.getDouble("vrRteIca"));
                fachadaPagoBean.setIdPlanilla(rs.getInt("idPlanilla"));
                fachadaPagoBean.setIdDcto(rs.getInt("idDcto"));
                fachadaPagoBean.setIdDctoNitCC(rs.getString("idDctoNitCC"));
                fachadaPagoBean.setVrSaldo(rs.getDouble("vrSaldo"));

                //
                list.add(new ColaboraPago(fachadaPagoBean.getIdLocal(),
                        fachadaPagoBean.getIdTipoOrden(),
                        fachadaPagoBean.getIdRecibo(),
                        fachadaPagoBean.getFechaPago(),
                        fachadaPagoBean.getVrPago(),
                        fachadaPagoBean.getNitCC(),
                        fachadaPagoBean.getIdUsuario(),
                        fachadaPagoBean.getVrRteFuente(),
                        fachadaPagoBean.getVrDescuento(),
                        fachadaPagoBean.getVrRteIva(),
                        fachadaPagoBean.getVrRteIca(),
                        fachadaPagoBean.getIdPlanilla(),
                        fachadaPagoBean.getIdDcto(),
                        fachadaPagoBean.getIdDctoNitCC(),
                        fachadaPagoBean.getVrSaldo()));

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
            return list;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {

            // Cierra la conexion
            jdbcAccess.cleanup(connection, selectStatement, null);
            return list;

        }
    }

    // Metodo
    public Collection listaPlanillaVendedor() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        //
        Collection list = new ArrayList();

        //
        Connection connection = null;

        //
        String selectStr
                = "SELECT tblPagos.idLocal,         "
                + "       tblPagos.idTipoOrden,     "
                + "       tblPagos.idRecibo,        "
                + "       tblPagos.indicador,       "
                + "       tblPagos.fechaPago,       "
                + "       tblPagos.vrPago,          "
                + "       tblPagos.nitCC,           "
                + "       tblPagos.estado,          "
                + "       tblPagos.idUsuario,       "
                + "       tblPagos.vrRteFuente,     "
                + "       tblPagos.vrDescuento,     "
                + "       tblPagos.idPeriodo,       "
                + "       tblPagos.vrRteIva,        "
                + "       tblPagos.vrRteIca,        "
                + "       tblPagos.idDcto,          "
                + "       tblPagos.idDctoNitCC,     "
                + "       tblPagos.idPlanilla,      "
                + "       tblPagos.vrSaldo,         "
                + "  (SELECT                        "
                + "     tblTerceros.nombreTercero + "
                + "                           '/' + "
                + "     tblTerceros.nombreEmpresa   "
                + "   FROM tblTerceros              "
                + "   WHERE tblTerceros.idCliente = "
                + "                tblPagos.nitCC ) "
                + "                AS nombreCliente "
                + "FROM   tblPagos                  "
                + "WHERE EXISTS (                   "
                + "SELECT tblPagosMedios.*          "
                + "FROM tblPagosMedios              "
                + "WHERE tblPagos.idLocal      =    "
                + "        tblPagosMedios.idLocal   "
                + "AND tblPagos.idTipoOrden    =    "
                + "    tblPagosMedios.idTipoOrden   "
                + "AND tblPagos.idRecibo       =    "
                + "       tblPagosMedios.idRecibo   "
                + "AND tblPagos.indicador      =    "
                + "     tblPagosMedios.indicador)   "
                + "AND  tblPagos.idLocal     =      "
                + getIdLocal() + "                  "
                + "AND    tblPagos.idTipoOrden =    "
                + getIdTipoOrden() + "              "
                + "AND    tblPagos.idPlanilla  =    "
                + getIdPlanilla() + "               "
                + "ORDER BY tblPagos.idLocal,       "
                + "         tblPagos.idTipoOrden,   "
                + "         tblPagos.idPlanilla,    "
                + "         tblPagos.nitCC          ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaPagoBean fachadaPagoBean;

            while (rs.next()) {

                //
                fachadaPagoBean = new FachadaPagoBean();

                //
                fachadaPagoBean.setIdLocal(rs.getInt("idLocal"));
                fachadaPagoBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaPagoBean.setIdRecibo(rs.getInt("idRecibo"));
                fachadaPagoBean.setFechaPago(rs.getString("fechaPago"));
                fachadaPagoBean.setVrPago(rs.getDouble("vrPago"));
                fachadaPagoBean.setNitCC(rs.getString("nitCC"));
                fachadaPagoBean.setIdUsuario(rs.getDouble("idUsuario"));
                fachadaPagoBean.setVrRteFuente(rs.getDouble("vrRteFuente"));
                fachadaPagoBean.setVrDescuento(rs.getDouble("vrDescuento"));
                fachadaPagoBean.setVrRteIva(rs.getDouble("vrRteIva"));
                fachadaPagoBean.setVrRteIca(rs.getDouble("vrRteIca"));
                fachadaPagoBean.setIdPlanilla(rs.getInt("idPlanilla"));
                fachadaPagoBean.setIdDcto(rs.getInt("idDcto"));
                fachadaPagoBean.setIdDctoNitCC(rs.getString("idDctoNitCC"));
                fachadaPagoBean.setVrSaldo(rs.getDouble("vrSaldo"));
                fachadaPagoBean.setNombreCliente(rs.getString("nombreCliente"));

                //
                list.add(new ColaboraPago(fachadaPagoBean.getIdLocal(),
                        fachadaPagoBean.getIdTipoOrden(),
                        fachadaPagoBean.getIdRecibo(),
                        fachadaPagoBean.getFechaPago(),
                        fachadaPagoBean.getVrPago(),
                        fachadaPagoBean.getNitCC(),
                        fachadaPagoBean.getIdUsuario(),
                        fachadaPagoBean.getVrRteFuente(),
                        fachadaPagoBean.getVrDescuento(),
                        fachadaPagoBean.getVrRteIva(),
                        fachadaPagoBean.getVrRteIca(),
                        fachadaPagoBean.getIdPlanilla(),
                        fachadaPagoBean.getIdDcto(),
                        fachadaPagoBean.getIdDctoNitCC(),
                        fachadaPagoBean.getVrSaldo(),
                        fachadaPagoBean.getNombreCliente()));

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
            return list;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {

            // Cierra la conexion
            jdbcAccess.cleanup(connection, selectStatement, null);
            return list;

        }
    }

    // listaHistoria
    public Collection listaHistoria() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        //
        Collection list = new ArrayList();

        Connection connection = null;

        //
        String selectStr
                = "SELECT tblPagos.idLocal,       "
                + "       tblPagos.idTipoOrden,   "
                + "       tblPagos.idRecibo,      "
                + "       tblPagos.indicador,     "
                + "       tblPagos.fechaPago,     "
                + "       tblPagos.vrPago,        "
                + "       tblPagos.nitCC,         "
                + "       tblPagos.estado,        "
                + "       tblPagos.idUsuario,     "
                + "       tblPagos.vrRteFuente,   "
                + "       tblPagos.vrDescuento,   "
                + "       tblPagos.idPeriodo,     "
                + "       tblPagos.vrRteIva,      "
                + "       tblPagos.vrRteIca,      "
                + "       tblPagos.idDcto,        "
                + "       tblPagos.idDctoNitCC,   "
                + "       tblPagos.idPlanilla,    "
                + "       tblPagos.vrSaldo        "
                + "FROM   tblPagos                "
                + "WHERE EXISTS (                 "
                + "SELECT tblPagosMedios.*        "
                + "FROM tblPagosMedios            "
                + "WHERE tblPagos.idLocal       = "
                + "        tblPagosMedios.idLocal "
                + "AND tblPagos.idTipoOrden    =  "
                + "    tblPagosMedios.idTipoOrden "
                + "AND tblPagos.idRecibo       =  "
                + "       tblPagosMedios.idRecibo "
                + "AND tblPagos.indicador      =  "
                + "     tblPagosMedios.indicador) "
                + "AND  tblPagos.idLocal       =  "
                + getIdLocal() + "                "
                + "AND    tblPagos.idTipoOrden =  "
                + getIdTipoOrden() + "            "
                + "AND    tblPagos.nitCC  =      '"
                + getNitCC() + "'                 "
                + "AND    tblPagos.fechaPago      "
                + "BETWEEN                       '"
                + getFechaInicialSqlServer() + "' "
                + " AND                          '"
                + getFechaFinalSqlServer() + "'   ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaPagoBean fachadaPagoBean;

            while (rs.next()) {

                //
                fachadaPagoBean = new FachadaPagoBean();

                //
                fachadaPagoBean.setIdLocal(rs.getInt("idLocal"));
                fachadaPagoBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaPagoBean.setIdRecibo(rs.getInt("idRecibo"));
                fachadaPagoBean.setFechaPago(rs.getString("fechaPago"));
                fachadaPagoBean.setVrPago(rs.getDouble("vrPago"));
                fachadaPagoBean.setNitCC(rs.getString("nitCC"));
                fachadaPagoBean.setIdUsuario(rs.getDouble("idUsuario"));
                fachadaPagoBean.setVrRteFuente(rs.getDouble("vrRteFuente"));
                fachadaPagoBean.setVrDescuento(rs.getDouble("vrDescuento"));
                fachadaPagoBean.setVrRteIva(rs.getDouble("vrRteIva"));
                fachadaPagoBean.setVrRteIca(rs.getDouble("vrRteIca"));
                fachadaPagoBean.setIdPlanilla(rs.getInt("idPlanilla"));
                fachadaPagoBean.setIdDcto(rs.getInt("idDcto"));
                fachadaPagoBean.setIdDctoNitCC(rs.getString("idDctoNitCC"));
                fachadaPagoBean.setVrSaldo(rs.getDouble("vrSaldo"));

                //
                list.add(new ColaboraPago(fachadaPagoBean.getIdLocal(),
                        fachadaPagoBean.getIdTipoOrden(),
                        fachadaPagoBean.getIdRecibo(),
                        fachadaPagoBean.getFechaPagoCorta(),
                        fachadaPagoBean.getVrPago(),
                        fachadaPagoBean.getNitCC(),
                        fachadaPagoBean.getIdUsuario(),
                        fachadaPagoBean.getVrRteFuente(),
                        fachadaPagoBean.getVrDescuento(),
                        fachadaPagoBean.getVrRteIva(),
                        fachadaPagoBean.getVrRteIca(),
                        fachadaPagoBean.getIdPlanilla(),
                        fachadaPagoBean.getIdDcto(),
                        fachadaPagoBean.getIdDctoNitCC(),
                        fachadaPagoBean.getVrSaldo()));

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
            return list;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {

            // Cierra la conexion
            jdbcAccess.cleanup(connection, selectStatement, null);
            return list;

        }
    }

    // listaRecaudo
    public Vector listaRecaudo(String xIdLocal) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        //
        Vector contenedor = new Vector();

        Connection connection = null;

        //
        String selectStr
                = "SELECT     tblPagos.nitCC,                                            "
                + "           (SELECT TOP 1 tblTerceros.nombreTercero                    "
                + "	     FROM tblTerceros                                          "
                + "	     WHERE tblTerceros.idCliente  =                            "
                + "	     tblPagos.nitCC)                                           "
                + "	     AS nombreTercero,                                         "
                + "	     tblPagos.idLocal,                                         "
                + "            tblPagos.idTipoOrden,                                     "
                + "	     tblPagos.idRecibo,                                        "
                + "	     tblPagos.indicador,                                       "
                + "	     tblPagos.fechaPago,                                       "
                + "	     tblPagos.vrPago,                                          "
                + "	     tblPagos.vrRteFuente,                                     "
                + "	     tblPagos.vrDescuento,                                     "
                + "	     tblPagos.vrRteIva,                                        "
                + "	     tblPagos.idDcto,                                          "
                + "	     tblPagos.idDctoNitCC,                                     "
                + "	     tblPagos.idPlanilla,                                      "
                + "	     tblPagos.vrRteIca,                                        "
                + "	( SELECT ctrlUsuarios.aliasUsuario                             "
                + "	FROM ctrlUsuarios                                              "
                + "	WHERE ctrlUsuarios.idUsuario =                                 "
                + "	tblPagos.idVendedor )                                            "
                + "	AS aliasUsuario,                                                 "
                + "	tmpLOG.horaTx                                                    "
                + "									 "
                + "	FROM   tblPagos,(                                                "
                + "	SELECT     tblAgendaLogVisitas.IDLOG,                            "
                + "	LTRIM(SUBSTRING(CONVERT(VARCHAR(20),                             "
                + "	tblAgendaLogVisitas.fechaTx, 22), 10,5)+                         "
                + "	RIGHT(CONVERT(VARCHAR(20), +                                     "
                + "	tblAgendaLogVisitas.fechaTx, 22), 3))                            "
                + "	AS horaTx                                                        "
                + "	FROM tblAgendaLogVisitas) AS tmpLOG                              "
                + "	WHERE EXISTS (                                                   "
                + "	SELECT tblPagosMedios.*                                          "
                + "	FROM tblPagosMedios                                              "
                + "	WHERE tblPagos.idLocal              =                            "
                + "	tblPagosMedios.idLocal                                           "
                + "	AND tblPagos.idTipoOrden            =                            "
                + "	tblPagosMedios.idTipoOrden                                       "
                + "	AND tblPagos.idRecibo               =                            "
                + "           tblPagosMedios.idRecibo                                      "
                + "	AND tblPagos.idLog                  =                            "
                + "	    tblPagosMedios.idLog                                         "
                + "       AND tblPagos.indicador              =                            "
                + "	    tblPagosMedios.indicador)                                    "
                + "AND   tblPagos.idLocal             =                  "
                + xIdLocal + "                                           "
                + "AND   tblPagos.idTipoOrden         IN (               "
                + getIdTipoOrdenCadena() + ")                            "
                + "AND tblPagos.indicador BETWEEN                        "
                + getIndicadorInicial() + " AND                          "
                + getIndicadorFinal() + "                                "
                + "AND tblPagos.fechaPago  BETWEEN                      '"
                + getFechaInicialSqlServer() + "'     AND               '"
                + getFechaFinalSqlServer() + "'                          "
                + "AND tblPagos.idLog = tmpLOG.idLog                      "
                + "ORDER BY tblPagos.idLocal,                             "
                + "tblPagos.idRecibo";

        /*
        String selectStr =
"SELECT         tblPagos.nitCC,                        " +
"           (SELECT TOP 1 tblTerceros.nombreTercero    " +
"                  FROM tblTerceros                    " +
"                  WHERE tblTerceros.idCliente  =      " +
"                                    tblPagos.nitCC)   " +
"                                  AS nombreTercero,   " +
"                     tblPagos.idLocal,                " +
"                     tblPagos.idTipoOrden,            " +
"                     tblPagos.idRecibo,               " +
"                     tblPagos.indicador,              " +
"                     tblPagos.fechaPago,              " +
"                     tblPagos.vrPago,                 " +
"                     tblPagos.vrRteFuente,            " +
"                     tblPagos.vrDescuento,            " +
"                     tblPagos.vrRteIva,               " +
"                     tblPagos.idDcto,                 " +
"                     tblPagos.idDctoNitCC,            " +
"                     tblPagos.idPlanilla,             " +
"                     tblPagos.vrRteIca,               " +
"                ( SELECT ctrlUsuarios.aliasUsuario    " +
"                  FROM ctrlUsuarios                   " +
"                  WHERE ctrlUsuarios.idUsuario =      " +
"                              tblPagos.idVendedor )   " +
"                                    AS aliasUsuario   " +
"              FROM   tblPagos                         " +
"              WHERE EXISTS (                          " +
"              SELECT tblPagosMedios.*                 " +
"              FROM tblPagosMedios                     " +
"              WHERE tblPagos.idLocal              =   " +
"                      tblPagosMedios.idLocal          " +
"              AND tblPagos.idTipoOrden            =   " +
"                  tblPagosMedios.idTipoOrden          " +
"              AND tblPagos.idRecibo               =   " +
"                            tblPagosMedios.idRecibo   " +
"              AND tblPagos.idLog                  =   " +
"                               tblPagosMedios.idLog   " +
"              AND tblPagos.indicador              =   " +
"                          tblPagosMedios.indicador)   " +
"AND   tblPagos.idLocal             =                  " +
xIdLocal + "                                           " +
"AND   tblPagos.idTipoOrden         IN (               " +
getIdTipoOrdenCadena() + ")                            " +
"AND tblPagos.indicador BETWEEN                        " +
getIndicadorInicial() + " AND                          " +
getIndicadorFinal() + "                                " +
"AND tblPagos.fechaPago  BETWEEN                      '" +
getFechaInicialSqlServer() + "'     AND               '" +
getFechaFinalSqlServer() + "'                          " +
"ORDER BY tblPagos.idLocal,                            " +
"         tblPagos.idRecibo" ;
         */
        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaPagoBean fachadaPagoBean;

            while (rs.next()) {

                //
                fachadaPagoBean = new FachadaPagoBean();

                //
                fachadaPagoBean.setNitCC(rs.getString("nitCC"));
                fachadaPagoBean.setIdLocal(rs.getInt("idLocal"));
                fachadaPagoBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaPagoBean.setIdRecibo(rs.getInt("idRecibo"));
                fachadaPagoBean.setFechaPago(rs.getString("fechaPago"));
                fachadaPagoBean.setIndicador(rs.getInt("indicador"));
                fachadaPagoBean.setVrPago(rs.getDouble("vrPago"));
                fachadaPagoBean.setVrRteFuente(rs.getDouble("vrRteFuente"));
                fachadaPagoBean.setVrDescuento(rs.getDouble("vrDescuento"));
                fachadaPagoBean.setVrRteIva(rs.getDouble("vrRteIva"));
                fachadaPagoBean.setVrRteIca(rs.getDouble("vrRteIca"));
                fachadaPagoBean.setIdDcto(rs.getInt("idDcto"));
                fachadaPagoBean.setIdDctoNitCC(rs.getString("idDctoNitCC"));
                fachadaPagoBean.setAliasUsuario(rs.getString("aliasUsuario"));
                fachadaPagoBean.setIdPlanilla(rs.getInt("idPlanilla"));
                fachadaPagoBean.setNombreCliente(rs.getString("nombreTercero"));
                fachadaPagoBean.setHoraTx(rs.getString("horaTx"));

                //
                contenedor.add(fachadaPagoBean);

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

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {

            // Cierra la conexion
            jdbcAccess.cleanup(connection, selectStatement, null);
            return contenedor;

        }
    }

    // listaRecaudoVendedor
    public Vector listaRecaudoVendedor(String xIdLocal) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        //
        Vector contenedor = new Vector();

        Connection connection = null;

        //
        String selectStr
                = "SELECT         tblPagos.nitCC,                        "
                + "           (SELECT TOP 1 tblTerceros.nombreTercero    "
                + "                  FROM tblTerceros                    "
                + "                  WHERE tblTerceros.idCliente  =      "
                + "                                    tblPagos.nitCC)   "
                + "                                  AS nombreTercero,   "
                + "                     tblPagos.idLocal,                "
                + "                     tblPagos.idTipoOrden,            "
                + "                     tblPagos.idRecibo,               "
                + "                     tblPagos.indicador,              "
                + "                     tblPagos.fechaPago,              "
                + "                     tblPagos.vrPago,                 "
                + "                     tblPagos.vrRteFuente,            "
                + "                     tblPagos.vrDescuento,            "
                + "                     tblPagos.vrRteIva,               "
                + "                     tblPagos.idDcto,                 "
                + "                     tblPagos.idDctoNitCC,            "
                + "                     tblPagos.idPlanilla,             "
                + "                     tblPagos.vrRteIca,               "
                + "                ( SELECT ctrlUsuarios.aliasUsuario    "
                + "                  FROM ctrlUsuarios                   "
                + "                  WHERE ctrlUsuarios.idUsuario =      "
                + "                              tblPagos.idVendedor )   "
                + "                                    AS aliasUsuario   "
                + "              FROM   tblPagos                         "
                + "              WHERE EXISTS (                          "
                + "              SELECT tblPagosMedios.*                 "
                + "              FROM tblPagosMedios                     "
                + "              WHERE tblPagos.idLocal              =   "
                + "                      tblPagosMedios.idLocal          "
                + "              AND tblPagos.idTipoOrden            =   "
                + "                  tblPagosMedios.idTipoOrden          "
                + "              AND tblPagos.idRecibo               =   "
                + "                            tblPagosMedios.idRecibo   "
                + "              AND tblPagos.idLog                  =   "
                + "                               tblPagosMedios.idLog   "
                + "              AND tblPagos.indicador              =   "
                + "                          tblPagosMedios.indicador)   "
                + "AND   tblPagos.idLocal             =                  "
                + xIdLocal + "                                           "
                + "AND   tblPagos.idTipoOrden         IN (               "
                + getIdTipoOrdenCadena() + ")                            "
                + "AND tblPagos.indicador BETWEEN                        "
                + getIndicadorInicial() + " AND                          "
                + getIndicadorFinal() + "                                "
                + "AND tblPagos.fechaPago  BETWEEN                      '"
                + getFechaInicialSqlServer() + "'     AND               '"
                + getFechaFinalSqlServer() + "'                          "
                + "AND tblPagos.idVendedor                           =   "
                + getIdVendedor() + "                                    "
                + "ORDER BY tblPagos.idLocal,                            "
                + "         tblPagos.idRecibo";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaPagoBean fachadaPagoBean;

            while (rs.next()) {

                //
                fachadaPagoBean = new FachadaPagoBean();

                //
                fachadaPagoBean.setNitCC(rs.getString("nitCC"));
                fachadaPagoBean.setIdLocal(rs.getInt("idLocal"));
                fachadaPagoBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaPagoBean.setIdRecibo(rs.getInt("idRecibo"));
                fachadaPagoBean.setFechaPago(rs.getString("fechaPago"));
                fachadaPagoBean.setIndicador(rs.getInt("indicador"));
                fachadaPagoBean.setVrPago(rs.getDouble("vrPago"));
                fachadaPagoBean.setVrRteFuente(rs.getDouble("vrRteFuente"));
                fachadaPagoBean.setVrDescuento(rs.getDouble("vrDescuento"));
                fachadaPagoBean.setVrRteIva(rs.getDouble("vrRteIva"));
                fachadaPagoBean.setVrRteIca(rs.getDouble("vrRteIca"));
                fachadaPagoBean.setIdDcto(rs.getInt("idDcto"));
                fachadaPagoBean.setIdDctoNitCC(rs.getString("idDctoNitCC"));
                fachadaPagoBean.setAliasUsuario(rs.getString("aliasUsuario"));
                fachadaPagoBean.setIdPlanilla(rs.getInt("idPlanilla"));
                fachadaPagoBean.setNombreCliente(rs.getString("nombreTercero"));

                //
                contenedor.add(fachadaPagoBean);

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

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {

            // Cierra la conexion
            jdbcAccess.cleanup(connection, selectStatement, null);
            return contenedor;

        }
    }

    // listaRecibo
    public Collection listaRecibo() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        //
        Collection list = new ArrayList();

        Connection connection = null;

        //
        String selectStr
                = "SELECT tblPagos.idLocal,       "
                + "       tblPagos.idTipoOrden,   "
                + "       tblPagos.idRecibo,      "
                + "       tblPagos.indicador,     "
                + "       tblPagos.fechaPago,     "
                + "       tblPagos.vrPago,        "
                + "       tblPagos.nitCC,         "
                + "       tblPagos.estado,        "
                + "       tblPagos.idUsuario,     "
                + "       tblPagos.vrRteFuente,   "
                + "       tblPagos.vrDescuento,   "
                + "       tblPagos.idPeriodo,     "
                + "       tblPagos.vrRteIva,      "
                + "       tblPagos.vrRteIca,      "
                + "     ISNULL((SELECT TOP 1 tblFacturaElectronica.idDctoDian "
                + " FROM tblFacturaElectronica "
                + "WHERE tblFacturaElectronica.idDcto =  tblPagos.idDcto),0) as idDcto,        "
                + "       tblPagos.idDctoNitCC,   "
                + "       tblPagos.idPlanilla,    "
                + "       tblPagos.vrSaldo        "
                + "FROM   tblPagos                "
                + "INNER JOIN tblPagosMedios      "
                + "ON tblPagos.idLocal         =  "
                + "       tblPagosMedios.idLocal  "
                + "AND tblPagos.idTipoOrden    =  "
                + "   tblPagosMedios.idTipoOrden  "
                + "AND tblPagos.idRecibo       =  "
                + "      tblPagosMedios.idRecibo  "
                + "AND tblPagos.indicador      =  "
                + "     tblPagosMedios.indicador  "
                + "WHERE  tblPagos.idLocal     =  "
                + getIdLocal() + "                "
                + "AND    tblPagos.idTipoOrden =  "
                + getIdTipoOrden() + "            "
                + "AND    tblPagos.idRecibo    =  "
                + getIdRecibo() + "              "
                + "AND    tblPagos.indicador   =  "
                + getIndicador();

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaPagoBean fachadaPagoBean;

            while (rs.next()) {

                //
                fachadaPagoBean = new FachadaPagoBean();

                //
                fachadaPagoBean.setIdLocal(rs.getInt("idLocal"));
                fachadaPagoBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaPagoBean.setIdRecibo(rs.getInt("idRecibo"));
                fachadaPagoBean.setFechaPago(rs.getString("fechaPago"));
                fachadaPagoBean.setVrPago(rs.getDouble("vrPago"));
                fachadaPagoBean.setNitCC(rs.getString("nitCC"));
                fachadaPagoBean.setIdUsuario(rs.getDouble("idUsuario"));
                fachadaPagoBean.setVrRteFuente(rs.getDouble("vrRteFuente"));
                fachadaPagoBean.setVrDescuento(rs.getDouble("vrDescuento"));
                fachadaPagoBean.setVrRteIva(rs.getDouble("vrRteIva"));
                fachadaPagoBean.setVrRteIca(rs.getDouble("vrRteIca"));
                fachadaPagoBean.setIdPlanilla(rs.getInt("idPlanilla"));
                fachadaPagoBean.setIdDcto(rs.getInt("idDcto"));
                fachadaPagoBean.setIdDctoNitCC(rs.getString("idDctoNitCC"));
                fachadaPagoBean.setVrSaldo(rs.getDouble("vrSaldo"));

                //
                list.add(new ColaboraPago(fachadaPagoBean.getIdLocal(),
                        fachadaPagoBean.getIdTipoOrden(),
                        fachadaPagoBean.getIdRecibo(),
                        fachadaPagoBean.getFechaPago(),
                        fachadaPagoBean.getVrPago(),
                        fachadaPagoBean.getNitCC(),
                        fachadaPagoBean.getIdUsuario(),
                        fachadaPagoBean.getVrRteFuente(),
                        fachadaPagoBean.getVrDescuento(),
                        fachadaPagoBean.getVrRteIva(),
                        fachadaPagoBean.getVrRteIca(),
                        fachadaPagoBean.getIdPlanilla(),
                        fachadaPagoBean.getIdDcto(),
                        fachadaPagoBean.getIdDctoNitCC(),
                        fachadaPagoBean.getVrSaldo()));

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
            return list;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {

            // Cierra la conexion
            jdbcAccess.cleanup(connection, selectStatement, null);
            return list;

        }
    }

    // listaPago
    public Vector listaPagoTercero(int xIdLocal,
            int xIdTipoOrden) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        //
        Vector contenedor = new Vector();

        boolean validaOk = false;
        Connection connection = null;

        //
        String selectStr
                = "SELECT tblPagos.idLocal,       "
                + "       tblPagos.idTipoOrden,   "
                + "       tblPagos.idRecibo,      "
                + "       tblPagos.indicador,     "
                + "       tblPagos.fechaPago,     "
                + "       tblPagos.vrPago,        "
                + "       tblPagos.nitCC,         "
                + "       tblPagos.estado,        "
                + "       tblPagos.idUsuario,     "
                + "       tblPagos.vrRteFuente,   "
                + "       tblPagos.vrDescuento,   "
                + "       tblPagos.idPeriodo,     "
                + "       tblPagos.vrRteIva,      "
                + "       tblPagos.vrRteIca,      "
                + "       tblPagos.idDcto,        "
                + "       tblPagos.idDctoNitCC,   "
                + "       tblPagos.idPlanilla,    "
                + "       tblPagos.vrSaldo,        "
                + "       ISNULL ( (SELECT TOP 1 idDctoDian FROM [tblFacturaElectronica] WHERE idDcto=tblPagos.idDcto AND idTipoOrden=9 AND tipo_xml=1) ,0) AS FE        "
                + " FROM   tblPagos                "
                + "WHERE EXISTS (                 "
                + "SELECT tblPagosMedios.*        "
                + "FROM tblPagosMedios            "
                + "WHERE tblPagos.idLocal       = "
                + "        tblPagosMedios.idLocal "
                + "AND tblPagos.idTipoOrden    =  "
                + "    tblPagosMedios.idTipoOrden "
                + "AND tblPagos.idRecibo       =  "
                + "       tblPagosMedios.idRecibo "
                + "AND tblPagos.indicador      =  "
                + "     tblPagosMedios.indicador) "
                + "AND  tblPagos.idLocal       =  "
                + xIdLocal + "                    "
                + "AND    tblPagos.idTipoOrden =  "
                + xIdTipoOrden + "                "
                + "AND    tblPagos.nitCC  =      '"
                + getNitCC() + "'                 "
                + "AND    tblPagos.fechaPago      "
                + "BETWEEN                       '"
                + getFechaInicialSqlServer() + "' "
                + " AND                          '"
                + getFechaFinalSqlServer()
                + "' ORDER BY tblPagos.idLocal,   "
                + "       tblPagos.idTipoOrden,   "
                + "       tblPagos.idRecibo DESC  ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaPagoBean fachadaPagoBean;

            while (rs.next()) {

                //
                fachadaPagoBean = new FachadaPagoBean();
            
                fachadaPagoBean.setIdLocal(rs.getInt("idLocal"));
                fachadaPagoBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaPagoBean.setIdRecibo(rs.getInt("idRecibo"));
                fachadaPagoBean.setIndicador(rs.getInt("indicador"));
                fachadaPagoBean.setFechaPago(rs.getString("fechaPago"));
                fachadaPagoBean.setVrPago(rs.getDouble("vrPago"));
                fachadaPagoBean.setNitCC(rs.getString("nitCC"));
                fachadaPagoBean.setEstado(rs.getInt("estado"));
                fachadaPagoBean.setIdUsuario(rs.getDouble("idUsuario"));
                fachadaPagoBean.setVrRteFuente(rs.getDouble("vrRteFuente"));
                fachadaPagoBean.setVrDescuento(rs.getDouble("vrDescuento"));
                fachadaPagoBean.setIdPeriodo(rs.getString("idPeriodo"));
                fachadaPagoBean.setVrRteIva(rs.getDouble("vrRteIva"));
                fachadaPagoBean.setVrRteIca(rs.getDouble("vrRteIca"));
                fachadaPagoBean.setIdDcto(rs.getInt("idDcto"));
                fachadaPagoBean.setIdDctoNitCC(rs.getString("idDctoNitCC"));
                fachadaPagoBean.setIdPlanilla(rs.getInt("idPlanilla"));
                fachadaPagoBean.setVrSaldo(rs.getDouble("vrSaldo"));
                fachadaPagoBean.setIdDctoDian(rs.getString("FE"));

                //
                contenedor.add(fachadaPagoBean);

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

    // listaPagoTemporalFCH
    public FachadaPagoBean listaPagoTemporalFCH(int xIdLocal,
            int xIdTipoOrden,
            double xIdDcto,
            int xIndicador) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        //
        FachadaPagoBean fachadaPagoBean = new FachadaPagoBean();

        Connection connection = null;

        //
        String selectStr
                = "SELECT SUM(tblPagos.vrPago)  AS vrPago,        "
                + "SUM(tblPagos.vrDescuento)                  AS vrDescuento,        "
                + "SUM(tblPagos.vrRteFuente)                 AS  vrRteFuente,        "
                + "SUM(tblPagos.vrRteIva)                       AS vrRteIva,         "
                + "SUM(tblPagos.vrRteIca)                       AS vrRteIca,         "
                + "SUM(tblPagos.vrRteCree)                      AS vrRteCree,   "
                + "( SELECT TOP 1 tblMediosPago.nombreMedio       "
                + "FROM   tblPagosMedios            "
                + "INNER JOIN tblMediosPago         "
                + "ON tblPagosMedios.idMedio   =          tblMediosPago.idMedio      "
                + "WHERE  tblPagos.idLocal     =          tblPagosMedios.idLocal     "
                + "AND tblPagos.idTipoOrden    =      tblPagosMedios.idTipoOrden     "
                + "AND tblPagos.indicador      =        tblPagosMedios.indicador     "
                + "AND tblPagos.idRecibo       =        tblPagosMedios.idRecibo)  "
                + "AS nombreMedio "
                + "FROM   tblPagos                  "
                + "WHERE EXISTS (   SELECT tblPagosMedios.*       FROM tblPagosMedios              "
                + "WHERE tblPagos.idLocal      =            tblPagosMedios.idLocal   "
                + "AND tblPagos.idTipoOrden    =        tblPagosMedios.idTipoOrden   "
                + "AND tblPagos.idRecibo       =           tblPagosMedios.idRecibo   "
                + "AND tblPagos.indicador      =         tblPagosMedios.indicador)   "
                + "AND    tblPagos.idLocal     =    "
                + xIdLocal + "                      "
                + "AND    tblPagos.idTipoOrden =    "
                + xIdTipoOrden + "                  "
                + "AND    tblPagos.indicador   =    "
                + xIndicador + "                    "
                + "AND    tblPagos.idDcto      =    "
                + xIdDcto + "                       "
                + "GROUP BY tblPagos.idLocal,       "
                + "         tblPagos.idTipoOrden,   "
                + "         tblPagos.idRecibo,      "
                + "         tblPagos.indicador      ";

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
                fachadaPagoBean.setVrPago(rs.getDouble("vrPago"));
                fachadaPagoBean.setVrRteFuente(rs.getDouble("vrRteFuente"));
                fachadaPagoBean.setVrDescuento(rs.getDouble("vrDescuento"));
                fachadaPagoBean.setVrRteIva(rs.getDouble("vrRteIva"));
                fachadaPagoBean.setVrRteIca(rs.getDouble("vrRteIca"));
                fachadaPagoBean.setVrRteIca(rs.getDouble("vrRteIca"));
                fachadaPagoBean.setVrRteCree(rs.getDouble("vrRteCree"));
                fachadaPagoBean.setNombreMedio(rs.getString("nombreMedio"));

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
            return fachadaPagoBean;

        }
    }

    // listaPagoTemporalFCH
    public Vector listaPagoTemporalTotal(int xIdLocal,
            int xIdTipoOrden,
            int xIdLog) {

        //
        Vector contenedor = new Vector();

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        Connection connection = null;

        //
        String selectStr
                = "SELECT SUM(tblPagos.vrPago)      "
                + "                      AS vrPago, "
                + "       SUM(tblPagos.vrDescuento) "
                + "                 AS vrDescuento, "
                + "       SUM(tblPagos.vrRteFuente) "
                + "                AS  vrRteFuente, "
                + "       SUM(tblPagos.vrRteIva)    "
                + "                   AS vrRteIva,  "
                + "       SUM(tblPagos.vrRteIca)    "
                + "                   AS vrRteIca   "
                + "FROM   tblPagos                  "
                + "WHERE EXISTS (                   "
                + "SELECT tblPagosMedios.*          "
                + "FROM tblPagosMedios              "
                + "WHERE tblPagos.idLocal      =    "
                + "        tblPagosMedios.idLocal   "
                + "AND tblPagos.idTipoOrden    =    "
                + "    tblPagosMedios.idTipoOrden   "
                + "AND tblPagos.idRecibo       =    "
                + "       tblPagosMedios.idRecibo   "
                + "AND tblPagos.indicador      =    "
                + "     tblPagosMedios.indicador)   "
                + "AND    tblPagos.idLocal     =    "
                + xIdLocal + "                      "
                + "AND    tblPagos.idTipoOrden =    "
                + xIdTipoOrden + "                  "
                + "AND    tblPagos.idlog       =    "
                + xIdLog;

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaPagoBean fachadaPagoBean;

            while (rs.next()) {

                //
                fachadaPagoBean = new FachadaPagoBean();

                //
                fachadaPagoBean.setVrPago(rs.getDouble("vrPago"));
                fachadaPagoBean.setVrRteFuente(rs.getDouble("vrRteFuente"));
                fachadaPagoBean.setVrDescuento(rs.getDouble("vrDescuento"));
                fachadaPagoBean.setVrRteIva(rs.getDouble("vrRteIva"));
                fachadaPagoBean.setVrRteIca(rs.getDouble("vrRteIca"));

                //
                contenedor.add(fachadaPagoBean);

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

    public int getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(int idLocal) {
        this.idLocal = idLocal;
    }

    public int getIdTipoOrden() {
        return idTipoOrden;
    }

    public void setIdTipoOrden(int idTipoOrden) {
        this.idTipoOrden = idTipoOrden;
    }

    public int getIdRecibo() {
        return idRecibo;
    }

    public void setIdRecibo(int idRecibo) {
        this.idRecibo = idRecibo;
    }

    public String getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(String fechaPago) {
        this.fechaPago = fechaPago;
    }

    public double getVrPago() {
        return vrPago;
    }

    public void setVrPago(double vrPago) {
        this.vrPago = vrPago;
    }

    public String getNitCC() {
        return nitCC;
    }

    public void setNitCC(String nitCC) {
        this.nitCC = nitCC;
    }

    public double getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(double idUsuario) {
        this.idUsuario = idUsuario;
    }

    public double getVrRteFuente() {
        return vrRteFuente;
    }

    public void setVrRteFuente(double vrRteFuente) {
        this.vrRteFuente = vrRteFuente;
    }

    public double getVrDescuento() {
        return vrDescuento;
    }

    public void setVrDescuento(double vrDescuento) {
        this.vrDescuento = vrDescuento;
    }

    public double getVrRteIva() {
        return vrRteIva;
    }

    public void setVrRteIva(double vrRteIva) {
        this.vrRteIva = vrRteIva;
    }

    public double getVrSaldo() {
        return vrSaldo;
    }

    public void setVrSaldo(double vrSaldo) {
        this.vrSaldo = vrSaldo;
    }

    public int getIdPlanilla() {
        return idPlanilla;
    }

    public void setIdPlanilla(int idPlanilla) {
        this.idPlanilla = idPlanilla;
    }

    public double getIdDcto() {
        return idDcto;
    }

    public void setIdDcto(double idDcto) {
        this.idDcto = idDcto;
    }

    public String getIdDctoNitCC() {
        return idDctoNitCC;
    }

    public void setIdDctoNitCC(String idDctoNitCC) {
        this.idDctoNitCC = idDctoNitCC;
    }

    public double getVrRteIca() {
        return vrRteIca;
    }

    public void setVrRteIca(double vrRteIca) {
        this.vrRteIca = vrRteIca;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    /**
     * @return the idTipoOrdenCadena
     */
    public String getIdTipoOrdenCadena() {
        return idTipoOrdenCadena;
    }

    /**
     * @param idTipoOrdenCadena the idTipoOrdenCadena to set
     */
    public void setIdTipoOrdenCadena(String idTipoOrdenCadena) {
        this.idTipoOrdenCadena = idTipoOrdenCadena;
    }
}
