package com.solucionesweb.tags;

import java.util.*;
import java.io.IOException;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.tagext.TagSupport;

// Importa el Bean de Asignaturas
import com.solucionesweb.losbeans.negocio.JobCausaBean;

// Importa el Bean de Fachada
import com.solucionesweb.losbeans.fachada.FachadaJobCausa;

public class ListaJobCausaTag extends TagSupport
                                     implements IterationTag {

    // Variable que recibe del JSP
    private String idTipoCausaTag;

    // Metodos Tag
    public void setIdTipoCausaTag( String idTipoCausaTag )
    {
        this.idTipoCausaTag = idTipoCausaTag ;
    }

    public String getIdTipoCausaTag()
    {
        return idTipoCausaTag;
    }

    // Variable para usar el bean de fachada
    FachadaJobCausa fachadaJobCausa;

	// Variable para usar el bean de EstadoPcBean
    JobCausaBean jobCausaBean;

    // Iterador de objetos
    Iterator iteratorBean;

    private void inicializarVariablesDeRetorno() {

      //
      fachadaJobCausa = (FachadaJobCausa)iteratorBean.next();

      // Variable que retornan al JSP
      pageContext.setAttribute("idTipoCausaVar",
                                           fachadaJobCausa.getIdTipoCausaStr());
      pageContext.setAttribute("idCausaVar",
                                           fachadaJobCausa.getIdCausaStr());
      pageContext.setAttribute("nombreCausaVar",
                                              fachadaJobCausa.getNombreCausa());
      pageContext.setAttribute("idCausaFormatoVar",
                                          fachadaJobCausa.getIdCausaFormato());

	}

	public int doStartTag() throws JspTagException {

      	// Parametros llegados de JSP
        jobCausaBean          = new JobCausaBean();

        //
        jobCausaBean.setIdTipoCausa(getIdTipoCausaTag());

        //
        Vector vectorBean    = jobCausaBean.listaAllOpcion();

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