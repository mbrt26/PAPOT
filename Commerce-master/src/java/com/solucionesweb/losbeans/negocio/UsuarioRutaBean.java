package com.solucionesweb.losbeans.negocio;

// Importa la clase helper JDBCAccess que contiene los accesos a la base de datos
import com.solucionesweb.lasayudas.JDBCAccess;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaUsuarioBean;

// Importa los paquetes de java
import java.sql.*;
import java.io.*;
import java.io.IOException.*;

// Importa los paquetes de javax
import java.util.Vector;
import javax.naming.*;

//
import org.kxml.*;
import org.kxml.parser.*;

public class UsuarioRutaBean extends FachadaUsuarioBean
        implements Serializable {

    // Nombre JNDI del Data Source que esta clase requiere
    private static final String DATA_SOURCE_NAME = "CommerceAccess";

    // Clase helper usada para acceder a la base de datos
    private JDBCAccess jdbcAccess;

    // Metodo constructor por defecto sin parametros
    public UsuarioRutaBean() {

        // Al instanciar el Bean establece la conexion a la base de datos
        jdbcAccess = new JDBCAccess(DATA_SOURCE_NAME);
    }

    // listaRutaUsuario
    public String listaRutaUsuario() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        String strRutaUsuario = "";
        Connection connection = null;


        String selectStr =
                "SELECT ctrlUsuariosRutas.idUsuario,        " +
                "       ctrlUsuariosRutas.idRuta,           " +
                "       ctrlUsuariosRutas.estado,           " +
                "       ctrlUsuariosRutas.indicadorInicial, " +
                "       ctrlUsuariosRutas.indicadorFinal    " +
                "FROM   ctrlUsuariosRutas                   " +
                "WHERE  ctrlUsuariosRutas.idUsuario = ( ? ) ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // inicializa los valores de los parametros
            selectStatement.setDouble(1, getIdUsuario());

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaUsuarioBean fachadaUsuarioBean;

            while (rs.next()) {

                fachadaUsuarioBean = new FachadaUsuarioBean();

                //
                fachadaUsuarioBean.setIdUsuario(rs.getString("idUsuario"));
                fachadaUsuarioBean.setIdRuta(rs.getString("idRuta"));
                fachadaUsuarioBean.setEstado(rs.getInt("estado"));

                strRutaUsuario += "'" + fachadaUsuarioBean.getIdRuta() + "',";

            }

            //
            strRutaUsuario += "'0'";

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
            return strRutaUsuario;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {

            // Cierra la conexion a la base de datos limpiando la statement usada y la
            jdbcAccess.cleanup(connection, selectStatement, null);
            return strRutaUsuario;
        }
    }

    // listaFCH
    public FachadaUsuarioBean listaFCH() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        //
        FachadaUsuarioBean fachadaUsuarioRutaBean = new FachadaUsuarioBean();
        Connection connection = null;


        String selectStr =
                "SELECT ctrlUsuariosRutas.idUsuario,        " +
                "       ctrlUsuariosRutas.idRuta,           " +
                "       ctrlUsuariosRutas.estado,           " +
                "       ctrlUsuariosRutas.indicadorInicial, " +
                "       ctrlUsuariosRutas.indicadorFinal    " +
                "FROM   ctrlUsuariosRutas                   " +
                "WHERE  ctrlUsuariosRutas.idUsuario = ( ? ) ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // inicializa los valores de los parametros
            selectStatement.setDouble(1, getIdUsuario());

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            if (rs.next()) {

                fachadaUsuarioRutaBean.setIdUsuario(rs.getString("idUsuario"));
                fachadaUsuarioRutaBean.setIdRuta(rs.getString("idRuta"));
                fachadaUsuarioRutaBean.setEstado(rs.getInt("estado"));
                fachadaUsuarioRutaBean.setIndicadorInicial(
                        rs.getInt("indicadorInicial"));
                fachadaUsuarioRutaBean.setIndicadorFinal(
                        rs.getInt("indicadorFinal"));

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
            return fachadaUsuarioRutaBean;
        }
    }

    // validaUsuarioRuta
    public boolean validaUsuarioRuta() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        boolean validaUsuarioRuta = false;
        Connection connection = null;


        String selectStr =
                "SELECT ctrlUsuariosRutas.idUsuario,        " +
                "       ctrlUsuariosRutas.idRuta,           " +
                "       ctrlUsuariosRutas.estado,           " +
                "       ctrlUsuariosRutas.indicadorInicial, " +
                "       ctrlUsuariosRutas.indicadorFinal    " +
                "FROM   ctrlUsuariosRutas                   " +
                "WHERE  ctrlUsuariosRutas.idUsuario = ( ? ) ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // inicializa los valores de los parametros
            selectStatement.setDouble(1, getIdUsuario());

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            if (rs.next()) {

                //
                validaUsuarioRuta = true;

            }

            //
            //strRutaUsuario += "~";

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
            return validaUsuarioRuta;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {

            jdbcAccess.cleanup(connection, selectStatement, null);
            return validaUsuarioRuta;
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
                "INSERT INTO ctrlUsuariosRutas " +
                "           (idUsuario         " +
                "           ,idRuta            " +
                "           ,estado            " +
                "           ,idSeq             " +
                "           ,indicadorInicial  " +
                "           ,indicadorFinal)   " +
                "     VALUES                 ( " +
                getIdUsuario() + ",'" +
                getIdRuta() + "'," +
                getEstado() + "," +
                getIdSeq() + "," +
                getIndicadorInicial() + "," +
                getIndicadorFinal() + ")";
 
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

    // listaIdSeqMaxima
    public int listaIdSeqMaxima() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        int intMaxIdSeq = 0;

        Connection connection = null;

        String selectStr =
                "SELECT MAX(ctrlUsuariosRutas.idSeq) " +
                "                   AS maxIdSeq      " +
                "FROM ctrlUsuariosRutas              ";

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

    //
    public Vector listaUsuarioRutaTx() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        Vector contenedor = new Vector();
        Connection connection = null;

        String selectStr =
                "SELECT ctrlUsuariosRutas.idUsuario         " +
                "      ,ctrlUsuariosRutas.idRuta            " +
                "      ,ctrlUsuariosRutas.estado            " +
                "      ,ctrlUsuariosRutas.idSeq             " +
                "      ,ctrlUsuariosRutas.indicadorInicial  " +
                "      ,ctrlUsuariosRutas.indicadorFinal    " +
                "FROM ctrlUsuariosRutas                     " +
                "WHERE ctrlUsuariosRutas.idSeq >            " +
                getIdSeq() + "                              " +
                "ORDER BY ctrlUsuariosRutas.idSeq";

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
                fachadaBean.setIdRuta(rs.getString("idRuta"));
                fachadaBean.setEstado(rs.getInt("estado"));
                fachadaBean.setIdSeq(rs.getInt("idSeq"));
                fachadaBean.setIndicadorInicial(rs.getInt("indicadorInicial"));
                fachadaBean.setIndicadorFinal(rs.getInt("indicadorFinal"));

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

    // actualiza
    public boolean actualiza() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        boolean validaOk = false;
        Connection connection = null;

        //
        String updateStr =
                "UPDATE ctrlUsuariosRutas               " +
                "SET   ctrlUsuariosRutas.idRuta       ='" +
                getIdRuta() + "',                       " +
                "    ctrlUsuariosRutas.estado         = " +
                getEstado() + ",                        " +
                "    ctrlUsuariosRutas.idSeq          = " +
                getIdSeq() + ",                         " +
                "  ctrlUsuariosRutas.indicadorInicial = " +
                getIndicadorInicial() + ",              " +
                "  ctrlUsuariosRutas.indicadorFinal   = " +
                getIndicadorFinal() + "                 " +
                "WHERE ctrlUsuariosRutas.idUsuario    = " +
                getIdUsuario();

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


    // existeUsuarioRuta
    public boolean existeUsuarioRuta() {

      //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
      Class tipoObjeto       = this.getClass();
      String nombreClase     = tipoObjeto.getName();
      boolean existeLinea    = false;

      Connection connection  = null;

      String selectStr =
           "SELECT ctrlUsuariosRutas.*          " +
           "FROM ctrlUsuariosRutas              " +
           "WHERE ctrlUsuariosRutas.idUsuario = " +
           getIdUsuario();

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

	  }

	  catch (Exception e)
	  {
		System.out.println("Exception In :" + nombreClase + " " + e);
	  }
      finally {
        // Cierra la conexion a la base de datos limpiando la statement usada y la
            jdbcAccess.cleanup(connection, selectStatement, null);
       return existeLinea;
      }
    }

