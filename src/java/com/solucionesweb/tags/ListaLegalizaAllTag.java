package com.solucionesweb.tags;

import java.util.*;
import java.io.IOException;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.tagext.TagSupport;

// Importa el bean de ColaboraResurtidoOrden
import com.solucionesweb.losbeans.colaboraciones.ColaboraResurtidoOrden;

// Importa el bean de FachadaColaboraDctoOrdenBean
import com.solucionesweb.losbeans.fachada.FachadaColaboraDctoOrdenBean;

public class ListaLegalizaAllTag
                                 extends TagSupport implements IterationTag {

    // Variable que recibe del JSP
    private String idLocalTag;
    private String idTerceroTag;
    private String idTipoOrdenTag;

    // Metodos Tag
    public void setIdLocalTag( String idLocalTag )
    {
        this.idLocalTag = idLocalTag ;
    }

    public String getIdLocalTag()
    {
        return idLocalTag;
    }

    public void setIdTerceroTag( String idTerceroTag )
    {
        this.idTerceroTag = idTerceroTag ;
    }

    public String getIdTerceroTag()
    {
        return idTerceroTag;
    }

    public void setIdTipoOrdenTag(String idTipoOrdenTag) {
        this.idTipoOrdenTag = idTipoOrdenTag;
    }

    public String getIdTipoOrdenTag() {
        return idTipoOrdenTag;
    }

    public int getIdTipoOrden() {
        return new Integer(getIdTipoOrdenTag());
    }

	//
    ColaboraResurtidoOrden	colaboraResurtidoOrden;

    //
    FachadaColaboraDctoOrdenBean fachada;

    // Iterador de objetos
    Iterator iteratorBean;

    private void inicializarVariablesDeRetorno() {

      //
      fachada = (FachadaColaboraDctoOrdenBean)iteratorBean.next();

      // Variable que retornan al JSP
      pageContext.setAttribute("idTerceroVar",fachada.getIdCliente());
      pageContext.setAttribute("nombreTerceroVar",fachada.getNombreTercero());
      pageContext.setAttribute("fechaOrdenVar",fachada.getFechaOrdenCorta());
      pageContext.setAttribute("fechaEntregaVar",fachada.getFechaEntregaCorta());
      pageContext.setAttribute("diasHistoriaVar",fachada.getDiasHistoriaStr());
      pageContext.setAttribute("diasInventarioVar",
                                                fachada.getDiasInventarioStr());
      pageContext.setAttribute("nombreUsuarioVar",fachada.getNombreUsuario());
      pageContext.setAttribute("vrCostoBaseVar",fachada.getVrCostoBaseDf0());
      pageContext.setAttribute("cantidadArticulosVar",
                                             fachada.getCantidadArticulosStr());
      pageContext.setAttribute("idOrdenVar",fachada.getIdOrdenStr());
      pageContext.setAttribute("idLogVar",fachada.getIdLogStr());

  	}

	public int doStartTag() throws JspTagException {

        // Parametros llegados de JSP
        colaboraResurtidoOrden      = new ColaboraResurtidoOrden();

        //
        colaboraResurtidoOrden.setIdLocal(getIdLocalTag());
        colaboraResurtidoOrden.setIdTipoOrden(getIdTipoOrdenTag());
        colaboraResurtidoOrden.setIdCliente(getIdTerceroTag());

        //
        Vector vectorBean            =
                                  colaboraResurtidoOrden.listaLegalizaTercero();

        //
        iteratorBean         = vectorBean.iterator();

        //
        if ( !iteratorBean.hasNext() ) {
            try {
              pageContext.getOut().write("La consulta no devolvio ningun dato <br/>");
              pageContext.getOut().write("<br/>");
			  pageContext.getOut().write("Por favor comunique al administrador<br/>");
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

          //
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