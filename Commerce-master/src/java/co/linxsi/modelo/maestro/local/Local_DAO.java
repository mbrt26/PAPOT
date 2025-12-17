package co.linxsi.modelo.maestro.local;

import com.solucionesweb.lasayudas.JDBCAccess;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

public class Local_DAO extends Local_DTO {
    
    private final JDBCAccess jdbcAccess;
    private static final String DATA_SOURCE_NAME = "CommerceAccess";
    
    public Local_DAO() {
        jdbcAccess = new JDBCAccess(DATA_SOURCE_NAME);
    }
    
    public boolean actualiza() {
        //Permite actualizar los registros de la bodegas
        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql
                = "UPDATE [BDCommerce].[dbo].[tblLocales] "
                + "SET nombreLocal = '" + getNombreLocal().trim() + "'"
                + ", razonSocial ='" + getRazonSocial().trim() + "'"
                + ", nit ='" + getNIT().trim() + "'"
                + ", email ='" + getEmail().trim() + "'"
                + ", direccion ='" + getDireccion().trim() + "'"
                + ", idCiudad ='" + getIdCiudad().trim() + "'"
                + ", telefono ='" + getTelefono().trim() + "'"
                + ", fax = '" + getFax().trim() + "'"
                + ", resolucion ='" + getResolucion().trim() + "'"
                + ", resolucion2 = '" + getResolucion2().trim() + "'"
                + ", idRes1 = '" + getResFiscal1() + "'"
                + ", idRes2 = '" + getResFiscal2() + "'"
                + ", idRes3 = '" + getResFiscal3() + "'"
                + ", idRes4 = '" + getResFiscal4() + "'"
                + ", idTipoOperacion = '" + getTipoOperacion() + "'"
                + ", idRegimen = '" + getIdRegimen() + "';";
        System.out.println(sql);
        try {
            c = this.jdbcAccess.getConnection();
            ps = c.prepareStatement(sql);
            ps.execute();
            okIngresar = true;
            return okIngresar;
        } catch (NamingException ne) {
            System.out.println("NamingException in " + nombreClase + " " + ne);
            return okIngresar;
        } catch (SQLException sqle) {
            System.out.println("SQLException in" + nombreClase);
            String sqlMessage = sqle.getMessage();
            String sqlState = sqle.getSQLState();
            int vendorCode = sqle.getErrorCode();
            System.out.println("Ocurrio una  excejdbcAccession");
            System.out.println("Mensaje " + sqlMessage);
            System.out.println("SQL State " + sqlState);
            System.out.println("Vendor Code " + vendorCode);
            return okIngresar;
        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
            return okIngresar;
        } finally {
            this.jdbcAccess.cleanup(c, ps, null);
            return okIngresar;
        }
    }
    
    public Local_DTO getLocal() {
        
        String sql = "SELECT TOP 1 * FROM [BDCommerce].[dbo].[tblLocales]";
        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();
        Local_DTO dto = new Local_DTO();
        Connection c = null;
        
        PreparedStatement ps = null;
        try {
            c = this.jdbcAccess.getConnection();
            ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                dto.setNombreLocal(rs.getString("nombreLocal"));
                dto.setRazonSocial(rs.getString("razonSocial"));
                dto.setNIT(rs.getString("nit"));
                dto.setDireccion(rs.getString("direccion"));
                dto.setTelefono(rs.getString("telefono"));
                dto.setFax(rs.getString("fax"));
                dto.setNombreRegimen("regimen");;
                dto.setResolucion(rs.getString("resolucion"));
                dto.setResolucion2(rs.getString("resolucion2"));
                dto.setEmail(rs.getString("email"));
                dto.setIdCiudad(rs.getString("idCiudad"));
                dto.setIdRegimen(Integer.parseInt(rs.getString("idRegimen")));
                dto.setTipoOperacion(rs.getString("idTipoOperacion"));
                dto.setResFiscal1(rs.getString("idRes1"));
                dto.setResFiscal2(rs.getString("idRes2"));
                dto.setResFiscal3(rs.getString("idRes3"));
                dto.setResFiscal4(rs.getString("idRes4"));
            }
            rs.close();
        } catch (NamingException ne) {
            System.out.println("NamingException in " + nombreClase + " " + ne);
            
            return dto;
        } catch (SQLException sqle) {
            System.out.println("SQLException in" + nombreClase);
            String sqlMessage = sqle.getMessage();
            String sqlState = sqle.getSQLState();
            int vendorCode = sqle.getErrorCode();
            System.out.println("Ocurrio una  excejdbcAccession");
            System.out.println("Mensaje " + sqlMessage);
            System.out.println("SQL State " + sqlState);
            System.out.println("Vendor Code " + vendorCode);
            
            return dto;
        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
            return dto;
        } finally {
            this.jdbcAccess.cleanup(c, ps, null);
            return dto;
        }
    }
    
    public List<Local_DTO> listaRegimen() {
        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        List<Local_DTO> listaLocal = new ArrayList();
        Connection connection = null;
        String selectStr
                = "SELECT tblRegimen.idRegimen,     "
                + "       tblRegimen.nombreRegimen,  "
                + "       tblRegimen.id  "
                + "FROM tblRegimen                  "
                + "ORDER BY tblRegimen.nombreRegimen";
        PreparedStatement selectStatement = null;
        try {
            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();
            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);
            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();
            // Variable de fachada de los datos
            Local_DTO fachadaBean;
            while (rs.next()) {
                fachadaBean = new Local_DTO();
                fachadaBean.setIdRegimen(rs.getInt("id"));
                fachadaBean.setNombreRegimen(rs.getString("nombreRegimen"));
                listaLocal.add(fachadaBean);
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
            return listaLocal;
        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {
            // Cierra la conexion a la base de datos limpiando la statement usada y la
            this.jdbcAccess.cleanup(connection, selectStatement, null);
            return listaLocal;
        }
    }
    
    public List<Local_DTO> listaCiudades() {
        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        List<Local_DTO> listaLocal = new ArrayList();
        Connection connection = null;
        String selectStr
                = "SELECT [idCiudad]\n"
                + "      ,[nombreCiudad]\n"
                + "      ,[nombreDpto]\n"
                + "      ,[estado]\n"
                + "      ,[idSeq]\n"
                + "  FROM [tblCiudades]  WHERE estado = 1"
                + "ORDER BY nombreCiudad;";
        PreparedStatement selectStatement = null;
        try {
            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();
            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);
            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();
            // Variable de fachada de los datos
            Local_DTO dto;
            while (rs.next()) {
                dto = new Local_DTO();
                dto.setIdCiudad(rs.getString("idCiudad"));
                dto.setCiudad(rs.getString("nombreCiudad"));
                dto.setDepartamento(rs.getString("nombreDpto"));
                listaLocal.add(dto);
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
            return listaLocal;
        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {
            // Cierra la conexion a la base de datos limpiando la statement usada y la
            this.jdbcAccess.cleanup(connection, selectStatement, null);
            return listaLocal;
        }
    }
    
}
