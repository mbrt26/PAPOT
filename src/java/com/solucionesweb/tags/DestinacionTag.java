package com.solucionesweb.tags;

import java.util.*;
import java.io.IOException;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.tagext.TagSupport;


// Importa el Bean de Negocio o Colaboraciones donde se encuentra el acceso a la base de datos
import com.solucionesweb.losbeans.negocio.DestinacionBean;

// Importa el Bean de Fachada que actua como contenedor
import com.solucionesweb.losbeans.fachada.FachadaDestinacionBean;

public class DestinacionTag  extends TagSupport
                             implements IterationTag, TryCatchFinally
{
    // Atributos que pasan desde el jsp

    // Variables de Retorno a jsp
	private String instruccionDestinacion;
	private String idDestinacion;

    // Variable del Bean de Negocio o Colaboraciones
    DestinacionBean DestinacionBean;

    // Variable del page Bean;
    FachadaDestinacionBean fachadaDestinacionBean;

    // Iterador de objetos
    Iterator iteratorObjetos;

    // Vector de objetos
    Vector vectorObjetos;

	public void setInstrucciondDestinacion(String instruccionDestinacion)
	{
		this.instruccionDestinacion = instruccionDestinacion;
	}

	public String getInstruccionDestinacion() {
		return instruccionDestinacion;
	}

	public void setIdDestinacion(String idDestinacion)
	{
		this.idDestinacion = idDestinacion;
	}

	public String getIdDestinacion() {
		return idDestinacion;
	}


    private void inicializarVariablesDeRetorno() {

	  fachadaDestinacionBean = (FachadaDestinacionBean)iteratorObjetos.next();

	  setInstrucciondDestinacion(fachadaDestinacionBean.getInstruccionDestinacion());
	  setIdDestinacion(fachadaDestinacionBean.getIdDestinacion());
      try {
        pageContext.setAttribute("instruccionDestinacion",getInstruccionDestinacion());
        pageContext.setAttribute("idDestinacion",getIdDestinacion());
      }
      catch (Exception e) {
         System.out.println("Exception in los pageContext de DestinacionTag" + e);
	  }
	}


	public int doStartTag() throws JspTagException
	{
		// Efectua la busqueda
        DestinacionBean = new DestinacionBean();

        // Efectua set si es del caso
        vectorObjetos = DestinacionBean.obtenerDestinacion();

        iteratorObjetos = vectorObjetos.iterator();

        if (!iteratorObjetos.hasNext()) {
            try {
              pageContext.getOut().write("<br>");
              pageContext.getOut().write("No se encontro ningun dato.");
              pageContext.getOut().write("<br/>");
		    }
            catch(IOException ex) {
			  throw new JspTagException ("Error: la respuesta no se pudo escribir");
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

      if (iteratorObjetos.hasNext()) {
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
