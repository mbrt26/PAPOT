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

// Importa el Bean de Fachada
import com.solucionesweb.losbeans.fachada.FachadaDctoBean;

public class ListaCuentaDetalladoClienteTag extends TagSupport
                                     implements IterationTag {

    // Variable que recibe del JSP
    private String idClienteTag;
    private String idTipoTerceroTag;
    private String idTipoOrdenTag;

    //
    int xIdLocal     = 1;

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

    // Variable para usar el bean de fachada
    FachadaDctoBean fachadaDctoBean;

	// Variable para usar el bean de EstadoPcBean
	ColaboraDctoBean colaboraDctoBean;

    // Iterador de objetos
    Iterator iteratorBean;

    private void inicializarVariablesDeRetorno() {

      //
      fachadaDctoBean = (FachadaDctoBean)iteratorBean.next();

      // Variable que retornan al JSP
      pageContext.setAttribute("idLocalVar",fachadaDctoBean.getIdLocalStr());
      pageContext.setAttribute("idDctoVar",fachadaDctoBean.getIdDctoStr());
      pageContext.setAttribute("idTipoOrdenVar",
                                         fachadaDctoBean.getIdTipoOrdenStr());
      pageContext.setAttribute("fechaDctoVar",fachadaDctoBean.getFechaDcto());
      pageContext.setAttribute("fechaVencimientoVar",
                                       fachadaDctoBean.getFechaVcto());
      pageContext.setAttribute("diasMoraVar",
                                            fachadaDctoBean.getDiasMoraStr());
      pageContext.setAttribute("vrSaldoVar",
                                     nf.format(fachadaDctoBean.getVrSaldo()));
      pageContext.setAttribute("vrSaldoSFVar",fachadaDctoBean.getVrSaldoStr());
      pageContext.setAttribute("indicadorVar",
                                             fachadaDctoBean.getIndicadorStr());
      pageContext.setAttribute("idDctoNitCCVar",
                                             fachadaDctoBean.getIdDctoNitCC());

	}

	public int doStartTag() throws JspTagException {


      	// Parametros llegados de JSP
        colaboraDctoBean  = new ColaboraDctoBean();

        //
        colaboraDctoBean.setIdCliente(getIdClienteTag());
        colaboraDctoBean.setIdTipoOrden(getIdTipoTerceroTag());
        colaboraDctoBean.setIdLocal(xIdLocal);

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