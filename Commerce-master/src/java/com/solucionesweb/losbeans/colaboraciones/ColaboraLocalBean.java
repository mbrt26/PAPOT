package com.solucionesweb.losbeans.colaboraciones;

// Importa la clase helper JDBCAccess que contiene los accesos a la base de datos
import com.solucionesweb.lasayudas.JDBCAccess;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaColaboraLocalBean;

// Importa el bean de utilidades
import com.solucionesweb.losbeans.utilidades.IConstantes;

// Importa los paquetes de java
import java.sql.*;
import java.io.*;
import java.util.*;
import java.io.IOException.*;

// Importa los paquetes de javax
import javax.naming.*;

public class ColaboraLocalBean  implements Serializable, IConstantes {

  	// Nombre JNDI del Data Source que esta clase requiere
    private static final String DATA_SOURCE_NAME = "CommerceAccess";

	// Clase helper usada para acceder a la base de datos
	private JDBCAccess jdbcAccess;

	// Metodo constructor por defecto sin parametros
    public ColaboraLocalBean() {

  	// Al instanciar el Bean establece la conexion a la base de datos
	   jdbcAccess = new JDBCAccess(DATA_SOURCE_NAME);
    }

    // Propiedades
    private int idLocal;
    private String nit;
    private String razonSocial;
    private String nombreLocal;
    private String direccion;
    private String telefono;
    private String fax;

    // Metodos
    public void setIdLocal( int idLocal )
    {
        this.idLocal = idLocal ;
    }

    public int getIdLocal()
    {
        return this.idLocal;
    }

    public void setIdLocal( String idLocalStr )
    {
        this.idLocal = new Integer(idLocalStr).intValue() ;
    }

    public String getIdLocalStr()
    {
        return new Integer(this.idLocal).toString();
    }

    public void setNit( String nit )
    {
        this.nit = nit ;
    }

    public String getNit()
    {
        return this.nit;
    }

    public void setRazonSocial( String razonSocial )
    {
        this.razonSocial = razonSocial ;
    }

    public String getRazonSocial()
    {
        return this.razonSocial;
    }

    public void setNombreLocal( String nombreLocal )
    {
        this.nombreLocal = nombreLocal ;
    }

    public String getNombreLocal()
    {
        return this.nombreLocal;
    }

    public void setDireccion( String direccion )
    {
        this.direccion = direccion ;
    }

    public String getDireccion()
    {
        if (this.direccion == null) {
           return STRINGVACIO;
        }
        return this.direccion;
    }

    public void setTelefono( String telefono )
    {
        this.telefono = telefono ;
    }

    public String getTelefono()
    {
        if (this.telefono == null) {
           return STRINGVACIO;
        }
        return this.telefono;
    }

    public void setFax( String fax )
    {
        this.fax = fax ;
    }

    public String getFax()
    {
        if (this.fax == null) {
           return STRINGVACIO;
        }
        return this.fax;
    }

