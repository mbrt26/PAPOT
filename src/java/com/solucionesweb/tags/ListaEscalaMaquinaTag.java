package com.solucionesweb.tags;

import com.solucionesweb.losbeans.colaboraciones.ColaboraJobEscala;
import com.solucionesweb.losbeans.fachada.FachadaJobEscala;
import java.io.IOException;
import java.util.Iterator;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.IterationTag;
import javax.servlet.jsp.tagext.TagSupport;

public class ListaEscalaMaquinaTag extends TagSupport
        implements IterationTag {

  ColaboraJobEscala colabora;
  FachadaJobEscala fachada;
  Iterator iteratorBean;
  
  private void inicializarVariablesDeRetorno()
  {
    this.fachada = ((FachadaJobEscala)this.iteratorBean.next());
    

    this.pageContext.setAttribute("idOperacionVar", this.fachada.getIdOperacionStr());
    this.pageContext.setAttribute("idEscalaVar", this.fachada.getIdEscalaStr());
    this.pageContext.setAttribute("nombreEscalaVar", this.fachada.getNombreEscala());
    this.pageContext.setAttribute("idTipoEscalaVar", this.fachada.getIdTipoEscalaStr());
    this.pageContext.setAttribute("estadoVar", this.fachada.getEstadoStr());
  }
  
  public int doStartTag()
    throws JspTagException
  {
    this.colabora = new ColaboraJobEscala();
    

    Vector vectorBean = this.colabora.listaEscalaMaquinaOpcion();
    

    this.iteratorBean = vectorBean.iterator();
    if (!this.iteratorBean.hasNext()) {
      try
      {
        this.pageContext.getOut().write("La consulta no devolvio ningun dato <br/>");
        this.pageContext.getOut().write("<br/>");
        this.pageContext.getOut().write("Por favor comunique al administrador<br/>");
      }
      catch (IOException ex)
      {
        throw new JspTagException("Error: la respuesta no se pudo escribir para los datos");
      }
      finally {}
    }
        inicializarVariablesDeRetorno();

        return EVAL_BODY_INCLUDE;
    }

    public int doAfterBody()
            throws JspTagException {
        if (this.iteratorBean.hasNext()) {
            inicializarVariablesDeRetorno();
            return EVAL_BODY_AGAIN;
        }
        return SKIP_BODY;
    }

    public int doEndTag()
            throws JspTagException {
        return EVAL_PAGE;
    }
}
