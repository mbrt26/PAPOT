package com.solucionesweb.losservlets;

//
import com.solucionesweb.lasayudas.ProcesoDespachoEnviaRecibeXML;

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

// Importa la clase ProcesoIngreso
import com.solucionesweb.lasayudas.ProcesoIngresoDespacho;

// Importa la clase que contiene el Bean de Validacion
import com.solucionesweb.losbeans.utilidades.Validacion;

// Importa la clase que contiene el Day
import com.solucionesweb.losbeans.utilidades.Day;

// Importa la clase que contiene ProcesoGuardaPluOrden
import com.solucionesweb.lasayudas.ProcesoGuardaPluOrden;
import com.solucionesweb.lasayudas.ProcesoIngresoDespachoOrigen;
import com.solucionesweb.losbeans.colaboraciones.ColaboraResurtidoOrden;
import com.solucionesweb.losbeans.fachada.FachadaColaboraDctoOrdenBean;
import com.solucionesweb.losbeans.fachada.FachadaLocalIp;
import com.solucionesweb.losbeans.negocio.LocalIpBean;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * Permite el regitro de despachos /
 * vtaContenedorResurtidoDespacho.jsp /
 * 
 * Este servlet implementa la interface GralManejadorRequest /
 */
public class VtasAdmResurtidoDespachoManejadorRequest
        implements GralManejadorRequest {

    private static int xIdOrdenOriginal = 0;
/**
    * BUTTON--
    * ("Elaborar")-Permite ingresar datos de despacho/
    * ("Traer")-Permite ver un despacho /
    * ("Listar")-Permite ver un listado de despachos /
    * ("Regresar")-("Salir")-Retorna al menu principal /
    * 
    * Metodo contructor por defecto, es decir, sin parametros/
    */
    public VtasAdmResurtidoDespachoManejadorRequest() {
    }
     /**
     * BUTTON PARAMETER--
     * "Fecha corte"-Ingreso fecha de corte/
     * "Bodega destino"-seleccione bodega destino de despacho/
     */
    public String generaPdf(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String accionContenedor = request.getParameter("accionContenedor");
        System.out.println("accionContenedor :" + accionContenedor);

        //
        int xEstadoActivo = 9;
        int xEstadoSuspendido = 8;
        int xIdListaPrecio = 1;
        int xIndicador = 1;

        //
        int xIdTipoOrdenDespacho = 5;
        int xIdTipoOrdenDespachoProceso = xIdTipoOrdenDespacho + 50;
        int xIdTipoOrdenAjusteDespacho = xIdTipoOrdenDespacho + 20;

        int xIdTipoOrdenAjusteDespachoProceso =
                xIdTipoOrdenAjusteDespacho + 50;

        int xIdTipoOrdenTraslado = 2;
        int xIdTipoOrdenTrasladoProceso = xIdTipoOrdenTraslado + 50;

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

        //
        agendaLogVisitaBean.setEstado(xEstadoActivo);
        agendaLogVisitaBean.setFechaVisitaStr(strFechaVisita);
        agendaLogVisitaBean.setIdUsuario(xIdUsuario);

        //
        FachadaAgendaLogVisitaBean fachadaAgendaLogVisitaBean
                                             = new FachadaAgendaLogVisitaBean();

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

            if (accionContenedor.compareTo("Regresar") == 0) {
                return "/jsp/empty.htm";

            }

            // ajustePositivo
            if (accionContenedor.compareTo("ajustePositivo") == 0) {

                //
                String xIdLocal = request.getParameter("xIdLocal");
                String xIdOrden = request.getParameter("xIdOrden");
                String xIdTipoOrden = request.getParameter("xIdTipoOrden");

                //
                int xIdTipoAjustePositivo = 1;

                //
                DctoOrdenBean dctoOrdenBean = new DctoOrdenBean();

                // Instancia el Bean de Validacion para validar los campos
                Validacion validacion = new Validacion();

                //
                dctoOrdenBean.setIdLog(xIdLogActual);
                dctoOrdenBean.setIdTipoOrden(xIdTipoOrdenAjusteDespachoProceso);

                // existePedido
                boolean existePedido = dctoOrdenBean.existePedido();

                if (existePedido) {

                    //
                    validacion.reasignar("AJUSTE POSITIVO", "");
                    validacion.setCodigoError("ERROR AJUSTE");
                    validacion.setDescripcionError("AJUSTE POSITIVO EN PROCESO");
                    validacion.setSolucion("INACTIVAR PROCESO");
                    validacion.setValorCampo("CANTIDAD AJUSTE POSITIVO");

                    // Aqui escribe el Bean de Validacion en el Request para manejar el error
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralErrFinalizaCotizacion.jsp";

                }


                //
                FachadaDctoBean fachadaDctoBean = new FachadaDctoBean();

                //
                fachadaDctoBean.setIdLocal(xIdLocal);
                fachadaDctoBean.setIdTipoOrden(xIdTipoOrden);
                fachadaDctoBean.setIdOrden(xIdOrden);
                fachadaDctoBean.setIndicador(xIndicador);
                fachadaDctoBean.setIdTipoAjuste(xIdTipoAjustePositivo);

                setxIdOrdenOriginal(fachadaDctoBean.getIdOrden());


                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaDctoBean", fachadaDctoBean);

                //
                return "/jsp/vtaFrmSelResurtidoDespacho.jsp";

            }

            // Ajustar
            if (accionContenedor.compareTo("Ajustar") == 0) {

                //
                DctoOrdenBean dctoOrdenBean = new DctoOrdenBean();

                //
                dctoOrdenBean.setIdLog(xIdLogActual);
                dctoOrdenBean.setIdTipoOrden(xIdTipoOrdenAjusteDespacho);

                System.out.println(xIdLogActual);
                System.out.println(xIdTipoOrdenAjusteDespacho);

                // existePedido
                boolean existePedido = dctoOrdenBean.existePedido();

                System.out.println(existePedido);

                //
                if (existePedido) {

                    //
                    Validacion validacion = new Validacion();

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
                FachadaDctoBean fachadaDctoBean = new FachadaDctoBean();


                // confirmaPedido
                ProcesoIngresoDespacho proceso = new ProcesoIngresoDespacho();

                // confirma
                fachadaDctoBean =
                        proceso.ingresa(new Integer(xIdLocalUsuario).intValue(),
                        xIdTipoOrdenAjusteDespacho,
                        xIdLogActual,
                        xIdTipoOrdenAjusteDespachoProceso,
                        getxIdOrdenOriginal());

                //
                int xIdEstadoTxSinTx = 1;
                int estadoProgramado = 1;
                int tareaVisita = 6;   // Cotizacion


                // finalizaVisita
                agendaLogVisitaBean.setIdLog(xIdLogActual);
                agendaLogVisitaBean.setIdCliente(
                        fachadaDctoBean.getIdCliente());
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
                boolean okLog = agendaLogVisitaBean.finaliza();

                //
                GeneraPDFResurtido generaPDFResurtido = new GeneraPDFResurtido();

                //
                generaPDFResurtido.setIdLocal(fachadaDctoBean.getIdLocalStr());
                generaPDFResurtido.setIdOrden(fachadaDctoBean.getIdOrden());
                generaPDFResurtido.setIdTipoOrden(
                        fachadaDctoBean.getIdTipoOrdenStr());
                generaPDFResurtido.setReporteName("RepEmpresaResurtido");

                //
                generaPDFResurtido.setTituloReporte("AJUSTE DESPACHO");

                //
                generaPDFResurtido.generaPdf(request, response);


            }

            // Guardar
            if (accionContenedor.compareTo("Guardar") == 0) {

                //
                String xIdLocal = request.getParameter("xIdLocal");
                String xIdOrden = request.getParameter("xIdOrden");
                //String xIdTipoOrden    = request.getParameter("xIdTipoOrden");
                String xIdTipoAjuste = request.getParameter("xIdTipoAjuste");
                String xIdTercero = "1";

                //
                DctoOrdenBean dctoOrdenBean = new DctoOrdenBean();

                // Instancia el Bean de Validacion para validar los campos
                Validacion validacion = new Validacion();

                //
                dctoOrdenBean.setIdLog(xIdLogActual);
                dctoOrdenBean.setIdTipoOrden(xIdTipoOrdenAjusteDespachoProceso);

                // existePedido
                boolean existePedido = dctoOrdenBean.existePedido();

                if (existePedido) {

                    //
                    validacion.reasignar("AJUSTE POSITIVO", "");
                    validacion.setCodigoError("ERROR AJUSTE");
                    validacion.setDescripcionError("AJUSTE POSITIVO EN PROCESO");
                    validacion.setSolucion("INACTIVAR PROCESO");
                    validacion.setValorCampo("CANTIDAD AJUSTE POSITIVO");

                    // Aqui escribe el Bean de Validacion en el Request para manejar el error
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralErrFinalizaCotizacion.jsp";

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


                    //valida el idTercero sea el mismo para todos
                    String strIdReferencia = arrIdReferencia[indice];
                    int xItemPadre = 0;
                    String xComentario = "ninguno";
                    String xIdResponsable = "0";
                    int xIdClasificacion = 0;
                    int xUnidadMedida = 1;

                    //
                    int maximoItem = proceso.guarda(xIdLogActual,
                            strIdReferencia,
                            xCantidad,
                            xVrVentaUnitario,
                            xItemPadre,
                            xIdTipoOrdenAjusteDespachoProceso,
                            xIdUsuario,
                            new Integer(xIdLocalUsuario).intValue(),
                            xIdTercero,
                            xComentario,
                            xIdResponsable,
                            xIdClasificacion,
                            xUnidadMedida);

                }

                //
                dctoOrdenBean.setIdLog(fachadaAgendaLogVisitaBean.getIdLog());
                dctoOrdenBean.setIdTipoOrden(xIdTipoOrdenAjusteDespachoProceso);

                //
                FachadaDctoOrdenBean fachadaDctoOrdenBean
                                                   = new FachadaDctoOrdenBean();

                //
                fachadaDctoOrdenBean =
                        dctoOrdenBean.listaDctoOrdenIdLogIdTipoOrden();


                //
                fachadaDctoOrdenBean.setIdListaPrecio(xIdListaPrecio);
                fachadaDctoOrdenBean.setIdCliente(xIdTercero);
                fachadaDctoOrdenBean.setFechaOrden(strFechaVisita);

                //
                request.setAttribute("fachadaDctoOrdenBean",
                                                          fachadaDctoOrdenBean);

                //
                return "/jsp/vtaFrmAjuResurtidoDespacho.jsp";

            }

            // ajustePositivo
            if (accionContenedor.compareTo("ajustePositivo") == 0) {

                //
                String xIdLocal = request.getParameter("xIdLocal");
                String xIdOrden = request.getParameter("xIdOrden");
                String xIdTipoOrden = request.getParameter("xIdTipoOrden");

                //
                int xIdTipoAjustePositivo = 1;

                //
                FachadaDctoBean fachadaDctoBean = new FachadaDctoBean();

                //
                fachadaDctoBean.setIdLocal(xIdLocal);
                fachadaDctoBean.setIdTipoOrden(xIdTipoOrden);
                fachadaDctoBean.setIdOrden(xIdOrden);
                fachadaDctoBean.setIndicador(xIndicador);
                fachadaDctoBean.setIdTipoAjuste(xIdTipoAjustePositivo);

                /*System.out.println(xIdLocal);
                System.out.println(xIdTipoOrden);
                System.out.println(xIdOrden);
                System.out.println(xIndicador);
                System.out.println(xIdTipoAjustePositivo);*/

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaDctoBean", fachadaDctoBean);

                //
                return "/jsp/vtaFrmSelResurtidoDespacho.jsp";

            }

            // tomar
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
                generaPDFResurtido.setTituloReporte("DESPACHO");

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

                //
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
                FachadaDctoOrdenBean fachadaDctoOrdenBean
                                                   = new FachadaDctoOrdenBean();

                //
                fachadaDctoOrdenBean.setDiasHistoria(xDiasHistoria);
                fachadaDctoOrdenBean.setDiasInventario(xDiasInventario);
                fachadaDctoOrdenBean.setIdCliente(xIdTercero);
                fachadaDctoOrdenBean.setFechaOrden(xFechaCorte);
                fachadaDctoOrdenBean.setIdLocal(xIdLocalUsuario);
                fachadaDctoOrdenBean.setIdTipoOrden(xIdTipoOrdenDespacho);
                fachadaDctoOrdenBean.setIdUsuario(xIdUsuario);
                fachadaDctoOrdenBean.setIdLog(xIdLogActual);

                //
                request.setAttribute("fachadaDctoOrdenBean",
                        fachadaDctoOrdenBean);

                //
                return "/jsp/vtaFrmConResurtidoDespacho.jsp";

            }

            //
            if (accionContenedor.compareTo("Legalizar") == 0) {

                //
                DctoOrdenBean dctoOrdenBean = new DctoOrdenBean();

                //
                dctoOrdenBean.setIdLog(xIdLogActual);
                dctoOrdenBean.setIdTipoOrden(xIdTipoOrdenDespacho + 50);

                // existePedido
                boolean existePedido = dctoOrdenBean.existePedido();

                //
                if (!existePedido) {

                    //
                    Validacion validacion = new Validacion();

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
                FachadaDctoBean fachadaDctoBean = new FachadaDctoBean();


                // confirmaPedido
                ProcesoIngresoDespacho proceso = new ProcesoIngresoDespacho();

                // confirma
                fachadaDctoBean =
                        proceso.ingresa(new Integer(xIdLocalUsuario).intValue(),
                        xIdTipoOrdenDespacho,
                        xIdLogActual,
                        xIdTipoOrdenDespachoProceso, getxIdOrdenOriginal());

                //
                int xIdEstadoTxSinTx = 1;
                int estadoProgramado = 1;
                int tareaVisita = 6;   // Cotizacion


                // finalizaVisita
                agendaLogVisitaBean.setIdLog(xIdLogActual);
                agendaLogVisitaBean.setIdCliente(
                        fachadaDctoBean.getIdCliente());
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
                boolean okLog = agendaLogVisitaBean.finaliza();

                //
                GeneraPDFResurtido generaPDFResurtido = new GeneraPDFResurtido();

                //
                generaPDFResurtido.setIdLocal(fachadaDctoBean.getIdLocalStr());
                generaPDFResurtido.setIdOrden(fachadaDctoBean.getIdOrden());
                generaPDFResurtido.setIdTipoOrden(
                        fachadaDctoBean.getIdTipoOrdenStr());
                generaPDFResurtido.setReporteName("RepEmpresaResurtido");

                //
                generaPDFResurtido.setTituloReporte("DESPACHO");

                //
                generaPDFResurtido.generaPdf(request, response);


            }

            // retira
            if (accionContenedor.compareTo("retira") == 0) {

                //
                String xItem = request.getParameter("xItem");
                String xIdLog = request.getParameter("xIdLog");
                //
                String xIdTercero = request.getParameter("xIdTercero");
                String xFechaCorte = request.getParameter("xFechaCorte");

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
                dctoOrdenDetalleBean.setIdTipoOrden(xIdTipoOrdenAjusteDespachoProceso);
                dctoOrdenDetalleBean.setItem(xItem);
                dctoOrdenDetalleBean.setIdLocal(xIdLocalUsuario);


                // retiraItem
                boolean okRetiro = dctoOrdenDetalleBean.retiraItem();

                // Retira DctoOrden
                dctoOrdenBean.setIdLocal(xIdLocalUsuario);
                dctoOrdenBean.setIdTipoOrden(xIdTipoOrdenAjusteDespachoProceso);
                dctoOrdenBean.setIdOrden(xIdLog);

                // validaArticulosxOrden
                boolean okDetalle = dctoOrdenDetalleBean.validaOrden();


                if (!okDetalle) {

                    // Retira DctoOrden
                    dctoOrdenBean.setIdLocal(xIdLocalUsuario);
                    dctoOrdenBean.setIdTipoOrden(xIdTipoOrdenAjusteDespachoProceso);
                    dctoOrdenBean.setIdOrden(xIdLog);

                    //
                    dctoOrdenBean.retiraOrden();

                }



                //
                dctoOrdenBean.setIdLog(xIdLogActual);
                dctoOrdenBean.setIdTipoOrden(xIdTipoOrdenAjusteDespachoProceso);

                //
                fachadaDctoOrdenBean =
                        dctoOrdenBean.listaDctoOrdenIdLogIdTipoOrden();

                //
                fachadaDctoOrdenBean.setIdListaPrecio(xIdListaPrecio);
                fachadaDctoOrdenBean.setIdCliente(xIdTercero);
                fachadaDctoOrdenBean.setFechaOrden(xFechaCorte);
                fachadaDctoOrdenBean.setIdLog(xIdLogActual);

                //
                request.setAttribute("fachadaDctoOrdenBean", fachadaDctoOrdenBean);


                return "/jsp/vtaFrmIngResurtidoDespacho.jsp";

            }

            // Confirmar
            if (accionContenedor.compareTo("Confirmar") == 0) {

                //
                String xIdTercero = request.getParameter("xIdTercero");
                String xFechaCorte = request.getParameter("xFechaCorte");

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


                    //valida el idTercero sea el mismo para todos
                    String strIdReferencia = arrIdReferencia[indice];
                    int xItemPadre = 0;
                    String xComentario = "ninguno";
                    String xIdResponsable = "0";
                    int xIdClasificacion = 0;
                    int xUnidadMedida = 1;

                    //
                    int maximoItem = proceso.guarda(xIdLogActual,
                            strIdReferencia,
                            xCantidad,
                            xVrVentaUnitario,
                            xItemPadre,
                            xIdTipoOrdenDespachoProceso,
                            xIdUsuario,
                            new Integer(xIdLocalUsuario).intValue(),
                            xIdTercero,
                            xComentario,
                            xIdResponsable,
                            xIdClasificacion,
                            xUnidadMedida);

                }

                //
                DctoOrdenBean dctoOrdenBean = new DctoOrdenBean();

                //
                dctoOrdenBean.setIdLog(fachadaAgendaLogVisitaBean.getIdLog());
                dctoOrdenBean.setIdTipoOrden(xIdTipoOrdenDespachoProceso);

                //
                FachadaDctoOrdenBean fachadaDctoOrdenBean = new FachadaDctoOrdenBean();

                //
                fachadaDctoOrdenBean =
                        dctoOrdenBean.listaDctoOrdenIdLogIdTipoOrden();

                //
                fachadaDctoOrdenBean.setIdListaPrecio(xIdListaPrecio);
                fachadaDctoOrdenBean.setIdCliente(xIdTercero);
                fachadaDctoOrdenBean.setFechaOrden(xFechaCorte);

                //
                request.setAttribute("fachadaDctoOrdenBean", fachadaDctoOrdenBean);

                //
                return "/jsp/vtaFrmIngResurtidoDespacho.jsp";

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
                dctoOrdenBean.setIdTipoOrden(xIdTipoOrdenDespachoProceso);

                //
                FachadaDctoOrdenBean fachadaDctoOrdenBean = new FachadaDctoOrdenBean();
                //
                fachadaDctoOrdenBean =
                        dctoOrdenBean.listaDctoOrdenIdLogIdTipoOrden();

                fachadaDctoOrdenBean.setIdListaPrecio(xIdListaPrecio);
                fachadaDctoOrdenBean.setIdCliente(xIdTercero);
                fachadaDctoOrdenBean.setFechaOrden(xFechaCorte);
                fachadaDctoOrdenBean.setIdLocal(xIdLocalUsuario);

                //
                request.setAttribute("fachadaDctoOrdenBean",
                        fachadaDctoOrdenBean);

                //
                return "/jsp/vtaFrmLstResurtidoDespacho.jsp";

            }

            // Listar

            if (accionContenedor.compareTo("selecciona_traslado") == 0) {
                //
                String xIdLocal = request.getParameter("xIdLocal");
                String xIdTipoOrden = request.getParameter("xIdTipoOrden");
                String xIdLog = request.getParameter("xIdLog");
                String xIdOrdenOrigen = request.getParameter("xIdOrdenOrigen");

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
                colaboraResurtidoOrden.setIdOrdenOrigen(xIdOrdenOrigen);

                //
                fachadaColaboraDctoOrdenBean
                                    = colaboraResurtidoOrden.listaLegalizaFCH();


                fachadaColaboraDctoOrdenBean.setIdOrdenOrigen(xIdOrdenOrigen);

                //
                request.setAttribute("fachadaColaboraDctoOrdenBean",
                        fachadaColaboraDctoOrdenBean);
                //
                return "/jsp/vtaFrmLegResurtidoDespachoTx.jsp";

            }


            //
            if (accionContenedor.compareTo("Finalizar") == 0) {

                //
                String xIdLocal = request.getParameter("xIdLocal");
                String xIdTipoOrden = request.getParameter("xIdTipoOrden");
                String xIdLog = request.getParameter("xIdLog");
                String xIdOrden  = request.getParameter("xIdOrden");
                String xIdOrdenOrigen = request.getParameter("xIdOrdenOrigen");

                //
                String xCantidadArr[] =
                        request.getParameterValues("xCantidad");
                String xVrCostoArr[] =
                        request.getParameterValues("xVrCosto");
                String xVrCostoINDArr[] =
                        request.getParameterValues("xVrCostoIND");
                String xIdPluArr[] = request.getParameterValues("xIdPlu");

                //
                Validacion valida = new Validacion();

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
                    valida.reasignar("VR.COSTO", xVrCostoArr[indice]);

                    //
                    valida.validarCampoDoublePositivo();

                    //isValido
                    if (!valida.isValido()) {

                        //
                        request.setAttribute("validacion", valida);
                        return "/jsp/gralError.jsp";
                    }

                    //
                    valida.reasignar("VR.COSTOIND", xVrCostoINDArr[indice]);

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
                    dctoOrdenDetalleBean.setVrCosto(xVrCostoArr[indice]);
                    dctoOrdenDetalleBean.setVrCostoIND(xVrCostoINDArr[indice]);
                    dctoOrdenDetalleBean.setIdOrdenOrigen(xIdOrdenOrigen);

                    //
                    dctoOrdenDetalleBean.actualizaResurtidoDespacho();

                }

                //
                DctoOrdenBean dctoOrdenBean = new DctoOrdenBean();

                //
                dctoOrdenBean.setIdLog(xIdLog);
                dctoOrdenBean.setIdTipoOrden(xIdTipoOrden);

                // existePedido
                boolean existePedido = dctoOrdenBean.existePedido();

                //
                if (!existePedido) {

                    //
                    Validacion validacion = new Validacion();

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
                FachadaDctoBean fachadaDctoBean = new FachadaDctoBean();


                // confirmaPedido
                ProcesoIngresoDespachoOrigen proceso
                                           = new ProcesoIngresoDespachoOrigen();

                // confirma
                fachadaDctoBean =
                        proceso.ingresa(new Integer(xIdLocalUsuario).intValue(),
                        xIdTipoOrdenDespacho,
                        new Integer(xIdLog).intValue(),
                        xIdTipoOrdenDespacho + 50,
                        new Integer(xIdOrden).intValue());

                //
                int xIdEstadoTxSinTx = 1;
                int estadoProgramado = 1;
                int tareaVisita = 6;   // Cotizacion


                // finalizaVisita
                agendaLogVisitaBean.setIdLog(xIdLogActual);
                agendaLogVisitaBean.setIdCliente(
                        fachadaDctoBean.getIdCliente());
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
                boolean okLog = agendaLogVisitaBean.finaliza();

                //
                GeneraPDFResurtidoDespacho generaPDFResurtido
                                             = new GeneraPDFResurtidoDespacho();

                //
                generaPDFResurtido.setIdLocal(fachadaDctoBean.getIdLocalStr());
                generaPDFResurtido.setIdOrden(fachadaDctoBean.getIdOrden());
                generaPDFResurtido.setIdTipoOrden(
                        fachadaDctoBean.getIdTipoOrdenStr());
                generaPDFResurtido.setIdOrdenOrigen(xIdOrdenOrigen);
                generaPDFResurtido.setReporteName("RepEmpresaResurtido");

                //
                generaPDFResurtido.setTituloReporte("DESPACHO");

                //
                generaPDFResurtido.generaPdf(request, response);

            }


            if (accionContenedor.compareTo("Traer") == 0) {

                //
                String xIdTercero = request.getParameter("xIdLocal");
                String xFechaCorte = request.getParameter("xFechaCorte");

                //
                int xIdLocal = new Integer(xIdTercero).intValue();

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
                //
                FachadaDctoOrdenBean fachadaDctoOrdenBean =
                        new FachadaDctoOrdenBean();

                //
                FachadaLocalIp fachadaLocalIp = new FachadaLocalIp();

                //
                LocalIpBean localIpBean = new LocalIpBean();

                //
                localIpBean.setIdLocal(xIdTercero);

                //
                fachadaLocalIp = localIpBean.listaUnLocal();

                //
                String xHostName = fachadaLocalIp.getIp();
                String xIdPuertoLocal = ":"
                        + fachadaLocalIp.getPuertoHttp();

                //
                String xPagina = xHostName
                        + xIdPuertoLocal
                        + "/Commerce/jsp/"
                        + "txDespachoLocal.jsp"
                        + "?xIdLocalOrigen=" + fachadaLocalIp.getIdLocal()
                        + "&xIdTipoOrden=" + xIdTipoOrdenTrasladoProceso
                        + "&xIdTipoOrdenNew=" + xIdTipoOrdenDespachoProceso
                        + "&xEstado=" + xEstadoSuspendido
                        + "&xIdOrden=" + fachadaDctoOrdenBean.getIdOrden()
                        + "&xIdLocal=" + xIdLocalUsuario;

                //
                ProcesoDespachoEnviaRecibeXML procesoXML =
                        new ProcesoDespachoEnviaRecibeXML(xPagina,
                                                          xIdUsuario,
                                       new Integer(xIdLocalUsuario).intValue());

                //
                procesoXML.setIdUsuario(xIdUsuario);


                //
                procesoXML.start();

                //
                fachadaDctoOrdenBean.setIdLocal(xIdLocalUsuario);
                fachadaDctoOrdenBean.setIdTipoOrden(xIdTipoOrdenDespachoProceso);                
                fachadaDctoOrdenBean.setIdCliente(fachadaLocalIp.getIdLocalStr());

                //
                request.setAttribute("fachadaDctoOrdenBean",
                        fachadaDctoOrdenBean);
                //
                try {
                  Thread.sleep(10000);
                } catch (InterruptedException ex) { }

                //
                return "/jsp/vtaFrmTraeResurtidoDespacho.jsp";

            }


            // Elaborar
            if (accionContenedor.compareTo("Elaborar") == 0) {

                //
                String xIdTercero = request.getParameter("xIdLocal");
                String xFechaCorte = request.getParameter("xFechaCorte");

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

                //
                FachadaDctoOrdenBean fachadaDctoOrdenBean = new FachadaDctoOrdenBean();

                //
                fachadaDctoOrdenBean.setIdLocal(xIdLocalUsuario);
                fachadaDctoOrdenBean.setIdTipoOrden(xIdTipoOrdenDespachoProceso);
                fachadaDctoOrdenBean.setIdUsuario(xIdUsuario);
                fachadaDctoOrdenBean.setIdCliente(xIdTercero);
                fachadaDctoOrdenBean.setFechaOrden(strFechaVisita);
                fachadaDctoOrdenBean.setIdLog(xIdLogActual);


                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaDctoOrdenBean", fachadaDctoOrdenBean);

                //
                return "/jsp/vtaFrmIngResurtidoDespacho.jsp";

            }

        }

        return "/jsp/empty.htm";

    }

    /**
     * @return the xIdOrdenOriginal
     */
    public int getxIdOrdenOriginal() {
        return xIdOrdenOriginal;
    }

    /**
     * @param xIdOrdenOriginal the xIdOrdenOriginal to set
     */
    public void setxIdOrdenOriginal(int xIdOrdenOriginal) {
        this.xIdOrdenOriginal = xIdOrdenOriginal;
    }
}
