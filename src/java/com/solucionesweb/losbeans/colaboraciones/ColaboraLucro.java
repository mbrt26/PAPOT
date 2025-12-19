package com.solucionesweb.losbeans.colaboraciones;

// Importa la clase helper JDBCAccess que contiene los accesos a la base de datos
import com.solucionesweb.lasayudas.JDBCAccess;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaPagoBean;

// Importa los paquetes de java
import java.sql.*;
import java.io.*;
import java.util.*;
import java.io.IOException.*;

// Importa los paquetes de javax
import javax.naming.*;

public class ColaboraLucro extends FachadaPagoBean implements Serializable {

    // Nombre JNDI del Data Source que esta clase requiere
    private static final String DATA_SOURCE_NAME = "CommerceAccess";
    // Clase helper usada para acceder a la base de datos
    private JDBCAccess jdbcAccess;

    // Metodo constructor por defecto sin parametros
    public ColaboraLucro() {

        // Al instanciar el Bean establece la conexion a la base de datos
        jdbcAccess = new JDBCAccess(DATA_SOURCE_NAME);
    }

    // Metodo listaComisionSenior
    public Vector listaComisionSenior() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        String selectStr =
                "SELECT tmpDFT.*,                                      "
                + "       ( SELECT ctrlUsuarios.aliasUsuario             "
                + "         FROM ctrlUsuarios                            "
                + "         WHERE ctrlUsuarios.idUsuario =               "
                + "                         tmpDFT.idVendedor)           "
                + "                           AS nombreVendedor,         "
                + "       ( SELECT TOP 1 tblTerceros.nombreTercero       "
                + "         FROM tblTerceros                             "
                + "         WHERE tblTerceros.idCliente  =               "
                + "                         tmpDFT.nitCC)                "
                + "                           AS nombreTercero           "
                + "FROM                                                  "
                + "(SELECT tmpCMS.*,                                     "
                + "       idLucro = CASE                                 "
                + "         WHEN (tmpCMS.diasPago <= tmpCMS.diasPlazo)   "
                + "                     THEN 1                           "
                + "          WHEN (tmpCMS.diasPago > tmpCMS.diasPlazo)   "
                + "             AND tmpCMS.diasPago <=                   "
                + "           ( tmpCMS.diasPlazo + tmpCMS.diasExcluidos) "
                + "                                              THEN 2  "
                + "          ELSE 3                                      "
                + "        END,                                          "
                + "       vrComision = CASE                              "
                + "         WHEN (tmpCMS.diasPago <= tmpCMS.diasPlazo)   "
                + "             THEN ( tmpCMS.vrPago *                   "
                + "                      tmpCMS.porcentajeComision/100 ) "
                + "          WHEN (tmpCMS.diasPago > tmpCMS.diasPlazo)   "
                + "             AND tmpCMS.diasPago <=                   "
                + "           ( tmpCMS.diasPlazo +                       "
                + "                      tmpCMS.diasExcluidos ) THEN 0.0 "
                + "          ELSE ( tmpCMS.vrPago *                      "
                + "               tmpCMS.porcentajeSancion/100 * (-1.0)) "
                + "        END,                                          "
                + "       porcentajeComisionEfectiva = CASE              "
                + "         WHEN (tmpCMS.diasPago <= tmpCMS.diasPlazo)   "
                + "             THEN ( tmpCMS.porcentajeComision)        "
                + "          WHEN (tmpCMS.diasPago > tmpCMS.diasPlazo)   "
                + "             AND tmpCMS.diasPago <=                   "
                + "           ( tmpCMS.diasPlazo +                       "
                + "                     tmpCMS.diasExcluidos ) THEN 0.0  "
                + "          ELSE ( tmpCMS.porcentajeSancion * (-1.0))   "
                + "        END                                           "
                + "FROM (                                                "
                + "SELECT   tblDctos.IDLOCAL, 			               "
                + "         tblDctos.IDTIPOORDEN, 		               "
                + "         tblDctos.IDORDEN, 		         	       "
                + "         tblDctos.idDcto, 			                   "
                + "         tblDctos.fechaDcto,           		       "
                + "         tblDctos.indicador, 			               "
                + "         tblDctos.idCliente AS nitCC,                 "
                + "         tblPagos.idRecibo, 			               "
                + "         tblPagos.fechaPago, 			               "
                + "         (tblPagos.vrPago - 		         	       "
                + "          tblPagos.vrPagoCambio ) 		               "
                + "                     AS vrPago , 		               "
                + "         tblPagos.idVendedor,			               "
                + "         DATEDIFF(dd, tblDctos.fechaDcto , 	       "
                + "                      tblPagos.fechaPago) 	           "
                + "                                 AS diasPago,	       "
                + getPorcentajeComision() + " AS porcentajeComision,     "
                + getDiasPlazo() + " AS diasPlazo,                       "
                + getDiasExcluidos() + " AS diasExcluidos,               "
                + getPorcentajeSancion() + " AS porcentajeSancion        "
                + "FROM  tblDctos					                       "
                + "INNER JOIN tblPagos				                   "
                + "ON  tblDctos.IDLOCAL     = tblPagos.idLocal	       "
                + "AND tblDctos.IDTIPOORDEN = tblPagos.idTipoOrden       "
                + "AND tblDctos.indicador   = tblPagos.indicador	       "
                + "AND tblDctos.idDcto      = tblPagos.idDcto	           "
                + "AND  tblPagos.idLocal    =      		               "
                + getIdLocal() + "                                       "
                + "AND  ( tblPagos.idTipoOrden=   		               "
                + getIdTipoOrdenInicial() + "                            "
                + "OR   tblPagos.idTipoOrden=   		                   "
                + getIdTipoOrdenFinal() + " )                            "
                + "AND  tblPagos.indicador  BETWEEN                      "
                + getIndicadorInicial() + " AND                          "
                + getIndicadorFinal() + "                                "
                + "AND  tblPagos.fechaPago       BETWEEN                '"
                + getFechaInicialSqlServer() + "'     AND               '"
                + getFechaFinalSqlServer() + "')         AS tmpCMS)      "
                + "                                       AS tmpDFT      "
                + "ORDER BY tmpDFT.IDLOCAL, 			                   "
                + "         tmpDFT.idVendedor,			               "
                + "         tmpDFT.idLucro,   			               "
                + "         tmpDFT.idRecibo 			                   ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaPagoBean fachadaBean;

