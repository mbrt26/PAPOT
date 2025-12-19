package com.solucionesweb.losbeans.colaboraciones;

// Importa la clase helper JDBCAccess que contiene los accesos a la base de datos
import com.solucionesweb.lasayudas.JDBCAccess;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaListaPrecioBean;

// Importa los paquetes de java
import java.sql.*;
import java.io.*;
import java.util.*;
import java.io.IOException.*;

// Importa los paquetes de javax
import javax.naming.*;

public class ColaboraListaPrecioBean extends FachadaListaPrecioBean
        implements Serializable {

    // Nombre JNDI del Data Source que esta clase requiere
    private static final String DATA_SOURCE_NAME = "CommerceAccess";

    // Clase helper usada para acceder a la base de datos
    private JDBCAccess jdbcAccess;

    // Metodo constructor por defecto sin parametros
    public ColaboraListaPrecioBean() {

        // Al instanciar el Bean establece la conexion a la base de datos
        jdbcAccess = new JDBCAccess(DATA_SOURCE_NAME);
    }

    // obtieneListaPreciosVendedor
    public FachadaListaPrecioBean obtieneListaPreciosVendedor(String strIdUsuario) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        FachadaListaPrecioBean fachadaListaPrecioBean = new FachadaListaPrecioBean();

        Connection connection = null;

        String selectStr =
                "SELECT tblListaPrecios.strIdLista,     " +
                "       tblListaPrecios.idLocal,        " +
                "       tblListaPrecios.nombreLista     " +
                "FROM tblListaPrecios                   " +
                "WHERE tblListaPrecios.idUsuario = ( ? )";


        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            //inicializa los valores de los parametros
            selectStatement.setString(1, strIdUsuario);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            while (rs.next()) {

                fachadaListaPrecioBean.setStrIdLista(rs.getString("strIdLista"));
                fachadaListaPrecioBean.setIdLocal(rs.getInt("idLocal"));
                fachadaListaPrecioBean.setNombreLista(rs.getString("nombreLista"));

            }

            // Cierra el Resultset
            rs.close();
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
            return fachadaListaPrecioBean;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {

            // Cierra la conexion
            return fachadaListaPrecioBean;

        }
    }

    // listaPrecioReferencia
    public double listaPrecioReferencia(String strIdReferencia,
            String strIdLista) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        double vrVentaUnitario = 0.0;

        Connection connection = null;

        String selectStr =
                "SELECT [Price_Breaks].PRTNUM_88         " +
                "                    AS strIdReferencia, " +
                "       [Price_Breaks].PRICE_88          " +
                "                    AS vrUnitarioVenta  " +
                "FROM  [MAXCAM].[dbo].[Price_Breaks] AS Price_Breaks         " +
                "WHERE  [Price_Breaks].PRTNUM_88 = '" + strIdReferencia + "' " +
                "AND    [Price_Breaks].CUSTYP_88 = '" + strIdLista + "' ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            if (rs.next()) {

                vrVentaUnitario = rs.getDouble("vrUnitarioVenta");

            }

            // Cierra el Resultset
            rs.close();
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
            return vrVentaUnitario;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {

            // Cierra la conexion
            return vrVentaUnitario;

        }
    }

    // listaPrecioGeneral
    public double listaPrecioGeneral(String strIdReferencia) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        double vrVentaUnitario = 0.0;

        Connection connection = null;

        String selectStr =
                "SELECT [Part_Sales].PRTNUM_29          " +
                "                   AS strIdReferencia, " +
                "       [Part_Sales].PRICE_29           " +
                "                   AS vrUnitarioVenta  " +
                "FROM   [MAXCAM].[dbo].[Part_Sales] AS Part_Sales  " +
                "WHERE  [Part_Sales].PRTNUM_29 = '" + strIdReferencia + "'";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            if (rs.next()) {

                vrVentaUnitario = rs.getDouble("vrUnitarioVenta");

            }

            // Cierra el Resultset
            rs.close();
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
            return vrVentaUnitario;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {

            // Cierra la conexion
            return vrVentaUnitario;

        }
    }
}