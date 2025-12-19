package com.solucionesweb.losbeans.negocio;

// Importa la clase helper JDBCAccess que contiene los accesos a la base de datos
import com.solucionesweb.lasayudas.JDBCAccess;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaJobCausa;
// Importa los paquetes de java
import java.sql.*;

//import java.util.Date;
import java.io.IOException.*;

// Importa los paquetes de javax
import java.util.Vector;
import javax.naming.*;

public class JobCausaBean extends FachadaJobCausa {

    // Nombre JNDI del Data Source que esta clase requiere
    private static final String DATA_SOURCE_NAME = "CommerceAccess";
    // Clase helper usada para acceder a la base de datos
    private JDBCAccess jdbcAccess;

    // Metodo constructor por defecto sin parametros
    public JobCausaBean() {

        // Al instanciar el Bean establece la conexion a la base de datos
        jdbcAccess = new JDBCAccess(DATA_SOURCE_NAME);
    }

    // listaAll
    public Vector listaAll() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        //
        String selectStr =
                " SELECT tblJobCausa.idTipoCausa       "
                + "       ,tblJobCausa.idCausa         "
                + "       ,tblJobCausa.nombreCausa     "
                + "       ,tblJobCausa.estado          "
                + " FROM tblJobCausa                   "
                + " WHERE tblJobCausa.idTipoCausa =    "
                + getIdTipoCausa() + "                 "
                + " ORDER BY tblJobCausa.nombreCausa   ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaJobCausa fachadaBean;

            while (rs.next()) {

                //
                fachadaBean = new FachadaJobCausa();

                //
                fachadaBean.setIdTipoCausa(rs.getInt("idTipoCausa"));
                fachadaBean.setIdCausa(rs.getInt("idCausa"));
                fachadaBean.setNombreCausa(rs.getString("nombreCausa"));
                fachadaBean.setEstado(rs.getInt("estado"));

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

    // listaAllOpcion
    public Vector listaAllOpcion() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        //
        String selectStr =
                " SELECT TOP 1 tblJobCausa.idTipoCausa "
                + "       ,00 AS idCausa               "
                + "       ,'NN' AS nombreCausa         "
                + "       ,tblJobCausa.estado          "
                + "       ,01 AS ordenSalida           "
                + " FROM tblJobCausa                   "
                + " WHERE tblJobCausa.idTipoCausa =    "
                + getIdTipoCausa() + "                 "
                + "UNION                               "
                + " SELECT tblJobCausa.idTipoCausa     "
                + "       ,tblJobCausa.idCausa         "
                + "       ,tblJobCausa.nombreCausa     "
                + "       ,tblJobCausa.estado          "
                + "       ,02 AS ordenSalida           "
                + " FROM tblJobCausa                   "
                + " WHERE tblJobCausa.idTipoCausa =    "
                + getIdTipoCausa() + "                 "
                + " ORDER BY 5, 3   ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaJobCausa fachadaBean;

            while (rs.next()) {

                //
                fachadaBean = new FachadaJobCausa();

                //
                fachadaBean.setIdTipoCausa(rs.getInt("idTipoCausa"));
                fachadaBean.setIdCausa(rs.getInt("idCausa"));
                fachadaBean.setNombreCausa(rs.getString("nombreCausa"));
                fachadaBean.setEstado(rs.getInt("estado"));

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

    // listaFCH
    public FachadaJobCausa listaFCH() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        //
        FachadaJobCausa fachadaBean = new FachadaJobCausa();

        Connection connection = null;

        //
        String selectStr =
                " SELECT tblJobCausa.idTipoCausa       "
                + "       ,tblJobCausa.idCausa         "
                + "       ,tblJobCausa.nombreCausa     "
                + "       ,tblJobCausa.estado          "
                + " FROM tblJobCausa                   "
                + " WHERE tblJobCausa.idTipoCausa =    "
                + getIdTipoCausa() + "                 "
                + " AND tblJobCausa.idCausa       =    "
                + getIdCausa();

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            if (rs.next()) {

                //
                fachadaBean.setIdTipoCausa(rs.getInt("idTipoCausa"));
                fachadaBean.setIdCausa(rs.getInt("idCausa"));
                fachadaBean.setNombreCausa(rs.getString("nombreCausa"));
                fachadaBean.setEstado(rs.getInt("estado"));

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
            return fachadaBean;

        }
    }
}
