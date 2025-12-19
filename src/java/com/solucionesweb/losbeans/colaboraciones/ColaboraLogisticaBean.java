package com.solucionesweb.losbeans.colaboraciones;

// Importa la clase helper JDBCAccess que contiene los accesos a la base de datos
import com.solucionesweb.lasayudas.JDBCAccess;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaColaboraLogisticaBean;

// Importa los paquetes de java
import java.sql.*;
import java.io.*;
import java.util.*;
import java.io.IOException.*;

// Importa los paquetes de javax
import javax.naming.*;

public class ColaboraLogisticaBean  implements Serializable {

  	// Nombre JNDI del Data Source que esta clase requiere
    private static final String DATA_SOURCE_NAME = "CommerceAccess";

	// Clase helper usada para acceder a la base de datos
	private JDBCAccess jdbcAccess;

	// Metodo constructor por defecto sin parametros
    public ColaboraLogisticaBean() {

  	// Al instanciar el Bean establece la conexion a la base de datos
	   jdbcAccess = new JDBCAccess(DATA_SOURCE_NAME);
    }

    // Propiedades
    private int idLocal;
    private int idTipoOrden;
    private double idCliente;
    private double idUsuario;

    //
    private int idPedido;
    private String tipoDcto;
    private int idDcto;
    private String idTipoPedido;
    private String placaVehiculo;
    private String fechaPedido;
    private String ordenCompra;

    //
    private String fechaInicial;
    private String fechaFinal;

    // Metodos
    public void setIdLocal( int idLocal )
    {
        this.idLocal = idLocal ;
    }

    public int getIdLocal()
    {
        return idLocal;
    }

    public void setIdLocal( String idLocalStr )
    {
        this.idLocal = new Integer(idLocalStr).intValue() ;
    }

    public String getIdLocalStr()
    {
        return new Integer(idLocal).toString();
    }

    public void setIdTipoOrden( int idTipoOrden )
    {
        this.idTipoOrden = idTipoOrden ;
    }

    public int getIdTipoOrden()
    {
        return idTipoOrden;
    }

    public void setIdTipoOrden( String idTipoOrdenStr )
    {
        this.idTipoOrden = new Integer(idTipoOrdenStr).intValue() ;
    }

    public String getIdTipoOrdenStr()
    {
        return new Integer(idTipoOrden).toString();
    }

    public void setIdCliente( double idCliente )
    {
        this.idCliente = idCliente ;
    }

    public double getIdCliente()
    {
        return idCliente;
    }

    public void setIdCliente( String idClienteStr )
    {
        this.idCliente = new Double(idClienteStr).doubleValue() ;
    }

    public String getIdClienteStr()
    {
        return new Double(idCliente).toString();
    }

    public void setIdUsuario( double idUsuario )
    {
        this.idUsuario = idUsuario ;
    }

    public double getIdUsuario()
    {
        return idUsuario;
    }

    public void setIdUsuario( String idUsuarioStr )
    {
        this.idUsuario = new Double(idUsuarioStr).doubleValue() ;
    }

    public String getIdUsuarioStr()
    {
        return new Double(idUsuario).toString();
    }

    public void setIdPedido( int idPedido )
    {
        this.idPedido = idPedido ;
    }

    public int getIdPedido()
    {
        return idPedido;
    }

    public void setIdPedido( String idPedidoStr )
    {
        this.idPedido = new Integer(idPedidoStr).intValue() ;
    }

    public String getIdPedidoStr()
    {
        return new Integer(idPedido).toString();
    }

    public String getTipoDcto()
    {
        return tipoDcto;
    }

    public void setTipoDcto( String tipoDcto )
    {
        this.tipoDcto = tipoDcto ;
    }

    public String getIdTipoPedido()
    {
        return idTipoPedido;
    }

    public void setIdTipoPedido( String idTipoPedido )
    {
        this.idTipoPedido = idTipoPedido ;
    }

    public String getPlacaVehiculo()
    {
        return placaVehiculo;
    }

