package com.solucionesweb.losbeans.negocio;

// Importa la clase helper JDBCAccess que contiene los accesos a la base de datos
import com.solucionesweb.lasayudas.JDBCAccess;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaLocalBean;

// Importa los paquetes de java
import java.sql.*;
import java.io.*;
import java.util.*;
import java.io.IOException.*;

// Importa la interface de las constantes
import com.solucionesweb.losbeans.utilidades.IConstantes;

// Importa los paquetes de javax
import javax.naming.*;

public class LocalBean extends FachadaLocalBean
        implements Serializable, IConstantes {

    // Nombre JNDI del Data Source que esta clase requiere
    private static final String DATA_SOURCE_NAME = "CommerceAccess";
    // Clase helper usada para acceder a la base de datos
    private JDBCAccess jdbcAccess;

    // Metodo constructor por defecto sin parametros
    public LocalBean() {

        // Al instanciar el Bean establece la conexion a la base de datos
        jdbcAccess = new JDBCAccess(DATA_SOURCE_NAME);
    }

    // listaAllOtros
    public Vector listaAllOtros() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        Vector contenedor = new Vector();
        Connection connection = null;

        String selectStr =
                "SELECT  tblLocales.idLocal,       "
                + "      tblLocales.nombreLocal,     "
                + "      tblLocales.estado,          "
                + "      tblLocales.razonSocial,     "
                + "      tblLocales.nit,             "
                + "      tblLocales.direccion,       "
                + "      tblLocales.telefono,        "
                + "      tblLocales.fax,             "
                + "      tblLocales.regimen,         "
                + "      tblLocales.resolucion,      "
                + "      tblLocales.fechaResolucion, "
                + "      tblLocales.ciudad,          "
                + "      tblLocales.txtFactura,      "
                + "      tblLocales.rango,           "
                + "      tblLocales.prefijo,         "
                + "      tblLocales.email,           "
                + "      tblLocales.margenDisponible,"
                + "      tblLocales.valorDisponible, "
                + "     tblLocales.inventarioVisible,"
                + "      tblLocales.ipLocal          "
                + "FROM   tblLocales                 "
                + "WHERE tblLocales.idLocal NOT IN  ("
                + getIdLocal() + ")                  "
                + "ORDER BY tblLocales.nombreLocal   ";


        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaLocalBean fachadaBean;

            while (rs.next()) {

                fachadaBean = new FachadaLocalBean();

                //
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setNombreLocal(rs.getString("nombreLocal"));
                fachadaBean.setEstado(rs.getInt("estado"));
                fachadaBean.setRazonSocial(rs.getString("razonSocial"));
                fachadaBean.setNit(rs.getString("nit"));
                fachadaBean.setDireccion(rs.getString("direccion"));
                fachadaBean.setTelefono(rs.getString("telefono"));
                fachadaBean.setFax(rs.getString("fax"));
                fachadaBean.setResolucion(rs.getString("resolucion"));
                fachadaBean.setFechaResolucionStr(rs.getString("fechaResolucion"));
                fachadaBean.setCiudad(rs.getString("ciudad"));
                fachadaBean.setTxtFactura(rs.getString("txtFactura"));
                fachadaBean.setRango(rs.getString("rango"));
                fachadaBean.setPrefijo(rs.getString("prefijo"));
                fachadaBean.setEmail(rs.getString("email"));
                fachadaBean.setMargenDisponible(rs.getDouble("margenDisponible"));
                fachadaBean.setValorDisponible(
                        rs.getDouble("valorDisponible"));
                fachadaBean.setInventarioVisible(
                        rs.getBoolean("inventarioVisible"));
                fachadaBean.setInventarioVisible(
                        rs.getBoolean("inventarioVisible"));
                fachadaBean.setIpLocal(rs.getString("ipLocal"));

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

    // listaAll
    public Vector listaAll() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        Vector contenedor = new Vector();
        Connection connection = null;

        String selectStr =
                "SELECT  tblLocales.idLocal,       "
                + "      tblLocales.nombreLocal,     "
                + "      tblLocales.estado,          "
                + "      tblLocales.razonSocial,     "
                + "      tblLocales.nit,             "
                + "      tblLocales.direccion,       "
                + "      tblLocales.telefono,        "
                + "      tblLocales.fax,             "
                + "      tblLocales.regimen,         "
                + "      tblLocales.resolucion,      "
                + "      tblLocales.fechaResolucion, "
                + "      tblLocales.ciudad,          "
                + "      tblLocales.txtFactura,      "
                + "      tblLocales.rango,           "
                + "      tblLocales.prefijo,         "
                + "      tblLocales.email,           "
                + "      tblLocales.margenDisponible,"
                + "      tblLocales.valorDisponible, "
                + "     tblLocales.inventarioVisible,"
                + "      tblLocales.ipLocal          "
                + "FROM   tblLocales                 "
                + "ORDER BY tblLocales.nombreLocal   ";


        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaLocalBean fachadaBean;

            while (rs.next()) {

                fachadaBean = new FachadaLocalBean();

                //
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setNombreLocal(rs.getString("nombreLocal"));
                fachadaBean.setEstado(rs.getInt("estado"));
                fachadaBean.setRazonSocial(rs.getString("razonSocial"));
                fachadaBean.setNit(rs.getString("nit"));
                fachadaBean.setDireccion(rs.getString("direccion"));
                fachadaBean.setTelefono(rs.getString("telefono"));
                fachadaBean.setFax(rs.getString("fax"));
                fachadaBean.setResolucion(rs.getString("resolucion"));
                fachadaBean.setFechaResolucionStr(rs.getString("fechaResolucion"));
                fachadaBean.setCiudad(rs.getString("ciudad"));
                fachadaBean.setTxtFactura(rs.getString("txtFactura"));
                fachadaBean.setRango(rs.getString("rango"));
                fachadaBean.setPrefijo(rs.getString("prefijo"));
                fachadaBean.setEmail(rs.getString("email"));
                fachadaBean.setMargenDisponible(rs.getDouble("margenDisponible"));
                fachadaBean.setValorDisponible(
                        rs.getDouble("valorDisponible"));
                fachadaBean.setInventarioVisible(
                        rs.getBoolean("inventarioVisible"));
                fachadaBean.setInventarioVisible(
                        rs.getBoolean("inventarioVisible"));
                fachadaBean.setIpLocal(rs.getString("ipLocal"));

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

    // Metodo
    public Vector listaTodosLocalSesion() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        Vector contenedor = new Vector();
        Connection connection = null;

        String selectStr =
                "SELECT  tblLocales.idLocal,       "
                + "      tblLocales.nombreLocal,     "
                + "      tblLocales.estado,          "
                + "      tblLocales.razonSocial,     "
                + "      tblLocales.nit,             "
                + "      tblLocales.direccion,       "
                + "      tblLocales.telefono,        "
                + "      tblLocales.fax,             "
                + "      tblLocales.regimen,         "
                + "      tblLocales.resolucion,      "
                + "      tblLocales.fechaResolucion, "
                + "      tblLocales.ciudad,          "
                + "      tblLocales.txtFactura,      "
                + "      tblLocales.rango,           "
                + "      tblLocales.prefijo,         "
                + "      tblLocales.email,           "
                + "      tblLocales.margenDisponible,"
                + "      tblLocales.valorDisponible, "
                + "     tblLocales.inventarioVisible,"
                + "      tblLocales.ipLocal          "
                + "FROM   tblLocales                 "
                + "WHERE tblLocales.idLocal IN     ( "
                + getIdLocal() + " )                 "
                + "ORDER BY tblLocales.nombreLocal   ";


        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaLocalBean fachadaBean;

            while (rs.next()) {

                fachadaBean = new FachadaLocalBean();

                //
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setNombreLocal(rs.getString("nombreLocal"));
                fachadaBean.setEstado(rs.getInt("estado"));
                fachadaBean.setRazonSocial(rs.getString("razonSocial"));
                fachadaBean.setNit(rs.getString("nit"));
                fachadaBean.setDireccion(rs.getString("direccion"));
                fachadaBean.setTelefono(rs.getString("telefono"));
                fachadaBean.setFax(rs.getString("fax"));
                fachadaBean.setResolucion(rs.getString("resolucion"));
                fachadaBean.setFechaResolucionStr(
                        rs.getString("fechaResolucion"));
                fachadaBean.setCiudad(rs.getString("ciudad"));
                fachadaBean.setTxtFactura(rs.getString("txtFactura"));
                fachadaBean.setRango(rs.getString("rango"));
                fachadaBean.setPrefijo(rs.getString("prefijo"));
                fachadaBean.setEmail(rs.getString("email"));
                fachadaBean.setMargenDisponible(rs.getDouble("margenDisponible"));
                fachadaBean.setValorDisponible(
                        rs.getDouble("valorDisponible"));
                fachadaBean.setInventarioVisible(
                        rs.getBoolean("inventarioVisible"));
                fachadaBean.setInventarioVisible(
                        rs.getBoolean("inventarioVisible"));
                fachadaBean.setIpLocal(rs.getString("ipLocal"));

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

    public Vector seleccionaLocalxNombre() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        Vector contenedor = new Vector();
        Connection connection = null;


        String selectStr =
                "SELECT  tblLocales.idLocal,        "
                + "      tblLocales.nombreLocal,      "
                + "      tblLocales.estado,           "
                + "      tblLocales.razonSocial,      "
                + "      tblLocales.nit,              "
                + "      tblLocales.direccion,        "
                + "      tblLocales.telefono,         "
                + "      tblLocales.fax,              "
                + "      tblLocales.regimen,          "
                + "      tblLocales.resolucion,       "
                + "      tblLocales.fechaResolucion,  "
                + "      tblLocales.ciudad,           "
                + "      tblLocales.txtFactura,       "
                + "      tblLocales.rango,            "
                + "      tblLocales.prefijo,          "
                + "      tblLocales.email,            "
                + "      tblLocales.margenDisponible, "
                + "      tblLocales.valorDisponible,  "
                + "     tblLocales.inventarioVisible, "
                + "      tblLocales.ipLocal           "
                + "FROM   tblLocales                  "
                + "WHERE tblLocales.nombreLocal LIKE ("
                + getNombreLocal() + ")   "
                + "ORDER BY tblLocales.nombreLocal ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaLocalBean fachadaBean;

            while (rs.next()) {

                fachadaBean = new FachadaLocalBean();

                //
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setNombreLocal(rs.getString("nombreLocal"));
                fachadaBean.setEstado(rs.getInt("estado"));
                fachadaBean.setRazonSocial(rs.getString("razonSocial"));
                fachadaBean.setNit(rs.getString("nit"));
                fachadaBean.setDireccion(rs.getString("direccion"));
                fachadaBean.setTelefono(rs.getString("telefono"));
                fachadaBean.setFax(rs.getString("fax"));
                fachadaBean.setResolucion(rs.getString("resolucion"));
                fachadaBean.setFechaResolucionStr(
                        rs.getString("fechaResolucion"));
                fachadaBean.setCiudad(rs.getString("ciudad"));
                fachadaBean.setTxtFactura(rs.getString("txtFactura"));
                fachadaBean.setRango(rs.getString("rango"));
                fachadaBean.setPrefijo(rs.getString("prefijo"));
                fachadaBean.setEmail(rs.getString("email"));
                fachadaBean.setMargenDisponible(rs.getDouble("margenDisponible"));
                fachadaBean.setValorDisponible(
                        rs.getDouble("valorDisponible"));
                fachadaBean.setInventarioVisible(
                        rs.getBoolean("inventarioVisible"));
                fachadaBean.setInventarioVisible(
                        rs.getBoolean("inventarioVisible"));
                fachadaBean.setIpLocal(rs.getString("ipLocal"));

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

    //selecciona 1 seleccionaIdLocal
    public Vector seleccionaIdLocal() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        Vector contenedor = new Vector();
        Connection connection = null;

        String selectStr =
                "SELECT  tblLocales.idLocal,       "
                + "      tblLocales.nombreLocal,     "
                + "      tblLocales.estado,          "
                + "      tblLocales.razonSocial,     "
                + "      tblLocales.nit,             "
                + "      tblLocales.direccion,       "
                + "      tblLocales.telefono,        "
                + "      tblLocales.fax,             "
                + "      tblLocales.regimen,         "
                + "      tblLocales.resolucion,      "
                + "      tblLocales.fechaResolucion, "
                + "      tblLocales.ciudad,          "
                + "      tblLocales.txtFactura,      "
                + "      tblLocales.rango,           "
                + "      tblLocales.prefijo,         "
                + "      tblLocales.email,           "
                + "      tblLocales.margenDisponible,"
                + "      tblLocales.valorDisponible, "
                + "     tblLocales.inventarioVisible,"
                + "      tblLocales.ipLocal          "
                + "FROM  tblLocales                  "
                + "WHERE tblLocales.idLocal    =     "
                + getIdLocal();

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaLocalBean fachadaBean;

            while (rs.next()) {

                fachadaBean = new FachadaLocalBean();

                //
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setNombreLocal(rs.getString("nombreLocal"));
                fachadaBean.setEstado(rs.getInt("estado"));
                fachadaBean.setRazonSocial(rs.getString("razonSocial"));
                fachadaBean.setNit(rs.getString("nit"));
                fachadaBean.setDireccion(rs.getString("direccion"));
                fachadaBean.setTelefono(rs.getString("telefono"));
                fachadaBean.setFax(rs.getString("fax"));
                fachadaBean.setResolucion(rs.getString("resolucion"));
                fachadaBean.setFechaResolucionStr(
                        rs.getString("fechaResolucion"));
                fachadaBean.setCiudad(rs.getString("ciudad"));
                fachadaBean.setTxtFactura(rs.getString("txtFactura"));
                fachadaBean.setRango(rs.getString("rango"));
                fachadaBean.setPrefijo(rs.getString("prefijo"));
                fachadaBean.setEmail(rs.getString("email"));
                fachadaBean.setMargenDisponible(rs.getDouble("margenDisponible"));
                fachadaBean.setValorDisponible(
                        rs.getDouble("valorDisponible"));
                fachadaBean.setInventarioVisible(
                        rs.getBoolean("inventarioVisible"));
                fachadaBean.setInventarioVisible(
                        rs.getBoolean("inventarioVisible"));
                fachadaBean.setIpLocal(rs.getString("ipLocal"));


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

    // actualizaLocal
    public boolean actualizaLocal() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Vector contenedor = new Vector();
        Connection connection = null;

        String selectStr =
                "UPDATE tblLocales                           "
                + "SET    tblLocales.nombreLocal      = ( ? ), "
                + "       tblLocales.idBodega         = ( ? ), "
                + "       tblLocales.idActivo         = ( ? ), "
                + "       tblLocales.estado           = ( ? ), "
                + "       tblLocales.razonSocial      = ( ? ), "
                + "       tblLocales.nit              = ( ? ), "
                + "       tblLocales.direccion        = ( ? ), "
                + "       tblLocales.telefono         = ( ? ), "
                + "       tblLocales.regimen          = ( ? ), "
                + "       tblLocales.resolucion       = ( ? ), "
                + "       tblLocales.ciudad           = ( ? ), "
                + "       tblLocales.txtFactura       = ( ? ), "
                + "       tblLocales.rango            = ( ? ), "
                + "       tblLocales.prefijo          = ( ? ), "
                + "       tblLocales.email            = ( ? ), "
                + "       tblLocales.direccionIp      = ( ? ) "
                + "WHERE tblLocales.idLocal           = ( ? )  ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            //
            selectStatement.setString(1, getNombreLocal().toUpperCase().trim());
            selectStatement.setInt(2, getIdBodega());
            selectStatement.setInt(3, getIdActivo());
            selectStatement.setInt(4, getEstado());
            selectStatement.setString(5, getRazonSocial().toUpperCase().trim());
            selectStatement.setString(6, getNit().toUpperCase().trim());
            selectStatement.setString(7, getDireccion().toUpperCase().trim());
            selectStatement.setString(8, getTelefono().toUpperCase().trim());
            selectStatement.setString(9, getRegimen().toUpperCase().trim());
            selectStatement.setString(10, getResolucion().toUpperCase().trim());
            selectStatement.setString(11, getCiudad().toUpperCase().trim());
            selectStatement.setString(12, getTxtFactura().toUpperCase().trim());
            selectStatement.setString(13, getRango().toUpperCase().trim());
            selectStatement.setString(14, getPrefijo().toUpperCase().trim());
            selectStatement.setString(15, getEmail().toUpperCase().trim());
            selectStatement.setString(16, getDireccionIp().toUpperCase().trim());
            selectStatement.setInt(17, getIdLocal());

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

    //listaUnLocal
    public FachadaLocalBean listaUnLocal() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        FachadaLocalBean fachadaBean = new FachadaLocalBean();
        Connection connection = null;

        String selectStr =
                "SELECT  tblLocales.idLocal,       "
                + "      tblLocales.nombreLocal,     "
                + "      tblLocales.estado,          "
                + "      tblLocales.razonSocial,     "
                + "      tblLocales.nit,             "
                + "      tblLocales.direccion,       "
                + "      tblLocales.telefono,        "
                + "      tblLocales.fax,             "
                + "      tblLocales.regimen,         "
                + "      tblLocales.resolucion,      "
                + "      tblLocales.fechaResolucion, "
                + "      tblLocales.ciudad,          "
                + "      tblLocales.txtFactura,      "
                + "      tblLocales.rango,           "
                + "      tblLocales.prefijo,         "
                + "      tblLocales.email,           "
                + "      tblLocales.margenDisponible,"
                + "      tblLocales.valorDisponible, "
                + "     tblLocales.inventarioVisible,"
                + "      tblLocales.ipLocal          "
                + "FROM  tblLocales                  "
                + "WHERE tblLocales.idLocal    =     "
                + getIdLocal();

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
                fachadaBean.setNombreLocal(rs.getString("nombreLocal"));
                fachadaBean.setEstado(rs.getInt("estado"));
                fachadaBean.setRazonSocial(rs.getString("razonSocial"));
                fachadaBean.setNit(rs.getString("nit"));
                fachadaBean.setDireccion(rs.getString("direccion"));
                fachadaBean.setTelefono(rs.getString("telefono"));
                fachadaBean.setFax(rs.getString("fax"));
                fachadaBean.setResolucion(rs.getString("resolucion"));
                fachadaBean.setFechaResolucionStr(
                        rs.getString("fechaResolucion"));
                fachadaBean.setCiudad(rs.getString("ciudad"));
                fachadaBean.setTxtFactura(rs.getString("txtFactura"));
                fachadaBean.setRango(rs.getString("rango"));
                fachadaBean.setPrefijo(rs.getString("prefijo"));
                fachadaBean.setEmail(rs.getString("email"));
                fachadaBean.setMargenDisponible(rs.getDouble("margenDisponible"));
                fachadaBean.setValorDisponible(
                        rs.getDouble("valorDisponible"));
                fachadaBean.setInventarioVisible(
                        rs.getBoolean("inventarioVisible"));
                fachadaBean.setInventarioVisible(
                        rs.getBoolean("inventarioVisible"));
                fachadaBean.setIpLocal(rs.getString("ipLocal"));

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

    public FachadaLocalBean listaLocalLogo() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        FachadaLocalBean fachadaBean = new FachadaLocalBean();
        Connection connection = null;

        String selectStr =
                "SELECT  tblLocales.idLocal,       "
                + "      tblLocales.nombreLocal,     "
                + "      tblLocales.estado,          "
                + "      tblLocales.razonSocial,     "
                + "      tblLocales.nit,             "
                + "      tblLocales.direccion,       "
                + "      tblLocales.telefono,        "
                + "      tblLocales.fax,             "
                + "      tblLocales.regimen,         "
                + "      tblLocales.resolucion,      "
                + "      tblLocales.fechaResolucion, "
                + "      tblLocales.ciudad,          "
                + "      tblLocales.txtFactura,      "
                + "      tblLocales.rango,           "
                + "      tblLocales.prefijo,         "
                + "      tblLocales.email,           "
                + "      tblLocales.margenDisponible,"
                + "      tblLocales.valorDisponible, "
                + "     tblLocales.inventarioVisible,"
                + "      tblLocales.ipLocal          "
                + "FROM  tblLocales                  "
                + "WHERE tblLocales.estado    =      "
                + getEstado();

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
                fachadaBean.setNombreLocal(rs.getString("nombreLocal"));
                fachadaBean.setEstado(rs.getInt("estado"));
                fachadaBean.setRazonSocial(rs.getString("razonSocial"));
                fachadaBean.setNit(rs.getString("nit"));
                fachadaBean.setDireccion(rs.getString("direccion"));
                fachadaBean.setTelefono(rs.getString("telefono"));
                fachadaBean.setFax(rs.getString("fax"));
                fachadaBean.setResolucion(rs.getString("resolucion"));
                fachadaBean.setFechaResolucionStr(
                        rs.getString("fechaResolucion"));
                fachadaBean.setCiudad(rs.getString("ciudad"));
                fachadaBean.setTxtFactura(rs.getString("txtFactura"));
                fachadaBean.setRango(rs.getString("rango"));
                fachadaBean.setPrefijo(rs.getString("prefijo"));
                fachadaBean.setEmail(rs.getString("email"));
                fachadaBean.setMargenDisponible(
                                              rs.getDouble("margenDisponible"));
                fachadaBean.setValorDisponible(
                        rs.getDouble("valorDisponible"));
                fachadaBean.setInventarioVisible(
                        rs.getBoolean("inventarioVisible"));
                fachadaBean.setInventarioVisible(
                        rs.getBoolean("inventarioVisible"));
                fachadaBean.setIpLocal(rs.getString("ipLocal"));

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
}
