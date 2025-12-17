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

import com.solucionesweb.losbeans.fachada.FachadaMarcaBean;

// Este servlet implementa la interface GralManejadorRequest
public class VtasAdmInventarioMarcaManejadorRequest
                                             implements GralManejadorRequest {

  // Metodo contructor por defecto, es decir, sin parametros
  public VtasAdmInventarioMarcaManejadorRequest (){ }

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
    HttpSession sesion             = request.getSession();
    UsuarioBean usuarioBean        =
                                (UsuarioBean)sesion.getAttribute("usuarioBean");

    //
    String xIdLocalUsuario         = usuarioBean.getIdLocalUsuarioStr();

    // Validacion de accion relacionada con el formulario requerido
    if (accionContenedor != null ) {

	    if (accionContenedor.compareTo("Salir") == 0 ) {
       	    return "/jsp/empty.htm";

        }

        // Listar
	    if (accionContenedor.compareTo("listaMarca") == 0 ) {

           //
		   String xIdMarca          = request.getParameter("xIdMarca");
		   String xIdDestinacion    = request.getParameter("xIdDestinacion");

           //
           int xIdTipo              = 1;
           int xIdBodega            = 1;
           String xTituloReporte    = "INVENTARIO";

           //
           FachadaMarcaBean fachadaMarcaBean
                                    = new FachadaMarcaBean();

           //
           fachadaMarcaBean.setIdMarca(xIdMarca);
           fachadaMarcaBean.setIdLocal(xIdLocalUsuario);
           fachadaMarcaBean.setIdBodega(xIdBodega);

           //
           if (xIdDestinacion.compareTo("Archivo")==0) {

              //
   		      request.setAttribute("fachadaMarcaBean",fachadaMarcaBean);

              //
              response.setContentType("application/vnd.ms-excel");
              response.setHeader("Content-Disposition",
                                             "attachment;filename=archivo.xls");

              //
              return "/jsp/vtaFrmLstInventarioMarca.jsp";

           }

           //
           GeneraPDFInventarioMarca generaPDFInventarioMarca
                                    = new GeneraPDFInventarioMarca();

           //
           generaPDFInventarioMarca.setIdLocal(xIdLocalUsuario);
           generaPDFInventarioMarca.setIdMarca(xIdMarca);
           generaPDFInventarioMarca.setIdTipo(xIdTipo);
           generaPDFInventarioMarca.setTituloReporte(xTituloReporte);
           generaPDFInventarioMarca.setIdBodega(xIdBodega);

           //
           generaPDFInventarioMarca.generaPdf(request, response);

		}
    }

    return "/jsp/empty.htm";

  }
};