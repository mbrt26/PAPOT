package com.solucionesweb.losbeans.negocio;

// Importa la clase helper JDBCAccess que contiene los accesos a la base de datos
import com.solucionesweb.lasayudas.JDBCAccess;

// Importa el bean de fachada
import com.solucionesweb.losbeans.colaboraciones.ColaboraPlu;
import com.solucionesweb.losbeans.fachada.FachadaDctoOrdenDetalleBean;

// Importa los paquetes de java
import com.solucionesweb.losbeans.fachada.FachadaPluBean;
import java.sql.*;
import java.io.*;
import java.io.IOException.*;

//
import org.kxml.*;
import org.kxml.parser.*;

// Importa los paquetes de javax
import javax.naming.*;

//
import com.solucionesweb.losbeans.utilidades.Day;

public class DctoOrdenDetalleDespachoTx extends FachadaDctoOrdenDetalleBean
        implements Serializable {

    // Nombre JNDI del Data Source que esta clase requiere
    private static final String DATA_SOURCE_NAME = "CommerceAccess";
    // Clase helper usada para acceder a la base de datos
    private JDBCAccess jdbcAccess;
    //
    FachadaDctoOrdenDetalleBean fachadaDetalle;
    //
    ColaboraPlu colaboraPlu = new ColaboraPlu();
    //
    FachadaPluBean fachadaPluBean = new FachadaPluBean();
    //
    AgendaLogVisitaBean agendaLogVisitaBean = new AgendaLogVisitaBean();
    //
    DctoOrdenBean dctoOrdenBean = new DctoOrdenBean();
    //
    Day day = new Day();
    //
    String strFechaVisita = day.getFechaFormateada();
    //
    int idPeriodo = 200611;
    int estadoAtendido = 1; // visitaActiva
    int estadoProgramada = 9; // visitaProgramada
    int idEstadoVisita = 1; // Programada
    int xEstadoDctoOrden = 1;
    int xEstadoSuspendido = 8;
    int xIdOrigenWeb = 1;

    // Metodo constructor por defecto sin parametros
    public DctoOrdenDetalleDespachoTx() {

        // Al instanciar el Bean establece la conexion a la base de datos
        jdbcAccess = new JDBCAccess(DATA_SOURCE_NAME);

    }

    // ingresa
    public boolean ingresa() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        //
        String selectStr =
                " INSERT INTO tblDctosOrdenesDetalle  "
                + "       ([IDLOCAL]                    "
                + "       ,[IDTIPOORDEN]                "
                + "       ,[IDORDEN]                    "
                + "       ,[IDPLU]                      "
                + "       ,[CANTIDAD]                   "
                + "       ,[IDTIPO]                     "
                + "       ,[PORCENTAJEIVA]              "
                + "       ,[VRVENTAUNITARIO]            "
                + "       ,[ESTADO]                     "
                + "       ,[NOMBREPLU]                  "
                + "       ,[EAN]                        "
                + "       ,[VRVENTAORIGINAL]            "
                + "       ,[VRCOSTO]                    "
                + "       ,[VRDSCTOPIE]                 "
                + "       ,[PORCENTAJEDSCTO]            "
                + "       ,[CANTIDADPEDIDA]             "
                + "       ,[vrCostoNegociado]           "
                + "       ,[strIdBodega]                "
                + "       ,[vrCostoResurtido]           "
                + "       ,[STRIDLISTA]                 "
                + "       ,[STRIDREFERENCIA]            "
                + "       ,[PESOTEORICO]                "
                + "       ,[NOMBREUNIDADMEDIDA]         "
                + "       ,[IDLOCALSUGERIDO]            "
                + "       ,[IDBODEGASUGERIDO]           "
                + "       ,[marcaArteCliente]           "
                + "       ,[referenciaCliente]          "
                + "       ,[comentario]                 "
                + "       ,[item]                       "
                + "       ,[itemPadre]                  "
                + "       ,[idEstadoTx]                 "
                + "       ,[idTipoTx]                   "
                + "       ,[idReferenciaOriginal]       "
                + "       ,[idEstadoRefOriginal]        "
                + "       ,[idClasificacion]            "
                + "       ,[idResponsable]              "
                + "       ,[idBodega]                   "
                + "       ,[vrImpoconsumo]              "
                + "       ,[vrCostoIND]                 "
                + "       ,[vrIvaResurtido]             "
                + "       ,[idSubcuenta]                "
                + "       ,[cantidadBonificada]         "
                + "       ,[idOrdenOrigen]              "
                + "       ,[idLocalOrigen]              "
                + "       ,[idTipoOrdenOrigen])         "
                + " VALUES (" + getIdLocal() + ","
                + getIdTipoOrden() + ","
                + getIdOrden() + ","
                + getIdPlu() + ","
                + getCantidad() + ","
                + getIdTipo() + ","
                + getPorcentajeIva() + ","
                + getVrVentaUnitario() + ","
                + getEstado() + ",'"
                + getNombrePlu() + "',"
                + getEan() + ","
                + getVrVentaOriginal() + ","
                + getVrCosto() + ","
                + getVrDsctoPie() + ","
                + getPorcentajeDscto() + ","
                + getCantidadPedida() + ","
                + getVrCostoNegociado() + ",'"
                + getStrIdBodega() + "',"
                + getVrCostoResurtido() + ",'"
                + getStrIdLista() + "','"
                + getStrIdReferencia() + "',"
                + getPesoTeorico() + ",'"
                + getNombreUnidadMedida() + "',"
                + getIdLocalSugerido() + ",'"
                + getIdBodegaSugerido() + "','"
                + getMarcaArteCliente() + "','"
                + getReferenciaCliente() + "','"
                + getComentario() + "',"
                + getItem() + ","
                + getItemPadre() + ","
                + getIdEstadoTx() + ","
                + getIdTipoTx() + ",'"
                + getIdReferenciaOriginal() + "',"
                + getIdEstadoRefOriginal() + ","
                + getIdClasificacion() + ","
                + getIdResponsable() + ","
                + getIdBodega() + ","
                + getVrImpoconsumo() + ","
                + getVrCostoIND() + ","
                + getVrIvaResurtido() + ",'"
                + getIdSubcuenta() + "',"
                + getCantidadBonificada() + ","
                + getIdOrdenOrigen() + ","
                + getIdLocalOrigen() + ","
                + getIdTipoOrdenOrigen() + ")";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);


            // Obtiene el resultset
            selectStatement.execute();
            okIngresar = true;

            // Cierra el Resultset

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
            jdbcAccess.cleanup(connection, selectStatement, null);
            return okIngresar;
        }
    }

    // existeOrdenPluOrigen
    public boolean existeOrdenPluOrigen(int xIdLocalOrigen,
            int xIdTipoOrdenOrigen,
            int xIdOrdenOrigen,
            int xEstadoOrigen) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        boolean xOkExiste = false;

        //
        Connection connection = null;

        String selectStr =
                "SELECT tblDctosOrdenesDetalle.*                "
                + "FROM  tblDctosOrdenes                          "
                + "INNER JOIN tblDctosOrdenesDetalle              "
                + "ON tblDctosOrdenes.IDLOCAL      =              "
                + "    tblDctosOrdenesDetalle.IDLOCAL             "
                + "AND tblDctosOrdenes.IDTIPOORDEN =              "
                + "  tblDctosOrdenesDetalle.IDTIPOORDEN           "
                + "AND tblDctosOrdenes.IDORDEN     =              "
                + "      tblDctosOrdenesDetalle.IDORDEN           "
                + "INNER JOIN tblAgendaLogVisitas                 "
                + "ON tblDctosOrdenes.idLog        =              "
                + "             tblAgendaLogVisitas.idLog         "
                + "WHERE tblDctosOrdenesDetalle.idOrdenOrigen   = "
                + xIdOrdenOrigen + "                              "
                + "AND tblDctosOrdenesDetalle.idLocalOrigen     = "
                + xIdLocalOrigen + "                              "
                + "AND tblDctosOrdenesDetalle.idTipoOrdenOrigen = "
                + xIdTipoOrdenOrigen + "                          "
                + "AND tblDctosOrdenesDetalle.idPlu             = "
                + getIdPlu() + "                                  "
                + " AND tblAgendaLogVisitas.estado              = "
                + xEstadoOrigen;


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
                xOkExiste = true;


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
            return xOkExiste;

        }
    }

    // ordenOrigen
    public int ordenOrigen(int xIdLocalOrigen,
            int xIdTipoOrdenOrigen,
            int xIdOrdenOrigen,
            int xEstadoOrigen) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();

        //
        int xIdOrdenOrigenMax = 0;

        //
        Connection connection = null;

        String selectStr =
                "SELECT tblDctosOrdenesDetalle.idOrden          "
                + "FROM  tblDctosOrdenes                          "
                + "INNER JOIN tblDctosOrdenesDetalle              "
                + "ON tblDctosOrdenes.IDLOCAL      =              "
                + "    tblDctosOrdenesDetalle.IDLOCAL             "
                + "AND tblDctosOrdenes.IDTIPOORDEN =              "
                + "  tblDctosOrdenesDetalle.IDTIPOORDEN           "
                + "AND tblDctosOrdenes.IDORDEN     =              "
                + "      tblDctosOrdenesDetalle.IDORDEN           "
                + "INNER JOIN tblAgendaLogVisitas                 "
                + "ON tblDctosOrdenes.idLog        =              "
                + "             tblAgendaLogVisitas.idLog         "
                + "WHERE tblDctosOrdenesDetalle.idOrdenOrigen   = "
                + xIdOrdenOrigen + "                              "
                + "AND tblDctosOrdenesDetalle.idLocalOrigen     = "
                + xIdLocalOrigen + "                              "
                + "AND tblDctosOrdenesDetalle.idTipoOrdenOrigen = "
                + xIdTipoOrdenOrigen + "                          "
                + " AND tblAgendaLogVisitas.estado              = "
                + xEstadoOrigen;

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
                xIdOrdenOrigenMax = rs.getInt("idOrden");

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
            return xIdOrdenOrigenMax;

        }
    }

    // retiraLog
    public boolean retira() {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;

        Connection connection = null;

        //
        String selectStr =
                " DELETE FROM tmpResurtido ";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);


            // Obtiene el resultset
            selectStatement.execute();
            okIngresar = true;

            // Cierra el Resultset

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
            jdbcAccess.cleanup(connection, selectStatement, null);
            return okIngresar;
        }
    }

