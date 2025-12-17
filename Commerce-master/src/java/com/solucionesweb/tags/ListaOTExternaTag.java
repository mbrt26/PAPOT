package com.solucionesweb.tags;

import java.util.*;
import java.io.IOException;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.tagext.TagSupport;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaDctoOrdenProgreso;

// Importa el Bean de Asignaturas
import com.solucionesweb.losbeans.colaboraciones.ColaboraOrdenProgreso;

public class ListaOTExternaTag extends TagSupport
        implements IterationTag {

    // Variable que recibe del JSP
    private String idLocalTag;
    private String idTipoOrdenTag;
    private String numeroOrdenTag;
    private String itemPadreTag;

    //
    String xNombreTipoControl = "";
    String xNombreTipoControlSalida = "SALIDA";
    String xNombreTipoControlEntrada = "ENTRADA";
    
    //
    double xCantidadTerminadaSaldo        = 0.0;
    double xPesoTerminadoSaldo            = 0.0;

    //
    int xIdControlTipoEntrada = 2;

    // Metodos Tag
    public void setIdTipoOrdenTag(String idTipoOrdenTag) {
        this.idTipoOrdenTag = idTipoOrdenTag;
    }

    public String getIdTipoOrdenTag() {
        return idTipoOrdenTag;
    }

    public int getIdTipoOrden() {
        return new Integer(getIdTipoOrdenTag());
    }

    public void setIdLocalTag(String idLocalTag) {
        this.idLocalTag = idLocalTag;
    }

    public String getIdLocalTag() {
        return idLocalTag;
    }

    public void setNumeroOrdenTag(String numeroOrdenTag) {
        this.numeroOrdenTag = numeroOrdenTag;
    }

    public String getNumeroOrdenTag() {
        return numeroOrdenTag;
    }

    public void setItemPadreTag(String itemPadreTag) {
        this.itemPadreTag = itemPadreTag;
    }

    public String getItemPadreTag() {
        return itemPadreTag;
    }
    // Variable para usar el bean de fachada
    FachadaDctoOrdenProgreso fachadaDctoOrdenProgreso;
    // Variable para usar el bean de EstadoPcBean
    ColaboraOrdenProgreso colaboraOrdenProgreso;
    // Iterador de objetos
    Iterator iteratorBean;

    private void inicializarVariablesDeRetorno() {

        //
        fachadaDctoOrdenProgreso =
                (FachadaDctoOrdenProgreso) iteratorBean.next();

        //
        xNombreTipoControl = xNombreTipoControlEntrada;

        //
        if (fachadaDctoOrdenProgreso.getIdControlTipo()
                == xIdControlTipoEntrada) {

            //
            xNombreTipoControl = xNombreTipoControlSalida;

        }

        //
        xCantidadTerminadaSaldo +=
                                fachadaDctoOrdenProgreso.getCantidadTerminada() *
                                fachadaDctoOrdenProgreso.getIdSigno() * (-1) ;
        
        //
        xPesoTerminadoSaldo += fachadaDctoOrdenProgreso.getPesoTerminado() *
                                fachadaDctoOrdenProgreso.getIdSigno() * (-1) ;

        //
        fachadaDctoOrdenProgreso.setCantidadTerminadaSaldo(
                                                       xCantidadTerminadaSaldo);
        fachadaDctoOrdenProgreso.setPesoTerminadoSaldo(xPesoTerminadoSaldo);


        // Variable que retornan al JSP
        pageContext.setAttribute("idOrdenVar",
                fachadaDctoOrdenProgreso.getIdOrdenStr());
        pageContext.setAttribute("itemVar",
                fachadaDctoOrdenProgreso.getItemStr());
        pageContext.setAttribute("cantidadPerdidaVar",
                fachadaDctoOrdenProgreso.getCantidadPerdidaDf0());
        pageContext.setAttribute("cantidadTerminadaVar",
                fachadaDctoOrdenProgreso.getCantidadTerminadaDf0());
        pageContext.setAttribute("pesoPerdidoVar",
                fachadaDctoOrdenProgreso.getPesoPerdidoDf0());
        pageContext.setAttribute("pesoTerminadoVar",
                fachadaDctoOrdenProgreso.getPesoTerminadoDf0());
        pageContext.setAttribute("fechaInicioVar",
                fachadaDctoOrdenProgreso.getFechaInicioCorta());
        pageContext.setAttribute("cantidadPedidaVar",
                fachadaDctoOrdenProgreso.getCantidadPedidaDf0());
        pageContext.setAttribute("pesoPedidoVar",
                fachadaDctoOrdenProgreso.getPesoPedidoDf0());
        pageContext.setAttribute("idControlTipoVar",
                fachadaDctoOrdenProgreso.getIdControlTipoStr());
        pageContext.setAttribute("idControlVar",
                fachadaDctoOrdenProgreso.getIdControlStr());
        pageContext.setAttribute("observacionVar",
                fachadaDctoOrdenProgreso.getObservacion());
        pageContext.setAttribute("nombreOperacionVar",
                fachadaDctoOrdenProgreso.getNombreOperacion());
        pageContext.setAttribute("nombreTerceroVar",
                fachadaDctoOrdenProgreso.getNombreTercero());
        pageContext.setAttribute("referenciaClienteVar",
                fachadaDctoOrdenProgreso.getReferenciaCliente());
        pageContext.setAttribute("referenciaVar",
                fachadaDctoOrdenProgreso.getReferencia());
        pageContext.setAttribute("idSignoVar",
                fachadaDctoOrdenProgreso.getIdSignoStr());
        pageContext.setAttribute("nombreTipoControlVar",xNombreTipoControl);
        pageContext.setAttribute("cantidadTerminadaSaldoVar",
                       fachadaDctoOrdenProgreso.getCantidadTerminadaSaldoDf0());
        pageContext.setAttribute("pesoTerminadoSaldoVar",
                           fachadaDctoOrdenProgreso.getPesoTerminadoSaldoDf0());

    }

    public int doStartTag() throws JspTagException {

        // Parametros llegados de JSP
        colaboraOrdenProgreso = new ColaboraOrdenProgreso();

        //
        colaboraOrdenProgreso.setIdLocal(getIdLocalTag());
        colaboraOrdenProgreso.setIdTipoOrden(getIdTipoOrdenTag());
        colaboraOrdenProgreso.setNumeroOrden(getNumeroOrdenTag());
        colaboraOrdenProgreso.setItemPadre(getItemPadreTag());

        //
        Vector vectorBean =
                colaboraOrdenProgreso.listaOTExterna();

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
        xCantidadTerminadaSaldo        = 0.0;
        xPesoTerminadoSaldo            = 0.0;

        //
        return EVAL_PAGE;
    }
}
