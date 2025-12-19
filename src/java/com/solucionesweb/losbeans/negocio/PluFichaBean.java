package com.solucionesweb.losbeans.negocio;

// Importa la clase helper JDBCAccess que contiene los accesos a la base de datos
import com.solucionesweb.lasayudas.JDBCAccess;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaPluFicha;

// Importa los paquetes de java
import java.sql.*;
import java.io.*;
import java.io.IOException.*;

// Importa los paquetes de javax
import javax.naming.*;

public class PluFichaBean extends FachadaPluFicha implements Serializable {

    // Nombre JNDI del Data Source que esta clase requiere
    private static final String DATA_SOURCE_NAME = "CommerceAccess";
    // Clase helper usada para acceder a la base de datos
    private JDBCAccess jdbcAccess;

    // Metodo constructor por defecto sin parametros
    public PluFichaBean() {

        // Al instanciar el Bean establece la conexion a la base de datos
        jdbcAccess = new JDBCAccess(DATA_SOURCE_NAME);
    }

    // ingresa
    public boolean ingresa() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        //
        Connection connection = null;

        //
        String selectStr =
                " INSERT INTO tblPlusFicha        "
                + "            (idPlu             "
                + "            ,referenciaCliente "
                + "            ,idCliente         "
                + "            ,idOperacion     "
                + "            ,nombreReferencia  "
                + "            ,idEscala          "
                + "            ,item              "
                + "            ,vrEscala          "
                + "            ,textoEscala       "
                + "            ,estado            "
                + "            ,idFicha)          "
                + " VALUES ( 		          "
                + getIdPlu() + ",'"
                + getReferenciaCliente() + "','"
                + getIdCliente() + "',"
                + getIdOperacion() + ",'"
                + getReferencia() + "',"
                + getIdEscala() + ","
                + getItem() + ","
                + getVrEscala() + ",'"
                + getTextoEscala() + "',"
                + getEstado() + ","
                + getIdFicha() + ")";

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

    // actualizaVrEscala
    public boolean actualizaVrEscala() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        //
        Connection connection = null;

        //
        String selectStr =
                " UPDATE tblPlusFicha                    "
                + "    SET tblPlusFicha.vrEscala       = "
                + getVrEscala() + "                      "
                + " WHERE tblPlusFicha.idCliente       ='"
                + getIdCliente() + "'                    "
                + " AND tblPlusFicha.idFicha           = "
                + getIdFicha() + "                       "
                + " AND tblPlusFicha.idOperacion       = "
                + getIdOperacion() + "                   "
                + " AND tblPlusFicha.idEscala          = "
                + getIdEscala() + "                      "
                + " AND tblPlusFicha.item              = "
                + getItem();

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

    // actualizaTextoEscala
    public boolean actualizaTextoEscala() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        //
        Connection connection = null;

        //
        String selectStr =
                " UPDATE tblPlusFicha                    "
                + "    SET tblPlusFicha.textoEscala    ='"
                + getTextoEscala() + "'                  "
                + " WHERE tblPlusFicha.idCliente       ='"
                + getIdCliente() + "'                    "
                + " AND tblPlusFicha.idFicha           = "
                + getIdFicha() + "                       "
                + " AND tblPlusFicha.idOperacion       = "
                + getIdOperacion() + "                   "
                + " AND tblPlusFicha.idEscala          = "
                + getIdEscala() + "                      "
                + " AND tblPlusFicha.item              = "
                + getItem();

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

    // ingresaComplemento
    public boolean ingresaComplemento() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        //
        Connection connection = null;

        //
        String selectStr =
                " INSERT INTO tblPlusFicha                  "
                + "            (idCliente                   "
                + "            ,referenciaCliente           "
                + "            ,idOperacion                 "
                + "            ,idPlu                       "
                + "            ,nombreReferencia            "
                + "            ,idEscala                    "
                + "            ,item                        "
                + "            ,vrEscala                    "
                + "            ,textoEscala                 "
                + "            ,estado                      "
                + "            ,idFicha)                    "
                + " SELECT                                 '"
                + getIdCliente() + "' AS idCliente,        '"
                + getReferenciaCliente() + "'               "
                + "                    AS referenciaCliente,"
                + " tblJobEscalaOperacion.idOperacion,      "
                + getIdPlu() + " AS idPlu,                 '"
                + getReferencia() + "'                      "
                + "                    AS nombreReferencia, "
                + " tblJobEscalaOperacion.idEscala,         "
                + " 1 AS item ,                             "
                + " 0 AS vrEscala,                          "
                + " '' AS textoEscala,                      "
                + " 1 AS estado,                            "
                + getIdFicha() + "                          "
                + " FROM tblJobEscalaOperacion              "
                + " WHERE tblJobEscalaOperacion.idOperacion "
                + " BETWEEN 2 AND 998 ";

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

