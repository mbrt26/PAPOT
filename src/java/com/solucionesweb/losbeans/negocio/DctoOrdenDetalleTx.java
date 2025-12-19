package com.solucionesweb.losbeans.negocio;

// Importa la clase helper JDBCAccess que contiene los accesos a la base de datos
import com.solucionesweb.lasayudas.JDBCAccess;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaDctoOrdenDetalleBean;

// Importa los paquetes de java
import java.sql.*;
import java.io.*;
import java.io.IOException.*;

//
import org.kxml.*;
import org.kxml.parser.*;

// Importa los paquetes de javax
import javax.naming.*;

public class DctoOrdenDetalleTx extends FachadaDctoOrdenDetalleBean
        implements Serializable {

    // Nombre JNDI del Data Source que esta clase requiere
    private static final String DATA_SOURCE_NAME = "CommerceAccess";
    // Clase helper usada para acceder a la base de datos
    private JDBCAccess jdbcAccess;
    //
    FachadaDctoOrdenDetalleBean fachadaDetalle;

    // Metodo constructor por defecto sin parametros
    public DctoOrdenDetalleTx() {

        // Al instanciar el Bean establece la conexion a la base de datos
        jdbcAccess = new JDBCAccess(DATA_SOURCE_NAME);

    }

    // ingresa
    public boolean ingresa(String xFechaInicial,
                           int xDiasHistoria) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        //
        String selectStr =
                "INSERT INTO tmpResurtido (idLocal,   "
                + "                      idTipoOrden, "
                + "                      idPlu,       "
                + "                      cantidad,    "
                + "                      existencia,  "
                + "                      idLog,       "
                + "                      fechaInicial,"
                + "                     diasHistoria) "
                + "VALUES ( " + getIdLocal() + ","
                + getIdTipoOrden() + ","
                + getIdPlu() + ","
                + getCantidad() + ","
                + getExistencia() + ","
                + getIdLog() + ",'"
                + xFechaInicial + "',"
                + xDiasHistoria + ")";

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

    // retiraLog
    public boolean retira() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        //
        String selectStr =
                " DELETE FROM tmpResurtido " ;

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

// traverse_DctoOrdenDetalleTx
    public void traverse_DctoOrdenDetalleTx(XmlParser parser,
            String indent,
            String xFechaInicial,
            int xDiasHistoria) throws Exception {

        //
        boolean leave = false;

        //
        String idLocal = new String();
        String idTipoOrden = new String();
        String idPlu = new String();
        String cantidad = new String();
        String existencia = new String();        
        String idLog = new String();

        do {
            ParseEvent event = parser.read();
            ParseEvent pe;

            switch (event.getType()) {

                // For example, <CxC>
                case Xml.START_TAG:

                    // see API doc of StartTag for more access methods
                    // Pick up idagenda for display
                    if ("idLocal".equals(event.getName())) {
                        pe = parser.read();
                        idLocal = pe.getText();

                    }

                    // Pick up clave for display
                    if ("idTipoOrden".equals(event.getName())) {
                        pe = parser.read();
                        idTipoOrden = pe.getText();

                    }

                    // Pick up clave for display
                    if ("idPlu".equals(event.getName())) {
                        pe = parser.read();
                        idPlu = pe.getText();

                    }

                    // Pick up clave for display
                    if ("cantidad".equals(event.getName())) {
                        pe = parser.read();
                        cantidad = pe.getText();

                    }

                    // Pick up clave for display
                    if ("existencia".equals(event.getName())) {
                        pe = parser.read();
                        existencia = pe.getText();

                    }

                    // Pick up clave for display
                    if ("idLog".equals(event.getName())) {
                        pe = parser.read();
                        idLog = pe.getText();

                        
                        //
                        this.setIdLocal(idLocal);
                        this.setIdTipoOrden(idTipoOrden);
                        this.setIdPlu(idPlu);
                        this.setCantidad(cantidad);
                        this.setExistencia(existencia);
                        this.setIdLog(idLog);

                        //
                        this.ingresa(xFechaInicial,
                                     xDiasHistoria);

                    }

                    // recursion call for each <tag></tag>
                    traverse_DctoOrdenDetalleTx(parser, 
                                                "",
                                                xFechaInicial,
                                                xDiasHistoria);
                    break;

                // For example </title?
                case Xml.END_TAG:
                    leave = true;
                    break;

                // For example </rss>
                case Xml.END_DOCUMENT:

                    leave = true;
                    break;

                // For example, the text between tags
                case Xml.TEXT:
                    break;
                case Xml.WHITESPACE:
                    break;
                default:
            }
        } while (!leave);
    }
}
