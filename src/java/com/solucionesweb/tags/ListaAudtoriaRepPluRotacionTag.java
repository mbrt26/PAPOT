package com.solucionesweb.tags;

import java.util.*;
import java.io.IOException;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.tagext.TagSupport;

// Importa el bean de ColaboraResurtidoOrden
import com.solucionesweb.losbeans.colaboraciones.ColaboraIndicadorInventario;

// Importa el bean de FachadaColaboraDctoOrdenBean
import com.solucionesweb.losbeans.fachada.FachadaDctoOrdenDetalleBean;

public class ListaAudtoriaRepPluRotacionTag extends TagSupport implements IterationTag {

    // Variable que recibe del JSP
    private String idLocalTag;
    private String idTipoOrdenTag;
    private String fechaInicialTag;
    private String fechaFinalTag;
    private String idBodegaTag;

    //
    ColaboraIndicadorInventario	colaboraIndicadorInventario;

    //
    FachadaDctoOrdenDetalleBean fachada;

    // Iterador de objetos
    Iterator iteratorBean;

    private void inicializarVariablesDeRetorno() {

      //
      fachada = (FachadaDctoOrdenDetalleBean)iteratorBean.next();


      // Variable que retornan al JSP
      pageContext.setAttribute("nombrePluVar",fachada.getNombrePlu());
      pageContext.setAttribute("idPluVar",fachada.getIdPluStr());
      pageContext.setAttribute("vrCostoVar",fachada.getVrCostoDf0());
      pageContext.setAttribute("factorDespachoVar",fachada.getFactorDespachoStr());
      pageContext.setAttribute("cantidadVar",fachada.getCantidadDf2());
      pageContext.setAttribute("vrVentaUnitarioVar",fachada.getVrVentaUnitarioDf0());
      pageContext.setAttribute("rotacionVar",fachada.getRotacioStr());

  	}

	public int doStartTag() throws JspTagException {

        // Parametros llegados de JSP
        colaboraIndicadorInventario      = new ColaboraIndicadorInventario();

        //
        colaboraIndicadorInventario.setIdLocal(getIdLocalTag());
        colaboraIndicadorInventario.setIdTipoOrden(getIdTipoOrdenTag());
        colaboraIndicadorInventario.setIdBodega(getIdBodegaTag());

        //
        Vector vectorBean = colaboraIndicadorInventario.listaIndicadorPluRotacion(
                                                       getFechaInicialTag(),
                                                       getFechaFinalTag());

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

    public String getIdLocalTag() {
        return idLocalTag;
    }

    public void setIdLocalTag(String idLocalTag) {
        this.idLocalTag = idLocalTag;
    }

    public String getIdTipoOrdenTag() {
        return idTipoOrdenTag;
    }

    public void setIdTipoOrdenTag(String idTipoOrdenTag) {
        this.idTipoOrdenTag = idTipoOrdenTag;
    }

    public String getFechaInicialTag() {
        return fechaInicialTag;
    }

    public void setFechaInicialTag(String fechaInicialTag) {
        this.fechaInicialTag = fechaInicialTag;
    }

    public String getFechaFinalTag() {
        return fechaFinalTag;
    }

    public void setFechaFinalTag(String fechaFinalTag) {
        this.fechaFinalTag = fechaFinalTag;
    }

    public String getIdBodegaTag() {
        return idBodegaTag;
    }

    public void setIdBodegaTag(String idBodegaTag) {
        this.idBodegaTag = idBodegaTag;
    }

        
}