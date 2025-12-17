package com.solucionesweb.tags;

import java.util.*;
import java.io.IOException;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.tagext.TagSupport;

// Importa el Bean de Asignaturas
import com.solucionesweb.losbeans.colaboraciones.ColaboraClienteAdicionalesBean;

// Importa el Bean de Fachada
import com.solucionesweb.losbeans.fachada.FachadaClienteAdicionalesBean;

public class ListaClienteAdicionalTag extends TagSupport
                                     implements IterationTag {

    // Variable que recibe del JSP
    private String idClienteTag;
    private String idSucursalTag;

    //
    String strMotivo   = "TERM";

    // Metodos Tag
    public void setIdClienteTag( String idClienteTag )
    {
        this.idClienteTag = idClienteTag ;
    }

    public String getIdClienteTag()
    {
        return idClienteTag;
    }

    public void setIdSucursalTag( String idSucursalTag )
    {
        this.idSucursalTag = idSucursalTag ;
    }

    public String getIdSucursalTag()
    {
        return idSucursalTag;
    }


    // Variable para usar el bean de fachada
    FachadaClienteAdicionalesBean fachadaClienteAdicionalesBean;

	// Variable para usar el bean de EstadoPcBean
	ColaboraClienteAdicionalesBean colaboraClienteAdicionalesBean;

    // Iterador de objetos
    Iterator iteratorBean;

    private void inicializarVariablesDeRetorno() {

      //
      fachadaClienteAdicionalesBean =
                             (FachadaClienteAdicionalesBean)iteratorBean.next();

      // Variable que retornan al JSP
      pageContext.setAttribute("idClienteVar",fachadaClienteAdicionalesBean.getIdCliente());
      pageContext.setAttribute("emailVar",fachadaClienteAdicionalesBean.getEmail());

	}

	public int doStartTag() throws JspTagException {


      	// Parametros llegados de JSP
        colaboraClienteAdicionalesBean  = new ColaboraClienteAdicionalesBean();
        colaboraClienteAdicionalesBean.setIdCliente(getIdClienteTag().trim());

        //
		Vector vectorBean    = colaboraClienteAdicionalesBean.listaDatos();

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