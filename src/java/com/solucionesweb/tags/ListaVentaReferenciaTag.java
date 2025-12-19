package com.solucionesweb.tags;

import java.util.*;
import java.io.IOException;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.tagext.TagSupport;

// Importa el bean de ColaboraResurtidoOrden
import com.solucionesweb.losbeans.colaboraciones.ColaboraReporteModDctoBean;

// Importa el bean de FachadaColaboraDctoOrdenBean
import com.solucionesweb.losbeans.fachada.FachadaColaboraReporteDctoModBean;
import static javax.servlet.jsp.tagext.IterationTag.EVAL_BODY_AGAIN;
import static javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE;
import static javax.servlet.jsp.tagext.Tag.EVAL_PAGE;
import static javax.servlet.jsp.tagext.Tag.SKIP_BODY;

public class ListaVentaReferenciaTag extends TagSupport implements IterationTag {

    // Variable que recibe del JSP
    private String idLocalTag;
    private String idTipoOrdenCadenaTag;
    private String indicadorInicialTag;
    private String indicadorFinalTag;
    private String idVendedorTag;
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

    public void setIdTipoOrdenCadenaTag( String idTipoOrdenCadenaTag )
    {
        this.idTipoOrdenCadenaTag = idTipoOrdenCadenaTag ;
    }

    public String getIdTipoOrdenCadenaTag()
    {
        return idTipoOrdenCadenaTag;
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
    public void setIdVendedorTag(String idVendedorTag) {
        this.idVendedorTag = idVendedorTag;
    }

    public String getIdVendedorTag() {
        return idVendedorTag;
    }

    public double getIdVendedor() {
        return new Double(getIdVendedorTag()).doubleValue();
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
    ColaboraReporteModDctoBean	colaboraReporteDctoBean;

    //
    FachadaColaboraReporteDctoModBean fachada;

    // Iterador de objetos
    Iterator iteratorBean;

    private void inicializarVariablesDeRetorno() {

      //
      fachada = (FachadaColaboraReporteDctoModBean)iteratorBean.next();

      // Variable que retornan al JSP
      pageContext.setAttribute("idDctoVar",fachada.getIdDctoStr());     
      pageContext.setAttribute("nombreTerceroVar",fachada.getNombreTercero());
      pageContext.setAttribute("nombreReferenciaVar",fachada.getNombreReferencia());
      pageContext.setAttribute("codigoVar",fachada.getReferenciaCliente());
      pageContext.setAttribute("fechaDctoVar",fachada.getFechaDctoFormato());
      pageContext.setAttribute("vrBaseVar",fachada.getVrBaseDf0());      
      pageContext.setAttribute("pesoFacturadoVar",
                                                 fachada.getPesoFacturadoDf2());
      pageContext.setAttribute("cantidadFacturadoVar",fachada.getCantidadFacturadoDf0());
      pageContext.setAttribute("nombreTipoNegocioVar",
                                                fachada.getNombreTipoNegocio());
      pageContext.setAttribute("aliasUsuarioVar",fachada.getAliasUsuario());

  	}

	public int doStartTag() throws JspTagException {

        // Parametros llegados de JSP
        colaboraReporteDctoBean      = new ColaboraReporteModDctoBean();

        //
        colaboraReporteDctoBean.setIdLocal(getIdLocalTag());
        colaboraReporteDctoBean.setIndicadorInicial(getIndicadorInicialTag());
        colaboraReporteDctoBean.setIndicadorFinal(getIndicadorFinalTag());
        colaboraReporteDctoBean.setIdVendedor(getIdVendedor());

        Vector vectorBean;

        //
        if (getIdVendedor()==0) {

           //
           vectorBean            = colaboraReporteDctoBean.listaVentaAll(
                                                      getIdTipoOrdenCadenaTag(),
                                                           getFechaInicialTag(),
                                                            getFechaFinalTag());

        } else {

           //
           vectorBean            = colaboraReporteDctoBean.listaVentaUn(
                                                      getIdTipoOrdenCadenaTag(),
                                                           getFechaInicialTag(),
                                                            getFechaFinalTag());
           
        }


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