    public void setPlacaVehiculo( String placaVehiculo )
    {
        this.placaVehiculo = placaVehiculo ;
    }

    public void setFechaPedido( String fechaPedido )
    {
        this.fechaPedido = fechaPedido ;
    }

    public String getFechaPedido()
    {
        return fechaPedido;
    }

    public void setIdDcto( int idDcto )
    {
        this.idDcto = idDcto ;
    }

    public int getIdDcto()
    {
        return idDcto;
    }

    public void setIdDcto( String idDctoStr )
    {
        this.idDcto = new Integer(idDctoStr).intValue() ;
    }

    public String getIdDctoStr()
    {
        return new Integer(idDcto).toString();
    }

    public void setOrdenCompra( String ordenCompra )
    {
        this.ordenCompra = ordenCompra;
    }

    public String getOrdenCompra()
    {

        return ordenCompra;
    }

    public String getOrdenCompraOracle()
    {

        return "'" + getOrdenCompra() + "'" ;
    }

    public void setFechaInicial( String fechaInicial )
    {
        this.fechaInicial = fechaInicial ;
    }

    public String getFechaInicial()
    {
        return fechaInicial;
    }

    public void setFechaFinal( String fechaFinal )
    {
        this.fechaFinal = fechaFinal ;
    }

    public String getFechaFinal()
    {
        return fechaFinal;
    }

