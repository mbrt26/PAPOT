package com.solucionesweb.losbeans.negocio;

// Importa la clase helper JDBCAccess que contiene los accesos a la base de datos
import com.solucionesweb.lasayudas.JDBCAccess;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaJobOperacionCosto;

// Importa los paquetes de java
import java.sql.*;
import java.io.*;
import java.io.IOException.*;

// Importa los paquetes de javax
import javax.naming.*;

public class JobOperacionCostoBean extends FachadaJobOperacionCosto
        implements Serializable {

    // Nombre JNDI del Data Source que esta clase requiere
    private static final String DATA_SOURCE_NAME = "CommerceAccess";
    // Clase helper usada para acceder a la base de datos
    private JDBCAccess jdbcAccess;

    // Metodo constructor por defecto sin parametros
    public JobOperacionCostoBean() {

        // Al instanciar el Bean establece la conexion a la base de datos
        jdbcAccess = new JDBCAccess(DATA_SOURCE_NAME);
    }

    // ingresa
    public boolean ingresa() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                "INSERT INTO tblJobOperacionCosto "
                + "           (idLocal            "
                + "           ,idCosto            "
                + "           ,idOperacion        "
                + "           ,idPeriodo          "
                + "           ,cantidadBase       "
                + "           ,vrCostoBase        "
                + "           ,estado)            "
                + "VALUES (  " + getIdLocal() + ","
                + getIdCosto() + ","
                + getIdOperacion() + ","
                + getIdPeriodo() + ","
                + getCantidadBase() + ","
                + getVrCostoBase() + ","
                + getEstado() + ")";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);


            // Obtiene el resultset
            selectStatement.execute();
            okIngresar = true;

            // Cierra el Resultset

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
            return okIngresar;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {

            // Cierra la conexion a la base de datos limpiando la statement usada y la
            jdbcAccess.cleanup(connection, selectStatement, null);
            return okIngresar;
        }
    }

    // retira
    public boolean retira() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =
                " DELETE FROM tblJobOperacionCosto          "
                + " WHERE  tblJobOperacionCosto.idLocal   = "
                + getIdLocal() + "                          "
                + " AND  tblJobOperacionCosto.idOperacion = "
                + getIdOperacion() + "                    "
                + " AND  tblJobOperacionCosto.idCosto     = "
                + getIdCosto();

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);


            // Obtiene el resultset
            selectStatement.execute();
            okIngresar = true;

            // Cierra el Resultset

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
            return okIngresar;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {

            // Cierra la conexion a la base de datos limpiando la statement usada y la
            jdbcAccess.cleanup(connection, selectStatement, null);
            return okIngresar;
        }
    }

    // listaCostoFCH
    public FachadaJobOperacionCosto listaCostoFCH() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        //
        FachadaJobOperacionCosto fachadaBean = new FachadaJobOperacionCosto();

        Connection connection = null;

        String selectStr =
                "  SELECT tmpCST.idLocal,                         "
                + "         tmpCST.idOperacion,                   "
                + "         SUM(tmpCST.vrCostoBaseMAT)            "
                + " 	                    AS vrCostoBaseMAT,    "
                + "         SUM(tmpCST.vrCostoBaseMOD)            "
                + " 		            AS vrCostoBaseMOD,    "
                + "         SUM(tmpCST.vrCostoBaseCIF) AS         "
                + " 		                vrCostoBaseCIF    "
                + "  FROM                                         "
                + "  ( SELECT  tblJobOperacionCosto.idLocal ,     "
                + "          tblJobOperacionCosto.idOperacion,    "
                + "          tblJobOperacionCosto.idCosto,        "
                + "      CASE tblJobOperacionCosto.idCosto        "
                + "         WHEN 1 THEN                           "
                + "       ( tblJobOperacionCosto.cantidadBase *   "
                + "          tblJobOperacionCosto.vrCostoBase )   "
                + "             ELSE 0                            "
                + "       END AS vrCostoBaseMAT,                  "
                + "      CASE tblJobOperacionCosto.idCosto        "
                + "         WHEN 2 THEN                           "
                + "       ( tblJobOperacionCosto.cantidadBase *   "
                + "          tblJobOperacionCosto.vrCostoBase )   "
                + "             ELSE 0                            "
                + "       END AS vrCostoBaseMOD,                  "
                + "      CASE tblJobOperacionCosto.idCosto        "
                + "         WHEN 3 THEN                           "
                + "       ( tblJobOperacionCosto.cantidadBase *   "
                + "          tblJobOperacionCosto.vrCostoBase )   "
                + "             ELSE 0                            "
                + "       END AS vrCostoBaseCIF                   "
                + "  FROM    tblJobOperacionCosto                 "
                + "  WHERE  tblJobOperacionCosto.idLocal     =    "
                + getIdLocal() + "                                "
                + "  AND    tblJobOperacionCosto.idOperacion =    "
                + getIdOperacion() + " )  AS tmpCST               "
                + "  GROUP BY tmpCST.idLocal,                     "
                + "           tmpCST.idOperacion                  ";

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
                fachadaBean.setIdOperacion(rs.getInt("idOperacion"));
                fachadaBean.setVrCostoBaseMAT(rs.getDouble("vrCostoBaseMAT"));
                fachadaBean.setVrCostoBaseMOD(rs.getDouble("vrCostoBaseMOD"));
                fachadaBean.setVrCostoBaseCIF(rs.getDouble("vrCostoBaseCIF"));

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
