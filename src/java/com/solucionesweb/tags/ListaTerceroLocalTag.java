package com.solucionesweb.tags;

import java.util.*;
import java.io.IOException;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.tagext.TagSupport;

// Importa el Bean de Asignaturas
import com.solucionesweb.losbeans.negocio.TerceroLocalBean;

// Importa el Bean de Fachada
import com.solucionesweb.losbeans.fachada.FachadaTerceroBean;

public class ListaTerceroLocalTag extends TagSupport
                                     implements IterationTag {

    // Variable que recibe del JSP
    private String idClienteTag;


    // Metodos Tag
    public void setIdClienteTag( String idClienteTag )
    {
        this.idClienteTag = idClienteTag ;
    }

    public String getIdClienteTag()
    {
        return idClienteTag;
    }

    // Variable para usar el bean de fachada
    FachadaTerceroBean fachadaTerceroBean;

	// Variable para usar el bean de EstadoPcBean
	TerceroLocalBean terceroLocalBean;

    // Iterador de objetos
    Iterator iteratorBean;

    private void inicializarVariablesDeRetorno() {

      //
      fachadaTerceroBean = (FachadaTerceroBean)iteratorBean.next();

      // Variable que retornan al JSP
      pageContext.setAttribute("idClienteVar",
                                             fachadaTerceroBean.getIdCliente());
      pageContext.setAttribute("idLocalTerceroVar",
                                     fachadaTerceroBean.getIdLocalTerceroStr());
      pageContext.setAttribute("nombreEmpresaVar",
                                         fachadaTerceroBean.getNombreEmpresa());
      pageContext.setAttribute("direccionTerceroVar",
                                      fachadaTerceroBean.getDireccionTercero());
      pageContext.setAttribute("idDptoCiudadVar",
                                       fachadaTerceroBean.getIdDptoCiudadStr());
      pageContext.setAttribute("telefonoFijoVar",
                                          fachadaTerceroBean.getTelefonoFijo());
      pageContext.setAttribute("telefonoCelularVar",
                                       fachadaTerceroBean.getTelefonoCelular());
      pageContext.setAttribute("telefonoFaxVar",
                                           fachadaTerceroBean.getTelefonoFax());
      pageContext.setAttribute("emailVar",fachadaTerceroBean.getEmail());
      pageContext.setAttribute("estadoVar",fachadaTerceroBean.getEstadoStr());
      pageContext.setAttribute("contactoTerceroVar",
                                       fachadaTerceroBean.getContactoTercero());
      pageContext.setAttribute("idVendedorVar",
                                         fachadaTerceroBean.getIdVendedorStr());
      pageContext.setAttribute("idSeqVar",fachadaTerceroBean.getIdSeqStr());
	}

	public int doStartTag() throws JspTagException {

      	// Parametros llegados de JSP
        terceroLocalBean  = new TerceroLocalBean();


        terceroLocalBean.setIdCliente(getIdClienteTag());

        //
		Vector vectorBean    = terceroLocalBean.listaLocal();

        //
        iteratorBean         = vectorBean.iterator();

        if ( !iteratorBean.hasNext() ) {
            try {
              pageContext.getOut().write("<br>No existen datos");
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