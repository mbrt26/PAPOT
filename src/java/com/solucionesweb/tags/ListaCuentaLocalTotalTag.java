package com.solucionesweb.tags;

import java.util.*;
import java.io.IOException;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.tagext.TagSupport;

// Importa el Bean de Asignaturas
import com.solucionesweb.losbeans.colaboraciones.ColaboraDctoBean;

// Importa el Bean de Fachada
import com.solucionesweb.losbeans.fachada.FachadaDctoBean;

public class ListaCuentaLocalTotalTag extends TagSupport
                                     implements IterationTag {

    // Variable que recibe del JSP
    private String idClienteTag;
    private String idTipoTerceroTag;
    private String idTipoOrdenTag;
    private String idLocalTag;

    // Metodos Tag
    public void setIdClienteTag( String idClienteTag )
    {
        this.idClienteTag = idClienteTag ;
    }

    public String getIdClienteTag()
    {
        return idClienteTag;
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
        return  new Integer(getIdTipoOrdenTag()).intValue();
    }

    public void setIdTipoTerceroTag( String idTipoTerceroTag )
    {
        this.idTipoTerceroTag = idTipoTerceroTag ;
    }

    public String getIdTipoTerceroTag()
    {
        return idTipoTerceroTag;
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
        return  new Integer(getIdLocalTag()).intValue();
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

      //
      pageContext.setAttribute("tipoCarteraVar",
                                              fachadaDctoBean.getTipoCartera());
      pageContext.setAttribute("numeroDctosVar",
                                            fachadaDctoBean.getNumeroDctoDf0());
      pageContext.setAttribute("vrSaldoVar",fachadaDctoBean.getVrSaldoDf0());

	}

	public int doStartTag() throws JspTagException {

      	// Parametros llegados de JSP
        colaboraDctoBean  = new ColaboraDctoBean(getIdLocal(),
                                                 getIdTipoOrden(),
                                                 getIdClienteTag());

        //
		Vector vectorBean    = colaboraDctoBean.listaCxCTotal();


        //
        iteratorBean         = vectorBean.iterator();

        if ( !iteratorBean.hasNext() ) {
            try {
              pageContext.getOut().write("<strong><font face='Verdana' size='1'>No hay facturas</font></strong>");
          //    pageContext.getOut().write("<br/>");
		  //	  pageContext.getOut().write("Por favor comunique al administrador del sistema que no existen datos <br/>");
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