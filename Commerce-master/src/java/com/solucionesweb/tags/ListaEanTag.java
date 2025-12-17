package com.solucionesweb.tags;

import java.util.*;
import java.io.IOException;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.tagext.TagSupport;

// Importa el Bean de PluEanBean
import com.solucionesweb.losbeans.negocio.PluEanBean;

// Importa el Bean de Fachada
import com.solucionesweb.losbeans.fachada.FachadaPluEanBean;

public class ListaEanTag extends TagSupport
                                     implements IterationTag {

    // Variable que recibe del JSP
    private String idPluTag;

    // Metodos Tag
    public void setIdPluTag( String idPluTag )
    {
        this.idPluTag = idPluTag ;
    }

    public String getIdPluTag()
    {
        return idPluTag;
    }

    // Variable para usar el bean de fachada
    FachadaPluEanBean fachadaPluEanBean;

	// Variable para usar el bean de EstadoPcBean
	PluEanBean pluEanBean;

    // Iterador de objetos
    Iterator iteratorBean;

    private void inicializarVariablesDeRetorno() {

      //
      fachadaPluEanBean = (FachadaPluEanBean)iteratorBean.next();

      // Variable que retornan al JSP
      pageContext.setAttribute("eanVar",fachadaPluEanBean.getEan());
      pageContext.setAttribute("idPluVar",fachadaPluEanBean.getIdPluSf0());
      pageContext.setAttribute("estadoVar",fachadaPluEanBean.getEstadoStr());
      pageContext.setAttribute("idSeqVar",fachadaPluEanBean.getIdSeqStr());
	}

	public int doStartTag() throws JspTagException {

      	// Parametros llegados de JSP
        pluEanBean            = new PluEanBean();

        //
        pluEanBean.setIdPlu(getIdPluTag());

        //
		Vector vectorBean        = pluEanBean.listaUnEan();

        //
        iteratorBean             = vectorBean.iterator();

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