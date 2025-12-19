package com.solucionesweb.losservlets;

//
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Importa la clase que contiene el Bean de Validacion
import com.solucionesweb.losbeans.utilidades.Validacion;

// Importa la clase que contiene el CategoriaBean
import com.solucionesweb.losbeans.negocio.CategoriaBean;

// Importa la clase que contiene el LineaBean
import com.solucionesweb.losbeans.negocio.LineaBean;

// Importa la clase que contiene el FachadaLineaBean
import com.solucionesweb.losbeans.fachada.FachadaCategoriaBean;

// Importa la clase que contiene el FachadaLineaBean
import com.solucionesweb.losbeans.fachada.FachadaLineaBean;
/**
 * Donde pueden crear nuevas Categorías o modificar las existentes./
 * vtaContenedorCatalogoCategoria.jsp /
 *  Este servlet implementa la interface GralManejadorRequest/
 */
//
public class VtasAdmCatalogoCategoriaManejadorRequest 
                                               implements GralManejadorRequest {
    /**
     * BUTTON--
     * ("Regresar")-("Salir")-Retorna al menu pricipal /
     * ("Ingresar")-Permite ingresar el nombre de una nueva categoria en una linea seleccionada /vtaFrmSelCatalogoCategoria/
     * ("Modificar")-Permite cambiar el nombre de la categoria seleccionada /vtaFrmModCatalogoCategoria.jsp/
     * 
     * Metodo contructor por defecto, es decir, sin parametros /
     */

  public VtasAdmCatalogoCategoriaManejadorRequest (){ }

  /**
   * BUTTON PARAMETER--
   * "Nombre Linea"-Permite seleccionar lineas /
   * "Nombre categoria"-ingresa nombre categoria nueva /
   * "Modifica Categoria"-Cambia o actualiza el nombre del categoria seleccionada/
   * 
   * Retorna la URL de la pagina que deberá ser entregada como respuesta
   * (normalmente un pagina jsp)./
   */
  public String generaPdf(HttpServletRequest request,
                              HttpServletResponse response)
                                         throws ServletException,IOException {

    //
    String accionContenedor = request.getParameter("accionContenedor");

    //
    System.out.println("accionContenedor :" + accionContenedor );

    // Validacion de accion relacionada con el formulario requerido
    if (accionContenedor != null ) {

	    if (accionContenedor.compareTo("Salir") == 0 ) {
       	    return "/jsp/empty.htm";

        }

            if (accionContenedor.compareTo("Regresar") == 0 ) {
       	    return "/jsp/empty.htm";

        }

	    if (accionContenedor.compareTo("Listar") == 0 ) {

           //
		   String xIdLinea = request.getParameter("xIdLinea");

           //
           LineaBean      lineaBean           = new LineaBean();

           //
           FachadaLineaBean  fachadaLineaBean = new FachadaLineaBean();

           //
           lineaBean.setIdLinea(xIdLinea);

           //
           fachadaLineaBean                   = lineaBean.listaFCH();

           //
           request.setAttribute("fachadaLineaBean",fachadaLineaBean);
           return "/jsp/vtaFrmSelCatalogoCategoria.jsp";

		}

        // Seleccionar
	    if (accionContenedor.compareTo("Seleccionar") == 0 ) {

           //
           String xIdLinea          = request.getParameter("xIdLinea");
           String xIdCategoria      = request.getParameter("xIdCategoria");

           // Valida radIdLineaIdCategoria
           if (xIdCategoria != null ) {

              //
              LineaBean      lineaBean           = new LineaBean();

              //
              FachadaLineaBean  fachadaLineaBean = new FachadaLineaBean();

              //
              lineaBean.setIdLinea(xIdLinea);

              //
              fachadaLineaBean                   = lineaBean.listaFCH();

              //
        	  FachadaCategoriaBean fachadaCategoriaBean
                                                 = new FachadaCategoriaBean();

              //
              CategoriaBean categoriaBean        = new CategoriaBean();

              //
              categoriaBean.setIdLinea(xIdLinea);
              categoriaBean.setIdCategoria(xIdCategoria);

              fachadaCategoriaBean               =
                                           categoriaBean.listaUnaCategoriaFCH();

   		      request.setAttribute("fachadaCategoriaBean",fachadaCategoriaBean);
   		      request.setAttribute("fachadaLineaBean",fachadaLineaBean);
              return "/jsp/vtaFrmModCatalogoCategoria.jsp";

           }
		}

        // Modificar
	    if (accionContenedor.compareTo("Modificar") == 0 ) {

           //
		   String xNombreCategoria = request.getParameter("xNombreCategoria");
           String xIdLinea         = request.getParameter("xIdLinea");
           String xIdCategoria     = request.getParameter("xIdCategoria");

           int estado             = 1;

           // Valida idLinea
           if ( (xIdLinea != null ) && (xIdCategoria != null ) ) {

              // validacion
              Validacion validacion = new Validacion();
              validacion.reasignar("NombreCategoria", xNombreCategoria.trim());

              validacion.validarCampoString();

              // isValido
              if (!validacion.isValido()) {

                 //
 	             request.setAttribute("validacion",validacion);
                 return "/jsp/gralError.jsp";
              }

              //
              CategoriaBean categoriaBean = new CategoriaBean();

              //
              int idSeqMaxima       = categoriaBean.listaIdSeqMaxima()   + 1;

              //
              categoriaBean.setIdLinea(xIdLinea);
              categoriaBean.setIdCategoria(xIdCategoria);
              categoriaBean.setNombreCategoria(xNombreCategoria);
              categoriaBean.setEstado(estado);
              categoriaBean.setIdSeq(idSeqMaxima);

              //
              boolean xOkActualizo =  categoriaBean.actualizaCategoria();

              //
              LineaBean      lineaBean           = new LineaBean();

              //
              FachadaLineaBean  fachadaLineaBean = new FachadaLineaBean();

              //
              lineaBean.setIdLinea(xIdLinea);

              //
              fachadaLineaBean                   = lineaBean.listaFCH();

              //
              request.setAttribute("fachadaLineaBean",fachadaLineaBean);
              return "/jsp/vtaFrmSelCatalogoCategoria.jsp";

           }
		}

        // Ingresar
	    if (accionContenedor.compareTo("Ingresar") == 0 ) {

           //
		   String xNombreCategoria = request.getParameter("xNombreCategoria");
           String xIdLinea         = request.getParameter("xIdLinea");
           int estado              = 1;

           // validacion
           Validacion validacion  = new Validacion();

           //
           validacion.reasignar("xIdLinea", xIdLinea);
           validacion.validarCampoEntero();

           // isValido
           if (!validacion.isValido()) {

              //
 	          request.setAttribute("validacion",validacion);
              return "/jsp/gralError.jsp";
           }

           //
           validacion.reasignar("nombreCategoria", xNombreCategoria);
           validacion.validarCampoString();

           // isValido
           if (!validacion.isValido()) {

              //
 	          request.setAttribute("validacion",validacion);
              return "/jsp/gralError.jsp";
           }

           //
           CategoriaBean categoriaBean = new CategoriaBean();

           // Maxima idCategoria y idSeq
           categoriaBean.setIdLinea(xIdLinea);

           //
           int idCategoriaMaxima = categoriaBean.listaIdCategoriaMaxima() + 1;

           //
           int idSeqMaxima       = categoriaBean.listaIdSeqMaxima()   + 1;

           // ingresaLinea
           categoriaBean.setIdLinea(xIdLinea);
           categoriaBean.setIdCategoria(idCategoriaMaxima);
           categoriaBean.setNombreCategoria(xNombreCategoria);
           categoriaBean.setIdSeq(idSeqMaxima);
           categoriaBean.setEstado(estado);

           //
           boolean xOkIngreso  =  categoriaBean.ingresa();

           //--- actualizaBodegaMP
           int xIdBodega     = 999;
           xIdLinea          = "2";
           int xIdCategoria  = 9;

           //
           categoriaBean.setIdBodega(xIdBodega);
           categoriaBean.setIdLinea(xIdLinea);
           categoriaBean.setIdCategoria(xIdCategoria);

           //---
           categoriaBean.actualizaBodegaMP();

           //
           LineaBean      lineaBean           = new LineaBean();

           //
           FachadaLineaBean  fachadaLineaBean = new FachadaLineaBean();

           //
           lineaBean.setIdLinea(xIdLinea);

           //
           fachadaLineaBean                   = lineaBean.listaFCH();

           //
           request.setAttribute("fachadaLineaBean",fachadaLineaBean);
           return "/jsp/vtaFrmSelCatalogoCategoria.jsp";           
		}
	}

    return "/jsp/vtaContenedorCatalogoCategoria.jsp";

  }
}