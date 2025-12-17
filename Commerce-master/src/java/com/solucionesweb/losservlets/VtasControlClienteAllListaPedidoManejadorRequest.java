package com.solucionesweb.losservlets;

// Importa los paquetes del lenguaje especificamente
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Importa la clase que contiene el FachadaAgendaLogVisitaBean
import com.solucionesweb.losbeans.fachada.FachadaColaboraReporteDctoBean;

// Importa la clase que contiene el Day
import com.solucionesweb.losbeans.utilidades.Day;

// Importa la clase que contiene el ValidacionUsuarioBean
import com.solucionesweb.losbeans.negocio.UsuarioBean;

/**
 * Este servlet valida que el usuario tenga permiso para entrar a esta opcion.
 * Este servlet implementa la interface GralManejadorRequest
*/
public class VtasControlClienteAllListaPedidoManejadorRequest implements GralManejadorRequest
{

  // Metodo contructor por defecto, es decir, sin parametros
  public VtasControlClienteAllListaPedidoManejadorRequest () { }

  /**
   * Retorna la URL de la pagina que deber� ser entregada como respuesta
   * (normalmente un pagina jsp).
   */
  public String generaPdf(HttpServletRequest request,HttpServletResponse response)
                throws ServletException,IOException     {

    //
    int diasAtras          = -8;

    //
    Day fechaFinal         = new Day();
    String fechaFinalStr   = fechaFinal.getFechaFormateada();

    // FechaInicial
    fechaFinal.advance(diasAtras);
    String fechaInicialStr = fechaFinal.getFechaFormateada();

    //
    HttpSession sesion      = request.getSession();
    UsuarioBean usuarioBean = (UsuarioBean)sesion.getAttribute("usuarioBean");
    String idUsuario        = usuarioBean.getIdUsuarioStr();

    //
    FachadaColaboraReporteDctoBean  fachadaColaboraReporteDctoBean = new FachadaColaboraReporteDctoBean();
    fachadaColaboraReporteDctoBean.setIdUsuario(idUsuario);
    fachadaColaboraReporteDctoBean.setFechaInicial(fechaInicialStr);
    fachadaColaboraReporteDctoBean.setFechaFinal(fechaFinalStr);

    // Aqui escribe el Bean de Validacion en el Request para manejar el error
    request.setAttribute("fachadaColaboraReporteDctoBean",fachadaColaboraReporteDctoBean);
    return "/jsp/vtaContenedorLstClienteAllPedido.jsp";

  }
}