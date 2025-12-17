/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.linxsi.modelo.retencion.retencion_contable;

import co.linxsi.modelo.maestro.paro_maquina.*;
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
 * @author Desarrollador
 */
public class Retencion_Contable_DAO extends Retencion_Contable_DTO {

    private final JDBCAccess jdbcAccess;
    private static final String DATA_SOURCE_NAME = "CommerceAccess";

    public Retencion_Contable_DAO() {
        jdbcAccess = new JDBCAccess(DATA_SOURCE_NAME);
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

    public boolean Elimina() {
//        String sql = "DELETE FROM tblParoMaquina WHERE sk_paro_maquina = " + getSk_paro_maquina()+ "";
        return ejecutarSentencia("");
    }

    public List<Retencion_Contable_DTO> listaRetFuente() {
        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();
        List<Retencion_Contable_DTO> lista = new ArrayList();
        Connection c = null;
        String sql
                = "SELECT idSubcuenta"
                + "       ,nombreConcepto"
                + "       ,porcentajeRetencion"
                + "       ,vrBaseRetencion "
                + "       ,idTipoOrdenAlcance"
                + "        FROM tblContableRetencion "
                + "	 WHERE idConcepto = 1 "
                + "	 AND( idTipoOrdenAlcance = 1 OR idTipoOrdenAlcance = 3 OR idTipoOrdenAlcance = 4 OR idTipoOrdenAlcance = 5);";
        PreparedStatement ps = null;
        try {
            c = this.jdbcAccess.getConnection();
            ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Retencion_Contable_DTO dto = new Retencion_Contable_DTO();
                dto.setIdSubcuenta(rs.getInt("idSubcuenta"));
                dto.setNombreConcepto(rs.getString("nombreConcepto"));
                dto.setPorcentajeRetencion(rs.getDouble("porcentajeRetencion"));
                dto.setVrBaseRetencion(rs.getDouble("vrBaseRetencion"));
                dto.setIdTipoOrdenAlcance(rs.getInt("idTipoOrdenAlcance"));
                lista.add(dto);
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

    private void reportarExcepcion(SQLException sqle, String nombreClase) {
        System.out.println("SQLException in" + nombreClase);
        String sqlMessage = sqle.getMessage();
        String sqlState = sqle.getSQLState();
        int vendorCode = sqle.getErrorCode();
        System.out.println("Ocurrio una  excejdbcAccession");
        System.out.println("Mensaje " + sqlMessage);
        System.out.println("SQL State " + sqlState);
        System.out.println("Vendor Code " + vendorCode);
    }

    public double calculaRetencionFactura(int xIdAutoRetenedor, int xIdConcepto, double xVrBase, int xIdRteFuenteVrBase) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        //
        double xPorcentajeRetencion = 0.0;
        double xVrRetencion = 0.0;
        double xVrBaseRetencion = 0.0;
        int xVrTope = 0;
        int xVrTope100 = 1;

        Connection connection = null;

        String selectStr
                = "SELECT tblContableRetencion.idConcepto,          "
                + "       tblContableRetencion.nombreConcepto,      "
                + "       tblContableRetencion.porcentajeRetencion, "
                + "       tblContableRetencion.vrBaseRetencion,     "
                + "       tblContableRetencion.estado               "
                + "FROM tblContableRetencion                        "
                + "WHERE tblContableRetencion.idConcepto =  1  "
                + "AND   tblContableRetencion.idTipoOrdenAlcance=   "
                + xIdConcepto;

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            //
            if (rs.next()) {

                //
                xPorcentajeRetencion = rs.getDouble("porcentajeRetencion");
                xVrBaseRetencion = rs.getDouble("vrBaseRetencion");

            }

            // Cierra el Resultset
            rs.close();

            // Valida autoretenedor---------------------------------------------
            if (xIdAutoRetenedor == 0) {

                //--- xVrTope
                if (xIdRteFuenteVrBase == xVrTope) {

                    //
                    if (xVrBaseRetencion <= xVrBase) {

                        //
                        xVrRetencion = xVrBase * (xPorcentajeRetencion / 100);

                    }

                }

                //--- xVrTope100
                if (xIdRteFuenteVrBase == xVrTope100) {

                    //
                    xVrRetencion = xVrBase * (xPorcentajeRetencion / 100);
                }
            }

            System.out.println(" xIdConcepto " + xIdConcepto);
            System.out.println(" xPorcentajeRetencion " + xPorcentajeRetencion);
            System.out.println(" xIdAutoRetenedor " + xIdAutoRetenedor);
            System.out.println(" xIdRteFuenteVrBase " + xIdRteFuenteVrBase);
            System.out.println(" xVrRetencion " + xVrRetencion);
            System.out.println(" xVrBase " + xVrBase);
            System.out.println(" xVrBaseRetencion " + xVrBaseRetencion);
        } catch (NamingException ne) {
            System.out.println("NamingException in " + nombreClase + " " + ne);
        } catch (SQLException sqle) {

            System.out.println("SQLException in" + nombreClase);

            String sqlMessage = sqle.getMessage();
            String sqlState = sqle.getSQLState();
            int vendorCode = sqle.getErrorCode();
            System.out.println("Ocurrio una  excepcion");
            System.out.println("Mensaje " + sqlMessage);
            System.out.println("SQL State " + sqlState);
            System.out.println("Vendor Code " + vendorCode);

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {

            // Cierra la conexion
            jdbcAccess.cleanup(connection, selectStatement, null);
            return xVrRetencion;

        }
    }

    public boolean actualizaRetencionPedido() {
        String sql = "UPDATE tblDctosOrdenes SET idTipoTx = " + getIdTipoOrdenAlcance() + " WHERE numeroOrden = " + getNumeroOrden() + " AND idtipoorden = 59 ";
        return ejecutarSentencia(sql);
    }

    public void getOTPedido() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getIdConceptoRetNota(int idOrdenFactura) {
        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();
        int IdConcepto = 0;
        Connection c = null;
        PreparedStatement ps = null;
        //Busca el tipo de retencion empleada 
        String sql = "SELECT idTipoTx FROM tblDctosOrdenes WHERE idOrden = (SELECT idOrdenOrigen  FROM tblDctosOrdenesDetalle WHERE idOrden = " + idOrdenFactura + " AND item = 1 );";
        try {
            c = this.jdbcAccess.getConnection();
            ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                IdConcepto = rs.getInt("idTipoTx");
            }
            rs.close();
            return IdConcepto;
        } catch (NamingException ne) {
            System.out.println("NamingException in " + nombreClase + " " + ne);
            return IdConcepto;
        } catch (SQLException sqle) {
            reportarExcepcion(sqle, nombreClase);
            int i = IdConcepto;
            return IdConcepto;
        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
            return IdConcepto;
        } finally {
            this.jdbcAccess.cleanup(c, ps, null);
            return IdConcepto;
        }
    }

}
