package com.solucionesweb.losservlets;

// Importa los paquetes del lenguaje especificamente io y los
// que permiten trabajar el servlet.
import com.solucionesweb.losbeans.negocio.DctoBean;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.*;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Importa la clase que contiene el Day
import com.solucionesweb.losbeans.utilidades.Day;

import com.solucionesweb.losbeans.utilidades.JhDate;

import com.solucionesweb.lasayudas.ProcesoIngresoIndicador;

import com.solucionesweb.lasayudas.ProcesoIngreso;

import com.solucionesweb.lasayudas.ProcesoIp;

import com.solucionesweb.lasayudas.ProcesoPagoMostrador;

// Importa la clase que contiene el DctoOrdenDetalleBean
import com.solucionesweb.losbeans.negocio.AgendaLogVisitaBean;

// Importa la clase que contiene el DctoOrdenDetalleBean
import com.solucionesweb.losbeans.negocio.DctoOrdenDetalleBean;

// Importa la clase que contiene el DctoOrdenBean
import com.solucionesweb.losbeans.negocio.DctoOrdenBean;

//
import com.solucionesweb.losbeans.colaboraciones.ColaboraTercero;

// Importa la clase que contiene el FachadaTerceroBean
import com.solucionesweb.losbeans.fachada.FachadaTerceroBean;

// Importa la clase que contiene el ColaboraDctoOrdenDetalleBean
import com.solucionesweb.losbeans.colaboraciones.ColaboraDctoOrdenDetalleBean;

// Importa la clase que contiene el FachadaDctoOrdenBean
import com.solucionesweb.losbeans.fachada.FachadaDctoOrdenBean;

// Importa la clase que contiene el FachadaColaboraHistoriaBean
import com.solucionesweb.losbeans.fachada.FachadaColaboraHistoriaBean;

// Importa la clase que contiene el FachadaDctoOrdenDetalleBean
import com.solucionesweb.losbeans.fachada.FachadaDctoOrdenDetalleBean;

// Importa la clase que contiene el FachadaAgendaLogVisitaBean
import com.solucionesweb.losbeans.fachada.FachadaAgendaLogVisitaBean;

// Importa la clase que contiene el FachadaDctoBean
import com.solucionesweb.losbeans.fachada.FachadaDctoBean;

// Importa la clase que contiene el Validacion
import com.solucionesweb.losbeans.utilidades.Validacion;

// Importa la clase que contiene el ValidacionUsuarioBean
import com.solucionesweb.losbeans.negocio.UsuarioBean;

// Importa la clase que contiene ProcesoGuardaPluOrden
import com.solucionesweb.lasayudas.ProcesoGuardaPluOrden;

