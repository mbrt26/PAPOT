package com.solucionesweb.losbeans.negocio;

// Importa la clase helper JDBCAccess que contiene los accesos a la base de datos
import com.solucionesweb.lasayudas.JDBCAccess;

// Importa los paquetes de java
import java.sql.*;
import java.io.*;
import java.io.IOException.*;

// Importa los paquetes de javax
import javax.naming.*;

public class LocalVirtualBean  implements Serializable {

  	// Nombre JNDI del Data Source que esta clase requiere
    private static final String DATA_SOURCE_NAME = "CommerceAccess";

	// Clase helper usada para acceder a la base de datos
	private JDBCAccess jdbcAccess;

	// Metodo constructor por defecto sin parametros
    public LocalVirtualBean() {

  	// Al instanciar el Bean establece la conexion a la base de datos
	   jdbcAccess = new JDBCAccess(DATA_SOURCE_NAME);
    }

    // Propiedades
    private int idLocal;
    private int idLocalVirtual;
    private int vrVentaSinIva;
    private int estado;

    // Metodos
    public void setIdLocal( int idLocal )
    {
        this.idLocal = idLocal;
    }

    public int getIdLocal()
    {
        return idLocal;
    }

    public void setIdLocal( String idLocalStr )
    {
        this.idLocal = new Integer(idLocalStr).intValue();
    }

    public String getIdLocalStr()
    {
        return new Integer(idLocal).toString();
    }

    public int getIdLocalVirtual()
    {
        return idLocalVirtual;
    }

    public void setIdLocalVirtual( String idLocalVirtualStr )
    {
        this.idLocalVirtual = new Integer(idLocalVirtualStr).intValue();
    }

    public void setVrVentaSinIva( int vrVentaSinIva )
    {
        this.vrVentaSinIva = vrVentaSinIva;
    }

    public int getVrVentaSinIva()
    {
        return vrVentaSinIva;
    }

    public void setVrVentaSinIva( String vrVentaSinIvaStr )
    {
        this.vrVentaSinIva = new Integer(vrVentaSinIvaStr).intValue();
    }

    public String getVrVentaSinIvaStr()
    {
        return new Integer(vrVentaSinIva).toString();
    }

    public void setEstado( int estado )
    {
        this.estado = estado ;
    }

    public int getEstado()
    {
        return estado;
    }

    public void setEstado( String estadoStr )
    {
        this.estado = new Integer(estadoStr).intValue();
    }

    public String getEstadoStr()
    {
        return new Integer(estado).toString();
    }


    // Metodo
    public int seleccionaLocalVirtual() {

      //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
      Class tipoObjeto       = this.getClass();
      String nombreClase     = tipoObjeto.getName();

      // localInicial
      int seleccionaLocalVirtual = getIdLocal();
      Connection connection      = null;

      String selectStr =
      "SELECT tblLocalesVirtuales.IDLOCAL,            " +
      "       tblLocalesVirtuales.IDLOCALVIRTUAL,     " +
      "       tblLocalesVirtuales.VRVENTASINIVA,      " +
      "       tblLocalesVirtuales.ESTADO              " +
      "FROM tblLocalesVirtuales                       " +
      "WHERE tblLocalesVirtuales.IDLOCAL        = (?) " +
      "AND   tblLocalesVirtuales.VRVENTASINIVA  < (?) " ;

      PreparedStatement selectStatement = null;

      try {

        // Obtiene una conexion a la base de datos, requiere Try
        connection = jdbcAccess.getConnection();

        // Prepara la sentencia de Busqueda
        selectStatement = connection.prepareStatement(selectStr);

        // inicializa los valores de los parametros

        selectStatement.setInt(1,getIdLocal());
        selectStatement.setInt(2,getVrVentaSinIva());

        // Obtiene el resultset
        ResultSet rs = selectStatement.executeQuery();

        if (rs.next()) {

           seleccionaLocalVirtual = rs.getInt("idLocalVirtual");

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
		return seleccionaLocalVirtual;

	  }

	  catch (Exception e)
	  {
		System.out.println("Exception In :" + nombreClase + " " + e);
	  }
      finally {
        // Cierra la conexion a la base de datos limpiando la statement usada y la
        // conexion
       return seleccionaLocalVirtual;
      }
    }
}