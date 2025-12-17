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

public class ListaCxCDetalleTag extends TagSupport
                                     implements IterationTag {

    // Variable que recibe del JSP
    private String idLocalTag;
    private String idTipoOrdenTag;
    private String indicadorInicialTag;
    private String indicadorFinalTag;
    private String idVendedorTag;
    private String fechaCorteTag;

    //
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

    public void setIndicadorInicialTag( String indicadorInicialTag )
    {
        this.indicadorInicialTag = indicadorInicialTag ;
    }

    public String getIndicadorInicialTag()
    {
        return indicadorInicialTag;
    }

    public int getIndicadorInicial()
    {
        return new Integer(getIndicadorInicialTag()).intValue();
    }

    public void setIndicadorFinalTag( String indicadorFinalTag )
    {
        this.indicadorFinalTag = indicadorFinalTag ;
    }

    public String getIndicadorFinalTag()
    {
        return indicadorFinalTag;
    }

    public int getIndicadorFinal()
    {
        return new Integer(getIndicadorFinalTag()).intValue();
    }

    public void setIdVendedorTag( String idVendedorTag )
    {
        this.idVendedorTag = idVendedorTag ;
    }

    public String getIdVendedorTag()
    {
        return idVendedorTag;
    }

    public double getIdVendedor()
    {
        return new Double(getIdVendedorTag()).doubleValue();
    }

    public String getFechaCorteTag() {
        return fechaCorteTag;
    }

    public void setFechaCorteTag(String fechaCorteTag) {
        this.fechaCorteTag = fechaCorteTag;
    }

    public String getFechaCorteSqlServer() {

            return getFechaCorteTag().substring(0, 4) +
                   getFechaCorteTag().substring(5, 7) +
                   getFechaCorteTag().substring(8, 10);
    }

    // Variable para usar el bean de fachada
    FachadaDctoBean fachadaDctoBean;

	// Variable para usar el bean de EstadoPcBean
	

    // Iterador de objetos
    Iterator iteratorBean;

    private void inicializarVariablesDeRetorno() {

      //
      fachadaDctoBean = (FachadaDctoBean)iteratorBean.next();

      // Variable que retornan al JSP
      pageContext.setAttribute("idLocalVar",fachadaDctoBean.getIdLocalStr());
      pageContext.setAttribute("idClienteVar",fachadaDctoBean.getIdCliente());
      pageContext.setAttribute("idTipoOrdenVar",
                                         fachadaDctoBean.getIdTipoOrdenStr());
      pageContext.setAttribute("idDctoVar",fachadaDctoBean.getIdDctoStr());
      pageContext.setAttribute("fechaDctoVar",fachadaDctoBean.getFechaDcto());
      pageContext.setAttribute("fechaVencimientoVar",
                                       fachadaDctoBean.getFechaVcto());
      pageContext.setAttribute("diasMoraVar",
                                            fachadaDctoBean.getDiasMoraStr());
      pageContext.setAttribute("vrSaldoVar",fachadaDctoBean.getVrSaldoDf0());
      pageContext.setAttribute("indicadorVar",
                                             fachadaDctoBean.getIndicadorStr());
      pageContext.setAttribute("idDctoNitCCVar",
                                             fachadaDctoBean.getIdDctoNitCC());      
      pageContext.setAttribute("nombreTerceroVar",
                                            fachadaDctoBean.getNombreTercero());
      pageContext.setAttribute("edadFraVar",fachadaDctoBean.getEdadFraStr());
      pageContext.setAttribute("nombreVendedorVar",
                                           fachadaDctoBean.getNombreVendedor());


	}

	public int doStartTag() throws JspTagException {

      	// Parametros llegados de JSP
        ColaboraDctoBean colaboraDctoBean = new ColaboraDctoBean(getIdLocal(),
                                                                 getIdTipoOrden(),
                                                                 getIndicadorInicial(),
                                                                 getIndicadorFinal(),
                                                                 getIdVendedor());

      	// listaCuentaDetalladoCliente
		Vector vectorBean    =
                 colaboraDctoBean.listaCxCDetalladoAll(
                                                      getFechaCorteSqlServer());

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