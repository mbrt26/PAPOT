package com.solucionesweb.losbeans.colaboraciones;

// Importa la clase helper JDBCAccess que contiene los accesos a la base de datos
import com.solucionesweb.lasayudas.JDBCAccess;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaDctoOrdenProgreso;

// Importa los paquetes de java
import java.sql.*;

//import java.util.Date;
import java.io.IOException.*;

// Importa los paquetes de javax
import java.util.Vector;
import javax.naming.*;

public class ColaboraCosto extends FachadaDctoOrdenProgreso {

    // Nombre JNDI del Data Source que esta clase requiere
    private static final String DATA_SOURCE_NAME = "CommerceAccess";
    // Clase helper usada para acceder a la base de datos
    private JDBCAccess jdbcAccess;

    // Metodo constructor por defecto sin parametros
    public ColaboraCosto() {

        // Al instanciar el Bean establece la conexion a la base de datos
        jdbcAccess = new JDBCAccess(DATA_SOURCE_NAME);
    }

    // lista
    public Vector lista() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        String selectStr =
                " SELECT tblDctosOrdenes.IDLOCAL                          "
                + "       ,tblDctosOrdenes.IDTIPOORDEN                    "
                + "       ,tblDctosOrdenes.IDORDEN                        "
                + "       ,tblDctosOrdenes.FECHAORDEN                     "
                + "       ,tblDctosOrdenes.idCliente                      "
                + "       ,tblDctosOrdenes.numeroOrden                    "
                + "       ,tblDctosOrdenes.idFicha                        "
                + "       ,tblTerceros.nombreTercero                      "
                + "       ,tmpORD.itemPadre                               "
                + "       ,tmpORD.idOperacion                             "
                + "       ,tmpORD.nombreOperacion                         "
                + "       ,tmpORD.vrCostoBaseMAT                          "
                + "       ,tmpORD.vrCostoBaseCIF                          "
                + "       ,tmpORD.vrCostoBaseMOD                          "
                + "       ,tmpORD.pesoTerminado                           "
                + " FROM tblDctosOrdenes                                  "
                + " INNER JOIN tblTerceros                                "
                + " ON tblTerceros.idCliente          =                   "
                + "              tblDctosOrdenes.idCliente                "
                + " INNER JOIN (                                          "
                + " SELECT tmpCOS.idLocal,                                "
                + "        tmpCOS.idTipoOrden,                            "
                + "        tmpCOS.idOrden,                                "
                + "        tmpCOS.itemPadre,                              "
                + "        tmpCOS.idOperacion,                            "
                + "        tblJobOperacion.nombreOperacion,               "
                + "        SUM(tmpCOS.vrCostoBaseMAT)                     "
                + " 	                 AS vrCostoBaseMAT,               "
                + "        SUM(tmpCOS.vrCostoBaseCIF)                     "
                + " 	                 AS vrCostoBaseCIF,               "
                + "        SUM(tmpCOS.vrCostoBaseMOD)                     "
                + " 	                 AS vrCostoBaseMOD,               "
                + "        SUM(tmpCOS.pesoTerminado)                      "
                + " 	                   AS pesoTerminado               "
                + " FROM (                                                "
                + " SELECT tmpCST.idLocal,                                "
                + "        tmpCST.idTipoOrden,                            "
                + "        tmpCST.idOrden,                                "
                + "        tmpCST.itemPadre,                              "
                + "        tmpCST.idOperacion,                            "
                + "        MAX(tmpCST.vrCostoMAT) *                       "
                + "        MAX(tmpCST.pesoTerminado)                      "
                + "               AS vrCostoBaseMAT,                      "
                + "        MAX(tmpCST.vrCostoCIF) *                       "
                + "        MAX(tmpCST.pesoTerminado)                      "
                + "               AS vrCostoBaseCIF,                      "
                + "        MAX(tmpCST.vrCostoMOD) *                       "
                + "        MAX(tmpCST.pesoTerminado)                      "
                + "               AS vrCostoBaseMOD,                      "
                + "        MAX(tmpCST.pesoTerminado)                      "
                + "                AS pesoTerminado                       "
                + " FROM                                                  "
                + " ( SELECT tblDctosOrdenesProgreso.idLocal              "
                + "       ,tblDctosOrdenesProgreso.idTipoOrden            "
                + "       ,tblDctosOrdenesProgreso.idOrden                "
                + "       ,tblDctosOrdenesProgreso.itemPadre              "
                + "       ,tblDctosOrdenesProgreso.idOperacion            "
                + "       ,0 AS vrCostoMAT                                "
                + "    ,MAX (tblDctosOrdenesProgreso.vrCostoBaseCIF)      "
                + "                                    AS vrCostoCIF      "
                + "   ,MAX ( tblDctosOrdenesProgreso.vrCostoBaseMOD)      "
                + "                   AS vrCostoMOD                       "
                + "       ,SUM(tblDctosOrdenesProgreso.pesoTerminado)     "
                + "                AS pesoTerminado                       "
                + " FROM tblDctosOrdenesProgreso                          "
                + " WHERE tblDctosOrdenesProgreso.idLocal       =         "
                + getIdLocal() + "                                        "
                + " AND   tblDctosOrdenesProgreso.idTipoOrden   =         "
                + getIdTipoOrden() + "                                    "
                + "   AND CONVERT(CHAR(10),                               "
	        + "   tblDctosOrdenesProgreso.fechaFin,112) BETWEEN      '"
                + getFechaInicioSqlServer() + "'                     AND '"
                + getFechaFinSqlServer() + "'                             "
                + " AND tblDctosOrdenesProgreso.idControlTipo != 2        "
                + " GROUP BY tblDctosOrdenesProgreso.idLocal              "
                + "         ,tblDctosOrdenesProgreso.idTipoOrden          "
                + "         ,tblDctosOrdenesProgreso.idOrden              "
                + "         ,tblDctosOrdenesProgreso.itemPadre            "
                + "         ,tblDctosOrdenesProgreso.idOperacion          "
                + " UNION                                                 "
                + " SELECT tblDctosOrdenesDetalle.idLocalOrigen           "
                + "       ,tblDctosOrdenesDetalle.idTipoOrdenOrigen       "
                + "       ,tblDctosOrdenesDetalle.idOrdenOrigen           "
                + "       ,tblDctosOrdenesDetalle.itemOrden               "
                + "                                    AS itemPadre       "
                + "       ,tblDctosOrdenesDetalle.idOperacion             "
                + "       ,SUM(tblDctosOrdenesDetalle.cantidad *          "
                + "         tblDctosOrdenesDetalle.vrCostoIND) /          "
                + "        SUM(tblDctosOrdenesDetalle.cantidad)           "
                + "                                 AS vrCostoMAT         "
                + "       ,0 AS vrCostoCIF                                "
                + "       ,0 AS vrCostoMOD                                "
                + "       ,SUM(tblDctosOrdenesDetalle.cantidad)           "
                + "                               AS pesoTerminado        "
                + " FROM tblDctosOrdenesDetalle                           "
                + " WHERE tblDctosOrdenesDetalle.idLocalOrigen     =      "
                + getIdLocal() + "                                        "
                + " AND   tblDctosOrdenesDetalle.idTipoOrdenOrigen =      "
                + getIdTipoOrden() + "                                    "
                + " AND   tblDctosOrdenesDetalle.cantidad          > 0    "
                + " AND EXISTS ( SELECT tblDctosOrdenes.*                 "
                + "              FROM tblDctosOrdenes                     "
                + "              WHERE tblDctosOrdenes.idLocal     =      "
                + "                    tblDctosOrdenesDetalle.idLocal     "
                + "              AND   tblDctosOrdenes.idTipoOrden =      "
                + "                   tblDctosOrdenesDetalle.idTipoOrden  "
                + "              AND   tblDctosOrdenes.idOrden     =      "
                + "                    tblDctosOrdenesDetalle.idOrden     "
                + "              AND tblDctosOrdenes.fechaOrden BETWEEN  '"
                + getFechaInicioSqlServer() + "'                     AND '"
                + getFechaFinSqlServer() + "'    )                        "
                + " GROUP BY tblDctosOrdenesDetalle.idLocalOrigen         "
                + "         ,tblDctosOrdenesDetalle.idTipoOrdenOrigen     "
                + "         ,tblDctosOrdenesDetalle.idOrdenOrigen         "
                + "         ,tblDctosOrdenesDetalle.itemOrden             "
                + "         ,tblDctosOrdenesDetalle.idOperacion           "
                + " HAVING SUM(tblDctosOrdenesDetalle.cantidad) != 0  )   "
                + "                                          AS tmpCST    "
                + " GROUP BY tmpCST.idLocal,                              "
                + "          tmpCST.idTipoOrden,                          "
                + "          tmpCST.idOrden,                              "
                + "          tmpCST.itemPadre,                            "
                + "          tmpCST.idOperacion ) AS tmpCOS               "
                + " INNER JOIN tblJobOperacion                            "
                + " ON tblJobOperacion.idOperacion                        "
                + "                     = tmpCOS.idOperacion              "
                + " WHERE tblJobOperacion.idOperacion                     "
                + "                  NOT IN (1,7,9,8,999,888)             "
                + " GROUP BY  tmpCOS.idLocal,                             "
                + "        tmpCOS.idTipoOrden,                            "
                + "        tmpCOS.idOrden,                                "
                + "        tmpCOS.itemPadre,                              "
                + "        tmpCOS.idOperacion,                            "
                + "        tblJobOperacion.nombreOperacion )              "
                + "                                 AS tmpORD             "
                + " ON tmpORD.idLocal      =                              "
                + "                   tblDctosOrdenes.idLocal             "
                + " AND tmpORD.idTipoOrden =                              "
                + "              tblDctosOrdenes.idTipoOrden              "
                + " AND tmpORD.idOrden     =                              "
                + "                  tblDctosOrdenes.idOrden              "
                + " WHERE  tblTerceros.idTipoTercero  = 1                 "
                + " ORDER BY  tblDctosOrdenes.FECHAORDEN,                 "
                + "           tblDctosOrdenes.numeroOrden,                "
                + "          tmpORD.itemPadre,                            "
                + "          tmpORD.idOperacion                           ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaDctoOrdenProgreso fachadaBean;

