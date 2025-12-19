package com.solucionesweb.tags;

import java.util.*;
import java.io.IOException;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.tagext.TagSupport;

// Importa la clase que posibilita el formateo
import java.text.DecimalFormat;

// Importa el bean de ColaboraResurtido
import com.solucionesweb.losbeans.colaboraciones.ColaboraResurtido;

// Importa el bean de FachadaDctoOrdenDetalleBean
import com.solucionesweb.losbeans.fachada.FachadaDctoOrdenDetalleBean;

public class ListaResurtidoEmpresaTag
        extends TagSupport implements IterationTag {

    // Variable que recibe del JSP
    private String idLocalTag;
    private String idLogTag;
    private String diasInventarioTag;
    //
    String letraEstilo = "";
    //
    DecimalFormat df2 = new DecimalFormat("###,###,###.00");
    DecimalFormat df1 = new DecimalFormat("###,###,###.0");
    DecimalFormat df0 = new DecimalFormat("###,###,###");
    DecimalFormat sf0 = new DecimalFormat("#########");
    DecimalFormat sf1 = new DecimalFormat("#########.0");
    DecimalFormat sf2 = new DecimalFormat("#########.00");

    // Metodos Tag
    public void setIdLocalTag(String idLocalTag) {
        this.idLocalTag = idLocalTag;
    }

    public String getIdLocalTag() {
        return idLocalTag;
    }

    public void setIdLogTag(String idLogTag) {
        this.idLogTag = idLogTag;
    }

    public String getIdLogTag() {
        return idLogTag;
    }

    public void setDiasInventarioTag(String diasInventarioTag) {
        this.diasInventarioTag = diasInventarioTag;
    }

    public String getDiasInventarioTag() {
        return diasInventarioTag;
    }

    public int getDiasInventario() {
        return new Integer(getDiasInventarioTag()).intValue();
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
        fachada.setCantidadInventarioMaximo(fachada.getCantidadPvd()
                * (double) getDiasInventario());

        //
        double xCantidadPedidoUV = fachada.getCantidadInventarioMaximo()
                - fachada.getExistencia()
                - fachada.getCantidadCompraPendiente();

        //
        double xCantidadPedidoUD = (fachada.getCantidadInventarioMaximo()
                - fachada.getExistencia()
                - fachada.getCantidadCompraPendiente() )
                / fachada.getFactorDespacho();

        //
        if (xCantidadPedidoUD<1) {

           //
           xCantidadPedidoUV = 0.0;
           xCantidadPedidoUD = 0.0;

        }


        /*
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
        fachada.getCantidadDespachoPendiente());*/

        //
        letraEstilo = "letraDetalle";

        //
        if (xCantidadPedidoUV >= 1) {

            //
            letraEstilo = "letraResaltadaDetalle";

        }


        // Variable que retornan al JSP
        pageContext.setAttribute("idPluVar", fachada.getIdPluStr());
        pageContext.setAttribute("idLocalVar", fachada.getIdLocalStr());
        pageContext.setAttribute("nombrePluVar", fachada.getNombrePlu());
        pageContext.setAttribute("vrCostoVar", fachada.getVrCostoINDSf0());
        pageContext.setAttribute("vrCostoNegociadoVar",
                                              fachada.getVrCostoNegociadoSf0());
        pageContext.setAttribute("existenciaVar", fachada.getExistenciaDf0());
        pageContext.setAttribute("factorDespachoVar",
                fachada.getFactorDespachoStr());
        pageContext.setAttribute("cantidadPedidaVar",
                fachada.getCantidadPedidaStr());
        pageContext.setAttribute("existenciaBodegaVar",
                fachada.getExistenciaBodegaDf0());
        pageContext.setAttribute("cantidadPvdVar", fachada.getCantidadPvdDf2());
        pageContext.setAttribute("cantidadInventarioMaximoVar",
                fachada.getCantidadInventarioMaximoDf0());
        pageContext.setAttribute("cantidadPedidoUVVar",
                                                 sf0.format(xCantidadPedidoUV));
        pageContext.setAttribute("cantidadPedidoUDVar",
                                                 sf0.format(xCantidadPedidoUD));
        pageContext.setAttribute("letraEstiloVar", letraEstilo);
        pageContext.setAttribute("cantidadBonificadaVar",
                                            fachada.getCantidadBonificadaStr());
        pageContext.setAttribute("porcentajeIvaVar",
                                            fachada.getPorcentajeIvaStr());

    }

    public int doStartTag() throws JspTagException {

        // Parametros llegados de JSP
        colaboraResurtido = new ColaboraResurtido();

        //
        colaboraResurtido.setIdLocal(getIdLocalTag());
        colaboraResurtido.setIdLog(getIdLogTag());

        //
        Vector vectorBean = colaboraResurtido.listaResurtidoEmpresa();

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
