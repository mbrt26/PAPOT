package com.solucionesweb.tags;

import java.util.*;
import java.io.IOException;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.tagext.TagSupport;

// Importa el Bean de Asignaturas
import com.solucionesweb.losbeans.negocio.LocalBodegaBean;

// Importa el Bean de Fachada
import com.solucionesweb.losbeans.fachada.FachadaLocalBodega;

public class ListaUnLocalBodegaTag extends TagSupport
                                     implements IterationTag {

    // Variable que recibe del JSP
    private String idLocalTag;
    private String idBodegaTag;

    // Metodos Tag
    public void setIdLocalTag( String idLocalTag )
    {
        this.idLocalTag = idLocalTag ;
    }

    public String getIdLocalTag()
    {
        return idLocalTag;
    }

    public void setIdBodegaTag( String idBodegaTag )
    {
        this.idBodegaTag = idBodegaTag ;
    }

    public String getIdBodegaTag()
    {
        return idBodegaTag;
    }

    // Variable para usar el bean de fachada
    LocalBodegaBean localBodegaBean;

	// Variable para usar el bean de EstadoPcBean
	FachadaLocalBodega fachadaLocalBodega;

    // Iterador de objetos
    Iterator iteratorBean;

    private void inicializarVariablesDeRetorno() {

      //
      fachadaLocalBodega = (FachadaLocalBodega)iteratorBean.next();

      // Variable que retornan al JSP
      pageContext.setAttribute("idLocalVar",fachadaLocalBodega.getIdLocalStr());
      pageContext.setAttribute("idBodegaVar",
                                           fachadaLocalBodega.getIdBodegaStr());
      pageContext.setAttribute("nombreBodegaVar",
                                          fachadaLocalBodega.getNombreBodega());

	}

	public int doStartTag() throws JspTagException {

      	// Parametros llegados de JSP
        localBodegaBean  = new LocalBodegaBean();


        localBodegaBean.setIdLocal(getIdLocalTag());
        localBodegaBean.setIdBodega(getIdBodegaTag());

        //
		Vector vectorBean    = localBodegaBean.listaUn();

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