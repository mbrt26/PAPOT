package com.solucionesweb.tags;

import com.solucionesweb.losbeans.fachada.FachadaTerceroClaseBean;
import com.solucionesweb.losbeans.negocio.TerceroClaseBean;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.IterationTag;
import javax.servlet.jsp.tagext.TagSupport;

public class ListaTerceroClaseTag
        extends TagSupport
        implements IterationTag {

    private int idClaseTag;
    private int idEstadoTag;
    private int idTipoTerceroTag;
    FachadaTerceroClaseBean fachadaTerceroClaseBean;
    TerceroClaseBean terceroClaseBean;
    Iterator iteratorBean;

    public int getIdClaseTag() {
        return idClaseTag;
    }

    public void setIdClaseTag(int idClaseTag) {
        this.idClaseTag = idClaseTag;
    }

    public void setIdClaseTag(String idClaseTag) {
        this.idClaseTag = Integer.parseInt(idClaseTag);
    }

    public int getIdEstadoTag() {
        return idEstadoTag;
    }

    public void setIdEstadoTag(int idEstadoTag) {
        this.idEstadoTag = idEstadoTag;
    }

    public void setIdEstadoTag(String idEstadoTag) {
        this.idEstadoTag = Integer.parseInt(idEstadoTag);
    }

    private void inicializarVariablesDeRetorno() {
        fachadaTerceroClaseBean = ((FachadaTerceroClaseBean) iteratorBean.next());

        pageContext.setAttribute("idClaseVar", fachadaTerceroClaseBean.getIdClaseStr());

        pageContext.setAttribute("nombreClaseVar", fachadaTerceroClaseBean.getNombreClase());
    }

    public int doStartTag() throws JspTagException {

        terceroClaseBean = new TerceroClaseBean();

        ArrayList listaVector = terceroClaseBean.listaTerceroClase(getIdEstadoTag(),
                getIdTipoTerceroTag());

        //
        iteratorBean = listaVector.iterator();

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

    public int getIdTipoTerceroTag() {
        return idTipoTerceroTag;
    }

    public void setIdTipoTerceroTag(int idTipoTerceroTag) {
        this.idTipoTerceroTag = idTipoTerceroTag;
    }

    public void setIdTipoTerceroTag(String idTipoTerceroTag) {
        this.idTipoTerceroTag = Integer.parseInt(idTipoTerceroTag);
    }
}
