package com.solucionesweb.tags;

import java.util.*;
import java.io.IOException;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.tagext.TagSupport;

// Importa la clase que posibilita el formateo
import java.text.NumberFormat;

// Importa el Bean de UsuarioBean
import com.solucionesweb.losbeans.negocio.UsuarioBean;

// Importa el Bean de UsuarioRutaBean
import com.solucionesweb.losbeans.negocio.UsuarioRutaBean;

// Importa el Bean de DctoEstadoBean
import com.solucionesweb.losbeans.negocio.DctoEstadoBean;

// Importa el Bean de ColaboraDctoOrdenBean
import com.solucionesweb.losbeans.colaboraciones.ColaboraDctoOrdenBean;

// Importa el Bean de ColaboraAgendaControlBean
import com.solucionesweb.losbeans.colaboraciones.ColaboraAgendaControlBean;

// Importa el Bean de Fachada
import com.solucionesweb.losbeans.fachada.FachadaColaboraDctoOrdenBean;

// Importa el Bean de Fachada
import com.solucionesweb.losbeans.fachada.FachadaColaboraAgendaControlBean;

// Importa el Bean de Fachada
import com.solucionesweb.losbeans.fachada.FachadaDctoEstadoBean;

// Importa el Bean de Fachada
import com.solucionesweb.losbeans.fachada.FachadaUsuarioBean;

