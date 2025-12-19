package co.linxsi.modelo.menu;


import com.solucionesweb.lasayudas.JDBCAccess;
import com.solucionesweb.losbeans.utilidades.IConstantes;
import java.io.PrintStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.naming.NamingException;

public class PerfilDAO extends PerfilDTO
        implements Serializable, IConstantes  {

   // Nombre JNDI del Data Source que esta clase requiere
    private static final String DATA_SOURCE_NAME = "CommerceAccess";
    // Clase helper usada para acceder a la base de datos
    private JDBCAccess jdbcAccess;

    public PerfilDAO() {
         jdbcAccess = new JDBCAccess(DATA_SOURCE_NAME);
    }

    public Vector listaPerfilOpcionAll() {
        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();
        Vector contenedor = new Vector();
        Connection connection = null;

        String selectStr = "SELECT COMOpcionesPerfil.idOpcion          ,COMOpcionesPerfil.idPerfil      FROM COMOpcionesPerfil             WHERE COMOpcionesPerfil.idOpcion =   "
                + getIdOpcion();

        PreparedStatement selectStatement = null;
        try {
            connection = this.jdbcAccess.getConnection();

            selectStatement = connection.prepareStatement(selectStr);

            ResultSet rs = selectStatement.executeQuery();

            while (rs.next()) {
                PerfilDTO fachadaBean = new PerfilDTO();

                fachadaBean.setIdOpcion(rs.getInt("idOpcion"));
                fachadaBean.setIdPerfil(rs.getInt("idPerfil"));

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

    public boolean ingresa() {
        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();

        boolean validaOk = false;
        Connection connection = null;

        String updateStr = "INSERT INTO COMOpcionesPerfil           (COMOpcionesPerfil.idOpcion           ,COMOpcionesPerfil.idPerfil)     VALUES         ("
                + getIdOpcion() + ","
                + getIdPerfil() + ")";
        PreparedStatement selectStatement = null;
        try {
            connection = this.jdbcAccess.getConnection();

            selectStatement = connection.prepareStatement(updateStr);

            selectStatement.executeUpdate();

            validaOk = true;

            this.jdbcAccess.cleanup(connection, selectStatement, null);
            return validaOk;
        } catch (NamingException ne) {
            System.out.println("NamingException in " + nombreClase + " " + ne);
            return validaOk;
        } catch (SQLException sqle) {
            System.out.println("SQLException in" + nombreClase);

            String sqlMessage = sqle.getMessage();
            String sqlState = sqle.getSQLState();
            int vendorCode = sqle.getErrorCode();
            System.out.println("Ocurrio una  excepcion");
            System.out.println("Mensaje " + sqlMessage);
            System.out.println("SQL State " + sqlState);
            System.out.println("Vendor Code " + vendorCode);
            boolean bool1 = validaOk;
            return validaOk;
        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
            return validaOk;
        } finally {
            return validaOk;
        }
    }

    public boolean elimina() {
        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();

        boolean validaOk = false;
        Connection connection = null;

        String updateStr = "DELETE FROM COMOpcionesPerfil       WHERE COMOpcionesPerfil.idPerfil=   "
                + getIdPerfil();

        PreparedStatement selectStatement = null;
        try {
            connection = this.jdbcAccess.getConnection();

            selectStatement = connection.prepareStatement(updateStr);

            selectStatement.executeUpdate();

            validaOk = true;

            this.jdbcAccess.cleanup(connection, selectStatement, null);
            return validaOk;
        } catch (NamingException ne) {
            System.out.println("NamingException in " + nombreClase + " " + ne);
            return validaOk;
        } catch (SQLException sqle) {
            System.out.println("SQLException in" + nombreClase);

            String sqlMessage = sqle.getMessage();
            String sqlState = sqle.getSQLState();
            int vendorCode = sqle.getErrorCode();
            System.out.println("Ocurrio una  excepcion");
            System.out.println("Mensaje " + sqlMessage);
            System.out.println("SQL State " + sqlState);
            System.out.println("Vendor Code " + vendorCode);
            boolean bool1 = validaOk;
            return validaOk;
        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
            return validaOk;
        } finally {
            return validaOk;
        }
    }
}
