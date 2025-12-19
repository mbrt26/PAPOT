package com.solucionesweb.tags;

import com.solucionesweb.losbeans.colaboraciones.ColaboraLucro;
import com.solucionesweb.losbeans.colaboraciones.ColaboraParametroComisionBean;
import java.util.*;
import java.io.IOException;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.tagext.TagSupport;

// Importa el bean de ColaboraResurtidoOrden
import com.solucionesweb.losbeans.colaboraciones.ColaboraReporteDctoBean;

// Importa el bean de FachadaColaboraDctoOrdenBean
import com.solucionesweb.losbeans.fachada.FachadaColaboraReporteDctoBean;
import com.solucionesweb.losbeans.fachada.FachadaPagoBean;
import com.solucionesweb.losbeans.fachada.FachadaParametroComisionBean;
import static javax.servlet.jsp.tagext.IterationTag.EVAL_BODY_AGAIN;
import static javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE;
import static javax.servlet.jsp.tagext.Tag.EVAL_PAGE;
import static javax.servlet.jsp.tagext.Tag.SKIP_BODY;

public class ListaComisionRecaudoTag extends TagSupport implements IterationTag {

    // Variable que recibe del JSP   
    private String idVendedorTag;
    private String fechaInicialTag;
    private String fechaFinalTag;

    // Metodos Tag   
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
    ColaboraParametroComisionBean colaboraLucro;
    //
    FachadaParametroComisionBean fachada;

    // Iterador de objetos
    Iterator iteratorBean;

    private void inicializarVariablesDeRetorno() {

      //
      fachada = (FachadaParametroComisionBean)iteratorBean.next();

      // Variable que retornan al JSP
      pageContext.setAttribute("idDctoVar",fachada.getIdDctoStr());
      pageContext.setAttribute("idClienteVar",fachada.getIdTercero());
      pageContext.setAttribute("nombreTerceroVar",fachada.getNombreTercero());
      pageContext.setAttribute("idFraVar",fachada.getIdDctoStr());
      pageContext.setAttribute("fechaDctoVar",fachada.getFechaFacturaFormato());
      pageContext.setAttribute("idReciboVar",fachada.getIdReciboStr());
      pageContext.setAttribute("fechaPagoVar",fachada.getFechaPagoFormato());
      pageContext.setAttribute("vrPagadoVar",fachada.getVrPagadoDf0());
      pageContext.setAttribute("idDiasVar",fachada.getIdDiasStr());
      pageContext.setAttribute("porcentajeVar",fachada.getPorcentajeStr());      
      pageContext.setAttribute("vrComisionVar",
                                               fachada.getVrComisionDf0());
      pageContext.setAttribute("aliasUsuarioVar",fachada.getNombreUsuario());


  	}

	public int doStartTag() throws JspTagException {

        // Parametros llegados de JSP
        colaboraLucro      = new ColaboraParametroComisionBean();

        //
        

        Vector vectorBean;

        //
        if (getIdVendedor()==0) {

           //
           vectorBean            = colaboraLucro.listaRecaudoEfectivo(getFechaInicialTag(),getFechaFinalTag());

        } else {

           //
           vectorBean            = colaboraLucro.listaRecaudoEfectivoUn(getFechaInicialTag(),
                                                              getFechaFinalTag(),getIdVendedorTag());
           
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