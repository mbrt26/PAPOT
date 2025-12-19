package com.solucionesweb.tags;

import java.util.*;
import java.io.IOException;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.tagext.TagSupport;

// Importa la clase que posibilita el formateo
import java.text.NumberFormat;

// Importa el Bean de Asignaturas
import com.solucionesweb.losbeans.colaboraciones.ColaboraDctoBean;

// Importa el Bean de Asignaturas
import com.solucionesweb.losbeans.colaboraciones.ColaboraPago;

// Importa el Bean de Fachada
import com.solucionesweb.losbeans.fachada.FachadaDctoBean;

import com.solucionesweb.losbeans.fachada.FachadaPagoBean;

public class ListaPagoPlanillaTag extends TagSupport
                                     implements IterationTag {

    // Variable que recibe del JSP
    private String idLocalTag;
    private String idTipoOrdenTag;
    private String idLogTag;

    //
    NumberFormat nf = NumberFormat.getNumberInstance();

    // Metodos Tag
    public void setIdLogTag( String idLogTag )
    {
        this.idLogTag = idLogTag ;
    }

    public String getIdLogTag()
    {
        return idLogTag;
    }

    public int getIdLog()
    {
        return new Integer(getIdLogTag()).intValue() ;
    }

    public void setIdTipoOrdenTag( String idTipoOrdenTag )
    {
        this.idTipoOrdenTag = idTipoOrdenTag ;
    }

    public String getIdTipoOrdenTag()
    {
        return idTipoOrdenTag;
    }

    public int getIdTipoOrden()
    {
        return new Integer(getIdTipoOrdenTag()).intValue();
    }

    public void setIdLocalTag( String idLocalTag )
    {
        this.idLocalTag = idLocalTag ;
    }

    public String getIdLocalTag()
    {
        return idLocalTag;
    }

    public int getIdLocal()
    {
        return new Integer(getIdLocalTag()).intValue();
    }

    // Variable para usar el bean de fachada
    FachadaDctoBean fachadaDctoBean;

    //
    FachadaPagoBean fachadaPagoBean = new FachadaPagoBean();

	// Variable para usar el bean de EstadoPcBean
	ColaboraDctoBean colaboraDctoBean;

    //
    ColaboraPago colaboraPago       = new ColaboraPago();

    // Iterador de objetos
    Iterator iteratorBean;

    private void inicializarVariablesDeRetorno() {

      //
      fachadaDctoBean = (FachadaDctoBean)iteratorBean.next();

      // Variable que retornan al JSP
      pageContext.setAttribute("idLocalVar",fachadaDctoBean.getIdLocalStr());
      pageContext.setAttribute("idDctoVar",fachadaDctoBean.getIdDctoSf0());
      pageContext.setAttribute("idTipoOrdenVar",
                                         fachadaDctoBean.getIdTipoOrdenStr());
      pageContext.setAttribute("fechaDctoVar",
                                           fachadaDctoBean.getFechaDctoCorta());
      pageContext.setAttribute("fechaVencimientoVar",
                                       fachadaDctoBean.getFechaVcto());
      pageContext.setAttribute("diasMoraVar",
                                            fachadaDctoBean.getDiasMoraStr());
      pageContext.setAttribute("vrSaldoVar",fachadaDctoBean.getVrSaldoDf0());
      pageContext.setAttribute("vrSaldoSFVar",fachadaDctoBean.getVrSaldoStr());
      pageContext.setAttribute("indicadorVar",
                                             fachadaDctoBean.getIndicadorStr());
      pageContext.setAttribute("idDctoNitCCVar",
                                             fachadaDctoBean.getIdDctoNitCC());
      pageContext.setAttribute("nombreTerceroVar",
                                           fachadaDctoBean.getNombreTercero());

      //
      fachadaPagoBean        = colaboraPago.listaPagoTemporalFCH(
                                          fachadaDctoBean.getIdLocal(),
                                          fachadaDctoBean.getIdTipoOrden() + 50,
                                          fachadaDctoBean.getIdDcto(),
                                          fachadaDctoBean.getIndicador());

      //
      pageContext.setAttribute("vrPagoVar",fachadaPagoBean.getVrPagoDf0());
      pageContext.setAttribute("vrDescuentoVar",
                                           fachadaPagoBean.getVrDescuentoDf0());
      pageContext.setAttribute("vrRteFuenteVar",
                                           fachadaPagoBean.getVrRteFuenteDf0());
      pageContext.setAttribute("vrRteIvaVar",fachadaPagoBean.getVrRteIvaDf0());
      pageContext.setAttribute("vrRteIcaVar",fachadaPagoBean.getVrRteIcaDf0());
      pageContext.setAttribute("vrRteCreeVar",fachadaPagoBean.getVrRteCreeDf0());
      pageContext.setAttribute("nombreMedioVar",
                                              fachadaPagoBean.getNombreMedio());

      //
      double xDiferencia  = fachadaDctoBean.getVrSaldo() - (
                                              fachadaPagoBean.getVrPago()      +
                                              fachadaPagoBean.getVrDescuento() +
                                              fachadaPagoBean.getVrRteFuente() +
                                              fachadaPagoBean.getVrRteIva()    +
                                              fachadaPagoBean.getVrRteIca() +
                                              fachadaPagoBean.getVrRteCree()) ;

      //
      fachadaPagoBean.setVrDiferencia(xDiferencia);

      //
      pageContext.setAttribute("vrDiferenciaDf0Var",
                              fachadaPagoBean.getVrDiferenciaDf0());


	}

	public int doStartTag() throws JspTagException {


      	// Parametros llegados de JSP
        colaboraDctoBean  = new ColaboraDctoBean(getIdLocal(),
                                                 getIdTipoOrden(),
                                                 getIdLog());

      	// listaCuentaPlanilla
		Vector vectorBean    =
                 colaboraDctoBean.listaCuentaPlanilla();

        //
        iteratorBean         = vectorBean.iterator();

        if ( !iteratorBean.hasNext() ) {
            try {
              pageContext.getOut().write("<strong>No hay facturas</strong>");
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
}