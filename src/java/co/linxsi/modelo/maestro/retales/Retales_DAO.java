/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.linxsi.modelo.maestro.retales;


import com.solucionesweb.lasayudas.JDBCAccess;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author Desarrollador
 */
public class Retales_DAO extends Retales_DTO{

    public final JDBCAccess jdbcAccess;
    private static final String DATA_SOURCE_NAME = "CommerceAccess";

    public Retales_DAO() {
        jdbcAccess = new JDBCAccess(DATA_SOURCE_NAME);
    }

    public boolean ingresa() {
        String sql = "INSERT INTO tblRetales (sk_retal,nombre_retal,sk_estado)"
                + " VALUES(" + getSk_retal() + ",'" + getNombre_retal()+ "'," + getSk_estado() + " )";
        return ejecutarSentencia(sql);
    }

    public boolean ejecutarSentencia(String sql) {
        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;
        Connection c = null;
        PreparedStatement ps = null;
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
            reportarExcepcion(sqle, nombreClase);
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
        String sql
                = "UPDATE tblRetales SET "
                + "nombre_retal = '" + getNombre_retal()+ "'"
                + ",sk_estado = " + getSk_estado()
                + " WHERE sk_retal = "
                + getSk_retal() + ";";
        return ejecutarSentencia(sql);
    }

    public boolean Elimina() {
        String sql = "DELETE FROM tblParoMaquina WHERE sk_retal = " + getSk_retal()+ "";
        return ejecutarSentencia(sql);
    }

   
    public List<Retales_DTO> listaAll() {
        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();
        List<Retales_DTO> lista = new ArrayList();
        Connection c = null;
        String sql = "SELECT * FROM tblRetales;";
        PreparedStatement ps = null;
        try {
            c = this.jdbcAccess.getConnection();
            ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Retales_DTO bdto = new Retales_DTO();
                bdto.setSk_retal(rs.getInt("sk_retal"));
                bdto.setNombre_retal(rs.getString("nombre_retal"));
                lista.add(bdto);
            }
            rs.close();
            return lista;
        } catch (NamingException ne) {
            System.out.println("NamingException in " + nombreClase + " " + ne);
            return lista;
        } catch (SQLException sqle) {
            reportarExcepcion(sqle, nombreClase);
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
   public List<Retales_DTO> listaAllO() {
        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();
        List<Retales_DTO> lista = new ArrayList();
        Connection c = null;
        String sql = "SELECT * FROM tblRetales ORDER BY nombre_retal;";
        PreparedStatement ps = null;
        try {
            c = this.jdbcAccess.getConnection();
            ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Retales_DTO bdto = new Retales_DTO();
                bdto.setSk_retal(rs.getInt("sk_retal"));
                bdto.setNombre_retal(rs.getString("nombre_retal"));
                lista.add(bdto);
            }
            rs.close();
            return lista;
        } catch (NamingException ne) {
            System.out.println("NamingException in " + nombreClase + " " + ne);
            return lista;
        } catch (SQLException sqle) {
            reportarExcepcion(sqle, nombreClase);
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

    public List<Retales_DAO> listaTiposBodegas() {
        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();
        List<Retales_DAO> lista = new ArrayList();
        Connection c = null;
        String sql = "SELECT * FROM tipo_bodega";
        PreparedStatement ps = null;
        try {
            c = this.jdbcAccess.getConnection();
            ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Retales_DAO bdto = new Retales_DAO();
                bdto.setSk_retal(rs.getInt("sk_tipo_bodega"));
                bdto.setNombre_retal(rs.getString("nombre_tipo_bodega"));
                lista.add(bdto);
            }
            rs.close();
            return lista;
        } catch (NamingException ne) {
            System.out.println("NamingException in " + nombreClase + " " + ne);
            return lista;
        } catch (SQLException sqle) {

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
            return lista;
        } finally {
            this.jdbcAccess.cleanup(c, ps, null);
            return lista;
        }
    }

    public Retales_DAO listaUno() {
        //Permita obtener solo un registro pasando como parametro 
        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();
        Connection c = null;
        Retales_DAO bdto = null;
        String sql = "SELECT * FROM tblRetales WHERE sk_retal = " + getSk_retal() + ";";
        PreparedStatement ps = null;
        try {
            c = this.jdbcAccess.getConnection();
            ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                bdto = new Retales_DAO();
                bdto.setSk_retal(rs.getInt("sk_retal"));
                bdto.setNombre_retal(rs.getString("nombre_retal"));
                          }
            rs.close();
            return bdto;
        } catch (NamingException ne) {
            System.out.println("NamingException in " + nombreClase + " " + ne);
            return bdto;
        } catch (SQLException sqle) {
            reportarExcepcion(sqle, nombreClase);
            Retales_DAO localRetales_DAO1 = bdto;
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
        String sql = "SELECT MAX(sk_retal) sk_retal FROM tblRetales ;";
        try {
            c = this.jdbcAccess.getConnection();
            ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                maximo = rs.getInt("sk_retal");
            }
            rs.close();
            return maximo;
        } catch (NamingException ne) {
            System.out.println("NamingException in " + nombreClase + " " + ne);
            return maximo;
        } catch (SQLException sqle) {
            reportarExcepcion(sqle, nombreClase);
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

    public void reportarExcepcion(SQLException sqle, String nombreClase) {
        System.out.println("SQLException in" + nombreClase);
        String sqlMessage = sqle.getMessage();
        String sqlState = sqle.getSQLState();
        int vendorCode = sqle.getErrorCode();
        System.out.println("Ocurrio una  excejdbcAccession");
        System.out.println("Mensaje " + sqlMessage);
        System.out.println("SQL State " + sqlState);
        System.out.println("Vendor Code " + vendorCode);
    }
}
