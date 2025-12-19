package com.solucionesweb.losservlets;

// Importa los paquetes del lenguaje especificamente io y los
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Importa la clase ProcesoPago
import com.solucionesweb.lasayudas.ProcesoPagoContado;

// Importa la clase ProcesoIngreso
import com.solucionesweb.lasayudas.ProcesoIngreso;

//
import com.solucionesweb.lasayudas.ProcesoIp;

//
import com.solucionesweb.losbeans.utilidades.JhDate;

// Importa la clase que contiene el Day
import com.solucionesweb.losbeans.utilidades.Day;

// Importa la clase que contiene el DctoOrdenBean
import com.solucionesweb.losbeans.negocio.DctoOrdenBean;

// Importa la clase que contiene el AgendaLogVisitaBean
import com.solucionesweb.losbeans.negocio.AgendaLogVisitaBean;

// Importa la clase que contiene el ColaboraTercero
import com.solucionesweb.losbeans.colaboraciones.ColaboraTercero;

// Importa la clase que contiene el FachadaAgendaLogVisitaBean
import com.solucionesweb.losbeans.fachada.FachadaAgendaLogVisitaBean;

// Importa la clase que contiene el FachadaDctoBean
import com.solucionesweb.losbeans.fachada.FachadaDctoBean;

// Importa la clase que contiene el FachadaTerceroBean
import com.solucionesweb.losbeans.fachada.FachadaTerceroBean;

// Importa la clase que contiene el UsuarioBean
import com.solucionesweb.losbeans.negocio.UsuarioBean;

// Importa la clase que contiene el Bean de Validacion
import com.solucionesweb.losbeans.utilidades.Validacion;

// Importa la clase que contiene el FachadaDctoOrdenBean
import com.solucionesweb.losbeans.fachada.FachadaDctoOrdenBean;

// Importa la clase que contiene el ColaboraOrdenDetalleBean
import com.solucionesweb.losbeans.colaboraciones.ColaboraOrdenDetalleBean;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaDctoOrdenDetalleBean;

// Importa la clase que contiene el PagoMedioBean
import com.solucionesweb.losbeans.negocio.PagoMedioBean;

// Importa la clase que contiene el PagoBean
import com.solucionesweb.losbeans.negocio.PagoBean;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaPagoMedioBean;

// Importa la clase que contiene el ValidacionUsuarioBean
import com.solucionesweb.losbeans.negocio.TerceroBean;
import javax.servlet.http.HttpSession;

