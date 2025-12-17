package com.solucionesweb.tags;

import java.util.*;
import java.io.IOException;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.tagext.TagSupport;

// Importa el Bean de Asignaturas
import com.solucionesweb.losbeans.colaboraciones.ColaboraPluPrecio;

// Importa el Bean de Fachada
import com.solucionesweb.losbeans.fachada.FachadaPluPrecio;

public class ListaPluPrecioTag extends TagSupport
                                     implements IterationTag {

    // Variable que recibe del JSP
    private String idPluTag;

    // Metodos Tag
    public void setIdPluTag( String idPluTag )
    {
        this.idPluTag = idPluTag ;
    }

    public String getIdPluTag()
    {
        return idPluTag;
    }

    public int getIdPlu()
    {
        return new Integer(getIdPluTag()).intValue();
    }


    // Variable para usar el bean de fachada
    FachadaPluPrecio fachadaPluPrecio;

	// Variable para usar el bean de EstadoPcBean
	ColaboraPluPrecio colaboraPluPrecio;

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
      pageContext.setAttribute("vrVentaUnitarioVar",
                                     fachadaPluPrecio.getVrVentaUnitarioStr());      
      pageContext.setAttribute("vrVentaUnitarioDf0Var",
                                     fachadaPluPrecio.getVrVentaUnitarioDf0());
      pageContext.setAttribute("factorBaseDf2Var",
                                     fachadaPluPrecio.getFactorBaseDf2());
	}

	public int doStartTag() throws JspTagException {

      	// Parametros llegados de JSP
        colaboraPluPrecio            = new ColaboraPluPrecio();

        //
		Vector vectorBean            =
                                   colaboraPluPrecio.listaNombrePlu(getIdPlu());

        //
        iteratorBean                 = vectorBean.iterator();

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