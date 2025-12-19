package com.solucionesweb.losbeans.negocio;

// Importa la clase helper JDBCAccess que contiene los accesos a la base de datos
import com.solucionesweb.lasayudas.JDBCAccess;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaLocalCaja;

// Importa los paquetes de java
import java.sql.*;
import java.io.*;
import java.io.IOException.*;

// Importa la interface de las constantes
import com.solucionesweb.losbeans.utilidades.IConstantes;

// Importa los paquetes de javax
import javax.naming.*;

public class LocalCajaBean extends FachadaLocalCaja
        implements Serializable, IConstantes {

    // Nombre JNDI del Data Source que esta clase requiere
    private static final String DATA_SOURCE_NAME = "CommerceAccess";

    // Clase helper usada para acceder a la base de datos
    private JDBCAccess jdbcAccess;

    // Metodo constructor por defecto sin parametros
    public LocalCajaBean() {

        // Al instanciar el Bean establece la conexion a la base de datos
        jdbcAccess = new JDBCAccess(DATA_SOURCE_NAME);
    }


    //listaUnLocal
    public FachadaLocalCaja listaFCH() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        FachadaLocalCaja fachadaBean = new FachadaLocalCaja();

        //
        Connection connection = null;

        String selectStr =
                "SELECT tblLocalesCaja.idLocal    " +
                "      ,tblLocalesCaja.idCaja     " +
                "      ,tblLocalesCaja.idTipoCaja " +
                "      ,tblLocalesCaja.textoRango " +
                "      ,tblLocalesCaja.rango      " +
                "      ,tblLocalesCaja.prefijo    " +
                "      ,tblLocalesCaja.estado     " +
                "      ,tblLocalesCaja.idSeq      " +
                "      ,tblLocalesCaja.ipLocal    " +
                "      ,tblLocalesCaja.resolucion " +
                "FROM tblLocalesCaja              " +
                "WHERE tblLocalesCaja.ipLocal =  '" +
                getIpLocal() + "'";

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
                fachadaBean.setIdCaja(rs.getInt("idLocal"));
                fachadaBean.setIdTipoCaja(rs.getInt("idTipoCaja"));
                fachadaBean.setTextoRango(rs.getString("textoRango"));
                fachadaBean.setRango(rs.getString("rango"));
                fachadaBean.setPrefijo(rs.getString("prefijo"));
                fachadaBean.setEstado(rs.getInt("estado"));
                fachadaBean.setIdSeq(rs.getInt("idSeq"));
                fachadaBean.setIpLocal(rs.getString("ipLocal"));
                fachadaBean.setResolucion(rs.getString("resolucion"));

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