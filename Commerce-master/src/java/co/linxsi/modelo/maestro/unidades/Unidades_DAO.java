/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.linxsi.modelo.maestro.unidades;

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
public class Unidades_DAO extends Unidades_DTO {

    private final JDBCAccess jdbcAccess;
    private static final String DATA_SOURCE_NAME = "CommerceAccess";
    private String tabla = "tblUnidades";

    public Unidades_DAO() {
        jdbcAccess = new JDBCAccess(DATA_SOURCE_NAME);
    }

    public boolean ingresa() {
        String sql = "INSERT INTO " + tabla + " (sk_unidad,nombre_unidad,sk_estado)"
                + " VALUES(" + getSk_unidad() + ",'" + getNombre_unidades() + "'," + getSk_estado() + " )";
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
                = "UPDATE " + tabla + " SET "
                + " nombre_unidad = '" + getNombre_unidades() + "'"
                + ", sk_estado = " + getSk_estado()
                + " WHERE sk_unidad = "
                + getSk_unidad() + ";";
        return ejecutarSentencia(sql);
    }

    public boolean Elimina() {
        String sql = " DELETE FROM " + tabla + " WHERE sk_unidad = " + getSk_unidad() + "";
        return ejecutarSentencia(sql);
    }

    public List<Unidades_DTO> listaAll() {
        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();
        List<Unidades_DTO> lista = new ArrayList();
        Connection c = null;
        String sql = "SELECT * FROM " + tabla + ";";
        PreparedStatement ps = null;
        try {
            c = this.jdbcAccess.getConnection();
            ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Unidades_DTO bdto = new Unidades_DTO();
                bdto.setSk_unidad(rs.getInt("sk_unidad"));
                bdto.setNombre_unidades(rs.getString("nombre_unidad"));
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

    public List<Unidades_DTO> listaAllO() {
        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();
        List<Unidades_DTO> lista = new ArrayList();
        Connection c = null;
        String sql = "SELECT * FROM " + tabla + " ORDER BY nombre_unidad;";
        PreparedStatement ps = null;
        try {
            c = this.jdbcAccess.getConnection();
            ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Unidades_DTO bdto = new Unidades_DTO();
                bdto.setSk_unidad(rs.getInt("sk_unidad"));
                bdto.setNombre_unidades(rs.getString("nombre_unidad"));
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

    public Unidades_DAO listaUno() {
        //Permita obtener solo un registro pasando como parametro 
        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();
        Connection c = null;
        Unidades_DAO bdto = null;
        String sql = "SELECT * FROM " + tabla + " WHERE sk_unidad = " + getSk_unidad() + ";";
        PreparedStatement ps = null;
        try {
            c = this.jdbcAccess.getConnection();
            ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                bdto = new Unidades_DAO();
                bdto.setSk_unidad(rs.getInt("sk_unidad"));
                bdto.setNombre_unidades(rs.getString("nombre_unidad"));
            }
            rs.close();
            return bdto;
        } catch (NamingException ne) {
            System.out.println("NamingException in " + nombreClase + " " + ne);
            return bdto;
        } catch (SQLException sqle) {
            reportarExcepcion(sqle, nombreClase);
            Unidades_DAO localRetales_DAO1 = bdto;
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
        String sql = "SELECT MAX(sk_unidad) sk_unidad FROM " + tabla + ";";
        try {
            c = this.jdbcAccess.getConnection();
            ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                maximo = rs.getInt("sk_unidad");
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

    public ArrayList<Unidades_DTO> listadoUnidadesDian() {
        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();
        ArrayList<Unidades_DTO> lista = new ArrayList();
        String sql = "SELECT * FROM tblUnidadesEmbalaje WHERE estado = 1 ORDER BY nombreembalaje DESC;";
        try {
            Connection c = this.jdbcAccess.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Unidades_DAO bdto = new Unidades_DAO();
                bdto.setSk_unidad(rs.getInt("id"));
                bdto.setNombre_unidades(rs.getString("nombreEmbalaje"));
                bdto.setSimbolo_unidad(rs.getString("codigoDian"));
                bdto.setSk_estado(rs.getInt("estado"));
                lista.add(bdto);
            }
            rs.close();
            this.jdbcAccess.cleanup(c, ps, null);
        } catch (NamingException ne) {
            System.out.println("NamingException in " + nombreClase + " " + ne);

        } catch (SQLException sqle) {
            reportarExcepcion(sqle, nombreClase);

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);

        } finally {

            return lista;

        }

    }
       public Unidades_DAO listaUnoPedido(String idOrden) {
        //Permita obtener solo un registro pasando como parametro 
        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();
        Connection c = null;
        Unidades_DAO bdto = null;
        String sql = "SELECT TOP (1) idTipoEmbalaje  FROM [BDCommerce].[dbo].[tblDctosOrdenesDetalle] "
                + "WHERE IDTIPOORDEN = 59 and IDORDEN = "+idOrden+" ;";
        PreparedStatement ps = null;
        try {
            c = this.jdbcAccess.getConnection();
            ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                bdto = new Unidades_DAO();
                bdto.setSk_unidad(rs.getInt("idTipoEmbalaje"));
                        }
            rs.close();
            return bdto;
        } catch (NamingException ne) {
            System.out.println("NamingException in " + nombreClase + " " + ne);
            return bdto;
        } catch (SQLException sqle) {
            reportarExcepcion(sqle, nombreClase);
            Unidades_DAO localRetales_DAO1 = bdto;
            return bdto;
        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
            return bdto;
        } finally {
            this.jdbcAccess.cleanup(c, ps, null);
            return bdto;
        }
    }
}
