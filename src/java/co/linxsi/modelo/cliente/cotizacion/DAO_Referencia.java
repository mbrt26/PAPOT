/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.linxsi.modelo.cliente.cotizacion;

import co.linxsi.modelo.maestro.accesorios.Accesorios_DTO;
import co.linxsi.modelo.maestro.operaciones.Operaciones_DTO;
import co.linxsi.modelo.maestro.operaciones.Proceso_Materia_DTO;
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
 * @author Edgar J García L
 */
public class DAO_Referencia extends DTO_Referencia {

    private static final String DATA_SOURCE_NAME = "CommerceAccess";
    private JDBCAccess pc;

    public DAO_Referencia() {
        pc = new JDBCAccess(DATA_SOURCE_NAME);

    }

    public boolean ingresa() {
        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;
//Permite ingresar las bodegas por primera vez en la tabla
        Connection c = null;
        PreparedStatement ps = null;
        String sql
                = "BEGIN TRAN "
                + "IF EXISTS(SELECT * FROM [BDCommerce].[dbo].[tblCotClienteRef] WHERE idPlu= " + getIdPlu() + ")\n"
                + "BEGIN \n"
                + "UPDATE [BDCommerce].[dbo].[tblCotClienteRef] SET "
                + "       [nombreReferencia]='" + getNombreReferencia() + "'"
                + "      ,[ancho]=" + getAncho()
                + "      ,[largo]=" + getLargo()
                + "      ,[calibre]=" + getCalibre()
                + "      ,[ficha]=" + getFicha()
                + "      ,[referencia]='" + getReferencia() + "'"
                + "      ,[bache]=" + getBache()
                + "      ,[retal]=" + getRetal()
                + "      ,[pesoMillar]=" + getPesoMillar()
                + "      ,[flete]=" + getFlete()
                + "      ,[precioUnitario]=" + getPrecio()
                + "      ,[precioVenta]=" + getPrecioVenta()
                + "      ,[material]= '" + getMaterial() + "'"
                + "      ,[observacion]= '" + getObservacion() + "'"
                + " WHERE [idPlu]=" + getIdPlu()
                + "	  END\n"
                + "	  ELSE\n"
                + "	  BEGIN \n"
                + "INSERT INTO tblCotClienteRef "
                + "      ([idPlu]\n"
                + "      ,[nombreReferencia]\n"
                + "      ,[estado]\n"
                + "      ,[ancho]\n"
                + "      ,[largo]\n"
                + "      ,[calibre]\n"
                + "      ,[ficha]\n"
                + "      ,[creado]\n"
                + "      ,idCliente\n"
                + "      ,referencia\n"
                + "      ,[pesoMillar]"
                + "      ,bache\n"
                + "      ,[retal]"
                + "      ,[flete]"
                + "      ,[precioUnitario]"
                + "      ,[precioVenta]"
                + "      ,[material]"
                + "      ,[observacion]"
                + ") "
                + "VALUES ("
                + getIdPlu()
                + ","
                + "'" + getNombreReferencia() + "'"
                + ","
                + 1
                + ","
                + getAncho()
                + ","
                + getLargo()
                + ","
                + getCalibre()
                + ","
                + getFicha()
                + ","
                + "GETDATE()"
                + ","
                + getIdCliente()
                + ","
                + "'" + getReferencia() + "'"
                + ","
                + getPesoMillar()
                + ","
                + getBache()
                + ","
                + getRetal()
                + ","
                + getFlete()
                + ","
                + getPrecio()
                + ","
                + getPrecioVenta()
                + ","
                + "'" + getMaterial() + "'"
                + ","
                + "'" + getObservacion() + "'"
                + ")"
                + " END\n"
                + "COMMIT TRAN";
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
                + "  FROM [BDCommerce].[dbo].[tblCotClienteRef] "
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
        List<DTO_Referencia> lista;
        lista = new ArrayList();

        String operador = getIdCliente() == 0 ? "<>" : "=";
        Connection c = null;
        String sql = "SELECT\n"
                + " [dbo].[tblCotClienteRef].[idCliente],\n"
                + " [nombreCliente],\n"
                + " [dbo].[tblCotClienteRef].idPlu,\n"
                + " [dbo].[tblCotClienteRef].ficha,\n"
                + " [dbo].[tblCotClienteRef].ancho,\n"
                + " [dbo].[tblCotClienteRef].largo,\n"
                + " [dbo].[tblCotClienteRef].calibre,\n"
                + " [dbo].[tblCotClienteRef].referencia,\n"
                + " [nombreReferencia], \n"
                + " precioUnitario as PrecioVenta,\n"
                + " ROUND( 100*((bache*1000/pesoMillar*precioUnitario)-(CostosProcesos+CostosMateria+flete*bache))/(bache*1000/pesoMillar*precioUnitario),2) AS MARGEN  \n"
                + " FROM [BDCommerce].[dbo].[tblCotClienteRef] 	\n"
                + " INNER JOIN [dbo].[vistaCostoProceso] ON [dbo].[tblCotClienteRef].idPlu=[dbo].[vistaCostoProceso].idplu\n"
                + " INNER JOIN [dbo].[vistaCostoMateria] ON [dbo].[tblCotClienteRef].idPlu=[dbo].[vistaCostoMateria].idplu\n"
                + " INNER JOIN [dbo].[tblClienteCotizacion] ON [dbo].[tblClienteCotizacion].idCliente=[dbo].[tblCotClienteRef].idCliente\n"
                + " WHERE  [dbo].[tblCotClienteRef].idCliente "+operador+getIdCliente()+" \n"
                + " ORDER BY  [dbo].[tblClienteCotizacion].nombreCliente,MARGEN;";

        PreparedStatement ps = null;
        try {
            c = this.pc.getConnection();
            ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DTO_Referencia dto = new DTO_Referencia();
                dto.setIdPlu(rs.getInt("idPlu"));
                dto.setNombreCliente(rs.getString("nombreCliente"));
                dto.setNombreReferencia(rs.getString("nombreReferencia"));
                dto.setAncho(rs.getDouble("ancho"));
                dto.setLargo(rs.getDouble("largo"));
                dto.setCalibre(rs.getDouble("calibre"));
                dto.setMargen(rs.getFloat("margen"));
                dto.setFicha(rs.getInt("ficha"));
                dto.setReferencia(rs.getString("referencia"));

                dto.setPrecioVenta(rs.getDouble("precioVenta"));
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

    public List<DTO_Referencia> getHistoryByCliente() {
        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();
        List<DTO_Referencia> lista;
        lista = new ArrayList();
        Connection c = null;
        String sql = "SELECT DISTINCT\n"
                + " tmpDET.referenciacliente,     \n"
                + " tmpDET.idficha,tmpDET.idCliente,\n"
                + " (SELECT top 1 vrEscala FROM [BDCommerce].[dbo].[tblPlusFicha] where idFicha= tmpDET.idficha  and idEscala =501 and idOperacion=1) AS ANCHO\n"
                + " ,(SELECT top 1 vrEscala FROM [BDCommerce].[dbo].[tblPlusFicha] where idFicha= tmpDET.idficha  and idEscala =502 and idOperacion=1) AS LARGO\n"
                + "  ,(SELECT top 1 vrEscala FROM [BDCommerce].[dbo].[tblPlusFicha] where idFicha= tmpDET.idficha  and idEscala =503 and idOperacion=1) AS CALIBRE\n"
                + "  ,(SELECT top 1 nombreReferencia FROM [BDCommerce].[dbo].[tblPlusFicha] where idFicha= tmpDET.idficha   and idOperacion=1) AS REFERENCIA\n"
                + " \n"
                + "FROM   tblterceros\n"
                + "       INNER JOIN tblagendalogvisitas\n"
                + "               ON tblterceros.idcliente = tblagendalogvisitas.idcliente\n"
                + "       INNER JOIN ctrlusuarios\n"
                + "               ON tblagendalogvisitas.idusuario = ctrlusuarios.idusuario\n"
                + "       INNER JOIN (SELECT tbldctosordenesdetalle.referenciacliente,                        \n"
                + "                          tbldctosordenes.idlog,                         \n"
                + "                          tbldctosordenes.idficha,\n"
                + "                   tblDctosOrdenes.idCliente                                    \n"
                + "                   FROM   tbldctosordenes\n"
                + "                          INNER JOIN tbldctosordenesdetalle\n"
                + "                                  ON tbldctosordenes.idlocal =\n"
                + "                                     tbldctosordenesdetalle.idlocal\n"
                + "                                     AND tbldctosordenes.idtipoorden =\n"
                + "                                         tbldctosordenesdetalle.idtipoorden\n"
                + "                                     AND tbldctosordenes.idorden =\n"
                + "                                         tbldctosordenesdetalle.idorden) AS\n"
                + "                  tmpDET\n"
                + "               ON tmpDET.idlog = tblagendalogvisitas.idlog\n"
                + "WHERE  tblagendalogvisitas.idlog IN (SELECT tblagendalogvisitas.idlog\n"
                + "                                     FROM   tblagendalogvisitas\n"
                + "                                            INNER JOIN tbldctosordenes\n"
                + "                                                    ON tblagendalogvisitas.idlog\n"
                + "                                                       =\n"
                + "                                                       tbldctosordenes.idlog\n"
                + "                                                       AND\n"
                + "                                            tbldctosordenes.idlocal = 1\n"
                + "                                                       AND\n"
                + "                                            tbldctosordenes.idtipoorden\n"
                + "                                            = 59\n"
                + "                                                       AND\n"
                + "                                            tbldctosordenes.numeroorden\n"
                + "                                            != 0\n"
                + "                                                       AND\n"
                + "                                            tbldctosordenes.idcliente =\n"
                + "                                            '" + getIdCliente() + " '\n"
                + "                                                       AND\n"
                + "                                            tblterceros.idtipotercero =\n"
                + "                                            1)\n"
                + "ORDER  BY \n"
                + "          tmpDET.referenciaCliente\n"
                + "    ";
        PreparedStatement ps = null;
        try {
            c = this.pc.getConnection();
            ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DTO_Referencia dto = new DTO_Referencia();
                dto.setNombreReferencia(rs.getString("referenciaCliente"));
                dto.setAncho(rs.getDouble("ancho"));
                dto.setLargo(rs.getDouble("largo"));
                dto.setCalibre(rs.getDouble("calibre"));
                dto.setFicha(rs.getInt("idficha"));
                dto.setReferencia(rs.getString("referencia"));
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

    public List<Operaciones_DTO> getProcesoByRef(int idReferencia) {
        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();
        List<Operaciones_DTO> lista = new ArrayList();
        Connection c = null;
        String sql = "SELECT TOP (100) "
                + "[porcion]\n"
                + " ,[idReferencia]\n "
                + ",[idProceso]      "
                + ",[costo_retal]\n "
                + ",[nombre_operacion]\n "
                + "FROM [BDCommerce].[dbo].[tblCotProcesosReferencias] "
                + "INNER JOIN [BDCommerce].[dbo].[tblOperaciones] "
                + "ON idProceso = [sk_operacion] WHERE idReferencia=" + idReferencia + " ORDER BY idProceso ";
        PreparedStatement ps = null;
        try {
            c = this.pc.getConnection();
            ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Operaciones_DTO dto = new Operaciones_DTO();
                dto.setCantidad(rs.getDouble("porcion"));
                dto.setCosto_retal(rs.getDouble("costo_retal"));
                dto.setNombre(rs.getString("nombre_operacion"));
                dto.setSk_operacion(rs.getInt("idProceso"));
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

    public int getMaxRef() {
        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();
        int max = 0;
        Connection c = null;
        String sql = "SELECT (ISNULL(MAX(idpLU) , 0)+1)  AS MAX FROM [BDCommerce].[dbo].tblCotClienteRef ";
        PreparedStatement ps = null;
        try {
            c = this.pc.getConnection();
            ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                max = rs.getInt("MAX");

            }
            rs.close();
            return max;
        } catch (NamingException ne) {
            System.out.println("NamingException in " + nombreClase + " " + ne);
            return max;

        } catch (SQLException sqle) {
            System.out.println("SQLException in" + nombreClase);
            String sqlMessage = sqle.getMessage();
            String sqlState = sqle.getSQLState();
            int vendorCode = sqle.getErrorCode();
            System.out.println("Ocurrio una  excepcion");
            System.out.println("Mensaje " + sqlMessage);
            System.out.println("SQL State " + sqlState);
            System.out.println("Vendor Code " + vendorCode);
            return max;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
            return max;

        } finally {
            this.pc.cleanup(c, ps, null);
            return max;

        }
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
                + "  FROM [BDCommerce].[dbo].[tblCotMateriasReferencias] "
                + "INNER JOIN [BDCommerce].[dbo].[tblOperacionMateria] ON idMateria = [sk_operacion_materia]"
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

    public List<Accesorios_DTO> getAccesorioByRef(int idReferencia) {
        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();
        List<Accesorios_DTO> lista = new ArrayList();
        Connection c = null;
        String sql = "SELECT TOP (1000) "
                + "[tblCotAccesoriosReferencias].[id]\n"
                + ",[idReferencia]\n"
                + ",[idAccesorio]"
                + ",nombre "
                + ",costo "
                + "FROM [BDCommerce].[dbo].[tblCotAccesoriosReferencias] INNER JOIN tblAccesorios ON idAccesorio = tblAccesorios.id "
                + "WHERE idReferencia =" + idReferencia
                + " ORDER BY [tblCotAccesoriosReferencias].[id];";
        PreparedStatement ps = null;
        try {
            c = this.pc.getConnection();
            ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Accesorios_DTO dto = new Accesorios_DTO();
                dto.setSk_accesorio(rs.getInt("idAccesorio"));
                dto.setPrecio(rs.getDouble("costo"));
                dto.setNombre(rs.getString("nombre"));
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

    public DTO_Referencia getOneRefByClient() {

        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();

        DTO_Referencia dto = new DTO_Referencia();
        Connection c = null;
        String sql = "SELECT TOP 1  "
                + "[idPlu]\n"
                + "      ,[nombreReferencia]\n"
                + "      ,[estado]\n"
                + "      ,[ancho]\n"
                + "      ,[largo]\n"
                + "      ,[calibre]\n"
                + "      ,[ficha]\n"
                + "      ,[creado]\n"
                + "      ,[pesoMillar]\n"
                + "      ,[referencia]\n"
                + "      ,[bache]\n"
                + "      ,[retal]\n"
                + "      ,[referencia]\n"
                + "      ,[flete]\n"
                + "      ,[precioUnitario]"
                + "      ,[material]\n"
                + "      ,[observacion]\n"
                + "  FROM [BDCommerce].[dbo].[tblCotClienteRef] "
                + " WHERE estado = 1 "
                + " AND idPlu = " + getIdPlu();

        PreparedStatement ps = null;
        try {
            c = this.pc.getConnection();
            ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                dto.setIdPlu(rs.getInt("idPlu"));
                dto.setNombreReferencia(rs.getString("nombreReferencia"));
                dto.setAncho(rs.getDouble("ancho"));
                dto.setLargo(rs.getDouble("largo"));
                dto.setCalibre(rs.getDouble("calibre"));
                dto.setPesoMillar(rs.getDouble("pesoMillar"));
                dto.setCreado(rs.getString("creado"));
                dto.setBache(rs.getDouble("bache"));
                dto.setRetal(rs.getDouble("retal"));
                dto.setFicha(rs.getInt("ficha"));
                dto.setReferencia(rs.getString("referencia"));
                dto.setFlete(rs.getDouble("flete"));
                dto.setPrecio(rs.getDouble("precioUnitario"));
                dto.setMaterial(rs.getString("material"));
                dto.setObservacion(rs.getString("observacion"));
            }
            rs.close();
            return dto;
        } catch (NamingException ne) {
            System.out.println("NamingException in " + nombreClase + " " + ne);
            return dto;
        } catch (SQLException sqle) {
            System.out.println("SQLException in" + nombreClase);
            String sqlMessage = sqle.getMessage();
            String sqlState = sqle.getSQLState();
            int vendorCode = sqle.getErrorCode();
            System.out.println("Ocurrio una  excepcion");
            System.out.println("Mensaje " + sqlMessage);
            System.out.println("SQL State " + sqlState);
            System.out.println("Vendor Code " + vendorCode);

            return dto;
        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
            return dto;
        } finally {
            this.pc.cleanup(c, ps, null);
            return dto;
        }
    }

    public boolean ingresarProcesosReferencia(Operaciones_DTO dto, int idPlu) {
        String sql
                = "INSERT INTO  [BDCommerce].[dbo].[tblCotProcesosReferencias] "
                + "([id]\n"
                + "      ,[porcion]\n"
                + "      ,[idReferencia]\n"
                + "      ,[idProceso]) "
                + "VALUES (ISNULL((SELECT (MAX(id) + 1) FROM [BDCommerce].[dbo].tblCotProcesosReferencias ),1)"
                + "," + dto.getCantidad()
                + "," + idPlu
                + "," + dto.getSk_operacion() + ")";
        return ejecutarSentencia(sql);

    }

    public boolean borrarMaterias(int idPlu) {
        String sql = "DELETE FROM [BDCommerce].[dbo].[tblCotMateriasReferencias] WHERE idReferencia = " + idPlu;
        return ejecutarSentencia(sql);

    }

    public boolean borrarProcesos(int idPlu) {
        String sql = "DELETE FROM [BDCommerce].[dbo].[tblCotProcesosReferencias] WHERE idReferencia = " + idPlu;
        return ejecutarSentencia(sql);

    }

    public boolean ingresarMateriasReferencia(Proceso_Materia_DTO dto, int idPlu) {
        String sql
                = "INSERT INTO  [BDCommerce].[dbo].[tblCotMateriasReferencias] "
                + "(    [id]\n"
                + "      ,[porcion]\n"
                + "      ,[batch]\n"
                + "      ,[idReferencia]\n"
                + "      ,[idMateria] )"
                + "VALUES (ISNULL((SELECT (MAX(id) + 1) FROM [BDCommerce].[dbo].tblCotMateriasReferencias ),1)"
                + "," + dto.getCantidad()
                + "," + dto.getBache()
                + "," + idPlu
                + "," + dto.getSk_operacion_materia() + ")";
        return ejecutarSentencia(sql);

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

    public boolean borrarAccesorios(int idPlu) {
        String sql = "DELETE FROM [BDCommerce].[dbo].[tblCotAccesoriosReferencias] WHERE idReferencia = " + idPlu;
        return ejecutarSentencia(sql);
    }

    public boolean ingresarAccesorioReferencia(Accesorios_DTO dto, int idPlu) {

        String sql = "INSERT INTO [BDCommerce].[dbo].[tblCotAccesoriosReferencias] "
                + "( [id]\n"
                + "      ,[idReferencia]\n"
                + "      ,[idAccesorio] "
                + "      ,costo) VALUES("
                + "((ISNULL((SELECT (MAX(id) + 1) FROM [BDCommerce].[dbo].tblCotAccesoriosReferencias ),1)))"
                + "," + idPlu
                + "," + dto.getSk_accesorio()
                + "," + dto.getPrecio()
                + ")";
        return ejecutarSentencia(sql);
    }
//Este metodo permite obtener el idFicha por defecto a los clientes que no tienen fichas vendidas o historicas

    public int obtenerIdFichaMaximo() {
        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();
        int max = 80000;// Se coloca valor por defecto 80.000
        Connection c = null;
        String sql = "SELECT ISNULL((MAX(ficha) + 1),80000) AS MAX\n"
                + " FROM [BDCommerce].[dbo].[tblCotClienteRef];";
        PreparedStatement ps = null;
        try {
            c = this.pc.getConnection();
            ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int xMax = rs.getInt("MAX");
                max = xMax < 80000 ? 80000 : xMax;
}
            rs.close();
            return max;
        } catch (NamingException ne) {
            System.out.println("NamingException in " + nombreClase + " " + ne);
            return max;

        } catch (SQLException sqle) {
            System.out.println("SQLException in" + nombreClase);
            String sqlMessage = sqle.getMessage();
            String sqlState = sqle.getSQLState();
            int vendorCode = sqle.getErrorCode();
            System.out.println("Ocurrio una  excepcion");
            System.out.println("Mensaje " + sqlMessage);
            System.out.println("SQL State " + sqlState);
            System.out.println("Vendor Code " + vendorCode);
            return max;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
            return max;

        } finally {
            this.pc.cleanup(c, ps, null);
            return max;

        }
    }      

}
