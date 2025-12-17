package com.solucionesweb.tags;

import java.util.*;
import java.io.IOException;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.tagext.TagSupport;

// Importa la clase que posibilita el formateo
import java.text.NumberFormat;

// Importa el Bean de Asignaturas
import com.solucionesweb.losbeans.colaboraciones.ColaboraReporteLogBean;

// Importa el Bean de Fachada
import com.solucionesweb.losbeans.fachada.FachadaColaboraReporteLogBean;

public class ListaVisitaActualCOTag extends TagSupport
                                    implements IterationTag {

    // Variable que recibe del Tag
    private String idUsuarioTag;
    private String fechaOrdenTag;

    NumberFormat nf = NumberFormat.getNumberInstance();

    //
    int idEstadoVisitaSeguimiento = 12;

    // Metodos Tag
    public void setIdUsuarioTag( String idUsuarioTag )
    {
        this.idUsuarioTag = idUsuarioTag ;
    }

    public String getIdUsuarioTag()
    {
        return idUsuarioTag;
    }

    public void setFechaOrdenTag( String fechaOrdenTag )
    {
        this.fechaOrdenTag = fechaOrdenTag ;
    }

    public String getFechaOrdenTag()
    {
        return fechaOrdenTag;
    }

    // Variable para usar el bean de fachada
    FachadaColaboraReporteLogBean fachadaColaboraReporteLogBean;

	// Variable para usar el bean de EstadoPcBean
	ColaboraReporteLogBean colaboraReporteLogBean;

    // Iterador de objetos
    Iterator iteratorBean;

    private void inicializarVariablesDeRetorno() {

      //
      fachadaColaboraReporteLogBean = (FachadaColaboraReporteLogBean)iteratorBean.next();

      // Variable que retornan al JSP
      pageContext.setAttribute("idUsuarioVar",nf.format(fachadaColaboraReporteLogBean.getIdUsuario()));
      pageContext.setAttribute("nombreUsuarioVar",fachadaColaboraReporteLogBean.getNombreUsuario());
      pageContext.setAttribute("idClienteVar",fachadaColaboraReporteLogBean.getIdCliente());
      pageContext.setAttribute("nombreClienteVar",fachadaColaboraReporteLogBean.getNombreCliente());
      pageContext.setAttribute("idEstadoVisitaVar",fachadaColaboraReporteLogBean.getIdEstadoVisitaStr());
      pageContext.setAttribute("nombreEstadoVar",fachadaColaboraReporteLogBean.getNombreEstado());
      pageContext.setAttribute("horaVisitaVar",fachadaColaboraReporteLogBean.getHoraVisita());
      pageContext.setAttribute("idLogVar",fachadaColaboraReporteLogBean.getIdLogStr());

	}

	public int doStartTag() throws JspTagException {

      	// Parametros llegados de JSP
        colaboraReporteLogBean  = new ColaboraReporteLogBean();

		//  listaVisitaActualCO
		colaboraReporteLogBean.setIdUsuario(getIdUsuarioTag());
        colaboraReporteLogBean.setFechaVisita(getFechaOrdenTag());
        colaboraReporteLogBean.setIdEstadoVisita(idEstadoVisitaSeguimiento);

        //
        Vector vectorBean = colaboraReporteLogBean.listaVisitaActualRuta();

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