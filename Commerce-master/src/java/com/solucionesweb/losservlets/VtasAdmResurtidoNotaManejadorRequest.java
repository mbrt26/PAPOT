package com.solucionesweb.losservlets;

// Importa los paquetes del lenguaje especificamente io y los
// que permiten trabajar el servlet.
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//
import com.solucionesweb.lasayudas.ProcesoIp;

//
import com.solucionesweb.losbeans.utilidades.JhDate;

// Importa la clase que contiene el AgendaControlBean
import com.solucionesweb.losbeans.negocio.AgendaLogVisitaBean;

// Importa la clase que contiene el FachadaAgendaLogVisitaBean
import com.solucionesweb.losbeans.fachada.FachadaAgendaLogVisitaBean;

// Importa la clase que contiene el FachadaColaboraLogisticaBean
import com.solucionesweb.losbeans.fachada.FachadaColaboraLogisticaBean;

// Importa la clase que contiene el FachadaTerceroBean
import com.solucionesweb.losbeans.fachada.FachadaTerceroBean;

// Importa la clase que contiene el FachadaDctoBean
import com.solucionesweb.losbeans.fachada.FachadaDctoBean;

// Importa la clase que contiene el Day
import com.solucionesweb.losbeans.utilidades.Day;


// Importa la clase que contiene el ProcesoIngreso
import com.solucionesweb.lasayudas.ProcesoIngresoNota;

// Importa la clase que contiene el Day
import com.solucionesweb.losbeans.utilidades.Validacion;

// Importa la clase que contiene el UsuarioBean
import com.solucionesweb.losbeans.negocio.UsuarioBean;

//
import com.solucionesweb.losbeans.negocio.DctoOrdenBean;

//
import com.solucionesweb.losbeans.negocio.DctoBean;

//
import com.solucionesweb.lasayudas.ProcesoGuardaPluResurtido;

import com.solucionesweb.losbeans.colaboraciones.ColaboraTercero;

// Importa la clase que contiene el DctoOrdenDetalleBean
import com.solucionesweb.losbeans.negocio.DctoOrdenDetalleBean;

// Importa la clase que contiene el ValidacionColaboraReporteDctoBean
import com.solucionesweb.losbeans.utilidades.ValidacionColaboraReporteDctoBean;

import com.solucionesweb.lasayudas.ProcesoPagoNota;

import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * 
 * Procedimiento que se hace cuando el usuario necesita hacer devoluciones al proveedor/
 * vtaContenedorResurtidoNota.jsp /
 * 
 *  Este servlet implementa la interface GralManejadorRequest /
 */
 
