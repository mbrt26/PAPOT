package com.solucionesweb.tags;

import java.util.*;
import java.io.IOException;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.tagext.TagSupport;

// Importa el bean de ColaboraResurtido
import com.solucionesweb.losbeans.colaboraciones.ColaboraOrdenTrabajo;

// Importa el bean de FachadaDctoOrdenDetalleBean
import com.solucionesweb.losbeans.fachada.FachadaPluFicha;

public class ListaFichaOperacionTag
        extends TagSupport implements IterationTag {

    // Variable que recibe del JSP
    private String idFichaTag;

    //
    String xTextoIndex = "textoIndex_" ;

    // Metodos Tag
    public void setIdFichaTag(String idFichaTag) {
        this.idFichaTag = idFichaTag;
    }

    public String getIdFichaTag() {
        return idFichaTag;
    }

    //
    ColaboraOrdenTrabajo colabora;
    //
    FachadaPluFicha fachada;
    // Iterador de objetos
    Iterator iteratorBean;

    private void inicializarVariablesDeRetorno() {

        //
        fachada = (FachadaPluFicha) iteratorBean.next();

        //
        String xVrEscalaTexto = fachada.getVrEscalaStr();

        //
        if  ((fachada.getIdTipoEscala())== 1 && (fachada.getVrEscala()==0)) {

            //
            if (fachada.getTextoEscala().trim().length()>0) {

               //
               xVrEscalaTexto  =  fachada.getTextoEscala().trim();

            }
        }

        //
        if  ((fachada.getIdTipoEscala())== 3 && (fachada.getVrEscala()==0)) {

            //
            if (fachada.getTextoEscala().trim().length()>0) {

               //
               xVrEscalaTexto  =  fachada.getTextoEscala().trim();

            }
        }

        // Variable que retornan al JSP
        pageContext.setAttribute("idClienteVar", fachada.getIdCliente());
        pageContext.setAttribute("referenciaClienteVar",
                                                fachada.getReferenciaCliente());
        pageContext.setAttribute("idOperacionVar", fachada.getIdOperacionStr());
        pageContext.setAttribute("idPluVar", fachada.getIdPluStr());
        pageContext.setAttribute("nombreReferenciaVar",
                                                 fachada.getNombreReferencia());
        pageContext.setAttribute("idEscalaVar", fachada.getIdEscalaStr());
        pageContext.setAttribute("itemVar", fachada.getItemStr());
        pageContext.setAttribute("vrEscalaVar", xVrEscalaTexto);
        pageContext.setAttribute("textoEscalaVar", fachada.getTextoEscala());
        pageContext.setAttribute("estadoVar", fachada.getEstadoStr());
        pageContext.setAttribute("idFichaVar", fachada.getIdFichaStr());
        pageContext.setAttribute("referenciaVar", fachada.getReferencia());        
        pageContext.setAttribute("nombreOperacionVar",
                                                  fachada.getNombreOperacion());

    }

    public int doStartTag() throws JspTagException {

        //
        colabora = new ColaboraOrdenTrabajo();

        //
        colabora.setIdFicha(getIdFichaTag());

        //
        Vector vectorBean = colabora.listaFichaOperacion();

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
