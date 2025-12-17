package com.solucionesweb.losbeans.negocio;

// Importa la clase helper JDBCAccess que contiene los accesos a la base de datos
import com.solucionesweb.lasayudas.JDBCAccess;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaTipoOrdenCuenta;

// Importa los paquetes de java
import java.sql.*;
import java.io.*;
import java.util.*;
import java.io.IOException.*;

// Importa los paquetes de javax
import javax.naming.*;

public class TipoOrdenCuentaBean extends FachadaTipoOrdenCuenta
        implements Serializable {

    // Nombre JNDI del Data Source que esta clase requiere
    private static final String DATA_SOURCE_NAME = "CommerceAccess";

    // Clase helper usada para acceder a la base de datos
    private JDBCAccess jdbcAccess;

    // Metodo constructor por defecto sin parametros
    public TipoOrdenCuentaBean() {

        // Al instanciar el Bean establece la conexion a la base de datos
        jdbcAccess = new JDBCAccess(DATA_SOURCE_NAME);
    }

    // Metodo
    public Vector listaCuenta() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        String selectStr =
                "SELECT tblTipoOrdenCuenta.idLocal               " +
                "      ,tblTipoOrdenCuenta.idTipoOrden           " +
                "      ,tblTipoOrdenCuenta.idAsiento             " +
                "      ,tblTipoOrdenCuenta.idSubcuenta           " +
                "      ,tblTipoOrdenCuenta.nombreMovimiento      " +
                "      ,tblTipoOrdenCuenta.porcentajeIva         " +
                "      ,tblTipoOrdenCuenta.estado                " +
                "      ,tblTipoOrdenCuenta.idSeq                 " +
                "      ,tblTipoOrdenCuenta.idComprobanteContable " +
                "FROM tblTipoOrdenCuenta                         " +
                "WHERE tblTipoOrdenCuenta.idLocal     =          " +
                getIdLocal() + "                                 " +
                "AND   tblTipoOrdenCuenta.idTipoOrden =          " +
                getIdTipoOrden() + "                             " +
                "ORDER BY tblTipoOrdenCuenta.idLocal             " +
                "        ,tblTipoOrdenCuenta.idTipoOrden         " +
                "        ,tblTipoOrdenCuenta.idAsiento           ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaTipoOrdenCuenta fachadaBean;

            while (rs.next()) {

                //
                fachadaBean = new FachadaTipoOrdenCuenta();

                //
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setIdAsiento(rs.getString("idAsiento"));
                fachadaBean.setIdSubcuenta(rs.getString("idSubcuenta"));
                fachadaBean.setNombreMovimiento(
                                              rs.getString("nombreMovimiento"));
                fachadaBean.setPorcentajeIva(rs.getDouble("porcentajeIva"));
                fachadaBean.setEstado(rs.getInt("estado"));
                fachadaBean.setIdSeq(rs.getInt("idSeq"));
                fachadaBean.setIdComprobanteContable(
                                         rs.getString("idComprobanteContable"));

                //
                contenedor.add(fachadaBean);

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
            return contenedor;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {

            // Cierra la conexion
            return contenedor;

        }
    }
}