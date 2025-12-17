package com.solucionesweb.losservlets;

// Importa los paquetes del lenguaje especificamente io y los
// que permiten trabajar el servlet.
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//a
import java.util.logging.Level;
import java.util.logging.Logger;

//
import com.solucionesweb.lasayudas.ProcesoIp;

import com.solucionesweb.lasayudas.ProcesoIngresoResurtidoCompra;

//
import com.solucionesweb.losbeans.utilidades.JhDate;

// Importa la clase que contiene el UsuarioBean
import com.solucionesweb.losbeans.negocio.UsuarioBean;

//
import com.solucionesweb.losbeans.negocio.AgendaLogVisitaBean;

import com.solucionesweb.losbeans.negocio.DctoOrdenDetalleBean;

//
import com.solucionesweb.losbeans.fachada.FachadaAgendaLogVisitaBean;

//
import com.solucionesweb.losbeans.fachada.FachadaDctoOrdenBean;

import com.solucionesweb.losbeans.fachada.FachadaColaboraDctoOrdenBean;

// Importa la clase que contiene el Bean de Validacion
import com.solucionesweb.losbeans.utilidades.Validacion;

// Importa la clase que contiene el Day
import com.solucionesweb.losbeans.utilidades.Day;

import com.solucionesweb.lasayudas.ProcesoGuardaPlu;

//
import com.solucionesweb.lasayudas.ProcesoReciboCompra;
import com.solucionesweb.losbeans.colaboraciones.ColaboraResurtidoOrden;

// Importa la clase que contiene el TerceroBean
import com.solucionesweb.losbeans.negocio.TerceroBean;

import com.solucionesweb.losbeans.fachada.FachadaTerceroBean;

import com.solucionesweb.losbeans.colaboraciones.ColaboraOrdenDetalleBean;

// Importa el Bean de Fachada
import com.solucionesweb.losbeans.fachada.FachadaDctoOrdenDetalleBean;

// Importa la clase que contiene el ContableRetencionBean
import com.solucionesweb.losbeans.negocio.ContableRetencionBean;

// Importa la clase que contiene el ColaboraTercero
import com.solucionesweb.losbeans.colaboraciones.ColaboraTercero;

// Importa la clase que contiene el DctoBean
import com.solucionesweb.losbeans.negocio.DctoBean;

//
import com.solucionesweb.losbeans.fachada.FachadaDctoBean;

// Importa la clase que contiene el DctoBean
import com.solucionesweb.losbeans.negocio.DctoOrdenBean;

