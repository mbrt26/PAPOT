package com.solucionesweb.tags;

import java.util.*;
import java.io.*;
import java.io.IOException;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.tagext.TagSupport;

// Importa el Bean de Asignaturas
import com.solucionesweb.losbeans.colaboraciones.ColaboraLogisticaBean;

// Importa el Bean de Fachada
import com.solucionesweb.losbeans.fachada.FachadaColaboraLogisticaBean;

public class ListaLogisticaOrdenCompraTag extends TagSupport
                                        implements IterationTag {

    // Variable que recibe del Tag
    private String idLocalTag;
    private String ordenCompraTag;

    // Metodos Tag
    public void setIdLocalTag( String idLocalTag )
    {
        this.idLocalTag = idLocalTag ;
    }

    public String getIdLocalTag()
    {
        return idLocalTag;
    }

    public void setOrdenCompraTag( String ordenCompraTag )
    {
        this.ordenCompraTag = ordenCompraTag ;
    }

    public String getOrdenCompraTag()
    {
        return ordenCompraTag;
    }
    // Variable para usar el bean de fachada
    FachadaColaboraLogisticaBean fachadaColaboraLogisticaBean;

	// Variable para usar el bean de EstadoPcBean
	ColaboraLogisticaBean colaboraLogisticaBean;

    // Iterador de objetos
    Iterator iteratorBean;

    private void inicializarVariablesDeRetorno() {

      //
      fachadaColaboraLogisticaBean = (FachadaColaboraLogisticaBean)iteratorBean.next();

      // Variable que retornan al JSP
      pageContext.setAttribute("idPedidoVar",fachadaColaboraLogisticaBean.getIdPedidoStr());
      pageContext.setAttribute("tipoDctoVar",fachadaColaboraLogisticaBean.getTipoDcto());
      pageContext.setAttribute("idDctoVar",fachadaColaboraLogisticaBean.getIdDctoStr());
      pageContext.setAttribute("fechaPedidoVar",fachadaColaboraLogisticaBean.getFechaPedido());

	}

	public int doStartTag() throws JspTagException {

      	// Parametros llegados de JSP
        colaboraLogisticaBean  = new ColaboraLogisticaBean();

		//  listaFechasTipoOrdenUsuario
		colaboraLogisticaBean.setIdLocal(getIdLocalTag());
        colaboraLogisticaBean.setOrdenCompra(getOrdenCompraTag());

        Vector vectorBean = colaboraLogisticaBean.consultaPedido();

        iteratorBean      = vectorBean.iterator();

        if ( !iteratorBean.hasNext() ) {
            try {
              pageContext.getOut().write("No existen datos <br/>");
		    }
            catch(IOException ex) {
			  throw new JspTagException ("Error: la respuesta no se pudo escribir para los datos");
		    }
		    finally {
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

	public int doEndTag() throws JspTagException
	{
       return EVAL_PAGE;
	}

    public void doCatch(Throwable t) throws Throwable {
      System.out.println("Error: " + t);
      pageContext.getOut().println("<font color=\"red\">" +
                                   "Invocando <b>doCatch</b> debido a (" +
                                   t + ")</font>");
    }
    public void doFinally() {
    }
}