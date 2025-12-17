

package com.solucionesweb.tags;

import java.util.*;
import java.io.IOException;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.tagext.TagSupport;


// Importa el bean de FachadaColaboraDctoOrdenBean
import com.solucionesweb.losbeans.colaboraciones.ColaboraIndicadorRentabilidad;
import com.solucionesweb.losbeans.fachada.FachadaDctoBean;


public class ListaAudtoriaRepRentabilidadClienteTag extends TagSupport
                                            implements IterationTag {

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
    ColaboraIndicadorRentabilidad colaboraIndicadorRentabilidad;

    //
    FachadaDctoBean fachada;

    // Iterador de objetos
    Iterator iteratorBean;

    private void inicializarVariablesDeRetorno() {

      //
      fachada = (FachadaDctoBean)iteratorBean.next();


      // Variable que retornan al JSP
      pageContext.setAttribute("idClienteVar",fachada.getIdCliente());
      pageContext.setAttribute("idLocalVar",fachada.getIdLocalStr());
      pageContext.setAttribute("nombreTerceroVar",fachada.getNombreTercero());
      pageContext.setAttribute("vrBaseVar",fachada.getVrBaseDecimal());
      pageContext.setAttribute("vrCostoIndVar",fachada.getVrCostoINDDecimal());
      pageContext.setAttribute("vrIvaVar",fachada.getVrIvaDecimal());
      pageContext.setAttribute("vrCostoMvVar",fachada.getVrCostoMVDecimal());
      pageContext.setAttribute("margenClienteVar",fachada.getMargenClientePorcentaje());

      
  	}

	public int doStartTag() throws JspTagException {

        // Parametros llegados de JSP
        colaboraIndicadorRentabilidad      = new ColaboraIndicadorRentabilidad();

        //
        colaboraIndicadorRentabilidad.setIdLocal(getIdLocalTag());
        colaboraIndicadorRentabilidad.setIdTipoOrdenINI(Integer.parseInt(getIdTipoOrdenInicialTag()));
        colaboraIndicadorRentabilidad.setIdTipoOrdenFIN(Integer.parseInt(getIdTipoOrdenFinalTag()));
        colaboraIndicadorRentabilidad.setFechaInicialStr(getFechaInicialTag());
        colaboraIndicadorRentabilidad.setFechaFinalStr(getFechaFinalTag());
        colaboraIndicadorRentabilidad.setIndicadorInicial(Integer.parseInt(getIndicadorInicialTag()));
        colaboraIndicadorRentabilidad.setIndicadorFinal(Integer.parseInt(getIndicadorFinalTag()));

        



        //
        Vector vectorBean = 
             colaboraIndicadorRentabilidad.listaIndicadorRentabilidadCliente();

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
// Metodos Tag
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