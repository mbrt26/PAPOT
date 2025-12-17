package com.solucionesweb.losservlets;

// Importa los paquetes del lenguaje especificamente io y los
import com.solucionesweb.lasayudas.ProcesoIngresoProduccion;
import com.solucionesweb.lasayudas.ProcesoIngresoCosto;
import com.solucionesweb.losbeans.colaboraciones.ColaboraDctoOrdenDetalleBean;
import com.solucionesweb.losbeans.colaboraciones.ColaboraOperacionPlu;
import com.solucionesweb.losbeans.colaboraciones.ColaboraOrdenTrabajo;
import com.solucionesweb.losbeans.colaboraciones.ColaboraPlu;
import com.solucionesweb.losbeans.colaboraciones.ColaboraTercero;
import com.solucionesweb.losbeans.fachada.FachadaAgendaLogVisitaBean;
import com.solucionesweb.losbeans.fachada.FachadaDctoOrdenBean;
import com.solucionesweb.losbeans.fachada.FachadaDctoOrdenDetalleBean;
import com.solucionesweb.losbeans.fachada.FachadaDctoOrdenProgreso;
import com.solucionesweb.losbeans.fachada.FachadaPluBean;
import com.solucionesweb.losbeans.fachada.FachadaPluFicha;
import com.solucionesweb.losbeans.fachada.FachadaTerceroBean;
import com.solucionesweb.losbeans.negocio.AgendaLogVisitaBean;
import com.solucionesweb.losbeans.negocio.DctoOrdenBean;
import com.solucionesweb.losbeans.negocio.DctoOrdenProgresoBean;
import com.solucionesweb.losbeans.negocio.UsuarioBean;
import com.solucionesweb.losbeans.utilidades.FichaTecnica;
import com.solucionesweb.losbeans.utilidades.Validacion;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// Este servlet implementa la interface GralManejadorRequest
public class VtasAdmOTCostoManejadorRequest
        implements GralManejadorRequest {

    // Metodo contructor por defecto, es decir, sin parametros
    public VtasAdmOTCostoManejadorRequest() { }

    public String generaPdf(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        //
        int xEstadoSuspendido = 8;
        int xEstadoActivo = 9;
        int xIdTipoOrdenTransformacion = 11;
        int xIdTipoInventario = 1;

        //
        HttpSession sesion = request.getSession();
        UsuarioBean usuarioBean =
                (UsuarioBean) sesion.getAttribute("usuarioBean");

        //
        double xIdUsuario = usuarioBean.getIdUsuario();
        int xIdLocalUsuario = usuarioBean.getIdLocalUsuario();

        //
        String accionContenedor = request.getParameter("accionContenedor");

        // Validacion de accion relacionada con el formulario requerido
        if (accionContenedor != null) {

            //
            if (accionContenedor.compareTo("Salir") == 0) {

                //
                return "/jsp/empty.htm";
            }


            // Consultar Pedido
            if (accionContenedor.compareTo("Consultar Pedido") == 0) {

                //
                String xNumeroOrden = request.getParameter("xNumeroOrden");
                String xIdOperacion = request.getParameter("xIdOperacion");

                //
                Validacion validacion = new Validacion();

                //
                validacion.reasignar("#PEDIDO", xNumeroOrden);

                //
                validacion.validarCampoEntero();

                //
                if (!validacion.isValido()) {

                    // Aqui escribe el Bean de Validacion en el Request para manejar el error
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralError.jsp";

                }

                //
                FachadaPluFicha fachadaPluFicha = new FachadaPluFicha();

                //
                ColaboraOrdenTrabajo colaboraOrdenTrabajo 
                                                = new ColaboraOrdenTrabajo();

                //
                int xItem = 1;

                //
                colaboraOrdenTrabajo.setNumeroOrden(xNumeroOrden);
                colaboraOrdenTrabajo.setIdOperacion(xIdOperacion);
                colaboraOrdenTrabajo.setItem(xItem);

                //
                fachadaPluFicha = colaboraOrdenTrabajo.listaOrdenFCH();

                //
                if (fachadaPluFicha.getIdPlu() == 0) {

                    //
                    validacion.setDescripcionError("NO EXISTE EL #PEDIDO " + xNumeroOrden);
                    validacion.setSolucion("INTENTAR CON OTRO #PEDIDO");

                    // Aqui escribe el Bean de Validacion en el Request para manejar el error
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralError.jsp";

                }

                double xCantidadPedido = fachadaPluFicha.getCantidad();
                int xIdFicha = fachadaPluFicha.getIdFicha();


                //
                fachadaPluFicha.setCantidad(xCantidadPedido);
                fachadaPluFicha.setNumeroOrden(xNumeroOrden);
                fachadaPluFicha.setIdOperacion(xIdOperacion);

                //
                request.setAttribute("fachadaPluFicha", fachadaPluFicha);

                //
                FachadaDctoOrdenBean fachadaDctoOrdenBean = new FachadaDctoOrdenBean();

                //
                fachadaDctoOrdenBean.setIdFicha(xIdFicha);
                fachadaDctoOrdenBean.setIdCliente(fachadaPluFicha.getIdCliente());

                //
                request.setAttribute("fachadaDctoOrdenBean",
                        fachadaDctoOrdenBean);

                //
                FachadaPluBean fachadaPluBean = new FachadaPluBean();

                //
                ColaboraPlu colaboraPlu = new ColaboraPlu();

                //
                colaboraPlu.setIdPlu(fachadaPluFicha.getIdPlu());

                //
                fachadaPluBean = colaboraPlu.listaUnPluFCH();


                //
                request.setAttribute("fachadaPluBean", fachadaPluBean);

                //
                return "/jsp/vtaFrmLstOTCosto.jsp";

            }

            /*
            if (accionContenedor.compareTo("Consultar") == 0) {

            //
            String xIdLocal = request.getParameter("xIdLocal");
            String xIdTipoOrden = request.getParameter("xIdTipoOrden");
            String xIdCliente = request.getParameter("xIdCliente");
            String xIdOperacion = request.getParameter("xIdOperacion");

            //
            Day day                        = new Day();
            String strFechaVisita          = day.getFechaFormateada();

            //
            HttpSession sesion             = request.getSession();
            UsuarioBean usuarioBean        =
            (UsuarioBean)sesion.getAttribute("usuarioBean");

            //
            String idUsuario               = usuarioBean.getIdUsuarioStr();
            int xIdLocalUsuario            = usuarioBean.getIdLocalUsuario();

            //
            int xIdTipoTercero             = 1;

            //
            FachadaDctoOrdenBean   fachadaDctoOrdenBean
            = new FachadaDctoOrdenBean();

            //
            fachadaDctoOrdenBean.setIdLocal(xIdLocal);
            fachadaDctoOrdenBean.setIdTipoOrden(xIdTipoOrden);
            fachadaDctoOrdenBean.setIdCliente(xIdCliente);
            fachadaDctoOrdenBean.setEstado(xEstadoSuspendido);
            fachadaDctoOrdenBean.setIdTipoTercero(xIdTipoTercero);
            fachadaDctoOrdenBean.setIdOperacion(xIdOperacion);

            // Aqui escribe el Bean de Validacion en el Request para manejar el error
            request.setAttribute("fachadaDctoOrdenBean",fachadaDctoOrdenBean);

            //
            FachadaPluFicha   fachadaPluFicha
            = new FachadaPluFicha();

            //
            fachadaPluFicha.setIdOperacion(xIdOperacion);

            // Aqui escribe el Bean de Validacion en el Request para manejar el error
            request.setAttribute("fachadaPluFicha",fachadaPluFicha);

            //
            return "/jsp/vtaFrmLstOrdenTrabajoActivo.jsp";

            }*/

            //
            if (accionContenedor.compareTo("trae") == 0) {

                //
                String xIdLocal = request.getParameter("xIdLocal");
                String xIdTipoOrden = request.getParameter("xIdTipoOrden");
                String xIdLog = request.getParameter("xIdLog");
                String xIdOperacion = request.getParameter("xIdOperacion");
                String xItemPadre = request.getParameter("xItem");

                //
                AgendaLogVisitaBean agendaLogVisitaBean = new AgendaLogVisitaBean();

                //
                agendaLogVisitaBean.setIdLog(xIdLog);

                //
                FachadaAgendaLogVisitaBean fachadaAgendaLogVisitaBean = new FachadaAgendaLogVisitaBean();

                //
                fachadaAgendaLogVisitaBean =
                        agendaLogVisitaBean.listaLogFachada();

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
                fachadaDctoOrdenBean =
                        dctoOrdenBean.listaDctoOrdenIdLog();

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
                String itemStr = "1";

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
                ColaboraDctoOrdenDetalleBean colaboraDctoOrdenDetalleBean =
                        new ColaboraDctoOrdenDetalleBean();

                //
                colaboraDctoOrdenDetalleBean.setItem(itemStr);
                colaboraDctoOrdenDetalleBean.setIdTipoOrden(
                        xIdTipoOrdenCotizacion);


                //
                FachadaDctoOrdenDetalleBean fachadaDctoOrdenDetalleBean =
                        new FachadaDctoOrdenDetalleBean();

                //
                fachadaDctoOrdenDetalleBean =
                        colaboraDctoOrdenDetalleBean.itemLogFachada(
                        new Integer(xIdLog).intValue());

                //
                Double xCantidad = fachadaDctoOrdenDetalleBean.getCantidad();

                //
                fachadaDctoOrdenDetalleBean.setItem(itemStr);
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

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaPluFicha", fachadaPluFicha);

                //
                FachadaPluFicha fachadaPluFichaPedido = new FachadaPluFicha();

                //
                DctoOrdenProgresoBean dctoOrdenProgresoBean =
                        new DctoOrdenProgresoBean();

                //
                dctoOrdenProgresoBean.setIdLocal(xIdLocal);
                dctoOrdenProgresoBean.setIdTipoOrden(xIdTipoOrden);
                dctoOrdenProgresoBean.setIdOrden(
                        fachadaDctoOrdenBean.getIdOrden());
                dctoOrdenProgresoBean.setIdOperacion(xIdOperacion);

                //
                FachadaDctoOrdenProgreso fachadaDctoOrdenConcluida
                                               = new FachadaDctoOrdenProgreso();

                //
                fachadaDctoOrdenConcluida =
                        dctoOrdenProgresoBean.listaTotalFCH();

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaDctoOrdenConcluida",
                        fachadaDctoOrdenConcluida);
                //
                return "/jsp/vtaFrmIngOTCosto.jsp";

            }

            //
            if (accionContenedor.compareTo("retira") == 0) {

                //
                String xIdLocal = request.getParameter("xIdLocal");
                String xIdTipoOrden = request.getParameter("xIdTipoOrden");
                String xIdOrden = request.getParameter("xIdOrden");
                String xIdLog = request.getParameter("xIdLog");
                String xIdPlu = request.getParameter("xIdPlu");
                String xIdOperacion = request.getParameter("xIdOperacion");
                String xItemPadre = request.getParameter("xItemPadre");
                String xPesoTerminado = request.getParameter("xPesoTerminado");

                //
                AgendaLogVisitaBean agendaLogVisitaBean
                                                    = new AgendaLogVisitaBean();

                //
                agendaLogVisitaBean.setIdLog(xIdLog);

                //
                FachadaAgendaLogVisitaBean fachadaAgendaLogVisitaBean
                                             = new FachadaAgendaLogVisitaBean();

                //
                fachadaAgendaLogVisitaBean =
                        agendaLogVisitaBean.listaLogFachada();

                //
                request.setAttribute("fachadaAgendaLogVisitaBean",
                        fachadaAgendaLogVisitaBean);

                //
                String itemStr = "1";

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
                FachadaDctoOrdenDetalleBean fachadaDctoOrdenDetalleBean =
                        new FachadaDctoOrdenDetalleBean();

                //
                ColaboraDctoOrdenDetalleBean colaboraDctoOrdenDetalleBean =
                        new ColaboraDctoOrdenDetalleBean();

                //
                colaboraDctoOrdenDetalleBean.setIdLocal(xIdLocal);
                colaboraDctoOrdenDetalleBean.setIdTipoOrden(xIdTipoOrden);
                colaboraDctoOrdenDetalleBean.setIdOrden(xIdOrden);

                //
                fachadaDctoOrdenDetalleBean =
                        colaboraDctoOrdenDetalleBean.listaOrdenOrigenFCH();

                //
                fachadaDctoOrdenDetalleBean.setIdLocal(
                                fachadaDctoOrdenDetalleBean.getIdLocalOrigen());
                fachadaDctoOrdenDetalleBean.setIdTipoOrden(
                            fachadaDctoOrdenDetalleBean.getIdTipoOrdenOrigen());
                fachadaDctoOrdenDetalleBean.setIdOrden(
                                fachadaDctoOrdenDetalleBean.getIdOrdenOrigen());
                fachadaDctoOrdenDetalleBean.setIdTipo(xIdTipoInventario);

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaDctoOrdenDetalleBean",
                        fachadaDctoOrdenDetalleBean);

                //
                FachadaDctoOrdenBean fachadaDctoOrdenBean
                                                   = new FachadaDctoOrdenBean();

                //
                DctoOrdenBean dctoOrdenBean = new DctoOrdenBean();

                //
                dctoOrdenBean.setIdLog(xIdLog);

                //
                fachadaDctoOrdenBean =
                        dctoOrdenBean.listaDctoOrdenIdLog();
                //
                int xIdFicha = fachadaDctoOrdenBean.getIdFicha();
                String xIdCliente = fachadaDctoOrdenBean.getIdCliente();
                String xIdTipoTerceroProveedor = "2";

                //
                fachadaDctoOrdenBean.setIdTipoTercero(xIdTipoTerceroProveedor);
                fachadaDctoOrdenBean.setIdOperacion(xIdOperacion);
                fachadaDctoOrdenBean.setItemPadre(xItemPadre);
                fachadaDctoOrdenBean.setIdLocal(
                                fachadaDctoOrdenDetalleBean.getIdLocalOrigen());
                fachadaDctoOrdenBean.setIdTipoOrden(
                            fachadaDctoOrdenDetalleBean.getIdTipoOrdenOrigen());
                fachadaDctoOrdenBean.setIdOrden(
                                fachadaDctoOrdenDetalleBean.getIdOrdenOrigen());

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaDctoOrdenBean", fachadaDctoOrdenBean);

                //--------------------------------------------------------------
                ColaboraOperacionPlu colaboraOperacionPlu =
                        new ColaboraOperacionPlu();

                //
                colaboraOperacionPlu.setIdFicha(xIdFicha);
                colaboraOperacionPlu.setIdOperacion(xIdOperacion);

                //
                ProcesoIngresoCosto proceso = new ProcesoIngresoCosto();

                //
                proceso.retira(new Integer(xIdLocal).intValue(),
                        xIdTipoOrdenTransformacion,
                        xIdUsuario,
                        xIdFicha,
                        new Integer(xIdOperacion).intValue(),
                        new Double(xPesoTerminado).doubleValue(),
                        xIdCliente,
                        fachadaDctoOrdenDetalleBean.getIdOrdenOrigen(),
                        fachadaDctoOrdenDetalleBean.getIdLocalOrigen(),
                        fachadaDctoOrdenDetalleBean.getIdTipoOrdenOrigen(),
                        new Integer(xIdPlu).intValue(),
                        new Integer(xItemPadre).intValue());

                //
                FichaTecnica fichaTecnica = new FichaTecnica();


                //
                double xPesoPedido = fichaTecnica.pesoPedido(xIdCliente,
                        new Integer(xIdFicha).intValue(),
                        new Double(xPesoTerminado).doubleValue());

                //
                FachadaPluFicha fachadaPluFicha = new FachadaPluFicha();

                //
                ColaboraOrdenTrabajo colaboraOrdenTrabajo
                                                   = new ColaboraOrdenTrabajo();

                //
                colaboraOrdenTrabajo.setIdFicha(xIdFicha);

                //
                fachadaPluFicha = colaboraOrdenTrabajo.listaIdFichaFCH();

                //
                fachadaPluFicha.setIdOperacion(xIdOperacion);
                fachadaPluFicha.setIdFicha(xIdFicha);
                fachadaPluFicha.setPesoPedido(xPesoPedido);
                fachadaPluFicha.setCantidad(xPesoTerminado);
                fachadaPluFicha.setIdOperacion(xIdOperacion);
                fachadaPluFicha.setIdFicha(xIdFicha);

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaPluFicha", fachadaPluFicha);

                //
                return "/jsp/vtaFrmIngOTCosto.jsp";
            }

            //
            if (accionContenedor.compareTo("Confirmar") == 0) {

                //
                String xIdLocal = request.getParameter("xIdLocal");
                String xIdTipoOrden = request.getParameter("xIdTipoOrden");
                String xIdLog = request.getParameter("xIdLog");
                String xPesoTerminado = request.getParameter("xPesoTerminado");
                String xIdPlu = request.getParameter("xIdPlu");
                String xIdOperacion = request.getParameter("xIdOperacion");
                String xItemPadre = request.getParameter("xItemPadre");
                //String xPesoPerdido = request.getParameter("xPesoPerdido");
                //String xIdOperario = request.getParameter("xIdTercero");
                //String xCantidadTerminada =
                //        request.getParameter("xCantidadTerminada");


                //
                Validacion valida = new Validacion();

                /*
                valida.reasignar("CAN.FIN", xCantidadTerminada);

                //
                valida.validarCampoDoublePositivo();

                //isValido
                if (!valida.isValido()) {

                    //
                    request.setAttribute("validacion", valida);
                    return "/jsp/gralError.jsp";
                }*/

                //
                valida.reasignar("KG.FIN", xPesoTerminado);

                //
                valida.validarCampoDoublePositivo();

                //isValido
                if (!valida.isValido()) {

                    //
                    request.setAttribute("validacion", valida);
                    return "/jsp/gralError.jsp";
                }

                //
                valida.reasignar("MATERIA PRIMA", xIdPlu);

                //
                valida.validarCampoEntero();

                //isValido
                if (!valida.isValido()) {

                    //
                    request.setAttribute("validacion", valida);
                    return "/jsp/gralError.jsp";
                }

                /*
                if (!((new Double(xPesoTerminado).doubleValue() != 0) ||
                   (new Double(xPesoPerdido).doubleValue() != 0))) {

                    //
                    valida.reasignar("ERROR, FALTA INGRESAR KG.FIN / KG.RET","");

                    //
                    request.setAttribute("validacion", valida);
                    return "/jsp/gralError.jsp";


                }*/

                //
                AgendaLogVisitaBean agendaLogVisitaBean
                                                    = new AgendaLogVisitaBean();

                //
                agendaLogVisitaBean.setIdLog(xIdLog);

                //
                FachadaAgendaLogVisitaBean fachadaAgendaLogVisitaBean
                                             = new FachadaAgendaLogVisitaBean();

                //
                fachadaAgendaLogVisitaBean =
                        agendaLogVisitaBean.listaLogFachada();

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
                fachadaDctoOrdenBean =
                        dctoOrdenBean.listaDctoOrdenIdLog();

                //
                int xIdLocalOrigen = fachadaDctoOrdenBean.getIdLocal();
                int xIdTipoOrdenOrigen = fachadaDctoOrdenBean.getIdTipoOrden();
                int xIdOrdenOrigen = fachadaDctoOrdenBean.getIdOrden();

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
                String itemStr = "1";

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
                        colaboraDctoOrdenDetalleBean.itemLogFachada(
                        new Integer(xIdLog).intValue());

                //
                Double xCantidad = fachadaDctoOrdenDetalleBean.getCantidad();
                
                //
                fachadaDctoOrdenDetalleBean.setIdTipo(xIdTipoInventario);

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaDctoOrdenDetalleBean",
                        fachadaDctoOrdenDetalleBean);


                //--------------------------------------------------------------
                ColaboraOperacionPlu colaboraOperacionPlu =
                        new ColaboraOperacionPlu();

                //
                colaboraOperacionPlu.setIdFicha(xIdFicha);
                colaboraOperacionPlu.setIdOperacion(xIdOperacion);

                //
                ProcesoIngresoCosto proceso = new ProcesoIngresoCosto();

                //
                proceso.ingresa(new Integer(xIdLocal).intValue(),
                        xIdTipoOrdenTransformacion,
                        xIdUsuario,
                        xIdFicha,
                        new Integer(xIdOperacion).intValue(),
                        new Double(xPesoTerminado).doubleValue(),
                        xIdCliente,
                        xIdOrdenOrigen,
                        xIdLocalOrigen,
                        xIdTipoOrdenOrigen,
                        new Integer(xIdPlu).intValue(),
                        new Integer(xItemPadre).intValue());

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

                //
                FachadaDctoOrdenProgreso fachadaDctoOrdenConcluida
                                               = new FachadaDctoOrdenProgreso();


                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaPluFicha", fachadaPluFicha);
                request.setAttribute("fachadaDctoOrdenConcluida",
                        fachadaDctoOrdenConcluida);

                //
                return "/jsp/vtaFrmIngOTCosto.jsp";
            }
        }

        //
        return "/jsp/empty.htm";
    }
}
