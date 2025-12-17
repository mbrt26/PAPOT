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

public class ListaUsuarioLocalOpcionTag extends TagSupport
                             implements IterationTag {

    //
    private String idUsuarioTag;
    private String idNivelTag;
    private String idLocalUsuarioTag;
    private String estadoTag;

    // Metodos Tag
    public void setIdUsuarioTag( String idUsuarioTag )
    {
        this.idUsuarioTag = idUsuarioTag ;
    }

    public String getIdUsuarioTag()
    {
        return idUsuarioTag;
    }

    // Metodos Tag
    public void setIdLocalUsuarioTag( String idLocalUsuarioTag )
    {
        this.idLocalUsuarioTag = idLocalUsuarioTag ;
    }

    public String getIdLocalUsuarioTag()
    {
        return idLocalUsuarioTag;
    }

    // Metodos Tag
    public void setIdNivelTag( String idNivelTag )
    {
        this.idNivelTag = idNivelTag ;
    }

    public String getIdNivelTag()
    {
        return idNivelTag;
    }

    // Metodos Tag
    public void setEstadoTag( String estadoTag )
    {
        this.estadoTag = estadoTag ;
    }

    public String getEstadoTag()
    {
        return estadoTag;
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
                                          fachadaUsuarioBean.getIdUsuarioStr());
      pageContext.setAttribute("nombreUsuarioVar",
                                         fachadaUsuarioBean.getNombreUsuario());

	}

	public int doStartTag() throws JspTagException 	{

		// Efectua la busqueda de los grados que estan matriculados
		usuarioBean        = new UsuarioBean();

        //
        usuarioBean.setIdUsuario(getIdUsuarioTag());
        usuarioBean.setIdNivel(getIdNivelTag());
        usuarioBean.setEstado(getEstadoTag());
        usuarioBean.setIdLocalUsuario(getIdLocalUsuarioTag());


        //
		Vector listaVector = usuarioBean.listaUsuarioOpcion();

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