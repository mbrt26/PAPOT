/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.linxsi.modelo.touch.edicion;

import co.linxsi.modelo.touch.retal.*;
import com.solucionesweb.losbeans.fachada.FachadaDctoOrdenProgreso;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/*   Document   :Touch Edicion OT DAO
    Created on : 15-jul-2019, 10:18:03
    Author     : Edgar J. García L.*/
public class Touch_Edicion_OT_DAO extends Touch_Retal_DAO {

    protected final SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyy HH:mm");
    String consultaFecha = " ";
    String consultaOrden = " ";
    String consultaOperador = " ";
    String consultaProceso = " ";

    public List<FachadaDctoOrdenProgreso> listHistorialEdicion() {
        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();
        List<FachadaDctoOrdenProgreso> lista = new ArrayList();
        asignarCondiciones();
        Connection c = null;
        String sql
                = "SELECT       [tblDctosOrdenesProgreso].[idLocal]\n"
                + "      ,[tblDctosOrdenesProgreso].[idOrden]\n"
                + "      ,[tblDctosOrdenes].[numeroOrden]\n"
                + "      ,tblDctosOrdenesProgreso.[item]\n"
                + "      ,tblDctosOrdenesProgreso.[idOperacion]\n"
                + "      ,[idOperario]\n"
                + "      ,[cantidadPerdida]\n"
                + "      ,tblDctosOrdenesProgreso.[cantidadTerminada]\n"
                + "      ,[pesoPerdido]\n"
                + "      ,tblDctosOrdenesProgreso.[pesoTerminado]\n"
                + "      ,[fechaInicio]\n"
                + "      ,[fechaFin]\n"
                + "      ,[idCausa]\n"
                + "      ,[tiempoPerdido]\n"
                + "      ,[tblDctosOrdenesProgreso].[item]\n"
                + "      ,[tblDctosOrdenesProgreso].[estado]\n"
                + "      ,tblDctosOrdenesProgreso.[pesoPedido]\n"
                + "      ,[idControl]\n"
                + "      ,tblDctosOrdenesProgreso.[idPlu]\n"
                + "      ,[idControlTipo]\n"
                + "      ,[tblDctosOrdenesProgreso].[observacion]\n"
                + "      ,[tblDctosOrdenesProgreso].[idUsuario]\n"
                + "      ,[cantidadPendiente]\n"
                + "      ,[pesoTara]\n"
                + "      ,[idTipoCausa]\n"
                + "      ,[idMaquina]\n"
                + "	 ,[nombreUsuario]\n"
                + "	 ,tblDctosOrdenesDetalle.referenciaCliente\n"
                + "	 ,tblOperaciones.nombre_operacion\n"
                + "FROM [dbo].[tblDctosOrdenesProgreso],\n"
                + "[dbo].[tblDctosOrdenes],\n"
                + "ctrlUsuarios,\n"
                + "tblOperaciones,\n"
                + "tblDctosOrdenesDetalle\n"
                + " WHERE\n"
                + " [tblDctosOrdenesProgreso].idOperacion = tblOperaciones.sk_operacion \n"
                + " AND ctrlUsuarios.idUsuario = tblDctosOrdenesProgreso.idOperario \n"
                + " AND tblDctosOrdenes.IDORDEN=tblDctosOrdenesProgreso.idOrden \n"
                + " AND tblDctosOrdenes.IDTipoOrden = 59\n"
                + " AND tblDctosOrdenesDetalle.IDTIPOORDEN = 59"
                + " AND tblDctosOrdenesDetalle.IDORDEN=tblDctosOrdenesProgreso.idOrden \n"
                + " AND tblDctosOrdenesProgreso.idOperacion BETWEEN 2 AND 9\n"
                + consultaFecha
                + consultaOrden
                + consultaOperador
                + consultaProceso
                + " ORDER BY [dbo].[tblDctosOrdenesProgreso].[idOperacion],[tblDctosOrdenesProgreso].[idOrden],[dbo].[tblDctosOrdenesProgreso].item ";
    
        PreparedStatement ps = null;
        try {
            c = this.jdbcAccess.getConnection();
            ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                FachadaDctoOrdenProgreso fachadaBean = new FachadaDctoOrdenProgreso();
                fachadaBean.setIdOrden(rs.getInt("idOrden"));
                fachadaBean.setNumeroOrden(rs.getInt("numeroOrden"));
                fachadaBean.setItem(rs.getInt("item"));
                fachadaBean.setIdOperacion(rs.getInt("idOperacion"));
                fachadaBean.setIdOperario(rs.getDouble("idOperario"));
                fachadaBean.setCantidadPerdida(rs.getDouble("cantidadPerdida"));
                fachadaBean.setCantidadTerminada(rs.getDouble("cantidadTerminada"));
                fachadaBean.setPesoPerdido(rs.getDouble("pesoPerdido"));
                fachadaBean.setPesoTerminado(rs.getDouble("pesoTerminado"));
                fachadaBean.setFechaInicio(rs.getString("fechaInicio"));
                fachadaBean.setFechaFin(rs.getString("fechaFin"));
                fachadaBean.setIdCausa(rs.getInt("idCausa"));
                fachadaBean.setReferenciaCliente(rs.getString("referenciaCliente"));
                fachadaBean.setNombreOperario(rs.getString("nombreUsuario"));
                fachadaBean.setNombreOperacion(rs.getString("nombre_operacion"));
                fachadaBean.setIdControl(rs.getInt("idControl"));
                fachadaBean.setIdPlu(rs.getInt("idPlu"));
                fachadaBean.setPesoTara(rs.getDouble("pesoTara"));
                fachadaBean.setItem(rs.getInt("item"));
                fachadaBean.setIdControlTipo(rs.getInt("idControlTipo"));
                fachadaBean.setTiempoPerdido(rs.getInt("tiempoPerdido"));

                lista.add(fachadaBean);
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

    public FachadaDctoOrdenProgreso getOneRecord(String idOperacion, String idOrden, String item) {
        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();
        FachadaDctoOrdenProgreso fachadaBean = new FachadaDctoOrdenProgreso();

        Connection c = null;
        String sql
                = "SELECT       [tblDctosOrdenesProgreso].[idLocal]\n"
                + "      ,[tblDctosOrdenesProgreso].[idOrden]\n"
                + "      ,[tblDctosOrdenes].[numeroOrden]\n"
                + "      ,tblDctosOrdenesProgreso.[item]\n"
                + "      ,tblDctosOrdenesProgreso.[idOperacion]\n"
                + "      ,tblDctosOrdenesProgreso.[observacion]\n"
                + "      ,[idOperario]\n"
                + "      ,[cantidadPerdida]\n"
                + "      ,tblDctosOrdenesProgreso.[cantidadTerminada]\n"
                + "      ,[pesoPerdido]\n"
                + "      ,tblDctosOrdenesProgreso.[pesoTerminado]\n"
                + "      ,[fechaInicio]\n"
                + "      ,[fechaFin]\n"
                + "      ,[idCausa]\n"
                + "      ,[tblDctosOrdenesProgreso].[estado]\n"
                + "      ,tblDctosOrdenesProgreso.[pesoPedido]\n"
                + "      ,[idControl]\n"
                + "      ,tblDctosOrdenesProgreso.[idPlu]\n"
                + "      ,[idControlTipo]\n"
                + "      ,[tblDctosOrdenesProgreso].[observacion]\n"
                + "      ,[tblDctosOrdenesProgreso].[idUsuario]\n"
                + "      ,[cantidadPendiente]\n"
                + "      ,[pesoTara]\n"
                + "      ,[idTipoCausa]\n"
                + "      ,[idMaquina]\n"
                + "	 ,[nombreUsuario]\n"
                + "	 ,tblDctosOrdenesDetalle.referenciaCliente\n"
                + "	 ,tblOperaciones.nombre_operacion\n"
                + "	 ,idControlTipo\n"
                + "	 ,nombreTercero\n"
                + "	 ,idFicha\n"
                + "	 ,tiempoPerdido\n "
                + "	 ,fechaProduccion\n "
                + "	 ,idTurno\n"
                + "FROM [dbo].[tblDctosOrdenesProgreso],\n"
                + "[dbo].[tblDctosOrdenes],\n"
                + "ctrlUsuarios,\n"
                + "tblOperaciones,\n"
                + "tblDctosOrdenesDetalle,\n"
                + "tblTerceros\n"
                + "WHERE\n"
                + "[tblDctosOrdenesProgreso].idOperacion = tblOperaciones.sk_operacion AND ctrlUsuarios.idUsuario = tblDctosOrdenesProgreso.idOperario \n"
                + " AND tblDctosOrdenes.IDORDEN=tblDctosOrdenesProgreso.idOrden \n"
                + " AND tblDctosOrdenesDetalle.IDORDEN=tblDctosOrdenesProgreso.idOrden \n"
                + " AND tblDctosOrdenesProgreso.idOperacion BETWEEN 2 AND 9\n"
                + " AND tblDctosOrdenesProgreso.[idOperacion] =  " + idOperacion
                + " AND tblDctosOrdenesProgreso.IDLOCAL=1\n"
                + " AND tblDctosOrdenes.IDORDEN=" + idOrden
                + " AND tblDctosOrdenesProgreso.item = " + item
                + " AND tblDctosOrdenes.idCliente=tblTerceros.idCliente\n"
                + "AND tblTerceros.idTipoTercero=1\n"
                + "ORDER BY fechaInicio DESC";
        System.out.println(sql);
        PreparedStatement ps = null;
        try {
            c = this.jdbcAccess.getConnection();
            ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                fachadaBean.setIdOrden(rs.getInt("idOrden"));
                fachadaBean.setNumeroOrden(rs.getInt("numeroOrden"));
                fachadaBean.setItem(rs.getInt("item"));
                fachadaBean.setIdOperacion(rs.getInt("idOperacion"));
                fachadaBean.setIdOperario(rs.getDouble("idOperario"));
                fachadaBean.setCantidadPerdida(rs.getDouble("cantidadPerdida"));
                fachadaBean.setCantidadTerminada(rs.getDouble("cantidadTerminada"));
                fachadaBean.setPesoPerdido(rs.getDouble("pesoPerdido"));
                fachadaBean.setPesoTerminado(rs.getDouble("pesoTerminado"));
                fachadaBean.setFechaInicio(rs.getString("fechaInicio"));
                fachadaBean.setFechaFin(rs.getString("fechaFin"));
                fachadaBean.setIdCausa(rs.getInt("idCausa"));
                fachadaBean.setReferenciaCliente(rs.getString("referenciaCliente"));
                fachadaBean.setNombreOperario(rs.getString("nombreUsuario"));
                fachadaBean.setNombreOperacion(rs.getString("nombre_operacion"));
                fachadaBean.setIdControl(rs.getInt("idControl"));
                fachadaBean.setIdPlu(rs.getInt("idPlu"));
                fachadaBean.setPesoTara(rs.getDouble("pesoTara"));
                fachadaBean.setNombreTercero(rs.getString("nombreTercero"));
                fachadaBean.setIdFicha(rs.getInt("idficha"));
                fachadaBean.setIdMaquina(rs.getInt("idControlTipo") == 0 ? rs.getInt("idMaquina") : rs.getInt("idControlTipo"));
                fachadaBean.setObservacion(rs.getString("observacion"));
                fachadaBean.setTiempoPerdido(rs.getDouble("tiempoPerdido"));
                fachadaBean.setFechaProduccion(rs.getString("fechaProduccion"));
                fachadaBean.setIdTurno(rs.getInt("idTurno"));
                
               

            }
            rs.close();
            return fachadaBean;
        } catch (NamingException ne) {
            System.out.println("NamingException in " + nombreClase + " " + ne);
            return fachadaBean;
        } catch (SQLException sqle) {
            reportarExcepcion(sqle, nombreClase);
            return fachadaBean;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
            return fachadaBean;
        } finally {
            this.jdbcAccess.cleanup(c, ps, null);
            return fachadaBean;
        }
    }

    public boolean Actualiza(FachadaDctoOrdenProgreso dcto) {
        String campoMaquina = dcto.getPesoPerdido() == 0.0 ? "[idMaquina]" : "[idControlTipo]";
        String sql
                = "UPDATE [dbo].[tblDctosOrdenesProgreso] "
                + "SET "
                + "       [idOperacion]  = " + dcto.getIdOperacion()
                + "      ,[idOperario] = " + dcto.getIdOperario()
                + "      ,[cantidadTerminada] = " + dcto.getCantidadTerminada()
                + "      ,[pesoPerdido] = " + dcto.getPesoPerdido()
                + "      ,[pesoTerminado] = " + dcto.getPesoTerminado()
                + "	 ,[pesoTara] =" + dcto.getPesoTara()
                + "      ,[fechaInicio]= '" + dcto.getFechaInicio() + "'"
                + "      ,[fechaFin] = '" + dcto.getFechaFin() + "'"
                + "      ,[observacion] = '" + dcto.getObservacion() + "'"
                + "      ,[idUsuario] = " + dcto.getIdUsuarioStr()
                + "      ,[idCausa] = " + dcto.getIdCausa()
                + "      ,[tiempoPerdido] = " + dcto.getTiempoPerdido()
                + "      ,[idTurno] = " + dcto.getIdTurno()
                + "      ,[fechaProduccion] = '" + dcto.getFechaProduccion()+"' "
                + "      ," + campoMaquina + "=" + dcto.getIdMaquina()
                + "       WHERE idOrden = " + dcto.getIdOrden()
                + "       AND [item] = " + dcto.getItem()
                + "       AND [idOperacion]  = " + dcto.getIdOperacion();
        return ejecutarSentencia(sql);
    }

    private void asignarCondiciones() {
        if (this.getSk_proceso() != 0) {
            consultaProceso = " AND tblDctosOrdenesProgreso.[idOperacion] = " + this.getSk_proceso();
        }
        if (this.getSk_Operario() != 0) {
            consultaOperador = " AND tblDctosOrdenesProgreso.idOperario = " + this.getSk_Operario();
        }
        if (this.getIdDcto() != 0) {
            consultaOrden = "AND tblDctosOrdenes.IdOrden = " + dameKeyOrden(String.valueOf(this.getIdDcto()));
        }
        consultaFecha = "AND tblDctosOrdenesProgreso.fechaFin BETWEEN '" + getFechaInicial() + "' AND '" + getFechaFinal() + " 23:59:59' ";

    }

    public boolean delete(FachadaDctoOrdenProgreso dcto) {

        String sql
                = " DELETE FROM  tblDctosOrdenesProgreso WHERE idOrden = " + dcto.getIdOrden()
                + "       AND [item] = " + dcto.getItem()
                + "       AND [idOperacion]  = " + dcto.getIdOperacion();
        return ejecutarSentencia(sql);
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
