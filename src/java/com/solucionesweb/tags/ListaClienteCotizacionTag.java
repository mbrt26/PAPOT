package com.solucionesweb.tags;

import java.util.*;
import java.io.*;
import java.io.IOException;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.tagext.TagSupport;

// Importa la clase que posibilita el formateo
import java.text.NumberFormat;

// Importa el Bean de Asignaturas
import com.solucionesweb.losbeans.colaboraciones.ColaboraDctoOrdenBean;


// Importa el Bean de DctoEstadoBean
import com.solucionesweb.losbeans.negocio.DctoEstadoBean;

// Importa el Bean de Fachada
import com.solucionesweb.losbeans.fachada.FachadaDctoEstadoBean;

// Importa el Bean de Fachada
import com.solucionesweb.losbeans.fachada.FachadaColaboraDctoOrdenBean;

public class ListaClienteCotizacionTag extends TagSupport
                                     implements IterationTag {

    // Variable que recibe del JSP
    private String idLogTag;
    private String idClienteTag;
    private String idSucursalTag;
    private String idUsuarioTag;

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

    public void setIdClienteTag( String idClienteTag )
    {
        this.idClienteTag = idClienteTag ;
    }

    public String getIdClienteTag()
    {
        return idClienteTag;
    }

    public void setIdSucursalTag( String idSucursalTag )
    {
        this.idSucursalTag = idSucursalTag ;
    }

    public String getIdSucursalTag()
    {
        return idSucursalTag;
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
    FachadaDctoEstadoBean   fachadaDctoEstadoBean = new FachadaDctoEstadoBean();

    // Variable para usar el bean de fachada
    FachadaColaboraDctoOrdenBean fachadaColaboraDctoOrdenBean;

	// Variable para usar el bean de EstadoPcBean
	ColaboraDctoOrdenBean colaboraDctoOrdenBean;

	//
	DctoEstadoBean dctoEstadoBean = new DctoEstadoBean();

    // Iterador de objetos
    Iterator iteratorBean;

    private void inicializarVariablesDeRetorno() {


      //
      fachadaColaboraDctoOrdenBean = (FachadaColaboraDctoOrdenBean)iteratorBean.next();

      // Variable que retornan al JSP
      pageContext.setAttribute("idLocalVar",
                                  fachadaColaboraDctoOrdenBean.getIdLocalStr());
      pageContext.setAttribute("idTipoOrdenVar",
                              fachadaColaboraDctoOrdenBean.getIdTipoOrdenStr());
      pageContext.setAttribute("idOrdenVar",
                                  fachadaColaboraDctoOrdenBean.getIdOrdenStr());
      pageContext.setAttribute("idLogVar",
                                    fachadaColaboraDctoOrdenBean.getIdLogStr());
      pageContext.setAttribute("idClienteVar",
                                   fachadaColaboraDctoOrdenBean.getIdCliente());
      pageContext.setAttribute("idUsuarioVar",
                        nf.format(fachadaColaboraDctoOrdenBean.getIdUsuario()));
      pageContext.setAttribute("fechaOrdenVar",
                                  fachadaColaboraDctoOrdenBean.getFechaOrden());
      pageContext.setAttribute("idSucursalVar",
                               fachadaColaboraDctoOrdenBean.getStrIdSucursal());
      pageContext.setAttribute("idEstadoTxVar",
                               fachadaColaboraDctoOrdenBean.getIdEstadoTxStr());
      pageContext.setAttribute("cantidadArticulosVar",
          "#" + nf.format(fachadaColaboraDctoOrdenBean.getCantidadArticulos()));
      pageContext.setAttribute("vrVentaSinIvaVar",
                      "$" + fachadaColaboraDctoOrdenBean.getVrVentaSinIvaDf2());
      pageContext.setAttribute("vrVentaConIvaVar",
                      "$" + fachadaColaboraDctoOrdenBean.getVrVentaConIvaDf2());
      pageContext.setAttribute("pesoTeoricoTotalVar",
                 nf.format(fachadaColaboraDctoOrdenBean.getPesoTeoricoTotal()));
      pageContext.setAttribute("vrCostoConIvaVar",
                            fachadaColaboraDctoOrdenBean.getVrCostoConIvaDf2());
      pageContext.setAttribute("vrCostoSinIvaVar",
                            fachadaColaboraDctoOrdenBean.getVrCostoSinIvaDf2());


      // Pedido sin CONFIRMAR
      if ( fachadaColaboraDctoOrdenBean.getIdEstadoTx() > 0 ) {

         idEstadoTxSinAutorizar =  fachadaColaboraDctoOrdenBean.getIdEstadoTx();
      }

      //
      dctoEstadoBean.setEstado(idEstadoTxSinAutorizar);

      //
      fachadaDctoEstadoBean = dctoEstadoBean.listaUnEstadoDctoFachada();

      //
      pageContext.setAttribute("nombreEstadoTxVar",
                                       fachadaDctoEstadoBean.getNombreEstado());

	}

	public int doStartTag() throws JspTagException {

      	// Parametros llegados de JSP
        colaboraDctoOrdenBean  = new ColaboraDctoOrdenBean();
        colaboraDctoOrdenBean.setIdCliente(getIdClienteTag());
        colaboraDctoOrdenBean.setStrIdSucursal(getIdSucursalTag());
        colaboraDctoOrdenBean.setIdLog(getIdLogTag());
        colaboraDctoOrdenBean.setIdUsuario(getIdUsuarioTag());

		//
        Vector vectorBean = colaboraDctoOrdenBean.liquidaOrden();

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