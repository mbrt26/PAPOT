/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.solucionesweb.losbeans.colaboraciones;

import com.solucionesweb.lasayudas.JDBCAccess;
import com.solucionesweb.losbeans.fachada.FachadaAuditoriaBean;
import com.solucionesweb.losbeans.fachada.FachadaDctoAuditoria;
import com.solucionesweb.losbeans.utilidades.IConstantes;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;

/**
 *
 * @author commerce
 */
public class ColaboraDctoAuditoriaBean extends FachadaDctoAuditoria
                                       implements Serializable, IConstantes {

    private int idLocal;
    private int idTipoOrden;
    private double idDcto;
    private int idOrden;
    private int idUsuarioResponsable;
    private int idAuditoria;
    private String fechaAuditoria;
    private int estado;
    private String ipTx;

       // Nombre JNDI del Data Source que esta clase requiere
    private static final String DATA_SOURCE_NAME = "CommerceAccess";

    // Clase helper usada para acceder a la base de datos
    private JDBCAccess jdbcAccess;

    // Metodo constructor por defecto sin parametros
    public ColaboraDctoAuditoriaBean() {

        // Al instanciar el Bean establece la conexion a la base de datos
        jdbcAccess = new JDBCAccess(DATA_SOURCE_NAME);
    }

        // Metodo
    public int reimpresion() {

      //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
      Class tipoObjeto       = this.getClass();
      String nombreClase     = tipoObjeto.getName();
      int  reimpresion      = 0;


      Connection connection  = null;

      //
      String selectStr =

            " SELECT                                     " +
            "       COUNT(tblDctosAuditoria.IDORDEN)     " +
            " 				AS reimpresion   " +
            " FROM tblDctosAuditoria                     " +
            " WHERE tblDctosAuditoria.IDLOCAL =          " +
              getIdLocal() +
            " AND   tblDctosAuditoria.IDTIPOORDEN =      " +
              getIdTipoOrden() +
            " AND	  tblDctosAuditoria.IDORDEN =    " +
              getIdOrden() ;

      PreparedStatement selectStatement = null;

      try {

        // Obtiene una conexion a la base de datos, requiere Try
        connection = jdbcAccess.getConnection();

        // Prepara la sentencia de Busqueda
        selectStatement = connection.prepareStatement(selectStr);

        // Obtiene el resultset
        ResultSet rs = selectStatement.executeQuery();

        if (rs.next()) {

            //
            reimpresion =  rs.getInt("reimpresion");

        }

        // Cierra el Resultset
        rs.close();

      }
      catch(NamingException ne)
      {
      	System.out.println("NamingException in " + nombreClase + " " + ne);
      }
      catch (SQLException sqle) {

		System.out.println("SQLException in" + nombreClase);

		String sqlMessage = sqle.getMessage();
        String sqlState = sqle.getSQLState();
        int vendorCode = sqle.getErrorCode();
		System.out.println("Ocurrio una  excepcion");
		System.out.println("Mensaje " + sqlMessage);
		System.out.println("SQL State " + sqlState);
		System.out.println("Vendor Code " + vendorCode);
        return reimpresion;

	  }

	  catch (Exception e)
	  {
		System.out.println("Exception In :" + nombreClase + " " + e);
	  }
      finally {

        // Cierra la conexion
        jdbcAccess.cleanup(connection, selectStatement,null);
        return reimpresion;

      }
    }

    //Reimpresion
    public FachadaAuditoriaBean listaReimpresionAuditoria() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        // Variable de fachada de los datos
        FachadaAuditoriaBean fachadaAuditoriaBean = new FachadaAuditoriaBean();

        Connection connection = null;

        String selectStr =

                " SELECT     TOP 1                                    " +
                " 		   tblDctosAuditoria.IDORDEN,         " +
                " 		   tblAuditoria.idAuditoria,          " +
                " 		   tblAuditoria.nombreAuditoria,      " +
                " 		   tblAuditoria.estado                " +
                " FROM       tblAuditoria INNER JOIN                  " +
                "            tblDctosAuditoria ON                     " +
                " 		   tblAuditoria.idAuditoria = 	      " +
                "			tblDctosAuditoria.idAuditoria " +
                " WHERE      tblDctosAuditoria.IDORDEN =              " +
                  getIdOrden()  +
                " AND      tblDctosAuditoria.IDTIPOORDEN =            " +
                  getIdTipoOrden() +
                " AND      tblDctosAuditoria.IDLOCAL =                " +
                  getIdLocal() ;

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
                fachadaAuditoriaBean.setIdAuditoria(rs.getInt("idAuditoria"));
                fachadaAuditoriaBean.setNombreAuditoria(rs.getString(
                                                            "nombreAuditoria"));
               
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

            return fachadaAuditoriaBean;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {

            // Cierra la conexion a la base de datos limpiando la statement usada y la
            jdbcAccess.cleanup(connection, selectStatement, null);

            return fachadaAuditoriaBean;
        }
    }




}
