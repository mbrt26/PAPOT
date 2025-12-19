package com.solucionesweb.tags;

import java.util.*;
import java.io.IOException;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.tagext.TagSupport;

// Importa el Bean de Asignaturas
import com.solucionesweb.losbeans.colaboraciones.ColaboraInventarioBean;

// Importa el Bean de Fachada
import com.solucionesweb.losbeans.fachada.FachadaInventarioBean;

public class ListaInventarioReferenciaTag extends TagSupport
                                                       implements IterationTag {

    // Variable que recibe del JSP
    private String strIdReferenciaTag;

    // Metodos Tag
    public void setStrIdReferenciaTag( String strIdReferenciaTag )
    {
        this.strIdReferenciaTag = strIdReferenciaTag ;
    }

    public String getStrIdReferenciaTag()
    {
        return "'" + strIdReferenciaTag + "'" ;
    }

    // Variable para usar el bean de fachada
    FachadaInventarioBean fachadaInventarioBean;

	// Variable para usar el bean de ColaboraInventarioBean
	ColaboraInventarioBean colaboraInventarioBean;

    // Iterador de objetos
    Iterator iteratorBean;

    private void inicializarVariablesDeRetorno() {

      //
      fachadaInventarioBean = (FachadaInventarioBean)iteratorBean.next();

      //
      int existencia = (int)fachadaInventarioBean.getExistencia();

      // Variable que retornan al JSP
      pageContext.setAttribute("strIdBodegaVar",fachadaInventarioBean.getStrIdBodega());
      pageContext.setAttribute("nombrePluVar",fachadaInventarioBean.getNombrePlu());
      pageContext.setAttribute("existenciaVar", fachadaInventarioBean.getExistenciaDf1());

	}

	public int doStartTag() throws JspTagException {

      	// Parametros llegados de JSP
        colaboraInventarioBean  = new ColaboraInventarioBean();
        colaboraInventarioBean.setStrIdReferencia(getStrIdReferenciaTag());
		Vector vectorBean       = colaboraInventarioBean.listaAll();

        iteratorBean            = vectorBean.iterator();

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