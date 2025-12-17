package co.linxsi.modelo.facturacionelectronica;

import co.linxsi.modelo.maestro.local.Local_DTO;
import com.solucionesweb.lasayudas.JDBCAccess;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

public class FacturacionElectronicaDAO extends FacturacionElectronicaDTO {

//    private Connection conect;

    // Nombre JNDI del Data Source que esta clase requiere
    private static final String DATA_SOURCE_NAME = "CommerceAccess";
    // Clase helper usada para acceder a la base de datos
    private JDBCAccess jdbcAccess;

    // Metodo constructor por defecto sin parametros
    public FacturacionElectronicaDAO() {

        // Al instanciar el Bean establece la conexion a la base de datos
        jdbcAccess = new JDBCAccess(DATA_SOURCE_NAME);

        /*    try {
            String connectionUrl = "jdbc:sqlserver://127.0.0.1:1433;databaseName=BDCommerce;user=sa;password=Tecnnova2018;";
            this.conect = DriverManager.getConnection(connectionUrl);
            System.out.println("Conectado.");
        } catch (SQLException ex) {
            System.out.println("Error.");
        }*/
    }

    // ingresaDcto
    public boolean ingresaXml() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection c = null;

        String sql
                = "INSERT INTO tblFacturaElectronica (idOrden,         "
                + "                      idTipoOrden,             "
                + "                      idDcto,              "
                + "                      idDctoDian,           "
                + "                      prefijoDian,           "
                + "                      tipo_xml,              "
                + "                      xml,              "
                + "                      status,            "
                + "                      tracer,               "
                + "                      url)        "
                + "VALUES ( " + getIdOrden() + ","
                + getIdTipoOrden() + ","
                + getIdDcto() + ","
                + getIdDctoDian() + ",'"
                + getPrefijo() + "',"
                + getTipo_xml() + ",'"
                + getXml() + "','"
                + isStatus() + "','"
                + getTracer() + "','"
                + getUrl() + "')";

