package com.solucionesweb.losbeans.negocio;

// Importa la clase helper JDBCAccess que contiene los accesos a la base de datos
import com.solucionesweb.lasayudas.JDBCAccess;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaPluBean;

// Importa los paquetes de java
import java.sql.*;
import java.io.*;
import java.util.*;

//
import org.kxml.*;
import org.kxml.parser.*;

// Importa los paquetes de javax
import javax.naming.*;

public class PluBean extends FachadaPluBean implements Serializable {

    // Nombre JNDI del Data Source que esta clase requiere
    private static final String DATA_SOURCE_NAME = "CommerceAccess";
    // Clase helper usada para acceder a la base de datos
    private final JDBCAccess jdbcAccess;

    // Metodo constructor por defecto sin parametros
    public PluBean() {

        // Al instanciar el Bean establece la conexion a la base de datos
        jdbcAccess = new JDBCAccess(DATA_SOURCE_NAME);
    }

    // Metodo
    public FachadaPluBean existePluFachada() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        // Variable de fachada de los datos
        FachadaPluBean fachadaPluBean = new FachadaPluBean();

        Connection connection = null;

        String selectStr
                = "SELECT tblPlus.referencia,             "
                + "       tblPlus.idPlu,                  "
                + "       tblPlus.estado                  "
                + "FROM tblPlus                           "
                + "WHERE LOWER(tblPlus.referencia) =      "
                + " " + getStrIdReferenciaMinuscula() + " ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            if (rs.next()) {

