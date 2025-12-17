package com.solucionesweb.tags;

import java.util.*;
import java.io.IOException;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.tagext.TagSupport;

// Importa el Bean de Asignaturas
import com.solucionesweb.losbeans.negocio.JobCostoBean;

// Importa el Bean de Fachada
import com.solucionesweb.losbeans.fachada.FachadaJobCosto;

public class ListaCostoTag extends TagSupport
        implements IterationTag {

    // Variable para usar el bean de fachada
    FachadaJobCosto fachadaJobCosto;
    // Variable para usar el bean de EstadoPcBean
    JobCostoBean jobCostoBean;
    // Iterador de objetos
    Iterator iteratorBean;

    private void inicializarVariablesDeRetorno() {


        //
        fachadaJobCosto = (FachadaJobCosto) iteratorBean.next();

        // Variable que retornan al JSP
        pageContext.setAttribute("idCostoVar", fachadaJobCosto.getIdCostoStr());
        pageContext.setAttribute("nombreCostoVar",
                fachadaJobCosto.getNombreCosto());

    }

    public int doStartTag() throws JspTagException {

        // Parametros llegados de JSP
        jobCostoBean = new JobCostoBean();

        //
        Vector vectorBean = jobCostoBean.listaAll();

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
