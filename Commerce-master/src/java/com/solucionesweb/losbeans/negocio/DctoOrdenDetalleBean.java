package com.solucionesweb.losbeans.negocio;

// Importa la clase helper JDBCAccess que contiene los accesos a la base de datos
import com.solucionesweb.lasayudas.JDBCAccess;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaDctoOrdenDetalleBean;


// Importa los paquetes de java
import java.sql.*;
import java.io.*;
import java.util.*;
import java.io.IOException.*;

// Importa los paquetes de javax
import javax.naming.*;

public class DctoOrdenDetalleBean extends FachadaDctoOrdenDetalleBean
        implements Serializable {

    // Nombre JNDI del Data Source que esta clase requiere
    private static final String DATA_SOURCE_NAME = "CommerceAccess";
    // Clase helper usada para acceder a la base de datos
    private JDBCAccess jdbcAccess;

    // Metodo constructor por defecto sin parametros
    public DctoOrdenDetalleBean() {

        // Al instanciar el Bean establece la conexion a la base de datos
        jdbcAccess = new JDBCAccess(DATA_SOURCE_NAME);
        
    }

    // listaDetalle
    public Vector listaDetalle() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        // Variable de fachada de los datos
        FachadaDctoOrdenDetalleBean fachadaBean;

        Connection connection = null;

        String selectStr =
                "SELECT tblDctosOrdenesDetalle.idLocal,            "
                + "        tblDctosOrdenesDetalle.idTipoOrden,       "
                + "        tblDctosOrdenesDetalle.idOrden,           "
                + "        tblDctosOrdenesDetalle.cantidad,          "
                + "        tblDctosOrdenesDetalle.nombrePlu,         "
                + "        tblDctosOrdenesDetalle.idPlu,             "
                + "        tblDctosOrdenesDetalle.idTipo,            "
                + "        tblDctosOrdenesDetalle.estado,            "
                + "        tblDctosOrdenesDetalle.porcentajeIva,     "
                + "        tblDctosOrdenesDetalle.vrVentaUnitario,   "
                + "        tblDctosOrdenesDetalle.ean,               "
                + "        tblDctosOrdenesDetalle.vrVentaOriginal,   "
                + "        tblDctosOrdenesDetalle.vrCosto,           "
                + "        tblDctosOrdenesDetalle.vrDsctoPie,        "
                + "        tblDctosOrdenesDetalle.porcentajeDscto,   "
                + "        tblDctosOrdenesDetalle.cantidadPedida,    "
                + "        tblDctosOrdenesDetalle.vrCostoNegociado,  "
                + "        tblDctosOrdenesDetalle.strIdLista ,       "
                + "        tblDctosOrdenesDetalle.strIdReferencia,   "
                + "        tblDctosOrdenesDetalle.pesoTeorico,       "
                + "        tblDctosOrdenesDetalle.nombreUnidadMedida,"
                + "        tblDctosOrdenesDetalle.marcaArte ,        "
                + "        tblDctosOrdenesDetalle.referenciaCliente, "
                + "        tblDctosOrdenesDetalle.comentario,        "
                + "        tblDctosOrdenesDetalle.item,              "
                + "        tblDctosOrdenesDetalle.vrImpoconsumo      "
                + "FROM tblDctosOrdenesDetalle                       "
                + "WHERE tblDctosOrdenesDetalle.idLocal     =        "
                + getIdLocal() + "                                   "
                + "AND   tblDctosOrdenesDetalle.idTipoOrden =        "
                + getIdTipoOrden() + "                               "
                + "AND   tblDctosOrdenesDetalle.idOrden     =        "
                + getIdOrden();


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
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setIdOrden(rs.getInt("idOrden"));
                fachadaBean.setCantidad(rs.getDouble("cantidad"));
                fachadaBean.setNombrePlu(rs.getString("nombrePlu"));
                fachadaBean.setIdPlu(rs.getInt("idPlu"));
                fachadaBean.setIdTipo(rs.getInt("idTipo"));
                fachadaBean.setEstado(rs.getInt("estado"));
                fachadaBean.setPorcentajeIva(rs.getDouble("PorcentajeIva"));
                fachadaBean.setVrVentaUnitario(rs.getDouble("vrVentaUnitario"));
                fachadaBean.setEan(rs.getDouble("ean"));
                fachadaBean.setVrVentaOriginal(rs.getInt("vrVentaOriginal"));
                fachadaBean.setVrCosto(rs.getDouble("vrCosto"));
                fachadaBean.setVrDsctoPie(rs.getDouble("vrDsctoPie"));
                fachadaBean.setPorcentajeDscto(rs.getDouble("porcentajeDscto"));
                fachadaBean.setCantidadPedida(rs.getDouble("cantidadPedida"));
                fachadaBean.setVrCostoNegociado(
                        rs.getDouble("vrCostoNegociado"));
                fachadaBean.setStrIdLista(rs.getString("strIdLista"));
                fachadaBean.setStrIdReferencia(rs.getString("strIdReferencia"));
                fachadaBean.setPesoTeorico(rs.getDouble("pesoTeorico"));
                fachadaBean.setNombreUnidadMedida(
                        rs.getString("nombreUnidadMedida"));
                fachadaBean.setMarcaArteCliente(rs.getString("marcaArte"));
                fachadaBean.setReferenciaCliente(
                        rs.getString("referenciaCliente"));
                fachadaBean.setComentario(rs.getString("comentario"));
                fachadaBean.setItem(rs.getInt("item"));
                fachadaBean.setVrImpoconsumo(rs.getDouble("vrImpoconsumo"));

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

    // listaResurtido
    public Vector listaResurtido(String xFechaInicial,
            String xFechaFinal) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        // Variable de fachada de los datos
        FachadaDctoOrdenDetalleBean fachadaBean;

        Connection connection = null;

        String selectStr =
                " SELECT tmpORD.IDLOCAL, 	  		"
                + "        tmpORD.IDTIPOORDEN,  		"
                + " 	        tmpORD.IDPLU,			"
                + "         SUM(tmpORD.cantidad) AS cantidad, 	"
                + "        MAX(ISNULL(tmpORD.existencia,0.0)) 	"
                + "                                AS existencia"
                + " FROM (					"
                + " SELECT tblDctosOrdenesDetalle.IDLOCAL, 	"
                + "          tblDctosOrdenesDetalle.IDTIPOORDEN,"
                + "          tblDctosOrdenesDetalle.IDPLU, 	"
                + "        SUM(tblDctosOrdenesDetalle.cantidad) "
                + "                                AS cantidad, "
                + " ( SELECT TOP 1 tblPlusInventario.existencia "
                + "   FROM   tblPlusInventario                  "
                + "   WHERE tblPlusInventario.idBodega    = 1   "
                + "   AND tblPlusInventario.IDLOCAL =           "
                + "              tblDctosOrdenesDetalle.idLocal "
                + "   AND   tblDctosOrdenesDetalle.idTipo = 1   "
                + "   AND     tblPlusInventario.idPlu     =     "
                + "               tblDctosOrdenesDetalle.IDPLU) "
                + "                               AS existencia "
                + " FROM  tblDctosOrdenesDetalle		"
                + " WHERE EXISTS 				"
                + " ( SELECT tblDctos.*				"
                + "   FROM tblDctos				"
                + "   WHERE tblDctos.IDLOCAL     = 		"
                + "              tblDctosOrdenesDetalle.IDLOCAL "
                + "   AND   tblDctos.IDTIPOORDEN = 		"
                + "          tblDctosOrdenesDetalle.IDTIPOORDEN "
                + "   AND   tblDctos.IDORDEN     =		"
                + "             tblDctosOrdenesDetalle.IDORDEN  "
                + "   AND tblDctos.fechaDcto BETWEEN           '"
                + xFechaInicial + "'  AND                      '"
                + xFechaFinal + "'                              "
                + "                 AND tblDctos.IDTIPOORDEN IN "
                + "                        (1,15,16,9,29,5,3,4) "
                + "   AND tblDctos.idLocal       =    		"
                + getIdLocal() + "                              "
                + "   AND tblDctosOrdenesDetalle.idTipo=1 )     "
                + " GROUP BY tblDctosOrdenesDetalle.IDLOCAL, 	"
                + "        tblDctosOrdenesDetalle.IDTIPOORDEN,  "
                + "          tblDctosOrdenesDetalle.IDPLU,      "
                + "          tblDctosOrdenesDetalle.idTipo    	"
                + "  UNION 					"
                + "  SELECT tblDctosOrdenesDetalle.IDLOCAL, 	"
                + "         tblDctosOrdenesDetalle.IDTIPOORDEN, "
                + "          tmpCMB.idPlu, 	  		"
                + "          SUM(tmpCMB.cantidad * 		"
                + "            tblDctosOrdenesDetalle.cantidad) "
                + "                                AS cantidad, "
                + "          0 AS existencia 			"
                + " FROM  tblDctosOrdenesDetalle,		"
                + "       (SELECT tblPlusCombo.idPluCombo,	"
                + "               tblPlusCombo.idPlu,		"
                + "               tblPlusCombo.cantidad		"
                + "        FROM tblPlusCombo) AS tmpCMB		"
                + " WHERE EXISTS 				"
                + " ( SELECT tblDctos.*				"
                + "   FROM tblDctos				"
                + "   WHERE tblDctos.IDLOCAL     = 		"
                + "               tblDctosOrdenesDetalle.IDLOCAL"
                + "   AND   tblDctos.IDTIPOORDEN = 		"
                + "          tblDctosOrdenesDetalle.IDTIPOORDEN "
                + "   AND   tblDctos.IDORDEN     =		"
                + "             tblDctosOrdenesDetalle.IDORDEN  "
                + "   AND tblDctos.fechaDcto BETWEEN           '"
                + xFechaInicial + "'  AND                      '"
                + xFechaFinal + "'                              "
                + "                 AND tblDctos.IDTIPOORDEN IN "
                + "                        (1,15,16,9,29,5,3,4) "
                + "   AND tblDctos.idLocal       =    		"
                + getIdLocal() + "  )                           "
                + " AND tblDctosOrdenesDetalle.idPlu  = 	"
                + "                           tmpCMB.idPluCombo	"
                + " AND tblDctosOrdenesDetalle.idTipo = 2	"
                + " GROUP BY tblDctosOrdenesDetalle.IDLOCAL, 	"
                + "          tblDctosOrdenesDetalle.IDTIPOORDEN,"
                + "          tmpCMB.idPlu, 			"
                + "          tblDctosOrdenesDetalle.idTipo) 	"
                + "                                   AS tmpORD	"
                + " GROUP BY tmpORD.IDLOCAL, 	  		"
                + "          tmpORD.IDTIPOORDEN,  		"
                + "          tmpORD.IDPLU			";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            //
            while (rs.next()) {

                //
                fachadaBean = new FachadaDctoOrdenDetalleBean();

                //
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setIdPlu(rs.getInt("idPlu"));
                fachadaBean.setCantidad(rs.getDouble("cantidad"));
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

    // listaResurtido
    public Vector listaDetalleLocal() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        // Variable de fachada de los datos
        FachadaDctoOrdenDetalleBean fachadaBean;

        Connection connection = null;

        String selectStr =
                "SELECT tblDctosOrdenesDetalle.idLocal            "
                + "       ,tblDctosOrdenesDetalle.idTipoOrden       "
                + "       ,tblDctosOrdenesDetalle.idOrden           "
                + "       ,tblDctosOrdenesDetalle.idPlu             "
                + "       ,tblDctosOrdenesDetalle.cantidadPedida    "
                + "FROM  tblAgendaLogVisitas                        "
                + "INNER JOIN tblDctosOrdenes                       "
                + "ON tblAgendaLogVisitas.IDLOG    =                "
                + "                           tblDctosOrdenes.IDLOG "
                + "INNER JOIN tblDctosOrdenesDetalle                "
                + "ON tblDctosOrdenes.IDLOCAL      =                "
                + "                  tblDctosOrdenesDetalle.IDLOCAL "
                + "AND tblDctosOrdenes.IDTIPOORDEN =                "
                + "              tblDctosOrdenesDetalle.IDTIPOORDEN "
                + "AND tblDctosOrdenes.IDORDEN     =                "
                + "                  tblDctosOrdenesDetalle.IDORDEN "
                + "WHERE tblDctosOrdenesDetalle.idLocal         =   "
                + getIdLocal() + "                                  "
                + "AND tblDctosOrdenesDetalle.idTipoOrden       =   "
                + getIdTipoOrden() + "                              "
                + "AND tblDctosOrdenes.idCliente                =  '"
                + getIdCliente() + "'                               "
                + "AND tblAgendaLogVisitas.estado               =   "
                + getEstado() + "                                   "
                + "ORDER BY tblDctosOrdenesDetalle.idLocal          "
                + "       ,tblDctosOrdenesDetalle.idTipoOrden       "
                + "       ,tblDctosOrdenesDetalle.idOrden ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            //
            while (rs.next()) {

                //
                fachadaBean = new FachadaDctoOrdenDetalleBean();

                //
                fachadaBean.setIdLocal(rs.getInt("IDLOCAL"));
                fachadaBean.setIdTipoOrden(rs.getInt("IDTIPOORDEN"));
                fachadaBean.setIdOrden(rs.getInt("IDORDEN"));
                fachadaBean.setIdPlu(rs.getInt("IDPLU"));
                fachadaBean.setCantidadPedida(rs.getDouble("CANTIDADPEDIDA"));

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

    // validaTrasladoMP
    public boolean validaTrasladoMP(int xIdTipoOrdenTraslado) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        boolean validaOk = false;
        Connection connection = null;

        //
        String selectStr =
                "   SELECT tblDctosOrdenesDetalle.*                  "
                + "   FROM tblDctosOrdenesDetalle                    "
                + "   WHERE tblDctosOrdenesDetalle.idLocal         = "
                + getIdLocal() + "                                   "
                + "   AND tblDctosOrdenesDetalle.idTipoOrden       = "
                + xIdTipoOrdenTraslado + "                           "
                + "   AND tblDctosOrdenesDetalle.idTipoOrdenOrigen = "
                + getIdTipoOrdenOrigen() + "                         "
                + "   AND tblDctosOrdenesDetalle.idOrdenOrigen     = "
                + getIdOrdenOrigen() + "                             "
                + "   AND tblDctosOrdenesDetalle.idBodega          = "
                + getIdBodega();

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
                validaOk = true;

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
            return validaOk;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {

            // Cierra la conexion
            return validaOk;

        }
    }
    // ingresaInterno

    public boolean ingresaInterno(int xIdBodegaDestino,
            double xSignoMovimientoDestino) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                "INSERT INTO tblDctosOrdenesDetalle (idLocal, "
                + "                      idTipoOrden,           "
                + "                      idOrden,               "
                + "                      cantidad,              "
                + "                      nombrePlu,             "
                + "                      idPlu,                 "
                + "                      idTipo,                "
                + "                      estado,                "
                + "                      porcentajeIva,         "
                + "                      vrVentaUnitario,       "
                + "                      ean,                   "
                + "                      vrVentaOriginal,       "
                + "                      vrCosto,               "
                + "                      vrDsctoPie,            "
                + "                      porcentajeDscto,       "
                + "                      cantidadPedida,        "
                + "                      vrCostoNegociado,      "
                + "                      strIdBodega,           "
                + "                      vrCostoResurtido,      "
                + "                      strIdLista,            "
                + "                      strIdReferencia,       "
                + "                      pesoTeorico,           "
                + "                      nombreUnidadMedida,    "
                + "                      idLocalSugerido,       "
                + "                      idBodegaSugerido,      "
                + "                      marcaArteCliente,      "
                + "                      referenciaCliente,     "
                + "                      comentario,            "
                + "                      item,                  "
                + "                      itemPadre,             "
                + "                      idEstadoTx,            "
                + "                      idTipoTx,              "
                + "                      idReferenciaOriginal,  "
                + "                      idEstadoRefOriginal,   "
                + "                      idClasificacion,       "
                + "                      idResponsable,         "
                + "                      fechaEntrega,          "
                + "                      idBodega,              "
                + "                      vrImpoconsumo)         "
                + "SELECT tblDctosOrdenesDetalle.idLocal,       "
                + "        tblDctosOrdenesDetalle.idTipoOrden,  "
                + "        tblDctosOrdenesDetalle.idOrden,      "
                + "     ( tblDctosOrdenesDetalle.cantidad *     "
                + xSignoMovimientoDestino + " )   AS cantidad,  "
                + "        tblDctosOrdenesDetalle.nombrePlu,    "
                + "        tblDctosOrdenesDetalle.idPlu,        "
                + "        tblDctosOrdenesDetalle.idTipo,       "
                + "        tblDctosOrdenesDetalle.estado,       "
                + "        tblDctosOrdenesDetalle.porcentajeIva,"
                + "      tblDctosOrdenesDetalle.vrVentaUnitario,"
                + "        tblDctosOrdenesDetalle.ean,          "
                + "      tblDctosOrdenesDetalle.vrVentaOriginal,"
                + "        tblDctosOrdenesDetalle.vrCosto,      "
                + "        tblDctosOrdenesDetalle.vrDsctoPie,   "
                + "      tblDctosOrdenesDetalle.porcentajeDscto,"
                + "       tblDctosOrdenesDetalle.cantidadPedida,"
                + "     tblDctosOrdenesDetalle.vrCostoNegociado,"
                + "          tblDctosOrdenesDetalle.strIdBodega,"
                + "     tblDctosOrdenesDetalle.vrCostoResurtido,"
                + "           tblDctosOrdenesDetalle.strIdLista,"
                + "     tblDctosOrdenesDetalle. strIdReferencia,"
                + "          tblDctosOrdenesDetalle.pesoTeorico,"
                + "   tblDctosOrdenesDetalle.nombreUnidadMedida,"
                + "      tblDctosOrdenesDetalle.idLocalSugerido,"
                + "     tblDctosOrdenesDetalle.idBodegaSugerido,"
                + "     tblDctosOrdenesDetalle.marcaArteCliente,"
                + "    tblDctosOrdenesDetalle.referenciaCliente,"
                + "           tblDctosOrdenesDetalle.comentario,"
                + "        (tblDctosOrdenesDetalle.item + 1000) "
                + "                                     AS item,"
                + "            tblDctosOrdenesDetalle.itemPadre,"
                + "           tblDctosOrdenesDetalle.idEstadoTx,"
                + "             tblDctosOrdenesDetalle.idTipoTx,"
                + " tblDctosOrdenesDetalle.idReferenciaOriginal,"
                + "  tblDctosOrdenesDetalle.idEstadoRefOriginal,"
                + "      tblDctosOrdenesDetalle.idClasificacion,"
                + "        tblDctosOrdenesDetalle.idResponsable,"
                + "         tblDctosOrdenesDetalle.fechaEntrega,"
                + xIdBodegaDestino + " AS idBodega,             "
                + "       tblDctosOrdenesDetalle.vrImpoconsumo  "
                + "FROM tblDctosOrdenesDetalle                  "
                + "WHERE tblDctosOrdenesDetalle.idLocal     =   "
                + getIdLocal() + "                              "
                + "AND   tblDctosOrdenesDetalle.idTipoOrden =   "
                + getIdTipoOrden() + "                          "
                + "AND   tblDctosOrdenesDetalle.idOrden     =   "
                + getIdOrden();

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

            // Cierra la conexion
            jdbcAccess.cleanup(connection, selectStatement, null);
            return okIngresar;

        }
    }

    // ingresaProduccionMP_Traslado
    public boolean ingresaProduccionMP_Traslado(int xIdFicha,
            int xIdOperacion,
            double xPesoTerminada,
            double xTotalMP,
            int xIdOrdenOrigen,
            int xIdLocalOrigen,
            int xIdTipoOrdenOrigen,
            int xIdTipoOrdenTraslado,
            int xIdBodega,
            int xItemPadre) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        boolean okIngresar = false;

        Connection connection = null;

        //
        String selectStr =
                " INSERT INTO tblDctosOrdenesDetalle         "
                + "            (IDLOCAL                      "
                + "            ,IDTIPOORDEN                  "
                + "            ,IDORDEN                      "
                + "            ,IDPLU                        "
                + "            ,CANTIDAD                     "
                + "            ,IDTIPO                       "
                + "            ,PORCENTAJEIVA                "
                + "            ,VRVENTAUNITARIO              "
                + "            ,ESTADO                       "
                + "            ,NOMBREPLU                    "
                + "            ,EAN                          "
                + "            ,VRVENTAORIGINAL              "
                + "            ,VRCOSTO                      "
                + "            ,VRDSCTOPIE                   "
                + "            ,PORCENTAJEDSCTO              "
                + "            ,CANTIDADPEDIDA               "
                + "            ,vrCostoNegociado             "
                + "            ,strIdBodega                  "
                + "            ,vrCostoResurtido             "
                + "            ,STRIDLISTA                   "
                + "            ,STRIDREFERENCIA              "
                + "            ,PESOTEORICO                  "
                + "            ,NOMBREUNIDADMEDIDA           "
                + "            ,IDLOCALSUGERIDO              "
                + "            ,IDBODEGASUGERIDO             "
                + "            ,marcaArteCliente             "
                + "            ,referenciaCliente            "
                + "            ,comentario                   "
                + "            ,item                         "
                + "            ,itemPadre                    "
                + "            ,idEstadoTx                   "
                + "            ,idTipoTx                     "
                + "            ,idReferenciaOriginal         "
                + "            ,idEstadoRefOriginal          "
                + "            ,idClasificacion              "
                + "            ,idResponsable                "
                + "            ,fechaEntrega                 "
                + "            ,idBodega                     "
                + "            ,vrImpoconsumo                "
                + "            ,vrCostoIND                   "
                + "            ,vrIvaResurtido               "
                + "            ,idSubcuenta                  "
                + "            ,cantidadBonificada           "
                + "            ,idOrdenOrigen                "
                + "            ,idLocalOrigen                "
                + "            ,idTipoOrdenOrigen            "
                + "            ,unidadVenta                  "
                + "            ,idOperacion                  "
                + "            ,itemOrden                    "
                + "            ,pesoTerminado)               "
                + " SELECT                                   "
                + getIdLocal() + " AS idLocal,               "
                + getIdTipoOrden() + " AS idTipoOrden,       "
                + getIdOrden() + " AS idOrden,               "
                + "        tblDctosOrdenesDetalle.idPlu,     "
                + xPesoTerminada + " AS cantidad,            "
                + "        tblDctosOrdenesDetalle.idTipo     "
                + " ,tblDctosOrdenesDetalle.PORCENTAJEIVA    "
                + " ,tblDctosOrdenesDetalle.VRVENTAUNITARIO, "
                + " 0 AS ESTADO,                             "
                + " tblDctosOrdenesDetalle.nombrePlu,        "
                + " tblDctosOrdenesDetalle.ean,              "
                + " tblDctosOrdenesDetalle.VRVENTAORIGINAL   "
                + "  ,tblDctosOrdenesDetalle.vrCosto         "
                + "       ,00 AS VRDSCTOPIE                  "
                + "       ,00 AS PORCENTAJEDSCTO,            "
                + xPesoTerminada + " AS CANTIDADPEDIDA       "
                + "       ,00 AS vrCostoNegociado            "
                + "       ,00 AS strIdBodega                 "
                + "       ,00 AS vrCostoResurtido            "
                + "       ,00 AS STRIDLISTA                  "
                + " ,tblDctosOrdenesDetalle.STRIDREFERENCIA  "
                + "       ,00 AS PESOTEORICO                 "
                + "       ,'' AS NOMBREUNIDADMEDIDA ,        "
                + getIdLocal() + " AS IDLOCALSUGERIDO ,      "
                + xIdBodega + " AS IDBODEGASUGERIDO          "
                + "       ,'' marcaArteCliente               "
                + ",tblDctosOrdenesDetalle.referenciaCliente "
                + "       ,'' AS comentario,                 "
                + "       tblDctosOrdenesDetalle.item,       "
                + "       tblDctosOrdenesDetalle.itemPadre,  "
                + "       tblDctosOrdenesDetalle.idEstadoTx  "
                + "       ,0 AS idTipoTx                     "
                + "       ,idReferenciaOriginal              "
                + "       ,01 AS idEstadoRefOriginal         "
                + "       ,00 AS idClasificacion             "
                + "       ,'' AS idResponsable               "
                + "       ,GETDATE() fechaEntrega ,          "
                + "       tblDctosOrdenesDetalle.idBodega    "
                + "       ,0 AS vrImpoconsumo                "
                + "   ,tblDctosOrdenesDetalle.vrCostoIND     "
                + "       ,00 AS vrIvaResurtido              "
                + "       ,00 AS idSubcuenta                 "
                + "       ,00 AS cantidadBonificada,         "
                + xIdOrdenOrigen + " AS idOrdenOrigen,       "
                + xIdLocalOrigen + " AS idLocalOrigen,       "
                + "tblDctosOrdenesDetalle.idTipoOrdenOrigen  "
                + "   ,tblDctosOrdenesDetalle.unidadVenta,   "
                + xIdOperacion + " AS idOperacion,           "
                + xItemPadre + " AS itemOrden,               "
                + xPesoTerminada + " AS pesoTerminado        "
                + " FROM tblDctosOrdenesDetalle              "
                + " WHERE tblDctosOrdenesDetalle.idLocal   = "
                + getIdLocal() + "                           "
                + " AND tblDctosOrdenesDetalle.idTipoOrden = "
                + xIdTipoOrdenTraslado + "                   "
                + " AND                                      "
                + "tblDctosOrdenesDetalle.idTipoOrdenOrigen= "
                + xIdTipoOrdenOrigen + "                     "
                + " AND                                      "
                + " tblDctosOrdenesDetalle.idOrdenOrigen   = "
                + xIdOrdenOrigen + "                         "
                + " AND tblDctosOrdenesDetalle.idBodega    = "
                + getIdBodega();

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

            // Cierra la conexion
            jdbcAccess.cleanup(connection, selectStatement, null);
            return okIngresar;

        }
    }

    // ingresaProduccionMP
    public boolean ingresaProduccionMP(int xIdFicha,
            int xIdOperacion,
            double xPesoTerminada,
            double xTotalMP,
            int xIdOrdenOrigen,
            int xIdLocalOrigen,
            int xIdTipoOrdenOrigen,
            int xIdTipoOrdenTraslado,
            int xIdBodega,
            int xItemPadre) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                " INSERT INTO tblDctosOrdenesDetalle         "
                + "            (IDLOCAL                      "
                + "            ,IDTIPOORDEN                  "
                + "            ,IDORDEN                      "
                + "            ,IDPLU                        "
                + "            ,CANTIDAD                     "
                + "            ,IDTIPO                       "
                + "            ,PORCENTAJEIVA                "
                + "            ,VRVENTAUNITARIO              "
                + "            ,ESTADO                       "
                + "            ,NOMBREPLU                    "
                + "            ,EAN                          "
                + "            ,VRVENTAORIGINAL              "
                + "            ,VRCOSTO                      "
                + "            ,VRDSCTOPIE                   "
                + "            ,PORCENTAJEDSCTO              "
                + "            ,CANTIDADPEDIDA               "
                + "            ,vrCostoNegociado             "
                + "            ,strIdBodega                  "
                + "            ,vrCostoResurtido             "
                + "            ,STRIDLISTA                   "
                + "            ,STRIDREFERENCIA              "
                + "            ,PESOTEORICO                  "
                + "            ,NOMBREUNIDADMEDIDA           "
                + "            ,IDLOCALSUGERIDO              "
                + "            ,IDBODEGASUGERIDO             "
                + "            ,marcaArteCliente             "
                + "            ,referenciaCliente            "
                + "            ,comentario                   "
                + "            ,item                         "
                + "            ,itemPadre                    "
                + "            ,idEstadoTx                   "
                + "            ,idTipoTx                     "
                + "            ,idReferenciaOriginal         "
                + "            ,idEstadoRefOriginal          "
                + "            ,idClasificacion              "
                + "            ,idResponsable                "
                + "            ,fechaEntrega                 "
                + "            ,idBodega                     "
                + "            ,vrImpoconsumo                "
                + "            ,vrCostoIND                   "
                + "            ,vrIvaResurtido               "
                + "            ,idSubcuenta                  "
                + "            ,cantidadBonificada           "
                + "            ,idOrdenOrigen                "
                + "            ,idLocalOrigen                "
                + "            ,idTipoOrdenOrigen            "
                + "            ,unidadVenta                  "
                + "            ,idOperacion                  "
                + "            ,itemOrden                    "
                + "            ,pesoTerminado                "
                + "            ,cantidadTerminada)           "
                + " SELECT                                   "
                + getIdLocal() + " AS idLocal,               "
                + getIdTipoOrden() + " AS idTipoOrden,       "
                + getIdOrden() + " AS idOrden,               "
                + "        tblPlusOperacion.idPlu,           "
                + "        (tblPlusFicha.vrEscala /          "
                + xTotalMP + " )        *                    "
                + xPesoTerminada + " AS cantidad,            "
                + "        tblPlus.idTipo                    "
                + "       ,tblPlus.PORCENTAJEIVA             "
                + "       ,tblPlus.vrGeneral                 "
                + "                      AS VRVENTAUNITARIO  "
                + "       ,tblPlus.ESTADO,                   "
                + "        (tblCategorias.nombreCategoria +  "
                + "                                   ' ' +  "
                + "        tblPlus.nombrePlu) AS nombrePlu,  "
                + "        tblPlusOperacion.idPlu AS ean     "
                + "       ,tblPlus.vrGeneral                 "
                + "                     AS VRVENTAORIGINAL   "
                + "       ,tblPlus.vrCosto                   "
                + "       ,00 AS VRDSCTOPIE                  "
                + "       ,00 AS PORCENTAJEDSCTO,            "
                + "        (tblPlusFicha.vrEscala /          "
                + xTotalMP + " )        *                    "
                + xPesoTerminada + " AS CANTIDADPEDIDA       "
                + "       ,00 AS vrCostoNegociado            "
                + "       ,00 AS strIdBodega                 "
                + "       ,00 AS vrCostoResurtido            "
                + "       ,00 AS STRIDLISTA                  "
                + "       ,tblPlusOperacion.idPlu            "
                + "                  AS STRIDREFERENCIA      "
                + "       ,00 AS PESOTEORICO                 "
                + "       ,'' AS NOMBREUNIDADMEDIDA ,        "
                + getIdLocal() + " AS IDLOCALSUGERIDO ,      "
                + xIdBodega + " AS IDBODEGASUGERIDO          "
                + "       ,'' marcaArteCliente               "
                + "       ,tblPlusFicha.referenciaCliente    "
                + "       ,'' AS comentario,                 "
                + xItemPadre + " AS item,                    "
                + xItemPadre + " AS itemPadre                "
                + "       ,0 AS idEstadoTx                   "
                + "       ,0 AS idTipoTx                     "
                + "       ,tblPlusOperacion.idPlu            "
                + "                AS idReferenciaOriginal   "
                + "       ,01 AS idEstadoRefOriginal         "
                + "       ,00 AS idClasificacion             "
                + "       ,'' AS idResponsable               "
                + "       ,GETDATE() fechaEntrega ,          "
                + xIdBodega + " AS idBodega                  "
                + "       ,0 AS vrImpoconsumo                "
                + "       ,tblPlus.vrCostoIND                "
                + "       ,00 AS vrIvaResurtido              "
                + "       ,00 AS idSubcuenta                 "
                + "       ,00 AS cantidadBonificada,         "
                + xIdOrdenOrigen + " AS idOrdenOrigen,       "
                + xIdLocalOrigen + " AS idLocalOrigen,       "
                + xIdTipoOrdenOrigen + "AS idTipoOrdenOrigen "
                + "       ,tblPlus.idUVenta unidadVenta,     "
                + xIdOperacion + "AS idOperacion,            "
                + xItemPadre + " AS itemOrden,               "
                + "        (tblPlusFicha.vrEscala /          "
                + xTotalMP + " )        *                    "
                + xPesoTerminada + " AS pesoTerminado,       "
                + "        (tblPlusFicha.vrEscala /          "
                + xTotalMP + " )        *                    "
                + xPesoTerminada + " AS cantidadTerminada    "
                + " FROM   tblPlusFicha                      "
                + " INNER JOIN tblJobEscala                  "
                + " ON tblPlusFicha.idEscala    =            "
                + "               tblJobEscala.idEscala      "
                + " INNER JOIN tblPlusOperacion              "
                + " ON tblPlusFicha.idOperacion =            "
                + "        tblPlusOperacion.idOperacion      "
                + " AND tblPlusFicha.idEscala   =            "
                + "           tblPlusOperacion.idEscala      "
                + " INNER JOIN tblPlus                       "
                + " ON tblPlusOperacion.idPlu   =            "
                + "                      tblPlus.idPlu       "
                + " INNER JOIN tblCategorias                 "
                + " ON tblPlus.idLinea          =            "
                + "              tblCategorias.idLinea       "
                + " AND tblPlus.idCategoria     =            "
                + "          tblCategorias.IdCategoria       "
                + " WHERE  (tblPlusFicha.idFicha =           "
                + xIdFicha + " )                             "
                + " AND (tblPlusFicha.idOperacion =          "
                + xIdOperacion + " )                         "
                + " AND  tblPlusFicha.vrEscala != 0          "
                + " AND  tblPlusOperacion.idEscala != 620    "
                + " UNION                                    "
                + "   SELECT                                    "
                + getIdLocal() + "  AS idLocal,                 "
                + getIdTipoOrden() + "   AS idTipoOrden,        "
                + getIdOrden() + "  AS idOrden,                "
                + "          tblPlus.idPlu,                     "
                + "          (CAST(tblPlusFicha.textoEscala     "
                + "                       AS DECIMAL(10,2)) /   "
                + xTotalMP + "   )        *                     "
                + xPesoTerminada + "   AS cantidad,             "
                + "          tblPlus.idTipo                     "
                + "         ,tblPlus.PORCENTAJEIVA              "
                + "         ,tblPlus.vrGeneral                  "
                + "                        AS VRVENTAUNITARIO   "
                + "         ,tblPlus.ESTADO,                    "
                + "          (tblCategorias.nombreCategoria +   "
                + "                                     ' ' +   "
                + "          tblPlus.nombrePlu) AS nombrePlu,   "
                + "          tblPlus.idPlu AS ean               "
                + "         ,tblPlus.vrGeneral                  "
                + "                       AS VRVENTAORIGINAL    "
                + "         ,tblPlus.vrCosto                    "
                + "         ,00 AS VRDSCTOPIE                   "
                + "         ,00 AS PORCENTAJEDSCTO,             "
                + "          (tblPlusFicha.vrEscala /           "
                + xTotalMP + "   )        *                     "
                + xPesoTerminada + "   AS CANTIDADPEDIDA        "
                + "         ,00 AS vrCostoNegociado             "
                + "         ,00 AS strIdBodega                  "
                + "         ,00 AS vrCostoResurtido             "
                + "         ,00 AS STRIDLISTA                   "
                + "         ,tblPlus.idPlu                      "
                + "                    AS STRIDREFERENCIA       "
                + "         ,00 AS PESOTEORICO                  "
                + "         ,'' AS NOMBREUNIDADMEDIDA ,         "
                + getIdLocal() + "   AS IDLOCALSUGERIDO ,       "
                + xIdBodega + "  AS IDBODEGASUGERIDO           "
                + "         ,'' marcaArteCliente                "
                + "         ,tblPlusFicha.referenciaCliente     "
                + "         ,'' AS comentario,                  "
                + xItemPadre + "   AS item,                     "
                + xItemPadre + "   AS itemPadre                 "
                + "         ,0 AS idEstadoTx                    "
                + "         ,0 AS idTipoTx                      "
                + "         ,tblPlus.idPlu                      "
                + "                  AS idReferenciaOriginal    "
                + "         ,01 AS idEstadoRefOriginal          "
                + "         ,00 AS idClasificacion              "
                + "         ,'' AS idResponsable                "
                + "         ,GETDATE() fechaEntrega ,           "
                + "                   1 AS idBodega             "
                + "         ,0 AS vrImpoconsumo                 "
                + "         ,tblPlus.vrCostoIND                 "
                + "         ,00 AS vrIvaResurtido               "
                + "         ,00 AS idSubcuenta                  "
                + "         ,00 AS cantidadBonificada,          "
                + xIdOrdenOrigen + "   AS idOrdenOrigen,        "
                + xIdLocalOrigen + "   AS idLocalOrigen,        "
                + xIdTipoOrdenOrigen + "  AS idTipoOrdenOrigen  "
                + "         ,tblPlus.idUVenta unidadVenta,      "
                + xIdOperacion + "  AS idOperacion,             "
                + xItemPadre + "   AS itemOrden,                "
                + "          (CAST(tblPlusFicha.textoEscala     "
                + "                       AS DECIMAL(10,2)) /   "
                + xTotalMP + "   )        *                     "
                + xPesoTerminada + " AS pesoTerminado,       "
                + "        (tblPlusFicha.vrEscala /          "
                + xTotalMP + " )        *                    "
                + xPesoTerminada + " AS cantidadTerminada    "
                + "   FROM   tblPlus                            "
                + "   INNER JOIN tblPlusFicha                   "
                + "   ON tblPlusFicha.vrEscala    =             "
                + "                           tblPlus.idPlu     "
                + "   INNER JOIN tblJobEscala                   "
                + "   ON tblPlusFicha.idEscala    =             "
                + "                 tblJobEscala.idEscala       "
                + "   INNER JOIN tblCategorias                  "
                + "   ON tblPlus.idLinea          =             "
                + "                tblCategorias.idLinea        "
                + "   AND tblPlus.idCategoria     =             "
                + "            tblCategorias.IdCategoria        "
                + " WHERE  (tblPlusFicha.idFicha =              "
                + xIdFicha + " )                                "
                + " AND (tblPlusFicha.idOperacion =             "
                + xIdOperacion + " )                            "
                + "   AND  tblPlusFicha.vrEscala != 0           "
                + "   AND  tblPlusFicha.idEscala IN (615,616)   ";

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

            // Cierra la conexion
            jdbcAccess.cleanup(connection, selectStatement, null);
            return okIngresar;

        }
    }

    // validaMPTraslado
    public boolean validaMPTraslado(int xIdFicha,
            int xIdOperacion,
            double xPesoTerminada,
            double xTotalMP,
            int xIdOrdenOrigen,
            int xIdLocalOrigen,
            int xIdTipoOrdenOrigen,
            int xIdTipoOrdenTraslado,
            int xIdBodega,
            int xItemPadre) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                " SELECT tblDctosOrdenesDetalle.*            "
                + " FROM tblDctosOrdenesDetalle              "
                + " WHERE tblDctosOrdenesDetalle.idLocal   = "
                + getIdLocal() + "                           "
                + " AND tblDctosOrdenesDetalle.idTipoOrden = "
                + xIdTipoOrdenTraslado + "                   "
                + " AND                                      "
                + "tblDctosOrdenesDetalle.idTipoOrdenOrigen= "
                + xIdTipoOrdenOrigen + "                     "
                + " AND                                      "
                + " tblDctosOrdenesDetalle.idOrdenOrigen   = "
                + xIdOrdenOrigen + "                         "
                + " AND tblDctosOrdenesDetalle.idBodega    = "
                + getIdBodega();

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

            // Cierra la conexion
            jdbcAccess.cleanup(connection, selectStatement, null);
            return okIngresar;

        }
    }

    // ingresaSuministro
    public boolean ingresaSuministro(int xIdFicha,
            int xIdOperacion,
            double xPesoTerminada,
            double xTotalMP,
            int xIdOrdenOrigen,
            int xIdLocalOrigen,
            int xIdTipoOrdenOrigen,
            int xIdBodega,
            int xItemPadre) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                " INSERT INTO tblDctosOrdenesDetalle        "
                + "            (IDLOCAL                     "
                + "            ,IDTIPOORDEN                 "
                + "            ,IDORDEN                     "
                + "            ,IDPLU                       "
                + "            ,CANTIDAD                    "
                + "            ,IDTIPO                      "
                + "            ,PORCENTAJEIVA               "
                + "            ,VRVENTAUNITARIO             "
                + "            ,ESTADO                      "
                + "            ,NOMBREPLU                   "
                + "            ,EAN                         "
                + "            ,VRVENTAORIGINAL             "
                + "            ,VRCOSTO                     "
                + "            ,VRDSCTOPIE                  "
                + "            ,PORCENTAJEDSCTO             "
                + "            ,CANTIDADPEDIDA              "
                + "            ,vrCostoNegociado            "
                + "            ,strIdBodega                 "
                + "            ,vrCostoResurtido            "
                + "            ,STRIDLISTA                  "
                + "            ,STRIDREFERENCIA             "
                + "            ,PESOTEORICO                 "
                + "            ,NOMBREUNIDADMEDIDA          "
                + "            ,IDLOCALSUGERIDO             "
                + "            ,IDBODEGASUGERIDO            "
                + "            ,marcaArteCliente            "
                + "            ,referenciaCliente           "
                + "            ,comentario                  "
                + "            ,item                        "
                + "            ,itemPadre                   "
                + "            ,idEstadoTx                  "
                + "            ,idTipoTx                    "
                + "            ,idReferenciaOriginal        "
                + "            ,idEstadoRefOriginal         "
                + "            ,idClasificacion             "
                + "            ,idResponsable               "
                + "            ,fechaEntrega                "
                + "            ,idBodega                    "
                + "            ,vrImpoconsumo               "
                + "            ,vrCostoIND                  "
                + "            ,vrIvaResurtido              "
                + "            ,idSubcuenta                 "
                + "            ,cantidadBonificada          "
                + "            ,idOrdenOrigen               "
                + "            ,idLocalOrigen               "
                + "            ,idTipoOrdenOrigen           "
                + "            ,unidadVenta                 "
                + "            ,idOperacion                 "
                + "            ,itemOrden                   "
                + "            ,pesoPedido                  "
                + "            ,pesoTerminado               "
                + "            ,pesoRetal                   "
                + "            ,cantidadTerminada           "
                + "            ,cantidadFacturada           "
                + "            ,pesoFacturado               "
                + "            ,cantidadEntregada           "
                + "            ,pesoEntregado)              "
                + " SELECT                                  "
                + getIdLocal() + " AS idLocal,              "
                + getIdTipoOrden() + " AS idTipoOrden,      "
                + getIdOrden() + " AS idOrden,              "
                + "        tblPlus.idPlu,                   "
                + xPesoTerminada + " AS cantidad,           "
                + "        tblPlus.idTipo                   "
                + "       ,tblPlus.PORCENTAJEIVA            "
                + "       ,tblPlus.vrGeneral                "
                + "                      AS VRVENTAUNITARIO "
                + "       ,tblPlus.estado,                  "
                + "        (tblCategorias.nombreCategoria + "
                + "                                   ' ' + "
                + "        tblPlus.nombrePlu) AS nombrePlu, "
                + "        tblPlus.idPlu AS ean             "
                + "       ,tblPlus.vrGeneral                "
                + "                     AS VRVENTAORIGINAL  "
                + "       ,tblPlus.vrCosto                  "
                + "       ,00 AS VRDSCTOPIE                 "
                + "       ,00 AS PORCENTAJEDSCTO,           "
                + xPesoTerminada + " AS CANTIDADPEDIDA      "
                + "       ,00 AS vrCostoNegociado           "
                + "       ,00 AS strIdBodega                "
                + "       ,00 AS vrCostoResurtido           "
                + "       ,00 AS STRIDLISTA                 "
                + "       ,tblPlus.idPlu                    "
                + "                  AS STRIDREFERENCIA     "
                + "       ,00 AS PESOTEORICO                "
                + "       ,'' AS NOMBREUNIDADMEDIDA ,       "
                + getIdLocal() + " AS IDLOCALSUGERIDO ,     "
                + xIdBodega + " AS IDBODEGASUGERIDO         "
                + "       ,'' marcaArteCliente              "
                + "       ,tblCategorias.nombreCategoria +  "
                + "        ' ' + tblPlus.nombrePlu          "
                + "                   AS referenciaCliente  "
                + "       ,'' AS comentario,                "
                + xItemPadre + " AS item,                   "
                + xItemPadre + " AS itemPadre               "
                + "       ,0 AS idEstadoTx                  "
                + "       ,0 AS idTipoTx                    "
                + "       ,tblPlus.idPlu                    "
                + "                AS idReferenciaOriginal  "
                + "       ,01 AS idEstadoRefOriginal        "
                + "       ,00 AS idClasificacion            "
                + "       ,'' AS idResponsable              "
                + "       ,GETDATE() fechaEntrega ,         "
                + xIdBodega + " AS idBodega                 "
                + "       ,0 AS vrImpoconsumo               "
                + "       ,tblPlus.vrCostoIND               "
                + "       ,00 AS vrIvaResurtido             "
                + "       ,00 AS idSubcuenta                "
                + "       ,00 AS cantidadBonificada,        "
                + xIdOrdenOrigen + " AS idOrdenOrigen,      "
                + xIdLocalOrigen + " AS idLocalOrigen,      "
                + xIdTipoOrdenOrigen + "AS idTipoOrdenOrigen"
                + "       ,tblPlus.idUVenta unidadVenta,    "
                + xIdOperacion + "AS idOperacion,           "
                + xItemPadre + " AS itemOrden               "
                + "            ,0.0 AS pesoPedido           "
                + "            ,0.0 AS pesoTerminado        "
                + "            ,0.0 AS pesoRetal            "
                + "            ,0.0 AS cantidadTerminada    "
                + "            ,0.0 AS cantidadFacturada    "
                + "            ,0.0 AS pesoFacturado        "
                + "            ,0.0 AS cantidadEntregada    "
                + "            ,0.0 AS pesoEntregado        "
                + " FROM   tblPlus                          "
                + " INNER JOIN tblCategorias                "
                + " ON tblPlus.idLinea          =           "
                + "              tblCategorias.idLinea      "
                + " AND tblPlus.idCategoria     =           "
                + "          tblCategorias.IdCategoria      "
                + " WHERE   tblPlus.idPlu       =           "
                + getIdPlu();

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

            // Cierra la conexion
            jdbcAccess.cleanup(connection, selectStatement, null);
            return okIngresar;

        }
    }

    // actualizaBodegaMP
    public boolean actualizaBodegaMP() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                " UPDATE tblDctosOrdenesDetalle                      "
                + " SET    tblDctosOrdenesDetalle.IDBODEGASUGERIDO = "
                + "                          tblCategorias.idBodega, "
                + "        tblDctosOrdenesDetalle.idBodega         = "
                + "                          tblCategorias.idBodega  "
                + " FROM tblDctosOrdenesDetalle                      "
                + " INNER JOIN tblDctosOrdenes                       "
                + " ON tblDctosOrdenesDetalle.IDLOCAL      =         "
                + "                         tblDctosOrdenes.IDLOCAL  "
                + " AND tblDctosOrdenesDetalle.IDTIPOORDEN =         "
                + "                    tblDctosOrdenes.IDTIPOORDEN   "
                + " AND tblDctosOrdenesDetalle.IDORDEN     =         "
                + "                        tblDctosOrdenes.IDORDEN   "
                + " INNER JOIN tblPlus                               "
                + " ON tblDctosOrdenesDetalle.IDPLU = tblPlus.idPlu  "
                + " INNER JOIN tblCategorias                         "
                + " ON tblPlus.idLinea              =                "
                + "                          tblCategorias.idLinea   "
                + " AND tblPlus.idCategoria         =                "
                + "                      tblCategorias.idCategoria   "
                + " WHERE  tblDctosOrdenes.IDLOCAL   =               "
                + getIdLocal() + "                                   "
                + " AND  tblDctosOrdenes.IDTIPOORDEN =               "
                + getIdTipoOrden() + "                               "
                + " AND  tblDctosOrdenes.IDORDEN     =               "
                + getIdOrden() + "                                   "
                + " AND  tblCategorias.idBodega != 0 ";

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

            // Cierra la conexion
            jdbcAccess.cleanup(connection, selectStatement, null);
            return okIngresar;

        }
    }
    
    // actualizaBodegaMP
    public boolean actualizaBodegaMPRecep() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                " UPDATE tblDctosOrdenesDetalle                             "
                + " SET    tblDctosOrdenesDetalle.IDBODEGASUGERIDO = 888,   "
                + "        tblDctosOrdenesDetalle.idBodega         = 888    "
                + " FROM tblDctosOrdenesDetalle                             "
                + " INNER JOIN tblDctosOrdenes                              "
                + " ON tblDctosOrdenesDetalle.IDLOCAL      =                "
                + "                         tblDctosOrdenes.IDLOCAL         "
                + " AND tblDctosOrdenesDetalle.IDTIPOORDEN =                "
                + "                    tblDctosOrdenes.IDTIPOORDEN          "
                + " AND tblDctosOrdenesDetalle.IDORDEN     =                "
                + "                        tblDctosOrdenes.IDORDEN          "
                + " INNER JOIN tblPlus                                      "
                + " ON tblDctosOrdenesDetalle.IDPLU = tblPlus.idPlu         "
                + " INNER JOIN tblCategorias                                "
                + " ON tblPlus.idLinea              =                       "
                + "                          tblCategorias.idLinea          "
                + " AND tblPlus.idCategoria         =                       "
                + "                      tblCategorias.idCategoria          "
                + " WHERE  tblDctosOrdenes.IDLOCAL   =                      "
                + getIdLocal() + "                                          "
                + " AND  tblDctosOrdenes.IDTIPOORDEN =                      "
                + getIdTipoOrden() + "                                      "
                + " AND  tblDctosOrdenes.IDORDEN     =                      "
                + getIdOrden() + "                                          "
                + " AND  tblCategorias.idBodega != 0                        ";

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

            // Cierra la conexion
            jdbcAccess.cleanup(connection, selectStatement, null);
            return okIngresar;

        }
    }

    // actualizaBodegaMP_Traslado
    public boolean actualizaBodegaMP_Traslado(int xIdBodegaTraslado) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                " UPDATE tblDctosOrdenesDetalle                      "
                + " SET    tblDctosOrdenesDetalle.IDBODEGASUGERIDO = "
                + xIdBodegaTraslado + ",                             "
                + "        tblDctosOrdenesDetalle.idBodega         = "
                + xIdBodegaTraslado + ",                             "
                + "        tblDctosOrdenesDetalle.idOperacion      = "
                + xIdBodegaTraslado + "                              "
                + " FROM tblDctosOrdenesDetalle                      "
                + " INNER JOIN tblDctosOrdenes                       "
                + " ON tblDctosOrdenesDetalle.IDLOCAL      =         "
                + "                         tblDctosOrdenes.IDLOCAL  "
                + " AND tblDctosOrdenesDetalle.IDTIPOORDEN =         "
                + "                    tblDctosOrdenes.IDTIPOORDEN   "
                + " AND tblDctosOrdenesDetalle.IDORDEN     =         "
                + "                        tblDctosOrdenes.IDORDEN   "
                + " WHERE  tblDctosOrdenes.IDLOCAL   =               "
                + getIdLocal() + "                                   "
                + " AND  tblDctosOrdenes.IDTIPOORDEN =               "
                + getIdTipoOrden() + "                               "
                + " AND  tblDctosOrdenes.IDORDEN     =               "
                + getIdOrden() + "                                   "
                + " AND  tblDctosOrdenesDetalle.cantidad < 0         ";

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

            // Cierra la conexion
            jdbcAccess.cleanup(connection, selectStatement, null);
            return okIngresar;

        }
    }

    // actualizaBodegaMP_TrasladoEntra
    public boolean actualizaBodegaMP_TrasladoEntra(int xIdBodegaTraslado) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                " UPDATE tblDctosOrdenesDetalle                      "
                + " SET    tblDctosOrdenesDetalle.IDBODEGASUGERIDO = "
                + xIdBodegaTraslado + ",                             "
                + "        tblDctosOrdenesDetalle.idBodega         = "
                + xIdBodegaTraslado + ",                             "
                + "        tblDctosOrdenesDetalle.idOperacion      = "
                + xIdBodegaTraslado + "                              "
                + " FROM tblDctosOrdenesDetalle                      "
                + " INNER JOIN tblDctosOrdenes                       "
                + " ON tblDctosOrdenesDetalle.IDLOCAL      =         "
                + "                         tblDctosOrdenes.IDLOCAL  "
                + " AND tblDctosOrdenesDetalle.IDTIPOORDEN =         "
                + "                    tblDctosOrdenes.IDTIPOORDEN   "
                + " AND tblDctosOrdenesDetalle.IDORDEN     =         "
                + "                        tblDctosOrdenes.IDORDEN   "
                + " WHERE  tblDctosOrdenes.IDLOCAL   =               "
                + getIdLocal() + "                                   "
                + " AND  tblDctosOrdenes.IDTIPOORDEN =               "
                + getIdTipoOrden() + "                               "
                + " AND  tblDctosOrdenes.IDORDEN     =               "
                + getIdOrden() + "                                   "
                + " AND  tblDctosOrdenesDetalle.cantidad < 0         "
                + " AND  tblDctosOrdenesDetalle.idBodega !=          "
                + getIdBodega();

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

            // Cierra la conexion
            jdbcAccess.cleanup(connection, selectStatement, null);
            return okIngresar;

        }
    }
    
    // actualizaBodegaMP_TrasladoEntra
    public boolean actualizaBodegaMPFactura(int xIdBodegaTraslado, int xIdOrden ) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                " UPDATE tblDctosOrdenesDetalle                      "
                + " SET    tblDctosOrdenesDetalle.idBodega         = "
                + xIdBodegaTraslado + ",                             "
                + "        tblDctosOrdenesDetalle.idOperacion      = "
                + xIdBodegaTraslado + "                              "
                + " FROM tblDctosOrdenesDetalle                      "
                + " INNER JOIN tblDctosOrdenes                       "
                + " ON tblDctosOrdenesDetalle.IDLOCAL      =         "
                + "                         tblDctosOrdenes.IDLOCAL  "
                + " AND tblDctosOrdenesDetalle.IDTIPOORDEN =         "
                + "                    tblDctosOrdenes.IDTIPOORDEN   "
                + " AND tblDctosOrdenesDetalle.IDORDEN     =         "
                + "                        tblDctosOrdenes.IDORDEN   "
                + " WHERE  tblDctosOrdenes.IDLOCAL   =               "
                + getIdLocal() + "                                   "
                + " AND  tblDctosOrdenes.IDTIPOORDEN =   9            "
                + " AND  tblDctosOrdenes.IDORDEN     =               "
                + xIdOrden + "                                   ";

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

            // Cierra la conexion
            jdbcAccess.cleanup(connection, selectStatement, null);
            return okIngresar;

        }
    }

    // actualizaBodegaMP_TrasladoSale
    public boolean actualizaBodegaMP_TrasladoSale(int xIdBodegaTraslado) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                " UPDATE tblDctosOrdenesDetalle                      "
                + " SET    tblDctosOrdenesDetalle.IDBODEGASUGERIDO = "
                + xIdBodegaTraslado + ",                             "
                + "        tblDctosOrdenesDetalle.idBodega         = "
                + xIdBodegaTraslado + ",                             "
                + "        tblDctosOrdenesDetalle.idOperacion      = "
                + xIdBodegaTraslado + "                              "
                + " FROM tblDctosOrdenesDetalle                      "
                + " INNER JOIN tblDctosOrdenes                       "
                + " ON tblDctosOrdenesDetalle.IDLOCAL      =         "
                + "                         tblDctosOrdenes.IDLOCAL  "
                + " AND tblDctosOrdenesDetalle.IDTIPOORDEN =         "
                + "                    tblDctosOrdenes.IDTIPOORDEN   "
                + " AND tblDctosOrdenesDetalle.IDORDEN     =         "
                + "                        tblDctosOrdenes.IDORDEN   "
                + " WHERE  tblDctosOrdenes.IDLOCAL   =               "
                + getIdLocal() + "                                   "
                + " AND  tblDctosOrdenes.IDTIPOORDEN =               "
                + getIdTipoOrden() + "                               "
                + " AND  tblDctosOrdenes.IDORDEN     =               "
                + getIdOrden() + "                                   "
                + " AND  tblDctosOrdenesDetalle.cantidad > 0         "
                + " AND  tblDctosOrdenesDetalle.idBodega !=          "
                + getIdBodega();

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

            // Cierra la conexion
            jdbcAccess.cleanup(connection, selectStatement, null);
            return okIngresar;

        }
    }

    // actualizaNota
    public boolean actualizaNota() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        boolean okIngresar = false;

        Connection connection = null;

        //
        String selectStr =
                " UPDATE tblDctosOrdenesDetalle                         "
                + " SET tblDctosOrdenesDetalle.cantidadFacturada   =    "
                + "     ( tblDctosOrdenesDetalle.cantidadFacturada +    "
                + "       tmpORD.cantidadFacturadaNota ) ,              "
                + "       tblDctosOrdenesDetalle.pesoFacturado     =    "
                + "     ( tblDctosOrdenesDetalle.pesoFacturado     +    "
                + "              tmpORD.pesoFacturadoNota ),            "
                + "        tblDctosOrdenesDetalle.estado           = 4  "
                + " FROM  tblDctosOrdenesDetalle                        "
                + " INNER JOIN                                          "
                + "  (SELECT tmpDOC.idLocalOrigen,                      "
                + "          tmpDOC.idTipoOrdenOrigen,                  "
                + "          tmpDOC.idOrdenOrigen,                      "
                + "          tmpDOC.item,                               "
                + "          tmpDET.CANTIDAD                            "
                + "          AS cantidadFacturadaNota,                  "
                + "          tmpDET.pesoFacturado                       "
                + "               AS pesoFacturadoNota                  "
                + "   FROM tblDctosOrdenesDetalle                       "
                + "                          AS tmpDET                  "
                + " INNER JOIN tblDctosOrdenesDetalle                   "
                + "                          AS tmpDOC                  "
                + " ON tmpDET.idLocalOrigen      =                      "
                + "                     tmpDOC.IDLOCAL                  "
                + " AND tmpDET.idTipoOrdenOrigen =                      "
                + "                 tmpDOC.IDTIPOORDEN                  "
                + " AND tmpDET.idOrdenOrigen     =                      "
                + "                     tmpDOC.IDORDEN                  "
                + " AND tmpDET.item = tmpDOC.item                       "
                + " WHERE   tmpDET.IDLOCAL =                            "
                + getIdLocal() + "                                      "
                + " AND  tmpDET.IDTIPOORDEN =                           "
                + getIdTipoOrden() + "                                  "
                + " AND  tmpDET.IDORDEN     =                           "
                + getIdOrden() + "                                      "
                + "                       ) AS tmpORD                   "
                + " ON tblDctosOrdenesDetalle.IDLOCAL      =            "
                + "                     tmpORD.idLocalOrigen            "
                + " AND tblDctosOrdenesDetalle.IDTIPOORDEN =            "
                + "                 tmpORD.idTipoOrdenOrigen            "
                + " AND tblDctosOrdenesDetalle.IDORDEN     =            "
                + "                     tmpORD.idOrdenOrigen            "
                + " WHERE tblDctosOrdenesDetalle.estado   != 4          ";

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

            // Cierra la conexion
            jdbcAccess.cleanup(connection, selectStatement, null);
            return okIngresar;

        }
    }

    // actualizaOT_Almacen
    public boolean actualizaOT_Almacen() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                " UPDATE tblDctosOrdenesDetalle                                   "
                + " SET    tblDctosOrdenesDetalle.pesoTerminado =                 "
                + "                          tmpPRG.pesoTerminado,                "
                + "        tblDctosOrdenesDetalle.pesoRetal     =                 "
                + "                             tmpPRG.pesoPerdido,               "
                + "  tblDctosOrdenesDetalle.cantidadTerminada   =                 "
                + "                       tmpPRG.cantidadTerminada                "
                + " FROM   tblDctosOrdenesDetalle                                 "
                + " INNER JOIN (SELECT idLocal,                                   "
                + "                    idTipoOrden,                               "
                + "                    idOrden,                                   "
                + "                    idOperacion,                               "
                + "                    itemPadre,                                 "
                + "                    SUM(cantidadPerdida)                       "
                + "                     AS cantidadPerdida,                       "
                + "                    SUM(cantidadTerminada)                     "
                + "                     AS cantidadTerminada,                     "
                + "                    SUM(pesoPerdido)                           "
                + "                           AS pesoPerdido,                     "
                + "                    SUM(pesoTerminado)                         "
                + "                          AS pesoTerminado                     "
                + "             FROM tblDctosOrdenesProgreso                      "
                + "             WHERE tblDctosOrdenesProgreso.idOperacion = 999   "
                + "             GROUP BY tblDctosOrdenesProgreso.idLocal,         "
                + "                      tblDctosOrdenesProgreso.idTipoOrden,     "
                + "                      tblDctosOrdenesProgreso.idOrden,         "
                + "                      tblDctosOrdenesProgreso.idOperacion,     "
                + "                      tblDctosOrdenesProgreso.itemPadre)       "
                + "                                                 AS tmpPRG     "
                + " ON  tblDctosOrdenesDetalle.IDLOCAL     = tmpPRG.idLocal       "
                + " AND tblDctosOrdenesDetalle.IDTIPOORDEN = tmpPRG.idTipoOrden   "
                + " AND tblDctosOrdenesDetalle.IDORDEN     = tmpPRG.idOrden       "
                + " AND tblDctosOrdenesDetalle.item        = tmpPRG.itemPadre     ";

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

            // Cierra la conexion
            jdbcAccess.cleanup(connection, selectStatement, null);
            return okIngresar;

        }
    }

    // actualizaOT_SinInicio
    public boolean actualizaOT_SinInicio() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        boolean okIngresar = false;

        Connection connection = null;

        //
        String selectStr =
                " UPDATE tblDctosOrdenesDetalle                            "
                + " SET    tblDctosOrdenesDetalle.pesoTerminado = 0,       "
                + "        tblDctosOrdenesDetalle.pesoRetal     = 0,       "
                + "  tblDctosOrdenesDetalle.cantidadTerminada   = 0        "
                + " FROM   tblDctosOrdenesDetalle                          "
                + " INNER JOIN                                             "
                + "   (SELECT tblDctosOrdenesDetalle.idLocal,              "
                + "           tblDctosOrdenesDetalle.idTipoOrden,          "
                + "           tblDctosOrdenesDetalle.idOrden,              "
                + "           tblDctosOrdenesDetalle.item AS itemPadre     "
                + " FROM  tblDctosOrdenesDetalle                           "
                + " WHERE tblDctosOrdenesDetalle.idOrden                   "
                + "    NOT IN                                              "
                + "    (SELECT tblDctosOrdenesProgreso.idOrden             "
                + "     FROM tblDctosOrdenesProgreso                       "
                + "     WHERE tblDctosOrdenesProgreso.idOperacion =        "
                + getIdOperacion() + "                                     "
                + "     AND tblDctosOrdenesProgreso.idLocal       =        "
                + getIdLocal() + "                                         "
                + "     AND tblDctosOrdenesProgreso.idTipoOrden   =        "
                + getIdTipoOrden() + "  )                                  "
                + " AND tblDctosOrdenesDetalle.idLocal            =        "
                + getIdLocal() + "                                         "
                + " AND tblDctosOrdenesDetalle.idTipoOrden        =        "
                + getIdTipoOrden() + "                                     "
                + " AND tblDctosOrdenesDetalle.idEstadoRefOriginal  != 9 ) "
                + "                                              AS tmpPRG "
                + " ON  tblDctosOrdenesDetalle.IDLOCAL     =               "
                + "                                        tmpPRG.idLocal  "
                + " AND tblDctosOrdenesDetalle.IDTIPOORDEN =               "
                + "                                    tmpPRG.idTipoOrden  "
                + " AND tblDctosOrdenesDetalle.IDORDEN     =               "
                + "                                        tmpPRG.idOrden  "
                + " AND tblDctosOrdenesDetalle.item        =               "
                + "                                      tmpPRG.itemPadre  ";

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

            // Cierra la conexion
            jdbcAccess.cleanup(connection, selectStatement, null);
            return okIngresar;

        }
    }

    // actualizaPesoPedido
    public boolean actualizaPesoPedido() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                " UPDATE tblDctosOrdenesDetalle                      "
                + " SET    tblDctosOrdenesDetalle.pesoPedido  =      "
                + getPesoPedido() + "                                "
                + " FROM   tblDctosOrdenes                           "
                + " INNER JOIN tblDctosOrdenesDetalle                "
                + " ON tblDctosOrdenes.IDLOCAL      =                "
                + "                  tblDctosOrdenesDetalle.IDLOCAL  "
                + " AND tblDctosOrdenes.IDTIPOORDEN =                "
                + "              tblDctosOrdenesDetalle.IDTIPOORDEN  "
                + " AND tblDctosOrdenes.IDORDEN     =                "
                + "                  tblDctosOrdenesDetalle.IDORDEN  "
                + " WHERE  tblDctosOrdenes.IDLOCAL =                 "
                + getIdLocal() + "                                   "
                + " AND  tblDctosOrdenes.IDTIPOORDEN =               "
                + getIdTipoOrden() + "                               "
                + " AND  tblDctosOrdenes.numeroOrden =               "
                + getNumeroOrden() + "                               "
                + " AND  tblDctosOrdenesDetalle.item =               "
                + getItem();

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

            // Cierra la conexion
            jdbcAccess.cleanup(connection, selectStatement, null);
            return okIngresar;

        }
    }

    // actualizaPedidoNuevo
    public boolean actualizaPedidoNuevo() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                " UPDATE tblDctosOrdenesDetalle                           "
                + " SET   tblDctosOrdenesDetalle.idEstadoRefOriginal = 1  "
                + " FROM      tblDctosOrdenes                             "
                + " INNER JOIN tblDctosOrdenesDetalle                     "
                + " ON tblDctosOrdenes.IDLOCAL      =                     "
                + "       tblDctosOrdenesDetalle.IDLOCAL                  "
                + " AND tblDctosOrdenes.IDTIPOORDEN =                     "
                + "   tblDctosOrdenesDetalle.IDTIPOORDEN                  "
                + " AND tblDctosOrdenes.IDORDEN     =                     "
                + "       tblDctosOrdenesDetalle.IDORDEN                  "
                + " INNER JOIN                                            "
                + "  (SELECT tblPlusFicha.idFicha,                        "
                + "          SUM(tblPlusFicha.vrEscala)                   "
                + "                        AS vrEscala                    "
                + "   FROM tblPlusFicha                                   "
                + "   WHERE (tblPlusFicha.idOperacion != 1)               "
                + "   AND (tblPlusFicha.vrEscala      != 888)             "
                + "   GROUP BY idFicha                                    "
                + "   HAVING                                              "
                + "  (SUM(tblPlusFicha.vrEscala) = 0))                    "
                + "                              AS tmpORD                "
                + " ON tmpORD.idFicha = tblDctosOrdenes.idFicha           "
                + " WHERE  tblDctosOrdenes.IDLOCAL                  =     "
                + getIdLocal() + "                                        "
                + " AND tblDctosOrdenes.IDTIPOORDEN                 =     "
                + getIdTipoOrden() + "                                    "
                + " AND  tblDctosOrdenesDetalle.idEstadoRefOriginal =     "
                + getIdEstadoRefOriginal();

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

            // Cierra la conexion
            jdbcAccess.cleanup(connection, selectStatement, null);
            return okIngresar;

        }
    }

    // actualizaPedidoPendiente
    public boolean actualizaPedidoPendiente() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                " UPDATE  tblDctosOrdenesDetalle                         "
                + " SET   tblDctosOrdenesDetalle.idEstadoRefOriginal =   "
                + getIdEstadoRefOriginal() + "                           "
                + " FROM    tblDctosOrdenes                              "
                + " INNER JOIN tblDctosOrdenesDetalle                    "
                + " ON tblDctosOrdenes.IDLOCAL      =                    "
                + "                     tblDctosOrdenesDetalle.IDLOCAL   "
                + " AND tblDctosOrdenes.IDTIPOORDEN =                    "
                + "                 tblDctosOrdenesDetalle.IDTIPOORDEN   "
                + " AND tblDctosOrdenes.IDORDEN     =                    "
                + "                     tblDctosOrdenesDetalle.IDORDEN   "
                + " WHERE tblDctosOrdenesDetalle.idLocal     =           "
                + getIdLocal() + "                                       "
                + " AND   tblDctosOrdenesDetalle.idTipoOrden =           "
                + getIdTipoOrden() + "                                   "
                + " AND   tblDctosOrdenes.idLog              =           "
                + getIdLog() + "                                         "
                + " AND   tblDctosOrdenesDetalle.item        =           "
                + getItem();

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

            // Cierra la conexion
            jdbcAccess.cleanup(connection, selectStatement, null);
            return okIngresar;

        }
    }

    // actualizaPedidoOrden
    public boolean actualizaPedidoOrden() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                " UPDATE  tblDctosOrdenesDetalle                         "
                + " SET   tblDctosOrdenesDetalle.idEstadoRefOriginal =   "
                + getIdEstadoRefOriginal() + "                           "
                + " FROM    tblDctosOrdenes                              "
                + " INNER JOIN tblDctosOrdenesDetalle                    "
                + " ON tblDctosOrdenes.IDLOCAL      =                    "
                + "                     tblDctosOrdenesDetalle.IDLOCAL   "
                + " AND tblDctosOrdenes.IDTIPOORDEN =                    "
                + "                 tblDctosOrdenesDetalle.IDTIPOORDEN   "
                + " AND tblDctosOrdenes.IDORDEN     =                    "
                + "                     tblDctosOrdenesDetalle.IDORDEN   "
                + " WHERE tblDctosOrdenesDetalle.idLocal     =           "
                + getIdLocal() + "                                       "
                + " AND   tblDctosOrdenesDetalle.idTipoOrden =           "
                + getIdTipoOrden() + "                                   "
                + " AND   tblDctosOrdenes.idOrden            =           "
                + getIdOrden() + "                                       "
                + " AND   tblDctosOrdenesDetalle.item        =           "
                + getItem();

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

            // Cierra la conexion
            jdbcAccess.cleanup(connection, selectStatement, null);
            return okIngresar;

        }
    }

    // ingresaProductoTerminado
    public boolean ingresaProductoTerminado(int xIdFicha,
            int xIdOperacion,
            double xPesoTerminada,
            double xCantidadTerminada,
            double xTotalMP,
            int xIdOrdenOrigen,
            int xIdLocalOrigen,
            int xIdTipoOrdenOrigen,
            int xIdBodega,
            int xItemPadre) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        boolean okIngresar = false;

        Connection connection = null;

        //
        String selectStr =
                " INSERT INTO tblDctosOrdenesDetalle        "
                + "            (IDLOCAL                     "
                + "            ,IDTIPOORDEN                 "
                + "            ,IDORDEN                     "
                + "            ,IDPLU                       "
                + "            ,CANTIDAD                    "
                + "            ,IDTIPO                      "
                + "            ,PORCENTAJEIVA               "
                + "            ,VRVENTAUNITARIO             "
                + "            ,ESTADO                      "
                + "            ,NOMBREPLU                   "
                + "            ,EAN                         "
                + "            ,VRVENTAORIGINAL             "
                + "            ,VRCOSTO                     "
                + "            ,VRDSCTOPIE                  "
                + "            ,PORCENTAJEDSCTO             "
                + "            ,CANTIDADPEDIDA              "
                + "            ,vrCostoNegociado            "
                + "            ,strIdBodega                 "
                + "            ,vrCostoResurtido            "
                + "            ,STRIDLISTA                  "
                + "            ,STRIDREFERENCIA             "
                + "            ,PESOTEORICO                 "
                + "            ,NOMBREUNIDADMEDIDA          "
                + "            ,IDLOCALSUGERIDO             "
                + "            ,IDBODEGASUGERIDO            "
                + "            ,marcaArteCliente            "
                + "            ,referenciaCliente           "
                + "            ,comentario                  "
                + "            ,item                        "
                + "            ,itemPadre                   "
                + "            ,idEstadoTx                  "
                + "            ,idTipoTx                    "
                + "            ,idReferenciaOriginal        "
                + "            ,idEstadoRefOriginal         "
                + "            ,idClasificacion             "
                + "            ,idResponsable               "
                + "            ,fechaEntrega                "
                + "            ,idBodega                    "
                + "            ,vrImpoconsumo               "
                + "            ,vrCostoIND                  "
                + "            ,vrIvaResurtido              "
                + "            ,idSubcuenta                 "
                + "            ,cantidadBonificada          "
                + "            ,idOrdenOrigen               "
                + "            ,idLocalOrigen               "
                + "            ,idTipoOrdenOrigen           "
                + "            ,unidadVenta                 "
                + "            ,idOperacion                 "
                + "            ,itemOrden                   "
                + "            ,cantidadTerminada           "
                + "            ,pesoTerminado)              "
                + " SELECT                                  "
                + getIdLocal() + " AS idLocal,              "
                + getIdTipoOrden() + " AS idTipoOrden,      "
                + getIdOrden() + " AS idOrden,              "
                + "        tblPlusOperacion.idPlu,          "
                + xCantidadTerminada + " *                  "
                + " tblPlusOperacion.idSigno AS cantidad    "
                + "       ,tblPlus.idTipo                   "
                + "       ,tblPlus.PORCENTAJEIVA            "
                + "       ,tblPlus.vrGeneral                "
                + "                      AS VRVENTAUNITARIO "
                + "       ,tblPlus.ESTADO,                  "
                + "        (tblCategorias.nombreCategoria + "
                + "                                   ' ' + "
                + "        tblPlus.nombrePlu) AS nombrePlu, "
                + "        tblPlusOperacion.idPlu AS ean    "
                + "       ,tblPlus.vrGeneral                "
                + "                     AS VRVENTAORIGINAL  "
                + "       ,tblPlus.vrCosto                  "
                + "       ,00 AS VRDSCTOPIE                 "
                + "       ,00 AS PORCENTAJEDSCTO,           "
                + xCantidadTerminada + " *                  "
                + " tblPlusOperacion.idSigno                "
                + "                      AS CANTIDADPEDIDA  "
                + "       ,00 AS vrCostoNegociado           "
                + "       ,00 AS strIdBodega                "
                + "       ,00 AS vrCostoResurtido           "
                + "       ,00 AS STRIDLISTA                 "
                + "       ,tblPlusOperacion.idPlu           "
                + "                  AS STRIDREFERENCIA     "
                + "       ,00 AS PESOTEORICO                "
                + "       ,'' AS NOMBREUNIDADMEDIDA ,       "
                + getIdLocal() + " AS IDLOCALSUGERIDO ,     "
                + xIdBodega + " AS IDBODEGASUGERIDO         "
                + "       ,'' marcaArteCliente              "
                + "       ,tblPlusFicha.referenciaCliente   "
                + "       ,'' AS comentario,                "
                + " tblPlusOperacion.idSigno AS item,       "
                + " tblPlusOperacion.idSigno AS itemPadre   "
                + "       ,0 AS idEstadoTx                  "
                + "       ,0 AS idTipoTx                    "
                + "       ,tblPlusOperacion.idPlu           "
                + "                AS idReferenciaOriginal  "
                + "       ,01 AS idEstadoRefOriginal        "
                + "       ,00 AS idClasificacion            "
                + "       ,'' AS idResponsable              "
                + "       ,GETDATE() fechaEntrega ,         "
                + xIdBodega + " AS idBodega                 "
                + "       ,0 AS vrImpoconsumo               "
                + "       ,tblPlus.vrCostoIND               "
                + "       ,00 AS vrIvaResurtido             "
                + "       ,00 AS idSubcuenta                "
                + "       ,00 AS cantidadBonificada,        "
                + xIdOrdenOrigen + " AS idOrdenOrigen,      "
                + xIdLocalOrigen + " AS idLocalOrigen,      "
                + xIdTipoOrdenOrigen + "AS idTipoOrdenOrigen"
                + "       ,tblPlus.idUVenta unidadVenta,    "
                + xIdOperacion + "AS idOperacion,           "
                + xItemPadre + " AS itemOrden,              "
                + xCantidadTerminada + " *                  "
                + " tblPlusOperacion.idSigno                "
                + "                   AS cantidadTerminada, "
                + xPesoTerminada + " *                      "
                + " tblPlusOperacion.idSigno                "
                + "                  AS pesoTerminado       "
                + " FROM   tblPlusFicha                     "
                + " INNER JOIN tblJobEscala                 "
                + " ON tblPlusFicha.idEscala    =           "
                + "               tblJobEscala.idEscala     "
                + " INNER JOIN tblPlusOperacion             "
                + " ON tblPlusFicha.idOperacion =           "
                + "        tblPlusOperacion.idOperacion     "
                + " AND tblPlusFicha.idEscala   =           "
                + "           tblPlusOperacion.idEscala     "
                + " INNER JOIN tblPlus                      "
                + " ON tblPlusOperacion.idPlu   =           "
                + "                      tblPlus.idPlu      "
                + " INNER JOIN tblCategorias                "
                + " ON tblPlus.idLinea          =           "
                + "              tblCategorias.idLinea      "
                + " AND tblPlus.idCategoria     =           "
                + "          tblCategorias.IdCategoria      "
                + " WHERE  (tblPlusFicha.idFicha =          "
                + xIdFicha + " )                            "
                + " AND (tblPlusFicha.idOperacion =         "
                + xIdOperacion + " )                        "
                + " AND  tblPlusFicha.vrEscala    != 0      "
                + " AND  tblPlusOperacion.idEscala IN (620) ";

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

            // Cierra la conexion
            jdbcAccess.cleanup(connection, selectStatement, null);
            return okIngresar;

        }
    }
    
    // ingresaProductoTerminado
    public boolean ingresaProductoTerminadoRetal(int xIdFicha,
            int xIdOperacion,
            double xPesoTerminada,
            double xCantidadTerminada,
            double xTotalMP,
            int xIdOrdenOrigen,
            int xIdLocalOrigen,
            int xIdTipoOrdenOrigen,
            int xIdBodega,
            int xItemPadre) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        boolean okIngresar = false;

        Connection connection = null;

        //
        String selectStr =
                " INSERT INTO tblDctosOrdenesDetalle        "
                + "            (IDLOCAL                     "
                + "            ,IDTIPOORDEN                 "
                + "            ,IDORDEN                     "
                + "            ,IDPLU                       "
                + "            ,CANTIDAD                    "
                + "            ,IDTIPO                      "
                + "            ,PORCENTAJEIVA               "
                + "            ,VRVENTAUNITARIO             "
                + "            ,ESTADO                      "
                + "            ,NOMBREPLU                   "
                + "            ,EAN                         "
                + "            ,VRVENTAORIGINAL             "
                + "            ,VRCOSTO                     "
                + "            ,VRDSCTOPIE                  "
                + "            ,PORCENTAJEDSCTO             "
                + "            ,CANTIDADPEDIDA              "
                + "            ,vrCostoNegociado            "
                + "            ,strIdBodega                 "
                + "            ,vrCostoResurtido            "
                + "            ,STRIDLISTA                  "
                + "            ,STRIDREFERENCIA             "
                + "            ,PESOTEORICO                 "
                + "            ,NOMBREUNIDADMEDIDA          "
                + "            ,IDLOCALSUGERIDO             "
                + "            ,IDBODEGASUGERIDO            "
                + "            ,marcaArteCliente            "
                + "            ,referenciaCliente           "
                + "            ,comentario                  "
                + "            ,item                        "
                + "            ,itemPadre                   "
                + "            ,idEstadoTx                  "
                + "            ,idTipoTx                    "
                + "            ,idReferenciaOriginal        "
                + "            ,idEstadoRefOriginal         "
                + "            ,idClasificacion             "
                + "            ,idResponsable               "
                + "            ,fechaEntrega                "
                + "            ,idBodega                    "
                + "            ,vrImpoconsumo               "
                + "            ,vrCostoIND                  "
                + "            ,vrIvaResurtido              "
                + "            ,idSubcuenta                 "
                + "            ,cantidadBonificada          "
                + "            ,idOrdenOrigen               "
                + "            ,idLocalOrigen               "
                + "            ,idTipoOrdenOrigen           "
                + "            ,unidadVenta                 "
                + "            ,idOperacion                 "
                + "            ,itemOrden                   "
                + "            ,cantidadTerminada           "
                + "            ,pesoTerminado)              "
                + " SELECT                                  "
                + getIdLocal() + " AS idLocal,              "
                + getIdTipoOrden() + " AS idTipoOrden,      "
                + getIdOrden() + " AS idOrden,              "
                + "        tblPlusOperacion.idPlu,          "
                + xCantidadTerminada + " *                  "
                + " tblPlusOperacion.idSigno AS cantidad    "
                + "       ,tblPlus.idTipo                   "
                + "       ,tblPlus.PORCENTAJEIVA            "
                + "       ,tblPlus.vrGeneral                "
                + "                      AS VRVENTAUNITARIO "
                + "       ,tblPlus.ESTADO,                  "
                + "        (tblCategorias.nombreCategoria + "
                + "                                   ' ' + "
                + "        tblPlus.nombrePlu) AS nombrePlu, "
                + "        tblPlusOperacion.idPlu AS ean    "
                + "       ,tblPlus.vrGeneral                "
                + "                     AS VRVENTAORIGINAL  "
                + "       ,tblPlus.vrCosto                  "
                + "       ,00 AS VRDSCTOPIE                 "
                + "       ,00 AS PORCENTAJEDSCTO,           "
                + xCantidadTerminada + " *                  "
                + " tblPlusOperacion.idSigno                "
                + "                      AS CANTIDADPEDIDA  "
                + "       ,00 AS vrCostoNegociado           "
                + "       ,00 AS strIdBodega                "
                + "       ,00 AS vrCostoResurtido           "
                + "       ,00 AS STRIDLISTA                 "
                + "       ,tblPlusOperacion.idPlu           "
                + "                  AS STRIDREFERENCIA     "
                + "       ,00 AS PESOTEORICO                "
                + "       ,'' AS NOMBREUNIDADMEDIDA ,       "
                + getIdLocal() + " AS IDLOCALSUGERIDO ,     "
                + xIdBodega + " AS IDBODEGASUGERIDO         "
                + "       ,'' marcaArteCliente              "
                + "       ,tblPlusFicha.referenciaCliente   "
                + "       ,'' AS comentario,                "
                + " tblPlusOperacion.idSigno AS item,       "
                + " tblPlusOperacion.idSigno AS itemPadre   "
                + "       ,0 AS idEstadoTx                  "
                + "       ,0 AS idTipoTx                    "
                + "       ,tblPlusOperacion.idPlu           "
                + "                AS idReferenciaOriginal  "
                + "       ,01 AS idEstadoRefOriginal        "
                + "       ,00 AS idClasificacion            "
                + "       ,'' AS idResponsable              "
                + "       ,GETDATE() fechaEntrega ,         "
                + xIdBodega + " AS idBodega                 "
                + "       ,0 AS vrImpoconsumo               "
                + "       ,tblPlus.vrCostoIND               "
                + "       ,00 AS vrIvaResurtido             "
                + "       ,00 AS idSubcuenta                "
                + "       ,00 AS cantidadBonificada,        "
                + xIdOrdenOrigen + " AS idOrdenOrigen,      "
                + xIdLocalOrigen + " AS idLocalOrigen,      "
                + xIdTipoOrdenOrigen + "AS idTipoOrdenOrigen"
                + "       ,tblPlus.idUVenta unidadVenta,    "
                + xIdBodega + "AS idOperacion,           "
                + xItemPadre + " AS itemOrden,              "
                + xCantidadTerminada + " *                  "
                + " tblPlusOperacion.idSigno                "
                + "                   AS cantidadTerminada, "
                + xPesoTerminada + " *                      "
                + " tblPlusOperacion.idSigno                "
                + "                  AS pesoTerminado       "
                + " FROM   tblPlusFicha                     "
                + " INNER JOIN tblJobEscala                 "
                + " ON tblPlusFicha.idEscala    =           "
                + "               tblJobEscala.idEscala     "
                + " INNER JOIN tblPlusOperacion             "
                + " ON tblPlusFicha.idOperacion =           "
                + "        tblPlusOperacion.idOperacion     "
                + " AND tblPlusFicha.idEscala   =           "
                + "           tblPlusOperacion.idEscala     "
                + " INNER JOIN tblPlus                      "
                + " ON tblPlusOperacion.idPlu   =           "
                + "                      tblPlus.idPlu      "
                + " INNER JOIN tblCategorias                "
                + " ON tblPlus.idLinea          =           "
                + "              tblCategorias.idLinea      "
                + " AND tblPlus.idCategoria     =           "
                + "          tblCategorias.IdCategoria      "
                + " WHERE  (tblPlusFicha.idFicha =          "
                + xIdFicha + " )                            "
                + " AND (tblPlusFicha.idOperacion =         "
                + xIdBodega + " )                        "
                + " AND  tblPlusFicha.vrEscala    != 0      "
                + " AND  tblPlusOperacion.idEscala IN (1200) ";

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

            // Cierra la conexion
            jdbcAccess.cleanup(connection, selectStatement, null);
            return okIngresar;

        }
    }

    // ingresaOTCruce
    public boolean ingresaOTCruce(int xIdLocalNew,
            int xIdTipoOrdenNew,
            int xIdOrdenNew) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        boolean okIngresar = false;

        Connection connection = null;

        //
        String selectStr =
                " INSERT INTO tblDctosOrdenesDetalle          "
                + "            ([IDLOCAL]                     "
                + "            ,[IDTIPOORDEN]                 "
                + "            ,[IDORDEN]                     "
                + "            ,[IDPLU]                       "
                + "            ,[CANTIDAD]                    "
                + "            ,[IDTIPO]                      "
                + "            ,[PORCENTAJEIVA]               "
                + "            ,[VRVENTAUNITARIO]             "
                + "            ,[ESTADO]                      "
                + "            ,[NOMBREPLU]                   "
                + "            ,[EAN]                         "
                + "            ,[VRVENTAORIGINAL]             "
                + "            ,[VRCOSTO]                     "
                + "            ,[VRDSCTOPIE]                  "
                + "            ,[PORCENTAJEDSCTO]             "
                + "            ,[CANTIDADPEDIDA]              "
                + "            ,[vrCostoNegociado]            "
                + "            ,[strIdBodega]                 "
                + "            ,[vrCostoResurtido]            "
                + "            ,[STRIDLISTA]                  "
                + "            ,[STRIDREFERENCIA]             "
                + "            ,[PESOTEORICO]                 "
                + "            ,[NOMBREUNIDADMEDIDA]          "
                + "            ,[IDLOCALSUGERIDO]             "
                + "            ,[IDBODEGASUGERIDO]            "
                + "            ,[marcaArteCliente]            "
                + "            ,[referenciaCliente]           "
                + "            ,[comentario]                  "
                + "            ,[item]                        "
                + "            ,[itemPadre]                   "
                + "            ,[idEstadoTx]                  "
                + "            ,[idTipoTx]                    "
                + "            ,[idReferenciaOriginal]        "
                + "            ,[idEstadoRefOriginal]         "
                + "            ,[idClasificacion]             "
                + "            ,[idResponsable]               "
                + "            ,[fechaEntrega]                "
                + "            ,[idBodega]                    "
                + "            ,[vrImpoconsumo]               "
                + "            ,[vrCostoIND]                  "
                + "            ,[vrIvaResurtido]              "
                + "            ,[idSubcuenta]                 "
                + "            ,[cantidadBonificada]          "
                + "            ,[idOrdenOrigen]               "
                + "            ,[idLocalOrigen]               "
                + "            ,[idTipoOrdenOrigen]           "
                + "            ,[unidadVenta]                 "
                + "            ,[idOperacion]                 "
                + "            ,[numeroOrden]                 "
                + "            ,[itemOrden]                   "
                + "            ,[vrVentaUnitarioSinIva]       "
                + "            ,[pesoPedido]                  "
                + "            ,[pesoTerminado]               "
                + "            ,[pesoRetal]                   "
                + "            ,[cantidadTerminada]           "
                + "            ,[cantidadFacturada]           "
                + "            ,[pesoFacturado]               "
                + "            ,[cantidadEntregada]           "
                + "            ,[pesoEntregado])              "
                + " SELECT                                    "
                + xIdLocalNew + " AS idLocal,                 "
                + xIdTipoOrdenNew + " AS idTipoOrden,         "
                + xIdOrdenNew + " AS idOrden                  "
                + "       ,[IDPLU]                            "
                + "       ,[CANTIDAD] * ( -1)                 "
                + "               AS cantidad                 "
                + "       ,[IDTIPO]                           "
                + "       ,[PORCENTAJEIVA]                    "
                + "       ,[VRVENTAUNITARIO]                  "
                + "       ,0 AS [ESTADO]                      "
                + "       ,[NOMBREPLU]                        "
                + "       ,[EAN]                              "
                + "       ,[VRVENTAORIGINAL]                  "
                + "       ,[VRCOSTO]                          "
                + "       ,[VRDSCTOPIE]                       "
                + "       ,[PORCENTAJEDSCTO]                  "
                + "       ,[CANTIDADPEDIDA] * ( -1)           "
                + "               AS CANTIDADPEDIDA           "
                + "       ,[vrCostoNegociado]                 "
                + "       ,[strIdBodega]                      "
                + "       ,[vrCostoResurtido]                 "
                + "       ,[STRIDLISTA]                       "
                + "       ,[STRIDREFERENCIA]                  "
                + "       ,[PESOTEORICO]                      "
                + "       ,[NOMBREUNIDADMEDIDA]               "
                + "       ,[IDLOCALSUGERIDO]                  "
                + "       ,[IDBODEGASUGERIDO]                 "
                + "       ,[marcaArteCliente]                 "
                + "       ,[referenciaCliente]                "
                + "       ,[comentario]                       "
                + "       ,[item]                             "
                + "       ,[itemPadre]                        "
                + "       ,[idEstadoTx]                       "
                + "       ,[idTipoTx]                         "
                + "       ,[idReferenciaOriginal]             "
                + "       ,[idEstadoRefOriginal]              "
                + "       ,[idClasificacion]                  "
                + "       ,[idResponsable]                    "
                + "       ,[fechaEntrega]                     "
                + "       ,[idBodega]                         "
                + "       ,[vrImpoconsumo]                    "
                + "       ,[vrCostoIND]                       "
                + "       ,[vrIvaResurtido]                   "
                + "       ,[idSubcuenta]                      "
                + "       ,[cantidadBonificada]               "
                + "       ,[idOrdenOrigen]                    "
                + "       ,[idLocalOrigen]                    "
                + "       ,[idTipoOrdenOrigen]                "
                + "       ,[unidadVenta]                      "
                + "       ,[idOperacion]                      "
                + "       ,[numeroOrden]                      "
                + "       ,[itemOrden]                        "
                + "       ,[vrVentaUnitarioSinIva]            "
                + "       ,[pesoPedido]                       "
                + "       ,[pesoTerminado] * ( -1)            "
                + "             AS [pesoTerminado]            "
                + "       ,[pesoRetal]                        "
                + "       ,[cantidadTerminada] * ( -1)        "
                + "             AS [cantidadTerminada]        "
                + "       ,[cantidadFacturada]                "
                + "       ,[pesoFacturado]                    "
                + "       ,[cantidadEntregada]                "
                + "       ,[pesoEntregado]                    "
                + "   FROM tblDctosOrdenesDetalle             "
                + " WHERE tblDctosOrdenesDetalle.IDLOCAL   =  "
                + getIdLocal() + "                            "
                + " AND tblDctosOrdenesDetalle.IDTIPOORDEN =  "
                + getIdTipoOrden() + "                        "
                + " AND tblDctosOrdenesDetalle.IDORDEN     =  "
                + getIdOrden();

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

            // Cierra la conexion
            jdbcAccess.cleanup(connection, selectStatement, null);
            return okIngresar;

        }
    }

    // ingresaPTComplemento
    public boolean ingresaPTComplemento(int xIdBodega) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        boolean okIngresar = false;

        Connection connection = null;

        //
        String selectStr =
                " INSERT INTO tblDctosOrdenesDetalle          "
                + "            ([IDLOCAL]                     "
                + "            ,[IDTIPOORDEN]                 "
                + "            ,[IDORDEN]                     "
                + "            ,[IDPLU]                       "
                + "            ,[CANTIDAD]                    "
                + "            ,[IDTIPO]                      "
                + "            ,[PORCENTAJEIVA]               "
                + "            ,[VRVENTAUNITARIO]             "
                + "            ,[ESTADO]                      "
                + "            ,[NOMBREPLU]                   "
                + "            ,[EAN]                         "
                + "            ,[VRVENTAORIGINAL]             "
                + "            ,[VRCOSTO]                     "
                + "            ,[VRDSCTOPIE]                  "
                + "            ,[PORCENTAJEDSCTO]             "
                + "            ,[CANTIDADPEDIDA]              "
                + "            ,[vrCostoNegociado]            "
                + "            ,[strIdBodega]                 "
                + "            ,[vrCostoResurtido]            "
                + "            ,[STRIDLISTA]                  "
                + "            ,[STRIDREFERENCIA]             "
                + "            ,[PESOTEORICO]                 "
                + "            ,[NOMBREUNIDADMEDIDA]          "
                + "            ,[IDLOCALSUGERIDO]             "
                + "            ,[IDBODEGASUGERIDO]            "
                + "            ,[marcaArteCliente]            "
                + "            ,[referenciaCliente]           "
                + "            ,[comentario]                  "
                + "            ,[item]                        "
                + "            ,[itemPadre]                   "
                + "            ,[idEstadoTx]                  "
                + "            ,[idTipoTx]                    "
                + "            ,[idReferenciaOriginal]        "
                + "            ,[idEstadoRefOriginal]         "
                + "            ,[idClasificacion]             "
                + "            ,[idResponsable]               "
                + "            ,[fechaEntrega]                "
                + "            ,[idBodega]                    "
                + "            ,[vrImpoconsumo]               "
                + "            ,[vrCostoIND]                  "
                + "            ,[vrIvaResurtido]              "
                + "            ,[idSubcuenta]                 "
                + "            ,[cantidadBonificada]          "
                + "            ,[idOrdenOrigen]               "
                + "            ,[idLocalOrigen]               "
                + "            ,[idTipoOrdenOrigen]           "
                + "            ,[unidadVenta]                 "
                + "            ,[idOperacion]                 "
                + "            ,[numeroOrden]                 "
                + "            ,[itemOrden]                   "
                + "            ,[vrVentaUnitarioSinIva]       "
                + "            ,[pesoPedido]                  "
                + "            ,[pesoTerminado]               "
                + "            ,[pesoRetal]                   "
                + "            ,[cantidadTerminada]           "
                + "            ,[cantidadFacturada]           "
                + "            ,[pesoFacturado]               "
                + "            ,[cantidadEntregada]           "
                + "            ,[pesoEntregado])              "
                + " SELECT TOP 1 [IDLOCAL]                    "
                + "       ,[IDTIPOORDEN]                      "
                + "       ,[IDORDEN]                          "
                + "       ,[IDPLU]                            "
                + "       ,[CANTIDAD]                         "
                + "       ,[IDTIPO]                           "
                + "       ,[PORCENTAJEIVA]                    "
                + "       ,[VRVENTAUNITARIO]                  "
                + "       ,[ESTADO]                           "
                + "       ,[NOMBREPLU]                        "
                + "       ,[EAN]                              "
                + "       ,[VRVENTAORIGINAL]                  "
                + "       ,[VRCOSTO]                          "
                + "       ,[VRDSCTOPIE]                       "
                + "       ,[PORCENTAJEDSCTO]                  "
                + "       ,[CANTIDADPEDIDA]                   "
                + "       ,[vrCostoNegociado]                 "
                + "       ,[strIdBodega]                      "
                + "       ,[vrCostoResurtido]                 "
                + "       ,[STRIDLISTA]                       "
                + "       ,[STRIDREFERENCIA]                  "
                + "       ,[PESOTEORICO]                      "
                + "       ,[NOMBREUNIDADMEDIDA]               "
                + "       ,[IDLOCALSUGERIDO],                 "
                + xIdBodega + " AS [IDBODEGASUGERIDO]         "
                + "       ,[marcaArteCliente]                 "
                + "       ,[referenciaCliente]                "
                + "       ,[comentario]                       "
                + "       ,[item]                             "
                + "       ,[itemPadre]                        "
                + "       ,[idEstadoTx]                       "
                + "       ,[idTipoTx]                         "
                + "       ,[idReferenciaOriginal]             "
                + "       ,[idEstadoRefOriginal]              "
                + "       ,[idClasificacion]                  "
                + "       ,[idResponsable]                    "
                + "       ,[fechaEntrega],                    "
                + xIdBodega + " AS [idBodega]                 "
                + "       ,[vrImpoconsumo]                    "
                + "       ,[vrCostoIND]                       "
                + "       ,[vrIvaResurtido]                   "
                + "       ,[idSubcuenta]                      "
                + "       ,[cantidadBonificada]               "
                + "       ,[idOrdenOrigen]                    "
                + "       ,[idLocalOrigen]                    "
                + "       ,[idTipoOrdenOrigen]                "
                + "       ,[unidadVenta],                     "
                + xIdBodega + " AS [idOperacion]              "
                + "       ,[numeroOrden]                      "
                + "       ,[itemOrden]                        "
                + "       ,[vrVentaUnitarioSinIva]            "
                + "       ,[pesoPedido]                       "
                + "       ,[pesoTerminado]                    "
                + "       ,[pesoRetal]                        "
                + "       ,[cantidadTerminada]                "
                + "       ,[cantidadFacturada]                "
                + "       ,[pesoFacturado]                    "
                + "       ,[cantidadEntregada]                "
                + "       ,[pesoEntregado]                    "
                + "   FROM tblDctosOrdenesDetalle             "
                + " WHERE tblDctosOrdenesDetalle.idLocal   =  "
                + getIdLocal() + "                            "
                + " AND tblDctosOrdenesDetalle.idTipoOrden =  "
                + getIdTipoOrden() + "                        "
                + " AND tblDctosOrdenesDetalle.idOrden     =  "
                + getIdOrden() + "                            "
                + " AND tblDctosOrdenesDetalle.idPlu       =  "
                + getIdPlu();


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

            // Cierra la conexion
            jdbcAccess.cleanup(connection, selectStatement, null);
            return okIngresar;

        }
    }

    // ingresaPTComplemento
    public boolean ingresaPTComplementoES(int xIdBodega) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        boolean okIngresar = false;

        Connection connection = null;

        //
        String selectStr =
                " INSERT INTO tblDctosOrdenesDetalle          "
                + "            ([IDLOCAL]                     "
                + "            ,[IDTIPOORDEN]                 "
                + "            ,[IDORDEN]                     "
                + "            ,[IDPLU]                       "
                + "            ,[CANTIDAD]                    "
                + "            ,[IDTIPO]                      "
                + "            ,[PORCENTAJEIVA]               "
                + "            ,[VRVENTAUNITARIO]             "
                + "            ,[ESTADO]                      "
                + "            ,[NOMBREPLU]                   "
                + "            ,[EAN]                         "
                + "            ,[VRVENTAORIGINAL]             "
                + "            ,[VRCOSTO]                     "
                + "            ,[VRDSCTOPIE]                  "
                + "            ,[PORCENTAJEDSCTO]             "
                + "            ,[CANTIDADPEDIDA]              "
                + "            ,[vrCostoNegociado]            "
                + "            ,[strIdBodega]                 "
                + "            ,[vrCostoResurtido]            "
                + "            ,[STRIDLISTA]                  "
                + "            ,[STRIDREFERENCIA]             "
                + "            ,[PESOTEORICO]                 "
                + "            ,[NOMBREUNIDADMEDIDA]          "
                + "            ,[IDLOCALSUGERIDO]             "
                + "            ,[IDBODEGASUGERIDO]            "
                + "            ,[marcaArteCliente]            "
                + "            ,[referenciaCliente]           "
                + "            ,[comentario]                  "
                + "            ,[item]                        "
                + "            ,[itemPadre]                   "
                + "            ,[idEstadoTx]                  "
                + "            ,[idTipoTx]                    "
                + "            ,[idReferenciaOriginal]        "
                + "            ,[idEstadoRefOriginal]         "
                + "            ,[idClasificacion]             "
                + "            ,[idResponsable]               "
                + "            ,[fechaEntrega]                "
                + "            ,[idBodega]                    "
                + "            ,[vrImpoconsumo]               "
                + "            ,[vrCostoIND]                  "
                + "            ,[vrIvaResurtido]              "
                + "            ,[idSubcuenta]                 "
                + "            ,[cantidadBonificada]          "
                + "            ,[idOrdenOrigen]               "
                + "            ,[idLocalOrigen]               "
                + "            ,[idTipoOrdenOrigen]           "
                + "            ,[unidadVenta]                 "
                + "            ,[idOperacion]                 "
                + "            ,[numeroOrden]                 "
                + "            ,[itemOrden]                   "
                + "            ,[vrVentaUnitarioSinIva]       "
                + "            ,[pesoPedido]                  "
                + "            ,[pesoTerminado]               "
                + "            ,[pesoRetal]                   "
                + "            ,[cantidadTerminada]           "
                + "            ,[cantidadFacturada]           "
                + "            ,[pesoFacturado]               "
                + "            ,[cantidadEntregada]           "
                + "            ,[pesoEntregado])              "
                + " SELECT TOP 1 [IDLOCAL]                    "
                + "       ,[IDTIPOORDEN]                      "
                + "       ,[IDORDEN]                          "
                + "       ,[IDPLU]                            "
                + "       ,( [CANTIDAD] * (-1) )              "
                + "                           AS CANTIDAD     "
                + "       ,[IDTIPO]                           "
                + "       ,[PORCENTAJEIVA]                    "
                + "       ,[VRVENTAUNITARIO]                  "
                + "       ,[ESTADO]                           "
                + "       ,[NOMBREPLU]                        "
                + "       ,[EAN]                              "
                + "       ,[VRVENTAORIGINAL]                  "
                + "       ,[VRCOSTO]                          "
                + "       ,[VRDSCTOPIE]                       "
                + "       ,[PORCENTAJEDSCTO]                  "
                + "       ,( [CANTIDADPEDIDA] * (-1) )        "
                + "                    AS CANTIDADPEDIDA      "
                + "       ,[vrCostoNegociado]                 "
                + "       ,[strIdBodega]                      "
                + "       ,[vrCostoResurtido]                 "
                + "       ,[STRIDLISTA]                       "
                + "       ,[STRIDREFERENCIA]                  "
                + "       ,[PESOTEORICO]                      "
                + "       ,[NOMBREUNIDADMEDIDA]               "
                + "       ,[IDLOCALSUGERIDO],                 "
                + xIdBodega + " AS [IDBODEGASUGERIDO]         "
                + "       ,[marcaArteCliente]                 "
                + "       ,[referenciaCliente]                "
                + "       ,[comentario]                       "
                + "       ,[item]                             "
                + "       ,[itemPadre]                        "
                + "       ,[idEstadoTx]                       "
                + "       ,[idTipoTx]                         "
                + "       ,[idReferenciaOriginal]             "
                + "       ,[idEstadoRefOriginal]              "
                + "       ,[idClasificacion]                  "
                + "       ,[idResponsable]                    "
                + "       ,[fechaEntrega],                    "
                + xIdBodega + " AS [idBodega]                 "
                + "       ,[vrImpoconsumo]                    "
                + "       ,[vrCostoIND]                       "
                + "       ,[vrIvaResurtido]                   "
                + "       ,[idSubcuenta]                      "
                + "       ,[cantidadBonificada]               "
                + "       ,[idOrdenOrigen]                    "
                + "       ,[idLocalOrigen]                    "
                + "       ,[idTipoOrdenOrigen]                "
                + "       ,[unidadVenta],                     "
                + xIdBodega + " AS [idOperacion]              "
                + "       ,[numeroOrden]                      "
                + "       ,[itemOrden]                        "
                + "       ,[vrVentaUnitarioSinIva]            "
                + "       ,[pesoPedido]                       "
                + "       ,( [pesoTerminado] * (-1) )         "
                + "                    AS pesoTerminado       "
                + "       ,[pesoRetal]                        "
                + "       ,( [cantidadTerminada] * (-1) )     "
                + "                    AS cantidadTerminada   "
                + "       ,[cantidadFacturada]                "
                + "       ,[pesoFacturado]                    "
                + "       ,[cantidadEntregada]                "
                + "       ,[pesoEntregado]                    "
                + "   FROM tblDctosOrdenesDetalle             "
                + " WHERE tblDctosOrdenesDetalle.idLocal   =  "
                + getIdLocal() + "                            "
                + " AND tblDctosOrdenesDetalle.idTipoOrden =  "
                + getIdTipoOrden() + "                        "
                + " AND tblDctosOrdenesDetalle.idOrden     =  "
                + getIdOrden() + "                            "
                + " AND tblDctosOrdenesDetalle.idPlu       =  "
                + getIdPlu();


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

            // Cierra la conexion
            jdbcAccess.cleanup(connection, selectStatement, null);
            return okIngresar;

        }
    }

    // ingresaCosto
    public boolean ingresaCosto(double xPesoTerminada,
            int xIdOrdenOrigen,
            int xIdLocalOrigen,
            int xIdTipoOrdenOrigen,
            int xIdBodega,
            int xItem,
            int xIdOperacion) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        boolean okIngresar = false;

        Connection connection = null;
        String selectStr =
                " INSERT INTO tblDctosOrdenesDetalle       "
                + "            (IDLOCAL                    "
                + "            ,IDTIPOORDEN                "
                + "            ,IDORDEN                    "
                + "            ,IDPLU                      "
                + "            ,CANTIDAD                   "
                + "            ,IDTIPO                     "
                + "            ,PORCENTAJEIVA              "
                + "            ,VRVENTAUNITARIO            "
                + "            ,ESTADO                     "
                + "            ,NOMBREPLU                  "
                + "            ,EAN                        "
                + "            ,VRVENTAORIGINAL            "
                + "            ,VRCOSTO                    "
                + "            ,VRDSCTOPIE                 "
                + "            ,PORCENTAJEDSCTO            "
                + "            ,CANTIDADPEDIDA             "
                + "            ,vrCostoNegociado           "
                + "            ,strIdBodega                "
                + "            ,vrCostoResurtido           "
                + "            ,STRIDLISTA                 "
                + "            ,STRIDREFERENCIA            "
                + "            ,PESOTEORICO                "
                + "            ,NOMBREUNIDADMEDIDA         "
                + "            ,IDLOCALSUGERIDO            "
                + "            ,IDBODEGASUGERIDO           "
                + "            ,marcaArteCliente           "
                + "            ,referenciaCliente          "
                + "            ,comentario                 "
                + "            ,item                       "
                + "            ,itemPadre                  "
                + "            ,idEstadoTx                 "
                + "            ,idTipoTx                   "
                + "            ,idReferenciaOriginal       "
                + "            ,idEstadoRefOriginal        "
                + "            ,idClasificacion            "
                + "            ,idResponsable              "
                + "            ,fechaEntrega               "
                + "            ,idBodega                   "
                + "            ,vrImpoconsumo              "
                + "            ,vrCostoIND                 "
                + "            ,vrIvaResurtido             "
                + "            ,idSubcuenta                "
                + "            ,cantidadBonificada         "
                + "            ,idOrdenOrigen              "
                + "            ,idLocalOrigen              "
                + "            ,idTipoOrdenOrigen          "
                + "            ,unidadVenta                "
                + "            ,idOperacion)               "
                + " SELECT                                 "
                + getIdLocal() + " AS idLocal,             "
                + getIdTipoOrden() + " AS idTipoOrden,     "
                + getIdOrden() + " AS idOrden,             "
                + "  tblPlus.idPlu,                        "
                + xPesoTerminada + " AS cantidad           "
                + "         ,tblPlus.idTipo                  "
                + "         ,tblPlus.PORCENTAJEIVA           "
                + "         ,tblPlus.vrGeneral               "
                + "                    AS VRVENTAUNITARIO    "
                + "         ,tblPlus.ESTADO,                 "
                + "         (tblCategorias.nombreCategoria   "
                + "                               + ' ' +    "
                + "       tblPlus.nombrePlu) AS nombrePlu,   "
                + "          tblPlus.idPlu AS ean            "
                + "         ,tblPlus.vrGeneral               "
                + "                    AS VRVENTAORIGINAL    "
                + "         ,tblPlus.vrCosto                 "
                + "         ,00 AS VRDSCTOPIE                "
                + "         ,00 AS PORCENTAJEDSCTO,          "
                + xPesoTerminada + " AS CANTIDADPEDIDA       "
                + "         ,00 AS vrCostoNegociado          "
                + "         ,00 AS strIdBodega               "
                + "         ,00 AS vrCostoResurtido          "
                + "         ,00 AS STRIDLISTA                "
                + "         ,tblPlus.idPlu                   "
                + "                    AS STRIDREFERENCIA    "
                + "         ,00 AS PESOTEORICO               "
                + "         ,'' AS NOMBREUNIDADMEDIDA ,      "
                + getIdLocal() + " AS IDLOCALSUGERIDO ,    "
                + xIdBodega + " AS IDBODEGASUGERIDO        "
                + "         ,'' marcaArteCliente             "
                + "         ,'' AS referenciaCliente         "
                + "         ,'' AS comentario,               "
                + xItem + " AS item,                         "
                + xItem + " AS itemPadre                     "
                + "         ,0 AS idEstadoTx                 "
                + "         ,0 AS idTipoTx                   "
                + "         ,tblPlus.idPlu                   "
                + "               AS idReferenciaOriginal    "
                + "         ,01 AS idEstadoRefOriginal       "
                + "         ,00 AS idClasificacion           "
                + "         ,'' AS idResponsable             "
                + "         ,GETDATE() fechaEntrega ,        "
                + xIdBodega + " AS idBodega                  "
                + "         ,0 AS vrImpoconsumo              "
                + "         ,tblPlus.vrCosto AS vrCostoIND   "
                + "         ,00 AS vrIvaResurtido            "
                + "         ,00 AS idSubcuenta               "
                + "         ,00 AS cantidadBonificada,       "
                + xIdOrdenOrigen + " AS idOrdenOrigen,       "
                + xIdLocalOrigen + " AS idLocalOrigen,       "
                + xIdTipoOrdenOrigen + "AS idTipoOrdenOrigen "
                + "         ,tblPlus.idUVenta unidadVenta,   "
                + xIdOperacion + " AS idOperacion            "
                + "   FROM    tblPlus                        "
                + "   INNER JOIN tblCategorias               "
                + "   ON tblPlus.idLinea          =          "
                + "                tblCategorias.idLinea     "
                + "   AND tblPlus.idCategoria     =          "
                + "            tblCategorias.IdCategoria     "
                + "   WHERE tblPlus.IDpLU =                  "
                + getIdPlu();

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

            // Cierra la conexion
            jdbcAccess.cleanup(connection, selectStatement, null);
            return okIngresar;

        }
    }

    // ingresaDetalle
    public boolean ingresaDetalle() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        //
        String selectStr =
                "INSERT INTO tblDctosOrdenesDetalle (idLocal,   "
                + "                      idTipoOrden,           "
                + "                      idOrden,               "
                + "                      cantidad,              "
                + "                      nombrePlu,             "
                + "                      idPlu,                 "
                + "                      idTipo,                "
                + "                      estado,                "
                + "                      porcentajeIva,         "
                + "                      vrVentaUnitario,       "
                + "                      ean,                   "
                + "                      vrVentaOriginal,       "
                + "                      vrCosto,               "
                + "                      vrDsctoPie,            "
                + "                      porcentajeDscto,       "
                + "                      cantidadPedida,        "
                + "                      vrCostoNegociado,      "
                + "                      strIdBodega,           "
                + "                      vrCostoResurtido,      "
                + "                      strIdLista ,           "
                + "                      strIdReferencia,       "
                + "                      pesoTeorico,           "
                + "                      nombreUnidadMedida,    "
                + "                      idLocalSugerido,       "
                + "                      idBodegaSugerido,      "
                + "                      marcaArteCliente,      "
                + "                      referenciaCliente,     "
                + "                      comentario,            "
                + "                      item,                  "
                + "                      itemPadre,             "
                + "                      idEstadoTx,            "
                + "                      idTipoTx,              "
                + "                      idReferenciaOriginal,  "
                + "                      idEstadoRefOriginal,   "
                + "                      idClasificacion,       "
                + "                      idResponsable,         "
                + "                      fechaEntrega,          "
                + "                      idBodega,              "
                + "                      vrImpoconsumo,         "
                + "                      vrCostoIND,            "
                + "                      vrIvaResurtido,        "
                + "                      idSubcuenta,           "
                + "                      unidadVenta,           "
                + "                      vrVentaUnitarioSinIva, "
                + "                      pesoPedido,            "
                + "                      pesoTerminado,         "
                + "                      pesoRetal,             "
                + "                      cantidadTerminada,     "
                + "                      cantidadFacturada,     "
                + "                      pesoFacturado,         "
                + "                      idLocalOrigen,         "
                + "                      idTipoOrdenOrigen,     "
                + "                      idOrdenOrigen,         "
                + "                      cantidadEntregada,     "
                + "                      pesoEntregado,         "
                + "                      numeroOrden,           "
                + "                      idOperacion)           "
                + "VALUES (" + getIdLocal() + ", "
                + getIdTipoOrden() + ", "
                + getIdOrden() + ", "
                + getCantidad() + ",'"
                + getNombrePlu() + "',"
                + getIdPlu() + ", "
                + getIdTipo() + ", "
                + getEstado() + ", "
                + getPorcentajeIva() + ", "
                + getVrVentaUnitario() + ",'"
                + getEan() + "', "
                + getVrVentaOriginal() + ", "
                + getVrCosto() + ", "
                + getVrDsctoPie() + ", "
                + getPorcentajeDscto() + ", "
                + getCantidadPedida() + ", "
                + getVrCostoNegociado() + ",'"
                + getStrIdBodega() + "',"
                + getVrCostoResurtido() + ",'"
                + getStrIdLista() + "','"
                + getStrIdReferencia() + "', "
                + getPesoTeorico() + ",'"
                + getNombreUnidadMedida() + "', "
                + getIdLocalSugerido() + ",'"
                + getIdBodegaSugerido() + "','"
                + getMarcaArteCliente() + "','"
                + getReferenciaCliente() + "','"
                + getComentario() + "',"
                + getItem() + ", "
                + getItemPadre() + ", "
                + getIdEstadoTx() + ", "
                + getIdTipoTx() + ",'"
                + getIdReferenciaOriginal() + "', "
                + getIdEstadoRefOriginal() + ", "
                + getIdClasificacion() + ", "
                + getIdResponsable() + ",'"
                + getFechaEntregaSqlServer() + "',"
                + getIdBodega() + ","
                + getVrImpoconsumo() + ","
                + getVrCostoIND() + ","
                + getVrIvaResurtido() + ","
                + getIdSubcuenta() + ","
                + getUnidadVenta() + ","
                + getVrVentaUnitarioSinIva() + ","
                + getPesoPedido() + ","
                + getPesoTerminado() + ","
                + getPesoRetal() + ","
                + getCantidadTerminada() + ","
                + getCantidadFacturada() + ","
                + getPesoFacturado() + ","
                + getIdLocalOrigen() + ","
                + getIdTipoOrdenOrigen() + ","
                + getIdOrdenOrigen() + ","
                + getCantidadEntregada() + ","
                + getPesoEntregado() + ","
                + getNumeroOrden() + ","
                + getIdOperacion() + ")";


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
            return okIngresar;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {

            // Cierra el Resultset
            jdbcAccess.cleanup(connection, selectStatement, null);
            return okIngresar;
        }
    }

    // ingresaDetalleOrigen
    public boolean ingresaDetalleOrigen() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                "INSERT INTO tblDctosOrdenesDetalle (idLocal,   "
                + "                      idTipoOrden,           "
                + "                      idOrden,               "
                + "                      cantidad,              "
                + "                      nombrePlu,             "
                + "                      idPlu,                 "
                + "                      idTipo,                "
                + "                      estado,                "
                + "                      porcentajeIva,         "
                + "                      vrVentaUnitario,       "
                + "                      ean,                   "
                + "                      vrVentaOriginal,       "
                + "                      vrCosto,               "
                + "                      vrDsctoPie,            "
                + "                      porcentajeDscto,       "
                + "                      cantidadPedida,        "
                + "                      vrCostoNegociado,      "
                + "                      strIdBodega,           "
                + "                      vrCostoResurtido,      "
                + "                      strIdLista ,           "
                + "                      strIdReferencia,       "
                + "                      pesoTeorico,           "
                + "                      nombreUnidadMedida,    "
                + "                      idLocalSugerido,       "
                + "                      idBodegaSugerido,      "
                + "                      marcaArteCliente,      "
                + "                      referenciaCliente,     "
                + "                      comentario,            "
                + "                      item,                  "
                + "                      itemPadre,             "
                + "                      idEstadoTx,            "
                + "                      idTipoTx,              "
                + "                      idReferenciaOriginal,  "
                + "                      idEstadoRefOriginal,   "
                + "                      idClasificacion,       "
                + "                      idResponsable,         "
                + "                      fechaEntrega,          "
                + "                      idBodega,              "
                + "                      vrImpoconsumo,         "
                + "                      vrCostoIND,            "
                + "                      vrIvaResurtido,        "
                + "                      idSubcuenta,           "
                + "                      unidadVenta,           "
                + "                      idLocalOrigen,         "
                + "                      idTipoOrdenOrigen,     "
                + "                      idOrdenOrigen,         "
                + "                      itemOrden,             "
                + "                      vrVentaUnitarioSinIva, "
                + "                      cantidadTerminada,     "
                + "                      cantidadFacturada,     "
                + "                      cantidadEntregada,     "
                + "                      pesoEntregado,         "
                + "                      pesoPedido,            "
                + "                      pesoTerminado,         "
                + "                      pesoRetal)             "
                + "VALUES (" + getIdLocal() + ", "
                + getIdTipoOrden() + ", "
                + getIdOrden() + ", "
                + getCantidad() + ",'"
                + getNombrePlu() + "',"
                + getIdPlu() + ", "
                + getIdTipo() + ", "
                + getEstado() + ", "
                + getPorcentajeIva() + ", "
                + getVrVentaUnitario() + ",'"
                + getEan() + "', "
                + getVrVentaOriginal() + ", "
                + getVrCosto() + ", "
                + getVrDsctoPie() + ", "
                + getPorcentajeDscto() + ", "
                + getCantidadPedida() + ", "
                + getVrCostoNegociado() + ",'"
                + getStrIdBodega() + "',"
                + getVrCostoResurtido() + ",'"
                + getStrIdLista() + "','"
                + getStrIdReferencia() + "', "
                + getPesoTeorico() + ",'"
                + getNombreUnidadMedida() + "', "
                + getIdLocalSugerido() + ",'"
                + getIdBodegaSugerido() + "','"
                + getMarcaArteCliente() + "','"
                + getReferenciaCliente() + "','"
                + getComentario() + "',"
                + getItem() + ", "
                + getItemPadre() + ", "
                + getIdEstadoTx() + ", "
                + getIdTipoTx() + ",'"
                + getIdReferenciaOriginal() + "', "
                + getIdEstadoRefOriginal() + ", "
                + getIdClasificacion() + ", "
                + getIdResponsable() + ",'"
                + getFechaEntregaSqlServer() + "',"
                + getIdBodega() + ","
                + getVrImpoconsumo() + ","
                + getVrCostoIND() + ","
                + getVrIvaResurtido() + ","
                + getIdSubcuenta() + ","
                + getUnidadVenta() + ","
                + getIdLocalOrigen() + ","
                + getIdTipoOrdenOrigen() + ","
                + getIdOrdenOrigen() + ","
                + getItemOrden() + ","
                + getVrVentaUnitarioSinIva() + ","
                + getCantidadTerminada() + ","
                + getCantidadFacturada() + ","
                + getCantidadEntregada() + ","
                + getPesoEntregado() + ","
                + getPesoPedido() + ","
                + getPesoTerminado() + ","
                + getPesoRetal() + ")";

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
            return okIngresar;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {

            // Cierra el Resultset
            jdbcAccess.cleanup(connection, selectStatement, null);
            return okIngresar;
        }
    }

    // ingresa
    public boolean ingresa(int xIdLocal) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        //
        String selectStr =
                "INSERT INTO tblDctosOrdenesDetalle (idLocal, "
                + "                      idTipoOrden,           "
                + "                      idOrden,               "
                + "                      cantidad,              "
                + "                      nombrePlu,             "
                + "                      idPlu,                 "
                + "                      idTipo,                "
                + "                      estado,                "
                + "                      porcentajeIva,         "
                + "                      vrVentaUnitario,       "
                + "                      ean,                   "
                + "                      vrVentaOriginal,       "
                + "                      vrCosto,               "
                + "                      vrDsctoPie,            "
                + "                      porcentajeDscto,       "
                + "                      cantidadPedida,        "
                + "                      vrCostoNegociado,      "
                + "                      strIdBodega,           "
                + "                      vrCostoResurtido,      "
                + "                      strIdLista ,           "
                + "                      strIdReferencia,       "
                + "                      pesoTeorico,           "
                + "                      nombreUnidadMedida,    "
                + "                      idLocalSugerido,       "
                + "                      idBodegaSugerido,      "
                + "                      marcaArteCliente,      "
                + "                      referenciaCliente,     "
                + "                      comentario,            "
                + "                      item,                  "
                + "                      itemPadre,             "
                + "                      idEstadoTx,            "
                + "                      idTipoTx,              "
                + "                      idReferenciaOriginal,  "
                + "                      idEstadoRefOriginal,   "
                + "                      idClasificacion,       "
                + "                      idResponsable,         "
                + "                      idBodega,              "
                + "                      vrImpoconsumo)         "
                + "VALUES (" + getIdLocal() + ", "
                + getIdTipoOrden() + ", "
                + getIdOrden() + ", "
                + getCantidad() + ",'"
                + getNombrePlu() + "',"
                + getIdPlu() + ", "
                + getIdTipo() + ", "
                + getEstado() + ", "
                + getPorcentajeIva() + ", "
                + getVrVentaUnitario() + ",'"
                + getEan() + "', "
                + getVrVentaOriginal() + ", "
                + getVrCosto() + ", "
                + getVrDsctoPie() + ", "
                + getPorcentajeDscto() + ", "
                + getCantidadPedida() + ", "
                + getVrCostoNegociado() + ",'"
                + getStrIdBodega() + "',"
                + getVrCostoResurtido() + ",'"
                + getStrIdLista() + "','"
                + getStrIdReferencia() + "', "
                + getPesoTeorico() + ",'"
                + getNombreUnidadMedida() + "', "
                + getIdLocalSugerido() + ",'"
                + getIdBodegaSugerido() + "','"
                + getMarcaArteCliente() + "','"
                + getReferenciaCliente() + "','"
                + getComentario() + "',"
                + getItem() + ", "
                + getItemPadre() + ", "
                + getIdEstadoTx() + ", "
                + getIdTipoTx() + ",'"
                + getIdReferenciaOriginal() + "', "
                + getIdEstadoRefOriginal() + ", "
                + getIdClasificacion() + ", "
                + getIdResponsable() + ","
                + getIdBodega() + ","
                + getVrImpoconsumo() + ")";


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
            return okIngresar;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {

            // Cierra el Resultset
            jdbcAccess.cleanup(connection, selectStatement, null);
            return okIngresar;
        }
    }

    // actualiza
    public boolean actualiza(int xIdTipoOrdenNew,
            int xIdOrdenNew) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                "UPDATE tblDctosOrdenesDetalle                 "
                + "SET tblDctosOrdenesDetalle.IDTIPOORDEN    = "
                + xIdTipoOrdenNew + "                         ,"
                + "    tblDctosOrdenesDetalle.IDORDEN        = "
                + xIdOrdenNew + "                              "
                + "WHERE tblDctosOrdenesDetalle.IDLOCAL      = "
                + getIdLocal() + "                             "
                + "AND   tblDctosOrdenesDetalle.IDTIPOORDEN  = "
                + getIdTipoOrden() + "                         "
                + "AND   tblDctosOrdenesDetalle.IDORDEN      = "
                + getIdOrden() + "                             ";

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
            return okIngresar;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {

            // conexion
            jdbcAccess.cleanup(connection, selectStatement, null);
            return okIngresar;
        }
    }

    // ingresoParcialCompra
    public boolean ingresoParcialCompra(int xIdLocalNew,
            int xIdTipoOrdenNew,
            int xIdOrdenNew) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                " INSERT INTO tblDctosOrdenesDetalle                    "
                + "            ([IDLOCAL]                               "
                + "            ,[IDTIPOORDEN]                           "
                + "            ,[IDORDEN]                               "
                + "            ,[IDPLU]                                 "
                + "            ,[CANTIDAD]                              "
                + "            ,[IDTIPO]                                "
                + "            ,[PORCENTAJEIVA]                         "
                + "            ,[VRVENTAUNITARIO]                       "
                + "            ,[ESTADO]                                "
                + "            ,[NOMBREPLU]                             "
                + "            ,[EAN]                                   "
                + "            ,[VRVENTAORIGINAL]                       "
                + "            ,[VRCOSTO]                               "
                + "            ,[VRDSCTOPIE]                            "
                + "            ,[PORCENTAJEDSCTO]                       "
                + "            ,[CANTIDADPEDIDA]                        "
                + "            ,[vrCostoNegociado]                      "
                + "            ,[strIdBodega]                           "
                + "            ,[vrCostoResurtido]                      "
                + "            ,[STRIDLISTA]                            "
                + "            ,[STRIDREFERENCIA]                       "
                + "            ,[PESOTEORICO]                           "
                + "            ,[NOMBREUNIDADMEDIDA]                    "
                + "            ,[IDLOCALSUGERIDO]                       "
                + "            ,[IDBODEGASUGERIDO]                      "
                + "            ,[marcaArteCliente]                      "
                + "            ,[referenciaCliente]                     "
                + "            ,[comentario]                            "
                + "            ,[item]                                  "
                + "            ,[itemPadre]                             "
                + "            ,[idEstadoTx]                            "
                + "            ,[idTipoTx]                              "
                + "            ,[idReferenciaOriginal]                  "
                + "            ,[idEstadoRefOriginal]                   "
                + "            ,[idClasificacion]                       "
                + "            ,[idResponsable]                         "
                + "            ,[fechaEntrega]                          "
                + "            ,[idBodega]                              "
                + "            ,[vrImpoconsumo]                         "
                + "            ,[vrCostoIND]                            "
                + "            ,[vrIvaResurtido]                        "
                + "            ,[idSubcuenta]                           "
                + "            ,[cantidadBonificada]                    "
                + "            ,[idOrdenOrigen]                         "
                + "            ,[idLocalOrigen]                         "
                + "            ,[idTipoOrdenOrigen]                     "
                + "            ,[unidadVenta]                           "
                + "            ,[idOperacion]                           "
                + "            ,[numeroOrden]                           "
                + "            ,[itemOrden]                             "
                + "            ,[vrVentaUnitarioSinIva]                 "
                + "            ,[pesoPedido]                            "
                + "            ,[pesoTerminado]                         "
                + "            ,[pesoRetal]                             "
                + "            ,[cantidadTerminada]                     "
                + "            ,[cantidadFacturada]                     "
                + "            ,[pesoFacturado]                         "
                + "            ,[cantidadEntregada]                     "
                + "            ,[pesoEntregado])                        "
                + " SELECT                                              "
                + xIdLocalNew + " AS idLocal,                          "
                + xIdTipoOrdenNew + " AS idTipoOrden,                   "
                + xIdOrdenNew + " AS idOrden                            "
                + "       ,tblDctosOrdenesDetalle.IDPLU                 "
                + "       ,tblDctosOrdenesDetalle.CANTIDAD              "
                + "       ,tblDctosOrdenesDetalle.IDTIPO                "
                + "       ,tblDctosOrdenesDetalle.PORCENTAJEIVA         "
                + "       ,tblDctosOrdenesDetalle.VRVENTAUNITARIO       "
                + "       ,tblDctosOrdenesDetalle.ESTADO                "
                + "       ,tblDctosOrdenesDetalle.NOMBREPLU             "
                + "       ,tblDctosOrdenesDetalle.EAN                   "
                + "       ,tblDctosOrdenesDetalle.VRVENTAORIGINAL       "
                + "       ,tblDctosOrdenesDetalle.VRCOSTO               "
                + "       ,tblDctosOrdenesDetalle.VRDSCTOPIE            "
                + "       ,tblDctosOrdenesDetalle.PORCENTAJEDSCTO       "
                + "       ,tblDctosOrdenesDetalle.CANTIDADPEDIDA        "
                + "       ,tblDctosOrdenesDetalle.vrCostoNegociado      "
                + "       ,tblDctosOrdenesDetalle.strIdBodega           "
                + "       ,tblDctosOrdenesDetalle.vrCostoResurtido      "
                + "       ,tblDctosOrdenesDetalle.STRIDLISTA            "
                + "       ,tblDctosOrdenesDetalle.STRIDREFERENCIA       "
                + "       ,tblDctosOrdenesDetalle.PESOTEORICO           "
                + "       ,tblDctosOrdenesDetalle.NOMBREUNIDADMEDIDA    "
                + "       ,tblDctosOrdenesDetalle.IDLOCALSUGERIDO       "
                + "       ,tblDctosOrdenesDetalle.IDBODEGASUGERIDO      "
                + "       ,tblDctosOrdenesDetalle.marcaArteCliente      "
                + "       ,tblDctosOrdenesDetalle.referenciaCliente     "
                + "       ,tblDctosOrdenesDetalle.comentario            "
                + "       ,tblDctosOrdenesDetalle.item                  "
                + "       ,tblDctosOrdenesDetalle.itemPadre             "
                + "       ,tblDctosOrdenesDetalle.idEstadoTx            "
                + "       ,tblDctosOrdenesDetalle.idTipoTx              "
                + "     ,tblDctosOrdenesDetalle.idReferenciaOriginal    "
                + "      ,tblDctosOrdenesDetalle.idEstadoRefOriginal    "
                + "       ,tblDctosOrdenesDetalle.idClasificacion       "
                + "       ,tblDctosOrdenesDetalle.idResponsable         "
                + "       ,tblDctosOrdenesDetalle.fechaEntrega          "
                + "       ,tblDctosOrdenesDetalle.idBodega              "
                + "       ,tblDctosOrdenesDetalle.vrImpoconsumo         "
                + "       ,tblDctosOrdenesDetalle.vrCostoIND            "
                + "       ,tblDctosOrdenesDetalle.vrIvaResurtido        "
                + "       ,tblDctosOrdenesDetalle.idSubcuenta           "
                + "       ,tblDctosOrdenesDetalle.cantidadBonificada    "
                + "       ,tblDctosOrdenesDetalle.idOrdenOrigen         "
                + "       ,tblDctosOrdenesDetalle.idLocalOrigen         "
                + "       ,tblDctosOrdenesDetalle.idTipoOrdenOrigen     "
                + "       ,tblDctosOrdenesDetalle.unidadVenta           "
                + "       ,tblDctosOrdenesDetalle.idOperacion           "
                + "       ,tblDctosOrdenesDetalle.numeroOrden           "
                + "       ,tblDctosOrdenesDetalle.itemOrden             "
                + "   ,tblDctosOrdenesDetalle.vrVentaUnitarioSinIva     "
                + "       ,tblDctosOrdenesDetalle.pesoPedido            "
                + "       ,tblDctosOrdenesDetalle.pesoTerminado         "
                + "       ,tblDctosOrdenesDetalle.pesoRetal             "
                + "       ,tblDctosOrdenesDetalle.cantidadTerminada     "
                + "       ,0.0 AS cantidadFacturada                     "
                + "       ,0.0 AS pesoFacturado                         "
                + "       ,tblDctosOrdenesDetalle.cantidadEntregada     "
                + "       ,tblDctosOrdenesDetalle.pesoEntregado         "
                + " FROM tblDctosOrdenesDetalle                         "
                + " WHERE tblDctosOrdenesDetalle.idLocal   =            "
                + getIdLocal() + "                                      "
                + " AND tblDctosOrdenesDetalle.idTipoOrden =            "
                + getIdTipoOrden() + "                                  "
                + " AND tblDctosOrdenesDetalle.idOrden     =            "
                + getIdOrden();


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
            return okIngresar;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {

            // conexion
            jdbcAccess.cleanup(connection, selectStatement, null);
            return okIngresar;
        }
    }

    // actualizaParcialCompra
    public boolean actualizaParcialCompra(int xIdLocalNew,
            int xIdTipoOrdenNew,
            int xIdOrdenNew) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                " UPDATE tblDctosOrdenesDetalle                   "
                + " SET tblDctosOrdenesDetalle.cantidadFacturada  "
                + "                        = ( tblDET.cantidad +  "
                + "   tblDctosOrdenesDetalle.cantidadFacturada )  "
                + " FROM   tblDctosOrdenesDetalle                 "
                + " INNER JOIN tblDctosOrdenesDetalle             "
                + "                 AS tblDET                     "
                + " ON tblDctosOrdenesDetalle.IDLOCAL      =      "
                + "                     tblDET.IDLOCALORIGEN      "
                + " AND tblDctosOrdenesDetalle.IDTIPOORDEN =      "
                + "                 tblDET.IDTIPOORDENORIGEN      "
                + " AND  tblDctosOrdenesDetalle.IDORDEN    =      "
                + "                     tblDET.IDORDENORIGEN      "
                + " AND tblDctosOrdenesDetalle.IDPLU       =      "
                + "                             tblDET.IDPLU      "
                + " WHERE tblDET.IDLOCAL   =                      "
                + +xIdLocalNew + "                              "
                + " AND tblDET.IDTIPOORDEN =                      "
                + xIdTipoOrdenNew + "                            "
                + " AND tblDET.IDORDEN     =                      "
                + xIdOrdenNew;

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
            return okIngresar;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {

            // conexion
            jdbcAccess.cleanup(connection, selectStatement, null);
            return okIngresar;
        }
    }

    // actualizaBodega
    public boolean actualizaBodega(int xIdSigno) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                " UPDATE tblDctosOrdenesDetalle                "
                + " SET tblDctosOrdenesDetalle.idBodega    =   "
                + getIdBodega() + ",                           "
                + "     tblDctosOrdenesDetalle.idOperacion =   "
                + getIdBodega() + "                            "
                + " WHERE tblDctosOrdenesDetalle.IDLOCAL   =   "
                + getIdLocal() + "                             "
                + " AND tblDctosOrdenesDetalle.IDTIPOORDEN =   "
                + getIdTipoOrden() + "                         "
                + " AND tblDctosOrdenesDetalle.IDORDEN     =   "
                + getIdOrden() + "                             "
                + " AND tblDctosOrdenesDetalle.idBodega    =   "
                + xIdSigno;

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
            return okIngresar;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {

            // conexion
            jdbcAccess.cleanup(connection, selectStatement, null);
            return okIngresar;
        }
    }

    // actualizaBodegaRetiro
    public boolean actualizaBodegaRetiro() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                " UPDATE tblDctosOrdenesDetalle                "
                + " SET tblDctosOrdenesDetalle.cantidad    =   "
                + " ( tblDctosOrdenesDetalle.cantidad * (-1) ) "
                + " , tblDctosOrdenesDetalle.cantidadPedida=   "
                + " ( tblDctosOrdenesDetalle.cantidadPedida * (-1) ) "
                + " WHERE tblDctosOrdenesDetalle.IDLOCAL   =   "
                + getIdLocal() + "                             "
                + " AND tblDctosOrdenesDetalle.IDTIPOORDEN =   "
                + getIdTipoOrden() + "                         "
                + " AND tblDctosOrdenesDetalle.IDORDEN     =   "
                + getIdOrden() + "                             ";

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
            return okIngresar;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {

            // conexion
            jdbcAccess.cleanup(connection, selectStatement, null);
            return okIngresar;
        }
    }

    // actualiza
    public boolean actualizaIvaResurtido() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                " UPDATE tblDctosOrdenesDetalle                     "
                + " SET  tblDctosOrdenesDetalle.porcentajeIva   = 0 "
                + " WHERE tblDctosOrdenesDetalle.IDLOCAL        =   "
                + getIdLocal() + "                                  "
                + " AND   tblDctosOrdenesDetalle.IDTIPOORDEN    =   "
                + getIdTipoOrden() + "                              "
                + " AND   tblDctosOrdenesDetalle.IDORDEN        =   "
                + getIdOrden() + "                                  "
                + " AND   tblDctosOrdenesDetalle.vrIvaResurtido = 0 ";

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
            return okIngresar;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {

            // conexion
            jdbcAccess.cleanup(connection, selectStatement, null);
            return okIngresar;
        }
    }

    // actualizaOrdenOrigen
    public boolean actualizaOrdenOrigen() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                " UPDATE tblDctosOrdenesDetalle                     "
                + " SET  tblDctosOrdenesDetalle.idLocalOrigen   =   "
                + getIdLocalOrigen() + ",                           "
                + "   tblDctosOrdenesDetalle.idTipoOrdenOrigen  =   "
                + getIdTipoOrdenOrigen() + ",                       "
                + "   tblDctosOrdenesDetalle.idOrdenOrigen      =   "
                + getIdOrdenOrigen() + "                            "
                + " WHERE tblDctosOrdenesDetalle.IDLOCAL        =   "
                + getIdLocal() + "                                  "
                + " AND   tblDctosOrdenesDetalle.IDTIPOORDEN    =   "
                + getIdTipoOrden() + "                              "
                + " AND   tblDctosOrdenesDetalle.IDORDEN        =   "
                + getIdOrden();

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
            return okIngresar;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {

            // conexion
            jdbcAccess.cleanup(connection, selectStatement, null);
            return okIngresar;
        }
    }

    // actualizaOrdenOrigenAjuste
    public boolean actualizaOrdenOrigenAjuste() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                " UPDATE tblDctosOrdenesDetalle                     "
                + " SET  tblDctosOrdenesDetalle.idLocalOrigen   =   "
                + getIdLocalOrigen() + ",                           "
                + "   tblDctosOrdenesDetalle.idTipoOrdenOrigen  =   "
                + getIdTipoOrdenOrigen() + ",                       "
                + "   tblDctosOrdenesDetalle.idOrdenOrigen      =   "
                + getIdOrdenOrigen() + "                            "
                + " FROM   tblDctosOrdenes                          "
                + " INNER JOIN tblDctosOrdenesDetalle               "
                + " ON  tblDctosOrdenes.IDLOCAL       =             "
                + "                 tblDctosOrdenesDetalle.IDLOCAL  "
                + " AND tblDctosOrdenes.IDTIPOORDEN   =             "
                + "             tblDctosOrdenesDetalle.IDTIPOORDEN  "
                + " AND tblDctosOrdenes.IDORDEN       =             "
                + "                 tblDctosOrdenesDetalle.IDORDEN  "
                + " WHERE tblDctosOrdenes.IDLOCAL     =             "
                + getIdLocal() + "                                  "
                + " AND tblDctosOrdenes.IDTIPOORDEN   =             "
                + getIdTipoOrden() + "                              "
                + " AND tblDctosOrdenes.IDLOG         =             "
                + getIdLog() + "                                    "
                + " AND tblDctosOrdenesDetalle.item   =             "
                + getItem();

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
            return okIngresar;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {

            // conexion
            jdbcAccess.cleanup(connection, selectStatement, null);
            return okIngresar;
        }
    }

    // actualizaOrdenOrigenProducto
    public boolean actualizaOrdenOrigenProducto() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                " UPDATE tblDctosOrdenesDetalle                     "
                + " SET  tblDctosOrdenesDetalle.idPlu           =   "
                + getIdPlu() + ",                                   "
                + "   tblDctosOrdenesDetalle.strIdReferencia    =   "
                + getIdPluStr() + "                                 "
                + " WHERE tblDctosOrdenesDetalle.IDLOCAL        =   "
                + getIdLocal() + "                                  "
                + " AND   tblDctosOrdenesDetalle.IDTIPOORDEN    =   "
                + getIdTipoOrden() + "                              "
                + " AND   tblDctosOrdenesDetalle.IDORDEN        =   "
                + getIdOrden() + "                                  "
                + " AND   tblDctosOrdenesDetalle.idBodega       =   "
                + getIdBodega();


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
            return okIngresar;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {

            // conexion
            jdbcAccess.cleanup(connection, selectStatement, null);
            return okIngresar;
        }
    }

    // retiraCantidadPedidaCero
    public boolean retiraCantidadPedidaCero() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                " DELETE FROM tblDctosOrdenesDetalle                "
                + " WHERE tblDctosOrdenesDetalle.idLocal        =   "
                + getIdLocal() + "                                  "
                + " AND   tblDctosOrdenesDetalle.idTipoOrden    =   "
                + getIdTipoOrden() + "                              "
                + " AND   tblDctosOrdenesDetalle.idOrden        =   "
                + getIdOrden() + "                                  "
                + " AND   tblDctosOrdenesDetalle.cantidadPedida = 0 ";

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
            return okIngresar;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {

            // conexion
            jdbcAccess.cleanup(connection, selectStatement, null);
            return okIngresar;
        }
    }

    // retiraUltimaOperacion
    public boolean retiraUltimaOperacion() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                " DELETE FROM tblDctosOrdenesDetalle             "
                + " WHERE tblDctosOrdenesDetalle.idLocal     =   "
                + getIdLocal() + "                               "
                + " AND   tblDctosOrdenesDetalle.idTipoOrden =   "
                + getIdTipoOrden() + "                           "
                + " AND   tblDctosOrdenesDetalle.idOrden     =   "
                + getIdOrden() + "                               "
                + " AND   tblDctosOrdenesDetalle.idOperacion =   "
                + getIdOperacion();

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
            return okIngresar;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {

            // conexion
            jdbcAccess.cleanup(connection, selectStatement, null);
            return okIngresar;
        }
    }

    // cambiaCantidadTerminadaSellado
    public boolean cambiaCantidadTerminadaSellado() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                "UPDATE tblDctosOrdenesDetalle                      "
                + "SET tblDctosOrdenesDetalle.cantidadTerminada = 0 "
                + "FROM   tblDctosOrdenesDetalle                    "
                + "WHERE tblDctosOrdenesDetalle.IDLOCAL         =   "
                + getIdLocal() + "                                  "
                + "AND   tblDctosOrdenesDetalle.IDTIPOORDEN     =   "
                + getIdTipoOrden() + "                              "
                + "AND   tblDctosOrdenesDetalle.idOrden         =   "
                + getIdOrden() + "                                  "
                + "AND   tblDctosOrdenesDetalle.idBodega        =   "
                + getIdBodega();

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
            return okIngresar;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {

            // conexion
            jdbcAccess.cleanup(connection, selectStatement, null);
            return okIngresar;
        }
    }

    // actualizaResurtido
    public boolean actualizaResurtido() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                "UPDATE tblDctosOrdenesDetalle                      "
                + "SET    tblDctosOrdenesDetalle.cantidad       =   "
                + getCantidadPedida() + ",                          "
                + "     tblDctosOrdenesDetalle.VrCostoResurtido =   "
                + getVrCostoResurtido() + ",                        "
                + "     tblDctosOrdenesDetalle.VrCosto          =   "
                + " (CASE WHEN                                      "
                + getCantidadPedida() + "  = 0                      "
                + " THEN                   0                        "
                + " ELSE                                            "
                + getVrCostoResurtido() + " /                       "
                + getCantidadPedida() + "    END),                  "
                + "   tblDctosOrdenesDetalle.VrCostoNegociado =     "
                + " (CASE WHEN                                      "
                + getCantidadPedida() + "  = 0                      "
                + " THEN                   0                        "
                + " ELSE                                            "
                + getVrCostoResurtido() + " /                       "
                + getCantidadPedida() + "   END  )                  "
                + "FROM   tblDctosOrdenes                           "
                + "INNER JOIN tblDctosOrdenesDetalle                "
                + "ON  tblDctosOrdenes.IDLOCAL       =              "
                + "                tblDctosOrdenesDetalle.IDLOCAL   "
                + "AND tblDctosOrdenes.IDTIPOORDEN   =              "
                + "            tblDctosOrdenesDetalle.IDTIPOORDEN   "
                + "AND tblDctosOrdenes.IDORDEN       =              "
                + "                tblDctosOrdenesDetalle.IDORDEN   "
                + "WHERE tblDctosOrdenes.IDLOCAL     =              "
                + getIdLocal() + "                                  "
                + "AND   tblDctosOrdenes.IDTIPOORDEN =              "
                + getIdTipoOrden() + "                              "
                + "AND   tblDctosOrdenes.IDLOG       =              "
                + getIdLog() + "                                    "
                + "AND   tblDctosOrdenesDetalle.idPlu=              "
                + getIdPlu();


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
            return okIngresar;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {

            // conexion
            jdbcAccess.cleanup(connection, selectStatement, null);
            return okIngresar;
        }
    }

    // actualizaResurtido
    public boolean actualizaResurtidoDespacho() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                "UPDATE tblDctosOrdenesDetalle                      "
                + "SET    tblDctosOrdenesDetalle.cantidad       =   "
                + getCantidadPedida() + ",                          "
                + "     tblDctosOrdenesDetalle.VrCostoResurtido =  ("
                + getVrCosto() + " *                                "
                + getCantidadPedida() + "),                         "
                + "     tblDctosOrdenesDetalle.VrCosto          =   "
                + getVrCosto() + ",                                 "
                + "   tblDctosOrdenesDetalle.VrCostoNegociado =     "
                + getVrCosto() + ",                                 "
                + "   tblDctosOrdenesDetalle.VrCostoIND =           "
                + getVrCostoIND() + "                               "
                + "FROM   tblDctosOrdenes                           "
                + "INNER JOIN tblDctosOrdenesDetalle                "
                + "ON  tblDctosOrdenes.IDLOCAL       =              "
                + "                tblDctosOrdenesDetalle.IDLOCAL   "
                + "AND tblDctosOrdenes.IDTIPOORDEN   =              "
                + "            tblDctosOrdenesDetalle.IDTIPOORDEN   "
                + "AND tblDctosOrdenes.IDORDEN       =              "
                + "                tblDctosOrdenesDetalle.IDORDEN   "
                + "WHERE tblDctosOrdenes.IDLOCAL     =              "
                + getIdLocal() + "                                  "
                + "AND   tblDctosOrdenes.IDTIPOORDEN =              "
                + getIdTipoOrden() + "                              "
                + "AND   tblDctosOrdenes.IDLOG       =              "
                + getIdLog() + "                                    "
                + "AND   tblDctosOrdenesDetalle.idOrdenOrigen  =    "
                + getIdOrdenOrigen() + "                            "
                + "AND   tblDctosOrdenesDetalle.idPlu=              "
                + getIdPlu();


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
            return okIngresar;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {

            // conexion
            jdbcAccess.cleanup(connection, selectStatement, null);
            return okIngresar;
        }
    }

    // guardaResurtido
    public boolean guardaResurtido() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                "UPDATE tblDctosOrdenesDetalle                      "
                + "SET    tblDctosOrdenesDetalle.cantidad       =  ("
                + getCantidadPedida() + " +                         "
                + getCantidadBonificada() + "),                     "
                + "     tblDctosOrdenesDetalle.cantidadPedida   =   "
                + getCantidadPedida() + ",                          "
                + "   tblDctosOrdenesDetalle.cantidadBonificada =   "
                + getCantidadBonificada() + ",                      "
                + "     tblDctosOrdenesDetalle.vrCostoNegociado =   "
                + getVrCostoNegociado() + ",                        "
                + "     tblDctosOrdenesDetalle.vrVentaUnitario  =   "
                + getVrCostoNegociado() + ",                        "
                + "     tblDctosOrdenesDetalle.vrCosto          =   "
                + getVrCostoNegociado() + ",                        "
                + "     tblDctosOrdenesDetalle.vrCostoResurtido =  ("
                + getVrCostoNegociado() + " *                       "
                + getCantidadPedida() + " ),                        "
                + "     tblDctosOrdenesDetalle.vrIvaResurtido =     "
                + getVrIvaResurtido() + "                           "
                + "FROM   tblDctosOrdenes                           "
                + "INNER JOIN tblDctosOrdenesDetalle                "
                + "ON  tblDctosOrdenes.IDLOCAL       =              "
                + "                tblDctosOrdenesDetalle.IDLOCAL   "
                + "AND tblDctosOrdenes.IDTIPOORDEN   =              "
                + "            tblDctosOrdenesDetalle.IDTIPOORDEN   "
                + "AND tblDctosOrdenes.IDORDEN       =              "
                + "                tblDctosOrdenesDetalle.IDORDEN   "
                + "WHERE tblDctosOrdenes.IDLOCAL     =              "
                + getIdLocal() + "                                  "
                + "AND   tblDctosOrdenes.IDTIPOORDEN =              "
                + getIdTipoOrden() + "                              "
                + "AND   tblDctosOrdenes.IDLOG       =              "
                + getIdLog() + "                                    "
                + "AND   tblDctosOrdenesDetalle.idPlu=              "
                + getIdPlu();

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
            return okIngresar;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {

            // Cierra el Resultset
            jdbcAccess.cleanup(connection, selectStatement, null);
            return okIngresar;
        }
    }

    // borraResurtidoCompra
    public boolean borraResurtidoCompra() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                "UPDATE tblDctosOrdenesDetalle                    "
                + "SET  tblDctosOrdenesDetalle.cantidad         = 0,"
                + "     tblDctosOrdenesDetalle.VrCostoResurtido = 0,"
                + "     tblDctosOrdenesDetalle.VrCosto          = 0 "
                + "FROM   tblDctosOrdenes                           "
                + "INNER JOIN tblDctosOrdenesDetalle                "
                + "ON  tblDctosOrdenes.IDLOCAL       =              "
                + "                tblDctosOrdenesDetalle.IDLOCAL   "
                + "AND tblDctosOrdenes.IDTIPOORDEN   =              "
                + "            tblDctosOrdenesDetalle.IDTIPOORDEN   "
                + "AND tblDctosOrdenes.IDORDEN       =              "
                + "                tblDctosOrdenesDetalle.IDORDEN   "
                + "WHERE tblDctosOrdenes.IDLOCAL     =              "
                + getIdLocal() + "                                  "
                + "AND   tblDctosOrdenes.IDTIPOORDEN =              "
                + getIdTipoOrden() + "                              "
                + "AND   tblDctosOrdenes.IDLOG       =              "
                + getIdLog();

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
            return okIngresar;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {
            // Cierra la conexion a la base de datos limpiando la statement usada y la
            // conexion
            return okIngresar;
        }
    }

    // borraCompraRecibo
    public boolean borraCompraRecibo() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                "UPDATE tblDctosOrdenesDetalle                      "
                + "SET  tblDctosOrdenesDetalle.cantidad         = 0 "
                + "FROM   tblDctosOrdenes                           "
                + "INNER JOIN tblDctosOrdenesDetalle                "
                + "ON  tblDctosOrdenes.IDLOCAL       =              "
                + "                tblDctosOrdenesDetalle.IDLOCAL   "
                + "AND tblDctosOrdenes.IDTIPOORDEN   =              "
                + "            tblDctosOrdenesDetalle.IDTIPOORDEN   "
                + "AND tblDctosOrdenes.IDORDEN       =              "
                + "                tblDctosOrdenesDetalle.IDORDEN   "
                + "WHERE tblDctosOrdenes.IDLOCAL     =              "
                + getIdLocal() + "                                  "
                + "AND   tblDctosOrdenes.IDTIPOORDEN =              "
                + getIdTipoOrden() + "                              "
                + "AND   tblDctosOrdenes.IDLOG       =              "
                + getIdLog();

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
            return okIngresar;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {
            // Cierra la conexion a la base de datos limpiando la statement usada y la
            // conexion
            return okIngresar;
        }
    }

    // actualizaPendiente
    public boolean actualizaPendiente() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                "UPDATE tblDctosOrdenesDetalle                      "
                + "SET  tblDctosOrdenesDetalle.cantidad         =   "
                + "   ( tblDctosOrdenesDetalle.cantidadPedida   -   "
                + "     tblDctosOrdenesDetalle.cantidadFacturada ), "
                + "     tblDctosOrdenesDetalle.vrCostoResurtido =   "
                + "   ( tblDctosOrdenesDetalle.cantidadPedida   -   "
                + "  tblDctosOrdenesDetalle.cantidadFacturada ) *   "
                + "    tblDctosOrdenesDetalle.vrCostoNegociado,     "
                + "     tblDctosOrdenesDetalle.vrIvaResurtido   =   "
                + "   ( tblDctosOrdenesDetalle.cantidadPedida   -   "
                + "  tblDctosOrdenesDetalle.cantidadFacturada ) *   "
                + "    tblDctosOrdenesDetalle.vrCostoNegociado  *   "
                + "    tblDctosOrdenesDetalle.porcentajeIva /100    "
                + "FROM   tblDctosOrdenes                           "
                + "INNER JOIN tblDctosOrdenesDetalle                "
                + "ON  tblDctosOrdenes.IDLOCAL       =              "
                + "                tblDctosOrdenesDetalle.IDLOCAL   "
                + "AND tblDctosOrdenes.IDTIPOORDEN   =              "
                + "            tblDctosOrdenesDetalle.IDTIPOORDEN   "
                + "AND tblDctosOrdenes.IDORDEN       =              "
                + "                tblDctosOrdenesDetalle.IDORDEN   "
                + "WHERE tblDctosOrdenes.IDLOCAL     =              "
                + getIdLocal() + "                                  "
                + "AND   tblDctosOrdenes.IDTIPOORDEN =              "
                + getIdTipoOrden() + "                              "
                + "AND   tblDctosOrdenes.IDLOG       =              "
                + getIdLog();

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
            return okIngresar;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {
            // Cierra la conexion a la base de datos limpiando la statement usada y la
            // conexion
            return okIngresar;
        }
    }

    // actualizaResurtidoCompra
    public boolean actualizaResurtidoCompra() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                "UPDATE tblDctosOrdenesDetalle                    "
                + "SET    tblDctosOrdenesDetalle.cantidad       =   "
                + getCantidad() + ",                                "
                + "     tblDctosOrdenesDetalle.vrCostoResurtido =   "
                + getVrCostoResurtido() + ",                        "
                + "     tblDctosOrdenesDetalle.vrIvaResurtido   =   "
                + getVrIvaResurtido() + ",                          "
                + "     tblDctosOrdenesDetalle.vrImpoconsumo    =   "
                + getVrImpoconsumo() + ",                           "
                + "     tblDctosOrdenesDetalle.vrCostoIND       =  ("
                + getVrCostoResurtido() + " - " + getVrIvaResurtido()
                + ") / " + getCantidad() + ",                       "
                + "     tblDctosOrdenesDetalle.vrCosto          =  ("
                + getVrCostoResurtido() + " - " + getVrIvaResurtido()
                + ") / " + getCantidad() + ",                       "
                + "     tblDctosOrdenesDetalle.vrVentaUnitario  =  ("
                + getVrCostoResurtido() + " - " + getVrIvaResurtido()
                + ") / " + getCantidad() + ",                       "
                + "     tblDctosOrdenesDetalle.vrVentaOriginal  =   "
                + getVrCostoResurtido() / getCantidad() + ",        "
                + "     tblDctosOrdenesDetalle.porcentajeIva    =   "
                + getPorcentajeIva() + "                            "
                + "FROM   tblDctosOrdenes                           "
                + "INNER JOIN tblDctosOrdenesDetalle                "
                + "ON  tblDctosOrdenes.IDLOCAL       =              "
                + "                tblDctosOrdenesDetalle.IDLOCAL   "
                + "AND tblDctosOrdenes.IDTIPOORDEN   =              "
                + "            tblDctosOrdenesDetalle.IDTIPOORDEN   "
                + "AND tblDctosOrdenes.IDORDEN       =              "
                + "                tblDctosOrdenesDetalle.IDORDEN   "
                + "WHERE tblDctosOrdenes.IDLOCAL     =              "
                + getIdLocal() + "                                  "
                + "AND   tblDctosOrdenes.IDTIPOORDEN =              "
                + getIdTipoOrden() + "                              "
                + "AND   tblDctosOrdenes.IDLOG       =              "
                + getIdLog() + "                                    "
                + "AND   tblDctosOrdenesDetalle.idPlu=              "
                + getIdPlu();

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
            return okIngresar;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {
            // Cierra la conexion a la base de datos limpiando la statement usada y la
            // conexion
            return okIngresar;
        }
    }

    // actualizaSuministro
    public boolean actualizaSuministro() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                "UPDATE tblDctosOrdenesDetalle                    "
                + "SET    tblDctosOrdenesDetalle.cantidad       =   "
                + getCantidad() + ",                                "
                + "     tblDctosOrdenesDetalle.vrCostoResurtido =   "
                + getVrCostoResurtido() + ",                        "
                + "     tblDctosOrdenesDetalle.vrIvaResurtido   =   "
                + getVrIvaResurtido() + ",                          "
                + "     tblDctosOrdenesDetalle.vrImpoconsumo    =   "
                + getVrImpoconsumo() + ",                           "
                + "     tblDctosOrdenesDetalle.vrCostoIND       =  ("
                + getVrCostoResurtido() + " - " + getVrIvaResurtido()
                + ") / " + getCantidad() + ",                       "
                + "     tblDctosOrdenesDetalle.vrCosto          =  ("
                + getVrCostoResurtido() + " - " + getVrIvaResurtido()
                + ") / " + getCantidad() + ",                       "
                + "     tblDctosOrdenesDetalle.vrVentaUnitario  =   "
                + getVrCostoResurtido() / getCantidad() + ",        "
                + "     tblDctosOrdenesDetalle.vrVentaOriginal  =   "
                + getVrCostoResurtido() / getCantidad() + ",        "
                + "     tblDctosOrdenesDetalle.vrCostoNegociado =  ("
                + getVrCostoResurtido() + " - " + getVrIvaResurtido()
                + ") / " + getCantidad() + ",                       "
                + "     tblDctosOrdenesDetalle.numeroOrden      =   "
                + getNumeroOrden() + ",                             "
                + "     tblDctosOrdenesDetalle.itemOrden        =   "
                + getItemOrden() + ",                               "
                + "     tblDctosOrdenesDetalle.idOperacion      =   "
                + getIdOperacion() + ",                             "
                + "     tblDctosOrdenesDetalle.idLocalOrigen    =   "
                + getIdLocalOrigen() + ",                           "
                + "    tblDctosOrdenesDetalle.idTipoOrdenOrigen =   "
                + getIdTipoOrdenOrigen() + ",                       "
                + "     tblDctosOrdenesDetalle.idOrdenOrigen    =   "
                + getIdOrdenOrigen() + "                            "
                + "FROM   tblDctosOrdenes                           "
                + "INNER JOIN tblDctosOrdenesDetalle                "
                + "ON  tblDctosOrdenes.IDLOCAL       =              "
                + "                tblDctosOrdenesDetalle.IDLOCAL   "
                + "AND tblDctosOrdenes.IDTIPOORDEN   =              "
                + "            tblDctosOrdenesDetalle.IDTIPOORDEN   "
                + "AND tblDctosOrdenes.IDORDEN       =              "
                + "                tblDctosOrdenesDetalle.IDORDEN   "
                + "WHERE tblDctosOrdenes.IDLOCAL     =              "
                + getIdLocal() + "                                  "
                + "AND   tblDctosOrdenes.IDTIPOORDEN =              "
                + getIdTipoOrden() + "                              "
                + "AND   tblDctosOrdenes.IDLOG       =              "
                + getIdLog() + "                                    "
                + "AND   tblDctosOrdenesDetalle.idPlu=              "
                + getIdPlu();

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
            return okIngresar;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {
            // Cierra la conexion a la base de datos limpiando la statement usada y la
            // conexion
            return okIngresar;
        }
    }

    // actualizaReciboCompra
    public boolean actualizaReciboCompra() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                "UPDATE tblDctosOrdenesDetalle                      "
                + "SET    tblDctosOrdenesDetalle.cantidad       =   "
                + getCantidad() + "                                 "
                + "FROM   tblDctosOrdenes                           "
                + "INNER JOIN tblDctosOrdenesDetalle                "
                + "ON  tblDctosOrdenes.IDLOCAL       =              "
                + "                tblDctosOrdenesDetalle.IDLOCAL   "
                + "AND tblDctosOrdenes.IDTIPOORDEN   =              "
                + "            tblDctosOrdenesDetalle.IDTIPOORDEN   "
                + "AND tblDctosOrdenes.IDORDEN       =              "
                + "                tblDctosOrdenesDetalle.IDORDEN   "
                + "WHERE tblDctosOrdenes.IDLOCAL     =              "
                + getIdLocal() + "                                  "
                + "AND   tblDctosOrdenes.IDTIPOORDEN =              "
                + getIdTipoOrden() + "                              "
                + "AND   tblDctosOrdenes.IDLOG       =              "
                + getIdLog() + "                                    "
                + "AND   tblDctosOrdenesDetalle.idPlu=              "
                + getIdPlu();

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
            return okIngresar;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {
            // Cierra la conexion a la base de datos limpiando la statement usada y la
            // conexion
            return okIngresar;
        }
    }

    // actualizaConteo
    public boolean actualizaConteo() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                "UPDATE tblDctosOrdenesDetalle                    "
                + "SET    tblDctosOrdenesDetalle.cantidad       =   "
                + getCantidad() + "                                 "
                + "FROM   tblDctosOrdenes                           "
                + "INNER JOIN tblDctosOrdenesDetalle                "
                + "ON  tblDctosOrdenes.IDLOCAL       =              "
                + "                tblDctosOrdenesDetalle.IDLOCAL   "
                + "AND tblDctosOrdenes.IDTIPOORDEN   =              "
                + "            tblDctosOrdenesDetalle.IDTIPOORDEN   "
                + "AND tblDctosOrdenes.IDORDEN       =              "
                + "                tblDctosOrdenesDetalle.IDORDEN   "
                + "WHERE tblDctosOrdenes.IDLOCAL     =              "
                + getIdLocal() + "                                  "
                + "AND   tblDctosOrdenes.IDTIPOORDEN =              "
                + getIdTipoOrden() + "                              "
                + "AND   tblDctosOrdenes.idOrden     =              "
                + getIdOrden() + "                                  "
                + "AND   tblDctosOrdenesDetalle.idPlu=              "
                + getIdPlu();

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
            return okIngresar;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {
            // Cierra la conexion a la base de datos limpiando la statement usada y la
            // conexion
            return okIngresar;
        }
    }

    // actualizaEstadoInventario
    public boolean actualizaEstadoInventario(int xEstadoInventarioNew) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                "UPDATE tblDctosOrdenesDetalle               "
                + "SET tblDctosOrdenesDetalle.estado         = "
                + xEstadoInventarioNew + "                     "
                + "WHERE tblDctosOrdenesDetalle.IDLOCAL      = "
                + getIdLocal() + "                             "
                + "AND   tblDctosOrdenesDetalle.IDTIPOORDEN  = "
                + getIdTipoOrden() + "                         "
                + "AND   tblDctosOrdenesDetalle.IDORDEN      = "
                + getIdOrden() + "                             "
                + "AND   tblDctosOrdenesDetalle.estado       = "
                + getEstado();

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
            return okIngresar;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {
            // Cierra la conexion a la base de datos limpiando la statement usada y la
            // conexion
            return okIngresar;
        }
    }

    // actualizaAjuste
    public boolean actualizaAjuste(int xEstadoInventarioNew) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                "UPDATE tblDctosOrdenesDetalle               "
                + "SET tblDctosOrdenesDetalle.estado         = "
                + xEstadoInventarioNew + ",                    "
                + "    tblDctosOrdenesDetalle.cantidad       = "
                + "   (tblDctosOrdenesDetalle.cantidad       - "
                + "    tblDctosOrdenesDetalle.cantidadPedida)  "
                + "WHERE tblDctosOrdenesDetalle.IDLOCAL      = "
                + getIdLocal() + "                             "
                + "AND   tblDctosOrdenesDetalle.IDTIPOORDEN  = "
                + getIdTipoOrden() + "                         "
                + "AND   tblDctosOrdenesDetalle.IDORDEN      = "
                + getIdOrden() + "                             "
                + "AND   tblDctosOrdenesDetalle.estado       = "
                + getEstado();

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
            return okIngresar;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {

            // conexion
            jdbcAccess.cleanup(connection, selectStatement, null);
            return okIngresar;
        }
    }

    // actualizaNota
    public boolean actualizaNota(int xIdTipoOrdenNew,
            int xIdOrdenNew,
            int xSignoOperacion) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Vector contenedor = new Vector();
        Connection connection = null;

        String selectStr =
                "UPDATE tblDctosOrdenesDetalle               "
                + "SET tblDctosOrdenesDetalle.IDTIPOORDEN    = "
                + xIdTipoOrdenNew + "                         ,"
                + "    tblDctosOrdenesDetalle.IDORDEN        = "
                + xIdOrdenNew + "                             ,"
                + "    tblDctosOrdenesDetalle.cantidad       = "
                + "(tblDctosOrdenesDetalle.cantidad *          "
                + xSignoOperacion + "),                        "
                + "    tblDctosOrdenesDetalle.pesoFacturado  = "
                + "(tblDctosOrdenesDetalle.pesoFacturado *     "
                + xSignoOperacion + ")                         "
                + "WHERE tblDctosOrdenesDetalle.IDLOCAL      = "
                + getIdLocal() + "                             "
                + "AND   tblDctosOrdenesDetalle.IDTIPOORDEN  = "
                + getIdTipoOrden() + "                         "
                + "AND   tblDctosOrdenesDetalle.IDORDEN      = "
                + getIdOrden() + "                             ";

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
            return okIngresar;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {
            // Cierra la conexion a la base de datos limpiando la statement usada y la
            // conexion
            return okIngresar;
        }
    }

    // actualizaFacturado
    public boolean actualizaFacturado() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                "UPDATE tblDctosOrdenesDetalle                      "
                + "SET tblDctosOrdenesDetalle.cantidadFacturada  =  "
                + "   (tblDctosOrdenesDetalle.cantidadFacturada  +  "
                + getCantidadFacturada() + "),                      "
                + "    tblDctosOrdenesDetalle.pesoFacturado      =  "
                + "   (tblDctosOrdenesDetalle.pesoFacturado  +      "
                + getPesoFacturado() + "),                          "
                + "  tblDctosOrdenesDetalle.comentario           = '"
                + getComentario() + "',                              "
                + "  tblDctosOrdenesDetalle.idTipoEmbalaje       = '"
                +  getUnidadDian() + "'                              "
                + "FROM   tblDctosOrdenes                           "
                + "INNER JOIN tblDctosOrdenesDetalle                "
                + "ON  tblDctosOrdenes.IDLOCAL       =              "
                + "                tblDctosOrdenesDetalle.IDLOCAL   "
                + "AND tblDctosOrdenes.IDTIPOORDEN   =              "
                + "            tblDctosOrdenesDetalle.IDTIPOORDEN   "
                + "AND tblDctosOrdenes.IDORDEN       =              "
                + "                tblDctosOrdenesDetalle.IDORDEN   "
                + "WHERE tblDctosOrdenes.IDLOCAL     =              "
                + getIdLocal() + "                                  "
                + "AND   tblDctosOrdenes.IDTIPOORDEN =              "
                + getIdTipoOrden() + "                              "
                + "AND   tblDctosOrdenes.idOrden =              "
                + getIdOrden() + "                                  "
                + "AND   tblDctosOrdenesDetalle.item =              "
                + getItem();


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
            return okIngresar;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {
            // Cierra el Resultset
            jdbcAccess.cleanup(connection, selectStatement, null);
            return okIngresar;
        }
    }

    // actualizaRemision
    public boolean actualizaRemision() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                "UPDATE tblDctosOrdenesDetalle                      "
                + "SET tblDctosOrdenesDetalle.cantidadEntregada   = "
                + " (tblDctosOrdenesDetalle.cantidadEntregada    +  "
                + getCantidadEntregada() + "),                      "
                + "    tblDctosOrdenesDetalle.pesoEntregado      =  "
                + "   (tblDctosOrdenesDetalle.pesoEntregado  +      "
                + getPesoEntregado() + ")                           "
                + "FROM   tblDctosOrdenes                           "
                + "INNER JOIN tblDctosOrdenesDetalle                "
                + "ON  tblDctosOrdenes.IDLOCAL       =              "
                + "                tblDctosOrdenesDetalle.IDLOCAL   "
                + "AND tblDctosOrdenes.IDTIPOORDEN   =              "
                + "            tblDctosOrdenesDetalle.IDTIPOORDEN   "
                + "AND tblDctosOrdenes.IDORDEN       =              "
                + "                tblDctosOrdenesDetalle.IDORDEN   "
                + "WHERE tblDctosOrdenes.IDLOCAL     =              "
                + getIdLocal() + "                                  "
                + "AND   tblDctosOrdenes.IDTIPOORDEN =              "
                + getIdTipoOrden() + "                              "
                + "AND   tblDctosOrdenes.idOrden =              "
                + getIdOrden() + "                                  "
                + "AND   tblDctosOrdenesDetalle.item =              "
                + getItem();

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
            return okIngresar;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {
            // Cierra el Resultset
            jdbcAccess.cleanup(connection, selectStatement, null);
            return okIngresar;
        }
    }

    // actualizaDescuentaRemision
    public boolean actualizaDescuentaRemision() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                "UPDATE tblDctosOrdenesDetalle                      "
                + "SET tblDctosOrdenesDetalle.cantidadEntregada  =  "
                + " (tblDctosOrdenesDetalle.cantidadEntregada    -  "
                + getCantidadFacturada() + "),                      "
                + "    tblDctosOrdenesDetalle.pesoEntregado      =  "
                + "   (tblDctosOrdenesDetalle.pesoEntregado      -  "
                + getPesoFacturado() + ")                           "
                + "FROM   tblDctosOrdenes                           "
                + "INNER JOIN tblDctosOrdenesDetalle                "
                + "ON  tblDctosOrdenes.IDLOCAL       =              "
                + "                tblDctosOrdenesDetalle.IDLOCAL   "
                + "AND tblDctosOrdenes.IDTIPOORDEN   =              "
                + "            tblDctosOrdenesDetalle.IDTIPOORDEN   "
                + "AND tblDctosOrdenes.IDORDEN       =              "
                + "                tblDctosOrdenesDetalle.IDORDEN   "
                + "WHERE tblDctosOrdenes.IDLOCAL     =              "
                + getIdLocal() + "                                  "
                + "AND   tblDctosOrdenes.IDTIPOORDEN =              "
                + getIdTipoOrden() + "                              "
                + "AND   tblDctosOrdenes.idOrden =                  "
                + getIdOrden() + "                                  "
                + "AND   tblDctosOrdenesDetalle.item =              "
                + getItem() + "                                     "
                + "AND tblDctosOrdenesDetalle.cantidadEntregada >=  "
                + getCantidadFacturada();


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
            return okIngresar;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {
            // Cierra el Resultset
            jdbcAccess.cleanup(connection, selectStatement, null);
            return okIngresar;
        }
    }

    // actualizaCantidad
    public boolean actualizaCantidad() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                "UPDATE tblDctosOrdenesDetalle                      "
                + "SET    tblDctosOrdenesDetalle.comentario      = '"
                + getComentario() + "'  ,                           "
                + "    tblDctosOrdenesDetalle.cantidad           =  "
                + getCantidad() + ",                                "
                + "    tblDctosOrdenesDetalle.cantidadFacturada  =  "
                + getCantidadFacturada() + ",                       "
                + "    tblDctosOrdenesDetalle.idTipoEmbalaje  =  "
                + getUnidadDian() + ",                       "
                + "    tblDctosOrdenesDetalle.pesoFacturado      =  "
                + getPesoFacturado() + "                            "
                + "FROM   tblDctosOrdenes                           "
                + "INNER JOIN tblDctosOrdenesDetalle                "
                + "ON  tblDctosOrdenes.IDLOCAL       =              "
                + "                tblDctosOrdenesDetalle.IDLOCAL   "
                + "AND tblDctosOrdenes.IDTIPOORDEN   =              "
                + "            tblDctosOrdenesDetalle.IDTIPOORDEN   "
                + "AND tblDctosOrdenes.IDORDEN       =              "
                + "                tblDctosOrdenesDetalle.IDORDEN   "
                + "WHERE tblDctosOrdenes.IDLOCAL     =              "
                + getIdLocal() + "                                  "
                + "AND   tblDctosOrdenes.IDTIPOORDEN =              "
                + getIdTipoOrden() + "                              "
                + "AND   tblDctosOrdenes.IDLOG       =              "
                + getIdLog() + "                                    "
                + "AND   tblDctosOrdenesDetalle.item =              "
                + getItem();

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
            return okIngresar;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {
            // Cierra el Resultset
            jdbcAccess.cleanup(connection, selectStatement, null);
            return okIngresar;
        }
    }

    // actualizaCantidadRemision
    public boolean actualizaCantidadRemision() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                "UPDATE tblDctosOrdenesDetalle                      "
                + "SET    tblDctosOrdenesDetalle.comentario      = '"
                + getComentario() + "'  ,                           "
                + "    tblDctosOrdenesDetalle.cantidad           =  "
                + getCantidad() + ",                                "
                + "    tblDctosOrdenesDetalle.cantidadEntregada  =  "
                + getCantidadEntregada() + ",                       "
                + "    tblDctosOrdenesDetalle.pesoEntregado      =  "
                + getPesoEntregado() + "                            "
                + "FROM   tblDctosOrdenes                           "
                + "INNER JOIN tblDctosOrdenesDetalle                "
                + "ON  tblDctosOrdenes.IDLOCAL       =              "
                + "                tblDctosOrdenesDetalle.IDLOCAL   "
                + "AND tblDctosOrdenes.IDTIPOORDEN   =              "
                + "            tblDctosOrdenesDetalle.IDTIPOORDEN   "
                + "AND tblDctosOrdenes.IDORDEN       =              "
                + "                tblDctosOrdenesDetalle.IDORDEN   "
                + "WHERE tblDctosOrdenes.IDLOCAL     =              "
                + getIdLocal() + "                                  "
                + "AND   tblDctosOrdenes.IDTIPOORDEN =              "
                + getIdTipoOrden() + "                              "
                + "AND   tblDctosOrdenes.IDLOG       =              "
                + getIdLog() + "                                    "
                + "AND   tblDctosOrdenesDetalle.item =              "
                + getItem();


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
            return okIngresar;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {
            // Cierra el Resultset
            jdbcAccess.cleanup(connection, selectStatement, null);
            return okIngresar;
        }
    }

    // modifica
    public boolean modifica(String xIdLocal,
            String xIdTipoOrden,
            String xIdOrden,
            String xItem) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        //
        Connection connection = null;

        String selectStr =
                "UPDATE tblDctosOrdenesDetalle                       "
                + "SET    tblDctosOrdenesDetalle.cantidad          =   "
                + getCantidad() + ",                                   "
                + "       tblDctosOrdenesDetalle.idClasificacion   =   "
                + getIdClasificacion() + ",                            "
                + "       tblDctosOrdenesDetalle.idResponsable     =   "
                + getIdResponsable() + ",                              "
                + "       tblDctosOrdenesDetalle.vrVentaUnitario   =   "
                + getVrVentaUnitarioSinIva() + " *           ( 1 + (   "
                + getPorcentajeIva() + " / 100 )) ,                    "
                + " tblDctosOrdenesDetalle.vrVentaUnitarioSinIva   =   "
                + getVrVentaUnitarioSinIva() + ",                      "
                + "       tblDctosOrdenesDetalle.porcentajeDscto   =   "
                + getPorcentajeDscto() + ",                            "
                + "       tblDctosOrdenesDetalle.fechaEntrega      =  '"
                + getFechaEntregaSqlServer() + "',                     "
                + "       tblDctosOrdenesDetalle.unidadVenta       =   "
                + getUnidadVenta() + ",                                "
                + "       tblDctosOrdenesDetalle.idTipoEmbalaje    =   "
                + getUnidadDian()+ ",                                "
                + "       tblDctosOrdenesDetalle.porcentajeIva     =   "
                + getPorcentajeIva() + "                               "
                + "WHERE  tblDctosOrdenesDetalle.idLocal =             "
                + xIdLocal + "                                         "
                + "AND tblDctosOrdenesDetalle.idTipoOrden =            "
                + xIdTipoOrden + "                                     "
                + "AND tblDctosOrdenesDetalle.idOrden     =            "
                + xIdOrden + "                                         "
                + "AND tblDctosOrdenesDetalle.item        =            "
                + xItem;


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
            return okIngresar;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {

            // Cierra el Resultset
            jdbcAccess.cleanup(connection, selectStatement, null);
            return okIngresar;
        }
    }

    // modificaCI_NoIva
    public boolean modificaCI_NoIva(String xIdLocal,
            String xIdTipoOrden,
            String xIdOrden,
            String xItem) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        //
        Connection connection = null;

        String selectStr =
                "UPDATE tblDctosOrdenesDetalle                       "
                + "SET    tblDctosOrdenesDetalle.vrVentaUnitario =   "
                + getVrVentaUnitarioSinIva() + ",                    "
                + "       tblDctosOrdenesDetalle.porcentajeIva   = 0 "
                + "WHERE  tblDctosOrdenesDetalle.idLocal =           "
                + xIdLocal + "                                       "
                + "AND tblDctosOrdenesDetalle.idTipoOrden =          "
                + xIdTipoOrden + "                                   "
                + "AND tblDctosOrdenesDetalle.idOrden     =          "
                + xIdOrden + "                                       "
                + "AND tblDctosOrdenesDetalle.item        =          "
                + xItem;

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
            return okIngresar;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {

            // Cierra el Resultset
            jdbcAccess.cleanup(connection, selectStatement, null);
            return okIngresar;
        }
    }

    // modificaCompra
    public boolean modificaCompra(String xIdLocal,
            String xIdTipoOrden,
            String xIdOrden,
            String xItem) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Vector contenedor = new Vector();
        Connection connection = null;

        String selectStr =
                "UPDATE tblDctosOrdenesDetalle             "
                + "SET    tblDctosOrdenesDetalle.cantidad =  "
                + getCantidad() + ",                         "
                + "       tblDctosOrdenesDetalle.vrCosto  =  "
                + getVrCosto() + "                           "
                + "WHERE  tblDctosOrdenesDetalle.idLocal  =  "
                + xIdLocal + "                               "
                + "AND tblDctosOrdenesDetalle.idTipoOrden =  "
                + xIdTipoOrden + "                           "
                + "AND tblDctosOrdenesDetalle.idOrden     =  "
                + xIdOrden + "                               "
                + "AND tblDctosOrdenesDetalle.item        =  "
                + xItem;

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
            return okIngresar;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {
            // Cierra la conexion a la base de datos limpiando la statement usada y la
            // conexion
            return okIngresar;
        }
    }

    // modifica
    public boolean modificaEstado(String xIdLocal,
            String xIdTipoOrden,
            String xIdOrden,
            String xItem) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Vector contenedor = new Vector();
        Connection connection = null;

        String selectStr =
                "UPDATE tblDctosOrdenesDetalle                                     "
                + "SET    tblDctosOrdenesDetalle.estado   =   " + getEstado() + " "
                + "WHERE  tblDctosOrdenesDetalle.idLocal  =   " + xIdLocal + " "
                + "AND tblDctosOrdenesDetalle.idTipoOrden =   " + xIdTipoOrden + " "
                + "AND tblDctosOrdenesDetalle.idOrden     =   " + xIdOrden + " "
                + "AND tblDctosOrdenesDetalle.item        =  " + xItem;

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
            return okIngresar;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {
            // Cierra la conexion a la base de datos limpiando la statement usada y la
            // conexion
            return okIngresar;
        }
    }

    // modificaReferencia
    public boolean modificaReferencia() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Vector contenedor = new Vector();
        Connection connection = null;

        String selectStr =
                "UPDATE tblDctosOrdenesDetalle                         "
                + "SET    tblDctosOrdenesDetalle.strIdReferencia = ( ? ),"
                + "       tblDctosOrdenesDetalle.nombrePlu       = ( ? ) "
                + "WHERE  tblDctosOrdenesDetalle.idLocal         = ( ? ) "
                + "AND    tblDctosOrdenesDetalle.idTipoOrden     = ( ? ) "
                + "AND    tblDctosOrdenesDetalle.idOrden         = ( ? ) "
                + "AND    tblDctosOrdenesDetalle.item            = ( ? ) ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            //
            selectStatement.setString(1, getStrIdReferencia());
            selectStatement.setString(2, getNombrePlu());
            selectStatement.setInt(3, getIdLocal());
            selectStatement.setInt(4, getIdTipoOrden());
            selectStatement.setInt(5, getIdOrden());
            selectStatement.setInt(6, getItem());

            // Obtiene el resultset
            selectStatement.execute();
            okIngresar = true;

            // Cierra el Resultset
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
            return okIngresar;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {
            // Cierra la conexion a la base de datos limpiando la statement usada y la
            // conexion
            return okIngresar;
        }
    }

    //retiraArticulosMarcados
    public boolean retiraArticulosMarcados() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Vector contenedor = new Vector();
        Connection connection = null;

        String selectStr =
                "DELETE FROM   tblDctosOrdenesDetalle          "
                + "WHERE  tblDctosOrdenesDetalle.idLocal     = "
                + getIdLocal() + "                             "
                + "AND    tblDctosOrdenesDetalle.idTipoOrden = "
                + getIdTipoOrden() + "                         "
                + "AND    tblDctosOrdenesDetalle.idOrden     = "
                + getIdOrden() + "                             "
                + "AND    tblDctosOrdenesDetalle.estado      = "
                + getEstado();


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
            return okIngresar;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {
            // Cierra la conexion a la base de datos limpiando la statement usada y la
            // conexion
            return okIngresar;
        }
    }

    //retiraItem
    public boolean retiraItem() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                "DELETE FROM tblDctosOrdenesDetalle          "
                + "FROM   tblDctosOrdenes                    "
                + "INNER JOIN tblDctosOrdenesDetalle         "
                + "ON  tblDctosOrdenes.IDLOCAL          =    "
                + "          tblDctosOrdenesDetalle.IDLOCAL  "
                + "AND tblDctosOrdenes.IDTIPOORDEN      =    "
                + "     tblDctosOrdenesDetalle.IDTIPOORDEN   "
                + "AND tblDctosOrdenes.IDORDEN          =    "
                + "         tblDctosOrdenesDetalle.IDORDEN   "
                + "WHERE  tblDctosOrdenesDetalle.item   =    "
                + getItem() + "                              "
                + "AND    tblDctosOrdenes.idLog         =    "
                + getIdLog();

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
            return okIngresar;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {
            // Cierra la conexion a la base de datos limpiando la statement usada y la
            // conexion
            return okIngresar;
        }
    }

    //acabarOrden
    public boolean acabarOrden() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                "  UPDATE tblDctosOrdenesDetalle                       "
                + "  SET tblDctosOrdenesDetalle.idEstadoRefOriginal =  "
                + getIdEstadoRefOriginal() + "                         "
                + "  FROM tblDctosOrdenesDetalle                       "
                + "  INNER JOIN tblDctosOrdenes                        "
                + "  ON  tblDctosOrdenes.IDLOCAL                    =  "
                + "            tblDctosOrdenesDetalle.IDLOCAL          "
                + "  AND tblDctosOrdenes.IDTIPOORDEN      =            "
                + "       tblDctosOrdenesDetalle.IDTIPOORDEN           "
                + "  AND tblDctosOrdenes.IDORDEN          =            "
                + "           tblDctosOrdenesDetalle.IDORDEN           "
                + "  WHERE  tblDctosOrdenesDetalle.item   =            "
                + getItem() + "                                        "
                + "  AND    tblDctosOrdenes.idLog         =            "
                + getIdLog();

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
            return okIngresar;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {
            // Cierra la conexion a la base de datos limpiando la statement usada y la
            // conexion
            return okIngresar;
        }
    }

    //retiraOrdenItem
    public boolean retiraOrdenItem() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Vector contenedor = new Vector();
        Connection connection = null;

        String selectStr =
                "DELETE FROM tblDctosOrdenesDetalle        "
                + "FROM   tblDctosOrdenes                  "
                + "INNER JOIN tblDctosOrdenesDetalle       "
                + "ON  tblDctosOrdenes.IDLOCAL           = "
                + "         tblDctosOrdenesDetalle.IDLOCAL "
                + "AND tblDctosOrdenes.IDTIPOORDEN       = "
                + "     tblDctosOrdenesDetalle.IDTIPOORDEN "
                + "AND tblDctosOrdenes.IDORDEN           = "
                + "         tblDctosOrdenesDetalle.IDORDEN "
                + "WHERE  tblDctosOrdenesDetalle.idLocal = "
                + getIdLocal() + "                         "
                + "AND    tblDctosOrdenes.idLog          = "
                + getIdLog();


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
            return okIngresar;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {
            // Cierra la conexion a la base de datos limpiando la statement usada y la
            // conexion
            return okIngresar;
        }
    }

    //ingresaOrdenItem
    public boolean ingresaOrdenItem(String xIdMarcaAll,
            int xIdTipoOrdenOld) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        //
        String selectStr =
                " INSERT INTO tblDctosOrdenesDetalle            "
                + "            ([IDLOCAL]			"
                + "            ,[IDTIPOORDEN]			"
                + "            ,[IDORDEN]			"
                + "            ,[IDPLU]				"
                + "            ,[CANTIDAD]			"
                + "            ,[IDTIPO]			"
                + "            ,[PORCENTAJEIVA]			"
                + "            ,[VRVENTAUNITARIO]		"
                + "            ,[ESTADO]			"
                + "            ,[NOMBREPLU]			"
                + "            ,[EAN]				"
                + "            ,[VRVENTAORIGINAL]		"
                + "            ,[VRCOSTO]			"
                + "            ,[VRDSCTOPIE]			"
                + "            ,[PORCENTAJEDSCTO]		"
                + "            ,[CANTIDADPEDIDA]		"
                + "            ,[vrCostoNegociado]		"
                + "            ,[strIdBodega]			"
                + "            ,[vrCostoResurtido]		"
                + "            ,[STRIDLISTA]			"
                + "            ,[STRIDREFERENCIA]		"
                + "            ,[PESOTEORICO]			"
                + "            ,[NOMBREUNIDADMEDIDA]		"
                + "            ,[IDLOCALSUGERIDO]		"
                + "            ,[IDBODEGASUGERIDO]		"
                + "            ,[marcaArteCliente]		"
                + "            ,[referenciaCliente]		"
                + "            ,[comentario]			"
                + "            ,[item]				"
                + "            ,[itemPadre]			"
                + "            ,[idEstadoTx]			"
                + "            ,[idTipoTx]			"
                + "            ,[idReferenciaOriginal]		"
                + "            ,[idEstadoRefOriginal]		"
                + "            ,[idClasificacion]		"
                + "            ,[idResponsable]			"
                + "            ,[idBodega]			"
                + "            ,[vrImpoconsumo]			"
                + "            ,[vrCostoIND]			"
                + "            ,[vrIvaResurtido]		"
                + "            ,[idSubcuenta] 			"
                + "            ,[pesoPedido] 			"
                + "            ,[pesoTerminado] 		"
                + "            ,[pesoRetal] 			"
                + "            ,[cantidadTerminada] 		"
                + "            ,[cantidadFacturada] 		"
                + "            ,[pesoFacturado] 		"
                + "            ,[cantidadEntregada]  		"
                + "            ,[pesoEntregado])		"
                + " SELECT                                      "
                + getIdLocal() + " AS idLocal,		        "
                + getIdTipoOrden() + " AS idTipoOrden,		"
                + getIdOrden() + " AS idOrden,       		"
                + "        tblPlus.idPlu, 			"
                + "        0.0 AS cantidad,			"
                + "        tblPlus.idTipo,			"
                + "        tblPlus.porcentajeIva,		"
                + "        tblPlus.vrGeneral AS vrVentaUnitario,"
                + "        0 AS estado,				"
                + "      (tblCategorias.nombreCategoria + ' ' + "
                + "         tblPlus.nombrePlu) AS nombrePlu, 	"
                + "        tblPlus.idPlu AS ean, 		"
                + "      tblPlus.vrGeneral AS vrVentaOriginal,	"
                + "     (SELECT  TOP 1 tmpDET.vrCostoNegociado  "
                + "        FROM   tblDctosOrdenesDetalle tmpDET "
                + "        WHERE EXISTS (                       "
                + "        SELECT tblDctos.*                    "
                + "        FROM tblDctos                        "
                + "        WHERE tblDctos.IDLOCAL   =           "
                + "                        tmpDET.IDLOCAL       "
                + "        AND tblDctos.IDTIPOORDEN =           "
                + "                    tmpDET.IDTIPOORDEN       "
                + "        AND tblDctos.IDORDEN     =           "
                + "                        tmpDET.IDORDEN       "
                + "        AND tmpDET.idLocal       =           "
                + getIdLocal() + "                              "
                + "        AND tmpDET.IDTIPOORDEN   =           "
                + xIdTipoOrdenOld + "                           "
                + "        AND tmpDET.idPlu         =           "
                + " tblPlus.idPlu )                             "
                + "        ORDER BY tmpDET.idOrden DESC)        "
                + "                                 AS vrCosto, "
                + "        0.0 AS vrDsctoPie,			"
                + "        0.0 AS porcentajeDscto,		"
                + "        0.0 AS cantidadPedida,		"
                + "    (SELECT  TOP 1 tmpDET.vrCostoNegociado   "
                + "        FROM   tblDctosOrdenesDetalle tmpDET "
                + "        WHERE EXISTS (                       "
                + "        SELECT tblDctos.*                    "
                + "        FROM tblDctos                        "
                + "        WHERE tblDctos.IDLOCAL   =           "
                + "                        tmpDET.IDLOCAL       "
                + "        AND tblDctos.IDTIPOORDEN =           "
                + "                    tmpDET.IDTIPOORDEN       "
                + "        AND tblDctos.IDORDEN     =           "
                + "                        tmpDET.IDORDEN       "
                + "        AND tmpDET.idLocal       =           "
                + getIdLocal() + "                              "
                + "        AND tmpDET.IDTIPOORDEN   =           "
                + xIdTipoOrdenOld + "                           "
                + "        AND tmpDET.idPlu         =           "
                + " tblPlus.idPlu )                             "
                + "        ORDER BY tmpDET.idOrden DESC)        "
                + "                        AS vrCostoNegociado, "
                + "        01 AS strIdBodega,			"
                + "        tblPlus.vrCosto AS vrCostoResurtido,	"
                + "        01 AS strIdLista,			"
                + "        tblPlus.idPlu AS strIdReferencia, 	"
                + "        0.0 AS pesoTeorico,			"
                + "        1 AS NOMBREUNIDADMEDIDA,		"
                + getIdLocal() + " AS IDLOCALSUGERIDO,		"
                + "        01 AS IDBODEGASUGERIDO,		"
                + "        '' AS [marcaArteCliente],		"
                + "        tblPlus.idPlu AS referenciaCliente,	"
                + "        '' AS comentario,			"
                + "        tblPlus.idPlu AS item,		"
                + "        tblPlus.idPlu AS itemPadre,		"
                + "        0 AS idEstadoTx,			"
                + "        0 AS idTipoTx,			"
                + "      tblPlus.idPlu AS idReferenciaOriginal,	"
                + "        0 AS idEstadoRefOriginal,		"
                + "        0 AS idClasificacion,		"
                + "        0 AS idResponsable,			"
                + "        01 AS idBodega,			"
                + "        tblPlus.vrImpoconsumo,		"
                + "        tblPlus.vrCostoIND,			"
                + "        0 AS vrIvaResurtido,			"
                + "        0 AS idSubcuenta,    		"
                + "        0.0 AS pesoPedido, 			"
                + "        0.0 AS pesoTerminado,  		"
                + "        0.0 AS pesoRetal, 			"
                + "        0.0 AS cantidadTerminada, 		"
                + "        0.0 AS cantidadFacturada, 		"
                + "        0.0 AS pesoFacturado,		"
                + "        0.0 AS cantidadEntregada, 		"
                + "        0.0 AS pesoEntregado 		"
                + " FROM   tblCategorias			"
                + " INNER JOIN tblLineas			"
                + " ON tblCategorias.idLinea     = 		"
                + "                    tblLineas.idLinea	"
                + " INNER JOIN tblPlus				"
                + " ON tblCategorias.IdCategoria = 		"
                + "                  tblPlus.idCategoria	"
                + " AND tblCategorias.idLinea    = 		"
                + "                      tblPlus.idLinea	"
                + " INNER JOIN tblMarcas			"
                + " ON tblPlus.idMarca           = 		"
                + "                    tblMarcas.idMarca	"
                + " WHERE tblPlus.idMarca IN                   ("
                + xIdMarcaAll + ")                              "
                + " AND tblPlus.idTipo           = 1            "
                + " ORDER BY tblCategorias.nombreCategoria, 	"
                + "          tblPlus.nombrePlu			";

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
            return okIngresar;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {
            // Cierra la conexion a la base de datos limpiando la statement usada y la
            // conexion
            return okIngresar;
        }
    }

    //actualizaCostoResurtido
    public boolean actualizaCostoResurtido() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        //
        String selectStr =
                " UPDATE tblDctosOrdenesDetalle                    "
                + " SET tblDctosOrdenesDetalle.VRCOSTO           = "
                + "       tblDctosOrdenesDetalle.vrCostoIND,       "
                + "    tblDctosOrdenesDetalle.vrCostoNegociado   = "
                + "       tblDctosOrdenesDetalle.vrCostoIND        "
                + " WHERE tblDctosOrdenesDetalle.VRCOSTO IS NULL   "
                + " OR tblDctosOrdenesDetalle.VRCOSTO  = 0         "
                + " AND tblDctosOrdenesDetalle.idLocal           = "
                + getIdLocal() + "                                 "
                + " AND tblDctosOrdenesDetalle.idTipoOrden       = "
                + getIdTipoOrden() + "                             "
                + " AND tblDctosOrdenesDetalle.idOrden           = "
                + getIdOrden() + "                                 ";

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
            return okIngresar;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {
            // Cierra la conexion a la base de datos limpiando la statement usada y la
            // conexion
            return okIngresar;
        }
    }

    // validaArticulosxOrden
    public boolean validaArticulosxOrden() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        //
        int maximaIdOrden = 0;

        boolean validaOk = false;
        Connection connection = null;

        String selectStr =
                "SELECT COUNT(tblDctosOrdenesDetalle.idPlu) AS     "
                + "        cantidadArticulos                         "
                + "FROM   tblDctosOrdenesDetalle                     "
                + "WHERE  tblDctosOrdenesDetalle.idLocal     = ( ? ) "
                + "AND    tblDctosOrdenesDetalle.idTipoOrden = ( ? ) "
                + "AND    tblDctosOrdenesDetalle.idOrden     = ( ? ) ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            //
            selectStatement.setInt(1, getIdLocal());
            selectStatement.setInt(2, getIdTipoOrden());
            selectStatement.setInt(3, getIdOrden());

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            int idRegistros = 0;

            //
            if (rs.next()) {

                idRegistros = rs.getInt("cantidadArticulos");
                if (idRegistros > 0) {
                    validaOk = true;
                }

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
            return validaOk;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {

            // Cierra la conexion
            return validaOk;

        }
    }

    // validaOrden
    public boolean validaOrden() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        boolean validaOk = false;
        Connection connection = null;

        String selectStr =
                "SELECT tblDctosOrdenesDetalle.*                 "
                + "FROM   tblDctosOrdenes                        "
                + "INNER JOIN tblDctosOrdenesDetalle             "
                + "ON tblDctosOrdenes.IDLOCAL      =             "
                + "               tblDctosOrdenesDetalle.IDLOCAL "
                + "AND tblDctosOrdenes.IDTIPOORDEN =             "
                + "           tblDctosOrdenesDetalle.IDTIPOORDEN "
                + "AND tblDctosOrdenes.IDORDEN     =             "
                + "               tblDctosOrdenesDetalle.IDORDEN "
                + "WHERE  tblDctosOrdenes.idLocal      =         "
                + getIdLocal() + "                               "
                + "AND    tblDctosOrdenes.IDTIPOORDEN  =         "
                + getIdTipoOrden() + "                           "
                + "AND    tblDctosOrdenes.idLog        =         "
                + getIdLog();

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
                validaOk = true;

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
            return validaOk;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {

            // Cierra la conexion
            return validaOk;

        }
    }

    // retiraDevolucion
    public boolean retiraDevolucion(int xIdLog) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Vector contenedor = new Vector();
        Connection connection = null;

        String selectStr =
                "DELETE FROM tblDctosOrdenesDetalle    "
                + "FROM        tblDctosOrdenes           "
                + "INNER JOIN  tblDctosOrdenesDetalle    "
                + "ON tblDctosOrdenes.IDLOCAL      =     "
                + "       tblDctosOrdenesDetalle.IDLOCAL "
                + "AND tblDctosOrdenes.IDTIPOORDEN =     "
                + "   tblDctosOrdenesDetalle.IDTIPOORDEN "
                + "AND tblDctosOrdenes.IDORDEN     =     "
                + "       tblDctosOrdenesDetalle.IDORDEN "
                + "WHERE  tblDctosOrdenes.IDLOG    =     "
                + xIdLog;

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
            return okIngresar;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {
            // Cierra la conexion a la base de datos limpiando la statement usada y la
            // conexion
            return okIngresar;
        }
    }

    // retiraPlu
    public boolean retiraPlu() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                "DELETE FROM tblDctosOrdenesDetalle    "
                + "FROM        tblDctosOrdenes           "
                + "INNER JOIN  tblDctosOrdenesDetalle    "
                + "ON tblDctosOrdenes.IDLOCAL      =     "
                + "       tblDctosOrdenesDetalle.IDLOCAL "
                + "AND tblDctosOrdenes.IDTIPOORDEN =     "
                + "   tblDctosOrdenesDetalle.IDTIPOORDEN "
                + "AND tblDctosOrdenes.IDORDEN     =     "
                + "       tblDctosOrdenesDetalle.IDORDEN "
                + "WHERE  tblDctosOrdenes.IDLOG    =     "
                + getIdLog() + "                         "
                + "AND    tblDctosOrdenes.idLocal  =     "
                + getIdLocal() + "                       "
                + "AND    tblDctosOrdenes.idTipoOrden =  "
                + getIdTipoOrden() + "                   "
                + "AND    tblDctosOrdenesDetalle.idPlu = "
                + getIdPlu();

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
            return okIngresar;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {
            // Cierra la conexion a la base de datos limpiando la statement usada y la
            // conexion
            return okIngresar;
        }
    }

    // modifica
    public boolean actualizaDescuentoPie() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                "UPDATE tblDctosOrdenesDetalle                "
                + "SET    tblDctosOrdenesDetalle.vrDsctoPie = "
                + getVrDsctoPie() + "                         "
                + "WHERE EXISTS (                             "
                + "   SELECT tblDctosOrdenes.*                "
                + "   FROM tblDctosOrdenes                    "
                + "   WHERE tblDctosOrdenes.idLocal      =    "
                + "          tblDctosOrdenesDetalle.idLocal   "
                + "   AND    tblDctosOrdenes.idTipoOrden =    "
                + "      tblDctosOrdenesDetalle.idTipoOrden   "
                + "   AND   tblDctosOrdenes.idOrden      =    "
                + "            tblDctosOrdenesDetalle.idOrden "
                + "   AND tblDctosOrdenes.idLocal        =    "
                + getIdLocal() + "                            "
                + "   AND tblDctosOrdenes.idTipoOrden    =    "
                + getIdTipoOrden() + "                        "
                + "   AND tblDctosOrdenes.idLog          =    "
                + getIdLog() + ")";

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
            return okIngresar;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {
            // Cierra la conexion a la base de datos limpiando la statement usada y la
            // conexion
            return okIngresar;
        }
    }

    // listaDetalle
    public FachadaDctoOrdenDetalleBean listaUnDctoDetalle() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();


        // Variable de fachada de los datos
        FachadaDctoOrdenDetalleBean fachadaBean = new FachadaDctoOrdenDetalleBean();

        Connection connection = null;

        String selectStr =
                "SELECT tblDctosOrdenesDetalle.idLocal,            "
                + "        tblDctosOrdenesDetalle.idTipoOrden,       "
                + "        tblDctosOrdenesDetalle.idOrden,           "
                + "        tblDctosOrdenesDetalle.cantidad,          "
                + "        tblDctosOrdenesDetalle.nombrePlu,         "
                + "        tblDctosOrdenesDetalle.idPlu,             "
                + "        tblDctosOrdenesDetalle.idTipo,            "
                + "        tblDctosOrdenesDetalle.estado,            "
                + "        tblDctosOrdenesDetalle.porcentajeIva,     "
                + "        tblDctosOrdenesDetalle.vrVentaUnitario,   "
                + "        tblDctosOrdenesDetalle.ean,               "
                + "        tblDctosOrdenesDetalle.vrVentaOriginal,   "
                + "        tblDctosOrdenesDetalle.vrCosto,           "
                + "        tblDctosOrdenesDetalle.vrDsctoPie,        "
                + "        tblDctosOrdenesDetalle.porcentajeDscto,   "
                + "        tblDctosOrdenesDetalle.cantidadPedida,    "
                + "        tblDctosOrdenesDetalle.vrCostoNegociado,  "
                + "        tblDctosOrdenesDetalle.strIdLista ,       "
                + "        tblDctosOrdenesDetalle.strIdReferencia,   "
                + "        tblDctosOrdenesDetalle.pesoTeorico,       "
                + "        tblDctosOrdenesDetalle.nombreUnidadMedida,"
                + "        tblDctosOrdenesDetalle.referenciaCliente, "
                + "        tblDctosOrdenesDetalle.comentario,        "
                + "        tblDctosOrdenesDetalle.item,              "
                + "        tblDctosOrdenesDetalle.vrImpoconsumo,     "
                + "        tblDctosOrdenesDetalle.idOperacion        "
                + "FROM tblDctosOrdenesDetalle                       "
                + "WHERE tblDctosOrdenesDetalle.idLocal     =        "
                + getIdLocal() + "                                   "
                + "AND   tblDctosOrdenesDetalle.idTipoOrden =        "
                + getIdTipoOrden() + "                               "
                + "AND   tblDctosOrdenesDetalle.idOrden     =        "
                + getIdOrden();

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            if (rs.next()) {

                fachadaBean = new FachadaDctoOrdenDetalleBean();

                //
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setIdOrden(rs.getInt("idOrden"));
                fachadaBean.setCantidad(rs.getDouble("cantidad"));
                fachadaBean.setNombrePlu(rs.getString("nombrePlu"));
                fachadaBean.setIdPlu(rs.getInt("idPlu"));
                fachadaBean.setIdTipo(rs.getInt("idTipo"));
                fachadaBean.setEstado(rs.getInt("estado"));
                fachadaBean.setPorcentajeIva(rs.getDouble("PorcentajeIva"));
                fachadaBean.setVrVentaUnitario(rs.getDouble("vrVentaUnitario"));
                fachadaBean.setEan(rs.getDouble("ean"));
                fachadaBean.setVrVentaOriginal(rs.getInt("vrVentaOriginal"));
                fachadaBean.setVrCosto(rs.getDouble("vrCosto"));
                fachadaBean.setVrDsctoPie(rs.getDouble("vrDsctoPie"));
                fachadaBean.setPorcentajeDscto(rs.getDouble("porcentajeDscto"));
                fachadaBean.setCantidadPedida(rs.getDouble("cantidadPedida"));
                fachadaBean.setVrCostoNegociado(
                        rs.getDouble("vrCostoNegociado"));
                fachadaBean.setStrIdLista(rs.getString("strIdLista"));
                fachadaBean.setStrIdReferencia(rs.getString("strIdReferencia"));
                fachadaBean.setPesoTeorico(rs.getDouble("pesoTeorico"));
                fachadaBean.setNombreUnidadMedida(
                        rs.getString("nombreUnidadMedida"));
                fachadaBean.setReferenciaCliente(
                        rs.getString("referenciaCliente"));
                fachadaBean.setComentario(rs.getString("comentario"));
                fachadaBean.setItem(rs.getInt("item"));
                fachadaBean.setVrImpoconsumo(rs.getDouble("vrImpoconsumo"));
                fachadaBean.setIdOperacion(rs.getInt("idOperacion"));

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

    // listaResurtido
    public Vector listaDetalleLocalOrigen() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        // Variable de fachada de los datos
        FachadaDctoOrdenDetalleBean fachadaBean;

        Connection connection = null;

        String selectStr =
                "    SELECT tblDctosOrdenesDetalle.IDLOCAL         "
                + "     ,tblDctosOrdenesDetalle.IDTIPOORDEN          "
                + "     ,tblDctosOrdenesDetalle.IDORDEN              "
                + "     ,tblDctosOrdenesDetalle.IDPLU                "
                + "     ,tblDctosOrdenesDetalle.CANTIDAD             "
                + "     ,tblDctosOrdenesDetalle.IDTIPO               "
                + "     ,tblDctosOrdenesDetalle.PORCENTAJEIVA        "
                + "     ,tblDctosOrdenesDetalle.VRVENTAUNITARIO      "
                + "     ,tblDctosOrdenesDetalle.ESTADO               "
                + "     ,tblDctosOrdenesDetalle.NOMBREPLU            "
                + "     ,tblDctosOrdenesDetalle.EAN                  "
                + "     ,tblDctosOrdenesDetalle.VRVENTAORIGINAL      "
                + "     ,tblDctosOrdenesDetalle.VRCOSTO              "
                + "     ,tblDctosOrdenesDetalle.VRDSCTOPIE           "
                + "     ,tblDctosOrdenesDetalle.PORCENTAJEDSCTO      "
                + "     ,tblDctosOrdenesDetalle.CANTIDADPEDIDA       "
                + "     ,tblDctosOrdenesDetalle.vrCostoNegociado     "
                + "     ,tblDctosOrdenesDetalle.strIdBodega          "
                + "     ,tblDctosOrdenesDetalle.vrCostoResurtido     "
                + "     ,tblDctosOrdenesDetalle.STRIDLISTA           "
                + "     ,tblDctosOrdenesDetalle.STRIDREFERENCIA      "
                + "     ,tblDctosOrdenesDetalle.PESOTEORICO          "
                + "     ,tblDctosOrdenesDetalle.NOMBREUNIDADMEDIDA   "
                + "     ,tblDctosOrdenesDetalle.IDLOCALSUGERIDO      "
                + "     ,tblDctosOrdenesDetalle.IDBODEGASUGERIDO     "
                + "     ,tblDctosOrdenesDetalle.marcaArteCliente     "
                + "     ,tblDctosOrdenesDetalle.referenciaCliente    "
                + "     ,tblDctosOrdenesDetalle.comentario           "
                + "     ,tblDctosOrdenesDetalle.item                 "
                + "     ,tblDctosOrdenesDetalle.itemPadre            "
                + "     ,tblDctosOrdenesDetalle.idEstadoTx           "
                + "     ,tblDctosOrdenesDetalle.idTipoTx             "
                + "     ,tblDctosOrdenesDetalle.idReferenciaOriginal "
                + "     ,tblDctosOrdenesDetalle.idEstadoRefOriginal  "
                + "     ,tblDctosOrdenesDetalle.idClasificacion      "
                + "     ,tblDctosOrdenesDetalle.idResponsable        "
                + "     ,tblDctosOrdenesDetalle.fechaEntrega         "
                + "     ,tblDctosOrdenesDetalle.idBodega             "
                + "     ,tblDctosOrdenesDetalle.vrImpoconsumo        "
                + "     ,tblDctosOrdenesDetalle.vrCostoIND           "
                + "     ,tblDctosOrdenesDetalle.vrIvaResurtido       "
                + "     ,tblDctosOrdenesDetalle.idSubcuenta          "
                + "     ,tblDctosOrdenesDetalle.cantidadBonificada   "
                + "     ,tblDctosOrdenesDetalle.idOrdenOrigen        "
                + "     ,tblDctosOrdenesDetalle.idLocalOrigen        "
                + "     ,tblDctosOrdenesDetalle.idTipoOrdenOrigen    "
                + " FROM tblDctosOrdenesDetalle,                     "
                + "      tblDctosOrdenes                             "
                + " WHERE tblDctosOrdenes.IDORDEN =                  "
                + "                tblDctosOrdenesDetalle.IDORDEN    "
                + " AND tblDctosOrdenesDetalle.idTipoOrden =         "
                + getIdTipoOrden() + "                               "
                + " AND tblDctosOrdenesDetalle.idTipoOrdenOrigen =   "
                + getIdTipoOrdenOrigen() + "                         "
                + " AND tblDctosOrdenesDetalle.idLocalOrigen =       "
                + getIdLocalOrigen() + "                             ";


        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            //
            while (rs.next()) {

                fachadaBean = new FachadaDctoOrdenDetalleBean();

                //
                fachadaBean.setIdLocal(rs.getInt("IDLOCAL"));
                fachadaBean.setIdTipoOrden(rs.getInt("IDTIPOORDEN"));
                fachadaBean.setIdOrden(rs.getInt("IDORDEN"));
                fachadaBean.setCantidad(rs.getDouble("CANTIDAD"));
                fachadaBean.setNombrePlu(rs.getString("NOMBREPLU"));
                fachadaBean.setIdPlu(rs.getInt("IDPLU"));
                fachadaBean.setIdTipo(rs.getInt("IDTIPO"));
                fachadaBean.setEstado(rs.getInt("ESTADO"));
                fachadaBean.setPorcentajeIva(rs.getDouble("PORCENTAJEIVA"));
                fachadaBean.setVrVentaUnitario(rs.getDouble("VRVENTAUNITARIO"));
                fachadaBean.setEan(rs.getDouble("EAN"));
                fachadaBean.setVrVentaOriginal(rs.getInt("VRVENTAORIGINAL"));
                fachadaBean.setVrCosto(rs.getDouble("VRCOSTO"));
                fachadaBean.setVrDsctoPie(rs.getDouble("VRDSCTOPIE"));
                fachadaBean.setPorcentajeDscto(rs.getDouble("PORCENTAJEDSCTO"));
                fachadaBean.setCantidadPedida(rs.getDouble("CANTIDADPEDIDA"));
                fachadaBean.setVrCostoNegociado(rs.getDouble("vrCostoNegociado"));
                fachadaBean.setStrIdLista(rs.getString("STRIDLISTA"));
                fachadaBean.setStrIdReferencia(rs.getString("STRIDREFERENCIA"));
                fachadaBean.setPesoTeorico(rs.getDouble("PESOTEORICO"));
                fachadaBean.setNombreUnidadMedida(rs.getString("NOMBREUNIDADMEDIDA"));
                fachadaBean.setMarcaArteCliente(rs.getString("marcaArteCliente"));
                fachadaBean.setReferenciaCliente(rs.getString("referenciaCliente"));
                fachadaBean.setComentario(rs.getString("comentario"));
                fachadaBean.setItem(rs.getInt("item"));
                fachadaBean.setVrImpoconsumo(rs.getDouble("vrImpoconsumo"));
                fachadaBean.setStrIdBodega(rs.getString("strIdBodega"));
                fachadaBean.setVrCostoResurtido(rs.getDouble("vrCostoResurtido"));
                fachadaBean.setIdLocalSugerido(rs.getInt("IDLOCALSUGERIDO"));
                fachadaBean.setIdBodegaSugerido(rs.getString("IDBODEGASUGERIDO"));
                fachadaBean.setItemPadre(rs.getInt("itemPadre"));
                fachadaBean.setIdEstadoTx(rs.getInt("idEstadoTx"));
                fachadaBean.setIdTipoTx(rs.getInt("idTipoTx"));
                fachadaBean.setIdReferenciaOriginal(rs.getString("idReferenciaOriginal"));
                fachadaBean.setIdEstadoRefOriginal(rs.getString("idEstadoRefOriginal"));
                fachadaBean.setIdClasificacion(rs.getString("idClasificacion"));
                fachadaBean.setIdResponsable(rs.getString("idResponsable"));
                fachadaBean.setFechaEntrega(rs.getString("fechaEntrega"));
                fachadaBean.setIdBodega(rs.getInt("idBodega"));
                fachadaBean.setVrCostoIND(rs.getDouble("vrCostoIND"));
                fachadaBean.setVrIvaResurtido(rs.getDouble("vrIvaResurtido"));
                fachadaBean.setIdSubcuenta(rs.getString("idSubcuenta"));
                fachadaBean.setCantidadBonificada(rs.getDouble("cantidadBonificada"));
                fachadaBean.setIdTipoOrdenOrigen(rs.getInt("idTipoOrdenOrigen"));
                fachadaBean.setIdOrdenOrigen(rs.getInt("idOrdenOrigen"));
                fachadaBean.setIdLocalOrigen(rs.getInt("idLocalOrigen"));

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

    // actualizaResurtido
    public boolean actualizaResurtidoTraslado() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                "UPDATE tblDctosOrdenesDetalle                      "
                + "SET    tblDctosOrdenesDetalle.cantidad       =   "
                + getCantidad() + "                                 "
                + "FROM   tblDctosOrdenes                           "
                + "INNER JOIN tblDctosOrdenesDetalle                "
                + "ON  tblDctosOrdenes.IDLOCAL       =              "
                + "                tblDctosOrdenesDetalle.IDLOCAL   "
                + "AND tblDctosOrdenes.IDTIPOORDEN   =              "
                + "            tblDctosOrdenesDetalle.IDTIPOORDEN   "
                + "AND tblDctosOrdenes.IDORDEN       =              "
                + "                tblDctosOrdenesDetalle.IDORDEN   "
                + "WHERE tblDctosOrdenes.IDLOCAL     =              "
                + getIdLocal() + "                                  "
                + "AND   tblDctosOrdenes.IDTIPOORDEN =              "
                + getIdTipoOrden() + "                              "
                + "AND   tblDctosOrdenes.IDLOG       =              "
                + getIdLog() + "                                    "
                + "AND   tblDctosOrdenesDetalle.idPlu=              "
                + getIdPlu();


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
            return okIngresar;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {

            // conexion
            jdbcAccess.cleanup(connection, selectStatement, null);
            return okIngresar;
        }
    }

    // actualizaOrigen
    public boolean actualizaOrigen() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                " UPDATE tblDctosOrdenesDetalle                       "
                + " SET tblDctosOrdenesDetalle.idOrdenOrigen        = "
                + getIdOrdenOrigen() + "                              "
                + "       ,tblDctosOrdenesDetalle.idLocalOrigen     = "
                + getIdLocalOrigen() + "                              "
                + "       ,tblDctosOrdenesDetalle.idTipoOrdenOrigen = "
                + getIdTipoOrdenOrigen() + "                          "
                + " WHERE tblDctosOrdenesDetalle.IDLOCAL            = "
                + getIdLocal() + "                                    "
                + " AND   tblDctosOrdenesDetalle.IDTIPOORDEN        = "
                + getIdTipoOrden() + "                                "
                + " AND   tblDctosOrdenesDetalle.IDORDEN            = "
                + getIdOrden();

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
            return okIngresar;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {

            // Cierra el Resultset
            jdbcAccess.cleanup(connection, selectStatement, null);
            return okIngresar;
        }
    }
}
