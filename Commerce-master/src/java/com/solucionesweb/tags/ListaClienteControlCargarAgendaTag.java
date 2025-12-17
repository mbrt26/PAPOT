package com.solucionesweb.tags;

import java.util.*;
import java.io.IOException;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.tagext.TagSupport;

// Importa la clase que posibilita el formateo
import java.text.NumberFormat;

// Importa el Bean de Asignaturas
import com.solucionesweb.losbeans.colaboraciones.ColaboraAgendaControlBean;

// Importa el Bean de Fachada
import com.solucionesweb.losbeans.fachada.FachadaColaboraAgendaControlBean;

public class ListaClienteControlCargarAgendaTag extends TagSupport
                                     implements IterationTag {

    // Variable que recibe del JSP
    private String idClienteTag;
    private String idSucursalTag;
    private String fechaVisitaTag;

    // Variable que retornan al JSP
    private String idClienteVar;
    private String idUsuarioVar;
    private String idSucursalVar;
    private String idPeriodoVar;
    private String fechaVisitaVar;
    private String estadoVar;

    private String nombreClienteVar;
    private String direccionVar;
    private String telefonoVar;
    private String nombreEmpresaVar;
    private String ciudadDireccionVar;
    private String departamentoVar;

    NumberFormat nf = NumberFormat.getNumberInstance();

    // Metodos Tag
    public void setIdClienteTag( String idClienteTag )
    {
        this.idClienteTag = idClienteTag ;
    }

    public String getIdClienteTag()
    {
        return idClienteTag;
    }

    public void setIdSucursalTag( String idSucursalTag )
    {
        this.idSucursalTag = idSucursalTag ;
    }

    public String getIdSucursalTag()
    {
        return idSucursalTag;
    }

    public void setFechaVisitaTag( String fechaVisitaTag )
    {
        this.fechaVisitaTag = fechaVisitaTag ;
    }

    public String getFechaVisitaTag()
    {
        return fechaVisitaTag;
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

    public void setIdPeriodoVar( String idPeriodoVar )
    {
        this.idPeriodoVar = idPeriodoVar ;
    }

    public String getIdPeriodoVar()
    {
        return idPeriodoVar;
    }

    public void setFechaVisitaVar( String fechaVisitaVar )
    {
        this.fechaVisitaVar = fechaVisitaVar ;
    }

    public String getFechaVisitaVar()
    {
        return fechaVisitaVar;
    }

    public String getEstadoVar()
    {
        return estadoVar;
    }

    public void setEstadoVar( String estadoVar )
    {
        this.estadoVar = estadoVar ;
    }

    public void setNombreClienteVar( String nombreClienteVar )
    {
        this.nombreClienteVar = nombreClienteVar ;
    }

    public String getNombreClienteVar()
    {
        return nombreClienteVar;
    }

    public void setDireccionVar( String direccionVar )
    {
        this.direccionVar = direccionVar ;
    }

    public String getDireccionVar()
    {
        return direccionVar;
    }

    public void setTelefonoVar( String telefonoVar )
    {
        this.telefonoVar = telefonoVar ;
    }

    public String getTelefonoVar()
    {
        return telefonoVar;
    }

    public void setNombreEmpresaVar( String nombreEmpresaVar )
    {
        this.nombreEmpresaVar = nombreEmpresaVar ;
    }

    public String getNombreEmpresaVar()
    {
        return nombreEmpresaVar;
    }

    public void setCiudadDireccionVar( String ciudadDireccionVar )
    {
        this.ciudadDireccionVar = ciudadDireccionVar ;
    }

    public String getCiudadDireccionVar()
    {
        return ciudadDireccionVar;
    }

    public void setDepartamentoVar( String departamentoVar )
    {
        this.departamentoVar = departamentoVar ;
    }

    public String getDepartamentoVar()
    {
        return departamentoVar;
    }

    // Variable para usar el bean de fachada
    FachadaColaboraAgendaControlBean fachadaAgendaBean;

	// Variable para usar el bean de EstadoPcBean
	ColaboraAgendaControlBean agendaBean;

    // Iterador de objetos
    Iterator iteratorBean;

    private void inicializarVariablesDeRetorno() {

      //
      fachadaAgendaBean = (FachadaColaboraAgendaControlBean)iteratorBean.next();

      // Variable que retornan al JSP
      idClienteVar       = fachadaAgendaBean.getIdCliente();
      idUsuarioVar       = nf.format(fachadaAgendaBean.getIdUsuario());
      idSucursalVar      = fachadaAgendaBean.getIdSucursal();
      idPeriodoVar       = fachadaAgendaBean.getIdPeriodoStr();
      fechaVisitaVar     = fachadaAgendaBean.getFechaVisitaStr();
      estadoVar          = fachadaAgendaBean.getEstadoStr();
      nombreClienteVar   = fachadaAgendaBean.getNombreCliente();
      direccionVar       = fachadaAgendaBean.getDireccion();
      telefonoVar        = fachadaAgendaBean.getTelefono();
      nombreEmpresaVar   = fachadaAgendaBean.getNombreEmpresa();
      ciudadDireccionVar = fachadaAgendaBean.getCiudadDireccion();
      departamentoVar    = fachadaAgendaBean.getDepartamento();

      pageContext.setAttribute("idClienteVar",idClienteVar);
      pageContext.setAttribute("idUsuarioVar",idUsuarioVar);
      pageContext.setAttribute("idSucursalVar",idSucursalVar);
      pageContext.setAttribute("idPeriodoVar",idPeriodoVar);
      pageContext.setAttribute("fechaVisitaVar",fechaVisitaVar);
      pageContext.setAttribute("estadoVar",estadoVar);
      pageContext.setAttribute("nombreClienteVar",nombreClienteVar);
      pageContext.setAttribute("direccionVar",direccionVar);
      pageContext.setAttribute("telefonoVar",telefonoVar);
      pageContext.setAttribute("nombreEmpresaVar",nombreEmpresaVar);
      pageContext.setAttribute("ciudadDireccionVar",ciudadDireccionVar);
      pageContext.setAttribute("departamentoVar",departamentoVar);

	}

	public int doStartTag() throws JspTagException {

      	// Parametros llegados de JSP
        agendaBean  = new ColaboraAgendaControlBean();
        agendaBean.setIdCliente(getIdClienteTag());
        agendaBean.setIdSucursal(getIdSucursalTag());
        agendaBean.setFechaVisitaStr(getFechaVisitaTag());

		Vector vectorBean    = agendaBean.listaClienteSucursalFechaVisita();

        //
        iteratorBean         = vectorBean.iterator();

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