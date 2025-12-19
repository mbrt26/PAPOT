package com.solucionesweb.tags;

import java.util.*;
import java.io.IOException;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.tagext.TagSupport;

// Importa el bean de ColaboraResurtido
import com.solucionesweb.losbeans.negocio.TipoCausaNotaBean;

// Importa el Bean de Fachada
import com.solucionesweb.losbeans.fachada.FachadaTipoCausaNota;

public class ListaCausaNotaOpcionTag extends TagSupport
        implements IterationTag {

    // Variable para usar el bean de fachada
    FachadaTipoCausaNota fachadaTipoCausaNota;
    //
    TipoCausaNotaBean tipoCausaNotaBean;
    // Iterador de objetos
    Iterator iteratorBean;

    private void inicializarVariablesDeRetorno() {

        fachadaTipoCausaNota = (FachadaTipoCausaNota) iteratorBean.next();

        //
        pageContext.setAttribute("idCausaVar",
                fachadaTipoCausaNota.getIdCausaStr());
        pageContext.setAttribute("nombreCausaVar",
                fachadaTipoCausaNota.getNombreCausa());

    }

    public int doStartTag() throws JspTagException {

        //
        tipoCausaNotaBean = new TipoCausaNotaBean();

        //
        Vector vectorBean = tipoCausaNotaBean.listaCausaNota();

        //
        iteratorBean = vectorBean.iterator();

        if (!iteratorBean.hasNext()) {
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
