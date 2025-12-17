package com.solucionesweb.tags;

import java.util.*;
import java.io.IOException;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.tagext.TagSupport;

// Importa el Bean de Asignaturas
import com.solucionesweb.losbeans.negocio.AgenteRetencionIvaVrBase;

// Importa el Bean de Fachada
import com.solucionesweb.losbeans.fachada.FachadaAgenteRetencionIvaVrBase;

public class ListaAgenteRteIvaVrBaseTag extends TagSupport
                                     implements IterationTag {

    // Variable que recibe del JSP
    private String idRteIvaVrBaseTag;


    // Metodos Tag
    public void setIdRteIvaVrBaseTag( String idRteIvaVrBaseTag )
    {
        this.idRteIvaVrBaseTag = idRteIvaVrBaseTag ;
    }

    public String getIdRteIvaVrBaseTag()
    {
        return idRteIvaVrBaseTag;
    }

    private int getIdRteIvaVrBase()
    {
        return  new Integer(getIdRteIvaVrBaseTag()).intValue();
    }

    // Variable para usar el bean de fachada
    FachadaAgenteRetencionIvaVrBase fachadaAgenteRetencionIvaVrBase;

    // Variable para usar el bean de EstadoPcBean
    AgenteRetencionIvaVrBase agenteRetencionIvaVrBase;

    // Iterador de objetos
    Iterator iteratorBean;

    private void inicializarVariablesDeRetorno() {

      //
      fachadaAgenteRetencionIvaVrBase = (FachadaAgenteRetencionIvaVrBase)iteratorBean.next();

      // Variable que retornan al JSP
      pageContext.setAttribute("idRteIvaVrBaseVar",
                            fachadaAgenteRetencionIvaVrBase.getIdRteIvaVrBaseStr());
      pageContext.setAttribute("nombreRteIvaVrBaseVar",
                           fachadaAgenteRetencionIvaVrBase.getNombreRteIvaVrBase());

	}

	public int doStartTag() throws JspTagException {

      	// Parametros llegados de JSP
        agenteRetencionIvaVrBase     = new AgenteRetencionIvaVrBase();

        //
		Vector vectorBean    = agenteRetencionIvaVrBase.listaUn(
                                                          getIdRteIvaVrBase());

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