                fachadaPluBean.setStrIdReferencia(rs.getString("referencia"));
                fachadaPluBean.setIdPlu(rs.getInt("idPlu"));
                fachadaPluBean.setEstado(rs.getInt("estado"));
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
            return fachadaPluBean;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {

            // Cierra la conexion
            return fachadaPluBean;

        }
    }

    // ingresa
    public boolean ingresa() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr
                = "INSERT INTO tblPlus  ( idPlu,        "
                + "                       nombrePlu,    "
                + "                       vrGeneral,    "
                + "                       vrMayorista,  "
                + "                       porcentajeIva,"
                + "                       idTipo,       "
                + "                       idLinea,      "
                + "                       idCategoria,  "
                + "                       idMarca,      "
                + "                       vrSucursal,   "
                + "                       referencia,   "
                + "                       estado,       "
                + "                       sk_tercero_ppal,       "
                + "                       idSeq,        "
                + "                      factorDespacho,"
                + "                      vrImpoconsumo, "
                + "                      vrCosto,       "
                + "                      vrCostoIND,    "
                + "                      factor,         "
                + "                      dolarizado,     "
                + "                      factorDensidad) "
                + "VALUES ("
                + +getIdPlu() + ",'"
                + getNombrePlu().toUpperCase() + "',"
                + getVrGeneral() + ", "
                + getVrMayorista() + ","
                + getPorcentajeIva() + " ,"
                + getIdTipo() + ",       "
                + getIdLinea() + ",      "
                + getIdCategoria() + " ,  "
                + getIdMarca() + " ,      "
                + getVrSucursal() + " ,'"
                + getReferencia() + "',   "
                + getEstado() + " ,       "
                + getSk_proveedor() + " ,       "
                + getIdSeq() + " ,        "
                + getFactorDespacho() + " ,"
                + getVrImpoconsumo() + ", "
                + getVrCosto() + " ,       "
                + getVrCostoIND() + " ,    "
                + getFactor() + " ,    "
                + getDolarizado() + " ,    "
                + getFactorDensidad() + ")";

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

            // Cierra el connection
            jdbcAccess.cleanup(connection, selectStatement, null);
            return okIngresar;
        }
    }

    // maximoIdPlu
    public int maximoIdPlu() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        int maximoIdPlu = 0;

        Connection connection = null;

        String selectStr
                = "SELECT MAX(tblPlus.idPlu)      "
                + "              AS maximoIdPlu   "
                + "FROM tblPlus                   ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            if (rs.next()) {

                maximoIdPlu = rs.getInt("maximoIdPlu");
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
            return maximoIdPlu;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {

            // Cierra la conexion
            return maximoIdPlu;

        }
    }

    // listaIdSeqMaxima
    public int listaIdSeqMaxima() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        int intMaxIdSeq = 0;

        Connection connection = null;

        String selectStr
                = "SELECT MAX(tblPlus.idSeq)   "
                + "              AS maxIdSeq   "
                + "FROM tblPlus                ";

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
                intMaxIdSeq = rs.getInt("maxIdSeq");

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

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {

            // Cierra la conexion
            jdbcAccess.cleanup(connection, selectStatement, null);
            return intMaxIdSeq;

        }
    }

    // actualizaAll
    public boolean actualizaAll() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr
                = "UPDATE tblPlus                   "
                + " SET   tblPlus.nombrePlu     =  '"
                + getNombrePlu() + "',              "
                + "       tblPlus.vrGeneral     =   "
                + getVrGeneral() + ",     "
                + "       tblPlus.vrMayorista   =   "
                + getVrMayorista() + ",             "
                + "       tblPlus.porcentajeIva =   "
                + getPorcentajeIva() + ",           "
                + "       tblPlus.idTipo        =   "
                + getIdTipo() + ",                  "
                + "       tblPlus.idLinea       =   "
                + getIdLinea() + ",                 "
                + "       tblPlus.idCategoria   =   "
                + getIdCategoria() + ",             "
                + "       tblPlus.idMarca       =   "
                + getIdMarca() + ",                 "
                + "       tblPlus.vrSucursal    =   "
                + getVrSucursal() + ",              "
                + "       tblPlus.vrCosto       =   "
                + getVrCosto() + ",                 "
                + "       tblPlus.vrCostoIND    =   "
                + getVrCostoIND() + ",              "
                + "       tblPlus.idSeq         =   "
                + getIdSeq() + ",                   "
                + "       tblPlus.estado        =   "
                + getEstado() + ",                  "
                + "       tblPlus.factorDespacho=   "
                + getFactorDespacho() + ",          "
                + "       tblPlus.vrImpoconsumo =   "
                + getVrImpoconsumo() + ",           "
                + "       tblPlus.factorDensidad =  "
                + getFactorDensidad() + ",          "
                + "       tblPlus.referencia     = '"
                + getReferencia() + "',             "
                + "       tblPlus.sk_tercero_ppal = "
                + getSk_proveedor() + ",            "
                + "       tblPlus.factor =          "
                + getFactor() + ",                  "
                + "       tblPlus.dolarizado =      "
                + getDolarizado() + "               "
                + " WHERE tblPlus.idPlu      =      "
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

            // Cierra el connection
            jdbcAccess.cleanup(connection, selectStatement, null);
            return okIngresar;
        }
    }

    //
    public Vector listaPluTx() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        Vector contenedor = new Vector();
        Connection connection = null;

        String selectStr
                = "SELECT tblPlus.idPlu     "
                + "  ,tblPlus.nombrePlu     "
                + "  ,tblPlus.vrGeneral     "
                + "  ,tblPlus.vrMayorista   "
                + "  ,tblPlus.porcentajeIva "
                + "  ,tblPlus.idTipo        "
                + "  ,tblPlus.idLinea       "
                + "  ,tblPlus.idUCompra     "
                + "  ,tblPlus.idUVenta      "
                + "  ,tblPlus.vrCosto       "
                + "  ,tblPlus.idCategoria   "
                + "  ,tblPlus.idMarca       "
                + "  ,tblPlus.vrSucursal    "
                + "  ,tblPlus.factorVenta   "
                + "  ,tblPlus.factorDespacho"
                + "  ,tblPlus.estado        "
                + "  ,tblPlus.idSeq         "
                + "  ,tblPlus.referencia    "
                + "  ,tblPlus.vrImpoconsumo "
                + "  ,tblPlus.vrCostoIND    "
                + "FROM tblPlus             "
                + "WHERE tblPlus.idSeq >    "
                + getIdSeq() + "            "
                + "ORDER BY tblPlus.idSeq   ";

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

                //
                fachadaBean = new FachadaPluBean();

                //
                fachadaBean.setIdPlu(rs.getInt("idPlu"));
                fachadaBean.setNombrePlu(rs.getString("nombrePlu"));
                fachadaBean.setVrGeneral(rs.getDouble("vrGeneral"));
                fachadaBean.setVrMayorista(rs.getInt("vrMayorista"));
                fachadaBean.setPorcentajeIva(rs.getDouble("porcentajeIva"));
                fachadaBean.setIdTipo(rs.getInt("idTipo"));
                fachadaBean.setIdLinea(rs.getInt("idLinea"));
                fachadaBean.setIdUCompra(rs.getInt("idUCompra"));
                fachadaBean.setIdUVenta(rs.getInt("idUVenta"));
                fachadaBean.setVrCosto(rs.getInt("vrCosto"));
                fachadaBean.setIdCategoria(rs.getInt("idCategoria"));
                fachadaBean.setIdMarca(rs.getInt("idMarca"));
                fachadaBean.setVrSucursal(rs.getInt("vrSucursal"));
                fachadaBean.setFactorVenta(rs.getInt("factorVenta"));
                fachadaBean.setFactorDespacho(rs.getInt("factorDespacho"));
                fachadaBean.setEstado(rs.getInt("estado"));
                fachadaBean.setIdSeq(rs.getInt("idSeq"));
                fachadaBean.setReferencia(rs.getString("referencia"));
                fachadaBean.setVrImpoconsumo(rs.getDouble("vrImpoconsumo"));
                fachadaBean.setVrCostoIND(rs.getDouble("vrCostoIND"));

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
            jdbcAccess.cleanup(connection, selectStatement, null);
            return contenedor;
        }
    }

    // actualizaCostoPluCombo
    public boolean actualizaCostoPluCombo() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        //
        String selectStr
                = "UPDATE    tblPlus                       "
                + "SET       tblPlus.vrCosto =           "
                + "                    tmpPLus.vrCosto,  "
                + "          tblPlus.vrCostoIND =        "
                + "                  tmpPLus.vrCostoIND  "
                + "FROM      tblPlus                     "
                + "INNER JOIN                            "
                + " ( SELECT tblPlus.idTipo,             "
                + "          (tblPlus.vrCosto *          "
                + "           tblPlusCombo.cantidad)     "
                + "                      AS vrCosto,     "
                + "          (tblPlus.vrCostoIND *       "
                + "           tblPlusCombo.cantidad)     "
                + "                     AS vrCostoIND,   "
                + "           tblPlusCombo.cantidad,     "
                + "           tblPlusCombo.idPlu,        "
                + "           tblPlusCombo.idPluCombo    "
                + "   FROM   tblPlus                     "
                + "   INNER JOIN tblPlusCombo            "
                + "   ON tblPlus.idPlu      =            "
                + "                tblPlusCombo.idPlu    "
                + "   WHERE  tblPlus.idTipo = 1          "
                + "   AND    tblPlusCombo.cantidad>0)    "
                + "                          tmpPLus     "
                + "ON tblPlus.idPlu = tmpPLus.idPluCombo ";

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

            // Cierra el connection
            jdbcAccess.cleanup(connection, selectStatement, null);
            return okIngresar;
        }
    }

    // actualizaCosto
    public boolean actualizaCosto(int xIdLocal,
            int xIdTipoOrden,
            int xIdOrden) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        //
        String selectStr
                = " UPDATE tblPlus                            "
                + " SET    tblPlus.vrCosto    = 		  "
                + "                          tmpCST.vrCosto,  "
                + "        tblPlus.vrCostoIND =		  "
                + "                       tmpCST.vrCostoIND	  "
                + " FROM   tblPlus			     	  "
                + " INNER JOIN (				  "
                + " SELECT tmpPLU.idPlu,			  "
                + "        SUM(tmpPLU.existencia * 	     	  "
                + "        tmpPLU.vrCosto) /		  "
                + "        SUM(tmpPLU.existencia) 	     	  "
                + "                    AS vrCosto,		  "
                + "        SUM(tmpPLU.existencia * 	     	  "
                + "        tmpPLU.vrCostoIND) /		  "
                + "        SUM(tmpPLU.existencia) 	     	  "
                + "                    AS vrCostoIND          "
                + " FROM  (			             	  "
                + " SELECT tblPlusInventario.idPlu,	     	  "
                + "        (CASE WHEN 			  "
                + "   tblPlusInventario.existencia > 0 	  "
                + "  THEN tblPlusInventario.existencia 	  "
                + "  ELSE 0				     	  "
                + "  END) AS existencia, 		     	  "
                + " (CASE WHEN 			          "
                + "  tblPlusInventario.existencia > 0 	  "
                + " THEN tblPlus.vrCosto 	                  "
                + " ELSE 0				     	  "
                + " END) AS vrCosto,                          "
                + " (CASE WHEN 			          "
                + "  tblPlusInventario.existencia > 0 	  "
                + " THEN tblPlus.vrCostoIND 	          "
                + " ELSE 0				     	  "
                + " END) AS vrCostoIND                        "
                + " FROM   tblDctosOrdenesDetalle	     	  "
                + " INNER JOIN tblPlusInventario 	     	  "
                + " ON tblDctosOrdenesDetalle.IDLOCAL = 	  "
                + "              tblPlusInventario.idLocal    "
                + " AND tblDctosOrdenesDetalle.IDPLU  = 	  "
                + "                tblPlusInventario.idPlu    "
                + " INNER JOIN tblPlus			  "
                + " ON tblPlusInventario.idPlu        = 	  "
                + "                           tblPlus.idPlu   "
                + " WHERE      tblPlusInventario.idBodega = 888 "
                + " AND  tblDctosOrdenesDetalle.IDLOCAL   =   "
                + +xIdLocal + "                              "
                + " AND tblDctosOrdenesDetalle.IDTIPOORDEN =  "
                + +xIdTipoOrden + "                          "
                + " AND  tblDctosOrdenesDetalle.IDORDEN    =  "
                + +xIdOrden + "                              "
                + " UNION				     	  "
                + " SELECT  tblDctosOrdenesDetalle.idPlu,     "
                + "         tblDctosOrdenesDetalle.cantidad,  "
                + "         tblDctosOrdenesDetalle.vrCosto,   "
                + "         tblDctosOrdenesDetalle.vrCostoIND "
                + " FROM    tblDctosOrdenesDetalle	     	  "
                + " WHERE  tblDctosOrdenesDetalle.IDLOCAL   = "
                + xIdLocal + "                               "
                + " AND  tblDctosOrdenesDetalle.IDTIPOORDEN = "
                + xIdTipoOrden + "                           "
                + " AND  tblDctosOrdenesDetalle.IDORDEN     = "
                + xIdOrden + " )                  AS tmpPLU   "
                + " GROUP BY tmpPLU.idPlu                     "
                + " HAVING SUM(tmpPLU.existencia) > 0)        "
                + "                             AS tmpCST	  "
                + " ON tblPlus.idPlu =  tmpCST.idPlu	  ";

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

            // Cierra el connection
            jdbcAccess.cleanup(connection, selectStatement, null);
            return okIngresar;
        }
    }

    public boolean existePlu() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean existeLinea = false;

        Connection connection = null;

        String selectStr
                = "SELECT tblPlus.*      "
                + "FROM tblPlus          "
                + "WHERE tblPlus.idPlu = "
                + getIdPlu();

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            if (rs.next()) {

                existeLinea = true;

            }

            // Cierra el Resultset
            rs.close();

        } catch (SQLException sqle) {

            System.out.println("SQLException in" + nombreClase);

            String sqlMessage = sqle.getMessage();
            String sqlState = sqle.getSQLState();
            int vendorCode = sqle.getErrorCode();
            System.out.println("Ocurrio una  excepcion");
            System.out.println("Mensaje " + sqlMessage);
            System.out.println("SQL State " + sqlState);
            System.out.println("Vendor Code " + vendorCode);
            return existeLinea;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {
            // Cierra la conexion a la base de datos limpiando la statement usada y la
            jdbcAccess.cleanup(connection, selectStatement, null);
            return existeLinea;
        }
    }

