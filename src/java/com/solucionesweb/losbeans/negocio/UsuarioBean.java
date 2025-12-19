package com.solucionesweb.losbeans.negocio;

// Importa la clase helper JDBCAccess que contiene los accesos a la base de datos
import com.solucionesweb.lasayudas.JDBCAccess;


import java.sql.*;
import java.io.IOException.*;
import java.io.*;
import java.util.*;

// Importa los paquetes de javax
import javax.naming.*;

// Importa el bean de utilidades
import com.solucionesweb.losbeans.utilidades.IConstantes;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaUsuarioBean;

//
import org.kxml.*;
import org.kxml.parser.*;

// Bean para manejo del usuario del Sistema
public class UsuarioBean extends FachadaUsuarioBean
        implements Serializable, IConstantes {

    // Nombre JNDI del Data Source que esta clase requiere
    private static final String DATA_SOURCE_NAME = "CommerceAccess";
    // Propiedades del Usuario
    private double idUsuario;
    private String clave;
    //
    private boolean vigente;
    // Clase helper usada para acceder a la base de datos
    private JDBCAccess jdbcAccess;

    // Metodo constructor por defecto sin parametros
    public UsuarioBean() {
        // Al instanciar el Bean establece la conexion a la base de datos
        jdbcAccess = new JDBCAccess(DATA_SOURCE_NAME);
    }

    // Metodo constructor con parametros idUsuario y password
    public UsuarioBean(double idUsuario, String clave) {
        this.idUsuario = idUsuario;
        this.clave = clave;
        this.vigente = false;

        // Al instanciar el Bean establece la conexion a la base de datos
        jdbcAccess = new JDBCAccess(DATA_SOURCE_NAME);
    }

    // Metodo
    public void validaUsuario() // throws SQLException,javax.naming.NamingException
    {

        Connection connection = null;

        // Inicia NO vigente
        setVigente(false);

        String selectUsuarioStr =
                "SELECT ctrlUsuarios.idUsuario,        "
                + "       ctrlUsuarios.clave,            "
                + "       ctrlUsuarios.idNivel,          "
                + "       ctrlUsuarios.direccion,        "
                + "       ctrlUsuarios.telefono,         "
                + "       ctrlUsuarios.fechaCambioClave, "
                + "       ctrlUsuarios.estado            "
                + "FROM   ctrlUsuarios                   "
                + "WHERE  ctrlUsuarios.idUsuario = ( ? ) "
                + "AND    ctrlUsuarios.clave     = ( ? ) ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectUsuarioStr);

            // inicializa los valores de los parametros
            selectStatement.setDouble(1, getIdUsuario());
            selectStatement.setString(2, getClave());

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Si el rs tiene next es que tiene un usuario en la base de datos
            if (rs.next()) {

                // Actualiza A Vigente
                setVigente(true);

            }

            rs.close();

        } // Area de obtencion de Excepciones
        catch (NamingException ne) {
            System.out.println("NamingException in JDBCAccess :" + ne);
        } catch (SQLException sqle) {
            System.out.println("SQLException in JDBCAccess:" + sqle);
        } catch (Exception e) {
            System.out.println("Exception In JDBCAccess:" + e);
        } finally {

            // conexion
            jdbcAccess.cleanup(connection, selectStatement, null);
        }
    }

    // seleccionaUsuario
    public Vector seleccionaUsuario() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        Vector contenedor = new Vector();
        Connection connection = null;

        String selectStr =
                "SELECT ctrlUsuarios.idUsuario           "
                + "      ,ctrlUsuarios.nombreUsuario       "
                + "      ,ctrlUsuarios.clave               "
                + "      ,ctrlUsuarios.idNivel             "
                + "      ,ctrlUsuarios.direccion           "
                + "      ,ctrlUsuarios.telefono            "
                + "      ,ctrlUsuarios.fechaCambioClave    "
                + "      ,ctrlUsuarios.estado              "
                + "      ,ctrlUsuarios.email               "
                + "      ,ctrlUsuarios.idLocal             "
                + "      ,ctrlUsuarios.aliasUsuario        "
                + "      ,ctrlUsuarios.idSeq               "
                + "FROM ctrlUsuarios                       "
                + " WHERE ctrlUsuarios.nombreUsuario LIKE ("
                + getNombreUsuario() + ")                  "
                + " ORDER BY ctrlUsuarios.nombreUsuario    ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaUsuarioBean fachadaBean;

            while (rs.next()) {

                //
                fachadaBean = new FachadaUsuarioBean();

                //
                fachadaBean.setIdUsuario(rs.getString("idUsuario"));
                fachadaBean.setNombreUsuario(rs.getString("nombreUsuario"));
                fachadaBean.setClave(rs.getString("clave"));
                fachadaBean.setIdNivel(rs.getInt("idNivel"));
                fachadaBean.setDireccion(rs.getString("direccion"));
                fachadaBean.setTelefono(rs.getString("telefono"));
                fachadaBean.setFechaCambioClave(
                        rs.getString("fechaCambioClave"));
                fachadaBean.setEstado(rs.getInt("estado"));
                fachadaBean.setEmail(rs.getString("email"));
                fachadaBean.setIdLocalUsuario(rs.getInt("idLocal"));
                fachadaBean.setAliasUsuario(rs.getString("aliasUsuario"));
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

    // listaUsuarioOperacion
    public Vector listaUsuarioOperacion(int xIdOperacion) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        Vector contenedor = new Vector();
        Connection connection = null;

        String selectStr =
                " SELECT ctrlUsuarios.idUsuario                     "
                + "       ,ctrlUsuarios.nombreUsuario               "
                + " FROM ctrlUsuarios                               "
                + " WHERE EXISTS                                    "
                + "  (SELECT tblJobOperacionOperario.*              "
                + "   FROM tblJobOperacionOperario                  "
                + "   WHERE tblJobOperacionOperario.idLocal   =     "
                + getIdLocalUsuario() + "                           "
                + "   AND tblJobOperacionOperario.idOperacion =     "
                + xIdOperacion + "                                  "
                + "   AND tblJobOperacionOperario.idOperario  =     "
                + "                      ctrlUsuarios.idUsuario )   "
                + " ORDER BY ctrlUsuarios.nombreUsuario             ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaUsuarioBean fachadaBean;

            while (rs.next()) {

                //
                fachadaBean = new FachadaUsuarioBean();

                //
                fachadaBean.setIdUsuario(rs.getString("idUsuario"));
                fachadaBean.setNombreUsuario(rs.getString("nombreUsuario"));

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

    // listaUsuario
    public FachadaUsuarioBean listaUsuario() {

        Connection connection = null;

        // Inicia NO vigente
        FachadaUsuarioBean fachadaUsuarioBean = new FachadaUsuarioBean();

        String selectUsuarioStr =
                "SELECT ctrlUsuarios.idUsuario,         "
                + "       ctrlUsuarios.clave,             "
                + "       ctrlUsuarios.nombreUsuario,     "
                + "       ctrlUsuarios.idNivel,           "
                + "       ctrlUsuarios.estado,            "
                + "       ctrlUsuarios.email,             "
                + "       ctrlUsuarios.idLocal,           "
                + "       ctrlUsuarios.aliasUsuario       "
                + "FROM   ctrlUsuarios                    "
                + "WHERE  ctrlUsuarios.idUsuario =        "
                + getIdUsuario();

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectUsuarioStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Si el rs tiene next es que tiene un usuario en la base de datos
            if (rs.next()) {

                //
                fachadaUsuarioBean.setIdUsuario(rs.getString("idUsuario"));
                fachadaUsuarioBean.setClave(rs.getString("clave"));
                fachadaUsuarioBean.setNombreUsuario(
                        rs.getString("nombreUsuario"));
                fachadaUsuarioBean.setIdNivel(rs.getInt("idNivel"));
                fachadaUsuarioBean.setEstado(rs.getInt("estado"));
                fachadaUsuarioBean.setEmail(rs.getString("email").trim());
                fachadaUsuarioBean.setIdLocalUsuario(rs.getInt("idLocal"));
                fachadaUsuarioBean.setAliasUsuario(
                        rs.getString("aliasUsuario"));

            }

            rs.close();

        } // Area de obtencion de Excepciones
        catch (NamingException ne) {
            System.out.println("NamingException in JDBCAccess :" + ne);
        } catch (SQLException sqle) {
            System.out.println("SQLException in JDBCAccess:" + sqle);
        } catch (Exception e) {
            System.out.println("Exception In JDBCAccess:" + e);
        } finally {
            // Cierra la conexion a la base de datos
            jdbcAccess.cleanup(connection, selectStatement, null);
            return fachadaUsuarioBean;

        }
    }

    // listaAutorizador
    public FachadaUsuarioBean listaAutorizador() {

        Connection connection = null;

        // Inicia NO vigente
        FachadaUsuarioBean fachadaUsuarioBean = new FachadaUsuarioBean();

        String selectUsuarioStr =
                "SELECT ctrlUsuarios.idUsuario,    "
                + "       ctrlUsuarios.clave,        "
                + "       ctrlUsuarios.nombreUsuario,"
                + "       ctrlUsuarios.idNivel,      "
                + "       ctrlUsuarios.estado,       "
                + "       ctrlUsuarios.email,        "
                + "       ctrlUsuarios.idLocal,      "
                + "       ctrlUsuarios.aliasUsuario  "
                + "FROM   ctrlUsuarios               "
                + "WHERE  ctrlUsuarios.idLocal =     "
                + getIdLocalUsuario() + "            "
                + "AND  ctrlUsuarios.estado    =     "
                + getEstado() + "                    "
                + "AND  ctrlUsuarios.idNivel IN     ("
                + getIdNivelCadena() + ")            "
                + "AND ctrlUsuarios.clave         = '"
                + getClave() + "'";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectUsuarioStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Si el rs tiene next es que tiene un usuario en la base de datos
            if (rs.next()) {

                //
                fachadaUsuarioBean.setIdUsuario(rs.getString("idUsuario"));
                fachadaUsuarioBean.setClave(rs.getString("clave"));
                fachadaUsuarioBean.setNombreUsuario(
                        rs.getString("nombreUsuario"));
                fachadaUsuarioBean.setIdNivel(rs.getInt("idNivel"));
                fachadaUsuarioBean.setEstado(rs.getInt("estado"));
                fachadaUsuarioBean.setEmail(rs.getString("email").trim());
                fachadaUsuarioBean.setIdLocalUsuario(rs.getInt("idLocal"));
                fachadaUsuarioBean.setAliasUsuario(
                        rs.getString("aliasUsuario"));

            }

            rs.close();

        } // Area de obtencion de Excepciones
        catch (NamingException ne) {
            System.out.println("NamingException in JDBCAccess :" + ne);
        } catch (SQLException sqle) {
            System.out.println("SQLException in JDBCAccess:" + sqle);
        } catch (Exception e) {
            System.out.println("Exception In JDBCAccess:" + e);
        } finally {
            // Cierra la conexion a la base de datos
            jdbcAccess.cleanup(connection, selectStatement, null);
            return fachadaUsuarioBean;

        }
    }

    // listaFCH
    public FachadaUsuarioBean listaFCH() {

        Connection connection = null;

        // Inicia NO vigente
        FachadaUsuarioBean fachadaUsuarioBean = new FachadaUsuarioBean();

        String selectUsuarioStr =
                "SELECT ctrlUsuarios.idUsuario           "
                + "      ,ctrlUsuarios.nombreUsuario       "
                + "      ,ctrlUsuarios.clave               "
                + "      ,ctrlUsuarios.idNivel             "
                + "      ,ctrlUsuarios.direccion           "
                + "      ,ctrlUsuarios.telefono            "
                + "      ,ctrlUsuarios.fechaCambioClave    "
                + "      ,ctrlUsuarios.estado              "
                + "      ,ctrlUsuarios.email               "
                + "      ,ctrlUsuarios.idLocal             "
                + "      ,ctrlUsuarios.aliasUsuario        "
                + "      ,ctrlUsuarios.idSeq               "
                + "FROM   ctrlUsuarios                     "
                + "WHERE  ctrlUsuarios.idUsuario =         "
                + getIdUsuario();

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectUsuarioStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Si el rs tiene next es que tiene un usuario en la base de datos
            if (rs.next()) {

                //
                //
                fachadaUsuarioBean.setIdUsuario(rs.getString("idUsuario"));
                fachadaUsuarioBean.setNombreUsuario(
                        rs.getString("nombreUsuario"));
                fachadaUsuarioBean.setClave(rs.getString("clave"));
                fachadaUsuarioBean.setIdNivel(rs.getInt("idNivel"));
                fachadaUsuarioBean.setDireccion(rs.getString("direccion"));
                fachadaUsuarioBean.setTelefono(rs.getString("telefono"));
                fachadaUsuarioBean.setFechaCambioClave(
                        rs.getString("fechaCambioClave"));
                fachadaUsuarioBean.setEstado(rs.getInt("estado"));
                fachadaUsuarioBean.setEmail(rs.getString("email"));
                fachadaUsuarioBean.setIdLocalUsuario(rs.getInt("idLocal"));
                fachadaUsuarioBean.setAliasUsuario(
                        rs.getString("aliasUsuario"));
                fachadaUsuarioBean.setIdSeq(rs.getInt("idSeq"));

            }

            rs.close();

        } // Area de obtencion de Excepciones
        catch (NamingException ne) {
            System.out.println("NamingException in JDBCAccess :" + ne);
        } catch (SQLException sqle) {
            System.out.println("SQLException in JDBCAccess:" + sqle);
        } catch (Exception e) {
            System.out.println("Exception In JDBCAccess:" + e);
        } finally {
            // Cierra la conexion a la base de datos
            jdbcAccess.cleanup(connection, selectStatement, null);
            return fachadaUsuarioBean;
        }
    }

    // listaUsuarioTodos
    public Vector listaUsuarioTodos() {

        Connection connection = null;

        Vector contenedor = new Vector();

        String selectUsuarioStr =
                "SELECT ctrlUsuarios.idUsuario,      "
                + "       ctrlUsuarios.nombreUsuario   "
                + "FROM   ctrlUsuarios                 "
                + "ORDER BY ctrlUsuarios.nombreUsuario ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectUsuarioStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            //
            FachadaUsuarioBean fachadaUsuarioBean;

            while (rs.next()) {

                fachadaUsuarioBean = new FachadaUsuarioBean();

                fachadaUsuarioBean.setIdUsuario(rs.getDouble("idUsuario"));
                fachadaUsuarioBean.setNombreUsuario(
                        rs.getString("nombreUsuario"));

                //
                contenedor.add(fachadaUsuarioBean);

            }

            rs.close();

        } // Area de obtencion de Excepciones
        catch (NamingException ne) {
            System.out.println("NamingException in JDBCAccess :" + ne);
        } catch (SQLException sqle) {
            System.out.println("SQLException in JDBCAccess:" + sqle);
        } catch (Exception e) {
            System.out.println("Exception In JDBCAccess:" + e);
        } finally {
            // Cierra la conexion a la base de datos
            jdbcAccess.cleanup(connection, selectStatement, null);
            return contenedor;

        }
    }

    // listaUnUsuario
    public Vector listaUnUsuario() {

        Connection connection = null;

        Vector contenedor = new Vector();

        String selectUsuarioStr =
                "SELECT ctrlUsuarios.idUsuario,         "
                + "       ctrlUsuarios.nombreUsuario      "
                + "FROM   ctrlUsuarios                    "
                + "WHERE  ctrlUsuarios.idUsuario =  ( ? ) ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectUsuarioStr);

            // inicializa los valores de los parametros
            selectStatement.setDouble(1, getIdUsuario());

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            //
            FachadaUsuarioBean fachadaUsuarioBean;

            while (rs.next()) {

                fachadaUsuarioBean = new FachadaUsuarioBean();

                fachadaUsuarioBean.setIdUsuario(rs.getDouble("idUsuario"));
                fachadaUsuarioBean.setNombreUsuario(
                        rs.getString("nombreUsuario"));

                //
                contenedor.add(fachadaUsuarioBean);

            }

            rs.close();

        } // Area de obtencion de Excepciones
        catch (NamingException ne) {
            System.out.println("NamingException in JDBCAccess :" + ne);
        } catch (SQLException sqle) {
            System.out.println("SQLException in JDBCAccess:" + sqle);
        } catch (Exception e) {
            System.out.println("Exception In JDBCAccess:" + e);
        } finally {
            // Cierra la conexion a la base de datos
            jdbcAccess.cleanup(connection, selectStatement, null);
            return contenedor;

        }
    }

    // Metodo
    public void vigenciaUsuario() {

        Connection connection = null;

        // Inicia NO vigente
        setVigente(false);
        String selectUsuarioStr =
                "SELECT ctrlUsuarios.idUsuario,        "
                + "       ctrlUsuarios.clave,            "
                + "       ctrlUsuarios.idNivel,          "
                + "       ctrlUsuarios.direccion,        "
                + "       ctrlUsuarios.telefono,         "
                + "       ctrlUsuarios.fechaCambioClave, "
                + "       ctrlUsuarios.estado            "
                + "FROM   ctrlUsuarios                   "
                + "WHERE  ctrlUsuarios.idUsuario = ( ? ) "
                + "AND    ctrlUsuarios.clave     = ( ? ) "
                + "AND    ctrlUsuarios.estado    = ( ? ) ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectUsuarioStr);

            // inicializa los valores de los parametros
            selectStatement.setDouble(1, getIdUsuario());
            selectStatement.setString(2, getClave());
            selectStatement.setInt(3, getEstado());

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Si el rs tiene next es que tiene un usuario en la base de datos
            // con el idUsuario y password suministrados por el usuario
            if (rs.next()) {

                // Actualiza A Vigente
                setVigente(true);

            }

            rs.close();

        } // Area de obtencion de Excepciones
        catch (NamingException ne) {
            System.out.println("NamingException in JDBCAccess :" + ne);
        } catch (SQLException sqle) {
            System.out.println("SQLException in JDBCAccess:" + sqle);
        } catch (Exception e) {
            System.out.println("Exception In JDBCAccess:" + e);
        } finally {
            // Cierra la conexion a la base de datos
            jdbcAccess.cleanup(connection, selectStatement, null);
            // conexion
        }
    }

    // listaUnUsuario
    public String emailRutaNivel(String xIdRuta) {

        Connection connection = null;

        String strEmail = "";

        String selectUsuarioStr =
                "SELECT ctrlUsuarios.EMAIL               "
                + "FROM   ctrlUsuariosRutas                "
                + "INNER JOIN ctrlUsuarios                 "
                + "ON ctrlUsuariosRutas.idUsuario  =       "
                + "          ctrlUsuarios.IDUSUARIO        "
                + "GROUP BY ctrlUsuarios.EMAIL,            "
                + "         ctrlUsuariosRutas.idRuta,      "
                + "         ctrlUsuarios.IDNIVEL           "
                + "HAVING ctrlUsuariosRutas.idRuta = ( ? ) "
                + "AND    ctrlUsuarios.IDNIVEL     = ( ? ) ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectUsuarioStr);

            // inicializa los valores de los parametros
            selectStatement.setString(1, xIdRuta);
            selectStatement.setInt(2, getIdNivel());

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            //
            if (rs.next()) {

                strEmail += rs.getString("EMAIL");

            }

            rs.close();

        } // Area de obtencion de Excepciones
        catch (NamingException ne) {
            System.out.println("NamingException in JDBCAccess :" + ne);
        } catch (SQLException sqle) {
            System.out.println("SQLException in JDBCAccess:" + sqle);
        } catch (Exception e) {
            System.out.println("Exception In JDBCAccess:" + e);
        } finally {
            // Cierra la conexion a la base de datos
            jdbcAccess.cleanup(connection, selectStatement, null);
            return strEmail;

        }
    }

    // listaUsuarioOpcion
    public Vector listaUsuarioOpcion() {

        Connection connection = null;

        Vector contenedor = new Vector();

        String selectUsuarioStr =
                "SELECT ctrlUsuarios.IDUSUARIO        "
                + "      ,ctrlUsuarios.NOMBREUSUARIO    "
                + "      ,ctrlUsuarios.IDNIVEL          "
                + "      ,ctrlUsuarios.DIRECCION        "
                + "      ,ctrlUsuarios.TELEFONO         "
                + "      ,ctrlUsuarios.FECHACAMBIOCLAVE "
                + "      ,ctrlUsuarios.ESTADO           "
                + "      ,ctrlUsuarios.EMAIL            "
                + "      ,ctrlUsuarios.idLocal          "
                + "      ,01 AS ordenSalida             "
                + "FROM ctrlUsuarios                    "
                + "WHERE ctrlUsuarios.IDLOCAL   =       "
                + getIdLocalUsuario() + "               "
                + "AND   ctrlUsuarios.idNivel   =       "
                + getIdNivel() + "                      "
                + "AND   ctrlUsuarios.IDUSUARIO =       "
                + getIdUsuario() + "                    "
                + "AND   ctrlUsuarios.estado    =       "
                + getEstado() + "                       "
                + "UNION                                "
                + "SELECT ctrlUsuarios.IDUSUARIO        "
                + "      ,ctrlUsuarios.NOMBREUSUARIO    "
                + "      ,ctrlUsuarios.IDNIVEL          "
                + "      ,ctrlUsuarios.DIRECCION        "
                + "      ,ctrlUsuarios.TELEFONO         "
                + "      ,ctrlUsuarios.FECHACAMBIOCLAVE "
                + "      ,ctrlUsuarios.ESTADO           "
                + "      ,ctrlUsuarios.EMAIL            "
                + "      ,ctrlUsuarios.idLocal          "
                + "      ,02 AS ordenSalida             "
                + "FROM ctrlUsuarios                    "
                + "WHERE ctrlUsuarios.IDLOCAL   =       "
                + getIdLocalUsuario() + "               "
                + "AND   ctrlUsuarios.idNivel   =       "
                + getIdNivel() + "                      "
                + "AND   ctrlUsuarios.IDUSUARIO !=      "
                + getIdUsuario() + "                    "
                + "AND   ctrlUsuarios.estado     =      "
                + getEstado() + "                       "
                + "ORDER BY 10                          ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectUsuarioStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            //
            FachadaUsuarioBean fachadaUsuarioBean;

            while (rs.next()) {

                fachadaUsuarioBean = new FachadaUsuarioBean();


                //
                fachadaUsuarioBean.setIdUsuario(rs.getString("idUsuario"));
                fachadaUsuarioBean.setNombreUsuario(
                        rs.getString("nombreUsuario"));
                fachadaUsuarioBean.setIdNivel(rs.getInt("idNivel"));
                fachadaUsuarioBean.setDireccion(rs.getString("DIRECCION"));
                fachadaUsuarioBean.setTelefono(rs.getString("TELEFONO"));
                fachadaUsuarioBean.setFechaCambioClave(
                        rs.getString("FECHACAMBIOCLAVE"));
                fachadaUsuarioBean.setEstado(rs.getInt("estado"));
                fachadaUsuarioBean.setEmail(rs.getString("email"));
                fachadaUsuarioBean.setIdLocalUsuario(rs.getInt("idLocal"));

                //
                contenedor.add(fachadaUsuarioBean);

            }

            rs.close();

        } // Area de obtencion de Excepciones
        catch (NamingException ne) {
            System.out.println("NamingException in JDBCAccess :" + ne);
        } catch (SQLException sqle) {
            System.out.println("SQLException in JDBCAccess:" + sqle);
        } catch (Exception e) {
            System.out.println("Exception In JDBCAccess:" + e);
        } finally {

            // Cierra la conexion a la base de datos
            jdbcAccess.cleanup(connection, selectStatement, null);
            return contenedor;

        }
    }

    // listaNivelOpcion
    public Vector listaNivelOpcion(String xIdNivelMultipleStr) {

        Connection connection = null;

        Vector contenedor = new Vector();

        String selectUsuarioStr =
                "SELECT TOP 1 00 AS IDUSUARIO         "
                + "      ,'TODOS' AS NOMBREUSUARIO      "
                + "      ,ctrlUsuarios.IDNIVEL          "
                + "      ,ctrlUsuarios.DIRECCION        "
                + "      ,ctrlUsuarios.TELEFONO         "
                + "      ,ctrlUsuarios.FECHACAMBIOCLAVE "
                + "      ,ctrlUsuarios.ESTADO           "
                + "      ,ctrlUsuarios.EMAIL            "
                + "      ,ctrlUsuarios.idLocal          "
                + "      ,01 AS ordenSalida             "
                + "FROM ctrlUsuarios                    "
                + "UNION                                "
                + "SELECT ctrlUsuarios.IDUSUARIO        "
                + "      ,ctrlUsuarios.NOMBREUSUARIO    "
                + "      ,ctrlUsuarios.IDNIVEL          "
                + "      ,ctrlUsuarios.DIRECCION        "
                + "      ,ctrlUsuarios.TELEFONO         "
                + "      ,ctrlUsuarios.FECHACAMBIOCLAVE "
                + "      ,ctrlUsuarios.ESTADO           "
                + "      ,ctrlUsuarios.EMAIL            "
                + "      ,ctrlUsuarios.idLocal          "
                + "      ,02 AS ordenSalida             "
                + "FROM ctrlUsuarios                    "
                + "WHERE ctrlUsuarios.IDLOCAL   =       "
                + getIdLocalUsuario() + "               "
                + "AND   ctrlUsuarios.idNivel   IN     ("
                + xIdNivelMultipleStr + ")              "
                + "AND   ctrlUsuarios.IDUSUARIO =       "
                + getIdUsuario() + "                    "
                + "AND   ctrlUsuarios.estado    =       "
                + getEstado() + "                       "
                + "UNION                                "
                + "SELECT ctrlUsuarios.IDUSUARIO        "
                + "      ,ctrlUsuarios.NOMBREUSUARIO    "
                + "      ,ctrlUsuarios.IDNIVEL          "
                + "      ,ctrlUsuarios.DIRECCION        "
                + "      ,ctrlUsuarios.TELEFONO         "
                + "      ,ctrlUsuarios.FECHACAMBIOCLAVE "
                + "      ,ctrlUsuarios.ESTADO           "
                + "      ,ctrlUsuarios.EMAIL            "
                + "      ,ctrlUsuarios.idLocal          "
                + "      ,03 AS ordenSalida             "
                + "FROM ctrlUsuarios                    "
                + "WHERE ctrlUsuarios.IDLOCAL   =       "
                + getIdLocalUsuario() + "               "
                + "AND   ctrlUsuarios.idNivel   IN     ("
                + xIdNivelMultipleStr + ")              "
                + "AND   ctrlUsuarios.IDUSUARIO !=      "
                + getIdUsuario() + "                    "
                + "AND   ctrlUsuarios.estado     =      "
                + getEstado() + "                       "
                + "ORDER BY 10 , 2                      ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectUsuarioStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            //
            FachadaUsuarioBean fachadaUsuarioBean;

            while (rs.next()) {

                fachadaUsuarioBean = new FachadaUsuarioBean();


                //
                fachadaUsuarioBean.setIdUsuario(rs.getString("idUsuario"));
                fachadaUsuarioBean.setNombreUsuario(
                        rs.getString("nombreUsuario"));
                fachadaUsuarioBean.setIdNivel(rs.getInt("idNivel"));
                fachadaUsuarioBean.setDireccion(rs.getString("DIRECCION"));
                fachadaUsuarioBean.setTelefono(rs.getString("TELEFONO"));
                fachadaUsuarioBean.setFechaCambioClave(
                        rs.getString("FECHACAMBIOCLAVE"));
                fachadaUsuarioBean.setEstado(rs.getInt("estado"));
                fachadaUsuarioBean.setEmail(rs.getString("email"));
                fachadaUsuarioBean.setIdLocalUsuario(rs.getInt("idLocal"));

                //
                contenedor.add(fachadaUsuarioBean);

            }

            rs.close();

        } // Area de obtencion de Excepciones
        catch (NamingException ne) {
            System.out.println("NamingException in JDBCAccess :" + ne);
        } catch (SQLException sqle) {
            System.out.println("SQLException in JDBCAccess:" + sqle);
        } catch (Exception e) {
            System.out.println("Exception In JDBCAccess:" + e);
        } finally {

            // Cierra la conexion a la base de datos
            jdbcAccess.cleanup(connection, selectStatement, null);
            return contenedor;

        }
    }

    // listaUsuarioPorDefecto
    public Vector listaUsuarioPorDefecto(String xIdCliente,
            String xIdNivelMostrarCombo) {

        Connection connection = null;

        Vector contenedor = new Vector();

        String selectUsuarioStr =
                "   SELECT                                                 "
                + "   ctrlUsuarios.idUsuario AS idVendedor,                  "
                + "   ctrlUsuarios.nombreUsuario AS nombreUsuario,           "
                + "   01 As consecutivo                                      "
                + "   FROM   ctrlUsuarios                                    "
                + "   WHERE  idUsuario = (SELECT TOP 1 tblDctos.idVendedor   "
                + "                       FROM     tblDctos                  "
                + "                       WHERE tblDctos.IDTIPOORDEN = 9     "
                + "                       AND tblDctos.idCliente     =      '"
                + xIdCliente + "'                                            "
                + "                       ORDER BY tblDctos.fechaDcto DESC)  "
                + "    AND idNivel IN (                                      "
                + xIdNivelMostrarCombo + ")                                 "
                + "    AND ctrlUsuarios.idLocal   =                          "
                + getIdLocalUsuario() + "                                   "
                + "    AND ctrlUsuarios.estado    = 1                        "
                + "   UNION                                                  "
                + "   SELECT ctrlUsuarios.idUsuario AS idVendedor,           "
                + "          ctrlUsuarios.nombreUsuario AS nombreUsuario,    "
                + "          02 As consecutivo                               "
                + "   FROM   ctrlUsuarios                                    "
                + "   WHERE  ctrlUsuarios.idNivel IN (                       "
                + xIdNivelMostrarCombo + ")                                 "
                + "    AND ctrlUsuarios.idLocal   =                          "
                + getIdLocalUsuario() + "                                   "
                + "    AND ctrlUsuarios.estado    = 1                        "
                + "   ORDER BY 3, 2 ASC";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectUsuarioStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            //
            FachadaUsuarioBean fachadaUsuarioBean;

            while (rs.next()) {

                fachadaUsuarioBean = new FachadaUsuarioBean();


                //
                fachadaUsuarioBean.setIdUsuario(rs.getString("idVendedor"));
                fachadaUsuarioBean.setNombreUsuario(
                        rs.getString("nombreUsuario"));

                //
                contenedor.add(fachadaUsuarioBean);

            }

            rs.close();

        } // Area de obtencion de Excepciones
        catch (NamingException ne) {
            System.out.println("NamingException in JDBCAccess :" + ne);
        } catch (SQLException sqle) {
            System.out.println("SQLException in JDBCAccess:" + sqle);
        } catch (Exception e) {
            System.out.println("Exception In JDBCAccess:" + e);
        } finally {

            // Cierra la conexion a la base de datos
            jdbcAccess.cleanup(connection, selectStatement, null);
            return contenedor;

        }
    }

    // listaNivelOpcionAll
    public Vector listaNivelOpcionAll(String xIdNivelMultipleStr) {

        Connection connection = null;

        Vector contenedor = new Vector();

        String selectUsuarioStr =
                "SELECT TOP 1 00 AS idUsuario         "
                + "      ,'TODOS' AS   NOMBREUSUARIO    "
                + "      ,0        AS  IDNIVEL          "
                + "      ,'NN' AS     DIRECCION         "
                + "      ,'NN' AS     TELEFONO          "
                + "      ,'20010101' AS FECHACAMBIOCLAVE"
                + "      ,1          AS ESTADO          "
                + "      ,'NN'       AS email,          "
                + getIdLocalUsuario() + " AS IDLOCAL    "
                + "      ,00 AS ordenSalida             "
                + "FROM ctrlUsuarios                    "
                + "UNION                                "
                + "SELECT ctrlUsuarios.IDUSUARIO        "
                + "      ,ctrlUsuarios.NOMBREUSUARIO    "
                + "      ,ctrlUsuarios.IDNIVEL          "
                + "      ,ctrlUsuarios.DIRECCION        "
                + "      ,ctrlUsuarios.TELEFONO         "
                + "      ,ctrlUsuarios.FECHACAMBIOCLAVE "
                + "      ,ctrlUsuarios.ESTADO           "
                + "      ,ctrlUsuarios.EMAIL            "
                + "      ,ctrlUsuarios.idLocal          "
                + "      ,01 AS ordenSalida             "
                + "FROM ctrlUsuarios                    "
                + "WHERE ctrlUsuarios.IDLOCAL   =       "
                + getIdLocalUsuario() + "               "
                + "AND   ctrlUsuarios.idNivel   IN     ("
                + xIdNivelMultipleStr + ")              "
                + "AND   ctrlUsuarios.IDUSUARIO =       "
                + getIdUsuario() + "                    "
                + "AND   ctrlUsuarios.estado    =       "
                + getEstado() + "                       "
                + "UNION                                "
                + "SELECT ctrlUsuarios.IDUSUARIO        "
                + "      ,ctrlUsuarios.NOMBREUSUARIO    "
                + "      ,ctrlUsuarios.IDNIVEL          "
                + "      ,ctrlUsuarios.DIRECCION        "
                + "      ,ctrlUsuarios.TELEFONO         "
                + "      ,ctrlUsuarios.FECHACAMBIOCLAVE "
                + "      ,ctrlUsuarios.ESTADO           "
                + "      ,ctrlUsuarios.EMAIL            "
                + "      ,ctrlUsuarios.idLocal          "
                + "      ,02 AS ordenSalida             "
                + "FROM ctrlUsuarios                    "
                + "WHERE ctrlUsuarios.IDLOCAL   =       "
                + getIdLocalUsuario() + "               "
                + "AND   ctrlUsuarios.idNivel   IN     ("
                + xIdNivelMultipleStr + ")              "
                + "AND   ctrlUsuarios.IDUSUARIO !=      "
                + getIdUsuario() + "                    "
                + "AND   ctrlUsuarios.estado     =      "
                + getEstado() + "                       "
                + "ORDER BY 10                          ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectUsuarioStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            //
            FachadaUsuarioBean fachadaUsuarioBean;

            while (rs.next()) {

                fachadaUsuarioBean = new FachadaUsuarioBean();


                //
                fachadaUsuarioBean.setIdUsuario(rs.getString("idUsuario"));
                fachadaUsuarioBean.setNombreUsuario(
                        rs.getString("nombreUsuario"));
                fachadaUsuarioBean.setIdNivel(rs.getInt("idNivel"));
                fachadaUsuarioBean.setDireccion(rs.getString("DIRECCION"));
                fachadaUsuarioBean.setTelefono(rs.getString("TELEFONO"));
                fachadaUsuarioBean.setFechaCambioClave(
                        rs.getString("FECHACAMBIOCLAVE"));
                fachadaUsuarioBean.setEstado(rs.getInt("estado"));
                fachadaUsuarioBean.setEmail(rs.getString("email"));
                fachadaUsuarioBean.setIdLocalUsuario(rs.getInt("idLocal"));

                //
                contenedor.add(fachadaUsuarioBean);

            }

            rs.close();

        } // Area de obtencion de Excepciones
        catch (NamingException ne) {
            System.out.println("NamingException in JDBCAccess :" + ne);
        } catch (SQLException sqle) {
            System.out.println("SQLException in JDBCAccess:" + sqle);
        } catch (Exception e) {
            System.out.println("Exception In JDBCAccess:" + e);
        } finally {

            // Cierra la conexion a la base de datos
            jdbcAccess.cleanup(connection, selectStatement, null);
            return contenedor;

        }
    }

    // listaUsuarioLocalAll
    public Vector listaUsuarioLocalAll() {

        Connection connection = null;

        Vector contenedor = new Vector();

        String selectUsuarioStr =
                "SELECT TOP 1 00 AS IDUSUARIO         "
                + "      ,'TODOS'  AS NOMBREUSUARIO     "
                + "      ,5 AS IDNIVEL                  "
                + "      ,'NN' AS DIRECCION             "
                + "      ,'NN' AS TELEFONO              "
                + "      ,'20010101' AS FECHACAMBIOCLAVE"
                + "      ,1 AS ESTADO                   "
                + "      ,'NN' AS EMAIL,                "
                + getIdLocalUsuario() + " AS idLocal    "
                + "      ,01 AS ordenSalida             "
                + "FROM ctrlUsuarios                    "
                + "UNION                                "
                + "SELECT ctrlUsuarios.IDUSUARIO        "
                + "      ,ctrlUsuarios.NOMBREUSUARIO    "
                + "      ,ctrlUsuarios.IDNIVEL          "
                + "      ,ctrlUsuarios.DIRECCION        "
                + "      ,ctrlUsuarios.TELEFONO         "
                + "      ,ctrlUsuarios.FECHACAMBIOCLAVE "
                + "      ,ctrlUsuarios.ESTADO           "
                + "      ,ctrlUsuarios.EMAIL            "
                + "      ,ctrlUsuarios.idLocal          "
                + "      ,02 AS ordenSalida             "
                + "FROM ctrlUsuarios                    "
                + "WHERE ctrlUsuarios.IDLOCAL   =       "
                + getIdLocalUsuario() + "               "
                + "AND   ctrlUsuarios.idNivel   =       "
                + getIdNivel() + "                      "
                + "AND   ctrlUsuarios.IDUSUARIO =       "
                + getIdUsuario() + "                    "
                + "AND   ctrlUsuarios.estado    =       "
                + getEstado() + "                       "
                + "UNION                                "
                + "SELECT ctrlUsuarios.IDUSUARIO        "
                + "      ,ctrlUsuarios.NOMBREUSUARIO    "
                + "      ,ctrlUsuarios.IDNIVEL          "
                + "      ,ctrlUsuarios.DIRECCION        "
                + "      ,ctrlUsuarios.TELEFONO         "
                + "      ,ctrlUsuarios.FECHACAMBIOCLAVE "
                + "      ,ctrlUsuarios.ESTADO           "
                + "      ,ctrlUsuarios.EMAIL            "
                + "      ,ctrlUsuarios.idLocal          "
                + "      ,03 AS ordenSalida             "
                + "FROM ctrlUsuarios                    "
                + "WHERE ctrlUsuarios.IDLOCAL   =       "
                + getIdLocalUsuario() + "               "
                + "AND   ctrlUsuarios.idNivel   =       "
                + getIdNivel() + "                      "
                + "AND   ctrlUsuarios.IDUSUARIO !=      "
                + getIdUsuario() + "                    "
                + "AND   ctrlUsuarios.estado     =      "
                + getEstado() + "                       "
                + "ORDER BY 10                          ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectUsuarioStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            //
            FachadaUsuarioBean fachadaUsuarioBean;

            while (rs.next()) {

                fachadaUsuarioBean = new FachadaUsuarioBean();


                //
                fachadaUsuarioBean.setIdUsuario(rs.getString("idUsuario"));
                fachadaUsuarioBean.setNombreUsuario(
                        rs.getString("nombreUsuario"));
                fachadaUsuarioBean.setIdNivel(rs.getInt("idNivel"));
                fachadaUsuarioBean.setDireccion(rs.getString("DIRECCION"));
                fachadaUsuarioBean.setTelefono(rs.getString("TELEFONO"));
                fachadaUsuarioBean.setFechaCambioClave(
                        rs.getString("FECHACAMBIOCLAVE"));
                fachadaUsuarioBean.setEstado(rs.getInt("estado"));
                fachadaUsuarioBean.setEmail(rs.getString("email"));
                fachadaUsuarioBean.setIdLocalUsuario(rs.getInt("idLocal"));

                //
                contenedor.add(fachadaUsuarioBean);

            }

            rs.close();

        } // Area de obtencion de Excepciones
        catch (NamingException ne) {
            System.out.println("NamingException in JDBCAccess :" + ne);
        } catch (SQLException sqle) {
            System.out.println("SQLException in JDBCAccess:" + sqle);
        } catch (Exception e) {
            System.out.println("Exception In JDBCAccess:" + e);
        } finally {

            // Cierra la conexion a la base de datos
            jdbcAccess.cleanup(connection, selectStatement, null);
            return contenedor;

        }
    }

    // listaIdSeqMaxima
    public int listaIdSeqMaxima() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        int intMaxIdSeq = 0;

        Connection connection = null;

        String selectStr =
                "SELECT MAX(ctrlUsuarios.idSeq) "
                + "              AS maxIdSeq      "
                + "FROM ctrlUsuarios              ";

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
                intMaxIdSeq = rs.getInt("maxIdSeq");

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

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {

            // Cierra la conexion
            jdbcAccess.cleanup(connection, selectStatement, null);
            return intMaxIdSeq;

        }
    }

    // actualizaClave
    public boolean actualizaClave() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        boolean validaOk = false;
        Connection connection = null;

        String updateStr =
                "UPDATE ctrlUsuarios                "
                + "SET ctrlUsuarios.clave         =  '"
                + getClave() + "',                    "
                + "    ctrlUsuarios.idSeq         =   "
                + getIdSeq() + ",                     "
                + " ctrlUsuarios.fechaCambioClave =  '"
                + getFechaCambioClaveSqlServer() + "' "
                + "WHERE ctrlUsuarios.idUsuario =  "
                + getIdUsuario();

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(updateStr);

            // Obtiene el resultset
            selectStatement.executeUpdate();

            //
            validaOk = true;

            // Cierra el Resultset
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
            return validaOk;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {
            // Cierra la conexion a la base de datos limpiando la statement usada y la
            // conexion
            return validaOk;
        }
    }

    // actualizaSeguridad
    public boolean actualizaSeguridad() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        boolean validaOk = false;
        Connection connection = null;

        String updateStr =
                "UPDATE ctrlUsuarios                       "
                + "SET    ctrlUsuarios.idLocal =             "
                + "      ( SELECT tblLocales.idLocal         "
                + "        FROM tblLocales                   "
                + "        WHERE tblLocales.estado = 1 )     "
                + "WHERE  ctrlUsuarios.idNivel NOT IN (1, 2) ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(updateStr);

            // Obtiene el resultset
            selectStatement.executeUpdate();

            //
            validaOk = true;

            // Cierra el Resultset
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
            return validaOk;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {
            // Cierra la conexion a la base de datos limpiando la statement usada y la
            // conexion
            return validaOk;
        }
    }

    // actualiza
    public boolean actualiza() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        boolean validaOk = false;
        Connection connection = null;

        //
        String updateStr =
                "UPDATE ctrlUsuarios                "
                + "SET ctrlUsuarios.nombreUsuario =  '"
                + getNombreUsuario() + "',            "
                + "    ctrlUsuarios.clave         =  '"
                + getClave() + "',                    "
                + "    ctrlUsuarios.idNivel       =   "
                + getIdNivel() + ",                   "
                + "    ctrlUsuarios.direccion     =  '"
                + getDireccion() + "',                "
                + "    ctrlUsuarios.telefono      =  '"
                + getTelefono() + "',                 "
                + "    ctrlUsuarios.estado        =   "
                + getEstado() + ",                    "
                + "    ctrlUsuarios.email         =  '"
                + getEmail() + "',                    "
                + "    ctrlUsuarios.idLocal       =   "
                + getIdLocalUsuario() + ",            "
                + "    ctrlUsuarios.aliasUsuario  =  '"
                + getAliasUsuario() + "',             "
                + "    ctrlUsuarios.idSeq         =   "
                + getIdSeq() + "                      "
                + "WHERE ctrlUsuarios.idUsuario   =   "
                + getIdUsuario();

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(updateStr);

            // Obtiene el resultset
            selectStatement.executeUpdate();

            //
            validaOk = true;

            // Cierra el Resultset
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
            return validaOk;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {
            // Cierra la conexion a la base de datos limpiando la statement usada y la
            // conexion
            return validaOk;
        }
    }

    // actualizaDato
    public boolean actualizaDato() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        boolean validaOk = false;
        Connection connection = null;

        //
        String updateStr =
                "UPDATE ctrlUsuarios                "
                + "SET ctrlUsuarios.nombreUsuario =  '"
                + getNombreUsuario() + "',            "
                + "    ctrlUsuarios.idNivel       =   "
                + getIdNivel() + ",                   "
                + "    ctrlUsuarios.direccion     =  '"
                + getDireccion() + "',                "
                + "    ctrlUsuarios.telefono      =  '"
                + getTelefono() + "',                 "
                + "    ctrlUsuarios.estado        =   "
                + getEstado() + ",                    "
                + "    ctrlUsuarios.email         =  '"
                + getEmail() + "',                    "
                + "    ctrlUsuarios.idLocal       =   "
                + getIdLocalUsuario() + ",            "
                + "    ctrlUsuarios.aliasUsuario  =  '"
                + getAliasUsuario() + "',             "
                + "    ctrlUsuarios.idSeq         =   "
                + getIdSeq() + "                      "
                + "WHERE ctrlUsuarios.idUsuario   =   "
                + getIdUsuario();

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(updateStr);

            // Obtiene el resultset
            selectStatement.executeUpdate();

            //
            validaOk = true;

            // Cierra el Resultset
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
            return validaOk;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {
            // Cierra la conexion a la base de datos limpiando la statement usada y la
            // conexion
            return validaOk;
        }
    }

    // ingresa
    public boolean ingresa() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        boolean validaOk = false;
        Connection connection = null;

        //
        String updateStr =
                "INSERT INTO ctrlUsuarios     "
                + "           (idUsuario        "
                + "           ,nombreUsuario    "
                + "           ,clave            "
                + "           ,idNivel          "
                + "           ,direccion        "
                + "           ,telefono         "
                + "           ,fechaCambioClave "
                + "           ,estado           "
                + "           ,email            "
                + "           ,idLocal          "
                + "           ,aliasUsuario     "
                + "           ,idSeq)           "
                + "VALUES                     ( "
                + getIdUsuario() + ",'"
                + getNombreUsuario() + "','"
                + getClave() + "',"
                + getIdNivel() + ",'"
                + getDireccion() + "','"
                + getTelefono() + "','"
                + getFechaCambioClaveSqlServer() + "',"
                + getEstado() + ",'"
                + getEmail() + "',"
                + getIdLocalUsuario() + ",'"
                + getAliasUsuario() + "',"
                + getIdSeq() + ")";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(updateStr);

            // Obtiene el resultset
            selectStatement.executeUpdate();

            //
            validaOk = true;

            // Cierra el Resultset
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
            return validaOk;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {
            // Cierra la conexion a la base de datos limpiando la statement usada y la
            // conexion
            return validaOk;
        }
    }

    //
    public Vector listaUsuarioTx() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        Vector contenedor = new Vector();
        Connection connection = null;

        String selectStr =
                "SELECT ctrlUsuarios.idUsuario           "
                + "      ,ctrlUsuarios.nombreUsuario       "
                + "      ,ctrlUsuarios.clave               "
                + "      ,ctrlUsuarios.idNivel             "
                + "      ,ctrlUsuarios.direccion           "
                + "      ,ctrlUsuarios.telefono            "
                + "      ,ctrlUsuarios.fechaCambioClave    "
                + "      ,ctrlUsuarios.estado              "
                + "      ,ctrlUsuarios.email               "
                + "      ,ctrlUsuarios.idLocal             "
                + "      ,ctrlUsuarios.aliasUsuario        "
                + "      ,ctrlUsuarios.idSeq               "
                + "FROM   ctrlUsuarios                     "
                + "WHERE ctrlUsuarios.idSeq >              "
                + getIdSeq() + "                           "
                + "ORDER BY ctrlUsuarios.idSeq";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaUsuarioBean fachadaBean;

            while (rs.next()) {

                //
                fachadaBean = new FachadaUsuarioBean();

                //
                fachadaBean.setIdUsuario(rs.getString("idUsuario"));
                fachadaBean.setNombreUsuario(rs.getString("nombreUsuario"));
                fachadaBean.setClave(rs.getString("clave"));
                fachadaBean.setIdNivel(rs.getInt("idNivel"));
                fachadaBean.setDireccion(rs.getString("direccion"));
                fachadaBean.setTelefono(rs.getString("telefono"));
                fachadaBean.setFechaCambioClave(
                        rs.getString("fechaCambioClave"));
                fachadaBean.setEstado(rs.getInt("estado"));
                fachadaBean.setEmail(rs.getString("email"));
                fachadaBean.setIdLocalUsuario(rs.getInt("idLocal"));
                fachadaBean.setAliasUsuario(rs.getString("aliasUsuario"));
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
            jdbcAccess.cleanup(connection, selectStatement, null);
            return contenedor;
        }
    }

    // existeUsuario
    public boolean existeUsuario() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean existeLinea = false;

        Connection connection = null;

        String selectStr =
                "SELECT ctrlUsuarios.*          "
                + "FROM ctrlUsuarios              "
                + "WHERE ctrlUsuarios.idUsuario = "
                + getIdUsuario();

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            if (rs.next()) {

                existeLinea = true;

            }

            // Cierra el Resultset
            rs.close();

        } catch (SQLException sqle) {

            System.out.println("SQLException in" + nombreClase);

            String sqlMessage = sqle.getMessage();
            String sqlState = sqle.getSQLState();
            int vendorCode = sqle.getErrorCode();
            System.out.println("Ocurrio una  excepcion");
            System.out.println("Mensaje " + sqlMessage);
            System.out.println("SQL State " + sqlState);
            System.out.println("Vendor Code " + vendorCode);
            return existeLinea;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {
            // Cierra la conexion a la base de datos limpiando la statement usada y la
            jdbcAccess.cleanup(connection, selectStatement, null);
            return existeLinea;
        }
    }

