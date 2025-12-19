package com.solucionesweb.losbeans.negocio;

// Importa la clase helper JDBCAccess que contiene los accesos a la base de datos
import com.solucionesweb.lasayudas.JDBCAccess;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaDctoOrdenProgreso;

// Importa los paquetes de java
import java.sql.*;

//import java.util.Date;
// Importa los paquetes de javax
import javax.naming.*;

public class DctoOrdenProgresoBean extends FachadaDctoOrdenProgreso {

    // Nombre JNDI del Data Source que esta clase requiere
    private static final String DATA_SOURCE_NAME = "CommerceAccess";
    // Clase helper usada para acceder a la base de datos
    private JDBCAccess jdbcAccess;

    // Metodo constructor por defecto sin parametros
    public DctoOrdenProgresoBean() {

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
        String selectStr
                = "INSERT INTO tblDctosOrdenesProgreso        "
                + "                      (idLocal,          "
                + "                      idTipoOrden,       "
                + "                      idOrden,           "
                + "                      item,              "
                + "                      idOperacion,       "
                + "                      idOperario,        "
                + "                      cantidadPerdida,   "
                + "                      cantidadTerminada, "
                + "                      pesoTerminado,     "
                + "                      pesoPerdido,       "
                + "                      fechaInicio,       "
                + "                      fechaFin,          "
                + "                      idCausa,           "
                + "                      estado,            "
                + "                      itemPadre,         "
                + "                      cantidadPedida,    "
                + "                      pesoPedido,        "
                + "                      idControl,         "
                + "                      idPlu,             "
                + "                      idControlTipo,     "
                + "                      observacion,       "
                + "                      idUsuario,         "
                + "                      cantidadPendiente, "
                + "                      vrCostoBaseMAT,    "
                + "                      vrCostoBaseCIF,    "
                + "                      vrCostoBaseMOD,    "
                + "                      pesoTara,          "
                + "                      idTipoCausa,       "
                + "                      idOrdenCruce,      "
                + "                      idDctoNitCC)       "
                + "VALUES (" + getIdLocal() + ","
                + getIdTipoOrden() + ","
                + getIdOrden() + ","
                + getItem() + ","
                + getIdOperacion() + ","
                + getIdOperario() + ","
                + getCantidadPerdida() + ","
                + getCantidadTerminada() + ","
                + getPesoTerminado() + ","
                + getPesoPerdido() + ","
                + "GETDATE() ,"
                + "GETDATE() ,"
                + getIdCausa() + ","
                + getEstado() + ","
                + getItemPadre() + ","
                + getCantidadPedida() + ","
                + getPesoPedido() + ","
                + getIdControl() + ","
                + getIdPlu() + ","
                + getIdControlTipo() + ",'"
                + getObservacion() + "',"
                + getIdUsuario() + ","
                + getCantidadPendiente() + ","
                + getVrCostoBaseMAT() + ","
                + getVrCostoBaseCIF() + ","
                + getVrCostoBaseMOD() + ","
                + getPesoTara() + ","
                + getIdTipoCausa() + ","
                + getIdOrdenCruce() + ",'"
                + getIdDctoNitCC() + "')";

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

    // ingresaAjuste
    public boolean ingresaAjuste(int xIdLocal,
            int xIdTipoOrden,
            int xIdOrden,
            int xItem,
            double xIdOperario,
            int xIdControl,
            int xIdControlTipo,
            double xIdUsuario) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        //
        String selectStr
                = " INSERT INTO tblDctosOrdenesProgreso  "
                + "            ([idLocal]              "
                + "            ,[idTipoOrden]          "
                + "            ,[idOrden]              "
                + "            ,[item]                 "
                + "            ,[idOperacion]          "
                + "            ,[idOperario]           "
                + "            ,[cantidadPerdida]      "
                + "            ,[cantidadTerminada]    "
                + "            ,[pesoPerdido]          "
                + "            ,[pesoTerminado]        "
                + "            ,[fechaInicio]          "
                + "            ,[fechaFin]             "
                + "            ,[idCausa]              "
                + "            ,[estado]               "
                + "            ,[itemPadre]            "
                + "            ,[cantidadPedida]       "
                + "            ,[pesoPedido]           "
                + "            ,[idControl]            "
                + "            ,[idPlu]                "
                + "            ,[idControlTipo]        "
                + "            ,[observacion]          "
                + "            ,[idUsuario]            "
                + "            ,[cantidadPendiente]    "
                + "            ,[vrCostoBaseMAT]       "
                + "            ,[vrCostoBaseCIF]       "
                + "            ,[vrCostoBaseMOD]       "
                + "            ,[pesoTara]             "
                + "            ,[idTipoCausa]          "
                + "            ,[idDctoNitCC]          "
                + "            ,[idOrdenCruce])        "
                + " SELECT                             "
                + xIdLocal + " AS idLocal,             "
                + xIdTipoOrden + " AS idTipoOrden,     "
                + xIdOrden + " AS idOrden,             "
                + xItem + " AS item                    "
                + "       ,idBodega AS idOperacion,    "
                + xIdOperario + " AS idOperario        "
                + "       ,0.0 AS cantidadPerdida      "
                + "                 ,cantidadTerminada "
                + "              ,0.0 AS pesoPerdido   "
                + "                 ,pesoTerminado     "
                + "       ,GETDATE() AS fechaInicio    "
                + "       ,GETDATE() AS fechaFin       "
                + "       ,0 AS idCausa                "
                + "       ,1 AS estado                 "
                + "       ,item AS itemPadre           "
                + "       ,0.0 AS cantidadPedida       "
                + "       ,0.0 AS pesoPedido,          "
                + xIdControl + " AS idControl          "
                + "       ,0 AS idPlu,                 "
                + xIdControlTipo + " AS idControlTipo  "
                + "       ,'' AS observacion,          "
                + xIdUsuario + " AS idUsuario          "
                + "       ,0.0 AS cantidadPendiente    "
                + "       ,0.0 AS vrCostoBaseMAT       "
                + "       ,0.0 AS vrCostoBaseCIF       "
                + "       ,0.0 AS vrCostoBaseMOD       "
                + "       ,0.0 AS pesoTara             "
                + "       ,0 AS idTipoCausa            "
                + "       ,'' AS idDctoNitCC           "
                + "       ,idOrden AS idOrdenCruce     "
                + " FROM [tblDctosOrdenesDetalle]      "
                + " WHERE idLocal     =                "
                + getIdLocal() + "                     "
                + " AND   idTipoOrden =                "
                + getIdTipoOrden() + "                 "
                + " AND   idOrden     =                "
                + getIdOrden();

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

    // ingresaJQuery
    public boolean ingresaJQuery() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr
                = "INSERT INTO tblDctosOrdenesProgreso        "
                + "                      (idLocal,          "
                + "                      idTipoOrden,       "
                + "                      idOrden,           "
                + "                      item,              "
                + "                      idOperacion,       "
                + "                      idOperario,        "
                + "                      cantidadPerdida,   "
                + "                      cantidadTerminada, "
                + "                      pesoTerminado,     "
                + "                      pesoPerdido,       "
                + "                      fechaInicio,       "
                + "                      fechaFin,          "
                + "                      idCausa,           "
                + "                      estado,            "
                + "                      itemPadre,         "
                + "                      cantidadPedida,    "
                + "                      pesoPedido,        "
                + "                      idControl,         "
                + "                      idPlu,             "
                + "                      idControlTipo,     "
                + "                      vrCostoBaseMAT,    "
                + "                      vrCostoBaseCIF,    "
                + "                      vrCostoBaseMOD,    "
                + "                      pesoTara,          "
                + "                      idTipoCausa,       "
                + "                      idOrdenCruce,      "
                + "                      idDctoNitCC)       "
                + "VALUES (" + getIdLocal() + ","
                + getIdTipoOrden() + ","
                + getIdOrden() + ","
                + getItem() + ","
                + getIdOperacion() + ","
                + getIdOperario() + ","
                + getCantidadPerdida() + ","
                + getCantidadTerminada() + ","
                + getPesoTerminado() + ","
                + getPesoPerdido() + ",'"
                + getFechaHoraInicioSqlServerJQuery() + "','"
                + getFechaHoraFinSqlServerJQuery() + "',"
                + getIdCausa() + ","
                + getEstado() + ","
                + getItemPadre() + ","
                + getCantidadPedida() + ","
                + getPesoPedido() + ","
                + getIdControl() + ","
                + getIdPlu() + ","
                + getIdControlTipo() + ","
                + getVrCostoBaseMAT() + ","
                + getVrCostoBaseCIF() + ","
                + getVrCostoBaseMOD() + ","
                + getPesoTara() + ","
                + getIdTipoCausa() + ","
                + getIdOrdenCruce() + ",'"
                + getIdDctoNitCC() + "')";

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

    // ingresaRealTime
    public boolean ingresaRealTime() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        //
        String selectStr
                = "INSERT INTO tblDctosOrdenesProgreso        "
                + "                      (idLocal,          "
                + "                      idTipoOrden,       "
                + "                      idOrden,           "
                + "                      item,              "
                + "                      idOperacion,       "
                + "                      idOperario,        "
                + "                      cantidadPerdida,   "
                + "                      cantidadTerminada, "
                + "                      pesoTerminado,     "
                + "                      pesoPerdido,       "
                + "                      fechaInicio,       "
                + "                      fechaFin,          "
                + "                      idCausa,           "
                + "                      estado,            "
                + "                      itemPadre,         "
                + "                      cantidadPedida,    "
                + "                      pesoPedido,        "
                + "                      idControl,         "
                + "                      idPlu,             "
                + "                      idControlTipo,     "
                + "                      idUsuario,         "
                + "                      observacion,       "
                + "                      cantidadPendiente, "
                + "                      vrCostoBaseMAT,    "
                + "                      vrCostoBaseCIF,    "
                + "                      vrCostoBaseMOD,    "
                + "                      pesoTara,          "
                + "                      idTipoCausa,       "
                + "                      idOrdenCruce,      "
                + "                      idDctoNitCC,      "
                + "                      idTurno,      "
                + "                      fechaProduccion,      "
                + "                      idMaquina   )    "
                + "VALUES (" + getIdLocal() + ","
                + getIdTipoOrden() + ","
                + getIdOrden() + ","
                + getItem() + ","
                + getIdOperacion() + ","
                + getIdOperario() + ","
                + getCantidadPerdida() + ","
                + getCantidadTerminada() + ","
                + getPesoTerminado() + ","
                + getPesoPerdido() + ",'"
                + getFechaHoraInicioSqlServer() + "',"
                + "GETDATE(),"
                + getIdCausa() + ","
                + getEstado() + ","
                + getItemPadre() + ","
                + getCantidadPedida() + ","
                + getPesoPedido() + ","
                + getIdControl() + ","
                + getIdPlu() + ","
                + getIdControlTipo() + ","
                + getIdUsuario() + ",'"
                + getObservacion() + "',"
                + getCantidadPendiente() + ","
                + getVrCostoBaseMAT() + ","
                + getVrCostoBaseCIF() + ","
                + getVrCostoBaseMOD() + ","
                + getPesoTara() + ","
                + getIdTipoCausa() + ","
                + getIdOrdenCruce()
                + ",'" + getIdDctoNitCC()
                + "'," + getIdTurno()
                + ",'" + getFechaProduccion()
                + "'," + getIdMaquina() + ")";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);
            System.out.println(selectStatement);
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

