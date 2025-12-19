package com.solucionesweb.losbeans.negocio;

// Importa la clase helper JDBCAccess que contiene los accesos a la base de datos
import com.solucionesweb.lasayudas.JDBCAccess;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaLocalBodega;

// Importa los paquetes de java
import java.sql.*;
import java.io.*;
import java.util.*;
import java.io.IOException.*;

// Importa la interface de las constantes
import com.solucionesweb.losbeans.utilidades.IConstantes;

// Importa los paquetes de javax
import javax.naming.*;

public class LocalBodegaBean extends FachadaLocalBodega
        implements Serializable, IConstantes {

    // Nombre JNDI del Data Source que esta clase requiere
    private static final String DATA_SOURCE_NAME = "CommerceAccess";

    // Clase helper usada para acceder a la base de datos
    private JDBCAccess jdbcAccess;

    // Metodo constructor por defecto sin parametros
    public LocalBodegaBean() {

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

        String selectStr =
                "SELECT tblLocalesBodega.idLocal      " +
                "      ,tblLocalesBodega.idBodega     " +
                "      ,tblLocalesBodega.nombreBodega " +
                "      ,tblLocalesBodega.estado       " +
                "      ,tblLocalesBodega.idSeq        " +
                "FROM tblLocalesBodega                " +
                "WHERE tblLocalesBodega.idLocal =     " +
                getIdLocal() + "                      " +
                "AND   tblLocalesBodega.estado  = 1   " +
                "ORDER BY  tblLocalesBodega.idLocal   " +
                "         ,tblLocalesBodega.idBodega  ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaLocalBodega fachadaBean;

            while (rs.next()) {

                fachadaBean = new FachadaLocalBodega();

                //
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdBodega(rs.getInt("idBodega"));
                fachadaBean.setNombreBodega(rs.getString("nombreBodega"));
                fachadaBean.setEstado(rs.getInt("estado"));
                fachadaBean.setIdSeq(rs.getInt("idSeq"));

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

    // listaOpcion
    public Vector listaOpcion() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        Vector contenedor = new Vector();
        Connection connection = null;

        String selectStr =
                "SELECT TOP 1 tblLocalesBodega.idLocal " +
                "      ,00 AS idBodega                 " +
                "      ,'TODAS' AS nombreBodega        " +
                "      ,tblLocalesBodega.estado        " +
                "      ,tblLocalesBodega.idSeq         " +
                "      ,01 AS ordenSalidad             " +
                "FROM tblLocalesBodega                 " +
                "WHERE tblLocalesBodega.idLocal =      " +
                getIdLocal() + "                       " +
                "UNION                                 " +
                "SELECT tblLocalesBodega.idLocal       " +
                "      ,tblLocalesBodega.idBodega      " +
                "      ,tblLocalesBodega.nombreBodega  " +
                "      ,tblLocalesBodega.estado        " +
                "      ,tblLocalesBodega.idSeq         " +
                "      ,02 AS ordenSalidad             " +
                "FROM tblLocalesBodega                 " +
                "WHERE tblLocalesBodega.idLocal =      " +
                getIdLocal() + "                       " +
                "AND   tblLocalesBodega.estado  = 1    " +
                "ORDER BY  6,1,3                       " ;


        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaLocalBodega fachadaBean;

            while (rs.next()) {

                fachadaBean = new FachadaLocalBodega();

                //
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdBodega(rs.getInt("idBodega"));
                fachadaBean.setNombreBodega(rs.getString("nombreBodega"));
                fachadaBean.setEstado(rs.getInt("estado"));
                fachadaBean.setIdSeq(rs.getInt("idSeq"));

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

    // listaUnFCH
    public FachadaLocalBodega listaUnFCH() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        //
        FachadaLocalBodega fachadaBean = new FachadaLocalBodega();

        Connection connection = null;

        String selectStr =
                "SELECT tblLocalesBodega.idLocal      " +
                "      ,tblLocalesBodega.idBodega     " +
                "      ,tblLocalesBodega.nombreBodega " +
                "      ,tblLocalesBodega.estado       " +
                "      ,tblLocalesBodega.idSeq        " +
                "FROM tblLocalesBodega                " +
                "WHERE tblLocalesBodega.idLocal =     " +
                getIdLocal() + "                      " +
                "AND   tblLocalesBodega.estado    = 1 " +
                "AND   tblLocalesBodega.idBodega  =   " +
                getIdBodega() + "                     " +
                "ORDER BY  tblLocalesBodega.idLocal   " +
                "         ,tblLocalesBodega.idBodega  ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            if (rs.next()) {

                //
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdBodega(rs.getInt("idBodega"));
                fachadaBean.setNombreBodega(rs.getString("nombreBodega"));
                fachadaBean.setEstado(rs.getInt("estado"));
                fachadaBean.setIdSeq(rs.getInt("idSeq"));

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
            return fachadaBean;
        }
    }

    // listaUn
    public Vector listaUn() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();

        Connection connection = null;

        String selectStr =
                "SELECT tblLocalesBodega.idLocal      " +
                "      ,tblLocalesBodega.idBodega     " +
                "      ,tblLocalesBodega.nombreBodega " +
                "      ,tblLocalesBodega.estado       " +
                "      ,tblLocalesBodega.idSeq        " +
                "FROM tblLocalesBodega                " +
                "WHERE tblLocalesBodega.idLocal =     " +
                getIdLocal() + "                      " +
                "AND   tblLocalesBodega.estado    = 1 " +
                "AND   tblLocalesBodega.idBodega  =   " +
                getIdBodega() + "                     " +
                "ORDER BY  tblLocalesBodega.idLocal   " +
                "         ,tblLocalesBodega.idBodega  ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaLocalBodega fachadaBean;

            if (rs.next()) {

                fachadaBean = new FachadaLocalBodega();

                //
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdBodega(rs.getInt("idBodega"));
                fachadaBean.setNombreBodega(rs.getString("nombreBodega"));
                fachadaBean.setEstado(rs.getInt("estado"));
                fachadaBean.setIdSeq(rs.getInt("idSeq"));

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

            // Cierra la conexion a la base de datos limpiando la statement usada y la
            jdbcAccess.cleanup(connection, selectStatement, null);
            return contenedor;

        }
    }
}
