/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.linxsi.modelo.maestro.accesorios;

import co.linxsi.modelo.maestro.operaciones.*;
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

/**
 *
 * @author Desarrollador
 */
public class Accesorios_DAO extends Accesorios_DTO {

    public final JDBCAccess jdbcAccess;
    private static final String DATA_SOURCE_NAME = "CommerceAccess";

    public Accesorios_DAO() {
        jdbcAccess = new JDBCAccess(DATA_SOURCE_NAME);
    }

    public boolean ingresa() {
        String sql = "";
        return ejecutarSentencia(sql);
    }

    public boolean Actualiza() {
        //Permite actualizar los registros de la bodegas
        Class tipoObjeto = getClass();
        String sql = "";
        return ejecutarSentencia(sql);
    }

    public boolean Elimina() {
        String sql = "DELETE FROM bodega WHERE sk_bodega = " + getSk_accesorio()+ "";
        return ejecutarSentencia(sql);
    }





    public List<Accesorios_DTO> listaAllOrdered() {
        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();
        List<Accesorios_DTO> lista = new ArrayList();
        Connection c = null;
        String sql = "SELECT * FROM tblAccesorios ORDER BY nombre ;";
        PreparedStatement ps = null;
        try {
            c = this.jdbcAccess.getConnection();
            ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Accesorios_DTO bdto = new Accesorios_DTO();

                bdto.setSk_accesorio(rs.getInt("id"));
                bdto.setNombre(rs.getString("nombre"));
                bdto.setPrecio(rs.getDouble("precio"));

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

    public Accesorios_DTO listaUno() {
        //Permita obtener solo un registro pasando como parametro 
        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();
        Connection c = null;
        Accesorios_DTO bdto = null;
        String sql
                = "";
        PreparedStatement ps = null;
        try {
            c = this.jdbcAccess.getConnection();
            ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                bdto = new Accesorios_DTO();
              
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
            Accesorios_DTO localOperacions_DTO1 = bdto;
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
        String sql = "SELECT MAX(sk_operacion) sk_operacion FROM tblOperaciones ;";
        try {
            c = this.jdbcAccess.getConnection();
            ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                maximo = rs.getInt("sk_operacion");
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

    public boolean ejecutarSentencia(String st) {
        Connection c = null;
        PreparedStatement ps = null;
        try {
            c = this.jdbcAccess.getConnection();
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
            this.jdbcAccess.cleanup(c, ps, null);
        }
        return true;
    }

    public void mostrarExcepcionSQL(SQLException ex) {
        Logger.getLogger(Accesorios_DAO.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(Accesorios_DAO.class.getName()).log(Level.SEVERE, null, ex);
    }

    public static String upperCaseFirst(String val) {
        char[] arr = val.toCharArray();
        arr[0] = Character.toUpperCase(arr[0]);
        return new String(arr);
    }

    public List<Accesorios_DTO> listAll() {
             Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();
        List<Accesorios_DTO> lista = new ArrayList();
        Connection c = null;
        String sql = "SELECT * FROM tblAccesorios where estado = 1 ORDER BY id ;";
        PreparedStatement ps = null;
        try {
            c = this.jdbcAccess.getConnection();
            ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Accesorios_DTO bdto = new Accesorios_DTO();

                bdto.setSk_accesorio(rs.getInt("id"));
                bdto.setNombre(rs.getString("nombre"));
                bdto.setPrecio(rs.getDouble("precio"));

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

    public boolean insert() {
       String sql = "";
        return ejecutarSentencia(sql);
    }
}
