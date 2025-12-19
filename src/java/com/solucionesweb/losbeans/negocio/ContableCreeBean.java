package com.solucionesweb.losbeans.negocio;

import com.solucionesweb.lasayudas.JDBCAccess;
import com.solucionesweb.losbeans.fachada.FachadaContableCree;
import java.io.PrintStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.naming.NamingException;

public class ContableCreeBean
        extends FachadaContableCree
        implements Serializable {

    private static final String DATA_SOURCE_NAME = "CommerceAccess";
    private JDBCAccess jdbcAccess;

    public ContableCreeBean() {
        this.jdbcAccess = new JDBCAccess("CommerceAccess");
    }

    public Vector listaAll() {
        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();

        Vector contenedor = new Vector();
        Connection connection = null;

        String selectStr
                = " SELECT tblContableCree.idRteCree                  "
                + ",tblContableCree.nombreRteCree            "
                + ",tblContableCree.porcentajeRteCree        "
                + ",tblContableCree.vrBaseRteCree            "
                + ",tblContableCree.estado                   "
                + ",tblContableCree.idSeq              "
                + "FROM tblContableCree                      "
                + "ORDER BY tblContableCree.idRteCree ";

        PreparedStatement selectStatement = null;
        try {
            connection = this.jdbcAccess.getConnection();

            selectStatement = connection.prepareStatement(selectStr);

            ResultSet rs = selectStatement.executeQuery();
            while (rs.next()) {
                FachadaContableCree fachadaBean = new FachadaContableCree();

                fachadaBean.setIdRteCree(rs.getInt("idRteCree"));
                fachadaBean.setNombreRteCree(rs.getString("nombreRteCree").trim());
                fachadaBean.setPorcentajeRteCree(rs.getDouble("porcentajeRteCree"));
                fachadaBean.setVrBaseRteCree(rs.getDouble("vrBaseRteCree"));
                fachadaBean.setEstado(rs.getInt("estado"));
                fachadaBean.setIdSeq(rs.getInt("idSeq"));

                contenedor.add(fachadaBean);
            }
            rs.close();
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
            return contenedor;
        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {

            // Cierra la conexion
            jdbcAccess.cleanup(connection, selectStatement, null);
            return contenedor;

        }
    }

    public Vector listaUn() {
        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();

        Vector contenedor = new Vector();
        Connection connection = null;

        String selectStr
                = " SELECT tblContableCree.idRteCree                  "
                + ",tblContableCree.nombreRteCree            "
                + ",tblContableCree.porcentajeRteCree        "
                + ",tblContableCree.vrBaseRteCree            "
                + ",tblContableCree.estado                "
                + ",tblContableCree.idSeq           "
                + "FROM tblContableCree                   "
                + "WHERE tblContableCree.idRteCree      =   " + getIdRteCree();

        PreparedStatement selectStatement = null;
        try {
            connection = this.jdbcAccess.getConnection();

            selectStatement = connection.prepareStatement(selectStr);

            ResultSet rs = selectStatement.executeQuery();
            while (rs.next()) {
                FachadaContableCree fachadaBean = new FachadaContableCree();

                fachadaBean.setIdRteCree(rs.getInt("idRteCree"));
                fachadaBean.setNombreRteCree(rs.getString("nombreRteCree"));
                fachadaBean.setPorcentajeRteCree(rs.getDouble("porcentajeRteCree"));
                fachadaBean.setVrBaseRteCree(rs.getDouble("vrBaseRteCree"));
                fachadaBean.setEstado(rs.getInt("estado"));
                fachadaBean.setIdSeq(rs.getInt("idSeq"));

                contenedor.add(fachadaBean);
            }
            rs.close();
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
            return contenedor;
        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {

            // Cierra la conexion
            jdbcAccess.cleanup(connection, selectStatement, null);
            return contenedor;

        }
    }

    public FachadaContableCree listaUnFCH() {
        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();

        FachadaContableCree fachadaBean = new FachadaContableCree();

        Connection connection = null;

        String selectStr
                = " SELECT tblContableCree.idRteCree                  "
                + ",tblContableCree.nombreRteCree            "
                + ",tblContableCree.porcentajeRteCree        "
                + ",tblContableCree.vrBaseRteCree            "
                + ",tblContableCree.estado                "
                + ",tblContableCree.idSeq           "
                + "FROM tblContableCree                   "
                + "WHERE tblContableCree.idRteCree      =   "
                + getIdRteCree() + "";

        PreparedStatement selectStatement = null;
        try {
            connection = this.jdbcAccess.getConnection();

            selectStatement = connection.prepareStatement(selectStr);

            ResultSet rs = selectStatement.executeQuery();
            if (rs.next()) {
                fachadaBean.setIdRteCree(rs.getInt("idRteCree"));
                fachadaBean.setNombreRteCree(rs.getString("nombreRteCree"));
                fachadaBean.setPorcentajeRteCree(rs.getDouble("porcentajeRteCree"));
                fachadaBean.setVrBaseRteCree(rs.getDouble("vrBaseRteCree"));
                fachadaBean.setEstado(rs.getInt("estado"));
                fachadaBean.setIdSeq(rs.getInt("idSeq"));
            }
            // Cierra el Resultset
            rs.close();

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
            return fachadaBean;
        }
    }

    public boolean actualiza() {
        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr
                = " UPDATE tblContableCree     "
                + "SET porcentajeRteCree = "
                + getPorcentajeRteCree() + "      "
                + ",vrBaseRteCree   = "
                + getVrBaseRteCree() + " "
                + "WHERE idRteCree       = "
                + getIdRteCree();

        PreparedStatement selectStatement = null;
        try {
            connection = this.jdbcAccess.getConnection();

            selectStatement = connection.prepareStatement(selectStr);

            selectStatement.execute();
            okIngresar = true;
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

            // Cierra el connection
            jdbcAccess.cleanup(connection, selectStatement, null);
            return okIngresar;
        }
    }

    public double calculaRetencionCree(int xIdTipoTercero, double xVrBase, String xIdCliente) {
        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();

        double xPorcentajeRteCree = 0.0D;
        double xVrBaseRteCree = 0.0D;
        int xVrRteCree = 0;

        Connection connection = null;

        String selectStr
                = " SELECT tblContableCree.porcentajeRteCree,        "
                + "tblContableCree.vrBaseRteCree        "
                + "FROM   tblTerceros                        "
                + "INNER JOIN tblContableCree                "
                + "ON tblTerceros.idRteCree      =                   "
                + "tblContableCree.idRteCree         "
                + "AND tblTerceros.idTipoTercero =          "
                + xIdTipoTercero + "                         "
                + " AND tblTerceros.idCliente     =         '"
                + xIdCliente + "'";

        PreparedStatement selectStatement = null;
        try {
            connection = this.jdbcAccess.getConnection();

            selectStatement = connection.prepareStatement(selectStr);

            ResultSet rs = selectStatement.executeQuery();
            if (rs.next()) {
                xPorcentajeRteCree = rs.getDouble("porcentajeRteCree");
                xVrBaseRteCree = rs.getDouble("vrBaseRteCree");
                if (xVrBase >= xVrBaseRteCree) {
                    xVrRteCree = (int) (xVrBase * (xPorcentajeRteCree / 100.0D));
                }
            }
            rs.close();
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

            // Cierra el connection
            jdbcAccess.cleanup(connection, selectStatement, null);
            return xVrRteCree;
        }
    }

    public double calculaRetencionCreeLocal(int xIdLocal, double xVrBase) {
        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();

        double xPorcentajeRteCree = 0.0D;
        double xVrBaseRteCree = 0.0D;
        int xVrRteCree = 0;

        Connection connection = null;

        String selectStr
                = " SELECT tblContableCree.porcentajeRteCree,          "
                + "tblContableCree.vrBaseRteCree      "
                + "FROM   tblLocales                         "
                + "INNER JOIN tblContableCree                "
                + "ON tblLocales.idRteCree  =                              "
                + "tblContableCree.idRteCree   "
                + "WHERE tblLocales.idLocal =               "
                + xIdLocal + " ";

        PreparedStatement selectStatement = null;
        try {
            connection = this.jdbcAccess.getConnection();

            selectStatement = connection.prepareStatement(selectStr);

            ResultSet rs = selectStatement.executeQuery();
            if (rs.next()) {
                xPorcentajeRteCree = rs.getDouble("porcentajeRteCree");
                xVrBaseRteCree = rs.getDouble("vrBaseRteCree");
                if (xVrBase >= xVrBaseRteCree) {
                    xVrRteCree = (int) (xVrBase * (xPorcentajeRteCree / 100.0D));
                }
            }
            rs.close();
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

            // Cierra el connection
            jdbcAccess.cleanup(connection, selectStatement, null);
            return xVrRteCree;
        }
    }
}
