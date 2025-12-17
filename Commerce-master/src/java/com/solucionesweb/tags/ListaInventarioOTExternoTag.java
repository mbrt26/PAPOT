package com.solucionesweb.tags;

import java.util.*;
import java.io.IOException;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.tagext.TagSupport;

// Importa el Bean de Asignaturas
import com.solucionesweb.losbeans.colaboraciones.ColaboraOrdenTrabajo;

// Importa el Bean de Fachada
import com.solucionesweb.losbeans.fachada.FachadaPluFicha;

// Importa la clase que posibilita el formateo
import java.text.DecimalFormat;

public class ListaInventarioOTExternoTag extends TagSupport
        implements IterationTag {

    // Variable que recibe del JSP
    private String idLocalTag;
    private String idTipoOrdenTag;
    private String idOperacionTag;

    //
    double xExistenciaPesoTotal = 0.0;
    double xExistenciaCantidadTotal = 0.0;
    //
    DecimalFormat df0 = new DecimalFormat("###,###,###,###");
    DecimalFormat df1 = new DecimalFormat("###,###,##0.0");
    DecimalFormat df2 = new DecimalFormat("###,###,###.00");
    DecimalFormat df5 = new DecimalFormat("###,###,##0.00000");
    DecimalFormat Sf0 = new DecimalFormat("################");
    DecimalFormat Sf2 = new DecimalFormat("############.00");
    DecimalFormat porcentaje = new DecimalFormat("%.00");

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

    public void setIdOperacionTag(String idOperacionTag) {
        this.idOperacionTag = idOperacionTag;
    }

    public String getIdOperacionTag() {
        return idOperacionTag;
    }

    public int getIdOperacion() {
        return new Integer(getIdOperacionTag()).intValue();
    }
    // Variable para usar el bean de fachada
    ColaboraOrdenTrabajo colaboraOrdenTrabajo;
    // Variable para usar el bean de EstadoPcBean
    FachadaPluFicha fachadaPluFicha;
    // Iterador de objetos
    Iterator iteratorBean;

    private void inicializarVariablesDeRetorno() {

        //
        fachadaPluFicha = (FachadaPluFicha) iteratorBean.next();

        // Variable que retornan al JSP
        pageContext.setAttribute("numeroOrdenVar",
                fachadaPluFicha.getNumeroOrdenSf0());
        pageContext.setAttribute("idFichaVar", fachadaPluFicha.getIdFichaStr());        
        pageContext.setAttribute("referenciaClientePluVar", 
                                        fachadaPluFicha.getReferenciaCliente());        
        pageContext.setAttribute("nombreTerceroVar", 
                                            fachadaPluFicha.getNombreTercero());        
        pageContext.setAttribute("nombreOperarioVar", 
                                           fachadaPluFicha.getNombreOperario());                
        pageContext.setAttribute("existenciaCantidadVar",
                fachadaPluFicha.getExistenciaCantidadDf1());
        pageContext.setAttribute("existenciaPesoVar",
                fachadaPluFicha.getExistenciaPesoDf1());

        //
        xExistenciaPesoTotal += fachadaPluFicha.getExistenciaPeso();
        xExistenciaCantidadTotal += fachadaPluFicha.getExistenciaCantidad();

    }

    public int doStartTag() throws JspTagException {

        //
        xExistenciaPesoTotal = 0.0;
        xExistenciaCantidadTotal = 0.0;

        // Parametros llegados de JSP
        colaboraOrdenTrabajo = new ColaboraOrdenTrabajo();

        //
        colaboraOrdenTrabajo.setIdLocal(getIdLocalTag());
        colaboraOrdenTrabajo.setIdTipoOrden(getIdTipoOrdenTag());
        colaboraOrdenTrabajo.setIdOperacion(getIdOperacionTag());

        //
        Vector vectorBean = new Vector();

        //
        vectorBean = colaboraOrdenTrabajo.listaOTExterno();

        //
        iteratorBean = vectorBean.iterator();

        if (!iteratorBean.hasNext()) {
            try {
                pageContext.getOut().write("<br>No existen datos");
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

        //
        pageContext.setAttribute("existenciaCantidadTotalVar", df1.format(xExistenciaCantidadTotal));
        pageContext.setAttribute("existenciaPesoTotalVar", df1.format(xExistenciaPesoTotal));

        //
        return EVAL_PAGE;
    }
}
