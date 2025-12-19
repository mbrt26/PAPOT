package com.solucionesweb.losservlets;

// Importa los paquetes del lenguaje especificamente io y los
// que permiten trabajar el servlet.
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.Iterator;
import java.util.Vector;

//
import com.solucionesweb.lasayudas.ProcesoIp;

//
import com.solucionesweb.losbeans.utilidades.JhDate;

// Importa la clase que contiene el Day
import com.solucionesweb.losbeans.utilidades.Day;

//
import com.solucionesweb.losbeans.fachada.FachadaUsuarioBean;

//
import com.solucionesweb.losbeans.fachada.FachadaTerceroBean;

import com.solucionesweb.losbeans.fachada.FachadaDctoBean;

import com.solucionesweb.losbeans.negocio.DctoBean;

import com.solucionesweb.losbeans.negocio.TerceroBean;

import com.solucionesweb.losbeans.negocio.PagoBean;

import com.solucionesweb.losbeans.negocio.PagoMedioBean;

// Importa la clase que contiene el ValidacionUsuarioBean
import com.solucionesweb.losbeans.negocio.UsuarioBean;

// Importa la clase que contiene el AgendaControlBean
import com.solucionesweb.losbeans.negocio.AgendaLogVisitaBean;

// Importa la clase que contiene el AgendaControlBean
import com.solucionesweb.losbeans.fachada.FachadaAgendaLogVisitaBean;

// Importa la clase que contiene el Validacion
import com.solucionesweb.losbeans.utilidades.Validacion;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaPagoBean;

import com.solucionesweb.losbeans.colaboraciones.ColaboraDctoBean;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Este servlet permite mostrar las facturas pendientes por pagar y su
 * respectivo saldo / vtaContenedorPagoCxCPlanilla.jsp /
 *
 * Este servlet implementa la interface GralManejadorRequest
 */
