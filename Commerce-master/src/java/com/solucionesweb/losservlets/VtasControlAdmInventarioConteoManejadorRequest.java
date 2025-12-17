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
public class VtasControlAdmInventarioConteoManejadorRequest
                                               implements GralManejadorRequest {

  // Metodo contructor por defecto, es decir, sin parametros
  public VtasControlAdmInventarioConteoManejadorRequest () { }

  /**
   * Retorna la URL de la pagina que deber? ser entregada como respuesta
   * (normalmente un pagina jsp).
   */
  public String generaPdf(HttpServletRequest request,
                                                   HttpServletResponse response)
                                       throws ServletException,IOException     {

    //
    int xDiasHistoria              = 30;
    int xDiasInventario            = 15;
    int xIdTipoOrdenTraslado       = 2;
    int estadoActivo               = 9;
    int xIdTipoTerceroProveedor    = 2;

    //
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
    fachadaDctoOrdenBean.setIdTipoOrden(xIdTipoOrdenTraslado + 50);
    fachadaDctoOrdenBean.setIdUsuario(xIdUsuario);
    fachadaDctoOrdenBean.setFechaOrden(strFechaVisita);
    fachadaDctoOrdenBean.setDiasHistoria(xDiasHistoria);
    fachadaDctoOrdenBean.setDiasInventario(xDiasInventario);
    fachadaDctoOrdenBean.setIdTipoTercero(xIdTipoTerceroProveedor);


    // Aqui escribe el Bean de Validacion en el Request para manejar el error
    request.setAttribute("fachadaDctoOrdenBean",fachadaDctoOrdenBean);

    //
    return "/jsp/vtaContenedorInventarioConteo.jsp";

  }
}

