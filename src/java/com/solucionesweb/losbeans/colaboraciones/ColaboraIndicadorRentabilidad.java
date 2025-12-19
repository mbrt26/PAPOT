package com.solucionesweb.losbeans.colaboraciones;

// Importa la clase helper JDBCAccess que contiene los accesos a la base de datos
import com.solucionesweb.lasayudas.JDBCAccess;

// Importa el bean de utilidades
import com.solucionesweb.losbeans.fachada.FachadaColaboraReporteDctoBean;
import com.solucionesweb.losbeans.utilidades.IConstantes;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaDctoBean;

// Importa los paquetes de java
import java.sql.*;
import java.io.*;
import java.util.*;
import java.io.IOException.*;

// Importa los paquetes de javax
import javax.naming.*;


public class ColaboraIndicadorRentabilidad
        extends FachadaDctoBean
        implements Serializable, IConstantes {

   /* private int idTipoOrdenINI;
    private int idTipoOrdenFIN;
    private int indicadorInicial;
    private int indicadorFinal;*/

     // Nombre JNDI del Data Source que esta clase requiere
    private static final String DATA_SOURCE_NAME = "CommerceAccess";

    // Clase helper usada para acceder a la base de datos
    private JDBCAccess jdbcAccess;

    // Metodo constructor por defecto sin parametros
    public ColaboraIndicadorRentabilidad() {

        // Al instanciar el Bean establece la conexion a la base de datos
        jdbcAccess = new JDBCAccess(DATA_SOURCE_NAME);
        
    }



     // Metodo listaIndicadorRentabilidadCliente
    public Vector listaIndicadorRentabilidadCliente() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        String selectStr =

" SELECT  tmpDET.idCliente,                                                  " +
"         tmpDET.idLocal,                                                    " +
"         tmpDET.nombreTercero,                                              " +
"         SUM(tmpDET.vrBase)                                                 " +
"             AS vrBase,                                                     " +
"         SUM(tmpDET.vrCostoIND)                                             " +
"             AS vrCostoIND,                                                 " +
"         SUM(tmpDET.vrIva)                                                  " +
"             AS vrIva,                                                      " +
"         SUM(tmpDET.vrCostoMV)                                              " +
"             AS vrCostoMV,                                                  " +
"         (CASE                                                              " +
"          WHEN (SUM(tmpDET.vrBase))= 0                                      " +
"          THEN (SUM(tmpDET.vrBase))                                         " +
"          ELSE (SUM(tmpDET.vrBase)-                                         " +
"         SUM(tmpDET.vrCostoIND))/                                           " +
"         SUM(tmpDET.vrBase)                                                 " +
"         END) AS margenCliente                                              " +
"                                                                            " +
" FROM (                                                                     " +
" SELECT        tblDctos.idLocal,                                            " +
"               tblDctos.idTipoOrden,                                        " +
"               tblDctos.idDcto,                                             " +
"               tblDctos.idOrden,                                            " +
"               tblDctos.fechaDcto,                                          " +
"               tblDctos.indicador,                                          " +
"               tblDctos.idCliente,                                          " +
"               (CASE WHEN                                                   " +
"                       tblDctos.indicador = 1                               " +
"                THEN   tblDctos.vrBase                                      " +
"                ELSE   ( tblDctos.vrBase +                                  " +
"                         tblDctos.vrIva )                                   " +
"                END) AS vrBase,                                             " +
"                                                                            " +
"                (CASE WHEN                                                  " +
"                         tblDctos.indicador = 1                             " +
"                 THEN    tblDctos.vrIva                                     " +
"                 ELSE    0                                                  " +
"                 END) AS vrIva,                                             " +
"                tblDctos.vrImpoconsumo,                                     " +
"                tblDctos.nombreTercero,                                     " +
"                tblDctos.idDctoNitCC,                                       " +
"                tblDctos.fechaDctoNitCC,                                    " +
"                tblDctos.idLocalCruce,                                      " +
"                tblDctos.idTipoOrdenCruce,                                  " +
"                tblDctos.idDctoCruce,                                       " +
"                (tblDctos.vrBase      +                                     " +
"                tblDctos.vrIva       +                                      " +
"                tblDctos.vrImpoconsumo -                                    " +
"                tblDctos.vrRteFuente   -                                    " +
"                tblDctos.vrRteIva) AS vrFactura,                            " +
"                tblDctos.vrDescuento,                                       " +
"                tblDctos.vrRteFuente,                                       " +
"                tmpDOR.vrCostoMV,                                           " +
"                tblDctos.vrCostoIND,                                        " +
"                (CASE WHEN                                                  " +
"                       tblDctos.idTipoOrden = 1                             " +
"                THEN    tblDctos.idDctoNitCC                                " +
"                ELSE    STR(tblDctos.idDcto)                                " +
"                END) AS idDctoStr,                                          " +
"                (CASE WHEN                                                  " +
"                       tblDctos.idTipoNegocio = 1                           " +
"                THEN      'CONTADO'                                         " +
"                ELSE      'CREDITO'                                         " +
"                END) AS nombreTipoNegocio,                                  " +
"                       tblDctos.idTipoNegocio,                              " +
"                ( SELECT   ctrlUsuarios.aliasUsuario                        " +
"                  FROM     ctrlUsuarios                                     " +
"                  WHERE    ctrlUsuarios.idUsuario =                         " +
"                           tblDctos.idVendedor )                            " +
"                AS aliasUsuario                                             " +
"                FROM   tblDctos,                                            " +
"                                                                            " +
"                ( SELECT  tblDctosOrdenesDetalle.IDLOCAL                    " +
"                          , tblDctosOrdenesDetalle.IDTIPOORDEN              " +
"                          , tblDctosOrdenesDetalle.IDORDEN                  " +
"                          ,SUM( tblDctosOrdenesDetalle.CANTIDAD *           " +
"                          tblDctosOrdenesDetalle.vrCosto ) AS vrCostoMV     " +
"                          FROM tblDctosOrdenesDetalle                       " +
"                          GROUP BY  tblDctosOrdenesDetalle.IDLOCAL          " +
"                                    , tblDctosOrdenesDetalle.IDTIPOORDEN    " +
"                                    , tblDctosOrdenesDetalle.IDORDEN )      " +
                "                                                 AS tmpDOR  " +
"                                                                            " +
"                  WHERE  tblDctos.idCliente NOT IN ('1', '10000000',        " +
"                                                           '10000', '1000') " +
"                 AND EXISTS                                                 " +
"                    ( SELECT *                                              " +
"                      FROM   tblDctosOrdenes                                " +
"                      INNER JOIN tblDctosOrdenesDetalle                     " +
"                      ON tblDctosOrdenes.IDLOCAL      =                     " +
"                         tblDctosOrdenesDetalle.IDLOCAL                     " +
"                      AND tblDctosOrdenes.IDTIPOORDEN =                     " +
"                         tblDctosOrdenesDetalle.IDTIPOORDEN                 " +
"                      AND tblDctosOrdenes.IDORDEN     =                     " +
"                         tblDctosOrdenesDetalle.IDORDEN                     " +
"                      WHERE tblDctosOrdenes.IDLOCAL   =                     " +
"                                           tblDctos.IDLOCAL                 " +
"                      AND tblDctosOrdenes.IDTIPOORDEN =                     " +
"                                       tblDctos.IDTIPOORDEN                 " +
"                      AND tblDctosOrdenes.IDORDEN     =                     " +
"                                          tblDctos.IDORDEN)                 " +
"                     AND tblDctos.idLocal   =                               " +
                                getIdLocal() + "                             " +
"                     AND tblDctos.fechaDcto BETWEEN                        '" +
                                getFechaInicialSqlServer() + "'              " +
"                                            AND                            '" +
                                getFechaFinalSqlServer() + "'                " +
"                     AND ( tblDctos.idTipoOrden  =                          " +
                                getIdTipoOrdenINI() + "                      " +
"                            OR tblDctos.idTipoOrden =                       " +
                                getIdTipoOrdenFIN() + "                      " +
"                         )                                                  " +
"                     AND tblDctos.indicador BETWEEN                         " +
                                getIndicadorInicial() + "                    " +
"                                            AND                             " +
                                getIndicadorFinal() + "                      " +
"                     AND tmpDOR.idLocal = tblDctos.IDLOCAL                  " +
"                     AND tmpDOR.idTipoOrden = tblDctos.idTipoOrden          " +
"                     AND tmpDOR.idOrden = tblDctos.idOrden) AS tmpDET       " +
"                     WHERE  tmpDET.vrBase != 0                              " +
"                     GROUP BY tmpDET.idCliente,                             " +
"                              tmpDET.idLocal,                               " +
"                              tmpDET.nombreTercero                          " +
"                     ORDER BY margenCliente DESC    " ;

        
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
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setNombreTercero(rs.getString("nombreTercero"));
                fachadaBean.setVrBase(rs.getDouble("vrBase"));
                fachadaBean.setVrCostoIND(rs.getDouble("vrCostoIND"));
                fachadaBean.setVrIva(rs.getDouble("vrIva"));
                fachadaBean.setVrCostoMV(rs.getDouble("vrCostoMV"));
                fachadaBean.setMargenCliente(rs.getDouble("margenCliente"));

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


   // Metodo listaIndicadorRentabilidadCliente
    public FachadaDctoBean listaIndicadorRentabilidadClienteTotal() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        //
        FachadaDctoBean fachadaBean = new FachadaDctoBean();

        Connection connection = null;

        String selectStr =

" SELECT                                                                     " +
"         tmpDET.idLocal,                                                    " +
"         SUM(tmpDET.vrBase)                                                 " +
"             AS vrBase,                                                     " +
"         SUM(tmpDET.vrCostoIND)                                             " +
"             AS vrCostoIND,                                                 " +
"         SUM(tmpDET.vrIva)                                                  " +
"             AS vrIva,                                                      " +
"         SUM(tmpDET.vrCostoMV)                                              " +
"             AS vrCostoMV,                                                  " +
"         (CASE                                                              " +
"          WHEN (SUM(tmpDET.vrBase))= 0                                      " +
"          THEN (SUM(tmpDET.vrBase))                                         " +
"          ELSE (SUM(tmpDET.vrBase)-                                         " +
"         SUM(tmpDET.vrCostoIND))/                                           " +
"         SUM(tmpDET.vrBase)                                                 " +
"         END) AS margenCliente                                              " +
"                                                                            " +
" FROM (                                                                     " +
" SELECT        tblDctos.idLocal,                                            " +
"               tblDctos.idTipoOrden,                                        " +
"               tblDctos.idDcto,                                             " +
"               tblDctos.idOrden,                                            " +
"               tblDctos.fechaDcto,                                          " +
"               tblDctos.indicador,                                          " +
"               tblDctos.idCliente,                                          " +
"               (CASE WHEN                                                   " +
"                       tblDctos.indicador = 1                               " +
"                THEN   tblDctos.vrBase                                      " +
"                ELSE   ( tblDctos.vrBase +                                  " +
"                         tblDctos.vrIva )                                   " +
"                END) AS vrBase,                                             " +
"                                                                            " +
"                (CASE WHEN                                                  " +
"                         tblDctos.indicador = 1                             " +
"                 THEN    tblDctos.vrIva                                     " +
"                 ELSE    0                                                  " +
"                 END) AS vrIva,                                             " +
"                tblDctos.vrImpoconsumo,                                     " +
"                tblDctos.nombreTercero,                                     " +
"                tblDctos.idDctoNitCC,                                       " +
"                tblDctos.fechaDctoNitCC,                                    " +
"                tblDctos.idLocalCruce,                                      " +
"                tblDctos.idTipoOrdenCruce,                                  " +
"                tblDctos.idDctoCruce,                                       " +
"                (tblDctos.vrBase      +                                     " +
"                tblDctos.vrIva       +                                      " +
"                tblDctos.vrImpoconsumo -                                    " +
"                tblDctos.vrRteFuente   -                                    " +
"                tblDctos.vrRteIva) AS vrFactura,                            " +
"                tblDctos.vrDescuento,                                       " +
"                tblDctos.vrRteFuente,                                       " +
"                tmpDOR.vrCostoMV,                                           " +
"                tblDctos.vrCostoIND,                                        " +
"                (CASE WHEN                                                  " +
"                       tblDctos.idTipoOrden = 1                             " +
"                THEN    tblDctos.idDctoNitCC                                " +
"                ELSE    STR(tblDctos.idDcto)                                " +
"                END) AS idDctoStr,                                          " +
"                (CASE WHEN                                                  " +
"                       tblDctos.idTipoNegocio = 1                           " +
"                THEN      'CONTADO'                                         " +
"                ELSE      'CREDITO'                                         " +
"                END) AS nombreTipoNegocio,                                  " +
"                       tblDctos.idTipoNegocio,                              " +
"                ( SELECT   ctrlUsuarios.aliasUsuario                        " +
"                  FROM     ctrlUsuarios                                     " +
"                  WHERE    ctrlUsuarios.idUsuario =                         " +
"                           tblDctos.idVendedor )                            " +
"                AS aliasUsuario                                             " +
"                FROM   tblDctos,                                            " +
"                                                                            " +
"                ( SELECT  tblDctosOrdenesDetalle.IDLOCAL                    " +
"                          , tblDctosOrdenesDetalle.IDTIPOORDEN              " +
"                          , tblDctosOrdenesDetalle.IDORDEN                  " +
"                          ,SUM( tblDctosOrdenesDetalle.CANTIDAD *           " +
"                          tblDctosOrdenesDetalle.vrCosto ) AS vrCostoMV     " +
"                          FROM tblDctosOrdenesDetalle                       " +
"                          GROUP BY  tblDctosOrdenesDetalle.IDLOCAL          " +
"                                    , tblDctosOrdenesDetalle.IDTIPOORDEN    " +
"                                    , tblDctosOrdenesDetalle.IDORDEN )      " +
                "                                                 AS tmpDOR  " +
"                                                                            " +
"                  WHERE  tblDctos.idCliente NOT IN ('1', '10000000',        " +
"                                                           '10000', '1000') " +
"                 AND EXISTS                                                 " +
"                    ( SELECT *                                              " +
"                      FROM   tblDctosOrdenes                                " +
"                      INNER JOIN tblDctosOrdenesDetalle                     " +
"                      ON tblDctosOrdenes.IDLOCAL      =                     " +
"                         tblDctosOrdenesDetalle.IDLOCAL                     " +
"                      AND tblDctosOrdenes.IDTIPOORDEN =                     " +
"                         tblDctosOrdenesDetalle.IDTIPOORDEN                 " +
"                      AND tblDctosOrdenes.IDORDEN     =                     " +
"                         tblDctosOrdenesDetalle.IDORDEN                     " +
"                      WHERE tblDctosOrdenes.IDLOCAL   =                     " +
"                                           tblDctos.IDLOCAL                 " +
"                      AND tblDctosOrdenes.IDTIPOORDEN =                     " +
"                                       tblDctos.IDTIPOORDEN                 " +
"                      AND tblDctosOrdenes.IDORDEN     =                     " +
"                                          tblDctos.IDORDEN)                 " +
"                     AND tblDctos.idLocal   =                               " +
                                getIdLocal() + "                             " +
"                     AND tblDctos.fechaDcto BETWEEN                        '" +
                                getFechaInicialSqlServer() + "'              " +
"                                            AND                            '" +
                                getFechaFinalSqlServer() + "'                " +
"                     AND ( tblDctos.idTipoOrden  =                          " +
                                getIdTipoOrdenINI() + "                      " +
"                            OR tblDctos.idTipoOrden =                       " +
                                getIdTipoOrdenFIN() + "                      " +
"                         )                                                  " +
"                     AND tblDctos.indicador BETWEEN                         " +
                                getIndicadorInicial() + "                    " +
"                                            AND                             " +
                                getIndicadorFinal() + "                      " +
"                     AND tmpDOR.idLocal = tblDctos.IDLOCAL                  " +
"                     AND tmpDOR.idTipoOrden = tblDctos.idTipoOrden          " +
"                     AND tmpDOR.idOrden = tblDctos.idOrden) AS tmpDET       " +
"                     WHERE  tmpDET.vrBase != 0                              " +
"                     GROUP BY tmpDET.idLocal ";


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
                fachadaBean.setVrBase(rs.getDouble("vrBase"));
                fachadaBean.setVrCostoIND(rs.getDouble("vrCostoIND"));
                fachadaBean.setVrIva(rs.getDouble("vrIva"));
                fachadaBean.setVrCostoMV(rs.getDouble("vrCostoMV"));
                fachadaBean.setMargenCliente(rs.getDouble("margenCliente"));


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

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {

            // Cierra la conexion
            return fachadaBean;

        }
    }

      // Metodo listaIndicadorRentabilidadCliente
    public Vector listaIndicadorRentabilidadVendPlu() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        String selectStr =

