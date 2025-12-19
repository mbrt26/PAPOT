/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.linxsi.modelo.cliente.cotizacion2;

import co.linxsi.modelo.cliente.cotizacion.*;
import co.linxsi.modelo.maestro.operaciones.Proceso_Materia_DTO;
import com.solucionesweb.lasayudas.JDBCAccess;
import com.solucionesweb.losbeans.fachada.FachadaPluBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author Edgar J García L
 */
public class DAO_Referencia2 extends DTO_Referencia {

    private static final String DATA_SOURCE_NAME = "CommerceAccess";
    private JDBCAccess pc;

    public DAO_Referencia2() {
        pc = new JDBCAccess(DATA_SOURCE_NAME);

    }

    public boolean actualizaVrCostoPredictPLU() {
        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        String selectStr = "UPDATE tblPlus "
                + "SET tblPlus.vrCostoPrediccion = '" + getCosto() + "' "
                + "WHERE tblPlus.idPlu = " + getIdPlu();
        
        PreparedStatement selectStatement = null;
        try {
            connection = this.pc.getConnection();

            selectStatement = connection.prepareStatement(selectStr);

            selectStatement.execute();
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
            System.out.println("Ocurrio una  excepcion");
            System.out.println("Mensaje " + sqlMessage);
            System.out.println("SQL State " + sqlState);
            System.out.println("Vendor Code " + vendorCode);
            return okIngresar;
        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
            return okIngresar;
        } finally {
            this.pc.cleanup(connection, selectStatement, null);
            return okIngresar;
        }
    }

