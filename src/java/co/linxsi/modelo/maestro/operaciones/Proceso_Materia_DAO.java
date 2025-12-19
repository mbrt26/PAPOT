/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.linxsi.modelo.maestro.operaciones;

import static co.linxsi.modelo.maestro.operaciones.Proceso_Paro_DAO.DATA_SOURCE_NAME;
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
public class Proceso_Materia_DAO extends Proceso_Materia_DTO {

    public Proceso_Materia_DAO() {
        jdbcAccess = new JDBCAccess(DATA_SOURCE_NAME);
        int i = 0;
        cp[i++] = "sk_operacion_materia";
        cp[i++] = "nombre_materia";
        cp[i++] = "costo_mat_prima";
        cp[i++] = "sk_estado";
        tabla = "tblOperacionMateria";
    }

    @Override
    public boolean insert() {
        int i = 0;

        String sql = "INSERT INTO " + tabla + "(" + cp[i++] + "," + cp[i++] + "," + cp[i++] + "," + cp[i++] + ") "
                + " VALUES( " + Maximo() + ",'" + getNombre() + "'," + getCostoMateriaPrima() + "," + getSk_estado() + ");";
        return ejecutarSentencia(sql);
    }

    public List<Proceso_Materia_DTO> listAllM() {
        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();
        List<Proceso_Materia_DTO> lista = new ArrayList();
        Connection c = null;
        String sql
                = "SELECT [sk_operacion_materia]\n"
                + "      ,[sk_estado]\n"
                + "      ,[costo_mat_prima]\n"
                + "      ,[nombre_materia]\n"
                + "  FROM [dbo].[tblOperacionMateria] where sk_estado = 1";
        PreparedStatement ps = null;
        try {
            c = this.jdbcAccess.getConnection();
            ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Proceso_Materia_DTO dto = new Proceso_Materia_DTO();
                dto.setSk_operacion_materia(rs.getInt("sk_operacion_materia"));
                dto.setNombrePlu(rs.getString("nombre_materia"));
                dto.setCostoMateriaPrima(rs.getDouble("costo_mat_prima"));
                lista.add(dto);
            }
            rs.close();
            return lista;
        } catch (NamingException ne) {
            System.out.println("NamingException in " + nombreClase + " " + ne);
            return lista;
        } catch (SQLException sqle) {
            mostrarExcepcion(sqle);
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
    public List<Proceso_Materia_DTO> listAllMateriasOrdered() {
        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();
        List<Proceso_Materia_DTO> lista = new ArrayList();
        Connection c = null;
        String sql
                = "SELECT [sk_operacion_materia]\n"
                + "      ,[sk_estado]\n"
                + "      ,[costo_mat_prima]\n"
                + "      ,[nombre_materia]\n"
                + "  FROM [dbo].[tblOperacionMateria] where sk_estado = 1 ORDER BY nombre_materia ";
        PreparedStatement ps = null;
        try {
            c = this.jdbcAccess.getConnection();
            ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Proceso_Materia_DTO dto = new Proceso_Materia_DTO();
                dto.setSk_operacion_materia(rs.getInt("sk_operacion_materia"));
                dto.setNombrePlu(rs.getString("nombre_materia"));
                dto.setCostoMateriaPrima(rs.getDouble("costo_mat_prima"));
                lista.add(dto);
            }
            rs.close();
            return lista;
        } catch (NamingException ne) {
            System.out.println("NamingException in " + nombreClase + " " + ne);
            return lista;
        } catch (SQLException sqle) {
            mostrarExcepcion(sqle);
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

    public void eliminarMateria() {
        String sql = "UPDATE tblOperacionMateria SET sk_estado = 2 WHERE sk_operacion_materia=" + getSk_operacion_materia();
        ejecutarSentencia(sql);
    }

    public Proceso_Materia_DTO listaUno() {
        //Permita obtener solo un registro pasando como parametro 
        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();
        Connection c = null;
        Proceso_Materia_DTO bdto = null;
        String sql
                = "SELECT [nombre_materia]\n"
                + "      ,[sk_estado]\n"
                + "      ,sk_operacion_materia\n"
                + "      ,[costo_mat_prima]\n"
                + "      ,[nombre_materia]\n"
                + "  FROM [dbo].[tblOperacionMateria] WHERE sk_operacion_materia = " + getSk_operacion() + ";";
        PreparedStatement ps = null;
        try {
            c = this.jdbcAccess.getConnection();
            ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                bdto = new Proceso_Materia_DTO();
                bdto.setSk_plu(rs.getInt("sk_operacion_materia"));
                bdto.setNombre(rs.getString("nombre_materia"));
                bdto.setCostoMateriaPrima(rs.getDouble("costo_mat_prima"));

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

    public boolean Actualiza() {
        //Permite actualizar los registros de la bodegas
        Class tipoObjeto = getClass();
        String sql = "UPDATE tblOperacionMateria SET nombre_materia = '"
                + getNombre() + "',costo_mat_prima = "
                + getCostoMateriaPrima()
                + " WHERE sk_operacion_materia = "
                + getSk_operacion() + "";
        return ejecutarSentencia(sql);
    }

}
