package com.solucionesweb.losservlets;

//
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//
import com.solucionesweb.lasayudas.ProcesoIp;

//
import com.solucionesweb.losbeans.utilidades.JhDate;

// Importa la clase que contiene el UsuarioBean
import com.solucionesweb.losbeans.negocio.UsuarioBean;

//
import com.solucionesweb.losbeans.negocio.AgendaLogVisitaBean;

//
import com.solucionesweb.losbeans.negocio.DctoOrdenBean;

// Importa la clase que contiene el DctoOrdenDetalleBean
import com.solucionesweb.losbeans.negocio.DctoOrdenDetalleBean;

//
import com.solucionesweb.losbeans.fachada.FachadaAgendaLogVisitaBean;
import com.solucionesweb.losbeans.fachada.FachadaDctoOrdenBean;
import com.solucionesweb.losbeans.fachada.FachadaDctoBean;

// Importa la clase ProcesoIngreso
import com.solucionesweb.lasayudas.ProcesoIngresoAjuste;

// Importa la clase que contiene el Bean de Validacion
import com.solucionesweb.losbeans.utilidades.Validacion;

// Importa la clase que contiene el Day
import com.solucionesweb.losbeans.utilidades.Day;
import com.solucionesweb.lasayudas.ProcesoGuardaAjusteOT;
import com.solucionesweb.losbeans.colaboraciones.ColaboraDctoOrdenDetalleBean;
import com.solucionesweb.losbeans.colaboraciones.ColaboraOrdenTrabajo;
import com.solucionesweb.losbeans.colaboraciones.ColaboraTercero;
import com.solucionesweb.losbeans.fachada.FachadaDctoOrdenDetalleBean;
import com.solucionesweb.losbeans.fachada.FachadaDctoOrdenProgreso;
import com.solucionesweb.losbeans.fachada.FachadaPluFicha;
import com.solucionesweb.losbeans.fachada.FachadaTerceroBean;
import com.solucionesweb.losbeans.negocio.DctoOrdenProgresoBean;
import com.solucionesweb.losbeans.utilidades.FichaTecnica;
/**
 *
 * Esta opción que despliega la interfaz MOVIMIENTO AJUSTE para Órdenes de trabajo 
 * (O.T.), que es un movimiento de inventario que aumenta o disminuye  la existencia de un producto./
 * vtaContenedorInventarioAjusteOT.jsp/
 * 
 * Este servlet implementa la interface GralManejadorRequest /
 */
