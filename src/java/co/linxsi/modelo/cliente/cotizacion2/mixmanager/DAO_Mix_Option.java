package co.linxsi.modelo.cliente.cotizacion2.mixmanager;

import com.solucionesweb.lasayudas.JDBCAccess;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;

/**
 *
 * @author egarcia
 */
public class DAO_Mix_Option extends MixOption {

    private static final String DATA_SOURCE_NAME = "CommerceAccess";
    private JDBCAccess pc;

    public DAO_Mix_Option() {
        this.pc = new JDBCAccess(DATA_SOURCE_NAME);
    }
       
    
        public Double getFactorDensidad(final int idMix, final int numberOfLayers) {

            Class tipoObjeto = getClass();
            String nombreClase = tipoObjeto.getName();
            Double factorDensidad = 0.0;
            Connection c = null;
            String sql
                    = "SELECT TOP (1) \n"
                    + " density\n"
                    + " FROM [dbo].[tbl_mixes_option] MO,\n"
                    + "[dbo].[tbl_options] O\n"
                    + "WHERE MO.id_mix= ?\n"
                    + "AND MO.id_option= O.id\n"
                    + "AND number_of_layers = ?\n"
                    + "AND MO.selected =1";

            PreparedStatement ps = null;
            try {
                c = this.pc.getConnection();
                ps = c.prepareStatement(sql);
                ps.setInt(1, idMix);
                ps.setInt(2, numberOfLayers);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {

                factorDensidad = rs.getDouble("density");
            }
            rs.close();

        } catch (NamingException ne) {
            System.out.println("NamingException in " + nombreClase + " " + ne);
            return factorDensidad;
        } catch (SQLException sqle) {
            System.out.println("SQLException in" + nombreClase);
            String sqlMessage = sqle.getMessage();
            String sqlState = sqle.getSQLState();
            int vendorCode = sqle.getErrorCode();
            System.out.println("Ocurrio una  excepcion");
            System.out.println("Mensaje " + sqlMessage);
            System.out.println("SQL State " + sqlState);
            System.out.println("Vendor Code " + vendorCode);
            return factorDensidad;
        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
            return factorDensidad;
        } finally {
            this.pc.cleanup(c, ps, null);
            return factorDensidad;
        }
    }
       public int getOptionId(final int idMix, final int numberOfLayers) {

            Class tipoObjeto = getClass();
            String nombreClase = tipoObjeto.getName();
            int idOption = 0;
            Connection c = null;
            String sql
                = "SELECT TOP (1) \n"
                + " [id_option]\n"
                + " FROM [dbo].[tbl_mixes_option] MO,\n"
                + "[dbo].[tbl_options] O\n"
                + "WHERE MO.id_mix= ?\n"
                + "AND MO.id_option= O.id\n"
                + "AND number_of_layers = ?\n"
                + "AND MO.selected =1";

        PreparedStatement ps = null;
        try {
            c = this.pc.getConnection();
            ps = c.prepareStatement(sql);
            ps.setInt(1, idMix);
            ps.setInt(2, numberOfLayers);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                idOption = rs.getInt("id_option");
            }
            rs.close();

        } catch (NamingException ne) {
            System.out.println("NamingException in " + nombreClase + " " + ne);
            return idOption;
        } catch (SQLException sqle) {
            System.out.println("SQLException in" + nombreClase);
            String sqlMessage = sqle.getMessage();
            String sqlState = sqle.getSQLState();
            int vendorCode = sqle.getErrorCode();
            System.out.println("Ocurrio una  excepcion");
            System.out.println("Mensaje " + sqlMessage);
            System.out.println("SQL State " + sqlState);
            System.out.println("Vendor Code " + vendorCode);
            return idOption;
        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
            return idOption;
        } finally {
            this.pc.cleanup(c, ps, null);
            return idOption;
        }
    }
        
    public Double getPesoMillar(int idFicha, int idMix,int numberOfLayers) {

        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();
        Double pesoMillar = 0.0;
        Connection c = null;
        String sql = "SELECT TOP 1 V.[idFicha],\n"
                + "(CASE WHEN V.bandera=0 THEN volumen*(SELECT TOP (1) \n"
                    + " density\n"
                    + " FROM [dbo].[tbl_mixes_option] MO,\n"
                    + "[dbo].[tbl_options] O\n"
                    + "WHERE MO.id_mix= ?\n"
                    + "AND MO.id_option= O.id\n"
                    + "AND number_of_layers = ?\n"
                    + "AND MO.selected =1) ELSE VOLUMEN END ) AS PESOMILLAR\n"
                + " FROM [dbo].[tmpVolumenFicha] V WHERE \n"
                + " V.idFicha = ?";

        PreparedStatement ps = null;
        try {
            c = this.pc.getConnection();
            ps = c.prepareStatement(sql);
            ps.setInt(1, idMix);
            ps.setInt(2, numberOfLayers);
            ps.setInt(3, idFicha);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                pesoMillar = rs.getDouble("PESOMILLAR");
            }
            rs.close();

        } catch (NamingException ne) {
            System.out.println("NamingException in " + nombreClase + " " + ne);
            return pesoMillar;
        } catch (SQLException sqle) {
            System.out.println("SQLException in" + nombreClase);
            String sqlMessage = sqle.getMessage();
            String sqlState = sqle.getSQLState();
            int vendorCode = sqle.getErrorCode();
            System.out.println("Ocurrio una  excepcion");
            System.out.println("Mensaje " + sqlMessage);
            System.out.println("SQL State " + sqlState);
            System.out.println("Vendor Code " + vendorCode);
            return pesoMillar;
        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
            return pesoMillar;
        } finally {
            this.pc.cleanup(c, ps, null);
            return pesoMillar;
        }
    }     
}