" SELECT  tmpRES.aliasUsuario,                                              " +
"  		tmpRES.idPlu,                                               " +
"  		tmpRES.nombrePlu,                                           " +
"     	tmpRES.nombreMarca,                                                 " +
" 		tmpRES.idVendedor,                                          " +
" 		tmpRES.vrCostoIND,                                          " +
" 		tmpRES.margenIND,                                           " +
" 		tmpRES.vrCostoMV,                                           " +
" 		tmpRES.vrPago,                                              " +
" 		tmpRES.Cantidad,                                            " +
" 		(CASE WHEN                                                  " +
"                              tmpRES.indicador = 1                         " +
"                         THEN    tmpRES.vrBase                             " +
"                         ELSE    ( tmpRES.vrBase +                         " +
"                                   tmpRES.vrIva )                          " +
"                         END) AS vrBase,                                   " +
"                                                                           " +
"                        (CASE WHEN                                         " +
"                              tmpRES.indicador = 1                         " +
"                         THEN    tmpRES.vrIva                              " +
"                         ELSE    0                                         " +
"                         END) AS vrIva                                     " +
"                                                                           " +
"                                                                           " +
" FROM (                                                                    " +
" SELECT  tmpDET.aliasUsuario,                                              " +
"  		tmpDET.idPlu,                                               " +
"  		tmpDET.nombrePlu,                                           " +
"     	tmpDET.nombreMarca,                                                 " +
" 		tmpDET.idVendedor,                                          " +
"         tmpDET.indicador,                                                 " +
" 		SUM(tmpDET.Cantidad) AS Cantidad,                           " +
" 		SUM((tmpDET.vrCosto * tmpDET.Cantidad) /                    " +
"                   (1 +( tmpDET.porcentajeIva)/100) )                      " +
"                                                 AS vrCostoMv,             " +
" 		SUM(tmpDET.vrCostoIND * tmpDET.Cantidad )  AS vrCostoIND,   " +
" 		SUM(tmpDET.vrPago)	   AS vrPago,                       " +
" 		SUM(tmpDET.cantidad          *                              " +
"         (tmpDET.vrVentaUnitario   -                                       " +
"         tmpDET.vrImpoconsumo)    *                                        " +
"        ( 1 - ( tmpDET.vrDsctoPie /  100 ) )      *                        " +
"        ( 1 - ( tmpDET.porcentajeDscto /  100 ) ) /                        " +
"        ( 1 + ( tmpDET.porcentajeIva /  100 ) ))     AS vrBase,            " +
" 	   SUM(tmpDET.cantidad          *                                   " +
"        (tmpDET.vrVentaUnitario   -                                        " +
"        tmpDET.vrImpoconsumo)    *                                         " +
"        ( 1 - ( tmpDET.vrDsctoPie /  100 ) )      *                        " +
"        ( 1 - ( tmpDET.porcentajeDscto /  100 ) ) ) -                      " +
"        SUM(tmpDET.cantidad          *                                     " +
"        (tmpDET.vrVentaUnitario   -                                        " +
"        tmpDET.vrImpoconsumo)    *                                         " +
"        ( 1 - ( tmpDET.vrDsctoPie /  100 ) )      *                        " +
"        ( 1 - ( tmpDET.porcentajeDscto /  100 ) ) /                        " +
"        ( 1 + ( tmpDET.porcentajeIva /  100 ) )) AS vrIva,                 " +
" 	   (CASE                                                            " +
" 		WHEN SUM(tmpDET.cantidad          *                         " +
"         (tmpDET.vrVentaUnitario   -                                       " +
"         tmpDET.vrImpoconsumo)    *                                        " +
"        ( 1 - ( tmpDET.vrDsctoPie /  100 ) )      *                        " +
"        ( 1 - ( tmpDET.porcentajeDscto /  100 ) ) /                        " +
"        ( 1 + ( tmpDET.porcentajeIva /  100 ) )) = 0                       " +
" 		THEN 0                                                      " +
" 		ELSE (SUM(tmpDET.cantidad          *                        " +
"         (tmpDET.vrVentaUnitario   -                                       " +
"         tmpDET.vrImpoconsumo)    *                                        " +
"        ( 1 - ( tmpDET.vrDsctoPie /  100 ) )      *                        " +
"        ( 1 - ( tmpDET.porcentajeDscto /  100 ) ) /                        " +
"        ( 1 + ( tmpDET.porcentajeIva /  100 ) )) -                         " +
"         SUM(tmpDET.vrCostoIND * tmpDET.Cantidad )) /                      " +
"         SUM(tmpDET.cantidad          *                                    " +
"         (tmpDET.vrVentaUnitario   -                                       " +
"         tmpDET.vrImpoconsumo)    *                                        " +
"        ( 1 - ( tmpDET.vrDsctoPie /  100 ) )      *                        " +
"        ( 1 - ( tmpDET.porcentajeDscto /  100 ) ) /                        " +
"        ( 1 + ( tmpDET.porcentajeIva /  100 ) ))                           " +
" 		END)                                                        " +
"        	   AS margenIND                                             " +
" FROM (SELECT tblDctos.idLocal,                                            " +
"                        tblDctos.idTipoOrden,                              " +
" 					   tblDctos.idOrden,                " +
"                        tblDctos.idDcto,                                   " +
"                        tblDctos.fechaDcto,                                " +
"                        tblDctos.indicador,                                " +
"                        tblDctos.idCliente,                                " +
" 		               tblDctos.idVendedor,                         " +
" 		       (CASE WHEN                                           " +
"                              tblDctos.indicador = 1                       " +
"                         THEN    tblDctos.vrBase                           " +
"                         ELSE    ( tblDctos.vrBase +                       " +
"                                   tblDctos.vrIva )                        " +
"                         END) AS vrBase,                                   " +
"                                                                           " +
"                        (CASE WHEN                                         " +
"                              tblDctos.indicador = 1                       " +
"                         THEN    tblDctos.vrIva                            " +
"                         ELSE    0                                         " +
"                         END) AS vrIva,                                    " +
"                                                                           " +
"                        tblDctos.nombreTercero,                            " +
"                        tblDctos.idDctoNitCC,                              " +
"                        tblDctos.fechaDctoNitCC,                           " +
"                        tblDctos.idLocalCruce,                             " +
"                        tblDctos.idTipoOrdenCruce,                         " +
" 						tblDctos.idOrdenCruce,      " +
"                        tblDctos.idDctoCruce,                              " +
"                        tblDctos.vrCostoMV,                                " +
"                                                                           " +
"                        tblDctos.vrPago,                                   " +
" 		       tblDctosOrdenesDetalle.nombrePlu,                    " +
" 			   tblPlus.idPlu,                                   " +
" 		       tblMarcas.nombreMarca,                               " +
" 		       tblDctosOrdenesDetalle.Cantidad,                     " +
" 			   tblDctosOrdenesDetalle.vrVentaUnitario,          " +
" 			   tblDctosOrdenesDetalle.vrVentaOriginal,          " +
" 			   tblDctosOrdenesDetalle.vrDsctoPie,               " +
" 			   tblDctosOrdenesDetalle.porcentajeDscto,          " +
" 			   tblDctosOrdenesDetalle.porcentajeIva,            " +
" 			   tblDctosOrdenesDetalle.vrImpoconsumo,            " +
" 			   tblDctosOrdenesDetalle.vrCosto,                  " +
" 			   tblDctosOrdenesDetalle.vrCostoIND,               " +
"                                                                           " +
"                        ( SELECT ctrlUsuarios.aliasUsuario                 " +
"                          FROM ctrlUsuarios                                " +
"                          WHERE ctrlUsuarios.idUsuario =                   " +
"                                      tblDctos.idVendedor )                " +
"                                            AS aliasUsuario                " +
"                 FROM   tblDctos                                           " +
" 		INNER JOIN tblDctosOrdenesDetalle                           " +
" 		ON tblDctosOrdenesDetalle.IDLOCAL                           " +
" 				= tblDctos.IDLOCAL                          " +
" 		AND tblDctosOrdenesDetalle.IDORDEN                          " +
" 				= tblDctos.IDORDEN                          " +
" 		AND tblDctosOrdenesDetalle.IDTIPOORDEN                      " +
" 				= tblDctos.IDTIPOORDEN                      " +
" 		INNER JOIN tblPlus                                          " +
" 		ON tblPlus.idPlu                                            " +
" 				= tblDctosOrdenesDetalle.IDPLU              " +
" 		INNER JOIN tblMarcas                                        " +
" 		ON tblMarcas.idMarca                                        " +
" 				= tblPlus.idMarca                           " +
"                 WHERE EXISTS                                              " +
"                    ( SELECT *                                             " +
"                      FROM   tblDctosOrdenes                               " +
"                      INNER JOIN tblDctosOrdenesDetalle                    " +
"                      ON tblDctosOrdenes.IDLOCAL      =                    " +
"                         tblDctosOrdenesDetalle.IDLOCAL                    " +
"                      AND tblDctosOrdenes.IDTIPOORDEN =                    " +
"                         tblDctosOrdenesDetalle.IDTIPOORDEN                " +
"                      AND tblDctosOrdenes.IDORDEN     =                    " +
"                         tblDctosOrdenesDetalle.IDORDEN                    " +
"                      WHERE tblDctosOrdenes.IDLOCAL   =                    " +
"                                           tblDctos.IDLOCAL                " +
"                      AND tblDctosOrdenes.IDTIPOORDEN =                    " +
"                                       tblDctos.IDTIPOORDEN                " +
"                      AND tblDctosOrdenes.IDORDEN     =                    " +
"                                          tblDctos.IDORDEN)                " +
"                 AND tblDctos.idLocal   =                                  " +
                            getIdLocal() +
