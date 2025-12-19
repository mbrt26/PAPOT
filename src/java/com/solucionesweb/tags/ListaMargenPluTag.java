package com.solucionesweb.tags;

import java.util.*;
import java.io.IOException;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.tagext.TagSupport;

// Importa el bean de ColaboraResurtidoOrden
import com.solucionesweb.losbeans.colaboraciones.ColaboraEstadistico;

// Importa el bean de FachadaColaboraDctoOrdenBean
import com.solucionesweb.losbeans.fachada.FachadaPluBean;

public class ListaMargenPluTag
                                 extends TagSupport implements IterationTag {

    // Variable que recibe del JSP
    private String idLocalTag;
    private String idTipoOrdenCadenaTag;
    private String idVendedorTag;
    private String fechaInicialTag;
    private String fechaFinalTag;
    private String indicadorInicialTag;
    private String indicadorFinalTag;

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

    public void setIndicadorInicialTag(String indicadorInicialTag) {
        this.indicadorInicialTag = indicadorInicialTag;
    }

    public String getIndicadorInicialTag() {
        return indicadorInicialTag;
    }

    public int getIndicadorInicial() {
        return new Integer(getIndicadorInicialTag());
    }

    public void setIndicadorFinalTag(String indicadorFinalTag) {
        this.indicadorFinalTag = indicadorFinalTag;
    }

    public String getIndicadorFinalTag() {
        return indicadorFinalTag;
    }

    public int getIndicadorFinal() {
        return new Integer(getIndicadorFinalTag());
    }

	//
    ColaboraEstadistico	colaboraEstadistico;

    //
    FachadaPluBean fachada;

    // Iterador de objetos
    Iterator iteratorBean;

    private void inicializarVariablesDeRetorno() {

      //
      fachada = (FachadaPluBean)iteratorBean.next();

      //
      fachada.setVrIvaVenta(fachada.getVrVentaConIva() -
                            fachada.getVrVentaSinIva());


     // fachada.setMargenIND(xMargenIND);


      // Variable que retornan al JSP
      pageContext.setAttribute("idPluVar",fachada.getIdPluStr());
      pageContext.setAttribute("nombrePluVar",fachada.getNombreCategoria() +
                                              " " +
                                              fachada.getNombrePlu());
      pageContext.setAttribute("porcentajeIvaVar",fachada.getPorcentajeIvaDf0());
      pageContext.setAttribute("idLineaVar",fachada.getIdLineaStr());
      pageContext.setAttribute("idCategoriaVar",fachada.getIdCategoriaStr());
      pageContext.setAttribute("idMarcaVar",fachada.getIdMarcaStr());
      pageContext.setAttribute("nombreCategoriaVar",
                                                  fachada.getNombreCategoria());
      pageContext.setAttribute("nombreMarcaVar",fachada.getNombreMarca());
      pageContext.setAttribute("nombreLineaVar",fachada.getNombreLinea());
      pageContext.setAttribute("cantidadVar",fachada.getCantidadDf2());
      pageContext.setAttribute("vrVentaSinIvaVar",
                                                 fachada.getVrVentaSinIvaDf2());
      pageContext.setAttribute("vrVentaConIvaVar",
                                                 fachada.getVrVentaConIvaDf2());
      pageContext.setAttribute("vrIvaVentaVar",fachada.getVrIvaVentaDf2());
      pageContext.setAttribute("vrCostoSinIvaVar",
                                                 fachada.getVrCostoSinIvaDf2());
      pageContext.setAttribute("vrCostoConIvaVar",
                                                 fachada.getVrCostoConIvaDf2());
      pageContext.setAttribute("vrCostoINDVar",fachada.getVrCostoINDDf2());
      pageContext.setAttribute("porcentajeMargenINDVar",
                                           fachada.getPorcentajeMargenINDDf2());



  	}

	public int doStartTag() throws JspTagException {

        // Parametros llegados de JSP
        colaboraEstadistico      = new ColaboraEstadistico();

        //
        colaboraEstadistico.setIdLocal(getIdLocalTag());

        Vector vectorBean;

        //
        if (getIdVendedor()==0) {

           //
           vectorBean            = colaboraEstadistico.listaMargenPlu(
                                                      getIdTipoOrdenCadenaTag(),
                                                           getFechaInicialTag(),
                                                            getFechaFinalTag(),
                                                       getIndicadorInicial(),
                                                       getIndicadorFinal());

        } else {

           //
           vectorBean            = colaboraEstadistico.listaMargenPlu(
                                                      getIdTipoOrdenCadenaTag(),
                                                           getFechaInicialTag(),
                                                            getFechaFinalTag(),
                                                       getIndicadorInicial(),
                                                       getIndicadorFinal());

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