package com.solucionesweb.losbeans.negocio;

// Importa la clase helper JDBCAccess que contiene los accesos a la base de datos
import com.solucionesweb.lasayudas.JDBCAccess;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaDctoAuditoria;

// Importa los paquetes de java
import java.sql.*;
import java.io.*;
import java.util.Vector;
import java.io.IOException.*;

// Importa los paquetes de javax
import javax.naming.*;

// Importa la interface de las constantes
import com.solucionesweb.losbeans.utilidades.IConstantes;


public class DctoAuditoriaBean extends FachadaDctoAuditoria implements Serializable,
        IConstantes {

    // Nombre JNDI del Data Source que esta clase requiere
    private static final String DATA_SOURCE_NAME = "CommerceAccess";
    // Clase helper usada para acceder a la base de datos
    private JDBCAccess jdbcAccess;

    // Metodo constructor por defecto sin parametros
    public DctoAuditoriaBean() {

        // Al instanciar el Bean establece la conexion a la base de datos
        jdbcAccess = new JDBCAccess(DATA_SOURCE_NAME);
    }

     // Metodo maximaDctoTipoOrdenIdLocal
    public int maximaDctoAutitoriaTipoOrdenIdLocal() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        int maximaIdDcto = 0;
        Connection connection = null;

        String insertStr =
             " SELECT MAX(tblDctosAuditoria.idDcto)    " +
             " AS maxIdDcto                            " +
             " FROM tblDctosAuditoria                  " +
             " WHERE tblDctosAuditoria.idLocal = (?)   " +
             " AND tblDctosAuditoria.idTipoOrden = (?) ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(insertStr);

            // inicializa los valore de los parametros
            selectStatement.setInt(1, getIdLocal());
            selectStatement.setInt(2, getIdTipoOrden());

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            if (rs.next()) {
                maximaIdDcto = rs.getInt("maxIdDcto");
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
            return maximaIdDcto;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {

            // Cierra la conexion a la base de datos limpiando la statement usada y la
            jdbcAccess.cleanup(connection, selectStatement, null);
            return maximaIdDcto;
        }
    }

     // Metodo maximaOrdenIdLocal
    public int maximaOrdenIdLocalAuditoria() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        int maximaIdOrden = 0;


        Connection connection = null;

        String insertStr =
               " SELECT MAX(tblDctosAuditoria.idOrden)    " +
               "        	AS maxIdOrden             " +
               " FROM tblDctosAuditoria                   " +
               " WHERE tblDctosAuditoria.idLocal = (?)    ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(insertStr);

            // inicializa los valore de los parametros
            selectStatement.setInt(1, getIdLocal());

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();


            if (rs.next()) {

                maximaIdOrden = rs.getInt("maxIdOrden");
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
            return maximaIdOrden;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {

            // Cierra la conexion a la base de datos limpiando la statement usada y la
            jdbcAccess.cleanup(connection, selectStatement, null);
            return maximaIdOrden;
        }
    }

    // Metodo maximoDctoLocalAlcance
    public int maximoDctoAuditoriaLocalAlcance(int xIdAlcance) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        int maxIdDcto = 0;

        Connection connection = null;

        String insertStr =

        " SELECT      MAX(tblDctosAuditoria.idDcto)    " +
        " 		   AS maxIdDcto                " +
        " FROM	      tblDctosAuditoria                " +
        " INNER JOIN  tblTipoOrden                     " +
        " ON	      tblDctosAuditoria.IDTIPOORDEN =  " +
        "                   tblTipoOrden.idTipoOrden   " +
        " WHERE       tblDctosAuditoria.idTipoOrden =  " +
            getIdLocal()  + "                          " +
        " AND               tblTipoOrden.idAlcance =   " +
            xIdAlcance;

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(insertStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            //
            if (rs.next()) {

                maxIdDcto = rs.getInt("maxIdDcto");

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
            return maxIdDcto;
        }
    }

     // ingresaDcto
    public boolean ingresaDctoAuditoria() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr =

        " INSERT INTO tblDctosAuditoria                      " +
        "            (tblDctosAuditoria.IDLOCAL              " +
        "            ,tblDctosAuditoria.IDTIPOORDEN          " +
        "            ,tblDctosAuditoria.IDORDEN              " +
        "            ,tblDctosAuditoria.idDcto               " +
        "            ,tblDctosAuditoria.idAuditoria          " +
        "            ,tblDctosAuditoria.idUsuarioResponsable " +
        "            ,tblDctosAuditoria.fechaAuditoria       " +
        "            ,tblDctosAuditoria.ipTx                 " +
        "            ,tblDctosAuditoria.estado               " +
        "            )         " +
        "      VALUES                                        " +
        "            (                                       " +
                    getIdLocal() +              ",           " +
                    getIdTipoOrden() +          ",           " +
                    getIdOrden() +              ",           " +
                    getIdDcto() +               ",           " +
                    getIdAuditoria() +          ",           " +
                    getIdUsuarioResponsable() + ",          '" +
                    getFechaAuditoria() + "',               '" +
                    getIpTx() + "',                          " +
                    getEstado() +               
                    ")" ;
      
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

      // Metodo listaUnDcto
    public FachadaDctoAuditoria listaUnDctoAuditoria() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        // Variable de fachada de los datos
        FachadaDctoAuditoria fachadaDctoAuditoria = new FachadaDctoAuditoria();

        Connection connection = null;

        String selectStr =

                " SELECT tblDctosAuditoria.IDLOCAL               " +
                "       ,tblDctosAuditoria.IDTIPOORDEN           " +
                "       ,tblDctosAuditoria.IDORDEN               " +
                "       ,tblDctosAuditoria.idDcto                " +
                "       ,tblDctosAuditoria.idAuditoria           " +
                "       ,tblDctosAuditoria.idUsuarioResponsable  " +
                "       ,tblDctosAuditoria.fechaAuditoria        " +
                "       ,tblDctosAuditoria.ipTx                  " +
                "       ,tblDctosAuditoria.estado                " +
                " FROM   tblDctosAuditoria                       " +
                " WHERE  tblDctosAuditoria.idLocal =             " +
                         getIdLocal() +  "                       " +
                "   AND  tblDctosAuditoria.idTipoOrden =         " +
                         getIdTipoOrden() + "                    " +
                "   AND  tblDctosAuditoria.idDcto =              " +
                         getIdDcto();

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
                fachadaDctoAuditoria.setIdLocal(rs.getInt("IDLOCAL"));
                fachadaDctoAuditoria.setIdTipoOrden(rs.getInt("IDTIPOORDEN"));
                fachadaDctoAuditoria.setIdOrden(rs.getInt("IDORDEN"));
                fachadaDctoAuditoria.setIdDcto(rs.getInt("idDcto"));
                fachadaDctoAuditoria.setIdAuditoria(rs.getInt("idAuditoria"));
                fachadaDctoAuditoria.setIdUsuarioResponsable(rs.getInt
                                     ("idUsuarioResponsable"));
                fachadaDctoAuditoria.setFechaAuditoria(rs.getString
                                     ("fechaAuditoria"));
                fachadaDctoAuditoria.setIpTx(rs.getString("ipTx"));
                fachadaDctoAuditoria.setEstado(rs.getInt("estado"));
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
            
            return fachadaDctoAuditoria;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {

            // Cierra la conexion a la base de datos limpiando la statement usada y la
            jdbcAccess.cleanup(connection, selectStatement, null);

            return fachadaDctoAuditoria;
        }
    }

      // ingresaPagoContado
    public boolean ingresaAuditoriaDcto() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okActualizarDctoOrdenFactura = false;

        Connection connection = null;

        //
        String selectStr =
                " UPDATE tblDctosAuditoria                         " +
                "    SET tblDctosAuditoria.idAuditoria =           " +
                     getIdAuditoria() +
                "       ,tblDctosAuditoria.idUsuarioResponsable =  " +
                     getIdUsuarioResponsable() +
                "       ,tblDctosAuditoria.fechaAuditoria =        " +
                     getFechaAuditoria() +
                "       ,tblDctosAuditoria.ipTx =                  " +
                     getIpTx() +
                "       ,tblDctosAuditoria.estado =                " +
                     getEstado() +
                "  WHERE tblDctosAuditoria.idLocal =               " +
                     getIdLocal() +
                " AND tblDctosAuditoria.idTipoOrden =              " +
                     getIdTipoOrden() +
                " AND	tblDctosAuditoria.idOrden =                " +
                     getIdOrden() +
                " AND tblDctosAuditoria.idDcto =                   " +
                     getIdDcto()    ;

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            selectStatement.execute();
            okActualizarDctoOrdenFactura = true;

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

            // Cierra el Resultset
            jdbcAccess.cleanup(connection, selectStatement, null);
            return okActualizarDctoOrdenFactura;
        }
    }
}
