package com.solucionesweb.tags;

import java.util.*;
import java.io.IOException;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.tagext.TagSupport;

// Importa el Bean de Asignaturas
import com.solucionesweb.losbeans.colaboraciones.ColaboraParametroComisionBean;

// Importa el Bean de Fachada
import com.solucionesweb.losbeans.fachada.FachadaParametroComisionBean;
import static javax.servlet.jsp.tagext.IterationTag.EVAL_BODY_AGAIN;
import static javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE;
import static javax.servlet.jsp.tagext.Tag.EVAL_PAGE;
import static javax.servlet.jsp.tagext.Tag.SKIP_BODY;

public class ListaParametroComisionTag extends TagSupport
                                    implements IterationTag {
   

    // Variable para usar el bean de fachada
    FachadaParametroComisionBean fachadaComision;

	// Variable para usar el bean de EstadoPcBean
	   ColaboraParametroComisionBean colaboraComision;

    // Iterador de objetos
    Iterator iteratorBean;

    private void inicializarVariablesDeRetorno() {

      //
      fachadaComision = (FachadaParametroComisionBean)iteratorBean.next();

      // Variable que retornan al JSP
      pageContext.setAttribute("idLucroVar",fachadaComision.getIdLucroStr());
      pageContext.setAttribute("nombreLucroVar",fachadaComision.getNombreLucro());
      pageContext.setAttribute("diaInicialVar",fachadaComision.getDiaInicialDf0());
      pageContext.setAttribute("diaFinalVar",fachadaComision.getDiaFinalDf0());
     // pageContext.setAttribute("estadoVar",fachadaComision.getEstado());
      pageContext.setAttribute("porcentajeVar",fachadaComision.getPorcentajeStr());

	}

	public int doStartTag() throws JspTagException {

      	// Parametros llegados de JSP
        colaboraComision  = new ColaboraParametroComisionBean();

        //
		Vector vectorBean = colaboraComision.listaDetalleLucro();
        iteratorBean      = vectorBean.iterator();

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