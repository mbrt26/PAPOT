package com.solucionesweb.tags;

import java.util.*;
import java.io.IOException;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.tagext.TagSupport;

// Importa el Bean de Asignaturas
import com.solucionesweb.losbeans.colaboraciones.ColaboraLocalBean;

// Importa el Bean de Fachada
import com.solucionesweb.losbeans.fachada.FachadaColaboraLocalBean;

public class ListaLocalTodosTag extends TagSupport
                                    implements IterationTag {

    // Variable para usar el bean de fachada
    FachadaColaboraLocalBean fachadaLocalBean;

	// Variable para usar el bean de area
	ColaboraLocalBean colaboraLocalBean;

    // Iterador de objetos
    Iterator iteratorBean;

    private void inicializarVariablesDeRetorno() {

      fachadaLocalBean = (FachadaColaboraLocalBean)iteratorBean.next();

      pageContext.setAttribute("idLocalVar",fachadaLocalBean.getIdLocalStr());
      pageContext.setAttribute("nitVar",fachadaLocalBean.getNit());
      pageContext.setAttribute("razonSocialVar",fachadaLocalBean.getRazonSocial());
      pageContext.setAttribute("nombreLocalVar",fachadaLocalBean.getNombreLocal());
      pageContext.setAttribute("direccionVar",fachadaLocalBean.getDireccion());
      pageContext.setAttribute("telefonoVar",fachadaLocalBean.getTelefono());
      pageContext.setAttribute("faxVar",fachadaLocalBean.getFax());

   }

	public int doStartTag() throws JspTagException
	{
		// Efectua la busqueda de los grados que estan matriculados

        colaboraLocalBean  = new ColaboraLocalBean();

        Vector listaVector = colaboraLocalBean.listaLocales();
        iteratorBean       = listaVector.iterator();

        if ( !iteratorBean.hasNext() ) {
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