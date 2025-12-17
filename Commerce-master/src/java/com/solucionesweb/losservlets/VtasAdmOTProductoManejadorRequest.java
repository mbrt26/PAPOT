package com.solucionesweb.losservlets;

// Importa los paquetes del lenguaje especificamente io y los
import com.solucionesweb.lasayudas.ProcesoIngresoProduccion;
import com.solucionesweb.losbeans.colaboraciones.ColaboraDctoOrdenDetalleBean;
import com.solucionesweb.losbeans.colaboraciones.ColaboraOperacionPlu;
import com.solucionesweb.losbeans.colaboraciones.ColaboraOrdenTrabajo;
import com.solucionesweb.losbeans.colaboraciones.ColaboraPlu;
import com.solucionesweb.losbeans.colaboraciones.ColaboraTercero;
import com.solucionesweb.losbeans.fachada.FachadaAgendaLogVisitaBean;
import com.solucionesweb.losbeans.fachada.FachadaDctoOrdenBean;
import com.solucionesweb.losbeans.fachada.FachadaDctoOrdenDetalleBean;
import com.solucionesweb.losbeans.fachada.FachadaDctoOrdenProgreso;
import com.solucionesweb.losbeans.fachada.FachadaJobOperacion;
import com.solucionesweb.losbeans.fachada.FachadaJobOperacionCosto;
import com.solucionesweb.losbeans.fachada.FachadaPluBean;
import com.solucionesweb.losbeans.fachada.FachadaPluFicha;
import com.solucionesweb.losbeans.fachada.FachadaTerceroBean;
import com.solucionesweb.losbeans.negocio.AgendaLogVisitaBean;
import com.solucionesweb.losbeans.negocio.DctoOrdenBean;
import com.solucionesweb.losbeans.negocio.DctoOrdenDetalleBean;
import com.solucionesweb.losbeans.negocio.DctoOrdenProgresoBean;
import com.solucionesweb.losbeans.negocio.JobOperacionBean;
import com.solucionesweb.losbeans.negocio.JobOperacionCostoBean;
import com.solucionesweb.losbeans.negocio.UsuarioBean;
import com.solucionesweb.losbeans.utilidades.FichaTecnica;
import com.solucionesweb.losbeans.utilidades.Validacion;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * En esta interfaz permite registrar estado de producción del pedido /
 * vtaContenedorOTProducto.jsp / 
 * 
 * Este servlet implementa la interface GralManejadorRequest /
 */

public class VtasAdmOTProductoManejadorRequest implements GralManejadorRequest {
     
    /**
     * BUTTON--
     * ("Salir")-("Regresar")-Retorna  al menu principal /
     * ("Registro produccion")-Permite ingresar cantidades de matria producida /
     * Selecciona orden de trabajo /
     * ("Pendiente")-permite poner en pendiente la produccion /
     * ("Confirmar")-permite confirmar ingreso de registro de produccion /
     * ("Registro Tiempo Perdido")-permite ingresar tiempo de no produccion /
     * ("Confirmar Tiempo Perdido")-confirma ingreso de registro de tiempo /
     * 
     * Metodo contructor por defecto, es decir, sin parametros /
     */
    // 
    public VtasAdmOTProductoManejadorRequest() {
    }
    /**
     * PARAMETER BUTTON--
     * "Operario/Tercero"-Selecciona Tercero externo de produccion /
     * "Peso(Kg) Finalizado"- ingreso peso de materia prima en produccion /
     * "Peso(Kg) Tara"-ingreso peso tara /
     * "Cantidad Unidades"-ingrese cantidad en unidades de produccion /
     * "Peso(Kg) Retal"-ingreso peso en retales /
     * "Causa retal"-Selecciona causa /
     * "Fecha/Hora inicial"-ingreso fecha y hora inicial/
     * "Fecha/Hora final"-Ingreso fehca y hora limite
     * "Causal Tiempo Perdido"-seleccione causa de tiempo perdido /
     */