// Este servlet implementa la interface GralManejadorRequest
public class VtasAdmEmpresaRemesaManejadorRequest
                                              implements GralManejadorRequest {
    private Object req;

  // Metodo contructor por defecto, es decir, sin parametros
  public VtasAdmEmpresaRemesaManejadorRequest () { }

  /**
   * Retorna la URL de la pagina que deber? ser entregada como respuesta
   * (normalmente un pagina jsp).
   */
  public String generaPdf(HttpServletRequest request,
                              HttpServletResponse response)
                throws ServletException,IOException  {

    //
    int xIdTipoTerceroCliente     = 1;
    int xIdTipoOrdenPedido        = 9;
    int xIdTipoOrdenPedidoProceso = xIdTipoOrdenPedido + 50;

    //
    int xNivelConIndicador51     = 51;
    int xNivelConIndicador52     = 52;

    //
    int estadoActivo              = 9;
    Day day                       = new Day();
    String strFechaVisita         = day.getFechaFormateada();

    //
    HttpSession sesion            = request.getSession();

    //
    UsuarioBean usuarioBean       =
                                (UsuarioBean)sesion.getAttribute("usuarioBean");

    //
    double xIdUsuario             = usuarioBean.getIdUsuario();
    int xIdLocalUsuario           = usuarioBean.getIdLocalUsuario();
    int xIdNivel                  = usuarioBean.getIdNivel();

    //
    AgendaLogVisitaBean agendaLogVisitaBean
                                  = new AgendaLogVisitaBean();

    //
    agendaLogVisitaBean.setEstado(estadoActivo);
    agendaLogVisitaBean.setFechaVisitaStr(strFechaVisita);
    agendaLogVisitaBean.setIdUsuario(xIdUsuario);

    //
    FachadaAgendaLogVisitaBean  fachadaAgendaLogVisitaBean
                               = new FachadaAgendaLogVisitaBean();

    //
    fachadaAgendaLogVisitaBean =
                              agendaLogVisitaBean.seleccionaVisitaEstadoFecha();

    //
    String xIdCliente          = fachadaAgendaLogVisitaBean.getIdCliente();
    int xIdLogActual           = fachadaAgendaLogVisitaBean.getIdLog();

    //
    String accionContenedor = request.getParameter("accionContenedor");

    // Validacion de accion relacionada con el formulario requerido
    if (accionContenedor != null ) {

        System.out.println(" accionContenedor " + accionContenedor );

        //
	    if (accionContenedor.compareTo("Regresar") == 0 ) {
            return "/jsp/empty.htm";
        }

        // Finalizar
	    if (accionContenedor.compareTo("Finalizar") == 0 ) {

           //
           String xIdLog              = request.getParameter("xIdLog");
           String xIdTipoOrden        = request.getParameter("xIdTipoOrden");
           String xVrPago             = request.getParameter("xVrPago");
           String xVrMedio            = request.getParameter("xVrMedio");
           String xMedioPago[]        =
                                       request.getParameterValues("xMedioPago");
           String xIndicadorMostrador =
                                    request.getParameter("xIndicadorMostrador");

           //
           Validacion validacion = new Validacion();

           //
           validacion.reasignar("VR.FACTURA","xVrPago");

           validacion.validarCampoDouble();

           //
           if (validacion.isValido()) {

              // Aqui escribe el Bean de Validacion en el Request para manejar el error
              request.setAttribute("validacion",validacion);
              return "/jsp/gralErrPedido.jsp";

           }

           //
           validacion.reasignar("VR.RECIBIDO","xVrMedio");

           validacion.validarCampoDouble();

           //
           if (validacion.isValido()) {

              // Aqui escribe el Bean de Validacion en el Request para manejar el error
              request.setAttribute("validacion",validacion);
              return "/jsp/gralErrPedido.jsp";

           }

           //
           xIdLogActual             = new Integer(xIdLog).intValue();
           String fechaEntrega      = strFechaVisita;
           String xObservacion      = "";
           String xNombreTercero    = "VENTAS MOSTRADOR";
           String xIdVendedor       = new Double(xIdUsuario).toString();

           //
           DctoOrdenBean dctoOrdenBean
                                    = new DctoOrdenBean();

           //
           dctoOrdenBean.setIdLog(xIdLogActual);
           dctoOrdenBean.setIdTipoOrden(xIdTipoOrdenPedido);

           // existePedido
           boolean existePedido     = dctoOrdenBean.existePedido();

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

           //
           ProcesoIp procesoIp = new ProcesoIp();

           //
           String xIpTx = procesoIp.getIpTx(request);

           //
           FachadaDctoBean fachadaDctoBean = new FachadaDctoBean();

           //
           int xIdTipoNegocioVenta     = 1;

           //
           if ((xIdNivel  == xNivelConIndicador51 ) ||
               (xIdNivel  == xNivelConIndicador52 )) {

                // confirmaPedido
                ProcesoIngresoIndicador proceso = new ProcesoIngresoIndicador();

                // confirma
                fachadaDctoBean = proceso.ingresa(
                                       xIdLocalUsuario,
                                       xIdTipoOrdenPedido,
                                       new Integer(xIdLogActual).intValue(),
                                       xIdTipoOrdenPedidoProceso,
                                       xNombreTercero.toUpperCase().trim(),
                                       xIdTipoNegocioVenta,
                                       xObservacion,
                                       xIdVendedor,
                                       xIpTx,
                                       xIndicadorMostrador);

           } else  {

                // confirmaPedido
                ProcesoIngreso          proceso = new ProcesoIngreso();

                // confirma
                fachadaDctoBean = proceso.ingresa(
                                       xIdLocalUsuario,
                                       xIdTipoOrdenPedido,
                                       new Integer(xIdLogActual).intValue(),
                                       xIdTipoOrdenPedidoProceso,
                                       xNombreTercero.toUpperCase().trim(),
                                       xIdTipoNegocioVenta,
                                       xObservacion,
                                       xIdVendedor,
                                       xIpTx);

           }


           //
           fachadaDctoBean.setVrPago(xVrPago);

           //
           String xIdMedioContado     = "1";

           // CONTADO
           ProcesoPagoMostrador procesoPagoMostrador
                                          = new ProcesoPagoMostrador();

           //
           procesoPagoMostrador.confirma((DctoBean) fachadaDctoBean,
                                          xIdLogActual,
                                          xIdMedioContado);

           //
           JhDate jhDate         = new JhDate();

           //
           String fechaTxHM      = null;

           try {
                 fechaTxHM = jhDate.getDate(4, false);
           } catch (Exception ex) {
                    Logger.getLogger(VtasAdmEmpresaRemesaManejadorRequest.class.getName()).log(Level.SEVERE, null, ex);
           }

           //
           int xIdEstadoTxSinTx = 1;
           int estadoProgramado = 1;
           int tareaVisita = 6;   // Cotizacion

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
           GeneraPDFRemesa generaPDFServlet = new GeneraPDFRemesa();

           //
           generaPDFServlet.setIdLocal(fachadaDctoBean.getIdLocalStr());
           generaPDFServlet.setIdOrden(fachadaDctoBean.getIdOrden());
           generaPDFServlet.setIdTipoOrden(fachadaDctoBean.getIdTipoOrdenStr());

           //
           generaPDFServlet.generaPdf(request, response);

           /*
           ProcesoContable procesoContable = new ProcesoContable();

           //
           procesoContable.procesaUnRegistro(fachadaDctoBean.getIdLocal(),
                                             fachadaDctoBean.getIdTipoOrden(),
                                             xIdLogActual); */


        }

	    if (accionContenedor.compareTo("Confirmar Descuento") == 0 )  {

            //
            String idLog               = request.getParameter("idLog");
            String xDescuentoComercial =
                                    request.getParameter("xDescuentoComercial");

            //
            Validacion validacion = new Validacion();

            //
            validacion.reasignar("%DESCUENTO COMERCIAL",xDescuentoComercial);
            validacion.validarCampoDoublePositivo();

            if (validacion.isValido() == false){

               // Aqui escribe el Bean de Validacion en el Request para manejar el error
               request.setAttribute("validacion",validacion);
               return "/jsp/gralErrPedido.jsp";
            }

           //
           DctoOrdenBean  dctoOrdenBean = new DctoOrdenBean();

           //
           dctoOrdenBean.setDescuentoComercial(xDescuentoComercial);
           dctoOrdenBean.setIdLocal(xIdLocalUsuario);
           dctoOrdenBean.setIdTipoOrden(xIdTipoOrdenPedidoProceso);
           dctoOrdenBean.setIdLog(fachadaAgendaLogVisitaBean.getIdLog());

           //
           dctoOrdenBean.actualizaDescuento();

           //
           FachadaTerceroBean fachadaTerceroBean = new FachadaTerceroBean();

           //
           ColaboraTercero       colaboraTercero = new ColaboraTercero();

           //
           fachadaTerceroBean.setIdTipoOrden(xIdTipoOrdenPedidoProceso);
           colaboraTercero.setIdTipoTercero(xIdTipoTerceroCliente);
           colaboraTercero.setIdCliente(
                                     fachadaAgendaLogVisitaBean.getIdCliente());
           colaboraTercero.setIdLocalTercero(
                                fachadaAgendaLogVisitaBean.getIdLocalTercero());

           //
           if (fachadaAgendaLogVisitaBean.getIdLocalTercero()>0) {

              //
              fachadaTerceroBean         =
                                       colaboraTercero.listaUnTerceroLocalFCH();
           } else {

                //
                fachadaTerceroBean          =
                                              colaboraTercero.listaTerceroFCH();
           }

           //
           fachadaTerceroBean.setIdCliente(
                                     fachadaAgendaLogVisitaBean.getIdCliente());
           fachadaTerceroBean.setIdTipoTercero(xIdTipoTerceroCliente);
           fachadaTerceroBean.setIdTipoOrden(xIdTipoOrdenPedidoProceso);
           fachadaTerceroBean.setIdLocal(xIdLocalUsuario);

           // Aqui escribe el Bean de Validacion en el Request para manejar el error
           request.setAttribute("fachadaTerceroBean",fachadaTerceroBean);

           //
           DctoOrdenDetalleBean dctoOrdenDetalleBean
                                          = new DctoOrdenDetalleBean();

           //
           dctoOrdenDetalleBean.setIdLocal(xIdLocalUsuario);
           dctoOrdenDetalleBean.setIdTipoOrden(xIdTipoOrdenPedidoProceso);
           dctoOrdenDetalleBean.setIdLog(fachadaAgendaLogVisitaBean.getIdLog());
           dctoOrdenDetalleBean.setVrDsctoPie(xDescuentoComercial);

           //
           dctoOrdenDetalleBean.actualizaDescuentoPie();

           //
           FachadaDctoOrdenBean fachadaDctoOrdenBean
                                            = new FachadaDctoOrdenBean();

           //
           fachadaDctoOrdenBean             =
                                 dctoOrdenBean.listaDctoOrdenIdLogIdTipoOrden();

           //
           request.setAttribute("fachadaDctoOrdenBean",fachadaDctoOrdenBean);

           // Retorna a seleccionar cliente
           if (fachadaAgendaLogVisitaBean.getIdCliente()==null) {

              return "/jsp/vtaContenedorEmpresaSelecciona.jsp";

           }

            // Aqui escribe el Bean de Validacion en el Request para manejar el error
           request.setAttribute("fachadaAgendaLogVisitaBean",
                                                    fachadaAgendaLogVisitaBean);

           //
           return "/jsp/vtaContenedorEmpresaRemesa.jsp";

        }

        // ModificaDescuento
        if (accionContenedor.compareTo("ModificaDescuento") == 0 )  {


           //
           DctoOrdenBean dctoOrdenBean = new DctoOrdenBean();

           //
           dctoOrdenBean.setIdLog(xIdLogActual);
           dctoOrdenBean.setIdTipoOrden(xIdTipoOrdenPedidoProceso);

           //
           FachadaDctoOrdenBean fachadaDctoOrdenBean
                                            = new FachadaDctoOrdenBean();

           //
           fachadaDctoOrdenBean             =
                                 dctoOrdenBean.listaDctoOrdenIdLogIdTipoOrden();

           //
           FachadaTerceroBean fachadaTerceroBean = new FachadaTerceroBean();

           //
           ColaboraTercero       colaboraTercero = new ColaboraTercero();

           //
           fachadaTerceroBean.setIdTipoOrden(xIdTipoOrdenPedidoProceso);
           colaboraTercero.setIdTipoTercero(xIdTipoTerceroCliente);
           colaboraTercero.setIdCliente(
                                     fachadaAgendaLogVisitaBean.getIdCliente());
           colaboraTercero.setIdLocalTercero(
                                fachadaAgendaLogVisitaBean.getIdLocalTercero());

           //
           if (fachadaAgendaLogVisitaBean.getIdLocalTercero()>0) {

              //
              fachadaTerceroBean         =
                                       colaboraTercero.listaUnTerceroLocalFCH();
           } else {

                //
                fachadaTerceroBean          =
                                              colaboraTercero.listaTerceroFCH();
           }

           //
           fachadaTerceroBean.setIdCliente(
                                     fachadaAgendaLogVisitaBean.getIdCliente());
           fachadaTerceroBean.setIdTipoTercero(xIdTipoTerceroCliente);
           fachadaTerceroBean.setIdTipoOrden(xIdTipoOrdenPedidoProceso);
           fachadaTerceroBean.setIdLocal(xIdLocalUsuario);

           // Aqui escribe el Bean de Validacion en el Request para manejar el error
           request.setAttribute("fachadaTerceroBean",fachadaTerceroBean);

           // Aqui escribe el Bean de Validacion en el Request para manejar el error
           request.setAttribute("fachadaAgendaLogVisitaBean",
                                                    fachadaAgendaLogVisitaBean);
           request.setAttribute("fachadaDctoOrdenBean",fachadaDctoOrdenBean);

           //
           return "/jsp/vtaFrmModDsctoEmpresaRemesa.jsp";

        }

        // Confirmar Cantidad
	    if (accionContenedor.compareTo("Confirmar Cantidad") == 0 )  {

           //
           String idClasificacion     = "1";
           String idResponsable       = "1";
           String xIdLog              = request.getParameter("xIdLog");
           String xItem               = request.getParameter("xItem");
           String cantidad            = request.getParameter("cantidad");
           String vrVentaUnitario     = request.getParameter("vrVentaUnitario");
           String xPorcentajeDescuento
                                 = request.getParameter("xPorcentajeDescuento");

           //
           Validacion validacion = new Validacion();

           //
           validacion.reasignar("CANTIDAD",cantidad);
           validacion.validarCampoDouble();

           if (validacion.isValido() == false){

              // Aqui escribe el Bean de Validacion en el Request para manejar el error
              request.setAttribute("validacion",validacion);
              return "/jsp/gralErrPedido.jsp";
           }

           //
           validacion.reasignar("VR.VENTA UNITARIO",vrVentaUnitario);
           validacion.validarCampoDoublePositivo();

           if (validacion.isValido() == false){

              // Aqui escribe el Bean de Validacion en el Request para manejar el error
              request.setAttribute("validacion",validacion);
              return "/jsp/gralErrPedido.jsp";
           }

           //
           validacion.reasignar("%DESCUENTO",xPorcentajeDescuento);
           validacion.validarCampoDoublePositivo();

           if (validacion.isValido() == false){

              // Aqui escribe el Bean de Validacion en el Request para manejar el error
              request.setAttribute("validacion",validacion);
              return "/jsp/gralErrPedido.jsp";
           }

           //
           DctoOrdenBean dctoOrdenBean = new DctoOrdenBean();

           //
           dctoOrdenBean.setIdLog(xIdLog);
           dctoOrdenBean.setIdTipoOrden(xIdTipoOrdenPedidoProceso);

           //
           FachadaDctoOrdenBean fachadaDctoOrdenBean
                                            = new FachadaDctoOrdenBean();

           //
           fachadaDctoOrdenBean =
                                 dctoOrdenBean.listaDctoOrdenIdLogIdTipoOrden();

           //
           String xIdTipoOrden              =
                                       fachadaDctoOrdenBean.getIdTipoOrdenStr();
           String xIdOrden                  =
                                           fachadaDctoOrdenBean.getIdOrdenStr();

           //
           DctoOrdenDetalleBean dctoOrdenDetalleBean
                                            = new DctoOrdenDetalleBean();

           //
           dctoOrdenDetalleBean.setItem(xItem);
           dctoOrdenDetalleBean.setCantidad(cantidad);
           dctoOrdenDetalleBean.setVrVentaUnitario(vrVentaUnitario);
           dctoOrdenDetalleBean.setIdClasificacion(idClasificacion);
           dctoOrdenDetalleBean.setIdResponsable(idResponsable);
           dctoOrdenDetalleBean.setPorcentajeDscto(xPorcentajeDescuento);

           //
           dctoOrdenDetalleBean.modifica(new Integer(xIdLocalUsuario).toString(),
                                         xIdTipoOrden,
                                         xIdOrden,
                                         xItem);

           //
           FachadaTerceroBean fachadaTerceroBean = new FachadaTerceroBean();

           //
           ColaboraTercero       colaboraTercero = new ColaboraTercero();

           //
           colaboraTercero.setIdCliente(
                                     fachadaAgendaLogVisitaBean.getIdCliente());
           colaboraTercero.setIdTipoTercero(xIdTipoTerceroCliente);
           colaboraTercero.setIdTipoOrden(xIdTipoOrdenPedidoProceso);

           //
           fachadaTerceroBean         = colaboraTercero.listaTerceroFCH();

           //
           fachadaTerceroBean.setIdTipoOrden(xIdTipoOrden);
           fachadaTerceroBean.setIdLocal(xIdLocalUsuario);

           //
           request.setAttribute("fachadaDctoOrdenBean",fachadaDctoOrdenBean);

           // Aqui escribe el Bean de Validacion en el Request para manejar el error
           request.setAttribute("fachadaTerceroBean",fachadaTerceroBean);

           // Aqui escribe el Bean de Validacion en el Request para manejar el error
           request.setAttribute("fachadaAgendaLogVisitaBean",
                                                    fachadaAgendaLogVisitaBean);
           
           //
           return "/jsp/vtaContenedorEmpresaRemesa.jsp";

        }


        // modifica
	    if (accionContenedor.compareTo("modifica") == 0 )  {

           //
           String xItem           = request.getParameter("xItem");
           String xIdLog          = request.getParameter("xIdLog");

           //
           FachadaDctoOrdenDetalleBean fachadaDctoOrdenDetalleBean =
                                              new FachadaDctoOrdenDetalleBean();

           //
           ColaboraDctoOrdenDetalleBean colaboraDctoOrdenDetalleBean =
                                             new ColaboraDctoOrdenDetalleBean();

           //
           colaboraDctoOrdenDetalleBean.setItem(xItem);
           colaboraDctoOrdenDetalleBean.setIdTipoOrden(
                                                     xIdTipoOrdenPedidoProceso);

           //
           fachadaDctoOrdenDetalleBean =
                     colaboraDctoOrdenDetalleBean.itemLogFachada(
                                                new Integer(xIdLog).intValue());

           //
           fachadaDctoOrdenDetalleBean.setItem(xItem);
           fachadaDctoOrdenDetalleBean.setIdLog(xIdLog);

           // Aqui escribe el Bean de Validacion en el Request para manejar el error
           request.setAttribute("fachadaDctoOrdenDetalleBean",
                                                   fachadaDctoOrdenDetalleBean);

           //
           request.setAttribute("fachadaAgendaLogVisitaBean",
                                                    fachadaAgendaLogVisitaBean);

           //
           FachadaTerceroBean fachadaTerceroBean = new FachadaTerceroBean();

           //
           fachadaTerceroBean.setIdCliente(
                                     fachadaAgendaLogVisitaBean.getIdCliente());
           fachadaTerceroBean.setIdTipoTercero(xIdTipoTerceroCliente);
           fachadaTerceroBean.setIdTipoOrden(xIdTipoOrdenPedidoProceso);

           // Aqui escribe el Bean de Validacion en el Request para manejar el error
           request.setAttribute("fachadaTerceroBean",fachadaTerceroBean);

           //
           return "/jsp/vtaFrmModEmpresaRemesa.jsp";

        }

        // retira
	    if (accionContenedor.compareTo("retira") == 0 )  {

           //
           String xItem           = request.getParameter("xItem");
           String xIdLog          = request.getParameter("xIdLog");

           //
           DctoOrdenBean  dctoOrdenBean = new DctoOrdenBean();

           //
           dctoOrdenBean.setIdLog(xIdLog);

           FachadaDctoOrdenBean fachadaDctoOrdenBean
                                              = new FachadaDctoOrdenBean();
           fachadaDctoOrdenBean               =
                                            dctoOrdenBean.listaDctoOrdenIdLog();

           //
           DctoOrdenDetalleBean dctoOrdenDetalleBean
                                              = new DctoOrdenDetalleBean();

           //
           dctoOrdenDetalleBean.setIdLog(xIdLog);
           dctoOrdenDetalleBean.setIdTipoOrden(xIdTipoOrdenPedidoProceso);
           dctoOrdenDetalleBean.setItem(xItem);


           // retiraArticulosMarcados
           boolean okRetiro = dctoOrdenDetalleBean.retiraItem();

           // Retira DctoOrden
           dctoOrdenBean.setIdLocal(xIdLocalUsuario);
           dctoOrdenBean.setIdTipoOrden(xIdTipoOrdenPedidoProceso);
           dctoOrdenBean.setIdOrden(xIdLog);

           // validaArticulosxOrden
           boolean okDetalle = dctoOrdenDetalleBean.validaOrden();

           if (!okDetalle) {

              // Retira DctoOrden
              dctoOrdenBean.setIdLocal(xIdLocalUsuario);
              dctoOrdenBean.setIdTipoOrden(xIdTipoOrdenPedidoProceso);
              dctoOrdenBean.setIdOrden(xIdLog);

              //
              dctoOrdenBean.retiraOrden();

           }

           //
           FachadaTerceroBean fachadaTerceroBean = new FachadaTerceroBean();

           //
           fachadaTerceroBean.setIdCliente(
                                     fachadaAgendaLogVisitaBean.getIdCliente());
           fachadaTerceroBean.setIdTipoTercero(xIdTipoTerceroCliente);
           fachadaTerceroBean.setIdTipoOrden(xIdTipoOrdenPedidoProceso);
           fachadaTerceroBean.setIdLocal(xIdLocalUsuario);

           //
           request.setAttribute("fachadaDctoOrdenBean",fachadaDctoOrdenBean);

           // Aqui escribe el Bean de Validacion en el Request para manejar el error
           request.setAttribute("fachadaTerceroBean",fachadaTerceroBean);

           // Aqui escribe el Bean de Validacion en el Request para manejar el error
           request.setAttribute("fachadaAgendaLogVisitaBean",
                                                    fachadaAgendaLogVisitaBean);

           //
           return "/jsp/vtaContenedorEmpresaRemesa.jsp";

        }


        // Confirmar
	    if (accionContenedor.compareTo("Confirmar") == 0 )  {

           //
           String arrIdReferencia[]  =
                                  request.getParameterValues("chkIdReferencia");

           //
           String arrcantidad[]      =
                                  request.getParameterValues("chkCantidad");

           //
           String ArrVrVentaUnitario[]  =
                               request.getParameterValues("chkVrVentaUnitario");

           //
           Validacion valida           = new Validacion();

           // Validar
           for (int indice=0;indice<arrcantidad.length;indice++){

                //
                if (arrcantidad[indice].length()==0) continue;

                //
                valida.reasignar("CANTIDAD", arrcantidad[indice]);

                //
    	        valida.validarCampoDoublePositivo();

                //isValido
                if (!valida.isValido()){

                   //
 	               request.setAttribute("validacion",valida);
                   return "/jsp/gralError.jsp";
                }

           }

           //
           ProcesoGuardaPluOrden proceso = new ProcesoGuardaPluOrden();

           //
           for (int indice=0;indice<arrcantidad.length;indice++){

                //
                if (arrcantidad[indice].length()==0) continue;

                //
                double xCantidad        =
                                new Double(arrcantidad[indice]).doubleValue();
                double xVrVentaUnitario =
                         new Double(ArrVrVentaUnitario[indice]).doubleValue();


                //valida el idTercero sea el mismo para todos
                String strIdReferencia  = arrIdReferencia[indice];
                int xItemPadre          = 0;
                String xComentario      = "ninguno";
                String xIdResponsable   = "0";
                int xIdClasificacion    = 0;
                int xUnidadMedida       = 1;

                //
                int maximoItem    = proceso.guarda(xIdLogActual,
                                                   strIdReferencia,
                                                   xCantidad,
                                                   xVrVentaUnitario,
                                                   xItemPadre,
                                                   xIdTipoOrdenPedidoProceso,
                                                   xIdUsuario,
                                                   xIdLocalUsuario,
                                                   xIdCliente,
                                                   xComentario,
                                                   xIdResponsable,
                                                   xIdClasificacion,
                                                   xUnidadMedida);

           }

           //
           FachadaTerceroBean fachadaTerceroBean = new FachadaTerceroBean();

           //
           fachadaTerceroBean.setIdCliente(
                                     fachadaAgendaLogVisitaBean.getIdCliente());
           fachadaTerceroBean.setIdTipoTercero(xIdTipoTerceroCliente);
           fachadaTerceroBean.setIdTipoOrden(xIdTipoOrdenPedidoProceso);

           //
           ColaboraTercero       colaboraTercero
                                         = new ColaboraTercero();

           //
           colaboraTercero.setIdTipoTercero(xIdTipoTerceroCliente);
           colaboraTercero.setIdCliente(
                                     fachadaAgendaLogVisitaBean.getIdCliente());

           //
           fachadaTerceroBean           = colaboraTercero.listaTerceroFCH();

           //
           fachadaTerceroBean.setIdTipoOrden(xIdTipoOrdenPedidoProceso);
           fachadaTerceroBean.setIdLocal(xIdLocalUsuario);

           //
           DctoOrdenBean  dctoOrdenBean = new DctoOrdenBean();

           //
           dctoOrdenBean.setIdLog(fachadaAgendaLogVisitaBean.getIdLog());
           dctoOrdenBean.setIdTipoOrden(xIdTipoOrdenPedidoProceso);

           //
           FachadaDctoOrdenBean fachadaDctoOrdenBean
                                            = new FachadaDctoOrdenBean();

           //
           fachadaDctoOrdenBean             =
                                 dctoOrdenBean.listaDctoOrdenIdLogIdTipoOrden();

           //
           request.setAttribute("fachadaDctoOrdenBean",fachadaDctoOrdenBean);

           // Aqui escribe el Bean de Validacion en el Request para manejar el error
           request.setAttribute("fachadaTerceroBean",fachadaTerceroBean);

           // Aqui escribe el Bean de Validacion en el Request para manejar el error
           request.setAttribute("fachadaAgendaLogVisitaBean",
                                                    fachadaAgendaLogVisitaBean);

           //
           return "/jsp/vtaContenedorEmpresaRemesa.jsp";

        }

        // Productos
	    if (accionContenedor.compareTo("+Productos") == 0 )  {

	       //
	       String idLinea        = request.getParameter("idLinea");
	       String xIdListaPrecio = request.getParameter("xIdListaPrecio");

           //
           String strCadena      = idLinea.trim();
           int    lonCadena      = strCadena.length();
           int    posCadena      = strCadena.indexOf('+',0);
           String xNombrePlu     = "";

           //
           if (posCadena>0) {

              //
              idLinea            = strCadena.substring(0,posCadena).trim();
              xNombrePlu         =
                              strCadena.substring(posCadena+1,lonCadena).trim();

           } else {

             //
             double xIdPlu       = 0.0 ;
             String strIdPlu     = strCadena;

		     try {

                 //
   		         xIdPlu = new Double(strIdPlu).doubleValue();


		  	 } catch(NumberFormatException nfe) {

                 //
			     xNombrePlu      = idLinea;
                 idLinea         = "";
    		}
           }


           //
           FachadaColaboraHistoriaBean fachadaColaboraHistoriaBean =
                                              new FachadaColaboraHistoriaBean();

           //
      	   fachadaColaboraHistoriaBean.setIdLinea(idLinea);
      	   fachadaColaboraHistoriaBean.setNombrePlu(xNombrePlu);

           //
           request.setAttribute("fachadaColaboraHistoriaBean",
                                                   fachadaColaboraHistoriaBean);

           request.setAttribute("fachadaAgendaLogVisitaBean",
                                                    fachadaAgendaLogVisitaBean);

           //
           FachadaTerceroBean fachadaTerceroBean = new FachadaTerceroBean();

           //
           fachadaTerceroBean.setIdCliente(
                                     fachadaAgendaLogVisitaBean.getIdCliente());
           fachadaTerceroBean.setIdTipoTercero(xIdTipoTerceroCliente);
           fachadaTerceroBean.setIdTipoOrden(xIdTipoOrdenPedidoProceso);

           //
           ColaboraTercero       colaboraTercero
                                         = new ColaboraTercero();

           //
           colaboraTercero.setIdTipoTercero(xIdTipoTerceroCliente);
           colaboraTercero.setIdCliente(
                                     fachadaAgendaLogVisitaBean.getIdCliente());
           //
           fachadaTerceroBean           = colaboraTercero.listaTerceroFCH();

           //
           fachadaTerceroBean.setIdListaPrecio(xIdListaPrecio);

           //
           fachadaTerceroBean.setIdLocal(xIdLocalUsuario);
           fachadaTerceroBean.setIdListaPrecio(xIdListaPrecio);

           //
           DctoOrdenBean  dctoOrdenBean = new DctoOrdenBean();

           //
           dctoOrdenBean.setIdLog(fachadaAgendaLogVisitaBean.getIdLog());
           dctoOrdenBean.setIdTipoOrden(xIdTipoOrdenPedidoProceso);

           //
           FachadaDctoOrdenBean fachadaDctoOrdenBean
                                            = new FachadaDctoOrdenBean();

           //
           fachadaDctoOrdenBean             =
                                 dctoOrdenBean.listaDctoOrdenIdLogIdTipoOrden();

           //
           request.setAttribute("fachadaDctoOrdenBean",fachadaDctoOrdenBean);

           // Aqui escribe el Bean de Validacion en el Request para manejar el error
           request.setAttribute("fachadaTerceroBean",fachadaTerceroBean);

           //
           return "/jsp/vtaFrmSelEmpresaRemesa.jsp";

        }

    }

    //
    return "/jsp/empty.htm";

  }
}