package com.solucionesweb.losservlets;

// Importa los paquetes del lenguaje especificamente io y los
// que permiten trabajar el servlet.
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 * Este servlet es la entrada a la logica de un subcontrolador
*/
public class GralPermisosTerminarManejadorRequest
                                                 implements GralManejadorRequest
{

  // Metodo contructor por defecto, es decir, sin parametros
  public GralPermisosTerminarManejadorRequest (){

  }

  /**
   * Retorna la URL de la pagina que deber� ser entregada como respuesta
   * (normalmente un pagina jsp).
   */
  public String generaPdf(HttpServletRequest request,
                              HttpServletResponse response)
                              throws ServletException,IOException  {

   // Obtiene la session actual
   HttpSession session = request.getSession(true);
   session.removeAttribute("usuarioBean");

   return "/jsp/gralTerminar.jsp";
  }
}