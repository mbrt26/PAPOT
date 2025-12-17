package com.solucionesweb.tags;

import java.util.*;
import java.io.IOException;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.tagext.TagSupport;

// Importa el Bean de Asignaturas
import com.solucionesweb.losbeans.negocio.AgendaLogSoporteBean;

// Importa el Bean de Fachada
import com.solucionesweb.losbeans.fachada.FachadaAgendaLogSoporteBean;

public class ListaContactoTag extends TagSupport
                                    implements IterationTag {

    // Variable que recibe del JSP
    private String idLogTag;

    // Metodos Tag
    public void setIdLogTag( String idLogTag )
    {
        this.idLogTag = idLogTag ;
    }

    public String getIdLogTag()
    {
        return idLogTag;
    }

    // Variable para usar el bean de fachada
    FachadaAgendaLogSoporteBean fachadaAgendaLogSoporteBean;

	// Variable para usar el bean de EstadoPcBean
	AgendaLogSoporteBean agendaLogSoporteBean;

    // Iterador de objetos
    Iterator iteratorBean;

    private void inicializarVariablesDeRetorno() {

      //
      fachadaAgendaLogSoporteBean = (FachadaAgendaLogSoporteBean)iteratorBean.next();

      // Variable que retornan al JSP
      pageContext.setAttribute("idLogVar",fachadaAgendaLogSoporteBean.getIdLogStr());
      pageContext.setAttribute("nombreContactoVar",fachadaAgendaLogSoporteBean.getNombreCliente().toUpperCase());
      pageContext.setAttribute("telefonoContactoVar",fachadaAgendaLogSoporteBean.getTelefono());

	}

	public int doStartTag() throws JspTagException {

      	// Parametros llegados de JSP
        agendaLogSoporteBean  = new AgendaLogSoporteBean();
        agendaLogSoporteBean.setIdLog(getIdLogTag());

        //
		Vector vectorBean = agendaLogSoporteBean.listaUnLog();
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