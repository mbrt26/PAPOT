package com.solucionesweb.losservlets;

// Importa los paquetes del lenguaje especificamente
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Este servlet valida que el usuario tenga permiso para entrar a esta opcion.
 * Este servlet implementa la interface GralManejadorRequest
*/
public class VtasControlAgendaConsultarManejadorRequest implements GralManejadorRequest
{

  // Metodo contructor por defecto, es decir, sin parametros
  public VtasControlAgendaConsultarManejadorRequest () { }

  /**
   * Retorna la URL de la pagina que deber� ser entregada como respuesta
   * (normalmente un pagina jsp).
   */
  public String generaPdf(HttpServletRequest request,HttpServletResponse response)
                throws ServletException,IOException
  {
    return "/jsp/vtaContenedorAgendaConsultar.jsp";
  }
}