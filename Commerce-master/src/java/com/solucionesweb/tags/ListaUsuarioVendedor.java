package com.solucionesweb.tags;

import com.solucionesweb.losbeans.fachada.FachadaUsuarioBean;
import com.solucionesweb.losbeans.negocio.UsuarioBean;
import java.io.IOException;
import java.util.Iterator;
import java.util.Vector;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.IterationTag;
import javax.servlet.jsp.tagext.TagSupport;

public class ListaUsuarioVendedor
        extends TagSupport
        implements IterationTag {

    private String idUsuarioTag;
    private String idNivelTag;
    private String idLocalUsuarioTag;
    private String estadoTag;
    FachadaUsuarioBean fachadaUsuarioBean;
    UsuarioBean usuarioBean;
    Iterator iteratorBean;

    public void setIdUsuarioTag(String idUsuarioTag) {
        this.idUsuarioTag = idUsuarioTag;
    }

    public String getIdUsuarioTag() {
        return idUsuarioTag;
    }

    public void setIdLocalUsuarioTag(String idLocalUsuarioTag) {
        this.idLocalUsuarioTag = idLocalUsuarioTag;
    }

    public String getIdLocalUsuarioTag() {
        return idLocalUsuarioTag;
    }

    public void setIdNivelTag(String idNivelTag) {
        this.idNivelTag = idNivelTag;
    }

    public String getIdNivelTag() {
        return idNivelTag;
    }

    public void setEstadoTag(String estadoTag) {
        this.estadoTag = estadoTag;
    }

    public String getEstadoTag() {
        return estadoTag;
    }

    private void inicializarVariablesDeRetorno() {
        fachadaUsuarioBean = ((FachadaUsuarioBean) iteratorBean.next());

        pageContext.setAttribute("idUsuarioVar", fachadaUsuarioBean.getIdUsuarioStr());

        pageContext.setAttribute("nombreUsuarioVar", fachadaUsuarioBean.getNombreUsuario());
    }

    public int doStartTag()
            throws JspTagException {
        usuarioBean = new UsuarioBean();

        usuarioBean.setIdUsuario(getIdUsuarioTag());
        usuarioBean.setEstado(getEstadoTag());
        usuarioBean.setIdLocalUsuario(getIdLocalUsuarioTag());

        Vector listaVector = usuarioBean.listaNivelOpcionNN(idNivelTag);

        //
        iteratorBean = listaVector.iterator();

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
