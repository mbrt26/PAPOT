package com.solucionesweb.tags;

import java.util.*;
import java.io.IOException;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.tagext.TagSupport;

// Importa el Bean de Asignaturas
import com.solucionesweb.losbeans.colaboraciones.ColaboraOrdenProgreso;

// Importa el Bean de Fachada
import com.solucionesweb.losbeans.fachada.FachadaDctoOrdenProgreso;

public class ListaOTProgresoTag extends TagSupport
        implements IterationTag {

    // Variable que recibe del JSP
    private String idLocalTag;
    private String idTipoOrdenTag;
    private String idClienteTag;
    private String estadoTag;
    private String idTipoTerceroTag;
    private String idOperacionTag;

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

    public void setIdClienteTag(String idClienteTag) {
        this.idClienteTag = idClienteTag;
    }

    public String getIdClienteTag() {
        return idClienteTag;
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

    public void setIdOperacionTag(String idOperacionTag) {
        this.idOperacionTag = idOperacionTag;
    }

    public String getIdOperacionTag() {
        return idOperacionTag;
    }

    public int getIdOperacion() {
        return new Integer(getIdOperacionTag()).intValue();
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
        pageContext.setAttribute("idOperacionVar",
                fachadaDctoOrdenProgreso.getIdOperacionStr());
        pageContext.setAttribute("idClienteVar",
                fachadaDctoOrdenProgreso.getIdCliente());
        pageContext.setAttribute("nombreOperacionVar",
                fachadaDctoOrdenProgreso.getNombreOperacion());
        pageContext.setAttribute("itemVar",
                fachadaDctoOrdenProgreso.getItemStr());        
        pageContext.setAttribute("fechaOrdenVar",
                fachadaDctoOrdenProgreso.getFechaOrdenCorta());        
        pageContext.setAttribute("idLogVar",
                fachadaDctoOrdenProgreso.getIdLogStr());
        pageContext.setAttribute("idFichaVar",
                fachadaDctoOrdenProgreso.getIdFichaStr()); 
        pageContext.setAttribute("ordenCompraVar",
                                fachadaDctoOrdenProgreso.getOrdenCompra());  
        pageContext.setAttribute("fechaEntregaVar",
                fachadaDctoOrdenProgreso.getFechaEntregaCorta());    
        pageContext.setAttribute("itemPadreVar",
                fachadaDctoOrdenProgreso.getItemPadreStr()); 
        pageContext.setAttribute("cantidadVar",
                fachadaDctoOrdenProgreso.getCantidadDf0());
        pageContext.setAttribute("cantidadPerdidaVar",
                fachadaDctoOrdenProgreso.getCantidadPerdidaDf0());
        pageContext.setAttribute("cantidadTerminadaVar",
                fachadaDctoOrdenProgreso.getCantidadTerminadaDf0());
        pageContext.setAttribute("pesoPerdidoVar",
                fachadaDctoOrdenProgreso.getPesoPerdidoDf1());
        pageContext.setAttribute("pesoTerminadoVar",
                fachadaDctoOrdenProgreso.getPesoTerminadoDf1());
        pageContext.setAttribute("nombreTerceroVar",
                fachadaDctoOrdenProgreso.getNombreTercero());

    }

    public int doStartTag() throws JspTagException {

        // Parametros llegados de JSP
        colaboraOrdenProgreso = new ColaboraOrdenProgreso();

        //
        colaboraOrdenProgreso.setIdLocal(getIdLocalTag());
        colaboraOrdenProgreso.setIdTipoOrden(getIdTipoOrdenTag());
        colaboraOrdenProgreso.setEstado(getEstadoTag());
        colaboraOrdenProgreso.setIdCliente(getIdClienteTag());
        colaboraOrdenProgreso.setIdOperacion(getIdOperacionTag());

        //
        Vector vectorBean  = new Vector();

        //
        if (getIdOperacion() == 0) {

           vectorBean=
                  colaboraOrdenProgreso.listaOTAllOperacion(getIdTipoTercero());

        }

        //
        if (getIdOperacion() > 0) {

           vectorBean=
                  colaboraOrdenProgreso.listaOTUnOperacion(getIdTipoTercero());

        }

        //
        iteratorBean = vectorBean.iterator();

        //
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
