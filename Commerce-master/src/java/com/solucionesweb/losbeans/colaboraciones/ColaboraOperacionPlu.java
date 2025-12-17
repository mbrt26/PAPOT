package com.solucionesweb.losbeans.colaboraciones;

// Importa la clase helper JDBCAccess que contiene los accesos a la base de datos
import com.solucionesweb.lasayudas.JDBCAccess;

// Importa el bean de utilidades
import com.solucionesweb.losbeans.utilidades.IConstantes;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaPluFicha;

// Importa los paquetes de java
import java.sql.*;
import java.io.*;
import java.util.*;
import java.io.IOException.*;

// Importa los paquetes de javax
import javax.naming.*;

public class ColaboraOperacionPlu extends FachadaPluFicha
        implements Serializable, IConstantes {

    // Nombre JNDI del Data Source que esta clase requiere
    private static final String DATA_SOURCE_NAME = "CommerceAccess";
    // Clase helper usada para acceder a la base de datos
    private JDBCAccess jdbcAccess;

    // Metodo constructor por defecto sin parametros
    public ColaboraOperacionPlu() {

        // Al instanciar el Bean establece la conexion a la base de datos
        jdbcAccess = new JDBCAccess(DATA_SOURCE_NAME);
    }

    // listaPlu
    public Vector listaPlu() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        //
        String selectStr =
                " SELECT tblPlusFicha.idCliente,           "
                + "        tblPlusFicha.referenciaCliente, "
                + "        tblPlusFicha.idOperacion,       "
                + "        tblPlusFicha.idPlu,             "
                + "        tblPlusFicha.nombreReferencia,  "
                + "        tblPlusFicha.idEscala,          "
                + "        tblPlusFicha.item,              "
                + "        tblPlusFicha.vrEscala,          "
                + "        tblPlusFicha.textoEscala,       "
                + "        tblPlusFicha.estado,            "
                + "        tblPlusFicha.idFicha,           "
                + "        tblPlusFicha.referencia         "
                + " FROM   tblPlusFicha                    "
                + " INNER JOIN tblJobEscala                "
                + " ON tblPlusFicha.idEscala =             "
                + "                  tblJobEscala.idEscala "
                + " INNER JOIN tblPlusOperacion            "
                + " ON tblPlusFicha.idOperacion    =       "
                + "         tblPlusOperacion.idOperacion   "
                + " AND tblPlusFicha.idEscala      =       "
                + "            tblPlusOperacion.idEscala   "
                + " WHERE tblPlusFicha.idFicha     =       "
                + getIdFicha() + "                         "
                + " AND   tblPlusFicha.idOperacion =       "
                + getIdOperacion() + "                     "
                + " AND   tblPlusFicha.vrEscala   != 0     ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaPluFicha fachadaBean;

            while (rs.next()) {

                //
                fachadaBean = new FachadaPluFicha();

                //
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setReferenciaCliente(
                        rs.getString("referenciaCliente"));
                fachadaBean.setIdOperacion(rs.getInt("idOperacion"));
                fachadaBean.setIdPlu(rs.getInt("idPlu"));
                fachadaBean.setNombreReferencia(
                        rs.getString("nombreReferencia"));
                fachadaBean.setNombreEscala(rs.getString("nombreEscala"));
                fachadaBean.setIdTipoEscala(rs.getInt("idTipoEscala"));
                fachadaBean.setIdEscala(rs.getInt("idEscala"));
                fachadaBean.setItem(rs.getInt("item"));
                fachadaBean.setVrEscala(rs.getDouble("vrEscala"));
                fachadaBean.setTextoEscala(rs.getString("textoEscala"));
                fachadaBean.setEstado(rs.getInt("estado"));
                fachadaBean.setIdFicha(rs.getInt("idFicha"));
                fachadaBean.setReferencia(rs.getString("referencia"));

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

    // totalMP
    public double totalMP() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        double xVrTotal = 0.0;

        Connection connection = null;

        //
        String selectStr =
                " SELECT SUM(tmpTOT.vrTotal) AS vrTotal     "
                + " FROM (                                  "
                + " SELECT SUM(tblPlusFicha.vrEscala)       "
                + "                           AS vrTotal    "
                + " FROM   tblPlusFicha                     "
                + " INNER JOIN tblJobEscala                 "
                + " ON tblPlusFicha.idEscala =              "
                + "                  tblJobEscala.idEscala  "
                + " INNER JOIN tblPlusOperacion             "
                + " ON tblPlusFicha.idOperacion    =        "
                + "         tblPlusOperacion.idOperacion    "
                + " AND tblPlusFicha.idEscala      =        "
                + "            tblPlusOperacion.idEscala    "
                + " WHERE tblPlusFicha.idFicha     =        "
                + getIdFicha() + "                          "
                + " AND   tblPlusFicha.idOperacion =        "
                + getIdOperacion() + "                      "
                + " AND   tblPlusFicha.vrEscala   != 0      "
                + " AND  tblPlusFicha.idEscala NOT IN (620) "
                + " UNION                                   "
                + " SELECT CAST(tblPlusFicha.textoEscala    "
                + "              AS DECIMAL(10,2)) vrTotal  "
                + " FROM tblPlusFicha                       "
                + " WHERE  (tblPlusFicha.idFicha =          "
                + getIdFicha() + " )                        "
                + " AND (tblPlusFicha.idOperacion =         "
                + getIdOperacion() + " )                    "
                + "   AND  tblPlusFicha.vrEscala != 0       "
                + " AND tblPlusFicha.idEscala IN (615,616)) "
                + "                               AS tmpTOT " ;

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
                xVrTotal = rs.getDouble("vrTotal");

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
            return xVrTotal;

        }
    }

    // listaPromedioMP
    public Vector listaPromedioMP(double xVrTotalMP) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        //
        String selectStr =
                " SELECT (tblPlusFicha.vrEscala /           "
                + xVrTotalMP + " )  AS vrPromedio,          "
                + "        tblPlusOperacion.idPlu           "
                + " FROM   tblPlusFicha                     "
                + " INNER JOIN tblJobEscala                 "
                + " ON tblPlusFicha.idEscala =              "
                + "                  tblJobEscala.idEscala  "
                + " INNER JOIN tblPlusOperacion             "
                + " ON tblPlusFicha.idOperacion    =        "
                + "         tblPlusOperacion.idOperacion    "
                + " AND tblPlusFicha.idEscala      =        "
                + "            tblPlusOperacion.idEscala    "
                + " WHERE tblPlusFicha.idFicha     =        "
                + getIdFicha() + "                          "
                + " AND   tblPlusFicha.idOperacion =        "
                + getIdOperacion() + "                      "
                + " AND   tblPlusFicha.vrEscala   != 0      ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaPluFicha fachadaBean;

            while (rs.next()) {

                //
                fachadaBean = new FachadaPluFicha();

                //
                fachadaBean.setVrPromedio(rs.getDouble("vrPromedio"));
                fachadaBean.setIdPlu(rs.getInt("idPlu"));

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
