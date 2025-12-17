package com.solucionesweb.losservlets;

// Importa los paquetes del lenguaje especificamente
import com.solucionesweb.losbeans.fachada.FachadaCategoriaBean;
import com.solucionesweb.losbeans.negocio.UsuarioBean;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Este servlet valida que el usuario tenga permiso para entrar a esta opcion.
 * Este servlet implementa la interface GralManejadorRequest
*/
public class VtasControlRepInventariomMPOperacionManejadorRequest
                                                 implements GralManejadorRequest
{

  // Metodo contructor por defecto, es decir, sin parametros
  public VtasControlRepInventariomMPOperacionManejadorRequest () { }

  /**
   * Retorna la URL de la pagina que deberá ser entregada como respuesta
   * (normalmente un pagina jsp).
   */
  public String generaPdf(HttpServletRequest request,
                              HttpServletResponse response)
                throws ServletException,IOException   {

    //
    HttpSession sesion             = request.getSession();
    UsuarioBean usuarioBean        =
                                (UsuarioBean)sesion.getAttribute("usuarioBean");

    //
    String xIdLocalUsuario         = usuarioBean.getIdLocalUsuarioStr();

    //
    FachadaCategoriaBean fachadaCategoriaBean
                                   = new FachadaCategoriaBean();

    //
    fachadaCategoriaBean.setIdLocal(xIdLocalUsuario);

    //
    request.setAttribute("fachadaCategoriaBean",fachadaCategoriaBean);

    //
    return "/jsp/vtaContenedorRepInventarioMPOperacion.jsp";

  }
}
