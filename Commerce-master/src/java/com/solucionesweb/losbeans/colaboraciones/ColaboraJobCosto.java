package com.solucionesweb.losbeans.colaboraciones;

// Importa la clase helper JDBCAccess que contiene los accesos a la base de datos
import com.solucionesweb.lasayudas.JDBCAccess;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaJobOperacionCosto;

// Importa los paquetes de java
import java.sql.*;
import java.io.*;
import java.util.*;
import java.io.IOException.*;

// Importa los paquetes de javax
import javax.naming.*;

public class ColaboraJobCosto extends FachadaJobOperacionCosto
        implements Serializable {

    // Nombre JNDI del Data Source que esta clase requiere
    private static final String DATA_SOURCE_NAME = "CommerceAccess";
    // Clase helper usada para acceder a la base de datos
    private JDBCAccess jdbcAccess;

    // Metodo constructor por defecto sin parametros
    // Metodo constructor por defecto sin parametros
    public ColaboraJobCosto() {

        // Al instanciar el Bean establece la conexion a la base de datos
        jdbcAccess = new JDBCAccess(DATA_SOURCE_NAME);

    }

    // lista
    public Vector lista() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        //
        String selectStr =
                " SELECT tblJobOperacionCosto.idLocal,         "
                + "        tblJobOperacionCosto.idCosto,       "
                + "        tblJobCosto.nombreCosto,            "
                + "        tblJobOperacionCosto.idOperacion,   "
                + "        tblJobOperacion.nombreOperacion,    "
                + "        tblJobOperacionCosto.idPeriodo,     "
                + "        tblJobOperacionCosto.cantidadBase,  "
                + "        tblJobOperacionCosto.vrCostoBase,   "
                + "        tblJobOperacionCosto.estado         "
                + " FROM tblJobCosto                           "
                + " INNER JOIN tblJobOperacionCosto            "
                + " ON tblJobCosto.idCosto              =      "
                + "             tblJobOperacionCosto.idCosto   "
                + " INNER JOIN tblJobOperacion                 "
                + " ON tblJobOperacionCosto.idOperacion =      "
                + "              tblJobOperacion.idOperacion   "
                + " WHERE tblJobOperacionCosto.idLocal  =      "
                + getIdLocal() + "                             "
                + " ORDER BY tblJobOperacionCosto.idLocal,     "
                + "          tblJobCosto.nombreCosto,          "
                + "          tblJobOperacion.nombreOperacion" ;

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaJobOperacionCosto fachadaBean;

            //
            while (rs.next()) {

                //
                fachadaBean = new FachadaJobOperacionCosto();

                //
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdCosto(rs.getInt("idCosto"));
                fachadaBean.setIdOperacion(rs.getInt("idOperacion"));
                fachadaBean.setIdPeriodo(rs.getInt("idPeriodo"));
                fachadaBean.setCantidadBase(rs.getDouble("cantidadBase"));
                fachadaBean.setVrCostoBase(rs.getDouble("vrCostoBase"));
                fachadaBean.setNombreCosto(rs.getString("nombreCosto"));
                fachadaBean.setNombreOperacion(rs.getString("nombreOperacion"));
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

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);


        } finally {

            // Cierra la conexion
            jdbcAccess.cleanup(connection, selectStatement, null);
            return contenedor;


        }
    }
}
