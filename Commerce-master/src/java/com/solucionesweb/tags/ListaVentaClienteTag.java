package com.solucionesweb.tags;

import java.util.*;
import java.io.IOException;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.tagext.TagSupport;

// Importa el bean de ColaboraResurtidoOrden
import com.solucionesweb.losbeans.colaboraciones.ColaboraDctoOrdenDetalleBean;

// Importa el bean de FachadaColaboraDctoOrdenBean
import com.solucionesweb.losbeans.fachada.FachadaDctoOrdenDetalleBean;

public class ListaVentaClienteTag extends TagSupport implements IterationTag {

    // Variable que recibe del JSP
    private String idLocalTag;
    private String idTipoOrdenInicialTag;
    private String idTipoOrdenFinalTag;
    private String indicadorInicialTag;
    private String indicadorFinalTag;
    private String fechaInicialTag;
    private String fechaFinalTag;

    // Metodos Tag
    public void setIdLocalTag( String idLocalTag )
    {
        this.idLocalTag = idLocalTag ;
    }

    public String getIdLocalTag()
    {
        return idLocalTag;
    }

    public void setIdTipoOrdenInicialTag( String idTipoOrdenInicialTag )
    {
        this.idTipoOrdenInicialTag = idTipoOrdenInicialTag ;
    }

    public String getIdTipoOrdenInicialTag()
    {
        return idTipoOrdenInicialTag;
    }

    public void setIdTipoOrdenFinalTag( String idTipoOrdenFinalTag )
    {
        this.idTipoOrdenFinalTag = idTipoOrdenFinalTag ;
    }

    public String getIdTipoOrdenFinalTag()
    {
        return idTipoOrdenFinalTag;
    }

    public void setIndicadorInicialTag(String indicadorInicialTag) {
        this.indicadorInicialTag = indicadorInicialTag;
    }

    public String getIndicadorInicialTag() {
        return indicadorInicialTag;
    }

     public void setIndicadorFinalTag(String indicadorFinalTag) {
         this.indicadorFinalTag = indicadorFinalTag;
     }

     public String getIndicadorFinalTag() {
         return indicadorFinalTag;
    }

    public void setFechaInicialTag(String fechaInicialTag) {
        this.fechaInicialTag = fechaInicialTag;
    }

    public String getFechaInicialTag() {
        return fechaInicialTag;
    }

    public void setFechaFinalTag(String fechaFinalTag) {
        this.fechaFinalTag = fechaFinalTag;
    }

    public String getFechaFinalTag() {
        return fechaFinalTag;
    }

	//
    ColaboraDctoOrdenDetalleBean	colaboraDctoOrdenDetalleBean;

    //
    FachadaDctoOrdenDetalleBean fachada;

    // Iterador de objetos
    Iterator iteratorBean;

    private void inicializarVariablesDeRetorno() {

      //
      fachada = (FachadaDctoOrdenDetalleBean)iteratorBean.next();

      // Variable que retornan al JSP
      pageContext.setAttribute("idDctoVar",fachada.getIdDctoStr());
      pageContext.setAttribute("idClienteVar",fachada.getIdCliente());
      pageContext.setAttribute("nombreTerceroVar",fachada.getNombreTercero());
      pageContext.setAttribute("indicadorVar",fachada.getIndicadorStr());
      pageContext.setAttribute("fechaDctoVar",fachada.getFechaDctoCorta());
      pageContext.setAttribute("vrFacturaVar",fachada.getVrFacturaDf0());
      pageContext.setAttribute("referenciaVar",fachada.getReferencia());
      pageContext.setAttribute("numeroOrdenVar",fachada.getNumeroOrdenSf0());
      pageContext.setAttribute("kgVar",fachada.getPesoFacturadoDf2());
      pageContext.setAttribute("cantidadVar",fachada.getCantidadDf0());
      pageContext.setAttribute("vrVentaUnitarioVar",fachada.getVrVentaUnitarioDf0());
      pageContext.setAttribute("vrVentaSinIvaVar",fachada.getVrVentaSinIvaDf0());      
      pageContext.setAttribute("porcentajeIvaVar",fachada.getPorcentajeIvaDf0());
      pageContext.setAttribute("referenciaClienteVar",fachada.getReferenciaCliente());

  	}

	public int doStartTag() throws JspTagException {

        // Parametros llegados de JSP
        colaboraDctoOrdenDetalleBean      = new ColaboraDctoOrdenDetalleBean();

        //
        colaboraDctoOrdenDetalleBean.setIdLocal(getIdLocalTag());
        colaboraDctoOrdenDetalleBean.setIdTipoOrdenINI(getIdTipoOrdenInicialTag());
        colaboraDctoOrdenDetalleBean.setIdTipoOrdenFIN(getIdTipoOrdenFinalTag());
        colaboraDctoOrdenDetalleBean.setIndicadorInicial(getIndicadorInicialTag());
        colaboraDctoOrdenDetalleBean.setIndicadorFinal(getIndicadorFinalTag());
        colaboraDctoOrdenDetalleBean.setFechaInicial(fechaInicialTag);
        colaboraDctoOrdenDetalleBean.setFechaFinal(fechaFinalTag);

        //
        Vector vectorBean = colaboraDctoOrdenDetalleBean.listaDctoOT();

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