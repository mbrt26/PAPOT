package com.solucionesweb.tags;

import java.util.*;
import java.io.IOException;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.tagext.TagSupport;

// Importa el Bean de Asignaturas
import com.solucionesweb.losbeans.colaboraciones.ColaboraTercero;

// Importa el Bean de Fachada
import com.solucionesweb.losbeans.fachada.FachadaTerceroBean;

public class SeleccionaTerceroxIdTipoTerceroTag extends TagSupport
                                   implements IterationTag {

    // Variables que llegan de JSP
    private String nombreTerceroTag;
    private String idTipoTerceroTag;

    // Metodos
	public String getNombreTerceroTag() {
		return nombreTerceroTag;
	}

	public void setNombreTerceroTag ( String nombreTerceroTag) {
		this.nombreTerceroTag = nombreTerceroTag;
	}

	public String getIdTipoTerceroTag() {
		return idTipoTerceroTag;
	}

	public void setIdTipoTerceroTag ( String idTipoTerceroTag) {
		this.idTipoTerceroTag = idTipoTerceroTag;
	}

    // Variable para usar el bean de fachada
    FachadaTerceroBean fachadaBean;

	// Variable para usar el bean de area
	ColaboraTercero colaboraTerceroBean;

    // Iterador de objetos
    Iterator iteratorBean;

    private void inicializarVariablesDeRetorno() {

      fachadaBean = (FachadaTerceroBean)iteratorBean.next();

      pageContext.setAttribute("idClienteVar",fachadaBean.getIdCliente());
      pageContext.setAttribute("idTerceroFormatVar",
                                           fachadaBean.getIdTerceroFormatStr());
      pageContext.setAttribute("tipoIdTerceroVar",
                                                fachadaBean.getTipoIdTercero());
      pageContext.setAttribute("digitoVerificacionVar",
                                        fachadaBean.getDigitoVerificacionStr());
      pageContext.setAttribute("idTipoTerceroVar",
                                             fachadaBean.getIdTipoTerceroStr());
      pageContext.setAttribute("idPersonaVar",fachadaBean.getIdPersonaStr());
      pageContext.setAttribute("idAutoRetenedorVar",
                                           fachadaBean.getIdAutoRetenedorStr());
      pageContext.setAttribute("idRegimenVar",fachadaBean.getIdRegimen());
      pageContext.setAttribute("nombreTerceroVar",
                                                fachadaBean.getNombreTercero());
      pageContext.setAttribute("direccionTerceroVar",
                                             fachadaBean.getDireccionTercero());
      pageContext.setAttribute("idDptoCiudadVar",
                                              fachadaBean.getIdDptoCiudadStr());
      pageContext.setAttribute("telefonoFijoVar",fachadaBean.getTelefonoFijo());
      pageContext.setAttribute("telefonoCelularVar",
                                              fachadaBean.getTelefonoCelular());
      pageContext.setAttribute("telefonoFaxVar",fachadaBean.getTelefonoFax());
      pageContext.setAttribute("emailVar",fachadaBean.getEmail());
      pageContext.setAttribute("idFormaPagoVar",
                                               fachadaBean.getIdFormaPagoStr());
      pageContext.setAttribute("nombreRegimenVar",
                                                fachadaBean.getNombreRegimen());
      pageContext.setAttribute("nombreTipoTerceroVar",
                                            fachadaBean.getNombreTipoTercero());
      pageContext.setAttribute("nombreCiudadVar",fachadaBean.getNombreCiudad());
      pageContext.setAttribute("nombreDptoVar",fachadaBean.getNombreDpto());
      pageContext.setAttribute("idVendedorVar",fachadaBean.getIdVendedorStr());
      pageContext.setAttribute("cupoCreditoVar",fachadaBean.getCupoCreditoDf0());

	}

	public int doStartTag() throws JspTagException
	{

		// Efectua la busqueda de los grados que estan matriculados
		colaboraTerceroBean               = new ColaboraTercero();

        //
		colaboraTerceroBean.setNombreTercero(getNombreTerceroTag());
		colaboraTerceroBean.setIdTipoTercero(getIdTipoTerceroTag());

        //
		Vector listaVector          = 
                    colaboraTerceroBean.seleccionaTerceroxNombreIdTipoTercero();

        //
        iteratorBean                = listaVector.iterator();

        //
        if ( !iteratorBean.hasNext() ) {
            try {
              pageContext.getOut().write("La consulta no devolvio ningun dato <br/>");
              pageContext.getOut().write("<br/>");
			  pageContext.getOut().write("Por favor comunique al administrador del sistema que no existen grados <br/>");
		    }
            catch(IOException ex) {
			  throw new JspTagException ("Error: la respuesta no se pudo escribir para los grados");
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