    // Metodo consultaPedido Oracle
    public Vector consultaPedido() {

      //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
      Class tipoObjeto       = this.getClass();
      String nombreClase     = tipoObjeto.getName();
      Vector contenedor      = new Vector();

      Connection connection  = null;


      //
      String selectStr =
"SELECT CMENCAPED.CMPED_OC_NRO              " +
"                        AS ordenCompra,    " +
"       CMENCAPED.CMPED_K1_TIPO_PED         " +
"                        AS idTipoPed,      " +
"       CMENCAPED.CMPED_K1_CO               " +
"                        AS idLocal,        " +
"       CMENCAPED.CMPED_K3_TERC             " +
"                        AS idCliente,      " +
"       CMENCAPED.CMPED_K1_NRO_PED          " +
"                        AS idPedido,       " +
"       CMDOCRM.CMDOCRM_K1_TIPO             " +
"                        AS tipoDcto,       " +
"       CMDOCRM.CMDOCRM_K1_NRO              " +
"                        AS idDcto,         " +
"       CMDOCRM.CMDOCRM_K2_FECHA            " +
"                        AS fechaPedido     " +
"FROM CMENCAPED                             " +
"INNER JOIN CMDOCRM                         " +
"ON (CMENCAPED.CMPED_K3_TERC        =       " +
"              CMDOCRM.CMDOCRM_K3_TERC)     " +
"AND (CMENCAPED.CMPED_K1_CO         =       " +
"                CMDOCRM.CMDOCRM_CO_OV)     " +
"AND (CMENCAPED.CMPED_OC_NRO        =       " +
"                   CMDOCRM.CMDOCRM_OC_NRO) " +
"AND ( CMENCAPED.CMPED_K1_TIPO_PED  =       " +
"               CMDOCRM.CMDOCRM_TIPO_OV)    " +
"WHERE CMENCAPED.CMPED_OC_NRO       = ( ? ) " +
"AND   CMENCAPED.CMPED_K1_TIPO_PED  = 'PB'  " +
"AND   CMENCAPED.CMPED_K1_CO        = ( ? ) " +
"AND   CMDOCRM.CMDOCRM_IND_ANULADO <> 'X'   " +
"UNION                                      " +
"SELECT CMENCAPED.CMPED_OC_NRO              " +
"                        AS ordenCompra,    " +
"       CMENCAPED.CMPED_K1_TIPO_PED         " +
"                        AS idTipoPed,      " +
"       CMENCAPED.CMPED_K1_CO               " +
"                        AS idLocal,        " +
"       CMENCAPED.CMPED_K3_TERC             " +
"                        AS idCliente,      " +
"       CMENCAPED.CMPED_K1_NRO_PED          " +
"                        AS idPedido,       " +
"       CMDOCFC.CMDOCFC_K1_TIPO             " +
"                        AS tipoDcto,       " +
"       CMDOCFC.CMDOCFC_K1_NRO              " +
"                        AS idDcto,         " +
"       CMDOCFC.CMDOCFC_K2_FECHA            " +
"                        AS fechaPedido     " +
"FROM CMENCAPED                             " +
"INNER JOIN CMDOCFC                         " +
"ON (CMENCAPED.CMPED_K3_TERC        =       " +
"                  CMDOCFC.CMDOCFC_K3_TERC) " +
"AND (CMENCAPED.CMPED_K1_CO         =       " +
"                    CMDOCFC.CMDOCFC_CO_OV) " +
"AND (CMENCAPED.CMPED_OC_NRO        =       " +
"                   CMDOCFC.CMDOCFC_OC_NRO) " +
"AND (CMENCAPED.CMPED_K1_TIPO_PED   =       " +
"                 CMDOCFC.CMDOCFC_TIPO_OV)  " +
"WHERE CMENCAPED.CMPED_OC_NRO       = ( ? ) " +
"AND   CMENCAPED.CMPED_K1_TIPO_PED  = 'PB'  " +
"AND   CMENCAPED.CMPED_K1_CO        = ( ? ) " +
"AND   CMDOCFC.CMDOCFC_IND_ANULADO <> 'X'   " ;

      PreparedStatement selectStatement = null;

      try {

        // Obtiene una conexion a la base de datos, requiere Try
        connection = jdbcAccess.getConnection();

        // Prepara la sentencia de Busqueda
        selectStatement = connection.prepareStatement(selectStr);

        // inicializa los valores de los parametros
        selectStatement.setString(1,getOrdenCompra());
        selectStatement.setInt(2,getIdLocal());
        selectStatement.setString(3,getOrdenCompra());
        selectStatement.setInt(4,getIdLocal());

        // Obtiene el resultset
        ResultSet rs = selectStatement.executeQuery();

        // Variable de fachada de los datos
        FachadaColaboraLogisticaBean fachadaBean;

        while (rs.next()) {

              fachadaBean = new FachadaColaboraLogisticaBean();

              fachadaBean.setOrdenCompra(rs.getString("ordenCompra"));
              fachadaBean.setIdTipoPedido(rs.getString("idTipoPed"));
              fachadaBean.setIdLocal(rs.getInt("idLocal"));
              fachadaBean.setIdCliente(rs.getString("idCliente"));
              fachadaBean.setIdPedido(rs.getInt("idPedido"));
              fachadaBean.setTipoDcto(rs.getString("tipoDcto"));
              fachadaBean.setIdDcto(rs.getInt("idDcto"));
              fachadaBean.setFechaPedido(rs.getString("fechaPedido").substring(0,10));

              //
              contenedor.add(fachadaBean);

        }

        // Cierra el Resultset
        rs.close();

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
        jdbcAccess.cleanup(connection, selectStatement,null);
        return contenedor;

      }
    }