    // ingresaInicio
    public boolean ingresaInicio() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        //
        String selectStr
                = "INSERT INTO tblDctosOrdenesProgreso        "
                + "                      (idLocal,          "
                + "                      idTipoOrden,       "
                + "                      idOrden,           "
                + "                      item,              "
                + "                      idOperacion,       "
                + "                      idOperario,        "
                + "                      cantidadPerdida,   "
                + "                      cantidadTerminada, "
                + "                      pesoTerminado,     "
                + "                      pesoPerdido,       "
                + "                      tiempoPerdido,     "
                + "                      fechaInicio,       "
                + "                      fechaFin,          "
                + "                      idCausa,           "
                + "                      estado,            "
                + "                      itemPadre,         "
                + "                      cantidadPedida,    "
                + "                      pesoPedido,        "
                + "                      idControl,         "
                + "                      idPlu,             "
                + "                      idMaquina,     "
                + "                      idControlTipo,     "
                + "                      idUsuario,         "
                + "                      observacion,       "
                + "                      cantidadPendiente, "
                + "                      vrCostoBaseMAT,    "
                + "                      vrCostoBaseCIF,    "
                + "                      vrCostoBaseMOD,    "
                + "                      pesoTara,          "
                + "                      idTipoCausa,       "
                + "                      idOrdenCruce,      "
                + "                      idTurno,      "
                + "                      fechaProduccion,      "
                + "                      idDctoNitCC)       "
                + "VALUES (" + getIdLocal() + ","
                + getIdTipoOrden() + ","
                + getIdOrden() + ","
                + getItem() + ","
                + getIdOperacion() + ","
                + getIdOperario() + ","
                + getCantidadPerdida() + ","
                + getCantidadTerminada() + ","
                + getPesoTerminado() + ","
                + getPesoPerdido() + ","
                + getTiempoPerdido() + ","
                + "GETDATE(),"
                + "GETDATE(),"
                + getIdCausa() + ","
                + getEstado() + ","
                + getItemPadre() + ","
                + getCantidadPedida() + ","
                + getPesoPedido() + ","
                + getIdControl() + ","
                + getIdPlu() + ","
                + getIdMaquina() + ","
                + getIdControlTipo() + ","
                + getIdUsuario() + ",'"
                + getObservacion() + "',"
                + getCantidadPendiente() + ","
                + getVrCostoBaseMAT() + ","
                + getVrCostoBaseCIF() + ","
                + getVrCostoBaseMOD() + ","
                + getPesoTara() + ","
                + getIdTipoCausa() + ","
                + getIdOrdenCruce() + ","
                + getIdTurno() + ",'"
                + getFechaProduccion().replace("-", "") + "','"
                + getIdDctoNitCC() + "')";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);
          //  System.out.println(selectStr);
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

    // retira
    public boolean retira() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr
                = " DELETE FROM tblDctosOrdenesProgreso           "
                + " WHERE tblDctosOrdenesProgreso.idLocal     = "
                + getIdLocal() + "                              "
                + " AND   tblDctosOrdenesProgreso.idTipoOrden = "
                + getIdTipoOrden() + "                          "
                + " AND   tblDctosOrdenesProgreso.idOrden     = "
                + getIdOrden() + "                              "
                + " AND   tblDctosOrdenesProgreso.idOperacion = "
                + getIdOperacion() + "                          "
                + " AND   tblDctosOrdenesProgreso.item        = "
                + getItem() + "                                 "
                + " AND   tblDctosOrdenesProgreso.itemPadre   = "
                + getItemPadre();

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

    // actualizaOrdenExterna
    public boolean actualizaOrdenExterna() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        //
        String selectStr
                = " UPDATE tblDctosOrdenesProgreso                 "
                + "    SET tblDctosOrdenesProgreso.idControl =   "
                + getIdControl() + ",                            "
                + "    tblDctosOrdenesProgreso.observacion   =  '"
                + getObservacion() + "',                         "
                + "    tblDctosOrdenesProgreso.idUsuario     =   "
                + getIdUsuario() + ",                            "
                + " tblDctosOrdenesProgreso.cantidadPendiente =  "
                + getCantidadPendiente() + "                     "
                + " WHERE tblDctosOrdenesProgreso.idLocal    =   "
                + getIdLocal() + "                               "
                + " AND tblDctosOrdenesProgreso.idTipoOrden  =   "
                + getIdTipoOrden() + "                           "
                + " AND tblDctosOrdenesProgreso.idOrden      =   "
                + getIdOrden() + "                               "
                + " AND tblDctosOrdenesProgreso.idOperacion  =   "
                + getIdOperacion() + "                           "
                + " AND tblDctosOrdenesProgreso.idControlTipo=   "
                + getIdControlTipo() + "                         "
                + " AND tblDctosOrdenesProgreso.itemPadre    =   "
                + getItemPadre() + "                             "
                + " AND tblDctosOrdenesProgreso.idControl    = 0 ";

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

    // actualizaCantidadPendiente
    public boolean actualizaCantidadPendiente() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr
                = " UPDATE tblDctosOrdenesProgreso                     "
                + "  SET tblDctosOrdenesProgreso.cantidadPendiente = "
                + getCantidadPendiente() + "                         "
                + " WHERE tblDctosOrdenesProgreso.idLocal          = "
                + getIdLocal() + "                                   "
                + " AND tblDctosOrdenesProgreso.idTipoOrden        = "
                + getIdTipoOrden() + "                               "
                + " AND tblDctosOrdenesProgreso.idOrden            = "
                + getIdOrden() + "                                   "
                + " AND tblDctosOrdenesProgreso.idOperacion        = "
                + getIdOperacion() + "                               "
                + " AND tblDctosOrdenesProgreso.idControlTipo      = "
                + getIdControlTipo() + "                             "
                + " AND tblDctosOrdenesProgreso.itemPadre          = "
                + getItemPadre() + "                                 "
                + " AND tblDctosOrdenesProgreso.idControl          = "
                + getIdControl();

        System.out.println(selectStr);

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

    // Metodo maximoItem
    public int maximoItem() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        int xMaximoItem = 0;

        //
        Connection connection = null;

        String insertStr
                = " SELECT MAX(tblDctosOrdenesProgreso.item)       "
                + "                             AS maximoItem    "
                + " FROM tblDctosOrdenesProgreso                 "
                + " WHERE tblDctosOrdenesProgreso.idLocal      = "
                + getIdLocal() + "                               "
                + " AND   tblDctosOrdenesProgreso.idTipoOrden  = "
                + getIdTipoOrden() + "                           "
                + " AND   tblDctosOrdenesProgreso.idOrden      = "
                + getIdOrden() + "                               "
                + " AND   tblDctosOrdenesProgreso.itemPadre    = "
                + getItemPadre() + "                              "
                + " AND   tblDctosOrdenesProgreso.idOperacion  = "
                + getIdOperacion();

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

                //
                xMaximoItem = rs.getInt("maximoItem");

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
            return xMaximoItem;
        }
    }

    // Metodo maximoIdControExterno
    public int maximoIdControExterno() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        int xMaximoIdControExterno = 0;

        //
        Connection connection = null;

        String insertStr
                = " SELECT MAX(tblDctosOrdenesProgreso.idControl)   "
                + "                            AS maximoIdControl "
                + "   FROM tblDctosOrdenesProgreso                "
                + " WHERE tblDctosOrdenesProgreso.idLocal    =    "
                + getIdLocal() + "                                "
                + " AND tblDctosOrdenesProgreso.idTipoOrden  =    "
                + getIdTipoOrden() + "                            "
                + " AND tblDctosOrdenesProgreso.idControlTipo=    "
                + getIdControlTipo();

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

                //
                xMaximoIdControExterno = rs.getInt("maximoIdControl");

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
            return xMaximoIdControExterno;
        }
    }

    // Metodo listaTotalFCH
    public FachadaDctoOrdenProgreso listaTotalFCH() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        //
        FachadaDctoOrdenProgreso fachadaDctoOrdenProgreso
                = new FachadaDctoOrdenProgreso();

        //
        Connection connection = null;

        //
        String insertStr
                = " SELECT tblDctosOrdenesProgreso.idLocal                  "
                + "       ,tblDctosOrdenesProgreso.idTipoOrden            "
                + "       ,tblDctosOrdenesProgreso.idOrden                "
                + "       ,SUM(tblDctosOrdenesProgreso.cantidadPerdida)   "
                + "                                 AS cantidadPerdida    "
                + "       ,SUM(tblDctosOrdenesProgreso.cantidadTerminada) "
                + "                                 AS cantidadTerminada  "
                + "       ,SUM(tblDctosOrdenesProgreso.pesoPerdido)       "
                + "                                       AS pesoPerdido  "
                + "       ,SUM(tblDctosOrdenesProgreso.pesoTerminado)     "
                + "                                     AS pesoTerminado  "
                + " FROM tblDctosOrdenesProgreso                          "
                + " WHERE tblDctosOrdenesProgreso.idLocal      =          "
                + getIdLocal() + "                                        "
                + " AND   tblDctosOrdenesProgreso.idTipoOrden  =          "
                + getIdTipoOrden() + "                                    "
                + " AND   tblDctosOrdenesProgreso.idOrden      =          "
                + getIdOrden() + "                                        "
                + " AND   tblDctosOrdenesProgreso.idOperacion  =          "
                + getIdOperacion() + "                                    "
                + " AND   tblDctosOrdenesProgreso.itemPadre    =          "
                + getItemPadre() + "                                      "
                + "  AND  tblDctosOrdenesProgreso.idControlTipo != 2      "
                + " GROUP BY tblDctosOrdenesProgreso.idLocal              "
                + "         ,tblDctosOrdenesProgreso.idTipoOrden          "
                + "         ,tblDctosOrdenesProgreso.idOrden              ";

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

                //
                fachadaDctoOrdenProgreso.setIdLocal(rs.getInt("idLocal"));
                fachadaDctoOrdenProgreso.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaDctoOrdenProgreso.setIdOrden(rs.getInt("idOrden"));
                fachadaDctoOrdenProgreso.setCantidadPerdida(
                        rs.getDouble("cantidadPerdida"));
                fachadaDctoOrdenProgreso.setCantidadTerminada(
                        rs.getDouble("cantidadTerminada"));
                fachadaDctoOrdenProgreso.setPesoPerdido(
                        rs.getDouble("pesoPerdido"));
                fachadaDctoOrdenProgreso.setPesoTerminado(
                        rs.getDouble("pesoTerminado"));

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
            return fachadaDctoOrdenProgreso;
        }
    }

    // Metodo listaItemFCH
    public FachadaDctoOrdenProgreso listaItemFCH() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        //
        FachadaDctoOrdenProgreso fachadaDctoOrdenProgreso
                = new FachadaDctoOrdenProgreso();

        //
        Connection connection = null;

        //
        String insertStr
                = " SELECT tblDctosOrdenesProgreso.idLocal                  "
                + "       ,tblDctosOrdenesProgreso.idTipoOrden            "
                + "       ,tblDctosOrdenesProgreso.idOrden                "
                + "       ,SUM(tblDctosOrdenesProgreso.cantidadPerdida)   "
                + "                                 AS cantidadPerdida    "
                + "       ,SUM(tblDctosOrdenesProgreso.cantidadTerminada) "
                + "                                 AS cantidadTerminada  "
                + "       ,SUM(tblDctosOrdenesProgreso.pesoPerdido)       "
                + "                                       AS pesoPerdido  "
                + "       ,SUM(tblDctosOrdenesProgreso.pesoTerminado)     "
                + "                                     AS pesoTerminado  "
                + " FROM tblDctosOrdenesProgreso                          "
                + " WHERE tblDctosOrdenesProgreso.idLocal      =          "
                + getIdLocal() + "                                        "
                + " AND   tblDctosOrdenesProgreso.idTipoOrden  =          "
                + getIdTipoOrden() + "                                    "
                + " AND   tblDctosOrdenesProgreso.idOrden      =          "
                + getIdOrden() + "                                        "
                + " AND   tblDctosOrdenesProgreso.idOperacion  =          "
                + getIdOperacion() + "                                    "
                + " AND   tblDctosOrdenesProgreso.itemPadre    =          "
                + getItemPadre() + "                                      "
                + " AND   tblDctosOrdenesProgreso.item         =          "
                + getItem() + "                                           "
                + " GROUP BY tblDctosOrdenesProgreso.idLocal              "
                + "         ,tblDctosOrdenesProgreso.idTipoOrden          "
                + "         ,tblDctosOrdenesProgreso.idOrden              ";

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

                //
                fachadaDctoOrdenProgreso.setIdLocal(rs.getInt("idLocal"));
                fachadaDctoOrdenProgreso.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaDctoOrdenProgreso.setIdOrden(rs.getInt("idOrden"));
                fachadaDctoOrdenProgreso.setCantidadPerdida(
                        rs.getDouble("cantidadPerdida"));
                fachadaDctoOrdenProgreso.setCantidadTerminada(
                        rs.getDouble("cantidadTerminada"));
                fachadaDctoOrdenProgreso.setPesoPerdido(
                        rs.getDouble("pesoPerdido"));
                fachadaDctoOrdenProgreso.setPesoTerminado(
                        rs.getDouble("pesoTerminado"));

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
            return fachadaDctoOrdenProgreso;
        }
    }

    // listaUltimoIngresoFCH
    public FachadaDctoOrdenProgreso listaUltimoIngresoFCH() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        //
        FachadaDctoOrdenProgreso fachadaDctoOrdenProgreso
                = new FachadaDctoOrdenProgreso();

        //
        Connection connection = null;

        //
        String insertStr
                = " SELECT TOP 1 tblDctosOrdenesProgreso.idLocal     "
                + "       ,tblDctosOrdenesProgreso.idTipoOrden     "
                + "       ,tblDctosOrdenesProgreso.idOrden         "
                + "       ,tblDctosOrdenesProgreso.fechaFin        "
                + "       ,tblDctosOrdenesProgreso.idOperario      "
                + " FROM tblDctosOrdenesProgreso                   "
                + " WHERE tblDctosOrdenesProgreso.idLocal      =   "
                + getIdLocal() + "                                 "
                + " AND   tblDctosOrdenesProgreso.idTipoOrden  =   "
                + getIdTipoOrden() + "                             "
                + " AND   tblDctosOrdenesProgreso.idOrden      =   "
                + getIdOrden() + "                                 "
                + " AND   tblDctosOrdenesProgreso.idOperacion  =   "
                + getIdOperacion() + "                             "
                + " AND   tblDctosOrdenesProgreso.itemPadre    =   "
                + getItemPadre() + "                               "
                //                       + " AND   tblDctosOrdenesProgreso.idMaquina    =   "
                //                + getIdMaquina()
                + " ORDER BY tblDctosOrdenesProgreso.idLocal       "
                + "         ,tblDctosOrdenesProgreso.idTipoOrden   "
                + "         ,tblDctosOrdenesProgreso.idOrden       "
                + "         ,tblDctosOrdenesProgreso.item DESC     ";

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

                //
                fachadaDctoOrdenProgreso.setIdLocal(rs.getInt("idLocal"));
                fachadaDctoOrdenProgreso.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaDctoOrdenProgreso.setIdOrden(rs.getInt("idOrden"));
                fachadaDctoOrdenProgreso.setFechaFin(rs.getString("fechaFin"));
                fachadaDctoOrdenProgreso.setIdOperario(rs.getDouble("idOperario"));

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
            return fachadaDctoOrdenProgreso;
        }
    }

    // listaExternoFCH
    public FachadaDctoOrdenProgreso listaExternoFCH() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        //
        FachadaDctoOrdenProgreso fachadaDctoOrdenProgreso
                = new FachadaDctoOrdenProgreso();

        //
        Connection connection = null;

        //
        String insertStr
                = " SELECT TOP 1 tblDctosOrdenesProgreso.idLocal     "
                + "       ,tblDctosOrdenesProgreso.idTipoOrden     "
                + "       ,tblDctosOrdenesProgreso.idOrden         "
                + "       ,tblDctosOrdenesProgreso.fechaFin        "
                + "       ,tblDctosOrdenesProgreso.idOperario      "
                + " FROM tblDctosOrdenesProgreso                   "
                + " WHERE tblDctosOrdenesProgreso.idLocal      =   "
                + getIdLocal() + "                                 "
                + " AND   tblDctosOrdenesProgreso.idTipoOrden  =   "
                + getIdTipoOrden() + "                             "
                + " AND   tblDctosOrdenesProgreso.idOrden      =   "
                + getIdOrden() + "                                 "
                + " AND   tblDctosOrdenesProgreso.idOperacion  =   "
                + getIdOperacion() + "                             "
                + " AND   tblDctosOrdenesProgreso.itemPadre    =   "
                + getItemPadre() + "                               "
                + " AND tblDctosOrdenesProgreso.idControlTipo  = 2 ";

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

                //
                fachadaDctoOrdenProgreso.setIdLocal(rs.getInt("idLocal"));
                fachadaDctoOrdenProgreso.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaDctoOrdenProgreso.setIdOrden(rs.getInt("idOrden"));
                fachadaDctoOrdenProgreso.setFechaFin(rs.getString("fechaFin"));
                fachadaDctoOrdenProgreso.setIdOperario(rs.getDouble("idOperario"));

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
            return fachadaDctoOrdenProgreso;
        }
    }

    // validaPesoAdicionTerminado
    public boolean validaPesoAdicionTerminado(double xPorcentajeAdicion,
            double xPesoTerminado) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        //
        boolean xValidaPesoExcedido = false;

        //
        Connection connection = null;

        //
        String insertStr
                = " SELECT tblDctosOrdenesDetalle.*                "
                + " FROM tblDctosOrdenesDetalle                  "
                + " WHERE tblDctosOrdenesDetalle.idLocal      =  "
                + getIdLocal() + "                               "
                + " AND   tblDctosOrdenesDetalle.idTipoOrden  =  "
                + getIdTipoOrden() + "                           "
                + " AND   tblDctosOrdenesDetalle.idOrden      =  "
                + getIdOrden() + "                               "
                + " AND   tblDctosOrdenesDetalle.itemPadre    =  "
                + getItemPadre() + "                             "
                + " AND ( tblDctosOrdenesDetalle.pesoPedido   *  "
                + xPorcentajeAdicion + " ) >                     "
                + "     (tblDctosOrdenesDetalle.pesoTerminado +  "
                + xPesoTerminado + " ) ";

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

                //
                xValidaPesoExcedido = true;

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
            return xValidaPesoExcedido;
        }
    }

    // Metodo listaFCH
    public FachadaDctoOrdenProgreso listaFCH() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        //
        FachadaDctoOrdenProgreso fachadaDctoOrdenProgreso
                = new FachadaDctoOrdenProgreso();

        //
        Connection connection = null;

        //
        String insertStr
                = " SELECT tblDctosOrdenesProgreso.idLocal                  "
                + "       ,tblDctosOrdenesProgreso.idTipoOrden            "
                + "       ,tblDctosOrdenesProgreso.idOrden                "
                + "       ,SUM(tblDctosOrdenesProgreso.cantidadPerdida)   "
                + "                                 AS cantidadPerdida    "
                + "       ,SUM(tblDctosOrdenesProgreso.cantidadTerminada) "
                + "                                 AS cantidadTerminada  "
                + "       ,SUM(tblDctosOrdenesProgreso.pesoPerdido)       "
                + "                                       AS pesoPerdido  "
                + "       ,SUM(tblDctosOrdenesProgreso.pesoTerminado)     "
                + "                                     AS pesoTerminado  "
                + " FROM tblDctosOrdenesProgreso                          "
                + " WHERE tblDctosOrdenesProgreso.idLocal      =          "
                + getIdLocal() + "                                        "
                + " AND   tblDctosOrdenesProgreso.idTipoOrden  =          "
                + getIdTipoOrden() + "                                    "
                + " AND   tblDctosOrdenesProgreso.idOrden      =          "
                + getIdOrden() + "                                        "
                + " AND   tblDctosOrdenesProgreso.idOperacion  =          "
                + getIdOperacion() + "                                    "
                + " AND   tblDctosOrdenesProgreso.item         =          "
                + getItem() + "                                           "
                + " GROUP BY tblDctosOrdenesProgreso.idLocal              "
                + "         ,tblDctosOrdenesProgreso.idTipoOrden          "
                + "         ,tblDctosOrdenesProgreso.idOrden              ";

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

                //
                fachadaDctoOrdenProgreso.setIdLocal(rs.getInt("idLocal"));
                fachadaDctoOrdenProgreso.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaDctoOrdenProgreso.setIdOrden(rs.getInt("idOrden"));
                fachadaDctoOrdenProgreso.setCantidadPerdida(
                        rs.getDouble("cantidadPerdida"));
                fachadaDctoOrdenProgreso.setCantidadTerminada(
                        rs.getDouble("cantidadTerminada"));
                fachadaDctoOrdenProgreso.setPesoPerdido(
                        rs.getDouble("pesoPerdido"));
                fachadaDctoOrdenProgreso.setPesoTerminado(
                        rs.getDouble("pesoTerminado"));

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
            return fachadaDctoOrdenProgreso;
        }
    }
}
