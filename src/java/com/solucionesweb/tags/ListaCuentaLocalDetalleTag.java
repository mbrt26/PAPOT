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

public class ListaCuentaLocalDetalleTag extends TagSupport
                                     implements IterationTag {

    // Variable que recibe del JSP
    private String idClienteTag;
    private String idTipoTerceroTag;
    private String idTipoOrdenTag;
    private String idLocalTag;

    String letraEstilo = "";

    //
    NumberFormat nf = NumberFormat.getNumberInstance();

    // Metodos Tag
    public void setIdClienteTag( String idClienteTag )
    {
        this.idClienteTag = idClienteTag ;
    }

    public String getIdClienteTag()
    {
        return idClienteTag;
    }

    public void setIdTipoTerceroTag( String idTipoTerceroTag )
    {
        this.idTipoTerceroTag = idTipoTerceroTag ;
    }

    public String getIdTipoTerceroTag()
    {
        return idTipoTerceroTag;
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

      //

      letraEstilo = "letraDetalle";
     
       //System.out.println(fachadaPagoBean.getDiasMora());

        if (fachadaDctoBean.getDiasMora()>0) {

           //
           letraEstilo = "letraResaltadaGrande";

        }

      // Variable que retornan al JSP
      pageContext.setAttribute("idLocalVar",fachadaDctoBean.getIdLocalStr());
      pageContext.setAttribute("idDctoVar",fachadaDctoBean.getIdDctoSf0());
      pageContext.setAttribute("idOrdenVar",fachadaDctoBean.getIdOrdenStr());
      pageContext.setAttribute("idTipoOrdenVar",
                                         fachadaDctoBean.getIdTipoOrdenStr());
      pageContext.setAttribute("fechaDctoVar",fachadaDctoBean.getFechaDcto());
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
      pageContext.setAttribute("fe",String.valueOf(fachadaDctoBean.getEdad()));
      pageContext.setAttribute("letraEstiloVar",letraEstilo);

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

      //
      double xDiferencia  = fachadaDctoBean.getVrSaldo() - (
                                              fachadaPagoBean.getVrPago()      +
                                              fachadaPagoBean.getVrDescuento() +
                                              fachadaPagoBean.getVrRteFuente() +
                                              fachadaPagoBean.getVrRteIva()    +
                                              fachadaPagoBean.getVrRteIca()    +
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
                                                 getIdClienteTag());

      	// listaCuentaDetalladoCliente
	Vector vectorBean    =
                 colaboraDctoBean.listaCuentaDetalladoCliente();

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