package com.solucionesweb.losservlets;

// Importa los paquetes del lenguaje especificamente io y los
// que permiten trabajar el servlet.

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Importa la clase que contiene el UsuarioBean
import com.solucionesweb.losbeans.negocio.UsuarioBean;

// Este servlet implementa la interface GralManejadorRequest
public class VtasAdmRepVentaIvaTotalManejadorRequest
                                                 implements GralManejadorRequest
{

  // Metodo contructor por defecto, es decir, sin parametros
  public VtasAdmRepVentaIvaTotalManejadorRequest (){ }

  /**
   * Retorna la URL de la pagina que deberá ser entregada como respuesta
   * (normalmente un pagina jsp).
   */
  public String generaPdf(HttpServletRequest request,
                              HttpServletResponse response)
                              throws ServletException,IOException   {

    String accionContenedor = request.getParameter("accionContenedor");
    System.out.println("accionContenedor :" + accionContenedor );

    //
    HttpSession sesion      = request.getSession();
    UsuarioBean usuarioBean = (UsuarioBean)sesion.getAttribute("usuarioBean");

    //
    int xIdLocalUsuario            = usuarioBean.getIdLocalUsuario();

    // Validacion de accion relacionada con el formulario requerido
    if (accionContenedor != null ) {

	    if (accionContenedor.compareTo("Salir") == 0 ) {
       	    return "/jsp/empty.htm";

        }

        // Consultar
	    if (accionContenedor.compareTo("Consultar") == 0 ) {

            //
            String xFechaInicial        = request.getParameter("xFechaInicial");
            String xFechaFinal          = request.getParameter("xFechaFinal");

            //
            GeneraPDFVentaTotalLegal generaPDFVentaTotalLegal
                                        = new GeneraPDFVentaTotalLegal();

            //
            generaPDFVentaTotalLegal.setTituloReporte("REPORTE TOTAL VENTAS");
            generaPDFVentaTotalLegal.setFechaInicial(xFechaInicial);
            generaPDFVentaTotalLegal.setFechaFinal(xFechaFinal);
            generaPDFVentaTotalLegal.setIdLocal(xIdLocalUsuario);

            //
            generaPDFVentaTotalLegal.generaPdf(request, response);


        }

	}

    //
    return "/jsp/empty.htm";

  }
}