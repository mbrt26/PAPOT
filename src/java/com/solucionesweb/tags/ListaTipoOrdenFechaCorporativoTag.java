package com.solucionesweb.tags;

import java.util.*;
import java.io.IOException;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.tagext.TagSupport;

// Importa la clase que posibilita el formateo
import java.text.NumberFormat;

// Importa el Bean de Asignaturas
import com.solucionesweb.losbeans.colaboraciones.ColaboraReporteDctoBean;

// Importa el Bean de Fachada
import com.solucionesweb.losbeans.fachada.FachadaColaboraReporteDctoBean;

public class ListaTipoOrdenFechaCorporativoTag extends TagSupport
                                    implements IterationTag {

    // Variable que recibe del Tag
    private String idTipoOrdenTag;
    private String fechaOrdenTag;

    NumberFormat nf = NumberFormat.getNumberInstance();

    // Metodos Tag
    public void setIdTipoOrdenTag( String idTipoOrdenTag )
    {
        this.idTipoOrdenTag = idTipoOrdenTag ;
    }

    public String getIdTipoOrdenTag()
    {
        return idTipoOrdenTag;
    }

    public void setFechaOrdenTag( String fechaOrdenTag )
    {
        this.fechaOrdenTag = fechaOrdenTag ;
    }

    public String getFechaOrdenTag()
    {
        return fechaOrdenTag;
    }

    // Variable para usar el bean de fachada
    FachadaColaboraReporteDctoBean fachadaColaboraReporteDctoBean;

	// Variable para usar el bean de EstadoPcBean
	ColaboraReporteDctoBean colaboraReporteDctoBean;

    // Iterador de objetos
    Iterator iteratorBean;

    private void inicializarVariablesDeRetorno() {

      //
      fachadaColaboraReporteDctoBean = (FachadaColaboraReporteDctoBean)iteratorBean.next();

      // Variable que retornan al JSP
      pageContext.setAttribute("idUsuarioVar",nf.format(fachadaColaboraReporteDctoBean.getIdUsuario()));
      pageContext.setAttribute("totalOrdenesVar",nf.format(fachadaColaboraReporteDctoBean.getTotalOrdenes()));
      pageContext.setAttribute("totalPesoTeoricoVar",fachadaColaboraReporteDctoBean.getTotalPesoTeoricoDf2());
      pageContext.setAttribute("totalVrVentaConIvaVar",fachadaColaboraReporteDctoBean.getTotalVrVentaConIvaDf2());
      pageContext.setAttribute("nombreUsuarioVar",fachadaColaboraReporteDctoBean.getNombreUsuario());

	}

	public int doStartTag() throws JspTagException {

      	// Parametros llegados de JSP
        colaboraReporteDctoBean  = new ColaboraReporteDctoBean();

		//  listaTipoOrdenFechaCO
        colaboraReporteDctoBean.setIdTipoOrden(getIdTipoOrdenTag());
        colaboraReporteDctoBean.setFechaInicial(getFechaOrdenTag());
        colaboraReporteDctoBean.setFechaFinal(getFechaOrdenTag());

        //
        Vector vectorBean =
                       colaboraReporteDctoBean.listaTipoOrdenFechaCorporativo();

        iteratorBean      = vectorBean.iterator();

        if ( !iteratorBean.hasNext() ) {
            try {
              pageContext.getOut().write("No existen datos <br/>");
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
		  inicializarVariablesDeRetorno();
		  return EVAL_BODY_AGAIN;
	   }

	   return SKIP_BODY;
	}

	public int doEndTag() throws JspTagException
	{
       return EVAL_PAGE;
	}

    public void doCatch(Throwable t) throws Throwable {
      System.out.println("Error: " + t);
      pageContext.getOut().println("<font color=\"red\">" +
                                   "Invocando <b>doCatch</b> debido a (" +
                                   t + ")</font>");
    }
    public void doFinally() {
    }

}