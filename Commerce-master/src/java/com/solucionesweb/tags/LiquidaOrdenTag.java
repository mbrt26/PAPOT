package com.solucionesweb.tags;

import java.util.*;
import java.io.IOException;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.tagext.TagSupport;

// Importa la clase que posibilita el formateo
import java.text.NumberFormat;

// Importa el Bean de Asignaturas
import com.solucionesweb.losbeans.colaboraciones.ColaboraOrdenDetalleBean;

// Importa el Bean de Fachada
import com.solucionesweb.losbeans.fachada.FachadaDctoOrdenDetalleBean;

public class LiquidaOrdenTag extends TagSupport
                                     implements IterationTag {

    // Variable que recibe del JSP
    private String idLogTag;
    private String idTipoOrdenTag;
    private String idLocalTag;

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

    // Variable para usar el bean de fachada
    FachadaDctoOrdenDetalleBean fachadaDctoOrdenDetalleBean;

	// Variable para usar el bean de EstadoPcBean
	ColaboraOrdenDetalleBean colaboraOrdenDetalleBean;

    // Iterador de objetos
    Iterator iteratorBean;

    private void inicializarVariablesDeRetorno() {


      //
      fachadaDctoOrdenDetalleBean =
                               (FachadaDctoOrdenDetalleBean)iteratorBean.next();

      // Variable que retornan al JSP
      pageContext.setAttribute("idLocalVar",
                                  fachadaDctoOrdenDetalleBean.getIdLocalStr());
      pageContext.setAttribute("idTipoOrdenVar",
                              fachadaDctoOrdenDetalleBean.getIdTipoOrdenStr());
      pageContext.setAttribute("idOrdenVar",
                                  fachadaDctoOrdenDetalleBean.getIdOrdenStr());
      pageContext.setAttribute("idLogVar",
                                    fachadaDctoOrdenDetalleBean.getIdLogStr());
      pageContext.setAttribute("idClienteVar",
                                   fachadaDctoOrdenDetalleBean.getIdCliente());
      pageContext.setAttribute("idUsuarioVar",
                        nf.format(fachadaDctoOrdenDetalleBean.getIdUsuario()));
      pageContext.setAttribute("fechaOrdenVar",
                                  fachadaDctoOrdenDetalleBean.getFechaOrden());
      pageContext.setAttribute("idSucursalVar",
                               fachadaDctoOrdenDetalleBean.getStrIdSucursal());
      pageContext.setAttribute("idEstadoTxVar",
                               fachadaDctoOrdenDetalleBean.getIdEstadoTxStr());
      pageContext.setAttribute("cantidadArticulosVar",
          "#" + nf.format(fachadaDctoOrdenDetalleBean.getCantidadArticulos()));
      pageContext.setAttribute("vrVentaSinIvaVar",
                      "$" + fachadaDctoOrdenDetalleBean.getVrVentaSinIvaDf2());
      pageContext.setAttribute("vrVentaConIvaVar",
                      "$" + fachadaDctoOrdenDetalleBean.getVrVentaConIvaDf0());
      pageContext.setAttribute("pesoTeoricoTotalVar",
                 nf.format(fachadaDctoOrdenDetalleBean.getPesoTeoricoTotal()));
      pageContext.setAttribute("vrCostoConIvaVar",
                            fachadaDctoOrdenDetalleBean.getVrCostoConIvaDf2());
      pageContext.setAttribute("vrCostoSinIvaVar",
                            fachadaDctoOrdenDetalleBean.getVrCostoSinIvaDf2());
      pageContext.setAttribute("vrVentaSinDsctoDf0Var",
                          fachadaDctoOrdenDetalleBean.getVrVentaSinDsctoDf0());
      pageContext.setAttribute("vrVentaConIvaSf0Var",
                          fachadaDctoOrdenDetalleBean.getVrVentaConIvaSf0());

	}

	public int doStartTag() throws JspTagException {

      	// Parametros llegados de JSP
        colaboraOrdenDetalleBean  = new ColaboraOrdenDetalleBean();

        //
        colaboraOrdenDetalleBean.setIdTipoOrden(getIdTipoOrdenTag());
        colaboraOrdenDetalleBean.setIdLog(getIdLogTag());
        colaboraOrdenDetalleBean.setIdLocal(getIdLocalTag());

		//
        Vector vectorBean = colaboraOrdenDetalleBean.liquidaOrden();

        //
        iteratorBean      = vectorBean.iterator();

        if ( !iteratorBean.hasNext() ) {
            try {
              pageContext.getOut().write("No existen datos<br/>");
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