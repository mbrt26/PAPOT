/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.linxsi.modelo.cliente.cotizacion;

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
 * @author Edgar J García L
 */
public class DAO_Cliente extends DTO_Cliente {

    private static final String DATA_SOURCE_NAME = "CommerceAccess";
    private JDBCAccess pc;

    public DAO_Cliente() {
        pc = new JDBCAccess(DATA_SOURCE_NAME);

    }

    public boolean ingresa() {
        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;
//Permite ingresar las bodegas por primera vez en la tabla
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "INSERT INTO tblClienteCotizacion (idCliente,nombreCliente,contacto,estado,creado)"
                + "VALUES ("
                + getNit()
                + ","
                + "'" + getRazonSocial() + "'"
                + ","
                + "'" + getContacto() + "'"
                + ","
                + 1
                + ","
                + getCreado()
                + ")";
        try {
            c = this.pc.getConnection();
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
            this.pc.cleanup(c, ps, null);
            return okIngresar;
        }
    }

    public boolean actualizar() {
        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;
//Permite ingresar las bodegas por primera vez en la tabla
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "UPDATE tblClienteCotizacion SET "
                + "nombreCliente = '" + getRazonSocial() + "'"
                + ",contacto = '" + getContacto() + "' "
                + "WHERE idCliente = " + getNit();
        try {
            c = this.pc.getConnection();
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
            this.pc.cleanup(c, ps, null);
            return okIngresar;
        }
    }

    public List<DTO_Cliente> getAll() {

        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();
        List<DTO_Cliente> lista;
        lista = new ArrayList();

        Connection c = null;
        String sql = "SELECT [idCliente]"
                + "      ,[nombreCliente]"
                + "      ,[contacto]"
                + "      ,[creado]"
                + "    FROM [BDCommerce].[dbo].[tblClienteCotizacion] where estado = 1 ORDER BY nombreCliente";

        PreparedStatement ps = null;
        try {
            c = this.pc.getConnection();
            ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DTO_Cliente dto = new DTO_Cliente();
                dto.setNit(rs.getInt("idCliente"));
                dto.setRazonSocial(rs.getString("nombreCliente"));
                dto.setContacto(rs.getString("contacto"));
                dto.setEstado(1);
                dto.setCreado(rs.getString("creado"));
                lista.add(dto);
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
            System.out.println("Ocurrio una  excepcion");
            System.out.println("Mensaje " + sqlMessage);
            System.out.println("SQL State " + sqlState);
            System.out.println("Vendor Code " + vendorCode);
            List localList1 = lista;
            return lista;
        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
            return lista;
        } finally {
            this.pc.cleanup(c, ps, null);
            return lista;
        }
    }

    public List<DTO_Cliente> getAllClientesHaveReferences() {

        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();
        List<DTO_Cliente> lista;
        lista = new ArrayList();

        Connection c = null;
        String sql = 
                 "SELECT "
                + "[BDCommerce].[dbo].[tblClienteCotizacion].[idCliente]"
                + ",[nombreCliente]  "
                + "FROM [BDCommerce].[dbo].[tblClienteCotizacion] "
                + "INNER JOIN [BDCommerce].[dbo].[tblCotClienteRef] ON [BDCommerce].[dbo].[tblCotClienteRef].idCliente=[BDCommerce].[dbo].[tblClienteCotizacion] .idCliente "
                + "WHERE [BDCommerce].[dbo].[tblClienteCotizacion].estado = 1 "
                + "GROUP BY [BDCommerce].[dbo].[tblClienteCotizacion].[idCliente],[nombreCliente]  "
                + "ORDER BY nombreCliente";

        PreparedStatement ps = null;
        try {
            c = this.pc.getConnection();
            ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DTO_Cliente dto = new DTO_Cliente();
                dto.setNit(rs.getInt("idCliente"));
                dto.setRazonSocial(rs.getString("nombreCliente"));

                lista.add(dto);
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
            System.out.println("Ocurrio una  excepcion");
            System.out.println("Mensaje " + sqlMessage);
            System.out.println("SQL State " + sqlState);
            System.out.println("Vendor Code " + vendorCode);
            List localList1 = lista;
            return lista;
        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
            return lista;
        } finally {
            this.pc.cleanup(c, ps, null);
            return lista;
        }
    }

