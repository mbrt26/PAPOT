package com.solucionesweb.losservlets;

// Importa los paquetes del lenguaje especificamente io y los
// que permiten trabajar el servlet.
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//
import com.solucionesweb.lasayudas.ProcesoIp;

//
import com.solucionesweb.losbeans.utilidades.JhDate;

// Importa la clase que contiene el Day
import com.solucionesweb.losbeans.utilidades.Day;

// Importa la clase que contiene el AgendaControlBean
import com.solucionesweb.losbeans.negocio.AgendaLogVisitaBean;

// Importa la clase que contiene el ValidacionColaboraReporteDctoBean
import com.solucionesweb.losbeans.utilidades.ValidacionColaboraReporteDctoBean;

// Importa la clase que contiene el Validacion
import com.solucionesweb.losbeans.utilidades.Validacion;

// Importa la clase que contiene el FachadaColaboraLogisticaBean
import com.solucionesweb.losbeans.fachada.FachadaColaboraLogisticaBean;

// Importa la clase que contiene el UsuarioBean
import com.solucionesweb.losbeans.negocio.UsuarioBean;

// Importa la clase que contiene el FachadaAgendaLogVisitaBean
import com.solucionesweb.losbeans.fachada.FachadaAgendaLogVisitaBean;

// Importa la clase que contiene el FachadaTerceroBean
import com.solucionesweb.losbeans.fachada.FachadaTerceroBean;

// Importa la clase que contiene el FachadaPagoBean
import com.solucionesweb.losbeans.fachada.FachadaPagoBean;

// Importa la clase que contiene el PagoBean
import com.solucionesweb.losbeans.negocio.PagoBean;

// Importa la clase que contiene el DctoBean
import com.solucionesweb.losbeans.negocio.DctoBean;

// Importa la clase que contiene el DctoBean
import com.solucionesweb.losbeans.negocio.PagoMedioBean;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * 
 * Este servlet permite revisar el listado de pagos a proveedores. /
 * vtaContenedorEmpresaHPagoCxP.jsp /
 * 
 * Este servlet implementa la interface GralManejadorRequest /
 */ 
 
