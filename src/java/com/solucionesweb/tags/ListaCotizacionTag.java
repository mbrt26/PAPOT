package com.solucionesweb.tags;

import java.util.*;
import java.io.IOException;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.tagext.TagSupport;

// Importa el Bean de Asignaturas
import com.solucionesweb.losbeans.colaboraciones.ColaboraAgendaLogVisitaBean;

// Importa el Bean de Fachada
import com.solucionesweb.losbeans.fachada.FachadaColaboraAgendaLogVisitaBean;

public class ListaCotizacionTag extends TagSupport
                                     implements IterationTag {

    // Variable que recibe del JSP
    private String idLocalTag;
    private String idTipoOrdenTag;
    private String fechaVisitaTag;
    private String estadoTag;

    // Metodos Tag
    public void setIdTipoOrdenTag( String idTipoOrdenTag )
    {
        this.idTipoOrdenTag = idTipoOrdenTag ;
    }

    public String getIdTipoOrdenTag()
    {
        return idTipoOrdenTag;
    }

    public int getIdTipoOrden()
    {
        return new Integer(getIdTipoOrdenTag());
    }

    public void setIdLocalTag( String idLocalTag )
    {
        this.idLocalTag = idLocalTag ;
    }

    public String getIdLocalTag()
    {
        return idLocalTag;
    }

    public void setFechaVisitaTag( String fechaVisitaTag )
    {
        this.fechaVisitaTag = fechaVisitaTag ;
    }

    public String getFechaVisitaTag()
    {
        return fechaVisitaTag;
    }

    public void setEstadoTag( String estadoTag )
    {
        this.estadoTag = estadoTag ;
    }

    public String getEstadoTag()
    {
        return estadoTag;
    }

    // Variable para usar el bean de fachada
    FachadaColaboraAgendaLogVisitaBean fachadaAgendaLogVisitaBean;

	// Variable para usar el bean de EstadoPcBean
	ColaboraAgendaLogVisitaBean colaboraAgendaLogVisitaBean;

    // Iterador de objetos
    Iterator iteratorBean;

    private void inicializarVariablesDeRetorno() {

      //
      fachadaAgendaLogVisitaBean =
                        (FachadaColaboraAgendaLogVisitaBean)iteratorBean.next();

      // Variable que retornan al JSP
      pageContext.setAttribute("idLogVar",
                                      fachadaAgendaLogVisitaBean.getIdLogStr());
      pageContext.setAttribute("idClienteVar",
                                  fachadaAgendaLogVisitaBean.getIdCliente());
      pageContext.setAttribute("idUsuarioVar",
                                 fachadaAgendaLogVisitaBean.getIdUsuarioStr());
      pageContext.setAttribute("fechaVisitaVar",
                                   fachadaAgendaLogVisitaBean.getFechaVisita());
      pageContext.setAttribute("idEstadoVisitaVar",
                             fachadaAgendaLogVisitaBean.getIdEstadoVisitaStr());
      pageContext.setAttribute("estadoVar",
                                     fachadaAgendaLogVisitaBean.getEstadoStr());
      pageContext.setAttribute("nombreTerceroVar",
                              fachadaAgendaLogVisitaBean.getNombreTercero());
      pageContext.setAttribute("direccionTerceroVar",
                              fachadaAgendaLogVisitaBean.getDireccionTercero());
      pageContext.setAttribute("telefonoFijoVar",
                                 fachadaAgendaLogVisitaBean.getTelefonoFijo());
      pageContext.setAttribute("nombreEmpresaVar",
                                 fachadaAgendaLogVisitaBean.getNombreEmpresa());
      pageContext.setAttribute("ciudadTerceroVar",
                                 fachadaAgendaLogVisitaBean.getCiudadTercero());
      pageContext.setAttribute("nombreUsuarioVar",
                                 fachadaAgendaLogVisitaBean.getNombreUsuario());
      pageContext.setAttribute("fechaVisitaCortaVar",
                              fachadaAgendaLogVisitaBean.getFechaVisitaCorta());
      pageContext.setAttribute("idOrdenVar",
                              fachadaAgendaLogVisitaBean.getIdOrdenStr());

	}

	public int doStartTag() throws JspTagException {

      	// Parametros llegados de JSP
        colaboraAgendaLogVisitaBean  = new ColaboraAgendaLogVisitaBean();

        //
        colaboraAgendaLogVisitaBean.setIdLocal(getIdLocalTag());
        colaboraAgendaLogVisitaBean.setFechaVisita(getFechaVisitaTag());
        colaboraAgendaLogVisitaBean.setEstado(getEstadoTag());

        //
		Vector vectorBean    =
               colaboraAgendaLogVisitaBean.listaCotizacion(getIdTipoOrden());

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