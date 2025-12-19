package com.solucionesweb.tags;

import java.util.*;
import java.io.IOException;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.tagext.TagSupport;

// Importa la clase que posibilita el formateo
import java.text.NumberFormat;

// Importa el Bean de Asignaturas
import com.solucionesweb.losbeans.colaboraciones.ColaboraDctoOrdenBean;

// Importa el Bean de ColaboraAgendaControlBean
import com.solucionesweb.losbeans.colaboraciones.ColaboraTercero;

// Importa el Bean de Fachada
import com.solucionesweb.losbeans.fachada.FachadaColaboraDctoOrdenBean;

// Importa el Bean de Fachada
import com.solucionesweb.losbeans.fachada.FachadaTerceroBean;

// Importa el Bean de FachadaDctoOrdenBean
import com.solucionesweb.losbeans.fachada.FachadaDctoOrdenBean;

// Importa el Bean de DctoOrdenBean
import com.solucionesweb.losbeans.negocio.DctoOrdenBean;


public class ListaPedidoEstadoTag extends TagSupport
                                          implements IterationTag {

    // Variable que recibe del JSP
    private String idClienteTag;
    private String idTipoOrdenTag;
    private String fechaOrdenTag;
    private String idLocalTag;

    int xIdEstadoTxAutorizado = 2;

    //
    NumberFormat nf   = NumberFormat.getNumberInstance();

    //
    int idTipoOrdenCotiza = 9;

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

    public void setFechaOrdenTag( String fechaOrdenTag )
    {
        this.fechaOrdenTag = fechaOrdenTag ;
    }

    public String getFechaOrdenTag()
    {
        return fechaOrdenTag;
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
    FachadaColaboraDctoOrdenBean fachadaColaboraDctoOrdenBean;

    // Variable para usar el bean de fachada
    FachadaDctoOrdenBean fachadaDctoOrdenBean;

    //
    FachadaTerceroBean fachadaTerceroBean
                                   = new FachadaTerceroBean();

	// Variable para usar el bean de EstadoPcBean
	ColaboraDctoOrdenBean colaboraDctoOrdenBean;

	// Variable para usar el bean de DctoOrdenBean
	DctoOrdenBean dctoOrdenBean    = new DctoOrdenBean();

	// Variable para usar el bean de EstadoPcBean
	ColaboraTercero colaboraTercero
                                   = new ColaboraTercero();

    // Iterador de objetos
    Iterator iteratorBean;

    private void inicializarVariablesDeRetorno() {

      //
      fachadaColaboraDctoOrdenBean
                            = (FachadaColaboraDctoOrdenBean)iteratorBean.next();

      // listaUnaSucursal
      String idCliente     = fachadaColaboraDctoOrdenBean.getIdCliente();
      String strIdSucursal = fachadaColaboraDctoOrdenBean.getStrIdSucursal();

      // listaDctoOrdenIdLogIdTipoOrden  ordenCompra
      dctoOrdenBean.setIdLog(fachadaColaboraDctoOrdenBean.getIdLog());
      dctoOrdenBean.setIdTipoOrden(idTipoOrdenCotiza);
      fachadaDctoOrdenBean = dctoOrdenBean.listaDctoOrdenIdLogIdTipoOrden();
      String numeroCotizacionVar
                                = fachadaDctoOrdenBean.getStrNumeroCotizacion();

      // listaUnaSucursal
      colaboraTercero.setIdCliente(idCliente);
      //colaboraTercero.setIdSucursal(strIdSucursal);

      //
      fachadaTerceroBean = colaboraTercero.listaTerceroFCH();

      // Variable que retornan al JSP
      pageContext.setAttribute("idLocalVar",
                                  fachadaColaboraDctoOrdenBean.getIdLocalStr());
      pageContext.setAttribute("idOrdenVar",
                                  fachadaColaboraDctoOrdenBean.getIdOrdenStr());
      pageContext.setAttribute("idLogVar",
                                    fachadaColaboraDctoOrdenBean.getIdLogStr());
      pageContext.setAttribute("idClienteVar",
                                   fachadaColaboraDctoOrdenBean.getIdCliente());
      pageContext.setAttribute("idSucursalVar",
                               fachadaColaboraDctoOrdenBean.getStrIdSucursal());
      pageContext.setAttribute("direccionDespachoVar",
                           fachadaColaboraDctoOrdenBean.getDireccionDespacho());
      pageContext.setAttribute("emailVar",
                                       fachadaColaboraDctoOrdenBean.getEmail());
      pageContext.setAttribute("faxVar",fachadaColaboraDctoOrdenBean.getFax());
      pageContext.setAttribute("contactoVar",
                                    fachadaColaboraDctoOrdenBean.getContacto());
      pageContext.setAttribute("observacionVar",
                                 fachadaColaboraDctoOrdenBean.getObservacion());
      pageContext.setAttribute("cantidadArticulosVar", "#" +
                nf.format(fachadaColaboraDctoOrdenBean.getCantidadArticulos()));
      pageContext.setAttribute("vrVentaSinIvaVar",
                      "$" + fachadaColaboraDctoOrdenBean.getVrVentaSinIvaDf2());
      pageContext.setAttribute("vrVentaConIvaVar",
                      "$" + fachadaColaboraDctoOrdenBean.getVrVentaConIvaDf2());
      pageContext.setAttribute("pesoTeoricoTotalVar",
                 nf.format(fachadaColaboraDctoOrdenBean.getPesoTeoricoTotal()));
      pageContext.setAttribute("strNumeroOrdenVar",
                             fachadaColaboraDctoOrdenBean.getStrNumeroOrden() );
      pageContext.setAttribute("idEstadoTx",
                               fachadaColaboraDctoOrdenBean.getIdEstadoTxStr());
      pageContext.setAttribute("nombreEstadoTxVar",
                               fachadaColaboraDctoOrdenBean.getNombreEstado() );
      pageContext.setAttribute("nombreClienteVar",
                                fachadaTerceroBean.getNombreTercero() );
      pageContext.setAttribute("nombreEmpresaVar",
                                fachadaTerceroBean.getNombreEmpresa() );
      pageContext.setAttribute("numeroCotizacionVar", numeroCotizacionVar);
      pageContext.setAttribute("idDctoVar",
                                   fachadaColaboraDctoOrdenBean.getIdDctoStr());
      pageContext.setAttribute("idDctoNitCCVar",
                                 fachadaColaboraDctoOrdenBean.getIdDctoNitCC());
      pageContext.setAttribute("vrCostoConIvaVar",
                            fachadaColaboraDctoOrdenBean.getVrCostoConIvaDf0());
      pageContext.setAttribute("vrCostoSinIvaVar",
                            fachadaColaboraDctoOrdenBean.getVrCostoSinIvaDf0());
      pageContext.setAttribute("vrCostoPagoVar",
                            fachadaColaboraDctoOrdenBean.getVrCostoPagoDf0());
      pageContext.setAttribute("indicadorVar",
                            fachadaColaboraDctoOrdenBean.getIndicadorStr());

	}

	public int doStartTag() throws JspTagException {

      	// Parametros llegados de JSP
        colaboraDctoOrdenBean  = new ColaboraDctoOrdenBean();

        //
        colaboraDctoOrdenBean.setIdLocal(getIdLocalTag());
        colaboraDctoOrdenBean.setIdCliente(getIdClienteTag());
        colaboraDctoOrdenBean.setIdTipoOrden(getIdTipoOrdenTag());
        colaboraDctoOrdenBean.setFechaOrden(getFechaOrdenTag());
        colaboraDctoOrdenBean.setIdEstadoTx(xIdEstadoTxAutorizado);

		//
        Vector vectorBean = colaboraDctoOrdenBean.listaHistoriaPedido();

        //
        iteratorBean      = vectorBean.iterator();

        if ( !iteratorBean.hasNext() ) {
            try {
              pageContext.getOut().write("La consulta no devolvio ningun dato <br/>");
              pageContext.getOut().write("<br/>");
			  pageContext.getOut().write("Por favor comunique al administrador del sistema que no existen datos <br/>");
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