public class VtasAdmInventarioAjusteOTManejadorRequest
        implements GralManejadorRequest {
    /**
     * BUTTON--
     * ("Regresar")/("Salir")-Permite retornar al menu principal/
     * ("Elaborar")-Inicia ajuste de movimiento despues de indicar la fecha del ajuste,
     *                      la bodega del ajuste y finalmente que tipo de movimiento/vtaContenedorInventarioAjuste.jsp/
     * ("O.T.")-Donde se ingresa manualmente las ordenes de trabajo que quiere mover/vtaFrmIngInventarioAjuste.jsp/
     * ("Confirmar")-Donde se confirma las cantidades de los articulos ingresados previamente/vtaFrmLstInventarioAjuste.jsp/
     * ("Legalizar")-Finaliza el proceso y genera pdf del movimiento/vtaFrmIngInventarioAjuste.jsp 
     * 
     * Metodo contructor por defecto, es decir, sin parametros/
     */
    public VtasAdmInventarioAjusteOTManejadorRequest() {
    }
    /**
     * BUTTON PARAMETER--
     * "Fecha corte"- Fecha de corte de movimiento/
     * "Bodega Origen"- Bodega de origen de movimiento/
     * "Movimiento"- Indicativo de movimiento de ajuste de inventario interno/
     * "O.T."-Ingreso orden de trabajo /
     * "Peso(Kg)"-ingreso peso de articulos /
     * "Cantidad Unidades"- Cantidad de articulos a mover /
     */
    public String generaPdf(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        //
        String accionContenedor = request.getParameter("accionContenedor");

        //
        System.out.println("accionContenedor :" + accionContenedor);

        //
        int xIdTipoOrdenAjuste = 0;
        int xIdTipoOrdenAjusteProceso = xIdTipoOrdenAjuste + 50;
        int xEstadoActivo = 9;
        int xIdListaPrecio = 1;

        //
        JhDate jhDate = new JhDate();

        //
        String fechaTxHM = null;

        //
        try {
            //
            fechaTxHM = jhDate.getDate(4, false);
        } catch (Exception ex) {
        }

        //
        Day day = new Day();

        //
        String strFechaVisita = day.getFechaFormateada();

        //
        HttpSession sesion = request.getSession();

        //
        UsuarioBean usuarioBean =
                (UsuarioBean) sesion.getAttribute("usuarioBean");

        //
        String xIdLocalUsuario = usuarioBean.getIdLocalUsuarioStr();
        Double xIdUsuario = usuarioBean.getIdUsuario();


        //
        AgendaLogVisitaBean agendaLogVisitaBean = new AgendaLogVisitaBean();
        agendaLogVisitaBean.setEstado(xEstadoActivo);
        agendaLogVisitaBean.setFechaVisitaStr(strFechaVisita);
        agendaLogVisitaBean.setIdUsuario(xIdUsuario);

        //
        FachadaAgendaLogVisitaBean fachadaAgendaLogVisitaBean = new FachadaAgendaLogVisitaBean();

        //
        fachadaAgendaLogVisitaBean =
                agendaLogVisitaBean.seleccionaVisitaEstadoFecha();

        //
        int xIdLogActual = fachadaAgendaLogVisitaBean.getIdLog();
        int xIdTipoOrdenActual = fachadaAgendaLogVisitaBean.getIdTipoOrden();

        // Validacion de accion relacionada con el formulario requerido
        if (accionContenedor != null) {

            if (accionContenedor.compareTo("Salir") == 0) {
                return "/jsp/empty.htm";

            }

            // Listar
            if (accionContenedor.compareTo("tomar") == 0) {

                //
                String xIdLocal = request.getParameter("xIdLocal");
                String xIdOrden = request.getParameter("xIdOrden");
                String xIdTipoOrden = request.getParameter("xIdTipoOrden");

                //
                GeneraPDFResurtido generaPDFResurtido = new GeneraPDFResurtido();

                //
                generaPDFResurtido.setIdLocal(xIdLocal);
                generaPDFResurtido.setIdOrden(xIdOrden);
                generaPDFResurtido.setIdTipoOrden(xIdTipoOrden);
                generaPDFResurtido.setReporteName("RepEmpresaResurtido");

                //
                generaPDFResurtido.setTituloReporte("MOVIMIENTO AJUSTE");

                //
                generaPDFResurtido.generaPdf(request, response);

            }

            // Listar
            if (accionContenedor.compareTo("Listar") == 0) {

                //
                String xIdTercero = request.getParameter("xIdTercero");
                String xFechaCorte = request.getParameter("xFechaCorte");


                // validacion
                Validacion validacion = new Validacion();

                //
                validacion.reasignar("PROVEEDOR", xIdTercero);

                //
                validacion.validarCampoDouble();

                // isValido
                if (!validacion.isValido()) {

                    //
                    validacion.setDescripcionError(
                            "ERROR, FALTA SELECCIONAR PROVEEDOR");

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
                int xDiasHistoria = 0;
                int xDiasInventario = 0;


                //
                FachadaDctoOrdenBean fachadaDctoOrdenBean = new FachadaDctoOrdenBean();

                //
                fachadaDctoOrdenBean.setDiasHistoria(xDiasHistoria);
                fachadaDctoOrdenBean.setDiasInventario(xDiasInventario);
                fachadaDctoOrdenBean.setIdCliente(xIdTercero);
                fachadaDctoOrdenBean.setFechaOrden(xFechaCorte);
                fachadaDctoOrdenBean.setIdLocal(xIdLocalUsuario);
                fachadaDctoOrdenBean.setIdTipoOrden(xIdTipoOrdenAjuste);
                fachadaDctoOrdenBean.setIdUsuario(xIdUsuario);
                fachadaDctoOrdenBean.setIdLog(xIdLogActual);

                //
                request.setAttribute("fachadaDctoOrdenBean",
                        fachadaDctoOrdenBean);

                //
                return "/jsp/vtaFrmConInventarioAjusteOT.jsp";

            }

            //
            if (accionContenedor.compareTo("Legalizar") == 0) {

                //
                String xIdTipoOrden = request.getParameter("xIdTipoOrden");
                String xIdBodegaOrigen =
                        request.getParameter("xIdBodegaOrigen");
                String xNumeroOrden =
                        request.getParameter("xNumeroOrdenOrigen");
                String xIdOperacion =
                        request.getParameter("xIdOperacion");

                //
                xIdTipoOrdenAjusteProceso = new Integer(xIdTipoOrden).intValue();

                //
                xIdTipoOrdenAjuste = new Integer(xIdTipoOrden).intValue()
                        - 50;

                //
                DctoOrdenBean dctoOrdenBean = new DctoOrdenBean();

                //
                dctoOrdenBean.setIdLog(xIdLogActual);
                dctoOrdenBean.setIdTipoOrden(xIdTipoOrdenAjusteProceso);

                // existePedido
                boolean existePedido = dctoOrdenBean.existePedido();

                //
                if (!existePedido) {

                    //
                    Validacion validacion = new Validacion();

                    //
                    validacion.reasignar("MOVIMIENTO", "");
                    validacion.setCodigoError("Error PEDIDO");
                    validacion.setDescripcionError("Pedido YA CONFIRMADO");
                    validacion.setSolucion("Iniciar NUEVO MOVIMIENTO");

                    // Aqui escribe el Bean de Validacion en el Request para manejar el error
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralError.jsp";

                }

                //--------------------------------------------------------------
                FachadaDctoBean fachadaDctoBean = new FachadaDctoBean();

                // confirmaPedido
                ProcesoIngresoAjuste proceso = new ProcesoIngresoAjuste();

                // confirma
                fachadaDctoBean =
                        proceso.ingresa(new Integer(xIdLocalUsuario).intValue(),
                        xIdTipoOrdenAjuste,
                        xIdLogActual,
                        xIdTipoOrdenAjusteProceso);

                //
                int xIdEstadoTxSinTx = 1;
                int estadoProgramado = 1;
                int tareaVisita = 6;   // Cotizacion

                //--------------------------------------------------------------
                FachadaDctoOrdenBean fachadaDctoOrdenBean = new FachadaDctoOrdenBean();

                //
                int xIdTipoOrdenPedido = 59;

                //
                dctoOrdenBean.setIdLocal(xIdLocalUsuario);
                dctoOrdenBean.setIdTipoOrden(xIdTipoOrdenPedido);
                dctoOrdenBean.setNumeroOrden(xNumeroOrden);

                //
                fachadaDctoOrdenBean = dctoOrdenBean.listaDctoNumeroOrden();

                //
                int xIdLocalOrigen = fachadaDctoOrdenBean.getIdLocal();
                int xIdOrdenOrigen = fachadaDctoOrdenBean.getIdOrden();


                //--------------------------------------------------------------
                int xIdControlTipo = 0;
                int xItemPadre = 1;

                // ajuste positivo --> entrada
                if (xIdTipoOrden.compareTo("66") == 0) {

                    //
                    xIdControlTipo = 1; // ajuste positivo --> entrada
                    
                }

                // ajustes negativo --> salida
                if (xIdTipoOrden.compareTo("65") == 0) {
                    
                    //
                    xIdControlTipo = 2; // ajustes negativo --> salida
                    
                }
                //---
                FachadaDctoOrdenProgreso fachadaDctoOrdenProgreso =
                        new FachadaDctoOrdenProgreso();

                //
                DctoOrdenProgresoBean dctoOrdenProgresoBean = new DctoOrdenProgresoBean();

                //---
                dctoOrdenProgresoBean.setIdLocal(xIdLocalOrigen);
                dctoOrdenProgresoBean.setIdTipoOrden(xIdTipoOrdenPedido);
                dctoOrdenProgresoBean.setIdOrden(xIdOrdenOrigen);
                dctoOrdenProgresoBean.setItemPadre(xItemPadre);
                dctoOrdenProgresoBean.setIdOperacion(xIdOperacion);
                dctoOrdenProgresoBean.setIdControlTipo(xIdControlTipo);

                //--
                fachadaDctoOrdenProgreso =
                        dctoOrdenProgresoBean.listaExternoFCH();

                //
                double xIdOperario = fachadaDctoOrdenProgreso.getIdOperario();

                //
                int xItemMaximo = dctoOrdenProgresoBean.maximoItem() + 1;

                //
                int xIdControlMaximo = dctoOrdenProgresoBean.maximoIdControExterno() + 1;

                //
                dctoOrdenProgresoBean.setIdLocal(fachadaDctoBean.getIdLocalStr());
                dctoOrdenProgresoBean.setIdTipoOrden(fachadaDctoBean.getIdTipoOrdenStr());
                dctoOrdenProgresoBean.setIdOrden(fachadaDctoBean.getIdOrden());

                //--
                dctoOrdenProgresoBean.ingresaAjuste(xIdLocalOrigen,
                        xIdTipoOrdenPedido,
                        xIdOrdenOrigen,
                        xItemMaximo,
                        xIdOperario,
                        xIdControlMaximo,
                        xIdControlTipo,
                        xIdUsuario);
                //--
                GeneraPDFMovimientoOT generaPDFMovimientoOT = new GeneraPDFMovimientoOT();

                //--
                generaPDFMovimientoOT.setIdLocal(fachadaDctoBean.getIdLocalStr());
                generaPDFMovimientoOT.setIdTipoOrden(
                        fachadaDctoBean.getIdTipoOrdenStr());
                generaPDFMovimientoOT.setIdOrden(fachadaDctoBean.getIdOrden());
                generaPDFMovimientoOT.setReporteName("RepEmpresaMovimientoOT");

                //
                generaPDFMovimientoOT.setTituloReporte("AJUSTES");

                //
                generaPDFMovimientoOT.generaPdf(request, response);

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

                //
                ProcesoIp procesoIp = new ProcesoIp();

                //
                String ipTx = procesoIp.getIpTx(request);

                agendaLogVisitaBean.setIpTx(ipTx);
                agendaLogVisitaBean.setFechaTx(fechaTxHM);

                //
                boolean okLog = agendaLogVisitaBean.finalizaVisita();


            }

            // retira
            if (accionContenedor.compareTo("retira") == 0) {

                //
                String xItem = request.getParameter("xItem");
                String xIdLog = request.getParameter("xIdLog");
                String xIdTercero = request.getParameter("xIdTercero");
                String xFechaCorte = request.getParameter("xFechaCorte");
                String xIdOperacion = request.getParameter("xIdOperacion");
                String xIdTipoOrden = request.getParameter("xIdTipoOrden");

                //
                xIdTipoOrdenAjusteProceso = new Integer(xIdTipoOrden).intValue();

                //
                DctoOrdenBean dctoOrdenBean = new DctoOrdenBean();

                //
                dctoOrdenBean.setIdLog(xIdLog);

                FachadaDctoOrdenBean fachadaDctoOrdenBean = new FachadaDctoOrdenBean();
                fachadaDctoOrdenBean =
                        dctoOrdenBean.listaDctoOrdenIdLog();

                //
                DctoOrdenDetalleBean dctoOrdenDetalleBean = new DctoOrdenDetalleBean();

                //
                dctoOrdenDetalleBean.setIdLog(xIdLog);
                dctoOrdenDetalleBean.setIdTipoOrden(xIdTipoOrdenAjusteProceso);
                dctoOrdenDetalleBean.setItem(xItem);


                // retiraItem
                boolean okRetiro = dctoOrdenDetalleBean.retiraItem();

                // Retira DctoOrden
                dctoOrdenBean.setIdLocal(xIdLocalUsuario);
                dctoOrdenBean.setIdTipoOrden(xIdTipoOrdenAjusteProceso);
                dctoOrdenBean.setIdOrden(xIdLog);

                // validaArticulosxOrden
                boolean okDetalle = dctoOrdenDetalleBean.validaOrden();

                if (!okDetalle) {

                    // Retira DctoOrden
                    dctoOrdenBean.setIdLocal(xIdLocalUsuario);
                    dctoOrdenBean.setIdTipoOrden(xIdTipoOrdenAjuste);
                    dctoOrdenBean.setIdOrden(xIdLog);

                    //
                    dctoOrdenBean.retiraOrden();

                }

                //
                dctoOrdenBean.setIdLog(xIdLogActual);
                dctoOrdenBean.setIdTipoOrden(xIdTipoOrdenAjusteProceso);

                //
                fachadaDctoOrdenBean =
                        dctoOrdenBean.listaDctoOrdenIdLogIdTipoOrden();

                //
                fachadaDctoOrdenBean.setIdListaPrecio(xIdListaPrecio);
                fachadaDctoOrdenBean.setIdCliente(xIdTercero);
                fachadaDctoOrdenBean.setFechaOrden(xFechaCorte);
                fachadaDctoOrdenBean.setIdLog(xIdLogActual);
                fachadaDctoOrdenBean.setIdOperacion(xIdOperacion);
                fachadaDctoOrdenBean.setIdTipoOrden(xIdTipoOrdenAjusteProceso);

                //
                request.setAttribute("fachadaDctoOrdenBean",
                        fachadaDctoOrdenBean);

                //
                return "/jsp/vtaFrmIngInventarioAjusteOT.jsp";

            }

            // Confirmar
            if (accionContenedor.compareTo("Confirmar") == 0) {

                //
                String xIdTercero = "1";
                String xFechaCorte = request.getParameter("xFechaCorte");
                String xIdOperacion =
                        request.getParameter("xIdOperacion");
                String xIdTipoOrden = request.getParameter("xIdTipoOrden");
                String xCantidadTerminada =
                        request.getParameter("xCantidadTerminada");
                String xPesoTerminado = request.getParameter("xPesoTerminado");
                String xNumeroOrden = request.getParameter("xNumeroOrden");

                //
                int xItem = 1;
                xIdTipoOrdenAjusteProceso = new Integer(xIdTipoOrden).intValue();

                //
                String xIdReferencia = "209";

                //---
                Validacion valida = new Validacion();

                //
                valida.reasignar("PESO (KG)", xPesoTerminado);

                //
                valida.validarCampoDoublePositivo();

                //isValido
                if (!valida.isValido()) {

                    //
                    request.setAttribute("validacion", valida);
                    return "/jsp/gralError.jsp";
                }


                //
                valida.reasignar("CANTIDAD", xCantidadTerminada);

                //
                valida.validarCampoDoublePositivo();

                //isValido
                if (!valida.isValido()) {

                    //
                    request.setAttribute("validacion", valida);
                    return "/jsp/gralError.jsp";
                }

                //
                ProcesoGuardaAjusteOT proceso = new ProcesoGuardaAjusteOT();

                //valida el idTercero sea el mismo para todos
                String strIdReferencia = xIdReferencia;
                int xItemPadre = 0;
                String xComentario = "ninguno";
                int xIdClasificacion = 0;

                //
                int maximoItem = proceso.guarda(xIdLogActual,
                        strIdReferencia,
                        new Double(xCantidadTerminada).doubleValue(),
                        new Double(xPesoTerminado).doubleValue(),
                        xItemPadre,
                        new Integer(xIdTipoOrdenAjusteProceso).intValue(),
                        xIdUsuario,
                        new Integer(xIdLocalUsuario).intValue(),
                        xIdTercero,
                        xComentario,
                        new Integer(xIdOperacion).intValue(),
                        xIdClasificacion);

                //--------------------------------------------------------------
                FachadaPluFicha fachadaPluFicha = new FachadaPluFicha();

                //
                ColaboraOrdenTrabajo colaboraOrdenTrabajo = new ColaboraOrdenTrabajo();

                //
                colaboraOrdenTrabajo.setNumeroOrden(xNumeroOrden);
                colaboraOrdenTrabajo.setItem(xItemPadre);

                //
                fachadaPluFicha = colaboraOrdenTrabajo.listaUnOTFCH(xItem);

                //---
                int xIdLocalOrigen = fachadaPluFicha.getIdLocal();
                int xIdTipoOrdenOrigen = fachadaPluFicha.getIdTipoOrden();
                int xIdOrdenOrigen = fachadaPluFicha.getIdOrden();

                //
                DctoOrdenDetalleBean dctoOrdenDetalleBean = new DctoOrdenDetalleBean();

                //
                dctoOrdenDetalleBean.setIdLocalOrigen(xIdLocalOrigen);
                dctoOrdenDetalleBean.setIdTipoOrdenOrigen(xIdTipoOrdenOrigen);
                dctoOrdenDetalleBean.setIdOrdenOrigen(xIdOrdenOrigen);
                dctoOrdenDetalleBean.setIdLocal(xIdLocalUsuario);
                dctoOrdenDetalleBean.setIdTipoOrden(xIdTipoOrdenAjusteProceso);
                dctoOrdenDetalleBean.setIdLog(xIdLogActual);
                dctoOrdenDetalleBean.setItem(maximoItem);

                //
                dctoOrdenDetalleBean.actualizaOrdenOrigenAjuste();


                //--------------------------------------------------------------
                DctoOrdenBean dctoOrdenBean = new DctoOrdenBean();

                //
                dctoOrdenBean.setIdLog(fachadaAgendaLogVisitaBean.getIdLog());
                dctoOrdenBean.setIdTipoOrden(xIdTipoOrdenAjusteProceso);

                //
                FachadaDctoOrdenBean fachadaDctoOrdenBean = new FachadaDctoOrdenBean();

                //
                fachadaDctoOrdenBean =
                        dctoOrdenBean.listaDctoOrdenIdLogIdTipoOrden();

                //
                fachadaDctoOrdenBean.setIdListaPrecio(xIdListaPrecio);
                fachadaDctoOrdenBean.setIdCliente(xIdTercero);
                fachadaDctoOrdenBean.setFechaOrden(xFechaCorte);
                fachadaDctoOrdenBean.setIdOperacion(xIdOperacion);
                fachadaDctoOrdenBean.setIdTipoOrden(xIdTipoOrdenAjusteProceso);
                fachadaDctoOrdenBean.setNumeroOrden(xNumeroOrden);

                //
                request.setAttribute("fachadaDctoOrdenBean",
                        fachadaDctoOrdenBean);

                //
                return "/jsp/vtaFrmIngInventarioAjusteOT.jsp";

            }

            // O.T.
            if (accionContenedor.compareTo("O.T.") == 0) {

                //
                String xIdLocal = request.getParameter("xIdLocal");
                String xIdTipoOrden = request.getParameter("xIdTipoOrden");
                String xNumeroOrden = request.getParameter("xNumeroOrden");
                String xIdOperacion = request.getParameter("xIdOperacion");
                String xItemPadre = "1";

                //
                Validacion validacion = new Validacion();

                //
                validacion.reasignar("OPERACION", xIdOperacion);

                //
                validacion.validarCampoEntero();

                //
                if (!validacion.isValido()) {

                    // Aqui escribe el Bean de Validacion en el Request para manejar el error
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralError.jsp";

                }

                //
                validacion.reasignar("#O.T.", xNumeroOrden);

                //
                validacion.validarCampoEntero();

                //
                if (!validacion.isValido()) {

                    // Aqui escribe el Bean de Validacion en el Request para manejar el error
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralError.jsp";

                }

                //--------------------------------------------------------------
                FachadaPluFicha fachadaPluFicha = new FachadaPluFicha();

                //
                ColaboraOrdenTrabajo colaboraOrdenTrabajo = new ColaboraOrdenTrabajo();

                //
                colaboraOrdenTrabajo.setNumeroOrden(xNumeroOrden);
                colaboraOrdenTrabajo.setIdOperacion(xIdOperacion);
                colaboraOrdenTrabajo.setItem(xItemPadre);

                //
                fachadaPluFicha = colaboraOrdenTrabajo.listaOrdenFCH();

                //
                int xIdLog = fachadaPluFicha.getIdLog();

                //
                if (xIdLog == 0) {

                    //
                    validacion.reasignar("#O.T.", xNumeroOrden);

                    //
                    validacion.setDescripcionError("NO EXISTE #O.T. " + xNumeroOrden);

                    //
                    validacion.setSolucion("INGRESE OTRA #O.T. ");

                    // Aqui escribe el Bean de Validacion en el Request para manejar el error
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralError.jsp";

                }

                //--------------------------------------------------------------
                agendaLogVisitaBean.setIdLog(xIdLog);

                //
                fachadaAgendaLogVisitaBean =
                        agendaLogVisitaBean.listaLogFachada();

                //
                request.setAttribute("fachadaAgendaLogVisitaBean",
                        fachadaAgendaLogVisitaBean);

                //
                FachadaDctoOrdenBean fachadaDctoOrdenBean = new FachadaDctoOrdenBean();

                //
                DctoOrdenBean dctoOrdenBean = new DctoOrdenBean();

                //
                dctoOrdenBean.setIdLog(xIdLog);

                //
                fachadaDctoOrdenBean =
                        dctoOrdenBean.listaDctoOrdenIdLog();

                //
                int xIdFicha = fachadaDctoOrdenBean.getIdFicha();
                String xIdCliente = fachadaDctoOrdenBean.getIdCliente();
                String xIdTipoTerceroProveedor = "2";

                //
                fachadaDctoOrdenBean.setIdTipoTercero(xIdTipoTerceroProveedor);
                fachadaDctoOrdenBean.setIdOperacion(xIdOperacion);
                fachadaDctoOrdenBean.setIdBodegaOrigen(xIdOperacion);
                fachadaDctoOrdenBean.setItemPadre(xItemPadre);
                fachadaDctoOrdenBean.setIdTipoOrden(xIdTipoOrden);

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaDctoOrdenBean", fachadaDctoOrdenBean);

                //
                FachadaTerceroBean fachadaTerceroBean = new FachadaTerceroBean();

                //
                ColaboraTercero colaboraTercero = new ColaboraTercero();

                //
                String xIdTipoOrdenCotizacion = "59";
                String xIdTipoTerceroCliente = "1";

                //
                fachadaTerceroBean.setIdTipoOrden(xIdTipoOrdenCotizacion);
                colaboraTercero.setIdTipoTercero(xIdTipoTerceroCliente);

                //
                colaboraTercero.setIdCliente(
                        fachadaAgendaLogVisitaBean.getIdCliente());

                //
                fachadaTerceroBean = colaboraTercero.listaTerceroFCH();

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaTerceroBean", fachadaTerceroBean);

                //
                ColaboraDctoOrdenDetalleBean colaboraDctoOrdenDetalleBean =
                        new ColaboraDctoOrdenDetalleBean();

                //
                colaboraDctoOrdenDetalleBean.setItem(xItemPadre);
                colaboraDctoOrdenDetalleBean.setIdTipoOrden(
                        xIdTipoOrdenCotizacion);


                //
                FachadaDctoOrdenDetalleBean fachadaDctoOrdenDetalleBean =
                        new FachadaDctoOrdenDetalleBean();

                //
                fachadaDctoOrdenDetalleBean =
                        colaboraDctoOrdenDetalleBean.itemLogFachada(
                        new Integer(xIdLog).intValue());

                //
                Double xCantidad = fachadaDctoOrdenDetalleBean.getCantidad();

                //
                fachadaDctoOrdenDetalleBean.setItem(xItemPadre);

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaDctoOrdenDetalleBean",
                        fachadaDctoOrdenDetalleBean);

                //
                FichaTecnica fichaTecnica = new FichaTecnica();


                //
                double xPesoPedido = fichaTecnica.pesoPedido(xIdCliente,
                        new Integer(xIdFicha).intValue(),
                        new Double(xCantidad).doubleValue());

                //---
                colaboraOrdenTrabajo.setIdFicha(xIdFicha);

                //
                fachadaPluFicha = colaboraOrdenTrabajo.listaIdFichaFCH();

                //
                fachadaPluFicha.setIdOperacion(xIdOperacion);
                fachadaPluFicha.setIdFicha(xIdFicha);
                fachadaPluFicha.setPesoPedido(xPesoPedido);
                fachadaPluFicha.setCantidad(xCantidad);
                fachadaPluFicha.setNumeroOrden(xNumeroOrden);

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaPluFicha", fachadaPluFicha);

                //
                DctoOrdenProgresoBean dctoOrdenProgresoBean =
                        new DctoOrdenProgresoBean();

                //
                dctoOrdenProgresoBean.setIdLocal(xIdLocalUsuario);
                dctoOrdenProgresoBean.setIdTipoOrden(xIdTipoOrden);
                dctoOrdenProgresoBean.setIdOrden(
                        fachadaDctoOrdenBean.getIdOrden());
                dctoOrdenProgresoBean.setIdOperacion(xIdOperacion);
                dctoOrdenProgresoBean.setItemPadre(xItemPadre);

                //
                FachadaDctoOrdenProgreso fachadaDctoOrdenConcluida = new FachadaDctoOrdenProgreso();

                //
                fachadaDctoOrdenConcluida =
                        dctoOrdenProgresoBean.listaTotalFCH();

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaDctoOrdenConcluida",
                        fachadaDctoOrdenConcluida);
                //
                return "/jsp/vtaFrmLstInventarioAjusteOT.jsp";

            }

            // Elaborar
            if (accionContenedor.compareTo("Elaborar") == 0) {

                //
                String xIdTercero = request.getParameter("xIdLocal");
                String xFechaCorte = request.getParameter("xFechaCorte");
                String xIdBodegaOrigen =
                        request.getParameter("xIdBodegaOrigen");
                String xIdTipoOrden =
                        request.getParameter("xIdTipoOrden");

                // validacion
                Validacion validacion = new Validacion();

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
                validacion.reasignar("BODEGA ORIGEN", xIdBodegaOrigen);

                //
                validacion.validarCampoDouble();

                // isValido
                if (!validacion.isValido()) {

                    //
                    validacion.setDescripcionError(
                            "ERROR, FALTA SELECCIONAR BODEGA ORIGEN");

                    //
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralError.jsp";

                }

                //
                validacion.reasignar("TIPO MOVIMIENTO", xIdTipoOrden);

                validacion.validarCampoEntero();

                // isValido
                if (!validacion.isValido()) {

                    //
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralError.jsp";

                }

                //
                xIdTipoOrdenAjusteProceso =
                        new Integer(xIdTipoOrden).intValue() + 50;

                //
                FachadaDctoOrdenBean fachadaDctoOrdenBean = new FachadaDctoOrdenBean();

                //
                fachadaDctoOrdenBean.setIdLocal(xIdLocalUsuario);
                fachadaDctoOrdenBean.setIdUsuario(xIdUsuario);
                fachadaDctoOrdenBean.setIdCliente(xIdLocalUsuario);
                fachadaDctoOrdenBean.setFechaOrden(strFechaVisita);
                fachadaDctoOrdenBean.setIdLog(xIdLogActual);
                fachadaDctoOrdenBean.setIdOperacion(xIdBodegaOrigen);
                fachadaDctoOrdenBean.setIdBodegaOrigen(xIdBodegaOrigen);
                fachadaDctoOrdenBean.setIdTipoOrden(xIdTipoOrdenAjusteProceso);

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaDctoOrdenBean", fachadaDctoOrdenBean);

                //
                return "/jsp/vtaFrmIngInventarioAjusteOT.jsp";

            }
        }


        return "/jsp/empty.htm";

    }
}