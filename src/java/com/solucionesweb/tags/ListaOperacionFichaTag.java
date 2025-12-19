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

public class ListaOperacionFichaTag
        extends TagSupport implements IterationTag {

    // Variable que recibe del JSP
    private String idClienteTag;
    private String idFichaTag;
    private String idOperacionTag;

    //
    String xTextoIndex = "textoIndex_" ;

    // Metodos Tag
    public String getIdClienteTag() {
        return idClienteTag;
    }

    public void setIdClienteTag(String idClienteTag) {
        this.idClienteTag = idClienteTag;
    }

    public String getidFichaTag() {
        return idFichaTag;
    }

    public void setidFichaTag(String idFichaTag) {
        this.idFichaTag = idFichaTag;
    }

    public void setIdOperacionTag(String idOperacionTag) {
        this.idOperacionTag = idOperacionTag;
    }

    public String getIdOperacionTag() {
        return idOperacionTag;
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
        String xTextoEscala = fachada.getVrEscalaStr();
        String xVrEscala = fachada.getVrEscalaStr();

        //
        if  ((fachada.getIdTipoEscala())== 1) {

            //
            xVrEscala  =  fachada.getVrEscalaStr();
            xTextoEscala = "";

        }

        //
        if  ((fachada.getIdTipoEscala())== 3) {

            //
            if (fachada.getTextoEscala().trim().length()>0) {

               //
               xVrEscala  = "";
               xTextoEscala  =  fachada.getTextoEscala().trim();

            }
        }

        //---
        if  ((fachada.getIdTipoEscala()== 4) ||
             (fachada.getIdTipoEscala()== 5) ||
             (fachada.getIdTipoEscala()== 6) ||
             (fachada.getIdTipoEscala()== 7)) {

            //
            if (fachada.getTextoEscala().trim().length()>0) {

               //
               xTextoEscala  =  fachada.getTextoEscala().trim();
               xVrEscala  =  fachada.getVrEscalaStr();

            }
        }

        // Variable que retornan al JSP
        pageContext.setAttribute("idClienteVar", fachada.getIdCliente());
        pageContext.setAttribute("idReferenciaClienteVar", fachada.getReferenciaCliente());
        pageContext.setAttribute("idOperacionVar", fachada.getIdOperacionStr());
        pageContext.setAttribute("idPluVar", fachada.getIdPluStr());
        pageContext.setAttribute("nombreReferenciaVar", fachada.getNombreReferencia());
        pageContext.setAttribute("idEscalaVar", fachada.getIdEscalaStr());
        pageContext.setAttribute("itemVar", fachada.getItemStr());
        pageContext.setAttribute("vrEscalaVar", xVrEscala);
        pageContext.setAttribute("textoEscalaVar", xTextoEscala);
        pageContext.setAttribute("nombreEscalaVar", fachada.getNombreEscala());
        pageContext.setAttribute("idTipoEscalaVar", fachada.getIdTipoEscalaStr());
        pageContext.setAttribute("idEscalaIndexVar", fachada.getIdEscalaStr() +
                                                                          "~" +
                                                         fachada.getItemStr()
                                                                         + "~" +
                                                 fachada.getIdTipoEscalaStr() +
                                                                   "~vrEscala");
        pageContext.setAttribute("idTextoEscalaIndexVar",fachada.getIdEscalaStr()
                                                                         + "~" +
                                                          fachada.getItemStr()
                                                                         + "~" +
                                                 fachada.getIdTipoEscalaStr() +
                                                                "~textoEscala");
    }

    public int doStartTag() throws JspTagException {

        //
        colabora = new ColaboraOrdenTrabajo();

        //
        colabora.setIdOperacion(getIdOperacionTag());
        colabora.setIdCliente(getIdClienteTag());
        colabora.setIdFicha(getidFichaTag());

        //
        Vector vectorBean = colabora.listaFichaOperacionCliente();

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
