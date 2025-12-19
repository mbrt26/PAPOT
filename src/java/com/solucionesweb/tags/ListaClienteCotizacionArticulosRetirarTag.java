package com.solucionesweb.tags;

import java.util.*;
import java.io.IOException;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.tagext.TagSupport;

// Importa la clase que posibilita el formateo
import java.text.NumberFormat;

// Importa el Bean de Asignaturas
import com.solucionesweb.losbeans.colaboraciones.ColaboraDctoOrdenDetalleBean;

// Importa el Bean de Fachada
import com.solucionesweb.losbeans.fachada.FachadaDctoOrdenDetalleBean;

public class ListaClienteCotizacionArticulosRetirarTag extends TagSupport
                                     implements IterationTag {

    // Variable que recibe del JSP
    private String idLocalTag;
    private String idLogTag;


    NumberFormat nf = NumberFormat.getNumberInstance();

    // Metodos Tag
    public void setIdLocalTag( String idLocalTag )
    {
        this.idLocalTag = idLocalTag ;
    }

    public String getIdLocalTag()
    {
        return idLocalTag;
    }

    public void setIdLogTag( String idLogTag )
    {
        this.idLogTag = idLogTag ;
    }

    public String getIdLogTag()
    {
        return idLogTag;
    }

    // Variable para usar el bean de fachada
    FachadaDctoOrdenDetalleBean fachadaDctoOrdenDetalleBean;

	// Variable para usar el bean
	ColaboraDctoOrdenDetalleBean colaboraDctoOrdenDetalleBean;

    // Iterador de objetos
    Iterator iteratorBean;

    private void inicializarVariablesDeRetorno() {

      //
      fachadaDctoOrdenDetalleBean
                             = (FachadaDctoOrdenDetalleBean)iteratorBean.next();

      // Variable que retornan al JSP
      pageContext.setAttribute("idPluVar",
                                     fachadaDctoOrdenDetalleBean.getIdPluStr());
      pageContext.setAttribute("strIdReferenciaVar",
                              fachadaDctoOrdenDetalleBean.getStrIdReferencia());
      pageContext.setAttribute("nombrePluVar",
                                    fachadaDctoOrdenDetalleBean.getNombrePlu());
      pageContext.setAttribute("cantidadVar", "#" +
                          nf.format(fachadaDctoOrdenDetalleBean.getCantidad()));
      pageContext.setAttribute("vrVentaUnitarioVar",
                           fachadaDctoOrdenDetalleBean.getVrVentaUnitarioDf2());
      pageContext.setAttribute("nombreLineaVar", "xxxxxxxxx");
      pageContext.setAttribute("nombreSublineaVar","xxxxxxx");


	}

	public int doStartTag() throws JspTagException {

        int estadoRetirar = 1;

      	// Parametros llegados de JSP
        colaboraDctoOrdenDetalleBean
                                       = new ColaboraDctoOrdenDetalleBean();

        //
        colaboraDctoOrdenDetalleBean.setIdLocal(getIdLocalTag());
        colaboraDctoOrdenDetalleBean.setIdLog(getIdLogTag());
        colaboraDctoOrdenDetalleBean.setEstado(estadoRetirar);

		//
        Vector vectorBean = 
                colaboraDctoOrdenDetalleBean.listaRetiraArticulo();


        iteratorBean      = vectorBean.iterator();

        //
        if ( !iteratorBean.hasNext() ) {
            try {
              pageContext.getOut().write("La consulta no devolvio ningun dato <br/>");
              pageContext.getOut().write("<br/>");
			  pageContext.getOut().write("Por favor comunique al administrador del sistema que no existen datos <br/>");
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
}