// Este servlet implementa la interface GralManejadorRequest
public class VtasAdmReciboCompraManejadorRequest
        implements GralManejadorRequest {

    // Metodo contructor por defecto, es decir, sin parametros
    public VtasAdmReciboCompraManejadorRequest() {
    }

    //
    public String generaPdf(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {


        //
        String accionContenedor = request.getParameter("accionContenedor");

        //
        System.out.println("accionContenedor :" + accionContenedor);

        //---
        int xIdTipoOrdenCompra = 1;
        int xIdTipoTerceroProveedor = 2;
        int xIdTipoOrdenCompraProceso = xIdTipoOrdenCompra + 50;


        //
        HttpSession sesion = request.getSession();

        //
        UsuarioBean usuarioBean =
                (UsuarioBean) sesion.getAttribute("usuarioBean");

        //
        String xIdLocalUsuario = usuarioBean.getIdLocalUsuarioStr();
        String xIdUsuario = usuarioBean.getIdUsuarioSf0();
        int xEstadoActivo = 9;
        int xEstadoSuspendido = 8;
        int estadoProgramada = 9; // visitaProgramada

        //
        Day day = new Day();

        //
        String strFechaVisita = day.getFechaFormateada();

        //
        JhDate jhDate = new JhDate();

        //
        String fechaTxHM = null;

        //
        try {
            //
            fechaTxHM = jhDate.getDate(4, false);
        } catch (Exception ex) {
            Logger.getLogger(VtasAdmResurtidoTrasladoManejadorRequest.class.getName()).log(Level.SEVERE, null, ex);
        }

        //
        FachadaAgendaLogVisitaBean fachadaAgendaLogVisitaBean = new FachadaAgendaLogVisitaBean();

        //
        AgendaLogVisitaBean agendaLogVisitaBean = new AgendaLogVisitaBean();

        //
        agendaLogVisitaBean.setEstado(xEstadoActivo);
        agendaLogVisitaBean.setFechaVisita(strFechaVisita);
        agendaLogVisitaBean.setIdUsuario(xIdUsuario);

        //
        fachadaAgendaLogVisitaBean =
                agendaLogVisitaBean.listaLogActivo();

        //
        int xIdLogActual = fachadaAgendaLogVisitaBean.getIdLog();

        // valida orden diferente en proceso
        boolean xOrdenDiferente =
                agendaLogVisitaBean.validaDiferenteOrdenProceso(
                xIdTipoOrdenCompraProceso);

        //
        if (xOrdenDiferente) {

            // validacion
            Validacion validacion = new Validacion();

            //
            validacion.setDescripcionError("ERROR, DEBE TERMINAR PROCESO ACTIVO");

            //
            request.setAttribute("validacion", validacion);
            return "/jsp/gralError.jsp";

        }

        //
        if (accionContenedor.compareTo("Crear") == 0) {

            //
            xIdLogActual = xIdLogActual;


        } else {

            //
            agendaLogVisitaBean.setEstado(xEstadoSuspendido);
            agendaLogVisitaBean.setIdLog(xIdLogActual);

            //
            fachadaAgendaLogVisitaBean =
                    agendaLogVisitaBean.listaLogSuspendidoFCH(
                    xIdTipoOrdenCompraProceso);

            //
            if (fachadaAgendaLogVisitaBean.getIdLog() > 0) {
                xIdLogActual = fachadaAgendaLogVisitaBean.getIdLog();
            }

        }

        // Validacion de accion relacionada con el formulario requerido
        if (accionContenedor != null) {

            //
            if (accionContenedor.compareTo("Salir") == 0) {
                return "/jsp/empty.htm";

            }
            //
            if (accionContenedor.compareTo("Regresar") == 0) {
                return "/jsp/empty.htm";

            }

            // Confirmar
            if (accionContenedor.compareTo("Confirmar") == 0) {

                //
                String xDiasHistoria = request.getParameter("xDiasHistoria");
                String xDiasInventario =
                        request.getParameter("xDiasInventario");
                String xIdTercero = request.getParameter("xIdTercero");
                String xFechaCorte = request.getParameter("xFechaCorte");
                String xIdPluArr[] = request.getParameterValues("xIdPlu");
                String xCantidadPedidaArr[] =
                        request.getParameterValues("xCantidadPedido");
                String xCostoPedidoArr[] =
                        request.getParameterValues("xCostoPedido");
                String xIdLog = request.getParameter("xIdLog");


                //
                Validacion valida = new Validacion();

                // Validar
                for (int indice = 0; indice < xIdPluArr.length; indice++) {

                    //
                    if (xCantidadPedidaArr[indice].length() == 0) {
                        continue;
                    }

                    //
                    valida.reasignar("CANTIDAD", xCantidadPedidaArr[indice]);

                    //
                    valida.validarCampoDoublePositivo();

                    //isValido
                    if (!valida.isValido()) {

                        //
                        request.setAttribute("validacion", valida);
                        return "/jsp/gralError.jsp";
                    }

                    //
                    if (xCostoPedidoArr[indice].length() == 0) {
                        continue;
                    }

                    //
                    valida.reasignar("VR.COSTO", xCostoPedidoArr[indice]);

                    //
                    valida.validarCampoDoublePositivo();

                    //isValido
                    if (!valida.isValido()) {

                        //
                        request.setAttribute("validacion", valida);
                        return "/jsp/gralError.jsp";
                    }
                }

                // validacion
                Validacion validacion = new Validacion();


                //
                for (int indice = 0; indice
                        < xCantidadPedidaArr.length; indice++) {

                    //
                    validacion.reasignar("CANTIDAD PEDIDA",
                            xCantidadPedidaArr[indice]);

                    //
                    validacion.validarCampoDoublePositivo();

                    // isValido
                    if (!validacion.isValido()) {

                        //
                        request.setAttribute("validacion", validacion);
                        return "/jsp/gralError.jsp";

                    }

                    //
                    validacion.reasignar("COSTO NEGOCIADO",
                            xCostoPedidoArr[indice]);

                    //
                    validacion.validarCampoDoublePositivo();

                    // isValido
                    if (!validacion.isValido()) {

                        //
                        request.setAttribute("validacion", validacion);
                        return "/jsp/gralError.jsp";

                    }
                }

                //
                xIdLogActual = new Integer(xIdLog).intValue();

                //
                ProcesoGuardaPlu procesoGuardaPlu = new ProcesoGuardaPlu();

                //
                for (int indice = 0; indice
                        < xCantidadPedidaArr.length; indice++) {

                    //
                    if (new Double(xCantidadPedidaArr[indice]).doubleValue() > 0) {

                        //
                        procesoGuardaPlu.guarda(xIdLogActual,
                                xIdPluArr[indice],
                                xCantidadPedidaArr[indice],
                                xCostoPedidoArr[indice],
                                xIdTipoOrdenCompraProceso,
                                xIdUsuario,
                                xIdLocalUsuario,
                                xIdTercero,
                                xFechaCorte,
                                xDiasHistoria,
                                xDiasInventario);
                    }
                }


                //
                ColaboraResurtidoOrden colaboraResurtidoOrden = new ColaboraResurtidoOrden();

                //
                colaboraResurtidoOrden.setIdLocal(xIdLocalUsuario);
                colaboraResurtidoOrden.setIdTipoOrden(xIdTipoOrdenCompraProceso);
                colaboraResurtidoOrden.setIdLog(xIdLog);

                //
                FachadaColaboraDctoOrdenBean fachadaColaboraProveedorBean = new FachadaColaboraDctoOrdenBean();

                //
                fachadaColaboraProveedorBean.setFechaEntrega(strFechaVisita);
                fachadaColaboraProveedorBean.setFechaOrden(strFechaVisita);
                fachadaColaboraProveedorBean.setIdDctoNitCC("");


                //
                request.setAttribute("fachadaColaboraProveedorBean",
                        fachadaColaboraProveedorBean);

                //
                FachadaColaboraDctoOrdenBean fachadaColaboraDctoOrdenBean = new FachadaColaboraDctoOrdenBean();

                //
                fachadaColaboraDctoOrdenBean = colaboraResurtidoOrden.listaLegalizaCompraFCH();

                //
                fachadaColaboraDctoOrdenBean.setFechaEntrega(strFechaVisita);
                fachadaColaboraDctoOrdenBean.setFechaOrden(strFechaVisita);
                fachadaColaboraDctoOrdenBean.setIdDctoNitCC("");

                //
                request.setAttribute("fachadaColaboraDctoOrdenBean",
                        fachadaColaboraDctoOrdenBean);

                //
                return "/jsp/vtaFrmLegReciboCompra.jsp";

            }

            // Iniciar
            if (accionContenedor.compareTo("Iniciar") == 0) {

                //
                String xDiasHistoria = request.getParameter("xDiasHistoria");
                String xDiasInventario =
                        request.getParameter("xDiasInventario");
                String xIdTercero = request.getParameter("xIdTercero");
                String xFechaCorte = request.getParameter("xFechaCorte");
                String xIdLog = request.getParameter("xIdLog");

                //
                xIdLogActual = new Integer(xIdLog).intValue();

                //
                ProcesoGuardaPlu procesoGuardaPlu = new ProcesoGuardaPlu();


                //
                procesoGuardaPlu.guarda(xIdLogActual,
                        "208",
                        "1",
                        "1",
                        xIdTipoOrdenCompraProceso,
                        xIdUsuario,
                        xIdLocalUsuario,
                        xIdTercero,
                        xFechaCorte,
                        xDiasHistoria,
                        xDiasInventario);

                //
                int estadoSuspendido = 8; // visitaProgramada

                //
                agendaLogVisitaBean.setIdUsuario(xIdUsuario);
                agendaLogVisitaBean.setEstado(estadoSuspendido);

                //
                boolean okActualiza =
                        agendaLogVisitaBean.actualizaLogVisitaUsuario(
                        estadoProgramada);


                //
                FachadaDctoOrdenBean fachadaDctoOrdenBean = new FachadaDctoOrdenBean();

                //
                fachadaDctoOrdenBean.setDiasHistoria(xDiasHistoria);
                fachadaDctoOrdenBean.setDiasInventario(xDiasInventario);
                fachadaDctoOrdenBean.setIdCliente(xIdTercero);
                fachadaDctoOrdenBean.setFechaOrden(xFechaCorte);
                fachadaDctoOrdenBean.setIdLocal(xIdLocalUsuario);
                fachadaDctoOrdenBean.setIdTipoOrden(xIdTipoOrdenCompraProceso);
                fachadaDctoOrdenBean.setIdUsuario(xIdUsuario);
                fachadaDctoOrdenBean.setIdLog(xIdLogActual);
                fachadaDctoOrdenBean.setIdTipoTercero(xIdTipoTerceroProveedor);

                //
                request.setAttribute("fachadaDctoOrdenBean",
                        fachadaDctoOrdenBean);

                //
                return "/jsp/vtaContenedorReciboCompra.jsp";

            }

            // retira
            if (accionContenedor.compareTo("retira") == 0) {

                //
                String xIdLocal = request.getParameter("xIdLocal");
                String xIdTipoOrden = request.getParameter("xIdTipoOrden");
                String xIdLog = request.getParameter("xIdLog");
                String xIdPlu = request.getParameter("xIdPlu");

                //
                DctoOrdenBean dctoOrdenBean = new DctoOrdenBean();

                //
                dctoOrdenBean.setIdLog(xIdLog);

                //
                FachadaDctoOrdenBean fachadaDctoOrdenBean = new FachadaDctoOrdenBean();

                fachadaDctoOrdenBean =
                        dctoOrdenBean.listaDctoOrdenIdLog();

                //
                DctoOrdenDetalleBean dctoOrdenDetalleBean = new DctoOrdenDetalleBean();

                //
                dctoOrdenDetalleBean.setIdLocal(xIdLocal);
                dctoOrdenDetalleBean.setIdTipoOrden(xIdTipoOrden);
                dctoOrdenDetalleBean.setIdLog(xIdLog);
                dctoOrdenDetalleBean.setIdPlu(xIdPlu);


                // retiraArticulosMarcados
                boolean okRetiro = dctoOrdenDetalleBean.retiraPlu();

                // Retira DctoOrden
                dctoOrdenBean.setIdLocal(xIdLocalUsuario);
                dctoOrdenBean.setIdTipoOrden(xIdTipoOrden);
                dctoOrdenBean.setIdOrden(xIdLog);

                // validaArticulosxOrden
                boolean okDetalle = dctoOrdenDetalleBean.validaOrden();

                //
                if (!okDetalle) {

                    // Retira DctoOrden
                    dctoOrdenBean.setIdLocal(xIdLocalUsuario);
                    dctoOrdenBean.setIdTipoOrden(xIdTipoOrden);
                    dctoOrdenBean.setIdOrden(xIdLog);

                    //
                    dctoOrdenBean.retiraOrden();

                    //
                    fachadaDctoOrdenBean.setDiasHistoria(30);
                    fachadaDctoOrdenBean.setDiasInventario(15);
                    fachadaDctoOrdenBean.setIdCliente("0");
                    fachadaDctoOrdenBean.setFechaOrden(strFechaVisita);
                    fachadaDctoOrdenBean.setIdLocal(xIdLocalUsuario);
                    fachadaDctoOrdenBean.setIdTipoOrden(
                            xIdTipoOrdenCompraProceso);
                    fachadaDctoOrdenBean.setIdUsuario(xIdUsuario);
                    fachadaDctoOrdenBean.setIdLog(xIdLogActual);
                    fachadaDctoOrdenBean.setIdTipoTercero(
                            xIdTipoTerceroProveedor);

                    //
                    request.setAttribute("fachadaDctoOrdenBean",
                            fachadaDctoOrdenBean);

                    //
                    return "/jsp/vtaContenedorReciboCompra.jsp";

                }

                //
                FachadaColaboraDctoOrdenBean fachadaColaboraDctoOrdenBean = new FachadaColaboraDctoOrdenBean();

                //
                ColaboraResurtidoOrden colaboraResurtidoOrden = new ColaboraResurtidoOrden();

                //
                colaboraResurtidoOrden.setIdLocal(xIdLocal);
                colaboraResurtidoOrden.setIdTipoOrden(xIdTipoOrden);
                colaboraResurtidoOrden.setIdLog(xIdLog);

                //
                fachadaColaboraDctoOrdenBean = colaboraResurtidoOrden.listaLegalizaCompraFCH();

                //
                fachadaColaboraDctoOrdenBean.setFechaEntrega(strFechaVisita);
                fachadaColaboraDctoOrdenBean.setVrCostoSinIva(0);
                fachadaColaboraDctoOrdenBean.setVrImpoconsumo(0);
                fachadaColaboraDctoOrdenBean.setVrCostoRteFuente(0);
                fachadaColaboraDctoOrdenBean.setVrCostoIva(0);

                //
                request.setAttribute("fachadaColaboraDctoOrdenBean",
                        fachadaColaboraDctoOrdenBean);

                //
                FachadaColaboraDctoOrdenBean fachadaColaboraProveedorBean = new FachadaColaboraDctoOrdenBean();

                //
                fachadaColaboraProveedorBean.setVrCostoSinIva(0);
                fachadaColaboraProveedorBean.setVrImpoconsumo(0);
                fachadaColaboraProveedorBean.setVrCostoRteFuente(0);
                fachadaColaboraProveedorBean.setVrCostoIva(0);
                fachadaColaboraProveedorBean.setFechaEntrega(strFechaVisita);
                fachadaColaboraProveedorBean.setFechaOrden(strFechaVisita);
                fachadaColaboraProveedorBean.setIdDctoNitCC("");

                //
                request.setAttribute("fachadaColaboraProveedorBean",
                        fachadaColaboraProveedorBean);

                //
                return "/jsp/vtaFrmLegReciboCompra.jsp";

            }


            // +Productos
            if (accionContenedor.compareTo("+Productos") == 0) {

                //
                String xIdLinea = request.getParameter("xIdLinea");

                //
                String xIdLocal = request.getParameter("xIdLocal");
                String xIdTipoOrden = request.getParameter("xIdTipoOrden");
                String xIdLog = request.getParameter("xIdLog");

                //
                String strCadena = xIdLinea.trim();
                int lonCadena = strCadena.length();
                int posCadena = strCadena.indexOf('+', 0);
                String xNombrePlu = "";

                //
                if (posCadena > 0) {

                    //
                    xIdLinea = strCadena.substring(0, posCadena).trim();
                    xNombrePlu =
                            strCadena.substring(posCadena + 1, lonCadena).trim();

                } else {

                    //
                    double xIdPlu = 0.0;
                    String strIdPlu = strCadena;

                    try {

                        //
                        xIdPlu = new Double(strIdPlu).doubleValue();


                    } catch (NumberFormatException nfe) {

                        //
                        xNombrePlu = xIdLinea;
                        xIdLinea = "";
                    }
                }

                //
                ColaboraResurtidoOrden colaboraResurtidoOrden = new ColaboraResurtidoOrden();

                //
                colaboraResurtidoOrden.setIdLocal(xIdLocal);
                colaboraResurtidoOrden.setIdTipoOrden(xIdTipoOrden);
                colaboraResurtidoOrden.setIdLog(xIdLog);

                //
                FachadaColaboraDctoOrdenBean fachadaColaboraProveedorBean = new FachadaColaboraDctoOrdenBean();


                //
                FachadaColaboraDctoOrdenBean fachadaColaboraDctoOrdenBean = new FachadaColaboraDctoOrdenBean();

                //
                fachadaColaboraDctoOrdenBean =
                        colaboraResurtidoOrden.listaLegalizaCompraFCH();

                //
                fachadaColaboraDctoOrdenBean.setIdLinea(xIdLinea);
                fachadaColaboraDctoOrdenBean.setNombrePlu(xNombrePlu);

                //
                request.setAttribute("fachadaColaboraDctoOrdenBean",
                        fachadaColaboraDctoOrdenBean);

                //
                return "/jsp/vtaFrmAdiReciboCompra.jsp";

            }

            // Eliminar
            if (accionContenedor.compareTo("Eliminar") == 0) {

                //
                String xIdLocal = request.getParameter("xIdLocal");
                String xIdOrden = request.getParameter("xIdOrden");
                String xIdTipoOrden = request.getParameter("xIdTipoOrden");
                String xIdLog = request.getParameter("xIdLog");

                //
                int xIdEstadoTxSinTx = 1;
                int tareaVisita = 6;
                int estadoTerminado = 1;

                // finalizaVisita
                agendaLogVisitaBean.setIdLog(xIdLog);
                agendaLogVisitaBean.setIdCliente(xIdUsuario);
                agendaLogVisitaBean.setIdUsuario(xIdUsuario);
                agendaLogVisitaBean.setIdTipoOrden(xIdTipoOrden);
                agendaLogVisitaBean.setIdEstadoTx(xIdEstadoTxSinTx);
                agendaLogVisitaBean.setIdLocal(xIdLocal);
                agendaLogVisitaBean.setFechaVisitaStr(strFechaVisita);
                agendaLogVisitaBean.setIdEstadoVisita(tareaVisita);
                agendaLogVisitaBean.setEstado(estadoTerminado);

                //
                ProcesoIp procesoIp = new ProcesoIp();

                //
                String ipTx = procesoIp.getIpTx(request);

                //
                agendaLogVisitaBean.setIpTx(ipTx);
                agendaLogVisitaBean.setFechaTx(fechaTxHM);

                //
                boolean okLog = agendaLogVisitaBean.finalizaVisita();


            }

            // Imprimir
            if (accionContenedor.compareTo("Imprimir") == 0) {

                //
                String xIdLocal = request.getParameter("xIdLocal");
                String xIdOrden = request.getParameter("xIdOrden");
                String xIdTipoOrden = request.getParameter("xIdTipoOrden");
                String xIdLog = request.getParameter("xIdLog");

                //
                GeneraPDFResurtidoCompraSolicitado generaPDFResurtidoSolicitado =
                        new GeneraPDFResurtidoCompraSolicitado();

                //
                generaPDFResurtidoSolicitado.setIdLocal(xIdLocal);
                generaPDFResurtidoSolicitado.setIdOrden(xIdOrden);
                generaPDFResurtidoSolicitado.setIdTipoOrden(xIdTipoOrden);
                generaPDFResurtidoSolicitado.setIdLog(xIdLog);
                generaPDFResurtidoSolicitado.setReporteName(
                        "RepEmpresaResurtidoCompraSolicitada");

                //
                generaPDFResurtidoSolicitado.setTituloReporte(
                        "COMPRA SOLICITADA");

                //
                generaPDFResurtidoSolicitado.generaPdf(request, response);

            }

            // tomar
            if (accionContenedor.compareTo("tomar") == 0) {

                //
                String xIdLocal = request.getParameter("xIdLocal");
                String xIdOrden = request.getParameter("xIdOrden");
                String xIdTipoOrden = request.getParameter("xIdTipoOrden");
                String xIdLog = request.getParameter("xIdLog");

                //
                GeneraPDFResurtidoCompraLegalizado generaPDF = new GeneraPDFResurtidoCompraLegalizado();

                //
                generaPDF.setIdLocal(xIdLocal);
                generaPDF.setIdOrden(xIdOrden);
                generaPDF.setIdTipoOrden(xIdTipoOrden);
                generaPDF.setIdLog(xIdLog);
                generaPDF.setTituloReporte("COMPRA CUMPLIDA");
                generaPDF.setReporteName("RepEmpresaResurtidoCompraLegalizado");

                //
                generaPDF.generaPdf(request, response);

            }

            //
            if (accionContenedor.compareTo("Legalizar") == 0) {

                //--------------------------------------------------------------
                String xIdLocal = request.getParameter("xIdLocal");
                String xIdTipoOrden = request.getParameter("xIdTipoOrden");
                String xIdLog = request.getParameter("xIdLog");

                //
                String xIdPluArr[] = request.getParameterValues("xIdPlu");
                String xCantidadArr[] =
                        request.getParameterValues("xCantidad");

                //--------------------------------------------------------------
                String xFechaDctoNitCC =
                        request.getParameter("xFechaDcto");
                String xFechaDcto =
                        request.getParameter("xFechaDcto");
                String xIdDctoNitCC = request.getParameter("xIdDctoNitCC");

                //
                Validacion valida = new Validacion();

                //
                valida.reasignar("FECHA FACTURA", xFechaDctoNitCC);

                //
                valida.validarCampoFecha();

                //isValido
                if (!valida.isValido()) {

                    //
                    request.setAttribute("validacion", valida);
                    return "/jsp/gralError.jsp";
                }

                //
                valida.reasignar("FECHA RECIBO", xFechaDcto);

                //
                valida.validarCampoFecha();

                //isValido
                if (!valida.isValido()) {

                    //
                    request.setAttribute("validacion", valida);
                    return "/jsp/gralError.jsp";
                }

                //
                valida.reasignar("NUMERO FACTURA", xIdDctoNitCC);

                //
                valida.validarCampoString();

                //isValido
                if (!valida.isValido()) {

                    //
                    request.setAttribute("validacion", valida);
                    return "/jsp/gralError.jsp";
                }

                //------------------
                for (int indice = 0; indice < xCantidadArr.length; indice++) {

                    //
                    valida.reasignar("CAN.REC", xCantidadArr[indice]);

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
                double xCero = 0.0;

                //
                DctoOrdenDetalleBean dctoOrdenDetalleBean
                                                   = new DctoOrdenDetalleBean();

                //
                dctoOrdenDetalleBean.borraCompraRecibo();

                //
                for (int indice = 0; indice < xCantidadArr.length; indice++) {

                    //----------------------------------------------------------
                    dctoOrdenDetalleBean.setIdLocal(xIdLocal);
                    dctoOrdenDetalleBean.setIdTipoOrden(xIdTipoOrden);
                    dctoOrdenDetalleBean.setIdLog(xIdLog);
                    dctoOrdenDetalleBean.setCantidad(xCantidadArr[indice]);
                    dctoOrdenDetalleBean.setIdPlu(xIdPluArr[indice]);
                    dctoOrdenDetalleBean.setVrCostoResurtido(xCero);
                    dctoOrdenDetalleBean.setVrIvaResurtido(xCero);
                    dctoOrdenDetalleBean.setVrImpoconsumo(xCero);

                    //
                    if (dctoOrdenDetalleBean.getCantidad() != 0) {

                        //
                        dctoOrdenDetalleBean.actualizaReciboCompra();

                    }
                }

                //
                ColaboraOrdenDetalleBean colaboraOrdenDetalleBean
                                               = new ColaboraOrdenDetalleBean();

                //
                FachadaDctoOrdenDetalleBean fachadaDctoOrdenDetalleBean
                                            = new FachadaDctoOrdenDetalleBean();

                //
                colaboraOrdenDetalleBean.setIdLocal(xIdLocal);
                colaboraOrdenDetalleBean.setIdTipoOrden(xIdTipoOrden);
                colaboraOrdenDetalleBean.setIdLog(xIdLog);

                //
                fachadaDctoOrdenDetalleBean =
                        colaboraOrdenDetalleBean.liquidaOrdenFCH();

                // --- verifica documento existente
                FachadaDctoBean fachadaDctoBean = new FachadaDctoBean();

                //
                DctoBean dctoBean = new DctoBean();

                //
                dctoBean.setIdLocal(xIdLocal);
                dctoBean.setIdTipoOrden(xIdTipoOrdenCompra);
                dctoBean.setIdDctoNitCC(xIdDctoNitCC.trim());
                dctoBean.setIdCliente(
                        fachadaDctoOrdenDetalleBean.getIdCliente());

                //
                fachadaDctoBean = dctoBean.listaUnIdDctoNitCC();

                //
                if (fachadaDctoBean.getIdDcto() > 0) {

                    //
                    valida.reasignar("ERROR, FACTURA YA EXISTE PARA EL PROVEEDOR",
                            "");

                    //
                    request.setAttribute("validacion", valida);
                    return "/jsp/gralError.jsp";

                }


                //
                int xIdTipoTerceroCliente = 2;

                //
                ColaboraTercero colaboraTercero = new ColaboraTercero();

                //
                colaboraTercero.setIdCliente(
                        fachadaDctoOrdenDetalleBean.getIdCliente());
                colaboraTercero.setIdTipoTercero(xIdTipoTerceroCliente);

                //
                FachadaTerceroBean fachadaTerceroBean = new FachadaTerceroBean();
                //
                fachadaTerceroBean =
                        colaboraTercero.listaTerceroFCH();

                //
                ColaboraResurtidoOrden colaboraResurtidoOrden
                                                 = new ColaboraResurtidoOrden();

                //
                colaboraResurtidoOrden.setIdLocal(xIdLocal);
                colaboraResurtidoOrden.setIdTipoOrden(xIdTipoOrden);
                colaboraResurtidoOrden.setIdLog(xIdLog);

                //
                FachadaColaboraDctoOrdenBean fachadaColaboraProveedorBean
                                           = new FachadaColaboraDctoOrdenBean();

                //
                FachadaDctoOrdenDetalleBean fachadaDctoOrdenProveedorBean
                                            = new FachadaDctoOrdenDetalleBean();

                //
                int xIdConceptoRFCompra = 1;

                //
                colaboraOrdenDetalleBean.setIdLocal(xIdLocal);
                colaboraOrdenDetalleBean.setIdTipoOrden(xIdTipoOrden);
                colaboraOrdenDetalleBean.setIdLog(xIdLog);

                //
                fachadaDctoOrdenProveedorBean =
                            colaboraOrdenDetalleBean.liquidaOrdenProveedorFCH();

                //--------------------------------------------------------------
                ContableRetencionBean contableRetencionBean = new ContableRetencionBean();
                //
                double xVrRetencion =0.0;;

                //
                fachadaColaboraProveedorBean.setVrCostoSinIva(xCero);
                fachadaColaboraProveedorBean.setVrImpoconsumo(xCero);
                fachadaColaboraProveedorBean.setVrCostoRteFuente(xVrRetencion);
                fachadaColaboraProveedorBean.setVrCostoIva(xCero);
                fachadaColaboraProveedorBean.setFechaEntrega(xFechaDctoNitCC);
                fachadaColaboraProveedorBean.setFechaOrden(xFechaDcto);
                fachadaColaboraProveedorBean.setIdDctoNitCC(xIdDctoNitCC);

                //
                FachadaColaboraDctoOrdenBean fachadaColaboraDctoOrdenBean
                                           = new FachadaColaboraDctoOrdenBean();

                //
                colaboraResurtidoOrden.setIdLocal(xIdLocal);
                colaboraResurtidoOrden.setIdTipoOrden(xIdTipoOrden);
                colaboraResurtidoOrden.setIdLog(xIdLog);

                //
                fachadaColaboraDctoOrdenBean =
                                colaboraResurtidoOrden.listaLegalizaCompraFCH();

                //
                fachadaColaboraDctoOrdenBean.setIdLocal(xIdLocal);
                fachadaColaboraDctoOrdenBean.setIdTipoOrden(xIdTipoOrden);
                fachadaColaboraDctoOrdenBean.setIdLog(xIdLog);
                fachadaColaboraDctoOrdenBean.setFechaEntrega(xFechaDctoNitCC);
                fachadaColaboraDctoOrdenBean.setFechaOrden(xFechaDcto);
                fachadaColaboraDctoOrdenBean.setVrCostoSinIva(xCero);
                fachadaColaboraDctoOrdenBean.setVrImpoconsumo(xCero);
                fachadaColaboraDctoOrdenBean.setVrDescuento(0);
                fachadaColaboraDctoOrdenBean.setVrCostoRteFuente(xCero);
                fachadaColaboraDctoOrdenBean.setVrCostoIva(xCero);
                fachadaColaboraDctoOrdenBean.setIdDctoNitCC(xIdDctoNitCC);

                //--------------------------------------------------------------
                fachadaColaboraDctoOrdenBean.setVrDiferencia(
                        fachadaColaboraDctoOrdenBean.getVrPagarFactura()
                        - fachadaColaboraProveedorBean.getVrPagarFactura());


                // confirmaPedido
                //ProcesoIngresoResurtidoCompra proceso = new ProcesoIngresoResurtidoCompra();


                ProcesoReciboCompra proceso = new ProcesoReciboCompra();

                //
                proceso.ingresa(
                        new Integer(xIdLocal).intValue(),
                        xIdTipoOrdenCompra,
                        new Integer(xIdLog).intValue(),
                        xIdTipoOrdenCompraProceso);

                //
                int xIdEstadoTxSinTx = 1;
                int tareaVisita = 6;
                int xEstadoProceso = 8;

                // finalizaVisita
                agendaLogVisitaBean.setIdLog(xIdLog);
                agendaLogVisitaBean.setIdCliente(xIdUsuario);
                agendaLogVisitaBean.setIdUsuario(xIdUsuario);
                agendaLogVisitaBean.setIdTipoOrden(
                        fachadaDctoBean.getIdTipoOrdenStr());
                agendaLogVisitaBean.setIdEstadoTx(xIdEstadoTxSinTx);
                agendaLogVisitaBean.setIdLocal(fachadaDctoBean.getIdLocalStr());
                agendaLogVisitaBean.setFechaVisitaStr(strFechaVisita);
                agendaLogVisitaBean.setIdEstadoVisita(tareaVisita);
                agendaLogVisitaBean.setEstado(xEstadoProceso);

                //
                ProcesoIp procesoIp = new ProcesoIp();

                //
                String ipTx = procesoIp.getIpTx(request);

                //
                agendaLogVisitaBean.setIpTx(ipTx);
                agendaLogVisitaBean.setFechaTx(fechaTxHM);

                //
                boolean okLog = agendaLogVisitaBean.finalizaVisita();

                /*
                GeneraPDFResurtidoCompraLegalizado generaPDF = new GeneraPDFResurtidoCompraLegalizado();

                //
                generaPDF.setIdLocal(xIdLocalUsuario);
                generaPDF.setIdOrden(fachadaDctoBean.getIdOrden());
                generaPDF.setIdTipoOrden(fachadaDctoBean.getIdTipoOrden());
                generaPDF.setTituloReporte("COMPRA CUMPLIDA");
                generaPDF.setReporteName("RepEmpresaResurtidoCompraLegalizado");

                //
                generaPDF.generaPdf(request, response);*/

            }

            // Liquidar
            if (accionContenedor.compareTo("Liquidar") == 0) {

                //--------------------------------------------------------------
                String xIdLocal = request.getParameter("xIdLocal");
                String xIdTipoOrden = request.getParameter("xIdTipoOrden");
                String xIdLog = request.getParameter("xIdLog");

                //
                String xIdPluArr[] = request.getParameterValues("xIdPlu");
                String xCantidadArr[] =
                        request.getParameterValues("xCantidad");

                //--------------------------------------------------------------
                String xVrBase = "0";
                String xVrImpoconsumo = "0";
                String xVrDescuento = "0";
                String xVrIva = "0";
                String xVrRteFuente = "0";

                //
                String xFechaDctoNitCC = request.getParameter("xFechaDcto");
                String xFechaDcto =
                        request.getParameter("xFechaDcto");
                String xIdDctoNitCC = request.getParameter("xIdDctoNitCC");

                //
                Validacion valida = new Validacion();

                //
                valida.reasignar("FECHA RECIBO ", xFechaDcto);

                //
                valida.validarCampoFecha();

                //isValido
                if (!valida.isValido()) {

                    //
                    request.setAttribute("validacion", valida);
                    return "/jsp/gralError.jsp";
                }

                //
                valida.reasignar("FECHA FACTURA", xFechaDctoNitCC);

                //
                valida.validarCampoFecha();

                //isValido
                if (!valida.isValido()) {

                    //
                    request.setAttribute("validacion", valida);
                    return "/jsp/gralError.jsp";
                }

                //
                valida.reasignar("NUMERO FACTURA", xIdDctoNitCC);

                //
                valida.validarCampoString();

                //isValido
                if (!valida.isValido()) {

                    //
                    request.setAttribute("validacion", valida);
                    return "/jsp/gralError.jsp";
                }

                //
                valida.reasignar("VR.BASE", xVrBase);

                //
                valida.validarCampoDoublePositivo();

                //isValido
                if (!valida.isValido()) {

                    //
                    request.setAttribute("validacion", valida);
                    return "/jsp/gralError.jsp";
                }

                //
                valida.reasignar("V.IMPOCO", xVrImpoconsumo);

                //
                valida.validarCampoDoublePositivo();

                //isValido
                if (!valida.isValido()) {

                    //
                    request.setAttribute("validacion", valida);
                    return "/jsp/gralError.jsp";
                }

                //
                valida.reasignar("VR.DESCUENTO", xVrDescuento);

                //
                valida.validarCampoDoublePositivo();

                //isValido
                if (!valida.isValido()) {

                    //
                    request.setAttribute("validacion", valida);
                    return "/jsp/gralError.jsp";
                }


                //
                valida.reasignar("VR.IVA", xVrIva);

                //
                valida.validarCampoDoublePositivo();

                //isValido
                if (!valida.isValido()) {

                    //
                    request.setAttribute("validacion", valida);
                    return "/jsp/gralError.jsp";
                }

                //
                valida.reasignar("VR.RFTE", xVrRteFuente);

                //
                valida.validarCampoDoublePositivo();

                //isValido
                if (!valida.isValido()) {

                    //
                    request.setAttribute("validacion", valida);
                    return "/jsp/gralError.jsp";
                }

                //------------------
                for (int indice = 0; indice < xCantidadArr.length; indice++) {

                    //
                    valida.reasignar("CAN.REC", xCantidadArr[indice]);

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
                double xCero = 0.0;

                //
                DctoOrdenDetalleBean dctoOrdenDetalleBean = new DctoOrdenDetalleBean();

                //
                dctoOrdenDetalleBean.setIdLocal(xIdLocal);
                dctoOrdenDetalleBean.setIdTipoOrden(xIdTipoOrden);
                dctoOrdenDetalleBean.setIdLog(xIdLog);

                //
                dctoOrdenDetalleBean.borraCompraRecibo();

                //
                for (int indice = 0; indice < xCantidadArr.length; indice++) {

                    //
                    dctoOrdenDetalleBean.setIdLocal(xIdLocal);
                    dctoOrdenDetalleBean.setIdTipoOrden(xIdTipoOrden);
                    dctoOrdenDetalleBean.setIdLog(xIdLog);
                    dctoOrdenDetalleBean.setCantidad(xCantidadArr[indice]);
                    dctoOrdenDetalleBean.setIdPlu(xIdPluArr[indice]);
                    dctoOrdenDetalleBean.setVrCostoResurtido(xCero);
                    dctoOrdenDetalleBean.setVrIvaResurtido(xCero);
                    dctoOrdenDetalleBean.setVrImpoconsumo(xCero);


                    //
                    if (dctoOrdenDetalleBean.getCantidad() != 0) {

                        //
                        dctoOrdenDetalleBean.actualizaReciboCompra();

                    }
                }

                //
                ColaboraOrdenDetalleBean colaboraOrdenDetalleBean = new ColaboraOrdenDetalleBean();

                //
                FachadaDctoOrdenDetalleBean fachadaDctoOrdenDetalleBean = new FachadaDctoOrdenDetalleBean();

                //
                colaboraOrdenDetalleBean.setIdLocal(xIdLocal);
                colaboraOrdenDetalleBean.setIdTipoOrden(xIdTipoOrden);
                colaboraOrdenDetalleBean.setIdLog(xIdLog);

                //
                fachadaDctoOrdenDetalleBean =
                        colaboraOrdenDetalleBean.liquidaOrdenFCH();

                // --- verifica documento existente
                FachadaDctoBean fachadaDctoBean = new FachadaDctoBean();

                //
                DctoBean dctoBean = new DctoBean();

                //
                dctoBean.setIdLocal(xIdLocal);
                dctoBean.setIdTipoOrden(xIdTipoOrdenCompra);
                dctoBean.setIdDctoNitCC(xIdDctoNitCC.trim());
                dctoBean.setIdCliente(
                        fachadaDctoOrdenDetalleBean.getIdCliente());

                //
                fachadaDctoBean = dctoBean.listaUnIdDctoNitCC();

                //
                if (fachadaDctoBean.getIdDcto() > 0) {

                    //
                    valida.reasignar("ERROR, FACTURA YA EXISTE PARA EL PROVEEDOR",
                            "");

                    //
                    request.setAttribute("validacion", valida);
                    return "/jsp/gralError.jsp";

                }

                // --- liquidacion
                int xIdTipoTerceroCliente = 2;

                //
                ColaboraTercero colaboraTercero = new ColaboraTercero();

                //
                colaboraTercero.setIdCliente(
                        fachadaDctoOrdenDetalleBean.getIdCliente());
                colaboraTercero.setIdTipoTercero(xIdTipoTerceroCliente);

                //
                FachadaTerceroBean fachadaTerceroBean = new FachadaTerceroBean();
                //
                fachadaTerceroBean =
                        colaboraTercero.listaTerceroFCH();

                //--------------------------------------------------------------
                FachadaColaboraDctoOrdenBean fachadaColaboraProveedorBean = new FachadaColaboraDctoOrdenBean();

                //
                FachadaDctoOrdenDetalleBean fachadaDctoOrdenProveedorBean = new FachadaDctoOrdenDetalleBean();

                //
                colaboraOrdenDetalleBean.setIdLocal(xIdLocal);
                colaboraOrdenDetalleBean.setIdTipoOrden(xIdTipoOrden);
                colaboraOrdenDetalleBean.setIdLog(xIdLog);

                //
                fachadaDctoOrdenProveedorBean = colaboraOrdenDetalleBean.liquidaOrdenProveedorFCH();

                //--------------------------------------------------------------
                int xIdConceptoRFCompra = 1;

                //
                ContableRetencionBean contableRetencionBean = new ContableRetencionBean();

                //
                int xIdRteFuenteVrBase      = 0;


                //
                double xVrRetencion =
                        contableRetencionBean.calculaRetencion(
                        fachadaTerceroBean.getIdAutoRetenedor(),
                        xIdConceptoRFCompra,
                        fachadaDctoOrdenProveedorBean.getVrCostoResurtido(),
                        xIdRteFuenteVrBase);

                //
                fachadaColaboraProveedorBean.setVrCostoSinIva(
                        fachadaDctoOrdenProveedorBean.getVrCostoResurtido());
                fachadaColaboraProveedorBean.setVrImpoconsumo(
                        fachadaDctoOrdenProveedorBean.getVrImpoconsumo());
                fachadaColaboraProveedorBean.setVrCostoRteFuente(xVrRetencion);
                fachadaColaboraProveedorBean.setVrCostoIva(
                        fachadaDctoOrdenProveedorBean.getVrIvaResurtido());
                fachadaColaboraProveedorBean.setFechaEntrega(strFechaVisita);
                fachadaColaboraProveedorBean.setFechaOrden(strFechaVisita);
                fachadaColaboraProveedorBean.setIdDctoNitCC(xIdDctoNitCC);

                //
                request.setAttribute("fachadaColaboraProveedorBean",
                        fachadaColaboraProveedorBean);

                //
                FachadaColaboraDctoOrdenBean fachadaColaboraDctoOrdenBean = new FachadaColaboraDctoOrdenBean();
                //
                ColaboraResurtidoOrden colaboraResurtidoOrden = new ColaboraResurtidoOrden();

                //
                colaboraResurtidoOrden.setIdLocal(xIdLocal);
                colaboraResurtidoOrden.setIdTipoOrden(xIdTipoOrden);
                colaboraResurtidoOrden.setIdLog(xIdLog);

                //
                fachadaColaboraDctoOrdenBean = colaboraResurtidoOrden.listaLegalizaCompraFCH();

                //
                fachadaColaboraDctoOrdenBean.setIdLocal(xIdLocal);
                fachadaColaboraDctoOrdenBean.setIdTipoOrden(xIdTipoOrden);
                fachadaColaboraDctoOrdenBean.setIdLog(xIdLog);
                fachadaColaboraDctoOrdenBean.setFechaEntrega(xFechaDctoNitCC);
                fachadaColaboraDctoOrdenBean.setFechaOrden(xFechaDcto);
                fachadaColaboraDctoOrdenBean.setVrCostoSinIva(xVrBase);
                fachadaColaboraDctoOrdenBean.setVrImpoconsumo(xVrImpoconsumo);
                fachadaColaboraDctoOrdenBean.setVrDescuento(0);
                fachadaColaboraDctoOrdenBean.setVrCostoRteFuente(xVrRetencion);
                fachadaColaboraDctoOrdenBean.setVrCostoIva(xVrIva);
                fachadaColaboraDctoOrdenBean.setIdDctoNitCC(xIdDctoNitCC);

                //--------------------------------------------------------------
                fachadaColaboraDctoOrdenBean.setVrDiferencia(
                        fachadaColaboraDctoOrdenBean.getVrPagarFactura()
                        - fachadaColaboraProveedorBean.getVrPagarFactura());

                //
                fachadaColaboraDctoOrdenBean.setVrDiferenciaVrCostoSinIva(
                        fachadaColaboraDctoOrdenBean.getVrCostoSinIva()
                        - fachadaColaboraProveedorBean.getVrCostoSinIva());
                //
                fachadaColaboraDctoOrdenBean.setVrDiferenciaVrImpoconsumo(
                        fachadaColaboraDctoOrdenBean.getVrImpoconsumo()
                        - fachadaColaboraProveedorBean.getVrImpoconsumo());
                //
                fachadaColaboraDctoOrdenBean.setVrDiferenciaVrCostoIva(
                        fachadaColaboraDctoOrdenBean.getVrCostoIva()
                        - fachadaColaboraProveedorBean.getVrCostoIva());

                fachadaColaboraDctoOrdenBean.setVrDiferenciaVrRteFuente(
                        fachadaColaboraDctoOrdenBean.getVrCostoRteFuente()
                        - fachadaColaboraProveedorBean.getVrCostoRteFuente());

                //--------------------------------------------------------------
                fachadaColaboraProveedorBean.setVrCostoSinIva(
                        fachadaDctoOrdenProveedorBean.getVrCostoResurtido());

                fachadaColaboraProveedorBean.setVrImpoconsumo(
                        fachadaDctoOrdenProveedorBean.getVrImpoconsumo());

                fachadaColaboraProveedorBean.setVrCostoRteFuente(xVrRteFuente);
                fachadaColaboraProveedorBean.setVrCostoIva(
                        fachadaDctoOrdenProveedorBean.getVrIvaResurtido());

                fachadaColaboraProveedorBean.setFechaEntrega(strFechaVisita);
                fachadaColaboraProveedorBean.setFechaOrden(strFechaVisita);
                fachadaColaboraProveedorBean.setIdDctoNitCC(xIdDctoNitCC);

                //
                request.setAttribute("fachadaColaboraDctoOrdenBean",
                        fachadaColaboraDctoOrdenBean);

                //
                return "/jsp/vtaFrmLegReciboCompra.jsp";

            }

            //
            if (accionContenedor.compareTo("coger") == 0) {

                //
                String xIdLocal = request.getParameter("xIdLocal");
                String xIdTipoOrden = request.getParameter("xIdTipoOrden");
                String xIdLog = request.getParameter("xIdLog");

                //
                FachadaColaboraDctoOrdenBean fachadaColaboraDctoOrdenBean = new FachadaColaboraDctoOrdenBean();

                //
                ColaboraResurtidoOrden colaboraResurtidoOrden = new ColaboraResurtidoOrden();

                //
                colaboraResurtidoOrden.setIdLocal(xIdLocal);
                colaboraResurtidoOrden.setIdTipoOrden(xIdTipoOrden);
                colaboraResurtidoOrden.setIdLog(xIdLog);

                //
                fachadaColaboraDctoOrdenBean = colaboraResurtidoOrden.listaLegalizaCompraFCH();

                //
                fachadaColaboraDctoOrdenBean.setFechaEntrega(strFechaVisita);

                //
                request.setAttribute("fachadaColaboraDctoOrdenBean",
                        fachadaColaboraDctoOrdenBean);

                //
                ColaboraOrdenDetalleBean colaboraOrdenDetalleBean = new ColaboraOrdenDetalleBean();

                //
                FachadaDctoOrdenDetalleBean fachadaDctoOrdenDetalleBean = new FachadaDctoOrdenDetalleBean();

                //
                colaboraOrdenDetalleBean.setIdLocal(xIdLocal);
                colaboraOrdenDetalleBean.setIdTipoOrden(xIdTipoOrden);
                colaboraOrdenDetalleBean.setIdLog(xIdLog);

                //
                fachadaDctoOrdenDetalleBean =
                        colaboraOrdenDetalleBean.liquidaOrdenFCH();
                //
                int xIdTipoTerceroCliente = 2;

                //
                ColaboraTercero colaboraTercero = new ColaboraTercero();

                //
                colaboraTercero.setIdCliente(
                        fachadaDctoOrdenDetalleBean.getIdCliente());
                colaboraTercero.setIdTipoTercero(xIdTipoTerceroCliente);

                //
                FachadaTerceroBean fachadaTerceroBean = new FachadaTerceroBean();
                //
                fachadaTerceroBean =
                        colaboraTercero.listaTerceroFCH();

                //
                int xIdConceptoRFCompra = 1;
                int xIdRteFuenteVrBase      = 0;

                //
                ContableRetencionBean contableRetencionBean = new ContableRetencionBean();

                //
                double xVrRetencion =
                        contableRetencionBean.calculaRetencion(
                        fachadaTerceroBean.getIdAutoRetenedor(),
                        xIdConceptoRFCompra,
                        fachadaDctoOrdenDetalleBean.getVrCostoSinIva(),
                        xIdRteFuenteVrBase);

                //
                colaboraResurtidoOrden.setIdLocal(xIdLocal);
                colaboraResurtidoOrden.setIdTipoOrden(xIdTipoOrden);
                colaboraResurtidoOrden.setIdLog(xIdLog);

                //
                fachadaColaboraDctoOrdenBean = colaboraResurtidoOrden.listaLegalizaCompraFCH();

                //
                fachadaColaboraDctoOrdenBean.setFechaEntrega(strFechaVisita);
                fachadaColaboraDctoOrdenBean.setVrCostoSinIva(0);
                fachadaColaboraDctoOrdenBean.setVrImpoconsumo(0);
                fachadaColaboraDctoOrdenBean.setVrCostoRteFuente(0);
                fachadaColaboraDctoOrdenBean.setVrCostoIva(0);
                fachadaColaboraDctoOrdenBean.setIdDctoNitCC("");

                //
                fachadaColaboraDctoOrdenBean.setNombreTercero(
                        fachadaColaboraDctoOrdenBean.getNombreTercero());
                fachadaColaboraDctoOrdenBean.setIdOrden(
                        fachadaColaboraDctoOrdenBean.getIdOrden());

                fachadaColaboraDctoOrdenBean.setFechaOrden(
                        fachadaColaboraDctoOrdenBean.getFechaOrden());
                fachadaColaboraDctoOrdenBean.setCantidadArticulos(
                        fachadaColaboraDctoOrdenBean.getCantidadArticulos());

                //
                request.setAttribute("fachadaColaboraDctoOrdenBean",
                        fachadaColaboraDctoOrdenBean);

                // ---
                FachadaColaboraDctoOrdenBean fachadaColaboraProveedorBean = new FachadaColaboraDctoOrdenBean();

                //--------------------------------------------------------------
                FachadaDctoOrdenDetalleBean fachadaDctoOrdenProveedorBean = new FachadaDctoOrdenDetalleBean();

                //
                colaboraOrdenDetalleBean.setIdLocal(xIdLocal);
                colaboraOrdenDetalleBean.setIdTipoOrden(xIdTipoOrden);
                colaboraOrdenDetalleBean.setIdLog(xIdLog);

                //
                fachadaDctoOrdenProveedorBean = colaboraOrdenDetalleBean.liquidaOrdenProveedorFCH();

                //
                fachadaColaboraProveedorBean.setVrCostoSinIva(
                        fachadaDctoOrdenProveedorBean.getVrCostoResurtido());
                fachadaColaboraProveedorBean.setVrImpoconsumo(
                        fachadaDctoOrdenProveedorBean.getVrImpoconsumo());
                fachadaColaboraProveedorBean.setVrCostoRteFuente(0);
                fachadaColaboraProveedorBean.setVrCostoIva(
                        fachadaDctoOrdenProveedorBean.getVrIvaResurtido());
                fachadaColaboraProveedorBean.setFechaEntrega(strFechaVisita);
                fachadaColaboraProveedorBean.setFechaOrden(strFechaVisita);
                fachadaColaboraProveedorBean.setIdDctoNitCC("");

                //
                request.setAttribute("fachadaColaboraProveedorBean",
                        fachadaColaboraProveedorBean);

                //
                return "/jsp/vtaFrmLegReciboCompra.jsp";


            }

            // Traer
            if (accionContenedor.compareTo("Traer") == 0) {

                //
                String xDiasHistoria = "0";
                String xDiasInventario = "0";
                String xIdTercero = request.getParameter("xIdTercero");
                String xFechaCorte = strFechaVisita;



                // validacion
                Validacion validacion = new Validacion();

                //
                validacion.reasignar("PROVEEDOR", xIdTercero);

                //
                validacion.validarCampoDouble();

                // isValido
                if (!validacion.isValido()) {

                    //
                    validacion.setDescripcionError("ERROR, FALTA SELECCIONAR PROVEEDOR");

                    //
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralError.jsp";

                }

                //
                validacion.reasignar("FECHA CORTE", xFechaCorte);

                validacion.validarCampoFecha();

                // isValido
                if (!validacion.isValido()) {

                    //
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralError.jsp";

                }

                //
                TerceroBean terceroBean = new TerceroBean();

                //
                FachadaTerceroBean fachadaTerceroBean = new FachadaTerceroBean();

                //
                terceroBean.setIdCliente(xIdTercero);
                terceroBean.setIdTipoTercero(xIdTipoTerceroProveedor);

                //
                fachadaTerceroBean = terceroBean.listaUnTerceroFCH();

                request.setAttribute("fachadaTerceroBean", fachadaTerceroBean);


                //
                FachadaDctoOrdenBean fachadaDctoOrdenBean = new FachadaDctoOrdenBean();

                //
                fachadaDctoOrdenBean.setDiasHistoria(xDiasHistoria);
                fachadaDctoOrdenBean.setDiasInventario(xDiasInventario);
                fachadaDctoOrdenBean.setIdCliente(xIdTercero);
                fachadaDctoOrdenBean.setFechaOrden(xFechaCorte);
                fachadaDctoOrdenBean.setIdLocal(xIdLocalUsuario);
                fachadaDctoOrdenBean.setIdTipoOrden(xIdTipoOrdenCompraProceso);
                fachadaDctoOrdenBean.setIdUsuario(xIdUsuario);
                fachadaDctoOrdenBean.setIdLog(xIdLogActual);

                //
                request.setAttribute("fachadaDctoOrdenBean",
                        fachadaDctoOrdenBean);

                //
                return "/jsp/vtaFrmLstReciboCompra.jsp";

            }

            // Guardar
            if (accionContenedor.compareTo("Guardar") == 0) {

                //
                String xDiasHistoria = request.getParameter("xDiasHistoria");
                String xDiasInventario =
                        request.getParameter("xDiasInventario");
                String xIdTercero = request.getParameter("xIdTercero");
                String xFechaCorte = request.getParameter("xFechaCorte");
                String xIdLocal = request.getParameter("xIdLocal");
                String xIdTipoOrden = request.getParameter("xIdTipoOrden");
                String xIdPluArr[] = request.getParameterValues("xIdPlu");
                String xCantidadPedidaArr[] =
                        request.getParameterValues("xCantidadPedido");
                String xCostoPedidoArr[] =
                        request.getParameterValues("xCostoPedido");
                String xIdLog = request.getParameter("xIdLog");

                System.out.println(xIdTercero);


                // validacion
                Validacion validacion = new Validacion();


                //
                for (int indice = 0; indice
                        < xCantidadPedidaArr.length; indice++) {

                    //
                    validacion.reasignar("CANTIDAD PEDIDA",
                            xCantidadPedidaArr[indice]);

                    //
                    validacion.validarCampoDoublePositivo();

                    // isValido
                    if (!validacion.isValido()) {

                        //
                        request.setAttribute("validacion", validacion);
                        return "/jsp/gralError.jsp";

                    }

                    //
                    validacion.reasignar("COSTO NEGOCIADO",
                            xCostoPedidoArr[indice]);

                    //
                    validacion.validarCampoDoublePositivo();

                    // isValido
                    if (!validacion.isValido()) {

                        //
                        request.setAttribute("validacion", validacion);
                        return "/jsp/gralError.jsp";

                    }

                }

                //
                xIdLogActual = new Integer(xIdLog).intValue();

                //
                ProcesoGuardaPlu procesoGuardaPlu = new ProcesoGuardaPlu();

                //
                for (int indice = 0; indice
                        < xCantidadPedidaArr.length; indice++) {

                    //
                    if (new Double(xCantidadPedidaArr[indice]).doubleValue() > 0) {

                        //
                        procesoGuardaPlu.guarda(xIdLogActual,
                                xIdPluArr[indice],
                                xCantidadPedidaArr[indice],
                                xCostoPedidoArr[indice],
                                xIdTipoOrdenCompraProceso,
                                xIdUsuario,
                                xIdLocalUsuario,
                                xIdTercero,
                                xFechaCorte,
                                xDiasHistoria,
                                xDiasInventario);
                    }
                }

                //
                int estadoSuspendido = 8; // visitaProgramada

                //
                agendaLogVisitaBean.setIdUsuario(xIdUsuario);
                agendaLogVisitaBean.setEstado(estadoSuspendido);

                //
                boolean okActualiza =
                        agendaLogVisitaBean.actualizaLogVisitaUsuario(
                        estadoProgramada);


                //
                FachadaDctoOrdenBean fachadaDctoOrdenBean = new FachadaDctoOrdenBean();

                //
                fachadaDctoOrdenBean.setDiasHistoria(xDiasHistoria);
                fachadaDctoOrdenBean.setDiasInventario(xDiasInventario);
                fachadaDctoOrdenBean.setIdCliente(xIdTercero);
                fachadaDctoOrdenBean.setFechaOrden(xFechaCorte);
                fachadaDctoOrdenBean.setIdLocal(xIdLocalUsuario);
                fachadaDctoOrdenBean.setIdTipoOrden(xIdTipoOrdenCompraProceso);
                fachadaDctoOrdenBean.setIdUsuario(xIdUsuario);
                fachadaDctoOrdenBean.setIdLog(xIdLogActual);
                fachadaDctoOrdenBean.setIdTipoTercero(xIdTipoTerceroProveedor);

                //
                request.setAttribute("fachadaDctoOrdenBean",
                        fachadaDctoOrdenBean);

                //
                return "/jsp/vtaContenedorReciboCompra.jsp";

            }
        }

        return "/jsp/empty.htm";

    }
}
