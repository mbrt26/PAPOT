package com.solucionesweb.tags;

import java.util.*;
import java.io.IOException;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.tagext.TagSupport;

// Importa el Bean de Asignaturas
import com.solucionesweb.losbeans.negocio.LocalBean;

// Importa el Bean de Fachada
import com.solucionesweb.losbeans.fachada.FachadaLocalBean;

public class ListaCentroOtroTag extends TagSupport
                                     implements IterationTag {

    // Variable que recibe del JSP
    private String idLocalTag;


    // Metodos Tag
    public void setIdLocalTag( String idLocalTag )
    {
        this.idLocalTag = idLocalTag ;
    }

    public String getIdLocalTag()
    {
        return idLocalTag;
    }

    // Variable para usar el bean de fachada
    FachadaLocalBean fachadaLocalBean;

	// Variable para usar el bean de EstadoPcBean
	LocalBean localBean;

    // Iterador de objetos
    Iterator iteratorBean;

    private void inicializarVariablesDeRetorno() {


      //
      fachadaLocalBean = (FachadaLocalBean)iteratorBean.next();

      // Variable que retornan al JSP
      pageContext.setAttribute("idLocalVar",fachadaLocalBean.getIdLocalStr());
      pageContext.setAttribute("nombreLocalVar",
                                             fachadaLocalBean.getNombreLocal());

	}

	public int doStartTag() throws JspTagException {

      	// Parametros llegados de JSP
        localBean  = new LocalBean();


        localBean.setIdLocal(getIdLocalTag());

        //
		Vector vectorBean    = localBean.listaAllOtros();

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