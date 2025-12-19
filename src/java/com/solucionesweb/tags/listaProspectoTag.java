package com.solucionesweb.tags;

import java.util.*;
import java.io.IOException;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.tagext.TagSupport;

// Importa el Bean de UsuarioRutaBean
import com.solucionesweb.losbeans.negocio.UsuarioRutaBean;

// Importa el Bean de AgendaLogSoporteBean
import com.solucionesweb.losbeans.negocio.AgendaLogSoporteBean;

// Importa el Bean de FachadaAgendaLogSoporteBean
import com.solucionesweb.losbeans.fachada.FachadaAgendaLogSoporteBean;

public class listaProspectoTag extends TagSupport
                                               implements IterationTag {

    // Variable que recibe del JSP
    private String idUsuarioTag;

    //
    public void setIdUsuarioTag( String idUsuarioTag )
    {
        this.idUsuarioTag = idUsuarioTag ;
    }

    public String getIdUsuarioTag()
    {
        return idUsuarioTag;
    }

    //
    FachadaAgendaLogSoporteBean fachadaAgendaLogSoporteBean;

    // Iterador de objetos
    Iterator iteratorBean;

    private void inicializarVariablesDeRetorno() {

      //
      fachadaAgendaLogSoporteBean = (FachadaAgendaLogSoporteBean)iteratorBean.next();

      // Variable que retornan al JSP
      pageContext.setAttribute("idLogVar", fachadaAgendaLogSoporteBean.getIdLogStr());
      pageContext.setAttribute("contactoVar", fachadaAgendaLogSoporteBean.getContacto());
      pageContext.setAttribute("emailVar", fachadaAgendaLogSoporteBean.getEmail());
      pageContext.setAttribute("idClienteVar", fachadaAgendaLogSoporteBean.getIdCliente());
      pageContext.setAttribute("nombreClienteVar", fachadaAgendaLogSoporteBean.getNombreCliente());
      pageContext.setAttribute("telefonoVar", fachadaAgendaLogSoporteBean.getTelefono());

	}

	public int doStartTag() throws JspTagException {

        // estadoProceso
        int estadoProceso = 1;

        //
        UsuarioRutaBean usuarioRutaBean = new UsuarioRutaBean();

        usuarioRutaBean.setIdUsuario(getIdUsuarioTag());

        //
        String idRuta     = usuarioRutaBean.listaRutaUsuario();

        //
        AgendaLogSoporteBean agendaLogSoporteBean = new AgendaLogSoporteBean();

		//
        Vector vectorBean = agendaLogSoporteBean.listaSoporteRuta(idRuta);
        iteratorBean      = vectorBean.iterator();

        if ( !iteratorBean.hasNext() ) {
            try {
              pageContext.getOut().write("La consulta no devolvio ningun dato <br/>");
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