package com.solucionesweb.losbeans.negocio;

// Importa la clase helper JDBCAccess que contiene los accesos a la base de datos
import com.solucionesweb.lasayudas.JDBCSoftlandAccess;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaDctoBean;

// Importa los paquetes de java
import java.sql.*;
import java.io.*;
import java.io.IOException.*;
import java.util.*;

// Importa los paquetes de javax
import javax.naming.*;

// Importa la interface de las constantes
import com.solucionesweb.losbeans.utilidades.IConstantes;

public class ContableMovimientoTx extends FachadaDctoBean
        implements Serializable, IConstantes {

    // Nombre JNDI del Data Source que esta clase requiere
    private static final String DATA_SOURCE_NAME = "SoftlandAccess";
    // Clase helper usada para acceder a la base de datos
    private JDBCSoftlandAccess jdbcSoftlandAccess;
    //
    Vector contenedorLog = new Vector();

    // Metodo constructor por defecto sin parametros
    public ContableMovimientoTx() {

        // Al instanciar el Bean establece la conexion a la base de datos
        jdbcSoftlandAccess = new JDBCSoftlandAccess(DATA_SOURCE_NAME);
    }

    // ingresa_GTERCEROS
    public boolean ingresa_GTERCEROS(String xBdNameContable) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;


        //
        String selectStr = "" ;

        //
        if ( getIdTipoOrden() == 21 ||
             getIdTipoOrden() == 29   ) {

           //
           selectStr =
                "INSERT INTO [" + xBdNameContable + "].[dbo].[CMDMOVIMIENTO]"
                + "           ([CMDAnoMovimiento]                 "
                + "           ,[CMDPeriodoMovimiento]             "
                + "           ,[CMDComprobanteMovimiento]         "
                + "           ,[CMDPrefijoMovimiento]             "
                + "           ,[CMDDocumentoMovimiento]           "
                + "           ,[CMDFechaMovimiento]               "
                + "           ,[CMDItemMovimiento]                "
                + "           ,[CMDCodigoCuentaMovimiento]        "
                + "           ,[CMDCodCentroCostosMovimiento]     "
                + "           ,[CMDCodigoMonedaMovimiento]        "
                + "           ,[CMDIdentificadorUnoMovimiento]    "
                + "           ,[CMDSucursalMovimiento]            "
                + "           ,[CMDIdentificadorDosMovimiento]    "
                + "           ,[CMDPrefijoRefmovimiento]          "
                + "           ,[CMDDocumentoRefMovimiento]        "
                + "           ,[CMDComentariosMovimiento]         "
                + "           ,[CMDValorMovimiento]               "
                + "           ,[CMDValorBaseMovimiento]           "
                + "           ,[CMDValorMonedaMovimiento]         "
                + "           ,[CMDNaturalezaMovimiento]          "
                + "           ,[CMDOrigenMovimiento])             "
                + "     VALUES    (                               "
                + getAnoMovimientoSoftLand() + ","
                + getPeriodoMovimientoSoftLand() + ","
                + getIdComprobanteContableSoftLand() + ","
                + getIdPrefijoMovimientoSoftLand() + ","
                + getIdDctoEnteroSoftLand() + ","
                + getFechaDctoSoftLand() + ","
                + getItemMovimientoSoftLand() + ","
                + getIdSubcuenta() + ","
                + getStrIdLocalSoftLand() + ","
                + getCodigoMonedaMovimientoSoftLand() + ","
                + getIdCliente() + ","
                + getIdSucursalSoftLand() + ","
                + getIdCliente() + ","
                + getIdPrefijoMovimientoSoftLand() + ","
                + getIdDctoEnteroSoftLand() + ","
                + getComentarioMovimientoSoftLand() + ","
                + getVrBase() + ","
                + getVrBaseContableRedondeo() + ","
                + getVrTotal() + ","
                + getNombreAsientoSoftland() + ","
                + getOrigenMovimientoSoftland() + ")";


        } else {

           //
           selectStr =
                "INSERT INTO [" + xBdNameContable + "].[dbo].[CMDMOVIMIENTO]"
                + "           ([CMDAnoMovimiento]                 "
                + "           ,[CMDPeriodoMovimiento]             "
                + "           ,[CMDComprobanteMovimiento]         "
                + "           ,[CMDPrefijoMovimiento]             "
                + "           ,[CMDDocumentoMovimiento]           "
                + "           ,[CMDFechaMovimiento]               "
                + "           ,[CMDItemMovimiento]                "
                + "           ,[CMDCodigoCuentaMovimiento]        "
                + "           ,[CMDCodCentroCostosMovimiento]     "
                + "           ,[CMDCodigoMonedaMovimiento]        "
                + "           ,[CMDIdentificadorUnoMovimiento]    "
                + "           ,[CMDSucursalMovimiento]            "
                + "           ,[CMDIdentificadorDosMovimiento]    "
                + "           ,[CMDPrefijoRefmovimiento]          "
                + "           ,[CMDDocumentoRefMovimiento]        "
                + "           ,[CMDComentariosMovimiento]         "
                + "           ,[CMDValorMovimiento]               "
                + "           ,[CMDValorBaseMovimiento]           "
                + "           ,[CMDValorMonedaMovimiento]         "
                + "           ,[CMDNaturalezaMovimiento]          "
                + "           ,[CMDOrigenMovimiento])             "
                + "     VALUES    (                               "
                + getAnoMovimientoSoftLand() + ","
                + getPeriodoMovimientoSoftLand() + ","
                + getIdComprobanteContableSoftLand() + ","
                + getIdPrefijoMovimientoSoftLand() + ","
                + getIdDctoEnteroSoftLand() + ","
                + getFechaDctoSoftLand() + ","
                + getItemMovimientoSoftLand() + ","
                + getIdSubcuenta() + ","
                + getStrIdLocalSoftLand() + ","
                + getCodigoMonedaMovimientoSoftLand() + ","
                + getIdCliente() + ","
                + getIdSucursalSoftLand() + ","
                + getIdCliente() + ","
                + getIdPrefijoMovimientoSoftLand() + ","
                + getIdDctoNitCCPrefijoSoftLand() + ","
                + getComentarioMovimientoSoftLand() + ","
                + getVrBase() + ","
                + getVrBaseContableRedondeo() + ","
                + getVrTotal() + ","
                + getNombreAsientoSoftland() + ","
                + getOrigenMovimientoSoftland() + ")";

        }
 
        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcSoftlandAccess.getConnection();

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
            jdbcSoftlandAccess.cleanup(connection, selectStatement, null);
            return okIngresar;
        }
    }

        // ingresa_GTERCEROS
    public boolean ingresa_GTERCEROS_1(String xBdNameContable) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        //
        String selectStr = "";


        //
        if (getIdComprobanteContable() == 2 ) {

        //
        selectStr =
                "INSERT INTO [" + xBdNameContable + "].[dbo].[CMDMOVIMIENTO]"
                + "           ([CMDAnoMovimiento]                 "
                + "           ,[CMDPeriodoMovimiento]             "
                + "           ,[CMDComprobanteMovimiento]         "
                + "           ,[CMDPrefijoMovimiento]             "
                + "           ,[CMDDocumentoMovimiento]           "
                + "           ,[CMDFechaMovimiento]               "
                + "           ,[CMDItemMovimiento]                "
                + "           ,[CMDCodigoCuentaMovimiento]        "
                + "           ,[CMDCodCentroCostosMovimiento]     "
                + "           ,[CMDCodigoMonedaMovimiento]        "
                + "           ,[CMDIdentificadorUnoMovimiento]    "
                + "           ,[CMDSucursalMovimiento]            "
                + "           ,[CMDIdentificadorDosMovimiento]    "
                + "           ,[CMDPrefijoRefmovimiento]          "
                + "           ,[CMDDocumentoRefMovimiento]        "
                + "           ,[CMDComentariosMovimiento]         "
                + "           ,[CMDValorMovimiento]               "
                + "           ,[CMDValorBaseMovimiento]           "
                + "           ,[CMDValorMonedaMovimiento]         "
                + "           ,[CMDNaturalezaMovimiento]          "
                + "           ,[CMDOrigenMovimiento])             "
                + "     VALUES    (                               "
                + getAnoMovimientoSoftLand() + ","
                + getPeriodoMovimientoSoftLand() + ","
                + getIdComprobanteContableSoftLand() + ","
                + getIdPrefijoMovimientoSoftLand() + ","
                + getIdDctoNitCCSoftLand() + ","
                + getFechaDctoSoftLand() + ","
                + getItemMovimientoSoftLand() + ","
                + getIdSubcuenta() + ","
                + getStrIdLocalSoftLand() + ","
                + getCodigoMonedaMovimientoSoftLand() + ","
                + getIdCliente() + ","
                + getIdSucursalSoftLand() + ","
                + getIdCliente() + ","
                + getIdPrefijoMovimientoSoftLand() + ","
                + getIdDctoNitCCSoftLand() + ","
                + getComentarioMovimientoSoftLand() + ","
                + getVrBase() + ","
                + getVrBaseContableRedondeo() + ","
                + getVrTotal() + ","
                + getNombreAsientoSoftland() + ","
                + getOrigenMovimientoSoftland() + ")";

        }


        //
        if (getIdComprobanteContable() == 13 ) {

        //
        selectStr =
                "INSERT INTO [" + xBdNameContable + "].[dbo].[CMDMOVIMIENTO]"
                + "           ([CMDAnoMovimiento]                 "
                + "           ,[CMDPeriodoMovimiento]             "
                + "           ,[CMDComprobanteMovimiento]         "
                + "           ,[CMDPrefijoMovimiento]             "
                + "           ,[CMDDocumentoMovimiento]           "
                + "           ,[CMDFechaMovimiento]               "
                + "           ,[CMDItemMovimiento]                "
                + "           ,[CMDCodigoCuentaMovimiento]        "
                + "           ,[CMDCodCentroCostosMovimiento]     "
                + "           ,[CMDCodigoMonedaMovimiento]        "
                + "           ,[CMDIdentificadorUnoMovimiento]    "
                + "           ,[CMDSucursalMovimiento]            "
                + "           ,[CMDIdentificadorDosMovimiento]    "
                + "           ,[CMDPrefijoRefmovimiento]          "
                + "           ,[CMDDocumentoRefMovimiento]        "
                + "           ,[CMDComentariosMovimiento]         "
                + "           ,[CMDValorMovimiento]               "
                + "           ,[CMDValorBaseMovimiento]           "
                + "           ,[CMDValorMonedaMovimiento]         "
                + "           ,[CMDNaturalezaMovimiento]          "
                + "           ,[CMDOrigenMovimiento])             "
                + "     VALUES    (                               "
                + getAnoMovimientoSoftLand() + ","
                + getPeriodoMovimientoSoftLand() + ","
                + getIdComprobanteContableSoftLand() + ","
                + getIdPrefijoMovimientoSoftLand() + ","
                + getIdDctoEnteroSoftLand() + ","
                + getFechaDctoSoftLand() + ","
                + getItemMovimientoSoftLand() + ","
                + getIdSubcuenta() + ","
                + getStrIdLocalSoftLand() + ","
                + getCodigoMonedaMovimientoSoftLand() + ","
                + getIdCliente() + ","
                + getIdSucursalSoftLand() + ","
                + getIdCliente() + ","
                + getIdPrefijoMovimientoSoftLand() + ","
                + getIdDctoNitCCSoftLand() + ","
                + getComentarioMovimientoSoftLand() + ","
                + getVrBase() + ","
                + getVrBaseContableRedondeo() + ","
                + getVrTotal() + ","
                + getNombreAsientoSoftland() + ","
                + getOrigenMovimientoSoftland() + ")";

        }


        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcSoftlandAccess.getConnection();

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
            jdbcSoftlandAccess.cleanup(connection, selectStatement, null);
            return okIngresar;
        }
    }

    // ingresaPrefijo_GTERCEROS
    public boolean ingresaPrefijo_GTERCEROS(String xBdNameContable) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        //
        String selectStr =
                "INSERT INTO [" + xBdNameContable + "].[dbo].[CMDMOVIMIENTO]"
                + "           ([CMDAnoMovimiento]                 "
                + "           ,[CMDPeriodoMovimiento]             "
                + "           ,[CMDComprobanteMovimiento]         "
                + "           ,[CMDPrefijoMovimiento]             "
                + "           ,[CMDDocumentoMovimiento]           "
                + "           ,[CMDFechaMovimiento]               "
                + "           ,[CMDItemMovimiento]                "
                + "           ,[CMDCodigoCuentaMovimiento]        "
                + "           ,[CMDCodCentroCostosMovimiento]     "
                + "           ,[CMDCodigoMonedaMovimiento]        "
                + "           ,[CMDIdentificadorUnoMovimiento]    "
                + "           ,[CMDSucursalMovimiento]            "
                + "           ,[CMDIdentificadorDosMovimiento]    "
                + "           ,[CMDPrefijoRefmovimiento]          "
                + "           ,[CMDDocumentoRefMovimiento]        "
                + "           ,[CMDComentariosMovimiento]         "
                + "           ,[CMDValorMovimiento]               "
                + "           ,[CMDValorBaseMovimiento]           "
                + "           ,[CMDValorMonedaMovimiento]         "
                + "           ,[CMDNaturalezaMovimiento]          "
                + "           ,[CMDOrigenMovimiento])             "
                + "     VALUES    (                               "
                + getAnoMovimientoSoftLand() + ","
                + getPeriodoMovimientoSoftLand() + ","
                + getIdComprobanteContableSoftLand() + ","
                + getIdPrefijoMovimientoSoftLand() + ","
                + getIdDctoPrefijoSoftLand() + ","
                + getFechaDctoSoftLand() + ","
                + getItemMovimientoSoftLand() + ","
                + getIdSubcuenta() + ","
                + getStrIdLocalSoftLand() + ","
                + getCodigoMonedaMovimientoSoftLand() + ","
                + getIdCliente() + ","
                + getIdSucursalSoftLand() + ","
                + getIdCliente() + ","
                + getIdPrefijoMovimientoSoftLand() + ","
                + getIdDctoPrefijoSoftLand() + ","
                + getComentarioMovimientoSoftLand() + ","
                + getVrBase() + ","
                + getVrBaseContableRedondeo() + ","
                + getVrTotal() + ","
                + getNombreAsientoSoftland() + ","
                + getOrigenMovimientoSoftland() + ")";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcSoftlandAccess.getConnection();

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
            jdbcSoftlandAccess.cleanup(connection, selectStatement, null);
            return okIngresar;
        }
    }

     // ingresaPrefijo_GTERCEROS_12
    public boolean ingresaPrefijo_GTERCEROS_12(String xBdNameContable) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        //
        String selectStr =
                "INSERT INTO [" + xBdNameContable + "].[dbo].[CMDMOVIMIENTO]"
                + "           ([CMDAnoMovimiento]                 "
                + "           ,[CMDPeriodoMovimiento]             "
                + "           ,[CMDComprobanteMovimiento]         "
                + "           ,[CMDPrefijoMovimiento]             "
                + "           ,[CMDDocumentoMovimiento]           "
                + "           ,[CMDFechaMovimiento]               "
                + "           ,[CMDItemMovimiento]                "
                + "           ,[CMDCodigoCuentaMovimiento]        "
                + "           ,[CMDCodCentroCostosMovimiento]     "
                + "           ,[CMDCodigoMonedaMovimiento]        "
                + "           ,[CMDIdentificadorUnoMovimiento]    "
                + "           ,[CMDSucursalMovimiento]            "
                + "           ,[CMDIdentificadorDosMovimiento]    "
                + "           ,[CMDPrefijoRefmovimiento]          "
                + "           ,[CMDDocumentoRefMovimiento]        "
                + "           ,[CMDComentariosMovimiento]         "
                + "           ,[CMDValorMovimiento]               "
                + "           ,[CMDValorBaseMovimiento]           "
                + "           ,[CMDValorMonedaMovimiento]         "
                + "           ,[CMDNaturalezaMovimiento]          "
                + "           ,[CMDOrigenMovimiento])             "
                + "     VALUES    (                               "
                + getAnoMovimientoSoftLand() + ","
                + getPeriodoMovimientoSoftLand() + ","
                + getIdComprobanteContableSoftLand() + ","
                + getIdPrefijoMovimientoSoftLand() + ","
                + getIdDctoEnteroSoftLand() + ","
                + getFechaDctoSoftLand() + ","
                + getItemMovimientoSoftLand() + ","
                + getIdSubcuenta() + ","
                + getStrIdLocalSoftLand() + ","
                + getCodigoMonedaMovimientoSoftLand() + ","
                + getIdCliente() + ","
                + getIdSucursalSoftLand() + ","
                + getIdCliente() + ","
                + getIdPrefijoMovimientoSoftLand() + ","
                + getIdDctoNitCCPrefijoSoftLand() + ","
                + getComentarioMovimientoSoftLand() + ","
                + getVrBase() + ","
                + getVrBaseContableRedondeo() + ","
                + getVrTotal() + ","
                + getNombreAsientoSoftland() + ","
                + getOrigenMovimientoSoftland() + ")";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcSoftlandAccess.getConnection();

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
            jdbcSoftlandAccess.cleanup(connection, selectStatement, null);
            return okIngresar;
        }
    }



    // Metodo listaDctoComprobante
    public FachadaDctoBean listaDctoComprobante() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        // Variable de fachada de los datos
        FachadaDctoBean fachadaBean = new FachadaDctoBean();

        Connection connection = null;

        String selectStr =
