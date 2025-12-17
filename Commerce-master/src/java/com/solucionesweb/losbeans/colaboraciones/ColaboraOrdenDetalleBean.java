package com.solucionesweb.losbeans.colaboraciones;

// Importa la clase helper JDBCAccess que contiene los accesos a la base de datos
import com.solucionesweb.lasayudas.JDBCAccess;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaDctoOrdenDetalleBean;
//import com.solucionesweb.losbeans.utilidades.Day;

// Importa los paquetes de java
import java.sql.*;
import java.io.*;
import java.io.IOException.*;

//import java.util.Date;
import java.util.Vector;
import java.io.IOException.*;

// Importa los paquetes de javax
import javax.naming.*;

public class ColaboraOrdenDetalleBean extends FachadaDctoOrdenDetalleBean
        implements Serializable {

    // Nombre JNDI del Data Source que esta clase requiere
    private static final String DATA_SOURCE_NAME = "CommerceAccess";
    // Clase helper usada para acceder a la base de datos
    private JDBCAccess jdbcAccess;

    // Metodo constructor por defecto sin parametros
    public ColaboraOrdenDetalleBean() {

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
                "SELECT tblDctosOrdenesDetalle.idPlu,				          "
                + "       tblDctosOrdenesDetalle.strIdReferencia,			      "
                + "       tblDctosOrdenesDetalle.nombrePlu,			          "
                + "       tblDctosOrdenesDetalle.idLocalSugerido,			      "
                + "       tblDctosOrdenesDetalle.idBodegaSugerido,		      "
                + "       tblDctosOrdenesDetalle.item,			        	  "
                + "       tblDctosOrdenesDetalle.itemPadre,			          "
                + "       tblDctosOrdenesDetalle.cantidad,			          "
                + "       tblDctosOrdenesDetalle.vrCosto,		   		          "
                + "       tblDctosOrdenesDetalle.vrVentaUnitario,	    		  "
                + "       tblDctosOrdenesDetalle.vrVentaOriginal,			      "
                + "       tblDctosOrdenesDetalle.porcentajeDscto,		    	  "
                + "       tblDctosOrdenesDetalle.porcentajeIva,			      "
                + "       (tblDctosOrdenesDetalle.cantidad        *		      "
                + "       tblDctosOrdenesDetalle.vrVentaUnitario) /		      "
                + "       ( 1 + ( tblDctosOrdenesDetalle.porcentajeIva / 100) ) "
                + "                                     AS vrVentaSinIva,		  "
                + "       (tblDctosOrdenesDetalle.cantidad        *		      "
                + "       tblDctosOrdenesDetalle.vrVentaUnitario)               "
                + "                                     AS vrVentaConIva,		  "
                + "              (((tblDctosOrdenesDetalle.cantidad       *     "
                + "              tblDctosOrdenesDetalle.vrVentaUnitario)  /     "
                + " ( 1 + ( tblDctosOrdenesDetalle.porcentajeIva / 100))) *     "
                + " ( 1 - ( tblDctosOrdenesDetalle.vrDsctoPie / 100))     *     "
                + " ( 1 - ( tblDctosOrdenesDetalle.porcentajeDscto / 100)))     "
                + "                                     AS vrVentaSinDscto,     "
                + "      (((tblDctosOrdenesDetalle.cantidad        *		      "
                + "       tblDctosOrdenesDetalle.vrVentaUnitario) /		      "
                + " ( 1 + ( tblDctosOrdenesDetalle.porcentajeIva / 100))) *     "
                + " ( 1 - ( tblDctosOrdenesDetalle.porcentajeDscto / 100))*     "
                + " ( 1 - ( tblDctosOrdenesDetalle.vrDsctoPie / 100))     *     "
                + "        ( tblDctosOrdenesDetalle.porcentajeIva / 100 ))      "
                + "                                      AS vrIvaVenta,         "
                + "       (tblDctosOrdenesDetalle.cantidad        *		      "
                + "        tblDctosOrdenesDetalle.pesoTeorico )			      "
                + "                                   AS pesoTeoricoTotal,	  "
                + "        tblDctosOrdenesDetalle.marcaArteCliente,		      "
                + "        tblDctosOrdenesDetalle.referenciaCliente,		      "
                + "        tblDctosOrdenesDetalle.comentario,			          "
                + "        tblDctosOrdenesDetalle.idEstadoTx,			          "
                + "        tblDctosOrdenesDetalle.nombreUnidadMedida,		      "
                + "        tmpPlu.vrCosto AS vrCostoActual,			          "
                + "        (tblDctosOrdenesDetalle.cantidad *			          "
                + "        tblDctosOrdenesDetalle.vrCosto ) 		        	  "
                + "                                     AS vrCostoConIva,		  "
                + "        tmpPlu.existencia,				                 	  "
                + "        tmpPlu.nombreCategoria,				              "
                + "        tmpPlu.nombreMarca,					              "
                + "        tmpPlu.nombreLinea					                  "
                + "FROM   tblDctosOrdenesDetalle ,				              "
                + "       (SELECT  tblPlusInventario.idLocal,			          "
                + "                tblPlusInventario.idPlu,			          "
                + "                tblPlusInventario.existencia,			      "
                + "                tblCategorias.nombreCategoria,			      "
                + "                tblMarcas.nombreMarca,				          "
                + "                tblLineas.nombreLinea,				          "
                + "                tblPlus.vrCosto				              "
                + "        FROM    tblPlus					                  "
                + "        INNER JOIN tblMarcas					              "
                + "        ON tblPlus.idMarca       =                           "
                + "                   tblMarcas.idMarca				          "
                + "        INNER JOIN tblCategorias				              "
                + "        ON tblPlus.idLinea       =				              "
                + "               tblCategorias.idLinea				          "
                + "        AND tblPlus.idCategoria  =				              "
                + "           tblCategorias.IdCategoria				          "
                + "        INNER JOIN tblLineas					              "
                + "        ON tblCategorias.idLinea =				              "
                + "                   tblLineas.idLinea				          "
                + "        INNER JOIN tblPlusInventario				          "
                + "        ON tblPlus.idPlu         =				              "
                + "                     tblPlusInventario.idPlu                 "
                + "        WHERE tblPlusInventario.idLocal =                    "
                + getIdLocal() + "                                              "
                + "        AND   tblPlusInventario.idBodega=                    "
                + getIdBodega() + " ) AS tmpPlu			                      "
                + "WHERE EXISTS ( SELECT tblDctosOrdenes.*			          "
                + "               FROM tblDctosOrdenes				          "
                + "               WHERE tblDctosOrdenes.idLocal     =		      "
                + "                       tblDctosOrdenesDetalle.idLocal		  "
                + "               AND   tblDctosOrdenes.idTipoOrden =           "
                + "                   tblDctosOrdenesDetalle.idTipoOrden        "
                + "               AND   tblDctosOrdenes.idOrden     =           "
                + "                       tblDctosOrdenesDetalle.idOrden        "
                + "               AND   tblDctosOrdenes.idLocal     =           "
                + getIdLocal() + "                                              "
                + "               AND   tblDctosOrdenes.idLog       =           "
                + getIdLog() + "                                                "
                + "               AND   tblDctosOrdenes.idTipoOrden =           "
                + getIdTipoOrden() + " )                                        "
                + "AND  tmpPlu.idPlu    = tblDctosOrdenesDetalle.idPlu          "
                + "AND  tmpPlu.idLocal  = tblDctosOrdenesDetalle.idLocal        "
                + "ORDER BY tblDctosOrdenesDetalle.itemPadre,                   "
                + "         tblDctosOrdenesDetalle.item                         ";

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

                fachadaBean.setIdPlu(rs.getInt("idPlu"));
                fachadaBean.setStrIdReferencia(rs.getString("strIdReferencia"));
                fachadaBean.setNombrePlu(rs.getString("nombrePlu"));
                fachadaBean.setIdLocalSugerido(rs.getInt("idLocalSugerido"));
                fachadaBean.setIdBodegaSugerido(rs.getString("idBodegaSugerido"));
                fachadaBean.setItem(rs.getInt("item"));
                fachadaBean.setItemPadre(rs.getInt("itemPadre"));
                fachadaBean.setCantidad(rs.getDouble("cantidad"));
                fachadaBean.setVrCosto(rs.getDouble("vrCosto"));
                fachadaBean.setVrVentaUnitario(rs.getDouble("vrVentaUnitario"));
                fachadaBean.setVrVentaOriginal(rs.getDouble("vrVentaOriginal"));
                fachadaBean.setCantidadArticulos(1);
                fachadaBean.setPorcentajeDscto(rs.getDouble("porcentajeDscto"));
                fachadaBean.setPorcentajeIva(rs.getDouble("porcentajeIva"));
                fachadaBean.setVrVentaSinIva(rs.getDouble("vrVentaSinIva"));
                fachadaBean.setVrVentaConIva(rs.getDouble("vrVentaConIva"));
                fachadaBean.setVrVentaSinDscto(rs.getDouble("vrVentaSinDscto"));
                fachadaBean.setVrIvaVenta(rs.getDouble("vrIvaVenta"));
                fachadaBean.setPesoTeoricoTotal(rs.getDouble("pesoTeoricoTotal"));
                fachadaBean.setMarcaArteCliente(rs.getString("marcaArteCliente"));
                fachadaBean.setReferenciaCliente(
                        rs.getString("referenciaCliente"));
                fachadaBean.setComentario(rs.getString("comentario"));
                fachadaBean.setIdEstadoTx(rs.getInt("idEstadoTx"));
                fachadaBean.setNombreUnidadMedida(
                        rs.getString("nombreUnidadMedida"));
                fachadaBean.setVrCostoActual(rs.getDouble("vrCostoActual"));
                fachadaBean.setVrCostoConIva(rs.getDouble("vrCostoConIva"));
                fachadaBean.setExistencia(rs.getDouble("existencia"));
                fachadaBean.setNombreCategoria(rs.getString("nombreCategoria"));
                fachadaBean.setNombreMarca(rs.getString("nombreMarca"));
                fachadaBean.setNombreLinea(rs.getString("nombreLinea"));

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

    // listaOP
    public Vector listaOP() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        // Variable de fachada de los datos
        FachadaDctoOrdenDetalleBean fachadaBean;

        Connection connection = null;

        String selectStr =
                "SELECT tblDctosOrdenesDetalle.idLocal,				      "
                + "       tblDctosOrdenesDetalle.idTipoOrden,		              "
                + "       tblDctosOrdenesDetalle.idOrden,       		      "
                + "       tblDctosOrdenesDetalle.idOrdenOrigen,       		      "
                + "       tblDctosOrdenesDetalle.idPlu,		                      "
                + "       tblDctosOrdenesDetalle.strIdReferencia,		      "
                + "       tblDctosOrdenesDetalle.nombrePlu,			      "
                + "       tblDctosOrdenesDetalle.idLocalSugerido,		      "
                + "       tblDctosOrdenesDetalle.idBodegaSugerido,		      "
                + "       tblDctosOrdenesDetalle.idTipo,            		      "
                + "       tblDctosOrdenesDetalle.item,			              "
                + "       tblDctosOrdenesDetalle.itemPadre,			      "
                + "       tblDctosOrdenesDetalle.itemOrden,			      "
                + "       tblDctosOrdenesDetalle.cantidad,			      "
                + "       ( tblDctosOrdenesDetalle.cantidadTerminada -                "
                + "         tblDctosOrdenesDetalle.cantidadFacturada )                "
                + "                                    AS cantidadPendiente,          "
                + "       (tblDctosOrdenesDetalle.pesoTerminado)                         "
                + "                                    AS pesoPendiente,              "
                + "       tblDctosOrdenesDetalle.vrCosto,		   	      "
                + "       tblDctosOrdenesDetalle.vrVentaUnitario,	    	      "
                + "       tblDctosOrdenesDetalle.vrVentaOriginal,		      "
                + "       tblDctosOrdenesDetalle.porcentajeDscto,		      "
                + "       tblDctosOrdenesDetalle.porcentajeIva,			      "
                + "       (tblDctosOrdenesDetalle.cantidad          *		      "
                + "       (tblDctosOrdenesDetalle.vrVentaUnitario   -		      "
                + "        tblDctosOrdenesDetalle.vrImpoconsumo)    /		      "
                + "    ( 1 + ( tblDctosOrdenesDetalle.porcentajeIva /  100 ) ))       "
                + "                                           AS vrVentaSinIva,       "
                + "           (tblDctosOrdenesDetalle.cantidad      * 		      "
                + "                   tblDctosOrdenesDetalle.vrVentaUnitario )        "
                + "                                          AS vrVentaConIva,        "
                + "      ((tblDctosOrdenesDetalle.cantidad          *		      "
                + "       (tblDctosOrdenesDetalle.vrVentaUnitario   -		      "
                + "        tblDctosOrdenesDetalle.vrImpoconsumo)    /		      "
                + "  (( 1 + ( tblDctosOrdenesDetalle.porcentajeIva /  100 ))) *       "
                + "      ( 1 - ( tblDctosOrdenesDetalle.vrDsctoPie /  100 ) ) *       "
                + "  ( 1 - ( tblDctosOrdenesDetalle.porcentajeDscto /  100 ))))       "
                + "                                        AS vrVentaSinDscto,        "
                + "       (tblDctosOrdenesDetalle.cantidad          *		      "
                + "       (tblDctosOrdenesDetalle.vrVentaUnitario   -		      "
                + "        tblDctosOrdenesDetalle.vrImpoconsumo)    *		      "
                + "   ( 1 - ( tblDctosOrdenesDetalle.vrDsctoPie /  100 ) )   *        "
                + "   ( 1 - ( tblDctosOrdenesDetalle.porcentajeDscto /100))) -        "
                + "   (tblDctosOrdenesDetalle.cantidad          *		      "
                + "   (tblDctosOrdenesDetalle.vrVentaUnitario   -		      "
                + "    tblDctosOrdenesDetalle.vrImpoconsumo)    *		      "
                + "    ( 1 - ( tblDctosOrdenesDetalle.vrDsctoPie /  100 ) ) *         "
                + "    ( 1 -(tblDctosOrdenesDetalle.porcentajeDscto/ 100 )) /         "
                + "    ( 1 + ( tblDctosOrdenesDetalle.porcentajeIva /  100 ) ))       "
                + "                                              AS vrIvaVenta,       "
                + "       (tblDctosOrdenesDetalle.cantidad        *		      "
                + "        tblDctosOrdenesDetalle.pesoTeorico )			      "
                + "                                   AS pesoTeoricoTotal,	      "
                + "        tblDctosOrdenesDetalle.marcaArteCliente,		      "
                + "        tblDctosOrdenesDetalle.referenciaCliente,		      "
                + "        tblDctosOrdenesDetalle.comentario,			      "
                + "        tblDctosOrdenesDetalle.fechaEntrega,			      "
                + "        tblDctosOrdenesDetalle.idEstadoTx,			      "
                + "        tblDctosOrdenesDetalle.nombreUnidadMedida,		      "
                + "        tmpPlu.vrCosto AS vrCostoActual,			      "
                + "        (tblDctosOrdenesDetalle.cantidad *			      "
                + "        tblDctosOrdenesDetalle.vrCosto ) 		              "
                + "                                     AS vrCostoConIva,	      "
                + "        tmpPlu.existencia,				              "
                + "        tmpPlu.nombreCategoria,				      "
                + "        tmpPlu.nombreMarca,					      "
                + "        tmpPlu.nombreLinea,  			              "
                + "        tmpPlu.factorDespacho,                                     "
                + "        (tblDctosOrdenesDetalle.cantidad *			      "
                + "                      tblDctosOrdenesDetalle.vrImpoconsumo)        "
                + "                                           AS vrImpoconsumo,       "
                + "        tblDctosOrdenesDetalle.unidadVenta,                        "                                
                + "       tmpFIC.referencia                                           "
                + "FROM   tblDctosOrdenesDetalle ,				      "
                + "( SELECT tblDctosOrdenes.idLocal,                                  "
                + "       tblDctosOrdenes.idTipoOrden,                                "
                + "       tblDctosOrdenes.idOrden,                                    "
                + "       tblPlusFicha.referencia                                     "
                + "FROM   tblDctosOrdenes                                             "
                + "INNER JOIN tblPlusFicha                                            "
                + "ON tblDctosOrdenes.idFicha =                                       "
                + "            tblPlusFicha.idFicha                                   "
                + "GROUP BY tblDctosOrdenes.idLocal,                                  "
                + "         tblDctosOrdenes.idTipoOrden,                              "
                + "         tblDctosOrdenes.idOrden,                                  "
                + "         tblPlusFicha.referencia) AS tmpFIC,                       "
                + "       (SELECT  tblPlusInventario.idLocal,			      "
                + "                tblPlusInventario.idPlu,			      "
                + "                tblPlusInventario.existencia,		      "
                + "                tblCategorias.nombreCategoria,		      "
                + "                tblMarcas.nombreMarca,			      "
                + "                tblLineas.nombreLinea,			      "
                + "                tblPlus.vrCosto,                                   "
                + "                tblPlus.factorDespacho			      "
                + "        FROM    tblPlus					      "
                + "        INNER JOIN tblMarcas					      "
                + "        ON tblPlus.idMarca       =                                 "
                + "                   tblMarcas.idMarca				      "
                + "        INNER JOIN tblCategorias				      "
                + "        ON tblPlus.idLinea       =				      "
                + "               tblCategorias.idLinea				      "
                + "        AND tblPlus.idCategoria  =			        "
                + "           tblCategorias.IdCategoria			        "
                + "        INNER JOIN tblLineas				        "
                + "        ON tblCategorias.idLinea =			        "
                + "                   tblLineas.idLinea			        "
                + "        INNER JOIN tblPlusInventario			        "
                + "        ON tblPlus.idPlu         =			        "
                + "                     tblPlusInventario.idPlu                 "
                + "        WHERE tblPlusInventario.idLocal =                    "
                + getIdLocal() + "                                              "
                + "        AND   tblPlusInventario.idBodega=                    "
                + getIdBodega() + " ) AS tmpPlu			                "
                + "WHERE EXISTS ( SELECT tblDctosOrdenes.*		        "
                + "               FROM tblDctosOrdenes			        "
                + "               WHERE tblDctosOrdenes.idLocal     =	        "
                + "                       tblDctosOrdenesDetalle.idLocal        "
                + "               AND   tblDctosOrdenes.idTipoOrden =           "
                + "                   tblDctosOrdenesDetalle.idTipoOrden        "
                + "               AND   tblDctosOrdenes.idOrden     =           "
                + "                       tblDctosOrdenesDetalle.idOrden        "
                + "               AND   tblDctosOrdenes.idLocal     =           "
                + getIdLocal() + "                                              "
                + "               AND   tblDctosOrdenes.idTipoOrden =           "
                + getIdTipoOrden() + "                                          "
                + "               AND   tblDctosOrdenes.idLog       =           "
                + getIdLog() + "  )                                             "
                + "AND  tmpPlu.idPlu    = tblDctosOrdenesDetalle.idPlu          "
                + "AND  tmpPlu.idLocal  = tblDctosOrdenesDetalle.idLocal        "
                + "AND  tmpFIC.idLocal  =                                       "
                + "                    tblDctosOrdenesDetalle.idLocalOrigen     "
                + "AND  tmpFIC.idTipoOrden                                      "
                + "                  = tblDctosOrdenesDetalle.idTipoOrdenOrigen "
                + "AND  tmpFIC.idOrden                                          " 
                + "                  = tblDctosOrdenesDetalle.idOrdenOrigen     "
                + "ORDER BY tblDctosOrdenesDetalle.itemPadre,                   "
                + "         tblDctosOrdenesDetalle.item                         ";

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
                fachadaBean.setIdOrden(rs.getInt("idOrden"));
                fachadaBean.setIdOrdenOrigen(rs.getInt("idOrdenOrigen"));
                fachadaBean.setIdPlu(rs.getInt("idPlu"));
                fachadaBean.setStrIdReferencia(rs.getString("strIdReferencia"));
                fachadaBean.setNombrePlu(rs.getString("nombrePlu"));
                fachadaBean.setIdLocalSugerido(rs.getInt("idLocalSugerido"));
                fachadaBean.setIdBodegaSugerido(
                        rs.getString("idBodegaSugerido"));
                fachadaBean.setIdTipo(rs.getInt("idTipo"));
                fachadaBean.setItem(rs.getInt("item"));
                fachadaBean.setItemPadre(rs.getInt("itemPadre"));
                fachadaBean.setItemOrden(rs.getInt("itemOrden"));
                fachadaBean.setCantidad(rs.getDouble("cantidad"));
                fachadaBean.setCantidadPendiente(rs.getDouble("cantidadPendiente"));                
                fachadaBean.setPesoPendiente(rs.getDouble("pesoPendiente"));
                fachadaBean.setVrCosto(rs.getDouble("vrCosto"));
                fachadaBean.setVrVentaUnitario(rs.getDouble("vrVentaUnitario"));
                fachadaBean.setVrVentaOriginal(rs.getDouble("vrVentaOriginal"));
                fachadaBean.setCantidadArticulos(1);
                fachadaBean.setPorcentajeDscto(rs.getDouble("porcentajeDscto"));
                fachadaBean.setPorcentajeIva(rs.getDouble("porcentajeIva"));
                fachadaBean.setVrVentaSinIva(rs.getDouble("vrVentaSinIva"));
                fachadaBean.setVrVentaConIva(rs.getDouble("vrVentaConIva"));
                fachadaBean.setVrVentaSinDscto(rs.getDouble("vrVentaSinDscto"));
                fachadaBean.setVrIvaVenta(rs.getDouble("vrIvaVenta"));
                fachadaBean.setPesoTeoricoTotal(
                        rs.getDouble("pesoTeoricoTotal"));
                fachadaBean.setMarcaArteCliente(
                        rs.getString("marcaArteCliente"));
                fachadaBean.setReferenciaCliente(
                        rs.getString("referenciaCliente"));
                fachadaBean.setComentario(rs.getString("comentario"));
                fachadaBean.setFechaEntrega(rs.getString("fechaEntrega"));
                fachadaBean.setIdEstadoTx(rs.getInt("idEstadoTx"));
                fachadaBean.setNombreUnidadMedida(
                        rs.getString("nombreUnidadMedida"));
                fachadaBean.setVrCostoActual(rs.getDouble("vrCostoActual"));
                fachadaBean.setVrCostoConIva(rs.getDouble("vrCostoConIva"));
                fachadaBean.setExistencia(rs.getDouble("existencia"));
                fachadaBean.setNombreCategoria(rs.getString("nombreCategoria"));
                fachadaBean.setNombreMarca(rs.getString("nombreMarca"));
                fachadaBean.setNombreLinea(rs.getString("nombreLinea"));
                fachadaBean.setFactorDespacho(rs.getString("factorDespacho"));
                fachadaBean.setVrImpoconsumo(rs.getDouble("vrImpoconsumo"));
                fachadaBean.setUnidadVenta(rs.getDouble("unidadVenta"));
                fachadaBean.setReferencia(rs.getString("referencia"));

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

    // listaOrden
    public Vector listaOrden() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        // Variable de fachada de los datos
        FachadaDctoOrdenDetalleBean fachadaBean;

        Connection connection = null;

        //
        String selectStr =
                "SELECT tblDctosOrdenesDetalle.idLocal,				      "
                + "       tblDctosOrdenesDetalle.idTipoOrden,		              "
                + "       tblDctosOrdenesDetalle.idOrden,       		      "
                + "       tblDctosOrdenesDetalle.idPlu,		                      "
                + "       tblDctosOrdenesDetalle.strIdReferencia,		      "
                + "       tblDctosOrdenesDetalle.nombrePlu,			      "
                + "       tblDctosOrdenesDetalle.idLocalSugerido,		      "
                + "       tblDctosOrdenesDetalle.idBodegaSugerido,		      "
                + "       tblDctosOrdenesDetalle.idTipo,            		      "
                + "       tblDctosOrdenesDetalle.item,			              "
                + "       tblDctosOrdenesDetalle.itemPadre,			      "
                + "       tblDctosOrdenesDetalle.cantidad,			      "
                + "       tblDctosOrdenesDetalle.vrCosto,		   	      "
                + "       tblDctosOrdenesDetalle.vrVentaUnitario,	    	      "
                + "       tblDctosOrdenesDetalle.vrVentaOriginal,		      "
                + "       tblDctosOrdenesDetalle.porcentajeDscto,		      "
                + "       tblDctosOrdenesDetalle.porcentajeIva,			      "
                + "       (tblDctosOrdenesDetalle.cantidad          /		      "
                + "        tblDctosOrdenesDetalle.unidadVenta       *		      "
                + "       (tblDctosOrdenesDetalle.vrVentaUnitario   -		      "
                + "        tblDctosOrdenesDetalle.vrImpoconsumo)    /		      "
                + "    ( 1 + ( tblDctosOrdenesDetalle.porcentajeIva /  100 ) ))       "
                + "                                           AS vrVentaSinIva,       "
                + "           (tblDctosOrdenesDetalle.cantidad      / 		      "
                + "        tblDctosOrdenesDetalle.unidadVenta       *		      "
                + "                   tblDctosOrdenesDetalle.vrVentaUnitario )        "
                + "                                          AS vrVentaConIva,        "
                + "      ((tblDctosOrdenesDetalle.cantidad          /		      "
                + "        tblDctosOrdenesDetalle.unidadVenta       *		      "
                + "       (tblDctosOrdenesDetalle.vrVentaUnitario   -		      "
                + "        tblDctosOrdenesDetalle.vrImpoconsumo)    /		      "
                + "  (( 1 + ( tblDctosOrdenesDetalle.porcentajeIva /  100 ))) *       "
                + "      ( 1 - ( tblDctosOrdenesDetalle.vrDsctoPie /  100 ) ) *       "
                + "  ( 1 - ( tblDctosOrdenesDetalle.porcentajeDscto /  100 ))))       "
                + "                                        AS vrVentaSinDscto,        "
                + "       (tblDctosOrdenesDetalle.cantidad          /		      "
                + "        tblDctosOrdenesDetalle.unidadVenta       *		      "
                + "       (tblDctosOrdenesDetalle.vrVentaUnitario   -		      "
                + "        tblDctosOrdenesDetalle.vrImpoconsumo)    *		      "
                + "   ( 1 - ( tblDctosOrdenesDetalle.vrDsctoPie /  100 ) )   *        "
                + "   ( 1 - ( tblDctosOrdenesDetalle.porcentajeDscto /100))) -        "
                + "   (tblDctosOrdenesDetalle.cantidad          /		      "
                + "        tblDctosOrdenesDetalle.unidadVenta   *     	      "
                + "   (tblDctosOrdenesDetalle.vrVentaUnitario   -		      "
                + "    tblDctosOrdenesDetalle.vrImpoconsumo)    *		      "
                + "    ( 1 - ( tblDctosOrdenesDetalle.vrDsctoPie /  100 ) ) *         "
                + "    ( 1 -(tblDctosOrdenesDetalle.porcentajeDscto/ 100 )) /         "
                + "    ( 1 + ( tblDctosOrdenesDetalle.porcentajeIva /  100 ) ))       "
                + "                                              AS vrIvaVenta,       "
                + "       (tblDctosOrdenesDetalle.cantidad        *		      "
                + "        tblDctosOrdenesDetalle.pesoTeorico )			      "
                + "                                   AS pesoTeoricoTotal,	      "
                + "        tblDctosOrdenesDetalle.marcaArteCliente,		      "
                + "        tblDctosOrdenesDetalle.referenciaCliente,		      "
                + "        tblDctosOrdenesDetalle.comentario,			      "
                + "        tblDctosOrdenesDetalle.fechaEntrega,			      "
                + "        tblDctosOrdenesDetalle.idEstadoTx,			      "
                + "        tblDctosOrdenesDetalle.nombreUnidadMedida,		      "
                + "        tmpPlu.vrCosto AS vrCostoActual,			      "
                + "        (tblDctosOrdenesDetalle.cantidad /			      "
                + "        tblDctosOrdenesDetalle.unidadVenta *                       "
                + "        tblDctosOrdenesDetalle.vrCosto ) 		              "
                + "                                     AS vrCostoConIva,	      "
                + "        tblDctosOrdenesDetalle.idLocalOrigen,                      "
                + "        tblDctosOrdenesDetalle.idTipoOrdenOrigen,                  "
                + "        tblDctosOrdenesDetalle.idOrdenOrigen,                      "
                + "        tmpPlu.existencia,				              "
                + "        tmpPlu.nombreCategoria,				      "
                + "        tmpPlu.nombreMarca,					      "
                + "        tmpPlu.nombreLinea,  			              "
                + "        tmpPlu.factorDespacho,                                     "
                + "        (tblDctosOrdenesDetalle.cantidad         /                 "
                + "        tblDctosOrdenesDetalle.unidadVenta       *		      "
                + "                      tblDctosOrdenesDetalle.vrImpoconsumo)        "
                + "                                           AS vrImpoconsumo,       "
                + "        tblDctosOrdenesDetalle.unidadVenta,                        "
                + "        ( SELECT TOP 1                                             "
                + "                 tblPlusFicha.referencia                           "
                + "          FROM   tblDctosOrdenes                                   "
                + "          INNER JOIN tblPlusFicha                                  "
                + "          ON tblDctosOrdenes.idFicha =                             "
                + "                  tblPlusFicha.idFicha                             "
                + "               AND   tblDctosOrdenes.idLocal     =                 "
                + getIdLocal() + "                                                    "
                + "               AND   tblDctosOrdenes.idTipoOrden =                 "
                + getIdTipoOrden() + "                                                "
                + "               AND   tblDctosOrdenes.idLog       =                 "
                + getIdLog() + "    )  AS referencia                                  "
                + "FROM   tblDctosOrdenesDetalle ,				      "
                + "       (SELECT  tblPlusInventario.idLocal,			      "
                + "                tblPlusInventario.idPlu,			      "
                + "                tblPlusInventario.existencia,		      "
                + "                tblCategorias.nombreCategoria,		      "
                + "                tblMarcas.nombreMarca,			      "
                + "                tblLineas.nombreLinea,			      "
                + "                tblPlus.vrCosto,                                   "
                + "                tblPlus.factorDespacho			      "
                + "        FROM    tblPlus					      "
                + "        INNER JOIN tblMarcas					      "
                + "        ON tblPlus.idMarca       =                                 "
                + "                   tblMarcas.idMarca				      "
                + "        INNER JOIN tblCategorias				      "
                + "        ON tblPlus.idLinea       =				      "
                + "               tblCategorias.idLinea				      "
                + "        AND tblPlus.idCategoria  =				      "
                + "           tblCategorias.IdCategoria				      "
                + "        INNER JOIN tblLineas					      "
                + "        ON tblCategorias.idLinea =				      "
                + "                   tblLineas.idLinea				      "
                + "        INNER JOIN tblPlusInventario				      "
                + "        ON tblPlus.idPlu         =				      "
                + "                     tblPlusInventario.idPlu                       "
                + "        WHERE tblPlusInventario.idLocal =                          "
                + getIdLocal() + "                                                    "
                + "        AND   tblPlusInventario.idBodega=                          "
                + getIdBodega() + " ) AS tmpPlu			                      "
                + "WHERE EXISTS ( SELECT tblDctosOrdenes.*			      "
                + "               FROM tblDctosOrdenes				      "
                + "               WHERE tblDctosOrdenes.idLocal     =		      "
                + "                       tblDctosOrdenesDetalle.idLocal              "
                + "               AND   tblDctosOrdenes.idTipoOrden =                 "
                + "                   tblDctosOrdenesDetalle.idTipoOrden        "
                + "               AND   tblDctosOrdenes.idOrden     =           "
                + "                       tblDctosOrdenesDetalle.idOrden        "
                + "               AND   tblDctosOrdenes.idLocal     =           "
                + getIdLocal() + "                                              "
                + "               AND   tblDctosOrdenes.idTipoOrden =           "
                + getIdTipoOrden() + "                                          "
                + "               AND   tblDctosOrdenes.idLog       =           "
                + getIdLog() + "    )                                           "
                + "AND  tmpPlu.idPlu    = tblDctosOrdenesDetalle.idPlu          "
                + "AND  tmpPlu.idLocal  = tblDctosOrdenesDetalle.idLocal        "
                + "ORDER BY tblDctosOrdenesDetalle.itemPadre,                   "
                + "         tblDctosOrdenesDetalle.item                         ";

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
                fachadaBean.setIdOrden(rs.getInt("idOrden"));
                fachadaBean.setIdPlu(rs.getInt("idPlu"));
                fachadaBean.setStrIdReferencia(rs.getString("strIdReferencia"));
                fachadaBean.setNombrePlu(rs.getString("nombrePlu"));
                fachadaBean.setIdLocalSugerido(rs.getInt("idLocalSugerido"));
                fachadaBean.setIdBodegaSugerido(
                        rs.getString("idBodegaSugerido"));
                fachadaBean.setIdTipo(rs.getInt("idTipo"));
                fachadaBean.setItem(rs.getInt("item"));
                fachadaBean.setItemPadre(rs.getInt("itemPadre"));
                fachadaBean.setCantidad(rs.getDouble("cantidad"));
                fachadaBean.setVrCosto(rs.getDouble("vrCosto"));
                fachadaBean.setVrVentaUnitario(rs.getDouble("vrVentaUnitario"));
                fachadaBean.setVrVentaOriginal(rs.getDouble("vrVentaOriginal"));
                fachadaBean.setCantidadArticulos(1);
                fachadaBean.setPorcentajeDscto(rs.getDouble("porcentajeDscto"));
                fachadaBean.setPorcentajeIva(rs.getDouble("porcentajeIva"));
                fachadaBean.setVrVentaSinIva(rs.getDouble("vrVentaSinIva"));
                fachadaBean.setVrVentaConIva(rs.getDouble("vrVentaConIva"));
                fachadaBean.setVrVentaSinDscto(rs.getDouble("vrVentaSinDscto"));
                fachadaBean.setVrIvaVenta(rs.getDouble("vrIvaVenta"));
                fachadaBean.setPesoTeoricoTotal(
                        rs.getDouble("pesoTeoricoTotal"));
                fachadaBean.setMarcaArteCliente(
                        rs.getString("marcaArteCliente"));
                fachadaBean.setReferenciaCliente(
                        rs.getString("referenciaCliente"));
                fachadaBean.setComentario(rs.getString("comentario"));
                fachadaBean.setFechaEntrega(rs.getString("fechaEntrega"));
                fachadaBean.setIdEstadoTx(rs.getInt("idEstadoTx"));
                fachadaBean.setNombreUnidadMedida(
                        rs.getString("nombreUnidadMedida"));
                fachadaBean.setVrCostoActual(rs.getDouble("vrCostoActual"));
                fachadaBean.setVrCostoConIva(rs.getDouble("vrCostoConIva"));
                fachadaBean.setIdLocalOrigen(rs.getInt("idLocalOrigen"));
                fachadaBean.setIdTipoOrdenOrigen(rs.getInt("idTipoOrdenOrigen"));
                fachadaBean.setIdOrdenOrigen(rs.getInt("idOrdenOrigen"));
                fachadaBean.setExistencia(rs.getDouble("existencia"));
                fachadaBean.setNombreCategoria(rs.getString("nombreCategoria"));
                fachadaBean.setNombreMarca(rs.getString("nombreMarca"));
                fachadaBean.setNombreLinea(rs.getString("nombreLinea"));
                fachadaBean.setFactorDespacho(rs.getString("factorDespacho"));
                fachadaBean.setVrImpoconsumo(rs.getDouble("vrImpoconsumo"));
                fachadaBean.setUnidadVenta(rs.getDouble("unidadVenta"));
                fachadaBean.setReferencia(rs.getString("referencia"));

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

    // listaTraslado
    public Vector listaTraslado() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        // Variable de fachada de los datos
        FachadaDctoOrdenDetalleBean fachadaBean;

        Connection connection = null;

        //
        String selectStr =
                "SELECT tblDctosOrdenesDetalle.idLocal,				      "
                + "       tblDctosOrdenesDetalle.idTipoOrden,		              "
                + "       tblDctosOrdenesDetalle.idOrden,       		      "
                + "       tblDctosOrdenesDetalle.idPlu,		                      "
                + "       tblDctosOrdenesDetalle.strIdReferencia,		      "
                + "       tblDctosOrdenesDetalle.nombrePlu,			      "
                + "       tblDctosOrdenesDetalle.idLocalSugerido,		      "
                + "       tblDctosOrdenesDetalle.idBodegaSugerido,		      "
                + "       tblDctosOrdenesDetalle.idTipo,            		      "
                + "       tblDctosOrdenesDetalle.item,			              "
                + "       tblDctosOrdenesDetalle.itemPadre,			      "
                + "       tblDctosOrdenesDetalle.cantidad,			      "
                + "       tblDctosOrdenesDetalle.vrCosto,		   	      "
                + "       tblDctosOrdenesDetalle.vrVentaUnitario,	    	      "
                + "       tblDctosOrdenesDetalle.vrVentaOriginal,		      "
                + "       tblDctosOrdenesDetalle.porcentajeDscto,		      "
                + "       tblDctosOrdenesDetalle.porcentajeIva,			      "
                + "       (tblDctosOrdenesDetalle.cantidad        *		      "
                + "        tblDctosOrdenesDetalle.pesoTeorico )			      "
                + "                                   AS pesoTeoricoTotal,	      "
                + "        tblDctosOrdenesDetalle.marcaArteCliente,		      "
                + "        tblDctosOrdenesDetalle.referenciaCliente,		      "
                + "        tblDctosOrdenesDetalle.comentario,			      "
                + "        tblDctosOrdenesDetalle.fechaEntrega,			      "
                + "        tblDctosOrdenesDetalle.idEstadoTx,			      "
                + "        tblDctosOrdenesDetalle.nombreUnidadMedida,		      "
                + "        tmpPlu.vrCosto AS vrCostoActual,			      "
                + "        (tblDctosOrdenesDetalle.cantidad) 		              "
                + "                                     AS vrCostoConIva,	      "
                + "        tblDctosOrdenesDetalle.idLocalOrigen,                      "
                + "        tblDctosOrdenesDetalle.idTipoOrdenOrigen,                  "
                + "        tblDctosOrdenesDetalle.idOrdenOrigen,                      "
                + "        tmpPlu.existencia,				              "
                + "        tmpPlu.nombreCategoria,				      "
                + "        tmpPlu.nombreMarca,					      "
                + "        tmpPlu.nombreLinea,  			              "
                + "        tmpPlu.factorDespacho,                                     "
                + "        (tblDctosOrdenesDetalle.cantidad         *                 "
                + "                      tblDctosOrdenesDetalle.vrImpoconsumo)        "
                + "                                           AS vrImpoconsumo,       "
                + "        tblDctosOrdenesDetalle.unidadVenta,                        "
                + "        ( SELECT TOP 1                                             "
                + "                 tblPlusFicha.referencia                           "
                + "          FROM   tblDctosOrdenes                                   "
                + "          INNER JOIN tblPlusFicha                                  "
                + "          ON tblDctosOrdenes.idFicha =                             "
                + "                  tblPlusFicha.idFicha                             "
                + "               AND   tblDctosOrdenes.idLocal     =                 "
                + getIdLocal() + "                                                    "
                + "               AND   tblDctosOrdenes.idTipoOrden =                 "
                + getIdTipoOrden() + "                                                "
                + "               AND   tblDctosOrdenes.idLog       =                 "
                + getIdLog() + "    )  AS referencia                                  "
                + "FROM   tblDctosOrdenesDetalle ,				      "
                + "       (SELECT  tblPlusInventario.idLocal,			      "
                + "                tblPlusInventario.idPlu,			      "
                + "                tblPlusInventario.existencia,		      "
                + "                tblCategorias.nombreCategoria,		      "
                + "                tblMarcas.nombreMarca,			      "
                + "                tblLineas.nombreLinea,			      "
                + "                tblPlus.vrCosto,                                   "
                + "                tblPlus.factorDespacho			      "
                + "        FROM    tblPlus					      "
                + "        INNER JOIN tblMarcas					      "
                + "        ON tblPlus.idMarca       =                                 "
                + "                   tblMarcas.idMarca				      "
                + "        INNER JOIN tblCategorias				      "
                + "        ON tblPlus.idLinea       =				      "
                + "               tblCategorias.idLinea				      "
                + "        AND tblPlus.idCategoria  =				      "
                + "           tblCategorias.IdCategoria				      "
                + "        INNER JOIN tblLineas					      "
                + "        ON tblCategorias.idLinea =				      "
                + "                   tblLineas.idLinea				      "
                + "        INNER JOIN tblPlusInventario				      "
                + "        ON tblPlus.idPlu         =				      "
                + "                     tblPlusInventario.idPlu                       "
                + "        WHERE tblPlusInventario.idLocal =                          "
                + getIdLocal() + "                                                    "
                + "        AND   tblPlusInventario.idBodega=                          "
                + getIdBodega() + " ) AS tmpPlu			                      "
                + "WHERE EXISTS ( SELECT tblDctosOrdenes.*			      "
                + "               FROM tblDctosOrdenes				      "
                + "               WHERE tblDctosOrdenes.idLocal     =		      "
                + "                       tblDctosOrdenesDetalle.idLocal              "
                + "               AND   tblDctosOrdenes.idTipoOrden =                 "
                + "                   tblDctosOrdenesDetalle.idTipoOrden        "
                + "               AND   tblDctosOrdenes.idOrden     =           "
                + "                       tblDctosOrdenesDetalle.idOrden        "
                + "               AND   tblDctosOrdenes.idLocal     =           "
                + getIdLocal() + "                                              "
                + "               AND   tblDctosOrdenes.idTipoOrden =           "
                + getIdTipoOrden() + "                                          "
                + "               AND   tblDctosOrdenes.idLog       =           "
                + getIdLog() + "    )                                           "
                + "AND  tmpPlu.idPlu    = tblDctosOrdenesDetalle.idPlu          "
                + "AND  tmpPlu.idLocal  = tblDctosOrdenesDetalle.idLocal        "
                + "ORDER BY tblDctosOrdenesDetalle.itemPadre,                   "
                + "         tblDctosOrdenesDetalle.item                         ";

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
                fachadaBean.setIdOrden(rs.getInt("idOrden"));
                fachadaBean.setIdPlu(rs.getInt("idPlu"));
                fachadaBean.setStrIdReferencia(rs.getString("strIdReferencia"));
                fachadaBean.setNombrePlu(rs.getString("nombrePlu"));
                fachadaBean.setIdLocalSugerido(rs.getInt("idLocalSugerido"));
                fachadaBean.setIdBodegaSugerido(
                        rs.getString("idBodegaSugerido"));
                fachadaBean.setIdTipo(rs.getInt("idTipo"));
                fachadaBean.setItem(rs.getInt("item"));
                fachadaBean.setItemPadre(rs.getInt("itemPadre"));
                fachadaBean.setCantidad(rs.getDouble("cantidad"));
                fachadaBean.setVrCosto(rs.getDouble("vrCosto"));
                fachadaBean.setVrVentaUnitario(rs.getDouble("vrVentaUnitario"));
                fachadaBean.setVrVentaOriginal(rs.getDouble("vrVentaOriginal"));
                fachadaBean.setCantidadArticulos(1);
                fachadaBean.setPorcentajeDscto(rs.getDouble("porcentajeDscto"));
                fachadaBean.setPorcentajeIva(rs.getDouble("porcentajeIva"));
                fachadaBean.setPesoTeoricoTotal(
                        rs.getDouble("pesoTeoricoTotal"));
                fachadaBean.setMarcaArteCliente(
                        rs.getString("marcaArteCliente"));
                fachadaBean.setReferenciaCliente(
                        rs.getString("referenciaCliente"));
                fachadaBean.setComentario(rs.getString("comentario"));
                fachadaBean.setFechaEntrega(rs.getString("fechaEntrega"));
                fachadaBean.setIdEstadoTx(rs.getInt("idEstadoTx"));
                fachadaBean.setNombreUnidadMedida(
                        rs.getString("nombreUnidadMedida"));
                fachadaBean.setVrCostoActual(rs.getDouble("vrCostoActual"));
                fachadaBean.setVrCostoConIva(rs.getDouble("vrCostoConIva"));
                fachadaBean.setIdLocalOrigen(rs.getInt("idLocalOrigen"));
                fachadaBean.setIdTipoOrdenOrigen(rs.getInt("idTipoOrdenOrigen"));
                fachadaBean.setIdOrdenOrigen(rs.getInt("idOrdenOrigen"));
                fachadaBean.setExistencia(rs.getDouble("existencia"));
                fachadaBean.setNombreCategoria(rs.getString("nombreCategoria"));
                fachadaBean.setNombreMarca(rs.getString("nombreMarca"));
                fachadaBean.setNombreLinea(rs.getString("nombreLinea"));
                fachadaBean.setFactorDespacho(rs.getString("factorDespacho"));
                fachadaBean.setVrImpoconsumo(rs.getDouble("vrImpoconsumo"));
                fachadaBean.setUnidadVenta(rs.getDouble("unidadVenta"));
                fachadaBean.setReferencia(rs.getString("referencia"));

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

    // liquidaOrden
    public Vector liquidaOrden() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        // Variable de fachada de los datos
        FachadaDctoOrdenDetalleBean fachadaBean;

        Connection connection = null;

        String selectStr =
                "SELECT tblDctosOrdenes.idLog,                 			          "
                + "       tblDctosOrdenes.idCliente,                      	          "
                + "       tblDctosOrdenes.idUsuario,                        	          "
                + "       tblDctosOrdenes.fechaOrden,                      	          "
                + "       tblDctosOrdenes.idEstadoTx,                      	          "
                + "       tmpDET.*						          "
                + "FROM tblDctosOrdenes,						  "
                + "    (								  "
                + "SELECT tblDctosOrdenesDetalle.idLocal,              		          "
                + "       tblDctosOrdenesDetalle.idTipoOrden,          		          "
                + "       tblDctosOrdenesDetalle.idOrden,  			          "
                + "       tblDctosOrdenes.idLog,  				          "
                + "       COUNT(*) AS cantidadArticulos,				  "
                + "       SUM(tblDctosOrdenesDetalle.cantidad      / 		          "
                + "           tblDctosOrdenesDetalle.unidadVenta   * 		          "
                + "          tblDctosOrdenesDetalle.vrVentaUnitario )  		          "
                + "                                 AS vrVentaConIva,  		          "
                + "       SUM(tblDctosOrdenesDetalle.cantidad      /      	          "
                + "           tblDctosOrdenesDetalle.unidadVenta   * 		          "
                + "        (tblDctosOrdenesDetalle.vrVentaUnitario   -		          "
                + "        tblDctosOrdenesDetalle.vrImpoconsumo)    /		          "
                + "       ( 1 + ( tblDctosOrdenesDetalle.porcentajeIva /  100 ) ))        "
                + "                                              AS vrVentaSinIva,        "
                + "       SUM(tblDctosOrdenesDetalle.pesoTeorico *             	          "
                + "                               tblDctosOrdenesDetalle.cantidad)        "
                + "                                           AS pesoTeoricoTotal,        "
                + "       SUM(tblDctosOrdenesDetalle.vrCosto *  			  "
                + "                       tblDctosOrdenesDetalle.cantidad)  	          "
                + "                                              AS vrCostoConIva,        "
                + "       SUM( (tblDctosOrdenesDetalle.cantidad / 		          "
                + "           tblDctosOrdenesDetalle.unidadVenta   * 		          "
                + "                            (tblDctosOrdenesDetalle.vrCosto   -        "
                + "                      tblDctosOrdenesDetalle.vrImpoconsumo) ) /        "
                + "             (1 + (tblDctosOrdenesDetalle.porcentajeIva/100)))         "
                + "                                              AS vrCostoSinIva,        "
                + "       SUM((tblDctosOrdenesDetalle.cantidad    /                       "
                + "           tblDctosOrdenesDetalle.unidadVenta   * 		          "
                + "        (tblDctosOrdenesDetalle.vrVentaUnitario   -		          "
                + "        tblDctosOrdenesDetalle.vrImpoconsumo)    /		          "
                + "       (( 1 + ( tblDctosOrdenesDetalle.porcentajeIva /  100 ))) *      "
                + "        ( 1 - ( tblDctosOrdenesDetalle.vrDsctoPie /  100 ) ) *	  "
                + "        ( 1 - ( tblDctosOrdenesDetalle.porcentajeDscto /  100 )) ))    "
                + "                                                AS vrVentaSinDscto,    "
                + "       SUM(tblDctosOrdenesDetalle.cantidad      /    	          "
                + "           tblDctosOrdenesDetalle.unidadVenta   * 		          "
                + "        (tblDctosOrdenesDetalle.vrVentaUnitario   -		          "
                + "        tblDctosOrdenesDetalle.vrImpoconsumo)    *		          "
                + "       ( 1 - ( tblDctosOrdenesDetalle.vrDsctoPie /  100 ) )      *     "
                + "       ( 1 - ( tblDctosOrdenesDetalle.porcentajeDscto /  100 ) ) ) -   "
                + "       SUM(tblDctosOrdenesDetalle.cantidad      /                      "
                + "           tblDctosOrdenesDetalle.unidadVenta   * 		          "
                + "        (tblDctosOrdenesDetalle.vrVentaUnitario   -		          "
                + "        tblDctosOrdenesDetalle.vrImpoconsumo)    *		          "
                + "       ( 1 - ( tblDctosOrdenesDetalle.vrDsctoPie /  100 ) )      *     "
                + "       ( 1 - ( tblDctosOrdenesDetalle.porcentajeDscto /  100 ) ) /     "
                + "       ( 1 + ( tblDctosOrdenesDetalle.porcentajeIva /  100 ) ))        "
                + "                                                     AS vrIvaVenta,    "
                + "       SUM(tblDctosOrdenesDetalle.cantidad / 			  "
                + "           tblDctosOrdenesDetalle.unidadVenta   * 		          "
                + "        tblDctosOrdenesDetalle.vrImpoconsumo) AS vrImpoconsumo,        "
                + "       SUM((tblDctosOrdenesDetalle.cantidad        *                   "
                + "           (tblDctosOrdenesDetalle.vrCosto         -                   "
                + "            tblDctosOrdenesDetalle.vrImpoconsumo)) -                   "
                + "           (tblDctosOrdenesDetalle.cantidad     /                      "
                + "           tblDctosOrdenesDetalle.unidadVenta   * 		          "
                + "           (tblDctosOrdenesDetalle.vrCosto         -                   "
                + "            tblDctosOrdenesDetalle.vrImpoconsumo)) /                   "
                + "           ( 1 + tblDctosOrdenesDetalle.porcentajeIva / 100))          "
                + "                                                     AS vrIvaCosto     "
                + "FROM   tblDctosOrdenes						  "
                + "INNER JOIN tblDctosOrdenesDetalle				          "
                + "ON  tblDctosOrdenes.IDLOCAL     = 				          "
                + "             tblDctosOrdenesDetalle.IDLOCAL			          "
                + "AND tblDctosOrdenes.IDTIPOORDEN = 				          "
                + "         tblDctosOrdenesDetalle.IDTIPOORDEN			          "
                + "AND tblDctosOrdenes.IDORDEN     = 				          "
                + "             tblDctosOrdenesDetalle.IDORDEN			          "
                + "GROUP BY tblDctosOrdenesDetalle.idLocal,              		  "
                + "         tblDctosOrdenesDetalle.idTipoOrden,          		  "
                + "         tblDctosOrdenesDetalle.idOrden,          		          "
                + "         tblDctosOrdenes.idLog) AS tmpDET     			  "
                + "WHERE tblDctosOrdenes.idLocal     =                      	          "
                + "                                tmpDET.idLocal                         "
                + "AND   tblDctosOrdenes.idTipoOrden =                     	          "
                + "                                tmpDET.idTipoOrden      	          "
                + "AND   tblDctosOrdenes.idOrden     =                      	          "
                + "                                tmpDET.idOrden          	          "
                + "AND   tmpDET.idLocal     =      				          "
                + getIdLocal() + "                                                        "
                + "AND   tmpDET.idTipoOrden =                       		          "
                + getIdTipoOrden() + "                                                    "
                + "AND   tmpDET.idLog       =                       		          "
                + getIdLog();


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
                fachadaBean.setIdTipoOrden(rs.getInt("IdTipoOrden"));
                fachadaBean.setIdOrden(rs.getInt("IdOrden"));
                fachadaBean.setIdLog(rs.getInt("idLog"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setIdUsuario(rs.getDouble("idUsuario"));
                fachadaBean.setFechaOrden(
                        rs.getString("fechaOrden"));
                fachadaBean.setIdEstadoTx(rs.getInt("idEstadoTx"));
                fachadaBean.setCantidadArticulos(
                        rs.getInt("cantidadArticulos"));
                fachadaBean.setVrVentaSinIva(rs.getDouble("vrVentaSinIva"));
                fachadaBean.setVrVentaConIva(rs.getDouble("vrVentaConIva"));
                fachadaBean.setPesoTeoricoTotal(
                        rs.getDouble("pesoTeoricoTotal"));
                fachadaBean.setVrCostoConIva(rs.getDouble("vrCostoConIva"));
                fachadaBean.setVrCostoSinIva(rs.getDouble("vrCostoSinIva"));
                fachadaBean.setVrVentaSinDscto(rs.getDouble("vrVentaSinDscto"));
                fachadaBean.setVrIvaVenta(rs.getDouble("vrIvaVenta"));
                fachadaBean.setVrImpoconsumo(rs.getDouble("vrImpoconsumo"));
                fachadaBean.setVrIvaCosto(rs.getDouble("vrIvaCosto"));



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

    // liquidaOrdenFCH
    public FachadaDctoOrdenDetalleBean liquidaOrdenFCH() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        // Variable de fachada de los datos
        FachadaDctoOrdenDetalleBean fachadaBean = new FachadaDctoOrdenDetalleBean();

        Connection connection = null;

        String selectStr =
                "SELECT tblDctosOrdenes.idLog,                 			      "
                + "       tblDctosOrdenes.idCliente,                      	      "
                + "       tblDctosOrdenes.idUsuario,                        	      "
                + "       tblDctosOrdenes.fechaOrden,                      	      "
                + "       tblDctosOrdenes.idEstadoTx,                      	      "
                + "       tblDctosOrdenes.observacion,                      	      "
                + "       tmpDET.*						      "
                + "FROM tblDctosOrdenes,					      "
                + "    (							      "
                + "SELECT tblDctosOrdenesDetalle.idLocal,              		      "
                + "       tblDctosOrdenesDetalle.idTipoOrden,          		      "
                + "       tblDctosOrdenesDetalle.idOrden,  			      "
                + "       tblDctosOrdenes.idLog,  				      "
                + "       COUNT(*) AS cantidadArticulos,			      "
                + "       SUM(tblDctosOrdenesDetalle.cantidad      / 		      "
                + "           tblDctosOrdenesDetalle.unidadVenta   * 		      "
                + "          tblDctosOrdenesDetalle.vrVentaUnitario )  		      "
                + "                                 AS vrVentaConIva,  		      "
                + "       SUM(tblDctosOrdenesDetalle.cantidad      /    	      "
                + "           tblDctosOrdenesDetalle.unidadVenta   * 		      "
                + "        (tblDctosOrdenesDetalle.vrVentaUnitario   -		      "
                + "        tblDctosOrdenesDetalle.vrImpoconsumo)    /		      "
                + "       ( 1 + ( tblDctosOrdenesDetalle.porcentajeIva /  100 ) ))    "
                + "                                              AS vrVentaSinIva,    "
                + "       SUM(tblDctosOrdenesDetalle.pesoTeorico *             	      "
                + "                               tblDctosOrdenesDetalle.cantidad)    "
                + "                                           AS pesoTeoricoTotal,    "
                + "       SUM(tblDctosOrdenesDetalle.vrCosto *  		      "
                + "                       tblDctosOrdenesDetalle.cantidad)  	      "
                + "                                              AS vrCostoConIva,    "
                + "       SUM( (tblDctosOrdenesDetalle.cantidad * 		      "
                + "                           ( tblDctosOrdenesDetalle.vrCosto -      "
                + "                      tblDctosOrdenesDetalle.vrImpoconsumo )) /    "
                + "             (1 + (tblDctosOrdenesDetalle.porcentajeIva/100)))     "
                + "                                              AS vrCostoSinIva,    "
                + "       SUM((tblDctosOrdenesDetalle.cantidad     /                  "
                + "           tblDctosOrdenesDetalle.unidadVenta   * 		      "
                + "        (tblDctosOrdenesDetalle.vrVentaUnitario   -		      "
                + "        tblDctosOrdenesDetalle.vrImpoconsumo)    /		      "
                + "       (( 1 + ( tblDctosOrdenesDetalle.porcentajeIva /  100 ))) *  "
                + "        ( 1 - ( tblDctosOrdenesDetalle.vrDsctoPie /  100 ) ) *     "
                + "      ( 1 - ( tblDctosOrdenesDetalle.porcentajeDscto /  100 )) ))  "
                + "                                              AS vrVentaSinDscto,  "
                + "       SUM(tblDctosOrdenesDetalle.cantidad      /                  "
                + "           tblDctosOrdenesDetalle.unidadVenta   * 		      "
                + "        (tblDctosOrdenesDetalle.vrVentaUnitario   -		      "
                + "        tblDctosOrdenesDetalle.vrImpoconsumo)    *		      "
                + "       ( 1 - ( tblDctosOrdenesDetalle.vrDsctoPie /  100 ) )      * "
                + "     ( 1 - ( tblDctosOrdenesDetalle.porcentajeDscto /  100 ) ) ) - "
                + "       SUM(tblDctosOrdenesDetalle.cantidad      /    	      "
                + "           tblDctosOrdenesDetalle.unidadVenta   * 		      "
                + "        (tblDctosOrdenesDetalle.vrVentaUnitario   -		      "
                + "        tblDctosOrdenesDetalle.vrImpoconsumo)    *		      "
                + "       ( 1 - ( tblDctosOrdenesDetalle.vrDsctoPie /  100 ) )      * "
                + "       ( 1 - ( tblDctosOrdenesDetalle.porcentajeDscto /  100 ) ) / "
                + "       ( 1 + ( tblDctosOrdenesDetalle.porcentajeIva /  100 ) ))    "
                + "                                                     AS vrIvaVenta,"
                + "       SUM(tblDctosOrdenesDetalle.cantidad      / 		      "
                + "           tblDctosOrdenesDetalle.unidadVenta   * 		      "
                + "        tblDctosOrdenesDetalle.vrImpoconsumo) AS vrImpoconsumo,    "
                + "       SUM((tblDctosOrdenesDetalle.cantidad     /                  "
                + "           tblDctosOrdenesDetalle.unidadVenta   * 		      "
                + "           (tblDctosOrdenesDetalle.vrCosto         -               "
                + "            tblDctosOrdenesDetalle.vrImpoconsumo)) -               "
                + "           (tblDctosOrdenesDetalle.cantidad     /                  "
                + "           tblDctosOrdenesDetalle.unidadVenta   * 		      "
                + "           (tblDctosOrdenesDetalle.vrCosto         -               "
                + "            tblDctosOrdenesDetalle.vrImpoconsumo)) /               "
                + "           ( 1 + tblDctosOrdenesDetalle.porcentajeIva / 100))      "
                + "                                                   AS vrIvaCosto,  "
                + "       SUM(tblDctosOrdenesDetalle.cantidad      /                  "
                + "           tblDctosOrdenesDetalle.unidadVenta   * 		      "
                + "        tblDctosOrdenesDetalle.vrCostoIND) AS vrCostoIND           "
                + "FROM   tblDctosOrdenes					      "
                + "INNER JOIN tblDctosOrdenesDetalle				      "
                + "ON  tblDctosOrdenes.IDLOCAL     = 				      "
                + "             tblDctosOrdenesDetalle.IDLOCAL			      "
                + "AND tblDctosOrdenes.IDTIPOORDEN = 				      "
                + "         tblDctosOrdenesDetalle.IDTIPOORDEN			      "
                + "AND tblDctosOrdenes.IDORDEN     = 				      "
                + "             tblDctosOrdenesDetalle.IDORDEN			      "
                + "GROUP BY tblDctosOrdenesDetalle.idLocal,              	      "
                + "         tblDctosOrdenesDetalle.idTipoOrden,          	      "
                + "         tblDctosOrdenesDetalle.idOrden,          		      "
                + "         tblDctosOrdenes.idLog) AS tmpDET     		      "
                + "WHERE tblDctosOrdenes.idLocal     =                      	      "
                + "                                tmpDET.idLocal                     "
                + "AND   tblDctosOrdenes.idTipoOrden =                     	      "
                + "                                tmpDET.idTipoOrden      	      "
                + "AND   tblDctosOrdenes.idOrden     =                      	      "
                + "                                tmpDET.idOrden          	      "
                + "AND   tmpDET.idLocal     =      				      "
                + getIdLocal() + "                                                    "
                + "AND   tmpDET.idTipoOrden =                       		      "
                + getIdTipoOrden() + "                                                "
                + "AND   tmpDET.idLog       =                       		      "
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
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("IdTipoOrden"));
                fachadaBean.setIdOrden(rs.getInt("IdOrden"));
                fachadaBean.setIdLog(rs.getInt("idLog"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setIdUsuario(rs.getDouble("idUsuario"));
                fachadaBean.setFechaOrden(
                        rs.getString("fechaOrden"));
                fachadaBean.setIdEstadoTx(rs.getInt("idEstadoTx"));
                fachadaBean.setObservacion(rs.getString("observacion"));
                fachadaBean.setCantidadArticulos(
                        rs.getInt("cantidadArticulos"));
                fachadaBean.setVrVentaSinIva(rs.getDouble("vrVentaSinIva"));
                fachadaBean.setVrVentaConIva(rs.getDouble("vrVentaConIva"));
                fachadaBean.setPesoTeoricoTotal(
                        rs.getDouble("pesoTeoricoTotal"));
                fachadaBean.setVrCostoConIva(rs.getDouble("vrCostoConIva"));
                fachadaBean.setVrCostoSinIva(rs.getDouble("vrCostoSinIva"));
                fachadaBean.setVrVentaSinDscto(rs.getDouble("vrVentaSinDscto"));
                fachadaBean.setVrIvaVenta(rs.getDouble("vrIvaVenta"));
                fachadaBean.setVrImpoconsumo(rs.getDouble("vrImpoconsumo"));
                fachadaBean.setVrIvaCosto(rs.getDouble("vrIvaCosto"));
                fachadaBean.setVrCostoIND(rs.getDouble("vrCostoIND"));

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
            return fachadaBean;

        }
    }

    // liquidaOrdenProveedorFCH
    public FachadaDctoOrdenDetalleBean liquidaOrdenProveedorFCH() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        // Variable de fachada de los datos
        FachadaDctoOrdenDetalleBean fachadaBean = new FachadaDctoOrdenDetalleBean();

        Connection connection = null;

        String selectStr =
                " SELECT     tblDctosOrdenesDetalle.IDLOCAL,               "
                + "            tblDctosOrdenesDetalle.IDTIPOORDEN,         "
                + "            tblDctosOrdenesDetalle.IDORDEN,             "
                + "           SUM(tblDctosOrdenesDetalle.cantidad)         "
                + "                                          AS cantidad,  "
                + "           SUM(tblDctosOrdenesDetalle.vrCostoResurtido) "
                + "                                   AS vrCostoResurtido, "
                + "           SUM(tblDctosOrdenesDetalle.vrImpoconsumo)    "
                + "                                      AS vrImpoconsumo, "
                + "           SUM(tblDctosOrdenesDetalle.vrIvaResurtido)   "
                + "                                      AS vrIvaResurtido "
                + " FROM  tblDctosOrdenesDetalle                           "
                + " INNER JOIN tblDctosOrdenes                             "
                + " ON  tblDctosOrdenesDetalle.IDLOCAL     =               "
                + "                                tblDctosOrdenes.IDLOCAL "
                + " AND tblDctosOrdenesDetalle.IDTIPOORDEN =               "
                + "                            tblDctosOrdenes.IDTIPOORDEN "
                + " AND tblDctosOrdenesDetalle.IDORDEN     =               "
                + "                                tblDctosOrdenes.IDORDEN "
                + " WHERE      tblDctosOrdenesDetalle.IDLOCAL =            "
                + getIdLocal() + "                                         "
                + " AND  tblDctosOrdenesDetalle.IDTIPOORDEN   =            "
                + getIdTipoOrden() + "                                     "
                + " AND  tblDctosOrdenes.idLog                =            "
                + getIdLog() + "                                           "
                + " GROUP BY tblDctosOrdenesDetalle.IDLOCAL,               "
                + "         tblDctosOrdenesDetalle.IDTIPOORDEN,            "
                + "         tblDctosOrdenesDetalle.IDORDEN ";

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
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("IdTipoOrden"));
                fachadaBean.setIdOrden(rs.getInt("IdOrden"));
                fachadaBean.setCantidad(rs.getDouble("cantidad"));
                fachadaBean.setVrCostoResurtido(
                        rs.getDouble("vrCostoResurtido"));
                fachadaBean.setVrImpoconsumo(rs.getDouble("vrImpoconsumo"));
                fachadaBean.setVrIvaResurtido(rs.getDouble("vrIvaResurtido"));

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
            return fachadaBean;

        }
    }

    // liquidaOrdenFCH
    public FachadaDctoOrdenDetalleBean liquidaOrdenFCHOrigen() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        // Variable de fachada de los datos
        FachadaDctoOrdenDetalleBean fachadaBean = new FachadaDctoOrdenDetalleBean();

        Connection connection = null;

        String selectStr =
                "SELECT tblDctosOrdenes.idLog,                 			      "
                + "       tblDctosOrdenes.idCliente,                      	      "
                + "       tblDctosOrdenes.idUsuario,                        	      "
                + "       tblDctosOrdenes.fechaOrden,                      	      "
                + "       tblDctosOrdenes.idEstadoTx,                      	      "
                + "       tmpDET.*						      "
                + "FROM tblDctosOrdenes,					      "
                + "    (							      "
                + "SELECT tblDctosOrdenesDetalle.idLocal,              		      "
                + "       tblDctosOrdenesDetalle.idTipoOrden,          		      "
                + "       tblDctosOrdenesDetalle.idOrden,  			      "
                + "       tblDctosOrdenes.idLog,  				      "
                + "       COUNT(*) AS cantidadArticulos,			      "
                + "       SUM(tblDctosOrdenesDetalle.cantidad      * 		      "
                + "          tblDctosOrdenesDetalle.vrVentaUnitario )  		      "
                + "                                 AS vrVentaConIva,  		      "
                + "       SUM(tblDctosOrdenesDetalle.cantidad          *	      "
                + "        (tblDctosOrdenesDetalle.vrVentaUnitario   -		      "
                + "        tblDctosOrdenesDetalle.vrImpoconsumo)    /		      "
                + "       ( 1 + ( tblDctosOrdenesDetalle.porcentajeIva /  100 ) ))    "
                + "                                              AS vrVentaSinIva,    "
                + "       SUM(tblDctosOrdenesDetalle.pesoTeorico *             	      "
                + "                               tblDctosOrdenesDetalle.cantidad)    "
                + "                                           AS pesoTeoricoTotal,    "
                + "       SUM(tblDctosOrdenesDetalle.vrCosto *  		      "
                + "                       tblDctosOrdenesDetalle.cantidad)  	      "
                + "                                              AS vrCostoConIva,    "
                + "       SUM( (tblDctosOrdenesDetalle.cantidad * 		      "
                + "                           ( tblDctosOrdenesDetalle.vrCosto -      "
                + "                      tblDctosOrdenesDetalle.vrImpoconsumo )) /    "
                + "             (1 + (tblDctosOrdenesDetalle.porcentajeIva/100)))     "
                + "                                              AS vrCostoSinIva,    "
                + "       SUM((tblDctosOrdenesDetalle.cantidad          *	      "
                + "        (tblDctosOrdenesDetalle.vrVentaUnitario   -		      "
                + "        tblDctosOrdenesDetalle.vrImpoconsumo)    /		      "
                + "       (( 1 + ( tblDctosOrdenesDetalle.porcentajeIva /  100 ))) *  "
                + "        ( 1 - ( tblDctosOrdenesDetalle.vrDsctoPie /  100 ) ) *     "
                + "      ( 1 - ( tblDctosOrdenesDetalle.porcentajeDscto /  100 )) ))  "
                + "                                              AS vrVentaSinDscto,  "
                + "       SUM(tblDctosOrdenesDetalle.cantidad          *              "
                + "        (tblDctosOrdenesDetalle.vrVentaUnitario   -		      "
                + "        tblDctosOrdenesDetalle.vrImpoconsumo)    *		      "
                + "       ( 1 - ( tblDctosOrdenesDetalle.vrDsctoPie /  100 ) )      * "
                + "     ( 1 - ( tblDctosOrdenesDetalle.porcentajeDscto /  100 ) ) ) - "
                + "       SUM(tblDctosOrdenesDetalle.cantidad          *	      "
                + "        (tblDctosOrdenesDetalle.vrVentaUnitario   -		      "
                + "        tblDctosOrdenesDetalle.vrImpoconsumo)    *		      "
                + "       ( 1 - ( tblDctosOrdenesDetalle.vrDsctoPie /  100 ) )      * "
                + "       ( 1 - ( tblDctosOrdenesDetalle.porcentajeDscto /  100 ) ) / "
                + "       ( 1 + ( tblDctosOrdenesDetalle.porcentajeIva /  100 ) ))    "
                + "                                                     AS vrIvaVenta,"
                + "       SUM(tblDctosOrdenesDetalle.cantidad * 		      "
                + "        tblDctosOrdenesDetalle.vrImpoconsumo) AS vrImpoconsumo,    "
                + "       SUM((tblDctosOrdenesDetalle.cantidad        *               "
                + "           (tblDctosOrdenesDetalle.vrCosto         -               "
                + "            tblDctosOrdenesDetalle.vrImpoconsumo)) -               "
                + "           (tblDctosOrdenesDetalle.cantidad        *               "
                + "           (tblDctosOrdenesDetalle.vrCosto         -               "
                + "            tblDctosOrdenesDetalle.vrImpoconsumo)) /               "
                + "           ( 1 + tblDctosOrdenesDetalle.porcentajeIva / 100))      "
                + "                                                   AS vrIvaCosto,  "
                + "       SUM(tblDctosOrdenesDetalle.cantidad * 		      "
                + "        tblDctosOrdenesDetalle.vrCostoIND) AS vrCostoIND           "
                + "FROM   tblDctosOrdenes					      "
                + "INNER JOIN tblDctosOrdenesDetalle				      "
                + "ON  tblDctosOrdenes.IDLOCAL     = 				      "
                + "             tblDctosOrdenesDetalle.IDLOCAL			      "
                + "AND tblDctosOrdenes.IDTIPOORDEN = 				      "
                + "         tblDctosOrdenesDetalle.IDTIPOORDEN			      "
                + "AND tblDctosOrdenes.IDORDEN     = 				      "
                + "             tblDctosOrdenesDetalle.IDORDEN			      "
                + "GROUP BY tblDctosOrdenesDetalle.idLocal,              	      "
                + "         tblDctosOrdenesDetalle.idTipoOrden,          	      "
                + "         tblDctosOrdenesDetalle.idOrden,          		      "
                + "         tblDctosOrdenes.idLog) AS tmpDET     		      "
                + "WHERE tblDctosOrdenes.idLocal     =                      	      "
                + "                                tmpDET.idLocal                     "
                + "AND   tblDctosOrdenes.idTipoOrden =                     	      "
                + "                                tmpDET.idTipoOrden      	      "
                + "AND   tblDctosOrdenes.idOrden     =                      	      "
                + "                                tmpDET.idOrden          	      "
                + "AND   tmpDET.idLocal     =      				      "
                + getIdLocal() + "                                                    "
                + "AND   tmpDET.idTipoOrden =                       		      "
                + getIdTipoOrden() + "                                                "
                + "AND   tmpDET.idLog       =                       		      "
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
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("IdTipoOrden"));
                fachadaBean.setIdOrden(rs.getInt("IdOrden"));
                fachadaBean.setIdLog(rs.getInt("idLog"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setIdUsuario(rs.getDouble("idUsuario"));
                fachadaBean.setFechaOrden(
                        rs.getString("fechaOrden"));
                fachadaBean.setIdEstadoTx(rs.getInt("idEstadoTx"));
                fachadaBean.setCantidadArticulos(
                        rs.getInt("cantidadArticulos"));
                fachadaBean.setVrVentaSinIva(rs.getDouble("vrVentaSinIva"));
                fachadaBean.setVrVentaConIva(rs.getDouble("vrVentaConIva"));
                fachadaBean.setPesoTeoricoTotal(
                        rs.getDouble("pesoTeoricoTotal"));
                fachadaBean.setVrCostoConIva(rs.getDouble("vrCostoConIva"));
                fachadaBean.setVrCostoSinIva(rs.getDouble("vrCostoSinIva"));
                fachadaBean.setVrVentaSinDscto(rs.getDouble("vrVentaSinDscto"));
                fachadaBean.setVrIvaVenta(rs.getDouble("vrIvaVenta"));
                fachadaBean.setVrImpoconsumo(rs.getDouble("vrImpoconsumo"));
                fachadaBean.setVrIvaCosto(rs.getDouble("vrIvaCosto"));
                fachadaBean.setVrCostoIND(rs.getDouble("vrCostoIND"));

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
            return fachadaBean;

        }
    }

    // listaOTCosto
    public Vector listaOTCosto() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        // Variable de fachada de los datos
        FachadaDctoOrdenDetalleBean fachadaBean;

        Connection connection = null;

        //
        String selectStr =
                "   SELECT tblDctosOrdenesDetalle.idLocal,           "
                + "        tblDctosOrdenesDetalle.idTipoOrden,       "
                + "        tblDctosOrdenesDetalle.idOrden,           "
                + "        tblDctosOrdenesDetalle.item,              "
                + "        tblDctosOrdenesDetalle.cantidad,          "
                + "        tblDctosOrdenesDetalle.vrCosto,           "
                + "        tblDctosOrdenesDetalle.nombrePlu,         "
                + "        tblDctosOrdenes.idLog,                    "
                + "        tblDctosOrdenesDetalle.idPlu,             "
                + "        tblDctosOrdenes.idFicha,                  "
                + "        tblDctosOrdenes.idUsuario                 "
                + "   FROM       tblDctosOrdenes                     "
                + "   INNER JOIN tblDctosOrdenesDetalle              "
                + "  ON tblDctosOrdenes.IDLOCAL      =               "
                + "                  tblDctosOrdenesDetalle.IDLOCAL  "
                + "  AND tblDctosOrdenes.IDTIPOORDEN =               "
                + "              tblDctosOrdenesDetalle.IDTIPOORDEN  "
                + "  AND tblDctosOrdenes.IDORDEN     =               "
                + "                  tblDctosOrdenesDetalle.IDORDEN  "
                + "  WHERE tblDctosOrdenesDetalle.idLocalOrigen    = "
                + getIdLocalOrigen() + "                             "
                + "  AND  tblDctosOrdenesDetalle.idTipoOrdenOrigen = "
                + getIdTipoOrdenOrigen() + "                         "
                + "  AND  tblDctosOrdenesDetalle.idOrdenOrigen     = "
                + getIdOrdenOrigen() + "                             "
                + "  AND  tblDctosOrdenesDetalle.idOperacion       = "
                + getIdOperacion() + "                               "
                + "  AND  tblDctosOrdenesDetalle.item              = "
                + getItemPadre();

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
                fachadaBean.setItem(rs.getInt("item"));
                fachadaBean.setCantidad(rs.getDouble("cantidad"));
                fachadaBean.setVrCosto(rs.getDouble("vrCosto"));
                fachadaBean.setNombrePlu(rs.getString("nombrePlu"));
                fachadaBean.setIdLog(rs.getInt("idLog"));
                fachadaBean.setIdPlu(rs.getInt("idPlu"));
                fachadaBean.setIdFicha(rs.getInt("idFicha"));
                fachadaBean.setIdUsuario(rs.getDouble("idUsuario"));

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

    // listaOTSumnistroFCH
    public FachadaDctoOrdenDetalleBean listaOTSumnistroFCH() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        // Variable de fachada de los datos
        FachadaDctoOrdenDetalleBean fachadaBean =
                new FachadaDctoOrdenDetalleBean();

        Connection connection = null;

        //
        String selectStr =
                " SELECT TOP 1 tblDctosOrdenes.IDLOCAL,     "
                + "        tblDctosOrdenes.IDTIPOORDEN,     "
                + "        tblDctosOrdenes.IDORDEN          "
                + " FROM   tblDctosOrdenes                  "
                + " INNER JOIN tblDctosOrdenesDetalle       "
                + " ON  tblDctosOrdenes.IDLOCAL     =       "
                + "      tblDctosOrdenesDetalle.IDLOCAL     "
                + " AND tblDctosOrdenes.IDTIPOORDEN =       "
                + "  tblDctosOrdenesDetalle.IDTIPOORDEN     "
                + " AND tblDctosOrdenes.IDORDEN     =       "
                + "      tblDctosOrdenesDetalle.IDORDEN     "
                + " WHERE tblDctosOrdenes.idLocal   =       "
                + getIdLocal() + "                          "
                + " AND tblDctosOrdenes.IDTIPOORDEN   =     "
                + getIdTipoOrden() + "                      "
                + " AND   tblDctosOrdenes.numeroOrden =     "
                + getNumeroOrden() + "                      "
                + " AND tblDctosOrdenesDetalle.item   =     "
                + getItem();

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
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setIdOrden(rs.getInt("idOrden"));

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

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {

            // Cierra la conexion
            return fachadaBean;

        }
    }
}
