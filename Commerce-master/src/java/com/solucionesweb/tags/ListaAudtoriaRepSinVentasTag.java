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

public class ListaAudtoriaRepSinVentasTag extends TagSupport implements IterationTag {

    // Variable que recibe del JSP
    private String idLocalTag;
    private String idTipoOrdenInicialTag;
    private String idTipoOrdenFinalTag;
    private String indicadorInicialTag;
    private String indicadorFinalTag;
    private String idVendedorTag;
    private String fechaInicialTag;
    private String fechaFinalTag;

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
      pageContext.setAttribute("vrCostoVar",fachada.getVrCostoDecimal());
      pageContext.setAttribute("factorDespachoVar",fachada.getFactorDespachoStr());
      pageContext.setAttribute("vrGeneralVar",fachada.getVrGeneralDecimal());
      pageContext.setAttribute("vrMayoristaVar",fachada.getVrMayoristaDecimal());
      pageContext.setAttribute("vrImpoconsumoVar",fachada.getVrImpoconsumoDecimal());
      pageContext.setAttribute("vrCostoIndVar",fachada.getVrCostoINDDecimal());

            System.out.println(fachada.getIdLocalStr());
            System.out.println(fachada.getFechaInicialSqlServer());
            System.out.println(fachada.getFechaFinalSqlServer());
            System.out.println(fachada.getIndicadorInicialStr());
            System.out.println(fachada.getIndicadorFinalStr());
            System.out.println(fachada.getIdTipoOrdenINIStr());
            System.out.println(fachada.getIdTipoOrdenFINStr());
  	}

	public int doStartTag() throws JspTagException {

        // Parametros llegados de JSP
        colaboraIndicadorInventario      = new ColaboraIndicadorInventario();

        //
        colaboraIndicadorInventario.setIdLocal(getIdLocalTag());
        colaboraIndicadorInventario.setIdTipoOrdenINI(Integer.parseInt(getIdTipoOrdenInicialTag()));
        colaboraIndicadorInventario.setIdTipoOrdenFIN(Integer.parseInt(getIdTipoOrdenFinalTag()));
        colaboraIndicadorInventario.setFechaInicialStr(getFechaInicialTag());
        colaboraIndicadorInventario.setFechaFinalStr(getFechaFinalTag());
        colaboraIndicadorInventario.setIndicadorInicial(Integer.parseInt(getIndicadorInicialTag()));
        colaboraIndicadorInventario.setIndicadorFinal(Integer.parseInt(getIndicadorFinalTag()));

        System.out.println(getIdLocalTag());
        System.out.println(Integer.parseInt(getIdTipoOrdenInicialTag()));
        System.out.println(Integer.parseInt(getIdTipoOrdenInicialTag()));
        System.out.println(getFechaInicialTag());
        System.out.println(getFechaFinalTag());
        System.out.println(Integer.parseInt(getIndicadorInicialTag()));
        System.out.println(Integer.parseInt(getIndicadorFinalTag()));
        //
        Vector vectorBean = colaboraIndicadorInventario.listaIndicadorSinVentas();

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

    public void setIdLocalTag( String idLocalTag )
    {
        this.idLocalTag = idLocalTag ;
    }

    public String getIdLocalTag()
    {
        return idLocalTag;
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

    public String getIdTipoOrdenInicialTag() {
        return idTipoOrdenInicialTag;
    }

    public void setIdTipoOrdenInicialTag(String idTipoOrdenInicialTag) {
        this.idTipoOrdenInicialTag = idTipoOrdenInicialTag;
    }

    public String getIdTipoOrdenFinalTag() {
        return idTipoOrdenFinalTag;
    }

    public void setIdTipoOrdenFinalTag(String idTipoOrdenFinalTag) {
        this.idTipoOrdenFinalTag = idTipoOrdenFinalTag;
    }

}