package com.solucionesweb.losservlets;

// Importa los paquetes del lenguaje especificamente io y los
// que permiten trabajar el servlet.
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//
import java.util.logging.Level;
import java.util.logging.Logger;

//
import com.solucionesweb.lasayudas.ProcesoIp;

import com.solucionesweb.lasayudas.ProcesoIngresoResurtido;

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
import com.solucionesweb.lasayudas.ProcesoGuardaPluOrden;
import com.solucionesweb.lasayudas.ProcesoTrasladoEnviaRecibeXML;

import com.solucionesweb.losbeans.fachada.FachadaDctoBean;

//
import com.solucionesweb.losbeans.colaboraciones.ColaboraResurtidoOrden;
import com.solucionesweb.losbeans.fachada.FachadaColaboraHistoriaBean;
import com.solucionesweb.losbeans.fachada.FachadaLocalIp;
import com.solucionesweb.losbeans.negocio.DctoOrdenBean;
import com.solucionesweb.losbeans.negocio.LocalIpBean;
/**
 * Consiste en la recepción y legalización de una mercancía cuando esta no se ha pedido. /
 * vtaContenedorResurtidoTraslado.jsp/
 * 
 * Este servlet implementa la interface GralManejadorRequest /
 */
public class VtasAdmResurtidoTrasladoManejadorRequest
        implements GralManejadorRequest {
 /**
    * BUTTON--
    * ("Crear")-Permite ingresar datos para un resurtido de mercancia/
    * ("Consultar")-Permite consultar un traslado /
    * ("Traer")-Permite ver un listado de pedidos sin legalizar /
    * ("Listar")-Permite ver un listado /
    * ("Regresar")-("Salir")-Retorna al menu principal/
    * ("+Productos")-Permite buscar productos /
    * ("Confirmar")-Permite confirmar las cantidades de los productos /
    * ("Liquidar")-Permite calcular valores que estan en la factura del proveedor 
    *                                       cuando llegan los productos a la bodega /
    * ("Legalizar")-Permite finalizar el proceso /
    * ("Imprimir")-Permite generar un pdf /
    * ("Eliminar")-Permite borrar procesos sin terminar /
    * 
    * Metodo contructor por defecto, es decir, sin parametros/
    */
    public VtasAdmResurtidoTrasladoManejadorRequest() { }
     /**
     * BUTTON PARAMETER--
     * "#Dias Historia"-Numero de dias de historia/
     * "Fecha corte"-Fecha de corte/
     * "#Dias Inventario"-Numero de dias de inventario/
     * "Proveedor"-Selecciona el proveedor /
     */
    public String generaPdf(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {


        //
        String accionContenedor = request.getParameter("accionContenedor");

        //
        System.out.println("accionContenedor :" + accionContenedor);

        //---
        int xIdTipoOrdenTraslado        = 2;
        int xIdTipoOrdenTrasladoProceso = xIdTipoOrdenTraslado + 50;
        int xIdTipoOrdenDespacho        = 5;

        //
        HttpSession sesion = request.getSession();

        //
        UsuarioBean usuarioBean =
                (UsuarioBean) sesion.getAttribute("usuarioBean");

        //
        String xIdLocalUsuario = usuarioBean.getIdLocalUsuarioStr();
        String xIdUsuario      = usuarioBean.getIdUsuarioSf0();
        int xEstadoActivo      = 9;
        int xEstadoSuspendido  = 8;

        //
        int idPeriodo = 200611;
        int estadoAtendido = 1; // visitaActiva
        int estadoProgramada = 9; // visitaProgramada
        int idEstadoVisita = 1; // Programada
        String xIdSucursal = "";
        int xIdTipoOrdenAjusteTraslado = xIdTipoOrdenTraslado + 20;
        int xIdTipoOrdenAjusteTrasladoProceso =
                xIdTipoOrdenAjusteTraslado + 50;

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
        FachadaAgendaLogVisitaBean fachadaAgendaLogVisitaBean
                                   = new FachadaAgendaLogVisitaBean();

        //
        AgendaLogVisitaBean agendaLogVisitaBean = new AgendaLogVisitaBean();

        //
        agendaLogVisitaBean.setEstado(xEstadoActivo);
        agendaLogVisitaBean.setFechaVisita(strFechaVisita);
        agendaLogVisitaBean.setIdUsuario(xIdUsuario);

        //
        fachadaAgendaLogVisitaBean = agendaLogVisitaBean.listaLogActivo();

        //
        int xIdLogActual = fachadaAgendaLogVisitaBean.getIdLog();

        // valida orden diferente en proceso
        boolean xOrdenDiferente      =
                agendaLogVisitaBean.validaDiferenteOrdenProceso(
                                                   xIdTipoOrdenTrasladoProceso);

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
           boolean okRetirar =
                        agendaLogVisitaBean.actualizaLogVisitaUsuario(
                        estadoProgramada);

           // estadoActivo = 9
           agendaLogVisitaBean.setEstado(estadoProgramada);

           //
           boolean okIngreso = agendaLogVisitaBean.ingresaLogVisita();

           //
           xIdLogActual = idLog;


        } else {

          //
          agendaLogVisitaBean.setEstado(xEstadoSuspendido);
          agendaLogVisitaBean.setIdLog(xIdLogActual);

          //
          fachadaAgendaLogVisitaBean =
                                  agendaLogVisitaBean.listaLogSuspendidoFCH(
                                                  xIdTipoOrdenTrasladoProceso);

          //
          if (fachadaAgendaLogVisitaBean.getIdLog()>0)
                           xIdLogActual = fachadaAgendaLogVisitaBean.getIdLog();

        }

        // Validacion de accion relacionada con el formulario requerido
        if (accionContenedor != null) {

            //
            if (accionContenedor.compareTo("Salir") == 0) {
                return "/jsp/empty.htm";

            }

              if (accionContenedor.compareTo("Regresar") == 0) {
                return "/jsp/empty.htm";

            }

            //
            if (accionContenedor.compareTo("Ajustar") == 0) {

                //
                String xIdLocal     = request.getParameter("xIdLocal");
                String xIdTipoOrden = request.getParameter("xIdTipoOrden");
                String xIdLog       = request.getParameter("xIdLog");

                //
                String xIdPluArr[]      = request.getParameterValues("xIdPlu");

                //
                String xCantidadArr[]   =
                                        request.getParameterValues("xCantidad");

                //
                Validacion valida       = new Validacion();

                //
                for (int indice = 0; indice < xCantidadArr.length; indice++) {

                    //
                    valida.reasignar("CANTIDAD", xCantidadArr[indice]);

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
                DctoOrdenDetalleBean dctoOrdenDetalleBean
                                            = new DctoOrdenDetalleBean();

                //
                for (int indice = 0; indice < xCantidadArr.length; indice++) {

                    //
                    dctoOrdenDetalleBean.setIdLocal(xIdLocal);
                    dctoOrdenDetalleBean.setIdTipoOrden(xIdTipoOrden);
                    dctoOrdenDetalleBean.setIdLog(xIdLog);
                    dctoOrdenDetalleBean.setCantidadPedida(
                                                          xCantidadArr[indice]);
                    dctoOrdenDetalleBean.setIdPlu(xIdPluArr[indice]);
                    dctoOrdenDetalleBean.setVrCostoResurtido(0);

                    //
                    dctoOrdenDetalleBean.actualizaResurtido();

                }

                //
                FachadaDctoBean fachadaDctoBean
                                           = new FachadaDctoBean();

                // confirmaPedido
                ProcesoIngresoResurtido proceso
                                            = new ProcesoIngresoResurtido();

                //
                fachadaDctoBean =  proceso.ingresa(
                                               new Integer(xIdLocal).intValue(),
                                               xIdTipoOrdenTraslado,
                                               new Integer(xIdLog).intValue(),
                                               xIdTipoOrdenTrasladoProceso);



                //
                GeneraPDFResurtido generaPDF = new GeneraPDFResurtido();

                //
                generaPDF.setIdLocal(xIdLocalUsuario);
                generaPDF.setIdOrden(fachadaDctoBean.getIdOrden());
                generaPDF.setIdTipoOrden(fachadaDctoBean.getIdTipoOrden());
                generaPDF.setTituloReporte("TRASLADO");
                generaPDF.setReporteName("RepEmpresaResurtidoCarta");

                //
                generaPDF.generaPdf(request, response);

                //
                int xIdEstadoTxSinTx  = 1;
                int tareaVisita       = 6;
                int estadoTerminado   = 1;

                // finalizaVisita
                agendaLogVisitaBean.setIdLog(xIdLogActual);
                agendaLogVisitaBean.setIdCliente(xIdUsuario);
                agendaLogVisitaBean.setIdUsuario(xIdUsuario);
                agendaLogVisitaBean.setIdTipoOrden(
                        fachadaDctoBean.getIdTipoOrdenStr());
                agendaLogVisitaBean.setIdEstadoTx(xIdEstadoTxSinTx);
                agendaLogVisitaBean.setIdLocal(fachadaDctoBean.getIdLocalStr());
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

            //
            if (accionContenedor.compareTo("tomarAjustar") == 0) {

                //
                String xIdLocal        = request.getParameter("xIdLocal");
                String xIdOrden        = request.getParameter("xIdOrden");
                String xIdTipoOrden    = request.getParameter("xIdTipoOrden");
                String xIdLog       = request.getParameter("xIdLog");

                //
                FachadaColaboraDctoOrdenBean fachadaColaboraDctoOrdenBean
                                           = new FachadaColaboraDctoOrdenBean();

                //
                ColaboraResurtidoOrden colaboraResurtidoOrden
                                           = new ColaboraResurtidoOrden();

                //
                colaboraResurtidoOrden.setIdLocal(xIdLocal);
                colaboraResurtidoOrden.setIdOrden(xIdOrden);
                colaboraResurtidoOrden.setIdTipoOrden(xIdTipoOrden);
                colaboraResurtidoOrden.setIdLog(xIdLog);

                //
                fachadaColaboraDctoOrdenBean
                                      = colaboraResurtidoOrden.listaAjusteFCH();

                //
                request.setAttribute("fachadaColaboraDctoOrdenBean",
                                                  fachadaColaboraDctoOrdenBean);
                //
                return "/jsp/vtaFrmAjuResurtidoTraslado.jsp";

            }

            // Eliminar
            if (accionContenedor.compareTo("Eliminar") == 0) {

                //
                String xIdLocal        = request.getParameter("xIdLocal");
                String xIdOrden        = request.getParameter("xIdOrden");
                String xIdTipoOrden    = request.getParameter("xIdTipoOrden");
                String xIdLog          = request.getParameter("xIdLog");

                //
                int xIdEstadoTxSinTx  = 1;
                int tareaVisita       = 6;
                int estadoTerminado   = 1;

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
                String xIdLocal        = request.getParameter("xIdLocal");
                String xIdOrden        = request.getParameter("xIdOrden");
                String xIdTipoOrden    = request.getParameter("xIdTipoOrden");
                String xIdLog          = request.getParameter("xIdLog");

                //
                GeneraPDFResurtidoSolicitado generaPDFResurtidoSolicitado =
                                             new GeneraPDFResurtidoSolicitado();

                //
                generaPDFResurtidoSolicitado.setIdLocal(xIdLocal);
                generaPDFResurtidoSolicitado.setIdOrden(xIdOrden);
                generaPDFResurtidoSolicitado.setIdTipoOrden(xIdTipoOrden);
                generaPDFResurtidoSolicitado.setIdLog(xIdLog);
                generaPDFResurtidoSolicitado.setReporteName(
                                          "RepEmpresaResurtidoSolicitadoCarta");

                //
                generaPDFResurtidoSolicitado.setTituloReporte(
                                                         "TRASLADO SILICITADO");

                //
                generaPDFResurtidoSolicitado.generaPdf(request, response);

            }

            // tomar
            if (accionContenedor.compareTo("tomar") == 0) {

                //
                String xIdLocal        = request.getParameter("xIdLocal");
                String xIdOrden        = request.getParameter("xIdOrden");
                String xIdTipoOrden    = request.getParameter("xIdTipoOrden");

                //
                GeneraPDFResurtido generaPDFResurtido = new GeneraPDFResurtido();

                //
                generaPDFResurtido.setIdLocal(xIdLocal);
                generaPDFResurtido.setIdOrden(xIdOrden);
                generaPDFResurtido.setIdTipoOrden(xIdTipoOrden);
                generaPDFResurtido.setReporteName("RepEmpresaResurtidoCarta");

                //
                generaPDFResurtido.setTituloReporte("TRASLADO");

                //
                generaPDFResurtido.generaPdf(request, response);

            }

            // Listar
            if (accionContenedor.compareTo("Listar") == 0) {

                //
                String xIdTercero      = request.getParameter("xIdLocal");
                String xFechaCorte     = request.getParameter("xFechaCorte");

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
                int xDiasHistoria     = 0;
                int xDiasInventario   = 0;


                //
                FachadaDctoOrdenBean fachadaDctoOrdenBean
                                                  = new FachadaDctoOrdenBean();

                //
                fachadaDctoOrdenBean.setDiasHistoria(xDiasHistoria);
                fachadaDctoOrdenBean.setDiasInventario(xDiasInventario);
                fachadaDctoOrdenBean.setIdCliente(xIdTercero);
                fachadaDctoOrdenBean.setFechaOrden(xFechaCorte);
                fachadaDctoOrdenBean.setIdLocal(xIdLocalUsuario);
                fachadaDctoOrdenBean.setIdTipoOrden(xIdTipoOrdenTraslado);
                fachadaDctoOrdenBean.setIdUsuario(xIdUsuario);
                fachadaDctoOrdenBean.setIdLog(xIdLogActual);

                //
                request.setAttribute("fachadaDctoOrdenBean",
                        fachadaDctoOrdenBean);

                //
                return "/jsp/vtaFrmConResurtidoTraslado.jsp";

            }

            //
            if (accionContenedor.compareTo("Legalizar") == 0) {

                //
                String xIdLocal     = request.getParameter("xIdLocal");
                String xIdTipoOrden = request.getParameter("xIdTipoOrden");
                String xIdLog       = request.getParameter("xIdLog");
                String xIdPluArr[]      = request.getParameterValues("xIdPlu");
                String xCantidadArr[]   =
                                        request.getParameterValues("xCantidad");
                String xVrCostoResurtidoArr[]   =
                                request.getParameterValues("xVrCostoResurtido");


                //
                Validacion valida       = new Validacion();

                //
                for (int indice = 0; indice < xCantidadArr.length; indice++) {

                    //
                    valida.reasignar("CAN.REC.", xCantidadArr[indice]);

                    //
                    valida.validarCampoDoublePositivo();

                    //isValido
                    if (!valida.isValido()) {

                        //
                        request.setAttribute("validacion", valida);
                        return "/jsp/gralError.jsp";
                    }

                    //
                    valida.reasignar("VR.COSTO", xVrCostoResurtidoArr[indice]);

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
                DctoOrdenDetalleBean dctoOrdenDetalleBean
                                            = new DctoOrdenDetalleBean();

                //
                for (int indice = 0; indice < xCantidadArr.length; indice++) {

                    //
                    dctoOrdenDetalleBean.setIdLocal(xIdLocal);
                    dctoOrdenDetalleBean.setIdTipoOrden(xIdTipoOrden);
                    dctoOrdenDetalleBean.setIdLog(xIdLog);
                    dctoOrdenDetalleBean.setCantidadPedida(
                                                          xCantidadArr[indice]);
                    dctoOrdenDetalleBean.setIdPlu(xIdPluArr[indice]);
                    dctoOrdenDetalleBean.setVrCostoResurtido(
                                                  xVrCostoResurtidoArr[indice]);

                    //
                    dctoOrdenDetalleBean.actualizaResurtido();

                }

                //
                FachadaDctoBean fachadaDctoBean
                                           = new FachadaDctoBean();

                // confirmaPedido
                ProcesoIngresoResurtido proceso
                                            = new ProcesoIngresoResurtido();

                //
                fachadaDctoBean =  proceso.ingresa(
                                               new Integer(xIdLocal).intValue(),
                                               xIdTipoOrdenTraslado,
                                               new Integer(xIdLog).intValue(),
                                               xIdTipoOrdenTrasladoProceso);


                //
                GeneraPDFResurtido generaPDF = new GeneraPDFResurtido();

                //
                generaPDF.setIdLocal(xIdLocalUsuario);
                generaPDF.setIdOrden(fachadaDctoBean.getIdOrden());
                generaPDF.setIdTipoOrden(fachadaDctoBean.getIdTipoOrden());
                generaPDF.setTituloReporte("TRASLADO");
                generaPDF.setReporteName("RepEmpresaResurtidoCarta");

                //
                generaPDF.generaPdf(request, response);

                //
                int xIdEstadoTxSinTx  = 1;
                int tareaVisita       = 6;
                int estadoTerminado   = 1;

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

            //
            if (accionContenedor.compareTo("coger") == 0) {

                //
                String xIdLocal     = request.getParameter("xIdLocal");
                String xIdTipoOrden = request.getParameter("xIdTipoOrden");
                String xIdLog       = request.getParameter("xIdLog");

                //
                FachadaColaboraDctoOrdenBean fachadaColaboraDctoOrdenBean
                                           = new FachadaColaboraDctoOrdenBean();

                //
                ColaboraResurtidoOrden colaboraResurtidoOrden
                                           = new ColaboraResurtidoOrden();

                //
                colaboraResurtidoOrden.setIdLocal(xIdLocal);
                colaboraResurtidoOrden.setIdTipoOrden(xIdTipoOrden);
                colaboraResurtidoOrden.setIdLog(xIdLog);

                //
                fachadaColaboraDctoOrdenBean
                                    = colaboraResurtidoOrden.listaLegalizaFCH();


                //
                request.setAttribute("fachadaColaboraDctoOrdenBean",
                                                  fachadaColaboraDctoOrdenBean);
                //
                return "/jsp/vtaFrmLegResurtidoTraslado.jsp";

            }

            // Consultar
            if (accionContenedor.compareTo("Consultar") == 0) {

                //
                String xDiasHistoria   = request.getParameter("xDiasHistoria");
                String xDiasInventario =
                                        request.getParameter("xDiasInventario");
                String xIdTercero      = request.getParameter("xIdLocal");
                String xFechaCorte     = request.getParameter("xFechaCorte");

                // validacion
                Validacion validacion = new Validacion();

                //
                validacion.reasignar("#DIAS HISTORIA", xDiasHistoria);

                validacion.validarCampoEntero();

                // isValido
                if (!validacion.isValido()) {

                    //
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralError.jsp";

                }

                //
                validacion.reasignar("#DIAS INVENTARIO", xDiasInventario);

                validacion.validarCampoEntero();

                // isValido
                if (!validacion.isValido()) {

                    //
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralError.jsp";

                }

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
                FachadaDctoOrdenBean fachadaDctoOrdenBean
                                                  = new FachadaDctoOrdenBean();

                //
                fachadaDctoOrdenBean.setDiasHistoria(xDiasHistoria);
                fachadaDctoOrdenBean.setDiasInventario(xDiasInventario);
                fachadaDctoOrdenBean.setIdCliente(xIdTercero);
                fachadaDctoOrdenBean.setFechaOrden(xFechaCorte);
                fachadaDctoOrdenBean.setIdLocal(xIdLocalUsuario);
                fachadaDctoOrdenBean.setIdTipoOrden(xIdTipoOrdenTrasladoProceso);
                fachadaDctoOrdenBean.setIdUsuario(xIdUsuario);
                fachadaDctoOrdenBean.setIdLog(xIdLogActual);

                //
                request.setAttribute("fachadaDctoOrdenBean",
                        fachadaDctoOrdenBean);

                //
                return "/jsp/vtaFrmLstResurtidoTraslado.jsp";

            }

            // Confirmar
            if (accionContenedor.compareTo("Guardar") == 0) {

                //
                String xDiasHistoria   = request.getParameter("xDiasHistoria");
                String xDiasInventario =
                                        request.getParameter("xDiasInventario");
                String xIdTercero      = request.getParameter("xIdTercero");
                String xFechaCorte     = request.getParameter("xFechaCorte");
                String xIdLocal        = request.getParameter("xIdLocal");
                String xIdTipoOrden    = request.getParameter("xIdTipoOrden");
                String xIdPluArr[]     = request.getParameterValues("xIdPlu");
                String xCantidadPedidaArr[] =
                                  request.getParameterValues("xCantidadPedida");
                String xIdLog          = request.getParameter("xIdLog");


                // validacion
                Validacion validacion = new Validacion();


                //
                for (int indice = 0; indice <
                                          xCantidadPedidaArr.length; indice++) {

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

                }

                //
                xIdLogActual = new Integer(xIdLog).intValue();

                //
                ProcesoGuardaPlu procesoGuardaPlu = new ProcesoGuardaPlu();

                //
                for (int indice = 0; indice <
                                          xCantidadPedidaArr.length; indice++) {

                    //
                    if (new Double(xCantidadPedidaArr[indice]).doubleValue()>0) {

                       //
                       procesoGuardaPlu.guarda(xIdLogActual,
                                               xIdPluArr[indice],
                                               xCantidadPedidaArr[indice],
                                               "0",
                                               xIdTipoOrdenTrasladoProceso,
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
                boolean okActualiza  =
                        agendaLogVisitaBean.actualizaLogVisitaUsuario(
                        estadoProgramada);


                //
                FachadaDctoOrdenBean fachadaDctoOrdenBean
                                             = new FachadaDctoOrdenBean();

                //
                fachadaDctoOrdenBean.setDiasHistoria(xDiasHistoria);
                fachadaDctoOrdenBean.setDiasInventario(xDiasInventario);
                fachadaDctoOrdenBean.setIdCliente(xIdTercero);
                fachadaDctoOrdenBean.setFechaOrden(xFechaCorte);
                fachadaDctoOrdenBean.setIdLocal(xIdLocalUsuario);
                fachadaDctoOrdenBean.setIdTipoOrden(xIdTipoOrdenTrasladoProceso);
                fachadaDctoOrdenBean.setIdUsuario(xIdUsuario);
                fachadaDctoOrdenBean.setIdLog(xIdLogActual);

                //
                request.setAttribute("fachadaDctoOrdenBean",
                        fachadaDctoOrdenBean);

                //
                return "/jsp/vtaContenedorResurtidoTraslado.jsp";

            }

            // Elaborar / Crear
            if ((accionContenedor.compareTo("Elaborar") == 0) ||
                (accionContenedor.compareTo("Crear") == 0)) {

                //
                String xDiasHistoria   = request.getParameter("xDiasHistoria");
                String xDiasInventario =
                                        request.getParameter("xDiasInventario");
                String xIdTercero      = request.getParameter("xIdLocal");
                String xFechaCorte     = request.getParameter("xFechaCorte");
                String xIdLog          = request.getParameter("xIdLog");                
                
                //
                if ((accionContenedor.compareTo("Elaborar") == 0)) {
                    
                   // 
                   xIdLogActual  =  new Integer(xIdLog).intValue();
                    
                }


                // validacion
                Validacion validacion = new Validacion();

                //
                validacion.reasignar("#DIAS HISTORIA", xDiasHistoria);

                validacion.validarCampoEntero();

                // isValido
                if (!validacion.isValido()) {

                    //
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralError.jsp";

                }

                //
                validacion.reasignar("#DIAS INVENTARIO", xDiasInventario);

                validacion.validarCampoEntero();

                // isValido
                if (!validacion.isValido()) {

                    //
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralError.jsp";

                }

                //
                validacion.reasignar("BODEGA ORIGEN", xIdTercero);

                //
                validacion.validarCampoDouble();

                // isValido
                if (!validacion.isValido()) {

                    //
                    validacion.setDescripcionError("ERROR, FALTA SELECCIONAR BODEGA ORIGEN");

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
                FachadaDctoOrdenBean fachadaDctoOrdenBean
                                             = new FachadaDctoOrdenBean();

                //
                fachadaDctoOrdenBean.setDiasHistoria(xDiasHistoria);
                fachadaDctoOrdenBean.setDiasInventario(xDiasInventario);
                fachadaDctoOrdenBean.setIdCliente(xIdTercero);
                fachadaDctoOrdenBean.setFechaOrden(xFechaCorte);
                fachadaDctoOrdenBean.setIdLocal(xIdLocalUsuario);
                fachadaDctoOrdenBean.setIdTipoOrden(xIdTipoOrdenTrasladoProceso);
                fachadaDctoOrdenBean.setIdUsuario(xIdUsuario);
                fachadaDctoOrdenBean.setIdLog(xIdLogActual);

                //
                request.setAttribute("fachadaDctoOrdenBean",
                        fachadaDctoOrdenBean);

                //
                return "/jsp/vtaFrmIngResurtidoTraslado.jsp";

            }


                if (accionContenedor.compareTo("Traer") == 0) {

                  //
                String xIdTercero = request.getParameter("xIdLocal");
                String xFechaCorte = request.getParameter("xFechaCorte");

                //
                int xIdLocal = new Integer(xIdTercero).intValue();
                int usuarioLocal = new Integer(xIdLocalUsuario).intValue();

                // validacion
                Validacion validacion = new Validacion();

                //
                validacion.reasignar("BODEGA", xIdTercero);

                //
                validacion.validarCampoDouble();

                // isValido
                if (!validacion.isValido()) {

                    //
                    validacion.setDescripcionError("ERROR, FALTA SELECCIONAR BODEGA");

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


               FachadaDctoOrdenBean fachadaDctoOrdenBean =
                                                    new FachadaDctoOrdenBean();


               DctoOrdenBean dctoOrdenbean = new DctoOrdenBean();

               
               fachadaDctoOrdenBean.setIdCliente(xIdTercero);
               fachadaDctoOrdenBean.setFechaOrden(xFechaCorte);
               fachadaDctoOrdenBean.setIdLocal(usuarioLocal);
               fachadaDctoOrdenBean.setIdTipoOrden(xIdTipoOrdenTrasladoProceso);
              
                
//             ---------------------------------------------------------------

                FachadaLocalIp fachadaLocalIp = new FachadaLocalIp();

                //
                LocalIpBean localIpBean = new LocalIpBean();

                //
                localIpBean.setIdLocal(xIdTercero);

                //
                fachadaLocalIp = localIpBean.listaUnLocal();

                request.setAttribute("fachadaLocalIp",
                        fachadaLocalIp);

                    //
                    String xHostName = fachadaLocalIp.getIp();
                    String xIdPuertoLocal = ":"
                            + fachadaLocalIp.getPuertoHttp();

                    //
                    String xPagina = xHostName
                            + xIdPuertoLocal
                            + "/Commerce/jsp/"
                            + "txTrasladoLocal.jsp"
                            + "?xIdLocalOrigen=" + usuarioLocal
                            + "&xIdTipoOrden=" + xIdTipoOrdenDespacho
                            + "&xIdTipoOrdenOrigen=" + xIdTipoOrdenTrasladoProceso;

                    //
                    ProcesoTrasladoEnviaRecibeXML procesoXML =
                            new ProcesoTrasladoEnviaRecibeXML(xPagina);

                    //
                    procesoXML.start();
//              --------------------------------------------------------------
                //
                request.setAttribute("fachadaDctoOrdenBean",
                        fachadaDctoOrdenBean);

                request.setAttribute("fachadaLocalIp",
                        fachadaLocalIp);

               try {
                 Thread.sleep(5000);
                } catch (InterruptedException ex) {

                }

                //
                return "/jsp/vtaFrmTraeResurtidoTraslado.jsp";

            }

                // Listar

             if (accionContenedor.compareTo("selecciona_despacho") == 0) {
                //
                String xIdLocal     = request.getParameter("xIdLocal");
                String xIdTipoOrden = request.getParameter("xIdTipoOrden");
                String xIdLog       = request.getParameter("xIdLog");

                //
                FachadaColaboraDctoOrdenBean fachadaColaboraDctoOrdenBean
                                           = new FachadaColaboraDctoOrdenBean();

                //
                ColaboraResurtidoOrden colaboraResurtidoOrden
                                           = new ColaboraResurtidoOrden();

                //
                colaboraResurtidoOrden.setIdLocal(xIdLocal);
                colaboraResurtidoOrden.setIdTipoOrden(xIdTipoOrden);
                colaboraResurtidoOrden.setIdLog(xIdLog);

                //
                fachadaColaboraDctoOrdenBean
                                    = colaboraResurtidoOrden.listaLegalizaFCH();


                //
                request.setAttribute("fachadaColaboraDctoOrdenBean",
                                                  fachadaColaboraDctoOrdenBean);
                //
                return "/jsp/vtaFrmLegResurtidoTrasladoTx.jsp";

            }


                //
            if (accionContenedor.compareTo("Finalizar") == 0) {


               //
                String xIdLocal     = request.getParameter("xIdLocal");
                String xIdTipoOrden = request.getParameter("xIdTipoOrden");
                String xIdLog       = request.getParameter("xIdLog");
                String xCantidadArr[]   =
                                        request.getParameterValues("xCantidad");
                String xIdPluArr[]      = request.getParameterValues("xIdPlu");
                


                //
                Validacion valida       = new Validacion();

                //
                for (int indice = 0; indice < xCantidadArr.length; indice++) {

                    //
                    valida.reasignar("CAN.REC.", xCantidadArr[indice]);

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
                DctoOrdenDetalleBean dctoOrdenDetalleBean
                                            = new DctoOrdenDetalleBean();

                //
                for (int indice = 0; indice < xCantidadArr.length; indice++) {

                    //
                    dctoOrdenDetalleBean.setIdLocal(xIdLocal);
                    dctoOrdenDetalleBean.setIdTipoOrden(xIdTipoOrden);
                    dctoOrdenDetalleBean.setIdLog(xIdLog);
                    dctoOrdenDetalleBean.setCantidad(xCantidadArr[indice]);
                    dctoOrdenDetalleBean.setIdPlu(xIdPluArr[indice]);
                    

                    //
                    dctoOrdenDetalleBean.actualizaResurtidoTraslado();

                }

                //
                FachadaDctoBean fachadaDctoBean
                                           = new FachadaDctoBean();

                // confirmaPedido
                ProcesoIngresoResurtido proceso
                                            = new ProcesoIngresoResurtido();

                //
                fachadaDctoBean =  proceso.ingresa(
                                               new Integer(xIdLocal).intValue(),
                                               xIdTipoOrdenTraslado,
                                               new Integer(xIdLog).intValue(),
                                               xIdTipoOrdenTrasladoProceso);


                //
                GeneraPDFResurtido generaPDF = new GeneraPDFResurtido();

                //
                generaPDF.setIdLocal(xIdLocalUsuario);
                generaPDF.setIdOrden(fachadaDctoBean.getIdOrden());
                generaPDF.setIdTipoOrden(fachadaDctoBean.getIdTipoOrden());
                generaPDF.setTituloReporte("TRASLADO");
                generaPDF.setReporteName("RepEmpresaResurtidoCarta");

                //
                generaPDF.generaPdf(request, response);

                //
                int xIdEstadoTxSinTx  = 1;
                int tareaVisita       = 6;
                int estadoTerminado   = 1;

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
            
             // TraerManual
            if (accionContenedor.compareTo("Manual") == 0) {

                //
                String xDiasHistoria   = request.getParameter("xDiasHistoria");
                String xDiasInventario =
                                        request.getParameter("xDiasInventario");
                String xIdTercero      = request.getParameter("xIdTercero");
                String xFechaCorte     = request.getParameter("xFechaCorte");

                // validacion
                Validacion validacion = new Validacion();

                //
                validacion.reasignar("#DIAS HISTORIA", xDiasHistoria);

                validacion.validarCampoEntero();

                // isValido
                if (!validacion.isValido()) {

                    //
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralError.jsp";

                }

                //
                validacion.reasignar("#DIAS INVENTARIO", xDiasInventario);

                validacion.validarCampoEntero();

                // isValido
                if (!validacion.isValido()) {

                    //
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralError.jsp";

                }

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
                FachadaDctoOrdenBean fachadaDctoOrdenBean
                                                  = new FachadaDctoOrdenBean();

                //
                fachadaDctoOrdenBean.setDiasHistoria(xDiasHistoria);
                fachadaDctoOrdenBean.setDiasInventario(xDiasInventario);
                fachadaDctoOrdenBean.setIdCliente(xIdTercero);
                fachadaDctoOrdenBean.setFechaOrden(xFechaCorte);
                fachadaDctoOrdenBean.setIdLocal(xIdLocalUsuario);
                fachadaDctoOrdenBean.setIdTipoOrden(xIdTipoOrdenTrasladoProceso);
                fachadaDctoOrdenBean.setIdUsuario(xIdUsuario);
                fachadaDctoOrdenBean.setIdLog(xIdLogActual);

                //
                request.setAttribute("fachadaDctoOrdenBean",
                        fachadaDctoOrdenBean);

                //
                //return "/jsp/vtaFrmLstResurtidoTraslado.jsp";
                
                return "/jsp/vtaFrmIngResurtidoTrasladoManual.jsp";


            }
            
            // Productos
            if (accionContenedor.compareTo("+Productos") == 0) {

                //
                String idLinea = request.getParameter("idLinea");
                String xIdTercero = request.getParameter("xIdTercero");
                String xFechaCorte = request.getParameter("xFechaCorte");

                //
                String strCadena = idLinea.trim();
                int lonCadena = strCadena.length();
                int posCadena = strCadena.indexOf('+', 0);
                String xNombrePlu = "";

                //
                if (posCadena > 0) {

                    //
                    idLinea = strCadena.substring(0, posCadena).trim();
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
                        xNombrePlu = idLinea;
                        idLinea = "";
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

                //
                DctoOrdenBean dctoOrdenBean = new DctoOrdenBean();

                //
                dctoOrdenBean.setIdLog(fachadaAgendaLogVisitaBean.getIdLog());
                dctoOrdenBean.setIdTipoOrden(xIdTipoOrdenTrasladoProceso);

                //
                FachadaDctoOrdenBean fachadaDctoOrdenBean = new FachadaDctoOrdenBean();
                //
                fachadaDctoOrdenBean =
                        dctoOrdenBean.listaDctoOrdenIdLogIdTipoOrden();

                fachadaDctoOrdenBean.setIdListaPrecio(1);
                fachadaDctoOrdenBean.setIdCliente(xIdTercero);
                fachadaDctoOrdenBean.setFechaOrden(xFechaCorte);
                fachadaDctoOrdenBean.setIdLocal(xIdLocalUsuario);
                fachadaDctoOrdenBean.setIdTipoOrden(xIdTipoOrdenTrasladoProceso);
                fachadaDctoOrdenBean.setIdLog(xIdLogActual);


                //
                request.setAttribute("fachadaDctoOrdenBean",
                        fachadaDctoOrdenBean);

                //
                //return "/jsp/vtaFrmLstResurtidoDespacho.jsp";
                return "/jsp/vtaFrmLstResurtidoTrasladoManual.jsp";


            }

              // Confirmar
            if (accionContenedor.compareTo("Confirmar") == 0) {

                //
                String xIdTercero = request.getParameter("xIdTercero");
                String xFechaCorte = request.getParameter("xFechaCorte");
                String xIdTipoOrden = request.getParameter("xIdTipoOrden");
                String xIdLog = request.getParameter("xIdLog");
                String xIdLocal = request.getParameter("xIdLocal");


                //
                String arrIdReferencia[] =
                        request.getParameterValues("chkIdReferencia");
                //
                String arrcantidad[] =
                        request.getParameterValues("chkCantidad");

                //
                String arrsubtotal[] =
                        request.getParameterValues("chkSubtotal");

                //
                String ArrVrVentaUnitario[] =
                        request.getParameterValues("chkVrVentaUnitario");

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

                //
                ProcesoGuardaPluOrden proceso = new ProcesoGuardaPluOrden();

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
                    double xSubtotal = new Double(arrsubtotal[indice]).doubleValue();


                    //valida el idTercero sea el mismo para todos
                    String strIdReferencia = arrIdReferencia[indice];
                    int xItemPadre = 0;
                    String xComentario = "ninguno";
                    String xIdResponsable = "0";
                    int xIdClasificacion = 0;

                    //
                    int maximoItem = proceso.guardaTraslado(xIdLogActual,
                            strIdReferencia,
                            xCantidad,
                            xVrVentaUnitario,
                            xItemPadre,
                            xIdTipoOrdenTrasladoProceso,
                            Double.parseDouble(xIdUsuario),
                            new Integer(xIdLocalUsuario).intValue(),
                            xIdTercero,
                            xComentario,
                            xIdResponsable,
                            xIdClasificacion,
                            xSubtotal);
                 }

                //
                DctoOrdenBean dctoOrdenBean = new DctoOrdenBean();

                //
                dctoOrdenBean.setIdLog(fachadaAgendaLogVisitaBean.getIdLog());
                dctoOrdenBean.setIdTipoOrden(xIdTipoOrdenTrasladoProceso);

                //
                FachadaDctoOrdenBean fachadaDctoOrdenBean = new FachadaDctoOrdenBean();

                //
                fachadaDctoOrdenBean =
                        dctoOrdenBean.listaDctoOrdenIdLogIdTipoOrden();

                //
                fachadaDctoOrdenBean.setIdListaPrecio(1);
                fachadaDctoOrdenBean.setIdCliente(xIdTercero);
                fachadaDctoOrdenBean.setFechaOrden(xFechaCorte);
                fachadaDctoOrdenBean.setIdTipoOrden(xIdTipoOrden);
                fachadaDctoOrdenBean.setIdLog(xIdLog);
                fachadaDctoOrdenBean.setIdLocal(xIdLocal);

               //
                request.setAttribute("fachadaDctoOrdenBean", fachadaDctoOrdenBean);

                //
                return "/jsp/vtaFrmIngResurtidoTrasladoManual.jsp";


            }

             // retira
            if (accionContenedor.compareTo("retira") == 0) {

                //
                String xItem = request.getParameter("xItem");
                String xIdLog = request.getParameter("xIdLog");
                //
                String xIdTercero = request.getParameter("xIdTercero");
                String xFechaCorte = request.getParameter("xFechaCorte");
                String xIdTipoOrden = request.getParameter("xIdTipoOrden");
                String xIdLocal = request.getParameter("xIdLocal");

                //
                DctoOrdenBean dctoOrdenBean = new DctoOrdenBean();

                //
                dctoOrdenBean.setIdLog(xIdLog);

                FachadaDctoOrdenBean fachadaDctoOrdenBean = new FachadaDctoOrdenBean();
                fachadaDctoOrdenBean =
                        dctoOrdenBean.listaDctoOrdenIdLog();

                //
                DctoOrdenDetalleBean dctoOrdenDetalleBean = new DctoOrdenDetalleBean();


                fachadaDctoOrdenBean =
                        dctoOrdenBean.listaDctoOrdenIdLog();

                //
                dctoOrdenDetalleBean.setIdLog(xIdLog);
                dctoOrdenDetalleBean.setIdTipoOrden(xIdTipoOrdenAjusteTrasladoProceso);
                dctoOrdenDetalleBean.setItem(xItem);
                dctoOrdenDetalleBean.setIdLocal(xIdLocalUsuario);


                //
                dctoOrdenDetalleBean.setIdLog(xIdLog);
                dctoOrdenDetalleBean.setIdTipoOrden(xIdTipoOrdenAjusteTrasladoProceso);
                dctoOrdenDetalleBean.setItem(xItem);
                dctoOrdenDetalleBean.setIdLocal(xIdLocalUsuario);

                // retiraItem
                boolean okRetiro = dctoOrdenDetalleBean.retiraItem();


                // Retira DctoOrden
                dctoOrdenBean.setIdLocal(xIdLocalUsuario);
                dctoOrdenBean.setIdTipoOrden(xIdTipoOrdenAjusteTrasladoProceso);
                dctoOrdenBean.setIdOrden(xIdLog);

                // validaArticulosxOrden
                boolean okDetalle = dctoOrdenDetalleBean.validaOrden();

                // Retira DctoOrden
                dctoOrdenBean.setIdLocal(xIdLocalUsuario);
                dctoOrdenBean.setIdTipoOrden(xIdTipoOrdenAjusteTrasladoProceso);
                dctoOrdenBean.setIdOrden(xIdLog);


                if (!okDetalle) {

                    // Retira DctoOrden
                    dctoOrdenBean.setIdLocal(xIdLocalUsuario);
                    dctoOrdenBean.setIdTipoOrden(xIdTipoOrdenAjusteTrasladoProceso);
                    dctoOrdenBean.setIdOrden(xIdLog);

                    //
                    dctoOrdenBean.retiraOrden();

                    // Retira DctoOrden
                    dctoOrdenBean.setIdLocal(xIdLocalUsuario);
                    dctoOrdenBean.setIdTipoOrden(xIdTipoOrdenAjusteTrasladoProceso);
                    dctoOrdenBean.setIdOrden(xIdLog);

                    //
                    dctoOrdenBean.retiraOrden();

                }

                //
                dctoOrdenBean.setIdLog(fachadaAgendaLogVisitaBean.getIdLog());
                dctoOrdenBean.setIdTipoOrden(xIdTipoOrdenTrasladoProceso);

                //
                fachadaDctoOrdenBean =
                        dctoOrdenBean.listaDctoOrdenIdLogIdTipoOrden();

                //
                fachadaDctoOrdenBean.setIdListaPrecio(1);
                fachadaDctoOrdenBean.setIdCliente(xIdTercero);
                fachadaDctoOrdenBean.setFechaOrden(xFechaCorte);
                fachadaDctoOrdenBean.setIdTipoOrden(xIdTipoOrden);
                fachadaDctoOrdenBean.setIdLog(xIdLog);
                fachadaDctoOrdenBean.setIdLocal(xIdLocal);

                //
                request.setAttribute("fachadaDctoOrdenBean", fachadaDctoOrdenBean);

                //
                return "/jsp/vtaFrmIngResurtidoTrasladoManual.jsp";


            }


        }

        return "/jsp/empty.htm";

    }
}