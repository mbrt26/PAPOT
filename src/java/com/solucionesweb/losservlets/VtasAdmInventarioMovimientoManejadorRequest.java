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

import com.solucionesweb.losbeans.fachada.FachadaColaboraHistoriaBean;

import com.solucionesweb.losbeans.fachada.FachadaDctoBean;

// Importa la clase ProcesoInternoInventario
import com.solucionesweb.lasayudas.ProcesoInternoInventario;

// Importa la clase que contiene el Bean de Validacion
import com.solucionesweb.losbeans.utilidades.Validacion;

// Importa la clase que contiene el Day
import com.solucionesweb.losbeans.utilidades.Day;

// Importa la clase que contiene ProcesoGuardaInventario
import com.solucionesweb.lasayudas.ProcesoGuardaInventario;
import com.solucionesweb.lasayudas.ProcesoIngresoProduccion;
import com.solucionesweb.lasayudas.ProcesoOTTraslado;
import com.solucionesweb.losbeans.colaboraciones.ColaboraDctoOrdenDetalleBean;
import com.solucionesweb.losbeans.colaboraciones.ColaboraOperacionPlu;
import com.solucionesweb.losbeans.colaboraciones.ColaboraOrdenTrabajo;
import com.solucionesweb.losbeans.fachada.FachadaDctoOrdenDetalleBean;
import com.solucionesweb.losbeans.fachada.FachadaJobOperacionCosto;
import com.solucionesweb.losbeans.fachada.FachadaPluFicha;
import com.solucionesweb.losbeans.negocio.DctoOrdenProgresoBean;
import com.solucionesweb.losbeans.negocio.JobOperacionCostoBean;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * 
 * Esta ventana permite elaborar traslado de inventario a otras bodegas internas o externas/
 * vtaContenedorInventarioMovimiento.jsp /
 *
 *  Este servlet implementa la interface GralManejadorRequest /
 */
