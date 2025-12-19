package com.solucionesweb.tags;

import java.util.*;
import java.io.IOException;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.tagext.TagSupport;

// Importa el bean de ColaboraResurtido
import com.solucionesweb.losbeans.colaboraciones.ColaboraResurtidoDetalle;

// Importa el bean de FachadaDctoOrdenDetalleBean
import com.solucionesweb.losbeans.fachada.FachadaDctoOrdenDetalleBean;

public class ListaAjusteTag
                                 extends TagSupport implements IterationTag {

    // Variable que recibe del JSP
    private String idLocalTag;
    private String idTipoOrdenTag;
    private String idOrdenTag;

    // Metodos Tag
    public void setIdLocalTag( String idLocalTag )
    {
        this.idLocalTag = idLocalTag ;
    }

    public String getIdLocalTag()
    {
        return idLocalTag;
    }

    public void setIdOrdenTag(String idOrdenTag) {
        this.idOrdenTag = idOrdenTag;
    }

    public String getIdOrdenTag() {
        return idOrdenTag;
    }

    public int getIdLog() {
        return new Integer(getIdOrdenTag());
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
    ColaboraResurtidoDetalle	colabora;

    //
    FachadaDctoOrdenDetalleBean fachada;

    // Iterador de objetos
    Iterator iteratorBean;

    private void inicializarVariablesDeRetorno() {

      //
      fachada = (FachadaDctoOrdenDetalleBean)iteratorBean.next();

      // Variable que retornan al JSP
      pageContext.setAttribute("idLocalVar",fachada.getIdLocalStr());
      pageContext.setAttribute("nombrePluVar",fachada.getNombrePlu());
      pageContext.setAttribute("nombreMarcaVar",fachada.getNombreMarca());
      pageContext.setAttribute("nombreCategoriaVar",
                                                  fachada.getNombreCategoria());
      pageContext.setAttribute("idTipoOrdenVar",
                                              fachada.getIdTipoOrdenStr());
      pageContext.setAttribute("idOrdenVar",fachada.getIdOrdenStr());
      pageContext.setAttribute("idPluVar",fachada.getIdPluStr());
      pageContext.setAttribute("porcentajeIvaVar",
                                                 fachada.getPorcentajeIvaDf0());
      pageContext.setAttribute("vrCostoVar",fachada.getVrCostoDf0());
      pageContext.setAttribute("idLogVar",fachada.getIdLogStr());
      pageContext.setAttribute("cantidadPedidoVar",
                                                fachada.getCantidadPedidoDf2());
      pageContext.setAttribute("vrCostoPedidoVar",
                                                 fachada.getVrCostoPedidoDf0());
      pageContext.setAttribute("cantidadVar",fachada.getCantidadDf2());
      
  	}

	public int doStartTag() throws JspTagException {

	//
    ColaboraResurtidoDetalle	colabora;

    //
    FachadaDctoOrdenDetalleBean fachada;

        // Parametros llegados de JSP
        colabora            = new ColaboraResurtidoDetalle();

        //
        colabora.setIdLocal(getIdLocalTag());
        colabora.setIdTipoOrden(getIdTipoOrdenTag());
        colabora.setIdOrden(getIdOrdenTag());

        //
        Vector vectorBean            = colabora.listaAjuste();

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