public class VtasAdmPagoCxCPlanillaManejadorRequest
        implements GralManejadorRequest {

    /**
     * BUTTON-- ("Registrar")-Permite el registro de pagosxplanilla por
     * vendedor/vtaContenedorPagoCxCPlanilla.jsp / ("Listar")-Permite ver una
     * planilla de pagos por vendedor/ ("Retirar")-Permite retirar los pagos de
     * un vendedor / (Guardar")- Guarda el proceso de pago parcial del pago de
     * planilla del cliente/ ("Cambiar Cliente")-Finaliza el proceso de ingreso
     * de pago de planilla/vtaFrmIngPagoCxCPlanilla.jsp/ ("Pagar")-Genera un pdf
     * con los pagos de la planilla del cliente/vtaFrmLiqPagoCxCPlanilla.jsp/
     * ("Salir")/("Regresar")-Salida al menu principal /
     *
     * Metodo contructor por defecto, es decir, sin parametros /
     */
    public VtasAdmPagoCxCPlanillaManejadorRequest() {
    }

    /**
     * PARAMETROS BUTTON-- "Vendedor"- selecciona el vendedor que realizara el
     * pago/ "FechaPago" - Ingresa fecha de pago/ "NombreTercero"- nombre del
     * cliente al que se le va a registrar pago planilla / "Nota"-Ingreso de
     * comentarios del pago / "Vr.Recibido"-Ingreso del valor a pagar /
     * "Vr.Dscto"-Ingreso valor descuento si lo hay / "Vr.RteFte"-Ingreso valor
     * retencion en la fuente si lo hay / "Vr. RteIva"-Ingreso valor retencion
     * iva si lo hay / "Vr. RteIca"-Ingreso valor si lo hay /
     *
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
        String xIdSucursal = "00";
        String xIdCliente = "-1";

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
        UsuarioBean usuarioBean
                = (UsuarioBean) sesion.getAttribute("usuarioBean");

        //
        String xIdUsuario = usuarioBean.getIdUsuarioStr();
        int xIdLocalUsuario = usuarioBean.getIdLocalUsuario();

        //
        AgendaLogVisitaBean agendaLogVisitaBean = new AgendaLogVisitaBean();
        agendaLogVisitaBean.setEstado(estadoActivo);
        agendaLogVisitaBean.setFechaVisitaStr(strFechaVisita);
        agendaLogVisitaBean.setIdUsuario(xIdUsuario);

        //
        FachadaAgendaLogVisitaBean fachadaAgendaLogVisitaBean = new FachadaAgendaLogVisitaBean();

        //
        fachadaAgendaLogVisitaBean
                = agendaLogVisitaBean.seleccionaVisitaEstadoFecha();

        //
        int xIdLogActual = fachadaAgendaLogVisitaBean.getIdLog();

        //
        fachadaAgendaLogVisitaBean.setIdUsuario(xIdUsuario);

        // Aqui escribe el Bean de Validacion en el Request para manejar el error
        request.setAttribute("fachadaAgendaLogVisitaBean",
                fachadaAgendaLogVisitaBean);

        // Validacion de accion relacionada con el formulario requerido
        if (accionContenedor != null) {

            //
            if (accionContenedor.compareTo("Salir") == 0) {
                return "/jsp/empty.htm";
            }

            //
            if (accionContenedor.compareTo("listaPlanilla") == 0) {

                //
                String xIdLocal = request.getParameter("xIdLocal");
                String xIdTipoOrden = request.getParameter("xIdTipoOrden");
                String xIdPlanilla = request.getParameter("xIdPlanilla");

                //
                GeneraPDFPagoPlanillaVendedor generaPDF
                        = new GeneraPDFPagoPlanillaVendedor();

                //
                generaPDF.setIdLocal(xIdLocal);
                generaPDF.setIdTipoOrden(xIdTipoOrden);
                generaPDF.setIdPlanilla(xIdPlanilla);
                generaPDF.setTerceroReporte("VENDEDOR  ");
                generaPDF.setTituloReporte("PLANILLA DE PAGO # ");
                generaPDF.setNombreReporte("VtasRepEmpresaPagoPlanillaVendedor");

                //
                generaPDF.generaPdf(request, response);

            }

            //
            if (accionContenedor.compareTo("Listar") == 0) {

                //
                String xIdVendedor = request.getParameter("xIdVendedor");
                String xFechaPago = request.getParameter("xFechaPago");

                //
                Validacion valida = new Validacion();

                //
                valida.reasignar("VENDEDOR", xIdVendedor);

                //
                valida.validarCampoDouble();

                //isValido
                if (!valida.isValido()) {

                    //
                    valida.setDescripcionError("ERROR, EL VENDEDOR NO EXISTE");

                    //
                    request.setAttribute("validacion", valida);
                    return "/jsp/gralError.jsp";
                }

                //
                valida.reasignar("FECHA PAGO", xFechaPago);

                //
                valida.validarCampoFecha();

                //isValido
                if (!valida.isValido()) {

                    //
                    valida.setDescripcionError("ERROR, FECHA PAGO CON FORMATO INCORRECTO");

                    //
                    request.setAttribute("validacion", valida);
                    return "/jsp/gralError.jsp";
                }

                //
                int idPeriodo = 200611;
                int estadoAtendido = 1; // visitaActiva
                int estadoProgramada = 9; // visitaProgramada
                int idEstadoVisita = 1; // Programada

                //
                agendaLogVisitaBean.setIdUsuario(xIdUsuario);
                agendaLogVisitaBean.setEstado(estadoProgramada);
                agendaLogVisitaBean.setFechaVisita(strFechaVisita);

                //
                boolean okLogOcupado = agendaLogVisitaBean.validaLogOcupado();

                //
                if (okLogOcupado) {

                    //
                    valida.reasignar("EXISTE UN PEDIDO O PAGO EN PROCESO", "");

                    // Valida el idCliente
                    valida.setDescripcionError("DEBE TERMINAR O CANCELAR LO ACTIVO");

                    //
                    request.setAttribute("validacion", valida);
                    return "/jsp/gralError.jsp";

                }

                //
                FachadaPagoBean fachadaPagoBean = new FachadaPagoBean();

                //
                fachadaPagoBean.setFechaPago(xFechaPago);
                fachadaPagoBean.setIdLocal(xIdLocalUsuario);
                fachadaPagoBean.setIdTipoOrden(xIdTipoOrdenVenta);
                fachadaPagoBean.setIdVendedor(xIdVendedor);

                //
                FachadaUsuarioBean fachadaUsuarioBean = new FachadaUsuarioBean();

                //
                usuarioBean.setIdUsuario(xIdVendedor);

                //
                fachadaUsuarioBean = usuarioBean.listaUsuario();

                //
                usuarioBean.setIdUsuario(xIdUsuario);

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaPagoBean", fachadaPagoBean);
                request.setAttribute("fachadaUsuarioBean", fachadaUsuarioBean);

                //
                return "/jsp/vtaFrmLstPagoCxCPlanilla.jsp";

            }

            // Guardar
            if (accionContenedor.compareTo("Guardar Medio") == 0) {

                //
                String xVrPago = request.getParameter("xVrPago");
                String xIdMedio = request.getParameter("xIdMedio");
                String xIdLocal = request.getParameter("xIdLocal");
                String xIdTipoOrden = request.getParameter("xIdTipoOrden");
                String xIndicador = request.getParameter("xIndicador");
                String xIdDcto = request.getParameter("xIdDcto");
                String xIdBanco = request.getParameter("xIdBanco");
                String xIdDctoMedio = request.getParameter("xIdDctoMedio");
                String xFechaCobro = request.getParameter("xFechaCobro");

                //
                Validacion validacion = new Validacion();

                //
                validacion.reasignar("CODIGO BANCO", xIdBanco);

                //
                validacion.validarCampoEntero();

                //
                if (!validacion.isValido()) {

                    //
                    request.setAttribute("validacion", validacion);

                    //
                    return "/jsp/gralError.jsp";

                }

                //
                validacion.reasignar("NUMERO CHEQUE", xIdDctoMedio);

                //
                validacion.validarCampoString();

                //
                if (!validacion.isValido()) {

                    //
                    request.setAttribute("validacion", validacion);

                    //
                    return "/jsp/gralError.jsp";

                }

                //
                validacion.reasignar("FECHA COBRO CHEQUE", xFechaCobro);

                //
                validacion.validarCampoFecha();

                //
                if (!validacion.isValido()) {

                    //
                    request.setAttribute("validacion", validacion);

                    //
                    return "/jsp/gralError.jsp";

                }

                //
                validacion.reasignar("MEDIO DE PAGO", xIdMedio);

                //
                validacion.validarCampoEntero();

                //
                if (!validacion.isValido()) {

                    //
                    request.setAttribute("validacion", validacion);

                    //
                    return "/jsp/gralError.jsp";

                }

                // PagoBean
                PagoBean pagoBean = new PagoBean();

                //
                pagoBean.setIdLocal(xIdLocalUsuario);
                pagoBean.setIdTipoOrden(xIdTipoOrdenPagoProceso);

                //
                PagoMedioBean pagoMedioBean = new PagoMedioBean();

                //
                int xEstadoOk = 1;

                //
                pagoMedioBean.setIdLocal(xIdLocal);
                pagoMedioBean.setIdTipoOrden(xIdTipoOrdenPagoProceso);
                pagoMedioBean.setIndicador(xIndicador);
                pagoMedioBean.setVrMedio(xVrPago);
                pagoMedioBean.setEstado(xEstadoOk);
                pagoMedioBean.setIdBanco(xIdBanco);
                pagoMedioBean.setIdDctoMedio(xIdDctoMedio);
                pagoMedioBean.setFechaCobro(xFechaCobro);
                pagoMedioBean.setIdMedio(xIdMedio);

                //
                pagoMedioBean.actualizarMedioTemporal(xIdTipoOrdenPagoProceso,
                        new Double(xIdDcto).doubleValue());

                //
                FachadaPagoBean fachadaPagoBean = new FachadaPagoBean();

                //--- listaUnDcto
                pagoBean.setIdDcto(xIdDcto);
                pagoBean.setIndicador(xIndicador);
                pagoBean.setIdTipoOrden(xIdTipoOrdenPagoProceso);
                pagoBean.setIdLocal(xIdLocal);

                //
                fachadaPagoBean = pagoBean.listaUnPagoDctoFCH();

                //
                fachadaPagoBean.setIdTipoOrden(xIdTipoOrdenVenta);

                //
                FachadaUsuarioBean fachadaUsuarioBean = new FachadaUsuarioBean();

                //
                usuarioBean.setIdUsuario(fachadaPagoBean.getIdVendedor());

                //
                fachadaUsuarioBean = usuarioBean.listaUsuario();

                //
                usuarioBean.setIdUsuario(xIdUsuario);

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaPagoBean", fachadaPagoBean);
                request.setAttribute("fachadaUsuarioBean", fachadaUsuarioBean);

                //
                return "/jsp/vtaFrmLiqPagoCxCPlanilla.jsp";

            }

            if (accionContenedor.compareTo("traeMedio") == 0) {

                //
                String xIdLocal = request.getParameter("xIdLocal");
                String xIdTipoOrden = request.getParameter("xIdTipoOrden");
                String xIndicador = request.getParameter("xIndicador");
                String xIdDcto = request.getParameter("xIdDcto");
                // String xIdVendedor   = request.getParameter("xIdVendedor");
                //  String xFechaPago    = request.getParameter("xFechaPago");

                // Importa la clase que contiene el DctoBean
                PagoBean pagoBean = new PagoBean();

                //
                FachadaPagoBean fachadaPagoBean = new FachadaPagoBean();

                //--- listaUnDcto
                pagoBean.setIdDcto(xIdDcto);
                pagoBean.setIndicador(xIndicador);
                pagoBean.setIdTipoOrden(xIdTipoOrdenPagoProceso);
                pagoBean.setIdLocal(xIdLocal);

                //
                fachadaPagoBean = pagoBean.listaUnPagoDctoFCH();

                //
                FachadaTerceroBean fachadaTerceroBean = new FachadaTerceroBean();

                //
                fachadaTerceroBean.setIdTipoOrden(xIdTipoOrden);
                fachadaTerceroBean.setIdCliente(fachadaPagoBean.getNitCC());
                fachadaTerceroBean.setIdLocal(xIdLocal);
                fachadaTerceroBean.setIdTipoTercero(xIdTipoTerceroCliente);

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaAgendaLogVisitaBean",
                        fachadaAgendaLogVisitaBean);
                request.setAttribute("fachadaTerceroBean", fachadaTerceroBean);
                request.setAttribute("fachadaPagoBean", fachadaPagoBean);

                //
                return "/jsp/vtaFrmModPagoCxCPlanilla.jsp";

            }

            //
            if (accionContenedor.compareTo("retiraDcto") == 0) {

                //
                String xIdTipoOrden = request.getParameter("xIdTipoOrden");
                String xIndicador = request.getParameter("xIndicador");
                String xIdDcto = request.getParameter("xIdDcto");
                String xIdLocal = request.getParameter("xIdLocal");
                String xIdVendedor = request.getParameter("xIdVendedor");
                String xFechaPago = request.getParameter("xFechaPago");

                // PagoBean
                PagoBean pagoBean = new PagoBean();

                //
                PagoMedioBean pagoMedioBean = new PagoMedioBean();

                //
                pagoMedioBean.setIdLocal(xIdLocal);
                pagoMedioBean.setIdTipoOrden(xIdTipoOrdenPagoProceso);
                pagoMedioBean.setIndicador(xIndicador);
                pagoMedioBean.setIdLog(xIdLogActual);

                //
                pagoMedioBean.retiraPagoDctoTemporal(
                        new Integer(xIdDcto).intValue());

                //
                pagoBean.setIdLocal(xIdLocal);
                pagoBean.setIndicador(xIndicador);
                pagoBean.setIdTipoOrden(xIdTipoOrdenPagoProceso);
                pagoBean.setIdDcto(xIdDcto);
                pagoBean.setIdLog(xIdLogActual);

                //
                pagoBean.retiraPagoDctoTemporal();

                //
                FachadaPagoBean fachadaPagoBean = new FachadaPagoBean();

                //
                fachadaPagoBean.setFechaPago(xFechaPago);
                fachadaPagoBean.setIdLocal(xIdLocalUsuario);
                fachadaPagoBean.setIdTipoOrden(xIdTipoOrdenVenta);
                fachadaPagoBean.setIdVendedor(xIdVendedor);
                fachadaPagoBean.setIdLog(xIdLogActual);

                //
                FachadaUsuarioBean fachadaUsuarioBean = new FachadaUsuarioBean();

                //
                usuarioBean.setIdUsuario(xIdVendedor);

                //
                fachadaUsuarioBean = usuarioBean.listaUsuario();

                //
                usuarioBean.setIdUsuario(xIdUsuario);

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaPagoBean", fachadaPagoBean);
                request.setAttribute("fachadaUsuarioBean", fachadaUsuarioBean);

                //
                return "/jsp/vtaFrmLiqPagoCxCPlanilla.jsp";

            }

            //
            if (accionContenedor.compareTo("Cambiar Cliente") == 0) {

                //
                String xIdVendedor = request.getParameter("xIdVendedor");
                String xFechaPago = request.getParameter("xFechaPago");

                //
                FachadaPagoBean fachadaPagoBean = new FachadaPagoBean();

                //
                fachadaPagoBean.setFechaPago(xFechaPago);
                fachadaPagoBean.setIdLocal(xIdLocalUsuario);
                fachadaPagoBean.setIdTipoOrden(xIdTipoOrdenVenta);
                fachadaPagoBean.setIdVendedor(xIdVendedor);
                fachadaPagoBean.setIdLog(xIdLogActual);

                //
                FachadaUsuarioBean fachadaUsuarioBean = new FachadaUsuarioBean();

                //
                usuarioBean.setIdUsuario(xIdVendedor);

                //
                fachadaUsuarioBean = usuarioBean.listaUsuario();

                //
                usuarioBean.setIdUsuario(xIdUsuario);

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaPagoBean", fachadaPagoBean);
                request.setAttribute("fachadaUsuarioBean", fachadaUsuarioBean);

                //
                return "/jsp/vtaFrmLiqPagoCxCPlanilla.jsp";

            }

            //
            if (accionContenedor.compareTo("Pagar") == 0) {

                //
                String xIdLocal = request.getParameter("xIdLocal");
                String xIdVendedor = request.getParameter("xIdVendedor");
                String xFechaPago = request.getParameter("xFechaPago");
                String xObservacion = request.getParameter("xObservacion");

                //
                Validacion valida = new Validacion();

                //
                valida.reasignar("NOTA", xObservacion);

                //
                valida.validarCampoString();

                //isValido
                if (!valida.isValido()) {

                    //
                    request.setAttribute("validacion", valida);
                    return "/jsp/gralError.jsp";
                }

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
                    int xIdReciboOld = fachadaPagoBean.getIdRecibo();
                    int xIndicador = fachadaPagoBean.getIndicador();

                    //
                    dctoBean.setIdDcto(fachadaPagoBean.getIdDcto());
                    dctoBean.setIdDctoCruce(fachadaPagoBean.getIdDcto());
                    dctoBean.setIndicador(xIndicador);
                    dctoBean.setIdTipoOrden(xIdTipoOrdenVenta);
                    dctoBean.setIdLocal(xIdLocal);
                    dctoBean.setIdDctoNitCC(fachadaPagoBean.getIdDctoNitCC());
                    dctoBean.setVrPago(fachadaPagoBean.getVrPago());
                    dctoBean.setVrRteFuente(fachadaPagoBean.getVrRteFuente());
                    dctoBean.setVrDsctoFcro(fachadaPagoBean.getVrDescuento());
                    dctoBean.setVrRteIva(fachadaPagoBean.getVrRteIva());
                    dctoBean.setVrRteIca(fachadaPagoBean.getVrRteIca());
                    dctoBean.setIdCliente(fachadaPagoBean.getNitCC());

                    //
                    okPago = dctoBean.actualizaPagoTercero();

                    //
                    pagoBean.setIdTipoOrden(xIdTipoOrdenVenta);
                    pagoBean.setIdLocal(xIdLocal);
                    pagoBean.setIndicador(fachadaPagoBean.getIndicador());

                    //
                    int xIdReciboMAX
                            = pagoBean.maximoReciboIdLocalxIndicador() + 1;

                    //
                    ColaboraDctoBean colaboraDctoBean = new ColaboraDctoBean();

                    //
                    colaboraDctoBean.setIdLocal(fachadaPagoBean.getIdLocal());
                    colaboraDctoBean.setIdCliente(fachadaPagoBean.getNitCC());
                    colaboraDctoBean.setIdTipoOrden(xIdTipoOrdenVenta);
                    colaboraDctoBean.setIdDcto(fachadaPagoBean.getIdDcto());

                    //
                    FachadaDctoBean fachadaDctoPagoBean = new FachadaDctoBean();

                    //
                    fachadaDctoPagoBean
                            = colaboraDctoBean.listaCuentaDetalladoClienteFCH();

                    //
                    double xVSaldoDctoActualizado
                            = fachadaDctoPagoBean.getVrSaldo();

                    //
                    pagoBean.setIdPlanilla(idMaximaPlanilla);
                    pagoBean.setIdLog(xIdLogActual);
                    pagoBean.setIdDcto(fachadaPagoBean.getIdDcto());
                    pagoBean.setIdTipoOrden(xIdTipoOrdenVenta);
                    pagoBean.setIdLocal(xIdLocal);
                    pagoBean.setIndicador(fachadaPagoBean.getIndicador());
                    pagoBean.setIdRecibo(xIdReciboMAX);
                    pagoBean.setVrSaldo(xVSaldoDctoActualizado);
                    pagoBean.setIdVendedor(xIdVendedor);

                    //
                    pagoBean.setIdLocal(xIdLocal);
                    pagoBean.setIdTipoOrden(xIdTipoOrdenPagoProceso);
                    pagoBean.setIdLog(xIdLogActual);
                    pagoBean.setIdRecibo(xIdReciboOld);

                    //
                    pagoBean.ingresaPago(xIdTipoOrdenVenta,
                            xIdReciboMAX,
                            xIndicador,
                            fachadaPagoBean.getIdDcto(),
                            xIdVendedor,
                            fachadaPagoBean.getVrPago(),
                            idMaximaPlanilla,
                            fachadaPagoBean.getIdDctoStr());

                    //
                    pagoBean.setIdLocal(xIdLocal);
                    pagoBean.setIdTipoOrden(xIdTipoOrdenVenta);
                    pagoBean.setIdRecibo(xIdReciboMAX);
                    pagoBean.setIndicador(xIndicador);
                    pagoBean.setObservacion(xObservacion);

                    //
                    pagoBean.actualizaObservacion();

                    //
                    PagoMedioBean pagoMedioBean = new PagoMedioBean();

                    //
                    pagoMedioBean.setIdLocal(xIdLocal);
                    pagoMedioBean.setIdTipoOrden(xIdTipoOrdenPagoProceso);
                    pagoMedioBean.setIdLog(xIdLogActual);
                    pagoMedioBean.setIdRecibo(xIdReciboOld);

                    //
                    pagoMedioBean.ingresaPago(xIdTipoOrdenVenta,
                            xIdReciboMAX,
                            xIndicador,
                            fachadaPagoBean.getIdDcto());

                    //
                    pagoMedioBean.setIdRecibo(xIdReciboOld);
                    pagoMedioBean.setIdTipoOrden(xIdTipoOrdenPagoProceso);
                    pagoMedioBean.setIndicador(xIndicador);
                    pagoMedioBean.setIdLocal(xIdLocal);

                    //
                    pagoMedioBean.retiraReciboTemporal();

                    //
                    pagoBean.setIdRecibo(xIdReciboOld);
                    pagoBean.setIdTipoOrden(xIdTipoOrdenPagoProceso);
                    pagoMedioBean.setIndicador(xIndicador);
                    pagoMedioBean.setIdLocal(xIdLocal);

                    //
                    pagoBean.retiraReciboTemporal();

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
                    GeneraPDFPagoPlanillaVendedor generaPDF
                            = new GeneraPDFPagoPlanillaVendedor();

                    //
                    generaPDF.setIdLocal(xIdLocalUsuario);
                    generaPDF.setIdTipoOrden(xIdTipoOrdenVenta);
                    generaPDF.setIdPlanilla(idMaximaPlanilla);
                    generaPDF.setTerceroReporte("VENDEDOR  ");
                    generaPDF.setTituloReporte("RECIBO DE CAJA # ");
                    generaPDF.setNombreReporte("VtasRepEmpresaPagoPlanillaVendedor");

                    //
                    generaPDF.generaPdf(request, response);

                }

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
                xIdCliente = request.getParameter("xIdCliente");
                String xIdVendedor = request.getParameter("xIdVendedor");
                String xFechaPago = request.getParameter("xFechaPago");
                String xIdOrden = request.getParameter("xIdOrden");

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
                dctoBean.setIdOrden(xIdOrden);
                dctoBean.setIndicador(xIndicador);
                dctoBean.setIdTipoOrden(xIdTipoOrden);
                dctoBean.setIdLocal(xIdLocal);

                //
                fachadaDctoBean = dctoBean.listaUnDctoOrden();

                //
                ColaboraDctoBean colaboraDctoBean = new ColaboraDctoBean();

                //
                colaboraDctoBean.setIdLocal(fachadaDctoBean.getIdLocal());
                colaboraDctoBean.setIdCliente(fachadaDctoBean.getIdCliente());
                colaboraDctoBean.setIdTipoOrden(fachadaDctoBean.getIdTipoOrden());
                colaboraDctoBean.setIdDcto(fachadaDctoBean.getIdDcto());

                //
                FachadaDctoBean fachadaDctoPagoBean = new FachadaDctoBean();

                //
                fachadaDctoPagoBean
                        = colaboraDctoBean.listaCuentaDetalladoOrdenFCH();

                //
                double xVSaldoDctoActualizado = fachadaDctoPagoBean.getVrSaldo();

                //
                PagoMedioBean pagoMedioBean = new PagoMedioBean();

                //
                pagoMedioBean.setIdLocal(xIdLocal);
                pagoMedioBean.setIdTipoOrden(xIdTipoOrdenPagoProceso);
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
                int xIdReciboMAX
                        = pagoBean.maximoReciboIdLocalxIndicador() + 1;

                //
                pagoBean.setIdLocal(fachadaDctoBean.getIdLocal());
                pagoBean.setIdTipoOrden(xIdTipoOrdenPagoProceso);
                pagoBean.setIdRecibo(xIdReciboMAX);
                pagoBean.setIndicador(fachadaDctoBean.getIndicador());
                pagoBean.setFechaPago(xFechaPago);
                pagoBean.setVrPago(xVSaldoDctoActualizado);
                pagoBean.setNitCC(fachadaDctoBean.getIdCliente());
                pagoBean.setEstado(estadoActivo);
                pagoBean.setIdUsuario(xIdUsuario);
                pagoBean.setVrRteFuente(xCero);
                pagoBean.setVrDescuento(xCero);
                pagoBean.setVrRteIva(xCero);
                pagoBean.setIdDcto(xIdDcto);
                pagoBean.setIdDctoNitCC(fachadaDctoBean.getIdCliente());
                pagoBean.setIdPlanilla(idMaximaPlanilla);
                pagoBean.setVrSaldo(xVSaldoDctoActualizado);
                pagoBean.setIdLog(xIdLogActual);
                pagoBean.setIdVendedor(xIdVendedor);

                // ingresaPago
                boolean okIngresaPago = pagoBean.ingresaPago();
                int xIdTransferencia = 3;
                int xBancoDefecto = 07;
                int xEstadoOk = 1;

                String xIdDctoMedioOk = "";

                //
                pagoMedioBean.setIdLocal(xIdLocal);
                pagoMedioBean.setIdTipoOrden(xIdTipoOrdenPagoProceso);
                pagoMedioBean.setIdRecibo(xIdReciboMAX);
                pagoMedioBean.setIndicador(fachadaDctoBean.getIndicador());
                pagoMedioBean.setVrMedio(xVSaldoDctoActualizado);
                pagoMedioBean.setEstado(xEstadoOk);
                pagoMedioBean.setIdBanco(xBancoDefecto);
                pagoMedioBean.setIdDctoMedio(xIdDctoMedioOk);
                pagoMedioBean.setFechaCobro(xFechaPago);
                pagoMedioBean.setIdMedio(xIdTransferencia);
                pagoMedioBean.setIdLog(xIdLogActual);

                //
                pagoMedioBean.ingresar();

                //
                FachadaTerceroBean fachadaTerceroBean = new FachadaTerceroBean();

                //
                fachadaTerceroBean.setIdTipoOrden(xIdTipoOrden);
                fachadaTerceroBean.setIdCliente(fachadaDctoBean.getIdCliente());
                fachadaTerceroBean.setIdLocal(xIdLocalUsuario);
                fachadaTerceroBean.setIdTipoTercero(xIdTipoTerceroCliente);

                //
                FachadaPagoBean fachadaPagoBean = new FachadaPagoBean();

                //
                fachadaPagoBean = pagoBean.listaUnPagoFCH();

                //
                fachadaPagoBean.setFechaPago(xFechaPago);
                fachadaPagoBean.setIdLocal(xIdLocalUsuario);
                fachadaPagoBean.setIdTipoOrden(xIdTipoOrdenVenta);
                fachadaPagoBean.setIdVendedor(xIdVendedor);
                fachadaPagoBean.setNitCC(xIdCliente);

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaPagoBean", fachadaPagoBean);
                request.setAttribute("fachadaTerceroBean", fachadaTerceroBean);

                //
                return "/jsp/vtaFrmIngPagoCxCPlanilla.jsp";

            }

            //
            if (accionContenedor.compareTo("Guardar") == 0) {

                //
                String xIdTipoOrden = request.getParameter("xIdTipoOrden");
                String xIndicador = request.getParameter("xIndicador");
                String xIdDcto = request.getParameter("xIdDcto");
                String xIdLocal = request.getParameter("xIdLocal");
                String xIdVendedor = request.getParameter("xIdVendedor");
                String xFechaPago = request.getParameter("xFechaPago");
                String xIdOrden = request.getParameter("xIdOrden");

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
                dctoBean.setIndicador(xIndicador);
                dctoBean.setIdTipoOrden(xIdTipoOrden);
                dctoBean.setIdLocal(xIdLocal);
                dctoBean.setIdOrden(xIdOrden);

                //
                fachadaDctoBean = dctoBean.listaUnDctoOrden();

                //
                ColaboraDctoBean colaboraDctoBean = new ColaboraDctoBean();

                colaboraDctoBean.setIdLocal(fachadaDctoBean.getIdLocal());
                colaboraDctoBean.setIdCliente(fachadaDctoBean.getIdCliente());
                colaboraDctoBean.setIdTipoOrden(fachadaDctoBean.getIdTipoOrden());
                colaboraDctoBean.setIdDcto(fachadaDctoBean.getIdDcto());
                colaboraDctoBean.setIdOrden(fachadaDctoBean.getIdOrden());

                //
                FachadaDctoBean fachadaDctoPagoBean = new FachadaDctoBean();

                //
                fachadaDctoPagoBean
                        = colaboraDctoBean.listaCuentaDetalladoOrdenFCH();

                //
                double xVSaldoDctoActualizado
                        = fachadaDctoPagoBean.getVrSaldo();

                //
                if (xVSaldoDctoActualizado
                        < (new Double(xVrPago).doubleValue()
                        + new Double(xVrDescuento).doubleValue()
                        + new Double(xVrRteFuente).doubleValue()
                        + new Double(xVrRteIva).doubleValue()
                        + new Double(xVrRteIca).doubleValue())) {

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
                dctoBean.setIndicador(xIndicador);
                dctoBean.setIdTipoOrden(xIdTipoOrden);
                dctoBean.setIdLocal(xIdLocal);
                dctoBean.setIdOrden(xIdOrden);

                //
                fachadaDctoBean = dctoBean.listaUnDctoOrden();

                //
                PagoMedioBean pagoMedioBean = new PagoMedioBean();

                //
                pagoMedioBean.setIdLocal(xIdLocal);
                pagoMedioBean.setIdTipoOrden(xIdTipoOrdenPagoProceso);
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
                int xIdReciboMAX
                        = pagoBean.maximoReciboIdLocalxIndicador() + 1;

                //
                pagoBean.setIdLocal(fachadaDctoBean.getIdLocal());
                pagoBean.setIdTipoOrden(xIdTipoOrdenPagoProceso);
                pagoBean.setIdRecibo(xIdReciboMAX);
                pagoBean.setIndicador(fachadaDctoBean.getIndicador());
                pagoBean.setFechaPago(xFechaPago);
                pagoBean.setVrPago(xVrPago);
                pagoBean.setNitCC(fachadaDctoBean.getIdCliente());
                pagoBean.setEstado(estadoActivo);
                pagoBean.setIdUsuario(xIdUsuario);
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
                pagoBean.setIdVendedor(xIdVendedor);

                // ingresaPago
                boolean okIngresaPago = pagoBean.ingresaPago();

                //
                int xEstadoOk = 1;

                String xIdDctoMedioOk = "";

                int xIdTransferencia = 3;
                int xBancoDefecto = 07;
                //
                pagoMedioBean.setIdLocal(xIdLocal);
                pagoMedioBean.setIdTipoOrden(xIdTipoOrdenPagoProceso);
                pagoMedioBean.setIdRecibo(xIdReciboMAX);
                pagoMedioBean.setIndicador(fachadaDctoBean.getIndicador());
                pagoMedioBean.setVrMedio(xVrPago);
                pagoMedioBean.setEstado(xEstadoOk);
                pagoMedioBean.setIdBanco(xBancoDefecto);
                pagoMedioBean.setIdDctoMedio(xIdDctoMedioOk);
                pagoMedioBean.setFechaCobro(strFechaVisita);
                pagoMedioBean.setIdMedio(xIdTransferencia);
                pagoMedioBean.setIdLog(xIdLogActual);

                //
                pagoMedioBean.ingresar();

                //
                FachadaTerceroBean fachadaTerceroBean = new FachadaTerceroBean();

                //
                fachadaTerceroBean.setIdTipoOrden(xIdTipoOrden);
                fachadaTerceroBean.setIdCliente(fachadaDctoBean.getIdCliente());
                fachadaTerceroBean.setIdLocal(xIdLocalUsuario);
                fachadaTerceroBean.setIdTipoTercero(xIdTipoTerceroCliente);

                //
                pagoBean.setIdLocal(xIdLocalUsuario);
                pagoBean.setIdLog(fachadaAgendaLogVisitaBean.getIdLog());
                pagoBean.setIdTipoOrden(xIdTipoOrdenPagoProceso);

                //
                FachadaPagoBean fachadaPagoBean = new FachadaPagoBean();

                //
                fachadaPagoBean = pagoBean.listaUnPagoFCH();

                //
                fachadaPagoBean.setFechaPago(xFechaPago);
                fachadaPagoBean.setIdLocal(xIdLocalUsuario);
                fachadaPagoBean.setIdTipoOrden(xIdTipoOrdenVenta);
                fachadaPagoBean.setIdVendedor(xIdVendedor);
                fachadaPagoBean.setNitCC(xIdCliente);

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaPagoBean", fachadaPagoBean);
                request.setAttribute("fachadaTerceroBean", fachadaTerceroBean);

                //
                return "/jsp/vtaFrmIngPagoCxCPlanilla.jsp";

            }

            //
            if (accionContenedor.compareTo("PagoParcial") == 0) {

                //
                String xIdLocal = request.getParameter("xIdLocal");
                String xIdTipoOrden = request.getParameter("xIdTipoOrden");
                String xIndicador = request.getParameter("xIndicador");
                String xIdDcto = request.getParameter("xIdDcto");
                xIdCliente = request.getParameter("xIdCliente");
                String xIdVendedor = request.getParameter("xIdVendedor");
                String xFechaPago = request.getParameter("xFechaPago");
                String xIdOrden = request.getParameter("xIdOrden");

                //
                FachadaTerceroBean fachadaTerceroBean = new FachadaTerceroBean();

                //
                fachadaTerceroBean.setIdTipoOrden(xIdTipoOrden);
                fachadaTerceroBean.setIdCliente(xIdCliente);
                fachadaTerceroBean.setIdLocal(xIdLocalUsuario);
                fachadaTerceroBean.setIdTipoTercero(xIdTipoTerceroCliente);

                //
                FachadaDctoBean fachadaDctoBean = new FachadaDctoBean();

                // Importa la clase que contiene el DctoBean
                DctoBean dctoBean = new DctoBean();

                //
                dctoBean.setIdOrden(xIdOrden);
                dctoBean.setIndicador(xIndicador);
                dctoBean.setIdTipoOrden(xIdTipoOrden);
                dctoBean.setIdLocal(xIdLocal);

                //
                fachadaDctoBean = dctoBean.listaUnDctoOrden();

                //
                ColaboraDctoBean colaboraDctoBean = new ColaboraDctoBean();

                //
                colaboraDctoBean.setIdLocal(fachadaDctoBean.getIdLocal());
                colaboraDctoBean.setIdCliente(fachadaDctoBean.getIdCliente());
                colaboraDctoBean.setIdTipoOrden(
                        fachadaDctoBean.getIdTipoOrden());
                colaboraDctoBean.setIdDcto(fachadaDctoBean.getIdDcto());
                colaboraDctoBean.setIdOrden(fachadaDctoBean.getIdOrden());

                //
                FachadaDctoBean fachadaDctoPagoBean = new FachadaDctoBean();

                //
                fachadaDctoPagoBean
                        = colaboraDctoBean.listaCuentaDetalladoOrdenFCH();

                //
                PagoBean pagoBean = new PagoBean();

                //
                pagoBean.setIdLocal(xIdLocalUsuario);
                pagoBean.setIdLog(fachadaAgendaLogVisitaBean.getIdLog());
                pagoBean.setIdTipoOrden(xIdTipoOrdenPagoProceso);

                //
                FachadaPagoBean fachadaPagoBean = new FachadaPagoBean();

                //
                fachadaPagoBean = pagoBean.listaUnPagoFCH();

                //
                fachadaPagoBean.setFechaPago(xFechaPago);
                fachadaPagoBean.setIdLocal(xIdLocalUsuario);
                fachadaPagoBean.setIdTipoOrden(xIdTipoOrdenVenta);
                fachadaPagoBean.setIdVendedor(xIdVendedor);
                fachadaPagoBean.setNitCC(xIdCliente);

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaPagoBean", fachadaPagoBean);
                request.setAttribute("fachadaTerceroBean", fachadaTerceroBean);
                request.setAttribute("fachadaDctoBean", fachadaDctoBean);
                request.setAttribute("fachadaDctoPagoBean", fachadaDctoPagoBean);

                //
                return "/jsp/vtaFrmIngPagoCxCPlanillaParcial.jsp";

            }

            //
            if (accionContenedor.compareTo("Confirmar") == 0) {

                //
                xIdCliente = request.getParameter("xIdCliente");
                String xIdVendedor = request.getParameter("xIdVendedor");
                String xFechaPago = request.getParameter("xFechaPago");

                //
                TerceroBean terceroBean = new TerceroBean();

                //
                terceroBean.setIdCliente(xIdCliente);
                terceroBean.setIdTipoTercero(xIdTipoTerceroCliente);

                //
                FachadaTerceroBean fachadaTerceroBean = new FachadaTerceroBean();

                //
                fachadaTerceroBean = terceroBean.listaUnTerceroFCH();

                //
                fachadaTerceroBean.setIdTipoOrden(xIdTipoOrdenVenta);
                fachadaTerceroBean.setIdCliente(xIdCliente);
                fachadaTerceroBean.setIdLocal(xIdLocalUsuario);

                //
                request.setAttribute("fachadaTerceroBean", fachadaTerceroBean);

                //
                PagoBean pagoBean = new PagoBean();

                //
                pagoBean.setIdLocal(xIdLocalUsuario);
                pagoBean.setIdLog(fachadaAgendaLogVisitaBean.getIdLog());
                pagoBean.setIdTipoOrden(xIdTipoOrdenPagoProceso);

                //
                FachadaPagoBean fachadaPagoBean = new FachadaPagoBean();

                //
                fachadaPagoBean = pagoBean.listaUnPagoFCH();

                //
                fachadaPagoBean.setFechaPago(xFechaPago);
                fachadaPagoBean.setIdLocal(xIdLocalUsuario);
                fachadaPagoBean.setIdTipoOrden(xIdTipoOrdenVenta);
                fachadaPagoBean.setIdVendedor(xIdVendedor);
                fachadaPagoBean.setNitCC(xIdCliente);

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaPagoBean", fachadaPagoBean);

                //
                return "/jsp/vtaFrmIngPagoCxCPlanilla.jsp";

            }

            //
            if (accionContenedor.compareTo("Buscar Cliente") == 0) {

                //
                String xNombreTercero = request.getParameter("xNombreTercero");
                String xIdVendedor = request.getParameter("xIdVendedor");
                String xFechaPago = request.getParameter("xFechaPago");

                //
                FachadaTerceroBean fachadaTerceroBean = new FachadaTerceroBean();

                //
                fachadaTerceroBean.setIdTipoTercero(xIdTipoTerceroCliente);

                //
                fachadaAgendaLogVisitaBean
                        = agendaLogVisitaBean.seleccionaVisitaEstadoFecha();

                // Retorna a seleccionar cliente
                if (fachadaAgendaLogVisitaBean.getIdCliente() == null) {

                    fachadaAgendaLogVisitaBean.setIdCliente(xIdCliente);

                }

                //
                fachadaAgendaLogVisitaBean.setIdUsuario(xIdUsuario);

                //
                fachadaTerceroBean.setIdTipoTercero(xIdTipoTerceroCliente);
                fachadaTerceroBean.setNombreTercero(xNombreTercero);

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaTerceroBean", fachadaTerceroBean);

                //
                FachadaPagoBean fachadaPagoBean = new FachadaPagoBean();

                //
                fachadaPagoBean.setFechaPago(xFechaPago);
                fachadaPagoBean.setIdLocal(xIdLocalUsuario);
                fachadaPagoBean.setIdTipoOrden(xIdTipoOrdenVenta);
                fachadaPagoBean.setIdVendedor(xIdVendedor);

                //
                usuarioBean.setIdUsuario(xIdUsuario);

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaPagoBean", fachadaPagoBean);

                //
                return "/jsp/vtaFrmSelPagoCxCPlanilla.jsp";

            }

            //
            if (accionContenedor.compareTo("Registrar") == 0) {

                //
                String xIdVendedor = request.getParameter("xIdVendedor");
                String xFechaPago = request.getParameter("xFechaPago");

                //
                Validacion valida = new Validacion();

                //
                valida.reasignar("VENDEDOR", xIdVendedor);

                //
                valida.validarCampoDouble();

                //isValido
                if (!valida.isValido()) {

                    //
                    valida.setDescripcionError("ERROR, EL VENDEDOR NO EXISTE");

                    //
                    request.setAttribute("validacion", valida);
                    return "/jsp/gralError.jsp";
                }

                //
                valida.reasignar("FECHA PAGO", xFechaPago);

                //
                valida.validarCampoFecha();

                //isValido
                if (!valida.isValido()) {

                    //
                    valida.setDescripcionError("ERROR, FECHA PAGO CON FORMATO INCORRECTO");

                    //
                    request.setAttribute("validacion", valida);
                    return "/jsp/gralError.jsp";
                }

                //
                int idPeriodo = 200611;
                int estadoAtendido = 1; // visitaActiva
                int estadoProgramada = 9; // visitaProgramada
                int idEstadoVisita = 1; // Programada

                //
                agendaLogVisitaBean.setIdUsuario(xIdUsuario);
                agendaLogVisitaBean.setEstado(estadoProgramada);
                agendaLogVisitaBean.setFechaVisita(strFechaVisita);

                //
                boolean okLogOcupado = agendaLogVisitaBean.validaLogOcupado();

                //
                if (okLogOcupado) {

                    //
                    valida.reasignar("EXISTE UN PEDIDO O PAGO EN PROCESO", "");

                    // Valida el idCliente
                    valida.setDescripcionError("DEBE TERMINAR O CANCELAR LO ACTIVO");

                    //
                    request.setAttribute("validacion", valida);
                    return "/jsp/gralError.jsp";

                }

                //
                int idLog = agendaLogVisitaBean.maximoIdLog() + 1;

                //
                agendaLogVisitaBean.setIdCliente(xIdVendedor);
                agendaLogVisitaBean.setIdUsuario(xIdUsuario);
                agendaLogVisitaBean.setIdPeriodo(idPeriodo);
                agendaLogVisitaBean.setFechaVisita(strFechaVisita);
                agendaLogVisitaBean.setIdLog(idLog);
                agendaLogVisitaBean.setIdEstadoVisita(idEstadoVisita);
                agendaLogVisitaBean.setEstado(estadoAtendido);

                //
                boolean okRetirar
                        = agendaLogVisitaBean.actualizaLogVisitaUsuario(estadoProgramada);

                // estadoActivo = 9
                agendaLogVisitaBean.setEstado(estadoProgramada);

                //
                boolean okIngreso = agendaLogVisitaBean.ingresaLogVisita();

                //
                FachadaPagoBean fachadaPagoBean = new FachadaPagoBean();

                //
                fachadaPagoBean.setFechaPago(xFechaPago);
                fachadaPagoBean.setIdLocal(xIdLocalUsuario);
                fachadaPagoBean.setIdTipoOrden(xIdTipoOrdenVenta);
                fachadaPagoBean.setIdVendedor(xIdVendedor);

                //
                FachadaUsuarioBean fachadaUsuarioBean = new FachadaUsuarioBean();

                //
                usuarioBean.setIdUsuario(xIdVendedor);

                //
                fachadaUsuarioBean = usuarioBean.listaUsuario();

                //
                usuarioBean.setIdUsuario(xIdUsuario);

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaPagoBean", fachadaPagoBean);
                request.setAttribute("fachadaUsuarioBean", fachadaUsuarioBean);

                //
                return "/jsp/vtaFrmLiqPagoCxCPlanilla.jsp";

            }

        }

        return "/jsp/empty.htm";

    }
}