//
public class VtasAdmInventarioMovimientoManejadorRequest
        implements GralManejadorRequest {
    /**
     * BUTTON--
     * ("Regresar")-("Salir")- Permite retornar al menu principal
     * ("Elaborar")-Permite iniciar movimiento de mercancias entre diferentes bodegas/vtaContenedorInventarioMovimiento.jsp/
     * ("+Productos")- Donde el usuario ingresa manualmente los productos que quiere mover/vtaFrmIngInventarioMovimiento.jsp/
     * ("Confirmar")- Donde el usuario confirma las cantidades de los articulos ingresados previamente/
     * ("Legalizar")-Finaliza el proceso y genera pdf del movimiento/vtaFrmIngInventarioMovimiento.jsp /
     * 
     * Metodo contructor por defecto, es decir, sin parametros /
     */

    public VtasAdmInventarioMovimientoManejadorRequest() { 
    }
     /**
     * BUTTON PARAMETER--
     * "#O.T"- ingrese oreden de trabajo/
     * "Bodega Origen"- Bodega de origen de movimiento/
     * "Bodega Destino"-Bodega de destino de movimiento/
     * "Movimiento"- indicativo de movimiento de inventario interno/
     * "Cantidad"- cantidad de articulos a mover /
     * "Tercero"-selecciona tercero /
     */

    public String generaPdf(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String accionContenedor = request.getParameter("accionContenedor");
        System.out.println("accionContenedor :" + accionContenedor);

        //
        int xIdTipoOrdenInterno = 4;
        int xIdTipoOrdenPedidoProceso = 59;
        int xEstadoActivo = 9;
        int xIdListaPrecio = 1;
        int xIdTipoTerceroProveedor = 2;

        //
        String xIdTipoOrden = request.getParameter("xIdTipoOrden");

        //
        int xIdTipoOrdenProceso = new Integer(xIdTipoOrden).intValue() + 50 ;

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
        Day day = new Day();
        String strFechaVisita = day.getFechaFormateada();

        //
        HttpSession sesion = request.getSession();
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

        // Validacion de accion relacionada con el formulario requerido
        if (accionContenedor != null) {

            if (accionContenedor.compareTo("Salir") == 0) {
                return "/jsp/empty.htm";

            }

            // tomar
            if (accionContenedor.compareTo("tomar") == 0) {

                //
                String xIdLocal = request.getParameter("xIdLocal");
                String xIdOrden = request.getParameter("xIdOrden");

                //
                GeneraPDFResurtido generaPDFResurtido = new GeneraPDFResurtido();

                //
                generaPDFResurtido.setIdLocal(xIdLocal);
                generaPDFResurtido.setIdOrden(xIdOrden);
                generaPDFResurtido.setIdTipoOrden(xIdTipoOrden);
                generaPDFResurtido.setReporteName("RepEmpresaResurtido");

                //
                generaPDFResurtido.setTituloReporte("MOVIMIENTO INTERNO");

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
                fachadaDctoOrdenBean.setIdTipoOrden(xIdTipoOrdenInterno);
                fachadaDctoOrdenBean.setIdUsuario(xIdUsuario);
                fachadaDctoOrdenBean.setIdLog(xIdLogActual);

                //
                request.setAttribute("fachadaDctoOrdenBean",
                        fachadaDctoOrdenBean);

                //
                return "/jsp/vtaFrmConInventarioMovimiento.jsp";

            }

            //
            if (accionContenedor.compareTo("Legalizar") == 0) {

                //
                String xIdBodegaOrigen =
                        request.getParameter("xIdBodegaOrigen");
                String xIdBodegaDestino =
                        request.getParameter("xIdBodegaDestino");
                String xNumeroOrden =
                        request.getParameter("xNumeroOrden");
                String xIdTercero =
                        request.getParameter("xIdTercero");
                String xIdOperario =
                        request.getParameter("xIdOperario");                

                //
                DctoOrdenBean dctoOrdenBean = new DctoOrdenBean();

                //
                dctoOrdenBean.setIdLog(xIdLogActual);
                dctoOrdenBean.setIdTipoOrden(xIdTipoOrdenProceso-50);

                // existePedido
                boolean existePedido = dctoOrdenBean.existePedido();

                //
                if (!existePedido) {

                    //
                    Validacion validacion = new Validacion();

                    //
                    validacion.reasignar("PEDIDO", "");
                    validacion.setDescripcionError("PEDIDO YA CONFIRMADO");
                    validacion.setSolucion("INICIAR NUEVO MOVIMIENTO");

                    // Aqui escribe el Bean de Validacion en el Request para manejar el error
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralError.jsp";

                }

                //--------------------------------------------------------------
                FachadaDctoBean fachadaDctoBean = new FachadaDctoBean();


                // confirmaPedido
                ProcesoInternoInventario proceso =
                        new ProcesoInternoInventario();

                // confirma
                fachadaDctoBean =
                        proceso.ingresa(new Integer(xIdLocalUsuario).intValue(),
                        new Integer(xIdTipoOrdenProceso).intValue() - 100,
                        xIdLogActual,
                        ( xIdTipoOrdenProceso - 50 ),
                        new Integer(xNumeroOrden).intValue(),
                        new Integer(xIdBodegaDestino).intValue());

                //
                int xIdEstadoTxSinTx = 1;
                int estadoProgramado = 1;
                int tareaVisita = 6;   // Cotizacion
                
                //
                int xIdLocal = fachadaDctoBean.getIdLocal();
                int xIdTipoOrdenTraslado = fachadaDctoBean.getIdTipoOrden();
                int xIdOrden = fachadaDctoBean.getIdOrden();                
                
                //--------------------------------------------------------------
                FachadaDctoOrdenDetalleBean fachadaDctoOrdenDetalleBean 
                                            = new FachadaDctoOrdenDetalleBean();
                
                ColaboraDctoOrdenDetalleBean colaboraDctoOrdenDetalleBean 
                                           = new ColaboraDctoOrdenDetalleBean();
                
                //
                colaboraDctoOrdenDetalleBean.setIdLocal(xIdLocal);
                colaboraDctoOrdenDetalleBean.setIdTipoOrden(
                                                          xIdTipoOrdenTraslado);
                colaboraDctoOrdenDetalleBean.setIdOrden(xIdOrden);
                colaboraDctoOrdenDetalleBean.setIdBodega(xIdBodegaOrigen);                                
                
                //
                fachadaDctoOrdenDetalleBean = 
                                  colaboraDctoOrdenDetalleBean.calculaPesoFCH();
                
                //
                double xPedoTerminado = fachadaDctoOrdenDetalleBean.getCantidad();
                
                //--------------------------------------------------------------
                int xItemPadre = 1 ;
                
                //
                FachadaPluFicha fachadaPluFicha = new FachadaPluFicha();
                
                //
                ColaboraOrdenTrabajo colaboraOrdenTrabajo = 
                                                     new ColaboraOrdenTrabajo(); 
                
                //
                colaboraOrdenTrabajo.setNumeroOrden(xNumeroOrden);
                
                //
                fachadaPluFicha =  colaboraOrdenTrabajo.listaUnOTFCH(xItemPadre);
                
                //
                int xIdFicha = fachadaPluFicha.getIdFicha();
                String xIdCliente = fachadaPluFicha.getIdCliente();
                int xIdLocalOrigen     = fachadaPluFicha.getIdLocal();
                int xIdTipoOrdenOrigen = fachadaPluFicha.getIdTipoOrden();
                int xIdOrdenOrigen     = fachadaPluFicha.getIdOrden();                
                
                //----------------------- ingresar  documento ------------------
                ColaboraOperacionPlu colaboraOperacionPlu =
                        new ColaboraOperacionPlu();

                //
                colaboraOperacionPlu.setIdFicha(xIdFicha);
                colaboraOperacionPlu.setIdOperacion(xIdBodegaDestino);

                //
                double xTotalMP = colaboraOperacionPlu.totalMP();
                String xObservacion = "";         
                int xIdTipoOrdenTransformacion = 11;
                
                
                //-------------------------- detalle ---------------------------
                ProcesoOTTraslado procesoOT = new ProcesoOTTraslado();

                //
                int xIdOrdenCruce =
                        procesoOT.ingresa(new Integer(xIdLocal).intValue(),
                        xIdTipoOrdenTransformacion,
                        xIdUsuario,
                        xIdFicha,
                        new Integer(xIdBodegaDestino).intValue(),
                        new Double(xPedoTerminado).doubleValue(),
                        new Double(xPedoTerminado).doubleValue(),
                        xTotalMP,
                        xIdCliente,
                        xIdOrdenOrigen,
                        xIdLocalOrigen,
                        xIdTipoOrdenOrigen,
                        new Integer(xNumeroOrden).intValue() ,
                        new Integer(xItemPadre).intValue(),
                        xObservacion);                
                
                //--------------------------------------------------------------
                FachadaJobOperacionCosto fachadaJobOperacionCosto 
                                               = new FachadaJobOperacionCosto();

                //
                JobOperacionCostoBean jobOperacionCostoBean =
                        new JobOperacionCostoBean();

                //---
                jobOperacionCostoBean.setIdLocal(xIdLocal);
                jobOperacionCostoBean.setIdOperacion(xIdBodegaDestino);

                //---
                fachadaJobOperacionCosto =
                        jobOperacionCostoBean.listaCostoFCH();

                //---
                double xVrCostoBaseMAT =
                        fachadaJobOperacionCosto.getVrCostoBaseMAT();
                double xVrCostoBaseMOD =
                        fachadaJobOperacionCosto.getVrCostoBaseMOD();
                double xVrCostoBaseCIF =
                        fachadaJobOperacionCosto.getVrCostoBaseCIF();

                //---
                int xIdTipoCausaProduccion = 0;
                int xIdControlTipoOTExterna = 2;   
                
                //--------------------------------------------------------------
                double xCantidadCero = 0.0;
                double xPesoTara = 0.0;
                int xIdCausaCero = 0;
                int xEstado = 1;
                String xObservacionVacia = "";
                String xIdDctoNitCC = "";
                
                //--------------------------------------------------------------
                DctoOrdenProgresoBean dctoOrdenProgresoBean = new DctoOrdenProgresoBean();

                //
                dctoOrdenProgresoBean.setIdLocal(xIdLocalOrigen);//
                dctoOrdenProgresoBean.setIdTipoOrden(xIdTipoOrdenOrigen);
                dctoOrdenProgresoBean.setIdOrden(xIdOrdenOrigen);
                dctoOrdenProgresoBean.setIdOperacion(xIdBodegaDestino);
                dctoOrdenProgresoBean.setItemPadre(xItemPadre);                
                
                //
                int xMaximoItem = dctoOrdenProgresoBean.maximoItem() + 1;
                
                //---
                dctoOrdenProgresoBean.setItem(xMaximoItem);
                dctoOrdenProgresoBean.setIdOperario(xIdOperario);
                dctoOrdenProgresoBean.setCantidadPerdida(xCantidadCero);
                dctoOrdenProgresoBean.setCantidadTerminada(xPedoTerminado);
                dctoOrdenProgresoBean.setPesoTerminado(xPedoTerminado);
                dctoOrdenProgresoBean.setPesoPerdido(xCantidadCero);
                dctoOrdenProgresoBean.setIdCausa(xIdCausaCero);
                dctoOrdenProgresoBean.setIdTipoCausa(xIdTipoCausaProduccion);
                dctoOrdenProgresoBean.setEstado(xEstado);
                dctoOrdenProgresoBean.setItemPadre(xItemPadre);
                dctoOrdenProgresoBean.setVrCostoBaseMAT(xVrCostoBaseMAT);
                dctoOrdenProgresoBean.setVrCostoBaseCIF(xVrCostoBaseCIF);
                dctoOrdenProgresoBean.setVrCostoBaseMOD(xVrCostoBaseMOD);
                dctoOrdenProgresoBean.setPesoTara(xPesoTara);
                dctoOrdenProgresoBean.setIdUsuario(xIdUsuario);
                dctoOrdenProgresoBean.setCantidadPendiente(xCantidadCero);
                dctoOrdenProgresoBean.setObservacion(xObservacionVacia);
                dctoOrdenProgresoBean.setIdOrdenCruce(xIdOrdenCruce);    
                dctoOrdenProgresoBean.setIdDctoNitCC(xIdDctoNitCC);   
                dctoOrdenProgresoBean.setIdControlTipo(xIdControlTipoOTExterna);                   
        
                //---
                dctoOrdenProgresoBean.ingresa();  
                


                //
                dctoOrdenProgresoBean.setIdControlTipo(xIdControlTipoOTExterna);

                //
                int xMaximoidControlExterno =
                        dctoOrdenProgresoBean.maximoIdControExterno() + 1;

                //
                dctoOrdenProgresoBean.setIdControl(xMaximoidControlExterno);
                dctoOrdenProgresoBean.setIdOrden(xIdOrdenOrigen);
                dctoOrdenProgresoBean.setItemPadre(xItemPadre);
                dctoOrdenProgresoBean.setIdOperacion(xIdBodegaDestino);
                dctoOrdenProgresoBean.setObservacion(xObservacion);
                dctoOrdenProgresoBean.setIdUsuario(xIdUsuario);

                //
                dctoOrdenProgresoBean.actualizaOrdenExterna();

                //--------------------------------------------------------------
                GeneraPDFResurtido generaPDFResurtido = new GeneraPDFResurtido();

                //
                generaPDFResurtido.setIdLocal(fachadaDctoBean.getIdLocalStr());
                generaPDFResurtido.setIdOrden(fachadaDctoBean.getIdOrden());
                generaPDFResurtido.setIdTipoOrden(
                        fachadaDctoBean.getIdTipoOrdenStr());
                generaPDFResurtido.setReporteName("RepEmpresaResurtido");

                //
                generaPDFResurtido.generaPdf(request, response);

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
                //
                String xIdTercero = request.getParameter("xIdTercero");
                String xFechaCorte = request.getParameter("xFechaCorte");
                String xIdBodegaOrigen =
                        request.getParameter("xIdBodegaOrigen");
                String xIdBodegaDestino =
                        request.getParameter("xIdBodegaDestino");
                String xNumeroOrden =
                        request.getParameter("xNumeroOrden");
                String xIdOperario =
                        request.getParameter("xIdOperario");
                
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
                dctoOrdenDetalleBean.setIdTipoOrden(xIdTipoOrdenProceso - 50);
                dctoOrdenDetalleBean.setItem(xItem);


                // retiraItem
                boolean okRetiro = dctoOrdenDetalleBean.retiraItem();

                // Retira DctoOrden
                dctoOrdenBean.setIdLocal(xIdLocalUsuario);
                dctoOrdenBean.setIdTipoOrden(xIdTipoOrdenProceso - 50);
                dctoOrdenBean.setIdOrden(xIdLog);

                // validaArticulosxOrden
                boolean okDetalle = dctoOrdenDetalleBean.validaOrden();

                if (!okDetalle) {

                    // Retira DctoOrden
                    dctoOrdenBean.setIdLocal(xIdLocalUsuario);
                    dctoOrdenBean.setIdTipoOrden(xIdTipoOrdenProceso - 50);
                    dctoOrdenBean.setIdOrden(xIdLog);

                    //
                    dctoOrdenBean.retiraOrden();

                }

                //
                dctoOrdenBean.setIdLog(xIdLogActual);
                dctoOrdenBean.setIdTipoOrden(xIdTipoOrdenProceso - 50);

                //
                fachadaDctoOrdenBean =
                        dctoOrdenBean.listaDctoOrdenIdLogIdTipoOrden();

                //
                fachadaDctoOrdenBean.setIdListaPrecio(xIdListaPrecio);
                fachadaDctoOrdenBean.setIdCliente(xIdTercero);
                fachadaDctoOrdenBean.setFechaOrden(xFechaCorte);
                fachadaDctoOrdenBean.setIdLog(xIdLogActual);
                fachadaDctoOrdenBean.setIdBodegaOrigen(xIdBodegaOrigen);
                fachadaDctoOrdenBean.setIdBodegaDestino(xIdBodegaDestino);
                fachadaDctoOrdenBean.setNumeroOrden(xNumeroOrden);
                fachadaDctoOrdenBean.setIdTipoOrden(xIdTipoOrdenProceso - 50);
                fachadaDctoOrdenBean.setIdOperario(xIdOperario);                

                //
                request.setAttribute("fachadaDctoOrdenBean", fachadaDctoOrdenBean);

                //
                return "/jsp/vtaFrmIngInventarioMovimiento.jsp";

            }

            // Confirmar
            if (accionContenedor.compareTo("Confirmar") == 0) {

                //
                String xIdTercero = request.getParameter("xIdTercero");
                String xFechaCorte = request.getParameter("xFechaCorte");
                String xIdBodegaOrigen =
                        request.getParameter("xIdBodegaOrigen");
                String xIdBodegaDestino =
                        request.getParameter("xIdBodegaDestino");
                String xNumeroOrden =
                        request.getParameter("xNumeroOrden");
                String xIdOperario = request.getParameter("xIdOperario");                

                //---
                String arrIdReferencia[] =
                        request.getParameterValues("chkIdReferencia");
                //
                String arrcantidad[] =
                        request.getParameterValues("chkCantidad");

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


                    //valida el idTercero sea el mismo para todos
                    String strIdReferencia = arrIdReferencia[indice];
                    int xItemPadre = 0;
                    String xComentario = "ninguno";
                    String xIdResponsable = "0";
                    int xIdClasificacion = 0;


                    //
                    ProcesoGuardaInventario proceso = new ProcesoGuardaInventario();

                    //
                    int maximoItem = proceso.guarda(xIdLogActual,
                            strIdReferencia,
                            xCantidad,
                            xVrVentaUnitario,
                            xItemPadre,
                            (xIdTipoOrdenProceso - 50) ,
                            xIdUsuario,
                            new Integer(xIdLocalUsuario).intValue(),
                            xIdTercero,
                            new Integer(xIdBodegaOrigen).intValue());

                }

                //
                DctoOrdenBean dctoOrdenBean = new DctoOrdenBean();

                //
                dctoOrdenBean.setIdLog(fachadaAgendaLogVisitaBean.getIdLog());
                dctoOrdenBean.setIdTipoOrden(xIdTipoOrdenProceso - 50);

                //
                FachadaDctoOrdenBean fachadaDctoOrdenBean = new FachadaDctoOrdenBean();

                //
                fachadaDctoOrdenBean =
                        dctoOrdenBean.listaDctoOrdenIdLogIdTipoOrden();

                //
                fachadaDctoOrdenBean.setIdListaPrecio(xIdListaPrecio);
                fachadaDctoOrdenBean.setIdCliente(xIdTercero);
                fachadaDctoOrdenBean.setFechaOrden(xFechaCorte);
                fachadaDctoOrdenBean.setIdBodegaOrigen(xIdBodegaOrigen);
                fachadaDctoOrdenBean.setIdBodegaDestino(xIdBodegaDestino);
                fachadaDctoOrdenBean.setNumeroOrden(xNumeroOrden);
                fachadaDctoOrdenBean.setIdTipoOrden(xIdTipoOrdenProceso - 50);
                fachadaDctoOrdenBean.setIdOperario(xIdOperario);                

                //
                request.setAttribute("fachadaDctoOrdenBean",
                        fachadaDctoOrdenBean);

                //
                return "/jsp/vtaFrmIngInventarioMovimiento.jsp";

            }

            // Productos
            if (accionContenedor.compareTo("+Productos") == 0) {

                //
                String idLinea = request.getParameter("idLinea");
                String xIdTercero = request.getParameter("xIdTercero");
                String xFechaCorte = request.getParameter("xFechaCorte");
                String xIdBodegaOrigen =
                        request.getParameter("xIdBodegaOrigen");
                String xIdBodegaDestino =
                        request.getParameter("xIdBodegaDestino");
                String xNumeroOrden =
                        request.getParameter("xNumeroOrden");
                String xIdOperario =
                        request.getParameter("xIdOperario");
                
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
                dctoOrdenBean.setIdTipoOrden(xIdTipoOrdenProceso);

                //
                FachadaDctoOrdenBean fachadaDctoOrdenBean 
                                                   = new FachadaDctoOrdenBean();
                
                //
                fachadaDctoOrdenBean =
                        dctoOrdenBean.listaDctoOrdenIdLogIdTipoOrden();

                //
                fachadaDctoOrdenBean.setIdListaPrecio(xIdListaPrecio);
                fachadaDctoOrdenBean.setIdCliente(xIdTercero);
                fachadaDctoOrdenBean.setFechaOrden(xFechaCorte);
                fachadaDctoOrdenBean.setIdBodegaOrigen(xIdBodegaOrigen);
                fachadaDctoOrdenBean.setIdBodegaDestino(xIdBodegaDestino);
                fachadaDctoOrdenBean.setIdLocal(xIdLocalUsuario);
                fachadaDctoOrdenBean.setNumeroOrden(xNumeroOrden);
                fachadaDctoOrdenBean.setIdTipoOrden(xIdTipoOrden);
                fachadaDctoOrdenBean.setIdOperario(xIdOperario);                

                //
                request.setAttribute("fachadaDctoOrdenBean",
                        fachadaDctoOrdenBean);

                //
                return "/jsp/vtaFrmLstInventarioMovimiento.jsp";

            }

            // Elaborar
            if (accionContenedor.compareTo("Elaborar") == 0) {

                //
                String xIdOperario = request.getParameter("xIdTercero");
                String xNumeroOrden = request.getParameter("xNumeroOrden");
                String xIdBodegaOrigen =
                        request.getParameter("xIdBodegaOrigen");
                String xIdBodegaDestino =
                        request.getParameter("xIdBodegaDestino");

                //
                String xIdTipoOrdenExterna = "18";
                String xNumeroOrdenStr = "0";

                // validacion
                Validacion validacion = new Validacion();

                //
                validacion.reasignar("#O.T.", xNumeroOrden);

                //
                validacion.validarCampoEnteroPositivo();

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
                            "FALTA SELECCIONAR BODEGA ORIGEN");

                    //
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralError.jsp";

                }

                //
                validacion.reasignar("BODEGA DESTINO", xIdBodegaDestino);

                //
                validacion.validarCampoDouble();

                // isValido
                if (!validacion.isValido()) {

                    //
                    validacion.setDescripcionError(
                            "FALTA SELECCIONAR BODEGA DESTINO");

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

                // isValido
                if (xIdBodegaOrigen.compareTo(xIdBodegaDestino) == 0) {

                    //
                    validacion.reasignar("ERROR, IGUAL BODEGA ORIGEN Y DESTINO", "");

                    //
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralError.jsp";

                }

                //--------------------------------------------------------------
                xNumeroOrdenStr = xNumeroOrden;

                DctoOrdenBean dctoOrdenBean = new DctoOrdenBean();

                //
                FachadaDctoOrdenBean fachadaDctoOrdenBean 
                                                   = new FachadaDctoOrdenBean();

                //
                dctoOrdenBean.setIdLocal(xIdLocalUsuario);
                dctoOrdenBean.setIdTipoOrden(xIdTipoOrdenPedidoProceso);
                dctoOrdenBean.setNumeroOrden(xNumeroOrdenStr);

                //
                fachadaDctoOrdenBean = dctoOrdenBean.listaDctoNumeroOrden();
                
                //                
                double xIdOperarioNN = 0.0;
                fachadaDctoOrdenBean.setIdOperario(xIdOperarioNN);

                //---
                if (xIdTipoOrden.compareTo(xIdTipoOrdenExterna) == 0) {

                    //
                    validacion.reasignar("#O.T.", xNumeroOrden);

                    //
                    validacion.validarCampoEntero();

                    // isValido
                    if (!validacion.isValido()) {

                        //
                        request.setAttribute("validacion", validacion);
                        return "/jsp/gralError.jsp";

                    }

                    //
                    if (fachadaDctoOrdenBean.getIdOrden() == 0) {

                        //
                        validacion.reasignar("NO EXISTE LA O.T. EXTERNA #" + xNumeroOrden, "");

                        //
                        request.setAttribute("validacion", validacion);
                        return "/jsp/gralError.jsp";

                    }
                    
                    //
                    validacion.reasignar("TERCERO", xIdOperario);

                    //
                    validacion.validarCampoDouble();

                    // isValido
                    if (!validacion.isValido()) {

                        //
                        request.setAttribute("validacion", validacion);
                        return "/jsp/gralError.jsp";

                    }                    
                    
                }

                //---
                fachadaDctoOrdenBean.setIdLocal(xIdLocalUsuario);
                fachadaDctoOrdenBean.setIdTipoOrden(xIdTipoOrdenProceso);
                fachadaDctoOrdenBean.setIdUsuario(xIdUsuario);
                fachadaDctoOrdenBean.setIdCliente(xIdLocalUsuario);
                fachadaDctoOrdenBean.setFechaOrden(strFechaVisita);
                fachadaDctoOrdenBean.setIdLog(xIdLogActual);
                fachadaDctoOrdenBean.setIdBodegaOrigen(xIdBodegaOrigen);
                fachadaDctoOrdenBean.setIdBodegaDestino(xIdBodegaDestino);
                fachadaDctoOrdenBean.setNumeroOrden(xNumeroOrdenStr);
                fachadaDctoOrdenBean.setIdOperario(xIdOperario);    
                fachadaDctoOrdenBean.setIdTipoTercero(xIdTipoTerceroProveedor);                    
                
                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaDctoOrdenBean", fachadaDctoOrdenBean);

                //
                return "/jsp/vtaFrmIngInventarioMovimiento.jsp";

            }
        }

        return "/jsp/empty.htm";

    }
}