"                 AND tblDctos.fechaDcto BETWEEN                           '" +
                            getFechaInicialSqlServer() + "'                 " +
"                                              AND                         '" +
                            getFechaFinalSqlServer() + "'                   " +
"                 AND ( tblDctos.idTipoOrden  =                             " +
                            getIdTipoOrdenINI() +
"                            OR tblDctos.idTipoOrden =                      " +
                            getIdTipoOrdenFIN() +
"                      )                                                    " +
"                 AND tblDctos.indicador BETWEEN                            " +
                            getIndicadorInicial() +
"                                                     AND                   " +
                            getIndicadorFinal() +
"                      )                                                    " +
"                                                                           " +
"                              AS tmpDET                                    " +
" GROUP BY      tmpDET.aliasUsuario,                                        " +
"  		tmpDET.idPlu,                                               " +
"  		tmpDET.nombrePlu,                                           " +
"               tmpDET.nombreMarca,                                         " +
" 		tmpDET.idVendedor,                                          " +
"               tmpDET.indicador ) AS tmpRES                                " +
" ORDER BY      tmpRES.aliasUsuario,                                        " +
" 		tmpRES.nombrePlu  ";

      
        PreparedStatement selectStatement = null;

     

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaColaboraReporteDctoBean fachadaColaboraReporteDctoBean;

         while (rs.next()) {

          fachadaColaboraReporteDctoBean = new FachadaColaboraReporteDctoBean();

         //
         fachadaColaboraReporteDctoBean.setAliasUsuario(
                                                  rs.getString("aliasUsuario"));
         fachadaColaboraReporteDctoBean.setIdPlu(rs.getInt("idPlu"));
         fachadaColaboraReporteDctoBean.setNombrePlu(rs.getString("nombrePlu"));
         fachadaColaboraReporteDctoBean.setNombreMarca(
                                                   rs.getString("nombreMarca"));
         fachadaColaboraReporteDctoBean.setIdVendedor(
                                                    rs.getString("idVendedor"));
         fachadaColaboraReporteDctoBean.setVrCostoIND(
                                                    rs.getDouble("vrCostoIND"));
         fachadaColaboraReporteDctoBean.setMargenIND(rs.getDouble("margenIND"));
         fachadaColaboraReporteDctoBean.setVrCostoMV(rs.getDouble("vrCostoMV"));
         fachadaColaboraReporteDctoBean.setVrPago(rs.getDouble("vrPago"));
         fachadaColaboraReporteDctoBean.setCantidad(rs.getDouble("Cantidad"));
         fachadaColaboraReporteDctoBean.setVrBase(rs.getDouble("vrBase"));
         fachadaColaboraReporteDctoBean.setVrIva(rs.getDouble("vrIva"));

           
         //
         contenedor.add(fachadaColaboraReporteDctoBean);

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
    
         // Metodo listaIndicadorRentabilidadCliente
    public FachadaColaboraReporteDctoBean
                                    listaIndicadorRentabilidadVendPluTotal() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        
        //
        FachadaColaboraReporteDctoBean fachadaColaboraReporteDctoBean
                                        = new FachadaColaboraReporteDctoBean();

        Connection connection = null;

        String selectStr =

" SELECT	tmpExt.aliasUsuario,                                         " +
"  		tmpExt.idVendedor,                                           " +
" 		SUM(tmpExt.vrCostoIND) As vrCostoIND,                        " +
" 		SUM(tmpExt.margenIND)  As margenIND,                         " +
" 		SUM(tmpExt.vrCostoMv)  As vrCostoMv,                         " +
" 		SUM(tmpExt.vrPago)	   As vrPago,                        " +
" 		SUM(tmpExt.vrBase)	   As vrBase,                        " +
" 		SUM(tmpExt.vrIva)	   As vrIva                          " +
"                                                                            " +
" FROM(                                                                      " +
"                                                                            " +
" SELECT  tmpRES.aliasUsuario,                                               " +
"  		tmpRES.idVendedor,                                           " +
" 		tmpRES.vrCostoIND,                                           " +
" 		tmpRES.margenIND,                                            " +
" 		tmpRES.vrCostoMv,                                            " +
" 		tmpRES.vrPago,                                               " +
" 		(CASE WHEN                                                   " +
"                              tmpRES.indicador = 1                          " +
"                         THEN    tmpRES.vrBase                              " +
"                         ELSE    ( tmpRES.vrBase +                          " +
"                                   tmpRES.vrIva )                           " +
"                         END) AS vrBase,                                    " +
"                                                                            " +
"                        (CASE WHEN                                          " +
"                              tmpRES.indicador = 1                          " +
"                         THEN    tmpRES.vrIva                               " +
"                         ELSE    0                                          " +
"                         END) AS vrIva                                      " +
"                                                                            " +
"                                                                            " +
" FROM (                                                                     " +
" SELECT  tmpDET.aliasUsuario,                                               " +
"  		tmpDET.idVendedor,                                           " +
"         tmpDET.indicador,                                                  " +
" 		SUM(tmpDET.Cantidad) AS Cantidad,                            " +
" 		SUM((tmpDET.vrCosto * tmpDET.Cantidad) /                     " +
" 			(1 +( tmpDET.porcentajeIva)/100) )  AS vrCostoMv,    " +
" 		SUM(tmpDET.vrCostoIND * tmpDET.Cantidad )  AS vrCostoIND,    " +
" 		SUM(tmpDET.vrPago)	   AS vrPago,                        " +
" 		SUM(tmpDET.cantidad          *                               " +
"         (tmpDET.vrVentaUnitario   -                                        " +
"         tmpDET.vrImpoconsumo)    *                                         " +
"        ( 1 - ( tmpDET.vrDsctoPie /  100 ) )      *                         " +
"        ( 1 - ( tmpDET.porcentajeDscto /  100 ) ) /                         " +
"        ( 1 + ( tmpDET.porcentajeIva /  100 ) ))     AS vrBase,             " +
" 	   SUM(tmpDET.cantidad          *                                    " +
"        (tmpDET.vrVentaUnitario   -                                         " +
"        tmpDET.vrImpoconsumo)    *                                          " +
"        ( 1 - ( tmpDET.vrDsctoPie /  100 ) )      *                         " +
"        ( 1 - ( tmpDET.porcentajeDscto /  100 ) ) ) -                       " +
"        SUM(tmpDET.cantidad          *                                      " +
"        (tmpDET.vrVentaUnitario   -                                         " +
"        tmpDET.vrImpoconsumo)    *                                          " +
"        ( 1 - ( tmpDET.vrDsctoPie /  100 ) )      *                         " +
"        ( 1 - ( tmpDET.porcentajeDscto /  100 ) ) /                         " +
"        ( 1 + ( tmpDET.porcentajeIva /  100 ) )) AS vrIva,                  " +
" 	   (CASE                                                             " +
" 		WHEN SUM(tmpDET.cantidad          *                          " +
"         (tmpDET.vrVentaUnitario   -                                        " +
"         tmpDET.vrImpoconsumo)    *                                         " +
"        ( 1 - ( tmpDET.vrDsctoPie /  100 ) )      *                         " +
"        ( 1 - ( tmpDET.porcentajeDscto /  100 ) ) /                         " +
"        ( 1 + ( tmpDET.porcentajeIva /  100 ) )) = 0                        " +
" 		THEN 0                                                       " +
" 		ELSE (SUM(tmpDET.cantidad          *                         " +
"         (tmpDET.vrVentaUnitario   -                                        " +
"         tmpDET.vrImpoconsumo)    *                                         " +
"        ( 1 - ( tmpDET.vrDsctoPie /  100 ) )      *                         " +
"        ( 1 - ( tmpDET.porcentajeDscto /  100 ) ) /                         " +
"        ( 1 + ( tmpDET.porcentajeIva /  100 ) )) -                          " +
"         SUM(tmpDET.vrCostoIND * tmpDET.Cantidad )) /                       " +
"         SUM(tmpDET.cantidad          *                                     " +
"         (tmpDET.vrVentaUnitario   -                                        " +
"         tmpDET.vrImpoconsumo)    *                                         " +
"        ( 1 - ( tmpDET.vrDsctoPie /  100 ) )      *                         " +
"        ( 1 - ( tmpDET.porcentajeDscto /  100 ) ) /                         " +
"        ( 1 + ( tmpDET.porcentajeIva /  100 ) ))                            " +
" 		END)                                                         " +
"        	   AS margenIND                                              " +
" FROM (SELECT		   tblDctos.idLocal,                                 " +
"                        tblDctos.idTipoOrden,                               " +
" 					   tblDctos.idOrden,                 " +
" 					   tblDctos.idVendedor,	             " +
"                        tblDctos.idDcto,                                    " +
"                        Max(tblDctos.fechaDcto) As fechaDcto,               " +
"                        tblDctos.indicador,                                 " +
"                        Max(tblDctos.idCliente) As idCliente,               " +
" 		       MAX(CASE WHEN                                         " +
"                              tblDctos.indicador = 1                        " +
"                         THEN    tblDctos.vrBase                            " +
"                         ELSE    ( tblDctos.vrBase +                        " +
"                                   tblDctos.vrIva )                         " +
"                         END) AS vrBase,                                    " +
"                                                                            " +
"                     MAX(CASE WHEN                                          " +
"                              tblDctos.indicador = 1                        " +
"                         THEN    tblDctos.vrIva                             " +
"                         ELSE    0                                          " +
"                         END) AS vrIva,                                     " +
"                                                                            " +
"               MAX(tblDctos.nombreTercero) As nombreTercero,                " +
"               MAX(tblDctos.idDctoNitCC) As idDctoNitCC,                    " +
"               MAX(tblDctos.fechaDctoNitCC) As fechaDctoNitCC,              " +
"               MAX(tblDctos.idLocalCruce) As idLocalCruce,                  " +
"               MAX(tblDctos.vrCostoMV) As vrCostoMV,                        " +
" 		MAX(tblDctos.vrPago) As vrPago,	                             " +
" 		MAX(tblDctosOrdenesDetalle.nombrePlu) As nombrePlu,          " +
" 		MAX(tblPlus.idPlu) As idPlu,                                 " +
" 		MAX(tblMarcas.nombreMarca) As nombreMarca,                   " +
" 		MAX(tblDctosOrdenesDetalle.Cantidad) As Cantidad,            " +
" 		MAX(tblDctosOrdenesDetalle.vrVentaUnitario)                  " +
"                                                  As vrVentaUnitario,       " +
" 		MAX(tblDctosOrdenesDetalle.vrVentaOriginal)                  " +
"                                                 As vrVentaOriginal,        " +
" 		MAX(tblDctosOrdenesDetalle.vrDsctoPie) As vrDsctoPie,        " +
" 		MAX(tblDctosOrdenesDetalle.porcentajeDscto)                  " +
"                                                      As porcentajeDscto,   " +
" 		MAX(tblDctosOrdenesDetalle.porcentajeIva) As porcentajeIva,  " +
" 		MAX(tblDctosOrdenesDetalle.vrImpoconsumo) As vrImpoconsumo,  " +
" 		MAX(tblDctosOrdenesDetalle.vrCosto) As vrCosto,              " +
" 		MAX(tblDctosOrdenesDetalle.vrCostoIND) As vrCostoIND,        " +
"                                                                            " +
"                        ( SELECT ctrlUsuarios.aliasUsuario                  " +
"                          FROM ctrlUsuarios                                 " +
"                          WHERE ctrlUsuarios.idUsuario =                    " +
"                                      tblDctos.idVendedor )                 " +
"                                            AS aliasUsuario                 " +
"                 FROM   tblDctos                                            " +
" 		INNER JOIN tblDctosOrdenesDetalle                            " +
" 		ON tblDctosOrdenesDetalle.IDLOCAL                            " +
" 				= tblDctos.IDLOCAL                           " +
" 		AND tblDctosOrdenesDetalle.IDORDEN                           " +
" 				= tblDctos.IDORDEN                           " +
" 		AND tblDctosOrdenesDetalle.IDTIPOORDEN                       " +
" 				= tblDctos.IDTIPOORDEN                       " +
" 		INNER JOIN tblPlus                                           " +
" 		ON tblPlus.idPlu                                             " +
" 				= tblDctosOrdenesDetalle.IDPLU               " +
" 		INNER JOIN tblMarcas                                         " +
" 		ON tblMarcas.idMarca                                         " +
" 				= tblPlus.idMarca                            " +
"                 WHERE EXISTS                                               " +
"                    ( SELECT *                                              " +
"                      FROM   tblDctosOrdenes                                " +
"                      INNER JOIN tblDctosOrdenesDetalle                     " +
"                      ON tblDctosOrdenes.IDLOCAL      =                     " +
"                         tblDctosOrdenesDetalle.IDLOCAL                     " +
"                      AND tblDctosOrdenes.IDTIPOORDEN =                     " +
"                         tblDctosOrdenesDetalle.IDTIPOORDEN                 " +
"                      AND tblDctosOrdenes.IDORDEN     =                     " +
"                         tblDctosOrdenesDetalle.IDORDEN                     " +
"                      WHERE tblDctosOrdenes.IDLOCAL   =                     " +
"                                           tblDctos.IDLOCAL                 " +
"                      AND tblDctosOrdenes.IDTIPOORDEN =                     " +
"                                       tblDctos.IDTIPOORDEN                 " +
"                      AND tblDctosOrdenes.IDORDEN     =                     " +
"                                          tblDctos.IDORDEN)                 " +
"                         AND tblDctos.idLocal   =                           " +
                 getIdLocal() +                                             
"             AND tblDctos.fechaDcto BETWEEN                                '" +
                 getFechaInicialSqlServer() + "'                             " +
"             AND                                                           '" +
                 getFechaFinalSqlServer() + "'                               " +
"             AND ( tblDctos.idTipoOrden  =                                  " +
                 getIdTipoOrdenINI() +
"             OR tblDctos.idTipoOrden =                                      " +
                 getIdTipoOrdenFIN() +
"                 )                                                          " +
"             AND tblDctos.indicador BETWEEN                                 " +
                 getIndicadorInicial() +
"             AND                                                            " +
                 getIndicadorFinal() +
" GROUP BY	tblDctos.idLocal,                                            " +
"             tblDctos.idTipoOrden,                                          " +
" 			tblDctos.idOrden,                                    " +
"             tblDctos.idDcto,                                               " +
" 			tblDctos.indicador,                                  " +
" 			tblDctos.idVendedor                                  " +
" 	                                                                     " +
" )                                                                          " +
"                                                                            " +
" 			AS tmpDET                                            " +
"                                                                            " +
" GROUP BY    tmpDET.aliasUsuario,                                           " +
" 			tmpDET.idVendedor,                                   " +
" 			tmpDET.indicador                                     " +
"          ) AS tmpRES                                                       " +
"                                                                            " +
" )	As tmpExt                                                            " +
" GROUP BY	tmpExt.aliasUsuario,                                         " +
"  		tmpExt.idVendedor ";





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
         fachadaColaboraReporteDctoBean.setAliasUsuario(
                                                  rs.getString("aliasUsuario"));
         fachadaColaboraReporteDctoBean.setIdVendedor(
                                                    rs.getString("idVendedor"));
         fachadaColaboraReporteDctoBean.setVrCostoIND(
                                                    rs.getDouble("vrCostoIND"));
         fachadaColaboraReporteDctoBean.setMargenIND(rs.getDouble("margenIND"));
         fachadaColaboraReporteDctoBean.setVrCostoMV(rs.getDouble("vrCostoMV"));
         fachadaColaboraReporteDctoBean.setVrPago(rs.getDouble("vrPago"));
         fachadaColaboraReporteDctoBean.setVrBase(rs.getDouble("vrBase"));
         fachadaColaboraReporteDctoBean.setVrIva(rs.getDouble("vrIva"));

           
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
            

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {

            // Cierra la conexion
            return fachadaColaboraReporteDctoBean;

        }
    }

          // Metodo listaIndicadorRentabilidadCliente
    public Vector listaIndicadorRentabilidadVendCliente() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        String selectStr =

