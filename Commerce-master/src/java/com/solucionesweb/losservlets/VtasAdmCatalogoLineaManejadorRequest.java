package com.solucionesweb.losservlets;

// Importa los paquetes del lenguaje especificamente io y los
// que permiten trabajar el servlet.

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Importa la clase que contiene el Bean de Validacion
import com.solucionesweb.losbeans.utilidades.Validacion;

// Importa la clase que contiene el NombreRubroBean
import com.solucionesweb.losbeans.negocio.LineaBean;

// Importa la clase que contiene el FachadaLineaBean
import com.solucionesweb.losbeans.fachada.FachadaLineaBean;
/**
 * Donde se pueden crear nuevas Líneas o modificar las existentes. Para crear 
 * una nueva Línea se digita el nombre y se presiona Ingresar. /
 * vtaContenedorCatalogoLinea.jsp /
 * 
 *  Este servlet implementa la interface GralManejadorRequest
 */
//
public class VtasAdmCatalogoLineaManejadorRequest
                                                 implements GralManejadorRequest
{
    /**
     * BUTTON--
     * ("Ingresar")-Permite ingresar el nombre de una linea nueva /
     * ("Listar")-permite ver un listado de lineas existentes o una linea especifica /vtaFrmSelCatalogoLinea.jsp /
     * ("Regresar")-("Salir")-Permite retornar al menu principal /
     * ("Modificar")-Permite cambiar el nombre de una linea seleccionada /vtaFrmModCatalogoLinea.jsp /
     * 
     *  Metodo contructor por defecto, es decir, sin parametros/
     */

  public VtasAdmCatalogoLineaManejadorRequest (){ }

  /**
   * BUTTON PARAMETER--
   * "Nombre Linea"-Ingresa nombre de la linea /
   * 
   * Retorna la URL de la pagina que deberá ser entregada como respuesta
   * (normalmente un pagina jsp)./
   */
  public String generaPdf(HttpServletRequest request,
                              HttpServletResponse response)
                              throws ServletException,IOException   {

    String accionContenedor = request.getParameter("accionContenedor");
    System.out.println("accionContenedor :" + accionContenedor );

    // Validacion de accion relacionada con el formulario requerido
    if (accionContenedor != null ) {

	    if (accionContenedor.compareTo("Salir") == 0 ) {
       	    return "/jsp/empty.htm";

        }
        //
             if (accionContenedor.compareTo("Regresar") == 0 ) {
       	    return "/jsp/empty.htm";

        }
        // Listar
	    if (accionContenedor.compareTo("Listar") == 0 ) {

		   String xNombreLinea = request.getParameter("xNombreLinea");


    	   FachadaLineaBean fachadaLineaBean = new FachadaLineaBean();
           fachadaLineaBean.setNombreLinea("'%" +
                                      xNombreLinea.trim().toUpperCase() + "%'");

   		   request.setAttribute("fachadaLineaBean",fachadaLineaBean);
           return "/jsp/vtaFrmSelCatalogoLinea.jsp";
		}

        // Seleccionar
	    if (accionContenedor.compareTo("Seleccionar") == 0 ) {

           String xIdLinea     = request.getParameter("xIdLinea");

           // Valida idLinea
           if (xIdLinea != null ) {

              //
              LineaBean             lineaBean   = new LineaBean();

              FachadaLineaBean fachadaLineaBean = new FachadaLineaBean();

              //
              lineaBean.setIdLinea(xIdLinea);

              //
              fachadaLineaBean                  = lineaBean.listaFCH();

              //
   		      request.setAttribute("fachadaLineaBean",fachadaLineaBean);

              //
              return "/jsp/vtaFrmModCatalogoLinea.jsp";
              
           }
		}

        // Modificar
	    if (accionContenedor.compareTo("Modificar") == 0 ) {

           //
		   String xNombreLinea = request.getParameter("xNombreLinea");
           String xIdLinea     = request.getParameter("xIdLinea");
           int estado          = 1;

           // Valida idLinea
           if (xIdLinea != null ) {

              // validacion
              Validacion validacion = new Validacion();
              validacion.reasignar("nombreLinea", xNombreLinea.trim());

              validacion.validarCampoString();

              // isValido
              if (!validacion.isValido()) {

                 //
 	             request.setAttribute("validacion",validacion);
                 return "/jsp/gralError.jsp";

              }

              //
              LineaBean lineaBean = new LineaBean();

              // Maxima idLinea y idSeq
              int maxIdSeq        = lineaBean.listaIdSeqMaxima() + 1;

              //
              lineaBean.setIdLinea(xIdLinea);
              lineaBean.setNombreLinea(xNombreLinea);
              lineaBean.setEstado(estado);
              lineaBean.setIdSeq(maxIdSeq);

              //
              boolean xOkActualizo =  lineaBean.actualizaLinea();

           }
		}

        // Ingresar
	    if (accionContenedor.compareTo("Ingresar") == 0 ) {

		   String xNombreLinea    = request.getParameter("xNombreLinea");
           int estado             = 1;

           // validacion
           Validacion validacion  = new Validacion();
           validacion.reasignar("nombreLinea", xNombreLinea);

           validacion.validarCampoString();

           // isValido
           if (!validacion.isValido()) {

              //
 	          request.setAttribute("validacion",validacion);
              return "/jsp/gralError.jsp";
           }

           //
           LineaBean lineaBean = new LineaBean();

           // Maxima idLinea y idSeq
           int idLineaMaxima   = lineaBean.listaLineaMaxima() + 1;

           // Maxima idLinea y idSeq
           int maxIdSeq        = lineaBean.listaIdSeqMaxima() + 1;

           // ingresaLinea
           lineaBean.setIdLinea(idLineaMaxima);
           lineaBean.setNombreLinea(xNombreLinea);
           lineaBean.setEstado(estado);
           lineaBean.setIdSeq(maxIdSeq);

           //
           boolean xOkIngreso  =  lineaBean.ingresaLinea();

           // Valida Actualizo
           if (!xOkIngreso) {

 	          request.setAttribute("validacion",validacion);
              return "/jsp/gralError.jsp";
           }
		}
	}

    return "/jsp/vtaContenedorCatalogoLinea.jsp";

  }
}