package com.solucionesweb.tags;

import java.util.*;
import java.io.IOException;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.tagext.TagSupport;

// Importa el Bean de Asignaturas
import com.solucionesweb.losbeans.negocio.DctoEstadoBean;

// Importa el Bean de Fachada
import com.solucionesweb.losbeans.fachada.FachadaDctoEstadoBean;

public class ListaDctoEstadoActualTag extends TagSupport
                                        implements IterationTag {

    // Variable que recibe del JSP
    private String idEstadoTag;

    // Metodos Tag
    public void setIdEstadoTag( String idEstadoTag )
    {
        this.idEstadoTag = idEstadoTag ;
    }

    public String getIdEstadoTag()
    {
        return idEstadoTag;
    }

    // Variable para usar el bean de fachada
    FachadaDctoEstadoBean fachadaDctoEstadoBean;

	// Variable para usar el bean de EstadoPcBean
	DctoEstadoBean dctoEstadoBean;

    // Iterador de objetos
    Iterator iteratorBean;

    private void inicializarVariablesDeRetorno() {

      //
      fachadaDctoEstadoBean = (FachadaDctoEstadoBean)iteratorBean.next();

      // Variable que retornan al JSP
      pageContext.setAttribute("estadoRegistroVar",fachadaDctoEstadoBean.getEstadoRegistroStr());
      pageContext.setAttribute("nombreEstadoVar",fachadaDctoEstadoBean.getNombreEstado());
      pageContext.setAttribute("estadoVar",fachadaDctoEstadoBean.getEstadoStr());

	}

	public int doStartTag() throws JspTagException {

      	// Parametros llegados de JSP
        dctoEstadoBean  = new DctoEstadoBean();
        dctoEstadoBean.setEstado(getIdEstadoTag());

        //
		Vector vectorBean    = dctoEstadoBean.listaOpcionEstados();
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