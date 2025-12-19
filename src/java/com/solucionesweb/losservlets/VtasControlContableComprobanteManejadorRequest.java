package com.solucionesweb.losservlets;

// Importa los paquetes del lenguaje especificamente
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Importa la clase que contiene el FachadaDctoOrdenBean
import com.solucionesweb.losbeans.fachada.FachadaDctoOrdenBean;

// Importa la clase que contiene el Day
import com.solucionesweb.losbeans.utilidades.Day;

// Importa la clase que contiene el ValidacionUsuarioBean
import com.solucionesweb.losbeans.negocio.UsuarioBean;

//
public class VtasControlContableComprobanteManejadorRequest
                                               implements GralManejadorRequest {

  // Metodo contructor por defecto, es decir, sin parametros
  public VtasControlContableComprobanteManejadorRequest () { }

  /**
   * Retorna la URL de la pagina que deber? ser entregada como respuesta
   * (normalmente un pagina jsp).
   */
  public String generaPdf(HttpServletRequest request,
                                                   HttpServletResponse response)
                                       throws ServletException,IOException     {

    //
    String xIdAlcance              = request.getParameter("xIdAlcance");

    //
    int xIdTipoOrden               = 0;
    int xIdAlcanceEgresoIngreso    = 4;

    //¿
    Day day                        = new Day();

    //
    String strFechaVisita          = day.getFechaFormateada();

    //
    HttpSession sesion             = request.getSession();

    //
    UsuarioBean usuarioBean        =
                               (UsuarioBean)sesion.getAttribute("usuarioBean");

    //
    String xIdUsuario              = usuarioBean.getIdUsuarioStr();
    int xIdLocalUsuario            = usuarioBean.getIdLocalUsuario();

    //
    FachadaDctoOrdenBean fachadaDctoOrdenBean
                                      = new FachadaDctoOrdenBean();

    //
    fachadaDctoOrdenBean.setIdLocal(xIdLocalUsuario);
    fachadaDctoOrdenBean.setIdTipoOrden(xIdTipoOrden);
    fachadaDctoOrdenBean.setIdUsuario(xIdUsuario);
    fachadaDctoOrdenBean.setFechaOrden(strFechaVisita);
    fachadaDctoOrdenBean.setIdAlcance(xIdAlcance);
    fachadaDctoOrdenBean.setTitulo(xIdAlcance);

    // Aqui escribe el Bean de Validacion en el Request para manejar el error
    request.setAttribute("fachadaDctoOrdenBean",fachadaDctoOrdenBean);

    //
    return "/jsp/vtaContenedorContableComprobante.jsp";

  }
}

