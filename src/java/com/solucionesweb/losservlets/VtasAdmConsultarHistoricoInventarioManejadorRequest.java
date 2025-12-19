package com.solucionesweb.losservlets;

// Importa los paquetes del lenguaje especificamente io y los
// que permiten trabajar el servlet.
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Importa la clase que contiene el FachadaInventarioBean
import com.solucionesweb.losbeans.fachada.FachadaInventarioBean;

// Este servlet implementa la interface GralManejadorRequest
public class VtasAdmConsultarHistoricoInventarioManejadorRequest implements GralManejadorRequest {

  // Metodo contructor por defecto, es decir, sin parametros
  public VtasAdmConsultarHistoricoInventarioManejadorRequest () { }

  /**
   * Retorna la URL de la pagina que deber� ser entregada como respuesta
   * (normalmente un pagina jsp).
   */
  public String generaPdf(HttpServletRequest request,HttpServletResponse response)
                throws ServletException,IOException  {

    String accionContenedor = request.getParameter("accionContenedor");

    // Validacion de accion relacionada con el formulario requerido
    if (accionContenedor != null ) {

        //
	    if (accionContenedor.compareTo("SeleccionarReferencia") == 0 ) {

	        String strIdReferencia = request.getParameter("strIdReferencia");
	        FachadaInventarioBean fachadaInventarioBean = new FachadaInventarioBean();
	        fachadaInventarioBean.setStrIdReferencia(strIdReferencia.trim());

            // Aqui escribe el Bean de Validacion en el Request para manejar el error
            request.setAttribute("fachadaInventarioBean",fachadaInventarioBean);
            return "/jsp/vtaFrmLstClienteConsultarHistoricoInventario.jsp";

        }

        //
	    if (accionContenedor.compareTo("Regresar") == 0 ) {

	        return "/jsp/mnuControlClienteBB.jsp";

        }

    }

    return "/jsp/mnuControlClienteBB.jsp";

  }
}