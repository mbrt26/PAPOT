package com.solucionesweb.tags;

import java.util.*;
import java.io.IOException;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.tagext.TagSupport;

// Importa el Bean de Asignaturas
import com.solucionesweb.losbeans.colaboraciones.ColaboraOrdenSubcuenta;

// Importa el Bean de Fachada
import com.solucionesweb.losbeans.fachada.FachadaTipoOrdenSubcuenta;

public class ListaOrdenSubcuentaTag extends TagSupport
        implements IterationTag {

    // Variable que recibe del Tag
    private String idTipoOrdenTag;

    //
    public String getIdTipoOrdenTag() {
        return idTipoOrdenTag;
    }

    public int getIdTipoOrden() {
        return new Integer(getIdTipoOrdenTag()).intValue();
    }

    public void setIdTipoOrdenTag(String idTipoOrdenTag) {
        this.idTipoOrdenTag = idTipoOrdenTag;
    }
    // Variable para usar el bean de fachada
    FachadaTipoOrdenSubcuenta fachadaTipoOrdenSubcuenta;
    // Variable para usar el bean de EstadoPcBean
    ColaboraOrdenSubcuenta colaboraOrdenSubcuenta;
    // Iterador de objetos
    Iterator iteratorBean;

    private void inicializarVariablesDeRetorno() {

        //
        fachadaTipoOrdenSubcuenta = (FachadaTipoOrdenSubcuenta) iteratorBean.next();

        // Variable que retornan al JSP
        pageContext.setAttribute("idTipoOrdenVar",
                fachadaTipoOrdenSubcuenta.getIdTipoOrdenStr());
        pageContext.setAttribute("idSubcuentaVar",
                fachadaTipoOrdenSubcuenta.getIdSubcuenta());
        pageContext.setAttribute("idAsientoVar",
                fachadaTipoOrdenSubcuenta.getIdAsientoStr());
        pageContext.setAttribute("nombreTipoOrdenVar",
                fachadaTipoOrdenSubcuenta.getNombreTipoOrden());
        pageContext.setAttribute("nombreSubcuentaVar",
                fachadaTipoOrdenSubcuenta.getNombreSubcuenta());


    }

    public int doStartTag() throws JspTagException {

        // Parametros llegados de JSP
        colaboraOrdenSubcuenta = new ColaboraOrdenSubcuenta();

        //
        colaboraOrdenSubcuenta.setIdTipoOrden(getIdTipoOrden());

        //
        Vector vectorBean = colaboraOrdenSubcuenta.listaCuenta();

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
