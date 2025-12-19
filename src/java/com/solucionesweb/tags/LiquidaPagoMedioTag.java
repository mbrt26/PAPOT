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

// Importa el Bean de DctoEstadoBean
import com.solucionesweb.losbeans.negocio.ContableRetencionBean;

import com.solucionesweb.losbeans.negocio.TerceroBean;

// Importa el Bean de Fachada
import com.solucionesweb.losbeans.fachada.FachadaTerceroBean;

// Importa el Bean de Asignaturas
import com.solucionesweb.losbeans.negocio.PagoMedioBean;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaPagoMedioBean;

public class LiquidaPagoMedioTag extends TagSupport
                                     implements IterationTag {

    // Variable que recibe del JSP
    private String idLocalTag;
    private String idLogTag;
    private String idTipoOrdenTag;

    //
    int idEstadoTxSinAutorizar = 1;

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

    public int getIdTipoOrden()
    {
        return  new Integer(getIdTipoOrdenTag()).intValue();
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
    FachadaDctoOrdenDetalleBean fachadaDctoOrdenDetalleBean
                                       = new FachadaDctoOrdenDetalleBean();

	// Variable para usar el bean de EstadoPcBean
	ColaboraOrdenDetalleBean colaboraOrdenDetalleBean;

    // Iterador de objetos
    Iterator iteratorBean;

    private void inicializarVariablesDeRetorno() {


      //
      fachadaDctoOrdenDetalleBean = (FachadaDctoOrdenDetalleBean)iteratorBean.next();


        //------------------
        colaboraOrdenDetalleBean  = new ColaboraOrdenDetalleBean();

        //
        colaboraOrdenDetalleBean.setIdLocal(getIdLocalTag());
        colaboraOrdenDetalleBean.setIdLog(getIdLogTag());
        colaboraOrdenDetalleBean.setIdTipoOrden(getIdTipoOrdenTag());

        //
        fachadaDctoOrdenDetalleBean    =
                                     colaboraOrdenDetalleBean.liquidaOrdenFCH();

        //
        double xVrVentaSinIva          =
                                 fachadaDctoOrdenDetalleBean.getVrVentaSinIva();
        double xVrVentaSinDscto        =
                              fachadaDctoOrdenDetalleBean.getVrVentaSinDscto();
        double xVrIva                  =
                                    fachadaDctoOrdenDetalleBean.getVrIvaVenta();
        double xVrImpoconsumo          =
                                 fachadaDctoOrdenDetalleBean.getVrImpoconsumo();

        //
        int xIdConceptoRFCompra        = 1;
        int xIdTipoTercero             = 1;
        int xIdRteFuenteVrBase      = 0;

        //
        TerceroBean terceroBean        = new TerceroBean();

        //
        terceroBean.setIdCliente(fachadaDctoOrdenDetalleBean.getIdCliente());
        terceroBean.setIdTipoTercero(xIdTipoTercero);


        //
        FachadaTerceroBean fachadaTerceroBean
                                       = new FachadaTerceroBean();

        fachadaTerceroBean             = terceroBean.listaUnTerceroFCH();

        //
        ContableRetencionBean contableRetencionBean
                                       = new ContableRetencionBean();

        //
        double xVrRetencion            =
                     contableRetencionBean.calculaRetencion(
                                        fachadaTerceroBean.getIdAutoRetenedor(),
                                                            xIdConceptoRFCompra,
                                                              xVrVentaSinDscto,
                                                            xIdRteFuenteVrBase);

        //
        PagoMedioBean pagoMedioBean    = new PagoMedioBean();

        //
        pagoMedioBean.setIdLocal(getIdLocalTag());
        pagoMedioBean.setIdLog(getIdLogTag());
        pagoMedioBean.setIdTipoOrden(getIdTipoOrden());

        //
        FachadaPagoMedioBean fachadaPagoMedioBean
                                       = new FachadaPagoMedioBean();

        //
        fachadaPagoMedioBean           = pagoMedioBean.liquidaPagoLogFCH();

                //
        double xVrFactura =   ( xVrVentaSinDscto +
                                xVrIva           +
                                xVrImpoconsumo   -
                                xVrRetencion ) ;

        //
        double xVrPago    =   fachadaPagoMedioBean.getVrMedio() ;
        double xVrCambio  =   0;

        //
        if ( xVrFactura   >= xVrPago ) xVrCambio  =   0;
        if ( xVrFactura   < xVrPago )  xVrCambio  = (xVrPago - xVrFactura);

        //
        fachadaDctoOrdenDetalleBean.setVrCosto(xVrFactura);
        fachadaDctoOrdenDetalleBean.setVrCostoConIva(xVrPago);
        fachadaDctoOrdenDetalleBean.setVrCostoSinIva(xVrCambio);

        // Variable que retornan al JSP
        pageContext.setAttribute("vrFacturaVar",
                             fachadaDctoOrdenDetalleBean.getVrCostoDf0());

        pageContext.setAttribute("vrPagoVar",
                             fachadaDctoOrdenDetalleBean.getVrCostoConIvaDf0());
        pageContext.setAttribute("vrCambioVar",
                             fachadaDctoOrdenDetalleBean.getVrCostoSinIvaDf0());
	}

	public int doStartTag() throws JspTagException {

        //
        colaboraOrdenDetalleBean       = new ColaboraOrdenDetalleBean();

        //
        colaboraOrdenDetalleBean.setIdTipoOrden(getIdTipoOrdenTag());
        colaboraOrdenDetalleBean.setIdLog(getIdLogTag());
        colaboraOrdenDetalleBean.setIdLocal(getIdLocalTag());

		//
        Vector vectorBean              = colaboraOrdenDetalleBean.liquidaOrden();

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