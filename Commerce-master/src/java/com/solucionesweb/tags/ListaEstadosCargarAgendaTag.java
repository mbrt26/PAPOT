package com.solucionesweb.tags;

import java.util.*;
import java.io.IOException;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.tagext.TagSupport;

// Importa el Bean de Asignaturas
import com.solucionesweb.losbeans.negocio.AgendaControlEstadoBean;

// Importa el Bean de Fachada
import com.solucionesweb.losbeans.fachada.FachadaAgendaControlEstadoBean;

public class ListaEstadosCargarAgendaTag extends TagSupport
                                     implements IterationTag {

    // Propiedades
    private String estadoRegistroVar;
    private String nombreEstadoVar;
    private String estadoVar;

    // Metodos Var
    public void setEstadoRegistroVar( String estadoRegistroVar )
    {
        this.estadoRegistroVar = estadoRegistroVar ;
    }

    public String getEstadoRegistroVar()
    {
        return estadoRegistroVar;
    }

    public void setNombreEstadoVar( String nombreEstadoVar )
    {
        this.nombreEstadoVar = nombreEstadoVar ;
    }

    public String getNombreEstadoVar()
    {
        return nombreEstadoVar;
    }

    public String getEstadoVar()
    {
        return estadoVar;
    }

    public void setEstadoVar( String estadoVar )
    {
        this.estadoVar = estadoVar ;
    }

    // Variable para usar el bean de fachada
    FachadaAgendaControlEstadoBean fachadaAgendaBean;

	// Variable para usar el bean de EstadoPcBean
	AgendaControlEstadoBean agendaBean;

    // Iterador de objetos
    Iterator iteratorBean;

    private void inicializarVariablesDeRetorno() {

      //
      fachadaAgendaBean = (FachadaAgendaControlEstadoBean)iteratorBean.next();

      // Variable que retornan al JSP
      pageContext.setAttribute("estadoRegistroVar",fachadaAgendaBean.getEstadoRegistroStr());
      pageContext.setAttribute("nombreEstadoVar",fachadaAgendaBean.getNombreEstado());
      pageContext.setAttribute("estadoVar",fachadaAgendaBean.getEstadoStr());

	}

	public int doStartTag() throws JspTagException {

      	// Parametros llegados de JSP
        agendaBean  = new AgendaControlEstadoBean();

		Vector vectorBean    = agendaBean.listaEstados();
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