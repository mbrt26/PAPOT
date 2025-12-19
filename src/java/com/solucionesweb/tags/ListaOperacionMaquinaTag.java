package com.solucionesweb.tags;

import com.solucionesweb.losbeans.colaboraciones.ColaboraJobEscala;
import com.solucionesweb.losbeans.fachada.FachadaJobEscala;
import java.io.IOException;
import java.util.Iterator;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.IterationTag;
import javax.servlet.jsp.tagext.TagSupport;

public class ListaOperacionMaquinaTag extends TagSupport
        implements IterationTag {

    private String idLocalTag;
    FachadaJobEscala fachadaJobEscala;
    ColaboraJobEscala colaboraJobEscala;
    Iterator iteratorBean;

    public void setIdLocalTag(String idLocalTag) {
        this.idLocalTag = idLocalTag;
    }

    public String getIdLocalTag() {
        return this.idLocalTag;
    }

    private void inicializarVariablesDeRetorno() {
        this.fachadaJobEscala = ((FachadaJobEscala) this.iteratorBean.next());

        this.pageContext.setAttribute("idOperacionVar", this.fachadaJobEscala.getIdOperacionStr());

        this.pageContext.setAttribute("nombreEscalaVar", this.fachadaJobEscala.getNombreEscala());

        this.pageContext.setAttribute("idEscalaVar", this.fachadaJobEscala.getIdEscalaStr());

        this.pageContext.setAttribute("itemVar", this.fachadaJobEscala.getItemStr());

        this.pageContext.setAttribute("nombreMaquinaVar", this.fachadaJobEscala.getNombreItem());
    }

    @Override
    public int doStartTag()
            throws JspTagException {
        this.colaboraJobEscala = new ColaboraJobEscala();

        Vector vectorBean = this.colaboraJobEscala.listaEscalaItemMaquina();

        this.iteratorBean = vectorBean.iterator();
        if (!this.iteratorBean.hasNext()) {
            try {
                this.pageContext.getOut().write("<br>No existen datos");
            } catch (IOException ex) {
                throw new JspTagException("Error: la respuesta no se pudo escribir para los datos");
            } finally {
            }
        }
        inicializarVariablesDeRetorno();
  return EVAL_BODY_INCLUDE;
    }

    @Override
    public int doAfterBody()
            throws JspTagException {
        if (this.iteratorBean.hasNext()) {
            inicializarVariablesDeRetorno();
            return EVAL_BODY_AGAIN;
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag()
            throws JspTagException {
        return EVAL_PAGE;
    }

}
