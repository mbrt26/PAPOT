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
public class DAO_Dcto_Cot extends DTO_Dcto {

    private static final String DATA_SOURCE_NAME = "CommerceAccess";
    private JDBCAccess pc;

    public DAO_Dcto_Cot() {
        pc = new JDBCAccess(DATA_SOURCE_NAME);

    }

    public int getMaxDcto() {
        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();
        int max = 0;
        Connection c = null;
        String sql = "SELECT (ISNULL(MAX(idDcto) , 0)+1) AS MAX FROM  [BDCommerce].[dbo].[tblDctoCotizacion] ";
        PreparedStatement ps = null;
        try {
            c = this.pc.getConnection();
            ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                max = rs.getInt("MAX");

            }
            rs.close();
            return max;
        } catch (NamingException ne) {
            System.out.println("NamingException in " + nombreClase + " " + ne);
            return max;

        } catch (SQLException sqle) {
            System.out.println("SQLException in" + nombreClase);
            String sqlMessage = sqle.getMessage();
            String sqlState = sqle.getSQLState();
            int vendorCode = sqle.getErrorCode();
            System.out.println("Ocurrio una  excepcion");
            System.out.println("Mensaje " + sqlMessage);
            System.out.println("SQL State " + sqlState);
            System.out.println("Vendor Code " + vendorCode);
            return max;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
            return max;

        } finally {
            this.pc.cleanup(c, ps, null);
            return max;

        }
    }

    public List<DTO_Dcto> getHistorial(int idCliente, String fechaInicial, String fechaFinal) {
        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();
        String operador = idCliente == 0 ? "<>" : "=";
        Connection c = null;
        String sql = "SELECT TOP (1000) [idDcto]\n"
                + "      ,[fechaEmision]\n"
                + "      ,[idEmisor]\n"
                + "      ,[idCliente]\n"
                + "      ,[nombreCliente]\n"
                + "      ,[contacto]\n"
                + "      ,[cargo]\n"
                + "      ,[encabezado]\n"
                + "      ,[piePagina]\n"
                + "	  ,[nombreUsuario]\n"
                + "  FROM [BDCommerce].[dbo].[tblDctoCotizacion] INNER JOIN ctrlUsuarios ON idEmisor  = ctrlUsuarios.idUsuario "
                + "  WHERE idCliente " + operador + " " + idCliente
                + "AND fechaEmision BETWEEN '" + fechaInicial + "' AND '" + fechaFinal + " 23:59:59' "
                + "  ORDER by idDcto DESC;";
        PreparedStatement ps = null;
        List<DTO_Dcto> lista = new ArrayList();
        try {
            c = this.pc.getConnection();
            ps = c.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                DTO_Dcto dto = new DTO_Dcto();
                dto.setIdDcto(rs.getInt("idDcto"));
                dto.setIdEmisor(rs.getInt("idEmisor"));
                dto.setNombreCliente(rs.getString("nombreCliente"));
                dto.setContacto(rs.getString("contacto"));
                dto.setCargoEmisor(rs.getString("cargo"));
                dto.setFecha(rs.getDate("fechaEmision"));
                dto.setNombreEmisor(rs.getString("nombreUsuario"));
                dto.setIdCliente(rs.getInt("idCliente"));
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
            return lista;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
            return lista;

        } finally {
            this.pc.cleanup(c, ps, null);
            return lista;

        }
    }

    public boolean ingresarDocumento() {
        String sql
                = "INSERT INTO  [BDCommerce].[dbo].[tblDctoCotizacion] "
                + " ([idDcto]\n"
                + "      ,[fechaEmision]\n"
                + "      ,[idEmisor]\n"
                + "      ,[idCliente]\n"
                + "      ,[nombreCliente]\n"
                + "      ,[contacto]"
                + "      ,[cargo]"
                + "      ,[encabezado]"
                + "      ,[piePagina])"
                + " VALUES ("
                + getIdDcto()
                + ",GETDATE()"
                + "," + getIdEmisor()
                + "," + getIdCliente()
                + ",'" + getNombreCliente()
                + "','" + getContacto()
                + "','" + getCargoEmisor()
                + "','" + getEncabezado()
                + "','" + getPiePagina() + "')";

        return ejecutarSentencia(sql);

    }

    public boolean ejecutarSentencia(String sql) {

        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;
//Permite ingresar las bodegas por primera vez en la tabla
        Connection c = null;
        PreparedStatement ps = null;

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

}
