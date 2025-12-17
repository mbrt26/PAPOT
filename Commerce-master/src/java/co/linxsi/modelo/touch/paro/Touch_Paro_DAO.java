/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.linxsi.modelo.touch.paro;

import co.linxsi.modelo.touch.retal.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/*   Document   :TouchRetalServlet
    Created on : 15-jul-2019, 10:18:03
    Author     : Edgar J. García L.*/
public class Touch_Paro_DAO extends Touch_Paro_DTO {

    protected final SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyy HH:mm");
    String consultaFecha = " ";
    String consultaConsultaMaquina = " ";
    String consultaOrden = " ";
    String consultaOperador = " ";
    String consultaProceso = " ";

    public List<Touch_Paro_DTO> getListScrapProcess() {
        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();
        List<Touch_Paro_DTO> lista = new ArrayList();
        Connection c = null;
        String sql
                = "SELECT tblRetales.sk_retal,nombre_retal,sk_operacion \n"
                + "FROM tblOperacionRetal \n"
                + "INNER JOIN tblRetales \n"
                + "ON tblOperacionRetal.sk_retal = tblRetales.sk_retal \n"
                + "WHERE sk_operacion= " + getSk_proceso() + "\n"
                + "ORDER BY nombre_retal";
        PreparedStatement ps = null;
        try {
            c = this.jdbcAccess.getConnection();
            ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Touch_Paro_DTO bdto = new Touch_Paro_DTO();
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

    public List<Touch_Paro_DTO> getListOperatorByProcess() {
        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();
        List<Touch_Paro_DTO> lista = new ArrayList();
        Connection c = null;
        String sql
                = "SELECT [idUsuario]\n"
                + "      ,[nombreUsuario]\n"
                + "  FROM [ctrlUsuarios] \n"
                + "  INNER JOIN tblJobOperacionOperario \n"
                + "  ON ctrlUsuarios.idUsuario = tblJobOperacionOperario.idOperario\n"
                + "  INNER JOIN tblOperaciones \n"
                + "  ON tblOperaciones.sk_operacion = tblJobOperacionOperario.idOperacion\n"
                + "  WHERE idOperacion=" + getSk_proceso() + "\n"
                + "  ORDER BY nombreUsuario;";
        PreparedStatement ps = null;
        try {
            c = this.jdbcAccess.getConnection();
            ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Touch_Paro_DTO bdto = new Touch_Paro_DTO();
                bdto.setSk_Operario(rs.getInt("idUsuario"));
                bdto.setNombreOperario(rs.getString("nombreUsuario"));
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

    public List<Touch_Paro_DTO> getListAllStops() {
        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();
        List<Touch_Paro_DTO> lista = new ArrayList();
        Connection c = null;
        String sql
                = "SELECT [id]\n"
                + "      ,[idDcto]\n"
                + "      ,[idMaquina]\n"
                + "      ,[fechaInicio]\n"
                + "      ,[fechaFin]\n"
                + "      ,[cantTerminada]\n"
                + "      ,[idCausa]\n"
                + "	  ,nombre_paro_maquina\n"
                + "      ,[idOperacion]\n"
                + "      ,[idoperador]\n"
                + "      ,[tiempoParada]\n"
                + "      ,[observacion]\n"
                + "  FROM [tblParosOrden] "
                + " INNER JOIN [tblParoMaquina] "
                + "ON sk_paro_maquina=idCausa WHERE idDcto = " + getIdDcto() + ";";
        PreparedStatement ps = null;
        try {
            c = this.jdbcAccess.getConnection();
            ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Touch_Paro_DTO bdto = new Touch_Paro_DTO();
                bdto.setSk_Operario(rs.getInt("idoperador"));
                bdto.setNombreParo(rs.getString("nombre_paro_maquina"));
                bdto.setFechaInicial(rs.getString("fechaInicio"));
                bdto.setFechaFinal(rs.getString("fechaFin"));
                bdto.setTiempoParo(rs.getDouble("tiempoParada"));
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

    public List<Touch_Paro_DTO> listHistorial() {
        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();
        List<Touch_Paro_DTO> lista = new ArrayList();
        asignarCondiciones();
        Connection c = null;
        String sql
                = "SELECT  *\n"
                + "  FROM  [tblParosOrden] INNER JOIN tblParoMaquina ON sk_paro_maquina = idCausa\n"
                + "  INNER JOIN ctrlUsuarios ON ctrlUsuarios.idUsuario = idoperador \n"
                + "  INNER JOIN TblMaquinas ON sk_maquina = idMaquina\n"
                + "  INNER JOIN tblOperaciones ON tblOperaciones.sk_operacion=idOperacion\n"
                + "  INNER JOIN tblDctosOrdenes ON tblDctosOrdenes.numeroOrden = tblParosOrden.idDcto AND tblDctosOrdenes.IDTIPOORDEN=59"
                + " WHERE "
                + consultaFecha + " "
                + consultaOrden
                + consultaProceso
                + consultaConsultaMaquina
                + consultaOperador
                + "ORDER BY fecha DESC";
        System.out.println(sql);
        PreparedStatement ps = null;
        try {
            c = this.jdbcAccess.getConnection();
            ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Touch_Paro_DTO bdto = new Touch_Paro_DTO();
                bdto.setIdDcto(rs.getInt("idDcto"));
                bdto.setNombreProceso(rs.getString("nombre_operacion"));
                bdto.setNombreParo(rs.getString("nombre_paro_maquina"));
                bdto.setFecha(sdf2.format(rs.getTimestamp("fecha")));
                bdto.setNombreOperario(rs.getString("aliasUsuario"));
                bdto.setNombreMaquina(rs.getString("nombre_maquina"));
                bdto.setObservacion(rs.getString("observacion"));
                bdto.setNombreParo(rs.getString("nombre_paro_maquina"));
                bdto.setTiempoParo(rs.getDouble("tiempoParada"));
                bdto.setFechaInicial(rs.getString("fechaInicio"));
                bdto.setFechaFinal(rs.getString("fechaFin"));
                bdto.setPesoProducido(rs.getDouble("cantTerminada"));
                bdto.setValorEstandar(rs.getDouble("tiempo_montaje"));
                bdto.setTiempoProduccion(rs.getDouble("tiempoProduccion"));
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


    private void asignarCondiciones() {
        if (this.getSk_proceso() != 0) {
            consultaProceso = " AND tblOperaciones.sk_operacion = " + this.getSk_proceso();
        }
        if (this.getSk_Maquina() != 0) {
            consultaConsultaMaquina = " AND TblMaquinas.sk_maquina = " + this.getSk_Maquina();
        }
        if (this.getSk_Operario() != 0) {
            consultaOperador = " AND idOperario = " + this.getSk_Operario();
        }
        if (this.getIdDcto() != 0) {
            consultaOrden = "tblDctosOrdenes.numeroOrden = " + this.getIdDcto();
        } else {
            consultaFecha = " fecha BETWEEN '" + getFechaInicial() + "' AND '" + getFechaFinal() + " 23:59:59' ";
        }
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

    public boolean insert() {
        String sql
                = "INSERT INTO tblParosOrden "
                + "("
                + "      [idDcto]\n"
                + "      ,[idMaquina]\n"
                + "      ,[fechaInicio]\n"
                + "      ,[fechaFin]\n"
                + "      ,[cantTerminada]\n"
                + "      ,[idCausa]\n"
                + "      ,[idoperador]\n"
                + "      ,[idOperacion]\n"
                + "      ,[tiempoParada]\n"
                + "      ,[observacion] "
                + "      ,[tiempoProduccion]"
                + "      ,[fecha]"
                + ") "
                + "VALUES (" + getIdDcto()
                + "," + getSk_Maquina()
                + ",'" + getFechaInicial()
                + "','" + getFechaFinal()
                + "'," + getPesoProducido()
                + ", " + getIdCausaParo()
                + ", " + getSk_Operario()
                + ", " + getSk_proceso()
                + ", " + getTiempoParo()
                + ", '" + getObservacion() + "'"
                + ", " + getTiempoProduccion() + ""
                + ", GETDATE ( )  "
                + ");";

        return ejecutarSentencia(sql);
    }
}
