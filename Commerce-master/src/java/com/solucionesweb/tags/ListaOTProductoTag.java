package com.solucionesweb.tags;

import java.util.*;
import java.io.IOException;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.tagext.TagSupport;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaPluFicha;

// Importa el Bean de Asignaturas
import com.solucionesweb.losbeans.colaboraciones.ColaboraOrdenTrabajo;

public class ListaOTProductoTag extends TagSupport
        implements IterationTag {

    // Variable que recibe del JSP
    private String idLocalTag;
    private String idTipoOrdenTag;
    private String numeroOrdenTag;

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

    public void setNumeroOrdenTag(String numeroOrdenTag) {
        this.numeroOrdenTag = numeroOrdenTag;
    }

    public String getNumeroOrdenTag() {
        return numeroOrdenTag;
    }

    // Variable para usar el bean de fachada
    FachadaPluFicha fachadaPluFicha;

    // Variable para usar el bean de EstadoPcBean
    ColaboraOrdenTrabajo colaboraOrdenTrabajo;

    // Iterador de objetos
    Iterator iteratorBean;

    private void inicializarVariablesDeRetorno() {

        //
        fachadaPluFicha =
                (FachadaPluFicha) iteratorBean.next();

        // Variable que retornan al JSP
        pageContext.setAttribute("idClienteVar",
                fachadaPluFicha.getIdCliente());
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
                fachadaPluFicha.getCantidadStr());
        pageContext.setAttribute("itemVar",
                fachadaPluFicha.getItemStr());
        pageContext.setAttribute("fechaEntregaVar",
                fachadaPluFicha.getFechaEntregaCorta());
        pageContext.setAttribute("idLogVar",
                fachadaPluFicha.getIdLogStr());
        pageContext.setAttribute("cantidadDf0Var",
                fachadaPluFicha.getCantidadDf0());

    }

    public int doStartTag() throws JspTagException {

        // Parametros llegados de JSP
        colaboraOrdenTrabajo = new ColaboraOrdenTrabajo();

        //
        colaboraOrdenTrabajo.setIdLocal(getIdLocalTag());
        colaboraOrdenTrabajo.setIdTipoOrden(getIdTipoOrdenTag());
        colaboraOrdenTrabajo.setNumeroOrden(getNumeroOrdenTag());

        //
        Vector vectorBean =
                colaboraOrdenTrabajo.listaUnOrdenItem();

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


