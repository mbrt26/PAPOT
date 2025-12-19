package com.solucionesweb.losservlets;

// Importa los paquetes del lenguaje especificamente io y los
// que permiten trabajar el servlet.

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Importa la clase que contiene el CategoriaBean
import com.solucionesweb.losbeans.negocio.CategoriaBean;

// Importa la clase que contiene el UsuarioBean
import com.solucionesweb.losbeans.negocio.UsuarioBean;

// Importa la clase que contiene el FachadaCategoriaBean
import com.solucionesweb.losbeans.fachada.FachadaCategoriaBean;

import com.solucionesweb.losbeans.fachada.FachadaColaboraReporteDctoBean;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaLocalBodega;

//Validacion
import com.solucionesweb.losbeans.utilidades.Validacion;

// Importa la clase que contiene el LocalBodegaBean
import com.solucionesweb.losbeans.negocio.LocalBodegaBean;
/**
 * En el que se muestra los movimientos del plu a partir de la fecha inicial 
 * seleccionada, si es después de un inventario sino a partir del último inventario. /
 * vtaContenedorInventarioKardex.jsp /
 * 
 * Este servlet implementa la interface GralManejadorRequest /
 */
// 
public class VtasAdmInventarioKardexManejadorRequest
                                               implements GralManejadorRequest {
    /**
     * BUTTON--
     * ("Consultar")-Permite ver un reporte de kardex /
     * ("Salir")-Retorna al menu principal /
     * 
     *  Metodo contructor por defecto, es decir, sin parametros /
     */

  public VtasAdmInventarioKardexManejadorRequest (){ }

  /**
     * BUTTON PARAMETER--
     * "Categoria"- Selecciona una categoria /
     * "Bodega Interna"-Selecciona bodega de la consulta /
     * "Fecha inicial"-Fecha inicial para ver un reporte /
     * "Fecha Final"-Fecha limite para ver un reporte /
     * 
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
	    if (accionContenedor.compareTo("Consultar") == 0 ) {

           //
		   String xIdLocal          = request.getParameter("xIdLocal");
		   String xIdLineaCategoria = request.getParameter("xIdLineaCategoria");
		   String xFechaInicial     = request.getParameter("xFechaInicial");           
		   String xFechaFinal       = request.getParameter("xFechaFinal");
		   String xIdBodega         = request.getParameter("xIdBodega");

           //
           int indiceSeparador      = xIdLineaCategoria.indexOf("~");
           int Longitud             = xIdLineaCategoria.length();
  	       String xIdLinea          = xIdLineaCategoria.substring(0,
                                                               indiceSeparador);
    	   String xIdCategoria      = xIdLineaCategoria.substring(
                                                    indiceSeparador+1,Longitud);

           //
           CategoriaBean  categoriaBean
                                    = new CategoriaBean();


            //
             Validacion validacion = new Validacion();

                //
                validacion.reasignar("FECHA INICIAL", xFechaInicial);

                //
                validacion.validarCampoFecha();

                // isValido
                if (!validacion.isValido()) {

                    //
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralError.jsp";
                }

                //
                validacion.reasignar("FECHA FINAL", xFechaFinal);

                //
                validacion.validarCampoFecha();

                // isValido
                if (!validacion.isValido()) {

                    //
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralError.jsp";
                }

                //
                validacion.validarRangoFecha(xFechaInicial, xFechaFinal);

                // isValido
                if (!validacion.isValido()) {

                    //
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralError.jsp";
                }


                //
           FachadaCategoriaBean fachadaCategoriaBean
                                    = new FachadaCategoriaBean();

           //
           categoriaBean.setIdLinea(xIdLinea);
           categoriaBean.setIdCategoria(xIdCategoria);

           //
           fachadaCategoriaBean     = categoriaBean.listaUnaCategoriaFCH();

           //
           request.setAttribute("fachadaCategoriaBean",fachadaCategoriaBean);



           //
           FachadaColaboraReporteDctoBean  fachadaColaboraReporteDctoBean
                                         = new FachadaColaboraReporteDctoBean();

           //
           int xIdTipo              = 1;

           //
           fachadaColaboraReporteDctoBean.setFechaInicial(xFechaInicial);
           fachadaColaboraReporteDctoBean.setFechaFinal(xFechaFinal);
           fachadaColaboraReporteDctoBean.setIdLocal(xIdLocal);
           fachadaColaboraReporteDctoBean.setIdBodega(xIdBodega);           
           fachadaColaboraReporteDctoBean.setIdTipo(xIdTipo);

           //
           request.setAttribute("fachadaColaboraReporteDctoBean",
                                                fachadaColaboraReporteDctoBean);

           //
           return "/jsp/vtaFrmSelInventarioKardexReferencia.jsp";

		}

        // Listar
	    if (accionContenedor.compareTo("traeReferencia") == 0 ) {

           //
		   String xIdPlu            = request.getParameter("xIdPlu");
		   String xIdLocal          = request.getParameter("xIdLocal");
           String xFechaInicial     = request.getParameter("xFechaInicial");
		   String xFechaFinal       = request.getParameter("xFechaFinal");
           String xIdTipo           = request.getParameter("xIdTipo");
           String xIdBodega         = request.getParameter("xIdBodega");

           //
           FachadaLocalBodega fachadaLocalBodega
                                    = new FachadaLocalBodega();

           //
           LocalBodegaBean localBodegaBean
                                    = new LocalBodegaBean();

           //
           localBodegaBean.setIdLocal(xIdLocal);
           localBodegaBean.setIdBodega(xIdBodega);

           //
           fachadaLocalBodega       = localBodegaBean.listaUnFCH();

           //
           String xTituloReporte    = "KARDEX DEL "  +
                                       xFechaInicial +
                                       " AL "        +
                                       xFechaFinal   +
                                       " - BODEGA "   +
                                       fachadaLocalBodega.getNombreBodega() +
                                       "(" +
                                       fachadaLocalBodega.getIdBodegaStr() +
                                       ")" ;

           //
           GeneraPDFInventarioKardex generaPDFInventarioKardex
                                    = new GeneraPDFInventarioKardex();

           //
           generaPDFInventarioKardex.setIdLocal(xIdLocal);
           generaPDFInventarioKardex.setIdPlu(xIdPlu);
           generaPDFInventarioKardex.setTituloReporte(xTituloReporte);
           generaPDFInventarioKardex.setFechaInicial(xFechaInicial);
           generaPDFInventarioKardex.setFechaFinal(xFechaFinal);
           generaPDFInventarioKardex.setIdBodega(xIdBodega);

           //
           generaPDFInventarioKardex.generaPdf(request, response);

		}
    }

    return "/jsp/empty.htm";

  }
}