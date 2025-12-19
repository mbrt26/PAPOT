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

public class ListaComprobanteTag extends TagSupport
        implements IterationTag {

    // Variable que recibe del Tag
    private String idLocalTag;
    private String fechaDctoTag;
    private String idAlcanceTag;

    //
    public String getIdLocalTag() {
        return idLocalTag;
    }

    public void setIdLocalTag(String idLocalTag) {
        this.idLocalTag = idLocalTag;
    }

    public String getFechaDctoTag() {
        return fechaDctoTag;
    }

    public void setFechaDctoTag(String fechaDctoTag) {
        this.fechaDctoTag = fechaDctoTag;
    }

    public String getIdAlcanceTag() {
        return idAlcanceTag;
    }

    public void setIdAlcanceTag(String idAlcanceTag) {
        this.idAlcanceTag = idAlcanceTag;
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
        pageContext.setAttribute("idLocalVar",
               getIdLocalTag());
        pageContext.setAttribute("idTipoOrdenVar",
                fachadaTipoOrdenSubcuenta.getIdTipoOrdenStr());
        pageContext.setAttribute("idOrdenVar",
                fachadaTipoOrdenSubcuenta.getIdOrdenStr());
        pageContext.setAttribute("nombreTerceroVar",
                fachadaTipoOrdenSubcuenta.getNombreTercero());
        pageContext.setAttribute("idDctoVar",
                fachadaTipoOrdenSubcuenta.getIdDctoStr());
        pageContext.setAttribute("idDctoNitCCVar",
                fachadaTipoOrdenSubcuenta.getIdDctoNitCC());
        pageContext.setAttribute("fechaDctoVar",
                fachadaTipoOrdenSubcuenta.getFechaDctoCorta());
        pageContext.setAttribute("observacionVar",
                fachadaTipoOrdenSubcuenta.getObservacionLogitud50());
        pageContext.setAttribute("nombreTipoOrdenVar",
                fachadaTipoOrdenSubcuenta.getNombreTipoOrden());
        pageContext.setAttribute("vrUnitarioVar",
                fachadaTipoOrdenSubcuenta.getVrUnitarioDf0());

        
    }

    public int doStartTag() throws JspTagException {

        // Parametros llegados de JSP
        colaboraOrdenSubcuenta = new ColaboraOrdenSubcuenta();

        colaboraOrdenSubcuenta.setIdLocal(idLocalTag);

        //
        colaboraOrdenSubcuenta.setIdLocal(getIdLocalTag());
        colaboraOrdenSubcuenta.setFechaDcto(getFechaDctoTag());
        colaboraOrdenSubcuenta.setIdAlcance(getIdAlcanceTag());

        //
        Vector vectorBean = colaboraOrdenSubcuenta.listaAlcance();

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