    // ingresaComplementoProducto
    public boolean ingresaComplementoProducto() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        //
        Connection connection = null;

        //
        String selectStr =
                " INSERT INTO tblPlusFicha                  "
                + "            (idCliente                   "
                + "            ,referenciaCliente           "
                + "            ,idOperacion                 "
                + "            ,idPlu                       "
                + "            ,nombreReferencia            "
                + "            ,idEscala                    "
                + "            ,item                        "
                + "            ,vrEscala                    "
                + "            ,textoEscala                 "
                + "            ,estado                      "
                + "            ,idFicha)                    "
                + " SELECT                                 '"
                + getIdCliente() + "' AS idCliente,        '"
                + getReferenciaCliente() + "'               "
                + "                    AS referenciaCliente,"
                + " tblJobEscalaOperacion.idOperacion,      "
                + getIdPlu() + " AS idPlu,                 '"
                + getReferencia() + "'                      "
                + "                    AS nombreReferencia, "
                + " tblJobEscalaOperacion.idEscala,         "
                + " 1 AS item ,                             "
                + " 1 AS vrEscala,                          "
                + " '' AS textoEscala,                      "
                + " 1 AS estado,                            "
                + getIdFicha() + "                          "
                + " FROM tblJobEscalaOperacion              "
                + " WHERE tblJobEscalaOperacion.idOperacion "
                + " IN (999)                                "
                + " AND tblJobEscalaOperacion.idEscala = 620";


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

    // actualizaProduccion
    public boolean actualizaProduccion() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        //
        Connection connection = null;

        //
        String selectStr =
                " UPDATE tblPlusFicha               "
                + " SET  tblPlusFicha.vrEscala = 1  "
                + " WHERE tblPlusFicha.idFicha   =  "
                + getIdFicha() + "                  "
                + " AND tblPlusFicha.idEscala       "
                + "                   IN (620,1200) ";

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

    // actualizaReferencia
    public boolean actualizaReferencia() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        //
        Connection connection = null;

        //
        String selectStr =
                " UPDATE tblPlusFicha               "
                + " SET  tblPlusFicha.referencia = '"
                + getReferencia() + "'              "
                + " WHERE tblPlusFicha.idFicha   =  "
                + getIdFicha();

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

    // actualizaReferenciaCliente
    public boolean actualizaReferenciaCliente() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        //
        Connection connection = null;

        //
        String selectStr =
                " UPDATE tblPlusFicha                      "
                + " SET  tblPlusFicha.referenciaCliente = '"
                + getReferenciaCliente() + "'              "
                + " WHERE tblPlusFicha.idFicha   =         "
                + getIdFicha();

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

    // eliminaReferenciaCliente
    public boolean eliminaReferenciaCliente() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        //
        Connection connection = null;

        //
        String selectStr =
                " DELETE FROM tblPlusFicha         "
                + " WHERE tblPlusFicha.idFicha   = "
                + getIdFicha();

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

    // actualizaOperacion
    public boolean actualizaOperacion() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        //
        Connection connection = null;

        //
        String selectStr =
                " UPDATE tblPlusFicha                  "
                + " SET  tblPlusFicha.vrEscala   = 888 "
                + " WHERE tblPlusFicha.vrEscala  = 0   "
                + " AND   tblPlusFicha.idEscala  = 610 "
                + " AND   tblPlusFicha.idFicha   =     "
                + getIdFicha() ;

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

    // Metodo maximaFicha
    public int maximaFicha() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        int xIdMaximaFicha = 0;

        Connection connection = null;

        String selectStr =
                " SELECT MAX(tblPlusFicha.idFicha) "
                + "                  AS maxIdFicha "
                + " FROM tblPlusFicha  ";

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
                xIdMaximaFicha = rs.getInt("maxIdFicha");

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
            return xIdMaximaFicha;

        }
    }

    // Metodo validaReferenciaCliente
    public boolean validaReferenciaCliente() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        boolean xValida = false;

        Connection connection = null;

        String selectStr =
                " SELECT tblPlusFicha.*                     "
                + " FROM tblPlusFicha                       "
                + " WHERE tblPlusFicha.idCliente =         '"
                + getIdCliente() + "'                       "
                + " AND   tblPlusFicha.referenciaCliente = '"
                + getReferenciaCliente() + "'" ;

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
                xValida = true;

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
            return xValida;

        }
    }
}