    public List<DTO_Cliente> getAllBusqueda() {

        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();
        List<DTO_Cliente> lista;
        lista = new ArrayList();

        Connection c = null;
        String sql = "SELECT TOP 15 [BDCommerce].[dbo].[tblClienteCotizacion].[idCliente]"
                + "      ,[nombreCliente]"
                + "      ,[contacto]"
                + "    FROM [BDCommerce].[dbo].[tblClienteCotizacion] INNER JOIN [BDCommerce].[dbo].[tblCotClienteRef] ON [BDCommerce].[dbo].[tblCotClienteRef].[idCliente] = [BDCommerce].[dbo].[tblClienteCotizacion].[idCliente]"
                + "WHERE nombreCliente LIKE '%" + getRazonSocial() + "%' AND [BDCommerce].[dbo].[tblClienteCotizacion].estado = 1 GROUP BY [nombreCliente],[BDCommerce].[dbo].[tblClienteCotizacion].[idCliente]   \n"
                + ",[nombreCliente]   \n"
                + ",[contacto] ORDER BY nombreCliente";

        PreparedStatement ps = null;
        try {
            c = this.pc.getConnection();
            ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DTO_Cliente dto = new DTO_Cliente();
                dto.setNit(rs.getInt("idCliente"));
                dto.setRazonSocial(rs.getString("nombreCliente"));
                dto.setContacto(rs.getString("contacto"));
                dto.setEstado(1);
                lista.add(dto);
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
            System.out.println("Ocurrio una  excepcion");
            System.out.println("Mensaje " + sqlMessage);
            System.out.println("SQL State " + sqlState);
            System.out.println("Vendor Code " + vendorCode);
            List localList1 = lista;
            return lista;
        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
            return lista;
        } finally {
            this.pc.cleanup(c, ps, null);
            return lista;
        }
    }

    public DTO_Cliente getUnCliente() {

        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();

        DTO_Cliente dto = new DTO_Cliente();
        Connection c = null;
        String sql = "SELECT TOP 1 [idCliente]"
                + "      ,[nombreCliente]"
                + "      ,[contacto]"
                + "      ,[creado]"
                + "    FROM [BDCommerce].[dbo].[tblClienteCotizacion] where estado = 1  AND idCliente = " + getNit()
                + " ORDER BY nombreCliente ";

        PreparedStatement ps = null;
        try {
            c = this.pc.getConnection();
            ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                dto.setNit(rs.getInt("idCliente"));
                dto.setRazonSocial(rs.getString("nombreCliente"));
                dto.setContacto(rs.getString("contacto"));
                dto.setEstado(1);
                dto.setCreado(rs.getString("creado"));

            }
            rs.close();
            return dto;
        } catch (NamingException ne) {
            System.out.println("NamingException in " + nombreClase + " " + ne);
            return dto;
        } catch (SQLException sqle) {
            System.out.println("SQLException in" + nombreClase);
            String sqlMessage = sqle.getMessage();
            String sqlState = sqle.getSQLState();
            int vendorCode = sqle.getErrorCode();
            System.out.println("Ocurrio una  excepcion");
            System.out.println("Mensaje " + sqlMessage);
            System.out.println("SQL State " + sqlState);
            System.out.println("Vendor Code " + vendorCode);

            return dto;
        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
            return dto;
        } finally {
            this.pc.cleanup(c, ps, null);
            return dto;
        }
    }

    public List<DTO_Cliente> getAllClients() {

        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();
        List<DTO_Cliente> lista;
        lista = new ArrayList();

        Connection c = null;
        String sql = "SELECT [idCliente]\n"
                + "      ,[idTercero]\n"
                + "      ,[nombreTercero]\n"
                + "     ,[nombreEmpresa]		        \n"
                + "     ,[contactoTercero]		        \n"
                + "  FROM [BDCommerce].[dbo].[tblTerceros] "
                + " WHERE estado  =1 AND idTipoTercero=1 ORDER BY nombreTercero ";

        PreparedStatement ps = null;
        try {
            c = this.pc.getConnection();
            ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DTO_Cliente dto = new DTO_Cliente();
                dto.setNit(rs.getInt("idCliente"));
                dto.setRazonSocial(rs.getString("nombreTercero"));
                dto.setContacto(rs.getString("contactoTercero"));
                dto.setEstado(1);
                lista.add(dto);
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
            System.out.println("Ocurrio una  excepcion");
            System.out.println("Mensaje " + sqlMessage);
            System.out.println("SQL State " + sqlState);
            System.out.println("Vendor Code " + vendorCode);
            List localList1 = lista;
            return lista;
        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
            return lista;
        } finally {
            this.pc.cleanup(c, ps, null);
            return lista;
        }
    }
}
