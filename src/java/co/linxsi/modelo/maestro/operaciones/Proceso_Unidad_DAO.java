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
public class Proceso_Unidad_DAO extends Proceso_Unidad_DTO {

    public Proceso_Unidad_DAO() {
        jdbcAccess = new JDBCAccess(DATA_SOURCE_NAME);
        int i = 0;
        cp[i++] = "sk_operacion_unidad";
        cp[i++] = "sk_operacion";
        cp[i++] = "sk_unidad";
        cp[i++] = "sk_estado";
        cp[i++] = "sk_opera_unidad_concate";
        tabla = "tblOperacionUnidad";
    }

    @Override
    public boolean insert() {
        int i = 0;
        String sk_concate = String.valueOf(getSk_operacion()) + String.valueOf(getSk_unidad());
        String sql = "INSERT INTO " + tabla + "(" + cp[i++] + "," + cp[i++] + "," + cp[i++] + "," + cp[i++] + "," + cp[i++] + ") " + " VALUES( " + Maximo() + "," + getSk_operacion() + "," + getSk_unidad() + "," + getSk_estado() + "," + sk_concate + ");";
        return ejecutarSentencia(sql);
    }

    public List<Proceso_Unidad_DTO> listAll() {
        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();
        List<Proceso_Unidad_DTO> lista = new ArrayList();
        Connection c = null;
        String sql = "SELECT * FROM tblOperacionUnidad "
                + "                    INNER JOIN tblOperaciones "
                + "                    ON tblOperacionUnidad.sk_operacion=tblOperaciones.sk_operacion "
                + "                    INNER JOIN tblUnidades "
                + "                    ON tblOperacionUnidad.sk_unidad=tblUnidades.sk_unidad;";
        PreparedStatement ps = null;
        try {
            c = this.jdbcAccess.getConnection();
            ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Proceso_Unidad_DTO dto = new Proceso_Unidad_DTO();
                dto.setSk_operacion_unidad(rs.getInt("sk_operacion_unidad"));
                dto.setSk_operacion(rs.getInt("sk_operacion"));
                dto.setNombre_operacion(rs.getString("nombre_operacion"));
                dto.setSk_unidad(rs.getInt("sk_unidad"));
                dto.setNombre_unidad(rs.getString("nombre_unidad"));
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

    public void eliminarUnidad() {
        String sql = "DELETE FROM tblOperacionUnidad WHERE sk_operacion_unidad = " + getSk_operacion_unidad() + ";";
        ejecutarSentencia(sql);
    }
}
