package com.solucionesweb.losservlets;

// Importa los paquetes del lenguaje especificamente io y los
// que permiten trabajar el servlet.
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Importa la clase que contiene el ValidacionColaboraReporteDctoBean
import com.solucionesweb.losbeans.utilidades.ValidacionColaboraReporteDctoBean;

// Importa la clase que contiene el FachadaAgendaLogVisitaBean
import com.solucionesweb.losbeans.fachada.FachadaColaboraLogisticaBean;

// Importa la clase que contiene el FachadaDctoOrdenBean
import com.solucionesweb.losbeans.fachada.FachadaDctoOrdenBean;

// Importa la clase que contiene el FachadaAgendaLogVisitaBean
import com.solucionesweb.losbeans.fachada.FachadaAgendaLogVisitaBean;

// Importa la clase que contiene el UsuarioBean
import com.solucionesweb.losbeans.negocio.UsuarioBean;

// Importa la clase que contiene el DctoOrdenBean
import com.solucionesweb.losbeans.negocio.DctoOrdenBean;

// Este servlet implementa la interface GralManejadorRequest
public class VtasAdmClienteAllListaPedidoManejadorRequest
                                               implements GralManejadorRequest {

  // Metodo contructor por defecto, es decir, sin parametros
  public VtasAdmClienteAllListaPedidoManejadorRequest () { }

  /**
   * Retorna la URL de la pagina que deber� ser entregada como respuesta
   * (normalmente un pagina jsp).
   */
  public String generaPdf(HttpServletRequest request,
                                                   HttpServletResponse response)
                throws ServletException, IOException  {

    //
    String accionContenedor = request.getParameter("accionContenedor");

    // Validacion de accion relacionada con el formulario requerido
    if (accionContenedor != null ) {

        //
	    if (accionContenedor.compareTo("Regresar") == 0 ) {
            return "/jsp/mnuControlTraficoMovilBB.jsp";
        }

	    if (accionContenedor.compareTo("Consultar") == 0 ) {

           String fechaInicial = request.getParameter("fechaInicial");
           String fechaFinal   = request.getParameter("fechaFinal");

           //
           HttpSession sesion      = request.getSession();
           UsuarioBean usuarioBean
                              = (UsuarioBean)sesion.getAttribute("usuarioBean");
           String idUsuario        = usuarioBean.getIdUsuarioStr();

           // Bean de ValidacionLogPcBean
           ValidacionColaboraReporteDctoBean campoAValidar
           = new ValidacionColaboraReporteDctoBean("fechaInicial",fechaInicial);

           // Valida el fechaInicial
           campoAValidar.validarCampoFecha();

           if (campoAValidar.isValido() == false) {

              // Aqui escribe el Bean de Validacion en el Request para manejar el error
	          request.setAttribute("validacionColaboraReporteDctoBean",
                                                                 campoAValidar);

              return "/gralErrReporteDcto.jsp";
           }

           // Valida el fechaFinal
           campoAValidar.reasignar("fechaFinal",fechaFinal);

           // Valida el fechaFinal
           campoAValidar.validarCampoFecha();

           if (campoAValidar.isValido() == false) {

              // Aqui escribe el Bean de Validacion en el Request para manejar el error
	          request.setAttribute("validacionColaboraReporteDctoBean",
                                                                 campoAValidar);

              return "/jsp/gralErrReporteDcto.jsp";
           }

           //
           String idTipoPedido   = "PB";
           int idTipoOrdenPedido = 9;

           // fachadaColaboraLogisticaBean
           FachadaColaboraLogisticaBean fachadaColaboraLogisticaBean
                                           = new FachadaColaboraLogisticaBean();
           fachadaColaboraLogisticaBean.setIdUsuario(idUsuario);
           fachadaColaboraLogisticaBean.setFechaInicial(fechaInicial);
           fachadaColaboraLogisticaBean.setFechaFinal(fechaFinal);
           fachadaColaboraLogisticaBean.setIdTipoPedido(idTipoPedido);
           fachadaColaboraLogisticaBean.setIdTipoOrden(idTipoOrdenPedido);

           // Aqui escribe el Bean de Validacion en el Request para manejar el error
           request.setAttribute("fachadaColaboraLogisticaBean",
                                                  fachadaColaboraLogisticaBean);
           return "/jsp/vtaFrmLstClienteAllPedido.jsp";
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
    	   FachadaDctoOrdenBean fachadaDctoOrdenBean = new FachadaDctoOrdenBean();
    	   fachadaDctoOrdenBean = dctoOrdenBean.listaDctoOrden();

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

           //
    	   fachadaDctoOrdenBean = dctoOrdenBean.listaDctoOrdenIdLogIdTipoOrden();

           request.setAttribute("fachadaDctoOrdenBean",fachadaDctoOrdenBean);
           request.setAttribute("fachadaAgendaLogVisitaBean",
                                                    fachadaAgendaLogVisitaBean);
           return "/jsp/vtaFrmConClienteAllPedido.jsp";
        }
    }

    return "/jsp/mnuControlTraficoMovilBB.jsp";

  }
}