package com.solucionesweb.tags;

import java.util.*;
import java.io.IOException;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.tagext.TagSupport;

// Importa el bean de ColaboraResurtido
import com.solucionesweb.losbeans.negocio.JobOperacionBean;

// Importa el bean de FachadaDctoOrdenDetalleBean
import com.solucionesweb.losbeans.fachada.FachadaJobOperacion;

public class ListaOperacionSiguienteTag
        extends TagSupport implements IterationTag {

    // Variable que recibe del JSP
    private String idFichaTag;

    // Metodos Tag
    public String getIdFichaTag() {
        return idFichaTag;
    }

    public int getIdFicha() {
        return new Integer(getIdFichaTag()).intValue();
    }


    public void setIdFichaTag(String idFichaTag) {
        this.idFichaTag = idFichaTag;
    }

    //
    JobOperacionBean jobOperacionBean;

    //
    FachadaJobOperacion fachada;

    // Iterador de objetos
    Iterator iteratorBean;

    private void inicializarVariablesDeRetorno() {

        //
        fachada = (FachadaJobOperacion) iteratorBean.next();

        // Variable que retornan al JSP
        pageContext.setAttribute("idOperacionVar", fachada.getIdOperacionStr());
        pageContext.setAttribute("nombreOperacionVar",
                                                  fachada.getNombreOperacion());
        pageContext.setAttribute("idOperacionSiguienteVar",
                                          fachada.getIdOperacionSiguienteStr());

    }

    public int doStartTag() throws JspTagException {

        //
        jobOperacionBean = new JobOperacionBean();

        //
        Vector vectorBean = jobOperacionBean.listaOperacionSiguiente(
                                                                  getIdFicha());

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
