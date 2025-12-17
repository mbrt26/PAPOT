package com.solucionesweb.tags;

import java.util.*;
import java.io.IOException;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.tagext.TagSupport;

// Importa el Bean de Asignaturas
import com.solucionesweb.losbeans.colaboraciones.ColaboraTercero;

// Importa el Bean de Asignaturas
import com.solucionesweb.losbeans.negocio.UsuarioRutaBean;

// Importa el Bean de Fachada
import com.solucionesweb.losbeans.fachada.FachadaTerceroBean;

public class ListaClienteDesactivoTag extends TagSupport
                                     implements IterationTag {

    // Variable que recibe del JSP
    private String idUsuarioTag;

    // Metodos Tag
    public void setIdUsuarioTag( String idUsuarioTag )
    {
        this.idUsuarioTag = idUsuarioTag ;
    }

    public String getIdUsuarioTag()
    {
        return idUsuarioTag;
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
                                             fachadaTerceroBean.getIdCliente());
      pageContext.setAttribute("nombreClienteVar",
                                         fachadaTerceroBean.getNombreTercero());
      pageContext.setAttribute("telefonoVar",
                                          fachadaTerceroBean.getTelefonoFijo());
	}

	public int doStartTag() throws JspTagException {

        //
        int idEstadoTxPendiente         = 1;

      	// Parametros llegados de JSP
        colaboraTercero             = new ColaboraTercero();

        //
        colaboraTercero.setIdCliente(getIdUsuarioTag());

        //
        UsuarioRutaBean usuarioRutaBean =  new UsuarioRutaBean();

        //
        usuarioRutaBean.setIdUsuario(getIdUsuarioTag());

        //
        String idRuta                   = usuarioRutaBean.listaRutaUsuario();

        //
		Vector vectorBean               = colaboraTercero.listaUnTercero();

        //
        iteratorBean                    = vectorBean.iterator();

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