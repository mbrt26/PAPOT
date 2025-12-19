package com.solucionesweb.tags;

import java.util.*;
import java.io.IOException;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.tagext.TagSupport;

// Importa el Bean de Asignaturas
import com.solucionesweb.losbeans.colaboraciones.ColaboraCosto;

// Importa el Bean de Fachada
import com.solucionesweb.losbeans.fachada.FachadaDctoOrdenProgreso;

public class ListaOTCostoTag extends TagSupport
        implements IterationTag {

    // Variable que recibe del JSP
    private String idLocalTag;
    private String idTipoOrdenTag;
    private String fechaInicioTag;
    private String fechaFinTag;

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

    public int getIdTipoOrden() {
        return new Integer(getIdTipoOrdenTag());
    }

    public void setFechaInicioTag(String fechaInicioTag) {
        this.fechaInicioTag = fechaInicioTag;
    }

    public String getFechaInicioTag() {
        return fechaInicioTag;
    }

    public void setFechaFinTag(String fechaFinTag) {
        this.fechaFinTag = fechaFinTag;
    }

    public String getFechaFinTag() {
        return fechaFinTag;
    }


    // Variable para usar el bean de fachada
    FachadaDctoOrdenProgreso fachadaDctoOrdenProgreso;
    // Variable para usar el bean de EstadoPcBean

    //
    ColaboraCosto colaboraCosto;

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
        pageContext.setAttribute("fechaOrdenVar",
                fachadaDctoOrdenProgreso.getFechaOrdenCorta());
        pageContext.setAttribute("idClienteVar",
                fachadaDctoOrdenProgreso.getIdCliente());
        pageContext.setAttribute("numeroOrdenVar",
                fachadaDctoOrdenProgreso.getNumeroOrdenStr());
        pageContext.setAttribute("idFichaVar",
                fachadaDctoOrdenProgreso.getIdFichaStr());
        pageContext.setAttribute("nombreTerceroVar",
                fachadaDctoOrdenProgreso.getNombreTercero());
        pageContext.setAttribute("itemPadreVar",
                fachadaDctoOrdenProgreso.getItemPadreStr());
        pageContext.setAttribute("idOperacionVar",
                fachadaDctoOrdenProgreso.getIdOperacionStr());
        pageContext.setAttribute("nombreOperacionVar",
                fachadaDctoOrdenProgreso.getNombreOperacion());
        pageContext.setAttribute("vrCostoBaseMATVar",
                fachadaDctoOrdenProgreso.getVrCostoBaseMATSf0());
        pageContext.setAttribute("vrCostoBaseCIFVar",
                fachadaDctoOrdenProgreso.getVrCostoBaseCIFSf0());
        pageContext.setAttribute("vrCostoMODVar",
                fachadaDctoOrdenProgreso.getVrCostoBaseMODSf0());
        pageContext.setAttribute("pesoTerminadoVar",
                fachadaDctoOrdenProgreso.getPesoTerminadoDf1());

    }

    public int doStartTag() throws JspTagException {

        // Parametros llegados de JSP
        colaboraCosto = new ColaboraCosto();

        //
        colaboraCosto.setIdLocal(getIdLocalTag());
        colaboraCosto.setIdTipoOrden(getIdTipoOrdenTag());
        colaboraCosto.setFechaInicio(getFechaInicioTag());
        colaboraCosto.setFechaFin(getFechaFinTag());

        //
        Vector vectorBean  = colaboraCosto.lista();

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
