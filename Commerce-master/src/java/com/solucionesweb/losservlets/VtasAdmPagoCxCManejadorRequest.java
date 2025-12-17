package com.solucionesweb.losservlets;

// Importa los paquetes del lenguaje especificamente io y los
// que permiten trabajar el servlet.
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//
import com.solucionesweb.lasayudas.ProcesoIp;

//
import com.solucionesweb.losbeans.utilidades.JhDate;

// Importa la clase que contiene el Day
import com.solucionesweb.losbeans.utilidades.Day;

//
import java.util.Vector;

//
import com.solucionesweb.losbeans.fachada.FachadaPagoBean;

//
import javax.servlet.http.HttpSession;

// Importa la clase que contiene el ValidacionUsuarioBean
import com.solucionesweb.losbeans.negocio.UsuarioBean;

// Importa la clase que contiene el AgendaControlBean
import com.solucionesweb.losbeans.negocio.AgendaLogVisitaBean;

// Importa la clase que contiene el AgendaControlBean
import com.solucionesweb.losbeans.fachada.FachadaAgendaLogVisitaBean;

// Importa la clase que contiene el FachadaTerceroBean
import com.solucionesweb.losbeans.fachada.FachadaTerceroBean;

// Importa la clase que contiene el Validacion
import com.solucionesweb.losbeans.utilidades.Validacion;

// Importa la clase que contiene el DctoBean
import com.solucionesweb.losbeans.negocio.DctoBean;

import com.solucionesweb.losbeans.fachada.FachadaDctoBean;

// Importa la clase que contiene el PagoBean
import com.solucionesweb.losbeans.negocio.PagoBean;

// Importa la clase que contiene el PagoMedioBean
import com.solucionesweb.losbeans.negocio.PagoMedioBean;


// Importa la clase que contiene el GeneraPDFPagoPlanilla
import com.solucionesweb.losservlets.GeneraPDFPagoPlanilla;

// Importa la clase que contiene el ColaboraDctoOrdenDetalleBean
import com.solucionesweb.losbeans.colaboraciones.ColaboraDctoBean;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

