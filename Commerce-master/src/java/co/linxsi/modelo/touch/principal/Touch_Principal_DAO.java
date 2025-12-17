/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.linxsi.modelo.touch.principal;

import com.solucionesweb.lasayudas.JDBCAccess;
import com.solucionesweb.losbeans.fachada.FachadaPluFicha;
import com.solucionesweb.losbeans.utilidades.IConstantes;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;

/*   Document   :TouchRetalServlet
    Created on : 15-jul-2019, 10:18:03
    Author     : Edgar J. García L.*/
public class Touch_Principal_DAO extends FachadaPluFicha
        implements Serializable, IConstantes {

    protected final SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyy HH:mm");

    String consultaFecha = " ";
    String consultaConsultaMaquina = " ";
    String consultaOrden = " ";
    String consultaOperador = " ";
    String consultaProceso = " ";
    private static final String DATA_SOURCE_NAME = "CommerceAccess";
    private JDBCAccess jdbcAccess = new JDBCAccess(DATA_SOURCE_NAME);

    private final double xPorcentajeAdicion = 1.05;

    public List<FachadaPluFicha> listaOTProgramaTouch_Operacion_Pedido(String operacion, String numeroOrden) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        List<FachadaPluFicha> contenedor = new ArrayList<FachadaPluFicha>();

        Connection connection = null;

        //
        String selectStr
                = " SELECT DISTINCT  *                                      "
                + " FROM (                                                "
                + "   SELECT tmpPEN.idLocal,                              "
                + "        tmpPEN.idTipoOrden,                            "
                + "        tmpPEN.idOrden,                                "
                + "        tmpPEN.itemPadre,                              "
                + "        tmpPEN.idCliente,                              "
                + "        tmpPEN.numeroOrden,                            "
                + "        tmpPEN.idFicha,                                "
                + "        tmpPEN.idLog,                                  "
                + "        tmpPEN.fechaEntrega,                           "
                + "        tmpPEN.referencia,                             "
                + "        tmpPEN.nombreOperacion,                        "
                + "        tmpPEN.idColor,                                "
                + "        tmpPEN.referenciaCliente,                      "
                + "        tmpPEN.nombreReferencia,                       "
                + "        tmpPEN.nombreItem,                             "
                + "        tmpPEN.nombreTercero,                          "
                + "        tmpPEN.idOperacionAnterior,                    "
                + "        tmpPEN.idOperacion,                            "
                + "                        tmpPEN.pesoPedido          "
                + "         AS pesoTerminadoAnterior,                  "
                + "                   tmpPEN.cantidadPedida          "
                + "        AS cantidadTerminadaAnterior,              "
                + "        ISNULL(tmpPEN.pesoTerminadoActual,0)           "
                + "                         AS pesoTerminadoActual,       "
                + "        ISNULL(tmpPEN.cantidadTerminadaActual,0)       "
                + "                     AS cantidadTerminadaActual,       "
                + "        tmpPEN.idOrdenPrograma                         "
                + " FROM (                                                "
                + " SELECT tmpPED.*,                                      "
                + " ( SELECT SUM(tblDctosOrdenesDetalle.pesoTerminado)    "
                + " FROM tblDctosOrdenesDetalle                           "
                + " WHERE tblDctosOrdenesDetalle.idLocalOrigen     =      "
                + "                                    tmpPED.idLocal     "
                + " AND   tblDctosOrdenesDetalle.idTipoOrdenOrigen =      "
                + "                                tmpPED.idTipoOrden     "
                + " AND   tblDctosOrdenesDetalle.idOrdenOrigen     =      "
                + "                                    tmpPED.idOrden     "
                + " AND   tblDctosOrdenesDetalle.itemPadre         =      "
                + "                                  tmpPED.itemPadre     "
                + " AND   tblDctosOrdenesDetalle.idOperacion       =      "
                + "                      tmpPED.idOperacionAnterior )     "
                + "                        AS pesoTerminadoAnterior,      "
                + " ( SELECT                                              "
                + "     SUM(tblDctosOrdenesDetalle.cantidadTerminada)     "
                + " FROM tblDctosOrdenesDetalle                           "
                + " WHERE tblDctosOrdenesDetalle.idLocalOrigen     =      "
                + "                                    tmpPED.idLocal     "
                + " AND   tblDctosOrdenesDetalle.idTipoOrdenOrigen =      "
                + "                                tmpPED.idTipoOrden     "
                + " AND   tblDctosOrdenesDetalle.idOrdenOrigen     =      "
                + "                                    tmpPED.idOrden     "
                + " AND   tblDctosOrdenesDetalle.itemPadre         =      "
                + "                                  tmpPED.itemPadre     "
                + " AND   tblDctosOrdenesDetalle.idOperacion       =      "
                + "                    tmpPED.idOperacionAnterior   )     "
                + "                     AS cantidadTerminadaAnterior,     "
                + " ( SELECT SUM(tblDctosOrdenesProgreso.pesoTerminado)   "
                + " FROM tblDctosOrdenesProgreso                          "
                + " WHERE tblDctosOrdenesProgreso.idLocal           =     "
                + "                                    tmpPED.idLocal     "
                + " AND  tblDctosOrdenesProgreso.idControlTipo     != 2   "
                + " AND   tblDctosOrdenesProgreso.idTipoOrden       =     "
                + "                                tmpPED.idTipoOrden     "
                + " AND   tblDctosOrdenesProgreso.idOrden           =     "
                + "                                    tmpPED.idOrden     "
                + " AND   tblDctosOrdenesProgreso.itemPadre         =     "
                + "                                  tmpPED.itemPadre     "
                + " AND   tblDctosOrdenesProgreso.idOperacion       =     "
                + "                              tmpPED.idOperacion )     "
                + "                           AS pesoTerminadoActual,     "
                + " ( SELECT                                              "
                + "     SUM(tblDctosOrdenesProgreso.cantidadTerminada)    "
                + " FROM tblDctosOrdenesProgreso                          "
                + " WHERE tblDctosOrdenesProgreso.idLocal          =      "
                + "                                   tmpPED.idLocal      "
                + " AND  tblDctosOrdenesProgreso.idControlTipo    != 2    "
                + " AND   tblDctosOrdenesProgreso.idTipoOrden      =      "
                + "                               tmpPED.idTipoOrden      "
                + " AND   tblDctosOrdenesProgreso.idOrden          =      "
                + "                                   tmpPED.idOrden      "
                + " AND   tblDctosOrdenesProgreso.itemPadre        =      "
                + "                                 tmpPED.itemPadre      "
                + " AND   tblDctosOrdenesProgreso.idOperacion       =     "
                + "                              tmpPED.idOperacion )     "
                + "                        AS cantidadTerminadaActual     "
                + " FROM (                                                "
                + "  SELECT tblDctosOrdenes.idLocal,                      "
                + "         tblDctosOrdenes.idTipoOrden,                  "
                + "         tblDctosOrdenes.idOrden,                      "
                + "         tblDctosOrdenesDetalle.itemPadre,             "
                + "         tblDctosOrdenes.idCliente,                    "
                + "         tblDctosOrdenes.numeroOrden,                  "
                + "         tblDctosOrdenes.idFicha,                      "
                + "         tblDctosOrdenes.idLog,                        "
                + "         tblDctosOrdenesDetalle.fechaEntrega,          "
                + "         tblDctosOrdenesDetalle.cantidad               "
                + "                           AS cantidadPedida,          "
                + "         tblDctosOrdenesDetalle.pesoPedido,            "
                + "         tmpFIC.referencia,                            "
                + "         tmpFIC.nombreOperacion,                       "
                + "         tmpFIC.idColor,                               "
                + "         tmpFIC.referenciaCliente,                     "
                + "         tmpFIC.nombreReferencia,                      "
                + "         tmpFIC.nombreItem,                            "
                + "         tblTerceros.nombreTercero,                    "
                + "         1 AS idOperacionAnterior,                     "
                + "         tmpFIC.idOperacion,                           "
                + "         tmpPPL.idOrdenPrograma                        "
                + "   FROM   tblDctosOrdenes                              "
                + "   INNER JOIN tblTerceros                              "
                + "   ON  tblTerceros.idCliente =                         "
                + "                       tblDctosOrdenes.idCliente       "
                + "   INNER JOIN (                                        "
                + "   SELECT tblJobProgramaPlusFicha.idOrden,             "
                + "          tblJobProgramaPlusFicha.idFicha,             "
                + "          MAX(tblJobProgramaPlusFicha.referencia)      "
                + "                        AS referencia,                 "
                + "          MAX(tblJobOperacion.nombreOperacion)         "
                + "                           AS nombreOperacion,         "
                + "          MAX(tblJobOperacion.idColor)                 "
                + "                                   AS idColor,         "
                + "        MAX(tblJobProgramaPlusFicha.referenciaCliente) "
                + "                         AS referenciaCliente,         "
                + "       MAX(tblJobProgramaPlusFicha.nombreReferencia)   "
                + "                          AS nombreReferencia,         "
                + "          tblJobEscalaDetalle.nombreItem,              "
                + "          tblJobProgramaPlusFicha.idOperacion          "
                + "   FROM   tblJobProgramaPlusFicha                      "
                + "   INNER JOIN tblJobOperacion                          "
                + "   ON tblJobProgramaPlusFicha.idOperacion      =       "
                + "                 tblJobOperacion.idOperacion           "
                + "   INNER JOIN tblJobEscalaDetalle                      "
                + "   ON tblJobProgramaPlusFicha.idEscala         =       "
                + "                tblJobEscalaDetalle.idEscala           "
                + "   AND tblJobProgramaPlusFicha.vrEscala        =       "
                + "                    tblJobEscalaDetalle.item           "
                + "   WHERE   tblJobProgramaPlusFicha.idOperacion =       "
                + operacion + "                                    "
                + "   AND tblJobProgramaPlusFicha.idEscala                "
                + "                    IN (600, 710, 910, 1000,1100)      "
                + "   GROUP BY tblJobProgramaPlusFicha.idOrden,           "
                + "            tblJobProgramaPlusFicha.idFicha,           "
                + "            tblJobProgramaPlusFicha.idOperacion,       "
                + "            tblJobEscalaDetalle.nombreItem)            "
                + "                                  AS tmpFIC            "
                + "   ON tblDctosOrdenes.idFicha  =                       "
                + "                             tmpFIC.idFicha            "
                + "   AND tblDctosOrdenes.idOrden =                       "
                + "                             tmpFIC.idOrden  	  "
                + "   INNER JOIN tblDctosOrdenesDetalle                   "
                + "   ON tblDctosOrdenesDetalle.idLocal     =             "
                + "                          tblDctosOrdenes.idLocal      "
                + "   AND   tblDctosOrdenesDetalle.idTipoOrden =          "
                + "                      tblDctosOrdenes.idTipoOrden      "
                + "   AND   tblDctosOrdenesDetalle.idOrden     =          "
                + "                        tblDctosOrdenes.idOrden        "
                + "   INNER JOIN                                          "
                + "    ( SELECT tblJobProgramaPlusFicha.idLocal           "
                + "          ,tblJobProgramaPlusFicha.idTipoOrden         "
                + "          ,tblJobProgramaPlusFicha.idOrden             "
                + "          ,tblJobProgramaPlusFicha.itemPadre           "
                + "          ,tblJobProgramaPlusFicha.idOperacion         "
                + "          ,tblJobProgramaPlusFicha.fechaPrograma       "
                + "          ,tblJobProgramaPlusFicha.estadoPrograma      "
                + "          ,tblJobProgramaPlusFicha.idOrdenPrograma     "
                + "      FROM tblJobProgramaPlusFicha                     "
                + "      WHERE tblJobProgramaPlusFicha.idOperacion =      "
                + operacion + "                                    "
                + "      GROUP BY tblJobProgramaPlusFicha.idLocal         "
                + "          ,tblJobProgramaPlusFicha.idTipoOrden         "
                + "          ,tblJobProgramaPlusFicha.idOrden             "
                + "          ,tblJobProgramaPlusFicha.itemPadre           "
                + "          ,tblJobProgramaPlusFicha.idOperacion         "
                + "          ,tblJobProgramaPlusFicha.fechaPrograma       "
                + "          ,tblJobProgramaPlusFicha.estadoPrograma      "
                + "          ,tblJobProgramaPlusFicha.idOrdenPrograma)    "
                + "                                         AS tmpPPL     "
                + "      ON    tblDctosOrdenesDetalle.idLocal     =       "
                + "                                     tmpPPL.idLocal    "
                + "      AND   tblDctosOrdenesDetalle.idTipoOrden =       "
                + "                                 tmpPPL.idTipoOrden    "
                + "      AND   tblDctosOrdenesDetalle.idOrden     =       "
                + "                                     tmpPPL.idOrden    "
                + "      AND   tblDctosOrdenesDetalle.itemPadre     =     "
                + "                                   tmpPPL.itemPadre    "
                + "    WHERE tblDctosOrdenes.idLocal            =  1     "
                + "   AND   tblDctosOrdenes.idTipoOrden         =   59     "
                + " AND   tblDctosOrdenes.numeroOrden        = " + numeroOrden + "          "
                + " AND tblDctosOrdenesDetalle.idEstadoRefOriginal != 9   "
                + " AND tmpPPL.idOperacion                    =           "
                + operacion + " )                                  "
                + "                                      AS tmpPED )      "
                + "                                      AS tmpPEN )      "
                + "                                        AS tmpRES      "
                + "      WHERE tmpRES.pesoTerminadoActual <               "
                + "                    ( tmpRES.pesoTerminadoAnterior *   "
                + xPorcentajeAdicion + " )                                "
                + "     AND tmpRES.fechaEntrega > '20190101'            "
                + "     ORDER BY tmpRES.numeroOrden";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = this.jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaPluFicha fachadaBean;

            //
            while (rs.next()) {

                //
                fachadaBean = new FachadaPluFicha();

                //
                fachadaBean.setIdLocal(rs.getInt("idLocal"));
                fachadaBean.setIdTipoOrden(rs.getInt("idTipoOrden"));
                fachadaBean.setIdOrden(rs.getInt("idOrden"));
                fachadaBean.setItemPadre(rs.getInt("itemPadre"));
                fachadaBean.setIdCliente(rs.getString("idCliente"));
                fachadaBean.setNumeroOrden(rs.getInt("numeroOrden"));
                fachadaBean.setIdFicha(rs.getInt("idFicha"));
                fachadaBean.setIdLog(rs.getInt("idLog"));
                fachadaBean.setFechaEntrega(rs.getString("fechaEntrega"));
                fachadaBean.setReferencia(rs.getString("referencia"));
                fachadaBean.setNombreOperacion(rs.getString("nombreOperacion"));
                fachadaBean.setIdColor(rs.getString("idColor").trim());
                fachadaBean.setReferenciaCliente(
                        rs.getString("referenciaCliente"));
                fachadaBean.setNombreItem(rs.getString("nombreItem"));
                fachadaBean.setNombreTercero(rs.getString("nombreTercero"));
                fachadaBean.setIdOrdenPrograma(rs.getInt("idOrdenPrograma"));

                //
                fachadaBean.setCantidadPendiente(
                        rs.getDouble("cantidadTerminadaAnterior")
                        - rs.getDouble("cantidadTerminadaActual"));
                fachadaBean.setPesoPendiente(
                        rs.getDouble("pesoTerminadoAnterior")
                        - rs.getDouble("pesoTerminadoActual"));

                //
                //
                fachadaBean.setPesoPerdido(0);
                fachadaBean.setCantidad(0);
                fachadaBean.setPesoTerminado(0);
                fachadaBean.setCantidadTerminada(0);
                fachadaBean.setCantidadPerdida(0);
                fachadaBean.setPorcentajeTerminado(0);

                //
                contenedor.add(fachadaBean);

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

    public Date getDateByMachine(String idMaquina) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Connection connection = null;
//        Calendar fechaFinMaquina = Calendar.getInstance();
        Date fechaFinMaquina = new Date();

        //
        String selectStr
                = "    SELECT TOP (1) "
                + "    [fechaFin]"
                + "    FROM [dbo].[tblDctosOrdenesProgreso]"
                + "    WHERE idMaquina=" + idMaquina
                + "    ORDER BY fechaFin DESC;";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = this.jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaPluFicha fachadaBean;
            String fecha = null;
            //
            if (rs.next()) {
                fecha = rs.getString("fechaFin");

            } else {
                System.out.println("No hay registro de ultimo registro de maquina ");

            }
            fechaFinMaquina = getFechaInicio(fecha, new Date());
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
            return fechaFinMaquina;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
            return fechaFinMaquina;
        } finally {

            // Cierra la conexion
            jdbcAccess.cleanup(connection, selectStatement, null);
            return fechaFinMaquina;

        }
    }

    public Double getWeightByIP(String ip) {

        //Averigua en tiempo de ejecucion cual es el nombre del archivo en ejecucion
        Class tipoObjeto = this.getClass();
        String nombreClase = tipoObjeto.getName();
        Connection connection = null;
        Double peso = 0D;
        String selectStr
                = "    SELECT TOP (1) "
                + "    [peso]"
                + "    FROM [dbo].[PesosBasculas]"
                + "    WHERE ip = '"+ip+"'   "
                + "   AND estado = 1";

        PreparedStatement selectStatement = null;

        try {

            // Obtiene una conexion a la base de datos, requiere Try
            connection = this.jdbcAccess.getConnection();

            // Prepara la sentencia de Busqueda
            selectStatement = connection.prepareStatement(selectStr);

            // Obtiene el resultset
            ResultSet rs = selectStatement.executeQuery();

            // Variable de fachada de los datos
            FachadaPluFicha fachadaBean;
            String fecha = null;
            //
            if (rs.next()) {
                peso= rs.getDouble("peso");
                ejecutarSentencia(" UPDATE [dbo].[PesosBasculas] SET estado = 0  WHERE ip ='"+ip+"';");

            } else {
                System.out.println("No hay registro de peso con ip: '"+ip+"'");

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
            return peso;

        } catch (Exception e) {
            System.out.println("Exception In :" + nombreClase + " " + e);
            return peso;
        } finally {

            // Cierra la conexion
            jdbcAccess.cleanup(connection, selectStatement, null);
            return peso;

        }
    }

    public Date getFechaInicio(String fechaUltimoRolloMaquina, Date fechaHoraActual) {
        Date fechaDate = null;
        if (fechaUltimoRolloMaquina != null) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                fechaDate = sdf.parse(fechaUltimoRolloMaquina);
            } catch (ParseException ex) {
                Logger.getLogger(Touch_Principal_DAO.class.getName()).log(Level.SEVERE, null, ex);
                fechaDate = new Date();
            }
        }
        if (fechaUltimoRolloMaquina == null || isYesterday(fechaDate)) {
            System.out.println("Logica avanzada de fecha de inicio");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(fechaHoraActual);
            int horaRegistro = calendar.get(Calendar.HOUR_OF_DAY);
            Calendar calendar2 = Calendar.getInstance();
            calendar2.set(Calendar.MINUTE, 0);
            calendar2.set(Calendar.SECOND, 0);

            if (horaRegistro >= 6 && horaRegistro < 14) {
                System.out.println("El registro se hizo en el primer turno ");
                calendar2.set(Calendar.HOUR_OF_DAY, 6);
                return calendar2.getTime();

            } else if (horaRegistro >= 14 && horaRegistro < 21) {
                System.out.println("El registro se hizo en el segundo turno");
                calendar2.set(Calendar.HOUR_OF_DAY, 14);
                return calendar2.getTime();

            } else if (horaRegistro >= 21 && horaRegistro < 24) {
                System.out.println("El registro se hizo en el tercer turno antes de media noche");
                calendar2.set(Calendar.HOUR_OF_DAY, 21);
                return calendar2.getTime();

            } else if (horaRegistro >= 0 && horaRegistro < 6) {
                System.out.println("El registro se hizo en el tercer turno despues de media noche");
                calendar2.set(Calendar.HOUR_OF_DAY, 21);
                calendar2.add(Calendar.DAY_OF_MONTH, -1);
                return calendar2.getTime();

            }
        }
        System.out.println("Sin procesar la fecha esta dentro del plazo valido de 24 horas");
        return fechaDate;
    }

// Suma o resta las horas recibidos a la fecha  
    private Date sumarRestarHorasFecha(Date fecha, int horas) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha); // Configuramos la fecha que se recibe
        calendar.add(Calendar.HOUR, horas);  // numero de horas a añadir, o restar en caso de horas<0
        return calendar.getTime(); // Devuelve el objeto Date con las nuevas horas añadidas
    }

    public boolean isYesterday(Date date) {
        Date fechaAyer = sumarRestarHorasFecha(new Date(), -24);
        return date.before(fechaAyer);
    }
public boolean ejecutarSentencia(String sql) {
        Class tipoObjeto = getClass();
        String nombreClase = tipoObjeto.getName();
        boolean okIngresar = false;
        Connection c = null;
        PreparedStatement ps = null;
        System.out.println(sql);
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
            this.jdbcAccess.cleanup(c, ps, null);
            return okIngresar;
        }
    }
}
