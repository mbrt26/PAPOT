package com.solucionesweb.losservlets;

// Importa los paquetes del lenguaje especificamente io y los
import com.solucionesweb.lasayudas.ProcesoGuardaInventario;
import com.solucionesweb.lasayudas.ProcesoIngresoES;
import com.solucionesweb.lasayudas.ProcesoIngresoProduccion;
import com.solucionesweb.lasayudas.ProcesoInternoInventario;
import com.solucionesweb.lasayudas.ProcesoIp;
import com.solucionesweb.lasayudas.ProcesoOTTraslado;
import com.solucionesweb.lasayudas.ProcesoSalidaExterno;
import com.solucionesweb.losbeans.colaboraciones.ColaboraDctoOrdenDetalleBean;
import com.solucionesweb.losbeans.colaboraciones.ColaboraOperacionPlu;
import com.solucionesweb.losbeans.colaboraciones.ColaboraOrdenTrabajo;
import com.solucionesweb.losbeans.colaboraciones.ColaboraPlu;
import com.solucionesweb.losbeans.colaboraciones.ColaboraTercero;
import com.solucionesweb.losbeans.fachada.FachadaAgendaLogVisitaBean;
import com.solucionesweb.losbeans.fachada.FachadaColaboraHistoriaBean;
import com.solucionesweb.losbeans.fachada.FachadaDctoBean;
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
import com.solucionesweb.losbeans.utilidades.Day;
import com.solucionesweb.losbeans.utilidades.FichaTecnica;
import com.solucionesweb.losbeans.utilidades.JhDate;
import com.solucionesweb.losbeans.utilidades.Validacion;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * En esta ventana muestra las operaciones de la producción que es desarrollada por terceros. /
 * vtaContenedorOTExterna.jsp /
 * 
 * Este servlet implementa la interface GralManejadorRequest /
 */

public class VtasAdmOTExternaManejadorRequest implements GralManejadorRequest {
    
    /**
     * BUTTON--
     * ("Salir")-("Regresar")-Retorna al menu principal /
     * ("Traer")-Permite el ingreso de produccion externa /
     * ("Retira")-Permite eliminar producido ingresado /
     * ("Listar")-Genera pef de produccion /
     * ("Confirmar Salida")-genera pdf de operacion/
     * ("Confirmar Entrada")-genera pdf de operacion /
     * 
     * Metodo contructor por defecto, es decir, sin parametros /
     */

    public VtasAdmOTExternaManejadorRequest() { }
    /**
     * BUTTON PARAMETER--
     * "Operario/Tercero"-Selecciona operario externo /
     * "#O.T"-Ingresa numero de orden de trabajo /
     * "Peso(Kg) Finalizado"-Ingreso de material finalizado /
     * "#Dcto tercero"-Ingreso de descuento de tercero /
     * 
     * Metodo contructor por defecto, es decir, sin parametros /
     */

    public String generaPdf(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        //
        int xIdTipoOrdenTransformacion = 11;
        double xCantidadPendienteCero = 0.0;
        String xObservacionVacia = "";
       //
        int xIdTipoOrdenInterno = 4;
        int xIdTipoOrdenPedidoProceso = 59;
        int xEstadoActivo = 9;
        int xIdListaPrecio = 1;
        int xIdTipoTerceroProveedor = 2;

        //
        String xIdTipoOrden = request.getParameter("xIdTipoOrden");

        //
        int xIdTipoOrdenProceso = new Integer(xIdTipoOrden).intValue() + 50 ;

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
                return "/jsp/vtaFrmMaqOTExterna.jsp";

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
                return "/jsp/vtaFrmMaqOTExterna.jsp";

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
                return "/jsp/vtaFrmConOTExterna.jsp";

            }
            
            //
            if (accionContenedor.compareTo("listaSalida") == 0) {

                //
                String xIdLocal = request.getParameter("xIdLocal");
//                String xIdTipoOrden = request.getParameter("xIdTipoOrden");
                String xIdLog = request.getParameter("xIdLog");                
                String xItem = request.getParameter("xItem");                
                String xIdOperacion = request.getParameter("xIdOperacion");    
                String xItemPadre = request.getParameter("xItemPadre");                    
                String xIdControl = request.getParameter("xIdControl");                                    
                
                //--------------------------------------------------------------
                int xIdControlTipoOTExterna = 2;
                
                //
                DctoOrdenBean dctoOrdenBean = new DctoOrdenBean();
                
                //
                FachadaDctoOrdenBean fachadaDctoOrdenBean 
                                                   = new FachadaDctoOrdenBean();

                //
                dctoOrdenBean.setIdLog(xIdLog);

                //
                fachadaDctoOrdenBean =
                        dctoOrdenBean.listaDctoOrdenIdLog();

                //
                int xIdOrden = fachadaDctoOrdenBean.getIdOrden(); 
                
                //
                GeneraPDFExterna generaPDFServlet = new GeneraPDFExterna();

                //
                generaPDFServlet.setIdLocal(xIdLocal);
                generaPDFServlet.setIdTipoOrden(xIdTipoOrden);
                generaPDFServlet.setIdOrden(xIdOrden);
                generaPDFServlet.setIdControl(xIdControl);
                generaPDFServlet.setIdOperacion(new Integer(xIdOperacion).intValue());
                generaPDFServlet.setItemPadre(new Integer(xItemPadre).intValue());
                generaPDFServlet.setIdControlTipo(xIdControlTipoOTExterna);
                generaPDFServlet.setNombreReporte("RepOTExternaSalida");
                generaPDFServlet.setTituloReporte("SALIDA");

                //
                generaPDFServlet.generaPdf(request, response);                  
                
            }               
            
            //
            if (accionContenedor.compareTo("listaEntrada") == 0) {

                //
                String xIdLocal = request.getParameter("xIdLocal");
//                String xIdTipoOrden = request.getParameter("xIdTipoOrden");
                String xIdLog = request.getParameter("xIdLog");                
                String xItem = request.getParameter("xItem");                
                String xIdOperacion = request.getParameter("xIdOperacion");    
                String xItemPadre = request.getParameter("xItemPadre");                    
                String xIdControl = request.getParameter("xIdControl");                                    
                
                //--------------------------------------------------------------
                int xIdControlTipoOTExterna = 1;
                
                //
                DctoOrdenBean dctoOrdenBean = new DctoOrdenBean();
                
                //
                FachadaDctoOrdenBean fachadaDctoOrdenBean 
                                                   = new FachadaDctoOrdenBean();

                //
                dctoOrdenBean.setIdLog(xIdLog);

                //
                fachadaDctoOrdenBean =
                        dctoOrdenBean.listaDctoOrdenIdLog();

                //
                int xIdOrden = fachadaDctoOrdenBean.getIdOrden(); 
                
                //
                GeneraPDFExterna generaPDFServlet = new GeneraPDFExterna();

                //
                generaPDFServlet.setIdLocal(xIdLocal);
                generaPDFServlet.setIdTipoOrden(xIdTipoOrden);
                generaPDFServlet.setIdOrden(xIdOrden);
                generaPDFServlet.setIdControl(xIdControl);
                generaPDFServlet.setIdOperacion(xIdOperacion);                
                generaPDFServlet.setIdOperacion(new Integer(xIdOperacion).intValue());
                generaPDFServlet.setItemPadre(new Integer(xItemPadre).intValue());
                generaPDFServlet.setIdControlTipo(xIdControlTipoOTExterna);
                generaPDFServlet.setNombreReporte("RepOTExternaEntrada");
                generaPDFServlet.setTituloReporte("ENTRADA");

                //
                generaPDFServlet.generaPdf(request, response);                  
                
            }    

            // Entrada
            if (accionContenedor.compareTo("Entrada") == 0) {

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
                fachadaDctoOrdenBean.setIdTipoTercero(xIdTipoTerceroProveedor);

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
                return "/jsp/vtaFrmLstOTExterna.jsp";


            }

