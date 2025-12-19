package com.solucionesweb.losservlets;

// Importa los paquetes del lenguaje especificamente
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Importa la clase que contiene el AgendaControlBean
import com.solucionesweb.losbeans.negocio.AgendaLogVisitaBean;

// Importa la clase que contiene el FachadaAgendaLogVisitaBean
import com.solucionesweb.losbeans.fachada.FachadaAgendaLogVisitaBean;

// Importa la clase que contiene el Day
import com.solucionesweb.losbeans.utilidades.Day;

// Importa la clase que contiene el ValidacionUsuarioBean
import com.solucionesweb.losbeans.negocio.UsuarioBean;

/**
 * Este servlet valida que el usuario tenga permiso para entrar a esta opcion.
 * Este servlet implementa la interface GralManejadorRequest
*/
public class VtasControlClienteNuevoActualizarManejadorRequest
                                        implements GralManejadorRequest {

  // Metodo contructor por defecto, es decir, sin parametros
  public VtasControlClienteNuevoActualizarManejadorRequest () { }

  /**
   * Retorna la URL de la pagina que deber� ser entregada como respuesta
   * (normalmente un pagina jsp).
   */
  public String generaPdf(HttpServletRequest request,HttpServletResponse response)
                throws ServletException,IOException     {

    //
    String strIdSucursal  = "--";
    String idCliente      = "-1";
    int estadoActivo      = 9;
    Day day               = new Day();
    String strFechaVisita = day.getFechaFormateada();

    //
    HttpSession sesion      = request.getSession();
    UsuarioBean usuarioBean = (UsuarioBean)sesion.getAttribute("usuarioBean");
    String idUsuario        = usuarioBean.getIdUsuarioStr();

    //
    AgendaLogVisitaBean agendaLogVisitaBean = new AgendaLogVisitaBean();
    agendaLogVisitaBean.setEstado(estadoActivo);
    agendaLogVisitaBean.setFechaVisitaStr(strFechaVisita);
    agendaLogVisitaBean.setIdUsuario(idUsuario);

    //
    FachadaAgendaLogVisitaBean  fachadaAgendaLogVisitaBean =
                                               new FachadaAgendaLogVisitaBean();

    //
    fachadaAgendaLogVisitaBean.setIdUsuario(idUsuario);

    // Aqui escribe el Bean de Validacion en el Request para manejar el error
    request.setAttribute("fachadaAgendaLogVisitaBean",fachadaAgendaLogVisitaBean);
    return "/jsp/vtaContenedorClienteNuevoActualizar.jsp";

  }
}