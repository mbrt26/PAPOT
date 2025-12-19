package com.solucionesweb.losbeans.colaboraciones;

// Importa la clase helper JDBCAccess que contiene los accesos a la base de datos
import com.solucionesweb.lasayudas.JDBCAccess;

// Importa el bean de utilidades
import com.solucionesweb.losbeans.utilidades.IConstantes;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaTipoOrdenSubcuenta;

// Importa los paquetes de java
import java.sql.*;
import java.io.*;
import java.util.*;
import java.io.IOException.*;

// Importa los paquetes de javax
import javax.naming.*;

public class ColaboraOrdenSubcuenta extends FachadaTipoOrdenSubcuenta
        implements Serializable, IConstantes {

    // Nombre JNDI del Data Source que esta clase requiere
    private static final String DATA_SOURCE_NAME = "CommerceAccess";
    // Clase helper usada para acceder a la base de datos
    private JDBCAccess jdbcAccess;

    // Metodo constructor por defecto sin parametros
    public ColaboraOrdenSubcuenta() {

        // Al instanciar el Bean establece la conexion a la base de datos
        jdbcAccess = new JDBCAccess(DATA_SOURCE_NAME);
    }

    // Metodo
    public Vector listaCuenta() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        String selectStr =
                "SELECT tblTipoOrden.idTipoOrden,              "
                + "       tblTipoOrden.nombreTipoOrden,        "
                + "       tblTipoOrdenSubcuenta.idAsiento,     "
                + "       tblContableSubcuenta.idSubcuenta,    "
                + "       tblContableSubcuenta.nombreSubcuenta "
                + "FROM   tblContableSubcuenta                 "
                + "INNER JOIN tblTipoOrdenSubcuenta            "
                + "ON tblContableSubcuenta.idSubcuenta     =   "
                + "         tblTipoOrdenSubcuenta.idSubcuenta  "
                + "INNER JOIN tblTipoOrden                     "
                + "ON tblTipoOrdenSubcuenta.idTipoOrden    =   "
                + "                  tblTipoOrden.idTipoOrden  "
                + "WHERE tblTipoOrdenSubcuenta.idTipoOrden =   "
                + getIdTipoOrden() + "                         "
                + "AND   tblTipoOrdenSubcuenta.idAsiento   = 1 "
                + "ORDER BY tblTipoOrden.idTipoOrden,          "
                + "         tblTipoOrdenSubcuenta.idAsiento,   "
                + "         tblContableSubcuenta.idSubcuenta   ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaTipoOrdenSubcuenta fachadaBean;

            while (rs.next()) {

                //
                fachadaBean = new FachadaTipoOrdenSubcuenta();

                //
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setNombreTipoOrden(rs.getString("nombreTipoOrden"));
                fachadaBean.setIdAsiento(rs.getInt("idAsiento"));
                fachadaBean.setIdSubcuenta(rs.getString("idSubcuenta"));
                fachadaBean.setNombreSubcuenta(rs.getString("nombreSubcuenta"));

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

    // listaOrden
    public Vector listaOrden(int xIdLocal,
            int xIdLog) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        String selectStr =
                "SELECT tblDctosOrdenesDetalle.idTipoOrden,      "
                + "       tblContableSubcuenta.nombreSubcuenta,  "
                + "       tblContableSubcuenta.idSubcuenta,      "
                + "       tblDctosOrdenesDetalle.cantidad,       "
                + "       tblDctosOrdenesDetalle.vrVentaUnitario "
                + "                               AS VrUnitario  "
                + "FROM   tblTipoOrden                           "
                + "INNER JOIN tblDctosOrdenesDetalle             "
                + "INNER JOIN tblDctosOrdenes                    "
                + "ON  tblDctosOrdenesDetalle.IDLOCAL     =      "
                + "                      tblDctosOrdenes.IDLOCAL "
                + "AND tblDctosOrdenesDetalle.IDTIPOORDEN =      "
                + "                  tblDctosOrdenes.IDTIPOORDEN "
                + "AND tblDctosOrdenesDetalle.IDORDEN     =      "
                + "                      tblDctosOrdenes.IDORDEN "
                + "ON  tblTipoOrden.idTipoOrden           =      "
                + "           tblDctosOrdenesDetalle.IDTIPOORDEN "
                + "INNER JOIN tblContableSubcuenta               "
                + "ON tblDctosOrdenesDetalle.idSubcuenta =       "
                + "             tblContableSubcuenta.idSubcuenta "
                + "WHERE tblDctosOrdenes.IDTIPOORDEN        =    "
                + getIdTipoOrden() + "                           "
                + "AND   tblDctosOrdenes.idLog              =    "
                + xIdLog + "                                     "
                + "AND   tblDctosOrdenes.idLocal            =    "
                + xIdLocal + "                                   "
                + "ORDER BY tblDctosOrdenesDetalle.item ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaTipoOrdenSubcuenta fachadaBean;

            while (rs.next()) {

                //
                fachadaBean = new FachadaTipoOrdenSubcuenta();

                //
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setNombreTipoOrden(rs.getString("nombreTipoOrden"));
                fachadaBean.setIdSubcuenta(rs.getString("idSubcuenta"));
                fachadaBean.setCantidad(rs.getDouble("CANTIDAD"));
                fachadaBean.setVrUnitario(rs.getDouble("VRVENTAUNITARIO"));

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

    // listaAlcance
    public Vector listaAlcance() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        String selectStr =
                "   SELECT tblDctos.idLocal, 			        "
                + "          tblDctos.idTipoOrden, 	                "
                + "          tblDctos.idOrden, 			        "
                + "          MAX(tmpCLI.nombreTercero) AS nombreTercero,"
                + "          MAX(tblDctos.idDcto)      AS idDcto,       "
                + "          MAX(tblDctos.idDctoNitCC) AS idDctoNitCC,  "
                + "          MAX(tblDctos.fechaDcto)   AS fechaDcto,    "
                + "          MAX(tblDctosOrdenes.OBSERVACION) 	        "
                + "                                    AS OBSERVACION,  "
                + "          SUM(tblDctosOrdenesDetalle.VRVENTAUNITARIO)"
                + "                                AS vrVentaUnitario,  "
                + "          (SELECT tblTipoOrden.nombreTipoOrden	"
                + "           FROM tblTipoOrden			        "
                + "           WHERE tblTipoOrden.idTipoOrden = 	        "
                + "                           tblDctos.idTipoOrden)     "
                + "                                AS nombreTipoOrden   "
                + "  FROM    tblDctosOrdenesDetalle	                "
                + "  INNER JOIN tblDctosOrdenes			        "
                + "  ON  tblDctosOrdenesDetalle.IDLOCAL     = 	        "
                + "                         tblDctosOrdenes.IDLOCAL     "
                + "  AND tblDctosOrdenesDetalle.IDTIPOORDEN = 	        "
                + "                     tblDctosOrdenes.IDTIPOORDEN     "
                + "  AND tblDctosOrdenesDetalle.IDORDEN     = 	        "
                + "                         tblDctosOrdenes.IDORDEN     "
                + "  INNER JOIN tblDctos				"
                + "  ON tblDctosOrdenes.IDLOCAL             = 	        "
                + "                                tblDctos.IDLOCAL     "
                + "  AND tblDctosOrdenes.IDTIPOORDEN        = 	        "
                + "                            tblDctos.IDTIPOORDEN     "
                + "  AND tblDctosOrdenes.IDORDEN            = 	        "
                + "                                tblDctos.IDORDEN     "
                + "  INNER JOIN					        "
                + "  (SELECT tmpTER.idCliente                           "
                + "         ,MAX(tmpTER.nombreTercero)         	        "
                + "                       AS nombreTercero   	        "
                + "         ,MAX(tmpTER.direccionTercero)      	        "
                + "                    AS direccionTercero   	        "
                + "         ,MAX(tmpTER.telefonoFijo)          	        "
                + "                       AS telefonoFijo   	        "
                + "         ,MAX(tmpTER.ordenSalida)            	"
                + "                        AS ordenSalida    	        "
                + "   FROM                                      	"
                + "       (SELECT LTRIM([idCliente]) AS idCliente       "
                + "              ,[nombreTercero] AS nombreTercero      "
                + "            ,[direccionTercero] AS direccionTercero  "
                + "            ,[telefonoFijo] AS telefonoFijo          "
                + "            ,02   AS ordenSalida                     "
                + "        FROM tblTerceros                             "
                + "        UNION                                        "
                + "        SELECT LTRIM([idUsuario])  AS idCliente      "
                + "              ,[nombreUsuario]  AS nombreTercero     "
                + "              ,[direccion] AS direccionTercero       "
                + "              ,[telefono] AS telefonoFijo            "
                + "              ,03   AS ordenSalida                   "
                + "        FROM  ctrlUsuarios                           "
                + "        UNION                                        "
                + "        SELECT LTRIM([idLocal])  AS idCliente        "
                + "              ,[nombreLocal]  AS nombreTercero       "
                + "              ,[direccion] AS direccionTercero       "
                + "              ,[telefono] AS telefonoFijo            "
                + "              ,04   AS ordenSalida                   "
                + "        FROM tblLocales)                             "
                + "                                    AS tmpTER        "
                + "        GROUP BY tmpTER.idCliente)  AS tmpCLI	"
                + "  ON  LTRIM(RTRIM(STR(tmpCLI.idCliente)))       = 	"
                + "    LTRIM(RTRIM(tblDctosOrdenes.idCliente))     "
                + "  INNER JOIN					        "
                + "  (SELECT tblTipoOrden.idTipoOrden                   "
                + "          ,tblTipoOrden.idAlcance                    "
                + "   FROM tblTipoOrden ) AS tmpORD                     "
                + "  ON  tmpORD.idTipoOrden       = 	                "
                + "                       tblDctosOrdenes.idTipoOrden   "
                + "WHERE tblDctos.idLocal     =      	                "
                + getIdLocal() + "                                      "
                + "AND   tblDctos.fechaDcto   <=                       '"
                + getFechaDctoSqlServer() + "'                          "
                + "AND   tmpORD.idAlcance =                             "
                + getIdAlcance() + "                                    "
                + "GROUP BY tblDctos.IDLOCAL, 			        "
                + "         tblDctos.IDTIPOORDEN, 	                "
                + "         tblDctos.IDORDEN                            "
                + "ORDER BY  7 DESC                                     ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaTipoOrdenSubcuenta fachadaBean;

            while (rs.next()) {

                //
                fachadaBean = new FachadaTipoOrdenSubcuenta();

                //
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setIdOrden(rs.getInt("idOrden"));
                fachadaBean.setNombreTercero(rs.getString("nombreTercero"));
                fachadaBean.setIdDcto(rs.getInt("idDcto"));
                fachadaBean.setIdDctoNitCC(rs.getString("idDctoNitCC"));
                fachadaBean.setFechaDcto(rs.getString("fechaDcto"));
                fachadaBean.setObservacion(rs.getString("observacion"));
                fachadaBean.setNombreTipoOrden(rs.getString("nombreTipoOrden"));
                fachadaBean.setVrUnitario(rs.getDouble("VRVENTAUNITARIO"));


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


    // listaAlcance
    public Vector listaAlcancePeriodo() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        String selectStr =
               "   SELECT tblDctos.idLocal, 			        "+
               "          tblDctos.idTipoOrden, 	                "+
               "          tblDctos.idOrden, 			        "+
               "          MAX(tmpCLI.nombreTercero) AS nombreTercero,   "+
               "          MAX(tblDctos.idDcto)      AS idDcto,          "+
               "          MAX(tblDctos.idDctoNitCC) AS idDctoNitCC,     "+
               "          MAX(tblDctos.fechaDcto)   AS fechaDcto,       "+
               "          MAX(tblDctosOrdenes.OBSERVACION) 	        "+
               "                                    AS OBSERVACION,     "+
               "          SUM(tblDctosOrdenesDetalle.VRVENTAUNITARIO)   "+
               "                                AS vrVentaUnitario,     "+
               "          (SELECT tblTipoOrden.nombreTipoOrden	        "+
               "           FROM tblTipoOrden			        "+
               "           WHERE tblTipoOrden.idTipoOrden = 	        "+
               "                           tblDctos.idTipoOrden)        "+
               "                                AS nombreTipoOrden      "+
               "  FROM    tblDctosOrdenesDetalle	                "+
               "  INNER JOIN tblDctosOrdenes			        "+
               "  ON  tblDctosOrdenesDetalle.IDLOCAL     = 	        "+
               "                         tblDctosOrdenes.IDLOCAL        "+
               "  AND tblDctosOrdenesDetalle.IDTIPOORDEN = 	        "+
               "                     tblDctosOrdenes.IDTIPOORDEN        "+
               "  AND tblDctosOrdenesDetalle.IDORDEN     = 	        "+
               "                         tblDctosOrdenes.IDORDEN        "+
               "  INNER JOIN tblDctos				        "+
               "  ON tblDctosOrdenes.IDLOCAL             = 	        "+
               "                                tblDctos.IDLOCAL        "+
               "  AND tblDctosOrdenes.IDTIPOORDEN        = 	        "+
               "                            tblDctos.IDTIPOORDEN        "+
               "  AND tblDctosOrdenes.IDORDEN            = 	        "+
               "                                tblDctos.IDORDEN        "+
               "  INNER JOIN					        "+
               "  (SELECT tmpTER.idCliente                              "+
               "         ,MAX(tmpTER.nombreTercero)         	        "+
               "                       AS nombreTercero   	        "+
               "         ,MAX(tmpTER.direccionTercero)      	        "+
               "                    AS direccionTercero   	        "+
               "         ,MAX(tmpTER.telefonoFijo)          	        "+
               "                       AS telefonoFijo   	        "+
               "         ,MAX(tmpTER.ordenSalida)            	        "+
               "                        AS ordenSalida    	        "+
               "   FROM                                      	        "+
               "       (SELECT [idCliente] AS idCliente                 "+
               "              ,[nombreTercero] AS nombreTercero         "+
               "            ,[direccionTercero] AS direccionTercero     "+
               "            ,[telefonoFijo] AS telefonoFijo             "+
               "            ,02   AS ordenSalida                        "+
               "        FROM tblTerceros                                "+
               "        UNION                                           "+
               "        SELECT [idUsuario]  AS idCliente                "+
               "              ,[nombreUsuario]  AS nombreTercero        "+
               "              ,[direccion] AS direccionTercero          "+
               "              ,[telefono] AS telefonoFijo               "+
               "              ,03   AS ordenSalida                      "+
               "        FROM  ctrlUsuarios                              "+
               "        UNION                                           "+
               "        SELECT LTRIM([idLocal])  AS idCliente                  "+
               "              ,[nombreLocal]  AS nombreTercero          "+
               "              ,[direccion] AS direccionTercero          "+
               "              ,[telefono] AS telefonoFijo               "+
               "              ,04   AS ordenSalida                      "+
               "        FROM tblLocales)                                "+
               "                                    AS tmpTER           "+
               "        GROUP BY tmpTER.idCliente)  AS tmpCLI	        "+
               "  ON  tmpCLI.idCliente       = 	                        "+
               "                         tblDctosOrdenes.idCliente      "+
               "  INNER JOIN					        "+
               "  (SELECT tblTipoOrden.idTipoOrden                      "+
               "          ,tblTipoOrden.idAlcance                       "+
               "   FROM tblTipoOrden ) AS tmpORD                        "+
               "  ON  tmpORD.idTipoOrden       = 	                "+
               "                       tblDctosOrdenes.idTipoOrden      "
                + "WHERE tblDctos.idLocal     =      	                "
                + getIdLocal() + "                                      "+
                 "AND   tblDctos.fechaDcto BETWEEN                     '"+
                getFechaInicialSqlServer() + "'     AND                '"+
                getFechaFinalSqlServer() + "'                           "
                + "AND   tmpORD.idAlcance =                             "
                + getIdAlcance() + "                                    "
                + "GROUP BY tblDctos.IDLOCAL, 			        "
                + "         tblDctos.IDTIPOORDEN, 	                "
                + "         tblDctos.IDORDEN                            "
                + "ORDER BY  7 DESC                                     " ;
        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaTipoOrdenSubcuenta fachadaBean;

            while (rs.next()) {

                //
                fachadaBean = new FachadaTipoOrdenSubcuenta();

                //
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setIdOrden(rs.getInt("idOrden"));
                fachadaBean.setNombreTercero(rs.getString("nombreTercero"));
                fachadaBean.setIdDcto(rs.getInt("idDcto"));
                fachadaBean.setIdDctoNitCC(rs.getString("idDctoNitCC"));
                fachadaBean.setFechaDcto(rs.getString("fechaDcto"));
                fachadaBean.setObservacion(rs.getString("observacion"));
                fachadaBean.setNombreTipoOrden(rs.getString("nombreTipoOrden"));
                fachadaBean.setVrUnitario(rs.getDouble("VRVENTAUNITARIO"));


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
