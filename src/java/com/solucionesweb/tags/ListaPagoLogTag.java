package com.solucionesweb.tags;

import java.util.*;
import java.io.IOException;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.tagext.TagSupport;

// Importa el Bean de Asignaturas
import com.solucionesweb.losbeans.negocio.PagoMedioBean;

// Importa el Bean de Fachada
import com.solucionesweb.losbeans.fachada.FachadaPagoMedioBean;

public class ListaPagoLogTag extends TagSupport
        implements IterationTag {

    // Variable que recibe del JSP
    private String idLocalTag;
    private String idTipoOrdenTag;
    private String idLogTag;

    // Metodos Tag
    public void setIdLocalTag(String idLocalTag) {
        this.idLocalTag = idLocalTag;
    }

    public String getIdLocalTag() {
        return idLocalTag;
    }
    
    public int getIdTipoOrden() {
                     
        return new Integer(getIdTipoOrdenTag()).intValue();
    }    

    public void setIdTipoOrdenTag(String idTipoOrdenTag) {
        this.idTipoOrdenTag = idTipoOrdenTag;
    }

    public String getIdTipoOrdenTag() {
        return idTipoOrdenTag;
    }

    public void setIdLogTag(String idLogTag) {
        this.idLogTag = idLogTag;
    }

    public String getIdLogTag() {
        return idLogTag;
    }
    // Variable para usar el bean de fachada
    FachadaPagoMedioBean fachadaPagoMedioBean;

    // Variable para usar el bean de EstadoPcBean
    PagoMedioBean pagoMedioBean;

    // Iterador de objetos
    Iterator iteratorBean;

    private void inicializarVariablesDeRetorno() {


        //
        fachadaPagoMedioBean = (FachadaPagoMedioBean) iteratorBean.next();


        //
        pageContext.setAttribute("idLocalVar",
                fachadaPagoMedioBean.getIdLocalStr());
        pageContext.setAttribute("idTipoOrdenVar",
                fachadaPagoMedioBean.getIdTipoOrdenStr());
        pageContext.setAttribute("idReciboVar",
                fachadaPagoMedioBean.getIdReciboStr());
        pageContext.setAttribute("indicadorVar",
                fachadaPagoMedioBean.getIndicadorStr());
        pageContext.setAttribute("idDctoMedioVar",
                fachadaPagoMedioBean.getIdDctoMedio());
        pageContext.setAttribute("idMedioVar",
                fachadaPagoMedioBean.getIdMedioStr());
        pageContext.setAttribute("vrMedioDf0Var",
                fachadaPagoMedioBean.getVrMedioDf0());
        pageContext.setAttribute("fechaCobroCortaVar",
                fachadaPagoMedioBean.getFechaCobroCorta());
        pageContext.setAttribute("idBancoVar",
                fachadaPagoMedioBean.getIdBancoStr());
        pageContext.setAttribute("estadoVar",
                fachadaPagoMedioBean.getEstadoStr());
        pageContext.setAttribute("nombreMedioVar",
                fachadaPagoMedioBean.getNombreMedio());
        pageContext.setAttribute("nombreBancoVar",
                fachadaPagoMedioBean.getNombreBanco());

    }

    public int doStartTag() throws JspTagException {

        // Parametros llegados de JSP
        pagoMedioBean = new PagoMedioBean();

        //
        pagoMedioBean.setIdLocal(getIdLocalTag());
        pagoMedioBean.setIdTipoOrden(getIdTipoOrden());
        pagoMedioBean.setIdLog(getIdLogTag());

        //
        Vector vectorBean = pagoMedioBean.listaPagoLog();
        

        //
        iteratorBean = vectorBean.iterator();

        if (!iteratorBean.hasNext()) {
            try {
                pageContext.getOut().write("<br>No existen pagos");
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