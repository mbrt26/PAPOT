package com.solucionesweb.tags;

import java.util.*;
import java.io.IOException;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.tagext.TagSupport;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaDctoOrdenProgreso;

// Importa el Bean de Asignaturas
import com.solucionesweb.losbeans.colaboraciones.ColaboraOrdenProgreso;

public class ListaOTExternaAllTag extends TagSupport
        implements IterationTag {

    // Variable que recibe del JSP
    private String idLocalTag;
    private String idTipoOrdenTag;


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
        pageContext.setAttribute("numeroOrdenVar",
                fachadaDctoOrdenProgreso.getNumeroOrdenStr());
        pageContext.setAttribute("itemVar",
                fachadaDctoOrdenProgreso.getItemPadreStr());
        pageContext.setAttribute("cantidadTerminadaVar",
                fachadaDctoOrdenProgreso.getCantidadTerminadaDf0());
        pageContext.setAttribute("pesoTerminadoVar",
                fachadaDctoOrdenProgreso.getPesoTerminadoDf0());
        pageContext.setAttribute("nombreOperacionVar",
                fachadaDctoOrdenProgreso.getNombreOperacion());
        pageContext.setAttribute("nombreTerceroVar",
                fachadaDctoOrdenProgreso.getNombreTercero());
        pageContext.setAttribute("referenciaClienteVar",
                fachadaDctoOrdenProgreso.getReferenciaCliente());
        pageContext.setAttribute("nombreTerceroOperacionVar",
                fachadaDctoOrdenProgreso.getNombreTerceroOperacion());

    }

    public int doStartTag() throws JspTagException {

        // Parametros llegados de JSP
        colaboraOrdenProgreso = new ColaboraOrdenProgreso();

        //
        colaboraOrdenProgreso.setIdLocal(getIdLocalTag());
        colaboraOrdenProgreso.setIdTipoOrden(getIdTipoOrdenTag());

        //
        Vector vectorBean =
                colaboraOrdenProgreso.listaOTExternaAllMaterial();

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
