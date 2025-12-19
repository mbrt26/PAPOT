package com.solucionesweb.losbeans.colaboraciones;

// Importa la clase helper JDBCAccess que contiene los accesos a la base de datos
import com.solucionesweb.lasayudas.JDBCAccess;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaOT;

// Importa los paquetes de java
import java.sql.*;
import java.io.*;
//import java.util.Date;
import java.io.IOException.*;

// Importa los paquetes de javax
import javax.naming.*;

// Importa la interface de las constantes
import com.solucionesweb.losbeans.utilidades.IConstantes;
import java.util.ArrayList;
import java.util.List;

public class ColaboraOT extends FachadaOT
        implements Serializable, IConstantes {

    // Nombre JNDI del Data Source que esta clase requiere
    private static final String DATA_SOURCE_NAME = "CommerceAccess";
    // Clase helper usada para acceder a la base de datos
    private JDBCAccess jdbcAccess;

    // Metodo constructor por defecto sin parametros
    public ColaboraOT() {

        // Al instanciar el Bean establece la conexion a la base de datos
        jdbcAccess = new JDBCAccess(DATA_SOURCE_NAME);

    }

    // Metodo listaPedidoUnOT_TipoEscala_1
    public FachadaOT listaPedidoUnOT_TipoEscala_1() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        //
        FachadaOT fachadaBean = new FachadaOT();

        Connection connection = null;

        String insertStr
                = "    SELECT tblPlusFicha.idOperacion,             "
                + "          tblPlusFicha.idEscala,               "
                + "          tblPlusFicha.nombreReferencia,       "
                + "    	   tblPlusFicha.vrEscala,                 "
                + "    	   STR(tblPlusFicha.vrEscala)             "
                + "                     AS textoEscala,           "
                + "    	   tblJobEscala.nombreEscala,             "
                + "          tblJobEscala.idTipoEscala            "
                + "    FROM   tblPlusFicha                        "
                + "    INNER JOIN tblJobEscala                    "
                + "    ON tblPlusFicha.idEscala =                 "
                + "                   tblJobEscala.idEscala       "
                + "    INNER JOIN tblJobEscalaOperacion           "
                + "    ON tblJobEscalaOperacion.idEscala     =    "
                + "                     tblPlusFicha.idEscala     "
                + "    AND tblJobEscalaOperacion.idOperacion =    "
                + "                   tblPlusFicha.idOperacion    "
                + "    INNER JOIN tblDctosOrdenes                 "
                + "    ON    tblDctosOrdenes.idFicha    =         "
                + "                 tblPlusFicha.idFicha          "
                + "    WHERE tblPlusFicha.idOperacion       =     "
                + getIdOperacion() + "                            "
                + "    AND    tblDctosOrdenes.numeroOrden   =     "
                + getNumeroOrden() + "                            "
                + "    AND    tblJobEscala.idTipoEscala     = 1   ";

        PreparedStatement sentenciaSQL = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            sentenciaSQL = connection.prepareStatement(insertStr);

            // Obtiene el resultset
            ResultSet rs = sentenciaSQL.executeQuery();

            while (rs.next()) {

                //
                fachadaBean.setIdEscala(rs.getInt("idEscala"));
                fachadaBean.setVrEscala(rs.getDouble("vrEscala"));
                fachadaBean.setTextoEscala(rs.getString("textoEscala"));
                fachadaBean.setNombreEscala(rs.getString("nombreEscala"));
                fachadaBean.setNombreReferencia(rs.getString("nombreReferencia"));

                //
                fachadaBean.setNombreReferencia(rs.getString("nombreReferencia"));

                //
                if (fachadaBean.getIdEscala() == 501) {

                    //
                    fachadaBean.setAnchoPedido(rs.getString("vrEscala"));

                }

                if (fachadaBean.getIdEscala() == 502) {

                    //
                    fachadaBean.setLargoPedido(rs.getString("vrEscala"));

                }

                if (fachadaBean.getIdEscala() == 503) {

                    //
                    fachadaBean.setCalibrePedido(rs.getString("vrEscala"));

                }

                if (fachadaBean.getIdEscala() == 517) {

                    //
                    fachadaBean.setCostoFotopolimeroPedido(rs.getString("vrEscala"));

                }

                if (fachadaBean.getIdEscala() == 518) {

                    //
                    fachadaBean.setPorcentajeFotopolimeroClientePedido(rs.getString("vrEscala"));

                }

                if (fachadaBean.getIdEscala() == 519) {

                    //
                    fachadaBean.setPorcentajeFotopolimeroUnionPedido(rs.getString("vrEscala"));

                }

                if (fachadaBean.getIdEscala() == 805) {

                    //
                    fachadaBean.setTipoEmbobinadoPedido(rs.getString("vrEscala"));

                }

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
            // conexion
            jdbcAccess.cleanup(connection, sentenciaSQL, null);
            return fachadaBean;
        }
    }

    // Metodo listaExtrusionUnOT_TipoEscala_1
    public FachadaOT listaExtrusionUnOT_TipoEscala_1() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        //
        FachadaOT fachadaBean = new FachadaOT();

        Connection connection = null;

        String insertStr
                = "    SELECT tblPlusFicha.idOperacion,             "
                + "          tblPlusFicha.idEscala,               "
                + "    	   tblPlusFicha.vrEscala,                 "
                + "    	   STR(tblPlusFicha.vrEscala)             "
                + "                     AS textoEscala,           "
                + "    	   tblJobEscala.nombreEscala,             "
                + "          tblJobEscala.idTipoEscala            "
                + "    FROM   tblPlusFicha                        "
                + "    INNER JOIN tblJobEscala                    "
                + "    ON tblPlusFicha.idEscala =                 "
                + "                   tblJobEscala.idEscala       "
                + "    INNER JOIN tblJobEscalaOperacion           "
                + "    ON tblJobEscalaOperacion.idEscala     =    "
                + "                     tblPlusFicha.idEscala     "
                + "    AND tblJobEscalaOperacion.idOperacion =    "
                + "                   tblPlusFicha.idOperacion    "
                + "    INNER JOIN tblDctosOrdenes                 "
                + "    ON    tblDctosOrdenes.idFicha    =         "
                + "                 tblPlusFicha.idFicha          "
                + "    WHERE tblPlusFicha.idOperacion       =     "
                + getIdOperacion() + "                            "
                + "    AND    tblDctosOrdenes.numeroOrden   =     "
                + getNumeroOrden() + "                            "
                + "    AND    tblJobEscala.idTipoEscala     = 1   ";

        PreparedStatement sentenciaSQL = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            sentenciaSQL = connection.prepareStatement(insertStr);

            // Obtiene el resultset
            ResultSet rs = sentenciaSQL.executeQuery();

            while (rs.next()) {

                //
                fachadaBean.setIdEscala(rs.getInt("idEscala"));
                fachadaBean.setVrEscala(rs.getDouble("vrEscala"));
                fachadaBean.setTextoEscala(rs.getString("textoEscala"));
                fachadaBean.setNombreEscala(rs.getString("nombreEscala"));

                //
                if (fachadaBean.getIdEscala() == 501) {

                    //
                    fachadaBean.setAnchoExtrusion(rs.getString("vrEscala"));

                }

                if (fachadaBean.getIdEscala() == 503) {

                    //
                    fachadaBean.setCalibreExtrusion(rs.getString("vrEscala"));

                }

                if (fachadaBean.getIdEscala() == 508) {

                    //
                    fachadaBean.setFuelle_1Extrusion(rs.getString("vrEscala"));

                }

                if (fachadaBean.getIdEscala() == 504) {

                    //
                    fachadaBean.setFuelle_2Extrusion(rs.getString("vrEscala"));

                }

                if (fachadaBean.getIdEscala() == 511) {

                    //
                    fachadaBean.setExcedenteExtrusion(rs.getString("vrEscala"));

                }

                if (fachadaBean.getIdEscala() == 606) {

                    //
                    fachadaBean.setNumeroRolloExtrusion(rs.getString("vrEscala"));

                }

                if (fachadaBean.getIdEscala() == 607) {

                    //
                    fachadaBean.setTratadoCaraExtrusion(rs.getString("vrEscala"));

                }

                if (fachadaBean.getIdEscala() == 608) {

                    //
                    fachadaBean.setTratadoDinaExtrusion(rs.getString("vrEscala"));

                }

                if (fachadaBean.getIdEscala() == 608) {

                    //
                    fachadaBean.setTratadoDinaExtrusion(rs.getString("vrEscala"));

                }

                if (fachadaBean.getIdEscala() == 611) {

                    //
                    fachadaBean.setMpLineal(rs.getString("vrEscala"));

                }

                if (fachadaBean.getIdEscala() == 612) {

                    //
                    fachadaBean.setMpBaja(rs.getString("vrEscala"));

                }

                if (fachadaBean.getIdEscala() == 613) {

                    //
                    fachadaBean.setMpAlta(rs.getString("vrEscala"));

                }

                if (fachadaBean.getIdEscala() == 614) {

                    //
                    fachadaBean.setMpPP(rs.getString("vrEscala"));

                }

                if (fachadaBean.getIdEscala() == 617) {

                    //
                    fachadaBean.setMpLinealAlta(rs.getString("vrEscala"));

                }

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
            // conexion
            jdbcAccess.cleanup(connection, sentenciaSQL, null);
            return fachadaBean;
        }
    }

    // Metodo listaExtrusionUnOT_TipoEscala_6_7
    public FachadaOT listaExtrusionUnOT_TipoEscala_6_7() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        //
        FachadaOT fachadaBean = new FachadaOT();

        Connection connection = null;

        String insertStr
                = "   SELECT tblPlusFicha.idOperacion,          "
                + "         tblPlusFicha.idEscala,            "
                + "   	   tblPlusFicha.vrEscala,       "
                + "      tblPlusFicha.textoEscala + ' kg ' +  "
                + "        tblPlus.nombrePlu AS textoEscala , "
                + "         tblJobEscala.idTipoEscala         "
                + "   FROM   tblPlusFicha                     "
                + "   INNER JOIN tblJobEscala                 "
                + "   ON tblPlusFicha.idEscala =              "
                + "                  tblJobEscala.idEscala    "
                + "   INNER JOIN tblJobEscalaOperacion        "
                + "   ON tblJobEscalaOperacion.idEscala     = "
                + "                    tblPlusFicha.idEscala  "
                + "   AND tblJobEscalaOperacion.idOperacion = "
                + "                  tblPlusFicha.idOperacion "
                + "   INNER JOIN tblDctosOrdenes              "
                + "   ON    tblDctosOrdenes.idFicha         = "
                + "                tblPlusFicha.idFicha       "
                + "   INNER JOIN tblPlus                      "
                + "   ON    tblPlus.idPlu                   = "
                + "                   tblPlusFicha.vrEscala   "
                + "   WHERE tblPlusFicha.idOperacion        = "
                + getIdOperacion() + "                        "
                + "   AND    tblDctosOrdenes.numeroOrden    = "
                + getNumeroOrden() + "                        "
                + "   AND tblJobEscala.idTipoEscala IN (6,7)  ";

        PreparedStatement sentenciaSQL = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            sentenciaSQL = connection.prepareStatement(insertStr);

            // Obtiene el resultset
            ResultSet rs = sentenciaSQL.executeQuery();

            while (rs.next()) {

                //
                fachadaBean.setIdEscala(rs.getInt("idEscala"));
                fachadaBean.setVrEscala(rs.getDouble("vrEscala"));
                fachadaBean.setTextoEscala(rs.getString("textoEscala"));

                //
                if (fachadaBean.getIdEscala() == 615) {

                    //mpPigmento
                    fachadaBean.setMpPigmento(rs.getString("textoEscala"));

                }

                //
                if (fachadaBean.getIdEscala() == 616) {

                    //mpPigmento
                    fachadaBean.setMpOtraRfcia1(rs.getString("textoEscala"));

                }

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
            // conexion
            jdbcAccess.cleanup(connection, sentenciaSQL, null);
            return fachadaBean;
        }
    }

    // Metodo listaImpresionUnOT_TipoEscala_1
    public FachadaOT listaImpresionUnOT_TipoEscala_1() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        //
        FachadaOT fachadaBean = new FachadaOT();

        Connection connection = null;

        String insertStr
                = "    SELECT tblPlusFicha.idOperacion,             "
                + "          tblPlusFicha.idEscala,               "
                + "    	   tblPlusFicha.vrEscala,                 "
                + "    	   STR(tblPlusFicha.vrEscala)             "
                + "                     AS textoEscala,           "
                + "    	   tblJobEscala.nombreEscala,             "
                + "          tblJobEscala.idTipoEscala            "
                + "    FROM   tblPlusFicha                        "
                + "    INNER JOIN tblJobEscala                    "
                + "    ON tblPlusFicha.idEscala =                 "
                + "                   tblJobEscala.idEscala       "
                + "    INNER JOIN tblJobEscalaOperacion           "
                + "    ON tblJobEscalaOperacion.idEscala     =    "
                + "                     tblPlusFicha.idEscala     "
                + "    AND tblJobEscalaOperacion.idOperacion =    "
                + "                   tblPlusFicha.idOperacion    "
                + "    INNER JOIN tblDctosOrdenes                 "
                + "    ON    tblDctosOrdenes.idFicha    =         "
                + "                 tblPlusFicha.idFicha          "
                + "    WHERE tblPlusFicha.idOperacion       =     "
                + getIdOperacion() + "                            "
                + "    AND    tblDctosOrdenes.numeroOrden   =     "
                + getNumeroOrden() + "                            "
                + "    AND    tblJobEscala.idTipoEscala     = 1   ";

        PreparedStatement sentenciaSQL = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            sentenciaSQL = connection.prepareStatement(insertStr);

            // Obtiene el resultset
            ResultSet rs = sentenciaSQL.executeQuery();

            while (rs.next()) {

                //
                fachadaBean.setIdEscala(rs.getInt("idEscala"));
                fachadaBean.setVrEscala(rs.getDouble("vrEscala"));
                fachadaBean.setTextoEscala(rs.getString("textoEscala"));
                fachadaBean.setNombreEscala(rs.getString("nombreEscala"));

                //
                if (fachadaBean.getIdEscala() == 513) {

                    //
                    fachadaBean.setColorImpresion(rs.getString("vrEscala"));

                }

                if (fachadaBean.getIdEscala() == 700) {

                    //
                    fachadaBean.setRepeticionImpresion(rs.getString("vrEscala"));

                }

                if (fachadaBean.getIdEscala() == 701) {

                    //
                    fachadaBean.setRodilloImpresion(rs.getString("vrEscala"));

                }

                if (fachadaBean.getIdEscala() == 704) {

                    //
                    fachadaBean.setCodigoBarraImpresion(rs.getString("vrEscala"));

                }

                if (fachadaBean.getIdEscala() == 705) {

                    //
                    fachadaBean.setCyrelCara1Impresion(rs.getString("vrEscala"));

                }

                if (fachadaBean.getIdEscala() == 712) {

                    //
                    fachadaBean.setCyrelCara2Impresion(rs.getString("vrEscala"));

                }

                if (fachadaBean.getIdEscala() == 805) {

                    //
                    fachadaBean.setTipoEmbobinadoImpresion(rs.getString("vrEscala"));

                }
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
            // conexion
            jdbcAccess.cleanup(connection, sentenciaSQL, null);
            return fachadaBean;
        }
    }

    // Metodo listaRefiladoUnOT_TipoEscala_1
    public FachadaOT listaRefiladoUnOT_TipoEscala_1() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        //
        FachadaOT fachadaBean = new FachadaOT();

        Connection connection = null;

        String insertStr
                = "    SELECT tblPlusFicha.idOperacion,             "
                + "          tblPlusFicha.idEscala,               "
                + "    	   tblPlusFicha.vrEscala,                 "
                + "    	   STR(tblPlusFicha.vrEscala)             "
                + "                     AS textoEscala,           "
                + "    	   tblJobEscala.nombreEscala,             "
                + "          tblJobEscala.idTipoEscala            "
                + "    FROM   tblPlusFicha                        "
                + "    INNER JOIN tblJobEscala                    "
                + "    ON tblPlusFicha.idEscala =                 "
                + "                   tblJobEscala.idEscala       "
                + "    INNER JOIN tblJobEscalaOperacion           "
                + "    ON tblJobEscalaOperacion.idEscala     =    "
                + "                     tblPlusFicha.idEscala     "
                + "    AND tblJobEscalaOperacion.idOperacion =    "
                + "                   tblPlusFicha.idOperacion    "
                + "    INNER JOIN tblDctosOrdenes                 "
                + "    ON    tblDctosOrdenes.idFicha    =         "
                + "                 tblPlusFicha.idFicha          "
                + "    WHERE tblPlusFicha.idOperacion       =     "
                + getIdOperacion() + "                            "
                + "    AND    tblDctosOrdenes.numeroOrden   =     "
                + getNumeroOrden() + "                            "
                + "    AND    tblJobEscala.idTipoEscala     = 1   ";

        PreparedStatement sentenciaSQL = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            sentenciaSQL = connection.prepareStatement(insertStr);

            // Obtiene el resultset
            ResultSet rs = sentenciaSQL.executeQuery();

            while (rs.next()) {

                //
                fachadaBean.setIdEscala(rs.getInt("idEscala"));
                fachadaBean.setVrEscala(rs.getDouble("vrEscala"));
                fachadaBean.setTextoEscala(rs.getString("textoEscala"));
                fachadaBean.setNombreEscala(rs.getString("nombreEscala"));

                //
                if (fachadaBean.getIdEscala() == 501) {

                    //
                    fachadaBean.setAnchoRefilado(rs.getString("vrEscala"));

                }

                if (fachadaBean.getIdEscala() == 801) {

                    //
                    fachadaBean.setAlturaRefilado(rs.getString("vrEscala"));

                }

                if (fachadaBean.getIdEscala() == 802) {

                    //
                    fachadaBean.setTipoRefilado(rs.getString("vrEscala"));

                }

                if (fachadaBean.getIdEscala() == 803) {

                    //
                    fachadaBean.setMetroRefilado(rs.getString("vrEscala"));

                }

                if (fachadaBean.getIdEscala() == 804) {

                    //
                    fachadaBean.setPesoRolloRefilado(rs.getString("vrEscala"));

                }

                if (fachadaBean.getIdEscala() == 805) {

                    //
                    fachadaBean.setTipoEmbobinadoRefilado(rs.getString("vrEscala"));

                }

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
            // conexion
            jdbcAccess.cleanup(connection, sentenciaSQL, null);
            return fachadaBean;
        }
    }

    // Metodo listaSelladoUnOT_TipoEscala_1
    public FachadaOT listaSelladoUnOT_TipoEscala_1() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        //
        FachadaOT fachadaBean = new FachadaOT();

        Connection connection = null;

        String insertStr
                = "    SELECT tblPlusFicha.idOperacion,             "
                + "          tblPlusFicha.idEscala,               "
                + "    	   tblPlusFicha.vrEscala,                 "
                + "    	   STR(tblPlusFicha.vrEscala)             "
                + "                     AS textoEscala,           "
                + "    	   tblJobEscala.nombreEscala,             "
                + "          tblJobEscala.idTipoEscala            "
                + "    FROM   tblPlusFicha                        "
                + "    INNER JOIN tblJobEscala                    "
                + "    ON tblPlusFicha.idEscala =                 "
                + "                   tblJobEscala.idEscala       "
                + "    INNER JOIN tblJobEscalaOperacion           "
                + "    ON tblJobEscalaOperacion.idEscala     =    "
                + "                     tblPlusFicha.idEscala     "
                + "    AND tblJobEscalaOperacion.idOperacion =    "
                + "                   tblPlusFicha.idOperacion    "
                + "    INNER JOIN tblDctosOrdenes                 "
                + "    ON    tblDctosOrdenes.idFicha    =         "
                + "                 tblPlusFicha.idFicha          "
                + "    WHERE tblPlusFicha.idOperacion       =     "
                + getIdOperacion() + "                            "
                + "    AND    tblDctosOrdenes.numeroOrden   =     "
                + getNumeroOrden() + "                            "
                + "    AND    tblJobEscala.idTipoEscala     = 1   ";

        PreparedStatement sentenciaSQL = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            sentenciaSQL = connection.prepareStatement(insertStr);

            // Obtiene el resultset
            ResultSet rs = sentenciaSQL.executeQuery();

            while (rs.next()) {

                //
                fachadaBean.setIdEscala(rs.getInt("idEscala"));
                fachadaBean.setVrEscala(rs.getDouble("vrEscala"));
                fachadaBean.setTextoEscala(rs.getString("textoEscala"));
                fachadaBean.setNombreEscala(rs.getString("nombreEscala"));

                //
                if (fachadaBean.getIdEscala() == 501) {

                    //
                    fachadaBean.setAnchoSellado(rs.getString("vrEscala"));

                }

                if (fachadaBean.getIdEscala() == 502) {

                    //
                    fachadaBean.setLargoSellado(rs.getString("vrEscala"));

                }

                if (fachadaBean.getIdEscala() == 503) {

                    //
                    fachadaBean.setCalibreSellado(rs.getString("vrEscala"));

                }

                if (fachadaBean.getIdEscala() == 507) {

                    //
                    fachadaBean.setSolapaSellado(rs.getString("vrEscala"));

                }

                if (fachadaBean.getIdEscala() == 508) {

                    //
                    fachadaBean.setFuelle1Sellado(rs.getString("vrEscala"));

                }

                if (fachadaBean.getIdEscala() == 504) {

                    //
                    fachadaBean.setFuelle2Sellado(rs.getString("vrEscala"));
                    ;

                }

                if (fachadaBean.getIdEscala() == 901) {

                    //
                    fachadaBean.setAlturaSellado(rs.getString("vrEscala"));

                }

                if (fachadaBean.getIdEscala() == 902) {

                    //
                    fachadaBean.setGolpeSellado(rs.getString("vrEscala"));

                }

                if (fachadaBean.getIdEscala() == 903) {

                    //
                    fachadaBean.setBultoSellado(rs.getString("vrEscala"));

                }

                if (fachadaBean.getIdEscala() == 904) {

                    //
                    fachadaBean.setPaqueteSellado(rs.getString("vrEscala"));

                }

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
            // conexion
            jdbcAccess.cleanup(connection, sentenciaSQL, null);
            return fachadaBean;
        }
    }

    // Metodo listaExtrusionUnOT_TipoEscala_2
    public FachadaOT listaExtrusionUnOT_TipoEscala_2() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        //
        FachadaOT fachadaBean = new FachadaOT();

        Connection connection = null;

        String insertStr
                = "   SELECT tblPlusFicha.idOperacion,           "
                + "          tblPlusFicha.idEscala,            "
                + "          tblPlusFicha.item,                "
                + "    	   tblPlusFicha.vrEscala,            "
                + "    	   tblJobEscalaDetalle.nombreItem    "
                + "                     AS textoEscala,        "
                + "    	   tblJobEscala.nombreEscala,        "
                + "          tblJobEscala.idTipoEscala         "
                + "    FROM   tblPlusFicha                     "
                + "    INNER JOIN tblJobEscala                 "
                + "    ON tblPlusFicha.idEscala =              "
                + "                   tblJobEscala.idEscala    "
                + "    INNER JOIN tblJobEscalaOperacion        "
                + "    ON tblJobEscalaOperacion.idEscala     = "
                + "                     tblPlusFicha.idEscala  "
                + "    AND tblJobEscalaOperacion.idOperacion = "
                + "                 tblPlusFicha.idOperacion   "
                + "    INNER JOIN tblDctosOrdenes              "
                + "    ON    tblDctosOrdenes.idFicha         = "
                + "                 tblPlusFicha.idFicha       "
                + "    INNER JOIN tblJobEscalaDetalle          "
                + "    ON tblPlusFicha.idEscala              = "
                + "              tblJobEscalaDetalle.idEscala  "
                + "    AND tblPlusFicha.vrEscala             = "
                + "                 tblJobEscalaDetalle.item   "
                + "    WHERE tblPlusFicha.idOperacion        = "
                + getIdOperacion() + "                         "
                + "    AND    tblDctosOrdenes.numeroOrden    = "
                + getNumeroOrden() + "                         "
                + "    AND    tblJobEscala.idTipoEscala      =2";

        PreparedStatement sentenciaSQL = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            sentenciaSQL = connection.prepareStatement(insertStr);

            // Obtiene el resultset
            ResultSet rs = sentenciaSQL.executeQuery();

            while (rs.next()) {

                //
                fachadaBean.setIdEscala(rs.getInt("idEscala"));
                fachadaBean.setVrEscala(rs.getDouble("vrEscala"));
                fachadaBean.setTextoEscala(rs.getString("textoEscala"));
                fachadaBean.setNombreEscala(rs.getString("nombreEscala"));

                //
                if (fachadaBean.getIdEscala() == 514) {

                    //
                    fachadaBean.setTipoSelladoExtrusion(rs.getString("textoEscala"));

                }

                if (fachadaBean.getIdEscala() == 600) {

                    //
                    fachadaBean.setMaquinaExtrusion(rs.getString("textoEscala"));

                }

                if (fachadaBean.getIdEscala() == 602) {

                    //
                    fachadaBean.setTipoRolloExtrusion(rs.getString("textoEscala"));

                }

                if (fachadaBean.getIdEscala() == 610) {

                    //
                    fachadaBean.setDestinoExtrusion(rs.getString("textoEscala"));

                }
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
            // conexion
            jdbcAccess.cleanup(connection, sentenciaSQL, null);
            return fachadaBean;
        }
    }

    // Metodo listaPedidoUnOT_TipoEscala_2
    public FachadaOT listaPedidoUnOT_TipoEscala_2() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        //
        FachadaOT fachadaBean = new FachadaOT();

        Connection connection = null;

        String insertStr
                = "   SELECT tblPlusFicha.idOperacion,           "
                + "          tblPlusFicha.idEscala,            "
                + "          tblPlusFicha.item,                "
                + "    	   tblPlusFicha.vrEscala,            "
                + "    	   tblJobEscalaDetalle.nombreItem    "
                + "                     AS textoEscala,        "
                + "    	   tblJobEscala.nombreEscala,        "
                + "          tblJobEscala.idTipoEscala         "
                + "    FROM   tblPlusFicha                     "
                + "    INNER JOIN tblJobEscala                 "
                + "    ON tblPlusFicha.idEscala =              "
                + "                   tblJobEscala.idEscala    "
                + "    INNER JOIN tblJobEscalaOperacion        "
                + "    ON tblJobEscalaOperacion.idEscala     = "
                + "                     tblPlusFicha.idEscala  "
                + "    AND tblJobEscalaOperacion.idOperacion = "
                + "                 tblPlusFicha.idOperacion   "
                + "    INNER JOIN tblDctosOrdenes              "
                + "    ON    tblDctosOrdenes.idFicha         = "
                + "                 tblPlusFicha.idFicha       "
                + "    INNER JOIN tblJobEscalaDetalle          "
                + "    ON tblPlusFicha.idEscala              = "
                + "              tblJobEscalaDetalle.idEscala  "
                + "    AND tblPlusFicha.vrEscala             = "
                + "                 tblJobEscalaDetalle.item   "
                + "    WHERE tblPlusFicha.idOperacion        = "
                + getIdOperacion() + "                         "
                + "    AND    tblDctosOrdenes.numeroOrden    = "
                + getNumeroOrden() + "                         "
                + "    AND    tblJobEscala.idTipoEscala      =2";

        PreparedStatement sentenciaSQL = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            sentenciaSQL = connection.prepareStatement(insertStr);

            // Obtiene el resultset
            ResultSet rs = sentenciaSQL.executeQuery();

            while (rs.next()) {

                //
                fachadaBean.setIdEscala(rs.getInt("idEscala"));
                fachadaBean.setVrEscala(rs.getDouble("vrEscala"));
                fachadaBean.setTextoEscala(rs.getString("textoEscala"));
                fachadaBean.setNombreEscala(rs.getString("nombreEscala"));

                //
                if (fachadaBean.getIdEscala() == 521) {

                    //
                    fachadaBean.setColorPedido(rs.getString("textoEscala"));

                }

                //
                if (fachadaBean.getIdEscala() == 520) {

                    //
                    fachadaBean.setCertificadoCalidadPedido(
                            rs.getString("textoEscala"));

                }

                if (fachadaBean.getIdEscala() == 900) {

                    //
                    fachadaBean.setTipoTroquelPedido(rs.getString("textoEscala"));

                }
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
            // conexion
            jdbcAccess.cleanup(connection, sentenciaSQL, null);
            return fachadaBean;
        }
    }

    // Metodo listaImpresionUnOT_TipoEscala_2
    public FachadaOT listaImpresionUnOT_TipoEscala_2() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        //
        FachadaOT fachadaBean = new FachadaOT();

        Connection connection = null;

        String insertStr
                = "   SELECT tblPlusFicha.idOperacion,           "
                + "          tblPlusFicha.idEscala,            "
                + "          tblPlusFicha.item,                "
                + "    	   tblPlusFicha.vrEscala,            "
                + "    	   tblJobEscalaDetalle.nombreItem    "
                + "                     AS textoEscala,        "
                + "    	   tblJobEscala.nombreEscala,        "
                + "          tblJobEscala.idTipoEscala         "
                + "    FROM   tblPlusFicha                     "
                + "    INNER JOIN tblJobEscala                 "
                + "    ON tblPlusFicha.idEscala =              "
                + "                   tblJobEscala.idEscala    "
                + "    INNER JOIN tblJobEscalaOperacion        "
                + "    ON tblJobEscalaOperacion.idEscala     = "
                + "                     tblPlusFicha.idEscala  "
                + "    AND tblJobEscalaOperacion.idOperacion = "
                + "                 tblPlusFicha.idOperacion   "
                + "    INNER JOIN tblDctosOrdenes              "
                + "    ON    tblDctosOrdenes.idFicha         = "
                + "                 tblPlusFicha.idFicha       "
                + "    INNER JOIN tblJobEscalaDetalle          "
                + "    ON tblPlusFicha.idEscala              = "
                + "              tblJobEscalaDetalle.idEscala  "
                + "    AND tblPlusFicha.vrEscala             = "
                + "                 tblJobEscalaDetalle.item   "
                + "    WHERE tblPlusFicha.idOperacion        = "
                + getIdOperacion() + "                         "
                + "    AND    tblDctosOrdenes.numeroOrden    = "
                + getNumeroOrden() + "                         "
                + "    AND    tblJobEscala.idTipoEscala      =2";

        PreparedStatement sentenciaSQL = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            sentenciaSQL = connection.prepareStatement(insertStr);

            // Obtiene el resultset
            ResultSet rs = sentenciaSQL.executeQuery();

            while (rs.next()) {

                //
                fachadaBean.setIdEscala(rs.getInt("idEscala"));
                fachadaBean.setVrEscala(rs.getDouble("vrEscala"));
                fachadaBean.setTextoEscala(rs.getString("textoEscala"));
                fachadaBean.setNombreEscala(rs.getString("nombreEscala"));

                //
                if (fachadaBean.getIdEscala() == 710) {

                    //
                    fachadaBean.setMaquinaImpresion(rs.getString("textoEscala"));

                }

                if (fachadaBean.getIdEscala() == 610) {

                    //
                    fachadaBean.setDestinoImpresion(rs.getString("textoEscala"));

                }
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
            // conexion
            jdbcAccess.cleanup(connection, sentenciaSQL, null);
            return fachadaBean;
        }
    }

    // Metodo listaRefiladoUnOT_TipoEscala_2
    public FachadaOT listaRefiladoUnOT_TipoEscala_2() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        //
        FachadaOT fachadaBean = new FachadaOT();

        Connection connection = null;

        String insertStr
                = "   SELECT tblPlusFicha.idOperacion,           "
                + "          tblPlusFicha.idEscala,            "
                + "          tblPlusFicha.item,                "
                + "    	   tblPlusFicha.vrEscala,              "
                + "    	   tblJobEscalaDetalle.nombreItem      "
                + "                     AS textoEscala,        "
                + "          tblJobEscala.idTipoEscala         "
                + "    FROM   tblPlusFicha                     "
                + "    INNER JOIN tblJobEscala                 "
                + "    ON tblPlusFicha.idEscala =              "
                + "                   tblJobEscala.idEscala    "
                + "    INNER JOIN tblJobEscalaOperacion        "
                + "    ON tblJobEscalaOperacion.idEscala     = "
                + "                     tblPlusFicha.idEscala  "
                + "    AND tblJobEscalaOperacion.idOperacion = "
                + "                 tblPlusFicha.idOperacion   "
                + "    INNER JOIN tblDctosOrdenes              "
                + "    ON    tblDctosOrdenes.idFicha         = "
                + "                 tblPlusFicha.idFicha       "
                + "    INNER JOIN tblJobEscalaDetalle          "
                + "    ON tblPlusFicha.idEscala              = "
                + "              tblJobEscalaDetalle.idEscala  "
                + "    AND tblPlusFicha.vrEscala             = "
                + "                 tblJobEscalaDetalle.item   "
                + "    WHERE tblPlusFicha.idOperacion        = "
                + getIdOperacion() + "                         "
                + "    AND    tblDctosOrdenes.numeroOrden    = "
                + getNumeroOrden() + "                         "
                + "    AND    tblJobEscala.idTipoEscala      =2";

        PreparedStatement sentenciaSQL = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            sentenciaSQL = connection.prepareStatement(insertStr);

            // Obtiene el resultset
            ResultSet rs = sentenciaSQL.executeQuery();

            while (rs.next()) {

                //
                fachadaBean.setIdEscala(rs.getInt("idEscala"));
                fachadaBean.setVrEscala(rs.getDouble("vrEscala"));
                fachadaBean.setTextoEscala(rs.getString("textoEscala"));

                //
                if (fachadaBean.getIdEscala() == 802) {

                    //
                    fachadaBean.setTipoRefilado(rs.getString("textoEscala"));

                }

                if (fachadaBean.getIdEscala() == 805) {

                    //
                    fachadaBean.setTipoRolloRefilado(rs.getString("textoEscala"));

                }

                if (fachadaBean.getIdEscala() == 610) {

                    //
                    fachadaBean.setDestinoRefilado(rs.getString("textoEscala"));

                }

                if (fachadaBean.getIdEscala() == 1100) {

                    //
                    fachadaBean.setMaquinaRefilado(rs.getString("textoEscala"));

                }

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
            // conexion
            jdbcAccess.cleanup(connection, sentenciaSQL, null);
            return fachadaBean;
        }
    }

    // Metodo listaManualidadUnOT_TipoEscala_2
    public FachadaOT listaManualidadUnOT_TipoEscala_2() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        //
        FachadaOT fachadaBean = new FachadaOT();

        Connection connection = null;

        String insertStr
                = "   SELECT tblPlusFicha.idOperacion,           "
                + "          tblPlusFicha.idEscala,            "
                + "          tblPlusFicha.item,                "
                + "    	   tblPlusFicha.vrEscala,              "
                + "    	   tblJobEscalaDetalle.nombreItem      "
                + "                     AS textoEscala,        "
                + "          tblJobEscala.idTipoEscala         "
                + "    FROM   tblPlusFicha                     "
                + "    INNER JOIN tblJobEscala                 "
                + "    ON tblPlusFicha.idEscala =              "
                + "                   tblJobEscala.idEscala    "
                + "    INNER JOIN tblJobEscalaOperacion        "
                + "    ON tblJobEscalaOperacion.idEscala     = "
                + "                     tblPlusFicha.idEscala  "
                + "    AND tblJobEscalaOperacion.idOperacion = "
                + "                 tblPlusFicha.idOperacion   "
                + "    INNER JOIN tblDctosOrdenes              "
                + "    ON    tblDctosOrdenes.idFicha         = "
                + "                 tblPlusFicha.idFicha       "
                + "    INNER JOIN tblJobEscalaDetalle          "
                + "    ON tblPlusFicha.idEscala              = "
                + "              tblJobEscalaDetalle.idEscala  "
                + "    AND tblPlusFicha.vrEscala             = "
                + "                 tblJobEscalaDetalle.item   "
                + "    WHERE tblPlusFicha.idOperacion        = "
                + getIdOperacion() + "                         "
                + "    AND    tblDctosOrdenes.numeroOrden    = "
                + getNumeroOrden() + "                         "
                + "    AND    tblJobEscala.idTipoEscala      =2";

        PreparedStatement sentenciaSQL = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            sentenciaSQL = connection.prepareStatement(insertStr);

            // Obtiene el resultset
            ResultSet rs = sentenciaSQL.executeQuery();

            while (rs.next()) {

                //
                fachadaBean.setIdEscala(rs.getInt("idEscala"));
                fachadaBean.setVrEscala(rs.getDouble("vrEscala"));
                fachadaBean.setTextoEscala(rs.getString("textoEscala"));

                //
                if (fachadaBean.getIdEscala() == 1000) {

                    //
                    fachadaBean.setMaquinaManualidad(rs.getString("textoEscala"));

                }

                if (fachadaBean.getIdEscala() == 610) {

                    //
                    fachadaBean.setDestinoManualidad(rs.getString("textoEscala"));

                }
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
            // conexion
            jdbcAccess.cleanup(connection, sentenciaSQL, null);
            return fachadaBean;
        }
    }

    // Metodo listaSelladoUnOT_TipoEscala_2
    public FachadaOT listaSelladoUnOT_TipoEscala_2() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        //
        FachadaOT fachadaBean = new FachadaOT();

        Connection connection = null;

        String insertStr
                = "   SELECT tblPlusFicha.idOperacion,           "
                + "          tblPlusFicha.idEscala,            "
                + "          tblPlusFicha.item,                "
                + "    	   tblPlusFicha.vrEscala,              "
                + "    	   tblJobEscalaDetalle.nombreItem      "
                + "                     AS textoEscala,        "
                + "          tblJobEscala.idTipoEscala         "
                + "    FROM   tblPlusFicha                     "
                + "    INNER JOIN tblJobEscala                 "
                + "    ON tblPlusFicha.idEscala =              "
                + "                   tblJobEscala.idEscala    "
                + "    INNER JOIN tblJobEscalaOperacion        "
                + "    ON tblJobEscalaOperacion.idEscala     = "
                + "                     tblPlusFicha.idEscala  "
                + "    AND tblJobEscalaOperacion.idOperacion = "
                + "                 tblPlusFicha.idOperacion   "
                + "    INNER JOIN tblDctosOrdenes              "
                + "    ON    tblDctosOrdenes.idFicha         = "
                + "                 tblPlusFicha.idFicha       "
                + "    INNER JOIN tblJobEscalaDetalle          "
                + "    ON tblPlusFicha.idEscala              = "
                + "              tblJobEscalaDetalle.idEscala  "
                + "    AND tblPlusFicha.vrEscala             = "
                + "                 tblJobEscalaDetalle.item   "
                + "    WHERE tblPlusFicha.idOperacion        = "
                + getIdOperacion() + "                         "
                + "    AND    tblDctosOrdenes.numeroOrden    = "
                + getNumeroOrden() + "                         "
                + "    AND    tblJobEscala.idTipoEscala      =2";

        PreparedStatement sentenciaSQL = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            sentenciaSQL = connection.prepareStatement(insertStr);

            // Obtiene el resultset
            ResultSet rs = sentenciaSQL.executeQuery();

            while (rs.next()) {

                //
                fachadaBean.setIdEscala(rs.getInt("idEscala"));
                fachadaBean.setVrEscala(rs.getDouble("vrEscala"));
                fachadaBean.setTextoEscala(rs.getString("textoEscala"));

                //
                if (fachadaBean.getIdEscala() == 910) {

                    //
                    fachadaBean.setMaquinaSellado(rs.getString("textoEscala"));

                }

                if (fachadaBean.getIdEscala() == 506) {

                    //
                    fachadaBean.setTipoSolapaSellado(rs.getString("textoEscala"));

                }

                if (fachadaBean.getIdEscala() == 505) {

                    //
                    fachadaBean.setTipoFuelleSellado(rs.getString("textoEscala"));

                }

                if (fachadaBean.getIdEscala() == 514) {

                    //
                    fachadaBean.setTipoSellado(rs.getString("textoEscala"));

                }

                if (fachadaBean.getIdEscala() == 900) {

                    //
                    fachadaBean.setTipoTroquelSellado(rs.getString("textoEscala"));

                }

                if (fachadaBean.getIdEscala() == 610) {

                    //
                    fachadaBean.setDestinoSellado(rs.getString("textoEscala"));

                }

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
            // conexion
            jdbcAccess.cleanup(connection, sentenciaSQL, null);
            return fachadaBean;
        }
    }

    // Metodo listaExtrusionUnOT_TipoEscala_3
    public FachadaOT listaExtrusionUnOT_TipoEscala_3() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        //
        FachadaOT fachadaBean = new FachadaOT();

        Connection connection = null;

        String insertStr
                = "  SELECT tblPlusFicha.idOperacion,            "
                + "       tblPlusFicha.idEscala,              "
                + "  	tblPlusFicha.vrEscala,                 "
                + "  	tblPlusFicha.textoEscala,              "
                + "  	tblJobEscala.nombreEscala,             "
                + "         tblJobEscala.idTipoEscala         "
                + "  FROM   tblPlusFicha                      "
                + "  INNER JOIN tblJobEscala                  "
                + "  ON tblPlusFicha.idEscala =               "
                + "                 tblJobEscala.idEscala     "
                + "  INNER JOIN tblJobEscalaOperacion         "
                + "  ON tblJobEscalaOperacion.idEscala     =  "
                + "                   tblPlusFicha.idEscala   "
                + "  AND tblJobEscalaOperacion.idOperacion =  "
                + "                 tblPlusFicha.idOperacion  "
                + "  INNER JOIN tblDctosOrdenes               "
                + "  ON    tblDctosOrdenes.idFicha         =  "
                + "               tblPlusFicha.idFicha        "
                + "  WHERE tblPlusFicha.idOperacion        =  "
                + getIdOperacion() + "                         "
                + "  AND    tblDctosOrdenes.numeroOrden    =  "
                + getNumeroOrden() + "                         "
                + "  AND    tblJobEscala.idTipoEscala      =3 ";

        PreparedStatement sentenciaSQL = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            sentenciaSQL = connection.prepareStatement(insertStr);

            // Obtiene el resultset
            ResultSet rs = sentenciaSQL.executeQuery();

            while (rs.next()) {

                //
                fachadaBean.setIdEscala(rs.getInt("idEscala"));
                fachadaBean.setVrEscala(rs.getDouble("vrEscala"));
                fachadaBean.setTextoEscala(rs.getString("textoEscala"));
                fachadaBean.setNombreEscala(rs.getString("nombreEscala"));

                //
                if (fachadaBean.getIdEscala() == 515) {

                    //
                    fachadaBean.setObservacionExtrusion(rs.getString("textoEscala"));

                }
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
            // conexion
            jdbcAccess.cleanup(connection, sentenciaSQL, null);
            return fachadaBean;
        }
    }

    // Metodo listaPedidoUnOT_TipoEscala_3
    public FachadaOT listaPedidoUnOT_TipoEscala_3() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        //
        FachadaOT fachadaBean = new FachadaOT();

        Connection connection = null;

        String insertStr
                = "  SELECT tblPlusFicha.idOperacion,           "
                + "       tblPlusFicha.idEscala,              "
                + "  	tblPlusFicha.vrEscala,                "
                + "  	tblPlusFicha.textoEscala,             "
                + "  	tblJobEscala.nombreEscala,            "
                + "         tblJobEscala.idTipoEscala         "
                + "  FROM   tblPlusFicha                      "
                + "  INNER JOIN tblJobEscala                  "
                + "  ON tblPlusFicha.idEscala =               "
                + "                 tblJobEscala.idEscala     "
                + "  INNER JOIN tblJobEscalaOperacion         "
                + "  ON tblJobEscalaOperacion.idEscala     =  "
                + "                   tblPlusFicha.idEscala   "
                + "  AND tblJobEscalaOperacion.idOperacion =  "
                + "                 tblPlusFicha.idOperacion  "
                + "  INNER JOIN tblDctosOrdenes               "
                + "  ON    tblDctosOrdenes.idFicha         =  "
                + "               tblPlusFicha.idFicha        "
                + "  WHERE tblPlusFicha.idOperacion        =  "
                + getIdOperacion() + "                         "
                + "  AND    tblDctosOrdenes.numeroOrden    =  "
                + getNumeroOrden() + "                         "
                + "  AND    tblJobEscala.idTipoEscala      =3 ";

        PreparedStatement sentenciaSQL = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            sentenciaSQL = connection.prepareStatement(insertStr);

            // Obtiene el resultset
            ResultSet rs = sentenciaSQL.executeQuery();

            while (rs.next()) {

                //
                fachadaBean.setIdEscala(rs.getInt("idEscala"));
                fachadaBean.setVrEscala(rs.getDouble("vrEscala"));
                fachadaBean.setTextoEscala(rs.getString("textoEscala"));
                fachadaBean.setNombreEscala(rs.getString("nombreEscala"));

                //
                if (fachadaBean.getIdEscala() == 515) {

                    //
                    fachadaBean.setObservacionPedido(rs.getString("textoEscala"));

                }

                if (fachadaBean.getIdEscala() == 516) {

                    //
                    fachadaBean.setTerminacionPedido(rs.getString("textoEscala"));

                }
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
            // conexion
            jdbcAccess.cleanup(connection, sentenciaSQL, null);
            return fachadaBean;
        }
    }

    // Metodo listaImpresionUnOT_TipoEscala_3
    public FachadaOT listaImpresionUnOT_TipoEscala_3() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        //
        FachadaOT fachadaBean = new FachadaOT();

        Connection connection = null;

        String insertStr
                = "  SELECT tblPlusFicha.idOperacion,           "
                + "       tblPlusFicha.idEscala,              "
                + "  	tblPlusFicha.vrEscala,                "
                + "  	tblPlusFicha.textoEscala,             "
                + "  	tblJobEscala.nombreEscala,            "
                + "         tblJobEscala.idTipoEscala         "
                + "  FROM   tblPlusFicha                      "
                + "  INNER JOIN tblJobEscala                  "
                + "  ON tblPlusFicha.idEscala =               "
                + "                 tblJobEscala.idEscala     "
                + "  INNER JOIN tblJobEscalaOperacion         "
                + "  ON tblJobEscalaOperacion.idEscala     =  "
                + "                   tblPlusFicha.idEscala   "
                + "  AND tblJobEscalaOperacion.idOperacion =  "
                + "                 tblPlusFicha.idOperacion  "
                + "  INNER JOIN tblDctosOrdenes               "
                + "  ON    tblDctosOrdenes.idFicha         =  "
                + "               tblPlusFicha.idFicha        "
                + "  WHERE tblPlusFicha.idOperacion        =  "
                + getIdOperacion() + "                        "
                + "  AND    tblDctosOrdenes.numeroOrden    =  "
                + getNumeroOrden() + "                        "
                + "  AND    tblJobEscala.idTipoEscala      =3 ";

        PreparedStatement sentenciaSQL = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            sentenciaSQL = connection.prepareStatement(insertStr);

            // Obtiene el resultset
            ResultSet rs = sentenciaSQL.executeQuery();

            while (rs.next()) {

                //
                fachadaBean.setIdEscala(rs.getInt("idEscala"));
                fachadaBean.setVrEscala(rs.getDouble("vrEscala"));
                fachadaBean.setTextoEscala(rs.getString("textoEscala"));
                fachadaBean.setNombreEscala(rs.getString("nombreEscala"));

                //
                if (fachadaBean.getIdEscala() == 515) {

                    //
                    fachadaBean.setObservacionImpresion(rs.getString("textoEscala"));

                }

                if (fachadaBean.getIdEscala() == 702) {

                    //
                    fachadaBean.setAlturaCara1Impresion(rs.getString("textoEscala"));

                }

                if (fachadaBean.getIdEscala() == 703) {

                    //
                    fachadaBean.setAlturaCara2Impresion(rs.getString("textoEscala"));

                }
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
            // conexion
            jdbcAccess.cleanup(connection, sentenciaSQL, null);
            return fachadaBean;
        }
    }

    // Metodo listaImpresionUnOT_TipoEscala_5
    public FachadaOT listaImpresionUnOT_TipoEscala_5() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        //
        String xTexto = "";

        //
        FachadaOT fachadaBean = new FachadaOT();

        Connection connection = null;

        String insertStr
                = "  SELECT tblPlusFicha.idOperacion,            "
                + "         tblPlusFicha.idEscala,             "
                + "         tblPlusFicha.item,                 "
                + "   	   tblPlusFicha.vrEscala,              "
                + "   	   tblJobEscalaDetalle.nombreItem,     "
                + "          tblPlusFicha.textoEscala,         "
                + "   	   tblJobEscala.nombreEscala           "
                + "    FROM   tblPlusFicha                     "
                + "    INNER JOIN tblJobEscala                 "
                + "    ON tblPlusFicha.idEscala =              "
                + "                   tblJobEscala.idEscala    "
                + "    INNER JOIN tblJobEscalaOperacion        "
                + "    ON tblJobEscalaOperacion.idEscala     = "
                + "                     tblPlusFicha.idEscala  "
                + "    AND tblJobEscalaOperacion.idOperacion = "
                + "                 tblPlusFicha.idOperacion   "
                + "    INNER JOIN tblDctosOrdenes              "
                + "    ON    tblDctosOrdenes.idFicha         = "
                + "                 tblPlusFicha.idFicha       "
                + "    INNER JOIN tblJobEscalaDetalle          "
                + "    ON tblPlusFicha.idEscala              = "
                + "              tblJobEscalaDetalle.idEscala  "
                + "    AND tblPlusFicha.vrEscala             = "
                + "                 tblJobEscalaDetalle.item   "
                + "    WHERE tblPlusFicha.idOperacion        = "
                + getIdOperacion() + "                         "
                + "    AND    tblDctosOrdenes.numeroOrden    = "
                + getNumeroOrden() + "                         "
                + "    AND    tblJobEscala.idTipoEscala      =5";

        PreparedStatement sentenciaSQL = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            sentenciaSQL = connection.prepareStatement(insertStr);

            // Obtiene el resultset
            ResultSet rs = sentenciaSQL.executeQuery();

            while (rs.next()) {

                //
                fachadaBean.setIdEscala(rs.getInt("idEscala"));
                fachadaBean.setVrEscala(rs.getDouble("vrEscala"));
                fachadaBean.setTextoEscala(rs.getString("textoEscala"));
                fachadaBean.setNombreEscala(rs.getString("nombreEscala"));
                fachadaBean.setNombreItem(rs.getString("nombreItem"));

                //
                xTexto = fachadaBean.getCaraColorPantone(fachadaBean.getNombreItem(),
                        fachadaBean.getTextoEscala());

                //
                if (fachadaBean.getIdEscala() == 706) {

                    //
                    fachadaBean.setCara1Color1Pantone(xTexto);

                }

                if (fachadaBean.getIdEscala() == 707) {

                    //
                    fachadaBean.setCara1Color2Pantone(xTexto);

                }

                if (fachadaBean.getIdEscala() == 708) {

                    //
                    fachadaBean.setCara1Color3Pantone(xTexto);

                }

                if (fachadaBean.getIdEscala() == 709) {

                    //
                    fachadaBean.setCara1Color4Pantone(xTexto);

                }

                if (fachadaBean.getIdEscala() == 714) {

                    //
                    fachadaBean.setCara1Color5Pantone(xTexto);

                }

                if (fachadaBean.getIdEscala() == 715) {

                    //
                    fachadaBean.setCara1Color6Pantone(xTexto);

                }

                //---
                if (fachadaBean.getIdEscala() == 720) {

                    //
                    fachadaBean.setCara2Color1Pantone(xTexto);

                }

                if (fachadaBean.getIdEscala() == 721) {

                    //
                    fachadaBean.setCara2Color2Pantone(xTexto);

                }

                if (fachadaBean.getIdEscala() == 722) {

                    //
                    fachadaBean.setCara2Color3Pantone(xTexto);

                }

                if (fachadaBean.getIdEscala() == 723) {

                    //
                    fachadaBean.setCara2Color4Pantone(xTexto);

                }

                if (fachadaBean.getIdEscala() == 724) {

                    //
                    fachadaBean.setCara2Color5Pantone(xTexto);

                }

                if (fachadaBean.getIdEscala() == 725) {

                    //
                    fachadaBean.setCara2Color6Pantone(xTexto);

                }

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
            // conexion
            jdbcAccess.cleanup(connection, sentenciaSQL, null);
            return fachadaBean;
        }
    }

    // Metodo listaRefiladoUnOT_TipoEscala_3
    public FachadaOT listaRefiladoUnOT_TipoEscala_3() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        //
        FachadaOT fachadaBean = new FachadaOT();

        Connection connection = null;

        String insertStr
                = "  SELECT tblPlusFicha.idOperacion,           "
                + "       tblPlusFicha.idEscala,              "
                + "  	tblPlusFicha.vrEscala,                "
                + "  	tblPlusFicha.textoEscala,             "
                + "  	tblJobEscala.nombreEscala,            "
                + "         tblJobEscala.idTipoEscala         "
                + "  FROM   tblPlusFicha                      "
                + "  INNER JOIN tblJobEscala                  "
                + "  ON tblPlusFicha.idEscala =               "
                + "                 tblJobEscala.idEscala     "
                + "  INNER JOIN tblJobEscalaOperacion         "
                + "  ON tblJobEscalaOperacion.idEscala     =  "
                + "                   tblPlusFicha.idEscala   "
                + "  AND tblJobEscalaOperacion.idOperacion =  "
                + "                 tblPlusFicha.idOperacion  "
                + "  INNER JOIN tblDctosOrdenes               "
                + "  ON    tblDctosOrdenes.idFicha         =  "
                + "               tblPlusFicha.idFicha        "
                + "  WHERE tblPlusFicha.idOperacion        =  "
                + getIdOperacion() + "                         "
                + "  AND    tblDctosOrdenes.numeroOrden    =  "
                + getNumeroOrden() + "                         "
                + "  AND    tblJobEscala.idTipoEscala      =3 ";

        PreparedStatement sentenciaSQL = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            sentenciaSQL = connection.prepareStatement(insertStr);

            // Obtiene el resultset
            ResultSet rs = sentenciaSQL.executeQuery();

            while (rs.next()) {

                //
                fachadaBean.setIdEscala(rs.getInt("idEscala"));
                fachadaBean.setVrEscala(rs.getDouble("vrEscala"));
                fachadaBean.setTextoEscala(rs.getString("textoEscala"));
                fachadaBean.setNombreEscala(rs.getString("nombreEscala"));

                //
                if (fachadaBean.getIdEscala() == 515) {

                    //
                    fachadaBean.setObservacionRefilado(rs.getString("textoEscala"));

                }
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
            // conexion
            jdbcAccess.cleanup(connection, sentenciaSQL, null);
            return fachadaBean;
        }
    }

    // Metodo listaSelladoUnOT_TipoEscala_3
    public FachadaOT listaSelladoUnOT_TipoEscala_3() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        //
        FachadaOT fachadaBean = new FachadaOT();

        Connection connection = null;

        String insertStr
                = "  SELECT tblPlusFicha.idOperacion,           "
                + "       tblPlusFicha.idEscala,              "
                + "  	tblPlusFicha.vrEscala,                "
                + "  	tblPlusFicha.textoEscala,             "
                + "  	tblJobEscala.nombreEscala,            "
                + "         tblJobEscala.idTipoEscala         "
                + "  FROM   tblPlusFicha                      "
                + "  INNER JOIN tblJobEscala                  "
                + "  ON tblPlusFicha.idEscala =               "
                + "                 tblJobEscala.idEscala     "
                + "  INNER JOIN tblJobEscalaOperacion         "
                + "  ON tblJobEscalaOperacion.idEscala     =  "
                + "                   tblPlusFicha.idEscala   "
                + "  AND tblJobEscalaOperacion.idOperacion =  "
                + "                 tblPlusFicha.idOperacion  "
                + "  INNER JOIN tblDctosOrdenes               "
                + "  ON    tblDctosOrdenes.idFicha         =  "
                + "               tblPlusFicha.idFicha        "
                + "  WHERE tblPlusFicha.idOperacion        =  "
                + getIdOperacion() + "                         "
                + "  AND    tblDctosOrdenes.numeroOrden    =  "
                + getNumeroOrden() + "                         "
                + "  AND    tblJobEscala.idTipoEscala      =3 ";

        PreparedStatement sentenciaSQL = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            sentenciaSQL = connection.prepareStatement(insertStr);

            // Obtiene el resultset
            ResultSet rs = sentenciaSQL.executeQuery();

            while (rs.next()) {

                //
                fachadaBean.setIdEscala(rs.getInt("idEscala"));
                fachadaBean.setVrEscala(rs.getDouble("vrEscala"));
                fachadaBean.setTextoEscala(rs.getString("textoEscala"));
                fachadaBean.setNombreEscala(rs.getString("nombreEscala"));

                //
                if (fachadaBean.getIdEscala() == 515) {

                    //
                    fachadaBean.setObservacionSellado(rs.getString("textoEscala"));

                }
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
            // conexion
            jdbcAccess.cleanup(connection, sentenciaSQL, null);
            return fachadaBean;
        }
    }

    // Metodo listaManualidadUnOT_TipoEscala_3
    public FachadaOT listaManualidadUnOT_TipoEscala_3() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        //
        FachadaOT fachadaBean = new FachadaOT();

        Connection connection = null;

        String insertStr
                = "  SELECT tblPlusFicha.idOperacion,           "
                + "       tblPlusFicha.idEscala,              "
                + "  	tblPlusFicha.vrEscala,                "
                + "  	tblPlusFicha.textoEscala,             "
                + "  	tblJobEscala.nombreEscala,            "
                + "         tblJobEscala.idTipoEscala         "
                + "  FROM   tblPlusFicha                      "
                + "  INNER JOIN tblJobEscala                  "
                + "  ON tblPlusFicha.idEscala =               "
                + "                 tblJobEscala.idEscala     "
                + "  INNER JOIN tblJobEscalaOperacion         "
                + "  ON tblJobEscalaOperacion.idEscala     =  "
                + "                   tblPlusFicha.idEscala   "
                + "  AND tblJobEscalaOperacion.idOperacion =  "
                + "                 tblPlusFicha.idOperacion  "
                + "  INNER JOIN tblDctosOrdenes               "
                + "  ON    tblDctosOrdenes.idFicha         =  "
                + "               tblPlusFicha.idFicha        "
                + "  WHERE tblPlusFicha.idOperacion        =  "
                + getIdOperacion() + "                         "
                + "  AND    tblDctosOrdenes.numeroOrden    =  "
                + getNumeroOrden() + "                         "
                + "  AND    tblJobEscala.idTipoEscala      =3 ";

        PreparedStatement sentenciaSQL = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            sentenciaSQL = connection.prepareStatement(insertStr);

            // Obtiene el resultset
            ResultSet rs = sentenciaSQL.executeQuery();

            while (rs.next()) {

                //
                fachadaBean.setIdEscala(rs.getInt("idEscala"));
                fachadaBean.setVrEscala(rs.getDouble("vrEscala"));
                fachadaBean.setTextoEscala(rs.getString("textoEscala"));
                fachadaBean.setNombreEscala(rs.getString("nombreEscala"));

                //
                if (fachadaBean.getIdEscala() == 515) {

                    //
                    fachadaBean.setObservacionManualidad(rs.getString("textoEscala"));

                }
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
            // conexion
            jdbcAccess.cleanup(connection, sentenciaSQL, null);
            return fachadaBean;
        }
    }

    // Metodo listaExtrusionUnOT_TipoEscala_3
    public String listaExtrusionUnOT_TipoEscala_4() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        String xOtrasRefMP = "";

        Connection connection = null;

        String insertStr
                = "  SELECT tblPlusFicha.idOperacion,            "
                + "       tblPlusFicha.idEscala,              "
                + "  	tblPlusFicha.vrEscala,                 "
                + "  	tblPlusFicha.textoEscala,              "
                + "  	tblJobEscala.nombreEscala,             "
                + "         tblJobEscala.idTipoEscala         "
                + "  FROM   tblPlusFicha                      "
                + "  INNER JOIN tblJobEscala                  "
                + "  ON tblPlusFicha.idEscala =               "
                + "                 tblJobEscala.idEscala     "
                + "  INNER JOIN tblJobEscalaOperacion         "
                + "  ON tblJobEscalaOperacion.idEscala     =  "
                + "                   tblPlusFicha.idEscala   "
                + "  AND tblJobEscalaOperacion.idOperacion =  "
                + "                 tblPlusFicha.idOperacion  "
                + "  INNER JOIN tblDctosOrdenes               "
                + "  ON    tblDctosOrdenes.idFicha         =  "
                + "               tblPlusFicha.idFicha        "
                + "  WHERE tblPlusFicha.idOperacion        =  "
                + getIdOperacion() + "                        "
                + "  AND    tblDctosOrdenes.numeroOrden    =  "
                + getNumeroOrden() + "                        "
                + "  AND    tblJobEscala.idTipoEscala      =4 "
                + "  AND tblPlusFicha.vrEscala > 0 ";

        PreparedStatement sentenciaSQL = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            sentenciaSQL = connection.prepareStatement(insertStr);

            // Obtiene el resultset
            ResultSet rs = sentenciaSQL.executeQuery();

            while (rs.next()) {

                //
                xOtrasRefMP += rs.getString("nombreEscala") + "  "
                        + rs.getString("textoEscala") + "  "
                        + rs.getString("vrEscala") + "  ";
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
            // conexion
            jdbcAccess.cleanup(connection, sentenciaSQL, null);
            return xOtrasRefMP;
        }
    }

    public String[] listaExtrusionTornillosMP(int idMix, int idOption) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        Connection connection = null;

        String insertStr
                = "SELECT \n"
                + "[dbo].[tbl_mixes_option_screw_plu].id_screw,\n"
                + "[id_plu],\n"
                + "[nombrePlu],\n"
                + "[vrCosto],\n"
                + "[quantity]\n"
                + "  FROM [dbo].[tbl_mixes_option_screw_plu],\n"
                + "[dbo].[tblPlus]\n"
                + "WHERE [dbo].[tbl_mixes_option_screw_plu].id_plu=[dbo].[tblPlus].idPlu\n"
                + "AND [dbo].[tbl_mixes_option_screw_plu].id_mix = ? \n"
                + "AND [dbo].[tbl_mixes_option_screw_plu].id_option = ? \n"
                + "ORDER BY id_mix,id_option;\n";

        PreparedStatement sentenciaSQL = null;
        String[] tornillos = new String[3];
        ResultSet rs = null;
        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            sentenciaSQL = connection.prepareStatement(insertStr);
            sentenciaSQL.setInt(1, idMix);
            sentenciaSQL.setInt(2, idOption);
            tornillos[0] = "";
            tornillos[1] = "";
            tornillos[2] = "";
            // Obtiene el resultset
            rs = sentenciaSQL.executeQuery();

            while (rs.next()) {
                int idTornillo = rs.getInt("id_screw");

                if (idTornillo == 1) {
                    tornillos[0] += " " + rs.getString("nombrePlu") + ":" + rs.getString("quantity") + ",";
                } else if (idTornillo == 2) {
                    tornillos[1] += " " + rs.getString("nombrePlu") + ":" + rs.getString("quantity") + ",";

                } else if (idTornillo == 3) {
                    tornillos[2] += " " + rs.getString("nombrePlu") + ":" + rs.getString("quantity") + ",";
                }
            }

            tornillos[0] = !tornillos[0].isEmpty() && tornillos[0].length() > 1 ? tornillos[0].substring(0, tornillos[0].length() - 1) : tornillos[0];
            tornillos[1] = !tornillos[1].isEmpty() && tornillos[1].length() > 1 ? tornillos[1].substring(0, tornillos[1].length() - 1) : tornillos[1];
            tornillos[2] = !tornillos[2].isEmpty() && tornillos[2].length() > 1 ? tornillos[2].substring(0, tornillos[2].length() - 1) : tornillos[2];
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
            if (rs != null) {
                rs.close();
            }

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
            if (rs != null) {
                rs.close();
            }
        } finally {

            // Cierra la conexion a la base de datos limpiando la statement usada y la
            // conexion
            jdbcAccess.cleanup(connection, sentenciaSQL, null);
            return tornillos;
        }
    }

    public List<String> listaFrecsTornillos(int idMix, int idOption) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        List<String> listaFrecs = new ArrayList();

        Connection connection = null;

        String insertStr
                = "SELECT  [id]\n"
                + "      ,[layer_of_ratio]\n"
                + "      ,[id_mix]\n"
                + "      ,[id_option]\n"
                + "      ,[id_screw]\n"
                + "  FROM [BDCommerce].[dbo].[tbl_mix_option_screw]\n"
                + "  WHERE id_mix=? "
                + "  AND id_option=?\n"
                + "  ORDER BY id_screw";

        PreparedStatement sentenciaSQL = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            sentenciaSQL = connection.prepareStatement(insertStr);
            sentenciaSQL.setInt(1, idMix);
            sentenciaSQL.setInt(2, idOption);

            // Obtiene el resultset
            ResultSet rs = sentenciaSQL.executeQuery();

            while (rs.next()) {

                listaFrecs.add(rs.getString("layer_of_ratio"));

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
            // conexion
            jdbcAccess.cleanup(connection, sentenciaSQL, null);
            return listaFrecs;
        }
    }
}
