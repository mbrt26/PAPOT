package com.solucionesweb.losbeans.negocio;

import com.solucionesweb.lasayudas.JDBCAccess;
import com.solucionesweb.losbeans.fachada.FachadaJobEscalaDetalle;
import com.solucionesweb.losbeans.utilidades.IConstantes;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;

public class JobEscalaDetalleBean
        extends FachadaJobEscalaDetalle
        implements Serializable, IConstantes {

    private static final String DATA_SOURCE_NAME = "CommerceAccess";
    private JDBCAccess jdbcAccess;

    public JobEscalaDetalleBean() {
        this.jdbcAccess = new JDBCAccess("CommerceAccess");
    }

    public boolean ingresa() {
        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr = " INSERT INTO              tblJobEscalaDetalle          (idEscala             ,item                 ,nombreItem           ,estado)        VALUES            (" + getIdEscala() + "," + getItem() + ",'" + getNombreItem() + "'," + getEstado() + ")";

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
            this.jdbcAccess.cleanup(connection, selectStatement, null);
        }
        return okIngresar;
    }

    public int maximoItem() {
        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();

        int xMaximoItem = 0;

        Connection connection = null;

        String selectStr = " SELECT MAX(item)               AS maximoItem  FROM                   tblJobEscalaDetalle  WHERE idEscala =     " + getIdEscala();

        PreparedStatement selectStatement = null;
        try {
            connection = this.jdbcAccess.getConnection();

            selectStatement = connection.prepareStatement(selectStr);

            ResultSet rs = selectStatement.executeQuery();
            if (rs.next()) {
                xMaximoItem = rs.getInt("maximoItem");
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
            this.jdbcAccess.cleanup(connection, selectStatement, null);
        }
        return xMaximoItem;
    }

    public FachadaJobEscalaDetalle traeFCH() {
        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();

        FachadaJobEscalaDetalle fachadaJobEscalaDetalle = new FachadaJobEscalaDetalle();

        Connection connection = null;

        String selectStr = " SELECT idEscala,               item,                 nombreItem,           estado         FROM                   tblJobEscalaDetalle  WHERE idEscala =     " + getIdEscala() + "      " + " AND item =           " + getItem();

        PreparedStatement selectStatement = null;
        try {
            connection = this.jdbcAccess.getConnection();

            selectStatement = connection.prepareStatement(selectStr);

            ResultSet rs = selectStatement.executeQuery();
            if (rs.next()) {
                fachadaJobEscalaDetalle.setIdEscala(rs.getInt("idEscala"));
                fachadaJobEscalaDetalle.setItem(rs.getInt("item"));
                fachadaJobEscalaDetalle.setNombreItem(rs.getString("nombreItem"));
                fachadaJobEscalaDetalle.setEstado(rs.getInt("estado"));
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
            this.jdbcAccess.cleanup(connection, selectStatement, null);
        }
        return fachadaJobEscalaDetalle;
    }

    public boolean cambia() {
        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr = " UPDATE tblJobEscalaDetalle     SET nombreItem =     '" + getNombreItem() + "'       " + "       ,estado =          " + getEstado() + "            " + "  WHERE idEscala =        " + getIdEscala() + "          " + "  AND   item =            " + getItem();

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
            this.jdbcAccess.cleanup(connection, selectStatement, null);
        }
        return okIngresar;
    }
}
