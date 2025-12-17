package com.solucionesweb.tags;

import java.util.*;
import java.io.IOException;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.tagext.TagSupport;

// Importa el Bean de Asignaturas
import com.solucionesweb.losbeans.colaboraciones.ColaboraPlu;

// Importa el Bean de Fachada
import com.solucionesweb.losbeans.fachada.FachadaPluBean;

public class ListaPluCategoriaOpcionTag extends TagSupport
        implements IterationTag {

    // Variables de Retorno a jsp
    private String idLineaTag;
    private String idCategoriaTag;
    private String idPluTag;

    // Metodos recibidos JSP
    public void setIdLineaTag(String idLineaTag) {
        this.idLineaTag = idLineaTag;
    }

    public String getIdLineaTag() {
        return idLineaTag;
    }

    public int getIdLinea() {
        return new Integer(getIdLineaTag()).intValue();
    }

    public void setIdCategoriaTag(String idCategoriaTag) {
        this.idCategoriaTag = idCategoriaTag;
    }

    public String getIdCategoriaTag() {
        return idCategoriaTag;
    }

    public int getIdCategoria() {
        return new Integer(getIdCategoriaTag()).intValue();
    }

    public void setIdPluTag(String idPluTag) {
        this.idPluTag = idPluTag;
    }

    public String getIdPluTag() {
        return idPluTag;
    }

    // Variable para usar el bean de fachada
    FachadaPluBean fachadaPluBean;
    // Variable para usar el bean de area
    ColaboraPlu colaboraPlu;
    // Iterador de objetos
    Iterator iteratorLineaBean;

    private void inicializarVariablesDeRetorno() {

        //
        fachadaPluBean = (FachadaPluBean) iteratorLineaBean.next();

        //
        pageContext.setAttribute("idPluVar", fachadaPluBean.getIdPluStr());
        pageContext.setAttribute("nombrePluVar", fachadaPluBean.getNombrePlu());
    }

    public int doStartTag() throws JspTagException {

        // Efectua la busqueda de los grados que estan matriculados
        colaboraPlu = new ColaboraPlu();

        //
        colaboraPlu.setIdLinea(getIdLineaTag());
        colaboraPlu.setIdCategoria(getIdCategoriaTag());
        colaboraPlu.setIdPlu(getIdPluTag());

        //
        Vector listaLineaVector;

        //
        listaLineaVector = colaboraPlu.listaPluCategoriaOpcion();

        //
        iteratorLineaBean = listaLineaVector.iterator();

        if (!iteratorLineaBean.hasNext()) {
            try {
                pageContext.getOut().write("La consulta no devolvio ningun dato <br/>");
                pageContext.getOut().write("<br/>");
                pageContext.getOut().write("Por favor comunique al administrador del sistema que no existen grados <br/>");
            } catch (IOException ex) {
                throw new JspTagException("Error: la respuesta no se pudo escribir para los grados");
            } finally {
                return SKIP_BODY;
            }
        }

        // Asigna los valores a las variables que se muestran en jsp
        inicializarVariablesDeRetorno();

        return EVAL_BODY_INCLUDE;
    }

    public int doAfterBody() throws JspTagException {

        if (iteratorLineaBean.hasNext()) {
            inicializarVariablesDeRetorno();
            return EVAL_BODY_AGAIN;
        }

        return SKIP_BODY;

    }

    public int doEndTag() throws JspTagException {
        return EVAL_PAGE;
    }
}
