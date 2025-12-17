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

public class LiquidaOTExternaTag extends TagSupport
        implements IterationTag {

    // Variable que recibe del JSP
    private String idLocalTag;
    private String idTipoOrdenTag;
    private String idLogTag;
    private String idOperacionTag;
    private String itemPadreTag;
    private String idControlTipoTag;
    //
    NumberFormat nf = NumberFormat.getNumberInstance();

    // Metodos Tag
    public void setIdLogTag(String idLogTag) {
        this.idLogTag = idLogTag;
    }

    public String getIdLogTag() {
        return idLogTag;
    }

    public int getIdLog() {
        return new Integer(getIdLogTag()).intValue();
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

    public String getIdControlTipoTag() {
        return idControlTipoTag;
    }

    public void setIdControlTipoTag(String idControlTipoTag) {
        this.idControlTipoTag = idControlTipoTag;
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
        pageContext.setAttribute("idLocalVar",
                fachadaDctoOrdenProgreso.getIdLocalStr());
        pageContext.setAttribute("idTipoOrdenVar",
                fachadaDctoOrdenProgreso.getIdTipoOrdenStr());
        pageContext.setAttribute("idOrdenVar",
                fachadaDctoOrdenProgreso.getIdOrdenStr());
        pageContext.setAttribute("itemVar",
                fachadaDctoOrdenProgreso.getItemStr());
        pageContext.setAttribute("idOperacionVar",
                fachadaDctoOrdenProgreso.getIdOperacionStr());
        pageContext.setAttribute("idOperarioVar",
                fachadaDctoOrdenProgreso.getIdOperarioSf0());
        pageContext.setAttribute("cantidadPerdidaVar",
                fachadaDctoOrdenProgreso.getCantidadPerdidaStr());
        pageContext.setAttribute("cantidadTerminadaVar",
                fachadaDctoOrdenProgreso.getCantidadTerminadaStr());
        pageContext.setAttribute("pesoPerdidoVar",
                fachadaDctoOrdenProgreso.getPesoPerdidoStr());
        pageContext.setAttribute("pesoTerminadoVar",
                fachadaDctoOrdenProgreso.getPesoTerminadoStr());
        pageContext.setAttribute("fechaInicioVar",
                fachadaDctoOrdenProgreso.getFechaInicio());
        pageContext.setAttribute("fechaFinVar",
                fachadaDctoOrdenProgreso.getFechaFinHHMM());
        pageContext.setAttribute("idCausaVar",
                fachadaDctoOrdenProgreso.getIdCausaStr());
        pageContext.setAttribute("estadoVar",
                fachadaDctoOrdenProgreso.getEstadoStr());
        pageContext.setAttribute("nombreOperarioVar",
                fachadaDctoOrdenProgreso.getNombreOperario());
        pageContext.setAttribute("cantidadPedidaVar",
                fachadaDctoOrdenProgreso.getCantidadPedidaDf0());
        pageContext.setAttribute("pesoPedidoVar",
                fachadaDctoOrdenProgreso.getPesoPedidoDf0());
        pageContext.setAttribute("idControlVar",
                fachadaDctoOrdenProgreso.getIdControlStr());
        pageContext.setAttribute("idPluVar",
                fachadaDctoOrdenProgreso.getIdPluStr());
        pageContext.setAttribute("nombrePluVar",
                fachadaDctoOrdenProgreso.getNombrePlu());
        pageContext.setAttribute("referenciaClienteVar",
                fachadaDctoOrdenProgreso.getReferenciaCliente());

    }

    public int doStartTag() throws JspTagException {

        // Parametros llegados de JSP
        colaboraOrdenProgreso = new ColaboraOrdenProgreso();

        //
        colaboraOrdenProgreso.setIdTipoOrden(getIdTipoOrdenTag());
        colaboraOrdenProgreso.setIdLocal(getIdLocalTag());
        colaboraOrdenProgreso.setIdOperacion(getIdOperacionTag());
        colaboraOrdenProgreso.setItemPadre(getItemPadreTag());
        colaboraOrdenProgreso.setIdControlTipo(getIdControlTipoTag());

        //
        Vector vectorBean = colaboraOrdenProgreso.listaOTExterna(getIdLog());

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
