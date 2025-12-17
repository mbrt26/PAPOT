package com.solucionesweb.tags;

import java.util.*;
import java.io.IOException;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.tagext.TagSupport;

// Importa el Bean de Asignaturas
import com.solucionesweb.losbeans.colaboraciones.ColaboraPlu;

// Importa el Bean de Fachada
import com.solucionesweb.losbeans.fachada.FachadaPluBean;

public class ListaInventarioPluTag extends TagSupport
        implements IterationTag {

    // Variables de Retorno a jsp
    private String idLocalTag;
    private String idPluTag;
    private String idBodegaTag;

    // Metodos recibidos JSP
    public void setIdLocalTag(String idLocalTag) {
        this.idLocalTag = idLocalTag;
    }

    public String getIdLocalTag() {
        return idLocalTag;
    }

    public void setIdPluTag(String idPluTag) {
        this.idPluTag = idPluTag;
    }

    public String getIdPluTag() {
        return idPluTag;
    }

    public void setIdBodegaTag(String idBodegaTag) {
        this.idBodegaTag = idBodegaTag;
    }

    public String getIdBodegaTag() {
        return idBodegaTag;
    }

    // Variable para usar el bean de fachada
    FachadaPluBean fachadaPluBean;

    // Variable para usar el bean de area
    ColaboraPlu colaboraPlu;
    
    // Iterador de objetos
    Iterator iteratorLineaBean;

    private void inicializarVariablesDeRetorno() {

        fachadaPluBean = (FachadaPluBean) iteratorLineaBean.next();

        //
        pageContext.setAttribute("idPluVar", fachadaPluBean.getIdPluStr());
        pageContext.setAttribute("nombrePluVar", fachadaPluBean.getNombrePlu());
        pageContext.setAttribute("vrGeneralVar", fachadaPluBean.getVrGeneralStr());
        pageContext.setAttribute("vrMayoristaVar",
                fachadaPluBean.getVrMayoristaStr());
        pageContext.setAttribute("porcentajeIvaVar",
                fachadaPluBean.getPorcentajeIvaStr());
        pageContext.setAttribute("idTipoVar",
                fachadaPluBean.getIdTipoStr());
        pageContext.setAttribute("idLineaVar", fachadaPluBean.getIdLineaStr());
        pageContext.setAttribute("idUCompraVar", fachadaPluBean.getIdUCompraStr());
        pageContext.setAttribute("idUVentaVar", fachadaPluBean.getIdUVentaStr());
        pageContext.setAttribute("vrCostoVar", fachadaPluBean.getVrCostoStr());
        pageContext.setAttribute("vrCostoINDVar",
                fachadaPluBean.getVrCostoINDStr());
        pageContext.setAttribute("idCategoriaVar",
                fachadaPluBean.getIdCategoriaStr());
        pageContext.setAttribute("idMarcaVar", fachadaPluBean.getIdMarcaStr());
        pageContext.setAttribute("vrSucursalVar",
                fachadaPluBean.getVrSucursalStr());
        pageContext.setAttribute("factorVentaVar",
                fachadaPluBean.getFactorVentaStr());
        pageContext.setAttribute("factorDespachoVar",
                fachadaPluBean.getFactorDespachoStr());
        pageContext.setAttribute("estadoVar", fachadaPluBean.getEstadoStr());
        pageContext.setAttribute("idSeqVar", fachadaPluBean.getIdSeqStr());
        pageContext.setAttribute("referenciaVar", fachadaPluBean.getReferencia());
        pageContext.setAttribute("nombreCategoriaVar",
                fachadaPluBean.getNombreCategoria());
        pageContext.setAttribute("nombreMarcaVar",
                fachadaPluBean.getNombreMarca());
        pageContext.setAttribute("vrGeneralDf0Var",
                fachadaPluBean.getVrGeneralDf0());
        pageContext.setAttribute("vrCostoDf2Var", fachadaPluBean.getVrCostoDf2());
        pageContext.setAttribute("existenciaDf2Var",
                fachadaPluBean.getExistenciaDf2());
        pageContext.setAttribute("vrCostoINDDf2Var",
                fachadaPluBean.getVrCostoINDDf2());

    }

    public int doStartTag() throws JspTagException {

        // Efectua la busqueda de los grados que estan matriculados
        colaboraPlu = new ColaboraPlu();

        //
        colaboraPlu.setIdLocal(getIdLocalTag());
        colaboraPlu.setIdPlu(getIdPluTag());
        colaboraPlu.setIdBodega(getIdBodegaTag());

        //
        Vector listaLineaVector = colaboraPlu.listaPluLocalBodega();

        //
        iteratorLineaBean = listaLineaVector.iterator();

        if (!iteratorLineaBean.hasNext()) {
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

        if (iteratorLineaBean.hasNext()) {
            inicializarVariablesDeRetorno();
            return EVAL_BODY_AGAIN;
        }

        return SKIP_BODY;

    }

    public int doEndTag() throws JspTagException {
        return EVAL_PAGE;
    }
}
