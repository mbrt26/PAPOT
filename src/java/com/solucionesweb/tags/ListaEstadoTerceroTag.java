package com.solucionesweb.tags;

import java.util.*;
import java.io.IOException;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.tagext.TagSupport;

// Importa el Bean de Asignaturas
import com.solucionesweb.losbeans.negocio.EstadoTerceroBean;
// Importa el Bean de Fachada
import com.solucionesweb.losbeans.fachada.FachadaEstadoTerceroBean;

public class ListaEstadoTerceroTag extends TagSupport
                                     implements IterationTag {

    // Variable que recibe del JSP
    private String estadoTag;


    // Metodos Tag
    public void setEstadoTag( String estadoTag )
    {
        this.estadoTag = estadoTag ;
    }

    public String getEstadoTag()
    {
        return estadoTag;
    }

    private int getEstado()
    {
        return  new Integer(getEstadoTag()).intValue();
    }

    // Variable para usar el bean de fachada
    FachadaEstadoTerceroBean fachadaEstadoTerceroBean;

    // Variable para usar el bean de EstadoPcBean
    EstadoTerceroBean estadoTerceroBean;

    // Iterador de objetos
    Iterator iteratorBean;

    private void inicializarVariablesDeRetorno() {

      //
      fachadaEstadoTerceroBean = (FachadaEstadoTerceroBean)iteratorBean.next();

      // Variable que retornan al JSP
      pageContext.setAttribute("estadoVar",
                            fachadaEstadoTerceroBean.getEstadoStr());
      pageContext.setAttribute("nombreEstadoVar",
                           fachadaEstadoTerceroBean.getNombreEstado());

	}

	public int doStartTag() throws JspTagException {

      	// Parametros llegados de JSP
        estadoTerceroBean     = new EstadoTerceroBean();

        //
		Vector vectorBean    = estadoTerceroBean.listaUn(getEstado());

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