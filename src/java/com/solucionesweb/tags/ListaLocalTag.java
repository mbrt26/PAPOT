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

public class ListaLocalTag extends TagSupport
                                    implements IterationTag {

    // Variable que recibe del JSP
    private String idLocalTag;

    // Metodos Tag
    public void setIdLocalTag( String idLocalTag )
    {
        this.idLocalTag = idLocalTag ;
    }

    public String getIdLocalTag()
    {
        return idLocalTag;
    }

    // Variable para usar el bean de fachada
    FachadaColaboraLocalBean fachadaColaboraLocalBean;

	// Variable para usar el bean de EstadoPcBean
	ColaboraLocalBean colaboraLocalBean;

    // Iterador de objetos
    Iterator iteratorBean;

    private void inicializarVariablesDeRetorno() {

      //
      fachadaColaboraLocalBean = (FachadaColaboraLocalBean)iteratorBean.next();

      // Variable que retornan al JSP
      pageContext.setAttribute("idLocalVar",fachadaColaboraLocalBean.getIdLocalStr());
      pageContext.setAttribute("nitVar",fachadaColaboraLocalBean.getNit());
      pageContext.setAttribute("razonSocialVar",fachadaColaboraLocalBean.getRazonSocial());
      pageContext.setAttribute("nombreLocalVar",fachadaColaboraLocalBean.getNombreLocal());
      pageContext.setAttribute("direccionVar",fachadaColaboraLocalBean.getDireccion());
      pageContext.setAttribute("telefonoVar",fachadaColaboraLocalBean.getTelefono());
      pageContext.setAttribute("faxVar",fachadaColaboraLocalBean.getFax());

	}

	public int doStartTag() throws JspTagException {

      	// Parametros llegados de JSP
        colaboraLocalBean  = new ColaboraLocalBean();
        colaboraLocalBean.setIdLocal(getIdLocalTag());

        //
		Vector vectorBean = colaboraLocalBean.listaDatosUnLocal();
        iteratorBean      = vectorBean.iterator();

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