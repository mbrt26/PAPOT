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

public class ListaOperacionActualSiguienteTag extends TagSupport
        implements IterationTag {

    // Variable que recibe del JSP
    private String idOperacionTag;
    private String idFichaTag;

    // Metodos Tag
    public void setIdOperacionTag( String idOperacionTag )
    {
        this.idOperacionTag = idOperacionTag ;
    }

    public String getIdOperacionTag()
    {
        return idOperacionTag;
    }

    public void setIdFichaTag( String idFichaTag )
    {
        this.idFichaTag = idFichaTag ;
    }

    public String getIdFichaTag()
    {
        return idFichaTag;
    }

    public int getIdFicha()
    {
        return  new Integer(getIdFichaTag()).intValue();
    }

    public int getIdOperacion()
    {
        return new Integer(getIdOperacionTag()).intValue();
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
        pageContext.setAttribute("idOperacionSiguienteVar",
                fachadaJobOperacion.getIdOperacionStr());
        pageContext.setAttribute("nombreOperacionSiguienteVar",
                fachadaJobOperacion.getNombreOperacionSiguiente());
        pageContext.setAttribute("idOperacionActualVar",
                fachadaJobOperacion.getIdOperacionStr());
        pageContext.setAttribute("nombreOperacionActualVar",
                fachadaJobOperacion.getNombreOperacion());
    }

    public int doStartTag() throws JspTagException {

        // Parametros llegados de JSP
        jobOperacionBean = new JobOperacionBean();

        //
        int xIdEscala = 610;

        //
        jobOperacionBean.setIdOperacion(getIdOperacionTag());

        //
        Vector vectorBean = jobOperacionBean.listaOperacionActualSiguiente(
                getIdFicha(),
                xIdEscala);

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
