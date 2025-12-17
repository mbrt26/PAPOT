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

public class ListaResurtidoPeriodoTag
                                 extends TagSupport implements IterationTag {

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
      pageContext.setAttribute("idDctoVar",fachada.getIdDctoStr());
      pageContext.setAttribute("vrCostoMVVar",fachada.getVrCostoMVDf2());
      pageContext.setAttribute("nombreLocalVar",fachada.getNombreLocal());

  	}

	public int doStartTag() throws JspTagException {

        // Parametros llegados de JSP
        colaboraResurtidoOrden      = new ColaboraResurtidoOrden();

        //
        colaboraResurtidoOrden.setIdLocal(getIdLocalTag());
        colaboraResurtidoOrden.setIdTipoOrdenCadena(getIdTipoOrdenCadenaTag());
        colaboraResurtidoOrden.setFechaInicial(getFechaInicialTag());
        colaboraResurtidoOrden.setFechaFinal(getFechaFinalTag());
        colaboraResurtidoOrden.setIndicador(getIndicadorInicialTag());
        colaboraResurtidoOrden.setIndicador(getIndicadorFinalTag());
        colaboraResurtidoOrden.setIdUsuario(getIdVendedorTag());

        //
        Vector vectorBean            =
                                     colaboraResurtidoOrden.listaResurtidoPeriodo();

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