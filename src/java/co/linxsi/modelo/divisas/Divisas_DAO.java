/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.linxsi.modelo.divisas;


import co.linxsi.modelo.maestro.operaciones.Proceso_Materia_DTO;
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
public class Divisas_DAO extends Divisas_DTO {

    private static final String DATA_SOURCE_NAME = "CommerceAccess";
    private JDBCAccess pc;

    public Divisas_DAO() {
        pc = new JDBCAccess(DATA_SOURCE_NAME);

    }

    public Divisas_DTO getDivisa(int id) {

        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();

        Connection c = null;
        String sql = "SELECT TOP 1 [id]\n"
                + "      ,[nombreDivisa]\n"
                + "      ,[vrActual]\n"
                + "      ,[vrFuturo]\n"
                + "      ,[simbolo]\n"
                + "      ,[estado]\n"
                + "  FROM [dbo].[tblDivisas] WHERE ID =" + id
                + "AND ESTADO =1";

        PreparedStatement ps = null;
        try {
            c = this.pc.getConnection();
            ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                this.setId(rs.getInt("id"));
                this.setNombreDivisa(rs.getString("nombreDivisa"));
                this.setVrActual(rs.getDouble("vrActual"));
                this.setVrFuturo(rs.getDouble("vrFuturo"));
                this.setSimbolo(rs.getString("simbolo"));
            }
            rs.close();
            return this;
        } catch (NamingException ne) {
            System.out.println("NamingException in " + nombreClase + " " + ne);
//            return this;
        } catch (SQLException sqle) {
            System.out.println("SQLException in" + nombreClase);
            String sqlMessage = sqle.getMessage();
            String sqlState = sqle.getSQLState();
            int vendorCode = sqle.getErrorCode();
            System.out.println("Ocurrio una  excepcion");
            System.out.println("Mensaje " + sqlMessage);
            System.out.println("SQL State " + sqlState);
            System.out.println("Vendor Code " + vendorCode);

            return this;
        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
            return this;
        } finally {
            this.pc.cleanup(c, ps, null);
            return this;
        }
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
     
       public boolean save() {
        String sql
                = "UPDATE [dbo].[tblDivisas] SET vrActual="+getVrActual()+", vrFuturo ="+getVrFuturo()+" WHERE id = 1";
        return ejecutarSentencia(sql);

    }
}
