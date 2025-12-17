package com.solucionesweb.tags;

import java.util.*;
import java.io.IOException;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.tagext.TagSupport;

// Importa el Bean de ColaboraDctoOrdenDetalleBean
import com.solucionesweb.losbeans.colaboraciones.ColaboraOrdenTrabajo;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaPluFicha;

public class ListaOrdenItemTag extends TagSupport
        implements IterationTag {

    // Variable que recibe del JSP
    private String idLocalTag;
    private String idTipoOrdenTag;
    private String numeroOrdenTag;

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

    public void setNumeroOrdenTag(String numeroOrdenTag) {
        this.numeroOrdenTag = numeroOrdenTag;
    }

    public String getNumeroOrdenTag() {
        return numeroOrdenTag;
    }

    public int getNumeroOrden() {
        return  new Integer(getNumeroOrdenTag()).intValue();
    }

    // Variable para usar el bean de fachada
    FachadaPluFicha fachadaPluFicha;

    // Variable para usar el bean de colaboraDctoOrdenDetalleBean
    ColaboraOrdenTrabajo colaboraOrdenTrabajo;

    // Iterador de objetos
    Iterator iteratorBean;

    private void inicializarVariablesDeRetorno() {

        //
        fachadaPluFicha =
                (FachadaPluFicha) iteratorBean.next();

        // Variable que retornan al JSP
        pageContext.setAttribute("referenciaClienteVar",
                fachadaPluFicha.getReferenciaCliente());
        pageContext.setAttribute("idPluVar",
                fachadaPluFicha.getIdPluStr());
        pageContext.setAttribute("nombreReferenciaVar",
                fachadaPluFicha.getNombreReferencia());
        pageContext.setAttribute("idOperacionVar",
                fachadaPluFicha.getIdOperacionStr());
        pageContext.setAttribute("nombrePluVar",
                fachadaPluFicha.getNombrePlu());
        pageContext.setAttribute("idFichaVar",
                fachadaPluFicha.getIdFichaStr());
        pageContext.setAttribute("referenciaVar",
                fachadaPluFicha.getReferencia());
        pageContext.setAttribute("numeroOrdenVar",
                fachadaPluFicha.getNumeroOrdenStr());
        pageContext.setAttribute("cantidadVar",
                fachadaPluFicha.getCantidadDf0());
        pageContext.setAttribute("itemVar",
                fachadaPluFicha.getItemStr());
        pageContext.setAttribute("fechaEntregaVar",
                fachadaPluFicha.getFechaEntregaCorta());

    }

    public int doStartTag() throws JspTagException {

        // Parametros llegados de JSP
        colaboraOrdenTrabajo = new ColaboraOrdenTrabajo();

        //
        colaboraOrdenTrabajo.setIdLocal(getIdLocalTag());
        colaboraOrdenTrabajo.setIdTipoOrden(getIdTipoOrdenTag());
        colaboraOrdenTrabajo.setNumeroOrden(getNumeroOrden());

        //
        Vector vectorBean = colaboraOrdenTrabajo.listaUnOrdenItem();

        iteratorBean = vectorBean.iterator();

        if (!iteratorBean.hasNext()) {
            try {
                pageContext.getOut().write("No existen datos <br/>");
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
