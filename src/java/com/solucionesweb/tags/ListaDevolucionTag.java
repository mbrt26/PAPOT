package com.solucionesweb.tags;

import java.util.*;
import java.io.IOException;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.tagext.TagSupport;

// Importa el bean de ColaboraInventarioBean
import com.solucionesweb.losbeans.colaboraciones.ColaboraDctoOrdenDetalleBean;

// Importa el bean de FachadaInventarioBean
import com.solucionesweb.losbeans.fachada.FachadaDctoOrdenDetalleBean;

public class ListaDevolucionTag
                                 extends TagSupport implements IterationTag {

    // Variable que recibe del JSP
    private String idTipoOrdenTag;
    private String idDctoTag;
    private String idLocalTag;
    private String indicadorTag;

    // Metodos Tag
    public void setIdTipoOrdenTag( String idTipoOrdenTag )
    {
        this.idTipoOrdenTag = idTipoOrdenTag ;
    }

    public String getIdTipoOrdenTag()
    {
        return idTipoOrdenTag;
    }

    public void setIdDctoTag( String idDctoTag )
    {
        this.idDctoTag = idDctoTag ;
    }

    public String getIdDctoTag()
    {
        return idDctoTag;
    }

    public int getIdDcto()
    {
        return new Integer(getIdDctoTag());
    }

    public void setIdLocalTag( String idLocalTag )
    {
        this.idLocalTag = idLocalTag ;
    }

    public String getIdLocalTag()
    {
        return idLocalTag;
    }

    public void setIndicadorTag( String indicadorTag )
    {
        this.indicadorTag = indicadorTag ;
    }

    public String getIndicadorTag()
    {
        return indicadorTag;
    }

    public int getIndicador()
    {
        return new Integer(getIndicadorTag());
    }

	//
    ColaboraDctoOrdenDetalleBean	colaboraDctoOrdenDetalleBean;

    //
    FachadaDctoOrdenDetalleBean fachadaDctoOrdenDetalleBean;


    // Iterador de objetos
    Iterator iteratorBean;

    private void inicializarVariablesDeRetorno() {

      //
      fachadaDctoOrdenDetalleBean =
                               (FachadaDctoOrdenDetalleBean)iteratorBean.next();

      // Variable que retornan al JSP
      pageContext.setAttribute("idPluVar",
                                  fachadaDctoOrdenDetalleBean.getIdPluStr());
      pageContext.setAttribute("cantidadVar",
                                  fachadaDctoOrdenDetalleBean.getCantidadStr());
      pageContext.setAttribute("nombrePluVar",
                                  fachadaDctoOrdenDetalleBean.getNombrePlu());
      pageContext.setAttribute("vrVentaUnitarioVar",
                           fachadaDctoOrdenDetalleBean.getVrVentaUnitarioStr());
      pageContext.setAttribute("porcentajeIvaVar",
                           fachadaDctoOrdenDetalleBean.getPorcentajeIvaStr());
      pageContext.setAttribute("vrCostoVar",
                           fachadaDctoOrdenDetalleBean.getVrCostoStr());
      pageContext.setAttribute("vrVentaUnitarioDf0Var",
                           fachadaDctoOrdenDetalleBean.getVrVentaUnitarioDf0());
      pageContext.setAttribute("vrSubtotalVentaDf0Var",
                           fachadaDctoOrdenDetalleBean.getVrSubtotalVentaDf0());
      pageContext.setAttribute("vrCostoConIvaDf0Var",
                           fachadaDctoOrdenDetalleBean.getVrCostoConIvaDf0());
      pageContext.setAttribute("vrCostoConIvaVar",
                           fachadaDctoOrdenDetalleBean.getVrCostoConIvaStr());

  	}

	public int doStartTag() throws JspTagException {

        //
        colaboraDctoOrdenDetalleBean = new ColaboraDctoOrdenDetalleBean();

        //
        colaboraDctoOrdenDetalleBean.setIdLocal(getIdLocalTag());
        colaboraDctoOrdenDetalleBean.setIdTipoOrden(getIdTipoOrdenTag());

        Vector vectorBean    =
                      colaboraDctoOrdenDetalleBean.listaDevolucion(getIdDcto(),
                                                                getIndicador());


        //
        iteratorBean         = vectorBean.iterator();

        //
        if ( !iteratorBean.hasNext() ) {
            try {
              pageContext.getOut().write("La consulta no devolvio ningun dato <br/>");
              pageContext.getOut().write("<br/>");
			  pageContext.getOut().write("Por favor comunique al administrador<br/>");
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

          //
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