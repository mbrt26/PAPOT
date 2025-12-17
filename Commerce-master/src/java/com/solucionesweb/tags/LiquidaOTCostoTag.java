package com.solucionesweb.tags;

import java.util.*;
import java.io.IOException;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.tagext.TagSupport;

// Importa el Bean de Asignaturas
import com.solucionesweb.losbeans.colaboraciones.ColaboraOrdenDetalleBean;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaDctoOrdenDetalleBean;

public class LiquidaOTCostoTag extends TagSupport
        implements IterationTag {

    // Variable que recibe del JSP
    private String idLocalTag;
    private String idTipoOrdenTag;
    private String idOrdenTag;
    private String idOperacionTag;
    private String itemPadreTag;

    // Metodos Tag
    public void setIdLocalTag(String idLocalTag) {
        this.idLocalTag = idLocalTag;
    }

    public String getIdLocalTag() {
        return idLocalTag;
    }

    public void setIdOrdenTag(String idOrdenTag) {
        this.idOrdenTag = idOrdenTag;
    }

    public String getIdOrdenTag() {
        return idOrdenTag;
    }

    public void setIdTipoOrdenTag(String idTipoOrdenTag) {
        this.idTipoOrdenTag = idTipoOrdenTag;
    }

    public String getIdTipoOrdenTag() {
        return idTipoOrdenTag;
    }

    public void setIdOperacionTag(String idOperacionTag) {
        this.idOperacionTag = idOperacionTag;
    }

    public String getIdOperacionTag() {
        return idOperacionTag;
    }

    public void setItemPadreTag(String itemPadreTag) {
        this.itemPadreTag = itemPadreTag;
    }

    public String getItemPadreTag() {
        return itemPadreTag;
    }

    // Variable para usar el bean de fachada
    FachadaDctoOrdenDetalleBean fachadaDctoOrdenDetalleBean;
    // Variable para usar el bean de EstadoPcBean
    ColaboraOrdenDetalleBean colaboraOrdenDetalleBean;
    // Iterador de objetos
    Iterator iteratorBean;

    private void inicializarVariablesDeRetorno() {

        //
        fachadaDctoOrdenDetalleBean =
                (FachadaDctoOrdenDetalleBean) iteratorBean.next();

        // Variable que retornan al JSP
        pageContext.setAttribute("idLocalVar",
                fachadaDctoOrdenDetalleBean.getIdLocalStr());
        pageContext.setAttribute("idTipoOrdenVar",
                fachadaDctoOrdenDetalleBean.getIdTipoOrdenStr());
        pageContext.setAttribute("idOrdenVar",
                fachadaDctoOrdenDetalleBean.getIdOrdenStr());
        pageContext.setAttribute("itemVar",
                fachadaDctoOrdenDetalleBean.getItemStr());
        pageContext.setAttribute("cantidadVar",
                fachadaDctoOrdenDetalleBean.getCantidadStr());
        pageContext.setAttribute("vrCostoVar",
                fachadaDctoOrdenDetalleBean.getVrCostoStr());
        pageContext.setAttribute("nombrePluVar",
                fachadaDctoOrdenDetalleBean.getNombrePlu());
        pageContext.setAttribute("idLogVar",
                fachadaDctoOrdenDetalleBean.getIdLogStr());
        pageContext.setAttribute("idPluVar",
                fachadaDctoOrdenDetalleBean.getIdPluStr());
        pageContext.setAttribute("idFichaVar",
                fachadaDctoOrdenDetalleBean.getIdFichaStr());
        pageContext.setAttribute("idUsuarioVar",
                fachadaDctoOrdenDetalleBean.getIdUsuarioStr());
    }

    public int doStartTag() throws JspTagException {

        // Parametros llegados de JSP
        colaboraOrdenDetalleBean = new ColaboraOrdenDetalleBean();

        //
        colaboraOrdenDetalleBean.setIdLocalOrigen(getIdLocalTag());
        colaboraOrdenDetalleBean.setIdTipoOrdenOrigen(getIdTipoOrdenTag());
        colaboraOrdenDetalleBean.setIdOrdenOrigen(getIdOrdenTag());
        colaboraOrdenDetalleBean.setIdOperacion(getIdOperacionTag());
        colaboraOrdenDetalleBean.setItemPadre(getItemPadreTag());

        //
        Vector vectorBean = colaboraOrdenDetalleBean.listaOTCosto();

        //
        iteratorBean = vectorBean.iterator();

        if (!iteratorBean.hasNext()) {
            try {
                pageContext.getOut().write("");
            } catch (IOException ex) {
                throw new JspTagException("");
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

