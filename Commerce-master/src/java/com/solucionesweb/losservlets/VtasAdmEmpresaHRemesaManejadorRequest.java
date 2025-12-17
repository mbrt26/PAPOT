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

// Importa la clase que contiene el AgendaControlBean
import com.solucionesweb.losbeans.negocio.AgendaLogVisitaBean;

// Importa la clase que contiene el Validacion
import com.solucionesweb.losbeans.utilidades.Validacion;

// Importa la clase que contiene el FachadaColaboraLogisticaBean
import com.solucionesweb.losbeans.fachada.FachadaColaboraLogisticaBean;

// Importa la clase que contiene el UsuarioBean
import com.solucionesweb.losbeans.negocio.UsuarioBean;

// Importa la clase que contiene el FachadaAgendaLogVisitaBean
import com.solucionesweb.losbeans.fachada.FachadaAgendaLogVisitaBean;

// Importa la clase que contiene el FachadaDctoOrdenBean
import com.solucionesweb.losbeans.fachada.FachadaDctoOrdenBean;

// Importa la clase que contiene el DctoOrdenBean
import com.solucionesweb.losbeans.negocio.DctoOrdenBean;

// Importa la clase que contiene el FachadaTerceroBean
import com.solucionesweb.losbeans.fachada.FachadaTerceroBean;

