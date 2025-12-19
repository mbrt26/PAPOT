package com.solucionesweb.tags;

import java.util.*;
import java.io.IOException;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.tagext.TagSupport;

// Importa el bean de ColaboraResurtidoOrden
import com.solucionesweb.losbeans.colaboraciones.ColaboraReporteDctoBean;

// Importa el bean de FachadaColaboraDctoOrdenBean
import com.solucionesweb.losbeans.fachada.FachadaColaboraReporteDctoBean;

public class ListaRemisionTag extends TagSupport implements IterationTag {

    // Variable que recibe del JSP
    private String idLocalTag;
    private String idTipoOrdenTag;
    private String fechaInicialTag;
    private String fechaFinalTag;
    private String idTerceroTag;

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

    public void setIdTerceroTag(String idTerceroTag) {
        this.idTerceroTag = idTerceroTag;
    }

    public String getIdTerceroTag() {
        return idTerceroTag;
    }

    public double getIdTercero() {
        return new Double(getIdTerceroTag()).doubleValue();
    }

    public void setFechaInicialTag(String fechaInicialTag) {
        this.fechaInicialTag = fechaInicialTag;
    }

    public String getFechaInicialTag() {
        return fechaInicialTag;
    }

    public void setFechaFinalTag(String fechaFinalTag) {
        this.fechaFinalTag = fechaFinalTag;
    }

    public String getFechaFinalTag() {
        return fechaFinalTag;
    }
    //
    ColaboraReporteDctoBean colaboraReporteDctoBean;
    //
    FachadaColaboraReporteDctoBean fachada;
    // Iterador de objetos
    Iterator iteratorBean;

    private void inicializarVariablesDeRetorno() {

        //
        fachada = (FachadaColaboraReporteDctoBean) iteratorBean.next();

        // Variable que retornan al JSP
        pageContext.setAttribute("idLocalVar", fachada.getIdLocalStr());
        pageContext.setAttribute("idTipoOrdenVar", fachada.getIdTipoOrdenStr());
        pageContext.setAttribute("idOrdenVar", fachada.getIdOrdenStr());
        pageContext.setAttribute("idDctoVar", fachada.getIdDctoStr());
        pageContext.setAttribute("idClienteVar", fachada.getIdCliente());
        pageContext.setAttribute("fechaDctoVar", fachada.getFechaDctoFormato());
        pageContext.setAttribute("nombreTerceroVar", fachada.getNombreTercero());
        pageContext.setAttribute("cantidadVar", fachada.getCantidadDf2());
        pageContext.setAttribute("referenciaClienteVar", fachada.getReferenciaCliente());
        pageContext.setAttribute("numeroOrdenVar", fachada.getNumeroOrdenDf0());

    }

    public int doStartTag() throws JspTagException {

        // Parametros llegados de JSP
        colaboraReporteDctoBean = new ColaboraReporteDctoBean();

        //
        colaboraReporteDctoBean.setIdLocal(getIdLocalTag());
        colaboraReporteDctoBean.setIdTipoOrden(getIdTipoOrdenTag());
        colaboraReporteDctoBean.setFechaInicial(getFechaInicialTag());
        colaboraReporteDctoBean.setFechaFinal(getFechaFinalTag());
        colaboraReporteDctoBean.setIdCliente(getIdTerceroTag());

        //
        Vector vectorBean;

        //
        if (getIdTercero() == 0) {

            //
            vectorBean = colaboraReporteDctoBean.listaAllRemision();

        } else {

            vectorBean = colaboraReporteDctoBean.listaUnRemision();

        }

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
