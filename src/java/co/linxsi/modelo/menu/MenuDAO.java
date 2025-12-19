package co.linxsi.modelo.menu;


import com.solucionesweb.lasayudas.JDBCAccess;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import javax.naming.NamingException;

public class MenuDAO extends MenuDTO {

    // Nombre JNDI del Data Source que esta clase requiere
    private static final String DATA_SOURCE_NAME = "CommerceAccess";
    // Clase helper usada para acceder a la base de datos
    private JDBCAccess jdbcAccess;

    public MenuDAO() {
         jdbcAccess = new JDBCAccess(DATA_SOURCE_NAME);
    }

    public ArrayList listaOpcionPerfil() {
        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();
        ArrayList contenedor = new ArrayList();
        Connection connection = null;

        String selectStr = "SELECT\top.idOpcion,         \n\t\top.nombreOpcion,\n\t\top.rutaOpcion,     \n\t\top.idTipoOpcion,\n\t\top.icono\nFROM opciones_menu op\nLEFT JOIN opciones_menu_perfil p\nON op.idOpcion = p.idOpcion                 \nWHERE p.idPerfil =  "
                + getIdPerfil() + "\nAND p.estado = 1\nAND op.idTipoOpcion = 1\nORDER BY idOpcion ";

        PreparedStatement selectStatement = null;
        try {
            connection = this.jdbcAccess.getConnection();

            selectStatement = connection.prepareStatement(selectStr);

            ResultSet rs = selectStatement.executeQuery();

            while (rs.next()) {
                MenuDTO fachadaBean = new MenuDTO();

                fachadaBean.setIdOpcionPadre(rs.getInt("idOpcion"));
                fachadaBean.setNombreOpcion(rs.getString("nombreOpcion"));
                fachadaBean.setRutaOpcion(rs.getString("rutaOpcion"));
                fachadaBean.setIdTipoOpcion(rs.getInt("idTipoOpcion"));
                fachadaBean.setIcono(rs.getString("icono"));

                contenedor.add(fachadaBean);
            }

            rs.close();
            return contenedor;
        } catch (NamingException ne) {
            System.out.println("NamingException in " + nombreClase + " " + ne);
            return contenedor;
        } catch (SQLException sqle) {
            System.out.println("SQLException in" + nombreClase);

            String sqlMessage = sqle.getMessage();
            String sqlState = sqle.getSQLState();
            int vendorCode = sqle.getErrorCode();
            System.out.println("Ocurrio una  excepcion");
            System.out.println("Mensaje " + sqlMessage);
            System.out.println("SQL State " + sqlState);
            System.out.println("Vendor Code " + vendorCode);
            ArrayList localArrayList1 = contenedor;
            return contenedor;
        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
            return contenedor;
        } finally {
            this.jdbcAccess.cleanup(connection, selectStatement, null);
            return contenedor;
        }
    }

    public Vector listaUnNivelPerfil(int xIdPerfil) {
        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();

        String xRutaOpcionPrefijo = "GralControladorServlet?nombrePaginaRequest=/";

        Vector contenedor = new Vector();
        Connection connection = null;

        String selectStr = "SELECT\top.idOpcion,         \n\t\top.nombreOpcion,\n\t\top.rutaOpcion,     \n\t\top.idTipoOpcion,\n\t\tidOpcionPadre\nFROM opciones_menu op\nLEFT JOIN opciones_menu_perfil p\nON op.idOpcion = p.idOpcion                 \nWHERE p.idPerfil =  " + xIdPerfil + "\nAND p.estado = 1\nAND op.idTipoOpcion = "
                + getIdTipoOpcion() + "\nAND op.idOpcionPadre = "
                + getIdOpcion() + "\nORDER BY idOpcion  ";

        PreparedStatement selectStatement = null;
        try {
            connection = this.jdbcAccess.getConnection();

            selectStatement = connection.prepareStatement(selectStr);

            ResultSet rs = selectStatement.executeQuery();

            while (rs.next()) {
                MenuDTO fachadaBean = new MenuDTO();

                fachadaBean.setIdOpcion(rs.getInt("idOpcion"));
                fachadaBean.setNombreOpcion(rs.getString("nombreOpcion"));
                fachadaBean.setRutaOpcion(xRutaOpcionPrefijo + rs.getString("rutaOpcion"));
                fachadaBean.setIdOpcionPadre(rs.getInt("idOpcionPadre"));

                contenedor.add(fachadaBean);
            }

            rs.close();
            return contenedor;
        } catch (NamingException ne) {
            System.out.println("NamingException in " + nombreClase + " " + ne);
            return contenedor;
        } catch (SQLException sqle) {
            System.out.println("SQLException in" + nombreClase);

            String sqlMessage = sqle.getMessage();
            String sqlState = sqle.getSQLState();
            int vendorCode = sqle.getErrorCode();
            System.out.println("Ocurrio una  excepcion");
            System.out.println("Mensaje " + sqlMessage);
            System.out.println("SQL State " + sqlState);
            System.out.println("Vendor Code " + vendorCode);
            Vector localVector1 = contenedor;
            return contenedor;
        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
            return contenedor;
        } finally {
            this.jdbcAccess.cleanup(connection, selectStatement, null);
            return contenedor;
        }
    }
}
