package com.solucionesweb.tags;

import java.util.*;
import java.io.IOException;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.tagext.TagSupport;

// Importa el Bean de Asignaturas
import com.solucionesweb.losbeans.negocio.CiudadBean;

// Importa el Bean de Fachada
import com.solucionesweb.losbeans.fachada.FachadaCiudadBean;

public class ListaUnDptoCiudadTag extends TagSupport
                                implements IterationTag {
    
    // Variable que recibe del JSP
    private String idCiudadTag;

    // Metodos Tag
    public void setIdCiudadTag( String idCiudadTag )
    {
        this.idCiudadTag = idCiudadTag ;
    }

    public String getIdCiudadTag()
    {
        return idCiudadTag;
    }

    // Variable para usar el bean de fachada
    FachadaCiudadBean fachadaCiudadBean;

	// Variable para usar el bean de area
	CiudadBean ciudadBean;

    // Iterador de objetos
    Iterator iteratorBean;

    private void inicializarVariablesDeRetorno() {

      fachadaCiudadBean = (FachadaCiudadBean)iteratorBean.next();

      //
      pageContext.setAttribute("idCiudadVar",fachadaCiudadBean.getIdCiudadStr());
      pageContext.setAttribute("nombreCiudadVar",fachadaCiudadBean.getNombreCiudad());
      pageContext.setAttribute("nombreDptoVar",fachadaCiudadBean.getNombreDpto());

	}

	public int doStartTag() throws JspTagException 	{

		// Efectua la busqueda de los grados que estan matriculados
		ciudadBean         = new CiudadBean();
        
        ciudadBean.setIdCiudad(getIdCiudadTag());

        //
		Vector listaVector = ciudadBean.listaUnCiudad();

        //
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