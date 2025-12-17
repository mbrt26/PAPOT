package com.solucionesweb.losservlets;

// Importa los paquetes del lenguaje especificamente io y los
// que permiten trabajar el servlet.
import java.io.*;
import java.util.*;
import java.util.Date;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Importa la clase que contiene el FachadaColaboraHistoriaBean
import com.solucionesweb.losbeans.fachada.FachadaColaboraHistoriaBean;

// Importa la clase que contiene el Validacion
import com.solucionesweb.losbeans.utilidades.Validacion;

// Este servlet implementa la interface GralManejadorRequest
public class VtasAdmCotizacionInventarioManejadorRequest
                                               implements GralManejadorRequest {

  // Metodo contructor por defecto, es decir, sin parametros
  public VtasAdmCotizacionInventarioManejadorRequest () { }

  /**
   * Retorna la URL de la pagina que deber� ser entregada como respuesta
   * (normalmente un pagina jsp).
   */
  public String generaPdf(HttpServletRequest request,
                                                   HttpServletResponse response)
                throws ServletException,IOException  {

    //
    String accionContenedor = request.getParameter("accionContenedor");

    // Validacion de accion relacionada con el formulario requerido
    if (accionContenedor != null ) {

        System.out.println(" accionContenedor " + accionContenedor );

        //
	    if (accionContenedor.compareTo("Salir") == 0 ) {
            return "/jsp/mnuControlTraficoMovilBB.jsp";
        }

        //
	    if (accionContenedor.compareTo("Regresar") == 0 ) {

	        System.out.println("aca estoy");
            return "/jsp/vtaContenedorCotizacionInventario.jsp";
        }

        // Validar
	    if (accionContenedor.compareTo("+Productos") == 0 )  {

	       //
	       String idLinea        = request.getParameter("idLinea");

	       //
           String strCadena = idLinea.trim();
           int    lonCadena = strCadena.length();
           int    posCadena = strCadena.indexOf('+',0);
           String xNombrePlu= "";

           //
           if (posCadena>0) {

              //
              idLinea       = strCadena.substring(0,posCadena).trim();
              xNombrePlu    = strCadena.substring(posCadena+1,lonCadena).trim();
           } else {

              //
              idLinea       = strCadena.substring(0,lonCadena).trim();
              xNombrePlu    = "";
           }

           //
           Validacion validacion = new Validacion();

           //
           validacion.reasignar("idReferencia", idLinea);
           validacion.validarCampoString();

           if (validacion.isValido() == false){

              // Aqui escribe el Bean de Validacion en el Request para manejar el error
              request.setAttribute("validacion",validacion);
              return "/jsp/gralErrPedido.jsp";
           }

           //
           validacion.reasignar("MINIMO 4 CARACTERES",idLinea);
           if (idLinea.trim().length()<4) {

              // Aqui escribe el Bean de Validacion en el Request para manejar el error
              request.setAttribute("validacion",validacion);
              return "/jsp/gralErrPedido.jsp";

           }

           //
           FachadaColaboraHistoriaBean fachadaColaboraHistoriaBean =
                                              new FachadaColaboraHistoriaBean();

           //
      	   fachadaColaboraHistoriaBean.setIdLinea(idLinea);
      	   fachadaColaboraHistoriaBean.setNombrePlu(xNombrePlu);

           //
           request.setAttribute("fachadaColaboraHistoriaBean",
                                                   fachadaColaboraHistoriaBean);
           return "/jsp/vtaFrmSelCotizacionInventario.jsp";

        }
    }
    return "/jsp/mnuControlCotizacionBB.jsp";

  }
}