            while (rs.next()) {

                //
                fachadaBean = new FachadaDctoOrdenProgreso();

                //
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setIdOrden(rs.getInt("idOrden"));
                fachadaBean.setFechaOrden(rs.getString("fechaOrden"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setNumeroOrden(rs.getInt("numeroOrden"));
                fachadaBean.setIdFicha(rs.getInt("idFicha"));
                fachadaBean.setNombreTercero(rs.getString("nombreTercero"));
                fachadaBean.setItemPadre(rs.getInt("itemPadre"));
                fachadaBean.setIdOperacion(rs.getInt("idOperacion"));
                fachadaBean.setNombreOperacion(rs.getString("nombreOperacion"));
                fachadaBean.setVrCostoBaseMAT(rs.getDouble("vrCostoBaseMAT"));
                fachadaBean.setVrCostoBaseCIF(rs.getDouble("vrCostoBaseCIF"));
                fachadaBean.setVrCostoBaseMOD(rs.getDouble("vrCostoBaseMOD"));
                fachadaBean.setPesoTerminado(rs.getDouble("pesoTerminado"));

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

    // listaProductivo
    public Vector listaProductivo() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        String selectStr =
"  SELECT tblDctosOrdenesProgreso.idLocal,          "
+ "        tblDctosOrdenesProgreso.idTipoOrden,     "
+ "          tblDctosOrdenesProgreso.idOrden,       "
+ "          tblDctosOrdenesProgreso.itemPadre,     "
+ "          tblDctosOrdenesProgreso.item,          "
+ "          tblDctosOrdenesProgreso.idOperario,    "
+ "         tblDctosOrdenesProgreso.pesoPerdido,    "
+ "     (SELECT tblJobOperacion.nombreOperacion     "
+ "      FROM   tblJobOperacion                     "
+ "      WHERE tblJobOperacion.idOperacion =        "
+ "          tblDctosOrdenesProgreso.idOperacion )  "
+ "                           AS nombreOperacion,   "
+ "          tblDctosOrdenesProgreso.idOperacion,   "
+ "    tblDctosOrdenesProgreso.cantidadTerminada,   "
+ "       tblDctosOrdenesProgreso.pesoTerminado,    "
+ "              DATEDIFF(ss,                       "
+ "         tblDctosOrdenesProgreso.fechaInicio,    "
+ "      tblDctosOrdenesProgreso.fechaFin) / 60     "
+ "                           AS cantidadMinuto,    "
+ "          tmpUSR.nombreUsuario,                  "
+ "          tblDctosOrdenes.numeroOrden,           "
+ "          tblDctosOrdenes.idFicha,               "
+ "          tmpORD.nombreItem,                     "
+ "   ( SELECT tblJobCausa.nombreCausa              "
+ "     FROM tblJobCausa                            "
+ "    WHERE tblJobCausa.idTipoCausa =              "
+ getIdTipoCausa() + "                              "
+ "     AND tblJobCausa.idCausa       =             "
+ "             tblDctosOrdenesProgreso.idCausa )   "
+ "                                AS nombreCausa   "
+ "   FROM   tblDctosOrdenesProgreso                "
+ " INNER JOIN                                      "
+ " ( SELECT ctrlUsuarios.idLocal                   "
+ "       ,ctrlUsuarios.idUsuario                   "
+ "       ,ctrlUsuarios.nombreUsuario               "
+ " FROM ctrlUsuarios                               "
+ " UNION                                           "
+ " SELECT 01 AS idLocal,                           "
+ "        CAST(tblTerceros.idCliente               "
+ "        AS decimal)                              "
+ "                    AS idUsuario                 "
+ "       ,tblTerceros.nombreTercero                "
+ "                 AS nombreUsuario                "
+ " FROM tblTerceros                                "
+ " WHERE tblTerceros.idTipoTercero = 2 )           "
+ "                             AS tmpUSR           "
+ " ON tmpUSR.idLocal    =                          "
+ "       tblDctosOrdenesProgreso.idLocal           "
+ " AND tmpUSR.idUsuario =                          "
+ "    tblDctosOrdenesProgreso.idOperario           "
+ "  INNER JOIN tblDctosOrdenes                     "
+ "  ON tblDctosOrdenesProgreso.idLocal      =      "
+ "                  tblDctosOrdenes.idLocal        "
+ "  AND tblDctosOrdenesProgreso.idTipoOrden =      "
+ "              tblDctosOrdenes.idTipoOrden        "
+ "  AND tblDctosOrdenesProgreso.idOrden     =      "
+ "                  tblDctosOrdenes.idOrden        "
+ "  INNER JOIN (                                   "
+ "  SELECT tblPlusFicha.idOperacion                "
+ "        ,tblPlusFicha.idEscala                   "
+ "        ,tblPlusFicha.item                       "
+ "        ,tblPlusFicha.vrEscala                   "
+ "        ,tblPlusFicha.idFicha                    "
+ "        ,tmpITE.nombreItem                       "
+ "  FROM tblPlusFicha                              "
+ "  INNER JOIN (                                   "
+ "  SELECT tblJobEscalaDetalle.idEscala            "
+ "        ,tblJobEscalaDetalle.item                "
+ "        ,tblJobEscalaDetalle.nombreItem          "
+ "        ,tblJobEscalaOperacion.idOperacion       "
+ "  FROM tblJobEscalaDetalle                       "
+ "  INNER JOIN tblJobEscalaOperacion               "
+ "  ON tblJobEscalaOperacion.idEscala =            "
+ "            tblJobEscalaDetalle.idEscala         "
+ "  WHERE tblJobEscalaDetalle.idEscala             "
+ "           IN (600, 710, 910, 1000,1100) )       "
+ "                                 AS tmpMAQ       "
+ "  ON tmpMAQ.idEscala     =                       "
+ "                     tblPlusFicha.idEscala       "
+ "  AND tmpMAQ.item        =                       "
+ "                     tblPlusFicha.vrEscala       "
+ "  AND tmpMAQ.idOperacion =                       "
+ "                  tblPlusFicha.idOperacion       "
+ "                                                 "
+ "  INNER JOIN                                     "
+ "  ( SELECT tblJobEscalaDetalle.idEscala          "
+ "        ,tblJobEscalaDetalle.item                "
+ "        ,tblJobEscalaDetalle.nombreItem          "
+ "        ,tblJobEscalaOperacion.idOperacion       "
+ "  FROM tblJobEscalaDetalle                       "
+ "  INNER JOIN tblJobEscalaOperacion               "
+ "  ON tblJobEscalaOperacion.idEscala =            "
+ "            tblJobEscalaDetalle.idEscala         "
+ "  WHERE tblJobEscalaDetalle.idEscala             "
+ "           IN (600, 710, 910, 1000,1100) )       "
+ "                                AS tmpITE        "
+ "  ON tmpITE.idEscala     =                       "
+ "                    tblPlusFicha.idEscala        "
+ "  AND tmpITE.idOperacion =                       "
+ "                 tblPlusFicha.idOperacion        "
+ "  AND tmpITE.item        =                       "
+ "                    tblPlusFicha.vrEscala        "
+ "  WHERE tblPlusFicha.idEscala                    "
+ "           IN (600, 710, 910, 1000,1100) )       "
+ "                                AS tmpORD        "
+ "  ON tblDctosOrdenesProgreso.idOperacion =       "
+ "                        tmpORD.idOperacion       "
+ "  AND tblDctosOrdenes.idFicha            =       "
+ "                            tmpORD.idFicha       "
+ " AND CONVERT(CHAR(10),                           "
+ "     tblDctosOrdenesProgreso.fechaFin,112)       "
+ " BETWEEN                                        '"
+ getFechaInicioSqlServer() + "'             AND   '"
+ getFechaFinSqlServer() + "'                       "
+ " AND tblDctosOrdenesProgreso.idControlTipo != 2  "
+ " ORDER BY 8,14 " ;

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaDctoOrdenProgreso fachadaBean;

            while (rs.next()) {

                //
                fachadaBean = new FachadaDctoOrdenProgreso();

                //
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setIdOrden(rs.getInt("idOrden"));
                fachadaBean.setItemPadre(rs.getInt("itemPadre"));
                fachadaBean.setItem(rs.getInt("item"));
                fachadaBean.setIdOperario(rs.getDouble("idOperario"));
                fachadaBean.setPesoPerdido(rs.getDouble("pesoPerdido"));
                fachadaBean.setIdOperacion(rs.getInt("idOperacion"));
                fachadaBean.setCantidadTerminada(
                                             rs.getDouble("cantidadTerminada"));
                fachadaBean.setPesoTerminado(rs.getDouble("pesoTerminado"));
                fachadaBean.setCantidadMinuto(rs.getInt("cantidadMinuto"));
                fachadaBean.setNombreOperario(rs.getString("nombreUsuario"));
                fachadaBean.setNombreOperacion(rs.getString("nombreOperacion"));
                fachadaBean.setNumeroOrden(rs.getInt("numeroOrden"));
                fachadaBean.setIdFicha(rs.getInt("idFicha"));
                fachadaBean.setNombreItem(rs.getString("nombreItem"));
                fachadaBean.setNombreCausa(rs.getString("nombreCausa"));

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

    // listaTiempoPerdido
    public Vector listaTiempoPerdido() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        String selectStr =
" SELECT tblDctosOrdenesProgreso.idLocal,         "
+ "       tblDctosOrdenesProgreso.idTipoOrden,    "
+ "         tblDctosOrdenesProgreso.idOrden,      "
+ "         tblDctosOrdenesProgreso.itemPadre,    "
+ "         tblDctosOrdenesProgreso.item,         "
+ "         tblDctosOrdenesProgreso.idOperario,   "
+ "        tblDctosOrdenesProgreso.pesoPerdido,   "
+ "    (SELECT tblJobOperacion.nombreOperacion    "
+ "     FROM   tblJobOperacion                    "
+ "     WHERE tblJobOperacion.idOperacion =       "
+ "         tblDctosOrdenesProgreso.idOperacion ) "
+ "                          AS nombreOperacion,  "
+ "         tblDctosOrdenesProgreso.idOperacion,  "
+ "   tblDctosOrdenesProgreso.cantidadTerminada,  "
+ "      tblDctosOrdenesProgreso.pesoTerminado,   "
+ "             DATEDIFF(ss,                      "
+ "        tblDctosOrdenesProgreso.fechaInicio,   "
+ "     tblDctosOrdenesProgreso.fechaFin) / 60    "
+ "                          AS cantidadMinuto,   "
+ "         ctrlUsuarios.nombreUsuario,           "
+ "         tblDctosOrdenes.numeroOrden,          "
+ "         tblDctosOrdenes.idFicha,              "
+ "         tmpORD.nombreItem,                    "
+ "  ( SELECT tblJobCausa.nombreCausa             "
+ "    FROM tblJobCausa                           "
+ "    WHERE tblJobCausa.idTipoCausa =            "
+ getIdTipoCausa() + "                            "
+ "    AND tblJobCausa.idCausa       =            "
+ "            tblDctosOrdenesProgreso.idCausa )  "
+ "                               AS nombreCausa  "
+ "  FROM   tblDctosOrdenesProgreso               "
+ "  INNER JOIN ctrlUsuarios                      "
+ "  ON tblDctosOrdenesProgreso.idLocal     =     "
+ "                       ctrlUsuarios.idLocal    "
+ "  AND tblDctosOrdenesProgreso.idOperario =     "
+ "                    ctrlUsuarios.idUsuario     "
+ " INNER JOIN tblDctosOrdenes                    "
+ " ON tblDctosOrdenesProgreso.idLocal      =     "
+ "                    tblDctosOrdenes.idLocal    "
+ " AND tblDctosOrdenesProgreso.idTipoOrden =     "
+ "                tblDctosOrdenes.idTipoOrden    "
+ " AND tblDctosOrdenesProgreso.idOrden     =     "
+ "                    tblDctosOrdenes.idOrden    "
+ " INNER JOIN (                                  "
+ " SELECT tblPlusFicha.idOperacion               "
+ "       ,tblPlusFicha.idEscala                  "
+ "       ,tblPlusFicha.item                      "
+ "       ,tblPlusFicha.vrEscala                  "
+ "       ,tblPlusFicha.idFicha                   "
+ "       ,tmpITE.nombreItem                      "
+ " FROM tblPlusFicha                             "
+ " INNER JOIN (                                  "
+ " SELECT tblJobEscalaDetalle.idEscala           "
+ "       ,tblJobEscalaDetalle.item               "
+ "       ,tblJobEscalaDetalle.nombreItem         "
+ "       ,tblJobEscalaOperacion.idOperacion      "
+ " FROM tblJobEscalaDetalle                      "
+ " INNER JOIN tblJobEscalaOperacion              "
+ " ON tblJobEscalaOperacion.idEscala =           "
+ "           tblJobEscalaDetalle.idEscala        "
+ " WHERE tblJobEscalaDetalle.idEscala            "
+ "          IN (600, 710, 910, 1000,1100))       "
+ "                                AS tmpMAQ      "
+ " ON tmpMAQ.idEscala     =                      "
+ "                    tblPlusFicha.idEscala      "
+ " AND tmpMAQ.item        =                      "
+ "                    tblPlusFicha.vrEscala      "
+ " AND tmpMAQ.idOperacion =                      "
+ "                 tblPlusFicha.idOperacion      "
+ "                                               "
+ " INNER JOIN                                    "
+ " ( SELECT tblJobEscalaDetalle.idEscala         "
+ "       ,tblJobEscalaDetalle.item               "
+ "       ,tblJobEscalaDetalle.nombreItem         "
+ "       ,tblJobEscalaOperacion.idOperacion      "
+ " FROM tblJobEscalaDetalle                      "
+ " INNER JOIN tblJobEscalaOperacion              "
+ " ON tblJobEscalaOperacion.idEscala =           "
+ "           tblJobEscalaDetalle.idEscala        "
+ " WHERE tblJobEscalaDetalle.idEscala            "
+ "           IN (600, 710, 910, 1000,1100) )     "
+ "                               AS tmpITE       "
+ "                                               "
+ " ON tmpITE.idEscala     =                      "
+ "                   tblPlusFicha.idEscala       "
+ " AND tmpITE.idOperacion =                      "
+ "                tblPlusFicha.idOperacion       "
+ " AND tmpITE.item        =                      "
+ "                   tblPlusFicha.vrEscala       "
+ " WHERE tblPlusFicha.idEscala                   "
+ "           IN (600, 710, 910, 1000,1100) )     "
+ "                               AS tmpORD       "
+ " ON tblDctosOrdenesProgreso.idOperacion =      "
+ "                       tmpORD.idOperacion      "
+ " AND tblDctosOrdenes.idFicha            =      "
+ "                           tmpORD.idFicha      "
+ " AND CONVERT(CHAR(10),                         "
+ "     tblDctosOrdenesProgreso.fechaFin,112)     "
+ " BETWEEN                                      '"
+ getFechaInicioSqlServer() + "'             AND '"
+ getFechaFinSqlServer() + "'                     "
+ " AND tblDctosOrdenesProgreso.idTipoCausa   =   "
+ getIdTipoCausa() + "                            "
+ " ORDER BY 8,14 " ;

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaDctoOrdenProgreso fachadaBean;

            while (rs.next()) {

                //
                fachadaBean = new FachadaDctoOrdenProgreso();

                //
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setIdOrden(rs.getInt("idOrden"));
                fachadaBean.setItemPadre(rs.getInt("itemPadre"));
                fachadaBean.setItem(rs.getInt("item"));
                fachadaBean.setIdOperario(rs.getDouble("idOperario"));
                fachadaBean.setPesoPerdido(rs.getDouble("pesoPerdido"));
                fachadaBean.setIdOperacion(rs.getInt("idOperacion"));
                fachadaBean.setCantidadTerminada(
                                             rs.getDouble("cantidadTerminada"));
                fachadaBean.setPesoTerminado(rs.getDouble("pesoTerminado"));
                fachadaBean.setCantidadMinuto(rs.getInt("cantidadMinuto"));
                fachadaBean.setNombreOperario(rs.getString("nombreUsuario"));
                fachadaBean.setNombreOperacion(rs.getString("nombreOperacion"));
                fachadaBean.setNumeroOrden(rs.getInt("numeroOrden"));
                fachadaBean.setIdFicha(rs.getInt("idFicha"));
                fachadaBean.setNombreItem(rs.getString("nombreItem"));
                fachadaBean.setNombreCausa(rs.getString("nombreCausa"));

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
