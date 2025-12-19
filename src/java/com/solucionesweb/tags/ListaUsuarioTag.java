package com.solucionesweb.tags;

import java.util.*;
import java.io.IOException;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.tagext.TagSupport;

// Importa el Bean de Asignaturas
import com.solucionesweb.losbeans.negocio.UsuarioBean;

// Importa el Bean de Fachada
import com.solucionesweb.losbeans.fachada.FachadaUsuarioBean;

public class ListaUsuarioTag extends TagSupport
                                    implements IterationTag {

    // Variable que recibe del JSP
    private String nombreUsuarioTag;

    // Metodos Tag
    public void setNombreUsuarioTag( String nombreUsuarioTag )
    {
        this.nombreUsuarioTag = nombreUsuarioTag ;
    }

    public String getNombreUsuarioTag()
    {
        return nombreUsuarioTag;
    }


    // Variable para usar el bean de fachada
    FachadaUsuarioBean fachadaUsuarioBean;

	// Variable para usar el bean de area
	UsuarioBean usuarioBean;

    // Iterador de objetos
    Iterator iteratorBean;

    private void inicializarVariablesDeRetorno() {

      fachadaUsuarioBean = (FachadaUsuarioBean)iteratorBean.next();

      //
      pageContext.setAttribute("idUsuarioVar",
                                          fachadaUsuarioBean.getIdUsuarioSf0());
      pageContext.setAttribute("nombreUsuarioVar",
                                         fachadaUsuarioBean.getNombreUsuario());
      pageContext.setAttribute("claveVar",fachadaUsuarioBean.getClave());
      pageContext.setAttribute("idNivelVar",fachadaUsuarioBean.getIdNivelStr());
      pageContext.setAttribute("direccionVar",
                                             fachadaUsuarioBean.getDireccion());
      pageContext.setAttribute("telefonoVar",fachadaUsuarioBean.getTelefono());
      pageContext.setAttribute("fechaCambioClaveVar",
                                      fachadaUsuarioBean.getFechaCambioClave());
      pageContext.setAttribute("estadoVar",fachadaUsuarioBean.getEstadoStr());
      pageContext.setAttribute("emailVar",fachadaUsuarioBean.getEmail());
      pageContext.setAttribute("idLocalVar",
                                     fachadaUsuarioBean.getIdLocalUsuarioStr());
      pageContext.setAttribute("aliasUsuarioVar",
                                          fachadaUsuarioBean.getAliasUsuario());
      pageContext.setAttribute("idSeqVar",fachadaUsuarioBean.getIdSeqStr());

   }

	public int doStartTag() throws JspTagException 	{


        //
        usuarioBean  = new UsuarioBean();

        //
        usuarioBean.setNombreUsuario(getNombreUsuarioTag());

        //
        Vector listaVector = usuarioBean.seleccionaUsuario();

        //
        iteratorBean       = listaVector.iterator();

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