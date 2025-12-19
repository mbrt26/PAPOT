package com.solucionesweb.losbeans.colaboraciones;

// Importa la clase helper JDBCAccess que contiene los accesos a la base de datos
import com.solucionesweb.lasayudas.JDBCAccess;

// Importa el bean de utilidades
import com.solucionesweb.losbeans.utilidades.IConstantes;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaColaboraReporteDctoModBean;

// Importa los paquetes de java
import java.sql.*;
import java.io.*;
import java.io.IOException.*;

//import java.util.Date;
import java.util.Vector;
import java.io.IOException.*;

// Importa los paquetes de javax
import javax.naming.*;

public class ColaboraReporteModDctoBean extends FachadaColaboraReporteDctoModBean
        implements Serializable, IConstantes {

    // Nombre JNDI del Data Source que esta clase requiere
    private static final String DATA_SOURCE_NAME = "CommerceAccess";

    // Clase helper usada para acceder a la base de datos
    private JDBCAccess jdbcAccess;

    // Metodo constructor por defecto sin parametros
    public ColaboraReporteModDctoBean() {

        // Al instanciar el Bean establece la conexion a la base de datos
        jdbcAccess = new JDBCAccess(DATA_SOURCE_NAME);
    }

    // Metodo listaFechasTipoOrdenLocal MsAccess

    // listaVentaAll
    public Vector listaVentaAll(String xIdTipoOrdenCadena,
            String xFechaInicial,
            String xFechaFinal) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        String selectStr=
                  "   SELECT	tmpRVR.referencia,\n"
                + "		tmpRVR.nombrePlu,\n"
                + "		tmpRVR.nombreTercero,\n"
                + "		tmpRVR.fechaDcto,\n"
                + "		tmpRVR.idDcto,\n"
                + "		(tmpRVR.cantidadFacturada * tmpRVR.vrVentaUnitarioSinIva ) as vrBase,\n"
                + "		tmpRVR.pesoFacturado,\n"
                + "		tmpRVR.cantidadFacturada,\n"
                + "		tmpRVR.nombreNegocio,\n"
                + "		tmpRVR.aliasUsuario		\n"
                + "FROM\n"
                + "(SELECT	tblDctosOrdenesDetalle.IDTIPOORDEN,\n"
                + "		tblDctosOrdenesDetalle.IDORDEN,\n"
                + "		tblDctosOrdenesDetalle.IDPLU,\n"
                + "		tblPlus.nombrePlu AS NOMBREPLU,\n"
                + "		tblPlus.referencia,\n"
                + "		tblDctosOrdenes.idFicha,\n"
                + "		tblDctosOrdenesDetalle.CANTIDAD,\n"
                + "		tblDctosOrdenesDetalle.CANTIDADPEDIDA,\n"
                + "		tblDctosOrdenesDetalle.pesoEntregado,\n"
                + "		tblDctosOrdenesDetalle.pesoFacturado,\n"
                + "		tblDctosOrdenesDetalle.pesoTerminado,\n"
                + "		tblDctosOrdenesDetalle.cantidadEntregada,\n"
                + "		tblDctosOrdenesDetalle.cantidadFacturada,\n"
                + "		tblDctosOrdenesDetalle.cantidadTerminada,\n"
                + "		tblDctosOrdenesDetalle.vrCostoIND,\n"
                + "		tblDctosOrdenesDetalle.vrCostoOriginal,\n"
                + "		tblDctosOrdenesDetalle.vrVentaUnitarioSinIva,\n"
                + "		tblDctosOrdenesDetalle.PORCENTAJEIVA,\n"
                + "		tblDctosOrdenesDetalle.fechaEntrega,\n"
                + "		tblDctosOrdenesDetalle.idOperacion,\n"
                + "		tblDctos.fechaDcto,\n"
                + "		tblDctos.IDORDEN as Orden,\n"
                + "		tblDctos.idDcto,\n"
                + "		tblDctos.idCliente,\n"
                + "		tblDctos.nombreTercero,\n"
                + "		tblDctos.diasPlazo,\n"
                + "		tblDctos.idVendedor,\n"
                + "		tblDctos.idTipoNegocio,\n"
                + "		(case when idTipoNegocio = 1 \n"
                + "                THEN 'CONTADO' \n"
                + "                ELSE 'CREDITO' END ) AS nombreNegocio,\n"
                + "		tblDctos.indicador,\n"
                + "		ctrlUsuarios.aliasUsuario\n"
                + "FROM tblDctosOrdenesDetalle\n"
                + "inner join tblDctos\n"
                + "on tblDctosOrdenesDetalle.IDORDEN = tblDctos.IDORDEN\n"
                + "inner join tblDctosOrdenes\n"
                + "on tblDctosOrdenes.IDORDEN = tblDctosOrdenesDetalle.IDORDEN\n"
                + "inner join tblPlus\n"
                + "on tblPlus.idPlu = tblDctosOrdenesDetalle.IDPLU\n"
                + "inner join ctrlUsuarios\n"
                + "on ctrlUsuarios.idUsuario = tblDctos.idVendedor\n"
                + "WHERE tblDctos.fechaDcto BETWEEN '"
                + xFechaInicial +"' \n"
                + "AND	'"
                + xFechaFinal +"'\n"
                + "AND  tblDctos.idTipoOrden  IN ("
                + xIdTipoOrdenCadena +")) AS tmpRVR";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaColaboraReporteDctoModBean fachadaBean;

            while (rs.next()) {

                fachadaBean = new FachadaColaboraReporteDctoModBean();

                //               
                fachadaBean.setIdDcto(rs.getInt("idDcto"));
                fachadaBean.setFechaDcto(rs.getString("fechaDcto"));                
                fachadaBean.setVrBase(rs.getDouble("vrBase"));                
                fachadaBean.setNombreTercero(rs.getString("nombreTercero"));
                fachadaBean.setNombreReferencia(rs.getString("nombrePlu"));
                fachadaBean.setCantidadFacturado(rs.getDouble("cantidadFacturada"));
                fachadaBean.setPesoFacturado(rs.getDouble("pesoFacturado"));
                fachadaBean.setReferenciaCliente(rs.getString("referencia"));
                fachadaBean.setNombreTipoNegocio(
                                             rs.getString("nombreNegocio"));
                fachadaBean.setAliasUsuario(rs.getString("aliasUsuario"));
               

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

    // listaVentaUn
    public Vector listaVentaUn(String xIdTipoOrdenCadena,
            String xFechaInicial,
            String xFechaFinal) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        String selectStr=
                  "   SELECT	tmpRVR.referencia,\n"
                + "		tmpRVR.nombrePlu,\n"
                + "		tmpRVR.nombreTercero,\n"
                + "		tmpRVR.fechaDcto,\n"
                + "		tmpRVR.idDcto,\n"
                + "		(tmpRVR.cantidadFacturada * "
                + "     tmpRVR.vrVentaUnitarioSinIva ) as vrBase,\n"
                + "		tmpRVR.pesoFacturado,\n"
                + "		tmpRVR.cantidadFacturada,\n"
                + "		tmpRVR.nombreNegocio,\n"
                + "		tmpRVR.aliasUsuario		\n"
                + "FROM\n"
                + "(SELECT	tblDctosOrdenesDetalle.IDTIPOORDEN,\n"
                + "		tblDctosOrdenesDetalle.IDORDEN,\n"
                + "		tblDctosOrdenesDetalle.IDPLU,\n"
                + "		tblPlus.nombrePlu AS NOMBREPLU,\n"
                + "		tblPlus.referencia,\n"
                + "		tblDctosOrdenes.idFicha,\n"
                + "		tblDctosOrdenesDetalle.CANTIDAD,\n"
                + "		tblDctosOrdenesDetalle.CANTIDADPEDIDA,\n"
                + "		tblDctosOrdenesDetalle.pesoEntregado,\n"
                + "		tblDctosOrdenesDetalle.pesoFacturado,\n"
                + "		tblDctosOrdenesDetalle.pesoTerminado,\n"
                + "		tblDctosOrdenesDetalle.cantidadEntregada,\n"
                + "		tblDctosOrdenesDetalle.cantidadFacturada,\n"
                + "		tblDctosOrdenesDetalle.cantidadTerminada,\n"
                + "		tblDctosOrdenesDetalle.vrCostoIND,\n"
                + "		tblDctosOrdenesDetalle.vrCostoOriginal,\n"
                + "		tblDctosOrdenesDetalle.vrVentaUnitarioSinIva,\n"
                + "		tblDctosOrdenesDetalle.PORCENTAJEIVA,\n"
                + "		tblDctosOrdenesDetalle.fechaEntrega,\n"
                + "		tblDctosOrdenesDetalle.idOperacion,\n"
                + "		tblDctos.fechaDcto,\n"
                + "		tblDctos.IDORDEN as Orden,\n"
                + "		tblDctos.idDcto,\n"
                + "		tblDctos.idCliente,\n"
                + "		tblDctos.nombreTercero,\n"
                + "		tblDctos.diasPlazo,\n"
                + "		tblDctos.idVendedor,\n"
                + "		tblDctos.idTipoNegocio,\n"
                + "		(case when idTipoNegocio = 1 \n"
                + "                THEN 'CONTADO' \n"
                + "                ELSE 'CREDITO' END ) AS nombreNegocio,\n"
                + "		tblDctos.indicador,\n"
                + "		ctrlUsuarios.aliasUsuario\n"
                + "FROM tblDctosOrdenesDetalle\n"
                + "inner join tblDctos\n"
                + "on tblDctosOrdenesDetalle.IDORDEN = tblDctos.IDORDEN\n"
                + "inner join tblDctosOrdenes\n"
                + "on tblDctosOrdenes.IDORDEN = tblDctosOrdenesDetalle.IDORDEN\n"
                + "inner join tblPlus\n"
                + "on tblPlus.idPlu = tblDctosOrdenesDetalle.IDPLU\n"
                + "inner join ctrlUsuarios\n"
                + "on ctrlUsuarios.idUsuario = tblDctos.idVendedor\n"
                + "WHERE tblDctos.fechaDcto BETWEEN '"
                + xFechaInicial + "' \n"
                + "	AND '"
                + xFechaFinal + "'\n"
                + "AND  tblDctos.idTipoOrden  IN ("
                + xIdTipoOrdenCadena + ")                                   \n"
                + "AND tblDctos.idVendedor     = "
                + getIdVendedor() + ") AS tmpRVR";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaColaboraReporteDctoModBean fachadaBean;

            while (rs.next()) {

                fachadaBean = new FachadaColaboraReporteDctoModBean();

                //               
                fachadaBean.setIdDcto(rs.getInt("idDcto"));
                fachadaBean.setFechaDcto(rs.getString("fechaDcto"));                
                fachadaBean.setVrBase(rs.getDouble("vrBase"));                
                fachadaBean.setNombreTercero(rs.getString("nombreTercero"));
                fachadaBean.setNombreReferencia(rs.getString("nombrePlu"));
                fachadaBean.setCantidadFacturado(rs.getDouble("cantidadFacturada"));
                fachadaBean.setPesoFacturado(rs.getDouble("pesoFacturado"));
                fachadaBean.setReferenciaCliente(rs.getString("referencia"));
                fachadaBean.setNombreTipoNegocio(
                                             rs.getString("nombreNegocio"));
                fachadaBean.setAliasUsuario(rs.getString("aliasUsuario"));

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