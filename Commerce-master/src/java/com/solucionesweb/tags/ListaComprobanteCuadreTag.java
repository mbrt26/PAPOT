package com.solucionesweb.tags;

import java.util.*;
import java.io.IOException;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.tagext.TagSupport;

// Importa el Bean de Asignaturas
import com.solucionesweb.losbeans.colaboraciones.ColaboraDctoCuadre;

// Importa el Bean de Fachada
import com.solucionesweb.losbeans.fachada.FachadaDctoCuadre;

public class ListaComprobanteCuadreTag extends TagSupport
        implements IterationTag {

    // Variable que recibe del Tag
    private int indicadorInicialTag;
    private int indicadorFinalTag;
   
   public int getIndicadorInicialTag() {
        return indicadorInicialTag;
    }

    public void setIndicadorInicialTag(int indicadorInicialTag) {
        this.indicadorInicialTag = indicadorInicialTag;
    }

    public int getIndicadorFinalTag() {
        return indicadorFinalTag;
    }

    public void setIndicadorFinalTag(int indicadorFinalTag) {
        this.indicadorFinalTag = indicadorFinalTag;
    }




    // Variable para usar el bean de fachada
    FachadaDctoCuadre fachadaDctoCuadre;
    // Variable para usar el bean de EstadoPcBean
    ColaboraDctoCuadre colaboraDctoCuadre;
    // Iterador de objetos
    Iterator iteratorBean;

    private void inicializarVariablesDeRetorno() {

        //
        fachadaDctoCuadre = (FachadaDctoCuadre) iteratorBean.next();

        // Variable que retornan al JSP
        pageContext.setAttribute("fechaCuadreVar",
                fachadaDctoCuadre.getFechaCuadreCorta());
        pageContext.setAttribute("nombreEstadoVar",
                fachadaDctoCuadre.getNombreEstado());
        pageContext.setAttribute("saldoInicialVar",
                fachadaDctoCuadre.getSaldoInicialDf0());
        pageContext.setAttribute("vrIngresoVar",
                fachadaDctoCuadre.getVrIngresoDf0());
        pageContext.setAttribute("vrEgresoVar",
                fachadaDctoCuadre.getVrEgresoDf0());
        pageContext.setAttribute("saldoFinalVar",
                fachadaDctoCuadre.getSaldoFinalDf0());
        pageContext.setAttribute("fechaOperacionVar",
                fachadaDctoCuadre.getFechaOperacionCorta());
     
    }

    public int doStartTag() throws JspTagException {

        // Parametros llegados de JSP
        colaboraDctoCuadre = new ColaboraDctoCuadre();

        //
        colaboraDctoCuadre.setIndicadorInicial(getIndicadorInicialTag());
        colaboraDctoCuadre.setIndicadorFinal(getIndicadorFinalTag());
       
        //
        Vector vectorBean = colaboraDctoCuadre.listaCuadre();

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
