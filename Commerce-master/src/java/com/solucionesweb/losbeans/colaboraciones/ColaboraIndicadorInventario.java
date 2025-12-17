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
import java.util.*;
import java.io.IOException.*;

// Importa los paquetes de javax
import javax.naming.*;


public class ColaboraIndicadorInventario
        extends FachadaDctoOrdenDetalleBean
        implements Serializable, IConstantes {

     // Nombre JNDI del Data Source que esta clase requiere
    private static final String DATA_SOURCE_NAME = "CommerceAccess";

    // Clase helper usada para acceder a la base de datos
    private JDBCAccess jdbcAccess;

    // Metodo constructor por defecto sin parametros
    public ColaboraIndicadorInventario() {

        // Al instanciar el Bean establece la conexion a la base de datos
        jdbcAccess = new JDBCAccess(DATA_SOURCE_NAME);
    }

    // Metodo listaIndicadorPluRotacion
    public Vector listaIndicadorPluRotacion(String xFechaInicialSqlServer,
                                              String xFechaFinalSqlServer) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        String selectStr =

" SELECT 	        tblCategorias.nombreCategoria + ' ' +               " +
" 			tblPlus.nombrePlu AS nombrePlu,                     " +
" 			tblPlus.idPlu,                                      " +
" 			tblPlus.vrCosto,                                    " +
" 			tblPlus.factorDespacho,                             " +
" 			SUM(tblDctosOrdenesDetalle.cantidad)                " +
"                       AS cantidad,                                        " +
"                     	tblDctosOrdenesDetalle.vrVentaUnitario,             " +
" 			COUNT (tblPlus.idPlu)                               " +
"                       AS rotacion                                         " +
" FROM    		tblDctos                                            " +
" INNER JOIN  		tblDctosOrdenesDetalle                              " +
" ON			tblDctos.IDLOCAL = tblDctosOrdenesDetalle.IDLOCAL   " +
" AND			tblDctos.IDTIPOORDEN                                " +
"                                     = tblDctosOrdenesDetalle.IDTIPOORDEN  " +
" AND			tblDctos.IDORDEN = tblDctosOrdenesDetalle.IDORDEN   " +
" INNER JOIN		tblPlus                                             " +
" ON	        	tblPlus.IdPlu = tblDctosOrdenesDetalle.IdPlu        " +
" INNER JOIN  		tblCategorias                                       " +
" ON			tblCategorias.idLinea = tblPlus.idLinea             " +
" AND 		        tblCategorias.idCategoria = tblPlus.idCategoria     " +
" INNER JOIN  		tblPlusInventario                                   " +
" ON			tblPlus.idPlu = tblPlusInventario.idPlu             " +
" WHERE			tblDctos.idLocal   =                                " +
getIdLocal() + "                                                            " +
" AND		     	tblDctos.idTipoOrden =                              " +
getIdTipoOrden() + "                                                        " +
" AND			tblDctos.fechaDcto BETWEEN                         '" +
xFechaInicialSqlServer + "'                                                 " +
" 			AND                                                '" +
xFechaFinalSqlServer + "'                                                    " +
" AND 		        tblPlusInventario.idBodega =                        " +
getIdBodega() + "                                                           " +
" GROUP BY                                                                  " +
" 			tblCategorias.nombreCategoria,                      " +
" 			tblPlus.nombrePlu,                                  " +
" 			tblPlus.idPlu,                                      " +
" 			tblPlus.vrCosto,                                    " +
" 			tblPlus.factorDespacho,                             " +
"                       tblDctosOrdenesDetalle.vrVentaUnitario              " +
" ORDER BY 7 DESC";
        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaDctoOrdenDetalleBean fachadaBean;

            while (rs.next()) {

                fachadaBean = new FachadaDctoOrdenDetalleBean();

                //
                fachadaBean.setNombrePlu(rs.getString("nombrePlu"));
                fachadaBean.setIdPlu(rs.getInt("idPlu"));
                fachadaBean.setVrCosto(rs.getDouble("vrCosto"));
                fachadaBean.setFactorDespacho(rs.getInt("factorDespacho"));
                fachadaBean.setCantidad(rs.getDouble("cantidad"));
                fachadaBean.setVrVentaUnitario(rs.getDouble("vrVentaUnitario"));
                fachadaBean.setRotacion(rs.getInt("rotacion"));
                
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

      // Metodo listaIndicadorPluRotacion
    public Vector listaIndicadorSinVentas() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

            System.out.println(getIdLocal());
            System.out.println(getFechaInicialSqlServerStr());
            System.out.println(getFechaFinalSqlServerStr());
            System.out.println(getIndicadorInicial());
            System.out.println(getIndicadorFinal());
            System.out.println(getIdTipoOrdenINI());
            System.out.println(getIdTipoOrdenFIN());

        String selectStr =

"  SELECT tblPlus.idPlu                                                  " +
"        ,tblCategorias.nombreCategoria + ' ' +                          " +
"         tblPlus.nombrePlu AS nombrePlu                                 " +
"        ,tblPlus.vrGeneral                                              " +
"        ,tblPlus.vrMayorista                                            " +
"        ,tblPlus.vrCosto                                                " +
"        ,tblPlus.factorDespacho                                         " +
"        ,tblPlus.vrImpoconsumo                                          " +
"        ,tblPlus.vrCostoIND                                             " +
"    FROM tblPlus                                                        " +
"    INNER JOIN tblCategorias                                            " +
"    ON	tblCategorias.idLinea =                                          " +
"  				tblPlus.idLinea                          " +
"    AND	tblCategorias.idCategoria =                              " +
"  				tblPlus.idCategoria                      " +
"    WHERE tblPlus.idPlu NOT IN                                          " +
"    (		SELECT tblPlusInventario.idPlu                           " +
"  		FROM	tblPlusInventario                                " +
"  		WHERE	tblPlusInventario.existencia > 0                 " +
"  		AND		tblPlusInventario.idBodega = 1		 " +
"  		AND		tblPlusInventario.idPlu IN               " +
"  		(	SELECT     tblDctosOrdenesDetalle.IDPLU          " +
"  			FROM       tblDctos                              " +
"  			INNER JOIN tblDctosOrdenesDetalle                " +
"  			ON  tblDctos.IDLOCAL =                           " +
"  					tblDctosOrdenesDetalle.IDLOCAL   " +
"  			AND tblDctos.IDTIPOORDEN =                       " +
"  				tblDctosOrdenesDetalle.IDTIPOORDEN       " +
"  			AND tblDctos.IDORDEN =                           " +
"  				tblDctosOrdenesDetalle.IDORDEN           " +
"  			WHERE tblDctos.idLocal =                         " +
                                    getIdLocal() +
"  			AND tblDctos.fechaDcto BETWEEN                  '" +
                                    getFechaInicialSqlServerStr() + "'   " +
"  					       AND                      '" +
                                    getFechaFinalSqlServerStr() + "'     " +
"  			AND (tblDctos.idTipoOrden =                      " +
                                    getIdTipoOrdenINI() +
"  				 OR tblDctos.idTipoOrden =               " +
                                    getIdTipoOrdenFIN() +
"  				)                                        " +
"  			AND	 tblDctos.indicador BETWEEN              " +
                                    getIndicadorInicial() +
"  			AND                                              " +
                                    getIndicadorFinal() +
"  			GROUP BY tblDctosOrdenesDetalle.IDPLU            " +
"  		 )                                                       " +
"  		 GROUP BY tblPlusInventario.idPlu                        " +
"  	)                                                                " +
"  ORDER BY idPlu ASC		                                 ";

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
            FachadaDctoOrdenDetalleBean fachadaBean;

            while (rs.next()) {

                fachadaBean = new FachadaDctoOrdenDetalleBean();

                //
                fachadaBean.setNombrePlu(rs.getString("nombrePlu"));
                fachadaBean.setIdPlu(rs.getInt("idPlu"));
                fachadaBean.setVrGeneral(rs.getDouble("vrGeneral"));
                fachadaBean.setVrMayorista(rs.getDouble("vrMayorista"));
                fachadaBean.setVrCosto(rs.getDouble("vrCosto"));
                fachadaBean.setFactorDespacho(rs.getInt("factorDespacho"));
                fachadaBean.setVrImpoconsumo(rs.getDouble("vrImpoconsumo"));
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
