package com.solucionesweb.tags;

import java.util.*;
import java.io.IOException;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.tagext.TagSupport;

// Importa el Bean de Asignaturas
import com.solucionesweb.losbeans.negocio.TerceroBean;

// Importa el Bean de Fachada
import com.solucionesweb.losbeans.fachada.FachadaTerceroBean;

public class ListaTerceroTag extends TagSupport
                                     implements IterationTag {

    //
    private String idTipoTerceroTag;

    public String getIdTipoTerceroTag() {
        return idTipoTerceroTag;
    }

    public void setIdTipoTerceroTag(String idTipoTerceroTag) {
        this.idTipoTerceroTag = idTipoTerceroTag;
    }

    // Variable para usar el bean de fachada
    FachadaTerceroBean fachadaTerceroBean;

	// Variable para usar el bean de EstadoPcBean
	TerceroBean terceroBean;

    // Iterador de objetos
    Iterator iteratorBean;

    private void inicializarVariablesDeRetorno() {

    //
    fachadaTerceroBean = (FachadaTerceroBean)iteratorBean.next();

    // Variable que retornan al JSP
    pageContext.setAttribute("idTerceroVar",
                                    fachadaTerceroBean.getIdTerceroFormatSf0());
    pageContext.setAttribute("nombreTerceroVar",
                                      fachadaTerceroBean.getNombreTercero());

	}

	public int doStartTag() throws JspTagException {

      	// Parametros llegados de JSP
        terceroBean            = new TerceroBean();

        //
        terceroBean.setIdTipoTercero(getIdTipoTerceroTag());


        //
		Vector vectorBean    = terceroBean.seleccionaTerceroConductorVigente();

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