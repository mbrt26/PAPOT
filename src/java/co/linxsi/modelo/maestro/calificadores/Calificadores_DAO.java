package co.linxsi.modelo.maestro.calificadores;
import com.solucionesweb.lasayudas.JDBCAccess;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

public class Calificadores_DAO extends Calificadores_DTO {

    private final JDBCAccess jdbcAccess;
    private static final String DATA_SOURCE_NAME = "CommerceAccess";

    public Calificadores_DAO() {
        jdbcAccess = new JDBCAccess(DATA_SOURCE_NAME);
    }

    public boolean ingresa() {
        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "INSERT INTO tipo_bodega (sk_tipo_bodega,nombre_tipo_bodega,pondera_costo,sk_estado) "
                +    " VALUES (" + getSk_calificador() + ",'" + getNombre() + "'," + getSk_pondera() + "," + getSk_estado() + ")";
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

    public boolean Actualiza() {
        //Permite actualizar los registros de la bodegas
        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql
                = "UPDATE tipo_bodega SET "
                + "nombre_tipo_bodega= '" + getNombre()+ "'"
                + ",sk_estado = " + getSk_estado()
                + ",pondera_costo = " + getSk_pondera()
                + " WHERE sk_tipo_bodega = "
                + getSk_calificador() + ";";
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

    public boolean Elimina() {
        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;
        Connection connection = null;
        PreparedStatement statement = null;
        String sql = "DELETE FROM bodega WHERE sk_bodega = " + getSk_calificador() + "";
        try {
            connection = this.jdbcAccess.getConnection();
            statement = connection.prepareStatement(sql);
            statement.execute();
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
            this.jdbcAccess.cleanup(connection, statement, null);
            return okIngresar;
        }
    }

    public List<Calificadores_DTO> listaAll() {
        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();
        List<Calificadores_DTO> lista = new ArrayList();
        Connection c = null;
        String sql = "SELECT * FROM tipo_bodega; ";
        PreparedStatement ps = null;
        try {
            c = this.jdbcAccess.getConnection();
            ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Calificadores_DTO bdto = new Calificadores_DTO();
                bdto.setSk_calificador(rs.getInt("sk_tipo_bodega"));
                bdto.setNombre(rs.getString("nombre_tipo_bodega"));
                bdto.setSk_pondera(rs.getInt("pondera_costo"));
                bdto.setSk_estado(rs.getInt("sk_estado"));
                lista.add(bdto);
            }
            rs.close();
            return lista;
        } catch (NamingException ne) {
            System.out.println("NamingException in " + nombreClase + " " + ne);
            return lista;
        } catch (SQLException sqle) {
            System.out.println("SQLException in" + nombreClase);
            String sqlMessage = sqle.getMessage();
            String sqlState = sqle.getSQLState();
            int vendorCode = sqle.getErrorCode();
            System.out.println("Ocurrio una  excejdbcAccession");
            System.out.println("Mensaje " + sqlMessage);
            System.out.println("SQL State " + sqlState);
            System.out.println("Vendor Code " + vendorCode);
            List localList1 = lista;
            return lista;
        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
            return lista;
        } finally {
            this.jdbcAccess.cleanup(c, ps, null);
            return lista;
        }
    }

//    public List<Calificadores_DTO> listaTiposBodegas() {
//        Class tipoObjeto = getClass();
//        String nombreClase = tipoObjeto.getName();
//        List<Calificadores_DTO> lista = new ArrayList();
//        Connection c = null;
//        String sql = "SELECT * FROM tipo_bodega";
//        PreparedStatement ps = null;
//        try {
//            c = this.jdbcAccess.getConnection();
//            ps = c.prepareStatement(sql);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                Calificadores_DTO bdto = new Calificadores_DTO();
//                bdto.setSk_(rs.getInt("sk_tipo_bodega"));
//                bdto.setNombreTipoBodega(rs.getString("nombre_tipo_bodega"));
//                lista.add(bdto);
//                              }
//            rs.close();
//            return lista;
//        } catch (NamingException ne) {
//            System.out.println("NamingException in " + nombreClase + " " + ne);
//            return lista;
//        } catch (SQLException sqle) {
//            System.out.println("SQLException in" + nombreClase);
//            String sqlMessage = sqle.getMessage();
//            String sqlState = sqle.getSQLState();
//            int vendorCode = sqle.getErrorCode();
//            System.out.println("Ocurrio una  excejdbcAccession");
//            System.out.println("Mensaje " + sqlMessage);
//            System.out.println("SQL State " + sqlState);
//            System.out.println("Vendor Code " + vendorCode);
//            List localList1 = lista;
//            return lista;
//        } catch (Exception e) {
//            System.out.println("Exception In :" + nombreClase + " " + e);
//            return lista;
//        } finally {
//            this.jdbcAccess.cleanup(c, ps, null);
//            return lista;
//        }
//    }
    public Calificadores_DTO listaUno() {
        //Permita obtener solo un registro pasando como parametro 
        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();
        Connection c = null;
        Calificadores_DTO bdto = null;
        String sql
                = "SELECT * FROM tipo_bodega WHERE sk_tipo_bodega = " + getSk_calificador() + ";";
        PreparedStatement ps = null;
        try {
            c = this.jdbcAccess.getConnection();
            ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                bdto = new Calificadores_DTO();
                bdto.setSk_calificador(rs.getInt("sk_tipo_bodega"));
                bdto.setNombre(rs.getString("nombre_tipo_bodega"));
                bdto.setSk_pondera(rs.getInt("pondera_costo"));
                bdto.setSk_estado(rs.getInt("sk_estado"));

            }
            rs.close();
            return bdto;
        } catch (NamingException ne) {
            System.out.println("NamingException in " + nombreClase + " " + ne);
            return bdto;
        } catch (SQLException sqle) {
            System.out.println("SQLException in" + nombreClase);
            String sqlMessage = sqle.getMessage();
            String sqlState = sqle.getSQLState();
            int vendorCode = sqle.getErrorCode();
            System.out.println("Ocurrio una  excejdbcAccession");
            System.out.println("Mensaje " + sqlMessage);
            System.out.println("SQL State " + sqlState);
            System.out.println("Vendor Code " + vendorCode);
            Calificadores_DTO localBodegaDTO1 = bdto;
            return bdto;
        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
            return bdto;
        } finally {
            this.jdbcAccess.cleanup(c, ps, null);
            return bdto;
        }
    }

    public int Maximo() {
        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();
        int maximo = 0;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "SELECT MAX(sk_tipo_bodega) sk_tipo_bodega  FROM  tipo_bodega;";
        try {
            c = this.jdbcAccess.getConnection();
            ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                maximo = rs.getInt("sk_tipo_bodega");
            }
            rs.close();
            return maximo;
        } catch (NamingException ne) {
            System.out.println("NamingException in " + nombreClase + " " + ne);
            return maximo;
        } catch (SQLException sqle) {
            System.out.println("SQLException in" + nombreClase);
            String sqlMessage = sqle.getMessage();
            String sqlState = sqle.getSQLState();
            int vendorCode = sqle.getErrorCode();
            System.out.println("Ocurrio una  excejdbcAccession");
            System.out.println("Mensaje " + sqlMessage);
            System.out.println("SQL State " + sqlState);
            System.out.println("Vendor Code " + vendorCode);
            int i = maximo;
            return maximo;
        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
            return maximo;
        } finally {
            this.jdbcAccess.cleanup(c, ps, null);
            return maximo;
        }
    }
}