// traverse_DctoOrdenDetalleDespachoTx
    public void traverse_DctoOrdenDetalleDespachoTx(XmlParser parser,
            String indent,
            double xIdUsuario,
            int xIdLocalOrigen) throws Exception {

        //
        int xEstadoPendiente = 8;
        int xIdTipoOrdenDespacho = 5;
        int xIdTipoOrdenDespachoTemporal = xIdTipoOrdenDespacho + 50;

        //
        boolean leave = false;

        //
        String xIdLocal = new String();
        String xIdTipoOrden = new String();
        String xIdOrden = new String();
        String xIdPlu = new String();
        String xCantidadPedida = new String();

        do {
            ParseEvent event = parser.read();
            ParseEvent pe;

            switch (event.getType()) {

                // For example, <CxC>
                case Xml.START_TAG:

                    // see API doc of StartTag for more access methods
                    // Pick up idagenda for display
                    if ("idLocal".equals(event.getName())) {
                        pe = parser.read();
                        xIdLocal = pe.getText();

                    }

                    // Pick up clave for display
                    if ("idTipoOrden".equals(event.getName())) {
                        pe = parser.read();
                        xIdTipoOrden = pe.getText();

                    }

                    // Pick up clave for display
                    if ("idOrden".equals(event.getName())) {
                        pe = parser.read();
                        xIdOrden = pe.getText();

                    }

                    // Pick up clave for display
                    if ("idPlu".equals(event.getName())) {
                        pe = parser.read();
                        xIdPlu = pe.getText();

                    }

                    // Pick up clave for display
                    if ("cantidadPedida".equals(event.getName())) {
                        pe = parser.read();
                        xCantidadPedida = pe.getText();

                        //
                        this.setIdPlu(xIdPlu);

                        //
                        boolean xExiste =
                                this.existeOrdenPluOrigen(
                                new Integer(xIdLocal).intValue(),
                                new Integer(xIdTipoOrden).intValue(),
                                new Integer(xIdOrden).intValue(),
                                xEstadoPendiente);
                        //
                        if (!xExiste) {

                            //
                            int xIdOrdenMax =
                                    this.ordenOrigen(
                                    new Integer(xIdLocal).intValue(),
                                    new Integer(xIdTipoOrden).intValue(),
                                    new Integer(xIdOrden).intValue(),
                                    xEstadoPendiente);

                            //
                            if (xIdOrdenMax == 0) {

                                //
                                int xIdLogMax =
                                        agendaLogVisitaBean.maximoIdLog() + 1;

                                //
                                agendaLogVisitaBean.setIdCliente(xIdLocal.trim());
                                agendaLogVisitaBean.setIdUsuario(xIdUsuario);
                                agendaLogVisitaBean.setIdPeriodo(idPeriodo);
                                agendaLogVisitaBean.setFechaVisita(
                                        strFechaVisita);
                                agendaLogVisitaBean.setIdLog(xIdLogMax);
                                agendaLogVisitaBean.setIdEstadoVisita(
                                        idEstadoVisita);
                                agendaLogVisitaBean.setEstado(xEstadoSuspendido);
                                agendaLogVisitaBean.setIdLocalTercero(
                                        xIdLocalOrigen);

                                //
                                boolean okIngreso =
                                        agendaLogVisitaBean.ingresaLogVisita();

                                //
                                String xEmail = "";
                                String xIdFormaPago = "";

                                // NO existeOrden y se igual idLocal = idLocalInicial
                                dctoOrdenBean.setIdLocal(xIdLocalOrigen);

                                //
                                xIdOrdenMax =
                                        dctoOrdenBean.maximaIdOrdenIdLocal() + 1;

                                //
                                dctoOrdenBean.setIdLocal(xIdLocalOrigen);
                                dctoOrdenBean.setIdTipoOrden(
                                        xIdTipoOrdenDespachoTemporal);
                                dctoOrdenBean.setIdOrden(xIdOrdenMax);
                                dctoOrdenBean.setFechaOrden(strFechaVisita);
                                dctoOrdenBean.setEstado(xEstadoDctoOrden);
                                dctoOrdenBean.setIdCliente(xIdLocal);
                                dctoOrdenBean.setIdUsuario(xIdUsuario);
                                dctoOrdenBean.setIdOrigen(xIdOrigenWeb);
                                dctoOrdenBean.setIdLog(xIdLogMax);
                                dctoOrdenBean.setFechaEntrega(strFechaVisita);
                                dctoOrdenBean.setTipoDcto(
                                        new Integer(xIdTipoOrden).toString());
                                dctoOrdenBean.setEmail(xEmail);
                                dctoOrdenBean.setFormaPago(xIdFormaPago);

                                //
                                boolean okIngresoOrden =
                                        dctoOrdenBean.ingresaDctosOrden();

                            }

                            //
                            colaboraPlu.setIdPlu(xIdPlu);

                            //
                            fachadaPluBean = colaboraPlu.listaUnPluFCH();

                            //
                            setIdLocal(xIdLocalOrigen);
                            setIdTipoOrden(xIdTipoOrdenDespachoTemporal);
                            setIdOrden(xIdOrdenMax);
                            setIdPlu(xIdPlu);
                            setIdTipo(fachadaPluBean.getIdTipo());
                            setPorcentajeIva(fachadaPluBean.getPorcentajeIva());
                            setVrVentaUnitario(fachadaPluBean.getVrGeneral());
                            setEstado(fachadaPluBean.getEstado());
                            setNombrePlu(fachadaPluBean.getNombreCategoria()
                                    + " "
                                    + fachadaPluBean.getNombrePlu());
                            setEan(xIdPlu);
                            setVrVentaOriginal(fachadaPluBean.getVrGeneral());
                            setVrCosto(fachadaPluBean.getVrCosto());
                            setCantidadPedida(xCantidadPedida);
                            setStrIdBodega("");
                            setStrIdLista("");
                            setStrIdReferencia(xIdPlu);
                            setNombreUnidadMedida("");
                            setIdBodegaSugerido("");
                            setMarcaArteCliente("");
                            setReferenciaCliente("");
                            setComentario("");
                            setIdReferenciaOriginal("");
                            setIdSubcuenta("");
                            setItem(xIdPlu);
                            setItemPadre(xIdPlu);
                            setIdBodega(1);
                            setVrImpoconsumo(fachadaPluBean.getVrImpoconsumo());
                            setVrCostoIND(fachadaPluBean.getVrCostoIND());
                            setIdOrdenOrigen(xIdOrden);
                            setIdLocalOrigen(xIdLocal);
                            setIdTipoOrdenOrigen(xIdTipoOrden);

                            //
                            this.ingresa();

                        }
                    }

                    // recursion call for each <tag></tag>
                    traverse_DctoOrdenDetalleDespachoTx(parser,
                            "",
                            xIdUsuario,
                            xIdLocalOrigen);
                    break;

                // For example </title?
                case Xml.END_TAG:
                    leave = true;
                    break;

                // For example </rss>
                case Xml.END_DOCUMENT:

                    leave = true;
                    break;

                // For example, the text between tags
                case Xml.TEXT:
                    break;
                case Xml.WHITESPACE:
                    break;
                default:
            }
        } while (!leave);
    }
}
