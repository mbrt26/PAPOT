package com.solucionesweb.losservlets;

// Importa los paquetes del lenguaje especificamente io y los
// que permiten trabajar el servlet.
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
import com.solucionesweb.lasayudas.ProcesoGuardaPluOrden;
import com.solucionesweb.losbeans.fachada.FachadaUsuarioBean;

// Este servlet implementa la interface GralManejadorRequest
public class VtasAdmEmpresaElaboraManejadorRequest
        implements GralManejadorRequest {

    // Metodo contructor por defecto, es decir, sin parametros
    public VtasAdmEmpresaElaboraManejadorRequest() {
    }

    /**
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
        int estadoRetira = 1;

        //
        int estadoActivo = 9;
        Day day = new Day();
        String strFechaVisita = day.getFechaFormateada();

        //
        HttpSession sesion = request.getSession();

        //
        UsuarioBean usuarioBean =
                (UsuarioBean) sesion.getAttribute("usuarioBean");

        //
        String idUsuario = usuarioBean.getIdUsuarioStr();
        int xIdLocalUsuario = usuarioBean.getIdLocalUsuario();
        String strIdLista = usuarioBean.getStrIdLista();

        //
        String accionContenedor = request.getParameter("accionContenedor");

        // Validacion de accion relacionada con el formulario requerido
        if (accionContenedor != null) {

            System.out.println(" accionContenedor " + accionContenedor);

            //
            if (accionContenedor.compareTo("Regresar") == 0) {
                return "/jsp/empty.htm";
            }

            // Confirmar Cantidad
            if (accionContenedor.compareTo("Confirmar Cantidad") == 0) {

                //
                String idClasificacion = "1";
                String idResponsable = "1";
                String idLog = request.getParameter("idLog");
                String itemStr = request.getParameter("item");
                String cantidad = request.getParameter("cantidad");
                String vrVentaUnitario = request.getParameter("vrVentaUnitario");
                String xPorcentajeDescuento = request.getParameter("xPorcentajeDescuento");
                String xAutorizacion = request.getParameter("xAutorizacion");

                //
                Validacion validacion = new Validacion();

                //
                validacion.reasignar("CANTIDAD", cantidad);
                validacion.validarCampoDouble();

                if (validacion.isValido() == false) {

                    // Aqui escribe el Bean de Validacion en el Request para manejar el error
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralErrPedido.jsp";
                }

                //
                validacion.reasignar("VR.VENTA UNITARIO", vrVentaUnitario);
                validacion.validarCampoDoublePositivo();

                if (validacion.isValido() == false) {

                    // Aqui escribe el Bean de Validacion en el Request para manejar el error
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralErrPedido.jsp";
                }

                //
                validacion.reasignar("%DESCUENTO", xPorcentajeDescuento);
                validacion.validarCampoDoublePositivo();

                if (validacion.isValido() == false) {

                    // Aqui escribe el Bean de Validacion en el Request para manejar el error
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralErrPedido.jsp";
                }

                // Valida xAutorizacion
                validacion.reasignar("AUTORIZACION", xAutorizacion);

                //
                validacion.validarCampoString();

                //
                if (validacion.isValido() == false) {

                    // Aqui escribe el Bean de Validacion en el Request para manejar el error
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralError.jsp";
                }

                //---  xIdLocalUsuario
                FachadaUsuarioBean fachadaUsuarioBean = new FachadaUsuarioBean();

                //
                usuarioBean.setIdLocalUsuario(xIdLocalUsuario);
                usuarioBean.setEstado(1);
                usuarioBean.setIdNivelCadena("2,52");
                usuarioBean.setClave(xAutorizacion.trim());

                //
                fachadaUsuarioBean = usuarioBean.listaAutorizador();

                //
                if (fachadaUsuarioBean.getIdUsuario()==0) {

                    //
                    validacion.reasignarDescripcion("AUTORIZACION", "Codigo Incorrecto",
                            "Codigo de autorizacion incorrecto", "Ingrese un codigo válido");

                    // Aqui escribe el Bean de Validacion en el Request para manejar el error
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralError.jsp";

                }
                
                //
                AgendaLogVisitaBean agendaLogVisitaBean = new AgendaLogVisitaBean();

                agendaLogVisitaBean.setEstado(estadoActivo);
                agendaLogVisitaBean.setFechaVisitaStr(strFechaVisita);
                agendaLogVisitaBean.setIdUsuario(idUsuario);

                //
                FachadaAgendaLogVisitaBean fachadaAgendaLogVisitaBean = new FachadaAgendaLogVisitaBean();
                fachadaAgendaLogVisitaBean =
                        agendaLogVisitaBean.seleccionaVisitaEstadoFecha();

                //
                DctoOrdenBean dctoOrdenBean = new DctoOrdenBean();

                //
                dctoOrdenBean.setIdLog(idLog);
                dctoOrdenBean.setIdTipoOrden(xIdTipoOrdenCotizacion);

                //
                FachadaDctoOrdenBean fachadaDctoOrdenBean = new FachadaDctoOrdenBean();

                //
                fachadaDctoOrdenBean =
                        dctoOrdenBean.listaDctoOrdenIdLogIdTipoOrden();

                //
                String xIdTipoOrden =
                        fachadaDctoOrdenBean.getIdTipoOrdenStr();
                String xIdOrden =
                        fachadaDctoOrdenBean.getIdOrdenStr();
                String xItem = itemStr;

                //
                DctoOrdenDetalleBean dctoOrdenDetalleBean = new DctoOrdenDetalleBean();

                //
                dctoOrdenDetalleBean.setItem(itemStr);
                dctoOrdenDetalleBean.setCantidad(cantidad);
                dctoOrdenDetalleBean.setVrVentaUnitario(vrVentaUnitario);
                dctoOrdenDetalleBean.setIdClasificacion(idClasificacion);
                dctoOrdenDetalleBean.setIdResponsable(idResponsable);
                dctoOrdenDetalleBean.setPorcentajeDscto(xPorcentajeDescuento);

                //
                dctoOrdenDetalleBean.modifica(new Integer(xIdLocalUsuario).toString(),
                        xIdTipoOrden,
                        xIdOrden,
                        xItem);

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
                    fachadaTerceroBean =
                            colaboraTercero.listaUnTerceroLocalFCH();
                } else {

                    //
                    fachadaTerceroBean =
                            colaboraTercero.listaTerceroFCH();
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



                return "/jsp/vtaContenedorEmpresaElaboraPedido.jsp";

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
                fachadaAgendaLogVisitaBean =
                        agendaLogVisitaBean.seleccionaVisitaEstadoFecha();

                //
                int idLog = fachadaAgendaLogVisitaBean.getIdLog();

                //
                String itemStr = request.getParameter("item");

                //
                if (itemStr != null) {

                    //
                    FachadaDctoOrdenDetalleBean fachadaDctoOrdenDetalleBean =
                            new FachadaDctoOrdenDetalleBean();

                    //
                    ColaboraDctoOrdenDetalleBean colaboraDctoOrdenDetalleBean =
                            new ColaboraDctoOrdenDetalleBean();


                    //
                    colaboraDctoOrdenDetalleBean.setItem(itemStr);
                    colaboraDctoOrdenDetalleBean.setIdTipoOrden(
                            xIdTipoOrdenCotizacion);

                    //
                    fachadaDctoOrdenDetalleBean =
                            colaboraDctoOrdenDetalleBean.itemLogFachada(idLog);

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
                        fachadaTerceroBean =
                                colaboraTercero.listaUnTerceroLocalFCH();
                    } else {

                        //
                        fachadaTerceroBean =
                                colaboraTercero.listaTerceroFCH();
                    }

                    // Aqui escribe el Bean de Validacion en el Request para manejar el error
                    request.setAttribute("fachadaTerceroBean", fachadaTerceroBean);

                    //
                    return "/jsp/vtaFrmModEmpresaElaboraRetiraPlu.jsp";

                }

                //
                FachadaTerceroBean fachadaTerceroBean = new FachadaTerceroBean();

                //
                ColaboraTercero colaboraTercero = new ColaboraTercero();

                //
                fachadaTerceroBean.setIdTipoOrden(xIdTipoOrdenCotizacion);
                colaboraTercero.setIdTercero(xIdTipoTerceroCliente);
                colaboraTercero.setIdTercero(
                        fachadaAgendaLogVisitaBean.getIdCliente());
                colaboraTercero.setIdLocalTercero(
                        fachadaAgendaLogVisitaBean.getIdLocalTercero());

                //
                if (fachadaAgendaLogVisitaBean.getIdLocalTercero() > 0) {

                    //
                    fachadaTerceroBean =
                            colaboraTercero.listaUnTerceroLocalFCH();
                } else {

                    //
                    fachadaTerceroBean =
                            colaboraTercero.listaTerceroFCH();
                }

                //
                DctoOrdenBean dctoOrdenBean = new DctoOrdenBean();

                //
                dctoOrdenBean.setIdLog(fachadaAgendaLogVisitaBean.getIdLog());
                dctoOrdenBean.setIdTipoOrden(xIdTipoOrdenCotizacion);

                //
                FachadaDctoOrdenBean fachadaDctoOrdenBean = new FachadaDctoOrdenBean();

                //
                fachadaDctoOrdenBean =
                        dctoOrdenBean.listaDctoOrdenIdLogIdTipoOrden();

                //
                request.setAttribute("fachadaDctoOrdenBean", fachadaDctoOrdenBean);

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaTerceroBean", fachadaTerceroBean);

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaAgendaLogVisitaBean",
                        fachadaAgendaLogVisitaBean);
                return "/jsp/vtaContenedorEmpresaElaboraPedido.jsp";

            }

            // Validar
            if (accionContenedor.compareTo("+Productos") == 0) {

                //
                String idLinea = request.getParameter("idLinea");
                String xIdListaPrecio = request.getParameter("xIdListaPrecio");

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
                AgendaLogVisitaBean agendaLogVisitaBean 
                                                    = new AgendaLogVisitaBean();

                //
                agendaLogVisitaBean.setEstado(estadoActivo);
                agendaLogVisitaBean.setFechaVisitaStr(strFechaVisita);
                agendaLogVisitaBean.setIdUsuario(idUsuario);

                //
                FachadaAgendaLogVisitaBean fachadaAgendaLogVisitaBean 
                                             = new FachadaAgendaLogVisitaBean();

                //
                fachadaAgendaLogVisitaBean =
                        agendaLogVisitaBean.seleccionaVisitaEstadoFecha();

                //
                FachadaColaboraHistoriaBean fachadaColaboraHistoriaBean =
                        new FachadaColaboraHistoriaBean();

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
                fachadaDctoOrdenBean =
                        dctoOrdenBean.listaDctoOrdenIdLogIdTipoOrden();

                //
                request.setAttribute("fachadaDctoOrdenBean", fachadaDctoOrdenBean);

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaTerceroBean", fachadaTerceroBean);

                return "/jsp/vtaFrmSelEmpresaElaboraAdicionaPlu.jsp";

            }

            // Confirmar
            if (accionContenedor.compareTo("Confirmar") == 0) {

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
                AgendaLogVisitaBean agendaLogVisitaBean
                                                    = new AgendaLogVisitaBean();

                //
                agendaLogVisitaBean.setEstado(estadoActivo);
                agendaLogVisitaBean.setFechaVisitaStr(strFechaVisita);
                agendaLogVisitaBean.setIdUsuario(idUsuario);

                //
                FachadaAgendaLogVisitaBean fachadaAgendaLogVisitaBean
                                             = new FachadaAgendaLogVisitaBean();

                //
                fachadaAgendaLogVisitaBean =
                        agendaLogVisitaBean.seleccionaVisitaEstadoFecha();

                //
                int idLog =
                        fachadaAgendaLogVisitaBean.getIdLog();
                double xIdUsuario =
                        fachadaAgendaLogVisitaBean.getIdUsuario();
                String xIdCliente =
                        fachadaAgendaLogVisitaBean.getIdCliente();
                int xIdLocalTercero =
                        fachadaAgendaLogVisitaBean.getIdLocalTercero();

                // Retorna a seleccionar cliente
                if (fachadaAgendaLogVisitaBean.getIdCliente() == null) {

                    request.setAttribute("fachadaAgendaLogVisitaBean",
                            fachadaAgendaLogVisitaBean);
                    //
                    fachadaAgendaLogVisitaBean.setIdCliente(xIdCliente);


                    return "/jsp/vtaContenedorEmpresaSelecciona.jsp";

                }

                //
                Validacion valida = new Validacion();

                // Validar
                for (int indice = 0; indice < arrcantidad.length; indice++) {

                    //
                    if (arrcantidad[indice].length() == 0) {
                        continue;
                    }

                    //
                    valida.reasignar("CANTIDAD", arrcantidad[indice]);

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
                    int maximoItem = proceso.guarda(idLog,
                            strIdReferencia,
                            xCantidad,
                            xVrVentaUnitario,
                            xItemPadre,
                            xIdTipoOrdenCotizacion,
                            xIdUsuario,
                            xIdLocalUsuario,
                            xIdCliente,
                            xComentario,
                            xIdResponsable,
                            xIdClasificacion,
                            xUnidadMedida);

                }

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
                fachadaDctoOrdenBean =
                        dctoOrdenBean.listaDctoOrdenIdLogIdTipoOrden();

                //
                request.setAttribute("fachadaDctoOrdenBean", fachadaDctoOrdenBean);

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaTerceroBean", fachadaTerceroBean);

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaAgendaLogVisitaBean",
                        fachadaAgendaLogVisitaBean);
                return "/jsp/vtaContenedorEmpresaElaboraPedido.jsp";

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
                fachadaDctoOrdenBean =
                        dctoOrdenBean.listaDctoOrdenIdLog();

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
                fachadaAgendaLogVisitaBean =
                        agendaLogVisitaBean.seleccionaVisitaEstadoFecha();

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
                    fachadaTerceroBean =
                            colaboraTercero.listaUnTerceroLocalFCH();
                } else {

                    //
                    fachadaTerceroBean =
                            colaboraTercero.listaTerceroFCH();
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
                return "/jsp/vtaContenedorEmpresaElaboraPedido.jsp";

            }

            if (accionContenedor.compareTo("Confirmar Descuento") == 0) {

                //
                String idLog = request.getParameter("idLog");
                String xDescuentoComercial =
                        request.getParameter("xDescuentoComercial");
                String xAutorizacion = request.getParameter("xAutorizacion");

                //
                Validacion validacion = new Validacion();

                //
                validacion.reasignar("%DESCUENTO COMERCIAL", xDescuentoComercial);
                validacion.validarCampoDoublePositivo();

                if (validacion.isValido() == false) {

                    // Aqui escribe el Bean de Validacion en el Request para manejar el error
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralErrPedido.jsp";
                }

                // Valida xAutorizacion
                validacion.reasignar("AUTORIZACION", xAutorizacion);

                //
                validacion.validarCampoString();

                //
                if (validacion.isValido() == false) {

                    // Aqui escribe el Bean de Validacion en el Request para manejar el error
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralError.jsp";
                }

                //---  xIdLocalUsuario
                FachadaUsuarioBean fachadaUsuarioBean = new FachadaUsuarioBean();

                //
                usuarioBean.setIdLocalUsuario(xIdLocalUsuario);
                usuarioBean.setEstado(1);
                usuarioBean.setIdNivelCadena("2,52");
                usuarioBean.setClave(xAutorizacion.trim());

                //
                fachadaUsuarioBean = usuarioBean.listaAutorizador();

                //
                if (fachadaUsuarioBean.getIdUsuario()==0) {

                    //
                    validacion.reasignarDescripcion("AUTORIZACION", "Codigo Incorrecto",
                            "Codigo de autorizacion incorrecto", "Ingrese un codigo válido");

                    // Aqui escribe el Bean de Validacion en el Request para manejar el error
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralError.jsp";

                }
                
                //
                AgendaLogVisitaBean agendaLogVisitaBean = new AgendaLogVisitaBean();

                //
                agendaLogVisitaBean.setEstado(estadoActivo);
                agendaLogVisitaBean.setFechaVisitaStr(strFechaVisita);
                agendaLogVisitaBean.setIdUsuario(idUsuario);

                //
                FachadaAgendaLogVisitaBean fachadaAgendaLogVisitaBean = new FachadaAgendaLogVisitaBean();
                fachadaAgendaLogVisitaBean =
                        agendaLogVisitaBean.seleccionaVisitaEstadoFecha();

                //
                DctoOrdenBean dctoOrdenBean = new DctoOrdenBean();

                //
                dctoOrdenBean.setDescuentoComercial(xDescuentoComercial);
                dctoOrdenBean.setIdLocal(xIdLocalUsuario);
                dctoOrdenBean.setIdTipoOrden(xIdTipoOrdenCotizacion);
                dctoOrdenBean.setIdLog(fachadaAgendaLogVisitaBean.getIdLog());

                //
                dctoOrdenBean.actualizaDescuento();

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
                    fachadaTerceroBean =
                            colaboraTercero.listaUnTerceroLocalFCH();
                } else {

                    //
                    fachadaTerceroBean =
                            colaboraTercero.listaTerceroFCH();
                }

                //
                fachadaTerceroBean.setIdCliente(
                        fachadaAgendaLogVisitaBean.getIdCliente());
                fachadaTerceroBean.setIdTipoTercero(xIdTipoTerceroCliente);
                fachadaTerceroBean.setIdTipoOrden(xIdTipoOrdenCotizacion);
                fachadaTerceroBean.setIdLocal(xIdLocalUsuario);

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaTerceroBean", fachadaTerceroBean);

                //
                DctoOrdenDetalleBean dctoOrdenDetalleBean = new DctoOrdenDetalleBean();

                //
                dctoOrdenDetalleBean.setIdLocal(xIdLocalUsuario);
                dctoOrdenDetalleBean.setIdTipoOrden(xIdTipoOrdenCotizacion);
                dctoOrdenDetalleBean.setIdLog(fachadaAgendaLogVisitaBean.getIdLog());
                dctoOrdenDetalleBean.setVrDsctoPie(xDescuentoComercial);

                //
                dctoOrdenDetalleBean.actualizaDescuentoPie();

                //
                FachadaDctoOrdenBean fachadaDctoOrdenBean = new FachadaDctoOrdenBean();

                //
                fachadaDctoOrdenBean =
                        dctoOrdenBean.listaDctoOrdenIdLogIdTipoOrden();

                //
                request.setAttribute("fachadaDctoOrdenBean", fachadaDctoOrdenBean);

                // Retorna a seleccionar cliente
                if (fachadaAgendaLogVisitaBean.getIdCliente() == null) {

                    return "/jsp/vtaContenedorEmpresaSelecciona.jsp";

                }

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaAgendaLogVisitaBean",
                        fachadaAgendaLogVisitaBean);
                return "/jsp/vtaContenedorEmpresaElaboraPedido.jsp";

            }

            // ModificaDescuento
            if (accionContenedor.compareTo("ModificaDescuento") == 0) {

                //
                String idLog = request.getParameter("idLog");

                //
                AgendaLogVisitaBean agendaLogVisitaBean = new AgendaLogVisitaBean();
                agendaLogVisitaBean.setEstado(estadoActivo);
                agendaLogVisitaBean.setFechaVisitaStr(strFechaVisita);
                agendaLogVisitaBean.setIdUsuario(idUsuario);

                //
                FachadaAgendaLogVisitaBean fachadaAgendaLogVisitaBean = new FachadaAgendaLogVisitaBean();

                //
                fachadaAgendaLogVisitaBean =
                        agendaLogVisitaBean.seleccionaVisitaEstadoFecha();

                //
                DctoOrdenBean dctoOrdenBean = new DctoOrdenBean();

                //
                dctoOrdenBean.setIdLog(idLog);
                dctoOrdenBean.setIdTipoOrden(xIdTipoOrdenCotizacion);

                //
                FachadaDctoOrdenBean fachadaDctoOrdenBean = new FachadaDctoOrdenBean();

                //
                fachadaDctoOrdenBean =
                        dctoOrdenBean.listaDctoOrdenIdLogIdTipoOrden();

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
                    fachadaTerceroBean =
                            colaboraTercero.listaUnTerceroLocalFCH();
                } else {

                    //
                    fachadaTerceroBean =
                            colaboraTercero.listaTerceroFCH();
                }

                //
                fachadaTerceroBean.setIdCliente(
                        fachadaAgendaLogVisitaBean.getIdCliente());
                fachadaTerceroBean.setIdTipoTercero(xIdTipoTerceroCliente);
                fachadaTerceroBean.setIdTipoOrden(xIdTipoOrdenCotizacion);
                fachadaTerceroBean.setIdLocal(xIdLocalUsuario);

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaTerceroBean", fachadaTerceroBean);

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaAgendaLogVisitaBean",
                        fachadaAgendaLogVisitaBean);
                request.setAttribute("fachadaDctoOrdenBean", fachadaDctoOrdenBean);

                //
                return "/jsp/vtaFrmModEmpresaElaboraDsctoComercial.jsp";

            }

            // Finalizar Finalizar
            if (accionContenedor.compareTo("Finalizar") == 0) {


                //
                String xIdVendedor = request.getParameter("xIdVendedor");

                //
                AgendaLogVisitaBean agendaLogVisitaBean = new AgendaLogVisitaBean();
                agendaLogVisitaBean.setEstado(estadoActivo);
                agendaLogVisitaBean.setFechaVisitaStr(strFechaVisita);
                agendaLogVisitaBean.setIdUsuario(idUsuario);

                //
                FachadaAgendaLogVisitaBean fachadaAgendaLogVisitaBean = new FachadaAgendaLogVisitaBean();

                //
                fachadaAgendaLogVisitaBean =
                        agendaLogVisitaBean.seleccionaVisitaEstadoFecha();

                //
                fachadaAgendaLogVisitaBean.setIdLocal(xIdLocalUsuario);
                fachadaAgendaLogVisitaBean.setIdTipoOrden(xIdTipoOrdenCotizacion);

                //
                ColaboraTercero colaboraTercero = new ColaboraTercero();

                //
                colaboraTercero.setIdCliente(
                        fachadaAgendaLogVisitaBean.getIdCliente());
                colaboraTercero.setIdTipoTercero(xIdTipoTerceroCliente);
                colaboraTercero.setIdLocalTercero(
                        fachadaAgendaLogVisitaBean.getIdLocalTercero());

                //
                FachadaTerceroBean fachadaTerceroBean = new FachadaTerceroBean();

                //
                if (fachadaAgendaLogVisitaBean.getIdLocalTercero() > 0) {

                    //
                    fachadaTerceroBean =
                            colaboraTercero.listaUnTerceroLocalFCH();


                } else {

                    //
                    fachadaTerceroBean = colaboraTercero.listaTerceroFCH();

                }

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaAgendaLogVisitaBean",
                        fachadaAgendaLogVisitaBean);
                request.setAttribute("fachadaTerceroBean",
                        fachadaTerceroBean);

                // Retorna a seleccionar cliente
                if (fachadaTerceroBean.getIdCliente() == null) {

                    return "/jsp/vtaContenedorEmpresaSelecciona.jsp";

                }

                //
                return "/jsp/vtaFrmSelEmpresaFinalizaPedido.jsp";

            }

            // Confirma Retiro
            if (accionContenedor.compareTo("Confirmar Retiro") == 0) {

                //
                AgendaLogVisitaBean agendaLogVisitaBean = new AgendaLogVisitaBean();

                //
                agendaLogVisitaBean.setEstado(estadoActivo);
                agendaLogVisitaBean.setFechaVisitaStr(strFechaVisita);
                agendaLogVisitaBean.setIdUsuario(idUsuario);

                //
                FachadaAgendaLogVisitaBean fachadaAgendaLogVisitaBean = new FachadaAgendaLogVisitaBean();
                fachadaAgendaLogVisitaBean =
                        agendaLogVisitaBean.seleccionaVisitaEstadoFecha();

                //
                DctoOrdenBean dctoOrdenBean = new DctoOrdenBean();
                dctoOrdenBean.setIdLog(fachadaAgendaLogVisitaBean.getIdLog());

                FachadaDctoOrdenBean fachadaDctoOrdenBean = new FachadaDctoOrdenBean();
                fachadaDctoOrdenBean =
                        dctoOrdenBean.listaDctoOrdenIdLog();

                //
                DctoOrdenDetalleBean dctoOrdenDetalleBean = new DctoOrdenDetalleBean();
                dctoOrdenDetalleBean.setEstado(estadoRetira);
                dctoOrdenDetalleBean.setIdLocal(
                        fachadaAgendaLogVisitaBean.getIdLog());

                //
                dctoOrdenDetalleBean.setIdLocal(fachadaDctoOrdenBean.getIdLocal());
                dctoOrdenDetalleBean.setIdTipoOrden(
                        fachadaDctoOrdenBean.getIdTipoOrden());
                dctoOrdenDetalleBean.setIdOrden(fachadaDctoOrdenBean.getIdOrden());


                // retiraArticulosMarcados
                boolean okRetiro = dctoOrdenDetalleBean.retiraArticulosMarcados();

                // validaArticulosxOrden
                boolean okDetalle = dctoOrdenDetalleBean.validaArticulosxOrden();

                if (!okDetalle) {

                    // Retira DctoOrden
                    dctoOrdenBean.setIdLocal(fachadaDctoOrdenBean.getIdLocal());
                    dctoOrdenBean.setIdTipoOrden(
                            fachadaDctoOrdenBean.getIdTipoOrden());
                    dctoOrdenBean.setIdOrden(fachadaDctoOrdenBean.getIdOrden());

                    //
                    dctoOrdenBean.retiraDctoOrden();

                }

                // Retorna a Contenedor
                agendaLogVisitaBean.setEstado(estadoActivo);
                agendaLogVisitaBean.setFechaVisitaStr(strFechaVisita);
                agendaLogVisitaBean.setIdUsuario(idUsuario);

                //
                fachadaAgendaLogVisitaBean =
                        agendaLogVisitaBean.seleccionaVisitaEstadoFecha();

                // Retorna a seleccionar cliente
                if (fachadaAgendaLogVisitaBean.getIdCliente() == null) {

                    return "/jsp/vtaContenedorEmpresaSelecciona.jsp";

                }

                //
                FachadaTerceroBean fachadaTerceroBean = new FachadaTerceroBean();

                //
                fachadaTerceroBean.setIdCliente(
                        fachadaAgendaLogVisitaBean.getIdCliente());
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
                return "/jsp/vtaContenedorEmpresaElaboraPedido.jsp";

            }

            // Suspender
            if (accionContenedor.compareTo("Suspender") == 0) {

                //
                int estadoVisita = 9;
                int estadoSuspendido = 8;
                int xIdEstadoVisita = 1;

                //
                AgendaLogVisitaBean agendaLogVisitaBean = new AgendaLogVisitaBean();
                agendaLogVisitaBean.setEstado(estadoVisita);
                agendaLogVisitaBean.setFechaVisitaStr(strFechaVisita);
                agendaLogVisitaBean.setIdUsuario(idUsuario);

                //
                FachadaAgendaLogVisitaBean fachadaAgendaLogVisitaBean = new FachadaAgendaLogVisitaBean();

                //
                fachadaAgendaLogVisitaBean =
                        agendaLogVisitaBean.seleccionaVisitaEstadoFecha();

                //
                int xIdLogActual =
                        fachadaAgendaLogVisitaBean.getIdLog();

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
                boolean okLog =
                        agendaLogVisitaBean.actualizaVisita(estadoVisita);


            }

            // Cotizar
            if (accionContenedor.compareTo("Cotizar") == 0) {

                //
                int estadoVisita = 9;
                int estadoCotizar = 13;
                int xIdEstadoVisita = 1;

                //
                AgendaLogVisitaBean agendaLogVisitaBean = new AgendaLogVisitaBean();
                agendaLogVisitaBean.setEstado(estadoVisita);
                agendaLogVisitaBean.setFechaVisitaStr(strFechaVisita);
                agendaLogVisitaBean.setIdUsuario(idUsuario);

                //
                FachadaAgendaLogVisitaBean fachadaAgendaLogVisitaBean = new FachadaAgendaLogVisitaBean();

                //
                fachadaAgendaLogVisitaBean =
                        agendaLogVisitaBean.seleccionaVisitaEstadoFecha();

                //
                int xIdLogActual =
                        fachadaAgendaLogVisitaBean.getIdLog();


                // finalizaVisita
                agendaLogVisitaBean.setIdLog(xIdLogActual);
                agendaLogVisitaBean.setIdCliente(
                        fachadaAgendaLogVisitaBean.getIdCliente());
                agendaLogVisitaBean.setIdUsuario(
                        fachadaAgendaLogVisitaBean.getIdUsuario());
                agendaLogVisitaBean.setIdEstadoVisita(xIdEstadoVisita);
                agendaLogVisitaBean.setEstado(estadoCotizar);

                //
                boolean okLog =
                        agendaLogVisitaBean.actualizaLogVisitaUsuario(estadoVisita);

            }
        }

        //
        return "/jsp/empty.htm";

    }
}
