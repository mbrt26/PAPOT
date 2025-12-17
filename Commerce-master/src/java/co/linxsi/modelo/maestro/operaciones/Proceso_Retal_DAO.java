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
import javax.naming.NamingException;

/**
 *
 * @author Desarrodor
 */
public class Proceso_Retal_DAO extends Proceso_Retal_DTO {

    public JDBCAccess jdbcAccess;

    public Proceso_Retal_DAO() {
        jdbcAccess = new JDBCAccess(DATA_SOURCE_NAME);
        int i = 0;
        cp[i++] = "sk_operacion_retal";
        cp[i++] = "sk_operacion";
        cp[i++] = "sk_retal";
        cp[i++] = "sk_estado";
        cp[i++] = "sk_opera_retal_concate";
        tabla = "tblOperacionRetal";
    }

    @Override
    public boolean insert() {
        int i = 0;
        String sk_concate = String.valueOf(getSk_operacion()) + String.valueOf(getSk_retal());

        String sql = "INSERT INTO " + tabla + "(" + cp[i++] + "," + cp[i++] + "," + cp[i++] + "," + cp[i++] + "," + cp[i++] + ") " + " VALUES( " + Maximo() + "," + getSk_operacion() + "," + getSk_retal() + "," + getSk_estado() + "," + sk_concate + ");";
        return ejecutarSentencia(sql);
    }

    public List<Proceso_Retal_DTO> listaAll_() {
        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();
        List<Proceso_Retal_DTO> lista = new ArrayList();
        Connection c = null;
        String sql = "SELECT * FROM tblOperacionRetal "
                + "INNER JOIN tblOperaciones "
                + "ON tblOperacionRetal.sk_operacion=tblOperaciones.sk_operacion "
                + "INNER JOIN tblRetales "
                + "ON tblOperacionRetal.sk_retal=tblRetales.sk_retal;";
        PreparedStatement ps = null;
        try {
            c = this.jdbcAccess.getConnection();
            ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Proceso_Retal_DTO dto = new Proceso_Retal_DTO();
                dto.setSk_operacion_retal(rs.getInt("sk_operacion_retal"));
                dto.setSk_operacion(rs.getInt("sk_operacion"));
                dto.setNombre_operacion(rs.getString("nombre_operacion"));
                dto.setSk_retal(rs.getInt("sk_retal"));
                dto.setNombre_retal(rs.getString("nombre_retal"));
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

    public void eliminarRetal() {
        String sql = "DELETE FROM tblOperacionRetal WHERE sk_operacion_retal=" + getSk_operacion_retal();
        ejecutarSentencia(sql);
    }
    

}