            //
            if (accionContenedor.compareTo("Traer") == 0) {

                //
                //String xIdTipoOrden = request.getParameter("xIdTipoOrden");
                //String xIdLocal = request.getParameter("xIdLocal");
                //String xIdLog = request.getParameter("xIdLog");
                String xNumeroOrden = request.getParameter("xNumeroOrden");
                String xIdOperacion = request.getParameter("xIdOperacion");
                //String xItemPadre = request.getParameter("xItem");
                String xItemPadre = "1";
                
                //
                Validacion validacion = new Validacion();

                //
                validacion.reasignar("OPERACION", xIdOperacion);

                //
                validacion.validarCampoEntero();

                //
                if (!validacion.isValido()) {

                    // Aqui escribe el Bean de Validacion en el Request para manejar el error
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralError.jsp";

                }

                //
                validacion.reasignar("#O.T.", xNumeroOrden);

                //
                validacion.validarCampoEntero();

                //
                if (!validacion.isValido()) {

                    // Aqui escribe el Bean de Validacion en el Request para manejar el error
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralError.jsp";

                }

                //--------------------------------------------------------------
                FachadaPluFicha fachadaPluFicha = new FachadaPluFicha();

                //
                ColaboraOrdenTrabajo colaboraOrdenTrabajo
                                                   = new ColaboraOrdenTrabajo();

                //
                colaboraOrdenTrabajo.setNumeroOrden(xNumeroOrden);
                colaboraOrdenTrabajo.setIdOperacion(xIdOperacion);
                colaboraOrdenTrabajo.setItem(xItemPadre);

                //
                fachadaPluFicha = colaboraOrdenTrabajo.listaOrdenFCH();

                //
                int xIdLog = fachadaPluFicha.getIdLog();

                //
                if (xIdLog == 0) {

                    //
                    validacion.reasignar("#O.T.", xNumeroOrden);

                    //
                    validacion.setDescripcionError("NO EXISTE #O.T. " + xNumeroOrden);
                    
                    //
                    validacion.setSolucion("INGRESE OTRA #O.T. ");

                    // Aqui escribe el Bean de Validacion en el Request para manejar el error
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralError.jsp";

                }

                //--------------------------------------------------------------
//                AgendaLogVisitaBean agendaLogVisitaBean
//                                                    = new AgendaLogVisitaBean();

                //
                agendaLogVisitaBean.setIdLog(xIdLog);

                //
//                FachadaAgendaLogVisitaBean fachadaAgendaLogVisitaBean
//                                             = new FachadaAgendaLogVisitaBean();

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
//                String xIdTipoTerceroProveedor = "2";

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

                //---
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
                dctoOrdenProgresoBean.setIdLocal(xIdLocalUsuario);
                dctoOrdenProgresoBean.setIdTipoOrden(xIdTipoOrdenTransformacion);
                dctoOrdenProgresoBean.setIdOrden(
                        fachadaDctoOrdenBean.getIdOrden());
                dctoOrdenProgresoBean.setIdOperacion(xIdOperacion);
                dctoOrdenProgresoBean.setItemPadre(xItemPadre);

                //
                FachadaDctoOrdenProgreso fachadaDctoOrdenConcluida
                                               = new FachadaDctoOrdenProgreso();

                //
                fachadaDctoOrdenConcluida =
                        dctoOrdenProgresoBean.listaTotalFCH();

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaDctoOrdenConcluida",
                        fachadaDctoOrdenConcluida);                
            
