package com.solucionesweb.tags;

import java.util.*;
import java.io.IOException;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.tagext.TagSupport;

// Importa el Bean de Asignaturas
import com.solucionesweb.losbeans.fachada.FachadaCategoriaBean;

// Importa el Bean de Asignaturas
import com.solucionesweb.losbeans.negocio.CategoriaBean;

public class ListaUnCategoriaTag extends TagSupport
                                     implements IterationTag {

    // Variable que recibe del JSP
    private String idLineaTag;

    // Metodos Tag
    public void setIdLineaTag( String idLineaTag )
    {
        this.idLineaTag = idLineaTag ;
    }

    public String getIdLineaTag()
    {
        return idLineaTag;
    }

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
      pageContext.setAttribute("estadoVar",
                                           fachadaCategoriaBean.getEstadoStr());
	}

	public int doStartTag() throws JspTagException {

      	// Parametros llegados de JSP
        categoriaBean                   = new CategoriaBean();

        //
        categoriaBean.setIdLinea(getIdLineaTag());

        //
		Vector vectorBean               =
                                        categoriaBean.listaCategoriasxLineas();

        //
        iteratorBean                    = vectorBean.iterator();

        if ( !iteratorBean.hasNext() ) {
            try {
              pageContext.getOut().write("<br>No existen datos");
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