package com.solucionesweb.tags;

import java.util.*;
import java.io.IOException;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.tagext.TagSupport;

// Importa el Bean de Asignaturas
import com.solucionesweb.losbeans.negocio.CategoriaBean;

// Importa el Bean de Fachada
import com.solucionesweb.losbeans.fachada.FachadaCategoriaBean;

public class ListaUnAllCategoriaTag extends TagSupport
                                     implements IterationTag {


    // Variable para usar el bean de fachada
    FachadaCategoriaBean fachadaCategoriaBean;

	// Variable para usar el bean de EstadoPcBean
	CategoriaBean categoriaBean;

    // Iterador de objetos
    Iterator iteratorBean;

    private void inicializarVariablesDeRetorno() {

      //
      fachadaCategoriaBean = (FachadaCategoriaBean)iteratorBean.next();

      // Variable que retornan al JSP
      pageContext.setAttribute("idLineaVar",
                                          fachadaCategoriaBean.getIdLineaStr());
      pageContext.setAttribute("idCategoriaVar",
                                      fachadaCategoriaBean.getIdCategoriaStr());
      pageContext.setAttribute("nombreCategoriaVar",
                                     fachadaCategoriaBean.getNombreCategoria());
      pageContext.setAttribute("idLineaCategoriaVar",
                                    fachadaCategoriaBean.getIdLineaStr() + "~" +
                                    fachadaCategoriaBean.getIdCategoriaStr());

	}

	public int doStartTag() throws JspTagException {

      	// Parametros llegados de JSP
        categoriaBean            = new CategoriaBean();

        //
		Vector vectorBean    = categoriaBean.listaUnAllCategoria();

        //
        iteratorBean         = vectorBean.iterator();

        if ( !iteratorBean.hasNext() ) {
            try {
              pageContext.getOut().write("La consulta no devolvio ningun dato <br/>");
              pageContext.getOut().write("<br/>");
			  pageContext.getOut().write("Por favor comunique al administrador del sistema que no existen datos <br/>");
		    }
            catch(IOException ex) {
			  throw new JspTagException ("Error: la respuesta no se pudo escribir para los datos");
		    }
		    finally {
		     return SKIP_BODY;
		    }
		}

		// Asigna los valores a las variables que se muestran en jsp
		inicializarVariablesDeRetorno();

		return EVAL_BODY_INCLUDE;
	}

    public int doAfterBody() throws JspTagException {

      if (iteratorBean.hasNext()) {
		  inicializarVariablesDeRetorno();
		  return EVAL_BODY_AGAIN;
	   }

	   return SKIP_BODY;

	}

	public int doEndTag() throws JspTagException
	{
       return EVAL_PAGE;
	}
}