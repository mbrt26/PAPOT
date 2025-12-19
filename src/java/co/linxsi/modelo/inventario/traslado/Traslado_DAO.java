/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.linxsi.modelo.inventario.traslado;

import com.solucionesweb.lasayudas.JDBCAccess;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author Desarrollador
 */
public class Traslado_DAO extends Traslado_DTO {

    private JDBCAccess pc;
    private static final String DATA_SOURCE_NAME = "CommerceAccess";
    private static String nombreClase;
    private Connection c;
    private static final int tipoOp = 54;

    protected final SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");

    public Traslado_DAO() {
        pc = new JDBCAccess(DATA_SOURCE_NAME);
        nombreClase = this.getClass().getName();

    }

    public ArrayList<Traslado_DTO> damePLUOrdenOrigen() {
        ArrayList<Traslado_DTO> dtoLista = new ArrayList<Traslado_DTO>();
        String sql = "SELECT * FROM tblDctosOrdenesProgreso"
                + " INNER JOIN tblOperaciones ON tblDctosOrdenesProgreso.idOperacion=tblOperaciones.sk_operacion"
                + " WHERE idorden = " + getNumOrdenOrigen()
                + " AND  cantidadTerminada > 0 "
                + " AND tblDctosOrdenesProgreso.idOperacion BETWEEN 2 AND 6\n"
                + " ORDER BY sk_operacion,item";
        PreparedStatement ps = null;
        try {
            c = pc.getConnection();
            ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Traslado_DTO dto = new Traslado_DTO();
                dto.setItem(rs.getInt("item"));
                dto.setCantidadOrigen(rs.getDouble("cantidadTerminada"));
                dto.setNombre_operacion(rs.getString("nombre_operacion"));
                dto.setPesoTerminado(rs.getDouble("pesoTerminado"));
                dto.setSk_operacion(rs.getInt("sk_operacion"));
                dto.setFechaInicial(rs.getString("fechaInicio"));
                dto.setFechaFinal(rs.getString("fechaFin"));
                dtoLista.add(dto);
            }
            rs.close();
            return dtoLista;
        } catch (NamingException ne) {
            System.out.println("NamingException in " + nombreClase + " " + ne);
            return dtoLista;
        } catch (SQLException sqle) {
            System.out.println("SQLException in" + nombreClase);
            String sqlMessage = sqle.getMessage();
            String sqlState = sqle.getSQLState();
            int vendorCode = sqle.getErrorCode();
            System.out.println("Ocurrio una  excejdbcAccession");
            System.out.println("Mensaje " + sqlMessage);
            System.out.println("SQL State " + sqlState);
            System.out.println("Vendor Code " + vendorCode);
            return dtoLista;
        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
            return dtoLista;
        } finally {
            this.jdbcAccess.cleanup(c, ps, null);
            return dtoLista;
        }

    }

    public ArrayList<Traslado_DTO> damePLUOrdenDestino() {
        ArrayList<Traslado_DTO> dtoLista = new ArrayList<Traslado_DTO>();
        String sql = "SELECT * FROM tblDctosOrdenesProgreso"
                + " INNER JOIN tblOperaciones ON tblDctosOrdenesProgreso.idOperacion=tblOperaciones.sk_operacion"
                + " WHERE idorden = " + getNumOrdenDestino()
                + " AND tblDctosOrdenesProgreso.idOperacion BETWEEN 2 AND 6\n"
                + " ORDER BY sk_operacion,item";
        PreparedStatement ps = null;
        try {
            c = pc.getConnection();
            ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Traslado_DTO dto = new Traslado_DTO();
                dto.setItem(rs.getInt("item"));
                dto.setCantidadOrigen(rs.getDouble("cantidadTerminada"));
                dto.setNombre_operacion(rs.getString("nombre_operacion"));
                dto.setPesoTerminado(rs.getDouble("pesoTerminado"));
                dto.setSk_operacion(rs.getInt("sk_operacion"));
                dto.setFechaInicial(rs.getString("fechaInicio"));
                dto.setFechaFinal(rs.getString("fechaFin"));
                dtoLista.add(dto);
            }
            rs.close();
            return dtoLista;
        } catch (NamingException ne) {
            System.out.println("NamingException in " + nombreClase + " " + ne);
            return dtoLista;
        } catch (SQLException sqle) {
            System.out.println("SQLException in" + nombreClase);
            String sqlMessage = sqle.getMessage();
            String sqlState = sqle.getSQLState();
            int vendorCode = sqle.getErrorCode();
            System.out.println("Ocurrio una  excejdbcAccession");
            System.out.println("Mensaje " + sqlMessage);
            System.out.println("SQL State " + sqlState);
            System.out.println("Vendor Code " + vendorCode);
            return dtoLista;
        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
            return dtoLista;
        } finally {
            this.jdbcAccess.cleanup(c, ps, null);
            return dtoLista;
        }

    }

    public ArrayList<Traslado_DTO> damePLUOrdenDestinoAgregado() {
        ArrayList<Traslado_DTO> dtoLista = new ArrayList<Traslado_DTO>();
        String sql = "SELECT * FROM tblDctosOrdenesDetalle"
                + " INNER JOIN tblOperaciones ON tblDctosOrdenesDetalle.idOperacion=tblOperaciones.sk_operacion"
                + " WHERE idorden = " + getOrden()
                + " ORDER BY sk_operacion;";
        PreparedStatement ps = null;
        try {
            c = pc.getConnection();
            ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Traslado_DTO dto = new Traslado_DTO();
                dto.setItem(rs.getInt("item"));

                dto.setCantidadOrigen(rs.getDouble("CANTIDAD"));
                dto.setNombre_operacion(rs.getString("nombre_operacion"));
                dto.setPesoTerminado(rs.getDouble("PESOTEORICO"));
                dto.setSk_operacion(rs.getInt("sk_operacion"));
                dtoLista.add(dto);
            }
            rs.close();
            return dtoLista;
        } catch (NamingException ne) {
            System.out.println("NamingException in " + nombreClase + " " + ne);
            return dtoLista;
        } catch (SQLException sqle) {
            System.out.println("SQLException in" + nombreClase);
            String sqlMessage = sqle.getMessage();
            String sqlState = sqle.getSQLState();
            int vendorCode = sqle.getErrorCode();
            System.out.println("Ocurrio una  excejdbcAccession");
            System.out.println("Mensaje " + sqlMessage);
            System.out.println("SQL State " + sqlState);
            System.out.println("Vendor Code " + vendorCode);
            return dtoLista;
        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
            return dtoLista;
        } finally {
            this.jdbcAccess.cleanup(c, ps, null);
            return dtoLista;
        }

    }

    public int maxOrden() {
        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();

        int idOrden = 0;
        Connection c = null;
        String sql = " SELECT MAX(idorden) AS idorden FROM tblDctosOrdenes;";
        PreparedStatement ps = null;
        try {
            c = this.pc.getConnection();
            ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                idOrden = rs.getInt("idorden");
            }

            return idOrden;
        } catch (NamingException ne) {
            System.out.println("NamingException in " + nombreClase + " " + ne);
            return idOrden;
        } catch (SQLException sqle) {
            System.out.println("SQLException in" + nombreClase);
            System.out.println("Ocurrio una  excepcion");
            System.out.println("Mensaje " + sqle.getMessage());
            System.out.println("SQL State " + sqle.getSQLState());
            System.out.println("Vendor Code " + sqle.getErrorCode());
            return idOrden;
        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
            return idOrden;
        } finally {
            this.pc.cleanup(c, ps, null);
            return idOrden;
        }
    }

    public int dameKeyOrden() {
        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();

        int idOrden = 0;
        Connection c = null;
        String sql = " SELECT (idorden) FROM tblDctosOrdenes WHERE numeroOrden = " + getOrden() + " AND idtipoorden = 59 ";
        PreparedStatement ps = null;
        try {
            c = this.pc.getConnection();
            ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                idOrden = rs.getInt("idorden");
            }
            return idOrden;
        } catch (NamingException ne) {
            System.out.println("NamingException in " + nombreClase + " " + ne);
            return idOrden;
        } catch (SQLException sqle) {
            System.out.println("SQLException in" + nombreClase);
            System.out.println("Ocurrio una  excepcion");
            System.out.println("Mensaje " + sqle.getMessage());
            System.out.println("SQL State " + sqle.getSQLState());
            System.out.println("Vendor Code " + sqle.getErrorCode());
            return idOrden;
        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
            return idOrden;
        } finally {
            this.pc.cleanup(c, ps, null);
            return idOrden;
        }
    }

    public Traslado_DTO getRegistro() {
        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();
        Traslado_DTO dto = new Traslado_DTO();
        Connection c = null;
        String sql = " SELECT  "
                + "tblDctosOrdenesProgreso.item, "
                + "tblDctosOrdenesProgreso.cantidadTerminada, "
                + "tblDctosOrdenesProgreso.pesoTerminado, "
                + "tblDctosOrdenesProgreso.idoperacion, "
                + "tblDctosOrdenesProgreso.fechaInicio, "
                + "tblDctosOrdenesProgreso.fechaFin, "
                + " DTLL.referenciaCliente "
                + " FROM tblDctosOrdenesProgreso "
                + " INNER JOIN tblOperaciones ON tblDctosOrdenesProgreso.idOperacion=tblOperaciones.sk_operacion "
                + " INNER JOIN tblDctosOrdenesDetalle DTLL ON  DTLL.idorden = tblDctosOrdenesProgreso.idorden AND DTLL.idTipoOrden = 59 "
                + " WHERE DTLL.idOrden = " + getNumOrdenOrigen()
                + " AND tblDctosOrdenesProgreso.item = " + getItem()
                + " AND sk_operacion= " + getSk_operacion() + ";";

        PreparedStatement ps = null;
        try {
            c = this.pc.getConnection();
            ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                dto.setItem(rs.getInt("item"));
                dto.setCantidadOrigen(rs.getDouble("cantidadTerminada"));
                dto.setPesoTerminado(rs.getDouble("pesoTerminado"));
                dto.setSk_operacion(rs.getInt("idoperacion"));
                dto.setFechaInicial(rs.getString("fechaInicio"));
                dto.setFechaFinal(rs.getString("fechaFin"));
                dto.setNombreReferenciaCliente(rs.getString("referenciaCliente"));

            }
            return dto;
        } catch (NamingException ne) {
            System.out.println("NamingException in " + nombreClase + " " + ne);
            return dto;
        } catch (SQLException sqle) {
            System.out.println("SQLException in" + nombreClase);
            System.out.println("Ocurrio una  excepcion");
            System.out.println("Mensaje " + sqle.getMessage());
            System.out.println("SQL State " + sqle.getSQLState());
            System.out.println("Vendor Code " + sqle.getErrorCode());
            return dto;
        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
            return dto;
        } finally {
            this.pc.cleanup(c, ps, null);
            return dto;
        }

    }

    public Traslado_DTO getNombreReferencia() {
        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();
        Traslado_DTO dto = new Traslado_DTO();
        Connection c = null;
        String sql = "SELECT referenciaCliente  FROM tblDctosOrdenesDetalle WHERE idOrden = " + getNumOrdenOrigen() + " ;";;

        PreparedStatement ps = null;
        try {
            c = this.pc.getConnection();
            ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                dto.setNombreReferenciaCliente(rs.getString("referenciaCliente"));

            }

            return dto;
        } catch (NamingException ne) {
            System.out.println("NamingException in " + nombreClase + " " + ne);
            return dto;
        } catch (SQLException sqle) {
            System.out.println("SQLException in" + nombreClase);
            System.out.println("Ocurrio una  excepcion");
            System.out.println("Mensaje " + sqle.getMessage());
            System.out.println("SQL State " + sqle.getSQLState());
            System.out.println("Vendor Code " + sqle.getErrorCode());
            return dto;
        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
            return dto;
        } finally {
            this.pc.cleanup(c, ps, null);
            return dto;
        }

    }

    public boolean insertPLU() {
        System.out.println("insertPLU");
        int itemDestino = 0;
        if (!getRegistrosOrden()) {
            itemDestino = getItemOrigen();
        } else {
            itemDestino = getItemDestino();
        }
        itemDestino++;

        //Permite ingresar el producto que el usuario previamente selecciono hacia la tabla tblDctosOrdenesDetalle para posteriormente realizar al traslado del objeto
        String sql = "INSERT INTO tblDctosOrdenesDetalle "
                + "     (idLocal\n"
                + "     ,IDTIPOORDEN\n"
                + "      ,IDORDEN\n"
                + "      ,idBodega\n"
                + "      ,IDPLU\n"
                + "      ,CANTIDAD\n"
                + "      ,[PESOTEORICO]\n"
                + "      ,item\n"
                + "      ,itemPadre\n"
                + "      ,idOperacion\n"
                + "      ,idOrdenOrigen\n"
                + "      ,referenciaCliente)\n"
                + "      SELECT "
                + "      idLocal\n"
                + "      ," + tipoOp + "\n"
                + "      ," + getOrden() + "\n"
                + "      ,(SELECT ISNULL((SELECT MAX(idbodega) FROM tblDctosOrdenesDetalle  WHERE IDTIPOORDEN =" + tipoOp + " AND IDORDEN = " + getOrden() + "),'0') + 1)\n"
                + "      ,idPlu\n"
                + "      ," + getCantidadDestino() + "\n"
                + "      ," + getPesoTerminado() + "\n"
                + "      ," + itemDestino + "\n"
                + "      ,item\n"
                + "      ,idOperacion\n"
                + "      ,IDORDEN\n"
                + "      ,'" + getReferencia() + "'\n"
                + "       FROM tblDctosOrdenesProgreso"
                + " WHERE IDORDEN = " + getNumOrdenOrigen() + " AND item = " + getItem() + " AND idPlu = " + getIdPlu() + " AND idOperacion = " + getSk_operacion() + ";";
        return ejecutarSentencia(sql);
    }

    public boolean insertNuevaOrden() {
        boolean res = false;
        String sql = "INSERT INTO tblDctosOrdenes "
                + "(IDLOCAL,ESTADO,IDTIPOORDEN,IDORDEN,numeroOrden,IDUSUARIO,FECHAORDEN) "
                + "VALUES"
                + "("
                + getIdLocal()
                + ",0"
                + "," + tipoOp
                + "," + getOrden()
                + ",((SELECT COUNT(*)  FROM tblDctosOrdenes WHERE [IDTIPOORDEN] = " + tipoOp + ")+1)"
                + "," + getIdUsuario()
                + ",GETDATE ())";
        res = ejecutarSentencia(sql);
        return res;
    }

    private int getItemOrigen() {
        int item = 0;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "SELECT MAX(item) FROM tblDctosOrdenesProgreso WHERE idOrden  = " + getNumOrdenDestino() + " AND idOperacion = " + getSk_operacion() + ";";
        try {
            c = this.jdbcAccess.getConnection();
            ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                item = rs.getInt(1);
            }
            rs.close();
            return item;
        } catch (NamingException ne) {
            System.out.println("NamingException in " + nombreClase + " " + ne);
            return item;
        } catch (SQLException sqle) {
            System.out.println("SQLException in" + nombreClase);
            String sqlMessage = sqle.getMessage();
            String sqlState = sqle.getSQLState();
            int vendorCode = sqle.getErrorCode();
            System.out.println("Ocurrio una  excejdbcAccession");
            System.out.println("Mensaje " + sqlMessage);
            System.out.println("SQL State " + sqlState);
            System.out.println("Vendor Code " + vendorCode);
            return item;
        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
            return item;
        } finally {
            this.jdbcAccess.cleanup(c, ps, null);
            return item;
        }
    }

    private int getItemDestino() {

        int item = 0;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "SELECT MAX(item) FROM tblDctosOrdenesDetalle WHERE idOrden  = " + getOrden() + " AND idOperacion = " + getSk_operacion() + ";";
        try {
            c = this.jdbcAccess.getConnection();
            ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                item = rs.getInt(1);
            }
            rs.close();
            return item;
        } catch (NamingException ne) {
            System.out.println("NamingException in " + nombreClase + " " + ne);
            return item;
        } catch (SQLException sqle) {
            System.out.println("SQLException in" + nombreClase);
            String sqlMessage = sqle.getMessage();
            String sqlState = sqle.getSQLState();
            int vendorCode = sqle.getErrorCode();
            System.out.println("Ocurrio una  excejdbcAccession");
            System.out.println("Mensaje " + sqlMessage);
            System.out.println("SQL State " + sqlState);
            System.out.println("Vendor Code " + vendorCode);
            return item;
        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
            return item;
        } finally {
            this.jdbcAccess.cleanup(c, ps, null);
            return item;
        }
    }

    private boolean getRegistrosOrden() {

        int item = 0;
        boolean existe = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "SELECT COUNT(*) FROM tblDctosOrdenesDetalle WHERE idOrden  = " + getOrden() + " AND idOperacion = " + getSk_operacion() + ";";
        try {
            c = this.jdbcAccess.getConnection();
            ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                item = rs.getInt(1);
                if (item > 0) {
                    existe = true;
                } else {
                    existe = false;
                }
            }
            rs.close();
        } catch (NamingException ne) {
            System.out.println("NamingException in " + nombreClase + " " + ne);
            return false;
        } catch (SQLException sqle) {
            System.out.println("SQLException in" + nombreClase);
            String sqlMessage = sqle.getMessage();
            String sqlState = sqle.getSQLState();
            int vendorCode = sqle.getErrorCode();
            System.out.println("Ocurrio una  excejdbcAccession");
            System.out.println("Mensaje " + sqlMessage);
            System.out.println("SQL State " + sqlState);
            System.out.println("Vendor Code " + vendorCode);
            int i = item;
            return false;
        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
            return false;
        } finally {
            this.jdbcAccess.cleanup(c, ps, null);
            return existe;
        }
    }

    public boolean ejecutarTraslado() {
        String sql = "INSERT INTO tblDctosOrdenesProgreso \n"
                + "      (\n"
                + "	   [idLocal]\n"
                + "	  ,[idTipoOrden]\n"
                + "      ,[idOrden]\n"
                + "      ,[item]\n"
                + "      ,[idOperacion]\n"
                + "      ,[idOperario]\n"
                + "      ,[cantidadPerdida]\n"
                + "      ,[cantidadTerminada]\n"
                + "      ,[pesoPerdido]\n"
                + "      ,[pesoTerminado]\n"
                + "      ,[fechaInicio]\n"
                + "      ,[fechaFin]\n"
                + "      ,[idCausa]\n"
                + "      ,[estado]\n"
                + "      ,[itemPadre]\n"
                + "      ,[cantidadPedida]\n"
                + "      ,[pesoPedido]\n"
                + "      ,[idControl]\n"
                + "      ,[idPlu]\n"
                + "      ,[idControlTipo]\n"
                + "      ,[observacion]\n"
                + "      ,[idUsuario]\n"
                + "      ,[cantidadPendiente]\n"
                + "      ,[vrCostoBaseMAT]\n"
                + "      ,[vrCostoBaseCIF]\n"
                + "      ,[vrCostoBaseMOD]\n"
                + "      ,[pesoTara]\n"
                + "      ,[idTipoCausa]\n"
                + "      ,[idDctoNitCC]\n"
                + "      ,[idOrdenCruce]\n"
                + "      ,[idMaquina]\n"
                + "	  )\n"
                + "    SELECT        \n"
                + "	   tblDctosOrdenesDetalle.IDLOCAL\n"
                + "       ,tblDctosOrdenesProgreso.idTipoOrden\n"
                + "	  ," + getNumOrdenDestino() + "\n"
                + "	  ,tblDctosOrdenesDetalle.[item]\n"
                + "	  ,tblDctosOrdenesDetalle.[idOperacion]\n"
                + "	  ,tblDctosOrdenesProgreso.idOperario\n"
                + "	  ,tblDctosOrdenesProgreso.cantidadPerdida\n"
                + "	  ,tblDctosOrdenesDetalle.CANTIDAD\n"
                + "	  ,tblDctosOrdenesProgreso.pesoPerdido\n"
                + "	  ,tblDctosOrdenesDetalle.PESOTEORICO\n"
                + "	  ,tblDctosOrdenesProgreso.fechaInicio\n"
                + "	  ,tblDctosOrdenesProgreso.fechaFin\n"
                + "	  ,tblDctosOrdenesProgreso.[idCausa]\n"
                + "       ,tblDctosOrdenesProgreso.[estado]\n"
                + "       ,tblDctosOrdenesProgreso.[itemPadre]\n"
                + "       ,tblDctosOrdenesProgreso.[cantidadPedida]\n"
                + "       ,tblDctosOrdenesProgreso.[pesoPedido]\n"
                + "       ,[idControl]\n"
                + "	  ,tblDctosOrdenesDetalle.IDPLU\n"
                + "	  ,[idControlTipo]\n"
                + "       ,[observacion]\n"
                + "       ,[idUsuario]\n"
                + "       ,[cantidadPendiente]\n"
                + "       ,[vrCostoBaseMAT]\n"
                + "       ,[vrCostoBaseCIF]\n"
                + "       ,[vrCostoBaseMOD]\n"
                + "       ,[pesoTara]\n"
                + "       ,[idTipoCausa]\n"
                + "       ,[idDctoNitCC]\n"
                + "       ,[idOrdenCruce]\n"
                + "       ,tblDctosOrdenesProgreso.[idMaquina]\n"
                + "      FROM tblDctosOrdenesDetalle LEFT JOIN tblDctosOrdenesProgreso ON tblDctosOrdenesProgreso.idOrden=tblDctosOrdenesDetalle.idOrdenOrigen AND tblDctosOrdenesDetalle.idOperacion = tblDctosOrdenesProgreso.idOperacion AND tblDctosOrdenesProgreso.item = tblDctosOrdenesDetalle.itemPadre \n"
                + "	 WHERE tblDctosOrdenesDetalle.IDORDEN = " + getOrden() + ";";

        return ejecutarSentencia(sql);
    }

    public boolean debitarTraslado() {
        String sql
                = "  UPDATE tblDctosOrdenesProgreso \n"
                + " SET tblDctosOrdenesProgreso.cantidadTerminada "
                + "  = tblDctosOrdenesProgreso.cantidadTerminada - tblDctosOrdenesDetalle.cantidad,\n"
                + " tblDctosOrdenesProgreso.pesoTerminado"
                + "  = tblDctosOrdenesProgreso.pesoTerminado - tblDctosOrdenesDetalle.PESOTEORICO,\n"
                + " tblDctosOrdenesProgreso.pesoTara = 0\n"
                + " FROM tblDctosOrdenesDetalle \n"
                + " INNER JOIN tblDctosOrdenesProgreso "
                + " ON tblDctosOrdenesDetalle.idOrdenOrigen = tblDctosOrdenesProgreso.idOrden \n"
                + " WHERE tblDctosOrdenesDetalle.IDORDEN = " + getOrden()
                + " AND tblDctosOrdenesDetalle.IDPLU = tblDctosOrdenesProgreso.idPlu "
                + " AND tblDctosOrdenesDetalle.itemPadre = tblDctosOrdenesProgreso.item  "
                + " AND tblDctosOrdenesDetalle.idOperacion = tblDctosOrdenesProgreso.idOperacion;";

        return ejecutarSentencia(sql);
    }

    public boolean insertarObservacion() {
        String sql
                = "   UPDATE tblDctosOrdenes "
                + " SET OBSERVACION  = '" + getObservacion() + "'"
                + ",ESTADO = 1  "
                + ",idBodegaOrigen = " + getNumOrdenOrigen()
                + ",idBodegaDestino = " + getNumOrdenDestino()
                + " WHERE  IDORDEN = " + getOrden() + ";";

        return ejecutarSentencia(sql);
    }

    public int getNumDocumento() {

        int item = 0;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "SELECT numeroOrden FROM tblDctosOrdenes WHERE idOrden  = " + getOrden() + ";";
        try {
            c = this.jdbcAccess.getConnection();
            ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                item = rs.getInt(1);
            }
            rs.close();
            return item;
        } catch (NamingException ne) {
            System.out.println("NamingException in " + nombreClase + " " + ne);
            return item;
        } catch (SQLException sqle) {
            System.out.println("SQLException in" + nombreClase);
            String sqlMessage = sqle.getMessage();
            String sqlState = sqle.getSQLState();
            int vendorCode = sqle.getErrorCode();
            System.out.println("Ocurrio una  excejdbcAccession");
            System.out.println("Mensaje " + sqlMessage);
            System.out.println("SQL State " + sqlState);
            System.out.println("Vendor Code " + vendorCode);
            return item;
        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
            return item;
        } finally {
            this.jdbcAccess.cleanup(c, ps, null);
            return item;
        }
    }

    public List<Traslado_DTO> dameHistorial() {
        ArrayList<Traslado_DTO> dtoLista = new ArrayList<Traslado_DTO>();
        String sql = "SELECT * FROM tblDctosOrdenes WHERE IDTIPOORDEN  =  " + tipoOp
                + " AND fechaOrden BETWEEN '" + getFechaInicial() + "' AND  '" + getFechaFinal() + " 23:59:59' AND " + getTipoOrden() + " LIKE '" + getOrdenSTR() + "' ORDER BY IDORDEN DESC";
        PreparedStatement ps = null;
        try {
            c = pc.getConnection();
            ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Traslado_DTO dto = new Traslado_DTO();
                dto.setNumeroDocumento(rs.getInt("numeroOrden"));
                dto.setOrden(rs.getInt("idorden"));
                dto.setFechaOrden(sdf2.format(rs.getDate("fechaorden")));
                dto.setNumOrdenOrigen(rs.getInt("idBodegaOrigen"));
                dto.setNumOrdenDestino(rs.getInt("idBodegaDestino"));
                dto.setObservacion(rs.getString("OBSERVACION"));
                dtoLista.add(dto);
            }
            rs.close();
            return dtoLista;
        } catch (NamingException ne) {
            System.out.println("NamingException in " + nombreClase + " " + ne);
            return dtoLista;
        } catch (SQLException sqle) {
            System.out.println("SQLException in" + nombreClase);
            String sqlMessage = sqle.getMessage();
            String sqlState = sqle.getSQLState();
            int vendorCode = sqle.getErrorCode();
            System.out.println("Ocurrio una  excejdbcAccession");
            System.out.println("Mensaje " + sqlMessage);
            System.out.println("SQL State " + sqlState);
            System.out.println("Vendor Code " + vendorCode);
            return dtoLista;
        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
            return dtoLista;
        } finally {
            this.jdbcAccess.cleanup(c, ps, null);
            return dtoLista;
        }

    }

    public boolean borrarPLU() {
        String sql = "DELETE FROM tblDctosOrdenesDetalle WHERE idPLU = " + getIdPlu() + " AND  item = " + getItem() + " AND  idOperacion = " + getSk_operacion() + " AND idorden = " + getOrden() + ";";
        return ejecutarSentencia(sql);
    }

    //Permie borrar todos los PLU de una orden recuperada
    public boolean borrarPLUS() {
        String sql = "DELETE FROM tblDctosOrdenesDetalle WHERE idorden = " + getOrden() + ";";
        return ejecutarSentencia(sql);
    }

}