// Este servlet implementa la interface GralManejadorRequest
public class VtasAdmPagoCxCManejadorRequest
        implements GralManejadorRequest {

    // Metodo contructor por defecto, es decir, sin parametros
    public VtasAdmPagoCxCManejadorRequest() {
    }

    /**
     * Retorna la URL de la pagina que deber? ser entregada como respuesta
     * (normalmente un pagina jsp).
     */
    public String generaPdf(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //
        String accionContenedor = request.getParameter("accionContenedor");

        // ---
        int xIdTipoTerceroCliente = 1;
        int xIdTipoOrdenVenta = 9;
        int tareaVisita = 6;   // Cotizacion
        int estadoProgramado = 1;
        int xIdEstadoTxSinTx = 1;

        //
        int xIdTipoOrdenPagoProceso = 59;

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

        //
        int estadoActivo = 9;
        Day day = new Day();
        String strFechaVisita = day.getFechaFormateada();

        //
        HttpSession sesion = request.getSession();

        //
        UsuarioBean usuarioBean =
                (UsuarioBean) sesion.getAttribute("usuarioBean");

        //
        String idUsuario = usuarioBean.getIdUsuarioStr();
        int xIdLocalUsuario = usuarioBean.getIdLocalUsuario();

        //
        AgendaLogVisitaBean agendaLogVisitaBean = new AgendaLogVisitaBean();
        agendaLogVisitaBean.setEstado(estadoActivo);
        agendaLogVisitaBean.setFechaVisitaStr(strFechaVisita);
        agendaLogVisitaBean.setIdUsuario(idUsuario);

        //
        FachadaAgendaLogVisitaBean fachadaAgendaLogVisitaBean = new FachadaAgendaLogVisitaBean();

        //
        fachadaAgendaLogVisitaBean =
                agendaLogVisitaBean.seleccionaVisitaEstadoFecha();

        //
        int xIdLogActual = fachadaAgendaLogVisitaBean.getIdLog();

        //
        fachadaAgendaLogVisitaBean.setIdUsuario(idUsuario);

        // Aqui escribe el Bean de Validacion en el Request para manejar el error
        request.setAttribute("fachadaAgendaLogVisitaBean",
                fachadaAgendaLogVisitaBean);

        // Retorna a seleccionar cliente
        if (fachadaAgendaLogVisitaBean.getIdCliente() == null) {

            //
            FachadaTerceroBean fachadaTerceroBean = new FachadaTerceroBean();

            // Aqui escribe el Bean de Validacion en el Request para manejar el error
            request.setAttribute("fachadaTerceroBean", fachadaTerceroBean);

            //
            return "/jsp/vtaContenedorEmpresaSelecciona.jsp";

        }

        //
        FachadaTerceroBean fachadaTerceroBean = new FachadaTerceroBean();

        //
        fachadaTerceroBean.setIdCliente(fachadaAgendaLogVisitaBean.getIdCliente());
        fachadaTerceroBean.setIdTipoTercero(xIdTipoTerceroCliente);

        // Validacion de accion relacionada con el formulario requerido
        if (accionContenedor != null) {

            //
            if (accionContenedor.compareTo("Salir") == 0) {
                return "/jsp/empty.htm";
            }

            if (accionContenedor.compareTo("Guardar") == 0) {

                //
                String xIdTipoOrden = request.getParameter("xIdTipoOrden");
                String xIndicador = request.getParameter("xIndicador");
                String xIdDcto = request.getParameter("xIdDcto");
                String xIdLocal = request.getParameter("xIdLocal");

                //
                String xVrPago = request.getParameter("xVrPago");
                String xVrDescuento = request.getParameter("xVrDescuento");
                String xVrRteFuente = request.getParameter("xVrRteFuente");
                String xVrRteIva = request.getParameter("xVrRteIva");
                String xVrRteIca = request.getParameter("xVrRteIca");

                //
                Validacion valida = new Validacion();

                //
                valida.reasignar("VrPago", xVrPago);

                //
                valida.validarCampoDoublePositivo();

                //isValido
                if (!valida.isValido()) {

                    //
                    request.setAttribute("validacion", valida);
                    return "/jsp/gralError.jsp";
                }

                //
                valida.reasignar("VrDescuento", xVrDescuento);

                //
                valida.validarCampoDoublePositivo();

                //isValido
                if (!valida.isValido()) {

                    //
                    request.setAttribute("validacion", valida);
                    return "/jsp/gralError.jsp";
                }

                //
                valida.reasignar("VrRteFuente", xVrRteFuente);

                //
                valida.validarCampoDoublePositivo();

                //isValido
                if (!valida.isValido()) {

                    //
                    request.setAttribute("validacion", valida);
                    return "/jsp/gralError.jsp";
                }

                //
                valida.reasignar("VrRteIva", xVrRteIva);

                //
                valida.validarCampoDoublePositivo();

                //isValido
                if (!valida.isValido()) {

                    //
                    request.setAttribute("validacion", valida);
                    return "/jsp/gralError.jsp";
                }

                //
                valida.reasignar("VrRteIca", xVrRteIca);

                //
                valida.validarCampoDoublePositivo();

                //isValido
                if (!valida.isValido()) {

                    //
                    request.setAttribute("validacion", valida);
                    return "/jsp/gralError.jsp";
                }

                // Importa la clase que contiene el DctoBean
                DctoBean dctoBean = new DctoBean();

                //
                FachadaDctoBean fachadaDctoBean = new FachadaDctoBean();

                //--- listaUnDcto
                dctoBean.setIdDcto(xIdDcto);
                dctoBean.setIndicador(xIndicador);
                dctoBean.setIdTipoOrden(xIdTipoOrden);
                dctoBean.setIdLocal(xIdLocal);

                //
                fachadaDctoBean = dctoBean.listaUnDcto();


                //
                ColaboraDctoBean colaboraDctoBean = new ColaboraDctoBean();

                colaboraDctoBean.setIdLocal(fachadaDctoBean.getIdLocal());
                ;
                colaboraDctoBean.setIdCliente(fachadaDctoBean.getIdCliente());
                colaboraDctoBean.setIdTipoOrden(fachadaDctoBean.getIdTipoOrden());
                colaboraDctoBean.setIdDcto(fachadaDctoBean.getIdDcto());

                //
                FachadaDctoBean fachadaDctoPagoBean = new FachadaDctoBean();

                //
                fachadaDctoPagoBean =
                        colaboraDctoBean.listaCuentaDetalladoClienteFCH();

                //
                double xVSaldoDctoActualizado =
                        fachadaDctoPagoBean.getVrSaldo();

                //
                if (xVSaldoDctoActualizado <
                        (new Double(xVrPago).doubleValue() +
                        new Double(xVrDescuento).doubleValue() +
                        new Double(xVrRteFuente).doubleValue() +
                        new Double(xVrRteIva).doubleValue() +
                        new Double(xVrRteIca).doubleValue())) {

                    //
                    valida.reasignar("EXCEDE VALOR A PAGAR", "");

                    //
                    request.setAttribute("validacion", valida);
                    return "/jsp/gralError.jsp";

                }

                // PagoBean
                PagoBean pagoBean = new PagoBean();

                //
                pagoBean.setIdLocal(xIdLocalUsuario);
                pagoBean.setIdTipoOrden(xIdTipoOrdenPagoProceso);

                //
                int idMaximaPlanilla = pagoBean.maximaPlanilla() + 1;

                //--- listaUnDcto
                dctoBean.setIdDcto(xIdDcto);
                dctoBean.setIndicador(xIndicador);
                dctoBean.setIdTipoOrden(xIdTipoOrden);
                dctoBean.setIdLocal(xIdLocal);

                //
                fachadaDctoBean = dctoBean.listaUnDcto();

                //
                PagoMedioBean pagoMedioBean = new PagoMedioBean();

                //
                pagoMedioBean.setIdLocal(xIdLocal);
                pagoMedioBean.setIdTipoOrden(xIdTipoOrden);
                pagoMedioBean.setIndicador(xIndicador);
                pagoMedioBean.setIdLog(xIdLogActual);

                //
                pagoMedioBean.retiraPagoDctoTemporal(
                        new Integer(xIdDcto).intValue());

                //
                pagoBean.setIdLocal(fachadaDctoBean.getIdLocal());
                pagoBean.setIndicador(fachadaDctoBean.getIndicador());
                pagoBean.setIdTipoOrden(xIdTipoOrdenPagoProceso);
                pagoBean.setIdDcto(fachadaDctoBean.getIdDcto());
                pagoBean.setIdLog(xIdLogActual);

                //
                pagoBean.retiraPagoDctoTemporal();

                //
                int xIdReciboMAX =
                        pagoBean.maximoReciboIdLocalxIndicador() + 1;

                //
                pagoBean.setIdLocal(fachadaDctoBean.getIdLocal());
                pagoBean.setIdTipoOrden(xIdTipoOrdenPagoProceso);
                pagoBean.setIdRecibo(xIdReciboMAX);
                pagoBean.setIndicador(fachadaDctoBean.getIndicador());
                pagoBean.setFechaPago(strFechaVisita);
                pagoBean.setVrPago(xVrPago);
                pagoBean.setNitCC(fachadaDctoBean.getIdCliente());
                pagoBean.setEstado(estadoActivo);
                pagoBean.setIdUsuario(idUsuario);
                pagoBean.setVrRteFuente(xVrRteFuente);
                pagoBean.setVrDescuento(xVrDescuento);
                pagoBean.setVrRteIva(xVrRteIva);
                pagoBean.setVrRteIca(xVrRteIca);
                pagoBean.setIdDcto(xIdDcto);
                pagoBean.setIdDctoNitCC(fachadaDctoBean.getIdCliente());
                pagoBean.setIdPlanilla(idMaximaPlanilla);
                pagoBean.setVrSaldo(xVSaldoDctoActualizado);
                pagoBean.setIdLog(xIdLogActual);
                pagoBean.setIdReciboCruce(0);

                // ingresaPago
                boolean okIngresaPago = pagoBean.ingresaPago();

                //
                int xEstadoOk = 1;
                int xIdBancoOk = 0;
                String xIdDctoMedioOk = "";
                int xIdMedioEfectivo = 1;

                //
                pagoMedioBean.setIdLocal(xIdLocal);
                pagoMedioBean.setIdTipoOrden(xIdTipoOrdenPagoProceso);
                pagoMedioBean.setIdRecibo(xIdReciboMAX);
                pagoMedioBean.setIndicador(fachadaDctoBean.getIndicador());
                pagoMedioBean.setVrMedio(xVrPago);
                pagoMedioBean.setEstado(xEstadoOk);
                pagoMedioBean.setIdBanco(xIdBancoOk);
                pagoMedioBean.setIdDctoMedio(xIdDctoMedioOk);
                pagoMedioBean.setFechaCobro(strFechaVisita);
                pagoMedioBean.setIdMedio(xIdMedioEfectivo);

                //
                pagoMedioBean.ingresar();

                //
                fachadaTerceroBean.setIdTipoOrden(xIdTipoOrden);
                fachadaTerceroBean.setIdCliente(
                        fachadaAgendaLogVisitaBean.getIdCliente());
                fachadaTerceroBean.setIdLocal(xIdLocalUsuario);

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaTerceroBean", fachadaTerceroBean);

                //
                return "/jsp/vtaContenedorPagoCxC.jsp";

            }

            if (accionContenedor.compareTo("PagoParcial") == 0) {

                //
                String xIdTipoOrden = request.getParameter("xIdTipoOrden");
                String xIndicador = request.getParameter("xIndicador");
                String xIdDcto = request.getParameter("xIdDcto");
                String xIdLocal = request.getParameter("xIdLocal");

                //
                fachadaTerceroBean.setIdTipoOrden(xIdTipoOrden);
                fachadaTerceroBean.setIdCliente(
                        fachadaAgendaLogVisitaBean.getIdCliente());
                fachadaTerceroBean.setIdLocal(xIdLocalUsuario);

                //
                FachadaDctoBean fachadaDctoBean = new FachadaDctoBean();

                // Importa la clase que contiene el DctoBean
                DctoBean dctoBean = new DctoBean();

                dctoBean.setIdDcto(xIdDcto);
                dctoBean.setIndicador(xIndicador);
                dctoBean.setIdTipoOrden(xIdTipoOrden);
                dctoBean.setIdLocal(xIdLocal);

                //
                fachadaDctoBean = dctoBean.listaUnDcto();

                //
                ColaboraDctoBean colaboraDctoBean = new ColaboraDctoBean();

                //
                colaboraDctoBean.setIdLocal(fachadaDctoBean.getIdLocal());
                ;
                colaboraDctoBean.setIdCliente(fachadaDctoBean.getIdCliente());
                colaboraDctoBean.setIdTipoOrden(fachadaDctoBean.getIdTipoOrden());
                colaboraDctoBean.setIdDcto(fachadaDctoBean.getIdDcto());

                //
                FachadaDctoBean fachadaDctoPagoBean = new FachadaDctoBean();

                //
                fachadaDctoPagoBean =
                        colaboraDctoBean.listaCuentaDetalladoClienteFCH();

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaTerceroBean", fachadaTerceroBean);
                request.setAttribute("fachadaDctoBean", fachadaDctoBean);
                request.setAttribute("fachadaDctoPagoBean", fachadaDctoPagoBean);

                //
                return "/jsp/vtaFrmLiqPagoCxCParcial.jsp";

            }

            //
            if (accionContenedor.compareTo("RetiraPagoTotal") == 0) {

                //
                String xIdTipoOrden = request.getParameter("xIdTipoOrden");
                String xIndicador = request.getParameter("xIndicador");
                String xIdDcto = request.getParameter("xIdDcto");
                String xIdLocal = request.getParameter("xIdLocal");

                //
                PagoMedioBean pagoMedioBean = new PagoMedioBean();

                //
                pagoMedioBean.setIdLocal(xIdLocal);
                pagoMedioBean.setIdTipoOrden(xIdTipoOrden);
                pagoMedioBean.setIndicador(xIndicador);
                pagoMedioBean.setIdLog(xIdLogActual);

                //
                pagoMedioBean.retiraPagoDctoTemporal(
                        new Integer(xIdDcto).intValue());

                //
                PagoBean pagoBean = new PagoBean();

                //
                pagoBean.setIdLocal(xIdLocal);
                pagoBean.setIndicador(xIndicador);
                pagoBean.setIdTipoOrden(xIdTipoOrdenPagoProceso);
                pagoBean.setIdDcto(xIdDcto);

                //
                pagoBean.retiraPagoDctoTemporal();

                //
                pagoMedioBean.setIdLocal(xIdLocal);
                pagoMedioBean.setIndicador(xIndicador);
                pagoMedioBean.setIdTipoOrden(xIdTipoOrdenPagoProceso);
                pagoMedioBean.setIdLog(xIdLogActual);

                //
                pagoMedioBean.retiraPagoDctoTemporal(
                        new Integer(xIdDcto).intValue());

                //
                fachadaTerceroBean.setIdTipoOrden(xIdTipoOrden);
                fachadaTerceroBean.setIdCliente(
                        fachadaAgendaLogVisitaBean.getIdCliente());
                fachadaTerceroBean.setIdLocal(xIdLocalUsuario);

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaTerceroBean", fachadaTerceroBean);

                //
                return "/jsp/vtaContenedorPagoCxC.jsp";

            }


            //
            if (accionContenedor.compareTo("PagoTotal") == 0) {

                //
                double xCero = 0.0;

                //
                String xIdTipoOrden = request.getParameter("xIdTipoOrden");
                String xIndicador = request.getParameter("xIndicador");
                String xIdDcto = request.getParameter("xIdDcto");
                String xIdLocal = request.getParameter("xIdLocal");

                // Importa la clase que contiene el DctoBean
                DctoBean dctoBean = new DctoBean();

                //
                FachadaDctoBean fachadaDctoBean = new FachadaDctoBean();


                // PagoBean
                PagoBean pagoBean = new PagoBean();

                //
                pagoBean.setIdLocal(xIdLocalUsuario);
                pagoBean.setIdTipoOrden(xIdTipoOrdenPagoProceso);

                //
                int idMaximaPlanilla = pagoBean.maximaPlanilla() + 1;

                //--- listaUnDcto
                dctoBean.setIdDcto(xIdDcto);
                dctoBean.setIndicador(xIndicador);
                dctoBean.setIdTipoOrden(xIdTipoOrden);
                dctoBean.setIdLocal(xIdLocal);

                //
                fachadaDctoBean = dctoBean.listaUnDcto();

                //
                ColaboraDctoBean colaboraDctoBean = new ColaboraDctoBean();

                //
                colaboraDctoBean.setIdLocal(fachadaDctoBean.getIdLocal());
                ;
                colaboraDctoBean.setIdCliente(fachadaDctoBean.getIdCliente());
                colaboraDctoBean.setIdTipoOrden(fachadaDctoBean.getIdTipoOrden());
                colaboraDctoBean.setIdDcto(fachadaDctoBean.getIdDcto());

                //
                FachadaDctoBean fachadaDctoPagoBean = new FachadaDctoBean();

                //
                fachadaDctoPagoBean =
                        colaboraDctoBean.listaCuentaDetalladoClienteFCH();

                //
                double xVSaldoDctoActualizado = fachadaDctoPagoBean.getVrSaldo();

                //
                pagoBean.setIdLocal(fachadaDctoBean.getIdLocal());
                pagoBean.setIndicador(fachadaDctoBean.getIndicador());
                pagoBean.setIdTipoOrden(xIdTipoOrdenPagoProceso);
                pagoBean.setIdDcto(fachadaDctoBean.getIdDcto());
                pagoBean.setIdLog(xIdLogActual);

                //
                pagoBean.retiraPagoDctoTemporal();

                //
                int xIdReciboMAX =
                        pagoBean.maximoReciboIdLocalxIndicador() + 1;

                //
                pagoBean.setIdLocal(fachadaDctoBean.getIdLocal());
                pagoBean.setIdTipoOrden(xIdTipoOrdenPagoProceso);
                pagoBean.setIdRecibo(xIdReciboMAX);
                pagoBean.setIndicador(fachadaDctoBean.getIndicador());
                pagoBean.setFechaPago(strFechaVisita);
                pagoBean.setVrPago(xVSaldoDctoActualizado);
                pagoBean.setNitCC(fachadaDctoBean.getIdCliente());
                pagoBean.setEstado(estadoActivo);
                pagoBean.setIdUsuario(idUsuario);
                pagoBean.setVrRteFuente(xCero);
                pagoBean.setVrDescuento(xCero);
                pagoBean.setVrRteIva(xCero);
                pagoBean.setIdDcto(xIdDcto);
                pagoBean.setIdDctoNitCC(fachadaDctoBean.getIdCliente());
                pagoBean.setIdPlanilla(idMaximaPlanilla);
                pagoBean.setVrSaldo(xVSaldoDctoActualizado);
                pagoBean.setIdLog(xIdLogActual);

                // ingresaPago
                boolean okIngresaPago = pagoBean.ingresaPago();

                //
                PagoMedioBean pagoMedioBean = new PagoMedioBean();

                //
                pagoMedioBean.setIdLocal(xIdLocal);
                pagoMedioBean.setIdTipoOrden(xIdTipoOrden);
                pagoMedioBean.setIndicador(xIndicador);
                pagoMedioBean.setIdLog(xIdLogActual);

                //
                pagoMedioBean.retiraPagoDctoTemporal(
                        new Integer(xIdDcto).intValue());

                //
                int xEstadoOk = 1;
                int xIdBancoOk = 0;
                String xIdDctoMedioOk = "";
                int xIdMedioEfectivo = 1;

                //
                pagoMedioBean.setIdLocal(xIdLocal);
                pagoMedioBean.setIdTipoOrden(xIdTipoOrdenPagoProceso);
                pagoMedioBean.setIdRecibo(xIdReciboMAX);
                pagoMedioBean.setIndicador(fachadaDctoBean.getIndicador());
                pagoMedioBean.setVrMedio(xVSaldoDctoActualizado);
                pagoMedioBean.setEstado(xEstadoOk);
                pagoMedioBean.setIdBanco(xIdBancoOk);
                pagoMedioBean.setIdDctoMedio(xIdDctoMedioOk);
                pagoMedioBean.setFechaCobro(strFechaVisita);
                pagoMedioBean.setIdMedio(xIdMedioEfectivo);

                //
                pagoMedioBean.ingresar();

                //
                fachadaTerceroBean.setIdTipoOrden(xIdTipoOrden);
                fachadaTerceroBean.setIdCliente(
                        fachadaAgendaLogVisitaBean.getIdCliente());
                fachadaTerceroBean.setIdLocal(xIdLocalUsuario);

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaTerceroBean", fachadaTerceroBean);

                //
                return "/jsp/vtaContenedorPagoCxC.jsp";
            }

            //
            if (accionContenedor.compareTo("Pagar") == 0) {

                //
                String xIdLocal = request.getParameter("xIdLocal");

                // Importa la clase que contiene el DctoBean
                DctoBean dctoBean = new DctoBean();

                // Importa la clase que contiene el DctoBean
                PagoBean pagoBean = new PagoBean();

                //
                pagoBean.setIdLocal(xIdLocal);
                pagoBean.setIdTipoOrden(xIdTipoOrdenVenta);

                //
                int idMaximaPlanilla = pagoBean.maximaPlanilla() + 1;

                //
                pagoBean.setIdLocal(xIdLocal);
                pagoBean.setIdTipoOrden(xIdTipoOrdenPagoProceso);
                pagoBean.setIdLog(xIdLogActual);

                //
                Vector vecPagoTmp = pagoBean.listaPagoProceso();

                //
                Iterator iteratorBean = vecPagoTmp.iterator();

                //
                FachadaPagoBean fachadaPagoBean;

                boolean okPago = false;

                //
                while (iteratorBean.hasNext()) {

                    //
                    fachadaPagoBean = (FachadaPagoBean) iteratorBean.next();

                    //
                    dctoBean.setIdDcto(fachadaPagoBean.getIdDcto());
                    dctoBean.setIndicador(fachadaPagoBean.getIndicador());
                    dctoBean.setIdTipoOrden(xIdTipoOrdenVenta);
                    dctoBean.setIdLocal(xIdLocal);
                    dctoBean.setIdDcto(fachadaPagoBean.getIdDcto());
                    dctoBean.setVrPago(fachadaPagoBean.getVrPago());
                    dctoBean.setVrRteFuente(fachadaPagoBean.getVrRteFuente());
                    dctoBean.setVrDsctoFcro(fachadaPagoBean.getVrDescuento());
                    dctoBean.setVrRteIva(fachadaPagoBean.getVrRteIva());
                    dctoBean.setVrRteIca(fachadaPagoBean.getVrRteIca());

                    //
                    okPago = dctoBean.actualizaPago();

                    //
                    pagoBean.setIdTipoOrden(xIdTipoOrdenVenta);
                    pagoBean.setIdLocal(xIdLocal);
                    pagoBean.setIndicador(fachadaPagoBean.getIndicador());

                    //
                    int xIdReciboMAX =
                            pagoBean.maximoReciboIdLocalxIndicador() + 1;

                    //
                    ColaboraDctoBean colaboraDctoBean = new ColaboraDctoBean();

                    colaboraDctoBean.setIdLocal(fachadaPagoBean.getIdLocal());
                    ;
                    colaboraDctoBean.setIdCliente(fachadaPagoBean.getNitCC());
                    colaboraDctoBean.setIdTipoOrden(xIdTipoOrdenVenta);
                    colaboraDctoBean.setIdDcto(fachadaPagoBean.getIdDcto());

                    //
                    FachadaDctoBean fachadaDctoPagoBean = new FachadaDctoBean();

                    //
                    fachadaDctoPagoBean =
                            colaboraDctoBean.listaCuentaDetalladoClienteFCH();

                    //
                    double xVSaldoDctoActualizado =
                            fachadaDctoPagoBean.getVrSaldo();

                    //
                    PagoMedioBean pagoMedioBean = new PagoMedioBean();

                    //
                    pagoMedioBean.setIdLocal(xIdLocal);
                    pagoMedioBean.setIdTipoOrden(xIdTipoOrdenVenta);
                    pagoMedioBean.setIdRecibo(xIdReciboMAX);
                    pagoMedioBean.setIndicador(fachadaPagoBean.getIndicador());

                    //
                    pagoMedioBean.actualizarPagoTemporal(xIdTipoOrdenPagoProceso,
                            fachadaPagoBean.getIdDcto());

                    //
                    pagoBean.setIdPlanilla(idMaximaPlanilla);
                    pagoBean.setIdLog(xIdLogActual);
                    pagoBean.setIdDcto(fachadaPagoBean.getIdDcto());
                    pagoBean.setIdTipoOrden(xIdTipoOrdenVenta);
                    pagoBean.setIdLocal(xIdLocal);
                    pagoBean.setIndicador(fachadaPagoBean.getIndicador());
                    pagoBean.setIdRecibo(xIdReciboMAX);
                    pagoBean.setVrSaldo(xVSaldoDctoActualizado);
                    pagoBean.setIdVendedor(idUsuario);

                    //
                    pagoBean.actualizarPagoTemporal(xIdTipoOrdenPagoProceso);

                }


                // Si pago (true)
                if (okPago) {

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

                    agendaLogVisitaBean.setIpTx(ipTx);
                    agendaLogVisitaBean.setFechaTx(fechaTxHM);

                    //
                    boolean okLog = agendaLogVisitaBean.finalizaVisita();

                    //
                    GeneraPDFPagoPlanilla generaPDF =
                            new GeneraPDFPagoPlanilla();

                    //
                    generaPDF.setIdLocal(xIdLocalUsuario);
                    generaPDF.setIdTipoOrden(xIdTipoOrdenVenta);
                    generaPDF.setIdPlanilla(idMaximaPlanilla);
                    generaPDF.setTerceroReporte("CLIENTE   ");
                    generaPDF.setTituloReporte("PLANILLA DE PAGO # ");

                    //
                    generaPDF.generaPdf(request, response);

                }
            }
        }

        return "/jsp/empty.htm";

    }
}