        PreparedStatement ps = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            c = jdbcAccess.getConnection();
//            c = this.conect;
            ps = c.prepareStatement(sql);
            // Obtiene el resultset
            ps.execute();
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
            return okIngresar;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {
            // Cierra la conexion a la base de datos limpiando la statement usada y la
            jdbcAccess.cleanup(c, ps, null);
            return okIngresar;
        }
    }

    // actualiza respuesta de la DIAN
    public boolean updateFE(String status, String error, String TracerID, String applicationResponseDian, int idorden) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okFactura = false;
        Connection c = null;

        String sql
                = " UPDATE tblFacturaElectronica                       "
                + " SET status   =      '" + status + "',   "
                + " errorList   =      '" + error + "',   "
                + " trackId   =      '" + TracerID + "',   "
                + " applicationResponseDian   =      '" + applicationResponseDian + "'   "
                + " WHERE idorden          =      "
                + idorden + "   "
                + " AND   tipo_xml         =   1   ";

        PreparedStatement ps = null;

        try {
            // Obtiene una conexion a la base de datos, requiere Try
            c = jdbcAccess.getConnection();
            // Prepara la sentencia de Busqueda
            ps = c.prepareStatement(sql);
            // Obtiene el resultset
            ps.execute();
            okFactura = true;

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
            return okFactura;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {
            // Cierra conexion
            jdbcAccess.cleanup(c, ps, null);
            return okFactura;
        }
    }

    // actualiza respuesta de la DIAN
    public boolean updateFEUrlPdf(String url, int idorden) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okFactura = false;
        Connection c = null;

        String sql
                = " UPDATE tblFacturaElectronica                       "
                + " SET url   =      '" + url + "' "
                + " WHERE idorden          =      "
                + idorden + "   "
                + " AND   tipo_xml         =   1   ";

        PreparedStatement ps = null;

        try {
            // Obtiene una conexion a la base de datos, requiere Try
            c = jdbcAccess.getConnection();
            // Prepara la sentencia de Busqueda
            ps = c.prepareStatement(sql);
            // Obtiene el resultset
            ps.execute();
            okFactura = true;

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
            return okFactura;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {
            // Cierra conexion
            jdbcAccess.cleanup(c, ps, null);
            return okFactura;
        }
    }

    public int maxDctoDian() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        int maximaIdDcto = 0;
        Connection c = null;

        String sql
                = "SELECT TOP 1 MAX(idDctoDian)        "
                + "                 AS maximaIdDcto   "
                + "FROM tblFacturaElectronica                      "
                + "where  idTipoOrden = ( ? ) ";

        PreparedStatement ps = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            c = jdbcAccess.getConnection();
            // Prepara la sentencia de Busqueda
            ps = c.prepareStatement(sql);
            // inicializa los valore de los parametros
            ps.setInt(1, getIdTipoOrden());

            // Obtiene el resultset
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                maximaIdDcto = rs.getInt("maximaIdDcto");
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
            return maximaIdDcto;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {

            // Cierra la conexion a la base de datos limpiando la statement usada y la
            jdbcAccess.cleanup(c, ps, null);
            return maximaIdDcto;
        }
    }

    public FEHeadDTO ListaHead(int dcto, int tipodcto) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        // Variable de fachada de los datos
        FEHeadDTO fehdto = new FEHeadDTO();

        Connection connection = null;

        String sql
                = "select	l.nombreLocal,\n"
                + "             (SUBSTRING(replace(l.nit,'.',''),4,10)) nit,\n"
                + "              l.telefono,\n"
                + "              c.prefijo,\n"
                + "              Concat(c.resolucion,' PREFIJO ',c.prefijo,' ',c.rango) resolucion,\n"
                + "              d.IDTIPOORDEN,\n"
                + "             concat(convert(varchar,d.fechaDcto,110),' ',convert(varchar,GETDATE(),108)) fecha,\n"
                + "              d.IDUSUARIO,\n"
                + "              u.nombreUsuario,\n"
                + "              d.diasPlazo,\n"
                + "              concat(convert(varchar,dateadd( day, d.diasPlazo, d.fechaDcto),110),' ',convert(varchar,GETDATE(),108)) fechavencimiento,\n"
                + "              d.idCliente,\n"
                + "              d.nombreTercero,\n"
                + "              round(((round(d.vrBase,0) + round(d.vrIva,0))-round(d.vrRteFuente,0) - round(d.vrRteIva,0)),0) totalfactura,\n"
                + "              round(d.vrBase,0) vrBase,\n"
                + "              round(d.vrIva,0) vrIva,\n"
                + "              round(d.vrRteFuente,0) vrRteFuente,\n"
                + "              round(d.vrRteIva,0) vrRteIva,\n"
                + "              round((round(d.vrRteFuente,0) + round(d.vrRteIva,0)),0) totalImpu,\n"
                + "              o.OBSERVACION,\n"
                + "              o.ordenCompra\n"
                + "from tblDctos d \n"
                + "inner join tblLocales l\n"
                + "on l.idLocal = d.IDLOCAL\n"
                + "and l.estado = 1\n"
                + "inner join tblLocalesCaja c\n"
                + "on c.idLocal = l.idLocal\n"
                + "and c.idCaja = 1\n"
                + "inner join ctrlUsuarios u\n"
                + "on u.idUsuario = d.IDUSUARIO\n"
                + "inner join tblDctosOrdenes o\n"
                + "on o.IDORDEN = d.IDORDEN\n"
                + "and o.IDTIPOORDEN = d.IDTIPOORDEN\n"
                + "where d.idDcto = " + dcto + "\n"
                + "and d.IDTIPOORDEN = " + tipodcto + "";

        PreparedStatement ps = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();
            // Prepara la sentencia de Busqueda
            ps = connection.prepareStatement(sql);
            // Obtiene el resultset
            ResultSet rs = ps.executeQuery();
            //
            if (rs.next()) {
                fehdto.setCompany(rs.getString("nombreLocal"));
                fehdto.setNit(rs.getString("nit"));
                fehdto.setTelefono(rs.getString("telefono"));
                fehdto.setPrefijo(rs.getString("prefijo"));
                fehdto.setResolucion(rs.getString("resolucion"));
                fehdto.setFecha(rs.getString("fecha"));
                fehdto.setIdUsuario(rs.getDouble("IDUSUARIO"));
                fehdto.setNombreUsuario(rs.getString("nombreUsuario"));
                fehdto.setDias(rs.getInt("diasPlazo"));
                fehdto.setFechaVencimiento(rs.getString("fechavencimiento"));
                fehdto.setNitCliente(rs.getString("idCliente"));
                fehdto.setNombreCliente(rs.getString("nombreTercero"));
                fehdto.setTotalfactura(rs.getFloat("totalfactura"));
                fehdto.setVrBase(rs.getFloat("vrBase"));
                fehdto.setVriva(rs.getFloat("vrIva"));
                fehdto.setVrRetefunte(rs.getFloat("vrRteFuente"));
                fehdto.setVrRteIva(rs.getFloat("vrRteIva"));
                fehdto.setTotalImpuesto(rs.getFloat("totalImpu"));
                fehdto.setObservacion(rs.getString("OBSERVACION"));
                fehdto.setOrdenCompra(rs.getString("ordenCompra"));
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
            return fehdto;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {

            // Cierra la conexion a la base de datos limpiando la statement usada y la
            jdbcAccess.cleanup(connection, ps, null);
            return fehdto;
        }
    }

    //lista url para pdf
    public FEPdfDTO ListaPdf(int orden) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        // Variable de fachada de los datos
        FEPdfDTO fehdto = new FEPdfDTO();

        Connection connection = null;

        String sql
                = "select url\n"
                + "from tblFacturaElectronica\n"
                + "where idorden = " + orden + "\n"
                + "and tipo_xml = 1";

        PreparedStatement ps = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();
            // Prepara la sentencia de Busqueda
            ps = connection.prepareStatement(sql);
            // Obtiene el resultset
            ResultSet rs = ps.executeQuery();
            //
            if (rs.next()) {
                //
                fehdto.setUrl(rs.getString("url"));
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
            return fehdto;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {

            // Cierra la conexion a la base de datos limpiando la statement usada y la
            jdbcAccess.cleanup(connection, ps, null);
            return fehdto;
        }
    }

    //Company
    public Local_DTO ListaCompany() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        // Variable de fachada de los datos
        Local_DTO fecdto = new Local_DTO();

        Connection connection = null;
        String sql
                = "SELECT l.nombreLocal,\n"
                + "        (SUBSTRING(replace(l.nit,'.',''),4,10)) nit,\n"
                + "        l.telefono,\n"
                + "        l.direccion,			\n"
                + "	    c.nombreCiudad,\n"
                + "	    c.nombreDpto,\n"
                + "	    c.idCiudad,\n"
                + "		CASE WHEN LEN(c.idCiudad) = '4' then SUBSTRING(convert(varchar,c.idCiudad), 0, 2)\n"
                + "		 ELSE SUBSTRING(convert(varchar,c.idCiudad), 1, 2) end idDpto,\n"
                + "		YEAR(GETDATE()) yearfiscal,\n"
                + "		l.email,\n"
                + "		idRes1,\n"
                + "		idRes2,\n"
                + "		idRes3,\n"
                + "		idRes4,l.\n"
                + "		idTipoOperacion,\n"
                + "		idRegimen,\n"
                + "		resolucion,\n"
                + "		resolucion2\n"
                + "FROM tblLocales l\n"
                + "INNER JOIN tblCiudades c\n"
                + "ON l.idCiudad =c.idCiudad\n"
                + "WHERE l.estado = 1;";

        PreparedStatement ps = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();
            // Prepara la sentencia de Busqueda
            ps = connection.prepareStatement(sql);
            // Obtiene el resultset
            ResultSet rs = ps.executeQuery();
            //
            if (rs.next()) {
                //
                fecdto.setRazonSocial(rs.getString("nombreLocal"));
                fecdto.setNIT(rs.getString("nit"));
                fecdto.setTelefono(rs.getString("telefono"));
                fecdto.setDireccion(rs.getString("direccion"));
                fecdto.setCiudad(rs.getString("nombreCiudad"));
                fecdto.setDepartamento(rs.getString("nombreDpto"));
                fecdto.setIdCiudad(rs.getString("idCiudad"));
                fecdto.setIdDpto(rs.getString("idDpto"));
                fecdto.setYearFiscal(rs.getString("yearfiscal"));
                fecdto.setEmail(rs.getString("email"));
                fecdto.setResFiscal1(rs.getString("idRes1"));
                fecdto.setResFiscal2(rs.getString("idRes2"));
                fecdto.setResFiscal3(rs.getString("idRes3"));
                fecdto.setResFiscal4(rs.getString("idRes4"));
                fecdto.setTipoOperacion(rs.getString("idTipoOperacion"));
                fecdto.setIdRegimen(rs.getInt("idRegimen"));
                fecdto.setResolucion(rs.getString("resolucion"));
                fecdto.setResolucion2(rs.getString("resolucion2"));
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
            return fecdto;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {

            // Cierra la conexion a la base de datos limpiando la statement usada y la
            jdbcAccess.cleanup(connection, ps, null);
            return fecdto;
        }
    }

    //UPdate respuesta PDF DIAN
    public FEUpdateDTO UrlPdfDian(int orden) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        // Variable de fachada de los datos
        FEUpdateDTO feudto = new FEUpdateDTO();

        Connection connection = null;

        String sql
                = "select top 1 (SUBSTRING(replace((select nit from tblLocales  where estado =1),'.',''),4,10)) nit,\n"
                + "        f.idOrden,\n"
                + "		idDcto,\n"
                + "		prefijoDian,\n"
                + "		idDctoDian,\n"
                + "		concat(prefijoDian,idDctoDian)dctodian\n"
                + "from tblFacturaElectronica f\n"
                + "where f.IDORDEN = " + orden + "";

        PreparedStatement ps = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();
            // Prepara la sentencia de Busqueda
            ps = connection.prepareStatement(sql);
            // Obtiene el resultset
            ResultSet rs = ps.executeQuery();
            //
            if (rs.next()) {
                //
                feudto.setNit(rs.getString("nit"));
                feudto.setDocumento(rs.getString("idDcto"));
                feudto.setDocumentoDian(rs.getString("dctodian"));
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
            return feudto;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {

            // Cierra la conexion a la base de datos limpiando la statement usada y la
            jdbcAccess.cleanup(connection, ps, null);
            return feudto;
        }
    }

    //Customer
    public FECustomerDTO ListaCustomer(String cliente) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        // Variable de fachada de los datos
        FECustomerDTO fecdto = new FECustomerDTO();

        Connection connection = null;

        String sql
                = "SELECT  t.idTercero,\n"
                + "        t.nombreTercero,\n"
                + "        t.idPersona,\n"
                + "        t.tipoIdTercero,\n"
                + "		t.direccionTercero,\n"
                + "		t.email,\n"
                + "		t.telefonoCelular,\n"
                + "		t.ciudadTercero,\n"
                + "		t.idDptoCiudad,\n"
                + "		case when LEN(t.idDptoCiudad) = '4' then SUBSTRING(convert(varchar,t.idDptoCiudad), 0, 2) else SUBSTRING(convert(varchar,t.idDptoCiudad), 1, 2) end dep,\n"
                + "		c.nombreDpto,\n"
                + "		c.nombreCiudad,\n"
                + "		(select * from respFiscal(" + cliente + ")) repFiscal,\n"
                + "		rp.idOperacionFactura,\n"
                + "		t.idRegimen,\n"
                + "		r.id				\n"
                + "from  tblTerceros t\n"
                + "inner join tblCiudades c\n"
                + "on c.idCiudad = t.idDptoCiudad\n"
                + "inner join tblFacturacionTercero rp\n"
                + "on rp.idtercero = t.idTercero\n"
                + "inner join tblRegimen r\n"
                + "on r.idRegimen = t.idRegimen\n"
                + "where t.idTercero = " + cliente + "\n"
                + "and t.idTipoTercero = 1";

        PreparedStatement ps = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();
            // Prepara la sentencia de Busqueda
            ps = connection.prepareStatement(sql);
            // Obtiene el resultset
            ResultSet rs = ps.executeQuery();
            //
            if (rs.next()) {
                //
                fecdto.setIdCustomer(rs.getDouble("idTercero"));
                fecdto.setNombreCustomer(rs.getString("nombreTercero"));
                fecdto.setDireccion(rs.getString("direccionTercero"));
                fecdto.setEmail(rs.getString("email"));
                fecdto.setTelefono(rs.getString("telefonoCelular"));
                fecdto.setNombreCiudad(rs.getString("nombreCiudad"));
                fecdto.setCodigoCiudad(rs.getString("idDptoCiudad"));
                fecdto.setCodigoDto(rs.getString("dep"));
                fecdto.setNombreDto(rs.getString("nombreDpto"));
                fecdto.setRespoFiscal(rs.getString("repFiscal"));
                fecdto.setTipoOperacion(rs.getString("idOperacionFactura"));
                fecdto.setRegimen(rs.getString("id"));
                fecdto.setCompanyType(rs.getString("idPersona").equals("0") ? "2" : "1");
                fecdto.setIdentificationType(rs.getString("tipoIdTercero").equals("A") ? "31" : "13");

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
            return fecdto;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {

            // Cierra la conexion a la base de datos limpiando la statement usada y la
            jdbcAccess.cleanup(connection, ps, null);
            return fecdto;
        }
    }

    //Nota
    public FENota ListaNota(int orden) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        // Variable de fachada de los datos
        FENota fen = new FENota();

        Connection connection = null;

        String sql
                = "select	d.idDcto,\n"
                + "		d.idCausa,\n"
                + "		d.idDctoCruce,\n"
                + "		f.idDcto,\n"
                + "		f.idDctoDian,\n"
                + "		concat(f.prefijoDian,f.idDctoDian) dctoDian,\n"
                + "		n.nombreCausa,\n"
                + "             FORMAT((SELECT fechaDcto FROM dbo.tblDctos WHERE idDcto = d.idDctoCruce AND IDTIPOORDEN=9), 'MM-dd-yyyy') AS fechaCruce\n" 
                + "from tblDctos d\n"
                + "left join tblFacturaElectronica f\n"
                + "on f.idDcto = d.idDctoCruce\n"
                + "and f.idOrden = d.idOrdenCruce\n"
                + "and f.idTipoOrden = d.idTipoOrdenCruce\n"
                + "and f.tipo_xml = 1\n"
                + "inner join tblTipoCausaNota n\n"
                + "on n.idCausa = d.idCausa\n"
                + "where d.idorden = " + orden + "";

        PreparedStatement ps = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();
            // Prepara la sentencia de Busqueda
            ps = connection.prepareStatement(sql);
            // Obtiene el resultset
            ResultSet rs = ps.executeQuery();
            //
            if (rs.next()) {
                //
                fen.setCausal(rs.getString("idCausa"));
                fen.setNombreCausal(rs.getString("nombreCausa"));
                fen.setFacturaDian(rs.getString("dctoDian"));
                fen.setFacturaInterna(rs.getString("idDctoCruce"));
                fen.setFechaCruce(rs.getString("fechaCruce"));
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
            return fen;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {

            // Cierra la conexion a la base de datos limpiando la statement usada y la
            jdbcAccess.cleanup(connection, ps, null);
            return fen;
        }
    }

    public List<FEDtlDTO> ListaDtl(int orden) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        // Variable de fachada de los datos
        List<FEDtlDTO> Lista = new ArrayList<FEDtlDTO>();
        FEDtlDTO feddto;

        Connection connection = null;

        String sql
                = "SELECT  d.idplu,\n"
                + "        d.NOMBREPLU,\n"
                + "        CONVERT(decimal(10,2), ROUND((d.VRVENTAUNITARIO/(1+(d.PORCENTAJEIVA/100))),2,0)) VRVENTAUNITARIO,\n"
                + "        d.CANTIDAD,\n"
                + "        ROUND((d.CANTIDAD * (d.VRVENTAUNITARIO/(1+(d.PORCENTAJEIVA/100)))),0) subtotal,\n"
                + "        d.referenciaCliente,\n"
                + "        d.comentario,\n"
                + "        d.PORCENTAJEIVA,\n"
                + "        ROUND(((d.CANTIDAD * (d.VRVENTAUNITARIO/(1+(d.PORCENTAJEIVA/100)))) * ((d.PORCENTAJEIVA/100))),0) iva,\n"
                + "        (SELECT ordenCompra FROM tblDctosOrdenes WHERE idOrden = d.idOrdenOrigen AND IDTIPOORDEN = 59) ordenCompra,\n"
                + "        (SELECT numeroOrden FROM tblDctosOrdenes WHERE idOrden = d.idOrdenOrigen AND IDTIPOORDEN = 59) ot,\n"
                + "        (SELECT codigoDian FROM tblUnidadesEmbalaje WHERE id =(SELECT TOP 1 idTipoEmbalaje FROM tblDctosOrdenesDetalle WHERE idOrden = d.idOrdenOrigen AND IDTIPOORDEN = 59)) unidadEmbalaje, \n "
                + "        (SELECT  idFicha    FROM tblDctosOrdenes where numeroOrden=(SELECT numeroOrden FROM tblDctosOrdenes WHERE idOrden = d.idOrdenOrigen AND IDTIPOORDEN = 59) and IDTIPOORDEN=59) idFicha \n "
                + "FROM tblDctosOrdenesDetalle d\n"
                + "INNER JOIN tblDctosOrdenes o\n"
                + "ON o.IDORDEN = d.IDORDEN\n"
                + "AND o.IDTIPOORDEN = d.IDTIPOORDEN \n"
                + "WHERE d.idorden = " + orden + "  AND  d.IDTIPOORDEN = 9";

        PreparedStatement ps = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();
            // Prepara la sentencia de Busqueda
            ps = connection.prepareStatement(sql);
            // Obtiene el resultset
            ResultSet rs = ps.executeQuery();
            //
            while (rs.next()) {
                feddto = new FEDtlDTO();
                feddto.setIdplu(rs.getInt("idplu"));
                feddto.setNombrePlu(rs.getString("NOMBREPLU"));
                feddto.setVrVenta(rs.getDouble("VRVENTAUNITARIO"));
                feddto.setCantidad(rs.getDouble("CANTIDAD"));
                feddto.setSubtotal(rs.getDouble("subtotal"));
                feddto.setNombreRefCliente(rs.getString("referenciaCliente"));
                feddto.setComentario(rs.getString("comentario"));
                feddto.setPorcentajeIva(rs.getDouble("PORCENTAJEIVA"));
                feddto.setIva(rs.getDouble("iva"));
                feddto.setOrcenCompra(rs.getString("ordenCompra"));
                feddto.setOt(rs.getString("ot"));
                feddto.setUnidadMedida(rs.getString("unidadEmbalaje"));
                feddto.setIdFicha(rs.getInt("idFicha"));
                Lista.add(feddto);
            }

            // Cierra el Resultset
            rs.close();

        } catch (NamingException ne) {
            System.out.println("NamingException in " + nombreClase + " " + ne);
        } catch (SQLException sqle) {
            System.out.println("SQL: " + sql);
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
            jdbcAccess.cleanup(connection, ps, null);
            return Lista;
        }
    }

    public List<FEDtlDTO> ListaDtlNota(int orden) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        // Variable de fachada de los datos
        List<FEDtlDTO> Lista = new ArrayList<FEDtlDTO>();
        FEDtlDTO feddto;

        Connection connection = null;

        String sql
                = "SELECT  d.idplu,\n"
                + "        d.NOMBREPLU,\n"
                + "        CONVERT(decimal(10,2), round((d.VRVENTAUNITARIO/(1+(d.PORCENTAJEIVA/100))),2,0)) VRVENTAUNITARIO,\n"
                + "        d.CANTIDAD,\n"
                + "        ROUND((d.CANTIDAD * (d.VRVENTAUNITARIO/(1+(d.PORCENTAJEIVA/100)))),0) subtotal,\n"
                + "        d.referenciaCliente,\n"
                + "        d.comentario,\n"
                + "        d.PORCENTAJEIVA,\n"
                + "        ROUND(((d.CANTIDAD * (d.VRVENTAUNITARIO/(1+(d.PORCENTAJEIVA/100)))) * ((d.PORCENTAJEIVA/100))),0) iva,\n"
                + "        (SELECT ordenCompra FROM tblDctosOrdenes WHERE idOrden = d.idOrdenOrigen  AND IDTIPOORDEN = 9) ordenCompra,\n"
                + "(SELECT idOrdenOrigen FROM tblDctos WHERE tblDctos.IDORDEN = " + orden + " AND IDTIPOORDEN = 29) ot,\n"
                + "(SELECT codigoDian FROM tblUnidadesEmbalaje WHERE id =(SELECT TOP 1 idTipoEmbalaje FROM tblDctosOrdenesDetalle WHERE idOrden = d.idOrdenOrigen AND IDTIPOORDEN = 9)) unidadEmbalaje, \n "
                + " d.itemPadre\n"
                + "from tblDctosOrdenesDetalle d\n"
                + "inner join tblDctosOrdenes o\n"
                + "on o.IDORDEN = d.IDORDEN\n"
                + "and o.IDTIPOORDEN = d.IDTIPOORDEN \n"
                + "where d.idorden =" + orden + "  AND  d.IDTIPOORDEN = 29 ORDER BY d.item";

        PreparedStatement ps = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();
            // Prepara la sentencia de Busqueda
            ps = connection.prepareStatement(sql);
            // Obtiene el resultset
            ResultSet rs = ps.executeQuery();
            //
            while (rs.next()) {
                feddto = new FEDtlDTO();
                //
                feddto.setIdplu(rs.getInt("idplu"));
                feddto.setNombrePlu(rs.getString("NOMBREPLU"));
                feddto.setVrVenta(rs.getDouble("VRVENTAUNITARIO"));
                feddto.setCantidad(rs.getDouble("CANTIDAD"));
                feddto.setSubtotal(rs.getDouble("subtotal"));
                feddto.setNombreRefCliente(rs.getString("referenciaCliente"));
                feddto.setComentario(rs.getString("comentario"));
                feddto.setPorcentajeIva(rs.getDouble("PORCENTAJEIVA"));
                feddto.setIva(rs.getDouble("iva"));
                feddto.setOrcenCompra(rs.getString("ordenCompra"));
                feddto.setOt(rs.getString("ot"));
                feddto.setItem(rs.getInt("itemPadre"));
                feddto.setUnidadMedida(rs.getString("unidadEmbalaje"));
                feddto.setComentario("");
                Lista.add(feddto);
            }

            // Cierra el Resultset
            rs.close();

        } catch (NamingException ne) {
            System.out.println("NamingException in " + nombreClase + " " + ne);
        } catch (SQLException sqle) {
            System.out.println("SQL: " + sql);
            System.out.println("SQLException in" + nombreClase);
            System.out.println(sql);
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
            jdbcAccess.cleanup(connection, ps, null);
            return Lista;
        }
    }

    public String DctoDianTransmision(int orden, int tipoorden) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        String maximaIdDcto = "0";
        Connection c = null;

        String sql
                = "SELECT concat(prefijoDian,idDctoDian)  dctoDian\n"
                + "FROM tblFacturaElectronica                      \n"
                + "where  idOrden = " + orden + "  \n"
                + "and idTipoOrden = " + tipoorden + " \n"
                + "and tipo_xml = 1                     ";

        PreparedStatement ps = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            c = jdbcAccess.getConnection();
            // Prepara la sentencia de Busqueda
            ps = c.prepareStatement(sql);
            // Obtiene el resultset
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                maximaIdDcto = rs.getString("dctoDian");
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
            return maximaIdDcto;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {

            // Cierra la conexion a la base de datos limpiando la statement usada y la
            jdbcAccess.cleanup(c, ps, null);
            return maximaIdDcto;
        }
    }

    public String dameNombreTercero(String idplu, String orden) {
        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();
        String nombreTercero = "";
        Connection c = null;
        String sql = "SELECT referenciaCliente  FROM tblDctosOrdenesDetalle WHERE idOrden = " + orden + " AND IDPLU =" + idplu + " ;";;

        PreparedStatement ps = null;
        try {
            c = jdbcAccess.getConnection();
            ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                nombreTercero = (rs.getString("referenciaCliente"));
            }
            return nombreTercero;
        } catch (NamingException ne) {
            System.out.println("NamingException in " + nombreClase + " " + ne);
            return nombreTercero;
        } catch (SQLException sqle) {
            System.out.println("SQLException in" + nombreClase);
            System.out.println("Ocurrio una  excepcion");
            System.out.println("Mensaje " + sqle.getMessage());
            System.out.println("SQL State " + sqle.getSQLState());
            System.out.println("Vendor Code " + sqle.getErrorCode());
            return nombreTercero;
        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
            return nombreTercero;
        } finally {
            jdbcAccess.cleanup(c, ps, null);
            return nombreTercero;
        }

    }

    public String getPedido(String orden, int item) {
        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();
        String numeroPedido = "";
        Connection c = null;
        String sql = "SELECT idOrdenOrigen  FROM tblDctosOrdenesDetalle WHERE idOrden = " + orden + " AND item = " + item + ";";

        PreparedStatement ps = null;
        try {
            c = jdbcAccess.getConnection();
            ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                numeroPedido = (rs.getString("idOrdenOrigen"));
            }
            return numeroPedido;
        } catch (NamingException ne) {
            System.out.println("NamingException in " + nombreClase + " " + ne);
            return numeroPedido;
        } catch (SQLException sqle) {
            System.out.println("SQLException in" + nombreClase);
            System.out.println("Ocurrio una  excepcion");
            System.out.println("Mensaje " + sqle.getMessage());
            System.out.println("SQL State " + sqle.getSQLState());
            System.out.println("Vendor Code " + sqle.getErrorCode());
            return numeroPedido;
        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
            return numeroPedido;
        } finally {
            jdbcAccess.cleanup(c, ps, null);
            return numeroPedido;
        }
    }

    public FEDtlDTO dameDtllNota(String numeroPedido) {
        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();

        Connection c = null;
        String sql = "SELECT numeroOrden,ordenCompra,idFicha FROM tblDctosOrdenes WHERE idOrden = " + numeroPedido + ";";
        FEDtlDTO feDtlDto = new FEDtlDTO();
        PreparedStatement ps = null;
        try {
            c = jdbcAccess.getConnection();
            ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                feDtlDto.setOt(rs.getString("numeroOrden"));
                feDtlDto.setOrcenCompra(rs.getString("ordenCompra"));
                feDtlDto.setIdFicha(rs.getInt("idFicha"));

            }
            return feDtlDto;
        } catch (NamingException ne) {
            System.out.println("NamingException in " + nombreClase + " " + ne);
            return feDtlDto;
        } catch (SQLException sqle) {
            System.out.println("SQLException in" + nombreClase);
            System.out.println("Ocurrio una  excepcion");
            System.out.println("Mensaje " + sqle.getMessage());
            System.out.println("SQL State " + sqle.getSQLState());
            System.out.println("Vendor Code " + sqle.getErrorCode());
            return feDtlDto;
        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
            return feDtlDto;
        } finally {
            jdbcAccess.cleanup(c, ps, null);
            return feDtlDto;
        }
    }

    public boolean existeDctoFacturaElectronica(int idOrden) {
        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        boolean existeDcto = false;
        Connection c = null;

        String sql = "SELECT * FROM tblFacturaElectronica  WHERE idorden =  " + idOrden + " AND tipo_xml = 1";

        PreparedStatement ps = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            c = jdbcAccess.getConnection();
//            c = this.conect;
            // Prepara la sentencia de Busqueda
            ps = c.prepareStatement(sql);

            // Obtiene el resultset
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                existeDcto = true;
            }

            // Cierra el Resultset
            rs.close();

        } catch (SQLException sqle) {

            System.out.println("SQLException in" + nombreClase);

            String sqlMessage = sqle.getMessage();
            String sqlState = sqle.getSQLState();
            int vendorCode = sqle.getErrorCode();
            System.out.println("Ocurrio una  excepcion");
            System.out.println("Mensaje " + sqlMessage);
            System.out.println("SQL State " + sqlState);
            System.out.println("Vendor Code " + vendorCode);
            return false;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
        } finally {

//             Cierra la conexion a la base de datos limpiando la statement usada y la
            jdbcAccess.cleanup(c, ps, null);
        }
        return existeDcto;
    }

}
