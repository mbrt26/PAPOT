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

public class ListaEscalaBaseTag
        extends TagSupport implements IterationTag {

    // Variable que recibe del JSP
    private String idOperacionTag;

    //
    String xIdEscalaPrefijo = "idEscala_" ;

    // Metodos Tag
    public void setIdOperacionTag(String idOperacionTag) {
        this.idOperacionTag = idOperacionTag;
    }

    public String getIdOperacionTag() {
        return idOperacionTag;
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
        pageContext.setAttribute("idTipoEscalaVar", fachada.getIdTipoEscalaStr());
        pageContext.setAttribute("idEscalaIndexVar", xIdEscalaPrefijo +
                                                    fachada.getIdEscalaStr());
        pageContext.setAttribute("idEscalaIndexVar", fachada.getIdEscalaStr());
        pageContext.setAttribute("idTextoEscalaIndexVar",fachada.getIdEscalaStr()
                                                                         + "~" +
                                                          fachada.getItemStr() +
                                                                "~textoEscala");  
    }

    public int doStartTag() throws JspTagException {

        //
        colabora = new ColaboraJobEscala();

        colabora.setIdOperacion(getIdOperacionTag());

        //
        Vector vectorBean = colabora.listaEscalaProceso();

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
