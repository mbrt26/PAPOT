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

public class ListaClientePendientesTag extends TagSupport
                                     implements IterationTag {

    // Variable que recibe del JSP
    private String idUsuarioTag;

    // Metodos Tag
    public void setIdUsuarioTag( String idUsuarioTag )
    {
        this.idUsuarioTag = idUsuarioTag ;
    }

    public String getIdUsuarioTag()
    {
        return idUsuarioTag;
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
      pageContext.setAttribute("idClienteVar",fachadaAgendaLogSoporteBean.getIdCliente());
      pageContext.setAttribute("nombreClienteVar",fachadaAgendaLogSoporteBean.getNombreCliente());
      pageContext.setAttribute("telefonoVar",fachadaAgendaLogSoporteBean.getTelefono());
      pageContext.setAttribute("estadoVar",fachadaAgendaLogSoporteBean.getEstadoStr());
      pageContext.setAttribute("emailVar",fachadaAgendaLogSoporteBean.getEmail());
      pageContext.setAttribute("contactoVar",fachadaAgendaLogSoporteBean.getContacto());
      pageContext.setAttribute("idEstadoTxVar",fachadaAgendaLogSoporteBean.getIdEstadoTxStr());

	}

	public int doStartTag() throws JspTagException {

        //
        int idEstadoTxPendiente   = 1;

      	// Parametros llegados de JSP
        agendaLogSoporteBean  = new AgendaLogSoporteBean();

        //
        agendaLogSoporteBean.setIdEstadoTx(idEstadoTxPendiente);

        //
		Vector vectorBean    =
           agendaLogSoporteBean.listaPendientes(
                                         Double.parseDouble(getIdUsuarioTag()));

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