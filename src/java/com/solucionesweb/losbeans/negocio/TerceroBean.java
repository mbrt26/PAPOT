package com.solucionesweb.losbeans.negocio;

// Importa la clase helper JDBCAccess que contiene los accesos a la base de datos
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

// Importa la interface de las constantes
import com.solucionesweb.losbeans.utilidades.IConstantes;

public class TerceroBean extends FachadaTerceroBean
        implements Serializable, IConstantes {

    // Nombre JNDI del Data Source que esta clase requiere
    private static final String DATA_SOURCE_NAME = "CommerceAccess";

    // Clase helper usada para acceder a la base de datos
    private JDBCAccess jdbcAccess;

    // Metodo constructor por defecto sin parametros
    public TerceroBean() {

        // Al instanciar el Bean establece la conexion a la base de datos
        jdbcAccess = new JDBCAccess(DATA_SOURCE_NAME);
    }

    // seleccionaTercero
    public Vector seleccionaTercero() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        Vector contenedor = new Vector();
        Connection connection = null;

        String selectStr
                = " SELECT tblTerceros.idCliente             "
                + "        tblTerceros.idTercero,	           "
                + "        tblTerceros.tipoIdTercero,         "
                + "        tblTerceros.digitoVerificacion,    "
                + "        tblTerceros.idTipoTercero,         "
                + "        tblTerceros.idPersona,             "
                + "        tblTerceros.idAutoRetenedor,       "
                + "        tblTerceros.idRegimen,             "
                + "        tblTerceros.nombreTercero,         "
                + "        tblTerceros.direccionTercero,      "
                + "        tblTerceros.idDptoCiudad,          "
                + "        tblTerceros.telefonoFijo,          "
                + "        tblTerceros.telefonoCelular,       "
                + "        tblTerceros.telefonoFax,           "
                + "        tblTerceros.email,                 "
                + "        tblTerceros.idFormaPago,           "
                + "        tblTerceros.estado,                "
                + "        tblTerceros.idRuta,                "
                + "        tblTerceros.nombreEmpresa,         "
                + "        tblTerceros.cupoCredito,           "
                + "        tblTerceros.indicador,             "
                + "        tblTerceros.ciudadTercero,         "
                + "        tblTerceros.contactoTercero,       "
                + "        tblTerceros.idListaPrecio,         "
                + "        tblTerceros.idVendedor             "
                + " FROM tblTerceros                          "
                + " WHERE tblTerceros.nombreTercero LIKE     ("
                + getNombreTercero() + ")                    "
                + " ORDER BY tblTerceros.nombreTercero        ";

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
                fachadaBean.setEstado(rs.getInt("estado"));
                fachadaBean.setIdRuta(rs.getString("idRuta"));
                fachadaBean.setNombreEmpresa(rs.getString("nombreEmpresa"));
                fachadaBean.setCupoCredito(rs.getDouble("cupoCredito"));
                fachadaBean.setIndicador(rs.getInt("indicador"));
                fachadaBean.setCiudadTercero(rs.getString("ciudadTercero"));
                fachadaBean.setContactoTercero(rs.getString("contactoTercero"));
                fachadaBean.setIdListaPrecio(rs.getInt("contactoTercero"));
                fachadaBean.setIdVendedor(rs.getDouble("idVendedor"));

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
            // Cierra la conexion a la base de datos limpiando la statement usada y la
            jdbcAccess.cleanup(connection, selectStatement, null);
            return contenedor;
        }
    }

    public boolean ingresaTercero() {
        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr = "INSERT INTO tblTerceros  "
                + "( idCliente,                                    "
                + "idTercero,                                    "
                + "tipoIdTercero,                                "
                + "digitoVerificacion,                           "
                + "idTipoTercero,                                "
                + "idPersona,                                    "
                + "idAutoRetenedor,                              "
                + "idRegimen,                                    "
                + "nombreTercero,                                "
                + "direccionTercero,                             "
                + "idDptoCiudad,                                 "
                + "telefonoFijo,                                 "
                + "telefonoCelular,                              "
                + "telefonoFax,                                 "
                + "email,                                        "
                + "idFormaPago,                                  "
                + "estado,                                       "
                + "idRuta,                                       "
                + "nombreEmpresa,                               "
                + "cupoCredito,                                  "
                + "indicador,                                    "
                + "ciudadTercero,                               "
                + " contactoTercero,                              "
                + "idListaPrecio,                                "
                + "idVendedor,                                   "
                + "idSeq,                                        "
                + "idClase,                                      "
                + "fechaCreacion,                                "
                + "diaMaxFacturacion,                                "
                + "idRteCree)         "
                + "VALUES ('" + getIdCliente() + "',"
                + getIdTercero() + ",'"
                + getTipoIdTercero() + "',"
                + getDigitoVerificacion() + ","
                + getIdTipoTercero() + ","
                + getIdPersona() + ","
                + getIdAutoRetenedor() + ",'"
                + getIdRegimen() + "','"
                + getNombreTercero() + "','"
                + getDireccionTercero() + "',"
                + getIdDptoCiudad() + ",'"
                + getTelefonoFijo() + "','"
                + getTelefonoCelular() + "','"
                + getTelefonoFax() + "','"
                + getEmail() + "',"
                + getIdFormaPago() + ","
                + getEstado() + ",'"
                + getIdRuta() + "','"
                + getNombreEmpresa() + "',"
                + getCupoCredito() + ","
                + getIndicador() + ",'"
                + getCiudadTercero() + "','"
                + getContactoTercero() + "',"
                + getIdListaPrecio() + ","
                + getIdVendedor() + ","
                + getIdSeq() + ","
                + getIdClase() + ","
                + getFechaCreacion() + ","
                + getDiaMaxFacturacion() + ","//NUEVO CAMPO REQUERIMIENTO MAESTROS
                + getIdRteCree() + ") ";

        PreparedStatement selectStatement = null;
        try {
            connection = this.jdbcAccess.getConnection();

            selectStatement = connection.prepareStatement(selectStr);

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

    // ingresaTercero
    public boolean ingresaTerceroviejo() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr
                = "INSERT INTO tblTerceros  ( idCliente,         "
                + "                           idTercero,         "
                + "                           tipoIdTercero,     "
                + "                           digitoVerificacion,"
                + "                           idTipoTercero,     "
                + "                           idPersona,         "
                + "                           idAutoRetenedor,   "
                + "                           idRegimen,         "
                + "                           nombreTercero,     "
                + "                           direccionTercero,  "
                + "                           idDptoCiudad,      "
                + "                           telefonoFijo,      "
                + "                           telefonoCelular,   "
                + "                           telefonoFax,       "
                + "                           email,             "
                + "                           idFormaPago,       "
                + "                           estado,            "
                + "                           idRuta,            "
                + "                           nombreEmpresa,     "
                + "                           cupoCredito,       "
                + "                           indicador,         "
                + "                           ciudadTercero,     "
                + "                           contactoTercero,   "
                + "                           idListaPrecio,     "
                + "                           idVendedor,        "
                + "                           idRteIva,          "
                + "                           idRteIvaVrBase,    "
                + "                           idRteFuenteVrBase )"
                + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,      "
                + "        ?,?,?,?,?,?,?,?,?,?,?,?)              ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            //
            selectStatement.setString(1, getIdCliente());
            selectStatement.setDouble(2, getIdTercero());
            selectStatement.setString(3, getTipoIdTercero());
            selectStatement.setInt(4, getDigitoVerificacion());
            selectStatement.setInt(5, getIdTipoTercero());
            selectStatement.setInt(6, getIdPersona());
            selectStatement.setInt(7, getIdAutoRetenedor());
            selectStatement.setString(8, getIdRegimen());
            selectStatement.setString(9, getNombreTercero());
            selectStatement.setString(10, getDireccionTercero());
            selectStatement.setInt(11, getIdDptoCiudad());
            selectStatement.setString(12, getTelefonoFijo());
            selectStatement.setString(13, getTelefonoCelular());
            selectStatement.setString(14, getTelefonoFax());
            selectStatement.setString(15, getEmail());
            selectStatement.setInt(16, getIdFormaPago());
            selectStatement.setInt(17, getEstado());
            selectStatement.setString(18, getIdRuta());
            selectStatement.setString(19, getNombreEmpresa());
            selectStatement.setDouble(20, getCupoCredito());
            selectStatement.setInt(21, getIndicador());
            selectStatement.setString(22, getCiudadTercero());
            selectStatement.setString(23, getContactoTercero());
            selectStatement.setInt(24, getIdListaPrecio());
            selectStatement.setDouble(25, getIdVendedor());
            selectStatement.setInt(26, getIdRteIva());
            selectStatement.setInt(27, getIdRteIvaVrBase());
            selectStatement.setInt(28, getIdRteFuenteVrBase());

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

    // Metodo
    public FachadaTerceroBean listaUnTerceroFachada() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        Connection connection = null;

        // Variable de fachada de los datos
        FachadaTerceroBean fachadaBean;
        fachadaBean = new FachadaTerceroBean();

        String selectStr
                = "SELECT tblTerceros.idCliente,           "
                + "       tblTerceros.idTercero,           "
                + "       tblTerceros.tipoIdTercero,       "
                + "       tblTerceros.digitoVerificacion,  "
                + "       tblTerceros.idTipoTercero,       "
                + "       tblTerceros.idPersona,           "
                + "       tblTerceros.idAutoRetenedor,     "
                + "       tblTerceros.idRegimen,           "
                + "       tblTerceros.nombreTercero,       "
                + "       tblTerceros.direccionTercero,    "
                + "       tblTerceros.idDptoCiudad,        "
                + "       tblTerceros.telefonoFijo,        "
                + "       tblTerceros.telefonoCelular,     "
                + "       tblTerceros.telefonoFax,         "
                + "       tblTerceros.email,               "
                + "       tblTerceros.idFormaPago,         "
                + "       tblTerceros.estado,              "
                + "       tblTerceros.idRuta,              "
                + "       tblTerceros.nombreEmpresa,       "
                + "       tblTerceros.cupoCredito,         "
                + "       tblTerceros.indicador,           "
                + "      ( SELECT tblCiudades.nombreCiudad "
                + "        FROM tblCiudades                "
                + "        WHERE tblTerceros.idDptoCiudad= "
                + "                   tblCiudades.idCiudad "
                + "                    ) AS ciudadTercero, "
                + "       tblTerceros.contactoTercero,     "
                + "       tblTerceros.idListaPrecio,       "
                + "       tblTerceros.idVendedor,          "
                + "       tblTerceros.idRteIva,            "
                + "       tblTerceros.idRteIvaVrBase,      "
                + "       tblTerceros.idRteFuenteVrBase    "
                + "FROM tblTerceros                        "
                + "WHERE tblTerceros.idCliente     =      '"
                + getIdCliente() + "'                      ";

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
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setIdTercero(rs.getDouble("idTercero"));
                fachadaBean.setTipoIdTercero(rs.getString("tipoIdTercero"));
                fachadaBean.setDigitoVerificacion(rs.getInt("digitoVerificacion"));
                fachadaBean.setIdPersona(rs.getInt("idPersona"));
                fachadaBean.setIdAutoRetenedor(rs.getInt("idAutoRetenedor"));
                fachadaBean.setIdTipoTercero(rs.getInt("idTipoTercero"));
                fachadaBean.setIdRegimen(rs.getString("idRegimen"));
                fachadaBean.setNombreTercero(rs.getString("nombreTercero"));
                fachadaBean.setDireccionTercero(rs.getString("direccionTercero"));
                fachadaBean.setIdDptoCiudad(rs.getInt("idDptoCiudad"));
                fachadaBean.setTelefonoFijo(rs.getString("telefonoFijo"));
                fachadaBean.setTelefonoCelular(rs.getString("telefonoCelular"));
                fachadaBean.setTelefonoFax(rs.getString("telefonoFax"));
                fachadaBean.setEmail(rs.getString("email"));
                fachadaBean.setIdFormaPago(rs.getInt("idFormaPago"));
                fachadaBean.setEstado(rs.getInt("estado"));
                fachadaBean.setIdRuta(rs.getString("idRuta"));
                fachadaBean.setNombreEmpresa(rs.getString("nombreEmpresa"));
                fachadaBean.setCupoCredito(rs.getDouble("cupoCredito"));
                fachadaBean.setIndicador(rs.getInt("indicador"));
                fachadaBean.setCiudadTercero(rs.getString("ciudadTercero"));
                fachadaBean.setContactoTercero(rs.getString("contactoTercero"));
                fachadaBean.setIdListaPrecio(rs.getInt("idListaPrecio"));
                fachadaBean.setCiudadTercero(rs.getString("ciudadTercero"));
                fachadaBean.setIdVendedor(rs.getDouble("idVendedor"));
                fachadaBean.setIdRteIva(rs.getInt("idRteIva"));
                fachadaBean.setIdRteIvaVrBase(rs.getInt("idRteIvaVrBase"));
                fachadaBean.setIdRteFuenteVrBase(rs.getInt("idRteFuenteVrBase"));

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
            jdbcAccess.cleanup(connection, selectStatement, null);
            return fachadaBean;
        }
    }
    public FachadaTerceroBean listaUnTerceroFachadaCliente() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        Connection connection = null;

        // Variable de fachada de los datos
        FachadaTerceroBean fachadaBean;
        fachadaBean = new FachadaTerceroBean();

        String selectStr
                = "SELECT tblTerceros.idCliente,           "
                + "       tblTerceros.idTercero,           "
                + "       tblTerceros.tipoIdTercero,       "
                + "       tblTerceros.digitoVerificacion,  "
                + "       tblTerceros.idTipoTercero,       "
                + "       tblTerceros.idPersona,           "
                + "       tblTerceros.idAutoRetenedor,     "
                + "       tblTerceros.idRegimen,           "
                + "       tblTerceros.nombreTercero,       "
                + "       tblTerceros.direccionTercero,    "
                + "       tblTerceros.idDptoCiudad,        "
                + "       tblTerceros.telefonoFijo,        "
                + "       tblTerceros.telefonoCelular,     "
                + "       tblTerceros.telefonoFax,         "
                + "       tblTerceros.email,               "
                + "       tblTerceros.idFormaPago,         "
                + "       tblTerceros.estado,              "
                + "       tblTerceros.idRuta,              "
                + "       tblTerceros.nombreEmpresa,       "
                + "       tblTerceros.cupoCredito,         "
                + "       tblTerceros.indicador,           "
                + "      ( SELECT tblCiudades.nombreCiudad "
                + "        FROM tblCiudades                "
                + "        WHERE tblTerceros.idDptoCiudad= "
                + "                   tblCiudades.idCiudad "
                + "                    ) AS ciudadTercero, "
                + "       tblTerceros.contactoTercero,     "
                + "       tblTerceros.idListaPrecio,       "
                + "       tblTerceros.idVendedor,          "
                + "       tblTerceros.idRteIva,            "
                + "       tblTerceros.idRteIvaVrBase,      "
                + "       tblTerceros.idRteFuenteVrBase    "
                + "FROM tblTerceros                        "
                + "WHERE tblTerceros.idCliente     =      '"
                + getIdCliente() + "'                      "
                + "  AND tblTerceros.idTipoTercero = 1";

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
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setIdTercero(rs.getDouble("idTercero"));
                fachadaBean.setTipoIdTercero(rs.getString("tipoIdTercero"));
                fachadaBean.setDigitoVerificacion(rs.getInt("digitoVerificacion"));
                fachadaBean.setIdPersona(rs.getInt("idPersona"));
                fachadaBean.setIdAutoRetenedor(rs.getInt("idAutoRetenedor"));
                fachadaBean.setIdTipoTercero(rs.getInt("idTipoTercero"));
                fachadaBean.setIdRegimen(rs.getString("idRegimen"));
                fachadaBean.setNombreTercero(rs.getString("nombreTercero"));
                fachadaBean.setDireccionTercero(rs.getString("direccionTercero"));
                fachadaBean.setIdDptoCiudad(rs.getInt("idDptoCiudad"));
                fachadaBean.setTelefonoFijo(rs.getString("telefonoFijo"));
                fachadaBean.setTelefonoCelular(rs.getString("telefonoCelular"));
                fachadaBean.setTelefonoFax(rs.getString("telefonoFax"));
                fachadaBean.setEmail(rs.getString("email"));
                fachadaBean.setIdFormaPago(rs.getInt("idFormaPago"));
                fachadaBean.setEstado(rs.getInt("estado"));
                fachadaBean.setIdRuta(rs.getString("idRuta"));
                fachadaBean.setNombreEmpresa(rs.getString("nombreEmpresa"));
                fachadaBean.setCupoCredito(rs.getDouble("cupoCredito"));
                fachadaBean.setIndicador(rs.getInt("indicador"));
                fachadaBean.setCiudadTercero(rs.getString("ciudadTercero"));
                fachadaBean.setContactoTercero(rs.getString("contactoTercero"));
                fachadaBean.setIdListaPrecio(rs.getInt("idListaPrecio"));
                fachadaBean.setCiudadTercero(rs.getString("ciudadTercero"));
                fachadaBean.setIdVendedor(rs.getDouble("idVendedor"));
                fachadaBean.setIdRteIva(rs.getInt("idRteIva"));
                fachadaBean.setIdRteIvaVrBase(rs.getInt("idRteIvaVrBase"));
                fachadaBean.setIdRteFuenteVrBase(rs.getInt("idRteFuenteVrBase"));

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
            jdbcAccess.cleanup(connection, selectStatement, null);
            return fachadaBean;
        }
    }

    public FachadaTerceroBean listaUnTerceroFCH() {
        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();

        Connection connection = null;

        FachadaTerceroBean fachadaBean = new FachadaTerceroBean();

        String selectStr = "SELECT tblTerceros.idCliente,                  "
                + "tblTerceros.idTercero,                  "
                + "tblTerceros.tipoIdTercero,              "
                + "tblTerceros.digitoVerificacion,         "
                + "tblTerceros.idTipoTercero,              "
                + "tblTerceros.idPersona,                  "
                + "tblTerceros.idAutoRetenedor,            "
                + "tblTerceros.idRegimen,                  "
                + "tblTerceros.nombreTercero,              "
                + "tblTerceros.direccionTercero,           "
                + "tblTerceros.idDptoCiudad,               "
                + "tblTerceros.telefonoFijo,               "
                + "tblTerceros.telefonoCelular,            "
                + "tblTerceros.telefonoFax,                "
                + "tblTerceros.email,                      "
                + "tblTerceros.idFormaPago,                "
                + "tblTerceros.estado,                     "
                + "tblTerceros.idRuta,                     "
                + "tblTerceros.nombreEmpresa,              "
                + "tblTerceros.cupoCredito,                "
                + "tblTerceros.indicador,                  "
                + "tblTerceros.ciudadTercero,              "
                + "tblTerceros.contactoTercero,            "
                + "tblTerceros.idListaPrecio,              "
                + "tblTerceros.idClase,                    "
                + "tblTerceros.idVendedor,                 "
                + "tblTerceros.idRteIva,                   "
                + "tblTerceros.idRteIvaVrBase,             "
                + "tblTerceros.idRteFuenteVrBase,          "
                + "tblTerceros.CC_Nit,                     "
                + "tblTerceros.diaMaxFacturacion,                     "
                + "tblTerceros.idRteCree           "
                + "FROM tblTerceros                        "
                + "WHERE tblTerceros.idCliente     =      '"
                + getIdCliente() + "'                      "
                + "AND  tblTerceros.idTipoTercero  =       "
                + getIdTipoTercero();

        PreparedStatement selectStatement = null;
        try {
            connection = jdbcAccess.getConnection();

            selectStatement = connection.prepareStatement(selectStr);

            ResultSet rs = selectStatement.executeQuery();
            if (rs.next()) {
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setIdTercero(rs.getDouble("idTercero"));
                fachadaBean.setTipoIdTercero(rs.getString("tipoIdTercero"));
                fachadaBean.setDigitoVerificacion(rs.getInt("digitoVerificacion"));

                fachadaBean.setIdPersona(rs.getInt("idPersona"));
                fachadaBean.setIdAutoRetenedor(rs.getInt("idAutoRetenedor"));
                fachadaBean.setIdTipoTercero(rs.getInt("idTipoTercero"));
                fachadaBean.setIdRegimen(rs.getString("idRegimen"));
                fachadaBean.setNombreTercero(rs.getString("nombreTercero"));
                fachadaBean.setDireccionTercero(rs.getString("direccionTercero"));
                fachadaBean.setIdDptoCiudad(rs.getInt("idDptoCiudad"));
                fachadaBean.setTelefonoFijo(rs.getString("telefonoFijo"));
                fachadaBean.setTelefonoCelular(rs.getString("telefonoCelular"));
                fachadaBean.setTelefonoFax(rs.getString("telefonoFax"));
                fachadaBean.setEmail(rs.getString("email"));
                fachadaBean.setIdFormaPago(rs.getInt("idFormaPago"));
                fachadaBean.setEstado(rs.getInt("estado"));
                fachadaBean.setIdRuta(rs.getString("idRuta"));
                fachadaBean.setNombreEmpresa(rs.getString("nombreEmpresa"));
                fachadaBean.setCupoCredito(rs.getDouble("cupoCredito"));
                fachadaBean.setIndicador(rs.getInt("indicador"));
                fachadaBean.setCiudadTercero(rs.getString("ciudadTercero"));
                fachadaBean.setContactoTercero(rs.getString("contactoTercero"));
                fachadaBean.setIdListaPrecio(rs.getInt("idListaPrecio"));
                fachadaBean.setIdClase(rs.getInt("idClase"));
                fachadaBean.setIdVendedor(rs.getDouble("idVendedor"));
                fachadaBean.setIdRteIva(rs.getInt("idRteIva"));
                fachadaBean.setIdRteIvaVrBase(rs.getInt("idRteIvaVrBase"));
                fachadaBean.setIdRteFuenteVrBase(rs.getInt("idRteFuenteVrBase"));
                fachadaBean.setCC_Nit(rs.getString("CC_Nit"));
                fachadaBean.setIdRteCree(rs.getInt("idRteCree"));
                fachadaBean.setDiaMaxFacturacion(rs.getInt("diaMaxFacturacion"));
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
            jdbcAccess.cleanup(connection, selectStatement, null);
            return fachadaBean;
        }
    }

    // listaUnTerceroFCH
    public FachadaTerceroBean listaUnTerceroFCHviejo() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        Connection connection = null;

        // Variable de fachada de los datos
        FachadaTerceroBean fachadaBean;
        fachadaBean = new FachadaTerceroBean();

        String selectStr
                = "SELECT tblTerceros.idCliente,           "
                + "       tblTerceros.idTercero,           "
                + "       tblTerceros.tipoIdTercero,       "
                + "       tblTerceros.digitoVerificacion,  "
                + "       tblTerceros.idTipoTercero,       "
                + "       tblTerceros.idPersona,           "
                + "       tblTerceros.idAutoRetenedor,     "
                + "       tblTerceros.idRegimen,           "
                + "       tblTerceros.nombreTercero,       "
                + "       tblTerceros.direccionTercero,    "
                + "       tblTerceros.idDptoCiudad,        "
                + "       tblTerceros.telefonoFijo,        "
                + "       tblTerceros.telefonoCelular,     "
                + "       tblTerceros.telefonoFax,         "
                + "       tblTerceros.email,               "
                + "       tblTerceros.idFormaPago,         "
                + "       tblTerceros.estado,              "
                + "       tblTerceros.idRuta,              "
                + "       tblTerceros.nombreEmpresa,       "
                + "       tblTerceros.cupoCredito,         "
                + "       tblTerceros.indicador,           "
                + "       tblTerceros.ciudadTercero,       "
                + "       tblTerceros.contactoTercero,     "
                + "       tblTerceros.idListaPrecio,       "
                + "       tblTerceros.idVendedor,          "
                + "       tblTerceros.idRteIva,            "
                + "       tblTerceros.idRteIvaVrBase,      "
                + "       tblTerceros.idRteFuenteVrBase    "
                + "FROM tblTerceros                        "
                + "WHERE tblTerceros.idCliente     =      '"
                + getIdCliente() + "'                      "
                + "AND  tblTerceros.idTipoTercero  =       "
                + getIdTipoTercero();

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
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setIdTercero(rs.getDouble("idTercero"));
                fachadaBean.setTipoIdTercero(rs.getString("tipoIdTercero"));
                fachadaBean.setDigitoVerificacion(
                        rs.getInt("digitoVerificacion"));
                fachadaBean.setIdPersona(rs.getInt("idPersona"));
                fachadaBean.setIdAutoRetenedor(rs.getInt("idAutoRetenedor"));
                fachadaBean.setIdTipoTercero(rs.getInt("idTipoTercero"));
                fachadaBean.setIdRegimen(rs.getString("idRegimen"));
                fachadaBean.setNombreTercero(rs.getString("nombreTercero"));
                fachadaBean.setDireccionTercero(rs.getString("direccionTercero"));
                fachadaBean.setIdDptoCiudad(rs.getInt("idDptoCiudad"));
                fachadaBean.setTelefonoFijo(rs.getString("telefonoFijo"));
                fachadaBean.setTelefonoCelular(rs.getString("telefonoCelular"));
                fachadaBean.setTelefonoFax(rs.getString("telefonoFax"));
                fachadaBean.setEmail(rs.getString("email"));
                fachadaBean.setIdFormaPago(rs.getInt("idFormaPago"));
                fachadaBean.setEstado(rs.getInt("estado"));
                fachadaBean.setIdRuta(rs.getString("idRuta"));
                fachadaBean.setNombreEmpresa(rs.getString("nombreEmpresa"));
                fachadaBean.setCupoCredito(rs.getDouble("cupoCredito"));
                fachadaBean.setIndicador(rs.getInt("indicador"));
                fachadaBean.setCiudadTercero(rs.getString("ciudadTercero"));
                fachadaBean.setContactoTercero(rs.getString("contactoTercero"));
                fachadaBean.setIdListaPrecio(rs.getInt("idListaPrecio"));
                fachadaBean.setIdVendedor(rs.getDouble("idVendedor"));
                fachadaBean.setIdRteIva(rs.getInt("idRteIva"));
                fachadaBean.setIdRteIvaVrBase(rs.getInt("idRteIvaVrBase"));
                fachadaBean.setIdRteFuenteVrBase(rs.getInt("idRteFuenteVrBase"));

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
            jdbcAccess.cleanup(connection, selectStatement, null);
            return fachadaBean;
        }
    }

    // seleccionaUnTercero
    public Vector seleccionaUnTercero() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        Vector contenedor = new Vector();
        Connection connection = null;

        String selectStr
                = " SELECT tblTerceros.idTercero,	           "
                + "       tblTerceros.tipoIdTercero,         "
                + "       tblTerceros.digitoVerificacion,    "
                + "       tblTerceros.idTipoTercero,         "
                + "       tblTerceros.idPersona,             "
                + "       tblTerceros.idAutoRetenedor,       "
                + "       tblTerceros.idRegimen,             "
                + "       tblTerceros.nombreTercero,         "
                + "       tblTerceros.direccionTercero,      "
                + "       tblTerceros.idDptoCiudad,          "
                + "       tblTerceros.telefonoFijo,          "
                + "       tblTerceros.telefonoCelular,       "
                + "       tblTerceros.telefonoFax,           "
                + "       tblTerceros.email,                 "
                + "       tblTerceros.idFormaPago,           "
                + "       tblTerceros.idCliente,             "
                + "       tblTerceros.idRuta                 "
                + " FROM tblTerceros                         "
                + " WHERE tblTerceros.idCliente =  (?)       ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // inicializa los valore de los parametros
            selectStatement.setString(1, getIdCliente());

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaTerceroBean fachadaBean;

            while (rs.next()) {

                fachadaBean = new FachadaTerceroBean();

                fachadaBean.setIdTercero(rs.getDouble("idTercero"));
                fachadaBean.setTipoIdTercero(rs.getString("tipoIdTercero"));
                fachadaBean.setDigitoVerificacion(rs.getInt("digitoVerificacion"));
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
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setIdRuta(rs.getString("idRuta"));

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
            // Cierra la conexion a la base de datos limpiando la statement usada y la
            jdbcAccess.cleanup(connection, selectStatement, null);
            return contenedor;
        }
    }

    // actualizaTercero
    public boolean actualizaTercero() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Vector contenedor = new Vector();
        Connection connection = null;

        String selectStr
                = "UPDATE tblTerceros                              "
                + " SET   tblTerceros.nombreTercero      =        '"
                + getNombreTercero() + "',                         "
                + "       tblTerceros.tipoIdTercero      =        '"
                + getTipoIdTercero() + "',                         "
                + "       tblTerceros.digitoVerificacion =         "
                + getDigitoVerificacion() + ",                     "
                + "       tblTerceros.idTipoTercero      =         "
                + getIdTipoTercero() + ",                          "
                + "       tblTerceros.idPersona          =         "
                + getIdPersona() + ",                              "
                + "       tblTerceros.idAutoRetenedor    =         "
                + getIdAutoRetenedor() + ",                        "
                + "       tblTerceros.idRegimen          =        '"
                + getIdRegimen() + "',                             "
                + "       tblTerceros.direccionTercero   =        '"
                + getDireccionTercero() + "',                      "
                + "       tblTerceros.idDptoCiudad       =         "
                + getIdDptoCiudad() + ",                           "
                + "       tblTerceros.telefonoFijo       =        '"
                + getTelefonoFijo() + "',                          "
                + "       tblTerceros.telefonoCelular    =        '"
                + getTelefonoCelular() + "',                       "
                + "       tblTerceros.telefonoFax        =        '"
                + getTelefonoFax() + "',                           "
                + "       tblTerceros.email              =        '"
                + getEmail() + "',                                 "
                + "       tblTerceros.idFormaPago        =         "
                + getIdFormaPago() + ",                            "
                + "       tblTerceros.estado             =         "
                + getEstado() + ",                                 "
                + "       tblTerceros.idTercero          =         "
                + getIdTercero() + ",                              "
                + "       tblTerceros.idRuta             =        '"
                + getIdRuta() + "'                                 "
                + " WHERE tblTerceros.idCliente          =        '"
                + getIdCliente() + "'";

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

    public boolean actualiza() {
        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Vector contenedor = new Vector();
        Connection connection = null;

        String selectStr = "UPDATE tblTerceros                               "
                + "SET   tblTerceros.nombreTercero      ='"
                + getNombreTercero() + "',                         "
                + "       tblTerceros.tipoIdTercero      ='"
                + getTipoIdTercero() + "',                         "
                + "       tblTerceros.digitoVerificacion = "
                + getDigitoVerificacion() + ",                     "
                + "       tblTerceros.idTipoTercero      = "
                + getIdTipoTercero() + ",                          "
                + "       tblTerceros.idPersona          = "
                + getIdPersona() + ",                              "
                + "       tblTerceros.idAutoRetenedor    = "
                + getIdAutoRetenedor() + ",                        "
                + "       tblTerceros.idRegimen          ='"
                + getIdRegimen() + "',                             "
                + "       tblTerceros.direccionTercero   ='"
                + getDireccionTercero() + "',                      "
                + "       tblTerceros.idDptoCiudad       = "
                + getIdDptoCiudad() + ",                           "
                + "       tblTerceros.telefonoFijo       ='"
                + getTelefonoFijo() + "',                          "
                + "       tblTerceros.telefonoCelular    ='"
                + getTelefonoCelular() + "',                       "
                + "       tblTerceros.telefonoFax        ='"
                + getTelefonoFax() + "',                           "
                + "       tblTerceros.email              ='"
                + getEmail() + "',                                 "
                + "       tblTerceros.idFormaPago        = "
                + getIdFormaPago() + ",                            "
                + "       tblTerceros.estado             =         "
                + getEstado() + ",                                 "
                + "       tblTerceros.idTercero          =         "
                + getIdTercero() + ",                              "
                + "       tblTerceros.idRuta             =        '"
                + getIdRuta() + "',                                "
                + "       tblTerceros.nombreEmpresa      =        '"
                + getNombreEmpresa() + "',                         "
                + "       tblTerceros.contactoTercero    =        '"
                + getContactoTercero() + "',                       "
                + "       tblTerceros.idListaPrecio      =         "
                + getIdListaPrecio() + ",                          "
                + "       tblTerceros.cupoCredito        =         "
                + getCupoCredito() + ",                            "
                + "       tblTerceros.indicador          =         "
                + getIndicador() + ",                              "
                + "       tblTerceros.idVendedor         =         "
                + getIdVendedor() + ",                             "
                + "       tblTerceros.idRteIva           =         "
                + getIdRteIva() + ",                               "
                + "       tblTerceros.idRteIvaVrBase     =         "
                + getIdRteIvaVrBase() + ",                         "
                + "       tblTerceros.idRteFuenteVrBase  =         "
                + getIdRteFuenteVrBase() + ",                      "
                + "       tblTerceros.idClase =                    "
                + getIdClase() + ",                                "
                + "       tblTerceros.idRteCree =                  "
                + getIdRteCree() + ",                               "
                + "       tblTerceros.diaMaxFacturacion =                  "
                + getDiaMaxFacturacion() + "                               "
                + " WHERE tblTerceros.idCliente          =        '"
                + getIdCliente() + "'                              "
                + "AND    tblTerceros.idTipoTercero      =         "
                + getIdTipoTercero() + "                           ";

        PreparedStatement selectStatement = null;
        try {
            connection = this.jdbcAccess.getConnection();

            selectStatement = connection.prepareStatement(selectStr);

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

    // actualiza
    public boolean actualizaViejo() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Vector contenedor = new Vector();
        Connection connection = null;

        String selectStr
                = "UPDATE tblTerceros                              "
                + " SET   tblTerceros.nombreTercero      ='"
                + getNombreTercero() + "',                         "
                + "       tblTerceros.tipoIdTercero      ='"
                + getTipoIdTercero() + "',                         "
                + "       tblTerceros.digitoVerificacion = "
                + getDigitoVerificacion() + ",                     "
                + "       tblTerceros.idTipoTercero      = "
                + getIdTipoTercero() + ",                          "
                + "       tblTerceros.idPersona          = "
                + getIdPersona() + ",                              "
                + "       tblTerceros.idAutoRetenedor    = "
                + getIdAutoRetenedor() + ",                        "
                + "       tblTerceros.idRegimen          ='"
                + getIdRegimen() + "',                             "
                + "       tblTerceros.direccionTercero   ='"
                + getDireccionTercero() + "',                      "
                + "       tblTerceros.idDptoCiudad       = "
                + getIdDptoCiudad() + ",                           "
                + "       tblTerceros.telefonoFijo       ='"
                + getTelefonoFijo() + "',                          "
                + "       tblTerceros.telefonoCelular    ='"
                + getTelefonoCelular() + "',                       "
                + "       tblTerceros.telefonoFax        ='"
                + getTelefonoFax() + "',                           "
                + "       tblTerceros.email              ='"
                + getEmail() + "',                                 "
                + "       tblTerceros.idFormaPago        = "
                + getIdFormaPago() + ",                            "
                + "       tblTerceros.estado             =         "
                + getEstado() + ",                                 "
                + "       tblTerceros.idTercero          =         "
                + getIdTercero() + ",                              "
                + "       tblTerceros.idRuta             =        '"
                + getIdRuta() + "',                                "
                + "       tblTerceros.nombreEmpresa      =        '"
                + getNombreEmpresa() + "',                         "
                + "       tblTerceros.contactoTercero    =        '"
                + getContactoTercero() + "',                       "
                + "       tblTerceros.idListaPrecio      =         "
                + getIdListaPrecio() + ",                          "
                + "       tblTerceros.cupoCredito        =         "
                + getCupoCredito() + ",                            "
                + "       tblTerceros.indicador          =         "
                + getIndicador() + ",                              "
                + "       tblTerceros.idVendedor         =         "
                + getIdVendedor() + ",                             "
                + "       tblTerceros.idRteIva           =         "
                + getIdRteIva() + ",                               "
                + "       tblTerceros.idRteIvaVrBase     =         "
                + getIdRteIvaVrBase() + ",                         "
                + "       tblTerceros.idRteFuenteVrBase  =         "
                + getIdRteFuenteVrBase() + "                       "
                + " WHERE tblTerceros.idCliente          =        '"
                + getIdCliente() + "'                              "
                + "AND    tblTerceros.idTipoTercero      =          "
                + getIdTipoTercero() + "                           ";

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

    // seleccionaTerceroTipoTercero
    public Vector seleccionaTerceroTipoTercero() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        Vector contenedor = new Vector();
        Connection connection = null;

        String selectStr
                = " SELECT tblTerceros.idTercero,	           "
                + "       tblTerceros.nombreTercero          "
                + " FROM tblTerceros                         "
                + " WHERE tblTerceros.idTipoTercero =  ( ? ) ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // inicializa los valore de los parametros
            selectStatement.setInt(1, getIdTipoTercero());

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaTerceroBean fachadaBean;

            while (rs.next()) {

                fachadaBean = new FachadaTerceroBean();

                fachadaBean.setIdTercero(rs.getDouble("idTercero"));
                fachadaBean.setNombreTercero(rs.getString("nombreTercero"));

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

    // seleccionaTerceroConductorVigente
    public Vector seleccionaTerceroConductorVigente() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        Vector contenedor = new Vector();
        Connection connection = null;

        String selectStr
                = "SELECT 0000 AS idTercero,         "
                + "       'NN' AS nombreTercero,     "
                + "       0 AS orden                 "
                + "FROM tblTerceros UNION            "
                + "SELECT tblTerceros.idTercero,     "
                + "       tblTerceros.nombreTercero, "
                + "       1 AS orden                 "
                + "FROM tblTerceros                  "
                + "WHERE tblTerceros.idTipoTercero = "
                + getIdTipoTercero() + "             "
                + "ORDER BY 3, 2                     ";

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

                //
                fachadaBean.setIdTercero(rs.getDouble("idTercero"));
                fachadaBean.setNombreTercero(rs.getString("nombreTercero"));

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

    // listaTerceroOperacion
    public Vector listaTerceroOperacion() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        Vector contenedor = new Vector();
        Connection connection = null;

        String selectStr
                = "SELECT 0000 AS idTercero,         "
                + "       'NN' AS nombreTercero,     "
                + "       0 AS orden                 "
                + "FROM tblTerceros                  "
                + "UNION                             "
                + " SELECT ctrlUsuarios.idUsuario    "
                + "                     AS idTercero "
                + "      ,ctrlUsuarios.nombreUsuario "
                + "                AS nombreTercero, "
                + "       1 AS orden                 "
                + "FROM ctrlUsuarios                 "
                + "UNION                             "
                + "SELECT tblTerceros.idTercero,     "
                + "       tblTerceros.nombreTercero, "
                + "       1 AS orden                 "
                + "FROM tblTerceros                  "
                + "WHERE tblTerceros.idTipoTercero = "
                + getIdTipoTercero() + "             "
                + "ORDER BY 3, 2                     ";

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

                //
                fachadaBean.setIdTercero(rs.getDouble("idTercero"));
                fachadaBean.setNombreTercero(rs.getString("nombreTercero"));

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

    // listaTerceroOpcion
    public Vector listaTerceroOpcion() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        Vector contenedor = new Vector();
        Connection connection = null;

        String selectStr
                = "SELECT 0000 AS idTercero,         "
                + "       'NN' AS nombreTercero,     "
                + "       1 AS orden                 "
                + "FROM tblTerceros                  "
                + "UNION                             "
                + "SELECT -1 AS idTercero,           "
                + "       'TODOS' AS nombreTercero,  "
                + "       2 AS orden                 "
                + "FROM tblTerceros                  "
                + "UNION                             "
                + "SELECT tblTerceros.idTercero,     "
                + "       tblTerceros.nombreTercero, "
                + "       3 AS orden                 "
                + "FROM tblTerceros                  "
                + "WHERE tblTerceros.idTipoTercero = "
                + getIdTipoTercero() + "             "
                + "ORDER BY 3, 2                     ";

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

                //
                fachadaBean.setIdTercero(rs.getDouble("idTercero"));
                fachadaBean.setNombreTercero(rs.getString("nombreTercero"));

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

    // Metodo eliminaTercero
    public boolean eliminaTercero() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        boolean eliminaTercero = false;
        Connection connection = null;

        String selectStr
                = " DELETE tblTerceros.idTercero         "
                + " FROM tblTerceros                     "
                + " WHERE  tblTerceros.idCliente = ( ? ) ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            selectStatement.setString(1, getIdCliente());

            // Obtiene el resultset
            selectStatement.execute();
            eliminaTercero = true;

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
            return eliminaTercero;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {
            // Cierra la conexion a la base de datos limpiando la statement usada y la
            // conexion
            return eliminaTercero;
        }
    }

    public ArrayList<FachadaTerceroBean> listaOperaciones() {
        //Averigusa en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        ArrayList<FachadaTerceroBean> listaOperaciones = new ArrayList<FachadaTerceroBean>();
        Connection connection = null;

        String selectStr
                = "SELECT * FROM tblOperacionesFactura; ";

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

                //
                fachadaBean.setIdOperacionFactura(rs.getInt("idOperacion"));
                fachadaBean.setNombreOperacionFactura(rs.getString("nombreOperacion"));

                listaOperaciones.add(fachadaBean);

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
            return listaOperaciones;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {
            // Cierra la conexion a la base de datos limpiando la statement usada y la
            // conexion
            return listaOperaciones;
        }
    }

    public ArrayList<FachadaTerceroBean> listaResponsabilidades() {
        //Averigusa en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        ArrayList<FachadaTerceroBean> listaOperaciones = new ArrayList<FachadaTerceroBean>();
        Connection connection = null;

        String selectStr
                = "SELECT * FROM tblResponsabilidadesFiscales; ";

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
            fachadaBean = new FachadaTerceroBean();
            fachadaBean.setIdResponsabilidad(null);
            fachadaBean.setNombreResponsabilidad("");
            listaOperaciones.add(fachadaBean);
            while (rs.next()) {

                fachadaBean = new FachadaTerceroBean();

                //
                fachadaBean.setIdResponsabilidad(rs.getString("idResponsabilidad"));
                fachadaBean.setNombreResponsabilidad(rs.getString("nombreResponsabilidad"));

                listaOperaciones.add(fachadaBean);

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
            return listaOperaciones;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {
            // Cierra la conexion a la base de datos limpiando la statement usada y la
            // conexion
            return listaOperaciones;
        }
    }

    public ArrayList<FachadaTerceroBean> listaSeleccionCliente() {
        //Averigusa en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        ArrayList<FachadaTerceroBean> listaOperaciones = new ArrayList<FachadaTerceroBean>();
        Connection connection = null;

        String selectStr
                = "SELECT * FROM tblFacturacionTercero WHERE idTercero = " + getIdCliente() + "; ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
          
        
            while (rs.next()) {
               FachadaTerceroBean fachadaBean = new FachadaTerceroBean();
                fachadaBean.setIdOperacionFactura(rs.getInt("idOperacionFactura"));
                String[] idRes = new String[10];
                for (int i = 1; i < 11; i++) {
                    idRes[i - 1] = rs.getString("idResp" + i);
                }
                fachadaBean.setIdResponsabilidades(idRes);
                listaOperaciones.add(fachadaBean);
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
            return listaOperaciones;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {
            // Cierra la conexion a la base de datos limpiando la statement usada y la
            // conexion
            return listaOperaciones;
        }
    }

    public boolean ingresaParFactura() {
        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;
        int i = 0;
        String selectStr = "INSERT INTO tblFacturacionTercero (idTercero"
                + ",idOperacionFactura"
                + ",idResp1"
                + ",idResp2"
                + ",idResp3"
                + ",idResp4"
                + ",idResp5"
                + ",idResp6"
                + ",idResp7"
                + ",idResp8"
                + ",idResp9"
                + ",idResp10"
                + ")  VALUES ("
                + getIdTercero()
                + "," + getIdOperacionFactura()
                + ",'" + getIdResponsabilidades()[i++] + "'" //idres1
                + ",'" + getIdResponsabilidades()[i++] + "'" //idres2
                + ",'" + getIdResponsabilidades()[i++] + "'" //idres3
                + ",'" + getIdResponsabilidades()[i++] + "'" //idres4
                + ",'" + getIdResponsabilidades()[i++] + "'" //idres5
                + ",'" + getIdResponsabilidades()[i++] + "'" //idres6
                + ",'" + getIdResponsabilidades()[i++] + "'" //idres7
                + ",'" + getIdResponsabilidades()[i++] + "'" //idres8
                + ",'" + getIdResponsabilidades()[i++] + "'" //idres9
                + ",'" + getIdResponsabilidades()[i++] + "'" //idres10
                + ")";

        PreparedStatement selectStatement = null;
        try {
            connection = this.jdbcAccess.getConnection();

            selectStatement = connection.prepareStatement(selectStr);

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

    public boolean  deleteParFactura() {
        
        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;
        int i = 0;
        String selectStr = "DELETE FROM tblFacturacionTercero WHERE idTercero = "+getIdTercero()+";";

        PreparedStatement selectStatement = null;
        try {
            connection = this.jdbcAccess.getConnection();

            selectStatement = connection.prepareStatement(selectStr);

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

}
