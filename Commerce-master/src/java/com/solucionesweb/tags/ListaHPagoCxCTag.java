package com.solucionesweb.tags;

import java.util.*;
import java.io.IOException;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.tagext.TagSupport;

// Importa el Bean de Asignaturas
import com.solucionesweb.losbeans.colaboraciones.ColaboraPago;

// Importa el Bean de Fachada
import com.solucionesweb.losbeans.fachada.FachadaPagoBean;

public class ListaHPagoCxCTag extends TagSupport
        implements IterationTag {

    // Variable que recibe del Tag
    private String idLocalTag;
    private String idTipoOrdenTag;
    private String fechaInicialTag;
    private String fechaFinalTag;
    private String idClienteTag;

    // Metodos Tag
    public void setIdLocalTag(String idLocalTag) {
        this.idLocalTag = idLocalTag;
    }

    public String getIdLocalTag() {
        return idLocalTag;
    }

    public int getIdLocal() {
        return new Integer(getIdLocalTag()).intValue();
    }

    public void setIdTipoOrdenTag(String idTipoOrdenTag) {
        this.idTipoOrdenTag = idTipoOrdenTag;
    }

    public String getIdTipoOrdenTag() {
        return idTipoOrdenTag;
    }

    public int getIdTipoOrden() {
        return new Integer(getIdTipoOrdenTag()).intValue();
    }

    public void setFechaInicialTag(String fechaInicialTag) {
        this.fechaInicialTag = fechaInicialTag;
    }

    public String getFechaInicialTag() {
        return fechaInicialTag;
    }

    public void setFechaFinalTag(String fechaFinalTag) {
        this.fechaFinalTag = fechaFinalTag;
    }

    public String getFechaFinalTag() {
        return fechaFinalTag;
    }

    public void setIdClienteTag(String idClienteTag) {
        this.idClienteTag = idClienteTag;
    }

    public String getIdClienteTag() {
        return idClienteTag;
    }

    // Variable para usar el bean de fachada
    FachadaPagoBean fachadaPagoBean;

    // Variable para usar el bean de EstadoPcBean
    ColaboraPago colaboraPago;

    // Iterador de objetos
    Iterator iteratorBean;

    private void inicializarVariablesDeRetorno() {

        //
        fachadaPagoBean = (FachadaPagoBean) iteratorBean.next();

        // Variable que retornan al JSP
        pageContext.setAttribute("idLocalVar",
                fachadaPagoBean.getIdLocalStr());
        pageContext.setAttribute("idTipoOrdenVar",
                fachadaPagoBean.getIdTipoOrdenStr());
        pageContext.setAttribute("idReciboVar",
                fachadaPagoBean.getIdReciboStr());
        pageContext.setAttribute("indicadorVar",
                fachadaPagoBean.getIndicadorStr());
        pageContext.setAttribute("fechaPagoVar",
                fachadaPagoBean.getFechaPagoCorta());
        pageContext.setAttribute("vrPagoVar",
                fachadaPagoBean.getVrPagoDf0());
        pageContext.setAttribute("nitCCVar",
                fachadaPagoBean.getNitCC());
        pageContext.setAttribute("estadoVar",
                fachadaPagoBean.getEstadoStr());
        pageContext.setAttribute("idUsuarioVar",
                fachadaPagoBean.getIdUsuarioStr());
        pageContext.setAttribute("vrRteFuenteVar",
                fachadaPagoBean.getVrRteFuenteDf0());
        pageContext.setAttribute("vrDescuentoVar",
                fachadaPagoBean.getVrDescuentoDf0());
        pageContext.setAttribute("idPeriodoVar",
                fachadaPagoBean.getIdPeriodoStr());
        pageContext.setAttribute("vrRteIvaVar",
                fachadaPagoBean.getVrRteIvaDf0());
        pageContext.setAttribute("vrRteIcaVar",
                fachadaPagoBean.getVrRteIcaDf0());
        pageContext.setAttribute("idDctoVar",
                fachadaPagoBean.getIdDctoStr());
        pageContext.setAttribute("idDctoNitCCVar",
                fachadaPagoBean.getIdDctoNitCC());
        pageContext.setAttribute("idPlanillaVar",
                fachadaPagoBean.getIdPlanillaStr());
        pageContext.setAttribute("idDctoDian",
                fachadaPagoBean.getIdDctoDian());

    }

    public int doStartTag() throws JspTagException {

        // Parametros llegados de JSP
        colaboraPago = new ColaboraPago();

        //  listaFechasTipoOrdenCliente
        colaboraPago.setFechaInicial(getFechaInicialTag());
        colaboraPago.setFechaFinal(getFechaFinalTag());
        colaboraPago.setNitCC(getIdClienteTag());

        Vector vectorBean
                = colaboraPago.listaPagoTercero(getIdLocal(),
                        getIdTipoOrden());

        iteratorBean = vectorBean.iterator();

        if (!iteratorBean.hasNext()) {
            try {
                pageContext.getOut().write("&nbsp;");
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

    public void doCatch(Throwable t) throws Throwable {
        System.out.println("Error: " + t);
        pageContext.getOut().println("<font color=\"red\">"
                + "Invocando <b>doCatch</b> debido a ("
                + t + ")</font>");
    }

    public void doFinally() {
    }
}
