/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.linxsi.modelo.cliente.cotizacion2.mixmanager;

import com.solucionesweb.lasayudas.JDBCAccess;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;

/**
 *
 * @author A
 */
public class DAO_Machine_Option {
        private static final String DATA_SOURCE_NAME = "CommerceAccess";
    private JDBCAccess pc;

    public DAO_Machine_Option() {
        this.pc = new JDBCAccess(DATA_SOURCE_NAME);
    }
    
    public int getnumberOfLayersFromIdMachine(final int idMachine){
    
        Class tipoObjeto = getClass();
            String nombreClase = tipoObjeto.getName();
            int numberOfLayers = 0;
            Connection c = null;
            String sql
                = "SELECT TOP (1) [numberOfLayers]\n"
                + "  FROM [BDCommerce].[dbo].[tbl_machine_option]\n"
                + "  WHERE idMachine =  ?" 
                + "  AND status = 1 ";

        PreparedStatement ps = null;
        try {
            c = this.pc.getConnection();
            ps = c.prepareStatement(sql);
            ps.setInt(1, idMachine);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                numberOfLayers = rs.getInt("numberOfLayers");
            }
            rs.close();

        } catch (NamingException ne) {
            System.out.println("NamingException in " + nombreClase + " " + ne);
            return numberOfLayers;
        } catch (SQLException sqle) {
            System.out.println("SQLException in" + nombreClase);
            String sqlMessage = sqle.getMessage();
            String sqlState = sqle.getSQLState();
            int vendorCode = sqle.getErrorCode();
            System.out.println("Ocurrio una  excepcion");
            System.out.println("Mensaje " + sqlMessage);
            System.out.println("SQL State " + sqlState);
            System.out.println("Vendor Code " + vendorCode);
            return numberOfLayers;
        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
            return numberOfLayers;
        } finally {
            this.pc.cleanup(c, ps, null);
            return numberOfLayers;
        } 
        
    }
       
    
}
