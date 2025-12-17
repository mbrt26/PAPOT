package com.solucionesweb.losservlets;

// Importa los paquetes del lenguaje especificamente io y los
// que permiten trabajar el servlet.
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Importa la clase que contiene el AgendaControlBean
import com.solucionesweb.losbeans.negocio.AgendaLogVisitaBean;

// Importa la clase que contiene el AgendaControlBean
import com.solucionesweb.losbeans.fachada.FachadaAgendaLogVisitaBean;

// Importa la clase que contiene el FachadaTerceroBean
import com.solucionesweb.losbeans.fachada.FachadaTerceroBean;

// Importa la clase que contiene el Day
import com.solucionesweb.losbeans.utilidades.Day;

// Importa la clase que contiene el ValidacionUsuarioBean
import com.solucionesweb.losbeans.negocio.UsuarioBean;
import javax.servlet.http.HttpSession;

// Importa la clase que contiene el ValidacionUsuarioBean
import com.solucionesweb.losservlets.GeneraPDFCxC;

// Este servlet implementa la interface GralManejadorRequest
public class VtasAdmConsultarCxCConsolidadaManejadorRequest
                                               implements GralManejadorRequest {

  // Metodo contructor por defecto, es decir, sin parametros
  public VtasAdmConsultarCxCConsolidadaManejadorRequest () { }

  /**
   * Retorna la URL de la pagina que deber? ser entregada como respuesta
   * (normalmente un pagina jsp).
   */
  public String generaPdf(HttpServletRequest request,HttpServletResponse response)
                throws ServletException, IOException  {

    //
    String accionContenedor = request.getParameter("accionContenedor");

    //---------
    int xIdTipoTerceroCliente      = 1;
    int xIdTipoOrdenPedido         = 9;

    //
    int estadoActivo               = 9;
    Day day                        = new Day();
    String strFechaVisita          = day.getFechaFormateada();

    //
    HttpSession sesion             = request.getSession();
    UsuarioBean usuarioBean        =
                                (UsuarioBean)sesion.getAttribute("usuarioBean");
    String idUsuario               = usuarioBean.getIdUsuarioStr();
    int xIdLocalUsuario            = usuarioBean.getIdLocalUsuario();

    //
    AgendaLogVisitaBean agendaLogVisitaBean
                                    = new AgendaLogVisitaBean();
    agendaLogVisitaBean.setEstado(estadoActivo);
    agendaLogVisitaBean.setFechaVisitaStr(strFechaVisita);
    agendaLogVisitaBean.setIdUsuario(idUsuario);

    //
    FachadaAgendaLogVisitaBean  fachadaAgendaLogVisitaBean
                                     = new FachadaAgendaLogVisitaBean();
    fachadaAgendaLogVisitaBean       =
                              agendaLogVisitaBean.seleccionaVisitaEstadoFecha();

    //
    fachadaAgendaLogVisitaBean.setIdUsuario(idUsuario);

    // Aqui escribe el Bean de Validacion en el Request para manejar el error
    request.setAttribute("fachadaAgendaLogVisitaBean",
                                                    fachadaAgendaLogVisitaBean);

    // Retorna a seleccionar cliente
    if (fachadaAgendaLogVisitaBean.getIdCliente() == null ) {

       //
       FachadaTerceroBean fachadaTerceroBean
                                    = new FachadaTerceroBean();

       //
       request.setAttribute("fachadaTerceroBean",fachadaTerceroBean);

       //
       return "/jsp/vtaContenedorClienteSeleccionarNombre.jsp";

    }

    //
    FachadaTerceroBean fachadaTerceroBean
                                    = new FachadaTerceroBean();

    //
    fachadaTerceroBean.setIdCliente(fachadaAgendaLogVisitaBean.getIdCliente());
    fachadaTerceroBean.setIdTipoTercero(xIdTipoTerceroCliente);

    // Validacion de accion relacionada con el formulario requerido
    if (accionContenedor != null ) {

        //
	    if (accionContenedor.compareTo("Regresar") == 0 ) {
                return "/jsp/mnuControlClienteBB.jsp";
        }



        //
        if (accionContenedor.compareTo("Listar") == 0 ) {

            //
            GeneraPDFCxC generaPDFCxC = new GeneraPDFCxC();

            //
            generaPDFCxC.setIdLocal(xIdLocalUsuario);
            generaPDFCxC.setIdTipoOrden(xIdTipoOrdenPedido);
            generaPDFCxC.setIdCliente(
                                     fachadaAgendaLogVisitaBean.getIdCliente());

            //
            generaPDFCxC.generaPdf(request, response);

            //
            return "/jsp/empty.htm";
        }
    }

    return "/jsp/empty.htm";

  }
}