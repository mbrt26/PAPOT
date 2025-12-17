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

// Importa la clase ProcesoIngreso
import com.solucionesweb.lasayudas.ProcesoIngresoAjuste;

// Importa la clase que contiene el Bean de Validacion
import com.solucionesweb.losbeans.utilidades.Validacion;

// Importa la clase que contiene el Day
import com.solucionesweb.losbeans.utilidades.Day;

// Importa la clase que contiene ProcesoGuardaPluOrden
import com.solucionesweb.lasayudas.ProcesoGuardaAjuste;

import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * Esta opción que despliega la interfaz MOVIMIENTO AJUSTE para la Materia Prima
 * (M.P.), que es un movimiento de inventario que aumenta o disminuye  la existencia de materia prima./
 * vtaContenedorInventarioAjuste.jsp/
 * 
 * Este servlet implementa la interface GralManejadorRequest /
 */
// 
public class VtasAdmInventarioAjusteManejadorRequest
                                               implements GralManejadorRequest {
    /**
     * BUTTON--
     * ("Regresar")/("Salir")-Permite retornar al menu principal/
     * ("Elaborar")-Inicia ajuste de movimiento interno despues de indicar la fecha del ajuste,
     *                      la bodega del ajuste y finalmente que tipo de movimiento/vtaContenedorInventarioAjuste.jsp/
     * ("+Productos")-Donde se ingresa manualmente los productos que quiere mover/vtaFrmIngInventarioAjuste.jsp/
     * ("Confirmar")-Donde se confirma las cantidades de los articulos ingresados previamente/vtaFrmLstInventarioAjuste.jsp/
     * ("Legalizar")-Finaliza el proceso y genera pdf del movimiento/vtaFrmIngInventarioAjuste.jsp 
     * 
     * Metodo contructor por defecto, es decir, sin parametros/
     */

    public VtasAdmInventarioAjusteManejadorRequest() { }
     /**
     * BUTTON PARAMETER--
     * "Fecha corte"- Fecha de corte de movimiento/
     * "Bodega Origen"- Bodega de origen de movimiento/
     * "Movimiento"- Indicativo de movimiento de ajuste de inventario interno/
     * "Cantidad"- Cantidad de articulos a mover /
     */

    public String generaPdf(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String accionContenedor = request.getParameter("accionContenedor");
        
        //
        System.out.println("accionContenedor :" + accionContenedor);

        //
        int xIdTipoOrdenAjuste         = 0;
        int xIdTipoOrdenAjusteProceso  = xIdTipoOrdenAjuste + 50 ;
        int xEstadoActivo              = 9;
        int xIdListaPrecio             = 1;

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

        //
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
        FachadaAgendaLogVisitaBean fachadaAgendaLogVisitaBean
                                   = new FachadaAgendaLogVisitaBean();

        //
        fachadaAgendaLogVisitaBean =
                agendaLogVisitaBean.seleccionaVisitaEstadoFecha();

        //
        int xIdLogActual       = fachadaAgendaLogVisitaBean.getIdLog();
        int xIdTipoOrdenActual = fachadaAgendaLogVisitaBean.getIdTipoOrden();

        // Validacion de accion relacionada con el formulario requerido
        if (accionContenedor != null) {

            if (accionContenedor.compareTo("Salir") == 0) {
                return "/jsp/empty.htm";

            }

            // Listar
            if (accionContenedor.compareTo("tomar") == 0) {

                //
                String xIdLocal        = request.getParameter("xIdLocal");
                String xIdOrden        = request.getParameter("xIdOrden");
                String xIdTipoOrden    = request.getParameter("xIdTipoOrden");

                //
                GeneraPDFResurtido generaPDFResurtido
                                       = new GeneraPDFResurtido();

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
                String xIdTercero      = request.getParameter("xIdTercero");
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
                fachadaDctoOrdenBean.setIdTipoOrden(xIdTipoOrdenAjuste);
                fachadaDctoOrdenBean.setIdUsuario(xIdUsuario);
                fachadaDctoOrdenBean.setIdLog(xIdLogActual);

                //
                request.setAttribute("fachadaDctoOrdenBean",
                        fachadaDctoOrdenBean);

                //
                return "/jsp/vtaFrmConInventarioAjuste.jsp";

            }

            //
            if (accionContenedor.compareTo("Legalizar") == 0) {

                //
                String xIdTipoOrden     = request.getParameter("xIdTipoOrden");
                String xIdBodegaOrigen  = 
                                        request.getParameter("xIdBodegaOrigen");

                //
                xIdTipoOrdenAjusteProceso
                                        = new Integer(xIdTipoOrden).intValue();

                //
                xIdTipoOrdenAjuste      = new Integer(xIdTipoOrden).intValue() -
                                              50 ;

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

                //
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


                //
                GeneraPDFResurtido generaPDFResurtido
                                             = new GeneraPDFResurtido();

                //
                generaPDFResurtido.setIdLocal(fachadaDctoBean.getIdLocalStr());
                generaPDFResurtido.setIdOrden(fachadaDctoBean.getIdOrden());
                generaPDFResurtido.setIdTipoOrden(
                                           fachadaDctoBean.getIdTipoOrdenStr());
                generaPDFResurtido.setReporteName("RepEmpresaResurtido");

                //
                generaPDFResurtido.setTituloReporte("MOVIMIENTO AJUSTES");

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
	        if (accionContenedor.compareTo("retira") == 0 )  {

               //
               String xItem           = request.getParameter("xItem");
               String xIdLog          = request.getParameter("xIdLog");
               String xIdTercero      = request.getParameter("xIdTercero");
               String xFechaCorte     = request.getParameter("xFechaCorte");
               String xIdBodegaOrigen = request.getParameter("xIdBodegaOrigen");
               String xIdTipoOrden    = request.getParameter("xIdTipoOrden");

               //
               xIdTipoOrdenAjusteProceso
                                      = new Integer(xIdTipoOrden).intValue();

               //
               DctoOrdenBean  dctoOrdenBean   = new DctoOrdenBean();

               //
               dctoOrdenBean.setIdLog(xIdLog);

               FachadaDctoOrdenBean fachadaDctoOrdenBean
                                              = new FachadaDctoOrdenBean();
               fachadaDctoOrdenBean           =
                                            dctoOrdenBean.listaDctoOrdenIdLog();

               //
               DctoOrdenDetalleBean dctoOrdenDetalleBean
                                              = new DctoOrdenDetalleBean();

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
               fachadaDctoOrdenBean.setIdBodegaOrigen(xIdBodegaOrigen);
               fachadaDctoOrdenBean.setIdBodegaDestino(xIdBodegaOrigen);
               fachadaDctoOrdenBean.setIdTipoOrden(xIdTipoOrdenAjusteProceso);

               //
               request.setAttribute("fachadaDctoOrdenBean",
                                                         fachadaDctoOrdenBean);

               //
               return "/jsp/vtaFrmIngInventarioAjuste.jsp";

            }

            // Confirmar
            if (accionContenedor.compareTo("Confirmar") == 0) {

                //
                String xIdTercero = request.getParameter("xIdTercero");
                String xFechaCorte = request.getParameter("xFechaCorte");
                String xIdBodegaOrigen  =
                                        request.getParameter("xIdBodegaOrigen");
                String xIdTipoOrden     = request.getParameter("xIdTipoOrden");

                //
                xIdTipoOrdenAjusteProceso
                                        = new Integer(xIdTipoOrden).intValue();

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
                ProcesoGuardaAjuste proceso = new ProcesoGuardaAjuste();

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
                    int xIdClasificacion = 0;

                    //
                    int maximoItem = proceso.guarda(xIdLogActual,
                            strIdReferencia,
                            xCantidad,
                            xVrVentaUnitario,
                            xItemPadre,
                            new Integer(xIdTipoOrdenAjusteProceso).intValue(),
                            xIdUsuario,
                            new Integer(xIdLocalUsuario).intValue(),
                            xIdTercero,
                            xComentario,
                            new Integer(xIdBodegaOrigen).intValue(),
                            xIdClasificacion);

                }

                //
                DctoOrdenBean dctoOrdenBean = new DctoOrdenBean();

                //
                dctoOrdenBean.setIdLog(fachadaAgendaLogVisitaBean.getIdLog());
                dctoOrdenBean.setIdTipoOrden(xIdTipoOrdenAjusteProceso);

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
                fachadaDctoOrdenBean.setIdBodegaDestino(xIdBodegaOrigen);
                fachadaDctoOrdenBean.setIdTipoOrden(xIdTipoOrdenAjusteProceso);

                //
                request.setAttribute("fachadaDctoOrdenBean",
                                                         fachadaDctoOrdenBean);

                //
                return "/jsp/vtaFrmIngInventarioAjuste.jsp";

            }

            // Productos
            if (accionContenedor.compareTo("+Productos") == 0) {

                //
                String idLinea          = request.getParameter("idLinea");
                String xIdTercero       = request.getParameter("xIdTercero");
                String xFechaCorte      = request.getParameter("xFechaCorte");
                String xIdBodegaOrigen  =
                                        request.getParameter("xIdBodegaOrigen");
                String xIdTipoOrden     = request.getParameter("xIdTipoOrden");

                //
                xIdTipoOrdenAjusteProceso
                                        = new Integer(xIdTipoOrden).intValue();
                
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
                dctoOrdenBean.setIdTipoOrden(xIdTipoOrdenAjusteProceso);

                //
                FachadaDctoOrdenBean fachadaDctoOrdenBean
                                        = new FachadaDctoOrdenBean();
                //
                fachadaDctoOrdenBean    =
                        dctoOrdenBean.listaDctoOrdenIdLogIdTipoOrden();

                fachadaDctoOrdenBean.setIdListaPrecio(xIdListaPrecio);
                fachadaDctoOrdenBean.setIdCliente(xIdTercero);
                fachadaDctoOrdenBean.setFechaOrden(xFechaCorte);
                fachadaDctoOrdenBean.setIdBodegaOrigen(xIdBodegaOrigen);
                fachadaDctoOrdenBean.setIdBodegaDestino(xIdBodegaOrigen);
                fachadaDctoOrdenBean.setIdTipoOrden(xIdTipoOrdenAjusteProceso);

                //
                request.setAttribute("fachadaDctoOrdenBean",
                                                          fachadaDctoOrdenBean);

                //
                return "/jsp/vtaFrmLstInventarioAjuste.jsp";

            }

            // Listar
            if (accionContenedor.compareTo("Elaborar") == 0) {

                //
                String xIdTercero       = request.getParameter("xIdLocal");
                String xFechaCorte      = request.getParameter("xFechaCorte");
                String xIdBodegaOrigen  =
                                        request.getParameter("xIdBodegaOrigen");
                String xIdTipoOrden     =
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
                xIdTipoOrdenAjusteProceso  =
                                     new Integer(xIdTipoOrden).intValue() + 50 ;

                //
                FachadaDctoOrdenBean fachadaDctoOrdenBean
                                                   = new FachadaDctoOrdenBean();

                //
                fachadaDctoOrdenBean.setIdLocal(xIdLocalUsuario);
                fachadaDctoOrdenBean.setIdUsuario(xIdUsuario);
                fachadaDctoOrdenBean.setIdCliente(xIdLocalUsuario);
                fachadaDctoOrdenBean.setFechaOrden(strFechaVisita);
                fachadaDctoOrdenBean.setIdLog(xIdLogActual);
                fachadaDctoOrdenBean.setIdBodegaOrigen(xIdBodegaOrigen);
                fachadaDctoOrdenBean.setIdBodegaDestino(xIdBodegaOrigen);
                fachadaDctoOrdenBean.setIdTipoOrden(xIdTipoOrdenAjusteProceso);

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaDctoOrdenBean", fachadaDctoOrdenBean);

                //
                return "/jsp/vtaFrmIngInventarioAjuste.jsp";

            }
        }

        return "/jsp/empty.htm";

    }
}