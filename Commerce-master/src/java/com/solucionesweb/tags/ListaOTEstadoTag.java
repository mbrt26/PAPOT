package com.solucionesweb.tags;

import java.util.*;
import java.io.IOException;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.tagext.TagSupport;

// Importa el Bean de Asignaturas
import com.solucionesweb.losbeans.colaboraciones.ColaboraDctoOrdenBean;

// Importa el Bean de Fachada
import com.solucionesweb.losbeans.fachada.FachadaColaboraDctoOrdenBean;

public class ListaOTEstadoTag extends TagSupport
        implements IterationTag {

    // Variable que recibe del JSP
    private String idLocalTag;
    private String idTipoOrdenTag;
    private String idClienteTag;
    private String idEstadoTag;
    private String idTipoTerceroTag;

    //
    double xIdClienteTodos = -1.0;
    
    //
    int xIdEstadoNuevo = 3;

    //
    int xIdEstadoProduccionCumplido = 20;
    int xIdEstadoProduccionPendiente = 21;
    int xIdEstadoFacturadoCliente = 50;
    int xIdEstadoSinFacturaCliente = 51;
    int xIdEstadoRemisionSinFacturaCliente = 52;
    int xIdEstadoRemisionSinFacturaGeneralMercadeo = 53; 
    int xIdEstadoHistoricoGeneralCliente = 54;


    // Metodos Tag
    public void setIdTipoOrdenTag(String idTipoOrdenTag) {
        this.idTipoOrdenTag = idTipoOrdenTag;
    }

    public String getIdTipoOrdenTag() {
        return idTipoOrdenTag;
    }

    public int getIdTipoOrden() {
        return new Integer(getIdTipoOrdenTag());
    }

    public void setIdLocalTag(String idLocalTag) {
        this.idLocalTag = idLocalTag;
    }

    public String getIdLocalTag() {
        return idLocalTag;
    }

    public void setIdClienteTag(String idClienteTag) {
        this.idClienteTag = idClienteTag;
    }

    public String getIdClienteTag() {
        return idClienteTag;
    }

    public void setIdEstadoTag(String idEstadoTag) {
        this.idEstadoTag = idEstadoTag;
    }

    public double getIdCliente() {
        return new Double(getIdClienteTag()).doubleValue();
    }

    public String getIdEstadoTag() {
        return idEstadoTag;
    }

    public int getIdEstado() {
        return new Integer(getIdEstadoTag()).intValue();
    }

    public void setIdTipoTerceroTag(String idTipoTerceroTag) {
        this.idTipoTerceroTag = idTipoTerceroTag;
    }

    public String getIdTipoTerceroTag() {
        return idTipoTerceroTag;
    }

    public int getIdTipoTercero() {
        return new Integer(getIdTipoTerceroTag()).intValue();
    }
    // Variable para usar el bean de fachada
    FachadaColaboraDctoOrdenBean fachadaColaboraDctoOrdenBean;
    // Variable para usar el bean de EstadoPcBean
    ColaboraDctoOrdenBean colaboraDctoOrdenBean;
    // Iterador de objetos
    Iterator iteratorBean;

    private void inicializarVariablesDeRetorno() {

        //
        fachadaColaboraDctoOrdenBean =
                (FachadaColaboraDctoOrdenBean) iteratorBean.next();

        // Variable que retornan al JSP
        pageContext.setAttribute("idLogVar",
                fachadaColaboraDctoOrdenBean.getIdLogStr());
        pageContext.setAttribute("idClienteVar",
                fachadaColaboraDctoOrdenBean.getIdCliente());
        pageContext.setAttribute("idUsuarioVar",
                fachadaColaboraDctoOrdenBean.getIdUsuarioStr());
        pageContext.setAttribute("nombreTerceroVar",
                fachadaColaboraDctoOrdenBean.getNombreTercero());
        pageContext.setAttribute("direccionTerceroVar",
                fachadaColaboraDctoOrdenBean.getDireccionDespacho());
        pageContext.setAttribute("telefonoFijoVar",
                fachadaColaboraDctoOrdenBean.getTelefonoFijo());
        pageContext.setAttribute("nombreEmpresaVar",
                fachadaColaboraDctoOrdenBean.getNombreEmpresa());
        pageContext.setAttribute("ciudadTerceroVar",
                fachadaColaboraDctoOrdenBean.getCiudadTercero());
        pageContext.setAttribute("nombreUsuarioVar",
                fachadaColaboraDctoOrdenBean.getNombreUsuario());
        pageContext.setAttribute("itemVar",
                fachadaColaboraDctoOrdenBean.getItemStr());
        pageContext.setAttribute("cantidadVar",
                fachadaColaboraDctoOrdenBean.getCantidadStr());
        pageContext.setAttribute("referenciaClienteVar",
                fachadaColaboraDctoOrdenBean.getReferenciaCliente());
        pageContext.setAttribute("fechaEntregaVar",
                fachadaColaboraDctoOrdenBean.getFechaEntregaCorta());
        pageContext.setAttribute("numeroOrdenVar",
                fachadaColaboraDctoOrdenBean.getNumeroOrdenStr());
        pageContext.setAttribute("idFichaVar",
                fachadaColaboraDctoOrdenBean.getIdFichaStr());
        pageContext.setAttribute("ordenCompraVar",
                fachadaColaboraDctoOrdenBean.getOrdenCompra());
        pageContext.setAttribute("cantidadDf0Var",
                fachadaColaboraDctoOrdenBean.getCantidadDf0());
        pageContext.setAttribute("fechaOrdenVar",
                fachadaColaboraDctoOrdenBean.getFechaOrdenCorta());
        pageContext.setAttribute("pesoPedidoVar",
                fachadaColaboraDctoOrdenBean.getPesoPedidoDf0());
        pageContext.setAttribute("pesoTerminadoVar",
                fachadaColaboraDctoOrdenBean.getPesoTerminadoDf0());
        pageContext.setAttribute("pesoRetalVar",
                fachadaColaboraDctoOrdenBean.getPesoRetalDf0());
        pageContext.setAttribute("pesoPendienteVar",
                fachadaColaboraDctoOrdenBean.getPesoPendienteDf0());
        pageContext.setAttribute("idLocalVar",
                fachadaColaboraDctoOrdenBean.getIdLocalStr());
        pageContext.setAttribute("idTipoOrdenVar",
                fachadaColaboraDctoOrdenBean.getIdTipoOrdenStr());
        pageContext.setAttribute("idOrdenVar",
                fachadaColaboraDctoOrdenBean.getIdOrdenStr());
        pageContext.setAttribute("cantidadPendienteVar",
                fachadaColaboraDctoOrdenBean.getCantidadPendienteDf0());
        pageContext.setAttribute("cantidadTerminadaVar",
                fachadaColaboraDctoOrdenBean.getCantidadTerminadaDf0());
        pageContext.setAttribute("cantidadFacturadaVar",
                fachadaColaboraDctoOrdenBean.getCantidadFacturadaDf0());
        pageContext.setAttribute("cantidadFacturadaPendienteVar",
              fachadaColaboraDctoOrdenBean.getCantidadFacturadaPendienteDf0());
        pageContext.setAttribute("cantidadProduccionPendienteVar",
              fachadaColaboraDctoOrdenBean.getCantidadProduccionPendienteDf0());
        pageContext.setAttribute("idDctoVar",
              fachadaColaboraDctoOrdenBean.getIdDctoDf0());
        pageContext.setAttribute("vrFacturaVar",
              fachadaColaboraDctoOrdenBean.getVrFacturaDf0());
        
    }

    public int doStartTag() throws JspTagException {

        // Parametros llegados de JSP
        colaboraDctoOrdenBean = new ColaboraDctoOrdenBean();

        //
        colaboraDctoOrdenBean.setIdLocal(getIdLocalTag());
        colaboraDctoOrdenBean.setIdTipoOrden(getIdTipoOrden());
        colaboraDctoOrdenBean.setIdEstado(getIdEstadoTag());
        colaboraDctoOrdenBean.setIdCliente(getIdClienteTag());

        // 
        Vector vectorBean = new Vector();
        
        System.out.println(" getIdEstado() " + getIdEstado() );
        System.out.println(" getIdCliente() " + getIdCliente() );

        
        //--- PRODUCCION cumplido 20--------------------------------------------
        if (getIdEstado() == xIdEstadoProduccionCumplido) {

            //
            if (getIdCliente() == xIdClienteTodos) {

                //
                vectorBean =
                     colaboraDctoOrdenBean.listaAllAlfabeticoProduccionCumplido(
                                                            getIdTipoTercero());

            } else {

                //
                vectorBean =
                      colaboraDctoOrdenBean.listaUnAlfabeticoProduccionCumplido(
                                                            getIdTipoTercero());

            }
        }

        //--- PRODUCCION pendiente = 21-----------------------------------------
        if (getIdEstado() == xIdEstadoProduccionPendiente) {

            //
            if (getIdCliente() == xIdClienteTodos) {

                //
                vectorBean = colaboraDctoOrdenBean.listaAllAlfabeticoProduccionPendiente(getIdTipoTercero());

            } else {

                //
                vectorBean = colaboraDctoOrdenBean.listaUnAlfabeticoProduccionPendiente(getIdTipoTercero());

            }
        }

            //--- CLIENTE sin factura = 51
        if (getIdEstado() == xIdEstadoSinFacturaCliente) {

            //
            if (getIdCliente() == xIdClienteTodos) {

                //
                vectorBean = colaboraDctoOrdenBean.listaAllAlfabeticoSinFactura(getIdTipoTercero());

            } else {

                //
                vectorBean = colaboraDctoOrdenBean.listaUnAlfabeticoSinFactura(getIdTipoTercero());

            }
        }

            //--- CLIENTE remision sin factura = 52
        if (getIdEstado() == xIdEstadoRemisionSinFacturaCliente) {

            //
            if (getIdCliente() == xIdClienteTodos) {

                //
                vectorBean = colaboraDctoOrdenBean.listaAllAlfabeticoRemisionSinFactura(getIdTipoTercero());

            } else {

                //
                vectorBean = colaboraDctoOrdenBean.listaUnAlfabeticoRemisionSinFactura(getIdTipoTercero());

            }
        }

            //--- CLIENTE GeneralMercadeo remision sin factura = 53
        if (getIdEstado() == xIdEstadoRemisionSinFacturaGeneralMercadeo) {

            //
            if (getIdCliente() == xIdClienteTodos) {

                //
                vectorBean = colaboraDctoOrdenBean.listaAllAlfabeticoGeneralMercadeo(getIdTipoTercero());

            } else {

                //
                vectorBean = colaboraDctoOrdenBean.listaUnAlfabeticoGeneralMercadeo(getIdTipoTercero());

            }
        }

            //--- CLIENTE HistoricoGeneralCliente = 54
        if (getIdEstado() == xIdEstadoHistoricoGeneralCliente) {

            //
            if (getIdCliente() == xIdClienteTodos) {

                //
                vectorBean = colaboraDctoOrdenBean.listaAllHistoricoGeneral(getIdTipoTercero());

            } else {

                //
                vectorBean = colaboraDctoOrdenBean.listaUnHistoricoGeneral(getIdTipoTercero());

            }
        }

            //---
        if (getIdEstado() == xIdEstadoNuevo) {

            //
            if (getIdCliente() == xIdClienteTodos) {

                //
                vectorBean = colaboraDctoOrdenBean.listaAllAlfabeticoNuevo(getIdTipoTercero());

            } else {

                //
                vectorBean = colaboraDctoOrdenBean.listaUnAlfabeticoNuevo(getIdTipoTercero());

            }
        }

            //--- CLIENTE facturado = 50
        if (getIdEstado() == xIdEstadoFacturadoCliente) {

            //
            if (getIdCliente() == xIdClienteTodos) {

                //
                vectorBean = colaboraDctoOrdenBean.listaAllFactura(getIdTipoTercero());

            } else {

                //
                vectorBean = colaboraDctoOrdenBean.listaUnFactura(getIdTipoTercero());

            }
        }


        //
        iteratorBean = vectorBean.iterator();

        if (!iteratorBean.hasNext()) {
            try {
                pageContext.getOut().write("<br>No existen datos");
            } catch (IOException ex) {
                throw new JspTagException("Error: la respuesta no se pudo escribir para los datos");
            } finally {
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

    public int doEndTag() throws JspTagException {
        return EVAL_PAGE;
    }
}
