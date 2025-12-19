package com.solucionesweb.losbeans.negocio;

// Importa la clase helper JDBCAccess que contiene los accesos a la base de datos
import com.solucionesweb.lasayudas.JDBCAccess;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaDctoOrdenDetalleBean;

// Importa los paquetes de java
import java.sql.*;
import java.io.*;
import java.util.*;
import java.io.IOException.*;

//
import org.kxml.*;
import org.kxml.parser.*;

// Importa los paquetes de javax
import javax.naming.*;

public class DctoOrdenDetalleTrasladoTx extends FachadaDctoOrdenDetalleBean
        implements Serializable {

    // Nombre JNDI del Data Source que esta clase requiere
    private static final String DATA_SOURCE_NAME = "CommerceAccess";
    // Clase helper usada para acceder a la base de datos
    private JDBCAccess jdbcAccess;
    //
    FachadaDctoOrdenDetalleBean fachadaDetalle;

    // Metodo constructor por defecto sin parametros
    public DctoOrdenDetalleTrasladoTx() {

        // Al instanciar el Bean establece la conexion a la base de datos
        jdbcAccess = new JDBCAccess(DATA_SOURCE_NAME);

    }

    // ingresa
    public boolean actualiza() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        //
        String selectStr =
 "   UPDATE tblDctosOrdenesDetalle                   "
+"   SET tblDctosOrdenesDetalle.CANTIDAD =           "
+    getCantidad()+"                                 "
+"      ,tblDctosOrdenesDetalle.VRCOSTO =            "
+    getVrCosto()+"                                  "
+"      ,tblDctosOrdenesDetalle.vrCostoNegociado =   "
+    getVrCostoNegociado()+"                         "
+"      ,tblDctosOrdenesDetalle.vrCostoResurtido =   "
+    getVrCostoResurtido()+"                         "
+"      ,tblDctosOrdenesDetalle.vrCostoIND =         "
+    getVrCostoIND()+"                               "
+"      ,tblDctosOrdenesDetalle.idOrdenOrigen =      "
+    getIdOrden()+"                                  "
+"      ,tblDctosOrdenesDetalle.idLocalOrigen =      "
+    getIdLocal()+"                                  "
+"      ,tblDctosOrdenesDetalle.idTipoOrdenOrigen =  "
+    getIdTipoOrden()+"                              "
+" WHERE tblDctosOrdenesDetalle.idLocal =            "
+    getIdLocalOrigen()+"                            "
+" AND   tblDctosOrdenesDetalle.idOrden =            "
+    getIdOrdenOrigen()+"                            "
+" AND tblDctosOrdenesDetalle.idTipoOrden=           " 
+    getIdTipoOrdenOrigen()+"                        "
+" AND tblDctosOrdenesDetalle.idPlu =                "
+    getIdPlu()+"                                    ";


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
    public void traverse_DctoOrdenDetalleTrasladoTx(XmlParser parser,
            String indent) throws Exception {

        //
        boolean leave = false;

        //
        String IDLOCAL = new String();
        String IDTIPOORDEN = new String();
        String IDORDEN = new String();
        String IDPLU = new String();
        String CANTIDAD = new String();       
        String VRCOSTO = new String();       
        String vrCostoNegociado = new String();        
        String vrCostoResurtido = new String();       
        String vrCostoIND = new String();
        String idOrdenOrigen = new String();
        String idLocalOrigen = new String();
        String idTipoOrdenOrigen = new String();


        do {
            ParseEvent event = parser.read();
            ParseEvent pe;

            //
            switch (event.getType()) {

                // For example, <CxC>
                case Xml.START_TAG:


                     if ("IDLOCAL".equals(event.getName())) {
                        pe = parser.read();
                        IDLOCAL = pe.getText();

                        this.setIdLocal(IDLOCAL);

                    }

                    // Pick up clave for display
                    if ("IDTIPOORDEN".equals(event.getName())) {
                        pe = parser.read();
                        IDTIPOORDEN = pe.getText();

                        this.setIdTipoOrden(IDTIPOORDEN);

                    }

                    // Pick up clave for display
                    if ("IDORDEN".equals(event.getName())) {
                        pe = parser.read();
                        IDORDEN = pe.getText();

                        this.setIdOrden(IDORDEN);

                    }

                     // Pick up clave for display
                    if ("IDPLU".equals(event.getName())) {
                        pe = parser.read();
                        IDPLU = pe.getText();

                        this.setIdPlu(IDPLU);

                    }

                    // Pick up clave for display
                    if ("CANTIDAD".equals(event.getName())) {
                        pe = parser.read();
                        CANTIDAD = pe.getText();

                        this.setCantidad(CANTIDAD);

                    }

                    // Pick up clave for display
                    if ("VRCOSTO".equals(event.getName())) {
                        pe = parser.read();
                        VRCOSTO = pe.getText();

                        this.setVrCosto(VRCOSTO);
 
                    }

                    // Pick up clave for display
                    if ("vrCostoNegociado".equals(event.getName())) {
                        pe = parser.read();
                        vrCostoNegociado = pe.getText();

                        this.setVrCostoNegociado(vrCostoNegociado);

                    }

                      // Pick up clave for display
                    if ("vrCostoResurtido".equals(event.getName())) {
                        pe = parser.read();
                        vrCostoResurtido = pe.getText();

                        this.setVrCostoResurtido(vrCostoResurtido);

                    }

                    // Pick up clave for display
                    if ("vrCostoIND".equals(event.getName())) {
                        pe = parser.read();
                        vrCostoIND = pe.getText();

                        this.setVrCostoIND(vrCostoIND);

                    }


                    // Pick up clave for display
                    if ("idOrdenOrigen".equals(event.getName())) {
                        pe = parser.read();
                        idOrdenOrigen = pe.getText();

                        this.setIdOrdenOrigen(idOrdenOrigen);

                        }

                    // Pick up clave for display
                    if ("idLocalOrigen".equals(event.getName())) {
                        pe = parser.read();
                        idLocalOrigen = pe.getText();

                        this.setIdLocalOrigen(idLocalOrigen);

                    }

                    if ("idTipoOrdenOrigen".equals(event.getName())) {
                        pe = parser.read();
                        idTipoOrdenOrigen = pe.getText();

                        this.setIdTipoOrdenOrigen(idTipoOrdenOrigen);

                        //
                        this.actualiza();

                    }

                    // recursion call for each <tag></tag>
                    traverse_DctoOrdenDetalleTrasladoTx(parser,"");
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
