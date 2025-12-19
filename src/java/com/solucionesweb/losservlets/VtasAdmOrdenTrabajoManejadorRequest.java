package com.solucionesweb.losservlets;

// Importa los paquetes del lenguaje especificamente io y los
// que permiten trabajar el servlet.
import co.linxsi.modelo.retencion.retencion_contable.Retencion_Contable_DAO;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Importa la clase que contiene el Day
import com.solucionesweb.losbeans.utilidades.Day;

// Importa la clase que contiene el DctoOrdenDetalleBean
import com.solucionesweb.losbeans.negocio.AgendaLogVisitaBean;

// Importa la clase que contiene el DctoOrdenDetalleBean
import com.solucionesweb.losbeans.negocio.DctoOrdenDetalleBean;

// Importa la clase que contiene el DctoOrdenBean
import com.solucionesweb.losbeans.negocio.DctoOrdenBean;

//
import com.solucionesweb.losbeans.colaboraciones.ColaboraTercero;

// Importa la clase que contiene el FachadaTerceroBean
import com.solucionesweb.losbeans.fachada.FachadaTerceroBean;

// Importa la clase que contiene el ColaboraDctoOrdenDetalleBean
import com.solucionesweb.losbeans.colaboraciones.ColaboraDctoOrdenDetalleBean;

// Importa la clase que contiene el FachadaDctoOrdenBean
import com.solucionesweb.losbeans.fachada.FachadaDctoOrdenBean;

// Importa la clase que contiene el FachadaColaboraHistoriaBean
import com.solucionesweb.losbeans.fachada.FachadaColaboraHistoriaBean;

// Importa la clase que contiene el FachadaDctoOrdenDetalleBean
import com.solucionesweb.losbeans.fachada.FachadaDctoOrdenDetalleBean;

// Importa la clase que contiene el FachadaAgendaProgramacionBean
import com.solucionesweb.losbeans.fachada.FachadaAgendaLogVisitaBean;

// Importa la clase que contiene el Validacion
import com.solucionesweb.losbeans.utilidades.Validacion;

// Importa la clase que contiene el ValidacionUsuarioBean
import com.solucionesweb.losbeans.negocio.UsuarioBean;

// Importa la clase que contiene ProcesoGuardaPluOrden
import com.solucionesweb.lasayudas.ProcesoGuardaOrdenTrabajo;

//
import com.solucionesweb.losbeans.colaboraciones.ColaboraJobEscala;

//
import com.solucionesweb.losbeans.colaboraciones.ColaboraOrdenTrabajo;
import com.solucionesweb.losbeans.fachada.FachadaPluFicha;

//
import com.solucionesweb.losbeans.fachada.FachadaPluBean;

// Importa la clase que contiene el ColaboraPlu
import com.solucionesweb.losbeans.colaboraciones.ColaboraPlu;

// Importa la clase que contiene el ColaboraPlu
import com.solucionesweb.losbeans.fachada.FachadaJobEscala;
import com.solucionesweb.losbeans.negocio.PluFichaBean;
import com.solucionesweb.losbeans.negocio.TerceroBean;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Vector;

/**
 * Esta ventana se pueden agregar articulos para elaborar un pedido/
 * vtaContenedorOrdenTrabajo.jsp /
 *
 * Este servlet implementa la interface GralManejadorRequest /
 */