    public List<DTO_Referencia> getAllByCliente() {

        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();
        List<DTO_Referencia> lista;
        lista = new ArrayList();

        Connection c = null;
        String sql = "SELECT  "
                + "       [idPlu]\n"
                + "      ,[nombreReferencia]\n"
                + "      ,[material]\n"
                + "      ,[estado]\n"
                + "      ,[ancho]\n"
                + "      ,[largo]\n"
                + "      ,[calibre]\n"
                + "      ,[ficha]\n"
                + "      ,[creado]\n"
                + "      ,[referencia]\n"
                + "      ,[pesoMillar]\n"
                + "      ,[flete]\n"
                + "      ,[bache]\n"
                + "      ,[precioUnitario]"
                + "      ,[precioVenta]"
                + "  FROM [dbo].[tblCotClienteRef] "
                + " WHERE estado = 1 "
                + " AND idCliente = " + getIdCliente()
                + " ORDER BY nombreReferencia";

        PreparedStatement ps = null;
        try {
            c = this.pc.getConnection();
            ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DTO_Referencia dto = new DTO_Referencia();
                dto.setIdPlu(rs.getInt("idPlu"));
                dto.setNombreReferencia(rs.getString("nombreReferencia"));
                dto.setAncho(rs.getDouble("ancho"));
                dto.setLargo(rs.getDouble("largo"));
                dto.setCalibre(rs.getDouble("calibre"));
                dto.setPesoMillar(rs.getDouble("pesoMillar"));
                dto.setCreado(rs.getString("creado"));
                dto.setFicha(rs.getInt("ficha"));
                dto.setReferencia(rs.getString("referencia"));
                dto.setFlete(rs.getDouble("flete"));
                dto.setBache(rs.getDouble("bache"));
                dto.setPrecio(rs.getDouble("precioUnitario"));
                dto.setPrecioVenta(rs.getDouble("precioVenta"));
                dto.setMaterial(rs.getString("material"));
                lista.add(dto);
            }
            rs.close();
            return lista;
        } catch (NamingException ne) {
            System.out.println("NamingException in " + nombreClase + " " + ne);
            return lista;
        } catch (SQLException sqle) {
            System.out.println("SQLException in" + nombreClase);
            String sqlMessage = sqle.getMessage();
            String sqlState = sqle.getSQLState();
            int vendorCode = sqle.getErrorCode();
            System.out.println("Ocurrio una  excepcion");
            System.out.println("Mensaje " + sqlMessage);
            System.out.println("SQL State " + sqlState);
            System.out.println("Vendor Code " + vendorCode);
            List localList1 = lista;
            return lista;
        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
            return lista;
        } finally {
            this.pc.cleanup(c, ps, null);
            return lista;
        }
    }

    public List<DTO_Referencia> getRefersByClientsRevenue() {

        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();
        List<DTO_Referencia> lista = new ArrayList();

        String operador = getIdCliente() == 0 ? "<>" : "=";
        Connection c = null;
        final String sql
                = "SELECT\n"
                + "  idFicha, \n"
                + "  idCliente, \n"
                + "  referencia, \n"
                + "  estado, \n"
                + "  referenciaCliente,\n"
                + "  VOLUMEN,\n"
                + "  FACTOR,\n"
                + "  BANDERA,\n"
                + "  (CASE WHEN BANDERA=0 THEN VOLUMEN*FACTOR\n"
                + "  ELSE VOLUMEN END ) AS PESO_MILLAR,\n"
                + "  SUM(CAST(KILO_T1 AS DECIMAL(10, 2))) AS KGT1, \n"
                + "  SUM(CAST(KILO_T2 AS DECIMAL(10, 2))) AS KGT2, \n"
                + "  SUM(CAST(KILO_T3 AS DECIMAL(10, 2))) AS KGT3, \n"
                + "  CASE WHEN (SUM(CAST(TMP.KILO_T1 AS DECIMAL(10, 2)))) > 0 THEN ((SUM(COSTOT1)/(SUM(CAST(TMP.KILO_T1 AS DECIMAL(10, 2)))))+DESP*(SUM(COSTOT1)/(SUM(CAST(TMP.KILO_T1 AS DECIMAL(10, 2)))))) ELSE 0 END AS COSTO_KG_TORN_1, \n"
                + "  CASE WHEN (SUM(CAST(TMP.KILO_T2 AS DECIMAL(10, 2)))) > 0 THEN ((SUM(COSTOT2)/(SUM(CAST(TMP.KILO_T2 AS DECIMAL(10, 2)))))+DESP*(SUM(COSTOT2)/(SUM(CAST(TMP.KILO_T2 AS DECIMAL(10, 2)))))) ELSE 0 END AS COSTO_KG_TORN_2, \n"
                + "  CASE WHEN (SUM(CAST(TMP.KILO_T3 AS DECIMAL(10, 2)))) > 0 THEN ((SUM(COSTOT3)/(SUM(CAST(TMP.KILO_T3 AS DECIMAL(10, 2)))))+DESP*(SUM(COSTOT3)/(SUM(CAST(TMP.KILO_T3 AS DECIMAL(10, 2)))))) ELSE 0 END AS COSTO_KG_TORN_3, \n"
                + " \n"
                + " ((CASE WHEN (SUM(CAST(TMP.KILO_T1 AS DECIMAL(10, 2)))) > 0 THEN ((SUM(COSTOT1)/(SUM(CAST(TMP.KILO_T1 AS DECIMAL(10, 2)))))+DESP*(SUM(COSTOT1)/(SUM(CAST(TMP.KILO_T1 AS DECIMAL(10, 2 )))))) ELSE 0 END) \n"
                + " +(CASE WHEN (SUM(CAST(TMP.KILO_T2 AS DECIMAL(10, 2)))) > 0 THEN ((SUM(COSTOT2)/(SUM(CAST(TMP.KILO_T2 AS DECIMAL(10, 2)))))+DESP*(SUM(COSTOT2)/(SUM(CAST(TMP.KILO_T2 AS DECIMAL(10, 2)))))) ELSE 0 END)\n"
                + " +(CASE WHEN (SUM(CAST(TMP.KILO_T3 AS DECIMAL(10, 2)))) > 0 THEN ((SUM(COSTOT3)/(SUM(CAST(TMP.KILO_T3 AS DECIMAL(10, 2)))))+DESP*(SUM(COSTOT3)/(SUM(CAST(TMP.KILO_T3 AS DECIMAL(10, 2)))))) ELSE 0 END) \n"
                + " + COSTO_PROCESOS) AS TOTAL_COSTO_KG, \n"
                + "  COSTO_PROCESOS, \n"
                + "  ISNULL((SELECT TOP (1) VRVENTAUNITARIO / (1 + PORCENTAJEIVA / 100) AS Expr1  FROM dbo.tblDctosOrdenesDetalle WHERE (IDORDEN = ISNULL((SELECT TOP (1) IDORDEN FROM dbo.tblDctosOrdenes WHERE (idFicha = TMP.idFicha)  AND (IDTIPOORDEN = 59) ORDER BY IDORDEN DESC),0)) AND (IDTIPOORDEN = 59)), 0) AS PRECIO_VENTA, \n"
                + "  ISNULL ((SELECT TOP (1) numeroOrden FROM dbo.tblDctosOrdenes AS tblDctosOrdenes_1  WHERE (idFicha = TMP.idFicha) AND (IDTIPOORDEN = 59) ORDER BY IDORDEN DESC), 0) AS ULTIMO_PEDIDO \n"
                + "FROM \n"
                + "  (SELECT \n"
                + "      FIC.idFicha, \n"
                + "      FIC.idEscala, \n"
                + "      FIC.estado, \n"
                + "      JOBESC.nombreEscala, \n"
                + "      FIC.referencia, \n"
                + "      FIC.item, \n"
                + "      FIC.vrEscala, \n"
                + "      FIC.textoEscala, \n"
                + "      FIC.idCliente, \n"
                + "      FIC.referenciaCliente,\n"
                + "      D.FACTOR,	 \n"
                + "	  ISNULL((SELECT TOP 1 [volumen] FROM [dbo].[tmpVolumenFicha] WHERE idficha=FIC.idficha),0) as VOLUMEN,\n"
                + "	  ISNULL((SELECT TOP 1 [BANDERA] FROM [dbo].[tmpVolumenFicha] WHERE idficha=FIC.idficha),0) as BANDERA,  \n"
                + "      (SELECT vrEscala FROM dbo.tblPlusFicha \n"
                + "        WHERE (idEscala = 511) AND (idOperacion = 2) AND (idFicha = FIC.idFicha)) / 100 AS DESP, \n"
                + "      (SELECT TOTAL FROM dbo.vistaFichaCostoProceso WHERE(idFicha = FIC.idFicha)) AS COSTO_PROCESOS, \n"
                + "      ISNULL((CASE WHEN fic.[idEscala] BETWEEN 611 AND 621 AND FIC.idEscala <> 620 \n"
                + "	  THEN (SELECT [vrCosto] FROM [dbo].[tblPlus] WHERE idPlu = vrEscala) * FIC.textoEscala * (SELECT TOP 1 vrEscala FROM [dbo].[tblPlusFicha] \n"
                + "  WHERE \n"
                + "              idEscala = 622 \n"
                + "              AND idOperacion = 2 \n"
                + "              AND idFicha = FIC.idFicha\n"
                + "          ) ELSE '0' END\n"
                + "        ), \n"
                + "        0\n"
                + "      ) AS COSTOT1, \n"
                + "      ISNULL(\n"
                + "        (\n"
                + "          CASE WHEN fic.[idEscala] BETWEEN 623 \n"
                + "          AND 631 THEN (\n"
                + "            SELECT \n"
                + "              [vrCosto] \n"
                + "            FROM \n"
                + "              [dbo].[tblPlus] \n"
                + "            WHERE \n"
                + "              idPlu = vrEscala\n"
                + "          ) * FIC.textoEscala * (\n"
                + "            SELECT \n"
                + "              TOP 1 vrEscala \n"
                + "            FROM \n"
                + "              [dbo].[tblPlusFicha] \n"
                + "            WHERE \n"
                + "              idEscala = 632 \n"
                + "              AND idOperacion = 2 \n"
                + "              AND idFicha = FIC.idFicha\n"
                + "          ) ELSE '0' END\n"
                + "        ), \n"
                + "        0\n"
                + "      ) AS COSTOT2, \n"
                + "      ISNULL(\n"
                + "        (CASE WHEN fic.[idEscala] BETWEEN 633 AND 641 THEN (\n"
                + "            SELECT \n"
                + "              [vrCosto] \n"
                + "            FROM \n"
                + "              [dbo].[tblPlus] \n"
                + "            WHERE \n"
                + "              idPlu = vrEscala) * FIC.textoEscala * (SELECT TOP 1 vrEscala FROM [dbo].[tblPlusFicha] \n"
                + "            WHERE idEscala = 642 AND idOperacion = 2 AND idFicha = FIC.idFicha) ELSE '0' END), 0) AS COSTOT3, \n"
                + "      ISNULL(\n"
                + "        (\n"
                + "          CASE WHEN fic.[idEscala] BETWEEN 611 \n"
                + "          AND 621 \n"
                + "          AND FIC.idEscala <> 620 THEN FIC.textoEscala ELSE '0' END\n"
                + "        ), \n"
                + "        0\n"
                + "      ) AS KILO_T1, \n"
                + "      ISNULL(\n"
                + "        (\n"
                + "          CASE WHEN fic.[idEscala] BETWEEN 623 \n"
                + "          AND 631 \n"
                + "          AND fic.[idEscala] <> 0 THEN FIC.textoEscala ELSE '0' END\n"
                + "        ), \n"
                + "        0\n"
                + "      ) AS KILO_T2, \n"
                + "      ISNULL(\n"
                + "        (\n"
                + "          CASE WHEN fic.[idEscala] BETWEEN 633 \n"
                + "          AND 641 \n"
                + "          AND fic.[idEscala] <> 0 THEN FIC.textoEscala ELSE '0' END\n"
                + "        ), \n"
                + "        0\n"
                + "      ) AS KILO_T3 \n"
                + "    FROM \n"
                + "      dbo.tblPlusFicha AS FIC \n"
                + "      INNER JOIN dbo.tblJobEscala AS JOBESC ON FIC.idEscala = JOBESC.idEscala \n"
                + "	  INNER JOIN [dbo].[tmpFichaDensidad] AS D ON FIC.idFicha=D.IDFICHA\n"
                + "    WHERE \n"
                + "      (FIC.idOperacion = 2) \n"
                + "      AND (FIC.idEscala > 610) \n"
                + "      AND (FIC.idEscala < 642) \n"
                + "      AND (FIC.textoEscala <> '0') \n"
                + "      AND (FIC.idEscala <> 620) \n"
                + "      AND (FIC.estado = 1) \n"
                + "	  AND FIC.IDCLIENTE=" + getIdCliente() + " \n"
                + " AND (\n"
                + "        ISNULL(\n"
                + "          (CASE WHEN fic.[idEscala] BETWEEN 611 AND 621 AND FIC.idEscala <> 620 THEN FIC.textoEscala ELSE '0' END), 0) > '0')) AS TMP \n"
                + "GROUP BY \n"
                + "  idFicha, \n"
                + "  COSTO_PROCESOS, \n"
                + "  DESP, \n"
                + "  idCliente, \n"
                + "  referenciaCliente, \n"
                + "  estado,\n"
                + "  factor,  \n"
                + "  referencia,\n"
                + "  VOLUMEN,\n"
                + "  BANDERA";

        PreparedStatement ps = null;
        try {
            c = this.pc.getConnection();
            ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int bandera = 0;
                DTO_Referencia dto = new DTO_Referencia();

                dto.setNombreReferencia(rs.getString("referenciaCliente"));
                dto.setVolumen(rs.getDouble("VOLUMEN"));
                dto.setFactor(rs.getDouble("FACTOR"));
                dto.setPesoMillar(rs.getDouble("PESO_MILLAR"));
                dto.setFicha(rs.getInt("IDFICHA"));
                dto.setPrecioVenta(rs.getDouble("PRECIO_VENTA"));
                dto.setIdPedido(rs.getLong("ULTIMO_PEDIDO"));
                bandera = rs.getInt("BANDERA");
                dto.setReferencia(rs.getString("referencia"));
                Double volumen = bandera == 0 ? dto.getFactor() * dto.getVolumen() : dto.getVolumen();
                dto.setCosto(Float.parseFloat(String.valueOf(volumen * rs.getDouble("TOTAL_COSTO_KG") / 1000)));
                Double margen = dto.getPrecioVenta() > 0 ? ((dto.getPrecioVenta() - dto.getCosto()) * 100 / dto.getPrecioVenta()) : 0f;
                dto.setMargen(Float.parseFloat(String.valueOf(margen)));

                lista.add(dto);
            }
            rs.close();
            return lista;
        } catch (NamingException ne) {
            System.out.println("NamingException in " + nombreClase + " " + ne);
            return lista;
        } catch (SQLException sqle) {
            System.out.println("SQLException in" + nombreClase);
            String sqlMessage = sqle.getMessage();
            String sqlState = sqle.getSQLState();
            int vendorCode = sqle.getErrorCode();
            System.out.println("Ocurrio una  excepcion");
            System.out.println("Mensaje " + sqlMessage);
            System.out.println("SQL State " + sqlState);
            System.out.println("Vendor Code " + vendorCode);
            List localList1 = lista;
            return lista;
        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
            return lista;
        } finally {
            this.pc.cleanup(c, ps, null);
            return lista;
        }
    }

    public List<DTO_Referencia> getRefersByClientsRevenue2() {

        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();
        List<DTO_Referencia> lista = new ArrayList();

        String operador = getIdCliente() == 0 ? "<>" : "=";
        Connection c = null;
        String sql
                = "SELECT\n"
                + "  idFicha, \n"
                + "  idCliente, \n"
                + "  referencia, \n"
                + "  estado, \n"
                + "  referenciaCliente,\n"
                + "  VOLUMEN,\n"
                + "  FACTOR,\n"
                + "  BANDERA,\n"
                + "  (CASE WHEN BANDERA=0 THEN VOLUMEN*FACTOR\n"
                + "  ELSE VOLUMEN END ) AS PESO_MILLAR,\n"
                + "  SUM(CAST(KILO_T1 AS DECIMAL(10, 2))) AS KGT1, \n"
                + "  SUM(CAST(KILO_T2 AS DECIMAL(10, 2))) AS KGT2, \n"
                + "  SUM(CAST(KILO_T3 AS DECIMAL(10, 2))) AS KGT3, \n"
                + "  CASE WHEN (SUM(CAST(TMP.KILO_T1 AS DECIMAL(10, 2)))) > 0 THEN ((SUM(COSTOT1)/(SUM(CAST(TMP.KILO_T1 AS DECIMAL(10, 2)))))+DESP*(SUM(COSTOT1)/(SUM(CAST(TMP.KILO_T1 AS DECIMAL(10, 2)))))) ELSE 0 END AS COSTO_KG_TORN_1, \n"
                + "  CASE WHEN (SUM(CAST(TMP.KILO_T2 AS DECIMAL(10, 2)))) > 0 THEN ((SUM(COSTOT2)/(SUM(CAST(TMP.KILO_T2 AS DECIMAL(10, 2)))))+DESP*(SUM(COSTOT2)/(SUM(CAST(TMP.KILO_T2 AS DECIMAL(10, 2)))))) ELSE 0 END AS COSTO_KG_TORN_2, \n"
                + "  CASE WHEN (SUM(CAST(TMP.KILO_T3 AS DECIMAL(10, 2)))) > 0 THEN ((SUM(COSTOT3)/(SUM(CAST(TMP.KILO_T3 AS DECIMAL(10, 2)))))+DESP*(SUM(COSTOT3)/(SUM(CAST(TMP.KILO_T3 AS DECIMAL(10, 2)))))) ELSE 0 END AS COSTO_KG_TORN_3, \n"
                + " \n"
                + " ((CASE WHEN (SUM(CAST(TMP.KILO_T1 AS DECIMAL(10, 2)))) > 0 THEN ((SUM(COSTOT1)/(SUM(CAST(TMP.KILO_T1 AS DECIMAL(10, 2)))))+DESP*(SUM(COSTOT1)/(SUM(CAST(TMP.KILO_T1 AS DECIMAL(10, 2 )))))) ELSE 0 END) \n"
                + " +(CASE WHEN (SUM(CAST(TMP.KILO_T2 AS DECIMAL(10, 2)))) > 0 THEN ((SUM(COSTOT2)/(SUM(CAST(TMP.KILO_T2 AS DECIMAL(10, 2)))))+DESP*(SUM(COSTOT2)/(SUM(CAST(TMP.KILO_T2 AS DECIMAL(10, 2)))))) ELSE 0 END)\n"
                + " +(CASE WHEN (SUM(CAST(TMP.KILO_T3 AS DECIMAL(10, 2)))) > 0 THEN ((SUM(COSTOT3)/(SUM(CAST(TMP.KILO_T3 AS DECIMAL(10, 2)))))+DESP*(SUM(COSTOT3)/(SUM(CAST(TMP.KILO_T3 AS DECIMAL(10, 2)))))) ELSE 0 END) \n"
                + " + COSTO_PROCESOS) AS TOTAL_COSTO_KG, \n"
                + "  COSTO_PROCESOS, \n"
                + "  ISNULL((SELECT TOP (1) VRVENTAUNITARIO / (1 + PORCENTAJEIVA / 100) AS Expr1  FROM dbo.tblDctosOrdenesDetalle WHERE (IDORDEN = ISNULL((SELECT TOP (1) IDORDEN FROM dbo.tblDctosOrdenes WHERE (idFicha = TMP.idFicha)  AND (IDTIPOORDEN = 59) ORDER BY IDORDEN DESC),0)) AND (IDTIPOORDEN = 59)), 0) AS PRECIO_VENTA, \n"
                + "  ISNULL ((SELECT TOP (1) numeroOrden FROM dbo.tblDctosOrdenes AS tblDctosOrdenes_1  WHERE (idFicha = TMP.idFicha) AND (IDTIPOORDEN = 59) ORDER BY IDORDEN DESC), 0) AS ULTIMO_PEDIDO \n"
                + "FROM \n"
                + "  (SELECT \n"
                + "      FIC.idFicha, \n"
                + "      FIC.idEscala, \n"
                + "      FIC.estado, \n"
                + "      JOBESC.nombreEscala, \n"
                + "      FIC.referencia, \n"
                + "      FIC.item, \n"
                + "      FIC.vrEscala, \n"
                + "      FIC.textoEscala, \n"
                + "      FIC.idCliente, \n"
                + "      FIC.referenciaCliente,\n"
                + "      D.FACTOR,	 \n"
                + "	  ISNULL((SELECT TOP 1 [volumen] FROM [dbo].[tmpVolumenFicha] WHERE idficha=FIC.idficha),0) as VOLUMEN,\n"
                + "	  ISNULL((SELECT TOP 1 [BANDERA] FROM [dbo].[tmpVolumenFicha] WHERE idficha=FIC.idficha),0) as BANDERA,  \n"
                + "      (SELECT vrEscala FROM dbo.tblPlusFicha \n"
                + "        WHERE (idEscala = 511) AND (idOperacion = 2) AND (idFicha = FIC.idFicha)) / 100 AS DESP, \n"
                + "      (SELECT TOTAL FROM dbo.vistaFichaCostoProceso WHERE(idFicha = FIC.idFicha)) AS COSTO_PROCESOS, \n"
                + "      ISNULL((CASE WHEN fic.[idEscala] BETWEEN 611 AND 621 AND FIC.idEscala <> 620 \n"
                + "	  THEN (SELECT [vrCostoPrediccion] FROM [dbo].[tblPlus] WHERE idPlu = vrEscala) * FIC.textoEscala * (SELECT TOP 1 vrEscala FROM [dbo].[tblPlusFicha] \n"
                + "  WHERE \n"
                + "              idEscala = 622 \n"
                + "              AND idOperacion = 2 \n"
                + "              AND idFicha = FIC.idFicha\n"
                + "          ) ELSE '0' END\n"
                + "        ), \n"
                + "        0\n"
                + "      ) AS COSTOT1, \n"
                + "      ISNULL(\n"
                + "        (\n"
                + "          CASE WHEN fic.[idEscala] BETWEEN 623 \n"
                + "          AND 631 THEN (\n"
                + "            SELECT \n"
                + "              [vrCostoPrediccion] \n"
                + "            FROM \n"
                + "              [dbo].[tblPlus] \n"
                + "            WHERE \n"
                + "              idPlu = vrEscala\n"
                + "          ) * FIC.textoEscala * (\n"
                + "            SELECT \n"
                + "              TOP 1 vrEscala \n"
                + "            FROM \n"
                + "              [dbo].[tblPlusFicha] \n"
                + "            WHERE \n"
                + "              idEscala = 632 \n"
                + "              AND idOperacion = 2 \n"
                + "              AND idFicha = FIC.idFicha\n"
                + "          ) ELSE '0' END\n"
                + "        ), \n"
                + "        0\n"
                + "      ) AS COSTOT2, \n"
                + "      ISNULL(\n"
                + "        (CASE WHEN fic.[idEscala] BETWEEN 633 AND 641 THEN (\n"
                + "            SELECT \n"
                + "              [vrCostoPrediccion] \n"
                + "            FROM \n"
                + "              [dbo].[tblPlus] \n"
                + "            WHERE \n"
                + "              idPlu = vrEscala) * FIC.textoEscala * (SELECT TOP 1 vrEscala FROM [dbo].[tblPlusFicha] \n"
                + "            WHERE idEscala = 642 AND idOperacion = 2 AND idFicha = FIC.idFicha) ELSE '0' END), 0) AS COSTOT3, \n"
                + "      ISNULL(\n"
                + "        (\n"
                + "          CASE WHEN fic.[idEscala] BETWEEN 611 \n"
                + "          AND 621 \n"
                + "          AND FIC.idEscala <> 620 THEN FIC.textoEscala ELSE '0' END\n"
                + "        ), \n"
                + "        0\n"
                + "      ) AS KILO_T1, \n"
                + "      ISNULL(\n"
                + "        (\n"
                + "          CASE WHEN fic.[idEscala] BETWEEN 623 \n"
                + "          AND 631 \n"
                + "          AND fic.[idEscala] <> 0 THEN FIC.textoEscala ELSE '0' END\n"
                + "        ), \n"
                + "        0\n"
                + "      ) AS KILO_T2, \n"
                + "      ISNULL(\n"
                + "        (\n"
                + "          CASE WHEN fic.[idEscala] BETWEEN 633 \n"
                + "          AND 641 \n"
                + "          AND fic.[idEscala] <> 0 THEN FIC.textoEscala ELSE '0' END\n"
                + "        ), \n"
                + "        0\n"
                + "      ) AS KILO_T3 \n"
                + "    FROM \n"
                + "      dbo.tblPlusFicha AS FIC \n"
                + "      INNER JOIN dbo.tblJobEscala AS JOBESC ON FIC.idEscala = JOBESC.idEscala \n"
                + "	  INNER JOIN [dbo].[tmpFichaDensidad] AS D ON FIC.idFicha=D.IDFICHA\n"
                + "    WHERE \n"
                + "      (FIC.idOperacion = 2) \n"
                + "      AND (FIC.idEscala > 610) \n"
                + "      AND (FIC.idEscala < 642) \n"
                + "      AND (FIC.textoEscala <> '0') \n"
                + "      AND (FIC.idEscala <> 620) \n"
                + "      AND (FIC.estado = 1) \n"
                + "	  AND FIC.IDCLIENTE=" + getIdCliente() + " \n"
                + "      AND (\n"
                + "        ISNULL(\n"
                + "          (CASE WHEN fic.[idEscala] BETWEEN 611 AND 621 AND FIC.idEscala <> 620 THEN FIC.textoEscala ELSE '0' END), 0) > '0')) AS TMP \n"
                + "GROUP BY \n"
                + "  idFicha, \n"
                + "  COSTO_PROCESOS, \n"
                + "  DESP, \n"
                + "  idCliente, \n"
                + "  referenciaCliente, \n"
                + "  estado,\n"
                + "  factor,  \n"
                + "  referencia,\n"
                + "  VOLUMEN,\n"
                + "  BANDERA";

        PreparedStatement ps = null;
        try {
            c = this.pc.getConnection();
            ps = c.prepareStatement(sql);
            final ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int bandera = 0;
                DTO_Referencia dto = new DTO_Referencia();

                dto.setNombreReferencia(rs.getString("referenciaCliente"));
                dto.setVolumen(rs.getDouble("VOLUMEN"));
                dto.setFactor(rs.getDouble("FACTOR"));
                dto.setPesoMillar(rs.getDouble("PESO_MILLAR"));
                dto.setFicha(rs.getInt("IDFICHA"));
                dto.setPrecioVenta(rs.getDouble("PRECIO_VENTA"));
                dto.setIdPedido(rs.getLong("ULTIMO_PEDIDO"));
                bandera = rs.getInt("BANDERA");
                dto.setReferencia(rs.getString("referencia"));
                Double volumen = bandera == 0 ? dto.getFactor() * dto.getVolumen() : dto.getVolumen();
                dto.setCosto(Float.parseFloat(String.valueOf(volumen * rs.getDouble("TOTAL_COSTO_KG") / 1000)));
                Double margen = dto.getPrecioVenta() > 0 ? ((dto.getPrecioVenta() - dto.getCosto()) * 100 / dto.getPrecioVenta()) : 0f;
                dto.setMargen(Float.parseFloat(String.valueOf(margen)));

                lista.add(dto);
            }
            rs.close();
            return lista;
        } catch (NamingException ne) {
            System.out.println("NamingException in " + nombreClase + " " + ne);
            return lista;
        } catch (SQLException sqle) {
            System.out.println("SQLException in" + nombreClase);
            String sqlMessage = sqle.getMessage();
            String sqlState = sqle.getSQLState();
            int vendorCode = sqle.getErrorCode();
            System.out.println("Ocurrio una  excepcion");
            System.out.println("Mensaje " + sqlMessage);
            System.out.println("SQL State " + sqlState);
            System.out.println("Vendor Code " + vendorCode);
            List localList1 = lista;
            return lista;
        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
            return lista;
        } finally {
            this.pc.cleanup(c, ps, null);
            return lista;
        }
    }

    public boolean saveVolumenParams(String idFicha) {
        String sentencia = "MERGE INTO tmpVolumenFicha AS target\n"
                + "USING (\n"
                + "    SELECT TOP (1) [VOLUMEN], [BANDERA_KG], [idFicha] FROM [dbo].[VISTA_VOLUMEN_FICHA] WHERE [idFicha] = " + idFicha
                + ") AS source ([volumen], [bandera], [idFicha])\n"
                + "ON (target.[idFicha] = source.[idFicha])\n"
                + "WHEN MATCHED THEN\n"
                + "    UPDATE SET [volumen] = source.[volumen], [bandera] = source.[bandera]\n"
                + "WHEN NOT MATCHED BY TARGET THEN\n"
                + "    INSERT ([idFicha], [volumen], [bandera])\n"
                + "    VALUES (source.[idFicha], source.[volumen], source.[bandera]);";
        return ejecutarSentencia(sentencia);
    }

    public boolean saveVolumenNomParams(String idFicha) {
        String sentencia = "MERGE INTO tmpVolumenFicha AS target\n"
                + "USING (\n"
                + "    SELECT TOP (1) [VOLUMEN], [BANDERA_KG], [idFicha] FROM [dbo].[VISTA_VOLUMEN_FICHA_NOM] WHERE [idFicha] = " + idFicha
                + ") AS source ([volumen], [bandera], [idFicha])\n"
                + "ON (target.[idFicha] = source.[idFicha])\n"
                + "WHEN MATCHED THEN\n"
                + "    UPDATE SET [volumenNominal] = source.[volumen], [bandera] = source.[bandera]\n;";
        return ejecutarSentencia(sentencia);
    }

    public boolean saveDensidad(String idFicha) {
        String sentencia = "MERGE INTO tmpFichaDensidad AS target\n"
                + "USING (SELECT TOP (1) [FACTOR_D], [idFicha] FROM [dbo].[VISTA_FICHA_DENSIDAD] WHERE [idFicha] = " + idFicha + " ) "
                + "AS source ([factor], [idFicha])\n"
                + "ON (target.[idFicha] = source.[idFicha])\n"
                + "WHEN MATCHED THEN\n"
                + "    UPDATE SET [factor] = source.[factor]\n"
                + "WHEN NOT MATCHED BY TARGET THEN\n"
                + "    INSERT ([idFicha], [factor])\n"
                + "    VALUES (source.[idFicha], source.[factor]);";
        return ejecutarSentencia(sentencia);
    }

    public List<Proceso_Materia_DTO> getMateriaByRef(int idReferencia) {
        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();
        List<Proceso_Materia_DTO> lista = new ArrayList();
        Connection c = null;
        String sql = "SELECT TOP (1000) [id]"
                + "      ,[porcion]"
                + "      ,[batch]"
                + "      ,[idReferencia]"
                + "      ,[idMateria]"
                + "   ,nombre_materia"
                + ",costo_mat_prima"
                + "  FROM [dbo].[tblCotMateriasReferencias] "
                + "INNER JOIN [dbo].[tblOperacionMateria] ON idMateria = [sk_operacion_materia]"
                + " WHERE idReferencia=" + idReferencia + " ORDER BY idMateria ";
        PreparedStatement ps = null;
        try {
            c = this.pc.getConnection();
            ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Proceso_Materia_DTO dto = new Proceso_Materia_DTO();
                dto.setCantidad(rs.getDouble("porcion"));
                dto.setBache(rs.getDouble("batch"));
                dto.setNombre(rs.getString("nombre_materia"));
                dto.setSk_operacion_materia(rs.getInt("idMateria"));
                dto.setCostoMateriaPrima(rs.getDouble("costo_mat_prima"));
                lista.add(dto);
            }
            rs.close();
            return lista;
        } catch (NamingException ne) {
            System.out.println("NamingException in " + nombreClase + " " + ne);
            return lista;
        } catch (SQLException sqle) {
            System.out.println("SQLException in" + nombreClase);
            String sqlMessage = sqle.getMessage();
            String sqlState = sqle.getSQLState();
            int vendorCode = sqle.getErrorCode();
            System.out.println("Ocurrio una  excepcion");
            System.out.println("Mensaje " + sqlMessage);
            System.out.println("SQL State " + sqlState);
            System.out.println("Vendor Code " + vendorCode);
            List localList1 = lista;
            return lista;
        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
            return lista;
        } finally {
            this.pc.cleanup(c, ps, null);
            return lista;
        }
    }

    public List<FachadaPluBean> getMateriaAll() {
        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();
        List<FachadaPluBean> lista = new ArrayList();
        Connection c = null;
        String sql
                = "SELECT  [idPlu]\n"
                + "      ,[nombrePlu]\n"
                + "      ,[vrGeneral]\n"
                + "      ,[vrMayorista]\n"
                + "      ,[porcentajeIva]\n"
                + "      ,[idTipo]\n"
                + "      ,[idLinea]\n"
                + "      ,[idUCompra]\n"
                + "      ,[idUVenta]\n"
                + "      ,[vrCosto]\n"
                + "      ,[vrCostoPrediccion]\n"
                + "      ,[idCategoria]\n"
                + "      ,[idMarca]\n"
                + "      ,[vrSucursal]\n"
                + "      ,[factorVenta]\n"
                + "      ,[factorDespacho]\n"
                + "      ,[estado]\n"
                + "      ,[idSeq]\n"
                + "      ,[referencia]\n"
                + "      ,[vrImpoconsumo]\n"
                + "      ,[vrCostoIND]\n"
                + "      ,[factorDensidad]\n"
                + "      ,[sk_tercero_ppal]\n"
                + "      ,[factor]\n"
                + "      ,[dolarizado]\n"
                + "  FROM [dbo].[tblPlus] WHERE idCategoria = 12  \n"
                + "  AND ESTADO =  0  ORDER BY nombrePlu";
        PreparedStatement ps = null;
        try {
            c = this.pc.getConnection();
            ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                FachadaPluBean fachadaPluBean = new FachadaPluBean();
                fachadaPluBean.setIdPlu(rs.getInt("idPlu"));
                fachadaPluBean.setNombrePlu(rs.getString("nombrePlu"));
                fachadaPluBean.setVrCostoPrediccion(rs.getDouble("vrCostoPrediccion"));
                fachadaPluBean.setVrCosto(rs.getDouble("vrCosto"));
                fachadaPluBean.setDolarizado(rs.getInt("dolarizado"));
                lista.add(fachadaPluBean);
            }
            rs.close();
            return lista;
        } catch (NamingException ne) {
            System.out.println("NamingException in " + nombreClase + " " + ne);
            return lista;
        } catch (SQLException sqle) {
            System.out.println("SQLException in" + nombreClase);
            String sqlMessage = sqle.getMessage();
            String sqlState = sqle.getSQLState();
            int vendorCode = sqle.getErrorCode();
            System.out.println("Ocurrio una  excepcion");
            System.out.println("Mensaje " + sqlMessage);
            System.out.println("SQL State " + sqlState);
            System.out.println("Vendor Code " + vendorCode);
            List localList1 = lista;
            return lista;
        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
            return lista;
        } finally {
            this.pc.cleanup(c, ps, null);
            return lista;
        }
    }

    public List<FachadaPluBean> getMateriaDolarizados() {
        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();
        List<FachadaPluBean> lista = new ArrayList();
        Connection c = null;
        String sql
                = "SELECT [idPlu]\n"
                + "      ,[nombrePlu]\n"
                + "      ,[vrGeneral]\n"
                + "      ,[vrMayorista]\n"
                + "      ,[porcentajeIva]\n"
                + "      ,[idTipo]\n"
                + "      ,[idLinea]\n"
                + "      ,[idUCompra]\n"
                + "      ,[idUVenta]\n"
                + "      ,[vrCosto]\n"
                + "      ,[idCategoria]\n"
                + "      ,[idMarca]\n"
                + "      ,[vrSucursal]\n"
                + "      ,[factorVenta]\n"
                + "      ,[factorDespacho]\n"
                + "      ,[estado]\n"
                + "      ,[idSeq]\n"
                + "      ,[referencia]\n"
                + "      ,[vrImpoconsumo]\n"
                + "      ,[vrCostoIND]\n"
                + "      ,[factorDensidad]\n"
                + "      ,[sk_tercero_ppal]\n"
                + "      ,[factor]\n"
                + "      ,[dolarizado]\n"
                + "  FROM [dbo].[tblPlus] WHERE idCategoria=12  \n"
                + "  AND dolarizado=1  \n"
                + "  AND ESTADO =  0  ORDER BY nombrePlu";
        PreparedStatement ps = null;
        try {
            c = this.pc.getConnection();
            ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                FachadaPluBean fachadaPluBean = new FachadaPluBean();
                fachadaPluBean.setIdPlu(rs.getInt("idPlu"));
                fachadaPluBean.setNombrePlu(rs.getString("nombrePlu"));
                fachadaPluBean.setVrCosto(rs.getString("vrCosto"));
                fachadaPluBean.setDolarizado(rs.getInt("dolarizado"));
                lista.add(fachadaPluBean);
            }
            rs.close();
            return lista;
        } catch (NamingException ne) {
            System.out.println("NamingException in " + nombreClase + " " + ne);
            return lista;
        } catch (SQLException sqle) {
            System.out.println("SQLException in" + nombreClase);
            String sqlMessage = sqle.getMessage();
            String sqlState = sqle.getSQLState();
            int vendorCode = sqle.getErrorCode();
            System.out.println("Ocurrio una  excepcion");
            System.out.println("Mensaje " + sqlMessage);
            System.out.println("SQL State " + sqlState);
            System.out.println("Vendor Code " + vendorCode);
            List localList1 = lista;
            return lista;
        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
            return lista;
        } finally {
            this.pc.cleanup(c, ps, null);
            return lista;
        }
    }

    public Double getPesoMillar(int idFicha) {

        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();
        Double pesoMillar = 0.0;
        Connection c = null;
        String sql = "SELECT TOP 1 V.[idFicha],\n"
                + "(CASE WHEN V.bandera=0 THEN volumen*D.factor ELSE VOLUMEN END ) AS PESOMILLAR\n"
                + " FROM [dbo].[tmpVolumenFicha] V,\n"
                + "[dbo].[tmpFichaDensidad] D WHERE V.idFicha=D.idFicha\n"
                + "AND V.idFicha = " + idFicha;

        PreparedStatement ps = null;
        try {
            c = this.pc.getConnection();
            ps = c.prepareStatement(sql);
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

    public Double getFactorDensidad(int idFicha) {

        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();
        Double factorDensidad = 0.0;
        Connection c = null;
        String sql
                = "SELECT TOP (1)\n"
                + " [factor]\n"
                + "FROM [dbo].[tmpFichaDensidad]\n"
                + "WHERE idFicha=" + idFicha;

        PreparedStatement ps = null;
        try {
            c = this.pc.getConnection();
            ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                factorDensidad = rs.getDouble("factor");
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
    
 /**
 * Revierte el desecho de extrusión restaurando los valores de la columna vrEscala
 * en la tabla tblPlusFicha de la base de datos. Restaura los valores de vrEscala 
 * con idEscala = 511 "% EXCEDENTE" de Extrusion Operacion 2 en la tabla tblPlusFicha
 * en los registros que comparten el mismo idFicha, idOperacion y idEscala en la tabla.
 * y que provienen del campo de respaldo IdEscala = 522 % "EXCEDENTE (RESPALDO)"
 *
 * @return true si la operación de reversión fue exitosa, false si ocurrió un error
 */
    public boolean revertirDesechoExtrusion() {
        // Definición de la sentencia SQL que revierte el desecho de extrusión
        String sentencia = "UPDATE DESTINO \n"
                + "	SET DESTINO.vrEscala = ORIGEN.vrEscala\n"
                + " FROM [dbo].[tblPlusFicha] DESTINO  \n"
                + "	INNER JOIN [dbo].[tblPlusFicha] ORIGEN \n"
                + "	ON DESTINO.idFicha = ORIGEN.idFicha \n"
                + "	AND DESTINO.idOperacion = ORIGEN.idOperacion\n"
                + "	WHERE DESTINO.idEscala = 511\n"
                + "	AND ORIGEN.idEscala = 522;";

        // Ejecuta la sentencia SQL y devuelve el resultado de la ejecución
        return ejecutarSentencia(sentencia);
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
