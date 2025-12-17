package com.solucionesweb.losbeans.colaboraciones;

// Importa la clase helper JDBCAccess que contiene los accesos a la base de datos
import com.solucionesweb.lasayudas.JDBCAccess;

// Importa el bean de utilidades
import com.solucionesweb.losbeans.utilidades.IConstantes;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaDctoOrdenDetalleBean;

// Importa los paquetes de java
import java.sql.*;
import java.io.*;
import java.io.IOException.*;

//import java.util.Date;
import java.util.Vector;
import java.io.IOException.*;

// Importa los paquetes de javax
import javax.naming.*;

public class ColaboraResurtido extends FachadaDctoOrdenDetalleBean
        implements Serializable, IConstantes {

    // Nombre JNDI del Data Source que esta clase requiere
    private static final String DATA_SOURCE_NAME = "CommerceAccess";
    // Clase helper usada para acceder a la base de datos
    private JDBCAccess jdbcAccess;

    // Metodo constructor por defecto sin parametros
    public ColaboraResurtido() {

        // Al instanciar el Bean establece la conexion a la base de datos
        jdbcAccess = new JDBCAccess(DATA_SOURCE_NAME);
    }

    // listaPVD
    public Vector listaPVD(String xFechaInicial,
            String xFechaFinal,
            int xDiasHistoria,
            int xDiasInventario) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        //
        Vector contenedor = new Vector();

        //
        int xIdTipoOrden = 0;
        double xCantidad = 0;

        //
        int xIdTipoOrdenVenta = 9;
        int xIdTipoOrdenDespacho = 5;
        int xIdTipoOrdenTraslado = 2;
        int xIdTipoOrdenCompra = 1;

        Connection connection = null;

        //
        String selectStr =
                "SELECT tmpRES.idLocal,                         "
                + "       tmpRES.idPlu,                         "
                + "       tmpRES.idTipoOrden,			"
                + "       SUM(tmpRES.cantidad) AS cantidad	"
                + "FROM ( SELECT TOP 1 tmpDET.idLocal,	    	"
                + "        tmpDET.idLog,			"
                + "        (SELECT TOP 1 tblPlusCombo.idPlu	"
                + "         FROM   tblPlusCombo		        "
                + "         WHERE  tmpDET.idPlu =		"
                + "                 tblPlusCombo.idPluCombo)    "
                + "                               AS idPlu,	"
                + "         tmpDET.idTipoOrden,			"
                + "         ( tmpDET.cantidad * 		"
                + "         (SELECT TOP 1 tblPlusCombo.cantidad "
                + "          FROM   tblPlusCombo		"
                + "          WHERE  tmpDET.idPlu =		"
                + "                 tblPlusCombo.idPluCombo)) 	"
                + "                             AS cantidad	"
                + "  FROM 					"
                + " (SELECT  tblDctosOrdenes.idLocal, 	 	"
                + "          tblDctosOrdenes.idLog,	        "
                + "          tblDctosOrdenesDetalle.idPlu,     	"
                + "          9 AS idTipoOrden,		        "
                + "        SUM(tblDctosOrdenesDetalle.cantidad) "
                + "                                 AS cantidad "
                + " FROM    tblDctosOrdenes		        "
                + " INNER JOIN tblDctosOrdenesDetalle	        "
                + " ON tblDctosOrdenes.IDLOCAL = 		"
                + "     tblDctosOrdenesDetalle.IDLOCAL	        "
                + " AND tblDctosOrdenes.IDTIPOORDEN = 	        "
                + "  tblDctosOrdenesDetalle.IDTIPOORDEN	      	"
                + " AND tblDctosOrdenes.IDORDEN     = 	        "
                + "      tblDctosOrdenesDetalle.IDORDEN	      	"
                + "WHERE tblDctosOrdenes.IDLOCAL   =   	        "
                + getIdLocal() + "                              "
                + "AND tblDctosOrdenes.FECHAORDEN 	        "
                + "                 BETWEEN                    '"
                + xFechaInicial + "'    AND                    '"
                + xFechaFinal + "'                              "
                + " AND tblDctosOrdenes.IDTIPOORDEN IN (9,29)  	"
                + " AND tblDctosOrdenesDetalle.idTipo = 2	"
                + " GROUP BY tblDctosOrdenes.idLocal,	        "
                + "          tblDctosOrdenes.idLog,	        "
                + "          tblDctosOrdenesDetalle.idPlu      	"
                + " UNION 					"
                + " SELECT  tblDctosOrdenes.idLocal, 	        "
                + "         tblDctosOrdenes.idLog,	        "
                + "         tblDctosOrdenesDetalle.idPlu,      	"
                + "         5 AS idTipoOrden,		        "
                + "       SUM(tblDctosOrdenesDetalle.cantidad) 	"
                + "                                 AS cantidad "
                + " FROM    tblDctosOrdenes		        "
                + " INNER JOIN tblDctosOrdenesDetalle	        "
                + " ON tblDctosOrdenes.IDLOCAL = 		"
                + "     tblDctosOrdenesDetalle.IDLOCAL	        "
                + " AND tblDctosOrdenes.IDTIPOORDEN = 	        "
                + "  tblDctosOrdenesDetalle.IDTIPOORDEN	      	"
                + " AND tblDctosOrdenes.IDORDEN     = 	        "
                + "      tblDctosOrdenesDetalle.IDORDEN	      	"
                + "WHERE tblDctosOrdenes.IDLOCAL   =   	        "
                + getIdLocal() + "                              "
                + "AND tblDctosOrdenes.FECHAORDEN 	        "
                + "                 BETWEEN                    '"
                + xFechaInicial + "'    AND                    '"
                + xFechaFinal + "'                              "
                + " AND tblDctosOrdenes.IDTIPOORDEN IN (55)    	"
                + " AND tblDctosOrdenesDetalle.idTipo = 2  	"
                + " GROUP BY tblDctosOrdenes.idLocal,	        "
                + "          tblDctosOrdenes.idLog,	        "
                + "          tblDctosOrdenesDetalle.idPlu      	"
                + " UNION			  	        "
                + " SELECT  tblDctosOrdenes.idLocal, 	        "
                + "         tblDctosOrdenes.idLog,	        "
                + "         tblDctosOrdenesDetalle.idPlu,	"
                + "         2 AS idTipoOrden,		        "
                + "      SUM(tblDctosOrdenesDetalle.cantidad) 	"
                + "                               AS cantidad  	"
                + " FROM    tblDctosOrdenes		        "
                + " INNER JOIN tblDctosOrdenesDetalle	        "
                + " ON tblDctosOrdenes.IDLOCAL = 		"
                + "     tblDctosOrdenesDetalle.IDLOCAL	        "
                + " AND tblDctosOrdenes.IDTIPOORDEN = 	        "
                + "  tblDctosOrdenesDetalle.IDTIPOORDEN	      	"
                + " AND tblDctosOrdenes.IDORDEN     = 	        "
                + "      tblDctosOrdenesDetalle.IDORDEN	      	"
                + "WHERE tblDctosOrdenes.IDLOCAL   =   	        "
                + getIdLocal() + "                              "
                + "AND tblDctosOrdenes.FECHAORDEN 	        "
                + "                 BETWEEN                    '"
                + xFechaInicial + "'    AND                    '"
                + xFechaFinal + "'                              "
                + " AND tblDctosOrdenes.IDTIPOORDEN IN (52)    	"
                + " AND tblDctosOrdenesDetalle.idTipo = 2  	"
                + " GROUP BY tblDctosOrdenes.idLocal,	        "
                + "          tblDctosOrdenes.idLog,	        "
                + "          tblDctosOrdenesDetalle.idPlu      	"
                + " UNION	                     	        "
                + " SELECT  tblDctosOrdenes.idLocal, 	        "
                + "         tblDctosOrdenes.idLog,	        "
                + "         tblDctosOrdenesDetalle.idPlu,      	"
                + "         1 AS idTipoOrden,		        "
                + "       SUM(tblDctosOrdenesDetalle.cantidad) 	"
                + "                                 AS cantidad	"
                + " FROM    tblDctosOrdenes		        "
                + " INNER JOIN tblDctosOrdenesDetalle	        "
                + " ON tblDctosOrdenes.IDLOCAL = 		"
                + "     tblDctosOrdenesDetalle.IDLOCAL	        "
                + " AND tblDctosOrdenes.IDTIPOORDEN = 	        "
                + "  tblDctosOrdenesDetalle.IDTIPOORDEN	      	"
                + " AND tblDctosOrdenes.IDORDEN     = 	        "
                + "      tblDctosOrdenesDetalle.IDORDEN	      	"
                + "WHERE tblDctosOrdenes.IDLOCAL   =   	        "
                + getIdLocal() + "                              "
                + "AND tblDctosOrdenes.FECHAORDEN 	        "
                + "                 BETWEEN                    '"
                + xFechaInicial + "'    AND                    '"
                + xFechaFinal + "'                              "
                + " AND tblDctosOrdenes.IDTIPOORDEN IN (51)    	"
                + " AND tblDctosOrdenesDetalle.idTipo = 2  	"
                + " GROUP BY tblDctosOrdenes.idLocal,	        "
                + "          tblDctosOrdenes.idLog,	        "
                + "          tblDctosOrdenesDetalle.idPlu)    	"
                + "                              AS tmpDET    	"
                + " UNION			               	"
                + " SELECT tmpDET.idLocal,		        "
                + "         tmpDET.idLog,		        "
                + "         tmpDET.idPlu,		        "
                + "         tmpDET.idTipoOrden,		        "
                + "         tmpDET.cantidad		        "
                + "  FROM 				        "
                + " (SELECT  tblDctosOrdenes.idLocal, 	    	"
                + "          tblDctosOrdenes.idLog,	        "
                + "          tblDctosOrdenesDetalle.idPlu,    	"
                + "          9 AS idTipoOrden,		        "
                + "        SUM(tblDctosOrdenesDetalle.cantidad) "
                + "                                AS cantidad  "
                + " FROM    tblDctosOrdenes		        "
                + " INNER JOIN tblDctosOrdenesDetalle	        "
                + " ON tblDctosOrdenes.IDLOCAL = 		"
                + "     tblDctosOrdenesDetalle.IDLOCAL	        "
                + " AND tblDctosOrdenes.IDTIPOORDEN = 	        "
                + "  tblDctosOrdenesDetalle.IDTIPOORDEN	      	"
                + " AND tblDctosOrdenes.IDORDEN     = 	        "
                + "      tblDctosOrdenesDetalle.IDORDEN	      	"
                + "WHERE tblDctosOrdenes.IDLOCAL   =   	        "
                + getIdLocal() + "                              "
                + "AND tblDctosOrdenes.FECHAORDEN 	        "
                + "                 BETWEEN                    '"
                + xFechaInicial + "'    AND                    '"
                + xFechaFinal + "'                              "
                + " AND tblDctosOrdenes.IDTIPOORDEN IN (9,29)  	"
                + " AND tblDctosOrdenesDetalle.idTipo = 1   	"
                + " GROUP BY tblDctosOrdenes.idLocal,	        "
                + "          tblDctosOrdenes.idLog,	        "
                + "          tblDctosOrdenesDetalle.idPlu	"
                + " UNION 		                    	"
                + " SELECT  tblDctosOrdenes.idLocal, 	        "
                + "         tblDctosOrdenes.idLog,	        "
                + "         tblDctosOrdenesDetalle.idPlu,	"
                + "         5 AS idTipoOrden,		        "
                + "        SUM(tblDctosOrdenesDetalle.cantidad) "
                + "                                 AS cantidad "
                + " FROM    tblDctosOrdenes		        "
                + " INNER JOIN tblDctosOrdenesDetalle	        "
                + " ON tblDctosOrdenes.IDLOCAL = 		"
                + "     tblDctosOrdenesDetalle.IDLOCAL	        "
                + " AND tblDctosOrdenes.IDTIPOORDEN = 	        "
                + "  tblDctosOrdenesDetalle.IDTIPOORDEN	      	"
                + " AND tblDctosOrdenes.IDORDEN     = 	        "
                + "      tblDctosOrdenesDetalle.IDORDEN	      	"
                + "WHERE tblDctosOrdenes.IDLOCAL   =   	        "
                + getIdLocal() + "                              "
                + "AND tblDctosOrdenes.FECHAORDEN 	        "
                + "                 BETWEEN                    '"
                + xFechaInicial + "'    AND                    '"
                + xFechaFinal + "'                              "
                + " AND tblDctosOrdenes.IDTIPOORDEN IN (55)    	"
                + " AND tblDctosOrdenesDetalle.idTipo = 1     	"
                + " GROUP BY tblDctosOrdenes.idLocal,	   	"
                + "          tblDctosOrdenes.idLog,	        "
                + "          tblDctosOrdenesDetalle.idPlu      	"
                + " UNION			  	        "
                + " SELECT  tblDctosOrdenes.idLocal, 	        "
                + "         tblDctosOrdenes.idLog,	        "
                + "         tblDctosOrdenesDetalle.idPlu,      	"
                + "         2 AS idTipoOrden,		        "
                + "       SUM(tblDctosOrdenesDetalle.cantidad) 	"
                + "                               AS cantidad  	"
                + " FROM    tblDctosOrdenes		        "
                + " INNER JOIN tblDctosOrdenesDetalle	        "
                + " ON tblDctosOrdenes.IDLOCAL = 		"
                + "     tblDctosOrdenesDetalle.IDLOCAL	        "
                + " AND tblDctosOrdenes.IDTIPOORDEN = 	        "
                + "  tblDctosOrdenesDetalle.IDTIPOORDEN	      	"
                + " AND tblDctosOrdenes.IDORDEN     = 	        "
                + "      tblDctosOrdenesDetalle.IDORDEN	      	"
                + "WHERE tblDctosOrdenes.IDLOCAL   =   	        "
                + getIdLocal() + "                              "
                + "AND tblDctosOrdenes.FECHAORDEN 	        "
                + "                 BETWEEN                    '"
                + xFechaInicial + "'    AND                    '"
                + xFechaFinal + "'                              "
                + " AND tblDctosOrdenes.IDTIPOORDEN IN (52)    	"
                + " AND tblDctosOrdenesDetalle.idTipo = 1   	"
                + " GROUP BY tblDctosOrdenes.idLocal,	        "
                + "          tblDctosOrdenes.idLog,	        "
                + "          tblDctosOrdenesDetalle.idPlu      	"
                + " UNION	                     	        "
                + " SELECT  tblDctosOrdenes.idLocal, 	        "
                + "         tblDctosOrdenes.idLog,	        "
                + "         tblDctosOrdenesDetalle.idPlu,      	"
                + "         1 AS idTipoOrden,		        "
                + "       SUM(tblDctosOrdenesDetalle.cantidad) 	"
                + "                                 AS cantidad	"
                + " FROM    tblDctosOrdenes		        "
                + " INNER JOIN tblDctosOrdenesDetalle	        "
                + " ON tblDctosOrdenes.IDLOCAL = 		"
                + "     tblDctosOrdenesDetalle.IDLOCAL	        "
                + " AND tblDctosOrdenes.IDTIPOORDEN = 	        "
                + "  tblDctosOrdenesDetalle.IDTIPOORDEN	      	"
                + " AND tblDctosOrdenes.IDORDEN     = 	        "
                + "      tblDctosOrdenesDetalle.IDORDEN	      	"
                + "WHERE tblDctosOrdenes.IDLOCAL   =   	        "
                + getIdLocal() + "                              "
                + "AND tblDctosOrdenes.FECHAORDEN 	        "
                + "                 BETWEEN                    '"
                + xFechaInicial + "'    AND                    '"
                + xFechaFinal + "'                              "
                + " AND tblDctosOrdenes.IDTIPOORDEN IN (51)    	"
                + " AND tblDctosOrdenesDetalle.idTipo = 1    	"
                + " GROUP BY tblDctosOrdenes.idLocal,	        "
                + "          tblDctosOrdenes.idLog,	        "
                + "          tblDctosOrdenesDetalle.idPlu)    	"
                + "                                 AS tmpDET   "
                + " WHERE EXISTS ( 			        "
                + "   SELECT tblAgendaLogVisitas.*	        "
                + "   FROM tblAgendaLogVisitas		        "
                + "   WHERE tblAgendaLogVisitas.idLog =	    	"
                + "                              tmpDET.idLog 	"
                + "   AND   tblAgendaLogVisitas.estado= 8)      "
                + " OR tmpDET.IDTIPOORDEN IN (9,29))  AS tmpRES	"
                + "WHERE  tmpRES.idPlu                =         "
                + getIdPlu() + "                                "
                + "GROUP BY tmpRES.idLocal,			"
                + "         tmpRES.idPlu,	                "
                + "         tmpRES.idTipoOrden		        ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaDctoOrdenDetalleBean fachadaBean
                                            = new FachadaDctoOrdenDetalleBean();

            //
            while (rs.next()) {



                //
                xIdTipoOrden = rs.getInt("idTipoOrden");
                xCantidad = rs.getDouble("cantidad");

                //
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdPlu(rs.getInt("idPlu"));

                //
                if (xIdTipoOrden == xIdTipoOrdenVenta) {

                    //
                    fachadaBean.setAcumuladoVenta(xCantidad);

                }

                //
                if (xIdTipoOrden == xIdTipoOrdenDespacho) {

                    //
                    fachadaBean.setCantidadDespachoPendiente(xCantidad);

                }

                //
                if (xIdTipoOrden == xIdTipoOrdenTraslado) {

                    //
                    fachadaBean.setCantidadTrasladoPendiente(xCantidad);

                }

                //
                if (xIdTipoOrden == xIdTipoOrdenCompra) {

                    //
                    fachadaBean.setCantidadCompraPendiente(xCantidad);

                }
            }

            //
            contenedor.add(fachadaBean);

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

    // listaResurtidoEmpresa
    public Vector listaResurtidoEmpresa() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        //
        Vector contenedor = new Vector();

        Connection connection = null;

        //
        String selectStr =
                " SELECT  tblDctosOrdenesDetalle.idLocal,       "
                + "         tblDctosOrdenesDetalle.idTipoOrden, "
                + "         tblDctosOrdenesDetalle.idOrden,     "
                + "         tblDctosOrdenesDetalle.idPlu,       "
                + "      tblDctosOrdenesDetalle.cantidadPedida, "
                + "     tblDctosOrdenesDetalle.porcentajeIva,   "
                + "  tblDctosOrdenesDetalle.cantidadBonificada, "
                + "         tblDctosOrdenesDetalle.vrCosto,     "
                + "         tblDctosOrdenesDetalle.vrCostoIND,  "
                + "    tblDctosOrdenesDetalle.vrCostoNegociado, "
                + "         tblDctosOrdenesDetalle.nombrePlu,   "
                + "         ISNULL ((SELECT tmpALL.existencia   "
                + " 	FROM ( SELECT tmpINV.IDPLU,	        "
                + " 	       SUM(tmpINV.existencia) 	        "
                + " 	                AS existencia	        "
                + " 	FROM (				        "
                + " 	SELECT tmpResurtido.IDLOCAL	        "
                + " 	      ,tmpResurtido.IDPLU	        "
                + " 	   ,MAX(tmpResurtido.existencia)        "
                + " 	                  AS existencia	        "
                + " 	  FROM tmpResurtido		        "
                + " 	GROUP BY tmpResurtido.IDLOCAL	        "
                + " 	         ,tmpResurtido.IDPLU )	        "
                + " 	                      AS tmpINV	        "
                + " 	GROUP BY tmpINV.IDPLU ) 	        "
                + "                       AS tmpALL	        "
                + "         WHERE tmpALL.idPlu =                "
                + "            tblDctosOrdenesDetalle.IDPLU),0) "
                + "                           AS existencia,    "
                + " ( SELECT tblPlus.factorDespacho             "
                + "   FROM tblPlus                              "
                + "   WHERE tblPlus.idPlu =                     "
                + "              tblDctosOrdenesDetalle.idPlu ) "
                + "                          AS factorDespacho, "
                + "   ( SELECT TOP 1 tmpResurtido.existencia    "
                + "                       AS existenciaBodega   "
                + "   FROM tmpResurtido		                "
                + "   WHERE tmpResurtido.IDLOCAL            =   "
                + getIdLocal() + "                              "
                + "   AND tmpResurtido.IDPLU                =   "
                + "             tblDctosOrdenesDetalle.IDPLU )  "
                + "                        AS existenciaBodega, "
                + " ISNULL ((SELECT SUM(tmpResurtido.cantidad)/ "
                + "   	           tmpResurtido.diasHistoria    "
                + " 	                      AS cantidadPvd    "
                + " 	  FROM tmpResurtido	                "
                + " 	  WHERE tmpResurtido.idTipoOrden        "
                + " 	                       IN (9,29,5)	"
                + " 	  AND tmpResurtido.idPlu            =   "
                + "             tblDctosOrdenesDetalle.IDPLU    " 
                + "    GROUP BY tmpResurtido.diasHistoria ),0)  "
                + "                              AS cantidadPvd,"
                + " ( SELECT SUM( tmpDPL.cantidadPedida)        "
                + "                 AS cantidadCompraPendiente  "
                + "   FROM tblDctosOrdenesDetalle tmpDPL        "
                + "   WHERE tmpDPL.idLocal                   =  " 
                + getIdLocal() + "                              "
                + " AND  tmpDPL.idPlu                        =  " 
                + "                tblDctosOrdenesDetalle.idPlu "
                + " AND   tmpDPL.idTipoOrden               = 51 " 
                + " GROUP BY tmpDPL.idPlu   )                   "
                + "                  AS cantidadCompraPendiente "
                + " FROM    tblDctosOrdenes		        "
                + " INNER JOIN tblDctosOrdenesDetalle	        "
                + " ON  tblDctosOrdenes.IDLOCAL     = 	        "
                + "             tblDctosOrdenesDetalle.IDLOCAL  "
                + " AND tblDctosOrdenes.IDTIPOORDEN = 	        "
                + "         tblDctosOrdenesDetalle.IDTIPOORDEN  "
                + " AND tblDctosOrdenes.IDORDEN     = 	        "
                + "             tblDctosOrdenesDetalle.IDORDEN  "
                + " INNER JOIN (SELECT tblPlus.idPlu,	        "
                + "                    tblPlus.factorDespacho   "
                + "             FROM tblPlus) AS tmpPLU	        "
                + " ON  tmpPLU.idPlu                = 	        "
                + "             tblDctosOrdenesDetalle.idPlu    "
                + " WHERE tblDctosOrdenes.idLog     =           "
                + getIdLog() + "                                "
                + " ORDER BY tblDctosOrdenesDetalle.NOMBREPLU   ";

System.out.println(selectStr);

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaDctoOrdenDetalleBean fachadaBean = new FachadaDctoOrdenDetalleBean();

            //
            while (rs.next()) {

                //
                fachadaBean = new FachadaDctoOrdenDetalleBean();

                //
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setIdOrden(rs.getInt("idOrden"));
                fachadaBean.setIdPlu(rs.getInt("idPlu"));
                fachadaBean.setCantidadPedida(rs.getDouble("cantidadPedida"));
                fachadaBean.setCantidadBonificada(
                                            rs.getDouble("cantidadBonificada"));
                fachadaBean.setVrCosto(rs.getDouble("vrCosto"));
                fachadaBean.setVrCostoIND(rs.getDouble("vrCostoIND"));
                fachadaBean.setVrCostoNegociado(
                                              rs.getDouble("vrCostoNegociado"));
                fachadaBean.setNombrePlu(rs.getString("nombrePlu"));
                fachadaBean.setExistencia(rs.getDouble("existencia"));
                fachadaBean.setFactorDespacho(rs.getInt("factorDespacho"));
                fachadaBean.setExistenciaBodega(
                                              rs.getDouble("existenciaBodega"));
                fachadaBean.setCantidadPvd(rs.getDouble("cantidadPvd"));
                fachadaBean.setCantidadCompraPendiente(
                                       rs.getDouble("cantidadCompraPendiente"));
                fachadaBean.setPorcentajeIva(
                                       rs.getDouble("porcentajeIva"));

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

    // pluTercero
    public Vector pluTercero(String xIdTercero) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        // Variable de fachada de los datos
        FachadaDctoOrdenDetalleBean fachadaBean;

        Connection connection = null;

        String selectStr =
                "SELECT  tblPlus.idPlu,                 "
                + "        tblPlus.nombrePlu,             "
                + "        tblPlus.vrCosto,               "
                + "        tblCategorias.nombreCategoria, "
                + "        tblMarcas.nombreMarca,         "
                + "        tblPlus.vrGeneral              "
                + "                  AS vrVentaUnitario,  "
                + "        tblPlusInventario.existencia,  "
                + "ISNULL((                                     "
                + "      SELECT                                 "
                + "       tblDctosOrdenesDetalle.cantidadPedida "
                + "  FROM   tblDctosOrdenes                     "
                + "  INNER JOIN tblDctosOrdenesDetalle          "
                + "  ON  tblDctosOrdenes.IDLOCAL        =       "
                + "         tblDctosOrdenesDetalle.IDLOCAL      "
                + "  AND tblDctosOrdenes.IDTIPOORDEN    =       "
                + "    tblDctosOrdenesDetalle.IDTIPOORDEN       "
                + "  AND tblDctosOrdenes.IDORDEN        =       "
                + "        tblDctosOrdenesDetalle.IDORDEN       "
                + "  WHERE tblDctosOrdenes.IDLOCAL      =       "
                + getIdLocal() + "                              "
                + "  AND   tblDctosOrdenes.IDTIPOORDEN  =       "
                + getIdTipoOrden() + "                          "
                + "  AND   tblDctosOrdenes.IDLOG        =       "
                + getIdLog() + "                                "
                + "  AND   tblDctosOrdenesDetalle.IDPLU =       "
                + "                      tblPlus.idPlu ),0)     "
                + "                      AS cantidadPedido,     "
                + "ISNULL((                                     "
                + "     SELECT                                  "
                + "     tblDctosOrdenesDetalle.vrCostoNegociado "
                + "  FROM   tblDctosOrdenes                     "
                + "  INNER JOIN tblDctosOrdenesDetalle          "
                + "  ON  tblDctosOrdenes.IDLOCAL        =       "
                + "         tblDctosOrdenesDetalle.IDLOCAL      "
                + "  AND tblDctosOrdenes.IDTIPOORDEN    =       "
                + "    tblDctosOrdenesDetalle.IDTIPOORDEN       "
                + "  AND tblDctosOrdenes.IDORDEN        =       "
                + "        tblDctosOrdenesDetalle.IDORDEN       "
                + "  WHERE tblDctosOrdenes.IDLOCAL      =       "
                + getIdLocal() + "                              "
                + "  AND   tblDctosOrdenes.IDTIPOORDEN  =       "
                + getIdTipoOrden() + "                          "
                + "  AND   tblDctosOrdenes.IDLOG        =       "
                + getIdLog() + "                                "
                + "  AND   tblDctosOrdenesDetalle.IDPLU =       "
                + "                      tblPlus.idPlu ),0)     "
                + "                        AS vrCostoNegociado  "
                + "FROM    tblCategorias                  "
                + "INNER JOIN tblPlus                     "
                + "ON tblCategorias.idLinea      =        "
                + "                 tblPlus.idLinea       "
                + "AND tblCategorias.IdCategoria =        "
                + "             tblPlus.idCategoria       "
                + "INNER JOIN tblLineas                   "
                + "ON tblPlus.idLinea            =        "
                + "               tblLineas.idLinea       "
                + "INNER JOIN tblMarcas                   "
                + "ON tblPlus.idMarca            =        "
                + "               tblMarcas.idMarca       "
                + "INNER JOIN tblPlusTercero              "
                + "ON tblPlus.idPlu              =        "
                + "            tblPlusTercero.idPlu       "
                + "INNER JOIN tblPlusInventario           "
                + "ON tblPlusTercero.idPlu       =        "
                + "         tblPlusInventario.idPlu       "
                + "WHERE  tblPlusTercero.idTercero  =    '"
                + xIdTercero + "'                         "
                + "AND   tblPlusInventario.idLocal  =     "
                + getIdLocal() + "                        "
                + "AND   tblPlusInventario.idBodega =     "
                + getIdBodega() + "                       "
                + "AND   tblPlus.idTipo  = 1		        "
                + "ORDER BY tblCategorias.nombreCategoria,"
                + "         tblPlus.nombrePlu";

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
                fachadaBean = new FachadaDctoOrdenDetalleBean();

                //
                fachadaBean.setIdPlu(rs.getInt("idPlu"));
                fachadaBean.setNombrePlu(rs.getString("nombrePlu"));
                fachadaBean.setVrCosto(rs.getDouble("vrCosto"));
                fachadaBean.setNombreCategoria(rs.getString("nombreCategoria"));
                fachadaBean.setNombreMarca(rs.getString("nombreMarca"));
                fachadaBean.setVrVentaUnitario(rs.getDouble("vrVentaUnitario"));
                fachadaBean.setExistencia(rs.getDouble("existencia"));
                fachadaBean.setCantidadPedido(rs.getDouble("cantidadPedido"));
                fachadaBean.setVrCostoPedido(rs.getDouble("vrCostoNegociado"));
                fachadaBean.setVrCostoNegociado(
                        rs.getDouble("vrCostoNegociado"));

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

    // pluTraslado
    public Vector pluTraslado() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        // Variable de fachada de los datos
        FachadaDctoOrdenDetalleBean fachadaBean;

        Connection connection = null;

        String selectStr =
                "SELECT  tblPlus.idPlu,                       "
                + "        tblPlus.nombrePlu,                   "
                + "        tblPlus.vrCosto,                     "
                + "        tblPlus.factorDespacho,              "
                + "        tblCategorias.nombreCategoria,       "
                + "        tblMarcas.nombreMarca,               "
                + "        tblPlus.vrGeneral                    "
                + "                  AS vrVentaUnitario,        "
                + "        tblPlusInventario.existencia,        "
                + "ISNULL((                                     "
                + "      SELECT                                 "
                + "       tblDctosOrdenesDetalle.cantidadPedida "
                + "  FROM   tblDctosOrdenes                     "
                + "  INNER JOIN tblDctosOrdenesDetalle          "
                + "  ON  tblDctosOrdenes.IDLOCAL        =       "
                + "         tblDctosOrdenesDetalle.IDLOCAL      "
                + "  AND tblDctosOrdenes.IDTIPOORDEN    =       "
                + "    tblDctosOrdenesDetalle.IDTIPOORDEN       "
                + "  AND tblDctosOrdenes.IDORDEN        =       "
                + "        tblDctosOrdenesDetalle.IDORDEN       "
                + "  WHERE tblDctosOrdenes.IDLOCAL      =       "
                + getIdLocal() + "                              "
                + "  AND   tblDctosOrdenes.IDTIPOORDEN  =       "
                + getIdTipoOrden() + "                          "
                + "  AND   tblDctosOrdenes.IDLOG        =       "
                + getIdLog() + "                                "
                + "  AND   tblDctosOrdenesDetalle.IDPLU =       "
                + "                      tblPlus.idPlu ),0)     "
                + "                      AS cantidadPedido,     "
                + "ISNULL((                                     "
                + "     SELECT                                  "
                + "     tblDctosOrdenesDetalle.vrCostoNegociado "
                + "  FROM   tblDctosOrdenes                     "
                + "  INNER JOIN tblDctosOrdenesDetalle          "
                + "  ON  tblDctosOrdenes.IDLOCAL        =       "
                + "         tblDctosOrdenesDetalle.IDLOCAL      "
                + "  AND tblDctosOrdenes.IDTIPOORDEN    =       "
                + "    tblDctosOrdenesDetalle.IDTIPOORDEN       "
                + "  AND tblDctosOrdenes.IDORDEN        =       "
                + "        tblDctosOrdenesDetalle.IDORDEN       "
                + "  WHERE tblDctosOrdenes.IDLOCAL      =       "
                + getIdLocal() + "                              "
                + "  AND   tblDctosOrdenes.IDTIPOORDEN  =       "
                + getIdTipoOrden() + "                          "
                + "  AND   tblDctosOrdenes.IDLOG        =       "
                + getIdLog() + "                                "
                + "  AND   tblDctosOrdenesDetalle.IDPLU =       "
                + "                      tblPlus.idPlu ),0)     "
                + "                           AS vrCostoPedido  "
                + "FROM    tblCategorias                        "
                + "INNER JOIN tblPlus                           "
                + "ON tblCategorias.idLinea             =       "
                + "                 tblPlus.idLinea             "
                + "AND tblCategorias.IdCategoria        =       "
                + "             tblPlus.idCategoria             "
                + "INNER JOIN tblLineas                         "
                + "ON tblPlus.idLinea                   =       "
                + "               tblLineas.idLinea             "
                + "INNER JOIN tblMarcas                         "
                + "ON tblPlus.idMarca                   =       "
                + "               tblMarcas.idMarca             "
                + "INNER JOIN tblPlusInventario                 "
                + "ON  tblPlus.idPlu                    =       "
                + "         tblPlusInventario.idPlu             "
                + "WHERE tblPlusInventario.idLocal      =       "
                + getIdLocal() + "                              "
                + "AND   tblPlusInventario.idBodega     =       "
                + getIdBodega() + "                             "
                + "AND tblPlus.idTipo                   = 1     "
                + "ORDER BY tblCategorias.nombreCategoria,      "
                + "         tblPlus.nombrePlu";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            while (rs.next()) {

                fachadaBean = new FachadaDctoOrdenDetalleBean();

                //
                fachadaBean.setIdPlu(rs.getInt("idPlu"));
                fachadaBean.setNombrePlu(rs.getString("nombrePlu"));
                fachadaBean.setVrCosto(rs.getDouble("vrCosto"));
                fachadaBean.setFactorDespacho(rs.getInt("factorDespacho"));
                fachadaBean.setNombreCategoria(rs.getString("nombreCategoria"));
                fachadaBean.setNombreMarca(rs.getString("nombreMarca"));
                fachadaBean.setVrVentaUnitario(rs.getDouble("vrVentaUnitario"));
                fachadaBean.setExistencia(rs.getDouble("existencia"));
                fachadaBean.setCantidadPedido(rs.getDouble("cantidadPedido"));
                fachadaBean.setVrCostoPedido(rs.getDouble("vrCostoPedido"));

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

    // pluTerceroUn
    public Vector pluTerceroUn(String xIdTercero) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        // Variable de fachada de los datos
        FachadaDctoOrdenDetalleBean fachadaBean;

        Connection connection = null;

        String selectStr =
                "SELECT  tblPlus.idPlu,                 "
                + "        tblPlus.nombrePlu,             "
                + "        tblPlus.vrCosto,               "
                + "        tblCategorias.nombreCategoria, "
                + "        tblMarcas.nombreMarca,         "
                + "        tblPlus.vrGeneral              "
                + "                  AS vrVentaUnitario,  "
                + "        tblPlusInventario.existencia   "
                + "FROM    tblCategorias                  "
                + "INNER JOIN tblPlus                     "
                + "ON tblCategorias.idLinea      =        "
                + "                 tblPlus.idLinea       "
                + "AND tblCategorias.IdCategoria =        "
                + "             tblPlus.idCategoria       "
                + "INNER JOIN tblLineas                   "
                + "ON tblPlus.idLinea            =        "
                + "               tblLineas.idLinea       "
                + "INNER JOIN tblMarcas                   "
                + "ON tblPlus.idMarca            =        "
                + "               tblMarcas.idMarca       "
                + "INNER JOIN tblPlusTercero              "
                + "ON tblPlus.idPlu              =        "
                + "            tblPlusTercero.idPlu       "
                + "INNER JOIN tblPlusInventario           "
                + "ON tblPlusTercero.idPlu       =        "
                + "         tblPlusInventario.idPlu       "
                + "WHERE  tblPlusTercero.idTercero  =    '"
                + xIdTercero + "'                         "
                + "AND   tblPlusInventario.idLocal =      "
                + getIdLocal() + "                        "
                + "AND   tblPlusInventario.idPlu   =      "
                + getIdPlu() + "                          "
                + "ORDER BY tblCategorias.nombreCategoria,"
                + "         tblPlus.nombrePlu";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            while (rs.next()) {

                fachadaBean = new FachadaDctoOrdenDetalleBean();

                //
                fachadaBean.setIdPlu(rs.getInt("idPlu"));
                fachadaBean.setNombrePlu(rs.getString("nombrePlu"));
                fachadaBean.setVrCosto(rs.getDouble("vrCosto"));
                fachadaBean.setNombreCategoria(rs.getString("nombreCategoria"));
                fachadaBean.setNombreMarca(rs.getString("nombreMarca"));
                fachadaBean.setVrVentaUnitario(rs.getDouble("vrVentaUnitario"));
                fachadaBean.setExistencia(rs.getDouble("existencia"));

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
