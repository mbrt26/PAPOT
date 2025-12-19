package com.solucionesweb.tags;

import java.util.*;
import java.io.IOException;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.tagext.TagSupport;

// Importa el Bean de Asignaturas
import com.solucionesweb.losbeans.colaboraciones.ColaboraReporteDctoBean;

// Importa el Bean de Fachada
import com.solucionesweb.losbeans.fachada.FachadaColaboraReporteDctoBean;

public class ListaPedidoFechaLocalTag extends TagSupport
                                            implements IterationTag {

    // Variable que recibe del Tag
    private String idTipoOrdenCadenaTag;
    private String fechaInicialTag;
    private String fechaFinalTag;
    private String idClienteTag;
    private String idLocalTag;
    private String indicadorInicialTag;
    private String indicadorFinalTag;

    // Metodos Tag
    public void setIdTipoOrdenCadenaTag( String idTipoOrdenCadenaTag )
    {
        this.idTipoOrdenCadenaTag = idTipoOrdenCadenaTag ;
    }

    public String getIdTipoOrdenCadenaTag()
    {
        return idTipoOrdenCadenaTag;
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

    public void setIdClienteTag( String idClienteTag )
    {
        this.idClienteTag = idClienteTag ;
    }

    public String getIdClienteTag()
    {
        return idClienteTag;
    }

    public void setIdLocalTag( String idLocalTag )
    {
        this.idLocalTag = idLocalTag ;
    }

    public String getIdLocalTag()
    {
        return idLocalTag;
    }

    public void setIndicadorInicialTag( String indicadorInicialTag )
    {
        this.indicadorInicialTag = indicadorInicialTag ;
    }

    public String getIndicadorInicialTag()
    {
        return indicadorInicialTag;
    }

    public void setIndicadorFinalTag( String indicadorFinalTag )
    {
        this.indicadorFinalTag = indicadorFinalTag ;
    }

    public String getIndicadorFinalTag()
    {
        return indicadorFinalTag;
    }

    // Variable para usar el bean de fachada
    FachadaColaboraReporteDctoBean fachadaColaboraReporteDctoBean;

	// Variable para usar el bean de EstadoPcBean
	ColaboraReporteDctoBean colaboraReporteDctoBean;

    // Iterador de objetos
    Iterator iteratorBean;

    private void inicializarVariablesDeRetorno() {

      //
      fachadaColaboraReporteDctoBean =
                            (FachadaColaboraReporteDctoBean)iteratorBean.next();

      // Variable que retornan al JSP
      pageContext.setAttribute("idLocalVar",
                              fachadaColaboraReporteDctoBean.getIdLocalStr());
      pageContext.setAttribute("idTipoOrdenVar",
                            fachadaColaboraReporteDctoBean.getIdTipoOrdenStr());
      pageContext.setAttribute("idOrdenVar",
                                fachadaColaboraReporteDctoBean.getIdOrdenStr());
      pageContext.setAttribute("idDctoVar",
                                fachadaColaboraReporteDctoBean.getIdDctoStr());
      pageContext.setAttribute("indicadorVar",
                              fachadaColaboraReporteDctoBean.getIndicadorDf0());
      pageContext.setAttribute("fechaDctoVar",
                        fachadaColaboraReporteDctoBean.getFechaDctoSqlServer());
      pageContext.setAttribute("vrBaseVar",
                                 fachadaColaboraReporteDctoBean.getVrBaseDf0());
      pageContext.setAttribute("vrIvaVar",
                                  fachadaColaboraReporteDctoBean.getVrIvaDf0());
      pageContext.setAttribute("numeroArticuloVar",
                         fachadaColaboraReporteDctoBean.getNumeroArticuloDf0());
      pageContext.setAttribute("vrVentaConIvaVar",
                         fachadaColaboraReporteDctoBean.getVrVentaConIvaDf0());
      pageContext.setAttribute("fechaDctoFormatoVar",
                        fachadaColaboraReporteDctoBean.getFechaDctoFormato());
      pageContext.setAttribute("nombreTipoOrdenVar",
                  fachadaColaboraReporteDctoBean.getNombreTipoOrdenMayuscula());
      pageContext.setAttribute("idDctoSf0Var",
                                 fachadaColaboraReporteDctoBean.getIdDctoStr());
      pageContext.setAttribute("idDctoNitCCVar",
                               fachadaColaboraReporteDctoBean.getIdDctoNitCC());
      pageContext.setAttribute("vrRteFuenteVar",
                            fachadaColaboraReporteDctoBean.getVrRteFuenteDf0());
      pageContext.setAttribute("vrDescuentoVar",
                            fachadaColaboraReporteDctoBean.getVrDescuentoDf0());
      pageContext.setAttribute("vrRteIvaVar",
                               fachadaColaboraReporteDctoBean.getVrRteIvaDf0());
      pageContext.setAttribute("vrRteIcaVar",
                               fachadaColaboraReporteDctoBean.getVrRteIcaDf0());
      pageContext.setAttribute("vrFacturaDf0Var",
                               fachadaColaboraReporteDctoBean.getVrFacturaDf0());
      pageContext.setAttribute("idDctoCruceVar",
                               fachadaColaboraReporteDctoBean.getIdDctoCruceStr());
      pageContext.setAttribute("idOrdenOrigenVar",
                               fachadaColaboraReporteDctoBean.getIdOrdenOrigenStr());
      pageContext.setAttribute("dctoDianVar",
                               fachadaColaboraReporteDctoBean.getDctoDianStr());
      pageContext.setAttribute("urlDianVar",
                               fachadaColaboraReporteDctoBean.getUrlDian());

	}

	public int doStartTag() throws JspTagException {

      	// Parametros llegados de JSP
        colaboraReporteDctoBean  = new ColaboraReporteDctoBean();

		//  listaFechasTipoOrdenCliente
        colaboraReporteDctoBean.setIdTipoOrdenCadena(getIdTipoOrdenCadenaTag());
        colaboraReporteDctoBean.setFechaInicial(getFechaInicialTag());
        colaboraReporteDctoBean.setFechaFinal(getFechaFinalTag());
        colaboraReporteDctoBean.setIdCliente(getIdClienteTag());
        colaboraReporteDctoBean.setIdLocal(getIdLocalTag());
        colaboraReporteDctoBean.setIndicadorInicial(getIndicadorInicialTag());
        colaboraReporteDctoBean.setIndicadorFinal(getIndicadorFinalTag());
       

        //
        Vector vectorBean =
                          colaboraReporteDctoBean.listaOrdenPeriodo();

        //
        iteratorBean      = vectorBean.iterator();

        //
        if ( !iteratorBean.hasNext() ) {
            try {
              pageContext.getOut().write("&nbsp;");
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