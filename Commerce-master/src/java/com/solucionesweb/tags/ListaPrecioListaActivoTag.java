package com.solucionesweb.tags;

import java.util.*;
import java.io.IOException;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.tagext.TagSupport;

// Importa el bean de ColaboraInventarioBean
import com.solucionesweb.losbeans.colaboraciones.ColaboraPlu;

// Importa el bean de FachadaInventarioBean
import com.solucionesweb.losbeans.fachada.FachadaPluBean;

// Importa el bean de FachadaInventarioBean
import com.solucionesweb.losbeans.fachada.FachadaPluPrecio;

public class ListaPrecioListaActivoTag
        extends TagSupport implements IterationTag {

    // Variable que recibe del JSP
    private String idLineaTag;
    private String nombrePluTag;
    private String idListaPrecioTag;
    private String idLocalTag;

    // Metodos Tag
    public void setIdLineaTag(String idLineaTag) {
        this.idLineaTag = idLineaTag;
    }

    public String getIdLineaTag() {
        return idLineaTag;
    }

    public void setNombrePluTag(String nombrePluTag) {
        this.nombrePluTag = nombrePluTag;
    }

    public String getNombrePluTag() {
        return nombrePluTag;
    }

    public void setIdListaPrecioTag(String idListaPrecioTag) {
        this.idListaPrecioTag = idListaPrecioTag;
    }

    public String getIdListaPrecioTag() {
        return idListaPrecioTag;
    }

    public int getIdListaPrecio() {
        return new Integer(getIdListaPrecioTag()).intValue();
    }

    public void setIdLocalTag(String idLocalTag) {
        this.idLocalTag = idLocalTag;
    }

    public String getIdLocalTag() {
        return idLocalTag;
    }
    //
    ColaboraPlu colaboraPlu;
    //
    FachadaPluBean fachadaPluBean;
    //
    FachadaPluPrecio fachadaPluPrecio = new FachadaPluPrecio();
    // Iterador de objetos
    Iterator iteratorBean;

    private void inicializarVariablesDeRetorno() {

        //
        fachadaPluBean = (FachadaPluBean) iteratorBean.next();

        //
        int xIdTipoHijo = 2;

        //
        String existenciaPadre = fachadaPluBean.getExistenciaDf2();

        //
        if (fachadaPluBean.getIdTipo() == xIdTipoHijo) {


            //
            existenciaPadre = "";

        }





        //
        fachadaPluBean.setIdListaPrecio(getIdListaPrecio());

        // Variable que retornan al JSP
        pageContext.setAttribute("nombrePluVar",
                fachadaPluBean.getNombreCategoria() + " "
                + fachadaPluBean.getNombrePlu());
        pageContext.setAttribute("nombreMarcaVar",
                fachadaPluBean.getNombreMarca());
        pageContext.setAttribute("strIdReferenciaVar",
                fachadaPluBean.getIdPluStr());
        pageContext.setAttribute("existenciaVar", existenciaPadre);
        pageContext.setAttribute("strUnidadMedidaVar", "UN");
        pageContext.setAttribute("vrGeneralVar", fachadaPluBean.getVrGeneralDf0());
        pageContext.setAttribute("vrMayoristaVar",
                fachadaPluBean.getVrMayoristaDf0());
        pageContext.setAttribute("vrSucursalVar",
                fachadaPluBean.getVrSucursalDf0());
        pageContext.setAttribute("vrVentaUnitarioVar",
                fachadaPluBean.getVrVentaUnitarioListaStr());
        pageContext.setAttribute("vrVentaUnitarioDf0Var",
                fachadaPluBean.getVrVentaUnitarioListaDf0());
        pageContext.setAttribute("vrCostoDf2Var",
                fachadaPluBean.getVrCostoDf2());
        pageContext.setAttribute("vrCostoVar",
                fachadaPluBean.getVrCostoStr());
        pageContext.setAttribute("vrCostoINDDf2Var",
                fachadaPluBean.getVrCostoINDDf2());
        pageContext.setAttribute("vrCostoINDVar",
                fachadaPluBean.getVrCostoINDStr());
        pageContext.setAttribute("factorDespachoVar",
                fachadaPluBean.getFactorDespachoStr());
    }

    public int doStartTag() throws JspTagException {



        //
        ArrayList vectorBean = new ArrayList();

        // Parametros llegados de JSP
        colaboraPlu = new ColaboraPlu();

        //
        int xIdBodega = 888;

        //
        colaboraPlu.setIdLocal(getIdLocalTag());
        colaboraPlu.setIdBodega(xIdBodega);

        //
        double xIdPlu = 0.0;

        try {

            //
            xIdPlu = Double.parseDouble(getIdLineaTag());

            if (xIdPlu > 99999) {

                //
                colaboraPlu.setEan(getIdLineaTag());

                //
                vectorBean = colaboraPlu.listaEanTras();

            } else {

                //
                colaboraPlu.setIdPlu((int) xIdPlu);

                //
                vectorBean = colaboraPlu.listaPluActivo();

            }

        } catch (NumberFormatException nfe) {

            //
            if ((getIdLineaTag().length() > 0)
                    && (getNombrePluTag().length() > 0)) {

                //
                vectorBean =
                        colaboraPlu.listaNombrePluCategoriaActivo(
                        "'%" + getIdLineaTag().trim() + "%'",
                        "'%" + getNombrePluTag().trim() + "%'");
                // colaboraPlu.listaCategoriaDescripcion(
                //                           "'%" + getIdLineaTag().trim()   + "%'");

            } else if (getIdLineaTag().length() > 0) {

                //
                vectorBean =
                        colaboraPlu.listaNombreCategoriaActivo(
                        "'%" + getIdLineaTag().trim() + "%'");
                //colaboraPlu.listaCategoriaDescripcion(
                //                          "'%" + getIdLineaTag().trim()   + "%'");


            } else if (getNombrePluTag().length() > 0) {

                //
                vectorBean =
                        colaboraPlu.listaNombreCategoriaActivo("'%"
                        + getNombrePluTag().trim() + "%'");
            }
        }

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