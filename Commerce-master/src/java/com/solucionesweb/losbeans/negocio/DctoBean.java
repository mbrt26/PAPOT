package com.solucionesweb.losbeans.negocio;

// Importa la clase helper JDBCAccess que contiene los accesos a la base de datos
import com.solucionesweb.lasayudas.JDBCAccess;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaDctoBean;

// Importa los paquetes de java
import java.sql.*;
import java.io.*;
import java.util.Vector;
import java.io.IOException.*;

// Importa los paquetes de javax
import javax.naming.*;

// Importa la interface de las constantes
import com.solucionesweb.losbeans.utilidades.IConstantes;

public class DctoBean extends FachadaDctoBean implements Serializable,
        IConstantes {

    // Nombre JNDI del Data Source que esta clase requiere
    private static final String DATA_SOURCE_NAME = "CommerceAccess";
    // Clase helper usada para acceder a la base de datos
    private JDBCAccess jdbcAccess;

    // Metodo constructor por defecto sin parametros
    public DctoBean() {

        // Al instanciar el Bean establece la conexion a la base de datos
        jdbcAccess = new JDBCAccess(DATA_SOURCE_NAME);
    }

    // Metodo maximaDctoTipoOrdenIdLocal
    public int maximaDctoTipoOrdenIdLocal() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        int maximaIdDcto = 0;
        Connection connection = null;

        String insertStr =
                "SELECT MAX(tblDctos.idDcto)        "
                + "                 AS maximaIdDcto   "
                + "FROM tblDctos                      "
                + "WHERE tblDctos.idLocal     = ( ? ) "
                + "AND   tblDctos.idTipoOrden = ( ? ) ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(insertStr);

            // inicializa los valore de los parametros
            selectStatement.setInt(1, getIdLocal());
            selectStatement.setInt(2, getIdTipoOrden());

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            if (rs.next()) {
                maximaIdDcto = rs.getInt("maximaIdDcto");
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
            return maximaIdDcto;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {

            // Cierra la conexion a la base de datos limpiando la statement usada y la
            jdbcAccess.cleanup(connection, selectStatement, null);
            return maximaIdDcto;
        }
    }

    // Metodo maximaOrdenIdLocal
    public int maximaOrdenIdLocal() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        int maximaIdOrden = 0;

        Vector contenedor = new Vector();
        Connection connection = null;

        String insertStr =
                "SELECT MAX(tblDctos.idOrden)       "
                + "                 AS maximaIdOrden  "
                + "FROM tblDctos                      "
                + "WHERE tblDctos.idLocal     = ( ? ) ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(insertStr);

            // inicializa los valore de los parametros
            selectStatement.setInt(1, getIdLocal());

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaDctoBean fachadaBean;

            if (rs.next()) {

                maximaIdOrden = rs.getInt("maximaIdOrden");
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
            return maximaIdOrden;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {

            // Cierra la conexion a la base de datos limpiando la statement usada y la
            jdbcAccess.cleanup(connection, selectStatement, null);
            return maximaIdOrden;
        }
    }

    // Metodo maximoDctoLocalAlcance
    public int maximoDctoLocalAlcance(int xIdAlcance) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        int maxIdDcto = 0;

        Connection connection = null;

        String insertStr =
                "SELECT  MAX(tblDctos.idDcto)   "
                + "                  AS maxIdDcto "
                + "FROM   tblDctos                "
                + "INNER JOIN tblTipoOrden        "
                + "ON tblDctos.idTipoOrden      = "
                + "  tblTipoOrden.idTipoOrden     "
                + "WHERE tblDctos.idLocal       = "
                + getIdLocal() + "                "
                + "AND   tblTipoOrden.idAlcance = "
                + xIdAlcance;

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(insertStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            //
            if (rs.next()) {

                maxIdDcto = rs.getInt("maxIdDcto");

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
            return maxIdDcto;
        }
    }

    // Metodo maximoDctoLocalIndicador
    public int maximoDctoLocalIndicador() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        int maxIdDcto = 0;

        Connection connection = null;

        //
        String insertStr =
                "SELECT MAX(tblDctos.idDcto)      "
                + "                 AS maxIdDcto  "
                + "FROM tblDctos                  "
                + "WHERE tblDctos.idLocal     =   "
                + getIdLocal() + "                "
                + "AND  tblDctos.idTipoOrden  =   "
                + getIdTipoOrden() + "            "
                + "AND  tblDctos.indicador    =   "
                + getIndicador();

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(insertStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            //
            if (rs.next()) {

                maxIdDcto = rs.getInt("maxIdDcto");

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
            return maxIdDcto;
        }
    }

    // Metodo maximoDctoCajaIndicador
    public int maximoDctoIpIndicador(String xIpTx) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        int maxIdDcto = 0;

        Connection connection = null;

        String insertStr =
                "SELECT MAX(tblDctos.idDcto)        "
                + "                  AS  maxIdDcto  "
                + "FROM   tblDctos                  "
                + "INNER JOIN tblDctosOrdenes       "
                + "ON tblDctos.IDLOCAL          =   "
                + "       tblDctosOrdenes.IDLOCAL   "
                + "AND tblDctos.IDTIPOORDEN     =   "
                + "   tblDctosOrdenes.IDTIPOORDEN   "
                + "AND tblDctos.IDORDEN         =   "
                + "       tblDctosOrdenes.IDORDEN   "
                + "INNER JOIN tblAgendaLogVisitas   "
                + "ON tblDctosOrdenes.IDLOG     =   "
                + "     tblAgendaLogVisitas.idLog   "
                + "WHERE tblDctos.idLocal       =   "
                + getIdLocal() + "                  "
                + "AND  tblDctos.idTipoOrden    =   "
                + getIdTipoOrden() + "              "
                + "AND  tblDctos.indicador      =   "
                + getIndicador() + "                "
                + "AND tblAgendaLogVisitas.ipTx =  '"
                + xIpTx + "'";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(insertStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            //
            if (rs.next()) {

                maxIdDcto = rs.getInt("maxIdDcto");

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
            return maxIdDcto;
        }
    }

    // ingresaDcto
    public boolean ingresaDcto() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                "INSERT INTO tblDctos (idLocal,             "
                + "                      idTipoOrden,         "
                + "                      idOrden,             "
                + "                      idDcto,              "
                + "                      indicador,           "
                + "                      fechaDcto,           "
                + "                      vrBase,              "
                + "                      vrPago,              "
                + "                      idEstado,            "
                + "                      vrIva,               "
                + "                      idTipoNegocio,       "
                + "                      vrRteFuente,         "
                + "                      vrDescuento,         "
                + "                      vrRteIva,            "
                + "                      vrRteIca,            "
                + "                      nombreTercero,       "
                + "                      idUsuario,           "
                + "                      idCliente,           "
                + "                      diasPlazo,           "
                + "                      descuentoComercial,  "
                + "                      idCausa,             "
                + "                      idDctoNitCC,         "
                + "                      fechaDctoNitCC,      "
                + "                      vrPagarDctoNitCC,    "
                + "                      vrDsctoFcro,         "
                + "                      vrCostoMV,           "
                + "                      idLocalCruce,        "
                + "                      idTipoOrdenCruce,    "
                + "                      idDctoCruce,         "
                + "                      idPeriodo,           "
                + "                      idVendedor,          "
                + "                      vrImpoconsumo,       "
                + "                      vrCostoIND,          "
                + "                      idOrdenCruce,"
                + "                      vrRteCree)        "
                + "VALUES ( " + getIdLocal() + ","
                + getIdTipoOrden() + ","
                + getIdOrden() + ","
                + getIdDcto() + ","
                + getIndicador() + ",'"
                + getFechaDctoSqlServer() + "',"
                + getVrBase() + ","
                + getVrPago() + ","
                + getEstado() + ","
                + getVrIva() + ","
                + getIdTipoNegocio() + ","
                + getVrRteFuente() + ","
                + getVrDescuento() + ","
                + getVrRteIva() + ","
                + getVrRteIca() + ",'"
                + getNombreTercero() + "',"
                + getIdUsuario() + ",'"
                + getIdCliente() + "',"
                + getDiasPlazo() + ","
                + getPorcentajeDscto() + ","
                + getIdCausa() + ",'"
                + getIdDctoNitCC() + "','"
                + getFechaDctoNitCCSqlServer() + "',"
                + getVrPagarDctoNitCC() + ","
                + getVrDsctoFcro() + ","
                + getVrCostoMV() + ","
                + getIdLocalCruce() + ","
                + getIdTipoOrdenCruce() + ","
                + getIdDctoCruce() + ","
                + getIdPeriodo() + ","
                + getIdVendedor() + ","
                + getVrImpoconsumo() + ","
                + getVrCostoIND() + ","
                + getIdOrdenCruce() + ","
                + getVrRteCree() + ")";

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
            return okIngresar;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {

            // Cierra la conexion a la base de datos limpiando la statement usada y la
            jdbcAccess.cleanup(connection, selectStatement, null);
            return okIngresar;
        }
    }

    // Metodo listaUnDcto
    public FachadaDctoBean listaUnDcto() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        // Variable de fachada de los datos
        FachadaDctoBean fachadaBean = new FachadaDctoBean();

        Connection connection = null;

        String selectStr =
                "SELECT tblDctos.IDLOCAL,             "
                + "       tblDctos.IDTIPOORDEN,         "
                + "       tblDctos.IDORDEN,             "
                + "       tblDctos.idDcto,              "
                + "       tblDctos.indicador,           "
                + "       tblDctos.idCliente,           "
                + "       tblDctos.fechaDcto,           "
                + "       tblDctos.vrBase,              "
                + "       tblDctos.vrPago,              "
                + "       tblDctos.idEstado,            "
                + "       tblDctos.vrIva,               "
                + "       tblDctos.idTipoNegocio,       "
                + "       tblDctos.vrRteFuente,         "
                + "       tblDctos.vrDescuento,         "
                + "       tblDctos.vrRteIva,            "
                + "       tblDctos.nombreTercero,       "
                + "       tblDctos.IDUSUARIO,           "
                + "       tblDctos.diasPlazo,           "
                + "       tblDctos.descuentoComercial,  "
                + "       tblDctos.idCausa,             "
                + "       tblDctos.idDctoNitCC,         "
                + "       tblDctos.fechaDctoNitCC,      "
                + "       tblDctos.vrPagarDctoNitCC,    "
                + "       tblDctos.vrDsctoFcro,         "
                + "       tblDctos.vrCostoMV,           "
                + "       tblDctos.idLocalCruce,        "
                + "       tblDctos.idTipoOrdenCruce,    "
                + "       tblDctos.idDctoCruce,         "
                + "       tblDctos.idPeriodo,           "
                + "       tblDctos.idVendedor,          "
                + "       tblDctos.vrImpoconsumo        "
                + "FROM tblDctos                        "
                + "WHERE tblDctos.idLocal    =          "
                + getIdLocal() + "                      "
                + "AND  tblDctos.idTipoOrden =          "
                + getIdTipoOrden() + "                  "
                + "AND  tblDctos.idDcto      =          "
                + getIdDcto() + "                       "
                + "AND  tblDctos.indicador   =          "
                + getIndicador() + "                    ";

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
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setIdOrden(rs.getInt("idOrden"));
                fachadaBean.setIdDcto(rs.getInt("idDcto"));
                fachadaBean.setIndicador(rs.getInt("indicador"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setFechaDcto(rs.getString("fechaDcto"));
                fachadaBean.setVrBase(rs.getDouble("vrBase"));
                fachadaBean.setVrPago(rs.getDouble("vrPago"));
                fachadaBean.setEstado(rs.getInt("idEstado"));
                fachadaBean.setVrIva(rs.getDouble("vrIva"));
                fachadaBean.setIdTipoNegocio(rs.getInt("idTipoNegocio"));
                fachadaBean.setVrRteFuente(rs.getDouble("vrRteFuente"));
                fachadaBean.setVrDescuento(rs.getDouble("vrDescuento"));
                fachadaBean.setVrRteIva(rs.getDouble("vrRteIva"));
                fachadaBean.setNombreTercero(rs.getString("nombreTercero"));
                fachadaBean.setIdUsuario(rs.getDouble("idUsuario"));
                fachadaBean.setDiasPlazo(rs.getInt("diasPlazo"));
                fachadaBean.setPorcentajeDscto(
                        rs.getDouble("descuentoComercial"));
                fachadaBean.setIdCausa(rs.getInt("idCausa"));
                fachadaBean.setIdDctoNitCC(rs.getString("idDctoNitCC"));
                fachadaBean.setFechaDctoNitCCStr(
                        rs.getString("fechaDctoNitCC"));
                fachadaBean.setVrPagarDctoNitCC(
                        rs.getDouble("vrPagarDctoNitCC"));
                fachadaBean.setVrDsctoFcro(rs.getDouble("vrDsctoFcro"));
                fachadaBean.setVrCostoMV(rs.getDouble("vrCostoMV"));
                fachadaBean.setIdLocalCruce(rs.getInt("idLocalCruce"));
                fachadaBean.setIdTipoOrdenCruce(rs.getInt("idTipoOrdenCruce"));
                fachadaBean.setIdDctoCruce(rs.getInt("idDctoCruce"));
                fachadaBean.setIdPeriodo(rs.getInt("idPeriodo"));
                fachadaBean.setIdVendedor(rs.getDouble("idVendedor"));
                fachadaBean.setVrImpoconsumo(rs.getDouble("vrImpoconsumo"));

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

    // Metodo listaDcto
    public FachadaDctoBean listaUnDctoOrden() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        // Variable de fachada de los datos
        FachadaDctoBean fachadaBean = new FachadaDctoBean();

        Connection connection = null;

        String selectStr =
                "SELECT tblDctos.IDLOCAL,             "
                + "       tblDctos.IDTIPOORDEN,         "
                + "       tblDctos.IDORDEN,             "
                + "       tblDctos.idDcto,              "
                + "       tblDctos.indicador,           "
                + "       tblDctos.idCliente,           "
                + "       tblDctos.fechaDcto,           "
                + "       tblDctos.vrBase,              "
                + "       tblDctos.vrPago,              "
                + "       tblDctos.idEstado,            "
                + "       tblDctos.vrIva,               "
                + "       tblDctos.idTipoNegocio,       "
                + "       tblDctos.vrRteFuente,         "
                + "       tblDctos.vrDescuento,         "
                + "       tblDctos.vrRteIva,            "
                + "       tblDctos.nombreTercero,       "
                + "       tblDctos.IDUSUARIO,           "
                + "       tblDctos.diasPlazo,           "
                + "       tblDctos.descuentoComercial,  "
                + "       tblDctos.idCausa,             "
                + "       tblDctos.idDctoNitCC,         "
                + "       tblDctos.fechaDctoNitCC,      "
                + "       tblDctos.vrPagarDctoNitCC,    "
                + "       tblDctos.vrDsctoFcro,         "
                + "       tblDctos.vrCostoMV,           "
                + "       tblDctos.idLocalCruce,        "
                + "       tblDctos.idTipoOrdenCruce,    "
                + "       tblDctos.idDctoCruce,         "
                + "       tblDctos.idPeriodo,           "
                + "       tblDctos.idVendedor,          "
                + "       tblDctos.vrImpoconsumo        "
                + "FROM tblDctos                        "
                + "WHERE tblDctos.idLocal    =          "
                + getIdLocal() + "                      "
                + "AND  tblDctos.idTipoOrden =          "
                + getIdTipoOrden() + "                  "
                + "AND  tblDctos.idOrden     =          "
                + getIdOrden() + "                      "
                + "AND  tblDctos.indicador   =          "
                + getIndicador() + "                    ";

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
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setIdOrden(rs.getInt("idOrden"));
                fachadaBean.setIdDcto(rs.getInt("idDcto"));
                fachadaBean.setIndicador(rs.getInt("indicador"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setFechaDcto(rs.getString("fechaDcto"));
                fachadaBean.setVrBase(rs.getDouble("vrBase"));
                fachadaBean.setVrPago(rs.getDouble("vrPago"));
                fachadaBean.setEstado(rs.getInt("idEstado"));
                fachadaBean.setVrIva(rs.getDouble("vrIva"));
                fachadaBean.setIdTipoNegocio(rs.getInt("idTipoNegocio"));
                fachadaBean.setVrRteFuente(rs.getDouble("vrRteFuente"));
                fachadaBean.setVrDescuento(rs.getDouble("vrDescuento"));
                fachadaBean.setVrRteIva(rs.getDouble("vrRteIva"));
                fachadaBean.setNombreTercero(rs.getString("nombreTercero"));
                fachadaBean.setIdUsuario(rs.getDouble("idUsuario"));
                fachadaBean.setDiasPlazo(rs.getInt("diasPlazo"));
                fachadaBean.setPorcentajeDscto(
                        rs.getDouble("descuentoComercial"));
                fachadaBean.setIdCausa(rs.getInt("idCausa"));
                fachadaBean.setIdDctoNitCC(rs.getString("idDctoNitCC"));
                fachadaBean.setFechaDctoNitCCStr(
                        rs.getString("fechaDctoNitCC"));
                fachadaBean.setVrPagarDctoNitCC(
                        rs.getDouble("vrPagarDctoNitCC"));
                fachadaBean.setVrDsctoFcro(rs.getDouble("vrDsctoFcro"));
                fachadaBean.setVrCostoMV(rs.getDouble("vrCostoMV"));
                fachadaBean.setIdLocalCruce(rs.getInt("idLocalCruce"));
                fachadaBean.setIdTipoOrdenCruce(rs.getInt("idTipoOrdenCruce"));
                fachadaBean.setIdDctoCruce(rs.getInt("idDctoCruce"));
                fachadaBean.setIdPeriodo(rs.getInt("idPeriodo"));
                fachadaBean.setIdVendedor(rs.getDouble("idVendedor"));
                fachadaBean.setVrImpoconsumo(rs.getDouble("vrImpoconsumo"));
                

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

    // Metodo listaUnPagoOrden
    public FachadaDctoBean listaUnPagoOrden() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        // Variable de fachada de los datos
        FachadaDctoBean fachadaBean = new FachadaDctoBean();

        Connection connection = null;

        String selectStr =
                "SELECT tblDctos.IDLOCAL,             "
                + "       tblDctos.IDTIPOORDEN,         "
                + "       tblDctos.IDORDEN,             "
                + "       tblDctos.idDcto,              "
                + "       tblDctos.indicador,           "
                + "       tblDctos.idCliente,           "
                + "       tblDctos.fechaDcto,           "
                + "       tblDctos.vrBase,              "
                + "       tblDctos.vrPago,              "
                + "       tblDctos.idEstado,            "
                + "       tblDctos.vrIva,               "
                + "       tblDctos.idTipoNegocio,       "
                + "       tblDctos.vrRteFuente,         "
                + "       tblDctos.vrDescuento,         "
                + "       tblDctos.vrRteIva,            "
                + "       tblDctos.nombreTercero,       "
                + "       tblDctos.IDUSUARIO,           "
                + "       tblDctos.diasPlazo,           "
                + "       tblDctos.descuentoComercial,  "
                + "       tblDctos.idCausa,             "
                + "       tblDctos.idDctoNitCC,         "
                + "       tblDctos.fechaDctoNitCC,      "
                + "       tblDctos.vrPagarDctoNitCC,    "
                + "       tblDctos.vrDsctoFcro,         "
                + "       tblDctos.vrCostoMV,           "
                + "       tblDctos.idLocalCruce,        "
                + "       tblDctos.idTipoOrdenCruce,    "
                + "       tblDctos.idDctoCruce,         "
                + "       tblDctos.idPeriodo,           "
                + "       tblDctos.idVendedor,          "
                + "       tblDctos.vrImpoconsumo        "
                + "FROM tblDctos                        "
                + "WHERE tblDctos.idLocal    =          "
                + getIdLocal() + "                      "
                + "AND  tblDctos.idTipoOrden =          "
                + getIdTipoOrden() + "                  "
                + "AND  tblDctos.IDORDEN     =          "
                + getIdOrden() + "                      ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            while (rs.next()) {

                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setIdOrden(rs.getInt("idOrden"));
                fachadaBean.setIdDcto(rs.getInt("idDcto"));
                fachadaBean.setIndicador(rs.getInt("indicador"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setFechaDcto(rs.getString("fechaDcto"));
                fachadaBean.setVrBase(rs.getDouble("vrBase"));
                fachadaBean.setVrPago(rs.getDouble("vrPago"));
                fachadaBean.setEstado(rs.getInt("idEstado"));
                fachadaBean.setVrIva(rs.getDouble("vrIva"));
                fachadaBean.setIdTipoNegocio(rs.getInt("idTipoNegocio"));
                fachadaBean.setVrRteFuente(rs.getDouble("vrRteFuente"));
                fachadaBean.setVrDescuento(rs.getDouble("vrDescuento"));
                fachadaBean.setVrRteIva(rs.getDouble("vrRteIva"));
                fachadaBean.setNombreTercero(rs.getString("nombreTercero"));
                fachadaBean.setIdUsuario(rs.getDouble("idUsuario"));
                fachadaBean.setDiasPlazo(rs.getInt("diasPlazo"));
                fachadaBean.setPorcentajeDscto(
                        rs.getDouble("descuentoComercial"));
                fachadaBean.setIdCausa(rs.getInt("idCausa"));
                fachadaBean.setIdDctoNitCC(rs.getString("idDctoNitCC"));
                fachadaBean.setFechaDctoNitCC(rs.getString("fechaDctoNitCC"));
                fachadaBean.setVrPagarDctoNitCC(
                        rs.getDouble("vrPagarDctoNitCC"));
                fachadaBean.setVrDsctoFcro(rs.getDouble("vrDsctoFcro"));
                fachadaBean.setVrCostoMV(rs.getDouble("vrCostoMV"));
                fachadaBean.setIdLocalCruce(rs.getInt("idLocalCruce"));
                fachadaBean.setIdTipoOrdenCruce(rs.getInt("idTipoOrdenCruce"));
                fachadaBean.setIdDctoCruce(rs.getInt("idDctoCruce"));
                fachadaBean.setIdPeriodo(rs.getInt("idPeriodo"));
                fachadaBean.setIdVendedor(rs.getDouble("idVendedor"));
                fachadaBean.setVrImpoconsumo(rs.getDouble("vrImpoconsumo"));

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

    // Metodo listaUnIdDctoNitCC
    public FachadaDctoBean listaUnIdDctoNitCC() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        // Variable de fachada de los datos
        FachadaDctoBean fachadaBean = new FachadaDctoBean();

        Connection connection = null;

        String selectStr =
                "SELECT tblDctos.IDLOCAL,             "
                + "       tblDctos.IDTIPOORDEN,         "
                + "       tblDctos.IDORDEN,             "
                + "       tblDctos.idDcto,              "
                + "       tblDctos.indicador,           "
                + "       tblDctos.idCliente,           "
                + "       tblDctos.fechaDcto,           "
                + "       tblDctos.vrBase,              "
                + "       tblDctos.vrPago,              "
                + "       tblDctos.idEstado,            "
                + "       tblDctos.vrIva,               "
                + "       tblDctos.idTipoNegocio,       "
                + "       tblDctos.vrRteFuente,         "
                + "       tblDctos.vrDescuento,         "
                + "       tblDctos.vrRteIva,            "
                + "       tblDctos.nombreTercero,       "
                + "       tblDctos.IDUSUARIO,           "
                + "       tblDctos.diasPlazo,           "
                + "       tblDctos.descuentoComercial,  "
                + "       tblDctos.idCausa,             "
                + "       tblDctos.idDctoNitCC,         "
                + "       tblDctos.fechaDctoNitCC,      "
                + "       tblDctos.vrPagarDctoNitCC,    "
                + "       tblDctos.vrDsctoFcro,         "
                + "       tblDctos.vrCostoMV,           "
                + "       tblDctos.idLocalCruce,        "
                + "       tblDctos.idTipoOrdenCruce,    "
                + "       tblDctos.idDctoCruce,         "
                + "       tblDctos.idPeriodo,           "
                + "       tblDctos.idVendedor           "
                + "FROM tblDctos                        "
                + "WHERE tblDctos.idLocal    =          "
                + getIdLocal() + "                      "
                + "AND  tblDctos.idTipoOrden =          "
                + getIdTipoOrden() + "                  "
                + "AND  tblDctos.idCliente   =         '"
                + getIdCliente() + "'                   "
                + "AND  tblDctos.idDctoNitCC =         '"
                + getIdDctoNitCC() + "'                 ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            while (rs.next()) {

                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setIdOrden(rs.getInt("idOrden"));
                fachadaBean.setIdDcto(rs.getInt("idDcto"));
                fachadaBean.setIndicador(rs.getInt("indicador"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setFechaDcto(rs.getString("fechaDcto"));
                fachadaBean.setVrBase(rs.getDouble("vrBase"));
                fachadaBean.setVrPago(rs.getDouble("vrPago"));
                fachadaBean.setEstado(rs.getInt("idEstado"));
                fachadaBean.setVrIva(rs.getDouble("vrIva"));
                fachadaBean.setIdTipoNegocio(rs.getInt("idTipoNegocio"));
                fachadaBean.setVrRteFuente(rs.getInt("vrRteFuente"));
                fachadaBean.setVrDescuento(rs.getInt("vrDescuento"));
                fachadaBean.setVrRteIva(rs.getInt("vrRteIva"));
                fachadaBean.setNombreTercero(rs.getString("nombreTercero"));
                fachadaBean.setIdUsuario(rs.getDouble("idUsuario"));
                fachadaBean.setDiasPlazo(rs.getInt("diasPlazo"));
                fachadaBean.setPorcentajeDscto(
                        rs.getDouble("descuentoComercial"));
                fachadaBean.setIdCausa(rs.getInt("idCausa"));
                fachadaBean.setIdDctoNitCC(rs.getString("idDctoNitCC"));
                fachadaBean.setFechaDctoNitCC(rs.getString("fechaDctoNitCC"));
                fachadaBean.setVrPagarDctoNitCC(rs.getInt("vrPagarDctoNitCC"));
                fachadaBean.setVrDsctoFcro(rs.getInt("vrDsctoFcro"));
                fachadaBean.setVrCostoMV(rs.getInt("vrCostoMV"));
                fachadaBean.setIdLocalCruce(rs.getInt("idLocalCruce"));
                fachadaBean.setIdTipoOrdenCruce(rs.getInt("idTipoOrdenCruce"));
                fachadaBean.setIdDctoCruce(rs.getInt("idDctoCruce"));
                fachadaBean.setIdPeriodo(rs.getInt("idPeriodo"));
                fachadaBean.setIdVendedor(rs.getDouble("idVendedor"));

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

    // actualizaEstadoAnuladoFacturaDcto
    public boolean actualizaEstadoAnuladoFacturaDcto(int estadoNuevo) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okActualizarDctoOrdenFactura = false;

        Vector contenedor = new Vector();
        Connection connection = null;

        String selectStr =
                " UPDATE tblDctos                       "
                + " SET tblDctos.estado            =      "
                + estadoNuevo + "   "
                + " WHERE tblDctos.idDcto          =      "
                + getIdDcto() + "   "
                + " AND   tblDctos.idLocal         =      "
                + getIdLocal() + "   "
                + " AND   tblDctos.idTipoOrden     =      "
                + getIdTipoOrden() + "   ";

        PreparedStatement selectStatement = null;

        System.out.println(" selectStr " + selectStr);

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            selectStatement.execute();
            okActualizarDctoOrdenFactura = true;

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
            return okActualizarDctoOrdenFactura;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {
            // Cierra la conexion a la base de datos limpiando la statement usada y la
            // conexion
            return okActualizarDctoOrdenFactura;
        }
    }

    // actualizaPago
    public boolean actualizaPago() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okActualizarDctoOrdenFactura = false;

        Connection connection = null;

        //
        String selectStr =
                "UPDATE tblDctos                         "
                + "SET tblDctos.vrPago         =           "
                + "                tblDctos.vrPago +       "
                + getVrPago() + ",                         "
                + "    tblDctos.vrRteFuente    =           "
                + "           tblDctos.vrRteFuente +       "
                + getVrRteFuente() + ",                    "
                + "    tblDctos.vrDsctoFcro    =           "
                + "           tblDctos.vrDsctoFcro +       "
                + getVrDsctoFcro() + ",                    "
                + "    tblDctos.vrRteIva       =           "
                + "           tblDctos.vrRteIva    +       "
                + getVrRteIva() + ",                       "
                + "    tblDctos.vrRteIca       =           "
                + "           tblDctos.vrRteIca    +       "
                + getVrRteIca() + "                        "
                + "WHERE tblDctos.IDLOCAL      =           "
                + getIdLocal() + "                         "
                + "AND   tblDctos.IDTIPOORDEN  =           "
                + getIdTipoOrden() + "                     "
                + "AND   tblDctos.idDcto       =           "
                + getIdDcto() + "                          "
                + "AND   tblDctos.indicador    =           "
                + getIndicador() + "                       ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            selectStatement.execute();
            okActualizarDctoOrdenFactura = true;

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

            // Cierra el Resultset
            jdbcAccess.cleanup(connection, selectStatement, null);
            return okActualizarDctoOrdenFactura;
        }
    }

    // actualizaPagoTercero
    public boolean actualizaPagoTercero() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okActualizarDctoOrdenFactura = false;

        Connection connection = null;

        //
        String selectStr =
                "UPDATE tblDctos                         "
                + "SET tblDctos.vrPago         =           "
                + "                tblDctos.vrPago +       "
                + getVrPago() + ",                         "
                + "    tblDctos.vrRteFuente    =           "
                + "           tblDctos.vrRteFuente +       "
                + getVrRteFuente() + ",                    "
                + "    tblDctos.vrDsctoFcro    =           "
                + "           tblDctos.vrDsctoFcro +       "
                + getVrDsctoFcro() + ",                    "
                + "    tblDctos.vrRteIva       =           "
                + "           tblDctos.vrRteIva    +       "
                + getVrRteIva() + ",                       "
                + "    tblDctos.vrRteIca       =           "
                + "           tblDctos.vrRteIca    +       "
                + getVrRteIca() + "                        "
                + "WHERE tblDctos.IDLOCAL      =           "
                + getIdLocal() + "                         "
                + "AND   tblDctos.IDTIPOORDEN  =           "
                + getIdTipoOrden() + "                     "
                + "AND   tblDctos.idDcto       =           "
                + getIdDcto() + "                          "
                + "AND   tblDctos.indicador    =           "
                + getIndicador() + "                       "
                + "AND   tblDctos.idCliente    =          '"
                + getIdCliente() + "'  ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            selectStatement.execute();
            okActualizarDctoOrdenFactura = true;

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

            // Cierra el Resultset
            jdbcAccess.cleanup(connection, selectStatement, null);
            return okActualizarDctoOrdenFactura;
        }
    }

    // ingresaPagoContado
    public boolean ingresaPagoContado() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okActualizarDctoOrdenFactura = false;

        Connection connection = null;

        //
        String selectStr =
                "UPDATE tblDctos                 "
                + "SET tblDctos.vrPago         =   "
                + getVrPago() + ",                 "
                + "    tblDctos.vrRteFuente    =   "
                + getVrRteFuente() + ",            "
                + "    tblDctos.vrDsctoFcro    =   "
                + getVrDsctoFcro() + ",            "
                + "    tblDctos.vrRteIva       =   "
                + getVrRteIva() + ",               "
                + "    tblDctos.vrRteIca       =   "
                + getVrRteIca() + "                "
                + "WHERE tblDctos.IDLOCAL      =   "
                + getIdLocal() + "                 "
                + "AND   tblDctos.IDTIPOORDEN  =   "
                + getIdTipoOrden() + "             "
                + "AND   tblDctos.idDcto       =   "
                + getIdDcto() + "                  "
                + "AND   tblDctos.indicador    =   "
                + getIndicador() + "               ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            selectStatement.execute();
            okActualizarDctoOrdenFactura = true;

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

            // Cierra el Resultset
            jdbcAccess.cleanup(connection, selectStatement, null);
            return okActualizarDctoOrdenFactura;
        }
    }


    // Metodo listaDcto
    public FachadaDctoBean listaUnDctoOrdenSinIndicador() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        // Variable de fachada de los datos
        FachadaDctoBean fachadaBean = new FachadaDctoBean();

        Connection connection = null;

        String selectStr =
                "SELECT tblDctos.IDLOCAL,             "
                + "       tblDctos.IDTIPOORDEN,         "
                + "       tblDctos.IDORDEN,             "
                + "       tblDctos.idDcto,              "
                + "       tblDctos.indicador,           "
                + "       tblDctos.idCliente,           "
                + "       tblDctos.fechaDcto,           "
                + "       tblDctos.vrBase,              "
                + "       tblDctos.vrPago,              "
                + "       tblDctos.idEstado,            "
                + "       tblDctos.vrIva,               "
                + "       tblDctos.idTipoNegocio,       "
                + "       tblDctos.vrRteFuente,         "
                + "       tblDctos.vrDescuento,         "
                + "       tblDctos.vrRteIva,            "
                + "       tblDctos.nombreTercero,       "
                + "       tblDctos.IDUSUARIO,           "
                + "       tblDctos.diasPlazo,           "
                + "       tblDctos.descuentoComercial,  "
                + "       tblDctos.idCausa,             "
                + "       tblDctos.idDctoNitCC,         "
                + "       tblDctos.fechaDctoNitCC,      "
                + "       tblDctos.vrPagarDctoNitCC,    "
                + "       tblDctos.vrDsctoFcro,         "
                + "       tblDctos.vrCostoMV,           "
                + "       tblDctos.idLocalCruce,        "
                + "       tblDctos.idTipoOrdenCruce,    "
                + "       tblDctos.idDctoCruce,         "
                + "       tblDctos.idPeriodo,           "
                + "       tblDctos.idVendedor,          "
                + "       tblDctos.vrImpoconsumo        "
                + "FROM tblDctos                        "
                + "WHERE tblDctos.idLocal    =          "
                + getIdLocal() + "                      "
                + "AND  tblDctos.idTipoOrden =          "
                + getIdTipoOrden() + "                  "
                + "AND  tblDctos.idOrden     =          "
                + getIdOrden() + "                      ";

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
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setIdOrden(rs.getInt("idOrden"));
                fachadaBean.setIdDcto(rs.getInt("idDcto"));
                fachadaBean.setIndicador(rs.getInt("indicador"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setFechaDcto(rs.getString("fechaDcto"));
                fachadaBean.setVrBase(rs.getDouble("vrBase"));
                fachadaBean.setVrPago(rs.getDouble("vrPago"));
                fachadaBean.setEstado(rs.getInt("idEstado"));
                fachadaBean.setVrIva(rs.getDouble("vrIva"));
                fachadaBean.setIdTipoNegocio(rs.getInt("idTipoNegocio"));
                fachadaBean.setVrRteFuente(rs.getDouble("vrRteFuente"));
                fachadaBean.setVrDescuento(rs.getDouble("vrDescuento"));
                fachadaBean.setVrRteIva(rs.getDouble("vrRteIva"));
                fachadaBean.setNombreTercero(rs.getString("nombreTercero"));
                fachadaBean.setIdUsuario(rs.getDouble("idUsuario"));
                fachadaBean.setDiasPlazo(rs.getInt("diasPlazo"));
                fachadaBean.setPorcentajeDscto(
                        rs.getDouble("descuentoComercial"));
                fachadaBean.setIdCausa(rs.getInt("idCausa"));
                fachadaBean.setIdDctoNitCC(rs.getString("idDctoNitCC"));
                fachadaBean.setFechaDctoNitCCStr(
                        rs.getString("fechaDctoNitCC"));
                fachadaBean.setVrPagarDctoNitCC(
                        rs.getDouble("vrPagarDctoNitCC"));
                fachadaBean.setVrDsctoFcro(rs.getDouble("vrDsctoFcro"));
                fachadaBean.setVrCostoMV(rs.getDouble("vrCostoMV"));
                fachadaBean.setIdLocalCruce(rs.getInt("idLocalCruce"));
                fachadaBean.setIdTipoOrdenCruce(rs.getInt("idTipoOrdenCruce"));
                fachadaBean.setIdDctoCruce(rs.getInt("idDctoCruce"));
                fachadaBean.setIdPeriodo(rs.getInt("idPeriodo"));
                fachadaBean.setIdVendedor(rs.getDouble("idVendedor"));
                fachadaBean.setVrImpoconsumo(rs.getDouble("vrImpoconsumo"));

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