                if (xIdOperacion.equals("555")) {

                    return "/jsp/vtaFrmIngOTExternaProNew.jsp";

                } else {
                    //
                    return "/jsp/vtaFrmIngOTExterna.jsp";
                }
            }
            
            //
            if (accionContenedor.compareTo("retiraSalida") == 0) {

                //
                String xIdLocal = request.getParameter("xIdLocal");
//                String xIdTipoOrden = request.getParameter("xIdTipoOrden");
                String xIdLog = request.getParameter("xIdLog");
                String xItem = request.getParameter("xItem");
                String xIdOperacion = request.getParameter("xIdOperacion");
                String xItemPadre = request.getParameter("xItemPadre");

                //---
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
                int xIdOrden = fachadaDctoOrdenBean.getIdOrden();
                xIdTipoTerceroProveedor = 1;
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
                FachadaDctoOrdenProgreso fachadaDctoOrdenProgreso
                                               = new FachadaDctoOrdenProgreso();

                //
                DctoOrdenProgresoBean dctoOrdenProgresoBean
                                                  = new DctoOrdenProgresoBean();

                //
                dctoOrdenProgresoBean.setIdLocal(xIdLocal);
                dctoOrdenProgresoBean.setIdTipoOrden(xIdTipoOrden);
                dctoOrdenProgresoBean.setIdOrden(xIdOrden);
                dctoOrdenProgresoBean.setIdOperacion(xIdOperacion);
                dctoOrdenProgresoBean.setItemPadre(xItemPadre);
                dctoOrdenProgresoBean.setItem(xItem);

                //
                fachadaDctoOrdenProgreso       =
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
                return "/jsp/vtaFrmRetOTExternaSalida.jsp";


            }            

            //
            if (accionContenedor.compareTo("retira") == 0) {

                //
                String xIdLocal = request.getParameter("xIdLocal");
//                String xIdTipoOrden = request.getParameter("xIdTipoOrden");
                String xIdLog = request.getParameter("xIdLog");
                String xItem = request.getParameter("xItem");
                String xIdOperacion = request.getParameter("xIdOperacion");
                String xItemPadre = request.getParameter("xItemPadre");
                String xIdOrdenCruce = request.getParameter("xIdOrdenCruce");                

                //---
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
                int xIdOrden = fachadaDctoOrdenBean.getIdOrden();
                 xIdTipoTerceroProveedor = 1;
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
                FachadaDctoOrdenProgreso fachadaDctoOrdenProgreso
                                               = new FachadaDctoOrdenProgreso();

                //
                DctoOrdenProgresoBean dctoOrdenProgresoBean
                                                  = new DctoOrdenProgresoBean();

                //
                dctoOrdenProgresoBean.setIdLocal(xIdLocal);
                dctoOrdenProgresoBean.setIdTipoOrden(xIdTipoOrden);
                dctoOrdenProgresoBean.setIdOrden(xIdOrden);
                dctoOrdenProgresoBean.setIdOperacion(xIdOperacion);
                dctoOrdenProgresoBean.setItemPadre(xItemPadre);
                dctoOrdenProgresoBean.setItem(xItem);

                //
                fachadaDctoOrdenProgreso       =
                                          dctoOrdenProgresoBean.listaItemFCH();

                //
                fachadaDctoOrdenProgreso.setItem(xItem);
                fachadaDctoOrdenProgreso.setIdOrdenCruce(xIdOrdenCruce);                

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
                return "/jsp/vtaFrmRetOTExterna.jsp";


            }
            
            //
            if (accionContenedor.compareTo("Confirmar Retiro Salida") == 0) {

                //
                String xIdLocal = request.getParameter("xIdLocal");
//                String xIdTipoOrden = request.getParameter("xIdTipoOrden");
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
//                AgendaLogVisitaBean agendaLogVisitaBean
//                                                    = new AgendaLogVisitaBean();

                //
                agendaLogVisitaBean.setIdLog(xIdLog);

                //
//                FachadaAgendaLogVisitaBean fachadaAgendaLogVisitaBean
//                                             = new FachadaAgendaLogVisitaBean();

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
                int xIdFicha = fachadaDctoOrdenBean.getIdFicha();
                String xIdCliente = fachadaDctoOrdenBean.getIdCliente();
                int xIdOrden = fachadaDctoOrdenBean.getIdOrden();
                int xNumeroOrden = fachadaDctoOrdenBean.getNumeroOrden();
                 xIdTipoTerceroProveedor = 2;

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
                FachadaDctoOrdenProgreso fachadaDctoOrdenProgreso
                                               = new FachadaDctoOrdenProgreso();

                //
                DctoOrdenProgresoBean dctoOrdenProgresoBean
                                                  = new DctoOrdenProgresoBean();

                dctoOrdenProgresoBean.setIdLocal(xIdLocal);
                dctoOrdenProgresoBean.setIdTipoOrden(xIdTipoOrden);
                dctoOrdenProgresoBean.setIdOrden(
                                      fachadaDctoOrdenDetalleBean.getIdOrden());
                dctoOrdenProgresoBean.setIdOperacion(xIdOperacion);
                dctoOrdenProgresoBean.setItem(xItem);

                //
                fachadaDctoOrdenProgreso       =
                                          dctoOrdenProgresoBean.listaFCH();

                //
                int xIdOrdenOrigen             =
                                          fachadaDctoOrdenProgreso.getIdOrden();
                int xIdLocalOrigen             =
                                          fachadaDctoOrdenProgreso.getIdLocal();
                int xIdTipoOrdenOrigen         =
                                      fachadaDctoOrdenProgreso.getIdTipoOrden();
                double xPesoTerminado          =
                                    fachadaDctoOrdenProgreso.getPesoTerminado();
                double xCantidadTerminada      =
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
                ColaboraOperacionPlu colaboraOperacionPlu =
                        new ColaboraOperacionPlu();

                //
                colaboraOperacionPlu.setIdFicha(xIdFicha);
                colaboraOperacionPlu.setIdOperacion(xIdOperacion);

                //
                double xTotalMP = colaboraOperacionPlu.totalMP();
                double xSignoRetiro = -1.0;

                //
                ProcesoIngresoProduccion proceso
                                               = new ProcesoIngresoProduccion();

                //
                proceso.retira(new Integer(xIdLocal).intValue(),
                        xIdTipoOrdenTransformacion,
                        xIdUsuario,
                        xIdFicha,
                        new Integer(xIdOperacion).intValue(),
                        new Double(xPesoTerminado).doubleValue() * xSignoRetiro ,
                        new Double(xCantidadTerminada).doubleValue() * xSignoRetiro,
                        xTotalMP,
                        xIdCliente,
                        xIdOrdenOrigen,
                        xIdLocalOrigen,
                        xIdTipoOrdenOrigen,
                        xNumeroOrden,
                        new Integer(xItemPadre).intValue(),
                        xObservacion);

                //
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
                FachadaDctoOrdenProgreso fachadaDctoOrdenConcluida
                                               = new FachadaDctoOrdenProgreso();

                //
                fachadaDctoOrdenConcluida =
                        dctoOrdenProgresoBean.listaTotalFCH();

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaDctoOrdenConcluida",
                        fachadaDctoOrdenConcluida);

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaPluFicha", fachadaPluFicha);

                //
                return "/jsp/vtaFrmIngOTExterna.jsp";
            }            

            //
            if (accionContenedor.compareTo("Confirmar Retiro") == 0) {

                //
                String xIdLocal = request.getParameter("xIdLocal");
//                String xIdTipoOrden = request.getParameter("xIdTipoOrden");
                String xIdLog = request.getParameter("xIdLog");
                String xItem = request.getParameter("xItem");
                String xIdOperacion = request.getParameter("xIdOperacion");
                String xItemPadre = request.getParameter("xItemPadre");
                String xObservacion = request.getParameter("xObservacion");
                String xIdOrdenCruce = request.getParameter("xIdOrdenCruce");                

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
//                AgendaLogVisitaBean agendaLogVisitaBean
//                                                    = new AgendaLogVisitaBean();

                //
                agendaLogVisitaBean.setIdLog(xIdLog);

                //
//                FachadaAgendaLogVisitaBean fachadaAgendaLogVisitaBean
//                                             = new FachadaAgendaLogVisitaBean();

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
                int xIdFicha = fachadaDctoOrdenBean.getIdFicha();
                String xIdCliente = fachadaDctoOrdenBean.getIdCliente();
                int xIdOrden = fachadaDctoOrdenBean.getIdOrden();
                int xNumeroOrden = fachadaDctoOrdenBean.getNumeroOrden();
//                xIdTipoTerceroProveedor = "2";

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
                FachadaDctoOrdenProgreso fachadaDctoOrdenProgreso
                                               = new FachadaDctoOrdenProgreso();

                //
                DctoOrdenProgresoBean dctoOrdenProgresoBean
                                                  = new DctoOrdenProgresoBean();

                dctoOrdenProgresoBean.setIdLocal(xIdLocal);
                dctoOrdenProgresoBean.setIdTipoOrden(xIdTipoOrden);
                dctoOrdenProgresoBean.setIdOrden(
                                      fachadaDctoOrdenDetalleBean.getIdOrden());
                dctoOrdenProgresoBean.setIdOperacion(xIdOperacion);
                dctoOrdenProgresoBean.setItem(xItem);

                //
                fachadaDctoOrdenProgreso       =
                                          dctoOrdenProgresoBean.listaFCH();

                //
                int xIdLocalOrigen             =
                                          fachadaDctoOrdenProgreso.getIdLocal();
                int xIdTipoOrdenOrigen         =
                                      fachadaDctoOrdenProgreso.getIdTipoOrden();
                int xIdOrdenOrigen             =
                                          fachadaDctoOrdenProgreso.getIdOrden();                
                double xPesoTerminado          =
                                    fachadaDctoOrdenProgreso.getPesoTerminado();
                double xCantidadTerminada      =
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
                ColaboraOperacionPlu colaboraOperacionPlu =
                        new ColaboraOperacionPlu();

                //
                colaboraOperacionPlu.setIdFicha(xIdFicha);
                colaboraOperacionPlu.setIdOperacion(xIdOperacion);

                //
                double xTotalMP = colaboraOperacionPlu.totalMP();

                //
                ProcesoIngresoProduccion proceso
                                               = new ProcesoIngresoProduccion();

                //---        
                proceso.retiraOTCruce(xIdUsuario,
                        xIdCliente,
                        xIdLocalOrigen,
                        xIdTipoOrdenTransformacion,
                        new Integer(xIdOrdenCruce).intValue(),
                        xNumeroOrden,
                        xObservacion);
                        
                //
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
                FachadaDctoOrdenProgreso fachadaDctoOrdenConcluida
                                               = new FachadaDctoOrdenProgreso();

                //
                fachadaDctoOrdenConcluida =
                        dctoOrdenProgresoBean.listaTotalFCH();

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaDctoOrdenConcluida",
                        fachadaDctoOrdenConcluida);

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaPluFicha", fachadaPluFicha);

                //
                return "/jsp/vtaFrmIngOTExterna.jsp";
            }

            //
            if (accionContenedor.compareTo("Confirmar Salida") == 0) {

                //
                String xIdLocal = request.getParameter("xIdLocal");
//                String xIdTipoOrden = request.getParameter("xIdTipoOrden");
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
                if ( (xIdOperacion.compareTo("2") == 0) ||
                     (xIdOperacion.compareTo("3") == 0) || 
                     (xIdOperacion.compareTo("4") == 0) ) {
                    
                    //
                    xCantidadTerminada = request.getParameter("xPesoTerminado");
                    
                }                   

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
                valida.reasignar("PESO (KG) FINALIZADO", xPesoTerminado);

                //
                valida.validarCampoDoublePositivo();

                //isValido
                if (!valida.isValido()) {

                    //
                    request.setAttribute("validacion", valida);
                    return "/jsp/gralError.jsp";
                }

                //
                valida.reasignar("PESO (KG) TARA", xPesoTara);

                //
                valida.validarCampoDoublePositivo();

                //isValido
                if (!valida.isValido()) {

                    //
                    request.setAttribute("validacion", valida);
                    return "/jsp/gralError.jsp";
                }

                //
                valida.reasignar("CANTIDAD FINALIZADA", xCantidadTerminada);

                //
                valida.validarCampoDoublePositivo();

                //isValido
                if (!valida.isValido()) {

                    //
                    request.setAttribute("validacion", valida);
                    return "/jsp/gralError.jsp";
                }

                //
                valida.reasignar("PESO (KG) RETAL", xPesoPerdido);

                //
                valida.validarCampoDoublePositivo();

                //isValido
                if (!valida.isValido()) {

                    //
                    request.setAttribute("validacion", valida);
                    return "/jsp/gralError.jsp";
                }

                //
                if (!((new Double(xPesoTerminado).doubleValue() != 0) ||
                   (new Double(xCantidadTerminada).doubleValue() != 0))) {

                    //
                    valida.reasignar("ERROR, FALTA INGRESAR PESO (KG) FINALIZADO / " +
                            "                           CANTIDAD FINALIZADA","");

                    //
                    request.setAttribute("validacion", valida);
                    return "/jsp/gralError.jsp";


                }

                //------------------------- traer O.T. -------------------------
//                AgendaLogVisitaBean agendaLogVisitaBean
//                                                    = new AgendaLogVisitaBean();

                //
                agendaLogVisitaBean.setIdLog(xIdLog);

                //
//                FachadaAgendaLogVisitaBean fachadaAgendaLogVisitaBean
//                                             = new FachadaAgendaLogVisitaBean();

                //---
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
                int xIdOrdenOrigen = fachadaDctoOrdenBean.getIdOrden();
                int xIdLocalOrigen = fachadaDctoOrdenBean.getIdLocal();
                int xIdTipoOrdenOrigen = fachadaDctoOrdenBean.getIdTipoOrden();
                int xNumeroOrden = fachadaDctoOrdenBean.getNumeroOrden();

                //
                int xIdFicha = fachadaDctoOrdenBean.getIdFicha();
                String xIdCliente = fachadaDctoOrdenBean.getIdCliente();
//                String xIdTipoTerceroProveedor = "2";

                //
                fachadaDctoOrdenBean.setIdTipoTercero(xIdTipoTerceroProveedor);
                fachadaDctoOrdenBean.setIdOperacion(xIdOperacion);
                fachadaDctoOrdenBean.setItemPadre(xItemPadre);

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaDctoOrdenBean", fachadaDctoOrdenBean);

                //------------------------- validar adicion --------------------
                DctoOrdenProgresoBean dctoOrdenProgresoBean = new
                                                        DctoOrdenProgresoBean();

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

                //
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

                //----------------------- ingresar  progreso -------------------
                FachadaDctoOrdenProgreso fachadaDctoOrdenProgreso =
                        new FachadaDctoOrdenProgreso();

                //
                dctoOrdenProgresoBean.setIdLocal(xIdLocal);
                dctoOrdenProgresoBean.setIdTipoOrden(xIdTipoOrden);
                dctoOrdenProgresoBean.setIdOrden(
                        fachadaDctoOrdenBean.getIdOrden());
                dctoOrdenProgresoBean.setItemPadre(xItemPadre);
                dctoOrdenProgresoBean.setIdOperacion(xIdOperacion);

                //
                fachadaDctoOrdenProgreso =
                        dctoOrdenProgresoBean.listaUltimoIngresoFCH();

                //--------------------------------------------------------------
                int xMaximoItem = dctoOrdenProgresoBean.maximoItem() + 1;

                //---
                FachadaJobOperacionCosto fachadaJobOperacionCosto
                                               = new FachadaJobOperacionCosto();

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
                int xIdControlTipo = 2;
                int xIdOrdenCruce = 0;                                           
                String xIdDctoNitCC = "";;                 

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
                dctoOrdenProgresoBean.setIdControlTipo(xIdControlTipo);
                dctoOrdenProgresoBean.setIdOrdenCruce(xIdOrdenCruce);                                           
                dctoOrdenProgresoBean.setIdDctoNitCC(xIdDctoNitCC);                

                //---
                dctoOrdenProgresoBean.ingresa();

                //--------------------------------------------------------------
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
                int xIdBodegaSiguiente = fachadaJobOperacion.getIdOperacion();

                //
                if (xIdBodegaSiguiente == xIdBodegaTerminados) {

                    //----------------------------------------------------------
                    int xMaximoItemTerminados =
                                         dctoOrdenProgresoBean.maximoItem() + 1;

                    //---
                    xVrCostoBaseMAT = 0.0;
                    xVrCostoBaseMOD = 0.0;
                    xVrCostoBaseCIF = 0.0;

                    //---
                    xIdTipoCausaProduccion = 0;
                    xIdControlTipo = 2;

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
                    dctoOrdenProgresoBean.setIdControlTipo(xIdControlTipo);
                    dctoOrdenProgresoBean.setIdOrdenCruce(xIdOrdenCruce);                                           
                    dctoOrdenProgresoBean.setIdDctoNitCC(xIdDctoNitCC.trim());                    

                    //---
                    dctoOrdenProgresoBean.ingresa();

                }

                //----------------------- ingresar  documento ------------------
                ColaboraOperacionPlu colaboraOperacionPlu =
                        new ColaboraOperacionPlu();

                //
                colaboraOperacionPlu.setIdFicha(xIdFicha);
                colaboraOperacionPlu.setIdOperacion(xIdOperacion);

                //
                double xTotalMP = colaboraOperacionPlu.totalMP();

                //
                ProcesoSalidaExterno proceso = new ProcesoSalidaExterno();

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
                        new Integer(xItemPadre).intValue());

                //--------------------------------------------------------------
                DctoOrdenDetalleBean dctoOrdenDetalleBean = new DctoOrdenDetalleBean();

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
                fachadaPluFicha.setCantidad(xCantidad);
                fachadaPluFicha.setIdOperacion(xIdOperacion);
                fachadaPluFicha.setIdFicha(xIdFicha);

                //
                FachadaDctoOrdenProgreso fachadaDctoOrdenConcluida
                                               = new FachadaDctoOrdenProgreso();

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

                //--------------------------------------------------------------
                int xIdControlTipoOTExterna = 2;
                String xObservacion = "";

                //
                dctoOrdenBean.setIdLog(xIdLog);

                //
                fachadaDctoOrdenBean =
                        dctoOrdenBean.listaDctoOrdenIdLog();

                //
                int xIdOrden = fachadaDctoOrdenBean.getIdOrden();

                //
                dctoOrdenProgresoBean.setIdLocal(xIdLocal);
                dctoOrdenProgresoBean.setIdTipoOrden(xIdTipoOrdenCotizacion);
                dctoOrdenProgresoBean.setIdControlTipo(xIdControlTipoOTExterna);

                //
                int xMaximoidControlExterno =
                        dctoOrdenProgresoBean.maximoIdControExterno() + 1;

                //
                dctoOrdenProgresoBean.setIdControl(xMaximoidControlExterno);
                dctoOrdenProgresoBean.setIdOrden(xIdOrden);
                dctoOrdenProgresoBean.setItemPadre(xItemPadre);
                dctoOrdenProgresoBean.setIdOperacion(xIdOperacion);
                dctoOrdenProgresoBean.setObservacion(xObservacion);
                dctoOrdenProgresoBean.setIdUsuario(xIdUsuario);

                //
                dctoOrdenProgresoBean.actualizaOrdenExterna();

                //
                GeneraPDFExterna generaPDFServlet = new GeneraPDFExterna();

                //
                generaPDFServlet.setIdLocal(xIdLocal);
                generaPDFServlet.setIdTipoOrden(xIdTipoOrdenCotizacion);
                generaPDFServlet.setIdOrden(xIdOrden);
                generaPDFServlet.setIdControl(xMaximoidControlExterno);
                generaPDFServlet.setIdOperacion(new Integer(xIdOperacion).intValue());
                generaPDFServlet.setItemPadre(new Integer(xItemPadre).intValue());
                generaPDFServlet.setIdControlTipo(xIdControlTipoOTExterna);
                generaPDFServlet.setNombreReporte("RepOTExternaSalida");
                generaPDFServlet.setTituloReporte("SALIDA");

                //
                generaPDFServlet.generaPdf(request, response);

                //
                return "/jsp/vtaFrmIngOTExterna.jsp";
            }
            
            //
            if (accionContenedor.compareTo("Guarda Salida") == 0) {

                //
                String xIdLocal = request.getParameter("xIdLocal");
//                String xIdTipoOrden = request.getParameter("xIdTipoOrden");
                String xIdLog = request.getParameter("xIdLog");
                String xCantidadTerminada ="0";
                String xPesoTerminado = request.getParameter("xPesoTerminado");
                String xPesoPerdido = request.getParameter("xPesoPerdido");
                String xIdOperario = request.getParameter("xIdTercero");
                String xEstado = "1";
                String xIdOperacion = request.getParameter("xIdOperacion");
                String xItemPadre = request.getParameter("xItemPadre");
                String xPesoTara = request.getParameter("xPesoTara");
                String xIdCausa = "0";
                String xBodegaDestino = "555";
                
                //--------------------------------------------------------------
                if ( (xIdOperacion.compareTo("2") == 0) ||
                     (xIdOperacion.compareTo("3") == 0) || 
                     (xIdOperacion.compareTo("4") == 0) ) {
                    
                    //
                    xCantidadTerminada = request.getParameter("xPesoTerminado");
                    
                }                   

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
                valida.reasignar("PESO (KG) FINALIZADO", xPesoTerminado);

                //
                valida.validarCampoDoublePositivo();

                //isValido
                if (!valida.isValido()) {

                    //
                    request.setAttribute("validacion", valida);
                    return "/jsp/gralError.jsp";
                }

                //
                valida.reasignar("PESO (KG) TARA", xPesoTara);

                //
                valida.validarCampoDoublePositivo();

                //isValido
                if (!valida.isValido()) {

                    //
                    request.setAttribute("validacion", valida);
                    return "/jsp/gralError.jsp";
                }

//                //
//                valida.reasignar("CANTIDAD FINALIZADA", xCantidadTerminada);
//
//                //
//                valida.validarCampoDoublePositivo();
//
//                //isValido
//                if (!valida.isValido()) {
//
//                    //
//                    request.setAttribute("validacion", valida);
//                    return "/jsp/gralError.jsp";
//                }

                //
                valida.reasignar("PESO (KG) RETAL", xPesoPerdido);

                //
                valida.validarCampoDoublePositivo();

                //isValido
                if (!valida.isValido()) {

                    //
                    request.setAttribute("validacion", valida);
                    return "/jsp/gralError.jsp";
                }

                //
                if (!((new Double(xPesoTerminado).doubleValue() != 0) ||
                   (new Double(xCantidadTerminada).doubleValue() != 0))) {

                    //
                    valida.reasignar("ERROR, FALTA INGRESAR PESO (KG) FINALIZADO / " +
                            "                           CANTIDAD FINALIZADA","");

                    //
                    request.setAttribute("validacion", valida);
                    return "/jsp/gralError.jsp";


                }

                //------------------------- traer O.T. -------------------------
//                AgendaLogVisitaBean agendaLogVisitaBean
//                                                    = new AgendaLogVisitaBean();

                //
                agendaLogVisitaBean.setIdLog(xIdLog);

//                //
//                FachadaAgendaLogVisitaBean fachadaAgendaLogVisitaBean
//                                             = new FachadaAgendaLogVisitaBean();

                //---
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
                int xIdOrdenOrigen = fachadaDctoOrdenBean.getIdOrden();
                int xIdLocalOrigen = fachadaDctoOrdenBean.getIdLocal();
                int xIdTipoOrdenOrigen = fachadaDctoOrdenBean.getIdTipoOrden();
                int xNumeroOrden = fachadaDctoOrdenBean.getNumeroOrden();

                //
                int xIdFicha = fachadaDctoOrdenBean.getIdFicha();
                String xIdCliente = fachadaDctoOrdenBean.getIdCliente();
//                String xIdTipoTerceroProveedor = "2";

                //
                fachadaDctoOrdenBean.setIdTipoTercero(xIdTipoTerceroProveedor);
                fachadaDctoOrdenBean.setIdOperacion(xIdOperacion);
                fachadaDctoOrdenBean.setItemPadre(xItemPadre);

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaDctoOrdenBean", fachadaDctoOrdenBean);

                //------------------------- validar adicion --------------------
                DctoOrdenProgresoBean dctoOrdenProgresoBean = new
                                                        DctoOrdenProgresoBean();

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

                //
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

                //----------------------- ingresar  progreso -------------------
                FachadaDctoOrdenProgreso fachadaDctoOrdenProgreso =
                        new FachadaDctoOrdenProgreso();

                //
                dctoOrdenProgresoBean.setIdLocal(xIdLocal);
                dctoOrdenProgresoBean.setIdTipoOrden(xIdTipoOrden);
                dctoOrdenProgresoBean.setIdOrden(
                        fachadaDctoOrdenBean.getIdOrden());
                dctoOrdenProgresoBean.setItemPadre(xItemPadre);
                dctoOrdenProgresoBean.setIdOperacion(xIdOperacion);

                //
                fachadaDctoOrdenProgreso =
                        dctoOrdenProgresoBean.listaUltimoIngresoFCH();

                //--------------------------------------------------------------
                int xMaximoItem = dctoOrdenProgresoBean.maximoItem() + 1;

                //---
                FachadaJobOperacionCosto fachadaJobOperacionCosto
                                               = new FachadaJobOperacionCosto();

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
                int xIdControlTipo = 2;
                int xIdOrdenCruce = 0;                                           
                String xIdDctoNitCC = "";;                 

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
                dctoOrdenProgresoBean.setIdControlTipo(xIdControlTipo);
                dctoOrdenProgresoBean.setIdOrdenCruce(xIdOrdenCruce);                                           
                dctoOrdenProgresoBean.setIdDctoNitCC(xIdDctoNitCC);                

                //---
                dctoOrdenProgresoBean.ingresa();

                //--------------------------------------------------------------
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
                int xIdBodegaSiguiente = 555;

                //
                if (xIdBodegaSiguiente == xIdBodegaTerminados) {

                    //----------------------------------------------------------
                    int xMaximoItemTerminados =
                                         dctoOrdenProgresoBean.maximoItem() + 1;

                    //---
                    xVrCostoBaseMAT = 0.0;
                    xVrCostoBaseMOD = 0.0;
                    xVrCostoBaseCIF = 0.0;

                    //---
                    xIdTipoCausaProduccion = 0;
                    xIdControlTipo = 2;

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
                    dctoOrdenProgresoBean.setIdControlTipo(xIdControlTipo);
                    dctoOrdenProgresoBean.setIdOrdenCruce(xIdOrdenCruce);                                           
                    dctoOrdenProgresoBean.setIdDctoNitCC(xIdDctoNitCC.trim());                    

                    //---
                    dctoOrdenProgresoBean.ingresa();

                }

                //----------------------- ingresar  documento ------------------
                ColaboraOperacionPlu colaboraOperacionPlu =
                        new ColaboraOperacionPlu();

                //
                colaboraOperacionPlu.setIdFicha(xIdFicha);
                colaboraOperacionPlu.setIdOperacion(xIdOperacion);

                //
                double xTotalMP = colaboraOperacionPlu.totalMP();

                //
                ProcesoSalidaExterno proceso = new ProcesoSalidaExterno();

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
                        new Integer(xItemPadre).intValue());

                //--------------------------------------------------------------
                DctoOrdenDetalleBean dctoOrdenDetalleBean = new DctoOrdenDetalleBean();

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
                fachadaPluFicha.setCantidad(xCantidad);
                fachadaPluFicha.setIdOperacion(xIdOperacion);
                fachadaPluFicha.setIdFicha(xIdFicha);

                //
                FachadaDctoOrdenProgreso fachadaDctoOrdenConcluida
                                               = new FachadaDctoOrdenProgreso();

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

                //--------------------------------------------------------------
                int xIdControlTipoOTExterna = 2;
                String xObservacion = "";

                //
                dctoOrdenBean.setIdLog(xIdLog);

                //
                fachadaDctoOrdenBean =
                        dctoOrdenBean.listaDctoOrdenIdLog();

                //
                int xIdOrden = fachadaDctoOrdenBean.getIdOrden();

                //
                dctoOrdenProgresoBean.setIdLocal(xIdLocal);
                dctoOrdenProgresoBean.setIdTipoOrden(xIdTipoOrdenCotizacion);
                dctoOrdenProgresoBean.setIdControlTipo(xIdControlTipoOTExterna);

                //
                int xMaximoidControlExterno =
                        dctoOrdenProgresoBean.maximoIdControExterno() + 1;

                //
                dctoOrdenProgresoBean.setIdControl(xMaximoidControlExterno);
                dctoOrdenProgresoBean.setIdOrden(xIdOrden);
                dctoOrdenProgresoBean.setItemPadre(xItemPadre);
                dctoOrdenProgresoBean.setIdOperacion(xIdOperacion);
                dctoOrdenProgresoBean.setObservacion(xObservacion);
                dctoOrdenProgresoBean.setIdUsuario(xIdUsuario);

                //
                dctoOrdenProgresoBean.actualizaOrdenExterna();

                //
                GeneraPDFExterna generaPDFServlet = new GeneraPDFExterna();

                //
                generaPDFServlet.setIdLocal(xIdLocal);
                generaPDFServlet.setIdTipoOrden(xIdTipoOrdenCotizacion);
                generaPDFServlet.setIdOrden(xIdOrden);
                generaPDFServlet.setIdControl(xMaximoidControlExterno);
                generaPDFServlet.setIdOperacion(new Integer(xIdOperacion).intValue());
                generaPDFServlet.setItemPadre(new Integer(xItemPadre).intValue());
                generaPDFServlet.setIdControlTipo(xIdControlTipoOTExterna);
                generaPDFServlet.setNombreReporte("RepOTExternaSalida");
                generaPDFServlet.setTituloReporte("SALIDA");

                //
                generaPDFServlet.generaPdf(request, response);

                //
                return "/jsp/vtaFrmIngOTExterna.jsp";
            }

            //---
            if (accionContenedor.compareTo("Confirmar Entrada") == 0) {

                //
                String xIdLocal = request.getParameter("xIdLocal");
//                String xIdTipoOrden = request.getParameter("xIdTipoOrden");
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
                String xIdDctoNitCC = request.getParameter("xIdDctoNitCC");                
                
                //--------------------------------------------------------------
                if ( (xIdOperacion.compareTo("2") == 0) ||
                     (xIdOperacion.compareTo("3") == 0) || 
                     (xIdOperacion.compareTo("4") == 0) ) {
                    
                    //
                    xCantidadTerminada = request.getParameter("xPesoTerminado");
                    
                }                

                //--------------------------------------------------------------
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
                valida.reasignar("PESO (KG) FINALIZADO", xPesoTerminado);

                //
                valida.validarCampoDoublePositivo();

                //isValido
                if (!valida.isValido()) {

                    //
                    request.setAttribute("validacion", valida);
                    return "/jsp/gralError.jsp";
                }

                //
                valida.reasignar("PESO (KG) TARA", xPesoTara);

                //
                valida.validarCampoDoublePositivo();

                //isValido
                if (!valida.isValido()) {

                    //
                    request.setAttribute("validacion", valida);
                    return "/jsp/gralError.jsp";
                }

                //
                valida.reasignar("CANTIDAD FINALIZADA", xCantidadTerminada);

                //
                valida.validarCampoDoublePositivo();

                //isValido
                if (!valida.isValido()) {

                    //
                    request.setAttribute("validacion", valida);
                    return "/jsp/gralError.jsp";
                }
                
                //
                valida.reasignar("PESO (KG) RETAL", xPesoPerdido);

                //
                valida.validarCampoDoublePositivo();

                //isValido
                if (!valida.isValido()) {

                    //
                    request.setAttribute("validacion", valida);
                    return "/jsp/gralError.jsp";
                }
                
                //
                valida.reasignar("#DCTO TERCERO", xIdDctoNitCC);

                //
                valida.validarCampoString();

                //isValido
                if (!valida.isValido()) {

                    //
                    request.setAttribute("validacion", valida);
                    return "/jsp/gralError.jsp";
                }                

                //
                if (!((new Double(xPesoTerminado).doubleValue() != 0) ||
                   (new Double(xCantidadTerminada).doubleValue() != 0))) {

                    //
                    valida.reasignar("ERROR, FALTA INGRESAR PESO (KG) FINALIZADO / " +
                            "                           CANTIDAD FINALIZADA","");

                    //
                    request.setAttribute("validacion", valida);
                    return "/jsp/gralError.jsp";


                }

                //------------------------- traer O.T. -------------------------
//                AgendaLogVisitaBean agendaLogVisitaBean
//                                                    = new AgendaLogVisitaBean();

                //
                agendaLogVisitaBean.setIdLog(xIdLog);

                //
//                FachadaAgendaLogVisitaBean fachadaAgendaLogVisitaBean
//                                             = new FachadaAgendaLogVisitaBean();

                //---
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
                int xIdOrdenOrigen = fachadaDctoOrdenBean.getIdOrden();
                int xIdLocalOrigen = fachadaDctoOrdenBean.getIdLocal();
                int xIdTipoOrdenOrigen = fachadaDctoOrdenBean.getIdTipoOrden();
                int xNumeroOrden = fachadaDctoOrdenBean.getNumeroOrden();

                //
                int xIdFicha = fachadaDctoOrdenBean.getIdFicha();
                String xIdCliente = fachadaDctoOrdenBean.getIdCliente();
//                String xIdTipoTerceroProveedor = "2";

                //
                fachadaDctoOrdenBean.setIdTipoTercero(xIdTipoTerceroProveedor);
                fachadaDctoOrdenBean.setIdOperacion(xIdOperacion);
                fachadaDctoOrdenBean.setItemPadre(xItemPadre);

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaDctoOrdenBean", fachadaDctoOrdenBean);

                //------------------------- validar adicion --------------------
                DctoOrdenProgresoBean dctoOrdenProgresoBean = new
                                                        DctoOrdenProgresoBean();

                //
                dctoOrdenProgresoBean.setIdLocal(xIdLocal);
                dctoOrdenProgresoBean.setIdTipoOrden(xIdTipoOrden);
                dctoOrdenProgresoBean.setIdOrden(xIdOrdenOrigen);
                dctoOrdenProgresoBean.setIdOperacion(xIdOperacion);
                dctoOrdenProgresoBean.setItemPadre(xItemPadre);

                //
                double xPorcentajeAdicion = 1.05;

                //
                boolean xOkExcedePeso =
                               dctoOrdenProgresoBean.validaPesoAdicionTerminado(
                                                                  xPorcentajeAdicion,
                                      new Double(xPesoTerminado).doubleValue());

                //
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
                ProcesoIngresoES proceso = new ProcesoIngresoES();

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
                FachadaJobOperacionCosto fachadaJobOperacionCosto 
                                               = new FachadaJobOperacionCosto();

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
                int xIdControlTipoOTExterna = 1;

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
                dctoOrdenProgresoBean.setIdDctoNitCC(xIdDctoNitCC);   
                dctoOrdenProgresoBean.setIdControlTipo(xIdControlTipoOTExterna);                   
        
                //---
                dctoOrdenProgresoBean.ingresa();

                //--------------------------------------------------------------
                DctoOrdenDetalleBean dctoOrdenDetalleBean = new DctoOrdenDetalleBean();

                //
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
                    
                    //---
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
                    dctoOrdenProgresoBean.setObservacion(xObservacion);
                    dctoOrdenProgresoBean.setIdOperacion(xIdBodegaTerminados);
                    dctoOrdenProgresoBean.setIdOrdenCruce(xIdOrdenCruce);
                    dctoOrdenProgresoBean.setIdDctoNitCC(xIdDctoNitCC);   
                    dctoOrdenProgresoBean.setIdControlTipo(xIdControlTipoOTExterna);

                    //---
                    dctoOrdenProgresoBean.ingresa();

                }

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
                fachadaPluFicha.setCantidad(xCantidad);
                fachadaPluFicha.setIdOperacion(xIdOperacion);
                fachadaPluFicha.setIdFicha(xIdFicha);

                //
                FachadaDctoOrdenProgreso fachadaDctoOrdenConcluida
                                               = new FachadaDctoOrdenProgreso();

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
                
                //--------------------------------------------------------------
                xIdControlTipoOTExterna = 1;

                //
                dctoOrdenBean.setIdLog(xIdLog);

                //
                fachadaDctoOrdenBean =
                        dctoOrdenBean.listaDctoOrdenIdLog();

                //
                int xIdOrden = fachadaDctoOrdenBean.getIdOrden();

                //
                dctoOrdenProgresoBean.setIdLocal(xIdLocal);
                dctoOrdenProgresoBean.setIdTipoOrden(xIdTipoOrdenCotizacion);
                dctoOrdenProgresoBean.setIdControlTipo(xIdControlTipoOTExterna);

                //
                int xMaximoidControlExterno =
                        dctoOrdenProgresoBean.maximoIdControExterno() + 1;

                //
                dctoOrdenProgresoBean.setIdControl(xMaximoidControlExterno);
                dctoOrdenProgresoBean.setIdOrden(xIdOrden);
                dctoOrdenProgresoBean.setItemPadre(xItemPadre);
                dctoOrdenProgresoBean.setIdOperacion(xIdOperacion);
                dctoOrdenProgresoBean.setObservacion(xObservacion);
                dctoOrdenProgresoBean.setIdUsuario(xIdUsuario);

                //
                dctoOrdenProgresoBean.actualizaOrdenExterna();

                //--------------------------------------------------------------
                GeneraPDFExterna generaPDFServlet = new GeneraPDFExterna();

                //
                generaPDFServlet.setIdLocal(xIdLocal);
                generaPDFServlet.setIdTipoOrden(xIdTipoOrdenCotizacion);
                generaPDFServlet.setIdOrden(xIdOrden);
                generaPDFServlet.setIdControl(xMaximoidControlExterno);
                generaPDFServlet.setIdOperacion(new Integer(xIdOperacion).intValue());
                generaPDFServlet.setItemPadre(new Integer(xItemPadre).intValue());
                generaPDFServlet.setIdControlTipo(xIdControlTipoOTExterna);
                generaPDFServlet.setNombreReporte("RepOTExternaEntrada");
                generaPDFServlet.setTituloReporte("ENTRADA");

                //
                generaPDFServlet.generaPdf(request, response);                

                //
                return "/jsp/vtaFrmIngOTExterna.jsp";
            }
            
            //---
            if (accionContenedor.compareTo("Elabora Entrada") == 0) {

                //
                String xIdLocal = request.getParameter("xIdLocal");
//                String xIdTipoOrden = request.getParameter("xIdTipoOrden");
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
                String xIdDctoNitCC = request.getParameter("xIdDctoNitCC");                
                String xNumeroOrdenStr =  request.getParameter("xIdOT");
                             

                //--------------------------------------------------------------
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
                valida.reasignar("PESO (KG) FINALIZADO", xPesoTerminado);

                //
                valida.validarCampoDoublePositivo();

                //isValido
                if (!valida.isValido()) {

                    //
                    request.setAttribute("validacion", valida);
                    return "/jsp/gralError.jsp";
                }

                //
                valida.reasignar("PESO (KG) TARA", xPesoTara);

                //
                valida.validarCampoDoublePositivo();

                //isValido
                if (!valida.isValido()) {

                    //
                    request.setAttribute("validacion", valida);
                    return "/jsp/gralError.jsp";
                }

                //
                valida.reasignar("CANTIDAD FINALIZADA", xCantidadTerminada);

                //
                valida.validarCampoDoublePositivo();

                //isValido
                if (!valida.isValido()) {

                    //
                    request.setAttribute("validacion", valida);
                    return "/jsp/gralError.jsp";
                }
                
                //
                valida.reasignar("PESO (KG) RETAL", xPesoPerdido);

                //
                valida.validarCampoDoublePositivo();

                //isValido
                if (!valida.isValido()) {

                    //
                    request.setAttribute("validacion", valida);
                    return "/jsp/gralError.jsp";
                }
                
                //
                valida.reasignar("#DCTO TERCERO", xIdDctoNitCC);

                //
                valida.validarCampoString();

                //isValido
                if (!valida.isValido()) {

                    //
                    request.setAttribute("validacion", valida);
                    return "/jsp/gralError.jsp";
                } 
                
                 DctoOrdenBean dctoOrdenBean = new DctoOrdenBean();

                //
                FachadaDctoOrdenBean fachadaDctoOrdenBean 
                                                   = new FachadaDctoOrdenBean();

                //
                dctoOrdenBean.setIdLocal(xIdLocalUsuario);
                dctoOrdenBean.setIdTipoOrden(xIdTipoOrdenPedidoProceso);
                dctoOrdenBean.setNumeroOrden(xNumeroOrdenStr);

                //
                fachadaDctoOrdenBean = dctoOrdenBean.listaDctoNumeroOrden();
                //
//                FachadaDctoOrdenBean fachadaDctoOrdenBean 
//                                                   = new FachadaDctoOrdenBean();
                
                fachadaDctoOrdenBean.setIdLocal(xIdLocalUsuario);
                fachadaDctoOrdenBean.setIdTipoOrden(59);
                fachadaDctoOrdenBean.setIdUsuario(xIdUsuario);
                fachadaDctoOrdenBean.setIdCliente(xIdOperario);
                fachadaDctoOrdenBean.setFechaOrden(strFechaVisita);
                fachadaDctoOrdenBean.setIdLog(xIdLog);
                fachadaDctoOrdenBean.setIdBodegaOrigen(555);
                fachadaDctoOrdenBean.setIdBodegaDestino(888);
                fachadaDctoOrdenBean.setNumeroOrden(xNumeroOrdenStr);
                fachadaDctoOrdenBean.setIdOperario(xIdOperario);    
                fachadaDctoOrdenBean.setIdTipoTercero(2);                    
                
                // Aqui escribe el Bean de Validacion en el Request para manejar el error
                request.setAttribute("fachadaDctoOrdenBean", fachadaDctoOrdenBean);
            
                //
                return "/jsp/vtaFrmIngInventarioMovimientoES.jsp";
            }
            
            // Productos
            if (accionContenedor.compareTo("+Productos") == 0) {

                //
                String idLinea = request.getParameter("idLinea");
                String xIdTercero = request.getParameter("xIdTercero");
                String xFechaCorte = request.getParameter("xFechaCorte");
                String xIdBodegaOrigen =
                        request.getParameter("xIdBodegaOrigen");
                String xIdBodegaDestino =
                        request.getParameter("xIdBodegaDestino");
                String xNumeroOrden =
                        request.getParameter("xNumeroOrden");
                String xIdOperario =
                        request.getParameter("xIdOperario");
                
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
//                dctoOrdenBean.setIdLog(fachadaAgendaLogVisitaBean.getIdLog());
//                dctoOrdenBean.setIdTipoOrden(xIdTipoOrdenProceso);

                //
                FachadaDctoOrdenBean fachadaDctoOrdenBean 
                                                   = new FachadaDctoOrdenBean();
                
                //
                fachadaDctoOrdenBean =
                        dctoOrdenBean.listaDctoOrdenIdLogIdTipoOrden();

                //
                fachadaDctoOrdenBean.setIdListaPrecio(1);
                fachadaDctoOrdenBean.setIdCliente(xIdTercero);
                fachadaDctoOrdenBean.setFechaOrden(xFechaCorte);
                fachadaDctoOrdenBean.setIdBodegaOrigen(xIdBodegaOrigen);
                fachadaDctoOrdenBean.setIdBodegaDestino(xIdBodegaDestino);
                fachadaDctoOrdenBean.setIdLocal(xIdLocalUsuario);
                fachadaDctoOrdenBean.setNumeroOrden(xNumeroOrden);
                fachadaDctoOrdenBean.setIdTipoOrden(59);
                fachadaDctoOrdenBean.setIdOperario(xIdOperario);                

                //
                request.setAttribute("fachadaDctoOrdenBean",
                        fachadaDctoOrdenBean);

                //
                return "/jsp/vtaFrmLstInventarioMovimientoES.jsp";

            }
            
                // Confirmar
            if (accionContenedor.compareTo("Confirmar") == 0) {

                //
                String xIdTercero = request.getParameter("xIdTercero");
                String xFechaCorte = request.getParameter("xFechaCorte");
                String xIdBodegaOrigen =
                        request.getParameter("xIdBodegaOrigen");
                String xIdBodegaDestino =
                        request.getParameter("xIdBodegaDestino");
                String xNumeroOrden =
                        request.getParameter("xNumeroOrden");
                String xIdOperario = request.getParameter("xIdOperario");                

                //---
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

                    
                    
                    //
                    ProcesoGuardaInventario proceso = new ProcesoGuardaInventario();

                    //
                    int maximoItem = proceso.guarda(xIdLogActual,
                            strIdReferencia,
                            xCantidad,
                            xVrVentaUnitario,
                            xItemPadre,
                            (xIdTipoOrdenProceso - 50) ,
                            xIdUsuario,
                            new Integer(xIdLocalUsuario).intValue(),
                            xIdTercero,
                            new Integer(xIdBodegaOrigen).intValue());

                }

                //
                DctoOrdenBean dctoOrdenBean = new DctoOrdenBean();

                //
                dctoOrdenBean.setIdLog(fachadaAgendaLogVisitaBean.getIdLog());
                dctoOrdenBean.setIdTipoOrden(xIdTipoOrdenProceso - 50);

                //
                FachadaDctoOrdenBean fachadaDctoOrdenBean = new FachadaDctoOrdenBean();

                //
                fachadaDctoOrdenBean =
                        dctoOrdenBean.listaDctoOrdenIdLogIdTipoOrden();

                //
                fachadaDctoOrdenBean.setIdListaPrecio(xIdListaPrecio);
                fachadaDctoOrdenBean.setIdCliente(xIdTercero);
                fachadaDctoOrdenBean.setFechaOrden(xFechaCorte);
                fachadaDctoOrdenBean.setIdBodegaOrigen(xIdBodegaOrigen);
                fachadaDctoOrdenBean.setIdBodegaDestino(xIdBodegaDestino);
                fachadaDctoOrdenBean.setNumeroOrden(xNumeroOrden);
                fachadaDctoOrdenBean.setIdTipoOrden(xIdTipoOrdenProceso - 50);
                fachadaDctoOrdenBean.setIdOperario(xIdOperario);                

                //
                request.setAttribute("fachadaDctoOrdenBean",
                        fachadaDctoOrdenBean);

                //
                return "/jsp/vtaFrmIngInventarioMovimientoES.jsp";

            }
            
             //
            if (accionContenedor.compareTo("Legalizar") == 0) {

                //
                String xIdBodegaOrigen =
                        request.getParameter("xIdBodegaOrigen");
                String xIdBodegaDestino =
                        request.getParameter("xIdBodegaDestino");
                String xNumeroOrden =
                        request.getParameter("xNumeroOrden");
                String xIdTercero =
                        request.getParameter("xIdTercero");
                String xIdOperario =
                        request.getParameter("xIdOperario");                

                //
                DctoOrdenBean dctoOrdenBean = new DctoOrdenBean();

                //
                dctoOrdenBean.setIdLog(xIdLogActual);
                dctoOrdenBean.setIdTipoOrden(xIdTipoOrdenProceso-50);

                // existePedido
                boolean existePedido = dctoOrdenBean.existePedido();

                //
                if (!existePedido) {

                    //
                    Validacion validacion = new Validacion();

                    //
                    validacion.reasignar("PEDIDO", "");
                    validacion.setDescripcionError("PEDIDO YA CONFIRMADO");
                    validacion.setSolucion("INICIAR NUEVO MOVIMIENTO");

                    // Aqui escribe el Bean de Validacion en el Request para manejar el error
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralError.jsp";

                }

                //--------------------------------------------------------------
                FachadaDctoBean fachadaDctoBean = new FachadaDctoBean();


                // confirmaPedido
                ProcesoInternoInventario proceso =
                        new ProcesoInternoInventario();

                // confirma
                fachadaDctoBean =
                        proceso.ingresa(new Integer(xIdLocalUsuario).intValue(),
                        new Integer(xIdTipoOrdenProceso).intValue() - 100,
                        xIdLogActual,
                        ( xIdTipoOrdenProceso - 50 ),
                        new Integer(xNumeroOrden).intValue(),
                        new Integer(xIdBodegaDestino).intValue());

                //
                int xIdEstadoTxSinTx = 1;
                int estadoProgramado = 1;
                int tareaVisita = 6;   // Cotizacion
                
                //
                int xIdLocal = fachadaDctoBean.getIdLocal();
                int xIdTipoOrdenTraslado = fachadaDctoBean.getIdTipoOrden();
                int xIdOrden = fachadaDctoBean.getIdOrden();                
                
                //--------------------------------------------------------------
                FachadaDctoOrdenDetalleBean fachadaDctoOrdenDetalleBean 
                                            = new FachadaDctoOrdenDetalleBean();
                
                ColaboraDctoOrdenDetalleBean colaboraDctoOrdenDetalleBean 
                                           = new ColaboraDctoOrdenDetalleBean();
                
                //
                colaboraDctoOrdenDetalleBean.setIdLocal(xIdLocal);
                colaboraDctoOrdenDetalleBean.setIdTipoOrden(
                                                          xIdTipoOrdenTraslado);
                colaboraDctoOrdenDetalleBean.setIdOrden(xIdOrden);
                colaboraDctoOrdenDetalleBean.setIdBodega(xIdBodegaOrigen);                                
                
                //
                fachadaDctoOrdenDetalleBean = 
                                  colaboraDctoOrdenDetalleBean.calculaPesoFCH();
                
                //
                double xPedoTerminado = fachadaDctoOrdenDetalleBean.getCantidad();
                
                //--------------------------------------------------------------
                int xItemPadre = 1 ;
                
                //
                FachadaPluFicha fachadaPluFicha = new FachadaPluFicha();
                
                //
                ColaboraOrdenTrabajo colaboraOrdenTrabajo = 
                                                     new ColaboraOrdenTrabajo(); 
                
                //
                colaboraOrdenTrabajo.setNumeroOrden(xNumeroOrden);
                
                //
                fachadaPluFicha =  colaboraOrdenTrabajo.listaUnOTFCH(xItemPadre);
                
                //
                int xIdFicha = fachadaPluFicha.getIdFicha();
                String xIdCliente = fachadaPluFicha.getIdCliente();
                int xIdLocalOrigen     = fachadaPluFicha.getIdLocal();
                int xIdTipoOrdenOrigen = fachadaPluFicha.getIdTipoOrden();
                int xIdOrdenOrigen     = fachadaPluFicha.getIdOrden();                
                
                //----------------------- ingresar  documento ------------------
                ColaboraOperacionPlu colaboraOperacionPlu =
                        new ColaboraOperacionPlu();

                //
                colaboraOperacionPlu.setIdFicha(xIdFicha);
                colaboraOperacionPlu.setIdOperacion(xIdBodegaDestino);

                //
                double xTotalMP = colaboraOperacionPlu.totalMP();
                String xObservacion = "";         
                xIdTipoOrdenTransformacion = 11;
                
                
                //-------------------------- detalle ---------------------------
                ProcesoOTTraslado procesoOT = new ProcesoOTTraslado();

                //
                int xIdOrdenCruce =
                        procesoOT.ingresa(new Integer(xIdLocal).intValue(),
                        xIdTipoOrdenTransformacion,
                        xIdUsuario,
                        xIdFicha,
                        new Integer(xIdBodegaDestino).intValue(),
                        new Double(xPedoTerminado).doubleValue(),
                        new Double(xPedoTerminado).doubleValue(),
                        xTotalMP,
                        xIdCliente,
                        xIdOrdenOrigen,
                        xIdLocalOrigen,
                        xIdTipoOrdenOrigen,
                        new Integer(xNumeroOrden).intValue() ,
                        new Integer(xItemPadre).intValue(),
                        xObservacion);                
                
                //--------------------------------------------------------------
                FachadaJobOperacionCosto fachadaJobOperacionCosto 
                                               = new FachadaJobOperacionCosto();

                //
                JobOperacionCostoBean jobOperacionCostoBean =
                        new JobOperacionCostoBean();

                //---
                jobOperacionCostoBean.setIdLocal(xIdLocal);
                jobOperacionCostoBean.setIdOperacion(xIdBodegaDestino);

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
                int xIdControlTipoOTExterna = 2;   
                
                //--------------------------------------------------------------
                double xCantidadCero = 0.0;
                double xPesoTara = 0.0;
                int xIdCausaCero = 0;
                int xEstado = 1;
                xObservacionVacia = "";
                String xIdDctoNitCC = "";
                
                //--------------------------------------------------------------
                DctoOrdenProgresoBean dctoOrdenProgresoBean = new DctoOrdenProgresoBean();

                //
                dctoOrdenProgresoBean.setIdLocal(xIdLocalOrigen);
                dctoOrdenProgresoBean.setIdTipoOrden(xIdTipoOrdenOrigen);
                dctoOrdenProgresoBean.setIdOrden(xIdOrdenOrigen);
                dctoOrdenProgresoBean.setIdOperacion(xIdBodegaDestino);
                dctoOrdenProgresoBean.setItemPadre(xItemPadre);                
                
                //
                int xMaximoItem = dctoOrdenProgresoBean.maximoItem() + 1;
                
                //---
                dctoOrdenProgresoBean.setItem(xMaximoItem);
                dctoOrdenProgresoBean.setIdOperario(xIdOperario);
                dctoOrdenProgresoBean.setCantidadPerdida(xCantidadCero);
                dctoOrdenProgresoBean.setCantidadTerminada(xPedoTerminado);
                dctoOrdenProgresoBean.setPesoTerminado(xPedoTerminado);
                dctoOrdenProgresoBean.setPesoPerdido(xCantidadCero);
                dctoOrdenProgresoBean.setIdCausa(xIdCausaCero);
                dctoOrdenProgresoBean.setIdTipoCausa(xIdTipoCausaProduccion);
                dctoOrdenProgresoBean.setEstado(xEstado);
                dctoOrdenProgresoBean.setItemPadre(xItemPadre);
                dctoOrdenProgresoBean.setVrCostoBaseMAT(xVrCostoBaseMAT);
                dctoOrdenProgresoBean.setVrCostoBaseCIF(xVrCostoBaseCIF);
                dctoOrdenProgresoBean.setVrCostoBaseMOD(xVrCostoBaseMOD);
                dctoOrdenProgresoBean.setPesoTara(xPesoTara);
                dctoOrdenProgresoBean.setIdUsuario(xIdUsuario);
                dctoOrdenProgresoBean.setCantidadPendiente(xCantidadCero);
                dctoOrdenProgresoBean.setObservacion(xObservacionVacia);
                dctoOrdenProgresoBean.setIdOrdenCruce(xIdOrdenCruce);    
                dctoOrdenProgresoBean.setIdDctoNitCC(xIdDctoNitCC);   
                dctoOrdenProgresoBean.setIdControlTipo(xIdControlTipoOTExterna);                   
        
                //---
                dctoOrdenProgresoBean.ingresa();  
                


                //
                dctoOrdenProgresoBean.setIdControlTipo(xIdControlTipoOTExterna);

                //
                int xMaximoidControlExterno =
                        dctoOrdenProgresoBean.maximoIdControExterno() + 1;

                //
                dctoOrdenProgresoBean.setIdControl(xMaximoidControlExterno);
                dctoOrdenProgresoBean.setIdOrden(xIdOrdenOrigen);
                dctoOrdenProgresoBean.setItemPadre(xItemPadre);
                dctoOrdenProgresoBean.setIdOperacion(xIdBodegaDestino);
                dctoOrdenProgresoBean.setObservacion(xObservacion);
                dctoOrdenProgresoBean.setIdUsuario(xIdUsuario);

                //
                dctoOrdenProgresoBean.actualizaOrdenExterna();

                //--------------------------------------------------------------
                GeneraPDFResurtido generaPDFResurtido = new GeneraPDFResurtido();

                //
                generaPDFResurtido.setIdLocal(fachadaDctoBean.getIdLocalStr());
                generaPDFResurtido.setIdOrden(fachadaDctoBean.getIdOrden());
                generaPDFResurtido.setIdTipoOrden(
                        fachadaDctoBean.getIdTipoOrdenStr());
                generaPDFResurtido.setReporteName("RepEmpresaResurtido");

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
            
        }

        //
        return "/jsp/empty.htm";
    }
}
