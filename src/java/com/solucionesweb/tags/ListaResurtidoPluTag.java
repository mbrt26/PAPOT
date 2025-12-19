package com.solucionesweb.tags;

import java.util.*;
import java.io.IOException;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.tagext.TagSupport;

// Importa el bean de ColaboraResurtido
import com.solucionesweb.losbeans.colaboraciones.ColaboraResurtido;

// Importa el bean de FachadaDctoOrdenDetalleBean
import com.solucionesweb.losbeans.fachada.FachadaDctoOrdenDetalleBean;

// Importa el bean de Day
import com.solucionesweb.losbeans.utilidades.Day;

public class ListaResurtidoPluTag
        extends TagSupport implements IterationTag {

    // Variable que recibe del JSP
    private String idLocalTag;
    private String diasHistoriaTag;
    private String diasInventarioTag;
    private String idTerceroTag;
    private String fechaCorteTag;
    private String idPluTag;
    private String existenciaActualTag;

    //
    String letraEstilo = "";

    // Metodos Tag
    public void setIdLocalTag(String idLocalTag) {
        this.idLocalTag = idLocalTag;
    }

    public String getIdLocalTag() {
        return idLocalTag;
    }

    public void setDiasHistoriaTag(String diasHistoriaTag) {
        this.diasHistoriaTag = diasHistoriaTag;
    }

    public String getDiasHistoriaTag() {
        return diasHistoriaTag;
    }

    public int getDiasHistoria() {
        return new Integer(getDiasHistoriaTag());
    }

    public void setDiasInventarioTag(String diasInventarioTag) {
        this.diasInventarioTag = diasInventarioTag;
    }

    public String getDiasInventarioTag() {
        return diasInventarioTag;
    }

    public int getDiasInventario() {
        return new Integer(getDiasInventarioTag());
    }

    public void setIdTerceroTag(String idTerceroTag) {
        this.idTerceroTag = idTerceroTag;
    }

    public String getIdTerceroTag() {
        return idTerceroTag;
    }

    public void setFechaCorteTag(String fechaCorteTag) {
        this.fechaCorteTag = fechaCorteTag;
    }

    public String getFechaCorteTag() {
        return fechaCorteTag;
    }

    public String getIdPluTag() {
        return idPluTag;
    }

    public void setIdPluTag(String idPluTag) {
        this.idPluTag = idPluTag;
    }

    public void setExistenciaActualTag(String existenciaActualTag) {
        this.existenciaActualTag = existenciaActualTag;
    }

    public String getExistenciaActualTag() {
        return existenciaActualTag;
    }

    public double getExistenciaActual() {
        return new Double(getExistenciaActualTag()).doubleValue();
    }

    public String getFechaInicial() {

        // Extrae el A?o, Mes y Dia
        String anoStr = getFechaCorteTag().substring(0, 4);
        String mesStr = getFechaCorteTag().substring(5, 7);
        String diaStr = getFechaCorteTag().substring(8, 10);

        int anoInt = Integer.parseInt(anoStr);
        int mesInt = Integer.parseInt(mesStr);
        int diaInt = Integer.parseInt(diaStr);

        //
        Day fechaCorte = new Day(anoInt, mesInt, diaInt);

        //
        fechaCorte.advance(getDiasHistoria() * (-1));

        return fechaCorte.getFechaFormateada().substring(0, 4) +
                fechaCorte.getFechaFormateada().substring(5, 7) +
                fechaCorte.getFechaFormateada().substring(8, 10);

    }

    public String getFechaFinal() {

        return getFechaCorteTag().substring(0, 4) +
                getFechaCorteTag().substring(5, 7) +
                getFechaCorteTag().substring(8, 10);
    }

    //
    ColaboraResurtido colaboraResurtido;

    //
    FachadaDctoOrdenDetalleBean fachada;

    

    // Iterador de objetos
    Iterator iteratorBean;

    private void inicializarVariablesDeRetorno() {

        //
        fachada = (FachadaDctoOrdenDetalleBean) iteratorBean.next();

        //
        fachada.setCantidadPvd(fachada.getAcumuladoVenta() /
                (double) getDiasHistoria());

        //
        fachada.setCantidadInventarioMaximo((fachada.getAcumuladoVenta() /
                (double) getDiasHistoria()) *
                getDiasInventario());

        //
        fachada.setCantidadPedidoSugerido(((fachada.getAcumuladoVenta() /
                (double) getDiasHistoria()) *
                getDiasInventario()) -
                getExistenciaActual() -
                fachada.getCantidadCompraPendiente() +
                fachada.getCantidadVentaPendiente() -
                fachada.getCantidadTrasladoPendiente() +
                fachada.getCantidadDespachoPendiente());

        //
        letraEstilo = "letraDetalle";

        //
        if (fachada.getCantidadPedidoSugerido()>=1) {

           //
           letraEstilo = "letraResaltadaGrande";

        }

        // Variable que retornan al JSP
        pageContext.setAttribute("idPluVar", fachada.getIdPluStr());
        pageContext.setAttribute("idLocalVar", fachada.getIdLocalStr());
        pageContext.setAttribute("acumuladoVentaVar",
                fachada.getAcumuladoVentaDf0());
        pageContext.setAttribute("cantidadVentaPendienteVar",
                fachada.getCantidadVentaPendienteDf0());
        pageContext.setAttribute("cantidadTrasladoPendienteVar",
                fachada.getCantidadTrasladoPendienteDf0());
        pageContext.setAttribute("cantidadDespachoPendienteVar",
                fachada.getCantidadDespachoPendienteDf0());
        pageContext.setAttribute("cantidadCompraPendienteVar",
                                       fachada.getCantidadCompraPendienteDf0());
        pageContext.setAttribute("cantidadPvdVar", fachada.getCantidadPvdDf2());
        pageContext.setAttribute("cantidadInventarioMaximoVar",
                                      fachada.getCantidadInventarioMaximoDf0());
        pageContext.setAttribute("cantidadPedidoSugeridoVar",
                                        fachada.getCantidadPedidoSugeridoDf0());
        pageContext.setAttribute("letraEstiloVar",letraEstilo);
        
    }

    public int doStartTag() throws JspTagException {

        // Parametros llegados de JSP
        colaboraResurtido = new ColaboraResurtido();

        //
        colaboraResurtido.setIdLocal(getIdLocalTag());
        colaboraResurtido.setIdPlu(getIdPluTag());

        //
        Vector vectorBean =
                colaboraResurtido.listaPVD(getFechaInicial(),
                getFechaFinal(),
                getDiasHistoria(),
                getDiasInventario());
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