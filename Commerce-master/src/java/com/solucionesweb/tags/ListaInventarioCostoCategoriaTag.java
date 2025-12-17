package com.solucionesweb.tags;

import java.util.*;
import java.io.IOException;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.tagext.TagSupport;

// Importa el Bean de Asignaturas
import com.solucionesweb.losbeans.colaboraciones.ColaboraInventarioBean;

// Importa el Bean de Fachada
import com.solucionesweb.losbeans.fachada.FachadaColaboraInventario;

public class ListaInventarioCostoCategoriaTag extends TagSupport
        implements IterationTag {

    // Variables de Retorno a jsp
    private String idLineaTag;
    private String idCategoriaTag;
    private String idLocalTag;
    private String idBodegaTag;

    //
    int xIdTipo = 1;

    // Metodos recibidos JSP
    public void setIdLineaTag(String idLineaTag) {
        this.idLineaTag = idLineaTag;
    }

    public String getIdLineaTag() {
        return idLineaTag;
    }

    public int getIdLinea() {
        return new Integer(getIdLineaTag()).intValue();
    }

    public void setIdCategoriaTag(String idCategoriaTag) {
        this.idCategoriaTag = idCategoriaTag;
    }

    public String getIdCategoriaTag() {
        return idCategoriaTag;
    }

    public int getIdCategoria() {
        return new Integer(getIdCategoriaTag()).intValue();
    }

    public void setIdLocalTag(String idLocalTag) {
        this.idLocalTag = idLocalTag;
    }

    public String getIdLocalTag() {
        return idLocalTag;
    }

    public int getIdLocal() {
        return new Integer(getIdLocalTag());
    }

    public void setIdBodegaTag(String idBodegaTag) {
        this.idBodegaTag = idBodegaTag;
    }

    public String getIdBodegaTag() {
        return idBodegaTag;
    }
    // Variable para usar el bean de fachada
    FachadaColaboraInventario fachadaColaboraInventario;
    // Variable para usar el bean de area
    ColaboraInventarioBean colaboraInventarioBean;
    // Iterador de objetos
    Iterator iteratorLineaBean;

    private void inicializarVariablesDeRetorno() {

        //
        fachadaColaboraInventario = (FachadaColaboraInventario) iteratorLineaBean.next();

        //
        pageContext.setAttribute("idLocalVar",
                fachadaColaboraInventario.getIdLocalStr());
        pageContext.setAttribute("idPluVar",
                fachadaColaboraInventario.getIdPluStr());
        pageContext.setAttribute("existenciaSf2Var",
                fachadaColaboraInventario.getExistenciaSf2());
        pageContext.setAttribute("nombreCategoriaVar",
                fachadaColaboraInventario.getNombreCategoria());
        pageContext.setAttribute("nombreMarcaVar",
                fachadaColaboraInventario.getNombreMarca());
        pageContext.setAttribute("nombrePluVar",
                fachadaColaboraInventario.getNombrePlu());
        pageContext.setAttribute("vrCostoInventarioVar",
                fachadaColaboraInventario.getVrCostoInventarioDf0());
        pageContext.setAttribute("vrTotalImpoconsumoVar",
                fachadaColaboraInventario.getVrTotalImpoconsumoDf0());
        pageContext.setAttribute("vrTotalCostoINDVar",
                fachadaColaboraInventario.getVrTotalCostoINDDf0());
        pageContext.setAttribute("vetVrCostoIvaVar",
                fachadaColaboraInventario.getVetVrCostoIvaDf0());
    }

    public int doStartTag() throws JspTagException {

        // Efectua la busqueda de los grados que estan matriculados
        colaboraInventarioBean = new ColaboraInventarioBean();

        //
        colaboraInventarioBean.setIdLocal(getIdLocalTag());
        colaboraInventarioBean.setIdBodega(getIdBodegaTag());
        colaboraInventarioBean.setIdTipo(xIdTipo);

        //
        Vector listaLineaVector;

        //
        if ((getIdLinea() == 0)
                && (getIdCategoria() == 0)) {

            //
            listaLineaVector = colaboraInventarioBean.listaAllCategoria(
                                                               getIdLocal());

        } else {

            //
            colaboraInventarioBean.setIdLinea(getIdLinea());
            colaboraInventarioBean.setIdCategoria(getIdCategoria());

            //
            listaLineaVector = colaboraInventarioBean.listaUnaCategoria(
                                                                  getIdLocal());
        }


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
