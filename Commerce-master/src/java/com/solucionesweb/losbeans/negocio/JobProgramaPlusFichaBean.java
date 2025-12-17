package com.solucionesweb.losbeans.negocio;

// Importa la clase helper JDBCAccess que contiene los accesos a la base de datos
import com.solucionesweb.lasayudas.JDBCAccess;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaJobProgramaPlusFicha;

// Importa los paquetes de java
import java.sql.*;
import java.io.*;
import java.io.IOException.*;

// Importa los paquetes de javax
import javax.naming.*;

public class JobProgramaPlusFichaBean extends FachadaJobProgramaPlusFicha
        implements Serializable {

    // Nombre JNDI del Data Source que esta clase requiere
    private static final String DATA_SOURCE_NAME = "CommerceAccess";
    // Clase helper usada para acceder a la base de datos
    private JDBCAccess jdbcAccess;

    // Metodo constructor por defecto sin parametros
    public JobProgramaPlusFichaBean() {

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

        String selectStr =
                " INSERT INTO tblJobProgramaPlusFicha           "
                + "            (idLocal                         "
                + "            ,idTipoOrden                     "
                + "            ,idOrden                         "
                + "            ,itemPadre                       "
                + "            ,idOperacion                     "
                + "            ,idEscala                        "
                + "            ,item                            "
                + "            ,vrEscala                        "
                + "            ,textoEscala                     "
                + "            ,fechaPrograma                   "
                + "            ,estadoPrograma                  "
                + "            ,fechaProceso                    "
                + "            ,idUsuario                       "
                + "            ,estado                          "
                + "            ,idOrdenPrograma                 "
                + "            ,idFicha                         "
                + "            ,referencia                      "
                + "            ,referenciaCliente               "
                + "            ,nombreReferencia)               "
                + " SELECT                                      "
                + getIdLocal() + " AS idLocal,                  "
                + getIdTipoOrden() + " AS idTipoOrden,          "
                + getIdOrden() + " AS idOrden,                  "
                + getItemPadre() + " AS itemPadre,              "
                + getIdOperacion() + " AS idOperacion           "
                + "       ,tblPlusFicha.idEscala                "
                + "       ,tblPlusFicha.item                    "
                + "       ,tblPlusFicha.vrEscala                "
                + "       ,tblPlusFicha.textoEscala,           '"
                + getFechaProgramaSqlServer() + "'              "
                + "              AS fechaPrograma               "
                + "         ,99 AS estadoPrograma,             '"
                + getFechaProcesoSqlServer() + "',              "
                + getIdUsuario() + " AS idUsuario               "
                + "       ,1 AS estado,                         "
                + getIdOrdenPrograma() + " AS idOrdenPrograma   "
                + "       ,tblPlusFicha.idFicha                 "
                + "       ,tblPlusFicha.referencia              "
                + "       ,tblPlusFicha.referenciaCliente       "
                + "       ,tblPlusFicha.nombreReferencia        "
                + " FROM tblPlusFicha                           "
                + " WHERE EXISTS                                "
                + "   ( SELECT tblDctosOrdenes.*                "
                + "     FROM   tblDctosOrdenes                  "
                + "     WHERE  tblDctosOrdenes.idLocal     =    "
                + getIdLocal() + "                              "
                + "     AND    tblDctosOrdenes.idTipoOrden =    "
                + getIdTipoOrden() + "                          "
                + "     AND    tblDctosOrdenes.idOrden     =    "
                + getIdOrden() + "                              "
                + "     AND    tblDctosOrdenes.idFicha     =    "
                + "                     tblPlusFicha.idFicha )  "
                + " AND tblPlusFicha.idOperacion           =    "
                + getIdOperacion();

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

    // ingresaAllOperacion
    public boolean ingresaAllOperacion() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                " INSERT INTO tblJobProgramaPlusFicha           "
                + "            (idLocal                         "
                + "            ,idTipoOrden                     "
                + "            ,idOrden                         "
                + "            ,itemPadre                       "
                + "            ,idOperacion                     "
                + "            ,idEscala                        "
                + "            ,item                            "
                + "            ,vrEscala                        "
                + "            ,textoEscala                     "
                + "            ,fechaPrograma                   "
                + "            ,estadoPrograma                  "
                + "            ,fechaProceso                    "
                + "            ,idUsuario                       "
                + "            ,estado                          "
                + "            ,idOrdenPrograma                 "
                + "            ,idFicha                         "
                + "            ,referencia                      "
                + "            ,referenciaCliente               "
                + "            ,nombreReferencia)               "
                + " SELECT                                      "
                + getIdLocal() + " AS idLocal,                  "
                + getIdTipoOrden() + " AS idTipoOrden,          "
                + getIdOrden() + " AS idOrden,                  "
                + getItemPadre() + " AS itemPadre               "
                + "       ,tblPlusFicha.idOperacion             "
                + "       ,tblPlusFicha.idEscala                "
                + "       ,tblPlusFicha.item                    "
                + "       ,tblPlusFicha.vrEscala                "
                + "       ,tblPlusFicha.textoEscala,           '"
                + getFechaProgramaSqlServer() + "'              "
                + "              AS fechaPrograma               "
                + "         ,1 AS estadoPrograma,              '"
                + getFechaProcesoSqlServer() + "',              "
                + getIdUsuario() + " AS idUsuario               "
                + "       ,1 AS estado,                         "
                + getIdOrdenPrograma() + " AS idOrdenPrograma   "
                + "       ,tblPlusFicha.idFicha                 "
                + "       ,tblPlusFicha.referencia              "
                + "       ,tblPlusFicha.referenciaCliente       "
                + "       ,tblPlusFicha.nombreReferencia        "
                + " FROM tblPlusFicha                           "
                + " INNER JOIN (                                "
                + " SELECT tblPlusFicha.idOperacion,            "
                + "        tblPlusFicha.idFicha                 "
                + " FROM tblPlusFicha                           "
                + " WHERE tblPlusFicha.idEscala                 "
                + "                  IN ( 610 , 620 , 1200 )    "
                + " AND tblPlusFicha.vrEscala                   "
                + "                      NOT IN ( 0, 777 )      "
                + " GROUP BY tblPlusFicha.idOperacion,          "
                + "        tblPlusFicha.idFicha )  AS tmpFIC    "
                + " ON tmpFIC.idOperacion      =                "
                + "              tblPlusFicha.idOperacion       "
                + " AND tmpFIC.idFicha         =                "
                + "                  tblPlusFicha.idFicha       "
                + " INNER JOIN tblDctosOrdenes                  "
                + " ON tblDctosOrdenes.idFicha =                "
                + "                  tblPlusFicha.idFicha       "
                + " WHERE tblDctosOrdenes.idLocal         =     "
                + getIdLocal() + "                              "
                + " AND tblDctosOrdenes.idTipoOrden       =     "
                + getIdTipoOrden() + "                          "
                + "AND    tblDctosOrdenes.idOrden         =     "
                + getIdOrden() ;

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

    // retiraFichaPrograma
    public boolean retiraFichaPrograma() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                " DELETE FROM tblJobProgramaPlusFicha         "
                + " WHERE  tblJobProgramaPlusFicha.idLocal  = "
                + getIdLocal() + "                            "
                + " AND tblJobProgramaPlusFicha.idTipoOrden = "
                + getIdTipoOrden() + "                        "
                + " AND tblJobProgramaPlusFicha.idOrden     = "
                + getIdOrden() + "                            "
                + " AND tblJobProgramaPlusFicha.itemPadre   = "
                + getItemPadre() ;

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

    // retiraOperacionPrograma
    public boolean retiraOperacionPrograma() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                " DELETE FROM tblJobProgramaPlusFicha         "
                + " WHERE  tblJobProgramaPlusFicha.idLocal  = "
                + getIdLocal() + "                            "
                + " AND tblJobProgramaPlusFicha.idTipoOrden = "
                + getIdTipoOrden() + "                        "
                + " AND tblJobProgramaPlusFicha.idOrden     = "
                + getIdOrden() + "                            "
                + " AND tblJobProgramaPlusFicha.itemPadre   = "
                + getItemPadre() + "                          "
                + " AND tblJobProgramaPlusFicha.idOperacion = "
                + getIdOperacion() ;

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

    // actualizaEstado
    public boolean actualizaEstado(int xEstadoProgramaNew) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                " UPDATE tblJobProgramaPlusFicha                     "
                + " SET    tblJobProgramaPlusFicha.estadoPrograma =  "
                + xEstadoProgramaNew + "                             "
                + " WHERE  tblJobProgramaPlusFicha.idLocal        =  "
                + getIdLocal() + "                                   "
                + " AND    tblJobProgramaPlusFicha.idTipoOrden    =  "
                + getIdTipoOrden() + "                               "
                + " AND    tblJobProgramaPlusFicha.idOrden        =  "
                + getIdOrden() + "                                   "
                + " AND    tblJobProgramaPlusFicha.itemPadre      =  "
                + getItemPadre() + "                                 "
                + " AND    tblJobProgramaPlusFicha.idOperacion    =  "
                + getIdOperacion() + "                               "
                + " AND    tblJobProgramaPlusFicha.estadoPrograma =  "
                + getEstadoPrograma();

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

    // actualizaPrograma
    public boolean actualizaPrograma() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                " UPDATE tblJobProgramaPlusFicha                     "
                + " SET  tblJobProgramaPlusFicha.idOrdenPrograma  =  "
                + getIdOrdenPrograma() + ",                          "
                + "      tblJobProgramaPlusFicha.cantidadPedida   =  "
                + getCantidadPedida() + ",                        "
                + "      tblJobProgramaPlusFicha.pesoPedido       =  "
                + getPesoPedido() + "                                "
                + " WHERE  tblJobProgramaPlusFicha.idLocal        =  "
                + getIdLocal() + "                                   "
                + " AND    tblJobProgramaPlusFicha.idTipoOrden    =  "
                + getIdTipoOrden() + "                               "
                + " AND    tblJobProgramaPlusFicha.idOrden        =  "
                + getIdOrden() + "                                   "
                + " AND    tblJobProgramaPlusFicha.itemPadre      =  "
                + getItemPadre() + "                                 "
                + " AND    tblJobProgramaPlusFicha.fechaPrograma  = '"
                + getFechaProgramaSqlServer() + "'                   "
                + " AND    tblJobProgramaPlusFicha.idOperacion    =  "
                + getIdOperacion() ;

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

    // modificaMaquina
    public boolean modificaMaquina() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                " UPDATE tblJobProgramaPlusFicha                     "
                + " SET  tblJobProgramaPlusFicha.vrEscala         =  "
                + getVrEscala() + "                                  "
                + " WHERE  tblJobProgramaPlusFicha.idLocal        =  "
                + getIdLocal() + "                                   "
                + " AND    tblJobProgramaPlusFicha.idTipoOrden    =  "
                + getIdTipoOrden() + "                               "
                + " AND    tblJobProgramaPlusFicha.idOrden        =  "
                + getIdOrden() + "                                   "
                + " AND    tblJobProgramaPlusFicha.itemPadre      =  "
                + getItemPadre() + "                                 "
                + " AND    tblJobProgramaPlusFicha.fechaPrograma  = '"
                + getFechaProgramaSqlServer() + "'                   "
                + " AND    tblJobProgramaPlusFicha.idOperacion    =  "
                + getIdOperacion() + "                               "
                + " AND    tblJobProgramaPlusFicha.idEscala       =  "
                + getIdEscala();

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

    // retiraPrograma
    public boolean retiraPrograma() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                " DELETE FROM tblJobProgramaPlusFicha                "
                + " WHERE tblJobProgramaPlusFicha.idLocal        =   "
                + getIdLocal() + "                                   "
                + " AND    tblJobProgramaPlusFicha.idTipoOrden    =  "
                + getIdTipoOrden() + "                               "
                + " AND    tblJobProgramaPlusFicha.idOrden        =  "
                + getIdOrden() + "                                   "
                + " AND    tblJobProgramaPlusFicha.itemPadre      =  "
                + getItemPadre() + "                                 "
                + " AND    tblJobProgramaPlusFicha.fechaPrograma  = '"
                + getFechaProgramaSqlServer() + "'                   "
                + " AND    tblJobProgramaPlusFicha.idOperacion    =  "
                + getIdOperacion() ;

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

    // retira
    public boolean retira() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                " DELETE FROM tblJobProgramaPlusFicha              "
                + " WHERE tblJobProgramaPlusFicha.estadoPrograma = "
                + getEstadoPrograma();

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

    // listaMaxima
    public int listaMaxima() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        int xIdOrdenMaxima = 0;
        Connection connection = null;

        String selectStr =
                " SELECT MAX(idOrdenPrograma) AS idOrdenMaxima     "
                + " FROM tblJobProgramaPlusFicha                   "
                + " WHERE tblJobProgramaPlusFicha.idLocal       =  "
                + getIdLocal() + "                                 "
                + " AND tblJobProgramaPlusFicha.fechaPrograma   = '"
                + getFechaProgramaSqlServer() + "'                 "
                + " AND tblJobProgramaPlusFicha.idOperacion     =  "
                + getIdOperacion() + "                             "
                + " AND tblJobProgramaPlusFicha.idEscala        =  "
                + getIdEscala() + "                                "
                + " AND tblJobProgramaPlusFicha.vrEscala        =  "
                + getVrEscala();

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            if (rs.next()) {

                xIdOrdenMaxima = rs.getInt("idOrdenMaxima");

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
            return xIdOrdenMaxima;
        }
    }
}