public class VtasAdmEmpresaHPagoCxPManejadorRequest
        implements GralManejadorRequest {

/** 
     * BUTTON--
     * ("Consultar")-Permite consultar un listado de pagos /
     * ("Retirar")-Permite borrar un listado de pagos /vtaFrmRetEmpresaHPagoCxP.jsp /
     * ("Listar")-Genera un pdf de un listado de pagos /vtaFrmLstEmpresaHPagoCxP.jsp /
     * ("Regresar")-Regresa al menu principal /
     * 
     *  Metodo contructor por defecto, es decir, sin parametros /
     */

    public VtasAdmEmpresaHPagoCxPManejadorRequest() {
    }

    /**
     * BUTTON PARAMETER--
     * "Fecha Inicial"-Fecha inicial para ver un historico de pago /
     * "Fecha Final"-Fecha limite para ver un historico de pago /
     * 
     * Retorna la URL de la pagina que deber? ser entregada como respuesta
     * (normalmente un pagina jsp). /
     */
    public String generaPdf(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //
        String accionContenedor = request.getParameter("accionContenedor");

        //---
        int idTipoOrdenPedido          = 1;
        int xIdTipoTerceroCliente      = 2;
        int idTipoOrdenPedidoProceso   = idTipoOrdenPedido + 50;
        int estadoActivo = 9;

        //
        HttpSession sesion             = request.getSession();

        //
        UsuarioBean usuarioBean        =
                                (UsuarioBean)sesion.getAttribute("usuarioBean");

        //
        String xIdLocalUsuario         = usuarioBean.getIdLocalUsuarioStr();
        String xIdUsuario              = usuarioBean.getIdUsuarioSf0();
        int xEstadoActivo              = 9;

        //
        Day day                        = new Day();

        //
        String strFechaVisita          = day.getFechaFormateada();

        //
        AgendaLogVisitaBean agendaLogVisitaBean
                                       = new AgendaLogVisitaBean();

        //
        agendaLogVisitaBean.setEstado(xEstadoActivo);
        agendaLogVisitaBean.setFechaVisita(strFechaVisita);
        agendaLogVisitaBean.setIdUsuario(xIdUsuario);

        //
        FachadaAgendaLogVisitaBean  fachadaAgendaLogVisitaBean
                                       = new FachadaAgendaLogVisitaBean();

        //
        fachadaAgendaLogVisitaBean     =
                              agendaLogVisitaBean.listaLogActivo();

        //
        int xIdLogActual               = fachadaAgendaLogVisitaBean.getIdLog();

        //
        boolean xOkProceso             =
                       agendaLogVisitaBean.validaTipoOrdenProceso(
                                                     idTipoOrdenPedidoProceso);
        //
        if (!xOkProceso) {

            boolean xOkOcupado        = agendaLogVisitaBean.validaLogOcupado();

            //
            if (xOkOcupado) {

               // validacion
               Validacion validacion    = new Validacion();

               validacion.setDescripcionError("ERROR, " +
                       "                         DEBE TERMINAR PROCESO ACTIVO");

               //
 	           request.setAttribute("validacion",validacion);
               return "/jsp/gralError.jsp";
            }

            //
            if (!xOkOcupado) {

               //
               int idPeriodo            = 200611;
               int estadoAtendido       = 1; // visitaActiva
               int estadoProgramada     = 9; // visitaProgramada
               int idEstadoVisita       = 1; // Programada

               //
               int idLog = agendaLogVisitaBean.maximoIdLog() + 1;

               //
               agendaLogVisitaBean.setIdUsuario(xIdUsuario);
               agendaLogVisitaBean.setIdCliente(xIdUsuario);
               agendaLogVisitaBean.setIdPeriodo(idPeriodo);
               agendaLogVisitaBean.setFechaVisita(strFechaVisita);
               agendaLogVisitaBean.setIdLog(idLog);
               agendaLogVisitaBean.setIdEstadoVisita(idEstadoVisita);
               agendaLogVisitaBean.setEstado(estadoAtendido);

               //
               boolean okRetirar          =
                    agendaLogVisitaBean.actualizaLogVisitaUsuario(
                                                              estadoProgramada);

               // estadoActivo = 9
               agendaLogVisitaBean.setEstado(estadoProgramada);

               //
               boolean okIngreso = agendaLogVisitaBean.ingresaLogVisita();

               //
               xIdLogActual      = idLog;
            }
        }


        //
        JhDate jhDate = new JhDate();

        //
        String fechaTxHM = null;

        //
        try {
            //
            fechaTxHM = jhDate.getDate(4, false);
        } catch (Exception ex) {
            Logger.getLogger(VtasAdmEmpresaFinalizaPedidoManejadorRequest.class.getName()).log(Level.SEVERE, null, ex);
        }


        // Validacion de accion relacionada con el formulario requerido
        if (accionContenedor != null) {

            //
            if (accionContenedor.compareTo("detallarCotizacion") == 0) {

                String idLocal = request.getParameter("idLocal");
                String idTipoOrden = request.getParameter("idTipoOrden");
                String idOrden = request.getParameter("idOrden");

                //
                GeneraPDFFactura generaPDFServlet = new GeneraPDFFactura();

                //
                generaPDFServlet.setIdOrden(idOrden);
                generaPDFServlet.setIdTipoOrden(idTipoOrden);

                //
                generaPDFServlet.generaPdf(request, response);

            }

            //
            if (accionContenedor.compareTo("Regresar") == 0) {
                return "/jsp/empty.htm";
            }

            //
            if (accionContenedor.compareTo("Salir") == 0) {
                return "/jsp/empty.htm";
            }

            //
            if (accionContenedor.compareTo("Listar") == 0) {

                //
                String idTipoOrden = request.getParameter("xIdTipoOrden");
                String fechaInicial = request.getParameter("xFechaInicial");
                String fechaFinal = request.getParameter("xFechaFinal");
                String idCliente = request.getParameter("xIdCliente");
                String idLocal = request.getParameter("xIdLocal");

                //
                GeneraPDFHistoriaPagoCxC generaPDF = new GeneraPDFHistoriaPagoCxC();

                //
                generaPDF.setNitCC(idCliente);
                generaPDF.setIdTipoOrden(idTipoOrden);
                generaPDF.setIdLocal(idLocal);
                generaPDF.setFechaInicial(fechaInicial);
                generaPDF.setFechaFinal(fechaFinal);
                generaPDF.setTerceroReporte("PROVEEDOR");
                generaPDF.setTituloReporte("HISTORIA PAGOS DEL " +
                        fechaInicial + " AL " +
                        fechaFinal);

                //
                generaPDF.generaPdf(request, response);

            }

            if (accionContenedor.compareTo("retiraRecibo") == 0) {

                //
                String xIdLocal = request.getParameter("xIdLocal");
                String xIdTipoOrden = request.getParameter("xIdTipoOrden");
                String xIndicador = request.getParameter("xIndicador");
                String xIdRecibo = request.getParameter("xIdRecibo");

                //
                String fechaInicial = request.getParameter("fechaInicial");
                String fechaFinal = request.getParameter("fechaFinal");
                String xIdCliente = request.getParameter("xIdCliente");

                //
                String xIdReciboCruce = xIdRecibo;

                //
                FachadaPagoBean fachadaPagoBean = new FachadaPagoBean();

                //
                PagoBean pagoBean = new PagoBean();

                //
                pagoBean.setIdLocal(xIdLocal);
                pagoBean.setIdTipoOrden(xIdTipoOrden);
                pagoBean.setIndicador(xIndicador);
                pagoBean.setIdRecibo(xIdRecibo);
                pagoBean.setIdReciboCruce(xIdReciboCruce);

                //
                boolean xReciboRetirado = pagoBean.validaReciboRetirado();

                //
                if (xReciboRetirado) {

                    //
                    Validacion validacion = new Validacion();

                    //
                    validacion.reasignar("ERROR, ESTE RECIBO FUE RETIRADO EN OTRO PROCESO",
                            xIdReciboCruce);

                    //
                    request.setAttribute("validacion", validacion);

                    //
                    return "/jsp/gralError.jsp";


                }

                //
                fachadaPagoBean = pagoBean.listaUnFCH();

                //
                if (fachadaPagoBean.getVrPago() < 0) {

                    //
                    Validacion validacion = new Validacion();

                    //
                    validacion.reasignar("ERROR, INTENTA RETIRAR RECIBO RETIRADO",
                            xIdReciboCruce);

                    //
                    request.setAttribute("validacion", validacion);

                    //
                    return "/jsp/gralError.jsp";


                }

                //
                pagoBean.setIdLocal(xIdLocal);
                pagoBean.setIdTipoOrden(xIdTipoOrden);

                //
                int idMaximaPlanilla = pagoBean.maximaPlanilla() + 1;

                // Importa la clase que contiene el DctoBean
                DctoBean dctoBean = new DctoBean();

                //
                dctoBean.setIdDcto(fachadaPagoBean.getIdDcto());
                dctoBean.setIndicador(fachadaPagoBean.getIndicador());
                dctoBean.setIdTipoOrden(xIdTipoOrden);
                dctoBean.setIdLocal(xIdLocal);
                dctoBean.setIdDcto(fachadaPagoBean.getIdDcto());
                dctoBean.setVrPago(fachadaPagoBean.getVrPago() * (-1));
                dctoBean.setVrRteFuente(fachadaPagoBean.getVrRteFuente() * (-1));
                dctoBean.setVrDsctoFcro(fachadaPagoBean.getVrDescuento() * (-1));
                dctoBean.setVrRteIva(fachadaPagoBean.getVrRteIva() * (-1));
                dctoBean.setVrRteIca(fachadaPagoBean.getVrRteIca() * (-1));

                //
                boolean okPago = dctoBean.actualizaPago();

                //
                pagoBean.setIdLocal(xIdLocal);
                pagoBean.setIndicador(fachadaPagoBean.getIndicador());
                pagoBean.setIdTipoOrden(xIdTipoOrden);

                //
                int xIdReciboMAX =
                        pagoBean.maximoReciboIdLocalxIndicador() + 1;

                //
                pagoBean.setIdLocal(xIdLocal);
                pagoBean.setIdTipoOrden(xIdTipoOrden);
                pagoBean.setIdRecibo(xIdReciboMAX);
                pagoBean.setIndicador(fachadaPagoBean.getIndicador());
                pagoBean.setFechaPago(strFechaVisita);
                pagoBean.setVrPago(fachadaPagoBean.getVrPago() * (-1));
                pagoBean.setNitCC(fachadaPagoBean.getNitCC());
                pagoBean.setEstado(estadoActivo);
                pagoBean.setIdUsuario(fachadaPagoBean.getIdUsuario());
                pagoBean.setVrRteFuente(fachadaPagoBean.getVrRteFuente() * (-1));
                pagoBean.setVrDescuento(fachadaPagoBean.getVrDescuento() * (-1));
                pagoBean.setVrRteIva(fachadaPagoBean.getVrRteIva() * (-1));
                pagoBean.setVrRteIca(fachadaPagoBean.getVrRteIca() * (-1));
                pagoBean.setIdDcto(fachadaPagoBean.getIdDcto());
                pagoBean.setIdDctoNitCC(fachadaPagoBean.getIdDctoNitCC());
                pagoBean.setIdPlanilla(idMaximaPlanilla);
                pagoBean.setIdLog(xIdLogActual);
                pagoBean.setIdVendedor(fachadaPagoBean.getIdVendedorStr());
                pagoBean.setIdReciboCruce(xIdReciboCruce);

                // ingresaPago
                boolean okIngresaPago = pagoBean.ingresaPago();

                //
                PagoMedioBean pagoMedioBean = new PagoMedioBean();

                //
                pagoMedioBean.setIdLocal(xIdLocal);
                pagoMedioBean.setIdTipoOrden(xIdTipoOrden);
                pagoMedioBean.setIndicador(fachadaPagoBean.getIndicador());
                pagoMedioBean.setIdRecibo(xIdReciboCruce);
                pagoMedioBean.setIdLog(xIdLogActual);

                //
                pagoMedioBean.ingresaRetiro(xIdReciboMAX);

                // ---
                int xIdTipoOrdenVenta = 9;
                int tareaVisita = 6;   // Cotizacion
                int estadoProgramado = 1;
                int xIdEstadoTxSinTx = 1;

                //
                int xIdTipoOrdenPagoProceso = 59;


                // finalizaVisita
                agendaLogVisitaBean.setIdLog(xIdLogActual);
                agendaLogVisitaBean.setIdCliente(
                        fachadaAgendaLogVisitaBean.getIdCliente());
                agendaLogVisitaBean.setIdUsuario(
                        fachadaAgendaLogVisitaBean.getIdUsuario());
                agendaLogVisitaBean.setIdTipoOrden(xIdTipoOrdenVenta);
                agendaLogVisitaBean.setIdEstadoTx(xIdEstadoTxSinTx);
                agendaLogVisitaBean.setIdLocal(xIdLocal);
                agendaLogVisitaBean.setFechaVisitaStr(strFechaVisita);
                agendaLogVisitaBean.setIdEstadoVisita(tareaVisita);
                agendaLogVisitaBean.setEstado(estadoProgramado);
                //
                ProcesoIp procesoIp = new ProcesoIp();

                //
                String ipTx = procesoIp.getIpTx(request);

                //
                agendaLogVisitaBean.setIpTx(ipTx);
                agendaLogVisitaBean.setFechaTx(fechaTxHM);

                //
                boolean okLog = agendaLogVisitaBean.finalizaVisita();

                //
                GeneraPDFPagoRecibo generaPDF =
                        new GeneraPDFPagoRecibo();

                //
                generaPDF.setIdLocal(fachadaPagoBean.getIdLocal());
                generaPDF.setIdTipoOrden(fachadaPagoBean.getIdTipoOrden());
                generaPDF.setIdRecibo(xIdReciboMAX);
                generaPDF.setIndicador(fachadaPagoBean.getIndicador());
                generaPDF.setTerceroReporte("PROVEEDOR   ");
                generaPDF.setTituloReporte("ANULA RECIBO CAJA # ");

                //
                generaPDF.generaPdf(request, response);
            }


            if (accionContenedor.compareTo("Retirar") == 0) {

                //
                String fechaInicial = request.getParameter("fechaInicial");
                String fechaFinal = request.getParameter("fechaFinal");
                String xIdCliente = request.getParameter("xIdCliente");
                String xIdTipoOrden = request.getParameter("xIdTipoOrden");

                // Bean de ValidacionLogPcBean
                ValidacionColaboraReporteDctoBean campoAValidar
                        = new ValidacionColaboraReporteDctoBean("fechaInicial", fechaInicial);

                // Valida el fechaInicial
                campoAValidar.validarCampoFecha();

                if (campoAValidar.isValido() == false) {

                    // Aqui escribe el Bean de Validacion en el Request
                    request.setAttribute("validacionColaboraReporteDctoBean",
                            campoAValidar);

                    return "/jsp/gralErrReporteCliente.jsp";
                }

                // Valida el fechaFinal
                campoAValidar.reasignar("fechaFinal", fechaFinal);

                // Valida el fechaFinal
                campoAValidar.validarCampoFecha();

                if (campoAValidar.isValido() == false) {

                    // Aqui escribe el Bean de Validacion en el Request
                    request.setAttribute("validacionColaboraReporteDctoBean",
                            campoAValidar);

                    return "/jsp/gralErrReporteCliente.jsp";
                }


                // Retorna a seleccionar cliente
                if (fachadaAgendaLogVisitaBean.getIdCliente() == null) {
                    return "/jsp/vtaContenedorEmpresaProveedorSelecciona.jsp";
                }

                // fachadaColaboraLogisticaBean
                FachadaColaboraLogisticaBean fachadaColaboraLogisticaBean
                                           = new FachadaColaboraLogisticaBean();
                //
                fachadaColaboraLogisticaBean.setIdUsuario(xIdUsuario);
                fachadaColaboraLogisticaBean.setIdCliente(
                        fachadaAgendaLogVisitaBean.getIdCliente());
                fachadaColaboraLogisticaBean.setFechaInicial(fechaInicial);
                fachadaColaboraLogisticaBean.setFechaFinal(fechaFinal);
                fachadaColaboraLogisticaBean.setIdLocal(xIdLocalUsuario);
                fachadaColaboraLogisticaBean.setIdTipoOrden(idTipoOrdenPedido);

                //
                FachadaTerceroBean fachadaTerceroBean
                                                = new FachadaTerceroBean();

                //
                fachadaTerceroBean.setIdCliente(
                                     fachadaAgendaLogVisitaBean.getIdCliente());
                fachadaTerceroBean.setIdTipoTercero(xIdTipoTerceroCliente);
                fachadaTerceroBean.setIdTipoOrden(xIdTipoOrden);

                //
                request.setAttribute("fachadaTerceroBean", fachadaTerceroBean);
                request.setAttribute("fachadaAgendaLogVisitaBean",
                        fachadaAgendaLogVisitaBean);
                request.setAttribute("fachadaColaboraLogisticaBean",
                        fachadaColaboraLogisticaBean);

                //
                return "/jsp/vtaFrmRetEmpresaHPagoCxP.jsp";

            }

            if (accionContenedor.compareTo("Consultar") == 0) {

                //
                String fechaInicial = request.getParameter("fechaInicial");
                String fechaFinal = request.getParameter("fechaFinal");
                String xIdCliente = request.getParameter("xIdCliente");
                String xIdTipoOrden = request.getParameter("xIdTipoOrden");

                // Bean de ValidacionLogPcBean
                ValidacionColaboraReporteDctoBean campoAValidar
                         = new ValidacionColaboraReporteDctoBean("fechaInicial", fechaInicial);

                // Valida el fechaInicial
                campoAValidar.validarCampoFecha();

                if (campoAValidar.isValido() == false) {

                    // Aqui escribe el Bean de Validacion en el Request
                    request.setAttribute("validacionColaboraReporteDctoBean",
                            campoAValidar);

                    return "/jsp/gralErrReporteCliente.jsp";
                }

                // Valida el fechaFinal
                campoAValidar.reasignar("fechaFinal", fechaFinal);

                // Valida el fechaFinal
                campoAValidar.validarCampoFecha();

                if (campoAValidar.isValido() == false) {

                    // Aqui escribe el Bean de Validacion en el Request
                    request.setAttribute("validacionColaboraReporteDctoBean",
                            campoAValidar);

                    return "/jsp/gralErrReporteCliente.jsp";
                }

                // Retorna a seleccionar cliente
                if (fachadaAgendaLogVisitaBean.getIdCliente() == null) {
                    return "/jsp/vtaContenedorEmpresaProveedorSelecciona.jsp";
                }

                // fachadaColaboraLogisticaBean
                FachadaColaboraLogisticaBean fachadaColaboraLogisticaBean
                                           = new FachadaColaboraLogisticaBean();
                //
                fachadaColaboraLogisticaBean.setIdUsuario(xIdUsuario);
                fachadaColaboraLogisticaBean.setIdCliente(
                        fachadaAgendaLogVisitaBean.getIdCliente());
                fachadaColaboraLogisticaBean.setFechaInicial(fechaInicial);
                fachadaColaboraLogisticaBean.setFechaFinal(fechaFinal);
                fachadaColaboraLogisticaBean.setIdLocal(xIdLocalUsuario);
                fachadaColaboraLogisticaBean.setIdTipoOrden(idTipoOrdenPedido);

                //
                FachadaTerceroBean fachadaTerceroBean
                                                = new FachadaTerceroBean();

                //
                fachadaTerceroBean.setIdCliente(
                                     fachadaAgendaLogVisitaBean.getIdCliente());
                fachadaTerceroBean.setIdTipoTercero(xIdTipoTerceroCliente);
                fachadaTerceroBean.setIdTipoOrden(xIdTipoOrden);

                //
                request.setAttribute("fachadaTerceroBean", fachadaTerceroBean);
                request.setAttribute("fachadaAgendaLogVisitaBean",
                        fachadaAgendaLogVisitaBean);
                request.setAttribute("fachadaColaboraLogisticaBean",
                        fachadaColaboraLogisticaBean);
                return "/jsp/vtaFrmLstEmpresaHPagoCxP.jsp";

            }

            // listaPlanilla
            if (accionContenedor.compareTo("listaPlanilla") == 0) {

                //
                String idLocal = request.getParameter("xIdLocal");
                String idTipoOrden = request.getParameter("xIdTipoOrden");
                String idPlanilla = request.getParameter("xIdPlanilla");

                //
                GeneraPDFPagoPlanillaVendedor generaPDF =
                        new GeneraPDFPagoPlanillaVendedor();

                //
                generaPDF.setIdLocal(new Integer(xIdLocalUsuario).intValue());
                generaPDF.setIdTipoOrden(idTipoOrden);
                generaPDF.setIdPlanilla(idPlanilla);
                generaPDF.setTerceroReporte("PROVEEDOR  ");
                generaPDF.setTituloReporte("COMPROBANTE DE EGRESO # ");
                generaPDF.setNombreReporte("VtasRepCxPPagoPlanillaVendedor");

                //
                generaPDF.generaPdf(request, response);

            }

            // listaRecibo
            if (accionContenedor.compareTo("listaRecibo") == 0) {

                //
                String idLocal = request.getParameter("xIdLocal");
                String idTipoOrden = request.getParameter("xIdTipoOrden");
                String idRecibo = request.getParameter("xIdRecibo");
                String indicador = request.getParameter("xIndicador");

                //
                GeneraPDFPagoRecibo generaPDF =
                        new GeneraPDFPagoRecibo();

                //
                generaPDF.setIdLocal(new Integer(xIdLocalUsuario).intValue());
                generaPDF.setIdTipoOrden(idTipoOrden);
                generaPDF.setIdRecibo(idRecibo);
                generaPDF.setIndicador(indicador);
                generaPDF.setTituloReporte(indicador);
                generaPDF.setTerceroReporte("PROVEEDOR   ");
                generaPDF.setTituloReporte("COPROBANTE DE EGRESO # ");

                //
                generaPDF.generaPdf(request, response);

            }
        }

        return "/jsp/empty.htm";

    }
}