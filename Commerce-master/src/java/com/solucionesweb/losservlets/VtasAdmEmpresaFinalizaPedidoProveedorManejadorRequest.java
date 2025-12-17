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

// Importa la clase ProcesoPago
import com.solucionesweb.lasayudas.ProcesoPago;

import com.solucionesweb.losservlets.GeneraPDFDetallaCxP;

// Importa la clase ProcesoConfirmaPedid
import com.solucionesweb.lasayudas.GeneraPDFJasper;

// Importa la clase ProcesoConfirmaPedido
import com.solucionesweb.lasayudas.ProcesoIngresoCompra;

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

// Importa la clase que contiene el FachadaTerceroBean
import com.solucionesweb.losbeans.fachada.FachadaDctoBean;

// Importa la clase que contiene el UsuarioBean
import com.solucionesweb.losbeans.negocio.UsuarioBean;

// Importa la clase que contiene el DctoBean
import com.solucionesweb.losbeans.negocio.DctoBean;

// Importa la clase que contiene el Bean de Validacion
import com.solucionesweb.losbeans.utilidades.Validacion;

// Importa la clase que contiene el FachadaTerceroBean
import com.solucionesweb.losbeans.fachada.FachadaTerceroBean;
import java.util.logging.Level;
import java.util.logging.Logger;

