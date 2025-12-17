package com.solucionesweb.losservlets;

// Importa los paquetes del lenguaje especificamente io y los
// que permiten trabajar el servlet.
import com.solucionesweb.lasayudas.ProcesoGuardaOTProgreso;
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
import com.solucionesweb.lasayudas.ProcesoGuardaPluServicio;
import com.solucionesweb.lasayudas.ProcesoGuardaSuministro;
import com.solucionesweb.lasayudas.ProcesoIngresoSuministroOLD;
import com.solucionesweb.losbeans.colaboraciones.ColaboraDctoOrdenBean;
import com.solucionesweb.losbeans.colaboraciones.ColaboraResurtidoOrden;

// Importa la clase que contiene el TerceroBean
import com.solucionesweb.losbeans.negocio.TerceroBean;

import com.solucionesweb.losbeans.fachada.FachadaTerceroBean;

import com.solucionesweb.losbeans.colaboraciones.ColaboraOrdenDetalleBean;

// Importa el Bean de Fachada
import com.solucionesweb.losbeans.colaboraciones.ColaboraOrdenTrabajo;
import com.solucionesweb.losbeans.colaboraciones.ColaboraPlu;
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
import com.solucionesweb.losbeans.fachada.FachadaJobOperacion;
import com.solucionesweb.losbeans.fachada.FachadaPluBean;
import com.solucionesweb.losbeans.fachada.FachadaPluFicha;
import com.solucionesweb.losbeans.negocio.DctoOrdenBean;
import com.solucionesweb.losbeans.negocio.JobOperacionBean;
import com.solucionesweb.losbeans.negocio.PluBean;
import com.solucionesweb.losbeans.negocio.PluFichaBean;
import com.solucionesweb.losbeans.utilidades.FichaTecnica;
/**
 * Esta ventana permite crear una orden de servicio, donde se remite un producto
 * para ser procesado o terminado por un tercero /
 * vtaContenedorServicio.jsp /
 * 
 * Este servlet implementa la interface GralManejadorRequest /
 */
// 
public class VtasAdmServicioManejadorRequest implements GralManejadorRequest {
    /**
     * BUTTON--
     * ("Crear")-Permite elaborar una orden de compra/
     * ("Traer")-Permite ver ordenes creadas /
     * ("Listar")-Permite ver un listado de ordenes de compra /
     * ("Regresar")-("Salir")-Permite retornar al menu principal /
     * ("Imprimir")-genera un pdf del proceso /
     * ("Liquidar")-calcula las cantidades que se ingresa /
     * ("Legalizar")-Finaliza proceso /
     * ("Eliminar")-Borrar proceso /
     * ("Guardar")-guarda proceso /
     * ("iniciar")-regresa a la interfaz de legaliza suministro para iniciarproceso con otro proveedor /
     * 
     * Metodo contructor por defecto, es decir, sin parametros /
     */

    public VtasAdmServicioManejadorRequest() {
    }
    /**
     * BUTTON PARAMETER--
     * "Proveedor"-Selecciona proveedor /
     * "Operacion"-Selecciona operacion /
     * "O.T."-Ingrese numero de orden de trabajo /
     * "Fecha Orden"-Ingrese fecha de orden  /
     * "Can.Rec"-ingreso cantidad recibida /
     * "Vr.Base"-Ingreso valor base /
     * "Vr.Iva"-Ingreso iva /
     * "O.T"-Ingreso orden de trabajo /
     * "Operacion"-seleccione operacion /
     * "Fecha Recibo"-Ingreso fecha de recibo /
     * "#Factura"-Ingrese numero de factura /
     * "Vr.Impoco"-Ingreso valor /
     * "Vr.Dscto"-Ingrese valor descuento si lo hay /
     * "Vr.Rfte"-ingrese valor retencion fuente /
     */

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
        int xIdTipoOrdenTrabajoProceso = 59;


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

