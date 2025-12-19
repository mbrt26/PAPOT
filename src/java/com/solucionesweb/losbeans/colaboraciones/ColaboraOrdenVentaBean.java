package com.solucionesweb.losbeans.colaboraciones;

// Importa la clase helper JDBCAccess que contiene los accesos a la base de datos
import com.solucionesweb.lasayudas.JDBCAccess;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaOrdenVenta;

// Importa los paquetes de java
import java.sql.*;
import java.io.IOException.*;

// Importa los paquetes de javax
import java.util.ArrayList;
import java.util.Collection;
import java.util.Vector;
import javax.naming.*;

public class ColaboraOrdenVentaBean extends FachadaOrdenVenta {

    // Nombre JNDI del Data Source que esta clase requiere
    private static final String DATA_SOURCE_NAME = "CommerceAccess";
    // Clase helper usada para acceder a la base de datos
    private JDBCAccess jdbcAccess;
    //
    private String strIdReferencia;
    private String nombrePlu;
    private double cantidad;
    private double porcentajeIva;
    private double vrVentaUnitario;
    private double vrVentaSinIva;
    private double porcentajeDscto;
    private String nombreMarca;
    private String nombreCategoria;
    private double vrCosto;
    private double vrCostoConIva;
    private double vrCostoSinIva;
    private double vrVentaConIvaSinDscto;
    private double vrCostoNegociado;
    private double vrCostoNegociadoConIva;
    private double vrCostoNegociadoSinIva;

    // Metodo constructor por defecto sin parametros
    public ColaboraOrdenVentaBean() {

        // Al instanciar el Bean establece la conexion a la base de datos
        jdbcAccess = new JDBCAccess(DATA_SOURCE_NAME);
    }

    public ColaboraOrdenVentaBean(String strIdReferencia,
            String nombrePlu,
            double cantidad,
            double porcentajeIva,
            double vrVentaUnitario,
            double vrVentaSinIva,
            double porcentajeDscto,
            String nombreMarca,
            String nombreCategoria,
            double vrCosto,
            double vrCostoConIva,
            double vrCostoSinIva,
            double vrVentaConIvaSinDscto,
            double vrCostoNegociado,
            double vrCostoNegociadoConIva,
            double vrCostoNegociadoSinIva) {
        this.strIdReferencia = strIdReferencia;
        this.nombrePlu = nombrePlu;
        this.cantidad = cantidad;
        this.porcentajeIva = porcentajeIva;
        this.vrVentaUnitario = vrVentaUnitario;
        this.vrVentaSinIva = vrVentaSinIva;
        this.porcentajeDscto = porcentajeDscto;
        this.nombreMarca = nombreMarca;
        this.nombreCategoria = nombreCategoria;
        this.vrCosto = vrCosto;
        this.vrCostoConIva = vrCostoConIva;
        this.vrCostoSinIva = vrCostoSinIva;
        this.vrVentaConIvaSinDscto = vrVentaConIvaSinDscto;
        this.vrCostoNegociado = vrCostoNegociado;
        this.vrCostoNegociadoConIva = vrCostoNegociadoConIva;
        this.vrCostoNegociadoSinIva = vrCostoNegociadoSinIva;
    }

    // listaUnLocal
    public FachadaOrdenVenta listaUnLocal() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        // Variable de fachada de los datos
        FachadaOrdenVenta fachadaOrdenVenta = new FachadaOrdenVenta();

        //
        boolean validaOk = false;
        Connection connection = null;

        //
        String selectStr =
                "SELECT tblLocales.idLocal,                   "
                + "             tblLocales.nombreLocal,         "
                + "             tblLocales.razonSocial,         "
                + "             tblLocales.nit,                 "
                + "             tblLocales.direccion,           "
                + "             tblLocales.ciudad,              "
                + "             tblLocales.telefono,            "
                + "             tblLocales.email                "
                + "FROM tblLocales,                             "
                + "     tblDctosOrdenes ,                       "
                + "     tblDctosOrdenesDetalle                  "
                + "WHERE tblDctosOrdenes.idOrden         =      "
                + "            tblDctosOrdenesDetalle.idOrden   "
                + "AND   tblDctosOrdenes.idTipoOrden     =      "
                + "      tblDctosOrdenesDetalle.idTipoOrden     "
                + "AND   tblDctosOrdenes.idLocal         =      "
                + "            tblDctosOrdenesDetalle.idLocal   "
                + "AND   tblDctosOrdenes.idLog           =      "
                + getIdLog() + "                                "
                + "AND   tblDctosOrdenes.idTipoOrden     =      "
                + getIdTipoOrden() + "                          "
                + "GROUP BY tblLocales.idLocal,                 "
                + "          tblLocales.nombreLocal,            "
                + "          tblLocales.razonSocial,            "
                + "          tblLocales.nit,                    "
                + "          tblLocales.direccion,              "
                + "          tblLocales.ciudad,                 "
                + "          tblLocales.telefono,               "
                + "          tblLocales.email                   ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            while (rs.next()) {

                fachadaOrdenVenta = new FachadaOrdenVenta();

