/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.linxsi.modelo.maestro.operaciones;

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
public class Operaciones_DAO extends Operaciones_DTO {

    public final JDBCAccess jdbcAccess;
    private static final String DATA_SOURCE_NAME = "CommerceAccess";

    public Operaciones_DAO() {
        jdbcAccess = new JDBCAccess(DATA_SOURCE_NAME);
    }

    public boolean ingresa() {
        String sql = "INSERT INTO tblOperaciones (idLocal,sk_operacion,nombre_operacion,costo_servicios,costo_arriendo,costo_mano_obra,sk_estado,control_tiempo,costo_retal)"
                + " VALUES(" + getSk_local() + "," + getSk_operacion() + ",'" + getNombre() + "'," + getCostoServicios() + "," + getCostoArriendo() + "," + getCostoManoObra() + "," + getSk_estado() + "," + getConteo() + "," + getCosto_retal() + ")";
        return ejecutarSentencia(sql);
    }

    public boolean Actualiza() {
        //Permite actualizar los registros de la bodegas
        Class tipoObjeto = getClass();
        String sql = "UPDATE tblOperaciones SET nombre_operacion = '"
                + getNombre() + "',costo_servicios = "
                + getCostoServicios() + ",costo_arriendo = "
                + getCostoArriendo() + ",costo_mano_obra = "
                + getCostoManoObra() + ",sk_estado = "
                + getSk_estado() + ",control_tiempo = "
                + getConteo() + ",costo_retal ="
                + getCosto_retal()
                + " WHERE sk_operacion = "
                + getSk_operacion() + "";
        return ejecutarSentencia(sql);
    }

    public boolean Elimina() {
        String sql = "DELETE FROM bodega WHERE sk_bodega = " + getSk_operacion() + "";
        return ejecutarSentencia(sql);
    }

    public List<Operaciones_DTO> listaProcesosPlanta() {
        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();
        List<Operaciones_DTO> lista = new ArrayList();
        Connection c = null;
        String sql = "SELECT * FROM tblOperaciones WHERE sk_operacion BETWEEN 2 AND 6;";
        PreparedStatement ps = null;
        try {
            c = this.jdbcAccess.getConnection();
            ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Operaciones_DTO bdto = new Operaciones_DTO();
                bdto.setSk_local(rs.getInt("idLocal"));
                bdto.setSk_operacion(rs.getInt("sk_operacion"));
                bdto.setNombre(rs.getString("nombre_operacion"));
                bdto.setCostoServicios(rs.getDouble("costo_servicios"));
                bdto.setCostoArriendo(rs.getDouble("costo_arriendo"));
                bdto.setCostoManoObra(rs.getDouble("costo_mano_obra"));
                bdto.setConteo(rs.getInt("control_tiempo"));
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

    public List<Operaciones_DTO> listaAll() {
        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();
        List<Operaciones_DTO> lista = new ArrayList();
        Connection c = null;
        final String sql = "SELECT * FROM tblOperaciones;";
        PreparedStatement ps = null;
        try {
            c = this.jdbcAccess.getConnection();
            ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Operaciones_DTO bdto = new Operaciones_DTO();
                bdto.setSk_local(rs.getInt("idLocal"));
                bdto.setSk_operacion(rs.getInt("sk_operacion"));
                bdto.setNombre(rs.getString("nombre_operacion"));
                bdto.setCostoServicios(rs.getDouble("costo_servicios"));
                bdto.setCostoArriendo(rs.getDouble("costo_arriendo"));
                bdto.setCostoManoObra(rs.getDouble("costo_mano_obra"));
                bdto.setConteo(rs.getInt("control_tiempo"));
                bdto.setCosto_retal(rs.getDouble("costo_retal"));
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

    public List<Operaciones_DTO> listaAllEdicion() {
        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();
        List<Operaciones_DTO> lista = new ArrayList();
        Connection c = null;
        String sql = "SELECT * FROM tblOperaciones "
                + "WHERE sk_operacion IN (2,3,4,5,6,9);";
        PreparedStatement ps = null;
        try {
            c = this.jdbcAccess.getConnection();
            ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Operaciones_DTO bdto = new Operaciones_DTO();
                bdto.setSk_local(rs.getInt("idLocal"));
                bdto.setSk_operacion(rs.getInt("sk_operacion"));
                bdto.setNombre(rs.getString("nombre_operacion"));
                bdto.setCostoServicios(rs.getDouble("costo_servicios"));
                bdto.setCostoArriendo(rs.getDouble("costo_arriendo"));
                bdto.setCostoManoObra(rs.getDouble("costo_mano_obra"));
                bdto.setConteo(rs.getInt("control_tiempo"));
                bdto.setCosto_retal(rs.getDouble("costo_retal"));
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

    public List<Operaciones_DTO> listaAllOrdered() {
        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();
        List<Operaciones_DTO> lista = new ArrayList();
        Connection c = null;
        String sql = "SELECT * FROM tblOperaciones WHERE sk_operacion BETWEEN 2 AND 5 OR sk_operacion >=1000  ORDER BY nombre_operacion ;";
        PreparedStatement ps = null;
        try {
            c = this.jdbcAccess.getConnection();
            ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Operaciones_DTO bdto = new Operaciones_DTO();
                bdto.setSk_local(rs.getInt("idLocal"));
                bdto.setSk_operacion(rs.getInt("sk_operacion"));
                bdto.setNombre(rs.getString("nombre_operacion"));
                bdto.setCostoServicios(rs.getDouble("costo_servicios"));
                bdto.setCostoArriendo(rs.getDouble("costo_arriendo"));
                bdto.setCostoManoObra(rs.getDouble("costo_mano_obra"));
                bdto.setConteo(rs.getInt("control_tiempo"));
                bdto.setCosto_retal(rs.getDouble("costo_retal"));
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

    public Operaciones_DTO listaUno() {
        //Permita obtener solo un registro pasando como parametro 
        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();
        Connection c = null;
        Operaciones_DTO bdto = null;
        String sql
                = "SELECT * FROM tblOperaciones WHERE sk_operacion = " + getSk_operacion() + ";";
        PreparedStatement ps = null;
        try {
            c = this.jdbcAccess.getConnection();
            ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                bdto = new Operaciones_DTO();
                bdto.setSk_local(rs.getInt("idLocal"));
                bdto.setSk_operacion(rs.getInt("sk_operacion"));
                bdto.setNombre(rs.getString("nombre_operacion"));
                bdto.setCostoServicios(rs.getDouble("costo_servicios"));
                bdto.setCostoArriendo(rs.getDouble("costo_arriendo"));
                bdto.setCostoManoObra(rs.getDouble("costo_mano_obra"));
                bdto.setSk_estado(rs.getInt("sk_estado"));
                bdto.setConteo(rs.getInt("control_tiempo"));
                bdto.setCosto_retal(rs.getDouble("costo_retal"));
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
            Operaciones_DTO localOperacions_DTO1 = bdto;
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
        Logger.getLogger(Operaciones_DAO.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(Operaciones_DAO.class.getName()).log(Level.SEVERE, null, ex);
    }

    public static String upperCaseFirst(String val) {
        char[] arr = val.toCharArray();
        arr[0] = Character.toUpperCase(arr[0]);
        return new String(arr);
    }
}
