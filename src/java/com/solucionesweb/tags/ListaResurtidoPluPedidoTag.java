package com.solucionesweb.tags;

import java.util.*;
import java.io.IOException;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.tagext.TagSupport;

// Importa el bean de ColaboraResurtido
import com.solucionesweb.losbeans.colaboraciones.ColaboraDctoOrdenDetalleBean;

// Importa el bean de FachadaDctoOrdenDetalleBean
import com.solucionesweb.losbeans.fachada.FachadaDctoOrdenDetalleBean;

public class ListaResurtidoPluPedidoTag
        extends TagSupport implements IterationTag {

    // Variable que recibe del JSP
    private String idLocalTag;
    private String idTipoOrdenTag;
    private String idLogTag;
    private String idPluTag;

    // Metodos Tag
    public void setIdLocalTag(String idLocalTag) {
        this.idLocalTag = idLocalTag;
    }

    public String getIdLocalTag() {
        return idLocalTag;
    }

    public void setIdTipoOrdenTag(String idTipoOrdenTag) {
        this.idTipoOrdenTag = idTipoOrdenTag;
    }

    public String getIdTipoOrdenTag() {
        return idTipoOrdenTag;
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

    public void setIdPluTag(String idPluTag) {
        this.idPluTag = idPluTag;
    }

    public String getIdPluTag() {
        return idPluTag;
    }

    //
    ColaboraDctoOrdenDetalleBean colaboraDetalleBean;

    //
    FachadaDctoOrdenDetalleBean fachada;

    // Iterador de objetos
    Iterator iteratorBean;

    private void inicializarVariablesDeRetorno() {

        //
        fachada = (FachadaDctoOrdenDetalleBean) iteratorBean.next();


        // Variable que retornan al JSP
        pageContext.setAttribute("cantidadPedidoVar", fachada.getCantidadDf2());
        pageContext.setAttribute("vrCostoPedidoVar", 
                                              fachada.getVrCostoNegociadoDf2());

    }

    public int doStartTag() throws JspTagException {

        // Parametros llegados de JSP
        colaboraDetalleBean = new ColaboraDctoOrdenDetalleBean();

        //
        colaboraDetalleBean.setIdLocal(getIdLocalTag());
        colaboraDetalleBean.setIdPlu(getIdPluTag());
        colaboraDetalleBean.setIdTipoOrden(getIdTipoOrdenTag());

        //
        Vector vectorBean = colaboraDetalleBean.listaPlu(getIdLog());

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