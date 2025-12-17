package com.solucionesweb.tags;

import java.util.*;
import java.io.IOException;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.tagext.TagSupport;

// Importa el Bean de Asignaturas
import com.solucionesweb.losbeans.negocio.PluPrecioBean;

// Importa el Bean de Fachada
import com.solucionesweb.losbeans.fachada.FachadaPluPrecio;

public class ListaPrecioTag extends TagSupport
                                     implements IterationTag {

    // Variables de Retorno a jsp
    private String idLocalTag;
    private String idListaPrecioTag;

	// Metodos recibidos JSP
    public void setIdLocalTag(String idLocalTag) {
	  this.idLocalTag = idLocalTag;
	}

	public String getIdLocalTag() {
		return idLocalTag;
	}

    public void setIdListaPrecioTag(String idListaPrecioTag) {
	  this.idListaPrecioTag = idListaPrecioTag;
	}

	public String getIdListaPrecioTag() {
		return idListaPrecioTag;
	}
    
    // Variable para usar el bean de fachada
    FachadaPluPrecio fachadaPluPrecio;

	// Variable para usar el bean de EstadoPcBean
	PluPrecioBean pluPrecioBean;

    // Iterador de objetos
    Iterator iteratorBean;

    private void inicializarVariablesDeRetorno() {

      //
      fachadaPluPrecio = (FachadaPluPrecio)iteratorBean.next();

      // Variable que retornan al JSP
      pageContext.setAttribute("idLocalVar",
                                          fachadaPluPrecio.getIdLocalStr());
      pageContext.setAttribute("idListaPrecioVar",
                                      fachadaPluPrecio.getIdListaPrecioStr());
      pageContext.setAttribute("nombreListaVar",
                                     fachadaPluPrecio.getNombreLista());
      pageContext.setAttribute("idListaBaseVar",
                                    fachadaPluPrecio.getIdListaBaseStr());
      pageContext.setAttribute("factorBaseVar",
                                    fachadaPluPrecio.getFactorBaseStr());
      pageContext.setAttribute("estadoVar",
                                    fachadaPluPrecio.getEstadoStr());

	}

	public int doStartTag() throws JspTagException {

      	// Parametros llegados de JSP
        pluPrecioBean            = new PluPrecioBean();

        //
        pluPrecioBean.setIdLocal(getIdLocalTag());
        pluPrecioBean.setIdListaPrecio(getIdListaPrecioTag());

        //
		Vector vectorBean        = pluPrecioBean.listaUn();

        //
        iteratorBean             = vectorBean.iterator();

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