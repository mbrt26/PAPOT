package com.solucionesweb.tags;

import java.util.*;
import java.io.IOException;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.tagext.TagSupport;

// Importa la clase que posibilita el formateo
//import java.text.NumberFormat;

// Importa el Bean de UsuarioRutaBean
import com.solucionesweb.losbeans.negocio.UsuarioRutaBean;

// Importa el Bean de Asignaturas
import com.solucionesweb.losbeans.colaboraciones.ColaboraReporteDctoBean;

// Importa el Bean de Fachada
import com.solucionesweb.losbeans.fachada.FachadaColaboraReporteDctoBean;

public class ListaTotalTipoOrdenFechaCOTag extends TagSupport
                                           implements IterationTag {

    // Variable que recibe del Tag
    private String idUsuarioTag;
    private String idTipoOrdenTag;
    private String fechaInicialTag;
    private String fechaFinalTag;

    //NumberFormat nf = NumberFormat.getNumberInstance();

    // Metodos Tag
    public void setIdUsuarioTag( String idUsuarioTag )
    {
        this.idUsuarioTag = idUsuarioTag ;
    }

    public String getIdUsuarioTag()
    {
        return idUsuarioTag;
    }

    public void setIdTipoOrdenTag( String idTipoOrdenTag )
    {
        this.idTipoOrdenTag = idTipoOrdenTag ;
    }

    public String getIdTipoOrdenTag()
    {
        return idTipoOrdenTag;
    }

    public void setFechaInicialTag( String fechaInicialTag )
    {
        this.fechaInicialTag = fechaInicialTag ;
    }

    public String getFechaInicialTag()
    {
        return fechaInicialTag;
    }

    public void setFechaFinalTag( String fechaFinalTag )
    {
        this.fechaFinalTag = fechaFinalTag ;
    }

    public String getFechaFinalTag()
    {
        return fechaFinalTag;
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
      pageContext.setAttribute("idUsuarioVendorVar",fachadaColaboraReporteDctoBean.getIdUsuarioStr());
      pageContext.setAttribute("idTipoOrdenVar",fachadaColaboraReporteDctoBean.getIdTipoOrdenStr());
      pageContext.setAttribute("idUsuarioVar",fachadaColaboraReporteDctoBean.getIdUsuarioDf0());
      pageContext.setAttribute("totalOrdenesVar",fachadaColaboraReporteDctoBean.getTotalOrdenesDf0());
      pageContext.setAttribute("totalPesoTeoricoVar",fachadaColaboraReporteDctoBean.getTotalPesoTeoricoDf2());
      pageContext.setAttribute("totalVrVentaConIvaVar",fachadaColaboraReporteDctoBean.getTotalVrVentaConIvaDf2());
      pageContext.setAttribute("nombreUsuarioVar",fachadaColaboraReporteDctoBean.getNombreUsuario());

	}

	public int doStartTag() throws JspTagException {

      	// Parametros llegados de JSP
        colaboraReporteDctoBean  = new ColaboraReporteDctoBean();

		//  listaTipoOrdenFechaCO
		colaboraReporteDctoBean.setIdUsuario(getIdUsuarioTag());
        colaboraReporteDctoBean.setIdTipoOrden(getIdTipoOrdenTag());
        colaboraReporteDctoBean.setFechaInicial(getFechaInicialTag());
        colaboraReporteDctoBean.setFechaFinal(getFechaFinalTag());

        //
        UsuarioRutaBean usuarioRutaBean = new UsuarioRutaBean();

        usuarioRutaBean.setIdUsuario(getIdUsuarioTag());

        //
        String idRuta     = usuarioRutaBean.listaRutaUsuario();

        //
        Vector vectorBean = colaboraReporteDctoBean.totalTipoOrdenRuta(idRuta);

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