// traverse_PluTx
    public void traverse_PluTx(XmlParser parser,
            String indent) throws Exception {

        //
        boolean leave = false;

        //
        String idPlu = new String();
        String nombrePlu = new String();
        String vrGeneral = new String();
        String vrMayorista = new String();
        String porcentajeIva = new String();
        String idTipo = new String();
        String idLinea = new String();
        String idUCompra = new String();
        String idUVenta = new String();
        String vrCosto = new String();
        String idCategoria = new String();
        String idMarca = new String();
        String vrSucursal = new String();
        String factorVenta = new String();
        String factorDespacho = new String();
        String estado = new String();
        String idSeq = new String();
        String referencia = new String();
        String vrImpoconsumo = new String();
        String vrCostoIND = new String();

        do {
            ParseEvent event = parser.read();
            ParseEvent pe;

            switch (event.getType()) {

                // For example, <CxC>
                case Xml.START_TAG:

                    // see API doc of StartTag for more access methods
                    // Pick up idagenda for display
                    if ("idPlu".equals(event.getName())) {
                        pe = parser.read();
                        idPlu = pe.getText();
                    }

                    // Pick up clave for display
                    if ("nombrePlu".equals(event.getName())) {
                        pe = parser.read();
                        nombrePlu = pe.getText();
                    }

                    // Pick up clave for display
                    if ("vrGeneral".equals(event.getName())) {
                        pe = parser.read();
                        vrGeneral = pe.getText();
                    }

                    // Pick up clave for display
                    if ("vrMayorista".equals(event.getName())) {
                        pe = parser.read();
                        vrMayorista = pe.getText();
                    }

                    // Pick up clave for display
                    if ("porcentajeIva".equals(event.getName())) {
                        pe = parser.read();
                        porcentajeIva = pe.getText();
                    }

                    // Pick up clave for display
                    if ("idTipo".equals(event.getName())) {
                        pe = parser.read();
                        idTipo = pe.getText();
                    }

                    // Pick up clave for display
                    if ("idLinea".equals(event.getName())) {
                        pe = parser.read();
                        idLinea = pe.getText();
                    }

                    // Pick up clave for display
                    if ("idUCompra".equals(event.getName())) {
                        pe = parser.read();
                        idUCompra = pe.getText();
                    }

                    // Pick up clave for display
                    if ("idUVenta".equals(event.getName())) {
                        pe = parser.read();
                        idUVenta = pe.getText();
                    }

                    // Pick up clave for display
                    if ("vrCosto".equals(event.getName())) {
                        pe = parser.read();
                        vrCosto = pe.getText();
                    }

                    // Pick up clave for display
                    if ("idCategoria".equals(event.getName())) {
                        pe = parser.read();
                        idCategoria = pe.getText();
                    }

                    // Pick up clave for display
                    if ("idMarca".equals(event.getName())) {
                        pe = parser.read();
                        idMarca = pe.getText();
                    }

                    // Pick up clave for display
                    if ("vrSucursal".equals(event.getName())) {
                        pe = parser.read();
                        vrSucursal = pe.getText();
                    }

                    // Pick up clave for display
                    if ("factorVenta".equals(event.getName())) {
                        pe = parser.read();
                        factorVenta = pe.getText();
                    }

                    // Pick up clave for display
                    if ("factorDespacho".equals(event.getName())) {
                        pe = parser.read();
                        factorDespacho = pe.getText();
                    }

                    // Pick up clave for display
                    if ("estado".equals(event.getName())) {
                        pe = parser.read();
                        estado = pe.getText();
                    }

                    // Pick up clave for display
                    if ("idSeq".equals(event.getName())) {
                        pe = parser.read();
                        idSeq = pe.getText();
                    }

                    // Pick up clave for display
                    if ("referencia".equals(event.getName())) {
                        pe = parser.read();
                        referencia = pe.getText();
                    }

                    // Pick up clave for display
                    if ("vrImpoconsumo".equals(event.getName())) {
                        pe = parser.read();
                        vrImpoconsumo = pe.getText();
                    }

                    // Pick up clave for display
                    if ("vrCostoIND".equals(event.getName())) {
                        pe = parser.read();
                        vrCostoIND = pe.getText();

                        // ingresa
                        setIdPlu(idPlu);
                        setNombrePlu(nombrePlu);
                        setVrGeneral(vrGeneral);
                        setVrMayorista(vrMayorista);
                        setPorcentajeIva(porcentajeIva);
                        setIdTipo(idTipo);
                        setIdLinea(idLinea);
                        setIdUCompra(idUCompra);
                        setIdUVenta(idUVenta);
                        setVrCosto(vrCosto);
                        setIdCategoria(idCategoria);
                        setIdMarca(idMarca);
                        setVrSucursal(vrSucursal);
                        setFactorVenta(factorVenta);
                        setFactorDespacho(factorDespacho);
                        setEstado(estado);
                        setIdSeq(idSeq);
                        setReferencia(referencia);
                        setVrImpoconsumo(vrImpoconsumo);
                        setVrCostoIND(vrCostoIND);

                        //
                        if (this.existePlu()) {
                            System.out.println(" actualiza plu");

                            //
                            this.actualizaAll();
                        } else {
                            System.out.println(" ingresa plu");

                            //
                            this.ingresa();
                        }

                    }

                    traverse_PluTx(parser, ""); // recursion call for each <tag></tag>
                    break;

                // For example </title?
                case Xml.END_TAG:
                    leave = true;
                    break;

                // For example </rss>
                case Xml.END_DOCUMENT:
                    leave = true;
                    break;

                // For example, the text between tags
                case Xml.TEXT:
                    break;
                case Xml.WHITESPACE:
                    break;
                default:
            }
        } while (!leave);
    }
}
