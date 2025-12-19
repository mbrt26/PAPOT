package com.solucionesweb.losbeans.colaboraciones;

// Importa la clase helper JDBCAccess que contiene acceso a la base de datos
import com.solucionesweb.lasayudas.JDBCAccess;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaTerceroBean;

// Importa los paquetes de java
import java.sql.*;
import java.io.*;
import java.util.*;
import java.io.IOException.*;

// Importa los paquetes de javax
import javax.naming.*;

public class ColaboraTercero extends FachadaTerceroBean
        implements Serializable {

    // Nombre JNDI del Data Source que esta clase requiere
    private static final String DATA_SOURCE_NAME = "CommerceAccess";
    // Clase helper usada para acceder a la base de datos
    private JDBCAccess jdbcAccess;

    // Metodo constructor por defecto sin parametros
    public ColaboraTercero() {

        // Al instanciar el Bean establece la conexion a la base de datos
        jdbcAccess = new JDBCAccess(DATA_SOURCE_NAME);
    }

    // Metodo listaVariosClientes
    public Vector listaVariosClientes() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        String selectStr =
                "SELECT tblTerceros.idCliente,            "
                + "       tblTerceros.idTercero,            "
                + "       tblTerceros.tipoIdTercero,        "
                + "       tblTerceros.digitoVerificacion,   "
                + "       tblTerceros.idTipoTercero,        "
                + "       tblTerceros.idPersona,            "
                + "       tblTerceros.idAutoRetenedor,      "
                + "       tblTerceros.idRegimen,            "
                + "       tblTerceros.nombreTercero,        "
                + "       tblTerceros.direccionTercero,     "
                + "       tblTerceros.idDptoCiudad,         "
                + "       tblTerceros.telefonoFijo,         "
                + "       tblTerceros.telefonoCelular,      "
                + "       tblTerceros.telefonoFax,          "
                + "       tblTerceros.email,                "
                + "       tblTerceros.idFormaPago,          "
                + "       tblTerceros.estado,               "
                + "       tblTerceros.idRuta,               "
                + "       tblTerceros.nombreEmpresa,        "
                + "       tblTerceros.cupoCredito,          "
                + "       tblTerceros.indicador,            "
                + " (SELECT tblCiudades.nombreCiudad + '/' +"
                + "         tblCiudades.nombreDpto          "
                + "  FROM tblCiudades                       "
                + "  WHERE tblCiudades.idCiudad      =      "
                + "            tblTerceros.idDptoCiudad)    "
                + "                      AS ciudadTercero,  "
                + "       tblTerceros.contactoTercero,      "
                + "       tblTerceros.idListaPrecio,        "
                + "       tblTerceros.idVendedor,           "
                + "       00 AS idLocalTercero              "
                + "FROM   tblTerceros                       "
                + "WHERE  tblTerceros.idTipoTercero =       "
                + getIdTipoTercero() + "                    "
                + "AND    tblTerceros.idCliente   IN       ("
                + getIdClienteCadena() + ")                 "
                + "ORDER BY tblTerceros.nombreTercero       ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaTerceroBean fachadaTerceroBean;

            while (rs.next()) {

                //
                fachadaTerceroBean = new FachadaTerceroBean();

                //
                fachadaTerceroBean.setIdCliente(rs.getString("idCliente"));
                fachadaTerceroBean.setIdTercero(rs.getDouble("idTercero"));
                fachadaTerceroBean.setTipoIdTercero(
                        rs.getString("tipoIdTercero"));
                fachadaTerceroBean.setDigitoVerificacion(
                        rs.getInt("digitoVerificacion"));
                fachadaTerceroBean.setIdTipoTercero(rs.getInt("idTipoTercero"));
                fachadaTerceroBean.setIdPersona(rs.getInt("idPersona"));
                fachadaTerceroBean.setIdAutoRetenedor(
                        rs.getInt("idAutoRetenedor"));
                fachadaTerceroBean.setIdRegimen(rs.getString("idRegimen"));
                fachadaTerceroBean.setNombreTercero(
                        rs.getString("nombreTercero"));
                fachadaTerceroBean.setDireccionTercero(
                        rs.getString("direccionTercero"));
                fachadaTerceroBean.setIdDptoCiudad(rs.getInt("idDptoCiudad"));
                fachadaTerceroBean.setTelefonoFijo(
                        rs.getString("telefonoFijo"));
                fachadaTerceroBean.setTelefonoCelular(
                        rs.getString("telefonoCelular"));
                fachadaTerceroBean.setTelefonoFax(rs.getString("telefonoFax"));
                fachadaTerceroBean.setEmail(rs.getString("email"));
                fachadaTerceroBean.setIdFormaPago(rs.getInt("idFormaPago"));
                fachadaTerceroBean.setEstado(rs.getInt("estado"));
                fachadaTerceroBean.setIdRuta(rs.getString("idRuta"));
                fachadaTerceroBean.setNombreEmpresa(
                        rs.getString("nombreEmpresa"));
                fachadaTerceroBean.setCupoCredito(rs.getDouble("cupoCredito"));
                fachadaTerceroBean.setIndicador(rs.getInt("indicador"));
                fachadaTerceroBean.setCiudadTercero(
                        rs.getString("ciudadTercero"));
                fachadaTerceroBean.setContactoTercero(
                        rs.getString("contactoTercero"));
                fachadaTerceroBean.setIdListaPrecio(rs.getInt("idListaPrecio"));
                fachadaTerceroBean.setIdVendedor(rs.getDouble("idVendedor"));
                fachadaTerceroBean.setIdLocalTercero(
                        rs.getInt("idLocalTercero"));

                //
                contenedor.add(fachadaTerceroBean);
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

            // Cierra connection
            jdbcAccess.cleanup(connection, selectStatement, null);
            return contenedor;

        }
    }

    // Metodo listaUnTercero
    public Vector listaUnTercero() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        boolean validaOk = false;
        Connection connection = null;

        String selectStr =
                "SELECT tblTerceros.idCliente,            "
                + "       tblTerceros.idTercero,            "
                + "       tblTerceros.tipoIdTercero,        "
                + "       tblTerceros.digitoVerificacion,   "
                + "       tblTerceros.idTipoTercero,        "
                + "       tblTerceros.idPersona,            "
                + "       tblTerceros.idAutoRetenedor,      "
                + "       tblTerceros.idRegimen,            "
                + "       tblTerceros.nombreTercero,        "
                + "       tblTerceros.direccionTercero,     "
                + "       tblTerceros.idDptoCiudad,         "
                + "       tblTerceros.telefonoFijo,         "
                + "       tblTerceros.telefonoCelular,      "
                + "       tblTerceros.telefonoFax,          "
                + "       tblTerceros.email,                "
                + "       tblTerceros.idFormaPago,          "
                + "       tblTerceros.estado,               "
                + "       tblTerceros.idRuta,               "
                + "       tblTerceros.nombreEmpresa,        "
                + "       tblTerceros.cupoCredito,          "
                + "       tblTerceros.indicador,            "
                + " (SELECT tblCiudades.nombreCiudad + '/' +"
                + "         tblCiudades.nombreDpto          "
                + "  FROM tblCiudades                       "
                + "  WHERE tblCiudades.idCiudad      =      "
                + "            tblTerceros.idDptoCiudad)    "
                + "                      AS ciudadTercero,  "
                + "       tblTerceros.contactoTercero,      "
                + "       tblTerceros.idListaPrecio,        "
                + "       tblTerceros.idVendedor,           "
                + "       00 AS idLocalTercero              "
                + "FROM   tblTerceros                       "
                + "WHERE  tblTerceros.idTipoTercero =       "
                + getIdTipoTercero() + "                    "
                + "AND    tblTerceros.idCliente   =        '"
                + getIdCliente() + "'                       "
                + "ORDER BY tblTerceros.nombreTercero       ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaTerceroBean fachadaTerceroBean;

            while (rs.next()) {

                //
                fachadaTerceroBean = new FachadaTerceroBean();

                //
                fachadaTerceroBean.setIdCliente(rs.getString("idCliente"));
                fachadaTerceroBean.setIdTercero(rs.getDouble("idTercero"));
                fachadaTerceroBean.setTipoIdTercero(
                        rs.getString("tipoIdTercero"));
                fachadaTerceroBean.setDigitoVerificacion(
                        rs.getInt("digitoVerificacion"));
                fachadaTerceroBean.setIdTipoTercero(rs.getInt("idTipoTercero"));
                fachadaTerceroBean.setIdPersona(rs.getInt("idPersona"));
                fachadaTerceroBean.setIdAutoRetenedor(
                        rs.getInt("idAutoRetenedor"));
                fachadaTerceroBean.setIdRegimen(rs.getString("idRegimen"));
                fachadaTerceroBean.setNombreTercero(
                        rs.getString("nombreTercero"));
                fachadaTerceroBean.setDireccionTercero(
                        rs.getString("direccionTercero"));
                fachadaTerceroBean.setIdDptoCiudad(rs.getInt("idDptoCiudad"));
                fachadaTerceroBean.setTelefonoFijo(
                        rs.getString("telefonoFijo"));
                fachadaTerceroBean.setTelefonoCelular(
                        rs.getString("telefonoCelular"));
                fachadaTerceroBean.setTelefonoFax(rs.getString("telefonoFax"));
                fachadaTerceroBean.setEmail(rs.getString("email"));
                fachadaTerceroBean.setIdFormaPago(rs.getInt("idFormaPago"));
                fachadaTerceroBean.setEstado(rs.getInt("estado"));
                fachadaTerceroBean.setIdRuta(rs.getString("idRuta"));
                fachadaTerceroBean.setNombreEmpresa(
                        rs.getString("nombreEmpresa"));
                fachadaTerceroBean.setCupoCredito(rs.getDouble("cupoCredito"));
                fachadaTerceroBean.setIndicador(rs.getInt("indicador"));
                fachadaTerceroBean.setCiudadTercero(
                        rs.getString("ciudadTercero"));
                fachadaTerceroBean.setContactoTercero(
                        rs.getString("contactoTercero"));
                fachadaTerceroBean.setIdListaPrecio(rs.getInt("idListaPrecio"));
                fachadaTerceroBean.setIdVendedor(rs.getDouble("idVendedor"));
                fachadaTerceroBean.setIdLocalTercero(
                        rs.getInt("idLocalTercero"));

                //
                contenedor.add(fachadaTerceroBean);

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

            // Cierra connection
            jdbcAccess.cleanup(connection, selectStatement, null);
            return contenedor;

        }
    }

    // Metodo listaUnTerceroSoftLand
    public Vector listaUnTerceroSoftLand() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        String selectStr =
                "SELECT tblTerceros.idCliente,            "
                + "       tblTerceros.idTercero,            "
                + "       tblTerceros.tipoIdTercero,        "
                + "       tblTerceros.digitoVerificacion,   "
                + "       tblTerceros.idTipoTercero,        "
                + "       tblTerceros.idPersona,            "
                + "       tblTerceros.idAutoRetenedor,      "
                + "       tblTerceros.idRegimen,            "
                + "       tblTerceros.nombreTercero,        "
                + "       tblTerceros.direccionTercero,     "
                + "       tblTerceros.idDptoCiudad,         "
                + "       tblTerceros.telefonoFijo,         "
                + "       tblTerceros.telefonoCelular,      "
                + "       tblTerceros.telefonoFax,          "
                + "       tblTerceros.email,                "
                + "       tblTerceros.idFormaPago,          "
                + "       tblTerceros.estado,               "
                + "       tblTerceros.idRuta,               "
                + "       tblTerceros.nombreEmpresa,        "
                + "       tblTerceros.cupoCredito,          "
                + "       tblTerceros.indicador,            "
                + " (SELECT tblCiudades.nombreCiudad        "
                + "  FROM tblCiudades                       "
                + "  WHERE tblCiudades.idCiudad      =      "
                + "            tblTerceros.idDptoCiudad)    "
                + "                      AS ciudadTercero,  "
                + " (SELECT tblCiudades.nombreDpto          "
                + "  FROM tblCiudades                       "
                + "  WHERE tblCiudades.idCiudad      =      "
                + "            tblTerceros.idDptoCiudad)    "
                + "                AS departamentoTercero,  "
                + "       tblTerceros.contactoTercero,      "
                + "       tblTerceros.idListaPrecio,        "
                + "       tblTerceros.idVendedor,           "
                + "       00 AS idLocalTercero              "
                + "FROM   tblTerceros                       "
                + "WHERE  tblTerceros.idTipoTercero =       "
                + getIdTipoTercero() + "                    "
                + "AND    tblTerceros.idCliente   =        '"
                + getIdCliente() + "'                       "
                + "ORDER BY tblTerceros.nombreTercero       ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaTerceroBean fachadaTerceroBean;

            while (rs.next()) {

                //
                fachadaTerceroBean = new FachadaTerceroBean();

                //
                fachadaTerceroBean.setIdCliente(
                        rs.getString("idCliente"));
                fachadaTerceroBean.setDigitoVerificacion(
                        rs.getInt("digitoVerificacion"));
                fachadaTerceroBean.setNombreTercero(
                        rs.getString("nombreTercero"));
                fachadaTerceroBean.setDireccionTercero(
                        rs.getString("direccionTercero"));
                fachadaTerceroBean.setCiudadTercero(
                        rs.getString("ciudadTercero"));
                fachadaTerceroBean.setTelefonoFijo(
                        rs.getString("telefonoFijo"));
                fachadaTerceroBean.setTelefonoCelular(
                        rs.getString("telefonoCelular"));
                fachadaTerceroBean.setTelefonoFax(
                        rs.getString("telefonoFax"));
                fachadaTerceroBean.setEmail(rs.getString("email"));
                fachadaTerceroBean.setContactoTercero(
                        rs.getString("contactoTercero"));
                fachadaTerceroBean.setIdPersona(rs.getInt("idPersona"));
                fachadaTerceroBean.setIdAutoRetenedor(
                        rs.getInt("idAutoRetenedor"));
                fachadaTerceroBean.setIdRegimen(
                        rs.getString("idRegimen"));
                fachadaTerceroBean.setIdDptoCiudad(
                        rs.getString("idDptoCiudad"));
                fachadaTerceroBean.setDepartamentoTercero(
                        rs.getString("departamentoTercero"));

                //
                contenedor.add(fachadaTerceroBean);

                /*
                System.out.println(
                fachadaTerceroBean.getIdClienteSoftLand());
                System.out.println(
                fachadaTerceroBean.getIdSucursalSoftLand());
                System.out.println(
                fachadaTerceroBean.getNombreTerceroSoftLand());
                System.out.println(
                fachadaTerceroBean.getDireccionTerceroSoftLand());
                System.out.println(
                fachadaTerceroBean.getCiudadTerceroSoftLand());
                System.out.println(
                fachadaTerceroBean.getTelefonoFijoSoftLand());
                System.out.println(
                fachadaTerceroBean.getTelefonoCelularSoftLand());
                System.out.println(
                fachadaTerceroBean.getTelefonoFaxSoftLand());
                System.out.println(
                fachadaTerceroBean.getEmailSoftLand());
                System.out.println(
                fachadaTerceroBean.getContactoTerceroSoftLand());
                System.out.println(
                fachadaTerceroBean.getPaisTerceroSoftLand());
                System.out.println(
                fachadaTerceroBean.getTextoVacioSoftLand());
                System.out.println(
                fachadaTerceroBean.getNombreTerceroSoftLand());
                System.out.println(
                fachadaTerceroBean.getTextoVacioSoftLand());
                System.out.println(
                fachadaTerceroBean.getTextoVacioSoftLand());
                System.out.println(
                fachadaTerceroBean.getTextoVacioSoftLand());
                System.out.println(
                fachadaTerceroBean.getTextoVacioSoftLand());
                System.out.println(
                fachadaTerceroBean.getTextoVacioSoftLand());
                System.out.println(
                fachadaTerceroBean.getTextoVacioSoftLand());
                System.out.println(
                fachadaTerceroBean.getIdPersonaSoftLand());
                System.out.println(
                fachadaTerceroBean.getIdAutoRetenedorSoftLand());
                System.out.println(
                fachadaTerceroBean.getIdRegimenSoftLand());
                System.out.println(
                fachadaTerceroBean.getIdCiudadSoftLand());
                System.out.println(
                fachadaTerceroBean.getIdDptoSoftLand());
                System.out.println(
                fachadaTerceroBean.getDepartamentoTerceroSoftLand());*/

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

            // Cierra connection
            jdbcAccess.cleanup(connection, selectStatement, null);
            return contenedor;

        }
    }

    // Metodo listaUnTerceroSoftLand
    public Vector listaAllTerceroSoftLand(String xFechaDcto) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        String selectStr =
                "SELECT tmpCLI.*				   "
                + "FROM ( SELECT tmpTER.idCliente                    "
                + "              ,MAX(tmpTER.nombreTercero)          "
                + "                            AS nombreTercero      "
                + "              ,MAX(tmpTER.digitoVerificacion)     "
                + "                          AS digitoVerificacion   "
                + "              ,MAX(tmpTER.direccionTercero)       "
                + "                          AS direccionTercero     "
                + "              ,MAX(tmpTER.ciudadTercero)          "
                + "                              AS ciudadTercero    "
                + "              ,MAX(tmpTER.telefonoFijo)           "
                + "                              AS telefonoFijo     "
                + "              ,MAX(tmpTER.telefonoCelular)        "
                + "                              AS telefonoCelular  "
                + "              ,MAX(tmpTER.telefonoFax)            "
                + "                              AS telefonoFax	     "
                + "              ,MAX(tmpTER.email)          	     "
                + "                              AS email	     "
                + "              ,MAX(tmpTER.contactoTercero)        "
                + "                              AS contactoTercero  "
                + "              ,MAX(tmpTER.idPersona)              "
                + "                              AS idPersona	     "
                + "              ,MAX(tmpTER.idAutoRetenedor)        "
                + "                              AS idAutoRetenedor  "
                + "              ,MAX(tmpTER.idRegimen)              "
                + "                              AS idRegimen	     "
                + "              ,MAX(tmpTER.idDptoCiudad)           "
                + "                              AS idDptoCiudad     "
                + "              ,MAX(tmpTER.departamentoTercero)    "
                + "                          AS departamentoTercero  "
                + "       FROM                                       "
                + "       (SELECT [idCliente] AS idCliente	     "
                + "              ,digitoVerificacion          	     "
                + "              ,[nombreTercero] 		     "
                + "                            AS nombreTercero      "
                + "              ,[direccionTercero] 		     "
                + "                       AS direccionTercero 	     "
                + "              ,ciudadTercero AS ciudadTercero     "
                + "              ,[telefonoFijo] AS telefonoFijo     "
                + "           ,[telefonoCelular] AS telefonoCelular  "
                + "              ,telefonoFax AS telefonoFax         "
                + "              ,email AS email     		     "
                + "              ,contactoTercero		     "
                + "              ,idPersona			     "
                + "              ,idAutoRetenedor		     "
                + "              ,idRegimen			     "
                + "              ,idDptoCiudad			     "
                + "              ,(SELECT tblCiudades.nombreDpto     "
                + "                FROM tblCiudades		     "
                + "                WHERE tblCiudades.idCiudad =      "
                + "                       tblTerceros.idDptoCiudad)  "
                + "                         AS departamentoTercero   "
                + "        FROM [BDCommerce].[dbo].[tblTerceros]     "
                + "        UNION                                     "
                + "        SELECT [idUsuario]  AS idCliente  	     "
                + "              ,0 AS digitoVerificacion            "
                + "              ,[nombreUsuario] 		     "
                + "                            AS nombreTercero      "
                + "              ,[direccion] AS direccionTercero    "
                + "              ,'' AS ciudadTercero       	     "
                + "              ,[telefono] AS telefonoFijo	     "
                + "              ,[telefono] AS telefonoCelular      "
                + "              ,[telefono] AS telefonoFax    	     "
                + "              ,email AS email           	     "
                + "              ,[nombreUsuario] AS contactoTercero "
                + "              ,0 AS idPersona		     "
                + "              ,0 AS idAutoRetenedor		     "
                + "              ,'NI' AS idRegimen		     "
                + "              ,5154 AS idDptoCiudad		     "
                + "              ,'ANTIOQUIA' AS departamentoTercero "
                + "        FROM [BDCommerce].[dbo].[ctrlUsuarios])   "
                + "                                     AS tmpTER    "
                + "        GROUP BY tmpTER.idCliente) AS tmpCLI	     "
                + "WHERE EXISTS (				     "
                + "SELECT tblDctos.idCliente			     "
                + "FROM tblDctos				     "
                + "WHERE EXISTS (				     "
                + "  SELECT tblDctosOrdenes.IDLOCAL, 		     "
                + "         tblDctosOrdenes.IDTIPOORDEN, 	     "
                + "         tblDctosOrdenes.IDORDEN		     "
                + "  FROM   tblAgendaLogVisitas 		     "
                + "  INNER JOIN tblDctosOrdenes			     "
                + "  ON tblAgendaLogVisitas.IDLOG       = 	     "
                + "                tblDctosOrdenes.IDLOG	     "
                + "  AND tblAgendaLogVisitas.idLocal    =            "
                + "               tblDctosOrdenes.IDLOCAL	     "
                + "  WHERE tblDctosOrdenes.IDLOCAL      = 	     "
                + "                       tblDctos.IDLOCAL	     "
                + "  AND   tblDctosOrdenes.IDTIPOORDEN  =	     "
                + "                  tblDctos.IDTIPOORDEN	     "
                + "  AND   tblDctosOrdenes.IDORDEN      = 	     "
                + "                      tblDctos.IDORDEN )	     "
                + "  AND   tblDctos.fechaDcto   BETWEEN  	    '"
                + xFechaDcto + "' AND                               '"
                + xFechaDcto + "'    	                             "
                + "  AND tmpCLI.idCliente               = 	     "
                + "                     tblDctos.idCliente)   	     ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaTerceroBean fachadaTerceroBean;

            while (rs.next()) {

                //
                fachadaTerceroBean = new FachadaTerceroBean();

                //
                fachadaTerceroBean.setIdCliente(
                        rs.getString("idCliente"));
                fachadaTerceroBean.setDigitoVerificacion(
                        rs.getInt("digitoVerificacion"));
                fachadaTerceroBean.setNombreTercero(
                        rs.getString("nombreTercero"));
                fachadaTerceroBean.setDireccionTercero(
                        rs.getString("direccionTercero"));
                fachadaTerceroBean.setCiudadTercero(
                        rs.getString("ciudadTercero"));
                fachadaTerceroBean.setTelefonoFijo(
                        rs.getString("telefonoFijo"));
                fachadaTerceroBean.setTelefonoCelular(
                        rs.getString("telefonoCelular"));
                fachadaTerceroBean.setTelefonoFax(
                        rs.getString("telefonoFax"));
                fachadaTerceroBean.setEmail(rs.getString("email"));
                fachadaTerceroBean.setContactoTercero(
                        rs.getString("contactoTercero"));
                fachadaTerceroBean.setIdPersona(rs.getInt("idPersona"));
                fachadaTerceroBean.setIdAutoRetenedor(
                        rs.getInt("idAutoRetenedor"));
                fachadaTerceroBean.setIdRegimen(
                        rs.getString("idRegimen"));
                fachadaTerceroBean.setIdDptoCiudad(
                        rs.getString("idDptoCiudad"));
                fachadaTerceroBean.setDepartamentoTercero(
                        rs.getString("departamentoTercero"));

                //
                contenedor.add(fachadaTerceroBean);

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

            // Cierra connection
            jdbcAccess.cleanup(connection, selectStatement, null);
            return contenedor;

        }
    }

    // Metodo listaLog
    public Vector listaLog() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        //
        int xUnLog = 0;

        //
        Connection connection = null;

        String selectStr =
                "SELECT   tmpORG.*,                            	     "
                + "       tmpCLI.*				     "
                + "FROM 					     "
                + "  (SELECT tmpLOG.idLocal,                         "
                + "        tmpLOG.idTipoOrden,             	     "
                + "        tmpLOG.idLog,      			     "
                + "        tmpLOG.idCliente,	   		     "
                + "        MIN(tmpLOG.idOrigenLog) AS idOrigenLog    "
                + " FROM					     "
                + " (SELECT tblDctos.idLocal,                        "
                + "      tblDctos.idTipoOrden,             	     "
                + "      tblDctos.idCliente,			     "
                + "      tblAgendaLogVisitas.idLog,		     "
                + "      1 AS idOrigenLog			     "
                + "  FROM   tblAgendaLogVisitas               	     "
                + "  INNER JOIN tblDctosOrdenes               	     "
                + "  ON tblAgendaLogVisitas.idLog         =   	     "
                + "                  tblDctosOrdenes.idLog 	     "
                + "  AND tblAgendaLogVisitas.idLocal      =   	     "
                + "                tblDctosOrdenes.IDLOCAL 	     "
                + "  AND tblAgendaLogVisitas.idTipoOrden  =   	     "
                + "            tblDctosOrdenes.IDTIPOORDEN 	     "
                + "  INNER JOIN tblDctos                      	     "
                + "  ON  tblDctosOrdenes.IDLOCAL          =   	     "
                + "                       tblDctos.IDLOCAL 	     "
                + "  AND tblDctosOrdenes.IDTIPOORDEN      =   	     "
                + "                   tblDctos.IDTIPOORDEN 	     "
                + "  AND tblDctosOrdenes.IDORDEN          =   	     "
                + "                       tblDctos.IDORDEN           "
                + "   WHERE tblDctos.idLocal               =         "
                + getIdLocal() + "                                   "
                + "   AND   tblDctos.indicador             = 1       "
                + "   AND   tblAgendaLogVisitas.idEstadoTx = 1       "
                + "  UNION 					     "
                + " SELECT tblPagos.idLocal,                         "
                + "      tblPagos.idTipoOrden,             	     "
                + "      tblPagos.nitCC AS idCliente,		     "
                + "      tblAgendaLogVisitas.idLog,		     "
                + "      2 AS idOrigenLog			     "
                + "  FROM   tblAgendaLogVisitas               	     "
                + "  INNER JOIN tblPagos               	      	     "
                + "  ON tblAgendaLogVisitas.idLog         =   	     "
                + "                                   tblPagos.idLog "
                + "  AND tblAgendaLogVisitas.idLocal      =   	     "
                + "                                 tblPagos.IDLOCAL "
                + "  AND tblAgendaLogVisitas.idTipoOrden  =   	     "
                + "                             tblPagos.IDTIPOORDEN "
                + "   WHERE tblPagos.idLocal               =         "
                + getIdLocal() + "                                   "
                + "   AND   tblAgendaLogVisitas.idEstadoTx = 1       "
                + "   AND   tblPagos.indicador             = 1   )   "
                + "                                   AS tmpLOG      "
                + "  GROUP BY tmpLOG.idLocal,                        "
                + "           tmpLOG.idTipoOrden,             	     "
                + "           tmpLOG.idLog,			     "
                + "           tmpLOG.idCliente) AS tmpORG	     "
                + "  INNER JOIN (				     "
                + "           SELECT LTRIM(RTRIM(tmpTER.idCliente))  "
                + "                                 AS idCliente     "
                + "              ,MAX(tmpTER.nombreTercero)          "
                + "                            AS nombreTercero      "
                + "              ,MAX(tmpTER.digitoVerificacion)     "
                + "                          AS digitoVerificacion   "
                + "              ,MAX(tmpTER.direccionTercero)       "
                + "                          AS direccionTercero     "
                + "              ,MAX(tmpTER.ciudadTercero)          "
                + "                              AS ciudadTercero    "
                + "              ,MAX(tmpTER.telefonoFijo)           "
                + "                              AS telefonoFijo     "
                + "              ,MAX(tmpTER.telefonoCelular)        "
                + "                              AS telefonoCelular  "
                + "              ,MAX(tmpTER.telefonoFax)            "
                + "                              AS telefonoFax      "
                + "              ,MAX(tmpTER.email)          	     "
                + "                              AS email	     "
                + "              ,MAX(tmpTER.contactoTercero)        "
                + "                              AS contactoTercero  "
                + "              ,MAX(tmpTER.idPersona)              "
                + "                              AS idPersona	  "
                + "              ,MAX(tmpTER.idAutoRetenedor)        "
                + "                              AS idAutoRetenedor  "
                + "              ,MAX(tmpTER.idRegimen)              "
                + "                              AS idRegimen	  "
                + "              ,MAX(tmpTER.idDptoCiudad)           "
                + "                              AS idDptoCiudad     "
                + "              ,MAX(tmpTER.departamentoTercero)    "
                + "                          AS departamentoTercero  "
                + "              ,MAX(tmpTER.tipoIdTercero)          "
                + "                          AS tipoIdTercero        "
                + "       FROM                                       "
                + "       (SELECT [idCliente] AS idCliente	     "
                + "              ,digitoVerificacion          	     "
                + "              ,[nombreTercero] 		     "
                + "                            AS nombreTercero      "
                + "              ,[direccionTercero] 		     "
                + "                       AS direccionTercero 	     "
                + "              ,ciudadTercero AS ciudadTercero     "
                + "              ,[telefonoFijo] AS telefonoFijo     "
                + "           ,[telefonoCelular] AS telefonoCelular  "
                + "              ,telefonoFax AS telefonoFax         "
                + "              ,email AS email     		     "
                + "              ,contactoTercero		     "
                + "              ,idPersona			     "
                + "              ,idAutoRetenedor		     "
                + "              ,idRegimen			     "
                + "              ,idDptoCiudad			     "
                + "              ,(SELECT tblCiudades.nombreDpto     "
                + "                FROM tblCiudades		     "
                + "                WHERE tblCiudades.idCiudad =      "
                + "                       tblTerceros.idDptoCiudad)  "
                + "                         AS departamentoTercero   "
                + "              ,tipoIdTercero 	             "
                + "        FROM [BDCommerce].[dbo].[tblTerceros]     "
                + "        UNION                                     "
                + "        SELECT STR([idUsuario]) AS idCliente      "
                + "              ,0 AS digitoVerificacion            "
                + "              ,[nombreUsuario] 		     "
                + "                            AS nombreTercero      "
                + "              ,[direccion] AS direccionTercero    "
                + "              ,'' AS ciudadTercero       	     "
                + "              ,[telefono] AS telefonoFijo	     "
                + "              ,[telefono] AS telefonoCelular      "
                + "              ,[telefono] AS telefonoFax          "
                + "              ,email AS email           	     "
                + "              ,[nombreUsuario] AS contactoTercero "
                + "              ,0 AS idPersona		     "
                + "              ,0 AS idAutoRetenedor		     "
                + "              ,'NI' AS idRegimen		     "
                + "              ,5154 AS idDptoCiudad		     "
                + "          ,'ANTIOQUIA' AS departamentoTercero     "
                + "              ,'C' AS tipoIdTercero 	      	     "
                + "        FROM [BDCommerce].[dbo].[ctrlUsuarios]    "
                + "UNION                                             "
                + "        SELECT STR([idLocal]) AS idCliente        "
                + "                ,0 AS digitoVerificacion          "
                + "                ,[nombreLocal] AS nombreTercero   "
                + "                ,[direccion] AS direccionTercero  "
                + "                ,'' AS ciudadTercero       	     "
                + "                ,[telefono] AS telefonoFijo	     "
                + "                ,[telefono] AS telefonoCelular    "
                + "                ,[telefono] AS telefonoFax        "
                + "                ,[email]                          "
                + "                ,[nombreLocal] AS contactoTercero "
                + "                ,0 AS idPersona		   	  "
                + "                ,0 AS idAutoRetenedor             "
                + "                ,'NI' AS idRegimen		  "
                + "                ,5154 AS idDptoCiudad             "
                + "            ,'ANTIOQUIA' AS departamentoTercero   "
                + "              , 'C' AS tipoIdTercero              "
                + "        FROM [BDCommerce].[dbo].[tblLocales] )    "
                + "                                     AS tmpTER    "
                + "        GROUP BY LTRIM(RTRIM(tmpTER.idCliente)))  "
                + "                                       AS tmpCLI  "
                + "ON LTRIM(RTRIM(tmpORG.idCliente))      = 	  "
                + "          RTRIM(LTRIM(tmpCLI.idCliente))	  "
                + "ORDER BY tmpORG.idLog                             ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaTerceroBean fachadaTerceroBean;

            //
            while (rs.next()) {

                //
                fachadaTerceroBean = new FachadaTerceroBean();

                //
                fachadaTerceroBean.setIdLocal(rs.getInt("idLocal"));
                fachadaTerceroBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaTerceroBean.setIdLog(rs.getInt("idLog"));
                fachadaTerceroBean.setIdCliente(
                        rs.getString("idCliente"));
                fachadaTerceroBean.setIdOrigenLog(rs.getInt("idOrigenLog"));
                fachadaTerceroBean.setDigitoVerificacion(
                        rs.getInt("digitoVerificacion"));
                fachadaTerceroBean.setNombreTercero(
                        rs.getString("nombreTercero"));
                fachadaTerceroBean.setDireccionTercero(
                        rs.getString("direccionTercero"));
                fachadaTerceroBean.setCiudadTercero(
                        rs.getString("ciudadTercero"));
                fachadaTerceroBean.setTelefonoFijo(
                        rs.getString("telefonoFijo"));
                fachadaTerceroBean.setTelefonoCelular(
                        rs.getString("telefonoCelular"));
                fachadaTerceroBean.setTelefonoFax(
                        rs.getString("telefonoFax"));
                fachadaTerceroBean.setEmail(rs.getString("email"));
                fachadaTerceroBean.setContactoTercero(
                        rs.getString("contactoTercero"));
                fachadaTerceroBean.setIdPersona(rs.getInt("idPersona"));
                fachadaTerceroBean.setIdAutoRetenedor(
                        rs.getInt("idAutoRetenedor"));
                fachadaTerceroBean.setIdRegimen(
                        rs.getString("idRegimen"));
                fachadaTerceroBean.setIdDptoCiudad(
                        rs.getString("idDptoCiudad"));
                fachadaTerceroBean.setDepartamentoTercero(
                        rs.getString("departamentoTercero"));
                fachadaTerceroBean.setTipoIdTercero(
                        rs.getString("tipoIdTercero"));

                //
                if (xUnLog == 0) {

                    //
                    xUnLog = fachadaTerceroBean.getIdLog();

                }


                // sale cuando cambie idLog
                if (xUnLog != fachadaTerceroBean.getIdLog()) {
                    break;
                }

                //
                contenedor.add(fachadaTerceroBean);

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

            // Cierra connection
            jdbcAccess.cleanup(connection, selectStatement, null);
            return contenedor;

        }
    }

    // Metodo listaAllNombre
    public Vector listaAllNombre() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        String selectStr =
                "SELECT tblTerceros.idCliente,            "
                + "       tblTerceros.idTercero,            "
                + "       tblTerceros.tipoIdTercero,        "
                + "       tblTerceros.digitoVerificacion,   "
                + "       tblTerceros.idTipoTercero,        "
                + "       tblTerceros.idPersona,            "
                + "       tblTerceros.idAutoRetenedor,      "
                + "       tblTerceros.idRegimen,            "
                + "       tblTerceros.nombreTercero,        "
                + "       tblTerceros.direccionTercero,     "
                + "       tblTerceros.idDptoCiudad,         "
                + "       tblTerceros.telefonoFijo,         "
                + "       tblTerceros.telefonoCelular,      "
                + "       tblTerceros.telefonoFax,          "
                + "       tblTerceros.email,                "
                + "       tblTerceros.idFormaPago,          "
                + "       tblTerceros.estado,               "
                + "       tblTerceros.idRuta,               "
                + "       tblTerceros.nombreEmpresa,        "
                + "       tblTerceros.cupoCredito,          "
                + "       tblTerceros.indicador,            "
                + " (SELECT tblCiudades.nombreCiudad + '/' +"
                + "         tblCiudades.nombreDpto          "
                + "  FROM tblCiudades                       "
                + "  WHERE tblCiudades.idCiudad      =      "
                + "            tblTerceros.idDptoCiudad)    "
                + "                      AS ciudadTercero,  "
                + "       tblTerceros.contactoTercero,      "
                + "       tblTerceros.idListaPrecio,        "
                + "       tblTerceros.idVendedor,           "
                + "       00 AS idLocalTercero              "
                + "FROM   tblTerceros                       "
                + "WHERE  tblTerceros.idTipoTercero =       "
                + getIdTipoTercero() + "                    "
                + "AND (  tblTerceros.nombreTercero LIKE    "
                + "('" + getNombreTercero() + "')           "
                + "OR     tblTerceros.nombreEmpresa LIKE    "
                + "('" + getNombreTercero() + "'))          "
                + "ORDER BY tblTerceros.nombreTercero       ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaTerceroBean fachadaTerceroBean;

            while (rs.next()) {

                //
                fachadaTerceroBean = new FachadaTerceroBean();

                //
                fachadaTerceroBean.setIdCliente(rs.getString("idCliente"));
                fachadaTerceroBean.setIdTercero(rs.getDouble("idTercero"));
                fachadaTerceroBean.setTipoIdTercero(
                        rs.getString("tipoIdTercero"));
                fachadaTerceroBean.setDigitoVerificacion(
                        rs.getInt("digitoVerificacion"));
                fachadaTerceroBean.setIdTipoTercero(rs.getInt("idTipoTercero"));
                fachadaTerceroBean.setIdPersona(rs.getInt("idPersona"));
                fachadaTerceroBean.setIdAutoRetenedor(
                        rs.getInt("idAutoRetenedor"));
                fachadaTerceroBean.setIdRegimen(rs.getString("idRegimen"));
                fachadaTerceroBean.setNombreTercero(
                        rs.getString("nombreTercero"));
                fachadaTerceroBean.setDireccionTercero(
                        rs.getString("direccionTercero"));
                fachadaTerceroBean.setIdDptoCiudad(rs.getInt("idDptoCiudad"));
                fachadaTerceroBean.setTelefonoFijo(
                        rs.getString("telefonoFijo"));
                fachadaTerceroBean.setTelefonoCelular(
                        rs.getString("telefonoCelular"));
                fachadaTerceroBean.setTelefonoFax(rs.getString("telefonoFax"));
                fachadaTerceroBean.setEmail(rs.getString("email"));
                fachadaTerceroBean.setIdFormaPago(rs.getInt("idFormaPago"));
                fachadaTerceroBean.setEstado(rs.getInt("estado"));
                fachadaTerceroBean.setIdRuta(rs.getString("idRuta"));
                fachadaTerceroBean.setNombreEmpresa(
                        rs.getString("nombreEmpresa"));
                fachadaTerceroBean.setCupoCredito(rs.getDouble("cupoCredito"));
                fachadaTerceroBean.setIndicador(rs.getInt("indicador"));
                fachadaTerceroBean.setCiudadTercero(
                        rs.getString("ciudadTercero"));
                fachadaTerceroBean.setContactoTercero(
                        rs.getString("contactoTercero"));
                fachadaTerceroBean.setIdListaPrecio(rs.getInt("idListaPrecio"));
                fachadaTerceroBean.setIdVendedor(rs.getDouble("idVendedor"));
                fachadaTerceroBean.setIdLocalTercero(
                        rs.getInt("idLocalTercero"));

                //
                contenedor.add(fachadaTerceroBean);
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

            // Cierra connection
            jdbcAccess.cleanup(connection, selectStatement, null);
            return contenedor;

        }
    }

    // Metodo listaAll
    public Vector listaAll() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        String selectStr =
                "SELECT tblTerceros.idCliente,            "
                + "       tblTerceros.idTercero,            "
                + "       tblTerceros.tipoIdTercero,        "
                + "       tblTerceros.digitoVerificacion,   "
                + "       tblTerceros.idTipoTercero,        "
                + "       tblTerceros.idPersona,            "
                + "       tblTerceros.idAutoRetenedor,      "
                + "       tblTerceros.idRegimen,            "
                + "       tblTerceros.nombreTercero,        "
                + "       tblTerceros.direccionTercero,     "
                + "       tblTerceros.idDptoCiudad,         "
                + "       tblTerceros.telefonoFijo,         "
                + "       tblTerceros.telefonoCelular,      "
                + "       tblTerceros.telefonoFax,          "
                + "       tblTerceros.email,                "
                + "       tblTerceros.idFormaPago,          "
                + "       tblTerceros.estado,               "
                + "       tblTerceros.idRuta,               "
                + "       tblTerceros.nombreEmpresa,        "
                + "       tblTerceros.cupoCredito,          "
                + "       tblTerceros.indicador,            "
                + " (SELECT tblCiudades.nombreCiudad + '/' +"
                + "         tblCiudades.nombreDpto          "
                + "  FROM tblCiudades                       "
                + "  WHERE tblCiudades.idCiudad      =      "
                + "            tblTerceros.idDptoCiudad)    "
                + "                      AS ciudadTercero,  "
                + "       tblTerceros.contactoTercero,      "
                + "       tblTerceros.idListaPrecio,        "
                + "       tblTerceros.idVendedor,           "
                + "       00 AS idLocalTercero              "
                + "FROM   tblTerceros                       "
                + "WHERE  tblTerceros.estado        != 0    "
                + "AND tblTerceros.idTipoTercero     =      "
                + getIdTipoTercero() + "                    "
                + "AND (  tblTerceros.nombreTercero LIKE    "
                + "('" + getNombreTercero() + "')           "
                + "OR     tblTerceros.nombreEmpresa LIKE    "
                + "('" + getNombreTercero() + "'))          "
                + "UNION                                    "
                + "SELECT tblTercerosLocales.idCliente,     "
                + "       tblTerceros.idTercero,            "
                + "       tblTerceros.tipoIdTercero,        "
                + "       tblTerceros.digitoVerificacion,   "
                + "       tblTerceros.idTipoTercero,        "
                + "       tblTerceros.idPersona,            "
                + "       tblTerceros.idAutoRetenedor,      "
                + "       tblTerceros.idRegimen,            "
                + "       tblTerceros.nombreTercero,        "
                + "     tblTercerosLocales.direccionTercero,"
                + "       tblTercerosLocales.idDptoCiudad,  "
                + "       tblTercerosLocales.telefonoFijo,  "
                + "      tblTercerosLocales.telefonoCelular,"
                + "       tblTercerosLocales.telefonoFax,   "
                + "       tblTercerosLocales.email,         "
                + "       tblTerceros.idFormaPago,          "
                + "       tblTercerosLocales.estado,        "
                + "       tblTerceros.idRuta,               "
                + "       tblTercerosLocales.nombreEmpresa, "
                + "       tblTerceros.cupoCredito,          "
                + "       tblTerceros.indicador,            "
                + " (SELECT tblCiudades.nombreCiudad + '/' +"
                + "         tblCiudades.nombreDpto          "
                + "  FROM tblCiudades                       "
                + "  WHERE tblCiudades.idCiudad      =      "
                + "        tblTercerosLocales.idDptoCiudad) "
                + "                      AS ciudadTercero,  "
                + "      tblTercerosLocales.contactoTercero,"
                + "       tblTerceros.idListaPrecio,        "
                + "       tblTercerosLocales.idVendedor,    "
                + "       tblTercerosLocales.idLocalTercero "
                + "FROM  tblTerceros                        "
                + "INNER JOIN tblTercerosLocales            "
                + "ON tblTerceros.idCliente =               "
                + "      tblTercerosLocales.idCliente       "
                + "WHERE  tblTerceros.idTipoTercero =       "
                + getIdTipoTercero() + "                    "
                + "AND    tblTerceros.estado        != 0    "
                + "AND    tblTercerosLocales.estado  = 1    "
                + "AND (  tblTerceros.nombreTercero LIKE    "
                + "('" + getNombreTercero() + "')           "
                + "OR tblTercerosLocales.nombreEmpresa LIKE "
                + "('" + getNombreTercero() + "'))          "
                + "ORDER BY 9 , 26                          ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaTerceroBean fachadaTerceroBean;

            while (rs.next()) {

                //
                fachadaTerceroBean = new FachadaTerceroBean();

                //
                fachadaTerceroBean.setIdCliente(rs.getString("idCliente"));
                fachadaTerceroBean.setIdTercero(rs.getDouble("idTercero"));
                fachadaTerceroBean.setTipoIdTercero(
                        rs.getString("tipoIdTercero"));
                fachadaTerceroBean.setDigitoVerificacion(
                        rs.getInt("digitoVerificacion"));
                fachadaTerceroBean.setIdTipoTercero(rs.getInt("idTipoTercero"));
                fachadaTerceroBean.setIdPersona(rs.getInt("idPersona"));
                fachadaTerceroBean.setIdAutoRetenedor(
                        rs.getInt("idAutoRetenedor"));
                fachadaTerceroBean.setIdRegimen(rs.getString("idRegimen"));
                fachadaTerceroBean.setNombreTercero(
                        rs.getString("nombreTercero"));
                fachadaTerceroBean.setDireccionTercero(
                        rs.getString("direccionTercero"));
                fachadaTerceroBean.setIdDptoCiudad(rs.getInt("idDptoCiudad"));
                fachadaTerceroBean.setTelefonoFijo(
                        rs.getString("telefonoFijo"));
                fachadaTerceroBean.setTelefonoCelular(
                        rs.getString("telefonoCelular"));
                fachadaTerceroBean.setTelefonoFax(rs.getString("telefonoFax"));
                fachadaTerceroBean.setEmail(rs.getString("email"));
                fachadaTerceroBean.setIdFormaPago(rs.getInt("idFormaPago"));
                fachadaTerceroBean.setEstado(rs.getInt("estado"));
                fachadaTerceroBean.setIdRuta(rs.getString("idRuta"));
                fachadaTerceroBean.setNombreEmpresa(
                        rs.getString("nombreEmpresa"));
                fachadaTerceroBean.setCupoCredito(rs.getDouble("cupoCredito"));
                fachadaTerceroBean.setIndicador(rs.getInt("indicador"));
                fachadaTerceroBean.setCiudadTercero(
                        rs.getString("ciudadTercero"));
                fachadaTerceroBean.setContactoTercero(
                        rs.getString("contactoTercero"));
                fachadaTerceroBean.setIdListaPrecio(rs.getInt("idListaPrecio"));
                fachadaTerceroBean.setIdVendedor(rs.getDouble("idVendedor"));
                fachadaTerceroBean.setIdLocalTercero(
                        rs.getInt("idLocalTercero"));

                //
                contenedor.add(fachadaTerceroBean);

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

            // Cierra connection
            jdbcAccess.cleanup(connection, selectStatement, null);
            return contenedor;

        }
    }

    // listaAllTercero
    public Vector listaAllTercero() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        String selectStr =
                "            SELECT tmpRST.idCliente,		   "
                + "                   tmpRST.nombreTercero,		   "
                + "                   tmpRST.direccionTercero,	   "
                + "                   tmpRST.telefonoFijo,		   "
                + "                   tmpRST.ordenSalida		   "
                + "            FROM (                			   "
                + "            SELECT '00'  AS idCliente                 "
                + "                  ,'NN' AS nombreTercero              "
                + "                  ,'NN' AS direccionTercero           "
                + "                  ,'NN' AS telefonoFijo               "
                + "                  ,01   AS ordenSalida                "
                + "            FROM [BDCommerce].[dbo].[ctrlUsuarios]    "
                + "            UNION                                     "
                + "            SELECT  tmpTER.idCliente                  "
                + "                   ,MAX(tmpTER.nombreTercero)         "
                + "                                   AS nombreTercero   "
                + "                   ,MAX(tmpTER.direccionTercero)      "
                + "                                AS direccionTercero   "
                + "                   ,MAX(tmpTER.telefonoFijo)          "
                + "                                    AS telefonoFijo   "
                + "                  ,MAX(tmpTER.ordenSalida)            "
                + "                                   AS ordenSalida     "
                + "            FROM                                      "
                + "            (SELECT [idCliente] AS idCliente          "
                + "                  ,[nombreTercero] AS nombreTercero   "
                + "            ,[direccionTercero] AS direccionTercero   "
                + "                  ,[telefonoFijo] AS telefonoFijo     "
                + "                  ,02   AS ordenSalida                "
                + "              FROM [BDCommerce].[dbo].[tblTerceros]   "
                + "            UNION                                     "
                + "      SELECT LTRIM(RTRIM(STR([idLocal])))             "
                + "                                         AS idCliente "
                + "                   ,[nombreLocal] AS nombreTercero    "
                + "                   ,[direccion]  AS direccionTercero  "
                + "                   ,[telefono] AS telefonoFijo        "
                + "                   ,02   AS ordenSalida               "
                + "            FROM [BDCommerce].[dbo].[tblLocales]      "
                + "            UNION                 		           "
                + "            SELECT LTRIM([idUsuario])  AS idCliente     "
                + "                  ,[nombreUsuario]  AS nombreTercero  "
                + "                  ,[direccion] AS direccionTercero    "
                + "                  ,[telefono] AS telefonoFijo         "
                + "                  ,02   AS ordenSalida                "
                + "            FROM [BDCommerce].[dbo].[ctrlUsuarios])   "
                + "                                       AS tmpTER      "
                + "                       GROUP BY tmpTER.idCliente )    "
                + "                                       AS tmpRST	   "
                + "            GROUP BY tmpRST.idCliente,		   "
                + "                     tmpRST.nombreTercero,		   "
                + "                     tmpRST.direccionTercero,	   "
                + "                     tmpRST.telefonoFijo,		   "
                + "                     tmpRST.ordenSalida		   "
                + "            ORDER BY 5, 2 				   ";

      


        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaTerceroBean fachadaTerceroBean;

            while (rs.next()) {

                //
                fachadaTerceroBean = new FachadaTerceroBean();

                //
                fachadaTerceroBean.setIdCliente(
                        rs.getString("idCliente"));
                fachadaTerceroBean.setNombreTercero(
                        rs.getString("nombreTercero"));
                fachadaTerceroBean.setDireccionTercero(
                        rs.getString("direccionTercero"));
                fachadaTerceroBean.setTelefonoFijo(
                        rs.getString("telefonoFijo"));

                //
                contenedor.add(fachadaTerceroBean);

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

            // Cierra connection
            jdbcAccess.cleanup(connection, selectStatement, null);
            return contenedor;

        }
    }

    // listaUnTerceroUnionFCH
    public FachadaTerceroBean listaUnTerceroUnionFCH() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        //
        FachadaTerceroBean fachadaTerceroBean = new FachadaTerceroBean();

        Connection connection = null;

        String selectStr =

                "   SELECT tmpLST.*                                    "+
                "   FROM                                               "+
                "   ( SELECT tmpRST.idCliente,		               "+
                "               tmpRST.nombreTercero,		       "+
                "               tmpRST.direccionTercero,	       "+
                "               tmpRST.telefonoFijo,		       "+
                "               tmpRST.ordenSalida		       "+
                "        FROM (                			       "+
                "        SELECT '00'  AS idCliente                     "+
                "              ,'NN' AS nombreTercero                  "+
                "              ,'NN' AS direccionTercero               "+
                "              ,'NN' AS telefonoFijo                   "+
                "              ,01   AS ordenSalida                    "+
                "        FROM ctrlUsuarios                             "+
                "        UNION                                         "+
                "        SELECT  tmpTER.idCliente                      "+
                "               ,MAX(tmpTER.nombreTercero)             "+
                "                               AS nombreTercero       "+
                "               ,MAX(tmpTER.direccionTercero)          "+
                "                            AS direccionTercero       "+
                "               ,MAX(tmpTER.telefonoFijo)              "+
                "                                AS telefonoFijo       "+
                "              ,MAX(tmpTER.ordenSalida)                "+
                "                               AS ordenSalida         "+
                "        FROM                                          "+
                "        (SELECT [idCliente] AS idCliente              "+
                "              ,[nombreTercero] AS nombreTercero       "+
                "        ,[direccionTercero] AS direccionTercero       "+
                "              ,[telefonoFijo] AS telefonoFijo         "+
                "              ,02   AS ordenSalida                    "+
                "          FROM tblTerceros                            "+
                "        UNION                                         "+
                "         SELECT STR(LTRIM([idLocal])) AS idCliente    "+
                "               ,[nombreLocal] AS nombreTercero        "+
                "               ,[direccion]  AS direccionTercero      "+
                "               ,[telefono] AS telefonoFijo            "+
                "               ,02   AS ordenSalida                   "+
                "        FROM tblLocales                               "+
                "        UNION                 		               "+
                "        SELECT LTRIM([idUsuario])  AS idCliente        "+
                "              ,[nombreUsuario]  AS nombreTercero      "+
                "              ,[direccion] AS direccionTercero        "+
                "              ,[telefono] AS telefonoFijo             "+
                "              ,02   AS ordenSalida                    "+
                "        FROM ctrlUsuarios) AS tmpTER                  "+
                "                   GROUP BY tmpTER.idCliente )        "+
                "                                   AS tmpRST	       "+
                "        GROUP BY tmpRST.idCliente,		       "+
                "                 tmpRST.nombreTercero,	               "+
                "                 tmpRST.direccionTercero,	       "+
                "                 tmpRST.telefonoFijo,		       "+
                "                 tmpRST.ordenSalida) AS tmpLST        "+
                "	WHERE LTRIM(RTRIM(tmpLST.idCliente)) =        '"
                + getIdCliente() + "'";


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
                fachadaTerceroBean.setIdCliente(
                        rs.getString("idCliente"));
                fachadaTerceroBean.setNombreTercero(
                        rs.getString("nombreTercero"));
                fachadaTerceroBean.setDireccionTercero(
                        rs.getString("direccionTercero"));
                fachadaTerceroBean.setTelefonoFijo(
                        rs.getString("telefonoFijo"));

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

            // Cierra connection
            jdbcAccess.cleanup(connection, selectStatement, null);
            return fachadaTerceroBean;

        }
    }

    // Metodo listaAllEstado
    public Vector listaAllEstado() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        String selectStr =
                "SELECT tblTerceros.idCliente,            "
                + "       tblTerceros.idTercero,            "
                + "       tblTerceros.tipoIdTercero,        "
                + "       tblTerceros.digitoVerificacion,   "
                + "       tblTerceros.idTipoTercero,        "
                + "       tblTerceros.idPersona,            "
                + "       tblTerceros.idAutoRetenedor,      "
                + "       tblTerceros.idRegimen,            "
                + "       tblTerceros.nombreTercero,        "
                + "       tblTerceros.direccionTercero,     "
                + "       tblTerceros.idDptoCiudad,         "
                + "       tblTerceros.telefonoFijo,         "
                + "       tblTerceros.telefonoCelular,      "
                + "       tblTerceros.telefonoFax,          "
                + "       tblTerceros.email,                "
                + "       tblTerceros.idFormaPago,          "
                + "       tblTerceros.estado,               "
                + "       tblTerceros.idRuta,               "
                + "       tblTerceros.nombreEmpresa,        "
                + "       tblTerceros.cupoCredito,          "
                + "       tblTerceros.indicador,            "
                + " (SELECT tblCiudades.nombreCiudad + '/' +"
                + "         tblCiudades.nombreDpto          "
                + "  FROM tblCiudades                       "
                + "  WHERE tblCiudades.idCiudad      =      "
                + "            tblTerceros.idDptoCiudad)    "
                + "                      AS ciudadTercero,  "
                + "       tblTerceros.contactoTercero,      "
                + "       tblTerceros.idListaPrecio,        "
                + "       tblTerceros.idVendedor,           "
                + "       00 AS idLocalTercero              "
                + "FROM   tblTerceros                       "
                + "WHERE  tblTerceros.estado        != 2    "
                + "AND tblTerceros.idTipoTercero     =      "
                + getIdTipoTercero() + "                    "
                + "AND (  tblTerceros.nombreTercero LIKE    "
                + "('" + getNombreTercero() + "')           "
                + "OR     tblTerceros.nombreEmpresa LIKE    "
                + "('" + getNombreTercero() + "'))          "
                + "UNION                                    "
                + "SELECT tblTercerosLocales.idCliente,     "
                + "       tblTerceros.idTercero,            "
                + "       tblTerceros.tipoIdTercero,        "
                + "       tblTerceros.digitoVerificacion,   "
                + "       tblTerceros.idTipoTercero,        "
                + "       tblTerceros.idPersona,            "
                + "       tblTerceros.idAutoRetenedor,      "
                + "       tblTerceros.idRegimen,            "
                + "       tblTerceros.nombreTercero,        "
                + "     tblTercerosLocales.direccionTercero,"
                + "       tblTercerosLocales.idDptoCiudad,  "
                + "       tblTercerosLocales.telefonoFijo,  "
                + "      tblTercerosLocales.telefonoCelular,"
                + "       tblTercerosLocales.telefonoFax,   "
                + "       tblTercerosLocales.email,         "
                + "       tblTerceros.idFormaPago,          "
                + "       tblTercerosLocales.estado,        "
                + "       tblTerceros.idRuta,               "
                + "       tblTercerosLocales.nombreEmpresa, "
                + "       tblTerceros.cupoCredito,          "
                + "       tblTerceros.indicador,            "
                + " (SELECT tblCiudades.nombreCiudad + '/' +"
                + "         tblCiudades.nombreDpto          "
                + "  FROM tblCiudades                       "
                + "  WHERE tblCiudades.idCiudad      =      "
                + "        tblTercerosLocales.idDptoCiudad) "
                + "                      AS ciudadTercero,  "
                + "      tblTercerosLocales.contactoTercero,"
                + "       tblTerceros.idListaPrecio,        "
                + "       tblTercerosLocales.idVendedor,    "
                + "       tblTercerosLocales.idLocalTercero "
                + "FROM  tblTerceros                        "
                + "INNER JOIN tblTercerosLocales            "
                + "ON tblTerceros.idCliente =               "
                + "      tblTercerosLocales.idCliente       "
                + "WHERE  tblTerceros.idTipoTercero =       "
                + getIdTipoTercero() + "                    "
                + "AND    tblTerceros.estado        != 2    "
                + "AND    tblTercerosLocales.estado  = 1    "
                + "AND (  tblTerceros.nombreTercero LIKE    "
                + "('" + getNombreTercero() + "')           "
                + "OR tblTercerosLocales.nombreEmpresa LIKE "
                + "('" + getNombreTercero() + "'))          "
                + "ORDER BY 9 , 26                          ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaTerceroBean fachadaTerceroBean;

            while (rs.next()) {

                //
                fachadaTerceroBean = new FachadaTerceroBean();

                //
                fachadaTerceroBean.setIdCliente(rs.getString("idCliente"));
                fachadaTerceroBean.setIdTercero(rs.getDouble("idTercero"));
                fachadaTerceroBean.setTipoIdTercero(
                        rs.getString("tipoIdTercero"));
                fachadaTerceroBean.setDigitoVerificacion(
                        rs.getInt("digitoVerificacion"));
                fachadaTerceroBean.setIdTipoTercero(rs.getInt("idTipoTercero"));
                fachadaTerceroBean.setIdPersona(rs.getInt("idPersona"));
                fachadaTerceroBean.setIdAutoRetenedor(
                        rs.getInt("idAutoRetenedor"));
                fachadaTerceroBean.setIdRegimen(rs.getString("idRegimen"));
                fachadaTerceroBean.setNombreTercero(
                        rs.getString("nombreTercero"));
                fachadaTerceroBean.setDireccionTercero(
                        rs.getString("direccionTercero"));
                fachadaTerceroBean.setIdDptoCiudad(rs.getInt("idDptoCiudad"));
                fachadaTerceroBean.setTelefonoFijo(
                        rs.getString("telefonoFijo"));
                fachadaTerceroBean.setTelefonoCelular(
                        rs.getString("telefonoCelular"));
                fachadaTerceroBean.setTelefonoFax(rs.getString("telefonoFax"));
                fachadaTerceroBean.setEmail(rs.getString("email"));
                fachadaTerceroBean.setIdFormaPago(rs.getInt("idFormaPago"));
                fachadaTerceroBean.setEstado(rs.getInt("estado"));
                fachadaTerceroBean.setIdRuta(rs.getString("idRuta"));
                fachadaTerceroBean.setNombreEmpresa(
                        rs.getString("nombreEmpresa"));
                fachadaTerceroBean.setCupoCredito(rs.getDouble("cupoCredito"));
                fachadaTerceroBean.setIndicador(rs.getInt("indicador"));
                fachadaTerceroBean.setCiudadTercero(
                        rs.getString("ciudadTercero"));
                fachadaTerceroBean.setContactoTercero(
                        rs.getString("contactoTercero"));
                fachadaTerceroBean.setIdListaPrecio(rs.getInt("idListaPrecio"));
                fachadaTerceroBean.setIdVendedor(rs.getDouble("idVendedor"));
                fachadaTerceroBean.setIdLocalTercero(
                        rs.getInt("idLocalTercero"));

                //
                contenedor.add(fachadaTerceroBean);

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

            // Cierra connection
            jdbcAccess.cleanup(connection, selectStatement, null);
            return contenedor;

        }
    }

    // listaUnTerceroLocal
    public Vector listaUnTerceroLocal() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        String selectStr =
                "SELECT tblTercerosLocales.idCliente,     "
                + "       tblTerceros.idTercero,            "
                + "       tblTerceros.tipoIdTercero,        "
                + "       tblTerceros.digitoVerificacion,   "
                + "       tblTerceros.idTipoTercero,        "
                + "       tblTerceros.idPersona,            "
                + "       tblTerceros.idAutoRetenedor,      "
                + "       tblTerceros.idRegimen,            "
                + "       tblTerceros.nombreTercero,        "
                + "     tblTercerosLocales.direccionTercero,"
                + "       tblTercerosLocales.idDptoCiudad,  "
                + "       tblTercerosLocales.telefonoFijo,  "
                + "      tblTercerosLocales.telefonoCelular,"
                + "       tblTercerosLocales.telefonoFax,   "
                + "       tblTercerosLocales.email,         "
                + "       tblTerceros.idFormaPago,          "
                + "       tblTercerosLocales.estado,        "
                + "       tblTerceros.idRuta,               "
                + "       tblTercerosLocales.nombreEmpresa, "
                + "       tblTerceros.cupoCredito,          "
                + "       tblTerceros.indicador,            "
                + " (SELECT tblCiudades.nombreCiudad + '/' +"
                + "         tblCiudades.nombreDpto          "
                + "  FROM tblCiudades                       "
                + "  WHERE tblCiudades.idCiudad      =      "
                + "        tblTercerosLocales.idDptoCiudad) "
                + "                      AS ciudadTercero,  "
                + "      tblTercerosLocales.contactoTercero,"
                + "       tblTerceros.idListaPrecio,        "
                + "       tblTercerosLocales.idVendedor,    "
                + "       tblTercerosLocales.idLocalTercero "
                + "FROM  tblTerceros                        "
                + "INNER JOIN tblTercerosLocales            "
                + "ON tblTerceros.idCliente =               "
                + "            tblTercerosLocales.idCliente "
                + "WHERE  tblTercerosLocales.idCliente =   '"
                + getIdCliente() + "'                       "
                + "AND tblTerceros.idTipoTercero       =    "
                + getIdTipoTercero() + "                    "
                + "AND    tblTerceros.estado        != 0    "
                + "AND    tblTercerosLocales.estado  = 1    "
                + "AND tblTercerosLocales.idLocalTercero =  "
                + getIdLocalTercero();

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaTerceroBean fachadaTerceroBean;

            while (rs.next()) {

                //
                fachadaTerceroBean = new FachadaTerceroBean();

                //
                fachadaTerceroBean.setIdCliente(rs.getString("idCliente"));
                fachadaTerceroBean.setIdTercero(rs.getDouble("idTercero"));
                fachadaTerceroBean.setTipoIdTercero(
                        rs.getString("tipoIdTercero"));
                fachadaTerceroBean.setDigitoVerificacion(
                        rs.getInt("digitoVerificacion"));
                fachadaTerceroBean.setIdTipoTercero(rs.getInt("idTipoTercero"));
                fachadaTerceroBean.setIdPersona(rs.getInt("idPersona"));
                fachadaTerceroBean.setIdAutoRetenedor(
                        rs.getInt("idAutoRetenedor"));
                fachadaTerceroBean.setIdRegimen(rs.getString("idRegimen"));
                fachadaTerceroBean.setNombreTercero(
                        rs.getString("nombreTercero"));
                fachadaTerceroBean.setDireccionTercero(
                        rs.getString("direccionTercero"));
                fachadaTerceroBean.setIdDptoCiudad(rs.getInt("idDptoCiudad"));
                fachadaTerceroBean.setTelefonoFijo(
                        rs.getString("telefonoFijo"));
                fachadaTerceroBean.setTelefonoCelular(
                        rs.getString("telefonoCelular"));
                fachadaTerceroBean.setTelefonoFax(rs.getString("telefonoFax"));
                fachadaTerceroBean.setEmail(rs.getString("email"));
                fachadaTerceroBean.setIdFormaPago(rs.getInt("idFormaPago"));
                fachadaTerceroBean.setEstado(rs.getInt("estado"));
                fachadaTerceroBean.setIdRuta(rs.getString("idRuta"));
                fachadaTerceroBean.setNombreEmpresa(
                        rs.getString("nombreEmpresa"));
                fachadaTerceroBean.setCupoCredito(rs.getDouble("cupoCredito"));
                fachadaTerceroBean.setIndicador(rs.getInt("indicador"));
                fachadaTerceroBean.setCiudadTercero(
                        rs.getString("ciudadTercero"));
                fachadaTerceroBean.setContactoTercero(
                        rs.getString("contactoTercero"));
                fachadaTerceroBean.setIdListaPrecio(rs.getInt("idListaPrecio"));
                fachadaTerceroBean.setIdVendedor(rs.getDouble("idVendedor"));
                fachadaTerceroBean.setIdLocalTercero(
                        rs.getInt("idLocalTercero"));

                //
                contenedor.add(fachadaTerceroBean);

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

            // Cierra connection
            jdbcAccess.cleanup(connection, selectStatement, null);
            return contenedor;

        }
    }

    // listaUnTerceroLocalFCH
    public FachadaTerceroBean listaUnTerceroLocalFCH() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        //
        FachadaTerceroBean fachadaTerceroBean = new FachadaTerceroBean();

        Connection connection = null;

        String selectStr =
                "SELECT tblTercerosLocales.idCliente,     "
                + "       tblTerceros.idTercero,            "
                + "       tblTerceros.tipoIdTercero,        "
                + "       tblTerceros.digitoVerificacion,   "
                + "       tblTerceros.idTipoTercero,        "
                + "       tblTerceros.idPersona,            "
                + "       tblTerceros.idAutoRetenedor,      "
                + "       tblTerceros.idRegimen,            "
                + "       tblTerceros.nombreTercero,        "
                + "     tblTercerosLocales.direccionTercero,"
                + "       tblTercerosLocales.idDptoCiudad,  "
                + "       tblTercerosLocales.telefonoFijo,  "
                + "      tblTercerosLocales.telefonoCelular,"
                + "       tblTercerosLocales.telefonoFax,   "
                + "       tblTercerosLocales.email,         "
                + "       tblTerceros.idFormaPago,          "
                + "       tblTercerosLocales.estado,        "
                + "       tblTerceros.idRuta,               "
                + "       tblTercerosLocales.nombreEmpresa, "
                + "       tblTerceros.cupoCredito,          "
                + "       tblTerceros.indicador,            "
                + " (SELECT tblCiudades.nombreCiudad + '/' +"
                + "         tblCiudades.nombreDpto          "
                + "  FROM tblCiudades                       "
                + "  WHERE tblCiudades.idCiudad      =      "
                + "        tblTercerosLocales.idDptoCiudad) "
                + "                       AS ciudadTercero, "
                + "      tblTercerosLocales.contactoTercero,"
                + "       tblTerceros.idListaPrecio,        "
                + "       tblTercerosLocales.idVendedor,    "
                + "       tblTercerosLocales.idLocalTercero "
                + "FROM  tblTerceros                        "
                + "INNER JOIN tblTercerosLocales            "
                + "ON tblTerceros.idCliente =               "
                + "            tblTercerosLocales.idCliente "
                + "WHERE  tblTercerosLocales.idCliente =   '"
                + getIdCliente() + "'                       "
                + "AND tblTerceros.idTipoTercero       =    "
                + getIdTipoTercero() + "                    "
                + "AND    tblTerceros.estado        != 0    "
                + "AND    tblTercerosLocales.estado  = 1    "
                + "AND tblTercerosLocales.idLocalTercero =  "
                + getIdLocalTercero();

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
                fachadaTerceroBean.setIdCliente(rs.getString("idCliente"));
                fachadaTerceroBean.setIdTercero(rs.getDouble("idTercero"));
                fachadaTerceroBean.setTipoIdTercero(
                        rs.getString("tipoIdTercero"));
                fachadaTerceroBean.setDigitoVerificacion(
                        rs.getInt("digitoVerificacion"));
                fachadaTerceroBean.setIdTipoTercero(rs.getInt("idTipoTercero"));
                fachadaTerceroBean.setIdPersona(rs.getInt("idPersona"));
                fachadaTerceroBean.setIdAutoRetenedor(
                        rs.getInt("idAutoRetenedor"));
                fachadaTerceroBean.setIdRegimen(rs.getString("idRegimen"));
                fachadaTerceroBean.setNombreTercero(
                        rs.getString("nombreTercero"));
                fachadaTerceroBean.setDireccionTercero(
                        rs.getString("direccionTercero"));
                fachadaTerceroBean.setIdDptoCiudad(rs.getInt("idDptoCiudad"));
                fachadaTerceroBean.setTelefonoFijo(
                        rs.getString("telefonoFijo"));
                fachadaTerceroBean.setTelefonoCelular(
                        rs.getString("telefonoCelular"));
                fachadaTerceroBean.setTelefonoFax(rs.getString("telefonoFax"));
                fachadaTerceroBean.setEmail(rs.getString("email"));
                fachadaTerceroBean.setIdFormaPago(rs.getInt("idFormaPago"));
                fachadaTerceroBean.setEstado(rs.getInt("estado"));
                fachadaTerceroBean.setIdRuta(rs.getString("idRuta"));
                fachadaTerceroBean.setNombreEmpresa(
                        rs.getString("nombreEmpresa"));
                fachadaTerceroBean.setCupoCredito(rs.getDouble("cupoCredito"));
                fachadaTerceroBean.setIndicador(rs.getInt("indicador"));
                fachadaTerceroBean.setCiudadTercero(
                        rs.getString("ciudadTercero"));
                fachadaTerceroBean.setContactoTercero(
                        rs.getString("contactoTercero"));
                fachadaTerceroBean.setIdListaPrecio(rs.getInt("idListaPrecio"));
                fachadaTerceroBean.setIdVendedor(rs.getDouble("idVendedor"));
                fachadaTerceroBean.setIdLocalTercero(
                        rs.getInt("idLocalTercero"));

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

            // Cierra connection
            jdbcAccess.cleanup(connection, selectStatement, null);
            return fachadaTerceroBean;

        }
    }

    // Metodo listaTerceroFCH
    public FachadaTerceroBean listaTerceroFCH() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        //
        FachadaTerceroBean fachadaTerceroBean = new FachadaTerceroBean();

        Connection connection = null;

        String selectStr =
                "SELECT tblTerceros.idCliente,            "
                + "       tblTerceros.idTercero,            "
                + "       tblTerceros.tipoIdTercero,        "
                + "       tblTerceros.digitoVerificacion,   "
                + "       tblTerceros.idTipoTercero,        "
                + "       tblTerceros.idPersona,            "
                + "       tblTerceros.idAutoRetenedor,      "
                + "       tblTerceros.idRegimen,            "
                + "       tblTerceros.nombreTercero,        "
                + "       tblTerceros.direccionTercero,     "
                + "       tblTerceros.idDptoCiudad,         "
                + "       tblTerceros.telefonoFijo,         "
                + "       tblTerceros.telefonoCelular,      "
                + "       tblTerceros.telefonoFax,          "
                + "       tblTerceros.email,                "
                + "       tblTerceros.idFormaPago,          "
                + "       tblTerceros.estado,               "
                + "       tblTerceros.idRuta,               "
                + "       tblTerceros.nombreEmpresa,        "
                + "       tblTerceros.cupoCredito,          "
                + "       tblTerceros.indicador,            "
                + " (SELECT tblCiudades.nombreCiudad + '/' +"
                + "         tblCiudades.nombreDpto          "
                + "  FROM tblCiudades                       "
                + "  WHERE tblCiudades.idCiudad      =      "
                + "            tblTerceros.idDptoCiudad)    "
                + "                      AS ciudadTercero,  "
                + "       tblTerceros.contactoTercero,      "
                + "       tblTerceros.idListaPrecio,        "
                + "       tblTerceros.idVendedor,           "
                + "       00 AS idLocalTercero              "
                + "FROM   tblTerceros                       "
                + "WHERE  tblTerceros.idTipoTercero =       "
                + getIdTipoTercero() + "                    "
                + "AND    tblTerceros.idCliente   =        '"
                + getIdCliente() + "'                       ";

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
                fachadaTerceroBean = new FachadaTerceroBean();

                //
                fachadaTerceroBean.setIdCliente(rs.getString("idCliente"));
                fachadaTerceroBean.setIdTercero(rs.getDouble("idTercero"));
                fachadaTerceroBean.setTipoIdTercero(
                        rs.getString("tipoIdTercero"));
                fachadaTerceroBean.setDigitoVerificacion(
                        rs.getInt("digitoVerificacion"));
                fachadaTerceroBean.setIdTipoTercero(rs.getInt("idTipoTercero"));
                fachadaTerceroBean.setIdPersona(rs.getInt("idPersona"));
                fachadaTerceroBean.setIdAutoRetenedor(
                        rs.getInt("idAutoRetenedor"));
                fachadaTerceroBean.setIdRegimen(rs.getString("idRegimen"));
                fachadaTerceroBean.setNombreTercero(
                        rs.getString("nombreTercero"));
                fachadaTerceroBean.setDireccionTercero(
                        rs.getString("direccionTercero"));
                fachadaTerceroBean.setIdDptoCiudad(rs.getInt("idDptoCiudad"));
                fachadaTerceroBean.setTelefonoFijo(
                        rs.getString("telefonoFijo"));
                fachadaTerceroBean.setTelefonoCelular(
                        rs.getString("telefonoCelular"));
                fachadaTerceroBean.setTelefonoFax(rs.getString("telefonoFax"));
                fachadaTerceroBean.setEmail(rs.getString("email"));
                fachadaTerceroBean.setIdFormaPago(rs.getInt("idFormaPago"));
                fachadaTerceroBean.setEstado(rs.getInt("estado"));
                fachadaTerceroBean.setIdRuta(rs.getString("idRuta"));
                fachadaTerceroBean.setNombreEmpresa(
                        rs.getString("nombreEmpresa"));
                fachadaTerceroBean.setCupoCredito(rs.getDouble("cupoCredito"));
                fachadaTerceroBean.setIndicador(rs.getInt("indicador"));
                fachadaTerceroBean.setCiudadTercero(
                        rs.getString("ciudadTercero"));
                fachadaTerceroBean.setContactoTercero(
                        rs.getString("contactoTercero"));
                fachadaTerceroBean.setIdListaPrecio(rs.getInt("idListaPrecio"));
                fachadaTerceroBean.setIdVendedor(rs.getDouble("idVendedor"));

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
            return fachadaTerceroBean;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {

            // Cierra connection
            jdbcAccess.cleanup(connection, selectStatement, null);
            return fachadaTerceroBean;

        }
    }

    // seleccionaTerceroxNombreIdTipoTercero
    public Vector seleccionaTerceroxNombreIdTipoTercero() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        Vector contenedor = new Vector();
        Connection connection = null;


        String selectStr =
                " SELECT tblTerceros.idCliente,	           	"
                + "        tblTerceros.idTercero,	           	"
                + "        tblTerceros.tipoIdTercero,	   	    "
                + "        tblTerceros.digitoVerificacion,   	"
                + "        tblTerceros.idTipoTercero, 	   	"
                + "        tblTerceros.idPersona, 	        "
                + "        tblTerceros.idAutoRetenedor, 		"
                + "        tblTerceros.idRegimen, 		    "
                + "        tblTerceros.cupoCredito, 		    "
                + "        tblTerceros.nombreTercero, 		"
                + "        tblTerceros.direccionTercero, 		"
                + "        tblTerceros.idDptoCiudad, 		    "
                + "        tblTerceros.telefonoFijo, 		    "
                + "        tblTerceros.telefonoCelular, 		"
                + "        tblTerceros.telefonoFax, 		    "
                + "        tblTerceros.email, 			    "
                + "        tblTerceros.idFormaPago, 		    "
                + "        tblTerceros.estado, 			    "
                + "        tblRegimen.nombreRegimen, 		    "
                + "        tblTipoTercero.nombreTipoTercero, 	"
                + "        tblCiudades.nombreCiudad, 		    "
                + "        tblCiudades.nombreDpto,    	    "
                + "        tblTerceros.idVendedor      	    "
                + " FROM tblRegimen, 				            "
                + "      tblTipoTercero, 				        "
                + "      tblCiudades,				            "
                + "      tblTerceros				            "
                + " WHERE tblTerceros.nombreTercero LIKE     ("
                + getNombreTercero() + ")            "
                + " AND   tblTerceros.idTipoTercero =         "
                + "           tblTipoTercero.idTipoTercero    "
                + " AND   tblTerceros.idRegimen     =         "
                + "                 tblRegimen.idRegimen     	"
                + " AND   tblTerceros.idDptoCiudad            "
                + "              = tblCiudades.idCiudad	    "
                + " AND   tblTerceros.idTipoTercero =         "
                + "         " + getIdTipoTercero() + "        "
                + " ORDER BY  tblTerceros.nombreTercero		";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaTerceroBean fachadaBean;

            while (rs.next()) {

                fachadaBean = new FachadaTerceroBean();

                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setIdTercero(rs.getDouble("idTercero"));
                fachadaBean.setTipoIdTercero(rs.getString("tipoIdTercero"));
                fachadaBean.setDigitoVerificacion(
                        rs.getInt("digitoVerificacion"));
                fachadaBean.setIdTipoTercero(rs.getInt("idTipoTercero"));
                fachadaBean.setIdPersona(rs.getInt("idPersona"));
                fachadaBean.setIdAutoRetenedor(rs.getInt("idAutoRetenedor"));
                fachadaBean.setIdRegimen(rs.getString("idRegimen"));
                fachadaBean.setNombreTercero(rs.getString("nombreTercero"));
                fachadaBean.setDireccionTercero(rs.getString("direccionTercero"));
                fachadaBean.setIdDptoCiudad(rs.getInt("idDptoCiudad"));
                fachadaBean.setTelefonoFijo(rs.getString("telefonoFijo"));
                fachadaBean.setTelefonoCelular(rs.getString("telefonoCelular"));
                fachadaBean.setTelefonoFax(rs.getString("telefonoFax"));
                fachadaBean.setEmail(rs.getString("email"));
                fachadaBean.setIdFormaPago(rs.getInt("idFormaPago"));
                fachadaBean.setNombreRegimen(rs.getString("nombreRegimen"));
                fachadaBean.setNombreTipoTercero(
                        rs.getString("nombreTipoTercero"));
                fachadaBean.setNombreCiudad(rs.getString("nombreCiudad"));
                fachadaBean.setNombreDpto(rs.getString("nombreDpto"));
                fachadaBean.setIdVendedor(rs.getDouble("idVendedor"));
                fachadaBean.setCupoCredito(rs.getString("cupoCredito"));

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
            // Cierra la conexion a la base de datos limpiando la statement usada y la
            // conexion
            return contenedor;
        }
    }
}
