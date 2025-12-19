package com.solucionesweb.tags;

import java.util.*;
import java.io.IOException;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.tagext.TagSupport;

// Importa la clase que posibilita el formateo
import java.text.NumberFormat;

// Importa el Bean de Asignaturas
import com.solucionesweb.losbeans.colaboraciones.ColaboraPago;

import com.solucionesweb.losbeans.fachada.FachadaPagoBean;

public class ListaTotalPagoPlanillaTag extends TagSupport
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

    //
    FachadaPagoBean fachadaPagoBean = new FachadaPagoBean();

    //
    ColaboraPago colaboraPago;

    // Iterador de objetos
    Iterator iteratorBean;

    private void inicializarVariablesDeRetorno() {

      //
      fachadaPagoBean = (FachadaPagoBean)iteratorBean.next();

      // Variable que retornan al JSP
      pageContext.setAttribute("vrPagoVar",fachadaPagoBean.getVrPagoDf0());
      pageContext.setAttribute("vrDescuentoVar",
                                           fachadaPagoBean.getVrDescuentoDf0());
      pageContext.setAttribute("vrRteFuenteVar",
                                           fachadaPagoBean.getVrRteFuenteDf0());
      pageContext.setAttribute("vrRteIvaVar",fachadaPagoBean.getVrRteIvaDf0());
      pageContext.setAttribute("vrRteIcaVar",fachadaPagoBean.getVrRteIcaDf0());
      pageContext.setAttribute("vrRteCreeVar",fachadaPagoBean.getVrRteCreeDf0());


	}

	public int doStartTag() throws JspTagException {

        //
        colaboraPago           = new ColaboraPago();

      	// listaCuentaPlanilla
		Vector vectorBean    = colaboraPago.listaPagoTemporalTotal(getIdLocal(),
                                                          getIdTipoOrden() + 50,
                                                                    getIdLog());

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