    // Metodo consultaLogistica Oracle
    public Vector consultaLogistica() {

      //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
      Class tipoObjeto       = this.getClass();
      String nombreClase     = tipoObjeto.getName();
      Vector contenedor      = new Vector();

      Connection connection  = null;


      //
      String selectStr =
"SELECT CMENCAPED.CMPED_OC_NRO              " +
"                        AS ordenCompra,    " +
"       CMENCAPED.CMPED_K1_TIPO_PED         " +
"                        AS idTipoPed,      " +
"       CMENCAPED.CMPED_K1_CO               " +
"                        AS idLocal,        " +
"       CMENCAPED.CMPED_K3_TERC             " +
"                        AS idCliente,      " +
"       CMENCAPED.CMPED_K1_NRO_PED          " +
"                        AS idPedido,       " +
"       ctrPcDetalle.TIPODCTO               " +
"                        AS tipoDcto,       " +
"       ctrPcDetalle.IDDCTO                 " +
"                        AS idDcto,         " +
"       ctrPc.PLACAVEHICULO                 " +
"                        AS placaVehiculo,  " +
"       ctrPc.FECHARECEPCION                " +
"                        AS fechaPedido     " +
"FROM (CMENCAPED INNER JOIN ctrPcDetalle    " +
"ON (TO_NUMBER(CMENCAPED.CMPED_K3_TERC) =   " +
"                       ctrPcDetalle.NITCC) " +
"AND (CMENCAPED.CMPED_K1_CO         =       " +
"                     ctrPcDetalle.IDPEDCO) " +
"AND (CMENCAPED.CMPED_K1_NRO_PED    =       " +
"                   ctrPcDetalle.IDPEDIDO)) " +
"INNER JOIN ctrPc                           " +
"ON (ctrPcDetalle.IDPCCO            =       " +
"                             ctrPc.IDPCCO) " +
"AND (ctrPcDetalle.IDPC             =       " +
"                               ctrPc.IDPC) " +
"WHERE CMENCAPED.CMPED_OC_NRO       = ( ? ) " +
"AND   CMENCAPED.CMPED_K1_TIPO_PED  = 'PB'  " +
"AND   CMENCAPED.CMPED_K1_CO        = ( ? ) " ;

      PreparedStatement selectStatement = null;

      try {

        // Obtiene una conexion a la base de datos, requiere Try
        connection = jdbcAccess.getConnection();

        // Prepara la sentencia de Busqueda
        selectStatement = connection.prepareStatement(selectStr);

        // inicializa los valores de los parametros
        selectStatement.setString(1,getOrdenCompra());
        selectStatement.setInt(2,getIdLocal());

        // Obtiene el resultset
        ResultSet rs = selectStatement.executeQuery();

        // Variable de fachada de los datos
        FachadaColaboraLogisticaBean fachadaBean;

        while (rs.next()) {

              fachadaBean = new FachadaColaboraLogisticaBean();

              fachadaBean.setOrdenCompra(rs.getString("ordenCompra"));
              fachadaBean.setIdTipoPedido(rs.getString("idTipoPed"));
              fachadaBean.setIdLocal(rs.getInt("idLocal"));
              fachadaBean.setIdCliente(rs.getString("idCliente"));
              fachadaBean.setIdPedido(rs.getInt("idPedido"));
              fachadaBean.setTipoDcto(rs.getString("tipoDcto"));
              fachadaBean.setIdDcto(rs.getInt("idDcto"));
              fachadaBean.setPlacaVehiculo(rs.getString("placaVehiculo"));
              fachadaBean.setFechaPedido(rs.getString("fechaPedido").substring(0,10));

              //
              contenedor.add(fachadaBean);

        }

        // Cierra el Resultset
        rs.close();

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
       jdbcAccess.cleanup(connection, selectStatement,null);
       return contenedor;

      }
    }

