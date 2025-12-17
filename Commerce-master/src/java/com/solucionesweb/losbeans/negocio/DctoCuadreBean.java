package com.solucionesweb.losbeans.negocio;

// Importa la clase helper JDBCAccess que contiene los accesos a la base de datos
import com.solucionesweb.lasayudas.JDBCAccess;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaDctoCuadre;

// Importa los paquetes de java
import java.sql.*;
import java.io.*;
import java.util.*;
import java.io.IOException.*;

//
import org.kxml.*;
import org.kxml.parser.*;

// Importa los paquetes de javax
import javax.naming.*;

public class DctoCuadreBean extends FachadaDctoCuadre
                                                       implements Serializable {

  	// Nombre JNDI del Data Source que esta clase requiere
    private static final String DATA_SOURCE_NAME = "CommerceAccess";

	// Clase helper usada para acceder a la base de datos
	private JDBCAccess jdbcAccess;

	// Metodo constructor por defecto sin parametros
    public DctoCuadreBean() {

  	// Al instanciar el Bean establece la conexion a la base de datos
	   jdbcAccess = new JDBCAccess(DATA_SOURCE_NAME);
    }

    // Metodo
    public FachadaDctoCuadre listaSaldoInicial() {

      //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
      Class tipoObjeto       = this.getClass();
      String nombreClase     = tipoObjeto.getName();

      FachadaDctoCuadre fachadaBean         = new FachadaDctoCuadre();

      Connection connection  = null;

      String selectStr =
"SELECT  saldoFinal AS vrDcto                  "+
"FROM tblDctosCuadre                           "+
"WHERE fechaCuadre = DATEADD(dd,-1,'"+getFechaCuadreSqlServer()+"') "+
"AND indicador ="+getIndicador()+"             ";

      PreparedStatement selectStatement = null;

      try {

        // Obtiene una conexion a la base de datos, requiere Try
        connection = jdbcAccess.getConnection();

        // Prepara la sentencia de Busqueda
        selectStatement = connection.prepareStatement(selectStr);

        // Obtiene el resultset
        ResultSet rs = selectStatement.executeQuery();

        if (rs.next()) {

              fachadaBean = new FachadaDctoCuadre();

              fachadaBean.setSaldoInicial(rs.getInt("vrDcto"));

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

	  }

	  catch (Exception e)
	  {
		System.out.println("Exception In :" + nombreClase + " " + e);
	  }
      finally {
        // Cierra la conexion a la base de datos limpiando la statement usada y la
        jdbcAccess.cleanup(connection, selectStatement,null);
        return fachadaBean;
      }
    }



   // ingresaTercero
    public boolean cierreCaja() {

      //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        //
        boolean okIngresar = false;

        //
        Connection connection = null;


      String selectStr =
          "INSERT INTO [BDCommerce].[dbo].[tblDctosCuadre]     "+
"           ([idLocal]                                         "+
"           ,[idCuadre]                                        "+
"           ,[fechaCuadre]                                     "+
"           ,[saldoInicial]                                    "+
"           ,[vrIngreso]                                       "+
"           ,[vrEgreso]                                        "+
"           ,[saldoFinal]                                      "+
"           ,[idUsuario]                                       "+
"           ,[estadoCuadre]                                    "+
"           ,[estado]                                          "+
"           ,[indicador]                                       "+
"           ,[fechaOperacion])                                 "+
"          SELECT                                              "+
getIdLocal()+"            AS idLocal,                          "+
"              SUM(tmpCuadre.idCuadre) AS idCuadre,            "+
"           '"+getFechaCuadreSqlServer()+"' AS fechaCuadre,    "+
"              SUM (tmpCuadre.saldoInicial) AS saldoInicial,   "+
"              SUM (tmpCuadre.vrIngreso) AS vrIngreso,         "+
"              SUM (tmpCuadre.vrEgreso) AS vrEgreso,           "+
"              SUM (tmpCuadre.saldoFinal) AS saldoFinal,       "+
getIdUsuario()+"          AS idUsuario,                        "+
"              1 AS estadoCuadre,                              "+
"              1 AS estado,                                    "+
getIndicador()+"   AS indicador,                               "+
" '"+getFechaOperacionSqlServer()+"' AS fechaOperacion         "+
"FROM (                                                        "+
"SELECT           SUM(tblPagos.vrPago) AS vrIngreso,           "+
"                 0  AS vrEgreso,                              "+
"                 SUM(tblPagos.vrPago)                         "+
"                                     AS saldoFinal,           "+
"                 0 AS saldoInicial,                           "+
"                 0 AS idCuadre                                "+
" FROM   tblPagos                                              "+
" WHERE EXISTS (                                               "+
"    SELECT tblPagosMedios.*                                   "+
"    FROM tblPagosMedios                                       "+
"    WHERE tblPagos.idLocal              =                     "+
"                     tblPagosMedios.idLocal                   "+
"    AND tblPagos.idTipoOrden            =                     "+
"                 tblPagosMedios.idTipoOrden                   "+
"    AND tblPagos.idRecibo               =                     "+
"                    tblPagosMedios.idRecibo                   "+
"    AND tblPagos.idLog                  =                     "+
"                       tblPagosMedios.idLog                   "+
"    AND tblPagos.indicador              =                     "+
"                   tblPagosMedios.indicador)                  "+
"                                                              "+
"AND   tblPagos.idLocal  =                                     "+
                             getIdLocal()+"                    "+
"AND tblPagos.idTipoOrden IN (9,29)                            "+
"AND tblPagos.indicador =                                      "+
                             getIndicador()+"                  "+
"                AND tblPagos.fechaPago =                     '"+
getFechaCuadreSqlServer()+"'                                   "+
"GROUP BY tblPagos.idLocal                                     "+
"UNION                                                         "+
"SELECT           0 AS vrIngreso,                              "+
"                 tblPagos.vrPago AS vrEgreso,                 "+
"                 tblPagos.vrPago * (-1) AS saldoFinal,        "+
"                 0 AS saldoInicial,                           "+
"                 0 AS idCuadre                                "+
" FROM   tblPagos                                              "+
" WHERE EXISTS (                                               "+
"    SELECT tblPagosMedios.*                                   "+
"    FROM tblPagosMedios                                       "+
"    WHERE tblPagos.idLocal              =                     "+
"                     tblPagosMedios.idLocal                   "+
"    AND tblPagos.idTipoOrden            =                     "+
"                 tblPagosMedios.idTipoOrden                   "+
"    AND tblPagos.idRecibo               =                     "+
"                    tblPagosMedios.idRecibo                   "+
"    AND tblPagos.idLog                  =                     "+
"                       tblPagosMedios.idLog                   "+
"    AND tblPagos.indicador              =                     "+
"                   tblPagosMedios.indicador)                  "+
"AND   tblPagos.idLocal  =                                     "+
                           getIdLocal()+"                      "+
"AND tblPagos.idTipoOrden IN (1,21)                            "+
"AND tblPagos.indicador =                                      "+
                             getIndicador()+"                  "+
"                AND tblPagos.fechaPago =                     '"+
getFechaCuadreSqlServer()+"'                                   "+
"UNION                                                         "+
"SELECT    (CASE WHEN                                          "+
"                 tblTipoOrden.idAlcance < 5                   "+
"           THEN  tblDctos.vrBase                              "+
"           ELSE  0                                            "+
"         END) AS vrIngreso,                                   "+
"          (CASE WHEN                                          "+
"                 tblTipoOrden.idAlcance < 5                   "+
"           THEN  0                                            "+
"           ELSE  tblDctos.vrBase                              "+
"         END) AS vrEgreso,                                    "+
"          (CASE WHEN                                          "+
"                 tblTipoOrden.idAlcance < 5                   "+
"           THEN  tblDctos.vrBase                              "+
"           ELSE  tblDctos.vrBase * (-1)                       "+
"         END) AS saldoFinal,                                  "+
"         0 AS saldoInicial,                                   "+
"         0 AS idCuadre                                        "+
"FROM       tblDctos                                           "+
"INNER JOIN tblTipoOrden                                       "+
"ON tblDctos.IDTIPOORDEN                    =                  "+
"                      tblTipoOrden.idTipoOrden                "+
"WHERE     EXISTS                                              "+
" (SELECT  *                                                   "+
"  FROM    tblDctosOrdenesDetalle                              "+
"  WHERE tblDctosOrdenesDetalle.idLocal     =                  "+
"                              tblDctos.IDLOCAL                "+
"  AND   tblDctosOrdenesDetalle.idTipoOrden =                  "+
"                          tblDctos.idTipoOrden                "+
"  AND   tblDctosOrdenesDetalle.idOrden     =                  "+
"                             tblDctos.idOrden)                "+
"AND tblTipoOrden.idAlcance             IN (4,5)               "+
"AND   tblDctos.IDLOCAL   =                                    "+
                             getIdLocal()+"                    "+
"AND tblDctos.indicador =                                      "+
                             getIndicador()+"                  "+
"AND tblDctos.fechaDcto =                                     '"+
getFechaCuadreSqlServer()+"'                                   "+
"UNION                                                         "+
"SELECT      0 AS vrIngreso,                                   "+
"            0 AS vrEgreso,                                    "+
"            0 AS saldoFinal,                                  "+
"            0 AS saldoInicial,                                "+
"            MAX(tblDctosCuadre.idCuadre)+1 AS idCuadre        "+
"FROM tblDctosCuadre                                           "+
"UNION                                                         "+
"SELECT      0 AS vrIngreso,                                   "+
"            0 AS vrEgreso,                                    "+
"            tblDctosCuadre.saldoFinal AS saldoFinal,          "+
"            tblDctosCuadre.saldoFinal AS saldoInicial,        "+
"            0 AS idCuadre                                     "+
"FROM tblDctosCuadre                                           "+
"WHERE  tblDctosCuadre.fechaCuadre =  DATEADD(dd,-1,'"+getFechaCuadreSqlServer()+"')"+
"AND tblDctosCuadre.indicador =                                "+
                             getIndicador()+"                  "+
") AS tmpCuadre                                                ";

      PreparedStatement selectStatement = null;

      try {

        // Obtiene una conexion a la base de datos, requiere Try
        connection = jdbcAccess.getConnection();

        // Prepara la sentencia de Busqueda
        selectStatement = connection.prepareStatement(selectStr);

        // Obtiene el resultset
        selectStatement.execute();
        okIngresar = true;

      }
      catch (NamingException ne) {
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

            // Cierra la conexion a la base de datos limpiando la statement usada y la
            jdbcAccess.cleanup(connection, selectStatement, null);
            return okIngresar;
        }
    }


        // Metodo
    public FachadaDctoCuadre estadoCuadre() {

      //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
      Class tipoObjeto       = this.getClass();
      String nombreClase     = tipoObjeto.getName();

      FachadaDctoCuadre fachadaBean         = new FachadaDctoCuadre();

      Connection connection  = null;

      String selectStr =
"SELECT estadoCuadre            "+
"  FROM tblDctosCuadre          "+
"WHERE fechaCuadre = '"+getFechaComprobanteSqlServer()+"' "+
"AND indicador = "+getIndicador()+" ";

      PreparedStatement selectStatement = null;

      try {

        // Obtiene una conexion a la base de datos, requiere Try
        connection = jdbcAccess.getConnection();

        // Prepara la sentencia de Busqueda
        selectStatement = connection.prepareStatement(selectStr);

        // Obtiene el resultset
        ResultSet rs = selectStatement.executeQuery();

        if (rs.next()) {

              fachadaBean = new FachadaDctoCuadre();

              fachadaBean.setEstadoCuadre(rs.getInt("estadoCuadre"));

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

	  }

	  catch (Exception e)
	  {
		System.out.println("Exception In :" + nombreClase + " " + e);
	  }
      finally {
        // Cierra la conexion a la base de datos limpiando la statement usada y la
        jdbcAccess.cleanup(connection, selectStatement,null);
        return fachadaBean;
      }
    }

 public boolean aperturaCaja() {

      //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
      Class tipoObjeto       = this.getClass();
      String nombreClase     = tipoObjeto.getName();
      boolean okIngresar     = false;

      Connection connection  = null;

      String selectStr =
      "UPDATE tblDctosCuadre          "+
      "   SET estadoCuadre = 2        "+
      "WHERE fechaCuadre = '"+getFechaCuadreSqlServer()+"' "+
      "AND indicador = "+getIndicador()+"    ";

      PreparedStatement selectStatement = null;

      try {

        // Obtiene una conexion a la base de datos, requiere Try
        connection = jdbcAccess.getConnection();

        // Prepara la sentencia de Busqueda
        selectStatement = connection.prepareStatement(selectStr);

        // Obtiene el resultset
        selectStatement.execute();
        okIngresar = true;

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
		return okIngresar;

	  }

	  catch (Exception e)
	  {
		System.out.println("Exception In :" + nombreClase + " " + e);
	  }
      finally {
        // Cierra la conexion a la base de datos limpiando la statement usada y la
        jdbcAccess.cleanup(connection, selectStatement,null);
        return okIngresar;
      }
    }

  public boolean actualizaCaja() {

      //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
      Class tipoObjeto       = this.getClass();
      String nombreClase     = tipoObjeto.getName();
      boolean okIngresar     = false;

      Connection connection  = null;

      String selectStr =
"   UPDATE tblDctosCuadre                                        "+
"   SET saldoInicial = tmpCierre.saldoInicial                    "+
"      ,vrIngreso =  tmpCierre.vrIngreso                         "+
"      ,vrEgreso = tmpCierre.vrEgreso                            "+
"      ,saldoFinal = tmpCierre.saldoFinal                        "+
"      ,estadoCuadre = 1                                         "+
"      ,fechaOperacion = '"+getFechaOperacionSqlServer()+"'      "+
"FROM (                                                          "+
"                                                                "+
"SELECT  SUM (tmpCuadre.saldoInicial) AS saldoInicial            "+
"       ,SUM (tmpCuadre.vrIngreso) AS vrIngreso                  "+
"       ,SUM (tmpCuadre.vrEgreso) AS vrEgreso                    "+
"       ,SUM (tmpCuadre.saldoFinal) AS saldoFinal                "+
"FROM(                                                           "+
"     SELECT           SUM(tblPagos.vrPago) AS vrIngreso,        "+
"                 0  AS vrEgreso,                                "+
"                 SUM(tblPagos.vrPago)                           "+
"                                     AS saldoFinal,             "+
"                 0 AS saldoInicial                              "+
" FROM   tblPagos                                                "+
" WHERE EXISTS (                                                 "+
"    SELECT tblPagosMedios.*                                     "+
"    FROM tblPagosMedios                                         "+
"    WHERE tblPagos.idLocal              =                       "+
"                     tblPagosMedios.idLocal                     "+
"    AND tblPagos.idTipoOrden            =                       "+
"                 tblPagosMedios.idTipoOrden                     "+
"    AND tblPagos.idRecibo               =                       "+
"                    tblPagosMedios.idRecibo                     "+
"    AND tblPagos.idLog                  =                       "+
"                       tblPagosMedios.idLog                     "+
"    AND tblPagos.indicador              =                       "+
"                   tblPagosMedios.indicador)                    "+
"AND   tblPagos.idLocal  = "+getIdLocal()+"                      "+
"AND tblPagos.idTipoOrden IN (9,29)                              "+
"AND tblPagos.indicador = "+getIndicador()+"                     "+
"AND tblPagos.fechaPago = '"+getFechaCuadreSqlServer()+"'        "+
"GROUP BY tblPagos.idLocal                                       "+
"UNION                                                           "+
"SELECT           0 AS vrIngreso,                                "+
"                 tblPagos.vrPago AS vrEgreso,                   "+
"                 tblPagos.vrPago * (-1) AS saldoFinal,          "+
"                 0 AS saldoInicial                              "+
" FROM   tblPagos                                                "+
" WHERE EXISTS (                                                 "+
"    SELECT tblPagosMedios.*                                     "+
"    FROM tblPagosMedios                                         "+
"    WHERE tblPagos.idLocal              =                       "+
"                     tblPagosMedios.idLocal                     "+
"    AND tblPagos.idTipoOrden            =                       "+
"                 tblPagosMedios.idTipoOrden                     "+
"    AND tblPagos.idRecibo               =                       "+
"                    tblPagosMedios.idRecibo                     "+
"    AND tblPagos.idLog                  =                       "+
"                       tblPagosMedios.idLog                     "+
"    AND tblPagos.indicador              =                       "+
"                   tblPagosMedios.indicador)                    "+
"AND   tblPagos.idLocal  =  "+getIdLocal()+"                     "+
"AND tblPagos.idTipoOrden IN (1,21)                              "+
"AND tblPagos.indicador = "+getIndicador()+"                     "+
"AND tblPagos.fechaPago = '"+getFechaCuadreSqlServer()+"'        "+
"UNION                                                           "+
"SELECT    (CASE WHEN                                            "+
"                 tblTipoOrden.idAlcance < 5                     "+
"           THEN  tblDctos.vrBase                                "+
"           ELSE  0                                              "+
"         END) AS vrIngreso,                                     "+
"          (CASE WHEN                                            "+
"                 tblTipoOrden.idAlcance < 5                     "+
"           THEN  0                                              "+
"           ELSE  tblDctos.vrBase                                "+
"         END) AS vrEgreso,                                      "+
"          (CASE WHEN                                            "+
"                 tblTipoOrden.idAlcance < 5                     "+
"           THEN  tblDctos.vrBase                                "+
"           ELSE  tblDctos.vrBase * (-1)                         "+
"         END) AS saldoFinal,                                    "+
"         0 AS saldoInicial                                      "+
"FROM       tblDctos                                             "+
"INNER JOIN tblTipoOrden                                         "+
"ON tblDctos.IDTIPOORDEN                    =                    "+
"                      tblTipoOrden.idTipoOrden                  "+
"WHERE     EXISTS                                                "+
" (SELECT  *                                                     "+
"  FROM    tblDctosOrdenesDetalle                                "+
"  WHERE tblDctosOrdenesDetalle.idLocal     =                    "+
"                              tblDctos.IDLOCAL                  "+
"  AND   tblDctosOrdenesDetalle.idTipoOrden =                    "+
"                          tblDctos.idTipoOrden                  "+
"  AND   tblDctosOrdenesDetalle.idOrden     =                    "+
"                             tblDctos.idOrden)                  "+
"AND tblTipoOrden.idAlcance             IN (4,5)                 "+
"AND   tblDctos.IDLOCAL   =  "+getIdLocal()+"                    "+
"AND tblDctos.indicador =    "+getIndicador()+"                  "+
"AND tblDctos.fechaDcto =    '"+getFechaCuadreSqlServer()+"'     "+
"UNION                                                           "+
"SELECT      0 AS vrIngreso,                                     "+
"            0 AS vrEgreso,                                      "+
"            tblDctosCuadre.saldoFinal AS saldoFinal,            "+
"            tblDctosCuadre.saldoFinal AS saldoInicial           "+
"FROM tblDctosCuadre                                             "+
"WHERE  tblDctosCuadre.fechaCuadre =  DATEADD(dd,-1,'"+getFechaCuadreSqlServer()+"')"+
"AND tblDctosCuadre.indicador = "+getIndicador()+"               "+
") AS tmpCuadre                                                  "+
") AS tmpCierre                                                  "+
"WHERE tblDctosCuadre.fechaCuadre='"+getFechaCuadreSqlServer()+"'"+
"AND tblDctosCuadre.indicador = "+getIndicador()+"               ";

      PreparedStatement selectStatement = null;

      try {

        // Obtiene una conexion a la base de datos, requiere Try
        connection = jdbcAccess.getConnection();

        // Prepara la sentencia de Busqueda
        selectStatement = connection.prepareStatement(selectStr);

        // Obtiene el resultset
        selectStatement.execute();
        okIngresar = true;

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
		return okIngresar;

	  }

	  catch (Exception e)
	  {
		System.out.println("Exception In :" + nombreClase + " " + e);
	  }
      finally {
        // Cierra la conexion a la base de datos limpiando la statement usada y la
        jdbcAccess.cleanup(connection, selectStatement,null);
        return okIngresar;
      }
    }





}