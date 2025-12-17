package co.linxsi.modelo.inventario.carga_inventario;

import co.linxsi.modelo.maestro.bodega.BodegaDTO;
import com.solucionesweb.lasayudas.JDBCAccess;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;

public class Carga_Inventario_DAO extends Carga_Inventario_DTO {

    private static final String DATA_SOURCE_NAME = "CommerceAccess";
    private JDBCAccess pc;

    public Carga_Inventario_DAO() {
        pc = new JDBCAccess(DATA_SOURCE_NAME);

    }

    public List<Carga_Inventario_DTO> listaAll() {

        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();
        List<Carga_Inventario_DTO> lista;
        lista = new ArrayList();
        String op = "=";
        if (getSk_bodega() == 0) {
            op = "<>";
        }
        Connection c = null;
        String sql = "SELECT *  FROM tblPlusInventario INNER JOIN tblPlus ON tblPlus.idPlu = tblPlusInventario.idPlu \n"
                + "INNER JOIN tblCategorias ON tblCategorias.idCategoria=tblPlus.idCategoria AND tblCategorias.idLinea=tblPlus.idLinea \n"
                + "INNER JOIN tblLocalesBodega ON tblLocalesBodega.idBodega=tblPlusInventario.idBodega\n"
                + "WHERE tblPlusInventario.idBodega " + op + " " + getSk_bodega() + " ORDER BY tblPlusInventario.idbodega;";

        PreparedStatement ps = null;
        try {
            c = this.pc.getConnection();
            ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Carga_Inventario_DTO bdto = new Carga_Inventario_DTO();
                bdto.setSk_bodega(rs.getInt("idbodega"));
                bdto.setNombreBodega(rs.getString("nombreBodega"));
                bdto.setSk_plu(rs.getInt("idplu"));
                bdto.setNombrePLU(rs.getString("nombreCategoria") + " " + rs.getString("nombrePLU"));
                bdto.setExistencia(rs.getDouble("existencia"));
                bdto.setVr_costo(rs.getDouble("vrCosto"));
                bdto.setMaterial(rs.getString("referencia"));
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
            System.out.println("Ocurrio una  excepcion");
            System.out.println("Mensaje " + sqlMessage);
            System.out.println("SQL State " + sqlState);
            System.out.println("Vendor Code " + vendorCode);
            List localList1 = lista;
            return lista;
        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
            return lista;
        } finally {
            this.pc.cleanup(c, ps, null);
            return lista;
        }
    }

    public BodegaDTO listaUno() {
        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();

        Connection c = null;
        BodegaDTO bdto = null;

        String sql = "SELECT sk_local\n      ,sk_bodega\n      ,nombre\n      ,sk_estado\n      ,actualiza \n      ,sk_inventario \nFROM bodega \n where sk_bodega = "
                + getSk_bodega() + "";
        PreparedStatement ps = null;
        try {
            c = this.pc.getConnection();

            ps = c.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                bdto = new BodegaDTO();

                bdto.setSk_local(rs.getInt("sk_local"));
                bdto.setSk_bodega(rs.getInt("sk_bodega"));
                bdto.setNombre(rs.getString("nombre"));
                bdto.setSk_estado(rs.getInt("sk_estado"));
                bdto.setActualiza(rs.getInt("actualiza"));
                bdto.setSk_inventario(rs.getInt("sk_inventario"));
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
            System.out.println("Ocurrio una  excepcion");
            System.out.println("Mensaje " + sqlMessage);
            System.out.println("SQL State " + sqlState);
            System.out.println("Vendor Code " + vendorCode);
            BodegaDTO localBodegaDTO1 = bdto;
            return bdto;
        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
            return bdto;
        } finally {
            this.pc.cleanup(c, ps, null);
            return bdto;
        }
    }

    public int Maximo() {
        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();
        int maximo = 0;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "SELECT MAX(sk_bodega) sk_bodega\nFROM bodega \n";
        try {
            c = this.pc.getConnection();

            ps = c.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                maximo = rs.getInt("sk_bodega");
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
            System.out.println("Ocurrio una  excepcion");
            System.out.println("Mensaje " + sqlMessage);
            System.out.println("SQL State " + sqlState);
            System.out.println("Vendor Code " + vendorCode);
            int i = maximo;
            return maximo;
        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
            return maximo;
        } finally {
            this.pc.cleanup(c, ps, null);
            return maximo;
        }
    }

    public void actualizarInventario() {
        String sql = "UPDATE inventario_producto SET existencia = " + getExistencia() + " WHERE sk_plu = " + getSk_plu() + " AND sk_bodega = " + getSk_bodega();
        ejecutarSentencia(sql);
    }

    public boolean ejecutarSentencia(String st) {
        Connection c = null;
        PreparedStatement ps = null;
        try {
            c = this.pc.getConnection();
            System.out.println(st);
            ps = (PreparedStatement) c.prepareStatement(st);
            ps.execute();
            ps.close();
            System.out.println(" Ejecutada sentencia con exito ");
        } catch (SQLException ex) {
            mostrarExcepcionSQL(ex);
            return false;
        } catch (NamingException ex) {
            mostrarExcepcion(ex);
        } finally {
            this.pc.cleanup(c, ps, null);
        }
        return true;
    }

    public void mostrarExcepcionSQL(SQLException ex) {
        Logger.getLogger(Carga_Inventario_DAO.class.getName()).log(Level.SEVERE, null, ex);
        System.out.println("SQLException in" + this.getClass().getName());
        String sqlMessage = ex.getMessage();
        String sqlState = ex.getSQLState();
        int vendorCode = ex.getErrorCode();
        System.out.println("Ocurrio una  excejdbcAccession");
        System.out.println("Mensaje " + sqlMessage);
        System.out.println("SQL State " + sqlState);
        System.out.println("Vendor Code " + vendorCode);
    }

    public void mostrarExcepcion(NamingException ex) {
        System.out.println("NamingException" + this.getClass().getName());
        Logger.getLogger(Carga_Inventario_DAO.class.getName()).log(Level.SEVERE, null, ex);
    }
}
