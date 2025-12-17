package com.solucionesweb.losservlets;

// Importa los paquetes del lenguaje especificamente io y los
// que permiten trabajar el servlet.
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URL;

//
import java.util.logging.Level;
import java.util.logging.Logger;

import com.solucionesweb.lasayudas.ProcesoResurtidoEnviaRecibeXML;

import com.solucionesweb.lasayudas.ProcesoOrdenCompra;

//
import com.solucionesweb.losbeans.utilidades.JhDate;

// Importa la clase que contiene el UsuarioBean
import com.solucionesweb.losbeans.negocio.UsuarioBean;

//
import com.solucionesweb.losbeans.negocio.AgendaLogVisitaBean;

//
import com.solucionesweb.losbeans.negocio.DctoOrdenDetalleBean;

//
import com.solucionesweb.losbeans.fachada.FachadaAgendaLogVisitaBean;

import com.solucionesweb.losbeans.fachada.FachadaColaboraDctoOrdenBean;

//
import com.solucionesweb.losbeans.fachada.FachadaDctoOrdenBean;

// Importa la clase que contiene el Bean de Validacion
import com.solucionesweb.losbeans.utilidades.Validacion;

// Importa la clase que contiene el Day
import com.solucionesweb.losbeans.utilidades.Day;

import com.solucionesweb.lasayudas.ProcesoGuardaResurtidoPlu;

// Importa la clase que contiene el TerceroBean
import com.solucionesweb.losbeans.negocio.TerceroBean;

import com.solucionesweb.losbeans.fachada.FachadaTerceroBean;

// Importa la clase que contiene el DctoBean
import com.solucionesweb.losbeans.negocio.DctoOrdenBean;

// Importa la clase que contiene el LocalIpBean
import com.solucionesweb.losbeans.negocio.LocalIpBean;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaLocalIp;

import com.solucionesweb.lasayudas.ProcesoIp;

// Importa la clase que contiene el DctoOrdenDetalleTx
import com.solucionesweb.losbeans.negocio.DctoOrdenDetalleTx;
import java.util.Iterator;
import java.util.Vector;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaDctoOrdenDetalleBean;

import com.solucionesweb.losbeans.colaboraciones.ColaboraDctoOrdenDetalleBean;

import com.solucionesweb.losbeans.colaboraciones.ColaboraResurtidoOrden;

import com.solucionesweb.losbeans.colaboraciones.ColaboraOrdenDetalleBean;

import com.solucionesweb.losbeans.colaboraciones.ColaboraTercero;

import com.solucionesweb.losbeans.negocio.ContableRetencionBean;
/**
 * sistema despliega la interfaz RESURTIDO ORDEN DE COMPRA /
 * vtaContenedorOrdenCompra.jsp /
 * 
 * Este servlet implementa la interface GralManejadorRequest/
 */
