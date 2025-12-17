package com.solucionesweb.tags;

import java.util.*;
import java.io.IOException;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.tagext.TagSupport;

// Importa el Bean de Asignaturas
import com.solucionesweb.losbeans.colaboraciones.ColaboraContable;

// Importa el Bean de Fachada
import com.solucionesweb.losbeans.fachada.FachadaTipoOrdenSubcuenta;

public class ListaContableCuentaTag extends TagSupport
        implements IterationTag {

    // Variable que recibe del JSP
    private String idLocalTag;
    private String fechaInicialTag;
    private String fechaFinalTag;

    // Metodos Tag
    public void setIdLocalTag(String idLocalTag) {
        this.idLocalTag = idLocalTag;
    }

    public String getIdLocalTag() {
        return idLocalTag;
    }

    public void setFechaInicialTag(String fechaInicialTag) {
        this.fechaInicialTag = fechaInicialTag;
    }

    public String getFechaInicialTag() {
        return fechaInicialTag;
    }

	public void setFechaFinalTag(String fechaFinalTag) {
        this.fechaFinalTag = fechaFinalTag;
    }

    public String getFechaFinalTag() {
        return fechaFinalTag;
    }

    // Variable para usar el bean de fachada
    FachadaTipoOrdenSubcuenta fachadaTipoOrdenSubcuenta;
    // Variable para usar el bean de EstadoPcBean
    ColaboraContable colaboraContable;
    // Iterador de objetos
    Iterator iteratorBean;

    private void inicializarVariablesDeRetorno() {

        //
        fachadaTipoOrdenSubcuenta = (FachadaTipoOrdenSubcuenta) iteratorBean.next();

        // Variable que retornan al JSP
        pageContext.setAttribute("idSubcuentaVar",
                fachadaTipoOrdenSubcuenta.getIdSubcuenta());
        pageContext.setAttribute("vrMovimientoVar",
                fachadaTipoOrdenSubcuenta.getVrMovimientoSf0());
        pageContext.setAttribute("nombreSubcuentaVar",
                fachadaTipoOrdenSubcuenta.getNombreSubcuenta());
        pageContext.setAttribute("idAsientoVar",
                fachadaTipoOrdenSubcuenta.getIdAsientoStr());

    }

    public int doStartTag() throws JspTagException {

        // Parametros llegados de JSP
        colaboraContable = new ColaboraContable();

        //
        colaboraContable.setIdLocal(getIdLocalTag());
        colaboraContable.setFechaInicial(getFechaInicialTag());
        colaboraContable.setFechaFinal(getFechaFinalTag());

        //
        Vector vectorBean = colaboraContable.listaCuenta();

        //
        iteratorBean = vectorBean.iterator();

        if (!iteratorBean.hasNext()) {
            try {
                pageContext.getOut().write("La consulta no devolvio ningun dato <br/>");
                pageContext.getOut().write("<br/>");
                pageContext.getOut().write("Por favor comunique al administrador del sistema que no existen datos <br/>");
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
