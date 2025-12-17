package com.solucionesweb.tags;

import java.io.IOException;
import javax.servlet.jsp.JspTagException;

// Importa la clase que posibilita el formateo
import java.text.DecimalFormat;

//
import java.util.*;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.tagext.TagSupport;

// Importa el bean de ColaboraResurtido
import com.solucionesweb.losbeans.colaboraciones.ColaboraResurtidoDetalle;

// Importa el bean de FachadaDctoOrdenDetalleBean
import com.solucionesweb.losbeans.fachada.FachadaDctoOrdenDetalleBean;

//
public class ListaResurtidoDetalleTag
        extends TagSupport implements IterationTag {

    // Variable que recibe del JSP
    private String idLocalTag;
    private String idLogTag;
    private String idTipoOrdenTag;

    //
    DecimalFormat df0 = new DecimalFormat("###,###,###,###");

    //
    double xCero = 0.0;

    // Metodos Tag
    public void setIdLocalTag(String idLocalTag) {
        this.idLocalTag = idLocalTag;
    }

    public String getIdLocalTag() {
        return idLocalTag;
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
    ColaboraResurtidoDetalle colabora;
    //
    FachadaDctoOrdenDetalleBean fachada;
    // Iterador de objetos
    Iterator iteratorBean;

    private void inicializarVariablesDeRetorno() {

        //
        fachada = (FachadaDctoOrdenDetalleBean) iteratorBean.next();

        //
        Double xVrCostoResurtidoTotal = ( fachada.getVrCostoResurtido() +
                                          fachada.getVrIvaResurtido()   +
                                          fachada.getVrImpoconsumo() ) ;

        //
        fachada.setCantidadAjuste(fachada.getCantidad()
                - fachada.getCantidadPedido());

        //
        fachada.setCantidadPendiente(fachada.getCantidadPedido()
                - fachada.getCantidadFacturada());

        //---
        if (fachada.getCantidadPendiente() <= xCero ) {

            //
            fachada.setCantidad(xCero);
            fachada.setVrCostoResurtido(xCero);
            fachada.setVrIvaResurtido(xCero);
            fachada.setVrIvaResurtido(xCero);
            fachada.setPorcentajeIva(xCero);
            xVrCostoResurtidoTotal = xCero;

        }

        // Variable que retornan al JSP
        pageContext.setAttribute("idLocalVar", fachada.getIdLocalStr());
        pageContext.setAttribute("nombrePluVar", fachada.getNombrePlu());
        pageContext.setAttribute("nombreMarcaVar", fachada.getNombreMarca());
        pageContext.setAttribute("nombreCategoriaVar",
                fachada.getNombreCategoria());
        pageContext.setAttribute("idTipoOrdenVar",
                fachada.getIdTipoOrdenStr());
        pageContext.setAttribute("idOrdenVar", fachada.getIdOrdenStr());
        pageContext.setAttribute("idPluVar", fachada.getIdPluStr());
        pageContext.setAttribute("porcentajeIvaVar",
                fachada.getPorcentajeIvaDf0());
        pageContext.setAttribute("vrCostoVar", fachada.getVrCostoDf2());
        pageContext.setAttribute("vrCostoNegociadoVar",
                fachada.getVrCostoNegociadoDf2());
        pageContext.setAttribute("idLogVar", fachada.getIdLogStr());
        pageContext.setAttribute("cantidadPedidoVar",
                fachada.getCantidadPedidoDf2());
        pageContext.setAttribute("vrCostoPedidoVar",
                fachada.getVrCostoPedidoStr());
        pageContext.setAttribute("cantidadDf2Var", fachada.getCantidadDf2());
        pageContext.setAttribute("cantidadAjusteVar",
                fachada.getCantidadAjusteDf2());
        pageContext.setAttribute("cantidadVar", fachada.getCantidadStr());
        pageContext.setAttribute("vrCostoPedidoDf0Var",
                fachada.getVrCostoPedidoDf0());
        pageContext.setAttribute("vrCostoResurtidoVar",
                fachada.getVrCostoResurtidoStr());
        pageContext.setAttribute("vrCostoResurtidoDf0Var",
                fachada.getVrCostoResurtidoDf0());
        pageContext.setAttribute("vrCostoResurtidoSf0Var",
                                              fachada.getVrCostoResurtidoSf0());
        pageContext.setAttribute("vrIvaResurtidoSf0Var",
                                                fachada.getVrIvaResurtidoSf0());
        pageContext.setAttribute("vrImpoconsumoSf0Var",
                                                 fachada.getVrImpoconsumoSf0());
        pageContext.setAttribute("vrCostoResurtidoTotalDf0Var",
                                             df0.format(xVrCostoResurtidoTotal));
        pageContext.setAttribute("cantidadPendienteVar",
                fachada.getCantidadPendienteDf0());
        pageContext.setAttribute("cantidadPendienteStrVar",
                fachada.getCantidadPendienteStr());

    }

    public int doStartTag() throws JspTagException {

        //
        ColaboraResurtidoDetalle colabora;

        //
        FachadaDctoOrdenDetalleBean fachada;

        // Parametros llegados de JSP
        colabora = new ColaboraResurtidoDetalle();

        //
        colabora.setIdLocal(getIdLocalTag());
        colabora.setIdTipoOrden(getIdTipoOrdenTag());
        colabora.setIdLog(getIdLogTag());

        //
        Vector vectorBean = colabora.listaLegaliza();

        //
        iteratorBean = vectorBean.iterator();

        //
        if (!iteratorBean.hasNext()) {
            try {
                pageContext.getOut().write("La consulta no devolvio ningun dato <br/>");
                pageContext.getOut().write("<br/>");
                pageContext.getOut().write("Por favor comunique al administrador<br/>");
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

            //
            inicializarVariablesDeRetorno();
            return EVAL_BODY_AGAIN;
        }

        return SKIP_BODY;

    }

    public int doEndTag() throws JspTagException {
        return EVAL_PAGE;
    }
}
