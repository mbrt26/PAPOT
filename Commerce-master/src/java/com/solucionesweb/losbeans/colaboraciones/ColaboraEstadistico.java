package com.solucionesweb.losbeans.colaboraciones;

// Importa la clase helper JDBCAccess que contiene los accesos a la base de datos
import com.solucionesweb.lasayudas.JDBCAccess;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaPluBean;

// Importa los paquetes de java
import java.sql.*;
import java.io.*;
import java.util.*;
import java.io.IOException.*;

// Importa los paquetes de javax
import javax.naming.*;

public class ColaboraEstadistico extends FachadaPluBean
        implements Serializable {

    // Nombre JNDI del Data Source que esta clase requiere
    private static final String DATA_SOURCE_NAME = "CommerceAccess";
    // Clase helper usada para acceder a la base de datos
    private JDBCAccess jdbcAccess;

    // Metodo constructor por defecto sin parametros
    // Metodo constructor por defecto sin parametros
    public ColaboraEstadistico() {

        // Al instanciar el Bean establece la conexion a la base de datos
        jdbcAccess = new JDBCAccess(DATA_SOURCE_NAME);

    }

    // listaVenta
    public Vector listaVenta(String xIdTipoOrdenCadena,
            String xFechaInicial,
            String xFechaFinal,
            int xIndicadorInicial,
            int xIndicadorFinal) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        //
        String selectStr =
                "SELECT  tblPlus.idPlu,                                     "
                + "        tblPlus.nombrePlu, 				    "
                + "        tblPlus.porcentajeIva, 			    "
                + "        tblPlus.idLinea, 				    "
                + "        tblPlus.idCategoria, 		            "
                + "        tblPlus.idMarca, 				    "
                + "        tblCategorias.nombreCategoria, 		    "
                + "        tblMarcas.nombreMarca, 			    "
                + "        tblLineas.nombreLinea,			    "
                + "        tmpMVT.cantidad,			            "
                + "        tmpMVT.vrVentaSinIva,			    "
                + "        tmpMVT.vrVentaConIva,			    "
                + "        tmpMVT.vrCostoSinIva,			    "
                + "        tmpMVT.vrCostoConIva,			    "
                + "        tmpMVT.vrCostoIND	  			    "
                + "FROM    tblLineas					    "
                + "INNER JOIN tblMarcas					    "
                + "INNER JOIN tblPlus 					    "
                + "ON tblMarcas.idMarca    = 				    "
                + "                     tblPlus.idMarca		            "
                + "INNER JOIN tblCategorias				    "
                + "ON tblPlus.idLinea      = 				    "
                + "               tblCategorias.idLinea			    "
                + "AND tblPlus.idCategoria = 				    "
                + "tblCategorias.IdCategoria 				    "
                + "ON tblLineas.idLinea = 				    "
                + "               tblCategorias.idLinea			    "
                + "INNER JOIN (						    "
                + "SELECT tmpDET.idPlu,					    "
                + "       SUM(tmpDET.cantidad) AS cantidad,		    "
                + "       SUM(tmpDET.vrVentaSinIva) AS vrVentaSinIva,	    "
                + "       SUM(tmpDET.vrVentaConIva) AS vrVentaConIva,       "
                + "       SUM(tmpDET.vrCostoSinIva) AS vrCostoSinIva,       "
                + "       SUM(tmpDET.vrCostoConIva) AS vrCostoConIva,       "
                + "       SUM(tmpDET.vrCostoIND)    AS vrCostoIND           "
                + "FROM (						    "
                + "SELECT tblDctosOrdenesDetalle.IDPLU			    "
                + "    ,SUM(tblDctosOrdenesDetalle.CANTIDAD)		    "
                + "                              AS cantidad		    "
                + "    ,SUM(tblDctosOrdenesDetalle.cantidad         *	    "
                + "    (tblDctosOrdenesDetalle.vrVentaUnitario   -	    "
                + "     tblDctosOrdenesDetalle.vrImpoconsumo)    /	    "
                + "    ((1 + (tblDctosOrdenesDetalle.porcentajeIva /100)) * "
                + "    (1 + (tblDctosOrdenesDetalle.vrDsctoPie /100 ) ) *   "
                + "  (1 + (tblDctosOrdenesDetalle.porcentajeDscto /100 )))) "
                + "                                       AS vrVentaSinIva  "
                + "    ,SUM( (tblDctosOrdenesDetalle.CANTIDAD           *   "
                + "           (tblDctosOrdenesDetalle.vrVentaUnitario -     "
                + "             tblDctosOrdenesDetalle.vrImpoconsumo)) *    "
                + "    (1 - tblDctosOrdenesDetalle.porcentajeDscto / 100) * "
                + "     (1 - tblDctosOrdenesDetalle.vrDsctoPie / 100))      "
                + "                                       AS vrVentaConIva  "
                + "    ,SUM( (tblDctosOrdenesDetalle.CANTIDAD             * "
                + "          tblDctosOrdenesDetalle.vrCosto )      /        "
                + "    (1 + tblDctosOrdenesDetalle.porcentajeIva / 100))    "
                + "                                      AS vrCostoSinIva   "
                + "    ,SUM(  tblDctosOrdenesDetalle.CANTIDAD            *  "
                + "            tblDctosOrdenesDetalle.vrCosto)              "
                + "                                       AS vrCostoConIva  "
                + "    ,SUM(  tblDctosOrdenesDetalle.CANTIDAD            *  "
                + "            tblDctosOrdenesDetalle.vrCostoIND)           "
                + "                                       AS vrCostoIND     "
                + "FROM tblDctosOrdenesDetalle				    "
                + "WHERE EXISTS 					    "
                + " ( SELECT tblDctos.*					    "
                + "   FROM   tblDctos					    "
                + "   INNER JOIN tblDctosOrdenes			    "
                + "   ON  tblDctos.IDLOCAL     = 			    "
                + "                    tblDctosOrdenes.IDLOCAL		    "
                + "   AND tblDctos.IDTIPOORDEN = 			    "
                + "                tblDctosOrdenes.IDTIPOORDEN		    "
                + "   AND tblDctos.IDORDEN     = 			    "
                + "                    tblDctosOrdenes.IDORDEN		    "
                + "   WHERE (tblDctos.IDLOCAL   =                           "
                + getIdLocal() + " )                                        "
                + "   AND (tblDctos.IDTIPOORDEN IN                         ("
                + xIdTipoOrdenCadena + " ))		                    "
                + "   AND tblDctos.fechaDcto BETWEEN                       '"
                + xFechaInicial + "' AND                                   '"
                + xFechaFinal + "'                                          "
                + "   AND tblDctos.indicador BETWEEN                        "
                + xIndicadorInicial + " AND                                 "
                + xIndicadorFinal + "                                       "
                + "   AND  tblDctos.IDLOCAL     = 			    "
                + "             tblDctosOrdenesDetalle.IDLOCAL		    "
                + "   AND  tblDctos.IDTIPOORDEN = 			    "
                + "         tblDctosOrdenesDetalle.IDTIPOORDEN		    "
                + "   AND  tblDctos.IDORDEN     = 			    "
                + "             tblDctosOrdenesDetalle.IDORDEN)  	    "
                + "AND tblDctosOrdenesDetalle.idTipo            = 1         "
                + "GROUP BY tblDctosOrdenesDetalle.IDPLU		    "
                + "UNION                                                    "
                + "SELECT tblPlusCombo.idPlu				    "
                + "    ,SUM(tblDctosOrdenesDetalle.CANTIDAD      *          "
                + "         tblPlusCombo.cantidad) AS cantidad		    "
                + "    ,SUM(tblDctosOrdenesDetalle.cantidad      *	    "
                + "    (tblDctosOrdenesDetalle.vrVentaUnitario   -	    "
                + "     tblDctosOrdenesDetalle.vrImpoconsumo)    /	    "
                + "    ((1 + (tblDctosOrdenesDetalle.porcentajeIva /100)) * "
                + "    (1 + (tblDctosOrdenesDetalle.vrDsctoPie /100 ) ) *   "
                + "  (1 + (tblDctosOrdenesDetalle.porcentajeDscto /100 )))) "
                + "                                       AS vrVentaSinIva  "
                + "    ,SUM( (tblDctosOrdenesDetalle.CANTIDAD           *   "
                + "           (tblDctosOrdenesDetalle.vrVentaUnitario -     "
                + "             tblDctosOrdenesDetalle.vrImpoconsumo)) *    "
                + "    (1 - tblDctosOrdenesDetalle.porcentajeDscto / 100) * "
                + "     (1 - tblDctosOrdenesDetalle.vrDsctoPie / 100))      "
                + "                                       AS vrVentaConIva  "
                + "    ,SUM( (tblDctosOrdenesDetalle.CANTIDAD             * "
                + "          tblDctosOrdenesDetalle.vrCosto )      /        "
                + "    (1 + tblDctosOrdenesDetalle.porcentajeIva / 100))    "
                + "                                      AS vrCostoSinIva   "
                + "    ,SUM(  tblDctosOrdenesDetalle.CANTIDAD            *  "
                + "            tblDctosOrdenesDetalle.vrCosto            )  "
                + "                                       AS vrCostoConIva  "
                + "    ,SUM(  tblDctosOrdenesDetalle.CANTIDAD            *  "
                + "            tblDctosOrdenesDetalle.vrCostoIND)           "
                + "                                       AS vrCostoIND     "
                + "FROM tblDctosOrdenesDetalle,				    "
                + "                tblPlusCombo				    "
                + "WHERE EXISTS 					    "
                + "  ( SELECT tblDctos.*				    "
                + "    FROM   tblDctos					    "
                + "    INNER JOIN tblDctosOrdenes			    "
                + "    ON  tblDctos.IDLOCAL     = 			    "
                + "                    tblDctosOrdenes.IDLOCAL		    "
                + "    AND tblDctos.IDTIPOORDEN = 			    "
                + "                tblDctosOrdenes.IDTIPOORDEN		    "
                + "    AND tblDctos.IDORDEN     = 			    "
                + "                    tblDctosOrdenes.IDORDEN	    	    "
                + "    WHERE (tblDctos.IDLOCAL   =     		            "
                + getIdLocal() + " )                                        "
                + "    AND (tblDctos.IDTIPOORDEN IN                        ("
                + xIdTipoOrdenCadena + " ))		                    "
                + "   AND tblDctos.fechaDcto BETWEEN                       '"
                + xFechaInicial + "' AND                                   '"
                + xFechaFinal + "'                                          "
                + "   AND tblDctos.indicador BETWEEN                        "
                + xIndicadorInicial + " AND                                 "
                + xIndicadorFinal + "                                       "
                + "    AND  tblDctos.IDLOCAL     =                          "
                + "             tblDctosOrdenesDetalle.IDLOCAL		    "
                + "    AND  tblDctos.IDTIPOORDEN = 	                    "
                + "        tblDctosOrdenesDetalle.IDTIPOORDEN		    "
                + "    AND  tblDctos.IDORDEN     = 	                    "
                + "           tblDctosOrdenesDetalle.IDORDEN)  		    "
                + "AND tblDctosOrdenesDetalle.idTipo            = 2         "
                + "AND tblDctosOrdenesDetalle.IDPLU             = 	    "
                + "                       tblPlusCombo.idPluCombo	    "
                + "GROUP BY tblPlusCombo.idPlu ) AS tmpDET                  "
                + "GROUP BY tmpDET.idPlu) AS tmpMVT	                    "
                + "ON tblPlus.idPlu  = tmpMVT.idPlu	                    "
                + "ORDER BY tblCategorias.nombreCategoria, 	            "
                + "         tblMarcas.nombreMarca,	        	    "
                + "          tblPlus.nombrePlu			            ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaPluBean fachadaBean;

            while (rs.next()) {

                fachadaBean = new FachadaPluBean();

                //
                fachadaBean.setIdPlu(rs.getInt("idPlu"));
                fachadaBean.setNombrePlu(rs.getString("nombrePlu"));
                fachadaBean.setPorcentajeIva(rs.getDouble("porcentajeIva"));
                fachadaBean.setIdLinea(rs.getInt("idLinea"));
                fachadaBean.setIdCategoria(rs.getInt("idCategoria"));
                fachadaBean.setIdMarca(rs.getInt("idMarca"));
                fachadaBean.setNombreCategoria(rs.getString("nombreCategoria"));
                fachadaBean.setNombreMarca(rs.getString("nombreMarca"));
                fachadaBean.setNombreLinea(rs.getString("nombreLinea"));
                fachadaBean.setCantidad(rs.getDouble("cantidad"));
                fachadaBean.setVrVentaSinIva(rs.getDouble("vrVentaSinIva"));
                fachadaBean.setVrVentaConIva(rs.getDouble("vrVentaConIva"));
                fachadaBean.setVrCostoSinIva(rs.getDouble("vrCostoSinIva"));
                fachadaBean.setVrCostoConIva(rs.getDouble("vrCostoConIva"));
                fachadaBean.setVrCostoIND(rs.getDouble("vrCostoIND"));

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

    // listaMargenPlu
    public Vector listaMargenPlu(String xIdTipoOrdenCadena,
            String xFechaInicial,
            String xFechaFinal,
            int xIndicadorInicial,
            int xIndicadorFinal) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        //
        String selectStr =
                "SELECT  tblCategorias.nombreCategoria,                     "
                + "        tblPlus.nombrePlu, 				    "
                + "        tblPlus.porcentajeIva, 			    "
                + "        tblMarcas.nombreMarca,			    "
                + "        tblPlus.idPlu,				    "
                + "        tmpVTA.vrVentaSinIva,			    "
                + "        tmpVTA.vrIva,				    "
                + "        tmpVTA.vrCostoIND,       			    "
                + "        tmpVTA.cantidad				    "
                + "FROM    tblCategorias				    "
                + "INNER JOIN tblPlus					    "
                + "ON tblCategorias.idLinea      = 			    "
                + "                      tblPlus.idLinea 		    "
                + "AND tblCategorias.IdCategoria = 			    "
                + "                  tblPlus.idCategoria 		    "
                + "INNER JOIN tblMarcas 				    "
                + "ON tblPlus.idMarca            = 			    "
                + "                     tblMarcas.idMarca		    "
                + "INNER JOIN (SELECT tmpMVT.idPlu,			    "
                + "       SUM((CASE WHEN			  	    "
                + "                   tmpMVT.indicador = 1		    "
                + "            THEN    tmpMVT.vrVentaSinIva		    "
                + "            ELSE    ( tmpMVT.vrVentaSinIva +	  	    "
                + "                      tmpMVT.vrIva )			    "
                + "            END)) AS vrVentaSinIva,    		    "
                + "        SUM((CASE WHEN			  	    "
                + "                    tmpMVT.indicador = 1		    "
                + "            THEN    tmpMVT.vrIva			    "
                + "            ELSE    0				    "
                + "            END)) AS vrIva,				    "
                + "       SUM(tmpMVT.vrCostoIND)    AS vrCostoIND,          "
                + "       SUM(tmpMVT.cantidad)    AS cantidad               "
                + " FROM						    "
                + "(SELECT tblDctos.indicador, 				    "
                + "       tblPlusCombo.IDPLU			            "
                + "       ,SUM(tblDctosOrdenesDetalle.CANTIDAD *	    "
                + "            tblPlusCombo.cantidad) AS cantidad	    "
                + "       ,SUM(tblDctosOrdenesDetalle.CANTIDAD *	    "
                + "            tblPlusCombo.cantidad         *		    "
                + "           (tblDctosOrdenesDetalle.vrVentaUnitario   -   "
                + "            tblDctosOrdenesDetalle.vrImpoconsumo)    /   "
                + "  ((1 + (tblDctosOrdenesDetalle.porcentajeIva /100)) *   "
                + " (1 + (tblDctosOrdenesDetalle.vrDsctoPie /100 ) ) *	    "
                + " (1 + (tblDctosOrdenesDetalle.porcentajeDscto /100 ))))  "
                + "                                      AS vrVentaSinIva   "
                + "       ,SUM( (tblDctosOrdenesDetalle.CANTIDAD *	    "
                + "            tblPlusCombo.cantidad           *   	    "
                + "           (tblDctosOrdenesDetalle.vrVentaUnitario -     "
                + "            tblDctosOrdenesDetalle.vrImpoconsumo)) *	    "
                + "    (1 - tblDctosOrdenesDetalle.porcentajeDscto / 100) * "
                + "        (1 - tblDctosOrdenesDetalle.vrDsctoPie / 100))   "
                + "                                     AS vrVentaConIva    "
                + "       ,SUM( (tblDctosOrdenesDetalle.CANTIDAD *	    "
                + "            tblPlusCombo.cantidad           *   	    "
                + "           (tblDctosOrdenesDetalle.vrVentaUnitario -     "
                + "            tblDctosOrdenesDetalle.vrImpoconsumo)) *	    "
                + "   (1 - tblDctosOrdenesDetalle.porcentajeDscto / 100) *  "
                + "   (1 - tblDctosOrdenesDetalle.vrDsctoPie / 100))     -  "
                + "       SUM(tblDctosOrdenesDetalle.CANTIDAD *		    "
                + "            tblPlusCombo.cantidad         *		    "
                + "           (tblDctosOrdenesDetalle.vrVentaUnitario   -   "
                + "            tblDctosOrdenesDetalle.vrImpoconsumo)    /   "
                + "    ((1 + (tblDctosOrdenesDetalle.porcentajeIva /100)) * "
                + "    (1 + (tblDctosOrdenesDetalle.vrDsctoPie /100 ) ) *   "
                + " (1 + (tblDctosOrdenesDetalle.porcentajeDscto /100 ))))  "
                + "                                               AS vrIva  "
                + "       ,SUM( (tblDctosOrdenesDetalle.CANTIDAD *	    "
                + "            tblPlusCombo.cantidad             * 	    "
                + "              tblDctosOrdenesDetalle.vrCosto )      /    "
                + "  (1 + tblDctosOrdenesDetalle.porcentajeIva / 100))      "
                + "                                   AS vrCostoSinIva	    "
                + "       ,SUM(  tblDctosOrdenesDetalle.CANTIDAD *	    "
                + "            tblPlusCombo.cantidad            *  	    "
                + "                     tblDctosOrdenesDetalle.vrCosto)     "
                + "                                     AS vrCostoConIva    "
                + "       ,SUM(  tblDctosOrdenesDetalle.CANTIDAD *	    "
                + "            tblPlusCombo.cantidad            *  	    "
                + "                  tblDctosOrdenesDetalle.vrCostoIND)     "
                + "                                         AS vrCostoIND   "
                + "FROM   tblDctos 					    "
                + "INNER JOIN tblDctosOrdenesDetalle			    "
                + "ON  tblDctos.IDLOCAL     = 				    "
                + "       tblDctosOrdenesDetalle.IDLOCAL		    "
                + "AND tblDctos.IDTIPOORDEN = 				    "
                + "   tblDctosOrdenesDetalle.IDTIPOORDEN		    "
                + "AND tblDctos.IDORDEN     = 				    "
                + "       tblDctosOrdenesDetalle.IDORDEN		    "
                + "INNER JOIN tblPlusCombo				    "
                + "ON tblDctosOrdenesDetalle.IDPLU                    =     "
                + "              tblPlusCombo.idPluCombo		    "
                + "   WHERE (tblDctos.IDLOCAL   =                           "
                + getIdLocal() + " )                                        "
                + "   AND (tblDctos.IDTIPOORDEN IN                         ("
                + xIdTipoOrdenCadena + " ))		                    "
                + "   AND tblDctos.fechaDcto BETWEEN                       '"
                + xFechaInicial + "' AND                                   '"
                + xFechaFinal + "'                                          "
                + "   AND tblDctos.indicador BETWEEN                        "
                + xIndicadorInicial + " AND                                 "
                + xIndicadorFinal + "                                       "
                + "GROUP BY tblDctos.indicador, 			    "
                + "       tblPlusCombo.IDPLU				    "
                + "UNION						    "
                + "SELECT tblDctos.indicador, 				    "
                + "       tblDctosOrdenesDetalle.IDPLU			    "
                + "       ,SUM(tblDctosOrdenesDetalle.CANTIDAD)		    "
                + "                                 AS cantidad		    "
                + "       ,SUM(tblDctosOrdenesDetalle.cantidad          *   "
                + "           (tblDctosOrdenesDetalle.vrVentaUnitario   -   "
                + "            tblDctosOrdenesDetalle.vrImpoconsumo)    /   "
                + "    ((1 + (tblDctosOrdenesDetalle.porcentajeIva /100)) * "
                + "    (1 + (tblDctosOrdenesDetalle.vrDsctoPie /100 ) ) *   "
                + "  (1 + (tblDctosOrdenesDetalle.porcentajeDscto /100 )))) "
                + "                                      AS vrVentaSinIva   "
                + "       ,SUM( (tblDctosOrdenesDetalle.CANTIDAD       *    "
                + "           (tblDctosOrdenesDetalle.vrVentaUnitario -     "
                + "            tblDctosOrdenesDetalle.vrImpoconsumo)) *	  "
                + "   (1 - tblDctosOrdenesDetalle.porcentajeDscto / 100) *  "
                + "          (1 - tblDctosOrdenesDetalle.vrDsctoPie / 100)) "
                + "                                     AS vrVentaConIva    "
                + "       ,SUM( (tblDctosOrdenesDetalle.CANTIDAD      *     "
                + "           (tblDctosOrdenesDetalle.vrVentaUnitario -     "
                + "            tblDctosOrdenesDetalle.vrImpoconsumo)) *	    "
                + "  (1 - tblDctosOrdenesDetalle.porcentajeDscto / 100) *   "
                + "  (1 - tblDctosOrdenesDetalle.vrDsctoPie / 100))     -   "
                + "        SUM(tblDctosOrdenesDetalle.cantidad         *    "
                + "           (tblDctosOrdenesDetalle.vrVentaUnitario   -   "
                + "            tblDctosOrdenesDetalle.vrImpoconsumo)    /   "
                + "  ((1 + (tblDctosOrdenesDetalle.porcentajeIva /100)) *   "
                + "      (1 + (tblDctosOrdenesDetalle.vrDsctoPie /100 ) ) * "
                + " (1 + (tblDctosOrdenesDetalle.porcentajeDscto /100 ))))  "
                + "                                               AS vrIva  "
                + "       ,SUM( (tblDctosOrdenesDetalle.CANTIDAD       *    "
                + "              tblDctosOrdenesDetalle.vrCosto )      /    "
                + "      (1 + tblDctosOrdenesDetalle.porcentajeIva / 100))  "
                + "                                     AS vrCostoSinIva    "
                + "       ,SUM(  tblDctosOrdenesDetalle.CANTIDAD    *       "
                + "                     tblDctosOrdenesDetalle.vrCosto)     "
                + "                                     AS vrCostoConIva    "
                + "       ,SUM(  tblDctosOrdenesDetalle.CANTIDAD    *       "
                + "                  tblDctosOrdenesDetalle.vrCostoIND)     "
                + "                                       AS vrCostoIND	    "
                + "FROM   tblDctos					    "
                + "INNER JOIN tblDctosOrdenesDetalle			    "
                + "ON  tblDctos.IDLOCAL     = 				    "
                + "    tblDctosOrdenesDetalle.IDLOCAL			    "
                + "AND tblDctos.IDTIPOORDEN = 				    "
                + "    tblDctosOrdenesDetalle.IDTIPOORDEN		    "
                + "AND tblDctos.IDORDEN     = 				    "
                + "    tblDctosOrdenesDetalle.IDORDEN			    "
                + "   WHERE (tblDctos.IDLOCAL   =                           "
                + getIdLocal() + " )                                        "
                + "   AND (tblDctos.IDTIPOORDEN IN                         ("
                + xIdTipoOrdenCadena + " ))		                    "
                + "   AND tblDctos.fechaDcto BETWEEN                       '"
                + xFechaInicial + "' AND                                   '"
                + xFechaFinal + "'                                          "
                + "   AND tblDctos.indicador BETWEEN                        "
                + xIndicadorInicial + " AND                                 "
                + xIndicadorFinal + "                                       "
                + "AND  tblDctos.IDLOCAL     = 			            "
                + "                          tblDctosOrdenesDetalle.IDLOCAL "
                + "AND  tblDctos.IDTIPOORDEN = 			            "
                + "                     tblDctosOrdenesDetalle.IDTIPOORDEN  "
                + "AND  tblDctos.IDORDEN     = 			            "
                + "                         tblDctosOrdenesDetalle.IDORDEN  "
                + "AND tblDctosOrdenesDetalle.idTipo            = 1         "
                + "GROUP BY tblDctos.indicador, 			    "
                + "         tblDctosOrdenesDetalle.IDPLU) AS tmpMVT	    "
                + "GROUP BY tmpMVT.idPlu) AS tmpVTA			    "
                + "ON tmpVTA.idPlu       = tblPlus.idPlu 		    "
                + "ORDER BY tblCategorias.nombreCategoria, 		    "
                + "         tblMarcas.nombreMarca,			    "
                + "          tblPlus.nombrePlu				    ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaPluBean fachadaBean;

            while (rs.next()) {

                fachadaBean = new FachadaPluBean();

                //
                fachadaBean.setIdPlu(rs.getInt("idPlu"));
                fachadaBean.setNombrePlu(rs.getString("nombrePlu"));
                fachadaBean.setPorcentajeIva(rs.getDouble("porcentajeIva"));
                fachadaBean.setNombreCategoria(rs.getString("nombreCategoria"));
                fachadaBean.setNombreMarca(rs.getString("nombreMarca"));
                fachadaBean.setVrVentaSinIva(rs.getDouble("vrVentaSinIva"));
                fachadaBean.setVrIva(rs.getDouble("vrIva"));
                fachadaBean.setVrCostoIND(rs.getDouble("vrCostoIND"));
                fachadaBean.setCantidad(rs.getDouble("cantidad"));
                fachadaBean.setVrVentaConIva(rs.getDouble("vrVentaSinIva")
                        + rs.getDouble("vrIva"));

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

    // listaRefciasProveedor
    public Vector listaRefciasProveedor(String xIdTipoOrdenCadena,
            String xFechaInicial,
            String xFechaFinal,
            int xIndicadorInicial,
            int xIndicadorFinal,
            String xIdTercero) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        //
        String selectStr =
                "SELECT  tblCategorias.nombreCategoria,                     "
                + "        tblPlus.nombrePlu, 				    "
                + "        tblPlus.porcentajeIva, 			    "
                + "        tblMarcas.nombreMarca,			    "
                + "        tblPlus.idPlu,				    "
                + "        tmpVTA.vrVentaSinIva,			    "
                + "        tmpVTA.vrIva,				    "
                + "        tmpVTA.vrCostoIND,       			    "
                + "        tmpVTA.cantidad				    "
                + "FROM    tblCategorias				    "
                + "INNER JOIN tblPlus					    "
                + "ON tblCategorias.idLinea      = 			    "
                + "                      tblPlus.idLinea 		    "
                + "AND tblCategorias.IdCategoria = 			    "
                + "                  tblPlus.idCategoria 		    "
                + "INNER JOIN tblMarcas 				    "
                + "ON tblPlus.idMarca            = 			    "
                + "                     tblMarcas.idMarca		    "
                + "INNER JOIN (SELECT tmpMVT.idPlu,			    "
                + "       SUM((CASE WHEN			  	    "
                + "                   tmpMVT.indicador = 1		    "
                + "            THEN    tmpMVT.vrVentaSinIva		    "
                + "            ELSE    ( tmpMVT.vrVentaSinIva +	  	    "
                + "                      tmpMVT.vrIva )			    "
                + "            END)) AS vrVentaSinIva,    		    "
                + "        SUM((CASE WHEN			  	    "
                + "                    tmpMVT.indicador = 1		    "
                + "            THEN    tmpMVT.vrIva			    "
                + "            ELSE    0				    "
                + "            END)) AS vrIva,				    "
                + "       SUM(tmpMVT.vrCostoIND)    AS vrCostoIND,          "
                + "       SUM(tmpMVT.cantidad)    AS cantidad               "
                + " FROM						    "
                + "(SELECT tblDctos.indicador, 				    "
                + "       tblPlusCombo.IDPLU			            "
                + "       ,SUM(tblDctosOrdenesDetalle.CANTIDAD *	    "
                + "            tblPlusCombo.cantidad) AS cantidad	    "
                + "       ,SUM(tblDctosOrdenesDetalle.CANTIDAD *	    "
                + "            tblPlusCombo.cantidad         *		    "
                + "           (tblDctosOrdenesDetalle.vrVentaUnitario   -   "
                + "            tblDctosOrdenesDetalle.vrImpoconsumo)    /   "
                + "  ((1 + (tblDctosOrdenesDetalle.porcentajeIva /100)) *   "
                + " (1 + (tblDctosOrdenesDetalle.vrDsctoPie /100 ) ) *	    "
                + " (1 + (tblDctosOrdenesDetalle.porcentajeDscto /100 ))))  "
                + "                                      AS vrVentaSinIva   "
                + "       ,SUM( (tblDctosOrdenesDetalle.CANTIDAD *	    "
                + "            tblPlusCombo.cantidad           *   	    "
                + "           (tblDctosOrdenesDetalle.vrVentaUnitario -     "
                + "            tblDctosOrdenesDetalle.vrImpoconsumo)) *	    "
                + "    (1 - tblDctosOrdenesDetalle.porcentajeDscto / 100) * "
                + "        (1 - tblDctosOrdenesDetalle.vrDsctoPie / 100))   "
                + "                                     AS vrVentaConIva    "
                + "       ,SUM( (tblDctosOrdenesDetalle.CANTIDAD *	    "
                + "            tblPlusCombo.cantidad           *   	    "
                + "           (tblDctosOrdenesDetalle.vrVentaUnitario -     "
                + "            tblDctosOrdenesDetalle.vrImpoconsumo)) *	    "
                + "   (1 - tblDctosOrdenesDetalle.porcentajeDscto / 100) *  "
                + "   (1 - tblDctosOrdenesDetalle.vrDsctoPie / 100))     -  "
                + "       SUM(tblDctosOrdenesDetalle.CANTIDAD *		    "
                + "            tblPlusCombo.cantidad         *		    "
                + "           (tblDctosOrdenesDetalle.vrVentaUnitario   -   "
                + "            tblDctosOrdenesDetalle.vrImpoconsumo)    /   "
                + "    ((1 + (tblDctosOrdenesDetalle.porcentajeIva /100)) * "
                + "    (1 + (tblDctosOrdenesDetalle.vrDsctoPie /100 ) ) *   "
                + " (1 + (tblDctosOrdenesDetalle.porcentajeDscto /100 ))))  "
                + "                                               AS vrIva  "
                + "       ,SUM( (tblDctosOrdenesDetalle.CANTIDAD *	    "
                + "            tblPlusCombo.cantidad             * 	    "
                + "              tblDctosOrdenesDetalle.vrCosto )      /    "
                + "  (1 + tblDctosOrdenesDetalle.porcentajeIva / 100))      "
                + "                                   AS vrCostoSinIva	    "
                + "       ,SUM(  tblDctosOrdenesDetalle.CANTIDAD *	    "
                + "            tblPlusCombo.cantidad            *  	    "
                + "                     tblDctosOrdenesDetalle.vrCosto)     "
                + "                                     AS vrCostoConIva    "
                + "       ,SUM(  tblDctosOrdenesDetalle.CANTIDAD *	    "
                + "            tblPlusCombo.cantidad            *  	    "
                + "                  tblDctosOrdenesDetalle.vrCostoIND)     "
                + "                                         AS vrCostoIND   "
                + "FROM   tblDctos 					    "
                + "INNER JOIN tblDctosOrdenesDetalle			    "
                + "ON  tblDctos.IDLOCAL     = 				    "
                + "       tblDctosOrdenesDetalle.IDLOCAL		    "
                + "AND tblDctos.IDTIPOORDEN = 				    "
                + "   tblDctosOrdenesDetalle.IDTIPOORDEN		    "
                + "AND tblDctos.IDORDEN     = 				    "
                + "       tblDctosOrdenesDetalle.IDORDEN		    "
                + "INNER JOIN tblPlusCombo				    "
                + "ON tblDctosOrdenesDetalle.IDPLU                    =     "
                + "              tblPlusCombo.idPluCombo		    "
                + "   WHERE (tblDctos.IDLOCAL   =                           "
                + getIdLocal() + " )                                        "
                + "   AND (tblDctos.IDTIPOORDEN IN                         ("
                + xIdTipoOrdenCadena + " ))		                    "
                + "   AND tblDctos.fechaDcto BETWEEN                       '"
                + xFechaInicial + "' AND                                   '"
                + xFechaFinal + "'                                          "
                + "   AND tblDctos.indicador BETWEEN                        "
                + xIndicadorInicial + " AND                                 "
                + xIndicadorFinal + "                                       "
                + "   AND tblDctos.idCliente =                             '"
                + xIdTercero + "'                                           "
                + "GROUP BY tblDctos.indicador, 			    "
                + "       tblPlusCombo.IDPLU				    "
                + "UNION						    "
                + "SELECT tblDctos.indicador, 				    "
                + "       tblDctosOrdenesDetalle.IDPLU			    "
                + "       ,SUM(tblDctosOrdenesDetalle.CANTIDAD)		    "
                + "                                 AS cantidad		    "
                + "       ,SUM(tblDctosOrdenesDetalle.cantidad          *   "
                + "           (tblDctosOrdenesDetalle.vrVentaUnitario   -   "
                + "            tblDctosOrdenesDetalle.vrImpoconsumo)    /   "
                + "    ((1 + (tblDctosOrdenesDetalle.porcentajeIva /100)) * "
                + "    (1 + (tblDctosOrdenesDetalle.vrDsctoPie /100 ) ) *   "
                + "  (1 + (tblDctosOrdenesDetalle.porcentajeDscto /100 )))) "
                + "                                      AS vrVentaSinIva   "
                + "       ,SUM( (tblDctosOrdenesDetalle.CANTIDAD       *    "
                + "           (tblDctosOrdenesDetalle.vrVentaUnitario -     "
                + "            tblDctosOrdenesDetalle.vrImpoconsumo)) *	  "
                + "   (1 - tblDctosOrdenesDetalle.porcentajeDscto / 100) *  "
                + "          (1 - tblDctosOrdenesDetalle.vrDsctoPie / 100)) "
                + "                                     AS vrVentaConIva    "
                + "       ,SUM( (tblDctosOrdenesDetalle.CANTIDAD      *     "
                + "           (tblDctosOrdenesDetalle.vrVentaUnitario -     "
                + "            tblDctosOrdenesDetalle.vrImpoconsumo)) *	    "
                + "  (1 - tblDctosOrdenesDetalle.porcentajeDscto / 100) *   "
                + "  (1 - tblDctosOrdenesDetalle.vrDsctoPie / 100))     -   "
                + "        SUM(tblDctosOrdenesDetalle.cantidad         *    "
                + "           (tblDctosOrdenesDetalle.vrVentaUnitario   -   "
                + "            tblDctosOrdenesDetalle.vrImpoconsumo)    /   "
                + "  ((1 + (tblDctosOrdenesDetalle.porcentajeIva /100)) *   "
                + "      (1 + (tblDctosOrdenesDetalle.vrDsctoPie /100 ) ) * "
                + " (1 + (tblDctosOrdenesDetalle.porcentajeDscto /100 ))))  "
                + "                                               AS vrIva  "
                + "       ,SUM( (tblDctosOrdenesDetalle.CANTIDAD       *    "
                + "              tblDctosOrdenesDetalle.vrCosto )      /    "
                + "      (1 + tblDctosOrdenesDetalle.porcentajeIva / 100))  "
                + "                                     AS vrCostoSinIva    "
                + "       ,SUM(  tblDctosOrdenesDetalle.CANTIDAD    *       "
                + "                     tblDctosOrdenesDetalle.vrCosto)     "
                + "                                     AS vrCostoConIva    "
                + "       ,SUM(  tblDctosOrdenesDetalle.CANTIDAD    *       "
                + "                  tblDctosOrdenesDetalle.vrCostoIND)     "
                + "                                       AS vrCostoIND	    "
                + "FROM   tblDctos					    "
                + "INNER JOIN tblDctosOrdenesDetalle			    "
                + "ON  tblDctos.IDLOCAL     = 				    "
                + "    tblDctosOrdenesDetalle.IDLOCAL			    "
                + "AND tblDctos.IDTIPOORDEN = 				    "
                + "    tblDctosOrdenesDetalle.IDTIPOORDEN		    "
                + "AND tblDctos.IDORDEN     = 				    "
                + "    tblDctosOrdenesDetalle.IDORDEN			    "
                + "   WHERE (tblDctos.IDLOCAL   =                           "
                + getIdLocal() + " )                                        "
                + "   AND (tblDctos.IDTIPOORDEN IN                         ("
                + xIdTipoOrdenCadena + " ))		                    "
                + "   AND tblDctos.fechaDcto BETWEEN                       '"
                + xFechaInicial + "' AND                                   '"
                + xFechaFinal + "'                                          "
                + "   AND tblDctos.indicador BETWEEN                        "
                + xIndicadorInicial + " AND                                 "
                + xIndicadorFinal + "                                       "
                + "   AND tblDctos.idCliente =                             '"
                + xIdTercero + "'                                           "                
                + "AND  tblDctos.IDLOCAL     = 			            "
                + "                          tblDctosOrdenesDetalle.IDLOCAL "
                + "AND  tblDctos.IDTIPOORDEN = 			            "
                + "                     tblDctosOrdenesDetalle.IDTIPOORDEN  "
                + "AND  tblDctos.IDORDEN     = 			            "
                + "                         tblDctosOrdenesDetalle.IDORDEN  "
                + "AND tblDctosOrdenesDetalle.idTipo            = 1         "
                + "GROUP BY tblDctos.indicador, 			    "
                + "         tblDctosOrdenesDetalle.IDPLU) AS tmpMVT	    "
                + "GROUP BY tmpMVT.idPlu) AS tmpVTA			    "
                + "ON tmpVTA.idPlu       = tblPlus.idPlu 		    "
                + "ORDER BY tblCategorias.nombreCategoria, 		    "
                + "         tblMarcas.nombreMarca,			    "
                + "          tblPlus.nombrePlu				    ";

       

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaPluBean fachadaBean;

            while (rs.next()) {

                fachadaBean = new FachadaPluBean();

                //
                fachadaBean.setIdPlu(rs.getInt("idPlu"));
                fachadaBean.setNombrePlu(rs.getString("nombrePlu"));
                fachadaBean.setPorcentajeIva(rs.getDouble("porcentajeIva"));
                fachadaBean.setNombreCategoria(rs.getString("nombreCategoria"));
                fachadaBean.setNombreMarca(rs.getString("nombreMarca"));
                fachadaBean.setVrVentaSinIva(rs.getDouble("vrVentaSinIva"));
                fachadaBean.setVrIva(rs.getDouble("vrIva"));
                fachadaBean.setVrCostoIND(rs.getDouble("vrCostoIND"));
                fachadaBean.setCantidad(rs.getDouble("cantidad"));
                fachadaBean.setVrVentaConIva(rs.getDouble("vrVentaSinIva")
                        + rs.getDouble("vrIva"));

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

    // listaVenta
    public Vector listaVentaUnVendedor(String xIdTipoOrdenCadena,
            String xFechaInicial,
            String xFechaFinal,
            int xIndicadorInicial,
            int xIndicadorFinal,
            double xIdVendedor) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        //
        String selectStr =
                "SELECT  tblPlus.idPlu,                                     "
                + "        tblPlus.nombrePlu, 				    "
                + "        tblPlus.porcentajeIva, 			    "
                + "        tblPlus.idLinea, 				    "
                + "        tblPlus.idCategoria, 		            "
                + "        tblPlus.idMarca, 				    "
                + "        tblCategorias.nombreCategoria, 		    "
                + "        tblMarcas.nombreMarca, 			    "
                + "        tblLineas.nombreLinea,			    "
                + "        tmpMVT.cantidad,			            "
                + "        tmpMVT.vrVentaSinIva,			    "
                + "        tmpMVT.vrVentaConIva,			    "
                + "        tmpMVT.vrCostoSinIva,			    "
                + "        tmpMVT.vrCostoConIva,			    "
                + "        tmpMVT.vrCostoIND	  			    "
                + "FROM    tblLineas					    "
                + "INNER JOIN tblMarcas					    "
                + "INNER JOIN tblPlus 					    "
                + "ON tblMarcas.idMarca    = 				    "
                + "                     tblPlus.idMarca		            "
                + "INNER JOIN tblCategorias				    "
                + "ON tblPlus.idLinea      = 				    "
                + "               tblCategorias.idLinea			    "
                + "AND tblPlus.idCategoria = 				    "
                + "tblCategorias.IdCategoria 				    "
                + "ON tblLineas.idLinea = 				    "
                + "               tblCategorias.idLinea			    "
                + "INNER JOIN (						    "
                + "SELECT tmpDET.idPlu,					    "
                + "       SUM(tmpDET.cantidad) AS cantidad,		    "
                + "       SUM(tmpDET.vrVentaSinIva) AS vrVentaSinIva,	    "
                + "       SUM(tmpDET.vrVentaConIva) AS vrVentaConIva,       "
                + "       SUM(tmpDET.vrCostoSinIva) AS vrCostoSinIva,       "
                + "       SUM(tmpDET.vrCostoConIva) AS vrCostoConIva,       "
                + "       SUM(tmpDET.vrCostoIND)    AS vrCostoIND           "
                + "FROM (						    "
                + "SELECT tblDctosOrdenesDetalle.IDPLU			    "
                + "    ,SUM(tblDctosOrdenesDetalle.CANTIDAD)		    "
                + "                              AS cantidad		    "
                + "    ,SUM(tblDctosOrdenesDetalle.cantidad         *	    "
                + "    (tblDctosOrdenesDetalle.vrVentaUnitario   -	    "
                + "     tblDctosOrdenesDetalle.vrImpoconsumo)    /	    "
                + "    ((1 + (tblDctosOrdenesDetalle.porcentajeIva /100)) * "
                + "    (1 + (tblDctosOrdenesDetalle.vrDsctoPie /100 ) ) *   "
                + "  (1 + (tblDctosOrdenesDetalle.porcentajeDscto /100 )))) "
                + "                                       AS vrVentaSinIva  "
                + "    ,SUM( (tblDctosOrdenesDetalle.CANTIDAD           *   "
                + "           (tblDctosOrdenesDetalle.vrVentaUnitario -     "
                + "             tblDctosOrdenesDetalle.vrImpoconsumo)) *    "
                + "    (1 - tblDctosOrdenesDetalle.porcentajeDscto / 100) * "
                + "     (1 - tblDctosOrdenesDetalle.vrDsctoPie / 100))      "
                + "                                       AS vrVentaConIva  "
                + "    ,SUM( (tblDctosOrdenesDetalle.CANTIDAD             * "
                + "          tblDctosOrdenesDetalle.vrCosto )      /        "
                + "    (1 + tblDctosOrdenesDetalle.porcentajeIva / 100))    "
                + "                                      AS vrCostoSinIva   "
                + "    ,SUM(  tblDctosOrdenesDetalle.CANTIDAD            *  "
                + "            tblDctosOrdenesDetalle.vrCosto)              "
                + "                                       AS vrCostoConIva  "
                + "    ,SUM(  tblDctosOrdenesDetalle.CANTIDAD            *  "
                + "            tblDctosOrdenesDetalle.vrCostoIND)           "
                + "                                       AS vrCostoIND     "
                + "FROM tblDctosOrdenesDetalle				    "
                + "WHERE EXISTS 					    "
                + " ( SELECT tblDctos.*					    "
                + "   FROM   tblDctos					    "
                + "   INNER JOIN tblDctosOrdenes			    "
                + "   ON  tblDctos.IDLOCAL     = 			    "
                + "                    tblDctosOrdenes.IDLOCAL		    "
                + "   AND tblDctos.IDTIPOORDEN = 			    "
                + "                tblDctosOrdenes.IDTIPOORDEN		    "
                + "   AND tblDctos.IDORDEN     = 			    "
                + "                    tblDctosOrdenes.IDORDEN		    "
                + "   WHERE (tblDctos.IDLOCAL   =                           "
                + getIdLocal() + " )                                        "
                + "   AND (tblDctos.IDTIPOORDEN IN                         ("
                + xIdTipoOrdenCadena + " ))		                    "
                + "   AND tblDctos.fechaDcto BETWEEN                       '"
                + xFechaInicial + "' AND                                   '"
                + xFechaFinal + "'                                          "
                + "   AND tblDctos.indicador BETWEEN                        "
                + xIndicadorInicial + " AND                                 "
                + xIndicadorFinal + "                                       "
                + "   AND  tblDctos.idVendedor    = 			    "
                + xIdVendedor + "                                           "
                + "   AND  tblDctos.IDLOCAL     = 			    "
                + "             tblDctosOrdenesDetalle.IDLOCAL		    "
                + "   AND  tblDctos.IDTIPOORDEN = 			    "
                + "         tblDctosOrdenesDetalle.IDTIPOORDEN		    "
                + "   AND  tblDctos.IDORDEN     = 			    "
                + "             tblDctosOrdenesDetalle.IDORDEN)  	    "
                + "AND tblDctosOrdenesDetalle.idTipo            = 1         "
                + "GROUP BY tblDctosOrdenesDetalle.IDPLU		    "
                + "UNION                                                    "
                + "SELECT tblPlusCombo.idPlu				    "
                + "    ,SUM(tblDctosOrdenesDetalle.CANTIDAD      *          "
                + "         tblPlusCombo.cantidad) AS cantidad		    "
                + "    ,SUM(tblDctosOrdenesDetalle.cantidad      *	    "
                + "    (tblDctosOrdenesDetalle.vrVentaUnitario   -	    "
                + "     tblDctosOrdenesDetalle.vrImpoconsumo)    /	    "
                + "    ((1 + (tblDctosOrdenesDetalle.porcentajeIva /100)) * "
                + "    (1 + (tblDctosOrdenesDetalle.vrDsctoPie /100 ) ) *   "
                + "  (1 + (tblDctosOrdenesDetalle.porcentajeDscto /100 )))) "
                + "                                       AS vrVentaSinIva  "
                + "    ,SUM( (tblDctosOrdenesDetalle.CANTIDAD           *   "
                + "           (tblDctosOrdenesDetalle.vrVentaUnitario -     "
                + "             tblDctosOrdenesDetalle.vrImpoconsumo)) *    "
                + "    (1 - tblDctosOrdenesDetalle.porcentajeDscto / 100) * "
                + "     (1 - tblDctosOrdenesDetalle.vrDsctoPie / 100))      "
                + "                                       AS vrVentaConIva  "
                + "    ,SUM( (tblDctosOrdenesDetalle.CANTIDAD             * "
                + "          tblDctosOrdenesDetalle.vrCosto )      /        "
                + "    (1 + tblDctosOrdenesDetalle.porcentajeIva / 100))    "
                + "                                      AS vrCostoSinIva   "
                + "    ,SUM(  tblDctosOrdenesDetalle.CANTIDAD            *  "
                + "            tblDctosOrdenesDetalle.vrCosto            )  "
                + "                                       AS vrCostoConIva  "
                + "    ,SUM(  tblDctosOrdenesDetalle.CANTIDAD            *  "
                + "            tblDctosOrdenesDetalle.vrCostoIND)           "
                + "                                       AS vrCostoIND     "
                + "FROM tblDctosOrdenesDetalle,				    "
                + "                tblPlusCombo				    "
                + "WHERE EXISTS 					    "
                + "  ( SELECT tblDctos.*				    "
                + "    FROM   tblDctos					    "
                + "    INNER JOIN tblDctosOrdenes			    "
                + "    ON  tblDctos.IDLOCAL     = 			    "
                + "                    tblDctosOrdenes.IDLOCAL		    "
                + "    AND tblDctos.IDTIPOORDEN = 			    "
                + "                tblDctosOrdenes.IDTIPOORDEN		    "
                + "    AND tblDctos.IDORDEN     = 			    "
                + "                    tblDctosOrdenes.IDORDEN	    	    "
                + "    WHERE (tblDctos.IDLOCAL   =     		            "
                + getIdLocal() + " )                                        "
                + "    AND (tblDctos.IDTIPOORDEN IN                        ("
                + xIdTipoOrdenCadena + " ))		                    "
                + "   AND tblDctos.fechaDcto BETWEEN                       '"
                + xFechaInicial + "' AND                                   '"
                + xFechaFinal + "'                                          "
                + "   AND tblDctos.indicador BETWEEN                        "
                + xIndicadorInicial + " AND                                 "
                + xIndicadorFinal + "                                       "
                + "   AND  tblDctos.idVendedor    = 			    "
                + xIdVendedor + "                                           "
                + "    AND  tblDctos.IDLOCAL     =                          "
                + "             tblDctosOrdenesDetalle.IDLOCAL		    "
                + "    AND  tblDctos.IDTIPOORDEN = 	                    "
                + "        tblDctosOrdenesDetalle.IDTIPOORDEN		    "
                + "    AND  tblDctos.IDORDEN     = 	                    "
                + "           tblDctosOrdenesDetalle.IDORDEN)  		    "
                + "AND tblDctosOrdenesDetalle.idTipo            = 2         "
                + "AND tblDctosOrdenesDetalle.IDPLU             = 	    "
                + "                       tblPlusCombo.idPluCombo	    "
                + "GROUP BY tblPlusCombo.idPlu ) AS tmpDET                  "
                + "GROUP BY tmpDET.idPlu) AS tmpMVT	                    "
                + "ON tblPlus.idPlu  = tmpMVT.idPlu	                    "
                + "ORDER BY tblCategorias.nombreCategoria, 	            "
                + "         tblMarcas.nombreMarca,	        	    "
                + "          tblPlus.nombrePlu			            ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaPluBean fachadaBean;

            while (rs.next()) {

                fachadaBean = new FachadaPluBean();

                //
                fachadaBean.setIdPlu(rs.getInt("idPlu"));
                fachadaBean.setNombrePlu(rs.getString("nombrePlu"));
                fachadaBean.setPorcentajeIva(rs.getDouble("porcentajeIva"));
                fachadaBean.setIdLinea(rs.getInt("idLinea"));
                fachadaBean.setIdCategoria(rs.getInt("idCategoria"));
                fachadaBean.setIdMarca(rs.getInt("idMarca"));
                fachadaBean.setNombreCategoria(rs.getString("nombreCategoria"));
                fachadaBean.setNombreMarca(rs.getString("nombreMarca"));
                fachadaBean.setNombreLinea(rs.getString("nombreLinea"));
                fachadaBean.setCantidad(rs.getDouble("cantidad"));
                fachadaBean.setVrVentaSinIva(rs.getDouble("vrVentaSinIva"));
                fachadaBean.setVrVentaConIva(rs.getDouble("vrVentaConIva"));
                fachadaBean.setVrCostoSinIva(rs.getDouble("vrCostoSinIva"));
                fachadaBean.setVrCostoConIva(rs.getDouble("vrCostoConIva"));
                fachadaBean.setVrCostoIND(rs.getDouble("vrCostoIND"));

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
}
