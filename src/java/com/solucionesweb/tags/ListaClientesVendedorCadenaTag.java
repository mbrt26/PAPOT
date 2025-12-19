package com.solucionesweb.tags;

import java.util.*;
import java.io.IOException;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.tagext.TagSupport;


// Importa el Bean de JhFormat
import com.solucionesweb.losbeans.utilidades.JhFormat;

// Importa el Bean de Asignaturas
import com.solucionesweb.losbeans.colaboraciones.ColaboraAgendaControlBean;

// Importa el Bean de Fachada
import com.solucionesweb.losbeans.fachada.FachadaColaboraAgendaControlBean;

// Importa la clase que posibilita el formateo
import java.text.NumberFormat;

public class ListaClientesVendedorCadenaTag extends TagSupport
                                     implements IterationTag {

    // Variable que recibe del JSP
    private String idUsuarioTag;
    private String idClienteCadenaTag;

    // Variable que retornan al JSP
    private String idClienteVar;
    private String idUsuarioVar;
    private String idSucursalVar;
    private String idFrecuenciaVar;
    private String idDiaVisitaVar;
    private String estadoVar;

    NumberFormat nf = NumberFormat.getNumberInstance();

    JhFormat formato        = new JhFormat();

    // Metodos Tag
    public void setIdUsuarioTag( String idUsuarioTag )
    {
        this.idUsuarioTag = idUsuarioTag ;
    }

    public String getIdUsuarioTag()
    {
        return idUsuarioTag;
    }


    public void setIdClienteCadenaTag( String idClienteCadenaTag )
    {
        this.idClienteCadenaTag = idClienteCadenaTag ;
    }

    public String getIdClienteCadenaTag()
    {
        return idClienteCadenaTag;
    }

    // Metodos Var
    public void setIdClienteVar( String idClienteVar )
    {
        this.idClienteVar = idClienteVar ;
    }

    public String getIdClienteVar()
    {
        return idClienteVar;
    }

    public void setIdUsuarioVar( String idUsuarioVar )
    {
        this.idUsuarioVar = idUsuarioVar ;
    }

    public String getIdUsuarioVar()
    {
        return idUsuarioVar;
    }

    public void setIdSucursalVar( String idSucursalVar )
    {
        this.idSucursalVar = idSucursalVar ;
    }

    public String getIdSucursalVar()
    {
        return idSucursalVar;
    }

    public void setIdFrecuenciaVar( String idFrecuenciaVar )
    {
        this.idFrecuenciaVar = idFrecuenciaVar ;
    }

    public String getIdFrecuenciaVar()
    {
        return idFrecuenciaVar;
    }

    public void setIdDiaVisitaVar( String idDiaVisitaVar )
    {
        this.idDiaVisitaVar = idDiaVisitaVar ;
    }

    public String getIdDiaVisitaVar()
    {
        return idDiaVisitaVar;
    }

    public String getEstadoVar()
    {
        return estadoVar;
    }

    public void setEstadoVar( String estadoVar )
    {
        this.estadoVar = estadoVar ;
    }

    // Variable para usar el bean de fachada
    FachadaColaboraAgendaControlBean fachadaColaboraAgendaControlBean;

	// Variable para usar el bean de EstadoPcBean
	ColaboraAgendaControlBean colaboraAgendaControlBean;

    // Iterador de objetos
    Iterator iteratorBean;

    private void inicializarVariablesDeRetorno() {

      //
      fachadaColaboraAgendaControlBean = (FachadaColaboraAgendaControlBean)iteratorBean.next();

      // Variable que retornan al JSP
      pageContext.setAttribute("idClienteVar",formato.withOutPoint(fachadaColaboraAgendaControlBean.getIdCliente()));
      pageContext.setAttribute("nombreClienteVar",fachadaColaboraAgendaControlBean.getNombreCliente());

	}

	public int doStartTag() throws JspTagException {

      	// Parametros llegados de JSP
        colaboraAgendaControlBean  = new ColaboraAgendaControlBean();
        colaboraAgendaControlBean.setIdUsuario(getIdUsuarioTag());
        colaboraAgendaControlBean.setIdClienteCadena(getIdClienteCadenaTag());

        //
		Vector vectorBean          = colaboraAgendaControlBean.listaVariosClientes();

        iteratorBean               = vectorBean.iterator();

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