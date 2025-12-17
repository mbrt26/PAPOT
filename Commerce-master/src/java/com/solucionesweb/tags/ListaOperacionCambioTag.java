package com.solucionesweb.tags;

import java.util.*;
import java.io.IOException;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.tagext.TagSupport;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaJobOperacion;

// Importa el bean de fachada
import com.solucionesweb.losbeans.negocio.JobOperacionBean;

public class ListaOperacionCambioTag extends TagSupport
        implements IterationTag {

    // Variable que recibe del JSP
    private String idOperacionTag;


    public void setIdOperacionTag(String idOperacionTag) {
        this.idOperacionTag = idOperacionTag;
    }

    public String getIdOperacionTag() {
        return idOperacionTag;
    }

    // Variable para usar el bean de fachada
    FachadaJobOperacion fachadaJobOperacion;

    // Variable para usar el bean de EstadoPcBean
    JobOperacionBean jobOperacionBean;

    // Iterador de objetos
    Iterator iteratorBean;

    private void inicializarVariablesDeRetorno() {

        //
        fachadaJobOperacion =
                (FachadaJobOperacion) iteratorBean.next();

        // Variable que retornan al JSP
        pageContext.setAttribute("idOperacionVar",
                fachadaJobOperacion.getIdOperacionStr());
        pageContext.setAttribute("nombreOperacionVar",
                fachadaJobOperacion.getNombreOperacion());

    }

    public int doStartTag() throws JspTagException {

        // Parametros llegados de JSP
        jobOperacionBean = new JobOperacionBean();

        //
        jobOperacionBean.setIdOperacion(getIdOperacionTag());

        //
        Vector vectorBean = jobOperacionBean.listaOperacionCambio();

        //
        iteratorBean = vectorBean.iterator();

        if (!iteratorBean.hasNext()) {
            try {
                pageContext.getOut().write("No existen datos<br/>");
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
