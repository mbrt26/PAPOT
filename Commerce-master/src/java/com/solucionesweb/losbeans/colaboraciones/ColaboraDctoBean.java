package com.solucionesweb.losbeans.colaboraciones;

// Importa la clase helper JDBCAccess que contiene los accesos a la base de datos
import com.solucionesweb.lasayudas.JDBCAccess;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaDctoBean;

// Importa los paquetes de java
import java.sql.*;
import java.io.*;
//import java.util.Date;
import java.util.Vector;
import java.io.IOException.*;

// Importa los paquetes de javax
import javax.naming.*;

// Importa la interface de las constantes
import com.solucionesweb.losbeans.utilidades.IConstantes;
import java.util.ArrayList;
import java.util.Collection;

public class ColaboraDctoBean extends FachadaDctoBean
        implements Serializable, IConstantes {

    //
    private int idLocal;
    private int idTipoOrden;
    private double idDcto;
    private String fechaDcto;
    private String fechaVcto;
    private int diasMora;
    private double vrSaldo;
    private int indicador;
    private String idDctoNitCC;
    private String idCliente;
    private double vrBase;
    private double vrIva;
    private String nombreTercero;
    private String fechaDctoNitCC;
    private int idLocalCruce;
    private int idTipoOrdenCruce;
    private double idDctoCruce;
    private double porcentajeIva;
    private String nombreTipoOrden;
    private int idRecibo;
    private String fechaPago;
    private int idPlanilla;
    private double vrRteFuente;
    private double vrDescuento;
    private double vrRteIva;
    private double vrFactura;
    private String nombreTipoNegocio;
    private String nombreVendedor;
    private String aliasUsuario;
    private int idLog;
    private int indicadorInicial;
    private int indicadorFinal;
    private double idVendedor;
    private String fechaCorte;
    private double margenCliente;
    private int idTipoOrdenINI;
    private int idTipoOrdenFIN;
    // Nombre JNDI del Data Source que esta clase requiere
    private static final String DATA_SOURCE_NAME = "CommerceAccess";
    // Clase helper usada para acceder a la base de datos
    private JDBCAccess jdbcAccess;

    // Metodo constructor por defecto sin parametros
    public ColaboraDctoBean() {

        // Al instanciar el Bean establece la conexion a la base de datos
        jdbcAccess = new JDBCAccess(DATA_SOURCE_NAME);
    }

    //
    public ColaboraDctoBean(int xIdLocal,
            int xIdTipoOrden,
            double xIdDcto,
            String xFechaDcto,
            String xFechaVcto,
            int xDiasMora,
            double xVrSaldo,
            int xIndicador,
            String xIdDctoNitCC,
            String xIdCliente,
            double xVrBase,
            double xVrIva,
            String xNombreTercero,
            String xFechaDctoNitCC,
            int xIdLocalCruce,
            int xIdTipoOrdenCruce,
            double xIdDctoCruce,
            double xPorcentajeIva,
            String xNombreTipoOrden,
            double xVrRteFuente,
            double xVrDescuento,
            double xVrRteIva) {
        this.idLocal = xIdLocal;
        this.idTipoOrden = xIdTipoOrden;
        this.idDcto = xIdDcto;
        this.fechaDcto = xFechaDcto;
        this.fechaVcto = xFechaVcto;
        this.diasMora = xDiasMora;
        this.vrSaldo = xVrSaldo;
        this.indicador = xIndicador;
        this.idDctoNitCC = xIdDctoNitCC;
        this.idCliente = xIdCliente;
        this.vrBase = xVrBase;
        this.vrIva = xVrIva;
        this.nombreTercero = xNombreTercero;
        this.fechaDctoNitCC = xFechaDctoNitCC;
        this.idLocalCruce = xIdLocalCruce;
        this.idTipoOrdenCruce = xIdTipoOrdenCruce;
        this.idDctoCruce = xIdDctoCruce;
        this.porcentajeIva = xPorcentajeIva;
        this.nombreTipoOrden = xNombreTipoOrden;
        this.vrRteFuente = xVrRteFuente;
        this.vrDescuento = xVrDescuento;
        this.vrRteIva = xVrRteIva;

        jdbcAccess = new JDBCAccess(DATA_SOURCE_NAME);

    }

    //
    public ColaboraDctoBean(int xIdLocal,
            int xIdTipoOrden,
            double xIdDcto,
            String xFechaDcto,
            int xIndicador,
            String xIdCliente,
            double xVrBase,
            double xVrIva,
            String xNombreTercero,
            String xIdDctoNitCC,
            String xFechaDctoNitCC,
            int xIdLocalCruce,
            int xIdTipoOrdenCruce,
            double xIdDctoCruce,
            double xVrFactura,
            double xVrDescuento,
            double xVrRteFuente,
            String xNombreTipoNegocio,
            String xAliasUsuario) {
        this.idLocal = xIdLocal;
        this.idTipoOrden = xIdTipoOrden;
        this.idDcto = xIdDcto;
        this.fechaDcto = xFechaDcto;
        this.indicador = xIndicador;
        this.idCliente = xIdCliente;
        this.vrBase = xVrBase;
        this.vrIva = xVrIva;
        this.nombreTercero = xNombreTercero;
        this.idDctoNitCC = xIdDctoNitCC;
        this.fechaDctoNitCC = xFechaDctoNitCC;
        this.idLocalCruce = xIdLocalCruce;
        this.idTipoOrdenCruce = xIdTipoOrdenCruce;
        this.vrFactura = xVrFactura;
        this.vrDescuento = xVrDescuento;
        this.vrRteFuente = xVrRteFuente;
        this.nombreTipoNegocio = xNombreTipoNegocio;
        this.aliasUsuario = xAliasUsuario;

        jdbcAccess = new JDBCAccess(DATA_SOURCE_NAME);

    }

    //
    public ColaboraDctoBean(int xIdLocal,
            int xIdTipoOrden,
            String xIdCliente) {
        this.idLocal = xIdLocal;
        this.idTipoOrden = xIdTipoOrden;
        this.idCliente = xIdCliente;

        jdbcAccess = new JDBCAccess(DATA_SOURCE_NAME);
    }

    //
    public ColaboraDctoBean(int xIdLocal,
            int xIdTipoOrden,
            int xIdLog) {
        this.idLocal = xIdLocal;
        this.idTipoOrden = xIdTipoOrden;
        this.idLog = xIdLog;

        jdbcAccess = new JDBCAccess(DATA_SOURCE_NAME);
    }

    public ColaboraDctoBean(int xIdLocal,
            int xIdTipoOrden,
            int xIndicadorInicial,
            int xIndicadorFinal,
            double xIdVendedor) {
        this.idLocal = xIdLocal;
        this.idTipoOrden = xIdTipoOrden;
        this.indicadorInicial = xIndicadorInicial;
        this.indicadorFinal = xIndicadorFinal;
        this.idVendedor = xIdVendedor;

        jdbcAccess = new JDBCAccess(DATA_SOURCE_NAME);
    }

    // Metodo totalDctosDetalle
    public Vector totalDctosDetalle() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        Vector contenedor = new Vector();
        Connection connection = null;

        String insertStr
                = "SELECT tmpDetalle.idLocal,                              "
                + "       tmpDetalle.idTipoOrden,                          "
                + "       SUM(tmpDetalle.vrTotal *                         "
                + "           tmpDetalle.signo) AS vrTotal,                "
                + "       SUM(tmpDetalle.vrIva) AS vrIva,                  "
                + "       SUM(tmpDetalle.vrDescuento) AS vrDescuento,      "
                + "       SUM(tmpDetalle.vrRteFuente) AS vrRteFuente,      "
                + "       SUM(tmpDetalle.vrRteIva) AS vrRteIva,            "
                + "       SUM(tmpDetalle.vrDsctoFcro) AS vrDsctoFcro       "
                + "FROM tblTerceros,                                       "
                + "     ( SELECT tblDctos.idLocal,                         "
                + "              tblDctos.idTipoOrden,                     "
                + "              tblDctos.idDcto,                          "
                + "              tblDctos.idDctoNitCC,                     "
                + "              tblDctos.indicador,                       "
                + "              tblDctos.nitCC,                           "
                + "              +1 AS signo,                              "
                + "              MIN(tblDctos.fechaDcto) AS fechaDcto,     "
                + "              SUM(tblDctos.vrBase +                     "
                + "                  tblDctos.vrIva) AS vrTotal,           "
                + "              SUM(tblDctos.vrIva) AS vrIva,             "
                + "              SUM(tblDctos.vrDescuento) AS vrDescuento, "
                + "              SUM(tblDctos.vrRteFuente) AS vrRteFuente, "
                + "              SUM(tblDctos.vrRteIva) AS vrRteIva,       "
                + "              SUM(tblDctos.vrDsctoFcro) AS vrDsctoFcro  "
                + "       FROM tblDctos                                    "
                + "       WHERE tblDctos.idLocal    IN ( " + getStrIdLocal() + " ) "
                + "       AND tblDctos.idTipoOrden  IN ( " + getIdTipoOrdenStr() + " ) "
                + "       AND tblDctos.indicador    IN ( " + getIndicadorSesion() + " ) "
                + "       AND tblDctos.estado   NOT IN ( " + getEstadoStr() + " ) "
                + "       AND tblDctos.idDctoCruce  = 0                                 "
                + "       AND (tblDctos.fechaDcto)                                      "
                + "       BETWEEN #" + getFechaInicialStr() + "# AND #" + getFechaCorteStr() + "# "
                + "       GROUP BY tblDctos.idLocal,                       "
                + "                tblDctos.idTipoOrden,                   "
                + "                tblDctos.idDcto,                        "
                + "                tblDctos.idDctoNitCC,                   "
                + "                tblDctos.indicador,                     "
                + "                tblDctos.nitCC UNION                    "
                + "       SELECT tblDctos.idLocal,                         "
                + "              tblDctos.idTipoOrden,                     "
                + "              tblDctos.idDctoCruce AS idDcto,           "
                + "              tblDctos.idDctoNitCC,                     "
                + "              tblDctos.indicador,                       "
                + "              tblDctos.nitCC,                           "
                + "              +1 AS signo,                              "
                + "              MIN(tblDctos.fechaDcto) AS fechaDcto,     "
                + "              SUM(tblDctos.vrBase +                     "
                + "                  tblDctos.vrIva) AS vrTotal,           "
                + "              SUM(tblDctos.vrIva) AS vrIva,             "
                + "              SUM(tblDctos.vrDescuento) AS vrDescuento, "
                + "              SUM(tblDctos.vrRteFuente) AS vrRteFuente, "
                + "              SUM(tblDctos.vrRteIva) AS vrRteIva,       "
                + "              SUM(tblDctos.vrDsctoFcro) AS vrDsctoFcro  "
                + "       FROM tblDctos                                    "
                + "       WHERE tblDctos.idLocal    IN ( " + getStrIdLocal() + " ) "
                + "       AND tblDctos.idTipoOrden  IN ( " + getIdTipoOrdenStr() + " ) "
                + "       AND tblDctos.indicador    IN ( " + getIndicadorSesion() + " ) "
                + "       AND tblDctos.estado   NOT IN ( " + getEstadoStr() + " ) "
                + "       AND tblDctos.idDctoCruce  > 0                                 "
                + "       GROUP BY tblDctos.idLocal,                       "
                + "                tblDctos.idTipoOrden,                   "
                + "                tblDctos.idDctoCruce,                   "
                + "                tblDctos.idDctoNitCC,                   "
                + "                tblDctos.indicador,                     "
                + "                tblDctos.nitCC ) AS tmpDetalle          "
                + "WHERE tmpDetalle.nitCC =                                "
                + "               tblTerceros.idTercero                    "
                + "GROUP BY tmpDetalle.idLocal,                            "
                + "         tmpDetalle.idTipoOrden                         "
                + "HAVING  ( SUM(tmpDetalle.vrTotal * tmpDetalle.signo) -  "
                + "          SUM(tmpDetalle.vrDsctoFcro) ) > 0             ";

        PreparedStatement sentenciaSQL = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            sentenciaSQL = connection.prepareStatement(insertStr);

            // Obtiene el resultset
            ResultSet rs = sentenciaSQL.executeQuery();

            // Variable de fachada de los datos
            FachadaDctoBean fachadaBean;

            while (rs.next()) {

                //
                fachadaBean = new FachadaDctoBean();

                //
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setVrTotal(rs.getDouble("vrTotal"));
                fachadaBean.setVrIva(rs.getDouble("vrIva"));
                fachadaBean.setVrDescuento(rs.getInt("vrDescuento"));
                fachadaBean.setVrRteFuente(rs.getInt("vrRteFuente"));
                fachadaBean.setVrRteIva(rs.getInt("vrRteIva"));
                fachadaBean.setVrDsctoFcro(rs.getInt("vrDsctoFcro"));

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

    // Metodo listaCxCConsolidadoClienteLocalIndicador
    public Vector listaCxCConsolidadoClienteLocalIndicador() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        Vector contenedor = new Vector();
        Connection connection = null;

        String insertStr
                = "SELECT  tmpConsolidado.idLocal,                         "
                + "        tmpConsolidado.idTipoOrden,                     "
                + "        tmpConsolidado.indicador,                       "
                + "        tmpConsolidado.nitCC,                           "
                + "        tmpConsolidado.nombreTercero,                   "
                + "        COUNT(tmpConsolidado.idDcto) AS idDcto,         "
                + "        SUM(tmpConsolidado.vrTotal) AS vrTotal,         "
                + "        SUM(tmpConsolidado.vrIva) AS vrIva,             "
                + "        SUM(tmpConsolidado.vrDescuento) AS vrDescuento, "
                + "        SUM(tmpConsolidado.vrRteFuente) AS vrRteFuente, "
                + "        SUM(tmpConsolidado.vrRteIva) AS vrRteIva,       "
                + "        SUM(tmpConsolidado.vrDsctoFcro) AS vrDsctoFcro  "
                + "FROM                                                    "
                + "(SELECT tmpDetalle.idLocal,                             "
                + "       tmpDetalle.idTipoOrden,                          "
                + "       tmpDetalle.idDcto,                               "
                + "       tmpDetalle.indicador,                            "
                + "       tmpDetalle.nitCC,                                "
                + "       tblTerceros.nombreTercero,                       "
                + "       MIN(tmpDetalle.fechaDcto) AS fechaDcto,          "
                + "       SUM(tmpDetalle.vrTotal *                         "
                + "           tmpDetalle.signo) AS vrTotal,                "
                + "       SUM(tmpDetalle.vrIva) AS vrIva,                  "
                + "       SUM(tmpDetalle.vrDescuento) AS vrDescuento,      "
                + "       SUM(tmpDetalle.vrRteFuente) AS vrRteFuente,      "
                + "       SUM(tmpDetalle.vrRteIva) AS vrRteIva,            "
                + "       SUM(tmpDetalle.vrDsctoFcro) AS vrDsctoFcro       "
                + "FROM tblTerceros,                                       "
                + "     ( SELECT tblDctos.idLocal,                         "
                + "              tblDctos.idTipoOrden,                     "
                + "              tblDctos.idDcto,                          "
                + "              tblDctos.indicador,                       "
                + "              tblDctos.nitCC,                           "
                + "              +1 AS signo,                              "
                + "              MIN(tblDctos.fechaDcto) AS fechaDcto,     "
                + "              SUM(tblDctos.vrBase +                     "
                + "                  tblDctos.vrIva) AS vrTotal,           "
                + "              SUM(tblDctos.vrIva) AS vrIva,             "
                + "              SUM(tblDctos.vrDescuento) AS vrDescuento, "
                + "              SUM(tblDctos.vrRteFuente) AS vrRteFuente, "
                + "              SUM(tblDctos.vrRteIva) AS vrRteIva,       "
                + "              SUM(tblDctos.vrDsctoFcro) AS vrDsctoFcro  "
                + "       FROM tblDctos                                    "
                + "       WHERE tblDctos.idLocal    IN ( " + getStrIdLocal() + " ) "
                + "       AND tblDctos.idTipoOrden  IN ( " + getIdTipoOrdenStr() + " ) "
                + "       AND tblDctos.indicador    IN ( " + getIndicadorSesion() + " ) "
                + "       AND tblDctos.estado   NOT IN ( " + getEstadoStr() + " ) "
                + "       AND tblDctos.idDctoCruce  = 0                                 "
                + "       GROUP BY tblDctos.idLocal,                       "
                + "                tblDctos.idTipoOrden,                   "
                + "                tblDctos.idDcto,                        "
                + "                tblDctos.indicador,                     "
                + "                tblDctos.nitCC UNION                    "
                + "       SELECT tblDctos.idLocal,                         "
                + "              tblDctos.idTipoOrden,                     "
                + "              tblDctos.idDctoCruce AS idDcto,           "
                + "              tblDctos.indicador,                       "
                + "              tblDctos.nitCC,                           "
                + "              +1 AS signo,                              "
                + "              MIN(tblDctos.fechaDcto) AS fechaDcto,     "
                + "              SUM(tblDctos.vrBase +                     "
                + "                  tblDctos.vrIva) AS vrTotal,           "
                + "              SUM(tblDctos.vrIva) AS vrIva,             "
                + "              SUM(tblDctos.vrDescuento) AS vrDescuento, "
                + "              SUM(tblDctos.vrRteFuente) AS vrRteFuente, "
                + "              SUM(tblDctos.vrRteIva) AS vrRteIva,       "
                + "              SUM(tblDctos.vrDsctoFcro) AS vrDsctoFcro  "
                + "       FROM tblDctos                                    "
                + "       WHERE tblDctos.idLocal    IN ( " + getStrIdLocal() + " ) "
                + "       AND tblDctos.idTipoOrden  IN ( " + getIdTipoOrdenStr() + " ) "
                + "       AND tblDctos.indicador    IN ( " + getIndicadorSesion() + " ) "
                + "       AND tblDctos.estado   NOT IN ( " + getEstadoStr() + " ) "
                + "       AND tblDctos.idDctoCruce  > 0                                 "
                + "       GROUP BY tblDctos.idLocal,                       "
                + "                tblDctos.idTipoOrden,                   "
                + "                tblDctos.idDctoCruce,                   "
                + "                tblDctos.indicador,                     "
                + "                tblDctos.nitCC UNION                    "
                + "       SELECT tblPagos.idLocal,                         "
                + "              tblPagos.idTipoOrden,                     "
                + "              tblPagos.idDcto,                          "
                + "              tblPagos.indicador,                       "
                + "              tblPagos.nitCC,                           "
                + "              -1 AS signo,                              "
                + "              MIN(tblPagos.fechaPago) AS fechaDcto,     "
                + "              SUM(tblPagos.vrPago                       "
                + "                  + tblPagos.vrDescuento                "
                + "                  + tblPagos.vrRteFuente                "
                + "                  + tblPagos.vrRteIva) AS vrTotal,      "
                + "              SUM( 0 ) AS vrIva,                        "
                + "              SUM(tblPagos.vrDescuento) AS vrDescuento, "
                + "              SUM(tblPagos.vrRteFuente) AS vrRteFuente, "
                + "              SUM(tblPagos.vrRteIva) AS vrRteIva,       "
                + "              SUM( 0 ) AS vrDsctoFcro                   "
                + "       FROM tblPagos                                    "
                + "       WHERE tblPagos.idLocal    IN ( " + getStrIdLocal() + " ) "
                + "       AND tblPagos.idTipoOrden  IN ( " + getIdTipoOrdenStr() + " ) "
                + "       AND tblPagos.indicador    IN ( " + getIndicadorSesion() + " ) "
                + "       GROUP BY  tblPagos.idLocal,                      "
                + "                 tblPagos.idTipoOrden,                  "
                + "                 tblPagos.idDcto,                       "
                + "                 tblPagos.indicador,                    "
                + "                 tblPagos.nitCC) AS tmpDetalle          "
                + "WHERE tmpDetalle.nitCC =                                "
                + "               tblTerceros.idTercero                    "
                + "GROUP BY tmpDetalle.idLocal,                            "
                + "         tmpDetalle.idTipoOrden,                        "
                + "         tmpDetalle.idDcto,                             "
                + "         tmpDetalle.indicador,                          "
                + "         tmpDetalle.nitCC,                              "
                + "         tblTerceros.nombreTercero                      "
                + "HAVING  ( SUM(tmpDetalle.vrTotal * tmpDetalle.signo) -  "
                + "          SUM(tmpDetalle.vrDsctoFcro) ) > 0 )           "
                + "                                    AS tmpConsolidado   "
                + "GROUP BY tmpConsolidado.idLocal,                        "
                + "         tmpConsolidado.idTipoOrden,                    "
                + "         tmpConsolidado.indicador,                      "
                + "         tmpConsolidado.nitCC,                          "
                + "         tmpConsolidado.nombreTercero                   "
                + "ORDER BY tmpConsolidado.idLocal,                        "
                + "         tmpConsolidado.idTipoOrden,                    "
                + "         tmpConsolidado.nombreTercero                   ";

        PreparedStatement sentenciaSQL = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            sentenciaSQL = connection.prepareStatement(insertStr);

            // Obtiene el resultset
            ResultSet rs = sentenciaSQL.executeQuery();

            // Variable de fachada de los datos
            FachadaDctoBean fachadaBean;

            while (rs.next()) {

                fachadaBean = new FachadaDctoBean();

                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setIndicador(rs.getInt("indicador"));
                fachadaBean.setNitCC(rs.getDouble("nitCC"));
                fachadaBean.setNombreTercero(rs.getString("nombreTercero"));
                fachadaBean.setIdDcto(rs.getInt("idDcto"));
                fachadaBean.setVrTotal(rs.getInt("vrTotal"));
                fachadaBean.setVrIva(rs.getDouble("vrIva"));
                fachadaBean.setVrDescuento(rs.getInt("vrDescuento"));
                fachadaBean.setVrRteFuente(rs.getInt("vrRteFuente"));
                fachadaBean.setVrRteIva(rs.getInt("vrRteIva"));
                fachadaBean.setVrDsctoFcro(rs.getInt("vrDsctoFcro"));

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

    // Metodo listaDctosIdPeriodoIndicadorVencimientoXDias
    public Vector listaDctosIdPeriodoIndicadorVencimientoXDias() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        Vector contenedor = new Vector();
        Connection connection = null;

        String insertStr
                = "SELECT tmpDetalle.idLocal,                              "
                + "       tmpDetalle.idTipoOrden,                          "
                + "       tmpDetalle.idDcto,                               "
                + "       tmpDetalle.indicador,                            "
                + "       tmpDetalle.nitCC,                                "
                + "       tblTerceros.nombreTercero,                       "
                + "       " + getFechaCorteMsAccessStr() + " - MIN(tmpDetalle.fechaDcto)  AS edad,"
                + "       MIN(tmpDetalle.fechaDcto) AS fechaDcto,          "
                + "       SUM(tmpDetalle.vrTotal *                         "
                + "           tmpDetalle.signo) AS vrTotal,                "
                + "       SUM(tmpDetalle.vrIva) AS vrIva,                  "
                + "       SUM(tmpDetalle.vrDescuento) AS vrDescuento,      "
                + "       SUM(tmpDetalle.vrRteFuente) AS vrRteFuente,      "
                + "       SUM(tmpDetalle.vrRteIva) AS vrRteIva,            "
                + "       SUM(tmpDetalle.vrDsctoFcro) AS vrDsctoFcro       "
                + "FROM tblTerceros,                                       "
                + "     ( SELECT tblDctos.idLocal,                         "
                + "              tblDctos.idTipoOrden,                     "
                + "              tblDctos.idDcto,                          "
                + "              tblDctos.indicador,                       "
                + "              tblDctos.nitCC,                           "
                + "              +1 AS signo,                              "
                + "              MIN(tblDctos.fechaDcto) AS fechaDcto,     "
                + "              SUM(tblDctos.vrBase +                     "
                + "                  tblDctos.vrIva) AS vrTotal,           "
                + "              SUM(tblDctos.vrIva) AS vrIva,             "
                + "              SUM(tblDctos.vrDescuento) AS vrDescuento, "
                + "              SUM(tblDctos.vrRteFuente) AS vrRteFuente, "
                + "              SUM(tblDctos.vrRteIva) AS vrRteIva,       "
                + "              SUM(tblDctos.vrDsctoFcro) AS vrDsctoFcro  "
                + "       FROM tblDctos                                    "
                + "       WHERE tblDctos.idLocal    IN ( " + getStrIdLocal() + " ) "
                + "       AND tblDctos.idTipoOrden  IN ( " + getIdTipoOrdenStr() + " ) "
                + "       AND tblDctos.indicador    IN ( " + getIndicadorSesion() + " ) "
                + "       AND tblDctos.estado   NOT IN ( " + getEstadoStr() + " ) "
                + "       AND tblDctos.idDctoCruce  = 0                                 "
                + "       GROUP BY tblDctos.idLocal,                       "
                + "                tblDctos.idTipoOrden,                   "
                + "                tblDctos.idDcto,                        "
                + "                tblDctos.indicador,                     "
                + "                tblDctos.nitCC UNION                    "
                + "       SELECT tblDctos.idLocal,                         "
                + "              tblDctos.idTipoOrden,                     "
                + "              tblDctos.idDctoCruce AS idDcto,           "
                + "              tblDctos.indicador,                       "
                + "              tblDctos.nitCC,                           "
                + "              +1 AS signo,                              "
                + "              MIN(tblDctos.fechaDcto) AS fechaDcto,     "
                + "              SUM(tblDctos.vrBase +                     "
                + "                  tblDctos.vrIva) AS vrTotal,           "
                + "              SUM(tblDctos.vrIva) AS vrIva,             "
                + "              SUM(tblDctos.vrDescuento) AS vrDescuento, "
                + "              SUM(tblDctos.vrRteFuente) AS vrRteFuente, "
                + "              SUM(tblDctos.vrRteIva) AS vrRteIva,       "
                + "              SUM(tblDctos.vrDsctoFcro) AS vrDsctoFcro  "
                + "       FROM tblDctos                                    "
                + "       WHERE tblDctos.idLocal    IN ( " + getStrIdLocal() + " ) "
                + "       AND tblDctos.idTipoOrden  IN ( " + getIdTipoOrdenStr() + " ) "
                + "       AND tblDctos.indicador    IN ( " + getIndicadorSesion() + " ) "
                + "       AND tblDctos.estado   NOT IN ( " + getEstadoStr() + " ) "
                + "       AND tblDctos.idDctoCruce  > 0                                 "
                + "       GROUP BY tblDctos.idLocal,                       "
                + "                tblDctos.idTipoOrden,                   "
                + "                tblDctos.idDctoCruce,                   "
                + "                tblDctos.indicador,                     "
                + "                tblDctos.nitCC UNION                    "
                + "       SELECT tblPagos.idLocal,                         "
                + "              tblPagos.idTipoOrden,                     "
                + "              tblPagos.idDcto,                          "
                + "              tblPagos.indicador,                       "
                + "              tblPagos.nitCC,                           "
                + "              -1 AS signo,                              "
                + "              MIN(tblPagos.fechaPago) AS fechaDcto,     "
                + "              SUM(tblPagos.vrPago                       "
                + "                  + tblPagos.vrDescuento                "
                + "                  + tblPagos.vrRteFuente                "
                + "                  + tblPagos.vrRteIva) AS vrTotal,      "
                + "              SUM( 0 ) AS vrIva,                        "
                + "              SUM(tblPagos.vrDescuento) AS vrDescuento, "
                + "              SUM(tblPagos.vrRteFuente) AS vrRteFuente, "
                + "              SUM(tblPagos.vrRteIva) AS vrRteIva,       "
                + "              SUM( 0 ) AS vrDsctoFcro                   "
                + "       FROM tblPagos                                    "
                + "       WHERE tblPagos.idLocal    IN ( " + getStrIdLocal() + " ) "
                + "       AND tblPagos.idTipoOrden  IN ( " + getIdTipoOrdenStr() + " ) "
                + "       AND tblPagos.indicador    IN ( " + getIndicadorSesion() + " ) "
                + "       GROUP BY  tblPagos.idLocal,                      "
                + "                 tblPagos.idTipoOrden,                  "
                + "                 tblPagos.idDcto,                       "
                + "                 tblPagos.indicador,                    "
                + "                 tblPagos.nitCC) AS tmpDetalle          "
                + "WHERE tmpDetalle.nitCC =                                "
                + "               tblTerceros.idTercero                    "
                + "GROUP BY tmpDetalle.idLocal,                            "
                + "         tmpDetalle.idTipoOrden,                        "
                + "         tmpDetalle.idDcto,                             "
                + "         tmpDetalle.indicador,                          "
                + "         tmpDetalle.nitCC,                              "
                + "         tblTerceros.nombreTercero                      "
                + "HAVING  ( SUM(tmpDetalle.vrTotal * tmpDetalle.signo) -  "
                + "          SUM(tmpDetalle.vrDsctoFcro) ) > 0             "
                + "AND (" + getFechaCorteMsAccessStr() + " - MIN(tmpDetalle.fechaDcto)) >= " + getDiasVencimiento() + " "
                + "ORDER BY tmpDetalle.idLocal,                            "
                + "         tmpDetalle.idTipoOrden,                        "
                + "         tblTerceros.nombreTercero                      ";

        PreparedStatement sentenciaSQL = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            sentenciaSQL = connection.prepareStatement(insertStr);

            // Obtiene el resultset
            ResultSet rs = sentenciaSQL.executeQuery();

            // Variable de fachada de los datos
            FachadaDctoBean fachadaBean;

            while (rs.next()) {

                fachadaBean = new FachadaDctoBean();
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setIdDcto(rs.getInt("idDcto"));
                fachadaBean.setIndicador(rs.getInt("indicador"));
                fachadaBean.setNitCC(rs.getDouble("nitCC"));
                fachadaBean.setNombreTercero(rs.getString("nombreTercero"));
                fachadaBean.setFechaDcto(rs.getString("fechaDcto"));
                fachadaBean.setVrTotal(rs.getDouble("vrTotal"));
                fachadaBean.setVrIva(rs.getDouble("vrIva"));
                fachadaBean.setVrDescuento(rs.getInt("vrDescuento"));
                fachadaBean.setVrRteFuente(rs.getInt("vrRteFuente"));
                fachadaBean.setVrRteIva(rs.getInt("vrRteIva"));
                fachadaBean.setVrDsctoFcro(rs.getInt("vrDsctoFcro"));
                fachadaBean.setEdad(rs.getInt("edad"));

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

    // Metodo listaCxCVencimientoXVendedor
    public Vector listaCxCVencimientoXVendedor() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        Vector contenedor = new Vector();
        Connection connection = null;

        String insertStr
                = "SELECT tmpDetalle.idLocal,                              "
                + "       tmpDetalle.idTipoOrden,                          "
                + "       tmpDetalle.idDcto,                               "
                + "       tmpDetalle.indicador,                            "
                + "       tmpDetalle.nitCC,                                "
                + "       tblTerceros.nombreTercero,                       "
                + "       " + getFechaCorteMsAccessStr() + " - "
                + "                     MIN(tmpDetalle.fechaDcto)  AS edad,"
                + "       MIN(tmpDetalle.fechaDcto) AS fechaDcto,          "
                + "       SUM(tmpDetalle.vrTotal *                         "
                + "           tmpDetalle.signo) AS vrTotal,                "
                + "       SUM(tmpDetalle.vrIva) AS vrIva,                  "
                + "       SUM(tmpDetalle.vrDescuento) AS vrDescuento,      "
                + "       SUM(tmpDetalle.vrRteFuente) AS vrRteFuente,      "
                + "       SUM(tmpDetalle.vrRteIva) AS vrRteIva,            "
                + "       SUM(tmpDetalle.vrDsctoFcro) AS vrDsctoFcro       "
                + "FROM tblTerceros,                                       "
                + "     ( SELECT tblDctos.idLocal,                         "
                + "              tblDctos.idTipoOrden,                     "
                + "              tblDctos.idDcto,                          "
                + "              tblDctos.indicador,                       "
                + "              tblDctos.nitCC,                           "
                + "              +1 AS signo,                              "
                + "              MIN(tblDctos.fechaDcto) AS fechaDcto,     "
                + "              SUM(tblDctos.vrBase +                     "
                + "                  tblDctos.vrIva) AS vrTotal,           "
                + "              SUM(tblDctos.vrIva) AS vrIva,             "
                + "              SUM(tblDctos.vrDescuento) AS vrDescuento, "
                + "              SUM(tblDctos.vrRteFuente) AS vrRteFuente, "
                + "              SUM(tblDctos.vrRteIva) AS vrRteIva,       "
                + "              SUM(tblDctos.vrDsctoFcro) AS vrDsctoFcro  "
                + "       FROM tblDctos                                    "
                + "       WHERE tblDctos.idLocal    IN ( "
                + getStrIdLocal() + " ) "
                + "       AND tblDctos.idTipoOrden  IN ( "
                + getIdTipoOrdenStr() + " ) "
                + "       AND tblDctos.indicador    IN ( "
                + getIndicadorSesion() + " ) "
                + "       AND tblDctos.estado   NOT IN ( "
                + getEstadoStr() + " ) "
                + "       AND tblDctos.idDctoCruce  = 0                    "
                + "       AND tblDctos.idUsuario    IN ( "
                + getIdUsuarioStr() + " ) "
                + "       GROUP BY tblDctos.idLocal,                       "
                + "                tblDctos.idTipoOrden,                   "
                + "                tblDctos.idDcto,                        "
                + "                tblDctos.indicador,                     "
                + "                tblDctos.nitCC UNION                    "
                + "       SELECT tblDctos.idLocal,                         "
                + "              tblDctos.idTipoOrden,                     "
                + "              tblDctos.idDctoCruce AS idDcto,           "
                + "              tblDctos.indicador,                       "
                + "              tblDctos.nitCC,                           "
                + "              +1 AS signo,                              "
                + "              MIN(tblDctos.fechaDcto) AS fechaDcto,     "
                + "              SUM(tblDctos.vrBase +                     "
                + "                  tblDctos.vrIva) AS vrTotal,           "
                + "              SUM(tblDctos.vrIva) AS vrIva,             "
                + "              SUM(tblDctos.vrDescuento) AS vrDescuento, "
                + "              SUM(tblDctos.vrRteFuente) AS vrRteFuente, "
                + "              SUM(tblDctos.vrRteIva) AS vrRteIva,       "
                + "              SUM(tblDctos.vrDsctoFcro) AS vrDsctoFcro  "
                + "       FROM tblDctos                                    "
                + "       WHERE tblDctos.idLocal    IN ( " + getStrIdLocal() + " ) "
                + "       AND tblDctos.idTipoOrden  IN ( " + getIdTipoOrdenStr() + " ) "
                + "       AND tblDctos.indicador    IN ( " + getIndicadorSesion() + " ) "
                + "       AND tblDctos.estado   NOT IN ( " + getEstadoStr() + " ) "
                + "       AND tblDctos.idDctoCruce  > 0                                 "
                + "       AND tblDctos.idUsuario    IN ( " + getIdUsuarioStr() + " ) "
                + "       GROUP BY tblDctos.idLocal,                       "
                + "                tblDctos.idTipoOrden,                   "
                + "                tblDctos.idDctoCruce,                   "
                + "                tblDctos.indicador,                     "
                + "                tblDctos.nitCC UNION                    "
                + "       SELECT tblPagos.idLocal,                         "
                + "              tblPagos.idTipoOrden,                     "
                + "              tblPagos.idDcto,                          "
                + "              tblPagos.indicador,                       "
                + "              tblPagos.nitCC,                           "
                + "              -1 AS signo,                              "
                + "              MIN(tblPagos.fechaPago) AS fechaDcto,     "
                + "              SUM(tblPagos.vrPago                       "
                + "                  + tblPagos.vrDescuento                "
                + "                  + tblPagos.vrRteFuente                "
                + "                  + tblPagos.vrRteIva) AS vrTotal,      "
                + "              SUM( 0 ) AS vrIva,                        "
                + "              SUM(tblPagos.vrDescuento) AS vrDescuento, "
                + "              SUM(tblPagos.vrRteFuente) AS vrRteFuente, "
                + "              SUM(tblPagos.vrRteIva) AS vrRteIva,       "
                + "              SUM( 0 ) AS vrDsctoFcro                   "
                + "       FROM tblPagos                                    "
                + "       WHERE tblPagos.idLocal    IN ( " + getStrIdLocal() + " ) "
                + "       AND tblPagos.idTipoOrden  IN ( " + getIdTipoOrdenStr() + " ) "
                + "       AND tblPagos.indicador    IN ( " + getIndicadorSesion() + " ) "
                + "       GROUP BY  tblPagos.idLocal,                      "
                + "                 tblPagos.idTipoOrden,                  "
                + "                 tblPagos.idDcto,                       "
                + "                 tblPagos.indicador,                    "
                + "                 tblPagos.nitCC) AS tmpDetalle          "
                + "WHERE tmpDetalle.nitCC =                                "
                + "               tblTerceros.idTercero                    "
                + "GROUP BY tmpDetalle.idLocal,                            "
                + "         tmpDetalle.idTipoOrden,                        "
                + "         tmpDetalle.idDcto,                             "
                + "         tmpDetalle.indicador,                          "
                + "         tmpDetalle.nitCC,                              "
                + "         tblTerceros.nombreTercero                      "
                + "HAVING  ( SUM(tmpDetalle.vrTotal * tmpDetalle.signo) -  "
                + "          SUM(tmpDetalle.vrDsctoFcro) ) > 0             "
                + "AND (" + getFechaCorteMsAccessStr() + " - MIN(tmpDetalle.fechaDcto)) >= " + getDiasVencimiento() + " "
                + "ORDER BY tmpDetalle.idLocal,                            "
                + "         tmpDetalle.idTipoOrden,                        "
                + "         tblTerceros.nombreTercero                      ";

        PreparedStatement sentenciaSQL = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            sentenciaSQL = connection.prepareStatement(insertStr);

            // Obtiene el resultset
            ResultSet rs = sentenciaSQL.executeQuery();

            // Variable de fachada de los datos
            FachadaDctoBean fachadaBean;

            while (rs.next()) {

                fachadaBean = new FachadaDctoBean();
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setIdDcto(rs.getInt("idDcto"));
                fachadaBean.setIndicador(rs.getInt("indicador"));
                fachadaBean.setNitCC(rs.getDouble("nitCC"));
                fachadaBean.setNombreTercero(rs.getString("nombreTercero"));
                fachadaBean.setFechaDcto(rs.getString("fechaDcto"));
                fachadaBean.setVrTotal(rs.getDouble("vrTotal"));
                fachadaBean.setVrIva(rs.getDouble("vrIva"));
                fachadaBean.setVrDescuento(rs.getInt("vrDescuento"));
                fachadaBean.setVrRteFuente(rs.getInt("vrRteFuente"));
                fachadaBean.setVrRteIva(rs.getInt("vrRteIva"));
                fachadaBean.setVrDsctoFcro(rs.getInt("vrDsctoFcro"));
                fachadaBean.setEdad(rs.getInt("edad"));

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

    // Metodo listaCxCxDiasConsolidado
    public Vector listaCxCxDiasConsolidado() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        Vector contenedor = new Vector();
        Connection connection = null;

        String insertStr
                = "SELECT  tmpConsolidado.idLocal,                         "
                + "        tmpConsolidado.idTipoOrden,                     "
                + "        tmpConsolidado.indicador,                       "
                + "        tmpConsolidado.nitCC,                           "
                + "        tmpConsolidado.nombreTercero,                   "
                + "        COUNT(tmpConsolidado.idDcto) AS idDcto,         "
                + "        SUM(tmpConsolidado.vrTotal) AS vrTotal,         "
                + "        SUM(tmpConsolidado.vrIva) AS vrIva,             "
                + "        SUM(tmpConsolidado.vrDescuento) AS vrDescuento, "
                + "        SUM(tmpConsolidado.vrRteFuente) AS vrRteFuente, "
                + "        SUM(tmpConsolidado.vrRteIva) AS vrRteIva,       "
                + "        SUM(tmpConsolidado.vrDsctoFcro) AS vrDsctoFcro  "
                + "FROM                                                    "
                + "(SELECT tmpDetalle.idLocal,                             "
                + "       tmpDetalle.idTipoOrden,                          "
                + "       tmpDetalle.idDcto,                               "
                + "       tmpDetalle.indicador,                            "
                + "       tmpDetalle.nitCC,                                "
                + "       tblTerceros.nombreTercero,                       "
                + "       MIN(tmpDetalle.fechaDcto) AS fechaDcto,          "
                + "       SUM(tmpDetalle.vrTotal *                         "
                + "           tmpDetalle.signo) AS vrTotal,                "
                + "       SUM(tmpDetalle.vrIva) AS vrIva,                  "
                + "       SUM(tmpDetalle.vrDescuento) AS vrDescuento,      "
                + "       SUM(tmpDetalle.vrRteFuente) AS vrRteFuente,      "
                + "       SUM(tmpDetalle.vrRteIva) AS vrRteIva,            "
                + "       SUM(tmpDetalle.vrDsctoFcro) AS vrDsctoFcro       "
                + "FROM tblTerceros,                                       "
                + "     ( SELECT tblDctos.idLocal,                         "
                + "              tblDctos.idTipoOrden,                     "
                + "              tblDctos.idDcto,                          "
                + "              tblDctos.indicador,                       "
                + "              tblDctos.nitCC,                           "
                + "              +1 AS signo,                              "
                + "              MIN(tblDctos.fechaDcto) AS fechaDcto,     "
                + "              SUM(tblDctos.vrBase +                     "
                + "                  tblDctos.vrIva) AS vrTotal,           "
                + "              SUM(tblDctos.vrIva) AS vrIva,             "
                + "              SUM(tblDctos.vrDescuento) AS vrDescuento, "
                + "              SUM(tblDctos.vrRteFuente) AS vrRteFuente, "
                + "              SUM(tblDctos.vrRteIva) AS vrRteIva,       "
                + "              SUM(tblDctos.vrDsctoFcro) AS vrDsctoFcro  "
                + "       FROM tblDctos                                    "
                + "       WHERE tblDctos.idLocal    IN ( " + getStrIdLocal() + " ) "
                + "       AND tblDctos.idTipoOrden  IN ( " + getIdTipoOrdenStr() + " ) "
                + "       AND tblDctos.indicador    IN ( " + getIndicadorSesion() + " ) "
                + "       AND tblDctos.estado   NOT IN ( " + getEstadoStr() + " ) "
                + "       AND tblDctos.idDctoCruce  = 0                                 "
                + "       GROUP BY tblDctos.idLocal,                       "
                + "                tblDctos.idTipoOrden,                   "
                + "                tblDctos.idDcto,                        "
                + "                tblDctos.indicador,                     "
                + "                tblDctos.nitCC UNION                    "
                + "       SELECT tblDctos.idLocal,                         "
                + "              tblDctos.idTipoOrden,                     "
                + "              tblDctos.idDctoCruce AS idDcto,           "
                + "              tblDctos.indicador,                       "
                + "              tblDctos.nitCC,                           "
                + "              +1 AS signo,                              "
                + "              MIN(tblDctos.fechaDcto) AS fechaDcto,     "
                + "              SUM(tblDctos.vrBase +                     "
                + "                  tblDctos.vrIva) AS vrTotal,           "
                + "              SUM(tblDctos.vrIva) AS vrIva,             "
                + "              SUM(tblDctos.vrDescuento) AS vrDescuento, "
                + "              SUM(tblDctos.vrRteFuente) AS vrRteFuente, "
                + "              SUM(tblDctos.vrRteIva) AS vrRteIva,       "
                + "              SUM(tblDctos.vrDsctoFcro) AS vrDsctoFcro  "
                + "       FROM tblDctos                                    "
                + "       WHERE tblDctos.idLocal    IN ( " + getStrIdLocal() + " ) "
                + "       AND tblDctos.idTipoOrden  IN ( " + getIdTipoOrdenStr() + " ) "
                + "       AND tblDctos.indicador    IN ( " + getIndicadorSesion() + " ) "
                + "       AND tblDctos.estado   NOT IN ( " + getEstadoStr() + " ) "
                + "       AND tblDctos.idDctoCruce  > 0                                 "
                + "       GROUP BY tblDctos.idLocal,                       "
                + "                tblDctos.idTipoOrden,                   "
                + "                tblDctos.idDctoCruce,                   "
                + "                tblDctos.indicador,                     "
                + "                tblDctos.nitCC UNION                    "
                + "       SELECT tblPagos.idLocal,                         "
                + "              tblPagos.idTipoOrden,                     "
                + "              tblPagos.idDcto,                          "
                + "              tblPagos.indicador,                       "
                + "              tblPagos.nitCC,                           "
                + "              -1 AS signo,                              "
                + "              MIN(tblPagos.fechaPago) AS fechaDcto,     "
                + "              SUM(tblPagos.vrPago                       "
                + "                  + tblPagos.vrDescuento                "
                + "                  + tblPagos.vrRteFuente                "
                + "                  + tblPagos.vrRteIva) AS vrTotal,      "
                + "              SUM( 0 ) AS vrIva,                        "
                + "              SUM(tblPagos.vrDescuento) AS vrDescuento, "
                + "              SUM(tblPagos.vrRteFuente) AS vrRteFuente, "
                + "              SUM(tblPagos.vrRteIva) AS vrRteIva,       "
                + "              SUM( 0 ) AS vrDsctoFcro                   "
                + "       FROM tblPagos                                    "
                + "       WHERE tblPagos.idLocal    IN ( " + getStrIdLocal() + " ) "
                + "       AND tblPagos.idTipoOrden  IN ( " + getIdTipoOrdenStr() + " ) "
                + "       AND tblPagos.indicador    IN ( " + getIndicadorSesion() + " ) "
                + "       GROUP BY  tblPagos.idLocal,                      "
                + "                 tblPagos.idTipoOrden,                  "
                + "                 tblPagos.idDcto,                       "
                + "                 tblPagos.indicador,                    "
                + "                 tblPagos.nitCC) AS tmpDetalle          "
                + "WHERE tmpDetalle.nitCC =                                "
                + "               tblTerceros.idTercero                    "
                + "GROUP BY tmpDetalle.idLocal,                            "
                + "         tmpDetalle.idTipoOrden,                        "
                + "         tmpDetalle.idDcto,                             "
                + "         tmpDetalle.indicador,                          "
                + "         tmpDetalle.nitCC,                              "
                + "         tblTerceros.nombreTercero                      "
                + "HAVING  ( SUM(tmpDetalle.vrTotal * tmpDetalle.signo) -  "
                + "          SUM(tmpDetalle.vrDsctoFcro) ) > 0             "
                + "AND (" + getFechaCorteMsAccessStr() + " - MIN(tmpDetalle.fechaDcto)) >= " + getDiasVencimiento() + " "
                + "                                  ) AS tmpConsolidado   "
                + "GROUP BY tmpConsolidado.idLocal,                        "
                + "         tmpConsolidado.idTipoOrden,                    "
                + "         tmpConsolidado.indicador,                      "
                + "         tmpConsolidado.nitCC,                          "
                + "         tmpConsolidado.nombreTercero                   "
                + "ORDER BY tmpConsolidado.idLocal,                        "
                + "         tmpConsolidado.idTipoOrden,                    "
                + "         tmpConsolidado.nombreTercero                   ";

        PreparedStatement sentenciaSQL = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            sentenciaSQL = connection.prepareStatement(insertStr);

            // Obtiene el resultset
            ResultSet rs = sentenciaSQL.executeQuery();

            // Variable de fachada de los datos
            FachadaDctoBean fachadaBean;

            while (rs.next()) {

                fachadaBean = new FachadaDctoBean();

                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setIndicador(rs.getInt("indicador"));
                fachadaBean.setNitCC(rs.getDouble("nitCC"));
                fachadaBean.setNombreTercero(rs.getString("nombreTercero"));
                fachadaBean.setIdDcto(rs.getInt("idDcto"));
                fachadaBean.setVrTotal(rs.getDouble("vrTotal"));
                fachadaBean.setVrIva(rs.getDouble("vrIva"));
                fachadaBean.setVrDescuento(rs.getInt("vrDescuento"));
                fachadaBean.setVrRteFuente(rs.getInt("vrRteFuente"));
                fachadaBean.setVrRteIva(rs.getInt("vrRteIva"));
                fachadaBean.setVrDsctoFcro(rs.getInt("vrDsctoFcro"));

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

    // listaCxCTotal
    public Vector listaCxCTotal() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        Vector contenedor = new Vector();
        Connection connection = null;

        String insertStr
                = "SELECT  tmpTotal.IDLOCAL,                     "
                + "        'Vencida' AS tipoCartera,             "
                + "        SUM(tmpTotal.vrSaldo) AS vrSaldo,     "
                + "        COUNT(*) AS numeroDctos,              "
                + "        MAX(tmpTotal.IDTIPOORDEN)             "
                + "                               AS IDTIPOORDEN "
                + "FROM (                                        "
                + "SELECT tmpSaldo.idLocal,                      "
                + "       tmpSaldo.IDTIPOORDEN,                  "
                + "       tmpSaldo.idDcto,                       "
                + "       tmpSaldo.fechaDcto,                    "
                + "       tmpSaldo.fechaVcto,                    "
                + "       tmpSaldo.diasVcto AS diasMora,         "
                + "       tmpSaldo.vrSaldo,                      "
                + "       tmpSaldo.indicador,                    "
                + "       MAX(tmpSaldo.idDctoNitCC)              "
                + "                             AS idDctoNitCC,  "
                + "       tmpSaldo.idOrden                       "
                + "FROM                                          "
                + "(SELECT tmpCXC.IDLOCAL,                       "
                + "        tmpCXC.IDTIPOORDEN,                   "
                + "        tmpCXC.idDcto,                        "
                + "        tmpCXC.idOrden,                       "
                + "       tmpCXC.indicador,                      "
                + "       MAX(tmpCXC.idDctoNitCC)                "
                + "                             AS idDctoNitCC,  "
                + "       MAX(tmpCXC.diasPlazo) AS diasPlazo,    "
                + "       MIN(tmpCXC.fechaDcto) AS fechaDcto,    "
                + "       MIN(tmpCXC.fechaDcto) +                "
                + "       MAX(tmpCXC.diasPlazo) AS fechaVcto,    "
                + "       DATEDIFF(DAY,  MIN(tmpCXC.fechaDcto) + "
                + "             MAX(tmpCXC.diasPlazo),GETDATE()) "
                + "                             AS diasVcto,     "
                + "       SUM(tmpCXC.vrBase) AS vrBase,          "
                + "       SUM(tmpCXC.vrPago) AS vrPago,          "
                + "       SUM(tmpCXC.vrIva) AS vrIva,            "
                + "       SUM(tmpCXC.vrRteFuente) AS vrRteFuente,"
                + "       SUM(tmpCXC.vrDsctoFcro) AS vrDsctoFcro,"
                + "       SUM(tmpCXC.vrRteIva) AS vrRteIva,      "
                + "       SUM(tmpCXC.vrRteIca) AS vrRteIca,      "
                + "       ( SUM(tmpCXC.vrBase)               +   "
                + "                SUM(tmpCXC.vrIva)         +   "
                + "                SUM(tmpCXC.vrImpoconsumo) -   "
                + "                SUM(tmpCXC.vrPago)        -   "
                + "                SUM(tmpCXC.vrRteFuente)   -   "
                + "                SUM(tmpCXC.vrDsctoFcro)   -   "
                + "                SUM(tmpCXC.vrRteIva)      -   "
                + "         SUM(tmpCXC.vrRteIca) ) AS vrSaldo    "
                + "FROM                                          "
                + "(SELECT tblDctos.idLocalCruce                 "
                + "                      AS idLocal,             "
                + "       tblDctos.idTipoOrdenCruce              "
                + "                  AS idTipoOrden,             "
                + "       tblDctos.idDctoCruce                   "
                + "                      AS idDcto,              "
                + "       tblDctos.idOrdenCruce AS idOrden,      "
                + "       tblDctos.indicador,                    "
                + "       tblDctos.idCliente,                    "
                + "       tblDctos.vrBase,                       "
                + "       tblDctos.vrPago,                       "
                + "       tblDctos.vrIva,                        "
                + "       tblDctos.vrRteFuente,                  "
                + "       tblDctos.vrDsctoFcro,                  "
                + "       tblDctos.vrRteIva,                     "
                + "       tblDctos.vrRteIca,                     "
                + "       tblDctos.vrImpoconsumo,                "
                + "       tblDctos.fechaDcto,                    "
                + "       tblDctos.diasPlazo,                    "
                + "       tblDctos.idDctoNitCC                   "
                + "FROM   tblDctos                               "
                + "WHERE  tblDctos.idLocal           =           "
                + this.getIdLocal() + "                          "
                + "AND   tblDctos.idCliente          =          '"
                + this.getIdCliente() + "'                       "
                + "AND    tblDctos.idTipoOrdenCruce  =           "
                + this.getIdTipoOrden() + "                      "
                + "AND    tblDctos.idDctoCruce      != 0         "
                + "AND    EXISTS (                               "
                + "       SELECT *                               "
                + "       FROM tblDctosOrdenesDetalle            "
                + "       WHERE tblDctosOrdenesDetalle.idLocal = "
                + "                            tblDctos.idLocal  "
                + "       AND tblDctosOrdenesDetalle.idTipoOrden "
                + "                                            = "
                + "                       tblDctos.idTipoOrden   "
                + "      AND  tblDctosOrdenesDetalle.idOrden   = "
                + "                           tblDctos.idOrden ) "
                + "UNION                                         "
                + "SELECT tblDctos.idLocal,                      "
                + "       tblDctos.idTipoOrden,                  "
                + "       tblDctos.idDcto,                       "
                + "       tblDctos.idOrden,                      "
                + "       tblDctos.indicador,                    "
                + "       tblDctos.idCliente,                    "
                + "       tblDctos.vrBase,                       "
                + "       tblDctos.vrPago,                       "
                + "       tblDctos.vrIva,                        "
                + "       tblDctos.vrRteFuente,                  "
                + "       tblDctos.vrDsctoFcro,                  "
                + "       tblDctos.vrRteIva,                     "
                + "       tblDctos.vrRteIca,                     "
                + "       tblDctos.vrImpoconsumo,                "
                + "       tblDctos.fechaDcto,                    "
                + "       tblDctos.diasPlazo,                    "
                + "       tblDctos.idDctoNitCC                   "
                + "FROM   tblDctos                               "
                + "WHERE  tblDctos.idLocal           =           "
                + this.getIdLocal() + "                          "
                + "AND   tblDctos.idCliente          =          '"
                + this.getIdCliente() + "'                       "
                + "AND    tblDctos.idTipoOrden       =           "
                + this.getIdTipoOrden() + "                      "
                + "AND    tblDctos.idDctoCruce       = 0         "
                + "AND    EXISTS (                               "
                + "       SELECT *                               "
                + "       FROM tblDctosOrdenesDetalle            "
                + "       WHERE tblDctosOrdenesDetalle.idLocal = "
                + "                            tblDctos.idLocal  "
                + "     AND tblDctosOrdenesDetalle.idTipoOrden = "
                + "                       tblDctos.idTipoOrden   "
                + "      AND  tblDctosOrdenesDetalle.idOrden   = "
                + "                           tblDctos.idOrden ) "
                + "UNION                                         "
                + "SELECT tblDctos.idLocal,                      "
                + "       tblDctos.idTipoOrden,                  "
                + "       tblDctos.idDctoCruce                   "
                + "                      AS idDcto,              "
                + "       tblDctos.idOrdenCruce AS idOrden,      "
                + "       tblDctos.indicador,                    "
                + "       tblDctos.idCliente,                    "
                + "       tblDctos.vrBase,                       "
                + "       tblDctos.vrPago,                       "
                + "       tblDctos.vrIva,                        "
                + "       tblDctos.vrRteFuente,                  "
                + "       tblDctos.vrDsctoFcro,                  "
                + "       tblDctos.vrRteIva,                     "
                + "       tblDctos.vrRteIca,                     "
                + "       tblDctos.vrImpoconsumo,                "
                + "       tblDctos.fechaDcto,                    "
                + "       tblDctos.diasPlazo,                    "
                + "       tblDctos.idDctoNitCC                   "
                + "FROM   tblDctos                               "
                + "WHERE  tblDctos.idLocal           =           "
                + this.getIdLocal() + "                          "
                + "AND   tblDctos.idCliente          =          '"
                + this.getIdCliente() + "'                       "
                + "AND    tblDctos.idTipoOrden       =           "
                + this.getIdTipoOrden() + "                      "
                + "AND    tblDctos.idTipoOrdenCruce  = 0         "
                + "AND    tblDctos.idDctoCruce      != 0         "
                + "AND    EXISTS (                               "
                + "       SELECT *                               "
                + "       FROM tblDctosOrdenesDetalle            "
                + "       WHERE tblDctosOrdenesDetalle.idLocal = "
                + "                            tblDctos.idLocal  "
                + "       AND tblDctosOrdenesDetalle.idTipoOrden "
                + "                                            = "
                + "                       tblDctos.idTipoOrden   "
                + "      AND  tblDctosOrdenesDetalle.idOrden   = "
                + "                           tblDctos.idOrden ) "
                + "                          )  AS tmpCXC        "
                + "GROUP BY tmpCXC.IDLOCAL,                      "
                + "         tmpCXC.IDTIPOORDEN,                  "
                + "         tmpCXC.idDcto,                       "
                + "         tmpCXC.idOrden,                      "
                + "         tmpCXC.indicador                     "
                + "HAVING ( SUM(tmpCXC.vrBase)        +          "
                + "         SUM(tmpCXC.vrIva)         +          "
                + "         SUM(tmpCXC.vrImpoconsumo) -          "
                + "         SUM(tmpCXC.vrPago)        -          "
                + "         SUM(tmpCXC.vrRteFuente)   -          "
                + "         SUM(tmpCXC.vrDsctoFcro)   -          "
                + "         SUM(tmpCXC.vrRteIva)      -          "
                + "         SUM(tmpCXC.vrRteIca) ) > 1 )         "
                + "                              AS tmpSaldo     "
                + "GROUP BY tmpSaldo.idLocal,                    "
                + "         tmpSaldo.IDTIPOORDEN,                "
                + "         tmpSaldo.idDcto,                     "
                + "         tmpSaldo.idOrden,                    "
                + "         tmpSaldo.fechaDcto,                  "
                + "         tmpSaldo.fechaVcto,                  "
                + "         tmpSaldo.diasVcto,                   "
                + "         tmpSaldo.vrSaldo,                    "
                + "         tmpSaldo.indicador )                 "
                + "                              AS tmpTotal     "
                + "GROUP BY tmpTotal.IDLOCAL                     ";

        PreparedStatement sentenciaSQL = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            sentenciaSQL = connection.prepareStatement(insertStr);
            // Obtiene el resultset
            ResultSet rs = sentenciaSQL.executeQuery();

            // Variable de fachada de los datos
            FachadaDctoBean fachadaBean;

            while (rs.next()) {

                //
                fachadaBean = new FachadaDctoBean();

                //
                fachadaBean.setTipoCartera(rs.getString("tipoCartera"));
                fachadaBean.setNumeroDctos(rs.getInt("numeroDctos"));
                fachadaBean.setVrSaldo(rs.getDouble("vrSaldo"));

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
            jdbcAccess.cleanup(connection, sentenciaSQL, null);
            return contenedor;
        }
    }

    // listaCxCTotalFCH
    public FachadaDctoBean listaCxCTotalFCH() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        Connection connection = null;

        // Variable de fachada de los datos
        FachadaDctoBean fachadaBean = new FachadaDctoBean();

        //
        String insertStr
                = "SELECT  tmpTotal.IDLOCAL,                     "
                + "        'Vencida' AS tipoCartera,             "
                + "        SUM(tmpTotal.vrSaldo) AS vrSaldo,     "
                + "        COUNT(*) AS numeroDctos,              "
                + "        MAX(tmpTotal.IDTIPOORDEN)             "
                + "                               AS IDTIPOORDEN "
                + "FROM (                                        "
                + "SELECT tmpSaldo.idLocal,                      "
                + "       tmpSaldo.IDTIPOORDEN,                  "
                + "       tmpSaldo.idDcto,                       "
                + "       tmpSaldo.fechaDcto,                    "
                + "       tmpSaldo.fechaVcto,                    "
                + "       tmpSaldo.diasVcto AS diasMora,         "
                + "       tmpSaldo.vrSaldo,                      "
                + "       tmpSaldo.indicador,                    "
                + "       MAX(tmpSaldo.idDctoNitCC)              "
                + "                             AS idDctoNitCC,  "
                + "       tmpSaldo.idOrden                       "
                + "FROM                                          "
                + "(SELECT tmpCXC.IDLOCAL,                       "
                + "        tmpCXC.IDTIPOORDEN,                   "
                + "        tmpCXC.idDcto,                        "
                + "        tmpCXC.idOrden,                       "
                + "       tmpCXC.indicador,                      "
                + "       MAX(tmpCXC.idDctoNitCC)                "
                + "                             AS idDctoNitCC,  "
                + "       MAX(tmpCXC.diasPlazo) AS diasPlazo,    "
                + "       MIN(tmpCXC.fechaDcto) AS fechaDcto,    "
                + "       MIN(tmpCXC.fechaDcto) +                "
                + "       MAX(tmpCXC.diasPlazo) AS fechaVcto,    "
                + "       DATEDIFF(DAY,  MIN(tmpCXC.fechaDcto) + "
                + "             MAX(tmpCXC.diasPlazo),GETDATE()) "
                + "                             AS diasVcto,     "
                + "       SUM(tmpCXC.vrBase) AS vrBase,          "
                + "       SUM(tmpCXC.vrPago) AS vrPago,          "
                + "       SUM(tmpCXC.vrIva) AS vrIva,            "
                + "       SUM(tmpCXC.vrRteFuente) AS vrRteFuente,"
                + "       SUM(tmpCXC.vrDsctoFcro) AS vrDsctoFcro,"
                + "       SUM(tmpCXC.vrRteIva) AS vrRteIva,      "
                + "       SUM(tmpCXC.vrRteIca) AS vrRteIca,      "
                + "       ( SUM(tmpCXC.vrBase)               +   "
                + "                SUM(tmpCXC.vrIva)         +   "
                + "                SUM(tmpCXC.vrImpoconsumo) -   "
                + "                SUM(tmpCXC.vrPago)        -   "
                + "                SUM(tmpCXC.vrRteFuente)   -   "
                + "                SUM(tmpCXC.vrDsctoFcro)   -   "
                + "                SUM(tmpCXC.vrRteIva)      -   "
                + "         SUM(tmpCXC.vrRteIca) ) AS vrSaldo    "
                + "FROM                                          "
                + "(SELECT tblDctos.idLocalCruce                 "
                + "                      AS idLocal,             "
                + "       tblDctos.idTipoOrdenCruce              "
                + "                  AS idTipoOrden,             "
                + "       tblDctos.idDctoCruce                   "
                + "                      AS idDcto,              "
                + "       tblDctos.idOrdenCruce AS idOrden,      "
                + "       tblDctos.indicador,                    "
                + "       tblDctos.idCliente,                    "
                + "       tblDctos.vrBase,                       "
                + "       tblDctos.vrPago,                       "
                + "       tblDctos.vrIva,                        "
                + "       tblDctos.vrRteFuente,                  "
                + "       tblDctos.vrDsctoFcro,                  "
                + "       tblDctos.vrRteIva,                     "
                + "       tblDctos.vrRteIca,                     "
                + "       tblDctos.vrImpoconsumo,                "
                + "       tblDctos.fechaDcto,                    "
                + "       tblDctos.diasPlazo,                    "
                + "       tblDctos.idDctoNitCC                   "
                + "FROM   tblDctos                               "
                + "WHERE  tblDctos.idLocal           =           "
                + this.getIdLocal() + "                          "
                + "AND   tblDctos.idCliente          =          '"
                + this.getIdCliente() + "'                       "
                + "AND    tblDctos.idTipoOrdenCruce  =           "
                + this.getIdTipoOrden() + "                      "
                + "AND    tblDctos.idDctoCruce      != 0         "
                + "AND    EXISTS (                               "
                + "       SELECT *                               "
                + "       FROM tblDctosOrdenesDetalle            "
                + "       WHERE tblDctosOrdenesDetalle.idLocal = "
                + "                            tblDctos.idLocal  "
                + "       AND tblDctosOrdenesDetalle.idTipoOrden "
                + "                                            = "
                + "                       tblDctos.idTipoOrden   "
                + "      AND  tblDctosOrdenesDetalle.idOrden   = "
                + "                           tblDctos.idOrden ) "
                + "UNION                                         "
                + "SELECT tblDctos.idLocal,                      "
                + "       tblDctos.idTipoOrden,                  "
                + "       tblDctos.idDcto,                       "
                + "       tblDctos.idOrden,                      "
                + "       tblDctos.indicador,                    "
                + "       tblDctos.idCliente,                    "
                + "       tblDctos.vrBase,                       "
                + "       tblDctos.vrPago,                       "
                + "       tblDctos.vrIva,                        "
                + "       tblDctos.vrRteFuente,                  "
                + "       tblDctos.vrDsctoFcro,                  "
                + "       tblDctos.vrRteIva,                     "
                + "       tblDctos.vrRteIca,                     "
                + "       tblDctos.vrImpoconsumo,                "
                + "       tblDctos.fechaDcto,                    "
                + "       tblDctos.diasPlazo,                    "
                + "       tblDctos.idDctoNitCC                   "
                + "FROM   tblDctos                               "
                + "WHERE  tblDctos.idLocal           =           "
                + this.getIdLocal() + "                          "
                + "AND   tblDctos.idCliente          =          '"
                + this.getIdCliente() + "'                       "
                + "AND    tblDctos.idTipoOrden       =           "
                + this.getIdTipoOrden() + "                      "
                + "AND    tblDctos.idDctoCruce       = 0         "
                + "AND    EXISTS (                               "
                + "       SELECT *                               "
                + "       FROM tblDctosOrdenesDetalle            "
                + "       WHERE tblDctosOrdenesDetalle.idLocal = "
                + "                            tblDctos.idLocal  "
                + "     AND tblDctosOrdenesDetalle.idTipoOrden = "
                + "                       tblDctos.idTipoOrden   "
                + "      AND  tblDctosOrdenesDetalle.idOrden   = "
                + "                           tblDctos.idOrden ) "
                + "UNION                                         "
                + "SELECT tblDctos.idLocal,                      "
                + "       tblDctos.idTipoOrden,                  "
                + "       tblDctos.idDctoCruce                   "
                + "                      AS idDcto,              "
                + "       tblDctos.idOrdenCruce AS idOrden,      "
                + "       tblDctos.indicador,                    "
                + "       tblDctos.idCliente,                    "
                + "       tblDctos.vrBase,                       "
                + "       tblDctos.vrPago,                       "
                + "       tblDctos.vrIva,                        "
                + "       tblDctos.vrRteFuente,                  "
                + "       tblDctos.vrDsctoFcro,                  "
                + "       tblDctos.vrRteIva,                     "
                + "       tblDctos.vrRteIca,                     "
                + "       tblDctos.vrImpoconsumo,                "
                + "       tblDctos.fechaDcto,                    "
                + "       tblDctos.diasPlazo,                    "
                + "       tblDctos.idDctoNitCC                   "
                + "FROM   tblDctos                               "
                + "WHERE  tblDctos.idLocal           =           "
                + this.getIdLocal() + "                          "
                + "AND   tblDctos.idCliente          =          '"
                + this.getIdCliente() + "'                       "
                + "AND    tblDctos.idTipoOrden       =           "
                + this.getIdTipoOrden() + "                      "
                + "AND    tblDctos.idTipoOrdenCruce  = 0         "
                + "AND    tblDctos.idDctoCruce      != 0         "
                + "AND    EXISTS (                               "
                + "       SELECT *                               "
                + "       FROM tblDctosOrdenesDetalle            "
                + "       WHERE tblDctosOrdenesDetalle.idLocal = "
                + "                            tblDctos.idLocal  "
                + "       AND tblDctosOrdenesDetalle.idTipoOrden "
                + "                                            = "
                + "                       tblDctos.idTipoOrden   "
                + "      AND  tblDctosOrdenesDetalle.idOrden   = "
                + "                           tblDctos.idOrden ) "
                + "                          )  AS tmpCXC        "
                + "GROUP BY tmpCXC.IDLOCAL,                      "
                + "         tmpCXC.IDTIPOORDEN,                  "
                + "         tmpCXC.idDcto,                       "
                + "         tmpCXC.idOrden,                      "
                + "         tmpCXC.indicador                     "
                + "HAVING ( SUM(tmpCXC.vrBase)        +          "
                + "         SUM(tmpCXC.vrIva)         +          "
                + "         SUM(tmpCXC.vrImpoconsumo) -          "
                + "         SUM(tmpCXC.vrPago)        -          "
                + "         SUM(tmpCXC.vrRteFuente)   -          "
                + "         SUM(tmpCXC.vrDsctoFcro)   -          "
                + "         SUM(tmpCXC.vrRteIva)      -          "
                + "         SUM(tmpCXC.vrRteIca) ) > 1 )         "
                + "                              AS tmpSaldo     "
                + "GROUP BY tmpSaldo.idLocal,                    "
                + "         tmpSaldo.IDTIPOORDEN,                "
                + "         tmpSaldo.idDcto,                     "
                + "         tmpSaldo.idOrden,                    "
                + "         tmpSaldo.fechaDcto,                  "
                + "         tmpSaldo.fechaVcto,                  "
                + "         tmpSaldo.diasVcto,                   "
                + "         tmpSaldo.vrSaldo,                    "
                + "         tmpSaldo.indicador )                 "
                + "                              AS tmpTotal     "
                + "GROUP BY tmpTotal.IDLOCAL                     ";

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
                fachadaBean.setTipoCartera(rs.getString("tipoCartera"));
                fachadaBean.setNumeroDctos(rs.getInt("numeroDctos"));
                fachadaBean.setVrSaldo(rs.getDouble("vrSaldo"));

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

            // Cierra la conexion a la base de datos limpiando la statement usada y la
            jdbcAccess.cleanup(connection, sentenciaSQL, null);
            return fachadaBean;
        }
    }

    // listaCxCTotalHistoriaFCH
    public FachadaDctoBean listaCxCTotalHistoriaFCH() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        Connection connection = null;

        // Variable de fachada de los datos
        FachadaDctoBean fachadaBean = new FachadaDctoBean();

        //
        String insertStr
                = "SELECT tblDctos.IDLOCAL,               "
                + "       COUNT(*) AS numeroDctos,        "
                + "       SUM(tblDctos.vrBase) AS vrBase, "
                + "       SUM(tblDctos.vrIva) AS vrIva,   "
                + "       SUM(tblDctos.vrImpoconsumo)     "
                + "                   AS vrImpoconsumo,   "
                + "       SUM(tblDctos.vrDescuento)       "
                + "                       AS vrDescuento  "
                + "FROM [BDCommerce].[dbo].[tblDctos]     "
                + "WHERE tblDctos.idLocal              =  "
                + getIdLocal() + "                        "
                + "AND   tblDctos.idTipoOrden          IN "
                + "  (" + getIdTipoOrdenCadena() + ")     "
                + "AND   tblDctos.idCliente            = '"
                + getIdCliente() + "'                     "
                + "AND   tblDctos.fechaDcto BETWEEN      '"
                + getFechaInicialSqlServer() + "'   AND  '"
                + getFechaFinalSqlServer() + "'           "
                + "AND   tblDctos.indicador BETWEEN       "
                + getIndicadorInicial() + "   AND         "
                + getIndicadorFinal() + "                 "
                + "AND   tblDctos.indicador BETWEEN       "
                + getIndicadorInicial() + "   AND         "
                + getIndicadorFinal() + "                 "
                + "GROUP BY tblDctos.IDLOCAL              ";

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
                fachadaBean.setNumeroDctos(rs.getInt("numeroDctos"));
                fachadaBean.setVrBase(rs.getDouble("vrBase"));
                fachadaBean.setVrIva(rs.getDouble("vrIva"));
                fachadaBean.setVrImpoconsumo(rs.getDouble("vrImpoconsumo"));
                fachadaBean.setVrDescuento(rs.getDouble("vrDescuento"));

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

            // Cierra la conexion a la base de datos limpiando la statement usada y la
            jdbcAccess.cleanup(connection, sentenciaSQL, null);
            return fachadaBean;
        }
    }

    public Vector listaCuentaDetalladoCliente() {
        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        String selectStr = "SELECT tmpSaldo.idLocal,                             "
                + "tmpSaldo.IDTIPOORDEN,                         "
                + "tmpSaldo.idDcto,                              "
                + "tmpSaldo.fechaDcto,                           "
                + "tmpSaldo.fechaVcto,                           "
                + "tmpSaldo.diasVcto AS diasMora,                "
                + "tmpSaldo.vrSaldo,                             "
                + "tmpSaldo.indicador,                           "
                + "MAX(tmpSaldo.idDctoNitCC)   AS idDctoNitCC,         "
                + "tmpSaldo.idOrden  ,"
                + "( SELECT TOP 1 tblFacturaElectronica.idDctoDian\n"
                + "                         FROM tblFacturaElectronica\n"
                + "                         WHERE tblFacturaElectronica.idDcto =\n"
                + "                         tmpSaldo.idDcto ) AS numeroFE                     "
                + "FROM  (SELECT tmpCXC.IDLOCAL,                               "
                + "tmpCXC.IDTIPOORDEN,                           "
                + "tmpCXC.idDcto,                                "
                + "tmpCXC.idOrden,                              "
                + "tmpCXC.indicador,                             "
                + "MAX(tmpCXC.idDctoNitCC)      AS idDctoNitCC,         "
                + "MAX(tmpCXC.diasPlazo) AS diasPlazo,           "
                + "MIN(tmpCXC.fechaDcto) AS fechaDcto,           "
                + "MIN(tmpCXC.fechaDcto) +                       "
                + "MAX(tmpCXC.diasPlazo) AS fechaVcto,           "
                + "DATEDIFF(DAY,  MIN(tmpCXC.fechaDcto) +    "
                + "MAX(tmpCXC.diasPlazo),GETDATE())    AS diasVcto,            "
                + "SUM(tmpCXC.vrBase) AS vrBase,                 "
                + "SUM(tmpCXC.vrPago) AS vrPago,                 "
                + "SUM(tmpCXC.vrIva) AS vrIva,                   "
                + "SUM(tmpCXC.vrRteFuente) AS vrRteFuente,       "
                + "SUM(tmpCXC.vrDsctoFcro) AS vrDsctoFcro,       "
                + "SUM(tmpCXC.vrRteIva) AS vrRteIva,             "
                + "SUM(tmpCXC.vrRteIca) AS vrRteIca,             "
                + "SUM(tmpCXC.vrRteCree) AS vrRteCree,           "
                + "( SUM(tmpCXC.vrBase)    +   SUM(tmpCXC.vrIva)     +   "
                + "SUM(tmpCXC.vrImpoconsumo) -  SUM(tmpCXC.vrPago)        -                   "
                + "SUM(tmpCXC.vrRteFuente)   -  SUM(tmpCXC.vrDsctoFcro)   -  "
                + "SUM(tmpCXC.vrRteIva)   -  SUM(tmpCXC.vrRteIca)   - "
                + "SUM(tmpCXC.vrRteCree) ) AS vrSaldo   "
                + "FROM      (SELECT tblDctos.idLocalCruce    AS idLocal,                    "
                + "tblDctos.idTipoOrdenCruce   AS idTipoOrden,                    "
                + "tblDctos.idDctoCruce    AS idDcto,                     "
                + "tblDctos.idOrdenCruce AS idOrden,             "
                + "tblDctos.indicador,                           "
                + "tblDctos.idCliente,                           "
                + "tblDctos.vrBase,                              "
                + "tblDctos.vrPago,                              "
                + "tblDctos.vrIva,                               "
                + "tblDctos.vrRteFuente,                         "
                + "tblDctos.vrDsctoFcro,                         "
                + "tblDctos.vrRteIva,                            "
                + "tblDctos.vrRteIca,                            "
                + "tblDctos.vrImpoconsumo,                       "
                + "tblDctos.fechaDcto,                           "
                + "tblDctos.diasPlazo,                           "
                + "tblDctos.idDctoNitCC,                         "
                + "tblDctos.vrRteCree                     "
                + "FROM   tblDctos                               "
                + "WHERE  tblDctos.idLocal           =           "
                + getIdLocal() + "                          "
                + "AND   tblDctos.idCliente          =          '"
                + getIdCliente() + "'                       "
                + "AND    tblDctos.idTipoOrdenCruce  =           "
                + getIdTipoOrden() + "                      "
                + "AND    tblDctos.idDctoCruce      != 0         "
                + "AND    EXISTS (                               "
                + "       SELECT *                               "
                + "       FROM tblDctosOrdenesDetalle            "
                + "       WHERE tblDctosOrdenesDetalle.idLocal = "
                + "                            tblDctos.idLocal  "
                + "       AND tblDctosOrdenesDetalle.idTipoOrden "
                + "                                            = "
                + "                       tblDctos.idTipoOrden   "
                + "      AND  tblDctosOrdenesDetalle.idOrden   = "
                + "                           tblDctos.idOrden ) "
                + "UNION                                         "
                + "SELECT tblDctos.idLocal,                      "
                + "       tblDctos.idTipoOrden,                  "
                + "       tblDctos.idDcto,                       "
                + "       tblDctos.idOrden,                      "
                + "       tblDctos.indicador,                    "
                + "       tblDctos.idCliente,                    "
                + "       tblDctos.vrBase,                       "
                + "       tblDctos.vrPago,                       "
                + "       tblDctos.vrIva,                        "
                + "       tblDctos.vrRteFuente,                  "
                + "       tblDctos.vrDsctoFcro,                  "
                + "       tblDctos.vrRteIva,                     "
                + "       tblDctos.vrRteIca,                     "
                + "       tblDctos.vrImpoconsumo,                "
                + "       tblDctos.fechaDcto,                    "
                + "       tblDctos.diasPlazo,                    "
                + "       tblDctos.idDctoNitCC,                  "
                + "       tblDctos.vrRteCree                     "
                + "FROM   tblDctos                               "
                + "WHERE  tblDctos.idLocal           =           "
                + getIdLocal() + "                          "
                + "AND   tblDctos.idCliente          =          '"
                + getIdCliente() + "'                       "
                + "AND    tblDctos.idTipoOrden       =           "
                + getIdTipoOrden() + "                      "
                + "AND    tblDctos.idDctoCruce       = 0         "
                + "AND    EXISTS (                               "
                + "       SELECT *                               "
                + "       FROM tblDctosOrdenesDetalle            "
                + "       WHERE tblDctosOrdenesDetalle.idLocal = "
                + "                            tblDctos.idLocal  "
                + "     AND tblDctosOrdenesDetalle.idTipoOrden = "
                + "                       tblDctos.idTipoOrden   "
                + "      AND  tblDctosOrdenesDetalle.idOrden   = "
                + "                           tblDctos.idOrden ) "
                + "UNION                                         "
                + " SELECT tblPagos.idLocal         "
                + "       ,tblPagos.idTipoOrden     "
                + "       ,tblPagos.idDcto          "
                + "       ,tblPagos.idOrden         "
                + "       ,tblPagos.indicador       "
                + "       ,tblPagos.nitCC           "
                + "                 AS idCliente    "
                + "       ,0 AS vrBase              "
                + "       ,0 AS vrPago              "
                + "       ,0 AS vrIva               "
                + "       ,tblPagos.vrRteFuente     "
                + "       ,tblPagos.vrDescuento     "
                + "                AS vrDsctoFcro   "
                + "       ,tblPagos.vrRteIva        "
                + "       ,tblPagos.vrRteIca        "
                + "       ,0 AS vrImpoconsumo       "
                + "       ,tblPagos.fechaPago       "
                + "       ,0 diasPlazo              "
                + "       ,tblPagos.idDctoNitCC     "
                + "       ,tblPagos.vrRteCree       "
                + " FROM tblPagos                   "
                + " WHERE tblPagos.idLocal    =     "
                + getIdLocal() + "             "
                + " AND   tblPagos.nitCC      =    '"
                + getIdCliente() + "'          "
                + " AND  tblPagos.idTipoOrden =     "
                + getIdTipoOrden() + "         "
                + "AND    EXISTS (                  "
                + "       SELECT *                               "
                + "       FROM tblDctosOrdenesDetalle            "
                + "       WHERE tblDctosOrdenesDetalle.idLocal = "
                + "                            tblPagos.idLocal  "
                + "     AND tblDctosOrdenesDetalle.idTipoOrden = "
                + "                       tblPagos.idTipoOrden   "
                + "      AND  tblDctosOrdenesDetalle.idOrden   = "
                + "                           tblPagos.idOrden ) "
                + "UNION                                         "
                + "SELECT tblDctos.idLocal,                      "
                + "       tblDctos.idTipoOrden,                  "
                + "       tblDctos.idDctoCruce                   "
                + "                      AS idDcto,              "
                + "       tblDctos.idOrdenCruce AS idOrden,      "
                + "       tblDctos.indicador,                    "
                + "       tblDctos.idCliente,                    "
                + "       tblDctos.vrBase,                       "
                + "       tblDctos.vrPago,                       "
                + "       tblDctos.vrIva,                        "
                + "       tblDctos.vrRteFuente,                  "
                + "       tblDctos.vrDsctoFcro,                  "
                + "       tblDctos.vrRteIva,                     "
                + "       tblDctos.vrRteIca,                     "
                + "       tblDctos.vrImpoconsumo,                "
                + "       tblDctos.fechaDcto,                    "
                + "       tblDctos.diasPlazo,                    "
                + "       tblDctos.idDctoNitCC,                  "
                + "       tblDctos.vrRteCree                     "
                + "FROM   tblDctos                               "
                + "WHERE  tblDctos.idLocal           =           "
                + getIdLocal() + "                          "
                + "AND   tblDctos.idCliente          =          '"
                + getIdCliente() + "'                       "
                + "AND    tblDctos.idTipoOrden       =           "
                + getIdTipoOrden() + "                      "
                + "AND    tblDctos.idTipoOrdenCruce  = 0         "
                + "AND    tblDctos.idDctoCruce      != 0         "
                + "AND    EXISTS (                               "
                + "       SELECT *                               "
                + "       FROM tblDctosOrdenesDetalle            "
                + "       WHERE tblDctosOrdenesDetalle.idLocal = "
                + "                            tblDctos.idLocal  "
                + "       AND tblDctosOrdenesDetalle.idTipoOrden "
                + "                                            = "
                + "                       tblDctos.idTipoOrden   "
                + "      AND  tblDctosOrdenesDetalle.idOrden   = "
                + "                           tblDctos.idOrden ) "
                + "                          )  AS tmpCXC        "
                + "GROUP BY tmpCXC.IDLOCAL,                      "
                + "         tmpCXC.IDTIPOORDEN,                  "
                + "         tmpCXC.idDcto,                       "
                + "         tmpCXC.idOrden,                      "
                + "         tmpCXC.indicador                     "
                + "HAVING ( SUM(tmpCXC.vrBase)        +          "
                + "         SUM(tmpCXC.vrIva)         +          "
                + "         SUM(tmpCXC.vrImpoconsumo) -          "
                + "         SUM(tmpCXC.vrPago)        -          "
                + "         SUM(tmpCXC.vrRteFuente)   -          "
                + "         SUM(tmpCXC.vrDsctoFcro)   -          "
                + "         SUM(tmpCXC.vrRteIva)      -          "
                + "         SUM(tmpCXC.vrRteIca)      -          "
                + "         SUM(tmpCXC.vrRteCree) ) > 1 )        "
                + "                              AS tmpSaldo     "
                + "GROUP BY tmpSaldo.idLocal,                    "
                + "         tmpSaldo.IDTIPOORDEN,                "
                + "         tmpSaldo.idDcto,                     "
                + "         tmpSaldo.idOrden,                    "
                + "         tmpSaldo.fechaDcto,                  "
                + "         tmpSaldo.fechaVcto,                  "
                + "         tmpSaldo.diasVcto,                   "
                + "         tmpSaldo.vrSaldo,                    "
                + "         tmpSaldo.indicador                   "
                + "ORDER BY tmpSaldo.fechaDcto                   ";

        PreparedStatement selectStatement = null;
        try {
            connection = this.jdbcAccess.getConnection();

            selectStatement = connection.prepareStatement(selectStr);

            ResultSet rs = selectStatement.executeQuery();
            while (rs.next()) {
                FachadaDctoBean fachadaBean = new FachadaDctoBean();

                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("IDTIPOORDEN"));
                fachadaBean.setIdDcto(rs.getInt("idDcto"));
                fachadaBean.setIdOrden(rs.getInt("idOrden"));
                fachadaBean.setFechaDcto(rs.getString("fechaDcto").substring(0, 11));

                fachadaBean.setFechaVcto(rs.getString("fechaVcto").substring(0, 11));

                fachadaBean.setDiasMora(rs.getInt("diasMora"));
                fachadaBean.setVrSaldo(rs.getInt("vrSaldo"));
                fachadaBean.setIndicador(rs.getInt("indicador"));
                fachadaBean.setIdDctoNitCC(rs.getString("idDctoNitCC"));
                fachadaBean.setEdad(rs.getInt("numeroFE"));

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

    // Metodo
    public Vector listaCuentaDetalladoClienteOld() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        //
        String selectStr
                = "SELECT tmpSaldo.idLocal,                      "
                + "       tmpSaldo.IDTIPOORDEN,                  "
                + "       tmpSaldo.idDcto,                       "
                + "       tmpSaldo.fechaDcto,                    "
                + "       tmpSaldo.fechaVcto,                    "
                + "       tmpSaldo.diasVcto AS diasMora,         "
                + "       tmpSaldo.vrSaldo,                      "
                + "       tmpSaldo.indicador,                    "
                + "       MAX(tmpSaldo.idDctoNitCC)              "
                + "                             AS idDctoNitCC,  "
                + "       tmpSaldo.idOrden                       "
                + "FROM                                          "
                + "(SELECT tmpCXC.IDLOCAL,                       "
                + "        tmpCXC.IDTIPOORDEN,                   "
                + "        tmpCXC.idDcto,                        "
                + "        tmpCXC.idOrden,                       "
                + "       tmpCXC.indicador,                      "
                + "       MAX(tmpCXC.idDctoNitCC)                "
                + "                             AS idDctoNitCC,  "
                + "       MAX(tmpCXC.diasPlazo) AS diasPlazo,    "
                + "       MIN(tmpCXC.fechaDcto) AS fechaDcto,    "
                + "       MIN(tmpCXC.fechaDcto) +                "
                + "       MAX(tmpCXC.diasPlazo) AS fechaVcto,    "
                + "       DATEDIFF(DAY,  MIN(tmpCXC.fechaDcto) + "
                + "             MAX(tmpCXC.diasPlazo),GETDATE()) "
                + "                             AS diasVcto,     "
                + "       SUM(tmpCXC.vrBase) AS vrBase,          "
                + "       SUM(tmpCXC.vrPago) AS vrPago,          "
                + "       SUM(tmpCXC.vrIva) AS vrIva,            "
                + "       SUM(tmpCXC.vrRteFuente) AS vrRteFuente,"
                + "       SUM(tmpCXC.vrDsctoFcro) AS vrDsctoFcro,"
                + "       SUM(tmpCXC.vrRteIva) AS vrRteIva,      "
                + "       SUM(tmpCXC.vrRteIca) AS vrRteIca,      "
                + "       ( SUM(tmpCXC.vrBase)               +   "
                + "                SUM(tmpCXC.vrIva)         +   "
                + "                SUM(tmpCXC.vrImpoconsumo) -   "
                + "                SUM(tmpCXC.vrPago)        -   "
                + "                SUM(tmpCXC.vrRteFuente)   -   "
                + "                SUM(tmpCXC.vrDsctoFcro)   -   "
                + "                SUM(tmpCXC.vrRteIva)      -   "
                + "         SUM(tmpCXC.vrRteIca) ) AS vrSaldo    "
                + "FROM                                          "
                + "(SELECT tblDctos.idLocalCruce                 "
                + "                      AS idLocal,             "
                + "       tblDctos.idTipoOrdenCruce              "
                + "                  AS idTipoOrden,             "
                + "       tblDctos.idDctoCruce                   "
                + "                      AS idDcto,              "
                + "       tblDctos.idOrdenCruce AS idOrden,      "
                + "       tblDctos.indicador,                    "
                + "       tblDctos.idCliente,                    "
                + "       tblDctos.vrBase,                       "
                + "       tblDctos.vrPago,                       "
                + "       tblDctos.vrIva,                        "
                + "       tblDctos.vrRteFuente,                  "
                + "       tblDctos.vrDsctoFcro,                  "
                + "       tblDctos.vrRteIva,                     "
                + "       tblDctos.vrRteIca,                     "
                + "       tblDctos.vrImpoconsumo,                "
                + "       tblDctos.fechaDcto,                    "
                + "       tblDctos.diasPlazo,                    "
                + "       tblDctos.idDctoNitCC                   "
                + "FROM   tblDctos                               "
                + "WHERE  tblDctos.idLocal           =           "
                + this.getIdLocal() + "                          "
                + "AND   tblDctos.idCliente          =          '"
                + this.getIdCliente() + "'                       "
                + "AND    tblDctos.idTipoOrdenCruce  =           "
                + this.getIdTipoOrden() + "                      "
                + "AND    tblDctos.idDctoCruce      != 0         "
                + "AND    EXISTS (                               "
                + "       SELECT *                               "
                + "       FROM tblDctosOrdenesDetalle            "
                + "       WHERE tblDctosOrdenesDetalle.idLocal = "
                + "                            tblDctos.idLocal  "
                + "       AND tblDctosOrdenesDetalle.idTipoOrden "
                + "                                            = "
                + "                       tblDctos.idTipoOrden   "
                + "      AND  tblDctosOrdenesDetalle.idOrden   = "
                + "                           tblDctos.idOrden ) "
                + "UNION                                         "
                + "SELECT tblDctos.idLocal,                      "
                + "       tblDctos.idTipoOrden,                  "
                + "       tblDctos.idDcto,                       "
                + "       tblDctos.idOrden,                      "
                + "       tblDctos.indicador,                    "
                + "       tblDctos.idCliente,                    "
                + "       tblDctos.vrBase,                       "
                + "       tblDctos.vrPago,                       "
                + "       tblDctos.vrIva,                        "
                + "       tblDctos.vrRteFuente,                  "
                + "       tblDctos.vrDsctoFcro,                  "
                + "       tblDctos.vrRteIva,                     "
                + "       tblDctos.vrRteIca,                     "
                + "       tblDctos.vrImpoconsumo,                "
                + "       tblDctos.fechaDcto,                    "
                + "       tblDctos.diasPlazo,                    "
                + "       tblDctos.idDctoNitCC                   "
                + "FROM   tblDctos                               "
                + "WHERE  tblDctos.idLocal           =           "
                + this.getIdLocal() + "                          "
                + "AND   tblDctos.idCliente          =          '"
                + this.getIdCliente() + "'                       "
                + "AND    tblDctos.idTipoOrden       =           "
                + this.getIdTipoOrden() + "                      "
                + "AND    tblDctos.idDctoCruce       = 0         "
                + "AND    EXISTS (                               "
                + "       SELECT *                               "
                + "       FROM tblDctosOrdenesDetalle            "
                + "       WHERE tblDctosOrdenesDetalle.idLocal = "
                + "                            tblDctos.idLocal  "
                + "     AND tblDctosOrdenesDetalle.idTipoOrden = "
                + "                       tblDctos.idTipoOrden   "
                + "      AND  tblDctosOrdenesDetalle.idOrden   = "
                + "                           tblDctos.idOrden ) "
                + "UNION                                         "
                + "SELECT tblDctos.idLocal,                      "
                + "       tblDctos.idTipoOrden,                  "
                + "       tblDctos.idDctoCruce                   "
                + "                      AS idDcto,              "
                + "       tblDctos.idOrdenCruce AS idOrden,      "
                + "       tblDctos.indicador,                    "
                + "       tblDctos.idCliente,                    "
                + "       tblDctos.vrBase,                       "
                + "       tblDctos.vrPago,                       "
                + "       tblDctos.vrIva,                        "
                + "       tblDctos.vrRteFuente,                  "
                + "       tblDctos.vrDsctoFcro,                  "
                + "       tblDctos.vrRteIva,                     "
                + "       tblDctos.vrRteIca,                     "
                + "       tblDctos.vrImpoconsumo,                "
                + "       tblDctos.fechaDcto,                    "
                + "       tblDctos.diasPlazo,                    "
                + "       tblDctos.idDctoNitCC                   "
                + "FROM   tblDctos                               "
                + "WHERE  tblDctos.idLocal           =           "
                + this.getIdLocal() + "                          "
                + "AND   tblDctos.idCliente          =          '"
                + this.getIdCliente() + "'                       "
                + "AND    tblDctos.idTipoOrden       =           "
                + this.getIdTipoOrden() + "                      "
                + "AND    tblDctos.idTipoOrdenCruce  = 0         "
                + "AND    tblDctos.idDctoCruce      != 0         "
                + "AND    EXISTS (                               "
                + "       SELECT *                               "
                + "       FROM tblDctosOrdenesDetalle            "
                + "       WHERE tblDctosOrdenesDetalle.idLocal = "
                + "                            tblDctos.idLocal  "
                + "       AND tblDctosOrdenesDetalle.idTipoOrden "
                + "                                            = "
                + "                       tblDctos.idTipoOrden   "
                + "      AND  tblDctosOrdenesDetalle.idOrden   = "
                + "                           tblDctos.idOrden ) "
                + "                          )  AS tmpCXC        "
                + "GROUP BY tmpCXC.IDLOCAL,                      "
                + "         tmpCXC.IDTIPOORDEN,                  "
                + "         tmpCXC.idDcto,                       "
                + "         tmpCXC.idOrden,                      "
                + "         tmpCXC.indicador                     "
                + "HAVING ( SUM(tmpCXC.vrBase)        +          "
                + "         SUM(tmpCXC.vrIva)         +          "
                + "         SUM(tmpCXC.vrImpoconsumo) -          "
                + "         SUM(tmpCXC.vrPago)        -          "
                + "         SUM(tmpCXC.vrRteFuente)   -          "
                + "         SUM(tmpCXC.vrDsctoFcro)   -          "
                + "         SUM(tmpCXC.vrRteIva)      -          "
                + "         SUM(tmpCXC.vrRteIca) ) > 1 )         "
                + "                              AS tmpSaldo     "
                + "GROUP BY tmpSaldo.idLocal,                    "
                + "         tmpSaldo.IDTIPOORDEN,                "
                + "         tmpSaldo.idDcto,                     "
                + "         tmpSaldo.idOrden,                    "
                + "         tmpSaldo.fechaDcto,                  "
                + "         tmpSaldo.fechaVcto,                  "
                + "         tmpSaldo.diasVcto,                   "
                + "         tmpSaldo.vrSaldo,                    "
                + "         tmpSaldo.indicador                   "
                + "ORDER BY tmpSaldo.fechaDcto                   ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaDctoBean fachadaBean;

            while (rs.next()) {

                //
                fachadaBean = new FachadaDctoBean();

                //
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("IDTIPOORDEN"));
                fachadaBean.setIdDcto(rs.getInt("idDcto"));
                fachadaBean.setIdOrden(rs.getInt("idOrden"));
                fachadaBean.setFechaDcto(
                        rs.getString("fechaDcto").substring(0, 11));
                fachadaBean.setFechaVcto(
                        rs.getString("fechaVcto").substring(0, 11));
                fachadaBean.setDiasMora(rs.getInt("diasMora"));
                fachadaBean.setVrSaldo(rs.getInt("vrSaldo"));
                fachadaBean.setIndicador(rs.getInt("indicador"));
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

    // excedePlazo
    public boolean excedePlazo(int xIdPlazoPago) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        boolean xPlazoOk = false;

        Connection connection = null;

        //
        String selectStr
                = "SELECT tmpSaldo.idLocal,                      "
                + "       tmpSaldo.IDTIPOORDEN,                  "
                + "       tmpSaldo.idDcto,                       "
                + "       tmpSaldo.fechaDcto,                    "
                + "       tmpSaldo.fechaVcto,                    "
                + "       tmpSaldo.diasVcto AS diasMora,         "
                + "       tmpSaldo.vrSaldo,                      "
                + "       tmpSaldo.indicador,                    "
                + "       MAX(tmpSaldo.idDctoNitCC)              "
                + "                             AS idDctoNitCC,  "
                + "       tmpSaldo.idOrden                       "
                + "FROM                                          "
                + "(SELECT tmpCXC.IDLOCAL,                       "
                + "        tmpCXC.IDTIPOORDEN,                   "
                + "        tmpCXC.idDcto,                        "
                + "        tmpCXC.idOrden,                       "
                + "       tmpCXC.indicador,                      "
                + "       MAX(tmpCXC.idDctoNitCC)                "
                + "                             AS idDctoNitCC,  "
                + "       MAX(tmpCXC.diasPlazo) AS diasPlazo,    "
                + "       MIN(tmpCXC.fechaDcto) AS fechaDcto,    "
                + "       MIN(tmpCXC.fechaDcto) +                "
                + "       MAX(tmpCXC.diasPlazo) AS fechaVcto,    "
                + "       DATEDIFF(DAY,  MIN(tmpCXC.fechaDcto) + "
                + "             MAX(tmpCXC.diasPlazo),GETDATE()) "
                + "                             AS diasVcto,     "
                + "       SUM(tmpCXC.vrBase) AS vrBase,          "
                + "       SUM(tmpCXC.vrPago) AS vrPago,          "
                + "       SUM(tmpCXC.vrIva) AS vrIva,            "
                + "       SUM(tmpCXC.vrRteFuente) AS vrRteFuente,"
                + "       SUM(tmpCXC.vrDsctoFcro) AS vrDsctoFcro,"
                + "       SUM(tmpCXC.vrRteIva) AS vrRteIva,      "
                + "       SUM(tmpCXC.vrRteIca) AS vrRteIca,      "
                + "       ( SUM(tmpCXC.vrBase)               +   "
                + "                SUM(tmpCXC.vrIva)         +   "
                + "                SUM(tmpCXC.vrImpoconsumo) -   "
                + "                SUM(tmpCXC.vrPago)        -   "
                + "                SUM(tmpCXC.vrRteFuente)   -   "
                + "                SUM(tmpCXC.vrDsctoFcro)   -   "
                + "                SUM(tmpCXC.vrRteIva)      -   "
                + "         SUM(tmpCXC.vrRteIca) ) AS vrSaldo    "
                + "FROM                                          "
                + "(SELECT tblDctos.idLocalCruce                 "
                + "                      AS idLocal,             "
                + "       tblDctos.idTipoOrdenCruce              "
                + "                  AS idTipoOrden,             "
                + "       tblDctos.idDctoCruce                   "
                + "                      AS idDcto,              "
                + "       tblDctos.idOrdenCruce AS idOrden,      "
                + "       tblDctos.indicador,                    "
                + "       tblDctos.idCliente,                    "
                + "       tblDctos.vrBase,                       "
                + "       tblDctos.vrPago,                       "
                + "       tblDctos.vrIva,                        "
                + "       tblDctos.vrRteFuente,                  "
                + "       tblDctos.vrDsctoFcro,                  "
                + "       tblDctos.vrRteIva,                     "
                + "       tblDctos.vrRteIca,                     "
                + "       tblDctos.vrImpoconsumo,                "
                + "       tblDctos.fechaDcto,                    "
                + "       tblDctos.diasPlazo,                    "
                + "       tblDctos.idDctoNitCC                   "
                + "FROM   tblDctos                               "
                + "WHERE  tblDctos.idLocal           =           "
                + this.getIdLocal() + "                          "
                + "AND   tblDctos.idCliente          =          '"
                + this.getIdCliente() + "'                       "
                + "AND    tblDctos.idTipoOrdenCruce  =           "
                + this.getIdTipoOrden() + "                      "
                + "AND    tblDctos.idDctoCruce      != 0         "
                + "AND    EXISTS (                               "
                + "       SELECT *                               "
                + "       FROM tblDctosOrdenesDetalle            "
                + "       WHERE tblDctosOrdenesDetalle.idLocal = "
                + "                            tblDctos.idLocal  "
                + "       AND tblDctosOrdenesDetalle.idTipoOrden "
                + "                                            = "
                + "                       tblDctos.idTipoOrden   "
                + "      AND  tblDctosOrdenesDetalle.idOrden   = "
                + "                           tblDctos.idOrden ) "
                + "UNION                                         "
                + "SELECT tblDctos.idLocal,                      "
                + "       tblDctos.idTipoOrden,                  "
                + "       tblDctos.idDcto,                       "
                + "       tblDctos.idOrden,                      "
                + "       tblDctos.indicador,                    "
                + "       tblDctos.idCliente,                    "
                + "       tblDctos.vrBase,                       "
                + "       tblDctos.vrPago,                       "
                + "       tblDctos.vrIva,                        "
                + "       tblDctos.vrRteFuente,                  "
                + "       tblDctos.vrDsctoFcro,                  "
                + "       tblDctos.vrRteIva,                     "
                + "       tblDctos.vrRteIca,                     "
                + "       tblDctos.vrImpoconsumo,                "
                + "       tblDctos.fechaDcto,                    "
                + "       tblDctos.diasPlazo,                    "
                + "       tblDctos.idDctoNitCC                   "
                + "FROM   tblDctos                               "
                + "WHERE  tblDctos.idLocal           =           "
                + this.getIdLocal() + "                          "
                + "AND   tblDctos.idCliente          =          '"
                + this.getIdCliente() + "'                       "
                + "AND    tblDctos.idTipoOrden       =           "
                + this.getIdTipoOrden() + "                      "
                + "AND    tblDctos.idDctoCruce       = 0         "
                + "AND    EXISTS (                               "
                + "       SELECT *                               "
                + "       FROM tblDctosOrdenesDetalle            "
                + "       WHERE tblDctosOrdenesDetalle.idLocal = "
                + "                            tblDctos.idLocal  "
                + "     AND tblDctosOrdenesDetalle.idTipoOrden = "
                + "                       tblDctos.idTipoOrden   "
                + "      AND  tblDctosOrdenesDetalle.idOrden   = "
                + "                           tblDctos.idOrden ) "
                + "UNION                                         "
                + "SELECT tblDctos.idLocal,                      "
                + "       tblDctos.idTipoOrden,                  "
                + "       tblDctos.idDctoCruce                   "
                + "                      AS idDcto,              "
                + "       tblDctos.idOrdenCruce AS idOrden,      "
                + "       tblDctos.indicador,                    "
                + "       tblDctos.idCliente,                    "
                + "       tblDctos.vrBase,                       "
                + "       tblDctos.vrPago,                       "
                + "       tblDctos.vrIva,                        "
                + "       tblDctos.vrRteFuente,                  "
                + "       tblDctos.vrDsctoFcro,                  "
                + "       tblDctos.vrRteIva,                     "
                + "       tblDctos.vrRteIca,                     "
                + "       tblDctos.vrImpoconsumo,                "
                + "       tblDctos.fechaDcto,                    "
                + "       tblDctos.diasPlazo,                    "
                + "       tblDctos.idDctoNitCC                   "
                + "FROM   tblDctos                               "
                + "WHERE  tblDctos.idLocal           =           "
                + this.getIdLocal() + "                          "
                + "AND   tblDctos.idCliente          =          '"
                + this.getIdCliente() + "'                       "
                + "AND    tblDctos.idTipoOrden       =           "
                + this.getIdTipoOrden() + "                      "
                + "AND    tblDctos.idTipoOrdenCruce  = 0         "
                + "AND    tblDctos.idDctoCruce      != 0         "
                + "AND    EXISTS (                               "
                + "       SELECT *                               "
                + "       FROM tblDctosOrdenesDetalle            "
                + "       WHERE tblDctosOrdenesDetalle.idLocal = "
                + "                            tblDctos.idLocal  "
                + "       AND tblDctosOrdenesDetalle.idTipoOrden "
                + "                                            = "
                + "                       tblDctos.idTipoOrden   "
                + "      AND  tblDctosOrdenesDetalle.idOrden   = "
                + "                           tblDctos.idOrden ) "
                + "                          )  AS tmpCXC        "
                + "GROUP BY tmpCXC.IDLOCAL,                      "
                + "         tmpCXC.IDTIPOORDEN,                  "
                + "         tmpCXC.idDcto,                       "
                + "         tmpCXC.idOrden,                      "
                + "         tmpCXC.indicador                     "
                + "HAVING ( SUM(tmpCXC.vrBase)        +          "
                + "         SUM(tmpCXC.vrIva)         +          "
                + "         SUM(tmpCXC.vrImpoconsumo) -          "
                + "         SUM(tmpCXC.vrPago)        -          "
                + "         SUM(tmpCXC.vrRteFuente)   -          "
                + "         SUM(tmpCXC.vrDsctoFcro)   -          "
                + "         SUM(tmpCXC.vrRteIva)      -          "
                + "         SUM(tmpCXC.vrRteIca) ) > 1 )         "
                + "                              AS tmpSaldo     "
                + " WHERE tmpSaldo.diasVcto >                    "
                + getDiasPlazo() + "                             "
                + "GROUP BY tmpSaldo.idLocal,                    "
                + "         tmpSaldo.IDTIPOORDEN,                "
                + "         tmpSaldo.idDcto,                     "
                + "         tmpSaldo.idOrden,                    "
                + "         tmpSaldo.fechaDcto,                  "
                + "         tmpSaldo.fechaVcto,                  "
                + "         tmpSaldo.diasVcto,                   "
                + "         tmpSaldo.vrSaldo,                    "
                + "         tmpSaldo.indicador                   "
                + "ORDER BY tmpSaldo.fechaDcto                   ";

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
                xPlazoOk = true;

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
            return xPlazoOk;

        }
    }

    // Metodo
    public Vector listaCuentaPlanilla() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        //
        String selectStr
                = "SELECT tmpSaldo.idLocal,                       "
                + "       tmpSaldo.IDTIPOORDEN,                   "
                + "       tmpSaldo.idDcto,                        "
                + "       tmpSaldo.idOrden,                       "
                + "       tmpSaldo.fechaDcto,                     "
                + "       tmpSaldo.fechaVcto,                     "
                + "       tmpSaldo.diasVcto AS diasMora,          "
                + "       tmpSaldo.vrSaldo,                       "
                + "       tmpSaldo.indicador,                     "
                + "       MAX(tmpSaldo.idDctoNitCC)               "
                + "                             AS idDctoNitCC,   "
                + "       (SELECT TOP 1 tblTerceros.nombreTercero "
                + "        FROM    tblTerceros                    "
                + "        WHERE   tblTerceros.idCliente =        "
                + "                          tmpSaldo.idCliente)  "
                + "                              AS nombreTercero "
                + "FROM                                           "
                + "(SELECT tmpCXC.IDLOCAL,                        "
                + "        tmpCXC.IDTIPOORDEN,                    "
                + "        tmpCXC.idDcto,                         "
                + "        tmpCXC.idOrden,                        "
                + "       tmpCXC.indicador,                       "
                + "       MAX(tmpCXC.idCliente) AS idCliente,     "
                + "       MAX(tmpCXC.idDctoNitCC)                 "
                + "                             AS idDctoNitCC,   "
                + "       MAX(tmpCXC.diasPlazo) AS diasPlazo,     "
                + "       MIN(tmpCXC.fechaDcto) AS fechaDcto,     "
                + "       MIN(tmpCXC.fechaDcto) +                 "
                + "       MAX(tmpCXC.diasPlazo) AS fechaVcto,     "
                + "       DATEDIFF(DAY,  MIN(tmpCXC.fechaDcto) +  "
                + "             MAX(tmpCXC.diasPlazo),GETDATE())  "
                + "                             AS diasVcto,      "
                + "       SUM(tmpCXC.vrBase) AS vrBase,           "
                + "       SUM(tmpCXC.vrPago) AS vrPago,           "
                + "       SUM(tmpCXC.vrIva) AS vrIva,             "
                + "       SUM(tmpCXC.vrRteFuente) AS vrRteFuente, "
                + "       SUM(tmpCXC.vrDsctoFcro) AS vrDsctoFcro, "
                + "       SUM(tmpCXC.vrRteIva) AS vrRteIva,       "
                + "       SUM(tmpCXC.vrRteIca) AS vrRteIca,       "
                + "       SUM(tmpCXC.vrImpoconsumo)               "
                + "                          AS vrImpoconsumo,    "
                + "       ( SUM(tmpCXC.vrBase)               +    "
                + "                SUM(tmpCXC.vrIva)         +    "
                + "                SUM(tmpCXC.vrImpoconsumo) -    "
                + "                SUM(tmpCXC.vrPago)        -    "
                + "                SUM(tmpCXC.vrRteFuente)   -    "
                + "                SUM(tmpCXC.vrDsctoFcro)   -    "
                + "                SUM(tmpCXC.vrRteIva)      -    "
                + "         SUM(tmpCXC.vrRteIca) ) AS vrSaldo     "
                + "FROM                                           "
                + "( SELECT tblDctos.idLocal,             	"
                + "       tblDctos.idTipoOrden,                   "
                + "       tblDctos.idDcto,              	        "
                + "       tblDctos.idOrden,              	        "
                + "       tblDctos.indicador,                     "
                + "       tblDctos.idCliente,                     "
                + "       tblDctos.vrBase,                        "
                + "       tblDctos.vrPago,                        "
                + "       tblDctos.vrIva,                         "
                + "       tblDctos.vrImpoconsumo,                 "
                + "       tblDctos.vrRteFuente,                   "
                + "       tblDctos.vrDsctoFcro,                   "
                + "       tblDctos.vrRteIva,                      "
                + "       tblDctos.vrRteIca,                      "
                + "       tblDctos.fechaDcto,                     "
                + "       tblDctos.diasPlazo,                     "
                + "       tblDctos.idDctoNitCC                    "
                + "FROM   tblDctos                                "
                + "WHERE  tblDctos.idLocal           =            "
                + this.getIdLocal() + "                           "
                + "AND    tblDctos.idTipoOrden       =            "
                + this.getIdTipoOrden() + "                       "
                + "AND    tblDctos.idDctoCruce       =   0        "
                + "AND    EXISTS (                                "
                + "       SELECT *                                "
                + "       FROM tblDctosOrdenesDetalle             "
                + "       WHERE tblDctosOrdenesDetalle.idLocal =  "
                + "                            tblDctos.idLocal   "
                + "       AND tblDctosOrdenesDetalle.idTipoOrden  "
                + "                                            =  "
                + "                       tblDctos.idTipoOrden    "
                + "      AND  tblDctosOrdenesDetalle.idOrden   =  "
                + "                           tblDctos.idOrden )  "
                + "AND    EXISTS (SELECT tblPagos.*	        "
                + "               FROM   tblPagos		        "
                + "               INNER JOIN tblPagosMedios       "
                + "               ON  tblPagos.idLocal      =     "
                + "                       tblPagosMedios.idLocal  "
                + "               AND tblPagos.idTipoOrden  =     "
                + "                   tblPagosMedios.idTipoOrden  "
                + "               AND tblPagos.idRecibo     =     "
                + "                      tblPagosMedios.idRecibo  "
                + "               AND tblPagos.indicador    =     "
                + "                     tblPagosMedios.indicador  "
                + "               WHERE tblPagos.idLocal     =    "
                + "                             tblDctos.idLocal  "
                + "               AND   tblPagos.idTipoOrden =    "
                + "                  (tblDctos.idTipoOrden + 50)  "
                + "               AND   tblPagos.idDcto      =    "
                + "                             tblDctos.idDcto   "
                + "               AND   tblPagos.indicador   =    "
                + "                           tblDctos.indicador  "
                + "               AND   tblPagos.nitCC   =        "
                + "                            tblDctos.idCliente "
                + "               AND   tblPagos.idLog       =    "
                + this.getIdLog() + "                             "
                + "               AND   tblPagos.idLocal     =    "
                + this.getIdLocal() + "                           "
                + "               AND   tblPagos.idTipoOrden =    "
                + this.getIdTipoOrden() + " + 50 )                "
                + " UNION				                "
                + " SELECT tblDctos.idLocalCruce                  "
                + "                      AS idLocal,              "
                + "       tblDctos.idTipoOrdenCruce               "
                + "                  AS idTipoOrden,              "
                + "       tblDctos.idDctoCruce                    "
                + "                      AS idDcto,               "
                + "       tblDctos.idOrdenCruce AS idOrden,       "
                + "       tblDctos.indicador,                     "
                + "       tblDctos.idCliente,                     "
                + "       tblDctos.vrBase,                        "
                + "       tblDctos.vrPago,                        "
                + "       tblDctos.vrIva,                         "
                + "       tblDctos.vrImpoconsumo,                 "
                + "       tblDctos.vrRteFuente,                   "
                + "       tblDctos.vrDsctoFcro,                   "
                + "       tblDctos.vrRteIva,                      "
                + "       tblDctos.vrRteIca,                      "
                + "       tblDctos.fechaDcto,                     "
                + "       tblDctos.diasPlazo,                     "
                + "       tblDctos.idDctoNitCC                    "
                + "FROM   tblDctos                                "
                + "WHERE  tblDctos.idLocal           =            "
                + this.getIdLocal() + "                           "
                + "AND    tblDctos.idTipoOrdenCruce  =            "
                + this.getIdTipoOrden() + "                       "
                + "AND    tblDctos.idDctoCruce      != 0          "
                + "AND    EXISTS (                                "
                + "       SELECT *                                "
                + "       FROM tblDctosOrdenesDetalle             "
                + "       WHERE tblDctosOrdenesDetalle.idLocal =  "
                + "                            tblDctos.idLocal   "
                + "       AND tblDctosOrdenesDetalle.idTipoOrden  "
                + "                                            =  "
                + "                       tblDctos.idTipoOrden    "
                + "      AND  tblDctosOrdenesDetalle.idOrden   =  "
                + "                           tblDctos.idOrden )  "
                + "AND    EXISTS (SELECT tblPagos.*	        "
                + "               FROM   tblPagos		        "
                + "               INNER JOIN tblPagosMedios       "
                + "               ON  tblPagos.idLocal      =     "
                + "                       tblPagosMedios.idLocal  "
                + "               AND tblPagos.idTipoOrden  =     "
                + "                   tblPagosMedios.idTipoOrden  "
                + "               AND tblPagos.idRecibo     =     "
                + "                      tblPagosMedios.idRecibo  "
                + "               AND tblPagos.indicador    =     "
                + "                     tblPagosMedios.indicador  "
                + "               WHERE tblPagos.idLocal     =    "
                + "                         tblDctos.idLocalCruce "
                + "               AND   tblPagos.idTipoOrden =    "
                + "               (tblDctos.idTipoOrdenCruce + 50)"
                + "               AND   tblPagos.idDcto      =    "
                + "                         tblDctos.idDctoCruce  "
                + "               AND   tblPagos.indicador   =    "
                + "                           tblDctos.indicador  "
                + "               AND   tblPagos.nitCC   =        "
                + "                            tblDctos.idCliente "
                + "               AND   tblPagos.idLog       =    "
                + this.getIdLog() + "                             "
                + "               AND   tblPagos.idLocal     =    "
                + this.getIdLocal() + "                           "
                + "               AND   tblPagos.idTipoOrden =    "
                + this.getIdTipoOrden() + " + 50 )                "
                + "UNION					        "
                + "SELECT tblDctos.idLocal,                       "
                + "       tblDctos.idTipoOrden,                   "
                + "       tblDctos.idDctoCruce                    "
                + "                      AS idDcto,               "
                + "       tblDctos.idOrdenCruce AS idOrden,       "
                + "       tblDctos.indicador,                     "
                + "       tblDctos.idCliente,                     "
                + "       tblDctos.vrBase,                        "
                + "       tblDctos.vrPago,                        "
                + "       tblDctos.vrIva,                         "
                + "       tblDctos.vrImpoconsumo,                 "
                + "       tblDctos.vrRteFuente,                   "
                + "       tblDctos.vrDsctoFcro,                   "
                + "       tblDctos.vrRteIva,                      "
                + "       tblDctos.vrRteIca,                      "
                + "       tblDctos.fechaDcto,                     "
                + "       tblDctos.diasPlazo,                     "
                + "       tblDctos.idDctoNitCC                    "
                + "FROM   tblDctos                                "
                + "WHERE  tblDctos.idLocal           =            "
                + this.getIdLocal() + "                           "
                + "AND    tblDctos.idTipoOrden       =            "
                + this.getIdTipoOrden() + "                       "
                + "AND    tblDctos.idDctoCruce      != 0          "
                + "AND    EXISTS (                                "
                + "       SELECT *                                "
                + "       FROM tblDctosOrdenesDetalle             "
                + "       WHERE tblDctosOrdenesDetalle.idLocal =  "
                + "                            tblDctos.idLocal   "
                + "       AND tblDctosOrdenesDetalle.idTipoOrden  "
                + "                                            =  "
                + "                       tblDctos.idTipoOrden    "
                + "      AND  tblDctosOrdenesDetalle.idOrden   =  "
                + "                           tblDctos.idOrden )  "
                + "AND    EXISTS (SELECT tblPagos.*	        "
                + "               FROM   tblPagos		        "
                + "               INNER JOIN tblPagosMedios       "
                + "               ON  tblPagos.idLocal      =     "
                + "                       tblPagosMedios.idLocal  "
                + "               AND tblPagos.idTipoOrden  =     "
                + "                   tblPagosMedios.idTipoOrden  "
                + "               AND tblPagos.idRecibo     =     "
                + "                      tblPagosMedios.idRecibo  "
                + "               AND tblPagos.indicador    =     "
                + "                     tblPagosMedios.indicador  "
                + "               WHERE tblPagos.idLocal     =    "
                + "                         tblDctos.idLocalCruce "
                + "               AND   tblPagos.idTipoOrden =    "
                + "                   (tblDctos.idTipoOrden + 50) "
                + "               AND   tblPagos.idDcto      =    "
                + "                         tblDctos.idDctoCruce  "
                + "               AND   tblPagos.indicador   =    "
                + "                           tblDctos.indicador  "
                + "               AND   tblPagos.nitCC   =        "
                + "                            tblDctos.idCliente "
                + "               AND   tblPagos.idLog       =    "
                + this.getIdLog() + "                             "
                + "               AND   tblPagos.idLocal     =    "
                + this.getIdLocal() + "                           "
                + "               AND   tblPagos.idTipoOrden =    "
                + this.getIdTipoOrden() + " + 50 ) )    AS tmpCXC "
                + "GROUP BY tmpCXC.IDLOCAL,                       "
                + "         tmpCXC.IDTIPOORDEN,                   "
                + "         tmpCXC.idDcto,                        "
                + "         tmpCXC.idOrden,                       "
                + "         tmpCXC.indicador                      "
                + "HAVING ( SUM(tmpCXC.vrBase)        +           "
                + "         SUM(tmpCXC.vrIva)         +           "
                + "         SUM(tmpCXC.vrImpoconsumo) -           "
                + "         SUM(tmpCXC.vrPago)        -           "
                + "         SUM(tmpCXC.vrRteFuente)   -           "
                + "         SUM(tmpCXC.vrDsctoFcro)   -           "
                + "         SUM(tmpCXC.vrRteIva)      -           "
                + "         SUM(tmpCXC.vrRteIca) ) > 1 )          "
                + "                              AS tmpSaldo      "
                + "GROUP BY tmpSaldo.idLocal,                     "
                + "         tmpSaldo.IDTIPOORDEN,                 "
                + "         tmpSaldo.idDcto,                      "
                + "         tmpSaldo.idOrden,                     "
                + "         tmpSaldo.fechaDcto,                   "
                + "         tmpSaldo.fechaVcto,                   "
                + "         tmpSaldo.diasVcto,                    "
                + "         tmpSaldo.vrSaldo,                     "
                + "         tmpSaldo.indicador,                   "
                + "         tmpSaldo.idCliente                    "
                + "ORDER BY tmpSaldo.fechaDcto                    ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaDctoBean fachadaBean;

            while (rs.next()) {

                //
                fachadaBean = new FachadaDctoBean();

                //
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("IDTIPOORDEN"));
                fachadaBean.setIdDcto(rs.getInt("idDcto"));
                fachadaBean.setIdOrden(rs.getInt("idOrden"));
                fachadaBean.setFechaDcto(rs.getString("fechaDcto"));
                fachadaBean.setFechaVcto(rs.getString("fechaVcto"));
                fachadaBean.setDiasMora(rs.getInt("diasMora"));
                fachadaBean.setVrSaldo(rs.getInt("vrSaldo"));
                fachadaBean.setIndicador(rs.getInt("indicador"));
                fachadaBean.setIdDctoNitCC(rs.getString("idDctoNitCC"));
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

    // Metodo
    public Vector listaDctoPlanilla() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        //
        String selectStr
                = "SELECT tmpSaldo.idLocal,                      "
                + "       tmpSaldo.IDTIPOORDEN,                  "
                + "       tmpSaldo.idDcto,                       "
                + "       tmpSaldo.fechaDcto,                    "
                + "       tmpSaldo.fechaVcto,                    "
                + "       tmpSaldo.diasVcto AS diasMora,         "
                + "       tmpSaldo.vrSaldo,                      "
                + "       tmpSaldo.indicador,                    "
                + "       MAX(tmpSaldo.idDctoNitCC)              "
                + "                             AS idDctoNitCC   "
                + "FROM                                          "
                + "(SELECT tmpCXC.IDLOCAL,                       "
                + "        tmpCXC.IDTIPOORDEN,                   "
                + "        tmpCXC.idDcto,                        "
                + "       tmpCXC.indicador,                      "
                + "       MAX(tmpCXC.idDctoNitCC)                "
                + "                             AS idDctoNitCC,  "
                + "       MAX(tmpCXC.diasPlazo) AS diasPlazo,    "
                + "       MIN(tmpCXC.fechaDcto) AS fechaDcto,    "
                + "       MIN(tmpCXC.fechaDcto) +                "
                + "       MAX(tmpCXC.diasPlazo) AS fechaVcto,    "
                + "       DATEDIFF(DAY,  MIN(tmpCXC.fechaDcto) + "
                + "             MAX(tmpCXC.diasPlazo),GETDATE()) "
                + "                             AS diasVcto,     "
                + "       SUM(tmpCXC.vrBase) AS vrBase,          "
                + "       SUM(tmpCXC.vrPago) AS vrPago,          "
                + "       SUM(tmpCXC.vrIva) AS vrIva,            "
                + "       SUM(tmpCXC.vrRteFuente) AS vrRteFuente,"
                + "       SUM(tmpCXC.vrDsctoFcro) AS vrDsctoFcro,"
                + "       SUM(tmpCXC.vrRteIva) AS vrRteIva,      "
                + "       SUM(tmpCXC.vrRteIca) AS vrRteIca,      "
                + "       ( SUM(tmpCXC.vrBase)             +     "
                + "                SUM(tmpCXC.vrIva)       -     "
                + "                SUM(tmpCXC.vrPago)      -     "
                + "                SUM(tmpCXC.vrRteFuente) -     "
                + "                SUM(tmpCXC.vrDsctoFcro) -     "
                + "                SUM(tmpCXC.vrRteIva)    -     "
                + "         SUM(tmpCXC.vrRteIca) ) AS vrSaldo    "
                + "FROM                                          "
                + "(SELECT tblDctos.idLocalCruce                 "
                + "                      AS idLocal,             "
                + "       tblDctos.idTipoOrdenCruce              "
                + "                  AS idTipoOrden,             "
                + "       tblDctos.idDctoCruce                   "
                + "                      AS idDcto,              "
                + "       tblDctos.indicador,                    "
                + "       tblDctos.idCliente,                    "
                + "       tblDctos.vrBase,                       "
                + "       tblDctos.vrPago,                       "
                + "       tblDctos.vrIva,                        "
                + "       tblDctos.vrRteFuente,                  "
                + "       tblDctos.vrDsctoFcro,                  "
                + "       tblDctos.vrRteIva,                     "
                + "       tblDctos.vrRteIca,                     "
                + "       tblDctos.fechaDcto,                    "
                + "       tblDctos.diasPlazo,                    "
                + "       tblDctos.idDctoNitCC                   "
                + "FROM   tblDctos                               "
                + "WHERE  tblDctos.idLocal           =           "
                + this.getIdLocal() + "                          "
                + "AND   tblDctos.idDcto             =           "
                + this.getIdDcto() + "                           "
                + "AND    tblDctos.idTipoOrdenCruce  =           "
                + this.getIdTipoOrden() + "                      "
                + "AND    tblDctos.idDctoCruce      != 0         "
                + "AND    EXISTS (                               "
                + "       SELECT *                               "
                + "       FROM tblDctosOrdenesDetalle            "
                + "       WHERE tblDctosOrdenesDetalle.idLocal = "
                + "                            tblDctos.idLocal  "
                + "       AND tblDctosOrdenesDetalle.idTipoOrden "
                + "                                            = "
                + "                       tblDctos.idTipoOrden   "
                + "      AND  tblDctosOrdenesDetalle.idOrden   = "
                + "                           tblDctos.idOrden ) "
                + "UNION                                         "
                + "SELECT tblDctos.idLocal,                      "
                + "       tblDctos.idTipoOrden,                  "
                + "       tblDctos.idDcto,                       "
                + "       tblDctos.indicador,                    "
                + "       tblDctos.idCliente,                    "
                + "       tblDctos.vrBase,                       "
                + "       tblDctos.vrPago,                       "
                + "       tblDctos.vrIva,                        "
                + "       tblDctos.vrRteFuente,                  "
                + "       tblDctos.vrDsctoFcro,                  "
                + "       tblDctos.vrRteIva,                     "
                + "       tblDctos.vrRteIca,                     "
                + "       tblDctos.fechaDcto,                    "
                + "       tblDctos.diasPlazo,                    "
                + "       tblDctos.idDctoNitCC                   "
                + "FROM   tblDctos                               "
                + "WHERE  tblDctos.idLocal           =           "
                + this.getIdLocal() + "                          "
                + "AND   tblDctos.idDcto             =           "
                + this.getIdDcto() + "                           "
                + "AND    tblDctos.idTipoOrden       =           "
                + this.getIdTipoOrden() + "                      "
                + "AND    tblDctos.idDctoCruce       = 0         "
                + "AND    EXISTS (                               "
                + "       SELECT *                               "
                + "       FROM tblDctosOrdenesDetalle            "
                + "       WHERE tblDctosOrdenesDetalle.idLocal = "
                + "                            tblDctos.idLocal  "
                + "       AND tblDctosOrdenesDetalle.idTipoOrden "
                + "                                            = "
                + "                       tblDctos.idTipoOrden   "
                + "      AND  tblDctosOrdenesDetalle.idOrden   = "
                + "                           tblDctos.idOrden ) "
                + "UNION                                         "
                + "SELECT tblDctos.idLocal,                      "
                + "       tblDctos.idTipoOrden,                  "
                + "       tblDctos.idDctoCruce AS idDcto,        "
                + "       tblDctos.indicador,                    "
                + "       tblDctos.idCliente,                    "
                + "       tblDctos.vrBase,                       "
                + "       tblDctos.vrPago,                       "
                + "       tblDctos.vrIva,                        "
                + "       tblDctos.vrRteFuente,                  "
                + "       tblDctos.vrDsctoFcro,                  "
                + "       tblDctos.vrRteIva,                     "
                + "       tblDctos.vrRteIca,                     "
                + "       tblDctos.fechaDcto,                    "
                + "       tblDctos.diasPlazo,                    "
                + "       tblDctos.idDctoNitCC                   "
                + "FROM   tblDctos                               "
                + "WHERE  tblDctos.idLocal           =           "
                + this.getIdLocal() + "                          "
                + "AND   tblDctos.idDcto             =           "
                + this.getIdDcto() + "                           "
                + "AND    tblDctos.idTipoOrden       =           "
                + this.getIdTipoOrden() + "                      "
                + "AND    tblDctos.idTipoOrdenCruce  = 0         "
                + "AND    tblDctos.idDctoCruce      != 0         "
                + "AND    EXISTS (                               "
                + "       SELECT *                               "
                + "       FROM tblDctosOrdenesDetalle            "
                + "       WHERE tblDctosOrdenesDetalle.idLocal = "
                + "                            tblDctos.idLocal  "
                + "       AND tblDctosOrdenesDetalle.idTipoOrden "
                + "                                            = "
                + "                       tblDctos.idTipoOrden   "
                + "      AND  tblDctosOrdenesDetalle.idOrden   = "
                + "                           tblDctos.idOrden ) "
                + "                          )  AS tmpCXC        "
                + "GROUP BY tmpCXC.IDLOCAL,                      "
                + "         tmpCXC.IDTIPOORDEN,                  "
                + "         tmpCXC.idDcto,                       "
                + "         tmpCXC.indicador                     "
                + "HAVING ( SUM(tmpCXC.vrBase)      +            "
                + "         SUM(tmpCXC.vrIva)       -            "
                + "         SUM(tmpCXC.vrPago)      -            "
                + "         SUM(tmpCXC.vrRteFuente) -            "
                + "         SUM(tmpCXC.vrDsctoFcro) -            "
                + "         SUM(tmpCXC.vrRteIva)    -            "
                + "         SUM(tmpCXC.vrRteIca) ) > 1 )         "
                + "                              AS tmpSaldo     "
                + "GROUP BY tmpSaldo.idLocal,                    "
                + "         tmpSaldo.IDTIPOORDEN,                "
                + "         tmpSaldo.idDcto,                     "
                + "         tmpSaldo.fechaDcto,                  "
                + "         tmpSaldo.fechaVcto,                  "
                + "         tmpSaldo.diasVcto,                   "
                + "         tmpSaldo.vrSaldo,                    "
                + "         tmpSaldo.indicador                   "
                + "ORDER BY tmpSaldo.fechaDcto                   ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaDctoBean fachadaBean;

            while (rs.next()) {

                //
                fachadaBean = new FachadaDctoBean();

                //
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("IDTIPOORDEN"));
                fachadaBean.setIdDcto(rs.getInt("idDcto"));
                fachadaBean.setFechaDcto(
                        rs.getString("fechaDcto").substring(0, 11));
                fachadaBean.setFechaVcto(
                        rs.getString("fechaVcto").substring(0, 11));
                fachadaBean.setDiasMora(rs.getInt("diasMora"));
                fachadaBean.setVrSaldo(rs.getInt("vrSaldo"));
                fachadaBean.setIndicador(rs.getInt("indicador"));
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

    // listaCuentaDetalladoClienteFCH
    public FachadaDctoBean listaCuentaDetalladoClienteFCH() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        Connection connection = null;

        //
        FachadaDctoBean fachadaBean = new FachadaDctoBean();

        //
        String selectStr
                = "SELECT tmpSaldo.idLocal,                      "
                + "       tmpSaldo.IDTIPOORDEN,                  "
                + "       tmpSaldo.idDcto,                       "
                + "       tmpSaldo.fechaDcto,                    "
                + "       tmpSaldo.fechaVcto,                    "
                + "       tmpSaldo.diasVcto AS diasMora,         "
                + "       tmpSaldo.vrSaldo,                      "
                + "       tmpSaldo.indicador,                    "
                + "       MAX(tmpSaldo.idDctoNitCC)              "
                + "                             AS idDctoNitCC,  "
                + "       MAX(tmpSaldo.idOrden) AS idOrden       "
                + "FROM                                          "
                + "(SELECT tmpCXC.IDLOCAL,                       "
                + "        tmpCXC.IDTIPOORDEN,                   "
                + "        tmpCXC.idDcto,                        "
                + "        tmpCXC.idOrden,                       "
                + "       tmpCXC.indicador,                      "
                + "       MAX(tmpCXC.idDctoNitCC)                "
                + "                             AS idDctoNitCC,  "
                + "       MAX(tmpCXC.diasPlazo) AS diasPlazo,    "
                + "       MIN(tmpCXC.fechaDcto) AS fechaDcto,    "
                + "       MIN(tmpCXC.fechaDcto) +                "
                + "       MAX(tmpCXC.diasPlazo) AS fechaVcto,    "
                + "       DATEDIFF(DAY,  MIN(tmpCXC.fechaDcto) + "
                + "             MAX(tmpCXC.diasPlazo),GETDATE()) "
                + "                             AS diasVcto,     "
                + "       SUM(tmpCXC.vrBase) AS vrBase,          "
                + "       SUM(tmpCXC.vrPago) AS vrPago,          "
                + "       SUM(tmpCXC.vrIva) AS vrIva,            "
                + "       SUM(tmpCXC.vrImpoconsumo)              "
                + "                          AS vrImpoconsumo,   "
                + "       SUM(tmpCXC.vrRteFuente) AS vrRteFuente,"
                + "       SUM(tmpCXC.vrDsctoFcro) AS vrDsctoFcro,"
                + "       SUM(tmpCXC.vrRteIva) AS vrRteIva,      "
                + "       SUM(tmpCXC.vrRteIca) AS vrRteIca,      "
                + "       ( SUM(tmpCXC.vrBase)               +   "
                + "                SUM(tmpCXC.vrIva)         +   "
                + "                SUM(tmpCXC.vrImpoconsumo) -   "
                + "                SUM(tmpCXC.vrPago)        -   "
                + "                SUM(tmpCXC.vrRteFuente)   -   "
                + "                SUM(tmpCXC.vrDsctoFcro)   -   "
                + "                SUM(tmpCXC.vrRteIva)      -   "
                + "         SUM(tmpCXC.vrRteIca) ) AS vrSaldo    "
                + "FROM                                          "
                + "(SELECT tblDctos.idLocalCruce                 "
                + "                      AS idLocal,             "
                + "       tblDctos.idTipoOrdenCruce              "
                + "                  AS idTipoOrden,             "
                + "       tblDctos.idDctoCruce                   "
                + "                      AS idDcto,              "
                + "       tblDctos.idOrdenCruce AS idOrden,      "
                + "       tblDctos.indicador,                    "
                + "       tblDctos.idCliente,                    "
                + "       tblDctos.vrBase,                       "
                + "       tblDctos.vrPago,                       "
                + "       tblDctos.vrIva,                        "
                + "       tblDctos.vrImpoconsumo,                "
                + "       tblDctos.vrRteFuente,                  "
                + "       tblDctos.vrDsctoFcro,                  "
                + "       tblDctos.vrRteIva,                     "
                + "       tblDctos.vrRteIca,                     "
                + "       tblDctos.fechaDcto,                    "
                + "       tblDctos.diasPlazo,                    "
                + "       tblDctos.idDctoNitCC                   "
                + "FROM   tblDctos                               "
                + "WHERE  tblDctos.idLocal           =           "
                + this.getIdLocal() + "                          "
                + "AND   tblDctos.idCliente          =          '"
                + this.getIdCliente() + "'                       "
                + "AND    tblDctos.idTipoOrdenCruce  =           "
                + this.getIdTipoOrden() + "                      "
                + "AND    tblDctos.idDctoCruce       =           "
                + this.getIdDcto() + "                           "
                + "AND    EXISTS (                               "
                + "       SELECT *                               "
                + "       FROM tblDctosOrdenesDetalle            "
                + "       WHERE tblDctosOrdenesDetalle.idLocal = "
                + "                            tblDctos.idLocal  "
                + "       AND tblDctosOrdenesDetalle.idTipoOrden "
                + "                                            = "
                + "                       tblDctos.idTipoOrden   "
                + "      AND  tblDctosOrdenesDetalle.idOrden   = "
                + "                           tblDctos.idOrden ) "
                + "UNION                                         "
                + "SELECT tblDctos.idLocal,                      "
                + "       tblDctos.idTipoOrden,                  "
                + "       tblDctos.idDcto,                       "
                + "       tblDctos.idOrden,                      "
                + "       tblDctos.indicador,                    "
                + "       tblDctos.idCliente,                    "
                + "       tblDctos.vrBase,                       "
                + "       tblDctos.vrPago,                       "
                + "       tblDctos.vrIva,                        "
                + "       tblDctos.vrImpoconsumo,                "
                + "       tblDctos.vrRteFuente,                  "
                + "       tblDctos.vrDsctoFcro,                  "
                + "       tblDctos.vrRteIva,                     "
                + "       tblDctos.vrRteIca,                     "
                + "       tblDctos.fechaDcto,                    "
                + "       tblDctos.diasPlazo,                    "
                + "       tblDctos.idDctoNitCC                   "
                + "FROM   tblDctos                               "
                + "WHERE  tblDctos.idLocal           =           "
                + this.getIdLocal() + "                          "
                + "AND   tblDctos.idCliente          =          '"
                + this.getIdCliente() + "'                       "
                + "AND    tblDctos.idTipoOrden       =           "
                + this.getIdTipoOrden() + "                      "
                + "AND    tblDctos.idDcto            =           "
                + this.getIdDcto() + "                           "
                + "AND    tblDctos.idDctoCruce       = 0         "
                + "AND    EXISTS (                               "
                + "       SELECT *                               "
                + "       FROM tblDctosOrdenesDetalle            "
                + "       WHERE tblDctosOrdenesDetalle.idLocal = "
                + "                            tblDctos.idLocal  "
                + "       AND tblDctosOrdenesDetalle.idTipoOrden "
                + "                                            = "
                + "                       tblDctos.idTipoOrden   "
                + "      AND  tblDctosOrdenesDetalle.idOrden   = "
                + "                           tblDctos.idOrden ) "
                + "UNION                                         "
                + "SELECT tblDctos.idLocal,                      "
                + "       tblDctos.idTipoOrden,                  "
                + "       tblDctos.idDctoCruce                   "
                + "                      AS idDcto,              "
                + "       tblDctos.idOrdenCruce AS idOrden,      "
                + "       tblDctos.indicador,                    "
                + "       tblDctos.idCliente,                    "
                + "       tblDctos.vrBase,                       "
                + "       tblDctos.vrPago,                       "
                + "       tblDctos.vrIva,                        "
                + "       tblDctos.vrImpoconsumo,                "
                + "       tblDctos.vrRteFuente,                  "
                + "       tblDctos.vrDsctoFcro,                  "
                + "       tblDctos.vrRteIva,                     "
                + "       tblDctos.vrRteIca,                     "
                + "       tblDctos.fechaDcto,                    "
                + "       tblDctos.diasPlazo,                    "
                + "       tblDctos.idDctoNitCC                   "
                + "FROM   tblDctos                               "
                + "WHERE  tblDctos.idLocal           =           "
                + this.getIdLocal() + "                          "
                + "AND   tblDctos.idCliente          =          '"
                + this.getIdCliente() + "'                       "
                + "AND    tblDctos.idTipoOrden       =           "
                + this.getIdTipoOrden() + "                      "
                + "AND    tblDctos.idTipoOrdenCruce  = 0         "
                + "AND    tblDctos.idDctoCruce       =           "
                + this.getIdDcto() + "                           "
                + "AND    EXISTS (                               "
                + "       SELECT *                               "
                + "       FROM tblDctosOrdenesDetalle            "
                + "       WHERE tblDctosOrdenesDetalle.idLocal = "
                + "                            tblDctos.idLocal  "
                + "       AND tblDctosOrdenesDetalle.idTipoOrden "
                + "                                            = "
                + "                       tblDctos.idTipoOrden   "
                + "      AND  tblDctosOrdenesDetalle.idOrden   = "
                + "                           tblDctos.idOrden ) "
                + "                          )  AS tmpCXC        "
                + "GROUP BY tmpCXC.IDLOCAL,                      "
                + "         tmpCXC.IDTIPOORDEN,                  "
                + "         tmpCXC.idDcto,                       "
                + "         tmpCXC.idOrden,                      "
                + "         tmpCXC.indicador                     "
                + "HAVING ( SUM(tmpCXC.vrBase)        +          "
                + "         SUM(tmpCXC.vrIva)         +          "
                + "         SUM(tmpCXC.vrImpoconsumo) -          "
                + "         SUM(tmpCXC.vrPago)        -          "
                + "         SUM(tmpCXC.vrRteFuente)   -          "
                + "         SUM(tmpCXC.vrDsctoFcro)   -          "
                + "         SUM(tmpCXC.vrRteIva)      -          "
                + "         SUM(tmpCXC.vrRteIca) ) > 1 )         "
                + "                              AS tmpSaldo     "
                + "GROUP BY tmpSaldo.idLocal,                    "
                + "         tmpSaldo.IDTIPOORDEN,                "
                + "         tmpSaldo.idDcto,                     "
                + "         tmpSaldo.fechaDcto,                  "
                + "         tmpSaldo.fechaVcto,                  "
                + "         tmpSaldo.diasVcto,                   "
                + "         tmpSaldo.vrSaldo,                    "
                + "         tmpSaldo.indicador                   "
                + "ORDER BY tmpSaldo.fechaDcto                   ";

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
                fachadaBean.setIdTipoOrden(rs.getInt("IDTIPOORDEN"));
                fachadaBean.setIdDcto(rs.getInt("idDcto"));
                fachadaBean.setIdOrden(rs.getInt("idOrden"));
                fachadaBean.setFechaDcto(
                        rs.getString("fechaDcto").substring(0, 11));
                fachadaBean.setFechaVcto(
                        rs.getString("fechaVcto").substring(0, 11));
                fachadaBean.setDiasMora(rs.getInt("diasMora"));
                fachadaBean.setVrSaldo(rs.getInt("vrSaldo"));
                fachadaBean.setIndicador(rs.getInt("indicador"));
                fachadaBean.setIdDctoNitCC(rs.getString("idDctoNitCC"));

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

    // listaSaldoDctoFCH
    public FachadaDctoBean listaSaldoDctoFCH(int xIdLocal,
            int xIdTipoOrden,
            int xIdDcto) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        Connection connection = null;

        //
        FachadaDctoBean fachadaBean = new FachadaDctoBean();

        //
        String selectStr
                = "SELECT tmpSaldo.idLocal,                      "
                + "       tmpSaldo.IDTIPOORDEN,                  "
                + "       tmpSaldo.idDcto,                       "
                + "       tmpSaldo.fechaDcto,                    "
                + "       tmpSaldo.fechaVcto,                    "
                + "       tmpSaldo.diasVcto AS diasMora,         "
                + "       tmpSaldo.vrSaldo,                      "
                + "       tmpSaldo.vrRteFuente,                  "
                + "       tmpSaldo.indicador,                    "
                + "       MAX(tmpSaldo.idDctoNitCC)              "
                + "                             AS idDctoNitCC,  "
                + "       MAX(tmpSaldo.idOrden) AS idOrden       "
                + "FROM                                          "
                + "(SELECT tmpCXC.IDLOCAL,                       "
                + "        tmpCXC.IDTIPOORDEN,                   "
                + "        tmpCXC.idDcto,                        "
                + "        tmpCXC.idOrden,                       "
                + "       tmpCXC.indicador,                      "
                + "       MAX(tmpCXC.idDctoNitCC)                "
                + "                             AS idDctoNitCC,  "
                + "       MAX(tmpCXC.diasPlazo) AS diasPlazo,    "
                + "       MIN(tmpCXC.fechaDcto) AS fechaDcto,    "
                + "       MIN(tmpCXC.fechaDcto) +                "
                + "       MAX(tmpCXC.diasPlazo) AS fechaVcto,    "
                + "       DATEDIFF(DAY,  MIN(tmpCXC.fechaDcto) + "
                + "             MAX(tmpCXC.diasPlazo),GETDATE()) "
                + "                             AS diasVcto,     "
                + "       SUM(tmpCXC.vrBase) AS vrBase,          "
                + "       SUM(tmpCXC.vrPago) AS vrPago,          "
                + "       SUM(tmpCXC.vrIva) AS vrIva,            "
                + "       SUM(tmpCXC.vrImpoconsumo)              "
                + "                          AS vrImpoconsumo,   "
                + "       SUM(tmpCXC.vrRteFuente) AS vrRteFuente,"
                + "       SUM(tmpCXC.vrDsctoFcro) AS vrDsctoFcro,"
                + "       SUM(tmpCXC.vrRteIva) AS vrRteIva,      "
                + "       SUM(tmpCXC.vrRteIca) AS vrRteIca,      "
                + "       ( SUM(tmpCXC.vrBase)               +   "
                + "                SUM(tmpCXC.vrIva)         +   "
                + "                SUM(tmpCXC.vrImpoconsumo) -   "
                + "                SUM(tmpCXC.vrPago)        -   "
                + "                SUM(tmpCXC.vrRteFuente)   -   "
                + "                SUM(tmpCXC.vrDsctoFcro)   -   "
                + "                SUM(tmpCXC.vrRteIva)      -   "
                + "         SUM(tmpCXC.vrRteIca) ) AS vrSaldo    "
                + "FROM                                          "
                + "(SELECT tblDctos.idLocalCruce                 "
                + "                      AS idLocal,             "
                + "       tblDctos.idTipoOrdenCruce              "
                + "                  AS idTipoOrden,             "
                + "       tblDctos.idDctoCruce                   "
                + "                      AS idDcto,              "
                + "       tblDctos.idOrdenCruce AS idOrden,      "
                + "       tblDctos.indicador,                    "
                + "       tblDctos.idCliente,                    "
                + "       tblDctos.vrBase,                       "
                + "       tblDctos.vrPago,                       "
                + "       tblDctos.vrIva,                        "
                + "       tblDctos.vrImpoconsumo,                "
                + "       tblDctos.vrRteFuente,                  "
                + "       tblDctos.vrDsctoFcro,                  "
                + "       tblDctos.vrRteIva,                     "
                + "       tblDctos.vrRteIca,                     "
                + "       tblDctos.fechaDcto,                    "
                + "       tblDctos.diasPlazo,                    "
                + "       tblDctos.idDctoNitCC                   "
                + "FROM   tblDctos                               "
                + "WHERE  tblDctos.idLocal           =           "
                + xIdLocal + "                                   "
                + "AND   tblDctos.idCliente          =          '"
                + this.getIdCliente() + "'                       "
                + "AND    tblDctos.idTipoOrdenCruce  =           "
                + xIdTipoOrden + "                               "
                + "AND    tblDctos.idDctoCruce       =           "
                + xIdDcto + "                                    "
                + "AND    EXISTS (                               "
                + "       SELECT *                               "
                + "       FROM tblDctosOrdenesDetalle            "
                + "       WHERE tblDctosOrdenesDetalle.idLocal = "
                + "                            tblDctos.idLocal  "
                + "       AND tblDctosOrdenesDetalle.idTipoOrden "
                + "                                            = "
                + "                       tblDctos.idTipoOrden   "
                + "      AND  tblDctosOrdenesDetalle.idOrden   = "
                + "                           tblDctos.idOrden ) "
                + "UNION                                         "
                + "SELECT tblDctos.idLocal,                      "
                + "       tblDctos.idTipoOrden,                  "
                + "       tblDctos.idDcto,                       "
                + "       tblDctos.idOrden,                      "
                + "       tblDctos.indicador,                    "
                + "       tblDctos.idCliente,                    "
                + "       tblDctos.vrBase,                       "
                + "       tblDctos.vrPago,                       "
                + "       tblDctos.vrIva,                        "
                + "       tblDctos.vrImpoconsumo,                "
                + "       tblDctos.vrRteFuente,                  "
                + "       tblDctos.vrDsctoFcro,                  "
                + "       tblDctos.vrRteIva,                     "
                + "       tblDctos.vrRteIca,                     "
                + "       tblDctos.fechaDcto,                    "
                + "       tblDctos.diasPlazo,                    "
                + "       tblDctos.idDctoNitCC                   "
                + "FROM   tblDctos                               "
                + "WHERE  tblDctos.idLocal           =           "
                + xIdLocal + "                                   "
                + "AND   tblDctos.idCliente          =          '"
                + this.getIdCliente() + "'                       "
                + "AND    tblDctos.idTipoOrden       =           "
                + xIdTipoOrden + "                               "
                + "AND    tblDctos.idDcto            =           "
                + xIdDcto + "                                    "
                + "AND    tblDctos.idDctoCruce       = 0         "
                + "AND    EXISTS (                               "
                + "       SELECT *                               "
                + "       FROM tblDctosOrdenesDetalle            "
                + "       WHERE tblDctosOrdenesDetalle.idLocal = "
                + "                            tblDctos.idLocal  "
                + "       AND tblDctosOrdenesDetalle.idTipoOrden "
                + "                                            = "
                + "                       tblDctos.idTipoOrden   "
                + "      AND  tblDctosOrdenesDetalle.idOrden   = "
                + "                           tblDctos.idOrden ) "
                + "UNION                                         "
                + "SELECT tblDctos.idLocal,                      "
                + "       tblDctos.idTipoOrden,                  "
                + "       tblDctos.idDctoCruce                   "
                + "                      AS idDcto,              "
                + "       tblDctos.idOrdenCruce AS idOrden,      "
                + "       tblDctos.indicador,                    "
                + "       tblDctos.idCliente,                    "
                + "       tblDctos.vrBase,                       "
                + "       tblDctos.vrPago,                       "
                + "       tblDctos.vrIva,                        "
                + "       tblDctos.vrImpoconsumo,                "
                + "       tblDctos.vrRteFuente,                  "
                + "       tblDctos.vrDsctoFcro,                  "
                + "       tblDctos.vrRteIva,                     "
                + "       tblDctos.vrRteIca,                     "
                + "       tblDctos.fechaDcto,                    "
                + "       tblDctos.diasPlazo,                    "
                + "       tblDctos.idDctoNitCC                   "
                + "FROM   tblDctos                               "
                + "WHERE  tblDctos.idLocal           =           "
                + xIdLocal + "                                   "
                + "AND   tblDctos.idCliente          =          '"
                + this.getIdCliente() + "'                       "
                + "AND    tblDctos.idTipoOrden       =           "
                + xIdTipoOrden + "                               "
                + "AND    tblDctos.idTipoOrdenCruce  = 0         "
                + "AND    tblDctos.idDctoCruce       =           "
                + xIdDcto + "                                    "
                + "AND    EXISTS (                               "
                + "       SELECT *                               "
                + "       FROM tblDctosOrdenesDetalle            "
                + "       WHERE tblDctosOrdenesDetalle.idLocal = "
                + "                            tblDctos.idLocal  "
                + "       AND tblDctosOrdenesDetalle.idTipoOrden "
                + "                                            = "
                + "                       tblDctos.idTipoOrden   "
                + "      AND  tblDctosOrdenesDetalle.idOrden   = "
                + "                           tblDctos.idOrden ) "
                + "                          )  AS tmpCXC        "
                + "GROUP BY tmpCXC.IDLOCAL,                      "
                + "         tmpCXC.IDTIPOORDEN,                  "
                + "         tmpCXC.idDcto,                       "
                + "         tmpCXC.idOrden,                      "
                + "         tmpCXC.indicador )   AS tmpSaldo     "
                + "GROUP BY tmpSaldo.idLocal,                    "
                + "         tmpSaldo.IDTIPOORDEN,                "
                + "         tmpSaldo.idDcto,                     "
                + "         tmpSaldo.fechaDcto,                  "
                + "         tmpSaldo.fechaVcto,                  "
                + "         tmpSaldo.diasVcto,                   "
                + "         tmpSaldo.vrSaldo,                    "
                + "         tmpSaldo.vrRteFuente,                "
                + "         tmpSaldo.indicador                   "
                + "ORDER BY tmpSaldo.fechaDcto                   ";

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
                fachadaBean.setIdTipoOrden(rs.getInt("IDTIPOORDEN"));
                fachadaBean.setIdDcto(rs.getInt("idDcto"));
                fachadaBean.setIdOrden(rs.getInt("idOrden"));
                fachadaBean.setFechaDcto(
                        rs.getString("fechaDcto").substring(0, 11));
                fachadaBean.setFechaVcto(
                        rs.getString("fechaVcto").substring(0, 11));
                fachadaBean.setDiasMora(rs.getInt("diasMora"));
                fachadaBean.setVrSaldo(rs.getDouble("vrSaldo"));
                fachadaBean.setVrRteFuente(rs.getDouble("vrRteFuente"));
                fachadaBean.setIndicador(rs.getInt("indicador"));
                fachadaBean.setIdDctoNitCC(rs.getString("idDctoNitCC"));

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

    // listaCuentaDetalladoOrdenFCH
    public FachadaDctoBean listaCuentaDetalladoOrdenFCH() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        Connection connection = null;

        //
        FachadaDctoBean fachadaBean = new FachadaDctoBean();

        //
        String selectStr
                = "SELECT tmpSaldo.idLocal,                      "
                + "       tmpSaldo.IDTIPOORDEN,                  "
                + "       tmpSaldo.idDcto,                       "
                + "       tmpSaldo.fechaDcto,                    "
                + "       tmpSaldo.fechaVcto,                    "
                + "       tmpSaldo.diasVcto AS diasMora,         "
                + "       tmpSaldo.vrSaldo,                      "
                + "       tmpSaldo.indicador,                    "
                + "       MAX(tmpSaldo.idDctoNitCC)              "
                + "                             AS idDctoNitCC   "
                + "FROM                                          "
                + "(SELECT tmpCXC.IDLOCAL,                       "
                + "        tmpCXC.IDTIPOORDEN,                   "
                + "        tmpCXC.idDcto,                        "
                + "        tmpCXC.idOrden,                       "
                + "       tmpCXC.indicador,                      "
                + "       MAX(tmpCXC.idDctoNitCC)                "
                + "                             AS idDctoNitCC,  "
                + "       MAX(tmpCXC.diasPlazo) AS diasPlazo,    "
                + "       MIN(tmpCXC.fechaDcto) AS fechaDcto,    "
                + "       MIN(tmpCXC.fechaDcto) +                "
                + "       MAX(tmpCXC.diasPlazo) AS fechaVcto,    "
                + "       DATEDIFF(DAY,  MIN(tmpCXC.fechaDcto) + "
                + "             MAX(tmpCXC.diasPlazo),GETDATE()) "
                + "                             AS diasVcto,     "
                + "       SUM(tmpCXC.vrBase) AS vrBase,          "
                + "       SUM(tmpCXC.vrPago) AS vrPago,          "
                + "       SUM(tmpCXC.vrIva) AS vrIva,            "
                + "       SUM(tmpCXC.vrImpoconsumo)              "
                + "                          AS vrImpoconsumo,   "
                + "       SUM(tmpCXC.vrRteFuente) AS vrRteFuente,"
                + "       SUM(tmpCXC.vrDsctoFcro) AS vrDsctoFcro,"
                + "       SUM(tmpCXC.vrRteIva) AS vrRteIva,      "
                + "       SUM(tmpCXC.vrRteIca) AS vrRteIca,      "
                + "       ( SUM(tmpCXC.vrBase)               +   "
                + "                SUM(tmpCXC.vrIva)         +   "
                + "                SUM(tmpCXC.vrImpoconsumo) -   "
                + "                SUM(tmpCXC.vrPago)        -   "
                + "                SUM(tmpCXC.vrRteFuente)   -   "
                + "                SUM(tmpCXC.vrDsctoFcro)   -   "
                + "                SUM(tmpCXC.vrRteIva)      -   "
                + "         SUM(tmpCXC.vrRteIca) ) AS vrSaldo    "
                + "FROM                                          "
                + "(SELECT tblDctos.idLocalCruce                 "
                + "                      AS idLocal,             "
                + "       tblDctos.idTipoOrdenCruce              "
                + "                  AS idTipoOrden,             "
                + "       tblDctos.idDctoCruce                   "
                + "                      AS idDcto,              "
                + "       tblDctos.idOrdenCruce AS idOrden,      "
                + "       tblDctos.indicador,                    "
                + "       tblDctos.idCliente,                    "
                + "       tblDctos.vrBase,                       "
                + "       tblDctos.vrPago,                       "
                + "       tblDctos.vrIva,                        "
                + "       tblDctos.vrImpoconsumo,                "
                + "       tblDctos.vrRteFuente,                  "
                + "       tblDctos.vrDsctoFcro,                  "
                + "       tblDctos.vrRteIva,                     "
                + "       tblDctos.vrRteIca,                     "
                + "       tblDctos.fechaDcto,                    "
                + "       tblDctos.diasPlazo,                    "
                + "       tblDctos.idDctoNitCC                   "
                + "FROM   tblDctos                               "
                + "WHERE  tblDctos.idLocal           =           "
                + this.getIdLocal() + "                          "
                + "AND   tblDctos.idCliente          =          '"
                + this.getIdCliente() + "'                       "
                + "AND    tblDctos.idTipoOrdenCruce  =           "
                + this.getIdTipoOrden() + "                      "
                + "AND    tblDctos.idDctoCruce       =           "
                + this.getIdDcto() + "                           "
                + "AND    EXISTS (                               "
                + "       SELECT *                               "
                + "       FROM tblDctosOrdenesDetalle            "
                + "       WHERE tblDctosOrdenesDetalle.idLocal = "
                + "                            tblDctos.idLocal  "
                + "       AND tblDctosOrdenesDetalle.idTipoOrden "
                + "                                            = "
                + "                       tblDctos.idTipoOrden   "
                + "      AND  tblDctosOrdenesDetalle.idOrden   = "
                + "                           tblDctos.idOrden ) "
                + "UNION                                         "
                + "SELECT tblDctos.idLocal,                      "
                + "       tblDctos.idTipoOrden,                  "
                + "       tblDctos.idDcto,                       "
                + "       tblDctos.idOrden,                      "
                + "       tblDctos.indicador,                    "
                + "       tblDctos.idCliente,                    "
                + "       tblDctos.vrBase,                       "
                + "       tblDctos.vrPago,                       "
                + "       tblDctos.vrIva,                        "
                + "       tblDctos.vrImpoconsumo,                "
                + "       tblDctos.vrRteFuente,                  "
                + "       tblDctos.vrDsctoFcro,                  "
                + "       tblDctos.vrRteIva,                     "
                + "       tblDctos.vrRteIca,                     "
                + "       tblDctos.fechaDcto,                    "
                + "       tblDctos.diasPlazo,                    "
                + "       tblDctos.idDctoNitCC                   "
                + "FROM   tblDctos                               "
                + "WHERE  tblDctos.idLocal           =           "
                + this.getIdLocal() + "                          "
                + "AND   tblDctos.idCliente          =          '"
                + this.getIdCliente() + "'                       "
                + "AND    tblDctos.idTipoOrden       =           "
                + this.getIdTipoOrden() + "                      "
                + "AND    tblDctos.idDcto            =           "
                + this.getIdDcto() + "                           "
                + "AND    tblDctos.idDctoCruce       = 0         "
                + "AND    EXISTS (                               "
                + "       SELECT *                               "
                + "       FROM tblDctosOrdenesDetalle            "
                + "       WHERE tblDctosOrdenesDetalle.idLocal = "
                + "                            tblDctos.idLocal  "
                + "       AND tblDctosOrdenesDetalle.idTipoOrden "
                + "                                            = "
                + "                       tblDctos.idTipoOrden   "
                + "      AND  tblDctosOrdenesDetalle.idOrden   = "
                + "                           tblDctos.idOrden ) "
                + "UNION                                         "
                + "SELECT tblDctos.idLocal,                      "
                + "       tblDctos.idTipoOrden,                  "
                + "       tblDctos.idDctoCruce                   "
                + "                      AS idDcto,              "
                + "       tblDctos.idOrdenCruce AS idOrden,      "
                + "       tblDctos.indicador,                    "
                + "       tblDctos.idCliente,                    "
                + "       tblDctos.vrBase,                       "
                + "       tblDctos.vrPago,                       "
                + "       tblDctos.vrIva,                        "
                + "       tblDctos.vrImpoconsumo,                "
                + "       tblDctos.vrRteFuente,                  "
                + "       tblDctos.vrDsctoFcro,                  "
                + "       tblDctos.vrRteIva,                     "
                + "       tblDctos.vrRteIca,                     "
                + "       tblDctos.fechaDcto,                    "
                + "       tblDctos.diasPlazo,                    "
                + "       tblDctos.idDctoNitCC                   "
                + "FROM   tblDctos                               "
                + "WHERE  tblDctos.idLocal           =           "
                + this.getIdLocal() + "                          "
                + "AND   tblDctos.idCliente          =          '"
                + this.getIdCliente() + "'                       "
                + "AND    tblDctos.idTipoOrden       =           "
                + this.getIdTipoOrden() + "                      "
                + "AND    tblDctos.idTipoOrdenCruce  = 0         "
                + "AND    tblDctos.idDctoCruce       =           "
                + this.getIdDcto() + "                           "
                + "AND    EXISTS (                               "
                + "       SELECT *                               "
                + "       FROM tblDctosOrdenesDetalle            "
                + "       WHERE tblDctosOrdenesDetalle.idLocal = "
                + "                            tblDctos.idLocal  "
                + "       AND tblDctosOrdenesDetalle.idTipoOrden "
                + "                                            = "
                + "                       tblDctos.idTipoOrden   "
                + "      AND  tblDctosOrdenesDetalle.idOrden   = "
                + "                           tblDctos.idOrden ) "
                + "                          )  AS tmpCXC        "
                + "GROUP BY tmpCXC.IDLOCAL,                      "
                + "         tmpCXC.IDTIPOORDEN,                  "
                + "         tmpCXC.idDcto,                       "
                + "         tmpCXC.idOrden,                      "
                + "         tmpCXC.indicador                     "
                + "HAVING ( SUM(tmpCXC.vrBase)        +          "
                + "         SUM(tmpCXC.vrIva)         +          "
                + "         SUM(tmpCXC.vrImpoconsumo) -          "
                + "         SUM(tmpCXC.vrPago)        -          "
                + "         SUM(tmpCXC.vrRteFuente)   -          "
                + "         SUM(tmpCXC.vrDsctoFcro)   -          "
                + "         SUM(tmpCXC.vrRteIva)      -          "
                + "         SUM(tmpCXC.vrRteIca) ) > 1 )         "
                + "                              AS tmpSaldo     "
                + "GROUP BY tmpSaldo.idLocal,                    "
                + "         tmpSaldo.IDTIPOORDEN,                "
                + "         tmpSaldo.idDcto,                     "
                + "         tmpSaldo.fechaDcto,                  "
                + "         tmpSaldo.fechaVcto,                  "
                + "         tmpSaldo.diasVcto,                   "
                + "         tmpSaldo.vrSaldo,                    "
                + "         tmpSaldo.indicador                   "
                + "ORDER BY tmpSaldo.fechaDcto                   ";

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
                fachadaBean.setIdTipoOrden(rs.getInt("IDTIPOORDEN"));
                fachadaBean.setIdDcto(rs.getInt("idDcto"));
                fachadaBean.setFechaDcto(
                        rs.getString("fechaDcto").substring(0, 11));
                fachadaBean.setFechaVcto(
                        rs.getString("fechaVcto").substring(0, 11));
                fachadaBean.setDiasMora(rs.getInt("diasMora"));
                fachadaBean.setVrSaldo(rs.getInt("vrSaldo"));
                fachadaBean.setIndicador(rs.getInt("indicador"));
                fachadaBean.setIdDctoNitCC(rs.getString("idDctoNitCC"));

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

    // Metodo
    public Collection listaDetallado() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        //
        Collection list = new ArrayList();

        Connection connection = null;
        int xCero = 0;

        //
        String selectStr
                = "SELECT tmpSaldo.idLocal,                      "
                + "       tmpSaldo.IDTIPOORDEN,                  "
                + "       tmpSaldo.idDcto,                       "
                + "       tmpSaldo.fechaDcto,                    "
                + "       tmpSaldo.fechaVcto,                    "
                + "       tmpSaldo.diasVcto AS diasMora,         "
                + "       tmpSaldo.vrSaldo,                      "
                + "       tmpSaldo.indicador,                    "
                + "       MAX(tmpSaldo.idDctoNitCC)              "
                + "                              AS idDctoNitCC  "
                + "FROM                                          "
                + "(SELECT tmpCXC.IDLOCAL,                       "
                + "        tmpCXC.IDTIPOORDEN,                   "
                + "        tmpCXC.idDcto,                        "
                + "        tmpCXC.idOrden,                       "
                + "       tmpCXC.indicador,                      "
                + "       MAX(tmpCXC.idDctoNitCC) AS idDctoNitCC,"
                + "       MAX(tmpCXC.diasPlazo) AS diasPlazo,    "
                + "       MIN(tmpCXC.fechaDcto) AS fechaDcto,    "
                + "       MIN(tmpCXC.fechaDcto) +                "
                + "       MAX(tmpCXC.diasPlazo) AS fechaVcto,    "
                + "       DATEDIFF(DAY,  MIN(tmpCXC.fechaDcto) + "
                + "             MAX(tmpCXC.diasPlazo),GETDATE()) "
                + "                             AS diasVcto,     "
                + "       SUM(tmpCXC.vrBase) AS vrBase,          "
                + "       SUM(tmpCXC.vrPago) AS vrPago,          "
                + "       SUM(tmpCXC.vrIva) AS vrIva,            "
                + "       SUM(tmpCXC.vrImpoconsumo)              "
                + "                          AS vrImpoconsumo,   "
                + "       SUM(tmpCXC.vrRteFuente) AS vrRteFuente,"
                + "       SUM(tmpCXC.vrDsctoFcro) AS vrDsctoFcro,"
                + "       SUM(tmpCXC.vrRteIva) AS vrRteIva,      "
                + "       SUM(tmpCXC.vrRteIca) AS vrRteIca,      "
                + "       ( SUM(tmpCXC.vrBase)               +   "
                + "                SUM(tmpCXC.vrIva)         +   "
                + "                SUM(tmpCXC.vrImpoconsumo) -   "
                + "                SUM(tmpCXC.vrPago)        -   "
                + "                SUM(tmpCXC.vrRteFuente)   -   "
                + "                SUM(tmpCXC.vrDsctoFcro)   -   "
                + "                SUM(tmpCXC.vrRteIva)      -   "
                + "         SUM(tmpCXC.vrRteIca) ) AS vrSaldo    "
                + "FROM                                          "
                + "(SELECT tblDctos.idLocalCruce                 "
                + "                      AS idLocal,             "
                + "       tblDctos.idTipoOrdenCruce              "
                + "                  AS idTipoOrden,             "
                + "       tblDctos.idDctoCruce                   "
                + "                      AS idDcto,              "
                + "       tblDctos.idOrdenCruce AS idOrden,      "
                + "       tblDctos.indicador,                    "
                + "       tblDctos.idCliente,                    "
                + "       tblDctos.vrBase,                       "
                + "       tblDctos.vrPago,                       "
                + "       tblDctos.vrIva,                        "
                + "       tblDctos.vrImpoconsumo,                "
                + "       tblDctos.vrRteFuente,                  "
                + "       tblDctos.vrDsctoFcro,                  "
                + "       tblDctos.vrRteIva,                     "
                + "       tblDctos.vrRteIca,                     "
                + "       tblDctos.fechaDcto,                    "
                + "       tblDctos.diasPlazo,                    "
                + "       tblDctos.idDctoNitCC                   "
                + "FROM   tblDctos                               "
                + "WHERE  tblDctos.idLocal           =           "
                + getIdLocal() + "                               "
                + "AND   tblDctos.idCliente          =          '"
                + getIdCliente() + "'                            "
                + "AND    tblDctos.idTipoOrdenCruce  =           "
                + getIdTipoOrden() + "                           "
                + "AND    tblDctos.idDctoCruce      != 0         "
                + "AND    EXISTS (                               "
                + "   SELECT *                                   "
                + "   FROM tblDctosOrdenesDetalle                "
                + "   WHERE tblDctosOrdenesDetalle.idLocal     = "
                + "                            tblDctos.idLocal  "
                + "   AND   tblDctosOrdenesDetalle.idTipoOrden = "
                + "                       tblDctos.idTipoOrden   "
                + "   AND   tblDctosOrdenesDetalle.idOrden     = "
                + "                           tblDctos.idOrden ) "
                + "UNION                                         "
                + "SELECT tblDctos.idLocal,                      "
                + "       tblDctos.idTipoOrden,                  "
                + "       tblDctos.idDcto,                       "
                + "       tblDctos.idOrden,                      "
                + "       tblDctos.indicador,                    "
                + "       tblDctos.idCliente,                    "
                + "       tblDctos.vrBase,                       "
                + "       tblDctos.vrPago,                       "
                + "       tblDctos.vrIva,                        "
                + "       tblDctos.vrImpoconsumo,                "
                + "       tblDctos.vrRteFuente,                  "
                + "       tblDctos.vrDsctoFcro,                  "
                + "       tblDctos.vrRteIva,                     "
                + "       tblDctos.vrRteIca,                     "
                + "       tblDctos.fechaDcto,                    "
                + "       tblDctos.diasPlazo,                    "
                + "       tblDctos.idDctoNitCC                   "
                + "FROM   tblDctos                               "
                + "WHERE  tblDctos.idLocal           =           "
                + getIdLocal() + "                               "
                + "AND   tblDctos.idCliente          =          '"
                + getIdCliente() + "'                            "
                + "AND    tblDctos.idTipoOrden       =           "
                + getIdTipoOrden() + "                           "
                + "AND    tblDctos.idDctoCruce       = 0         "
                + "AND    EXISTS (                               "
                + "   SELECT *                                   "
                + "   FROM tblDctosOrdenesDetalle                "
                + "   WHERE tblDctosOrdenesDetalle.idLocal     = "
                + "                            tblDctos.idLocal  "
                + "   AND   tblDctosOrdenesDetalle.idTipoOrden = "
                + "                       tblDctos.idTipoOrden   "
                + "   AND   tblDctosOrdenesDetalle.idOrden     = "
                + "                            tblDctos.idOrden )"
                + "UNION                                         "
                + "SELECT tblDctos.idLocal,                      "
                + "       tblDctos.idTipoOrden,                  "
                + "       tblDctos.idDctoCruce                   "
                + "                      AS idDcto,              "
                + "       tblDctos.idOrdenCruce AS idOrden,      "
                + "       tblDctos.indicador,                    "
                + "       tblDctos.idCliente,                    "
                + "       tblDctos.vrBase,                       "
                + "       tblDctos.vrPago,                       "
                + "       tblDctos.vrIva,                        "
                + "       tblDctos.vrImpoconsumo,                "
                + "       tblDctos.vrRteFuente,                  "
                + "       tblDctos.vrDsctoFcro,                  "
                + "       tblDctos.vrRteIva,                     "
                + "       tblDctos.vrRteIca,                     "
                + "       tblDctos.fechaDcto,                    "
                + "       tblDctos.diasPlazo,                    "
                + "       tblDctos.idDctoNitCC                   "
                + "FROM   tblDctos                               "
                + "WHERE  tblDctos.idLocal           =           "
                + getIdLocal() + "                               "
                + "AND   tblDctos.idCliente          =          '"
                + getIdCliente() + "'                            "
                + "AND    tblDctos.idTipoOrdenCruce  = 0         "
                + "AND    tblDctos.idTipoOrden       =           "
                + getIdTipoOrden() + "                           "
                + "AND    tblDctos.idDctoCruce      != 0         "
                + "AND    EXISTS (                               "
                + "   SELECT *                                   "
                + "   FROM tblDctosOrdenesDetalle                "
                + "   WHERE tblDctosOrdenesDetalle.idLocal     = "
                + "                            tblDctos.idLocal  "
                + "   AND   tblDctosOrdenesDetalle.idTipoOrden = "
                + "                       tblDctos.idTipoOrden   "
                + "   AND   tblDctosOrdenesDetalle.idOrden     = "
                + "                           tblDctos.idOrden ) "
                + "                          )  AS tmpCXC        "
                + "GROUP BY tmpCXC.IDLOCAL,                      "
                + "         tmpCXC.IDTIPOORDEN,                  "
                + "         tmpCXC.idDcto,                       "
                + "         tmpCXC.idOrden,                      "
                + "         tmpCXC.indicador                     "
                + "HAVING ( SUM(tmpCXC.vrBase)        +          "
                + "         SUM(tmpCXC.vrIva)         +          "
                + "         SUM(tmpCXC.vrImpoconsumo) -          "
                + "         SUM(tmpCXC.vrPago)        -          "
                + "         SUM(tmpCXC.vrRteFuente)   -          "
                + "         SUM(tmpCXC.vrDsctoFcro)   -          "
                + "         SUM(tmpCXC.vrRteIva)      -          "
                + "         SUM(tmpCXC.vrRteIca) ) > 1 )         "
                + "                              AS tmpSaldo     "
                + "GROUP BY tmpSaldo.idLocal,                    "
                + "         tmpSaldo.IDTIPOORDEN,                "
                + "         tmpSaldo.idDcto,                     "
                + "         tmpSaldo.fechaDcto,                  "
                + "         tmpSaldo.fechaVcto,                  "
                + "         tmpSaldo.diasVcto,                   "
                + "         tmpSaldo.vrSaldo,                    "
                + "         tmpSaldo.indicador                   ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaDctoBean fachadaBean;

            while (rs.next()) {

                fachadaBean = new FachadaDctoBean();

                //
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("IDTIPOORDEN"));
                fachadaBean.setIdDcto(rs.getInt("idDcto"));
                fachadaBean.setFechaDcto(
                        rs.getString("fechaDcto").substring(0, 11));
                fachadaBean.setFechaVcto(
                        rs.getString("fechaVcto").substring(0, 11));
                fachadaBean.setDiasMora(rs.getInt("diasMora"));
                fachadaBean.setVrSaldo(rs.getInt("vrSaldo"));
                fachadaBean.setIndicador(rs.getInt("indicador"));
                fachadaBean.setIdDctoNitCC(rs.getString("idDctoNitCC"));

                //
                list.add(new ColaboraDctoBean(fachadaBean.getIdLocal(),
                        fachadaBean.getIdTipoOrden(),
                        fachadaBean.getIdDcto(),
                        fachadaBean.getFechaDcto(),
                        fachadaBean.getFechaVcto(),
                        fachadaBean.getDiasMora(),
                        fachadaBean.getVrSaldo(),
                        fachadaBean.getIndicador(),
                        fachadaBean.getIdDctoNitCC(),
                        "",
                        0,
                        0,
                        "",
                        "",
                        0,
                        0,
                        0,
                        0,
                        "",
                        0,
                        0,
                        0));

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
    public Collection listaHistoriaDetallado() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        //
        Collection list = new ArrayList();

        Connection connection = null;

        //
        String selectStr
                = "SELECT tmpSaldo.idLocal,                      "
                + "       tmpSaldo.IDTIPOORDEN,                  "
                + "       tmpSaldo.idDcto,                       "
                + "       tmpSaldo.fechaDcto,                    "
                + "       tmpSaldo.fechaVcto,                    "
                + "       tmpSaldo.diasVcto AS diasMora,         "
                + "       tmpSaldo.vrSaldo,                      "
                + "       tmpSaldo.indicador,                    "
                + "       tmpSaldo.idDctoNitCC                   "
                + "FROM                                          "
                + "(SELECT tmpCXC.IDLOCAL,                       "
                + "        tmpCXC.IDTIPOORDEN,                   "
                + "        tmpCXC.idDcto,                        "
                + "       tmpCXC.indicador,                      "
                + "       MAX(tmpCXC.idDctoNitCC) AS idDctoNitCC,"
                + "       MAX(tmpCXC.diasPlazo) AS diasPlazo,    "
                + "       MIN(tmpCXC.fechaDcto) AS fechaDcto,    "
                + "       MIN(tmpCXC.fechaDcto) +                "
                + "       MAX(tmpCXC.diasPlazo) AS fechaVcto,    "
                + "       DATEDIFF(DAY,  MIN(tmpCXC.fechaDcto) + "
                + "             MAX(tmpCXC.diasPlazo),GETDATE()) "
                + "                             AS diasVcto,     "
                + "       SUM(tmpCXC.vrBase) AS vrBase,          "
                + "       SUM(tmpCXC.vrPago) AS vrPago,          "
                + "       SUM(tmpCXC.vrIva) AS vrIva,            "
                + "       SUM(tmpCXC.vrRteFuente) AS vrRteFuente,"
                + "       SUM(tmpCXC.vrDsctoFcro) AS vrDsctoFcro,"
                + "       SUM(tmpCXC.vrRteIva) AS vrRteIva,      "
                + "       SUM(tmpCXC.vrRteIca) AS vrRteIca,      "
                + "       SUM(tmpCXC.vrImpoconsumo)              "
                + "                          AS vrImpoconsumo,   "
                + "       ( SUM(tmpCXC.vrBase)               +   "
                + "                SUM(tmpCXC.vrIva)         +   "
                + "                SUM(tmpCXC.vrImpoconsumo) -   "
                + "                SUM(tmpCXC.vrPago)        -   "
                + "                SUM(tmpCXC.vrRteFuente)   -   "
                + "                SUM(tmpCXC.vrDsctoFcro)   -   "
                + "                SUM(tmpCXC.vrRteIva)      -   "
                + "         SUM(tmpCXC.vrRteIca) ) AS vrSaldo    "
                + "FROM                                          "
                + "(SELECT tblDctos.idLocalCruce                 "
                + "                      AS idLocal,             "
                + "       tblDctos.idTipoOrdenCruce              "
                + "                  AS idTipoOrden,             "
                + "       tblDctos.idDctoCruce                   "
                + "                      AS idDcto,              "
                + "       tblDctos.indicador,                    "
                + "       tblDctos.idCliente,                    "
                + "       tblDctos.vrBase,                       "
                + "       tblDctos.vrPago,                       "
                + "       tblDctos.vrIva,                        "
                + "       tblDctos.vrImpoconsumo,                "
                + "       tblDctos.vrRteFuente,                  "
                + "       tblDctos.vrDsctoFcro,                  "
                + "       tblDctos.vrRteIva,                     "
                + "       tblDctos.vrRteIca,                     "
                + "       tblDctos.fechaDcto,                    "
                + "       tblDctos.diasPlazo,                    "
                + "       tblDctos.idDctoNitCC                   "
                + "FROM   tblDctos                               "
                + "WHERE  tblDctos.idLocal           =           "
                + getIdLocal() + "                               "
                + "AND   tblDctos.idCliente          =          '"
                + getIdCliente() + "'                            "
                + "AND    tblDctos.idTipoOrdenCruce  IN         ("
                + getIdTipoOrdenCadena() + ")                    "
                + "AND    tblDctos.idDctoCruce      != 0         "
                + "AND    EXISTS ( SELECT *                                   "
                + "                FROM tblDctosOrdenesDetalle                "
                + "                WHERE tblDctosOrdenesDetalle.idLocal     = "
                + "                                         tblDctos.idLocal  "
                + "                AND   tblDctosOrdenesDetalle.idTipoOrden = "
                + "                                    tblDctos.idTipoOrden   "
                + "                AND   tblDctosOrdenesDetalle.idOrden     = "
                + "                                        tblDctos.idOrden ) "
                + "UNION                                         "
                + "SELECT tblDctos.idLocal,                      "
                + "       tblDctos.idTipoOrden,                  "
                + "       tblDctos.idDcto,                       "
                + "       tblDctos.indicador,                    "
                + "       tblDctos.idCliente,                    "
                + "       tblDctos.vrBase,                       "
                + "       tblDctos.vrPago,                       "
                + "       tblDctos.vrIva,                        "
                + "       tblDctos.vrImpoconsumo,                "
                + "       tblDctos.vrRteFuente,                  "
                + "       tblDctos.vrDsctoFcro,                  "
                + "       tblDctos.vrRteIva,                     "
                + "       tblDctos.vrRteIca,                     "
                + "       tblDctos.fechaDcto,                    "
                + "       tblDctos.diasPlazo,                    "
                + "       tblDctos.idDctoNitCC                   "
                + "FROM   tblDctos                               "
                + "WHERE  tblDctos.idLocal           =           "
                + getIdLocal() + "                               "
                + "AND   tblDctos.idCliente          =          '"
                + getIdCliente() + "'                            "
                + "AND    tblDctos.idTipoOrden       IN         ("
                + getIdTipoOrdenCadena() + ")                    "
                + "AND    tblDctos.idDctoCruce       = 0         "
                + "AND    EXISTS ( SELECT *                                   "
                + "                FROM tblDctosOrdenesDetalle                "
                + "                WHERE tblDctosOrdenesDetalle.idLocal     = "
                + "                                         tblDctos.idLocal  "
                + "                AND   tblDctosOrdenesDetalle.idTipoOrden = "
                + "                                    tblDctos.idTipoOrden   "
                + "                AND   tblDctosOrdenesDetalle.idOrden     = "
                + "                                        tblDctos.idOrden )"
                + "UNION                                         "
                + "SELECT tblDctos.idLocal,                      "
                + "       tblDctos.idTipoOrden,                  "
                + "       tblDctos.idDctoCruce AS idDcto,        "
                + "       tblDctos.indicador,                    "
                + "       tblDctos.idCliente,                    "
                + "       tblDctos.vrBase,                       "
                + "       tblDctos.vrPago,                       "
                + "       tblDctos.vrIva,                        "
                + "       tblDctos.vrImpoconsumo,                "
                + "       tblDctos.vrRteFuente,                  "
                + "       tblDctos.vrDsctoFcro,                  "
                + "       tblDctos.vrRteIva,                     "
                + "       tblDctos.vrRteIca,                     "
                + "       tblDctos.fechaDcto,                    "
                + "       tblDctos.diasPlazo,                    "
                + "       tblDctos.idDctoNitCC                   "
                + "FROM   tblDctos                               "
                + "WHERE  tblDctos.idLocal           =           "
                + getIdLocal() + "                               "
                + "AND   tblDctos.idCliente          =          '"
                + getIdCliente() + "'                            "
                + "AND    tblDctos.idTipoOrdenCruce  = 0         "
                + "AND    tblDctos.idTipoOrden       IN         ("
                + getIdTipoOrdenCadena() + ")                    "
                + "AND    tblDctos.idDctoCruce      != 0         "
                + "AND    EXISTS ( SELECT *                                   "
                + "                FROM tblDctosOrdenesDetalle                "
                + "                WHERE tblDctosOrdenesDetalle.idLocal     = "
                + "                                         tblDctos.idLocal  "
                + "                AND   tblDctosOrdenesDetalle.idTipoOrden = "
                + "                                    tblDctos.idTipoOrden   "
                + "                AND   tblDctosOrdenesDetalle.idOrden     = "
                + "                                        tblDctos.idOrden ) "
                + "                          )  AS tmpCXC        "
                + "GROUP BY tmpCXC.IDLOCAL,                      "
                + "         tmpCXC.IDTIPOORDEN,                  "
                + "         tmpCXC.idDcto,                       "
                + "         tmpCXC.indicador  )                  "
                + "                              AS tmpSaldo     "
                + "WHERE tmpSaldo.fechaDcto BETWEEN             '"
                + getFechaInicialSqlServer() + "' AND           '"
                + getFechaFinalSqlServer() + "'                  "
                + "AND   tmpSaldo.indicador BETWEEN              "
                + getIndicadorInicial() + "   AND                "
                + getIndicadorFinal() + "                        "
                + "GROUP BY tmpSaldo.idLocal,                    "
                + "         tmpSaldo.IDTIPOORDEN,                "
                + "         tmpSaldo.idDcto,                     "
                + "         tmpSaldo.fechaDcto,                  "
                + "         tmpSaldo.fechaVcto,                  "
                + "         tmpSaldo.diasVcto,                   "
                + "         tmpSaldo.vrSaldo,                    "
                + "         tmpSaldo.indicador,                  "
                + "         tmpSaldo.idDctoNitCC                 ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaDctoBean fachadaBean;

            while (rs.next()) {

                fachadaBean = new FachadaDctoBean();

                //
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("IDTIPOORDEN"));
                fachadaBean.setIdDcto(rs.getInt("idDcto"));
                fachadaBean.setFechaDcto(
                        rs.getString("fechaDcto").substring(0, 11));
                fachadaBean.setFechaVcto(
                        rs.getString("fechaVcto").substring(0, 11));
                fachadaBean.setDiasMora(rs.getInt("diasMora"));
                fachadaBean.setVrSaldo(rs.getInt("vrSaldo"));
                fachadaBean.setIndicador(rs.getInt("indicador"));
                fachadaBean.setIdDctoNitCC(rs.getString("idDctoNitCC"));

                //
                list.add(new ColaboraDctoBean(fachadaBean.getIdLocal(),
                        fachadaBean.getIdTipoOrden(),
                        fachadaBean.getIdDcto(),
                        fachadaBean.getFechaDcto(),
                        fachadaBean.getFechaVcto(),
                        fachadaBean.getDiasMora(),
                        fachadaBean.getVrSaldo(),
                        fachadaBean.getIndicador(),
                        fachadaBean.getIdDctoNitCC(),
                        "",
                        0,
                        0,
                        "",
                        "",
                        0,
                        0,
                        0,
                        0,
                        "",
                        0,
                        0,
                        0));

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

    // listaHistoriaDetalle
    public Vector listaHistoriaDetalle() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        //
        Vector contenedor = new Vector();

        Connection connection = null;

        //
        String selectStr
                = "SELECT tblDctos.IDLOCAL,                      "
                + "       tblDctos.IDTIPOORDEN,                  "
                + "       tblDctos.IDORDEN,                      "
                + "       tblDctos.idDcto,                       "
                + "       tblDctos.indicador,                    "
                + "       tblDctos.fechaDcto,                    "
                + "       tblDctos.vrBase,                       "
                + "       tblDctos.vrIva,                        "
                + "       tblDctos.vrImpoconsumo,                "
                + "       tblDctos.vrRteFuente,                  "
                + "       tblDctos.vrRteIva,                     "
                + "       tblDctos.vrRteIca,                     "
                + "       tblDctos.idDctoNitCC,                  "
                + "       DATEDIFF(DAY,tblDctos.fechaDcto      + "
                + "             tblDctos.diasPlazo ,GETDATE())   "
                + "                               AS diasMora,   "
                + "       (SELECT COUNT(*) AS numeroArticulo     "
                + "        FROM   tblDctosOrdenes                "
                + "        INNER JOIN tblDctosOrdenesDetalle     "
                + "        ON tblDctosOrdenes.IDLOCAL         =  "
                + "              tblDctosOrdenesDetalle.IDLOCAL  "
                + "        AND tblDctosOrdenes.IDTIPOORDEN    =  "
                + "          tblDctosOrdenesDetalle.IDTIPOORDEN  "
                + "        AND tblDctosOrdenes.IDORDEN        =  "
                + "              tblDctosOrdenesDetalle.IDORDEN  "
                + "        WHERE tblDctosOrdenes.IDLOCAL      =  "
                + "                            tblDctos.IDLOCAL  "
                + "        AND   tblDctosOrdenes.IDTIPOORDEN  =  "
                + "                        tblDctos.IDTIPOORDEN  "
                + "        AND   tblDctosOrdenes.IDORDEN      =  "
                + "                           tblDctos.IDORDEN)  "
                + "                           AS numeroArticulo, "
                + "        ( SELECT tblTipoOrden.nombreTipoOrden "
                + "          FROM tblTipoOrden                   "
                + "          WHERE tblTipoOrden.idTipoOrden   =  "
                + "                        tblDctos.idTipoOrden) "
                + "                           AS nombreTipoOrden "
                + "FROM [BDCommerce].[dbo].[tblDctos]            "
                + "WHERE tblDctos.idLocal                     =  "
                + getIdLocal() + "                               "
                + "AND   tblDctos.idTipoOrden                 IN "
                + "  (" + getIdTipoOrdenCadena() + ")            "
                + "AND   tblDctos.idCliente                   = '"
                + getIdCliente() + "'                            "
                + "AND   tblDctos.fechaDcto BETWEEN             '"
                + getFechaInicialSqlServer() + "'     AND       '"
                + getFechaFinalSqlServer() + "'                  "
                + "AND   tblDctos.indicador BETWEEN              "
                + getIndicadorInicial() + "   AND                "
                + getIndicadorFinal() + "                        "
                + "ORDER BY tblDctos.fechaDcto DESC              ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaDctoBean fachadaBean;

            while (rs.next()) {

                fachadaBean = new FachadaDctoBean();

                //
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("IDTIPOORDEN"));
                fachadaBean.setIdOrden(rs.getInt("IDORDEN"));
                fachadaBean.setIdDcto(rs.getInt("idDcto"));
                fachadaBean.setIndicador(rs.getInt("indicador"));
                fachadaBean.setFechaDcto(rs.getString("fechaDcto"));
                fachadaBean.setVrBase(rs.getDouble("vrBase"));
                fachadaBean.setVrIva(rs.getDouble("vrIva"));
                fachadaBean.setVrImpoconsumo(rs.getDouble("vrImpoconsumo"));
                fachadaBean.setVrRteFuente(rs.getDouble("vrRteFuente"));
                fachadaBean.setVrRteIva(rs.getDouble("vrRteIva"));
                fachadaBean.setVrRteIca(rs.getDouble("vrRteIca"));
                fachadaBean.setIdDctoNitCC(rs.getString("idDctoNitCC"));
                fachadaBean.setDiasMora(rs.getInt("diasMora"));
                fachadaBean.setNombreTipoOrden(rs.getString("nombreTipoOrden"));

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

    // listaTotalCxC
    public double listaTotalCxC() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        Connection connection = null;

        double xVrSaldo = 0.0;

        String insertStr
                = "SELECT SUM(tblDctos.vrBase  +    "
                + "           tblDctos.vrIva   -    "
                + "        tblDctos.vrRteFuente -   "
                + "        tblDctos.vrRteIva)       "
                + "                      AS vrSaldo "
                + "FROM tblDctos                    "
                + "WHERE tblDctos.idLocal     =     "
                + getIdLocal() + "                  "
                + "AND   tblDctos.idTipoOrden =     "
                + getIdTipoOrden() + "              "
                + "AND   tblDctos.indicador   =     "
                + getIndicador() + "                "
                + "AND   tblDctos.idCliente   =    '"
                + getIdCliente() + "'               ";

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
                xVrSaldo = rs.getDouble("vrSaldo");

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
            return xVrSaldo;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {

            // Cierra la conexion a la base de datos limpiando la statement usada y la
            jdbcAccess.cleanup(connection, sentenciaSQL, null);
            return xVrSaldo;
        }
    }

    // listaVentaAll
    public Collection listaVentaAll(String xFechaDctoInicial,
            String xFechaDctoFinal,
            String xIdTipoOrden,
            String xIndicador) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        //
        Collection list = new ArrayList();

        boolean validaOk = false;
        Connection connection = null;

        //
        String selectStr
                = "SELECT tblDctos.idLocal,                   "
                + "       tblDctos.idTipoOrden,               "
                + "       tblDctos.idDcto,                    "
                + "       tblDctos.fechaDcto,                 "
                + "       tblDctos.indicador,                 "
                + "       tblDctos.idCliente,                 "
                + "       tblDctos.vrBase,                    "
                + "       tblDctos.vrIva,                     "
                + "       tblDctos.nombreTercero,             "
                + "       tblDctos.idDctoNitCC,               "
                + "       tblDctos.fechaDctoNitCC,            "
                + "       tblDctos.idLocalCruce,              "
                + "       tblDctos.idTipoOrdenCruce,          "
                + "       tblDctos.idDctoCruce,               "
                + "       (tblDctos.vrBase      +             "
                + "        tblDctos.vrIva       -             "
                + "        tblDctos.vrRteFuente -             "
                + "        tblDctos.vrRteIva) AS vrFactura,   "
                + "       tblDctos.vrDescuento,               "
                + "       tblDctos.vrRteFuente,               "
                + "       (CASE WHEN                          "
                + "             tblDctos.idTipoNegocio = 1    "
                + "        THEN      'CONTADO'                "
                + "        ELSE      'CREDITO'                "
                + "        END) AS nombreTipoNegocio,         "
                + "       tblDctos.idTipoNegocio,             "
                + "       ( SELECT ctrlUsuarios.aliasUsuario  "
                + "         FROM ctrlUsuarios                 "
                + "         WHERE ctrlUsuarios.idUsuario =    "
                + "                     tblDctos.idVendedor ) "
                + "                           AS aliasUsuario "
                + "FROM   tblDctos                            "
                + "WHERE EXISTS                               "
                + "   ( SELECT *                              "
                + "     FROM   tblDctosOrdenes                "
                + "     INNER JOIN tblDctosOrdenesDetalle     "
                + "     ON tblDctosOrdenes.IDLOCAL      =     "
                + "        tblDctosOrdenesDetalle.IDLOCAL     "
                + "     AND tblDctosOrdenes.IDTIPOORDEN =     "
                + "        tblDctosOrdenesDetalle.IDTIPOORDEN "
                + "     AND tblDctosOrdenes.IDORDEN     =     "
                + "        tblDctosOrdenesDetalle.IDORDEN     "
                + "     WHERE tblDctosOrdenes.IDLOCAL   =     "
                + "                          tblDctos.IDLOCAL "
                + "     AND tblDctosOrdenes.IDTIPOORDEN =     "
                + "                      tblDctos.IDTIPOORDEN "
                + "     AND tblDctosOrdenes.IDORDEN     =     "
                + "                         tblDctos.IDORDEN) "
                + "AND tblDctos.fechaDcto                     "
                + "     BETWEEN '" + xFechaDctoInicial + "'   "
                + "AND '" + xFechaDctoFinal + "'              "
                + "AND tblDctos.idTipoOrden               IN ("
                + xIdTipoOrden + ")                           "
                + "AND tblDctos.indicador                 IN ("
                + xIndicador + ")                             "
                + "ORDER BY tblDctos.fechaDcto,               "
                + "         tblDctos.idTipoOrden,             "
                + "         tblDctos.idDcto                   ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaDctoBean fachadaBean;

            while (rs.next()) {

                //
                fachadaBean = new FachadaDctoBean();

                //
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setIdDcto(rs.getInt("idDcto"));
                fachadaBean.setFechaDcto(rs.getString("fechaDcto"));
                fachadaBean.setIndicador(rs.getInt("indicador"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setVrBase(rs.getDouble("vrBase"));
                fachadaBean.setVrIva(rs.getDouble("vrIva"));
                fachadaBean.setNombreTercero(rs.getString("nombreTercero"));
                fachadaBean.setIdDctoNitCC(rs.getString("idDctoNitCC"));
                fachadaBean.setFechaDctoNitCC(rs.getString("fechaDctoNitCC"));
                fachadaBean.setIdLocalCruce(rs.getInt("idLocalCruce"));
                fachadaBean.setIdTipoOrdenCruce(rs.getInt("idTipoOrdenCruce"));
                fachadaBean.setIdDctoCruce(rs.getInt("idDctoCruce"));
                fachadaBean.setVrFactura(rs.getInt("vrFactura"));
                fachadaBean.setVrDescuento(rs.getInt("vrDescuento"));
                fachadaBean.setVrRteFuente(rs.getInt("vrRteFuente"));
                fachadaBean.setNombreTipoNegocio(
                        rs.getString("nombreTipoNegocio"));
                fachadaBean.setAliasUsuario(rs.getString("aliasUsuario"));

                //
                list.add(new ColaboraDctoBean(fachadaBean.getIdLocal(),
                        fachadaBean.getIdTipoOrden(),
                        fachadaBean.getIdDcto(),
                        fachadaBean.getFechaDcto(),
                        fachadaBean.getIndicador(),
                        fachadaBean.getIdCliente(),
                        fachadaBean.getVrBase(),
                        fachadaBean.getVrIva(),
                        fachadaBean.getNombreTercero(),
                        fachadaBean.getIdDctoNitCC(),
                        fachadaBean.getFechaDctoNitCC(),
                        fachadaBean.getIdLocalCruce(),
                        fachadaBean.getIdTipoOrdenCruce(),
                        fachadaBean.getIdDctoCruce(),
                        fachadaBean.getVrFactura(),
                        fachadaBean.getVrDescuento(),
                        fachadaBean.getVrRteFuente(),
                        fachadaBean.getNombreTipoNegocio(),
                        fachadaBean.getAliasUsuario()));

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

    // listaVentaUn
    public Collection listaVentaUn(String xFechaDctoInicial,
            String xFechaDctoFinal,
            String xIdTipoOrden,
            String xIndicador) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        //
        Collection list = new ArrayList();

        boolean validaOk = false;
        Connection connection = null;

        //
        String selectStr
                = "SELECT tblDctos.idLocal,                   "
                + "       tblDctos.idTipoOrden,               "
                + "       tblDctos.idDcto,                    "
                + "       tblDctos.fechaDcto,                 "
                + "       tblDctos.indicador,                 "
                + "       tblDctos.idCliente,                 "
                + "       tblDctos.vrBase,                    "
                + "       tblDctos.vrIva,                     "
                + "       tblDctos.nombreTercero,             "
                + "       tblDctos.idDctoNitCC,               "
                + "       tblDctos.fechaDctoNitCC,            "
                + "       tblDctos.idLocalCruce,              "
                + "       tblDctos.idTipoOrdenCruce,          "
                + "       tblDctos.idDctoCruce,               "
                + "       (tblDctos.vrBase      +             "
                + "        tblDctos.vrIva       -             "
                + "        tblDctos.vrRteFuente -             "
                + "        tblDctos.vrRteIva)  AS vrFactura,  "
                + "       tblDctos.vrDescuento,               "
                + "       tblDctos.vrRteFuente,               "
                + "       (CASE WHEN                          "
                + "             tblDctos.idTipoNegocio = 1    "
                + "        THEN      'CONTADO'                "
                + "        ELSE      'CREDITO'                "
                + "        END) AS nombreTipoNegocio,         "
                + "       tblDctos.idTipoNegocio,             "
                + "       ( SELECT ctrlUsuarios.aliasUsuario  "
                + "         FROM ctrlUsuarios                 "
                + "         WHERE ctrlUsuarios.idUsuario =    "
                + "                     tblDctos.idVendedor ) "
                + "                           AS aliasUsuario "
                + "FROM   tblDctos                            "
                + "WHERE EXISTS                               "
                + "   ( SELECT *                              "
                + "     FROM   tblDctosOrdenes                "
                + "     INNER JOIN tblDctosOrdenesDetalle     "
                + "     ON tblDctosOrdenes.IDLOCAL      =     "
                + "        tblDctosOrdenesDetalle.IDLOCAL     "
                + "     AND tblDctosOrdenes.IDTIPOORDEN =     "
                + "        tblDctosOrdenesDetalle.IDTIPOORDEN "
                + "     AND tblDctosOrdenes.IDORDEN     =     "
                + "        tblDctosOrdenesDetalle.IDORDEN     "
                + "     WHERE tblDctosOrdenes.IDLOCAL   =     "
                + "                          tblDctos.IDLOCAL "
                + "     AND tblDctosOrdenes.IDTIPOORDEN =     "
                + "                      tblDctos.IDTIPOORDEN "
                + "     AND tblDctosOrdenes.IDORDEN     =     "
                + "                         tblDctos.IDORDEN) "
                + "AND tblDctos.fechaDcto                     "
                + "     BETWEEN '" + xFechaDctoInicial + "'   "
                + "AND '" + xFechaDctoFinal + "'              "
                + "AND tblDctos.idTipoOrden               IN ("
                + xIdTipoOrden + ")                           "
                + "AND tblDctos.indicador                 IN ("
                + xIndicador + ")                             "
                + "AND tblDctos.idVendedor                =   "
                + getIdVendedor() + "                         "
                + "ORDER BY tblDctos.fechaDcto,               "
                + "         tblDctos.idTipoOrden,             "
                + "         tblDctos.idDcto                   ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaDctoBean fachadaBean;

            while (rs.next()) {

                fachadaBean = new FachadaDctoBean();

                //
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setIdDcto(rs.getInt("idDcto"));
                fachadaBean.setFechaDcto(rs.getString("fechaDcto"));
                fachadaBean.setIndicador(rs.getInt("indicador"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setVrBase(rs.getDouble("vrBase"));
                fachadaBean.setVrIva(rs.getDouble("vrIva"));
                fachadaBean.setNombreTercero(rs.getString("nombreTercero"));
                fachadaBean.setIdDctoNitCC(rs.getString("idDctoNitCC"));
                fachadaBean.setFechaDctoNitCC(rs.getString("fechaDctoNitCC"));
                fachadaBean.setIdLocalCruce(rs.getInt("idLocalCruce"));
                fachadaBean.setIdTipoOrdenCruce(rs.getInt("idTipoOrdenCruce"));
                fachadaBean.setIdDctoCruce(rs.getInt("idDctoCruce"));
                fachadaBean.setVrFactura(rs.getInt("vrFactura"));
                fachadaBean.setVrDescuento(rs.getInt("vrDescuento"));
                fachadaBean.setVrRteFuente(rs.getInt("vrRteFuente"));
                fachadaBean.setNombreTipoNegocio(
                        rs.getString("nombreTipoNegocio"));
                fachadaBean.setAliasUsuario(rs.getString("aliasUsuario"));

                //
                list.add(new ColaboraDctoBean(fachadaBean.getIdLocal(),
                        fachadaBean.getIdTipoOrden(),
                        fachadaBean.getIdDcto(),
                        fachadaBean.getFechaDcto(),
                        fachadaBean.getIndicador(),
                        fachadaBean.getIdCliente(),
                        fachadaBean.getVrBase(),
                        fachadaBean.getVrIva(),
                        fachadaBean.getNombreTercero(),
                        fachadaBean.getIdDctoNitCC(),
                        fachadaBean.getFechaDctoNitCC(),
                        fachadaBean.getIdLocalCruce(),
                        fachadaBean.getIdTipoOrdenCruce(),
                        fachadaBean.getIdDctoCruce(),
                        fachadaBean.getVrFactura(),
                        fachadaBean.getVrDescuento(),
                        fachadaBean.getVrRteFuente(),
                        fachadaBean.getNombreTipoNegocio(),
                        fachadaBean.getAliasUsuario()));

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
    public Collection listaCompra(String xFechaDctoInicial,
            String xFechaDctoFinal,
            String xIdTipoOrden,
            String xIndicador) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        //
        Collection list = new ArrayList();

        //
        Connection connection = null;

        //
        String selectStr
                = "SELECT tblDctos.idLocal,                   "
                + "       tblDctos.idTipoOrden,               "
                + "       tblDctos.idDcto,                    "
                + "       tblDctos.fechaDcto,                 "
                + "       tblDctos.indicador,                 "
                + "       tblDctos.idCliente,                 "
                + "       tblDctos.vrBase,                    "
                + "       tblDctos.vrIva,                     "
                + "       tblDctos.vrRteFuente,               "
                + "       tblDctos.nombreTercero,             "
                + "       tblDctos.idDctoNitCC,               "
                + "       tblDctos.fechaDctoNitCC,            "
                + "       tblDctos.idLocalCruce,              "
                + "       tblDctos.idTipoOrdenCruce,          "
                + "       tblDctos.idDctoCruce                "
                + "FROM   tblDctos                            "
                + "WHERE EXISTS                               "
                + "   ( SELECT *                              "
                + "     FROM   tblDctosOrdenes                "
                + "     INNER JOIN tblDctosOrdenesDetalle     "
                + "     ON tblDctosOrdenes.IDLOCAL      =     "
                + "        tblDctosOrdenesDetalle.IDLOCAL     "
                + "     AND tblDctosOrdenes.IDTIPOORDEN =     "
                + "        tblDctosOrdenesDetalle.IDTIPOORDEN "
                + "     AND tblDctosOrdenes.IDORDEN     =     "
                + "        tblDctosOrdenesDetalle.IDORDEN     "
                + "     WHERE tblDctosOrdenes.IDLOCAL   =     "
                + "                          tblDctos.IDLOCAL "
                + "     AND tblDctosOrdenes.IDTIPOORDEN =     "
                + "                      tblDctos.IDTIPOORDEN "
                + "     AND tblDctosOrdenes.IDORDEN     =     "
                + "                         tblDctos.IDORDEN) "
                + "AND tblDctos.fechaDcto                     "
                + "     BETWEEN '" + xFechaDctoInicial + "'   "
                + "AND '" + xFechaDctoFinal + "'              "
                + "AND tblDctos.idTipoOrden               IN ("
                + xIdTipoOrden + ")                           "
                + "AND tblDctos.indicador                 IN ("
                + xIndicador + ")                             "
                + "ORDER BY tblDctos.fechaDcto,               "
                + "         tblDctos.idTipoOrden,             "
                + "         tblDctos.idDcto                   ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaDctoBean fachadaBean;

            while (rs.next()) {

                fachadaBean = new FachadaDctoBean();

                //
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setIdDcto(rs.getInt("idDcto"));
                fachadaBean.setFechaDcto(rs.getString("fechaDcto"));
                fachadaBean.setIndicador(rs.getInt("indicador"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setVrBase(rs.getDouble("vrBase"));
                fachadaBean.setVrIva(rs.getDouble("vrIva"));
                fachadaBean.setVrRteFuente(rs.getDouble("vrRteFuente"));
                fachadaBean.setNombreTercero(rs.getString("nombreTercero"));
                fachadaBean.setIdDctoNitCC(rs.getString("idDctoNitCC"));
                fachadaBean.setFechaDctoNitCC(rs.getString("fechaDctoNitCC"));
                fachadaBean.setIdLocalCruce(rs.getInt("idLocalCruce"));
                fachadaBean.setIdTipoOrdenCruce(rs.getInt("idTipoOrdenCruce"));
                fachadaBean.setIdDctoCruce(rs.getInt("idDctoCruce"));

                //
                list.add(new ColaboraDctoBean(fachadaBean.getIdLocal(),
                        fachadaBean.getIdTipoOrden(),
                        fachadaBean.getIdDcto(),
                        fachadaBean.getFechaDcto(),
                        fachadaBean.getFechaDcto(),
                        0,
                        0,
                        fachadaBean.getIndicador(),
                        fachadaBean.getIdDctoNitCC(),
                        fachadaBean.getIdCliente(),
                        fachadaBean.getVrBase(),
                        fachadaBean.getVrIva(),
                        fachadaBean.getNombreTercero(),
                        fachadaBean.getFechaDctoNitCC(),
                        fachadaBean.getIdLocalCruce(),
                        fachadaBean.getIdTipoOrdenCruce(),
                        fachadaBean.getIdDctoCruce(),
                        0,
                        "",
                        fachadaBean.getVrRteFuente(),
                        0,
                        0));

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

    // listaVentaTotal
    public Collection listaVentaTotal(String xFechaDctoInicial,
            String xFechaDctoFinal,
            String xIdTipoOrden,
            String xIndicador) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        //
        Collection list = new ArrayList();

        boolean validaOk = false;
        Connection connection = null;

        //
        String selectStr
                = "SELECT tblDctosOrdenesDetalle.idLocal,                   "
                + "       tblDctosOrdenesDetalle.porcentajeIva,             "
                + "       SUM((tblDctosOrdenesDetalle.vrVentaUnitario *     "
                + "       tblDctosOrdenesDetalle.cantidad) /                "
                + "       ( 1 + tblDctosOrdenesDetalle.porcentajeIva/100 )) "
                + "                                              AS vrBase, "
                + "       SUM((tblDctosOrdenesDetalle.vrVentaUnitario *     "
                + "              tblDctosOrdenesDetalle.cantidad)     -     "
                + "       (tblDctosOrdenesDetalle.vrVentaUnitario     *     "
                + "       tblDctosOrdenesDetalle.cantidad)            /     "
                + "       ( 1 + tblDctosOrdenesDetalle.porcentajeIva/100 )) "
                + "                                              AS vrIva   "
                + "FROM tblDctosOrdenesDetalle,                             "
                + "     tblDctos                                            "
                + "WHERE EXISTS ( SELECT tblDctosOrdenes.             *     "
                + "               FROM tblDctosOrdenes                      "
                + "               WHERE tblDctosOrdenesDetalle.idLocal    = "
                + "                                 tblDctosOrdenes.idLocal "
                + "               AND  tblDctosOrdenesDetalle.idTipoOrden = "
                + "                             tblDctosOrdenes.idTipoOrden "
                + "               AND  tblDctosOrdenesDetalle.idOrden     = "
                + "                              tblDctosOrdenes.idOrden )  "
                + "AND   tblDctosOrdenesDetalle.idLocal    =                "
                + "                                        tblDctos.idLocal "
                + "AND  tblDctosOrdenesDetalle.idTipoOrden =                "
                + "                                    tblDctos.idTipoOrden "
                + "AND  tblDctosOrdenesDetalle.idOrden     =                "
                + "                                        tblDctos.idOrden "
                + "AND  tblDctos.fechaDcto                                  "
                + "     BETWEEN '" + xFechaDctoInicial + "'                 "
                + "AND '" + xFechaDctoFinal + "'                            "
                + "AND tblDctos.idTipoOrden                             IN ("
                + xIdTipoOrden + ")                                         "
                + "AND tblDctos.indicador                               IN ("
                + xIndicador + ")                                           "
                + "GROUP BY  tblDctosOrdenesDetalle.idLocal,                "
                + "          tblDctosOrdenesDetalle.porcentajeIva           ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaDctoBean fachadaBean;

            while (rs.next()) {

                fachadaBean = new FachadaDctoBean();

                //
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setVrBase(rs.getDouble("vrBase"));
                fachadaBean.setVrIva(rs.getDouble("vrIva"));
                fachadaBean.setPorcentajeIva(rs.getDouble("porcentajeIva"));

                //
                list.add(new ColaboraDctoBean(fachadaBean.getIdLocal(),
                        fachadaBean.getIdTipoOrden(),
                        fachadaBean.getIdDcto(),
                        fachadaBean.getFechaDcto(),
                        fachadaBean.getFechaDcto(),
                        0,
                        0,
                        fachadaBean.getIndicador(),
                        fachadaBean.getIdDctoNitCC(),
                        fachadaBean.getIdCliente(),
                        fachadaBean.getVrBase(),
                        fachadaBean.getVrIva(),
                        fachadaBean.getNombreTercero(),
                        fachadaBean.getFechaDctoNitCC(),
                        fachadaBean.getIdLocalCruce(),
                        fachadaBean.getIdTipoOrdenCruce(),
                        fachadaBean.getIdDctoCruce(),
                        fachadaBean.getPorcentajeIva(),
                        "",
                        0,
                        0,
                        0));

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

    // listaCompraTotal
    public Collection listaCompraTotal(String xFechaDctoInicial,
            String xFechaDctoFinal,
            String xIdTipoOrden,
            String xIndicador) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        //
        Collection list = new ArrayList();

        boolean validaOk = false;
        Connection connection = null;

        //
        String selectStr
                = "SELECT tblDctosOrdenesDetalle.idLocal,                   "
                + "       tblDctosOrdenesDetalle.porcentajeIva,             "
                + "       SUM(tblDctosOrdenesDetalle.vrCosto *              "
                + "           tblDctosOrdenesDetalle.cantidad)   AS vrBase, "
                + "       SUM((tblDctosOrdenesDetalle.vrCosto         *     "
                + "              tblDctosOrdenesDetalle.cantidad)     *     "
                + "       ( tblDctosOrdenesDetalle.porcentajeIva/100 ))     "
                + "                                              AS vrIva   "
                + "FROM tblDctosOrdenesDetalle,                             "
                + "     tblDctos                                            "
                + "WHERE EXISTS ( SELECT tblDctosOrdenes.             *     "
                + "               FROM tblDctosOrdenes                      "
                + "               WHERE tblDctosOrdenesDetalle.idLocal    = "
                + "                                 tblDctosOrdenes.idLocal "
                + "               AND  tblDctosOrdenesDetalle.idTipoOrden = "
                + "                             tblDctosOrdenes.idTipoOrden "
                + "               AND  tblDctosOrdenesDetalle.idOrden     = "
                + "                              tblDctosOrdenes.idOrden )  "
                + "AND   tblDctosOrdenesDetalle.idLocal    =                "
                + "                                        tblDctos.idLocal "
                + "AND  tblDctosOrdenesDetalle.idTipoOrden =                "
                + "                                    tblDctos.idTipoOrden "
                + "AND  tblDctosOrdenesDetalle.idOrden     =                "
                + "                                        tblDctos.idOrden "
                + "AND  tblDctos.fechaDcto                                  "
                + "     BETWEEN '" + xFechaDctoInicial + "'                 "
                + "AND '" + xFechaDctoFinal + "'                            "
                + "AND tblDctos.idTipoOrden                             IN ("
                + xIdTipoOrden + ")                                         "
                + "AND tblDctos.indicador                               IN ("
                + xIndicador + ")                                           "
                + "GROUP BY  tblDctosOrdenesDetalle.idLocal,                "
                + "          tblDctosOrdenesDetalle.porcentajeIva           ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaDctoBean fachadaBean;

            while (rs.next()) {

                fachadaBean = new FachadaDctoBean();

                //
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setVrBase(rs.getDouble("vrBase"));
                fachadaBean.setVrIva(rs.getDouble("vrIva"));
                fachadaBean.setPorcentajeIva(rs.getDouble("porcentajeIva"));

                //
                list.add(new ColaboraDctoBean(fachadaBean.getIdLocal(),
                        fachadaBean.getIdTipoOrden(),
                        fachadaBean.getIdDcto(),
                        fachadaBean.getFechaDcto(),
                        fachadaBean.getFechaDcto(),
                        0,
                        0,
                        fachadaBean.getIndicador(),
                        fachadaBean.getIdDctoNitCC(),
                        fachadaBean.getIdCliente(),
                        fachadaBean.getVrBase(),
                        fachadaBean.getVrIva(),
                        fachadaBean.getNombreTercero(),
                        fachadaBean.getFechaDctoNitCC(),
                        fachadaBean.getIdLocalCruce(),
                        fachadaBean.getIdTipoOrdenCruce(),
                        fachadaBean.getIdDctoCruce(),
                        fachadaBean.getPorcentajeIva(),
                        "",
                        0,
                        0,
                        0));

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

    // listaTotalUnFCH
    public FachadaDctoBean listaTotalUnFCH(String xFechaDctoInicial,
            String xFechaDctoFinal,
            String xIdTipoOrden,
            String xIndicador) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        //
        FachadaDctoBean fachadaDctoBean = new FachadaDctoBean();

        boolean validaOk = false;
        Connection connection = null;

        //
        String selectStr
                = "SELECT tblDctos.idLocal,                   "
                + "       0 AS idTipoOrden,                   "
                + "       0 AS idDcto,                        "
                + "       MAX(tblDctos.fechaDcto)             "
                + "                             AS fechaDcto, "
                + "       0 AS indicador,                     "
                + "       '00' AS idCliente,                  "
                + "       SUM(tblDctos.vrBase) AS vrBase,     "
                + "       SUM(tblDctos.vrIva) AS vrIva,       "
                + "       'TOTAL VENTAS' AS nombreTercero,    "
                + "       0  AS idDctoNitCC,                  "
                + "       MAX(tblDctos.fechaDctoNitCC)        "
                + "                       AS fechaDctoNitCC,  "
                + "       0 AS idLocalCruce,                  "
                + "       0 AS idTipoOrdenCruce,              "
                + "       0 AS idDctoCruce,                   "
                + "       SUM(tblDctos.vrBase     +           "
                + "           tblDctos.vrIva) AS vrFactura,   "
                + "       SUM(tblDctos.vrDescuento)           "
                + "                         AS vrDescuento,   "
                + "       SUM(tblDctos.vrRteFuente)           "
                + "                         AS vrRteFuente    "
                + "FROM   tblDctos                            "
                + "WHERE EXISTS                               "
                + "   ( SELECT *                              "
                + "     FROM   tblDctosOrdenes                "
                + "     INNER JOIN tblDctosOrdenesDetalle     "
                + "     ON tblDctosOrdenes.IDLOCAL      =     "
                + "        tblDctosOrdenesDetalle.IDLOCAL     "
                + "     AND tblDctosOrdenes.IDTIPOORDEN =     "
                + "        tblDctosOrdenesDetalle.IDTIPOORDEN "
                + "     AND tblDctosOrdenes.IDORDEN     =     "
                + "        tblDctosOrdenesDetalle.IDORDEN     "
                + "     WHERE tblDctosOrdenes.IDLOCAL   =     "
                + "                          tblDctos.IDLOCAL "
                + "     AND tblDctosOrdenes.IDTIPOORDEN =     "
                + "                      tblDctos.IDTIPOORDEN "
                + "     AND tblDctosOrdenes.IDORDEN     =     "
                + "                         tblDctos.IDORDEN) "
                + "AND tblDctos.fechaDcto                     "
                + "     BETWEEN '" + xFechaDctoInicial + "'   "
                + "AND '" + xFechaDctoFinal + "'              "
                + "AND tblDctos.idTipoOrden               IN ("
                + xIdTipoOrden + ")                           "
                + "AND tblDctos.indicador                 IN ("
                + xIndicador + ")                             "
                + "AND tblDctos.idVendedor                =   "
                + getIdVendedor() + "                         "
                + "GROUP BY tblDctos.idLocal                  ";

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
                fachadaDctoBean.setIdLocal(rs.getInt("idLocal"));
                fachadaDctoBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaDctoBean.setIdDcto(rs.getInt("idDcto"));
                fachadaDctoBean.setFechaDcto(rs.getString("fechaDcto"));
                fachadaDctoBean.setIndicador(rs.getInt("indicador"));
                fachadaDctoBean.setIdCliente(rs.getString("idCliente"));
                fachadaDctoBean.setVrBase(rs.getDouble("vrBase"));
                fachadaDctoBean.setVrIva(rs.getDouble("vrIva"));
                fachadaDctoBean.setNombreTercero(rs.getString("nombreTercero"));
                fachadaDctoBean.setIdDctoNitCC(rs.getString("idDctoNitCC"));
                fachadaDctoBean.setFechaDctoNitCC(
                        rs.getString("fechaDctoNitCC"));
                fachadaDctoBean.setIdLocalCruce(rs.getInt("idLocalCruce"));
                fachadaDctoBean.setIdTipoOrdenCruce(
                        rs.getInt("idTipoOrdenCruce"));
                fachadaDctoBean.setIdDctoCruce(rs.getInt("idDctoCruce"));
                fachadaDctoBean.setVrFactura(rs.getDouble("vrFactura"));
                fachadaDctoBean.setVrDescuento(rs.getDouble("vrDescuento"));
                fachadaDctoBean.setVrRteFuente(rs.getDouble("vrRteFuente"));

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
            return fachadaDctoBean;

        }
    }

    // listaTotalAllFCH
    public FachadaDctoBean listaTotalAllFCH(String xFechaDctoInicial,
            String xFechaDctoFinal,
            String xIdTipoOrden,
            String xIndicador) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        //
        FachadaDctoBean fachadaDctoBean = new FachadaDctoBean();

        boolean validaOk = false;
        Connection connection = null;

        //
        String selectStr
                = "SELECT tblDctos.idLocal,                   "
                + "       0 AS idTipoOrden,                   "
                + "       0 AS idDcto,                        "
                + "       MAX(tblDctos.fechaDcto)             "
                + "                             AS fechaDcto, "
                + "       0 AS indicador,                     "
                + "       '00' AS idCliente,                  "
                + "       SUM(tblDctos.vrBase) AS vrBase,     "
                + "       SUM(tblDctos.vrIva) AS vrIva,       "
                + "       'TOTAL VENTAS' AS nombreTercero,    "
                + "       0  AS idDctoNitCC,                  "
                + "       MAX(tblDctos.fechaDctoNitCC)        "
                + "                       AS fechaDctoNitCC,  "
                + "       0 AS idLocalCruce,                  "
                + "       0 AS idTipoOrdenCruce,              "
                + "       0 AS idDctoCruce,                   "
                + "       SUM(tblDctos.vrBase     +           "
                + "           tblDctos.vrIva) AS vrFactura,   "
                + "       SUM(tblDctos.vrDescuento)           "
                + "                         AS vrDescuento,   "
                + "       SUM(tblDctos.vrRteFuente)           "
                + "                         AS vrRteFuente    "
                + "FROM   tblDctos                            "
                + "WHERE EXISTS                               "
                + "   ( SELECT *                              "
                + "     FROM   tblDctosOrdenes                "
                + "     INNER JOIN tblDctosOrdenesDetalle     "
                + "     ON tblDctosOrdenes.IDLOCAL      =     "
                + "        tblDctosOrdenesDetalle.IDLOCAL     "
                + "     AND tblDctosOrdenes.IDTIPOORDEN =     "
                + "        tblDctosOrdenesDetalle.IDTIPOORDEN "
                + "     AND tblDctosOrdenes.IDORDEN     =     "
                + "        tblDctosOrdenesDetalle.IDORDEN     "
                + "     WHERE tblDctosOrdenes.IDLOCAL   =     "
                + "                          tblDctos.IDLOCAL "
                + "     AND tblDctosOrdenes.IDTIPOORDEN =     "
                + "                      tblDctos.IDTIPOORDEN "
                + "     AND tblDctosOrdenes.IDORDEN     =     "
                + "                         tblDctos.IDORDEN) "
                + "AND tblDctos.fechaDcto                     "
                + "     BETWEEN '" + xFechaDctoInicial + "'   "
                + "AND '" + xFechaDctoFinal + "'              "
                + "AND tblDctos.idTipoOrden               IN ("
                + xIdTipoOrden + ")                           "
                + "AND tblDctos.indicador                 IN ("
                + xIndicador + ")                             "
                + "GROUP BY tblDctos.idLocal                  ";

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
                fachadaDctoBean.setIdLocal(rs.getInt("idLocal"));
                fachadaDctoBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaDctoBean.setIdDcto(rs.getInt("idDcto"));
                fachadaDctoBean.setFechaDcto(rs.getString("fechaDcto"));
                fachadaDctoBean.setIndicador(rs.getInt("indicador"));
                fachadaDctoBean.setIdCliente(rs.getString("idCliente"));
                fachadaDctoBean.setVrBase(rs.getDouble("vrBase"));
                fachadaDctoBean.setVrIva(rs.getDouble("vrIva"));
                fachadaDctoBean.setNombreTercero(rs.getString("nombreTercero"));
                fachadaDctoBean.setIdDctoNitCC(rs.getString("idDctoNitCC"));
                fachadaDctoBean.setFechaDctoNitCC(
                        rs.getString("fechaDctoNitCC"));
                fachadaDctoBean.setIdLocalCruce(rs.getInt("idLocalCruce"));
                fachadaDctoBean.setIdTipoOrdenCruce(
                        rs.getInt("idTipoOrdenCruce"));
                fachadaDctoBean.setIdDctoCruce(rs.getInt("idDctoCruce"));
                fachadaDctoBean.setVrFactura(rs.getDouble("vrFactura"));
                fachadaDctoBean.setVrDescuento(rs.getDouble("vrDescuento"));
                fachadaDctoBean.setVrRteFuente(rs.getDouble("vrRteFuente"));

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
            return fachadaDctoBean;

        }
    }

    // Metodo
    public FachadaDctoBean listaCompraFCH(String xFechaDctoInicial,
            String xFechaDctoFinal,
            String xIdTipoOrden,
            String xIndicador) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        //
        FachadaDctoBean fachadaDctoBean = new FachadaDctoBean();

        boolean validaOk = false;
        Connection connection = null;

        //
        String selectStr
                = "SELECT tblDctos.idLocal,                   "
                + "       0 AS idTipoOrden,                   "
                + "       0 AS idDcto,                        "
                + "       MAX(tblDctos.fechaDcto)             "
                + "                             AS fechaDcto, "
                + "       0 AS indicador,                     "
                + "       '00' AS idCliente,                  "
                + "       SUM(tblDctos.vrBase) AS vrBase,     "
                + "       SUM(tblDctos.vrIva) AS vrIva,       "
                + "       SUM(tblDctos.vrRteFuente)           "
                + "                          AS vrRteFuente,  "
                + "       'TOTAL VENTAS' AS nombreTercero,    "
                + "       0  AS idDctoNitCC,                  "
                + "       MAX(tblDctos.fechaDctoNitCC)        "
                + "                       AS fechaDctoNitCC,  "
                + "       0 AS idLocalCruce,                  "
                + "       0 AS idTipoOrdenCruce,              "
                + "       0 AS idDctoCruce                    "
                + "FROM   tblDctos                            "
                + "WHERE EXISTS                               "
                + "   ( SELECT *                              "
                + "     FROM   tblDctosOrdenes                "
                + "     INNER JOIN tblDctosOrdenesDetalle     "
                + "     ON tblDctosOrdenes.IDLOCAL      =     "
                + "        tblDctosOrdenesDetalle.IDLOCAL     "
                + "     AND tblDctosOrdenes.IDTIPOORDEN =     "
                + "        tblDctosOrdenesDetalle.IDTIPOORDEN "
                + "     AND tblDctosOrdenes.IDORDEN     =     "
                + "        tblDctosOrdenesDetalle.IDORDEN     "
                + "     WHERE tblDctosOrdenes.IDLOCAL   =     "
                + "                          tblDctos.IDLOCAL "
                + "     AND tblDctosOrdenes.IDTIPOORDEN =     "
                + "                      tblDctos.IDTIPOORDEN "
                + "     AND tblDctosOrdenes.IDORDEN     =     "
                + "                         tblDctos.IDORDEN) "
                + "AND tblDctos.fechaDcto                     "
                + "     BETWEEN '" + xFechaDctoInicial + "'   "
                + "AND '" + xFechaDctoFinal + "'              "
                + "AND tblDctos.idTipoOrden               IN ("
                + xIdTipoOrden + ")                           "
                + "AND tblDctos.indicador                 IN ("
                + xIndicador + ")                             "
                + "GROUP BY tblDctos.idLocal                  ";

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
                fachadaDctoBean.setIdLocal(rs.getInt("idLocal"));
                fachadaDctoBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaDctoBean.setIdDcto(rs.getInt("idDcto"));
                fachadaDctoBean.setFechaDcto(rs.getString("fechaDcto"));
                fachadaDctoBean.setIndicador(rs.getInt("indicador"));
                fachadaDctoBean.setIdCliente(rs.getString("idCliente"));
                fachadaDctoBean.setVrBase(rs.getDouble("vrBase"));
                fachadaDctoBean.setVrIva(rs.getDouble("vrIva"));
                fachadaDctoBean.setVrRteFuente(rs.getDouble("vrRteFuente"));
                fachadaDctoBean.setNombreTercero(rs.getString("nombreTercero"));
                fachadaDctoBean.setIdDctoNitCC(rs.getString("idDctoNitCC"));
                fachadaDctoBean.setFechaDctoNitCC(
                        rs.getString("fechaDctoNitCC"));
                fachadaDctoBean.setIdLocalCruce(rs.getInt("idLocalCruce"));
                fachadaDctoBean.setIdTipoOrdenCruce(
                        rs.getInt("idTipoOrdenCruce"));
                fachadaDctoBean.setIdDctoCruce(rs.getInt("idDctoCruce"));

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
            return fachadaDctoBean;

        }
    }

    // listaCxCDetalladoAll
    public Vector listaCxCDetalladoAll(String xFechaCorte) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        //
        String selectStr
                = "SELECT tmpSaldo.idLocal,                      "
                + "       tmpSaldo.IDTIPOORDEN,                  "
                + "       tmpSaldo.idDcto,                       "
                + "       tmpSaldo.fechaDcto,                    "
                + "       tmpSaldo.fechaVcto,                    "
                + "       tmpSaldo.idCliente,                    "
                + "       tmpSaldo.diasVcto AS diasMora,         "
                + "       tmpSaldo.vrSaldo,                      "
                + "       tmpSaldo.indicador,                    "
                + "       MAX(tmpSaldo.idDctoNitCC)              "
                + "                             AS idDctoNitCC,  "
                + "       MAX(tmpSaldo.nombreTercero)            "
                + "                            AS nombreTercero, "
                + "       DATEDIFF(DAY,tmpSaldo.fechaDcto,      '"
                + xFechaCorte + "') AS edadFra,                  "
                + "       MAX(tmpSaldo.nombreVendedor)           "
                + "                            AS nombreVendedor "
                + "FROM                                          "
                + "(SELECT tmpCXC.IDLOCAL,                       "
                + "        tmpCXC.IDTIPOORDEN,                   "
                + "        tmpCXC.idDcto,                        "
                + "        tmpCXC.idOrden,                       "
                + "       tmpCXC.indicador,                      "
                + "       tmpCXC.idCliente,                      "
                + "       MAX(tmpCXC.idDctoNitCC)                "
                + "                             AS idDctoNitCC,  "
                + "       MAX(tmpCXC.diasPlazo) AS diasPlazo,    "
                + "       MIN(tmpCXC.fechaDcto) AS fechaDcto,    "
                + "       MIN(tmpCXC.fechaDcto) +                "
                + "       MAX(tmpCXC.diasPlazo) AS fechaVcto,    "
                + "       DATEDIFF(DAY,  MIN(tmpCXC.fechaDcto) + "
                + "             MAX(tmpCXC.diasPlazo),          '"
                + xFechaCorte + "') AS diasVcto,                 "
                + "       SUM(tmpCXC.vrBase) AS vrBase,          "
                + "       SUM(tmpCXC.vrPago) AS vrPago,          "
                + "       SUM(tmpCXC.vrIva) AS vrIva,            "
                + "       SUM(tmpCXC.vrImpoconsumo)              "
                + "                          AS vrImpoconsumo,   "
                + "       SUM(tmpCXC.vrRteFuente) AS vrRteFuente,"
                + "       SUM(tmpCXC.vrDsctoFcro) AS vrDsctoFcro,"
                + "       SUM(tmpCXC.vrRteIva) AS vrRteIva,      "
                + "       SUM(tmpCXC.vrRteIca) AS vrRteIca,      "
                + "       ( SUM(tmpCXC.vrBase)               +   "
                + "                SUM(tmpCXC.vrIva)         +   "
                + "                SUM(tmpCXC.vrImpoconsumo) -   "
                + "                SUM(tmpCXC.vrPago)        -   "
                + "                SUM(tmpCXC.vrRteFuente)   -   "
                + "                SUM(tmpCXC.vrDsctoFcro)   -   "
                + "                SUM(tmpCXC.vrRteIva)      -   "
                + "         SUM(tmpCXC.vrRteIca) ) AS vrSaldo,   "
                + "         MAX(tmpCXC.nombreTercero)            "
                + "                             AS nombreTercero,"
                + "         MAX(tmpCXC.idVendedor) AS idVendedor,"
                + "         MAX(tmpCXC.nombreVendedor)           "
                + "                            AS nombreVendedor "
                + "FROM                                          "
                + "(SELECT tblDctos.idLocalCruce                 "
                + "                      AS idLocal,             "
                + "       tblDctos.idTipoOrdenCruce              "
                + "                  AS idTipoOrden,             "
                + "       tblDctos.idDctoCruce                   "
                + "                      AS idDcto,              "
                + "       tblDctos.idOrdenCruce AS idOrden,      "
                + "       tblDctos.indicador,                    "
                + "       tblDctos.idCliente,                    "
                + "       tblDctos.vrBase,                       "
                + "       tblDctos.vrPago,                       "
                + "       tblDctos.vrIva,                        "
                + "       tblDctos.vrImpoconsumo,                "
                + "       tblDctos.vrRteFuente,                  "
                + "       tblDctos.vrDsctoFcro,                  "
                + "       tblDctos.vrRteIva,                     "
                + "       tblDctos.vrRteIca,                     "
                + "       tblDctos.fechaDcto,                    "
                + "       tblDctos.diasPlazo,                    "
                + "       tblDctos.idDctoNitCC,                  "
                + "       tblDctos.nombreTercero,                "
                + "       tblDctos.idVendedor,                   "
                + "       (SELECT ctrlUsuarios.aliasUsuario      "
                + "        FROM ctrlUsuarios                     "
                + "        WHERE ctrlUsuarios.idUsuario  =       "
                + "                        tblDctos.idVendedor)  "
                + "                            AS nombreVendedor "
                + "FROM   tblDctos                               "
                + "WHERE  tblDctos.idLocal           =           "
                + this.getIdLocal() + "                          "
                + "AND    tblDctos.idTipoOrdenCruce  =           "
                + this.getIdTipoOrden() + "                      "
                + "AND    tblDctos.idDctoCruce      != 0         "
                + "AND    EXISTS (                               "
                + "       SELECT *                               "
                + "       FROM tblDctosOrdenesDetalle            "
                + "       WHERE tblDctosOrdenesDetalle.idLocal = "
                + "                            tblDctos.idLocal  "
                + "       AND tblDctosOrdenesDetalle.idTipoOrden "
                + "                                            = "
                + "                       tblDctos.idTipoOrden   "
                + "      AND  tblDctosOrdenesDetalle.idOrden   = "
                + "                           tblDctos.idOrden ) "
                + "UNION                                         "
                + "SELECT tblDctos.idLocal,                      "
                + "       tblDctos.idTipoOrden,                  "
                + "       tblDctos.idDcto,                       "
                + "       tblDctos.idOrden,                      "
                + "       tblDctos.indicador,                    "
                + "       tblDctos.idCliente,                    "
                + "       tblDctos.vrBase,                       "
                + "       tblDctos.vrPago,                       "
                + "       tblDctos.vrIva,                        "
                + "       tblDctos.vrImpoconsumo,                "
                + "       tblDctos.vrRteFuente,                  "
                + "       tblDctos.vrDsctoFcro,                  "
                + "       tblDctos.vrRteIva,                     "
                + "       tblDctos.vrRteIca,                     "
                + "       tblDctos.fechaDcto,                    "
                + "       tblDctos.diasPlazo,                    "
                + "       tblDctos.idDctoNitCC,                  "
                + "       tblDctos.nombreTercero,                "
                + "       tblDctos.idVendedor,                   "
                + "       (SELECT ctrlUsuarios.aliasUsuario      "
                + "        FROM ctrlUsuarios                     "
                + "        WHERE ctrlUsuarios.idUsuario  =       "
                + "                        tblDctos.idVendedor)  "
                + "                            AS nombreVendedor "
                + "FROM   tblDctos                               "
                + "WHERE  tblDctos.idLocal           =           "
                + this.getIdLocal() + "                          "
                + "AND    tblDctos.idTipoOrden       =           "
                + this.getIdTipoOrden() + "                      "
                + "AND    tblDctos.idDctoCruce       = 0         "
                + "AND    EXISTS (                               "
                + "       SELECT *                               "
                + "       FROM tblDctosOrdenesDetalle            "
                + "       WHERE tblDctosOrdenesDetalle.idLocal = "
                + "                            tblDctos.idLocal  "
                + "       AND tblDctosOrdenesDetalle.idTipoOrden "
                + "                                            = "
                + "                       tblDctos.idTipoOrden   "
                + "      AND  tblDctosOrdenesDetalle.idOrden   = "
                + "                           tblDctos.idOrden ) "
                + "UNION                                         "
                + "SELECT tblDctos.idLocal,                      "
                + "       tblDctos.idTipoOrden,                  "
                + "       tblDctos.idDctoCruce                   "
                + "                      AS idDcto,              "
                + "       tblDctos.idOrdenCruce AS idOrden,      "
                + "       tblDctos.indicador,                    "
                + "       tblDctos.idCliente,                    "
                + "       tblDctos.vrBase,                       "
                + "       tblDctos.vrPago,                       "
                + "       tblDctos.vrIva,                        "
                + "       tblDctos.vrImpoconsumo,                "
                + "       tblDctos.vrRteFuente,                  "
                + "       tblDctos.vrDsctoFcro,                  "
                + "       tblDctos.vrRteIva,                     "
                + "       tblDctos.vrRteIca,                     "
                + "       tblDctos.fechaDcto,                    "
                + "       tblDctos.diasPlazo,                    "
                + "       tblDctos.idDctoNitCC,                  "
                + "       tblDctos.nombreTercero,                "
                + "       tblDctos.idVendedor,                   "
                + "       (SELECT ctrlUsuarios.aliasUsuario      "
                + "        FROM ctrlUsuarios                     "
                + "        WHERE ctrlUsuarios.idUsuario  =       "
                + "                        tblDctos.idVendedor)  "
                + "                            AS nombreVendedor "
                + "FROM   tblDctos                               "
                + "WHERE  tblDctos.idLocal           =           "
                + this.getIdLocal() + "                          "
                + "AND    tblDctos.idTipoOrden       =           "
                + this.getIdTipoOrden() + "                      "
                + "AND    tblDctos.idTipoOrdenCruce  = 0         "
                + "AND    tblDctos.idDctoCruce      != 0         "
                + "AND    EXISTS (                               "
                + "       SELECT *                               "
                + "       FROM tblDctosOrdenesDetalle            "
                + "       WHERE tblDctosOrdenesDetalle.idLocal = "
                + "                            tblDctos.idLocal  "
                + "       AND tblDctosOrdenesDetalle.idTipoOrden "
                + "                                            = "
                + "                       tblDctos.idTipoOrden   "
                + "      AND  tblDctosOrdenesDetalle.idOrden   = "
                + "                           tblDctos.idOrden ) "
                + "                          )  AS tmpCXC        "
                + "GROUP BY tmpCXC.IDLOCAL,                      "
                + "         tmpCXC.IDTIPOORDEN,                  "
                + "         tmpCXC.idDcto,                       "
                + "         tmpCXC.idOrden,                      "
                + "         tmpCXC.indicador,                    "
                + "         tmpCXC.idCliente                     "
                + "HAVING ( SUM(tmpCXC.vrBase)        +          "
                + "         SUM(tmpCXC.vrIva)         +          "
                + "         SUM(tmpCXC.vrImpoconsumo) -          "
                + "         SUM(tmpCXC.vrPago)        -          "
                + "         SUM(tmpCXC.vrRteFuente)   -          "
                + "         SUM(tmpCXC.vrDsctoFcro)   -          "
                + "         SUM(tmpCXC.vrRteIva)      -          "
                + "         SUM(tmpCXC.vrRteIca) ) > 1 )         "
                + "                              AS tmpSaldo     "
                + "WHERE    tmpSaldo.indicador BETWEEN           "
                + this.getIndicadorInicial() + " AND             "
                + this.getIndicadorFinal() + "                   "
                + "GROUP BY tmpSaldo.idLocal,                    "
                + "         tmpSaldo.IDTIPOORDEN,                "
                + "         tmpSaldo.idDcto,                     "
                + "         tmpSaldo.fechaDcto,                  "
                + "         tmpSaldo.fechaVcto,                  "
                + "         tmpSaldo.idCliente,                  "
                + "         tmpSaldo.diasVcto,                   "
                + "         tmpSaldo.vrSaldo,                    "
                + "         tmpSaldo.indicador                   "
                + "ORDER BY diasMora DESC ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaDctoBean fachadaBean;

            while (rs.next()) {

                //
                fachadaBean = new FachadaDctoBean();

                //
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("IDTIPOORDEN"));
                fachadaBean.setIdDcto(rs.getInt("idDcto"));
                fachadaBean.setFechaDcto(
                        rs.getString("fechaDcto").substring(0, 11));
                fachadaBean.setFechaVcto(
                        rs.getString("fechaVcto").substring(0, 11));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setDiasMora(rs.getInt("diasMora"));
                fachadaBean.setVrSaldo(rs.getDouble("vrSaldo"));
                fachadaBean.setIndicador(rs.getInt("indicador"));
                fachadaBean.setIdDctoNitCC(rs.getString("idDctoNitCC"));
                fachadaBean.setNombreTercero(rs.getString("nombreTercero"));
                fachadaBean.setEdadFra(rs.getInt("edadFra"));
                fachadaBean.setNombreVendedor(rs.getString("nombreVendedor"));

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

    // listaCxCClienteAll
    public Vector listaCxCClienteAll(String xFechaCorte) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        //
        String selectStr
                = "SELECT tmpSaldo.idLocal,                      "
                + "       tmpSaldo.IDTIPOORDEN,                  "
                + "       tmpSaldo.idDcto,                       "
                + "       tmpSaldo.fechaDcto,                    "
                + "       tmpSaldo.fechaVcto,                    "
                + "       tmpSaldo.idCliente,                    "
                + "       tmpSaldo.diasVcto AS diasMora,         "
                + "       tmpSaldo.vrSaldo,                      "
                + "       tmpSaldo.indicador,                    "
                + "       MAX(tmpSaldo.idDctoNitCC)              "
                + "                             AS idDctoNitCC,  "
                + "       MAX(tmpSaldo.nombreTercero)            "
                + "                            AS nombreTercero, "
                + "       DATEDIFF(DAY,tmpSaldo.fechaDcto,      '"
                + xFechaCorte + "') AS edadFra,                  "
                + "       MAX(tmpSaldo.nombreVendedor)           "
                + "                            AS nombreVendedor "
                + "FROM                                          "
                + "(SELECT tmpCXC.IDLOCAL,                       "
                + "        tmpCXC.IDTIPOORDEN,                   "
                + "        tmpCXC.idDcto,                        "
                + "        tmpCXC.idOrden,                       "
                + "       tmpCXC.indicador,                      "
                + "       tmpCXC.idCliente,                      "
                + "       MAX(tmpCXC.idDctoNitCC)                "
                + "                             AS idDctoNitCC,  "
                + "       MAX(tmpCXC.diasPlazo) AS diasPlazo,    "
                + "       MIN(tmpCXC.fechaDcto) AS fechaDcto,    "
                + "       MIN(tmpCXC.fechaDcto) +                "
                + "       MAX(tmpCXC.diasPlazo) AS fechaVcto,    "
                + "       DATEDIFF(DAY,  MIN(tmpCXC.fechaDcto) + "
                + "             MAX(tmpCXC.diasPlazo),          '"
                + xFechaCorte + "') AS diasVcto,                 "
                + "       SUM(tmpCXC.vrBase) AS vrBase,          "
                + "       SUM(tmpCXC.vrPago) AS vrPago,          "
                + "       SUM(tmpCXC.vrIva) AS vrIva,            "
                + "       SUM(tmpCXC.vrImpoconsumo)              "
                + "                          AS vrImpoconsumo,   "
                + "       SUM(tmpCXC.vrRteFuente) AS vrRteFuente,"
                + "       SUM(tmpCXC.vrDsctoFcro) AS vrDsctoFcro,"
                + "       SUM(tmpCXC.vrRteIva) AS vrRteIva,      "
                + "       SUM(tmpCXC.vrRteIca) AS vrRteIca,      "
                + "       ( SUM(tmpCXC.vrBase)               +   "
                + "                SUM(tmpCXC.vrIva)         +   "
                + "                SUM(tmpCXC.vrImpoconsumo) -   "
                + "                SUM(tmpCXC.vrPago)        -   "
                + "                SUM(tmpCXC.vrRteFuente)   -   "
                + "                SUM(tmpCXC.vrDsctoFcro)   -   "
                + "                SUM(tmpCXC.vrRteIva)      -   "
                + "         SUM(tmpCXC.vrRteIca) ) AS vrSaldo,   "
                + "         MAX(tmpCXC.nombreTercero)            "
                + "                             AS nombreTercero,"
                + "         MAX(tmpCXC.idVendedor) AS idVendedor,"
                + "         MAX(tmpCXC.nombreVendedor)           "
                + "                            AS nombreVendedor "
                + "FROM                                          "
                + "(SELECT tblDctos.idLocalCruce                 "
                + "                      AS idLocal,             "
                + "       tblDctos.idTipoOrdenCruce              "
                + "                  AS idTipoOrden,             "
                + "       tblDctos.idDctoCruce                   "
                + "                      AS idDcto,              "
                + "       tblDctos.idOrdenCruce AS idOrden,      "
                + "       tblDctos.indicador,                    "
                + "       tblDctos.idCliente,                    "
                + "       tblDctos.vrBase,                       "
                + "       tblDctos.vrPago,                       "
                + "       tblDctos.vrIva,                        "
                + "       tblDctos.vrImpoconsumo,                "
                + "       tblDctos.vrRteFuente,                  "
                + "       tblDctos.vrDsctoFcro,                  "
                + "       tblDctos.vrRteIva,                     "
                + "       tblDctos.vrRteIca,                     "
                + "       tblDctos.fechaDcto,                    "
                + "       tblDctos.diasPlazo,                    "
                + "       tblDctos.idDctoNitCC,                  "
                + "       tblDctos.nombreTercero,                "
                + "       tblDctos.idVendedor,                   "
                + "       (SELECT ctrlUsuarios.aliasUsuario      "
                + "        FROM ctrlUsuarios                     "
                + "        WHERE ctrlUsuarios.idUsuario  =       "
                + "                        tblDctos.idVendedor)  "
                + "                            AS nombreVendedor "
                + "FROM   tblDctos                               "
                + "WHERE  tblDctos.idLocal           =           "
                + this.getIdLocal() + "                          "
                + "AND    tblDctos.idTipoOrdenCruce  =           "
                + this.getIdTipoOrden() + "                      "
                + "AND    tblDctos.idDctoCruce      != 0         "
                + "AND    EXISTS (                               "
                + "       SELECT *                               "
                + "       FROM tblDctosOrdenesDetalle            "
                + "       WHERE tblDctosOrdenesDetalle.idLocal = "
                + "                            tblDctos.idLocal  "
                + "       AND tblDctosOrdenesDetalle.idTipoOrden "
                + "                                            = "
                + "                       tblDctos.idTipoOrden   "
                + "      AND  tblDctosOrdenesDetalle.idOrden   = "
                + "                           tblDctos.idOrden ) "
                + "UNION                                         "
                + "SELECT tblDctos.idLocal,                      "
                + "       tblDctos.idTipoOrden,                  "
                + "       tblDctos.idDcto,                       "
                + "       tblDctos.idOrden,                      "
                + "       tblDctos.indicador,                    "
                + "       tblDctos.idCliente,                    "
                + "       tblDctos.vrBase,                       "
                + "       tblDctos.vrPago,                       "
                + "       tblDctos.vrIva,                        "
                + "       tblDctos.vrImpoconsumo,                "
                + "       tblDctos.vrRteFuente,                  "
                + "       tblDctos.vrDsctoFcro,                  "
                + "       tblDctos.vrRteIva,                     "
                + "       tblDctos.vrRteIca,                     "
                + "       tblDctos.fechaDcto,                    "
                + "       tblDctos.diasPlazo,                    "
                + "       tblDctos.idDctoNitCC,                  "
                + "       tblDctos.nombreTercero,                "
                + "       tblDctos.idVendedor,                   "
                + "       (SELECT ctrlUsuarios.aliasUsuario      "
                + "        FROM ctrlUsuarios                     "
                + "        WHERE ctrlUsuarios.idUsuario  =       "
                + "                        tblDctos.idVendedor)  "
                + "                            AS nombreVendedor "
                + "FROM   tblDctos                               "
                + "WHERE  tblDctos.idLocal           =           "
                + this.getIdLocal() + "                          "
                + "AND    tblDctos.idTipoOrden       =           "
                + this.getIdTipoOrden() + "                      "
                + "AND    tblDctos.idDctoCruce       = 0         "
                + "AND    EXISTS (                               "
                + "       SELECT *                               "
                + "       FROM tblDctosOrdenesDetalle            "
                + "       WHERE tblDctosOrdenesDetalle.idLocal = "
                + "                            tblDctos.idLocal  "
                + "       AND tblDctosOrdenesDetalle.idTipoOrden "
                + "                                            = "
                + "                       tblDctos.idTipoOrden   "
                + "      AND  tblDctosOrdenesDetalle.idOrden   = "
                + "                           tblDctos.idOrden ) "
                + "UNION                                         "
                + "SELECT tblDctos.idLocal,                      "
                + "       tblDctos.idTipoOrden,                  "
                + "       tblDctos.idDctoCruce                   "
                + "                      AS idDcto,              "
                + "       tblDctos.idOrdenCruce AS idOrden,      "
                + "       tblDctos.indicador,                    "
                + "       tblDctos.idCliente,                    "
                + "       tblDctos.vrBase,                       "
                + "       tblDctos.vrPago,                       "
                + "       tblDctos.vrIva,                        "
                + "       tblDctos.vrImpoconsumo,                "
                + "       tblDctos.vrRteFuente,                  "
                + "       tblDctos.vrDsctoFcro,                  "
                + "       tblDctos.vrRteIva,                     "
                + "       tblDctos.vrRteIca,                     "
                + "       tblDctos.fechaDcto,                    "
                + "       tblDctos.diasPlazo,                    "
                + "       tblDctos.idDctoNitCC,                  "
                + "       tblDctos.nombreTercero,                "
                + "       tblDctos.idVendedor,                   "
                + "       (SELECT ctrlUsuarios.aliasUsuario      "
                + "        FROM ctrlUsuarios                     "
                + "        WHERE ctrlUsuarios.idUsuario  =       "
                + "                        tblDctos.idVendedor)  "
                + "                            AS nombreVendedor "
                + "FROM   tblDctos                               "
                + "WHERE  tblDctos.idLocal           =           "
                + this.getIdLocal() + "                          "
                + "AND    tblDctos.idTipoOrden       =           "
                + this.getIdTipoOrden() + "                      "
                + "AND    tblDctos.idTipoOrdenCruce  = 0         "
                + "AND    tblDctos.idDctoCruce      != 0         "
                + "AND    EXISTS (                               "
                + "       SELECT *                               "
                + "       FROM tblDctosOrdenesDetalle            "
                + "       WHERE tblDctosOrdenesDetalle.idLocal = "
                + "                            tblDctos.idLocal  "
                + "       AND tblDctosOrdenesDetalle.idTipoOrden "
                + "                                            = "
                + "                       tblDctos.idTipoOrden   "
                + "      AND  tblDctosOrdenesDetalle.idOrden   = "
                + "                           tblDctos.idOrden ) "
                + "                          )  AS tmpCXC        "
                + "GROUP BY tmpCXC.IDLOCAL,                      "
                + "         tmpCXC.IDTIPOORDEN,                  "
                + "         tmpCXC.idDcto,                       "
                + "         tmpCXC.idOrden,                      "
                + "         tmpCXC.indicador,                    "
                + "         tmpCXC.idCliente                     "
                + "HAVING ( SUM(tmpCXC.vrBase)        +          "
                + "         SUM(tmpCXC.vrIva)         +          "
                + "         SUM(tmpCXC.vrImpoconsumo) -          "
                + "         SUM(tmpCXC.vrPago)        -          "
                + "         SUM(tmpCXC.vrRteFuente)   -          "
                + "         SUM(tmpCXC.vrDsctoFcro)   -          "
                + "         SUM(tmpCXC.vrRteIva)      -          "
                + "         SUM(tmpCXC.vrRteIca) ) > 1 )         "
                + "                              AS tmpSaldo     "
                + "WHERE    tmpSaldo.indicador BETWEEN           "
                + this.getIndicadorInicial() + " AND             "
                + this.getIndicadorFinal() + "                   "
                + "GROUP BY tmpSaldo.idLocal,                    "
                + "         tmpSaldo.IDTIPOORDEN,                "
                + "         tmpSaldo.idDcto,                     "
                + "         tmpSaldo.fechaDcto,                  "
                + "         tmpSaldo.fechaVcto,                  "
                + "         tmpSaldo.idCliente,                  "
                + "         tmpSaldo.diasVcto,                   "
                + "         tmpSaldo.vrSaldo,                    "
                + "         tmpSaldo.indicador                   "
                + " ORDER BY diasMora  DESC ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaDctoBean fachadaBean;

            while (rs.next()) {

                //
                fachadaBean = new FachadaDctoBean();

                //
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("IDTIPOORDEN"));
                fachadaBean.setIdDcto(rs.getInt("idDcto"));
                fachadaBean.setFechaDcto(
                        rs.getString("fechaDcto").substring(0, 11));
                fachadaBean.setFechaVcto(
                        rs.getString("fechaVcto").substring(0, 11));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setDiasMora(rs.getInt("diasMora"));
                fachadaBean.setVrSaldo(rs.getDouble("vrSaldo"));
                fachadaBean.setIndicador(rs.getInt("indicador"));
                fachadaBean.setIdDctoNitCC(rs.getString("idDctoNitCC"));
                fachadaBean.setNombreTercero(rs.getString("nombreTercero"));
                fachadaBean.setEdadFra(rs.getInt("edadFra"));
                fachadaBean.setNombreVendedor(rs.getString("nombreVendedor"));

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

    // listaCxCDetalladoUn
    public Vector listaCxCDetalladoUn() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        //
        String selectStr
                = "SELECT tmpSaldo.idLocal,                      "
                + "       tmpSaldo.IDTIPOORDEN,                  "
                + "       tmpSaldo.idDcto,                       "
                + "       tmpSaldo.fechaDcto,                    "
                + "       tmpSaldo.fechaVcto,                    "
                + "       tmpSaldo.idCliente,                    "
                + "       tmpSaldo.diasVcto AS diasMora,         "
                + "       tmpSaldo.vrSaldo,                      "
                + "       tmpSaldo.indicador,                    "
                + "       MAX(tmpSaldo.idDctoNitCC)              "
                + "                             AS idDctoNitCC,  "
                + "       MAX(tmpSaldo.nombreTercero)            "
                + "                            AS nombreTercero, "
                + "       DATEDIFF(DAY,tmpSaldo.fechaDcto,      '"
                + getFechaCorteSqlServer() + "') AS edadFra      "
                + "FROM                                          "
                + "(SELECT tmpCXC.IDLOCAL,                       "
                + "        tmpCXC.IDTIPOORDEN,                   "
                + "        tmpCXC.idDcto,                        "
                + "        tmpCXC.idOrden,                       "
                + "       tmpCXC.indicador,                      "
                + "       tmpCXC.idCliente,                      "
                + "       MAX(tmpCXC.idDctoNitCC)                "
                + "                             AS idDctoNitCC,  "
                + "       MAX(tmpCXC.diasPlazo) AS diasPlazo,    "
                + "       MIN(tmpCXC.fechaDcto) AS fechaDcto,    "
                + "       MIN(tmpCXC.fechaDcto) +                "
                + "       MAX(tmpCXC.diasPlazo) AS fechaVcto,    "
                + "       DATEDIFF(DAY,  MIN(tmpCXC.fechaDcto) + "
                + "             MAX(tmpCXC.diasPlazo),          '"
                + getFechaCorteSqlServer() + "') AS diasVcto,    "
                + "       SUM(tmpCXC.vrBase) AS vrBase,          "
                + "       SUM(tmpCXC.vrPago) AS vrPago,          "
                + "       SUM(tmpCXC.vrIva) AS vrIva,            "
                + "       SUM(tmpCXC.vrRteFuente) AS vrRteFuente,"
                + "       SUM(tmpCXC.vrDsctoFcro) AS vrDsctoFcro,"
                + "       SUM(tmpCXC.vrRteIva) AS vrRteIva,      "
                + "       SUM(tmpCXC.vrRteIca) AS vrRteIca,      "
                + "       ( SUM(tmpCXC.vrBase)             +     "
                + "                SUM(tmpCXC.vrIva)       -     "
                + "                SUM(tmpCXC.vrPago)      -     "
                + "                SUM(tmpCXC.vrRteFuente) -     "
                + "                SUM(tmpCXC.vrDsctoFcro) -     "
                + "                SUM(tmpCXC.vrRteIva)    -     "
                + "         SUM(tmpCXC.vrRteIca) ) AS vrSaldo,   "
                + "         MAX(tmpCXC.nombreTercero)            "
                + "                             AS nombreTercero "
                + "FROM                                          "
                + "(SELECT tblDctos.idLocalCruce                 "
                + "                      AS idLocal,             "
                + "       tblDctos.idTipoOrdenCruce              "
                + "                  AS idTipoOrden,             "
                + "       tblDctos.idDctoCruce                   "
                + "                      AS idDcto,              "
                + "       tblDctos.idOrdenCruce AS idOrden,      "
                + "       tblDctos.indicador,                    "
                + "       tblDctos.idCliente,                    "
                + "       tblDctos.vrBase,                       "
                + "       tblDctos.vrPago,                       "
                + "       tblDctos.vrIva,                        "
                + "       tblDctos.vrRteFuente,                  "
                + "       tblDctos.vrDsctoFcro,                  "
                + "       tblDctos.vrRteIva,                     "
                + "       tblDctos.vrRteIca,                     "
                + "       tblDctos.fechaDcto,                    "
                + "       tblDctos.diasPlazo,                    "
                + "       tblDctos.idDctoNitCC,                  "
                + "       tblDctos.nombreTercero                 "
                + "FROM   tblDctos                               "
                + "WHERE  tblDctos.idLocal           =           "
                + this.getIdLocal() + "                          "
                + "AND    tblDctos.idTipoOrdenCruce  =           "
                + this.getIdTipoOrden() + "                      "
                + "AND    tblDctos.idDctoCruce      != 0         "
                + "AND    tblDctos.idVendedor        =           "
                + getIdVendedor() + "                            "
                + "AND    EXISTS (                               "
                + "       SELECT *                               "
                + "       FROM tblDctosOrdenesDetalle            "
                + "       WHERE tblDctosOrdenesDetalle.idLocal = "
                + "                            tblDctos.idLocal  "
                + "       AND tblDctosOrdenesDetalle.idTipoOrden "
                + "                                            = "
                + "                       tblDctos.idTipoOrden   "
                + "      AND  tblDctosOrdenesDetalle.idOrden   = "
                + "                           tblDctos.idOrden ) "
                + "UNION                                         "
                + "SELECT tblDctos.idLocal,                      "
                + "       tblDctos.idTipoOrden,                  "
                + "       tblDctos.idDcto,                       "
                + "       tblDctos.idOrden,                      "
                + "       tblDctos.indicador,                    "
                + "       tblDctos.idCliente,                    "
                + "       tblDctos.vrBase,                       "
                + "       tblDctos.vrPago,                       "
                + "       tblDctos.vrIva,                        "
                + "       tblDctos.vrRteFuente,                  "
                + "       tblDctos.vrDsctoFcro,                  "
                + "       tblDctos.vrRteIva,                     "
                + "       tblDctos.vrRteIca,                     "
                + "       tblDctos.fechaDcto,                    "
                + "       tblDctos.diasPlazo,                    "
                + "       tblDctos.idDctoNitCC,                  "
                + "       tblDctos.nombreTercero                 "
                + "FROM   tblDctos                               "
                + "WHERE  tblDctos.idLocal           =           "
                + this.getIdLocal() + "                          "
                + "AND    tblDctos.idTipoOrden       =           "
                + this.getIdTipoOrden() + "                      "
                + "AND    tblDctos.idVendedor        =           "
                + getIdVendedor() + "                            "
                + "AND    tblDctos.idDctoCruce       = 0         "
                + "AND    EXISTS (                               "
                + "       SELECT *                               "
                + "       FROM tblDctosOrdenesDetalle            "
                + "       WHERE tblDctosOrdenesDetalle.idLocal = "
                + "                            tblDctos.idLocal  "
                + "       AND tblDctosOrdenesDetalle.idTipoOrden "
                + "                                            = "
                + "                       tblDctos.idTipoOrden   "
                + "      AND  tblDctosOrdenesDetalle.idOrden   = "
                + "                           tblDctos.idOrden ) "
                + "UNION                                         "
                + "SELECT tblDctos.idLocal,                      "
                + "       tblDctos.idTipoOrden,                  "
                + "       tblDctos.idDctoCruce                   "
                + "                      AS idDcto,              "
                + "       tblDctos.idOrdenCruce AS idOrden,      "
                + "       tblDctos.indicador,                    "
                + "       tblDctos.idCliente,                    "
                + "       tblDctos.vrBase,                       "
                + "       tblDctos.vrPago,                       "
                + "       tblDctos.vrIva,                        "
                + "       tblDctos.vrRteFuente,                  "
                + "       tblDctos.vrDsctoFcro,                  "
                + "       tblDctos.vrRteIva,                     "
                + "       tblDctos.vrRteIca,                     "
                + "       tblDctos.fechaDcto,                    "
                + "       tblDctos.diasPlazo,                    "
                + "       tblDctos.idDctoNitCC,                  "
                + "       tblDctos.nombreTercero                 "
                + "FROM   tblDctos                               "
                + "WHERE  tblDctos.idLocal           =           "
                + this.getIdLocal() + "                          "
                + "AND    tblDctos.idTipoOrden       =           "
                + this.getIdTipoOrden() + "                      "
                + "AND    tblDctos.idTipoOrdenCruce  = 0         "
                + "AND    tblDctos.idDctoCruce      != 0         "
                + "AND    tblDctos.idVendedor        =           "
                + getIdVendedor() + "                            "
                + "AND    EXISTS (                               "
                + "       SELECT *                               "
                + "       FROM tblDctosOrdenesDetalle            "
                + "       WHERE tblDctosOrdenesDetalle.idLocal = "
                + "                            tblDctos.idLocal  "
                + "       AND tblDctosOrdenesDetalle.idTipoOrden "
                + "                                            = "
                + "                       tblDctos.idTipoOrden   "
                + "      AND  tblDctosOrdenesDetalle.idOrden   = "
                + "                           tblDctos.idOrden ) "
                + "                          )  AS tmpCXC        "
                + "GROUP BY tmpCXC.IDLOCAL,                      "
                + "         tmpCXC.IDTIPOORDEN,                  "
                + "         tmpCXC.idDcto,                       "
                + "         tmpCXC.idOrden,                      "
                + "         tmpCXC.indicador,                    "
                + "         tmpCXC.idCliente                     "
                + "HAVING ( SUM(tmpCXC.vrBase)      +            "
                + "         SUM(tmpCXC.vrIva)       -            "
                + "         SUM(tmpCXC.vrPago)      -            "
                + "         SUM(tmpCXC.vrRteFuente) -            "
                + "         SUM(tmpCXC.vrDsctoFcro) -            "
                + "         SUM(tmpCXC.vrRteIva)    -            "
                + "         SUM(tmpCXC.vrRteIca) ) > 1 )         "
                + "                              AS tmpSaldo     "
                + "WHERE    tmpSaldo.indicador BETWEEN           "
                + getIndicadorInicial() + " AND                  "
                + getIndicadorFinal() + "                        "
                + "GROUP BY tmpSaldo.idLocal,                    "
                + "         tmpSaldo.IDTIPOORDEN,                "
                + "         tmpSaldo.idDcto,                     "
                + "         tmpSaldo.fechaDcto,                  "
                + "         tmpSaldo.fechaVcto,                  "
                + "         tmpSaldo.idCliente,                  "
                + "         tmpSaldo.diasVcto,                   "
                + "         tmpSaldo.vrSaldo,                    "
                + "         tmpSaldo.indicador                   "
                + "ORDER BY diasMora DESC	 ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaDctoBean fachadaBean;

            while (rs.next()) {

                //
                fachadaBean = new FachadaDctoBean();

                //
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("IDTIPOORDEN"));
                fachadaBean.setIdDcto(rs.getInt("idDcto"));
                fachadaBean.setFechaDcto(
                        rs.getString("fechaDcto").substring(0, 11));
                fachadaBean.setFechaVcto(
                        rs.getString("fechaVcto").substring(0, 11));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setDiasMora(rs.getInt("diasMora"));
                fachadaBean.setVrSaldo(rs.getDouble("vrSaldo"));
                fachadaBean.setIndicador(rs.getInt("indicador"));
                fachadaBean.setIdDctoNitCC(rs.getString("idDctoNitCC"));
                fachadaBean.setNombreTercero(rs.getString("nombreTercero"));
                fachadaBean.setEdadFra(rs.getInt("edadFra"));

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

    // listaCxCClienteUn
    public Vector listaCxCClienteUn() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        //
        String selectStr
                = "SELECT tmpSaldo.idLocal,                      "
                + "       tmpSaldo.IDTIPOORDEN,                  "
                + "       tmpSaldo.idDcto,                       "
                + "       tmpSaldo.fechaDcto,                    "
                + "       tmpSaldo.fechaVcto,                    "
                + "       tmpSaldo.idCliente,                    "
                + "       tmpSaldo.diasVcto AS diasMora,         "
                + "       tmpSaldo.vrSaldo,                      "
                + "       tmpSaldo.indicador,                    "
                + "       MAX(tmpSaldo.idDctoNitCC)              "
                + "                             AS idDctoNitCC,  "
                + "       MAX(tmpSaldo.nombreTercero)            "
                + "                            AS nombreTercero, "
                + "       DATEDIFF(DAY,tmpSaldo.fechaDcto,      '"
                + getFechaCorteSqlServer() + "') AS edadFra      "
                + "FROM                                          "
                + "(SELECT tmpCXC.IDLOCAL,                       "
                + "        tmpCXC.IDTIPOORDEN,                   "
                + "        tmpCXC.idDcto,                        "
                + "        tmpCXC.idOrden,                       "
                + "       tmpCXC.indicador,                      "
                + "       tmpCXC.idCliente,                      "
                + "       MAX(tmpCXC.idDctoNitCC)                "
                + "                             AS idDctoNitCC,  "
                + "       MAX(tmpCXC.diasPlazo) AS diasPlazo,    "
                + "       MIN(tmpCXC.fechaDcto) AS fechaDcto,    "
                + "       MIN(tmpCXC.fechaDcto) +                "
                + "       MAX(tmpCXC.diasPlazo) AS fechaVcto,    "
                + "       DATEDIFF(DAY,  MIN(tmpCXC.fechaDcto) + "
                + "             MAX(tmpCXC.diasPlazo),          '"
                + getFechaCorteSqlServer() + "') AS diasVcto,    "
                + "       SUM(tmpCXC.vrBase) AS vrBase,          "
                + "       SUM(tmpCXC.vrPago) AS vrPago,          "
                + "       SUM(tmpCXC.vrIva) AS vrIva,            "
                + "       SUM(tmpCXC.vrImpoconsumo)              "
                + "                          AS vrImpoconsumo,   "
                + "       SUM(tmpCXC.vrRteFuente) AS vrRteFuente,"
                + "       SUM(tmpCXC.vrDsctoFcro) AS vrDsctoFcro,"
                + "       SUM(tmpCXC.vrRteIva) AS vrRteIva,      "
                + "       SUM(tmpCXC.vrRteIca) AS vrRteIca,      "
                + "       ( SUM(tmpCXC.vrBase)               +   "
                + "                SUM(tmpCXC.vrIva)         +   "
                + "                SUM(tmpCXC.vrImpoconsumo) -   "
                + "                SUM(tmpCXC.vrPago)        -   "
                + "                SUM(tmpCXC.vrRteFuente)   -   "
                + "                SUM(tmpCXC.vrDsctoFcro)   -   "
                + "                SUM(tmpCXC.vrRteIva)      -   "
                + "         SUM(tmpCXC.vrRteIca) ) AS vrSaldo,   "
                + "         MAX(tmpCXC.nombreTercero)            "
                + "                             AS nombreTercero "
                + "FROM                                          "
                + "(SELECT tblDctos.idLocalCruce                 "
                + "                      AS idLocal,             "
                + "       tblDctos.idTipoOrdenCruce              "
                + "                  AS idTipoOrden,             "
                + "       tblDctos.idDctoCruce                   "
                + "                      AS idDcto,              "
                + "       tblDctos.idOrdenCruce AS idOrden,      "
                + "       tblDctos.indicador,                    "
                + "       tblDctos.idCliente,                    "
                + "       tblDctos.vrBase,                       "
                + "       tblDctos.vrPago,                       "
                + "       tblDctos.vrIva,                        "
                + "       tblDctos.vrImpoconsumo,                "
                + "       tblDctos.vrRteFuente,                  "
                + "       tblDctos.vrDsctoFcro,                  "
                + "       tblDctos.vrRteIva,                     "
                + "       tblDctos.vrRteIca,                     "
                + "       tblDctos.fechaDcto,                    "
                + "       tblDctos.diasPlazo,                    "
                + "       tblDctos.idDctoNitCC,                  "
                + "       tblDctos.nombreTercero                 "
                + "FROM   tblDctos                               "
                + "WHERE  tblDctos.idLocal           =           "
                + this.getIdLocal() + "                          "
                + "AND    tblDctos.idTipoOrdenCruce  =           "
                + this.getIdTipoOrden() + "                      "
                + "AND    tblDctos.idDctoCruce      != 0         "
                + "AND    tblDctos.idVendedor        =           "
                + getIdVendedor() + "                            "
                + "AND    EXISTS (                               "
                + "       SELECT *                               "
                + "       FROM tblDctosOrdenesDetalle            "
                + "       WHERE tblDctosOrdenesDetalle.idLocal = "
                + "                            tblDctos.idLocal  "
                + "       AND tblDctosOrdenesDetalle.idTipoOrden "
                + "                                            = "
                + "                       tblDctos.idTipoOrden   "
                + "      AND  tblDctosOrdenesDetalle.idOrden   = "
                + "                           tblDctos.idOrden ) "
                + "UNION                                         "
                + "SELECT tblDctos.idLocal,                      "
                + "       tblDctos.idTipoOrden,                  "
                + "       tblDctos.idDcto,                       "
                + "       tblDctos.idOrden,                      "
                + "       tblDctos.indicador,                    "
                + "       tblDctos.idCliente,                    "
                + "       tblDctos.vrBase,                       "
                + "       tblDctos.vrPago,                       "
                + "       tblDctos.vrIva,                        "
                + "       tblDctos.vrImpoconsumo,                "
                + "       tblDctos.vrRteFuente,                  "
                + "       tblDctos.vrDsctoFcro,                  "
                + "       tblDctos.vrRteIva,                     "
                + "       tblDctos.vrRteIca,                     "
                + "       tblDctos.fechaDcto,                    "
                + "       tblDctos.diasPlazo,                    "
                + "       tblDctos.idDctoNitCC,                  "
                + "       tblDctos.nombreTercero                 "
                + "FROM   tblDctos                               "
                + "WHERE  tblDctos.idLocal           =           "
                + this.getIdLocal() + "                          "
                + "AND    tblDctos.idTipoOrden       =           "
                + this.getIdTipoOrden() + "                      "
                + "AND    tblDctos.idVendedor        =           "
                + getIdVendedor() + "                            "
                + "AND    tblDctos.idDctoCruce       = 0         "
                + "AND    EXISTS (                               "
                + "       SELECT *                               "
                + "       FROM tblDctosOrdenesDetalle            "
                + "       WHERE tblDctosOrdenesDetalle.idLocal = "
                + "                            tblDctos.idLocal  "
                + "       AND tblDctosOrdenesDetalle.idTipoOrden "
                + "                                            = "
                + "                       tblDctos.idTipoOrden   "
                + "      AND  tblDctosOrdenesDetalle.idOrden   = "
                + "                           tblDctos.idOrden ) "
                + "UNION                                         "
                + "SELECT tblDctos.idLocal,                      "
                + "       tblDctos.idTipoOrden,                  "
                + "       tblDctos.idDctoCruce                   "
                + "                      AS idDcto,              "
                + "       tblDctos.idOrdenCruce AS idOrden,      "
                + "       tblDctos.indicador,                    "
                + "       tblDctos.idCliente,                    "
                + "       tblDctos.vrBase,                       "
                + "       tblDctos.vrPago,                       "
                + "       tblDctos.vrIva,                        "
                + "       tblDctos.vrImpoconsumo,                "
                + "       tblDctos.vrRteFuente,                  "
                + "       tblDctos.vrDsctoFcro,                  "
                + "       tblDctos.vrRteIva,                     "
                + "       tblDctos.vrRteIca,                     "
                + "       tblDctos.fechaDcto,                    "
                + "       tblDctos.diasPlazo,                    "
                + "       tblDctos.idDctoNitCC,                  "
                + "       tblDctos.nombreTercero                 "
                + "FROM   tblDctos                               "
                + "WHERE  tblDctos.idLocal           =           "
                + this.getIdLocal() + "                          "
                + "AND    tblDctos.idTipoOrden       =           "
                + this.getIdTipoOrden() + "                      "
                + "AND    tblDctos.idTipoOrdenCruce  = 0         "
                + "AND    tblDctos.idDctoCruce      != 0         "
                + "AND    tblDctos.idVendedor        =           "
                + getIdVendedor() + "                            "
                + "AND    EXISTS (                               "
                + "       SELECT *                               "
                + "       FROM tblDctosOrdenesDetalle            "
                + "       WHERE tblDctosOrdenesDetalle.idLocal = "
                + "                            tblDctos.idLocal  "
                + "       AND tblDctosOrdenesDetalle.idTipoOrden "
                + "                                            = "
                + "                       tblDctos.idTipoOrden   "
                + "      AND  tblDctosOrdenesDetalle.idOrden   = "
                + "                           tblDctos.idOrden ) "
                + "                          )  AS tmpCXC        "
                + "GROUP BY tmpCXC.IDLOCAL,                      "
                + "         tmpCXC.IDTIPOORDEN,                  "
                + "         tmpCXC.idDcto,                       "
                + "         tmpCXC.idOrden,                      "
                + "         tmpCXC.indicador,                    "
                + "         tmpCXC.idCliente                     "
                + "HAVING ( SUM(tmpCXC.vrBase)        +          "
                + "         SUM(tmpCXC.vrIva)         +          "
                + "         SUM(tmpCXC.vrImpoconsumo) -          "
                + "         SUM(tmpCXC.vrPago)        -          "
                + "         SUM(tmpCXC.vrRteFuente)   -          "
                + "         SUM(tmpCXC.vrDsctoFcro)   -          "
                + "         SUM(tmpCXC.vrRteIva)      -          "
                + "         SUM(tmpCXC.vrRteIca) ) > 1 )         "
                + "                              AS tmpSaldo     "
                + "WHERE    tmpSaldo.indicador BETWEEN           "
                + getIndicadorInicial() + " AND                  "
                + getIndicadorFinal() + "                        "
                + "GROUP BY tmpSaldo.idLocal,                    "
                + "         tmpSaldo.IDTIPOORDEN,                "
                + "         tmpSaldo.idDcto,                     "
                + "         tmpSaldo.fechaDcto,                  "
                + "         tmpSaldo.fechaVcto,                  "
                + "         tmpSaldo.idCliente,                  "
                + "         tmpSaldo.diasVcto,                   "
                + "         tmpSaldo.vrSaldo,                    "
                + "         tmpSaldo.indicador                   "
                + "ORDER BY tmpSaldo.idLocal,                    "
                + "         tmpSaldo.IDTIPOORDEN,                "
                + "         tmpSaldo.idCliente,                  "
                + "         tmpSaldo.fechaDcto                   ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaDctoBean fachadaBean;

            while (rs.next()) {

                //
                fachadaBean = new FachadaDctoBean();

                //
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("IDTIPOORDEN"));
                fachadaBean.setIdDcto(rs.getInt("idDcto"));
                fachadaBean.setFechaDcto(
                        rs.getString("fechaDcto").substring(0, 11));
                fachadaBean.setFechaVcto(
                        rs.getString("fechaVcto").substring(0, 11));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setDiasMora(rs.getInt("diasMora"));
                fachadaBean.setVrSaldo(rs.getDouble("vrSaldo"));
                fachadaBean.setIndicador(rs.getInt("indicador"));
                fachadaBean.setIdDctoNitCC(rs.getString("idDctoNitCC"));
                fachadaBean.setNombreTercero(rs.getString("nombreTercero"));
                fachadaBean.setEdadFra(rs.getInt("edadFra"));

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

    // listaCxCTotalAllFCH
    public FachadaDctoBean listaCxCTotalAllFCH(String xFechaCorte) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        // Variable de fachada de los datos
        FachadaDctoBean fachadaBean = new FachadaDctoBean();

        Connection connection = null;

        //
        String selectStr
                = "SELECT tmpSaldo.idLocal,                      "
                + "       COUNT(*) AS numeroDctos,               "
                + "       SUM(tmpSaldo.vrSaldo) AS vrSaldo       "
                + "FROM                                          "
                + "(SELECT tmpCXC.IDLOCAL,                       "
                + "        tmpCXC.IDTIPOORDEN,                   "
                + "        tmpCXC.idDcto,                        "
                + "        tmpCXC.idOrden,                       "
                + "       tmpCXC.indicador,                      "
                + "       tmpCXC.idCliente,                      "
                + "       MAX(tmpCXC.idDctoNitCC)                "
                + "                             AS idDctoNitCC,  "
                + "       MAX(tmpCXC.diasPlazo) AS diasPlazo,    "
                + "       MIN(tmpCXC.fechaDcto) AS fechaDcto,    "
                + "       MIN(tmpCXC.fechaDcto) +                "
                + "       MAX(tmpCXC.diasPlazo) AS fechaVcto,    "
                + "       DATEDIFF(DAY,  MIN(tmpCXC.fechaDcto) + "
                + "             MAX(tmpCXC.diasPlazo),          '"
                + xFechaCorte + "')                              "
                + "                             AS diasVcto,     "
                + "       SUM(tmpCXC.vrBase) AS vrBase,          "
                + "       SUM(tmpCXC.vrPago) AS vrPago,          "
                + "       SUM(tmpCXC.vrIva) AS vrIva,            "
                + "       SUM(tmpCXC.vrImpoconsumo)              "
                + "                          AS vrImpoconsumo,   "
                + "       SUM(tmpCXC.vrRteFuente) AS vrRteFuente,"
                + "       SUM(tmpCXC.vrDsctoFcro) AS vrDsctoFcro,"
                + "       SUM(tmpCXC.vrRteIva) AS vrRteIva,      "
                + "       SUM(tmpCXC.vrRteIca) AS vrRteIca,      "
                + "       ( SUM(tmpCXC.vrBase)               +   "
                + "                SUM(tmpCXC.vrIva)         +   "
                + "                SUM(tmpCXC.vrImpoconsumo) -   "
                + "                SUM(tmpCXC.vrPago)        -   "
                + "                SUM(tmpCXC.vrRteFuente)   -   "
                + "                SUM(tmpCXC.vrDsctoFcro)   -   "
                + "                SUM(tmpCXC.vrRteIva)      -   "
                + "         SUM(tmpCXC.vrRteIca) ) AS vrSaldo    "
                + "FROM                                          "
                + "(SELECT tblDctos.idLocalCruce                 "
                + "                      AS idLocal,             "
                + "       tblDctos.idTipoOrdenCruce              "
                + "                  AS idTipoOrden,             "
                + "       tblDctos.idDctoCruce                   "
                + "                      AS idDcto,              "
                + "       tblDctos.idOrdenCruce AS idOrden,      "
                + "       tblDctos.indicador,                    "
                + "       tblDctos.idCliente,                    "
                + "       tblDctos.vrBase,                       "
                + "       tblDctos.vrPago,                       "
                + "       tblDctos.vrIva,                        "
                + "       tblDctos.vrImpoconsumo,                "
                + "       tblDctos.vrRteFuente,                  "
                + "       tblDctos.vrDsctoFcro,                  "
                + "       tblDctos.vrRteIva,                     "
                + "       tblDctos.vrRteIca,                     "
                + "       tblDctos.fechaDcto,                    "
                + "       tblDctos.diasPlazo,                    "
                + "       tblDctos.idDctoNitCC                   "
                + "FROM   tblDctos                               "
                + "WHERE  tblDctos.idLocal           =           "
                + this.getIdLocal() + "                          "
                + "AND    tblDctos.idTipoOrdenCruce  =           "
                + this.getIdTipoOrden() + "                      "
                + "AND    tblDctos.idDctoCruce      != 0         "
                + "AND    EXISTS (                               "
                + "       SELECT *                               "
                + "       FROM tblDctosOrdenesDetalle            "
                + "       WHERE tblDctosOrdenesDetalle.idLocal = "
                + "                            tblDctos.idLocal  "
                + "       AND tblDctosOrdenesDetalle.idTipoOrden "
                + "                                            = "
                + "                       tblDctos.idTipoOrden   "
                + "      AND  tblDctosOrdenesDetalle.idOrden   = "
                + "                           tblDctos.idOrden ) "
                + "UNION                                         "
                + "SELECT tblDctos.idLocal,                      "
                + "       tblDctos.idTipoOrden,                  "
                + "       tblDctos.idDcto,                       "
                + "       tblDctos.idOrden,                      "
                + "       tblDctos.indicador,                    "
                + "       tblDctos.idCliente,                    "
                + "       tblDctos.vrBase,                       "
                + "       tblDctos.vrPago,                       "
                + "       tblDctos.vrIva,                        "
                + "       tblDctos.vrImpoconsumo,                "
                + "       tblDctos.vrRteFuente,                  "
                + "       tblDctos.vrDsctoFcro,                  "
                + "       tblDctos.vrRteIva,                     "
                + "       tblDctos.vrRteIca,                     "
                + "       tblDctos.fechaDcto,                    "
                + "       tblDctos.diasPlazo,                    "
                + "       tblDctos.idDctoNitCC                   "
                + "FROM   tblDctos                               "
                + "WHERE  tblDctos.idLocal           =           "
                + this.getIdLocal() + "                          "
                + "AND    tblDctos.idTipoOrden       =           "
                + this.getIdTipoOrden() + "                      "
                + "AND    tblDctos.idDctoCruce       = 0         "
                + "AND    EXISTS (                               "
                + "       SELECT *                               "
                + "       FROM tblDctosOrdenesDetalle            "
                + "       WHERE tblDctosOrdenesDetalle.idLocal = "
                + "                            tblDctos.idLocal  "
                + "       AND tblDctosOrdenesDetalle.idTipoOrden "
                + "                                            = "
                + "                       tblDctos.idTipoOrden   "
                + "      AND  tblDctosOrdenesDetalle.idOrden   = "
                + "                           tblDctos.idOrden ) "
                + "UNION                                         "
                + "SELECT tblDctos.idLocal,                      "
                + "       tblDctos.idTipoOrden,                  "
                + "       tblDctos.idDctoCruce                   "
                + "                      AS idDcto,              "
                + "       tblDctos.idOrdenCruce AS idOrden,      "
                + "       tblDctos.indicador,                    "
                + "       tblDctos.idCliente,                    "
                + "       tblDctos.vrBase,                       "
                + "       tblDctos.vrPago,                       "
                + "       tblDctos.vrIva,                        "
                + "       tblDctos.vrImpoconsumo,                "
                + "       tblDctos.vrRteFuente,                  "
                + "       tblDctos.vrDsctoFcro,                  "
                + "       tblDctos.vrRteIva,                     "
                + "       tblDctos.vrRteIca,                     "
                + "       tblDctos.fechaDcto,                    "
                + "       tblDctos.diasPlazo,                    "
                + "       tblDctos.idDctoNitCC                   "
                + "FROM   tblDctos                               "
                + "WHERE  tblDctos.idLocal           =           "
                + this.getIdLocal() + "                          "
                + "AND    tblDctos.idTipoOrden       =           "
                + this.getIdTipoOrden() + "                      "
                + "AND    tblDctos.idTipoOrdenCruce  = 0         "
                + "AND    tblDctos.idDctoCruce      != 0         "
                + "AND    EXISTS (                               "
                + "       SELECT *                               "
                + "       FROM tblDctosOrdenesDetalle            "
                + "       WHERE tblDctosOrdenesDetalle.idLocal = "
                + "                            tblDctos.idLocal  "
                + "       AND tblDctosOrdenesDetalle.idTipoOrden "
                + "                                            = "
                + "                       tblDctos.idTipoOrden   "
                + "      AND  tblDctosOrdenesDetalle.idOrden   = "
                + "                           tblDctos.idOrden ) "
                + "                          )  AS tmpCXC        "
                + "GROUP BY tmpCXC.IDLOCAL,                      "
                + "         tmpCXC.IDTIPOORDEN,                  "
                + "         tmpCXC.idDcto,                       "
                + "         tmpCXC.idOrden,                      "
                + "         tmpCXC.indicador,                    "
                + "         tmpCXC.idCliente                     "
                + "HAVING ( SUM(tmpCXC.vrBase)        +          "
                + "         SUM(tmpCXC.vrIva)         +          "
                + "         SUM(tmpCXC.vrImpoconsumo) -          "
                + "         SUM(tmpCXC.vrPago)        -          "
                + "         SUM(tmpCXC.vrRteFuente)   -          "
                + "         SUM(tmpCXC.vrDsctoFcro)   -          "
                + "         SUM(tmpCXC.vrRteIva)      -          "
                + "         SUM(tmpCXC.vrRteIca) ) > 1 )         "
                + "                              AS tmpSaldo     "
                + "WHERE    tmpSaldo.indicador BETWEEN           "
                + getIndicadorInicial() + " AND                  "
                + getIndicadorFinal() + "                        "
                + "GROUP BY tmpSaldo.idLocal";

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
                fachadaBean.setNumeroDctos(rs.getInt("numeroDctos"));
                fachadaBean.setVrSaldo(rs.getDouble("vrSaldo"));

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

    // listaCxCTotalUnFCH
    public FachadaDctoBean listaCxCTotalUnFCH() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        // Variable de fachada de los datos
        FachadaDctoBean fachadaBean = new FachadaDctoBean();

        Connection connection = null;

        //
        String selectStr
                = "SELECT tmpSaldo.idLocal,                      "
                + "       COUNT(*) AS numeroDctos,               "
                + "       SUM(tmpSaldo.vrSaldo) AS vrSaldo       "
                + "FROM                                          "
                + "(SELECT tmpCXC.IDLOCAL,                       "
                + "        tmpCXC.IDTIPOORDEN,                   "
                + "        tmpCXC.idDcto,                        "
                + "        tmpCXC.idOrden,                       "
                + "       tmpCXC.indicador,                      "
                + "       tmpCXC.idCliente,                      "
                + "       MAX(tmpCXC.idDctoNitCC)                "
                + "                             AS idDctoNitCC,  "
                + "       MAX(tmpCXC.diasPlazo) AS diasPlazo,    "
                + "       MIN(tmpCXC.fechaDcto) AS fechaDcto,    "
                + "       MIN(tmpCXC.fechaDcto) +                "
                + "       MAX(tmpCXC.diasPlazo) AS fechaVcto,    "
                + "       DATEDIFF(DAY,  MIN(tmpCXC.fechaDcto) + "
                + "             MAX(tmpCXC.diasPlazo),          '"
                + getFechaCorteSqlServer() + "') AS diasVcto,     "
                + "       SUM(tmpCXC.vrBase) AS vrBase,          "
                + "       SUM(tmpCXC.vrPago) AS vrPago,          "
                + "       SUM(tmpCXC.vrIva) AS vrIva,            "
                + "       SUM(tmpCXC.vrImpoconsumo)              "
                + "                          AS vrImpoconsumo,   "
                + "       SUM(tmpCXC.vrRteFuente) AS vrRteFuente,"
                + "       SUM(tmpCXC.vrDsctoFcro) AS vrDsctoFcro,"
                + "       SUM(tmpCXC.vrRteIva) AS vrRteIva,      "
                + "       SUM(tmpCXC.vrRteIca) AS vrRteIca,      "
                + "       ( SUM(tmpCXC.vrBase)               +   "
                + "                SUM(tmpCXC.vrIva)         +   "
                + "                SUM(tmpCXC.vrImpoconsumo) -   "
                + "                SUM(tmpCXC.vrPago)        -   "
                + "                SUM(tmpCXC.vrRteFuente)   -   "
                + "                SUM(tmpCXC.vrDsctoFcro)   -   "
                + "                SUM(tmpCXC.vrRteIva)      -   "
                + "         SUM(tmpCXC.vrRteIca) ) AS vrSaldo    "
                + "FROM                                          "
                + "(SELECT tblDctos.idLocalCruce                 "
                + "                      AS idLocal,             "
                + "       tblDctos.idTipoOrdenCruce              "
                + "                  AS idTipoOrden,             "
                + "       tblDctos.idDctoCruce                   "
                + "                      AS idDcto,              "
                + "       tblDctos.idOrdenCruce AS idOrden,      "
                + "       tblDctos.indicador,                    "
                + "       tblDctos.idCliente,                    "
                + "       tblDctos.vrBase,                       "
                + "       tblDctos.vrPago,                       "
                + "       tblDctos.vrIva,                        "
                + "       tblDctos.vrImpoconsumo,                "
                + "       tblDctos.vrRteFuente,                  "
                + "       tblDctos.vrDsctoFcro,                  "
                + "       tblDctos.vrRteIva,                     "
                + "       tblDctos.vrRteIca,                     "
                + "       tblDctos.fechaDcto,                    "
                + "       tblDctos.diasPlazo,                    "
                + "       tblDctos.idDctoNitCC                   "
                + "FROM   tblDctos                               "
                + "WHERE  tblDctos.idLocal           =           "
                + this.getIdLocal() + "                          "
                + "AND    tblDctos.idTipoOrdenCruce  =           "
                + this.getIdTipoOrden() + "                      "
                + "AND    tblDctos.idDctoCruce      != 0         "
                + "AND    tblDctos.idVendedor        =           "
                + getIdVendedor() + "                            "
                + "AND    EXISTS (                               "
                + "       SELECT *                               "
                + "       FROM tblDctosOrdenesDetalle            "
                + "       WHERE tblDctosOrdenesDetalle.idLocal = "
                + "                            tblDctos.idLocal  "
                + "       AND tblDctosOrdenesDetalle.idTipoOrden "
                + "                                            = "
                + "                       tblDctos.idTipoOrden   "
                + "      AND  tblDctosOrdenesDetalle.idOrden   = "
                + "                           tblDctos.idOrden ) "
                + "UNION                                         "
                + "SELECT tblDctos.idLocal,                      "
                + "       tblDctos.idTipoOrden,                  "
                + "       tblDctos.idDcto,                       "
                + "       tblDctos.idOrden,                      "
                + "       tblDctos.indicador,                    "
                + "       tblDctos.idCliente,                    "
                + "       tblDctos.vrBase,                       "
                + "       tblDctos.vrPago,                       "
                + "       tblDctos.vrIva,                        "
                + "       tblDctos.vrImpoconsumo,                "
                + "       tblDctos.vrRteFuente,                  "
                + "       tblDctos.vrDsctoFcro,                  "
                + "       tblDctos.vrRteIva,                     "
                + "       tblDctos.vrRteIca,                     "
                + "       tblDctos.fechaDcto,                    "
                + "       tblDctos.diasPlazo,                    "
                + "       tblDctos.idDctoNitCC                   "
                + "FROM   tblDctos                               "
                + "WHERE  tblDctos.idLocal           =           "
                + this.getIdLocal() + "                          "
                + "AND    tblDctos.idTipoOrden       =           "
                + this.getIdTipoOrden() + "                      "
                + "AND    tblDctos.idDctoCruce       = 0         "
                + "AND    tblDctos.idVendedor        =           "
                + getIdVendedor() + "                            "
                + "AND    EXISTS (                               "
                + "       SELECT *                               "
                + "       FROM tblDctosOrdenesDetalle            "
                + "       WHERE tblDctosOrdenesDetalle.idLocal = "
                + "                            tblDctos.idLocal  "
                + "       AND tblDctosOrdenesDetalle.idTipoOrden "
                + "                                            = "
                + "                       tblDctos.idTipoOrden   "
                + "      AND  tblDctosOrdenesDetalle.idOrden   = "
                + "                           tblDctos.idOrden ) "
                + "UNION                                         "
                + "SELECT tblDctos.idLocal,                      "
                + "       tblDctos.idTipoOrden,                  "
                + "       tblDctos.idDctoCruce                   "
                + "                      AS idDcto,              "
                + "       tblDctos.idOrdenCruce AS idOrden,      "
                + "       tblDctos.indicador,                    "
                + "       tblDctos.idCliente,                    "
                + "       tblDctos.vrBase,                       "
                + "       tblDctos.vrPago,                       "
                + "       tblDctos.vrIva,                        "
                + "       tblDctos.vrImpoconsumo,                "
                + "       tblDctos.vrRteFuente,                  "
                + "       tblDctos.vrDsctoFcro,                  "
                + "       tblDctos.vrRteIva,                     "
                + "       tblDctos.vrRteIca,                     "
                + "       tblDctos.fechaDcto,                    "
                + "       tblDctos.diasPlazo,                    "
                + "       tblDctos.idDctoNitCC                   "
                + "FROM   tblDctos                               "
                + "WHERE  tblDctos.idLocal           =           "
                + this.getIdLocal() + "                          "
                + "AND    tblDctos.idTipoOrden       =           "
                + this.getIdTipoOrden() + "                      "
                + "AND    tblDctos.idTipoOrdenCruce  = 0         "
                + "AND    tblDctos.idDctoCruce      != 0         "
                + "AND    tblDctos.idVendedor        =           "
                + getIdVendedor() + "                            "
                + "AND    EXISTS (                               "
                + "       SELECT *                               "
                + "       FROM tblDctosOrdenesDetalle            "
                + "       WHERE tblDctosOrdenesDetalle.idLocal = "
                + "                            tblDctos.idLocal  "
                + "       AND tblDctosOrdenesDetalle.idTipoOrden "
                + "                                            = "
                + "                       tblDctos.idTipoOrden   "
                + "      AND  tblDctosOrdenesDetalle.idOrden   = "
                + "                           tblDctos.idOrden ) "
                + "                          )  AS tmpCXC        "
                + "GROUP BY tmpCXC.IDLOCAL,                      "
                + "         tmpCXC.IDTIPOORDEN,                  "
                + "         tmpCXC.idDcto,                       "
                + "         tmpCXC.idOrden,                      "
                + "         tmpCXC.indicador,                    "
                + "         tmpCXC.idCliente                     "
                + "HAVING ( SUM(tmpCXC.vrBase)        +          "
                + "         SUM(tmpCXC.vrIva)         +          "
                + "         SUM(tmpCXC.vrImpoconsumo) -          "
                + "         SUM(tmpCXC.vrPago)        -          "
                + "         SUM(tmpCXC.vrRteFuente)   -          "
                + "         SUM(tmpCXC.vrDsctoFcro)   -          "
                + "         SUM(tmpCXC.vrRteIva)      -          "
                + "         SUM(tmpCXC.vrRteIca) ) > 1 )         "
                + "                              AS tmpSaldo     "
                + "WHERE    tmpSaldo.indicador BETWEEN           "
                + getIndicadorInicial() + " AND                  "
                + getIndicadorFinal() + "                        "
                + "GROUP BY tmpSaldo.idLocal";

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
                fachadaBean.setNumeroDctos(rs.getInt("numeroDctos"));
                fachadaBean.setVrSaldo(rs.getDouble("vrSaldo"));

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

    // listaVentaIngresoCajaAll
    public Vector listaVentaIngresoCajaAll(String xIdTipoOrden) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        //
        String selectStr
                = "SELECT tblPagos.nitCC AS idCliente,   "
                + "  ( SELECT tblTerceros.nombreTercero  "
                + "    FROM tblTerceros                  "
                + "    WHERE tblTerceros.idCliente  =    "
                + "                      tblPagos.nitCC) "
                + "                    AS nombreTercero, "
                + "       tblPagos.idLocal,              "
                + "       tblPagos.idTipoOrden,          "
                + "       tblPagos.idRecibo,             "
                + "       tblPagos.indicador,            "
                + "       tblPagos.fechaPago,            "
                + "       tblPagos.vrPago,               "
                + "       tblPagos.vrRteFuente,          "
                + "       tblPagos.vrDescuento,          "
                + "       tblPagos.vrRteIva,             "
                + "       tblPagos.idDcto,               "
                + "       tblPagos.idDctoNitCC,          "
                + "       tblPagos.idPlanilla,           "
                + "       tblPagos.vrRteIca,             "
                + "  ( SELECT ctrlUsuarios.aliasUsuario  "
                + "    FROM ctrlUsuarios                 "
                + "    WHERE ctrlUsuarios.idUsuario =    "
                + "                tblPagos.idVendedor ) "
                + "                      AS aliasUsuario "
                + "FROM   tblPagos                       "
                + "WHERE EXISTS (                        "
                + "SELECT tblPagosMedios.*               "
                + "FROM tblPagosMedios                   "
                + "WHERE tblPagos.idLocal              = "
                + "        tblPagosMedios.idLocal        "
                + "AND tblPagos.idTipoOrden            = "
                + "    tblPagosMedios.idTipoOrden        "
                + "AND tblPagos.idRecibo               = "
                + "              tblPagosMedios.idRecibo "
                + "AND tblPagos.indicador              = "
                + "            tblPagosMedios.indicador) "
                + "AND   tblPagos.idLocal              = "
                + getIdLocal() + "                       "
                + "AND tblPagos.idTipoOrden          IN ("
                + xIdTipoOrden + ")                      "
                + "AND tblPagos.fechaPago                "
                + " BETWEEN                             '"
                + getFechaInicialSqlServer() + "'        "
                + "AND                                  '"
                + getFechaCorteSqlServer() + "'          "
                + "ORDER BY tblPagos.idLocal,            "
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
            FachadaDctoBean fachadaBean;

            while (rs.next()) {

                //
                fachadaBean = new FachadaDctoBean();

                //
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setNombreTercero(rs.getString("nombreTercero"));
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("IDTIPOORDEN"));
                fachadaBean.setIdRecibo(rs.getInt("idRecibo"));
                fachadaBean.setIndicador(rs.getInt("indicador"));
                fachadaBean.setFechaPago(
                        rs.getString("fechaPago").substring(0, 11));
                fachadaBean.setVrPago(rs.getDouble("vrPago"));
                fachadaBean.setVrRteFuente(rs.getDouble("vrRteFuente"));
                fachadaBean.setVrDescuento(rs.getDouble("vrDescuento"));
                fachadaBean.setVrRteIva(rs.getDouble("vrRteIva"));
                fachadaBean.setIdDcto(rs.getInt("idDcto"));
                fachadaBean.setIdDctoNitCC(rs.getString("idDctoNitCC"));
                fachadaBean.setIdPlanilla(rs.getInt("idPlanilla"));
                fachadaBean.setVrRteIca(rs.getDouble("vrRteIca"));
                fachadaBean.setAliasUsuario(rs.getString("aliasUsuario"));

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

    // listaVentaIngresoCajaUn
    public Vector listaVentaIngresoCajaUn(String xIdTipoOrden) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        //
        String selectStr
                = "SELECT tblPagos.nitCC AS idCliente,   "
                + "  ( SELECT tblTerceros.nombreTercero  "
                + "    FROM tblTerceros                  "
                + "    WHERE tblTerceros.idCliente  =    "
                + "                      tblPagos.nitCC) "
                + "                    AS nombreTercero, "
                + "       tblPagos.idLocal,              "
                + "       tblPagos.idTipoOrden,          "
                + "       tblPagos.idRecibo,             "
                + "       tblPagos.indicador,            "
                + "       tblPagos.fechaPago,            "
                + "       tblPagos.vrPago,               "
                + "       tblPagos.vrRteFuente,          "
                + "       tblPagos.vrDescuento,          "
                + "       tblPagos.vrRteIva,             "
                + "       tblPagos.idDcto,               "
                + "       tblPagos.idDctoNitCC,          "
                + "       tblPagos.idPlanilla,           "
                + "       tblPagos.vrRteIca,             "
                + "  ( SELECT ctrlUsuarios.aliasUsuario  "
                + "    FROM ctrlUsuarios                 "
                + "    WHERE ctrlUsuarios.idUsuario =    "
                + "                tblPagos.idVendedor ) "
                + "                      AS aliasUsuario "
                + "FROM   tblPagos                       "
                + "WHERE EXISTS (                        "
                + "SELECT tblPagosMedios.*               "
                + "FROM tblPagosMedios                   "
                + "WHERE tblPagos.idLocal              = "
                + "        tblPagosMedios.idLocal        "
                + "AND tblPagos.idTipoOrden            = "
                + "    tblPagosMedios.idTipoOrden        "
                + "AND tblPagos.idRecibo               = "
                + "              tblPagosMedios.idRecibo "
                + "AND tblPagos.indicador              = "
                + "            tblPagosMedios.indicador) "
                + "AND   tblPagos.idLocal              = "
                + getIdLocal() + "                       "
                + "AND tblPagos.idTipoOrden          IN ("
                + xIdTipoOrden + ")                      "
                + "AND tblPagos.idVendedor       =       "
                + getIdVendedor() + "                    "
                + "AND tblPagos.fechaPago                "
                + " BETWEEN                             '"
                + getFechaInicialSqlServer() + "'        "
                + "AND                                  '"
                + getFechaCorteSqlServer() + "'          "
                + "ORDER BY tblPagos.idLocal,            "
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
            FachadaDctoBean fachadaBean;

            while (rs.next()) {

                //
                fachadaBean = new FachadaDctoBean();

                //
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setNombreTercero(rs.getString("nombreTercero"));
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("IDTIPOORDEN"));
                fachadaBean.setIdRecibo(rs.getInt("idRecibo"));
                fachadaBean.setIndicador(rs.getInt("indicador"));
                fachadaBean.setFechaPago(
                        rs.getString("fechaPago").substring(0, 11));
                fachadaBean.setVrPago(rs.getDouble("vrPago"));
                fachadaBean.setVrRteFuente(rs.getDouble("vrRteFuente"));
                fachadaBean.setVrDescuento(rs.getDouble("vrDescuento"));
                fachadaBean.setVrRteIva(rs.getDouble("vrRteIva"));
                fachadaBean.setIdDcto(rs.getInt("idDcto"));
                fachadaBean.setIdDctoNitCC(rs.getString("idDctoNitCC"));
                fachadaBean.setIdPlanilla(rs.getInt("idPlanilla"));
                fachadaBean.setVrRteIca(rs.getDouble("vrRteIca"));
                fachadaBean.setAliasUsuario(rs.getString("aliasUsuario"));

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

    // listaVentaIngresoCajaTotalFCH
    public FachadaDctoBean listaVentaIngresoCajaTotalFCH(String xIdTipoOrden) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        //
        FachadaDctoBean fachadaBean = new FachadaDctoBean();

        Connection connection = null;

        //
        String selectStr
                = "SELECT tblPagos.idLocal,         "
                + "       SUM(tblPagos.vrPago)      "
                + "                      AS vrPago, "
                + "       SUM(tblPagos.vrRteFuente) "
                + "                 AS vrRteFuente, "
                + "       SUM(tblPagos.vrDescuento) "
                + "                 AS vrDescuento, "
                + "       SUM(tblPagos.vrRteIva)    "
                + "                    AS vrRteIva, "
                + "       SUM(tblPagos.vrRteIca)    "
                + "                    AS vrRteIca, "
                + "       COUNT(*) AS numeroDctos   "
                + "FROM   tblPagos                  "
                + "WHERE EXISTS (                   "
                + "SELECT tblPagosMedios.*          "
                + "FROM tblPagosMedios              "
                + "WHERE tblPagos.idLocal       =   "
                + "        tblPagosMedios.idLocal   "
                + "AND tblPagos.idTipoOrden     =   "
                + "    tblPagosMedios.idTipoOrden   "
                + "AND tblPagos.idRecibo        =   "
                + "       tblPagosMedios.idRecibo   "
                + "AND tblPagos.indicador       =   "
                + "     tblPagosMedios.indicador)   "
                + "AND   tblPagos.idLocal   =       "
                + getIdLocal() + "                  "
                + "AND tblPagos.idTipoOrden     IN ("
                + xIdTipoOrden + ")                 "
                + "AND tblPagos.fechaPago           "
                + " BETWEEN                        '"
                + getFechaInicialSqlServer() + "'   "
                + "AND                             '"
                + getFechaCorteSqlServer() + "'     "
                + "GROUP BY tblPagos.idLocal";

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
                fachadaBean.setVrPago(rs.getDouble("vrPago"));
                fachadaBean.setNumeroDctos(rs.getInt("numeroDctos"));
                fachadaBean.setVrRteFuente(rs.getDouble("vrRteFuente"));
                fachadaBean.setVrDescuento(rs.getDouble("vrDescuento"));
                fachadaBean.setVrRteIva(rs.getDouble("vrRteIva"));
                fachadaBean.setVrRteIca(rs.getDouble("vrRteIca"));

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

    // listaVentaIngresoCajaTotalUnFCH
    public FachadaDctoBean listaVentaIngresoCajaTotalUnFCH(
            String xIdTipoOrden) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        //
        FachadaDctoBean fachadaBean = new FachadaDctoBean();

        Connection connection = null;

        //
        String selectStr
                = "SELECT tblPagos.idLocal,         "
                + "       SUM(tblPagos.vrPago)      "
                + "                      AS vrPago, "
                + "       SUM(tblPagos.vrRteFuente) "
                + "                 AS vrRteFuente, "
                + "       SUM(tblPagos.vrDescuento) "
                + "                 AS vrDescuento, "
                + "       SUM(tblPagos.vrRteIva)    "
                + "                    AS vrRteIva, "
                + "       SUM(tblPagos.vrRteIca)    "
                + "                    AS vrRteIca, "
                + "       COUNT(*) AS numeroDctos   "
                + "FROM   tblPagos                  "
                + "WHERE EXISTS (                   "
                + "SELECT tblPagosMedios.*          "
                + "FROM tblPagosMedios              "
                + "WHERE tblPagos.idLocal       =   "
                + "        tblPagosMedios.idLocal   "
                + "AND tblPagos.idTipoOrden     =   "
                + "    tblPagosMedios.idTipoOrden   "
                + "AND tblPagos.idRecibo        =   "
                + "       tblPagosMedios.idRecibo   "
                + "AND tblPagos.indicador       =   "
                + "     tblPagosMedios.indicador)   "
                + "AND   tblPagos.idLocal   =       "
                + getIdLocal() + "                  "
                + "AND tblPagos.idTipoOrden     IN ("
                + xIdTipoOrden + ")                 "
                + "AND tblPagos.idVendedor       =  "
                + getIdVendedor() + "               "
                + "AND tblPagos.fechaPago           "
                + " BETWEEN                        '"
                + getFechaInicialSqlServer() + "'   "
                + "AND                             '"
                + getFechaCorteSqlServer() + "'     "
                + "GROUP BY tblPagos.idLocal";

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
                fachadaBean.setVrPago(rs.getDouble("vrPago"));
                fachadaBean.setNumeroDctos(rs.getInt("numeroDctos"));
                fachadaBean.setVrRteFuente(rs.getDouble("vrRteFuente"));
                fachadaBean.setVrDescuento(rs.getDouble("vrDescuento"));
                fachadaBean.setVrRteIva(rs.getDouble("vrRteIva"));
                fachadaBean.setVrRteIca(rs.getDouble("vrRteIca"));

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

    // listaUnDctoFCH
    public FachadaDctoBean listaUnDctoFCH() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        //
        FachadaDctoBean fachadaDctoBean = new FachadaDctoBean();

        Connection connection = null;

        //
        String selectStr
                = "SELECT tblDctos.idLocal,                   "
                + "       tblDctos.idTipoOrden,               "
                + "       tblDctos.idDcto,                    "
                + "       tblDctos.fechaDcto,                 "
                + "       tblDctos.indicador,                 "
                + "       tblDctos.idCliente,                 "
                + "       tblDctos.vrBase,                    "
                + "       tblDctos.vrIva,                     "
                + "       tblDctos.vrRteFuente,               "
                + "       tblDctos.vrDescuento,               "
                + "       tblDctos.vrRteIva,                  "
                + "       tblDctos.vrRteIca,                  "
                + "       tblDctos.vrRteCree,                 "
                + "       tblDctos.vrCostoMV,                 "
                + "       tblDctos.nombreTercero,             "
                + "       tblDctos.vrImpoconsumo,             "
                + "       tblDctos.idDctoCruce,               "
                + "       tblDctos.idLocalCruce,              "
                + "       tblDctos.idTipoOrdenCruce,          "
                + "       tblDctos.idOrdenCruce,              "
                + "       tblDctos.diasPlazo,                 "
                + "       tmpORD.observacion,                 "
                + "       tmpORD.idOrdenOrigen,               "
                + "      (SELECT tblAgendaLogVisitas.fechaTx  "
                + "       FROM tblAgendaLogVisitas            "
                + "       WHERE tblAgendaLogVisitas.idLog =   "
                + "                            tmpORD.idLog)  "
                + "                               AS fechaTx, "
                + "     ( SELECT ctrlUsuarios.nombreUsuario   "
                + "       FROM   ctrlUsuarios                 "
                + "       WHERE ctrlUsuarios.idusuario =      "
                + "                 tblDctos.idVendedor )     "
                + "                        AS nombreVendedor, "
                + "     ( SELECT ctrlUsuarios.nombreUsuario   "
                + "       FROM   ctrlUsuarios                 "
                + "       WHERE ctrlUsuarios.idusuario =      "
                + "                 tblDctos.idUsuario  )     "
                + "                        AS nombreUsuario,  "
                + "       tblDctos.idDctoNitCC,               "
                + "       tblDctos.fechaDctoNitCC,            "
                + " (SELECT tblTipoCausaNota.nombreCausa      "
                + "  FROM   tblTipoCausaNota                  "
                + "  WHERE  tblTipoCausaNota.idCausa =        "
                + " 		         tblDctos.idCausa)  "
                + "                            AS nombreCausa,"
                + "  ( tblDctos.fechaDcto +                   "
                + "        tblDctos.diasPlazo ) AS fechaVcto  "
                + "FROM   tblDctos,                           "
                + "   ( SELECT MAX(tblDctosOrdenes.formaPago) "
                + "                             AS diasPlazo, "
                + "          MAX(tblDctosOrdenes.observacion) "
                + "                           AS observacion, "
                + "            tblDctosOrdenes.idLocal,       "
                + "            tblDctosOrdenes.idTipoOrden,   "
                + "            tblDctosOrdenes.idOrden,       "
                + "            tblDctosOrdenes.idLog,         "
                + " MAX(tblDctosOrdenesDetalle.idOrdenOrigen) "
                + "                 AS idOrdenOrigen          "
                + "     FROM   tblDctosOrdenes                "
                + "     INNER JOIN tblDctosOrdenesDetalle     "
                + "     ON tblDctosOrdenes.IDLOCAL      =     "
                + "        tblDctosOrdenesDetalle.IDLOCAL     "
                + "     AND tblDctosOrdenes.IDTIPOORDEN =     "
                + "        tblDctosOrdenesDetalle.IDTIPOORDEN "
                + "     AND tblDctosOrdenes.IDORDEN     =     "
                + "        tblDctosOrdenesDetalle.IDORDEN     "
                + "     GROUP BY tblDctosOrdenes.idLocal,     "
                + "            tblDctosOrdenes.idTipoOrden,   "
                + "            tblDctosOrdenes.idOrden,       "
                + "            tblDctosOrdenes.idLog )        "
                + "                                AS tmpORD  "
                + "WHERE tblDctos.idLocal        =            "
                + getIdLocal() + "                            "
                + "AND tblDctos.idTipoOrden      =            "
                + getIdTipoOrden() + "                        "
                + "AND tblDctos.idOrden          =            "
                + getIdOrden() + "                            "
                + "AND tblDctos.idLocal          =            "
                + "                         tmpORD.idLocal    "
                + "AND tblDctos.idTipoOrden          =        "
                + "                       tmpORD.idTipoOrden  "
                + "AND tblDctos.idOrden          =            "
                + "                           tmpORD.idOrden  ";

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
                fachadaDctoBean.setIdLocal(rs.getInt("idLocal"));
                fachadaDctoBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaDctoBean.setIdDcto(rs.getDouble("idDcto"));
                fachadaDctoBean.setFechaDcto(rs.getString("fechaDcto"));
                fachadaDctoBean.setIndicador(rs.getInt("indicador"));
                fachadaDctoBean.setIdCliente(rs.getString("idCliente"));
                fachadaDctoBean.setVrBase(rs.getDouble("vrBase"));
                fachadaDctoBean.setVrIva(rs.getDouble("vrIva"));
                fachadaDctoBean.setVrRteFuente(rs.getDouble("vrRteFuente"));
                fachadaDctoBean.setVrDescuento(rs.getDouble("vrDescuento"));
                fachadaDctoBean.setVrRteIva(rs.getDouble("vrRteIva"));
                fachadaDctoBean.setVrRteIca(rs.getDouble("vrRteIca"));
                fachadaDctoBean.setVrCostoMV(rs.getDouble("vrCostoMV"));
                fachadaDctoBean.setNombreTercero(rs.getString("nombreTercero"));
                fachadaDctoBean.setVrImpoconsumo(rs.getDouble("vrImpoconsumo"));
                fachadaDctoBean.setIdDctoCruce(rs.getInt("idDctoCruce"));
                fachadaDctoBean.setIdLocalCruce(rs.getInt("idLocalCruce"));
                fachadaDctoBean.setIdTipoOrdenCruce(
                        rs.getInt("idTipoOrdenCruce"));
                fachadaDctoBean.setIdOrdenCruce(rs.getInt("idOrdenCruce"));
                fachadaDctoBean.setDiasPlazo(rs.getInt("diasPlazo"));
                fachadaDctoBean.setObservacion(rs.getString("observacion"));
                fachadaDctoBean.setIdOrdenOrigen(rs.getInt("idOrdenOrigen"));
                fachadaDctoBean.setFechaTx(rs.getString("fechaTx"));
                fachadaDctoBean.setNombreVendedor(
                        rs.getString("nombreVendedor"));
                fachadaDctoBean.setNombreUsuario(rs.getString("nombreUsuario"));
                fachadaDctoBean.setIdDctoNitCC(rs.getString("idDctoNitCC"));
                fachadaDctoBean.setFechaDctoNitCC(
                        rs.getString("fechaDctoNitCC"));
                fachadaDctoBean.setNombreCausa(rs.getString("nombreCausa"));
                fachadaDctoBean.setFechaVcto(rs.getString("fechaVcto"));
                fachadaDctoBean.setVrRteCree(rs.getDouble("vrRteCree"));
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
            return fachadaDctoBean;

        }
    }

    // listaIngresoCajaVendedor
    public Vector listaIngresoCajaVendedor(String xIdTipoOrden) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        //
        String selectStr
                = "SELECT tblPagos.idLocal,              "
                + "       tblPagos.idVendedor,           "
                + "       tblPagos.idPlanilla,           "
                + "       tblPagos.fechaPago,            "
                + "       COUNT(*) AS idDcto,            "
                + "       SUM(tblPagos.vrPago)           "
                + "                          AS vrPago,  "
                + "       SUM(tblPagos.vrRteFuente)      "
                + "                     AS vrRteFuente,  "
                + "       SUM(tblPagos.vrDescuento)      "
                + "                     AS vrDescuento,  "
                + "       SUM(tblPagos.vrRteIva)         "
                + "                        AS vrRteIva,  "
                + "       SUM(tblPagos.vrRteIca)         "
                + "                        AS vrRteIca,  "
                + "  ( SELECT ctrlUsuarios.aliasUsuario  "
                + "    FROM ctrlUsuarios                 "
                + "    WHERE ctrlUsuarios.idUsuario =    "
                + "               tblPagos.idVendedor )  "
                + "                     AS aliasUsuario  "
                + "FROM   tblPagos                      "
                + "WHERE EXISTS (                       "
                + "SELECT tblPagosMedios.*              "
                + "FROM tblPagosMedios                  "
                + "WHERE tblPagos.idLocal             = "
                + "        tblPagosMedios.idLocal       "
                + "AND tblPagos.idTipoOrden           = "
                + "    tblPagosMedios.idTipoOrden       "
                + "AND tblPagos.idRecibo              = "
                + "             tblPagosMedios.idRecibo "
                + "AND tblPagos.indicador             = "
                + "           tblPagosMedios.indicador) "
                + "AND   tblPagos.idLocal             = "
                + getIdLocal() + "                      "
                + "AND tblPagos.idTipoOrden         IN ("
                + xIdTipoOrden + ")                     "
                + "AND tblPagos.fechaPago               "
                + " BETWEEN                            '"
                + getFechaInicialSqlServer() + "'       "
                + "AND                                 '"
                + getFechaFinalSqlServer() + "'         "
                + "AND tblPagos.idVendedor           =  "
                + getIdVendedor() + "                   "
                + "GROUP BY tblPagos.idLocal,           "
                + "         tblPagos.idVendedor,        "
                + "         tblPagos.idPlanilla,        "
                + "         tblPagos.fechaPago          "
                + "ORDER BY tblPagos.idLocal,           "
                + "         tblPagos.idPlanilla         ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaDctoBean fachadaBean;

            while (rs.next()) {

                //
                fachadaBean = new FachadaDctoBean();

                //
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdVendedor(rs.getDouble("idVendedor"));
                fachadaBean.setIdPlanilla(rs.getInt("idPlanilla"));
                fachadaBean.setFechaPago(rs.getString("fechaPago"));
                fachadaBean.setIdDcto(rs.getInt("idDcto"));
                fachadaBean.setVrPago(rs.getDouble("vrPago"));
                fachadaBean.setVrRteFuente(rs.getDouble("vrRteFuente"));
                fachadaBean.setVrDescuento(rs.getDouble("vrDescuento"));
                fachadaBean.setVrRteIva(rs.getDouble("vrRteIva"));
                fachadaBean.setVrRteIca(rs.getDouble("vrRteIca"));
                fachadaBean.setAliasUsuario(rs.getString("aliasUsuario"));

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

    // Metodo listaUnRegistro
    public Vector listaUnRegistro(int xIdLog) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        Vector contenedor = new Vector();
        Connection connection = null;

        String insertStr
                = "SELECT  tblDctosOrdenesDetalle.IDLOCAL,                             "
                + "        tblDctosOrdenesDetalle.IDTIPOORDEN, 			             "
                + "        tblDctosOrdenesDetalle.IDORDEN, 			                 "
                + "        tblDctosOrdenesDetalle.porcentajeIva			             "
                + "        ,MAX(tmpDCT.idDcto) AS idDcto				                 "
                + "        ,MAX(tmpDCT.indicador) AS indicador			             "
                + "        ,MAX(tmpDCT.idCliente) AS idCliente			             "
                + "        ,MAX(tmpDCT.fechaDcto) AS fechaDcto			             "
                + "        ,MAX(tmpDCT.vrBase) AS vrBase				                 "
                + "        ,MAX(tmpDCT.vrPago) AS vrPago				                 "
                + "        ,MAX(tmpDCT.vrIva) AS vrIva				                 "
                + "        ,MAX(tmpDCT.vrRteFuente) AS vrRteFuente		             "
                + "        ,MAX(tmpDCT.vrRteIva) AS vrRteIva			                 "
                + "        ,MAX(tmpDCT.vrRteIca) AS vrRteIca			                 "
                + "        ,MAX(tmpDCT.vrDsctoFcro) AS vrDsctoFcro    	 	         "
                + "        ,MAX(tmpDCT.vrCostoMV) AS vrCostoMV			             "
                + "        ,MAX(tmpDCT.vrImpoconsumo) AS vrImpoconsumo,		         "
                + "       SUM(tblDctosOrdenesDetalle.cantidad          *		         "
                + "        (tblDctosOrdenesDetalle.vrVentaUnitario   -		         "
                + "         tblDctosOrdenesDetalle.vrImpoconsumo)    /		         "
                + "        (( 1 + ( tblDctosOrdenesDetalle.porcentajeIva /  100 ) ) *  "
                + "        ( 1 + ( tblDctosOrdenesDetalle.vrDsctoPie /  100 ) ) *      "
                + "       ( 1 + ( tblDctosOrdenesDetalle.porcentajeDscto /  100 )) ))  "
                + "                                                AS vrVentaSinDscto, "
                + "       SUM(tblDctosOrdenesDetalle.cantidad          *		         "
                + "          (tblDctosOrdenesDetalle.vrVentaUnitario   -		         "
                + "           tblDctosOrdenesDetalle.vrImpoconsumo)    *		         "
                + "       ( 1 - ( tblDctosOrdenesDetalle.vrDsctoPie /  100 ) )      *  "
                + "     ( 1 - ( tblDctosOrdenesDetalle.porcentajeDscto /  100 ) ) ) -  "
                + "       SUM(tblDctosOrdenesDetalle.cantidad          *		         "
                + "          (tblDctosOrdenesDetalle.vrVentaUnitario   -		         "
                + "           tblDctosOrdenesDetalle.vrImpoconsumo)    *		         "
                + "       ( 1 - ( tblDctosOrdenesDetalle.vrDsctoPie /  100 ) )      *  "
                + "       ( 1 - ( tblDctosOrdenesDetalle.porcentajeDscto /  100 ) ) /  "
                + "       ( 1 + ( tblDctosOrdenesDetalle.porcentajeIva /  100 ) ))     "
                + "                                                    AS vrIvaVenta,  "
                + "       SUM(tblDctosOrdenesDetalle.cantidad * 			             "
                + "           tblDctosOrdenesDetalle.vrImpoconsumo) AS vrImpoconsumo,  "
                + "       SUM(tblDctosOrdenesDetalle.vrCosto             *  	         "
                + "                      tblDctosOrdenesDetalle.cantidad )	         "
                + "                                     AS vrCostoIva		             "
                + "FROM    tblDctosOrdenes 					                         "
                + "INNER JOIN tblDctosOrdenesDetalle				                     "
                + "ON tblDctosOrdenes.IDLOCAL       = 				                 "
                + "           tblDctosOrdenesDetalle.IDLOCAL			                 "
                + "AND  tblDctosOrdenes.IDTIPOORDEN = 				                 "
                + "       tblDctosOrdenesDetalle.IDTIPOORDEN 			                 "
                + "AND tblDctosOrdenes.IDORDEN      = 				                 "
                + "           tblDctosOrdenesDetalle.IDORDEN			                 "
                + "INNER JOIN ( SELECT tblDctos.IDLOCAL				                 "
                + "                   ,tblDctos.IDTIPOORDEN			                 "
                + "                   ,tblDctos.IDORDEN				                 "
                + "                   ,tblDctos.idDcto				                 "
                + "                   ,tblDctos.indicador				                 "
                + "                   ,tblDctos.idCliente				                 "
                + "                   ,tblDctos.fechaDcto				                 "
                + "                   ,tblDctos.vrBase				                 "
                + "                   ,tblDctos.vrPago				                 "
                + "                   ,tblDctos.vrIva				                     "
                + "                   ,tblDctos.vrRteFuente			                 "
                + "                   ,tblDctos.vrRteIva			            	     "
                + "                   ,tblDctos.vrRteIca				                 "
                + "                   ,tblDctos.vrDsctoFcro			                 "
                + "                   ,tblDctos.vrCostoMV				                 "
                + "                   ,tblDctos.vrImpoconsumo			                 "
                + "             FROM tblDctos ) AS tmpDCT				                 "
                + "ON tblDctosOrdenes.IDLOCAL       = 				                 "
                + "                            tmpDCT.IDLOCAL			                 "
                + "AND  tblDctosOrdenes.IDTIPOORDEN = 				                 "
                + "                        tmpDCT.IDTIPOORDEN 			             "
                + "AND tblDctosOrdenes.IDORDEN      = 				                 "
                + "                            tmpDCT.IDORDEN			                 "
                + "WHERE tblDctosOrdenes.idLocal     =   				                 "
                + getIdLocal() + "                                                     "
                + "AND   tblDctosOrdenes.idTipoOrden =  				                 "
                + getIdTipoOrden() + "                                                 "
                + "AND   tblDctosOrdenes.idLog       =        		                 "
                + xIdLog + "                                                           "
                + "GROUP BY tblDctosOrdenesDetalle.IDLOCAL, 			                 "
                + "         tblDctosOrdenesDetalle.IDTIPOORDEN, 			             "
                + "         tblDctosOrdenesDetalle.IDORDEN, 			                 "
                + "         tblDctosOrdenesDetalle.porcentajeIva			             ";

        PreparedStatement sentenciaSQL = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            sentenciaSQL = connection.prepareStatement(insertStr);

            // Obtiene el resultset
            ResultSet rs = sentenciaSQL.executeQuery();

            // Variable de fachada de los datos
            FachadaDctoBean fachadaBean;

            while (rs.next()) {

                //
                fachadaBean = new FachadaDctoBean();

                //
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setIdOrden(rs.getInt("idOrden"));
                fachadaBean.setPorcentajeIva(rs.getDouble("porcentajeIva"));
                fachadaBean.setIdDcto(rs.getInt("idDcto"));
                fachadaBean.setIndicador(rs.getInt("indicador"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setFechaDcto(rs.getString("fechaDcto"));
                fachadaBean.setVrBase(rs.getDouble("vrBase"));
                fachadaBean.setVrPago(rs.getDouble("vrPago"));
                fachadaBean.setVrIva(rs.getDouble("vrIva"));
                fachadaBean.setVrRteFuente(rs.getDouble("vrRteFuente"));
                fachadaBean.setVrRteIva(rs.getDouble("vrRteIva"));
                fachadaBean.setVrRteIca(rs.getDouble("vrRteIca"));
                fachadaBean.setVrDsctoFcro(rs.getDouble("vrDsctoFcro"));
                fachadaBean.setVrCostoMV(rs.getDouble("vrCostoMV"));
                fachadaBean.setVrImpoconsumo(rs.getDouble("vrImpoconsumo"));
                fachadaBean.setVrVentaSinDscto(rs.getDouble("vrVentaSinDscto"));
                fachadaBean.setVrTotal(rs.getDouble("vrIvaVenta"));
                fachadaBean.setVrTotal(rs.getDouble("vrCostoIva"));

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
            // Cierra la conexion a la base de datos limpiando la statement usada y la
            jdbcAccess.cleanup(connection, sentenciaSQL, null);
            return contenedor;
        }
    }

    // Metodo listaIndicadorPluRotacion
    public Vector listaAjuste() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        String selectStr
                = "  SELECT tblDctos.IDLOCAL                        "
                + "        ,tblDctos.IDTIPOORDEN                    "
                + "        ,tblDctos.IDORDEN                        "
                + "        ,tblDctos.idDcto                         "
                + "        ,tblDctos.indicador                      "
                + "        ,tblDctos.idCliente                      "
                + "        ,tblDctos.fechaDcto                      "
                + "        ,tblDctos.vrBase                         "
                + "        ,tblDctos.vrPago                         "
                + "        ,tblDctos.idEstado                       "
                + "        ,tblDctos.vrIva                          "
                + "        ,tblDctos.idTipoNegocio                  "
                + "        ,tblDctos.vrRteFuente                    "
                + "        ,tblDctos.vrDescuento                    "
                + "        ,tblDctos.vrRteIva                       "
                + "        ,tblDctos.vrRteIca                       "
                + "        ,tblDctos.nombreTercero                  "
                + "        ,tblDctos.IDUSUARIO                      "
                + "        ,tblDctos.diasPlazo                      "
                + "        ,tblDctos.descuentoComercial             "
                + "        ,tblDctos.idCausa                        "
                + "        ,tblDctos.idDctoNitCC                    "
                + "        ,tblDctos.fechaDctoNitCC                 "
                + "        ,tblDctos.vrPagarDctoNitCC               "
                + "        ,tblDctos.vrDsctoFcro                    "
                + "        ,tblDctos.vrCostoMV                      "
                + "        ,tblDctos.idLocalCruce                   "
                + "        ,tblDctos.idTipoOrdenCruce               "
                + "        ,tblDctos.idDctoCruce                    "
                + "        ,tblDctos.idPeriodo                      "
                + "        ,tblDctos.idVendedor                     "
                + "        ,tblDctos.vrImpoconsumo                  "
                + "        ,tblDctos.vrCostoIND                     "
                + "        ,tblDctos.idOrdenCruce                   "
                + "        ,ctrlUsuarios.aliasUsuario               "
                + "        ,ctrlUsuarios.nombreUsuario              "
                + "    FROM tblDctos                                "
                + "    INNER JOIN  ctrlUsuarios                     "
                + "    ON ctrlUsuarios.idUsuario =                  "
                + "				tblDctos.idVendedor "
                + "    WHERE tblDctos.idTipoOrden =                 "
                + getIdTipoOrden()
                + "    AND (tblDctos.fechaDcto	BETWEEN            '"
                + getFechaInicialSqlServer() + "'   "
                + "  	             		AND                '"
                + getFechaFinalSqlServer() + "'     "
                + "         )                                       "
                + "    AND (tblDctos.indicador	BETWEEN             "
                + getIndicadorInicial()
                + "  				AND                 "
                + getIndicadorFinal()
                + "         )                                       "
                + "    AND  tblDctos.idLocal =                      "
                + getIdLocal()
                + "    ORDER BY idDcto                              "
                + "    	                                            ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaDctoBean fachadaBean;

            while (rs.next()) {

                fachadaBean = new FachadaDctoBean();

                //
                fachadaBean.setIdLocal(rs.getInt("IDLOCAL"));
                fachadaBean.setIdTipoOrden(rs.getInt("IDTIPOORDEN"));
                fachadaBean.setIdOrden(rs.getInt("IDORDEN"));
                fachadaBean.setIdDcto(rs.getDouble("idDcto"));
                fachadaBean.setIndicador(rs.getInt("indicador"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setFechaDcto(rs.getString("fechaDcto"));
                fachadaBean.setVrBase(rs.getDouble("vrBase"));
                fachadaBean.setVrPago(rs.getDouble("vrPago"));
                fachadaBean.setIdEstadoTx(rs.getInt("idEstado"));
                fachadaBean.setVrIva(rs.getDouble("vrIva"));
                fachadaBean.setIdTipoNegocio(rs.getInt("idTipoNegocio"));
                fachadaBean.setVrRteFuente(rs.getDouble("vrRteFuente"));
                fachadaBean.setVrDescuento(rs.getDouble("vrDescuento"));
                fachadaBean.setVrRteIca(rs.getDouble("vrRteIva"));
                fachadaBean.setNombreTercero(rs.getString("nombreTercero"));
                fachadaBean.setIdUsuario(rs.getDouble("IDUSUARIO"));
                fachadaBean.setDiasPlazo(rs.getInt("diasPlazo"));
                fachadaBean.setIdCausa(rs.getInt("idCausa"));
                fachadaBean.setIdDctoNitCC(rs.getString("idDctoNitCC"));
                fachadaBean.setFechaDctoNitCC(rs.getString("fechaDctoNitCC"));
                fachadaBean.setVrPagarDctoNitCC(rs.getDouble("vrDsctoFcro"));
                fachadaBean.setVrDsctoFcro(rs.getDouble("vrDsctoFcro"));
                fachadaBean.setVrCostoMV(rs.getDouble("vrCostoMV"));
                fachadaBean.setIdLocalCruce(rs.getInt("idLocalCruce"));
                fachadaBean.setIdTipoOrdenCruce(rs.getInt("idTipoOrdenCruce"));
                fachadaBean.setIdDctoCruce(rs.getDouble("idDctoCruce"));
                fachadaBean.setIdPeriodo(rs.getInt("idPeriodo"));
                fachadaBean.setIdVendedor(rs.getDouble("idVendedor"));
                fachadaBean.setVrImpoconsumo(rs.getDouble("vrImpoconsumo"));
                fachadaBean.setVrCostoIND(rs.getDouble("vrCostoIND"));
                fachadaBean.setIdOrdenCruce(rs.getInt("idOrdenCruce"));
                fachadaBean.setAliasUsuario(rs.getString("aliasUsuario"));
                fachadaBean.setNombreUsuario(rs.getString("nombreUsuario"));

                //
                contenedor.add(fachadaBean);

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
            return contenedor;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {

            // Cierra la conexion
            return contenedor;

        }
    }

    //
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

    public double getIdDcto() {
        return idDcto;
    }

    public void setIdDcto(double idDcto) {
        this.idDcto = idDcto;
    }

    public String getFechaDcto() {
        return fechaDcto;
    }

    public void setFechaDcto(String fechaDctoStr) {
        this.fechaDcto = fechaDctoStr;
    }

    public String getFechaVcto() {
        return fechaVcto;
    }

    public void setFechaVcto(String fechaVctoStr) {
        this.fechaVcto = fechaVctoStr;
    }

    public int getDiasMora() {
        return diasMora;
    }

    public void setDiasMora(int diasMora) {
        this.diasMora = diasMora;
    }

    public double getVrSaldo() {
        return vrSaldo;
    }

    public void setVrSaldo(double vrSaldo) {
        this.vrSaldo = vrSaldo;
    }

    public int getIndicador() {
        return indicador;
    }

    public void setIndicador(int indicador) {
        this.indicador = indicador;
    }

    public String getIdDctoNitCC() {
        return idDctoNitCC;
    }

    public void setIdDctoNitCC(String idDctoNitCC) {
        this.idDctoNitCC = idDctoNitCC;
    }

    public double getVrBase() {
        return vrBase;
    }

    public void setVrBase(double vrBase) {
        this.vrBase = vrBase;
    }

    public double getVrIva() {
        return vrIva;
    }

    public void setVrIva(double vrIva) {
        this.vrIva = vrIva;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombreTercero() {
        return nombreTercero;
    }

    public void setNombreTercero(String nombreTercero) {
        this.nombreTercero = nombreTercero;
    }

    public double getPorcentajeIva() {
        return porcentajeIva;
    }

    public void setPorcentajeIva(double porcentajeIva) {
        this.porcentajeIva = porcentajeIva;
    }

    public void setIdRecibo(int idRecibo) {
        this.idRecibo = idRecibo;
    }

    public int getIdRecibo() {
        return idRecibo;
    }

    public void setIdRecibo(String idReciboStr) {
        this.idRecibo = new Integer(idReciboStr).intValue();
    }

    public String getIdReciboStr() {
        return new Integer(idRecibo).toString();
    }

    public void setFechaPago(String fechaPago) {
        this.fechaPago = fechaPago;
    }

    public String getFechaPago() {
        return fechaPago;
    }

    public String getFechaPagoSqlServer() {

        return getFechaPago().substring(0, 4)
                + getFechaPago().substring(5, 7)
                + getFechaPago().substring(8, 10);
    }

    public String getFechaPagoFormato() {

        return getFechaPago().substring(0, 4) + "/"
                + getFechaPago().substring(5, 7) + "/"
                + getFechaPago().substring(8, 10);
    }

    public void setIdPlanilla(int idPlanilla) {
        this.idPlanilla = idPlanilla;
    }

    public int getIdPlanilla() {
        return idPlanilla;
    }

    public void setIdPlanilla(String idPlanillaStr) {
        this.idPlanilla = new Integer(idPlanillaStr).intValue();
    }

    public String getIdPlanillaStr() {
        return new Integer(idPlanilla).toString();
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

    public double getVrFactura() {
        return vrFactura;
    }

    public String getNombreVendedor() {
        return nombreVendedor;
    }

    public void setNombreVendedor(String nombreVendedor) {
        this.nombreVendedor = nombreVendedor;
    }

    public void setVrFactura(double vrFactura) {
        this.vrFactura = vrFactura;
    }

    public String getAliasUsuario() {
        return aliasUsuario;
    }

    public void setAliasUsuario(String aliasUsuario) {
        this.aliasUsuario = aliasUsuario;
    }

    public String getNombreTipoNegocio() {
        return nombreTipoNegocio;
    }

    public void setNombreTipoNegocio(String nombreTipoNegocio) {
        this.nombreTipoNegocio = nombreTipoNegocio;
    }

    public int getIdLog() {
        return idLog;
    }

    public int getIndicadorInicial() {
        return indicadorInicial;
    }

    public void setIndicadorInicial(int indicadorInicial) {
        this.indicadorInicial = indicadorInicial;
    }

    public void setIndicadorInicial(String indicadorInicialStr) {
        this.indicadorInicial = new Integer(indicadorInicialStr).intValue();
    }

    public int getIndicadorFinal() {
        return indicadorFinal;
    }

    public void setIndicadorFinal(int indicadorFinal) {
        this.indicadorFinal = indicadorFinal;
    }

    public void setIndicadorFinal(String indicadorFinalStr) {
        this.indicadorFinal = new Integer(indicadorFinalStr).intValue();
    }

    public String getFechaCorte() {
        return fechaCorte;
    }

    public void setFechaCorte(String fechaCorte) {
        this.fechaCorte = fechaCorte;
    }

    public String getFechaCorteSqlServer() {

        return getFechaCorte().substring(0, 4)
                + getFechaCorte().substring(5, 7)
                + getFechaCorte().substring(8, 10);
    }

    public String getNombreTipoOrden() {
        return nombreTipoOrden;
    }

    public void setNombreTipoOrden(String nombreTipoOrden) {
        this.nombreTipoOrden = nombreTipoOrden;
    }

    public double getMargenCliente() {
        return margenCliente;
    }

    public void setMargenCliente(double margenCliente) {
        this.margenCliente = margenCliente;
    }

    public int getIdTipoOrdenINI() {
        return idTipoOrdenINI;
    }

    public void setIdTipoOrdenINI(int idTipoOrdenINI) {
        this.idTipoOrdenINI = idTipoOrdenINI;
    }

    public int getIdTipoOrdenFIN() {
        return idTipoOrdenFIN;
    }

    public void setIdTipoOrdenFIN(int idTipoOrdenFIN) {
        this.idTipoOrdenFIN = idTipoOrdenFIN;
    }

    public int dameFacturaElectronica(int numeroDctoAntiguo) {
        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        Connection connection = null;
        int numeroFactura = 0;
        String sql = "SELECT TOP 1 \n"
                + "  [idDctoDian]\n"
                + "  FROM [BDCommerce].[dbo].[tblFacturaElectronica] \n"
                + "  WHERE idDcto =" + numeroDctoAntiguo + " AND idTipoOrden = 9;";

        PreparedStatement sentenciaSQL = null;

        try {
            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();
            // Prepara la sentencia de Busqueda
            sentenciaSQL = connection.prepareStatement(sql);
            // Obtiene el resultset
            ResultSet rs = sentenciaSQL.executeQuery();
            // Variable de fachada de los datos

            while (rs.next()) {
                numeroFactura = rs.getInt("idDctoDian");
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
            // conexion
            
            jdbcAccess.cleanup(connection, sentenciaSQL, null);

        }

        return numeroFactura;
    }
}
