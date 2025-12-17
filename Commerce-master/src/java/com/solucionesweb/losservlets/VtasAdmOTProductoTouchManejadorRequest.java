package com.solucionesweb.losservlets;

// Importa los paquetes del lenguaje especificamente io y los
import com.solucionesweb.lasayudas.ProcesoIngresoProduccion;
import com.solucionesweb.lasayudas.ProcesoIngresoRetal;
import com.solucionesweb.lasayudas.ProcesoIngresoSuminisitro;
import com.solucionesweb.losbeans.colaboraciones.ColaboraDctoOrdenBean;
import com.solucionesweb.losbeans.colaboraciones.ColaboraDctoOrdenDetalleBean;
import com.solucionesweb.losbeans.colaboraciones.ColaboraOperacionPlu;
import com.solucionesweb.losbeans.colaboraciones.ColaboraOrdenTrabajo;
import com.solucionesweb.losbeans.colaboraciones.ColaboraTercero;
import com.solucionesweb.losbeans.fachada.FachadaAgendaLogVisitaBean;
import com.solucionesweb.losbeans.fachada.FachadaColaboraDctoOrdenBean;
import com.solucionesweb.losbeans.fachada.FachadaDctoOrdenBean;
import com.solucionesweb.losbeans.fachada.FachadaDctoOrdenDetalleBean;
import com.solucionesweb.losbeans.fachada.FachadaDctoOrdenProgreso;
import com.solucionesweb.losbeans.fachada.FachadaJobOperacion;
import com.solucionesweb.losbeans.fachada.FachadaJobOperacionCosto;
import com.solucionesweb.losbeans.fachada.FachadaPluFicha;
import com.solucionesweb.losbeans.fachada.FachadaTerceroBean;
import com.solucionesweb.losbeans.negocio.AgendaLogVisitaBean;
import com.solucionesweb.losbeans.negocio.DctoOrdenBean;
import com.solucionesweb.losbeans.negocio.DctoOrdenDetalleBean;
import com.solucionesweb.losbeans.negocio.DctoOrdenProgresoBean;
import com.solucionesweb.losbeans.negocio.JobOperacionBean;
import com.solucionesweb.losbeans.negocio.JobOperacionCostoBean;
import com.solucionesweb.losbeans.negocio.UsuarioBean;
import com.solucionesweb.losbeans.utilidades.Day;
import com.solucionesweb.losbeans.utilidades.FichaTecnica;
import com.solucionesweb.losbeans.utilidades.Validacion;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Esta ventana permite ingresar a los operarios las diferentes operaciones por
 * las que tienen que pasar un pedido para producirlo. /
 * vtaContenedorOTProductoTouch.jsp /
 *
 * Este servlet implementa la interface GralManejadorRequest /
 */
