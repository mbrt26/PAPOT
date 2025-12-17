package co.linxsi.modelo.maestro.bodega;

import com.solucionesweb.lasayudas.JDBCAccess;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

public class BodegaDAO extends BodegaDTO {

    private final JDBCAccess jdbcAccess;
    private static final String DATA_SOURCE_NAME = "CommerceAccess";

    public BodegaDAO() {
        jdbcAccess = new JDBCAccess(DATA_SOURCE_NAME);
    }

    public boolean ingresa() {
        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;
//Permite ingresar las bodegas por primera vez en la tabla
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "INSERT INTO tblLocalesBodega(idLocal,idBodega,nombreBodega,estado,idSeq,codigoBodega,sk_tipo_bodega)"
                + " VALUES(" + getSk_local() + "," + getSk_bodega() + ",'" + getNombre() + "'," + getSk_estado() + ","
                + getActualiza() + ",'" + getNombre() + "',"+ getSk_tipo_bodega()+" )";
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
        String sql = 
                "UPDATE tblLocalesBodega SET "
                + "nombreBodega = '"+getNombre()+"'"
                + ",estado = " + getSk_estado() 
                +",sk_tipo_bodega = "+getSk_tipo_bodega()
                + ",codigoBodega = '"+getNombre()+"' WHERE idBodega = "
                + getSk_bodega() + ";";
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
        String sql = "DELETE FROM bodega WHERE sk_bodega = " + getSk_bodega() + "";
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

    public List<BodegaDTO> listaAll() {
        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();
        List<BodegaDTO> lista = new ArrayList();
        Connection c = null;
        String sql = "SELECT idLocal\n     "
                + ",idBodega\n             "
                + ",nombreBodega\n                "
                + ",estado\n             "
                + ",idseq\n            "
                + ",codigoBodega \n        "
                + ",tblLocalesBodega.sk_tipo_bodega \n        "
                + ",nombre_tipo_bodega \n        "
                + "FROM tblLocalesBodega INNER JOIN tipo_bodega ON tblLocalesBodega.sk_tipo_bodega=tipo_bodega.sk_tipo_bodega \n           ";
        PreparedStatement ps = null;
        try {
            c = this.jdbcAccess.getConnection();
            ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                BodegaDTO bdto = new BodegaDTO();
                bdto.setSk_local(rs.getInt("idLocal"));
                bdto.setSk_bodega(rs.getInt("idBodega"));
                bdto.setNombre(rs.getString("nombreBodega"));
                bdto.setSk_tipo_bodega(rs.getInt("sk_tipo_bodega"));
                bdto.setNombreTipoBodega(rs.getString("nombre_tipo_bodega"));
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

    public List<BodegaDTO> listaTiposBodegas() {
        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();
        List<BodegaDTO> lista = new ArrayList();
        Connection c = null;
        String sql = "SELECT * FROM tipo_bodega";
        PreparedStatement ps = null;
        try {
            c = this.jdbcAccess.getConnection();
            ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                BodegaDTO bdto = new BodegaDTO();
                bdto.setSk_tipo_bodega(rs.getInt("sk_tipo_bodega"));
                bdto.setNombreTipoBodega(rs.getString("nombre_tipo_bodega"));
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

    public BodegaDTO listaUno() {
        //Permita obtener solo un registro pasando como parametro 
        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();
        Connection c = null;
        BodegaDTO bdto = null;
        String sql
                = "SELECT * FROM tblLocalesBodega WHERE idBodega = " + getSk_bodega() + ";";
        PreparedStatement ps = null;
        try {
            c = this.jdbcAccess.getConnection();
            ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                bdto = new BodegaDTO();
                bdto.setSk_local(rs.getInt("idLocal"));
                bdto.setSk_bodega(rs.getInt("idBodega"));
                bdto.setNombre(rs.getString("nombreBodega"));
                bdto.setSk_estado(rs.getInt("estado"));
                bdto.setActualiza(rs.getInt("idSeq"));
                bdto.setSk_tipo_bodega(rs.getInt("sk_tipo_bodega"));
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
            BodegaDTO localBodegaDTO1 = bdto;
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
        String sql = "SELECT MAX(idBodega) idBodega FROM tblLocalesBodega ;";
        try {
            c = this.jdbcAccess.getConnection();
            ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                maximo = rs.getInt("idbodega");
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