// traverse_UsuarioRutaTx
public void traverse_UsuarioRutaTx( XmlParser parser,
                                  String indent ) throws Exception {

    //
	boolean leave           = false;

    //
    String idUsuario        = new String();
    String idRuta           = new String();
    String estado           = new String();
    String idSeq            = new String();
    String indicadorInicial = new String();
    String indicadorFinal   = new String();

	do {
	   ParseEvent event = parser.read();
	   ParseEvent pe;

	switch ( event.getType() ) {

	 // For example, <CxC>
	 case Xml.START_TAG:

	 // see API doc of StartTag for more access methods
	 // Pick up idagenda for display
	  if ("idUsuario".equals(event.getName()))
	  {
			pe          = parser.read();
			idUsuario   = pe.getText();
	  }

	  // Pick up clave for display
	  if ("idRuta".equals(event.getName()))
	  {
	  		pe           = parser.read();
	  		idRuta= pe.getText();
	  }

	  // Pick up clave for display
	  if ("estado".equals(event.getName()))
	  {
	  		pe          = parser.read();
	  		estado   = pe.getText();
	  }

	  // Pick up clave for display
	  if ("idSeq".equals(event.getName()))
	  {
	  		pe          = parser.read();
	  		idSeq = pe.getText();
	  }

	  // Pick up clave for display
	  if ("indicadorInicial".equals(event.getName()))
	  {
	  		pe            = parser.read();
	  		indicadorInicial = pe.getText();
	  }


	  // Pick up clave for display
	  if ("indicadorFinal".equals(event.getName()))
	  {
	  		pe          = parser.read();
	  		indicadorFinal = pe.getText();

   	        // ingresa
   	        setIdUsuario(idUsuario);
   	        setIdRuta(idRuta);
   	        setEstado(estado);
   	        setIdSeq(idSeq);
   	        setIndicadorInicial(indicadorInicial);
   	        setIndicadorFinal(indicadorFinal);

            //
   	        if ( this.existeUsuarioRuta() ) {
   	             System.out.println(" actualiza usuario ruta");

                 //
                 this.actualiza();
   	        } else {
   	             System.out.println(" ingresa usuario");

                 //
    	         this.ingresa();
   	        }

	  }

	  traverse_UsuarioRutaTx( parser, "" ) ; // recursion call for each <tag></tag>
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
	  } while( !leave );
  }
}