                //---
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
                FachadaColaboraDctoOrdenBean fachadaColaboraDctoOrdenBean
                        = new FachadaColaboraDctoOrdenBean();

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
                return "/jsp/vtaFrmLegServicio.jsp";

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
                return "/jsp/vtaContenedorServicio.jsp";

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
                    return "/jsp/vtaContenedorServicio.jsp";

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
                return "/jsp/vtaFrmLegServicio.jsp";

            }

            // +O.T.
            if (accionContenedor.compareTo("O.T.") == 0) {

                //
                String xIdLocal = request.getParameter("xIdLocal");
                String xIdTipoOrden = request.getParameter("xIdTipoOrden");
                String xIdOrden = request.getParameter("xIdOrden");
                String xIdLog = request.getParameter("xIdLog");
                String xIdOperacionStr = request.getParameter("xIdOperacion");
                String xNumeroOrdenStr = request.getParameter("xNumeroOrden");

                // validacion
                Validacion validacion = new Validacion();

                //
                validacion.reasignar("OPERACION", xIdOperacionStr);

                //
                validacion.validarCampoEntero();

                // isValido
                if (!validacion.isValido()) {

                    //
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralError.jsp";

                }

                //
                validacion.reasignar("O.T.", xNumeroOrdenStr);

                //
                validacion.validarCampoDouble();

                // isValido
                if (!validacion.isValido()) {

                    //
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralError.jsp";

                }

                //--
                ColaboraDctoOrdenBean colaboraDctoOrdenBean = new ColaboraDctoOrdenBean();

                //
                FachadaColaboraDctoOrdenBean fachadaColaboraDctoOrdenBean = new FachadaColaboraDctoOrdenBean();

                //
                ColaboraResurtidoOrden colaboraResurtidoOrden = new ColaboraResurtidoOrden();

                //
                colaboraDctoOrdenBean.setIdLocal(xIdLocal);
                colaboraDctoOrdenBean.setIdTipoOrden(xIdTipoOrdenTrabajoProceso);
                colaboraDctoOrdenBean.setNumeroOrden(xNumeroOrdenStr);

                //
                fachadaColaboraDctoOrdenBean = colaboraDctoOrdenBean.listaOTFCH();

                //
                validacion.reasignar("O.T.", xNumeroOrdenStr);

                //
                if (fachadaColaboraDctoOrdenBean.getNumeroOrden() == 0) {

                    //
                    validacion.setDescripcionError("NO EXISTE LA O.T. #" + xNumeroOrdenStr);

                    //
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralError.jsp";

                }

                //
                String xIdPlu = fachadaColaboraDctoOrdenBean.getIdPluStr();
                String xCantidadPedida = "0.0";
                String xCostoPedido = "0.0";
                String xIdCliente = fachadaColaboraDctoOrdenBean.getIdCliente();
                String xFechaCorte = fachadaColaboraDctoOrdenBean.getFechaOrdenCorta();
                String xDiasHistoria = fachadaColaboraDctoOrdenBean.getDiasHistoriaStr();
                String xDiasInventario = fachadaColaboraDctoOrdenBean.getDiasInventarioStr();
                int xNumeroOrden = fachadaColaboraDctoOrdenBean.getNumeroOrden();
                int xIdOperacion = new Integer(xIdOperacionStr).intValue();

                //---
                xIdLogActual = new Integer(xIdLog).intValue();

                //---
                ProcesoGuardaSuministro procesoGuardaSuministro = new ProcesoGuardaSuministro();

                //
                procesoGuardaSuministro.guarda(xIdLogActual,
                        xIdPlu,
                        xCantidadPedida,
                        xCostoPedido,
                        xIdTipoOrdenCompraProceso,
                        xIdUsuario,
                        xIdLocalUsuario,
                        xIdCliente,
                        xFechaCorte,
                        xDiasHistoria,
                        xDiasInventario,
                        xNumeroOrden,
                        xIdOperacion);

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
                fachadaColaboraDctoOrdenBean = colaboraResurtidoOrden.listaLegalizaCompraFCH();

                //
                fachadaColaboraDctoOrdenBean.setFechaEntrega(strFechaVisita);
                fachadaColaboraDctoOrdenBean.setFechaOrden(strFechaVisita);
                fachadaColaboraDctoOrdenBean.setIdDctoNitCC("");

                //
                request.setAttribute("fachadaColaboraDctoOrdenBean",
                        fachadaColaboraDctoOrdenBean);

                //
                return "/jsp/vtaFrmLegServicio.jsp";

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
                fachadaDctoOrdenBean.setIdTipoOrden(xIdTipoOrdenCompra);
                fachadaDctoOrdenBean.setIdUsuario(xIdUsuario);
                fachadaDctoOrdenBean.setIdLog(xIdLogActual);

                //
                request.setAttribute("fachadaDctoOrdenBean",
                        fachadaDctoOrdenBean);

                //
                return "/jsp/vtaFrmConServicio.jsp";

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
                String xVrSubtotalArr[] =
                        request.getParameterValues("xVrSubtotalArr");
                String xVrIvaResurtidoArr[] =
                        request.getParameterValues("xVrIvaResurtidoArr");
                String xVrImpoconsumoArr[] =
                        request.getParameterValues("xVrImpoconsumoArr");
                String xNumeroOrdenArr[] =
                        request.getParameterValues("xNumeroOrdenArr");
                String xIdOperacionArr[] =
                        request.getParameterValues("xIdOperacionArr");



                //--------------------------------------------------------------
                String xVrBase = request.getParameter("xVrBase");
                String xVrImpoconsumo =
                        request.getParameter("xVrImpoconsumo");
                String xVrDescuento =
                        request.getParameter("xVrDescuento");
                String xVrIva = request.getParameter("xVrIva");
                String xVrRteFuente = request.getParameter("xVrRteFuente");

                String xVrPagarDctoNitCC =
                        request.getParameter("xVrPagarDctoNitCC");
                String xFechaDctoNitCC =
                        request.getParameter("xFechaDctoNitCC");
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
                valida.reasignar("VR.IMPOCONSUMO", xVrImpoconsumo);

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

                    //
                    valida.reasignar("VR.BASE", xVrSubtotalArr[indice]);

                    //
                    valida.validarCampoDoublePositivo();

                    //isValido
                    if (!valida.isValido()) {

                        //
                        request.setAttribute("validacion", valida);
                        return "/jsp/gralError.jsp";
                    }

                    //
                    valida.reasignar("V.IMPOCO", xVrImpoconsumoArr[indice]);

                    //
                    valida.validarCampoDoublePositivo();

                    //isValido
                    if (!valida.isValido()) {

                        //
                        request.setAttribute("validacion", valida);
                        return "/jsp/gralError.jsp";
                    }

                    //
                    valida.reasignar("VR.IVA", xVrIvaResurtidoArr[indice]);

                    //
                    valida.validarCampoDoublePositivo();

                    //isValido
                    if (!valida.isValido()) {

                        //
                        request.setAttribute("validacion", valida);
                        return "/jsp/gralError.jsp";
                    }

                    //
                    valida.reasignar("O.T.", xNumeroOrdenArr[indice]);

                    //
                    valida.validarCampoDoublePositivo();

                    //isValido
                    if (!valida.isValido()) {

                        //
                        request.setAttribute("validacion", valida);
                        return "/jsp/gralError.jsp";
                    }

                    //
                    valida.reasignar("OPERACION", xIdOperacionArr[indice]);

                    //
                    valida.validarCampoDoublePositivo();

                    //isValido
                    if (!valida.isValido()) {

                        //
                        request.setAttribute("validacion", valida);
                        return "/jsp/gralError.jsp";
                    }

                }

                //--------------------------------------------------------------
                FachadaJobOperacion fachadaJobOperacion = new FachadaJobOperacion();

                //
                JobOperacionBean jobOperacionBean = new JobOperacionBean();

                //---
                for (int indice = 0; indice < xCantidadArr.length; indice++) {

                    //
                    jobOperacionBean.setIdOperacion(
                            xIdOperacionArr[indice].trim());

                    //
                    fachadaJobOperacion =
                            jobOperacionBean.listaUnaOperacionFCH();

                    //
                    valida.reasignar("OPERACION", xIdOperacionArr[indice]);

                    //isValido
                    if (fachadaJobOperacion.getIdOperacion() < 1) {

                        //
                        valida.setCodigoError("ERROR, LA OPERACION NO EXISTE");
                        valida.setSolucion("CORREGIR CODIGO OPERACION");

                        //
                        request.setAttribute("validacion", valida);
                        return "/jsp/gralError.jsp";

                    }


                }

                //--------------------------------------------------------------
                int xItemBase = 1;

                //
                FachadaDctoOrdenDetalleBean fachadaDctoOrdenDetalleBean =
                        new FachadaDctoOrdenDetalleBean();

                //
                ColaboraOrdenDetalleBean colaboraOrdenDetalleBean =
                        new ColaboraOrdenDetalleBean();

                //---
                for (int indice = 0; indice < xCantidadArr.length; indice++) {

                    //
                    colaboraOrdenDetalleBean.setIdLocal(xIdLocal);
                    colaboraOrdenDetalleBean.setIdTipoOrden(
                            xIdTipoOrdenTrabajoProceso);
                    colaboraOrdenDetalleBean.setNumeroOrden(
                            xNumeroOrdenArr[indice].trim());
                    colaboraOrdenDetalleBean.setItem(xItemBase);

                    //
                    fachadaDctoOrdenDetalleBean =
                            colaboraOrdenDetalleBean.listaOTSumnistroFCH();

                    //
                    valida.reasignar("O.T.", xNumeroOrdenArr[indice]);

                    //isValido
                    if (fachadaDctoOrdenDetalleBean.getIdOrden() < 1) {

                        //
                        valida.setCodigoError("ERROR, LA O.T. NO EXISTE");
                        valida.setSolucion("CORREGIR CODIGO O.T.");

                        //
                        request.setAttribute("validacion", valida);
                        return "/jsp/gralError.jsp";

                    }
                }

                //--------------------------------------------------------------
                DctoOrdenDetalleBean dctoOrdenDetalleBean = new DctoOrdenDetalleBean();

                //
                dctoOrdenDetalleBean.borraResurtidoCompra();

                //
                for (int indice = 0; indice < xCantidadArr.length; indice++) {

                    //
                    colaboraOrdenDetalleBean.setIdLocal(xIdLocal);
                    colaboraOrdenDetalleBean.setIdTipoOrden(
                            xIdTipoOrdenTrabajoProceso);
                    colaboraOrdenDetalleBean.setNumeroOrden(
                            xNumeroOrdenArr[indice].trim());
                    colaboraOrdenDetalleBean.setItem(xItemBase);

                    //
                    fachadaDctoOrdenDetalleBean =
                            colaboraOrdenDetalleBean.listaOTSumnistroFCH();

                    //----------------------------------------------------------
                    dctoOrdenDetalleBean.setIdLocal(xIdLocal);
                    dctoOrdenDetalleBean.setIdTipoOrden(xIdTipoOrden);
                    dctoOrdenDetalleBean.setIdLog(xIdLog);
                    dctoOrdenDetalleBean.setCantidad(xCantidadArr[indice]);
                    dctoOrdenDetalleBean.setIdPlu(xIdPluArr[indice]);
                    dctoOrdenDetalleBean.setVrCostoResurtido(
                            new Double(xVrSubtotalArr[indice]).doubleValue()
                            + new Double(xVrIvaResurtidoArr[indice]).doubleValue()
                            + new Double(xVrImpoconsumoArr[indice]).doubleValue());
                    dctoOrdenDetalleBean.setVrIvaResurtido(
                            xVrIvaResurtidoArr[indice]);
                    dctoOrdenDetalleBean.setVrImpoconsumo(
                            xVrImpoconsumoArr[indice]);
                    dctoOrdenDetalleBean.setNumeroOrden(
                            xNumeroOrdenArr[indice].trim());
                    dctoOrdenDetalleBean.setItemOrden(xItemBase);
                    dctoOrdenDetalleBean.setIdOperacion(
                            xIdOperacionArr[indice].trim());

                    //---
                    dctoOrdenDetalleBean.setIdLocalOrigen(
                            fachadaDctoOrdenDetalleBean.getIdLocal());
                    dctoOrdenDetalleBean.setIdOrdenOrigen(
                            fachadaDctoOrdenDetalleBean.getIdOrden());
                    dctoOrdenDetalleBean.setIdTipoOrdenOrigen(
                            fachadaDctoOrdenDetalleBean.getIdTipoOrden());

                    //
                    if (dctoOrdenDetalleBean.getCantidad() != 0) {

                        //
                        dctoOrdenDetalleBean.actualizaSuministro();

                    }
                }

                //--------------------------------------------------------------
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


                //--------------------------------------------------------------
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
                String xIdTercero = fachadaTerceroBean.getIdCliente();

                //
                ColaboraResurtidoOrden colaboraResurtidoOrden = new ColaboraResurtidoOrden();

                //
                colaboraResurtidoOrden.setIdLocal(xIdLocal);
                colaboraResurtidoOrden.setIdTipoOrden(xIdTipoOrden);
                colaboraResurtidoOrden.setIdLog(xIdLog);

                //
                FachadaColaboraDctoOrdenBean fachadaColaboraProveedorBean = new FachadaColaboraDctoOrdenBean();

                //
                FachadaDctoOrdenDetalleBean fachadaDctoOrdenProveedorBean = new FachadaDctoOrdenDetalleBean();

                //
                int xIdConceptoRFCompra = 1;

                //
                colaboraOrdenDetalleBean.setIdLocal(xIdLocal);
                colaboraOrdenDetalleBean.setIdTipoOrden(xIdTipoOrden);
                colaboraOrdenDetalleBean.setIdLog(xIdLog);

                //
                fachadaDctoOrdenProveedorBean = colaboraOrdenDetalleBean.liquidaOrdenProveedorFCH();

                //--------------------------------------------------------------
                ContableRetencionBean contableRetencionBean = new ContableRetencionBean();

                //
                int xIdRteFuenteVrBase = 0;

                //
                double xVrRetencion =
                        contableRetencionBean.calculaRetencion(
                        fachadaTerceroBean.getIdAutoRetenedor(),
                        xIdConceptoRFCompra,
                        fachadaDctoOrdenProveedorBean.getVrCostoResurtido()
                        - fachadaDctoOrdenProveedorBean.getVrIvaResurtido()
                        - fachadaDctoOrdenProveedorBean.getVrImpoconsumo(),
                        xIdRteFuenteVrBase);

                //
                fachadaColaboraProveedorBean.setVrCostoSinIva(
                        fachadaDctoOrdenProveedorBean.getVrCostoResurtido());
                fachadaColaboraProveedorBean.setVrImpoconsumo(
                        fachadaDctoOrdenProveedorBean.getVrImpoconsumo());
                fachadaColaboraProveedorBean.setVrCostoRteFuente(xVrRetencion);
                fachadaColaboraProveedorBean.setVrCostoIva(
                        fachadaDctoOrdenProveedorBean.getVrIvaResurtido());
                fachadaColaboraProveedorBean.setFechaEntrega(xFechaDctoNitCC);
                fachadaColaboraProveedorBean.setFechaOrden(xFechaDcto);
                fachadaColaboraProveedorBean.setIdDctoNitCC(xIdDctoNitCC);

                //
                FachadaColaboraDctoOrdenBean fachadaColaboraDctoOrdenBean = new FachadaColaboraDctoOrdenBean();

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
                fachadaColaboraDctoOrdenBean.setVrCostoRteFuente(xVrRteFuente);
                fachadaColaboraDctoOrdenBean.setVrCostoIva(xVrIva);
                fachadaColaboraDctoOrdenBean.setIdDctoNitCC(xIdDctoNitCC);

                //--------------------------------------------------------------
                fachadaColaboraDctoOrdenBean.setVrDiferencia(
                        fachadaColaboraDctoOrdenBean.getVrPagarFactura()
                        - fachadaColaboraProveedorBean.getVrPagarFactura());


                // mayor 1 o menor -1
                if ((((((int) fachadaColaboraDctoOrdenBean.getVrCostoSinIva()
                        + (int) fachadaColaboraDctoOrdenBean.getVrImpoconsumo()
                        + (int) fachadaColaboraDctoOrdenBean.getVrCostoIva()
                        - (int) fachadaColaboraDctoOrdenBean.getVrCostoRteFuente())
                        - ((int) fachadaColaboraProveedorBean.getVrCostoSinIva()
                        - (int) fachadaColaboraProveedorBean.getVrCostoRteFuente()))) > 1)
                        || (((((int) fachadaColaboraDctoOrdenBean.getVrCostoSinIva()
                        + (int) fachadaColaboraDctoOrdenBean.getVrImpoconsumo()
                        + (int) fachadaColaboraDctoOrdenBean.getVrCostoIva()
                        - (int) fachadaColaboraDctoOrdenBean.getVrCostoRteFuente())
                        - ((int) fachadaColaboraProveedorBean.getVrCostoSinIva()
                        - (int) fachadaColaboraProveedorBean.getVrCostoRteFuente()))) < -1)) {

                    //
                    valida.reasignar("ERROR, LIQUIDACION INCORRECTA DE FACTURA", "");

                    //
                    request.setAttribute("validacion", valida);
                    return "/jsp/gralError.jsp";

                }

                // confirmaPedido
                ProcesoIngresoSuministroOLD proceso = new ProcesoIngresoSuministroOLD();

                //
                fachadaDctoBean = proceso.ingresa(
                        new Integer(xIdLocal).intValue(),
                        xIdTipoOrdenCompra,
                        new Integer(xIdLog).intValue(),
                        xIdTipoOrdenCompraProceso,
                        fachadaColaboraProveedorBean);

                //--------------------------------------------------------------
                ProcesoGuardaOTProgreso procesoGuardaOTProgreso = new ProcesoGuardaOTProgreso();

                //--
                int xIdCausa = 0;
                double xPesoTara = 0.0;
                double xPesoPerdido = 0.0;
                int xIdOperacion = 0;
                double xIdOperario = 0.0;
                double xCantidadTerminada = 0.0;
                double xPesoTerminado = 0.0;

                //--
                for (int indice = 0; indice < xCantidadArr.length; indice++) {

                    //--
                    colaboraOrdenDetalleBean.setIdLocal(xIdLocal);
                    colaboraOrdenDetalleBean.setIdTipoOrden(
                            xIdTipoOrdenTrabajoProceso);
                    colaboraOrdenDetalleBean.setNumeroOrden(
                            xNumeroOrdenArr[indice].trim());
                    colaboraOrdenDetalleBean.setItem(xItemBase);

                    //
                    fachadaDctoOrdenDetalleBean =
                            colaboraOrdenDetalleBean.listaOTSumnistroFCH();

                    //---
                    xIdOperacion = new Integer(xIdOperacionArr[indice]).intValue();
                    xIdOperario = new Double(xIdTercero).doubleValue();
                    xCantidadTerminada = new Double(xCantidadArr[indice]).doubleValue();
                    xPesoTerminado = new Double(xCantidadArr[indice]).doubleValue();
                    ;


                    //
                    if (xPesoTerminado != 0) {

                        //--
                        procesoGuardaOTProgreso.guarda_OS(
                                fachadaDctoOrdenDetalleBean.getIdLocal(),
                                fachadaDctoOrdenDetalleBean.getIdTipoOrden(),
                                fachadaDctoOrdenDetalleBean.getIdOrden(),
                                xItemBase,
                                xIdOperacion,
                                xIdOperario,
                                xCantidadTerminada,
                                xPesoTerminado,
                                xPesoPerdido,
                                xIdCausa,
                                xPesoTara);

                    }
                }

                //--------------------------------------------------------------
                int xIdEstadoTxSinTx = 1;
                int tareaVisita = 6;
                int estadoTerminado = 1;

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

                //
                GeneraPDFSuministroLegalizado generaPDF = new GeneraPDFSuministroLegalizado();

                //
                generaPDF.setIdLocal(xIdLocalUsuario);
                generaPDF.setIdOrden(fachadaDctoBean.getIdOrden());
                generaPDF.setIdTipoOrden(fachadaDctoBean.getIdTipoOrden());
                generaPDF.setTituloReporte("COMPRA CUMPLIDA");
                generaPDF.setReporteName("RepEmpresaSumnistroLegalizado");

                //
                generaPDF.generaPdf(request, response);

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
                String xVrSubtotalArr[] =
                        request.getParameterValues("xVrSubtotalArr");
                String xPorcentajeIvaArr[] =
                        request.getParameterValues("xPorcentajeIvaArr");
                String xVrIvaResurtidoArr[] =
                        request.getParameterValues("xVrIvaResurtidoArr");
                String xVrImpoconsumoArr[] =
                        request.getParameterValues("xVrImpoconsumoArr");
                String xNumeroOrdenArr[] =
                        request.getParameterValues("xNumeroOrdenArr");
                String xIdOperacionArr[] =
                        request.getParameterValues("xIdOperacionArr");

                //--------------------------------------------------------------
                String xVrBase = request.getParameter("xVrBase");
                String xVrImpoconsumo =
                        request.getParameter("xVrImpoconsumo");
                String xVrDescuento =
                        request.getParameter("xVrDescuento");
                String xVrIva = request.getParameter("xVrIva");
                String xVrRteFuente = request.getParameter("xVrRteFuente");

                String xVrPagarDctoNitCC =
                        request.getParameter("xVrPagarDctoNitCC");
                String xFechaDctoNitCC =
                        request.getParameter("xFechaDctoNitCC");
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

                    //
                    valida.reasignar("VR.BASE", xVrSubtotalArr[indice]);

                    //
                    valida.validarCampoDoublePositivo();

                    //isValido
                    if (!valida.isValido()) {

                        //
                        request.setAttribute("validacion", valida);
                        return "/jsp/gralError.jsp";
                    }

                    //
                    valida.reasignar("%IVA", xPorcentajeIvaArr[indice]);

                    //
                    valida.validarCampoDoublePositivo();

                    //isValido
                    if (!valida.isValido()) {

                        //
                        request.setAttribute("validacion", valida);
                        return "/jsp/gralError.jsp";
                    }

                    //
                    valida.reasignar("VR.IVA", xVrIvaResurtidoArr[indice]);

                    //
                    valida.validarCampoDoublePositivo();

                    //isValido
                    if (!valida.isValido()) {

                        //
                        request.setAttribute("validacion", valida);
                        return "/jsp/gralError.jsp";
                    }

                    //
                    valida.reasignar("O.T.", xNumeroOrdenArr[indice]);

                    //
                    valida.validarCampoEntero();

                    //isValido
                    if (!valida.isValido()) {

                        //
                        request.setAttribute("validacion", valida);
                        return "/jsp/gralError.jsp";
                    }

                    //
                    valida.reasignar("OPERACION", xIdOperacionArr[indice]);

                    //
                    valida.validarCampoDoublePositivo();

                    //isValido
                    if (!valida.isValido()) {

                        //
                        request.setAttribute("validacion", valida);
                        return "/jsp/gralError.jsp";
                    }
                }

                //--------------------------------------------------------------
                FachadaJobOperacion fachadaJobOperacion = new FachadaJobOperacion();

                //
                JobOperacionBean jobOperacionBean = new JobOperacionBean();

                //------------------
                for (int indice = 0; indice < xCantidadArr.length; indice++) {

                    //
                    jobOperacionBean.setIdOperacion(
                            xIdOperacionArr[indice].trim());

                    //
                    fachadaJobOperacion =
                            jobOperacionBean.listaUnaOperacionFCH();

                    //
                    valida.reasignar("OPERACION", xIdOperacionArr[indice]);

                    //isValido
                    if (fachadaJobOperacion.getIdOperacion() < 1) {

                        //
                        valida.setCodigoError("ERROR, LA OPERACION NO EXISTE");
                        valida.setSolucion("CORREGIR CODIGO OPERACION");

                        //
                        request.setAttribute("validacion", valida);
                        return "/jsp/gralError.jsp";

                    }
                }

                //--------------------------------------------------------------
                int xItemBase = 1;

                //
                FachadaDctoOrdenDetalleBean fachadaDctoOrdenDetalleBean =
                        new FachadaDctoOrdenDetalleBean();

                //
                ColaboraOrdenDetalleBean colaboraOrdenDetalleBean =
                        new ColaboraOrdenDetalleBean();

                //------------------
                for (int indice = 0; indice < xCantidadArr.length; indice++) {

                    //
                    colaboraOrdenDetalleBean.setIdLocal(xIdLocal);
                    colaboraOrdenDetalleBean.setIdTipoOrden(
                            xIdTipoOrdenTrabajoProceso);
                    colaboraOrdenDetalleBean.setNumeroOrden(
                            xNumeroOrdenArr[indice].trim());
                    colaboraOrdenDetalleBean.setItem(xItemBase);

                    //
                    fachadaDctoOrdenDetalleBean =
                            colaboraOrdenDetalleBean.listaOTSumnistroFCH();

                    //
                    valida.reasignar("O.T.", xNumeroOrdenArr[indice]);

                    //isValido
                    if (fachadaDctoOrdenDetalleBean.getIdOrden() < 1) {

                        //
                        valida.setCodigoError("ERROR, LA O.T. NO EXISTE");
                        valida.setSolucion("CORREGIR CODIGO O.T.");

                        //
                        request.setAttribute("validacion", valida);
                        return "/jsp/gralError.jsp";

                    }
                }

                //
                DctoOrdenDetalleBean dctoOrdenDetalleBean = new DctoOrdenDetalleBean();

                //
                dctoOrdenDetalleBean.setIdLocal(xIdLocal);
                dctoOrdenDetalleBean.setIdTipoOrden(xIdTipoOrden);
                dctoOrdenDetalleBean.setIdLog(xIdLog);

                //
                dctoOrdenDetalleBean.borraResurtidoCompra();

                //
                for (int indice = 0; indice < xCantidadArr.length; indice++) {

                    //
                    colaboraOrdenDetalleBean.setIdLocal(xIdLocal);
                    colaboraOrdenDetalleBean.setIdTipoOrden(
                            xIdTipoOrdenTrabajoProceso);
                    colaboraOrdenDetalleBean.setNumeroOrden(
                            xNumeroOrdenArr[indice].trim());
                    colaboraOrdenDetalleBean.setItem(xItemBase);

                    //
                    fachadaDctoOrdenDetalleBean =
                            colaboraOrdenDetalleBean.listaOTSumnistroFCH();

                    //---
                    dctoOrdenDetalleBean.setIdLocal(xIdLocal);
                    dctoOrdenDetalleBean.setIdTipoOrden(xIdTipoOrden);
                    dctoOrdenDetalleBean.setIdLog(xIdLog);
                    dctoOrdenDetalleBean.setCantidad(xCantidadArr[indice]);

                    dctoOrdenDetalleBean.setPorcentajeIva(
                            xPorcentajeIvaArr[indice]);

                    dctoOrdenDetalleBean.setIdPlu(xIdPluArr[indice]);
                    dctoOrdenDetalleBean.setVrCostoResurtido(
                            xVrSubtotalArr[indice]);
                    dctoOrdenDetalleBean.setVrIvaResurtido(
                            xVrIvaResurtidoArr[indice]);
                    dctoOrdenDetalleBean.setVrImpoconsumo(
                            xVrImpoconsumoArr[indice]);
                    dctoOrdenDetalleBean.setNumeroOrden(
                            xNumeroOrdenArr[indice].trim());
                    dctoOrdenDetalleBean.setItemOrden(xItemBase);
                    dctoOrdenDetalleBean.setIdOperacion(
                            xIdOperacionArr[indice].trim());
                    dctoOrdenDetalleBean.setIdLocalOrigen(
                            fachadaDctoOrdenDetalleBean.getIdLocal());
                    dctoOrdenDetalleBean.setIdOrdenOrigen(
                            fachadaDctoOrdenDetalleBean.getIdOrden());
                    dctoOrdenDetalleBean.setIdTipoOrdenOrigen(
                            fachadaDctoOrdenDetalleBean.getIdTipoOrden());


                    //---
                    double xVrIvaResurtido = new Double(xVrSubtotalArr[indice]).doubleValue()
                            * (new Double(xPorcentajeIvaArr[indice]).doubleValue() / 100);

                    //
                    dctoOrdenDetalleBean.setVrIvaResurtido(xVrIvaResurtido);

                    //
                    if (dctoOrdenDetalleBean.getCantidad() != 0) {

                        //
                        dctoOrdenDetalleBean.actualizaResurtidoCompra();

                    }
                }

                //---
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
                int xIdRteFuenteVrBase = 0;

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
                return "/jsp/vtaFrmLegServicio.jsp";

            }

            // servicio
            if (accionContenedor.compareTo("servicio") == 0) {

                //
                String xIdLocal = request.getParameter("xIdLocal");
                String xIdTipoOrden = request.getParameter("xIdTipoOrden");
                String xIdLog = request.getParameter("xIdLog");

                //--------------------------------------------------------------
                FachadaDctoOrdenBean fachadaDctoOrdenBean =
                        new FachadaDctoOrdenBean();

                //--
                DctoOrdenBean dctoOrdenBean = new DctoOrdenBean();

                //--
                dctoOrdenBean.setIdLocal(xIdLocal);
                dctoOrdenBean.setIdTipoOrden(xIdTipoOrden);
                dctoOrdenBean.setIdLog(xIdLog);

                //--
                fachadaDctoOrdenBean = dctoOrdenBean.listaOrdenFCH();

                //
                int xNumeroOrden = fachadaDctoOrdenBean.getNumeroOrden();
                int xIdOrden = fachadaDctoOrdenBean.getIdOrden();
                String xIdTerceroServicio = fachadaDctoOrdenBean.getIdCliente();
                String xFechaServicio     = fachadaDctoOrdenBean.getFechaEntregaCorta();
                int xIdOrdenServicio      = fachadaDctoOrdenBean.getIdOrden();

                //
                int xIdTipoTerceroCliente = 2;

                //
                ColaboraTercero colaboraTercero = new ColaboraTercero();

                //
                colaboraTercero.setIdCliente(xIdTerceroServicio);
                colaboraTercero.setIdTipoTercero(xIdTipoTerceroCliente);

                //
                FachadaTerceroBean fachadaTerceroBean = new FachadaTerceroBean();
                //
                fachadaTerceroBean =
                        colaboraTercero.listaTerceroFCH();

                //
                String xNombreTerceroServicio = fachadaTerceroBean.getNombreTercero();

                //
                FachadaDctoOrdenDetalleBean fachadaDctoOrdenDetalleBean = new FachadaDctoOrdenDetalleBean();

                //
                DctoOrdenDetalleBean dctoOrdenDetalleBean = new DctoOrdenDetalleBean();

                //
                dctoOrdenDetalleBean.setIdLocal(xIdLocal);
                dctoOrdenDetalleBean.setIdTipoOrden(xIdTipoOrden);
                dctoOrdenDetalleBean.setIdOrden(xIdOrden);

                fachadaDctoOrdenDetalleBean = dctoOrdenDetalleBean.listaUnDctoDetalle();

                int xIdOperacion = fachadaDctoOrdenDetalleBean.getIdOperacion();

                //---
                FachadaColaboraDctoOrdenBean fachadaColaboraDctoOrdenBean
                                           = new FachadaColaboraDctoOrdenBean();

                //--
                ColaboraDctoOrdenBean colaboraDctoOrdenBean
                                                  = new ColaboraDctoOrdenBean();

                //--
                colaboraDctoOrdenBean.setIdLocal(xIdLocal);
                colaboraDctoOrdenBean.setIdTipoOrden(xIdTipoOrdenTrabajoProceso);
                colaboraDctoOrdenBean.setNumeroOrden(xNumeroOrden);

                //
                fachadaColaboraDctoOrdenBean = colaboraDctoOrdenBean.listaOTFCH();

                //
                xIdTipoOrden = fachadaColaboraDctoOrdenBean.getIdTipoOrdenStr();
                xIdOrden = fachadaColaboraDctoOrdenBean.getIdOrden();
                String xIdCliente = fachadaColaboraDctoOrdenBean.getIdCliente();
                int xIdFicha = fachadaColaboraDctoOrdenBean.getIdFicha();

                //--------------------------------------------------------------
                dctoOrdenDetalleBean.setIdLocal(xIdLocal);
                dctoOrdenDetalleBean.setIdTipoOrden(xIdTipoOrden);
                dctoOrdenDetalleBean.setIdOrden(xIdOrden);

                //
                fachadaDctoOrdenDetalleBean =
                        dctoOrdenDetalleBean.listaUnDctoDetalle();

                //
                String xNombreReporte = "";
                int xItemPadre = fachadaDctoOrdenDetalleBean.getItem();

                //--------------------------------------------------------------
                switch (xIdOperacion) {
                    case 2:
                        xNombreReporte = "POTRepOTOperacion_Extrusion";
                        break;
                    case 3:
                        xNombreReporte = "POTRepOTOperacion_Impresion";
                        break;
                    case 4:
                        xNombreReporte = "POTRepOTOperacion_Refilado";
                        break;
                    case 5:
                        xNombreReporte = "POTRepOTOperacion_Sellado";
                        break;
                    case 6:
                        xNombreReporte = "POTRepOTOperacion_Manualidad";
                        break;
                    default:
                        xNombreReporte = "POTRepOTOperacion";
                        break;
                }

                //---
                GeneraPDF_OTOperacion generaPDF = new GeneraPDF_OTOperacion();

                //---
                generaPDF.setIdLocal(xIdLocal);
                generaPDF.setOrdenServicio(xIdOrdenServicio);
                generaPDF.setNumeroOrden(xNumeroOrden);
                generaPDF.setItemPadre(xItemPadre);
                generaPDF.setNombreTercero(xNombreTerceroServicio);
                generaPDF.setTituloReporte("ORDEN DE SERVICIO");
                generaPDF.setFechaServicio(xFechaServicio);
                generaPDF.setNombreReporte(xNombreReporte);

                //---
                generaPDF.generaPdf(request, response);


            }

            //
            if (accionContenedor.compareTo("coger") == 0) {

                //
                String xIdLocal = request.getParameter("xIdLocal");
                String xIdTipoOrden = request.getParameter("xIdTipoOrden");
                String xIdLog = request.getParameter("xIdLog");

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
                              = colaboraResurtidoOrden.listaLegalizaCompraFCH();

                //
                fachadaColaboraDctoOrdenBean.setFechaEntrega(strFechaVisita);

                //
                request.setAttribute("fachadaColaboraDctoOrdenBean",
                        fachadaColaboraDctoOrdenBean);

                /*--------------------------------------------------------------
                DctoOrdenDetalleBean  dctoOrdenDetalleBean = new
                DctoOrdenDetalleBean();

                //
                dctoOrdenDetalleBean.setIdLocal(xIdLocal);
                dctoOrdenDetalleBean.setIdTipoOrden(xIdTipoOrden);
                dctoOrdenDetalleBean.setIdLog(xIdLog);

                //--- actualizaPendiente recibo parcial
                dctoOrdenDetalleBean.actualizaPendiente();*/

                //
                ColaboraOrdenDetalleBean colaboraOrdenDetalleBean = new ColaboraOrdenDetalleBean();

                //
                FachadaDctoOrdenDetalleBean fachadaDctoOrdenDetalleBean = new FachadaDctoOrdenDetalleBean();

                //
                colaboraOrdenDetalleBean.setIdLocal(xIdLocal);
                colaboraOrdenDetalleBean.setIdTipoOrden(xIdTipoOrden);
                colaboraOrdenDetalleBean.setIdLog(xIdLog);

                //---
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
                int xIdRteFuenteVrBase = 0;

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
                fachadaColaboraDctoOrdenBean.setIdLocal(xIdLocal);

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
                return "/jsp/vtaFrmLegServicio.jsp";


            }

            // Traer
            if (accionContenedor.compareTo("Traer") == 0) {

                //
                String xDiasHistoria = "0";
                String xDiasInventario = "0";
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
                fachadaDctoOrdenBean.setIdLocal(xIdLocalUsuario);
                fachadaDctoOrdenBean.setIdTipoOrden(xIdTipoOrdenCompraProceso);
                fachadaDctoOrdenBean.setIdUsuario(xIdUsuario);
                fachadaDctoOrdenBean.setIdLog(xIdLogActual);

                //
                request.setAttribute("fachadaDctoOrdenBean",
                        fachadaDctoOrdenBean);

                //
                return "/jsp/vtaFrmLstServicio.jsp";

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
                return "/jsp/vtaContenedorServicio.jsp";

            }

            // Crear
            if (accionContenedor.compareTo("Crear") == 0) {

                //---
                String xIdLocal = request.getParameter("xIdLocal");
                String xDiasHistoria = "0";
                String xDiasInventario = "0";
                String xIdTercero = request.getParameter("xIdTercero");
                String xFechaCorte = request.getParameter("xFechaCorte");
                String xNumeroOrdenStr = request.getParameter("xNumeroOrden");
                String xIdOperacionStr = request.getParameter("xIdOperacion");

                //--------------------------------------------------------------
                xIdLogActual = new Integer(xIdLogActual).intValue();

                // validacion
                Validacion validacion = new Validacion();

                //
                validacion.reasignar("O.T.", xNumeroOrdenStr);

                validacion.validarCampoEntero();

                // isValido
                if (!validacion.isValido()) {

                    //
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralError.jsp";

                }

                //
                validacion.reasignar("OPERACION", xIdOperacionStr);

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
                int xNumeroOrden = new Integer(xNumeroOrdenStr).intValue();
                int xIdOperacion = new Integer(xIdOperacionStr).intValue();

                //--------------------------------------------------------------
                ColaboraDctoOrdenBean colaboraDctoOrdenBean = new ColaboraDctoOrdenBean();

                //
                FachadaColaboraDctoOrdenBean fachadaColaboraDctoOrdenBean = new FachadaColaboraDctoOrdenBean();

                //
                colaboraDctoOrdenBean.setIdLocal(xIdLocal);
                colaboraDctoOrdenBean.setIdTipoOrden(xIdTipoOrdenTrabajoProceso);
                colaboraDctoOrdenBean.setNumeroOrden(xNumeroOrden);

                //
                fachadaColaboraDctoOrdenBean = colaboraDctoOrdenBean.listaOTFCH();

                //
                String xIdPlu = fachadaColaboraDctoOrdenBean.getIdPluStr();

                //
                validacion.reasignar("O.T.", xNumeroOrdenStr);

                //
                if (fachadaColaboraDctoOrdenBean.getNumeroOrden() == 0) {

                    //
                    validacion.setDescripcionError("NO EXISTE LA O.T. #" + xNumeroOrden);

                    //
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralError.jsp";

                }

                //--------------------------------------------------------------
                ProcesoGuardaPluServicio procesoGuardaPluServicio = new ProcesoGuardaPluServicio();


                //
                procesoGuardaPluServicio.guarda(xIdLogActual,
                        xIdPlu,
                        "1",
                        "1",
                        xIdTipoOrdenCompraProceso,
                        xIdUsuario,
                        xIdLocalUsuario,
                        xIdTercero,
                        xFechaCorte,
                        xDiasHistoria,
                        xDiasInventario,
                        xNumeroOrden,
                        xIdOperacion);

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
                return "/jsp/vtaContenedorServicio.jsp";

            }
        }

        return "/jsp/empty.htm";

    }
}
