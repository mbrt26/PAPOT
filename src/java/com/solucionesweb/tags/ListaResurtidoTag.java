package com.solucionesweb.tags;

import java.util.*;
import java.io.IOException;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.tagext.TagSupport;

// Importa el bean de ColaboraResurtido
import com.solucionesweb.losbeans.colaboraciones.ColaboraResurtido;

// Importa el bean de FachadaDctoOrdenDetalleBean
import com.solucionesweb.losbeans.fachada.FachadaDctoOrdenDetalleBean;

public class ListaResurtidoTag
                                 extends TagSupport implements IterationTag {

    // Variable que recibe del JSP
    private String idLocalTag;
    private String idTerceroTag;
    private String idLogTag;
    private String idTipoOrdenTag;

    // Metodos Tag
    public void setIdLocalTag( String idLocalTag )
    {
        this.idLocalTag = idLocalTag ;
    }

    public String getIdLocalTag()
    {
        return idLocalTag;
    }

    public void setIdTerceroTag( String idTerceroTag )
    {
        this.idTerceroTag = idTerceroTag ;
    }

    public String getIdTerceroTag()
    {
        return idTerceroTag;
    }

    public void setIdLogTag(String idLogTag) {
        this.idLogTag = idLogTag;
    }

    public String getIdLogTag() {
        return idLogTag;
    }

    public int getIdLog() {
        return new Integer(getIdLogTag());
    }

    public void setIdTipoOrdenTag(String idTipoOrdenTag) {
        this.idTipoOrdenTag = idTipoOrdenTag;
    }

    public String getIdTipoOrdenTag() {
        return idTipoOrdenTag;
    }

    public int getIdTipoOrden() {
        return new Integer(getIdTipoOrdenTag());
    }

	//
    ColaboraResurtido	colaboraResurtido;

    //
    FachadaDctoOrdenDetalleBean fachada;

    // Iterador de objetos
    Iterator iteratorBean;

    private void inicializarVariablesDeRetorno() {

      //
      fachada = (FachadaDctoOrdenDetalleBean)iteratorBean.next();


      //
      double xVrCostoNegociado = fachada.getVrCostoNegociado();
      double xVrCosto          = fachada.getVrCosto();
      double xCantidadPedido   = fachada.getCantidadPedido();

      //
      if (fachada.getVrCostoNegociado() != fachada.getVrCosto()) {

          //
          fachada.setVrCosto(xVrCostoNegociado);

      }

      //
      if (fachada.getVrCostoNegociado() == 0) {

          //
          fachada.setVrCosto(xVrCosto);

      }

      // Variable que retornan al JSP
      pageContext.setAttribute("idPluVar",fachada.getIdPluStr());
      pageContext.setAttribute("nombrePluVar",fachada.getNombrePlu());
      pageContext.setAttribute("vrCostoVar",fachada.getVrCostoDf0());
      pageContext.setAttribute("nombreCategoriaVar",
                                                  fachada.getNombreCategoria());
      pageContext.setAttribute("nombreMarcaVar",fachada.getNombreMarca());
      pageContext.setAttribute("vrVentaUnitarioVar",
                                               fachada.getVrVentaUnitarioDf0());
      pageContext.setAttribute("existenciaActualVar",
                                              fachada.getExistenciaDf0());
      pageContext.setAttribute("existenciaActualSf0Var",
                                              fachada.getExistenciaStr());
      pageContext.setAttribute("cantidadPedidoVar",
                                                fachada.getCantidadPedidoDf2());
      pageContext.setAttribute("cantidadPedidoStrVar",
                                                fachada.getCantidadPedidoStr());
      pageContext.setAttribute("vrCostoPedidoVar",
                                                 fachada.getVrCostoPedidoDf0());
      pageContext.setAttribute("vrCostoStrVar",fachada.getVrCostoStr());
      pageContext.setAttribute("vrCostoNegociadoVar",
                                              fachada.getVrCostoNegociadoStr());

  	}

	public int doStartTag() throws JspTagException {

        // Parametros llegados de JSP
        colaboraResurtido            = new ColaboraResurtido();

        //
        int xIdBodega                = 1;

        //
        colaboraResurtido.setIdLocal(getIdLocalTag());
        colaboraResurtido.setIdLog(getIdLogTag());
        colaboraResurtido.setIdTipoOrden(getIdTipoOrdenTag());
        colaboraResurtido.setIdBodega(xIdBodega);

        //
        Vector vectorBean            =
                                colaboraResurtido.pluTercero(getIdTerceroTag());
        
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