" SELECT  tmpDET.aliasUsuario,                                             " +
"         tmpDET.idCliente AS idCliente,                                   " +
"         tmpDET.idVendedor,                                               " +
" 	tmpDET.nombreCiudad AS nombreCiudad,                               " +
"         MAX(tmpDET.nombreTercero) AS nombreTercero,                      " +
" 		SUM(tmpDET.vrCostoMV)  AS vrCostoMv,                       " +
" 		SUM(tmpDET.vrIva)	   AS vrIva,                       " +
" 		SUM(tmpDET.vrPago)	   AS vrPago,                      " +
" 		SUM(tmpDET.vrBase)     AS vrBase,                          " +
" 		SUM(tmpDET.vrCostoIND) AS vrCostoIND,                      " +
"        (CASE                                                             " +
" 		WHEN SUM(tmpDET.vrBase) = 0                                " +
" 		THEN 0                                                     " +
" 		ELSE (SUM(tmpDET.vrBase) -                                 " +
"         SUM(tmpDET.vrCostoIND)) /                                        " +
"         SUM(tmpDET.vrBase)                                               " +
" 		END)                                                       " +
"        	   AS margenIND                                            " +
" FROM (	SELECT tblDctos.idLocal,                                   " +
"                        tblDctos.idTipoOrden,                             " +
" 		       tblDctos.idOrden,                                   " +
"                        tblDctos.idDcto,                                  " +
"                        tblDctos.fechaDcto,                               " +
"                        tblDctos.indicador,                               " +
"                        tblDctos.idCliente,                               " +
" 		       (CASE WHEN                                          " +
"                              tblDctos.indicador = 1                      " +
"                         THEN    tblDctos.vrBase                          " +
"                         ELSE    ( tblDctos.vrBase +                      " +
"                                   tblDctos.vrIva )                       " +
"                         END) AS vrBase,                                  " +
"                                                                          " +
"                        (CASE WHEN                                        " +
"                              tblDctos.indicador = 1                      " +
"                         THEN    tblDctos.vrIva                           " +
"                         ELSE    0                                        " +
"                         END) AS vrIva,                                   " +
"                        tblDctos.vrImpoconsumo,                           " +
"                        tblDctos.idDctoNitCC,                             " +
"                        tblDctos.fechaDctoNitCC,                          " +
"                        tblDctos.idLocalCruce,                            " +
"                        tblDctos.idTipoOrdenCruce,                        " +
" 		       tblDctos.idOrdenCruce,                              " +
"                        tblDctos.idDctoCruce,                             " +
"                        tblDctos.vrCostoMV,                               " +
"                        tblDctos.vrCostoIND,                              " +
"                        tblDctos.idVendedor,                              " +
"                        tblDctos.vrPago,                                  " +
" 		       tblTerceros.nombreTercero,                          " +
" 			tblCiudades.nombreCiudad,                          " +
"                        ( SELECT ctrlUsuarios.aliasUsuario                " +
"                          FROM ctrlUsuarios                               " +
"                          WHERE ctrlUsuarios.idUsuario =                  " +
"                                      tblDctos.idVendedor )               " +
"                                            AS aliasUsuario               " +
"                                                                          " +
"                FROM   tblDctos                                           " +
"         	INNER JOIN tblTerceros ON tblTerceros.idCliente            " +
" 		         		   = tblDctos.idCliente            " +
" 		INNER JOIN tblCiudades ON tblCiudades.idCiudad             " +
" 	    				= tblTerceros.idDptoCiudad         " +
"                 WHERE EXISTS                                             " +
"                    ( SELECT *                                            " +
"                      FROM   tblDctosOrdenes                              " +
"                      INNER JOIN tblDctosOrdenesDetalle                   " +
"                      ON tblDctosOrdenes.IDLOCAL      =                   " +
"                         tblDctosOrdenesDetalle.IDLOCAL                   " +
"                      AND tblDctosOrdenes.IDTIPOORDEN =                   " +
"                         tblDctosOrdenesDetalle.IDTIPOORDEN               " +
"                      AND tblDctosOrdenes.IDORDEN     =                   " +
"                         tblDctosOrdenesDetalle.IDORDEN                   " +
"                      WHERE tblDctosOrdenes.IDLOCAL   =                   " +
"                                           tblDctos.IDLOCAL               " +
"                      AND tblDctosOrdenes.IDTIPOORDEN =                   " +
"                                       tblDctos.IDTIPOORDEN               " +
"                      AND tblDctosOrdenes.IDORDEN     =                   " +
"                                          tblDctos.IDORDEN)               " +
"                 AND tblDctos.idLocal   =                                 " +
                         getIdLocal() +
