package com.solucionesweb.losservlets;

// Importa los paquetes del lenguaje especificamente io y los
import com.solucionesweb.losbeans.colaboraciones.ColaboraDctoOrdenDetalleBean;
import com.solucionesweb.losbeans.colaboraciones.ColaboraTercero;
import com.solucionesweb.losbeans.fachada.FachadaAgendaLogVisitaBean;
import com.solucionesweb.losbeans.fachada.FachadaDctoOrdenBean;
import com.solucionesweb.losbeans.fachada.FachadaDctoOrdenDetalleBean;
import com.solucionesweb.losbeans.fachada.FachadaDctoOrdenProgreso;
import com.solucionesweb.losbeans.fachada.FachadaPluFicha;
import com.solucionesweb.losbeans.fachada.FachadaTerceroBean;
import com.solucionesweb.losbeans.negocio.AgendaLogVisitaBean;
import com.solucionesweb.losbeans.negocio.DctoOrdenBean;
import com.solucionesweb.losbeans.negocio.DctoOrdenProgresoBean;
import com.solucionesweb.losbeans.negocio.UsuarioBean;
import com.solucionesweb.losbeans.utilidades.Day;
import com.solucionesweb.losbeans.utilidades.FichaTecnica;
import com.solucionesweb.losbeans.utilidades.Validacion;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// Este servlet implementa la interface GralManejadorRequest
public class VtasAdmOrdenTrabajoActivoManejadorRequest
        implements GralManejadorRequest {

    // Metodo contructor por defecto, es decir, sin parametros
    public VtasAdmOrdenTrabajoActivoManejadorRequest() {
    }

    public String generaPdf(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        //
        int xEstadoSuspendido = 8;
        int xEstadoActivo = 9;

        //
        String accionContenedor = request.getParameter("accionContenedor");

        // Validacion de accion relacionada con el formulario requerido
        if (accionContenedor != null) {

            //
            if (accionContenedor.compareTo("Salir") == 0) {

                //
                return "/jsp/empty.htm";
            }

            //
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
               fachadaDctoOrdenBean        =
                                            dctoOrdenBean.listaDctoOrdenIdLog();

               //
               int xIdFicha                = fachadaDctoOrdenBean.getIdFicha();
               String xIdCliente           = fachadaDctoOrdenBean.getIdCliente();
               String xIdTipoTerceroProveedor = "2";

               //
               fachadaDctoOrdenBean.setIdTipoTercero(xIdTipoTerceroProveedor);
               fachadaDctoOrdenBean.setIdOperacion(xIdOperacion);
               fachadaDctoOrdenBean.setItemPadre(xItemPadre);

               // Aqui escribe el Bean de Validacion en el Request para manejar el error
               request.setAttribute("fachadaDctoOrdenBean",fachadaDctoOrdenBean);
               
               //
               String itemStr = "1";

               //
               String xIdTipoOrdenCotizacion = "59";
               
               //
               String xIdTipoTerceroCliente = "1";

               //
               FachadaTerceroBean fachadaTerceroBean
                                                     = new FachadaTerceroBean();

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
               request.setAttribute("fachadaTerceroBean",fachadaTerceroBean);

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

               // Aqui escribe el Bean de Validacion en el Request para manejar el error
               request.setAttribute("fachadaDctoOrdenDetalleBean",
                        fachadaDctoOrdenDetalleBean);

               //
               FichaTecnica fichaTecnica     = new FichaTecnica();


               //
               double xPesoPedido = fichaTecnica.pesoPedido(xIdCliente,
                                             new Integer(xIdFicha).intValue(),
                                     new Double(xCantidad).doubleValue());

               //
               FachadaPluFicha fachadaPluFicha = new FachadaPluFicha();

               //
               fachadaPluFicha.setIdOperacion(xIdOperacion);
               fachadaPluFicha.setIdFicha(xIdFicha);
               fachadaPluFicha.setPesoPedido(xPesoPedido);
               fachadaPluFicha.setCantidad(xCantidad);

               // Aqui escribe el Bean de Validacion en el Request para manejar el error
               request.setAttribute("fachadaPluFicha",fachadaPluFicha);

               //
               FachadaPluFicha fachadaPluFichaPedido = new FachadaPluFicha();

               //
               DctoOrdenProgresoBean dctoOrdenProgresoBean =
                                                   new  DctoOrdenProgresoBean();

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
               return "/jsp/vtaFrmIngOrdenTrabajoActivo.jsp";

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
               fachadaDctoOrdenBean        =
                                            dctoOrdenBean.listaDctoOrdenIdLog();
               //
               int xIdFicha                = fachadaDctoOrdenBean.getIdFicha();
               String xIdCliente           = fachadaDctoOrdenBean.getIdCliente();
               int xIdOrden                = fachadaDctoOrdenBean.getIdOrden();
               String xIdTipoTerceroProveedor = "2";

               //
               fachadaDctoOrdenBean.setIdTipoTercero(xIdTipoTerceroProveedor);
               fachadaDctoOrdenBean.setIdOperacion(xIdOperacion);
               fachadaDctoOrdenBean.setItemPadre(xItemPadre);

               // Aqui escribe el Bean de Validacion en el Request para manejar el error
               request.setAttribute("fachadaDctoOrdenBean",fachadaDctoOrdenBean);

               //
               String itemStr = "1";

               //
               String xIdTipoOrdenCotizacion = "59";

               //
               String xIdTipoTerceroCliente = "1";

               //
               FachadaTerceroBean fachadaTerceroBean
                                                     = new FachadaTerceroBean();

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
               request.setAttribute("fachadaTerceroBean",fachadaTerceroBean);

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

               // Aqui escribe el Bean de Validacion en el Request para manejar el error
               request.setAttribute("fachadaDctoOrdenDetalleBean",
                        fachadaDctoOrdenDetalleBean);

               //
               DctoOrdenProgresoBean dctoOrdenProgresoBean
                                             = new DctoOrdenProgresoBean();

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

               //
               FichaTecnica fichaTecnica     = new FichaTecnica();


               //
               double xPesoPedido = fichaTecnica.pesoPedido(xIdCliente,
                                             new Integer(xIdFicha).intValue(),
                                     new Double(xCantidad).doubleValue());

               //
               FachadaPluFicha fachadaPluFicha = new FachadaPluFicha();

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

               //
               fachadaDctoOrdenConcluida =
                                          dctoOrdenProgresoBean.listaTotalFCH();

               // Aqui escribe el Bean de Validacion en el Request para manejar el error
               request.setAttribute("fachadaDctoOrdenConcluida",
                                                     fachadaDctoOrdenConcluida);

               // Aqui escribe el Bean de Validacion en el Request para manejar el error
               request.setAttribute("fachadaPluFicha",fachadaPluFicha);

               //
               return "/jsp/vtaFrmIngOrdenTrabajoActivo.jsp";
            }

            //
            if (accionContenedor.compareTo("Confirmar") == 0) {

               //
               String xIdLocal = request.getParameter("xIdLocal");
               String xIdTipoOrden = request.getParameter("xIdTipoOrden");
               String xIdLog = request.getParameter("xIdLog");
               String xCantidadTerminada =
                                     request.getParameter("xCantidadTerminada");
               String xPesoTerminado = request.getParameter("xPesoTerminado");
               String xPesoPerdido = request.getParameter("xPesoPerdido");
               String xIdOperario = request.getParameter("xIdTercero");
               String xIdCausa = "0";
               String xEstado = "1";
               String xIdOperacion = request.getParameter("xIdOperacion");
               String xItemPadre = request.getParameter("xItemPadre");

               //
               Validacion valida           = new Validacion();

                //
                valida.reasignar("CANTIDAD CONCLUIDA",xCantidadTerminada);

                //
    	        valida.validarCampoDoublePositivo();

                //isValido
                if (!valida.isValido()){

                   //
 	               request.setAttribute("validacion",valida);
                   return "/jsp/gralError.jsp";
                }

                //
                valida.reasignar("KILOS CONCLUIDO",xPesoTerminado);

                //
    	        valida.validarCampoDoublePositivo();

                //isValido
                if (!valida.isValido()){

                   //
 	               request.setAttribute("validacion",valida);
                   return "/jsp/gralError.jsp";
                }

                //
                valida.reasignar("KILOS RETAL",xPesoPerdido);

                //
    	        valida.validarCampoDoublePositivo();

                //isValido
                if (!valida.isValido()){

                   //
 	               request.setAttribute("validacion",valida);
                   return "/jsp/gralError.jsp";
                }

                //
                valida.reasignar("OPERARIO/TERCERO",xIdOperario);

                //
    	        valida.validarCampoDouble();

                //isValido
                if (!valida.isValido()){

                   //
 	               request.setAttribute("validacion",valida);
                   return "/jsp/gralError.jsp";
                }

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
               fachadaDctoOrdenBean        =
                                            dctoOrdenBean.listaDctoOrdenIdLog();
               //
               int xIdFicha                = fachadaDctoOrdenBean.getIdFicha();
               String xIdCliente           = fachadaDctoOrdenBean.getIdCliente();
               String xIdTipoTerceroProveedor = "2";

               //
               fachadaDctoOrdenBean.setIdTipoTercero(xIdTipoTerceroProveedor);
               fachadaDctoOrdenBean.setIdOperacion(xIdOperacion);
               fachadaDctoOrdenBean.setItemPadre(xItemPadre);

               // Aqui escribe el Bean de Validacion en el Request para manejar el error
               request.setAttribute("fachadaDctoOrdenBean",fachadaDctoOrdenBean);

               //
               String itemStr = "1";

               //
               String xIdTipoOrdenCotizacion = "59";

               //
               String xIdTipoTerceroCliente = "1";

               //
               FachadaTerceroBean fachadaTerceroBean
                                                     = new FachadaTerceroBean();

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
               request.setAttribute("fachadaTerceroBean",fachadaTerceroBean);

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
               Double xCantidadPerdida = 0.0;

               // Aqui escribe el Bean de Validacion en el Request para manejar el error
               request.setAttribute("fachadaDctoOrdenDetalleBean",
                        fachadaDctoOrdenDetalleBean);

               //
               DctoOrdenProgresoBean dctoOrdenProgresoBean
                                             = new DctoOrdenProgresoBean();

               //
               dctoOrdenProgresoBean.setIdLocal(xIdLocal);
               dctoOrdenProgresoBean.setIdTipoOrden(xIdTipoOrden);
               dctoOrdenProgresoBean.setIdOrden(
                                             fachadaDctoOrdenBean.getIdOrden());
               dctoOrdenProgresoBean.setItemPadre(xItemPadre);
               dctoOrdenProgresoBean.setIdOperacion(xIdOperacion);
               
               //
               int xMaximoItem = dctoOrdenProgresoBean.maximoItem() + 1 ;

               //
               dctoOrdenProgresoBean.setItem(xMaximoItem);
               dctoOrdenProgresoBean.setIdOperario(xIdOperario);
               dctoOrdenProgresoBean.setCantidadPerdida(xCantidadPerdida);
               dctoOrdenProgresoBean.setCantidadTerminada(xCantidadTerminada);
               dctoOrdenProgresoBean.setPesoTerminado(xPesoTerminado);
               dctoOrdenProgresoBean.setPesoPerdido(xPesoPerdido);
               //dctoOrdenProgresoBean.setFechaInicio(xIdLocal);
               //dctoOrdenProgresoBean.setFechaFin(xIdLocal);
               dctoOrdenProgresoBean.setIdCausa(xIdCausa);
               dctoOrdenProgresoBean.setEstado(xEstado);
               dctoOrdenProgresoBean.setItemPadre(xItemPadre);

               //
               dctoOrdenProgresoBean.ingresa();

               //
               FichaTecnica fichaTecnica     = new FichaTecnica();


               //
               double xPesoPedido = fichaTecnica.pesoPedido(xIdCliente,
                                             new Integer(xIdFicha).intValue(),
                                     new Double(xCantidad).doubleValue());

               //
               FachadaPluFicha fachadaPluFicha = new FachadaPluFicha();

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
               request.setAttribute("fachadaPluFicha",fachadaPluFicha);
               request.setAttribute("fachadaDctoOrdenConcluida",
                                                     fachadaDctoOrdenConcluida);

               //
               return "/jsp/vtaFrmIngOrdenTrabajoActivo.jsp";
            }
        }

        //
        return "/jsp/empty.htm";
    }
}
