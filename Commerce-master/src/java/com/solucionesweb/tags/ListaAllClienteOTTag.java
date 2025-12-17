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

public class ListaAllClienteOTTag extends TagSupport
        implements IterationTag {

    // Variable que recibe del JSP
    private String idLocalTag;
    private String idTipoOrdenTag;
    private String estadoTag;
    private String idTipoTerceroTag;

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

    public void setEstadoTag(String estadoTag) {
        this.estadoTag = estadoTag;
    }

    public String getEstadoTag() {
        return estadoTag;
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
        pageContext.setAttribute("idClienteVar",
                fachadaColaboraDctoOrdenBean.getIdCliente());
        pageContext.setAttribute("nombreTerceroVar",
                fachadaColaboraDctoOrdenBean.getNombreTercero());
    }

    public int doStartTag() throws JspTagException {

        // Parametros llegados de JSP
        colaboraDctoOrdenBean = new ColaboraDctoOrdenBean();

        //
        colaboraDctoOrdenBean.setIdLocal(getIdLocalTag());
        colaboraDctoOrdenBean.setIdTipoOrden(getIdTipoOrdenTag());
        colaboraDctoOrdenBean.setEstado(getEstadoTag());

        //
        Vector vectorBean =
                colaboraDctoOrdenBean.listaAllOT(getIdTipoTercero());

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