"                 AND tblDctos.fechaDcto BETWEEN                           '" +
                        getFechaInicialSqlServer() + "'                     " +
"                 AND                                                      '" +
                        getFechaFinalSqlServer() + "'                       " +
"                 AND ( tblDctos.idTipoOrden  = " +
                        getIdTipoOrdenINI() +
"                 OR tblDctos.idTipoOrden =                                 " +
                        getIdTipoOrdenFIN() +
"                     )                                                     " +
"                 AND tblDctos.indicador BETWEEN " +
                        getIndicadorInicial() +
"                 AND                                                       " +
                        getIndicadorFinal() +
                ")  " +
"                       AS tmpDET                                           " +
" GROUP BY tmpDET.aliasUsuario,                                             " +
"          tmpDET.idCliente,                                                " +
"          tmpDET.idVendedor,                                               " +
" 	 tmpDET.nombreCiudad                                                " +
" ORDER BY  1 , 3  ";


        PreparedStatement selectStatement = null;



        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaColaboraReporteDctoBean fachadaColaboraReporteDctoBean;

         while (rs.next()) {

          fachadaColaboraReporteDctoBean = new FachadaColaboraReporteDctoBean();

         //
         fachadaColaboraReporteDctoBean.setAliasUsuario(
                                                  rs.getString("aliasUsuario"));
         fachadaColaboraReporteDctoBean.setIdVendedor(
                                                    rs.getString("idVendedor"));
         fachadaColaboraReporteDctoBean.setVrCostoIND(
                                                    rs.getDouble("vrCostoIND"));
         fachadaColaboraReporteDctoBean.setMargenIND(rs.getDouble("margenIND"));
         fachadaColaboraReporteDctoBean.setVrCostoMV(rs.getDouble("vrCostoMv"));
         fachadaColaboraReporteDctoBean.setVrPago(rs.getDouble("vrPago"));
         fachadaColaboraReporteDctoBean.setVrBase(rs.getDouble("vrBase"));
         fachadaColaboraReporteDctoBean.setVrIva(rs.getDouble("vrIva"));
         fachadaColaboraReporteDctoBean.setIdCliente(rs.getString("idCliente"));
         fachadaColaboraReporteDctoBean.setNombreCiudad(
                                                 rs.getString("nombreCiudad"));
         fachadaColaboraReporteDctoBean.setNombreTercero(
                                                 rs.getString("nombreTercero"));
         
         //
         contenedor.add(fachadaColaboraReporteDctoBean);

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

           // Metodo listaIndicadorRentabilidadMarca
    public Vector listaIndicadorRentabilidadMarca() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        String selectStr =

" SELECT			tblPlus.porcentajeIva,                       " +
"                         tblMarcas.nombreMarca,                             " +
" 			tblMarcas.idMarca,                                   " +
"                         SUM(tmpVTA.vrVentaSinIva)                          " +
" 				AS vrVentaSinIva,                            " +
"                         SUM(tmpVTA.vrIva)                                  " +
" 				AS vrVentaIva,                               " +
"                         SUM(tmpVTA.vrCostoIND)                             " +
" 				AS vrCostoIND,(CASE                          " +
" 				WHEN SUM(tmpVTA.vrVentaSinIva) = 0           " +
" 				THEN 0                                       " +
" 				ELSE                                         " +
" 				(SUM(tmpVTA.vrVentaSinIva) -                 " +
"                                   SUM(tmpVTA.vrCostoIND))/                 " +
" 			     SUM(tmpVTA.vrVentaSinIva)                       " +
" 				END)                                         " +
" 			    AS margenMarca                                   " +
" 				FROM    tblCategorias                        " +
"                 INNER JOIN tblPlus                                         " +
"                 ON tblCategorias.idLinea      =                            " +
"                                       tblPlus.idLinea                      " +
"                 AND tblCategorias.IdCategoria =                            " +
"                                   tblPlus.idCategoria                      " +
"                 INNER JOIN tblMarcas                                       " +
"                 ON tblPlus.idMarca            =                            " +
"                                      tblMarcas.idMarca                     " +
"                 INNER JOIN (SELECT tmpMVT.idPlu,                           " +
"                        SUM((CASE WHEN                                      " +
"                                    tmpMVT.indicador = 1                    " +
"                             THEN    tmpMVT.vrVentaSinIva                   " +
"                             ELSE    ( tmpMVT.vrVentaSinIva +               " +
"                                       tmpMVT.vrIva )                       " +
"                             END)) AS vrVentaSinIva,                        " +
"                         SUM((CASE WHEN                                     " +
"                                     tmpMVT.indicador = 1                   " +
"                             THEN    tmpMVT.vrIva                           " +
"                             ELSE    0                                      " +
"                             END)) AS vrIva,                                " +
"                        SUM(tmpMVT.vrCostoIND)    AS vrCostoIND,            " +
"                        SUM(tmpMVT.cantidad)    AS cantidad                 " +
"                  FROM                                                      " +
"                 (SELECT tblDctos.indicador,                                " +
"                        tblPlusCombo.IDPLU                                  " +
"                        ,SUM(tblDctosOrdenesDetalle.CANTIDAD *              " +
"                             tblPlusCombo.cantidad) AS cantidad             " +
"                        ,SUM(tblDctosOrdenesDetalle.CANTIDAD *              " +
"                             tblPlusCombo.cantidad         *                " +
"                            (tblDctosOrdenesDetalle.vrVentaUnitario   -     " +
"                             tblDctosOrdenesDetalle.vrImpoconsumo)    /     " +
"                   ((1 + (tblDctosOrdenesDetalle.porcentajeIva /100)) *     " +
"                  (1 + (tblDctosOrdenesDetalle.vrDsctoPie /100 ) ) *        " +
"                  (1 + (tblDctosOrdenesDetalle.porcentajeDscto /100 ))))    " +
"                                                       AS vrVentaSinIva     " +
"                        ,SUM( (tblDctosOrdenesDetalle.CANTIDAD *            " +
"                             tblPlusCombo.cantidad           *              " +
"                            (tblDctosOrdenesDetalle.vrVentaUnitario -       " +
"                             tblDctosOrdenesDetalle.vrImpoconsumo)) *       " +
"                     (1 - tblDctosOrdenesDetalle.porcentajeDscto / 100) *   " +
"                         (1 - tblDctosOrdenesDetalle.vrDsctoPie / 100))     " +
"                                                      AS vrVentaConIva      " +
"                        ,SUM( (tblDctosOrdenesDetalle.CANTIDAD *            " +
"                             tblPlusCombo.cantidad           *              " +
"                            (tblDctosOrdenesDetalle.vrVentaUnitario -       " +
"                             tblDctosOrdenesDetalle.vrImpoconsumo)) *       " +
"                    (1 - tblDctosOrdenesDetalle.porcentajeDscto / 100) *    " +
"                    (1 - tblDctosOrdenesDetalle.vrDsctoPie / 100))     -    " +
"                        SUM(tblDctosOrdenesDetalle.CANTIDAD *               " +
"                             tblPlusCombo.cantidad         *                " +
"                            (tblDctosOrdenesDetalle.vrVentaUnitario   -     " +
"                             tblDctosOrdenesDetalle.vrImpoconsumo)    /     " +
"                     ((1 + (tblDctosOrdenesDetalle.porcentajeIva /100)) *   " +
"                     (1 + (tblDctosOrdenesDetalle.vrDsctoPie /100 ) ) *     " +
"                  (1 + (tblDctosOrdenesDetalle.porcentajeDscto /100 ))))    " +
"                                                                AS vrIva    " +
"                        ,SUM( (tblDctosOrdenesDetalle.CANTIDAD *            " +
"                             tblPlusCombo.cantidad             *            " +
"                               tblDctosOrdenesDetalle.vrCosto )      /      " +
"                   (1 + tblDctosOrdenesDetalle.porcentajeIva / 100))        " +
"                                                    AS vrCostoSinIva        " +
"                        ,SUM(  tblDctosOrdenesDetalle.CANTIDAD *            " +
"                             tblPlusCombo.cantidad            *             " +
"                                      tblDctosOrdenesDetalle.vrCosto)       " +
"                                                      AS vrCostoConIva      " +
"                        ,SUM(  tblDctosOrdenesDetalle.CANTIDAD *            " +
"                             tblPlusCombo.cantidad            *             " +
"                                   tblDctosOrdenesDetalle.vrCostoIND)       " +
"                                                          AS vrCostoIND     " +
"                 FROM   tblDctos                                            " +
"                 INNER JOIN tblDctosOrdenesDetalle                          " +
"                 ON  tblDctos.IDLOCAL     =                                 " +
"                        tblDctosOrdenesDetalle.IDLOCAL                      " +
"                 AND tblDctos.IDTIPOORDEN =                                 " +
"                    tblDctosOrdenesDetalle.IDTIPOORDEN                      " +
"                 AND tblDctos.IDORDEN     =                                 " +
"                        tblDctosOrdenesDetalle.IDORDEN                      " +
"                 INNER JOIN tblPlusCombo                                    " +
"                 ON tblDctosOrdenesDetalle.IDPLU                    =       " +
"                               tblPlusCombo.idPluCombo                      " +
"                    WHERE (tblDctos.IDLOCAL   =                             " +
                             getIdLocal() +
"                     AND ( tblDctos.idTipoOrden  =                          " +
                             getIdTipoOrdenINI() +
"                            OR tblDctos.idTipoOrden =                       " +
                             getIdTipoOrdenFIN() +
"                        )                                                   " +
"                    AND tblDctos.fechaDcto BETWEEN                         '" +
                             getFechaInicialSqlServer() + "'                 " +
"                    AND                                                    '" +
                             getFechaFinalSqlServer() + "'                   " +
"                    AND tblDctos.indicador BETWEEN                          " +
                             getIndicadorInicial() +
"                    AND                                                     " +
                             getIndicadorFinal() +
"                       )                                                    " +
"                 GROUP BY tblDctos.indicador,                               " +
" 						 tblPlusCombo.IDPLU          " +
"                 UNION                                                      " +
"                 SELECT tblDctos.indicador,                                 " +
"                        tblDctosOrdenesDetalle.IDPLU                        " +
"                        ,SUM(tblDctosOrdenesDetalle.CANTIDAD)               " +
"                                                  AS cantidad               " +
"                        ,SUM(tblDctosOrdenesDetalle.cantidad          *     " +
"                            (tblDctosOrdenesDetalle.vrVentaUnitario   -     " +
"                             tblDctosOrdenesDetalle.vrImpoconsumo)    /     " +
"                     ((1 + (tblDctosOrdenesDetalle.porcentajeIva /100)) *   " +
"                     (1 + (tblDctosOrdenesDetalle.vrDsctoPie /100 ) ) *     " +
"                   (1 + (tblDctosOrdenesDetalle.porcentajeDscto /100 ))))   " +
"                                                       AS vrVentaSinIva     " +
"                        ,SUM( (tblDctosOrdenesDetalle.CANTIDAD       *      " +
"                            (tblDctosOrdenesDetalle.vrVentaUnitario -       " +
"                             tblDctosOrdenesDetalle.vrImpoconsumo)) *       " +
"                    (1 - tblDctosOrdenesDetalle.porcentajeDscto / 100) *    " +
"                           (1 - tblDctosOrdenesDetalle.vrDsctoPie / 100))   " +
"                                                      AS vrVentaConIva      " +
"                        ,SUM( (tblDctosOrdenesDetalle.CANTIDAD      *       " +
"                            (tblDctosOrdenesDetalle.vrVentaUnitario -       " +
"                             tblDctosOrdenesDetalle.vrImpoconsumo)) *       " +
"                   (1 - tblDctosOrdenesDetalle.porcentajeDscto / 100) *     " +
"                   (1 - tblDctosOrdenesDetalle.vrDsctoPie / 100))     -     " +
"                         SUM(tblDctosOrdenesDetalle.cantidad         *      " +
"                            (tblDctosOrdenesDetalle.vrVentaUnitario   -     " +
"                             tblDctosOrdenesDetalle.vrImpoconsumo)    /     " +
"                   ((1 + (tblDctosOrdenesDetalle.porcentajeIva /100)) *     " +
"                       (1 + (tblDctosOrdenesDetalle.vrDsctoPie /100 ) ) *   " +
"                  (1 + (tblDctosOrdenesDetalle.porcentajeDscto /100 ))))    " +
"                                                                AS vrIva    " +
"                        ,SUM( (tblDctosOrdenesDetalle.CANTIDAD       *      " +
"                               tblDctosOrdenesDetalle.vrCosto )      /      " +
"                       (1 + tblDctosOrdenesDetalle.porcentajeIva / 100))    " +
"                                                      AS vrCostoSinIva      " +
"                        ,SUM(  tblDctosOrdenesDetalle.CANTIDAD    *         " +
"                                      tblDctosOrdenesDetalle.vrCosto)       " +
"                                                      AS vrCostoConIva      " +
"                        ,SUM(  tblDctosOrdenesDetalle.CANTIDAD    *         " +
"                                   tblDctosOrdenesDetalle.vrCostoIND)       " +
"                                                        AS vrCostoIND       " +
"                 FROM   tblDctos                                            " +
"                 INNER JOIN tblDctosOrdenesDetalle                          " +
"                 ON  tblDctos.IDLOCAL     =                                 " +
"                     tblDctosOrdenesDetalle.IDLOCAL                         " +
"                 AND tblDctos.IDTIPOORDEN =                                 " +
"                     tblDctosOrdenesDetalle.IDTIPOORDEN                     " +
"                 AND tblDctos.IDORDEN     =                                 " +
"                     tblDctosOrdenesDetalle.IDORDEN                         " +
"                    WHERE (tblDctos.IDLOCAL   =                             " +
                             getIdLocal() +
"                     AND ( tblDctos.idTipoOrden  =                          " +
                             getIdTipoOrdenINI() +
"                            OR tblDctos.idTipoOrden =                       " +
                             getIdTipoOrdenFIN() +
"                        )                                                   " +
"                    AND tblDctos.fechaDcto BETWEEN                         '" +
                             getFechaInicialSqlServer() + "'                 " +
"                    AND                                                    '" +
                             getFechaFinalSqlServer() + "'                   " +
"                    AND tblDctos.indicador BETWEEN                          " +
                             getIndicadorInicial() +
"                    AND                                                     " +
                             getIndicadorFinal() +
"                 AND  tblDctos.IDLOCAL     =                                " +
"                                           tblDctosOrdenesDetalle.IDLOCAL   " +
"                 AND  tblDctos.IDTIPOORDEN =                                " +
"                                      tblDctosOrdenesDetalle.IDTIPOORDEN    " +
"                 AND  tblDctos.IDORDEN     =                                " +
"                                          tblDctosOrdenesDetalle.IDORDEN    " +
"                 AND tblDctosOrdenesDetalle.idTipo            = 1 )         " +
"                 GROUP BY tblDctos.indicador,                               " +
"                          tblDctosOrdenesDetalle.IDPLU) AS tmpMVT           " +
"                 GROUP BY tmpMVT.idPlu) AS tmpVTA                           " +
"                 ON tmpVTA.idPlu       = tblPlus.idPlu                      " +
" GROUP BY	tblPlus.porcentajeIva,                                       " +
"                 tblMarcas.nombreMarca,                                     " +
" 		tblMarcas.idMarca                                            " +
" ORDER BY	7 DESC ";


        PreparedStatement selectStatement = null;



        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaColaboraReporteDctoBean fachadaColaboraReporteDctoBean;

         while (rs.next()) {

          fachadaColaboraReporteDctoBean = new FachadaColaboraReporteDctoBean();

         //

          fachadaColaboraReporteDctoBean.setPorcentajeIva(
                                                rs.getDouble("porcentajeIva"));
          fachadaColaboraReporteDctoBean.setNombreMarca(
                                                   rs.getString("nombreMarca"));
          fachadaColaboraReporteDctoBean.setIdMarca(rs.getInt("idMarca"));
          fachadaColaboraReporteDctoBean.setVrVentaSinIva(
                                                rs.getDouble("vrVentaSinIva"));
          fachadaColaboraReporteDctoBean.setVrVentaIva(
                                                    rs.getDouble("vrVentaIva"));
          fachadaColaboraReporteDctoBean.setVrCostoIND(
                                                    rs.getDouble("vrCostoIND"));
          fachadaColaboraReporteDctoBean.setMargenIND(
                                                   rs.getDouble("margenMarca"));
         //
         contenedor.add(fachadaColaboraReporteDctoBean);

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


}