// 
public class VtasAdmOTProductoTouchManejadorRequest
        implements GralManejadorRequest {

    /**
     * BUTTON-- ("Extrusion")-Seleccionar para ver ordenes de trabajo para esa
     * operacion / ("Impresion")-Seleccionar para ver ordenes de trabajo para
     * esa operacion / ("Manual")-Seleccionar para ver ordenes de trabajo para
     * esa operacion / ("Refilado")-Seleccionar para ver ordenes de trabajo para
     * esa operacion / ("Sellado")-Seleccionar para ver ordenes de trabajo para
     * esa operacion / ("Producir")-Ingreso para ventana de produccion /
     * ("Etiquetar")-Pemite ver una lista con materia prima y Genera etiquetas
     * para marcar la produccion / ("Retal")-permite el ingreso de materia prima
     * perdida / ("Ingresar")-Permite confirmar el ingreso de material
     * defectuoso / ("Suministrar")-permite ingreso de materia adicional para
     * produccion / ("Guardar")-permite guardar el ingreso de materia l
     * adicional para produccion / ("Confirmar")-Confirmar ingreso de datos de
     * produccion/
     *
     * Metodo contructor por defecto, es decir, sin parametros /
     */
    public VtasAdmOTProductoTouchManejadorRequest() {
    }

    /**
     * BUTTON PARAMETER-- selecciona operario que ejecuta la produccion/
     * "Peso(Kg) Finalizado"-Ingreso de material finalizado / "Peso(Kg)
     * Tara"-Ingreso de material finlizado / "Fecha/Hora inicio"-Fecha y hora
     * inicial de produccion / "#O.T"-Ingreso numero de orden de trabajo /
     * "Peso(Kg) Retal"-ingreso de peso de producto defectuoso / "Causa
     * retal"-seleccion de causa de produccion defectuosa / "Peso(Kg)
     * Suministro"-Ingreso de peso suministro /
     */
    public String generaPdf(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        //
        int xIdTipoOrdenTransformacion = 11;
        int xIdTipoOrdenPedido = 59;

        //
        String xObservacionVacia = "";
        String xIdDctoNitCCVacio = "";
        double xCantidadPendienteCero = 0.0;

        //
        Day day = new Day();

        //
        String xFechaVisita = day.getFechaFormateada();

        //
        HttpSession sesion = request.getSession();
        UsuarioBean usuarioBean
                = (UsuarioBean) sesion.getAttribute("usuarioBean");

        //
        double xIdUsuario = usuarioBean.getIdUsuario();
        int xIdLocalUsuario = usuarioBean.getIdLocalUsuario();

        //
        String accionContenedor = request.getParameter("accionContenedor");

        //
        System.out.println(accionContenedor);

        // Validacion de accion relacionada con el formulario requerido
        if (accionContenedor != null) {

            //
            if (accionContenedor.compareTo("Regresar") == 0) {

                //
                String xIdLocal = request.getParameter("xIdLocal");
                String xIdTipoOrden = request.getParameter("xIdTipoOrden");
                String xIdLog = request.getParameter("xIdLog");
                String xItem = request.getParameter("xItem");
                String xIdOperacion = request.getParameter("xIdOperacion");
                String xItemPadre = request.getParameter("xItemPadre");

                //--------------------------------------------------------------
                FachadaPluFicha fachadaPluFicha = new FachadaPluFicha();

                //
                fachadaPluFicha.setIdOperacion(xIdOperacion);
                fachadaPluFicha.setIdOperacion(xIdOperacion);
                fachadaPluFicha.setIdLocal(xIdLocal);
                fachadaPluFicha.setIdTipoOrden(xIdTipoOrden);
                fachadaPluFicha.setFechaPrograma(xFechaVisita);

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaPluFicha", fachadaPluFicha);

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                return "/jsp/vtaContenedorOTProductoTouch.jsp";

            }

            //
            if (accionContenedor.compareTo("imprimir") == 0) {

                //
                String xIdLocal = request.getParameter("xIdLocal");
                String xIdTipoOrden = request.getParameter("xIdTipoOrden");
                String xIdLog = request.getParameter("xIdLog");
                String xItem = request.getParameter("xItem");
                String xIdOperacion = request.getParameter("xIdOperacion");
                String xItemPadre = request.getParameter("xItemPadre");

                //
                FachadaDctoOrdenBean fachadaDctoOrdenBean
                        = new FachadaDctoOrdenBean();

                //
                DctoOrdenBean dctoOrdenBean = new DctoOrdenBean();

                //
                dctoOrdenBean.setIdLog(xIdLog);

                //
                fachadaDctoOrdenBean
                        = dctoOrdenBean.listaDctoOrdenIdLog();

                //
                int xNumeroOrden = fachadaDctoOrdenBean.getNumeroOrden();

                //---
                //
                GeneraPDFStickerOT generaPDFStickerOT = new GeneraPDFStickerOT();

                //
                generaPDFStickerOT.setIdLocal(xIdLocal);
                generaPDFStickerOT.setIdTipoOrden(xIdTipoOrden);
                generaPDFStickerOT.setIdLog(xIdLog);
                generaPDFStickerOT.setIdOperacion(xIdOperacion);
                generaPDFStickerOT.setItemPadre(xItemPadre);
                generaPDFStickerOT.setItem(xItem);
                generaPDFStickerOT.setNumeroOrden(xNumeroOrden);

                //
                generaPDFStickerOT.generaPdf(request, response);

            }

            //
            if (accionContenedor.compareTo("Etiquetar") == 0) {

                //
                String xIdLocal = request.getParameter("xIdLocal");
                String xIdTipoOrden = request.getParameter("xIdTipoOrden");
                String xIdOperacion = request.getParameter("xIdOperacion");
                String xIdLogItemPadre
                        = request.getParameter("xIdLogItemPadre");

                //--
                if (xIdLogItemPadre == null) {

                    //
                    Validacion valida = new Validacion();

                    //
                    valida.reasignar("O.T. NO EXISTE O SIN SELECCIONAR", "");

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
                int xLongitud = xIdLogItemPadre.length();
                int xUno = xIdLogItemPadre.indexOf('~');

                //
                String xIdLog = xIdLogItemPadre.substring(0, xUno);
                String xItemPadre = xIdLogItemPadre.substring(xUno + 1, xLongitud);

                //---------------------------------------------------------------
                AgendaLogVisitaBean agendaLogVisitaBean = new AgendaLogVisitaBean();

                //
                agendaLogVisitaBean.setIdLog(xIdLog);

                //
                FachadaAgendaLogVisitaBean fachadaAgendaLogVisitaBean = new FachadaAgendaLogVisitaBean();

                //
                fachadaAgendaLogVisitaBean
                        = agendaLogVisitaBean.listaLogFachada();

                //
                request.setAttribute("fachadaAgendaLogVisitaBean",
                        fachadaAgendaLogVisitaBean);

                //
                FachadaDctoOrdenBean fachadaDctoOrdenBean
                        = new FachadaDctoOrdenBean();

                //
                DctoOrdenBean dctoOrdenBean = new DctoOrdenBean();

                //
                dctoOrdenBean.setIdLog(xIdLog);

                //
                fachadaDctoOrdenBean
                        = dctoOrdenBean.listaDctoOrdenIdLog();

                //
                int xIdFicha = fachadaDctoOrdenBean.getIdFicha();
                String xIdCliente = fachadaDctoOrdenBean.getIdCliente();
                String xIdTipoTerceroProveedor = "2";
                int xNumeroOrden = fachadaDctoOrdenBean.getNumeroOrden();

                //
                fachadaDctoOrdenBean.setIdTipoTercero(xIdTipoTerceroProveedor);
                fachadaDctoOrdenBean.setIdOperacion(xIdOperacion);
                fachadaDctoOrdenBean.setItemPadre(xItemPadre);

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
                ColaboraDctoOrdenDetalleBean colaboraDctoOrdenDetalleBean
                        = new ColaboraDctoOrdenDetalleBean();

                //
                colaboraDctoOrdenDetalleBean.setItem(xItemPadre);
                colaboraDctoOrdenDetalleBean.setIdTipoOrden(
                        xIdTipoOrdenCotizacion);

                //
                FachadaDctoOrdenDetalleBean fachadaDctoOrdenDetalleBean
                        = new FachadaDctoOrdenDetalleBean();

                //
                fachadaDctoOrdenDetalleBean
                        = colaboraDctoOrdenDetalleBean.itemLogFachada(
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

                //
                FachadaPluFicha fachadaPluFicha = new FachadaPluFicha();

                //
                ColaboraOrdenTrabajo colaboraOrdenTrabajo = new ColaboraOrdenTrabajo();

                //
                colaboraOrdenTrabajo.setIdFicha(xIdFicha);

                //
                fachadaPluFicha = colaboraOrdenTrabajo.listaIdFichaFCH();

                //
                fachadaPluFicha.setIdOperacion(xIdOperacion);
                fachadaPluFicha.setIdFicha(xIdFicha);
                fachadaPluFicha.setPesoPedido(xPesoPedido);
                fachadaPluFicha.setCantidad(xCantidad);
                fachadaPluFicha.setFechaPrograma(xFechaVisita);
                fachadaPluFicha.setIdLocal(xIdLocal);
                fachadaPluFicha.setIdTipoOrden(xIdTipoOrden);
                fachadaPluFicha.setIdLog(xIdLog);
                fachadaPluFicha.setItemPadre(xItemPadre);

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaPluFicha", fachadaPluFicha);

                //
                FachadaPluFicha fachadaPluFichaAcumula = new FachadaPluFicha();

                //
                colaboraOrdenTrabajo.setIdLocal(xIdLocal);
                colaboraOrdenTrabajo.setIdTipoOrden(xIdTipoOrden);
                colaboraOrdenTrabajo.setIdFicha(xIdFicha);
                colaboraOrdenTrabajo.setNumeroOrden(xNumeroOrden);
                colaboraOrdenTrabajo.setItemPadre(xItemPadre);
                colaboraOrdenTrabajo.setIdOperacion(xIdOperacion);
                colaboraOrdenTrabajo.setFechaPrograma(xFechaVisita);

                //
                fachadaPluFichaAcumula = colaboraOrdenTrabajo.listaOTProgramaTouchFCH();

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaPluFichaAcumula",
                        fachadaPluFichaAcumula);

                //---
                DctoOrdenProgresoBean dctoOrdenProgresoBean
                        = new DctoOrdenProgresoBean();

                //
                dctoOrdenProgresoBean.setIdLocal(xIdLocal);
                dctoOrdenProgresoBean.setIdTipoOrden(xIdTipoOrden);
                dctoOrdenProgresoBean.setIdOrden(
                        fachadaDctoOrdenBean.getIdOrden());
                dctoOrdenProgresoBean.setIdOperacion(xIdOperacion);
                dctoOrdenProgresoBean.setItemPadre(xItemPadre);

                //
                FachadaDctoOrdenProgreso fachadaDctoOrdenProgreso = new FachadaDctoOrdenProgreso();

                //
                fachadaDctoOrdenProgreso
                        = dctoOrdenProgresoBean.listaUltimoIngresoFCH();

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaDctoOrdenProgreso",
                        fachadaDctoOrdenProgreso);
                //
                return "/jsp/vtaFrmManOTProductoTouch.jsp";

                //---------------------------------------------------------------
            }

            //
            if (accionContenedor.compareTo("Ingresar") == 0) {

                //
                String xIdLocal = request.getParameter("xIdLocal");
                String xIdTipoOrden = request.getParameter("xIdTipoOrden");
                String xNumeroOrdenStr = request.getParameter("xNumeroOrden");
                String xCantidadTerminada = "0.0";
                String xPesoTerminado = "0.0";
                String xPesoPerdido = request.getParameter("xPesoPerdido");
                String xIdOperario = request.getParameter("xIdOperario");
                String xIdCausa = request.getParameter("xIdCausa");
                String xEstado = "1";
                String xIdOperacion = request.getParameter("xIdOperacion");
                String xPesoTara = "0.0";
                String xFechaFin
                        = request.getParameter("xFechaFin");

                //
                int xItemPadre = 1;
                int xIdBodegaSiguienteRetales = 666;

                //
                Validacion valida = new Validacion();

                //
                valida.reasignar("OPERARIO/TERCERO", xIdOperario);

                //
                valida.validarCampoDouble();

                //isValido
                if (!valida.isValido()) {

                    //
                    request.setAttribute("validacion", valida);
                    return "/jsp/gralError.jsp";
                }

                //
                valida.reasignar("#PEDIDO", xNumeroOrdenStr);

                //
                valida.validarCampoEntero();

                //isValido
                if (!valida.isValido()) {

                    //
                    request.setAttribute("validacion", valida);
                    return "/jsp/gralError.jsp";
                }

                //
                valida.reasignar("KG.RETAL", xPesoPerdido);

                //
                valida.validarCampoDouble();

                //isValido
                if (!valida.isValido()) {

                    //
                    request.setAttribute("validacion", valida);
                    return "/jsp/gralError.jsp";
                }

                //
                if (new Double(xPesoPerdido).doubleValue() == 0) {

                    //
                    valida.reasignar("ERROR, FALTA INGRESAR KG.RETAL", "");

                    //
                    request.setAttribute("validacion", valida);
                    return "/jsp/gralError.jsp";
                }

                //
                valida.reasignar("CAUSA RETAL", xIdCausa);

                //
                valida.validarCampoEntero();

                //isValido
                if (!valida.isValido()) {

                    //
                    request.setAttribute("validacion", valida);
                    return "/jsp/gralError.jsp";
                }

                //--------------------------------------------------------------
                FachadaColaboraDctoOrdenBean fachadaColaboraDctoOrdenBean
                        = new FachadaColaboraDctoOrdenBean();

                //
                ColaboraDctoOrdenBean colaboraDctoOrdenBean
                        = new ColaboraDctoOrdenBean();

                //
                colaboraDctoOrdenBean.setIdLocal(xIdLocal);
                colaboraDctoOrdenBean.setIdTipoOrden(xIdTipoOrden);
                colaboraDctoOrdenBean.setNumeroOrden(xNumeroOrdenStr);

                //
                fachadaColaboraDctoOrdenBean
                        = colaboraDctoOrdenBean.listaOTFCH();

                //--------------------------------------------------------------
                int xIdLog = fachadaColaboraDctoOrdenBean.getIdLog();

                //
                if (xIdLog == 0) {

                    //
                    valida.reasignar("ERROR, NO EXISTE #O.T.", xNumeroOrdenStr);

                    //
                    request.setAttribute("validacion", valida);
                    return "/jsp/gralError.jsp";

                }

                //---
                AgendaLogVisitaBean agendaLogVisitaBean = new AgendaLogVisitaBean();

                //
                agendaLogVisitaBean.setIdLog(xIdLog);

                //
                FachadaAgendaLogVisitaBean fachadaAgendaLogVisitaBean = new FachadaAgendaLogVisitaBean();

                //---
                fachadaAgendaLogVisitaBean
                        = agendaLogVisitaBean.listaLogFachada();

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
                fachadaDctoOrdenBean
                        = dctoOrdenBean.listaDctoOrdenIdLog();

                //
                int xIdOrdenOrigen = fachadaDctoOrdenBean.getIdOrden();
                int xIdLocalOrigen = fachadaDctoOrdenBean.getIdLocal();
                int xIdTipoOrdenOrigen = fachadaDctoOrdenBean.getIdTipoOrden();
                int xNumeroOrden = fachadaDctoOrdenBean.getNumeroOrden();

                //
                int xIdFicha = fachadaDctoOrdenBean.getIdFicha();
                String xIdCliente = fachadaDctoOrdenBean.getIdCliente();
                String xIdTipoTerceroProveedor = "2";

                //
                fachadaDctoOrdenBean.setIdTipoTercero(xIdTipoTerceroProveedor);
                fachadaDctoOrdenBean.setIdOperacion(xIdOperacion);
                fachadaDctoOrdenBean.setItemPadre(xItemPadre);

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaDctoOrdenBean", fachadaDctoOrdenBean);

                //
                String xIdTipoOrdenCotizacion = "59";

                //
                String xIdTipoTerceroCliente = "1";

                //
                FachadaTerceroBean fachadaTerceroBean = new FachadaTerceroBean();

                //
                ColaboraTercero colaboraTercero = new ColaboraTercero();

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
                FachadaDctoOrdenDetalleBean fachadaDctoOrdenDetalleBean
                        = new FachadaDctoOrdenDetalleBean();

                //
                ColaboraDctoOrdenDetalleBean colaboraDctoOrdenDetalleBean
                        = new ColaboraDctoOrdenDetalleBean();

                //
                colaboraDctoOrdenDetalleBean.setItem(xItemPadre);
                colaboraDctoOrdenDetalleBean.setIdTipoOrden(
                        xIdTipoOrdenCotizacion);

                //
                fachadaDctoOrdenDetalleBean
                        = colaboraDctoOrdenDetalleBean.itemLogFachada(
                                new Integer(xIdLog).intValue());

                //
                Double xCantidad = fachadaDctoOrdenDetalleBean.getCantidad();
                Double xCantidadPerdida = 0.0;

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaDctoOrdenDetalleBean",
                        fachadaDctoOrdenDetalleBean);

                //--------------------------------------------------------------
                DctoOrdenProgresoBean dctoOrdenProgresoBean = new DctoOrdenProgresoBean();

                //
                dctoOrdenProgresoBean.setIdLocal(xIdLocal);
                dctoOrdenProgresoBean.setIdTipoOrden(xIdTipoOrden);
                dctoOrdenProgresoBean.setIdOrden(
                        fachadaDctoOrdenBean.getIdOrden());
                dctoOrdenProgresoBean.setItemPadre(xItemPadre);
                dctoOrdenProgresoBean.setIdOperacion(xIdOperacion);

                //---
                int xMaximoItem = dctoOrdenProgresoBean.maximoItem() + 1;

                //--------------------------------------------------------------
                FachadaJobOperacionCosto fachadaJobOperacionCosto
                        = new FachadaJobOperacionCosto();

                //
                JobOperacionCostoBean jobOperacionCostoBean
                        = new JobOperacionCostoBean();

                //---
                jobOperacionCostoBean.setIdLocal(xIdLocal);
                jobOperacionCostoBean.setIdOperacion(xIdOperacion);

                //---
                fachadaJobOperacionCosto
                        = jobOperacionCostoBean.listaCostoFCH();

                //---
                double xVrCostoBaseMAT
                        = fachadaJobOperacionCosto.getVrCostoBaseMAT();
                double xVrCostoBaseMOD
                        = fachadaJobOperacionCosto.getVrCostoBaseMOD();
                double xVrCostoBaseCIF
                        = fachadaJobOperacionCosto.getVrCostoBaseCIF();

                //---
                int xIdTipoCausaRetal = 2;

                //--------------------------------------------------------------
                dctoOrdenProgresoBean.setItem(xMaximoItem);
                dctoOrdenProgresoBean.setIdOperario(xIdOperario);
                dctoOrdenProgresoBean.setCantidadPerdida(xCantidadPerdida);
                dctoOrdenProgresoBean.setCantidadTerminada(xCantidadTerminada);
                dctoOrdenProgresoBean.setPesoTerminado(xPesoTerminado);
                dctoOrdenProgresoBean.setPesoPerdido(xPesoPerdido);
                dctoOrdenProgresoBean.setIdCausa(xIdCausa);
                dctoOrdenProgresoBean.setIdTipoCausa(xIdTipoCausaRetal);
                dctoOrdenProgresoBean.setEstado(xEstado);
                dctoOrdenProgresoBean.setItemPadre(xItemPadre);
                dctoOrdenProgresoBean.setVrCostoBaseMAT(xVrCostoBaseMAT);
                dctoOrdenProgresoBean.setVrCostoBaseCIF(xVrCostoBaseCIF);
                dctoOrdenProgresoBean.setVrCostoBaseMOD(xVrCostoBaseMOD);
                dctoOrdenProgresoBean.setPesoTara(xPesoTara);
                dctoOrdenProgresoBean.setIdUsuario(xIdUsuario);
                dctoOrdenProgresoBean.setCantidadPendiente(xCantidadPendienteCero);
                dctoOrdenProgresoBean.setObservacion(xObservacionVacia);

                //---
                dctoOrdenProgresoBean.ingresaInicio();

                //--------------------------------------------------------------
                ColaboraOperacionPlu colaboraOperacionPlu
                        = new ColaboraOperacionPlu();

                //
                colaboraOperacionPlu.setIdFicha(xIdFicha);
                colaboraOperacionPlu.setIdOperacion(xIdOperacion);

                //
                double xTotalMP = colaboraOperacionPlu.totalMP();

                //
                ProcesoIngresoRetal proceso = new ProcesoIngresoRetal();

                //
                proceso.ingresaRetal(new Integer(xIdLocal).intValue(),
                        xIdTipoOrdenTransformacion,
                        xIdUsuario,
                        xIdFicha,
                        new Integer(xIdOperacion).intValue(),
                        new Double(xPesoPerdido).doubleValue(),
                        new Double(xPesoPerdido).doubleValue(),
                        xTotalMP,
                        xIdCliente,
                        xIdOrdenOrigen,
                        xIdLocalOrigen,
                        xIdTipoOrdenOrigen,
                        xNumeroOrden,
                        new Integer(xItemPadre).intValue());

                //--------------------------------------------------------------
                DctoOrdenDetalleBean dctoOrdenDetalleBean = new DctoOrdenDetalleBean();

                //
                dctoOrdenDetalleBean.actualizaOT_Almacen();

                //--- actualizaPedidoPendiente ---------------------------------
                dctoOrdenDetalleBean.setIdLocal(xIdLocal);
                dctoOrdenDetalleBean.setIdTipoOrden(xIdTipoOrden);
                dctoOrdenDetalleBean.setIdLog(xIdLog);
                dctoOrdenDetalleBean.setItem(xItemPadre);

                //
                dctoOrdenDetalleBean.actualizaPedidoPendiente();

                //--------------------------------------------------------------
                FichaTecnica fichaTecnica = new FichaTecnica();

                //
                double xPesoPedido = fichaTecnica.pesoPedido(xIdCliente,
                        new Integer(xIdFicha).intValue(),
                        new Double(xCantidad).doubleValue());

                //
                FachadaPluFicha fachadaPluFicha = new FachadaPluFicha();

                //
                ColaboraOrdenTrabajo colaboraOrdenTrabajo = new ColaboraOrdenTrabajo();

                //
                colaboraOrdenTrabajo.setIdFicha(xIdFicha);

                //
                fachadaPluFicha = colaboraOrdenTrabajo.listaIdFichaFCH();

                //
                fachadaPluFicha.setIdOperacion(xIdOperacion);
                fachadaPluFicha.setIdFicha(xIdFicha);
                fachadaPluFicha.setPesoPedido(xPesoPedido);
                fachadaPluFicha.setCantidad(xCantidad);
                fachadaPluFicha.setIdOperacion(xIdOperacion);
                fachadaPluFicha.setIdFicha(xIdFicha);
                fachadaPluFicha.setIdLocal(xIdLocal);
                fachadaPluFicha.setIdTipoOrden(xIdTipoOrden);
                fachadaPluFicha.setFechaPrograma(xFechaVisita);

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaPluFicha", fachadaPluFicha);

                //
                FachadaDctoOrdenProgreso fachadaDctoOrdenConcluida = new FachadaDctoOrdenProgreso();

                //
                fachadaDctoOrdenConcluida
                        = dctoOrdenProgresoBean.listaTotalFCH();

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaDctoOrdenConcluida",
                        fachadaDctoOrdenConcluida);

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                return "/jsp/vtaContenedorOTProductoTouch.jsp";

            }

            // Guardar
            if (accionContenedor.compareTo("Guardar") == 0) {

                //
                String xIdLocal = request.getParameter("xIdLocal");
                String xIdTipoOrden = request.getParameter("xIdTipoOrden");
                String xIdOperacion = request.getParameter("xIdOperacion");
                String xIdLog = request.getParameter("xIdLog");
                String xCantidadTerminada = "0.0";
                String xPesoTerminado = request.getParameter("xPesoTerminado");
                String xPesoPerdido = "0.0";
                String xIdOperario = request.getParameter("xIdOperario");
                String xIdCausa = "0";
                String xEstado = "1";
                String xItemPadre = request.getParameter("xItemPadre");
                String xPesoTara = "0.0";
                String xIdPlu = request.getParameter("xIdPlu");

                //
                Validacion valida = new Validacion();

                //
                valida.reasignar("PESO(KG) SUMINISTRO ", xPesoTerminado);

                //
                valida.validarCampoDouble();

                //isValido
                if (!valida.isValido()) {

                    //
                    request.setAttribute("validacion", valida);
                    return "/jsp/gralError.jsp";
                }

                //
                valida.reasignar("NOMBRE SUMINISTRO", xIdPlu);

                //
                valida.validarCampoEntero();

                //isValido
                if (!valida.isValido()) {

                    //
                    request.setAttribute("validacion", valida);
                    return "/jsp/gralError.jsp";
                }

                //
                valida.reasignar("OPERARIO/TERCERO", xIdOperario);

                //
                valida.validarCampoDouble();

                //isValido
                if (!valida.isValido()) {

                    //
                    request.setAttribute("validacion", valida);
                    return "/jsp/gralError.jsp";
                }

                //--------------------------------------------------------------
                AgendaLogVisitaBean agendaLogVisitaBean = new AgendaLogVisitaBean();

                //
                agendaLogVisitaBean.setIdLog(xIdLog);

                //
                FachadaAgendaLogVisitaBean fachadaAgendaLogVisitaBean = new FachadaAgendaLogVisitaBean();

                //---
                fachadaAgendaLogVisitaBean
                        = agendaLogVisitaBean.listaLogFachada();

                //
                request.setAttribute("fachadaAgendaLogVisitaBean",
                        fachadaAgendaLogVisitaBean);

                //
                FachadaDctoOrdenBean fachadaDctoOrdenBean = new FachadaDctoOrdenBean();

                //--------------------------------------------------------------
                DctoOrdenBean dctoOrdenBean = new DctoOrdenBean();

                //
                dctoOrdenBean.setIdLog(xIdLog);

                //
                fachadaDctoOrdenBean = dctoOrdenBean.listaDctoOrdenIdLog();

                //
                int xIdOrdenOrigen = fachadaDctoOrdenBean.getIdOrden();
                int xIdLocalOrigen = fachadaDctoOrdenBean.getIdLocal();
                int xIdTipoOrdenOrigen = fachadaDctoOrdenBean.getIdTipoOrden();
                int xNumeroOrden = fachadaDctoOrdenBean.getNumeroOrden();

                //
                int xIdFicha = fachadaDctoOrdenBean.getIdFicha();
                String xIdCliente = fachadaDctoOrdenBean.getIdCliente();
                String xIdTipoTerceroProveedor = "2";

                //
                fachadaDctoOrdenBean.setIdTipoTercero(xIdTipoTerceroProveedor);
                fachadaDctoOrdenBean.setIdOperacion(xIdOperacion);
                fachadaDctoOrdenBean.setItemPadre(xItemPadre);

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaDctoOrdenBean", fachadaDctoOrdenBean);

                //
                String xIdTipoOrdenCotizacion = "59";

                //
                String xIdTipoTerceroCliente = "1";

                //--------------------------------------------------------------
                FachadaTerceroBean fachadaTerceroBean = new FachadaTerceroBean();

                //
                ColaboraTercero colaboraTercero = new ColaboraTercero();

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

                //--------------------------------------------------------------
                FachadaDctoOrdenDetalleBean fachadaDctoOrdenDetalleBean
                        = new FachadaDctoOrdenDetalleBean();

                //
                ColaboraDctoOrdenDetalleBean colaboraDctoOrdenDetalleBean
                        = new ColaboraDctoOrdenDetalleBean();

                //
                colaboraDctoOrdenDetalleBean.setItem(xItemPadre);
                colaboraDctoOrdenDetalleBean.setIdTipoOrden(
                        xIdTipoOrdenCotizacion);

                //
                fachadaDctoOrdenDetalleBean
                        = colaboraDctoOrdenDetalleBean.itemLogFachada(
                                new Integer(xIdLog).intValue());
                fachadaDctoOrdenDetalleBean.setIdPlu(xIdPlu);

                //
                Double xCantidad = fachadaDctoOrdenDetalleBean.getCantidad();
                Double xCantidadPerdida = 0.0;

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaDctoOrdenDetalleBean",
                        fachadaDctoOrdenDetalleBean);

                //--------------------------------------------------------------
                FachadaDctoOrdenProgreso fachadaDctoOrdenProgreso = new FachadaDctoOrdenProgreso();

                //
                DctoOrdenProgresoBean dctoOrdenProgresoBean = new DctoOrdenProgresoBean();

                //
                dctoOrdenProgresoBean.setIdLocal(xIdLocal);
                dctoOrdenProgresoBean.setIdTipoOrden(xIdTipoOrden);
                dctoOrdenProgresoBean.setIdOrden(
                        fachadaDctoOrdenBean.getIdOrden());
                dctoOrdenProgresoBean.setItemPadre(xItemPadre);
                dctoOrdenProgresoBean.setIdOperacion(xIdOperacion);

                //---
                int xMaximoItem = dctoOrdenProgresoBean.maximoItem() + 1;

                //--------------------------------------------------------------
                FachadaJobOperacionCosto fachadaJobOperacionCosto = new FachadaJobOperacionCosto();

                //
                JobOperacionCostoBean jobOperacionCostoBean
                        = new JobOperacionCostoBean();

                //---
                jobOperacionCostoBean.setIdLocal(xIdLocal);
                jobOperacionCostoBean.setIdOperacion(xIdOperacion);

                //---
                fachadaJobOperacionCosto
                        = jobOperacionCostoBean.listaCostoFCH();

                //---
                double xVrCostoBaseMAT = 0.0;
                double xVrCostoBaseMOD = 0.0;
                double xVrCostoBaseCIF = 0.0;

                //---
                int xIdTipoCausaSuministro = 4;

                //--------------------------------------------------------------
                dctoOrdenProgresoBean.setItem(xMaximoItem);
                dctoOrdenProgresoBean.setIdOperario(xIdOperario);
                dctoOrdenProgresoBean.setCantidadPerdida(xCantidadPerdida);
                dctoOrdenProgresoBean.setCantidadTerminada(xCantidadTerminada);
                dctoOrdenProgresoBean.setPesoTerminado(xPesoTerminado);
                dctoOrdenProgresoBean.setPesoPerdido(xPesoPerdido);
                dctoOrdenProgresoBean.setIdCausa(xIdCausa);
                dctoOrdenProgresoBean.setIdTipoCausa(xIdTipoCausaSuministro);
                dctoOrdenProgresoBean.setEstado(xEstado);
                dctoOrdenProgresoBean.setItemPadre(xItemPadre);
                dctoOrdenProgresoBean.setVrCostoBaseMAT(xVrCostoBaseMAT);
                dctoOrdenProgresoBean.setVrCostoBaseCIF(xVrCostoBaseCIF);
                dctoOrdenProgresoBean.setVrCostoBaseMOD(xVrCostoBaseMOD);
                dctoOrdenProgresoBean.setPesoTara(xPesoTara);
                dctoOrdenProgresoBean.setIdUsuario(xIdUsuario);
                dctoOrdenProgresoBean.setCantidadPendiente(xCantidadPendienteCero);
                dctoOrdenProgresoBean.setObservacion(xObservacionVacia);
                dctoOrdenProgresoBean.setIdPlu(xIdPlu);

                //---
                dctoOrdenProgresoBean.ingresa();

                //--------------------------------------------------------------
                ColaboraOperacionPlu colaboraOperacionPlu
                        = new ColaboraOperacionPlu();

                //
                colaboraOperacionPlu.setIdFicha(xIdFicha);
                colaboraOperacionPlu.setIdOperacion(xIdOperacion);

                //
                double xTotalMP = colaboraOperacionPlu.totalMP();

                //
                ProcesoIngresoSuminisitro proceso = new ProcesoIngresoSuminisitro();

                //
                proceso.ingresa(new Integer(xIdLocal).intValue(),
                        xIdTipoOrdenTransformacion,
                        xIdUsuario,
                        xIdFicha,
                        new Integer(xIdOperacion).intValue(),
                        new Double(xPesoTerminado).doubleValue(),
                        new Double(xCantidadTerminada).doubleValue(),
                        xTotalMP,
                        xIdCliente,
                        xIdOrdenOrigen,
                        xIdLocalOrigen,
                        xIdTipoOrdenOrigen,
                        xNumeroOrden,
                        new Integer(xItemPadre).intValue(),
                        new Integer(xIdPlu).intValue());

                /*--------------------------------------------------------------
                DctoOrdenDetalleBean dctoOrdenDetalleBean = new DctoOrdenDetalleBean();
                
                //
                dctoOrdenDetalleBean.actualizaOT_Almacen();
                
                //--- actualizaPedidoPendiente ---------------------------------
                dctoOrdenDetalleBean.setIdLocal(xIdLocal);
                dctoOrdenDetalleBean.setIdTipoOrden(xIdTipoOrden);
                dctoOrdenDetalleBean.setIdLog(xIdLog);
                dctoOrdenDetalleBean.setItem(xItemPadre);
                
                //
                dctoOrdenDetalleBean.actualizaPedidoPendiente();*/
                //--------------------------------------------------------------
                FichaTecnica fichaTecnica = new FichaTecnica();

                //
                double xPesoPedido = fichaTecnica.pesoPedido(xIdCliente,
                        new Integer(xIdFicha).intValue(),
                        new Double(xCantidad).doubleValue());

                //
                FachadaPluFicha fachadaPluFicha = new FachadaPluFicha();

                //
                ColaboraOrdenTrabajo colaboraOrdenTrabajo = new ColaboraOrdenTrabajo();

                //
                colaboraOrdenTrabajo.setIdFicha(xIdFicha);

                //
                fachadaPluFicha = colaboraOrdenTrabajo.listaIdFichaFCH();

                //
                fachadaPluFicha.setIdOperacion(xIdOperacion);
                fachadaPluFicha.setIdFicha(xIdFicha);
                fachadaPluFicha.setPesoPedido(xPesoPedido);
                fachadaPluFicha.setCantidad(xCantidad);
                fachadaPluFicha.setIdOperacion(xIdOperacion);
                fachadaPluFicha.setIdFicha(xIdFicha);
                fachadaPluFicha.setIdLocal(xIdLocal);
                fachadaPluFicha.setIdTipoOrden(xIdTipoOrden);
                fachadaPluFicha.setFechaPrograma(xFechaVisita);

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaPluFicha", fachadaPluFicha);

                //
                FachadaDctoOrdenProgreso fachadaDctoOrdenConcluida = new FachadaDctoOrdenProgreso();

                //
                fachadaDctoOrdenConcluida
                        = dctoOrdenProgresoBean.listaTotalFCH();

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaDctoOrdenConcluida",
                        fachadaDctoOrdenConcluida);

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                return "/jsp/vtaContenedorOTProductoTouch.jsp";

            }

            // Suministrar
            if (accionContenedor.compareTo("Suministrar") == 0) {

                //
                String xIdLocal = request.getParameter("xIdLocal");
                String xIdTipoOrden = request.getParameter("xIdTipoOrden");
                String xIdOperacion = request.getParameter("xIdOperacion");
                String xIdLogItemPadre
                        = request.getParameter("xIdLogItemPadre");

                //--
                if (xIdLogItemPadre == null) {

                    //
                    Validacion valida = new Validacion();

                    //
                    valida.reasignar("O.T. NO EXISTE O SIN SELECCIONAR", "");

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
                int xLongitud = xIdLogItemPadre.length();
                int xUno = xIdLogItemPadre.indexOf('~');

                //
                String xIdLog = xIdLogItemPadre.substring(0, xUno);
                String xItemPadre = xIdLogItemPadre.substring(xUno + 1, xLongitud);

                //---------------------------------------------------------------
                AgendaLogVisitaBean agendaLogVisitaBean = new AgendaLogVisitaBean();

                //
                agendaLogVisitaBean.setIdLog(xIdLog);

                //
                FachadaAgendaLogVisitaBean fachadaAgendaLogVisitaBean = new FachadaAgendaLogVisitaBean();

                //
                fachadaAgendaLogVisitaBean
                        = agendaLogVisitaBean.listaLogFachada();

                //
                request.setAttribute("fachadaAgendaLogVisitaBean",
                        fachadaAgendaLogVisitaBean);

                //
                FachadaDctoOrdenBean fachadaDctoOrdenBean
                        = new FachadaDctoOrdenBean();

                //
                DctoOrdenBean dctoOrdenBean = new DctoOrdenBean();

                //
                dctoOrdenBean.setIdLog(xIdLog);

                //
                fachadaDctoOrdenBean
                        = dctoOrdenBean.listaDctoOrdenIdLog();

                //
                int xIdFicha = fachadaDctoOrdenBean.getIdFicha();
                String xIdCliente = fachadaDctoOrdenBean.getIdCliente();
                String xIdTipoTerceroProveedor = "2";
                int xNumeroOrden = fachadaDctoOrdenBean.getNumeroOrden();

                //
                fachadaDctoOrdenBean.setIdTipoTercero(xIdTipoTerceroProveedor);
                fachadaDctoOrdenBean.setIdOperacion(xIdOperacion);
                fachadaDctoOrdenBean.setItemPadre(xItemPadre);

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
                ColaboraDctoOrdenDetalleBean colaboraDctoOrdenDetalleBean
                        = new ColaboraDctoOrdenDetalleBean();

                //
                colaboraDctoOrdenDetalleBean.setItem(xItemPadre);
                colaboraDctoOrdenDetalleBean.setIdTipoOrden(
                        xIdTipoOrdenCotizacion);

                //
                FachadaDctoOrdenDetalleBean fachadaDctoOrdenDetalleBean
                        = new FachadaDctoOrdenDetalleBean();

                //
                fachadaDctoOrdenDetalleBean
                        = colaboraDctoOrdenDetalleBean.itemLogFachada(
                                new Integer(xIdLog).intValue());

                //
                Double xCantidad = fachadaDctoOrdenDetalleBean.getCantidad();

                //
                int xIdTipoInventario = 1;

                //
                fachadaDctoOrdenDetalleBean.setItem(xItemPadre);
                fachadaDctoOrdenDetalleBean.setIdTipo(xIdTipoInventario);

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaDctoOrdenDetalleBean",
                        fachadaDctoOrdenDetalleBean);

                //
                FichaTecnica fichaTecnica = new FichaTecnica();

                //
                double xPesoPedido = fichaTecnica.pesoPedido(xIdCliente,
                        new Integer(xIdFicha).intValue(),
                        new Double(xCantidad).doubleValue());

                //
                FachadaPluFicha fachadaPluFicha = new FachadaPluFicha();

                //
                ColaboraOrdenTrabajo colaboraOrdenTrabajo = new ColaboraOrdenTrabajo();

                //
                colaboraOrdenTrabajo.setIdFicha(xIdFicha);

                //
                fachadaPluFicha = colaboraOrdenTrabajo.listaIdFichaFCH();

                //
                fachadaPluFicha.setIdOperacion(xIdOperacion);
                fachadaPluFicha.setIdFicha(xIdFicha);
                fachadaPluFicha.setPesoPedido(xPesoPedido);
                fachadaPluFicha.setCantidad(xCantidad);
                fachadaPluFicha.setFechaPrograma(xFechaVisita);
                fachadaPluFicha.setIdLocal(xIdLocal);
                fachadaPluFicha.setIdTipoOrden(xIdTipoOrden);
                fachadaPluFicha.setIdLog(xIdLog);
                fachadaPluFicha.setItemPadre(xItemPadre);

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaPluFicha", fachadaPluFicha);

                //
                FachadaPluFicha fachadaPluFichaAcumula = new FachadaPluFicha();

                //
                colaboraOrdenTrabajo.setIdLocal(xIdLocal);
                colaboraOrdenTrabajo.setIdTipoOrden(xIdTipoOrden);
                colaboraOrdenTrabajo.setIdFicha(xIdFicha);
                colaboraOrdenTrabajo.setNumeroOrden(xNumeroOrden);
                colaboraOrdenTrabajo.setItemPadre(xItemPadre);
                colaboraOrdenTrabajo.setIdOperacion(xIdOperacion);
                colaboraOrdenTrabajo.setFechaPrograma(xFechaVisita);

                //
                fachadaPluFichaAcumula = colaboraOrdenTrabajo.listaOTProgramaTouchFCH();

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaPluFichaAcumula",
                        fachadaPluFichaAcumula);

                //---
                DctoOrdenProgresoBean dctoOrdenProgresoBean
                        = new DctoOrdenProgresoBean();

                //
                dctoOrdenProgresoBean.setIdLocal(xIdLocal);
                dctoOrdenProgresoBean.setIdTipoOrden(xIdTipoOrden);
                dctoOrdenProgresoBean.setIdOrden(
                        fachadaDctoOrdenBean.getIdOrden());
                dctoOrdenProgresoBean.setIdOperacion(xIdOperacion);
                dctoOrdenProgresoBean.setItemPadre(xItemPadre);

                //
                FachadaDctoOrdenProgreso fachadaDctoOrdenProgreso = new FachadaDctoOrdenProgreso();

                //
                fachadaDctoOrdenProgreso
                        = dctoOrdenProgresoBean.listaUltimoIngresoFCH();

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaDctoOrdenProgreso",
                        fachadaDctoOrdenProgreso);

                //
                return "/jsp/vtaFrmSumOTProductoTouch.jsp";

            }

            //
            if (accionContenedor.compareTo("Confirmar") == 0) {

                //-------------------------ingreso -----------------------------
                String xIdLocal = request.getParameter("xIdLocal");
                String xIdTipoOrden = request.getParameter("xIdTipoOrden");
                String xIdLog = request.getParameter("xIdLog");
                String xCantidadTerminada
                        = request.getParameter("xCantidadTerminada");
                String xPesoTerminado = request.getParameter("xPesoTerminado");
                String xPesoPerdido = "0.0";
                String xIdOperario = request.getParameter("xIdOperario");
                String xIdCausa = "0";
                String xEstado = "1";
                String xIdOperacion = request.getParameter("xIdOperacion");
                String xItemPadre = request.getParameter("xItemPadre");
                String xPesoTara = request.getParameter("xPesoTara");
                String xFechaHoraInicio
                        = request.getParameter("xFechaHoraInicio");
                String xFechaFin
                        = request.getParameter("xFechaFin");
                String idMaquina = request.getParameter("listaMaquinas");
                int idOperacion = Integer.parseInt(xIdOperacion);
                boolean esSellRef = (idOperacion == 4 || idOperacion == 5);
                int idTurno = esSellRef ? Integer.parseInt(request.getParameter("listaTurnos")) : 0;
                String fechaProduccion = esSellRef ? request.getParameter("fecha") : "";
                //--------------------------------------------------------------
                if ((xIdOperacion.compareTo("2") == 0)
                        || (xIdOperacion.compareTo("3") == 0)
                        || (xIdOperacion.compareTo("4") == 0)) {

                    //
                    xCantidadTerminada = request.getParameter("xPesoTerminado");

                }

                //------------------------- validacion -------------------------
                Validacion valida = new Validacion();
                valida.reasignar("turno", request.getParameter("listaTurnos"));
                valida.validarCampoDouble();
                if (!valida.isValido() && esSellRef) {

                    //
                    request.setAttribute("validacion", valida);
                    return "/jsp/gralError.jsp";
                }
                valida.reasignar("Fecha Produccion", fechaProduccion);

                if (fechaProduccion.isEmpty() && esSellRef) {
                    request.setAttribute("validacion", valida);
                    return "/jsp/gralError.jsp";
                }

                //
                valida.reasignar("CAN.FINALIZADA", xCantidadTerminada);

                //
                valida.validarCampoDoublePositivo();

                //isValido
                if (!valida.isValido()) {

                    //
                    request.setAttribute("validacion", valida);
                    return "/jsp/gralError.jsp";
                }

                //
                valida.reasignar("KG.TARA", xPesoTara);

                //
                valida.validarCampoDoublePositivo();

                //isValido
                if (!valida.isValido()) {

                    //
                    request.setAttribute("validacion", valida);
                    return "/jsp/gralError.jsp";
                }

                //
                valida.reasignar("KG.FINALIZADA", xPesoTerminado);

                //
                valida.validarCampoDoublePositivo();

                //isValido
                if (!valida.isValido()) {

                    //
                    request.setAttribute("validacion", valida);
                    return "/jsp/gralError.jsp";
                }

                //
                valida.reasignar("KG.RETAL", xPesoPerdido);

                //
                valida.validarCampoDoublePositivo();

                //isValido
                if (!valida.isValido()) {

                    //
                    request.setAttribute("validacion", valida);
                    return "/jsp/gralError.jsp";
                }

                //
                valida.reasignar("OPERARIO/TERCERO", xIdOperario);

                //
                valida.validarCampoDouble();

                //isValido
                if (!valida.isValido()) {

                    //
                    request.setAttribute("validacion", valida);
                    return "/jsp/gralError.jsp";
                }

                //
                if (!((new Double(xPesoTerminado).doubleValue() != 0)
                        || (new Double(xPesoPerdido).doubleValue() != 0))) {

                    //
                    valida.reasignar("ERROR, FALTA INGRESAR CAN.FINALIZADA / KG.FINALIZADA", "");

                    //
                    request.setAttribute("validacion", valida);
                    return "/jsp/gralError.jsp";

                }

                //
                valida.reasignar("FECHA/HORA INICIO", xFechaFin);

                //
                valida.validarCampoString();

                //isValido
                if ((xFechaFin.length() == 4) && (xFechaHoraInicio.length() < 10)) {

                    //
                    request.setAttribute("validacion", valida);
                    return "/jsp/gralError.jsp";

                }
                valida.reasignar("FECHA/HORA INICIO VACIA", xFechaFin);
                if ((xFechaFin.length() == 0)) {

                    //
                    request.setAttribute("validacion", valida);
                    return "/jsp/gralError.jsp";

                }

                //------------------------- traer O.T. -------------------------
                AgendaLogVisitaBean agendaLogVisitaBean = new AgendaLogVisitaBean();

                //
                agendaLogVisitaBean.setIdLog(xIdLog);

                //
                FachadaAgendaLogVisitaBean fachadaAgendaLogVisitaBean
                        = new FachadaAgendaLogVisitaBean();

                //---
                fachadaAgendaLogVisitaBean
                        = agendaLogVisitaBean.listaLogFachada();

                //
                request.setAttribute("fachadaAgendaLogVisitaBean",
                        fachadaAgendaLogVisitaBean);

                //
                FachadaDctoOrdenBean fachadaDctoOrdenBean
                        = new FachadaDctoOrdenBean();

                //
                DctoOrdenBean dctoOrdenBean = new DctoOrdenBean();

                //
                dctoOrdenBean.setIdLog(xIdLog);

                //
                fachadaDctoOrdenBean
                        = dctoOrdenBean.listaDctoOrdenIdLog();

                //
                int xIdOrdenOrigen = fachadaDctoOrdenBean.getIdOrden();
                int xIdLocalOrigen = fachadaDctoOrdenBean.getIdLocal();
                int xIdTipoOrdenOrigen = fachadaDctoOrdenBean.getIdTipoOrden();
                int xNumeroOrden = fachadaDctoOrdenBean.getNumeroOrden();

                //
                int xIdFicha = fachadaDctoOrdenBean.getIdFicha();
                String xIdCliente = fachadaDctoOrdenBean.getIdCliente();
                String xIdTipoTerceroProveedor = "2";

                //
                fachadaDctoOrdenBean.setIdTipoTercero(xIdTipoTerceroProveedor);
                fachadaDctoOrdenBean.setIdOperacion(xIdOperacion);
                fachadaDctoOrdenBean.setItemPadre(xItemPadre);

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaDctoOrdenBean", fachadaDctoOrdenBean);

                //------------------------- validar adicion --------------------
                DctoOrdenProgresoBean dctoOrdenProgresoBean = new DctoOrdenProgresoBean();

                //
                dctoOrdenProgresoBean.setIdLocal(xIdLocal);
                dctoOrdenProgresoBean.setIdTipoOrden(xIdTipoOrden);
                dctoOrdenProgresoBean.setIdOrden(xIdOrdenOrigen);
                dctoOrdenProgresoBean.setIdOperacion(xIdOperacion);
                dctoOrdenProgresoBean.setItemPadre(xItemPadre);

                //--
                double xPorcentajeAdicion = 1.20;

                //
                boolean xOkExcedePeso
                        = dctoOrdenProgresoBean.validaPesoAdicionTerminado(
                                xPorcentajeAdicion,
                                new Double(xPesoTerminado).doubleValue());

                //
                valida.reasignar("EXCEDE EL PESO DEL PEDIDO", xPesoTerminado);

                //xOkExcedePeso
                if (!xOkExcedePeso) {

                    //
                    request.setAttribute("validacion", valida);
                    return "/jsp/gralError.jsp";

                }

                //--------------------------------------------------------------
                String xIdTipoOrdenCotizacion = "59";

                //
                String xIdTipoTerceroCliente = "1";

                //
                FachadaTerceroBean fachadaTerceroBean = new FachadaTerceroBean();

                // 
                ColaboraTercero colaboraTercero = new ColaboraTercero();

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
                FachadaDctoOrdenDetalleBean fachadaDctoOrdenDetalleBean
                        = new FachadaDctoOrdenDetalleBean();

                //
                ColaboraDctoOrdenDetalleBean colaboraDctoOrdenDetalleBean
                        = new ColaboraDctoOrdenDetalleBean();

                //
                colaboraDctoOrdenDetalleBean.setItem(xItemPadre);
                colaboraDctoOrdenDetalleBean.setIdTipoOrden(
                        xIdTipoOrdenCotizacion);

                //
                fachadaDctoOrdenDetalleBean
                        = colaboraDctoOrdenDetalleBean.itemLogFachada(
                                new Integer(xIdLog).intValue());

                //
                Double xCantidad = fachadaDctoOrdenDetalleBean.getCantidad();
                Double xCantidadPerdida = 0.0;

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaDctoOrdenDetalleBean",
                        fachadaDctoOrdenDetalleBean);

                //----------------------- ingresar  progreso -------------------
                FachadaDctoOrdenProgreso fachadaDctoOrdenProgreso
                        = new FachadaDctoOrdenProgreso();

                //
                dctoOrdenProgresoBean.setIdLocal(xIdLocal);
                dctoOrdenProgresoBean.setIdTipoOrden(xIdTipoOrden);
                dctoOrdenProgresoBean.setIdOrden(
                        fachadaDctoOrdenBean.getIdOrden());
                dctoOrdenProgresoBean.setItemPadre(xItemPadre);
                dctoOrdenProgresoBean.setIdOperacion(xIdOperacion);

                //