public class VtasAdmOrdenTrabajoManejadorRequest
        implements GralManejadorRequest {

    /**
     * BUTTON-- ("+Productos")-Permite buscar el o los productos para el pedido
     * / ("Finalizar")-Permite terminar el pedido / ("Crear")-Permite ingresar
     * un nuevo articulo / ("Regresar")-Permite retornar la menu principal /
     * ("ConfirmaReferencia")-Permite confirma el ingreso de la nueva referencia
     * / ("ConfirmarCantidad")-Permite confirmar el ingreso de una nueva
     * cantidad de un articulo o producto / ("Cambiar")-Permite guardar cambios
     * de la referencia / ("Eliminar")-Permite eliminar una referencia /
     *
     * Metodo contructor por defecto, es decir, sin parametros /
     */
    public VtasAdmOrdenTrabajoManejadorRequest() {
    }

    /**
     * BUTTON PARAMETER-- "Cantidad Pedida"-Ingreso de cantidad que necesite el
     * cliente / "Vr.Unitario.Venta"-ingreso del valor de venta del producto /
     * "Unidad Venta"-Ingreso unidades para la venta / "Dias Plazo"-Ingreso de
     * numeros dias de plazo de pago para el cliente / "Responsable Iva"-Si la
     * venta tiene Iva si-no/ "Vendedor"-Seleccione el vendedor que recibe el
     * pedido / "Fecha Entrega"-Fecha entrega del pedido / "Orden
     * Compra"-Ingrese el numero de orden de compra / "Direccion
     * Despacho"-Direccion de donde se envia el pedido/ "Contacto"-Persona que
     * pide el pedido / "Observacion Pedido"-Comentarios del pedido /
     * "Ancho"-parametro de pedido / "Largo"-parametro de pedido / "Color
     * Pelicula"-parametro de pedido / "Calibre"-parametro de pedido /
     * "Fuelle-1"-parametro de pedido / "Fuelle-2"-parametro de pedido / "Tipo
     * embobinado"-parametro de pedido / "Tipo Troquel"-parametro de pedido /
     * "Tipo Solapa"-parametro de pedido / "Terminacion Manija"-parametro de
     * pedido / "Unidad Medida"-parametro de pedido / "Costo
     * Folopolimeros"-parametro de pedido / "%Fotopolimeros clientes"-parametro
     * de pedido / "%Fotopolimeros Plasticos union"-parametro de pedido /
     * "Observarcion"-parametro de pedido / "Destino"-parametro de pedido /
     * "Ref.Cliente"-Ingrese referencia del producto / "Articulos"-ingreso de
     * los articulos/
     *
     * Retorna la URL de la pagina que deber? ser entregada como respuesta
     * (normalmente un pagina jsp).
     */
    public String generaPdf(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        //
        int xIdTipoTerceroCliente = 1;
        int xIdTipoOrdenPedido = 9;
        int xIdTipoOrdenCotizacion = xIdTipoOrdenPedido + 50;

        //
        int estadoActivo = 9;
        String xIdListaPrecio = "1";
        int xEstadoActivo = 9;
        int xIdOperacionPedido = 1;

        //
        Day day = new Day();

        //
        String strFechaVisita = day.getFechaFormateada();

        //
        HttpSession sesion = request.getSession();

        //
        UsuarioBean usuarioBean
                = (UsuarioBean) sesion.getAttribute("usuarioBean");

        //
        String idUsuario = usuarioBean.getIdUsuarioStr();
        int xIdLocalUsuario = usuarioBean.getIdLocalUsuario();

        //
        String accionContenedor = request.getParameter("accionContenedor");

        // Validacion de accion relacionada con el formulario requerido
        if (accionContenedor != null) {

            System.out.println(" accionContenedor " + accionContenedor);

            //
            if (accionContenedor.compareTo("Regresar") == 0) {
                return "/jsp/empty.htm";
            }

            // activaPedido
            if (accionContenedor.compareTo("activaPedido") == 0) {

                //
                String xIdLog = request.getParameter("xIdLog");

                //
                AgendaLogVisitaBean agendaLogVisitaBean = new AgendaLogVisitaBean();

                //
                agendaLogVisitaBean.setIdLog(xIdLog);
                agendaLogVisitaBean.setEstado(xEstadoActivo);
                agendaLogVisitaBean.setFechaVisita(strFechaVisita);
                agendaLogVisitaBean.setIdUsuario(idUsuario);

                //
                boolean xOkActivo
                        = agendaLogVisitaBean.validaEstadoFechaUduario();

                //
                if (xOkActivo) {

                    //
                    Validacion validacion = new Validacion();

                    //
                    validacion.reasignar("DEBE SUSPENDER PEDIDO ACTIVO", "");

                    // Aqui escribe el Bean de Validacio
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralError.jsp";

                }

                //
                agendaLogVisitaBean.setIdLog(xIdLog);
                agendaLogVisitaBean.setEstado(xEstadoActivo);
                agendaLogVisitaBean.setFechaVisita(strFechaVisita);
                agendaLogVisitaBean.setIdUsuario(idUsuario);

                //
                agendaLogVisitaBean.activaLogUsuario();

                //
                FachadaAgendaLogVisitaBean fachadaAgendaLogVisitaBean = new FachadaAgendaLogVisitaBean();

                //
                fachadaAgendaLogVisitaBean
                        = agendaLogVisitaBean.seleccionaVisitaEstadoFechaSql();

                //
                fachadaAgendaLogVisitaBean.setIdUsuario(idUsuario);

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaAgendaLogVisitaBean",
                        fachadaAgendaLogVisitaBean);

                //
                TerceroBean terceroBean = new TerceroBean();

                //
                terceroBean.setIdCliente(
                        fachadaAgendaLogVisitaBean.getIdCliente());
                terceroBean.setIdTipoTercero(xIdTipoTerceroCliente);
                terceroBean.setIdTipoOrden(xIdTipoTerceroCliente);

                //
                FachadaTerceroBean fachadaTerceroBean = new FachadaTerceroBean();

                //
                fachadaTerceroBean = terceroBean.listaUnTerceroFCH();

                //
                fachadaTerceroBean.setIdTipoOrden(xIdTipoOrdenCotizacion);
                fachadaTerceroBean.setIdLocal(xIdLocalUsuario);
                fachadaTerceroBean.setIdLocalTercero(
                        fachadaAgendaLogVisitaBean.getIdLocalTercero());

                //
                request.setAttribute("fachadaTerceroBean", fachadaTerceroBean);

                //
                DctoOrdenBean dctoOrdenBean = new DctoOrdenBean();

                //
                dctoOrdenBean.setIdLog(fachadaAgendaLogVisitaBean.getIdLog());
                dctoOrdenBean.setIdTipoOrden(xIdTipoOrdenCotizacion);

                //
                FachadaDctoOrdenBean fachadaDctoOrdenBean = new FachadaDctoOrdenBean();

                //
                fachadaDctoOrdenBean
                        = dctoOrdenBean.listaDctoOrdenIdLogIdTipoOrden();

                //
                request.setAttribute("fachadaDctoOrdenBean", fachadaDctoOrdenBean);

                //
                return "/jsp/vtaContenedorOrdenTrabajo.jsp";

            }

            //
            if (accionContenedor.compareTo("Imprimir") == 0) {

                //
                String xNumeroOrden = request.getParameter("xNumeroOrden");
                String xItemPadre = request.getParameter("item");

                //
                GeneraPDF_Pedido generaPDFPedido = new GeneraPDF_Pedido();

                //
                generaPDFPedido.setNumeroOrden(xNumeroOrden);
                generaPDFPedido.setItemPadre(xItemPadre);
                generaPDFPedido.setNombreReporte("POTRepOrdenPedido");

                //
                generaPDFPedido.generaPdf(request, response);

            }

            // Eliminar
            if (accionContenedor.compareTo("Eliminar") == 0) {

                //
                String xIdFicha = request.getParameter("xIdFicha");
                String xReferenciaClente
                        = request.getParameter("xReferenciaClente");

                //
                Validacion validacion = new Validacion();

                //
                validacion.reasignar("REFERENCIA CLIENTE", xReferenciaClente);

                //
                validacion.validarCampoString();

                //
                if (validacion.isValido() == false) {

                    // Aqui escribe el Bean de Validacion en el Request para manejar el error
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralError.jsp";
                }

                //--
                DctoOrdenBean dctoOrdenBean = new DctoOrdenBean();

                //
                dctoOrdenBean.setIdFicha(xIdFicha);

                //
                boolean xExisteFichaOT = dctoOrdenBean.existeFichaOT();

                //
                validacion.reasignar("ERROR, EXISTE PRODUCCION PARA ESTA FICHA# " + xIdFicha, "");

                //
                if (xExisteFichaOT) {

                    // Aqui escribe el Bean de Validacion en el Request para manejar el error
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralError.jsp";

                }

                //
                PluFichaBean pluFichaBean = new PluFichaBean();

                //
                pluFichaBean.setIdFicha(xIdFicha);
                pluFichaBean.setReferenciaCliente(xReferenciaClente);

                //---
                pluFichaBean.eliminaReferenciaCliente();

                //
                AgendaLogVisitaBean agendaLogVisitaBean = new AgendaLogVisitaBean();

                //
                agendaLogVisitaBean.setEstado(estadoActivo);
                agendaLogVisitaBean.setFechaVisitaStr(strFechaVisita);
                agendaLogVisitaBean.setIdUsuario(idUsuario);

                //
                FachadaAgendaLogVisitaBean fachadaAgendaLogVisitaBean = new FachadaAgendaLogVisitaBean();

                //
                fachadaAgendaLogVisitaBean
                        = agendaLogVisitaBean.seleccionaVisitaEstadoFecha();

                //
                FachadaColaboraHistoriaBean fachadaColaboraHistoriaBean
                        = new FachadaColaboraHistoriaBean();

                //---
                String xIdLinea = "";
                String xNombrePlu = "";

                //
                fachadaColaboraHistoriaBean.setIdLinea(xIdLinea);
                fachadaColaboraHistoriaBean.setNombrePlu(xNombrePlu);

                //
                request.setAttribute("fachadaColaboraHistoriaBean",
                        fachadaColaboraHistoriaBean);

                //
                request.setAttribute("fachadaAgendaLogVisitaBean",
                        fachadaAgendaLogVisitaBean);

                //
                FachadaTerceroBean fachadaTerceroBean = new FachadaTerceroBean();

                //
                fachadaTerceroBean.setIdCliente(
                        fachadaAgendaLogVisitaBean.getIdCliente());
                fachadaTerceroBean.setIdTipoTercero(xIdTipoTerceroCliente);
                fachadaTerceroBean.setIdTipoOrden(xIdTipoOrdenCotizacion);

                //
                ColaboraTercero colaboraTercero = new ColaboraTercero();

                //
                colaboraTercero.setIdTipoTercero(xIdTipoTerceroCliente);
                colaboraTercero.setIdCliente(
                        fachadaAgendaLogVisitaBean.getIdCliente());
                //
                fachadaTerceroBean = colaboraTercero.listaTerceroFCH();

                //
                fachadaTerceroBean.setIdListaPrecio(xIdListaPrecio);

                //
                fachadaTerceroBean.setIdLocal(xIdLocalUsuario);
                fachadaTerceroBean.setIdListaPrecio(xIdListaPrecio);

                //---
                dctoOrdenBean.setIdLog(fachadaAgendaLogVisitaBean.getIdLog());
                dctoOrdenBean.setIdTipoOrden(xIdTipoOrdenCotizacion);

                //
                FachadaDctoOrdenBean fachadaDctoOrdenBean = new FachadaDctoOrdenBean();

                //
                fachadaDctoOrdenBean
                        = dctoOrdenBean.listaDctoOrdenIdLogIdTipoOrden();

                //
                request.setAttribute("fachadaDctoOrdenBean", fachadaDctoOrdenBean);

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaTerceroBean", fachadaTerceroBean);

                //
                FachadaPluFicha fachadaPluFicha = new FachadaPluFicha();

                //
                request.setAttribute("fachadaPluFicha", fachadaPluFicha);

                //
                return "/jsp/vtaFrmSelOrdenTrabajo.jsp";

            }

            // Cambiar
            if (accionContenedor.compareTo("Cambiar") == 0) {

                //
                String xIdFicha = request.getParameter("xIdFicha");
                String xReferenciaClente
                        = request.getParameter("xReferenciaClente");

                //
                Validacion validacion = new Validacion();

                //
                validacion.reasignar("REFERENCIA CLIENTE", xReferenciaClente);

                //
                validacion.validarCampoString();

                //
                if (validacion.isValido() == false) {

                    // Aqui escribe el Bean de Validacion en el Request para manejar el error
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralError.jsp";
                }

                //
                PluFichaBean pluFichaBean = new PluFichaBean();

                //
                pluFichaBean.setIdFicha(xIdFicha);
                pluFichaBean.setReferenciaCliente(xReferenciaClente);

                //
                pluFichaBean.actualizaReferenciaCliente();

                //
                AgendaLogVisitaBean agendaLogVisitaBean = new AgendaLogVisitaBean();

                //
                agendaLogVisitaBean.setEstado(estadoActivo);
                agendaLogVisitaBean.setFechaVisitaStr(strFechaVisita);
                agendaLogVisitaBean.setIdUsuario(idUsuario);

                //
                FachadaAgendaLogVisitaBean fachadaAgendaLogVisitaBean = new FachadaAgendaLogVisitaBean();

                //
                fachadaAgendaLogVisitaBean
                        = agendaLogVisitaBean.seleccionaVisitaEstadoFecha();

                //
                FachadaColaboraHistoriaBean fachadaColaboraHistoriaBean
                        = new FachadaColaboraHistoriaBean();

                //---
                String xIdLinea = "";
                String xNombrePlu = "";

                //
                fachadaColaboraHistoriaBean.setIdLinea(xIdLinea);
                fachadaColaboraHistoriaBean.setNombrePlu(xNombrePlu);

                //
                request.setAttribute("fachadaColaboraHistoriaBean",
                        fachadaColaboraHistoriaBean);

                //
                request.setAttribute("fachadaAgendaLogVisitaBean",
                        fachadaAgendaLogVisitaBean);

                //
                FachadaTerceroBean fachadaTerceroBean = new FachadaTerceroBean();

                //
                fachadaTerceroBean.setIdCliente(
                        fachadaAgendaLogVisitaBean.getIdCliente());
                fachadaTerceroBean.setIdTipoTercero(xIdTipoTerceroCliente);
                fachadaTerceroBean.setIdTipoOrden(xIdTipoOrdenCotizacion);

                //
                ColaboraTercero colaboraTercero = new ColaboraTercero();

                //
                colaboraTercero.setIdTipoTercero(xIdTipoTerceroCliente);
                colaboraTercero.setIdCliente(
                        fachadaAgendaLogVisitaBean.getIdCliente());
                //
                fachadaTerceroBean = colaboraTercero.listaTerceroFCH();

                //
                fachadaTerceroBean.setIdListaPrecio(xIdListaPrecio);

                //
                fachadaTerceroBean.setIdLocal(xIdLocalUsuario);
                fachadaTerceroBean.setIdListaPrecio(xIdListaPrecio);

                //
                DctoOrdenBean dctoOrdenBean = new DctoOrdenBean();

                //
                dctoOrdenBean.setIdLog(fachadaAgendaLogVisitaBean.getIdLog());
                dctoOrdenBean.setIdTipoOrden(xIdTipoOrdenCotizacion);

                //
                FachadaDctoOrdenBean fachadaDctoOrdenBean = new FachadaDctoOrdenBean();

                //
                fachadaDctoOrdenBean
                        = dctoOrdenBean.listaDctoOrdenIdLogIdTipoOrden();

                //
                request.setAttribute("fachadaDctoOrdenBean", fachadaDctoOrdenBean);

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaTerceroBean", fachadaTerceroBean);

                //
                FachadaPluFicha fachadaPluFicha = new FachadaPluFicha();

                //
                request.setAttribute("fachadaPluFicha", fachadaPluFicha);

                //
                return "/jsp/vtaFrmSelOrdenTrabajo.jsp";

            }

            // modificaretira
            if (accionContenedor.compareTo("modificaretira") == 0) {

                //
                String xIdPlu = request.getParameter("xIdPlu");
                String xIdFicha = request.getParameter("xIdFicha");
                String xIdLog = request.getParameter("xIdLog");

                //
                FachadaPluFicha fachadaPluFicha = new FachadaPluFicha();

                //
                ColaboraOrdenTrabajo colaboraOrdenTrabajo = new ColaboraOrdenTrabajo();

                //
                colaboraOrdenTrabajo.setIdFicha(xIdFicha);

                //
                fachadaPluFicha = colaboraOrdenTrabajo.listaIdFichaFCH();

                //
                request.setAttribute("fachadaPluFicha", fachadaPluFicha);

                //
                FachadaTerceroBean fachadaTerceroBean = new FachadaTerceroBean();

                //
                fachadaTerceroBean.setIdCliente(fachadaPluFicha.getIdCliente());
                fachadaTerceroBean.setIdTipoTercero(xIdTipoTerceroCliente);

                //
                request.setAttribute("fachadaTerceroBean", fachadaTerceroBean);

                //
                return "/jsp/vtaFrmCamOrdenTrabajo.jsp";

            }

            // Confirmar Referencia
            if (accionContenedor.compareTo("Confirmar Referencia") == 0) {

                //
                String xIdLog = request.getParameter("xIdLog");
                String xIdPlu = request.getParameter("xIdPlu");
                String xReferenciaClente
                        = request.getParameter("xReferenciaClente");
                String xComentario
                        = request.getParameter("xComentario");

                //
                Vector vectorBean = new Vector();

                //
                Iterator iteratorBean;

                //--------------------------------------------------------------
                int xItemOcurre = 1;

                ColaboraJobEscala colaboraJobEscala = new ColaboraJobEscala();

                //
                FachadaJobEscala fachadaJobEscala = new FachadaJobEscala();

                //
                FachadaPluFicha fachadaPluFicha;

                //
                Validacion validacion = new Validacion();

                //----------
                Enumeration listaAtributos = request.getParameterNames();

                //
                while (listaAtributos.hasMoreElements()) {

                    //
                    String nombreAtributo = (String) listaAtributos.nextElement();

                    //
                    int xIdEscala;
                    String xTextoEscala = "";

                    try {

                        //
                        xIdEscala = Integer.parseInt(nombreAtributo);

                        //
                        colaboraJobEscala.setIdEscala(nombreAtributo);

                        //
                        fachadaJobEscala = colaboraJobEscala.listaUnFCH();

                        String xItem = request.getParameter(nombreAtributo);

                        //--- asigna texto idTipoEscala = 3
                        if (fachadaJobEscala.getIdTipoEscala() == 3) {

                            //
                            xTextoEscala = xItem;
                            xItem = "0";

                        }

                        //
                        validacion.reasignar(
                                fachadaJobEscala.getNombreEscala(),
                                xItem);

                        //--- valida numericos
                        if (fachadaJobEscala.getIdTipoEscala() == 1) {

                            //
                            validacion.validarCampoDoublePositivo();

                            //
                            if (validacion.isValido() == false) {

                                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                                request.setAttribute("validacion", validacion);
                                return "/jsp/gralError.jsp";
                            }
                        }

                        //
                        fachadaPluFicha = new FachadaPluFicha();

                        //
                        fachadaPluFicha.setIdEscala(xIdEscala);
                        fachadaPluFicha.setVrEscala(xItem);
                        fachadaPluFicha.setItem(xItemOcurre);
                        fachadaPluFicha.setTextoEscala(xTextoEscala);

                        //
                        vectorBean.add(fachadaPluFicha);

                    } catch (NumberFormatException nfe) {
                    }

                }

                //--------------------------------------------------------------
                validacion.reasignar("MATERIAL", xIdPlu);
                //
                validacion.validarCampoDoublePositivo();

                if (validacion.isValido() == false) {

                    // Aqui escribe el Bean de Validacion en el Request para manejar el error
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralError.jsp";
                }

                //
                validacion.reasignar("REFERENCIA CLIENTE", xReferenciaClente);

                //
                validacion.validarCampoString();

                if (validacion.isValido() == false) {

                    // Aqui escribe el Bean de Validacion en el Request para manejar el error
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralError.jsp";
                }

                //
                AgendaLogVisitaBean agendaLogVisitaBean = new AgendaLogVisitaBean();

                //
                agendaLogVisitaBean.setIdLog(xIdLog);

                //
                //
                FachadaAgendaLogVisitaBean fachadaAgendaLogVisitaBean = new FachadaAgendaLogVisitaBean();

                //
                fachadaAgendaLogVisitaBean
                        = agendaLogVisitaBean.listaLogFachada();

                //
                request.setAttribute("fachadaAgendaLogVisitaBean",
                        fachadaAgendaLogVisitaBean);

                //
                String xIdCliente
                        = fachadaAgendaLogVisitaBean.getIdCliente();

                //
                FachadaTerceroBean fachadaTerceroBean = new FachadaTerceroBean();

                //
                ColaboraTercero colaboraTercero = new ColaboraTercero();

                //
                fachadaTerceroBean.setIdTipoOrden(xIdTipoOrdenCotizacion);
                colaboraTercero.setIdTipoTercero(xIdTipoTerceroCliente);
                colaboraTercero.setIdCliente(
                        fachadaAgendaLogVisitaBean.getIdCliente());
                colaboraTercero.setIdLocalTercero(
                        fachadaAgendaLogVisitaBean.getIdLocalTercero());

                //
                if (fachadaAgendaLogVisitaBean.getIdLocalTercero() > 0) {

                    //
                    fachadaTerceroBean
                            = colaboraTercero.listaUnTerceroLocalFCH();
                } else {

                    //
                    fachadaTerceroBean
                            = colaboraTercero.listaTerceroFCH();
                }

                //
                fachadaTerceroBean.setIdTipoOrden(xIdTipoOrdenCotizacion);
                fachadaTerceroBean.setIdLocal(xIdLocalUsuario);

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaTerceroBean", fachadaTerceroBean);

                //
                ColaboraPlu colaboraPlu = new ColaboraPlu();

                //
                colaboraPlu.setIdPlu(xIdPlu);

                //
                FachadaPluBean fachadaPluBean = new FachadaPluBean();

                //
                fachadaPluBean = colaboraPlu.listaUnPluFCH();

                //
                PluFichaBean pluFichaBean = new PluFichaBean();

                pluFichaBean.setIdCliente(xIdCliente);
                pluFichaBean.setReferenciaCliente(xReferenciaClente);

                //
                if (pluFichaBean.validaReferenciaCliente()) {

                    //
                    validacion.reasignar("REFERENCIA YA EXISTE", xReferenciaClente);

                    //
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralError.jsp";

                }

                //
                int xIdMaximaFicha = pluFichaBean.maximaFicha() + 1;

                //
                boolean xOkIngreso = false;

                //
                int xIdOperacionSinFichaTecnica = 1;
                int xEstadoOk = 1;

                //
                iteratorBean = vectorBean.iterator();

                //
                while (iteratorBean.hasNext()) {

                    //
                    fachadaPluFicha = (FachadaPluFicha) iteratorBean.next();

                    //
                    pluFichaBean.setIdPlu(xIdPlu);
                    pluFichaBean.setReferenciaCliente(xReferenciaClente);
                    pluFichaBean.setIdCliente(xIdCliente);
                    pluFichaBean.setIdOperacion(xIdOperacionSinFichaTecnica);
                    pluFichaBean.setReferencia(fachadaPluBean.getReferencia());
                    pluFichaBean.setIdEscala(fachadaPluFicha.getIdEscala());
                    pluFichaBean.setItem(fachadaPluFicha.getItem());
                    pluFichaBean.setVrEscala(fachadaPluFicha.getVrEscala());
                    pluFichaBean.setTextoEscala(
                            fachadaPluFicha.getTextoEscala());
                    pluFichaBean.setEstado(xEstadoOk);
                    pluFichaBean.setIdFicha(xIdMaximaFicha);

                    //
                    xOkIngreso = pluFichaBean.ingresa();

                }

                // ingresaComplemento
                boolean xOkComplemento = pluFichaBean.ingresaComplemento();

                // ingresaComplemento
                boolean xOkComplementoProducto
                        = pluFichaBean.ingresaComplementoProducto();

                // para inventarios productos
                pluFichaBean.actualizaProduccion();

                //
                ColaboraOrdenTrabajo colaboraOrdenTrabajo = new ColaboraOrdenTrabajo();

                //
                colaboraOrdenTrabajo.setIdCliente(xIdCliente);
                colaboraOrdenTrabajo.setReferenciaCliente(xReferenciaClente);

                //
                String xReferencia = colaboraOrdenTrabajo.referencia();

                //
                pluFichaBean.setReferencia(xReferencia);

                //
                xOkIngreso = pluFichaBean.actualizaOperacion();

                //
                xOkIngreso = pluFichaBean.actualizaReferencia();

                //
                if ((xOkIngreso == false) || (xOkComplemento == false)) {

                    //
                    validacion.reasignar("INGRESO PEDIDO", "");
                    validacion.setDescripcionError("ERROR REFERENCIA CLIENTE");

                    // Aqui escribe el Bean de Validacion en el Request para manejar el error
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralError.jsp";
                }

                //
                fachadaPluFicha = new FachadaPluFicha();

                //
                request.setAttribute("fachadaPluFicha", fachadaPluFicha);

                return "/jsp/vtaFrmSelOrdenTrabajo.jsp";

            }

            // Confirmar Cantidad
            if (accionContenedor.compareTo("Confirmar Cantidad") == 0) {

                //
                //String xNumeroOrden = request.getParameter("xNumeroOrden");
                String xOrdenCompra = request.getParameter("xOrdenCompra");
                String xContacto = request.getParameter("xContacto");
                String xDireccionDespacho = request.getParameter("xDireccionDespacho");
                String xObservacion = request.getParameter("xObservacion");
                String xUnidadVenta = request.getParameter("xUnidadVenta");
                String idClasificacion = "1";
                String idResponsable = "1";
                String idLog = request.getParameter("idLog");
                String itemStr = request.getParameter("item");
                String cantidad = request.getParameter("cantidad");
                String vrVentaUnitarioSinIva = request.getParameter("vrVentaUnitarioSinIva");
                String xPorcentajeDescuento = "0.0";
                String xIdVendedor = request.getParameter("xIdVendedor");
                String xIdFormaPago = request.getParameter("xIdFormaPago");
                String xFechaEntrega = request.getParameter("xFechaEntrega");
                String xImpuestoVenta = request.getParameter("xImpuestoVenta");
                String xUnidadMedidaDian =  request.getParameter("xUnidadVenta2");

                //
                Validacion validacion = new Validacion();

                //
                validacion.reasignar("CANTIDAD", cantidad);

                //
                validacion.validarCampoDouble();

                if (validacion.isValido() == false) {

                    // Aqui escribe el Bean de Validacion en el Request para manejar el error
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralError.jsp";
                }

                //
                validacion.reasignar("VR.VENTA UNITARIO", vrVentaUnitarioSinIva);
                validacion.validarCampoDoublePositivo();

                if (validacion.isValido() == false) {

                    // Aqui escribe el Bean de Validacion en el Request para manejar el error
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralError.jsp";
                }

                //
                validacion.reasignar("UNIDAD VENTA", xUnidadVenta);

                //
                validacion.validarCampoDouble();

                if (validacion.isValido() == false) {

                    // Aqui escribe el Bean de Validacion en el Request para manejar el error
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralError.jsp";
                }

                //
                validacion.reasignar("%DESCUENTO", xPorcentajeDescuento);
                validacion.validarCampoDoublePositivo();

                if (validacion.isValido() == false) {

                    // Aqui escribe el Bean de Validacion en el Request para manejar el error
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralError.jsp";
                }

                //
                validacion.reasignar("DIAS PAGO", xIdFormaPago);

                //
                validacion.validarCampoEnteroPositivo();

                if (validacion.isValido() == false) {

                    // Aqui escribe el Bean de Validacion en el Request para manejar el error
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralError.jsp";
                }

                //
                validacion.reasignar("RESPONSABLE IVA", xImpuestoVenta);

                //
                validacion.validarCampoEnteroPositivo();

                if (validacion.isValido() == false) {

                    // Aqui escribe el Bean de Validacion en el Request para manejar el error
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralError.jsp";
                }

                //---
                AgendaLogVisitaBean agendaLogVisitaBean = new AgendaLogVisitaBean();

                //
                agendaLogVisitaBean.setEstado(estadoActivo);
                agendaLogVisitaBean.setFechaVisitaStr(strFechaVisita);
                agendaLogVisitaBean.setIdUsuario(idUsuario);

                //
                FachadaAgendaLogVisitaBean fachadaAgendaLogVisitaBean = new FachadaAgendaLogVisitaBean();

                //
                fachadaAgendaLogVisitaBean
                        = agendaLogVisitaBean.seleccionaVisitaEstadoFecha();

                //
                DctoOrdenBean dctoOrdenBean = new DctoOrdenBean();

                //---
                int xMaximaOT = dctoOrdenBean.maximoOT() + 1;

                //
                int xIdEstado = 1;

                //
                dctoOrdenBean.setIdLog(idLog);
                dctoOrdenBean.setIdTipoOrden(xIdTipoOrdenCotizacion);
                dctoOrdenBean.setEstado(xIdEstado);
                dctoOrdenBean.setDireccionDespacho(xDireccionDespacho);
                dctoOrdenBean.setOrdenCompra(xOrdenCompra);
                dctoOrdenBean.setFechaEntrega(xFechaEntrega);
                dctoOrdenBean.setContacto(xContacto);
                dctoOrdenBean.setObservacion(xObservacion);
                dctoOrdenBean.setNumeroOrden(xMaximaOT);
                dctoOrdenBean.setIdUsuario(idUsuario);
                dctoOrdenBean.setFormaPago(xIdFormaPago);
                dctoOrdenBean.setIdLocal(xIdLocalUsuario);
                dctoOrdenBean.setIdVendedor(xIdVendedor);
                dctoOrdenBean.setImpuestoVenta(xImpuestoVenta);

                //
                int xIdResponsableIva = new Integer(xImpuestoVenta).intValue();

                //
                dctoOrdenBean.cambiaOT();

                //
                dctoOrdenBean.actualizaOT();

                //
                FachadaDctoOrdenBean fachadaDctoOrdenBean = new FachadaDctoOrdenBean();

                //
                fachadaDctoOrdenBean
                        = dctoOrdenBean.listaDctoOrdenIdLogIdTipoOrden();

                //
                String xIdTipoOrden
                        = fachadaDctoOrdenBean.getIdTipoOrdenStr();
                String xIdOrden
                        = fachadaDctoOrdenBean.getIdOrdenStr();
                String xItem = itemStr;
                int xIdFicha = fachadaDctoOrdenBean.getIdFicha();
                double xPorcentajeIva = 19.0;

                //
                DctoOrdenDetalleBean dctoOrdenDetalleBean = new DctoOrdenDetalleBean();

                //
                dctoOrdenDetalleBean.setItem(itemStr);
                dctoOrdenDetalleBean.setCantidad(cantidad);
                dctoOrdenDetalleBean.setVrVentaUnitarioSinIva(
                        vrVentaUnitarioSinIva);
                dctoOrdenDetalleBean.setIdClasificacion(idClasificacion);
                dctoOrdenDetalleBean.setIdResponsable(idResponsable);
                dctoOrdenDetalleBean.setPorcentajeDscto(xPorcentajeDescuento);
                dctoOrdenDetalleBean.setFechaEntrega(xFechaEntrega);
                dctoOrdenDetalleBean.setUnidadVenta(xUnidadVenta);
                dctoOrdenDetalleBean.setPorcentajeIva(xPorcentajeIva);
                dctoOrdenDetalleBean.setUnidadDian(Integer.parseInt(xUnidadMedidaDian));
                

                //
                dctoOrdenDetalleBean.modifica(
                        new Integer(xIdLocalUsuario).toString(),
                        xIdTipoOrden,
                        xIdOrden,
                        xItem);

                // C.I. No Responsable IVA
                if (xIdResponsableIva != 0) {

                    //
                    dctoOrdenDetalleBean.modificaCI_NoIva(
                            new Integer(xIdLocalUsuario).toString(),
                            xIdTipoOrden,
                            xIdOrden,
                            xItem);

                }

                //---
                FachadaTerceroBean fachadaTerceroBean = new FachadaTerceroBean();

                //
                ColaboraTercero colaboraTercero = new ColaboraTercero();

                //
                fachadaTerceroBean.setIdTipoOrden(xIdTipoOrdenCotizacion);
                colaboraTercero.setIdTipoTercero(xIdTipoTerceroCliente);
                colaboraTercero.setIdCliente(
                        fachadaAgendaLogVisitaBean.getIdCliente());
                colaboraTercero.setIdLocalTercero(
                        fachadaAgendaLogVisitaBean.getIdLocalTercero());

                //
                String xIdCliente = fachadaAgendaLogVisitaBean.getIdCliente();

                //
                if (fachadaAgendaLogVisitaBean.getIdLocalTercero() > 0) {

                    //
                    fachadaTerceroBean
                            = colaboraTercero.listaUnTerceroLocalFCH();
                } else {

                    //
                    fachadaTerceroBean
                            = colaboraTercero.listaTerceroFCH();
                }

                //--------------------------------------------------------------
                ColaboraJobEscala colaboraJobEscala = new ColaboraJobEscala();

                //
                FachadaJobEscala fachadaJobEscala = new FachadaJobEscala();

                //
                FachadaPluFicha fachadaPluFicha;

                //--------------------------------------------------------------
                String xIdEscala = "";
                String xVrEscala = "";
                String xTextoEscala = "";
                xItem = "";
                String xNombreEscala = "";
                String strCaracter = "~";
                int intLugarUno = 0;
                int intLugarDos = 0;
                int intLongitud = 0;

                //
                Enumeration listaAtributos = request.getParameterNames();

                //
                Vector vectorBean = new Vector();

                //
                Iterator iteratorBean;

                //
                while (listaAtributos.hasMoreElements()) {

                    //
                    String nombreAtributo = (String) listaAtributos.nextElement();

                    //
                    intLongitud = nombreAtributo.length();
                    intLugarUno = nombreAtributo.indexOf(strCaracter);
                    intLugarDos = nombreAtributo.indexOf(strCaracter, intLugarUno + 1);

                    //
                    if ((intLugarUno > 0) && (intLugarDos > 0)) {

                        //
                        xIdEscala = nombreAtributo.substring(0, intLugarUno);
                        xItem = nombreAtributo.substring(intLugarUno + 1, intLugarDos);
                        xNombreEscala = nombreAtributo.substring(intLugarDos + 1, intLongitud);

                        //
                        xVrEscala = request.getParameter(nombreAtributo);

                        try {

                            double valor = Double.parseDouble(xVrEscala);

                            xTextoEscala = "";

                        } catch (NumberFormatException nfe) {
                            xTextoEscala = xVrEscala;
                            xVrEscala = "0";

                        }

                        //
                        fachadaPluFicha = new FachadaPluFicha();

                        //
                        fachadaPluFicha.setIdEscala(xIdEscala);
                        fachadaPluFicha.setVrEscala(xVrEscala);
                        fachadaPluFicha.setItem(xItem);
                        fachadaPluFicha.setTextoEscala(xTextoEscala);

                        //
                        vectorBean.add(fachadaPluFicha);

                    }
                }

                //
                boolean xOkIngreso = false;

                //
                int xEstadoOk = 1;

                //
                PluFichaBean pluFichaBean = new PluFichaBean();

                //
                iteratorBean = vectorBean.iterator();

                //
                while (iteratorBean.hasNext()) {

                    //
                    fachadaPluFicha = (FachadaPluFicha) iteratorBean.next();

                    //
                    pluFichaBean.setIdCliente(xIdCliente);
                    pluFichaBean.setIdFicha(xIdFicha);
                    pluFichaBean.setIdOperacion(xIdOperacionPedido);
                    pluFichaBean.setIdEscala(fachadaPluFicha.getIdEscala());
                    pluFichaBean.setItem(fachadaPluFicha.getItem());
                    pluFichaBean.setVrEscala(
                            fachadaPluFicha.getVrEscala());
                    pluFichaBean.setTextoEscala(
                            fachadaPluFicha.getTextoEscala());
                    pluFichaBean.setEstado(xEstadoOk);

                    //
                    if (fachadaPluFicha.getTextoEscala().trim().length() > 0) {

                        //
                        xOkIngreso = pluFichaBean.actualizaTextoEscala();

                    } else {

                        //
                        xOkIngreso = pluFichaBean.actualizaVrEscala();
                    }
                }

                //
                fachadaTerceroBean.setIdTipoOrden(xIdTipoOrden);
                fachadaTerceroBean.setIdLocal(xIdLocalUsuario);

                //
                request.setAttribute("fachadaDctoOrdenBean", fachadaDctoOrdenBean);

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaTerceroBean", fachadaTerceroBean);

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaAgendaLogVisitaBean",
                        fachadaAgendaLogVisitaBean);

                //
                fachadaPluFicha = new FachadaPluFicha();

                //
                fachadaPluFicha.setIdFicha(xIdFicha);

                //
                request.setAttribute("fachadaPluFicha", fachadaPluFicha);

                //
                return "/jsp/vtaContenedorOrdenTrabajo.jsp";

            }

            // ModificarItem
            if (accionContenedor.compareTo("ModificarItem") == 0) {

                //
                AgendaLogVisitaBean agendaLogVisitaBean = new AgendaLogVisitaBean();

                //
                agendaLogVisitaBean.setEstado(estadoActivo);
                agendaLogVisitaBean.setFechaVisitaStr(strFechaVisita);
                agendaLogVisitaBean.setIdUsuario(idUsuario);

                //
                FachadaAgendaLogVisitaBean fachadaAgendaLogVisitaBean = new FachadaAgendaLogVisitaBean();

                //
                fachadaAgendaLogVisitaBean
                        = agendaLogVisitaBean.seleccionaVisitaEstadoFecha();

                //
                int idLog = fachadaAgendaLogVisitaBean.getIdLog();

                //
                FachadaDctoOrdenBean fachadaDctoOrdenBean = new FachadaDctoOrdenBean();

                //
                DctoOrdenBean dctoOrdenBean = new DctoOrdenBean();

                //
                dctoOrdenBean.setIdLog(idLog);

                //
                fachadaDctoOrdenBean
                        = dctoOrdenBean.listaDctoOrdenIdLog();

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaDctoOrdenBean", fachadaDctoOrdenBean);

                //
                String itemStr = request.getParameter("item");

                //
                FachadaDctoOrdenDetalleBean fachadaDctoOrdenDetalleBean
                        = new FachadaDctoOrdenDetalleBean();

                //
                ColaboraDctoOrdenDetalleBean colaboraDctoOrdenDetalleBean
                        = new ColaboraDctoOrdenDetalleBean();

                //
                colaboraDctoOrdenDetalleBean.setItem(itemStr);
                colaboraDctoOrdenDetalleBean.setIdTipoOrden(
                        xIdTipoOrdenCotizacion);

                //
                fachadaDctoOrdenDetalleBean
                        = colaboraDctoOrdenDetalleBean.itemLogFachada(idLog);

                //
                fachadaDctoOrdenDetalleBean.setItem(itemStr);
                fachadaDctoOrdenDetalleBean.getCantidad();

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaDctoOrdenDetalleBean",
                        fachadaDctoOrdenDetalleBean);

                //
                request.setAttribute("fachadaAgendaLogVisitaBean",
                        fachadaAgendaLogVisitaBean);

                //
                FachadaTerceroBean fachadaTerceroBean = new FachadaTerceroBean();

                //
                ColaboraTercero colaboraTercero = new ColaboraTercero();

                //
                fachadaTerceroBean.setIdTipoOrden(xIdTipoOrdenCotizacion);
                colaboraTercero.setIdTipoTercero(xIdTipoTerceroCliente);
                colaboraTercero.setIdCliente(
                        fachadaAgendaLogVisitaBean.getIdCliente());
                colaboraTercero.setIdLocalTercero(
                        fachadaAgendaLogVisitaBean.getIdLocalTercero());

                //
                if (fachadaAgendaLogVisitaBean.getIdLocalTercero() > 0) {

                    //
                    fachadaTerceroBean
                            = colaboraTercero.listaUnTerceroLocalFCH();
                } else {

                    //
                    fachadaTerceroBean
                            = colaboraTercero.listaTerceroFCH();
                }

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaTerceroBean", fachadaTerceroBean);

                //
                FachadaPluFicha fachadaPluFicha = new FachadaPluFicha();

                //
                request.setAttribute("fachadaPluFicha", fachadaPluFicha);

                //
                return "/jsp/vtaFrmModOrdenTrabajo.jsp";

            }

            // ModificarItem
            if (accionContenedor.compareTo("Crear") == 0) {

                //
                AgendaLogVisitaBean agendaLogVisitaBean = new AgendaLogVisitaBean();

                //
                agendaLogVisitaBean.setEstado(estadoActivo);
                agendaLogVisitaBean.setFechaVisitaStr(strFechaVisita);
                agendaLogVisitaBean.setIdUsuario(idUsuario);

                //
                FachadaAgendaLogVisitaBean fachadaAgendaLogVisitaBean = new FachadaAgendaLogVisitaBean();

                //
                fachadaAgendaLogVisitaBean
                        = agendaLogVisitaBean.seleccionaVisitaEstadoFecha();

                //
                FachadaPluFicha fachadaPluFicha = new FachadaPluFicha();

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaPluFicha", fachadaPluFicha);

                //
                request.setAttribute("fachadaAgendaLogVisitaBean",
                        fachadaAgendaLogVisitaBean);

                //
                FachadaTerceroBean fachadaTerceroBean = new FachadaTerceroBean();

                //
                ColaboraTercero colaboraTercero = new ColaboraTercero();

                //
                fachadaTerceroBean.setIdTipoOrden(xIdTipoOrdenCotizacion);
                colaboraTercero.setIdTipoTercero(xIdTipoTerceroCliente);
                colaboraTercero.setIdCliente(
                        fachadaAgendaLogVisitaBean.getIdCliente());
                colaboraTercero.setIdLocalTercero(
                        fachadaAgendaLogVisitaBean.getIdLocalTercero());

                //
                if (fachadaAgendaLogVisitaBean.getIdLocalTercero() > 0) {

                    //
                    fachadaTerceroBean
                            = colaboraTercero.listaUnTerceroLocalFCH();
                } else {

                    //
                    fachadaTerceroBean
                            = colaboraTercero.listaTerceroFCH();
                }

                //
                FachadaPluBean fachadaPluBean = new FachadaPluBean();

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaPluBean", fachadaPluBean);

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaTerceroBean", fachadaTerceroBean);

                //
                return "/jsp/vtaFrmCreOrdenTrabajo.jsp";

            }

            // Validar
            if (accionContenedor.compareTo("+Productos") == 0) {

                //
                String idLinea = request.getParameter("idLinea");

                //
                String strCadena = idLinea.trim();
                int lonCadena = strCadena.length();
                int posCadena = strCadena.indexOf('+', 0);
                String xNombrePlu = "";

                //
                if (posCadena > 0) {

                    //
                    idLinea = strCadena.substring(0, posCadena).trim();
                    xNombrePlu
                            = strCadena.substring(posCadena + 1, lonCadena).trim();

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
                AgendaLogVisitaBean agendaLogVisitaBean = new AgendaLogVisitaBean();

                //
                agendaLogVisitaBean.setEstado(estadoActivo);
                agendaLogVisitaBean.setFechaVisitaStr(strFechaVisita);
                agendaLogVisitaBean.setIdUsuario(idUsuario);

                //
                FachadaAgendaLogVisitaBean fachadaAgendaLogVisitaBean = new FachadaAgendaLogVisitaBean();

                //
                fachadaAgendaLogVisitaBean
                        = agendaLogVisitaBean.seleccionaVisitaEstadoFecha();

                //
                FachadaColaboraHistoriaBean fachadaColaboraHistoriaBean
                        = new FachadaColaboraHistoriaBean();

                //
                fachadaColaboraHistoriaBean.setIdLinea(idLinea);
                fachadaColaboraHistoriaBean.setNombrePlu(xNombrePlu);

                //
                request.setAttribute("fachadaColaboraHistoriaBean",
                        fachadaColaboraHistoriaBean);
                request.setAttribute("fachadaAgendaLogVisitaBean",
                        fachadaAgendaLogVisitaBean);

                //
                FachadaTerceroBean fachadaTerceroBean = new FachadaTerceroBean();

                //
                fachadaTerceroBean.setIdCliente(
                        fachadaAgendaLogVisitaBean.getIdCliente());
                fachadaTerceroBean.setIdTipoTercero(xIdTipoTerceroCliente);
                fachadaTerceroBean.setIdTipoOrden(xIdTipoOrdenCotizacion);

                //
                ColaboraTercero colaboraTercero = new ColaboraTercero();

                //
                colaboraTercero.setIdTipoTercero(xIdTipoTerceroCliente);
                colaboraTercero.setIdCliente(
                        fachadaAgendaLogVisitaBean.getIdCliente());
                //
                fachadaTerceroBean = colaboraTercero.listaTerceroFCH();

                //
                fachadaTerceroBean.setIdListaPrecio(xIdListaPrecio);

                //
                fachadaTerceroBean.setIdLocal(xIdLocalUsuario);
                fachadaTerceroBean.setIdListaPrecio(xIdListaPrecio);

                //
                DctoOrdenBean dctoOrdenBean = new DctoOrdenBean();

                //
                dctoOrdenBean.setIdLog(fachadaAgendaLogVisitaBean.getIdLog());
                dctoOrdenBean.setIdTipoOrden(xIdTipoOrdenCotizacion);

                //
                FachadaDctoOrdenBean fachadaDctoOrdenBean = new FachadaDctoOrdenBean();

                //
                fachadaDctoOrdenBean
                        = dctoOrdenBean.listaDctoOrdenIdLogIdTipoOrden();

                //
                request.setAttribute("fachadaDctoOrdenBean", fachadaDctoOrdenBean);

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaTerceroBean", fachadaTerceroBean);

                //
                FachadaPluFicha fachadaPluFicha = new FachadaPluFicha();

                //
                request.setAttribute("fachadaPluFicha", fachadaPluFicha);

                //
                return "/jsp/vtaFrmSelOrdenTrabajo.jsp";

            }

            // Crear
            if (accionContenedor.compareTo("+Crear") == 0) {

                //
                String idLinea = request.getParameter("idLinea");

                //
                String strCadena = idLinea.trim();
                int lonCadena = strCadena.length();
                int posCadena = strCadena.indexOf('+', 0);
                String xNombrePlu = "";

                //
                if (posCadena > 0) {

                    //
                    idLinea = strCadena.substring(0, posCadena).trim();
                    xNombrePlu
                            = strCadena.substring(posCadena + 1, lonCadena).trim();

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
                AgendaLogVisitaBean agendaLogVisitaBean = new AgendaLogVisitaBean();

                //
                agendaLogVisitaBean.setEstado(estadoActivo);
                agendaLogVisitaBean.setFechaVisitaStr(strFechaVisita);
                agendaLogVisitaBean.setIdUsuario(idUsuario);

                //
                FachadaAgendaLogVisitaBean fachadaAgendaLogVisitaBean = new FachadaAgendaLogVisitaBean();

                //
                fachadaAgendaLogVisitaBean
                        = agendaLogVisitaBean.seleccionaVisitaEstadoFecha();

                //
                FachadaColaboraHistoriaBean fachadaColaboraHistoriaBean
                        = new FachadaColaboraHistoriaBean();

                //
                fachadaColaboraHistoriaBean.setIdLinea(idLinea);
                fachadaColaboraHistoriaBean.setNombrePlu(xNombrePlu);

                //
                request.setAttribute("fachadaColaboraHistoriaBean",
                        fachadaColaboraHistoriaBean);
                request.setAttribute("fachadaAgendaLogVisitaBean",
                        fachadaAgendaLogVisitaBean);

                //
                FachadaTerceroBean fachadaTerceroBean = new FachadaTerceroBean();

                //
                fachadaTerceroBean.setIdCliente(
                        fachadaAgendaLogVisitaBean.getIdCliente());
                fachadaTerceroBean.setIdTipoTercero(xIdTipoTerceroCliente);
                fachadaTerceroBean.setIdTipoOrden(xIdTipoOrdenCotizacion);

                //
                ColaboraTercero colaboraTercero = new ColaboraTercero();

                //
                colaboraTercero.setIdTipoTercero(xIdTipoTerceroCliente);
                colaboraTercero.setIdCliente(
                        fachadaAgendaLogVisitaBean.getIdCliente());
                //
                fachadaTerceroBean = colaboraTercero.listaTerceroFCH();

                //
                fachadaTerceroBean.setIdListaPrecio(xIdListaPrecio);

                //
                fachadaTerceroBean.setIdLocal(xIdLocalUsuario);
                fachadaTerceroBean.setIdListaPrecio(xIdListaPrecio);

                //
                DctoOrdenBean dctoOrdenBean = new DctoOrdenBean();

                //
                dctoOrdenBean.setIdLog(fachadaAgendaLogVisitaBean.getIdLog());
                dctoOrdenBean.setIdTipoOrden(xIdTipoOrdenCotizacion);

                //
                FachadaDctoOrdenBean fachadaDctoOrdenBean = new FachadaDctoOrdenBean();

                //
                fachadaDctoOrdenBean
                        = dctoOrdenBean.listaDctoOrdenIdLogIdTipoOrden();

                //
                request.setAttribute("fachadaDctoOrdenBean",
                        fachadaDctoOrdenBean);

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaTerceroBean", fachadaTerceroBean);

                //
                FachadaPluBean fachadaPluBean = new FachadaPluBean();

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaPluBean", fachadaPluBean);

                //
                FachadaPluFicha fachadaPluFicha = new FachadaPluFicha();

                //
                request.setAttribute("fachadaPluFicha", fachadaPluFicha);

                //
                return "/jsp/vtaFrmMatOrdenTrabajo.jsp";

            }

            // confirmaMaterial
            if (accionContenedor.compareTo("confirmaMaterial") == 0) {

                //
                String xIdPlu = request.getParameter("xIdPlu");
                String xReferenciaCliente
                        = request.getParameter("xReferenciaCliente");

                //
                String xCantidadStr = "1";
                String xVrVentaUnitarioStr = "1";

                //
                AgendaLogVisitaBean agendaLogVisitaBean = new AgendaLogVisitaBean();

                //
                agendaLogVisitaBean.setEstado(estadoActivo);
                agendaLogVisitaBean.setFechaVisitaStr(strFechaVisita);
                agendaLogVisitaBean.setIdUsuario(idUsuario);

                //
                FachadaAgendaLogVisitaBean fachadaAgendaLogVisitaBean = new FachadaAgendaLogVisitaBean();

                //
                fachadaAgendaLogVisitaBean
                        = agendaLogVisitaBean.seleccionaVisitaEstadoFecha();

                //
                int idLog
                        = fachadaAgendaLogVisitaBean.getIdLog();
                double xIdUsuario
                        = fachadaAgendaLogVisitaBean.getIdUsuario();
                String xIdCliente
                        = fachadaAgendaLogVisitaBean.getIdCliente();
                int xIdLocalTercero
                        = fachadaAgendaLogVisitaBean.getIdLocalTercero();

                // Retorna a seleccionar cliente
                if (fachadaAgendaLogVisitaBean.getIdCliente() == null) {

                    request.setAttribute("fachadaAgendaLogVisitaBean",
                            fachadaAgendaLogVisitaBean);
                    //
                    fachadaAgendaLogVisitaBean.setIdCliente(xIdCliente);

                    return "/jsp/vtaContenedorEmpresaSelecciona.jsp";

                }

                //
                FachadaTerceroBean fachadaTerceroBean = new FachadaTerceroBean();

                //
                fachadaTerceroBean.setIdCliente(xIdCliente);
                fachadaTerceroBean.setIdTipoTercero(xIdTipoTerceroCliente);
                fachadaTerceroBean.setIdTipoOrden(xIdTipoOrdenCotizacion);

                //
                ColaboraTercero colaboraTercero = new ColaboraTercero();

                //
                colaboraTercero.setIdTipoTercero(xIdTipoTerceroCliente);
                colaboraTercero.setIdCliente(
                        fachadaAgendaLogVisitaBean.getIdCliente());

                //
                fachadaTerceroBean = colaboraTercero.listaTerceroFCH();

                //
                fachadaTerceroBean.setIdTipoOrden(xIdTipoOrdenCotizacion);
                fachadaTerceroBean.setIdLocal(xIdLocalUsuario);
                fachadaTerceroBean.setIdLocalTercero(xIdLocalTercero);

                //
                DctoOrdenBean dctoOrdenBean = new DctoOrdenBean();

                //
                dctoOrdenBean.setIdLog(fachadaAgendaLogVisitaBean.getIdLog());
                dctoOrdenBean.setIdTipoOrden(xIdTipoOrdenCotizacion);

                //
                FachadaDctoOrdenBean fachadaDctoOrdenBean = new FachadaDctoOrdenBean();

                //
                fachadaDctoOrdenBean
                        = dctoOrdenBean.listaDctoOrdenIdLogIdTipoOrden();

                //
                request.setAttribute("fachadaDctoOrdenBean",
                        fachadaDctoOrdenBean);

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaTerceroBean", fachadaTerceroBean);

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaAgendaLogVisitaBean",
                        fachadaAgendaLogVisitaBean);

                //
                ColaboraPlu colaboraPlu = new ColaboraPlu();

                colaboraPlu.setIdPlu(xIdPlu);

                //
                FachadaPluBean fachadaPluBean = new FachadaPluBean();

                //
                fachadaPluBean = colaboraPlu.listaUnPluFCH();

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaPluBean", fachadaPluBean);

                //
                FachadaColaboraHistoriaBean fachadaColaboraHistoriaBean
                        = new FachadaColaboraHistoriaBean();

                //
                fachadaColaboraHistoriaBean.setIdLinea("");
                fachadaColaboraHistoriaBean.setNombrePlu("");

                //
                request.setAttribute("fachadaColaboraHistoriaBean",
                        fachadaColaboraHistoriaBean);

                //----------
                FachadaPluFicha fachadaPluFicha = new FachadaPluFicha();

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaPluFicha", fachadaPluFicha);

                //
                fachadaTerceroBean.setIdTipoOrden(xIdTipoOrdenCotizacion);
                colaboraTercero.setIdTipoTercero(xIdTipoTerceroCliente);
                colaboraTercero.setIdCliente(
                        fachadaAgendaLogVisitaBean.getIdCliente());
                colaboraTercero.setIdLocalTercero(
                        fachadaAgendaLogVisitaBean.getIdLocalTercero());

                //
                if (fachadaAgendaLogVisitaBean.getIdLocalTercero() > 0) {

                    //
                    fachadaTerceroBean
                            = colaboraTercero.listaUnTerceroLocalFCH();
                } else {

                    //
                    fachadaTerceroBean
                            = colaboraTercero.listaTerceroFCH();
                }

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaTerceroBean", fachadaTerceroBean);

                //
                return "/jsp/vtaFrmCreOrdenTrabajo.jsp";
            }

            // Confirmar
            if (accionContenedor.compareTo("Confirmar") == 0) {

                //
                String xIdPlu = request.getParameter("xIdPlu");
                String xIdFicha = request.getParameter("xIdFicha");

                //
                String xCantidadStr = "1";
                String xVrVentaUnitarioStr = "1";
                double xUnidadVenta = 1;

                //
                AgendaLogVisitaBean agendaLogVisitaBean = new AgendaLogVisitaBean();

                //
                agendaLogVisitaBean.setEstado(estadoActivo);
                agendaLogVisitaBean.setFechaVisitaStr(strFechaVisita);
                agendaLogVisitaBean.setIdUsuario(idUsuario);

                //
                FachadaAgendaLogVisitaBean fachadaAgendaLogVisitaBean = new FachadaAgendaLogVisitaBean();

                //
                fachadaAgendaLogVisitaBean
                        = agendaLogVisitaBean.seleccionaVisitaEstadoFecha();

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaAgendaLogVisitaBean",
                        fachadaAgendaLogVisitaBean);

                //
                int idLog
                        = fachadaAgendaLogVisitaBean.getIdLog();
                double xIdUsuario
                        = fachadaAgendaLogVisitaBean.getIdUsuario();
                String xIdCliente
                        = fachadaAgendaLogVisitaBean.getIdCliente();
                int xIdLocalTercero
                        = fachadaAgendaLogVisitaBean.getIdLocalTercero();

                // Retorna a seleccionar cliente
                if (fachadaAgendaLogVisitaBean.getIdCliente() == null) {

                    request.setAttribute("fachadaAgendaLogVisitaBean",
                            fachadaAgendaLogVisitaBean);
                    //
                    fachadaAgendaLogVisitaBean.setIdCliente(xIdCliente);

                    return "/jsp/vtaContenedorEmpresaSelecciona.jsp";

                }

                //
                DctoOrdenBean dctoOrdenBean = new DctoOrdenBean();

                //
                dctoOrdenBean.setIdFicha(xIdFicha);
                dctoOrdenBean.setIdCliente(xIdCliente);

                //
                double xVrUltimoPrecioVenta
                        = dctoOrdenBean.listaUltimoPrecioVenta();

                //
                ProcesoGuardaOrdenTrabajo proceso = new ProcesoGuardaOrdenTrabajo();

                //
                double xCantidad
                        = new Double(xCantidadStr).doubleValue();
                double xVrVentaUnitario
                        = new Double(xVrVentaUnitarioStr).doubleValue();

                //valida el idTercero sea el mismo para todos
                String strIdReferencia = xIdPlu;
                int xItemPadre = 0;
                String xComentario = "ninguno";
                int xIdClasificacion = 0;

                //
                int maximoItem = proceso.guarda(idLog,
                        strIdReferencia,
                        xCantidad,
                        xVrVentaUnitario,
                        xItemPadre,
                        xIdTipoOrdenCotizacion,
                        xIdUsuario,
                        xIdLocalUsuario,
                        xIdCliente,
                        xVrUltimoPrecioVenta,
                        xIdFicha,
                        xUnidadVenta);

                //--------------------------------------------------------------
                int xIdEstadoRefOriginal = 0;

                //
                DctoOrdenDetalleBean dctoOrdenDetalleBean = new DctoOrdenDetalleBean();

                dctoOrdenDetalleBean.setIdLocal(xIdLocalUsuario);
                dctoOrdenDetalleBean.setIdTipoOrden(xIdTipoOrdenCotizacion);
                dctoOrdenDetalleBean.setIdEstadoRefOriginal(xIdEstadoRefOriginal);

                // actualizaPedidoNuevo ---> 1
                dctoOrdenDetalleBean.actualizaPedidoNuevo();

                //--------------------------------------------------------------
                FachadaTerceroBean fachadaTerceroBean = new FachadaTerceroBean();

                //
                fachadaTerceroBean.setIdCliente(xIdCliente);
                fachadaTerceroBean.setIdTipoTercero(xIdTipoTerceroCliente);
                fachadaTerceroBean.setIdTipoOrden(xIdTipoOrdenCotizacion);

                //
                ColaboraTercero colaboraTercero = new ColaboraTercero();

                //
                colaboraTercero.setIdTipoTercero(xIdTipoTerceroCliente);
                colaboraTercero.setIdCliente(xIdCliente);

                //
                fachadaTerceroBean = colaboraTercero.listaTerceroFCH();

                //
                fachadaTerceroBean.setIdTipoOrden(xIdTipoOrdenCotizacion);
                fachadaTerceroBean.setIdLocal(xIdLocalUsuario);
                fachadaTerceroBean.setIdLocalTercero(xIdLocalTercero);

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaTerceroBean", fachadaTerceroBean);

                //
                FachadaDctoOrdenBean fachadaDctoOrdenBean = new FachadaDctoOrdenBean();

                //
                dctoOrdenBean.setIdLog(fachadaAgendaLogVisitaBean.getIdLog());
                dctoOrdenBean.setIdTipoOrden(xIdTipoOrdenCotizacion);

                //
                fachadaDctoOrdenBean
                        = dctoOrdenBean.listaDctoOrdenIdLogIdTipoOrden();

                //
                request.setAttribute("fachadaDctoOrdenBean",
                        fachadaDctoOrdenBean);

                //
                FachadaPluFicha fachadaPluFicha = new FachadaPluFicha();

                //
                fachadaPluFicha.setIdFicha(xIdFicha);

                //
                request.setAttribute("fachadaPluFicha", fachadaPluFicha);

                //
                return "/jsp/vtaContenedorOrdenTrabajo.jsp";

            }

            // cumplir
            if (accionContenedor.compareTo("cumplir") == 0) {

                //
                String xItem = request.getParameter("item");
                String xIdLog = request.getParameter("idLog");

                //
                DctoOrdenBean dctoOrdenBean = new DctoOrdenBean();

                //
                dctoOrdenBean.setIdLog(xIdLog);

                FachadaDctoOrdenBean fachadaDctoOrdenBean = new FachadaDctoOrdenBean();

                //
                fachadaDctoOrdenBean
                        = dctoOrdenBean.listaDctoOrdenIdLog();

                //
                DctoOrdenDetalleBean dctoOrdenDetalleBean = new DctoOrdenDetalleBean();

                //
                int xIdEstadoRefOriginalAcabada = 9;

                //
                dctoOrdenDetalleBean.setIdEstadoRefOriginal(
                        xIdEstadoRefOriginalAcabada);
                dctoOrdenDetalleBean.setIdLog(xIdLog);
                dctoOrdenDetalleBean.setIdTipoOrden(xIdTipoOrdenCotizacion);
                dctoOrdenDetalleBean.setItem(xItem);

                // retiraArticulosMarcados
                boolean okRetiro = dctoOrdenDetalleBean.acabarOrden();

                //
                int estadoVisita = 9;
                int estadoSuspendido = 8;
                int xIdEstadoVisita = 1;

                //
                AgendaLogVisitaBean agendaLogVisitaBean = new AgendaLogVisitaBean();

                //
                agendaLogVisitaBean.setEstado(estadoVisita);
                agendaLogVisitaBean.setFechaVisitaStr(strFechaVisita);
                agendaLogVisitaBean.setIdUsuario(idUsuario);

                //
                FachadaAgendaLogVisitaBean fachadaAgendaLogVisitaBean = new FachadaAgendaLogVisitaBean();

                //
                fachadaAgendaLogVisitaBean
                        = agendaLogVisitaBean.seleccionaVisitaEstadoFecha();

                //
                int xIdLogActual
                        = fachadaAgendaLogVisitaBean.getIdLog();

                // finalizaVisita
                agendaLogVisitaBean.setIdLog(xIdLogActual);
                agendaLogVisitaBean.setIdCliente(
                        fachadaAgendaLogVisitaBean.getIdCliente());
                agendaLogVisitaBean.setIdUsuario(
                        fachadaAgendaLogVisitaBean.getIdUsuario());
                agendaLogVisitaBean.setIdEstadoVisita(xIdEstadoVisita);
                agendaLogVisitaBean.setEstado(estadoSuspendido);
                agendaLogVisitaBean.setFechaVisita(strFechaVisita);

                //
                boolean okLog
                        = agendaLogVisitaBean.actualizaVisita(estadoVisita);

            }

            // Retirar
            if (accionContenedor.compareTo("Retirar") == 0) {

                //
                String xItem = request.getParameter("item");
                String xIdLog = request.getParameter("idLog");

                //
                DctoOrdenBean dctoOrdenBean = new DctoOrdenBean();

                //
                dctoOrdenBean.setIdLog(xIdLog);

                FachadaDctoOrdenBean fachadaDctoOrdenBean = new FachadaDctoOrdenBean();

                //
                fachadaDctoOrdenBean
                        = dctoOrdenBean.listaDctoOrdenIdLog();

                //
                DctoOrdenDetalleBean dctoOrdenDetalleBean = new DctoOrdenDetalleBean();

                //
                dctoOrdenDetalleBean.setIdLog(xIdLog);
                dctoOrdenDetalleBean.setIdTipoOrden(xIdTipoOrdenCotizacion);
                dctoOrdenDetalleBean.setItem(xItem);

                // retiraArticulosMarcados
                boolean okRetiro = dctoOrdenDetalleBean.retiraItem();

                // Retira DctoOrden
                dctoOrdenBean.setIdLocal(xIdLocalUsuario);
                dctoOrdenBean.setIdTipoOrden(xIdTipoOrdenCotizacion);
                dctoOrdenBean.setIdOrden(xIdLog);

                // validaArticulosxOrden
                boolean okDetalle = dctoOrdenDetalleBean.validaOrden();

                if (!okDetalle) {

                    // Retira DctoOrden
                    dctoOrdenBean.setIdLocal(xIdLocalUsuario);
                    dctoOrdenBean.setIdTipoOrden(xIdTipoOrdenCotizacion);
                    dctoOrdenBean.setIdOrden(xIdLog);

                    //
                    dctoOrdenBean.retiraOrden();

                }

                //
                AgendaLogVisitaBean agendaLogVisitaBean = new AgendaLogVisitaBean();

                //
                agendaLogVisitaBean.setEstado(estadoActivo);
                agendaLogVisitaBean.setFechaVisitaStr(strFechaVisita);
                agendaLogVisitaBean.setIdUsuario(idUsuario);

                //
                FachadaAgendaLogVisitaBean fachadaAgendaLogVisitaBean = new FachadaAgendaLogVisitaBean();

                //
                fachadaAgendaLogVisitaBean
                        = agendaLogVisitaBean.seleccionaVisitaEstadoFecha();

                //
                FachadaTerceroBean fachadaTerceroBean = new FachadaTerceroBean();

                //
                ColaboraTercero colaboraTercero = new ColaboraTercero();

                //
                fachadaTerceroBean.setIdTipoOrden(xIdTipoOrdenCotizacion);
                colaboraTercero.setIdTipoTercero(xIdTipoTerceroCliente);
                colaboraTercero.setIdCliente(
                        fachadaAgendaLogVisitaBean.getIdCliente());
                colaboraTercero.setIdLocalTercero(
                        fachadaAgendaLogVisitaBean.getIdLocalTercero());

                //
                if (fachadaAgendaLogVisitaBean.getIdLocalTercero() > 0) {

                    //
                    fachadaTerceroBean
                            = colaboraTercero.listaUnTerceroLocalFCH();
                } else {

                    //
                    fachadaTerceroBean
                            = colaboraTercero.listaTerceroFCH();
                }

                //
                fachadaTerceroBean.setIdTipoTercero(xIdTipoTerceroCliente);
                fachadaTerceroBean.setIdTipoOrden(xIdTipoOrdenCotizacion);
                fachadaTerceroBean.setIdLocal(xIdLocalUsuario);

                //
                request.setAttribute("fachadaDctoOrdenBean", fachadaDctoOrdenBean);

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaTerceroBean", fachadaTerceroBean);

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaAgendaLogVisitaBean",
                        fachadaAgendaLogVisitaBean);

                //
                FachadaPluFicha fachadaPluFicha = new FachadaPluFicha();

                //
                request.setAttribute("fachadaPluFicha", fachadaPluFicha);

                return "/jsp/vtaContenedorOrdenTrabajo.jsp";

            }

            // Finalizar
            if (accionContenedor.compareTo("Finalizar") == 0) {

                //
                int estadoVisita = 9;
                int estadoSuspendido = 8;
                int xIdEstadoVisita = 1;

                //
                AgendaLogVisitaBean agendaLogVisitaBean = new AgendaLogVisitaBean();

                //
                agendaLogVisitaBean.setEstado(estadoVisita);
                agendaLogVisitaBean.setFechaVisitaStr(strFechaVisita);
                agendaLogVisitaBean.setIdUsuario(idUsuario);

                //
                FachadaAgendaLogVisitaBean fachadaAgendaLogVisitaBean = new FachadaAgendaLogVisitaBean();

                //
                fachadaAgendaLogVisitaBean
                        = agendaLogVisitaBean.seleccionaVisitaEstadoFecha();

                //
                int xIdLogActual
                        = fachadaAgendaLogVisitaBean.getIdLog();

                // finalizaVisita
                agendaLogVisitaBean.setIdLog(xIdLogActual);
                agendaLogVisitaBean.setIdCliente(
                        fachadaAgendaLogVisitaBean.getIdCliente());
                agendaLogVisitaBean.setIdUsuario(
                        fachadaAgendaLogVisitaBean.getIdUsuario());
                agendaLogVisitaBean.setIdEstadoVisita(xIdEstadoVisita);
                agendaLogVisitaBean.setEstado(estadoSuspendido);
                agendaLogVisitaBean.setFechaVisita(strFechaVisita);

                //
                boolean okLog
                        = agendaLogVisitaBean.actualizaVisita(estadoVisita);
                String idTipoRetencionContable = request.getParameter("listaRetencion");
                Retencion_Contable_DAO daoRetContable = new Retencion_Contable_DAO();
                int OT = Integer.parseInt(request.getParameter("numeroOrden"));
                daoRetContable.setNumeroOrden(OT);//Ingresa el numero de Documento 
                daoRetContable.setIdTipoOrdenAlcance(Integer.parseInt(idTipoRetencionContable));
                daoRetContable.actualizaRetencionPedido();//Actualiza el tipo de retencion a aplicar.
            }

            // Cotizar
            if (accionContenedor.compareTo("Cotizar") == 0) {

                //
                int estadoVisita = 9;
                int estadoCotizar = 13;
                int xIdEstadoVisita = 1;

                //
                AgendaLogVisitaBean agendaLogVisitaBean = new AgendaLogVisitaBean();

                //
                agendaLogVisitaBean.setEstado(estadoVisita);
                agendaLogVisitaBean.setFechaVisitaStr(strFechaVisita);
                agendaLogVisitaBean.setIdUsuario(idUsuario);

                //
                FachadaAgendaLogVisitaBean fachadaAgendaLogVisitaBean = new FachadaAgendaLogVisitaBean();

                //
                fachadaAgendaLogVisitaBean
                        = agendaLogVisitaBean.seleccionaVisitaEstadoFecha();

                //
                int xIdLogActual
                        = fachadaAgendaLogVisitaBean.getIdLog();

                // finalizaVisita
                agendaLogVisitaBean.setIdLog(xIdLogActual);
                agendaLogVisitaBean.setIdCliente(
                        fachadaAgendaLogVisitaBean.getIdCliente());
                agendaLogVisitaBean.setIdUsuario(
                        fachadaAgendaLogVisitaBean.getIdUsuario());
                agendaLogVisitaBean.setIdEstadoVisita(xIdEstadoVisita);
                agendaLogVisitaBean.setEstado(estadoCotizar);

                //
                boolean okLog
                        = agendaLogVisitaBean.actualizaLogVisitaUsuario(estadoVisita);

            }
        }

        //
        return "/jsp/empty.htm";

    }
}
