package com.solucionesweb.tags;

import java.util.*;
import java.io.IOException;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.tagext.TagSupport;

// Importa el Bean de Asignaturas
import com.solucionesweb.losbeans.negocio.LineaBean;

// Importa el Bean de Fachada
import com.solucionesweb.losbeans.fachada.FachadaLineaBean;

public class ListaLineasTodasTag extends TagSupport
                                    implements IterationTag
{

    // Variable para usar el bean de fachada
    FachadaLineaBean fachadaLineaBean;

	// Variable para usar el bean de area
	LineaBean lineaBean;

    // Iterador de objetos
    Iterator iteratorLineaBean;

    private void inicializarVariablesDeRetorno() {

      fachadaLineaBean = (FachadaLineaBean)iteratorLineaBean.next();

      //
      pageContext.setAttribute("idLineaVar",fachadaLineaBean.getIdLineaStr());
      pageContext.setAttribute("nombreLineaVar",fachadaLineaBean.getNombreLinea());
      pageContext.setAttribute("estadoVar",fachadaLineaBean.getEstadoStr());

	}

	public int doStartTag() throws JspTagException
	{
		// Efectua la busqueda de los grados que estan matriculados

		lineaBean = new LineaBean();
		Vector listaLineaVector = lineaBean.seleccionaUnaLinea();
        iteratorLineaBean       = listaLineaVector.iterator();

        if ( !iteratorLineaBean.hasNext() ) {
            try {
              pageContext.getOut().write("La consulta no devolvio ningun dato <br/>");
              pageContext.getOut().write("<br/>");
			  pageContext.getOut().write("Por favor comunique al administrador del sistema que no existen grados <br/>");
		    }
            catch(IOException ex) {
			  throw new JspTagException ("Error: la respuesta no se pudo escribir para los grados");
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

      if (iteratorLineaBean.hasNext()) {
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