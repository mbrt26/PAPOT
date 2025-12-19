package com.solucionesweb.tags;

import com.solucionesweb.losbeans.colaboraciones.ColaboraDctoBean;
import com.solucionesweb.losbeans.fachada.FachadaDctoBean;
import java.util.*;
import java.io.IOException;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.tagext.TagSupport;


public class  ListaInventarioRepAjustePositivoTag  extends TagSupport implements IterationTag {

    // Variable que recibe del JSP
    private String idLocalTag;
    private String idTipoOrdenTag;
    private String indicadorInicialTag;
    private String indicadorFinalTag;
    private String idVendedorTag;
    private String fechaInicialTag;
    private String fechaFinalTag;

    //
    ColaboraDctoBean colaboraDctoBean;

    //
    FachadaDctoBean fachada;

    // Iterador de objetos
    Iterator iteratorBean;

    private void inicializarVariablesDeRetorno() {

      //
      fachada = (FachadaDctoBean)iteratorBean.next();


      // Variable que retornan al JSP
      pageContext.setAttribute("idDctoVar",fachada.getIdDctoSf0());
      pageContext.setAttribute("fechaDctoVar",fachada.getFechaDcto());
      pageContext.setAttribute("vrBaseVar",fachada.getVrBaseSf0());
      pageContext.setAttribute("vrIvaVar",fachada.getVrIvaSf0());
      pageContext.setAttribute("vrCostoMvVar",fachada.getVrCostoMVSf0());
      pageContext.setAttribute("nombreUsuarioVar",fachada.getNombreUsuario());

    }

	public int doStartTag() throws JspTagException {

        // Parametros llegados de JSP
        colaboraDctoBean      = new ColaboraDctoBean();

        //
        colaboraDctoBean.setIdLocal(Integer.parseInt(getIdLocalTag()));
        colaboraDctoBean.setIdTipoOrden(Integer.parseInt(getIdTipoOrdenTag()));
        colaboraDctoBean.setFechaInicialStr(getFechaInicialTag());
        colaboraDctoBean.setFechaFinalStr(getFechaFinalTag());
        colaboraDctoBean.setIndicadorInicial(Integer.parseInt(getIndicadorInicialTag()));
        colaboraDctoBean.setIndicadorFinal(Integer.parseInt(getIndicadorFinalTag()));

        //
        Vector vectorBean = colaboraDctoBean.listaAjuste();

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

    public String getIdTipoOrdenTag() {
        return idTipoOrdenTag;
    }

    public void setIdTipoOrdenTag(String idTipoOrdenInicialTag) {
        this.idTipoOrdenTag = idTipoOrdenInicialTag;
    }

  
}