    public String generaPdf(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        //
        int xIdTipoOrdenTransformacion = 11;
        double xCantidadPendienteCero = 0.0;
        String xObservacionVacia = "";

        //
        HttpSession sesion = request.getSession();
        UsuarioBean usuarioBean =
                (UsuarioBean) sesion.getAttribute("usuarioBean");

        //
        double xIdUsuario = usuarioBean.getIdUsuario();
        int xIdLocalUsuario = usuarioBean.getIdLocalUsuario();
        int xIdTipoTercero = 1;

        //
        String accionContenedor = request.getParameter("accionContenedor");

        //
        System.out.println(accionContenedor);

        // Validacion de accion relacionada con el formulario requerido
        if (accionContenedor != null) {

            //
            if (accionContenedor.compareTo("Regresar") == 0) {

                //
                return "/jsp/empty.htm";
            }

            // Consultar Operacion
            if (accionContenedor.compareTo("Consultar Pedido") == 0) {

                //
                String xIdOperacion = request.getParameter("xIdOperacion");

                //
                FachadaPluFicha fachadaPluFicha = new FachadaPluFicha();

                //
                fachadaPluFicha.setIdOperacion(xIdOperacion);

                //
                request.setAttribute("fachadaPluFicha", fachadaPluFicha);

                //
                return "/jsp/vtaFrmMaqOTProducto.jsp";

            }

            // Consultar Operacion
            if (accionContenedor.compareTo("Consultar Maquina") == 0) {

                //
                String xIdOperacion = request.getParameter("xIdOperacion");

                //
                FachadaPluFicha fachadaPluFicha = new FachadaPluFicha();

                //
                fachadaPluFicha.setIdOperacion(xIdOperacion);

                //
                request.setAttribute("fachadaPluFicha", fachadaPluFicha);

                //
                return "/jsp/vtaFrmMaqOTProducto.jsp";

            }

            // Consultar Operacion
            if (accionContenedor.compareTo("Consultar Operacion") == 0) {

                //
                String xIdOperacion = request.getParameter("xIdOperacion");

                //
                FachadaPluFicha fachadaPluFicha = new FachadaPluFicha();

                //
                fachadaPluFicha.setIdOperacion(xIdOperacion);

                //
                request.setAttribute("fachadaPluFicha", fachadaPluFicha);

                //
                return "/jsp/vtaFrmConOTProducto.jsp";

            }

            // Registro Tiempo Perdido
            if (accionContenedor.compareTo("Registro Tiempo Perdido") == 0) {

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
                int xItem = 1;
                int xIdOperacionINI = 1;

                //
                FachadaPluFicha fachadaPluFicha = new FachadaPluFicha();

                //
                ColaboraOrdenTrabajo colaboraOrdenTrabajo = new ColaboraOrdenTrabajo();

                //
                colaboraOrdenTrabajo.setNumeroOrden(xNumeroOrden);
                colaboraOrdenTrabajo.setIdOperacion(xIdOperacionINI);
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
                fachadaDctoOrdenBean.setIdTipoTercero(xIdTipoTercero);

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
                return "/jsp/vtaFrmTieOTProducto.jsp";

            }

            // Registro Produccion
            if (accionContenedor.compareTo("Registro Produccion") == 0) {

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
                int xItem = 1;
                int xIdOperacionINI = 1;

                //
                FachadaPluFicha fachadaPluFicha = new FachadaPluFicha();

                //
                ColaboraOrdenTrabajo colaboraOrdenTrabajo = new ColaboraOrdenTrabajo();

                //
                colaboraOrdenTrabajo.setNumeroOrden(xNumeroOrden);
                colaboraOrdenTrabajo.setIdOperacion(xIdOperacionINI);
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
                fachadaDctoOrdenBean.setIdTipoTercero(xIdTipoTercero);

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
                return "/jsp/vtaFrmLstOTProducto.jsp";

            }

            // traetiempo
            if (accionContenedor.compareTo("traetiempo") == 0) {

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
                ColaboraDctoOrdenDetalleBean colaboraDctoOrdenDetalleBean =
                        new ColaboraDctoOrdenDetalleBean();

                //
                colaboraDctoOrdenDetalleBean.setItem(xItemPadre);
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

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaPluFicha", fachadaPluFicha);

                //
                DctoOrdenProgresoBean dctoOrdenProgresoBean =
                        new DctoOrdenProgresoBean();

                //
                dctoOrdenProgresoBean.setIdLocal(xIdLocal);
                dctoOrdenProgresoBean.setIdTipoOrden(xIdTipoOrden);
                dctoOrdenProgresoBean.setIdOrden(
                        fachadaDctoOrdenBean.getIdOrden());
                dctoOrdenProgresoBean.setIdOperacion(xIdOperacion);
                dctoOrdenProgresoBean.setItemPadre(xItemPadre);

                //
                FachadaDctoOrdenProgreso fachadaDctoOrdenConcluida = new FachadaDctoOrdenProgreso();

                //
                fachadaDctoOrdenConcluida =
                        dctoOrdenProgresoBean.listaTotalFCH();

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaDctoOrdenConcluida",
                        fachadaDctoOrdenConcluida);
                //
                return "/jsp/vtaFrmPerOTProducto.jsp";

            }

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
                ColaboraDctoOrdenDetalleBean colaboraDctoOrdenDetalleBean =
                        new ColaboraDctoOrdenDetalleBean();

                //
                colaboraDctoOrdenDetalleBean.setItem(xItemPadre);
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

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaPluFicha", fachadaPluFicha);

                //
                DctoOrdenProgresoBean dctoOrdenProgresoBean =
                        new DctoOrdenProgresoBean();

                //
                dctoOrdenProgresoBean.setIdLocal(xIdLocal);
                dctoOrdenProgresoBean.setIdTipoOrden(xIdTipoOrden);
                dctoOrdenProgresoBean.setIdOrden(
                        fachadaDctoOrdenBean.getIdOrden());
                dctoOrdenProgresoBean.setIdOperacion(xIdOperacion);
                dctoOrdenProgresoBean.setItemPadre(xItemPadre);

                //
                FachadaDctoOrdenProgreso fachadaDctoOrdenConcluida = new FachadaDctoOrdenProgreso();

                //
                fachadaDctoOrdenConcluida =
                        dctoOrdenProgresoBean.listaTotalFCH();

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaDctoOrdenConcluida",
                        fachadaDctoOrdenConcluida);
                //
                return "/jsp/vtaFrmIngOTProducto.jsp";

            }

            //
            if (accionContenedor.compareTo("retira") == 0) {

                //
                String xIdLocal = request.getParameter("xIdLocal");
                String xIdTipoOrden = request.getParameter("xIdTipoOrden");
                String xIdLog = request.getParameter("xIdLog");
                String xItem = request.getParameter("xItem");
                String xIdOperacion = request.getParameter("xIdOperacion");
                String xItemPadre = request.getParameter("xItemPadre");

                //---
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
                int xIdOrden = fachadaDctoOrdenBean.getIdOrden();
                String xIdTipoTerceroProveedor = "1";
                //
                fachadaDctoOrdenBean.setIdTipoTercero(xIdTipoTerceroProveedor);
                fachadaDctoOrdenBean.setIdOperacion(xIdOperacion);
                fachadaDctoOrdenBean.setItemPadre(xItemPadre);

                //
                fachadaDctoOrdenBean.setIdTipoTercero(xIdTipoTerceroProveedor);
                fachadaDctoOrdenBean.setIdOperacion(xIdOperacion);
                fachadaDctoOrdenBean.setItemPadre(xItemPadre);

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaDctoOrdenBean", fachadaDctoOrdenBean);

                //--------------------------------------------------------------
                FachadaDctoOrdenProgreso fachadaDctoOrdenProgreso = new FachadaDctoOrdenProgreso();

                //
                DctoOrdenProgresoBean dctoOrdenProgresoBean = new DctoOrdenProgresoBean();

                //
                dctoOrdenProgresoBean.setIdLocal(xIdLocal);
                dctoOrdenProgresoBean.setIdTipoOrden(xIdTipoOrden);
                dctoOrdenProgresoBean.setIdOrden(xIdOrden);
                dctoOrdenProgresoBean.setIdOperacion(xIdOperacion);
                dctoOrdenProgresoBean.setItemPadre(xItemPadre);
                dctoOrdenProgresoBean.setItem(xItem);

                //
                fachadaDctoOrdenProgreso =
                        dctoOrdenProgresoBean.listaItemFCH();

                //
                fachadaDctoOrdenProgreso.setItem(xItem);

                //
                request.setAttribute("fachadaDctoOrdenProgreso",
                        fachadaDctoOrdenProgreso);

                //---
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
                fachadaPluFicha.setIdOperacion(xIdOperacion);
                fachadaPluFicha.setIdFicha(xIdFicha);

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaPluFicha", fachadaPluFicha);

                //
                return "/jsp/vtaFrmRetOTProducto.jsp";


            }

            //
            if (accionContenedor.compareTo("Confirmar Retiro") == 0) {

                //
                String xIdLocal = request.getParameter("xIdLocal");
                String xIdTipoOrden = request.getParameter("xIdTipoOrden");
                String xIdLog = request.getParameter("xIdLog");
                String xItem = request.getParameter("xItem");
                String xIdOperacion = request.getParameter("xIdOperacion");
                String xItemPadre = request.getParameter("xItemPadre");
                String xObservacion = request.getParameter("xObservacion");

                //
                Validacion validacion = new Validacion();

                //
                validacion.reasignar("OBSERVACION", xObservacion);

                //
                validacion.validarCampoString();

                //
                if (!validacion.isValido()) {

                    // Aqui escribe el Bean de Validacion en el Request para manejar el error
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralError.jsp";

                }

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
                int xIdOrden = fachadaDctoOrdenBean.getIdOrden();
                int xNumeroOrden = fachadaDctoOrdenBean.getNumeroOrden();
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
                FachadaDctoOrdenDetalleBean fachadaDctoOrdenDetalleBean =
                        new FachadaDctoOrdenDetalleBean();

                //
                ColaboraDctoOrdenDetalleBean colaboraDctoOrdenDetalleBean =
                        new ColaboraDctoOrdenDetalleBean();

                //
                colaboraDctoOrdenDetalleBean.setItem(xItemPadre);
                colaboraDctoOrdenDetalleBean.setIdTipoOrden(
                        xIdTipoOrdenCotizacion);

                //
                fachadaDctoOrdenDetalleBean =
                        colaboraDctoOrdenDetalleBean.itemLogFachada(
                        new Integer(xIdLog).intValue());

                //
                Double xCantidad = fachadaDctoOrdenDetalleBean.getCantidad();

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaDctoOrdenDetalleBean",
                        fachadaDctoOrdenDetalleBean);

                //--------------------------------------------------------------
                FachadaDctoOrdenProgreso fachadaDctoOrdenProgreso = new FachadaDctoOrdenProgreso();

                //
                DctoOrdenProgresoBean dctoOrdenProgresoBean = new DctoOrdenProgresoBean();

                dctoOrdenProgresoBean.setIdLocal(xIdLocal);
                dctoOrdenProgresoBean.setIdTipoOrden(xIdTipoOrden);
                dctoOrdenProgresoBean.setIdOrden(
                        fachadaDctoOrdenDetalleBean.getIdOrden());
                dctoOrdenProgresoBean.setIdOperacion(xIdOperacion);
                dctoOrdenProgresoBean.setItem(xItem);

                //
                fachadaDctoOrdenProgreso =
                        dctoOrdenProgresoBean.listaFCH();

                //
                int xIdOrdenOrigen =
                        fachadaDctoOrdenProgreso.getIdOrden();
                int xIdLocalOrigen =
                        fachadaDctoOrdenProgreso.getIdLocal();
                int xIdTipoOrdenOrigen =
                        fachadaDctoOrdenProgreso.getIdTipoOrden();
                double xPesoTerminado =
                        fachadaDctoOrdenProgreso.getPesoTerminado();
                double xCantidadTerminada =
                        fachadaDctoOrdenProgreso.getCantidadTerminada();

                //
                dctoOrdenProgresoBean.setIdLocal(xIdLocal);
                dctoOrdenProgresoBean.setIdTipoOrden(xIdTipoOrden);
                dctoOrdenProgresoBean.setIdOrden(
                        fachadaDctoOrdenBean.getIdOrden());
                dctoOrdenProgresoBean.setItem(xItem);
                dctoOrdenProgresoBean.setIdOperacion(xIdOperacion);
                dctoOrdenProgresoBean.setItemPadre(xItemPadre);

                //
                dctoOrdenProgresoBean.retira();

                //--------------------------------------------------------------
                DctoOrdenDetalleBean dctoOrdenDetalleBean 
                                                   = new DctoOrdenDetalleBean();

                //
                dctoOrdenDetalleBean.actualizaOT_Almacen();
                
                
                //--------------------------------------------------------------
                int xIdOperacionTerminados = 999;
                int xIdTipoOrdenPedido = 59;
                
                //
                dctoOrdenDetalleBean.setIdLocal(xIdLocal);
                dctoOrdenDetalleBean.setIdTipoOrden(xIdTipoOrdenPedido);
                dctoOrdenDetalleBean.setIdOperacion(xIdOperacionTerminados);                                
                dctoOrdenDetalleBean.setItem(xItemPadre);                
                
                //--
                dctoOrdenDetalleBean.actualizaOT_SinInicio();                    
                

                //--------------------------------------------------------------
                ColaboraOperacionPlu colaboraOperacionPlu =
                                                     new ColaboraOperacionPlu();

                //
                colaboraOperacionPlu.setIdFicha(xIdFicha);
                colaboraOperacionPlu.setIdOperacion(xIdOperacion);

                //
                double xTotalMP = colaboraOperacionPlu.totalMP();

                //
                ProcesoIngresoProduccion proceso = new ProcesoIngresoProduccion();

                //
                proceso.retira(new Integer(xIdLocal).intValue(),
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
                FichaTecnica fichaTecnica = new FichaTecnica();


                //
                double xPesoPedido = fichaTecnica.pesoPedido(xIdCliente,
                        new Integer(xIdFicha).intValue(),
                        new Double(xCantidad).doubleValue());

                //---
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
                FachadaDctoOrdenProgreso fachadaDctoOrdenConcluida = new FachadaDctoOrdenProgreso();

                //
                fachadaDctoOrdenConcluida =
                        dctoOrdenProgresoBean.listaTotalFCH();

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaDctoOrdenConcluida",
                        fachadaDctoOrdenConcluida);

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaPluFicha", fachadaPluFicha);

                //
                return "/jsp/vtaFrmIngOTProducto.jsp";
            }

            // Pendiente
            if (accionContenedor.compareTo("Pendiente") == 0) {

                //
                String xIdLocal = request.getParameter("xIdLocal");
                String xIdTipoOrden = request.getParameter("xIdTipoOrden");
                String xIdLog = request.getParameter("xIdLog");
                String xItemPadre = request.getParameter("xItemPadre");

                //---
                DctoOrdenDetalleBean dctoOrdenDetalleBean = new DctoOrdenDetalleBean();

                //
                dctoOrdenDetalleBean.setIdLocal(xIdLocal);
                dctoOrdenDetalleBean.setIdTipoOrden(xIdTipoOrden);
                dctoOrdenDetalleBean.setIdLog(xIdLog);
                dctoOrdenDetalleBean.setItem(xItemPadre);

                //
                dctoOrdenDetalleBean.actualizaPedidoPendiente();

                //---
                AgendaLogVisitaBean agendaLogVisitaBean = new AgendaLogVisitaBean();

                //
                int xEstadoSuspendido = 8;

                //
                agendaLogVisitaBean.setEstado(xEstadoSuspendido);
                agendaLogVisitaBean.setIdLog(xIdLog);

                //
                agendaLogVisitaBean.actualizaLogVisita();

                //
                return "/jsp/vtaContenedorOTProducto.jsp";

            }

            //
            if (accionContenedor.compareTo("Confirmar Tiempo Perdido") == 0) {

                //
                String xIdLocal = request.getParameter("xIdLocal");
                String xIdTipoOrden = request.getParameter("xIdTipoOrden");
                String xIdLog = request.getParameter("xIdLog");
                String xCantidadTerminada = "0.0";
                String xPesoTerminado = "0.0";
                String xPesoPerdido = "0.0";
                String xIdOperario = request.getParameter("xIdTercero");
                String xEstado = "1";
                String xIdOperacion = request.getParameter("xIdOperacion");
                String xItemPadre = request.getParameter("xItemPadre");
                String xPesoTara = "0.0";
                String xIdCausa = request.getParameter("xIdCausa");
                String xFechaHoraInicio = request.getParameter("xFechaHoraInicio");
                String xFechaHoraFin = request.getParameter("xFechaHoraFin");

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
                valida.reasignar("FECHA/HORA INICIAL", xFechaHoraInicio);

                //
                valida.validarCampoString();

                //isValido
                if (!valida.isValido()) {

                    //
                    request.setAttribute("validacion", valida);
                    return "/jsp/gralError.jsp";
                }

                //
                valida.reasignar("FECHA/HORA FINAL", xFechaHoraInicio);

                //
                valida.validarCampoString();

                //isValido
                if (!valida.isValido()) {

                    //
                    request.setAttribute("validacion", valida);
                    return "/jsp/gralError.jsp";
                }

                //
                AgendaLogVisitaBean agendaLogVisitaBean = new AgendaLogVisitaBean();

                //
                agendaLogVisitaBean.setIdLog(xIdLog);

                //
                FachadaAgendaLogVisitaBean fachadaAgendaLogVisitaBean = new FachadaAgendaLogVisitaBean();

                //---
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
                FachadaDctoOrdenDetalleBean fachadaDctoOrdenDetalleBean =
                        new FachadaDctoOrdenDetalleBean();

                //
                ColaboraDctoOrdenDetalleBean colaboraDctoOrdenDetalleBean =
                        new ColaboraDctoOrdenDetalleBean();

                //
                colaboraDctoOrdenDetalleBean.setItem(xItemPadre);
                colaboraDctoOrdenDetalleBean.setIdTipoOrden(
                        xIdTipoOrdenCotizacion);

                //
                fachadaDctoOrdenDetalleBean =
                        colaboraDctoOrdenDetalleBean.itemLogFachada(
                        new Integer(xIdLog).intValue());

                //
                Double xCantidad = fachadaDctoOrdenDetalleBean.getCantidad();
                Double xCantidadPerdida = 0.0;

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaDctoOrdenDetalleBean",
                        fachadaDctoOrdenDetalleBean);

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
                JobOperacionCostoBean jobOperacionCostoBean =
                        new JobOperacionCostoBean();

                //---
                jobOperacionCostoBean.setIdLocal(xIdLocal);
                jobOperacionCostoBean.setIdOperacion(xIdOperacion);

                //---
                fachadaJobOperacionCosto =
                        jobOperacionCostoBean.listaCostoFCH();

                //---
                double xVrCostoBaseMAT =
                        fachadaJobOperacionCosto.getVrCostoBaseMAT();
                double xVrCostoBaseMOD =
                        fachadaJobOperacionCosto.getVrCostoBaseMOD();
                double xVrCostoBaseCIF =
                        fachadaJobOperacionCosto.getVrCostoBaseCIF();

                //---
                int xIdTipoCausaTiempoPerdido = 1;

                //--------------------------------------------------------------
                dctoOrdenProgresoBean.setItem(xMaximoItem);
                dctoOrdenProgresoBean.setIdOperario(xIdOperario);
                dctoOrdenProgresoBean.setCantidadPerdida(xCantidadPerdida);
                dctoOrdenProgresoBean.setCantidadTerminada(xCantidadTerminada);
                dctoOrdenProgresoBean.setPesoTerminado(xPesoTerminado);
                dctoOrdenProgresoBean.setPesoPerdido(xPesoPerdido);
                dctoOrdenProgresoBean.setIdCausa(xIdCausa);
                dctoOrdenProgresoBean.setEstado(xEstado);
                dctoOrdenProgresoBean.setItemPadre(xItemPadre);
                dctoOrdenProgresoBean.setVrCostoBaseMAT(xVrCostoBaseMAT);
                dctoOrdenProgresoBean.setVrCostoBaseCIF(xVrCostoBaseCIF);
                dctoOrdenProgresoBean.setVrCostoBaseMOD(xVrCostoBaseMOD);
                dctoOrdenProgresoBean.setPesoTara(xPesoTara);
                dctoOrdenProgresoBean.setIdTipoCausa(xIdTipoCausaTiempoPerdido);
                dctoOrdenProgresoBean.setFechaHoraInicio(xFechaHoraInicio);
                dctoOrdenProgresoBean.setFechaHoraFin(xFechaHoraFin);
                dctoOrdenProgresoBean.setIdUsuario(xIdUsuario);
                dctoOrdenProgresoBean.setCantidadPendiente(xCantidadPendienteCero);
                dctoOrdenProgresoBean.setObservacion(xObservacionVacia);

                //---
                dctoOrdenProgresoBean.ingresaJQuery();

                //--------------------------------------------------------------
                ColaboraOperacionPlu colaboraOperacionPlu =
                        new ColaboraOperacionPlu();

                //
                colaboraOperacionPlu.setIdFicha(xIdFicha);
                colaboraOperacionPlu.setIdOperacion(xIdOperacion);

                //
                double xTotalMP = colaboraOperacionPlu.totalMP();

                //
                String xObservacion = "";

                //
                ProcesoIngresoProduccion proceso = new ProcesoIngresoProduccion();

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
                        xObservacion);


                //--------------------------------------------------------------
                DctoOrdenDetalleBean dctoOrdenDetalleBean = new DctoOrdenDetalleBean();

                //
                dctoOrdenDetalleBean.actualizaOT_Almacen();

                //
                return "/jsp/vtaContenedorOTProducto.jsp";

            }

            //
            if (accionContenedor.compareTo("Confirmar") == 0) {


                //-------------------------ingreso -----------------------------
                String xIdLocal = request.getParameter("xIdLocal");
                String xIdTipoOrden = request.getParameter("xIdTipoOrden");
                String xIdLog = request.getParameter("xIdLog");
                String xCantidadTerminada =
                        request.getParameter("xCantidadTerminada");
                String xPesoTerminado = request.getParameter("xPesoTerminado");
                String xPesoPerdido = request.getParameter("xPesoPerdido");
                String xIdOperario = request.getParameter("xIdTercero");
                String xEstado = "1";
                String xIdOperacion = request.getParameter("xIdOperacion");
                String xItemPadre = request.getParameter("xItemPadre");
                String xPesoTara = request.getParameter("xPesoTara");
                String xIdCausa = "0";


                //--------------------------------------------------------------
                if ((xIdOperacion.compareTo("2") == 0)
                        || (xIdOperacion.compareTo("3") == 0)
                        || (xIdOperacion.compareTo("4") == 0)) {

                    //
                    xCantidadTerminada = request.getParameter("xPesoTerminado");

                }

                //------------------------- validacion -------------------------
                Validacion valida = new Validacion();

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

                //------------------------- traer O.T. -------------------------
                AgendaLogVisitaBean agendaLogVisitaBean = new AgendaLogVisitaBean();

                //
                agendaLogVisitaBean.setIdLog(xIdLog);

                //
                FachadaAgendaLogVisitaBean fachadaAgendaLogVisitaBean = new FachadaAgendaLogVisitaBean();

                //---
                fachadaAgendaLogVisitaBean =
                        agendaLogVisitaBean.listaLogFachada();

                //
                request.setAttribute("fachadaAgendaLogVisitaBean",
                        fachadaAgendaLogVisitaBean);

                //--------------------------------------------------------------
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
                boolean xOkExcedePeso =
                        dctoOrdenProgresoBean.validaPesoAdicionTerminado(
                        xPorcentajeAdicion,
                        new Double(xPesoTerminado).doubleValue());

                //--
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
                FachadaDctoOrdenDetalleBean fachadaDctoOrdenDetalleBean =
                        new FachadaDctoOrdenDetalleBean();

                //
                ColaboraDctoOrdenDetalleBean colaboraDctoOrdenDetalleBean =
                        new ColaboraDctoOrdenDetalleBean();

                //
                colaboraDctoOrdenDetalleBean.setItem(xItemPadre);
                colaboraDctoOrdenDetalleBean.setIdTipoOrden(
                        xIdTipoOrdenCotizacion);

                //
                fachadaDctoOrdenDetalleBean =
                        colaboraDctoOrdenDetalleBean.itemLogFachada(
                        new Integer(xIdLog).intValue());

                //
                Double xCantidad = fachadaDctoOrdenDetalleBean.getCantidad();
                Double xCantidadPerdida = 0.0;

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaDctoOrdenDetalleBean",
                        fachadaDctoOrdenDetalleBean);

                //----------------------- ingresar  documento ------------------
                ColaboraOperacionPlu colaboraOperacionPlu =
                        new ColaboraOperacionPlu();

                //
                colaboraOperacionPlu.setIdFicha(xIdFicha);
                colaboraOperacionPlu.setIdOperacion(xIdOperacion);

                //
                double xTotalMP = colaboraOperacionPlu.totalMP();
                String xObservacion = "";

                //-------------------------- detalle ---------------------------
                ProcesoIngresoProduccion proceso = new ProcesoIngresoProduccion();

                //
                int xIdOrdenCruce =
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
                        xObservacion);

                //--------------------------------------------------------------
                int xMaximoItem = dctoOrdenProgresoBean.maximoItem() + 1;

                //---
                FachadaJobOperacionCosto fachadaJobOperacionCosto = new FachadaJobOperacionCosto();

                //
                JobOperacionCostoBean jobOperacionCostoBean =
                        new JobOperacionCostoBean();

                //---
                jobOperacionCostoBean.setIdLocal(xIdLocal);
                jobOperacionCostoBean.setIdOperacion(xIdOperacion);

                //---
                fachadaJobOperacionCosto =
                        jobOperacionCostoBean.listaCostoFCH();

                //---
                double xVrCostoBaseMAT =
                        fachadaJobOperacionCosto.getVrCostoBaseMAT();
                double xVrCostoBaseMOD =
                        fachadaJobOperacionCosto.getVrCostoBaseMOD();
                double xVrCostoBaseCIF =
                        fachadaJobOperacionCosto.getVrCostoBaseCIF();

                //---
                int xIdTipoCausaProduccion = 0;
                int xIdControlTipoInterna = 0;
                String xIdDctoNitCCVacio = "";

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
                dctoOrdenProgresoBean.setIdUsuario(xIdUsuario);
                dctoOrdenProgresoBean.setCantidadPendiente(xCantidadPendienteCero);
                dctoOrdenProgresoBean.setObservacion(xObservacionVacia);
                dctoOrdenProgresoBean.setIdOrdenCruce(xIdOrdenCruce);
                dctoOrdenProgresoBean.setIdDctoNitCC(xIdDctoNitCCVacio);
                dctoOrdenProgresoBean.setIdControlTipo(xIdControlTipoInterna);

                //---
                dctoOrdenProgresoBean.ingresa();

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
                fachadaJobOperacion =
                        jobOperacionBean.listaOperacionActualSiguienteFCH(
                        xIdFicha,
                        xIdEscala);

                //
                int xIdBodegaActual = fachadaJobOperacion.getIdOperacion();
                int xIdBodegaSiguiente = fachadaJobOperacion.getIdOperacionSiguiente();