" SELECT tmpDctosContableDetalle.idLocal          " +
"       ,tmpDctosContableDetalle.idTipoOrden      " +
"       ,tmpDctosContableDetalle.idDcto           " +
"       ,tmpDctosContableDetalle.idDctoNitCC      " +
"       ,tmpDctosContableDetalle.idCliente        " +
"       ,tmpDctosContableDetalle.fechaDcto        " +
"       ,tmpDctosContableDetalle.vrMovimiento     " +
"                                   AS vrBase 	  " +
"       ,tmpDctosContableDetalle.idSubcuenta      " +
"       ,tmpDctosContableDetalle.idAsiento        " +
"       ,tmpDctosContableDetalle.idComprobante    " +
"       ,(CASE WHEN                               " +
"          tmpDctosContableDetalle.idAsiento = 1  " +
"        THEN              'DNO'                  " +
"        ELSE              'CNO'                  " +
"        END)  AS nombreAsiento			  " +
"  ,ISNULL(( SELECT TOP 1                         " +
"     tmpDctosContableDetalle.vrMovimiento /      " +
"     (tblContableRetencion.porcentajeRetencion   " +
"                                    / 100)       " +
"            FROM tblContableRetencion            " +
"    WHERE tblContableRetencion.idSubcuenta =     " +
"        tmpDctosContableDetalle.idSubcuenta ),0) " +
"                            AS vrBaseContable    " +
" FROM tmpDctosContableDetalle                    " +
" ORDER BY  tmpDctosContableDetalle.idLocal       " +
"          ,tmpDctosContableDetalle.idTipoOrden   " +
"          ,tmpDctosContableDetalle.idDcto        " +
"          ,tmpDctosContableDetalle.idCliente     " +
"          ,tmpDctosContableDetalle.idAsiento     " ;


        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcSoftlandAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            //
            while (rs.next()) {

                //
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setIdDcto(rs.getInt("idDcto"));
                fachadaBean.setIdDctoNitCC(rs.getString("idDctoNitCC"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setIdSubcuenta(rs.getString("idSubcuenta"));
                fachadaBean.setIdAsiento(rs.getInt("idAsiento"));
                fachadaBean.setIdComprobanteContable(rs.getInt("idComprobante"));
                fachadaBean.setFechaDcto(rs.getString("fechaDcto"));
                fachadaBean.setVrBase(rs.getDouble("vrMovimiento"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setVrBaseContable(rs.getDouble("vrBaseContable"));
                fachadaBean.setNombreAsiento(rs.getString("nombreAsiento"));

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

            // Cierra la conexion a la base de datos limpiando la statement usada y la
            jdbcSoftlandAccess.cleanup(connection, selectStatement, null);
            return fachadaBean;
        }
    }

}
