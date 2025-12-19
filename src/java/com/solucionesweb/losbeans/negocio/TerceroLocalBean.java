package com.solucionesweb.losbeans.negocio;

// Importa la clase helper JDBCAccess que contiene los accesos a la base de datos
import com.solucionesweb.lasayudas.JDBCAccess;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaTerceroBean;

// Importa los paquetes de java
import java.sql.*;
import java.io.*;
import java.io.IOException.*;
import java.util.Vector;

// Importa los paquetes de javax
import javax.naming.*;

// Importa la interface de las constantes
import com.solucionesweb.losbeans.utilidades.IConstantes;

public class TerceroLocalBean extends FachadaTerceroBean
        implements Serializable, IConstantes {

    // Nombre JNDI del Data Source que esta clase requiere
    private static final String DATA_SOURCE_NAME = "CommerceAccess";

    // Clase helper usada para acceder a la base de datos
    private JDBCAccess jdbcAccess;

    // Metodo constructor por defecto sin parametros
    public TerceroLocalBean() {

        // Al instanciar el Bean establece la conexion a la base de datos
        jdbcAccess = new JDBCAccess(DATA_SOURCE_NAME);
    }

    // ingresa
    public boolean ingresa() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        //
        String selectStr =
                "INSERT INTO tblTercerosLocales  ( idCliente,  " +
                "                           idLocalTercero,    " +
                "                           nombreEmpresa,     " +
                "                           direccionTercero,  " +
                "                           idDptoCiudad,      " +
                "                           telefonoFijo,      " +
                "                           telefonoCelular,   " +
                "                           telefonoFax,       " +
                "                           email,             " +
                "                           estado,            " +
                "                           contactoTercero,   " +
                "                           idVendedor,        " +
                "                           idSeq   )          " +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)            ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            //
            selectStatement.setString(1, getIdCliente());
            selectStatement.setInt(2, getIdLocalTercero());
            selectStatement.setString(3, getNombreEmpresa());
            selectStatement.setString(4, getDireccionTercero());
            selectStatement.setInt(5, getIdDptoCiudad());
            selectStatement.setString(6, getTelefonoFijo());
            selectStatement.setString(7, getTelefonoCelular());
            selectStatement.setString(8, getTelefonoFax());
            selectStatement.setString(9, getEmail());
            selectStatement.setInt(10, getEstado());
            selectStatement.setString(11, getContactoTercero());
            selectStatement.setDouble(12, getIdVendedor());
            selectStatement.setDouble(13, getIdSeq());

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

    // ingresa
    public boolean modifica() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        //
        String selectStr =
                "UPDATE tblTercerosLocales                  " +
                "SET tblTercerosLocales.idCliente         ='" +
                getIdCliente() + "' ,                       " +
                "    tblTercerosLocales.idLocalTercero    = " +
                getIdLocalTercero() + "  ,                  " +
                "    tblTercerosLocales.nombreEmpresa     ='" +
                getNombreEmpresa() + "' ,                   " +
                "    tblTercerosLocales.direccionTercero  ='" +
                getDireccionTercero() + "' ,                " +
                "    tblTercerosLocales.idDptoCiudad      = " +
                getIdDptoCiudad() + " ,                     " +
                "    tblTercerosLocales.telefonoFijo      ='" +
                getTelefonoFijo() + "' ,                    " +
                "    tblTercerosLocales.telefonoCelular   ='" +
                getTelefonoCelular() + "' ,                 " +
                "    tblTercerosLocales.telefonoFax       ='" +
                getTelefonoFax() + "' ,                     " +
                "    tblTercerosLocales.email             ='" +
                getEmail() + "' ,                           " +
                "    tblTercerosLocales.estado            = " +
                getEstado() + " ,                           " +
                "    tblTercerosLocales.contactoTercero   ='" +
                getContactoTercero() + "' ,                 " +
                "    tblTercerosLocales.idVendedor        = " +
                getIdVendedor() + " ,                       " +
                "    tblTercerosLocales.idSeq             = " +
                getIdSeq() + "                              " +
                "WHERE tblTercerosLocales.idCliente       ='" +
                getIdCliente() + "'  AND                    " +
                "tblTercerosLocales.idLocalTercero        = " +
                getIdLocalTercero();

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

    // maxIdLocal
    public int maxIdLocal() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        //
        int intMaxIdLocal = 0;

        Connection connection = null;

        //
        String selectStr =
                "SELECT MAX(tblTercerosLocales.idLocalTercero) " +
                "                        AS maxIdLocalTercero  " +
                "FROM tblTercerosLocales                       " +
                "WHERE tblTercerosLocales.idCliente =         '" +
                getIdCliente() + "'";

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
                intMaxIdLocal = rs.getInt("maxIdLocalTercero");

            }

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

            // Cierra el connection
            jdbcAccess.cleanup(connection, selectStatement, null);
            return intMaxIdLocal;
        }
    }

    // listaIdSeqMaxima
    public int listaIdSeqMaxima() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        int idMaxIdSeq = 0;
        Connection connection = null;

        String selectStr =
                "SELECT MAX(tblTercerosLocales.idSeq) " +
                "                         AS maxIdSeq " +
                "FROM tblTercerosLocales              ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            if (rs.next()) {

                idMaxIdSeq = rs.getInt("maxIdSeq");

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
            // Cierra la conexion a la base de datos limpiando la statement usada y la
            jdbcAccess.cleanup(connection, selectStatement, null);
            return idMaxIdSeq;
        }
    }

    // listaLocal
    public Vector listaLocal() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        Vector contenedor = new Vector();
        Connection connection = null;

        String selectStr =
                "SELECT     tblTercerosLocales.idCliente,       " +
                "           tblTercerosLocales.idLocalTercero,  " +
                "           tblTercerosLocales.nombreEmpresa,   " +
                "           tblTercerosLocales.direccionTercero," +
                "           tblTercerosLocales.idDptoCiudad,    " +
                "           tblTercerosLocales.telefonoFijo,    " +
                "           tblTercerosLocales.telefonoCelular, " +
                "           tblTercerosLocales.telefonoFax,     " +
                "           tblTercerosLocales.email,           " +
                "           tblTercerosLocales.estado,          " +
                "           tblTercerosLocales.contactoTercero, " +
                "           tblTercerosLocales.idVendedor,      " +
                "           tblTercerosLocales.idSeq            " +
                "FROM       tblTerceros                         " +
                "INNER JOIN tblTercerosLocales                  " +
                "ON tblTerceros.idCliente                =      " +
                "                 tblTercerosLocales.idCliente  " +
                "WHERE     tblTercerosLocales.idCliente  =     '" +
                getIdCliente() + "'                             " +
                "AND       tblTercerosLocales.estado     = 1    " +
                "ORDER BY tblTercerosLocales.idLocalTercero ";

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

                //
                fachadaBean = new FachadaTerceroBean();

                //
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setIdLocalTercero(rs.getInt("idLocalTercero"));
                fachadaBean.setNombreEmpresa(rs.getString("nombreEmpresa"));
                fachadaBean.setDireccionTercero(
                        rs.getString("direccionTercero"));
                fachadaBean.setIdDptoCiudad(rs.getInt("idDptoCiudad"));
                fachadaBean.setTelefonoFijo(rs.getString("telefonoFijo"));
                fachadaBean.setTelefonoCelular(rs.getString("telefonoCelular"));
                fachadaBean.setTelefonoFax(rs.getString("telefonoFax"));
                fachadaBean.setEmail(rs.getString("email"));
                fachadaBean.setEstado(rs.getInt("estado"));
                fachadaBean.setContactoTercero(rs.getString("contactoTercero"));
                fachadaBean.setIdVendedor(rs.getDouble("idVendedor"));
                fachadaBean.setIdSeq(rs.getInt("idSeq"));

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
            // Cierra la conexion a la base de datos limpiando la statement usada y la
            jdbcAccess.cleanup(connection, selectStatement, null);
            return contenedor;
        }
    }

    // listaLocalFCH
    public FachadaTerceroBean listaLocalFCH() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        // Variable de fachada de los datos
        FachadaTerceroBean fachadaBean = new FachadaTerceroBean();

        Connection connection = null;

        String selectStr =
                "SELECT     tblTercerosLocales.idCliente,       " +
                "           tblTercerosLocales.idLocalTercero,  " +
                "           tblTercerosLocales.nombreEmpresa,   " +
                "           tblTercerosLocales.direccionTercero," +
                "           tblTercerosLocales.idDptoCiudad,    " +
                "           tblTercerosLocales.telefonoFijo,    " +
                "           tblTercerosLocales.telefonoCelular, " +
                "           tblTercerosLocales.telefonoFax,     " +
                "           tblTercerosLocales.email,           " +
                "           tblTercerosLocales.estado,          " +
                "           tblTercerosLocales.contactoTercero, " +
                "           tblTercerosLocales.idVendedor,      " +
                "           tblTercerosLocales.idSeq            " +
                "FROM       tblTerceros                         " +
                "INNER JOIN tblTercerosLocales                  " +
                "ON tblTerceros.idCliente                =      " +
                "                 tblTercerosLocales.idCliente  " +
                "WHERE     tblTercerosLocales.idCliente  =     '" +
                getIdCliente() + "'                             " +
                "AND       tblTercerosLocales.idLocalTercero  = " +
                getIdLocalTercero();

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
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setIdLocalTercero(rs.getInt("idLocalTercero"));
                fachadaBean.setNombreEmpresa(rs.getString("nombreEmpresa"));
                fachadaBean.setDireccionTercero(
                        rs.getString("direccionTercero"));
                fachadaBean.setIdDptoCiudad(rs.getInt("idDptoCiudad"));
                fachadaBean.setTelefonoFijo(rs.getString("telefonoFijo"));
                fachadaBean.setTelefonoCelular(rs.getString("telefonoCelular"));
                fachadaBean.setTelefonoFax(rs.getString("telefonoFax"));
                fachadaBean.setEmail(rs.getString("email"));
                fachadaBean.setEstado(rs.getInt("estado"));
                fachadaBean.setContactoTercero(rs.getString("contactoTercero"));
                fachadaBean.setIdVendedor(rs.getDouble("idVendedor"));
                fachadaBean.setIdSeq(rs.getInt("idSeq"));

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
            // Cierra la conexion a la base de datos limpiando la statement usada y la
            jdbcAccess.cleanup(connection, selectStatement, null);
            return fachadaBean;
        }
    }
}
