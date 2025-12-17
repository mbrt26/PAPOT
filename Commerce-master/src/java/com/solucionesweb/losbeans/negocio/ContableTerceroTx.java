package com.solucionesweb.losbeans.negocio;

// Importa la clase helper JDBCAccess que contiene los accesos a la base de datos
import com.solucionesweb.lasayudas.JDBCSoftlandAccess;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaTerceroBean;

// Importa los paquetes de java
import java.sql.*;
import java.io.*;
import java.io.IOException.*;
import java.util.*;

//
import org.kxml.*;
import org.kxml.parser.*;

// Importa los paquetes de javax
import javax.naming.*;

// Importa la interface de las constantes
import com.solucionesweb.losbeans.utilidades.IConstantes;

public class ContableTerceroTx extends FachadaTerceroBean
        implements Serializable, IConstantes {

    // Nombre JNDI del Data Source que esta clase requiere
    private static final String DATA_SOURCE_NAME = "SoftlandAccess";

    // Clase helper usada para acceder a la base de datos
    private JDBCSoftlandAccess jdbcSoftlandAccess;

    //
    Vector contenedorLog = new Vector();
    
    // Variable de fachada de los datos
    FachadaTerceroBean fachadaTerceroLog;

    // Metodo constructor por defecto sin parametros
    public ContableTerceroTx() {

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


        String selectStr =
                "INSERT INTO [" + xBdNameContable + "].[dbo].[GTERCEROS]"
                + "           ([GBLIdentificadorUnoTerceros]       "
                + "           ,[GBLSucursalTerceros]               "
                + "           ,[GBLRazonSocialTerceros]            "
                + "           ,[GBLDireccionTerceros]              "
                + "           ,[GBLCiudadTerceros]                 "
                + "           ,[GBLTelefono1Terceros]              "
                + "           ,[GBLTelefono2Terceros]              "
                + "           ,[GBLFaxTerceros]                    "
                + "           ,[GBLEMailTerceros]                  "
                + "           ,[GBLContactoTerceros]               "
                + "           ,[GBLPaisTerceros]                   "
                + "           ,[GBLFechaIngresoTerceros]           "
                + "           ,[GBLRepresentanteLegalTerceros]     "
                + "           ,[GBLFechaRetiroTerceros]            "
                + "           ,[GBLIdentificadorClienteTerceros]   "
                + "           ,[GBLIdentificadorAcreedorTerceros]  "
                + "           ,[GBLIdentificadorProveedorTerceros] "
                + "           ,[GBLIdentificadorEmpleadoTerceros]  "
                + "           ,[GBLIdentificadorDeudorTerceros]    "
                + "           ,[GBLPersonaNaturalTerceros]         "
                + "           ,[GBLAutorretenedorTerceros]         "
                + "           ,[GBLRegimenTributarioTerceros]      "
                + "           ,[GCODMUNICIPIOTERCERO]              "
                + "           ,[GCODDEPARTAMENTOTERCERO]           "
                + "           ,[GBLDEPARTAMENTOTERCERO])           "
                + "     VALUES (                                   "
                + getIdClienteSoftLand() + ","
                + getIdSucursalSoftLand() + ","
                + getNombreTerceroSoftLand() + ","
                + getDireccionTerceroSoftLand() + ","
                + getCiudadTerceroSoftLand() + ","
                + getTelefonoFijoSoftLand() + ","
                + getTelefonoCelularSoftLand() + ","
                + getTelefonoFaxSoftLand() + ","
                + getEmailSoftLand() + ","
                + getContactoTerceroSoftLand() + ","
                + getPaisTerceroSoftLand() + ","
                + getTextoVacioSoftLand() + ","
                + getNombreTerceroSoftLand() + ","
                + getTextoVacioSoftLand() + ","
                + getTextoVacioSoftLand() + ","
                + getTextoVacioSoftLand() + ","
                + getTextoVacioSoftLand() + ","
                + getTextoVacioSoftLand() + ","
                + getTextoVacioSoftLand() + ","
                + getIdPersonaSoftLand() + ","
                + getIdAutoRetenedorSoftLand() + ","
                + getIdRegimenSoftLand() + ","
                + getIdCiudadSoftLand() + ","
                + getIdDptoSoftLand() + ","
                + getDepartamentoTerceroSoftLand() + ")";

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
    public boolean ingresa_CMDTERCEROS(String xBdNameContable) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;


        String selectStr =
                "INSERT INTO [" + xBdNameContable + "].[dbo].[CMDTERCEROS]"
                + "           ([CMDIdentificadorUnoTerceros] "
                + "           ,[CMDSucursalTerceros]         "
                + "           ,[CMDIdentificadorDosTerceros] "
                + "           ,[CMDClaseTerceros])           "
                + "     VALUES    (                          "
                + getIdClienteSoftLand() + ",                "
                + getIdSucursalSoftLand() + ",               "
                + getIdClienteSoftLand() + ",                "
                + getTipoIdTerceroSoftLand() + ")";
 
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

    // existe
    public boolean existeGTERCEROS(int xIdLocalPadre) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean existeLinea = false;

        Connection connection = null;

        //
        String selectStr = "";

        //
        if (xIdLocalPadre == 12 ) {

           selectStr =
           "SELECT [GTERCEROS].*                                 "
           + "FROM [C11].[dbo].[GTERCEROS]                       "
           + "WHERE [GTERCEROS].[GBLIdentificadorUnoTerceros] = '"
           + getIdCliente() + "'" ;

        }

        //
        if (xIdLocalPadre == 51 ) {

           selectStr =
           "SELECT [GTERCEROS].*                                   "
           + "FROM [CDC].[dbo].[GTERCEROS]                       "
           + "WHERE [GTERCEROS].[GBLIdentificadorUnoTerceros] = '"
           + getIdCliente() + "'" ;

        }

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcSoftlandAccess.getConnection();

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
            jdbcSoftlandAccess.cleanup(connection, selectStatement, null);
            return existeLinea;
        }
    }

    // existe
    public boolean existeCMDTERCEROS(int xIdLocalPadre) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean existeLinea = false;

        Connection connection = null;

        //
        String selectStr = "";

        //
        if (xIdLocalPadre == 12 ) {

            //
            selectStr =
                "SELECT [CMDTERCEROS].[CMDIdentificadorUnoTerceros]     "
                + "FROM [C11].[dbo].[CMDTERCEROS]                       "
                + "WHERE [CMDTERCEROS].[CMDIdentificadorUnoTerceros] = '"
                + getIdCliente().trim() + "'";
        }

        //
        if (xIdLocalPadre == 51 ) {

            //
            selectStr =
                "SELECT [CMDTERCEROS].[CMDIdentificadorUnoTerceros]     "
                + "FROM [CDC].[dbo].[CMDTERCEROS]                       "
                + "WHERE [CMDTERCEROS].[CMDIdentificadorUnoTerceros] = '"
                + getIdCliente().trim() + "'";
            
        }

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcSoftlandAccess.getConnection();

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
            jdbcSoftlandAccess.cleanup(connection, selectStatement, null);
            return existeLinea;
        }
    }