    // Metodo listaUnLocal
    public FachadaColaboraLocalBean listaUnLocal() {

      //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
      Class tipoObjeto       = this.getClass();
      String nombreClase     = tipoObjeto.getName();

      // Variable de fachada de los datos
      FachadaColaboraLocalBean fachadaBean = new FachadaColaboraLocalBean();

      boolean    validaOk    = false;
      Connection connection  = null;

      //
      String selectStr =
      "SELECT tblLocales.idLocal,      " +
      "       tblLocales.nit,          " +
      "       tblLocales.razonSocial,  " +
      "       tblLocales.nombreLocal,  " +
      "       tblLocales.direccion,    " +
      "       tblLocales.telefono,     " +
      "       tblLocales.fax           " +
      "FROM tblLocales                 " +
      "WHERE tblLocales.idLocal = ( ? )" ;

      PreparedStatement selectStatement = null;

      try {

        // Obtiene una conexion a la base de datos, requiere Try
        connection = jdbcAccess.getConnection();

        // Prepara la sentencia de Busqueda
        selectStatement = connection.prepareStatement(selectStr);

        //
	    selectStatement.setInt(1,getIdLocal());

        // Obtiene el resultset
        ResultSet rs = selectStatement.executeQuery();

        if (rs.next()) {

              fachadaBean.setIdLocal(rs.getInt("idLocal"));
              fachadaBean.setNit(rs.getString("nit"));
              fachadaBean.setRazonSocial(rs.getString("razonSocial"));
              fachadaBean.setNombreLocal(rs.getString("nombreLocal"));
              fachadaBean.setDireccion(rs.getString("direccion"));
              fachadaBean.setTelefono(rs.getString("telefono"));
              fachadaBean.setFax(rs.getString("fax"));

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
        return fachadaBean;

	  }

	  catch (Exception e)
	  {
		System.out.println("Exception In :" + nombreClase + " " + e);
	  }
      finally {

        // Cierra la conexion
       return fachadaBean;

      }
    }

    // Metodo listaLocales
    public Vector listaLocales() {

      //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
      Class tipoObjeto       = this.getClass();
      String nombreClase     = tipoObjeto.getName();
      Vector contenedor      = new Vector();

      boolean    validaOk    = false;
      Connection connection  = null;

      String selectStr =
      "SELECT tblLocales.idLocal,      " +
      "       tblLocales.nit,          " +
      "       tblLocales.razonSocial,  " +
      "       tblLocales.nombreLocal,  " +
      "       tblLocales.direccion,    " +
      "       tblLocales.telefono,     " +
      "       tblLocales.fax           " +
      "FROM tblLocales                 " ;

      PreparedStatement selectStatement = null;

      try {

        // Obtiene una conexion a la base de datos, requiere Try
        connection = jdbcAccess.getConnection();

        // Prepara la sentencia de Busqueda
        selectStatement = connection.prepareStatement(selectStr);

        // Obtiene el resultset
        ResultSet rs = selectStatement.executeQuery();

        // Variable de fachada de los datos
        FachadaColaboraLocalBean fachadaBean;

        while (rs.next()) {

              fachadaBean = new FachadaColaboraLocalBean();

              fachadaBean.setIdLocal(rs.getInt("idLocal"));
              fachadaBean.setNit(rs.getString("nit"));
              fachadaBean.setRazonSocial(rs.getString("razonSocial"));
              fachadaBean.setNombreLocal(rs.getString("nombreLocal"));
              fachadaBean.setDireccion(rs.getString("direccion"));
              fachadaBean.setTelefono(rs.getString("telefono"));
              fachadaBean.setFax(rs.getString("fax"));

              //
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

        // Cierra la conexion
       return contenedor;

      }
    }

    // Metodo listaDatosUnLocal
    public Vector listaDatosUnLocal() {

      //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
      Class tipoObjeto       = this.getClass();
      String nombreClase     = tipoObjeto.getName();
      Vector contenedor      = new Vector();

      boolean    validaOk    = false;
      Connection connection  = null;

      String selectStr =
      "SELECT tblLocales.idLocal,      " +
      "       tblLocales.nit,          " +
      "       tblLocales.razonSocial,  " +
      "       tblLocales.nombreLocal,  " +
      "       tblLocales.direccion,    " +
      "       tblLocales.telefono,     " +
      "       tblLocales.fax           " +
      "FROM tblLocales                 " +
      "WHERE tblLocales.idLocal = ( ? )" ;

      PreparedStatement selectStatement = null;

      try {

        // Obtiene una conexion a la base de datos, requiere Try
        connection = jdbcAccess.getConnection();

        // Prepara la sentencia de Busqueda
        selectStatement = connection.prepareStatement(selectStr);

        //
	    selectStatement.setInt(1,getIdLocal());

        // Obtiene el resultset
        ResultSet rs = selectStatement.executeQuery();

        // Variable de fachada de los datos
        FachadaColaboraLocalBean fachadaBean;

        while (rs.next()) {

              fachadaBean = new FachadaColaboraLocalBean();

              fachadaBean.setIdLocal(rs.getInt("idLocal"));
              fachadaBean.setNit(rs.getString("nit"));
              fachadaBean.setRazonSocial(rs.getString("razonSocial"));
              fachadaBean.setNombreLocal(rs.getString("nombreLocal"));
              fachadaBean.setDireccion(rs.getString("direccion"));
              fachadaBean.setTelefono(rs.getString("telefono"));
              fachadaBean.setFax(rs.getString("fax"));

              //
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

        // Cierra la conexion
       return contenedor;

      }
    }

}