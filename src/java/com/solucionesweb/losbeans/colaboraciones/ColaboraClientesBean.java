
package com.solucionesweb.losbeans.colaboraciones;

import com.solucionesweb.lasayudas.JDBCAccess;
import com.solucionesweb.losbeans.fachada.FachadaTerceroBean;
import com.solucionesweb.losbeans.utilidades.IConstantes;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.naming.NamingException;


public class ColaboraClientesBean extends FachadaTerceroBean
                                          implements Serializable, IConstantes {

  	// Nombre JNDI del Data Source que esta clase requiere
    private static final String DATA_SOURCE_NAME = "CommerceAccess";

	// Clase helper usada para acceder a la base de datos
	private JDBCAccess jdbcAccess;

	// Metodo constructor por defecto sin parametros
    public ColaboraClientesBean() {

  	// Al instanciar el Bean establece la conexion a la base de datos
	   jdbcAccess = new JDBCAccess(DATA_SOURCE_NAME);
    }
    
    // listaTerceroOpcion
    public Vector listaTerceroOpcion() {

      //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
      Class tipoObjeto       = this.getClass();
      String nombreClase     = tipoObjeto.getName();

      Vector contenedor      = new Vector();
      Connection connection  = null;


      String selectStr =       
                  "SELECT tblTerceros.idCliente,     "
                + "       tblTerceros.nombreTercero, "
                + "       1 AS orden                 "
                + "FROM tblTerceros                  "
                + "WHERE tblTerceros.idTipoTercero = 1"
                + "ORDER BY 3, 2                     ";

       PreparedStatement selectStatement = null;

      try {

        // Obtiene una conexion a la base de datos, requiere Try
        connection = jdbcAccess.getConnection();

        // Prepara la sentencia de Busqueda
        selectStatement = connection.prepareStatement(selectStr);

        // Obtiene el resultset
        ResultSet rs = selectStatement.executeQuery();

        // Variable de fachada de los datos
        FachadaTerceroBean fachadaBean;

        while (rs.next()) {

              fachadaBean = new FachadaTerceroBean();

              //
              fachadaBean.setIdCliente(rs.getString("idCliente"));
              fachadaBean.setNombreTercero(rs.getString("nombreTercero"));

              contenedor.add(fachadaBean);

        }

        // Cierra el Resultset
        rs.close();
        jdbcAccess.cleanup(connection, selectStatement,null);

      }
      catch(NamingException ne)
      {
      	System.out.println("NamingException in " + nombreClase + " " + ne);
      }
      catch (SQLException sqle) {

		System.out.println("SQLException in" + nombreClase);

		String sqlMessage = sqle.getMessage();
        String sqlState = sqle.getSQLState();
        int vendorCode = sqle.getErrorCode();
		System.out.println("Ocurrio una  excepcion");
		System.out.println("Mensaje " + sqlMessage);
		System.out.println("SQL State " + sqlState);
		System.out.println("Vendor Code " + vendorCode);
		return contenedor;

	  }

	  catch (Exception e)
	  {
		System.out.println("Exception In :" + nombreClase + " " + e);
	  }
      finally {
        // Cierra la conexion a la base de datos limpiando la statement usada y la
        // conexion
       return contenedor;
      }
   }
    
     // Activa referencia cliente    
    public boolean activaRefCliente(String  xFicha) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean activa = false;

        Vector contenedor = new Vector();
        Connection connection = null;

        String selectStr =
                " UPDATE tblPlusFicha                       "
                + " SET tblPlusFicha.estado       =   1   "
                + " WHERE tblPlusFicha.idFicha          =      "
                + xFicha + "   "
                + " AND   tblPlusFicha.idCliente         =      "
                + getIdCliente() + "   ";

        PreparedStatement selectStatement = null;

        System.out.println(" selectStr " + selectStr);

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            selectStatement.execute();
            activa = true;

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
            return activa;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {
            // Cierra la conexion a la base de datos limpiando la statement usada y la
            // conexion
            return activa;
        }
    }
    
     // Activa referencia cliente    
    public boolean inactivaRefCliente(String  xFicha) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean inactiva = false;

        Vector contenedor = new Vector();
        Connection connection = null;

        String selectStr =
                " UPDATE tblPlusFicha                       "
                + " SET tblPlusFicha.estado       =   2   "
                + " WHERE tblPlusFicha.idFicha          =      "
                + xFicha + "   "
                + " AND   tblPlusFicha.idCliente         =      "
                + getIdCliente() + "   ";

        PreparedStatement selectStatement = null;

        System.out.println(" selectStr " + selectStr);

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            selectStatement.execute();
            inactiva = true;

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
            return inactiva;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {
            // Cierra la conexion a la base de datos limpiando la statement usada y la
            // conexion
            return inactiva;
        }
    }
    
    // listaUnTerceroUnionFCH
    public FachadaTerceroBean listaUnTercero() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        //
        FachadaTerceroBean fachadaTerceroBean = new FachadaTerceroBean();

        Connection connection = null;

        String selectStr =
                  "SELECT tblTerceros.idCliente,            "
                + "       tblTerceros.idTercero,            "
                + "       tblTerceros.tipoIdTercero,        "
                + "       tblTerceros.digitoVerificacion,   "
                + "       tblTerceros.idTipoTercero,        "
                + "       tblTerceros.idPersona,            "
                + "       tblTerceros.idAutoRetenedor,      "
                + "       tblTerceros.idRegimen,            "
                + "       tblTerceros.nombreTercero,        "
                + "       tblTerceros.direccionTercero,     "
                + "       tblTerceros.idDptoCiudad,         "
                + "       tblTerceros.telefonoFijo,         "
                + "       tblTerceros.telefonoCelular,      "
                + "       tblTerceros.telefonoFax,          "
                + "       tblTerceros.email,                "
                + "       tblTerceros.idFormaPago,          "
                + "       tblTerceros.estado,               "
                + "       tblTerceros.idRuta,               "
                + "       tblTerceros.nombreEmpresa,        "
                + "       tblTerceros.cupoCredito,          "
                + "       tblTerceros.indicador,            "
                + " (SELECT tblCiudades.nombreCiudad + '/' +"
                + "         tblCiudades.nombreDpto          "
                + "  FROM tblCiudades                       "
                + "  WHERE tblCiudades.idCiudad      =      "
                + "            tblTerceros.idDptoCiudad)    "
                + "                      AS ciudadTercero,  "
                + "       tblTerceros.contactoTercero,      "
                + "       tblTerceros.idListaPrecio,        "
                + "       tblTerceros.idVendedor,           "
                + "       00 AS idLocalTercero              "
                + "FROM   tblTerceros                       "
                + "WHERE  tblTerceros.idTipoTercero =       "
                + getIdTipoTercero() + "                    "
                + "AND    tblTerceros.idCliente   =         "
                + getIdCliente() + "                        "
                + "ORDER BY tblTerceros.nombreTercero       ";


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
                 fachadaTerceroBean.setIdCliente(rs.getString("idCliente"));
                fachadaTerceroBean.setIdTercero(rs.getDouble("idTercero"));
                fachadaTerceroBean.setTipoIdTercero(
                        rs.getString("tipoIdTercero"));
                fachadaTerceroBean.setDigitoVerificacion(
                        rs.getInt("digitoVerificacion"));
                fachadaTerceroBean.setIdTipoTercero(rs.getInt("idTipoTercero"));
                fachadaTerceroBean.setIdPersona(rs.getInt("idPersona"));
                fachadaTerceroBean.setIdAutoRetenedor(
                        rs.getInt("idAutoRetenedor"));
                fachadaTerceroBean.setIdRegimen(rs.getString("idRegimen"));
                fachadaTerceroBean.setNombreTercero(
                        rs.getString("nombreTercero"));
                fachadaTerceroBean.setDireccionTercero(
                        rs.getString("direccionTercero"));
                fachadaTerceroBean.setIdDptoCiudad(rs.getInt("idDptoCiudad"));
                fachadaTerceroBean.setTelefonoFijo(
                        rs.getString("telefonoFijo"));
                fachadaTerceroBean.setTelefonoCelular(
                        rs.getString("telefonoCelular"));
                fachadaTerceroBean.setTelefonoFax(rs.getString("telefonoFax"));
                fachadaTerceroBean.setEmail(rs.getString("email"));
                fachadaTerceroBean.setIdFormaPago(rs.getInt("idFormaPago"));
                fachadaTerceroBean.setEstado(rs.getInt("estado"));
                fachadaTerceroBean.setIdRuta(rs.getString("idRuta"));
                fachadaTerceroBean.setNombreEmpresa(
                        rs.getString("nombreEmpresa"));
                fachadaTerceroBean.setCupoCredito(rs.getDouble("cupoCredito"));
                fachadaTerceroBean.setIndicador(rs.getInt("indicador"));
                fachadaTerceroBean.setCiudadTercero(
                        rs.getString("ciudadTercero"));
                fachadaTerceroBean.setContactoTercero(
                        rs.getString("contactoTercero"));
                fachadaTerceroBean.setIdListaPrecio(rs.getInt("idListaPrecio"));
                fachadaTerceroBean.setIdVendedor(rs.getDouble("idVendedor"));
                fachadaTerceroBean.setIdLocalTercero(
                        rs.getInt("idLocalTercero"));

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

            // Cierra connection
            jdbcAccess.cleanup(connection, selectStatement, null);
            return fachadaTerceroBean;

        }
    }
}