// Este servlet implementa la interface GralManejadorRequest
public class VtasAdmEmpresaFinalizaPedidoProveedorManejadorRequest
        implements GralManejadorRequest {

    // Metodo contructor por defecto, es decir, sin parametros
    public VtasAdmEmpresaFinalizaPedidoProveedorManejadorRequest() {
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
        int xIdTipoTerceroCliente = 2;
        int xIdTipoOrdenCotizacion = 10;
        int xIdTipoOrdenPedido = 1;
        int xIdEstadoTxSinTx = 1;
        int estadoActivo = 9;
        int estadoProgramado = 1;
        int tareaVisita = 6;   // Cotizacion

        //
        Day day = new Day();
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
            Logger.getLogger(VtasAdmEmpresaFinalizaPedidoManejadorRequest.class.getName()).log(Level.SEVERE, null, ex);
        }

        //
        HttpSession sesion = request.getSession();
        UsuarioBean usuarioBean =
                (UsuarioBean) sesion.getAttribute("usuarioBean");

        //
        String idUsuario = usuarioBean.getIdUsuarioStr();
        int xIdLocalUsuario = usuarioBean.getIdLocalUsuario();
        String strIdLista = usuarioBean.getStrIdLista();

        // Validacion de accion relacionada con el formulario requerido
        if (accionContenedor != null) {

            System.out.println(" accionContenedor " + accionContenedor);

            //
            if (accionContenedor.compareTo("Salir") == 0) {
                return "/jsp/empty.htm";
            }

            // Regresar
            if (accionContenedor.compareTo("REGRESAR") == 0) {

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
                FachadaTerceroBean fachadaTerceroBean = new FachadaTerceroBean();

                //
                fachadaTerceroBean.setIdCliente(
                        fachadaAgendaLogVisitaBean.getIdCliente());
                fachadaTerceroBean.setIdTipoTercero(xIdTipoTerceroCliente);
                fachadaTerceroBean.setIdTipoOrden(xIdTipoOrdenCotizacion);

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaTerceroBean", fachadaTerceroBean);

                // Retorna a seleccionar cliente
                if (fachadaAgendaLogVisitaBean.getIdCliente() == null) {

                    return "/jsp/vtaContenedorEmpresaProveedorSelecciona.jsp";

                }

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaAgendaLogVisitaBean",
                        fachadaAgendaLogVisitaBean);
                return "/jsp/vtaContenedorEmpresaElaboraProveedorPedido.jsp";

            }

            //
            if (accionContenedor.compareTo("CONFIRMAR") == 0) {

                // Datos recibidos cotizaci?n
                String xIdLogActual = request.getParameter("idLog");
                String xFechaDctoNitCC = request.getParameter("xFechaDctoNitCC");
                String xIdDctoNitCC = request.getParameter("xIdDctoNitCC");

                String xVrBase = request.getParameter("xVrBase");
                String xVrDescuento = request.getParameter("xVrDescuento");
                String xVrIva = request.getParameter("xVrIva");
                String xVrRteFuente = request.getParameter("xVrRteFuente");


                //String observacion        = request.getParameter("observacion");
                //String contacto           = request.getParameter("contacto");

                String impuestoVenta = "1";
                String clave = request.getParameter("clave");
                String xIdTipoNegocio[] =
                        request.getParameterValues("xIdTipoNegocio");
                String xVrPagarFactura = request.getParameter("xVrPagarFactura");

                // Instancia el Bean de Validacion para validar los campos
                Validacion validacion = new Validacion();

                // Valida fechaEntrega
                validacion.reasignar("FECHA FACTURA", xFechaDctoNitCC);
                validacion.validarCampoFecha();
                if (validacion.isValido() == false) {

                    // Aqui escribe el Bean de Validacion en el Request para manejar el error
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralErrFinalizaCotizacion.jsp";
                }

                // Valida fechaEntrega
                validacion.reasignar("NUMERO FACTURA", xIdDctoNitCC);
                validacion.validarCampoDouble();

                if (validacion.isValido() == false) {
                    // Aqui escribe el Bean de Validacion en el Request para manejar el error
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralErrFinalizaCotizacion.jsp";
                }

                // Valida xVrBase
                validacion.reasignar("VR.BASE", xVrBase);
                validacion.validarCampoDouble();

                if (validacion.isValido() == false) {
                    // Aqui escribe el Bean de Validacion en el Request para manejar el error
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralErrFinalizaCotizacion.jsp";
                }

                // Valida xVrDescuento
                validacion.reasignar("VR.DESCUENTO", xVrDescuento);
                validacion.validarCampoDoublePositivo();

                if (validacion.isValido() == false) {
                    // Aqui escribe el Bean de Validacion en el Request para manejar el error
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralErrFinalizaCotizacion.jsp";
                }

                // Valida xVrDescuento
                validacion.reasignar("VR.IVA", xVrIva);
                validacion.validarCampoDoublePositivo();

                if (validacion.isValido() == false) {
                    // Aqui escribe el Bean de Validacion en el Request para manejar el error
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralErrFinalizaCotizacion.jsp";
                }

                // Valida xVrDescuento
                validacion.reasignar("VR.RTE.FUENTE", xVrRteFuente);
                validacion.validarCampoDoublePositivo();

                if (validacion.isValido() == false) {
                    // Aqui escribe el Bean de Validacion en el Request para manejar el error
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralErrFinalizaCotizacion.jsp";
                }

                // Valida IdLogActual
                validacion.reasignar("IdLogActual", xIdLogActual);
                validacion.validarCampoEntero();
                if (validacion.isValido() == false) {

                    // Aqui escribe el Bean de Validacion en el Request para manejar el error
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralErrFinalizaCotizacion.jsp";
                }

                // Valida CREDITO/CONTADO
                validacion.reasignar("COMPRA CONTADO/CREDITO", "");
                if (xIdTipoNegocio == null) {

                    // Aqui escribe el Bean de Validacion en el Request para manejar el error
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralErrFinalizaCotizacion.jsp";
                }

                // Valida fechaEntrega
                validacion.reasignar("CONTRASEÑA", clave);
                validacion.validarCampoString();

                if (validacion.isValido() == false) {
                    // Aqui escribe el Bean de Validacion en el Request para manejar el error
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralErrFinalizaCotizacion.jsp";
                }

                // Valida xVrBase
                validacion.reasignar("VR.PAGAR SEGUN FACTURA", xVrPagarFactura);
                validacion.validarCampoDouble();

                if (validacion.isValido() == false) {
                    // Aqui escribe el Bean de Validacion en el Request para manejar el error
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralErrFinalizaCotizacion.jsp";
                }

                //
                if (new Double(xVrBase).doubleValue() +
                        new Double(xVrIva).doubleValue() -
                        new Double(xVrDescuento).doubleValue() -
                        new Double(xVrRteFuente).doubleValue() !=
                        new Double(xVrPagarFactura).doubleValue()) {

                    //
                    validacion.reasignar("VR.A PAGAR", "");
                    validacion.setCodigoError("Error PEDIDO");
                    validacion.setDescripcionError("VERIFICAR TOTALES FACTURA");

                    // Aqui escribe el Bean de Validacion en el Request para manejar el error
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralErrFinalizaCotizacion.jsp";

                }

                // Valida el UsuarioBean con parametros.
                int estadoVigente = 1;         // Usuario vigente
                usuarioBean.setIdUsuario(idUsuario);
                usuarioBean.setClave(clave);
                usuarioBean.setEstado(estadoVigente);

                // Efectua la busqueda del usuario en la base de datos
                usuarioBean.vigenciaUsuario();
                if (!usuarioBean.isVigente()) {

                    // Aqui escribe el Bean de Validacion en el Request para manejar el error
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralErrFinalizaCotizacion.jsp";

                }

                //
                FachadaAgendaLogVisitaBean fachadaAgendaLogVisitaBean = new FachadaAgendaLogVisitaBean();

                //
                AgendaLogVisitaBean agendaLogVisitaBean = new AgendaLogVisitaBean();

                //
                agendaLogVisitaBean.setEstado(estadoActivo);
                agendaLogVisitaBean.setFechaVisitaStr(strFechaVisita);
                agendaLogVisitaBean.setIdUsuario(idUsuario);

                //
                fachadaAgendaLogVisitaBean =
                        agendaLogVisitaBean.seleccionaVisitaEstadoFecha();


                //
                FachadaTerceroBean fachadaTerceroBean = new FachadaTerceroBean();

                //
                ColaboraTercero colaboraTercero = new ColaboraTercero();

                colaboraTercero.setIdCliente(
                        fachadaAgendaLogVisitaBean.getIdCliente());
                colaboraTercero.setIdTipoTercero(xIdTipoTerceroCliente);

                //
                fachadaTerceroBean =
                        colaboraTercero.listaTerceroFCH();

                //
                String strIdSucursal = "01";
                String direccionDespacho =
                        fachadaTerceroBean.getDireccionTercero();
                String ciudadDespacho =
                        fachadaTerceroBean.getCiudadTercero();
                String email = fachadaTerceroBean.getEmail();
                String fax = fachadaTerceroBean.getTelefonoFax();
                String formaPago =
                        fachadaTerceroBean.getIdFormaPagoStr();
                String descuentoComercial = "";
                String idRazon =
                        fachadaTerceroBean.getIdFormaPagoStr();
                String xIdRuta = fachadaTerceroBean.getIdRuta();
                String xNombreTercero =
                        fachadaTerceroBean.getNombreTercero();
                String xIdCliente =
                        fachadaTerceroBean.getIdCliente();

                //
                DctoOrdenBean dctoOrdenBean = new DctoOrdenBean();

                //
                dctoOrdenBean.setIdLog(xIdLogActual);
                dctoOrdenBean.setIdTipoOrden(xIdTipoOrdenPedido);

                // existePedido
                boolean existePedido = dctoOrdenBean.existePedido();

                if (existePedido) {

                    //
                    validacion.reasignar("PEDIDO", "");
                    validacion.setCodigoError("Error PEDIDO");
                    validacion.setDescripcionError("Pedido YA CONFIRMADO");
                    validacion.setSolucion("Iniciar NUEVO PEDIDO");

                    // Aqui escribe el Bean de Validacion en el Request para manejar el error
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralErrFinalizaCotizacion.jsp";

                }

                //
                DctoBean dctoBean = new DctoBean();

                //
                dctoBean.setIdLocal(xIdLocalUsuario);
                dctoBean.setIdTipoOrden(xIdTipoOrdenPedido);
                dctoBean.setIdCliente(fachadaAgendaLogVisitaBean.getIdCliente());
                dctoBean.setIdDctoNitCC(xIdDctoNitCC.trim());

                //
                FachadaDctoBean fachadaDctoBean = new FachadaDctoBean();

                //
                fachadaDctoBean = dctoBean.listaUnIdDctoNitCC();

                //
                if (fachadaDctoBean.getIdDcto() > 0) {

                    //
                    validacion.reasignar("FACTURA", "");
                    validacion.setCodigoError("Error FACTURA PROVEEDOR");
                    validacion.setDescripcionError("FACTURA YA INGRESADA");

                    // Aqui escribe el Bean de Validacion en el Request para manejar el error
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralErrFinalizaCotizacion.jsp";
                }

                // confirmaPedido
                ProcesoIngresoCompra proceso = new ProcesoIngresoCompra();

                // confirma
                fachadaDctoBean =
                        proceso.ingresa(xIdLocalUsuario,
                        xIdTipoOrdenPedido,
                        0,
                        new Integer(xIdLogActual).intValue(),
                        xIdTipoOrdenCotizacion,
                        xNombreTercero,
                        xFechaDctoNitCC,
                        xIdDctoNitCC,
                        new Double(xVrBase).doubleValue(),
                        new Double(xVrDescuento).doubleValue(),
                        new Double(xVrIva).doubleValue(),
                        new Double(xVrRteFuente).doubleValue());

                // CONTADO
                if (xIdTipoNegocio[0].compareTo("CONTADO") == 0) {

                    //
                    ProcesoPago procesoPago = new ProcesoPago();

                    //
                    procesoPago.confirma(fachadaDctoBean.getIdDctoStr(),
                            new Integer(xIdTipoOrdenCotizacion).toString(),
                            new Integer(xIdTipoOrdenPedido).toString(),
                            fachadaDctoBean.getIdLocalStr(),
                            xIdLogActual,
                            idUsuario,
                            0);
                }

                //
                GeneraPDFDetallaCxP generaPDF = new GeneraPDFDetallaCxP();

                //
                generaPDF.setIdLocal(xIdLocalUsuario);
                generaPDF.setIdOrden(fachadaDctoBean.getIdOrden());
                generaPDF.setIdTipoOrden(xIdTipoOrdenPedido);

                //
                generaPDF.generaPdf(request, response);

                // finalizaVisita
                agendaLogVisitaBean.setIdLog(xIdLogActual);
                agendaLogVisitaBean.setIdCliente(
                        fachadaAgendaLogVisitaBean.getIdCliente());
                agendaLogVisitaBean.setIdUsuario(
                        fachadaAgendaLogVisitaBean.getIdUsuario());
                agendaLogVisitaBean.setIdTipoOrden(
                        fachadaDctoBean.getIdTipoOrdenStr());
                agendaLogVisitaBean.setIdEstadoTx(xIdEstadoTxSinTx);
                agendaLogVisitaBean.setIdLocal(fachadaDctoBean.getIdLocalStr());
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

            }
        }

        return "/jsp/empty.htm";

    }
}