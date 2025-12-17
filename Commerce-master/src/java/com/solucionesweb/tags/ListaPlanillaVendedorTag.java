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

public class ListaPlanillaVendedorTag extends TagSupport
                                     implements IterationTag {

    // Variable que recibe del JSP
    private String idLocalTag;
    private String idTipoOrdenTag;
    private String idVendedorTag;
    private String fechaPagoTag;

    //
    NumberFormat nf = NumberFormat.getNumberInstance();

    // Metodos Tag
    public void setIdVendedorTag( String idVendedorTag )
    {
        this.idVendedorTag = idVendedorTag ;
    }

    public String getIdVendedorTag()
    {
        return idVendedorTag;
    }

    public void setIdTipoOrdenTag( String idTipoOrdenTag )
    {
        this.idTipoOrdenTag = idTipoOrdenTag ;
    }

    public String getIdTipoOrdenTag()
    {
        return idTipoOrdenTag;
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

    public void setFechaPagoTag( String fechaPagoTag )
    {
        this.fechaPagoTag = fechaPagoTag ;
    }

    public String getFechaPagoTag()
    {
        return fechaPagoTag;
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
      pageContext.setAttribute("idVendedorVar",
                                            fachadaDctoBean.getIdVendedorStr());
      pageContext.setAttribute("idPlanillaVar",
                                            fachadaDctoBean.getIdPlanillaStr());
      pageContext.setAttribute("fechaPagoVar",
                                         fachadaDctoBean.getFechaPagoFormato());
      pageContext.setAttribute("idDctoVar",fachadaDctoBean.getIdDctoStr());
      pageContext.setAttribute("vrPagoVar",fachadaDctoBean.getVrPagoDf0());
      pageContext.setAttribute("vrDescuentoVar",
                                           fachadaDctoBean.getVrDescuentoDf0());
      pageContext.setAttribute("vrRteFuenteVar",
                                           fachadaDctoBean.getVrRteFuenteDf0());
      pageContext.setAttribute("vrRteIvaVar",fachadaDctoBean.getVrRteIvaDf0());
      pageContext.setAttribute("vrRteIcaVar",fachadaDctoBean.getVrRteIcaDf0());
      pageContext.setAttribute("aliasUsuarioVar",
                                             fachadaDctoBean.getAliasUsuario());

	}

	public int doStartTag() throws JspTagException {


      	// Parametros llegados de JSP
        colaboraDctoBean  = new ColaboraDctoBean();

        colaboraDctoBean.setIdLocal(getIdLocal());
        colaboraDctoBean.setIdVendedor(getIdVendedorTag());
        colaboraDctoBean.setFechaInicialStr(getFechaPagoTag());
        colaboraDctoBean.setFechaFinalStr(getFechaPagoTag());

      	// listaCuentaPlanilla
		Vector vectorBean    =
                 colaboraDctoBean.listaIngresoCajaVendedor(getIdTipoOrdenTag());

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