//                fachadaDctoOrdenProgreso
//                        = dctoOrdenProgresoBean.listaUltimoIngresoFCH();
                //
                String xFechaHoraUltimoRegistro = xFechaFin;

//                //---
//                if (xFechaHoraUltimoRegistro == null) {
//
//                    //
//                    fachadaDctoOrdenProgreso.setFechaHoraInicio(xFechaHoraInicio);
//
//                    //
//                    xFechaHoraUltimoRegistro
//                            = fachadaDctoOrdenProgreso.getFechaHoraInicioJQuery();
//
//                }
                //----------------------- ingresar  documento ------------------
                ColaboraOperacionPlu colaboraOperacionPlu
                        = new ColaboraOperacionPlu();

                //
                colaboraOperacionPlu.setIdFicha(xIdFicha);
                colaboraOperacionPlu.setIdOperacion(xIdOperacion);

                //
                double xTotalMP = colaboraOperacionPlu.totalMP();
                String xObservacion = "";

                //-------------------------- detalle ---------------------------
                ProcesoIngresoProduccion proceso = new ProcesoIngresoProduccion();

                int xIdOrdenCruce
                        = proceso.ingresa(new Integer(xIdLocal).intValue(),
                                xIdTipoOrdenTransformacion,
                                xIdUsuario,
                                xIdFicha,
                                new Integer(xIdOperacion).intValue(),
                                new Double(xPesoTerminado).doubleValue(),
                                new Double(xCantidadTerminada).doubleValue(),
                                xTotalMP,
                                xIdCliente,
                                xIdOrdenOrigen,
                                xIdLocalOrigen,
                                xIdTipoOrdenOrigen,
                                xNumeroOrden,
                                new Integer(xItemPadre).intValue(),
                                xObservacion);

                //--------------------------------------------------------------
                int xMaximoItem = dctoOrdenProgresoBean.maximoItem() + 1;

                //---
                FachadaJobOperacionCosto fachadaJobOperacionCosto
                        = new FachadaJobOperacionCosto();

                //
                JobOperacionCostoBean jobOperacionCostoBean
                        = new JobOperacionCostoBean();

                //---
                jobOperacionCostoBean.setIdLocal(xIdLocal);
                jobOperacionCostoBean.setIdOperacion(xIdOperacion);

                //---
                fachadaJobOperacionCosto
                        = jobOperacionCostoBean.listaCostoFCH();

                //---
                double xVrCostoBaseMAT
                        = fachadaJobOperacionCosto.getVrCostoBaseMAT();
                double xVrCostoBaseMOD
                        = fachadaJobOperacionCosto.getVrCostoBaseMOD();
                double xVrCostoBaseCIF
                        = fachadaJobOperacionCosto.getVrCostoBaseCIF();

                //---
                int xIdTipoCausaProduccion = 0;
                int xIdControlTipoInterna = 0;

                //--------------------------------------------------------------
                dctoOrdenProgresoBean.setItem(xMaximoItem);
                dctoOrdenProgresoBean.setIdOperario(xIdOperario);
                dctoOrdenProgresoBean.setCantidadPerdida(xCantidadPerdida);
                dctoOrdenProgresoBean.setCantidadTerminada(xCantidadTerminada);
                dctoOrdenProgresoBean.setPesoTerminado(xPesoTerminado);
                dctoOrdenProgresoBean.setPesoPerdido(xPesoPerdido);
                dctoOrdenProgresoBean.setIdCausa(xIdCausa);
                dctoOrdenProgresoBean.setIdTipoCausa(xIdTipoCausaProduccion);
                dctoOrdenProgresoBean.setEstado(xEstado);
                dctoOrdenProgresoBean.setItemPadre(xItemPadre);
                dctoOrdenProgresoBean.setVrCostoBaseMAT(xVrCostoBaseMAT);
                dctoOrdenProgresoBean.setVrCostoBaseCIF(xVrCostoBaseCIF);
                dctoOrdenProgresoBean.setVrCostoBaseMOD(xVrCostoBaseMOD);
                dctoOrdenProgresoBean.setPesoTara(xPesoTara);
                dctoOrdenProgresoBean.setFechaHoraInicio(
                        xFechaHoraUltimoRegistro);
                dctoOrdenProgresoBean.setFechaInicio(
                        xFechaHoraUltimoRegistro);
                dctoOrdenProgresoBean.setIdUsuario(xIdUsuario);
                dctoOrdenProgresoBean.setCantidadPendiente(xCantidadPendienteCero);
                dctoOrdenProgresoBean.setObservacion(xObservacionVacia);
                dctoOrdenProgresoBean.setIdOrdenCruce(xIdOrdenCruce);
                dctoOrdenProgresoBean.setIdDctoNitCC(xIdDctoNitCCVacio);
                dctoOrdenProgresoBean.setIdMaquina(Integer.parseInt(idMaquina));
                dctoOrdenProgresoBean.setIdTurno(idTurno);

                if (esSellRef) {
                    
                fechaProduccion = fechaProduccion.length() < 17 ? fechaProduccion + ":00" :fechaProduccion;
                    dctoOrdenProgresoBean.setFechaProduccion(fechaProduccion);
                } else {
                    dctoOrdenProgresoBean.setFechaProduccion("");
                }
                dctoOrdenProgresoBean.ingresaRealTime();

                //--------------------------------------------------------------
                DctoOrdenDetalleBean dctoOrdenDetalleBean = new DctoOrdenDetalleBean();

                //--
                int xIdBodegaSellado = 5;
                dctoOrdenDetalleBean.setIdLocal(xIdLocal);
                dctoOrdenDetalleBean.setIdTipoOrden(xIdTipoOrdenTransformacion);
                dctoOrdenDetalleBean.setIdOrden(xIdOrdenCruce);
                dctoOrdenDetalleBean.setIdBodega(xIdBodegaSellado);

                //                
                dctoOrdenDetalleBean.cambiaCantidadTerminadaSellado();

                //---
                int xIdEscala = 610;
                int xIdBodegaTerminados = 999;

                FachadaJobOperacion fachadaJobOperacion = new FachadaJobOperacion();

                //
                JobOperacionBean jobOperacionBean = new JobOperacionBean();

                //
                jobOperacionBean.setIdOperacion(xIdOperacion);

                //
                fachadaJobOperacion
                        = jobOperacionBean.listaOperacionActualSiguienteFCH(
                                xIdFicha,
                                xIdEscala);

                //
                int xIdBodegaActual = fachadaJobOperacion.getIdOperacion();
                int xIdBodegaSiguiente = fachadaJobOperacion.getIdOperacionSiguiente();

                //
                if (xIdBodegaSiguiente == xIdBodegaTerminados) {

                    //-------------------------- detalle -----------------------
                    xIdOrdenCruce
                            = proceso.ingresa(new Integer(xIdLocal).intValue(),
                                    xIdTipoOrdenTransformacion,
                                    xIdUsuario,
                                    xIdFicha,
                                    new Integer(xIdOperacion).intValue(),
                                    new Double(xPesoTerminado).doubleValue(),
                                    new Double(xCantidadTerminada).doubleValue(),
                                    xTotalMP,
                                    xIdCliente,
                                    xIdOrdenOrigen,
                                    xIdLocalOrigen,
                                    xIdTipoOrdenOrigen,
                                    xNumeroOrden,
                                    new Integer(xItemPadre).intValue(),
                                    xObservacion);

                    //----------------------------------------------------------
                    dctoOrdenDetalleBean.setIdLocal(xIdLocal);
                    dctoOrdenDetalleBean.setIdTipoOrden(xIdTipoOrdenTransformacion);
                    dctoOrdenDetalleBean.setIdOrden(xIdOrdenCruce);
                    dctoOrdenDetalleBean.setIdOperacion(xIdBodegaActual);

                    //
                    dctoOrdenDetalleBean.retiraUltimaOperacion();

                    //----------------------------------------------------------
                    int xMaximoItemTerminados
                            = dctoOrdenProgresoBean.maximoItem() + 1;

                    //---
                    xVrCostoBaseMAT = 0.0;
                    xVrCostoBaseMOD = 0.0;
                    xVrCostoBaseCIF = 0.0;

                    //---
                    xIdTipoCausaProduccion = 0;

                    //----------------------------------------------------------
                    dctoOrdenProgresoBean.setItem(xMaximoItemTerminados);
                    dctoOrdenProgresoBean.setIdOperario(xIdOperario);
                    dctoOrdenProgresoBean.setCantidadPerdida(xCantidadPerdida);
                    dctoOrdenProgresoBean.setCantidadTerminada(xCantidadTerminada);
                    dctoOrdenProgresoBean.setPesoTerminado(xPesoTerminado);
                    dctoOrdenProgresoBean.setPesoPerdido(xPesoPerdido);
                    dctoOrdenProgresoBean.setIdCausa(xIdCausa);
                    dctoOrdenProgresoBean.setIdTipoCausa(xIdTipoCausaProduccion);
                    dctoOrdenProgresoBean.setEstado(xEstado);
                    dctoOrdenProgresoBean.setItemPadre(xItemPadre);
                    dctoOrdenProgresoBean.setVrCostoBaseMAT(xVrCostoBaseMAT);
                    dctoOrdenProgresoBean.setVrCostoBaseCIF(xVrCostoBaseCIF);
                    dctoOrdenProgresoBean.setVrCostoBaseMOD(xVrCostoBaseMOD);
                    dctoOrdenProgresoBean.setPesoTara(xPesoTara);
                    dctoOrdenProgresoBean.setFechaHoraInicio(
                            xFechaHoraUltimoRegistro);
                    dctoOrdenProgresoBean.setFechaInicio(
                            xFechaHoraUltimoRegistro);
                    dctoOrdenProgresoBean.setIdUsuario(xIdUsuario);
                    dctoOrdenProgresoBean.setCantidadPendiente(
                            xCantidadPendienteCero);
                    dctoOrdenProgresoBean.setObservacion(xObservacionVacia);
                    dctoOrdenProgresoBean.setIdOperacion(xIdBodegaTerminados);
                    dctoOrdenProgresoBean.setIdOrdenCruce(xIdOrdenCruce);
                    dctoOrdenProgresoBean.setIdDctoNitCC(xIdDctoNitCCVacio);
                    dctoOrdenProgresoBean.setIdControlTipo(xIdControlTipoInterna);

                    //---
                    dctoOrdenProgresoBean.ingresaRealTime();

                }

                //--------------------------------------------------------------
                //--- actualizaPedidoPendiente ---------------------------------
                dctoOrdenDetalleBean.setIdLocal(xIdLocal);
                dctoOrdenDetalleBean.setIdTipoOrden(xIdTipoOrden);
                dctoOrdenDetalleBean.setIdLog(xIdLog);
                dctoOrdenDetalleBean.setItem(xItemPadre);

                //
                dctoOrdenDetalleBean.actualizaPedidoPendiente();

                // actualizaOT_Almacen
                dctoOrdenDetalleBean.actualizaOT_Almacen();

                //------------------------ pdf documento -----------------------
                GeneraPDFStickerOT generaPDFStickerOT = new GeneraPDFStickerOT();

                //
                generaPDFStickerOT.setIdLocal(xIdLocal);
                generaPDFStickerOT.setIdTipoOrden(xIdTipoOrden);
                generaPDFStickerOT.setIdLog(xIdLog);
                generaPDFStickerOT.setIdOperacion(xIdOperacion);
                generaPDFStickerOT.setItemPadre(xItemPadre);
                generaPDFStickerOT.setItem(xMaximoItem);
                generaPDFStickerOT.setNumeroOrden(xNumeroOrden);

                //
                generaPDFStickerOT.generaPdf(request, response);

                //------------------------ regresar pagina ---------------------
                FichaTecnica fichaTecnica = new FichaTecnica();

                //
                double xPesoPedido = fichaTecnica.pesoPedido(xIdCliente,
                        new Integer(xIdFicha).intValue(),
                        new Double(xCantidad).doubleValue());

                //
                FachadaPluFicha fachadaPluFicha = new FachadaPluFicha();

                //
                ColaboraOrdenTrabajo colaboraOrdenTrabajo = new ColaboraOrdenTrabajo();

                //
                colaboraOrdenTrabajo.setIdFicha(xIdFicha);

                //
                fachadaPluFicha = colaboraOrdenTrabajo.listaIdFichaFCH();

                //
                fachadaPluFicha.setIdOperacion(xIdOperacion);
                fachadaPluFicha.setIdFicha(xIdFicha);
                fachadaPluFicha.setPesoPedido(xPesoPedido);
                fachadaPluFicha.setCantidad(xCantidad);
                fachadaPluFicha.setIdOperacion(xIdOperacion);
                fachadaPluFicha.setIdFicha(xIdFicha);
                fachadaPluFicha.setIdLocal(xIdLocal);
                fachadaPluFicha.setIdTipoOrden(xIdTipoOrden);
                fachadaPluFicha.setFechaPrograma(xFechaVisita);

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaPluFicha", fachadaPluFicha);

                //
                FachadaDctoOrdenProgreso fachadaDctoOrdenConcluida = new FachadaDctoOrdenProgreso();

                //
                fachadaDctoOrdenConcluida
                        = dctoOrdenProgresoBean.listaTotalFCH();

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaDctoOrdenConcluida",
                        fachadaDctoOrdenConcluida);

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                return "/jsp/vtaContenedorOTProductoTouch.jsp";

            }

            //
            if (accionContenedor.compareTo("Retal") == 0) {

                //
                String xIdLocal = request.getParameter("xIdLocal");
                String xIdTipoOrden = request.getParameter("xIdTipoOrden");
                String xIdOperacion = request.getParameter("xIdOperacion");

                //
                FachadaDctoOrdenProgreso fachadaDctoOrdenProgreso
                        = new FachadaDctoOrdenProgreso();

                //
                fachadaDctoOrdenProgreso.setIdLocal(xIdLocal);
                fachadaDctoOrdenProgreso.setIdTipoOrden(xIdTipoOrden);
                fachadaDctoOrdenProgreso.setIdOperacion(xIdOperacion);
                fachadaDctoOrdenProgreso.setFechaFin(xFechaVisita);

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaDctoOrdenProgreso",
                        fachadaDctoOrdenProgreso);

                return "vista/Touch/VistaProduccionTouchRetal.jsp";

                //---------------------------------------------------------------
            }
            if (accionContenedor.compareTo("Paros") == 0) {

                //
                String xIdLocal = request.getParameter("xIdLocal");
                String xIdTipoOrden = request.getParameter("xIdTipoOrden");
                String xIdOperacion = request.getParameter("xIdOperacion");

                //
                FachadaDctoOrdenProgreso fachadaDctoOrdenProgreso
                        = new FachadaDctoOrdenProgreso();

                //
                fachadaDctoOrdenProgreso.setIdLocal(xIdLocal);
                fachadaDctoOrdenProgreso.setIdTipoOrden(xIdTipoOrden);
                fachadaDctoOrdenProgreso.setIdOperacion(xIdOperacion);
                fachadaDctoOrdenProgreso.setFechaFin(xFechaVisita);

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaDctoOrdenProgreso",
                        fachadaDctoOrdenProgreso);
                return "vista/Touch/VistaProduccionTouchParo.jsp";

                //---------------------------------------------------------------
            }

            //
            if (accionContenedor.compareTo("Producir") == 0) {

                //
                String xIdLocal = request.getParameter("xIdLocal");
                String xIdTipoOrden = request.getParameter("xIdTipoOrden");
                String xIdOperacion = request.getParameter("xIdOperacion");
                String xIdLogItemPadre
                        = request.getParameter("xIdLogItemPadre");

                //--
                if (xIdLogItemPadre == null) {

                    //
                    Validacion valida = new Validacion();

                    //
                    valida.reasignar("O.T. NO EXISTE O SIN SELECCIONAR", "");

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
                int xLongitud = xIdLogItemPadre.length();
                int xUno = xIdLogItemPadre.indexOf('~');

                //
                String xIdLog = xIdLogItemPadre.substring(0, xUno);
                String xItemPadre = xIdLogItemPadre.substring(xUno + 1, xLongitud);

                //---------------------------------------------------------------
                AgendaLogVisitaBean agendaLogVisitaBean = new AgendaLogVisitaBean();

                //
                agendaLogVisitaBean.setIdLog(xIdLog);

                //
                FachadaAgendaLogVisitaBean fachadaAgendaLogVisitaBean = new FachadaAgendaLogVisitaBean();

                //
                fachadaAgendaLogVisitaBean
                        = agendaLogVisitaBean.listaLogFachada();

                //
                request.setAttribute("fachadaAgendaLogVisitaBean",
                        fachadaAgendaLogVisitaBean);

                //
                FachadaDctoOrdenBean fachadaDctoOrdenBean
                        = new FachadaDctoOrdenBean();

                //
                DctoOrdenBean dctoOrdenBean = new DctoOrdenBean();

                //
                dctoOrdenBean.setIdLog(xIdLog);

                //
                fachadaDctoOrdenBean
                        = dctoOrdenBean.listaDctoOrdenIdLog();

                //
                int xIdFicha = fachadaDctoOrdenBean.getIdFicha();
                String xIdCliente = fachadaDctoOrdenBean.getIdCliente();
                String xIdTipoTerceroProveedor = "2";
                int xNumeroOrden = fachadaDctoOrdenBean.getNumeroOrden();

                //
                fachadaDctoOrdenBean.setIdTipoTercero(xIdTipoTerceroProveedor);
                fachadaDctoOrdenBean.setIdOperacion(xIdOperacion);
                fachadaDctoOrdenBean.setItemPadre(xItemPadre);

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
                ColaboraDctoOrdenDetalleBean colaboraDctoOrdenDetalleBean
                        = new ColaboraDctoOrdenDetalleBean();

                //
                colaboraDctoOrdenDetalleBean.setItem(xItemPadre);
                colaboraDctoOrdenDetalleBean.setIdTipoOrden(
                        xIdTipoOrdenCotizacion);

                //
                FachadaDctoOrdenDetalleBean fachadaDctoOrdenDetalleBean
                        = new FachadaDctoOrdenDetalleBean();

                //
                fachadaDctoOrdenDetalleBean
                        = colaboraDctoOrdenDetalleBean.itemLogFachada(
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

                //
                FachadaPluFicha fachadaPluFicha = new FachadaPluFicha();

                //
                ColaboraOrdenTrabajo colaboraOrdenTrabajo = new ColaboraOrdenTrabajo();

                //
                colaboraOrdenTrabajo.setIdFicha(xIdFicha);

                //
                fachadaPluFicha = colaboraOrdenTrabajo.listaIdFichaFCH();

                //
                fachadaPluFicha.setIdOperacion(xIdOperacion);
                fachadaPluFicha.setIdFicha(xIdFicha);
                fachadaPluFicha.setPesoPedido(xPesoPedido);
                fachadaPluFicha.setCantidad(xCantidad);
                fachadaPluFicha.setFechaPrograma(xFechaVisita);
                fachadaPluFicha.setIdLocal(xIdLocal);
                fachadaPluFicha.setIdTipoOrden(xIdTipoOrden);
                fachadaPluFicha.setIdLog(xIdLog);
                fachadaPluFicha.setItemPadre(xItemPadre);

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaPluFicha", fachadaPluFicha);

                //
                FachadaPluFicha fachadaPluFichaAcumula = new FachadaPluFicha();

                //
                colaboraOrdenTrabajo.setIdLocal(xIdLocal);
                colaboraOrdenTrabajo.setIdTipoOrden(xIdTipoOrden);
                colaboraOrdenTrabajo.setIdFicha(xIdFicha);
                colaboraOrdenTrabajo.setNumeroOrden(xNumeroOrden);
                colaboraOrdenTrabajo.setItemPadre(xItemPadre);
                colaboraOrdenTrabajo.setIdOperacion(xIdOperacion);
                colaboraOrdenTrabajo.setFechaPrograma(xFechaVisita);

                //
                fachadaPluFichaAcumula = colaboraOrdenTrabajo.listaOTProgramaTouchFCH();

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaPluFichaAcumula",
                        fachadaPluFichaAcumula);

                //---
                DctoOrdenProgresoBean dctoOrdenProgresoBean
                        = new DctoOrdenProgresoBean();

                //
                dctoOrdenProgresoBean.setIdLocal(xIdLocal);
                dctoOrdenProgresoBean.setIdTipoOrden(xIdTipoOrden);
                dctoOrdenProgresoBean.setIdOrden(
                        fachadaDctoOrdenBean.getIdOrden());
                dctoOrdenProgresoBean.setIdOperacion(xIdOperacion);
                dctoOrdenProgresoBean.setItemPadre(xItemPadre);

                //
                FachadaDctoOrdenProgreso fachadaDctoOrdenProgreso = new FachadaDctoOrdenProgreso();

                //
                fachadaDctoOrdenProgreso
                        = dctoOrdenProgresoBean.listaUltimoIngresoFCH();

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaDctoOrdenProgreso",
                        fachadaDctoOrdenProgreso);
                //
                return "/jsp/vtaFrmIngOTProductoTouch.jsp";

                //---------------------------------------------------------------
            }

            //
            if (accionContenedor.compareTo("inicia") == 0) {

                //
                String xIdLocal = request.getParameter("xIdLocal");
                String xIdTipoOrden = request.getParameter("xIdTipoOrden");
                String xIdOperacion = request.getParameter("xIdOperacion");

                //
                FachadaPluFicha fachadaPluFicha = new FachadaPluFicha();

                //
                fachadaPluFicha.setIdLocal(xIdLocal);
                fachadaPluFicha.setIdTipoOrden(xIdTipoOrden);
                fachadaPluFicha.setFechaPrograma(xFechaVisita);
                fachadaPluFicha.setIdOperacion(xIdOperacion);

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaPluFicha", fachadaPluFicha);

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                return "/jsp/vtaContenedorOTProductoTouch.jsp";
            }
        }

        //
        return "/jsp/empty.htm";
    }
}
