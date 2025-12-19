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

public class ListaInventarioOperacionTag extends TagSupport
        implements IterationTag {

    // Variable que recibe del JSP
    private String idLocalTag;
    private String idTipoOrdenTag;
    private String idOperacionTag;

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

    public int vtaFrmLstInventarioOperacion() {
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
        pageContext.setAttribute("idLocalVar",fachadaPluFicha.getIdLocalStr());
        pageContext.setAttribute("idTipoOrdenVar",
                                               fachadaPluFicha.getIdLocalStr());
        pageContext.setAttribute("idOrdenVar", fachadaPluFicha.getIdLocalStr());
        pageContext.setAttribute("itemVar", fachadaPluFicha.getIdLocalStr());
        pageContext.setAttribute("nombreOperacionVar",
                                          fachadaPluFicha.getNombreOperacion());
        pageContext.setAttribute("cantidadPedidaVar",
                                        fachadaPluFicha.getCantidadPedidaStr());
        pageContext.setAttribute("pesoPerdidoVar",
                                           fachadaPluFicha.getPesoPerdidoStr());
        pageContext.setAttribute("cantidadPerdidaVar",
                                       fachadaPluFicha.getCantidadPerdidaStr());
        pageContext.setAttribute("nombreTerceroVar",
                                            fachadaPluFicha.getNombreTercero());
        pageContext.setAttribute("referenciaClienteVar",
                                        fachadaPluFicha.getReferenciaCliente());
        pageContext.setAttribute("nombreReferenciaVar",
                                         fachadaPluFicha.getNombreReferencia());
        pageContext.setAttribute("numeroOrdenVar",
                                           fachadaPluFicha.getNumeroOrdenStr());
        pageContext.setAttribute("cantidadTerminadaVar",
                                     fachadaPluFicha.getCantidadTerminadaStr());
        pageContext.setAttribute("cantidadEntregadaVar",
                                     fachadaPluFicha.getCantidadEntregadaStr());
        pageContext.setAttribute("cantidadFacturadaVar",
                                     fachadaPluFicha.getCantidadFacturadaStr());
        pageContext.setAttribute("pesoEntregadoVar",
                                         fachadaPluFicha.getPesoEntregadoStr());
        pageContext.setAttribute("pesoFacturadoVar",
                                         fachadaPluFicha.getPesoFacturadoStr());
        pageContext.setAttribute("pesoTerminadoVar",
                                         fachadaPluFicha.getPesoTerminadoStr());
        pageContext.setAttribute("existenciaCantidadVar",
                                   fachadaPluFicha.getExistenciaCantidadDf0());
        pageContext.setAttribute("existenciaPesoVar",
                                        fachadaPluFicha.getExistenciaPesoDf0());

    }

    public int doStartTag() throws JspTagException {

        // Parametros llegados de JSP
        colaboraOrdenTrabajo = new ColaboraOrdenTrabajo();

        //
        colaboraOrdenTrabajo.setIdLocal(getIdLocalTag());
        colaboraOrdenTrabajo.setIdTipoOrden(getIdTipoOrdenTag());
        colaboraOrdenTrabajo.setIdOperacion(getIdOperacionTag());
        
        //
        Vector vectorBean = new Vector();

        //
        if (vtaFrmLstInventarioOperacion() == 0) {

           //
           vectorBean = colaboraOrdenTrabajo.listaOTAllOperacion();

        }

        //
        if (vtaFrmLstInventarioOperacion() > 0) {

           //
           vectorBean = colaboraOrdenTrabajo.listaOT();

        }


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
        return EVAL_PAGE;
    }
}