// traverse_UsuarioTx
    public void traverse_UsuarioTx(XmlParser parser,
            String indent) throws Exception {

        //
        boolean leave = false;

        //
        String xIdUsuario = new String();
        String nombreUsuario = new String();
        String xClave = new String();
        String idNivel = new String();
        String direccion = new String();
        String telefono = new String();
        String fechaCambioClave = new String();
        String estado = new String();
        String email = new String();
        String idLocal = new String();
        String aliasUsuario = new String();
        String idSeq = new String();

        do {
            ParseEvent event = parser.read();
            ParseEvent pe;

            switch (event.getType()) {

                // For example, <CxC>
                case Xml.START_TAG:

                    // see API doc of StartTag for more access methods
                    // Pick up idagenda for display
                    if ("idUsuario".equals(event.getName())) {
                        pe = parser.read();
                        xIdUsuario = pe.getText();
                    }

                    // Pick up clave for display
                    if ("nombreUsuario".equals(event.getName())) {
                        pe = parser.read();
                        nombreUsuario = pe.getText();
                    }

                    // Pick up clave for display
                    if ("clave".equals(event.getName())) {
                        pe = parser.read();
                        xClave = pe.getText();
                    }

                    // Pick up clave for display
                    if ("idNivel".equals(event.getName())) {
                        pe = parser.read();
                        idNivel = pe.getText();
                    }

                    // Pick up clave for display
                    if ("direccion".equals(event.getName())) {
                        pe = parser.read();
                        direccion = pe.getText();
                    }

                    // Pick up clave for display
                    if ("telefono".equals(event.getName())) {
                        pe = parser.read();
                        telefono = pe.getText();
                    }


                    // Pick up clave for display
                    if ("fechaCambioClave".equals(event.getName())) {
                        pe = parser.read();
                        fechaCambioClave = pe.getText();
                    }

                    // Pick up clave for display
                    if ("estado".equals(event.getName())) {
                        pe = parser.read();
                        estado = pe.getText();
                    }

                    // Pick up clave for display
                    if ("email".equals(event.getName())) {
                        pe = parser.read();
                        email = pe.getText();
                    }

                    // Pick up clave for display
                    if ("idLocal".equals(event.getName())) {
                        pe = parser.read();
                        idLocal = pe.getText();
                    }

                    // Pick up clave for display
                    if ("aliasUsuario".equals(event.getName())) {
                        pe = parser.read();
                        aliasUsuario = pe.getText();
                    }

                    // Pick up clave for display
                    if ("idSeq".equals(event.getName())) {
                        pe = parser.read();
                        idSeq = pe.getText();

                        // ingresa
                        setIdUsuario(xIdUsuario);
                        setNombreUsuario(nombreUsuario);
                        setClave(xClave);
                        setIdNivel(idNivel);
                        setDireccion(direccion);
                        setTelefono(telefono);
                        setFechaCambioClave(fechaCambioClave);
                        setEstado(estado);
                        setEmail(email);
                        setIdLocalUsuario(idLocal);
                        setAliasUsuario(aliasUsuario);
                        setIdSeq(idSeq);

                        //
                        if (this.existeUsuario()) {
                            System.out.println(" actualiza usuario");

                            //
                            this.actualiza();
                        } else {
                            System.out.println(" ingresa usuario");

                            //
                            this.ingresa();
                        }

                    }

                    traverse_UsuarioTx(parser, ""); // recursion call for each <tag></tag>
                    break;

                // For example </title?
                case Xml.END_TAG:
                    leave = true;
                    break;

                // For example </rss>
                case Xml.END_DOCUMENT:
                    leave = true;
                    break;

                // For example, the text between tags
                case Xml.TEXT:
                    break;
                case Xml.WHITESPACE:
                    break;
                default:
            }
        } while (!leave);
    }
    
    public Vector listaNivelOpcionNN(String xIdNivelMultipleStr) {
        Connection connection = null;

        Vector contenedor = new Vector();

        String selectUsuarioStr = "SELECT ctrlUsuarios.IDUSUARIO              "
                + ",ctrlUsuarios.NOMBREUSUARIO          "
                + ",ctrlUsuarios.IDNIVEL                "
                + ",ctrlUsuarios.DIRECCION              "
                + ",ctrlUsuarios.TELEFONO               "
                + ",ctrlUsuarios.FECHACAMBIOCLAVE       "
                + ",ctrlUsuarios.ESTADO                 "
                + ",ctrlUsuarios.EMAIL                  "
                + ",ctrlUsuarios.idLocal                "
                + ",01 AS ordenSalida             "
                + "FROM ctrlUsuarios                    "
                + "WHERE ctrlUsuarios.IDLOCAL   =       "
                + getIdLocalUsuario() + "               "
                + "AND   ctrlUsuarios.idNivel   IN     ("
                + xIdNivelMultipleStr + ")              "
                + "AND   ctrlUsuarios.IDUSUARIO =       "
                + getIdUsuario() + "                    "
                + "AND   ctrlUsuarios.estado    =       "
                + getEstado() + "                       "
                + "UNION                                "
                + "SELECT ctrlUsuarios.IDUSUARIO        "
                + "      ,ctrlUsuarios.NOMBREUSUARIO    "
                + "      ,ctrlUsuarios.IDNIVEL          "
                + "      ,ctrlUsuarios.DIRECCION        "
                + "      ,ctrlUsuarios.TELEFONO         "
                + "      ,ctrlUsuarios.FECHACAMBIOCLAVE "
                + "      ,ctrlUsuarios.ESTADO           "
                + "      ,ctrlUsuarios.EMAIL            "
                + "      ,ctrlUsuarios.idLocal          "
                + "      ,02 AS ordenSalida             "
                + "FROM ctrlUsuarios                    "
                + "WHERE ctrlUsuarios.IDLOCAL   =       "
                + getIdLocalUsuario() + "               "
                + "AND   ctrlUsuarios.idNivel   IN     ("
                + xIdNivelMultipleStr + ")              "
                + "AND   ctrlUsuarios.IDUSUARIO !=      "
                + getIdUsuario() + "                    "
                + "AND   ctrlUsuarios.estado     =      "
                + getEstado() + "                       "
                + "ORDER BY 10,2                        ";

        PreparedStatement selectStatement = null;
        try {
            connection = this.jdbcAccess.getConnection();

            selectStatement = connection.prepareStatement(selectUsuarioStr);

            ResultSet rs = selectStatement.executeQuery();
            while (rs.next()) {
                FachadaUsuarioBean fachadaUsuarioBean = new FachadaUsuarioBean();

                fachadaUsuarioBean.setIdUsuario(rs.getString("idUsuario"));
                fachadaUsuarioBean.setNombreUsuario(rs.getString("nombreUsuario"));

                fachadaUsuarioBean.setIdNivel(rs.getInt("idNivel"));
                fachadaUsuarioBean.setDireccion(rs.getString("DIRECCION"));
                fachadaUsuarioBean.setTelefono(rs.getString("TELEFONO"));
                fachadaUsuarioBean.setFechaCambioClave(rs.getString("FECHACAMBIOCLAVE"));

                fachadaUsuarioBean.setEstado(rs.getInt("estado"));
                fachadaUsuarioBean.setEmail(rs.getString("email"));
                fachadaUsuarioBean.setIdLocalUsuario(rs.getInt("idLocal"));

                contenedor.add(fachadaUsuarioBean);
            }

            rs.close();

        } // Area de obtencion de Excepciones
        catch (NamingException ne) {
            System.out.println("NamingException in JDBCAccess :" + ne);
        } catch (SQLException sqle) {
            System.out.println("SQLException in JDBCAccess:" + sqle);
        } catch (Exception e) {
            System.out.println("Exception In JDBCAccess:" + e);
        } finally {

            // Cierra la conexion a la base de datos
            jdbcAccess.cleanup(connection, selectStatement, null);
            return contenedor;

        }
    }
}