// Este servlet implementa la interface GralManejadorRequest
public class VtasAdmEmpresaHRemesaManejadorRequest
                                               implements GralManejadorRequest {

  // Metodo contructor por defecto, es decir, sin parametros
  public VtasAdmEmpresaHRemesaManejadorRequest () { }

  /**
   * Retorna la URL de la pagina que deber? ser entregada como respuesta
   * (normalmente un pagina jsp).
   */
  public String generaPdf(HttpServletRequest request,HttpServletResponse response)
                throws ServletException, IOException  {

    //
    String accionContenedor = request.getParameter("accionContenedor");

    //
    String idTipoPedido       = "PB";
    int estadoActivo          = 9;
    String xIdTipoOrdenCadena = "9,29"; // venta + nota venta


    // strFechaVisita
    Day day                   = new Day();
    String strFechaVisita     = day.getFechaFormateada();

    //
    HttpSession sesion        = request.getSession();

    //
    UsuarioBean usuarioBean   = (UsuarioBean)sesion.getAttribute("usuarioBean");

    //
    String idUsuario               = usuarioBean.getIdUsuarioStr();

    //
    int xIdLocalUsuario            = usuarioBean.getIdLocalUsuario();
    int xIndicadorInicial          = usuarioBean.getIndicadorInicial();
    int xIndicadorFinal            = usuarioBean.getIndicadorFinal();

    // Validacion de accion relacionada con el formulario requerido
    if (accionContenedor != null ) {

        //
	    if (accionContenedor.compareTo("detallarCotizacion") == 0 ) {

               String idLocal = request.getParameter("idLocal");
               String idTipoOrden = request.getParameter("idTipoOrden");
               String idOrden = request.getParameter("idOrden");

               //
               GeneraPDFRemesa generaPDFServlet
                                             = new GeneraPDFRemesa();

               //
               generaPDFServlet.setIdLocal(xIdLocalUsuario);
               generaPDFServlet.setIdOrden(idOrden);
               generaPDFServlet.setIdTipoOrden(idTipoOrden);

               //
               generaPDFServlet.generaPdf(request, response);

        }

        //
	    if (accionContenedor.compareTo("Regresar") == 0 ) {
            return "/jsp/empty.htm";
        }

        //
	    if (accionContenedor.compareTo("Salir") == 0 ) {
            return "/jsp/empty.htm";
        }

        //
	    if (accionContenedor.compareTo("Listar") == 0 ) {

           //
           String xIdTipoOrden    = request.getParameter("xIdTipoOrden");
           String xFechaInicial   = request.getParameter("xFechaInicial");
           String xFechaFinal     = request.getParameter("xFechaFinal");
           String xIdCliente      = request.getParameter("xIdCliente");
           String xIdLocal        = request.getParameter("xIdLocal");
           String xIdTipoTercero = request.getParameter("xIdTipoTercero");

           //
           GeneraPDFHistoriaCxC generaPDF = new GeneraPDFHistoriaCxC();

           //
           generaPDF.setIdCliente(xIdCliente);
           generaPDF.setIdTipoOrdenCadena(xIdTipoOrdenCadena);
           generaPDF.setIdLocal(xIdLocal);
           generaPDF.setFechaInicial(xFechaInicial);
           generaPDF.setFechaFinal(xFechaFinal);
           generaPDF.setTerceroReporte("CLIENTE");
           generaPDF.setTituloReporte("HISTORICO REMESAS DEL " + xFechaInicial +
                                      " AL " + xFechaFinal );
           generaPDF.setIndicadorINI(xIndicadorInicial);
           generaPDF.setIndicadorFIN(xIndicadorFinal);

           //
           generaPDF.generaPdf(request, response);

        }

	    if (accionContenedor.compareTo("Consultar") == 0 ) {

           //
           String xIdTipoOrden    = request.getParameter("xIdTipoOrden");
           String xFechaInicial   = request.getParameter("xFechaInicial");
           String xFechaFinal     = request.getParameter("xFechaFinal");
           String xIdCliente      = request.getParameter("xIdCliente");
           String xIdLocal        = request.getParameter("xIdLocal");
           String xIdTipoTercero = request.getParameter("xIdTipoTercero");


           // Bean de ValidacionLogPcBean
           Validacion validacion = new Validacion();

           //
           validacion.reasignar("FECHA INICIAL",xFechaInicial);

           // Valida el fechaInicial
           validacion.validarCampoFecha();

           if (validacion.isValido() == false) {

              // Aqui escribe el Bean de Validacion en el Request
	          request.setAttribute("validacion",validacion);

              return "/jsp/gralError.jsp";
           }

           //
           validacion.reasignar("FECHA FINAL",xFechaFinal);

           // Valida el fechaInicial
           validacion.validarCampoFecha();

           if (validacion.isValido() == false) {

              // Aqui escribe el Bean de Validacion en el Request
	          request.setAttribute("validacion",validacion);

              return "/jsp/gralError.jsp";
           }


           //
           AgendaLogVisitaBean agendaLogVisitaBean = new AgendaLogVisitaBean();
           agendaLogVisitaBean.setEstado(estadoActivo);
           agendaLogVisitaBean.setFechaVisitaStr(strFechaVisita);
           agendaLogVisitaBean.setIdUsuario(idUsuario);

           //
           FachadaAgendaLogVisitaBean  fachadaAgendaLogVisitaBean
                                             = new FachadaAgendaLogVisitaBean();
           fachadaAgendaLogVisitaBean        =
                              agendaLogVisitaBean.seleccionaVisitaEstadoFecha();

           // Retorna a seleccionar cliente
           if (fachadaAgendaLogVisitaBean.getIdCliente()==null) {
               return "/jsp/vtaContenedorEmpresaSelecciona.jsp";
           }

           // fachadaColaboraLogisticaBean
           FachadaColaboraLogisticaBean fachadaColaboraLogisticaBean
                                           = new FachadaColaboraLogisticaBean();
           //
           fachadaColaboraLogisticaBean.setIdUsuario(idUsuario);
           fachadaColaboraLogisticaBean.setIdCliente(
                                     fachadaAgendaLogVisitaBean.getIdCliente());
           fachadaColaboraLogisticaBean.setFechaInicial(xFechaInicial);
           fachadaColaboraLogisticaBean.setFechaFinal(xFechaFinal);
           fachadaColaboraLogisticaBean.setIdTipoPedido(idTipoPedido);
           fachadaColaboraLogisticaBean.setIdTipoOrdenCadena(
                                                            xIdTipoOrdenCadena);
           fachadaColaboraLogisticaBean.setIdLocal(xIdLocalUsuario);
           fachadaColaboraLogisticaBean.setIndicadorInicial(xIndicadorInicial);
           fachadaColaboraLogisticaBean.setIndicadorFinal(xIndicadorFinal);

           //
           FachadaTerceroBean fachadaTerceroBean = new FachadaTerceroBean();

           fachadaTerceroBean.setIdCliente(xIdCliente);
           fachadaTerceroBean.setIdTipoOrden(xIdTipoOrden);
           fachadaTerceroBean.setIdTipoTercero(xIdTipoTercero);
           fachadaTerceroBean.setIdLocal(xIdLocal);

           // Aqui escribe el Bean de Validacion en el Request
           request.setAttribute("fachadaTerceroBean",
                                                  fachadaTerceroBean);
           request.setAttribute("fachadaAgendaLogVisitaBean",
                                                  fachadaAgendaLogVisitaBean);
           request.setAttribute("fachadaColaboraLogisticaBean",
                                                  fachadaColaboraLogisticaBean);
           return "/jsp/vtaFrmLstEmpresaHRemesa.jsp";

        }

        // Seleccionar
	    if (accionContenedor.compareTo("Seleccionar") == 0 ) {

           //
           String idLocal        = request.getParameter("idLocal");
           String idTipoOrden    = request.getParameter("idTipoOrden");
           String idOrden        = request.getParameter("idOrden");

    	   //
    	   DctoOrdenBean dctoOrdenBean = new DctoOrdenBean();
    	   dctoOrdenBean.setIdLocal(idLocal);
    	   dctoOrdenBean.setIdTipoOrden(idTipoOrden);
    	   dctoOrdenBean.setIdOrden(idOrden);

    	   //
    	   FachadaDctoOrdenBean fachadaDctoOrdenBean
                                             = new FachadaDctoOrdenBean();

           //
    	   fachadaDctoOrdenBean              = dctoOrdenBean.listaDctoOrden();

    	   //
    	   FachadaAgendaLogVisitaBean fachadaAgendaLogVisitaBean
                                             = new FachadaAgendaLogVisitaBean();

    	   fachadaAgendaLogVisitaBean.setIdCliente(
                                           fachadaDctoOrdenBean.getIdCliente());
    	   fachadaAgendaLogVisitaBean.setIdLog(fachadaDctoOrdenBean.getIdLog());
           fachadaAgendaLogVisitaBean.setIdUsuario(
                                           fachadaDctoOrdenBean.getIdUsuario());


           // idTipoOrdenCotizacion
           int idTipoOrdenCotizacion = 10;
           dctoOrdenBean.setIdLog(fachadaDctoOrdenBean.getIdLog());
           dctoOrdenBean.setIdTipoOrden(idTipoOrdenCotizacion);
    	   fachadaDctoOrdenBean = dctoOrdenBean.listaDctoOrdenIdLogIdTipoOrden();

           request.setAttribute("fachadaDctoOrdenBean",fachadaDctoOrdenBean);
           request.setAttribute("fachadaAgendaLogVisitaBean",
                                                    fachadaAgendaLogVisitaBean);
           return "/jsp/vtaFrmConHRemesa.jsp";
        }


    }

    return "/jsp/empty.htm";

  }
}