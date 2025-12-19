package com.solucionesweb.tags;

import java.util.*;
import java.io.IOException;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.tagext.TagSupport;

// Importa el Bean de ColaboraDctoOrdenDetalleBean
import com.solucionesweb.losbeans.colaboraciones.ColaboraOrdenDetalleBean;

// Importa el Bean de ColaboraArticuloAtributoBean
import com.solucionesweb.losbeans.colaboraciones.ColaboraArticuloAtributoBean;

// Importa el Bean de Fachada FachadaDctoOrdenDetalleBean
import com.solucionesweb.losbeans.fachada.FachadaDctoOrdenDetalleBean;

// Importa el Bean de FachadaColaboraArticuloAtributoBean
import com.solucionesweb.losbeans.fachada.FachadaColaboraArticuloAtributoBean;

public class ListaClienteCotizacionArticulosTag extends TagSupport
                                         implements IterationTag {

    // Variable que recibe del JSP
    private String idLocalTag;
    private String idTipoOrdenTag;
    private String idLogTag;
    private String idBodegaTag;

    //
    int idEstadoTxSinAutorizar = 1;

    // Metodos Tag
    public void setIdLogTag( String idLogTag )
    {
        this.idLogTag = idLogTag ;
    }

    public String getIdLogTag()
    {
        return idLogTag;
    }

    public void setIdLocalTag( String idLocalTag )
    {
        this.idLocalTag = idLocalTag ;
    }

    public String getIdLocalTag()
    {
        return idLocalTag;
    }

    public void setIdTipoOrdenTag( String idTipoOrdenTag )
    {
        this.idTipoOrdenTag = idTipoOrdenTag ;
    }

    public String getIdTipoOrdenTag()
    {
        return idTipoOrdenTag;
    }

    public void setIdBodegaTag( String idBodegaTag )
    {
        this.idBodegaTag = idBodegaTag ;
    }

    public String getIdBodegaTag()
    {
        return idBodegaTag;
    }

    // Variable para usar el bean de fachada
    FachadaDctoOrdenDetalleBean fachadaDctoOrdenDetalleBean;

    // Variable para usar el bean de fachada
    FachadaColaboraArticuloAtributoBean fachadaColaboraArticuloAtributoBean;

	// Variable para usar el bean de colaboraDctoOrdenDetalleBean
	ColaboraOrdenDetalleBean colaboraOrdenDetalleBean;

	// Variable para usar el bean de colaboraArticuloAtributoBean
    ColaboraArticuloAtributoBean colaboraArticuloAtributoBean
                                  = new ColaboraArticuloAtributoBean();
    // Iterador de objetos
    Iterator iteratorBean;

    private void inicializarVariablesDeRetorno() {

      //
      fachadaDctoOrdenDetalleBean =
                               (FachadaDctoOrdenDetalleBean)iteratorBean.next();

      // Variable que retornan al JSP
      pageContext.setAttribute("idPluVar",
                                     fachadaDctoOrdenDetalleBean.getIdPluStr());
      pageContext.setAttribute("strIdReferenciaVar",
                              fachadaDctoOrdenDetalleBean.getStrIdReferencia());
      pageContext.setAttribute("nombrePluVar",
                                    fachadaDctoOrdenDetalleBean.getNombrePlu());
      pageContext.setAttribute("cantidadVar",
                                  fachadaDctoOrdenDetalleBean.getCantidadDf2());
      pageContext.setAttribute("vrVentaUnitarioVar",
                           fachadaDctoOrdenDetalleBean.getVrVentaUnitarioDf0());
      pageContext.setAttribute("vrVentaSinIvaVar",
                             fachadaDctoOrdenDetalleBean.getVrVentaSinIvaDf0());
      pageContext.setAttribute("vrVentaConIvaVar",
                             fachadaDctoOrdenDetalleBean.getVrVentaConIvaDf0());
      pageContext.setAttribute("marcaArteClienteVar",
                             fachadaDctoOrdenDetalleBean.getMarcaArteCliente());
      pageContext.setAttribute("nombreArchivoVar",
                                fachadaDctoOrdenDetalleBean.getNombreArchivo());
      pageContext.setAttribute("referenciaClienteVar",
                            fachadaDctoOrdenDetalleBean.getReferenciaCliente());
      pageContext.setAttribute("comentarioVar",
                                   fachadaDctoOrdenDetalleBean.getComentario());
      pageContext.setAttribute("itemVar",
                                      fachadaDctoOrdenDetalleBean.getItemStr());
      pageContext.setAttribute("idEstadoTxVar",
                                fachadaDctoOrdenDetalleBean.getIdEstadoTxStr());
      pageContext.setAttribute("nombreUnidadMedidaVar", 
                           fachadaDctoOrdenDetalleBean.getNombreUnidadMedida());
      pageContext.setAttribute("nombreMarcaVar",
                           fachadaDctoOrdenDetalleBean.getNombreMarca());
      pageContext.setAttribute("nombreCategoriaVar",
                           fachadaDctoOrdenDetalleBean.getNombreCategoria());
      pageContext.setAttribute("vrCostoVar",
                           fachadaDctoOrdenDetalleBean.getVrCostoDf2());
      pageContext.setAttribute("vrCostoActualVar",
                           fachadaDctoOrdenDetalleBean.getVrCostoActualDf2());
      pageContext.setAttribute("incrementoCostoVar",
                           fachadaDctoOrdenDetalleBean.getIncrementoCostoDf2());
      pageContext.setAttribute("vrCostoConIvaVar",
                             fachadaDctoOrdenDetalleBean.getVrCostoConIvaDf2());
      pageContext.setAttribute("existenciaVar",
                             fachadaDctoOrdenDetalleBean.getExistenciaDf2());
      pageContext.setAttribute("porcentajeDsctoVar",
                          fachadaDctoOrdenDetalleBean.getPorcentajeDsctoStr());
      pageContext.setAttribute("vrVentaSinDsctoDf0Var",
                          fachadaDctoOrdenDetalleBean.getVrVentaSinDsctoDf0());

	}

	public int doStartTag() throws JspTagException {

      	// Parametros llegados de JSP
        colaboraOrdenDetalleBean  = new ColaboraOrdenDetalleBean();

        //
        colaboraOrdenDetalleBean.setIdLocal(getIdLocalTag());
        colaboraOrdenDetalleBean.setIdTipoOrden(getIdTipoOrdenTag());
        colaboraOrdenDetalleBean.setIdLog(getIdLogTag());
        colaboraOrdenDetalleBean.setIdBodega(getIdBodegaTag());

		//
        Vector vectorBean        = colaboraOrdenDetalleBean.listaDetalle();

        iteratorBean             = vectorBean.iterator();

        if ( !iteratorBean.hasNext() ) {
            try {
              pageContext.getOut().write("No existen datos <br/>");
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