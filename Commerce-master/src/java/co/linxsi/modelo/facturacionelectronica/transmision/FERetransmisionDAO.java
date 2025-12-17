package co.linxsi.modelo.facturacionelectronica.transmision;

import com.solucionesweb.lasayudas.JDBCAccess;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

public class FERetransmisionDAO extends FERetransmisionDTO {

    // Nombre JNDI del Data Source que esta clase requiere
    private static final String DATA_SOURCE_NAME = "CommerceAccess";
    // Clase helper usada para acceder a la base de datos
    private JDBCAccess jdbcAccess;

    // Metodo constructor por defecto sin parametros
    public FERetransmisionDAO() {

        // Al instanciar el Bean establece la conexion a la base de datos
        jdbcAccess = new JDBCAccess(DATA_SOURCE_NAME);
    }

    public List<FERetransmisionDTO> ListaFE() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        // Variable de fachada de los datos
        List<FERetransmisionDTO> Lista = new ArrayList<FERetransmisionDTO>();
        FERetransmisionDTO ferdto;

        Connection c = null;

        final String sql = "SELECT d.IDTIPOORDEN,\n"
                + "		d.IDORDEN,\n"
                + "		d.idCliente,\n"
                + "		d.nombreTercero,\n"
                + "		convert(varchar(10),d.fechaDcto,111) fechaDcto,\n"
                + "		f.iddcto, \n"
                + "		f.idDctoDian,\n"
                + "		f.prefijoDian,\n"
                + "		f.status,\n"
                + "		f.url,\n"
                + "	ISNULL((SELECT TOP 1 concat(prefijoDian,idDctoDian)  \n"
                + "						FROM tblFacturaElectronica \n"
                + "						WHERE idOrden= d.idordencruce  \n"
                + "						AND f.tipo_xml = 1),0) AS dctoDianOrigen\n"
                + "FROM tblDctos d \n"
                + "INNER JOIN tblFacturaElectronica f\n"
                + "ON f.idOrden = d.idOrden\n"
                + "AND f.idtipoorden = d.IDTIPOORDEN\n"
                + "WHERE f.tipo_xml = 1\n"
                + "AND f.status = 'false'\n";

        PreparedStatement ps = null;

        try {

            c = this.jdbcAccess.getConnection();
            ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            //
            while (rs.next()) {
                ferdto = new FERetransmisionDTO();
                //
                ferdto.setIddctoDian(rs.getInt("idDctoDian"));
                ferdto.setIddcto(rs.getInt("idDcto"));
                ferdto.setPrefijo(rs.getString("prefijoDian"));
                ferdto.setNombreCliente(rs.getString("nombreTercero"));
                ferdto.setIdCliente(rs.getString("idCliente"));
                ferdto.setUrl(rs.getString("url"));
                ferdto.setEstado(rs.getBoolean("status"));
                ferdto.setFechaDcto(rs.getString("fechaDcto"));                
                ferdto.setIdTipoOrden(rs.getInt("IDTIPOORDEN"));
                ferdto.setIdorden(rs.getInt("IDORDEN"));
                ferdto.setIddcto(rs.getInt("iddcto"));
                ferdto.setDctoDianOrigen(rs.getString("dctoDianOrigen"));

                Lista.add(ferdto);
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
            return Lista;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {

            // Cierra la conexion a la base de datos limpiando la statement usada y la
            jdbcAccess.cleanup(c, ps, null);
            return Lista;
        }
    }

    public String xmlDian() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        String xmlDian = "";
        Connection c = null;

        String sql
                = "declare @orden int = " + getIdorden() + ", @tipoorden int = " + getIdTipoOrden() + "\n"
                + "select\n"
                + "		isnull((select top 1	xml from tblFacturaElectronica where idOrden = @orden AND idTipoOrden = @tipoorden and tipo_xml = 1),0) head,\n"
                + "		isnull((select top 1	xml from tblFacturaElectronica where idOrden = @orden AND idTipoOrden = @tipoorden and tipo_xml = 2),0) dtl,\n"
                + "		isnull((select top 1	xml from tblFacturaElectronica where idOrden = @orden AND idTipoOrden = @tipoorden and tipo_xml = 3),0) payment,\n"
                + "		isnull((select top 1	xml from tblFacturaElectronica where idOrden = @orden AND idTipoOrden = @tipoorden and tipo_xml = 4),0) tax,\n"
                + "		isnull((select top 1	xml from tblFacturaElectronica where idOrden = @orden AND idTipoOrden = @tipoorden and tipo_xml = 5),0) company,\n"
                + "		isnull((select top 1	xml from tblFacturaElectronica where idOrden = @orden AND idTipoOrden = @tipoorden and tipo_xml = 6),0) trc,\n"
                + "		isnull((select top 1	xml from tblFacturaElectronica where idOrden = @orden AND idTipoOrden = @tipoorden and tipo_xml = 7),0) customer,\n"
                + "		isnull((select top 1	xml from tblFacturaElectronica where idOrden = @orden AND idTipoOrden = @tipoorden and tipo_xml = 8),0) netime\n"
                + "from  tblFacturaElectronica f\n"
                + "where f.tipo_xml = 1\n"
                + "AND idOrden = @orden AND idTipoOrden = @tipoorden\n"
                + "and f.status = 'false'";

        PreparedStatement ps = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            c = jdbcAccess.getConnection();
            // Prepara la sentencia de Busqueda
            ps = c.prepareStatement(sql); 
            // Obtiene el resultset
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                xmlDian = rs.getString("head")+rs.getString("dtl")+rs.getString("payment")+rs.getString("tax")+rs.getString("company")+rs.getString("trc")+rs.getString("customer")+rs.getString("netime");
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
            return xmlDian;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {

            // Cierra la conexion a la base de datos limpiando la statement usada y la
            jdbcAccess.cleanup(c, ps, null);
            return xmlDian;
        }
    }

    public boolean deleteItemsOnQuantityZero(final int idOrden) {
        final String sql = "DELETE [BDCommerce].dbo.[tblDctosOrdenesDetalle]\n"
                + " WHERE CANTIDAD = 0 \n"
                + " AND IDORDEN = "+idOrden+"\n";
      return  ejecutarSentencia(sql);
   }
    public boolean updateDateCreditNote(final int idOrden) {
        final String sql = "UPDATE [BDCommerce].[dbo].[tblDctos]\n"
                + "SET [fechaDcto] = CAST(GETDATE() AS DATE)\n"
                + "WHERE IDORDEN = " + idOrden + "\n"
                + "AND IDTIPOORDEN = 29";
        return ejecutarSentencia(sql);
   }
    
    public boolean ejecutarSentencia(final String sql) {

        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;
//Permite ingresar las bodegas por primera vez en la tabla
        Connection c = null;
        PreparedStatement ps = null;

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
            this.jdbcAccess.cleanup(c, ps, null);
            return okIngresar;
        }
}
}
