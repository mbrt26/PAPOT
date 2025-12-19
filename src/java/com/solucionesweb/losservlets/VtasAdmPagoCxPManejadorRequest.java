package com.solucionesweb.losservlets;

// Importa los paquetes del lenguaje especificamente io y los
// que permiten trabajar el servlet.
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Importa la clase que contiene el Day
import com.solucionesweb.losbeans.utilidades.Day;
import java.util.Vector;

//
import com.solucionesweb.lasayudas.ProcesoIp;

//
import com.solucionesweb.losbeans.utilidades.JhDate;

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

// Importa la clase que contiene el PagoReciboBean
import com.solucionesweb.losservlets.GeneraPDFPagoPlanillaProveedor;

// Importa la clase que contiene el ColaboraDctoOrdenDetalleBean
import com.solucionesweb.losbeans.colaboraciones.ColaboraDctoBean;
import java.util.logging.Level;
import java.util.logging.Logger;

// Este servlet implementa la interface GralManejadorRequest
public class VtasAdmPagoCxPManejadorRequest
        implements GralManejadorRequest {

    // Metodo contructor por defecto, es decir, sin parametros
    public VtasAdmPagoCxPManejadorRequest() {
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
        int xIdTipoTerceroCliente = 2;
        int xIdTipoOrdenVenta = 1;
        int tareaVisita = 6;   // Cotizacion
        int estadoProgramado = 1;
        int xIdEstadoTxSinTx = 1;

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
            return "/jsp/vtaContenedorEmpresaProveedorSelecciona.jsp";

        }

        //
        FachadaTerceroBean fachadaTerceroBean = new FachadaTerceroBean();

        //
        fachadaTerceroBean.setIdCliente(fachadaAgendaLogVisitaBean.getIdCliente());
        fachadaTerceroBean.setIdTipoTercero(xIdTipoTerceroCliente);
        //---


        // Validacion de accion relacionada con el formulario requerido
        if (accionContenedor != null) {

            //
            if (accionContenedor.compareTo("Regresar") == 0) {
                return "/jsp/empty.htm";
            }

            //
            if (accionContenedor.compareTo("Salir") == 0) {
                return "/jsp/empty.htm";
            }

            //
            if (accionContenedor.compareTo("Liquidar") == 0) {

                //
                String xIdDctoArr[] = request.getParameterValues("xIdDcto");
                String xIdDctoNitCCArr[] =
                        request.getParameterValues("xIdDctoNitCC");
                String xVrPagoArr[] = request.getParameterValues("xVrPago");
                String xVrDescuentoArr[] =
                        request.getParameterValues("xVrDescuento");
                String xVrRteFuenteArr[] =
                        request.getParameterValues("xVrRteFuente");
                String xVrRteIvaArr[] =
                        request.getParameterValues("xVrRteIva");
                String xVrSaldoArr[] =
                        request.getParameterValues("xVrSaldo");
                String xFechaVencimientoArr[] =
                        request.getParameterValues("xFechaVencimiento");
                String xDiasMoraArr[] =
                        request.getParameterValues("xDiasMora");
                String xIndicadorArr[] =
                        request.getParameterValues("xIndicador");
                String xIdTipoOrdenArr[] =
                        request.getParameterValues("xIdTipoOrden");
                String xIdLocalArr[] =
                        request.getParameterValues("xIdLocal");

                //
                Validacion valida = new Validacion();

                // Validar
                for (int indice = 0; indice < xIdDctoArr.length; indice++) {


                    //
                    valida.reasignar("VrPago", xVrPagoArr[indice]);

                    //
                    valida.validarCampoDoublePositivo();

                    //isValido
                    if (!valida.isValido()) {

                        //
                        request.setAttribute("validacion", valida);
                        return "/jsp/gralError.jsp";
                    }

                    //
                    valida.reasignar("VrDescuento", xVrDescuentoArr[indice]);

                    //
                    valida.validarCampoDoublePositivo();

                    //isValido
                    if (!valida.isValido()) {

                        //
                        request.setAttribute("validacion", valida);
                        return "/jsp/gralError.jsp";
                    }

                    //
                    valida.reasignar("VrRteFuente", xVrRteFuenteArr[indice]);

                    //
                    valida.validarCampoDoublePositivo();

                    //isValido
                    if (!valida.isValido()) {

                        //
                        request.setAttribute("validacion", valida);
                        return "/jsp/gralError.jsp";
                    }

                    //
                    valida.reasignar("VrRteIva", xVrRteIvaArr[indice]);

                    //
                    valida.validarCampoDoublePositivo();

                    //isValido
                    if (!valida.isValido()) {

                        //
                        request.setAttribute("validacion", valida);
                        return "/jsp/gralError.jsp";
                    }
                }

                //
                FachadaPagoBean fachadaPagoTmpBean;

                //
                Vector vecPagos = new Vector();

                //recorre el vector
                for (int indice = 0; indice < xIdDctoArr.length; indice++) {

                    //
                    fachadaPagoTmpBean = new FachadaPagoBean();

                    //ingresa en fachadaPagoTmpBean
                    fachadaPagoTmpBean.setIdDcto(xIdDctoArr[indice]);
                    fachadaPagoTmpBean.setIdDctoNitCC(xIdDctoNitCCArr[indice]);
                    fachadaPagoTmpBean.setVrPago(xVrPagoArr[indice]);
                    fachadaPagoTmpBean.setVrDescuento(xVrDescuentoArr[indice]);
                    fachadaPagoTmpBean.setVrRteFuente(xVrRteFuenteArr[indice]);
                    fachadaPagoTmpBean.setVrRteIva(xVrRteIvaArr[indice]);
                    fachadaPagoTmpBean.setFechaVencimiento(
                            xFechaVencimientoArr[indice]);
                    fachadaPagoTmpBean.setDiasMora(xDiasMoraArr[indice]);
                    fachadaPagoTmpBean.setVrSaldo(xVrSaldoArr[indice]);
                    fachadaPagoTmpBean.setIndicador(xIndicadorArr[indice]);
                    fachadaPagoTmpBean.setIdTipoOrden(xIdTipoOrdenArr[indice]);
                    fachadaPagoTmpBean.setIdLocal(xIdLocalArr[indice]);

                    //
                    vecPagos.add(fachadaPagoTmpBean);

                }

                //
                FachadaPagoBean fachadaPagoBean = new FachadaPagoBean();

                //
                request.setAttribute("fachadaPagoBean", fachadaPagoBean);
                request.setAttribute("vecPagos", vecPagos);
                request.setAttribute("fachadaTerceroBean", fachadaTerceroBean);

                //
                return "/jsp/vtaFrmLiqPagoCxP.jsp";

            }

            //
            if (accionContenedor.compareTo("Pagar") == 0) {

                //
                String xIdDctoArr[] = request.getParameterValues("xIdDcto");
                String xIdDctoNitCCArr[] =
                        request.getParameterValues("xIdDctoNitCC");
                String xVrPagoArr[] = request.getParameterValues("xVrPago");
                String xVrDescuentoArr[] =
                        request.getParameterValues("xVrDescuento");
                String xVrRteFuenteArr[] =
                        request.getParameterValues("xVrRteFuente");
                String xVrRteIvaArr[] =
                        request.getParameterValues("xVrRteIva");
                String xVrSaldoArr[] =
                        request.getParameterValues("xVrSaldo");
                String xFechaVencimientoArr[] =
                        request.getParameterValues("xFechaVencimiento");
                String xDiasMoraArr[] =
                        request.getParameterValues("xDiasMora");
                String xIndicadorArr[] =
                        request.getParameterValues("xIndicador");
                String xIdTipoOrdenArr[] =
                        request.getParameterValues("xIdTipoOrden");
                String xIdLocalArr[] =
                        request.getParameterValues("xIdLocal");


                //
                Validacion valida = new Validacion();

                // Validar
                for (int indice = 0; indice < xIdDctoArr.length; indice++) {


                    //
                    valida.reasignar("VrPago", xVrPagoArr[indice]);

                    //
                    valida.validarCampoDoublePositivo();

                    //isValido
                    if (!valida.isValido()) {

                        //
                        request.setAttribute("validacion", valida);
                        return "/jsp/gralError.jsp";
                    }

                    //
                    valida.reasignar("VrDescuento", xVrDescuentoArr[indice]);

                    //
                    valida.validarCampoDoublePositivo();

                    //isValido
                    if (!valida.isValido()) {

                        //
                        request.setAttribute("validacion", valida);
                        return "/jsp/gralError.jsp";
                    }

                    //
                    valida.reasignar("VrRteFuente", xVrRteFuenteArr[indice]);

                    //
                    valida.validarCampoDoublePositivo();

                    //isValido
                    if (!valida.isValido()) {

                        //
                        request.setAttribute("validacion", valida);
                        return "/jsp/gralError.jsp";
                    }

                    //
                    valida.reasignar("VrRteIva", xVrRteIvaArr[indice]);

                    //
                    valida.validarCampoDoublePositivo();

                    //isValido
                    if (!valida.isValido()) {

                        //
                        request.setAttribute("validacion", valida);
                        return "/jsp/gralError.jsp";
                    }
                }

                // Importa la clase que contiene el DctoBean
                DctoBean dctoBean = new DctoBean();

                //
                FachadaDctoBean fachadaDctoBean = new FachadaDctoBean();

                // PagoBean
                PagoBean pagoBean = new PagoBean();

                //
                pagoBean.setIdLocal(xIdLocalUsuario);
                pagoBean.setIdTipoOrden(xIdTipoOrdenVenta);

                //
                int idMaximaPlanilla = pagoBean.maximaPlanilla() + 1;

                //
                boolean okPago = false;

                //recorre el vector
                for (int indice = 0; indice < xIdDctoArr.length; indice++) {

                    //  salta si no existe pago (continue)
                    if (!((new Double(xVrPagoArr[indice]).doubleValue() != 0) ||
                            (new Double(xVrRteFuenteArr[indice]).doubleValue() != 0) ||
                            (new Double(xVrDescuentoArr[indice]).doubleValue() != 0) ||
                            (new Double(xVrRteIvaArr[indice]).doubleValue() != 0))) {
                        continue;
                    }

                    //
                    dctoBean.setIdDcto(xIdDctoArr[indice]);
                    dctoBean.setIndicador(xIndicadorArr[indice]);
                    dctoBean.setIdTipoOrden(xIdTipoOrdenArr[indice]);
                    dctoBean.setIdLocal(xIdLocalArr[indice]);

                    //
                    fachadaDctoBean = dctoBean.listaUnDcto();

                    double xVSaldoDcto =
                            fachadaDctoBean.calculaSaldo();

                    /*
                    if ((xVSaldoDcto <
                    (new Double(xVrPagoArr[indice]).doubleValue()      +
                    new Double(xVrRteFuenteArr[indice]).doubleValue() +
                    new Double(xVrDescuentoArr[indice]).doubleValue() +
                    new Double(xVrRteIvaArr[indice]).doubleValue()) )) {

                    //
                    valida.reasignar("VALOR PAGO", "");
                    valida.setDescripcionError(
                    "ERROR, VR.PAGO MAYOR QUE SALDO FACTURA");

                    //
                    request.setAttribute("validacion",valida);
                    return "/jsp/gralError.jsp";
                    }*/

                    //
                    dctoBean.setIdDcto(xIdDctoArr[indice]);
                    dctoBean.setIndicador(xIndicadorArr[indice]);
                    dctoBean.setIdTipoOrden(xIdTipoOrdenArr[indice]);
                    dctoBean.setIdLocal(xIdLocalArr[indice]);
                    dctoBean.setVrPago(xVrPagoArr[indice]);
                    dctoBean.setVrRteFuente(xVrRteFuenteArr[indice]);
                    dctoBean.setVrDescuento(xVrDescuentoArr[indice]);
                    dctoBean.setVrRteIva(xVrRteIvaArr[indice]);

                    //
                    okPago = dctoBean.actualizaPago();

                    //--- calculaSaldo
                    dctoBean.setIdDcto(xIdDctoArr[indice]);
                    dctoBean.setIndicador(xIndicadorArr[indice]);
                    dctoBean.setIdTipoOrden(xIdTipoOrdenArr[indice]);
                    dctoBean.setIdLocal(xIdLocalArr[indice]);

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
                    pagoBean.setIdLocal(fachadaDctoBean.getIdLocal());
                    pagoBean.setIndicador(fachadaDctoBean.getIndicador());
                    pagoBean.setIdTipoOrden(fachadaDctoBean.getIdTipoOrden());

                    //
                    int xIdReciboMAX =
                            pagoBean.maximoReciboIdLocalxIndicador() + 1;

                    //
                    pagoBean.setIdLocal(fachadaDctoBean.getIdLocal());
                    pagoBean.setIdTipoOrden(fachadaDctoBean.getIdTipoOrden());
                    pagoBean.setIdRecibo(xIdReciboMAX);
                    pagoBean.setIndicador(fachadaDctoBean.getIndicador());
                    pagoBean.setFechaPago(strFechaVisita);
                    pagoBean.setVrPago(xVrPagoArr[indice]);
                    pagoBean.setNitCC(fachadaDctoBean.getIdCliente());
                    pagoBean.setEstado(estadoActivo);
                    pagoBean.setIdUsuario(idUsuario);
                    pagoBean.setVrRteFuente(xVrRteFuenteArr[indice]);
                    pagoBean.setVrDescuento(xVrDescuentoArr[indice]);
                    pagoBean.setVrRteIva(xVrRteIvaArr[indice]);
                    pagoBean.setIdDcto(xIdDctoArr[indice]);
                    pagoBean.setIdDctoNitCC(fachadaDctoBean.getIdDctoNitCC());
                    pagoBean.setIdPlanilla(idMaximaPlanilla);
                    pagoBean.setVrSaldo(xVSaldoDctoActualizado);
                    pagoBean.setIdLog(xIdLogActual);
                    pagoBean.setIdVendedor(idUsuario);

                    // ingresaPago
                    boolean okIngresaPago = pagoBean.ingresaPago();

                }

                // Si pago (true)
                if (okPago) {

                    // finalizaVisita
                    agendaLogVisitaBean.setIdLog(xIdLogActual);
                    agendaLogVisitaBean.setIdCliente(
                            fachadaAgendaLogVisitaBean.getIdCliente());
                    agendaLogVisitaBean.setIdUsuario(
                            fachadaAgendaLogVisitaBean.getIdUsuario());
                    agendaLogVisitaBean.setIdTipoOrden(
                            fachadaDctoBean.getIdTipoOrden());
                    agendaLogVisitaBean.setIdEstadoTx(xIdEstadoTxSinTx);
                    agendaLogVisitaBean.setIdLocal(fachadaDctoBean.getIdLocal());

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
                    GeneraPDFPagoPlanillaProveedor generaPDF =
                            new GeneraPDFPagoPlanillaProveedor();

                    //
                    generaPDF.setIdLocal(xIdLocalUsuario);
                    generaPDF.setIdTipoOrden(xIdTipoOrdenVenta);
                    generaPDF.setIdPlanilla(idMaximaPlanilla);
                    generaPDF.setTerceroReporte("PROVEEDOR   ");
                    generaPDF.setTituloReporte("PLANILLA DE PAGO # ");

                    //
                    generaPDF.generaPdf(request, response);

                }


            }
        }

        return "/jsp/empty.htm";

    }
}