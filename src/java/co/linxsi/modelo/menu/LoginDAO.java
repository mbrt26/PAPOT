package co.linxsi.modelo.menu;

import com.solucionesweb.lasayudas.JDBCAccess;
import com.solucionesweb.losbeans.utilidades.IConstantes;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;

public class LoginDAO extends LoginDTO
        implements Serializable, IConstantes {

    private double idUsuario;
    private String clave;
    private boolean vigente;
    // Nombre JNDI del Data Source que esta clase requiere
    private static final String DATA_SOURCE_NAME = "CommerceAccess";
    // Clase helper usada para acceder a la base de datos
    private JDBCAccess jdbcAccess;

    public LoginDAO() {
         jdbcAccess = new JDBCAccess(DATA_SOURCE_NAME);
    }

    public LoginDAO(double idUsuario, String clave) {
        this.idUsuario = idUsuario;
        this.clave = clave;
        this.vigente = false;

        jdbcAccess = new JDBCAccess(DATA_SOURCE_NAME);
    }

    public LoginDTO validaUsuario() {
        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();
        Connection connection = null;

        LoginDTO fachadaBean = new LoginDTO();

        String insertStr = "SELECT idUsuario           \n      "
                + ",nombreUsuario     \n      "
                + ",clave             \n      "
                + ",idNivel             \n      "
                + ",direccion         \n      "
                + ",telefono          \n      "
                + ",email             \n      "
                + ",estado            \n      "
                + ",fechaCambioClave     \n  "
                + "FROM ctrlUsuarios      \n"
                + "WHERE  idUsuario = "+ getIdUsuario() + " \n";
        PreparedStatement sentenciaSQL = null;
        try {
            connection = this.jdbcAccess.getConnection();

            sentenciaSQL = connection.prepareStatement(insertStr);

            ResultSet rs = sentenciaSQL.executeQuery();

            if (rs.next()) {
                fachadaBean.setIdUsuario(rs.getDouble("idUsuario"));
                fachadaBean.setClave(rs.getString("clave"));
                fachadaBean.setNombreUsuario(rs.getString("nombreUsuario"));
                fachadaBean.setEmail(rs.getString("email"));
                fachadaBean.setEstado(rs.getInt("estado"));
                fachadaBean.setIdNivel(rs.getInt("idNivel"));
                fachadaBean.setFechaCambioClave(rs.getString("fechaCambioClave"));
                fachadaBean.setDireccion(rs.getString("direccion"));
                fachadaBean.setTelefono(rs.getString("telefono"));
            }

            rs.close();
            return fachadaBean;
        } catch (NamingException ne) {
            System.out.println("NamingException in " + nombreClase + " " + ne);
            return fachadaBean;
        } catch (SQLException sqle) {
            System.out.println("SQLException in" + nombreClase);
            LoginDTO localLoginDTO1 = fachadaBean;
            return fachadaBean;
        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
            return fachadaBean;
        } finally {
            this.jdbcAccess.cleanup(connection, sentenciaSQL, null);
            return fachadaBean;
        }
    }
}
