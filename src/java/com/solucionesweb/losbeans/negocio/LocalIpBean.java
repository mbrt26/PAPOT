package com.solucionesweb.losbeans.negocio;

// Importa la clase helper JDBCAccess que contiene los accesos a la base de datos
import com.solucionesweb.lasayudas.JDBCAccess;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaLocalIp;

// Importa los paquetes de java
import java.sql.*;
import java.io.*;
import java.io.IOException.*;

// Importa la interface de las constantes
import com.solucionesweb.losbeans.utilidades.IConstantes;

// Importa los paquetes de javax
import java.util.Vector;
import javax.naming.*;

public class LocalIpBean extends FachadaLocalIp
        implements Serializable, IConstantes {

    // Nombre JNDI del Data Source que esta clase requiere
    private static final String DATA_SOURCE_NAME = "CommerceAccess";
    // Clase helper usada para acceder a la base de datos
    private JDBCAccess jdbcAccess;

    // Metodo constructor por defecto sin parametros
    public LocalIpBean() {

        // Al instanciar el Bean establece la conexion a la base de datos
        jdbcAccess = new JDBCAccess(DATA_SOURCE_NAME);

    }

    // listaAllHijo
    public Vector listaAllHijo() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        Vector contenedor = new Vector();
        Connection connection = null;

        String selectStr =
                "SELECT tblLocalesIp.idLocal          "
                + "      ,tblLocalesIp.ip             "
                + "      ,tblLocalesIp.hostName       "
                + "      ,tblLocalesIp.estado         "
                + "      ,tblLocalesIp.idSeq          "
                + "      ,tblLocalesIp.idLocalPadre   "
                + "      ,tblLocalesIp.bdNameContable "
                + "      ,tblLocalesIp.puertoHttp     "
                + "FROM tblLocalesIp                  "
                + "WHERE tblLocalesIp.idLocalPadre =  "
                + getIdLocalPadre() + "               "
                + "ORDER BY tblLocalesIp.idLocal      ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaLocalIp fachadaBean;

            while (rs.next()) {

                fachadaBean = new FachadaLocalIp();

                //
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIp(rs.getString("ip"));
                fachadaBean.setHostName(rs.getString("hostName"));
                fachadaBean.setEstado(rs.getInt("estado"));
                fachadaBean.setIdSeq(rs.getInt("idSeq"));
                fachadaBean.setIdLocalPadre(rs.getInt("idLocalPadre"));
                fachadaBean.setBdNameContable(rs.getString("bdNameContable"));
                fachadaBean.setPuertoHttp(rs.getString("puertoHttp"));

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
            // Cierra la conexion a la base de datos limpiando la statement usada y la
            jdbcAccess.cleanup(connection, selectStatement, null);
            return contenedor;
        }
    }

    //listaUnLocal
    public FachadaLocalIp listaUnLocal() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();


        FachadaLocalIp fachadaBean = new FachadaLocalIp();
        Connection connection = null;

        String selectStr =
                "SELECT tblLocalesIp.idLocal          "
                + "      ,tblLocalesIp.ip             "
                + "      ,tblLocalesIp.hostName       "
                + "      ,tblLocalesIp.estado         "
                + "      ,tblLocalesIp.idSeq          "
                + "      ,tblLocalesIp.idLocalPadre   "
                + "      ,tblLocalesIp.bdNameContable "
                + "      ,tblLocalesIp.puertoHttp     "
                + "FROM tblLocalesIp                  "
                + "WHERE idLocal                   =  "
                + getIdLocal();

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
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIp(rs.getString("ip"));
                fachadaBean.setHostName(rs.getString("hostName"));
                fachadaBean.setEstado(rs.getInt("estado"));
                fachadaBean.setIdSeq(rs.getInt("idSeq"));
                fachadaBean.setIdLocalPadre(rs.getInt("idLocalPadre"));
                fachadaBean.setBdNameContable(rs.getString("bdNameContable"));
                fachadaBean.setPuertoHttp(rs.getString("puertoHttp"));

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
            return fachadaBean;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {
            // Cierra la conexion a la base de datos limpiando la statement usada y la
            jdbcAccess.cleanup(connection, selectStatement, null);
            return fachadaBean;
        }
    }
}
