// Incluye el bean dentro del paquete de utilidades
package com.solucionesweb.losbeans.utilidades;

// Importa la clase helper JDBCAccess que contiene los accesos a la base de datos
import com.solucionesweb.lasayudas.JDBCAccess;

import java.sql.*;
import java.io.*;
import java.io.IOException.*;

// Importa los paquetes de javax
import javax.naming.*;

public class PermisoUsuarioBean implements Serializable {
    // Nombre JNDI del Data Source que esta clase requiere

    private static final String DATA_SOURCE_NAME = "CommerceAccess";

    // Propiedades del Bean de Permisos Usuario
    private boolean tieneAcceso;
    private double idUsuario;
    private String nombrePaginaSolicitada;

    // Clase helper usada para acceder a la base de datos
    private JDBCAccess jdbcAccess;

    /**
     * Recupera el valor de tieneAcceso
     */
    public boolean getTieneAcceso() {
        return tieneAcceso;
    }

    /**
     * Inicializa el valor de tieneAcceso
     */
    public void setTieneAcceso(boolean tieneAcceso) {
        this.tieneAcceso = tieneAcceso;
    }

    /**
     * Recupera el valor de idUsuario
     */
    public double getIdUsuario() {
        return idUsuario;
    }

    /**
     * Inicializa el valor de idUsuario
     */
    public void setIdUsuario(double idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * Recupera el nombre de la pagina solicitada
     */
    public String getNombrePaginaSolicitada() {
        return nombrePaginaSolicitada;
    }

    /**
     * Inicializa el nombre de la pagina solicitada
     */
    public void setNombrePaginaSolicitada(String nombrePaginaSolicitada) {
        this.nombrePaginaSolicitada = nombrePaginaSolicitada;
    }

    /** Metodo constructor con parametros el idUsuario y el nombre de la pagina
     *  @param idUsuario Nombre del Usuario a Validar
     *  @param nombrePagina Nombre de la pagina a validar
     */

    // Metodo constructor por defecto sin parametros
    public PermisoUsuarioBean() {
    }

    public PermisoUsuarioBean(double idUsuario, String nombrePaginaSolicitada) {
        setIdUsuario(idUsuario);
        setNombrePaginaSolicitada(nombrePaginaSolicitada);
        setTieneAcceso(false);

        // Al instanciar el Bean establece la conexion a la base de datos
        jdbcAccess = new JDBCAccess(DATA_SOURCE_NAME);

    }

    public void verificarPermiso() {

        // System.out.println("Validara el permiso en la base de datos");
        Connection connection = null;

        /*
        String selectCuentaPaginaStr =
        "SELECT NombrePagina " +
        "FROM   tblUsuarios," +
        "       tblUsuarioAplicacionRol, " +
        "       tblAplicacionRol, " +
        "       tblAplicacionRolEvento " +
        "WHERE  tblUsuarios.idUsuario = tblUsuarioAplicacionRol.idUsuario " +
        "AND    tblUsuarioAplicacionRol.idAplicacion = tblAplicacionRol.idAplicacion " +
        "AND    tblUsuarioAplicacionRol.idRol = tblAplicacionRol.idRol " +
        "AND    tblAplicacionRol.idAplicacion = tblAplicacionRolEvento.idAplicacion " +
        "AND    tblAplicacionRol.idRol = tblAplicacionRolEvento.idRol " +
        "AND    tblUsuarios.idUsuario = ? " +
        "AND    tblAplicacionRolEvento.NombrePagina = ? ";
         */

        String selectCuentaPaginaStr =
                "SELECT ctrlUsuarios.idUsuario         " +
                "FROM   ctrlUsuarios                   " +
                "WHERE  ctrlUsuarios.idUsuario = ( ? ) ";

        // System.out.println("selectCuentaPaginaStr: " + selectCuentaPaginaStr);
        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectCuentaPaginaStr);

            // inicializa los valores de los parametros
            selectStatement.setDouble(1, idUsuario);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Si el rs tiene next es que tiene al menos un dato en la seleccion
            // con el idUsuario y nombrePaginaSolicitada suministrados por el servlet

            // System.out.println("valor de idUsuario " + idUsuario);
            // System.out.println("valor de nombrePaginaSolicitada " + nombrePaginaSolicitada);

            if (rs.next()) {
                // Si obtuvo al menos un dato carga la propiedad tieneAcceso con verdadero
                // System.out.println("Permiso Autorizado");
                setTieneAcceso(true);
            } else {
                // Como no encontro la pagina niega el permiso
                System.out.println("Permiso Denegado");
                setTieneAcceso(false);
            }

            // Cierra el Resultset
            rs.close();
            jdbcAccess.cleanup(connection, selectStatement, null);
        } // Area de obtencion de Excepciones
        catch (NamingException ne) {
            System.out.println("NamingException Validando Permiso del Usuario :" + ne);
        } catch (SQLException sqle) {
            System.out.println("SQLException in Validando Permiso del Usuario :" + sqle);
        } catch (Exception e) {
            System.out.println("Exception In Validando Permiso del Usuario :" + e);
        } finally {
            // Cierra la conexion a la base de datos limpiando la statement usada y la
            // conexion
        }
    }
}