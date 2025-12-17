package com.solucionesweb.losservlets;

// Importa los paquetes del lenguaje especificamente io y los
// que permiten trabajar el servlet.
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Importa la clase que contiene el TerceroInactivoBean
import com.solucionesweb.losbeans.negocio.TerceroInactivoBean;

// Este servlet implementa la interface GralManejadorRequest
public class VtasAdmClienteNuevoActivarManejadorRequest
                                               implements GralManejadorRequest {

  // Metodo contructor por defecto, es decir, sin parametros
  public VtasAdmClienteNuevoActivarManejadorRequest () { }

  /**
   * Retorna la URL de la pagina que deber� ser entregada como respuesta
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
            return "/mnuControlClienteNuevoBB.jsp";
        }

        // Confirmar
	    if (accionContenedor.compareTo("Confirmar") == 0 )  {

           //
	       String arrIdCliente[]   = request.getParameterValues("chkIdCliente");
	       String fechaVisita      = request.getParameter("fechaVisita");
	       String xIdUsuario       = request.getParameter("xIdUsuario");

           if (arrIdCliente != null) {

              //
              TerceroInactivoBean terceroInactivoBean = new TerceroInactivoBean();

              //
              for(int contador=0;contador<arrIdCliente.length;contador++) {

                  //
                  terceroInactivoBean.setIdCliente(arrIdCliente[contador]);
                  terceroInactivoBean.setIdUsuario(xIdUsuario);
                  terceroInactivoBean.setFechaInactivo(fechaVisita);
                  terceroInactivoBean.setEstado(1);

                  //
                  terceroInactivoBean.retira();

              }

           }

           return "/jsp/mnuControlClienteNuevoBB.jsp";

        }
    }

    return "/jsp/mnuControlClienteNuevoBB.jsp";

  }
}