package com.solucionesweb.tags;

import java.util.*;
import java.io.IOException;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.tagext.TagSupport;

// Importa la clase que posibilita el formateo
import java.text.NumberFormat;

// Importa el Bean de Asignaturas
import com.solucionesweb.losbeans.colaboraciones.ColaboraAgendaLogVisitaBean;

// Importa el Bean de Fachada
import com.solucionesweb.losbeans.fachada.FachadaColaboraAgendaLogVisitaBean;

public class ListaTotalClienteNuevoTag extends TagSupport
                                           implements IterationTag {

    // Variable que recibe del Tag
    private String idLocalTag;
    private String fechaInicialTag;
    private String fechaFinalTag;

    // Cliente nuevo
    int idEstadovisita = 7;

    NumberFormat nf = NumberFormat.getNumberInstance();

    // Metodos Tag
    public void setIdLocalTag( String idLocalTag )
    {
        this.idLocalTag = idLocalTag ;
    }

    public String getIdLocalTag()
    {
        return idLocalTag;
    }

    public void setFechaInicialTag( String fechaInicialTag )
    {
        this.fechaInicialTag = fechaInicialTag ;
    }

    public String getFechaInicialTag()
    {
        return fechaInicialTag;
    }

    public void setFechaFinalTag( String fechaFinalTag )
    {
        this.fechaFinalTag = fechaFinalTag ;
    }

    public String getFechaFinalTag()
    {
        return fechaFinalTag;
    }

    // Variable para usar el bean de fachada
    FachadaColaboraAgendaLogVisitaBean fachadaColaboraAgendaLogVisitaBean;

	// Variable para usar el bean de EstadoPcBean
	ColaboraAgendaLogVisitaBean colaboraAgendaLogVisitaBean;

    // Iterador de objetos
    Iterator iteratorBean;

    private void inicializarVariablesDeRetorno() {

      //
      fachadaColaboraAgendaLogVisitaBean = (FachadaColaboraAgendaLogVisitaBean)iteratorBean.next();

      // Variable que retornan al JSP
      pageContext.setAttribute("nombreUsuarioVar",fachadaColaboraAgendaLogVisitaBean.getNombreUsuario());
      pageContext.setAttribute("totalNuevoVar",fachadaColaboraAgendaLogVisitaBean.getTotalNuevoStr());

	}

	public int doStartTag() throws JspTagException {

      	// Parametros llegados de JSP
        colaboraAgendaLogVisitaBean  = new ColaboraAgendaLogVisitaBean();

		//  listaTipoOrdenFechaCO
		colaboraAgendaLogVisitaBean.setIdLocal(getIdLocalTag());
		colaboraAgendaLogVisitaBean.setIdEstadoVisita(idEstadovisita);
        colaboraAgendaLogVisitaBean.setFechaInicial(getFechaInicialTag());
        colaboraAgendaLogVisitaBean.setFechaFinal(getFechaFinalTag());
        Vector vectorBean = colaboraAgendaLogVisitaBean.listaTotalClienteNuevoLocal();

        iteratorBean      = vectorBean.iterator();

        if ( !iteratorBean.hasNext() ) {
            try {
              pageContext.getOut().write("No existen datos <br/>");
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

    public void doCatch(Throwable t) throws Throwable {
      System.out.println("Error: " + t);
      pageContext.getOut().println("<font color=\"red\">" +
                                   "Invocando <b>doCatch</b> debido a (" +
                                   t + ")</font>");
    }
    public void doFinally() {
    }

}