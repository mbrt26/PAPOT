package com.solucionesweb.tags;

import java.util.*;
import java.io.IOException;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.tagext.TagSupport;

// Importa el bean de ColaboraResurtido
import com.solucionesweb.losbeans.colaboraciones.ColaboraJobEscala;

// Importa el bean de FachadaDctoOrdenDetalleBean
import com.solucionesweb.losbeans.fachada.FachadaJobEscala;

public class ListaEscalaTag
        extends TagSupport implements IterationTag {

    // Variable que recibe del JSP
    private String idEscalaTag;
    private String itemTag;

    // Metodos Tag
    public void setIdEscalaTag(String idEscalaTag) {
        this.idEscalaTag = idEscalaTag;
    }

    public String getIdEscalaTag() {
        return idEscalaTag;
    }

    public void setItemTag(String itemTag) {
        this.itemTag = itemTag;
    }

    public String getItemTag() {
        return itemTag;
    }

    public int getItem() {
        return (int)(new Double(getItemTag()).doubleValue());
    }

    //
    ColaboraJobEscala colabora;
    //
    FachadaJobEscala fachada;
    // Iterador de objetos
    Iterator iteratorBean;

    private void inicializarVariablesDeRetorno() {

        //
        fachada = (FachadaJobEscala) iteratorBean.next();

        // Variable que retornan al JSP
        pageContext.setAttribute("idEscalaVar", fachada.getIdEscalaStr());
        pageContext.setAttribute("nombreEscalaVar", fachada.getNombreEscala());
        pageContext.setAttribute("itemVar", fachada.getItemStr());
        pageContext.setAttribute("nombreItemVar", fachada.getNombreItem());

    }

    public int doStartTag() throws JspTagException {

        //
        colabora = new ColaboraJobEscala();

        //
        colabora.setIdEscala(getIdEscalaTag());
        colabora.setItem((int)getItem());

        //
        Vector vectorBean = colabora.listaEscalaOpcion();

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
