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
import javax.naming.NamingException;

/**
 *
 * @author Edgar J García L
 */
public class DAO_Dcto_Detalles_Cot extends DTO_Detalles_Dcto {

    private static final String DATA_SOURCE_NAME = "CommerceAccess";
    private JDBCAccess pc;

    public DAO_Dcto_Detalles_Cot() {
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

    public boolean ingresarDocumentoDetalle() {
        String sql
                = "INSERT INTO  [BDCommerce].[dbo].[tblDetalleCotizacion] "
                + "      ([idDcto]\n"
                + "      ,[nombreReferencia]\n"
                + "      ,[cant]\n"
                + "      ,[precioVenta]\n"
                + "      ,[material]\n"
                + "      ,[item]\n"
                + "      ,[idPlu]\n"
                + "      ,[medida]\n"
                + "      ,[calibre])"
                + "      VALUES (" + getIdDcto()
                + " ,'" + getNombreReferencia() + "'"
                + " ," + getCantidad() + ""
                + " ," + getPrecio()+ ""
                + " ,'" + getNombreMaterial() + "'"
                + " ," + getItem() + ""
                + " ," + getIdPlu() + ""
                + " ,'" + getMedida() + "'"
                + " ," + getCalibre()
                + ")";

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