public class VtasAdmResurtidoNotaManejadorRequest
                                              implements GralManejadorRequest {
    /**
     * BUTTON--
     * ("Consultar")-Permite ver un historico de resurtido por rango de fecha de un proveedor /
     * Seleccionar Debito(+) o credito(-) del historico resurtido/vtaFrmSelResurtidoNota.jsp/
     * ("Confirmar")-Permite finalizar la eleccion de articulos a devolver /vtaFrmLstResurtidoNota.jsp /
     * ("Finalizar"-Genera pdf de nota de credito /vtaFrmSelEmpresaFinalizaNota.jsp /vtaFrmFinResurtidoNota.jsp /
     * ("Regresar")-("Salir")-Retorna  al menu principal /
     * 
     * Metodo contructor por defecto, es decir, sin parametros /
     */
   
    public VtasAdmResurtidoNotaManejadorRequest() { }

    /**
     * BUTTON PARAMETER--
     * "Fecha inicial"-Fecha inical en la que se desea ver el historico de resurtido/
     * "Fecha final"-Fecha limite en la se desea ver el historico de resurtido /
     * "Cantidad"-cantidad de produto  /
     * 
     * Retorna la URL de la pagina que deber? ser entregada como respuesta
     * (normalmente un pagina jsp). /
     */
    public String generaPdf(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //
        String accionContenedor = request.getParameter("accionContenedor");
        System.out.println(accionContenedor);

        //---------
        int xIdTipoTerceroCliente    = 2;
        int xIdTipoOrdenNota         = 21;
        int xIdTipoOrdenNotaTemporal = xIdTipoOrdenNota + 50 ;
        String xIdTipoOrdenCadena    = "1"; // venta + nota venta
        int xIdEstadoTxSinTX         = 1;
        String xIdMedio              = "1";
        String xIdTipoOrdenOrigen    = "1";

        //
        String idCliente = "-1";

        //
        JhDate jhDate = new JhDate();

        //
        String fechaTxHM = null;

        //
        try {
            //
            fechaTxHM = jhDate.getDate(4, false);
        } catch (Exception ex) {
            Logger.getLogger(VtasAdmResurtidoNotaManejadorRequest.class.getName()).log(Level.SEVERE, null, ex);
        }

        //
        Day day = new Day();

        //
        String strFechaVisita = day.getFechaFormateada();
        int xEstadoProgramado = 9;

        int xIdEstadoVisitaConcluido = 6;
        int xEstadoTerminado         = 1;
        int xSignoOperacionNotaCR    =-1;
        int xSignoOperacionNotaDB    = 1;
        int xSignoOperacionNota      = 1;
        //
        HttpSession sesion = request.getSession();
        UsuarioBean usuarioBean      =
                (UsuarioBean) sesion.getAttribute("usuarioBean");

        //
        String idUsuario               = usuarioBean.getIdUsuarioStr();
        int xIdLocalUsuario            = usuarioBean.getIdLocalUsuario();

        //
        int xIndicadorInicial          = usuarioBean.getIndicadorInicial();
        int xIndicadorFinal            = usuarioBean.getIndicadorFinal();

        //
        AgendaLogVisitaBean agendaLogVisitaBean = new AgendaLogVisitaBean();
        agendaLogVisitaBean.setEstado(xEstadoProgramado);
        agendaLogVisitaBean.setFechaVisitaStr(strFechaVisita);
        agendaLogVisitaBean.setIdUsuario(idUsuario);

        //
        FachadaAgendaLogVisitaBean fachadaAgendaLogVisitaBean
                                             = new FachadaAgendaLogVisitaBean();
        fachadaAgendaLogVisitaBean =
                agendaLogVisitaBean.seleccionaVisitaEstadoFecha();

        //
        fachadaAgendaLogVisitaBean.setIdUsuario(idUsuario);

        // Aqui escribe el Bean de Validacion en el Request para manejar el error
        request.setAttribute("fachadaAgendaLogVisitaBean",
                fachadaAgendaLogVisitaBean);

        // Retorna a seleccionar cliente
        if (fachadaAgendaLogVisitaBean.getIdCliente() == null) {

            //
            FachadaTerceroBean fachadaTerceroBean = new FachadaTerceroBean();

            //
            request.setAttribute("fachadaTerceroBean", fachadaTerceroBean);

            //
            return "/jsp/vtaContenedorEmpresaProveedorSelecciona.jsp";

        }

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

            if (accionContenedor.compareTo("detallarCotizacion") == 0) {

                //
                String xIdTipoOrden = request.getParameter("idTipoOrden");
                String xIdOrden     = request.getParameter("xIdOrden");
                String xIdLocal     = request.getParameter("idLocal");
                String xIndicador   = request.getParameter("xIndicador");
                String xIdDcto      = request.getParameter("xIdDcto");
                String xTipoNota    = request.getParameter("xTipoNota");
                String xIdDctoNitCC = request.getParameter("xIdDctoNitCC");


                //
                FachadaDctoBean fachadaDctoBean = new FachadaDctoBean();

                //
                fachadaDctoBean.setIdLocal(xIdLocal);
                fachadaDctoBean.setIdTipoOrden(xIdTipoOrden);
                fachadaDctoBean.setIdOrden(xIdOrden);
                fachadaDctoBean.setIndicador(xIndicador);
                fachadaDctoBean.setIdDcto(xIdDcto);
                fachadaDctoBean.setTipoNota(xTipoNota);
                fachadaDctoBean.setIdDctoNitCC(xIdDctoNitCC);

                //
                FachadaTerceroBean fachadaTerceroBean = new FachadaTerceroBean();

                //
                fachadaTerceroBean.setIdTipoTercero(xIdTipoTerceroCliente);
                fachadaTerceroBean.setIdCliente(
                        fachadaAgendaLogVisitaBean.getIdCliente());

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaDctoBean", fachadaDctoBean);

                //
                request.setAttribute("fachadaTerceroBean", fachadaTerceroBean);

                //
                return "/jsp/vtaFrmSelResurtidoNota.jsp";

            }

            // Confirmar
            if (accionContenedor.compareTo("Confirmar") == 0) {

                //
                String xIdTipoOrden = request.getParameter("xIdTipoOrden");
                String xIdDcto      = request.getParameter("xIdDcto");
                String xIdLocal     = request.getParameter("xIdLocal");
                String xIndicador   = request.getParameter("xIndicador");
                String xIdOrden     = request.getParameter("xIdOrden");
                String xTipoNota    = request.getParameter("xTipoNota");

                //
                if (xTipoNota.compareTo("notaCredito")==0) {

                  //
                  xSignoOperacionNota  = xSignoOperacionNotaCR;


                } else {

                  //
                  xSignoOperacionNota  = xSignoOperacionNotaDB;

                }

                //
                String arrIdReferencia[] =
                        request.getParameterValues("chkIdReferencia");

                //
                String arrcantidad[] =
                        request.getParameterValues("chkCantidad");

                //
                String ArrVrVentaUnitario[] =
                        request.getParameterValues("chkVrVentaUnitario");

                //
                String arrCantidadOrigen[] =
                        request.getParameterValues("chkCantidadOrigen");

                String arrPorcentajeIva[] =
                        request.getParameterValues("chkPorcentajeIva");
                String arrVrCosto[] =
                                    request.getParameterValues("chkVrCosto");

                //
                int idLog =
                        fachadaAgendaLogVisitaBean.getIdLog();
                double xIdUsuario =
                        fachadaAgendaLogVisitaBean.getIdUsuario();
                String xIdTercero =
                        fachadaAgendaLogVisitaBean.getIdCliente();

                //
                DctoOrdenDetalleBean dctoOrdenDetalleBean
                                                  = new DctoOrdenDetalleBean();

                // retiraDevolucion
                dctoOrdenDetalleBean.retiraDevolucion(idLog);

                // Retorna a seleccionar cliente
                if (fachadaAgendaLogVisitaBean.getIdCliente() == null) {

                    request.setAttribute("fachadaAgendaLogVisitaBean",
                            fachadaAgendaLogVisitaBean);
                    fachadaAgendaLogVisitaBean.setIdCliente(idCliente);

                    //
                    return "/jsp/vtaContenedorEmpresaProveedorSelecciona.jsp";

                }

                //
                Validacion valida = new Validacion();

                // Validar
                for (int indice = 0; indice < arrcantidad.length; indice++) {

                    //
                    if (arrcantidad[indice].length() == 0) {
                        continue;
                    }

                    //
                    valida.reasignar("Cantidad", arrcantidad[indice]);

                    //
                    valida.validarCampoDoublePositivo();

                    //isValido
                    if (!valida.isValido()) {

                        //
                        request.setAttribute("validacion", valida);
                        return "/jsp/gralError.jsp";
                    }

                }

                // Validar
                for (int indice = 0; indice < arrcantidad.length; indice++) {

                    //
                    if (arrcantidad[indice].length() == 0) {
                        continue;
                    }

                    if (new Double(arrcantidad[indice]).doubleValue() >
                            new Double(arrCantidadOrigen[indice]).doubleValue()) {

                        //
                        valida.reasignar("EXCEDE CANTIDAD FACTURA ORIGEN", "");

                        //
                        request.setAttribute("validacion", valida);
                        return "/jsp/gralError.jsp";
                    }
                }

                //
                ProcesoGuardaPluResurtido proceso
                                              = new ProcesoGuardaPluResurtido();

                //
                for (int indice = 0; indice < arrcantidad.length; indice++) {

                    //
                    if (arrcantidad[indice].length() == 0) {
                        continue;
                    }

                    //
                    double xCantidad =
                            new Double(arrcantidad[indice]).doubleValue();
                    double xVrVentaUnitario =
                            new Double(ArrVrVentaUnitario[indice]).doubleValue();
                    double xPorcentajeIva   =
                            new Double(arrPorcentajeIva[indice]).doubleValue();
                    double xVrCosto         =
                            new Double(arrVrCosto[indice]).doubleValue();


                    //valida el idTercero sea el mismo para todos
                    String strIdReferencia = arrIdReferencia[indice];
                    int xItemPadre = 0;
                    String xComentario = "ninguno";
                    String xIdResponsable = "0";
                    int xIdClasificacion = 0;

                    //
                    int maximoItem = proceso.guarda(idLog,
                            strIdReferencia,
                            xCantidad * xSignoOperacionNota ,
                            xVrVentaUnitario,
                            xPorcentajeIva,
                            xVrCosto,
                            xItemPadre,
                            xIdTipoOrdenNotaTemporal,
                            xIdUsuario,
                            new Integer(xIdLocal).intValue(),
                            xIdTercero,
                            xComentario,
                            xIdResponsable,
                            xIdClasificacion);

                }

                //
                FachadaTerceroBean fachadaTerceroBean
                                                  = new FachadaTerceroBean();

                //
                fachadaTerceroBean.setIdCliente(
                        fachadaAgendaLogVisitaBean.getIdCliente());
                fachadaTerceroBean.setIdTipoTercero(xIdTipoTerceroCliente);
                fachadaTerceroBean.setIdTipoOrden(xIdTipoOrdenNotaTemporal);

                //
                ColaboraTercero colaboraTercero = new ColaboraTercero();

                //
                colaboraTercero.setIdTipoTercero(xIdTipoTerceroCliente);
                colaboraTercero.setIdCliente(
                        fachadaAgendaLogVisitaBean.getIdCliente());

                //
                fachadaTerceroBean = colaboraTercero.listaTerceroFCH();

                //
                fachadaTerceroBean.setIdTipoOrden(xIdTipoOrdenNotaTemporal);

                //
                FachadaDctoBean fachadaDctoBean = new FachadaDctoBean();

                //
                fachadaDctoBean.setIdLocal(xIdLocal);
                fachadaDctoBean.setIdTipoOrden(xIdTipoOrdenNotaTemporal);
                fachadaDctoBean.setIdDcto(xIdDcto);
                fachadaDctoBean.setIndicador(xIndicador);
                fachadaDctoBean.setIdOrden(xIdOrden);

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaDctoBean", fachadaDctoBean);

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaTerceroBean", fachadaTerceroBean);

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaAgendaLogVisitaBean",
                        fachadaAgendaLogVisitaBean);
                return "/jsp/vtaFrmFinResurtidoNota.jsp";

            }

            //
            if (accionContenedor.compareTo("Finalizar") == 0) {

                // Datos recibidos cotizaci?n
                String xIdLogActual = request.getParameter("xIdLog");
                String xIdTipoOrden = request.getParameter("xIdTipoOrden");
                String xIdLocal     = request.getParameter("xIdLocal");
                String xIdDcto      = request.getParameter("xIdDcto");
                String xIndicador   = request.getParameter("xIndicador");
                String xIdOrden     = request.getParameter("xIdOrden");
                String xIdCausa     = request.getParameter("xIdCausa");

                // Instancia el Bean de Validacion para validar los campos
                Validacion validacion = new Validacion();

                // Valida IdLogActual
                validacion.reasignar("IdLogActual", xIdLogActual);
                validacion.validarCampoEntero();
                if (validacion.isValido() == false) {

                    // Aqui escribe el Bean de Validacion en el Request para manejar el error
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralErrFinalizaCotizacion.jsp";
                }

                 // Valida IdLogActual
                validacion.reasignar("CAUSA", xIdCausa);
                validacion.validarCampoEntero();
                if (validacion.isValido() == false) {

                    // Aqui escribe el Bean de Validacion en el Request para manejar el error
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralError.jsp";
                }

                //
                FachadaTerceroBean fachadaTerceroBean
                                           = new FachadaTerceroBean();

                //
                ColaboraTercero colaboraTercero = new ColaboraTercero();

                colaboraTercero.setIdCliente(
                        fachadaAgendaLogVisitaBean.getIdCliente());
                colaboraTercero.setIdTipoTercero(xIdTipoTerceroCliente);

                //
                fachadaTerceroBean =
                        colaboraTercero.listaTerceroFCH();

                //
                DctoOrdenBean dctoOrdenBean = new DctoOrdenBean();

                //
                dctoOrdenBean.setIdLog(xIdLogActual);
                dctoOrdenBean.setIdTipoOrden(xIdTipoOrdenNota);

                // existePedido
                boolean existePedido = dctoOrdenBean.existePedido();

                if (existePedido) {

                    //
                    validacion.reasignar("NOTA CREDITO", "");
                    validacion.setCodigoError("Error PEDIDO");
                    validacion.setDescripcionError("Nota Credito YA CONFIRMADO");
                    validacion.setSolucion("Iniciar NOTA CREDITO");

                    // Aqui escribe el Bean de Validacion en el Request para manejar el error
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralErrFinalizaCotizacion.jsp";

                }

                //
                FachadaDctoBean fachadaDctoBean
                                           = new FachadaDctoBean();
                //
                DctoBean dctoBean          = new DctoBean();

                //
                dctoBean.setIdLocal(xIdLocal);
                dctoBean.setIdTipoOrden(xIdTipoOrdenOrigen);
                dctoBean.setIdOrden(xIdOrden);
                dctoBean.setIndicador(xIndicador);

                //
                fachadaDctoBean            = dctoBean.listaUnPagoOrden();

                //
                double xVrSaldoDctoOrigen  =
                                    (fachadaDctoBean.getVrBase()         +
                                     fachadaDctoBean.getVrIva()          +
                                     fachadaDctoBean.getVrImpoconsumo()  -
                                     fachadaDctoBean.getVrPago()         -
                                     fachadaDctoBean.getVrRteFuente()    -
                                     fachadaDctoBean.getVrDsctoFcro()    -
                                     fachadaDctoBean.getVrRteIva()       -
                                     fachadaDctoBean.getVrRteIca());

                                //
                double xVrRteFuenteTotal  =
                                     fachadaDctoBean.getVrRteFuente();

                //
                double xVrPagoDctoOrigen  =
                                     fachadaDctoBean.getVrPago();

                int xSignoOperacion       = 1;
               

                // confirmaPedido
                ProcesoIngresoNota proceso = new ProcesoIngresoNota();

                // confirma
                dctoBean =
                        proceso.ingresa(new Integer(xIdLocal).intValue(),
                                           xIdTipoOrdenNota,
                                           new Integer(xIdOrden).intValue(),
                                           new Integer(xIdLogActual).intValue(),
                                           xIdTipoOrdenNotaTemporal,
                                           xSignoOperacion,
                                           new Integer(xIdDcto).intValue(),
                                           new Integer(xIndicador).intValue(),
                                           new Integer(xIdCausa).intValue(),
                                           xVrRteFuenteTotal);

                //
                double xVrSaldoDcto        =
                                    (dctoBean.getVrBase()         +
                                     dctoBean.getVrIva()          +
                                     dctoBean.getVrImpoconsumo()  -
                                     dctoBean.getVrPago()         -
                                     dctoBean.getVrRteFuente()    -
                                     dctoBean.getVrDsctoFcro()    -
                                     dctoBean.getVrRteIva()       -
                                     dctoBean.getVrRteIca());

                //
                double xVrPagoDcto        = xVrSaldoDcto * (-1);

                // devolucion dinero
                if (xVrPagoDctoOrigen >= xVrPagoDcto) {

                    //
                    ProcesoPagoNota procesoPagoNota
                                                   = new ProcesoPagoNota();
                    //
                    dctoBean.setVrPago(dctoBean.getVrBase()         +
                                       dctoBean.getVrIva()          +
                                       dctoBean.getVrImpoconsumo()  -
                                       dctoBean.getVrRteFuente()    -
                                       dctoBean.getVrDsctoFcro()    -
                                       dctoBean.getVrRteIva()       -
                                       dctoBean.getVrRteIca());

                    //
                    procesoPagoNota.confirma(dctoBean,
                                           new Integer(xIdLogActual).intValue(),
                                           xIdMedio);

                }

                //
                GeneraPDFNota generaPDFNota = new GeneraPDFNota();

                //
                generaPDFNota.setIdLocal(dctoBean.getIdLocalStr());
                generaPDFNota.setIdOrden(dctoBean.getIdOrden());
                generaPDFNota.setIdTipoOrden(dctoBean.getIdTipoOrdenStr());

                //
                generaPDFNota.generaPdf(request, response);

                // finalizaVisita
                agendaLogVisitaBean.setIdLog(xIdLogActual);
                agendaLogVisitaBean.setIdCliente(
                        fachadaAgendaLogVisitaBean.getIdCliente());
                agendaLogVisitaBean.setIdUsuario(
                        fachadaAgendaLogVisitaBean.getIdUsuario());
                agendaLogVisitaBean.setIdEstadoTx(xIdEstadoTxSinTX);
                agendaLogVisitaBean.setIdTipoOrden(
                        dctoBean.getIdTipoOrdenStr());
                agendaLogVisitaBean.setIdLocal(dctoBean.getIdLocalStr());
                agendaLogVisitaBean.setFechaVisitaStr(strFechaVisita);
                agendaLogVisitaBean.setIdEstadoVisita(xIdEstadoVisitaConcluido);
                agendaLogVisitaBean.setEstado(xEstadoTerminado);
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

            if (accionContenedor.compareTo("Consultar") == 0) {

                //
                String fechaInicial = request.getParameter("fechaInicial");
                String fechaFinal = request.getParameter("fechaFinal");
                String xIdCliente = request.getParameter("xIdCliente");
                String xIdTipoOrden = request.getParameter("xIdTipoOrden");
                String xIdTipoTercero = request.getParameter("xIdTipoTercero");

                // Bean de ValidacionLogPcBean
                ValidacionColaboraReporteDctoBean campoAValidar
                        = new ValidacionColaboraReporteDctoBean("fechaInicial",
                                                                 fechaInicial);

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

                // fachadaColaboraLogisticaBean
                FachadaColaboraLogisticaBean fachadaColaboraLogisticaBean
                                           = new FachadaColaboraLogisticaBean();
                //
                fachadaColaboraLogisticaBean.setIdUsuario(idUsuario);
                fachadaColaboraLogisticaBean.setIdCliente(
                        fachadaAgendaLogVisitaBean.getIdCliente());
                fachadaColaboraLogisticaBean.setFechaInicial(fechaInicial);
                fachadaColaboraLogisticaBean.setFechaFinal(fechaFinal);
                fachadaColaboraLogisticaBean.setIdLocal(xIdLocalUsuario);
                fachadaColaboraLogisticaBean.setIdTipoOrdenCadena(
                        xIdTipoOrdenCadena);
                fachadaColaboraLogisticaBean.setIndicadorInicial(
                                                             xIndicadorInicial);
                fachadaColaboraLogisticaBean.setIndicadorFinal(
                                                               xIndicadorFinal);

                //
                FachadaTerceroBean fachadaTerceroBean
                                                     = new FachadaTerceroBean();

                //
                fachadaTerceroBean.setIdCliente(xIdCliente);
                fachadaTerceroBean.setIdTipoOrden(xIdTipoOrden);
                fachadaTerceroBean.setIdTipoTercero(xIdTipoTercero);
                fachadaTerceroBean.setIdLocal(xIdLocalUsuario);

                // Aqui escribe el Bean de Validacion en el Request
                request.setAttribute("fachadaTerceroBean",
                        fachadaTerceroBean);
                request.setAttribute("fachadaAgendaLogVisitaBean",
                        fachadaAgendaLogVisitaBean);
                request.setAttribute("fachadaColaboraLogisticaBean",
                        fachadaColaboraLogisticaBean);

                //
                return "/jsp/vtaFrmLstResurtidoNota.jsp";

            }

            //
            if (accionContenedor.compareTo("Listar") == 0) {

                //
                GeneraPDFCxC generaPDFCxC = new GeneraPDFCxC();

                /*
                generaPDFCxC.setIdLocal(xIdLocal);
                generaPDFCxC.setIdTipoOrden(xIdTipoOrdenPedido);
                generaPDFCxC.setIdCliente(
                fachadaAgendaLogVisitaBean.getIdCliente());*/

                //
                generaPDFCxC.generaPdf(request, response);

                //
                return "/jsp/empty.htm";
            }

        }

        return "/jsp/empty.htm";

    }
}