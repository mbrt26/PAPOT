package com.solucionesweb.losbeans.negocio;

import com.solucionesweb.lasayudas.JDBCAccess;
import com.solucionesweb.losbeans.fachada.FachadaTerceroClaseBean;
import com.solucionesweb.losbeans.utilidades.IConstantes;
import java.io.PrintStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;

public class TerceroClaseBean
        extends FachadaTerceroClaseBean
        implements Serializable, IConstantes {

    private static final String DATA_SOURCE_NAME = "CommerceAccess";
    private JDBCAccess jdbcAccess;

    public TerceroClaseBean() {
        this.jdbcAccess = new JDBCAccess("CommerceAccess");
    }

    public ArrayList listaTerceroClase(int xIdEstado, int xIdTipoTercero) {
        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();

        Connection connection = null;

        ArrayList contenedor = new ArrayList();

        String selectStr = " SELECT  idClase    "
                + ",nombreClase                 "
                + ",idEstado                    "
                + ",idSeq                       "
                + "FROM    tblTerceroClase      "
                + "WHERE   idEstado =           " 
                + xIdEstado + "                 " 
                + " AND idTipoTercero =" 
                + xIdTipoTercero;

        PreparedStatement selectStatement = null;
        try {
            connection = this.jdbcAccess.getConnection();

            selectStatement = connection.prepareStatement(selectStr);

            ResultSet rs = selectStatement.executeQuery();
            while (rs.next()) {
                FachadaTerceroClaseBean fachadaTerceroClaseBean = new FachadaTerceroClaseBean();

                fachadaTerceroClaseBean.setNombreClase(rs.getString("nombreClase"));

                fachadaTerceroClaseBean.setIdClase(rs.getInt("idClase"));

                contenedor.add(fachadaTerceroClaseBean);
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
            return contenedor;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {

            // Cierra la conexion
            jdbcAccess.cleanup(connection, selectStatement, null);
            return contenedor;

        }
    }
}
