package com.solucionesweb.losservlets;

// Importa los paquetes del lenguaje especificamente io y los
// que permiten trabajar el servlet.

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Importa la clase que contiene el Bean de Validacion
import com.solucionesweb.losbeans.utilidades.Validacion;

// Importa la clase que contiene el PluBean
import com.solucionesweb.losbeans.negocio.PluBean;

// Importa la clase que contiene el PluEanBean
import com.solucionesweb.losbeans.negocio.PluEanBean;

// Importa la clase que contiene el PluBean
import com.solucionesweb.losbeans.colaboraciones.ColaboraPlu;

// Importa la clase que contiene el FachadaPluBean
import com.solucionesweb.losbeans.fachada.FachadaPluBean;
/**
 * 
 * Esa opción permite ingresar el código de barras de un producto. /
 * vtaContenedorCatalogoEan.jsp /
 *
 * Este servlet implementa la interface GralManejadorRequest
 */
// 
public class VtasAdmCatalogoEanManejadorRequest
                                                 implements GralManejadorRequest
{
    /**
     * BUTTON--
     * ("Traer")-Permite traer por código de barras un producto /
     * ("Modificar")-Permite cambiar el código de barras de los articulos /
     * ("Regresar")-("Salir")-Permite Retornar al menu principal /
     * 
     * Metodo contructor por defecto, es decir, sin parametros
     */

  public VtasAdmCatalogoEanManejadorRequest (){ }

  /**
   * BUTTON PARAMETER--
   * "Referencia"-codigo de barras de los productos/
   * "ean"- codigo de barras de los productos /
   * 
   * Retorna la URL de la pagina que deberá ser entregada como respuesta
   * (normalmente un pagina jsp).
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

        // Traer
	    if (accionContenedor.compareTo("Traer") == 0 ) {

           String xIdPlu          = request.getParameter("xIdPlu");

           // Valida idLinea
           if (xIdPlu != null ) {

              //
              xIdPlu                            = xIdPlu.trim();

              // validacion
              Validacion validacion = new Validacion();

              //
              validacion.reasignar("CODIGO", xIdPlu );

              validacion.validarCampoEntero();

              // isValido
              if (!validacion.isValido()) {

                 //
 	             request.setAttribute("validacion",validacion);
                 return "/jsp/gralError.jsp";

              }

              //
              ColaboraPlu         colaboraPlu   = new ColaboraPlu();

              //
              colaboraPlu.setIdPlu(xIdPlu);

              //
              FachadaPluBean fachadaPluBean     = new FachadaPluBean();

              //
              fachadaPluBean                    = colaboraPlu.listaUnPluFCH();

              //
              if (fachadaPluBean.getIdLinea()==0) {

                //
                validacion.reasignar("NO EXISTE REFERENCIA",xIdPlu);

                //
                request.setAttribute("validacion",validacion);
                return "/jsp/gralError.jsp";

              }

              //
   		      request.setAttribute("fachadaPluBean",fachadaPluBean);

              //
              return "/jsp/vtaFrmModCatalogoEan.jsp";

           }
		}

        // Modificar
	    if (accionContenedor.compareTo("Modificar") == 0 ) {

           //
		   String xIdPlu            = request.getParameter("xIdPlu");
           String xEan              = request.getParameter("xEan");

           //
           int xEstadoOk            = 1;

           // Valida idLinea
           if (xEan != null ) {

              // validacion
              Validacion validacion = new Validacion();

              //
              validacion.reasignar("CODIGO DE BARRAS", xEan);

              validacion.validarCampoString();

              // isValido
              if (!validacion.isValido()) {

                 //
 	             request.setAttribute("validacion",validacion);
                 return "/jsp/gralError.jsp";

              }

              //
              PluEanBean pluEanBean = new PluEanBean();

              //
              int xMaxIdSeq         = pluEanBean.listaMaximaIdSeq() + 1;

              //
              pluEanBean.setIdPlu(xIdPlu);
              pluEanBean.setEan(xEan.trim());
              pluEanBean.setEstado(xEstadoOk);
              pluEanBean.setIdSeq(xMaxIdSeq);

              //
              boolean xOkEan     =  pluEanBean.retira();

              //
              boolean xOkIngreso =  pluEanBean.ingresa();

           }
		}

        // Modificar
	    if (accionContenedor.compareTo("retira") == 0 ) {

           //
		   String xIdPlu            = request.getParameter("xIdPlu");
           String xEan              = request.getParameter("xEan");

           //
           int xEstadoRetiro        = 0;

           // Valida idLinea
           if (xEan != null ) {

              // validacion
              Validacion validacion = new Validacion();

              //
              validacion.reasignar("CODIGO DE BARRAS", xEan);

              validacion.validarCampoString();

              // isValido
              if (!validacion.isValido()) {

                 //
 	             request.setAttribute("validacion",validacion);
                 return "/jsp/gralError.jsp";

              }

              //
              PluEanBean pluEanBean = new PluEanBean();

              //
              int xMaxIdSeq         = pluEanBean.listaMaximaIdSeq() + 1;

              //
              pluEanBean.setIdPlu(xIdPlu);
              pluEanBean.setEan(xEan.trim());
              pluEanBean.setEstado(xEstadoRetiro);
              pluEanBean.setIdSeq(xMaxIdSeq);

              //
              boolean xOkEan        =  pluEanBean.modifica();


           }
		}

	}

    return "/jsp/vtaContenedorCatalogoEan.jsp";

  }
}