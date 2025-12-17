package com.solucionesweb.tags;

import java.util.*;
import java.io.IOException;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.tagext.TagSupport;

// Importa el Bean de Asignaturas
import com.solucionesweb.losbeans.negocio.DctoOrdenEstadoBean;

// Importa el Bean de Fachada
import com.solucionesweb.losbeans.fachada.FachadaDctoOrdenEstado;

public class ListaOrdenEstadoTag extends TagSupport
        implements IterationTag {

    // Variable para usar el bean de fachada
    FachadaDctoOrdenEstado fachadaDctoOrdenEstado;

    // Variable para usar el bean de EstadoPcBean
    DctoOrdenEstadoBean dctoOrdenEstadoBean;
    // Iterador de objetos
    Iterator iteratorBean;

    private void inicializarVariablesDeRetorno() {

        //
        fachadaDctoOrdenEstado =
                (FachadaDctoOrdenEstado) iteratorBean.next();


        // Variable que retornan al JSP
        pageContext.setAttribute("idEstadoVar",
                fachadaDctoOrdenEstado.getIdEstadoStr());
        pageContext.setAttribute("nombreEstadoVar",
                fachadaDctoOrdenEstado.getNombreEstado());
        pageContext.setAttribute("estadoVar",
                fachadaDctoOrdenEstado.getEstadoStr());
        pageContext.setAttribute("idSeqVar",
                fachadaDctoOrdenEstado.getIdSeqStr());

    }

    public int doStartTag() throws JspTagException {

        // Parametros llegados de JSP
        dctoOrdenEstadoBean = new DctoOrdenEstadoBean();

        //
        Vector vectorBean =
                dctoOrdenEstadoBean.listaAll();

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
