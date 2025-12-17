package com.solucionesweb.losbeans.negocio;

// Importa la clase helper JDBCAccess que contiene los accesos a la base de datos
import com.solucionesweb.lasayudas.JDBCAccess;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaContableRetencionBean;

// Importa los paquetes de java
import java.sql.*;
import java.io.*;
import java.io.IOException.*;
import java.util.Vector;

// Importa los paquetes de javax
import javax.naming.*;

public class ContableRetencionBean extends FachadaContableRetencionBean
        implements Serializable {

    // Nombre JNDI del Data Source que esta clase requiere
    private static final String DATA_SOURCE_NAME = "CommerceAccess";
    // Clase helper usada para acceder a la base de datos
    private JDBCAccess jdbcAccess;

    // Metodo constructor por defecto sin parametros
    public ContableRetencionBean() {

        // Al instanciar el Bean establece la conexion a la base de datos
        jdbcAccess = new JDBCAccess(DATA_SOURCE_NAME);
    }

    // calculaRetencion
    public double calculaRetencion(int xIdAutoRetenedor,
            int xIdConcepto,
            double xVrBase,
            int xIdRteFuenteVrBase) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        //
        double xPorcentajeRetencion = 0.0;
        double xVrRetencion = 0.0;
        double xVrBaseRetencion = 0.0;
        int xVrTope = 0;
        int xVrTope100 = 1;

        Connection connection = null;

        String selectStr
                = "SELECT tblContableRetencion.idConcepto,          "
                + "       tblContableRetencion.nombreConcepto,      "
                + "       tblContableRetencion.porcentajeRetencion, "
                + "       tblContableRetencion.vrBaseRetencion,     "
                + "       tblContableRetencion.estado               "
                + "FROM tblContableRetencion                        "
                + "WHERE tblContableRetencion.idConcepto        =   "
                + xIdConcepto + "                                   "
                + "AND   tblContableRetencion.idTipoOrdenAlcance=   "
                + xIdConcepto;

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
                xPorcentajeRetencion = rs.getDouble("porcentajeRetencion");
                xVrBaseRetencion = rs.getDouble("vrBaseRetencion");

            }

            // Cierra el Resultset
            rs.close();

            // Valida autoretenedor---------------------------------------------
            if (xIdAutoRetenedor == 0) {

                //--- xVrTope
                if (xIdRteFuenteVrBase == xVrTope) {

                    //
                    if (xVrBaseRetencion <= xVrBase) {

                        //
                        xVrRetencion = xVrBase * (xPorcentajeRetencion / 100);

                    }

                }

                //--- xVrTope100
                if (xIdRteFuenteVrBase == xVrTope100) {

                    //
                    xVrRetencion = xVrBase * (xPorcentajeRetencion / 100);
                }
            }

            /*----
             System.out.println(" xIdConcepto " + xIdConcepto );
             System.out.println(" xPorcentajeRetencion " + xPorcentajeRetencion );
             System.out.println(" xIdAutoRetenedor " + xIdAutoRetenedor );
             System.out.println(" xIdRteFuenteVrBase " + xIdRteFuenteVrBase );
             System.out.println(" xVrRetencion " + xVrRetencion );
             System.out.println(" xVrBase " + xVrBase );
             System.out.println(" xVrBaseRetencion " + xVrBaseRetencion );*/
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
            return xVrRetencion;

        }
    }

    // calculaRetencion
    public double calculaRteIva(int xIdAutoRetenedor,
            int xIdConcepto,
            double xIdRteIva,
            double xIdRteIvaVrBase,
            double xVrIva,
            double xVrBase) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        //
        double xPorcentajeRetencionIva = 0;
        double xVrRetencionIva = 0.0;
        double xVrBaseRetencion = 0.0;

        Connection connection = null;

        String selectStr
                = "SELECT tblContableRetencion.idConcepto,          "
                + "       tblContableRetencion.nombreConcepto,      "
                + "       tblContableRetencion.porcentajeRetencion, "
                + "       tblContableRetencion.vrBaseRetencion,     "
                + "       tblContableRetencion.estado               "
                + "FROM tblContableRetencion                        "
                + "WHERE tblContableRetencion.idConcepto        =  1 "
               + "AND   tblContableRetencion.idTipoOrdenAlcance =  2 ";
                
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
               xPorcentajeRetencionIva = rs.getDouble("porcentajeRetencion");
                xVrBaseRetencion = rs.getDouble("vrBaseRetencion");

            }

            // Cierra el Resultset
            rs.close();

            //  xIdRteIvaVrBase = TOPE + xIdRteIva = retenedorIVA
            if ((xIdRteIvaVrBase == 0) && (xIdRteIva == 0)) {

                // resta xVrBaseRetencion
                if (xVrBaseRetencion <= xVrBase) {

                    //
                    xVrRetencionIva = xVrIva * (xPorcentajeRetencionIva / 100);

                }
            }

            //  xIdRteIvaVrBase = 100% + xIdRteIva = retenedorIVA
            if ((xIdRteIvaVrBase == 1) && (xIdRteIva == 0)) {

                // 100% xVrBaseRetencion
                xVrRetencionIva = xVrIva * (xPorcentajeRetencionIva / 100);

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

            // Cierra la conexion
            jdbcAccess.cleanup(connection, selectStatement, null);
            return xVrRetencionIva;

        }
    }

    // calculaRetencion
    public double calculaRetencionNota(int xIdAutoRetenedor,
            int xIdConcepto,
            double xVrBase) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        //
        double xPorcentajeRetencion = 0.0;
        double xVrRetencion = 0.0;

        Connection connection = null;

        String selectStr
                = "SELECT tblContableRetencion.idConcepto,          "
                + "       tblContableRetencion.nombreConcepto,      "
                + "       tblContableRetencion.porcentajeRetencion, "
                + "       tblContableRetencion.vrBaseRetencion,     "
                + "       tblContableRetencion.estado               "
                + "FROM tblContableRetencion                        "
                + "WHERE tblContableRetencion.idConcepto         =  1 "
                //+ xIdConcepto + "                                   "
                + "AND   tblContableRetencion.idTipoOrdenAlcance =  "
                + xIdConcepto;

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
                xPorcentajeRetencion = rs.getDouble("porcentajeRetencion");

            }

            // Cierra el Resultset
            rs.close();

            // Valida autoretenedor
            if (xIdAutoRetenedor == 0) {

                //
                xVrRetencion = xVrBase * (xPorcentajeRetencion / 100);

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

            // Cierra la conexion
            jdbcAccess.cleanup(connection, selectStatement, null);
            return xVrRetencion;

        }
    }

    public Vector listaAll() {
        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();

        Vector contenedor = new Vector();

        Connection connection = null;

        String selectStr = " SELECT idConcepto                   "
                + ",idSubcuenta                "
                + ",idPersona                  "
                + ",nombreConcepto             "
                + ",porcentajeRetencion        "
                + ",vrBaseRetencion            "
                + ",estado                     "
                + ",idSeq                      "
                + ",idTipoOrdenAlcance   "
                + "FROM tblContableRetencion   "
                + "ORDER BY idConcepto                  "
                + ",idSubcuenta      ";

        PreparedStatement selectStatement = null;
        try {
            connection = this.jdbcAccess.getConnection();

            selectStatement = connection.prepareStatement(selectStr);

            ResultSet rs = selectStatement.executeQuery();
            while (rs.next()) {
                FachadaContableRetencionBean fachadaBean = new FachadaContableRetencionBean();

                fachadaBean.setIdConcepto(rs.getInt("idConcepto"));
                fachadaBean.setIdSubcuenta(rs.getString("idSubcuenta"));
                fachadaBean.setIdPersona(rs.getInt("idPersona"));
                fachadaBean.setNombreConcepto(rs.getString("nombreConcepto"));
                fachadaBean.setPorcentajeRetencion(rs.getDouble("porcentajeRetencion"));
                fachadaBean.setVrBaseRetencion(rs.getDouble("vrBaseRetencion"));
                fachadaBean.setEstado(rs.getInt("estado"));
                fachadaBean.setIdSeq(rs.getInt("idSeq"));
                fachadaBean.setIdTipoOrdenAlcance(rs.getInt("idTipoOrdenAlcance"));

                contenedor.add(fachadaBean);
            }
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

    public FachadaContableRetencionBean listaFCH() {
        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();

        FachadaContableRetencionBean fachadaBean = new FachadaContableRetencionBean();

        Connection connection = null;

        String selectStr = " SELECT idConcepto                                 "
                + ",idSubcuenta                              "
                + ",idPersona                                "
                + ",nombreConcepto                           "
                + ",porcentajeRetencion                      "
                + ",vrBaseRetencion                          "
                + ",estado                                   "
                + ",idSeq                                    "
                + ",idTipoOrdenAlcance                 "
                + "FROM tblContableRetencion                 "
                + "WHERE tblContableRetencion.idConcepto =  "
                + getIdConcepto() + "                        "
                + " AND tblContableRetencion.idSubcuenta  = '"
                + getIdSubcuenta() + "'                      "
                + " AND tblContableRetencion.idPersona    =  "
                + getIdPersona();

        PreparedStatement selectStatement = null;
        try {
            connection = this.jdbcAccess.getConnection();

            selectStatement = connection.prepareStatement(selectStr);

            ResultSet rs = selectStatement.executeQuery();
            if (rs.next()) {

                fachadaBean.setIdConcepto(rs.getInt("idConcepto"));
                fachadaBean.setIdSubcuenta(rs.getString("idSubcuenta"));
                fachadaBean.setIdPersona(rs.getInt("idPersona"));
                fachadaBean.setNombreConcepto(rs.getString("nombreConcepto"));
                fachadaBean.setPorcentajeRetencion(rs.getDouble("porcentajeRetencion"));
                fachadaBean.setVrBaseRetencion(rs.getDouble("vrBaseRetencion"));
                fachadaBean.setEstado(rs.getInt("estado"));
                fachadaBean.setIdSeq(rs.getInt("idSeq"));
                fachadaBean.setIdTipoOrdenAlcance(rs.getInt("idTipoOrdenAlcance"));
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
            return fachadaBean;

        }
    }

    public boolean actualiza() {
        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okActualizarDctoOrdenFactura = false;

        Vector contenedor = new Vector();
        Connection connection = null;

        String selectStr
                = " UPDATE tblContableRetencion                 "
                + "SET nombreConcepto =                    '"
                + getNombreConcepto() + "                    "
                + "'      ,porcentajeRetencion =             "
                + getPorcentajeRetencion() + "               "
                + "       ,vrBaseRetencion =                 "
                + getVrBaseRetencion() + "                   "
                + "       ,estado =                          "
                + getEstado() + "                            "
                + "       ,idTipoOrdenAlcance =              "
                + getIdTipoOrdenAlcance() + "                "
                + " WHERE tblContableRetencion.idConcepto  = "
                + getIdConcepto() + "                        "
                + " AND   tblContableRetencion.idSubcuenta ='"
                + getIdSubcuenta() + "'                      "
                + " AND   tblContableRetencion.idPersona   = "
                + getIdPersona();

        PreparedStatement selectStatement = null;

        try {
            connection = this.jdbcAccess.getConnection();

            selectStatement = connection.prepareStatement(selectStr);

            selectStatement.execute();
            okActualizarDctoOrdenFactura = true;

            this.jdbcAccess.cleanup(connection, selectStatement, null);
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
            return okActualizarDctoOrdenFactura;
        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {
            // Cierra el connection
            jdbcAccess.cleanup(connection, selectStatement, null);
            return okActualizarDctoOrdenFactura;
        }
    }
}
