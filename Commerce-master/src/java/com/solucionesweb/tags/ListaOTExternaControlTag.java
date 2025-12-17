package com.solucionesweb.tags;

import java.util.*;
import java.io.IOException;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.tagext.TagSupport;

// Importa la clase que posibilita el formateo
import java.text.NumberFormat;

// Importa el Bean de Asignaturas
import com.solucionesweb.losbeans.colaboraciones.ColaboraOrdenProgreso;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaDctoOrdenProgreso;

public class ListaOTExternaControlTag extends TagSupport
        implements IterationTag {

    // Variable que recibe del JSP
    private String idLocalTag;
    private String idTipoOrdenTag;
    private String idOrdenTag;
    private String idOperacionTag;
    private String itemPadreTag;
    private String idControlTag;

    //
    NumberFormat nf = NumberFormat.getNumberInstance();

    // Metodos Tag
    public void setIdOrdenTag(String dOrdenTag) {
        this.idOrdenTag = dOrdenTag;
    }

    public String getIdOrdenTag() {
        return idOrdenTag;
    }

    public int getIdOrden() {
        return new Integer(getIdOrdenTag()).intValue() ;
    }

    public void setIdTipoOrdenTag(String idTipoOrdenTag) {
        this.idTipoOrdenTag = idTipoOrdenTag;
    }

    public String getIdTipoOrdenTag() {
        return idTipoOrdenTag;
    }

    public void setIdLocalTag(String idLocalTag) {
        this.idLocalTag = idLocalTag;
    }

    public String getIdLocalTag() {
        return idLocalTag;
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

    public void setIdControlTag(String idControlTag) {
        this.idControlTag = idControlTag;
    }

    public String getIdControlTag() {
        return idControlTag;
    }

    // Variable para usar el bean de fachada
    FachadaDctoOrdenProgreso fachadaDctoOrdenProgreso;
    // Variable para usar el bean de EstadoPcBean
    ColaboraOrdenProgreso colaboraOrdenProgreso;
    // Iterador de objetos
    Iterator iteratorBean;

    private void inicializarVariablesDeRetorno() {

        //
        fachadaDctoOrdenProgreso =
                (FachadaDctoOrdenProgreso) iteratorBean.next();

        // Variable que retornan al JSP
        pageContext.setAttribute("idPluVar",
                                 fachadaDctoOrdenProgreso.getIdPluStr());
        pageContext.setAttribute("itemVar",
                                 fachadaDctoOrdenProgreso.getItemStr());
        pageContext.setAttribute("nombrePluVar",
                                 fachadaDctoOrdenProgreso.getNombrePlu());
        pageContext.setAttribute("referenciaClienteVar",
                                 fachadaDctoOrdenProgreso.getReferenciaCliente());

    }

    public int doStartTag() throws JspTagException {

        // Parametros llegados de JSP
        colaboraOrdenProgreso = new ColaboraOrdenProgreso();

        //
        colaboraOrdenProgreso.setIdLocal(getIdLocalTag());
        colaboraOrdenProgreso.setIdTipoOrden(getIdTipoOrdenTag());
        colaboraOrdenProgreso.setIdOrden(getIdOrdenTag());
        colaboraOrdenProgreso.setIdOperacion(getIdOperacionTag());
        colaboraOrdenProgreso.setItemPadre(getItemPadreTag());
        colaboraOrdenProgreso.setIdControl(getIdControlTag());

        //
        Vector vectorBean = colaboraOrdenProgreso.listaUnOTExternaPlu();

        //
        iteratorBean = vectorBean.iterator();

        //
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
