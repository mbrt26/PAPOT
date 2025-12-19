/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.linxsi.modelo.maestro.maquinas;

import co.linxsi.modelo.maestro.bodega.BodegaDAO;
import co.linxsi.modelo.maestro.operaciones.Operaciones_DTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

public class Maquinas_DAO extends Maquinas_DTO {

    @Override
    public boolean ingresa() {
         String sql = "INSERT INTO TblMaquinas (idLocal,sk_operacion,sk_maquina,nombre_maquina,sk_estado,tiempo_montaje,capacidad,velocidad)"
                + " VALUES(" + getSk_local() + 
                "," + getSk_operacion() + 
                "," + getSk_maquina() + 
                ",'" + getNombre() + 
                "'," + getSk_estado() +
                "," + getTiempoMontaje()+
                "," + getCapacidadInstalada()+
                "," + getVelocidad()
                + ")";
      return  ejecutarSentencia(sql);
    }

    @Override
    public int Maximo() {
        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();
        int maximo = 0;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "SELECT MAX(sk_maquina) sk_maquina FROM tblMaquinas ;";
        try {
            c = this.jdbcAccess.getConnection();
            ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                maximo = rs.getInt("sk_maquina");
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

    public List<Maquinas_DTO> listaAllM() {
        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();
        List<Maquinas_DTO> lista = new ArrayList();
        Connection c = null;
        String sql = "SELECT * FROM tblMaquinas INNER JOIN tblOperaciones ON tblMaquinas.sk_operacion = tblOperaciones.sk_operacion;";
        PreparedStatement ps = null;
        try {
            c = this.jdbcAccess.getConnection();
            ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Maquinas_DTO bdto = new Maquinas_DTO();
                bdto.setSk_local(rs.getInt("idLocal"));
                bdto.setSk_operacion(rs.getInt("sk_operacion"));
                bdto.setSk_maquina(rs.getInt("sk_maquina"));
                bdto.setNombre(rs.getString("nombre_operacion"));
                bdto.setNombreMaquina(rs.getString("nombre_maquina"));
                bdto.setTiempoMontaje(rs.getDouble("tiempo_montaje"));
                bdto.setCapacidadInstalada(rs.getDouble("capacidad"));
                bdto.setVelocidad(rs.getDouble("velocidad"));
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
    public List<Maquinas_DTO> listaAllMachineByProcess() {
        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();
        List<Maquinas_DTO> lista = new ArrayList();
        Connection c = null;
        String operator = "=";
        if(getSk_operacion()==0){
        operator="<>";
        }
        String sql =
                "SELECT * FROM tblMaquinas INNER JOIN tblOperaciones ON "
                + "tblMaquinas.sk_operacion = tblOperaciones.sk_operacion "
                + "WHERE tblOperaciones.sk_operacion "+operator+" "+getSk_operacion()+";";
        PreparedStatement ps = null;
        try {
            c = this.jdbcAccess.getConnection();
            ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Maquinas_DTO bdto = new Maquinas_DTO();
                bdto.setSk_local(rs.getInt("idLocal"));
                bdto.setSk_operacion(rs.getInt("sk_operacion"));
                bdto.setSk_maquina(rs.getInt("sk_maquina"));
                bdto.setNombre(rs.getString("nombre_operacion"));
                bdto.setNombreMaquina(rs.getString("nombre_maquina"));
                bdto.setTiempoMontaje(rs.getDouble("tiempo_montaje"));
                bdto.setCapacidadInstalada(rs.getDouble("capacidad"));
                bdto.setVelocidad(rs.getDouble("velocidad"));
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

    public Maquinas_DTO listaUno() {
        //Permita obtener solo un registro pasando como parametro 
        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();
        Connection c = null;
        Maquinas_DTO bdto = null;
        String sql
                = "SELECT * FROM tblMaquinas WHERE sk_maquina = " + getSk_maquina()+ ";";
        PreparedStatement ps = null;
        try {
            c = this.jdbcAccess.getConnection();
            ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                bdto = new Maquinas_DTO();
                bdto.setSk_local(rs.getInt("idLocal"));
                bdto.setSk_maquina(rs.getInt("sk_maquina"));
                bdto.setSk_operacion(rs.getInt("sk_operacion"));
                bdto.setNombreMaquina(rs.getString("nombre_maquina"));
                bdto.setSk_estado(rs.getInt("sk_estado"));
                bdto.setTiempoMontaje(rs.getDouble("tiempo_montaje"));
                bdto.setCapacidadInstalada(rs.getDouble("capacidad"));
                bdto.setVelocidad(rs.getDouble("velocidad"));
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

            return bdto;
        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
            return bdto;
        } finally {
            this.jdbcAccess.cleanup(c, ps, null);
            return bdto;
        }

    }

    @Override
    public boolean Actualiza() {
        String sql = "UPDATE tblMaquinas SET nombre_maquina = '"
                + getNombreMaquina()+ "',sk_estado = "
                + getSk_estado() +  ", sk_operacion = "
                + getSk_operacion() +  ",tiempo_montaje = "
                + getTiempoMontaje() +  ",capacidad = "
                + getCapacidadInstalada()+  ", velocidad = "
                + getVelocidad() + " WHERE sk_maquina = "
                + getSk_maquina()+ "";
      return  ejecutarSentencia(sql);
    }

public boolean ejecutarSentencia(String sql) {
        //Permite actualizar los registros de la bodegas
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
            System.out.println("SQLException in" + nombreClase);
            String sqlMessage = sqle.getMessage();
            String sqlState = sqle.getSQLState();
            int vendorCode = sqle.getErrorCode();
            System.out.println("Ocurrio una  excejdbcAccession");
            System.out.println("Mensaje " + sqlMessage);
            System.out.println("SQL State " + sqlState);
            System.out.println("Vendor Code " + vendorCode);
            return okIngresar;
        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
            return okIngresar;
        } finally {
            this.jdbcAccess.cleanup(c, ps, null);
            return okIngresar;
        }
    }
}
