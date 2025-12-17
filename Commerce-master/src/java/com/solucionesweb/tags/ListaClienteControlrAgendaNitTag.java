package com.solucionesweb.tags;

import java.util.*;
import java.io.IOException;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.tagext.TagSupport;

// Importa el Bean de Asignaturas
import com.solucionesweb.losbeans.colaboraciones.ColaboraTercero;

// Importa el Bean de Fachada
import com.solucionesweb.losbeans.fachada.FachadaTerceroBean;

public class ListaClienteControlrAgendaNitTag extends TagSupport
                                     implements IterationTag {

    // Variable que recibe del JSP
    private String idClienteTag;
    private String idTipoTerceroTag;
                   
    //
    int idLocal = 1;

    // Metodos Tag
    public void setIdClienteTag( String idClienteTag )
    {
        this.idClienteTag = idClienteTag ;
    }

    public String getIdClienteTag()
    {
        return idClienteTag;
    }

    public void setIdTipoTerceroTag( String idTipoTerceroTag )
    {
        this.idTipoTerceroTag = idTipoTerceroTag ;
    }

    public String getIdTipoTerceroTag()
    {
        return idTipoTerceroTag;
    }

    // Variable para usar el bean de fachada
    FachadaTerceroBean fachadaTerceroBean;

	// Variable para usar el bean de EstadoPcBean
	ColaboraTercero colaboraTercero;

    // Iterador de objetos
    Iterator iteratorBean;

    private void inicializarVariablesDeRetorno() {


      //
      fachadaTerceroBean = (FachadaTerceroBean)iteratorBean.next();

      // Variable que retornan al JSP
      pageContext.setAttribute("idClienteVar",
                                     fachadaTerceroBean.getIdClienteDf0());
      pageContext.setAttribute("estadoVar",
                                     fachadaTerceroBean.getEstadoStr());
      pageContext.setAttribute("nombreTerceroVar",
                                 fachadaTerceroBean.getNombreTercero());
      pageContext.setAttribute("direccionTerceroVar",
                                     fachadaTerceroBean.getDireccionTercero());
      pageContext.setAttribute("telefonoFijoVar",
                                      fachadaTerceroBean.getTelefonoFijo());
      pageContext.setAttribute("nombreEmpresaVar",
                                 fachadaTerceroBean.getNombreEmpresa());
      pageContext.setAttribute("ciudadTerceroVar",
                               fachadaTerceroBean.getCiudadTercero());
      pageContext.setAttribute("contactoTerceroVar",
                                      fachadaTerceroBean.getContactoTercero());
      pageContext.setAttribute("telefonoFaxVar",
                                   fachadaTerceroBean.getTelefonoFax());
      pageContext.setAttribute("telefonoCelularVar",
                                       fachadaTerceroBean.getTelefonoCelular());
      pageContext.setAttribute("listaPrecioVar",
                                      fachadaTerceroBean.getIdListaPrecioStr());
      pageContext.setAttribute("emailVar",
                                         fachadaTerceroBean.getEmail());
      pageContext.setAttribute("idFormaPagoVar",
                                        fachadaTerceroBean.getIdFormaPagoStr());
      pageContext.setAttribute("cupoCreditoVar",
                                        fachadaTerceroBean.getCupoCreditoDf0());
	}

	public int doStartTag() throws JspTagException {

      	// Parametros llegados de JSP
        colaboraTercero  = new ColaboraTercero();

        //
        colaboraTercero.setIdCliente(getIdClienteTag());
        colaboraTercero.setIdTipoTercero(getIdTipoTerceroTag());

        //
		Vector vectorBean    = colaboraTercero.listaUnTercero();

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