package com.solucionesweb.losbeans.colaboraciones;

// Importa la clase helper JDBCAccess que contiene los accesos a la base de datos
import com.solucionesweb.lasayudas.JDBCAccess;

// Importa el bean de utilidades
import com.solucionesweb.losbeans.utilidades.IConstantes;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaDctoCuadre;

// Importa los paquetes de java
import java.sql.*;
import java.io.*;
import java.util.*;
import java.io.IOException.*;

// Importa los paquetes de javax
import javax.naming.*;

public class ColaboraDctoCuadre extends FachadaDctoCuadre
        implements Serializable, IConstantes{



        // Nombre JNDI del Data Source que esta clase requiere
    private static final String DATA_SOURCE_NAME = "CommerceAccess";
    // Clase helper usada para acceder a la base de datos
    private JDBCAccess jdbcAccess;

    // Metodo constructor por defecto sin parametros
    public ColaboraDctoCuadre() {

        // Al instanciar el Bean establece la conexion a la base de datos
        jdbcAccess = new JDBCAccess(DATA_SOURCE_NAME);
    }

    // Metodo
    public Vector listaCuadre() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        String selectStr =
            "SELECT fechaCuadre             "+
            "      ,(CASE WHEN              "+
            "           estadoCuadre = 1    "+
            "           THEN  'CERRADO'     "+
            "           ELSE  'ABIERTO'     "+
            "         END) AS nombreEstado  "+
            "      ,saldoInicial            "+
            "      ,vrIngreso               "+
            "      ,vrEgreso                "+
            "      ,saldoFinal              "+
            "      ,fechaOperacion          "+
            "  FROM tblDctosCuadre          "+
            "WHERE indicador BETWEEN "+getIndicadorInicial()+" "+
            "AND "+getIndicadorFinal()+" "+
            "ORDER BY fechaCuadre DESC      ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaDctoCuadre fachadaBean;

            while (rs.next()) {

                //
                fachadaBean = new FachadaDctoCuadre();

                //
                fachadaBean.setFechaCuadre(rs.getString("fechaCuadre"));
                fachadaBean.setNombreEstado(rs.getString("nombreEstado"));
                fachadaBean.setSaldoInicial(rs.getDouble("saldoInicial"));
                fachadaBean.setVrIngreso(rs.getDouble("vrIngreso"));
                fachadaBean.setVrEgreso(rs.getDouble("vrEgreso"));
                fachadaBean.setSaldoFinal(rs.getDouble("saldoFinal"));
                fachadaBean.setFechaOperacion(rs.getString("fechaOperacion"));

               
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
