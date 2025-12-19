package com.solucionesweb.losservlets;

// Importa los paquetes del lenguaje especificamente io y los
// que permiten trabajar el servlet.
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Este servlet implementa la interface GralManejadorRequest
public class VtasAdmClienteConsultarDatosManejadorRequest implements GralManejadorRequest {

  // Metodo contructor por defecto, es decir, sin parametros
  public VtasAdmClienteConsultarDatosManejadorRequest () { }

  /**
   * Retorna la URL de la pagina que deberá ser entregada como respuesta
   * (normalmente un pagina jsp).
   */
  public String generaPdf(HttpServletRequest request,HttpServletResponse response)
                throws ServletException,IOException  {

    String accionContenedor = request.getParameter("accionContenedor");

    // Validacion de accion relacionada con el formulario requerido
    if (accionContenedor != null ) {

        System.out.println(" accionContenedor " + accionContenedor );

        //
	    if (accionContenedor.compareTo("Salir") == 0 ) {

                //
                return "/jsp/mnuControlClienteBB.jsp";
        }
    }

    return "/jsp/mnuControlClienteBB.jsp";

  }
}