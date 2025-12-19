package com.solucionesweb.losbeans.colaboraciones;

// Importa la clase helper JDBCAccess que contiene los accesos a la base de datos
import com.solucionesweb.lasayudas.JDBCAccess;

// Importa el bean de utilidades
import com.solucionesweb.losbeans.utilidades.IConstantes;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaColaboraAgendaLogVisitaBean;

// Importa los paquetes de java
import java.sql.*;
import java.io.*;
import java.util.*;
import java.io.IOException.*;

// Importa los paquetes de javax
import javax.naming.*;

public class ColaboraAgendaLogVisitaBean
        extends FachadaColaboraAgendaLogVisitaBean
        implements Serializable, IConstantes {

    // Nombre JNDI del Data Source que esta clase requiere
    private static final String DATA_SOURCE_NAME = "CommerceAccess";

    // Clase helper usada para acceder a la base de datos
    private JDBCAccess jdbcAccess;

    // Metodo constructor por defecto sin parametros
    public ColaboraAgendaLogVisitaBean() {

        // Al instanciar el Bean establece la conexion a la base de datos
        jdbcAccess = new JDBCAccess(DATA_SOURCE_NAME);
    }

    // Metodo listaTotalClienteNuevoLocal MsAccess
    public Vector listaTotalClienteNuevoLocal() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        boolean validaOk = false;
        Connection connection = null;

        String selectStr =
                "SELECT ctrlUsuarios.nombreUsuario,                           " +
                "       COUNT(*) AS totalNuevo                                " +
                "FROM tblAgendaLogSoportes,                                   " +
                "     ctrlUsuarios,                                           " +
                "     (SELECT  tblAgendaLogVisitas.idLog,                     " +
                "              tblAgendaLogVisitas.idUsuario                  " +
                "      FROM tblAgendaLogVisitas                               " +
                "      INNER JOIN ctrlUsuarios                                " +
                "      ON  tblAgendaLogVisitas.IDUSUARIO        =             " +
                "                  ctrlUsuarios.IDUSUARIO                     " +
                "      WHERE ctrlUsuarios.idLocal               =" + getIdLocal() + " " +
                "      AND tblAgendaLogVisitas.idEstadoVisita   =" + getIdEstadoVisita() + " " +
                "      AND FORMAT(tblAgendaLogVisitas.fechaVisita,'yyyy/mm/dd')              " +
                "           BETWEEN '" + getFechaInicial() + "' AND '" + getFechaFinal() + "' " +
                "      GROUP BY  tblAgendaLogVisitas.idLog,                   " +
                "                tblAgendaLogVisitas.idUsuario ) AS tmpLog    " +
                "WHERE tblAgendaLogSoportes.idLog = tmpLog.idLog              " +
                "AND   ctrlUsuarios.idUsuario     = tmpLog.idUsuario          " +
                "GROUP BY  ctrlUsuarios.nombreUsuario                         " +
                "ORDER BY ctrlUsuarios.nombreUsuario                          ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaColaboraAgendaLogVisitaBean fachadaBean;

            while (rs.next()) {

                fachadaBean = new FachadaColaboraAgendaLogVisitaBean();

                fachadaBean.setNombreUsuario(rs.getString("nombreUsuario"));
                fachadaBean.setTotalNuevo(rs.getInt("totalNuevo"));

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

            // Cierra la conexion
            return contenedor;

        }
    }

    // Metodo listaSuspendida
    public Vector listaSuspendida(int xIdTipoOrden,
                                  int xIdTipoTercero) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        String selectStr =
                "SELECT tblAgendaLogVisitas.IDLOG,           " +
                "       tblAgendaLogVisitas.idCliente,       " +
                "       tblAgendaLogVisitas.IDUSUARIO,       " +
                "       tblAgendaLogVisitas.FECHAVISITA,     " +
                "       tblAgendaLogVisitas.IDESTADOVISITA,  " +
                "       tblAgendaLogVisitas.ESTADO,          " +
                "       tblTerceros.nombreTercero,           " +
                "       tblTerceros.direccionTercero,        " +
                "       tblTerceros.telefonoFijo,            " +
                "       tblTerceros.nombreEmpresa,           " +
                "       tblTerceros.ciudadTercero,           " +
                "       ctrlUsuarios.NOMBREUSUARIO           " +
                "FROM   tblTerceros                          " +
                "INNER JOIN tblAgendaLogVisitas              " +
                "ON tblTerceros.idCliente         =          " +
                "            tblAgendaLogVisitas.idCliente   " +
                "INNER JOIN ctrlUsuarios                     " +
                "ON tblAgendaLogVisitas.IDUSUARIO =          " +
                "                   ctrlUsuarios.IDUSUARIO   " +
                "WHERE tblAgendaLogVisitas.IDLOG IN (        " +
                "   SELECT tblAgendaLogVisitas.IDLOG         " +
                "   FROM   tblAgendaLogVisitas               " +
                "   INNER JOIN tblDctosOrdenes               " +
                "   ON tblAgendaLogVisitas.IDLOG  =          " +
                "                    tblDctosOrdenes.IDLOG   " +
                "   AND tblAgendaLogVisitas.fechaVisita =   '" +
                getFechaVisitaSqlServer() + "'               " +
                "   AND   tblDctosOrdenes.idLocal =          " +
                getIdLocal() + "                             " +
                "   AND   tblDctosOrdenes.idTipoOrden        " +
                "                                 =          " +
                xIdTipoOrden + "                             " +
                "AND   tblAgendaLogVisitas.ESTADO =          " +
                getEstado() + "  )                           " +
                "AND tblTerceros.idTipoTercero    =          " +
                xIdTipoTercero;

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaColaboraAgendaLogVisitaBean fachadaBean;

            while (rs.next()) {

                fachadaBean = new FachadaColaboraAgendaLogVisitaBean();

                fachadaBean.setIdLog(rs.getInt("IDLOG"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setIdUsuario(rs.getDouble("IDUSUARIO"));
                fachadaBean.setFechaVisita(rs.getString("FECHAVISITA"));
                fachadaBean.setIdEstadoVisita(rs.getInt("idEstadoVisita"));
                fachadaBean.setEstado(rs.getInt("estado"));
                fachadaBean.setNombreTercero(rs.getString("nombreTercero"));
                fachadaBean.setDireccionTercero(
                                              rs.getString("direccionTercero"));
                fachadaBean.setTelefonoFijo(rs.getString("telefonoFijo"));
                fachadaBean.setNombreEmpresa(rs.getString("nombreEmpresa"));
                fachadaBean.setCiudadTercero(rs.getString("ciudadTercero"));
                fachadaBean.setNombreUsuario(rs.getString("nombreUsuario"));

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

            // Cierra la conexion
            return contenedor;

        }
    }

    // Metodo listaOTSuspendida
    public Vector listaOTSuspendida(int xIdTipoOrden,
                                  int xIdTipoTercero) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        String selectStr =
              "   SELECT tblAgendaLogVisitas.IDLOG,                " +
              "          tblAgendaLogVisitas.idCliente,            " +
              "          tblAgendaLogVisitas.IDUSUARIO,            " +
              "          tblAgendaLogVisitas.FECHAVISITA,          " +
              "          tblAgendaLogVisitas.IDESTADOVISITA,       " +
              "          tblAgendaLogVisitas.ESTADO,               " +
              "          tblTerceros.nombreTercero,                " +
              "          tblTerceros.direccionTercero,             " +
              "          tblTerceros.telefonoFijo,                 " +
              "          tblTerceros.nombreEmpresa,                " +
              "          tblTerceros.ciudadTercero,                " +
              "          ctrlUsuarios.NOMBREUSUARIO,               " +
              "          tmpDET.idOrden,                           " +
              "          tmpDET.item,                              " +
              "          tmpDET.cantidad,                          " +
              "          tmpDET.referenciaCliente,                 " +
              "          tmpDET.fechaEntrega,                      " +
              "          tmpDET.numeroOrden,                       " +
              "          tmpDET.idFicha                            " +
              "   FROM   tblTerceros                               " +
              "   INNER JOIN tblAgendaLogVisitas                   " +
              "   ON tblTerceros.idCliente         =               " +
              "               tblAgendaLogVisitas.idCliente        " +
              "   INNER JOIN ctrlUsuarios                          " +
              "   ON tblAgendaLogVisitas.IDUSUARIO =               " +
              "                      ctrlUsuarios.IDUSUARIO        " +
              "   INNER JOIN                                       " +
              "  (SELECT tblDctosOrdenesDetalle.idLocal,           " +
              "          tblDctosOrdenesDetalle.idTipoOrden,       " +
              "          tblDctosOrdenesDetalle.idOrden,           " +
              "          tblDctosOrdenesDetalle.item,              " +
              "          tblDctosOrdenesDetalle.cantidad,          " +
              "          tblDctosOrdenesDetalle.referenciaCliente, " +
              "          tblDctosOrdenesDetalle.fechaEntrega,      " +
              "          tblDctosOrdenes.idLog,                    " +
              "          tblDctosOrdenes.numeroOrden,              " +
              "          tblDctosOrdenes.idFicha                   " +
              "    FROM       tblDctosOrdenes                      " +
              "    INNER JOIN tblDctosOrdenesDetalle               " +
              "    ON tblDctosOrdenes.IDLOCAL      =               " +
              "                    tblDctosOrdenesDetalle.IDLOCAL  " +
              "    AND tblDctosOrdenes.IDTIPOORDEN =               " +
              "                tblDctosOrdenesDetalle.IDTIPOORDEN  " +
              "    AND tblDctosOrdenes.IDORDEN     =               " +
              "                    tblDctosOrdenesDetalle.IDORDEN) " +
              "                                          AS tmpDET " +
              "   ON tmpDET.idLog = tblAgendaLogVisitas.idLog      " +
              "   WHERE tblAgendaLogVisitas.IDLOG IN (             " +
              "      SELECT tblAgendaLogVisitas.IDLOG              " +
              "      FROM   tblAgendaLogVisitas                    " +
              "      INNER JOIN tblDctosOrdenes                    " +
              "      ON tblAgendaLogVisitas.IDLOG     =            " +
              "                       tblDctosOrdenes.IDLOG        " +
              "   AND   tblDctosOrdenes.idLocal       =            " +
              getIdLocal() + "                                     " +
              "   AND   tblDctosOrdenes.idTipoOrden   =            " +
              xIdTipoOrden + "                                     " +
              "   AND   tblAgendaLogVisitas.estado    =            " +
              getEstado() + "  )                                   " +
              "   AND tblTerceros.idTipoTercero       =            " +
              xIdTipoTercero + "                                   " +
              "ORDER BY tmpDET.numeroOrden,                        " +
              "         tmpDET.fechaEntrega                        " ;

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaColaboraAgendaLogVisitaBean fachadaBean;

            //
            while (rs.next()) {

                //
                fachadaBean = new FachadaColaboraAgendaLogVisitaBean();

                //
                fachadaBean.setIdLog(rs.getInt("IDLOG"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setIdUsuario(rs.getDouble("IDUSUARIO"));
                fachadaBean.setFechaVisita(rs.getString("FECHAVISITA"));
                fachadaBean.setIdEstadoVisita(rs.getInt("idEstadoVisita"));
                fachadaBean.setEstado(rs.getInt("estado"));
                fachadaBean.setNombreTercero(rs.getString("nombreTercero"));
                fachadaBean.setDireccionTercero(
                                              rs.getString("direccionTercero"));
                fachadaBean.setTelefonoFijo(rs.getString("telefonoFijo"));
                fachadaBean.setNombreEmpresa(rs.getString("nombreEmpresa"));
                fachadaBean.setCiudadTercero(rs.getString("ciudadTercero"));
                fachadaBean.setNombreUsuario(rs.getString("nombreUsuario"));
                fachadaBean.setIdOrden(rs.getInt("idOrden"));
                fachadaBean.setItem(rs.getInt("item"));
                fachadaBean.setCantidad(rs.getDouble("cantidad"));
                fachadaBean.setReferenciaCliente(
                                             rs.getString("referenciaCliente"));
                fachadaBean.setFechaEntrega(rs.getString("fechaEntrega"));                
                fachadaBean.setNumeroOrden(rs.getInt("numeroOrden"));
                fachadaBean.setIdFicha(rs.getInt("idFicha"));


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

            // Cierra la conexion
            return contenedor;

        }
    }

    // Metodo listaCotizacion
    public Vector listaCotizacion(int xIdTipoOrden) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        boolean validaOk = false;
        Connection connection = null;

        String selectStr =
                "SELECT tblAgendaLogVisitas.IDLOG,                    " +
                "       tblAgendaLogVisitas.idCliente,                " +
                "       tblAgendaLogVisitas.IDUSUARIO,                " +
                "       tblAgendaLogVisitas.FECHAVISITA,              " +
                "       tblAgendaLogVisitas.IDESTADOVISITA,           " +
                "       tblAgendaLogVisitas.ESTADO,                   " +
                "       tblTerceros.nombreTercero,                    " +
                "       tblTerceros.direccionTercero,                 " +
                "       tblTerceros.telefonoFijo,                     " +
                "       tblTerceros.nombreEmpresa,                    " +
                "       tblTerceros.ciudadTercero,                    " +
                "       ctrlUsuarios.NOMBREUSUARIO,                   " +
                "       ( SELECT tblDctosOrdenes.idOrden              " +
                "         FROM   tblDctosOrdenes                      " +
                "         WHERE  tblAgendaLogVisitas.IDLOG     =      " +
                "                          tblDctosOrdenes.IDLOG)     " +
                "                                        AS idOrden   " +
                "FROM   tblTerceros                                   " +
                "INNER JOIN tblAgendaLogVisitas                       " +
                "ON tblTerceros.idCliente         =                   " +
                "                    tblAgendaLogVisitas.idCliente    " +
                "INNER JOIN ctrlUsuarios                              " +
                "ON tblAgendaLogVisitas.IDUSUARIO =                   " +
                "                            ctrlUsuarios.IDUSUARIO   " +
                "WHERE tblAgendaLogVisitas.IDLOG IN (                 " +
                "SELECT tblAgendaLogVisitas.IDLOG                     " +
                "FROM   tblAgendaLogVisitas                           " +
                "INNER JOIN tblDctosOrdenes                           " +
                "ON tblAgendaLogVisitas.IDLOG        =                " +
                "                    tblDctosOrdenes.IDLOG            " +
                "AND   tblDctosOrdenes.idLocal       =                " +
                getIdLocal() + "                                      " +
                "AND   tblDctosOrdenes.idTipoOrden   =                " +
                xIdTipoOrden + "                                      " +
                "AND   tblAgendaLogVisitas.ESTADO    =                " +
                getEstado() + "  )                                    " +
                "AND tblTerceros.idTipoTercero       = 1              " +
                "ORDER BY 4,2                                         " ;

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaColaboraAgendaLogVisitaBean fachadaBean;

            while (rs.next()) {

                fachadaBean = new FachadaColaboraAgendaLogVisitaBean();

                fachadaBean.setIdLog(rs.getInt("IDLOG"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setIdUsuario(rs.getDouble("IDUSUARIO"));
                fachadaBean.setFechaVisita(rs.getString("FECHAVISITA"));
                fachadaBean.setIdEstadoVisita(rs.getInt("idEstadoVisita"));
                fachadaBean.setEstado(rs.getInt("estado"));
                fachadaBean.setNombreTercero(rs.getString("nombreTercero"));
                fachadaBean.setDireccionTercero(
                                              rs.getString("direccionTercero"));
                fachadaBean.setTelefonoFijo(rs.getString("telefonoFijo"));
                fachadaBean.setNombreEmpresa(rs.getString("nombreEmpresa"));
                fachadaBean.setCiudadTercero(rs.getString("ciudadTercero"));
                fachadaBean.setNombreUsuario(rs.getString("nombreUsuario"));
                fachadaBean.setIdOrden(rs.getInt("idOrden"));

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

            // Cierra la conexion
            return contenedor;

        }
    }

    // Metodo listaFechasClienteNuevoLocal MsAccess
    public Vector listaFechasClienteNuevoLocal() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        boolean validaOk = false;
        Connection connection = null;

        String selectStr =
                "SELECT ctrlUsuarios.idLocal AS idLocal ,                      " +
                "       FORMAT(tblAgendaLogVisitas.fechaVisita,'yyyy/mm/dd')   " +
                "                                       AS fechaVisita         " +
                "FROM tblAgendaLogVisitas                                      " +
                "INNER JOIN ctrlUsuarios                                       " +
                "ON tblAgendaLogVisitas.IDUSUARIO =                            " +
                "                          ctrlUsuarios.IDUSUARIO              " +
                "WHERE ctrlUsuarios.idLocal             =" + getIdLocal() + "  " +
                "AND tblAgendaLogVisitas.idEstadoVisita =" + getIdEstadoVisita() + "  " +
                "AND FORMAT(tblAgendaLogVisitas.fechaVisita,'yyyy/mm/dd')              " +
                "    BETWEEN '" + getFechaInicial() + "' AND '" + getFechaFinal() + "' " +
                "GROUP BY ctrlUsuarios.idLocal,                                " +
                "         FORMAT(tblAgendaLogVisitas.fechaVisita,'yyyy/mm/dd') " +
                "ORDER BY FORMAT(tblAgendaLogVisitas.fechaVisita,'yyyy/mm/dd') ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaColaboraAgendaLogVisitaBean fachadaBean;

            while (rs.next()) {

                fachadaBean = new FachadaColaboraAgendaLogVisitaBean();

                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setFechaVisita(rs.getString("fechaVisita").substring(0, 10));

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

            // Cierra la conexion
            return contenedor;

        }
    }

    // Metodo listaClienteNuevoLocal MsAccess
    public Vector listaClienteNuevoLocal() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        boolean validaOk = false;
        Connection connection = null;

        String selectStr =
                "SELECT tblAgendaLogSoportes.IDLOG,                " +
                "       tblAgendaLogSoportes.IDCLIENTE,            " +
                "       tblAgendaLogSoportes.NOMBRECLIENTE,        " +
                "       tblAgendaLogSoportes.TELEFONO,             " +
                "       ctrlUsuarios.idUsuario,                    " +
                "       ctrlUsuarios.nombreUsuario                 " +
                "FROM tblAgendaLogSoportes,                        " +
                "     ctrlUsuarios,                                " +
                "     (SELECT  tblAgendaLogVisitas.idLog,          " +
                "              tblAgendaLogVisitas.idUsuario       " +
                "      FROM tblAgendaLogVisitas                    " +
                "      INNER JOIN ctrlUsuarios                     " +
                "      ON tblAgendaLogVisitas.IDUSUARIO =          " +
                "                          ctrlUsuarios.IDUSUARIO  " +
                "      WHERE ctrlUsuarios.idLocal               =  " + 
                getIdLocal() + "                                   " +
                "      AND tblAgendaLogVisitas.idEstadoVisita   =  " + 
                getIdEstadoVisita() + "                            " +
                "      AND FORMAT(tblAgendaLogVisitas.fechaVisita,'yyyy/mm/dd') = '" + 
                getFechaVisita() + "'                              " +
                "      GROUP BY  tblAgendaLogVisitas.idLog,        " +
                "                tblAgendaLogVisitas.idUsuario)    " +
                "                                        AS tmpLog " +
                "WHERE tblAgendaLogSoportes.idLog = tmpLog.idLog   " +
                "AND   tmpLog.idUsuario = ctrlUsuarios.idUsuario   " +
                "ORDER BY ctrlUsuarios.nombreUsuario,              " +
                "         tblAgendaLogSoportes.nombreCliente       ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);


            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaColaboraAgendaLogVisitaBean fachadaBean;

            while (rs.next()) {

                fachadaBean = new FachadaColaboraAgendaLogVisitaBean();

                fachadaBean.setIdLocal(rs.getInt("idLog"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setNombreCliente(rs.getString("nombreCliente").toUpperCase());
                fachadaBean.setTelefono(rs.getString("TELEFONO"));
                fachadaBean.setIdUsuario(rs.getDouble("idUsuario"));
                fachadaBean.setNombreUsuario(rs.getString("nombreUsuario").toUpperCase());
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

            // Cierra la conexion
            return contenedor;

        }
    }

    // Metodo listaVisitaVendedor
    public Vector listaVisitaVendedor() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        boolean validaOk = false;
        Connection connection = null;

        String selectStr =
                "SELECT tmpLog.idCliente,                        " +
                "       tblTerceros.nombreTercero                " +
                "                          AS  nombrecliente,    " +
                "       tmpLog.horaVisita,                       " +
                "       tmpLog.IDESTADOVISITA,                   " +
                "       tmpLog.NOMBREESTADO                      " +
                "FROM tblTerceros,                               " +
                "( SELECT tblAgendaLogVisitas.idCliente,         " +
                "         tblAgendaLogVisitas.fechaVisita        " +
                "                                 AS horaVisita, " +
                "         tblAgendaLogVisitas.IDESTADOVISITA,    " +
                "         tblAgendaEstadosVisitas.NOMBREESTADO,  " +
                "         tblAgendaLogVisitas.idLog              " +
                "  FROM tblAgendaLogVisitas                      " +
                "  INNER JOIN tblAgendaEstadosVisitas            " +
                "  ON  tblAgendaLogVisitas.IDESTADOVISITA =      " +
                "      tblAgendaEstadosVisitas.IDESTADOVISITA    " +
                "  WHERE tblAgendaLogVisitas.IDUSUARIO        =  " +
                getIdUsuario() + "                               " +
                "  AND   tblAgendaLogVisitas.fechaVisita      = '" +
                getFechaVisitaSqlServer() + "'                   " +
                "                                  ) AS tmpLog   " +
                "WHERE tblTerceros.idCliente      =              " +
                "                       tmpLog.idCliente         " +
                "GROUP BY tmpLog.IdCliente,                      " +
                "         tblTerceros.nombreTercero,             " +
                "         tmpLog.horaVisita,                     " +
                "         tmpLog.IDESTADOVISITA,                 " +
                "         tmpLog.NOMBREESTADO                    " +
                "ORDER BY 3,2, 4                                 ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaColaboraAgendaLogVisitaBean fachadaBean;

            while (rs.next()) {

                fachadaBean = new FachadaColaboraAgendaLogVisitaBean();

                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setNombreCliente(rs.getString("nombrecliente"));
                fachadaBean.setHoraVisita(rs.getString("horaVisita"));
                fachadaBean.setIdEstadoVisita(rs.getInt("idEstadoVisita"));
                fachadaBean.setNombreEstado(rs.getString("nombreEstado"));

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

            // Cierra la conexion
            return contenedor;

        }
    }

    // Metodo listaTotalVisitaVendedor MsAccess
    public Vector listaTotalVisitaVendedor() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        boolean validaOk = false;
        Connection connection = null;

        String selectStr =
                "SELECT COUNT(*) AS idEstadoVisita,            " +
                "       tblAgendaEstadosVisitas.nombreEstado   " +
                "FROM tblAgendaLogVisitas                      " +
                "INNER JOIN tblAgendaEstadosVisitas            " +
                "ON tblAgendaLogVisitas.IDESTADOVISITA =       " +
                "   tblAgendaEstadosVisitas.IDESTADOVISITA     " +
                "WHERE tblAgendaLogVisitas.idUsuario      =    " +
                getIdUsuario() + "                             " +
                "AND FORMAT(tblAgendaLogVisitas.fechaVisita,'yyyy/mm/dd')  " +
                "                                              " +
                "    BETWEEN '" + getFechaInicial() + "' AND  '" +
                getFechaFinal() + "'                           " +
                "GROUP BY tblAgendaEstadosVisitas.nombreEstado ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaColaboraAgendaLogVisitaBean fachadaBean;

            while (rs.next()) {

                fachadaBean = new FachadaColaboraAgendaLogVisitaBean();

                fachadaBean.setIdEstadoVisita(rs.getInt("idEstadoVisita"));
                fachadaBean.setNombreEstado(rs.getString("nombreEstado"));

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

            // Cierra la conexion
            return contenedor;

        }
    }
}