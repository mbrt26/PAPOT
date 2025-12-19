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
import com.solucionesweb.lasayudas.ProcesoIngresoResurtido;

// Importa la clase que contiene el Bean de Validacion
import com.solucionesweb.losbeans.utilidades.Validacion;

// Importa la clase que contiene el Day
import com.solucionesweb.losbeans.utilidades.Day;

// Importa la clase que contiene ProcesoGuardaPluOrden
import com.solucionesweb.lasayudas.ProcesoGuardaPluOrden;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * Esta interfaz permite escoger la bodega y luego lista los productos que se tomaran en el autoconsumo /
 * vtaContenedorResurtidoAutoconsumo.jsp /
 *  Este servlet implementa la interface GralManejadorRequest/
 * 
 */

public class VtasAdmResurtidoAutoconsumoManejadorRequest
                                              implements GralManejadorRequest {
 /**
 *  BUTTON--
 * ("Elaborar")-Permite ingresar autoconsumos/vtaContenedorResurtidoAutoconsumo.jsp/
 * ("Listar")-Permite ver un listado de autoconsumos/
 * ("Regresar")-("Salir")-Permite retornar al menu principal/
 * ("+Productos")-Permite buscar productos/
 * ("Confirmar")-Permite confirmar las cantidades de los productos/
 * ("Legalizar")- Permite finalizar un proceso y generar pdf del proceso/
 * 
 * Metodo contructor por defecto, es decir, sin parametros /
 */

    public VtasAdmResurtidoAutoconsumoManejadorRequest() { }

    /**
     * BUTTON PARAMETER--
     * "FechaCorte"-Fecha de inicio del proceso/
     * "BodegaDestino"-Bodega donde se elabora el parametro/
     * "Productos"-Nombre de producto a que se le elaborara el parametro/
     * "Cantidad"-Donde se ingresa cantidad de producto 
     */
    public String generaPdf(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String accionContenedor = request.getParameter("accionContenedor");
        System.out.println("accionContenedor :" + accionContenedor);

        //
        int xIdTipoOrdenDespacho = 3;
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
            Logger.getLogger(VtasAdmResurtidoAutoconsumoManejadorRequest.class.getName()).log(Level.SEVERE, null, ex);
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
        Double xIdUsuario      = usuarioBean.getIdUsuario();


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

             if (accionContenedor.compareTo("Regresar") == 0) {
                return "/jsp/empty.htm";

            }

            // Listar
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
                generaPDFResurtido.setReporteName("RepEmpresaResurtido");

                //
                generaPDFResurtido.setTituloReporte("AUTOCONSUMO");

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
                validacion.reasignar("CENTRO OPERATIVO", xIdTercero);

                //
                validacion.validarCampoDouble();

                // isValido
                if (!validacion.isValido()) {

                    //
                    validacion.setDescripcionError(
                                          "ERROR, FALTA SELECCIONAR CENTRO OPERATIVO");

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
                fachadaDctoOrdenBean.setIdTipoOrden(xIdTipoOrdenDespacho);
                fachadaDctoOrdenBean.setIdUsuario(xIdUsuario);
                fachadaDctoOrdenBean.setIdLog(xIdLogActual);

                //
                request.setAttribute("fachadaDctoOrdenBean",
                        fachadaDctoOrdenBean);

                //
                return "/jsp/vtaFrmConResurtidoAutoconsumo.jsp";

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
                ProcesoIngresoResurtido proceso = new ProcesoIngresoResurtido();

                // confirma
                fachadaDctoBean =
                        proceso.ingresa(new Integer(xIdLocalUsuario).intValue(),
                        xIdTipoOrdenDespacho,
                        xIdLogActual,
                        xIdTipoOrdenDespacho + 50);

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
	        if (accionContenedor.compareTo("retira") == 0 )  {

               //
               String xItem           = request.getParameter("xItem");
               String xIdLog          = request.getParameter("xIdLog");
               //
               String xIdTercero = request.getParameter("xIdTercero");
               String xFechaCorte = request.getParameter("xFechaCorte");

               //
               DctoOrdenBean  dctoOrdenBean = new DctoOrdenBean();

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
               dctoOrdenDetalleBean.setIdTipoOrden(xIdTipoOrdenDespacho);
               dctoOrdenDetalleBean.setItem(xItem);


               // retiraItem
               boolean okRetiro = dctoOrdenDetalleBean.retiraItem();

               // Retira DctoOrden
               dctoOrdenBean.setIdLocal(xIdLocalUsuario);
               dctoOrdenBean.setIdTipoOrden(xIdTipoOrdenDespacho);
               dctoOrdenBean.setIdOrden(xIdLog);

               // validaArticulosxOrden
               boolean okDetalle = dctoOrdenDetalleBean.validaOrden();

               if (!okDetalle) {

                  // Retira DctoOrden
                  dctoOrdenBean.setIdLocal(xIdLocalUsuario);
                  dctoOrdenBean.setIdTipoOrden(xIdTipoOrdenDespacho);
                  dctoOrdenBean.setIdOrden(xIdLog);

                  //
                  dctoOrdenBean.retiraOrden();

               }

               //
               dctoOrdenBean.setIdLog(xIdLogActual);
               dctoOrdenBean.setIdTipoOrden(xIdTipoOrdenDespacho + 50);

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


               return "/jsp/vtaFrmIngResurtidoAutoconsumo.jsp";

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
                            xIdTipoOrdenDespacho + 50,
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
                dctoOrdenBean.setIdTipoOrden(xIdTipoOrdenDespacho + 50);

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
                return "/jsp/vtaFrmIngResurtidoAutoconsumo.jsp";

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
                dctoOrdenBean.setIdTipoOrden(xIdTipoOrdenDespacho + 50);

                //
                FachadaDctoOrdenBean fachadaDctoOrdenBean
                                                   = new FachadaDctoOrdenBean();
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
                return "/jsp/vtaFrmLstResurtidoAutoconsumo.jsp";

            }

            // Listar
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
                fachadaDctoOrdenBean.setIdTipoOrden(xIdTipoOrdenDespacho + 50);
                fachadaDctoOrdenBean.setIdUsuario(xIdUsuario);
                fachadaDctoOrdenBean.setIdCliente(xIdTercero);
                fachadaDctoOrdenBean.setFechaOrden(strFechaVisita);
                fachadaDctoOrdenBean.setIdLog(xIdLogActual);


                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaDctoOrdenBean", fachadaDctoOrdenBean);

                //
                return "/jsp/vtaFrmIngResurtidoAutoconsumo.jsp";

            }

        }

        return "/jsp/empty.htm";

    }
}