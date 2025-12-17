package com.solucionesweb.losbeans.colaboraciones;

// Importa la clase helper JDBCAccess que contiene los accesos a la base de datos
import com.solucionesweb.lasayudas.JDBCAccess;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaTipoOrdenSubcuenta;

// Importa los paquetes de java
import java.sql.*;
import java.io.*;
import java.util.*;
import java.io.IOException.*;

// Importa los paquetes de javax
import javax.naming.*;

public class ColaboraContable extends FachadaTipoOrdenSubcuenta
        implements Serializable {

    // Nombre JNDI del Data Source que esta clase requiere
    private static final String DATA_SOURCE_NAME = "CommerceAccess";
    // Clase helper usada para acceder a la base de datos
    private JDBCAccess jdbcAccess;

    // Metodo constructor por defecto sin parametros
    public ColaboraContable() {

        // Al instanciar el Bean establece la conexion a la base de datos
        jdbcAccess = new JDBCAccess(DATA_SOURCE_NAME);
    }

    // listaCuenta
    public Vector listaCuenta() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        String selectStr =
                "SELECT tmpDctosContableDetalle.idSubcuenta,         "
                + "       SUM(tmpDctosContableDetalle.vrMovimiento)  "
                + "                                AS vrMovimiento,  "
                + "       tmpDctosContableDetalle.idAsiento,         "
                + "       tblContableSubcuenta.nombreSubcuenta       "
                + " FROM   tmpDctosContableDetalle                   "
                + " INNER JOIN tblContableSubcuenta                  "
                + " ON tmpDctosContableDetalle.idSubcuenta =         "
                + "                tblContableSubcuenta.idSubcuenta  "
                + " WHERE tmpDctosContableDetalle.idLocal =          "
                + getIdLocal() + "                                   "
                + " AND tmpDctosContableDetalle.fechaDcto BETWEEN   '"
                + getFechaInicialSqlServer() + "' AND               '"
                + getFechaFinalSqlServer() + "'                      "
                + " GROUP BY tmpDctosContableDetalle.idSubcuenta,    "
                + "         tmpDctosContableDetalle.idAsiento,       "
                + "         tblContableSubcuenta.nombreSubcuenta     "
                + "ORDER BY tmpDctosContableDetalle.idAsiento,       "
                + "         tmpDctosContableDetalle.idSubcuenta      ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaTipoOrdenSubcuenta fachadaBean;

            while (rs.next()) {

                //
                fachadaBean = new FachadaTipoOrdenSubcuenta();

                //
                fachadaBean.setIdAsiento(rs.getInt("idAsiento"));
                fachadaBean.setIdSubcuenta(rs.getString("idSubcuenta"));
                fachadaBean.setVrMovimiento(rs.getDouble("vrMovimiento"));
                fachadaBean.setNombreSubcuenta(rs.getString("nombreSubcuenta"));

                //
                contenedor.add(fachadaBean);
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
            return contenedor;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {

            // Cierra la conexion
            jdbcAccess.cleanup(connection, selectStatement, null);
            return contenedor;

        }
    }

    // listaDcto
    public Vector listaDcto() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        String selectStr =
              "    SELECT tmpDB.idDctoNitCC,                               "
            + "           tmpDB.idDcto,                                    "
            + "           tmpDB.idRecibo,                                  "
            + "           tmpDB.fechaDcto,                                 "
            + "           tmpDB.idCliente,                                 "
            + "           tmpDB.vrMovimiento AS vrDebito,                  "
            + "           tmpCR.vrMovimiento AS vrCredito                  "
            + "    FROM                                                    "
            + "    ( SELECT tmpDctosContableDetalle.idAsiento,             "
            + "           tmpDctosContableDetalle.idDcto,                  "
            + "           tmpDctosContableDetalle.idDctoNitCC,             "
            + "           MAX(tmpDctosContableDetalle.idRecibo)            "
            + "                                            AS idRecibo,    "
            + "           MAX(tmpDctosContableDetalle.idCliente)           "
            + "                                           AS idCliente,    "
            + "           MAX(tmpDctosContableDetalle.fechaDcto)           "
            + "                                           AS fechaDcto,    "
            + "		   tmpDctosContableDetalle.idOrden,                "
            + "           SUM(tmpDctosContableDetalle.vrMovimiento)        "
            + "                                    AS vrMovimiento         "
            + "      FROM   tmpDctosContableDetalle                        "
            + "      WHERE tmpDctosContableDetalle.idAsiento = 1           "
            + "    AND   tmpDctosContableDetalle.idLocal      =            "
            + getIdLocal()  + "                                            "      
            + "     AND tmpDctosContableDetalle.fechaDcto BETWEEN         '"
            + getFechaInicialSqlServer() + "' AND                         '"
            + getFechaFinalSqlServer() + "'                                "
            + "      GROUP BY tmpDctosContableDetalle.idAsiento,           "
            + "          tmpDctosContableDetalle.idDcto,                   "
	    + "		  tmpDctosContableDetalle.idOrden,                 "
            + "        tmpDctosContableDetalle.idDctoNitCC) AS tmpDB,      "
            + "    ( SELECT tmpDctosContableDetalle.idAsiento,             "
            + "           tmpDctosContableDetalle.idDcto,                  "
            + "           tmpDctosContableDetalle.idDctoNitCC,             "
            + "           MAX(tmpDctosContableDetalle.idRecibo)            "
            + "                                            AS idRecibo,    "            
            + "           MAX(tmpDctosContableDetalle.idCliente)           "
            + "                                           AS idCliente,    "
            + "           MAX(tmpDctosContableDetalle.fechaDcto)           "
            + "                                           AS fechaDcto,    "
            + "		   tmpDctosContableDetalle.idOrden,                "
            + "           SUM(tmpDctosContableDetalle.vrMovimiento)        "
            + "                                    AS vrMovimiento         "
            + "      FROM   tmpDctosContableDetalle                        "
            + "      WHERE tmpDctosContableDetalle.idAsiento = 2           "
            + "    AND   tmpDctosContableDetalle.idLocal      =            "
            + getIdLocal()  + "                                            "
            + "     AND tmpDctosContableDetalle.fechaDcto BETWEEN         '"
                + getFechaInicialSqlServer() + "' AND                     '"
                + getFechaFinalSqlServer() + "'                            "
            + "      GROUP BY tmpDctosContableDetalle.idAsiento,           "
            + "          tmpDctosContableDetalle.idDcto,                   "
            + "		  tmpDctosContableDetalle.idOrden,                 "
            + "         tmpDctosContableDetalle.idDctoNitCC) AS tmpCR      "
            + "    WHERE tmpDB.idDcto  = tmpCR.idDcto                      "
            + "    AND   tmpDB.idOrden = tmpCR.idOrden                     "
            + " ORDER BY 3 " ;

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaTipoOrdenSubcuenta fachadaBean;

            while (rs.next()) {

                //
                fachadaBean = new FachadaTipoOrdenSubcuenta();

                //
                fachadaBean.setIdDcto(rs.getInt("idDcto"));
                fachadaBean.setIdRecibo(rs.getInt("idRecibo"));
                fachadaBean.setIdDctoNitCC(rs.getString("idDctoNitCC"));
                fachadaBean.setFechaDcto(rs.getString("fechaDcto"));
                fachadaBean.setVrDebito(rs.getDouble("vrDebito"));
                fachadaBean.setVrCredito(rs.getDouble("vrCredito"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));


                //
                contenedor.add(fachadaBean);
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
            return contenedor;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {

            // Cierra la conexion
            jdbcAccess.cleanup(connection, selectStatement, null);
            return contenedor;

        }
    }

    // listaComprobante
    public Vector listaComprobante() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        String selectStr =
                "SELECT tblTipoOrdenSubcuenta.idComprobante     "
                + "FROM tblTipoOrdenSubcuenta                   "
                + "GROUP BY tblTipoOrdenSubcuenta.idComprobante "
                + "ORDER BY tblTipoOrdenSubcuenta.idComprobante ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaTipoOrdenSubcuenta fachadaBean;

            while (rs.next()) {

                //
                fachadaBean = new FachadaTipoOrdenSubcuenta();

                //
                fachadaBean.setIdComprobante(rs.getInt("idComprobante"));

                //
                contenedor.add(fachadaBean);
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
            return contenedor;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {

            // Cierra la conexion
            jdbcAccess.cleanup(connection, selectStatement, null);
            return contenedor;

        }
    }
}
