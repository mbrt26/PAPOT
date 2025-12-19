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

import com.solucionesweb.lasayudas.ProcesoIngresoAjustaConteo;

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

import com.solucionesweb.losbeans.fachada.FachadaPluBean;

import com.solucionesweb.losbeans.fachada.FachadaColaboraDctoOrdenBean;

// Importa la clase que contiene el Bean de Validacion
import com.solucionesweb.losbeans.utilidades.Validacion;

// Importa la clase que contiene el Day
import com.solucionesweb.losbeans.utilidades.Day;

import com.solucionesweb.lasayudas.ProcesoGuardaPlu;

import com.solucionesweb.losbeans.fachada.FachadaDctoBean;

//
import com.solucionesweb.losbeans.colaboraciones.ColaboraResurtidoOrden;
import com.solucionesweb.losbeans.negocio.DctoOrdenBean;
/**
 * Esta interfaz del sistema permite al usuario traer la informaci�n del inventario actual./
 * vtaContenedorInventarioConteo.jsp /
 * 
 * Este servlet implementa la interface GralManejadorRequest/
 */

public class VtasAdmInventarioConteoManejadorRequest
                                              implements GralManejadorRequest {
    /**
     * BUTTON--
    * ("Regresar")-("Salir")-Permite retornar al menu principal/
    * ("CrearFoto")-Permite al usuario traer la informacion del inventario actual./
    * ("GuardarFoto")-Permite almacenar inventarios/
    * ("TraerFoto")-Permite ver una lista de inventarios/
    * ("Bajar Excel")-Descarga un archivo en una hoja de c�lculo con todos los articulos
    *                   y cantidades que se encuentran en el inventario del sistema/vtaFrmLstInventarioConteo.jsp/
    * ("Subir Excel")-Permite subir un archivo de excel con cambios en el inventario/vtaFrmSubInventarioConteo.jsp
    * ("Validar")-Permite validar invetarios/vtaFrmLstInventarioConteo.jsp
    * ("Legalizar")-Muestra un archivo pdf con el inventario ajustado /
    * 
    *  Metodo contructor por defecto, es decir, sin parametros /
    */

    public VtasAdmInventarioConteoManejadorRequest() {
    }

    //
    public String generaPdf(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {


        //
        String accionContenedor = request.getParameter("accionContenedor");

        //
        System.out.println("accionContenedor :" + accionContenedor);

        //---
        int xIdTipoOrdenInventario        = 6;
        int xIdTipoOrdenInventarioProceso = xIdTipoOrdenInventario + 50;

        //
        HttpSession sesion = request.getSession();

        //
        UsuarioBean usuarioBean           =
                (UsuarioBean) sesion.getAttribute("usuarioBean");

        //
        String xIdLocalUsuario            = usuarioBean.getIdLocalUsuarioStr();
        String xIdUsuario                 = usuarioBean.getIdUsuarioSf0();
        int xEstadoActivo                 = 9;
        int xEstadoSuspendido             = 8;

        //
        int idPeriodo                     = 200611;
        int estadoAtendido                = 1; // visitaActiva
        int estadoProgramada              = 9; // visitaProgramada
        int idEstadoVisita                = 1; // Programada

        //
        Day day                           = new Day();

        //
        String strFechaVisita             = day.getFechaFormateada();

        //
        JhDate jhDate                     = new JhDate();

        //
        String fechaTxHM                  = null;

        //
        try {
            //
            fechaTxHM = jhDate.getDate(4, false);
        } catch (Exception ex) {
            Logger.getLogger(VtasAdmInventarioConteoManejadorRequest.class.getName()).log(Level.SEVERE, null, ex);
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
                                                xIdTipoOrdenInventarioProceso);

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
        if (accionContenedor.compareTo("CrearFoto") == 0) {

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
                                                 xIdTipoOrdenInventarioProceso);

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

            // tomar
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

            // tomar
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
                generaPDFResurtido.setTituloReporte("INVENTARIO AJUSTADO");

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
                fachadaDctoOrdenBean.setIdTipoOrden(xIdTipoOrdenInventario);
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
                
                //
                String xIdPluArr[]      = request.getParameterValues("xIdPlu");

                //
                String xCantidadArr[]   =
                                        request.getParameterValues("xCantidad");

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
                    dctoOrdenDetalleBean.actualizaConteo();

                }

                //
                FachadaDctoBean fachadaDctoBean
                                           = new FachadaDctoBean();

                // confirmaPedido
                ProcesoIngresoAjustaConteo proceso
                                            = new ProcesoIngresoAjustaConteo();

                //
                fachadaDctoBean =  proceso.ingresa(
                                               new Integer(xIdLocal).intValue(),
                                               xIdTipoOrdenInventario,
                                               new Integer(xIdLog).intValue(),
                                               xIdTipoOrdenInventarioProceso);



                //
                GeneraPDFResurtido generaPDF = new GeneraPDFResurtido();

                //
                generaPDF.setIdLocal(xIdLocalUsuario);
                generaPDF.setIdOrden(fachadaDctoBean.getIdOrden());
                generaPDF.setIdTipoOrden(fachadaDctoBean.getIdTipoOrden());
                generaPDF.setTituloReporte("INVENTARIO AJUSTADO");
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
            if (accionContenedor.compareTo("validar") == 0) {

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
                return "/jsp/vtaFrmConInventarioConteo.jsp";

            }
            
            // subirExcel
            if (accionContenedor.compareTo("subirExcel") == 0) {

                //
                String xIdTercero      = request.getParameter("xIdLocal");
                String xIdTipoOrden     = request.getParameter("xIdTipoOrden");
                String xIdLog          = request.getParameter("xIdLog");

                //
                int xDiasHistoria            = 0;
                int xDiasInventario          = 0;

                //
                FachadaDctoOrdenBean fachadaDctoOrdenBean
                                             = new FachadaDctoOrdenBean();

                //
                fachadaDctoOrdenBean.setDiasHistoria(xDiasHistoria);
                fachadaDctoOrdenBean.setDiasInventario(xDiasInventario);
                fachadaDctoOrdenBean.setIdCliente(xIdTercero);
                fachadaDctoOrdenBean.setFechaOrden(strFechaVisita);
                fachadaDctoOrdenBean.setIdLocal(xIdLocalUsuario);
                fachadaDctoOrdenBean.setIdTipoOrden(
                                                 xIdTipoOrdenInventarioProceso);
                fachadaDctoOrdenBean.setIdUsuario(xIdUsuario);
                fachadaDctoOrdenBean.setIdLog(xIdLogActual);

                //
                request.setAttribute("fachadaDctoOrdenBean",
                        fachadaDctoOrdenBean);

                //
                return "/jsp/vtaFrmSubInventarioConteo.jsp";

            }

            //
            if (accionContenedor.compareTo("bajarExcel") == 0) {

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
                response.setContentType("application/vnd.ms-excel");
                response.setHeader("Content-Disposition",
                                             "attachment;filename=archivo.xls");

                //
                return "/jsp/vtaFrmConInventarioConteoArchivo.jsp";

            }

            // TraerFoto
            if (accionContenedor.compareTo("TraerFoto") == 0) {

                //
                int xDiasHistoria      = 0;
                int xDiasInventario    = 0;
                String xFechaCorte     = request.getParameter("xFechaCorte");

                //
                FachadaDctoOrdenBean fachadaDctoOrdenBean
                                                  = new FachadaDctoOrdenBean();

                //
                fachadaDctoOrdenBean.setDiasHistoria(xDiasHistoria);
                fachadaDctoOrdenBean.setDiasInventario(xDiasInventario);
                fachadaDctoOrdenBean.setIdCliente(xIdLocalUsuario);
                fachadaDctoOrdenBean.setFechaOrden(xFechaCorte);
                fachadaDctoOrdenBean.setIdLocal(xIdLocalUsuario);
                fachadaDctoOrdenBean.setIdTipoOrden(
                                                 xIdTipoOrdenInventarioProceso);
                fachadaDctoOrdenBean.setIdUsuario(xIdUsuario);
                fachadaDctoOrdenBean.setIdLog(xIdLogActual);

                //
                request.setAttribute("fachadaDctoOrdenBean",
                        fachadaDctoOrdenBean);

                //
                return "/jsp/vtaFrmLstInventarioConteo.jsp";

            }

            // Confirmar
            if (accionContenedor.compareTo("GuardarFoto") == 0) {

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

                //
                xIdLogActual = new Integer(xIdLog).intValue();

                //
                ProcesoGuardaPlu procesoGuardaPlu = new ProcesoGuardaPlu();

                //
                for (int indice = 0; indice <
                                          xCantidadPedidaArr.length; indice++) {
                    //
                    procesoGuardaPlu.guarda(xIdLogActual,
                                               xIdPluArr[indice],
                                               xCantidadPedidaArr[indice],
                                               "0",
                                               xIdTipoOrdenInventarioProceso,
                                               xIdUsuario,
                                               xIdLocalUsuario,
                                               xIdTercero,
                                               xFechaCorte,
                                               xDiasHistoria,
                                               xDiasInventario);
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
                fachadaDctoOrdenBean.setIdTipoOrden(
                                                 xIdTipoOrdenInventarioProceso);
                fachadaDctoOrdenBean.setIdUsuario(xIdUsuario);
                fachadaDctoOrdenBean.setIdLog(xIdLogActual);

                //
                request.setAttribute("fachadaDctoOrdenBean",
                        fachadaDctoOrdenBean);

                //
                return "/jsp/vtaContenedorInventarioConteo.jsp";

            }

            // Elaborar / CrearFoto
            if ((accionContenedor.compareTo("CrearFoto") == 0)) {
                
                //
                String xIdTercero      = request.getParameter("xIdLocal");
                String xFechaCorte     = request.getParameter("xFechaCorte");
                String xIdLog          = request.getParameter("xIdLog");

                //
                if ((accionContenedor.compareTo("Elaborar") == 0)) {

                   //
                   xIdLogActual  =  new Integer(xIdLog).intValue();

                }

                //
                int xDiasHistoria            = 0;
                int xDiasInventario          = 0;

                //
                FachadaDctoOrdenBean fachadaDctoOrdenBean
                                             = new FachadaDctoOrdenBean();

                //
                fachadaDctoOrdenBean.setDiasHistoria(xDiasHistoria);
                fachadaDctoOrdenBean.setDiasInventario(xDiasInventario);
                fachadaDctoOrdenBean.setIdCliente(xIdLocalUsuario);
                fachadaDctoOrdenBean.setFechaOrden(xFechaCorte);
                fachadaDctoOrdenBean.setIdLocal(xIdLocalUsuario);
                fachadaDctoOrdenBean.setIdTipoOrden(
                                                 xIdTipoOrdenInventarioProceso);
                fachadaDctoOrdenBean.setIdUsuario(xIdUsuario);
                fachadaDctoOrdenBean.setIdLog(xIdLogActual);

                //
                FachadaPluBean fachadaPluBean = new FachadaPluBean();

                //
                int xIdBodega                 = 1;
                int xIdTipo                 = 1;

                //
                fachadaPluBean.setIdBodega(xIdBodega);
                fachadaPluBean.setIdTipo(xIdTipo);

                //
                request.setAttribute("fachadaDctoOrdenBean",
                                      fachadaDctoOrdenBean);
                request.setAttribute("fachadaPluBean",
                                            fachadaPluBean);

                //
                return "/jsp/vtaFrmIngInventarioConteo.jsp";

            }
        }

        return "/jsp/empty.htm";

    }
}