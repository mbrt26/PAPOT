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
public class Proceso_Paro_DAO extends Proceso_Paro_DTO {

    private final JDBCAccess jdbcAccess;
    static final String DATA_SOURCE_NAME = "CommerceAccess";
    public String[] cp = new String[5];
    public String tabla = "tblOperacionParo";

  
    public Proceso_Paro_DAO() {
        jdbcAccess = new JDBCAccess(DATA_SOURCE_NAME);
        int i = 0;
        cp[i++] = "sk_operacion_paro";
        cp[i++] = "sk_operacion";
        cp[i++] = "sk_paro_maquina";
        cp[i++] = "sk_estado";
        cp[i++] = "sk_opera_paro_concate";
    }

    public boolean insert() {
        int i = 0;
        String sk_concate=String.valueOf(getSk_operacion())+String.valueOf(getSk_paro_maquina());
        String sql = "INSERT INTO " + tabla + "(" + cp[i++] + "," + cp[i++] + "," + cp[i++] + ","+ cp[i++] + "," + cp[i++] + ") " + " VALUES( " + Maximo() + "," + getSk_operacion() + "," + getSk_paro_maquina() + "," + getSk_estado()+","+sk_concate + ");";
        return ejecutarSentencia(sql);
    }

    public int Maximo() {
        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();
        int maximo = 0;
        Connection c = null;
        PreparedStatement ps = null;
        String campo = cp[0];
        String sql = "SELECT MAX("+campo+") max FROM " + tabla + " ;";
        try {
            c = this.jdbcAccess.getConnection();
            ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                maximo = rs.getInt("max");
                maximo++;
            }
            rs.close();
            return maximo;
        } catch (NamingException ne) {
            mostrarExcepcion(ne);
            return maximo;
        } catch (SQLException sqle) {
            mostrarExcepcion(sqle);
            int i = maximo;
            return maximo;
        } catch (Exception e) {
            mostrarExcepcion(e);
            return maximo;
        } finally {
            this.jdbcAccess.cleanup(c, ps, null);
            return maximo;
        }
    }

    public List<Proceso_Paro_DTO> listaAll() {
        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();
        List<Proceso_Paro_DTO> lista = new ArrayList();
        Connection c = null;
        String sql = "SELECT * FROM tblOperacionParo oparo "
                + "INNER JOIN tblOperaciones op ON "
                + "oparo.sk_operacion=op.sk_operacion "
                + "INNER JOIN tblParoMaquina pMq  ON "
                + "pMq.sk_paro_maquina = oparo.sk_paro_maquina;";
        PreparedStatement ps = null;
        try {
            c = this.jdbcAccess.getConnection();
            ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Proceso_Paro_DTO dto = new Proceso_Paro_DTO();
                dto.setSk_operaciones_paro(rs.getInt("sk_operacion_paro"));
                dto.setSk_operacion(rs.getInt("sk_operacion"));
                dto.setNombre_operacion(rs.getString("nombre_operacion"));
                dto.setSk_paro_maquina(rs.getInt("sk_paro_maquina"));
                dto.setNombre_paro_maquina(rs.getString("nombre_paro_maquina"));
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
            mostrarExcepcion(ex);
            return false;
        } catch (NamingException ex) {
            mostrarExcepcion(ex);
        } finally {
            this.jdbcAccess.cleanup(c, ps, null);
        }
        return true;
    }

    public void mostrarExcepcion(Exception ex) {
        Logger.getLogger(Proceso_Paro_DAO.class.getName()).log(Level.SEVERE, null, ex);
    }

    public void mostrarExcepcionSQL(SQLException sqle) {
        System.out.println("SQLException in" + getClass().getName() );
        String sqlMessage = sqle.getMessage();
        String sqlState = sqle.getSQLState();
        int vendorCode = sqle.getErrorCode();
        System.out.println("Ocurrio una  excejdbcAccession");
        System.out.println("Mensaje " + sqlMessage);
        System.out.println("SQL State " + sqlState);
        System.out.println("Vendor Code " + vendorCode);
    }
       public void eliminarParo() {
  String sql = "DELETE FROM tblOperacionParo WHERE sk_operacion_paro=" + getSk_operaciones_paro();
       ejecutarSentencia(sql);

    }
}
