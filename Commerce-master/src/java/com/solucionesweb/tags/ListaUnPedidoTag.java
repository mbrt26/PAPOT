package com.solucionesweb.tags;

import java.util.*;
import java.io.IOException;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.tagext.TagSupport;

// Importa el bean de ColaboraInventarioBean
import com.solucionesweb.losbeans.colaboraciones.ColaboraOrdenTrabajo;

// Importa el bean de FachadaInventarioBean
import com.solucionesweb.losbeans.fachada.FachadaPluFicha;

public class ListaUnPedidoTag
        extends TagSupport implements IterationTag {

    // Variable que recibe del JSP
    private String idLocalTag;
    private String idClienteTag;
    private String idOperacionTag;
    private String idFichaTag;

    public String getidFichaTag() {
        return idFichaTag;
    }

    public void setidFichaTag(String idFichaTag) {
        this.idFichaTag = idFichaTag;
    }

    // Metodos Tag
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

    public void setIdOperacionTag(String idOperacionTag) {
        this.idOperacionTag = idOperacionTag;
    }

    public String getIdOperacionTag() {
        return idOperacionTag;
    }

    //
    ColaboraOrdenTrabajo colaboraOrdenTrabajo;
    //
    FachadaPluFicha fachadaPluFicha;
    // Iterador de objetos
    Iterator iteratorBean;

    private void inicializarVariablesDeRetorno() {

        //
        fachadaPluFicha = (FachadaPluFicha) iteratorBean.next();

        // Variable que retornan al JSP
        pageContext.setAttribute("idClienteVar", fachadaPluFicha.getIdCliente());
        pageContext.setAttribute("referenciaClienteVar",
                fachadaPluFicha.getReferenciaCliente());
        pageContext.setAttribute("idOperacionVar",
                fachadaPluFicha.getIdOperacionStr());
        pageContext.setAttribute("idPluVar", fachadaPluFicha.getIdPluStr());
        pageContext.setAttribute("nombreReferenciaVar",
                fachadaPluFicha.getNombreReferencia());
        pageContext.setAttribute("nombreEscalaVar",
                fachadaPluFicha.getNombreEscala());
        pageContext.setAttribute("idEscalaVar",
                fachadaPluFicha.getIdEscalaStr());
        pageContext.setAttribute("itemVar",
                fachadaPluFicha.getItemStr());
        pageContext.setAttribute("vrEscalaVar",
                fachadaPluFicha.getVrEscalaFormato());
        pageContext.setAttribute("textoEscalaVar",
                fachadaPluFicha.getTextoEscala());
        pageContext.setAttribute("estadoVar",
                fachadaPluFicha.getEstadoStr());

    }

    public int doStartTag() throws JspTagException {

        // Parametros llegados de JSP
        colaboraOrdenTrabajo = new ColaboraOrdenTrabajo();

        //
        colaboraOrdenTrabajo.setIdLocal(getIdLocalTag());
        colaboraOrdenTrabajo.setIdCliente(getIdClienteTag());
        colaboraOrdenTrabajo.setIdOperacion(getIdOperacionTag());
        colaboraOrdenTrabajo.setIdFicha(getidFichaTag());

        //
        Vector vectorBean = colaboraOrdenTrabajo.listaUnOrdenDetalle();

        //
        iteratorBean = vectorBean.iterator();

        //
        if (!iteratorBean.hasNext()) {
            try {
                pageContext.getOut().write("La consulta no devolvio ningun dato <br/>");
                pageContext.getOut().write("<br/>");
                pageContext.getOut().write("Por favor comunique al administrador<br/>");
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

            //
            inicializarVariablesDeRetorno();
            return EVAL_BODY_AGAIN;
        }

        return SKIP_BODY;

    }

    public int doEndTag() throws JspTagException {
        return EVAL_PAGE;
    }
}