    /* Metodo consultaPedido MsAccess
    public Vector consultaPedido() {

      //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
      Class tipoObjeto       = this.getClass();
      String nombreClase     = tipoObjeto.getName();
      Vector contenedor      = new Vector();

      Connection connection  = null;

      String selectStr =
      "SELECT CMENCAPED.CMPED_OC_NRO              " +
      "                        AS ordenCompra,    " +
      "       CMENCAPED.CMPED_K1_TIPO_PED         " +
      "                        AS idTipoPed,      " +
      "       CMENCAPED.CMPED_K1_CO               " +
      "                        AS idLocal,        " +
      "       VAL(CMENCAPED.CMPED_K3_TERC)        " +
      "                        AS idCliente,      " +
      "       CMENCAPED.CMPED_K1_NRO_PED          " +
      "                        AS idPedido,       " +
      "       CMDOCRM.CMDOCRM_K1_TIPO             " +
      "                        AS tipoDcto,       " +
      "       CMDOCRM.CMDOCRM_K1_NRO              " +
      "                        AS idDcto,         " +
      "       CMDOCRM.CMDOCRM_K2_FECHA            " +
      "                        AS fechaPedido     " +
      "FROM CMENCAPED                             " +
      "INNER JOIN CMDOCRM                         " +
      "ON (CMENCAPED.CMPED_K3_TERC        =       " +
      "              CMDOCRM.CMDOCRM_K3_TERC)     " +
      "AND (CMENCAPED.CMPED_K1_CO         =       " +
      "                CMDOCRM.CMDOCRM_K1_CO)     " +
      "AND (CMENCAPED.CMPED_OC_NRO        =       " +
      "               CMDOCRM.CMDOCRM_OC_NRO)     " +
      "WHERE CMENCAPED.CMPED_OC_NRO       =      '" + getOrdenCompra() + "' " +
      "AND   CMENCAPED.CMPED_K1_TIPO_PED  = 'PB'  " +
      "AND   CMENCAPED.CMPED_K1_CO        =       " + getIdLocal()     + "  " +
      "UNION                                      " +
      "SELECT CMENCAPED.CMPED_OC_NRO              " +
      "                        AS ordenCompra,    " +
      "       CMENCAPED.CMPED_K1_TIPO_PED         " +
      "                        AS idTipoPed,      " +
      "       CMENCAPED.CMPED_K1_CO               " +
      "                        AS idLocal,        " +
      "       CMENCAPED.CMPED_K3_TERC             " +
      "                        AS idCliente,      " +
      "       CMENCAPED.CMPED_K1_NRO_PED          " +
      "                        AS idPedido,       " +
      "       CMDOCFC.CMDOCFC_K1_TIPO             " +
      "                        AS tipoDcto,       " +
      "       CMDOCFC.CMDOCFC_K1_NRO              " +
      "                        AS idDcto,         " +
      "       CMDOCFC.CMDOCFC_K2_FECHA            " +
      "                        AS fechaPedido     " +
      "FROM CMENCAPED                             " +
      "INNER JOIN CMDOCFC                         " +
      "ON (CMENCAPED.CMPED_K3_TERC        =       " +
      "                  CMDOCFC.CMDOCFC_K3_TERC) " +
      "AND (CMENCAPED.CMPED_K1_CO         =       " +
      "                    CMDOCFC.CMDOCFC_K1_CO) " +
      "AND (CMENCAPED.CMPED_OC_NRO        =       " +
      "                   CMDOCFC.CMDOCFC_OC_NRO) " +
      "WHERE CMENCAPED.CMPED_OC_NRO       =      '" + getOrdenCompra() + "' " +
      "AND   CMENCAPED.CMPED_K1_TIPO_PED  = 'PB'  " +
      "AND   CMENCAPED.CMPED_K1_CO        =       " + getIdLocal()     + "  " ;


      PreparedStatement selectStatement = null;

      try {

        // Obtiene una conexion a la base de datos, requiere Try
        connection = jdbcAccess.getConnection();

        // Prepara la sentencia de Busqueda
        selectStatement = connection.prepareStatement(selectStr);

        // Obtiene el resultset
        ResultSet rs = selectStatement.executeQuery();

        // Variable de fachada de los datos
        FachadaColaboraLogisticaBean fachadaBean;

        while (rs.next()) {

              fachadaBean = new FachadaColaboraLogisticaBean();

              fachadaBean.setOrdenCompra(rs.getString("ordenCompra"));
              fachadaBean.setIdTipoPedido(rs.getString("idTipoPed"));
              fachadaBean.setIdLocal(rs.getInt("idLocal"));
              fachadaBean.setIdCliente(rs.getDouble("idCliente"));
              fachadaBean.setIdPedido(rs.getInt("idPedido"));
              fachadaBean.setTipoDcto(rs.getString("tipoDcto"));
              fachadaBean.setIdDcto(rs.getInt("idDcto"));
              fachadaBean.setFechaPedido(rs.getString("fechaPedido").substring(0,10));

              //
              contenedor.add(fachadaBean);

        }

        // Cierra el Resultset
        rs.close();

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
        jdbcAccess.cleanup(connection, selectStatement,null);
        return contenedor;

      }
    }

    // Metodo consultaLogistica MsAccess
    public Vector consultaLogistica() {

      //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
      Class tipoObjeto       = this.getClass();
      String nombreClase     = tipoObjeto.getName();
      Vector contenedor      = new Vector();

      Connection connection  = null;

      //
      String selectStr =
      "SELECT CMENCAPED.CMPED_OC_NRO              " +
      "                        AS ordenCompra,    " +
      "       CMENCAPED.CMPED_K1_TIPO_PED         " +
      "                        AS idTipoPed,      " +
      "       CMENCAPED.CMPED_K1_CO               " +
      "                        AS idLocal,        " +
      "       VAL(CMENCAPED.CMPED_K3_TERC)        " +
      "                        AS idCliente,      " +
      "       CMENCAPED.CMPED_K1_NRO_PED          " +
      "                        AS idPedido,       " +
      "       ctrPcDetalle.TIPODCTO               " +
      "                        AS tipoDcto,       " +
      "       ctrPcDetalle.IDDCTO                 " +
      "                        AS idDcto,         " +
      "       ctrPc.PLACAVEHICULO                 " +
      "                        AS placaVehiculo,  " +
      "       ctrPc.FECHARECEPCION                " +
      "                        AS fechaPedido     " +
      "FROM (CMENCAPED INNER JOIN ctrPcDetalle    " +
      "ON (VAL(CMENCAPED.CMPED_K3_TERC)   =       " +
      "                       ctrPcDetalle.NITCC) " +
      "AND (CMENCAPED.CMPED_K1_CO         =       " +
      "                     ctrPcDetalle.IDPEDCO) " +
      "AND (CMENCAPED.CMPED_K1_NRO_PED    =       " +
      "                   ctrPcDetalle.IDPEDIDO)) " +
      "INNER JOIN ctrPc                           " +
      "ON (ctrPcDetalle.IDPCCO            =       " +
      "                             ctrPc.IDPCCO) " +
      "AND (ctrPcDetalle.IDPC             =       " +
      "                               ctrPc.IDPC) " +
      "WHERE CMENCAPED.CMPED_OC_NRO       =      '" + getOrdenCompra() + "' " +
      "AND   CMENCAPED.CMPED_K1_TIPO_PED  = 'PB'  " +
      "AND   CMENCAPED.CMPED_K1_CO        =       " + getIdLocal()     + "  " ;

      PreparedStatement selectStatement = null;

      try {

        // Obtiene una conexion a la base de datos, requiere Try
        connection = jdbcAccess.getConnection();

        // Prepara la sentencia de Busqueda
        selectStatement = connection.prepareStatement(selectStr);

        // Obtiene el resultset
        ResultSet rs = selectStatement.executeQuery();

        // Variable de fachada de los datos
        FachadaColaboraLogisticaBean fachadaBean;

        while (rs.next()) {

              fachadaBean = new FachadaColaboraLogisticaBean();

              fachadaBean.setOrdenCompra(rs.getString("ordenCompra"));
              fachadaBean.setIdTipoPedido(rs.getString("idTipoPed"));
              fachadaBean.setIdLocal(rs.getInt("idLocal"));
              fachadaBean.setIdCliente(rs.getDouble("idCliente"));
              fachadaBean.setIdPedido(rs.getInt("idPedido"));
              fachadaBean.setTipoDcto(rs.getString("tipoDcto"));
              fachadaBean.setIdDcto(rs.getInt("idDcto"));
              fachadaBean.setPlacaVehiculo(rs.getString("placaVehiculo"));
              fachadaBean.setFechaPedido(rs.getString("fechaPedido").substring(0,10));

              //
              contenedor.add(fachadaBean);

        }

        // Cierra el Resultset
        rs.close();

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
        jdbcAccess.cleanup(connection, selectStatement,null);
        return contenedor;

      }
    } */
}