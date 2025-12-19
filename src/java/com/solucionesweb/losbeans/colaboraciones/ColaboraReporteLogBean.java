package com.solucionesweb.losbeans.colaboraciones;

// Importa la clase helper JDBCAccess que contiene los accesos a la base de datos
import com.solucionesweb.lasayudas.JDBCAccess;

// Importa el bean de utilidades
import com.solucionesweb.losbeans.utilidades.IConstantes;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaColaboraReporteLogBean;

// Importa los paquetes de java
import java.sql.*;
import java.io.*;
import java.io.IOException.*;

//import java.util.Date;
import java.util.Vector;
import java.io.IOException.*;

// Importa los paquetes de javax
import javax.naming.*;

public class ColaboraReporteLogBean extends FachadaColaboraReporteLogBean
        implements Serializable, IConstantes {

    // Nombre JNDI del Data Source que esta clase requiere
    private static final String DATA_SOURCE_NAME = "CommerceAccess";

    // Clase helper usada para acceder a la base de datos
    private JDBCAccess jdbcAccess;

    // Metodo constructor por defecto sin parametros
    public ColaboraReporteLogBean() {

        // Al instanciar el Bean establece la conexion a la base de datos
        jdbcAccess = new JDBCAccess(DATA_SOURCE_NAME);
    }

    // Metodo listaFechasLogLocal MsAccess
    public Vector listaFechasLogLocal() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        boolean validaOk = false;
        Connection connection = null;

        String selectStr =
                "SELECT ctrlUsuarios.idLocal,                                          " +
                "       FORMAT(tblAgendaLogVisitas.FECHAVISITA,'yyyy/mm/dd')           " +
                "                                              AS FECHAORDEN           " +
                "FROM ctrlUsuarios                                                     " +
                "INNER JOIN tblAgendaLogVisitas                                        " +
                "ON ctrlUsuarios.IDUSUARIO =                                           " +
                "      tblAgendaLogVisitas.idUsuario                                   " +
                "WHERE ctrlUsuarios.idLocal = " + getIdLocal() + "                     " +
                "AND FORMAT(tblAgendaLogVisitas.fechaVisita,'yyyy/mm/dd')              " +
                "    BETWEEN '" + getFechaInicial() + "' AND '" + getFechaFinal() + "' " +
                "GROUP BY ctrlUsuarios.idLocal,                                        " +
                "         FORMAT(tblAgendaLogVisitas.fechaVisita,'yyyy/mm/dd')         " +
                "ORDER BY FORMAT(tblAgendaLogVisitas.fechaVisita,'yyyy/mm/dd')         ";


        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaColaboraReporteLogBean fachadaBean;

            while (rs.next()) {

                fachadaBean = new FachadaColaboraReporteLogBean();

                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setFechaOrden(rs.getString("fechaOrden").substring(0, 10));

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

    // Metodo listaFechasRuta MsAccess
    public Vector listaFechasRuta() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        boolean validaOk = false;
        Connection connection = null;

        String selectStr =
                "SELECT tblAgendaLogVisitas.FECHAVISITA AS FECHAORDEN           " +
                "FROM (ctrlUsuariosRutas INNER JOIN tblTerceros                 " +
                "ON ctrlUsuariosRutas.idRuta = tblTerceros.idRuta)              " +
                "INNER JOIN tblAgendaLogVisitas ON tblTerceros.idCliente =      " +
                "                                tblAgendaLogVisitas.idCliente  " +
                "WHERE ctrlUsuariosRutas.idUsuario = " + getIdUsuario() + "     " +
                "AND tblAgendaLogVisitas.fechaVisita                            " +
                " BETWEEN '" + getFechaInicialSqlServer() + "' AND             '" +
                getFechaFinalSqlServer() + "'                                   " +
                "GROUP BY tblAgendaLogVisitas.FECHAVISITA                       " +
                "ORDER BY tblAgendaLogVisitas.FECHAVISITA                       ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaColaboraReporteLogBean fachadaBean;

            while (rs.next()) {

                fachadaBean = new FachadaColaboraReporteLogBean();

                fachadaBean.setIdLocal(1);
                fachadaBean.setFechaOrden(rs.getString("fechaOrden").substring(0, 10));

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

    // Metodo listaVisitaActualRuta MsAccess
    public Vector listaVisitaActualRuta() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        boolean validaOk = false;
        Connection connection = null;

        String selectStr =
                "SELECT " + getIdLocal() + " AS idLocal,                     " +
                "       tblAgendaLogVisitas.IDUSUARIO,                       " +
                "       ctrlUsuarios.NOMBREUSUARIO,                          " +
                "       tblAgendaLogVisitas.IDCLIENTE,                       " +
                "       tblTerceros.nombreTercero AS nombrecliente,          " +
                "       tblAgendaLogVisitas.IDESTADOVISITA,                  " +
                "       tblAgendaEstadosVisitas.NOMBREESTADO,                " +
                "       tblAgendaLogVisitas.FECHAVISITA       AS horaVisita, " +
                "       tblAgendaLogVisitas.IDLOG                            " +
                "FROM tblAgendaLogVisitas,                                   " +
                "     ctrlUsuarios,                                          " +
                "     tblAgendaEstadosVisitas,                               " +
                "     tblTerceros,                                           " +
                "     ctrlUsuariosRutas                                      " +
                "WHERE tblAgendaLogVisitas.IDUSUARIO          =              " +
                "                                     ctrlUsuarios.IDUSUARIO " +
                "AND   tblAgendaEstadosVisitas.IDESTADOVISITA =              " +
                "                         tblAgendaLogVisitas.IDESTADOVISITA " +
                "AND   tblAgendaLogVisitas.IDCLIENTE          =              " +
                "                                      tblTerceros.idCliente " +
                "AND   ctrlUsuariosRutas.idRuta               =              " +
                "                                      tblTerceros.idRuta    " +
                "AND   ctrlUsuariosRutas.idUsuario  = " + getIdUsuario() + " " +
                "AND   tblAgendaLogVisitas.IDLOG                             " +
                "      IN (SELECT MAX(tblAgendaLogVisitas.IDLOG) AS idLog    " +
                "          FROM tblAgendaLogVisitas                          " +
                "          INNER JOIN ctrlUsuarios                           " +
                "          ON tblAgendaLogVisitas.IDUSUARIO   =              " +
                "                                     ctrlUsuarios.idUsuario " +
                "          WHERE tblAgendaLogVisitas.FECHAVISITA =          '" +
                getFechaVisitaSqlServer() + "'                               " +
                " AND tblAgendaLogVisitas.IDESTADOVISITA                   = " +
                getIdEstadoVisita() + "                                      " +
                "          GROUP BY  tblAgendaLogVisitas.IDUSUARIO )         ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaColaboraReporteLogBean fachadaBean;

            while (rs.next()) {

                fachadaBean = new FachadaColaboraReporteLogBean();

                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdUsuario(rs.getDouble("idUsuario"));
                fachadaBean.setNombreUsuario(rs.getString("nombreUsuario"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setNombreCliente(rs.getString("nombreCliente"));
                fachadaBean.setIdEstadoVisita(rs.getInt("idEstadoVisita"));
                fachadaBean.setNombreEstado(rs.getString("nombreEstado"));
                fachadaBean.setHoraVisita(rs.getString("horaVisita"));
                fachadaBean.setIdLog(rs.getInt("idLog"));

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

    // Metodo listaFechasVendedor
    public Vector listaFechasVendedor() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        boolean validaOk = false;
        Connection connection = null;

        String selectStr =
                "SELECT tblAgendaLogVisitas.idUsuario,                 " +
                "       tblAgendaLogVisitas.fechaVisita                " +
                "FROM tblAgendaLogVisitas                              " +
                "WHERE tblAgendaLogVisitas.idUsuario      =            " +
                getIdUsuario() + "                                     " +
                "AND tblAgendaLogVisitas.fechaVisita                   " +
                "    BETWEEN '" + getFechaInicialSqlServer() + "' AND '" +
                getFechaFinalSqlServer() + "'                          " +
                "GROUP BY tblAgendaLogVisitas.idUsuario,               " +
                "         tblAgendaLogVisitas.fechaVisita              " +
                "ORDER BY tblAgendaLogVisitas.fechaVisita              ";
 
        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaColaboraReporteLogBean fachadaBean;

            while (rs.next()) {

                fachadaBean = new FachadaColaboraReporteLogBean();

                fachadaBean.setIdUsuario(rs.getInt("idUsuario"));
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

    // Metodo listaLogFechaCO MsAccess
    public Vector listaLogFechaCO() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        boolean validaOk = false;
        Connection connection = null;

        String selectStr =
                "SELECT tmpTotales.idLocal,                                   " +
                "       tmpTotales.idUsuario,                                 " +
                "       ctrlUsuarios.nombreUsuario,                           " +
                "       tmpTotales.idCliente,                                 " +
                "       tblTerceros.nombreTercero AS nombreCliente,           " +
                "       tmpTotales.idEstadoVisita,                            " +
                "       tblAgendaEstadosVisitas.nombreEstado,                 " +
                "       tmpTotales.horaVisita,                                " +
                "       tmpTotales.fechaVisita,                               " +
                "       tmpTotales.idLog                                      " +
                "FROM ctrlUsuarios,                                           " +
                "     tblTerceros,                                            " +
                "     tblAgendaEstadosVisitas,                                " +
                "( SELECT ctrlUsuarios.idLocal,                               " +
                "         tblAgendaLogVisitas.IDUSUARIO,                      " +
                "         tblAgendaLogVisitas.idCliente,                      " +
                "         tblAgendaLogVisitas.idEstadoVisita,                 " +
                "         tblAgendaLogVisitas.FECHAVISITA,                    " +
                "         tblAgendaLogVisitas.idLog,                          " +
                "         FORMAT(tblAgendaLogVisitas.FECHAVISITA,'hh:mm:ss')  " +
                "                                              AS horaVisita  " +
                "  FROM tblAgendaLogVisitas                                   " +
                "  INNER JOIN ctrlUsuarios                                    " +
                "  ON tblAgendaLogVisitas.IDUSUARIO =                         " +
                "                          ctrlUsuarios.idUsuario             " +
                "  WHERE ctrlUsuarios.idLocal                                 = " + getIdLocal() + " " +
                "  AND   FORMAT(tblAgendaLogVisitas.FECHAVISITA,'yyyy/mm/dd') ='" + getFechaVisita() + "'" +
                "  GROUP BY ctrlUsuarios.idLocal,                             " +
                "        tblAgendaLogVisitas.IDUSUARIO,                       " +
                "        tblAgendaLogVisitas.idCliente,                       " +
                "        tblAgendaLogVisitas.idEstadoVisita,                  " +
                "        tblAgendaLogVisitas.FECHAVISITA,                     " +
                "        tblAgendaLogVisitas.idLog,                           " +
                "        FORMAT(tblAgendaLogVisitas.FECHAVISITA,'hh:mm:ss')   " +
                "  ORDER BY tblAgendaLogVisitas.IDUSUARIO) AS tmpTotales      " +
                "WHERE ctrlUsuarios.idUsuario = tmpTotales.IDUSUARIO          " +
                "AND   tblAgendaEstadosVisitas.idEstadoVisita =               " +
                "                                tmpTotales.idEstadoVisita    " +
                "AND   tblTerceros.idCliente  =  tmpTotales.idCliente         " +
                "ORDER BY tmpTotales.fechaVisita                              ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaColaboraReporteLogBean fachadaBean;

            while (rs.next()) {

                fachadaBean = new FachadaColaboraReporteLogBean();

                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdUsuario(rs.getDouble("idUsuario"));
                fachadaBean.setNombreUsuario(rs.getString("nombreUsuario"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setNombreCliente(rs.getString("nombreCliente"));
                fachadaBean.setIdEstadoVisita(rs.getInt("idEstadoVisita"));
                fachadaBean.setNombreEstado(rs.getString("nombreEstado"));
                fachadaBean.setHoraVisita(rs.getString("horaVisita"));
                fachadaBean.setIdLog(rs.getInt("idLog"));

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

    // Metodo listaVisitaActualCO MsAccess
    public Vector listaVisitaActualCO() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        boolean validaOk = false;
        Connection connection = null;

        String selectStr =
                "SELECT " + getIdLocal() + " AS idLocal,                     " +
                "       tblAgendaLogVisitas.IDUSUARIO,                       " +
                "       ctrlUsuarios.NOMBREUSUARIO,                          " +
                "       tblAgendaLogVisitas.IDCLIENTE,                       " +
                "       tblTerceros.nombreTercero AS nombrecliente,          " +
                "       tblAgendaLogVisitas.IDESTADOVISITA,                  " +
                "       tblAgendaEstadosVisitas.NOMBREESTADO,                " +
                "       FORMAT(tblAgendaLogVisitas.FECHAVISITA,'hh:mm:ss')   " +
                "                                             AS horaVisita, " +
                "       tblAgendaLogVisitas.IDLOG                            " +
                "FROM tblAgendaLogVisitas,                                   " +
                "     ctrlUsuarios,                                          " +
                "     tblAgendaEstadosVisitas,                               " +
                "     tblTerceros                                            " +
                "WHERE tblAgendaLogVisitas.IDUSUARIO          =              " +
                "                                     ctrlUsuarios.IDUSUARIO " +
                "AND   tblAgendaEstadosVisitas.IDESTADOVISITA =              " +
                "                         tblAgendaLogVisitas.IDESTADOVISITA " +
                "AND   tblAgendaLogVisitas.IDCLIENTE          =              " +
                "                                      tblTerceros.idCliente " +
                "AND   tblAgendaLogVisitas.IDLOG                             " +
                "      IN (SELECT MAX(tblAgendaLogVisitas.IDLOG) AS idLog    " +
                "          FROM tblAgendaLogVisitas                          " +
                "          INNER JOIN ctrlUsuarios                           " +
                "          ON tblAgendaLogVisitas.IDUSUARIO   =              " +
                "                                     ctrlUsuarios.idUsuario " +
                "          WHERE ctrlUsuarios.idLocal                               = " + getIdLocal() + " " +
                "          AND FORMAT(tblAgendaLogVisitas.FECHAVISITA,'yyyy/mm/dd') ='" + getFechaVisita() + "'" +
                "          AND tblAgendaLogVisitas.IDESTADOVISITA                   = " + getIdEstadoVisita() + " " +
                "          GROUP BY  tblAgendaLogVisitas.IDUSUARIO)          ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaColaboraReporteLogBean fachadaBean;

            while (rs.next()) {

                fachadaBean = new FachadaColaboraReporteLogBean();

                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdUsuario(rs.getDouble("idUsuario"));
                fachadaBean.setNombreUsuario(rs.getString("nombreUsuario"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setNombreCliente(rs.getString("nombreCliente"));
                fachadaBean.setIdEstadoVisita(rs.getInt("idEstadoVisita"));
                fachadaBean.setNombreEstado(rs.getString("nombreEstado"));
                fachadaBean.setHoraVisita(rs.getString("horaVisita"));
                fachadaBean.setIdLog(rs.getInt("idLog"));

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

    // Metodo listaTotalListaLogFechaCO MsAccess
    public Vector listaTotalListaLogFechaCO() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        boolean validaOk = false;
        Connection connection = null;

        String selectStr =
                "SELECT tmpTotales.IDUSUARIO,                   " +
                "       ctrlUsuarios.nombreUsuario,             " +
                "       COUNT(*) AS totalOrdenes                " +
                "FROM ctrlUsuarios,                             " +
                "(SELECT ctrlUsuarios.idLocal,                  " +
                "        tblAgendaLogVisitas.IDUSUARIO,         " +
                "        tblAgendaLogVisitas.idCliente          " +
                " FROM tblAgendaEstadosVisitas                  " +
                " INNER JOIN (ctrlUsuarios                      " +
                " INNER JOIN tblAgendaLogVisitas                " +
                " ON ctrlUsuarios.idUsuario                 =   " +
                "                tblAgendaLogVisitas.IDUSUARIO) " +
                " ON tblAgendaEstadosVisitas.IDESTADOVISITA =   " +
                "            tblAgendaLogVisitas.IDESTADOVISITA " +
                " WHERE ctrlUsuarios.idLocal = " + getIdLocal() + "      " +
                " AND FORMAT(tblAgendaLogVisitas.FECHAVISITA,'yyyy/mm/dd') " +
                "           BETWEEN '" + getFechaInicial() + "' AND '" + getFechaFinal() + "' " +
                "GROUP BY ctrlUsuarios.idLocal,                 " +
                "         tblAgendaLogVisitas.idUsuario,        " +
                "         tblAgendaLogVisitas.idCliente)        " +
                "                                 AS tmpTotales " +
                "WHERE ctrlUsuarios.idUsuario =                 " +
                "                          tmpTotales.IDUSUARIO " +
                "GROUP BY tmpTotales.IDUSUARIO,                 " +
                "         ctrlUsuarios.nombreUsuario            ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaColaboraReporteLogBean fachadaBean;

            while (rs.next()) {

                fachadaBean = new FachadaColaboraReporteLogBean();

                fachadaBean.setIdUsuario(rs.getDouble("idUsuario"));
                fachadaBean.setNombreUsuario(rs.getString("nombreUsuario"));
                fachadaBean.setTotalOrdenes(rs.getInt("totalOrdenes"));

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

    // Metodo listaTotalRutaLogFechaCO MsAccess
    public Vector listaTotalRutaLogFechaCO() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        boolean validaOk = false;
        Connection connection = null;

        String selectStr =
                "SELECT tmpTotales.IDUSUARIO,                   " +
                "       ctrlUsuarios.nombreUsuario,             " +
                "       COUNT(*) AS totalOrdenes                " +
                "FROM ctrlUsuarios,                             " +
                "(SELECT tblAgendaLogVisitas.IDUSUARIO,         " +
                "       tblAgendaLogVisitas.idCliente           " +
                "FROM (tblAgendaEstadosVisitas                  " +
                "INNER JOIN tblAgendaLogVisitas                 " +
                "ON tblAgendaEstadosVisitas.IDESTADOVISITA =    " +
                "           tblAgendaLogVisitas.IDESTADOVISITA) " +
                "INNER JOIN (tblTerceros                        " +
                "            INNER JOIN ctrlUsuariosRutas       " +
                "ON tblTerceros.idRuta =                        " +
                "            ctrlUsuariosRutas.idRuta)          " +
                "ON tblAgendaLogVisitas.idCliente =             " +
                "                       tblTerceros.idCliente   " +
                "WHERE tblAgendaLogVisitas.FECHAVISITA          " +
                " BETWEEN '" + getFechaInicialSqlServer() + "'  " +
                " AND     '" + getFechaFinalSqlServer() + "'  " +
                "GROUP BY tblAgendaLogVisitas.IDUSUARIO,        " +
                "         tblAgendaLogVisitas.idCliente,        " +
                "         ctrlUsuariosRutas.idUsuario           " +
                "HAVING ctrlUsuariosRutas.idUsuario =           " +
                getIdUsuario() + ")  AS tmpTotales          " +
                "WHERE ctrlUsuarios.idUsuario =                 " +
                "                          tmpTotales.IDUSUARIO " +
                "GROUP BY tmpTotales.IDUSUARIO,                 " +
                "         ctrlUsuarios.nombreUsuario            ";


        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaColaboraReporteLogBean fachadaBean;

            while (rs.next()) {

                fachadaBean = new FachadaColaboraReporteLogBean();

                fachadaBean.setIdUsuario(rs.getDouble("idUsuario"));
                fachadaBean.setNombreUsuario(rs.getString("nombreUsuario"));
                fachadaBean.setTotalOrdenes(rs.getInt("totalOrdenes"));

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


    // Metodo listaLogFechaRuta MsAccess
    public Vector listaLogFechaRuta() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        boolean validaOk = false;
        Connection connection = null;

        String selectStr =
                "SELECT tmpTotales.idLocal,                                   " +
                "       tmpTotales.idUsuario,                                 " +
                "       ctrlUsuarios.nombreUsuario,                           " +
                "       tmpTotales.idCliente,                                 " +
                "       tblTerceros.nombreTercero AS nombreCliente,           " +
                "       tmpTotales.idEstadoVisita,                            " +
                "       tblAgendaEstadosVisitas.nombreEstado,                 " +
                "       tmpTotales.horaVisita,                                " +
                "       tmpTotales.fechaVisita,                               " +
                "       tmpTotales.idLog                                      " +
                "FROM ctrlUsuarios,                                           " +
                "     tblTerceros,                                            " +
                "     tblAgendaEstadosVisitas,                                " +
                "     ctrlUsuariosRutas,                                      " +
                "( SELECT ctrlUsuarios.idLocal,                               " +
                "         tblAgendaLogVisitas.IDUSUARIO,                      " +
                "         tblAgendaLogVisitas.idCliente,                      " +
                "         tblAgendaLogVisitas.idEstadoVisita,                 " +
                "         MAX(tblAgendaLogVisitas.FECHAVISITA)                " +
                "                                            AS FECHAVISITA,  " +
                "         MAX(tblAgendaLogVisitas.idLog) AS idLog,            " +
                "         MAX(tblAgendaLogVisitas.FECHAVISITA) AS horaVisita  " +
                "  FROM tblAgendaLogVisitas                                   " +
                "  INNER JOIN ctrlUsuarios                                    " +
                "  ON tblAgendaLogVisitas.IDUSUARIO =                         " +
                "                          ctrlUsuarios.idUsuario             " +
                "  WHERE tblAgendaLogVisitas.FECHAVISITA =                   '" +
                getFechaVisitaSqlServer() + "'                                " +
                "  GROUP BY ctrlUsuarios.idLocal,                             " +
                "         tblAgendaLogVisitas.IDUSUARIO,                      " +
                "         tblAgendaLogVisitas.idCliente,                      " +
                "         tblAgendaLogVisitas.idEstadoVisita ) AS tmpTotales  " +
                "WHERE ctrlUsuarios.idUsuario = tmpTotales.IDUSUARIO          " +
                "AND   tblAgendaEstadosVisitas.idEstadoVisita =               " +
                "                                tmpTotales.idEstadoVisita    " +
                "AND   tblTerceros.idCliente       =  tmpTotales.idCliente    " +
                "AND   ctrlUsuariosRutas.idRuta    = tblTerceros.idRuta       " +
                "AND   ctrlUsuariosRutas.idUsuario = " + getIdUsuario() + "   " +
                "ORDER BY tmpTotales.fechaVisita                              ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaColaboraReporteLogBean fachadaBean;

            while (rs.next()) {

                fachadaBean = new FachadaColaboraReporteLogBean();

                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdUsuario(rs.getDouble("idUsuario"));
                fachadaBean.setNombreUsuario(rs.getString("nombreUsuario"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setNombreCliente(rs.getString("nombreCliente"));
                fachadaBean.setIdEstadoVisita(rs.getInt("idEstadoVisita"));
                fachadaBean.setNombreEstado(rs.getString("nombreEstado"));
                fachadaBean.setHoraVisita(rs.getString("horaVisita"));
                fachadaBean.setIdLog(rs.getInt("idLog"));

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

    // Metodo listaTotalClienteVendedor
    public Vector listaTotalClienteVendedor() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        boolean validaOk = false;
        Connection connection = null;

        String selectStr =
                "SELECT tmpTotales.IDUSUARIO,                   " +
                "       ctrlUsuarios.nombreUsuario,             " +
                "       COUNT(*) AS totalOrdenes                " +
                "FROM ctrlUsuarios,                             " +
                "(SELECT ctrlUsuarios.idLocal,                  " +
                "        tblAgendaLogVisitas.IDUSUARIO,         " +
                "        tblAgendaLogVisitas.idCliente          " +
                " FROM tblAgendaEstadosVisitas                  " +
                " INNER JOIN (ctrlUsuarios                      " +
                " INNER JOIN tblAgendaLogVisitas                " +
                " ON ctrlUsuarios.idUsuario                 =   " +
                "                tblAgendaLogVisitas.IDUSUARIO) " +
                " ON tblAgendaEstadosVisitas.IDESTADOVISITA =   " +
                "            tblAgendaLogVisitas.IDESTADOVISITA " +
                " WHERE tblAgendaLogVisitas.idUsuario       =   " +
                getIdUsuario() + "                              " +
                " AND tblAgendaLogVisitas.FECHAVISITA           " +
                "  BETWEEN '" + getFechaInicialSqlServer() + "' " +
                "  AND '" + getFechaFinalSqlServer() + "'       " +
                "GROUP BY ctrlUsuarios.idLocal,                 " +
                "         tblAgendaLogVisitas.idUsuario,        " +
                "         tblAgendaLogVisitas.idCliente)        " +
                "                                 AS tmpTotales " +
                "WHERE ctrlUsuarios.idUsuario =                 " +
                "                          tmpTotales.IDUSUARIO " +
                "GROUP BY tmpTotales.IDUSUARIO,                 " +
                "         ctrlUsuarios.nombreUsuario            ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaColaboraReporteLogBean fachadaBean;

            while (rs.next()) {

                fachadaBean = new FachadaColaboraReporteLogBean();

                fachadaBean.setIdUsuario(rs.getDouble("idUsuario"));
                fachadaBean.setNombreUsuario(rs.getString("nombreUsuario"));
                fachadaBean.setTotalOrdenes(rs.getInt("totalOrdenes"));

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