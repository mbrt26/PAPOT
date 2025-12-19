package com.solucionesweb.losbeans.negocio;

// Importa la clase helper JDBCAccess que contiene los accesos a la base de datos
import com.solucionesweb.lasayudas.JDBCAccess;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaDctoBean;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaTerceroBean;

// Importa los paquetes de java
import java.sql.*;
import java.io.*;
import java.io.IOException.*;

// Importa los paquetes de javax
import java.util.Vector;
import javax.naming.*;

public class DctoContableBean extends FachadaDctoBean
        implements Serializable {

    // Nombre JNDI del Data Source que esta clase requiere
    private static final String DATA_SOURCE_NAME = "CommerceAccess";
    // Clase helper usada para acceder a la base de datos
    private JDBCAccess jdbcAccess;

    // Metodo constructor por defecto sin parametros
    public DctoContableBean() {

        // Al instanciar el Bean establece la conexion a la base de datos
        jdbcAccess = new JDBCAccess(DATA_SOURCE_NAME);
    }

    // borraTmpDctosContable
    public boolean borraTmpDctosContable() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                " IF  EXISTS (SELECT * FROM sys.objects                      "
                + " WHERE object_id = OBJECT_ID(N'[dbo].[tmpDctosContable]') "
                + " AND type in (N'U'))                                      "
                + " DROP TABLE [dbo].[tmpDctosContable] ";

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

    // borraTmpDctosContableDetalle
    public boolean borraTmpDctosContableDetalle() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                " IF  EXISTS (SELECT * FROM sys.objects                             "
                + " WHERE object_id = OBJECT_ID(N'[dbo].[tmpDctosContableDetalle]') "
                + " AND type in (N'U'))                                             "
                + " DROP TABLE [dbo].[tmpDctosContableDetalle] ";

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

    // ingresaTmpDctosContableCompra
    public boolean ingresaTmpDctosContableCompra(int xIdTipoTercero) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                "  SELECT tmpDET.IDLOCAL,                                                     "
                + "          tmpDET.IDTIPOORDEN,						"
                + "          tmpDET.IDORDEN,						        "
                + "          tmpDET.porcentajeIva,       				        "
                + "          tmpDET.idDcto,						        "
                + "          tmpDET.fechaDcto,						        "
                + "          tmpDET.idCliente,						        "
                + "          tmpDET.vrBase,						        "
                + "          tmpDET.vrIva,						        "
                + "          tmpDET.vrImpoconsumo,					        "
                + "          tmpDET.vrCostoMv,						        "
                + "          tmpDET.idDctoNitCC,						"
                + "          tmpDET.fechaDctoNitCC,				                "
                + "          tmpDET.vrRteFuente,						"
                + "          tmpDET.vrRteIva,						        "
                + "          tmpDET.vrRteIca,						        "
                + "          tmpDET.idTipoNegocio,       				        "
                + "          tmpDET.vrPago,       					        "
                + "          0 AS idRecibo,       					        "
                + "          SUM(tmpDET.vrVentaSinIva) 					        "
                + "                     AS vrVentaSinIva,				        "
                + "          SUM(tmpDET.vrVentaSinDscto) 				        "
                + "                   AS vrVentaSinDscto,				        "
                + "          SUM(tmpDET.vrIvaVenta) 					        "
                + "                        AS vrVentaIva,				        "
                + "          MAX(tmpTER.digitoVerificacion) AS digitoVerificacion	        "
                + "           ,MAX(tmpTER.nombreTercero) AS nombreTercero		        "
                + "           ,MAX(tmpTER.direccionTercero) AS direccionTercero 		"
                + "           ,MAX(tmpTER.ciudadTercero) AS ciudadTercero		        "
                + "           ,MAX(tmpTER.telefonoFijo) AS telefonoFijo 			"
                + "           ,MAX(tmpTER.telefonoCelular) AS telefonoCelular 		        "
                + "           ,MAX(tmpTER.telefonoFax) AS telefonoFax 			        "
                + "           ,MAX(tmpTER.email) AS email 				        "
                + "           ,MAX(tmpTER.contactoTercero) AS contactoTercero 		        "
                + "           ,MAX(tmpTER.idPersona) AS idPersona   			        "
                + "           ,MAX(tmpTER.idAutoRetenedor) AS idAutoRetenedor 		        "
                + "           ,MAX(tmpTER.idRegimen) AS idRegimen	     		        "
                + "           ,MAX(tmpTER.idDptoCiudad) AS idDptoCiudad			        "
                + "           ,MAX(tmpTER.departamentoTercero) AS departamentoTercero	        "
                + "           ,MAX(tmpTER.tipoIdTercero) AS tipoIdTercero		        "
                + "           ,MAX(tmpDET.prefijo) AS prefijo                                   "
                + "          INTO tmpDctosContable                                              "
                + "   FROM (								        "
                + "   SELECT tblDctosOrdenesDetalle.IDLOCAL,				        "
                + "          tblDctosOrdenesDetalle.IDTIPOORDEN,				"
                + "          tblDctosOrdenesDetalle.IDORDEN,				        "
                + "          tblDctosOrdenesDetalle.porcentajeIva,			        "
                + " 		(tblDctosOrdenesDetalle.cantidad          *                     "
                + " 		(tblDctosOrdenesDetalle.vrVentaUnitario   -                     "
                + " 		tblDctosOrdenesDetalle.vrImpoconsumo /                          "
                + "              tblDctosOrdenesDetalle.cantidad)    *                          "
                + " 		( 1 - ( tblDctosOrdenesDetalle.vrDsctoPie /  100 ) )      *     "
                + " 		( 1 - ( tblDctosOrdenesDetalle.porcentajeDscto /  100 ) ) /     "
                + "		( 1 + ( tblDctosOrdenesDetalle.porcentajeIva /  100 ) ))        "
                + "             	                            AS vrVentaSinIva,           "
                + " 			(tblDctosOrdenesDetalle.cantidad          *             "
                + " 			(tblDctosOrdenesDetalle.vrVentaUnitario   -             "
                + " 		(tblDctosOrdenesDetalle.vrImpoconsumo /                         "
                + "               tblDctosOrdenesDetalle.cantidad))    *                        "
                + "		( 1 - ( tblDctosOrdenesDetalle.vrDsctoPie /  100 ) )      *     "
                + " 		( 1 - ( tblDctosOrdenesDetalle.porcentajeDscto /  100 ) ) /     "
                + " 		( 1 + ( tblDctosOrdenesDetalle.porcentajeIva /  100 ) ))        "
                + "                                                  AS vrVentaSinDscto,	"
                + " 		(tblDctosOrdenesDetalle.cantidad          *                     "
                + "		(tblDctosOrdenesDetalle.vrVentaUnitario   -                     "
                + "		(tblDctosOrdenesDetalle.vrImpoconsumo /                         "
                + "              tblDctosOrdenesDetalle.cantidad))    *                         "
                + "     	( 1 - ( tblDctosOrdenesDetalle.vrDsctoPie /  100 ) )      *     "
                + "		( 1 - ( tblDctosOrdenesDetalle.porcentajeDscto /  100 ) ) ) -   "
                + "		(tblDctosOrdenesDetalle.cantidad          *                     "
                + "		(tblDctosOrdenesDetalle.vrVentaUnitario   -                     "
                + "     	(tblDctosOrdenesDetalle.vrImpoconsumo     /                     "
                + "           tblDctosOrdenesDetalle.cantidad))    *                            "
                + " 	       ( 1 - ( tblDctosOrdenesDetalle.vrDsctoPie /  100 ) )      *      "
                + "            ( 1 - ( tblDctosOrdenesDetalle.porcentajeDscto /  100 ) ) /      "
                + " 		( 1 + ( tblDctosOrdenesDetalle.porcentajeIva /  100 ) ))        "
                + "                                                           AS vrIvaVenta,	"
                + "           tblDctos.idDcto,						        "
                + "           tblDctos.fechaDcto,					        "
                + "           tblDctos.idCliente,					        "
                + "           tblDctos.vrBase,						        "
                + "           tblDctos.vrIva,						        "
                + "           tblDctos.vrImpoconsumo,					        "
                + "           tblDctos.nombreTercero,					        "
                + "           tblDctos.idDctoNitCC,					        "
                + "           tblDctos.fechaDctoNitCC,					        "
                + "           tblDctos.vrCostoMV, 			                        "
                + "           tblDctos.vrRteFuente,					        "
                + "           tblDctos.vrRteIva,						"
                + "           tblDctos.vrRteIca,						"
                + "           2 AS idTipoNegocio,					        "
                + "           0 AS vrPago, 					                "
                + "           ( SELECT TOP 1  tblLocalesCaja.prefijo                            "
                + "             FROM   tblLocalesCaja                                           "
                + "              INNER JOIN tblAgendaLogVisitas                                 "
                + "              ON tblLocalesCaja.ipLocal =                                    "
                + "                   tblAgendaLogVisitas.ipTx                                  "
                + "              INNER JOIN tblDctosOrdenes                                     "
                + "              INNER JOIN tblDctos tmpDCT                                     "
                + "              ON  tblDctosOrdenes.IDLOCAL     =                              "
                + "                              tmpDCT.IDLOCAL                                 "
                + "              AND tblDctosOrdenes.IDTIPOORDEN =                              "
                + "                            tmpDCT.IDTIPOORDEN                               "
                + "              AND tblDctosOrdenes.IDORDEN     =                              "
                + "                                tmpDCT.IDORDEN                               "
                + "              ON tblAgendaLogVisitas.idLog    =                              "
                + "                           tblDctosOrdenes.IDLOG                             "
                + "           WHERE tblDctos.IDLOCAL     =                                      "
                + "                              tmpDCT.IDLOCAL                                 "
                + "              AND tblDctos.IDTIPOORDEN =                                     "
                + "                            tmpDCT.IDTIPOORDEN                               "
                + "              AND tblDctos.IDORDEN     =                                     "
                + "                                tmpDCT.IDORDEN )                             "
                + "                                AS prefijo                                   "
                + "   FROM  tblDctosOrdenesDetalle					        "
                + "   INNER JOIN tblDctosOrdenes					        "
                + "   ON  tblDctosOrdenesDetalle.IDLOCAL     = 				        "
                + "                                       tblDctosOrdenes.IDLOCAL	        "
                + "   AND tblDctosOrdenesDetalle.IDTIPOORDEN = 				        "
                + "                                   tblDctosOrdenes.IDTIPOORDEN  	        "
                + "   AND tblDctosOrdenesDetalle.IDORDEN     = 				        "
                + "                                       tblDctosOrdenes.IDORDEN	        "
                + "   INNER JOIN tblDctos						        "
                + "   ON  tblDctosOrdenes.IDLOCAL     = tblDctos.IDLOCAL		        "
                + "   AND tblDctosOrdenes.IDTIPOORDEN = tblDctos.IDTIPOORDEN		        "
                + "   AND tblDctosOrdenes.IDORDEN     = tblDctos.IDORDEN	                "
                + " WHERE tblDctos.idLocal          =     				        "
                + getIdLocal() + "                                                              "
                + " AND   tblDctos.vrBase  != 0   	    		    	                "
                + " AND tblDctos.fechaDcto BETWEEN                                             '"
                + getFechaInicialSqlServer() + "' AND                                          '"
                + getFechaFinalSqlServer() + "'                                                 "
                + " AND ( tblDctos.idTipoOrden  =  			       	                "
                + getIdTipoOrdenINI() + "                                                       "
                + " OR tblDctos.idTipoOrden =                                                   "
                + getIdTipoOrdenFIN() + " )                                                     "
                + " AND tblDctos.indicador BETWEEN                                              "
                + getIndicadorInicial() + " AND                                                 "
                + getIndicadorFinal() + " ) AS tmpDET			                        "
                + "   INNER JOIN ( SELECT tblTerceros.idCliente			                "
                + "        ,MAX(tblTerceros.digitoVerificacion) AS digitoVerificacion           "
                + "       ,MAX(tblTerceros.nombreTercero) AS nombreTercero 		        "
                + "       ,MAX(tblTerceros.direccionTercero) AS direccionTercero     	        "
                + "       ,MAX(tblTerceros.ciudadTercero) AS ciudadTercero  		        "
                + "       ,MAX(tblTerceros.telefonoFijo) AS telefonoFijo  		        "
                + "       ,MAX(tblTerceros.telefonoCelular) AS telefonoCelular 	                "
                + "       ,MAX(tblTerceros.telefonoFax) AS telefonoFax 			        "
                + "       ,MAX(tblTerceros.email) AS email 				        "
                + "       ,MAX(tblTerceros.contactoTercero) AS contactoTercero 	                "
                + "       ,MAX(tblTerceros.idPersona) AS idPersona 			        "
                + "       ,MAX(tblTerceros.idAutoRetenedor) AS idAutoRetenedor 		        "
                + "       ,MAX(tblTerceros.idRegimen) AS idRegimen 			        "
                + "       ,MAX(tblTerceros.idDptoCiudad) AS idDptoCiudad 	                "
                + "       ,(SELECT tblCiudades.nombreDpto     			                "
                + "         FROM tblCiudades		     			                "
                + "         WHERE tblCiudades.idCiudad =      			                "
                + "                      tblTerceros.idDptoCiudad)  			        "
                + "                            AS departamentoTercero   		        "
                + "       ,MAX(tblTerceros.tipoIdTercero) AS tipoIdTercero 	                "
                + "  FROM tblTerceros                                                           "
                + " WHERE tblTerceros.idTipoTercero   =                                         "
                + xIdTipoTercero + "                                                            "
                + "  GROUP BY tblTerceros.idCliente,                                            "
                + "           tblTerceros.idDptoCiudad) AS tmpTER	                        "
                + "  ON tmpTER.idCliente = tmpDET.idCliente				        "
                + "   GROUP BY tmpDET.IDLOCAL,						        "
                + "            tmpDET.IDTIPOORDEN,					        "
                + "            tmpDET.IDORDEN,						        "
                + "            tmpDET.porcentajeIva,       				        "
                + "            tmpDET.idDcto,						        "
                + "            tmpDET.fechaDcto,					        "
                + "            tmpDET.idCliente,					        "
                + "            tmpDET.vrBase,						        "
                + "            tmpDET.vrIva,						        "
                + "            tmpDET.vrImpoconsumo,					        "
                + "            tmpDET.vrCostoMv,  					        "
                + "            tmpDET.idDctoNitCC,					        "
                + "            tmpDET.fechaDctoNitCC,					        "
                + "            tmpDET.vrRteFuente,					        "
                + "            tmpDET.vrRteIva,						        "
                + "            tmpDET.vrRteIca,						        "
                + "            tmpDET.idTipoNegocio, 		 		 	        "
                + "            tmpDET.vrPago        				                "
                + "   ORDER BY tmpDET.IDLOCAL,						        "
                + "            tmpDET.IDTIPOORDEN,					        "
                + "            tmpDET.idDcto						        ";

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

    // ingresaTmpDctosContableVenta
    public boolean ingresaTmpDctosContableVenta(int xIdTipoTercero) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                "  SELECT tmpDET.IDLOCAL,                                               	                                         "
                + "                            tmpDET.IDTIPOORDEN,						                                             "
                + "                            tmpDET.IDORDEN,						                                                 "
                + "                            tmpDET.porcentajeIva,       				                                             "
                + "                            tmpDET.idDcto,						                                                 "
                + "                            0 AS idRecibo,						                                                 "
                + "                            tmpDET.fechaDcto,						                                             "
                + "                            tmpDET.idCliente,						                                             "
                + "                            tmpDET.vrBase,						                                                 "
                + "                            tmpDET.vrIva,						                                                 "
                + "                            tmpDET.vrImpoconsumo,					                                             "
                + "                            tmpDET.vrCostoMv,						                                             "
                + "                            tmpDET.idDctoNitCC,						                                             "
                + "                            tmpDET.fechaDctoNitCC,				                                                 "
                + "                            tmpDET.vrRteFuente,						                                             "
                + "                            tmpDET.vrRteIva,						                                                 "
                + "                            tmpDET.vrRteIca,						                                                 "
                + "                            tmpDET.idTipoNegocio,       				                                             "
                + "                            tmpDET.vrPago,       					                                             "
                + "                            SUM(tmpDET.vrVentaSinIva) 					                                         "
                + "                                       AS vrVentaSinIva,				                                             "
                + "                            SUM(tmpDET.vrVentaSinDscto) 				                                             "
                + "                                     AS vrVentaSinDscto,				                                             "
                + "                            SUM(tmpDET.vrIvaVenta) 					                                             "
                + "                                          AS vrVentaIva,				                                             "
                + "                            MAX(tmpTER.digitoVerificacion) AS digitoVerificacion	                                 "
                + "                             ,MAX(tmpTER.nombreTercero) AS nombreTercero		                                     "
                + "                             ,MAX(tmpTER.direccionTercero) AS direccionTercero 		                             "
                + "                             ,MAX(tmpTER.ciudadTercero) AS ciudadTercero		                                     "
                + "                             ,MAX(tmpTER.telefonoFijo) AS telefonoFijo 			                                 "
                + "                             ,MAX(tmpTER.telefonoCelular) AS telefonoCelular 		                             "
                + "                             ,MAX(tmpTER.telefonoFax) AS telefonoFax 			                                 "
                + "                             ,MAX(tmpTER.email) AS email 				                                         "
                + "                             ,MAX(tmpTER.contactoTercero) AS contactoTercero 		                             "
                + "                             ,MAX(tmpTER.idPersona) AS idPersona   			                                     "
                + "                             ,MAX(tmpTER.idAutoRetenedor) AS idAutoRetenedor 		                             "
                + "                             ,MAX(tmpTER.idRegimen) AS idRegimen	     		                                     "
                + "                             ,MAX(tmpTER.idDptoCiudad) AS idDptoCiudad			                                 "
                + "                             ,MAX(tmpTER.departamentoTercero) AS departamentoTercero	                             "
                + "                             ,MAX(tmpTER.tipoIdTercero) AS tipoIdTercero		                                     "
                + "                             ,MAX(tmpDET.prefijo) AS prefijo                                                      "
                + " 	            INTO tmpDctosContable                                                                            "
                + "                     FROM (								                                                         "
                + "                     SELECT tblDctosOrdenesDetalle.IDLOCAL,				                                         "
                + "                            tblDctosOrdenesDetalle.IDTIPOORDEN,				                                     "
                + "                            tblDctosOrdenesDetalle.IDORDEN,				                                         "
                + "                            tblDctosOrdenesDetalle.porcentajeIva,	                                             "
                + " 							(tblDctosOrdenesDetalle.cantidad          *                                          "
                + " 									(tblDctosOrdenesDetalle.vrVentaUnitario   -                                  "
                + " 									tblDctosOrdenesDetalle.vrImpoconsumo)    *                                   "
                + " 								( 1 - ( tblDctosOrdenesDetalle.vrDsctoPie /  100 ) )      *                      "
                + " 								( 1 - ( tblDctosOrdenesDetalle.porcentajeDscto /  100 ) ) /                      "
                + " 								( 1 + ( tblDctosOrdenesDetalle.porcentajeIva /  100 ) ))                         "
                + " 																			AS vrVentaSinIva,                    "
                + " 																			                                     "
                + " 							(tblDctosOrdenesDetalle.cantidad          *                                          "
                + " 									(tblDctosOrdenesDetalle.vrVentaUnitario   -                                  "
                + " 									(tblDctosOrdenesDetalle.vrImpoconsumo))    *                                 "
                + " 								( 1 - ( tblDctosOrdenesDetalle.vrDsctoPie /  100 ) )      *                      "
                + " 								( 1 - ( tblDctosOrdenesDetalle.porcentajeDscto /  100 ) ) /                      "
                + " 								( 1 + ( tblDctosOrdenesDetalle.porcentajeIva /  100 ) )) AS vrVentaSinDscto,	"
                + " 																				                                 "
                + " 								(tblDctosOrdenesDetalle.cantidad          *                                      "
                + " 									(tblDctosOrdenesDetalle.vrVentaUnitario   -                                  "
                + " 									(tblDctosOrdenesDetalle.vrImpoconsumo))    *                                 "
                + " 								( 1 - ( tblDctosOrdenesDetalle.vrDsctoPie /  100 ) )      *                      "
                + " 								( 1 - ( tblDctosOrdenesDetalle.porcentajeDscto /  100 ) ) ) -                    "
                + " 								(tblDctosOrdenesDetalle.cantidad          *                                      "
                + " 									(tblDctosOrdenesDetalle.vrVentaUnitario   -                                  "
                + " 									(tblDctosOrdenesDetalle.vrImpoconsumo))    *                                 "
                + " 								( 1 - ( tblDctosOrdenesDetalle.vrDsctoPie /  100 ) )      *                      "
                + " 								( 1 - ( tblDctosOrdenesDetalle.porcentajeDscto /  100 ) ) /                      "
                + " 								( 1 + ( tblDctosOrdenesDetalle.porcentajeIva /  100 ) ))                         "
                + "                                                            AS vrIvaVenta,										"
                + "                             tblDctos.idDcto,						                                             "
                + "                             tblDctos.fechaDcto,					                                                 "
                + "                             tblDctos.idCliente,					                                                 "
                + "                             tblDctos.vrBase,						                                             "
                + "                             tblDctos.vrIva,						                                                 "
                + "                             tblDctos.vrImpoconsumo,					                                             "
                + "                             tblDctos.nombreTercero,					                                             "
                + "                             tblDctos.idDctoNitCC,					                                             "
                + "                             tblDctos.fechaDctoNitCC,					                                         "
                + "                             tblDctos.vrCostoMV, 			                                                     "
                + "                             tblDctos.vrRteFuente,					                                             "
                + "                             tblDctos.vrRteIva,						                                             "
                + "                             tblDctos.vrRteIca,						                                             "
                + "                             2 AS idTipoNegocio,					                                                 "
                + "                             0 AS vrPago, 					                                                     "
                + "                             ( SELECT TOP 1  tblLocalesCaja.prefijo                                               "
                + "                               FROM   tblLocalesCaja                                                              "
                + "                                INNER JOIN tblAgendaLogVisitas                                                    "
                + "                                ON tblLocalesCaja.ipLocal =                                                       "
                + "                                     tblAgendaLogVisitas.ipTx                                                     "
                + "                                INNER JOIN tblDctosOrdenes                                                        "
                + "                                INNER JOIN tblDctos tmpDCT                                                        "
                + "                                ON  tblDctosOrdenes.IDLOCAL     =                                                 "
                + "                                                tmpDCT.IDLOCAL                                                    "
                + "                                AND tblDctosOrdenes.IDTIPOORDEN =                                                 "
                + "                                              tmpDCT.IDTIPOORDEN                                                  "
                + "                                AND tblDctosOrdenes.IDORDEN     =                                                 "
                + "                                                  tmpDCT.IDORDEN                                                  "
                + "                                ON tblAgendaLogVisitas.idLog    =                                                 "
                + "                                             tblDctosOrdenes.IDLOG                                                "
                + "                             WHERE tblDctos.IDLOCAL     =                                                         "
                + "                                                tmpDCT.IDLOCAL                                                    "
                + "                                AND tblDctos.IDTIPOORDEN =                                                        "
                + "                                              tmpDCT.IDTIPOORDEN                                                  "
                + "                                AND tblDctos.IDORDEN     =                                                        "
                + "                                                  tmpDCT.IDORDEN )                                                "
                + "                                                  AS prefijo                                                      "
                + "                     FROM  tblDctosOrdenesDetalle					                                             "
                + "                     INNER JOIN tblDctosOrdenes						                                             "
                + "                     ON  tblDctosOrdenesDetalle.IDLOCAL     = 				                                     "
                + "                                                         tblDctosOrdenes.IDLOCAL	                                 "
                + "                     AND tblDctosOrdenesDetalle.IDTIPOORDEN = 				                                     "
                + "                                                     tblDctosOrdenes.IDTIPOORDEN  	                             "
                + "                     AND tblDctosOrdenesDetalle.IDORDEN     = 				                                     "
                + "                                                         tblDctosOrdenes.IDORDEN	                                 "
                + "                     INNER JOIN tblDctos						                                                     "
                + "                     ON  tblDctosOrdenes.IDLOCAL     = tblDctos.IDLOCAL			                                 "
                + "                     AND tblDctosOrdenes.IDTIPOORDEN = tblDctos.IDTIPOORDEN		                                 "
                + "                     AND tblDctosOrdenes.IDORDEN     = tblDctos.IDORDEN			                                 "
                + "                   WHERE tblDctos.idLocal          =                                                              "
                + getIdLocal() + "                                                                           "
                + "                   AND   tblDctos.vrBase  != 0   	    			                                             "
                + "                   AND tblDctos.fechaDcto BETWEEN                                                               '"
                + getFechaInicialSqlServer() + "' AND                                                             '"
                + getFechaFinalSqlServer() + "'                                                                    "
                + "                   AND ( tblDctos.idTipoOrden  =                                                                  "
                + getIdTipoOrdenINI() + "                                                                          "
                + "                   OR tblDctos.idTipoOrden =                                                                      "
                + getIdTipoOrdenFIN() + "                                                                          "
                + " 	 		 )                                                                                                   "
                + "                   AND tblDctos.indicador BETWEEN                                                                 "
                + getIndicadorInicial() + "  AND                                                                    "
                + getIndicadorFinal() + " ) AS tmpDET			                                                     "
                + "                     INNER JOIN ( SELECT tblTerceros.idCliente			                                         "
                + "                          ,MAX(tblTerceros.digitoVerificacion) AS digitoVerificacion                              "
                + "                         ,MAX(tblTerceros.nombreTercero) AS nombreTercero 		                                 "
                + "                         ,MAX(tblTerceros.direccionTercero) AS direccionTercero 	                                 "
                + "                         ,MAX(tblTerceros.ciudadTercero) AS ciudadTercero  		                                 "
                + "                         ,MAX(tblTerceros.telefonoFijo) AS telefonoFijo  		                                 "
                + "                         ,MAX(tblTerceros.telefonoCelular) AS telefonoCelular 		                             "
                + "                         ,MAX(tblTerceros.telefonoFax) AS telefonoFax 			                                 "
                + "                         ,MAX(tblTerceros.email) AS email 				                                         "
                + "                         ,MAX(tblTerceros.contactoTercero) AS contactoTercero 		                             "
                + "                         ,MAX(tblTerceros.idPersona) AS idPersona 			                                     "
                + "                         ,MAX(tblTerceros.idAutoRetenedor) AS idAutoRetenedor 		                             "
                + "                         ,MAX(tblTerceros.idRegimen) AS idRegimen 			                                     "
                + "                         ,MAX(tblTerceros.idDptoCiudad) AS idDptoCiudad 	                                         "
                + "                         ,(SELECT tblCiudades.nombreDpto     			                                         "
                + "                           FROM tblCiudades		     				                                             "
                + "                           WHERE tblCiudades.idCiudad =      			                                         "
                + "                                        tblTerceros.idDptoCiudad)  			                                     "
                + "                                              AS departamentoTercero   			                                 "
                + "                         ,MAX(tblTerceros.tipoIdTercero) AS tipoIdTercero 	                                     "
                + "                    FROM tblTerceros                                                                              "
                + "                   WHERE tblTerceros.idTipoTercero   =                                                            "
                + xIdTipoTercero + "                                                                    "
                + "                    GROUP BY tblTerceros.idCliente,                                                               "
                + "                             tblTerceros.idDptoCiudad) AS tmpTER	                                                 "
                + "                    ON tmpTER.idCliente = tmpDET.idCliente				                                         "
                + "                     GROUP BY tmpDET.IDLOCAL,						                                             "
                + "                              tmpDET.IDTIPOORDEN,					                                             "
                + "                              tmpDET.IDORDEN,						                                             "
                + "                              tmpDET.porcentajeIva,       				                                         "
                + "                              tmpDET.idDcto,						                                                 "
                + "                              tmpDET.fechaDcto,						                                             "
                + "                              tmpDET.idCliente,						                                             "
                + "                              tmpDET.vrBase,						                                                 "
                + "                              tmpDET.vrIva,						                                                 "
                + "                              tmpDET.vrImpoconsumo,					                                             "
                + "                              tmpDET.vrCostoMv,  					                                             "
                + "                              tmpDET.idDctoNitCC,					                                             "
                + "                              tmpDET.fechaDctoNitCC,					                                             "
                + "                              tmpDET.vrRteFuente,					                                             "
                + "                              tmpDET.vrRteIva,						                                             "
                + "                              tmpDET.vrRteIca,						                                             "
                + "                              tmpDET.idTipoNegocio, 		 		 	                                             "
                + "                              tmpDET.vrPago        				                                                 "
                + "                     ORDER BY tmpDET.IDLOCAL,						                                             "
                + "                              tmpDET.IDTIPOORDEN,					                                             "
                + "                              tmpDET.idDcto	";



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

    // ingresaTmpDctosContableDetalle
    public boolean ingresaTmpDctosContableDetalle(int xIdComprobante) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                "SELECT tmpDET.*                                    "
                + "        INTO tmpDctosContableDetalle             "
                + "FROM  (                                          "
                + "SELECT     tmpDctosContable.idLocal,		    "
                + "           tblTipoOrdenSubcuenta.idTipoOrden,    "
                + "           tmpDctosContable.idDcto,		    "
                + "           tmpDctosContable.idOrden,             "
                + "           tmpDctosContable.idRecibo,            "
                + "           tmpDctosContable.idDctoNitCC,	    "
                + "           tblTipoOrdenSubcuenta.idSubcuenta,    "
                + "           tblTipoOrdenSubcuenta.idAsiento, 	    "
                + "           tblTipoOrdenSubcuenta.idComprobante,  "
                + "           tblTipoOrdenSubcuenta.porcentajeIva,  "
                + "           tblTipoOrdenSubcuenta.nombreSubcuenta,"
                + "           tmpDctosContable.fechaDcto,	    "
                + "           tmpDctosContable.idCliente,	    "
                + "           tmpDctosContable.vrBase,		    "
                + "           tmpDctosContable.vrIva,		    "
                + "           tmpDctosContable.vrImpoconsumo,	    "
                + "           tmpDctosContable.vrRteFuente,	    "
                + "           tmpDctosContable.vrRteIva, 	    "
                + "           tmpDctosContable.vrRteIca, 	    "
                + "           tmpDctosContable.vrCostoMV,	    "
                + "           tmpDctosContable.vrVentaSinDscto,	    "
                + "           tmpDctosContable.vrVentaIva,	    "
                + "           tmpDctosContable.idTipoNegocio,	    "
                + "           tmpDctosContable.vrPago,		    "
                + "           tmpDctosContable.prefijo,		    "
                + "         (tmpDctosContable.vrBase        +       "
                + "          tmpDctosContable.vrIva         +       "
                + "          tmpDctosContable.vrImpoconsumo -       "
                + "          tmpDctosContable.vrRteFuente   -       "
                + "          tmpDctosContable.vrRteIca      -       "
                + "          tmpDctosContable.vrRteIva)             "
                + "                                   AS vrFactura, "
                + "      CONVERT(decimal(14,0), 0) AS vrMovimiento  "
                + "FROM       tblTipoOrdenSubcuenta		    "
                + "INNER JOIN tmpDctosContable			    "
                + "ON tblTipoOrdenSubcuenta.idTipoOrden        =    "
                + "                    tmpDctosContable.IDTIPOORDEN "
                + "WHERE  (tblTipoOrdenSubcuenta.idComprobante =    "
                + xIdComprobante + " )                              "
                + "AND tmpDctosContable.porcentajeIva          =    "
                + "             tblTipoOrdenSubcuenta.porcentajeIva "
                + " AND tblTipoOrdenSubcuenta.nombreSubcuenta 	    "
                + "                       IN ('vrBase','vrIva')	    "
                + "UNION                                            "
                + "SELECT     tmpDctosContable.idLocal,		    "
                + "           tblTipoOrdenSubcuenta.idTipoOrden,    "
                + "           tmpDctosContable.idDcto,		    "
                + "           tmpDctosContable.idOrden,             "
                + "           tmpDctosContable.idRecibo,            "
                + "           tmpDctosContable.idDctoNitCC,	    "
                + "           tblTipoOrdenSubcuenta.idSubcuenta,    "
                + "           tblTipoOrdenSubcuenta.idAsiento, 	    "
                + "     MAX(tblTipoOrdenSubcuenta.idComprobante),   "
                + "       MAX(tblTipoOrdenSubcuenta.porcentajeIva), "
                + "     MAX(tblTipoOrdenSubcuenta.nombreSubcuenta), "
                + "        MAX(tmpDctosContable.fechaDcto),	    "
                + "           (tmpDctosContable.idCliente),	    "
                + "        MAX(tmpDctosContable.vrBase),            "
                + "        MAX(tmpDctosContable.vrIva),		    "
                + "        MAX(tmpDctosContable.vrImpoconsumo),	    "
                + "        MAX(tmpDctosContable.vrRteFuente),	    "
                + "        MAX(tmpDctosContable.vrRteIva), 	    "
                + "        MAX(tmpDctosContable.vrRteIca), 	    "
                + "        MAX(tmpDctosContable.vrCostoMV),	    "
                + "        MAX(tmpDctosContable.vrVentaSinDscto),   "
                + "        MAX(tmpDctosContable.vrVentaIva),	    "
                + "        MAX(tmpDctosContable.idTipoNegocio),	    "
                + "        MAX(tmpDctosContable.vrPago),	    "
                + "        MAX(tmpDctosContable.prefijo),	    "
                + "        MAX(tmpDctosContable.vrBase     +        "
                + "         tmpDctosContable.vrIva         +        "
                + "         tmpDctosContable.vrImpoconsumo -        "
                + "         tmpDctosContable.vrRteFuente   -        "
                + "         tmpDctosContable.vrRteIca      -        "
                + "         tmpDctosContable.vrRteIva)              "
                + "                                  AS vrFactura,  "
                + "       CONVERT(decimal(14,0), 0) AS vrMovimiento "
                + " FROM       tblTipoOrdenSubcuenta		    "
                + " INNER JOIN tmpDctosContable			    "
                + " ON tblTipoOrdenSubcuenta.idTipoOrden        =   "
                + "                   tmpDctosContable.IDTIPOORDEN  "
                + "WHERE  (tblTipoOrdenSubcuenta.idComprobante  =   "
                + xIdComprobante + " )                              "
                + " AND tmpDctosContable.porcentajeIva          =   "
                + "           tblTipoOrdenSubcuenta.porcentajeIva   "
                + " AND tblTipoOrdenSubcuenta.nombreSubcuenta 	    "
                + "                     NOT IN ('vrBase','vrIva')   "
                + " GROUP BY tblTipoOrdenSubcuenta.idTipoOrden,     "
                + "            tblTipoOrdenSubcuenta.idSubcuenta,   "
                + "            tmpDctosContable.idLocal,            "
                + "           tmpDctosContable.idRecibo,            "
                + "            tblTipoOrdenSubcuenta.idAsiento,     "
                + "            tmpDctosContable.idDcto,		    "
                + "           tmpDctosContable.idOrden,             "
                + "            tmpDctosContable.idDctoNitCC,        "
                + "            tmpDctosContable.idCliente      )    "
                + "                                   AS tmpDET     ";

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

    // borraTmpDctosContablePago
    public boolean borraTmpDctosContablePago() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                " DELETE FROM tmpDctosContableDetalle                           "
                + " WHERE tmpDctosContableDetalle.nombreSubcuenta IN ('vrPago') "
                + " AND   tmpDctosContableDetalle.idTipoNegocio   = 2           "
                + " AND ( tmpDctosContableDetalle.idTipoOrden     =             "
                + getIdTipoOrdenINI() + "                                       "
                + " OR    tmpDctosContableDetalle.idTipoOrden     =             "
                + getIdTipoOrdenFIN() + " ) ";

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

    // borraTmpDctosContableCxC
    public boolean borraTmpDctosContableCxC() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                " DELETE FROM tmpDctosContableDetalle                                  "
                + " WHERE tmpDctosContableDetalle.nombreSubcuenta IN ('vrCxC','vrCxP') "
                + " AND   tmpDctosContableDetalle.idTipoNegocio   = 1                  "
                + " AND ( tmpDctosContableDetalle.idTipoOrden     =                    "
                + getIdTipoOrdenINI() + "                                              "
                + " OR    tmpDctosContableDetalle.idTipoOrden     =                    "
                + getIdTipoOrdenFIN() + " ) ";

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

    // borraTmpDctosContableImpoconsumo
    public boolean borraTmpDctosContableImpoconsumo() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                " DELETE FROM tmpDctosContableDetalle                                  "
                + " WHERE tmpDctosContableDetalle.nombreSubcuenta IN ('vrImpoconsumo') "
                + " AND   tmpDctosContableDetalle.vrImpoConsumo   = 0                  "
                + " AND ( tmpDctosContableDetalle.idTipoOrden     =                    "
                + getIdTipoOrdenINI() + "                                              "
                + " OR    tmpDctosContableDetalle.idTipoOrden     =                    "
                + getIdTipoOrdenFIN() + " ) ";

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

    // borraTmpDctosContableRteFuente
    public boolean borraTmpDctosContableRteFuente() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                " DELETE FROM tmpDctosContableDetalle                                "
                + " WHERE tmpDctosContableDetalle.nombreSubcuenta IN ('vrRteFuente') "
                + " AND   tmpDctosContableDetalle.vrRteFuente     = 0                "
                + " AND ( tmpDctosContableDetalle.idTipoOrden     =                  "
                + getIdTipoOrdenINI() + "                                            "
                + " OR    tmpDctosContableDetalle.idTipoOrden     =                  "
                + getIdTipoOrdenFIN() + " ) ";

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

    // borraTmpDctosContableDsctoFcro
    public boolean borraTmpDctosContableDsctoFcro() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                " DELETE FROM tmpDctosContableDetalle                "
                + " WHERE tmpDctosContableDetalle.nombreSubcuenta IN "
                + "                                  ('vrDsctoFcro') "
                + " AND   tmpDctosContableDetalle.vrDescuento   = 0  "
                + " AND ( tmpDctosContableDetalle.idTipoOrden   =    "
                + getIdTipoOrdenINI() + "                            "
                + " OR    tmpDctosContableDetalle.idTipoOrden   =    "
                + getIdTipoOrdenFIN() + " ) ";

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

    // borraTmpDctosContableVrCero
    public boolean borraTmpDctosContableVrCero() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                " DELETE FROM tmpDctosContableDetalle              "
                + " WHERE tmpDctosContableDetalle.vrMovimiento = 0 ";

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

    // borraTmpDctosContableIva
    public boolean borraTmpDctosContableIva() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                " DELETE FROM tmpDctosContableDetalle             "
                + " WHERE tmpDctosContableDetalle.nombreSubcuenta "
                + "                                  IN ('vrIva') "
                + " AND   tmpDctosContableDetalle.vrIva       = 0 "
                + " AND ( tmpDctosContableDetalle.idTipoOrden =   "
                + getIdTipoOrdenINI() + "                         "
                + " OR    tmpDctosContableDetalle.idTipoOrden =   "
                + getIdTipoOrdenFIN() + " ) ";

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

    // borraTmpDctosContableAjusteNegativo
    public boolean borraTmpDctosContableAjusteNegativo() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                "DELETE FROM tmpDctosContableDetalle               "
                + "WHERE tmpDctosContableDetalle.idComprobante = 6 "
                + "AND   tmpDctosContableDetalle.idTipoOrden   = 6 "
                + "AND tmpDctosContableDetalle.vrFactura       < 0 "
                + "AND tmpDctosContableDetalle.porcentajeIva   > 0 ";

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

    // borraTmpDctosContableAjustePositivo
    public boolean borraTmpDctosContableAjustePositivo() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                "DELETE FROM tmpDctosContableDetalle               "
                + "WHERE tmpDctosContableDetalle.idComprobante = 6 "
                + "AND   tmpDctosContableDetalle.idTipoOrden   = 6 "                
                + "AND tmpDctosContableDetalle.vrFactura       > 0 "
                + "AND tmpDctosContableDetalle.porcentajeIva   < 0 " ;

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

    // actualizaTmpDctosContableVrMovimiento
    public boolean actualizaTmpDctosContableVrMovimiento() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                "UPDATE tmpDctosContableDetalle                           "
                + "   SET tmpDctosContableDetalle.vrMovimiento =          "
                + "                 tmpDctosContableDetalle.vrFactura     "
                + " WHERE tmpDctosContableDetalle.nombreSubcuenta         "
                + "                         IN ('vrPago','vrCxC','vrCxP') "
                + " AND ( tmpDctosContableDetalle.idTipoOrden     =       "
                + getIdTipoOrdenINI() + "                                 "
                + " OR    tmpDctosContableDetalle.idTipoOrden     =       "
                + getIdTipoOrdenFIN() + " ) ";

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

    // actualizaTmpDctosContableInventarioVrMovimiento
    public boolean actualizaTmpDctosContableInventarioVrMovimiento() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                "UPDATE tmpDctosContableDetalle                       "
                + "   SET tmpDctosContableDetalle.vrMovimiento =      "
                + "                 tmpDctosContableDetalle.vrFactura "
                + " WHERE tmpDctosContableDetalle.nombreSubcuenta     "
                + "               IN ('vrCosto','vrInventario')       ";

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

    // actualizaTmpDctosContableEgresoIngresoVrMovimiento
    public boolean actualizaTmpDctosContableEgresoIngresoVrMovimiento() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                "UPDATE tmpDctosContableDetalle                       "
                + "   SET tmpDctosContableDetalle.vrMovimiento =      "
                + "                 tmpDctosContableDetalle.vrFactura "
                + " WHERE tmpDctosContableDetalle.nombreSubcuenta     "
                + "               IN ('vrBase','vrPago')              ";

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

    // actualizaTmpDctosContableVrImpoconsumo
    public boolean actualizaTmpDctosContableVrImpoconsumo() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                "UPDATE tmpDctosContableDetalle                         "
                + "   SET tmpDctosContableDetalle.vrMovimiento =          "
                + "                 tmpDctosContableDetalle.vrImpoconsumo "
                + " WHERE tmpDctosContableDetalle.nombreSubcuenta         "
                + "                                  IN ('vrImpoconsumo')	"
                + " AND ( tmpDctosContableDetalle.idTipoOrden     =       "
                + getIdTipoOrdenINI() + "                                 "
                + " OR    tmpDctosContableDetalle.idTipoOrden     =       "
                + getIdTipoOrdenFIN() + " ) ";

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

    // actualizaTmpVrRteFuente
    public boolean actualizaTmpVrRteFuente() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                "UPDATE tmpDctosContableDetalle                         "
                + "   SET tmpDctosContableDetalle.vrMovimiento =          "
                + "                 tmpDctosContableDetalle.vrRteFuente   "
                + " WHERE tmpDctosContableDetalle.nombreSubcuenta         "
                + "                                  IN ('vrRteFuente')	"
                + " AND ( tmpDctosContableDetalle.idTipoOrden     =       "
                + getIdTipoOrdenINI() + "                                 "
                + " OR    tmpDctosContableDetalle.idTipoOrden     =       "
                + getIdTipoOrdenFIN() + " ) ";

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

    // actualizaTmpVrRteIva
    public boolean actualizaTmpVrRteIva() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                "UPDATE tmpDctosContableDetalle                      "
                + "   SET tmpDctosContableDetalle.vrMovimiento =     "
                + "               tmpDctosContableDetalle.vrRteIva   "
                + " WHERE tmpDctosContableDetalle.nombreSubcuenta    "
                + "                                  IN ('vrRteIva') "
                + " AND ( tmpDctosContableDetalle.idTipoOrden     =  "
                + getIdTipoOrdenINI() + "                            "
                + " OR    tmpDctosContableDetalle.idTipoOrden     =  "
                + getIdTipoOrdenFIN() + " ) ";

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

    // actualizaTmpVrRteIca
    public boolean actualizaTmpVrRteIca() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                "UPDATE tmpDctosContableDetalle                      "
                + "   SET tmpDctosContableDetalle.vrMovimiento =     "
                + "               tmpDctosContableDetalle.vrRteIca   "
                + " WHERE tmpDctosContableDetalle.nombreSubcuenta    "
                + "                                  IN ('vrRteIca') "
                + " AND ( tmpDctosContableDetalle.idTipoOrden     =  "
                + getIdTipoOrdenINI() + "                            "
                + " OR    tmpDctosContableDetalle.idTipoOrden     =  "
                + getIdTipoOrdenFIN() + " ) ";

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

    // actualizaTmpDctosContableInventario
    public boolean actualizaTmpDctosContableInventario() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                "UPDATE tmpDctosContableDetalle                                      "
                + "   SET tmpDctosContableDetalle.vrMovimiento    =                    "
                + "                        tmpDctosContableDetalle.vrCostoMV           "
                + " WHERE  tmpDctosContableDetalle.nombreSubcuenta IN ('vrCostoMV')    "
                + " AND ( tmpDctosContableDetalle.idTipoOrden     =                    "
                + getIdTipoOrdenINI() + "                                              "
                + " OR    tmpDctosContableDetalle.idTipoOrden     =                    "
                + getIdTipoOrdenFIN() + " )                                            "
                + " OR   tmpDctosContableDetalle.nombreSubcuenta IN ('vrInventario')   "
                + " AND ( tmpDctosContableDetalle.idTipoOrden     =                    "
                + getIdTipoOrdenINI() + "                                              "
                + " OR    tmpDctosContableDetalle.idTipoOrden     =                    "
                + getIdTipoOrdenFIN() + " )                                            ";

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

    // actualizaTmpDctosContableInventarioCompra
    public boolean actualizaTmpDctosContableInventarioCompra() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                "UPDATE tmpDctosContableDetalle                                        "
                + "   SET tmpDctosContableDetalle.vrMovimiento    =                    "
                + "                        tmpDctosContableDetalle.vrBase              "
                + " WHERE  tmpDctosContableDetalle.nombreSubcuenta IN ('vrInventario') "
                + " AND ( tmpDctosContableDetalle.idTipoOrden     =                    "
                + getIdTipoOrdenINI() + "                                              "
                + " OR    tmpDctosContableDetalle.idTipoOrden     =                    "
                + getIdTipoOrdenFIN() + " )                                            ";

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

    // actualizaTmpDctosContableIva
    public boolean actualizaTmpDctosContableIva() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                "UPDATE tmpDctosContableDetalle                                "
                + "   SET tmpDctosContableDetalle.vrMovimiento =                 "
                + "                        tmpDctosContableDetalle.vrVentaIva    "
                + " WHERE   tmpDctosContableDetalle.nombreSubcuenta IN ('vrIva') "
                + " AND ( tmpDctosContableDetalle.idTipoOrden     =              "
                + getIdTipoOrdenINI() + "                                        "
                + " OR    tmpDctosContableDetalle.idTipoOrden     =              "
                + getIdTipoOrdenFIN() + " ) ";

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

    // actualizaTmpDctosContableBase
    public boolean actualizaTmpDctosContableBase() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                "UPDATE tmpDctosContableDetalle                                     "
                + "   SET tmpDctosContableDetalle.vrMovimiento =                    "
                + "                        tmpDctosContableDetalle.vrVentaSinDscto  "
                + " WHERE   tmpDctosContableDetalle.nombreSubcuenta IN ('vrBase')   "
                + " AND ( tmpDctosContableDetalle.idTipoOrden     =                 "
                + getIdTipoOrdenINI() + "                                           "
                + " OR    tmpDctosContableDetalle.idTipoOrden     =                 "
                + getIdTipoOrdenFIN() + " ) ";

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

    // actualizaTmpDctosNaturalezaCredito
    public boolean actualizaTmpDctosNaturalezaCredito() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                " UPDATE tmpDctosContableDetalle                       "
                + "   SET tmpDctosContableDetalle.idAsiento    = 2     "
                + "      ,tmpDctosContableDetalle.vrMovimiento =       "
                + "        tmpDctosContableDetalle.vrMovimiento * (-1) "
                + " WHERE tmpDctosContableDetalle.vrMovimiento < 0     "
                + "AND    tmpDctosContableDetalle.idAsiento    = 1     ";

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

    // actualizaTmpDctosNaturalezaDebito
    public boolean actualizaTmpDctosNaturalezaDebito() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                " UPDATE tmpDctosContableDetalle                       "
                + "   SET tmpDctosContableDetalle.idAsiento    = 1     "
                + "      ,tmpDctosContableDetalle.vrMovimiento =       "
                + "        tmpDctosContableDetalle.vrMovimiento * (-1) "
                + " WHERE tmpDctosContableDetalle.vrMovimiento < 0     "
                + "AND    tmpDctosContableDetalle.idAsiento    = 2     ";

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

    // actualizaTmpDctosNaturaleza
    public boolean actualizaTmpDctosNaturaleza() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                " UPDATE tmpDctosContableDetalle                       "
                + "   SET tmpDctosContableDetalle.vrMovimiento =       "
                + "        tmpDctosContableDetalle.vrMovimiento * (-1) "
                + " WHERE tmpDctosContableDetalle.vrMovimiento < 0     ";

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

    // actualizaTmpDescuento
    public boolean actualizaTmpDescuento() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                "UPDATE tmpDctosContableDetalle                                      "
                + "   SET tmpDctosContableDetalle.vrMovimiento =                     "
                + "                        tmpDctosContableDetalle.vrDescuento       "
                + " WHERE tmpDctosContableDetalle.nombreSubcuenta IN ('vrDsctoFcro') "
                + " AND ( tmpDctosContableDetalle.idTipoOrden     =                  "
                + getIdTipoOrdenINI() + "                                            "
                + " OR    tmpDctosContableDetalle.idTipoOrden     =                  "
                + getIdTipoOrdenFIN() + " ) ";
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

    // actualizaTmpMovimientoRteFuente
    public boolean actualizaTmpMovimientoRteFuente() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                "UPDATE tmpDctosContableDetalle                                  "
                + "   SET tmpDctosContableDetalle.vrMovimiento =                 "
                + "                       (tmpDctosContableDetalle.vrFactura +   "
                + "                        tmpDctosContableDetalle.vrRteFuente ) "
                + " WHERE tmpDctosContableDetalle.nombreSubcuenta IN ('vrCxC')   "
                + " AND ( tmpDctosContableDetalle.idTipoOrden     =              "
                + getIdTipoOrdenINI() + "                                        "
                + " OR    tmpDctosContableDetalle.idTipoOrden     =              "
                + getIdTipoOrdenFIN() + " ) ";

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

    // actualizaTmpMovimientoDescuentoRteFuente
    public boolean actualizaTmpMovimientoDescuentoRteFuenteRteIvaRteIca() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                "UPDATE tmpDctosContableDetalle                                  "
                + "   SET tmpDctosContableDetalle.vrMovimiento =                 "
                + "                       (tmpDctosContableDetalle.vrFactura   + "
                + "                        tmpDctosContableDetalle.vrRteFuente + "
                + "                        tmpDctosContableDetalle.vrRteIva    + "
                + "                        tmpDctosContableDetalle.vrRteIca    + "
                + "                        tmpDctosContableDetalle.vrDescuento ) "
                + " WHERE tmpDctosContableDetalle.nombreSubcuenta IN ('vrCxC')   "
                + " AND ( tmpDctosContableDetalle.idTipoOrden     =              "
                + getIdTipoOrdenINI() + "                                        "
                + " OR    tmpDctosContableDetalle.idTipoOrden     =              "
                + getIdTipoOrdenFIN() + " ) ";
        
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

    // ingresaTmpDctosContablePagos
    public boolean ingresaTmpDctosContablePago(int xIdTipoTercero) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                "   SELECT tblPagos.nitCC AS idCliente,      	       "
                + "                       tblPagos.idLocal,            "
                + "                       tblPagos.idTipoOrden,        "
                + "                       tblPagos.idRecibo,           "
                + "                       tblPagos.indicador,          "
                + "                       tblPagos.fechaPago,          "
                + "                       tblPagos.vrPago,             "
                + "                       tblPagos.vrRteFuente,        "
                + "                       tblPagos.vrDescuento,        "
                + "                       tblPagos.vrRteIva,           "
                + "                       tblPagos.idDcto,             "
                + "                       tblPagos.idDctoNitCC,        "
                + "                       tblPagos.idPlanilla,         "
                + "                       tblPagos.vrRteIca            "
                + "                       ,(SELECT TOP 1               "
                + "                            tblLocalesCaja.prefijo  "
                + "                       FROM   tblDctos              "
                + "                       INNER JOIN tblDctosOrdenes   "
                + "                       ON  tblDctos.IDLOCAL      =  "
                + "                            tblDctosOrdenes.IDLOCAL "
                + "                       AND tblDctos.IDTIPOORDEN  =  "
                + "                        tblDctosOrdenes.IDTIPOORDEN "
                + "                       AND tblDctos.IDORDEN      =  "
                + "                            tblDctosOrdenes.IDORDEN "
                + "                     INNER JOIN tblAgendaLogVisitas "
                + "                       ON tblDctosOrdenes.IDLOG  =  "
                + "                         tblAgendaLogVisitas.IDLOG  "
                + "                       INNER JOIN  tblLocalesCaja   "
                + "                     ON tblAgendaLogVisitas.ipTx =  "
                + "                             tblLocalesCaja.ipLocal "
                + "                     WHERE tblDctos.IDLOCAL      =  "
                + "                                  tblPagos.idLocal  "
                + "                     AND   tblDctos.IDTIPOORDEN  =  "
                + "                               tblPagos.idTipoOrden "
                + "                     AND   tblDctos.idDcto       =  "
                + "                                   tblPagos.idDcto  "
                + "                     AND   tblDctos.indicador    =  "
                + "                     tblPagos.indicador) AS prefijo "
                + "                       ,1 AS idTipoNegocio          "
                + "                       ,0 AS idOrden                "
                + "          ,MAX(tmpTER.digitoVerificacion) 	       "
                + "                            AS digitoVerificacion   "
                + "          ,MAX(tmpTER.nombreTercero) 	       "
                + "                                 AS nombreTercero   "
                + "          ,MAX(tmpTER.direccionTercero) 	       "
                + "                              AS direccionTercero   "
                + "          ,MAX(tmpTER.ciudadTercero) 	       "
                + "                                 AS ciudadTercero   "
                + "          ,MAX(tmpTER.telefonoFijo) 		       "
                + "                                  AS telefonoFijo   "
                + "          ,MAX(tmpTER.telefonoCelular) 	       "
                + "                               AS telefonoCelular   "
                + "          ,MAX(tmpTER.telefonoFax) AS telefonoFax   "
                + "          ,MAX(tmpTER.email) AS email	       "
                + "          ,MAX(tmpTER.contactoTercero) 	       "
                + "                               AS contactoTercero   "
                + "          ,MAX(tmpTER.idPersona) AS idPersona       "
                + "          ,MAX(tmpTER.idAutoRetenedor) 	       "
                + "                               AS idAutoRetenedor   "
                + "          ,MAX(tmpTER.idRegimen)		       "
                + "                                     AS idRegimen   "
                + "          ,MAX(tmpTER.idDptoCiudad)  	       "
                + "                                  AS idDptoCiudad   "
                + "          ,MAX(tmpTER.departamentoTercero) 	       "
                + "                          AS departamentoTercero    "
                + "          ,MAX(tmpTER.tipoIdTercero) 	       "
                + "                               AS tipoIdTercero     "
                + "          INTO tmpDctosContable                     "
                + " FROM   tblPagos 				       "
                + " INNER JOIN (SELECT   tblTerceros.idCliente	       "
                + "          ,tblTerceros.digitoVerificacion	       "
                + "          ,tblTerceros.nombreTercero		       "
                + "          ,tblTerceros.direccionTercero	       "
                + "          ,tblTerceros.ciudadTercero		       "
                + "          ,tblTerceros.telefonoFijo		       "
                + "          ,tblTerceros.telefonoCelular	       "
                + "          ,tblTerceros.telefonoFax		       "
                + "          ,tblTerceros.email			       "
                + "          ,tblTerceros.contactoTercero	       "
                + "          ,tblTerceros.idPersona		       "
                + "          ,tblTerceros.idAutoRetenedor	       "
                + "          ,tblTerceros.idRegimen		       "
                + "          ,tblTerceros.idDptoCiudad		       "
                + "          ,(SELECT tblCiudades.nombreDpto           "
                + "           FROM tblCiudades		     	       "
                + "           WHERE tblCiudades.idCiudad =             "
                + "                 tblTerceros.idDptoCiudad)  	       "
                + "                    AS departamentoTercero          "
                + "          ,tblTerceros.tipoIdTercero 	       "
                + " FROM tblTerceros                                   "
                + " WHERE tblTerceros.idTipoTercero      =             "
                + xIdTipoTercero + ") AS tmpTER			       "
                + " ON tmpTER.idCliente = tblPagos.nitCC	       "
                + " WHERE                                              "
                + " EXISTS ( SELECT tblPagosMedios.*                   "
                + "          FROM tblPagosMedios                       "
                + "          WHERE tblPagos.idLocal              =     "
                + "                   tblPagosMedios.idLocal           "
                + "          AND tblPagos.idTipoOrden            =     "
                + "                   tblPagosMedios.idTipoOrden       "
                + "          AND tblPagos.idRecibo               =     "
                + "                         tblPagosMedios.idRecibo    "
                + "          AND tblPagos.idLog                  =     "
                + "                            tblPagosMedios.idLog    "
                + "          AND tblPagos.indicador              =     "
                + "                        tblPagosMedios.indicador)   "
                + " AND tblPagos.idLocal                         =     "
                + getIdLocal() + "                                     "
                + " AND tblPagos.fechaPago BETWEEN                    '"
                + getFechaInicialSqlServer() + "' AND                 '"
                + getFechaFinalSqlServer() + "'                        "
                + " AND ( tblPagos.idTipoOrden  =                      "
                + getIdTipoOrdenINI() + "                              "
                + " OR tblPagos.idTipoOrden     =                      "
                + getIdTipoOrdenFIN() + " )                            "
                + " AND tblPagos.indicador BETWEEN                     "
                + getIndicadorInicial() + " AND                        "
                + getIndicadorFinal() + "                              "
                + " GROUP BY  tblPagos.nitCC,			       "
                + "           tblPagos.idLocal,               	       "
                + "           tblPagos.idTipoOrden,           	       "
                + "           tblPagos.idRecibo,              	       "
                + "           tblPagos.indicador,             	       "
                + "           tblPagos.fechaPago,             	       "
                + "           tblPagos.vrPago,                	       "
                + "           tblPagos.vrRteFuente,           	       "
                + "           tblPagos.vrDescuento,           	       "
                + "           tblPagos.vrRteIva,              	       "
                + "           tblPagos.idDcto,                	       "
                + "           tblPagos.idDctoNitCC,           	       "
                + "           tblPagos.idPlanilla,            	       "
                + "           tblPagos.vrRteIca             ";

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

    // ingresaTmpDctosContableInventario
    public boolean ingresaTmpDctosContableInventario() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                " SELECT tblDctos.idCliente                            "
                + "        ,tblDctos.IDLOCAL                          "
                + "        ,tblDctos.IDTIPOORDEN                       "
                + "        ,tblDctos.idDcto                            "
                + "        ,0 AS idRecibo                              "
                + "        ,tblDctos.indicador                         "
                + "        ,tblDctos.idOrden                           "
                + "         ,tblDctos.fechaDcto                        "
                + "      ,tblDctos.vrCostoMV                           "
                + "      ,tblDctos.idDctoNitCC                         "
                + "      ,'' AS prefijo                                "
                + "      ,tblDctos.idTipoNegocio                       "
                + "      ,tmpTER.digitoVerificacion                    "
                + "      ,tmpTER.nombreTercero                         "
                + "      ,tmpTER.direccionTercero                      "
                + "      ,tmpTER.ciudadTercero                         "
                + "      ,tmpTER.telefonoFijo                          "
                + "      ,tmpTER.telefonoCelular                       "
                + "      ,tmpTER.telefonoFax                           "
                + "      ,tmpTER.email                                 "
                + "      ,tmpTER.contactoTercero                       "
                + "      ,tmpTER.idPersona                             "
                + "      ,tmpTER.idAutoRetenedor                       "
                + "      ,tmpTER.idRegimen                             "
                + "      ,tmpTER.idDptoCiudad                          "
                + "      ,tmpTER.departamentoTercero                   "
                + "      ,tmpTER.tipoIdTercero                         "
                + "          INTO tmpDctosContable                     "
                + " FROM tblDctos                                      "
                + " INNER JOIN (                                       "
                + " SELECT tblLocales.idLocal AS idCliente             "
                + "      ,0 AS digitoVerificacion                      "
                + "      ,tblLocales.nombreLocal AS nombreTercero      "
                + "      ,tblLocales.direccion AS direccionTercero     "
                + "      ,tblLocales.direccion AS ciudadTercero        "
                + "      ,tblLocales.telefono AS telefonoFijo          "
                + "      ,tblLocales.telefono AS telefonoCelular       "
                + "      ,tblLocales.telefono AS telefonoFax           "
                + "      ,tblLocales.email                             "
                + "      ,'' AS contactoTercero                        "
                + "      ,1 AS idPersona                               "
                + "      ,1 AS idAutoRetenedor                         "
                + "      ,'NI' AS idRegimen                            "
                + "      ,5154 AS idDptoCiudad                         "
                + "      ,'ANTIOQUIA' AS departamentoTercero           "
                + "      ,'C' AS tipoIdTercero                         "
                + "  FROM tblLocales) AS tmpTER                        "
                + " ON tmpTER.idCliente = tblDctos.idCliente           "
                + " WHERE EXISTS (                                     "
                + " SELECT tblDctosOrdenesDetalle.*                    "
                + " FROM tblDctosOrdenesDetalle                        "
                + " WHERE tblDctosOrdenesDetalle.idLocal =             "
                + "                          tblDctos.idLocal          "
                + " AND   tblDctosOrdenesDetalle.idTipoOrden =         "
                + "                      tblDctos.idTipoOrden          "
                + " AND   tblDctosOrdenesDetalle.idOrden =             "
                + "                          tblDctos.idOrden)         "
                + " AND   tblDctos.IDTIPOORDEN IN (15,16,5,3,2,6)      "
                + " AND tblDctos.idLocal                         =     "
                + getIdLocal() + "                                     "
                + " AND tblDctos.fechaDcto BETWEEN                    '"
                + getFechaInicialSqlServer() + "' AND                 '"
                + getFechaFinalSqlServer() + "'                        "
                + " AND tblDctos.indicador BETWEEN                     "
                + getIndicadorInicial() + " AND                        "
                + getIndicadorFinal() + "                              ";


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

    // ingresaTmpDctosContableEgresoIngreso
    public boolean ingresaTmpDctosContableEgresoIngreso() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        //
        String selectStr =
                "    SELECT tblDctos.idCliente               "
                + "         ,tblDctos.IDLOCAL                  "
                + "         ,tblDctos.IDTIPOORDEN              "
                + "         ,tblDctos.idDcto                   "
                + "         ,0 AS idRecibo                     "
                + "         ,tblDctos.indicador                "
                + "         ,tblDctos.idOrden                  "
                + "         ,tblDctos.fechaDcto                "
                + "         ,tblDctos.vrBase AS vrCostoMV      "
                + "         ,tblDctos.idDctoNitCC              "
                + "         ,'' AS prefijo                     "
                + "         ,tblDctos.idTipoNegocio            "
                + "         ,tmpCLI.digitoVerificacion         "
                + "         ,tmpCLI.nombreTercero              "
                + "         ,tmpCLI.direccionTercero           "
                + "         ,tmpCLI.ciudadTercero              "
                + "         ,tmpCLI.telefonoFijo               "
                + "         ,tmpCLI.telefonoCelular            "
                + "         ,tmpCLI.telefonoFax                "
                + "         ,tmpCLI.email                      "
                + "         ,tmpCLI.contactoTercero            "
                + "         ,tmpCLI.idPersona                  "
                + "         ,tmpCLI.idAutoRetenedor            "
                + "         ,tmpCLI.idRegimen                  "
                + "         ,tmpCLI.idDptoCiudad               "
                + "         ,tmpCLI.departamentoTercero        "
                + "         ,tmpCLI.tipoIdTercero              "
                + "          INTO tmpDctosContable             "
                + "    FROM tblDctos                           "
                + "    INNER JOIN (                            "
                + "  SELECT LTRIM(RTRIM(tmpTER.idCliente))     "
                + "                  AS idCliente  "
                + "        ,MAX(tmpTER.nombreTercero)          "
                + "                      AS nombreTercero      "
                + "        ,MAX(tmpTER.digitoVerificacion)     "
                + "                    AS digitoVerificacion   "
                + "        ,MAX(tmpTER.direccionTercero)       "
                + "                    AS direccionTercero     "
                + "        ,MAX(tmpTER.ciudadTercero)          "
                + "                        AS ciudadTercero    "
                + "        ,MAX(tmpTER.telefonoFijo)           "
                + "                        AS telefonoFijo     "
                + "        ,MAX(tmpTER.telefonoCelular)        "
                + "                        AS telefonoCelular  "
                + "        ,MAX(tmpTER.telefonoFax)            "
                + "                        AS telefonoFax      "
                + "        ,MAX(tmpTER.email)          	     "
                + "                        AS email	     "
                + "        ,MAX(tmpTER.contactoTercero)        "
                + "                        AS contactoTercero  "
                + "        ,MAX(tmpTER.idPersona)              "
                + "                        AS idPersona	     "
                + "        ,MAX(tmpTER.idAutoRetenedor)        "
                + "                        AS idAutoRetenedor  "
                + "        ,MAX(tmpTER.idRegimen)              "
                + "                        AS idRegimen	     "
                + "        ,MAX(tmpTER.idDptoCiudad)           "
                + "                        AS idDptoCiudad     "
                + "        ,MAX(tmpTER.departamentoTercero)    "
                + "                    AS departamentoTercero  "
                + "        ,MAX(tmpTER.tipoIdTercero)          "
                + "                    AS tipoIdTercero        "
                + " FROM                                       "
                + " (SELECT LTRIM(RTRIM([idCliente])) AS       "
                + "                                 idCliente  "
                + "        ,digitoVerificacion          	     "
                + "        ,[nombreTercero] 		     "
                + "                      AS nombreTercero      "
                + "        ,[direccionTercero] 		     "
                + "                 AS direccionTercero 	     "
                + "        ,ciudadTercero AS ciudadTercero     "
                + "        ,[telefonoFijo] AS telefonoFijo     "
                + "     ,[telefonoCelular] AS telefonoCelular  "
                + "        ,telefonoFax AS telefonoFax         "
                + "        ,email AS email     		     "
                + "        ,contactoTercero		     "
                + "        ,idPersona			     "
                + "        ,idAutoRetenedor		     "
                + "        ,idRegimen			     "
                + "        ,idDptoCiudad			     "
                + "        ,(SELECT tblCiudades.nombreDpto     "
                + "          FROM tblCiudades		     "
                + "          WHERE tblCiudades.idCiudad =      "
                + "                 tblTerceros.idDptoCiudad)  "
                + "                   AS departamentoTercero   "
                + "        ,tipoIdTercero 	             "
                + "  FROM [BDCommerce].[dbo].[tblTerceros]     "
                + "  UNION                                     "
                + "  SELECT LTRIM(RTRIM([idUsuario])) AS       "
                + "                                 idCliente  "
                + "        ,0 AS digitoVerificacion            "
                + "        ,[nombreUsuario] 		     "
                + "                      AS nombreTercero      "
                + "        ,[direccion] AS direccionTercero    "
                + "        ,'' AS ciudadTercero       	     "
                + "        ,[telefono] AS telefonoFijo	     "
                + "        ,[telefono] AS telefonoCelular      "
                + "        ,[telefono] AS telefonoFax          "
                + "        ,email AS email           	     "
                + "        ,[nombreUsuario] AS contactoTercero "
                + "        ,0 AS idPersona		     "
                + "        ,0 AS idAutoRetenedor		     "
                + "        ,'NI' AS idRegimen		     "
                + "        ,5154 AS idDptoCiudad		     "
                + "    ,'ANTIOQUIA' AS departamentoTercero     "
                + "        ,'C' AS tipoIdTercero 	      	     "
                + "  FROM [BDCommerce].[dbo].[ctrlUsuarios]    "
                + "  UNION                                     "
                + "  SELECT LTRIM(RTRIM([idLocal])) AS         "
                + "                                 idCliente  "
                + "          ,0 AS digitoVerificacion          "
                + "          ,[nombreLocal] AS nombreTercero   "
                + "          ,[direccion] AS direccionTercero  "
                + "          ,'' AS ciudadTercero       	     "
                + "          ,[telefono] AS telefonoFijo	     "
                + "          ,[telefono] AS telefonoCelular    "
                + "          ,[telefono] AS telefonoFax        "
                + "          ,[email]                          "
                + "          ,[nombreLocal] AS contactoTercero "
                + "          ,0 AS idPersona		     "
                + "          ,0 AS idAutoRetenedor             "
                + "          ,'NI' AS idRegimen		     "
                + "          ,5154 AS idDptoCiudad             "
                + "      ,'ANTIOQUIA' AS departamentoTercero   "
                + "        , 'C' AS tipoIdTercero              "
                + "  FROM [BDCommerce].[dbo].[tblLocales] )    "
                + "                                 AS tmpTER  "
                + "  GROUP BY  LTRIM(RTRIM(tmpTER.idCliente))) "
                + "                             AS tmpCLI      "
                + " ON tmpCLI.idCliente = tblDctos.idCliente   "
                + " WHERE EXISTS (                             "
                + " SELECT tblDctosOrdenesDetalle.*            "
                + " FROM tblDctosOrdenesDetalle                "
                + " WHERE tblDctosOrdenesDetalle.idLocal     = "
                + "                          tblDctos.idLocal  "
                + " AND   tblDctosOrdenesDetalle.idTipoOrden = "
                + "                       tblDctos.idTipoOrden "
                + " AND   tblDctosOrdenesDetalle.idOrden     = "
                + "                          tblDctos.idOrden) "
                + " AND   tblDctos.IDTIPOORDEN BETWEEN         "
                + getIdTipoOrdenINI() + "  AND                 "
                + getIdTipoOrdenFIN() + "                      "
                + " AND tblDctos.idLocal                  =    "
                + getIdLocal() + "                             "
                + " AND tblDctos.fechaDcto BETWEEN            '"
                + getFechaInicialSqlServer() + "' AND         '"
                + getFechaFinalSqlServer() + "'                "
                + " AND tblDctos.indicador BETWEEN             "
                + getIndicadorInicial() + " AND                "
                + getIndicadorFinal() + "                      ";


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

    // ingresaTmpDctosContableDetallePago_12
    public boolean ingresaTmpDctosContableDetallePago_12(int xIdComprobante) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                " SELECT tmpDctosContable.idLocal,		 "
                + "        tblTipoOrdenSubcuenta.idTipoOrden,    "
                + "        tmpDctosContable.idDcto               "
                + "                           AS idDctoNitCC,    "
                + "        tmpDctosContable.idOrden,       	 "
                + "        tmpDctosContable.idRecibo,       	 "
                + "        tblTipoOrdenSubcuenta.idSubcuenta, 	 "
                + "        tblTipoOrdenSubcuenta.idAsiento, 	 "
                + "        tblTipoOrdenSubcuenta.idComprobante,  "
                + "        tblTipoOrdenSubcuenta.porcentajeIva,  "
                + "        tblTipoOrdenSubcuenta.nombreSubcuenta,"
                + "        tmpDctosContable.idCliente		 "
                + "       ,tmpDctosContable.idRecibo 		 "
                + "                             AS idDcto	 "
                + "       ,tmpDctosContable.indicador		 "
                + "       ,tmpDctosContable.fechaPago 		 "
                + "                          AS fechaDcto	 "
                + "       ,tmpDctosContable.vrPago 		 "
                + "                            AS vrFactura 	 "
                + "       ,tmpDctosContable.vrRteFuente		 "
                + "       ,tmpDctosContable.vrDescuento		 "
                + "       ,tmpDctosContable.vrRteIva		 "
                + "       ,tmpDctosContable.vrRteIca      	 "
                + "       ,tmpDctosContable.prefijo		 "
                + "       ,CONVERT(decimal(14,0), 0) 		 "
                + "                         AS vrMovimiento  	 "
                + "        INTO tmpDctosContableDetalle          "
                + " FROM       tblTipoOrdenSubcuenta		 "
                + " INNER JOIN tmpDctosContable			 "
                + " ON tblTipoOrdenSubcuenta.idTipoOrden       = "
                + "                 tmpDctosContable.IDTIPOORDEN "
                + " WHERE  tblTipoOrdenSubcuenta.idComprobante = "
                + xIdComprobante;

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

    // ingresaTmpDctosContableDetallePago_13
    public boolean ingresaTmpDctosContableDetallePago_13(int xIdComprobante) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                " SELECT tmpDctosContable.idLocal,		 "
                + "        tblTipoOrdenSubcuenta.idTipoOrden,    "
                + "        tmpDctosContable.idDctoNitCC,	 "
                + "        tmpDctosContable.idOrden,       	 "
                + "        tmpDctosContable.idRecibo,       	 "
                + "        tblTipoOrdenSubcuenta.idSubcuenta, 	 "
                + "        tblTipoOrdenSubcuenta.idAsiento, 	 "
                + "        tblTipoOrdenSubcuenta.idComprobante,  "
                + "        tblTipoOrdenSubcuenta.porcentajeIva,  "
                + "        tblTipoOrdenSubcuenta.nombreSubcuenta,"
                + "        tmpDctosContable.idCliente		 "
                + "       ,tmpDctosContable.idRecibo 		 "
                + "                             AS idDcto	 "
                + "       ,tmpDctosContable.indicador		 "
                + "       ,tmpDctosContable.fechaPago 		 "
                + "                          AS fechaDcto	 "
                + "       ,tmpDctosContable.vrPago 		 "
                + "                            AS vrFactura 	 "
                + "       ,tmpDctosContable.vrRteFuente		 "
                + "       ,tmpDctosContable.vrDescuento		 "
                + "       ,tmpDctosContable.vrRteIva		 "
                + "       ,tmpDctosContable.vrRteIca      	 "
                + "       ,tmpDctosContable.prefijo		 "
                + "       ,CONVERT(decimal(14,0), 0) 		 "
                + "                         AS vrMovimiento  	 "
                + "        INTO tmpDctosContableDetalle          "
                + " FROM       tblTipoOrdenSubcuenta		 "
                + " INNER JOIN tmpDctosContable			 "
                + " ON tblTipoOrdenSubcuenta.idTipoOrden       = "
                + "                 tmpDctosContable.IDTIPOORDEN "
                + " WHERE  tblTipoOrdenSubcuenta.idComprobante = "
                + xIdComprobante;

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

    // ingresaTmpDctosContableDetallePago
    public boolean ingresaTmpDctosContableDetalleInventario(int xIdComprobante) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                " SELECT tmpDctosContable.idLocal,		 "
                + "        tblTipoOrdenSubcuenta.idTipoOrden,    "
                + "        tmpDctosContable.idDctoNitCC,	 "
                + "        tmpDctosContable.idOrden,    	 "
                + "        tmpDctosContable.idRecibo,    	 "
                + "        tblTipoOrdenSubcuenta.idSubcuenta, 	 "
                + "        tblTipoOrdenSubcuenta.idAsiento, 	 "
                + "        tblTipoOrdenSubcuenta.idComprobante,  "
                + "        tblTipoOrdenSubcuenta.porcentajeIva,  "
                + "        tblTipoOrdenSubcuenta.nombreSubcuenta,"
                + "        tmpDctosContable.idCliente		 "
                + "       ,tmpDctosContable.idDcto 		 "
                + "       ,tmpDctosContable.indicador		 "
                + "       ,tmpDctosContable.fechaDcto 		 "
                + "       ,tmpDctosContable.vrCostoMV 		 "
                + "                            AS vrFactura 	 "
                + "       ,0 AS vrRteFuente        		 "
                + "       ,0 AS vrDescuento		         "
                + "       ,0 AS  vrRteIva		         "
                + "       ,0 AS vrRteIca      	                 "
                + "       ,tmpDctosContable.prefijo		 "
                + "       ,CONVERT(decimal(14,0), 0) 		 "
                + "                         AS vrMovimiento  	 "
                + "        INTO tmpDctosContableDetalle          "
                + " FROM       tblTipoOrdenSubcuenta		 "
                + " INNER JOIN tmpDctosContable			 "
                + " ON tblTipoOrdenSubcuenta.idTipoOrden       = "
                + "                 tmpDctosContable.IDTIPOORDEN "
                + " WHERE  tblTipoOrdenSubcuenta.idComprobante = "
                + xIdComprobante;

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

    // listaTercero
    public Vector listaTercero() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        Vector contenedor = new Vector();
        Connection connection = null;

        String selectStr =
                " SELECT tmpDctosContable.idCliente		  "
                + "       ,MAX(tmpDctosContable.digitoVerificacion) "
                + "                          AS digitoVerificacion  "
                + "       ,MAX(tmpDctosContable.nombreTercero) 	  "
                + "                               AS nombreTercero  "
                + "       ,MAX(tmpDctosContable.direccionTercero)   "
                + "                            AS direccionTercero  "
                + "       ,MAX(tmpDctosContable.ciudadTercero) 	  "
                + "                               AS ciudadTercero  "
                + "       ,MAX(tmpDctosContable.telefonoFijo) 	  "
                + "                                AS telefonoFijo  "
                + "       ,MAX(tmpDctosContable.telefonoCelular) 	  "
                + "                             AS telefonoCelular  "
                + "       ,MAX(tmpDctosContable.telefonoFax) 	  "
                + "                                 AS telefonoFax  "
                + "       ,MAX(tmpDctosContable.email) AS email	  "
                + "       ,MAX(tmpDctosContable.contactoTercero) 	  "
                + "                             AS contactoTercero  "
                + "       ,MAX(tmpDctosContable.idPersona) 	  "
                + "                                   AS idPersona  "
                + "       ,MAX(tmpDctosContable.idAutoRetenedor) 	  "
                + "                             AS idAutoRetenedor  "
                + "       ,MAX(tmpDctosContable.idRegimen) 	  "
                + "                                   AS idRegimen  "
                + "       ,MAX(tmpDctosContable.idDptoCiudad) 	  "
                + "                                AS idDptoCiudad  "
                + "       ,MAX(tmpDctosContable.departamentoTercero)"
                + "                          AS departamentoTercero "
                + "       ,MAX(tmpDctosContable.tipoIdTercero) 	  "
                + "                                AS tipoIdTercero "
                + " FROM tmpDctosContable				  "
                + " GROUP BY tmpDctosContable.idCliente		  ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaTerceroBean fachadaBean;

            //
            while (rs.next()) {

                fachadaBean = new FachadaTerceroBean();

                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setDigitoVerificacion(
                        rs.getInt("digitoVerificacion"));
                fachadaBean.setNombreTercero(rs.getString("nombreTercero"));
                fachadaBean.setDireccionTercero(rs.getString("direccionTercero"));
                fachadaBean.setCiudadTercero(rs.getString("ciudadTercero"));
                fachadaBean.setTelefonoFijo(rs.getString("telefonoFijo"));
                fachadaBean.setTelefonoCelular(rs.getString("telefonoCelular"));
                fachadaBean.setTelefonoFax(rs.getString("telefonoFax"));
                fachadaBean.setEmail(rs.getString("email"));
                fachadaBean.setContactoTercero(rs.getString("contactoTercero"));
                fachadaBean.setIdPersona(rs.getInt("idPersona"));
                fachadaBean.setIdAutoRetenedor(rs.getInt("idAutoRetenedor"));
                fachadaBean.setIdRegimen(rs.getString("idRegimen"));
                fachadaBean.setIdDptoCiudad(rs.getInt("idDptoCiudad"));
                fachadaBean.setTipoIdTercero(rs.getString("tipoIdTercero"));
                fachadaBean.setDepartamentoTercero(
                        rs.getString("departamentoTercero"));

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
            jdbcAccess.cleanup(connection, selectStatement, null);
            return contenedor;
        }
    }

    // Metodo listaDcto
    public Vector listaDcto() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        //
        int xItemMovimiento = 0;
        double xIdDctoInicial = 0;

        //
        Vector contenedor = new Vector();

        Connection connection = null;

        String selectStr =
                " 	SELECT tmpDctosContableDetalle.idLocal       "
                + "            ,tmpDctosContableDetalle.idTipoOrden      "
                + "            ,tmpDctosContableDetalle.idDcto           "
                + " 		   ,tmpDctosContableDetalle.idOrden  "
                + "            ,tmpDctosContableDetalle.idDctoNitCC      "
                + "            ,tmpDctosContableDetalle.idCliente        "
                + "            ,tmpDctosContableDetalle.fechaDcto        "
                + "            ,tmpDctosContableDetalle.prefijo          "
                + "            ,tmpDctosContableDetalle.vrMovimiento     "
                + "                                        AS vrBase     "
                + "            ,tmpDctosContableDetalle.idSubcuenta      "
                + "            ,tmpDctosContableDetalle.idAsiento        "
                + "            ,tmpDctosContableDetalle.idComprobante    "
                + "            ,(CASE WHEN                               "
                + "               tmpDctosContableDetalle.idAsiento = 1  "
                + "             THEN              'DNO'                  "
                + "             ELSE              'CNO'                  "
                + "             END)  AS nombreAsiento		     "
                + "       ,ISNULL(( SELECT TOP 1                         "
                + "          tmpDctosContableDetalle.vrMovimiento /      "
                + "          (tblContableRetencion.porcentajeRetencion   "
                + "                                         / 100)       "
                + "                 FROM tblContableRetencion            "
                + "         WHERE tblContableRetencion.idSubcuenta =     "
                + "             tmpDctosContableDetalle.idSubcuenta ),0) "
                + "                                 AS vrBaseContable,   "
                + "      ( SELECT tblTipoOrden.nombreTipoOrden           "
                + "                                                      "
                + "        FROM tblTipoOrden                             "
                + "        WHERE tblTipoOrden.idTipoOrden           =    "
                + "              tmpDctosContableDetalle.idTipoOrden )   "
                + "                             AS comentarioMovimiento  "
                + "      FROM tmpDctosContableDetalle                    "
                + "      ORDER BY  tmpDctosContableDetalle.idLocal       "
                + "               ,tmpDctosContableDetalle.idTipoOrden   "
                + "               ,tmpDctosContableDetalle.idOrden       "
                + "               ,tmpDctosContableDetalle.idDcto        "
                + "               ,tmpDctosContableDetalle.idCliente     "
                + "               ,tmpDctosContableDetalle.idAsiento  ";


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

            //
            while (rs.next()) {

                // Variable de fachada de los datos
                fachadaBean = new FachadaDctoBean();

                //
                fachadaBean.setIdDcto(rs.getInt("idDcto"));

                //-- item
                if (xIdDctoInicial != fachadaBean.getIdDcto()) {

                    //
                    xIdDctoInicial = fachadaBean.getIdDcto();

                    xItemMovimiento = 0;

                }

                //
                xItemMovimiento += 1;

                //
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setIdDctoNitCC(rs.getString("idDctoNitCC"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setIdSubcuenta(rs.getString("idSubcuenta"));
                fachadaBean.setIdAsiento(rs.getInt("idAsiento"));
                fachadaBean.setIdComprobanteContable(rs.getInt("idComprobante"));
                fachadaBean.setFechaDcto(rs.getString("fechaDcto"));
                fachadaBean.setVrBase(rs.getDouble("vrBase"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setVrBaseContable(rs.getDouble("vrBaseContable"));
                fachadaBean.setNombreAsiento(rs.getString("nombreAsiento"));
                fachadaBean.setItemMovimiento(xItemMovimiento);
                fachadaBean.setComentarioMovimiento(
                        rs.getString("ComentarioMovimiento"));
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

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {

            // Cierra la conexion a la base de datos limpiando la statement usada y la
            jdbcAccess.cleanup(connection, selectStatement, null);
            return contenedor;
        }
    }

    // Metodo listaDctoPago
    public Vector listaDctoPago() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        //
        int xItemMovimiento = 0;
        double xIdDctoInicial = 0;

        //
        Vector contenedor = new Vector();

        Connection connection = null;

        String selectStr =
                " SELECT tmpDctosContableDetalle.idLocal            "
                + "       ,tmpDctosContableDetalle.idTipoOrden      "
                + "       ,tmpDctosContableDetalle.idDcto           "
                + "       ,tmpDctosContableDetalle.idDctoNitCC      "
                + "       ,tmpDctosContableDetalle.idCliente        "
                + "       ,tmpDctosContableDetalle.fechaDcto        "
                + "       ,tmpDctosContableDetalle.prefijo          "
                + "       ,tmpDctosContableDetalle.vrMovimiento     "
                + "                                   AS vrBase     "
                + "       ,tmpDctosContableDetalle.idSubcuenta      "
                + "       ,tmpDctosContableDetalle.idAsiento        "
                + "       ,tmpDctosContableDetalle.idComprobante    "
                + "       ,(CASE WHEN                               "
                + "          tmpDctosContableDetalle.idAsiento = 1  "
                + "        THEN              'DNO'                  "
                + "        ELSE              'CNO'                  "
                + "        END)  AS nombreAsiento		    "
                + "  ,ISNULL(( SELECT TOP 1                         "
                + "     tmpDctosContableDetalle.vrMovimiento /      "
                + "     (tblContableRetencion.porcentajeRetencion   "
                + "                                    / 100)       "
                + "            FROM tblContableRetencion            "
                + "    WHERE tblContableRetencion.idSubcuenta =     "
                + "        tmpDctosContableDetalle.idSubcuenta ),0) "
                + "                            AS vrBaseContable,   "
                + " ( SELECT tblTipoOrden.nombreTipoOrden           "
                + "   FROM tblTipoOrden                             "
                + "   WHERE tblTipoOrden.idTipoOrden           =    "
                + "         tmpDctosContableDetalle.idTipoOrden )   "
                + "                        AS comentarioMovimiento  "
                + " FROM tmpDctosContableDetalle                    "
                + " WHERE  NOT EXISTS                               "
                + "  ( SELECT tmpDctosContable.*                    "
                + "    FROM tmpDctosContable                        "
                + "    WHERE tmpDctosContable.idLocal     =         "
                + "                tmpDctosContableDetalle.idLocal  "
                + "    AND   tmpDctosContable.idTipoOrden =         "
                + "           tmpDctosContableDetalle.idTipoOrden   "
                + "    AND   tmpDctosContable.idRecibo    =         "
                + "                 tmpDctosContableDetalle.idDcto  "
                + "    AND   tmpDctosContable.idCliente   =         "
                + "           tmpDctosContableDetalle.idCliente     "
                + "      AND tmpDctosContable.idTipoNegocio = 1  )  "
                + " ORDER BY  tmpDctosContableDetalle.idLocal       "
                + "          ,tmpDctosContableDetalle.idTipoOrden   "
                + "          ,tmpDctosContableDetalle.idDcto        "
                + "          ,tmpDctosContableDetalle.idCliente     "
                + "          ,tmpDctosContableDetalle.idAsiento     ";


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

            //
            while (rs.next()) {

                // Variable de fachada de los datos
                fachadaBean = new FachadaDctoBean();

                //
                fachadaBean.setIdDcto(rs.getInt("idDcto"));

                //-- item
                if (xIdDctoInicial != fachadaBean.getIdDcto()) {

                    //
                    xIdDctoInicial = fachadaBean.getIdDcto();

                    xItemMovimiento = 0;

                }

                //
                xItemMovimiento += 1;

                //
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setIdDctoNitCC(rs.getString("idDctoNitCC"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setIdSubcuenta(rs.getString("idSubcuenta"));
                fachadaBean.setIdAsiento(rs.getInt("idAsiento"));
                fachadaBean.setIdComprobanteContable(rs.getInt("idComprobante"));
                fachadaBean.setFechaDcto(rs.getString("fechaDcto"));
                fachadaBean.setVrBase(rs.getDouble("vrBase"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setVrBaseContable(rs.getDouble("vrBaseContable"));
                fachadaBean.setNombreAsiento(rs.getString("nombreAsiento"));
                fachadaBean.setItemMovimiento(xItemMovimiento);
                fachadaBean.setComentarioMovimiento(
                        rs.getString("ComentarioMovimiento"));
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

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {

            // Cierra la conexion a la base de datos limpiando la statement usada y la
            jdbcAccess.cleanup(connection, selectStatement, null);
            return contenedor;
        }
    }
}
