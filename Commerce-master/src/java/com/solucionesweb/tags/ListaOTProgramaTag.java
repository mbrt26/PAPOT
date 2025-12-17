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

public class ListaOTProgramaTag extends TagSupport
        implements IterationTag {

    // Variable que recibe del JSP
    private String idLocalTag;
    private String idTipoOrdenTag;
    private String idOperacionTag;
    private String fechaProgramaTag;
    private String vrEscalaTag;

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

    public void setIdOperacionTag(String idOperacionTag) {
        this.idOperacionTag = idOperacionTag;
    }

    public String getIdOperacionTag() {
        return idOperacionTag;
    }

    public void setFechaProgramaTag(String fechaProgramaTag) {
        this.fechaProgramaTag = fechaProgramaTag;
    }

    public String getFechaProgramaTag() {
        return fechaProgramaTag;
    }

    public void setVrEscalaTag(String vrEscalaTag) {
        this.vrEscalaTag = vrEscalaTag;
    }

    public String getVrEscalaTag() {
        return vrEscalaTag;
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
        pageContext.setAttribute("itemPadreVar",
                fachadaPluFicha.getItemPadreStr());
        pageContext.setAttribute("cantidadPerdidaVar",
                fachadaPluFicha.getCantidadPerdidaStr());
        pageContext.setAttribute("pesoPerdidoVar",
                fachadaPluFicha.getPesoPerdidoStr());
        pageContext.setAttribute("pesoTerminadoVar",
                fachadaPluFicha.getPesoTerminadoStr());
        pageContext.setAttribute("numeroOrdenVar",
                fachadaPluFicha.getNumeroOrdenStr());
        pageContext.setAttribute("idClienteVar",
                fachadaPluFicha.getIdCliente());
        pageContext.setAttribute("fechaEntregaVar",
                fachadaPluFicha.getFechaEntregaCorta());
        pageContext.setAttribute("cantidadDf0Var",
                fachadaPluFicha.getCantidadDf0());
        pageContext.setAttribute("cantidadTerminadaDf0Var",
                fachadaPluFicha.getCantidadTerminadaDf0());
        pageContext.setAttribute("porcentajeTerminadoDf0Var",
                fachadaPluFicha.getPorcentajeTerminadoDf0());
        pageContext.setAttribute("nombreTerceroVar",
                fachadaPluFicha.getNombreTercero());
        pageContext.setAttribute("referenciaClienteVar",
                fachadaPluFicha.getReferenciaCliente());
        pageContext.setAttribute("nombreOperacionVar",
                fachadaPluFicha.getNombreOperacion());
        pageContext.setAttribute("nombreItemVar",
                fachadaPluFicha.getNombreItem());
        pageContext.setAttribute("idOrdenProgramaVar",
                                      fachadaPluFicha.getIdOrdenProgramaStr());
        pageContext.setAttribute("referenciaVar",
                                      fachadaPluFicha.getReferencia());
        pageContext.setAttribute("cantidadPendienteVar",
                             fachadaPluFicha.getCantidadPendienteDf0());
        pageContext.setAttribute("pesoPendienteVar",
                                 fachadaPluFicha.getPesoPendienteDf0());
        pageContext.setAttribute("cantidadPendienteStrVar",
                             fachadaPluFicha.getCantidadPendienteStr());
        pageContext.setAttribute("pesoPendienteStrVar",
                                 fachadaPluFicha.getPesoPendienteStr());
    }

    public int doStartTag() throws JspTagException {

        // Parametros llegados de JSP
        colaboraOrdenTrabajo = new ColaboraOrdenTrabajo();

        //
        colaboraOrdenTrabajo.setIdLocal(getIdLocalTag());
        colaboraOrdenTrabajo.setIdTipoOrden(getIdTipoOrdenTag());
        colaboraOrdenTrabajo.setIdOperacion(getIdOperacionTag());
        colaboraOrdenTrabajo.setFechaPrograma(getFechaProgramaTag());
        colaboraOrdenTrabajo.setVrEscala(getVrEscalaTag());

        //
        Vector vectorBean =
                colaboraOrdenTrabajo.listaOTPrograma();

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