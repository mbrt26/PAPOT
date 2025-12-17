package com.solucionesweb.tags;

import java.util.*;
import java.io.IOException;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.tagext.TagSupport;

// Importa el Bean de Asignaturas
import com.solucionesweb.losbeans.negocio.AgenteRetencionIvaBean;

// Importa el Bean de Fachada
import com.solucionesweb.losbeans.fachada.FachadaAgenteRetencionIva;

public class ListaAgenteRetencionIvaTag extends TagSupport
                                     implements IterationTag {

    // Variable que recibe del JSP
    private String idRteIvaTag;


    // Metodos Tag
    public void setIdRteIvaTag( String idRteIvaTag )
    {
        this.idRteIvaTag = idRteIvaTag ;
    }

    public String getIdRteIvaTag()
    {
        return idRteIvaTag;
    }

    private int getIdRteIva()
    {
        return  new Integer(getIdRteIvaTag()).intValue();
    }

    // Variable para usar el bean de fachada
    FachadaAgenteRetencionIva fachadaAgenteRetencionIva;

    // Variable para usar el bean de EstadoPcBean
    AgenteRetencionIvaBean agenteRetencionIvaBean;

    // Iterador de objetos
    Iterator iteratorBean;

    private void inicializarVariablesDeRetorno() {

      //
      fachadaAgenteRetencionIva = (FachadaAgenteRetencionIva)iteratorBean.next();

      // Variable que retornan al JSP
      pageContext.setAttribute("idRteIvaVar",
                            fachadaAgenteRetencionIva.getIdRteIvaStr());
      pageContext.setAttribute("nombreRteIvaVar",
                           fachadaAgenteRetencionIva.getNombreRteIva());

	}

	public int doStartTag() throws JspTagException {

      	// Parametros llegados de JSP
        agenteRetencionIvaBean     = new AgenteRetencionIvaBean();

        //
		Vector vectorBean    = agenteRetencionIvaBean.listaUn(
                                                          getIdRteIva());

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