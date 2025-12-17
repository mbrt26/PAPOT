package com.solucionesweb.tags;

import java.util.*;
import java.io.IOException;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.tagext.TagSupport;

// Importa el Bean de ColaboraDctoOrdenDetalleBean
import com.solucionesweb.losbeans.colaboraciones.ColaboraOrdenDetalleBean;

// Importa el Bean de Fachada FachadaDctoOrdenDetalleBean
import com.solucionesweb.losbeans.fachada.FachadaDctoOrdenDetalleBean;

public class ListaOrdenTag extends TagSupport
        implements IterationTag {

    // Variable que recibe del JSP
    private String idLocalTag;
    private String idTipoOrdenTag;
    private String idLogTag;
    private String idBodegaTag;

    // Metodos Tag
    public void setIdLocalTag(String idLocalTag) {
        this.idLocalTag = idLocalTag;
    }

    public String getIdLocalTag() {
        return idLocalTag;
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

    public void setIdBodegaTag(String idBodegaTag) {
        this.idBodegaTag = idBodegaTag;
    }

    public String getIdBodegaTag() {
        return idBodegaTag;
    }
    // Variable para usar el bean de fachada
    FachadaDctoOrdenDetalleBean fachadaDctoOrdenDetalleBean;
    // Variable para usar el bean de colaboraDctoOrdenDetalleBean
    ColaboraOrdenDetalleBean colaboraOrdenDetalleBean;
    // Iterador de objetos
    Iterator iteratorBean;

    private void inicializarVariablesDeRetorno() {

        //
        fachadaDctoOrdenDetalleBean =
                (FachadaDctoOrdenDetalleBean) iteratorBean.next();

        //
        String xLetraDetalle = "letraDetalle";
        String xLetraDetalleResaltada = "letraResaltada";
        String xLetraDetalleInventario = xLetraDetalle;

        //
        int xIdTipoHijo = 2;

        //
        String existenciaPadre =
                fachadaDctoOrdenDetalleBean.getExistenciaDf2();

        //
        if (fachadaDctoOrdenDetalleBean.getExistencia()
                < fachadaDctoOrdenDetalleBean.getCantidad()) {

            //
            xLetraDetalleInventario = xLetraDetalleResaltada;

        }

        //
        if (fachadaDctoOrdenDetalleBean.getIdTipo() == xIdTipoHijo) {

            //
            xLetraDetalleInventario = xLetraDetalle;

            //
            existenciaPadre = "";

        }

        // Variable que retornan al JSP
        pageContext.setAttribute("idLocalVar",
                fachadaDctoOrdenDetalleBean.getIdLocalStr());
        pageContext.setAttribute("idTipoOrdenVar",
                fachadaDctoOrdenDetalleBean.getIdTipoOrdenStr());
        pageContext.setAttribute("idOrdenVar",
                fachadaDctoOrdenDetalleBean.getIdOrdenStr());
        pageContext.setAttribute("idLocalOrigenVar",
                fachadaDctoOrdenDetalleBean.getIdLocalOrigenStr());
        pageContext.setAttribute("idTipoOrdenOrigenVar",
                fachadaDctoOrdenDetalleBean.getIdTipoOrdenOrigenStr());
        pageContext.setAttribute("idOrdenOrigenVar",
                fachadaDctoOrdenDetalleBean.getIdOrdenOrigenStr());
        pageContext.setAttribute("itemOrdenVar",
                fachadaDctoOrdenDetalleBean.getItemOrdenStr());
        pageContext.setAttribute("idPluVar",
                fachadaDctoOrdenDetalleBean.getIdPluStr());
        pageContext.setAttribute("strIdReferenciaVar",
                fachadaDctoOrdenDetalleBean.getStrIdReferencia());
        pageContext.setAttribute("nombrePluVar",
                fachadaDctoOrdenDetalleBean.getNombrePlu());
        pageContext.setAttribute("cantidadVar",
                fachadaDctoOrdenDetalleBean.getCantidadDf2());
        pageContext.setAttribute("vrVentaUnitarioVar",
                fachadaDctoOrdenDetalleBean.getVrVentaUnitarioDf0());
        pageContext.setAttribute("vrVentaSinIvaVar",
                fachadaDctoOrdenDetalleBean.getVrVentaSinIvaDf0());
        pageContext.setAttribute("vrVentaConIvaVar",
                fachadaDctoOrdenDetalleBean.getVrVentaConIvaDf0());
        pageContext.setAttribute("marcaArteClienteVar",
                fachadaDctoOrdenDetalleBean.getMarcaArteCliente());
        pageContext.setAttribute("nombreArchivoVar",
                fachadaDctoOrdenDetalleBean.getNombreArchivo());
        pageContext.setAttribute("referenciaClienteVar",
                fachadaDctoOrdenDetalleBean.getReferenciaCliente());
        pageContext.setAttribute("comentarioVar",
                fachadaDctoOrdenDetalleBean.getComentario());
        pageContext.setAttribute("itemVar",
                fachadaDctoOrdenDetalleBean.getItemStr());
        pageContext.setAttribute("itemOrdenVar",
                fachadaDctoOrdenDetalleBean.getItemOrdenStr());
        pageContext.setAttribute("idEstadoTxVar",
                fachadaDctoOrdenDetalleBean.getIdEstadoTxStr());
        pageContext.setAttribute("nombreUnidadMedidaVar",
                fachadaDctoOrdenDetalleBean.getNombreUnidadMedida());
        pageContext.setAttribute("nombreMarcaVar",
                fachadaDctoOrdenDetalleBean.getNombreMarca());
        pageContext.setAttribute("nombreCategoriaVar",
                fachadaDctoOrdenDetalleBean.getNombreCategoria());
        pageContext.setAttribute("vrCostoVar",
                fachadaDctoOrdenDetalleBean.getVrCostoDf2());
        pageContext.setAttribute("vrCostoActualVar",
                fachadaDctoOrdenDetalleBean.getVrCostoActualDf2());
        pageContext.setAttribute("incrementoCostoVar",
                fachadaDctoOrdenDetalleBean.getIncrementoCostoDf2());
        pageContext.setAttribute("vrCostoConIvaVar",
                fachadaDctoOrdenDetalleBean.getVrCostoConIvaDf2());
        pageContext.setAttribute("existenciaVar", existenciaPadre);
        pageContext.setAttribute("porcentajeDsctoVar",
                fachadaDctoOrdenDetalleBean.getPorcentajeDsctoStr());
        pageContext.setAttribute("vrVentaSinDsctoDf0Var",
                fachadaDctoOrdenDetalleBean.getVrVentaSinDsctoDf0());
        pageContext.setAttribute("letraDetalleInventarioVar",
                xLetraDetalleInventario);
        pageContext.setAttribute("factorDespachoVar",
                fachadaDctoOrdenDetalleBean.getFactorDespachoStr());
        pageContext.setAttribute("fechaEntregaVar",
                fachadaDctoOrdenDetalleBean.getFechaEntregaCorta());
        pageContext.setAttribute("unidadVentaVar",
                fachadaDctoOrdenDetalleBean.getUnidadVentaDf0());
        pageContext.setAttribute("referenciaVar",
                fachadaDctoOrdenDetalleBean.getReferencia());
        pageContext.setAttribute("vrVentaUnitarioDf2Var",
                fachadaDctoOrdenDetalleBean.getVrVentaUnitarioDf2());
        pageContext.setAttribute("cantidadStrVar",
                fachadaDctoOrdenDetalleBean.getCantidadStr());
        pageContext.setAttribute("cantidadPendienteVar",
                fachadaDctoOrdenDetalleBean.getCantidadPendienteStr());
        pageContext.setAttribute("pesoPendienteVar",
                fachadaDctoOrdenDetalleBean.getPesoPendienteStr());
    }

    public int doStartTag() throws JspTagException {

        // Parametros llegados de JSP
        colaboraOrdenDetalleBean = new ColaboraOrdenDetalleBean();

        //
        colaboraOrdenDetalleBean.setIdLocal(getIdLocalTag());
        colaboraOrdenDetalleBean.setIdTipoOrden(getIdTipoOrdenTag());
        colaboraOrdenDetalleBean.setIdLog(getIdLogTag());
        colaboraOrdenDetalleBean.setIdBodega(getIdBodegaTag());

        //
/*        Vector vectorBean = colaboraOrdenDetalleBean.listaOrden();*/

        Vector vectorBean = colaboraOrdenDetalleBean.listaTraslado();



        //
        iteratorBean = vectorBean.iterator();

        if (!iteratorBean.hasNext()) {
            try {
                pageContext.getOut().write("No existen datos <br/>");
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