                fachadaOrdenVenta.setIdLocal(rs.getInt("idLocal"));
                fachadaOrdenVenta.setNombreLocal(rs.getString("nombreLocal"));
                fachadaOrdenVenta.setRazonSocial(rs.getString("razonSocial"));
                fachadaOrdenVenta.setNit(rs.getString("nit"));
                fachadaOrdenVenta.setDireccion(rs.getString("direccion"));
                fachadaOrdenVenta.setCiudad(rs.getString("ciudad"));
                fachadaOrdenVenta.setTelefono(rs.getString("telefono"));
                fachadaOrdenVenta.setEmail(rs.getString("email"));

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
            return fachadaOrdenVenta;

        }
    }

    // listaUnLocalOrden
    public FachadaOrdenVenta listaUnLocalOrden() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        // Variable de fachada de los datos
        FachadaOrdenVenta fachadaOrdenVenta = new FachadaOrdenVenta();

        Connection connection = null;

        //
        String selectStr =
                "SELECT tblLocales.idLocal,                   "
                + "       tblLocales.nombreLocal,               "
                + "       tblLocales.razonSocial,               "
                + "       tblLocales.nit,                       "
                + "       tblLocales.direccion,                 "
                + "       tblLocales.ciudad,                    "
                + "       tblLocales.telefono,                  "
                + "       tblLocales.email,                     "
                + "       tblLocales.txtFactura,                "
                + "       tblLocales.resolucion,                "
                + "       tblLocales.rango,                     "
                + "       tblLocales.regimen                    "
                + "FROM tblLocales,                             "
                + "     tblDctosOrdenes ,                       "
                + "     tblDctosOrdenesDetalle                  "
                + "WHERE tblDctosOrdenes.idOrden         =      "
                + "            tblDctosOrdenesDetalle.idOrden   "
                + "AND   tblDctosOrdenes.idTipoOrden     =      "
                + "      tblDctosOrdenesDetalle.idTipoOrden     "
                + "AND   tblDctosOrdenes.idLocal         =      "
                + "            tblDctosOrdenesDetalle.idLocal   "
                + "AND   tblDctosOrdenes.idLocal         =      "
                + "                        tblLocales.idLocal   "
                + "AND   tblDctosOrdenes.idOrden         =      "
                + getIdOrden() + "                              "
                + "AND   tblDctosOrdenes.idTipoOrden     =      "
                + getIdTipoOrden() + "                          "
                + "AND   tblDctosOrdenes.idLocal         =      "
                + getIdLocal() + "                              "
                + "GROUP BY tblLocales.idLocal,                 "
                + "       tblLocales.nombreLocal,               "
                + "       tblLocales.razonSocial,               "
                + "       tblLocales.nit,                       "
                + "       tblLocales.direccion,                 "
                + "       tblLocales.ciudad,                    "
                + "       tblLocales.telefono,                  "
                + "       tblLocales.email,                     "
                + "       tblLocales.txtFactura,                "
                + "       tblLocales.resolucion,                "
                + "       tblLocales.rango,                     "
                + "       tblLocales.regimen                    ";


        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            while (rs.next()) {

                fachadaOrdenVenta = new FachadaOrdenVenta();

                fachadaOrdenVenta.setIdLocal(rs.getInt("idLocal"));
                fachadaOrdenVenta.setNombreLocal(rs.getString("nombreLocal"));
                fachadaOrdenVenta.setRazonSocial(rs.getString("razonSocial"));
                fachadaOrdenVenta.setNit(rs.getString("nit"));
                fachadaOrdenVenta.setDireccion(rs.getString("direccion"));
                fachadaOrdenVenta.setCiudad(rs.getString("ciudad"));
                fachadaOrdenVenta.setTelefono(rs.getString("telefono"));
                fachadaOrdenVenta.setEmail(rs.getString("email"));
                fachadaOrdenVenta.setTxtFactura(rs.getString("txtFactura"));
                fachadaOrdenVenta.setResolucion(rs.getString("resolucion"));
                fachadaOrdenVenta.setRango(rs.getString("rango"));
                fachadaOrdenVenta.setRegimen(rs.getString("regimen"));

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
            return fachadaOrdenVenta;

        }
    }

    // listaUnLocalResolucion
    public FachadaOrdenVenta listaUnLocalResolucion() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        // Variable de fachada de los datos
        FachadaOrdenVenta fachadaOrdenVenta = new FachadaOrdenVenta();

        Connection connection = null;

        //
        String selectStr =
                "SELECT tblLocales.idLocal,                     "
                + "       tblLocales.nombreLocal,               "
                + "       tblLocales.razonSocial,               "
                + "       tblLocales.nit,                       "
                + "       tblLocales.direccion,                 "
                + "       tblLocales.ciudad,                    "
                + "       tblLocales.telefono,                  "
                + "       tblLocales.fax AS telefonoFax,        "
                + "       tblLocales.email,                     "
                + "       tblLocales.txtFactura,                "
                + "(SELECT TOP 1 tblLocalesCaja.resolucion      "
                + " FROM         tblAgendaLogVisitas            "
                + " INNER JOIN tblLocalesCaja                   "
                + " ON tblAgendaLogVisitas.ipTx =               "
                + " tblLocalesCaja.ipLocal                      "
                + " WHERE  tblAgendaLogVisitas.IDLOG =          "
                + "   tblDctosOrdenes.idLog )                   "
                + "                           AS resolucion,    "
                + "       tblLocales.rango,                     "
                + "       tblLocales.regimen                    "
                + "FROM tblLocales,                             "
                + "     tblDctosOrdenes ,                       "
                + "     tblDctosOrdenesDetalle                  "
                + "WHERE tblDctosOrdenes.idOrden         =      "
                + "            tblDctosOrdenesDetalle.idOrden   "
                + "AND   tblDctosOrdenes.idTipoOrden     =      "
                + "      tblDctosOrdenesDetalle.idTipoOrden     "
                + "AND   tblDctosOrdenes.idLocal         =      "
                + "            tblDctosOrdenesDetalle.idLocal   "
                + "AND   tblDctosOrdenes.idLocal         =      "
                + "                        tblLocales.idLocal   "
                + "AND   tblDctosOrdenes.idOrden         =      "
                + getIdOrden() + "                              "
                + "AND   tblDctosOrdenes.idTipoOrden     =      "
                + getIdTipoOrden() + "                          "
                + "AND   tblDctosOrdenes.idLocal         =      "
                + getIdLocal() + "                              "
                + "GROUP BY tblLocales.idLocal,                 "
                + "       tblLocales.nombreLocal,               "
                + "       tblLocales.razonSocial,               "
                + "       tblLocales.nit,                       "
                + "       tblLocales.direccion,                 "
                + "       tblLocales.ciudad,                    "
                + "       tblLocales.telefono,                  "
                + "       tblLocales.fax,                       "
                + "       tblLocales.email,                     "
                + "       tblLocales.txtFactura,                "
                + "       tblLocales.resolucion,                "
                + "       tblLocales.rango,                     "
                + "       tblDctosOrdenes.idLog,                "
                + "       tblLocales.regimen                    ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            while (rs.next()) {

                fachadaOrdenVenta = new FachadaOrdenVenta();

                fachadaOrdenVenta.setIdLocal(rs.getInt("idLocal"));
                fachadaOrdenVenta.setNombreLocal(rs.getString("nombreLocal"));
                fachadaOrdenVenta.setRazonSocial(rs.getString("razonSocial"));
                fachadaOrdenVenta.setNit(rs.getString("nit"));
                fachadaOrdenVenta.setDireccion(rs.getString("direccion"));
                fachadaOrdenVenta.setCiudad(rs.getString("ciudad"));
                fachadaOrdenVenta.setTelefono(rs.getString("telefono"));
                fachadaOrdenVenta.setTelefonoFax(rs.getString("telefonoFax"));
                fachadaOrdenVenta.setEmail(rs.getString("email"));
                fachadaOrdenVenta.setTxtFactura(rs.getString("txtFactura"));
                fachadaOrdenVenta.setResolucion(rs.getString("resolucion"));
                fachadaOrdenVenta.setRango(rs.getString("rango"));
                fachadaOrdenVenta.setRegimen(rs.getString("regimen"));

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
            return fachadaOrdenVenta;

        }
    }

    // listaUnTercero
    public FachadaOrdenVenta listaUnTercero() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        // Variable de fachada de los datos
        FachadaOrdenVenta fachadaOrdenVenta = new FachadaOrdenVenta();

        //
        boolean validaOk = false;
        Connection connection = null;

        //
        String selectStr =
                "SELECT MAX(tblTerceros.idTercero)                     "
                + "                           AS idTercero,              "
                + "       MAX(tblTerceros.nombreTercero)                 "
                + "                      AS nombreTercero ,              "
                + "       MAX(RTRIM(LTRIM(tblTerceros.direccionTercero)))"
                + "                    AS direccionTercero,              "
                + "       MAX(tblTerceros.ciudadTercero) AS ciudad,      "
                + "       MAX(tblTerceros.telefonoFijo)  AS telefonoFijo,"
                + "       MAX(tblTerceros.idFormaPago)   AS formaPago,   "
                + "       tblTerceros.idCliente,                         "
                + "       MAX(tblTerceros.idRuta)        AS idRuta,      "
                + "       MAX(tblTerceros.email)         AS email        "
                + "FROM tblTerceros,                                     "
                + "     tblDctosOrdenes ,                                "
                + "     tblDctosOrdenesDetalle                           "
                + "WHERE tblDctosOrdenes.idOrden         =               "
                + "            tblDctosOrdenesDetalle.idOrden            "
                + "AND   tblDctosOrdenes.idTipoOrden     =               "
                + "      tblDctosOrdenesDetalle.idTipoOrden              "
                + "AND   tblDctosOrdenes.idLocal         =               "
                + "            tblDctosOrdenesDetalle.idLocal            "
                + "AND   tblDctosOrdenes.idLog           =               "
                + getIdLog() + "                                         "
                + "AND   tblDctosOrdenes.idTipoOrden     =               "
                + getIdTipoOrden() + "                                   "
                + "AND   tblTerceros.idCliente           =               "
                + "                   tblDctosOrdenes.idCliente          "
                + "GROUP BY tblTerceros.idCliente                        ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            while (rs.next()) {

                fachadaOrdenVenta = new FachadaOrdenVenta();

                //
                fachadaOrdenVenta.setIdTercero(rs.getDouble("idTercero"));
                fachadaOrdenVenta.setNombreTercero(
                        rs.getString("nombreTercero"));
                fachadaOrdenVenta.setDireccionTercero(
                        rs.getString("direccionTercero"));
                fachadaOrdenVenta.setCiudad(rs.getString("ciudad"));
                fachadaOrdenVenta.setTelefonoFijo(rs.getString("telefonoFijo"));
                fachadaOrdenVenta.setFormaPago(rs.getString("formaPago"));
                fachadaOrdenVenta.setIdRuta(rs.getString("idRuta"));
                fachadaOrdenVenta.setEmail(rs.getString("email"));
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
            return fachadaOrdenVenta;

        }
    }

    // listaUnTerceroOrden
    public FachadaOrdenVenta listaUnTerceroOrden() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        // Variable de fachada de los datos
        FachadaOrdenVenta fachadaOrdenVenta = new FachadaOrdenVenta();

        //
        Connection connection = null;

        //
        String selectStr =
                "SELECT MAX(tblTerceros.idTercero)                     "
                + "                           AS idTercero,              "
                + "       MAX(tblTerceros.nombreTercero)                 "
                + "                      AS nombreTercero ,              "
                + "       MAX(RTRIM(LTRIM(tblTerceros.direccionTercero)))"
                + "                    AS direccionTercero,              "
                + "      (SELECT tblCiudades.nombreCiudad + ' / ' +      "
                + "                 tblCiudades.nombreDpto               "
                + "       FROM tblCiudades                               "
                + "       WHERE tblCiudades.idCiudad =                   "
                + "                tblTerceros.idDptoCiudad) AS ciudad,  "
                + "       MAX(tblTerceros.telefonoFijo)  AS telefonoFijo,"
                + "       MAX(tblTerceros.idFormaPago)   AS formaPago,   "
                + "       tblTerceros.idCliente,                         "
                + "       MAX(tblTerceros.idRuta)        AS idRuta,      "
                + "       MAX(tblTerceros.email)         AS email,       "
                + "       MAX(tblTerceros.digitoVerificacion)            "
                + "                                AS digitoVerificacion "
                + "FROM tblTerceros,                                     "
                + "     tblDctosOrdenes ,                                "
                + "     tblDctosOrdenesDetalle                           "
                + "WHERE tblDctosOrdenes.idOrden         =               "
                + "            tblDctosOrdenesDetalle.idOrden            "
                + "AND   tblDctosOrdenes.idTipoOrden     =               "
                + "      tblDctosOrdenesDetalle.idTipoOrden              "
                + "AND   tblDctosOrdenes.idLocal         =               "
                + "            tblDctosOrdenesDetalle.idLocal            "
                + "AND   tblDctosOrdenes.idOrden         =               "
                + getIdOrden() + "                                       "
                + "AND   tblDctosOrdenes.idTipoOrden     =               "
                + getIdTipoOrden() + "                                   "
                + "AND   tblTerceros.idCliente           =               "
                + "                   tblDctosOrdenes.idCliente          "
                + "GROUP BY tblTerceros.idCliente,                       "
                + "         tblTerceros.idDptoCiudad                     ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            while (rs.next()) {

                fachadaOrdenVenta = new FachadaOrdenVenta();

                //
                fachadaOrdenVenta.setIdTercero(rs.getDouble("idTercero"));
                fachadaOrdenVenta.setNombreTercero(
                        rs.getString("nombreTercero"));
                fachadaOrdenVenta.setDireccionTercero(
                        rs.getString("direccionTercero"));
                fachadaOrdenVenta.setCiudad(rs.getString("ciudad"));
                fachadaOrdenVenta.setTelefonoFijo(rs.getString("telefonoFijo"));
                fachadaOrdenVenta.setFormaPago(rs.getString("formaPago"));
                fachadaOrdenVenta.setIdRuta(rs.getString("idRuta"));
                fachadaOrdenVenta.setEmail(rs.getString("email"));
                fachadaOrdenVenta.setDigitoVerificacion(
                        rs.getInt("digitoVerificacion"));


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
            return fachadaOrdenVenta;

        }
    }

    // listaOrdenResurtido
    public FachadaOrdenVenta listaOrdenResurtido() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        // Variable de fachada de los datos
        FachadaOrdenVenta fachadaOrdenVenta = new FachadaOrdenVenta();

        //
        Connection connection = null;

        //
        String selectStr =
                "SELECT tblLocales.idLocal,                     "
                + "       MAX(tblLocales.nombreLocal)             "
                + "                              AS nombreLocal , "
                + "       MAX(RTRIM(LTRIM(tblLocales.direccion))) "
                + "                                 AS direccion, "
                + "       MAX(tblLocales.ciudad) AS ciudad,       "
                + "       MAX(tblLocales.telefono)  AS telefono,  "
                + "       MAX(0)   AS formaPago,                  "
                + "       MAX(0) AS idRuta,                       "
                + "       MAX(tblLocales.email)         AS email, "
                + "       MAX(0)            AS digitoVerificacion "
                + "FROM tblLocales,                               "
                + "     tblDctosOrdenes ,                         "
                + "     tblDctosOrdenesDetalle                    "
                + "WHERE tblDctosOrdenes.idOrden         =        "
                + "            tblDctosOrdenesDetalle.idOrden     "
                + "AND   tblDctosOrdenes.idTipoOrden     =        "
                + "      tblDctosOrdenesDetalle.idTipoOrden       "
                + "AND   tblDctosOrdenes.idLocal         =        "
                + "            tblDctosOrdenesDetalle.idLocal     "
                + "AND   tblDctosOrdenes.idOrden         =        "
                + getIdOrden() + "                                "
                + "AND   tblDctosOrdenes.idTipoOrden     =        "
                + getIdTipoOrden() + "                            "
                + "AND   tblLocales.idLocal              =        "
                + getIdLocal() + "                                "
                + "GROUP BY tblLocales.idLocal                    ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            while (rs.next()) {

                fachadaOrdenVenta = new FachadaOrdenVenta();

                //
                fachadaOrdenVenta.setIdTercero(rs.getDouble("idLocal"));
                fachadaOrdenVenta.setNombreTercero(
                        rs.getString("nombreLocal"));
                fachadaOrdenVenta.setDireccionTercero(
                        rs.getString("direccion"));
                fachadaOrdenVenta.setCiudad(rs.getString("ciudad"));
                fachadaOrdenVenta.setTelefonoFijo(rs.getString("telefono"));
                fachadaOrdenVenta.setFormaPago(rs.getString("formaPago"));
                fachadaOrdenVenta.setIdRuta(rs.getString("idRuta"));
                fachadaOrdenVenta.setEmail(rs.getString("email"));
                fachadaOrdenVenta.setDigitoVerificacion(
                        rs.getInt("digitoVerificacion"));

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
            return fachadaOrdenVenta;

        }
    }

    // liquidaUnaOrden
    public FachadaOrdenVenta liquidaUnaOrden() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        // Variable de fachada de los datos
        FachadaOrdenVenta fachadaOrdenVenta = new FachadaOrdenVenta();

        //
        boolean validaOk = false;
        Connection connection = null;

        //
        String selectStr =
                "SELECT COUNT(tblDctosOrdenesDetalle.IDPLU)                    "
                + "                            AS cantidadArticulos,             "
                + "       SUM(tblDctosOrdenesDetalle.cantidad        *           "
                + "           tblDctosOrdenesDetalle.vrVentaUnitario )           "
                + "                                           AS vrVentaSinIva,  "
                + "      SUM(tblDctosOrdenesDetalle.cantidad         *           "
                + "           tblDctosOrdenesDetalle.vrVentaUnitario *           "
                + "          (1 + (tblDctosOrdenesDetalle.porcentajeIva/100)))   "
                + "                                           AS vrVentaConIva,  "
                + "      SUM(tblDctosOrdenesDetalle.cantidad                  *  "
                + "          tblDctosOrdenesDetalle.vrVentaUnitario    *         "
                + "          (1 + (tblDctosOrdenesDetalle.porcentajeIva/100))) - "
                + "          SUM(tblDctosOrdenesDetalle.cantidad        *        "
                + "          tblDctosOrdenesDetalle.vrVentaUnitario      )       "
                + "                                                    AS vrIva, "
                + "      SUM(tblDctosOrdenesDetalle.pesoTeorico   *              "
                + "          tblDctosOrdenesDetalle.cantidad)                    "
                + "                                         AS pesoTeoricoTotal, "
                + "      MAX(tblDctosOrdenes.fechaOrden)                         "
                + "                                               AS fechaOrden, "
                + "      MAX(tblDctosOrdenes.idOrden) AS idOrden,                "
                + "      MAX(tblDctosOrdenes.idLocal) AS idLocal,                "
                + "      MAX(tblDctosOrdenes.idTipoOrden) AS idTipoOrden         "
                + "FROM tblDctosOrdenes,                                         "
                + "     tblDctosOrdenesDetalle                                   "
                + "WHERE tblDctosOrdenes.IDORDEN     =                           "
                + "                          tblDctosOrdenesDetalle.idOrden      "
                + "AND   tblDctosOrdenes.IDTIPOORDEN =                           "
                + "                       tblDctosOrdenesDetalle.idTipoOrden     "
                + "AND   tblDctosOrdenes.IDTIPOORDEN =                           "
                + getIdTipoOrden() + "                                           "
                + "AND   tblDctosOrdenes.IDLOG       =                           "
                + getIdLog() + "                                                 "
                + "AND   tblDctosOrdenes.IDLOCAL     =                           "
                + "                          tblDctosOrdenesDetalle.idLocal      ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            while (rs.next()) {

                fachadaOrdenVenta = new FachadaOrdenVenta();

                //
                fachadaOrdenVenta.setCantidadArticulos(rs.getInt("cantidadArticulos"));
                fachadaOrdenVenta.setVrVentaSinIva(rs.getDouble("vrVentaSinIva"));
                fachadaOrdenVenta.setVrVentaConIva(rs.getDouble("vrVentaConIva"));
                fachadaOrdenVenta.setVrIva(rs.getDouble("vrIva"));
                fachadaOrdenVenta.setPesoTeoricoTotal(rs.getDouble("pesoTeoricoTotal"));
                fachadaOrdenVenta.setFechaOrden(rs.getString("fechaOrden"));
                fachadaOrdenVenta.setIdOrden(rs.getInt("idOrden"));
                fachadaOrdenVenta.setIdLocal(rs.getInt("idLocal"));
                fachadaOrdenVenta.setIdTipoOrden(rs.getInt("idTipoOrden"));

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
            return fachadaOrdenVenta;

        }
    }

    // liquidaUnPedidoOrden
    public FachadaOrdenVenta liquidaUnPedidoOrden() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        // Variable de fachada de los datos
        FachadaOrdenVenta fachadaOrdenVenta = new FachadaOrdenVenta();

        //
        boolean validaOk = false;
        Connection connection = null;

        //
        String selectStr =
                "SELECT COUNT(tblDctosOrdenesDetalle.idPlu)                    "
                + "                            AS cantidadArticulos,             "
                + "       SUM(tblDctosOrdenesDetalle.cantidad        *           "
                + "           tblDctosOrdenesDetalle.vrVentaUnitario )           "
                + "                                      AS vrVentaConIva,       "
                + "         SUM((tblDctosOrdenesDetalle.cantidad        *        "
                + "               tblDctosOrdenesDetalle.vrVentaUnitario) /      "
                + "   ( 1 + ( tblDctosOrdenesDetalle.porcentajeIva / 100)))      "
                + "                                       AS vrVentaSinIva,      "
                + "   SUM(((tblDctosOrdenesDetalle.cantidad        *		       "
                + "       tblDctosOrdenesDetalle.vrVentaUnitario) /		       "
                + " ( 1 + ( tblDctosOrdenesDetalle.porcentajeIva / 100))) *      "
                + " ( 1 - ( tblDctosOrdenesDetalle.vrDsctoPie / 100))     *      "
                + " ( 1 - ( tblDctosOrdenesDetalle.porcentajeDscto / 100))*      "
                + "        ( tblDctosOrdenesDetalle.porcentajeIva / 100 ))       "
                + "                                      AS vrIvaVenta,          "
                + "           SUM(((tblDctosOrdenesDetalle.cantidad       *      "
                + "              tblDctosOrdenesDetalle.vrVentaUnitario)  /      "
                + " ( 1 + ( tblDctosOrdenesDetalle.porcentajeIva / 100))) *      "
                + " ( 1 - ( tblDctosOrdenesDetalle.vrDsctoPie / 100))     *      "
                + " ( 1 - ( tblDctosOrdenesDetalle.porcentajeDscto / 100)))      "
                + "                                     AS vrVentaSinDscto,      "
                + "      SUM(tblDctosOrdenesDetalle.pesoTeorico   *              "
                + "          tblDctosOrdenesDetalle.cantidad)                    "
                + "                                         AS pesoTeoricoTotal, "
                + "      MAX(tblDctosOrdenes.fechaOrden)                         "
                + "                                               AS fechaOrden, "
                + "      MAX(tblDctosOrdenes.idOrden) AS idOrden,                "
                + "      MAX(tblDctosOrdenes.idLocal) AS idLocal,                "
                + "      MAX(tblDctosOrdenes.idTipoOrden) AS idTipoOrden,        "
                + "      MAX(tblDctosOrdenes.idCliente) AS idCliente,            "
                + "      MAX(tblDctos.idDcto) AS idDcto,                         "
                + "      MAX(tblDctos.fechaDcto) AS fechaDcto,                   "
                + "      MAX(tblDctos.idDctoNitCC) AS idDctoNitCC,               "
                + "      MAX(tblDctos.nombreTercero) AS nombreTercero,           "
                + "      SUM(tblDctosOrdenesDetalle.cantidad         *           "
                + "           tblDctosOrdenesDetalle.vrCosto )                   "
                + "                                           AS vrCostoConIva,  "
                + "      SUM(tblDctosOrdenesDetalle.cantidad         *           "
                + "           tblDctosOrdenesDetalle.vrCosto /                   "
                + "          (1 + (tblDctosOrdenesDetalle.porcentajeIva/100)))   "
                + "                                           AS vrCostoSinIva,  "
                + "      MIN(tblDctos.idDctoCruce) AS idDctoCruce,               "
                + "      MIN(tblDctos.diasPlazo) AS diasPlazo,                   "
                + "      MIN(tblDctosOrdenes.observacion) AS observacion         "
                + "FROM tblDctosOrdenes,                                         "
                + "     tblDctosOrdenesDetalle,                                  "
                + "     tblDctos                                                 "
                + "WHERE tblDctosOrdenes.IDORDEN     =                           "
                + "                          tblDctosOrdenesDetalle.idOrden      "
                + "AND   tblDctosOrdenes.IDTIPOORDEN =                           "
                + "                       tblDctosOrdenesDetalle.idTipoOrden     "
                + "AND   tblDctosOrdenes.idLocal     =                           "
                + "                       tblDctosOrdenesDetalle.idLocal         "
                + "AND   tblDctosOrdenes.IDORDEN     =                           "
                + "                          tblDctos.idOrden                    "
                + "AND   tblDctosOrdenes.IDTIPOORDEN =                           "
                + "                       tblDctos.idTipoOrden                   "
                + "AND   tblDctosOrdenes.idLocal     =                           "
                + "                       tblDctos.idLocal                       "
                + "AND   tblDctosOrdenes.IDTIPOORDEN =                           "
                + getIdTipoOrden() + "                                           "
                + "AND   tblDctosOrdenes.idOrden     =                           "
                + getIdOrden() + "                                               ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            while (rs.next()) {

                fachadaOrdenVenta = new FachadaOrdenVenta();

                //
                fachadaOrdenVenta.setCantidadArticulos(
                        rs.getInt("cantidadArticulos"));
                fachadaOrdenVenta.setVrVentaSinIva(
                        rs.getDouble("vrVentaSinIva"));
                fachadaOrdenVenta.setVrVentaConIva(
                        rs.getDouble("vrVentaConIva"));
                fachadaOrdenVenta.setVrIva(rs.getDouble("vrIvaVenta"));
                fachadaOrdenVenta.setPesoTeoricoTotal(
                        rs.getDouble("pesoTeoricoTotal"));
                fachadaOrdenVenta.setFechaOrden(rs.getString("fechaOrden"));
                fachadaOrdenVenta.setIdOrden(rs.getInt("idOrden"));
                fachadaOrdenVenta.setIdLocal(rs.getInt("idLocal"));
                fachadaOrdenVenta.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaOrdenVenta.setIdCliente(rs.getString("idCliente"));
                fachadaOrdenVenta.setIdDcto(rs.getInt("idDcto"));
                fachadaOrdenVenta.setFechaOrden(rs.getString("fechaDcto"));
                fachadaOrdenVenta.setIdDctoNitCC(rs.getString("idDctoNitCC"));
                fachadaOrdenVenta.setNombreTercero(
                        rs.getString("nombreTercero"));
                fachadaOrdenVenta.setVrCostoConIva(
                        rs.getDouble("vrCostoConIva"));
                fachadaOrdenVenta.setVrCostoSinIva(
                        rs.getDouble("vrCostoSinIva"));
                fachadaOrdenVenta.setIdDctoCruce(rs.getInt("idDctoCruce"));
                fachadaOrdenVenta.setDiasPlazo(rs.getInt("diasPlazo"));
                fachadaOrdenVenta.setObservacion(rs.getString("observacion"));
                fachadaOrdenVenta.setVrVentaSinDscto(
                        rs.getDouble("vrVentaSinDscto"));

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
            return fachadaOrdenVenta;

        }
    }

    // liquidaUnCotizacion
    public FachadaOrdenVenta liquidaUnCotizacion() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        // Variable de fachada de los datos
        FachadaOrdenVenta fachadaOrdenVenta = new FachadaOrdenVenta();

        //
        Connection connection = null;

        //
        String selectStr =
                "SELECT COUNT(tblDctosOrdenesDetalle.IDPLU)                   "
                + "                            AS cantidadArticulos,            "
                + "       SUM(tblDctosOrdenesDetalle.cantidad        *          "
                + "           tblDctosOrdenesDetalle.vrVentaUnitario )          "
                + "                                          AS vrVentaConIva,  "
                + "      SUM(tblDctosOrdenesDetalle.cantidad         *          "
                + "           tblDctosOrdenesDetalle.vrVentaUnitario /          "
                + "          (1 + (tblDctosOrdenesDetalle.porcentajeIva/100)))  "
                + "                                          AS vrVentaSinIva,  "
                + "       SUM(tblDctosOrdenesDetalle.cantidad        *          "
                + "           tblDctosOrdenesDetalle.vrVentaUnitario )    -     "
                + "      SUM(tblDctosOrdenesDetalle.cantidad              *     "
                + "          tblDctosOrdenesDetalle.vrVentaUnitario       /     "
                + "          (1 + (tblDctosOrdenesDetalle.porcentajeIva/100)))  "
                + "                                                  AS vrIva,  "
                + "      SUM(tblDctosOrdenesDetalle.pesoTeorico   *             "
                + "          tblDctosOrdenesDetalle.cantidad)                   "
                + "                                       AS pesoTeoricoTotal,  "
                + "      MAX(tblDctosOrdenes.fechaOrden)                        "
                + "                                             AS fechaOrden,  "
                + "      MAX(tblDctosOrdenes.idOrden) AS idOrden,               "
                + "      MAX(tblDctosOrdenes.idLocal) AS idLocal,               "
                + "      MAX(tblDctosOrdenes.idTipoOrden) AS idTipoOrden,       "
                + "      MAX(tblDctosOrdenes.idOrden) AS idDcto,                "
                + "      MAX(tblDctosOrdenes.fechaOrden) AS fechaDcto,          "
                + "      MAX(tblDctosOrdenes.idOrden) AS idDctoNitCC,           "
                + "      MAX(tblTerceros.nombreTercero) AS nombreTercero        "
                + "FROM tblDctosOrdenes,                                        "
                + "     tblDctosOrdenesDetalle,                                 "
                + "     tblTerceros                                             "
                + "WHERE tblDctosOrdenes.IDORDEN     =                          "
                + "                          tblDctosOrdenesDetalle.idOrden     "
                + "AND   tblDctosOrdenes.IDTIPOORDEN =                          "
                + "                       tblDctosOrdenesDetalle.idTipoOrden    "
                + "AND   tblDctosOrdenes.idLocal     =                          "
                + "                       tblDctosOrdenesDetalle.idLocal        "
                + "AND   tblDctosOrdenes.idCliente   =                          "
                + "                       tblTerceros.idCliente                 "
                + "AND   tblDctosOrdenes.IDTIPOORDEN =                          "
                + getIdTipoOrden() + "                                          "
                + "AND   tblDctosOrdenes.idOrden     =                          "
                + getIdOrden() + "                                              "
                + "AND tblTerceros.idTipoTercero     = 1                        ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            while (rs.next()) {

                fachadaOrdenVenta = new FachadaOrdenVenta();

                //
                fachadaOrdenVenta.setCantidadArticulos(
                        rs.getInt("cantidadArticulos"));
                fachadaOrdenVenta.setVrVentaSinIva(
                        rs.getDouble("vrVentaSinIva"));
                fachadaOrdenVenta.setVrVentaConIva(
                        rs.getDouble("vrVentaConIva"));
                fachadaOrdenVenta.setVrIva(rs.getDouble("vrIva"));
                fachadaOrdenVenta.setPesoTeoricoTotal(
                        rs.getDouble("pesoTeoricoTotal"));
                fachadaOrdenVenta.setFechaOrden(rs.getString("fechaOrden"));
                fachadaOrdenVenta.setIdOrden(rs.getInt("idOrden"));
                fachadaOrdenVenta.setIdLocal(rs.getInt("idLocal"));
                fachadaOrdenVenta.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaOrdenVenta.setIdDcto(rs.getInt("idDcto"));
                fachadaOrdenVenta.setFechaOrden(rs.getString("fechaDcto"));
                fachadaOrdenVenta.setIdDctoNitCC(rs.getString("idDctoNitCC"));
                fachadaOrdenVenta.setNombreTercero(
                        rs.getString("nombreTercero"));
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
            return fachadaOrdenVenta;

        }
    }

    // detallaUnaOrden
    public Collection detallaUnaOrden() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        // Variable de fachada de los datos
        FachadaOrdenVenta fachadaOrdenVenta = new FachadaOrdenVenta();

        //
        Collection list = new ArrayList();

        //
        boolean validaOk = false;
        Connection connection = null;

        //
        String selectStr =
                "SELECT MIN(tblDctosOrdenesDetalle.strIdReferencia)         "
                + "                           AS    strIdReferencia     ,     "
                + "       MIN(tblDctosOrdenesDetalle.nombrePlu)               "
                + "                                            AS nombrePlu , "
                + "       MIN(tblDctosOrdenesDetalle.cantidad) AS             "
                + "                                                 cantidad, "
                + "       MIN( tblDctosOrdenesDetalle.vrVentaUnitario)        "
                + "                                       AS vrVentaUnitario, "
                + "       MIN( tblDctosOrdenesDetalle.vrVentaOriginal)        "
                + "                                       AS vrVentaOriginal, "
                + "       MIN  (( tblDctosOrdenesDetalle.vrVentaUnitario -    "
                + "               tblDctosOrdenesDetalle.vrVentaOriginal ) /  "
                + "               tblDctosOrdenesDetalle.vrVentaOriginal )    "
                + "                                  AS porcentajeDscto,      "
                + "       SUM ( tblDctosOrdenesDetalle.cantidad          *    "
                + "             tblDctosOrdenesDetalle.vrVentaUnitario   )    "
                + "                                        AS vrVentaSinIva,  "
                + "       MIN(tblDctosOrdenesDetalle.porcentajeIva)           "
                + "                                         AS porcentajeIva  "
                + "FROM  tblDctosOrdenes ,                                    "
                + "      tblDctosOrdenesDetalle                               "
                + "WHERE tblDctosOrdenes.idOrden         =                    "
                + "      tblDctosOrdenesDetalle.idOrden                       "
                + "AND   tblDctosOrdenes.idTipoOrden     =                    "
                + "      tblDctosOrdenesDetalle.idTipoOrden                   "
                + "AND   tblDctosOrdenes.idLocal         =                    "
                + "      tblDctosOrdenesDetalle.idLocal                       "
                + "AND   tblDctosOrdenes.IDTIPOORDEN =                        "
                + getIdTipoOrden() + "                                        "
                + "AND   tblDctosOrdenes.IDLOG       =                        "
                + getIdLog() + "                                              "
                + "GROUP BY tblDctosOrdenesDetalle.itemPadre,                 "
                + "         tblDctosOrdenesDetalle.item                       ";

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
                fachadaOrdenVenta.setStrIdReferencia(
                        rs.getString("strIdReferencia"));
                fachadaOrdenVenta.setNombrePlu(rs.getString("nombrePlu"));
                fachadaOrdenVenta.setCantidad(rs.getDouble("cantidad"));
                fachadaOrdenVenta.setVrVentaUnitario(
                        rs.getDouble("vrVentaUnitario"));
                fachadaOrdenVenta.setVrVentaOriginal(
                        rs.getDouble("vrVentaOriginal"));
                fachadaOrdenVenta.setPorcentajeDscto(
                        rs.getDouble("porcentajeDscto"));
                fachadaOrdenVenta.setVrVentaSinIva(rs.getDouble("vrVentaSinIva"));
                fachadaOrdenVenta.setPorcentajeIva(rs.getDouble("porcentajeIva"));

                //
                list.add(new ColaboraOrdenVentaBean(
                        fachadaOrdenVenta.getStrIdReferencia(),
                        fachadaOrdenVenta.getNombrePlu(),
                        fachadaOrdenVenta.getCantidad(),
                        fachadaOrdenVenta.getPorcentajeIva(),
                        fachadaOrdenVenta.getVrVentaUnitario(),
                        fachadaOrdenVenta.getVrVentaSinIva(),
                        fachadaOrdenVenta.getPorcentajeDscto(),
                        fachadaOrdenVenta.getNombreMarca(),
                        fachadaOrdenVenta.getNombreCategoria(),
                        0,
                        0,
                        0,
                        0,
                        0,
                        0,
                        0));
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
            return list;

        }
    }

    // detallaUnPedidoOrden
    public Vector detallaUnPedidoOrden() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        // Variable de fachada de los datos
        FachadaOrdenVenta fachadaOrdenVenta;

        //
        Vector contenedor = new Vector();

        //
        Connection connection = null;

        //
        String selectStr =
                "   SELECT   tblDctosOrdenesDetalle.idLocal,                        "
                + "            tblDctosOrdenesDetalle.idTipoOrden,                    "
                + "            tblDctosOrdenesDetalle.idOrden,                        "
                + "            tblDctosOrdenesDetalle.strIdReferencia,                "
                + "            tblDctosOrdenesDetalle.nombrePlu,                      "
                + "            tblDctosOrdenesDetalle.item,                           "
                + "            tblDctosOrdenesDetalle.idLocalOrigen,                  "
                + "            tblDctosOrdenesDetalle.idTipoOrdenOrigen,              "
                + "            tblDctosOrdenesDetalle.idOrdenOrigen,                  "
                + "            tblDctosOrdenesDetalle.cantidadEntregada,              "
                + "            tblDctosOrdenesDetalle.pesoEntregado,                  "
                + "            tmpFIC.referencia,                                     "
                + "           ( SELECT  tblDctosOrdenes.numeroOrden                   "
                + "             FROM   tblDctosOrdenes                                "
                + "             WHERE  tblDctosOrdenes.idLocal =                      "
                + "               tblDctosOrdenesDetalle.idLocalOrigen                "
                + "             AND  tblDctosOrdenes.idTipoOrden =                    "
                + "               tblDctosOrdenesDetalle.idTipoOrdenOrigen            "
                + "             AND  tblDctosOrdenes.idOrden =                        "
                + "             tblDctosOrdenesDetalle.idOrdenOrigen)                 "
                + "                                         AS numeroOrden,           "
                + "           ( SELECT  tblDctosOrdenes.ordenCompra                   "
                + "             FROM   tblDctosOrdenes                                "
                + "             WHERE  tblDctosOrdenes.idLocal =                      "
                + "               tblDctosOrdenesDetalle.idLocalOrigen                "
                + "             AND  tblDctosOrdenes.idTipoOrden =                    "
                + "               tblDctosOrdenesDetalle.idTipoOrdenOrigen            "
                + "             AND  tblDctosOrdenes.idOrden =                        "
                + "               tblDctosOrdenesDetalle.idOrdenOrigen)               "
                + "                                             AS ordenCompra,       "
                + "            MIN(tblMarcas.nombreMarca) AS nombreMarca,             "
                + "            MIN(tblCategorias.nombreCategoria) AS nombreCategoria, "
                + "            MIN(tblDctosOrdenesDetalle.cantidad) AS cantidad,      "
                + "            MIN( tblDctosOrdenesDetalle.vrVentaUnitario)           "
                + "                                          AS vrVentaUnitario,      "
                + "            MIN( tblDctosOrdenesDetalle.vrVentaOriginal)           "
                + "                                         AS vrVentaOriginal,       "
                + "            MIN  ( tblDctosOrdenesDetalle.porcentajeDscto)         "
                + "                                         AS porcentajeDscto,       "
                + "            SUM (tblDctosOrdenesDetalle.cantidad         /         "
                + "                 tblDctosOrdenesDetalle.unidadVenta      *         "
                + "                  tblDctosOrdenesDetalle.vrVentaUnitario )         "
                + "                                          AS vrVentaConIva,        "
                + "            SUM((tblDctosOrdenesDetalle.cantidad         /         "
                + "                 tblDctosOrdenesDetalle.unidadVenta      *         "
                + "                  tblDctosOrdenesDetalle.vrVentaUnitario) /        "
                + "      ( 1 + ( tblDctosOrdenesDetalle.porcentajeIva / 100)))        "
                + "                                          AS vrVentaSinIva,        "
                + "            MIN(tblDctosOrdenesDetalle.porcentajeIva)              "
                + "                                         AS porcentajeIva,         "
                + "            MIN( tblDctosOrdenesDetalle.vrCosto)                   "
                + "                                          AS vrCosto,              "
                + "                      SUM(tblDctosOrdenesDetalle.vrCosto *         "
                + "                          tblDctosOrdenesDetalle.cantidad)         "
                + "                                         AS vrCostoConIva,         "
                + "                   SUM( (tblDctosOrdenesDetalle.cantidad *         "
                + "                         tblDctosOrdenesDetalle.vrCosto ) /        "
                + "         (1 + (tblDctosOrdenesDetalle.porcentajeIva/100)))         "
                + "                                         AS vrCostoSinIva,         "
                + "              SUM(((tblDctosOrdenesDetalle.cantidad      /         "
                + "                 tblDctosOrdenesDetalle.unidadVenta      *         "
                + "                 tblDctosOrdenesDetalle.vrVentaUnitario)  /        "
                + "    ( 1 + ( tblDctosOrdenesDetalle.porcentajeIva / 100))) *        "
                + "    ( 1 - ( tblDctosOrdenesDetalle.vrDsctoPie / 100))     *        "
                + "    ( 1 - ( tblDctosOrdenesDetalle.porcentajeDscto / 100)))        "
                + "                                        AS vrVentaSinDscto,        "
                + "      SUM(((tblDctosOrdenesDetalle.cantidad              /         "
                + "                 tblDctosOrdenesDetalle.unidadVenta      *         "
                + "          tblDctosOrdenesDetalle.vrVentaUnitario) /                "
                + "    ( 1 + ( tblDctosOrdenesDetalle.porcentajeIva / 100))) *        "
                + "    ( 1 - ( tblDctosOrdenesDetalle.vrDsctoPie / 100))     *        "
                + "    ( 1 - ( tblDctosOrdenesDetalle.porcentajeDscto / 100))*        "
                + "           ( tblDctosOrdenesDetalle.porcentajeIva / 100 ))         "
                + "                                         AS vrIvaVenta,            "
                + "            MIN( tblDctosOrdenesDetalle.vrCostoNegociado)          "
                + "                                       AS vrCostoNegociado,        "
                + "             SUM(tblDctosOrdenesDetalle.vrCostoNegociado *         "
                + "                          tblDctosOrdenesDetalle.cantidad)         "
                + "                                  AS vrCostoNegociadoConIva,       "
                + "                   SUM( (tblDctosOrdenesDetalle.cantidad *         "
                + "                tblDctosOrdenesDetalle.vrCostoNegociado ) /        "
                + "         (1 + (tblDctosOrdenesDetalle.porcentajeIva/100)))         "
                + "                                   AS vrCostoNegociadoSinIva,      "
                + "           MAX(tblDctosOrdenesDetalle.referenciaCliente)           "
                + "                                    AS referenciaCliente,          "
                + "           MAX(  tblDctosOrdenesDetalle.comentario)                "
                + "                                    AS comentario,                 "
                + "                   SUM( (tblDctosOrdenesDetalle.cantidad *         "
                + "                tblDctosOrdenesDetalle.vrCostoNegociado ) /        "
                + "         (1 + (tblDctosOrdenesDetalle.porcentajeIva/100)))         "
                + "                                   AS vrCostoNegociadoSinIva,      "
                + "            MAX(tblDctosOrdenesDetalle.vrVentaUnitarioSinIva)      "
                + "                                    AS vrVentaUnitarioSinIva       "
                + "   FROM   tblDctosOrdenes                                          "
                + "   INNER JOIN tblDctosOrdenesDetalle                               "
                + "   ON tblDctosOrdenes.IDLOCAL      =                               "
                + "                          tblDctosOrdenesDetalle.IDLOCAL           "
                + "   AND tblDctosOrdenes.IDTIPOORDEN =                               "
                + "                      tblDctosOrdenesDetalle.IDTIPOORDEN           "
                + "   AND tblDctosOrdenes.IDORDEN     =                               "
                + "                          tblDctosOrdenesDetalle.IDORDEN           "
                + "  INNER JOIN                                                       "
                + "  ( SELECT tblDctosOrdenes.idLocal,                                "
                + "       tblDctosOrdenes.idTipoOrden,                                "
                + "       tblDctosOrdenes.idOrden,                                    "
                + "       tblPlusFicha.referencia                                     "
                + "  FROM   tblDctosOrdenes                                           "
                + "  INNER JOIN tblPlusFicha                                          "
                + "  ON tblDctosOrdenes.idFicha =                                     "
                + "            tblPlusFicha.idFicha                                   "
                + "  GROUP BY tblDctosOrdenes.idLocal,                                "
                + "         tblDctosOrdenes.idTipoOrden,                              "
                + "         tblDctosOrdenes.idOrden,                                  "
                + "         tblPlusFicha.referencia) AS tmpFIC                        "
                + "  ON  tmpFIC.idLocal  =                                            "
                + "                    tblDctosOrdenesDetalle.idLocalOrigen           "
                + "  AND  tmpFIC.idTipoOrden                                          "
                + "                  = tblDctosOrdenesDetalle.idTipoOrdenOrigen       "
                + "  AND  tmpFIC.idOrden                                              "
                + "                  = tblDctosOrdenesDetalle.idOrdenOrigen           "
                + "  INNER JOIN tblPlus                                               "
                + "  ON tblPlus.idPlu  = tblDctosOrdenesDetalle.idPlu                 "
                + "  INNER JOIN tblCategorias                                         "
                + "  ON tblPlus.idLinea       = tblCategorias.idLinea                 "
                + "  AND tblPlus.idCategoria  = tblCategorias.idCategoria             "
                + "  INNER JOIN tblMarcas                                             "
                + "  ON tblPlus.idMarca       = tblMarcas.idMarca                     "
                + " WHERE tblDctosOrdenes.IDTIPOORDEN =                               "
                + getIdTipoOrden() + "                                                "
                + "AND   tblDctosOrdenes.idOrden     =                                "
                + getIdOrden() + "                                                    "
                + "  GROUP BY tblDctosOrdenesDetalle.idLocal,                         "
                + "            tblDctosOrdenesDetalle.idTipoOrden,                    "
                + "            tblDctosOrdenesDetalle.idOrden,                        "
                + "            tblDctosOrdenesDetalle.strIdReferencia,                "
                + "            tblDctosOrdenesDetalle.nombrePlu,                      "
                + "            tblDctosOrdenesDetalle.item,                           "
                + "            tblDctosOrdenesDetalle.idLocalOrigen,                  "
                + "            tblDctosOrdenesDetalle.idTipoOrdenOrigen,              "
                + "            tblDctosOrdenesDetalle.idOrdenOrigen,                  "
                + "            tmpFIC.referencia,                                     "
                + "            tblDctosOrdenesDetalle.cantidadEntregada,              "
                + "            tblDctosOrdenesDetalle.pesoEntregado                   ";

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
                fachadaOrdenVenta = new FachadaOrdenVenta();

                //
                fachadaOrdenVenta.setStrIdReferencia(
                        rs.getString("strIdReferencia"));
                fachadaOrdenVenta.setNombrePlu(rs.getString("nombrePlu"));
                fachadaOrdenVenta.setCantidad(rs.getDouble("cantidad"));
                fachadaOrdenVenta.setVrVentaUnitario(
                        rs.getDouble("vrVentaUnitario"));
                fachadaOrdenVenta.setVrVentaOriginal(
                        rs.getDouble("vrVentaOriginal"));
                fachadaOrdenVenta.setPorcentajeDscto(
                        rs.getDouble("porcentajeDscto"));
                fachadaOrdenVenta.setVrVentaSinDscto(
                        rs.getDouble("vrVentaSinDscto"));
                fachadaOrdenVenta.setPorcentajeIva(
                        rs.getDouble("porcentajeIva"));
                fachadaOrdenVenta.setNombreMarca(rs.getString("nombreMarca"));
                fachadaOrdenVenta.setNombreCategoria(
                        rs.getString("nombreCategoria"));
                fachadaOrdenVenta.setVrCosto(rs.getDouble("vrCosto"));
                fachadaOrdenVenta.setVrCostoConIva(
                        rs.getDouble("vrCostoConIva"));
                fachadaOrdenVenta.setVrCostoSinIva(
                        rs.getDouble("vrCostoSinIva"));
                fachadaOrdenVenta.setVrIvaVenta(rs.getDouble("vrIvaVenta"));
                fachadaOrdenVenta.setVrCostoNegociado(
                        rs.getDouble("vrCostoNegociado"));
                fachadaOrdenVenta.setVrCostoNegociadoConIva(
                        rs.getDouble("vrCostoNegociadoConIva"));
                fachadaOrdenVenta.setVrCostoNegociadoSinIva(
                        rs.getDouble("vrCostoNegociadoSinIva"));
                fachadaOrdenVenta.setReferenciaCliente(
                        rs.getString("referenciaCliente"));
                fachadaOrdenVenta.setComentario(
                        rs.getString("comentario"));
                fachadaOrdenVenta.setNumeroOrden(rs.getInt("numeroOrden"));
                fachadaOrdenVenta.setItem(rs.getInt("item"));
                fachadaOrdenVenta.setOrdenCompra(rs.getString("OrdenCompra"));
                fachadaOrdenVenta.setReferencia(rs.getString("referencia"));
                fachadaOrdenVenta.setVrVentaUnitarioSinIva(
                        rs.getDouble("vrVentaUnitarioSinIva"));
                fachadaOrdenVenta.setCantidadEntregada(
                        rs.getDouble("cantidadEntregada"));
                fachadaOrdenVenta.setPesoEntregado(
                        rs.getDouble("pesoEntregado"));

                //
                contenedor.add(fachadaOrdenVenta);

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
            return contenedor;
        }
    }

    // detallaUnPedidoOrdenInventario
    public Vector detallaUnPedidoOrdenInventario() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        // Variable de fachada de los datos
        FachadaOrdenVenta fachadaOrdenVenta;

        //
        Vector contenedor = new Vector();

        //
        Connection connection = null;

        //
        String selectStr =
                "  SELECT   tblDctosOrdenesDetalle.idLocal,                    "
                + "           tblDctosOrdenesDetalle.idTipoOrden,               "
                + "           tblDctosOrdenesDetalle.idOrden,                   "
                + "           tblDctosOrdenesDetalle.strIdReferencia,           "
                + "           tblDctosOrdenesDetalle.nombrePlu,                 "
                + "           tblDctosOrdenesDetalle.item,                      "
                + "           tblDctosOrdenesDetalle.idLocalOrigen,             "
                + "           tblDctosOrdenesDetalle.idTipoOrdenOrigen,         "
                + "           tblDctosOrdenesDetalle.idOrdenOrigen,             "
                + "           tblDctosOrdenesDetalle.cantidadEntregada,         "
                + "           tblDctosOrdenesDetalle.pesoEntregado,             "
                + "          ( SELECT  tblDctosOrdenes.numeroOrden              "
                + "            FROM   tblDctosOrdenes                           "
                + "            WHERE  tblDctosOrdenes.idLocal =                 "
                + "              tblDctosOrdenesDetalle.idLocalOrigen           "
                + "            AND  tblDctosOrdenes.idTipoOrden =               "
                + "              tblDctosOrdenesDetalle.idTipoOrdenOrigen       "
                + "            AND  tblDctosOrdenes.idOrden =                   "
                + "            tblDctosOrdenesDetalle.idOrdenOrigen)            "
                + "                                        AS numeroOrden,      "
                + "          ( SELECT  tblDctosOrdenes.ordenCompra              "
                + "            FROM   tblDctosOrdenes                           "
                + "            WHERE  tblDctosOrdenes.idLocal =                 "
                + "              tblDctosOrdenesDetalle.idLocalOrigen           "
                + "            AND  tblDctosOrdenes.idTipoOrden =               "
                + "              tblDctosOrdenesDetalle.idTipoOrdenOrigen       "
                + "            AND  tblDctosOrdenes.idOrden =                   "
                + "              tblDctosOrdenesDetalle.idOrdenOrigen)          "
                + "                                            AS ordenCompra,  "
                + "           MIN(tblMarcas.nombreMarca) AS nombreMarca,        "
                + "           MIN(tblCategorias.nombreCategoria)                "
                + " 		                            AS nombreCategoria,  "
                + "           MIN(tblDctosOrdenesDetalle.cantidad) AS cantidad, "
                + "           MIN( tblDctosOrdenesDetalle.vrVentaUnitario)      "
                + "                                         AS vrVentaUnitario, "
                + "           MIN( tblDctosOrdenesDetalle.vrVentaOriginal)      "
                + "                                        AS vrVentaOriginal,  "
                + "           MIN  ( tblDctosOrdenesDetalle.porcentajeDscto)    "
                + "                                        AS porcentajeDscto,  "
                + "           SUM (tblDctosOrdenesDetalle.cantidad         /    "
                + "                tblDctosOrdenesDetalle.unidadVenta      *    "
                + "                 tblDctosOrdenesDetalle.vrVentaUnitario )    "
                + "                                         AS vrVentaConIva,   "
                + "           SUM((tblDctosOrdenesDetalle.cantidad         /    "
                + "                tblDctosOrdenesDetalle.unidadVenta      *    "
                + "                 tblDctosOrdenesDetalle.vrVentaUnitario) /   "
                + "     ( 1 + ( tblDctosOrdenesDetalle.porcentajeIva / 100)))   "
                + "                                         AS vrVentaSinIva,   "
                + "           MIN(tblDctosOrdenesDetalle.porcentajeIva)         "
                + "                                        AS porcentajeIva,    "
                + "           MIN( tblDctosOrdenesDetalle.vrCosto)              "
                + "                                         AS vrCosto,         "
                + "                     SUM(tblDctosOrdenesDetalle.vrCosto *    "
                + "                         tblDctosOrdenesDetalle.cantidad)    "
                + "                                        AS vrCostoConIva,    "
                + "                  SUM( (tblDctosOrdenesDetalle.cantidad *    "
                + "                        tblDctosOrdenesDetalle.vrCosto ) /   "
                + "        (1 + (tblDctosOrdenesDetalle.porcentajeIva/100)))    "
                + "                                        AS vrCostoSinIva,    "
                + "             SUM(((tblDctosOrdenesDetalle.cantidad      /    "
                + "                tblDctosOrdenesDetalle.unidadVenta      *    "
                + "                tblDctosOrdenesDetalle.vrVentaUnitario)  /   "
                + "   ( 1 + ( tblDctosOrdenesDetalle.porcentajeIva / 100))) *   "
                + "   ( 1 - ( tblDctosOrdenesDetalle.vrDsctoPie / 100))     *   "
                + "   ( 1 - ( tblDctosOrdenesDetalle.porcentajeDscto / 100)))   "
                + "                                       AS vrVentaSinDscto,   "
                + "     SUM(((tblDctosOrdenesDetalle.cantidad              /    "
                + "                tblDctosOrdenesDetalle.unidadVenta      *    "
                + "         tblDctosOrdenesDetalle.vrVentaUnitario) /           "
                + "   ( 1 + ( tblDctosOrdenesDetalle.porcentajeIva / 100))) *   "
                + "   ( 1 - ( tblDctosOrdenesDetalle.vrDsctoPie / 100))     *   "
                + "   ( 1 - ( tblDctosOrdenesDetalle.porcentajeDscto / 100))*   "
                + "          ( tblDctosOrdenesDetalle.porcentajeIva / 100 ))    "
                + "                                        AS vrIvaVenta,       "
                + "           MIN( tblDctosOrdenesDetalle.vrCostoNegociado)     "
                + "                                      AS vrCostoNegociado,   "
                + "            SUM(tblDctosOrdenesDetalle.vrCostoNegociado *    "
                + "                         tblDctosOrdenesDetalle.cantidad)    "
                + "                                 AS vrCostoNegociadoConIva,  "
                + "                  SUM( (tblDctosOrdenesDetalle.cantidad *    "
                + "               tblDctosOrdenesDetalle.vrCostoNegociado ) /   "
                + "        (1 + (tblDctosOrdenesDetalle.porcentajeIva/100)))    "
                + "                                  AS vrCostoNegociadoSinIva, "
                + "          MAX(tblDctosOrdenesDetalle.referenciaCliente)      "
                + "                                   AS referenciaCliente,     "
                + "          MAX(  tblDctosOrdenesDetalle.comentario)           "
                + "                                   AS comentario,            "
                + "                  SUM( (tblDctosOrdenesDetalle.cantidad *    "
                + "               tblDctosOrdenesDetalle.vrCostoNegociado ) /   "
                + "        (1 + (tblDctosOrdenesDetalle.porcentajeIva/100)))    "
                + "                                  AS vrCostoNegociadoSinIva, "
                + "           MAX(tblDctosOrdenesDetalle.vrVentaUnitarioSinIva) "
                + "                                   AS vrVentaUnitarioSinIva, "
                + "                                                             "
                + "        ( CASE                                               "
                + "          WHEN tblDctosOrdenesDetalle.CANTIDAD < 0           "
                + "          THEN 'ENTRADA ' +                                  "
                + "                      tblLocalesBodega.nombreBodega          "
                + "          ELSE 'SALIDA ' +                                   "
                + "                      tblLocalesBodega.nombreBodega          "
                + "          END ) AS nombreBodega                              "
                + "  FROM   tblDctosOrdenes                                     "
                + "  INNER JOIN tblDctosOrdenesDetalle                          "
                + "  ON tblDctosOrdenes.IDLOCAL      =                          "
                + "                         tblDctosOrdenesDetalle.IDLOCAL      "
                + "  AND tblDctosOrdenes.IDTIPOORDEN =                          "
                + "                     tblDctosOrdenesDetalle.IDTIPOORDEN      "
                + "  AND tblDctosOrdenes.IDORDEN     =                          "
                + "                         tblDctosOrdenesDetalle.IDORDEN      "
                + "  INNER JOIN tblPlus                                         "
                + "  ON tblPlus.idPlu  = tblDctosOrdenesDetalle.idPlu           "
                + "  INNER JOIN tblCategorias                                   "
                + "  ON tblPlus.idLinea       = tblCategorias.idLinea           "
                + "  AND tblPlus.idCategoria  = tblCategorias.idCategoria       "
                + "  INNER JOIN tblMarcas                                       "
                + "  ON tblPlus.idMarca       = tblMarcas.idMarca               "
                + "  INNER JOIN tblLocalesBodega                                "
                + "  ON  tblLocalesBodega.idBodega =                            "
                + "                       tblDctosOrdenesDetalle.idBodega       "
                + " WHERE tblDctosOrdenes.IDTIPOORDEN =                        "
                + getIdTipoOrden() + "                                         "
                + "AND   tblDctosOrdenes.idOrden     =                         "
                + getIdOrden() + "                                             "
                + "  GROUP BY tblDctosOrdenesDetalle.idLocal,                   "
                + "           tblDctosOrdenesDetalle.idTipoOrden,               "
                + "           tblDctosOrdenesDetalle.idOrden,                   "
                + "           tblLocalesBodega.nombreBodega,                    "
                + "           tblDctosOrdenesDetalle.strIdReferencia,           "
                + "           tblDctosOrdenesDetalle.nombrePlu,                 "
                + "           tblDctosOrdenesDetalle.item,                      "
                + "           tblDctosOrdenesDetalle.idLocalOrigen,             "
                + "           tblDctosOrdenesDetalle.idTipoOrdenOrigen,         "
                + "           tblDctosOrdenesDetalle.idOrdenOrigen,             "
                + "           tblDctosOrdenesDetalle.cantidadEntregada,         "
                + "           tblDctosOrdenesDetalle.pesoEntregado,             "
                + "           tblDctosOrdenesDetalle.CANTIDAD                   "
                + "  ORDER BY tblDctosOrdenesDetalle.idLocal,                   "
                + "           tblDctosOrdenesDetalle.idTipoOrden,               "
                + "           tblDctosOrdenesDetalle.idOrden,                   "
                + "           tblLocalesBodega.nombreBodega                     ";

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
                fachadaOrdenVenta = new FachadaOrdenVenta();

                //
                fachadaOrdenVenta.setStrIdReferencia(
                        rs.getString("strIdReferencia"));
                fachadaOrdenVenta.setNombrePlu(rs.getString("nombrePlu"));
                fachadaOrdenVenta.setCantidad(rs.getDouble("cantidad"));
                fachadaOrdenVenta.setVrVentaUnitario(
                        rs.getDouble("vrVentaUnitario"));
                fachadaOrdenVenta.setVrVentaOriginal(
                        rs.getDouble("vrVentaOriginal"));
                fachadaOrdenVenta.setPorcentajeDscto(
                        rs.getDouble("porcentajeDscto"));
                fachadaOrdenVenta.setVrVentaSinDscto(
                        rs.getDouble("vrVentaSinDscto"));
                fachadaOrdenVenta.setPorcentajeIva(
                        rs.getDouble("porcentajeIva"));
                fachadaOrdenVenta.setNombreMarca(rs.getString("nombreMarca"));
                fachadaOrdenVenta.setNombreCategoria(
                        rs.getString("nombreCategoria"));
                fachadaOrdenVenta.setVrCosto(rs.getDouble("vrCosto"));
                fachadaOrdenVenta.setVrCostoConIva(
                        rs.getDouble("vrCostoConIva"));
                fachadaOrdenVenta.setVrCostoSinIva(
                        rs.getDouble("vrCostoSinIva"));
                fachadaOrdenVenta.setVrIvaVenta(rs.getDouble("vrIvaVenta"));
                fachadaOrdenVenta.setVrCostoNegociado(
                        rs.getDouble("vrCostoNegociado"));
                fachadaOrdenVenta.setVrCostoNegociadoConIva(
                        rs.getDouble("vrCostoNegociadoConIva"));
                fachadaOrdenVenta.setVrCostoNegociadoSinIva(
                        rs.getDouble("vrCostoNegociadoSinIva"));
                fachadaOrdenVenta.setReferenciaCliente(
                        rs.getString("referenciaCliente"));
                fachadaOrdenVenta.setComentario(
                        rs.getString("comentario"));
                fachadaOrdenVenta.setNumeroOrden(rs.getInt("numeroOrden"));
                fachadaOrdenVenta.setItem(rs.getInt("item"));
                fachadaOrdenVenta.setOrdenCompra(rs.getString("OrdenCompra"));
                fachadaOrdenVenta.setVrVentaUnitarioSinIva(
                        rs.getDouble("vrVentaUnitarioSinIva"));
                fachadaOrdenVenta.setCantidadEntregada(
                        rs.getDouble("cantidadEntregada"));
                fachadaOrdenVenta.setPesoEntregado(
                        rs.getDouble("pesoEntregado"));
                fachadaOrdenVenta.setNombreBodega(
                        rs.getString("nombreBodega"));                

                //
                contenedor.add(fachadaOrdenVenta);

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
            return contenedor;
        }
    }

    // listaOrdenMovimientoOT
    public Vector listaOrdenMovimientoOT() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        // Variable de fachada de los datos
        FachadaOrdenVenta fachadaOrdenVenta;

        //
        Vector contenedor = new Vector();

        //
        Connection connection = null;

        //
        String selectStr =
                "   SELECT   tblDctosOrdenesDetalle.idPlu,                  "
                + "            tblDctosOrdenesDetalle.nombrePlu,              "
                + "            tblDctosOrdenesDetalle.cantidadTerminada,      "
                + "            tblDctosOrdenesDetalle.pesoTerminado,          "
                + "           ( SELECT  tblDctosOrdenes.numeroOrden           "
                + "             FROM   tblDctosOrdenes                        "
                + "             WHERE  tblDctosOrdenes.idLocal =              "
                + "               tblDctosOrdenesDetalle.idLocalOrigen        "
                + "             AND  tblDctosOrdenes.idTipoOrden =            "
                + "               tblDctosOrdenesDetalle.idTipoOrdenOrigen    "
                + "             AND  tblDctosOrdenes.idOrden =                "
                + "             tblDctosOrdenesDetalle.idOrdenOrigen)         "
                + "                                         AS numeroOrden,   "
                + "             (tblMarcas.nombreMarca) AS nombreMarca,       "
                + "             (tblCategorias.nombreCategoria)               "
                + "                                   AS nombreCategoria      "
                + "   FROM   tblDctosOrdenes                                  "
                + "   INNER JOIN tblDctosOrdenesDetalle                       "
                + "   ON tblDctosOrdenes.IDLOCAL      =                       "
                + "                          tblDctosOrdenesDetalle.IDLOCAL   "
                + "   AND tblDctosOrdenes.IDTIPOORDEN =                       "
                + "                      tblDctosOrdenesDetalle.IDTIPOORDEN   "
                + "   AND tblDctosOrdenes.IDORDEN     =                       "
                + "                          tblDctosOrdenesDetalle.IDORDEN   "
                + "  INNER JOIN tblPlus                                       "
                + "  ON tblPlus.idPlu  = tblDctosOrdenesDetalle.idPlu         "
                + "  INNER JOIN tblCategorias                                 "
                + "  ON tblPlus.idLinea       = tblCategorias.idLinea         "
                + "  AND tblPlus.idCategoria  = tblCategorias.idCategoria     "
                + "  INNER JOIN tblMarcas                                     "
                + "  ON tblPlus.idMarca       = tblMarcas.idMarca             "
                + " WHERE tblDctosOrdenes.idLocal =                           "
                + getIdLocal() + "                                            "
                + "AND   tblDctosOrdenes.idTipoOrden     =                    "
                + getIdTipoOrden() + "                                        "
                + "AND   tblDctosOrdenes.idOrden     =                         "
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

                //
                fachadaOrdenVenta = new FachadaOrdenVenta();

                //
                fachadaOrdenVenta.setIdPlu(rs.getInt("idPlu"));
                fachadaOrdenVenta.setNombrePlu(rs.getString("nombrePlu"));
                fachadaOrdenVenta.setCantidadTerminada(
                        rs.getDouble("cantidadTerminada"));
                fachadaOrdenVenta.setPesoTerminado(
                        rs.getDouble("pesoTerminado"));
                fachadaOrdenVenta.setNumeroOrden(rs.getInt("numeroOrden"));
                fachadaOrdenVenta.setNombreMarca(rs.getString("nombreMarca"));
                fachadaOrdenVenta.setNombreCategoria(
                        rs.getString("nombreCategoria"));

                //
                contenedor.add(fachadaOrdenVenta);

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
            return contenedor;
        }
    }

    // detallaUnPedidoOrdenCopia
    public Vector detallaUnPedidoOrdenCopia() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        // Variable de fachada de los datos
        FachadaOrdenVenta fachadaOrdenVenta;

        //
        Vector contenedor = new Vector();

        //
        Connection connection = null;

        //
        String selectStr =
                "   SELECT   tblDctosOrdenesDetalle.idLocal,                    "
                + "            tblDctosOrdenesDetalle.idTipoOrden,                "
                + "            tblDctosOrdenesDetalle.idOrden,                    "
                + "            tblDctosCopia.idCopia,                             "
                + "            tblDctosCopia.nombreCopia,                         "
                + "            tblDctosOrdenesDetalle.strIdReferencia,            "
                + "            tblDctosOrdenesDetalle.nombrePlu,                  "
                + "            tblDctosOrdenesDetalle.item,                       "
                + "            tblDctosOrdenesDetalle.idLocalOrigen,              "
                + "            tblDctosOrdenesDetalle.idTipoOrdenOrigen,          "
                + "            tblDctosOrdenesDetalle.idOrdenOrigen,              "
                + "            tmpFIC.referencia,                                 "
                + "           ( SELECT  tblDctosOrdenes.numeroOrden               "
                + "             FROM   tblDctosOrdenes                            "
                + "             WHERE  tblDctosOrdenes.idLocal =                  "
                + "               tblDctosOrdenesDetalle.idLocalOrigen            "
                + "             AND  tblDctosOrdenes.idTipoOrden =                "
                + "               tblDctosOrdenesDetalle.idTipoOrdenOrigen        "
                + "             AND  tblDctosOrdenes.idOrden =                    "
                + "             tblDctosOrdenesDetalle.idOrdenOrigen)             "
                + "                                         AS numeroOrden,       "
                + "           ( SELECT  tblDctosOrdenes.ordenCompra               "
                + "             FROM   tblDctosOrdenes                            "
                + "             WHERE  tblDctosOrdenes.idLocal =                  "
                + "               tblDctosOrdenesDetalle.idLocalOrigen            "
                + "             AND  tblDctosOrdenes.idTipoOrden =                "
                + "               tblDctosOrdenesDetalle.idTipoOrdenOrigen        "
                + "             AND  tblDctosOrdenes.idOrden =                    "
                + "               tblDctosOrdenesDetalle.idOrdenOrigen)           "
                + "                                             AS ordenCompra,   "
                + "            MIN(tblMarcas.nombreMarca) AS nombreMarca,         "
                + "       MIN(tblCategorias.nombreCategoria) AS nombreCategoria,  "
                + "            MIN(tblDctosOrdenesDetalle.cantidad) AS cantidad,  "
                + "            MIN( tblDctosOrdenesDetalle.vrVentaUnitario)       "
                + "                                          AS vrVentaUnitario,  "
                + "            MIN( tblDctosOrdenesDetalle.vrVentaOriginal)       "
                + "                                         AS vrVentaOriginal,   "
                + "            MIN  ( tblDctosOrdenesDetalle.porcentajeDscto)     "
                + "                                         AS porcentajeDscto,   "
                + "            SUM (tblDctosOrdenesDetalle.cantidad         /     "
                + "                 tblDctosOrdenesDetalle.unidadVenta      *     "
                + "                  tblDctosOrdenesDetalle.vrVentaUnitario )     "
                + "                                          AS vrVentaConIva,    "
                + "            SUM((tblDctosOrdenesDetalle.cantidad         /     "
                + "                 tblDctosOrdenesDetalle.unidadVenta      *     "
                + "                  tblDctosOrdenesDetalle.vrVentaUnitario) /    "
                + "      ( 1 + ( tblDctosOrdenesDetalle.porcentajeIva / 100)))    "
                + "                                          AS vrVentaSinIva,    "
                + "            MIN(tblDctosOrdenesDetalle.porcentajeIva)          "
                + "                                         AS porcentajeIva,     "
                + "            MIN( tblDctosOrdenesDetalle.vrCosto)               "
                + "                                          AS vrCosto,          "
                + "                      SUM(tblDctosOrdenesDetalle.vrCosto *     "
                + "                          tblDctosOrdenesDetalle.cantidad)     "
                + "                                         AS vrCostoConIva,     "
                + "                   SUM( (tblDctosOrdenesDetalle.cantidad *     "
                + "                         tblDctosOrdenesDetalle.vrCosto ) /    "
                + "         (1 + (tblDctosOrdenesDetalle.porcentajeIva/100)))     "
                + "                                         AS vrCostoSinIva,     "
                + "              SUM(((tblDctosOrdenesDetalle.cantidad      /     "
                + "                 tblDctosOrdenesDetalle.unidadVenta      *     "
                + "                 tblDctosOrdenesDetalle.vrVentaUnitario)  /    "
                + "    ( 1 + ( tblDctosOrdenesDetalle.porcentajeIva / 100))) *    "
                + "    ( 1 - ( tblDctosOrdenesDetalle.vrDsctoPie / 100))     *    "
                + "    ( 1 - ( tblDctosOrdenesDetalle.porcentajeDscto / 100)))    "
                + "                                        AS vrVentaSinDscto,    "
                + "      SUM(((tblDctosOrdenesDetalle.cantidad              /     "
                + "                 tblDctosOrdenesDetalle.unidadVenta      *     "
                + "          tblDctosOrdenesDetalle.vrVentaUnitario) /            "
                + "    ( 1 + ( tblDctosOrdenesDetalle.porcentajeIva / 100))) *    "
                + "    ( 1 - ( tblDctosOrdenesDetalle.vrDsctoPie / 100))     *    "
                + "    ( 1 - ( tblDctosOrdenesDetalle.porcentajeDscto / 100))*    "
                + "           ( tblDctosOrdenesDetalle.porcentajeIva / 100 ))     "
                + "                                         AS vrIvaVenta,        "
                + "            MIN( tblDctosOrdenesDetalle.vrCostoNegociado)      "
                + "                                       AS vrCostoNegociado,    "
                + "             SUM(tblDctosOrdenesDetalle.vrCostoNegociado *     "
                + "                          tblDctosOrdenesDetalle.cantidad)     "
                + "                                  AS vrCostoNegociadoConIva,   "
                + "                   SUM( (tblDctosOrdenesDetalle.cantidad *     "
                + "                tblDctosOrdenesDetalle.vrCostoNegociado ) /    "
                + "         (1 + (tblDctosOrdenesDetalle.porcentajeIva/100)))     "
                + "                                   AS vrCostoNegociadoSinIva,  "
                + "           MAX(tblDctosOrdenesDetalle.referenciaCliente)       "
                + "                                    AS referenciaCliente,      "
                + "           MAX(  tblDctosOrdenesDetalle.comentario)            "
                + "                                    AS comentario,             "
                + "                   SUM( (tblDctosOrdenesDetalle.cantidad *     "
                + "                tblDctosOrdenesDetalle.vrCostoNegociado ) /    "
                + "         (1 + (tblDctosOrdenesDetalle.porcentajeIva/100)))     "
                + "                                   AS vrCostoNegociadoSinIva,  "
                + "            MAX(tblDctosOrdenesDetalle.vrVentaUnitarioSinIva)  "
                + "                                    AS vrVentaUnitarioSinIva   "
                + "   FROM   tblDctosOrdenes                                      "
                + "   INNER JOIN tblDctosOrdenesDetalle                           "
                + "   ON tblDctosOrdenes.IDLOCAL      =                           "
                + "                          tblDctosOrdenesDetalle.IDLOCAL       "
                + "   AND tblDctosOrdenes.IDTIPOORDEN =                           "
                + "                      tblDctosOrdenesDetalle.IDTIPOORDEN       "
                + "   AND tblDctosOrdenes.IDORDEN     =                           "
                + "                          tblDctosOrdenesDetalle.IDORDEN       "
                + "   INNER JOIN                                                  "
                + "   ( SELECT tblDctosOrdenes.idLocal,                           "
                + "       tblDctosOrdenes.idTipoOrden,                            "
                + "       tblDctosOrdenes.idOrden,                                "
                + "       tblPlusFicha.referencia                                 "
                + "   FROM   tblDctosOrdenes                                      "
                + "   INNER JOIN tblPlusFicha                                     "
                + "   ON tblDctosOrdenes.idFicha =                                "
                + "            tblPlusFicha.idFicha                               "
                + "   GROUP BY tblDctosOrdenes.idLocal,                           "
                + "         tblDctosOrdenes.idTipoOrden,                          "
                + "         tblDctosOrdenes.idOrden,                              "
                + "         tblPlusFicha.referencia) AS tmpFIC                    "
                + "   ON  tmpFIC.idLocal  =                                       "
                + "                    tblDctosOrdenesDetalle.idLocalOrigen       "
                + "   AND  tmpFIC.idTipoOrden                                     "
                + "                  = tblDctosOrdenesDetalle.idTipoOrdenOrigen   "
                + "   AND  tmpFIC.idOrden                                         "
                + "                  = tblDctosOrdenesDetalle.idOrdenOrigen       "
                + "   INNER JOIN tblPlus                                          "
                + "   ON tblPlus.idPlu  = tblDctosOrdenesDetalle.idPlu            "
                + "   INNER JOIN tblCategorias                                    "
                + "   ON tblPlus.idLinea       = tblCategorias.idLinea            "
                + "   AND tblPlus.idCategoria  = tblCategorias.idCategoria        "
                + "   INNER JOIN tblMarcas                                        "
                + "   ON tblPlus.idMarca       = tblMarcas.idMarca                "
                + "   INNER JOIN tblDctosCopia                                    "
                + "   ON tblDctosCopia.idTipoOrden                                "
                + "                   =  tblDctosOrdenesDetalle.idTipoOrden       "
                + "   WHERE tblDctosOrdenes.IDTIPOORDEN  =                        "
                + +getIdTipoOrden() + "                                          "
                + "   AND   tblDctosOrdenes.idOrden      =                        "
                + +getIdOrden() + "                                              "
                + "   GROUP BY tblDctosOrdenesDetalle.idLocal,                    "
                + "            tblDctosOrdenesDetalle.idTipoOrden,                "
                + "            tblDctosOrdenesDetalle.idOrden,                    "
                + "            tblDctosCopia.idCopia,                             "
                + "            tblDctosCopia.nombreCopia,                         "
                + "            tblDctosOrdenesDetalle.strIdReferencia,            "
                + "            tblDctosOrdenesDetalle.nombrePlu,                  "
                + "            tblDctosOrdenesDetalle.item,                       "
                + "            tblDctosOrdenesDetalle.idLocalOrigen,              "
                + "            tblDctosOrdenesDetalle.idTipoOrdenOrigen,          "
                + "            tblDctosOrdenesDetalle.idOrdenOrigen,              "
                + "            tmpFIC.referencia                                  "
                + "   ORDER BY   tblDctosOrdenesDetalle.idLocal,                  "
                + "             tblDctosOrdenesDetalle.idTipoOrden,               "
                + "             tblDctosCopia.idCopia,                            "
                + "             tblDctosOrdenesDetalle.idOrden,                   "
                + "             tblDctosOrdenesDetalle.item                       ";

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
                fachadaOrdenVenta = new FachadaOrdenVenta();

                //
                fachadaOrdenVenta.setStrIdReferencia(
                        rs.getString("strIdReferencia"));
                fachadaOrdenVenta.setNombrePlu(rs.getString("nombrePlu"));
                fachadaOrdenVenta.setCantidad(rs.getDouble("cantidad"));
                fachadaOrdenVenta.setVrVentaUnitario(
                        rs.getDouble("vrVentaUnitario"));
                fachadaOrdenVenta.setVrVentaOriginal(
                        rs.getDouble("vrVentaOriginal"));
                fachadaOrdenVenta.setPorcentajeDscto(
                        rs.getDouble("porcentajeDscto"));
                fachadaOrdenVenta.setVrVentaSinDscto(
                        rs.getDouble("vrVentaSinDscto"));
                fachadaOrdenVenta.setPorcentajeIva(
                        rs.getDouble("porcentajeIva"));
                fachadaOrdenVenta.setNombreMarca(rs.getString("nombreMarca"));
                fachadaOrdenVenta.setNombreCategoria(
                        rs.getString("nombreCategoria"));
                fachadaOrdenVenta.setVrCosto(rs.getDouble("vrCosto"));
                fachadaOrdenVenta.setVrCostoConIva(
                        rs.getDouble("vrCostoConIva"));
                fachadaOrdenVenta.setVrCostoSinIva(
                        rs.getDouble("vrCostoSinIva"));
                fachadaOrdenVenta.setVrIvaVenta(rs.getDouble("vrIvaVenta"));
                fachadaOrdenVenta.setVrCostoNegociado(
                        rs.getDouble("vrCostoNegociado"));
                fachadaOrdenVenta.setVrCostoNegociadoConIva(
                        rs.getDouble("vrCostoNegociadoConIva"));
                fachadaOrdenVenta.setVrCostoNegociadoSinIva(
                        rs.getDouble("vrCostoNegociadoSinIva"));
                fachadaOrdenVenta.setReferenciaCliente(
                        rs.getString("referenciaCliente"));
                fachadaOrdenVenta.setComentario(
                        rs.getString("comentario"));
                fachadaOrdenVenta.setNumeroOrden(rs.getInt("numeroOrden"));
                fachadaOrdenVenta.setItem(rs.getInt("item"));
                fachadaOrdenVenta.setOrdenCompra(rs.getString("OrdenCompra"));
                fachadaOrdenVenta.setReferencia(rs.getString("referencia"));
                fachadaOrdenVenta.setVrVentaUnitarioSinIva(
                        rs.getDouble("vrVentaUnitarioSinIva"));
                fachadaOrdenVenta.setIdCopia(rs.getInt("idCopia"));
                fachadaOrdenVenta.setNombreCopia(rs.getString("nombreCopia"));

                //
                contenedor.add(fachadaOrdenVenta);

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
            return contenedor;
        }
    }

    // listaNotaOT
    public Vector listaNotaOT() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        // Variable de fachada de los datos
        FachadaOrdenVenta fachadaOrdenVenta;

        //
        Vector contenedor = new Vector();

        //
        Connection connection = null;

        //
        String selectStr =
                " SELECT tmpDET.idLocal,                          "
                + "        tmpDET.idTipoOrden,                    "
                + "        tmpDET.referenciaCliente,              "
                + "         tmpNOT.item,                          "
                + "        tmpDET.numeroOrden,                    "
                + "        (tmpNOT.cantidad)                      "
                + "                        AS cantidad,           "
                + "        (tmpNOT.porcentajeIva)                 "
                + "                   AS porcentajeIva,           "
                + "        (tmpNOT.vrVentaUnitario /              "
                + "        ( 1 + ( tmpNOT.porcentajeIva / 100)))  "
                + "                   AS vrVentaUnitarioSinIva,   "
                + "           ((tmpNOT.cantidad         /         "
                + "             tmpNOT.unidadVenta      *         "
                + "             tmpNOT.vrVentaUnitario) /         "
                + "        ( 1 + ( tmpNOT.porcentajeIva / 100)))  "
                + "                         AS vrVentaSinIva,     "
                + "            tblDctosCopia.idCopia,             "
                + "            tblDctosCopia.nombreCopia          "
                + " FROM tblDctosOrdenesDetalle tmpNOT            "
                + " INNER JOIN tblDctosOrdenesDetalle             "
                + "                          AS tmpORT            "
                + " ON  tmpORT.idLocal     =                      "
                + "               tmpNOT.idLocalOrigen            "
                + " AND tmpORT.idTipoOrden =                      "
                + "           tmpNOT.idTipoOrdenOrigen            "
                + " AND tmpORT.idOrden     =                      "
                + "               tmpNOT.idOrdenOrigen            "
                + " AND tmpORT.item        =                      "
                + "               tmpNOT.item                     "
                + " INNER JOIN                                    "
                + " ( SELECT tblDctosOrdenes.idLocal,             "
                + "        tblDctosOrdenes.idTipoOrden,           "
                + "        tblDctosOrdenes.idOrden,               "
                + "        MAX(tmpFIC.referenciaCliente)          "
                + "                     AS referenciaCliente,     "
                + "       MAX(tblDctosOrdenes.numeroOrden)        "
                + "                            AS numeroOrden     "
                + " FROM   tblDctosOrdenes                        "
                + " INNER JOIN                                    "
                + " (SELECT tblPlusFicha.idFicha                  "
                + "     ,MAX(tblPlusFicha.referenciaCliente)      "
                + "                   AS referenciaCliente        "
                + " FROM tblPlusFicha                             "
                + " GROUP BY tblPlusFicha.idFicha                 "
                + "        ,tblPlusFicha.referenciaCliente)       "
                + "                               AS tmpFIC       "
                + " ON tmpFIC.idFicha   =                         "
                + "                tblDctosOrdenes.idFicha        "
                + " WHERE tblDctosOrdenes.idTipoOrden = 59        "
                + " AND   tblDctosOrdenes.numeroOrden != 0        "
                + " GROUP BY tblDctosOrdenes.idLocal,             "
                + "        tblDctosOrdenes.idTipoOrden,           "
                + "        tblDctosOrdenes.idOrden)               "
                + "                               AS tmpDET       "
                + " ON  tmpDET.idLocal     =                      "
                + "               tmpORT.idLocalOrigen            "
                + " AND tmpDET.idTipoOrden =                      "
                + "           tmpORT.idTipoOrdenOrigen            "
                + " AND tmpDET.idOrden     =                      "
                + "               tmpORT.idOrdenOrigen            "
                + " INNER JOIN tblDctosCopia                      "
                + "   ON tblDctosCopia.idTipoOrden   =            "
                + "                          tmpDET.idTipoOrden   "
                + " WHERE tmpNOT.idLocal     =                    "
                + getIdLocal() + "                                "
                + " AND   tmpNOT.idTipoOrden =                    "
                + getIdTipoOrden() + "                            "
                + " AND   tmpNOT.idOrden     =                    "
                + getIdOrden() + "                                "
                + " ORDER BY   tmpDET.idLocal,                    "
                + "            tmpDET.idTipoOrden,                "
                + "            tblDctosCopia.idCopia,             "
                + "            tmpDET.numeroOrden ";


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
                fachadaOrdenVenta = new FachadaOrdenVenta();

                //
                fachadaOrdenVenta.setReferenciaCliente(
                        rs.getString("referenciaCliente"));
                fachadaOrdenVenta.setItem(rs.getInt("item"));
                fachadaOrdenVenta.setNumeroOrden(rs.getInt("numeroOrden"));
                fachadaOrdenVenta.setCantidad(rs.getDouble("cantidad"));
                fachadaOrdenVenta.setPorcentajeIva(
                        rs.getDouble("porcentajeIva"));
                fachadaOrdenVenta.setVrVentaUnitarioSinIva(
                        rs.getDouble("vrVentaUnitarioSinIva"));
                fachadaOrdenVenta.setVrVentaSinIva(
                        rs.getDouble("vrVentaSinIva"));
                fachadaOrdenVenta.setIdCopia(rs.getInt("idCopia"));
                fachadaOrdenVenta.setNombreCopia(rs.getString("nombreCopia"));

                //
                contenedor.add(fachadaOrdenVenta);

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
            return contenedor;
        }
    }

    // detallaUnOrdenCompra
    public Vector detallaUnOrdenCompra() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        // Variable de fachada de los datos
        FachadaOrdenVenta fachadaOrdenVenta;

        //
        Vector contenedor = new Vector();

        //
        Connection connection = null;

        //
        String selectStr =
                "SELECT   MIN(tblDctosOrdenesDetalle.strIdReferencia)       "
                + "                                    AS    strIdReferencia, "
                + "         MIN(tblDctosOrdenesDetalle.nombrePlu)             "
                + "                                            AS nombrePlu , "
                + "         MIN(tblDctosOrdenesDetalle.cantidad) AS cantidad, "
                + "         MIN( tblDctosOrdenesDetalle.vrVentaUnitario)      "
                + "                                       AS vrVentaUnitario, "
                + "         MIN( tblDctosOrdenesDetalle.vrVentaOriginal)      "
                + "                                      AS vrVentaOriginal,  "
                + "         MIN( tblDctosOrdenesDetalle.vrCostoIND )          "
                + "                                AS vrCostoUnitarioSinIva,  "
                + "         MIN  ( tblDctosOrdenesDetalle.porcentajeDscto)    "
                + "                                      AS porcentajeDscto,  "
                + "         SUM (tblDctosOrdenesDetalle.cantidad         *    "
                + "               tblDctosOrdenesDetalle.vrVentaUnitario )    "
                + "                                       AS vrVentaConIva,   "
                + "         SUM((tblDctosOrdenesDetalle.cantidad        *     "
                + "               tblDctosOrdenesDetalle.vrVentaUnitario) /   "
                + "   ( 1 + ( tblDctosOrdenesDetalle.porcentajeIva / 100)))   "
                + "                                       AS vrVentaSinIva,   "
                + "         MIN(tblDctosOrdenesDetalle.porcentajeIva)         "
                + "                                      AS porcentajeIva,    "
                + "        MAX(tblCategorias.nombreCategoria)                 "
                + "                                       AS nombreCategoria, "
                + "        MAX(tblMarcas.nombreMarca) AS nombreMarca,         "
                + "         MIN( tblDctosOrdenesDetalle.vrCosto)              "
                + "                                       AS vrCosto,         "
                + "                   SUM(tblDctosOrdenesDetalle.vrCosto *    "
                + "                       tblDctosOrdenesDetalle.cantidad)    "
                + "                                      AS vrCostoConIva,    "
                + "                SUM(  tblDctosOrdenesDetalle.cantidad *    "
                + "             tblDctosOrdenesDetalle.vrCostoIND )           "
                + "                                      AS vrCostoSinIva,    "
                + "           SUM(((tblDctosOrdenesDetalle.cantidad       *   "
                + "              tblDctosOrdenesDetalle.vrVentaUnitario)  /   "
                + " ( 1 + ( tblDctosOrdenesDetalle.porcentajeIva / 100))) *   "
                + " ( 1 - ( tblDctosOrdenesDetalle.vrDsctoPie / 100))     *   "
                + " ( 1 - ( tblDctosOrdenesDetalle.porcentajeDscto / 100)))   "
                + "                                     AS vrVentaSinDscto,   "
                + "   SUM(((tblDctosOrdenesDetalle.cantidad        *          "
                + "       tblDctosOrdenesDetalle.vrVentaUnitario) /           "
                + " ( 1 + ( tblDctosOrdenesDetalle.porcentajeIva / 100))) *   "
                + " ( 1 - ( tblDctosOrdenesDetalle.vrDsctoPie / 100))     *   "
                + " ( 1 - ( tblDctosOrdenesDetalle.porcentajeDscto / 100))*   "
                + "        ( tblDctosOrdenesDetalle.porcentajeIva / 100 ))    "
                + "                                      AS vrIvaVenta,       "
                + "         MIN( tblDctosOrdenesDetalle.vrCostoNegociado)     "
                + "                                    AS vrCostoNegociado,   "
                + "          SUM(tblDctosOrdenesDetalle.vrCostoNegociado *    "
                + "                       tblDctosOrdenesDetalle.cantidad)    "
                + "                               AS vrCostoNegociadoConIva,  "
                + "                SUM( (tblDctosOrdenesDetalle.cantidad *    "
                + "             tblDctosOrdenesDetalle.vrCostoNegociado ) /   "
                + "      (1 + (tblDctosOrdenesDetalle.porcentajeIva/100)))    "
                + "                                AS vrCostoNegociadoSinIva, "
                + "        MAX(tblDctosOrdenesDetalle.referenciaCliente)      "
                + "                                 AS referenciaCliente,     "
                + "        MAX(  tblDctosOrdenesDetalle.comentario)           "
                + "                                 AS comentario,            "
                + "                SUM( (tblDctosOrdenesDetalle.cantidad *    "
                + "             tblDctosOrdenesDetalle.vrCostoNegociado ) /   "
                + "      (1 + (tblDctosOrdenesDetalle.porcentajeIva/100)))    "
                + "                                AS vrCostoNegociadoSinIva  "
                + "FROM   tblCategorias                                       "
                + "INNER JOIN tblPlus                                         "
                + "INNER JOIN tblMarcas                                       "
                + "ON tblPlus.idMarca              = tblMarcas.idMarca        "
                + "ON tblCategorias.idLinea        = tblPlus.idLinea          "
                + "AND tblCategorias.IdCategoria   = tblPlus.idCategoria      "
                + "INNER JOIN tblDctosOrdenes                                 "
                + "INNER JOIN tblDctosOrdenesDetalle                          "
                + "ON tblDctosOrdenes.IDLOCAL      =                          "
                + "                       tblDctosOrdenesDetalle.IDLOCAL      "
                + "AND tblDctosOrdenes.IDTIPOORDEN =                          "
                + "                   tblDctosOrdenesDetalle.IDTIPOORDEN      "
                + "AND tblDctosOrdenes.IDORDEN     =                          "
                + "                       tblDctosOrdenesDetalle.IDORDEN      "
                + "ON tblPlus.idPlu                =                          "
                + "                         tblDctosOrdenesDetalle.IDPLU      "
                + "WHERE tblDctosOrdenes.IDTIPOORDEN =                        "
                + getIdTipoOrden() + "                                        "
                + "AND   tblDctosOrdenes.idOrden     =                        "
                + getIdOrden() + "                                            "
                + "GROUP BY tblDctosOrdenesDetalle.itemPadre,                 "
                + "         tblDctosOrdenesDetalle.item,                      "
                + "         tblDctosOrdenesDetalle.porcentajeIva              ";

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
                fachadaOrdenVenta = new FachadaOrdenVenta();

                //
                fachadaOrdenVenta.setStrIdReferencia(
                        rs.getString("strIdReferencia"));
                fachadaOrdenVenta.setNombrePlu(rs.getString("nombrePlu"));
                fachadaOrdenVenta.setCantidad(rs.getDouble("cantidad"));
                fachadaOrdenVenta.setVrVentaUnitario(
                        rs.getDouble("vrVentaUnitario"));
                fachadaOrdenVenta.setVrVentaOriginal(
                        rs.getDouble("vrVentaOriginal"));
                fachadaOrdenVenta.setVrCostoUnitarioSinIva(
                        rs.getDouble("vrCostoUnitarioSinIva"));
                fachadaOrdenVenta.setPorcentajeDscto(
                        rs.getDouble("porcentajeDscto"));
                fachadaOrdenVenta.setVrVentaSinDscto(
                        rs.getDouble("vrVentaSinDscto"));
                fachadaOrdenVenta.setPorcentajeIva(
                        rs.getDouble("porcentajeIva"));
                fachadaOrdenVenta.setNombreMarca(rs.getString("nombreMarca"));
                fachadaOrdenVenta.setNombreCategoria(
                        rs.getString("nombreCategoria"));
                fachadaOrdenVenta.setVrCosto(rs.getDouble("vrCosto"));
                fachadaOrdenVenta.setVrCostoConIva(
                        rs.getDouble("vrCostoConIva"));
                fachadaOrdenVenta.setVrCostoSinIva(
                        rs.getDouble("vrCostoSinIva"));
                fachadaOrdenVenta.setVrIvaVenta(rs.getDouble("vrIvaVenta"));
                fachadaOrdenVenta.setVrCostoNegociado(
                        rs.getDouble("vrCostoNegociado"));
                fachadaOrdenVenta.setVrCostoNegociadoConIva(
                        rs.getDouble("vrCostoNegociadoConIva"));
                fachadaOrdenVenta.setVrCostoNegociadoSinIva(
                        rs.getDouble("vrCostoNegociadoSinIva"));
                fachadaOrdenVenta.setReferenciaCliente(
                        rs.getString("referenciaCliente"));
                fachadaOrdenVenta.setComentario(
                        rs.getString("comentario"));

                //
                contenedor.add(fachadaOrdenVenta);

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
            return contenedor;
        }
    }

    // detallaUnSuministro
    public Vector detallaUnSuministro() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        // Variable de fachada de los datos
        FachadaOrdenVenta fachadaOrdenVenta;

        Vector contenedor = new Vector();

        //
        Connection connection = null;

        //
        String selectStr =
                "SELECT   MIN(tblDctosOrdenesDetalle.strIdReferencia)       "
                + "                                    AS    strIdReferencia, "
                + "         MIN(tblDctosOrdenesDetalle.nombrePlu)             "
                + "                                            AS nombrePlu , "
                + "         MIN(tblDctosOrdenesDetalle.cantidad) AS cantidad, "
                + "         MIN( tblDctosOrdenesDetalle.vrVentaUnitario)      "
                + "                                       AS vrVentaUnitario, "
                + "         MIN( tblDctosOrdenesDetalle.vrVentaOriginal)      "
                + "                                      AS vrVentaOriginal,  "
                + "         MIN  ( tblDctosOrdenesDetalle.porcentajeDscto)    "
                + "                                      AS porcentajeDscto,  "
                + "         SUM (tblDctosOrdenesDetalle.cantidad         *    "
                + "               tblDctosOrdenesDetalle.vrVentaUnitario )    "
                + "                                       AS vrVentaConIva,   "
                + "         SUM((tblDctosOrdenesDetalle.cantidad        *     "
                + "               tblDctosOrdenesDetalle.vrVentaUnitario) /   "
                + "   ( 1 + ( tblDctosOrdenesDetalle.porcentajeIva / 100)))   "
                + "                                       AS vrVentaSinIva,   "
                + "         MIN(tblDctosOrdenesDetalle.porcentajeIva)         "
                + "                                      AS porcentajeIva,    "
                + "        MAX(tblCategorias.nombreCategoria)                 "
                + "                                       AS nombreCategoria, "
                + "        MAX(tblMarcas.nombreMarca) AS nombreMarca,         "
                + "         MIN( tblDctosOrdenesDetalle.vrCosto)              "
                + "                                       AS vrCosto,         "
                + "                   SUM(tblDctosOrdenesDetalle.vrCosto *    "
                + "                       tblDctosOrdenesDetalle.cantidad)    "
                + "                                      AS vrCostoConIva,    "
                + "                SUM( (tblDctosOrdenesDetalle.cantidad *    "
                + "                      tblDctosOrdenesDetalle.vrCosto ) /   "
                + "      (1 + (tblDctosOrdenesDetalle.porcentajeIva/100)))    "
                + "                                      AS vrCostoSinIva,    "
                + "           SUM(((tblDctosOrdenesDetalle.cantidad       *   "
                + "              tblDctosOrdenesDetalle.vrVentaUnitario)  /   "
                + " ( 1 + ( tblDctosOrdenesDetalle.porcentajeIva / 100))) *   "
                + " ( 1 - ( tblDctosOrdenesDetalle.vrDsctoPie / 100))     *   "
                + " ( 1 - ( tblDctosOrdenesDetalle.porcentajeDscto / 100)))   "
                + "                                     AS vrVentaSinDscto,   "
                + "   SUM(((tblDctosOrdenesDetalle.cantidad        *          "
                + "       tblDctosOrdenesDetalle.vrVentaUnitario) /           "
                + " ( 1 + ( tblDctosOrdenesDetalle.porcentajeIva / 100))) *   "
                + " ( 1 - ( tblDctosOrdenesDetalle.vrDsctoPie / 100))     *   "
                + " ( 1 - ( tblDctosOrdenesDetalle.porcentajeDscto / 100))*   "
                + "        ( tblDctosOrdenesDetalle.porcentajeIva / 100 ))    "
                + "                                      AS vrIvaVenta,       "
                + "         MIN( tblDctosOrdenesDetalle.vrCostoNegociado)     "
                + "                                    AS vrCostoNegociado,   "
                + "          SUM(tblDctosOrdenesDetalle.vrCostoNegociado *    "
                + "                       tblDctosOrdenesDetalle.cantidad)    "
                + "                               AS vrCostoNegociadoConIva,  "
                + "                SUM( (tblDctosOrdenesDetalle.cantidad *    "
                + "             tblDctosOrdenesDetalle.vrCostoNegociado ) /   "
                + "      (1 + (tblDctosOrdenesDetalle.porcentajeIva/100)))    "
                + "                                AS vrCostoNegociadoSinIva, "
                + "   MAX(tblDctosOrdenesDetalle.numeroOrden) AS numeroOrden, "
                + "   MAX(tblDctosOrdenesDetalle.itemOrden) AS itemOrden      "
                + "FROM   tblCategorias                                       "
                + "INNER JOIN tblPlus                                         "
                + "INNER JOIN tblMarcas                                       "
                + "ON tblPlus.idMarca              = tblMarcas.idMarca        "
                + "ON tblCategorias.idLinea        = tblPlus.idLinea          "
                + "AND tblCategorias.IdCategoria   = tblPlus.idCategoria      "
                + "INNER JOIN tblDctosOrdenes                                 "
                + "INNER JOIN tblDctosOrdenesDetalle                          "
                + "ON tblDctosOrdenes.IDLOCAL      =                          "
                + "                       tblDctosOrdenesDetalle.IDLOCAL      "
                + "AND tblDctosOrdenes.IDTIPOORDEN =                          "
                + "                   tblDctosOrdenesDetalle.IDTIPOORDEN      "
                + "AND tblDctosOrdenes.IDORDEN     =                          "
                + "                       tblDctosOrdenesDetalle.IDORDEN      "
                + "ON tblPlus.idPlu                =                          "
                + "                         tblDctosOrdenesDetalle.IDPLU      "
                + "AND   tblDctosOrdenes.IDTIPOORDEN =                        "
                + getIdTipoOrden() + "                                        "
                + "AND   tblDctosOrdenes.idOrden     =                        "
                + getIdOrden() + "                                            "
                + "GROUP BY tblDctosOrdenesDetalle.itemPadre,                 "
                + "         tblDctosOrdenesDetalle.item                       ";

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
                fachadaOrdenVenta = new FachadaOrdenVenta();

                //
                fachadaOrdenVenta.setStrIdReferencia(
                        rs.getString("strIdReferencia"));
                fachadaOrdenVenta.setNombrePlu(rs.getString("nombrePlu"));
                fachadaOrdenVenta.setCantidad(rs.getDouble("cantidad"));
                fachadaOrdenVenta.setVrVentaUnitario(
                        rs.getDouble("vrVentaUnitario"));
                fachadaOrdenVenta.setVrVentaOriginal(
                        rs.getDouble("vrVentaOriginal"));
                fachadaOrdenVenta.setPorcentajeDscto(
                        rs.getDouble("porcentajeDscto"));
                fachadaOrdenVenta.setVrVentaSinDscto(
                        rs.getDouble("vrVentaSinDscto"));
                fachadaOrdenVenta.setPorcentajeIva(
                        rs.getDouble("porcentajeIva"));
                fachadaOrdenVenta.setNombreMarca(rs.getString("nombreMarca"));
                fachadaOrdenVenta.setNombreCategoria(
                        rs.getString("nombreCategoria"));
                fachadaOrdenVenta.setVrCosto(rs.getDouble("vrCosto"));
                fachadaOrdenVenta.setVrCostoConIva(
                        rs.getDouble("vrCostoConIva"));
                fachadaOrdenVenta.setVrCostoSinIva(
                        rs.getDouble("vrCostoSinIva"));
                fachadaOrdenVenta.setVrIvaVenta(rs.getDouble("vrIvaVenta"));
                fachadaOrdenVenta.setVrCostoNegociado(
                        rs.getDouble("vrCostoNegociado"));
                fachadaOrdenVenta.setVrCostoNegociadoConIva(
                        rs.getDouble("vrCostoNegociadoConIva"));
                fachadaOrdenVenta.setVrCostoNegociadoSinIva(
                        rs.getDouble("vrCostoNegociadoSinIva"));
                fachadaOrdenVenta.setNumeroOrden(rs.getInt("numeroOrden"));
                fachadaOrdenVenta.setItemOrden(rs.getInt("itemOrden"));

                //
                contenedor.add(fachadaOrdenVenta);

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
            return contenedor;

        }
    }

    // detallaRemision
    public Vector detallaRemision() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        // Variable de fachada de los datos
        FachadaOrdenVenta fachadaOrdenVenta;

        Vector contenedor = new Vector();

        //
        Connection connection = null;

        //
        String selectStr =
                "   SELECT   MIN(tmpDET.referenciaCliente)                         "
                + "                                      AS    referenciaCliente,  "
                + "            MIN(tblDctosOrdenesDetalle.strIdReferencia)         "
                + "                                       AS    strIdReferencia,   "
                + "            MIN(tblDctosOrdenesDetalle.nombrePlu)               "
                + "                                               AS nombrePlu ,   "
                + "            MIN(tblDctosOrdenesDetalle.cantidad) AS cantidad,   "
                + "            MIN( tblDctosOrdenesDetalle.vrVentaUnitario)        "
                + "                                          AS vrVentaUnitario,   "
                + "            MIN( tblDctosOrdenesDetalle.vrVentaOriginal)        "
                + "                                         AS vrVentaOriginal,    "
                + "            MIN  ( tblDctosOrdenesDetalle.porcentajeDscto)      "
                + "                                         AS porcentajeDscto,    "
                + "            SUM (tblDctosOrdenesDetalle.cantidad         *      "
                + "                  tblDctosOrdenesDetalle.vrVentaUnitario )      "
                + "                                          AS vrVentaConIva,     "
                + "            SUM((tblDctosOrdenesDetalle.cantidad        *       "
                + "                  tblDctosOrdenesDetalle.vrVentaUnitario) /     "
                + "      ( 1 + ( tblDctosOrdenesDetalle.porcentajeIva / 100)))     "
                + "                                          AS vrVentaSinIva,     "
                + "            MIN(tblDctosOrdenesDetalle.porcentajeIva)           "
                + "                                         AS porcentajeIva,      "
                + "           MAX(tblCategorias.nombreCategoria)                   "
                + "                                          AS nombreCategoria,   "
                + "           MAX(tblMarcas.nombreMarca) AS nombreMarca,           "
                + "        MAX(tblDctosOrdenesDetalle.comentario) AS comentario,   "
                + "            MIN( tblDctosOrdenesDetalle.vrCosto)                "
                + "                                          AS vrCosto,           "
                + "                      SUM(tblDctosOrdenesDetalle.vrCosto *      "
                + "                          tblDctosOrdenesDetalle.cantidad)      "
                + "                                         AS vrCostoConIva,      "
                + "                   SUM( (tblDctosOrdenesDetalle.cantidad *      "
                + "                         tblDctosOrdenesDetalle.vrCosto ) /     "
                + "         (1 + (tblDctosOrdenesDetalle.porcentajeIva/100)))      "
                + "                                         AS vrCostoSinIva,      "
                + "              SUM(((tblDctosOrdenesDetalle.cantidad       *     "
                + "                 tblDctosOrdenesDetalle.vrVentaUnitario)  /     "
                + "    ( 1 + ( tblDctosOrdenesDetalle.porcentajeIva / 100))) *     "
                + "    ( 1 - ( tblDctosOrdenesDetalle.vrDsctoPie / 100))     *     "
                + "    ( 1 - ( tblDctosOrdenesDetalle.porcentajeDscto / 100)))     "
                + "                                        AS vrVentaSinDscto,     "
                + "      SUM(((tblDctosOrdenesDetalle.cantidad        *            "
                + "          tblDctosOrdenesDetalle.vrVentaUnitario) /             "
                + "    ( 1 + ( tblDctosOrdenesDetalle.porcentajeIva / 100))) *     "
                + "    ( 1 - ( tblDctosOrdenesDetalle.vrDsctoPie / 100))     *     "
                + "    ( 1 - ( tblDctosOrdenesDetalle.porcentajeDscto / 100))*     "
                + "           ( tblDctosOrdenesDetalle.porcentajeIva / 100 ))      "
                + "                                         AS vrIvaVenta,         "
                + "            MIN( tblDctosOrdenesDetalle.vrCostoNegociado)       "
                + "                                       AS vrCostoNegociado,     "
                + "             SUM(tblDctosOrdenesDetalle.vrCostoNegociado *      "
                + "                          tblDctosOrdenesDetalle.cantidad)      "
                + "                                  AS vrCostoNegociadoConIva,    "
                + "                   SUM( (tblDctosOrdenesDetalle.cantidad *      "
                + "                tblDctosOrdenesDetalle.vrCostoNegociado ) /     "
                + "         (1 + (tblDctosOrdenesDetalle.porcentajeIva/100)))      "
                + "                                   AS vrCostoNegociadoSinIva    "
                + "   FROM   tblCategorias                                         "
                + "   INNER JOIN tblPlus                                           "
                + "   INNER JOIN tblMarcas                                         "
                + "   ON tblPlus.idMarca              = tblMarcas.idMarca          "
                + "   ON tblCategorias.idLinea        = tblPlus.idLinea            "
                + "   AND tblCategorias.IdCategoria   = tblPlus.idCategoria        "
                + "   INNER JOIN tblDctosOrdenes                                   "
                + "   INNER JOIN tblDctosOrdenesDetalle                            "
                + "   ON tblDctosOrdenes.IDLOCAL      =                            "
                + "                          tblDctosOrdenesDetalle.IDLOCAL        "
                + "   AND tblDctosOrdenes.IDTIPOORDEN =                            "
                + "                      tblDctosOrdenesDetalle.IDTIPOORDEN        "
                + "   AND tblDctosOrdenes.IDORDEN     =                            "
                + "                          tblDctosOrdenesDetalle.IDORDEN        "
                + "   ON tblPlus.idPlu                =                            "
                + "                            tblDctosOrdenesDetalle.IDPLU        "
                + "   INNER JOIN                                                   "
                + "      ( SELECT tblPlusFicha.referenciaCliente,                  "
                + "               tblPlusFicha.idPlu                               "
                + "        FROM   tblDctosOrdenes                                  "
                + "        INNER JOIN tblPlusFicha                                 "
                + "        ON tblDctosOrdenes.idCliente =                          "
                + "                           tblPlusFicha.idCliente               "
                + "        AND tblDctosOrdenes.idFicha =                           "
                + "                             tblPlusFicha.idFicha               "
                + "        INNER JOIN                                              "
                + "        ( SELECT tblDctosOrdenesDetalle.idLocalOrigen,          "
                + "             tblDctosOrdenesDetalle.idTipoOrdenOrigen,          "
                + "                 tblDctosOrdenesDetalle.idOrdenOrigen           "
                + "          FROM tblDctosOrdenesDetalle                           "
                + "          WHERE tblDctosOrdenesDetalle.IDTIPOORDEN =            "
                + getIdTipoOrden() + "                                            "
                + "          AND   tblDctosOrdenesDetalle.idOrden     =            "
                + getIdOrden() + " )                                              "
                + "                          AS tmpDET                             "
                + "          ON  tmpDET.idLocalOrigen     =                        "
                + "                               tblDctosOrdenes.idLocal          "
                + "          AND tmpDET.idTipoOrdenOrigen =                        "
                + "                           tblDctosOrdenes.idTipoOrden          "
                + "          AND tmpDET.idOrdenOrigen     =                        "
                + "            tblDctosOrdenes.idOrden )                           "
                + "                            AS tmpDET                           "
                + "   ON tmpDET.idPlu = tblDctosOrdenesDetalle.idPlu               "
                + "AND   tblDctosOrdenes.IDTIPOORDEN =                             "
                + getIdTipoOrden() + "                                            "
                + "AND   tblDctosOrdenes.idOrden     =                            "
                + getIdOrden() + "                                                "
                + "   GROUP BY tblDctosOrdenesDetalle.itemPadre,                   "
                + "            tblDctosOrdenesDetalle.item                         ";

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
                fachadaOrdenVenta = new FachadaOrdenVenta();

                //
                fachadaOrdenVenta.setStrIdReferencia(
                        rs.getString("strIdReferencia"));
                fachadaOrdenVenta.setNombrePlu(rs.getString("nombrePlu"));
                fachadaOrdenVenta.setCantidad(rs.getDouble("cantidad"));
                fachadaOrdenVenta.setVrVentaUnitario(
                        rs.getDouble("vrVentaUnitario"));
                fachadaOrdenVenta.setVrVentaOriginal(
                        rs.getDouble("vrVentaOriginal"));
                fachadaOrdenVenta.setPorcentajeDscto(
                        rs.getDouble("porcentajeDscto"));
                fachadaOrdenVenta.setVrVentaSinDscto(
                        rs.getDouble("vrVentaSinDscto"));
                fachadaOrdenVenta.setPorcentajeIva(
                        rs.getDouble("porcentajeIva"));
                fachadaOrdenVenta.setNombreMarca(rs.getString("nombreMarca"));
                fachadaOrdenVenta.setComentario(rs.getString("comentario"));
                fachadaOrdenVenta.setNombreCategoria(
                        rs.getString("nombreCategoria"));
                fachadaOrdenVenta.setVrCosto(rs.getDouble("vrCosto"));
                fachadaOrdenVenta.setVrCostoConIva(
                        rs.getDouble("vrCostoConIva"));
                fachadaOrdenVenta.setVrCostoSinIva(
                        rs.getDouble("vrCostoSinIva"));
                fachadaOrdenVenta.setVrIvaVenta(rs.getDouble("vrIvaVenta"));
                fachadaOrdenVenta.setVrCostoNegociado(
                        rs.getDouble("vrCostoNegociado"));
                fachadaOrdenVenta.setVrCostoNegociadoConIva(
                        rs.getDouble("vrCostoNegociadoConIva"));
                fachadaOrdenVenta.setVrCostoNegociadoSinIva(
                        rs.getDouble("vrCostoNegociadoSinIva"));
                fachadaOrdenVenta.setReferenciaCliente(
                        rs.getString("referenciaCliente"));

                //
                contenedor.add(fachadaOrdenVenta);

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
            return contenedor;

        }
    }

    // detallaUnCompra
    public Collection detallaUnCompra() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        // Variable de fachada de los datos
        FachadaOrdenVenta fachadaOrdenVenta = new FachadaOrdenVenta();

        //
        Collection list = new ArrayList();

        //
        boolean validaOk = false;
        Connection connection = null;

        //
        String selectStr =
                "SELECT   MIN(tblDctosOrdenesDetalle.strIdReferencia)       "
                + "                                    AS    strIdReferencia, "
                + "         MIN(tblDctosOrdenesDetalle.nombrePlu)             "
                + "                                            AS nombrePlu , "
                + "         MIN(tblDctosOrdenesDetalle.cantidad) AS cantidad, "
                + "         MIN( tblDctosOrdenesDetalle.vrVentaUnitario)      "
                + "                                       AS vrVentaUnitario, "
                + "         MIN( tblDctosOrdenesDetalle.vrVentaOriginal)      "
                + "                                      AS vrVentaOriginal,  "
                + "         MIN  (( tblDctosOrdenesDetalle.vrVentaUnitario -  "
                + "                tblDctosOrdenesDetalle.vrVentaOriginal ) / "
                + "                tblDctosOrdenesDetalle.vrVentaOriginal )   "
                + "                                AS porcentajeDscto,        "
                + "         SUM(tblDctosOrdenesDetalle.cantidad        *      "
                + "                tblDctosOrdenesDetalle.vrVentaUnitario )   "
                + "                                        AS vrVentaConIva,  "
                + "         SUM(tblDctosOrdenesDetalle.cantidad         *     "
                + "                  tblDctosOrdenesDetalle.vrVentaUnitario / "
                + "          (1 + (tblDctosOrdenesDetalle.porcentajeIva/100)))"
                + "                                          AS vrVentaSinIva,"
                + "         MIN(tblDctosOrdenesDetalle.porcentajeIva)         "
                + "                                      AS porcentajeIva,    "
                + "        MAX(tblCategorias.nombreCategoria)                 "
                + "                                       AS nombreCategoria, "
                + "        MAX(tblMarcas.nombreMarca) AS nombreMarca,         "
                + "         MIN( tblDctosOrdenesDetalle.vrCosto)              "
                + "                                       AS vrCosto,         "
                + "         SUM(tblDctosOrdenesDetalle.cantidad        *      "
                + "                tblDctosOrdenesDetalle.vrCosto )           "
                + "                                        AS vrCostoConIva,  "
                + "         SUM(tblDctosOrdenesDetalle.cantidad         *     "
                + "                  tblDctosOrdenesDetalle.vrCosto /         "
                + "          (1 + (tblDctosOrdenesDetalle.porcentajeIva/100)))"
                + "                                          AS vrCostoSinIva "
                + "FROM   tblCategorias                                       "
                + "INNER JOIN tblPlus                                         "
                + "INNER JOIN tblMarcas                                       "
                + "ON tblPlus.idMarca              = tblMarcas.idMarca        "
                + "ON tblCategorias.idLinea        = tblPlus.idLinea          "
                + "AND tblCategorias.IdCategoria   = tblPlus.idCategoria      "
                + "INNER JOIN tblDctosOrdenes                                 "
                + "INNER JOIN tblDctosOrdenesDetalle                          "
                + "ON tblDctosOrdenes.IDLOCAL      =                          "
                + "                       tblDctosOrdenesDetalle.IDLOCAL      "
                + "AND tblDctosOrdenes.IDTIPOORDEN =                          "
                + "                   tblDctosOrdenesDetalle.IDTIPOORDEN      "
                + "AND tblDctosOrdenes.IDORDEN     =                          "
                + "                       tblDctosOrdenesDetalle.IDORDEN      "
                + "ON tblPlus.idPlu                =                          "
                + "                         tblDctosOrdenesDetalle.IDPLU      "
                + "AND   tblDctosOrdenes.IDTIPOORDEN =                        "
                + getIdTipoOrden() + "                                        "
                + "AND   tblDctosOrdenes.idOrden     =                        "
                + getIdOrden() + "                                            "
                + "GROUP BY tblDctosOrdenesDetalle.itemPadre,                 "
                + "         tblDctosOrdenesDetalle.item                       ";

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
                fachadaOrdenVenta.setStrIdReferencia(
                        rs.getString("strIdReferencia"));
                fachadaOrdenVenta.setNombrePlu(rs.getString("nombrePlu"));
                fachadaOrdenVenta.setCantidad(rs.getDouble("cantidad"));
                fachadaOrdenVenta.setVrVentaUnitario(
                        rs.getDouble("vrVentaUnitario"));
                fachadaOrdenVenta.setVrVentaOriginal(
                        rs.getDouble("vrVentaOriginal"));
                fachadaOrdenVenta.setPorcentajeDscto(
                        rs.getDouble("porcentajeDscto"));
                fachadaOrdenVenta.setVrVentaSinIva(
                        rs.getDouble("vrVentaSinIva"));
                fachadaOrdenVenta.setPorcentajeIva(
                        rs.getDouble("porcentajeIva"));
                fachadaOrdenVenta.setNombreMarca(rs.getString("nombreMarca"));
                fachadaOrdenVenta.setNombreCategoria(
                        rs.getString("nombreCategoria"));
                fachadaOrdenVenta.setVrCosto(rs.getDouble("vrCosto"));
                fachadaOrdenVenta.setVrCostoConIva(
                        rs.getDouble("vrCostoConIva"));
                fachadaOrdenVenta.setVrCostoSinIva(
                        rs.getDouble("vrCostoSinIva"));

                //
                list.add(new ColaboraOrdenVentaBean(
                        fachadaOrdenVenta.getStrIdReferencia(),
                        fachadaOrdenVenta.getNombrePlu(),
                        fachadaOrdenVenta.getCantidad(),
                        fachadaOrdenVenta.getPorcentajeIva(),
                        fachadaOrdenVenta.getVrVentaUnitario(),
                        fachadaOrdenVenta.getVrVentaSinIva(),
                        fachadaOrdenVenta.getPorcentajeDscto(),
                        fachadaOrdenVenta.getNombreMarca(),
                        fachadaOrdenVenta.getNombreCategoria(),
                        fachadaOrdenVenta.getVrCosto(),
                        fachadaOrdenVenta.getVrCostoConIva(),
                        fachadaOrdenVenta.getVrCostoSinIva(),
                        0,
                        0,
                        0,
                        0));

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
            return list;

        }
    }

    // detallaUnPedidoOrden
    public Collection detallaUnPedidoOrdenOrigen() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        // Variable de fachada de los datos
        FachadaOrdenVenta fachadaOrdenVenta = new FachadaOrdenVenta();

        //
        Collection list = new ArrayList();

        //
        Connection connection = null;

        //
        String selectStr =
                "SELECT   MIN(tblDctosOrdenesDetalle.strIdReferencia)       "
                + "                                    AS    strIdReferencia, "
                + "         MIN(tblDctosOrdenesDetalle.nombrePlu)             "
                + "                                            AS nombrePlu , "
                + "         MIN(tblDctosOrdenesDetalle.cantidad) AS cantidad, "
                + "         MIN( tblDctosOrdenesDetalle.vrVentaUnitario)      "
                + "                                       AS vrVentaUnitario, "
                + "         MIN( tblDctosOrdenesDetalle.vrVentaOriginal)      "
                + "                                      AS vrVentaOriginal,  "
                + "         MIN  ( tblDctosOrdenesDetalle.porcentajeDscto)    "
                + "                                      AS porcentajeDscto,  "
                + "         SUM (tblDctosOrdenesDetalle.cantidad         *    "
                + "               tblDctosOrdenesDetalle.vrVentaUnitario )    "
                + "                                       AS vrVentaConIva,   "
                + "         SUM((tblDctosOrdenesDetalle.cantidad        *     "
                + "               tblDctosOrdenesDetalle.vrVentaUnitario) /   "
                + "   ( 1 + ( tblDctosOrdenesDetalle.porcentajeIva / 100)))   "
                + "                                       AS vrVentaSinIva,   "
                + "         MIN(tblDctosOrdenesDetalle.porcentajeIva)         "
                + "                                      AS porcentajeIva,    "
                + "        MAX(tblCategorias.nombreCategoria)                 "
                + "                                       AS nombreCategoria, "
                + "        MAX(tblMarcas.nombreMarca) AS nombreMarca,         "
                + "         MIN( tblDctosOrdenesDetalle.vrCosto)              "
                + "                                       AS vrCosto,         "
                + "                   SUM(tblDctosOrdenesDetalle.vrCosto *    "
                + "                       tblDctosOrdenesDetalle.cantidad)    "
                + "                                      AS vrCostoConIva,    "
                + "                SUM( (tblDctosOrdenesDetalle.cantidad *    "
                + "                      tblDctosOrdenesDetalle.vrCosto ) /   "
                + "      (1 + (tblDctosOrdenesDetalle.porcentajeIva/100)))    "
                + "                                      AS vrCostoSinIva,    "
                + "           SUM(((tblDctosOrdenesDetalle.cantidad       *   "
                + "              tblDctosOrdenesDetalle.vrVentaUnitario)  /   "
                + " ( 1 + ( tblDctosOrdenesDetalle.porcentajeIva / 100))) *   "
                + " ( 1 - ( tblDctosOrdenesDetalle.vrDsctoPie / 100))     *   "
                + " ( 1 - ( tblDctosOrdenesDetalle.porcentajeDscto / 100)))   "
                + "                                     AS vrVentaSinDscto,   "
                + "   SUM(((tblDctosOrdenesDetalle.cantidad        *          "
                + "       tblDctosOrdenesDetalle.vrVentaUnitario) /           "
                + " ( 1 + ( tblDctosOrdenesDetalle.porcentajeIva / 100))) *   "
                + " ( 1 - ( tblDctosOrdenesDetalle.vrDsctoPie / 100))     *   "
                + " ( 1 - ( tblDctosOrdenesDetalle.porcentajeDscto / 100))*   "
                + "        ( tblDctosOrdenesDetalle.porcentajeIva / 100 ))    "
                + "                                      AS vrIvaVenta,       "
                + "         MIN( tblDctosOrdenesDetalle.vrCostoNegociado)     "
                + "                                    AS vrCostoNegociado,   "
                + "          SUM(tblDctosOrdenesDetalle.vrCostoNegociado *    "
                + "                       tblDctosOrdenesDetalle.cantidad)    "
                + "                               AS vrCostoNegociadoConIva,  "
                + "                SUM( (tblDctosOrdenesDetalle.cantidad *    "
                + "             tblDctosOrdenesDetalle.vrCostoNegociado ) /   "
                + "      (1 + (tblDctosOrdenesDetalle.porcentajeIva/100)))    "
                + "                                AS vrCostoNegociadoSinIva  "
                + "FROM   tblCategorias                                       "
                + "INNER JOIN tblPlus                                         "
                + "INNER JOIN tblMarcas                                       "
                + "ON tblPlus.idMarca              = tblMarcas.idMarca        "
                + "ON tblCategorias.idLinea        = tblPlus.idLinea          "
                + "AND tblCategorias.IdCategoria   = tblPlus.idCategoria      "
                + "INNER JOIN tblDctosOrdenes                                 "
                + "INNER JOIN tblDctosOrdenesDetalle                          "
                + "ON tblDctosOrdenes.IDLOCAL      =                          "
                + "                       tblDctosOrdenesDetalle.IDLOCAL      "
                + "AND tblDctosOrdenes.IDTIPOORDEN =                          "
                + "                   tblDctosOrdenesDetalle.IDTIPOORDEN      "
                + "AND tblDctosOrdenes.IDORDEN     =                          "
                + "                       tblDctosOrdenesDetalle.IDORDEN      "
                + "ON tblPlus.idPlu                =                          "
                + "                         tblDctosOrdenesDetalle.IDPLU      "
                + "WHERE tblDctosOrdenes.IDTIPOORDEN =                        "
                + getIdTipoOrden() + "                                        "
                + "AND   tblDctosOrdenes.idOrden     =                        "
                + getIdOrden() + "                                            "
                + "AND   tblDctosOrdenesDetalle.idOrdenOrigen     =           "
                + getIdOrdenOrigen() + "                                      "
                + "AND   tblDctosOrdenesDetalle.cantidad          > 0         "
                + "GROUP BY tblDctosOrdenesDetalle.itemPadre,                 "
                + "         tblDctosOrdenesDetalle.item                       "
                + "ORDER BY 2                   ";

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
                fachadaOrdenVenta.setStrIdReferencia(
                        rs.getString("strIdReferencia"));
                fachadaOrdenVenta.setNombrePlu(rs.getString("nombrePlu"));
                fachadaOrdenVenta.setCantidad(rs.getDouble("cantidad"));
                fachadaOrdenVenta.setVrVentaUnitario(
                        rs.getDouble("vrVentaUnitario"));
                fachadaOrdenVenta.setVrVentaOriginal(
                        rs.getDouble("vrVentaOriginal"));
                fachadaOrdenVenta.setPorcentajeDscto(
                        rs.getDouble("porcentajeDscto"));
                fachadaOrdenVenta.setVrVentaSinDscto(
                        rs.getDouble("vrVentaSinDscto"));
                fachadaOrdenVenta.setPorcentajeIva(
                        rs.getDouble("porcentajeIva"));
                fachadaOrdenVenta.setNombreMarca(rs.getString("nombreMarca"));
                fachadaOrdenVenta.setNombreCategoria(
                        rs.getString("nombreCategoria"));
                fachadaOrdenVenta.setVrCosto(rs.getDouble("vrCosto"));
                fachadaOrdenVenta.setVrCostoConIva(
                        rs.getDouble("vrCostoConIva"));
                fachadaOrdenVenta.setVrCostoSinIva(
                        rs.getDouble("vrCostoSinIva"));
                fachadaOrdenVenta.setVrIvaVenta(rs.getDouble("vrIvaVenta"));
                fachadaOrdenVenta.setVrCostoNegociado(
                        rs.getDouble("vrCostoNegociado"));
                fachadaOrdenVenta.setVrCostoNegociadoConIva(
                        rs.getDouble("vrCostoNegociadoConIva"));
                fachadaOrdenVenta.setVrCostoNegociadoSinIva(
                        rs.getDouble("vrCostoNegociadoSinIva"));

                //
                list.add(new ColaboraOrdenVentaBean(
                        fachadaOrdenVenta.getStrIdReferencia(),
                        fachadaOrdenVenta.getNombrePlu(),
                        fachadaOrdenVenta.getCantidad(),
                        fachadaOrdenVenta.getPorcentajeIva(),
                        fachadaOrdenVenta.getVrVentaUnitario(),
                        fachadaOrdenVenta.getVrVentaSinDscto(),
                        fachadaOrdenVenta.getPorcentajeDscto(),
                        fachadaOrdenVenta.getNombreMarca(),
                        fachadaOrdenVenta.getNombreCategoria(),
                        fachadaOrdenVenta.getVrCosto(),
                        fachadaOrdenVenta.getVrCostoConIva(),
                        fachadaOrdenVenta.getVrCostoSinIva(),
                        fachadaOrdenVenta.getVrVentaSinDscto()
                        + fachadaOrdenVenta.getVrIvaVenta(),
                        fachadaOrdenVenta.getVrCostoNegociado(),
                        fachadaOrdenVenta.getVrCostoNegociadoConIva(),
                        fachadaOrdenVenta.getVrCostoNegociadoSinIva()));

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
            return list;

        }
    }

    //
    public String getStrIdReferencia() {
        return strIdReferencia;
    }

    public void setStrIdReferencia(String strIdReferencia) {
        this.strIdReferencia = strIdReferencia;
    }

    public String getNombrePlu() {
        return nombrePlu;
    }

    public void setNombrePlu(String nombrePlu) {
        this.nombrePlu = nombrePlu;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public double getPorcentajeIva() {
        return porcentajeIva;
    }

    public void setPorcentajeIva(double porcentajeIva) {
        this.porcentajeIva = porcentajeIva;
    }

    public double getVrVentaUnitario() {
        return vrVentaUnitario;
    }

    public void setVrVentaUnitario(double vrVentaUnitario) {
        this.vrVentaUnitario = vrVentaUnitario;
    }

    public double getVrVentaSinIva() {
        return vrVentaSinIva;
    }

    public void setVrVentaSinIva(double vrVentaSinIva) {
        this.vrVentaSinIva = vrVentaSinIva;
    }

    public double getPorcentajeDscto() {
        return porcentajeDscto;
    }

    public void setPorcentajeDscto(double porcentajeDscto) {
        this.porcentajeDscto = porcentajeDscto;
    }

    public String getNombreMarca() {
        return nombreMarca;
    }

    public void setNombreMarca(String nombreMarca) {
        this.nombreMarca = nombreMarca;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public double getVrCosto() {
        return vrCosto;
    }

    public void setVrCosto(double vrCosto) {
        this.vrCosto = vrCosto;
    }

    public double getVrCostoConIva() {
        return vrCostoConIva;
    }

    public void setVrCostoConIva(double vrCostoConIva) {
        this.vrCostoConIva = vrCostoConIva;
    }

    public double getVrCostoSinIva() {
        return vrCostoSinIva;
    }

    public void setVrCostoSinIva(double vrCostoSinIva) {
        this.vrCostoSinIva = vrCostoSinIva;
    }

    public double getVrVentaConIvaSinDscto() {
        return vrVentaConIvaSinDscto;
    }

    public void setVrVentaConIvaSinDscto(double vrVentaConIvaSinDscto) {
        this.vrVentaConIvaSinDscto = vrVentaConIvaSinDscto;
    }

    public double getVrCostoNegociado() {
        return vrCostoNegociado;
    }

    public void setVrCostoNegociado(double vrCostoNegociado) {
        this.vrCostoNegociado = vrCostoNegociado;
    }

    public double getVrCostoNegociadoConIva() {
        return vrCostoNegociadoConIva;
    }

    public void setVrCostoNegociadoConIva(double vrCostoNegociadoConIva) {
        this.vrCostoNegociadoConIva = vrCostoNegociadoConIva;
    }

    public double getVrCostoNegociadoSinIva() {
        return vrCostoNegociadoSinIva;
    }

    public void setVrCostoNegociadoSinIva(double vrCostoNegociadoSinIva) {
        this.vrCostoNegociadoSinIva = vrCostoNegociadoSinIva;
    }
}
