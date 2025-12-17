package com.solucionesweb.tags;

import java.util.*;
import java.io.IOException;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.tagext.TagSupport;

// Importa el Bean de Asignaturas
import com.solucionesweb.losbeans.negocio.MarcaBean;

// Importa el Bean de Fachada
import com.solucionesweb.losbeans.fachada.FachadaMarcaBean;

public class ListaMarcaNombreTag extends TagSupport
                                    implements IterationTag
{

    // Variables de Retorno a jsp
    private String nombreMarcaTag;

    public void setNombreMarcaTag( String nombreMarcaTag )
    {
        this.nombreMarcaTag = nombreMarcaTag ;
    }

    public String getNombreMarcaTag()
    {
        return nombreMarcaTag;
    }

    // Variable para usar el bean de fachada
    FachadaMarcaBean fachadaMarcaBean;

	// Variable para usar el bean de area
	MarcaBean marcaBean;

    // Iterador de objetos
    Iterator iteratorLineaBean;

    private void inicializarVariablesDeRetorno() {

      fachadaMarcaBean = (FachadaMarcaBean)iteratorLineaBean.next();

      pageContext.setAttribute("idMarcaVar",fachadaMarcaBean.getIdMarcaStr());
      pageContext.setAttribute("nombreMarcaVar",
                                             fachadaMarcaBean.getNombreMarca());
      pageContext.setAttribute("estadoVar",fachadaMarcaBean.getEstadoStr());

	}

	public int doStartTag() throws JspTagException
	{

		// Efectua la busqueda de los grados que estan matriculados
		marcaBean               = new MarcaBean();
		marcaBean.setNombreMarca(getNombreMarcaTag());

        //
		Vector listaLineaVector = marcaBean.seleccionaMarcaxNombre();

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