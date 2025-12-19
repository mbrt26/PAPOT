package com.solucionesweb.losbeans.negocio;

// Importa la clase helper JDBCAccess que contiene los accesos a la base de datos
import com.solucionesweb.lasayudas.JDBCAccess;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaLucroBonificacionUsuario;

// Importa los paquetes de java
import java.sql.*;
import java.io.*;
import java.io.IOException.*;

// Importa los paquetes de javax
import javax.naming.*;

public class LucroBonificacionUsuarioBean extends
                       FachadaLucroBonificacionUsuario implements Serializable {

    // Nombre JNDI del Data Source que esta clase requiere
    private static final String DATA_SOURCE_NAME = "CommerceAccess";

    // Clase helper usada para acceder a la base de datos
    private JDBCAccess jdbcAccess;

    // Metodo constructor por defecto sin parametros
    public LucroBonificacionUsuarioBean() {

        // Al instanciar el Bean establece la conexion a la base de datos
        jdbcAccess = new JDBCAccess(DATA_SOURCE_NAME);
    }

    // valorMes
    public double valorMes() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        Connection connection = null;

        //
        double xValorMes      = 0;

        //
        String selectStr =
                "SELECT tblLucroBonificacionUsuario.valor        " +
                "FROM   tblLucroBonificacionUsuario              " +
                "WHERE  tblLucroBonificacionUsuario.idUsuario  = " +
                getIdUsuario() + "                               " +
                "AND    tblLucroBonificacionUsuario.idAno      = " +
                getIdAno() + "                                   " +
                "AND    tblLucroBonificacionUsuario.idMes      = " +
                getIdMes();
 
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
                xValorMes  = rs.getDouble("valor");

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
            return xValorMes;
            
        }
    }

    // valorTrimestre
    public double valorTrimestre(int xIdMesInicial,
                                 int xIdMesFinal) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        Connection connection = null;

        //
        double xValorMes      = 0;

        //
        String selectStr =
                "SELECT SUM(tblLucroBonificacionUsuario.valor)    " +
                "                                      AS valor   " +
                "FROM   tblLucroBonificacionUsuario               " +
                "WHERE  tblLucroBonificacionUsuario.idUsuario   = " +
                getIdUsuario() + "                                " +
                "AND    tblLucroBonificacionUsuario.idAno       = " +
                getIdAno() + "                                    " +
                "AND    tblLucroBonificacionUsuario.idMes BETWEEN " +
                xIdMesInicial + " AND                             " +
                xIdMesFinal;


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
                xValorMes  = rs.getDouble("valor");

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
            return xValorMes;

        }
    }
}
