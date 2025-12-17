package com.solucionesweb.tags;

import java.util.*;
import java.io.IOException;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.tagext.TagSupport;

// Importa el Bean de Asignaturas
import com.solucionesweb.losbeans.negocio.AgenteRetencionFuenteVrBase;

// Importa el Bean de Fachada
import com.solucionesweb.losbeans.fachada.FachadaAgenteRteFuenteVrBase;

public class ListaAgenteRteFuenteVrBaseTag extends TagSupport
                                     implements IterationTag {

    // Variable que recibe del JSP
    private String idRteFuenteVrBaseTag;


    // Metodos Tag
    public void setIdRteFuenteVrBaseTag( String idRteFuenteVrBaseTag )
    {
        this.idRteFuenteVrBaseTag = idRteFuenteVrBaseTag ;
    }

    public String getIdRteFuenteVrBaseTag()
    {
        return idRteFuenteVrBaseTag;
    }

    private int getIdRteFuenteVrBase()
    {
        return  new Integer(getIdRteFuenteVrBaseTag()).intValue();
    }

    // Variable para usar el bean de fachada
    FachadaAgenteRteFuenteVrBase fachadaAgenteRteFuenteVrBase;

    // Variable para usar el bean de EstadoPcBean
    AgenteRetencionFuenteVrBase agenteRetencionFuenteVrBase;

    // Iterador de objetos
    Iterator iteratorBean;

    private void inicializarVariablesDeRetorno() {

      //
      fachadaAgenteRteFuenteVrBase = (FachadaAgenteRteFuenteVrBase)iteratorBean.next();

      // Variable que retornan al JSP
      pageContext.setAttribute("idRteFuenteVrBaseVar",
                            fachadaAgenteRteFuenteVrBase.getIdRteFuenteVrBaseStr());
      pageContext.setAttribute("nombreRteFuenteVrBaseVar",
                           fachadaAgenteRteFuenteVrBase.getNombreRteFuenteVrBase());

	}

	public int doStartTag() throws JspTagException {

      	// Parametros llegados de JSP
        agenteRetencionFuenteVrBase     = new AgenteRetencionFuenteVrBase();

        //
		Vector vectorBean    = agenteRetencionFuenteVrBase.listaUn(
                                                          getIdRteFuenteVrBase());

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