package com.solucionesweb.losbeans.colaboraciones;

// Importa la clase helper JDBCAccess que contiene los accesos a la base de datos
import com.solucionesweb.lasayudas.JDBCAccess;

// Importa el bean de utilidades
import com.solucionesweb.losbeans.utilidades.IConstantes;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaJobOperacionOperario;

// Importa los paquetes de java
import java.sql.*;
import java.io.*;
import java.util.*;
import java.io.IOException.*;

// Importa los paquetes de javax
import javax.naming.*;

public class ColaboraOperario extends FachadaJobOperacionOperario
        implements Serializable, IConstantes {

    // Nombre JNDI del Data Source que esta clase requiere
    private static final String DATA_SOURCE_NAME = "CommerceAccess";
    // Clase helper usada para acceder a la base de datos
    private JDBCAccess jdbcAccess;

    // Metodo constructor por defecto sin parametros
    public ColaboraOperario() {

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
                " SELECT tblJobOperacionOperario.idLocal,         "
                + "        tblJobOperacionOperario.idPeriodo,     "
                + "        tblJobOperacionOperario.idOperacion,   "
                + "        tblJobOperacion.nombreOperacion,       "
                + "        tblJobOperacionOperario.idOperario,    "
                + "        ctrlUsuarios.nombreUsuario             "
                + "                          AS nombreOperario    "
                + " FROM   tblJobOperacion                        "
                + " INNER JOIN tblJobOperacionOperario            "
                + " ON tblJobOperacion.idOperacion        =       "
                + "        tblJobOperacionOperario.idOperacion    "
                + " INNER JOIN ctrlUsuarios                       "
                + " ON tblJobOperacionOperario.idOperario =       "
                + "                     ctrlUsuarios.idUsuario    "
                + " WHERE tblJobOperacionOperario.idLocal =       "
                + getIdLocal() + "                                "
                + " ORDER BY tblJobOperacionOperario.idLocal,     "
                + "        tblJobOperacionOperario.idOperacion,   "
                + "        tblJobOperacion.nombreOperacion,       "
                + "        ctrlUsuarios.nombreUsuario ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaJobOperacionOperario fachadaBean;

            while (rs.next()) {

                //
                fachadaBean = new FachadaJobOperacionOperario();

                //
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdPeriodo(rs.getInt("idPeriodo"));
                fachadaBean.setIdOperacion(rs.getInt("idOperacion"));
                fachadaBean.setNombreOperacion(rs.getString("nombreOperacion"));
                fachadaBean.setIdOperario(rs.getDouble("idOperario"));
                fachadaBean.setNombreOperario(rs.getString("nombreOperario"));

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
