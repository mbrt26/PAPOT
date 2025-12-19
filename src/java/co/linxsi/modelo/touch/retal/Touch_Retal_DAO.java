/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.linxsi.modelo.touch.retal;

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
public class Touch_Retal_DAO extends Touch_Retal_DTO {

    protected final SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyy HH:mm");
    String consultaFecha = " ";
    String consultaConsultaMaquina = " ";
    String consultaOrden = " ";
    String consultaOperador = " ";
    String consultaProceso = " ";

    public List<Touch_Retal_DTO> getListScrapProcess() {
        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();
        List<Touch_Retal_DTO> lista = new ArrayList();
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
                Touch_Retal_DTO bdto = new Touch_Retal_DTO();
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

    public List<Touch_Retal_DTO> getListOperatorByProcess() {
        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();
        List<Touch_Retal_DTO> lista = new ArrayList();
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
                Touch_Retal_DTO bdto = new Touch_Retal_DTO();
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

    public List<Touch_Retal_DTO> getListOperatorAll() {
        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();
        List<Touch_Retal_DTO> lista = new ArrayList();
        Connection c = null;
        String sql
                = "SELECT DISTINCT idUsuario,\n"
                + "  nombreUsuario\n"
                + "  FROM [ctrlUsuarios] \n"
                + "  INNER JOIN tblJobOperacionOperario \n"
                + "  ON ctrlUsuarios.idUsuario = tblJobOperacionOperario.idOperario\n"
                + "  INNER JOIN tblOperaciones \n"
                + "  ON tblOperaciones.sk_operacion = tblJobOperacionOperario.idOperacion\n"
                + "  ORDER BY nombreUsuario;";
        PreparedStatement ps = null;
        try {
            c = this.jdbcAccess.getConnection();
            ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Touch_Retal_DTO bdto = new Touch_Retal_DTO();
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

    public List<Touch_Retal_DTO> listHistorial() {
        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();
        List<Touch_Retal_DTO> lista = new ArrayList();
        asignarCondiciones();
        Connection c = null;
        String sql
                = "       SELECT "
                + "       tblDctosOrdenes.numeroOrden as OT,"
                + "       nombre_operacion AS PROCESO,[nombre_retal] AS RETAL,pesoPerdido AS PESORETAL ,"
                + "       fechaFin AS FECHA,nombreUsuario AS OPERARIO,\n"
                + "	  TblMaquinas.nombre_maquina AS MAQUINA, "
                + "	  tblDctosOrdenesProgreso.observacion AS OBSERVACION "
                + "	  FROM [BDCommerce].[dbo].[tblRetales] INNER JOIN tblDctosOrdenesProgreso "
                + "       ON tblRetales.sk_retal =  tblDctosOrdenesProgreso.idCausa "
                + "	  INNER JOIN ctrlUsuarios ON ctrlUsuarios.idUsuario = tblDctosOrdenesProgreso.idOperario "
                + "	  INNER JOIN tblOperaciones ON tblOperaciones.sk_operacion = tblDctosOrdenesProgreso.idOperacion "
                + "	  INNER JOIN tblDctosOrdenes ON tblDctosOrdenes.IDORDEN =  tblDctosOrdenesProgreso.idOrden"
                + "	  INNER JOIN TblMaquinas ON tblDctosOrdenesProgreso.idControlTipo=TblMaquinas.sk_maquina "
                + "	  WHERE "
                + consultaFecha + " "
                + consultaOrden
                + consultaProceso
                + consultaConsultaMaquina
                + consultaOperador
                + "ORDER BY FECHA DESC";
        System.out.println(sql);
        PreparedStatement ps = null;
        try {
            c = this.jdbcAccess.getConnection();
            ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Touch_Retal_DTO bdto = new Touch_Retal_DTO();
                bdto.setIdDcto(rs.getInt("OT"));
                bdto.setNombreProceso(rs.getString("PROCESO"));
                bdto.setNombre_retal(rs.getString("RETAL"));
                bdto.setPesoPerdido(rs.getDouble("PESORETAL"));
                bdto.setFecha(sdf2.format(rs.getTimestamp("FECHA")));
                bdto.setNombreOperario(rs.getString("OPERARIO"));
                bdto.setNombreMaquina(rs.getString("MAQUINA"));
                bdto.setObservacion(rs.getString("OBSERVACION"));
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

    public List<Touch_Retal_DTO> listHistorialAgrupado() {
        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();
        List<Touch_Retal_DTO> lista = new ArrayList();
        asignarCondiciones();
        Connection c = null;
        String sql
                = "SELECT numeroOrden AS OT,nombre_operacion AS PROCESO,\n"
                + "ISNULL(TblMaquinas.nombre_maquina, '') AS MAQUINA,\n"
                + "SUM(pesoTerminado) AS PRODUCIDO,\n"
                + "ISNULL(pesoPerdidoV, 0) AS RETAL,\n"
                + "CASE WHEN (SUM(pesoTerminado) + pesoPerdidoV) = 0 THEN 0\n"
                + "ELSE CAST(ROUND(ISNULL(pesoPerdidoV, 0)*100/(sum(pesoTerminado)+pesoPerdidoV),2,1) AS DEC(10,2)) \n"
                + "END AS  PORCENTAJE\n"
                + "FROM tblDctosOrdenesProgreso\n"
                + "INNER JOIN tblOperaciones ON tblDctosOrdenesProgreso.idOperacion = tblOperaciones.sk_operacion\n"
                + "LEFT JOIN vistaOrdenMaquina ON vistaOrdenMaquina.idOrden=tblDctosOrdenesProgreso.idOrden \n"
                + "AND vistaOrdenMaquina.idoperacion = tblDctosOrdenesProgreso.idOperacion\n"
                + "LEFT JOIN TblMaquinas ON TblMaquinas.sk_maquina = vistaOrdenMaquina.idControlTipo\n"
                + "INNER JOIN tblDctosOrdenes ON tblDctosOrdenes.IDORDEN = tblDctosOrdenesProgreso.idOrden\n"
                + "INNER JOIN ctrlUsuarios ON tblDctosOrdenesProgreso.idOperario=ctrlUsuarios.idUsuario\n"
                + "WHERE "
                + consultaFecha
                + consultaOrden
                + consultaProceso
                + consultaConsultaMaquina
                + consultaOperador
                + "AND tbloperaciones.sk_operacion BETWEEN 2 AND 6\n"
                + "GROUP BY nombre_operacion,tblDctosOrdenesProgreso.idOrden,pesoPerdidoV,numeroOrden,nombre_maquina,tbloperaciones.sk_operacion \n"
                + "ORDER BY tbloperaciones.sk_operacion,PORCENTAJE DESC;";

        PreparedStatement ps = null;
        try {
            c = this.jdbcAccess.getConnection();
            ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Touch_Retal_DTO bdto = new Touch_Retal_DTO();
                bdto.setIdDcto(rs.getInt("OT"));
                bdto.setNombreProceso(rs.getString("PROCESO"));
                bdto.setPesoPerdido(rs.getDouble("RETAL"));
                bdto.setNombreMaquina(rs.getString("MAQUINA"));
                bdto.setPesoProducido(rs.getDouble("PRODUCIDO"));
                bdto.setPorcentajeRetal(rs.getDouble("PORCENTAJE"));//PORCENTAJE PERDIDA RETAL

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

    public List<Touch_Retal_DTO> listGlobalAgrupado() {
        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();
        List<Touch_Retal_DTO> lista = new ArrayList();
        asignarCondiciones();
        Connection c = null;
        String sql
                = "SELECT\n"
                + "nombre_maquina AS MAQUINA,\n"
                + "ISNULL(SUM(pesoPerdidoV), 0) AS SUMA\n"
                + "FROM tblDctosOrdenesProgreso\n"
                + "INNER JOIN tblOperaciones ON tblDctosOrdenesProgreso.idOperacion = tblOperaciones.sk_operacion\n"
                // + "LEFT JOIN vistaRetalMaquina ON vistaRetalMaquina.idControlTipo=tblDctosOrdenesProgreso.idControlTipo \n"
                + "INNER JOIN vistaRetalMaquina ON vistaRetalMaquina.idControlTipo=tblDctosOrdenesProgreso.idControlTipo \n"
                + "AND vistaRetalMaquina.fechafin = tblDctosOrdenesProgreso.fechaInicio\n"
                + "LEFT JOIN TblMaquinas ON TblMaquinas.sk_maquina = vistaRetalMaquina.idControlTipo\n"
                + "INNER JOIN tblDctosOrdenes ON tblDctosOrdenes.IDORDEN = tblDctosOrdenesProgreso.idOrden\n"
                + "INNER JOIN tblRetales ON tblDctosOrdenesProgreso.idCausa=tblRetales.sk_retal\n"
                + "INNER JOIN ctrlUsuarios ON tblDctosOrdenesProgreso.idOperario=ctrlUsuarios.idUsuario"
                + " WHERE "
                + consultaFecha
                + consultaOrden
                + consultaProceso
                + consultaConsultaMaquina
                + consultaOperador
                + "GROUP BY nombre_maquina,tbloperaciones.sk_operacion \n"
                + "ORDER BY nombre_maquina";

        PreparedStatement ps = null;
        try {
            c = this.jdbcAccess.getConnection();
            ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Touch_Retal_DTO bdto = new Touch_Retal_DTO();
                bdto.setPesoPerdido(rs.getDouble("SUMA"));
                bdto.setNombreMaquina(rs.getString("MAQUINA"));
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

    public List<Touch_Retal_DTO> listGlobalAgrupadoBarras() {
        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();
        List<Touch_Retal_DTO> lista = new ArrayList();
        asignarCondiciones();
        Connection c = null;
        String sql
                = "SELECT\n"
                + "nombre_maquina AS MAQUINA,\n"
                + "ISNULL(SUM(pesoPerdidoV), 0) AS SUMA\n"
                + "FROM tblDctosOrdenesProgreso\n"
                + "INNER JOIN tblOperaciones ON tblDctosOrdenesProgreso.idOperacion = tblOperaciones.sk_operacion\n"
                + "INNER JOIN vistaRetalMaquina ON vistaRetalMaquina.idControlTipo=tblDctosOrdenesProgreso.idControlTipo \n"
                + "AND vistaRetalMaquina.fechafin = tblDctosOrdenesProgreso.fechaInicio\n"
                + "LEFT JOIN TblMaquinas ON TblMaquinas.sk_maquina = vistaRetalMaquina.idControlTipo\n"
                + "INNER JOIN tblDctosOrdenes ON tblDctosOrdenes.IDORDEN = tblDctosOrdenesProgreso.idOrden\n"
                + "INNER JOIN tblRetales ON tblDctosOrdenesProgreso.idCausa=tblRetales.sk_retal\n"
                + "INNER JOIN ctrlUsuarios ON tblDctosOrdenesProgreso.idOperario=ctrlUsuarios.idUsuario"
                + " WHERE "
                + consultaFecha
                + consultaOrden
                + consultaProceso
                + consultaConsultaMaquina
                + consultaOperador
                + "GROUP BY nombre_maquina,tbloperaciones.sk_operacion \n"
                + "ORDER BY SUMA ";

        PreparedStatement ps = null;
        try {
            c = this.jdbcAccess.getConnection();
            ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Touch_Retal_DTO bdto = new Touch_Retal_DTO();
                bdto.setPesoPerdido(rs.getDouble("SUMA"));
                bdto.setNombreMaquina(rs.getString("MAQUINA"));
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

    public List<Touch_Retal_DTO> listGlobalAgrupadoPie() {
        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();
        List<Touch_Retal_DTO> lista = new ArrayList();
        asignarCondiciones();
        Connection c = null;
        String sql
                = "SELECT SUM(pesoPerdido) AS PESO,nombre_retal\n"
                + "  FROM [BDCommerce].[dbo].[tblDctosOrdenesProgreso] \n"
                + "  INNER JOIN TblMaquinas ON idControlTipo = sk_maquina\n"
                + "  INNER JOIN tblDctosOrdenes ON tblDctosOrdenes.IDORDEN = tblDctosOrdenesProgreso.idOrden\n"
                + "  INNER JOIN tblRetales ON idCausa = sk_retal\n"
                + "  INNER JOIN ctrlUsuarios ON idOperario=ctrlusuarios.idUsuario\n"
                + "  INNER JOIN tblOperaciones ON tblOperaciones.sk_operacion=tblDctosOrdenesProgreso.idOperacion"
                + " WHERE "
                + consultaFecha
                + consultaOrden
                + consultaProceso
                + consultaConsultaMaquina
                + consultaOperador
                + " GROUP BY nombre_retal ORDER BY PESO DESC;";

        PreparedStatement ps = null;
        try {
            c = this.jdbcAccess.getConnection();
            ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Touch_Retal_DTO bdto = new Touch_Retal_DTO();
                bdto.setPesoPerdido(rs.getDouble("PESO"));
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

    public Touch_Retal_DTO sumRetalHistorial() {
        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();
        Touch_Retal_DTO bdto = new Touch_Retal_DTO();
        asignarCondiciones();
        Connection c = null;
        String sql
                = "       SELECT "
                + "       CAST(ROUND(SUM(pesoPerdido),2,1) AS DEC(10,2)) AS SUMA"
                + "	  FROM [BDCommerce].[dbo].[tblRetales] INNER JOIN tblDctosOrdenesProgreso "
                + "       ON tblRetales.sk_retal =  tblDctosOrdenesProgreso.idCausa "
                + "	  INNER JOIN ctrlUsuarios ON ctrlUsuarios.idUsuario = tblDctosOrdenesProgreso.idOperario "
                + "	  INNER JOIN tblOperaciones ON tblOperaciones.sk_operacion = tblDctosOrdenesProgreso.idOperacion "
                + "	  INNER JOIN tblDctosOrdenes ON tblDctosOrdenes.IDORDEN =  tblDctosOrdenesProgreso.idOrden"
                + "	  INNER JOIN TblMaquinas ON tblDctosOrdenesProgreso.idControlTipo=TblMaquinas.sk_maquina "
                + "	  WHERE "
                + consultaFecha + " "
                + consultaOrden
                + consultaProceso
                + consultaConsultaMaquina
                + consultaOperador;
        PreparedStatement ps = null;
        try {
            c = this.jdbcAccess.getConnection();
            ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                bdto.setPesoPerdido(rs.getDouble("SUMA"));
            }
            rs.close();
            return bdto;
        } catch (NamingException ne) {
            System.out.println("NamingException in " + nombreClase + " " + ne);
            return bdto;
        } catch (SQLException sqle) {
            reportarExcepcion(sqle, nombreClase);

            return bdto;
        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
            return bdto;
        } finally {
            this.jdbcAccess.cleanup(c, ps, null);
            return bdto;
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
            consultaOperador = " AND tblDctosOrdenesProgreso.idOperario = " + this.getSk_Operario();
        }
        if (this.getIdDcto() != 0) {
            consultaOrden = "tblDctosOrdenes.idOrden = " +dameKeyOrden(String.valueOf( this.getIdDcto()));
        } else {
            consultaFecha = " tblDctosOrdenesProgreso.fechaInicio BETWEEN '" + getFechaInicial() + "' AND '" + getFechaFinal() + " 23:59:59' ";
        }
    }
    public String dameKeyOrden(String numeroOrden) {
        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();

        String idOrden = "0";
        Connection c = null;
        String sql = " SELECT (idorden) FROM tblDctosOrdenes WHERE numeroOrden = " + numeroOrden + " AND idtipoorden = 59 ";
        PreparedStatement ps = null;
        try {
            c = this.jdbcAccess.getConnection();
            ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                idOrden = rs.getString("idorden");
            }
            return idOrden;
        } catch (NamingException ne) {
            System.out.println("NamingException in " + nombreClase + " " + ne);
            return idOrden;
        } catch (SQLException sqle) {
            System.out.println("SQLException in" + nombreClase);
            System.out.println("Ocurrio una  excepcion");
            System.out.println("Mensaje " + sqle.getMessage());
            System.out.println("SQL State " + sqle.getSQLState());
            System.out.println("Vendor Code " + sqle.getErrorCode());
            return idOrden;
        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
            return idOrden;
        } finally {
            this.jdbcAccess.cleanup(c, ps, null);
            return idOrden;
        }
    }
    
    
    
}