// Este servlet implementa la interface GralManejadorRequest
public class VtasAdmEmpresaFinalizaVentaManejadorRequest
        implements GralManejadorRequest {

    // Metodo contructor por defecto, es decir, sin parametros
    public VtasAdmEmpresaFinalizaVentaManejadorRequest() {
    }

    /**
     * Retorna la URL de la pagina que deber? ser entregada como respuesta
     * (normalmente un pagina jsp).
     */
    public String generaPdf(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        //
        String accionContenedor = request.getParameter("accionContenedor");

        //
        int xIdEstadoTxSinTx = 1;
        int xIndicadorTemporal = 1;

        //
        int xIdTipoTerceroCliente     = 1;
        int xIdTipoOrdenPedido        = 9;
        int xIdTipoOrdenPedidoProceso = xIdTipoOrdenPedido + 50;

        //
        int estadoActivo = 9;

        //
        Day day = new Day();

        //
        String strFechaVisita = day.getFechaFormateada();

        //
        JhDate jhDate         = new JhDate();

        //
        String fechaTxHM      = null;

        //
        try {
            //
            fechaTxHM = jhDate.getDate(4, false);
        } catch (Exception ex) {
            Logger.getLogger(VtasAdmEmpresaFinalizaVentaManejadorRequest.class.getName()).log(Level.SEVERE, null, ex);
        }


        //
        HttpSession sesion = request.getSession();
        UsuarioBean usuarioBean =
                (UsuarioBean) sesion.getAttribute("usuarioBean");

        //
        String idUsuario = usuarioBean.getIdUsuarioStr();
        int xIdLocalUsuario = usuarioBean.getIdLocalUsuario();

        // Validacion de accion relacionada con el formulario requerido
        if (accionContenedor != null) {

            System.out.println(" accionContenedor " + accionContenedor);

            //
            if (accionContenedor.compareTo("Salir") == 0) {
                return "/jsp/empty.htm";
            }

            // retiraMedio
            if (accionContenedor.compareTo("retiraMedio") == 0) {

                //
                String xIdLogActual = request.getParameter("xIdLog");
                String xIdTipoOrden = request.getParameter("xIdTipoOrden");
                String xIdRecibo = request.getParameter("xIdRecibo");
                String xIdLocal = request.getParameter("xIdLocal");

                //
                PagoMedioBean pagoMedioBean = new PagoMedioBean();

                pagoMedioBean.setIdLocal(xIdLocal);
                pagoMedioBean.setIdTipoOrden(xIdTipoOrden);
                pagoMedioBean.setIdRecibo(xIdRecibo);

                //
                pagoMedioBean.retiraPagoTemporal(
                        new Integer(xIdLogActual).intValue());


                // PagoBean
                PagoBean pagoBean = new PagoBean();

                //
                pagoBean.setIdLocal(xIdLocal);
                pagoBean.setIdTipoOrden(xIdTipoOrden);
                pagoBean.setIdRecibo(xIdRecibo);
                pagoBean.setIdLog(xIdLogActual);

                //
                pagoBean.retiraPagoTemporal();

                //
                AgendaLogVisitaBean agendaLogVisitaBean = new AgendaLogVisitaBean();
                agendaLogVisitaBean.setEstado(estadoActivo);
                agendaLogVisitaBean.setFechaVisitaStr(strFechaVisita);
                agendaLogVisitaBean.setIdUsuario(idUsuario);

                //
                FachadaAgendaLogVisitaBean fachadaAgendaLogVisitaBean = new FachadaAgendaLogVisitaBean();
                fachadaAgendaLogVisitaBean =
                        agendaLogVisitaBean.seleccionaVisitaEstadoFecha();

                //
                fachadaAgendaLogVisitaBean.setIdLocal(xIdLocalUsuario);
                fachadaAgendaLogVisitaBean.setIdTipoOrden(xIdTipoOrdenPedidoProceso);

                //
                ColaboraTercero colaboraTercero = new ColaboraTercero();
                colaboraTercero.setIdCliente(
                        fachadaAgendaLogVisitaBean.getIdCliente());
                colaboraTercero.setIdTipoTercero(xIdTipoTerceroCliente);

                //
                FachadaTerceroBean fachadaTerceroBean = new FachadaTerceroBean();

                //
                fachadaTerceroBean = colaboraTercero.listaTerceroFCH();

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaAgendaLogVisitaBean",
                        fachadaAgendaLogVisitaBean);
                request.setAttribute("fachadaTerceroBean",
                        fachadaTerceroBean);

                // Retorna a seleccionar cliente
                if (fachadaTerceroBean.getIdCliente() == null) {

                    return "/jsp/vtaContenedorEmpresaSelecciona.jsp";

                }

                //
                return "/jsp/vtaFrmSelEmpresaFinalizaVenta.jsp";

            }


            // Guardar
            if (accionContenedor.compareTo("GUARDAR") == 0) {

                //
                String xVrPago = request.getParameter("xVrPago");
                String xIdLogActual = request.getParameter("idLog");
                String xIdMedio = request.getParameter("xIdMedio");

                //
                Validacion validacion = new Validacion();

                //
                validacion.reasignar("VALOR PAGO", xVrPago);

                //
                validacion.validarCampoDouble();

                //
                if (!validacion.isValido()) {

                    //
                    request.setAttribute("validacion", validacion);

                    //
                    return "/jsp/gralError.jsp";

                }

                //
                FachadaDctoOrdenDetalleBean fachadaDctoOrdenDetalleBean = new FachadaDctoOrdenDetalleBean();

                ColaboraOrdenDetalleBean colaboraDctoOrdenBean = new ColaboraOrdenDetalleBean();

                //
                colaboraDctoOrdenBean.setIdLog(xIdLogActual);
                colaboraDctoOrdenBean.setIdTipoOrden(xIdTipoOrdenPedidoProceso);

                //
                fachadaDctoOrdenDetalleBean =
                        colaboraDctoOrdenBean.liquidaOrdenFCH();

                // PagoBean
                PagoBean pagoBean = new PagoBean();

                //
                pagoBean.setIdLocal(xIdLocalUsuario);
                pagoBean.setIdTipoOrden(xIdTipoOrdenPedidoProceso);

                //
                int idMaximaPlanilla = pagoBean.maximaPlanilla() + 1;

                //
                PagoMedioBean pagoMedioBean = new PagoMedioBean();

                //
                pagoMedioBean.setIdLocal(xIdLocalUsuario);
                pagoMedioBean.setIdTipoOrden(xIdTipoOrdenPedidoProceso);
                pagoMedioBean.setIndicador(xIndicadorTemporal);

                //
                pagoBean.setIdLocal(xIdLocalUsuario);
                pagoBean.setIndicador(xIndicadorTemporal);
                pagoBean.setIdTipoOrden(xIdTipoOrdenPedidoProceso);
                pagoBean.setIdDcto(xIdLogActual);
                pagoBean.setIdLog(xIdLogActual);

                //
                int xIdReciboMAX =
                        pagoBean.maximoReciboIdLocalxIndicador() + 1;

                //
                pagoBean.setIdLocal(xIdLocalUsuario);
                pagoBean.setIdTipoOrden(xIdTipoOrdenPedidoProceso);
                pagoBean.setIdRecibo(xIdReciboMAX);
                pagoBean.setIndicador(xIndicadorTemporal);
                pagoBean.setFechaPago(strFechaVisita);
                pagoBean.setVrPago(xVrPago);
                pagoBean.setNitCC(fachadaDctoOrdenDetalleBean.getIdCliente());
                pagoBean.setEstado(estadoActivo);
                pagoBean.setIdUsuario(idUsuario);
                pagoBean.setVrRteFuente(0);
                pagoBean.setVrDescuento(0);
                pagoBean.setVrRteIva(0);
                pagoBean.setVrRteIca(0);
                pagoBean.setIdDcto(xIdLogActual);
                pagoBean.setIdDctoNitCC(fachadaDctoOrdenDetalleBean.getIdCliente());
                pagoBean.setIdPlanilla(idMaximaPlanilla);
                pagoBean.setVrSaldo(0);
                pagoBean.setIdLog(xIdLogActual);
                pagoBean.setIdReciboCruce(0);

                // ingresaPago
                boolean okIngresaPago = pagoBean.ingresaPago();

                //
                int xEstadoOk = 1;
                int xIdBancoOk = 0;
                String xIdDctoMedioOk = "";

                //
                pagoMedioBean.setIdLocal(xIdLocalUsuario);
                pagoMedioBean.setIdTipoOrden(xIdTipoOrdenPedidoProceso);
                pagoMedioBean.setIdRecibo(xIdReciboMAX);
                pagoMedioBean.setIndicador(xIndicadorTemporal);
                pagoMedioBean.setVrMedio(xVrPago);
                pagoMedioBean.setEstado(xEstadoOk);
                pagoMedioBean.setIdBanco(xIdBancoOk);
                pagoMedioBean.setIdDctoMedio(xIdDctoMedioOk);
                pagoMedioBean.setFechaCobro(strFechaVisita);
                pagoMedioBean.setIdMedio(xIdMedio);
                pagoMedioBean.setIdLog(xIdLogActual);

                //
                pagoMedioBean.ingresar();

                //
                AgendaLogVisitaBean agendaLogVisitaBean
                                           = new AgendaLogVisitaBean();
                agendaLogVisitaBean.setEstado(estadoActivo);
                agendaLogVisitaBean.setFechaVisitaStr(strFechaVisita);
                agendaLogVisitaBean.setIdUsuario(idUsuario);

                //
                FachadaAgendaLogVisitaBean fachadaAgendaLogVisitaBean
                                           = new FachadaAgendaLogVisitaBean();
                fachadaAgendaLogVisitaBean =
                        agendaLogVisitaBean.seleccionaVisitaEstadoFecha();

                //
                fachadaAgendaLogVisitaBean.setIdLocal(xIdLocalUsuario);
                fachadaAgendaLogVisitaBean.setIdTipoOrden(
                                                     xIdTipoOrdenPedidoProceso);

                //
                ColaboraTercero colaboraTercero = new ColaboraTercero();
                colaboraTercero.setIdCliente(
                        fachadaAgendaLogVisitaBean.getIdCliente());
                colaboraTercero.setIdTipoTercero(xIdTipoTerceroCliente);

                //
                FachadaTerceroBean fachadaTerceroBean
                                            = new FachadaTerceroBean();

                //
                fachadaTerceroBean = colaboraTercero.listaTerceroFCH();

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaAgendaLogVisitaBean",
                        fachadaAgendaLogVisitaBean);
                request.setAttribute("fachadaTerceroBean",
                        fachadaTerceroBean);

                // Retorna a seleccionar cliente
                if (fachadaTerceroBean.getIdCliente() == null) {

                    return "/jsp/vtaContenedorEmpresaSelecciona.jsp";

                }

                //
                return "/jsp/vtaFrmSelEmpresaFinalizaVenta.jsp";
            }

            // Regresar
            if (accionContenedor.compareTo("REGRESAR") == 0) {

                //
                AgendaLogVisitaBean agendaLogVisitaBean
                                           = new AgendaLogVisitaBean();

                //
                agendaLogVisitaBean.setEstado(estadoActivo);
                agendaLogVisitaBean.setFechaVisitaStr(strFechaVisita);
                agendaLogVisitaBean.setIdUsuario(idUsuario);

                //
                FachadaAgendaLogVisitaBean fachadaAgendaLogVisitaBean
                                          = new FachadaAgendaLogVisitaBean();
                fachadaAgendaLogVisitaBean =
                        agendaLogVisitaBean.seleccionaVisitaEstadoFecha();

                //
                FachadaTerceroBean fachadaTerceroBean
                                          = new FachadaTerceroBean();

                //
                TerceroBean terceroBean = new TerceroBean();

                //
                terceroBean.setIdCliente(
                                     fachadaAgendaLogVisitaBean.getIdCliente());
                terceroBean.setIdTipoTercero(xIdTipoTerceroCliente);

                //
                fachadaTerceroBean = terceroBean.listaUnTerceroFCH();

                //
                fachadaTerceroBean.setIdTipoOrden(xIdTipoOrdenPedidoProceso);
                fachadaTerceroBean.setIdLocal(xIdLocalUsuario);

                //
                fachadaTerceroBean.setIdCliente(
                        fachadaAgendaLogVisitaBean.getIdCliente());
                fachadaTerceroBean.setIdTipoTercero(xIdTipoTerceroCliente);

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaTerceroBean", fachadaTerceroBean);
                //
                DctoOrdenBean dctoOrdenBean = new DctoOrdenBean();

                //
                dctoOrdenBean.setIdLog(fachadaAgendaLogVisitaBean.getIdLog());
                dctoOrdenBean.setIdTipoOrden(xIdTipoOrdenPedidoProceso);

                //
                FachadaDctoOrdenBean fachadaDctoOrdenBean
                                          = new FachadaDctoOrdenBean();

                //
                fachadaDctoOrdenBean      =
                        dctoOrdenBean.listaDctoOrdenIdLogIdTipoOrden();

                //
                request.setAttribute("fachadaDctoOrdenBean", fachadaDctoOrdenBean);

                // Retorna a seleccionar cliente
                if (fachadaAgendaLogVisitaBean.getIdCliente() == null) {

                    return "/jsp/vtaContenedorEmpresaSelecciona.jsp";

                }

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaAgendaLogVisitaBean",
                        fachadaAgendaLogVisitaBean);
                return "/jsp/vtaContenedorEmpresaElaboraPedido.jsp";

            }

            //
            if (accionContenedor.compareTo("CONFIRMAR") == 0) {

                // Datos recibidos cotizaci?n
                String xIdLogActual = request.getParameter("idLog");
                String fechaEntrega = request.getParameter("fechaEntrega");
                String xObservacion = request.getParameter("observacion");
                String xNombreTercero = request.getParameter("xNombreTercero");
                String xIdVendedor = request.getParameter("xIdVendedor");
 
                // Instancia el Bean de Validacion para validar los campos
                Validacion validacion = new Validacion();

                // Valida fechaEntrega
                validacion.reasignar("fechaEntrega", fechaEntrega);
                validacion.validarCampoFecha();
                if (validacion.isValido() == false) {

                    // Aqui escribe el Bean de Validacion en el Request para manejar el error
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralError.jsp";
                }

                // Valida IdLogActual
                validacion.reasignar("IdLogActual", xIdLogActual);
                validacion.validarCampoEntero();
                if (validacion.isValido() == false) {

                    // Aqui escribe el Bean de Validacion en el Request para manejar el error
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralError.jsp";
                }

                // Valida fechaEntrega
                validacion.reasignar("NOMBRE TERCERO", xNombreTercero);
                validacion.validarCampoString();

                if (validacion.isValido() == false) {
                    // Aqui escribe el Bean de Validacion en el Request para manejar el error
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralError.jsp";
                }

                //
                int estadoProgramado = 1;
                int tareaVisita = 6;   // Cotizacion
                int idTipoOrdenFactura = 9;
                int xIdTipoNegocioContado = 1;
                int xIdTipoNegocioCredito = 2;
                int xIdTipoNegocioVenta = 1;

                //
                FachadaAgendaLogVisitaBean fachadaAgendaLogVisitaBean
                                         = new FachadaAgendaLogVisitaBean();

                //
                AgendaLogVisitaBean agendaLogVisitaBean
                                         = new AgendaLogVisitaBean();

                //
                agendaLogVisitaBean.setEstado(estadoActivo);
                agendaLogVisitaBean.setFechaVisitaStr(strFechaVisita);
                agendaLogVisitaBean.setIdUsuario(idUsuario);

                //
                fachadaAgendaLogVisitaBean =
                        agendaLogVisitaBean.seleccionaVisitaEstadoFecha();


                //
                FachadaTerceroBean fachadaTerceroBean
                                           = new FachadaTerceroBean();

                //
                ColaboraTercero colaboraTercero
                                           = new ColaboraTercero();

                //
                colaboraTercero.setIdCliente(
                        fachadaAgendaLogVisitaBean.getIdCliente());
                colaboraTercero.setIdTipoTercero(xIdTipoTerceroCliente);

                //
                fachadaTerceroBean         =
                        colaboraTercero.listaTerceroFCH();

                //
                DctoOrdenBean dctoOrdenBean = new DctoOrdenBean();

                //
                dctoOrdenBean.setIdLog(xIdLogActual);
                dctoOrdenBean.setIdTipoOrden(idTipoOrdenFactura);

                // existePedido
                boolean existePedido        = dctoOrdenBean.existePedido();

                //
                if (existePedido) {

                    //
                    validacion.reasignar("PEDIDO", "");
                    validacion.setCodigoError("Error PEDIDO");
                    validacion.setDescripcionError("Pedido YA CONFIRMADO");
                    validacion.setSolucion("Iniciar NUEVO PEDIDO");

                    // Aqui escribe el Bean de Validacion en el Request para manejar el error
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralError.jsp";

                }

                //-------------------------------

                ColaboraOrdenDetalleBean colaboraOrdenDetalleBean
                                           = new ColaboraOrdenDetalleBean();

                //
                colaboraOrdenDetalleBean.setIdLocal(xIdLocalUsuario);
                colaboraOrdenDetalleBean.setIdLog(xIdLogActual);
                colaboraOrdenDetalleBean.setIdTipoOrden(xIdTipoOrdenPedidoProceso);

                //
                FachadaDctoOrdenDetalleBean fachadaDctoOrdenDetalleBean
                                           = new FachadaDctoOrdenDetalleBean();

                //
                fachadaDctoOrdenDetalleBean =
                        colaboraOrdenDetalleBean.liquidaOrdenFCH();

                //-------------------------------
                FachadaPagoMedioBean fachadaPagoMedioBean
                                           = new FachadaPagoMedioBean();

                //
                PagoMedioBean pagoMedioBean = new PagoMedioBean();

                //
                pagoMedioBean.setIdLocal(xIdLocalUsuario);
                pagoMedioBean.setIdLog(xIdLogActual);
                pagoMedioBean.setIdTipoOrden(xIdTipoOrdenPedidoProceso);

                //
                fachadaPagoMedioBean = pagoMedioBean.liquidaPagoLogFCH();

                //-------------------------------
                double xVrVentaSinDscto =
                        fachadaDctoOrdenDetalleBean.getVrVentaSinDscto();

                //
                double xVrMedio = fachadaPagoMedioBean.getVrMedio();

                //
                if ((fachadaTerceroBean.getIdFormaPago() == 0) &&
                        ((xVrVentaSinDscto > xVrMedio))) {

                    //
                    validacion.reasignar("PEDIDO A CREDITO", "");
                    validacion.setDescripcionError("CREDITO NO PERMITIDO A CLIENTE CONTADO");

                    // Aqui escribe el Bean de Validacion en el Request para manejar el error
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralError.jsp";

                }

                //
                if ((xVrMedio > 0) &&
                        ((xVrVentaSinDscto > xVrMedio))) {

                    //
                    validacion.reasignar("PEDIDO A CREDITO", "");
                    validacion.setDescripcionError("NO PERMITIDO ABONOS O PAGOS PARCIALES");

                    // Aqui escribe el Bean de Validacion en el Request para manejar el error
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralError.jsp";

                }

                //
                if ((fachadaTerceroBean.getIdFormaPago() > 0) &&
                        (xVrMedio == 0)) {

                    //
                    xIdTipoNegocioVenta = xIdTipoNegocioCredito;

                } else {

                    //
                    xIdTipoNegocioVenta = xIdTipoNegocioContado;

                }

                //
                ProcesoIp procesoIp = new ProcesoIp();

                //
                String xIpTx = procesoIp.getIpTx(request);

                //
                FachadaDctoBean fachadaDctoBean = new FachadaDctoBean();

                // confirmaPedido
                ProcesoIngreso proceso = new ProcesoIngreso();

                // confirma
                fachadaDctoBean = proceso.ingresa(xIdLocalUsuario,
                        xIdTipoOrdenPedido,
                        new Integer(xIdLogActual).intValue(),
                        xIdTipoOrdenPedidoProceso,
                        xNombreTercero.toUpperCase().trim(),
                        xIdTipoNegocioVenta,
                        xObservacion,
                        xIdVendedor,
                        xIpTx);


                // CONTADO
                if (xIdTipoNegocioVenta == xIdTipoNegocioContado) {

                    //
                    ProcesoPagoContado procesoPagoContado
                                          = new ProcesoPagoContado();

                    //
                    procesoPagoContado.confirma(fachadaDctoBean.getIdDctoStr(),
                            new Integer(xIdTipoOrdenPedidoProceso).toString(),
                            new Integer(xIdTipoOrdenPedido).toString(),
                            fachadaDctoBean.getIdLocalStr(),
                            xIdLogActual,
                            xIdVendedor,
                            fachadaDctoBean.getVrPago());

                }

                // finalizaVisita
                agendaLogVisitaBean.setIdLog(xIdLogActual);
                agendaLogVisitaBean.setIdCliente(
                        fachadaAgendaLogVisitaBean.getIdCliente());
                agendaLogVisitaBean.setIdUsuario(
                        fachadaAgendaLogVisitaBean.getIdUsuario());
                agendaLogVisitaBean.setIdLocal(fachadaDctoBean.getIdLocalStr());
                agendaLogVisitaBean.setIdTipoOrden(
                        fachadaDctoBean.getIdTipoOrdenStr());
                agendaLogVisitaBean.setIdEstadoTx(xIdEstadoTxSinTx);
                agendaLogVisitaBean.setFechaVisitaStr(strFechaVisita);
                agendaLogVisitaBean.setIdEstadoVisita(tareaVisita);
                agendaLogVisitaBean.setEstado(estadoProgramado);
                agendaLogVisitaBean.setIpTx(xIpTx);
                agendaLogVisitaBean.setFechaTx(fechaTxHM);

                //
                boolean okLog = agendaLogVisitaBean.finalizaVisita();

                //
                GeneraPDFFactura generaPDFServlet
                                                = new GeneraPDFFactura();

                //
                generaPDFServlet.setIdLocal(fachadaDctoBean.getIdLocalStr());
                generaPDFServlet.setIdOrden(fachadaDctoBean.getIdOrden());
                generaPDFServlet.setIdTipoOrden(
                                           fachadaDctoBean.getIdTipoOrdenStr());

                //
                generaPDFServlet.generaPdf(request, response);

            }
        }

        return "/jsp/empty.htm";

    }
}