            while (rs.next()) {

                fachadaBean = new FachadaPagoBean();

                //
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setIdOrden(rs.getInt("idOrden"));
                fachadaBean.setIdDcto(rs.getInt("idDcto"));
                fachadaBean.setFechaDcto(rs.getString("fechaDcto"));
                fachadaBean.setIndicador(rs.getInt("indicador"));
                fachadaBean.setNitCC(rs.getString("nitCC"));
                fachadaBean.setIdRecibo(rs.getInt("idRecibo"));
                fachadaBean.setFechaPago(rs.getString("fechaPago"));
                fachadaBean.setVrPago(rs.getDouble("vrPago"));
                fachadaBean.setDiasPago(rs.getInt("diasPago"));
                fachadaBean.setPorcentajeComision(
                        rs.getDouble("porcentajeComision"));
                fachadaBean.setDiasPlazo(rs.getInt("diasPlazo"));
                fachadaBean.setDiasExcluidos(rs.getInt("diasExcluidos"));
                fachadaBean.setPorcentajeSancion(
                        rs.getDouble("porcentajeSancion"));
                fachadaBean.setIdLucro(rs.getInt("idLucro"));
                fachadaBean.setVrComision(
                        rs.getDouble("vrComision"));
                fachadaBean.setPorcentajeComisionEfectiva(
                        rs.getDouble("porcentajeComisionEfectiva"));
                fachadaBean.setNombreVendedor(rs.getString("nombreVendedor"));
                fachadaBean.setNombreCliente(rs.getString("nombreTercero"));

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

    // Metodo listaComisionSeniorUn
    public Vector listaComisionSeniorUn() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        String selectStr =
                "SELECT tmpDFT.*,                                      "
                + "       ( SELECT ctrlUsuarios.aliasUsuario             "
                + "         FROM ctrlUsuarios                            "
                + "         WHERE ctrlUsuarios.idUsuario =               "
                + "                         tmpDFT.idVendedor)           "
                + "                           AS nombreVendedor,         "
                + "       ( SELECT TOP 1 tblTerceros.nombreTercero       "
                + "         FROM tblTerceros                             "
                + "         WHERE tblTerceros.idCliente  =               "
                + "                         tmpDFT.nitCC)                "
                + "                           AS nombreTercero           "
                + "FROM                                                  "
                + "(SELECT tmpCMS.*,                                     "
                + "       idLucro = CASE                                 "
                + "         WHEN (tmpCMS.diasPago <= tmpCMS.diasPlazo)   "
                + "                     THEN 1                           "
                + "          WHEN (tmpCMS.diasPago > tmpCMS.diasPlazo)   "
                + "             AND tmpCMS.diasPago <=                   "
                + "           ( tmpCMS.diasPlazo + tmpCMS.diasExcluidos) "
                + "                                              THEN 2  "
                + "          ELSE 3                                      "
                + "        END,                                          "
                + "       vrComision = CASE                              "
                + "         WHEN (tmpCMS.diasPago <= tmpCMS.diasPlazo)   "
                + "             THEN ( tmpCMS.vrPago *                   "
                + "                      tmpCMS.porcentajeComision/100 ) "
                + "          WHEN (tmpCMS.diasPago > tmpCMS.diasPlazo)   "
                + "             AND tmpCMS.diasPago <=                   "
                + "           ( tmpCMS.diasPlazo +                       "
                + "                      tmpCMS.diasExcluidos ) THEN 0.0 "
                + "          ELSE ( tmpCMS.vrPago *                      "
                + "               tmpCMS.porcentajeSancion/100 * (-1.0)) "
                + "        END,                                          "
                + "       porcentajeComisionEfectiva = CASE              "
                + "         WHEN (tmpCMS.diasPago <= tmpCMS.diasPlazo)   "
                + "             THEN ( tmpCMS.porcentajeComision)        "
                + "          WHEN (tmpCMS.diasPago > tmpCMS.diasPlazo)   "
                + "             AND tmpCMS.diasPago <=                   "
                + "           ( tmpCMS.diasPlazo +                       "
                + "                     tmpCMS.diasExcluidos ) THEN 0.0  "
                + "          ELSE ( tmpCMS.porcentajeSancion * (-1.0))   "
                + "        END                                           "
                + "FROM (                                                "
                + "SELECT   tblDctos.IDLOCAL, 			               "
                + "         tblDctos.IDTIPOORDEN, 		               "
                + "         tblDctos.IDORDEN, 		         	       "
                + "         tblDctos.idDcto, 			                   "
                + "         tblDctos.fechaDcto,           		       "
                + "         tblDctos.indicador, 			               "
                + "         tblDctos.idCliente AS nitCC,                 "
                + "         tblPagos.idRecibo, 			               "
                + "         tblPagos.fechaPago, 			               "
                + "         (tblPagos.vrPago - 		         	       "
                + "          tblPagos.vrPagoCambio ) 		               "
                + "                     AS vrPago , 		               "
                + "         tblPagos.idVendedor,			               "
                + "         DATEDIFF(dd, tblDctos.fechaDcto , 	       "
                + "                      tblPagos.fechaPago) 	           "
                + "                                 AS diasPago,	       "
                + getPorcentajeComision() + " AS porcentajeComision,     "
                + getDiasPlazo() + " AS diasPlazo,                       "
                + getDiasExcluidos() + " AS diasExcluidos,               "
                + getPorcentajeSancion() + " AS porcentajeSancion        "
                + "FROM  tblDctos					                       "
                + "INNER JOIN tblPagos				                   "
                + "ON  tblDctos.IDLOCAL     = tblPagos.idLocal	       "
                + "AND tblDctos.IDTIPOORDEN = tblPagos.idTipoOrden       "
                + "AND tblDctos.indicador   = tblPagos.indicador	       "
                + "AND tblDctos.idDcto      = tblPagos.idDcto	           "
                + "AND  tblPagos.idLocal    =      		               "
                + getIdLocal() + "                                       "
                + "AND  ( tblPagos.idTipoOrden=   		               "
                + getIdTipoOrdenInicial() + "                            "
                + "OR   tblPagos.idTipoOrden=   		                   "
                + getIdTipoOrdenFinal() + " )                            "
                + "AND  tblPagos.indicador  BETWEEN                      "
                + getIndicadorInicial() + " AND                          "
                + getIndicadorFinal() + "                                "
                + "AND  tblPagos.idVendedor =                            "
                + getIdVendedor() + "                                    "
                + "AND  tblPagos.fechaPago       BETWEEN                '"
                + getFechaInicialSqlServer() + "'     AND               '"
                + getFechaFinalSqlServer() + "')         AS tmpCMS)      "
                + "                                       AS tmpDFT      "
                + "ORDER BY tmpDFT.IDLOCAL, 			                   "
                + "         tmpDFT.idVendedor,			               "
                + "         tmpDFT.idLucro,   			               "
                + "         tmpDFT.idRecibo 			                   ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaPagoBean fachadaBean;

            while (rs.next()) {

                fachadaBean = new FachadaPagoBean();

                //
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setIdOrden(rs.getInt("idOrden"));
                fachadaBean.setIdDcto(rs.getInt("idDcto"));
                fachadaBean.setFechaDcto(rs.getString("fechaDcto"));
                fachadaBean.setIndicador(rs.getInt("indicador"));
                fachadaBean.setNitCC(rs.getString("nitCC"));
                fachadaBean.setIdRecibo(rs.getInt("idRecibo"));
                fachadaBean.setFechaPago(rs.getString("fechaPago"));
                fachadaBean.setVrPago(rs.getDouble("vrPago"));
                fachadaBean.setDiasPago(rs.getInt("diasPago"));
                fachadaBean.setPorcentajeComision(
                        rs.getDouble("porcentajeComision"));
                fachadaBean.setDiasPlazo(rs.getInt("diasPlazo"));
                fachadaBean.setDiasExcluidos(rs.getInt("diasExcluidos"));
                fachadaBean.setPorcentajeSancion(
                        rs.getDouble("porcentajeSancion"));
                fachadaBean.setIdLucro(rs.getInt("idLucro"));
                fachadaBean.setVrComision(
                        rs.getDouble("vrComision"));
                fachadaBean.setPorcentajeComisionEfectiva(
                        rs.getDouble("porcentajeComisionEfectiva"));
                fachadaBean.setNombreVendedor(rs.getString("nombreVendedor"));
                fachadaBean.setNombreCliente(rs.getString("nombreTercero"));

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

    // Metodo listaRecaudoEfectivo
    public Vector listaRecaudoEfectivo() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        String selectStr =
                "SELECT tmpCMS.*,                                "
                + "       tmpCMS.vrPago * 		         "
                + "                tmpCMS.porcentajeComision/100 	 "
                + "                                AS vrComision	 "
                + "FROM (					         "
                + "SELECT   tblDctos.IDLOCAL,		         "
                + "         tblDctos.IDTIPOORDEN,		         "
                + "         tblDctos.IDORDEN,	             	 "
                + "         tblDctos.idDcto,	            	 "
                + "         tblDctos.fechaDcto,			 "
                + "         tblDctos.indicador,		         "
                + "         tblDctos.idCliente,			 "
                + "         tblPagos.idRecibo,			 "
                + "         tblPagos.fechaPago,			 "
                + "         (tblPagos.vrPago -			 "
                + "  (((tblDctos.vrBase ) -(((tblDctos.vrBase)*0.4)/100))- "
                + "     tblDctos.vrRteFuente - tblPagos.vrRteIva - "
                + "     tblPagos.vrRteCree - tblPagos.vrRteFuente - "
                + "     tblPagos.vrRteIca -                      "
                + "         tblPagos.vrPagoCambio ))		 "
                + "                     AS vrPago ,		 "
                + "         tblPagos.idVendedor,			 "
                + "         DATEDIFF(dd, tblDctos.fechaDcto ,	 "
                + "                      tblPagos.fechaPago)	 "
                + "                                AS diasPago,    "
                + "( SELECT tblComisionRecaudo.porcentaje	 "
                + "  FROM   tblComisionRecaudo		 "
                + "  WHERE DATEDIFF(dd, tblDctos.fechaDcto ,    	 "
                + "                      tblPagos.fechaPago)    	 "
                + "  BETWEEN tblComisionRecaudo.diaInicial  "
                + "  AND tblComisionRecaudo.diaFinal )	 "
                + "                       AS porcentajeComision,	 "
                + "( SELECT tblComisionRecaudo.idLucro	 "
                + "  FROM   tblComisionRecaudo		 "
                + "  WHERE DATEDIFF(dd, tblDctos.fechaDcto ,	 "
                + "                      tblPagos.fechaPago)	 "
                + "  BETWEEN tblComisionRecaudo.diaInicial	 "
                + "  AND tblComisionRecaudo.diaFinal )	 "
                + "                       AS idLucro,          	 "
                + "       ( SELECT ctrlUsuarios.aliasUsuario       "
                + "         FROM ctrlUsuarios                      "
                + "         WHERE ctrlUsuarios.idUsuario =         "
                + "                         tblPagos.idVendedor)   "
                + "                             AS nombreVendedor, "
                + "       ( SELECT TOP 1 tblTerceros.nombreTercero "
                + "         FROM tblTerceros                       "
                + "         WHERE tblTerceros.idCliente  =         "
                + "                              tblPagos.nitCC)   "
                + "                              AS nombreTercero  "
                + "FROM  tblDctos					 "
                + "INNER JOIN tblPagos				 "
                + "ON  tblDctos.IDLOCAL     = tblPagos.idLocal	 "
                + "AND tblDctos.IDTIPOORDEN = tblPagos.idTipoOrden "
                + "AND tblDctos.indicador   = tblPagos.indicador	 "
                + "AND tblDctos.idDcto      = tblPagos.idDcto	 "
                + "AND tblPagos.idLocal     =   		         "
                + getIdLocal() + "                                 "
                + "AND ( tblPagos.idTipoOrden  =              	 "
                + getIdTipoOrdenInicial() + "                      "
                + "           OR tblDctos.idTipoOrden =            "
                + getIdTipoOrdenFinal() + " )                      "
                + "AND tblPagos.indicador BETWEEN  	         "
                + getIndicadorInicial() + " AND            	 "
                + getIndicadorFinal() + "                          "
                + "AND tblPagos.fechaPago BETWEEN                 '"
                + getFechaInicialSqlServer() + "'  AND            '"
                + getFechaFinalSqlServer() + "')       AS tmpCMS	 "
                + "ORDER BY tmpCMS.idLocal,			 "
                + "         tmpCMS.idVendedor,		         "
                + "         tmpCMS.idLucro,		         "
                + "         tmpCMS.idRecibo			 ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaPagoBean fachadaBean;

            while (rs.next()) {

                fachadaBean = new FachadaPagoBean();

                //
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setIdOrden(rs.getInt("idOrden"));
                fachadaBean.setIdDcto(rs.getInt("idDcto"));
                fachadaBean.setFechaDcto(rs.getString("fechaDcto"));
                fachadaBean.setIndicador(rs.getInt("indicador"));
                fachadaBean.setNitCC(rs.getString("idCliente"));
                fachadaBean.setIdRecibo(rs.getInt("idRecibo"));
                fachadaBean.setFechaPago(rs.getString("fechaPago"));
                fachadaBean.setVrPago(rs.getDouble("vrPago"));
                fachadaBean.setDiasPago(rs.getInt("diasPago"));
                fachadaBean.setPorcentajeComision(
                        rs.getDouble("porcentajeComision"));
                fachadaBean.setIdLucro(rs.getInt("idLucro"));
                fachadaBean.setVrComision(
                        rs.getDouble("vrComision"));
                fachadaBean.setNombreVendedor(rs.getString("nombreVendedor"));
                fachadaBean.setNombreCliente(rs.getString("nombreTercero"));

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

    // Metodo listaRecaudoEfectivoUn
    public Vector listaRecaudoEfectivoUn() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        String selectStr =
                "SELECT tmpCMS.*,                                "
                + "       tmpCMS.vrPago * 			 "
                + "                tmpCMS.porcentajeComision/100 	 "
                + "                                AS vrComision	 "
                + "FROM (						 "
                + "SELECT   tblDctos.IDLOCAL,			 "
                + "         tblDctos.IDTIPOORDEN,		         "
                + "         tblDctos.IDORDEN,	             	 "
                + "         tblDctos.idDcto,	            	 "
                + "         tblDctos.fechaDcto,			 "
                + "         tblDctos.indicador,		         "
                + "         tblDctos.idCliente,			 "
                + "         tblPagos.idRecibo,			 "
                + "         tblPagos.fechaPago,			 "
                + "  (((tblDctos.vrBase ) -(((tblDctos.vrBase)*0.4)/100))- "
                + "     tblDctos.vrRteFuente - tblPagos.vrRteIva - "
                + "     tblPagos.vrRteCree - tblPagos.vrRteFuente - "
                + "     tblPagos.vrRteIca -                      "
                + "         tblPagos.vrPagoCambio )		 "
                + "                     AS vrPago ,		 "
                + "         tblPagos.idVendedor,			 "
                + "         DATEDIFF(dd, tblDctos.fechaDcto ,	 "
                + "                      tblPagos.fechaPago)	 "
                + "                                AS diasPago,    "
                + "( SELECT tblComisionRecaudo.porcentaje	 "
                + "  FROM   tblComisionRecaudo		 "
                + "  WHERE DATEDIFF(dd, tblDctos.fechaDcto ,    	 "
                + "                      tblPagos.fechaPago)    	 "
                + "  BETWEEN tblComisionRecaudo.diaInicial  "
                + "  AND tblComisionRecaudo.diaFinal )	 "
                + "                       AS porcentajeComision,	 "
                + "( SELECT tblComisionRecaudo.idLucro	 "
                + "  FROM   tblComisionRecaudo		 "
                + "  WHERE DATEDIFF(dd, tblDctos.fechaDcto ,	 "
                + "                      tblPagos.fechaPago)	 "
                + "  BETWEEN tblComisionRecaudo.diaInicial	 "
                + "  AND tblComisionRecaudo.diaFinal )	 "
                + "                       AS idLucro,          	 "
                + "       ( SELECT ctrlUsuarios.aliasUsuario       "
                + "         FROM ctrlUsuarios                      "
                + "         WHERE ctrlUsuarios.idUsuario =         "
                + "                         tblPagos.idVendedor)   "
                + "                             AS nombreVendedor, "
                + "       ( SELECT TOP 1 tblTerceros.nombreTercero "
                + "         FROM tblTerceros                       "
                + "         WHERE tblTerceros.idCliente  =         "
                + "                              tblPagos.nitCC)   "
                + "                              AS nombreTercero  "
                + "FROM  tblDctos					 "
                + "INNER JOIN tblPagos				 "
                + "ON  tblDctos.IDLOCAL     = tblPagos.idLocal	 "
                + "AND tblDctos.IDTIPOORDEN = tblPagos.idTipoOrden "
                + "AND tblDctos.indicador   = tblPagos.indicador	 "
                + "AND tblDctos.idDcto      = tblPagos.idDcto	 "
                + "AND tblDctos.idCliente   = tblPagos.nitCC       "
                + "AND tblPagos.idLocal     =   		         "
                + getIdLocal() + "                                 "
                + "AND ( tblPagos.idTipoOrden  =              	 "
                + getIdTipoOrdenInicial() + "                      "
                + "           OR tblDctos.idTipoOrden =            "
                + getIdTipoOrdenFinal() + " )                      "
                + "AND tblPagos.indicador BETWEEN  	         "
                + getIndicadorInicial() + " AND            	 "
                + getIndicadorFinal() + "                          "
                + "AND tblPagos.fechaPago BETWEEN                 '"
                + getFechaInicialSqlServer() + "'  AND            '"
                + getFechaFinalSqlServer() + "'                    "
                + "AND tblPagos.idVendedor            =            "
                + getIdVendedor() + " )               AS tmpCMS	 "
                + "ORDER BY tmpCMS.idLocal,			 "
                + "         tmpCMS.idVendedor,		         "
                + "         tmpCMS.idLucro,		         "
                + "         tmpCMS.idRecibo			 ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaPagoBean fachadaBean;

            while (rs.next()) {

                fachadaBean = new FachadaPagoBean();

                //
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setIdOrden(rs.getInt("idOrden"));
                fachadaBean.setIdDcto(rs.getInt("idDcto"));
                fachadaBean.setFechaDcto(rs.getString("fechaDcto"));
                fachadaBean.setIndicador(rs.getInt("indicador"));
                fachadaBean.setNitCC(rs.getString("idCliente"));
                fachadaBean.setIdRecibo(rs.getInt("idRecibo"));
                fachadaBean.setFechaPago(rs.getString("fechaPago"));
                fachadaBean.setVrPago(rs.getDouble("vrPago"));
                fachadaBean.setDiasPago(rs.getInt("diasPago"));
                fachadaBean.setPorcentajeComision(
                        rs.getDouble("porcentajeComision"));
                fachadaBean.setIdLucro(rs.getInt("idLucro"));
                fachadaBean.setVrComision(
                        rs.getDouble("vrComision"));
                fachadaBean.setNombreVendedor(rs.getString("nombreVendedor"));
                fachadaBean.setNombreCliente(rs.getString("nombreTercero"));

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

    // Metodo listaVentaMesUn
    public Vector listaVentaMesUn() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        String selectStr =
                "SELECT tblDctos.idLocal,                   "
                + "       tblDctos.idTipoOrden,               "
                + "       tblDctos.idOrden,                   "
                + "       tblDctos.idDcto,                    "
                + "       tblDctos.indicador,                 "
                + "       tblDctos.fechaDcto,                 "
                + "       tblDctos.idCliente,                 "
                + "       (tblDctos.vrBase -                  "
                + "       tblDctos.vrRteFuente) AS vrTotal,   "
                + "       tblDctos.nombreTercero,             "
                + "       ( SELECT ctrlUsuarios.aliasUsuario  "
                + "         FROM ctrlUsuarios                 "
                + "         WHERE ctrlUsuarios.idUsuario =    "
                + "                      tblDctos.idVendedor) "
                + "                        AS nombreVendedor  "
                + "FROM  tblDctos                             "
                + "WHERE  tblDctos.idLocal     =              "
                + getIdLocal() + "                            "
                + "AND ( tblDctos.idTipoOrden  =              "
                + getIdTipoOrdenInicial() + "                 "
                + "          OR tblDctos.idTipoOrden =        "
                + getIdTipoOrdenFinal() + "  )                "
                + "AND tblDctos.indicador BETWEEN             "
                + getIndicadorInicial() + " AND               "
                + getIndicadorFinal() + "                     "
                + "AND tblDctos.fechaDcto BETWEEN            '"
                + getFechaInicialSqlServer() + "'  AND       '"
                + getFechaFinalSqlServer() + "'               "
                + "AND tblDctos.idVendedor           =        "
                + getIdVendedor() + "               	    "
                + "ORDER BY tblDctos.idLocal,                 "
                + "         tblDctos.idVendedor,              "
                + "         tblDctos.fechaDcto                ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaPagoBean fachadaBean;

            while (rs.next()) {

                fachadaBean = new FachadaPagoBean();

                //
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setIdOrden(rs.getInt("idOrden"));
                fachadaBean.setIdDcto(rs.getInt("idDcto"));
                fachadaBean.setFechaDcto(rs.getString("fechaDcto"));
                fachadaBean.setIndicador(rs.getInt("indicador"));
                fachadaBean.setNitCC(rs.getString("idCliente"));
                fachadaBean.setVrTotal(rs.getDouble("vrTotal"));
                fachadaBean.setNombreVendedor(rs.getString("nombreVendedor"));
                fachadaBean.setNombreCliente(rs.getString("nombreTercero"));

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

    // Metodo listaTotalVentaMesUn
    public double listaTotalVentaMesUn() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        //
        double xTotalVenta = 0.0;

        //
        Connection connection = null;

        String selectStr =
                "SELECT tblDctos.idLocal,             "
                + "         tblDctos.idVendedor,        "
                + "SUM (tblDctos.vrBase -               "
                + "     tblDctos.vrRteFuente)           "
                + "                          AS vrTotal "
                + "FROM  tblDctos                       "
                + "WHERE  tblDctos.idLocal     =        "
                + getIdLocal() + "                      "
                + "AND ( tblDctos.idTipoOrden  =        "
                + getIdTipoOrdenInicial() + "           "
                + "          OR tblDctos.idTipoOrden =  "
                + getIdTipoOrdenFinal() + "  )          "
                + "AND tblDctos.indicador BETWEEN       "
                + getIndicadorInicial() + " AND         "
                + getIndicadorFinal() + "               "
                + "AND tblDctos.fechaDcto BETWEEN      '"
                + getFechaInicialSqlServer() + "'  AND '"
                + getFechaFinalSqlServer() + "'         "
                + "AND tblDctos.idVendedor           =  "
                + getIdVendedor() + "                   "
                + "GROUP BY tblDctos.idLocal,           "
                + "         tblDctos.idVendedor         ";

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
                xTotalVenta = rs.getDouble("vrTotal");

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
            return xTotalVenta;

        }
    }
}
