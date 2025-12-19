/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.linxsi.modelo.maestro.paro_maquina;

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
public class Paro_Maquina_DAO extends Paro_Maquina_DTO {

    private final JDBCAccess jdbcAccess;
    private static final String DATA_SOURCE_NAME = "CommerceAccess";

    public Paro_Maquina_DAO() {
        jdbcAccess = new JDBCAccess(DATA_SOURCE_NAME);
    }

    public boolean ingresa() {
        String sql = "INSERT INTO tblParoMaquina(sk_paro_maquina,nombre_paro_maquina,sk_estado)"
                + " VALUES(" + getSk_paro_maquina() + ",'" + getNombre_paro_maquina() + "'," + getSk_estado() + " )";
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
                = "UPDATE tblParoMaquina SET "
                + "nombre_paro_maquina = '" + getNombre_paro_maquina() + "'"
                + ",sk_estado = " + getSk_estado()
                + " WHERE sk_paro_maquina = "
                + getSk_paro_maquina() + ";";
        return ejecutarSentencia(sql);
    }

    public boolean Elimina() {
        String sql = "DELETE FROM tblParoMaquina WHERE sk_paro_maquina = " + getSk_paro_maquina() + "";
        return ejecutarSentencia(sql);
    }

    public List<Paro_Maquina_DTO> listaAll() {
        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();
        List<Paro_Maquina_DTO> lista = new ArrayList();
        Connection c = null;
        String sql = "SELECT * FROM tblParoMaquina;";
        PreparedStatement ps = null;
        try {
            c = this.jdbcAccess.getConnection();
            ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Paro_Maquina_DTO bdto = new Paro_Maquina_DTO();
                bdto.setSk_paro_maquina(rs.getInt("sk_paro_maquina"));
                bdto.setNombre_paro_maquina(rs.getString("nombre_paro_maquina"));
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

    public List<Paro_Maquina_DTO> listaAllO() {
        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();
        List<Paro_Maquina_DTO> lista = new ArrayList();
        Connection c = null;
        String sql = "SELECT * FROM tblParoMaquina ORDER BY nombre_paro_maquina;";
        PreparedStatement ps = null;
        try {
            c = this.jdbcAccess.getConnection();
            ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Paro_Maquina_DTO bdto = new Paro_Maquina_DTO();
                bdto.setSk_paro_maquina(rs.getInt("sk_paro_maquina"));
                bdto.setNombre_paro_maquina(rs.getString("nombre_paro_maquina"));
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

    public List<Paro_Maquina_DTO> listParoByProcess() {
        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();
        List<Paro_Maquina_DTO> lista = new ArrayList();
        Connection c = null;
        String sql
                = "SELECT * FROM tblParoMaquina "
                + "INNER JOIN tblOperacionParo ON  tblParoMaquina.sk_paro_maquina =tblOperacionParo.sk_paro_maquina \n"
                + "INNER JOIN tblOperaciones ON tblOperaciones.sk_operacion=tblOperacionParo.sk_operacion "
                + "WHERE tblOperaciones.sk_operacion =" + getSk_operacion() + " ORDER BY nombre_paro_maquina;";
        PreparedStatement ps = null;
        try {
            c = this.jdbcAccess.getConnection();
            ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Paro_Maquina_DTO bdto = new Paro_Maquina_DTO();
                bdto.setSk_paro_maquina(rs.getInt("sk_paro_maquina"));
                bdto.setNombre_paro_maquina(rs.getString("nombre_paro_maquina"));
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

    public List<Paro_Maquina_DTO> listaTiposBodegas() {
        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();
        List<Paro_Maquina_DTO> lista = new ArrayList();
        Connection c = null;
        String sql = "SELECT * FROM tipo_bodega";
        PreparedStatement ps = null;
        try {
            c = this.jdbcAccess.getConnection();
            ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Paro_Maquina_DTO bdto = new Paro_Maquina_DTO();
                bdto.setSk_paro_maquina(rs.getInt("sk_tipo_bodega"));
                bdto.setNombre_paro_maquina(rs.getString("nombre_tipo_bodega"));
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

    public Paro_Maquina_DTO listaUno() {
        //Permita obtener solo un registro pasando como parametro 
        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();
        Connection c = null;
        Paro_Maquina_DTO bdto = null;
        String sql = "SELECT * FROM tblParoMaquina WHERE sk_paro_maquina = " + getSk_paro_maquina() + ";";
        PreparedStatement ps = null;
        try {
            c = this.jdbcAccess.getConnection();
            ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                bdto = new Paro_Maquina_DTO();
                bdto.setSk_paro_maquina(rs.getInt("sk_paro_maquina"));
                bdto.setNombre_paro_maquina(rs.getString("nombre_paro_maquina"));
            }
            rs.close();
            return bdto;
        } catch (NamingException ne) {
            System.out.println("NamingException in " + nombreClase + " " + ne);
            return bdto;
        } catch (SQLException sqle) {
            reportarExcepcion(sqle, nombreClase);
            Paro_Maquina_DTO localParo_Maquina_DTO1 = bdto;
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
        String sql = "SELECT MAX(sk_paro_maquina) sk_paro_maquina FROM tblParoMaquina ;";
        try {
            c = this.jdbcAccess.getConnection();
            ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                maximo = rs.getInt("sk_paro_maquina");
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

    private void reportarExcepcion(SQLException sqle, String nombreClase) {
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
