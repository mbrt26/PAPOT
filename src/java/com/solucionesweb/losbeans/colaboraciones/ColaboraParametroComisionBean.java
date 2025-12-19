/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.solucionesweb.losbeans.colaboraciones;

import com.solucionesweb.lasayudas.JDBCAccess;
import com.solucionesweb.losbeans.fachada.FachadaParametroComisionBean;
import com.solucionesweb.losbeans.utilidades.IConstantes;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.naming.NamingException;

/**
 *
 * @author PLASTICAUCA
 */
public class ColaboraParametroComisionBean extends FachadaParametroComisionBean implements Serializable, IConstantes {

  	// Nombre JNDI del Data Source que esta clase requiere
    private static final String DATA_SOURCE_NAME = "CommerceAccess";

	// Clase helper usada para acceder a la base de datos
	private JDBCAccess jdbcAccess;

	// Metodo constructor por defecto sin parametros
        public ColaboraParametroComisionBean() {

  	// Al instanciar el Bean establece la conexion a la base de datos
	   jdbcAccess = new JDBCAccess(DATA_SOURCE_NAME);
    }
        
        // Metodo listaUnLocal
    public Vector listaDetalleLucro() {
   
       //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
      Class tipoObjeto       = this.getClass();
      String nombreClase     = tipoObjeto.getName();
      Vector contenedor      = new Vector();

      boolean    validaOk    = false;
      Connection connection  = null;     

      //
      String selectStr =
                  "SELECT  [idLucro]\n"
                + "      ,[nombreLucro]\n"
                + "      ,[diaInicial]\n"
                + "      ,[diaFinal]\n"
                + "      ,[porcentaje]\n"
                + "      ,[estado]\n"
                + "      ,[idSeq]\n"
                + "  FROM [tblComisionRecaudo]";

        PreparedStatement selectStatement = null;

      try {

        // Obtiene una conexion a la base de datos, requiere Try
        connection = jdbcAccess.getConnection();

        // Prepara la sentencia de Busqueda
        selectStatement = connection.prepareStatement(selectStr);

        // Obtiene el resultset
        ResultSet rs = selectStatement.executeQuery();

        // Variable de fachada de los datos
           FachadaParametroComisionBean fachadaBean;

        while (rs.next()) {
            
              fachadaBean  = new FachadaParametroComisionBean();  

              fachadaBean.setIdLucro(rs.getString("idLucro"));
              fachadaBean.setNombreLucro(rs.getString("nombreLucro"));
              fachadaBean.setDiaInicial(rs.getDouble("diaInicial"));
              fachadaBean.setDiaFinal(rs.getDouble("diaFinal"));
              fachadaBean.setPorcentaje(rs.getDouble("porcentaje"));
              fachadaBean.setEstado(rs.getInt("estado"));

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
    
        // Metodo listaUnLocal
    public FachadaParametroComisionBean listaParametro(String idLucro) {
   
              //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        Connection connection = null;
        
        FachadaParametroComisionBean fachadaBean = new FachadaParametroComisionBean(); 

      //
      String selectStr =
                "SELECT  [idLucro]          \n"
              + "      ,[nombreLucro]       \n"
              + "      ,[diaInicial]        \n"
              + "      ,[diaFinal]          \n"
              + "      ,[porcentaje]        \n"
              + "      ,[estado]            \n"
              + "      ,[idSeq]             \n"
              + "  FROM [tblComisionRecaudo]  "
              + "WHERE idLucro =              "
              + idLucro + "                   ";

        PreparedStatement selectStatement = null;

      try {

        // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();  

        while (rs.next()) {
            
              

              fachadaBean.setIdLucro(rs.getString("idLucro"));
              fachadaBean.setNombreLucro(rs.getString("nombreLucro"));
              fachadaBean.setDiaInicial(rs.getDouble("diaInicial"));
              fachadaBean.setDiaFinal(rs.getDouble("diaFinal"));
              fachadaBean.setPorcentaje(rs.getDouble("porcentaje"));
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

            // Cierra la conexion
            jdbcAccess.cleanup(connection, selectStatement, null);
            return fachadaBean;
        }
    }
    
     public boolean actualizaParametro(String idLucro,String NombreRango,
             String DiaInicial,String DiaFinal,String Porcentaje) {
        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okActualizarDctoOrdenFactura = false;

        Vector contenedor = new Vector();
        Connection connection = null;

        String selectStr
                = " UPDATE tblComisionRecaudo                 "
                + "SET nombreLucro =                    '"
                + NombreRango + "'                    "
                + " ,diaInicial =             "
                + DiaInicial + "               "
                + " ,diaFinal =                 "
                + DiaFinal + "                   "
                + "       ,porcentaje =                          "
                + Porcentaje + "                            "
                + " WHERE tblComisionRecaudo.idLucro  = "
                + idLucro + "                        ";

        PreparedStatement selectStatement = null;

        try {
            connection = this.jdbcAccess.getConnection();

            selectStatement = connection.prepareStatement(selectStr);

            selectStatement.execute();
            okActualizarDctoOrdenFactura = true;

            this.jdbcAccess.cleanup(connection, selectStatement, null);
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
            return okActualizarDctoOrdenFactura;
        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {

            // Cierra el connection
            jdbcAccess.cleanup(connection, selectStatement, null);
            return okActualizarDctoOrdenFactura;
        }
    }
     
     // Metodo listaRecaudoEfectivo
    public Vector listaRecaudoEfectivo(String fechaInicial,String fechaFinal) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

//        String selectStr =
//                "SELECT tmpCMS.*,                                "
//                + "       tmpCMS.vrPago * 		         "
//                + "                tmpCMS.porcentajeComision/100 	 "
//                + "                                AS vrComision	 "
//                + "FROM (					         "
//                + "SELECT   tblDctos.IDLOCAL,		         "
//                + "         tblDctos.IDTIPOORDEN,		         "
//                + "         tblDctos.IDORDEN,	             	 "
//                + "         tblDctos.idDcto,	            	 "
//                + "         tblDctos.fechaDcto,			 "
//                + "         tblDctos.indicador,		         "
//                + "         tblDctos.idCliente,			 "
//                + "         tblPagos.idRecibo,			 "
//                + "         tblPagos.fechaPago,			 "
//                + "         (tblPagos.vrPago -			 "
//                + "  ((tblDctos.vrBase ) -(((tblDctos.vrBase)*0.4)/100))- "
//                + "     tblDctos.vrRteFuente - tblPagos.vrRteIva - "
//                + "     tblPagos.vrRteCree - tblPagos.vrRteFuente - "
//                + "     tblPagos.vrRteIca -                      "
//                + "         tblPagos.vrPagoCambio )		 "
//                + "                     AS vrPago ,		 "
//                + "         tblPagos.idVendedor,			 "
//                + "         DATEDIFF(dd, tblDctos.fechaDcto ,	 "
//                + "                      tblPagos.fechaPago)	 "
//                + "                                AS diasPago,    "
//                + "( SELECT tblComisionRecaudo.porcentaje	 "
//                + "  FROM   tblComisionRecaudo		 "
//                + "  WHERE DATEDIFF(dd, tblDctos.fechaDcto ,    	 "
//                + "                      tblPagos.fechaPago)    	 "
//                + "  BETWEEN tblComisionRecaudo.diaInicial  "
//                + "  AND tblComisionRecaudo.diaFinal )	 "
//                + "                       AS porcentajeComision,	 "
//                + "( SELECT tblComisionRecaudo.idLucro	 "
//                + "  FROM   tblComisionRecaudo		 "
//                + "  WHERE DATEDIFF(dd, tblDctos.fechaDcto ,	 "
//                + "                      tblPagos.fechaPago)	 "
//                + "  BETWEEN tblComisionRecaudo.diaInicial	 "
//                + "  AND tblComisionRecaudo.diaFinal )	 "
//                + "                       AS idLucro,          	 "
//                + "       ( SELECT ctrlUsuarios.aliasUsuario       "
//                + "         FROM ctrlUsuarios                      "
//                + "         WHERE ctrlUsuarios.idUsuario =         "
//                + "                         tblPagos.idVendedor)   "
//                + "                             AS nombreVendedor, "
//                + "       ( SELECT TOP 1 tblTerceros.nombreTercero "
//                + "         FROM tblTerceros                       "
//                + "         WHERE tblTerceros.idCliente  =         "
//                + "                              tblPagos.nitCC)   "
//                + "                              AS nombreTercero  "
//                + "FROM  tblDctos					 "
//                + "INNER JOIN tblPagos				 "
//                + "ON  tblDctos.IDLOCAL     = tblPagos.idLocal	 "
//                + "AND tblDctos.IDTIPOORDEN = tblPagos.idTipoOrden "
//                + "AND tblDctos.indicador   = tblPagos.indicador	 "
//                + "AND tblDctos.idDcto      = tblPagos.idDcto	 "
//                + "AND ( tblPagos.idTipoOrden  = 9             "
//                + "           OR tblDctos.idTipoOrden = 29 )                      "
//                + "AND tblPagos.indicador BETWEEN 1 AND 2                      "
//                + "AND tblPagos.fechaPago BETWEEN                 '"
//                + fechaInicial + "'  AND            '"
//                + fechaFinal + "')       AS tmpCMS	 "
//                + "ORDER BY tmpCMS.idLocal,			 "
//                + "         tmpCMS.idVendedor,		         "
//                + "         tmpCMS.idLucro,		         "
//                + "         tmpCMS.idRecibo			 ";
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
                + "AND ( tblPagos.idTipoOrden  =  9                    "
                + "           OR tblDctos.idTipoOrden = 29  )     "
                + "AND tblPagos.indicador BETWEEN  1 AND  2       "
                + "AND tblPagos.fechaPago BETWEEN                 '"
                + fechaInicial + "'  AND            '"
                + fechaFinal + "'                    "
       
                + " )               AS tmpCMS	 "
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
            FachadaParametroComisionBean fachadaBean;

            while (rs.next()) {

                fachadaBean = new FachadaParametroComisionBean();

                //
                fachadaBean.setIdDcto(rs.getInt("idDcto"));
                fachadaBean.setFechaFactura(rs.getString("fechaDcto"));
                fachadaBean.setIdTercero(rs.getString("idCliente"));
                fachadaBean.setIdRecibo(rs.getInt("idRecibo"));
                fachadaBean.setFechaPago(rs.getString("fechaPago"));
                fachadaBean.setVrPagado(rs.getDouble("vrPago"));
                fachadaBean.setIdDias(rs.getInt("diasPago"));
                fachadaBean.setPorcentaje(
                        rs.getDouble("porcentajeComision"));
                fachadaBean.setIdLucro(rs.getInt("idLucro"));
                fachadaBean.setVrComision(
                        rs.getDouble("vrComision"));
                fachadaBean.setNombreUsuario(rs.getString("nombreVendedor"));
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

    // Metodo listaRecaudoEfectivoUn
    public Vector listaRecaudoEfectivoUn(String fehcaInicial, String fechaFinal, String vendedor) {

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
                + "AND ( tblPagos.idTipoOrden  =  9                    "
                + "           OR tblDctos.idTipoOrden = 29  )     "
                + "AND tblPagos.indicador BETWEEN  1 AND  2       "
                + "AND tblPagos.fechaPago BETWEEN                 '"
                + fehcaInicial + "'  AND            '"
                + fechaFinal + "'                    "
                + "AND tblPagos.idVendedor            =            "
                + vendedor + " )               AS tmpCMS	 "
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
            FachadaParametroComisionBean fachadaBean;

            while (rs.next()) {

                fachadaBean = new FachadaParametroComisionBean();

                //
                   fachadaBean.setIdDcto(rs.getInt("idDcto"));
                fachadaBean.setFechaFactura(rs.getString("fechaDcto"));
                fachadaBean.setIdTercero(rs.getString("idCliente"));
                fachadaBean.setIdRecibo(rs.getInt("idRecibo"));
                fachadaBean.setFechaPago(rs.getString("fechaPago"));
                fachadaBean.setVrPagado(rs.getDouble("vrPago"));
                fachadaBean.setIdDias(rs.getInt("diasPago"));
                fachadaBean.setPorcentaje(
                        rs.getDouble("porcentajeComision"));
                fachadaBean.setIdLucro(rs.getInt("idLucro"));
                fachadaBean.setVrComision(
                        rs.getDouble("vrComision"));
                fachadaBean.setNombreUsuario(rs.getString("nombreVendedor"));
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
    
}