public class ListaPedidoAdministradorUnCOTag extends TagSupport
                                               implements IterationTag {

    // Variable que recibe del JSP
    private String idTipoOrdenTag;
    private String fechaOrdenTag;
    private String idUsuarioTag;

    private String strNumeroOrdenVar;
    private int idTipoOrdenCotizacion = 9;

    //
    NumberFormat nf   = NumberFormat.getNumberInstance();

    // Metodos Tag
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

    public void setIdUsuarioTag( String idUsuarioTag )
    {
        this.idUsuarioTag = idUsuarioTag ;
    }

    public String getIdUsuarioTag()
    {
        return idUsuarioTag;
    }

    //
    DctoEstadoBean dctoEstadoBean = new DctoEstadoBean();

    //
    UsuarioBean usuarioBean;

    // Variable para usar el bean de fachada
    FachadaColaboraDctoOrdenBean fachadaColaboraDctoOrdenBean;

    //
    FachadaColaboraAgendaControlBean fachadaColaboraAgendaControlBean;

    //
    FachadaUsuarioBean fachadaUsuarioBean;

	// Variable para usar el bean de colaboraDctoOrdenBean
	ColaboraDctoOrdenBean colaboraDctoOrdenBean;

	// Variable para usar el bean de ColaboraAgendaControlBean
	ColaboraAgendaControlBean colaboraAgendaControlBean;

	//
	FachadaDctoEstadoBean fachadaDctoEstadoBean = new FachadaDctoEstadoBean();

    // Iterador de objetos
    Iterator iteratorBean;

    private void inicializarVariablesDeRetorno() {

      //
      fachadaColaboraDctoOrdenBean = (FachadaColaboraDctoOrdenBean)iteratorBean.next();

      // Completa datos Cliente
      colaboraAgendaControlBean = new ColaboraAgendaControlBean();
      colaboraAgendaControlBean.setIdCliente(fachadaColaboraDctoOrdenBean.getIdCliente());
      colaboraAgendaControlBean.setIdSucursal(fachadaColaboraDctoOrdenBean.getStrIdSucursal());

      //
      fachadaColaboraAgendaControlBean = new FachadaColaboraAgendaControlBean();
      fachadaColaboraAgendaControlBean = colaboraAgendaControlBean.listaUnaSucursal();

      //
      usuarioBean = new UsuarioBean();
      usuarioBean.setIdUsuario(fachadaColaboraDctoOrdenBean.getIdUsuario());

      //
      fachadaUsuarioBean = new FachadaUsuarioBean();
      fachadaUsuarioBean = usuarioBean.listaUsuario();

      // Variable que retornan al JSP
      pageContext.setAttribute("idLocalVar",fachadaColaboraDctoOrdenBean.getIdLocalStr());
      pageContext.setAttribute("idTipoOrdenVar",fachadaColaboraDctoOrdenBean.getIdTipoOrdenStr());
      pageContext.setAttribute("idOrdenVar",fachadaColaboraDctoOrdenBean.getIdOrdenStr());
      pageContext.setAttribute("idLogVar", fachadaColaboraDctoOrdenBean.getIdLogStr());
      pageContext.setAttribute("idClienteVar",fachadaColaboraDctoOrdenBean.getIdCliente());
      pageContext.setAttribute("idSucursalVar",fachadaColaboraDctoOrdenBean.getStrIdSucursal());
      pageContext.setAttribute("idUsuarioVar",nf.format(fachadaColaboraDctoOrdenBean.getIdUsuario()));
      pageContext.setAttribute("fechaOrdenVar",fachadaColaboraDctoOrdenBean.getFechaOrden());
      pageContext.setAttribute("tipoDctoVar",fachadaColaboraDctoOrdenBean.getTipoDcto());
      pageContext.setAttribute("direccionDespachoVar",fachadaColaboraDctoOrdenBean.getDireccionDespacho());
      pageContext.setAttribute("emailVar",fachadaColaboraDctoOrdenBean.getEmail());
      pageContext.setAttribute("faxVar",fachadaColaboraDctoOrdenBean.getFax());
      pageContext.setAttribute("contactoVar",fachadaColaboraDctoOrdenBean.getContacto());
      pageContext.setAttribute("observacionVar",fachadaColaboraDctoOrdenBean.getObservacion());
      pageContext.setAttribute("cantidadArticulosVar", "#" + nf.format(fachadaColaboraDctoOrdenBean.getCantidadArticulos()));
      pageContext.setAttribute("vrVentaSinIvaVar", "$" + fachadaColaboraDctoOrdenBean.getVrVentaSinIvaDf2());
      pageContext.setAttribute("vrVentaConIvaVar", "$" + fachadaColaboraDctoOrdenBean.getVrVentaConIvaDf2());
      pageContext.setAttribute("pesoTeoricoTotalVar",fachadaColaboraDctoOrdenBean.getPesoTeoricoTotalDf0());
      pageContext.setAttribute("strNumeroOrdenVar", fachadaColaboraDctoOrdenBean.getStrNumeroOrden() );
      pageContext.setAttribute("idEstadoTxVar", fachadaColaboraDctoOrdenBean.getIdEstadoTxStr());
      pageContext.setAttribute("nombreClienteVar", fachadaColaboraAgendaControlBean.getNombreCliente() );
      pageContext.setAttribute("nombreEmpresaVar", fachadaColaboraAgendaControlBean.getNombreEmpresa() );
      pageContext.setAttribute("nombreUsuarioVar", fachadaUsuarioBean.getNombreUsuario());

      //
      ColaboraDctoOrdenBean dctoOrdenBean = new ColaboraDctoOrdenBean();
      dctoOrdenBean.setIdOrden(fachadaColaboraDctoOrdenBean.getIdOrdenStr());
      dctoOrdenBean.setIdTipoOrden(idTipoOrdenCotizacion);

      //
      FachadaColaboraDctoOrdenBean fachada = new FachadaColaboraDctoOrdenBean();
      fachada  = dctoOrdenBean.listaDctoIdTipoOrdenIdOrden();

      //
      pageContext.setAttribute("strNumeroCotizacionVar",fachada.getStrNumeroOrden());

      //
      dctoEstadoBean.setEstado(fachadaColaboraDctoOrdenBean.getEstado());

      //
      fachadaDctoEstadoBean =  dctoEstadoBean.listaUnEstadoDctoFachada();

      //
      pageContext.setAttribute("nombreEstadoVar", fachadaColaboraDctoOrdenBean.getNombreEstado() );

	}

	public int doStartTag() throws JspTagException {

        // estadoProceso
        int estadoProceso = 1;

      	// Parametros llegados de JSP
        colaboraDctoOrdenBean  = new ColaboraDctoOrdenBean();

        //
        colaboraDctoOrdenBean.setIdTipoOrden(getIdTipoOrdenTag());
        colaboraDctoOrdenBean.setFechaOrden(getFechaOrdenTag());
        colaboraDctoOrdenBean.setIdUsuario(getIdUsuarioTag());
        colaboraDctoOrdenBean.setEstado(estadoProceso);

        //
        UsuarioRutaBean usuarioRutaBean = new UsuarioRutaBean();

        usuarioRutaBean.setIdUsuario(getIdUsuarioTag());

        //
        String idRuta     = usuarioRutaBean.listaRutaUsuario();

		//
        Vector vectorBean = colaboraDctoOrdenBean.listaPedidoRuta(idRuta);
        iteratorBean      = vectorBean.iterator();

        if ( !iteratorBean.hasNext() ) {
            try {
              pageContext.getOut().write("La consulta no devolvio ningun dato <br/>");
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