// traverse_TerceroTx
    public Vector traverse_TerceroTx(XmlParser parser,
            String indent,
            String xBdNameContable) throws Exception {

        boolean leave = false;

        //
        String idLocal = new String();
        String idTipoOrden = new String();
        String idLog = new String();
        String idTercero = new String();
        String idOrigenLog = new String();
        String digitoVerificacion = new String();
        String nombreTercero = new String();
        String direccionTercero = new String();
        String ciudadTercero = new String();
        String telefonoFijo = new String();
        String telefonoCelular = new String();
        String telefonoFax = new String();
        String email = new String();
        String contactoTercero = new String();
        String idPersona = new String();
        String idAutoRetenedor = new String();
        String idRegimen = new String();
        String idDptoCiudad = new String();
        String departamentoTercero = new String();
        String tipoIdTercero = new String();

        do {
            ParseEvent event = parser.read();
            ParseEvent pe;

            switch (event.getType()) {

                // For example, <CxC>
                case Xml.START_TAG:

                    // Pick up idagenda for display
                    if ("idLocal".equals(event.getName())) {
                        pe = parser.read();
                        idLocal = pe.getText();

                    }

                    // Pick up idagenda for display
                    if ("idTipoOrden".equals(event.getName())) {
                        pe = parser.read();
                        idTipoOrden = pe.getText();

                    }

                    // Pick up idagenda for display
                    if ("idLog".equals(event.getName())) {
                        pe = parser.read();
                        idLog = pe.getText();

                    }

                    // Pick up idagenda for display
                    if ("idTercero".equals(event.getName())) {
                        pe = parser.read();
                        idTercero = pe.getText();

                    }
                    
                    // Pick up idagenda for display
                    if ("idOrigenLog".equals(event.getName())) {
                        pe = parser.read();
                        idOrigenLog = pe.getText();

                    }

                    // Pick up clave for display
                    if ("digitoVerificacion".equals(event.getName())) {
                        pe = parser.read();
                        digitoVerificacion = pe.getText();

                    }

                    // Pick up clave for display
                    if ("nombreTercero".equals(event.getName())) {
                        pe = parser.read();
                        nombreTercero = pe.getText();
                    }

                    // Pick up clave for display
                    if ("direccionTercero".equals(event.getName())) {
                        pe = parser.read();
                        direccionTercero = pe.getText();
                    }

                    // Pick up clave for display
                    if ("ciudadTercero".equals(event.getName())) {
                        pe = parser.read();
                        ciudadTercero = pe.getText();

                    }

                    // Pick up clave for display
                    if ("telefonoFijo".equals(event.getName())) {
                        pe = parser.read();
                        telefonoFijo = pe.getText();
                    }

                    // Pick up clave for display
                    if ("telefonoCelular".equals(event.getName())) {
                        pe = parser.read();
                        telefonoCelular = pe.getText();

                    }

                    // Pick up clave for display
                    if ("telefonoFax".equals(event.getName())) {
                        pe = parser.read();
                        telefonoFax = pe.getText();

                    }

                    // Pick up clave for display
                    if ("email".equals(event.getName())) {
                        pe = parser.read();
                        email = pe.getText();

                    }

                    // Pick up clave for display
                    if ("contactoTercero".equals(event.getName())) {
                        pe = parser.read();
                        contactoTercero = pe.getText();

                    }

                    // Pick up clave for display
                    if ("idPersona".equals(event.getName())) {
                        pe = parser.read();
                        idPersona = pe.getText();

                    }

                    // Pick up clave for display
                    if ("idAutoRetenedor".equals(event.getName())) {
                        pe = parser.read();
                        idAutoRetenedor = pe.getText();

                    }

                    // Pick up clave for display
                    if ("idRegimen".equals(event.getName())) {
                        pe = parser.read();
                        idRegimen = pe.getText();

                    }

                    // Pick up clave for display
                    if ("idDptoCiudad".equals(event.getName())) {
                        pe = parser.read();
                        idDptoCiudad = pe.getText();

                    }

                    // Pick up idlocal for display
                    if ("departamentoTercero".equals(event.getName())) {
                        pe = parser.read();
                        departamentoTercero = pe.getText();
                        
                    }

                    if ("tipoIdTercero".equals(event.getName())) {
                        pe = parser.read();
                        tipoIdTercero = pe.getText();

                        //
                        setIdLocal(idLocal);
                        setIdTipoOrden(idTipoOrden);
                        setIdLog(idLog);
                        setIdCliente(idTercero.trim());
                        setIdOrigenLog(idOrigenLog);
                        setDigitoVerificacion(digitoVerificacion);
                        setNombreTercero(nombreTercero);
                        setDireccionTercero(direccionTercero);
                        setCiudadTercero(ciudadTercero);
                        setTelefonoFijo(telefonoFijo);
                        setTelefonoCelular(telefonoCelular);
                        setTelefonoFax(telefonoFax);
                        setEmail(email);
                        setContactoTercero(contactoTercero);
                        setIdPersona(idPersona);
                        setIdAutoRetenedor(idAutoRetenedor);
                        setIdRegimen(idRegimen);
                        setIdDptoCiudad(idDptoCiudad);
                        setDepartamentoTercero(departamentoTercero);
                        setTipoIdTercero(tipoIdTercero);

                        //
                        //boolean xExisteGTE
                        //               = this.existeGTERCEROS(xIdLocalPadre) ;

                        //
                        this.ingresa_GTERCEROS(xBdNameContable);
                        this.ingresa_CMDTERCEROS(xBdNameContable);


                        /*
                        if (!xExisteGTE) {

                            //
                            this.ingresa_GTERCEROS(xBdNameContable);

                        }

                        //
                        boolean xExisteCMD
                                     = this.existeCMDTERCEROS(xIdLocalPadre) ;
                        //
                        if (!xExisteCMD) {

                            //
                            this.ingresa_CMDTERCEROS(xBdNameContable);

                        }*/

                        //
                        fachadaTerceroLog = new FachadaTerceroBean();

                        //
                        fachadaTerceroLog.setIdLocal(idLocal);
                        fachadaTerceroLog.setIdTipoOrden(idTipoOrden);
                        fachadaTerceroLog.setIdLog(idLog);
                        fachadaTerceroLog.setIdCliente(idTercero);
                        fachadaTerceroLog.setIdOrigenLog(idOrigenLog);                        
                        fachadaTerceroLog.setDigitoVerificacion(digitoVerificacion);
                        fachadaTerceroLog.setNombreTercero(nombreTercero);
                        fachadaTerceroLog.setDireccionTercero(direccionTercero);
                        fachadaTerceroLog.setCiudadTercero(ciudadTercero);
                        fachadaTerceroLog.setTelefonoFijo(telefonoFijo);
                        fachadaTerceroLog.setTelefonoCelular(telefonoCelular);
                        fachadaTerceroLog.setTelefonoFax(telefonoFax);
                        fachadaTerceroLog.setEmail(email);
                        fachadaTerceroLog.setContactoTercero(contactoTercero);
                        fachadaTerceroLog.setIdPersona(idPersona);
                        fachadaTerceroLog.setIdAutoRetenedor(idAutoRetenedor);
                        fachadaTerceroLog.setIdRegimen(idRegimen);
                        fachadaTerceroLog.setIdDptoCiudad(idDptoCiudad);
                        fachadaTerceroLog.setDepartamentoTercero(departamentoTercero);
                        fachadaTerceroLog.setTipoIdTercero(tipoIdTercero);

                        //
                        contenedorLog.add(fachadaTerceroLog);

                    }

                    // recursion call for each <tag></tag>
                    this.traverse_TerceroTx(parser, "",
                                            xBdNameContable);
                    
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

        //
        return contenedorLog;
    }
}