//--------------------------
                //
                if (xIdBodegaSiguiente == xIdBodegaTerminados) {

                    //-------------------------- detalle -----------------------
                    xIdOrdenCruce =
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
                            xObservacion);

                    //----------------------------------------------------------
                    dctoOrdenDetalleBean.setIdLocal(xIdLocal);
                    dctoOrdenDetalleBean.setIdTipoOrden(xIdTipoOrdenTransformacion);
                    dctoOrdenDetalleBean.setIdOrden(xIdOrdenCruce);
                    dctoOrdenDetalleBean.setIdOperacion(xIdBodegaActual);

                    //
                    dctoOrdenDetalleBean.retiraUltimaOperacion();

                    //----------------------------------------------------------
                    int xMaximoItemTerminados =
                            dctoOrdenProgresoBean.maximoItem() + 1;

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
                    dctoOrdenProgresoBean.setIdUsuario(xIdUsuario);
                    dctoOrdenProgresoBean.setCantidadPendiente(
                            xCantidadPendienteCero);
                    dctoOrdenProgresoBean.setObservacion(xObservacionVacia);
                    dctoOrdenProgresoBean.setIdOperacion(xIdBodegaTerminados);
                    dctoOrdenProgresoBean.setIdOrdenCruce(xIdOrdenCruce);
                    dctoOrdenProgresoBean.setIdDctoNitCC(xIdDctoNitCCVacio);
                    dctoOrdenProgresoBean.setIdControlTipo(xIdControlTipoInterna);

                    //---
                    dctoOrdenProgresoBean.ingresa();

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
                FachadaDctoOrdenProgreso fachadaDctoOrdenConcluida = new FachadaDctoOrdenProgreso();

                //
                fachadaDctoOrdenConcluida =
                        dctoOrdenProgresoBean.listaTotalFCH();

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaPluFicha", fachadaPluFicha);
                request.setAttribute("fachadaDctoOrdenConcluida",
                        fachadaDctoOrdenConcluida);

                //--- actualizaPedidoPendiente ---------------------------------
                dctoOrdenDetalleBean.setIdLocal(xIdLocal);
                dctoOrdenDetalleBean.setIdTipoOrden(xIdTipoOrden);
                dctoOrdenDetalleBean.setIdLog(xIdLog);
                dctoOrdenDetalleBean.setItem(xItemPadre);

                //
                dctoOrdenDetalleBean.actualizaPedidoPendiente();

                //
                return "/jsp/vtaFrmIngOTProducto.jsp";
            }
        }

        //
        return "/jsp/empty.htm";
    }
}
