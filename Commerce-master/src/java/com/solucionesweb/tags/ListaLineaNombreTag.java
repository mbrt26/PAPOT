package com.solucionesweb.tags;

import java.util.*;
import java.io.IOException;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.tagext.TagSupport;

// Importa el Bean de Asignaturas
import com.solucionesweb.losbeans.negocio.LineaBean;

// Importa el Bean de Fachada
import com.solucionesweb.losbeans.fachada.FachadaLineaBean;

public class ListaLineaNombreTag extends TagSupport
                                    implements IterationTag
{

    // Variables de Retorno a jsp
    private String nombreLineaTag;

    // Variables de Retorno a jsp
    private String idLineaVar;
    private String nombreLineaVar;
    private String estadoVar;

	// Metodos recibidos JSP
    public void setNombreLineaTag(String nombreLineaTag) {
	  this.nombreLineaTag = nombreLineaTag;
	}

	public String getNombreLineaTag() {
		return nombreLineaTag;
	}

    // Metodos de retorno a JSP
	public void setIdLineaVar(String idLineaVar) {
	    this.idLineaVar = idLineaVar;
	}

    public String getIdLineaVar() {
		return idLineaVar;
	}

	public void setNombreLineaVar(String nombreLineaVar) {
	  this.nombreLineaVar = nombreLineaVar;
	}

	public String getNombreLineaVar() {
		return nombreLineaVar;
	}

    public void setEstadoVar(String estadoVar) {
   	 this.estadoVar = estadoVar ;
    }

    public String getEstadoVar() {
   	 return estadoVar;
    }

    // Variable para usar el bean de fachada
    FachadaLineaBean fachadaLineaBean;

	// Variable para usar el bean de area
	LineaBean lineaBean;

    // Iterador de objetos
    Iterator iteratorLineaBean;

    private void inicializarVariablesDeRetorno() {

      fachadaLineaBean = (FachadaLineaBean)iteratorLineaBean.next();

      idLineaVar       = fachadaLineaBean.getIdLineaStr();
      nombreLineaVar   = fachadaLineaBean.getNombreLinea();
      estadoVar        = fachadaLineaBean.getEstadoStr();

      pageContext.setAttribute("idLineaVar",idLineaVar);
      pageContext.setAttribute("nombreLineaVar",nombreLineaVar);
      pageContext.setAttribute("estadoVar",estadoVar);

	}

	public int doStartTag() throws JspTagException
	{

		// Efectua la busqueda de los grados que estan matriculados
		lineaBean               = new LineaBean();
		lineaBean.setNombreLinea(getNombreLineaTag());

        //
		Vector listaLineaVector = lineaBean.seleccionaLineaxNombre();

        //
        iteratorLineaBean       = listaLineaVector.iterator();

        if ( !iteratorLineaBean.hasNext() ) {
            try {
              pageContext.getOut().write("La consulta no devolvio ningun dato <br/>");
              pageContext.getOut().write("<br/>");
			  pageContext.getOut().write("Por favor comunique al administrador del sistema que no existen grados <br/>");
		    }
            catch(IOException ex) {
			  throw new JspTagException ("Error: la respuesta no se pudo escribir para los grados");
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

      if (iteratorLineaBean.hasNext()) {
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