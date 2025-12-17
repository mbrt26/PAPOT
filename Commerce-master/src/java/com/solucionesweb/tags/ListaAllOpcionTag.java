package com.solucionesweb.tags;

import java.util.*;
import java.io.IOException;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.tagext.TagSupport;

// Importa el Bean de Asignaturas
import com.solucionesweb.losbeans.negocio.TipoOrdenBean;

// Importa el Bean de Fachada
import com.solucionesweb.losbeans.fachada.FachadaTipoOrden;

public class ListaAllOpcionTag extends TagSupport
                                     implements IterationTag {

    // Variable que recibe del JSP
    private String idTipoOrdenOpcionTag;

    // Metodos Tag
    public void setIdTipoOrdenOpcionTag( String idTipoOrdenOpcionTag )
    {
        this.idTipoOrdenOpcionTag = idTipoOrdenOpcionTag ;
    }

    public String getIdTipoOrdenOpcionTag()
    {
        return idTipoOrdenOpcionTag;
    }

    // Variable para usar el bean de fachada
    TipoOrdenBean tipoOrdenBean;

	// Variable para usar el bean de EstadoPcBean
	FachadaTipoOrden fachadaTipoOrden;

    // Iterador de objetos
    Iterator iteratorBean;

    private void inicializarVariablesDeRetorno() {

      //
      fachadaTipoOrden = (FachadaTipoOrden)iteratorBean.next();

      // Variable que retornan al JSP
      pageContext.setAttribute("idTipoOrdenVar",
                                          fachadaTipoOrden.getIdTipoOrdenStr());
      pageContext.setAttribute("nombreTipoOrdenVar",
                                fachadaTipoOrden.getNombreTipoOrdenMayuscula());
      pageContext.setAttribute("signoVar",
                                          fachadaTipoOrden.getSignoStr());

	}

	public int doStartTag() throws JspTagException {

      	// Parametros llegados de JSP
        tipoOrdenBean  = new TipoOrdenBean();

        //
        Vector vectorBean    = tipoOrdenBean.listaAllOpcion(getIdTipoOrdenOpcionTag());

        //
        iteratorBean         = vectorBean.iterator();

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