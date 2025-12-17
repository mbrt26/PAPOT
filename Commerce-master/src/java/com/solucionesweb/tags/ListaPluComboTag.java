package com.solucionesweb.tags;

import java.util.*;
import java.io.IOException;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.tagext.TagSupport;

// Importa el Bean de Asignaturas
import com.solucionesweb.losbeans.colaboraciones.ColaboraPluCombo;

// Importa el Bean de Fachada
import com.solucionesweb.losbeans.fachada.FachadaPluCombo;

public class ListaPluComboTag extends TagSupport
                                    implements IterationTag {

    // Variables de Retorno a jsp
    private String idPluComboTag;

	// Metodos recibidos JSP
    public void setIdPluComboTag(String idPluComboTag) {
	  this.idPluComboTag = idPluComboTag;
	}

	public String getIdPluComboTag() {
		return idPluComboTag;
	}

    // Variable para usar el bean de fachada
    FachadaPluCombo fachadaPluCombo;

	// Variable para usar el bean de area
	ColaboraPluCombo colaboraPluCombo;

    // Iterador de objetos
    Iterator iteratorLineaBean;

    private void inicializarVariablesDeRetorno() {

      fachadaPluCombo = (FachadaPluCombo)iteratorLineaBean.next();

      //
      pageContext.setAttribute("idPluComboVar",
                                            fachadaPluCombo.getIdPluComboStr());
      pageContext.setAttribute("idPluVar",fachadaPluCombo.getIdPluStr());
      pageContext.setAttribute("nombrePluVar",fachadaPluCombo.getNombrePlu());
      pageContext.setAttribute("cantidadVar",
                                            fachadaPluCombo.getCantidadStr());
      pageContext.setAttribute("estadoVar",
                                          fachadaPluCombo.getEstadoStr());
      pageContext.setAttribute("nombreCategoriaVar",
                                          fachadaPluCombo.getNombreCategoria());
      pageContext.setAttribute("cantidadSf4Var",
                                            fachadaPluCombo.getCantidadSf4());

	}

	public int doStartTag() throws JspTagException
	{

		// Efectua la busqueda de los grados que estan matriculados
		colaboraPluCombo               = new ColaboraPluCombo();

        //
		colaboraPluCombo.setIdPluCombo(getIdPluComboTag());

        //
		Vector listaLineaVector = colaboraPluCombo.listaUnCombo();

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