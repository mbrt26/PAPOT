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

import java.util.logging.Level;
import java.util.logging.Logger;

// Este servlet implementa la interface GralManejadorRequest
public class VtasAdmClienteConsultarHPagoManejadorRequest
        implements GralManejadorRequest {

    // Metodo contructor por defecto, es decir, sin parametros
    public VtasAdmClienteConsultarHPagoManejadorRequest() { }

    /**
     * Retorna la URL de la pagina que deber? ser entregada como respuesta
     * (normalmente un pagina jsp).
     */
    public String generaPdf(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //
        String accionContenedor = request.getParameter("accionContenedor");

        //---
        int idTipoOrdenPedido          = 9;
        int xIdTipoTerceroCliente      = 1;
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
            Logger.getLogger(VtasAdmClienteConsultarHPagoManejadorRequest.class.getName()).log(Level.SEVERE, null, ex);
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

                //
                return "/jsp/mnuControlClienteBB.jsp";
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
                generaPDF.setTerceroReporte("CLIENTE");
                generaPDF.setTituloReporte("HISTORIA PAGOS DEL " +
                        fechaInicial + " AL " +
                        fechaFinal);

                //
                generaPDF.generaPdf(request, response);

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

                   //
                   return "/jsp/vtaContenedorClienteSeleccionarNombre.jsp";
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
                return "/jsp/vtaFrmLstClienteConsultarHPago.jsp";

            }

            // listaPlanilla
            if (accionContenedor.compareTo("listaPlanilla") == 0) {

                //
                String idLocal = request.getParameter("xIdLocal");
                String idTipoOrden = request.getParameter("xIdTipoOrden");
                String idPlanilla = request.getParameter("xIdPlanilla");

                //
                GeneraPDFPagoPlanilla generaPDF =
                        new GeneraPDFPagoPlanilla();

                //
                generaPDF.setIdLocal(new Integer(xIdLocalUsuario).intValue());
                generaPDF.setIdTipoOrden(idTipoOrden);
                generaPDF.setIdPlanilla(idPlanilla);
                generaPDF.setTerceroReporte("CLIENTE   ");
                generaPDF.setTituloReporte("PLANILLA DE PAGO # ");

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
                generaPDF.setTituloReporte("CLIENTE   ");
                generaPDF.setTituloReporte("RECIBO DE CAJA # ");

                //
                generaPDF.generaPdf(request, response);

            }

        }

        return "/jsp/empty.htm";

    }
}