// 
public class VtasAdmOrdenCompraManejadorRequest
        implements GralManejadorRequest {

    // xmlByteArray
    byte[] xmlByteArray = null;
    ByteArrayInputStream xmlStream = null;
    InputStreamReader xmlReader = null;
    // inicializamos las variables no gestionadas por el compilador NetBeans 3.6
    private URL destino = null;
    /**
     * BUTTON--
     *("Regresar")-Retorna al menu principal /
     * ("Consolidar")- /
     * ("Listar")-Permite ver una lista de productos /
     * ("Elaborar")-Permite confirmar orden de compra /
     * ("Guardar")-Permite guardar cambios /
     * ("Confirmar")-Permite terminar proceso /
     * 
     * Metodo contructor por defecto, es decir, sin parametros /
     */
 
    public VtasAdmOrdenCompraManejadorRequest() {
    }
     /**
     * BUTTON PARAMETER--
     * "#Dias Historia"-Ingreso de numero de dias /
     * "Fecha Corte"-Fecha de corte para el resurtido compra /
     * "#Dias Inventario"-Ingrese numero de dias de inventario /
     * "Proveedor"-Selecciona proveedor /
     * "Marca"-Selecciona marcas de productos /
     * "Cto.Neg"- /
     * "Can.Ped"- /
     * "Can.Bon"- /
     * "Observaciones"-Comentarios de pedido /
     * "Fecha Entrega"-Fecha de entrega de pedido /
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
            Logger.getLogger(VtasAdmOrdenCompraManejadorRequest.class.getName()).log(Level.SEVERE, null, ex);
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
        if (accionContenedor.compareTo("Consolidar") == 0) {

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

            //
            if (accionContenedor.compareTo("tomarExcel") == 0) {

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
                fachadaColaboraDctoOrdenBean.setVrCostoSinIva(
                        fachadaDctoOrdenDetalleBean.getVrCostoSinIva());
                fachadaColaboraDctoOrdenBean.setVrImpoconsumo(
                        fachadaDctoOrdenDetalleBean.getVrImpoconsumo());
                fachadaColaboraDctoOrdenBean.setVrCostoRteFuente(xVrRetencion);
                fachadaColaboraDctoOrdenBean.setVrCostoIva(
                        fachadaDctoOrdenDetalleBean.getVrIvaCosto());

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
                response.setContentType("application/vnd.ms-excel");
                response.setHeader("Content-Disposition",
                        "attachment;filename=archivo.xls");

                //          
                return "/jsp/vtaFrmConOrdenCompraArchivo.jsp";

            }

            // tomar
            if (accionContenedor.compareTo("tomar") == 0) {

                //
                String xIdLocal = request.getParameter("xIdLocal");
                String xIdOrden = request.getParameter("xIdOrden");
                String xIdTipoOrden = request.getParameter("xIdTipoOrden");
                String xIdLog = request.getParameter("xIdLog");

                //
                GeneraPDFResurtidoCompraSolicitado generaPDF = new GeneraPDFResurtidoCompraSolicitado();

                //
                generaPDF.setIdLocal(xIdLocal);
                generaPDF.setIdOrden(xIdOrden);
                generaPDF.setIdTipoOrden(xIdTipoOrden);
                generaPDF.setIdLog(xIdLog);
                generaPDF.setTituloReporte("COMPRA SOLICITADA");
                generaPDF.setReporteName("RepEmpresaResurtidoCompraSolicitada");

                //
                generaPDF.generaPdf(request, response);

            }

            // tomar
            if (accionContenedor.compareTo("tomarCiega") == 0) {

                //
                String xIdLocal = request.getParameter("xIdLocal");
                String xIdOrden = request.getParameter("xIdOrden");
                String xIdTipoOrden = request.getParameter("xIdTipoOrden");
                String xIdLog = request.getParameter("xIdLog");

                //
                GeneraPDFResurtidoCompraSolicitado generaPDF = new GeneraPDFResurtidoCompraSolicitado();

                //
                generaPDF.setIdLocal(xIdLocal);
                generaPDF.setIdOrden(xIdOrden);
                generaPDF.setIdTipoOrden(xIdTipoOrden);
                generaPDF.setIdLog(xIdLog);
                generaPDF.setTituloReporte("ORDEN COMPRA CIEGA");
                generaPDF.setReporteName("RepEmpresaOrdenCompraSolicitadaCiega");

                //
                generaPDF.generaPdf(request, response);

            }

            // Listar
            if (accionContenedor.compareTo("Listar") == 0) {

                //
                String xFechaCorte = request.getParameter("xFechaCorte");

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
                int xDiasHistoria = 0;
                int xDiasInventario = 0;


                //
                FachadaDctoOrdenBean fachadaDctoOrdenBean = new FachadaDctoOrdenBean();

                //
                fachadaDctoOrdenBean.setDiasHistoria(xDiasHistoria);
                fachadaDctoOrdenBean.setDiasInventario(xDiasInventario);
                fachadaDctoOrdenBean.setFechaOrden(xFechaCorte);
                fachadaDctoOrdenBean.setIdLocal(xIdLocalUsuario);
                fachadaDctoOrdenBean.setIdTipoOrden(xIdTipoOrdenCompraProceso);
                fachadaDctoOrdenBean.setIdUsuario(xIdUsuario);
                fachadaDctoOrdenBean.setIdLog(xIdLogActual);

                //
                request.setAttribute("fachadaDctoOrdenBean",
                        fachadaDctoOrdenBean);

                //
                return "/jsp/vtaFrmConOrdenCompra.jsp";


            }

            // Confirmar
            if (accionContenedor.compareTo("Confirmar") == 0) {

                //
                String xIdLog = request.getParameter("xIdLog");

                //
                ProcesoIp procesoIp = new ProcesoIp();

                //
                String xIpTx = procesoIp.getIpTx(request);

                //
                ProcesoOrdenCompra procesoOrdenCompra =
                        new ProcesoOrdenCompra();

                //
                procesoOrdenCompra.guarda(new Integer(xIdLocalUsuario).intValue(),
                        xIdTipoOrdenCompraProceso,
                        new Integer(xIdLog).intValue(),
                        xIpTx);


            }

            // Guardar
            if (accionContenedor.compareTo("Guardar") == 0) {

                //
                String xCantidadPedidaArr[] =
                        request.getParameterValues("xCantidadPedido");
                String xCantidadBonificadaArr[] =
                        request.getParameterValues("xCantidadBonificada");
                String xVrCostoNegociadoArr[] =
                        request.getParameterValues("xVrCostoNegociado");
                String xIdPluArr[] = request.getParameterValues("xIdPlu");
                String xIdLog = request.getParameter("xIdLog");
                String xDiasInventario = request.getParameter("xDiasInventario");
                String xDiasHistoria = request.getParameter("xDiasHistoria");
                String xIdTercero = request.getParameter("xIdTercero");
                String xObservacion = request.getParameter("xObservacion");
                String xFechaEntrega = request.getParameter("xFechaEntrega");

                //
                Validacion valida = new Validacion();

                //
                valida.reasignar("FECHA ENTREGA", xFechaEntrega);

                //
                valida.validarCampoFecha();

                //isValido
                if (!valida.isValido()) {

                    //
                    request.setAttribute("validacion", valida);
                    return "/jsp/gralError.jsp";
                }

                // Validar
                for (int indice = 0; indice < xIdPluArr.length; indice++) {

                    //
                    if (xCantidadPedidaArr[indice].length() == 0) {
                        continue;
                    }

                    //
                    valida.reasignar("CAN.PED", xCantidadPedidaArr[indice]);

                    //
                    valida.validarCampoDoublePositivo();

                    //isValido
                    if (!valida.isValido()) {

                        //
                        request.setAttribute("validacion", valida);
                        return "/jsp/gralError.jsp";
                    }

                    //
                    valida.reasignar("CAN.BON", xCantidadBonificadaArr[indice]);

                    //
                    valida.validarCampoDoublePositivo();

                    //isValido
                    if (!valida.isValido()) {

                        //
                        request.setAttribute("validacion", valida);
                        return "/jsp/gralError.jsp";
                    }

                    //
                    if (xVrCostoNegociadoArr[indice].length() == 0) {
                        continue;
                    }

                    //
                    valida.reasignar("CTO.NEG", xVrCostoNegociadoArr[indice]);

                    //
                    valida.validarCampoDoublePositivo();

                    //isValido
                    if (!valida.isValido()) {

                        //
                        request.setAttribute("validacion", valida);
                        return "/jsp/gralError.jsp";
                    }

                    //
                    if (xVrCostoNegociadoArr[indice].length() == 0) {
                        continue;
                    }

                }

                //
                xIdLogActual = new Integer(xIdLog).intValue();

                //
                ProcesoGuardaResurtidoPlu procesoGuardaPlu
                                             = new ProcesoGuardaResurtidoPlu();

                //
                for (int indice = 0; indice
                        < xCantidadPedidaArr.length; indice++) {

                    //
                    procesoGuardaPlu.guarda(
                            new Integer(xIdLocalUsuario).intValue(),
                            xIdTipoOrdenCompraProceso,
                            xIdLogActual,
                            xIdPluArr[indice],
                            xCantidadPedidaArr[indice],
                            xCantidadBonificadaArr[indice],
                            xVrCostoNegociadoArr[indice],
                            xIdTercero,
                            xObservacion,
                            new Integer(xDiasHistoria).intValue(),
                            new Integer(xDiasInventario).intValue(),
                            xFechaEntrega);
                }

                //
                DctoOrdenBean dctoOrdenBean = new DctoOrdenBean();

                //
                FachadaDctoOrdenBean fachadaDctoOrdenBean
                                                   = new FachadaDctoOrdenBean();

                // Consulta si existeOrden
                dctoOrdenBean.setIdLog(xIdLog);

                //
                fachadaDctoOrdenBean = dctoOrdenBean.listaDctoOrdenIdLog();

                //
                fachadaDctoOrdenBean.setIdLocal(xIdLocalUsuario);
                fachadaDctoOrdenBean.setIdTipoOrden(xIdTipoOrdenCompraProceso);
                fachadaDctoOrdenBean.setIdUsuario(xIdUsuario);
                fachadaDctoOrdenBean.setFechaOrden(strFechaVisita);
                fachadaDctoOrdenBean.setDiasHistoria(xDiasHistoria);
                fachadaDctoOrdenBean.setDiasInventario(xDiasInventario);
                fachadaDctoOrdenBean.setIdLog(xIdLog);
                fachadaDctoOrdenBean.setIdTipoTercero(xIdTipoTerceroProveedor);
                fachadaDctoOrdenBean.setIdCliente(
                        fachadaDctoOrdenBean.getIdCliente());

                //
                request.setAttribute("fachadaDctoOrdenBean",
                        fachadaDctoOrdenBean);

                //
                FachadaTerceroBean fachadaTerceroBean = new FachadaTerceroBean();

                //
                TerceroBean terceroBean = new TerceroBean();

                //
                terceroBean.setIdTipoTercero(xIdTipoTerceroProveedor);
                terceroBean.setIdCliente(fachadaDctoOrdenBean.getIdCliente());

                //
                fachadaTerceroBean =
                        terceroBean.listaUnTerceroFCH();

                //
                request.setAttribute("fachadaTerceroBean", fachadaTerceroBean);

                //
                ColaboraDctoOrdenDetalleBean colaboraDctoOrdenDetalleBean =
                        new ColaboraDctoOrdenDetalleBean();

                //
                colaboraDctoOrdenDetalleBean.setIdLocal(xIdLocalUsuario);
                colaboraDctoOrdenDetalleBean.setIdTipoOrden(
                        xIdTipoOrdenCompraProceso);
                colaboraDctoOrdenDetalleBean.setIdLog(xIdLog);

                //
                FachadaDctoOrdenDetalleBean fachadaDctoOrdenDetalleBean =
                        new FachadaDctoOrdenDetalleBean();

                // liquidaResurtidoFCH
                fachadaDctoOrdenDetalleBean =
                        colaboraDctoOrdenDetalleBean.liquidaPedidoResurtidoFCH();

                //
                request.setAttribute("fachadaDctoOrdenDetalleBean",
                        fachadaDctoOrdenDetalleBean);

                //
                return "/jsp/vtaFrmLstOrdenCompra.jsp";

            }

            // Elaborar
            if (accionContenedor.compareTo("Elaborar") == 0) {

                //
                String xIdLog = request.getParameter("xIdLog");
                String xIdLocal = request.getParameter("xIdLocal");
                String xDiasHistoria = request.getParameter("xDiasHistoria");
                String xDiasInventario = request.getParameter("xDiasInventario");
                String xIdTercero = request.getParameter("xIdTercero");
                String xIdMarcaArr[] = request.getParameterValues("xIdMarca");

                //
                Validacion valida = new Validacion();

                //
                valida.reasignar("#DIAS INVENTARIO", xDiasInventario);

                //
                valida.validarCampoEntero();

                //isValido
                if (!valida.isValido()) {

                    //
                    request.setAttribute("validacion", valida);
                    return "/jsp/gralError.jsp";
                }

                //
                valida.reasignar("PROVEEDOR", xIdTercero);

                //
                valida.validarCampoDouble();

                //isValido
                if (!valida.isValido()) {

                    //
                    request.setAttribute("validacion", valida);
                    return "/jsp/gralError.jsp";
                }

                //
                if (xIdMarcaArr == null) {

                    //
                    valida.reasignar("MARCA", "");
                    valida.setSolucion("ERROR, MINIMO SELECCIONAR UNA MARCA");

                    //
                    request.setAttribute("validacion", valida);
                    return "/jsp/gralError.jsp";

                }

                String xIdMarcaAll = "";

                //
                for (int indice = 0; indice < xIdMarcaArr.length; indice++) {

                    //
                    xIdMarcaAll += xIdMarcaArr[indice] + ",";

                }

                //
                xIdMarcaAll += "0";

                //
                int xIdOrdenMax = 0;
                int xIdOrigenWeb = 4;
                int xEstadoDctoOrden = 1;

                //
                DctoOrdenBean dctoOrdenBean = new DctoOrdenBean();

                //
                FachadaDctoOrdenBean fachadaDctoOrdenBean = new FachadaDctoOrdenBean();

                // Consulta si existeOrden
                dctoOrdenBean.setIdLog(xIdLog);

                //
                fachadaDctoOrdenBean = dctoOrdenBean.listaDctoOrdenIdLog();

                //
                if (fachadaDctoOrdenBean.getIdOrden() > 0) {

                    // SI existeOrden
                    xIdOrdenMax = fachadaDctoOrdenBean.getIdOrden();

                } else {

                    //
                    FachadaTerceroBean fachadaTerceroBean = new FachadaTerceroBean();

                    //
                    TerceroBean terceroBean = new TerceroBean();

                    //
                    terceroBean.setIdCliente(xIdTercero);

                    //
                    fachadaTerceroBean =
                            terceroBean.listaUnTerceroFachada();

                    //
                    String xEmail = fachadaTerceroBean.getEmail();
                    String xIdFormaPago =
                            fachadaTerceroBean.getIdFormaPagoStr();

                    // NO existeOrden y se igual idLocal = idLocalInicial
                    dctoOrdenBean.setIdLocal(xIdLocalUsuario);
                    dctoOrdenBean.setIdTipoOrden(xIdTipoOrdenCompraProceso);

                    //
                    xIdOrdenMax =
                            dctoOrdenBean.maximaIdOrdenIdLocal() + 1;

                    //
                    dctoOrdenBean.setIdLocal(xIdLocalUsuario);
                    dctoOrdenBean.setIdTipoOrden(xIdTipoOrdenCompraProceso);
                    dctoOrdenBean.setIdOrden(xIdOrdenMax);
                    dctoOrdenBean.setFechaOrden(strFechaVisita);
                    dctoOrdenBean.setEstado(xEstadoDctoOrden);
                    dctoOrdenBean.setIdCliente(xIdTercero);
                    dctoOrdenBean.setIdUsuario(xIdUsuario);
                    dctoOrdenBean.setIdOrigen(xIdOrigenWeb);
                    dctoOrdenBean.setIdLog(xIdLog);
                    dctoOrdenBean.setFechaEntrega(strFechaVisita);
                    dctoOrdenBean.setTipoDcto(
                            new Integer(xIdTipoOrdenCompraProceso).toString());
                    dctoOrdenBean.setEmail(xEmail);
                    dctoOrdenBean.setFormaPago(xIdFormaPago);

                    //
                    boolean okIngreso = dctoOrdenBean.ingresaDctosOrden();

                }

                //
                DctoOrdenDetalleBean dctoOrdenDetalleBean = new DctoOrdenDetalleBean();

                //
                dctoOrdenDetalleBean.setIdLocal(xIdLocal);
                dctoOrdenDetalleBean.setIdLog(xIdLog);
                dctoOrdenDetalleBean.setIdLocal(xIdLocal);
                dctoOrdenDetalleBean.setIdTipoOrden(xIdTipoOrdenCompraProceso);
                dctoOrdenDetalleBean.setIdOrden(xIdOrdenMax);


                // retiraLogItem
                dctoOrdenDetalleBean.retiraOrdenItem();

                // retiraLogItem
                dctoOrdenDetalleBean.ingresaOrdenItem(xIdMarcaAll,
                        xIdTipoOrdenCompra);

                // actualizaCostoResurtido
                dctoOrdenDetalleBean.actualizaCostoResurtido();

                //
                fachadaDctoOrdenBean.setIdLocal(xIdLocalUsuario);
                fachadaDctoOrdenBean.setIdTipoOrden(xIdTipoOrdenCompraProceso);
                fachadaDctoOrdenBean.setIdUsuario(xIdUsuario);
                fachadaDctoOrdenBean.setFechaOrden(strFechaVisita);
                fachadaDctoOrdenBean.setDiasHistoria(xDiasHistoria);
                fachadaDctoOrdenBean.setDiasInventario(xDiasInventario);
                fachadaDctoOrdenBean.setIdLog(xIdLog);
                fachadaDctoOrdenBean.setIdTipoTercero(xIdTipoTerceroProveedor);
                fachadaDctoOrdenBean.setIdCliente(xIdTercero);

                //
                request.setAttribute("fachadaDctoOrdenBean",
                        fachadaDctoOrdenBean);

                //
                FachadaTerceroBean fachadaTerceroBean = new FachadaTerceroBean();

                //
                TerceroBean terceroBean = new TerceroBean();

                //
                terceroBean.setIdTipoTercero(xIdTipoTerceroProveedor);
                terceroBean.setIdCliente(xIdTercero);

                //
                fachadaTerceroBean =
                        terceroBean.listaUnTerceroFCH();

                //
                request.setAttribute("fachadaTerceroBean", fachadaTerceroBean);

                //
                ColaboraDctoOrdenDetalleBean colaboraDctoOrdenDetalleBean =
                        new ColaboraDctoOrdenDetalleBean();

                //
                colaboraDctoOrdenDetalleBean.setIdLocal(xIdLocalUsuario);
                colaboraDctoOrdenDetalleBean.setIdTipoOrden(
                        xIdTipoOrdenCompraProceso);
                colaboraDctoOrdenDetalleBean.setIdLog(xIdLog);

                //
                FachadaDctoOrdenDetalleBean fachadaDctoOrdenDetalleBean =
                        new FachadaDctoOrdenDetalleBean();

                // liquidaResurtidoFCH
                fachadaDctoOrdenDetalleBean =
                        colaboraDctoOrdenDetalleBean.liquidaPedidoResurtidoFCH();

                //
                request.setAttribute("fachadaDctoOrdenDetalleBean",
                        fachadaDctoOrdenDetalleBean);

                //
                return "/jsp/vtaFrmLstOrdenCompra.jsp";

            }

            // trae
            if (accionContenedor.compareTo("trae") == 0) {

                //
                String xIdLog = request.getParameter("xIdLog");
                String xIdLocal = request.getParameter("xIdLocal");
                String xDiasHistoria = request.getParameter("xDiasHistoria");



                FachadaDctoOrdenDetalleBean fachadaDctoOrdenDetalleBean = new FachadaDctoOrdenDetalleBean();

                //
                ColaboraDctoOrdenDetalleBean colaboraDctoOrdenDetalleBean = new ColaboraDctoOrdenDetalleBean();

                //
                colaboraDctoOrdenDetalleBean.setIdLog(xIdLog);

                //
                fachadaDctoOrdenDetalleBean = colaboraDctoOrdenDetalleBean.listaResurtidoFCH();

                //
                FachadaDctoOrdenBean fachadaDctoOrdenBean =
                        new FachadaDctoOrdenBean();

                //
                fachadaDctoOrdenBean.setIdLocal(xIdLocalUsuario);
                fachadaDctoOrdenBean.setIdTipoOrden(xIdTipoOrdenCompraProceso);
                fachadaDctoOrdenBean.setIdUsuario(xIdUsuario);
                fachadaDctoOrdenBean.setFechaOrden(strFechaVisita);
                fachadaDctoOrdenBean.setDiasHistoria(
                        fachadaDctoOrdenDetalleBean.getDiasHistoria());
                fachadaDctoOrdenBean.setDiasInventario(0);
                fachadaDctoOrdenBean.setIdLog(xIdLog);
                fachadaDctoOrdenBean.setIdTipoTercero(xIdTipoTerceroProveedor);

                //
                request.setAttribute("fachadaDctoOrdenBean",
                        fachadaDctoOrdenBean);

                //
                return "/jsp/vtaFrmIngOrdenCompra.jsp";

            }

            // Consolidar
            if (accionContenedor.compareTo("Consolidar") == 0) {

                //
                String xDiasHistoria = request.getParameter("xDiasHistoria");
                String xFechaCorte = request.getParameter("xFechaCorte");

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
                validacion.reasignar("FECHA CORTE", xFechaCorte);

                validacion.validarCampoFecha();

                // isValido
                if (!validacion.isValido()) {

                    //
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralError.jsp";

                }

                //
                String anoStr = xFechaCorte.substring(0, 4);
                String mesStr = xFechaCorte.substring(5, 7);
                String diaStr = xFechaCorte.substring(8, 10);

                //
                int anoInt = Integer.parseInt(anoStr);
                int mesInt = Integer.parseInt(mesStr);
                int diaInt = Integer.parseInt(diaStr);

                //
                Day xFechaFinal = new Day(anoInt, mesInt, diaInt);
                Day xFechaInicial = new Day(anoInt, mesInt, diaInt);

                //
                xFechaInicial.advance(new Integer(xDiasHistoria).intValue()
                        * (-1));

                //
                FachadaDctoOrdenBean fachadaDctoOrdenBean =
                        new FachadaDctoOrdenBean();

                //
                fachadaDctoOrdenBean.setDiasHistoria(xDiasHistoria);
                fachadaDctoOrdenBean.setFechaInicial(
                        xFechaInicial.getFechaFormateada());
                fachadaDctoOrdenBean.setFechaFinal(
                        xFechaFinal.getFechaFormateada());

                //------------------------------------------------ consolida ---
                DctoOrdenDetalleTx dctoOrdenDetalleTx = new DctoOrdenDetalleTx();

                // retira
                dctoOrdenDetalleTx.retira();

                //-------------------------------------------- inicio thread ---
                FachadaLocalIp fachadaLocalIp = new FachadaLocalIp();

                //
                LocalIpBean localIpBean = new LocalIpBean();

                //
                localIpBean.setIdLocalPadre(xIdLocalUsuario);

                //
                Vector vecLocalBean = localIpBean.listaAllHijo();

                //---listaAllHijo
                Iterator iteratorBean = vecLocalBean.iterator();

                //---
                while (iteratorBean.hasNext()) {

                    //
                    fachadaLocalIp = (FachadaLocalIp) iteratorBean.next();

                    //
                    String xHostName = fachadaLocalIp.getIp();
                    String xIdPuertoLocal = ":"
                            + fachadaLocalIp.getPuertoHttp();

                    //
                    String xPagina = xHostName
                            + xIdPuertoLocal
                            + "/Commerce/jsp/"
                            + "txResurtidoLocal.jsp"
                            + "?xIdLocal=" + fachadaLocalIp.getIdLocal()
                            + "&xFechaInicial="
                            + fachadaDctoOrdenBean.getFechaInicialSqlServer()
                            + "&xFechaFinal="
                            + fachadaDctoOrdenBean.getFechaFinalSqlServer()
                            + "&xIdLog=" + xIdLogActual;

                    //
                    ProcesoResurtidoEnviaRecibeXML procesoXML =
                            new ProcesoResurtidoEnviaRecibeXML(xPagina,
                            fachadaDctoOrdenBean.getFechaInicialSqlServer(),
                            fachadaDctoOrdenBean.getDiasHistoria());

                    //
                    procesoXML.start();

                }
            }
        }

        return "/jsp/empty.htm";

    }
}
