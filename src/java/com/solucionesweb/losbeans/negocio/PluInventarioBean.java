package com.solucionesweb.losbeans.negocio;

// Importa la clase helper JDBCAccess que contiene los accesos a la base de datos
import com.solucionesweb.lasayudas.JDBCAccess;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaPluInventarioBean;

// Importa los paquetes de java
import java.sql.*;
import java.io.*;
//import java.util.Date;
import java.io.IOException.*;

// Importa los paquetes de javax
import javax.naming.*;

// Importa la interface de las constantes
import com.solucionesweb.losbeans.utilidades.IConstantes;

public class PluInventarioBean extends FachadaPluInventarioBean
        implements Serializable,
        IConstantes {

    // Nombre JNDI del Data Source que esta clase requiere
    private static final String DATA_SOURCE_NAME = "CommerceAccess";
    // Clase helper usada para acceder a la base de datos
    private JDBCAccess jdbcAccess;

    // Metodo constructor por defecto sin parametros
    public PluInventarioBean() {

        // Al instanciar el Bean establece la conexion a la base de datos
        jdbcAccess = new JDBCAccess(DATA_SOURCE_NAME);
    }

    // actualiza
    public boolean actualiza() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okActualizarDctoOrdenFactura = false;

        //
        Connection connection = null;

        String selectStr =
                "UPDATE tblPlusInventario            "
                + "SET  tblPlusInventario.existencia = "
                + "   ( tblPlusInventario.existencia + "
                + getCantidadOrden() + " )             "
                + "WHERE tblPlusInventario.idLocal   = "
                + getIdLocal() + "                     "
                + "AND   tblPlusInventario.idPlu     = "
                + getIdPlu();

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            selectStatement.execute();
            okActualizarDctoOrdenFactura = true;


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

            // Cierra el Resultset
            jdbcAccess.cleanup(connection, selectStatement, null);
            return okActualizarDctoOrdenFactura;
        }
    }

    // actualizaInventarioEstado
    public boolean actualizaInventarioEstado() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okActualizarDctoOrdenFactura = false;

        //
        Connection connection = null;

        String selectStr =
                "UPDATE tblPlusInventario                               "
                + "SET    tblPlusInventario.existencia       =      	"
                + "     ( tmpINV.cantidad *                      	"
                + "           ( SELECT tblTipoOrden.signo               "
                + "             FROM tblTipoOrden                       "
                + "             WHERE tblTipoOrden.idTipoOrden   =      "
                + getIdTipoOrden() + " ) )                              "
                + "                    + tblPlusInventario.existencia   "
                + "             FROM tblPlusInventario,	   		"
                + "                (					"
                + "SELECT tmpTOT.idLocal,				"
                + "       tmpTOT.idPlu,			                "
                + "       tmpTOT.idBodega,    	                    	"
                + "       SUM(tmpTOT.cantidad) 			        "
                + "                  AS cantidad		        "
                + "FROM (SELECT tmpPLU.idLocal,			        "
                + "             tblPlusCombo.idPlu,			"
                + "             tmpPLU.idBodega,	                "
                + "            (tblPlusCombo.cantidad *		        "
                + "                      tmpPLU.cantidad ) AS cantidad	"
                + "      FROM tblPlusCombo,				"
                + " ( SELECT tblPlus.idTipo,			        "
                + "          tmpDOR.*				        "
                + "   FROM tblPlus,(					"
                + "       SELECT tblDctosOrdenesDetalle.idLocal,      	"
                + "              tblDctosOrdenesDetalle.idPlu,        	"
                + "              tblDctosOrdenesDetalle.idBodega,     	"
                + "              SUM(tblDctosOrdenesDetalle.cantidad) 	"
                + "                                       AS cantidad 	"
                + "       FROM tblDctosOrdenesDetalle                 	"
                + "       WHERE tblDctosOrdenesDetalle.idLocal     =    "
                + getIdLocal() + "                                      "
                + "       AND   tblDctosOrdenesDetalle.idTipoOrden =    "
                + getIdTipoOrden() + "                                  "
                + "       AND   tblDctosOrdenesDetalle.idOrden     =    "
                + getIdOrden() + "                                      "
                + "       AND   tblDctosOrdenesDetalle.estado      != 4	"
                + "       GROUP BY tblDctosOrdenesDetalle.idLocal,      "
                + "                tblDctosOrdenesDetalle.idPlu,      	"
                + "                tblDctosOrdenesDetalle.idBodega)    	"
                + "                                          AS tmpDOR	"
                + "       WHERE tblPlus.idPlu  = 		        "
                + "                            tmpDOR.idPlu ) AS tmpPLU	"
                + "   WHERE tmpPLU.idPlu       =  		        "
                + "                             tblPlusCombo.idPluCombo	"
                + "UNION			                        "
                + "SELECT tblDctosOrdenesDetalle.idLocal,  	        "
                + "       tblDctosOrdenesDetalle.idPlu,        	        "
                + "       tblDctosOrdenesDetalle.idBodega,             	"
                + "       SUM(tblDctosOrdenesDetalle.cantidad) 	    	"
                + "                        AS cantidad 			"
                + "FROM tblDctosOrdenesDetalle                 	     	"
                + "WHERE tblDctosOrdenesDetalle.idLocal            =    "
                + getIdLocal() + "                                      "
                + "       AND   tblDctosOrdenesDetalle.idTipoOrden =    "
                + getIdTipoOrden() + "                                  "
                + "       AND   tblDctosOrdenesDetalle.idOrden     =    "
                + getIdOrden() + "                                      "
                + "AND   tblDctosOrdenesDetalle.estado            != 4	"
                + "AND   NOT EXISTS 				        "
                + "      ( SELECT tblPlus.*			        "
                + "        FROM tblPlus				        "
                + "        WHERE tblPlus.idPlu = 			"
                + "                   tblDctosOrdenesDetalle.idPlu     	"
                + "        AND tblPlus.idTipo  = 2 )			"
                + "GROUP BY tblDctosOrdenesDetalle.idLocal,      	"
                + "         tblDctosOrdenesDetalle.idPlu,      	        "
                + "         tblDctosOrdenesDetalle.idBodega)          	"
                + "AS tmpTOT					        "
                + "GROUP BY tmpTOT.idLocal,			        "
                + "         tmpTOT.idPlu,                               "
                + "         tmpTOT.idBodega ) AS tmpINV		        "
                + "WHERE tmpINV.idPlu       = tblPlusInventario.idPlu	"
                + "AND   tmpINV.idLocal     = tblPlusInventario.idLocal	"
                + "AND   tmpINV.idBodega    = tblPlusInventario.idBodega";


        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            selectStatement.execute();
            okActualizarDctoOrdenFactura = true;


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

            // Cierra el Resultset
            jdbcAccess.cleanup(connection, selectStatement, null);
            return okActualizarDctoOrdenFactura;
        }
    }
    
    // actualizaInventarioEstado
    public boolean actualizaInventarioBodegas(int xBodegaOrigen, int xSingnoEntrada) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okActualizarDctoOrdenFactura = false;

        //
        Connection connection = null;

        String selectStr =
                  "UPDATE tblPlusInventario\n"
                + "   SET tblPlusInventario.existencia       =  ( tmpTIV.cantidad * (\n"
                + xSingnoEntrada + "))  + tblPlusInventario.existencia  \n"
                + "   FROM tblPlusInventario,(SELECT TOP 1 tblDctosOrdenes.idLocal,\n"
                + "		tblPlusFicha.idFicha,\n"
                + "		tblPlusFicha.idPlu,\n"
                + "		tblDctosOrdenesDetalle.idoperacion,\n"
                + "		tblDctosOrdenesdetalle.idordenorigen,\n"
                + "		tblDctosOrdenesDetalle.cantidad,\n"
                + "		tblDctosOrdenesDetalle.idtipoorden,\n"
                + "		tblDctosOrdenes.idorden,\n"
                + "		tblDctosOrdenes.idficha as ficha\n"
                + "		from tblDctosOrdenesDetalle\n"
                + "		inner join tblDctosOrdenes\n"
                + "		on tblDctosOrdenesDetalle.idordenOrigen = tblDctosOrdenes.idorden\n"
                + "		inner join tblPlusFicha\n"
                + "		on tblDctosOrdenes.idficha = tblPlusFicha.idficha\n"
                + "		where tblDctosOrdenesDetalle.idorden =  "
                + getIdOrden() +"                                               \n"
                + "		AND tblDctosOrdenesDetalle.idoperacion = "
                + xBodegaOrigen + ") AS tmpTIV                                  \n"
                + "		WHERE tmpTIV.idPlu = tblPlusInventario.idPLu\n"
                + "		AND tmpTIV.idoperacion = tblPlusInventario.idBodega";


        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            selectStatement.execute();
            okActualizarDctoOrdenFactura = true;


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

            // Cierra el Resultset
            jdbcAccess.cleanup(connection, selectStatement, null);
            return okActualizarDctoOrdenFactura;
        }
    }
    
    // actualizaInventarioEstado
    public boolean actualizaInventarioBodega(int xBodegaDestino, int xSingnoSalida) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okActualizarDctoOrdenFactura = false;

        //
        Connection connection = null;

        String selectStr =
                  "UPDATE tblPlusInventario\n"
                + "   SET tblPlusInventario.existencia       =  ( tmpTIV.cantidad * (\n"
                + xSingnoSalida + "))  + tblPlusInventario.existencia  \n"
                + "   FROM tblPlusInventario,(SELECT TOP 1 tblDctosOrdenes.idLocal,\n"
                + "		tblPlusFicha.idFicha,\n"
                + "		tblPlusFicha.idPlu,\n"
                + "		tblDctosOrdenesDetalle.idoperacion,\n"
                + "		tblDctosOrdenesdetalle.idordenorigen,\n"
                + "		tblDctosOrdenesDetalle.cantidad,\n"
                + "		tblDctosOrdenesDetalle.idtipoorden,\n"
                + "		tblDctosOrdenes.idorden,\n"
                + "		tblDctosOrdenes.idficha as ficha\n"
                + "		from tblDctosOrdenesDetalle\n"
                + "		inner join tblDctosOrdenes\n"
                + "		on tblDctosOrdenesDetalle.idordenOrigen = tblDctosOrdenes.idorden\n"
                + "		inner join tblPlusFicha\n"
                + "		on tblDctosOrdenes.idficha = tblPlusFicha.idficha\n"
                + "		where tblDctosOrdenesDetalle.idorden =  "
                + getIdOrden() +"                                               \n"
                + "		AND tblDctosOrdenesDetalle.idoperacion = "
                + xBodegaDestino + ") AS tmpTIV                                  \n"
                + "		WHERE tmpTIV.idPlu = tblPlusInventario.idPLu\n"
                + "		AND tmpTIV.idoperacion = tblPlusInventario.idBodega";


        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            selectStatement.execute();
            okActualizarDctoOrdenFactura = true;


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

            // Cierra el Resultset
            jdbcAccess.cleanup(connection, selectStatement, null);
            return okActualizarDctoOrdenFactura;
        }
    }
    // actualizaInventarioEstado
    public boolean actualizaInventarioBodegaRetal(int xBodegaDestino, int xSingnoSalida) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okActualizarDctoOrdenFactura = false;

        //
        Connection connection = null;

        String selectStr =
                  "UPDATE tblPlusInventario\n"
                + "   SET tblPlusInventario.existencia       =  ( tmpTIV.cantidad * (\n"
                + xSingnoSalida + "))  + tblPlusInventario.existencia  \n"
                + "   FROM tblPlusInventario,(SELECT TOP 1 tblDctosOrdenes.idLocal,\n"
                + "		tblPlusFicha.idFicha,\n"
                + "		tblPlusFicha.idPluRetal,\n"
                + "		tblDctosOrdenesDetalle.idoperacion,\n"
                + "		tblDctosOrdenesdetalle.idordenorigen,\n"
                + "		tblDctosOrdenesDetalle.cantidad,\n"
                + "		tblDctosOrdenesDetalle.idtipoorden,\n"
                + "		tblDctosOrdenes.idorden,\n"
                + "		tblDctosOrdenes.idficha as ficha\n"
                + "		from tblDctosOrdenesDetalle\n"
                + "		inner join tblDctosOrdenes\n"
                + "		on tblDctosOrdenesDetalle.idordenOrigen = tblDctosOrdenes.idorden\n"
                + "		inner join tblPlusFicha\n"
                + "		on tblDctosOrdenes.idficha = tblPlusFicha.idficha\n"
                + "		where tblDctosOrdenesDetalle.idorden =  "
                + getIdOrden() +"                                               \n"
                + "		AND tblDctosOrdenesDetalle.idoperacion = "
                + xBodegaDestino + ") AS tmpTIV                                  \n"
                + "		WHERE tmpTIV.idPluRetal = tblPlusInventario.idPLu\n"
                + "		AND tmpTIV.idoperacion = tblPlusInventario.idBodega";


        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            selectStatement.execute();
            okActualizarDctoOrdenFactura = true;


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

            // Cierra el Resultset
            jdbcAccess.cleanup(connection, selectStatement, null);
            return okActualizarDctoOrdenFactura;
        }
    }
    
    // actualizaInventarioEstado
    public boolean actualizaInventarioEstadoTer(int xIdBodega) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okActualizarDctoOrdenFactura = false;

        //
        Connection connection = null;

        String selectStr =
                "UPDATE tblPlusInventario                               "
                + "SET    tblPlusInventario.existencia       =      	"
                + "     ( tmpINV.cantidad *                      	"
                + "           ( SELECT tblTipoOrden.signo               "
                + "             FROM tblTipoOrden                       "
                + "             WHERE tblTipoOrden.idTipoOrden   =      "
                + getIdTipoOrden() + " ) )                              "
                + "                    + tblPlusInventario.existencia   "
                + "             FROM tblPlusInventario,	   		"
                + "                (					"
                + "SELECT tmpTOT.idLocal,				"
                + "       tmpTOT.idPlu,			                "
                + "       tmpTOT.idBodega,    	                    	"
                + "       SUM(tmpTOT.cantidad) 			        "
                + "                  AS cantidad		        "
                + "FROM (SELECT tmpPLU.idLocal,			        "
                + "             tblPlusCombo.idPlu,			"
                + "             tmpPLU.idBodega,	                "
                + "            (tblPlusCombo.cantidad *		        "
                + "                      tmpPLU.cantidad ) AS cantidad	"
                + "      FROM tblPlusCombo,				"
                + " ( SELECT tblPlus.idTipo,			        "
                + "          tmpDOR.*				        "
                + "   FROM tblPlus,(					"
                + "       SELECT tblDctosOrdenesDetalle.idLocal,      	"
                + "              tblDctosOrdenesDetalle.idPlu,        	"
                + "              tblDctosOrdenesDetalle.idBodega,     	"
                + "              SUM(tblDctosOrdenesDetalle.cantidad) 	"
                + "                                       AS cantidad 	"
                + "       FROM tblDctosOrdenesDetalle                 	"
                + "       WHERE tblDctosOrdenesDetalle.idLocal     =    "
                + getIdLocal() + "                                      "
                + "       AND   tblDctosOrdenesDetalle.idTipoOrden =    "
                + getIdTipoOrden() + "                                  "
                + "       AND   tblDctosOrdenesDetalle.idOrden     =    "
                + getIdOrden() + "                                      "
                + "       AND   tblDctosOrdenesDetalle.estado      != 4	"
                + " AND tblDctosOrdenesDetalle.idBodega =               "
                + xIdBodega +   "                                       "
                + "       GROUP BY tblDctosOrdenesDetalle.idLocal,      "
                + "                tblDctosOrdenesDetalle.idPlu,      	"
                + "                tblDctosOrdenesDetalle.idBodega)    	"
                + "                                          AS tmpDOR	"
                + "       WHERE tblPlus.idPlu  = 		        "
                + "                            tmpDOR.idPlu ) AS tmpPLU	"
                + "   WHERE tmpPLU.idPlu       =  		        "
                + "                             tblPlusCombo.idPluCombo	"
                + "UNION			                        "
                + "SELECT tblDctosOrdenesDetalle.idLocal,  	        "
                + "       tblDctosOrdenesDetalle.idPlu,        	        "
                + "       tblDctosOrdenesDetalle.idBodega,             	"
                + "       SUM(tblDctosOrdenesDetalle.cantidad) 	    	"
                + "                        AS cantidad 			"
                + "FROM tblDctosOrdenesDetalle                 	     	"
                + "WHERE tblDctosOrdenesDetalle.idLocal            =    "
                + getIdLocal() + "                                      "
                + "       AND   tblDctosOrdenesDetalle.idTipoOrden =    "
                + getIdTipoOrden() + "                                  "
                + "       AND   tblDctosOrdenesDetalle.idOrden     =    "
                + getIdOrden() + "                                      "
                + "AND   tblDctosOrdenesDetalle.estado            != 4	"                
                + " AND tblDctosOrdenesDetalle.idBodega =               "
                + xIdBodega +   "                                       "
                + "AND   NOT EXISTS 				        "
                + "      ( SELECT tblPlus.*			        "
                + "        FROM tblPlus				        "
                + "        WHERE tblPlus.idPlu = 			"
                + "                   tblDctosOrdenesDetalle.idPlu     	"
                + "        AND tblPlus.idTipo  = 2 )			"
                + "GROUP BY tblDctosOrdenesDetalle.idLocal,      	"
                + "         tblDctosOrdenesDetalle.idPlu,      	        "
                + "         tblDctosOrdenesDetalle.idBodega)          	"
                + "AS tmpTOT					        "
                + "GROUP BY tmpTOT.idLocal,			        "
                + "         tmpTOT.idPlu,                               "
                + "         tmpTOT.idBodega ) AS tmpINV		        "
                + "WHERE tmpINV.idPlu       = tblPlusInventario.idPlu	"
                + "AND   tmpINV.idLocal     = tblPlusInventario.idLocal	"
                + "AND   tmpINV.idBodega    = tblPlusInventario.idBodega";


        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            selectStatement.execute();
            okActualizarDctoOrdenFactura = true;


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

            // Cierra el Resultset
            jdbcAccess.cleanup(connection, selectStatement, null);
            return okActualizarDctoOrdenFactura;
        }
    }
    public boolean actualizaInventarioEstadoTerminado(int xIdBodega) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okActualizarDctoOrdenFactura = false;

        //
        Connection connection = null;

        String selectStr =
                "UPDATE tblPlusInventario                               "
                + "SET    tblPlusInventario.existencia       =      	"
                + "     ( tmpINV.cantidad *                      	"
                + "           ( SELECT tblTipoOrden.signo               "
                + "             FROM tblTipoOrden                       "
                + "             WHERE tblTipoOrden.idTipoOrden   =      "
                + getIdTipoOrden() + " ) )                              "
                + "                    + tblPlusInventario.existencia   "
                + "             FROM tblPlusInventario,	   		"
                + "                (					"
                + "SELECT tmpTOT.idLocal,				"
                + "       tmpTOT.idPlu,			                "
                + "       tmpTOT.idBodega,    	                    	"
                + "       SUM(tmpTOT.cantidad) 			        "
                + "                  AS cantidad		        "
                + "FROM (SELECT tmpPLU.idLocal,			        "
                + "             tblPlusCombo.idPlu,			"
                + "             tmpPLU.idBodega,	                "
                + "            (tblPlusCombo.cantidad *		        "
                + "                      tmpPLU.cantidad ) AS cantidad	"
                + "      FROM tblPlusCombo,				"
                + " ( SELECT tblPlus.idTipo,			        "
                + "          tmpDOR.*				        "
                + "   FROM tblPlus,(					"
                + "       SELECT tblDctosOrdenesDetalle.idLocal,      	"
                + "              tblDctosOrdenesDetalle.idPlu,        	"
                + "              tblDctosOrdenesDetalle.idBodega,     	"
                + "          SUM(tblDctosOrdenesDetalle.pesoTerminado) 	"
                + "                                       AS cantidad 	"
                + "       FROM tblDctosOrdenesDetalle                 	"
                + "       WHERE tblDctosOrdenesDetalle.idLocal     =    "
                + getIdLocal() + "                                      "
                + "       AND   tblDctosOrdenesDetalle.idTipoOrden =    "
                + getIdTipoOrden() + "                                  "
                + "       AND   tblDctosOrdenesDetalle.idOrden     =    "
                + getIdOrden() + "                                      "
                + "       AND   tblDctosOrdenesDetalle.estado      != 4	"
                + " AND tblDctosOrdenesDetalle.idBodega =               "
                + xIdBodega +   "                                       "
                + "       GROUP BY tblDctosOrdenesDetalle.idLocal,      "
                + "                tblDctosOrdenesDetalle.idPlu,      	"
                + "                tblDctosOrdenesDetalle.idBodega)    	"
                + "                                          AS tmpDOR	"
                + "       WHERE tblPlus.idPlu  = 		        "
                + "                            tmpDOR.idPlu ) AS tmpPLU	"
                + "   WHERE tmpPLU.idPlu       =  		        "
                + "                             tblPlusCombo.idPluCombo	"
                + "UNION			                        "
                + "SELECT tblDctosOrdenesDetalle.idLocal,  	        "
                + "       tblDctosOrdenesDetalle.idPlu,        	        "
                + "       tblDctosOrdenesDetalle.idBodega,             	"
                + "       SUM(tblDctosOrdenesDetalle.pesoTerminado)   	"
                + "                        AS cantidad 			"
                + "FROM tblDctosOrdenesDetalle                 	     	"
                + "WHERE tblDctosOrdenesDetalle.idLocal            =    "
                + getIdLocal() + "                                      "
                + "       AND   tblDctosOrdenesDetalle.idTipoOrden =    "
                + getIdTipoOrden() + "                                  "
                + "       AND   tblDctosOrdenesDetalle.idOrden     =    "
                + getIdOrden() + "                                      "
                + "AND   tblDctosOrdenesDetalle.estado            != 4	"                
                + " AND tblDctosOrdenesDetalle.idBodega =               "
                + xIdBodega +   "                                       "
                + "AND   NOT EXISTS 				        "
                + "      ( SELECT tblPlus.*			        "
                + "        FROM tblPlus				        "
                + "        WHERE tblPlus.idPlu = 			"
                + "                   tblDctosOrdenesDetalle.idPlu     	"
                + "        AND tblPlus.idTipo  = 2 )			"
                + "GROUP BY tblDctosOrdenesDetalle.idLocal,      	"
                + "         tblDctosOrdenesDetalle.idPlu,      	        "
                + "         tblDctosOrdenesDetalle.idBodega)          	"
                + "AS tmpTOT					        "
                + "GROUP BY tmpTOT.idLocal,			        "
                + "         tmpTOT.idPlu,                               "
                + "         tmpTOT.idBodega ) AS tmpINV		        "
                + "WHERE tmpINV.idPlu       = tblPlusInventario.idPlu	"
                + "AND   tmpINV.idLocal     = tblPlusInventario.idLocal	"
                + "AND   tmpINV.idBodega    = tblPlusInventario.idBodega";


        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            selectStatement.execute();
            okActualizarDctoOrdenFactura = true;


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

            // Cierra el Resultset
            jdbcAccess.cleanup(connection, selectStatement, null);
            return okActualizarDctoOrdenFactura;
        }
    }

    // ingresaBodega
    public boolean ingresaBodega() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        //
        boolean okIngresar = false;

        //
        Connection connection = null;

        String selectStr =
                "INSERT INTO tblPlusInventario         "
                + "           ( idLocal                  "
                + "           , idPlu                    "
                + "           , idBodega                 "
                + "           , existencia               "
                + "           , idTipoOrden              "
                + "           , idOrden                  "
                + "           , cantidadOrden            "
                + "           , estado )                 "
                + "SELECT tblLocales.idLocal,            "
                + "       tblPlus.idPlu,                 "
                + "       tblLocalesBodega.idBodega,     "
                + "       00 AS existencia,              "
                + "       00 as idTipoOrden,             "
                + "       00 AS idOrden,                 "
                + "       00 AS cantidad,                "
                + "       01 AS estado                   "
                + "FROM   tblLocales CROSS JOIN tblPlus  "
                + "         CROSS JOIN tblLocalesBodega  "
                + "WHERE NOT EXISTS                      "
                + "(SELECT [tblPlusInventario].*         "
                + " FROM [tblPlusInventario]             "
                + " WHERE [tblPlusInventario].idLocal  = "
                + "            tblLocalesBodega.idLocal  "
                + " AND   [tblPlusInventario].idPlu    = "
                + "                       tblPlus.idPlu  "
                + " AND   [tblPlusInventario].idBodega = "
                + "           tblLocalesBodega.idBodega) "
                + "AND (tblLocales.estado=1)             ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);


            // Obtiene el resultset
            selectStatement.execute();

            okIngresar = true;

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

    // ajustaInventario
    public boolean ajustaInventario() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okActualizarDctoOrdenFactura = false;

        //
        Connection connection = null;

        String selectStr =
                "UPDATE tblPlusInventario                       "
                + "SET  tblPlusInventario.existencia       =      "
                + "     tblPlusInventario.existencia       +      "
                + "     tmpDOR.cantidad *                      	"
                + "     ( SELECT tblTipoOrden.signo               "
                + "       FROM tblTipoOrden                       "
                + "       WHERE tblTipoOrden.idTipoOrden   =      "
                + getIdTipoOrden() + " )                          "
                + "FROM tblPlusInventario,			            "
                + " (SELECT  tblDctosOrdenesDetalle.idPlu,        "
                + "          ( tblDctosOrdenesDetalle.CANTIDAD  - "
                + "       tblDctosOrdenesDetalle.CANTIDADPEDIDA ) "
                + "                                   AS CANTIDAD "
                + "  FROM  tblDctosOrdenesDetalle                 "
                + "  WHERE tblDctosOrdenesDetalle.IDLOCAL     =   "
                + getIdLocal() + "                                "
                + "  AND   tblDctosOrdenesDetalle.IDTIPOORDEN =   "
                + getIdTipoOrden() + "                            "
                + "  AND  tblDctosOrdenesDetalle.IDORDEN     =    "
                + getIdOrden() + "                                "
                + "AND   tblDctosOrdenesDetalle.estado      != 4) "
                + "                                AS tmpDOR	    "
                + "WHERE tblPlusInventario.idPlu             =    "
                + "                                 tmpDOR.idPlu  ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            selectStatement.execute();
            okActualizarDctoOrdenFactura = true;


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

            // Cierra el Resultset
            jdbcAccess.cleanup(connection, selectStatement, null);
            return okActualizarDctoOrdenFactura;
        }
    }

    // actualizaInventario
    public boolean actualizaInventario() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okActualizarDctoOrdenFactura = false;

        //
        Connection connection = null;

        String selectStr =
                "UPDATE tblPlusInventario                       "
                + "SET  tblPlusInventario.existencia       =      "
                + "     tmpDOR.cantidad *                      	"
                + "     ( SELECT tblTipoOrden.signo               "
                + "       FROM tblTipoOrden                       "
                + "       WHERE tblTipoOrden.idTipoOrden   =      "
                + getIdTipoOrden() + " )                          "
                + "       + tblPlusInventario.existencia          "
                + "FROM tblPlusInventario,			            "
                + "( SELECT tmpPLU.idLocal,				        "
                + "         tblPlusCombo.idPlu,			        "
                + "         (tblPlusCombo.cantidad *		        "
                + "                tmpPLU.cantidad )		        "
                + "                    AS cantidad		        "
                + "FROM tblPlusCombo,				                "
                + "( SELECT tblPlus.idTipo,			            "
                + "       tmpDOR.*				                "
                + "FROM tblPlus,(					                "
                + "SELECT tblDctosOrdenesDetalle.idLocal,      	"
                + "       tblDctosOrdenesDetalle.idPlu,        	"
                + "       tblDctosOrdenesDetalle.idBodega,  	    "
                + "       SUM(tblDctosOrdenesDetalle.cantidad) 	"
                + "                               AS cantidad 	"
                + "FROM tblDctosOrdenesDetalle                 	"
                + "WHERE tblDctosOrdenesDetalle.idLocal     =    	"
                + getIdLocal() + "                                "
                + "AND   tblDctosOrdenesDetalle.idTipoOrden =   	"
                + getIdTipoOrden() + "                            "
                + "AND   tblDctosOrdenesDetalle.idOrden     =    	"
                + getIdOrden() + "                                "
                + "AND   tblDctosOrdenesDetalle.estado      != 4	"
                + "GROUP BY tblDctosOrdenesDetalle.idPlu,      	"
                + "         tblDctosOrdenesDetalle.idBodega,    	"
                + "         tblDctosOrdenesDetalle.idLocal)	    "
                + "                              AS tmpDOR	    "
                + "WHERE tblPlus.idPlu  = 			            "
                + "               tmpDOR.idPlu ) AS tmpPLU	    "
                + "WHERE tmpPLU.idPlu   = 			            "
                + "                tblPlusCombo.idPluCombo	    "
                + "UNION						                    "
                + "SELECT tblDctosOrdenesDetalle.idLocal,      	"
                + "       tblDctosOrdenesDetalle.idPlu,        	"
                + "       tblDctosOrdenesDetalle.idBodega,    	"
                + "       SUM(tblDctosOrdenesDetalle.cantidad) 	"
                + "                                AS cantidad 	"
                + "FROM tblDctosOrdenesDetalle                 	"
                + "WHERE tblDctosOrdenesDetalle.idLocal     =    	"
                + getIdLocal() + "                                "
                + "AND   tblDctosOrdenesDetalle.idTipoOrden =   	"
                + getIdTipoOrden() + "                            "
                + "AND   tblDctosOrdenesDetalle.idOrden     =    	"
                + getIdOrden() + "                                "
                + "AND   tblDctosOrdenesDetalle.estado      != 4	"
                + "AND   NOT EXISTS 				                "
                + "   ( SELECT tblPlus.*			              	"
                + "     FROM tblPlus				                "
                + "     WHERE tblPlus.idPlu = 			        "
                + "          tblDctosOrdenesDetalle.idPlu		    "
                + "     AND tblPlus.idTipo  = 2 )			        "
                + "GROUP BY tblDctosOrdenesDetalle.idPlu,      	"
                + "         tblDctosOrdenesDetalle.idBodega,    	"
                + "         tblDctosOrdenesDetalle.idLocal)       "
                + "                          AS tmpDOR		    "
                + "WHERE tmpDOR.idPlu       = 	   			    "
                + "                      tblPlusInventario.idPlu  "
                + "AND   tmpDOR.idLocal     = 				    "
                + "                    tblPlusInventario.idLocal  "
                + "AND   tmpDOR.idBodega    = 				    "
                + "                    tblPlusInventario.idBodega ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            selectStatement.execute();
            okActualizarDctoOrdenFactura = true;


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

            // Cierra el Resultset
            jdbcAccess.cleanup(connection, selectStatement, null);
            return okActualizarDctoOrdenFactura;
        }
    }

    // ingresa
    public boolean ingresa() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        //
        boolean okIngresar = false;

        //
        Connection connection = null;

        String selectStr =
                "INSERT INTO tblPlusInventario   "
                + "           (idLocal             "
                + "           ,idBodega            "
                + "           ,idPlu               "
                + "           ,existencia          "
                + "           ,idTipoOrden         "
                + "           ,idOrden             "
                + "           ,cantidadOrden       "
                + "           ,estado)             "
                + "SELECT tblLocalesBodega.idLocal,"
                + "      tblLocalesBodega.idBodega,"
                + "       tblPlus.idPlu,           "
                + "       0.0 AS existencia,       "
                + "       0  AS idTipoOrden,       "
                + "       0  AS idOrden,           "
                + "       0.0 AS cantidadOrden,    "
                + "       01 AS estado             "
                + "FROM tblPlus,                   "
                + "     tblLocalesBodega           "
                + "WHERE NOT EXISTS                "
                + "  (SELECT tblPlusInventario.*   "
                + "   FROM tblPlusInventario       "
                + "   WHERE tblPlus.idPlu =        "
                + "       tblPlusInventario.idPlu  "
                + " AND tblLocalesBodega.idBodega= "
                + "   tblPlusInventario.idBodega ) ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);


            // Obtiene el resultset
            selectStatement.execute();

            okIngresar = true;

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

    // Metodo listaUnFCH
    public FachadaPluInventarioBean listaUnFCH() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        FachadaPluInventarioBean fachadaPluInventarioBean = new FachadaPluInventarioBean();
        Connection connection = null;

        String insertStr =
                "SELECT tblPlusInventario,idLocal,       "
                + "       tblPlusInventario.idPlu,         "
                + "       tblPlusInventario.existencia,    "
                + "       tblPlusInventario.idTipoOrden,   "
                + "       tblPlusInventario.idOrden,       "
                + "       tblPlusInventario.cantidadOrden, "
                + "       tblPlusInventario.estado         "
                + "FROM tblPlusInventario                  "
                + "WHERE tblPlusInventario,idLocal =       "
                + getIdLocal() + "                         "
                + "AND   tblPlusInventario.idPlu   =       "
                + getIdPlu();

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

                //
                fachadaPluInventarioBean.setIdLocal(rs.getInt("idLocal"));
                fachadaPluInventarioBean.setIdPlu(rs.getInt("idPlu"));
                fachadaPluInventarioBean.setExistencia(
                        rs.getDouble("existencia"));
                fachadaPluInventarioBean.setIdTipoOrden(
                        rs.getInt("idTipoOrden"));
                fachadaPluInventarioBean.setIdOrden(rs.getInt("idOrden"));
                fachadaPluInventarioBean.setCantidadOrden(
                        rs.getDouble("cantidadOrden"));
                fachadaPluInventarioBean.setEstado(rs.getInt("estado"));

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
            return fachadaPluInventarioBean;
        }
    }
}
