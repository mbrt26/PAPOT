package com.solucionesweb.tags;

import java.util.*;
import java.io.IOException;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.tagext.TagSupport;

// Importa el Bean de Asignaturas
import com.solucionesweb.losbeans.colaboraciones.ColaboraOrdenTrabajo;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaPluFicha;

public class ListaOTEstadoPedidoTag extends TagSupport
        implements IterationTag {

    // Variable que recibe del JSP
    private String idLocalTag;
    private String idTipoOrdenTag;
    private String idOrdenTag;
    private String itemPadreTag;

    //
    public void setIdLocalTag(String idLocalTag) {
        this.idLocalTag = idLocalTag;
    }

    public String getIdLocalTag() {
        return idLocalTag;
    }

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

    public void setIdOrdenTag(String idOrdenTag) {
        this.idOrdenTag = idOrdenTag;
    }

    public String getIdOrdenTag() {
        return idOrdenTag;
    }

    public void setItemPadreTag(String itemPadreTag) {
        this.itemPadreTag = itemPadreTag;
    }

    public String getItemPadreTag() {
        return itemPadreTag;
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
        pageContext.setAttribute("idLocalVar",
                fachadaPluFicha.getIdLocalStr());
        pageContext.setAttribute("idTipoOrdenVar",
                fachadaPluFicha.getIdTipoOrdenStr());
        pageContext.setAttribute("idOrdenVar",
                fachadaPluFicha.getIdOrdenStr());
        pageContext.setAttribute("itemVar",
                fachadaPluFicha.getItemStr());
        pageContext.setAttribute("nombreOperacionVar",
                fachadaPluFicha.getNombreOperacion());
        pageContext.setAttribute("cantidadPedidaVar",
                fachadaPluFicha.getCantidadPedidaStr());
        pageContext.setAttribute("cantidadFacturadaVar",
                fachadaPluFicha.getCantidadFacturadaStr());
        pageContext.setAttribute("cantidadTerminadaVar",
                fachadaPluFicha.getCantidadTerminadaDf1());
        pageContext.setAttribute("pesoTerminadoVar",
                fachadaPluFicha.getPesoTerminadoDf1());
        pageContext.setAttribute("pesoPerdidoVar",
                fachadaPluFicha.getPesoPerdidoDf1());
        pageContext.setAttribute("cantidadPerdidaVar",
                fachadaPluFicha.getCantidadPerdidaStr());
        pageContext.setAttribute("pesoPedidoVar",
                fachadaPluFicha.getPesoPedidoDf0());
    }

    public int doStartTag() throws JspTagException {

        // Parametros llegados de JSP
        colaboraOrdenTrabajo = new ColaboraOrdenTrabajo();

        //
        colaboraOrdenTrabajo.setIdLocal(getIdLocalTag());
        colaboraOrdenTrabajo.setIdTipoOrden(getIdTipoOrdenTag());
        colaboraOrdenTrabajo.setIdOrden(getIdOrdenTag());
        colaboraOrdenTrabajo.setItemPadre(getItemPadreTag());

        //
        Vector vectorBean =
                colaboraOrdenTrabajo.listaOTEstadoPedido();

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
