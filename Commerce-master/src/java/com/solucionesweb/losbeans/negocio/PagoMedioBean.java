package com.solucionesweb.losbeans.negocio;

// Importa la clase helper JDBCAccess que contiene los accesos a la base de datos
import com.solucionesweb.lasayudas.JDBCAccess;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaPagoMedioBean;

// Importa los paquetes de java
import java.sql.*;
import java.io.*;
import java.util.*;
import java.io.IOException.*;

// Importa los paquetes de javax
import javax.naming.*;

public class PagoMedioBean extends FachadaPagoMedioBean
        implements Serializable {

    // Nombre JNDI del Data Source que esta clase requiere
    private static final String DATA_SOURCE_NAME = "CommerceAccess";

    // Clase helper usada para acceder a la base de datos
    private JDBCAccess jdbcAccess;

    // Metodo constructor por defecto sin parametros
    public PagoMedioBean() {

        // Al instanciar el Bean establece la conexion a la base de datos
        jdbcAccess = new JDBCAccess(DATA_SOURCE_NAME);
    }

    // ingresar
    public boolean ingresar() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        boolean validaOk = false;
        Connection connection = null;

        String updateStr =
                "INSERT INTO tblPagosMedios   " +
                "           (idLocal          " +
                "           ,idTipoOrden      " +
                "           ,idRecibo         " +
                "           ,indicador        " +
                "           ,idMedio          " +
                "           ,vrMedio          " +
                "           ,fechaCobro       " +
                "           ,idBanco          " +
                "           ,idDctoMedio      " +
                "           ,estado           " +
                "           ,idLog)           " +
                "VALUES (                     " +
                getIdLocal() + ",             " +
                getIdTipoOrden() + ",         " +
                getIdRecibo() + ",            " +
                getIndicador() + ",           " +
                getIdMedio() + ",             " +
                getVrMedio() + ",            '" +
                getFechaCobroSqlServer() + "'," +
                getIdBanco() + ",            '" +
                getIdDctoMedio() + "',        " +
                getEstado() + ",              " +
                getIdLog() + ")";


        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(updateStr);

            // Obtiene el resultset
            selectStatement.executeUpdate();

            //
            validaOk = true;

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
            return validaOk;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {
            // Cierra la conexion a la base de datos limpiando la statement usada y la
            // conexion
            return validaOk;
        }
    }

    // retiraPagoDctoTemporal
    public boolean retiraPagoDctoTemporal(int xIdDcto) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                "DELETE FROM tblPagosMedios      " +
                "FROM   tblPagos                 " +
                "INNER JOIN tblPagosMedios       " +
                "ON tblPagos.idLocal         =   " +
                "     tblPagosMedios.idLocal     " +
                "AND tblPagos.idTipoOrden    =   " +
                " tblPagosMedios.idTipoOrden     " +
                "AND tblPagos.idRecibo       =   " +
                "    tblPagosMedios.idRecibo     " +
                "AND tblPagos.indicador      =   " +
                "   tblPagosMedios.indicador     " +
                "AND tblPagos.idLog          =   " +
                "   tblPagosMedios.idLog         " +
                "WHERE   tblPagos.idLocal    =   " +
                getIdLocal() + "                 " +
                "AND tblPagos.idTipoOrden    =   " +
                getIdTipoOrden() + "             " +
                "AND tblPagos.indicador      =   " +
                getIndicador() + "               " +
                "AND tblPagos.idLog          =   " +
                getIdLog() + "                   " +
                "AND tblPagos.idDcto         =   " +
                xIdDcto;
 
        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            selectStatement.execute();
            okIngresar = true;

        // Cierra el Resultset

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
            return okIngresar;
        }
    }

    // retiraPagoTemporal
    public boolean retiraPagoTemporal(int xIdLog) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                "DELETE FROM tblPagosMedios      " +
                "FROM   tblPagos                 " +
                "INNER JOIN tblPagosMedios       " +
                "ON tblPagos.idLocal         =   " +
                "     tblPagosMedios.idLocal     " +
                "AND tblPagos.idTipoOrden    =   " +
                " tblPagosMedios.idTipoOrden     " +
                "AND tblPagos.idRecibo       =   " +
                "    tblPagosMedios.idRecibo     " +
                "AND tblPagos.indicador      =   " +
                "   tblPagosMedios.indicador     " +
                "WHERE   tblPagos.idLocal    =   " +
                getIdLocal() + "                 " +
                "AND tblPagos.idTipoOrden    =   " +
                getIdTipoOrden() + "             " +
                "AND tblPagos.idRecibo       =   " +
                getIdRecibo() + "                " +
                "AND tblPagos.idLog          =   " +
                xIdLog;

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            selectStatement.execute();
            okIngresar = true;

        // Cierra el Resultset

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
            return okIngresar;
        }
    }

    // retiraPagoTemporalLog
    public boolean retiraPagoTemporalLog() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                "DELETE FROM tblPagosMedios      " +
                "FROM   tblPagos                 " +
                "INNER JOIN tblPagosMedios       " +
                "ON tblPagos.idLocal         =   " +
                "     tblPagosMedios.idLocal     " +
                "AND tblPagos.idTipoOrden    =   " +
                " tblPagosMedios.idTipoOrden     " +
                "AND tblPagos.idRecibo       =   " +
                "    tblPagosMedios.idRecibo     " +
                "AND tblPagos.indicador      =   " +
                "   tblPagosMedios.indicador     " +
                "WHERE   tblPagos.idLocal    =   " +
                getIdLocal() + "                 " +
                "AND tblPagos.idTipoOrden    =   " +
                getIdTipoOrden() + "             " +
                "AND tblPagos.idLog          =   " +
                getIdLog();

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            selectStatement.execute();
            okIngresar = true;

        // Cierra el Resultset

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
            return okIngresar;
        }
    }

    // retiraReciboTemporal
    public boolean retiraReciboTemporal() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                "DELETE FROM tblPagosMedios       " +
                "WHERE tblPagosMedios.idLocal   = " +
                getIdLocal() + "                  " +
                "AND tblPagosMedios.idTipoOrden = " +
                getIdTipoOrden() + "              " +
                "AND tblPagosMedios.indicador   = " +
                getIndicador() + "                " +
                "AND tblPagosMedios.idRecibo    = " +
                getIdRecibo();

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            selectStatement.execute();
            okIngresar = true;

        // Cierra el Resultset

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
            return okIngresar;
        }
    }

    // actualizarPagoTemporal
    public boolean actualizarPagoTemporal(int xIdTipoOrdenTmp,
            double xIdDcto) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                "UPDATE tblPagosMedios           " +
                "SET tblPagosMedios.idRecibo   = " +
                getIdRecibo() + ",               " +
                "    tblPagosMedios.idTipoOrden= " +
                getIdTipoOrden() + "             " +
                "FROM   tblPagos                 " +
                "INNER JOIN tblPagosMedios       " +
                "ON tblPagos.idLocal         =   " +
                "     tblPagosMedios.idLocal     " +
                "AND tblPagos.idTipoOrden    =   " +
                " tblPagosMedios.idTipoOrden     " +
                "AND tblPagos.idRecibo       =   " +
                "    tblPagosMedios.idRecibo     " +
                "AND tblPagos.indicador      =   " +
                "   tblPagosMedios.indicador     " +
                "WHERE   tblPagos.idLocal    =   " +
                getIdLocal() + "                 " +
                "AND tblPagos.idTipoOrden    =   " +
                xIdTipoOrdenTmp + "              " +
                "AND tblPagos.indicador      =   " +
                getIndicador() + "               " +
                "AND tblPagos.idDcto         =   " +
                xIdDcto + "                     ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            selectStatement.execute();
            okIngresar = true;

        // Cierra el Resultset

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
            return okIngresar;
        }
    }

    // actualizarMedioTemporal
    public boolean actualizarMedioTemporal(int xIdTipoOrdenTmp,
            double xIdDcto) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                "UPDATE tblPagosMedios          " +
                "SET tblPagosMedios.idMedio  =  " +
                getIdMedio() + ",               " +
                "    tblPagosMedios.idBanco  =  " +
                getIdBanco() + ",               " +
                " tblPagosMedios.fechaCobro  = '" +
                getFechaCobroSqlServer() + "',  " +
                " tblPagosMedios.idDctoMedio = '" +
                getIdDctoMedio() + "'           " +
                "FROM   tblPagos                " +
                "INNER JOIN tblPagosMedios      " +
                "ON tblPagos.idLocal         =  " +
                "     tblPagosMedios.idLocal    " +
                "AND tblPagos.idTipoOrden    =  " +
                " tblPagosMedios.idTipoOrden    " +
                "AND tblPagos.idRecibo       =  " +
                "    tblPagosMedios.idRecibo    " +
                "AND tblPagos.indicador      =  " +
                "   tblPagosMedios.indicador    " +
                "WHERE   tblPagos.idLocal    =  " +
                getIdLocal() + "                " +
                "AND tblPagos.idTipoOrden    =  " +
                xIdTipoOrdenTmp + "             " +
                "AND tblPagos.indicador      =  " +
                getIndicador() + "              " +
                "AND tblPagos.idDcto         =  " +
                xIdDcto + "                     ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            selectStatement.execute();
            okIngresar = true;

        // Cierra el Resultset

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
            return okIngresar;
        }
    }

    // ingresaRetiro
    public boolean ingresaRetiro(int xIdReciboNew) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        boolean validaOk = false;
        Connection connection = null;

        String updateStr =
                "INSERT INTO tblPagosMedios         " +
                "           (idLocal                " +
                "           ,idTipoOrden            " +
                "           ,idRecibo               " +
                "           ,indicador              " +
                "           ,idDctoMedio            " +
                "           ,idMedio                " +
                "           ,vrMedio                " +
                "           ,fechaCobro             " +
                "           ,idBanco                " +
                "           ,estado                 " +
                "           ,idLog)                 " +
                "SELECT tblPagosMedios.idLocal      " +
                "      ,tblPagosMedios.idTipoOrden, " +
                xIdReciboNew + "                    " +
                "      ,tblPagosMedios.indicador    " +
                "      ,tblPagosMedios.idDctoMedio  " +
                "      ,tblPagosMedios.idMedio      " +
                "      ,tblPagosMedios.vrMedio *    " +
                "                  (-1) AS vrMedio  " +
                "      ,tblPagosMedios.fechaCobro   " +
                "      ,tblPagosMedios.idBanco      " +
                "      ,tblPagosMedios.estado,      " +
                getIdLog() + "                      " +
                "FROM tblPagosMedios                " +
                "WHERE tblPagosMedios.idLocal     = " +
                getIdLocal() + "                    " +
                "AND   tblPagosMedios.idTipoOrden = " +
                getIdTipoOrden() + "                " +
                "AND   tblPagosMedios.idRecibo    = " +
                getIdRecibo() + "                   " +
                "AND   tblPagosMedios.indicador   = " +
                getIndicador();

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(updateStr);

            // Obtiene el resultset
            selectStatement.executeUpdate();

            //
            validaOk = true;

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
            return validaOk;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {
            // Cierra la conexion a la base de datos limpiando la statement usada y la
            // conexion
            return validaOk;
        }
    }

    // Metodo listaPagoLog
    public Vector listaPagoLog() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        String selectStr =
                "SELECT tblPagosMedios.idLocal         " +
                "      ,tblPagosMedios.idTipoOrden     " +
                "      ,tblPagosMedios.idRecibo        " +
                "      ,tblPagosMedios.indicador       " +
                "      ,tblPagosMedios.idDctoMedio     " +
                "      ,tblPagosMedios.idMedio         " +
                "      ,tblPagosMedios.vrMedio         " +
                "      ,tblPagosMedios.fechaCobro      " +
                "      ,tblPagosMedios.idBanco         " +
                "      ,tblPagosMedios.estado,         " +
                "   ( SELECT tblMediosPago.nombreMedio " +
                "     FROM  tblMediosPago              " +
                "     WHERE tblMediosPago.idMedio =    " +
                "             tblPagosMedios.idMedio ) " +
                "                      AS nombreMedio, " +
                "   ( SELECT tblBancos.nombreBanco     " +
                "     FROM  tblBancos                  " +
                "     WHERE tblBancos.idBanco    =     " +
                "             tblPagosMedios.idBanco ) " +
                "                       AS nombreBanco " +
                "FROM tblPagosMedios                   " +
                "WHERE EXISTS (                        " +
                "    SELECT tblPagos.*                 " +
                "    FROM tblPagos                     " +
                "    WHERE tblPagos.idLocal     =      " +
                "               tblPagosMedios.idLocal " +
                "    AND   tblPagos.idTipoOrden =      " +
                "          tblPagosMedios.idTipoOrden  " +
                "    AND   tblPagos.idRecibo    =      " +
                "             tblPagosMedios.idRecibo  " +
                "    AND tblPagos.idTipoOrden   =      " +
                getIdTipoOrden() + "                   " +
                "    AND tblPagos.idLog         =      " +
                getIdLog() + "                         " +
                "    AND tblPagos.idLocal       =      " +
                getIdLocal() + ")";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaPagoMedioBean fachadaPagoMedioBean;

            while (rs.next()) {

                //
                fachadaPagoMedioBean = new FachadaPagoMedioBean();

                //
                fachadaPagoMedioBean.setIdLocal(rs.getInt("idLocal"));
                fachadaPagoMedioBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaPagoMedioBean.setIdRecibo(rs.getInt("idRecibo"));
                fachadaPagoMedioBean.setIndicador(rs.getInt("indicador"));
                fachadaPagoMedioBean.setIdDctoMedio(
                        rs.getString("idDctoMedio"));
                fachadaPagoMedioBean.setIdMedio(rs.getInt("idMedio"));
                fachadaPagoMedioBean.setVrMedio(rs.getDouble("vrMedio"));
                fachadaPagoMedioBean.setFechaCobro(rs.getString("fechaCobro"));
                fachadaPagoMedioBean.setIdBanco(rs.getInt("idBanco"));
                fachadaPagoMedioBean.setEstado(rs.getInt("estado"));
                fachadaPagoMedioBean.setNombreMedio(
                        rs.getString("nombreMedio"));
                fachadaPagoMedioBean.setNombreBanco(
                        rs.getString("nombreBanco"));

                //
                contenedor.add(fachadaPagoMedioBean);
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

    // Metodo liquidaPagoLogFCH
    public FachadaPagoMedioBean liquidaPagoLogFCH() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        // Variable de fachada de los datos
        FachadaPagoMedioBean fachadaPagoMedioBean = new FachadaPagoMedioBean();


        Connection connection = null;

        String selectStr =
                "SELECT tblPagosMedios.idLocal         " +
                "      ,SUM(tblPagosMedios.vrMedio)    " +
                "                           AS vrMedio " +
                "FROM tblPagosMedios                   " +
                "WHERE EXISTS (                        " +
                "    SELECT tblPagos.*                 " +
                "    FROM tblPagos                     " +
                "    WHERE tblPagos.idLocal     =      " +
                "               tblPagosMedios.idLocal " +
                "    AND   tblPagos.idTipoOrden =      " +
                "          tblPagosMedios.idTipoOrden  " +
                "    AND   tblPagos.idRecibo    =      " +
                "             tblPagosMedios.idRecibo  " +
                "    AND   tblPagos.idLog       =      " +
                "                tblPagosMedios.idLog  " +
                "    AND tblPagos.idTipoOrden   =      " +
                getIdTipoOrden() + "                   " +
                "    AND tblPagos.idLog         =      " +
                getIdLog() + "                         " +
                "    AND tblPagos.idLocal       =      " +
                getIdLocal() + ")                      " +
                "GROUP BY tblPagosMedios.idLocal       ";

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
                fachadaPagoMedioBean.setVrMedio(rs.getDouble("vrMedio"));

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
            return fachadaPagoMedioBean;

        }
    }

    // ingresaPagoLog
    public boolean ingresaPagoLog(int xIdTipoOrdenNew,
            int xIdReciboNew,
            int xIndicadorNew) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        boolean validaOk = false;
        Connection connection = null;

        String updateStr =
                "INSERT INTO tblPagosMedios " +
                "           (idLocal        " +
                "           ,idTipoOrden    " +
                "           ,idRecibo       " +
                "           ,indicador      " +
                "           ,idMedio        " +
                "           ,vrMedio        " +
                "           ,fechaCobro     " +
                "           ,idBanco        " +
                "           ,idDctoMedio    " +
                "           ,estado         " +
                "           ,idLog)         " +
                "SELECT tblPagosMedios.idLocal,         " +
                xIdTipoOrdenNew + " AS idTipoOrden,     " +
                xIdReciboNew + " AS idRecibo,           " +
                xIndicadorNew + " AS indicador          " +
                "      ,tblPagosMedios.idMedio          " +
                "      ,tblPagosMedios.vrMedio          " +
                "      ,tblPagosMedios.fechaCobro       " +
                "      ,tblPagosMedios.idBanco          " +
                "      ,tblPagosMedios.idDctoMedio      " +
                "      ,tblPagosMedios.estado AS estado " +
                "      ,tblPagosMedios.idLog  AS idLog  " +
                "FROM tblPagosMedios                    " +
                "WHERE EXISTS (                         " +
                "SELECT tblPagos.*                      " +
                "FROM tblPagos                          " +
                "WHERE tblPagos.idLocal     =           " +
                "             tblPagosMedios.idLocal    " +
                "AND   tblPagos.idTipoOrden =           " +
                "         tblPagosMedios.idTipoOrden    " +
                "AND   tblPagos.idRecibo    =           " +
                "            tblPagosMedios.idRecibo    " +
                "AND   tblPagos.indicador   =           " +
                "           tblPagosMedios.indicador    " +
                "AND   tblPagos.idLog       =           " +
                "           tblPagosMedios.idLog        " +
                "AND tblPagos.idLocal     =             " +
                getIdLocal() + "                        " +
                "AND tblPagos.idTipoOrden =             " +
                getIdTipoOrden() + "                    " +
                "AND tblPagos.idRecibo    =             " +
                getIdRecibo() + "                       " +
                "AND tblPagos.idLog       =             " +
                getIdLog() + " )";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(updateStr);

            // Obtiene el resultset
            selectStatement.executeUpdate();

            //
            validaOk = true;

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
            return validaOk;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {
            // Cierra la conexion a la base de datos limpiando la statement usada y la
            // conexion
            return validaOk;
        }
    }

    // ingresaPago
    public boolean ingresaPago(int xIdTipoOrdenNew,
            int xIdReciboNew,
            int xIndicadorNew,
            double xIdDcto) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        boolean validaOk = false;
        Connection connection = null;

        String updateStr =
                "INSERT INTO tblPagosMedios " +
                "           (idLocal        " +
                "           ,idTipoOrden    " +
                "           ,idRecibo       " +
                "           ,indicador      " +
                "           ,idMedio        " +
                "           ,vrMedio        " +
                "           ,fechaCobro     " +
                "           ,idBanco        " +
                "           ,idDctoMedio    " +
                "           ,estado         " +
                "           ,idLog)         " +
                "SELECT tblPagosMedios.idLocal,         " +
                xIdTipoOrdenNew + " AS idTipoOrden,     " +
                xIdReciboNew + " AS idRecibo,           " +
                xIndicadorNew + " AS indicador          " +
                "      ,tblPagosMedios.idMedio          " +
                "      ,tblPagosMedios.vrMedio          " +
                "      ,tblPagosMedios.fechaCobro       " +
                "      ,tblPagosMedios.idBanco          " +
                "      ,tblPagosMedios.idDctoMedio      " +
                "      ,tblPagosMedios.estado AS estado " +
                "      ,tblPagosMedios.idLog  AS idLog  " +
                "FROM tblPagosMedios                    " +
                "WHERE EXISTS (                         " +
                "SELECT tblPagos.*                      " +
                "FROM tblPagos                          " +
                "WHERE tblPagos.idLocal     =           " +
                "             tblPagosMedios.idLocal    " +
                "AND   tblPagos.idTipoOrden =           " +
                "         tblPagosMedios.idTipoOrden    " +
                "AND   tblPagos.idRecibo    =           " +
                "            tblPagosMedios.idRecibo    " +
                "AND   tblPagos.indicador   =           " +
                "           tblPagosMedios.indicador    " +
                "AND   tblPagos.idLog       =           " +
                "           tblPagosMedios.idLog        " +
                "AND tblPagos.idLocal     =             " +
                getIdLocal() + "                        " +
                "AND tblPagos.idTipoOrden =             " +
                getIdTipoOrden() + "                    " +
                "AND tblPagos.idRecibo    =             " +
                getIdRecibo() + "                       " +
                "AND tblPagos.idDcto      =             " +
                xIdDcto + "                             " +
                "AND tblPagos.idLog       =             " +
                getIdLog() + " )";
 
        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(updateStr);

            // Obtiene el resultset
            selectStatement.executeUpdate();

            //
            validaOk = true;

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
            return validaOk;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {
            // Cierra la conexion a la base de datos limpiando la statement usada y la
            // conexion
            return validaOk;
        }
    }

    // retiraPagoLog
    public boolean retiraPagoLog() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                "DELETE tblPagosMedios          " +
                "WHERE EXISTS (                 " +
                "SELECT tblPagos.*              " +
                "FROM tblPagos                  " +
                "WHERE tblPagos.idLocal     =   " +
                "        tblPagosMedios.idLocal " +
                "AND   tblPagos.idTipoOrden =   " +
                "    tblPagosMedios.idTipoOrden " +
                "AND   tblPagos.idRecibo    =   " +
                "       tblPagosMedios.idRecibo " +
                "AND   tblPagos.indicador   =   " +
                "      tblPagosMedios.indicador " +
                "AND   tblPagos.idLog       =   " +
                "          tblPagosMedios.idLog " +
                "AND tblPagos.idLocal       =   " +
                getIdLocal() + "                " +
                "AND tblPagos.idTipoOrden   =   " +
                getIdTipoOrden() + "            " +
                "AND tblPagos.idLog         =   " +
                getIdLog() + ")";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            selectStatement.execute();
            okIngresar = true;

        // Cierra el Resultset

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
            return okIngresar;
        }
    }

    // listaPagoProceso
    public Vector listaPagoProceso() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        Vector contenedor = new Vector();

        Connection connection = null;

        String insertStr =
                "SELECT tblPagosMedios.idLocal       " +
                "      ,tblPagosMedios.idTipoOrden   " +
                "      ,tblPagosMedios.idRecibo      " +
                "      ,tblPagosMedios.indicador     " +
                "      ,tblPagosMedios.idMedio       " +
                "      ,SUM(tblPagosMedios.vrMedio)  " +
                "                        AS vrMedio  " +
                "    ,MAX(tblPagosMedios.fechaCobro) " +
                "                    AS fechaCobro   " +
                "    ,MAX(tblPagosMedios.idBanco)    " +
                "                    AS  idBanco     " +
                "    ,tblPagosMedios.idDctoMedio     " +
                "FROM tblPagosMedios                 " +
                "WHERE EXISTS (                      " +
                "SELECT tblPagos.*                   " +
                "FROM tblPagos                       " +
                "WHERE tblPagos.idLocal         =    " +
                "                  tblPagos.idLocal  " +
                "AND tblPagos.idTipoOrden       =    " +
                "              tblPagos.idTipoOrden  " +
                "AND tblPagos.idRecibo          =    " +
                "                 tblPagos.idRecibo  " +
                "AND tblPagos.indicador         =    " +
                "                tblPagos.indicador) " +
                "AND tblPagosMedios.idLocal     =    " +
                getIdLocal() + "                     " +
                "AND tblPagosMedios.idTipoOrden =    " +
                getIdTipoOrden() + "                 " +
                "AND tblPagosMedios.idLog       =    " +
                getIdLog() + "                       " +
                "GROUP BY tblPagosMedios.idLocal     " +
                "      ,tblPagosMedios.idTipoOrden   " +
                "      ,tblPagosMedios.idRecibo      " +
                "      ,tblPagosMedios.indicador     " +
                "      ,tblPagosMedios.idMedio       " +
                "      ,tblPagosMedios.idDctoMedio   ";

        PreparedStatement sentenciaSQL = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            sentenciaSQL = connection.prepareStatement(insertStr);

            // Variable de fachada de los datos
            FachadaPagoMedioBean fachadaBean;

            // Obtiene el resultset
            ResultSet rs = sentenciaSQL.executeQuery();

            while (rs.next()) {

                //
                fachadaBean = new FachadaPagoMedioBean();

                //
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setIdRecibo(rs.getInt("idRecibo"));
                fachadaBean.setIdDctoMedio(rs.getString("idDctoMedio"));
                fachadaBean.setIndicador(rs.getInt("indicador"));
                fachadaBean.setIdMedio(rs.getInt("idMedio"));
                fachadaBean.setVrMedio(rs.getDouble("vrMedio"));
                fachadaBean.setFechaCobro(rs.getString("fechaCobro"));
                fachadaBean.setIdBanco(rs.getString("idBanco"));
                fachadaBean.setIdDctoMedio(rs.getString("idDctoMedio"));

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


        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {

            //
            jdbcAccess.cleanup(connection, sentenciaSQL, null);
            return contenedor;
        }
    }
}
